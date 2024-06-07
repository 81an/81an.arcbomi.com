package io.flutter.view;

import android.graphics.SurfaceTexture;

/* loaded from: classes2.dex */
public interface TextureRegistry {

    /* renamed from: io.flutter.view.TextureRegistry$-CC, reason: invalid class name */
    /* loaded from: classes2.dex */
    public final /* synthetic */ class CC {
        public static void $default$onTrimMemory(TextureRegistry _this, int i) {
        }
    }

    /* loaded from: classes2.dex */
    public interface OnFrameConsumedListener {
        void onFrameConsumed();
    }

    /* loaded from: classes2.dex */
    public interface OnTrimMemoryListener {
        void onTrimMemory(int i);
    }

    /* loaded from: classes2.dex */
    public interface SurfaceTextureEntry {

        /* renamed from: io.flutter.view.TextureRegistry$SurfaceTextureEntry$-CC, reason: invalid class name */
        /* loaded from: classes2.dex */
        public final /* synthetic */ class CC {
            public static void $default$setOnFrameConsumedListener(SurfaceTextureEntry _this, OnFrameConsumedListener onFrameConsumedListener) {
            }

            public static void $default$setOnTrimMemoryListener(SurfaceTextureEntry _this, OnTrimMemoryListener onTrimMemoryListener) {
            }
        }

        long id();

        void release();

        void setOnFrameConsumedListener(OnFrameConsumedListener onFrameConsumedListener);

        void setOnTrimMemoryListener(OnTrimMemoryListener onTrimMemoryListener);

        SurfaceTexture surfaceTexture();
    }

    SurfaceTextureEntry createSurfaceTexture();

    void onTrimMemory(int i);

    SurfaceTextureEntry registerSurfaceTexture(SurfaceTexture surfaceTexture);
}
