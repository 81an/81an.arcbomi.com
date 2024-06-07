package io.flutter.plugins.firebase.storage;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.firebase.core.FlutterFirebasePlugin;
import io.flutter.plugins.firebase.database.Constants;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class FlutterFirebaseStorageTask {
    static final SparseArray<FlutterFirebaseStorageTask> inProgressTasks = new SparseArray<>();
    private static final Executor taskExecutor = Executors.newSingleThreadExecutor();
    private final byte[] bytes;
    private final Uri fileUri;
    private final int handle;
    private final StorageMetadata metadata;
    private final StorageReference reference;
    private StorageTask<?> storageTask;
    private final FlutterFirebaseStorageTaskType type;
    private final Object pauseSyncObject = new Object();
    private final Object resumeSyncObject = new Object();
    private final Object cancelSyncObject = new Object();
    private Boolean destroyed = false;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public enum FlutterFirebaseStorageTaskType {
        FILE,
        BYTES,
        DOWNLOAD
    }

    private FlutterFirebaseStorageTask(FlutterFirebaseStorageTaskType flutterFirebaseStorageTaskType, int i, StorageReference storageReference, byte[] bArr, Uri uri, StorageMetadata storageMetadata) {
        this.type = flutterFirebaseStorageTaskType;
        this.handle = i;
        this.reference = storageReference;
        this.bytes = bArr;
        this.fileUri = uri;
        this.metadata = storageMetadata;
        SparseArray<FlutterFirebaseStorageTask> sparseArray = inProgressTasks;
        synchronized (sparseArray) {
            sparseArray.put(i, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FlutterFirebaseStorageTask getInProgressTaskForHandle(int i) {
        FlutterFirebaseStorageTask flutterFirebaseStorageTask;
        SparseArray<FlutterFirebaseStorageTask> sparseArray = inProgressTasks;
        synchronized (sparseArray) {
            flutterFirebaseStorageTask = sparseArray.get(i);
        }
        return flutterFirebaseStorageTask;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void cancelInProgressTasks() {
        synchronized (inProgressTasks) {
            int i = 0;
            while (true) {
                SparseArray<FlutterFirebaseStorageTask> sparseArray = inProgressTasks;
                if (i < sparseArray.size()) {
                    FlutterFirebaseStorageTask valueAt = sparseArray.valueAt(i);
                    if (valueAt != null) {
                        valueAt.destroy();
                    }
                    i++;
                } else {
                    sparseArray.clear();
                }
            }
        }
    }

    public static FlutterFirebaseStorageTask uploadBytes(int i, StorageReference storageReference, byte[] bArr, StorageMetadata storageMetadata) {
        return new FlutterFirebaseStorageTask(FlutterFirebaseStorageTaskType.BYTES, i, storageReference, bArr, null, storageMetadata);
    }

    public static FlutterFirebaseStorageTask uploadFile(int i, StorageReference storageReference, Uri uri, StorageMetadata storageMetadata) {
        return new FlutterFirebaseStorageTask(FlutterFirebaseStorageTaskType.FILE, i, storageReference, null, uri, storageMetadata);
    }

    public static FlutterFirebaseStorageTask downloadFile(int i, StorageReference storageReference, File file) {
        return new FlutterFirebaseStorageTask(FlutterFirebaseStorageTaskType.DOWNLOAD, i, storageReference, null, Uri.fromFile(file), null);
    }

    public static Map<String, Object> parseUploadTaskSnapshot(UploadTask.TaskSnapshot taskSnapshot) {
        HashMap hashMap = new HashMap();
        hashMap.put(Constants.PATH, taskSnapshot.getStorage().getPath());
        hashMap.put("bytesTransferred", Long.valueOf(taskSnapshot.getBytesTransferred()));
        hashMap.put("totalBytes", Long.valueOf(taskSnapshot.getTotalByteCount()));
        if (taskSnapshot.getMetadata() != null) {
            hashMap.put(io.flutter.plugins.firebase.auth.Constants.METADATA, FlutterFirebaseStoragePlugin.parseMetadata(taskSnapshot.getMetadata()));
        }
        return hashMap;
    }

    public static Map<String, Object> parseDownloadTaskSnapshot(FileDownloadTask.TaskSnapshot taskSnapshot) {
        HashMap hashMap = new HashMap();
        hashMap.put(Constants.PATH, taskSnapshot.getStorage().getPath());
        if (taskSnapshot.getTask().isSuccessful()) {
            hashMap.put("bytesTransferred", Long.valueOf(taskSnapshot.getTotalByteCount()));
        } else {
            hashMap.put("bytesTransferred", Long.valueOf(taskSnapshot.getBytesTransferred()));
        }
        hashMap.put("totalBytes", Long.valueOf(taskSnapshot.getTotalByteCount()));
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, Object> parseTaskSnapshot(Object obj) {
        if (obj instanceof FileDownloadTask.TaskSnapshot) {
            return parseDownloadTaskSnapshot((FileDownloadTask.TaskSnapshot) obj);
        }
        return parseUploadTaskSnapshot((UploadTask.TaskSnapshot) obj);
    }

    void destroy() {
        this.destroyed = true;
        SparseArray<FlutterFirebaseStorageTask> sparseArray = inProgressTasks;
        synchronized (sparseArray) {
            if (this.storageTask.isInProgress() || this.storageTask.isPaused()) {
                this.storageTask.cancel();
            }
            sparseArray.remove(this.handle);
        }
        synchronized (this.cancelSyncObject) {
            this.cancelSyncObject.notifyAll();
        }
        synchronized (this.pauseSyncObject) {
            this.pauseSyncObject.notifyAll();
        }
        synchronized (this.resumeSyncObject) {
            this.resumeSyncObject.notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Task<Boolean> pause() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        FlutterFirebasePlugin.cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStorageTask$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseStorageTask.this.m380xa87a61ef(taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$pause$0$io-flutter-plugins-firebase-storage-FlutterFirebaseStorageTask, reason: not valid java name */
    public /* synthetic */ void m380xa87a61ef(TaskCompletionSource taskCompletionSource) {
        synchronized (this.pauseSyncObject) {
            if (!this.storageTask.pause()) {
                taskCompletionSource.setResult(false);
                return;
            }
            try {
                this.pauseSyncObject.wait();
                taskCompletionSource.setResult(true);
            } catch (InterruptedException unused) {
                taskCompletionSource.setResult(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Task<Boolean> resume() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        FlutterFirebasePlugin.cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStorageTask$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseStorageTask.this.m381xf5f57b1d(taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$resume$1$io-flutter-plugins-firebase-storage-FlutterFirebaseStorageTask, reason: not valid java name */
    public /* synthetic */ void m381xf5f57b1d(TaskCompletionSource taskCompletionSource) {
        synchronized (this.resumeSyncObject) {
            if (!this.storageTask.resume()) {
                taskCompletionSource.setResult(false);
                return;
            }
            try {
                this.resumeSyncObject.wait();
                taskCompletionSource.setResult(true);
            } catch (InterruptedException unused) {
                taskCompletionSource.setResult(false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Task<Boolean> cancel() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        FlutterFirebasePlugin.cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStorageTask$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseStorageTask.this.m379x9432ca4f(taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$cancel$2$io-flutter-plugins-firebase-storage-FlutterFirebaseStorageTask, reason: not valid java name */
    public /* synthetic */ void m379x9432ca4f(TaskCompletionSource taskCompletionSource) {
        taskCompletionSource.setResult(Boolean.valueOf(this.storageTask.cancel()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startTaskWithMethodChannel(final MethodChannel methodChannel) throws Exception {
        Uri uri;
        Uri uri2;
        byte[] bArr;
        if (this.type == FlutterFirebaseStorageTaskType.BYTES && (bArr = this.bytes) != null) {
            StorageMetadata storageMetadata = this.metadata;
            if (storageMetadata == null) {
                this.storageTask = this.reference.putBytes(bArr);
            } else {
                this.storageTask = this.reference.putBytes(bArr, storageMetadata);
            }
        } else if (this.type == FlutterFirebaseStorageTaskType.FILE && (uri2 = this.fileUri) != null) {
            StorageMetadata storageMetadata2 = this.metadata;
            if (storageMetadata2 == null) {
                this.storageTask = this.reference.putFile(uri2);
            } else {
                this.storageTask = this.reference.putFile(uri2, storageMetadata2);
            }
        } else if (this.type == FlutterFirebaseStorageTaskType.DOWNLOAD && (uri = this.fileUri) != null) {
            this.storageTask = this.reference.getFile(uri);
        } else {
            throw new Exception("Unable to start task. Some arguments have no been initialized.");
        }
        StorageTask<?> storageTask = this.storageTask;
        Executor executor = taskExecutor;
        storageTask.addOnProgressListener(executor, new OnProgressListener() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStorageTask$$ExternalSyntheticLambda7
            @Override // com.google.firebase.storage.OnProgressListener
            public final void onProgress(Object obj) {
                FlutterFirebaseStorageTask.this.m386xff3440f2(methodChannel, (StorageTask.ProvideError) obj);
            }
        });
        this.storageTask.addOnPausedListener(executor, new OnPausedListener() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStorageTask$$ExternalSyntheticLambda6
            @Override // com.google.firebase.storage.OnPausedListener
            public final void onPaused(Object obj) {
                FlutterFirebaseStorageTask.this.m388xfbf648b0(methodChannel, (StorageTask.ProvideError) obj);
            }
        });
        this.storageTask.addOnSuccessListener(executor, new OnSuccessListener() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStorageTask$$ExternalSyntheticLambda5
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                FlutterFirebaseStorageTask.this.m390xf8b8506e(methodChannel, (StorageTask.ProvideError) obj);
            }
        });
        this.storageTask.addOnCanceledListener(executor, new OnCanceledListener() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStorageTask$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnCanceledListener
            public final void onCanceled() {
                FlutterFirebaseStorageTask.this.m382xdfa28003(methodChannel);
            }
        });
        this.storageTask.addOnFailureListener(executor, new OnFailureListener() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStorageTask$$ExternalSyntheticLambda4
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                FlutterFirebaseStorageTask.this.m384xdc6487c1(methodChannel, exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$startTaskWithMethodChannel$4$io-flutter-plugins-firebase-storage-FlutterFirebaseStorageTask, reason: not valid java name */
    public /* synthetic */ void m386xff3440f2(final MethodChannel methodChannel, final StorageTask.ProvideError provideError) {
        if (this.destroyed.booleanValue()) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStorageTask$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseStorageTask.this.m385x80d33d13(methodChannel, provideError);
            }
        });
        synchronized (this.resumeSyncObject) {
            this.resumeSyncObject.notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$startTaskWithMethodChannel$3$io-flutter-plugins-firebase-storage-FlutterFirebaseStorageTask, reason: not valid java name */
    public /* synthetic */ void m385x80d33d13(MethodChannel methodChannel, StorageTask.ProvideError provideError) {
        methodChannel.invokeMethod("Task#onProgress", getTaskEventMap(provideError, null));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$startTaskWithMethodChannel$6$io-flutter-plugins-firebase-storage-FlutterFirebaseStorageTask, reason: not valid java name */
    public /* synthetic */ void m388xfbf648b0(final MethodChannel methodChannel, final StorageTask.ProvideError provideError) {
        if (this.destroyed.booleanValue()) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStorageTask$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseStorageTask.this.m387x7d9544d1(methodChannel, provideError);
            }
        });
        synchronized (this.pauseSyncObject) {
            this.pauseSyncObject.notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$startTaskWithMethodChannel$5$io-flutter-plugins-firebase-storage-FlutterFirebaseStorageTask, reason: not valid java name */
    public /* synthetic */ void m387x7d9544d1(MethodChannel methodChannel, StorageTask.ProvideError provideError) {
        methodChannel.invokeMethod("Task#onPaused", getTaskEventMap(provideError, null));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$startTaskWithMethodChannel$8$io-flutter-plugins-firebase-storage-FlutterFirebaseStorageTask, reason: not valid java name */
    public /* synthetic */ void m390xf8b8506e(final MethodChannel methodChannel, final StorageTask.ProvideError provideError) {
        if (this.destroyed.booleanValue()) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStorageTask$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseStorageTask.this.m389x7a574c8f(methodChannel, provideError);
            }
        });
        destroy();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$startTaskWithMethodChannel$7$io-flutter-plugins-firebase-storage-FlutterFirebaseStorageTask, reason: not valid java name */
    public /* synthetic */ void m389x7a574c8f(MethodChannel methodChannel, StorageTask.ProvideError provideError) {
        methodChannel.invokeMethod("Task#onSuccess", getTaskEventMap(provideError, null));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$startTaskWithMethodChannel$10$io-flutter-plugins-firebase-storage-FlutterFirebaseStorageTask, reason: not valid java name */
    public /* synthetic */ void m382xdfa28003(final MethodChannel methodChannel) {
        if (this.destroyed.booleanValue()) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStorageTask$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseStorageTask.this.m391x7719544d(methodChannel);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$startTaskWithMethodChannel$9$io-flutter-plugins-firebase-storage-FlutterFirebaseStorageTask, reason: not valid java name */
    public /* synthetic */ void m391x7719544d(MethodChannel methodChannel) {
        methodChannel.invokeMethod("Task#onCanceled", getTaskEventMap(null, null));
        destroy();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$startTaskWithMethodChannel$12$io-flutter-plugins-firebase-storage-FlutterFirebaseStorageTask, reason: not valid java name */
    public /* synthetic */ void m384xdc6487c1(final MethodChannel methodChannel, final Exception exc) {
        if (this.destroyed.booleanValue()) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: io.flutter.plugins.firebase.storage.FlutterFirebaseStorageTask$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseStorageTask.this.m383x5e0383e2(methodChannel, exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$startTaskWithMethodChannel$11$io-flutter-plugins-firebase-storage-FlutterFirebaseStorageTask, reason: not valid java name */
    public /* synthetic */ void m383x5e0383e2(MethodChannel methodChannel, Exception exc) {
        methodChannel.invokeMethod("Task#onFailure", getTaskEventMap(null, exc));
        destroy();
    }

    private Map<String, Object> getTaskEventMap(Object obj, Exception exc) {
        HashMap hashMap = new HashMap();
        hashMap.put("handle", Integer.valueOf(this.handle));
        hashMap.put("appName", this.reference.getStorage().getApp().getName());
        hashMap.put("bucket", this.reference.getBucket());
        if (obj != null) {
            hashMap.put(Constants.SNAPSHOT, parseTaskSnapshot(obj));
        }
        if (exc != null) {
            hashMap.put("error", FlutterFirebaseStoragePlugin.getExceptionDetails(exc));
        }
        return hashMap;
    }

    public Object getSnapshot() {
        return this.storageTask.getSnapshot();
    }
}
