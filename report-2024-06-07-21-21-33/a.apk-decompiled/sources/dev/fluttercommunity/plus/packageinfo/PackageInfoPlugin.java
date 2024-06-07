package dev.fluttercommunity.plus.packageinfo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.tekartik.sqflite.Constant;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.firebase.auth.Constants;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PackageInfoPlugin.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001f2\u00020\u00012\u00020\u0002:\u0001\u001fB\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0012\u0010\f\u001a\u0004\u0018\u00010\t2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u000bH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Ldev/fluttercommunity/plus/packageinfo/PackageInfoPlugin;", "Lio/flutter/plugin/common/MethodChannel$MethodCallHandler;", "Lio/flutter/embedding/engine/plugins/FlutterPlugin;", "()V", "applicationContext", "Landroid/content/Context;", "methodChannel", "Lio/flutter/plugin/common/MethodChannel;", "bytesToHex", "", "bytes", "", "getBuildSignature", "pm", "Landroid/content/pm/PackageManager;", "getLongVersionCode", "", "info", "Landroid/content/pm/PackageInfo;", "onAttachedToEngine", "", "binding", "Lio/flutter/embedding/engine/plugins/FlutterPlugin$FlutterPluginBinding;", "onDetachedFromEngine", "onMethodCall", NotificationCompat.CATEGORY_CALL, "Lio/flutter/plugin/common/MethodCall;", Constant.PARAM_RESULT, "Lio/flutter/plugin/common/MethodChannel$Result;", "signatureToSha1", "sig", "Companion", "package_info_plus_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class PackageInfoPlugin implements MethodChannel.MethodCallHandler, FlutterPlugin {
    private static final String CHANNEL_NAME = "dev.fluttercommunity.plus/package_info";
    private Context applicationContext;
    private MethodChannel methodChannel;

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        this.applicationContext = binding.getApplicationContext();
        MethodChannel methodChannel = new MethodChannel(binding.getBinaryMessenger(), CHANNEL_NAME);
        this.methodChannel = methodChannel;
        Intrinsics.checkNotNull(methodChannel);
        methodChannel.setMethodCallHandler(this);
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        this.applicationContext = null;
        MethodChannel methodChannel = this.methodChannel;
        Intrinsics.checkNotNull(methodChannel);
        methodChannel.setMethodCallHandler(null);
        this.methodChannel = null;
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(result, "result");
        try {
            if (Intrinsics.areEqual(call.method, "getAll")) {
                Context context = this.applicationContext;
                Intrinsics.checkNotNull(context);
                PackageManager packageManager = context.getPackageManager();
                Context context2 = this.applicationContext;
                Intrinsics.checkNotNull(context2);
                PackageInfo info = packageManager.getPackageInfo(context2.getPackageName(), 0);
                Intrinsics.checkNotNullExpressionValue(packageManager, "packageManager");
                String buildSignature = getBuildSignature(packageManager);
                Context context3 = this.applicationContext;
                Intrinsics.checkNotNull(context3);
                String installerPackageName = packageManager.getInstallerPackageName(context3.getPackageName());
                HashMap hashMap = new HashMap();
                hashMap.put("appName", info.applicationInfo.loadLabel(packageManager).toString());
                Context context4 = this.applicationContext;
                Intrinsics.checkNotNull(context4);
                hashMap.put(Constants.PACKAGE_NAME, context4.getPackageName());
                hashMap.put("version", info.versionName);
                Intrinsics.checkNotNullExpressionValue(info, "info");
                hashMap.put("buildNumber", String.valueOf(getLongVersionCode(info)));
                if (buildSignature != null) {
                    hashMap.put("buildSignature", buildSignature);
                }
                if (installerPackageName != null) {
                    hashMap.put("installerStore", installerPackageName);
                }
                result.success(hashMap);
                return;
            }
            result.notImplemented();
        } catch (PackageManager.NameNotFoundException e) {
            result.error("Name not found", e.getMessage(), null);
        }
    }

    private final long getLongVersionCode(PackageInfo info) {
        if (Build.VERSION.SDK_INT >= 28) {
            return info.getLongVersionCode();
        }
        return info.versionCode;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x007b, code lost:
    
        if ((r1.length == 0) != false) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.String getBuildSignature(android.content.pm.PackageManager r6) {
        /*
            r5 = this;
            r0 = 0
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            r2 = 28
            if (r1 < r2) goto L60
            android.content.Context r1 = r5.applicationContext     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            java.lang.String r1 = r1.getPackageName()     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            r2 = 134217728(0x8000000, float:3.85186E-34)
            android.content.pm.PackageInfo r6 = r6.getPackageInfo(r1, r2)     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            android.content.pm.SigningInfo r6 = r6.signingInfo     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            if (r6 != 0) goto L1b
            return r0
        L1b:
            boolean r1 = r6.hasMultipleSigners()     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            if (r1 == 0) goto L40
            android.content.pm.Signature[] r6 = r6.getApkContentsSigners()     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            java.lang.String r1 = "signingInfo.apkContentsSigners"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r1)     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            java.lang.Object[] r6 = (java.lang.Object[]) r6     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            java.lang.Object r6 = kotlin.collections.ArraysKt.first(r6)     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            android.content.pm.Signature r6 = (android.content.pm.Signature) r6     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            byte[] r6 = r6.toByteArray()     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            java.lang.String r1 = "signingInfo.apkContentsS…ers.first().toByteArray()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r1)     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            java.lang.String r6 = r5.signatureToSha1(r6)     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            goto L5e
        L40:
            android.content.pm.Signature[] r6 = r6.getSigningCertificateHistory()     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            java.lang.String r1 = "signingInfo.signingCertificateHistory"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r1)     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            java.lang.Object[] r6 = (java.lang.Object[]) r6     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            java.lang.Object r6 = kotlin.collections.ArraysKt.first(r6)     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            android.content.pm.Signature r6 = (android.content.pm.Signature) r6     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            byte[] r6 = r6.toByteArray()     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            java.lang.String r1 = "signingInfo.signingCerti…ory.first().toByteArray()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r1)     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            java.lang.String r6 = r5.signatureToSha1(r6)     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
        L5e:
            r0 = r6
            goto Lb4
        L60:
            android.content.Context r1 = r5.applicationContext     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            java.lang.String r1 = r1.getPackageName()     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            r2 = 64
            android.content.pm.PackageInfo r6 = r6.getPackageInfo(r1, r2)     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            android.content.pm.Signature[] r1 = r6.signatures     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L7d
            int r4 = r1.length     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            if (r4 != 0) goto L7a
            r4 = 1
            goto L7b
        L7a:
            r4 = 0
        L7b:
            if (r4 == 0) goto L7e
        L7d:
            r2 = 1
        L7e:
            if (r2 != 0) goto La9
            android.content.pm.Signature[] r6 = r6.signatures     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            java.lang.String r2 = "packageInfo.signatures"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r2)     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            java.lang.Object[] r6 = (java.lang.Object[]) r6     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            java.lang.Object r6 = kotlin.collections.ArraysKt.first(r6)     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            if (r6 != 0) goto L90
            goto La9
        L90:
            java.lang.String r6 = "signatures"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r6)     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            java.lang.Object r6 = kotlin.collections.ArraysKt.first(r1)     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            android.content.pm.Signature r6 = (android.content.pm.Signature) r6     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            byte[] r6 = r6.toByteArray()     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            java.lang.String r1 = "signatures.first().toByteArray()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r1)     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            java.lang.String r6 = r5.signatureToSha1(r6)     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            goto L5e
        La9:
            r6 = r0
            java.lang.String r6 = (java.lang.String) r6     // Catch: java.security.NoSuchAlgorithmException -> Lad android.content.pm.PackageManager.NameNotFoundException -> Lb1
            goto Lb4
        Lad:
            r6 = r0
            java.lang.String r6 = (java.lang.String) r6
            goto Lb4
        Lb1:
            r6 = r0
            java.lang.String r6 = (java.lang.String) r6
        Lb4:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: dev.fluttercommunity.plus.packageinfo.PackageInfoPlugin.getBuildSignature(android.content.pm.PackageManager):java.lang.String");
    }

    private final String signatureToSha1(byte[] sig) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
        messageDigest.update(sig);
        byte[] hashText = messageDigest.digest();
        Intrinsics.checkNotNullExpressionValue(hashText, "hashText");
        return bytesToHex(hashText);
    }

    private final String bytesToHex(byte[] bytes) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] cArr2 = new char[bytes.length * 2];
        int length = bytes.length;
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            int i3 = bytes[i] & 255;
            int i4 = i * 2;
            cArr2[i4] = cArr[i3 >>> 4];
            cArr2[i4 + 1] = cArr[i3 & 15];
            i = i2;
        }
        return new String(cArr2);
    }
}
