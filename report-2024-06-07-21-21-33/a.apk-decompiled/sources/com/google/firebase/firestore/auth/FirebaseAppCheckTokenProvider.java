package com.google.firebase.firestore.auth;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.appcheck.AppCheckTokenResult;
import com.google.firebase.appcheck.interop.AppCheckTokenListener;
import com.google.firebase.appcheck.interop.InternalAppCheckTokenProvider;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Listener;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;

/* loaded from: classes.dex */
public final class FirebaseAppCheckTokenProvider extends CredentialsProvider<String> {
    private static final String LOG_TAG = "FirebaseAppCheckTokenProvider";
    private Listener<String> changeListener;
    private boolean forceRefresh;
    private InternalAppCheckTokenProvider internalAppCheckTokenProvider;
    private final AppCheckTokenListener tokenListener = new AppCheckTokenListener() { // from class: com.google.firebase.firestore.auth.FirebaseAppCheckTokenProvider$$ExternalSyntheticLambda1
        @Override // com.google.firebase.appcheck.interop.AppCheckTokenListener
        public final void onAppCheckTokenChanged(AppCheckTokenResult appCheckTokenResult) {
            FirebaseAppCheckTokenProvider.this.m104xabf6b76c(appCheckTokenResult);
        }
    };

    public FirebaseAppCheckTokenProvider(Deferred<InternalAppCheckTokenProvider> deferred) {
        deferred.whenAvailable(new Deferred.DeferredHandler() { // from class: com.google.firebase.firestore.auth.FirebaseAppCheckTokenProvider$$ExternalSyntheticLambda2
            @Override // com.google.firebase.inject.Deferred.DeferredHandler
            public final void handle(Provider provider) {
                FirebaseAppCheckTokenProvider.this.m105x16263f8b(provider);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$1$com-google-firebase-firestore-auth-FirebaseAppCheckTokenProvider, reason: not valid java name */
    public /* synthetic */ void m105x16263f8b(Provider provider) {
        synchronized (this) {
            InternalAppCheckTokenProvider internalAppCheckTokenProvider = (InternalAppCheckTokenProvider) provider.get();
            this.internalAppCheckTokenProvider = internalAppCheckTokenProvider;
            if (internalAppCheckTokenProvider != null) {
                internalAppCheckTokenProvider.addAppCheckTokenListener(this.tokenListener);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onTokenChanged, reason: merged with bridge method [inline-methods] */
    public synchronized void m104xabf6b76c(AppCheckTokenResult appCheckTokenResult) {
        if (appCheckTokenResult.getError() != null) {
            Logger.warn(LOG_TAG, "Error getting App Check token; using placeholder token instead. Error: " + appCheckTokenResult.getError(), new Object[0]);
        }
        Listener<String> listener = this.changeListener;
        if (listener != null) {
            listener.onValue(appCheckTokenResult.getToken());
        }
    }

    @Override // com.google.firebase.firestore.auth.CredentialsProvider
    public synchronized Task<String> getToken() {
        InternalAppCheckTokenProvider internalAppCheckTokenProvider = this.internalAppCheckTokenProvider;
        if (internalAppCheckTokenProvider == null) {
            return Tasks.forException(new FirebaseApiNotAvailableException("AppCheck is not available"));
        }
        Task<AppCheckTokenResult> token = internalAppCheckTokenProvider.getToken(this.forceRefresh);
        this.forceRefresh = false;
        return token.continueWithTask(Executors.DIRECT_EXECUTOR, new Continuation() { // from class: com.google.firebase.firestore.auth.FirebaseAppCheckTokenProvider$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return FirebaseAppCheckTokenProvider.lambda$getToken$2(task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Task lambda$getToken$2(Task task) throws Exception {
        if (task.isSuccessful()) {
            return Tasks.forResult(((AppCheckTokenResult) task.getResult()).getToken());
        }
        return Tasks.forException(task.getException());
    }

    @Override // com.google.firebase.firestore.auth.CredentialsProvider
    public synchronized void invalidateToken() {
        this.forceRefresh = true;
    }

    @Override // com.google.firebase.firestore.auth.CredentialsProvider
    public synchronized void removeChangeListener() {
        this.changeListener = null;
        InternalAppCheckTokenProvider internalAppCheckTokenProvider = this.internalAppCheckTokenProvider;
        if (internalAppCheckTokenProvider != null) {
            internalAppCheckTokenProvider.removeAppCheckTokenListener(this.tokenListener);
        }
    }

    @Override // com.google.firebase.firestore.auth.CredentialsProvider
    public synchronized void setChangeListener(Listener<String> listener) {
        this.changeListener = listener;
    }
}
