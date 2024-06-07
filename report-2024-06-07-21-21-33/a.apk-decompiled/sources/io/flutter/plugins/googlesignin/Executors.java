package io.flutter.plugins.googlesignin;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* loaded from: classes2.dex */
public final class Executors {

    /* loaded from: classes2.dex */
    private static final class UiThreadExecutor implements Executor {
        private static final Handler UI_THREAD = new Handler(Looper.getMainLooper());

        private UiThreadExecutor() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            UI_THREAD.post(runnable);
        }
    }

    public static Executor uiThreadExecutor() {
        return new UiThreadExecutor();
    }

    private Executors() {
    }
}
