package io.flutter.plugins.webviewflutter;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.firebase.messaging.Constants;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugins.webviewflutter.GeneratedAndroidWebView;
import io.flutter.plugins.webviewflutter.WebChromeClientHostApiImpl;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes2.dex */
public class WebViewHostApiImpl implements GeneratedAndroidWebView.WebViewHostApi {
    private final BinaryMessenger binaryMessenger;
    private final View containerView;
    private Context context;
    private final InstanceManager instanceManager;
    private final WebViewProxy webViewProxy;

    /* loaded from: classes2.dex */
    public static class WebViewProxy {
        public WebViewPlatformView createWebView(Context context, BinaryMessenger binaryMessenger, InstanceManager instanceManager) {
            return new WebViewPlatformView(context, binaryMessenger, instanceManager);
        }

        public InputAwareWebViewPlatformView createInputAwareWebView(Context context, BinaryMessenger binaryMessenger, InstanceManager instanceManager, View view) {
            return new InputAwareWebViewPlatformView(context, binaryMessenger, instanceManager, view);
        }

        public void setWebContentsDebuggingEnabled(boolean z) {
            WebView.setWebContentsDebuggingEnabled(z);
        }
    }

    /* loaded from: classes2.dex */
    public static class WebViewPlatformView extends WebView implements PlatformView {
        private WebChromeClientHostApiImpl.SecureWebChromeClient currentWebChromeClient;
        private WebViewClient currentWebViewClient;

        @Override // io.flutter.plugin.platform.PlatformView
        public void dispose() {
        }

        @Override // io.flutter.plugin.platform.PlatformView
        public View getView() {
            return this;
        }

        @Override // io.flutter.plugin.platform.PlatformView
        public /* synthetic */ void onFlutterViewAttached(View view) {
            PlatformView.CC.$default$onFlutterViewAttached(this, view);
        }

        @Override // io.flutter.plugin.platform.PlatformView
        public /* synthetic */ void onFlutterViewDetached() {
            PlatformView.CC.$default$onFlutterViewDetached(this);
        }

        @Override // io.flutter.plugin.platform.PlatformView
        public /* synthetic */ void onInputConnectionLocked() {
            PlatformView.CC.$default$onInputConnectionLocked(this);
        }

        @Override // io.flutter.plugin.platform.PlatformView
        public /* synthetic */ void onInputConnectionUnlocked() {
            PlatformView.CC.$default$onInputConnectionUnlocked(this);
        }

        public WebViewPlatformView(Context context, BinaryMessenger binaryMessenger, InstanceManager instanceManager) {
            super(context);
            this.currentWebViewClient = new WebViewClient();
            this.currentWebChromeClient = new WebChromeClientHostApiImpl.SecureWebChromeClient();
            setWebViewClient(this.currentWebViewClient);
            setWebChromeClient(this.currentWebChromeClient);
        }

        @Override // android.webkit.WebView
        public void setWebViewClient(WebViewClient webViewClient) {
            super.setWebViewClient(webViewClient);
            this.currentWebViewClient = webViewClient;
            this.currentWebChromeClient.setWebViewClient(webViewClient);
        }

        @Override // android.webkit.WebView
        public void setWebChromeClient(WebChromeClient webChromeClient) {
            super.setWebChromeClient(webChromeClient);
            if (!(webChromeClient instanceof WebChromeClientHostApiImpl.SecureWebChromeClient)) {
                throw new AssertionError("Client must be a SecureWebChromeClient.");
            }
            WebChromeClientHostApiImpl.SecureWebChromeClient secureWebChromeClient = (WebChromeClientHostApiImpl.SecureWebChromeClient) webChromeClient;
            this.currentWebChromeClient = secureWebChromeClient;
            secureWebChromeClient.setWebViewClient(this.currentWebViewClient);
        }

        @Override // android.webkit.WebView
        public WebChromeClient getWebChromeClient() {
            return this.currentWebChromeClient;
        }
    }

    /* loaded from: classes2.dex */
    public static class InputAwareWebViewPlatformView extends InputAwareWebView implements PlatformView {
        private WebChromeClientHostApiImpl.SecureWebChromeClient currentWebChromeClient;
        private WebViewClient currentWebViewClient;

        @Override // io.flutter.plugin.platform.PlatformView
        public View getView() {
            return this;
        }

        @Override // io.flutter.plugins.webviewflutter.InputAwareWebView, android.view.View
        public /* bridge */ /* synthetic */ boolean checkInputConnectionProxy(View view) {
            return super.checkInputConnectionProxy(view);
        }

        @Override // io.flutter.plugins.webviewflutter.InputAwareWebView, android.view.ViewGroup, android.view.View
        public /* bridge */ /* synthetic */ void clearFocus() {
            super.clearFocus();
        }

        public InputAwareWebViewPlatformView(Context context, BinaryMessenger binaryMessenger, InstanceManager instanceManager, View view) {
            super(context, view);
            this.currentWebViewClient = new WebViewClient();
            this.currentWebChromeClient = new WebChromeClientHostApiImpl.SecureWebChromeClient();
            setWebViewClient(this.currentWebViewClient);
            setWebChromeClient(this.currentWebChromeClient);
        }

        @Override // io.flutter.plugin.platform.PlatformView
        public void onFlutterViewAttached(View view) {
            setContainerView(view);
        }

        @Override // io.flutter.plugin.platform.PlatformView
        public void onFlutterViewDetached() {
            setContainerView(null);
        }

        @Override // io.flutter.plugins.webviewflutter.InputAwareWebView, io.flutter.plugin.platform.PlatformView
        public void dispose() {
            super.dispose();
            destroy();
        }

        @Override // io.flutter.plugin.platform.PlatformView
        public void onInputConnectionLocked() {
            lockInputConnection();
        }

        @Override // io.flutter.plugin.platform.PlatformView
        public void onInputConnectionUnlocked() {
            unlockInputConnection();
        }

        @Override // android.webkit.WebView
        public void setWebViewClient(WebViewClient webViewClient) {
            super.setWebViewClient(webViewClient);
            this.currentWebViewClient = webViewClient;
            this.currentWebChromeClient.setWebViewClient(webViewClient);
        }

        @Override // android.webkit.WebView
        public void setWebChromeClient(WebChromeClient webChromeClient) {
            super.setWebChromeClient(webChromeClient);
            if (!(webChromeClient instanceof WebChromeClientHostApiImpl.SecureWebChromeClient)) {
                throw new AssertionError("Client must be a SecureWebChromeClient.");
            }
            WebChromeClientHostApiImpl.SecureWebChromeClient secureWebChromeClient = (WebChromeClientHostApiImpl.SecureWebChromeClient) webChromeClient;
            this.currentWebChromeClient = secureWebChromeClient;
            secureWebChromeClient.setWebViewClient(this.currentWebViewClient);
        }
    }

    public WebViewHostApiImpl(InstanceManager instanceManager, BinaryMessenger binaryMessenger, WebViewProxy webViewProxy, Context context, View view) {
        this.instanceManager = instanceManager;
        this.binaryMessenger = binaryMessenger;
        this.webViewProxy = webViewProxy;
        this.context = context;
        this.containerView = view;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void create(Long l, Boolean bool) {
        Object createInputAwareWebView;
        DisplayListenerProxy displayListenerProxy = new DisplayListenerProxy();
        DisplayManager displayManager = (DisplayManager) this.context.getSystemService(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION);
        displayListenerProxy.onPreWebViewInitialization(displayManager);
        if (bool.booleanValue()) {
            createInputAwareWebView = this.webViewProxy.createWebView(this.context, this.binaryMessenger, this.instanceManager);
        } else {
            createInputAwareWebView = this.webViewProxy.createInputAwareWebView(this.context, this.binaryMessenger, this.instanceManager, this.containerView);
        }
        displayListenerProxy.onPostWebViewInitialization(displayManager);
        this.instanceManager.addDartCreatedInstance(createInputAwareWebView, l.longValue());
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void loadData(Long l, String str, String str2, String str3) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).loadData(str, str2, str3);
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void loadDataWithBaseUrl(Long l, String str, String str2, String str3, String str4, String str5) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).loadDataWithBaseURL(str, str2, str3, str4, str5);
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void loadUrl(Long l, String str, Map<String, String> map) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).loadUrl(str, map);
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void postUrl(Long l, String str, byte[] bArr) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).postUrl(str, bArr);
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public String getUrl(Long l) {
        return ((WebView) this.instanceManager.getInstance(l.longValue())).getUrl();
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public Boolean canGoBack(Long l) {
        return Boolean.valueOf(((WebView) this.instanceManager.getInstance(l.longValue())).canGoBack());
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public Boolean canGoForward(Long l) {
        return Boolean.valueOf(((WebView) this.instanceManager.getInstance(l.longValue())).canGoForward());
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void goBack(Long l) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).goBack();
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void goForward(Long l) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).goForward();
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void reload(Long l) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).reload();
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void clearCache(Long l, Boolean bool) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).clearCache(bool.booleanValue());
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void evaluateJavascript(Long l, String str, final GeneratedAndroidWebView.Result<String> result) {
        WebView webView = (WebView) this.instanceManager.getInstance(l.longValue());
        Objects.requireNonNull(result);
        webView.evaluateJavascript(str, new ValueCallback() { // from class: io.flutter.plugins.webviewflutter.WebViewHostApiImpl$$ExternalSyntheticLambda0
            @Override // android.webkit.ValueCallback
            public final void onReceiveValue(Object obj) {
                GeneratedAndroidWebView.Result.this.success((String) obj);
            }
        });
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public String getTitle(Long l) {
        return ((WebView) this.instanceManager.getInstance(l.longValue())).getTitle();
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void scrollTo(Long l, Long l2, Long l3) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).scrollTo(l2.intValue(), l3.intValue());
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void scrollBy(Long l, Long l2, Long l3) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).scrollBy(l2.intValue(), l3.intValue());
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public Long getScrollX(Long l) {
        return Long.valueOf(((WebView) this.instanceManager.getInstance(l.longValue())).getScrollX());
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public Long getScrollY(Long l) {
        return Long.valueOf(((WebView) this.instanceManager.getInstance(l.longValue())).getScrollY());
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public GeneratedAndroidWebView.WebViewPoint getScrollPosition(Long l) {
        Objects.requireNonNull((WebView) this.instanceManager.getInstance(l.longValue()));
        return new GeneratedAndroidWebView.WebViewPoint.Builder().setX(Long.valueOf(r4.getScrollX())).setY(Long.valueOf(r4.getScrollY())).build();
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void setWebContentsDebuggingEnabled(Boolean bool) {
        this.webViewProxy.setWebContentsDebuggingEnabled(bool.booleanValue());
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void setWebViewClient(Long l, Long l2) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).setWebViewClient((WebViewClient) this.instanceManager.getInstance(l2.longValue()));
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void addJavaScriptChannel(Long l, Long l2) {
        WebView webView = (WebView) this.instanceManager.getInstance(l.longValue());
        JavaScriptChannel javaScriptChannel = (JavaScriptChannel) this.instanceManager.getInstance(l2.longValue());
        webView.addJavascriptInterface(javaScriptChannel, javaScriptChannel.javaScriptChannelName);
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void removeJavaScriptChannel(Long l, Long l2) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).removeJavascriptInterface(((JavaScriptChannel) this.instanceManager.getInstance(l2.longValue())).javaScriptChannelName);
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void setDownloadListener(Long l, Long l2) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).setDownloadListener((DownloadListener) this.instanceManager.getInstance(l2.longValue()));
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void setWebChromeClient(Long l, Long l2) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).setWebChromeClient((WebChromeClient) this.instanceManager.getInstance(l2.longValue()));
    }

    @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi
    public void setBackgroundColor(Long l, Long l2) {
        ((WebView) this.instanceManager.getInstance(l.longValue())).setBackgroundColor(l2.intValue());
    }

    public InstanceManager getInstanceManager() {
        return this.instanceManager;
    }
}
