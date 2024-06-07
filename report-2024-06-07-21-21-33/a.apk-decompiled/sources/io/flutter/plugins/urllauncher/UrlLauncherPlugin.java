package io.flutter.plugins.urllauncher;

import android.util.Log;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.PluginRegistry;

/* loaded from: classes2.dex */
public final class UrlLauncherPlugin implements FlutterPlugin, ActivityAware {
    private static final String TAG = "UrlLauncherPlugin";
    private MethodCallHandlerImpl methodCallHandler;
    private UrlLauncher urlLauncher;

    public static void registerWith(PluginRegistry.Registrar registrar) {
        new MethodCallHandlerImpl(new UrlLauncher(registrar.context(), registrar.activity())).startListening(registrar.messenger());
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        UrlLauncher urlLauncher = new UrlLauncher(flutterPluginBinding.getApplicationContext(), null);
        this.urlLauncher = urlLauncher;
        MethodCallHandlerImpl methodCallHandlerImpl = new MethodCallHandlerImpl(urlLauncher);
        this.methodCallHandler = methodCallHandlerImpl;
        methodCallHandlerImpl.startListening(flutterPluginBinding.getBinaryMessenger());
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        MethodCallHandlerImpl methodCallHandlerImpl = this.methodCallHandler;
        if (methodCallHandlerImpl == null) {
            Log.wtf(TAG, "Already detached from the engine.");
            return;
        }
        methodCallHandlerImpl.stopListening();
        this.methodCallHandler = null;
        this.urlLauncher = null;
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        if (this.methodCallHandler == null) {
            Log.wtf(TAG, "urlLauncher was never set.");
        } else {
            this.urlLauncher.setActivity(activityPluginBinding.getActivity());
        }
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
        if (this.methodCallHandler == null) {
            Log.wtf(TAG, "urlLauncher was never set.");
        } else {
            this.urlLauncher.setActivity(null);
        }
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
        onDetachedFromActivity();
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
        onAttachedToActivity(activityPluginBinding);
    }
}
