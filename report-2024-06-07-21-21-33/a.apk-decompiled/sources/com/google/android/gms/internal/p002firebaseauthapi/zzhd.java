package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Arrays;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
final class zzhd {
    private final Class zza;
    private final zzqv zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzhd(Class cls, zzqv zzqvVar, zzhc zzhcVar) {
        this.zza = cls;
        this.zzb = zzqvVar;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzhd)) {
            return false;
        }
        zzhd zzhdVar = (zzhd) obj;
        return zzhdVar.zza.equals(this.zza) && zzhdVar.zzb.equals(this.zzb);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, this.zzb});
    }

    public final String toString() {
        return this.zza.getSimpleName() + ", object identifier: " + String.valueOf(this.zzb);
    }
}
