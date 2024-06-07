package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Arrays;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zzhv extends zziv {
    private final int zza;
    private final int zzb;
    private final zzht zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzhv(int i, int i2, zzht zzhtVar, zzhu zzhuVar) {
        this.zza = i;
        this.zzb = i2;
        this.zzc = zzhtVar;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzhv)) {
            return false;
        }
        zzhv zzhvVar = (zzhv) obj;
        return zzhvVar.zza == this.zza && zzhvVar.zzb() == zzb() && zzhvVar.zzc == this.zzc;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzb), this.zzc});
    }

    public final String toString() {
        return "AES-CMAC Parameters (variant: " + String.valueOf(this.zzc) + ", " + this.zzb + "-byte tags, and " + this.zza + "-byte key)";
    }

    public final int zza() {
        return this.zza;
    }

    public final int zzb() {
        zzht zzhtVar = this.zzc;
        if (zzhtVar == zzht.zzd) {
            return this.zzb;
        }
        if (zzhtVar == zzht.zza || zzhtVar == zzht.zzb || zzhtVar == zzht.zzc) {
            return this.zzb + 5;
        }
        throw new IllegalStateException("Unknown variant");
    }

    public final zzht zzc() {
        return this.zzc;
    }

    public final boolean zzd() {
        return this.zzc != zzht.zzd;
    }
}
