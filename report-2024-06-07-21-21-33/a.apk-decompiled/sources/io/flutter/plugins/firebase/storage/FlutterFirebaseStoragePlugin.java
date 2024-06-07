package io.flutter.plugins.firebase.storage;

import android.net.Uri;
import android.util.Base64;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.tekartik.sqflite.Constant;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.firebase.core.FlutterFirebasePlugin;
import io.flutter.plugins.firebase.core.FlutterFirebasePluginRegistry;
import io.flutter.plugins.firebase.database.Constants;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes2.dex */
public class FlutterFirebaseStoragePlugin implements FlutterFirebasePlugin, MethodChannel.MethodCallHandler, FlutterPlugin {
    private MethodChannel channel;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, Object> parseMetadata(StorageMetadata storageMetadata) {
        if (storageMetadata == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        if (storageMetadata.getName() != null) {
            hashMap.put("name", storageMetadata.getName());
        }
        if (storageMetadata.getBucket() != null) {
            hashMap.put("bucket", storageMetadata.getBucket());
        }
        if (storageMetadata.getGeneration() != null) {
            hashMap.put("generation", storageMetadata.getGeneration());
        }
        if (storageMetadata.getMetadataGeneration() != null) {
            hashMap.put("metadataGeneration", storageMetadata.getMetadataGeneration());
        }
        hashMap.put("fullPath", storageMetadata.getPath());
        hashMap.put("size", Long.valueOf(storageMetadata.getSizeBytes()));
        hashMap.put("creationTimeMillis", Long.valueOf(storageMetadata.getCreationTimeMillis()));
        hashMap.put("updatedTimeMillis", Long.valueOf(storageMetadata.getUpdatedTimeMillis()));
        if (storageMetadata.getMd5Hash() != null) {
            hashMap.put("md5Hash", storageMetadata.getMd5Hash());
        }
        if (storageMetadata.getCacheControl() != null) {
            hashMap.put("cacheControl", storageMetadata.getCacheControl());
        }
        if (storageMetadata.getContentDisposition() != null) {
            hashMap.put("contentDisposition", storageMetadata.getContentDisposition());
        }
        if (storageMetadata.getContentEncoding() != null) {
            hashMap.put("contentEncoding", storageMetadata.getContentEncoding());
        }
        if (storageMetadata.getContentLanguage() != null) {
            hashMap.put("contentLanguage", storageMetadata.getContentLanguage());
        }
        if (storageMetadata.getContentType() != null) {
            hashMap.put("contentType", storageMetadata.getContentType());
        }
        HashMap hashMap2 = new HashMap();
        for (String str : storageMetadata.getCustomMetadataKeys()) {
            if (storageMetadata.getCustomMetadata(str) == null) {
                hashMap2.put(str, "");
            } else {
                String customMetadata = storageMetadata.getCustomMetadata(str);
                Objects.requireNonNull(customMetadata);
                hashMap2.put(str, customMetadata);
            }
        }
        hashMap.put("customMetadata", hashMap2);
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, String> getExceptionDetails(Exception exc) {
        FlutterFirebaseStorageException flutterFirebaseStorageException;
        Throwable cause;
        HashMap hashMap = new HashMap();
        if (exc instanceof StorageException) {
            flutterFirebaseStorageException = new FlutterFirebaseStorageException(exc, exc.getCause());
        } else if (exc.getCause() == null || !(exc.getCause() instanceof StorageException)) {
            flutterFirebaseStorageException = null;
        } else {
            StorageException storageException = (StorageException) exc.getCause();
            if (exc.getCause().getCause() != null) {
                cause = exc.getCause().getCause();
            } else {
                cause = exc.getCause();
            }
            flutterFirebaseStorageException = new FlutterFirebaseStorageException(storageException, cause);
        }
        if (flutterFirebaseStorageException != null) {
            hashMap.put("code", flutterFirebaseStorageException.getCode());
            hashMap.put("message", flutterFirebaseStorageException.getMessage());
        }
        return hashMap;
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        initInstance(flutterPluginBinding.getBinaryMessenger());
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        FlutterFirebaseStorageTask.cancelInProgressTasks();
        this.channel.setMethodCallHandler(null);
        this.channel = null;
    }

    private void initInstance(BinaryMessenger binaryMessenger) {
        MethodChannel methodChannel = new MethodChannel(binaryMessenger, "plugins.flutter.io/firebase_storage");
        this.channel = methodChannel;
        methodChannel.setMethodCallHandler(this);
        FlutterFirebasePluginRegistry.registerPlugin("plugins.flutter.io/firebase_storage", this);
    }

    private FirebaseStorage getStorage(Map<String, Object> map) {
        FirebaseStorage firebaseStorage;
        Object obj = map.get("appName");
        Objects.requireNonNull(obj);
        FirebaseApp firebaseApp = FirebaseApp.getInstance((String) obj);
        String str = (String) map.get("bucket");
        if (str == null) {
            firebaseStorage = FirebaseStorage.getInstance(firebaseApp);
        } else {
            firebaseStorage = FirebaseStorage.getInstance(firebaseApp, "gs://" + str);
        }
        Object obj2 = map.get("maxOperationRetryTime");
        if (obj2 != null) {
            firebaseStorage.setMaxOperationRetryTimeMillis(getLongValue(obj2).longValue());
        }
        Object obj3 = map.get("maxDownloadRetryTime");
        if (obj3 != null) {
            firebaseStorage.setMaxDownloadRetryTimeMillis(getLongValue(obj3).longValue());
        }
        Object obj4 = map.get("maxUploadRetryTime");
        if (obj4 != null) {
            firebaseStorage.setMaxUploadRetryTimeMillis(getLongValue(obj4).longValue());
        }
        return firebaseStorage;
    }

    private StorageReference getReference(Map<String, Object> map) {
        Object obj = map.get(Constants.PATH);
        Objects.requireNonNull(obj);
        return getStorage(map).getReference((String) obj);
    }

    private Map<String, Object> parseListResult(ListResult listResult) {
        HashMap hashMap = new HashMap();
        if (listResult.getPageToken() != null) {
            hashMap.put("nextPageToken", listResult.getPageToken());
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<StorageReference> it = listResult.getItems().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getPath());
        }
        Iterator<StorageReference> it2 = listResult.getPrefixes().iterator();
        while (it2.hasNext()) {
            arrayList2.add(it2.next().getPath());
        }
        hashMap.put(FirebaseAnalytics.Param.ITEMS, arrayList);
        hashMap.put("prefixes", arrayList2);
        return hashMap;
    }

    private Task<Void> useEmulator(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseStoragePlugin.this.m378xe0ecd9d7(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$useEmulator$0$io-flutter-plugins-firebase-storage-FlutterFirebaseStoragePlugin, reason: not valid java name */
    public /* synthetic */ void m378xe0ecd9d7(Map map, TaskCompletionSource taskCompletionSource) {
        FirebaseStorage storage = getStorage(map);
        Object obj = map.get(io.flutter.plugins.firebase.auth.Constants.HOST);
        Objects.requireNonNull(obj);
        Object obj2 = map.get(io.flutter.plugins.firebase.auth.Constants.PORT);
        Objects.requireNonNull(obj2);
        storage.useEmulator((String) obj, ((Integer) obj2).intValue());
        taskCompletionSource.setResult(null);
    }

    private Task<Void> referenceDelete(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseStoragePlugin.this.m367xc7f0b61c(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$referenceDelete$1$io-flutter-plugins-firebase-storage-FlutterFirebaseStoragePlugin, reason: not valid java name */
    public /* synthetic */ void m367xc7f0b61c(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Tasks.await(getReference(map).delete());
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> referenceGetDownloadURL(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseStoragePlugin.this.m369x60f65595(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$referenceGetDownloadURL$2$io-flutter-plugins-firebase-storage-FlutterFirebaseStoragePlugin, reason: not valid java name */
    public /* synthetic */ void m369x60f65595(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            Uri uri = (Uri) Tasks.await(getReference(map).getDownloadUrl());
            HashMap hashMap = new HashMap();
            hashMap.put("downloadURL", uri.toString());
            taskCompletionSource.setResult(hashMap);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<byte[]> referenceGetData(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseStoragePlugin.this.m368x332a021(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$referenceGetData$3$io-flutter-plugins-firebase-storage-FlutterFirebaseStoragePlugin, reason: not valid java name */
    public /* synthetic */ void m368x332a021(Map map, TaskCompletionSource taskCompletionSource) {
        Object obj = map.get("maxSize");
        Objects.requireNonNull(obj);
        Integer num = (Integer) obj;
        try {
            taskCompletionSource.setResult((byte[]) Tasks.await(getReference(map).getBytes(num.intValue())));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> referenceGetMetadata(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseStoragePlugin.this.m370xd7978e9b(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$referenceGetMetadata$4$io-flutter-plugins-firebase-storage-FlutterFirebaseStoragePlugin, reason: not valid java name */
    public /* synthetic */ void m370xd7978e9b(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            taskCompletionSource.setResult(parseMetadata((StorageMetadata) Tasks.await(getReference(map).getMetadata())));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> referenceList(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin$$ExternalSyntheticLambda15
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseStoragePlugin.this.m371x19118f45(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$referenceList$5$io-flutter-plugins-firebase-storage-FlutterFirebaseStoragePlugin, reason: not valid java name */
    public /* synthetic */ void m371x19118f45(Map map, TaskCompletionSource taskCompletionSource) {
        Task<ListResult> list;
        StorageReference reference = getReference(map);
        Object obj = map.get(Constant.METHOD_OPTIONS);
        Objects.requireNonNull(obj);
        Map map2 = (Map) obj;
        Object obj2 = map2.get("maxResults");
        Objects.requireNonNull(obj2);
        int intValue = ((Integer) obj2).intValue();
        if (map2.get("pageToken") != null) {
            Object obj3 = map2.get("pageToken");
            Objects.requireNonNull(obj3);
            list = reference.list(intValue, (String) obj3);
        } else {
            list = reference.list(intValue);
        }
        try {
            taskCompletionSource.setResult(parseListResult((ListResult) Tasks.await(list)));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> referenceListAll(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin$$ExternalSyntheticLambda16
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseStoragePlugin.this.m372xa2de19b(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$referenceListAll$6$io-flutter-plugins-firebase-storage-FlutterFirebaseStoragePlugin, reason: not valid java name */
    public /* synthetic */ void m372xa2de19b(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            taskCompletionSource.setResult(parseListResult((ListResult) Tasks.await(getReference(map).listAll())));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> referenceUpdateMetadata(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin$$ExternalSyntheticLambda17
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseStoragePlugin.this.m373x120bf049(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$referenceUpdateMetadata$7$io-flutter-plugins-firebase-storage-FlutterFirebaseStoragePlugin, reason: not valid java name */
    public /* synthetic */ void m373x120bf049(Map map, TaskCompletionSource taskCompletionSource) {
        StorageReference reference = getReference(map);
        Object obj = map.get(io.flutter.plugins.firebase.auth.Constants.METADATA);
        Objects.requireNonNull(obj);
        try {
            taskCompletionSource.setResult(parseMetadata((StorageMetadata) Tasks.await(reference.updateMetadata(parseMetadata((Map<String, Object>) obj)))));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> taskPutData(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseStoragePlugin.this.m374xc63ab777(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$taskPutData$8$io-flutter-plugins-firebase-storage-FlutterFirebaseStoragePlugin, reason: not valid java name */
    public /* synthetic */ void m374xc63ab777(Map map, TaskCompletionSource taskCompletionSource) {
        StorageReference reference = getReference(map);
        Object obj = map.get("data");
        Objects.requireNonNull(obj);
        Map<String, Object> map2 = (Map) map.get(io.flutter.plugins.firebase.auth.Constants.METADATA);
        Object obj2 = map.get("handle");
        Objects.requireNonNull(obj2);
        try {
            FlutterFirebaseStorageTask.uploadBytes(((Integer) obj2).intValue(), reference, (byte[]) obj, parseMetadata(map2)).startTaskWithMethodChannel(this.channel);
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> taskPutString(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseStoragePlugin.this.m376xff60b10f(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$taskPutString$9$io-flutter-plugins-firebase-storage-FlutterFirebaseStoragePlugin, reason: not valid java name */
    public /* synthetic */ void m376xff60b10f(Map map, TaskCompletionSource taskCompletionSource) {
        StorageReference reference = getReference(map);
        Object obj = map.get("data");
        Objects.requireNonNull(obj);
        Object obj2 = map.get("format");
        Objects.requireNonNull(obj2);
        int intValue = ((Integer) obj2).intValue();
        Map<String, Object> map2 = (Map) map.get(io.flutter.plugins.firebase.auth.Constants.METADATA);
        Object obj3 = map.get("handle");
        Objects.requireNonNull(obj3);
        try {
            FlutterFirebaseStorageTask.uploadBytes(((Integer) obj3).intValue(), reference, stringToByteData((String) obj, intValue), parseMetadata(map2)).startTaskWithMethodChannel(this.channel);
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> taskPutFile(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseStoragePlugin.this.m375x87fd7ec8(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$taskPutFile$10$io-flutter-plugins-firebase-storage-FlutterFirebaseStoragePlugin, reason: not valid java name */
    public /* synthetic */ void m375x87fd7ec8(Map map, TaskCompletionSource taskCompletionSource) {
        StorageReference reference = getReference(map);
        Object obj = map.get("filePath");
        Objects.requireNonNull(obj);
        Map<String, Object> map2 = (Map) map.get(io.flutter.plugins.firebase.auth.Constants.METADATA);
        Object obj2 = map.get("handle");
        Objects.requireNonNull(obj2);
        try {
            FlutterFirebaseStorageTask.uploadFile(((Integer) obj2).intValue(), reference, Uri.fromFile(new File((String) obj)), parseMetadata(map2)).startTaskWithMethodChannel(this.channel);
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> taskWriteToFile(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseStoragePlugin.this.m377xfab92892(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$taskWriteToFile$11$io-flutter-plugins-firebase-storage-FlutterFirebaseStoragePlugin, reason: not valid java name */
    public /* synthetic */ void m377xfab92892(Map map, TaskCompletionSource taskCompletionSource) {
        StorageReference reference = getReference(map);
        Object obj = map.get("filePath");
        Objects.requireNonNull(obj);
        Object obj2 = map.get("handle");
        Objects.requireNonNull(obj2);
        try {
            FlutterFirebaseStorageTask.downloadFile(((Integer) obj2).intValue(), reference, new File((String) obj)).startTaskWithMethodChannel(this.channel);
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> taskPause(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseStoragePlugin.lambda$taskPause$12(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$taskPause$12(Map map, TaskCompletionSource taskCompletionSource) {
        Object obj = map.get("handle");
        Objects.requireNonNull(obj);
        FlutterFirebaseStorageTask inProgressTaskForHandle = FlutterFirebaseStorageTask.getInProgressTaskForHandle(((Integer) obj).intValue());
        if (inProgressTaskForHandle == null) {
            taskCompletionSource.setException(new Exception("Pause operation was called on a task which does not exist."));
            return;
        }
        HashMap hashMap = new HashMap();
        try {
            boolean booleanValue = ((Boolean) Tasks.await(inProgressTaskForHandle.pause())).booleanValue();
            hashMap.put(NotificationCompat.CATEGORY_STATUS, Boolean.valueOf(booleanValue));
            if (booleanValue) {
                hashMap.put(Constants.SNAPSHOT, FlutterFirebaseStorageTask.parseTaskSnapshot(inProgressTaskForHandle.getSnapshot()));
            }
            taskCompletionSource.setResult(hashMap);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> taskResume(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseStoragePlugin.lambda$taskResume$13(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$taskResume$13(Map map, TaskCompletionSource taskCompletionSource) {
        Object obj = map.get("handle");
        Objects.requireNonNull(obj);
        FlutterFirebaseStorageTask inProgressTaskForHandle = FlutterFirebaseStorageTask.getInProgressTaskForHandle(((Integer) obj).intValue());
        if (inProgressTaskForHandle == null) {
            taskCompletionSource.setException(new Exception("Resume operation was called on a task which does not exist."));
            return;
        }
        try {
            boolean booleanValue = ((Boolean) Tasks.await(inProgressTaskForHandle.resume())).booleanValue();
            HashMap hashMap = new HashMap();
            hashMap.put(NotificationCompat.CATEGORY_STATUS, Boolean.valueOf(booleanValue));
            if (booleanValue) {
                hashMap.put(Constants.SNAPSHOT, FlutterFirebaseStorageTask.parseTaskSnapshot(inProgressTaskForHandle.getSnapshot()));
            }
            taskCompletionSource.setResult(hashMap);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> taskCancel(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseStoragePlugin.lambda$taskCancel$14(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$taskCancel$14(Map map, TaskCompletionSource taskCompletionSource) {
        Object obj = map.get("handle");
        Objects.requireNonNull(obj);
        FlutterFirebaseStorageTask inProgressTaskForHandle = FlutterFirebaseStorageTask.getInProgressTaskForHandle(((Integer) obj).intValue());
        if (inProgressTaskForHandle == null) {
            taskCompletionSource.setException(new Exception("Cancel operation was called on a task which does not exist."));
            return;
        }
        try {
            boolean booleanValue = ((Boolean) Tasks.await(inProgressTaskForHandle.cancel())).booleanValue();
            HashMap hashMap = new HashMap();
            hashMap.put(NotificationCompat.CATEGORY_STATUS, Boolean.valueOf(booleanValue));
            if (booleanValue) {
                hashMap.put(Constants.SNAPSHOT, FlutterFirebaseStorageTask.parseTaskSnapshot(inProgressTaskForHandle.getSnapshot()));
            }
            taskCompletionSource.setResult(hashMap);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, final MethodChannel.Result result) {
        Task taskPutString;
        String str = methodCall.method;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1816321956:
                if (str.equals("Task#startPutString")) {
                    c = 0;
                    break;
                }
                break;
            case -1707714184:
                if (str.equals("Task#writeToFile")) {
                    c = 1;
                    break;
                }
                break;
            case -598660204:
                if (str.equals("Storage#useEmulator")) {
                    c = 2;
                    break;
                }
                break;
            case -487339152:
                if (str.equals("Reference#updateMetadata")) {
                    c = 3;
                    break;
                }
                break;
            case 524582600:
                if (str.equals("Reference#getData")) {
                    c = 4;
                    break;
                }
                break;
            case 762112515:
                if (str.equals("Reference#delete")) {
                    c = 5;
                    break;
                }
                break;
            case 782125771:
                if (str.equals("Reference#listAll")) {
                    c = 6;
                    break;
                }
                break;
            case 1007699637:
                if (str.equals("Task#startPutData")) {
                    c = 7;
                    break;
                }
                break;
            case 1007766663:
                if (str.equals("Task#startPutFile")) {
                    c = '\b';
                    break;
                }
                break;
            case 1337346806:
                if (str.equals("Reference#list")) {
                    c = '\t';
                    break;
                }
                break;
            case 1384794957:
                if (str.equals("Reference#getMetadata")) {
                    c = '\n';
                    break;
                }
                break;
            case 1521380120:
                if (str.equals("Task#cancel")) {
                    c = 11;
                    break;
                }
                break;
            case 1536042729:
                if (str.equals("Reference#getDownloadURL")) {
                    c = '\f';
                    break;
                }
                break;
            case 1585110424:
                if (str.equals("Task#pause")) {
                    c = '\r';
                    break;
                }
                break;
            case 1954677963:
                if (str.equals("Task#resume")) {
                    c = 14;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                taskPutString = taskPutString((Map) methodCall.arguments());
                break;
            case 1:
                taskPutString = taskWriteToFile((Map) methodCall.arguments());
                break;
            case 2:
                taskPutString = useEmulator((Map) methodCall.arguments());
                break;
            case 3:
                taskPutString = referenceUpdateMetadata((Map) methodCall.arguments());
                break;
            case 4:
                taskPutString = referenceGetData((Map) methodCall.arguments());
                break;
            case 5:
                taskPutString = referenceDelete((Map) methodCall.arguments());
                break;
            case 6:
                taskPutString = referenceListAll((Map) methodCall.arguments());
                break;
            case 7:
                taskPutString = taskPutData((Map) methodCall.arguments());
                break;
            case '\b':
                taskPutString = taskPutFile((Map) methodCall.arguments());
                break;
            case '\t':
                taskPutString = referenceList((Map) methodCall.arguments());
                break;
            case '\n':
                taskPutString = referenceGetMetadata((Map) methodCall.arguments());
                break;
            case 11:
                taskPutString = taskCancel((Map) methodCall.arguments());
                break;
            case '\f':
                taskPutString = referenceGetDownloadURL((Map) methodCall.arguments());
                break;
            case '\r':
                taskPutString = taskPause((Map) methodCall.arguments());
                break;
            case 14:
                taskPutString = taskResume((Map) methodCall.arguments());
                break;
            default:
                result.notImplemented();
                return;
        }
        taskPutString.addOnCompleteListener(new OnCompleteListener() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                FlutterFirebaseStoragePlugin.lambda$onMethodCall$15(MethodChannel.Result.this, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onMethodCall$15(MethodChannel.Result result, Task task) {
        if (task.isSuccessful()) {
            result.success(task.getResult());
            return;
        }
        Exception exception = task.getException();
        result.error("firebase_storage", exception != null ? exception.getMessage() : null, getExceptionDetails(exception));
    }

    private StorageMetadata parseMetadata(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        StorageMetadata.Builder builder = new StorageMetadata.Builder();
        if (map.get("cacheControl") != null) {
            builder.setCacheControl((String) map.get("cacheControl"));
        }
        if (map.get("contentDisposition") != null) {
            builder.setContentDisposition((String) map.get("contentDisposition"));
        }
        if (map.get("contentEncoding") != null) {
            builder.setContentEncoding((String) map.get("contentEncoding"));
        }
        if (map.get("contentLanguage") != null) {
            builder.setContentLanguage((String) map.get("contentLanguage"));
        }
        if (map.get("contentType") != null) {
            builder.setContentType((String) map.get("contentType"));
        }
        if (map.get("customMetadata") != null) {
            Object obj = map.get("customMetadata");
            Objects.requireNonNull(obj);
            Map map2 = (Map) obj;
            for (String str : map2.keySet()) {
                builder.setCustomMetadata(str, (String) map2.get(str));
            }
        }
        return builder.build();
    }

    private byte[] stringToByteData(String str, int i) {
        if (i == 1) {
            return Base64.decode(str, 0);
        }
        if (i != 2) {
            return null;
        }
        return Base64.decode(str, 8);
    }

    private Long getLongValue(Object obj) {
        if (obj instanceof Long) {
            return (Long) obj;
        }
        if (obj instanceof Integer) {
            return Long.valueOf(((Integer) obj).intValue());
        }
        return 0L;
    }

    @Override // io.flutter.plugins.firebase.core.FlutterFirebasePlugin
    public Task<Map<String, Object>> getPluginConstantsForFirebaseApp(FirebaseApp firebaseApp) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                TaskCompletionSource.this.setResult(new HashMap());
            }
        });
        return taskCompletionSource.getTask();
    }

    @Override // io.flutter.plugins.firebase.core.FlutterFirebasePlugin
    public Task<Void> didReinitializeFirebaseCore() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStoragePlugin$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseStoragePlugin.lambda$didReinitializeFirebaseCore$17(TaskCompletionSource.this);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$didReinitializeFirebaseCore$17(TaskCompletionSource taskCompletionSource) {
        FlutterFirebaseStorageTask.cancelInProgressTasks();
        taskCompletionSource.setResult(null);
    }
}
