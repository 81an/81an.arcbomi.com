package io.flutter.plugins.webviewflutter;

import android.util.Log;
import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MessageCodec;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugins.webviewflutter.GeneratedAndroidWebView;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class GeneratedAndroidWebView {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$2, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ boolean $assertionsDisabled = false;
    }

    /* loaded from: classes2.dex */
    public interface Result<T> {
        void error(Throwable th);

        void success(T t);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ArrayList<Object> wrapError(Throwable th) {
        ArrayList<Object> arrayList = new ArrayList<>(3);
        arrayList.add(th.toString());
        arrayList.add(th.getClass().getSimpleName());
        arrayList.add("Cause: " + th.getCause() + ", Stacktrace: " + Log.getStackTraceString(th));
        return arrayList;
    }

    /* loaded from: classes2.dex */
    public enum FileChooserMode {
        OPEN(0),
        OPEN_MULTIPLE(1),
        SAVE(2);

        private final int index;

        FileChooserMode(int i) {
            this.index = i;
        }
    }

    /* loaded from: classes2.dex */
    public static final class FileChooserModeEnumData {
        private FileChooserMode value;

        public FileChooserMode getValue() {
            return this.value;
        }

        public void setValue(FileChooserMode fileChooserMode) {
            if (fileChooserMode == null) {
                throw new IllegalStateException("Nonnull field \"value\" is null.");
            }
            this.value = fileChooserMode;
        }

        private FileChooserModeEnumData() {
        }

        /* loaded from: classes2.dex */
        public static final class Builder {
            private FileChooserMode value;

            public Builder setValue(FileChooserMode fileChooserMode) {
                this.value = fileChooserMode;
                return this;
            }

            public FileChooserModeEnumData build() {
                FileChooserModeEnumData fileChooserModeEnumData = new FileChooserModeEnumData();
                fileChooserModeEnumData.setValue(this.value);
                return fileChooserModeEnumData;
            }
        }

        ArrayList<Object> toList() {
            ArrayList<Object> arrayList = new ArrayList<>(1);
            FileChooserMode fileChooserMode = this.value;
            arrayList.add(fileChooserMode == null ? null : Integer.valueOf(fileChooserMode.index));
            return arrayList;
        }

        static FileChooserModeEnumData fromList(ArrayList<Object> arrayList) {
            FileChooserModeEnumData fileChooserModeEnumData = new FileChooserModeEnumData();
            Object obj = arrayList.get(0);
            fileChooserModeEnumData.setValue(obj == null ? null : FileChooserMode.values()[((Integer) obj).intValue()]);
            return fileChooserModeEnumData;
        }
    }

    /* loaded from: classes2.dex */
    public static final class WebResourceRequestData {
        private Boolean hasGesture;
        private Boolean isForMainFrame;
        private Boolean isRedirect;
        private String method;
        private Map<String, String> requestHeaders;
        private String url;

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            if (str == null) {
                throw new IllegalStateException("Nonnull field \"url\" is null.");
            }
            this.url = str;
        }

        public Boolean getIsForMainFrame() {
            return this.isForMainFrame;
        }

        public void setIsForMainFrame(Boolean bool) {
            if (bool == null) {
                throw new IllegalStateException("Nonnull field \"isForMainFrame\" is null.");
            }
            this.isForMainFrame = bool;
        }

        public Boolean getIsRedirect() {
            return this.isRedirect;
        }

        public void setIsRedirect(Boolean bool) {
            this.isRedirect = bool;
        }

        public Boolean getHasGesture() {
            return this.hasGesture;
        }

        public void setHasGesture(Boolean bool) {
            if (bool == null) {
                throw new IllegalStateException("Nonnull field \"hasGesture\" is null.");
            }
            this.hasGesture = bool;
        }

        public String getMethod() {
            return this.method;
        }

        public void setMethod(String str) {
            if (str == null) {
                throw new IllegalStateException("Nonnull field \"method\" is null.");
            }
            this.method = str;
        }

        public Map<String, String> getRequestHeaders() {
            return this.requestHeaders;
        }

        public void setRequestHeaders(Map<String, String> map) {
            if (map == null) {
                throw new IllegalStateException("Nonnull field \"requestHeaders\" is null.");
            }
            this.requestHeaders = map;
        }

        private WebResourceRequestData() {
        }

        /* loaded from: classes2.dex */
        public static final class Builder {
            private Boolean hasGesture;
            private Boolean isForMainFrame;
            private Boolean isRedirect;
            private String method;
            private Map<String, String> requestHeaders;
            private String url;

            public Builder setUrl(String str) {
                this.url = str;
                return this;
            }

            public Builder setIsForMainFrame(Boolean bool) {
                this.isForMainFrame = bool;
                return this;
            }

            public Builder setIsRedirect(Boolean bool) {
                this.isRedirect = bool;
                return this;
            }

            public Builder setHasGesture(Boolean bool) {
                this.hasGesture = bool;
                return this;
            }

            public Builder setMethod(String str) {
                this.method = str;
                return this;
            }

            public Builder setRequestHeaders(Map<String, String> map) {
                this.requestHeaders = map;
                return this;
            }

            public WebResourceRequestData build() {
                WebResourceRequestData webResourceRequestData = new WebResourceRequestData();
                webResourceRequestData.setUrl(this.url);
                webResourceRequestData.setIsForMainFrame(this.isForMainFrame);
                webResourceRequestData.setIsRedirect(this.isRedirect);
                webResourceRequestData.setHasGesture(this.hasGesture);
                webResourceRequestData.setMethod(this.method);
                webResourceRequestData.setRequestHeaders(this.requestHeaders);
                return webResourceRequestData;
            }
        }

        ArrayList<Object> toList() {
            ArrayList<Object> arrayList = new ArrayList<>(6);
            arrayList.add(this.url);
            arrayList.add(this.isForMainFrame);
            arrayList.add(this.isRedirect);
            arrayList.add(this.hasGesture);
            arrayList.add(this.method);
            arrayList.add(this.requestHeaders);
            return arrayList;
        }

        static WebResourceRequestData fromList(ArrayList<Object> arrayList) {
            WebResourceRequestData webResourceRequestData = new WebResourceRequestData();
            webResourceRequestData.setUrl((String) arrayList.get(0));
            webResourceRequestData.setIsForMainFrame((Boolean) arrayList.get(1));
            webResourceRequestData.setIsRedirect((Boolean) arrayList.get(2));
            webResourceRequestData.setHasGesture((Boolean) arrayList.get(3));
            webResourceRequestData.setMethod((String) arrayList.get(4));
            webResourceRequestData.setRequestHeaders((Map) arrayList.get(5));
            return webResourceRequestData;
        }
    }

    /* loaded from: classes2.dex */
    public static final class WebResourceErrorData {
        private String description;
        private Long errorCode;

        public Long getErrorCode() {
            return this.errorCode;
        }

        public void setErrorCode(Long l) {
            if (l == null) {
                throw new IllegalStateException("Nonnull field \"errorCode\" is null.");
            }
            this.errorCode = l;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String str) {
            if (str == null) {
                throw new IllegalStateException("Nonnull field \"description\" is null.");
            }
            this.description = str;
        }

        private WebResourceErrorData() {
        }

        /* loaded from: classes2.dex */
        public static final class Builder {
            private String description;
            private Long errorCode;

            public Builder setErrorCode(Long l) {
                this.errorCode = l;
                return this;
            }

            public Builder setDescription(String str) {
                this.description = str;
                return this;
            }

            public WebResourceErrorData build() {
                WebResourceErrorData webResourceErrorData = new WebResourceErrorData();
                webResourceErrorData.setErrorCode(this.errorCode);
                webResourceErrorData.setDescription(this.description);
                return webResourceErrorData;
            }
        }

        ArrayList<Object> toList() {
            ArrayList<Object> arrayList = new ArrayList<>(2);
            arrayList.add(this.errorCode);
            arrayList.add(this.description);
            return arrayList;
        }

        static WebResourceErrorData fromList(ArrayList<Object> arrayList) {
            Long valueOf;
            WebResourceErrorData webResourceErrorData = new WebResourceErrorData();
            Object obj = arrayList.get(0);
            if (obj == null) {
                valueOf = null;
            } else {
                valueOf = Long.valueOf(obj instanceof Integer ? ((Integer) obj).intValue() : ((Long) obj).longValue());
            }
            webResourceErrorData.setErrorCode(valueOf);
            webResourceErrorData.setDescription((String) arrayList.get(1));
            return webResourceErrorData;
        }
    }

    /* loaded from: classes2.dex */
    public static final class WebViewPoint {
        private Long x;
        private Long y;

        public Long getX() {
            return this.x;
        }

        public void setX(Long l) {
            if (l == null) {
                throw new IllegalStateException("Nonnull field \"x\" is null.");
            }
            this.x = l;
        }

        public Long getY() {
            return this.y;
        }

        public void setY(Long l) {
            if (l == null) {
                throw new IllegalStateException("Nonnull field \"y\" is null.");
            }
            this.y = l;
        }

        private WebViewPoint() {
        }

        /* loaded from: classes2.dex */
        public static final class Builder {
            private Long x;
            private Long y;

            public Builder setX(Long l) {
                this.x = l;
                return this;
            }

            public Builder setY(Long l) {
                this.y = l;
                return this;
            }

            public WebViewPoint build() {
                WebViewPoint webViewPoint = new WebViewPoint();
                webViewPoint.setX(this.x);
                webViewPoint.setY(this.y);
                return webViewPoint;
            }
        }

        ArrayList<Object> toList() {
            ArrayList<Object> arrayList = new ArrayList<>(2);
            arrayList.add(this.x);
            arrayList.add(this.y);
            return arrayList;
        }

        static WebViewPoint fromList(ArrayList<Object> arrayList) {
            Long valueOf;
            WebViewPoint webViewPoint = new WebViewPoint();
            Object obj = arrayList.get(0);
            Long l = null;
            if (obj == null) {
                valueOf = null;
            } else {
                valueOf = Long.valueOf(obj instanceof Integer ? ((Integer) obj).intValue() : ((Long) obj).longValue());
            }
            webViewPoint.setX(valueOf);
            Object obj2 = arrayList.get(1);
            if (obj2 != null) {
                l = Long.valueOf(obj2 instanceof Integer ? ((Integer) obj2).intValue() : ((Long) obj2).longValue());
            }
            webViewPoint.setY(l);
            return webViewPoint;
        }
    }

    /* loaded from: classes2.dex */
    public interface InstanceManagerHostApi {
        void clear();

        /* renamed from: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$InstanceManagerHostApi$-CC, reason: invalid class name */
        /* loaded from: classes2.dex */
        public final /* synthetic */ class CC {
            public static MessageCodec<Object> getCodec() {
                return new StandardMessageCodec();
            }

            public static void setup(BinaryMessenger binaryMessenger, final InstanceManagerHostApi instanceManagerHostApi) {
                BasicMessageChannel basicMessageChannel = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.InstanceManagerHostApi.clear", getCodec());
                if (instanceManagerHostApi != null) {
                    basicMessageChannel.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$InstanceManagerHostApi$$ExternalSyntheticLambda0
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.InstanceManagerHostApi.CC.lambda$setup$0(GeneratedAndroidWebView.InstanceManagerHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel.setMessageHandler(null);
                }
            }

            public static /* synthetic */ void lambda$setup$0(InstanceManagerHostApi instanceManagerHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList = new ArrayList();
                try {
                    instanceManagerHostApi.clear();
                    arrayList.add(0, null);
                } catch (Error | RuntimeException e) {
                    arrayList = GeneratedAndroidWebView.wrapError(e);
                }
                reply.reply(arrayList);
            }
        }
    }

    /* loaded from: classes2.dex */
    public interface JavaObjectHostApi {
        static /* synthetic */ int $desugar$clinit;

        void dispose(Long l);

        static {
            boolean z = AnonymousClass2.$assertionsDisabled;
        }

        /* renamed from: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$JavaObjectHostApi$-CC, reason: invalid class name */
        /* loaded from: classes2.dex */
        public final /* synthetic */ class CC {
            static {
                int i = JavaObjectHostApi.$desugar$clinit;
            }

            public static MessageCodec<Object> getCodec() {
                return new StandardMessageCodec();
            }

            public static void setup(BinaryMessenger binaryMessenger, final JavaObjectHostApi javaObjectHostApi) {
                BasicMessageChannel basicMessageChannel = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.JavaObjectHostApi.dispose", getCodec());
                if (javaObjectHostApi != null) {
                    basicMessageChannel.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$JavaObjectHostApi$$ExternalSyntheticLambda0
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.JavaObjectHostApi.CC.lambda$setup$0(GeneratedAndroidWebView.JavaObjectHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel.setMessageHandler(null);
                }
            }

            public static /* synthetic */ void lambda$setup$0(JavaObjectHostApi javaObjectHostApi, Object obj, BasicMessageChannel.Reply reply) {
                Number number;
                ArrayList arrayList = new ArrayList();
                try {
                    ArrayList arrayList2 = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList2 == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList2.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("identifierArg unexpectedly null.");
                }
                javaObjectHostApi.dispose(number == null ? null : Long.valueOf(number.longValue()));
                arrayList.add(0, null);
                reply.reply(arrayList);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class JavaObjectFlutterApi {
        private final BinaryMessenger binaryMessenger;

        /* loaded from: classes2.dex */
        public interface Reply<T> {
            void reply(T t);
        }

        public JavaObjectFlutterApi(BinaryMessenger binaryMessenger) {
            this.binaryMessenger = binaryMessenger;
        }

        static MessageCodec<Object> getCodec() {
            return new StandardMessageCodec();
        }

        public void dispose(Long l, final Reply<Void> reply) {
            new BasicMessageChannel(this.binaryMessenger, "dev.flutter.pigeon.JavaObjectFlutterApi.dispose", getCodec()).send(new ArrayList(Collections.singletonList(l)), new BasicMessageChannel.Reply() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$JavaObjectFlutterApi$$ExternalSyntheticLambda0
                @Override // io.flutter.plugin.common.BasicMessageChannel.Reply
                public final void reply(Object obj) {
                    GeneratedAndroidWebView.JavaObjectFlutterApi.Reply.this.reply(null);
                }
            });
        }
    }

    /* loaded from: classes2.dex */
    public interface CookieManagerHostApi {
        void clearCookies(Result<Boolean> result);

        void setCookie(String str, String str2);

        /* renamed from: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$CookieManagerHostApi$-CC, reason: invalid class name */
        /* loaded from: classes2.dex */
        public final /* synthetic */ class CC {
            public static MessageCodec<Object> getCodec() {
                return new StandardMessageCodec();
            }

            public static void setup(BinaryMessenger binaryMessenger, final CookieManagerHostApi cookieManagerHostApi) {
                BasicMessageChannel basicMessageChannel = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.CookieManagerHostApi.clearCookies", getCodec());
                if (cookieManagerHostApi != null) {
                    basicMessageChannel.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$CookieManagerHostApi$$ExternalSyntheticLambda0
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.CookieManagerHostApi.CC.lambda$setup$0(GeneratedAndroidWebView.CookieManagerHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel2 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.CookieManagerHostApi.setCookie", getCodec());
                if (cookieManagerHostApi != null) {
                    basicMessageChannel2.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$CookieManagerHostApi$$ExternalSyntheticLambda1
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.CookieManagerHostApi.CC.lambda$setup$1(GeneratedAndroidWebView.CookieManagerHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel2.setMessageHandler(null);
                }
            }

            public static /* synthetic */ void lambda$setup$0(CookieManagerHostApi cookieManagerHostApi, Object obj, final BasicMessageChannel.Reply reply) {
                final ArrayList arrayList = new ArrayList();
                try {
                    cookieManagerHostApi.clearCookies(new Result<Boolean>() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.CookieManagerHostApi.1
                        @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.Result
                        public void success(Boolean bool) {
                            arrayList.add(0, bool);
                            reply.reply(arrayList);
                        }

                        @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.Result
                        public void error(Throwable th) {
                            reply.reply(GeneratedAndroidWebView.wrapError(th));
                        }
                    });
                } catch (Error | RuntimeException e) {
                    reply.reply(GeneratedAndroidWebView.wrapError(e));
                }
            }

            public static /* synthetic */ void lambda$setup$1(CookieManagerHostApi cookieManagerHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                String str;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    str = (String) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (str == null) {
                    throw new NullPointerException("urlArg unexpectedly null.");
                }
                String str2 = (String) arrayList.get(1);
                if (str2 == null) {
                    throw new NullPointerException("valueArg unexpectedly null.");
                }
                cookieManagerHostApi.setCookie(str, str2);
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class WebViewHostApiCodec extends StandardMessageCodec {
        public static final WebViewHostApiCodec INSTANCE = new WebViewHostApiCodec();

        private WebViewHostApiCodec() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.flutter.plugin.common.StandardMessageCodec
        public Object readValueOfType(byte b, ByteBuffer byteBuffer) {
            if (b == Byte.MIN_VALUE) {
                return WebViewPoint.fromList((ArrayList) readValue(byteBuffer));
            }
            return super.readValueOfType(b, byteBuffer);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.flutter.plugin.common.StandardMessageCodec
        public void writeValue(ByteArrayOutputStream byteArrayOutputStream, Object obj) {
            if (obj instanceof WebViewPoint) {
                byteArrayOutputStream.write(128);
                writeValue(byteArrayOutputStream, ((WebViewPoint) obj).toList());
            } else {
                super.writeValue(byteArrayOutputStream, obj);
            }
        }
    }

    /* loaded from: classes2.dex */
    public interface WebViewHostApi {
        void addJavaScriptChannel(Long l, Long l2);

        Boolean canGoBack(Long l);

        Boolean canGoForward(Long l);

        void clearCache(Long l, Boolean bool);

        void create(Long l, Boolean bool);

        void evaluateJavascript(Long l, String str, Result<String> result);

        WebViewPoint getScrollPosition(Long l);

        Long getScrollX(Long l);

        Long getScrollY(Long l);

        String getTitle(Long l);

        String getUrl(Long l);

        void goBack(Long l);

        void goForward(Long l);

        void loadData(Long l, String str, String str2, String str3);

        void loadDataWithBaseUrl(Long l, String str, String str2, String str3, String str4, String str5);

        void loadUrl(Long l, String str, Map<String, String> map);

        void postUrl(Long l, String str, byte[] bArr);

        void reload(Long l);

        void removeJavaScriptChannel(Long l, Long l2);

        void scrollBy(Long l, Long l2, Long l3);

        void scrollTo(Long l, Long l2, Long l3);

        void setBackgroundColor(Long l, Long l2);

        void setDownloadListener(Long l, Long l2);

        void setWebChromeClient(Long l, Long l2);

        void setWebContentsDebuggingEnabled(Boolean bool);

        void setWebViewClient(Long l, Long l2);

        /* renamed from: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewHostApi$-CC, reason: invalid class name */
        /* loaded from: classes2.dex */
        public final /* synthetic */ class CC {
            public static MessageCodec<Object> getCodec() {
                return WebViewHostApiCodec.INSTANCE;
            }

            public static void setup(BinaryMessenger binaryMessenger, final WebViewHostApi webViewHostApi) {
                BasicMessageChannel basicMessageChannel = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewHostApi.create", getCodec());
                if (webViewHostApi != null) {
                    basicMessageChannel.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewHostApi$$ExternalSyntheticLambda0
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewHostApi.CC.lambda$setup$0(GeneratedAndroidWebView.WebViewHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel2 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewHostApi.loadData", getCodec());
                if (webViewHostApi != null) {
                    basicMessageChannel2.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewHostApi$$ExternalSyntheticLambda11
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewHostApi.CC.lambda$setup$1(GeneratedAndroidWebView.WebViewHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel2.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel3 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewHostApi.loadDataWithBaseUrl", getCodec());
                if (webViewHostApi != null) {
                    basicMessageChannel3.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewHostApi$$ExternalSyntheticLambda3
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewHostApi.CC.lambda$setup$2(GeneratedAndroidWebView.WebViewHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel3.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel4 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewHostApi.loadUrl", getCodec());
                if (webViewHostApi != null) {
                    basicMessageChannel4.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewHostApi$$ExternalSyntheticLambda10
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewHostApi.CC.lambda$setup$3(GeneratedAndroidWebView.WebViewHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel4.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel5 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewHostApi.postUrl", getCodec());
                if (webViewHostApi != null) {
                    basicMessageChannel5.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewHostApi$$ExternalSyntheticLambda12
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewHostApi.CC.lambda$setup$4(GeneratedAndroidWebView.WebViewHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel5.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel6 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewHostApi.getUrl", getCodec());
                if (webViewHostApi != null) {
                    basicMessageChannel6.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewHostApi$$ExternalSyntheticLambda13
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewHostApi.CC.lambda$setup$5(GeneratedAndroidWebView.WebViewHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel6.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel7 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewHostApi.canGoBack", getCodec());
                if (webViewHostApi != null) {
                    basicMessageChannel7.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewHostApi$$ExternalSyntheticLambda14
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewHostApi.CC.lambda$setup$6(GeneratedAndroidWebView.WebViewHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel7.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel8 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewHostApi.canGoForward", getCodec());
                if (webViewHostApi != null) {
                    basicMessageChannel8.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewHostApi$$ExternalSyntheticLambda15
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewHostApi.CC.lambda$setup$7(GeneratedAndroidWebView.WebViewHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel8.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel9 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewHostApi.goBack", getCodec());
                if (webViewHostApi != null) {
                    basicMessageChannel9.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewHostApi$$ExternalSyntheticLambda16
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewHostApi.CC.lambda$setup$8(GeneratedAndroidWebView.WebViewHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel9.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel10 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewHostApi.goForward", getCodec());
                if (webViewHostApi != null) {
                    basicMessageChannel10.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewHostApi$$ExternalSyntheticLambda17
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewHostApi.CC.lambda$setup$9(GeneratedAndroidWebView.WebViewHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel10.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel11 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewHostApi.reload", getCodec());
                if (webViewHostApi != null) {
                    basicMessageChannel11.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewHostApi$$ExternalSyntheticLambda18
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewHostApi.CC.lambda$setup$10(GeneratedAndroidWebView.WebViewHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel11.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel12 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewHostApi.clearCache", getCodec());
                if (webViewHostApi != null) {
                    basicMessageChannel12.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewHostApi$$ExternalSyntheticLambda19
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewHostApi.CC.lambda$setup$11(GeneratedAndroidWebView.WebViewHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel12.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel13 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewHostApi.evaluateJavascript", getCodec());
                if (webViewHostApi != null) {
                    basicMessageChannel13.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewHostApi$$ExternalSyntheticLambda20
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewHostApi.CC.lambda$setup$12(GeneratedAndroidWebView.WebViewHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel13.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel14 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewHostApi.getTitle", getCodec());
                if (webViewHostApi != null) {
                    basicMessageChannel14.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewHostApi$$ExternalSyntheticLambda21
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewHostApi.CC.lambda$setup$13(GeneratedAndroidWebView.WebViewHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel14.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel15 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewHostApi.scrollTo", getCodec());
                if (webViewHostApi != null) {
                    basicMessageChannel15.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewHostApi$$ExternalSyntheticLambda22
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewHostApi.CC.lambda$setup$14(GeneratedAndroidWebView.WebViewHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel15.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel16 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewHostApi.scrollBy", getCodec());
                if (webViewHostApi != null) {
                    basicMessageChannel16.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewHostApi$$ExternalSyntheticLambda23
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewHostApi.CC.lambda$setup$15(GeneratedAndroidWebView.WebViewHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel16.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel17 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewHostApi.getScrollX", getCodec());
                if (webViewHostApi != null) {
                    basicMessageChannel17.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewHostApi$$ExternalSyntheticLambda24
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewHostApi.CC.lambda$setup$16(GeneratedAndroidWebView.WebViewHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel17.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel18 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewHostApi.getScrollY", getCodec());
                if (webViewHostApi != null) {
                    basicMessageChannel18.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewHostApi$$ExternalSyntheticLambda25
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewHostApi.CC.lambda$setup$17(GeneratedAndroidWebView.WebViewHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel18.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel19 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewHostApi.getScrollPosition", getCodec());
                if (webViewHostApi != null) {
                    basicMessageChannel19.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewHostApi$$ExternalSyntheticLambda1
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewHostApi.CC.lambda$setup$18(GeneratedAndroidWebView.WebViewHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel19.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel20 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewHostApi.setWebContentsDebuggingEnabled", getCodec());
                if (webViewHostApi != null) {
                    basicMessageChannel20.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewHostApi$$ExternalSyntheticLambda2
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewHostApi.CC.lambda$setup$19(GeneratedAndroidWebView.WebViewHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel20.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel21 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewHostApi.setWebViewClient", getCodec());
                if (webViewHostApi != null) {
                    basicMessageChannel21.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewHostApi$$ExternalSyntheticLambda4
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewHostApi.CC.lambda$setup$20(GeneratedAndroidWebView.WebViewHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel21.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel22 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewHostApi.addJavaScriptChannel", getCodec());
                if (webViewHostApi != null) {
                    basicMessageChannel22.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewHostApi$$ExternalSyntheticLambda5
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewHostApi.CC.lambda$setup$21(GeneratedAndroidWebView.WebViewHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel22.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel23 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewHostApi.removeJavaScriptChannel", getCodec());
                if (webViewHostApi != null) {
                    basicMessageChannel23.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewHostApi$$ExternalSyntheticLambda6
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewHostApi.CC.lambda$setup$22(GeneratedAndroidWebView.WebViewHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel23.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel24 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewHostApi.setDownloadListener", getCodec());
                if (webViewHostApi != null) {
                    basicMessageChannel24.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewHostApi$$ExternalSyntheticLambda7
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewHostApi.CC.lambda$setup$23(GeneratedAndroidWebView.WebViewHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel24.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel25 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewHostApi.setWebChromeClient", getCodec());
                if (webViewHostApi != null) {
                    basicMessageChannel25.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewHostApi$$ExternalSyntheticLambda8
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewHostApi.CC.lambda$setup$24(GeneratedAndroidWebView.WebViewHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel25.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel26 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewHostApi.setBackgroundColor", getCodec());
                if (webViewHostApi != null) {
                    basicMessageChannel26.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewHostApi$$ExternalSyntheticLambda9
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewHostApi.CC.lambda$setup$25(GeneratedAndroidWebView.WebViewHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel26.setMessageHandler(null);
                }
            }

            public static /* synthetic */ void lambda$setup$0(WebViewHostApi webViewHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                Boolean bool = (Boolean) arrayList.get(1);
                if (bool == null) {
                    throw new NullPointerException("useHybridCompositionArg unexpectedly null.");
                }
                webViewHostApi.create(number == null ? null : Long.valueOf(number.longValue()), bool);
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }

            public static /* synthetic */ void lambda$setup$1(WebViewHostApi webViewHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                String str = (String) arrayList.get(1);
                if (str == null) {
                    throw new NullPointerException("dataArg unexpectedly null.");
                }
                webViewHostApi.loadData(number == null ? null : Long.valueOf(number.longValue()), str, (String) arrayList.get(2), (String) arrayList.get(3));
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }

            public static /* synthetic */ void lambda$setup$2(WebViewHostApi webViewHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                String str = (String) arrayList.get(1);
                String str2 = (String) arrayList.get(2);
                if (str2 == null) {
                    throw new NullPointerException("dataArg unexpectedly null.");
                }
                webViewHostApi.loadDataWithBaseUrl(number == null ? null : Long.valueOf(number.longValue()), str, str2, (String) arrayList.get(3), (String) arrayList.get(4), (String) arrayList.get(5));
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }

            public static /* synthetic */ void lambda$setup$3(WebViewHostApi webViewHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                String str = (String) arrayList.get(1);
                if (str == null) {
                    throw new NullPointerException("urlArg unexpectedly null.");
                }
                Map<String, String> map = (Map) arrayList.get(2);
                if (map == null) {
                    throw new NullPointerException("headersArg unexpectedly null.");
                }
                webViewHostApi.loadUrl(number == null ? null : Long.valueOf(number.longValue()), str, map);
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }

            public static /* synthetic */ void lambda$setup$4(WebViewHostApi webViewHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                String str = (String) arrayList.get(1);
                if (str == null) {
                    throw new NullPointerException("urlArg unexpectedly null.");
                }
                byte[] bArr = (byte[]) arrayList.get(2);
                if (bArr == null) {
                    throw new NullPointerException("dataArg unexpectedly null.");
                }
                webViewHostApi.postUrl(number == null ? null : Long.valueOf(number.longValue()), str, bArr);
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }

            public static /* synthetic */ void lambda$setup$5(WebViewHostApi webViewHostApi, Object obj, BasicMessageChannel.Reply reply) {
                Number number;
                ArrayList arrayList = new ArrayList();
                try {
                    ArrayList arrayList2 = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList2 == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList2.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                arrayList.add(0, webViewHostApi.getUrl(number == null ? null : Long.valueOf(number.longValue())));
                reply.reply(arrayList);
            }

            public static /* synthetic */ void lambda$setup$6(WebViewHostApi webViewHostApi, Object obj, BasicMessageChannel.Reply reply) {
                Number number;
                ArrayList arrayList = new ArrayList();
                try {
                    ArrayList arrayList2 = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList2 == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList2.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                arrayList.add(0, webViewHostApi.canGoBack(number == null ? null : Long.valueOf(number.longValue())));
                reply.reply(arrayList);
            }

            public static /* synthetic */ void lambda$setup$7(WebViewHostApi webViewHostApi, Object obj, BasicMessageChannel.Reply reply) {
                Number number;
                ArrayList arrayList = new ArrayList();
                try {
                    ArrayList arrayList2 = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList2 == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList2.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                arrayList.add(0, webViewHostApi.canGoForward(number == null ? null : Long.valueOf(number.longValue())));
                reply.reply(arrayList);
            }

            public static /* synthetic */ void lambda$setup$8(WebViewHostApi webViewHostApi, Object obj, BasicMessageChannel.Reply reply) {
                Number number;
                ArrayList arrayList = new ArrayList();
                try {
                    ArrayList arrayList2 = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList2 == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList2.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                webViewHostApi.goBack(number == null ? null : Long.valueOf(number.longValue()));
                arrayList.add(0, null);
                reply.reply(arrayList);
            }

            public static /* synthetic */ void lambda$setup$9(WebViewHostApi webViewHostApi, Object obj, BasicMessageChannel.Reply reply) {
                Number number;
                ArrayList arrayList = new ArrayList();
                try {
                    ArrayList arrayList2 = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList2 == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList2.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                webViewHostApi.goForward(number == null ? null : Long.valueOf(number.longValue()));
                arrayList.add(0, null);
                reply.reply(arrayList);
            }

            public static /* synthetic */ void lambda$setup$10(WebViewHostApi webViewHostApi, Object obj, BasicMessageChannel.Reply reply) {
                Number number;
                ArrayList arrayList = new ArrayList();
                try {
                    ArrayList arrayList2 = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList2 == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList2.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                webViewHostApi.reload(number == null ? null : Long.valueOf(number.longValue()));
                arrayList.add(0, null);
                reply.reply(arrayList);
            }

            public static /* synthetic */ void lambda$setup$11(WebViewHostApi webViewHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                Boolean bool = (Boolean) arrayList.get(1);
                if (bool == null) {
                    throw new NullPointerException("includeDiskFilesArg unexpectedly null.");
                }
                webViewHostApi.clearCache(number == null ? null : Long.valueOf(number.longValue()), bool);
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }

            public static /* synthetic */ void lambda$setup$12(WebViewHostApi webViewHostApi, Object obj, final BasicMessageChannel.Reply reply) {
                final ArrayList arrayList = new ArrayList();
                try {
                    ArrayList arrayList2 = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList2 == null) {
                        throw new AssertionError();
                    }
                    Number number = (Number) arrayList2.get(0);
                    if (number == null) {
                        throw new NullPointerException("instanceIdArg unexpectedly null.");
                    }
                    String str = (String) arrayList2.get(1);
                    if (str == null) {
                        throw new NullPointerException("javascriptStringArg unexpectedly null.");
                    }
                    webViewHostApi.evaluateJavascript(number == null ? null : Long.valueOf(number.longValue()), str, new Result<String>() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.WebViewHostApi.1
                        @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.Result
                        public void success(String str2) {
                            arrayList.add(0, str2);
                            reply.reply(arrayList);
                        }

                        @Override // io.flutter.plugins.webviewflutter.GeneratedAndroidWebView.Result
                        public void error(Throwable th) {
                            reply.reply(GeneratedAndroidWebView.wrapError(th));
                        }
                    });
                } catch (Error | RuntimeException e) {
                    reply.reply(GeneratedAndroidWebView.wrapError(e));
                }
            }

            public static /* synthetic */ void lambda$setup$13(WebViewHostApi webViewHostApi, Object obj, BasicMessageChannel.Reply reply) {
                Number number;
                ArrayList arrayList = new ArrayList();
                try {
                    ArrayList arrayList2 = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList2 == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList2.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                arrayList.add(0, webViewHostApi.getTitle(number == null ? null : Long.valueOf(number.longValue())));
                reply.reply(arrayList);
            }

            public static /* synthetic */ void lambda$setup$14(WebViewHostApi webViewHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                Number number2 = (Number) arrayList.get(1);
                if (number2 == null) {
                    throw new NullPointerException("xArg unexpectedly null.");
                }
                Number number3 = (Number) arrayList.get(2);
                if (number3 == null) {
                    throw new NullPointerException("yArg unexpectedly null.");
                }
                webViewHostApi.scrollTo(number == null ? null : Long.valueOf(number.longValue()), number2 == null ? null : Long.valueOf(number2.longValue()), number3 == null ? null : Long.valueOf(number3.longValue()));
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }

            public static /* synthetic */ void lambda$setup$15(WebViewHostApi webViewHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                Number number2 = (Number) arrayList.get(1);
                if (number2 == null) {
                    throw new NullPointerException("xArg unexpectedly null.");
                }
                Number number3 = (Number) arrayList.get(2);
                if (number3 == null) {
                    throw new NullPointerException("yArg unexpectedly null.");
                }
                webViewHostApi.scrollBy(number == null ? null : Long.valueOf(number.longValue()), number2 == null ? null : Long.valueOf(number2.longValue()), number3 == null ? null : Long.valueOf(number3.longValue()));
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }

            public static /* synthetic */ void lambda$setup$16(WebViewHostApi webViewHostApi, Object obj, BasicMessageChannel.Reply reply) {
                Number number;
                ArrayList arrayList = new ArrayList();
                try {
                    ArrayList arrayList2 = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList2 == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList2.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                arrayList.add(0, webViewHostApi.getScrollX(number == null ? null : Long.valueOf(number.longValue())));
                reply.reply(arrayList);
            }

            public static /* synthetic */ void lambda$setup$17(WebViewHostApi webViewHostApi, Object obj, BasicMessageChannel.Reply reply) {
                Number number;
                ArrayList arrayList = new ArrayList();
                try {
                    ArrayList arrayList2 = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList2 == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList2.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                arrayList.add(0, webViewHostApi.getScrollY(number == null ? null : Long.valueOf(number.longValue())));
                reply.reply(arrayList);
            }

            public static /* synthetic */ void lambda$setup$18(WebViewHostApi webViewHostApi, Object obj, BasicMessageChannel.Reply reply) {
                Number number;
                ArrayList arrayList = new ArrayList();
                try {
                    ArrayList arrayList2 = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList2 == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList2.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                arrayList.add(0, webViewHostApi.getScrollPosition(number == null ? null : Long.valueOf(number.longValue())));
                reply.reply(arrayList);
            }

            public static /* synthetic */ void lambda$setup$19(WebViewHostApi webViewHostApi, Object obj, BasicMessageChannel.Reply reply) {
                Boolean bool;
                ArrayList arrayList = new ArrayList();
                try {
                    ArrayList arrayList2 = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList2 == null) {
                        throw new AssertionError();
                    }
                    bool = (Boolean) arrayList2.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList = GeneratedAndroidWebView.wrapError(e);
                }
                if (bool == null) {
                    throw new NullPointerException("enabledArg unexpectedly null.");
                }
                webViewHostApi.setWebContentsDebuggingEnabled(bool);
                arrayList.add(0, null);
                reply.reply(arrayList);
            }

            public static /* synthetic */ void lambda$setup$20(WebViewHostApi webViewHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                Number number2 = (Number) arrayList.get(1);
                if (number2 == null) {
                    throw new NullPointerException("webViewClientInstanceIdArg unexpectedly null.");
                }
                webViewHostApi.setWebViewClient(number == null ? null : Long.valueOf(number.longValue()), number2 == null ? null : Long.valueOf(number2.longValue()));
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }

            public static /* synthetic */ void lambda$setup$21(WebViewHostApi webViewHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                Number number2 = (Number) arrayList.get(1);
                if (number2 == null) {
                    throw new NullPointerException("javaScriptChannelInstanceIdArg unexpectedly null.");
                }
                webViewHostApi.addJavaScriptChannel(number == null ? null : Long.valueOf(number.longValue()), number2 == null ? null : Long.valueOf(number2.longValue()));
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }

            public static /* synthetic */ void lambda$setup$22(WebViewHostApi webViewHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                Number number2 = (Number) arrayList.get(1);
                if (number2 == null) {
                    throw new NullPointerException("javaScriptChannelInstanceIdArg unexpectedly null.");
                }
                webViewHostApi.removeJavaScriptChannel(number == null ? null : Long.valueOf(number.longValue()), number2 == null ? null : Long.valueOf(number2.longValue()));
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }

            public static /* synthetic */ void lambda$setup$23(WebViewHostApi webViewHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                Number number2 = (Number) arrayList.get(1);
                webViewHostApi.setDownloadListener(number == null ? null : Long.valueOf(number.longValue()), number2 == null ? null : Long.valueOf(number2.longValue()));
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }

            public static /* synthetic */ void lambda$setup$24(WebViewHostApi webViewHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                Number number2 = (Number) arrayList.get(1);
                webViewHostApi.setWebChromeClient(number == null ? null : Long.valueOf(number.longValue()), number2 == null ? null : Long.valueOf(number2.longValue()));
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }

            public static /* synthetic */ void lambda$setup$25(WebViewHostApi webViewHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                Number number2 = (Number) arrayList.get(1);
                if (number2 == null) {
                    throw new NullPointerException("colorArg unexpectedly null.");
                }
                webViewHostApi.setBackgroundColor(number == null ? null : Long.valueOf(number.longValue()), number2 == null ? null : Long.valueOf(number2.longValue()));
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class WebViewFlutterApi {
        private final BinaryMessenger binaryMessenger;

        /* loaded from: classes2.dex */
        public interface Reply<T> {
            void reply(T t);
        }

        public WebViewFlutterApi(BinaryMessenger binaryMessenger) {
            this.binaryMessenger = binaryMessenger;
        }

        static MessageCodec<Object> getCodec() {
            return new StandardMessageCodec();
        }

        public void create(Long l, final Reply<Void> reply) {
            new BasicMessageChannel(this.binaryMessenger, "dev.flutter.pigeon.WebViewFlutterApi.create", getCodec()).send(new ArrayList(Collections.singletonList(l)), new BasicMessageChannel.Reply() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewFlutterApi$$ExternalSyntheticLambda0
                @Override // io.flutter.plugin.common.BasicMessageChannel.Reply
                public final void reply(Object obj) {
                    GeneratedAndroidWebView.WebViewFlutterApi.Reply.this.reply(null);
                }
            });
        }
    }

    /* loaded from: classes2.dex */
    public interface WebSettingsHostApi {
        void create(Long l, Long l2);

        void setAllowFileAccess(Long l, Boolean bool);

        void setBuiltInZoomControls(Long l, Boolean bool);

        void setDisplayZoomControls(Long l, Boolean bool);

        void setDomStorageEnabled(Long l, Boolean bool);

        void setJavaScriptCanOpenWindowsAutomatically(Long l, Boolean bool);

        void setJavaScriptEnabled(Long l, Boolean bool);

        void setLoadWithOverviewMode(Long l, Boolean bool);

        void setMediaPlaybackRequiresUserGesture(Long l, Boolean bool);

        void setSupportMultipleWindows(Long l, Boolean bool);

        void setSupportZoom(Long l, Boolean bool);

        void setTextZoom(Long l, Long l2);

        void setUseWideViewPort(Long l, Boolean bool);

        void setUserAgentString(Long l, String str);

        /* renamed from: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebSettingsHostApi$-CC, reason: invalid class name */
        /* loaded from: classes2.dex */
        public final /* synthetic */ class CC {
            public static MessageCodec<Object> getCodec() {
                return new StandardMessageCodec();
            }

            public static void setup(BinaryMessenger binaryMessenger, final WebSettingsHostApi webSettingsHostApi) {
                BasicMessageChannel basicMessageChannel = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebSettingsHostApi.create", getCodec());
                if (webSettingsHostApi != null) {
                    basicMessageChannel.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebSettingsHostApi$$ExternalSyntheticLambda0
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebSettingsHostApi.CC.lambda$setup$0(GeneratedAndroidWebView.WebSettingsHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel2 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebSettingsHostApi.setDomStorageEnabled", getCodec());
                if (webSettingsHostApi != null) {
                    basicMessageChannel2.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebSettingsHostApi$$ExternalSyntheticLambda5
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebSettingsHostApi.CC.lambda$setup$1(GeneratedAndroidWebView.WebSettingsHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel2.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel3 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebSettingsHostApi.setJavaScriptCanOpenWindowsAutomatically", getCodec());
                if (webSettingsHostApi != null) {
                    basicMessageChannel3.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebSettingsHostApi$$ExternalSyntheticLambda10
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebSettingsHostApi.CC.lambda$setup$2(GeneratedAndroidWebView.WebSettingsHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel3.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel4 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebSettingsHostApi.setSupportMultipleWindows", getCodec());
                if (webSettingsHostApi != null) {
                    basicMessageChannel4.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebSettingsHostApi$$ExternalSyntheticLambda11
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebSettingsHostApi.CC.lambda$setup$3(GeneratedAndroidWebView.WebSettingsHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel4.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel5 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebSettingsHostApi.setJavaScriptEnabled", getCodec());
                if (webSettingsHostApi != null) {
                    basicMessageChannel5.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebSettingsHostApi$$ExternalSyntheticLambda12
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebSettingsHostApi.CC.lambda$setup$4(GeneratedAndroidWebView.WebSettingsHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel5.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel6 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebSettingsHostApi.setUserAgentString", getCodec());
                if (webSettingsHostApi != null) {
                    basicMessageChannel6.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebSettingsHostApi$$ExternalSyntheticLambda13
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebSettingsHostApi.CC.lambda$setup$5(GeneratedAndroidWebView.WebSettingsHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel6.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel7 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebSettingsHostApi.setMediaPlaybackRequiresUserGesture", getCodec());
                if (webSettingsHostApi != null) {
                    basicMessageChannel7.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebSettingsHostApi$$ExternalSyntheticLambda1
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebSettingsHostApi.CC.lambda$setup$6(GeneratedAndroidWebView.WebSettingsHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel7.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel8 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebSettingsHostApi.setSupportZoom", getCodec());
                if (webSettingsHostApi != null) {
                    basicMessageChannel8.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebSettingsHostApi$$ExternalSyntheticLambda2
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebSettingsHostApi.CC.lambda$setup$7(GeneratedAndroidWebView.WebSettingsHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel8.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel9 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebSettingsHostApi.setLoadWithOverviewMode", getCodec());
                if (webSettingsHostApi != null) {
                    basicMessageChannel9.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebSettingsHostApi$$ExternalSyntheticLambda3
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebSettingsHostApi.CC.lambda$setup$8(GeneratedAndroidWebView.WebSettingsHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel9.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel10 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebSettingsHostApi.setUseWideViewPort", getCodec());
                if (webSettingsHostApi != null) {
                    basicMessageChannel10.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebSettingsHostApi$$ExternalSyntheticLambda4
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebSettingsHostApi.CC.lambda$setup$9(GeneratedAndroidWebView.WebSettingsHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel10.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel11 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebSettingsHostApi.setDisplayZoomControls", getCodec());
                if (webSettingsHostApi != null) {
                    basicMessageChannel11.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebSettingsHostApi$$ExternalSyntheticLambda6
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebSettingsHostApi.CC.lambda$setup$10(GeneratedAndroidWebView.WebSettingsHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel11.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel12 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebSettingsHostApi.setBuiltInZoomControls", getCodec());
                if (webSettingsHostApi != null) {
                    basicMessageChannel12.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebSettingsHostApi$$ExternalSyntheticLambda7
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebSettingsHostApi.CC.lambda$setup$11(GeneratedAndroidWebView.WebSettingsHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel12.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel13 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebSettingsHostApi.setAllowFileAccess", getCodec());
                if (webSettingsHostApi != null) {
                    basicMessageChannel13.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebSettingsHostApi$$ExternalSyntheticLambda8
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebSettingsHostApi.CC.lambda$setup$12(GeneratedAndroidWebView.WebSettingsHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel13.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel14 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebSettingsHostApi.setTextZoom", getCodec());
                if (webSettingsHostApi != null) {
                    basicMessageChannel14.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebSettingsHostApi$$ExternalSyntheticLambda9
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebSettingsHostApi.CC.lambda$setup$13(GeneratedAndroidWebView.WebSettingsHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel14.setMessageHandler(null);
                }
            }

            public static /* synthetic */ void lambda$setup$0(WebSettingsHostApi webSettingsHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                Number number2 = (Number) arrayList.get(1);
                if (number2 == null) {
                    throw new NullPointerException("webViewInstanceIdArg unexpectedly null.");
                }
                webSettingsHostApi.create(number == null ? null : Long.valueOf(number.longValue()), number2 == null ? null : Long.valueOf(number2.longValue()));
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }

            public static /* synthetic */ void lambda$setup$1(WebSettingsHostApi webSettingsHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                Boolean bool = (Boolean) arrayList.get(1);
                if (bool == null) {
                    throw new NullPointerException("flagArg unexpectedly null.");
                }
                webSettingsHostApi.setDomStorageEnabled(number == null ? null : Long.valueOf(number.longValue()), bool);
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }

            public static /* synthetic */ void lambda$setup$2(WebSettingsHostApi webSettingsHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                Boolean bool = (Boolean) arrayList.get(1);
                if (bool == null) {
                    throw new NullPointerException("flagArg unexpectedly null.");
                }
                webSettingsHostApi.setJavaScriptCanOpenWindowsAutomatically(number == null ? null : Long.valueOf(number.longValue()), bool);
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }

            public static /* synthetic */ void lambda$setup$3(WebSettingsHostApi webSettingsHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                Boolean bool = (Boolean) arrayList.get(1);
                if (bool == null) {
                    throw new NullPointerException("supportArg unexpectedly null.");
                }
                webSettingsHostApi.setSupportMultipleWindows(number == null ? null : Long.valueOf(number.longValue()), bool);
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }

            public static /* synthetic */ void lambda$setup$4(WebSettingsHostApi webSettingsHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                Boolean bool = (Boolean) arrayList.get(1);
                if (bool == null) {
                    throw new NullPointerException("flagArg unexpectedly null.");
                }
                webSettingsHostApi.setJavaScriptEnabled(number == null ? null : Long.valueOf(number.longValue()), bool);
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }

            public static /* synthetic */ void lambda$setup$5(WebSettingsHostApi webSettingsHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                webSettingsHostApi.setUserAgentString(number == null ? null : Long.valueOf(number.longValue()), (String) arrayList.get(1));
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }

            public static /* synthetic */ void lambda$setup$6(WebSettingsHostApi webSettingsHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                Boolean bool = (Boolean) arrayList.get(1);
                if (bool == null) {
                    throw new NullPointerException("requireArg unexpectedly null.");
                }
                webSettingsHostApi.setMediaPlaybackRequiresUserGesture(number == null ? null : Long.valueOf(number.longValue()), bool);
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }

            public static /* synthetic */ void lambda$setup$7(WebSettingsHostApi webSettingsHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                Boolean bool = (Boolean) arrayList.get(1);
                if (bool == null) {
                    throw new NullPointerException("supportArg unexpectedly null.");
                }
                webSettingsHostApi.setSupportZoom(number == null ? null : Long.valueOf(number.longValue()), bool);
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }

            public static /* synthetic */ void lambda$setup$8(WebSettingsHostApi webSettingsHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                Boolean bool = (Boolean) arrayList.get(1);
                if (bool == null) {
                    throw new NullPointerException("overviewArg unexpectedly null.");
                }
                webSettingsHostApi.setLoadWithOverviewMode(number == null ? null : Long.valueOf(number.longValue()), bool);
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }

            public static /* synthetic */ void lambda$setup$9(WebSettingsHostApi webSettingsHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                Boolean bool = (Boolean) arrayList.get(1);
                if (bool == null) {
                    throw new NullPointerException("useArg unexpectedly null.");
                }
                webSettingsHostApi.setUseWideViewPort(number == null ? null : Long.valueOf(number.longValue()), bool);
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }

            public static /* synthetic */ void lambda$setup$10(WebSettingsHostApi webSettingsHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                Boolean bool = (Boolean) arrayList.get(1);
                if (bool == null) {
                    throw new NullPointerException("enabledArg unexpectedly null.");
                }
                webSettingsHostApi.setDisplayZoomControls(number == null ? null : Long.valueOf(number.longValue()), bool);
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }

            public static /* synthetic */ void lambda$setup$11(WebSettingsHostApi webSettingsHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                Boolean bool = (Boolean) arrayList.get(1);
                if (bool == null) {
                    throw new NullPointerException("enabledArg unexpectedly null.");
                }
                webSettingsHostApi.setBuiltInZoomControls(number == null ? null : Long.valueOf(number.longValue()), bool);
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }

            public static /* synthetic */ void lambda$setup$12(WebSettingsHostApi webSettingsHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                Boolean bool = (Boolean) arrayList.get(1);
                if (bool == null) {
                    throw new NullPointerException("enabledArg unexpectedly null.");
                }
                webSettingsHostApi.setAllowFileAccess(number == null ? null : Long.valueOf(number.longValue()), bool);
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }

            public static /* synthetic */ void lambda$setup$13(WebSettingsHostApi webSettingsHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                Number number2 = (Number) arrayList.get(1);
                if (number2 == null) {
                    throw new NullPointerException("textZoomArg unexpectedly null.");
                }
                webSettingsHostApi.setTextZoom(number == null ? null : Long.valueOf(number.longValue()), number2 == null ? null : Long.valueOf(number2.longValue()));
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }
        }
    }

    /* loaded from: classes2.dex */
    public interface JavaScriptChannelHostApi {
        void create(Long l, String str);

        /* renamed from: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$JavaScriptChannelHostApi$-CC, reason: invalid class name */
        /* loaded from: classes2.dex */
        public final /* synthetic */ class CC {
            public static MessageCodec<Object> getCodec() {
                return new StandardMessageCodec();
            }

            public static void setup(BinaryMessenger binaryMessenger, final JavaScriptChannelHostApi javaScriptChannelHostApi) {
                BasicMessageChannel basicMessageChannel = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.JavaScriptChannelHostApi.create", getCodec());
                if (javaScriptChannelHostApi != null) {
                    basicMessageChannel.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$JavaScriptChannelHostApi$$ExternalSyntheticLambda0
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.JavaScriptChannelHostApi.CC.lambda$setup$0(GeneratedAndroidWebView.JavaScriptChannelHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel.setMessageHandler(null);
                }
            }

            public static /* synthetic */ void lambda$setup$0(JavaScriptChannelHostApi javaScriptChannelHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                String str = (String) arrayList.get(1);
                if (str == null) {
                    throw new NullPointerException("channelNameArg unexpectedly null.");
                }
                javaScriptChannelHostApi.create(number == null ? null : Long.valueOf(number.longValue()), str);
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class JavaScriptChannelFlutterApi {
        private final BinaryMessenger binaryMessenger;

        /* loaded from: classes2.dex */
        public interface Reply<T> {
            void reply(T t);
        }

        public JavaScriptChannelFlutterApi(BinaryMessenger binaryMessenger) {
            this.binaryMessenger = binaryMessenger;
        }

        static MessageCodec<Object> getCodec() {
            return new StandardMessageCodec();
        }

        public void postMessage(Long l, String str, final Reply<Void> reply) {
            new BasicMessageChannel(this.binaryMessenger, "dev.flutter.pigeon.JavaScriptChannelFlutterApi.postMessage", getCodec()).send(new ArrayList(Arrays.asList(l, str)), new BasicMessageChannel.Reply() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$JavaScriptChannelFlutterApi$$ExternalSyntheticLambda0
                @Override // io.flutter.plugin.common.BasicMessageChannel.Reply
                public final void reply(Object obj) {
                    GeneratedAndroidWebView.JavaScriptChannelFlutterApi.Reply.this.reply(null);
                }
            });
        }
    }

    /* loaded from: classes2.dex */
    public interface WebViewClientHostApi {
        void create(Long l);

        void setSynchronousReturnValueForShouldOverrideUrlLoading(Long l, Boolean bool);

        /* renamed from: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewClientHostApi$-CC, reason: invalid class name */
        /* loaded from: classes2.dex */
        public final /* synthetic */ class CC {
            public static MessageCodec<Object> getCodec() {
                return new StandardMessageCodec();
            }

            public static void setup(BinaryMessenger binaryMessenger, final WebViewClientHostApi webViewClientHostApi) {
                BasicMessageChannel basicMessageChannel = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewClientHostApi.create", getCodec());
                if (webViewClientHostApi != null) {
                    basicMessageChannel.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewClientHostApi$$ExternalSyntheticLambda0
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewClientHostApi.CC.lambda$setup$0(GeneratedAndroidWebView.WebViewClientHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel2 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebViewClientHostApi.setSynchronousReturnValueForShouldOverrideUrlLoading", getCodec());
                if (webViewClientHostApi != null) {
                    basicMessageChannel2.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewClientHostApi$$ExternalSyntheticLambda1
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebViewClientHostApi.CC.lambda$setup$1(GeneratedAndroidWebView.WebViewClientHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel2.setMessageHandler(null);
                }
            }

            public static /* synthetic */ void lambda$setup$0(WebViewClientHostApi webViewClientHostApi, Object obj, BasicMessageChannel.Reply reply) {
                Number number;
                ArrayList arrayList = new ArrayList();
                try {
                    ArrayList arrayList2 = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList2 == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList2.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                webViewClientHostApi.create(number == null ? null : Long.valueOf(number.longValue()));
                arrayList.add(0, null);
                reply.reply(arrayList);
            }

            public static /* synthetic */ void lambda$setup$1(WebViewClientHostApi webViewClientHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                Boolean bool = (Boolean) arrayList.get(1);
                if (bool == null) {
                    throw new NullPointerException("valueArg unexpectedly null.");
                }
                webViewClientHostApi.setSynchronousReturnValueForShouldOverrideUrlLoading(number == null ? null : Long.valueOf(number.longValue()), bool);
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class WebViewClientFlutterApiCodec extends StandardMessageCodec {
        public static final WebViewClientFlutterApiCodec INSTANCE = new WebViewClientFlutterApiCodec();

        private WebViewClientFlutterApiCodec() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.flutter.plugin.common.StandardMessageCodec
        public Object readValueOfType(byte b, ByteBuffer byteBuffer) {
            if (b == Byte.MIN_VALUE) {
                return WebResourceErrorData.fromList((ArrayList) readValue(byteBuffer));
            }
            if (b == -127) {
                return WebResourceRequestData.fromList((ArrayList) readValue(byteBuffer));
            }
            return super.readValueOfType(b, byteBuffer);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.flutter.plugin.common.StandardMessageCodec
        public void writeValue(ByteArrayOutputStream byteArrayOutputStream, Object obj) {
            if (obj instanceof WebResourceErrorData) {
                byteArrayOutputStream.write(128);
                writeValue(byteArrayOutputStream, ((WebResourceErrorData) obj).toList());
            } else if (obj instanceof WebResourceRequestData) {
                byteArrayOutputStream.write(129);
                writeValue(byteArrayOutputStream, ((WebResourceRequestData) obj).toList());
            } else {
                super.writeValue(byteArrayOutputStream, obj);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class WebViewClientFlutterApi {
        private final BinaryMessenger binaryMessenger;

        /* loaded from: classes2.dex */
        public interface Reply<T> {
            void reply(T t);
        }

        public WebViewClientFlutterApi(BinaryMessenger binaryMessenger) {
            this.binaryMessenger = binaryMessenger;
        }

        static MessageCodec<Object> getCodec() {
            return WebViewClientFlutterApiCodec.INSTANCE;
        }

        public void onPageStarted(Long l, Long l2, String str, final Reply<Void> reply) {
            new BasicMessageChannel(this.binaryMessenger, "dev.flutter.pigeon.WebViewClientFlutterApi.onPageStarted", getCodec()).send(new ArrayList(Arrays.asList(l, l2, str)), new BasicMessageChannel.Reply() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewClientFlutterApi$$ExternalSyntheticLambda1
                @Override // io.flutter.plugin.common.BasicMessageChannel.Reply
                public final void reply(Object obj) {
                    GeneratedAndroidWebView.WebViewClientFlutterApi.Reply.this.reply(null);
                }
            });
        }

        public void onPageFinished(Long l, Long l2, String str, final Reply<Void> reply) {
            new BasicMessageChannel(this.binaryMessenger, "dev.flutter.pigeon.WebViewClientFlutterApi.onPageFinished", getCodec()).send(new ArrayList(Arrays.asList(l, l2, str)), new BasicMessageChannel.Reply() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewClientFlutterApi$$ExternalSyntheticLambda0
                @Override // io.flutter.plugin.common.BasicMessageChannel.Reply
                public final void reply(Object obj) {
                    GeneratedAndroidWebView.WebViewClientFlutterApi.Reply.this.reply(null);
                }
            });
        }

        public void onReceivedRequestError(Long l, Long l2, WebResourceRequestData webResourceRequestData, WebResourceErrorData webResourceErrorData, final Reply<Void> reply) {
            new BasicMessageChannel(this.binaryMessenger, "dev.flutter.pigeon.WebViewClientFlutterApi.onReceivedRequestError", getCodec()).send(new ArrayList(Arrays.asList(l, l2, webResourceRequestData, webResourceErrorData)), new BasicMessageChannel.Reply() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewClientFlutterApi$$ExternalSyntheticLambda3
                @Override // io.flutter.plugin.common.BasicMessageChannel.Reply
                public final void reply(Object obj) {
                    GeneratedAndroidWebView.WebViewClientFlutterApi.Reply.this.reply(null);
                }
            });
        }

        public void onReceivedError(Long l, Long l2, Long l3, String str, String str2, final Reply<Void> reply) {
            new BasicMessageChannel(this.binaryMessenger, "dev.flutter.pigeon.WebViewClientFlutterApi.onReceivedError", getCodec()).send(new ArrayList(Arrays.asList(l, l2, l3, str, str2)), new BasicMessageChannel.Reply() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewClientFlutterApi$$ExternalSyntheticLambda2
                @Override // io.flutter.plugin.common.BasicMessageChannel.Reply
                public final void reply(Object obj) {
                    GeneratedAndroidWebView.WebViewClientFlutterApi.Reply.this.reply(null);
                }
            });
        }

        public void requestLoading(Long l, Long l2, WebResourceRequestData webResourceRequestData, final Reply<Void> reply) {
            new BasicMessageChannel(this.binaryMessenger, "dev.flutter.pigeon.WebViewClientFlutterApi.requestLoading", getCodec()).send(new ArrayList(Arrays.asList(l, l2, webResourceRequestData)), new BasicMessageChannel.Reply() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewClientFlutterApi$$ExternalSyntheticLambda4
                @Override // io.flutter.plugin.common.BasicMessageChannel.Reply
                public final void reply(Object obj) {
                    GeneratedAndroidWebView.WebViewClientFlutterApi.Reply.this.reply(null);
                }
            });
        }

        public void urlLoading(Long l, Long l2, String str, final Reply<Void> reply) {
            new BasicMessageChannel(this.binaryMessenger, "dev.flutter.pigeon.WebViewClientFlutterApi.urlLoading", getCodec()).send(new ArrayList(Arrays.asList(l, l2, str)), new BasicMessageChannel.Reply() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebViewClientFlutterApi$$ExternalSyntheticLambda5
                @Override // io.flutter.plugin.common.BasicMessageChannel.Reply
                public final void reply(Object obj) {
                    GeneratedAndroidWebView.WebViewClientFlutterApi.Reply.this.reply(null);
                }
            });
        }
    }

    /* loaded from: classes2.dex */
    public interface DownloadListenerHostApi {
        void create(Long l);

        /* renamed from: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$DownloadListenerHostApi$-CC, reason: invalid class name */
        /* loaded from: classes2.dex */
        public final /* synthetic */ class CC {
            public static MessageCodec<Object> getCodec() {
                return new StandardMessageCodec();
            }

            public static void setup(BinaryMessenger binaryMessenger, final DownloadListenerHostApi downloadListenerHostApi) {
                BasicMessageChannel basicMessageChannel = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.DownloadListenerHostApi.create", getCodec());
                if (downloadListenerHostApi != null) {
                    basicMessageChannel.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$DownloadListenerHostApi$$ExternalSyntheticLambda0
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.DownloadListenerHostApi.CC.lambda$setup$0(GeneratedAndroidWebView.DownloadListenerHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel.setMessageHandler(null);
                }
            }

            public static /* synthetic */ void lambda$setup$0(DownloadListenerHostApi downloadListenerHostApi, Object obj, BasicMessageChannel.Reply reply) {
                Number number;
                ArrayList arrayList = new ArrayList();
                try {
                    ArrayList arrayList2 = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList2 == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList2.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                downloadListenerHostApi.create(number == null ? null : Long.valueOf(number.longValue()));
                arrayList.add(0, null);
                reply.reply(arrayList);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class DownloadListenerFlutterApi {
        private final BinaryMessenger binaryMessenger;

        /* loaded from: classes2.dex */
        public interface Reply<T> {
            void reply(T t);
        }

        public DownloadListenerFlutterApi(BinaryMessenger binaryMessenger) {
            this.binaryMessenger = binaryMessenger;
        }

        static MessageCodec<Object> getCodec() {
            return new StandardMessageCodec();
        }

        public void onDownloadStart(Long l, String str, String str2, String str3, String str4, Long l2, final Reply<Void> reply) {
            new BasicMessageChannel(this.binaryMessenger, "dev.flutter.pigeon.DownloadListenerFlutterApi.onDownloadStart", getCodec()).send(new ArrayList(Arrays.asList(l, str, str2, str3, str4, l2)), new BasicMessageChannel.Reply() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$DownloadListenerFlutterApi$$ExternalSyntheticLambda0
                @Override // io.flutter.plugin.common.BasicMessageChannel.Reply
                public final void reply(Object obj) {
                    GeneratedAndroidWebView.DownloadListenerFlutterApi.Reply.this.reply(null);
                }
            });
        }
    }

    /* loaded from: classes2.dex */
    public interface WebChromeClientHostApi {
        void create(Long l);

        void setSynchronousReturnValueForOnShowFileChooser(Long l, Boolean bool);

        /* renamed from: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebChromeClientHostApi$-CC, reason: invalid class name */
        /* loaded from: classes2.dex */
        public final /* synthetic */ class CC {
            public static MessageCodec<Object> getCodec() {
                return new StandardMessageCodec();
            }

            public static void setup(BinaryMessenger binaryMessenger, final WebChromeClientHostApi webChromeClientHostApi) {
                BasicMessageChannel basicMessageChannel = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebChromeClientHostApi.create", getCodec());
                if (webChromeClientHostApi != null) {
                    basicMessageChannel.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebChromeClientHostApi$$ExternalSyntheticLambda0
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebChromeClientHostApi.CC.lambda$setup$0(GeneratedAndroidWebView.WebChromeClientHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel2 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebChromeClientHostApi.setSynchronousReturnValueForOnShowFileChooser", getCodec());
                if (webChromeClientHostApi != null) {
                    basicMessageChannel2.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebChromeClientHostApi$$ExternalSyntheticLambda1
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebChromeClientHostApi.CC.lambda$setup$1(GeneratedAndroidWebView.WebChromeClientHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel2.setMessageHandler(null);
                }
            }

            public static /* synthetic */ void lambda$setup$0(WebChromeClientHostApi webChromeClientHostApi, Object obj, BasicMessageChannel.Reply reply) {
                Number number;
                ArrayList arrayList = new ArrayList();
                try {
                    ArrayList arrayList2 = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList2 == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList2.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                webChromeClientHostApi.create(number == null ? null : Long.valueOf(number.longValue()));
                arrayList.add(0, null);
                reply.reply(arrayList);
            }

            public static /* synthetic */ void lambda$setup$1(WebChromeClientHostApi webChromeClientHostApi, Object obj, BasicMessageChannel.Reply reply) {
                ArrayList arrayList;
                Number number;
                ArrayList arrayList2 = new ArrayList();
                try {
                    arrayList = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList2 = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                Boolean bool = (Boolean) arrayList.get(1);
                if (bool == null) {
                    throw new NullPointerException("valueArg unexpectedly null.");
                }
                webChromeClientHostApi.setSynchronousReturnValueForOnShowFileChooser(number == null ? null : Long.valueOf(number.longValue()), bool);
                arrayList2.add(0, null);
                reply.reply(arrayList2);
            }
        }
    }

    /* loaded from: classes2.dex */
    public interface FlutterAssetManagerHostApi {
        String getAssetFilePathByName(String str);

        List<String> list(String str);

        /* renamed from: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$FlutterAssetManagerHostApi$-CC, reason: invalid class name */
        /* loaded from: classes2.dex */
        public final /* synthetic */ class CC {
            public static MessageCodec<Object> getCodec() {
                return new StandardMessageCodec();
            }

            public static void setup(BinaryMessenger binaryMessenger, final FlutterAssetManagerHostApi flutterAssetManagerHostApi) {
                BasicMessageChannel basicMessageChannel = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.FlutterAssetManagerHostApi.list", getCodec());
                if (flutterAssetManagerHostApi != null) {
                    basicMessageChannel.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$FlutterAssetManagerHostApi$$ExternalSyntheticLambda0
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.FlutterAssetManagerHostApi.CC.lambda$setup$0(GeneratedAndroidWebView.FlutterAssetManagerHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel2 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.FlutterAssetManagerHostApi.getAssetFilePathByName", getCodec());
                if (flutterAssetManagerHostApi != null) {
                    basicMessageChannel2.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$FlutterAssetManagerHostApi$$ExternalSyntheticLambda1
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.FlutterAssetManagerHostApi.CC.lambda$setup$1(GeneratedAndroidWebView.FlutterAssetManagerHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel2.setMessageHandler(null);
                }
            }

            public static /* synthetic */ void lambda$setup$0(FlutterAssetManagerHostApi flutterAssetManagerHostApi, Object obj, BasicMessageChannel.Reply reply) {
                String str;
                ArrayList arrayList = new ArrayList();
                try {
                    ArrayList arrayList2 = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList2 == null) {
                        throw new AssertionError();
                    }
                    str = (String) arrayList2.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList = GeneratedAndroidWebView.wrapError(e);
                }
                if (str == null) {
                    throw new NullPointerException("pathArg unexpectedly null.");
                }
                arrayList.add(0, flutterAssetManagerHostApi.list(str));
                reply.reply(arrayList);
            }

            public static /* synthetic */ void lambda$setup$1(FlutterAssetManagerHostApi flutterAssetManagerHostApi, Object obj, BasicMessageChannel.Reply reply) {
                String str;
                ArrayList arrayList = new ArrayList();
                try {
                    ArrayList arrayList2 = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList2 == null) {
                        throw new AssertionError();
                    }
                    str = (String) arrayList2.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList = GeneratedAndroidWebView.wrapError(e);
                }
                if (str == null) {
                    throw new NullPointerException("nameArg unexpectedly null.");
                }
                arrayList.add(0, flutterAssetManagerHostApi.getAssetFilePathByName(str));
                reply.reply(arrayList);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class WebChromeClientFlutterApi {
        private final BinaryMessenger binaryMessenger;

        /* loaded from: classes2.dex */
        public interface Reply<T> {
            void reply(T t);
        }

        public WebChromeClientFlutterApi(BinaryMessenger binaryMessenger) {
            this.binaryMessenger = binaryMessenger;
        }

        static MessageCodec<Object> getCodec() {
            return new StandardMessageCodec();
        }

        public void onProgressChanged(Long l, Long l2, Long l3, final Reply<Void> reply) {
            new BasicMessageChannel(this.binaryMessenger, "dev.flutter.pigeon.WebChromeClientFlutterApi.onProgressChanged", getCodec()).send(new ArrayList(Arrays.asList(l, l2, l3)), new BasicMessageChannel.Reply() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebChromeClientFlutterApi$$ExternalSyntheticLambda0
                @Override // io.flutter.plugin.common.BasicMessageChannel.Reply
                public final void reply(Object obj) {
                    GeneratedAndroidWebView.WebChromeClientFlutterApi.Reply.this.reply(null);
                }
            });
        }

        public void onShowFileChooser(Long l, Long l2, Long l3, final Reply<List<String>> reply) {
            new BasicMessageChannel(this.binaryMessenger, "dev.flutter.pigeon.WebChromeClientFlutterApi.onShowFileChooser", getCodec()).send(new ArrayList(Arrays.asList(l, l2, l3)), new BasicMessageChannel.Reply() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebChromeClientFlutterApi$$ExternalSyntheticLambda1
                @Override // io.flutter.plugin.common.BasicMessageChannel.Reply
                public final void reply(Object obj) {
                    GeneratedAndroidWebView.WebChromeClientFlutterApi.Reply.this.reply((List) obj);
                }
            });
        }
    }

    /* loaded from: classes2.dex */
    public interface WebStorageHostApi {
        void create(Long l);

        void deleteAllData(Long l);

        /* renamed from: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebStorageHostApi$-CC, reason: invalid class name */
        /* loaded from: classes2.dex */
        public final /* synthetic */ class CC {
            public static MessageCodec<Object> getCodec() {
                return new StandardMessageCodec();
            }

            public static void setup(BinaryMessenger binaryMessenger, final WebStorageHostApi webStorageHostApi) {
                BasicMessageChannel basicMessageChannel = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebStorageHostApi.create", getCodec());
                if (webStorageHostApi != null) {
                    basicMessageChannel.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebStorageHostApi$$ExternalSyntheticLambda0
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebStorageHostApi.CC.lambda$setup$0(GeneratedAndroidWebView.WebStorageHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel2 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.WebStorageHostApi.deleteAllData", getCodec());
                if (webStorageHostApi != null) {
                    basicMessageChannel2.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$WebStorageHostApi$$ExternalSyntheticLambda1
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidWebView.WebStorageHostApi.CC.lambda$setup$1(GeneratedAndroidWebView.WebStorageHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel2.setMessageHandler(null);
                }
            }

            public static /* synthetic */ void lambda$setup$0(WebStorageHostApi webStorageHostApi, Object obj, BasicMessageChannel.Reply reply) {
                Number number;
                ArrayList arrayList = new ArrayList();
                try {
                    ArrayList arrayList2 = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList2 == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList2.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                webStorageHostApi.create(number == null ? null : Long.valueOf(number.longValue()));
                arrayList.add(0, null);
                reply.reply(arrayList);
            }

            public static /* synthetic */ void lambda$setup$1(WebStorageHostApi webStorageHostApi, Object obj, BasicMessageChannel.Reply reply) {
                Number number;
                ArrayList arrayList = new ArrayList();
                try {
                    ArrayList arrayList2 = (ArrayList) obj;
                    if (!AnonymousClass2.$assertionsDisabled && arrayList2 == null) {
                        throw new AssertionError();
                    }
                    number = (Number) arrayList2.get(0);
                } catch (Error | RuntimeException e) {
                    arrayList = GeneratedAndroidWebView.wrapError(e);
                }
                if (number == null) {
                    throw new NullPointerException("instanceIdArg unexpectedly null.");
                }
                webStorageHostApi.deleteAllData(number == null ? null : Long.valueOf(number.longValue()));
                arrayList.add(0, null);
                reply.reply(arrayList);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class FileChooserParamsFlutterApiCodec extends StandardMessageCodec {
        public static final FileChooserParamsFlutterApiCodec INSTANCE = new FileChooserParamsFlutterApiCodec();

        private FileChooserParamsFlutterApiCodec() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.flutter.plugin.common.StandardMessageCodec
        public Object readValueOfType(byte b, ByteBuffer byteBuffer) {
            if (b == Byte.MIN_VALUE) {
                return FileChooserModeEnumData.fromList((ArrayList) readValue(byteBuffer));
            }
            return super.readValueOfType(b, byteBuffer);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.flutter.plugin.common.StandardMessageCodec
        public void writeValue(ByteArrayOutputStream byteArrayOutputStream, Object obj) {
            if (obj instanceof FileChooserModeEnumData) {
                byteArrayOutputStream.write(128);
                writeValue(byteArrayOutputStream, ((FileChooserModeEnumData) obj).toList());
            } else {
                super.writeValue(byteArrayOutputStream, obj);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class FileChooserParamsFlutterApi {
        private final BinaryMessenger binaryMessenger;

        /* loaded from: classes2.dex */
        public interface Reply<T> {
            void reply(T t);
        }

        public FileChooserParamsFlutterApi(BinaryMessenger binaryMessenger) {
            this.binaryMessenger = binaryMessenger;
        }

        static MessageCodec<Object> getCodec() {
            return FileChooserParamsFlutterApiCodec.INSTANCE;
        }

        public void create(Long l, Boolean bool, List<String> list, FileChooserModeEnumData fileChooserModeEnumData, String str, final Reply<Void> reply) {
            new BasicMessageChannel(this.binaryMessenger, "dev.flutter.pigeon.FileChooserParamsFlutterApi.create", getCodec()).send(new ArrayList(Arrays.asList(l, bool, list, fileChooserModeEnumData, str)), new BasicMessageChannel.Reply() { // from class: io.flutter.plugins.webviewflutter.GeneratedAndroidWebView$FileChooserParamsFlutterApi$$ExternalSyntheticLambda0
                @Override // io.flutter.plugin.common.BasicMessageChannel.Reply
                public final void reply(Object obj) {
                    GeneratedAndroidWebView.FileChooserParamsFlutterApi.Reply.this.reply(null);
                }
            });
        }
    }
}
