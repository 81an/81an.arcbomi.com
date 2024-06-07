package io.flutter.plugins.firebase.firestore;

import android.app.Activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.AggregateQuerySnapshot;
import com.google.firebase.firestore.AggregateSource;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.Source;
import com.google.firebase.firestore.Transaction;
import com.tekartik.sqflite.Constant;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.StandardMethodCodec;
import io.flutter.plugins.firebase.core.FlutterFirebasePlugin;
import io.flutter.plugins.firebase.core.FlutterFirebasePluginRegistry;
import io.flutter.plugins.firebase.firestore.streamhandler.DocumentSnapshotsStreamHandler;
import io.flutter.plugins.firebase.firestore.streamhandler.LoadBundleStreamHandler;
import io.flutter.plugins.firebase.firestore.streamhandler.OnTransactionResultListener;
import io.flutter.plugins.firebase.firestore.streamhandler.QuerySnapshotsStreamHandler;
import io.flutter.plugins.firebase.firestore.streamhandler.SnapshotsInSyncStreamHandler;
import io.flutter.plugins.firebase.firestore.streamhandler.TransactionStreamHandler;
import io.flutter.plugins.firebase.firestore.utils.ExceptionConverter;
import io.flutter.plugins.firebase.firestore.utils.ServerTimestampBehaviorConverter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
public class FlutterFirebaseFirestorePlugin implements FlutterFirebasePlugin, MethodChannel.MethodCallHandler, FlutterPlugin, ActivityAware {
    public static final String DEFAULT_ERROR_CODE = "firebase_firestore";
    private static final String METHOD_CHANNEL_NAME = "plugins.flutter.io/firebase_firestore";
    protected static final HashMap<String, FirebaseFirestore> firestoreInstanceCache = new HashMap<>();
    public static final Map<Integer, DocumentSnapshot.ServerTimestampBehavior> serverTimestampBehaviorHashMap = new HashMap();
    private BinaryMessenger binaryMessenger;
    private MethodChannel channel;
    final StandardMethodCodec MESSAGE_CODEC = new StandardMethodCodec(FlutterFirebaseFirestoreMessageCodec.INSTANCE);
    private final AtomicReference<Activity> activity = new AtomicReference<>(null);
    private final Map<String, Transaction> transactions = new HashMap();
    private final Map<String, EventChannel> eventChannels = new HashMap();
    private final Map<String, EventChannel.StreamHandler> streamHandlers = new HashMap();
    private final Map<String, OnTransactionResultListener> transactionHandlers = new HashMap();

    /* JADX INFO: Access modifiers changed from: protected */
    public static FirebaseFirestore getCachedFirebaseFirestoreInstanceForKey(String str) {
        FirebaseFirestore firebaseFirestore;
        HashMap<String, FirebaseFirestore> hashMap = firestoreInstanceCache;
        synchronized (hashMap) {
            firebaseFirestore = hashMap.get(str);
        }
        return firebaseFirestore;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void setCachedFirebaseFirestoreInstanceForKey(FirebaseFirestore firebaseFirestore, String str) {
        HashMap<String, FirebaseFirestore> hashMap = firestoreInstanceCache;
        synchronized (hashMap) {
            if (hashMap.get(str) == null) {
                hashMap.put(str, firebaseFirestore);
            }
        }
    }

    private static void destroyCachedFirebaseFirestoreInstanceForKey(String str) {
        HashMap<String, FirebaseFirestore> hashMap = firestoreInstanceCache;
        synchronized (hashMap) {
            if (hashMap.get(str) != null) {
                hashMap.remove(str);
            }
        }
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        initInstance(flutterPluginBinding.getBinaryMessenger());
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.channel.setMethodCallHandler(null);
        this.channel = null;
        removeEventListeners();
        this.binaryMessenger = null;
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        attachToActivity(activityPluginBinding);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
        detachToActivity();
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
        attachToActivity(activityPluginBinding);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
        detachToActivity();
    }

    private void attachToActivity(ActivityPluginBinding activityPluginBinding) {
        this.activity.set(activityPluginBinding.getActivity());
    }

    private void detachToActivity() {
        this.activity.set(null);
    }

    private Task<Void> disableNetwork(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$disableNetwork$0(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$disableNetwork$0(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Object obj = map.get("firestore");
            Objects.requireNonNull(obj);
            Tasks.await(((FirebaseFirestore) obj).disableNetwork());
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> enableNetwork(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$enableNetwork$1(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$enableNetwork$1(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Object obj = map.get("firestore");
            Objects.requireNonNull(obj);
            Tasks.await(((FirebaseFirestore) obj).enableNetwork());
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<DocumentSnapshot> transactionGet(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda16
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.this.m353x1ee035df(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$transactionGet$2$io-flutter-plugins-firebase-firestore-FlutterFirebaseFirestorePlugin, reason: not valid java name */
    public /* synthetic */ void m353x1ee035df(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Object obj = map.get("reference");
            Objects.requireNonNull(obj);
            DocumentReference documentReference = (DocumentReference) obj;
            Object obj2 = map.get(Constant.PARAM_TRANSACTION_ID);
            Objects.requireNonNull(obj2);
            String str = (String) obj2;
            Transaction transaction = this.transactions.get(str);
            if (transaction == null) {
                taskCompletionSource.setException(new Exception("Transaction.getDocument(): No transaction handler exists for ID: " + str));
                return;
            }
            taskCompletionSource.setResult(transaction.get(documentReference));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private void transactionStoreResult(Map<String, Object> map) {
        Object obj = map.get(Constant.PARAM_TRANSACTION_ID);
        Objects.requireNonNull(obj);
        Object obj2 = map.get(Constant.PARAM_RESULT);
        Objects.requireNonNull(obj2);
        this.transactionHandlers.get((String) obj).receiveTransactionResponse((Map) obj2);
    }

    private Task<Void> batchCommit(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda18
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$batchCommit$3(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0086, code lost:
    
        if (r8 == 1) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x00da, code lost:
    
        java.util.Objects.requireNonNull(r7);
        r4 = r7;
        r3 = r3.update(r6, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0088, code lost:
    
        if (r8 == 2) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x008b, code lost:
    
        r4 = r4.get(com.tekartik.sqflite.Constant.METHOD_OPTIONS);
        java.util.Objects.requireNonNull(r4);
        r4 = (java.util.Map) r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009a, code lost:
    
        if (r4.get("merge") == null) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00a6, code lost:
    
        if (((java.lang.Boolean) r4.get("merge")).booleanValue() == false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00b9, code lost:
    
        if (r4.get("mergeFields") == null) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00d1, code lost:
    
        java.util.Objects.requireNonNull(r7);
        r3 = r3.set(r6, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00bb, code lost:
    
        r4 = r4.get("mergeFields");
        java.util.Objects.requireNonNull(r4);
        java.util.Objects.requireNonNull(r7);
        r3 = r3.set(r6, r7, com.google.firebase.firestore.SetOptions.mergeFieldPaths((java.util.List) r4));
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00a8, code lost:
    
        java.util.Objects.requireNonNull(r7);
        r3 = r3.set(r6, r7, com.google.firebase.firestore.SetOptions.merge());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static /* synthetic */ void lambda$batchCommit$3(java.util.Map r13, com.google.android.gms.tasks.TaskCompletionSource r14) {
        /*
            java.lang.String r0 = "mergeFields"
            java.lang.String r1 = "merge"
            java.lang.String r2 = "writes"
            java.lang.Object r2 = r13.get(r2)     // Catch: java.lang.Exception -> Lf8
            java.util.Objects.requireNonNull(r2)     // Catch: java.lang.Exception -> Lf8
            java.util.List r2 = (java.util.List) r2     // Catch: java.lang.Exception -> Lf8
            java.lang.String r3 = "firestore"
            java.lang.Object r13 = r13.get(r3)     // Catch: java.lang.Exception -> Lf8
            java.util.Objects.requireNonNull(r13)     // Catch: java.lang.Exception -> Lf8
            com.google.firebase.firestore.FirebaseFirestore r13 = (com.google.firebase.firestore.FirebaseFirestore) r13     // Catch: java.lang.Exception -> Lf8
            com.google.firebase.firestore.WriteBatch r3 = r13.batch()     // Catch: java.lang.Exception -> Lf8
            java.util.Iterator r2 = r2.iterator()     // Catch: java.lang.Exception -> Lf8
        L22:
            boolean r4 = r2.hasNext()     // Catch: java.lang.Exception -> Lf8
            if (r4 == 0) goto Lec
            java.lang.Object r4 = r2.next()     // Catch: java.lang.Exception -> Lf8
            java.util.Map r4 = (java.util.Map) r4     // Catch: java.lang.Exception -> Lf8
            java.lang.String r5 = "type"
            java.lang.Object r5 = r4.get(r5)     // Catch: java.lang.Exception -> Lf8
            java.util.Objects.requireNonNull(r5)     // Catch: java.lang.Exception -> Lf8
            java.lang.String r5 = (java.lang.String) r5     // Catch: java.lang.Exception -> Lf8
            java.lang.String r6 = "path"
            java.lang.Object r6 = r4.get(r6)     // Catch: java.lang.Exception -> Lf8
            java.util.Objects.requireNonNull(r6)     // Catch: java.lang.Exception -> Lf8
            java.lang.String r6 = (java.lang.String) r6     // Catch: java.lang.Exception -> Lf8
            java.lang.String r7 = "data"
            java.lang.Object r7 = r4.get(r7)     // Catch: java.lang.Exception -> Lf8
            java.util.Map r7 = (java.util.Map) r7     // Catch: java.lang.Exception -> Lf8
            com.google.firebase.firestore.DocumentReference r6 = r13.document(r6)     // Catch: java.lang.Exception -> Lf8
            r8 = -1
            int r9 = r5.hashCode()     // Catch: java.lang.Exception -> Lf8
            r10 = -1785516855(0xffffffff95932cc9, float:-5.9443486E-26)
            r11 = 2
            r12 = 1
            if (r9 == r10) goto L7b
            r10 = 81986(0x14042, float:1.14887E-40)
            if (r9 == r10) goto L71
            r10 = 2012838315(0x77f979ab, float:1.0119919E34)
            if (r9 == r10) goto L67
            goto L84
        L67:
            java.lang.String r9 = "DELETE"
            boolean r5 = r5.equals(r9)     // Catch: java.lang.Exception -> Lf8
            if (r5 == 0) goto L84
            r8 = 0
            goto L84
        L71:
            java.lang.String r9 = "SET"
            boolean r5 = r5.equals(r9)     // Catch: java.lang.Exception -> Lf8
            if (r5 == 0) goto L84
            r8 = 2
            goto L84
        L7b:
            java.lang.String r9 = "UPDATE"
            boolean r5 = r5.equals(r9)     // Catch: java.lang.Exception -> Lf8
            if (r5 == 0) goto L84
            r8 = 1
        L84:
            if (r8 == 0) goto Le6
            if (r8 == r12) goto Lda
            if (r8 == r11) goto L8b
            goto L22
        L8b:
            java.lang.String r5 = "options"
            java.lang.Object r4 = r4.get(r5)     // Catch: java.lang.Exception -> Lf8
            java.util.Objects.requireNonNull(r4)     // Catch: java.lang.Exception -> Lf8
            java.util.Map r4 = (java.util.Map) r4     // Catch: java.lang.Exception -> Lf8
            java.lang.Object r5 = r4.get(r1)     // Catch: java.lang.Exception -> Lf8
            if (r5 == 0) goto Lb5
            java.lang.Object r5 = r4.get(r1)     // Catch: java.lang.Exception -> Lf8
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch: java.lang.Exception -> Lf8
            boolean r5 = r5.booleanValue()     // Catch: java.lang.Exception -> Lf8
            if (r5 == 0) goto Lb5
            java.util.Objects.requireNonNull(r7)     // Catch: java.lang.Exception -> Lf8
            com.google.firebase.firestore.SetOptions r4 = com.google.firebase.firestore.SetOptions.merge()     // Catch: java.lang.Exception -> Lf8
            com.google.firebase.firestore.WriteBatch r3 = r3.set(r6, r7, r4)     // Catch: java.lang.Exception -> Lf8
            goto L22
        Lb5:
            java.lang.Object r5 = r4.get(r0)     // Catch: java.lang.Exception -> Lf8
            if (r5 == 0) goto Ld1
            java.lang.Object r4 = r4.get(r0)     // Catch: java.lang.Exception -> Lf8
            java.util.Objects.requireNonNull(r4)     // Catch: java.lang.Exception -> Lf8
            java.util.List r4 = (java.util.List) r4     // Catch: java.lang.Exception -> Lf8
            java.util.Objects.requireNonNull(r7)     // Catch: java.lang.Exception -> Lf8
            com.google.firebase.firestore.SetOptions r4 = com.google.firebase.firestore.SetOptions.mergeFieldPaths(r4)     // Catch: java.lang.Exception -> Lf8
            com.google.firebase.firestore.WriteBatch r3 = r3.set(r6, r7, r4)     // Catch: java.lang.Exception -> Lf8
            goto L22
        Ld1:
            java.util.Objects.requireNonNull(r7)     // Catch: java.lang.Exception -> Lf8
            com.google.firebase.firestore.WriteBatch r3 = r3.set(r6, r7)     // Catch: java.lang.Exception -> Lf8
            goto L22
        Lda:
            java.util.Objects.requireNonNull(r7)     // Catch: java.lang.Exception -> Lf8
            r4 = r7
            java.util.Map r4 = (java.util.Map) r4     // Catch: java.lang.Exception -> Lf8
            com.google.firebase.firestore.WriteBatch r3 = r3.update(r6, r7)     // Catch: java.lang.Exception -> Lf8
            goto L22
        Le6:
            com.google.firebase.firestore.WriteBatch r3 = r3.delete(r6)     // Catch: java.lang.Exception -> Lf8
            goto L22
        Lec:
            com.google.android.gms.tasks.Task r13 = r3.commit()     // Catch: java.lang.Exception -> Lf8
            com.google.android.gms.tasks.Tasks.await(r13)     // Catch: java.lang.Exception -> Lf8
            r13 = 0
            r14.setResult(r13)     // Catch: java.lang.Exception -> Lf8
            goto Lfc
        Lf8:
            r13 = move-exception
            r14.setException(r13)
        Lfc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin.lambda$batchCommit$3(java.util.Map, com.google.android.gms.tasks.TaskCompletionSource):void");
    }

    private Task<QuerySnapshot> queryGet(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda15
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.this.m352xab92b6c7(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$queryGet$4$io-flutter-plugins-firebase-firestore-FlutterFirebaseFirestorePlugin, reason: not valid java name */
    public /* synthetic */ void m352xab92b6c7(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Source source = getSource(map);
            Query query = (Query) map.get("query");
            if (query == null) {
                taskCompletionSource.setException(new IllegalArgumentException("An error occurred while parsing query arguments, see native logs for more information. Please report this issue."));
                return;
            }
            QuerySnapshot querySnapshot = (QuerySnapshot) Tasks.await(query.get(source));
            saveTimestampBehavior(map, querySnapshot.hashCode());
            taskCompletionSource.setResult(querySnapshot);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<DocumentSnapshot> documentGet(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.this.m349x369ff2d3(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$documentGet$5$io-flutter-plugins-firebase-firestore-FlutterFirebaseFirestorePlugin, reason: not valid java name */
    public /* synthetic */ void m349x369ff2d3(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Source source = getSource(map);
            Object obj = map.get("reference");
            Objects.requireNonNull(obj);
            DocumentSnapshot documentSnapshot = (DocumentSnapshot) Tasks.await(((DocumentReference) obj).get(source));
            saveTimestampBehavior(map, documentSnapshot.hashCode());
            taskCompletionSource.setResult(documentSnapshot);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<QuerySnapshot> namedQueryGet(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.this.m350x82976326(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$namedQueryGet$6$io-flutter-plugins-firebase-firestore-FlutterFirebaseFirestorePlugin, reason: not valid java name */
    public /* synthetic */ void m350x82976326(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Source source = getSource(map);
            Object obj = map.get("name");
            Objects.requireNonNull(obj);
            Object obj2 = map.get("firestore");
            Objects.requireNonNull(obj2);
            Query query = (Query) Tasks.await(((FirebaseFirestore) obj2).getNamedQuery((String) obj));
            if (query == null) {
                taskCompletionSource.setException(new NullPointerException("Named query has not been found. Please check it has been loaded properly via loadBundle()."));
                return;
            }
            QuerySnapshot querySnapshot = (QuerySnapshot) Tasks.await(query.get(source));
            saveTimestampBehavior(map, querySnapshot.hashCode());
            taskCompletionSource.setResult(querySnapshot);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private void saveTimestampBehavior(Map<String, Object> map, int i) {
        serverTimestampBehaviorHashMap.put(Integer.valueOf(i), ServerTimestampBehaviorConverter.toServerTimestampBehavior((String) map.get("serverTimestampBehavior")));
    }

    private Task<Void> documentSet(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$documentSet$7(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$documentSet$7(Map map, TaskCompletionSource taskCompletionSource) {
        Task<Void> task;
        try {
            Object obj = map.get("reference");
            Objects.requireNonNull(obj);
            DocumentReference documentReference = (DocumentReference) obj;
            Object obj2 = map.get("data");
            Objects.requireNonNull(obj2);
            Map map2 = (Map) obj2;
            Object obj3 = map.get(Constant.METHOD_OPTIONS);
            Objects.requireNonNull(obj3);
            Map map3 = (Map) obj3;
            if (map3.get("merge") != null && ((Boolean) map3.get("merge")).booleanValue()) {
                task = documentReference.set(map2, SetOptions.merge());
            } else if (map3.get("mergeFields") != null) {
                Object obj4 = map3.get("mergeFields");
                Objects.requireNonNull(obj4);
                task = documentReference.set(map2, SetOptions.mergeFieldPaths((List) obj4));
            } else {
                task = documentReference.set(map2);
            }
            taskCompletionSource.setResult((Void) Tasks.await(task));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> documentUpdate(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$documentUpdate$8(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$documentUpdate$8(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Object obj = map.get("reference");
            Objects.requireNonNull(obj);
            DocumentReference documentReference = (DocumentReference) obj;
            Object obj2 = map.get("data");
            Objects.requireNonNull(obj2);
            Map map2 = (Map) obj2;
            FieldPath fieldPath = (FieldPath) map2.keySet().iterator().next();
            Object obj3 = map2.get(fieldPath);
            ArrayList arrayList = new ArrayList();
            for (FieldPath fieldPath2 : map2.keySet()) {
                if (!fieldPath2.equals(fieldPath)) {
                    arrayList.add(fieldPath2);
                    arrayList.add(map2.get(fieldPath2));
                }
            }
            taskCompletionSource.setResult((Void) Tasks.await(documentReference.update(fieldPath, obj3, arrayList.toArray())));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> documentDelete(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$documentDelete$9(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$documentDelete$9(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Object obj = map.get("reference");
            Objects.requireNonNull(obj);
            taskCompletionSource.setResult((Void) Tasks.await(((DocumentReference) obj).delete()));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> clearPersistence(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$clearPersistence$10(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$clearPersistence$10(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Object obj = map.get("firestore");
            Objects.requireNonNull(obj);
            taskCompletionSource.setResult((Void) Tasks.await(((FirebaseFirestore) obj).clearPersistence()));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> terminate(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$terminate$11(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$terminate$11(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Object obj = map.get("firestore");
            Objects.requireNonNull(obj);
            FirebaseFirestore firebaseFirestore = (FirebaseFirestore) obj;
            Tasks.await(firebaseFirestore.terminate());
            destroyCachedFirebaseFirestoreInstanceForKey(firebaseFirestore.getApp().getName());
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> waitForPendingWrites(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$waitForPendingWrites$12(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$waitForPendingWrites$12(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Object obj = map.get("firestore");
            Objects.requireNonNull(obj);
            taskCompletionSource.setResult((Void) Tasks.await(((FirebaseFirestore) obj).waitForPendingWrites()));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> aggregateQuery(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda17
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$aggregateQuery$13(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$aggregateQuery$13(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Object obj = map.get("query");
            Objects.requireNonNull(obj);
            AggregateQuerySnapshot aggregateQuerySnapshot = (AggregateQuerySnapshot) Tasks.await(((Query) obj).count().get(AggregateSource.SERVER));
            HashMap hashMap = new HashMap();
            hashMap.put("count", Long.valueOf(aggregateQuerySnapshot.getCount()));
            taskCompletionSource.setResult(hashMap);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> setIndexConfiguration(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$setIndexConfiguration$14(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setIndexConfiguration$14(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Object obj = map.get("firestore");
            Objects.requireNonNull(obj);
            Object obj2 = map.get("indexConfiguration");
            Objects.requireNonNull(obj2);
            Tasks.await(((FirebaseFirestore) obj).setIndexConfiguration((String) obj2));
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, final MethodChannel.Result result) {
        Task namedQueryGet;
        String str = methodCall.method;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2105138801:
                if (str.equals("Firestore#namedQueryGet")) {
                    c = 0;
                    break;
                }
                break;
            case -1414526391:
                if (str.equals("SnapshotsInSync#setup")) {
                    c = 1;
                    break;
                }
                break;
            case -1345933986:
                if (str.equals("Firestore#disableNetwork")) {
                    c = 2;
                    break;
                }
                break;
            case -1269951812:
                if (str.equals("DocumentReference#snapshots")) {
                    c = 3;
                    break;
                }
                break;
            case -1237005313:
                if (str.equals("WriteBatch#commit")) {
                    c = 4;
                    break;
                }
                break;
            case -1205229031:
                if (str.equals("Transaction#storeResult")) {
                    c = 5;
                    break;
                }
                break;
            case -787001759:
                if (str.equals("Transaction#create")) {
                    c = 6;
                    break;
                }
                break;
            case -780009068:
                if (str.equals("LoadBundle#snapshots")) {
                    c = 7;
                    break;
                }
                break;
            case -658486978:
                if (str.equals("DocumentReference#delete")) {
                    c = '\b';
                    break;
                }
                break;
            case -231135191:
                if (str.equals("Firestore#terminate")) {
                    c = '\t';
                    break;
                }
                break;
            case -161874852:
                if (str.equals("DocumentReference#update")) {
                    c = '\n';
                    break;
                }
                break;
            case 33139875:
                if (str.equals("DocumentReference#get")) {
                    c = 11;
                    break;
                }
                break;
            case 33151407:
                if (str.equals("DocumentReference#set")) {
                    c = '\f';
                    break;
                }
                break;
            case 68800788:
                if (str.equals("Query#snapshots")) {
                    c = '\r';
                    break;
                }
                break;
            case 195628283:
                if (str.equals("Query#get")) {
                    c = 14;
                    break;
                }
                break;
            case 264528913:
                if (str.equals("Transaction#get")) {
                    c = 15;
                    break;
                }
                break;
            case 406828874:
                if (str.equals("Firestore#clearPersistence")) {
                    c = 16;
                    break;
                }
                break;
            case 515912559:
                if (str.equals("Firestore#waitForPendingWrites")) {
                    c = 17;
                    break;
                }
                break;
            case 783577717:
                if (str.equals("AggregateQuery#count")) {
                    c = 18;
                    break;
                }
                break;
            case 915784462:
                if (str.equals("Firestore#setIndexConfiguration")) {
                    c = 19;
                    break;
                }
                break;
            case 1562339571:
                if (str.equals("Firestore#enableNetwork")) {
                    c = 20;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                namedQueryGet = namedQueryGet((Map) methodCall.arguments());
                break;
            case 1:
                result.success(registerEventChannel("plugins.flutter.io/firebase_firestore/snapshotsInSync", new SnapshotsInSyncStreamHandler()));
                return;
            case 2:
                namedQueryGet = disableNetwork((Map) methodCall.arguments());
                break;
            case 3:
                result.success(registerEventChannel("plugins.flutter.io/firebase_firestore/document", new DocumentSnapshotsStreamHandler()));
                return;
            case 4:
                namedQueryGet = batchCommit((Map) methodCall.arguments());
                break;
            case 5:
                transactionStoreResult((Map) methodCall.arguments());
                result.success(null);
                return;
            case 6:
                final String lowerCase = UUID.randomUUID().toString().toLowerCase(Locale.US);
                TransactionStreamHandler transactionStreamHandler = new TransactionStreamHandler(new TransactionStreamHandler.OnTransactionStartedListener() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda10
                    @Override // io.flutter.plugins.firebase.firestore.streamhandler.TransactionStreamHandler.OnTransactionStartedListener
                    public final void onStarted(Transaction transaction) {
                        FlutterFirebaseFirestorePlugin.this.m351x44d105f5(lowerCase, transaction);
                    }
                });
                registerEventChannel("plugins.flutter.io/firebase_firestore/transaction", lowerCase, transactionStreamHandler);
                this.transactionHandlers.put(lowerCase, transactionStreamHandler);
                result.success(lowerCase);
                return;
            case 7:
                result.success(registerEventChannel("plugins.flutter.io/firebase_firestore/loadBundle", new LoadBundleStreamHandler()));
                return;
            case '\b':
                namedQueryGet = documentDelete((Map) methodCall.arguments());
                break;
            case '\t':
                namedQueryGet = terminate((Map) methodCall.arguments());
                break;
            case '\n':
                namedQueryGet = documentUpdate((Map) methodCall.arguments());
                break;
            case 11:
                namedQueryGet = documentGet((Map) methodCall.arguments());
                break;
            case '\f':
                namedQueryGet = documentSet((Map) methodCall.arguments());
                break;
            case '\r':
                result.success(registerEventChannel("plugins.flutter.io/firebase_firestore/query", new QuerySnapshotsStreamHandler()));
                return;
            case 14:
                namedQueryGet = queryGet((Map) methodCall.arguments());
                break;
            case 15:
                namedQueryGet = transactionGet((Map) methodCall.arguments());
                break;
            case 16:
                namedQueryGet = clearPersistence((Map) methodCall.arguments());
                break;
            case 17:
                namedQueryGet = waitForPendingWrites((Map) methodCall.arguments());
                break;
            case 18:
                namedQueryGet = aggregateQuery((Map) methodCall.arguments());
                break;
            case 19:
                namedQueryGet = setIndexConfiguration((Map) methodCall.arguments());
                break;
            case 20:
                namedQueryGet = enableNetwork((Map) methodCall.arguments());
                break;
            default:
                result.notImplemented();
                return;
        }
        namedQueryGet.addOnCompleteListener(new OnCompleteListener() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                FlutterFirebaseFirestorePlugin.lambda$onMethodCall$16(MethodChannel.Result.this, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onMethodCall$15$io-flutter-plugins-firebase-firestore-FlutterFirebaseFirestorePlugin, reason: not valid java name */
    public /* synthetic */ void m351x44d105f5(String str, Transaction transaction) {
        this.transactions.put(str, transaction);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onMethodCall$16(MethodChannel.Result result, Task task) {
        if (task.isSuccessful()) {
            result.success(task.getResult());
            return;
        }
        Exception exception = task.getException();
        result.error(DEFAULT_ERROR_CODE, exception != null ? exception.getMessage() : null, ExceptionConverter.createDetails(exception));
    }

    private void initInstance(BinaryMessenger binaryMessenger) {
        this.binaryMessenger = binaryMessenger;
        MethodChannel methodChannel = new MethodChannel(binaryMessenger, METHOD_CHANNEL_NAME, this.MESSAGE_CODEC);
        this.channel = methodChannel;
        methodChannel.setMethodCallHandler(this);
        FlutterFirebasePluginRegistry.registerPlugin(METHOD_CHANNEL_NAME, this);
    }

    private Source getSource(Map<String, Object> map) {
        Object obj = map.get("source");
        Objects.requireNonNull(obj);
        String str = (String) obj;
        str.hashCode();
        if (str.equals("server")) {
            return Source.SERVER;
        }
        if (str.equals("cache")) {
            return Source.CACHE;
        }
        return Source.DEFAULT;
    }

    @Override // io.flutter.plugins.firebase.core.FlutterFirebasePlugin
    public Task<Map<String, Object>> getPluginConstantsForFirebaseApp(FirebaseApp firebaseApp) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.lambda$getPluginConstantsForFirebaseApp$17(TaskCompletionSource.this);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getPluginConstantsForFirebaseApp$17(TaskCompletionSource taskCompletionSource) {
        try {
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    @Override // io.flutter.plugins.firebase.core.FlutterFirebasePlugin
    public Task<Void> didReinitializeFirebaseCore() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseFirestorePlugin.this.m348x9355d022(taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$didReinitializeFirebaseCore$18$io-flutter-plugins-firebase-firestore-FlutterFirebaseFirestorePlugin, reason: not valid java name */
    public /* synthetic */ void m348x9355d022(TaskCompletionSource taskCompletionSource) {
        try {
            for (FirebaseApp firebaseApp : FirebaseApp.getApps(null)) {
                Tasks.await(FirebaseFirestore.getInstance(firebaseApp).terminate());
                destroyCachedFirebaseFirestoreInstanceForKey(firebaseApp.getName());
            }
            removeEventListeners();
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private String registerEventChannel(String str, EventChannel.StreamHandler streamHandler) {
        return registerEventChannel(str, UUID.randomUUID().toString().toLowerCase(Locale.US), streamHandler);
    }

    private String registerEventChannel(String str, String str2, EventChannel.StreamHandler streamHandler) {
        EventChannel eventChannel = new EventChannel(this.binaryMessenger, str + "/" + str2, this.MESSAGE_CODEC);
        eventChannel.setStreamHandler(streamHandler);
        this.eventChannels.put(str2, eventChannel);
        this.streamHandlers.put(str2, streamHandler);
        return str2;
    }

    private void removeEventListeners() {
        Iterator<String> it = this.eventChannels.keySet().iterator();
        while (it.hasNext()) {
            this.eventChannels.get(it.next()).setStreamHandler(null);
        }
        this.eventChannels.clear();
        Iterator<String> it2 = this.streamHandlers.keySet().iterator();
        while (it2.hasNext()) {
            this.streamHandlers.get(it2.next()).onCancel(null);
        }
        this.streamHandlers.clear();
        this.transactionHandlers.clear();
    }
}
