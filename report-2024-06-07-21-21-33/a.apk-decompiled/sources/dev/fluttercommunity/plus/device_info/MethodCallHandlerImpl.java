package dev.fluttercommunity.plus.device_info;

import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.Constants;
import com.tekartik.sqflite.Constant;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MethodCallHandlerImpl.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0002J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u0014\u0010\u0007\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Ldev/fluttercommunity/plus/device_info/MethodCallHandlerImpl;", "Lio/flutter/plugin/common/MethodChannel$MethodCallHandler;", "packageManager", "Landroid/content/pm/PackageManager;", "windowManager", "Landroid/view/WindowManager;", "(Landroid/content/pm/PackageManager;Landroid/view/WindowManager;)V", "isEmulator", "", "()Z", "getSystemFeatures", "", "", "onMethodCall", "", NotificationCompat.CATEGORY_CALL, "Lio/flutter/plugin/common/MethodCall;", Constant.PARAM_RESULT, "Lio/flutter/plugin/common/MethodChannel$Result;", "device_info_plus_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MethodCallHandlerImpl implements MethodChannel.MethodCallHandler {
    private final PackageManager packageManager;
    private final WindowManager windowManager;

    public MethodCallHandlerImpl(PackageManager packageManager, WindowManager windowManager) {
        Intrinsics.checkNotNullParameter(packageManager, "packageManager");
        Intrinsics.checkNotNullParameter(windowManager, "windowManager");
        this.packageManager = packageManager;
        this.windowManager = windowManager;
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
        String str;
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(result, "result");
        if (call.method.equals("getDeviceInfo")) {
            HashMap hashMap = new HashMap();
            String BOARD = Build.BOARD;
            Intrinsics.checkNotNullExpressionValue(BOARD, "BOARD");
            hashMap.put("board", BOARD);
            String BOOTLOADER = Build.BOOTLOADER;
            Intrinsics.checkNotNullExpressionValue(BOOTLOADER, "BOOTLOADER");
            hashMap.put("bootloader", BOOTLOADER);
            String BRAND = Build.BRAND;
            Intrinsics.checkNotNullExpressionValue(BRAND, "BRAND");
            hashMap.put("brand", BRAND);
            String DEVICE = Build.DEVICE;
            Intrinsics.checkNotNullExpressionValue(DEVICE, "DEVICE");
            hashMap.put("device", DEVICE);
            String DISPLAY = Build.DISPLAY;
            Intrinsics.checkNotNullExpressionValue(DISPLAY, "DISPLAY");
            hashMap.put(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION, DISPLAY);
            String FINGERPRINT = Build.FINGERPRINT;
            Intrinsics.checkNotNullExpressionValue(FINGERPRINT, "FINGERPRINT");
            hashMap.put("fingerprint", FINGERPRINT);
            String HARDWARE = Build.HARDWARE;
            Intrinsics.checkNotNullExpressionValue(HARDWARE, "HARDWARE");
            hashMap.put("hardware", HARDWARE);
            String HOST = Build.HOST;
            Intrinsics.checkNotNullExpressionValue(HOST, "HOST");
            hashMap.put(io.flutter.plugins.firebase.auth.Constants.HOST, HOST);
            String ID = Build.ID;
            Intrinsics.checkNotNullExpressionValue(ID, "ID");
            hashMap.put("id", ID);
            String MANUFACTURER = Build.MANUFACTURER;
            Intrinsics.checkNotNullExpressionValue(MANUFACTURER, "MANUFACTURER");
            hashMap.put("manufacturer", MANUFACTURER);
            String MODEL = Build.MODEL;
            Intrinsics.checkNotNullExpressionValue(MODEL, "MODEL");
            hashMap.put("model", MODEL);
            String PRODUCT = Build.PRODUCT;
            Intrinsics.checkNotNullExpressionValue(PRODUCT, "PRODUCT");
            hashMap.put("product", PRODUCT);
            if (Build.VERSION.SDK_INT >= 21) {
                String[] SUPPORTED_32_BIT_ABIS = Build.SUPPORTED_32_BIT_ABIS;
                Intrinsics.checkNotNullExpressionValue(SUPPORTED_32_BIT_ABIS, "SUPPORTED_32_BIT_ABIS");
                hashMap.put("supported32BitAbis", CollectionsKt.listOf(Arrays.copyOf(SUPPORTED_32_BIT_ABIS, SUPPORTED_32_BIT_ABIS.length)));
                String[] SUPPORTED_64_BIT_ABIS = Build.SUPPORTED_64_BIT_ABIS;
                Intrinsics.checkNotNullExpressionValue(SUPPORTED_64_BIT_ABIS, "SUPPORTED_64_BIT_ABIS");
                hashMap.put("supported64BitAbis", CollectionsKt.listOf(Arrays.copyOf(SUPPORTED_64_BIT_ABIS, SUPPORTED_64_BIT_ABIS.length)));
                String[] SUPPORTED_ABIS = Build.SUPPORTED_ABIS;
                Intrinsics.checkNotNullExpressionValue(SUPPORTED_ABIS, "SUPPORTED_ABIS");
                hashMap.put("supportedAbis", CollectionsKt.listOf(Arrays.copyOf(SUPPORTED_ABIS, SUPPORTED_ABIS.length)));
            } else {
                hashMap.put("supported32BitAbis", CollectionsKt.emptyList());
                hashMap.put("supported64BitAbis", CollectionsKt.emptyList());
                hashMap.put("supportedAbis", CollectionsKt.emptyList());
            }
            String TAGS = Build.TAGS;
            Intrinsics.checkNotNullExpressionValue(TAGS, "TAGS");
            hashMap.put("tags", TAGS);
            String TYPE = Build.TYPE;
            Intrinsics.checkNotNullExpressionValue(TYPE, "TYPE");
            hashMap.put("type", TYPE);
            hashMap.put("isPhysicalDevice", Boolean.valueOf(!isEmulator()));
            hashMap.put("systemFeatures", getSystemFeatures());
            HashMap hashMap2 = new HashMap();
            if (Build.VERSION.SDK_INT >= 23) {
                String BASE_OS = Build.VERSION.BASE_OS;
                Intrinsics.checkNotNullExpressionValue(BASE_OS, "BASE_OS");
                hashMap2.put("baseOS", BASE_OS);
                hashMap2.put("previewSdkInt", Integer.valueOf(Build.VERSION.PREVIEW_SDK_INT));
                String SECURITY_PATCH = Build.VERSION.SECURITY_PATCH;
                Intrinsics.checkNotNullExpressionValue(SECURITY_PATCH, "SECURITY_PATCH");
                hashMap2.put("securityPatch", SECURITY_PATCH);
            }
            String CODENAME = Build.VERSION.CODENAME;
            Intrinsics.checkNotNullExpressionValue(CODENAME, "CODENAME");
            hashMap2.put("codename", CODENAME);
            String INCREMENTAL = Build.VERSION.INCREMENTAL;
            Intrinsics.checkNotNullExpressionValue(INCREMENTAL, "INCREMENTAL");
            hashMap2.put("incremental", INCREMENTAL);
            String RELEASE = Build.VERSION.RELEASE;
            Intrinsics.checkNotNullExpressionValue(RELEASE, "RELEASE");
            hashMap2.put("release", RELEASE);
            hashMap2.put("sdkInt", Integer.valueOf(Build.VERSION.SDK_INT));
            hashMap.put("version", hashMap2);
            Display defaultDisplay = this.windowManager.getDefaultDisplay();
            Intrinsics.checkNotNullExpressionValue(defaultDisplay, "windowManager.defaultDisplay");
            DisplayMetrics displayMetrics = new DisplayMetrics();
            if (Build.VERSION.SDK_INT >= 17) {
                defaultDisplay.getRealMetrics(displayMetrics);
            } else {
                defaultDisplay.getMetrics(displayMetrics);
            }
            HashMap hashMap3 = new HashMap();
            hashMap3.put("widthPx", Double.valueOf(displayMetrics.widthPixels));
            hashMap3.put("heightPx", Double.valueOf(displayMetrics.heightPixels));
            hashMap3.put("xDpi", Float.valueOf(displayMetrics.xdpi));
            hashMap3.put("yDpi", Float.valueOf(displayMetrics.ydpi));
            hashMap.put("displayMetrics", hashMap3);
            if (Build.VERSION.SDK_INT >= 26) {
                try {
                    str = Build.getSerial();
                } catch (SecurityException unused) {
                    str = "unknown";
                }
                Intrinsics.checkNotNullExpressionValue(str, "try {\n                  …UNKNOWN\n                }");
                hashMap.put("serialNumber", str);
            } else {
                String SERIAL = Build.SERIAL;
                Intrinsics.checkNotNullExpressionValue(SERIAL, "SERIAL");
                hashMap.put("serialNumber", SERIAL);
            }
            result.success(hashMap);
            return;
        }
        result.notImplemented();
    }

    private final List<String> getSystemFeatures() {
        FeatureInfo[] systemAvailableFeatures = this.packageManager.getSystemAvailableFeatures();
        Intrinsics.checkNotNullExpressionValue(systemAvailableFeatures, "packageManager.systemAvailableFeatures");
        ArrayList arrayList = new ArrayList();
        for (FeatureInfo featureInfo : systemAvailableFeatures) {
            if (!(featureInfo.name == null)) {
                arrayList.add(featureInfo);
            }
        }
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.add(((FeatureInfo) it.next()).name);
        }
        return arrayList3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x001d, code lost:
    
        if (kotlin.text.StringsKt.startsWith$default(r0, "generic", false, 2, (java.lang.Object) null) == false) goto L6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean isEmulator() {
        /*
            Method dump skipped, instructions count: 287
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: dev.fluttercommunity.plus.device_info.MethodCallHandlerImpl.isEmulator():boolean");
    }
}
