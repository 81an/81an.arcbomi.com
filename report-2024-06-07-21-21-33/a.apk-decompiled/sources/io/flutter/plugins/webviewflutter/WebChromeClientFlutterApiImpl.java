package io.flutter.plugins.webviewflutter;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugins.webviewflutter.GeneratedAndroidWebView;
import java.util.List;
import java.util.Objects;

/* loaded from: classes2.dex */
public class WebChromeClientFlutterApiImpl extends GeneratedAndroidWebView.WebChromeClientFlutterApi {
    private final BinaryMessenger binaryMessenger;
    private final InstanceManager instanceManager;
    private final WebViewFlutterApiImpl webViewFlutterApi;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onProgressChanged$0(Void r0) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onShowFileChooser$1(Void r0) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onShowFileChooser$2(Void r0) {
    }

    public WebChromeClientFlutterApiImpl(BinaryMessenger binaryMessenger, InstanceManager instanceManager) {
        super(binaryMessenger);
        this.binaryMessenger = binaryMessenger;
        this.instanceManager = instanceManager;
        this.webViewFlutterApi = new WebViewFlutterApiImpl(binaryMessenger, instanceManager);
    }

    public void onProgressChanged(WebChromeClient webChromeClient, WebView webView, Long l, GeneratedAndroidWebView.WebChromeClientFlutterApi.Reply<Void> reply) {
        this.webViewFlutterApi.create(webView, new GeneratedAndroidWebView.WebViewFlutterApi.Reply() { // from class: io.flutter.plugins.webviewflutter.WebChromeClientFlutterApiImpl$$ExternalSyntheticLambda1
            @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewFlutterApi.Reply
            public final void reply(Object obj) {
                WebChromeClientFlutterApiImpl.lambda$onProgressChanged$0((Void) obj);
            }
        });
        Long identifierForStrongReference = this.instanceManager.getIdentifierForStrongReference(webView);
        Objects.requireNonNull(identifierForStrongReference);
        super.onProgressChanged(Long.valueOf(getIdentifierForClient(webChromeClient)), identifierForStrongReference, l, reply);
    }

    public void onShowFileChooser(WebChromeClient webChromeClient, WebView webView, WebChromeClient.FileChooserParams fileChooserParams, GeneratedAndroidWebView.WebChromeClientFlutterApi.Reply<List<String>> reply) {
        this.webViewFlutterApi.create(webView, new GeneratedAndroidWebView.WebViewFlutterApi.Reply() { // from class: io.flutter.plugins.webviewflutter.WebChromeClientFlutterApiImpl$$ExternalSyntheticLambda2
            @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewFlutterApi.Reply
            public final void reply(Object obj) {
                WebChromeClientFlutterApiImpl.lambda$onShowFileChooser$1((Void) obj);
            }
        });
        new FileChooserParamsFlutterApiImpl(this.binaryMessenger, this.instanceManager).create(fileChooserParams, new GeneratedAndroidWebView.FileChooserParamsFlutterApi.Reply() { // from class: io.flutter.plugins.webviewflutter.WebChromeClientFlutterApiImpl$$ExternalSyntheticLambda0
            @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.FileChooserParamsFlutterApi.Reply
            public final void reply(Object obj) {
                WebChromeClientFlutterApiImpl.lambda$onShowFileChooser$2((Void) obj);
            }
        });
        Long identifierForStrongReference = this.instanceManager.getIdentifierForStrongReference(webChromeClient);
        Objects.requireNonNull(identifierForStrongReference);
        Long identifierForStrongReference2 = this.instanceManager.getIdentifierForStrongReference(webView);
        Objects.requireNonNull(identifierForStrongReference2);
        Long identifierForStrongReference3 = this.instanceManager.getIdentifierForStrongReference(fileChooserParams);
        Objects.requireNonNull(identifierForStrongReference3);
        onShowFileChooser(identifierForStrongReference, identifierForStrongReference2, identifierForStrongReference3, reply);
    }

    private long getIdentifierForClient(WebChromeClient webChromeClient) {
        Long identifierForStrongReference = this.instanceManager.getIdentifierForStrongReference(webChromeClient);
        if (identifierForStrongReference == null) {
            throw new IllegalStateException("Could not find identifier for WebChromeClient.");
        }
        return identifierForStrongReference.longValue();
    }
}
