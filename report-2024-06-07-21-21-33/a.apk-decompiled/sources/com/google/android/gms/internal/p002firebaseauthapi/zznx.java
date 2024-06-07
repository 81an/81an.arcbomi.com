package com.google.android.gms.internal.p002firebaseauthapi;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zznx extends zzadf implements zzael {
    private static final zznx zzb;
    private String zzd = "";
    private zzacc zze = zzacc.zzb;
    private int zzf;

    static {
        zznx zznxVar = new zznx();
        zzb = zznxVar;
        zzadf.zzG(zznx.class, zznxVar);
    }

    private zznx() {
    }

    public static zznw zza() {
        return (zznw) zzb.zzt();
    }

    public static /* synthetic */ zznx zzb() {
        return zzb;
    }

    public static zznx zzc() {
        return zzb;
    }

    public static /* synthetic */ void zzg(zznx zznxVar, String str) {
        str.getClass();
        zznxVar.zzd = str;
    }

    public static /* synthetic */ void zzh(zznx zznxVar, zzacc zzaccVar) {
        zznxVar.zze = zzaccVar;
    }

    public static /* synthetic */ void zzi(zznx zznxVar, zzoy zzoyVar) {
        zznxVar.zzf = zzoyVar.zza();
    }

    public final zzoy zzd() {
        zzoy zzb2 = zzoy.zzb(this.zzf);
        return zzb2 == null ? zzoy.UNRECOGNIZED : zzb2;
    }

    public final zzacc zze() {
        return this.zze;
    }

    public final String zzf() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzadf
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzD(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"zzd", "zze", "zzf"});
        }
        if (i2 == 3) {
            return new zznx();
        }
        zznv zznvVar = null;
        if (i2 == 4) {
            return new zznw(zznvVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
