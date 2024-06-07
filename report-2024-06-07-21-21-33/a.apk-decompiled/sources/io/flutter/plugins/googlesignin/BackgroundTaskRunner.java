package io.flutter.plugins.googlesignin;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public final class BackgroundTaskRunner {
    private final ThreadPoolExecutor executor;

    /* loaded from: classes2.dex */
    public interface Callback<T> {
        void run(Future<T> future);
    }

    public BackgroundTaskRunner(int i) {
        this.executor = new ThreadPoolExecutor(i, i, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue());
    }

    public <T> void runInBackground(Callable<T> callable, final Callback<T> callback) {
        final ListenableFuture<T> runInBackground = runInBackground(callable);
        runInBackground.addListener(new Runnable() { // from class: io.flutter.plugins.googlesignin.BackgroundTaskRunner.1
            @Override // java.lang.Runnable
            public void run() {
                callback.run(runInBackground);
            }
        }, Executors.uiThreadExecutor());
    }

    public <T> ListenableFuture<T> runInBackground(final Callable<T> callable) {
        final SettableFuture create = SettableFuture.create();
        this.executor.execute(new Runnable() { // from class: io.flutter.plugins.googlesignin.BackgroundTaskRunner.2
            @Override // java.lang.Runnable
            public void run() {
                if (create.isCancelled()) {
                    return;
                }
                try {
                    create.set(callable.call());
                } catch (Throwable th) {
                    create.setException(th);
                }
            }
        });
        return create;
    }
}
