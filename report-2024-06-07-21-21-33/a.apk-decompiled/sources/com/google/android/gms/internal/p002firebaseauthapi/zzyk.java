package com.google.android.gms.internal.p002firebaseauthapi;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zzyk {
    private final String zza;
    private final String zzb;

    public zzyk(Context context, String str) {
        Preconditions.checkNotNull(context);
        String checkNotEmpty = Preconditions.checkNotEmpty(str);
        this.zza = checkNotEmpty;
        try {
            byte[] packageCertificateHashBytes = AndroidUtilsLight.getPackageCertificateHashBytes(context, checkNotEmpty);
            if (packageCertificateHashBytes == null) {
                Log.e("FBA-PackageInfo", "single cert required: ".concat(String.valueOf(str)));
                this.zzb = null;
            } else {
                this.zzb = Hex.bytesToStringUppercase(packageCertificateHashBytes, false);
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e("FBA-PackageInfo", "no pkg: ".concat(String.valueOf(str)));
            this.zzb = null;
        }
    }

    public final String zza() {
        return this.zzb;
    }

    public final String zzb() {
        return this.zza;
    }
}
