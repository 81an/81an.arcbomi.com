package com.google.firebase.storage;

import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.internal.ExponentialBackoffSender;
import com.google.firebase.storage.network.GetMetadataNetworkRequest;
import org.json.JSONException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class GetMetadataTask implements Runnable {
    private static final String TAG = "GetMetadataTask";
    private TaskCompletionSource<StorageMetadata> mPendingResult;
    private StorageMetadata mResultMetadata;
    private ExponentialBackoffSender mSender;
    private StorageReference mStorageRef;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GetMetadataTask(StorageReference storageReference, TaskCompletionSource<StorageMetadata> taskCompletionSource) {
        Preconditions.checkNotNull(storageReference);
        Preconditions.checkNotNull(taskCompletionSource);
        this.mStorageRef = storageReference;
        this.mPendingResult = taskCompletionSource;
        if (storageReference.getRoot().getName().equals(storageReference.getName())) {
            throw new IllegalArgumentException("getMetadata() is not supported at the root of the bucket.");
        }
        FirebaseStorage storage = this.mStorageRef.getStorage();
        this.mSender = new ExponentialBackoffSender(storage.getApp().getApplicationContext(), storage.getAuthProvider(), storage.getAppCheckProvider(), storage.getMaxDownloadRetryTimeMillis());
    }

    @Override // java.lang.Runnable
    public void run() {
        GetMetadataNetworkRequest getMetadataNetworkRequest = new GetMetadataNetworkRequest(this.mStorageRef.getStorageReferenceUri(), this.mStorageRef.getApp());
        this.mSender.sendWithExponentialBackoff(getMetadataNetworkRequest);
        if (getMetadataNetworkRequest.isResultSuccess()) {
            try {
                this.mResultMetadata = new StorageMetadata.Builder(getMetadataNetworkRequest.getResultBody(), this.mStorageRef).build();
            } catch (JSONException e) {
                Log.e(TAG, "Unable to parse resulting metadata. " + getMetadataNetworkRequest.getRawResult(), e);
                this.mPendingResult.setException(StorageException.fromException(e));
                return;
            }
        }
        TaskCompletionSource<StorageMetadata> taskCompletionSource = this.mPendingResult;
        if (taskCompletionSource != null) {
            getMetadataNetworkRequest.completeTask(taskCompletionSource, this.mResultMetadata);
        }
    }
}
