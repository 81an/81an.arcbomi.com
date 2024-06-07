package io.flutter.plugins.webviewflutter;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.platform.PlatformViewRegistry;
import io.flutter.plugins.webviewflutter.DownloadListenerHostApiImpl;
import io.flutter.plugins.webviewflutter.FlutterAssetManager;
import io.flutter.plugins.webviewflutter.GeneratedAndroidWebView;
import io.flutter.plugins.webviewflutter.InstanceManager;
import io.flutter.plugins.webviewflutter.JavaScriptChannelHostApiImpl;
import io.flutter.plugins.webviewflutter.WebChromeClientHostApiImpl;
import io.flutter.plugins.webviewflutter.WebSettingsHostApiImpl;
import io.flutter.plugins.webviewflutter.WebStorageHostApiImpl;
import io.flutter.plugins.webviewflutter.WebViewClientHostApiImpl;
import io.flutter.plugins.webviewflutter.WebViewHostApiImpl;

/* loaded from: classes2.dex */
public class WebViewFlutterPlugin implements FlutterPlugin, ActivityAware {
    private InstanceManager instanceManager;
    private JavaScriptChannelHostApiImpl javaScriptChannelHostApi;
    private FlutterPlugin.FlutterPluginBinding pluginBinding;
    private WebViewHostApiImpl webViewHostApi;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setUp$0(Void r0) {
    }

    public static void registerWith(PluginRegistry.Registrar registrar) {
        new WebViewFlutterPlugin().setUp(registrar.messenger(), registrar.platformViewRegistry(), registrar.activity(), registrar.view(), new FlutterAssetManager.RegistrarFlutterAssetManager(registrar.context().getAssets(), registrar));
    }

    private void setUp(final BinaryMessenger binaryMessenger, PlatformViewRegistry platformViewRegistry, Context context, View view, FlutterAssetManager flutterAssetManager) {
        this.instanceManager = InstanceManager.open(new InstanceManager.FinalizationListener() { // from class: io.flutter.plugins.webviewflutter.WebViewFlutterPlugin$$ExternalSyntheticLambda2
            @Override // io.flutter.plugins.webviewflutter.InstanceManager.FinalizationListener
            public final void onFinalize(long j) {
                new GeneratedAndroidWebView.JavaObjectFlutterApi(BinaryMessenger.this).dispose(Long.valueOf(j), new GeneratedAndroidWebView.JavaObjectFlutterApi.Reply() { // from class: io.flutter.plugins.webviewflutter.WebViewFlutterPlugin$$ExternalSyntheticLambda1
                    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.JavaObjectFlutterApi.Reply
                    public final void reply(Object obj) {
                        WebViewFlutterPlugin.lambda$setUp$0((Void) obj);
                    }
                });
            }
        });
        GeneratedAndroidWebView.InstanceManagerHostApi.CC.setup(binaryMessenger, new GeneratedAndroidWebView.InstanceManagerHostApi() { // from class: io.flutter.plugins.webviewflutter.WebViewFlutterPlugin$$ExternalSyntheticLambda0
            @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.InstanceManagerHostApi
            public final void clear() {
                WebViewFlutterPlugin.this.m393xe294f6d1();
            }
        });
        platformViewRegistry.registerViewFactory("plugins.flutter.io/webview", new FlutterWebViewFactory(this.instanceManager));
        this.webViewHostApi = new WebViewHostApiImpl(this.instanceManager, binaryMessenger, new WebViewHostApiImpl.WebViewProxy(), context, view);
        this.javaScriptChannelHostApi = new JavaScriptChannelHostApiImpl(this.instanceManager, new JavaScriptChannelHostApiImpl.JavaScriptChannelCreator(), new JavaScriptChannelFlutterApiImpl(binaryMessenger, this.instanceManager), new Handler(context.getMainLooper()));
        GeneratedAndroidWebView.JavaObjectHostApi.CC.setup(binaryMessenger, new JavaObjectHostApiImpl(this.instanceManager));
        GeneratedAndroidWebView.WebViewHostApi.CC.setup(binaryMessenger, this.webViewHostApi);
        GeneratedAndroidWebView.JavaScriptChannelHostApi.CC.setup(binaryMessenger, this.javaScriptChannelHostApi);
        GeneratedAndroidWebView.WebViewClientHostApi.CC.setup(binaryMessenger, new WebViewClientHostApiImpl(this.instanceManager, new WebViewClientHostApiImpl.WebViewClientCreator(), new WebViewClientFlutterApiImpl(binaryMessenger, this.instanceManager)));
        GeneratedAndroidWebView.WebChromeClientHostApi.CC.setup(binaryMessenger, new WebChromeClientHostApiImpl(this.instanceManager, new WebChromeClientHostApiImpl.WebChromeClientCreator(), new WebChromeClientFlutterApiImpl(binaryMessenger, this.instanceManager)));
        GeneratedAndroidWebView.DownloadListenerHostApi.CC.setup(binaryMessenger, new DownloadListenerHostApiImpl(this.instanceManager, new DownloadListenerHostApiImpl.DownloadListenerCreator(), new DownloadListenerFlutterApiImpl(binaryMessenger, this.instanceManager)));
        GeneratedAndroidWebView.WebSettingsHostApi.CC.setup(binaryMessenger, new WebSettingsHostApiImpl(this.instanceManager, new WebSettingsHostApiImpl.WebSettingsCreator()));
        GeneratedAndroidWebView.FlutterAssetManagerHostApi.CC.setup(binaryMessenger, new FlutterAssetManagerHostApiImpl(flutterAssetManager));
        GeneratedAndroidWebView.CookieManagerHostApi.CC.setup(binaryMessenger, new CookieManagerHostApiImpl());
        GeneratedAndroidWebView.WebStorageHostApi.CC.setup(binaryMessenger, new WebStorageHostApiImpl(this.instanceManager, new WebStorageHostApiImpl.WebStorageCreator()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$setUp$2$io-flutter-plugins-webviewflutter-WebViewFlutterPlugin, reason: not valid java name */
    public /* synthetic */ void m393xe294f6d1() {
        this.instanceManager.clear();
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.pluginBinding = flutterPluginBinding;
        setUp(flutterPluginBinding.getBinaryMessenger(), flutterPluginBinding.getPlatformViewRegistry(), flutterPluginBinding.getApplicationContext(), null, new FlutterAssetManager.PluginBindingFlutterAssetManager(flutterPluginBinding.getApplicationContext().getAssets(), flutterPluginBinding.getFlutterAssets()));
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        InstanceManager instanceManager = this.instanceManager;
        if (instanceManager != null) {
            instanceManager.close();
            this.instanceManager = null;
        }
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        updateContext(activityPluginBinding.getActivity());
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
        updateContext(this.pluginBinding.getApplicationContext());
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
        updateContext(activityPluginBinding.getActivity());
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
        updateContext(this.pluginBinding.getApplicationContext());
    }

    private void updateContext(Context context) {
        this.webViewHostApi.setContext(context);
        this.javaScriptChannelHostApi.setPlatformThreadHandler(new Handler(context.getMainLooper()));
    }

    public InstanceManager getInstanceManager() {
        return this.instanceManager;
    }
}
