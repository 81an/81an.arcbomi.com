package io.flutter.plugins.webviewflutter;

import android.webkit.WebView;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugins.webviewflutter.GeneratedAndroidWebView;

/* loaded from: classes2.dex */
public class WebViewFlutterApiImpl {
    private GeneratedAndroidWebView.WebViewFlutterApi api;
    private final BinaryMessenger binaryMessenger;
    private final InstanceManager instanceManager;

    public WebViewFlutterApiImpl(BinaryMessenger binaryMessenger, InstanceManager instanceManager) {
        this.binaryMessenger = binaryMessenger;
        this.instanceManager = instanceManager;
        this.api = new GeneratedAndroidWebView.WebViewFlutterApi(binaryMessenger);
    }

    public void create(WebView webView, GeneratedAndroidWebView.WebViewFlutterApi.Reply<Void> reply) {
        if (this.instanceManager.containsInstance(webView)) {
            return;
        }
        this.api.create(Long.valueOf(this.instanceManager.addHostCreatedInstance(webView)), reply);
    }

    void setApi(GeneratedAndroidWebView.WebViewFlutterApi webViewFlutterApi) {
        this.api = webViewFlutterApi;
    }
}
