package io.flutter.plugins.firebase.firestore.streamhandler;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.firestore.TransactionOptions;
import com.tekartik.sqflite.Constant;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugins.firebase.auth.Constants;
import io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestoreTransactionResult;
import io.flutter.plugins.firebase.firestore.utils.ExceptionConverter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class TransactionStreamHandler implements OnTransactionResultListener, EventChannel.StreamHandler {
    final OnTransactionStartedListener onTransactionStartedListener;
    final Semaphore semaphore = new Semaphore(0);
    final Map<String, Object> response = new HashMap();
    final Handler mainLooper = new Handler(Looper.getMainLooper());

    /* loaded from: classes2.dex */
    public interface OnTransactionStartedListener {
        void onStarted(Transaction transaction);
    }

    public TransactionStreamHandler(OnTransactionStartedListener onTransactionStartedListener) {
        this.onTransactionStartedListener = onTransactionStartedListener;
    }

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onListen(Object obj, final EventChannel.EventSink eventSink) {
        final long j;
        Map map = (Map) obj;
        Object obj2 = map.get("firestore");
        Objects.requireNonNull(obj2);
        final FirebaseFirestore firebaseFirestore = (FirebaseFirestore) obj2;
        Object obj3 = map.get(Constants.TIMEOUT);
        if (obj3 instanceof Long) {
            j = (Long) obj3;
        } else if (obj3 instanceof Integer) {
            j = Long.valueOf(((Integer) obj3).intValue());
        } else {
            j = 5000L;
        }
        firebaseFirestore.runTransaction(new TransactionOptions.Builder().setMaxAttempts(((Integer) map.get("maxAttempts")).intValue()).build(), new Transaction.Function() { // from class: io.flutter.plugins.firebase.firestore.streamhandler.TransactionStreamHandler$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.Transaction.Function
            public final Object apply(Transaction transaction) {
                return TransactionStreamHandler.this.m357xe9e08326(firebaseFirestore, eventSink, j, transaction);
            }
        }).addOnCompleteListener(new OnCompleteListener() { // from class: io.flutter.plugins.firebase.firestore.streamhandler.TransactionStreamHandler$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                TransactionStreamHandler.this.m358x1be32664(firebaseFirestore, eventSink, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Failed to find 'out' block for switch in B:21:0x00a3. Please report as an issue. */
    /* renamed from: lambda$onListen$1$io-flutter-plugins-firebase-firestore-streamhandler-TransactionStreamHandler, reason: not valid java name */
    public /* synthetic */ FlutterFirebaseFirestoreTransactionResult m357xe9e08326(FirebaseFirestore firebaseFirestore, final EventChannel.EventSink eventSink, Long l, Transaction transaction) throws FirebaseFirestoreException {
        this.onTransactionStartedListener.onStarted(transaction);
        final HashMap hashMap = new HashMap();
        hashMap.put("appName", firebaseFirestore.getApp().getName());
        this.mainLooper.post(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.streamhandler.TransactionStreamHandler$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                EventChannel.EventSink.this.success(hashMap);
            }
        });
        try {
            if (!this.semaphore.tryAcquire(l.longValue(), TimeUnit.MILLISECONDS)) {
                return FlutterFirebaseFirestoreTransactionResult.failed(new FirebaseFirestoreException("timed out", FirebaseFirestoreException.Code.DEADLINE_EXCEEDED));
            }
            if (this.response.isEmpty()) {
                return FlutterFirebaseFirestoreTransactionResult.complete();
            }
            if ("ERROR".equalsIgnoreCase((String) this.response.get("type"))) {
                return FlutterFirebaseFirestoreTransactionResult.complete();
            }
            for (Map map : (List) this.response.get("commands")) {
                Object obj = map.get("type");
                Objects.requireNonNull(obj);
                String str = (String) obj;
                Object obj2 = map.get(io.flutter.plugins.firebase.database.Constants.PATH);
                Objects.requireNonNull(obj2);
                DocumentReference document = firebaseFirestore.document((String) obj2);
                Map<String, Object> map2 = (Map) map.get("data");
                str.hashCode();
                char c = 65535;
                switch (str.hashCode()) {
                    case -1785516855:
                        if (str.equals("UPDATE")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 81986:
                        if (str.equals("SET")) {
                            c = 1;
                            break;
                        }
                        break;
                    case 2012838315:
                        if (str.equals("DELETE")) {
                            c = 2;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        Objects.requireNonNull(map2);
                        transaction.update(document, map2);
                        break;
                    case 1:
                        Object obj3 = map.get(Constant.METHOD_OPTIONS);
                        Objects.requireNonNull(obj3);
                        Map map3 = (Map) obj3;
                        SetOptions setOptions = null;
                        if (map3.get("merge") != null && ((Boolean) map3.get("merge")).booleanValue()) {
                            setOptions = SetOptions.merge();
                        } else if (map3.get("mergeFields") != null) {
                            Object obj4 = map3.get("mergeFields");
                            Objects.requireNonNull(obj4);
                            setOptions = SetOptions.mergeFieldPaths((List) obj4);
                        }
                        if (setOptions == null) {
                            Objects.requireNonNull(map2);
                            transaction.set(document, map2);
                            break;
                        } else {
                            Objects.requireNonNull(map2);
                            transaction.set(document, map2, setOptions);
                            break;
                        }
                        break;
                    case 2:
                        transaction.delete(document);
                        break;
                }
            }
            return FlutterFirebaseFirestoreTransactionResult.complete();
        } catch (InterruptedException unused) {
            return FlutterFirebaseFirestoreTransactionResult.failed(new FirebaseFirestoreException("interrupted", FirebaseFirestoreException.Code.DEADLINE_EXCEEDED));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onListen$3$io-flutter-plugins-firebase-firestore-streamhandler-TransactionStreamHandler, reason: not valid java name */
    public /* synthetic */ void m358x1be32664(FirebaseFirestore firebaseFirestore, final EventChannel.EventSink eventSink, Task task) {
        final HashMap hashMap = new HashMap();
        if (task.getException() != null || ((FlutterFirebaseFirestoreTransactionResult) task.getResult()).exception != null) {
            Exception exception = task.getException() != null ? task.getException() : ((FlutterFirebaseFirestoreTransactionResult) task.getResult()).exception;
            hashMap.put("appName", firebaseFirestore.getApp().getName());
            hashMap.put("error", ExceptionConverter.createDetails(exception));
        } else if (task.getResult() != null) {
            hashMap.put("complete", true);
        }
        this.mainLooper.post(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.streamhandler.TransactionStreamHandler$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                TransactionStreamHandler.lambda$onListen$2(EventChannel.EventSink.this, hashMap);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onListen$2(EventChannel.EventSink eventSink, HashMap hashMap) {
        eventSink.success(hashMap);
        eventSink.endOfStream();
    }

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onCancel(Object obj) {
        this.semaphore.release();
    }

    @Override // io.flutter.plugins.firebase.firestore.streamhandler.OnTransactionResultListener
    public void receiveTransactionResponse(Map<String, Object> map) {
        this.response.putAll(map);
        this.semaphore.release();
    }
}
