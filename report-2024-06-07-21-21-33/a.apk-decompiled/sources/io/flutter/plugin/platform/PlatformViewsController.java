package io.flutter.plugin.platform;

import android.content.Context;
import android.content.MutableContextWrapper;
import android.os.Build;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import io.flutter.Log;
import io.flutter.embedding.android.AndroidTouchProcessor;
import io.flutter.embedding.android.FlutterView;
import io.flutter.embedding.android.MotionEventTracker;
import io.flutter.embedding.engine.FlutterOverlaySurface;
import io.flutter.embedding.engine.dart.DartExecutor;
import io.flutter.embedding.engine.mutatorsstack.FlutterMutatorView;
import io.flutter.embedding.engine.mutatorsstack.FlutterMutatorsStack;
import io.flutter.embedding.engine.renderer.FlutterRenderer;
import io.flutter.embedding.engine.systemchannels.PlatformViewsChannel;
import io.flutter.plugin.editing.TextInputPlugin;
import io.flutter.plugin.platform.PlatformViewsController;
import io.flutter.util.ViewUtils;
import io.flutter.view.AccessibilityBridge;
import io.flutter.view.TextureRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class PlatformViewsController implements PlatformViewsAccessibilityDelegate {
    private static final String TAG = "PlatformViewsController";
    private static Class[] VIEW_TYPES_REQUIRE_VIRTUAL_DISPLAY = {SurfaceView.class};
    private AndroidTouchProcessor androidTouchProcessor;
    private Context context;
    private FlutterView flutterView;
    private PlatformViewsChannel platformViewsChannel;
    private TextInputPlugin textInputPlugin;
    private TextureRegistry textureRegistry;
    private int nextOverlayLayerId = 0;
    private boolean flutterViewConvertedToImageView = false;
    private boolean synchronizeToNativeViewHierarchy = true;
    private boolean usesSoftwareRendering = false;
    private final PlatformViewsChannel.PlatformViewsHandler channelHandler = new AnonymousClass1();
    private final PlatformViewRegistryImpl registry = new PlatformViewRegistryImpl();
    final HashMap<Integer, VirtualDisplayController> vdControllers = new HashMap<>();
    private final AccessibilityEventsDelegate accessibilityEventsDelegate = new AccessibilityEventsDelegate();
    final HashMap<Context, View> contextToEmbeddedView = new HashMap<>();
    private final SparseArray<PlatformOverlayView> overlayLayerViews = new SparseArray<>();
    private final HashSet<Integer> currentFrameUsedOverlayLayerIds = new HashSet<>();
    private final HashSet<Integer> currentFrameUsedPlatformViewIds = new HashSet<>();
    private final SparseArray<PlatformViewWrapper> viewWrappers = new SparseArray<>();
    private final SparseArray<PlatformView> platformViews = new SparseArray<>();
    private final SparseArray<FlutterMutatorView> platformViewParent = new SparseArray<>();
    private final MotionEventTracker motionEventTracker = MotionEventTracker.getInstance();

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean validateDirection(int i) {
        return i == 0 || i == 1;
    }

    public void onAttachedToJNI() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: io.flutter.plugin.platform.PlatformViewsController$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass1 implements PlatformViewsChannel.PlatformViewsHandler {
        AnonymousClass1() {
        }

        @Override // io.flutter.embedding.engine.systemchannels.PlatformViewsChannel.PlatformViewsHandler
        public void createForPlatformViewLayer(PlatformViewsChannel.PlatformViewCreationRequest platformViewCreationRequest) {
            ensureValidAndroidVersion(19);
            ensureValidRequest(platformViewCreationRequest);
            configureForHybridComposition(createPlatformView(platformViewCreationRequest, false), platformViewCreationRequest);
        }

        @Override // io.flutter.embedding.engine.systemchannels.PlatformViewsChannel.PlatformViewsHandler
        public long createForTextureLayer(PlatformViewsChannel.PlatformViewCreationRequest platformViewCreationRequest) {
            ensureValidRequest(platformViewCreationRequest);
            int i = platformViewCreationRequest.viewId;
            if (PlatformViewsController.this.viewWrappers.get(i) == null) {
                if (PlatformViewsController.this.textureRegistry != null) {
                    if (PlatformViewsController.this.flutterView == null) {
                        throw new IllegalStateException("Flutter view is null. This means the platform views controller doesn't have an attached view, view id: " + i);
                    }
                    PlatformView createPlatformView = createPlatformView(platformViewCreationRequest, true);
                    View view = createPlatformView.getView();
                    if (view.getParent() != null) {
                        throw new IllegalStateException("The Android view returned from PlatformView#getView() was already added to a parent view.");
                    }
                    if (!(Build.VERSION.SDK_INT >= 23 && !ViewUtils.hasChildViewOfType(view, PlatformViewsController.VIEW_TYPES_REQUIRE_VIRTUAL_DISPLAY))) {
                        if (platformViewCreationRequest.displayMode != PlatformViewsChannel.PlatformViewCreationRequest.RequestedDisplayMode.TEXTURE_WITH_HYBRID_FALLBACK) {
                            if (!PlatformViewsController.this.usesSoftwareRendering) {
                                return configureForVirtualDisplay(createPlatformView, platformViewCreationRequest);
                            }
                        } else {
                            configureForHybridComposition(createPlatformView, platformViewCreationRequest);
                            return -2L;
                        }
                    }
                    return configureForTextureLayerComposition(createPlatformView, platformViewCreationRequest);
                }
                throw new IllegalStateException("Texture registry is null. This means that platform views controller was detached, view id: " + i);
            }
            throw new IllegalStateException("Trying to create an already created platform view, view id: " + i);
        }

        @Override // io.flutter.embedding.engine.systemchannels.PlatformViewsChannel.PlatformViewsHandler
        public void dispose(int i) {
            PlatformView platformView = (PlatformView) PlatformViewsController.this.platformViews.get(i);
            if (platformView != null) {
                PlatformViewsController.this.platformViews.remove(i);
                try {
                    platformView.dispose();
                } catch (RuntimeException e) {
                    Log.e(PlatformViewsController.TAG, "Disposing platform view threw an exception", e);
                }
                if (!PlatformViewsController.this.usesVirtualDisplay(i)) {
                    PlatformViewWrapper platformViewWrapper = (PlatformViewWrapper) PlatformViewsController.this.viewWrappers.get(i);
                    if (platformViewWrapper == null) {
                        FlutterMutatorView flutterMutatorView = (FlutterMutatorView) PlatformViewsController.this.platformViewParent.get(i);
                        if (flutterMutatorView != null) {
                            flutterMutatorView.removeAllViews();
                            flutterMutatorView.unsetOnDescendantFocusChangeListener();
                            ViewGroup viewGroup = (ViewGroup) flutterMutatorView.getParent();
                            if (viewGroup != null) {
                                viewGroup.removeView(flutterMutatorView);
                            }
                            PlatformViewsController.this.platformViewParent.remove(i);
                            return;
                        }
                        return;
                    }
                    platformViewWrapper.removeAllViews();
                    platformViewWrapper.release();
                    platformViewWrapper.unsetOnDescendantFocusChangeListener();
                    ViewGroup viewGroup2 = (ViewGroup) platformViewWrapper.getParent();
                    if (viewGroup2 != null) {
                        viewGroup2.removeView(platformViewWrapper);
                    }
                    PlatformViewsController.this.viewWrappers.remove(i);
                    return;
                }
                View view = PlatformViewsController.this.vdControllers.get(Integer.valueOf(i)).getView();
                if (view != null) {
                    PlatformViewsController.this.contextToEmbeddedView.remove(view.getContext());
                }
                PlatformViewsController.this.vdControllers.remove(Integer.valueOf(i));
                return;
            }
            Log.e(PlatformViewsController.TAG, "Disposing unknown platform view with id: " + i);
        }

        @Override // io.flutter.embedding.engine.systemchannels.PlatformViewsChannel.PlatformViewsHandler
        public void offset(int i, double d, double d2) {
            if (PlatformViewsController.this.usesVirtualDisplay(i)) {
                return;
            }
            PlatformViewWrapper platformViewWrapper = (PlatformViewWrapper) PlatformViewsController.this.viewWrappers.get(i);
            if (platformViewWrapper != null) {
                int physicalPixels = PlatformViewsController.this.toPhysicalPixels(d);
                int physicalPixels2 = PlatformViewsController.this.toPhysicalPixels(d2);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) platformViewWrapper.getLayoutParams();
                layoutParams.topMargin = physicalPixels;
                layoutParams.leftMargin = physicalPixels2;
                platformViewWrapper.setLayoutParams(layoutParams);
                return;
            }
            Log.e(PlatformViewsController.TAG, "Setting offset for unknown platform view with id: " + i);
        }

        @Override // io.flutter.embedding.engine.systemchannels.PlatformViewsChannel.PlatformViewsHandler
        public void resize(PlatformViewsChannel.PlatformViewResizeRequest platformViewResizeRequest, final PlatformViewsChannel.PlatformViewBufferResized platformViewBufferResized) {
            int physicalPixels = PlatformViewsController.this.toPhysicalPixels(platformViewResizeRequest.newLogicalWidth);
            int physicalPixels2 = PlatformViewsController.this.toPhysicalPixels(platformViewResizeRequest.newLogicalHeight);
            int i = platformViewResizeRequest.viewId;
            if (PlatformViewsController.this.usesVirtualDisplay(i)) {
                final float displayDensity = PlatformViewsController.this.getDisplayDensity();
                final VirtualDisplayController virtualDisplayController = PlatformViewsController.this.vdControllers.get(Integer.valueOf(i));
                PlatformViewsController.this.lockInputConnection(virtualDisplayController);
                virtualDisplayController.resize(physicalPixels, physicalPixels2, new Runnable() { // from class: io.flutter.plugin.platform.PlatformViewsController$1$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        PlatformViewsController.AnonymousClass1.this.m280x7b26d3(virtualDisplayController, displayDensity, platformViewBufferResized);
                    }
                });
                return;
            }
            PlatformView platformView = (PlatformView) PlatformViewsController.this.platformViews.get(i);
            PlatformViewWrapper platformViewWrapper = (PlatformViewWrapper) PlatformViewsController.this.viewWrappers.get(i);
            if (platformView == null || platformViewWrapper == null) {
                Log.e(PlatformViewsController.TAG, "Resizing unknown platform view with id: " + i);
                return;
            }
            if (physicalPixels > platformViewWrapper.getBufferWidth() || physicalPixels2 > platformViewWrapper.getBufferHeight()) {
                platformViewWrapper.setBufferSize(physicalPixels, physicalPixels2);
            }
            ViewGroup.LayoutParams layoutParams = platformViewWrapper.getLayoutParams();
            layoutParams.width = physicalPixels;
            layoutParams.height = physicalPixels2;
            platformViewWrapper.setLayoutParams(layoutParams);
            View view = platformView.getView();
            if (view != null) {
                ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
                layoutParams2.width = physicalPixels;
                layoutParams2.height = physicalPixels2;
                view.setLayoutParams(layoutParams2);
            }
            platformViewBufferResized.run(new PlatformViewsChannel.PlatformViewBufferSize(PlatformViewsController.this.toLogicalPixels(platformViewWrapper.getBufferWidth()), PlatformViewsController.this.toLogicalPixels(platformViewWrapper.getBufferHeight())));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$resize$0$io-flutter-plugin-platform-PlatformViewsController$1, reason: not valid java name */
        public /* synthetic */ void m280x7b26d3(VirtualDisplayController virtualDisplayController, float f, PlatformViewsChannel.PlatformViewBufferResized platformViewBufferResized) {
            PlatformViewsController.this.unlockInputConnection(virtualDisplayController);
            if (PlatformViewsController.this.context != null) {
                f = PlatformViewsController.this.getDisplayDensity();
            }
            platformViewBufferResized.run(new PlatformViewsChannel.PlatformViewBufferSize(PlatformViewsController.this.toLogicalPixels(virtualDisplayController.getBufferWidth(), f), PlatformViewsController.this.toLogicalPixels(virtualDisplayController.getBufferHeight(), f)));
        }

        @Override // io.flutter.embedding.engine.systemchannels.PlatformViewsChannel.PlatformViewsHandler
        public void onTouch(PlatformViewsChannel.PlatformViewTouch platformViewTouch) {
            int i = platformViewTouch.viewId;
            float f = PlatformViewsController.this.context.getResources().getDisplayMetrics().density;
            if (!PlatformViewsController.this.usesVirtualDisplay(i)) {
                PlatformView platformView = (PlatformView) PlatformViewsController.this.platformViews.get(i);
                if (platformView == null) {
                    Log.e(PlatformViewsController.TAG, "Sending touch to an unknown view with id: " + i);
                    return;
                }
                View view = platformView.getView();
                if (view == null) {
                    Log.e(PlatformViewsController.TAG, "Sending touch to a null view with id: " + i);
                    return;
                }
                view.dispatchTouchEvent(PlatformViewsController.this.toMotionEvent(f, platformViewTouch, false));
                return;
            }
            PlatformViewsController.this.vdControllers.get(Integer.valueOf(i)).dispatchTouchEvent(PlatformViewsController.this.toMotionEvent(f, platformViewTouch, true));
        }

        @Override // io.flutter.embedding.engine.systemchannels.PlatformViewsChannel.PlatformViewsHandler
        public void setDirection(int i, int i2) {
            View view;
            if (!PlatformViewsController.validateDirection(i2)) {
                throw new IllegalStateException("Trying to set unknown direction value: " + i2 + "(view id: " + i + ")");
            }
            if (!PlatformViewsController.this.usesVirtualDisplay(i)) {
                PlatformView platformView = (PlatformView) PlatformViewsController.this.platformViews.get(i);
                if (platformView == null) {
                    Log.e(PlatformViewsController.TAG, "Setting direction to an unknown view with id: " + i);
                    return;
                }
                view = platformView.getView();
            } else {
                view = PlatformViewsController.this.vdControllers.get(Integer.valueOf(i)).getView();
            }
            if (view == null) {
                Log.e(PlatformViewsController.TAG, "Setting direction to a null view with id: " + i);
                return;
            }
            view.setLayoutDirection(i2);
        }

        @Override // io.flutter.embedding.engine.systemchannels.PlatformViewsChannel.PlatformViewsHandler
        public void clearFocus(int i) {
            View view;
            if (!PlatformViewsController.this.usesVirtualDisplay(i)) {
                PlatformView platformView = (PlatformView) PlatformViewsController.this.platformViews.get(i);
                if (platformView == null) {
                    Log.e(PlatformViewsController.TAG, "Clearing focus on an unknown view with id: " + i);
                    return;
                }
                view = platformView.getView();
            } else {
                view = PlatformViewsController.this.vdControllers.get(Integer.valueOf(i)).getView();
            }
            if (view == null) {
                Log.e(PlatformViewsController.TAG, "Clearing focus on a null view with id: " + i);
                return;
            }
            view.clearFocus();
        }

        private void ensureValidAndroidVersion(int i) {
            if (Build.VERSION.SDK_INT >= i) {
                return;
            }
            throw new IllegalStateException("Trying to use platform views with API " + Build.VERSION.SDK_INT + ", required API level is: " + i);
        }

        private void ensureValidRequest(PlatformViewsChannel.PlatformViewCreationRequest platformViewCreationRequest) {
            if (PlatformViewsController.validateDirection(platformViewCreationRequest.direction)) {
                return;
            }
            throw new IllegalStateException("Trying to create a view with unknown direction value: " + platformViewCreationRequest.direction + "(view id: " + platformViewCreationRequest.viewId + ")");
        }

        private PlatformView createPlatformView(PlatformViewsChannel.PlatformViewCreationRequest platformViewCreationRequest, boolean z) {
            Context context;
            PlatformViewFactory factory = PlatformViewsController.this.registry.getFactory(platformViewCreationRequest.viewType);
            if (factory == null) {
                throw new IllegalStateException("Trying to create a platform view of unregistered type: " + platformViewCreationRequest.viewType);
            }
            Object decodeMessage = platformViewCreationRequest.params != null ? factory.getCreateArgsCodec().decodeMessage(platformViewCreationRequest.params) : null;
            if (!z) {
                context = PlatformViewsController.this.context;
            } else {
                context = new MutableContextWrapper(PlatformViewsController.this.context);
            }
            PlatformView create = factory.create(context, platformViewCreationRequest.viewId, decodeMessage);
            View view = create.getView();
            if (view == null) {
                throw new IllegalStateException("PlatformView#getView() returned null, but an Android view reference was expected.");
            }
            view.setLayoutDirection(platformViewCreationRequest.direction);
            PlatformViewsController.this.platformViews.put(platformViewCreationRequest.viewId, create);
            return create;
        }

        private void configureForHybridComposition(PlatformView platformView, PlatformViewsChannel.PlatformViewCreationRequest platformViewCreationRequest) {
            Log.i(PlatformViewsController.TAG, "Using hybrid composition for platform view: " + platformViewCreationRequest.viewId);
        }

        private long configureForVirtualDisplay(PlatformView platformView, final PlatformViewsChannel.PlatformViewCreationRequest platformViewCreationRequest) {
            ensureValidAndroidVersion(20);
            Log.i(PlatformViewsController.TAG, "Hosting view in a virtual display for platform view: " + platformViewCreationRequest.viewId);
            TextureRegistry.SurfaceTextureEntry createSurfaceTexture = PlatformViewsController.this.textureRegistry.createSurfaceTexture();
            VirtualDisplayController create = VirtualDisplayController.create(PlatformViewsController.this.context, PlatformViewsController.this.accessibilityEventsDelegate, platformView, createSurfaceTexture, PlatformViewsController.this.toPhysicalPixels(platformViewCreationRequest.logicalWidth), PlatformViewsController.this.toPhysicalPixels(platformViewCreationRequest.logicalHeight), platformViewCreationRequest.viewId, null, new View.OnFocusChangeListener() { // from class: io.flutter.plugin.platform.PlatformViewsController$1$$ExternalSyntheticLambda1
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view, boolean z) {
                    PlatformViewsController.AnonymousClass1.this.m279xbd462dcc(platformViewCreationRequest, view, z);
                }
            });
            if (create != null) {
                if (PlatformViewsController.this.flutterView != null) {
                    create.onFlutterViewAttached(PlatformViewsController.this.flutterView);
                }
                PlatformViewsController.this.vdControllers.put(Integer.valueOf(platformViewCreationRequest.viewId), create);
                View view = platformView.getView();
                PlatformViewsController.this.contextToEmbeddedView.put(view.getContext(), view);
                return createSurfaceTexture.id();
            }
            throw new IllegalStateException("Failed creating virtual display for a " + platformViewCreationRequest.viewType + " with id: " + platformViewCreationRequest.viewId);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$configureForVirtualDisplay$1$io-flutter-plugin-platform-PlatformViewsController$1, reason: not valid java name */
        public /* synthetic */ void m279xbd462dcc(PlatformViewsChannel.PlatformViewCreationRequest platformViewCreationRequest, View view, boolean z) {
            if (z) {
                PlatformViewsController.this.platformViewsChannel.invokeViewFocused(platformViewCreationRequest.viewId);
            }
        }

        private long configureForTextureLayerComposition(PlatformView platformView, final PlatformViewsChannel.PlatformViewCreationRequest platformViewCreationRequest) {
            PlatformViewWrapper platformViewWrapper;
            long j;
            ensureValidAndroidVersion(23);
            Log.i(PlatformViewsController.TAG, "Hosting view in view hierarchy for platform view: " + platformViewCreationRequest.viewId);
            int physicalPixels = PlatformViewsController.this.toPhysicalPixels(platformViewCreationRequest.logicalWidth);
            int physicalPixels2 = PlatformViewsController.this.toPhysicalPixels(platformViewCreationRequest.logicalHeight);
            if (!PlatformViewsController.this.usesSoftwareRendering) {
                TextureRegistry.SurfaceTextureEntry createSurfaceTexture = PlatformViewsController.this.textureRegistry.createSurfaceTexture();
                PlatformViewWrapper platformViewWrapper2 = new PlatformViewWrapper(PlatformViewsController.this.context, createSurfaceTexture);
                long id = createSurfaceTexture.id();
                platformViewWrapper = platformViewWrapper2;
                j = id;
            } else {
                platformViewWrapper = new PlatformViewWrapper(PlatformViewsController.this.context);
                j = -1;
            }
            platformViewWrapper.setTouchProcessor(PlatformViewsController.this.androidTouchProcessor);
            platformViewWrapper.setBufferSize(physicalPixels, physicalPixels2);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(physicalPixels, physicalPixels2);
            int physicalPixels3 = PlatformViewsController.this.toPhysicalPixels(platformViewCreationRequest.logicalTop);
            int physicalPixels4 = PlatformViewsController.this.toPhysicalPixels(platformViewCreationRequest.logicalLeft);
            layoutParams.topMargin = physicalPixels3;
            layoutParams.leftMargin = physicalPixels4;
            platformViewWrapper.setLayoutParams(layoutParams);
            View view = platformView.getView();
            view.setLayoutParams(new FrameLayout.LayoutParams(physicalPixels, physicalPixels2));
            view.setImportantForAccessibility(4);
            platformViewWrapper.addView(view);
            platformViewWrapper.setOnDescendantFocusChangeListener(new View.OnFocusChangeListener() { // from class: io.flutter.plugin.platform.PlatformViewsController$1$$ExternalSyntheticLambda0
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view2, boolean z) {
                    PlatformViewsController.AnonymousClass1.this.m278xfdfb23ee(platformViewCreationRequest, view2, z);
                }
            });
            PlatformViewsController.this.flutterView.addView(platformViewWrapper);
            PlatformViewsController.this.viewWrappers.append(platformViewCreationRequest.viewId, platformViewWrapper);
            return j;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$configureForTextureLayerComposition$2$io-flutter-plugin-platform-PlatformViewsController$1, reason: not valid java name */
        public /* synthetic */ void m278xfdfb23ee(PlatformViewsChannel.PlatformViewCreationRequest platformViewCreationRequest, View view, boolean z) {
            if (z) {
                PlatformViewsController.this.platformViewsChannel.invokeViewFocused(platformViewCreationRequest.viewId);
            } else if (PlatformViewsController.this.textInputPlugin != null) {
                PlatformViewsController.this.textInputPlugin.clearPlatformViewClient(platformViewCreationRequest.viewId);
            }
        }

        @Override // io.flutter.embedding.engine.systemchannels.PlatformViewsChannel.PlatformViewsHandler
        public void synchronizeToNativeViewHierarchy(boolean z) {
            PlatformViewsController.this.synchronizeToNativeViewHierarchy = z;
        }
    }

    public MotionEvent toMotionEvent(float f, PlatformViewsChannel.PlatformViewTouch platformViewTouch, boolean z) {
        MotionEvent pop = this.motionEventTracker.pop(MotionEventTracker.MotionEventId.from(platformViewTouch.motionEventId));
        MotionEvent.PointerProperties[] pointerPropertiesArr = (MotionEvent.PointerProperties[]) parsePointerPropertiesList(platformViewTouch.rawPointerPropertiesList).toArray(new MotionEvent.PointerProperties[platformViewTouch.pointerCount]);
        MotionEvent.PointerCoords[] pointerCoordsArr = (MotionEvent.PointerCoords[]) parsePointerCoordsList(platformViewTouch.rawPointerCoords, f).toArray(new MotionEvent.PointerCoords[platformViewTouch.pointerCount]);
        if (!z && pop != null) {
            return MotionEvent.obtain(pop.getDownTime(), pop.getEventTime(), platformViewTouch.action, platformViewTouch.pointerCount, pointerPropertiesArr, pointerCoordsArr, pop.getMetaState(), pop.getButtonState(), pop.getXPrecision(), pop.getYPrecision(), pop.getDeviceId(), pop.getEdgeFlags(), pop.getSource(), pop.getFlags());
        }
        return MotionEvent.obtain(platformViewTouch.downTime.longValue(), platformViewTouch.eventTime.longValue(), platformViewTouch.action, platformViewTouch.pointerCount, pointerPropertiesArr, pointerCoordsArr, platformViewTouch.metaState, platformViewTouch.buttonState, platformViewTouch.xPrecision, platformViewTouch.yPrecision, platformViewTouch.deviceId, platformViewTouch.edgeFlags, platformViewTouch.source, platformViewTouch.flags);
    }

    public void attach(Context context, TextureRegistry textureRegistry, DartExecutor dartExecutor) {
        if (this.context != null) {
            throw new AssertionError("A PlatformViewsController can only be attached to a single output target.\nattach was called while the PlatformViewsController was already attached.");
        }
        this.context = context;
        this.textureRegistry = textureRegistry;
        PlatformViewsChannel platformViewsChannel = new PlatformViewsChannel(dartExecutor);
        this.platformViewsChannel = platformViewsChannel;
        platformViewsChannel.setPlatformViewsHandler(this.channelHandler);
    }

    public void setSoftwareRendering(boolean z) {
        this.usesSoftwareRendering = z;
    }

    public void detach() {
        PlatformViewsChannel platformViewsChannel = this.platformViewsChannel;
        if (platformViewsChannel != null) {
            platformViewsChannel.setPlatformViewsHandler(null);
        }
        destroyOverlaySurfaces();
        this.platformViewsChannel = null;
        this.context = null;
        this.textureRegistry = null;
    }

    public void attachToView(FlutterView flutterView) {
        this.flutterView = flutterView;
        for (int i = 0; i < this.viewWrappers.size(); i++) {
            this.flutterView.addView(this.viewWrappers.valueAt(i));
        }
        for (int i2 = 0; i2 < this.platformViewParent.size(); i2++) {
            this.flutterView.addView(this.platformViewParent.valueAt(i2));
        }
        for (int i3 = 0; i3 < this.platformViews.size(); i3++) {
            this.platformViews.valueAt(i3).onFlutterViewAttached(this.flutterView);
        }
    }

    public void detachFromView() {
        for (int i = 0; i < this.viewWrappers.size(); i++) {
            this.flutterView.removeView(this.viewWrappers.valueAt(i));
        }
        for (int i2 = 0; i2 < this.platformViewParent.size(); i2++) {
            this.flutterView.removeView(this.platformViewParent.valueAt(i2));
        }
        destroyOverlaySurfaces();
        removeOverlaySurfaces();
        this.flutterView = null;
        this.flutterViewConvertedToImageView = false;
        for (int i3 = 0; i3 < this.platformViews.size(); i3++) {
            this.platformViews.valueAt(i3).onFlutterViewDetached();
        }
    }

    @Override // io.flutter.plugin.platform.PlatformViewsAccessibilityDelegate
    public void attachAccessibilityBridge(AccessibilityBridge accessibilityBridge) {
        this.accessibilityEventsDelegate.setAccessibilityBridge(accessibilityBridge);
    }

    @Override // io.flutter.plugin.platform.PlatformViewsAccessibilityDelegate
    public void detachAccessibilityBridge() {
        this.accessibilityEventsDelegate.setAccessibilityBridge(null);
    }

    public void attachTextInputPlugin(TextInputPlugin textInputPlugin) {
        this.textInputPlugin = textInputPlugin;
    }

    public void detachTextInputPlugin() {
        this.textInputPlugin = null;
    }

    public boolean checkInputConnectionProxy(View view) {
        if (view == null || !this.contextToEmbeddedView.containsKey(view.getContext())) {
            return false;
        }
        View view2 = this.contextToEmbeddedView.get(view.getContext());
        if (view2 == view) {
            return true;
        }
        return view2.checkInputConnectionProxy(view);
    }

    public PlatformViewRegistry getRegistry() {
        return this.registry;
    }

    public void onDetachedFromJNI() {
        diposeAllViews();
    }

    public void onPreEngineRestart() {
        diposeAllViews();
    }

    @Override // io.flutter.plugin.platform.PlatformViewsAccessibilityDelegate
    public View getPlatformViewById(int i) {
        if (usesVirtualDisplay(i)) {
            return this.vdControllers.get(Integer.valueOf(i)).getView();
        }
        PlatformView platformView = this.platformViews.get(i);
        if (platformView == null) {
            return null;
        }
        return platformView.getView();
    }

    @Override // io.flutter.plugin.platform.PlatformViewsAccessibilityDelegate
    public boolean usesVirtualDisplay(int i) {
        return this.vdControllers.containsKey(Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lockInputConnection(VirtualDisplayController virtualDisplayController) {
        TextInputPlugin textInputPlugin = this.textInputPlugin;
        if (textInputPlugin == null) {
            return;
        }
        textInputPlugin.lockPlatformViewInputConnection();
        virtualDisplayController.onInputConnectionLocked();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unlockInputConnection(VirtualDisplayController virtualDisplayController) {
        TextInputPlugin textInputPlugin = this.textInputPlugin;
        if (textInputPlugin == null) {
            return;
        }
        textInputPlugin.unlockPlatformViewInputConnection();
        virtualDisplayController.onInputConnectionUnlocked();
    }

    private static List<MotionEvent.PointerProperties> parsePointerPropertiesList(Object obj) {
        ArrayList arrayList = new ArrayList();
        Iterator it = ((List) obj).iterator();
        while (it.hasNext()) {
            arrayList.add(parsePointerProperties(it.next()));
        }
        return arrayList;
    }

    private static MotionEvent.PointerProperties parsePointerProperties(Object obj) {
        List list = (List) obj;
        MotionEvent.PointerProperties pointerProperties = new MotionEvent.PointerProperties();
        pointerProperties.id = ((Integer) list.get(0)).intValue();
        pointerProperties.toolType = ((Integer) list.get(1)).intValue();
        return pointerProperties;
    }

    private static List<MotionEvent.PointerCoords> parsePointerCoordsList(Object obj, float f) {
        ArrayList arrayList = new ArrayList();
        Iterator it = ((List) obj).iterator();
        while (it.hasNext()) {
            arrayList.add(parsePointerCoords(it.next(), f));
        }
        return arrayList;
    }

    private static MotionEvent.PointerCoords parsePointerCoords(Object obj, float f) {
        List list = (List) obj;
        MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
        pointerCoords.orientation = (float) ((Double) list.get(0)).doubleValue();
        pointerCoords.pressure = (float) ((Double) list.get(1)).doubleValue();
        pointerCoords.size = (float) ((Double) list.get(2)).doubleValue();
        pointerCoords.toolMajor = ((float) ((Double) list.get(3)).doubleValue()) * f;
        pointerCoords.toolMinor = ((float) ((Double) list.get(4)).doubleValue()) * f;
        pointerCoords.touchMajor = ((float) ((Double) list.get(5)).doubleValue()) * f;
        pointerCoords.touchMinor = ((float) ((Double) list.get(6)).doubleValue()) * f;
        pointerCoords.x = ((float) ((Double) list.get(7)).doubleValue()) * f;
        pointerCoords.y = ((float) ((Double) list.get(8)).doubleValue()) * f;
        return pointerCoords;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getDisplayDensity() {
        return this.context.getResources().getDisplayMetrics().density;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int toPhysicalPixels(double d) {
        return (int) Math.round(d * getDisplayDensity());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int toLogicalPixels(double d, float f) {
        return (int) Math.round(d / f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int toLogicalPixels(double d) {
        return toLogicalPixels(d, getDisplayDensity());
    }

    private void diposeAllViews() {
        while (this.platformViews.size() > 0) {
            this.channelHandler.dispose(this.platformViews.keyAt(0));
        }
    }

    private void initializeRootImageViewIfNeeded() {
        if (!this.synchronizeToNativeViewHierarchy || this.flutterViewConvertedToImageView) {
            return;
        }
        this.flutterView.convertToImageView();
        this.flutterViewConvertedToImageView = true;
    }

    void initializePlatformViewIfNeeded(final int i) {
        PlatformView platformView = this.platformViews.get(i);
        if (platformView == null) {
            throw new IllegalStateException("Platform view hasn't been initialized from the platform view channel.");
        }
        if (this.platformViewParent.get(i) != null) {
            return;
        }
        View view = platformView.getView();
        if (view == null) {
            throw new IllegalStateException("PlatformView#getView() returned null, but an Android view reference was expected.");
        }
        if (view.getParent() != null) {
            throw new IllegalStateException("The Android view returned from PlatformView#getView() was already added to a parent view.");
        }
        Context context = this.context;
        FlutterMutatorView flutterMutatorView = new FlutterMutatorView(context, context.getResources().getDisplayMetrics().density, this.androidTouchProcessor);
        flutterMutatorView.setOnDescendantFocusChangeListener(new View.OnFocusChangeListener() { // from class: io.flutter.plugin.platform.PlatformViewsController$$ExternalSyntheticLambda0
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view2, boolean z) {
                PlatformViewsController.this.m276x825f680(i, view2, z);
            }
        });
        this.platformViewParent.put(i, flutterMutatorView);
        view.setImportantForAccessibility(4);
        flutterMutatorView.addView(view);
        this.flutterView.addView(flutterMutatorView);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$initializePlatformViewIfNeeded$0$io-flutter-plugin-platform-PlatformViewsController, reason: not valid java name */
    public /* synthetic */ void m276x825f680(int i, View view, boolean z) {
        if (z) {
            this.platformViewsChannel.invokeViewFocused(i);
            return;
        }
        TextInputPlugin textInputPlugin = this.textInputPlugin;
        if (textInputPlugin != null) {
            textInputPlugin.clearPlatformViewClient(i);
        }
    }

    public void attachToFlutterRenderer(FlutterRenderer flutterRenderer) {
        this.androidTouchProcessor = new AndroidTouchProcessor(flutterRenderer, true);
    }

    public void onDisplayPlatformView(int i, int i2, int i3, int i4, int i5, int i6, int i7, FlutterMutatorsStack flutterMutatorsStack) {
        initializeRootImageViewIfNeeded();
        initializePlatformViewIfNeeded(i);
        FlutterMutatorView flutterMutatorView = this.platformViewParent.get(i);
        flutterMutatorView.readyToDisplay(flutterMutatorsStack, i2, i3, i4, i5);
        flutterMutatorView.setVisibility(0);
        flutterMutatorView.bringToFront();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i6, i7);
        View view = this.platformViews.get(i).getView();
        if (view != null) {
            view.setLayoutParams(layoutParams);
            view.bringToFront();
        }
        this.currentFrameUsedPlatformViewIds.add(Integer.valueOf(i));
    }

    public void onDisplayOverlaySurface(int i, int i2, int i3, int i4, int i5) {
        if (this.overlayLayerViews.get(i) == null) {
            throw new IllegalStateException("The overlay surface (id:" + i + ") doesn't exist");
        }
        initializeRootImageViewIfNeeded();
        PlatformOverlayView platformOverlayView = this.overlayLayerViews.get(i);
        if (platformOverlayView.getParent() == null) {
            this.flutterView.addView(platformOverlayView);
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i4, i5);
        layoutParams.leftMargin = i2;
        layoutParams.topMargin = i3;
        platformOverlayView.setLayoutParams(layoutParams);
        platformOverlayView.setVisibility(0);
        platformOverlayView.bringToFront();
        this.currentFrameUsedOverlayLayerIds.add(Integer.valueOf(i));
    }

    public void onBeginFrame() {
        this.currentFrameUsedOverlayLayerIds.clear();
        this.currentFrameUsedPlatformViewIds.clear();
    }

    public void onEndFrame() {
        boolean z = false;
        if (this.flutterViewConvertedToImageView && this.currentFrameUsedPlatformViewIds.isEmpty()) {
            this.flutterViewConvertedToImageView = false;
            this.flutterView.revertImageView(new Runnable() { // from class: io.flutter.plugin.platform.PlatformViewsController$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    PlatformViewsController.this.m277xe1328d28();
                }
            });
        } else {
            if (this.flutterViewConvertedToImageView && this.flutterView.acquireLatestImageViewFrame()) {
                z = true;
            }
            finishFrame(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onEndFrame$1$io-flutter-plugin-platform-PlatformViewsController, reason: not valid java name */
    public /* synthetic */ void m277xe1328d28() {
        finishFrame(false);
    }

    private void finishFrame(boolean z) {
        for (int i = 0; i < this.overlayLayerViews.size(); i++) {
            int keyAt = this.overlayLayerViews.keyAt(i);
            PlatformOverlayView valueAt = this.overlayLayerViews.valueAt(i);
            if (this.currentFrameUsedOverlayLayerIds.contains(Integer.valueOf(keyAt))) {
                this.flutterView.attachOverlaySurfaceToRender(valueAt);
                z &= valueAt.acquireLatestImage();
            } else {
                if (!this.flutterViewConvertedToImageView) {
                    valueAt.detachFromRenderer();
                }
                valueAt.setVisibility(8);
            }
        }
        for (int i2 = 0; i2 < this.platformViewParent.size(); i2++) {
            int keyAt2 = this.platformViewParent.keyAt(i2);
            FlutterMutatorView flutterMutatorView = this.platformViewParent.get(keyAt2);
            if (this.currentFrameUsedPlatformViewIds.contains(Integer.valueOf(keyAt2)) && (z || !this.synchronizeToNativeViewHierarchy)) {
                flutterMutatorView.setVisibility(0);
            } else {
                flutterMutatorView.setVisibility(8);
            }
        }
    }

    public FlutterOverlaySurface createOverlaySurface(PlatformOverlayView platformOverlayView) {
        int i = this.nextOverlayLayerId;
        this.nextOverlayLayerId = i + 1;
        this.overlayLayerViews.put(i, platformOverlayView);
        return new FlutterOverlaySurface(i, platformOverlayView.getSurface());
    }

    public FlutterOverlaySurface createOverlaySurface() {
        return createOverlaySurface(new PlatformOverlayView(this.flutterView.getContext(), this.flutterView.getWidth(), this.flutterView.getHeight(), this.accessibilityEventsDelegate));
    }

    public void destroyOverlaySurfaces() {
        for (int i = 0; i < this.overlayLayerViews.size(); i++) {
            PlatformOverlayView valueAt = this.overlayLayerViews.valueAt(i);
            valueAt.detachFromRenderer();
            valueAt.closeImageReader();
        }
    }

    private void removeOverlaySurfaces() {
        if (this.flutterView == null) {
            Log.e(TAG, "removeOverlaySurfaces called while flutter view is null");
            return;
        }
        for (int i = 0; i < this.overlayLayerViews.size(); i++) {
            this.flutterView.removeView(this.overlayLayerViews.valueAt(i));
        }
        this.overlayLayerViews.clear();
    }
}
