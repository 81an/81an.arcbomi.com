package com.google.android.gms.internal.p002firebaseauthapi;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zzmd extends zzadf implements zzael {
    private static final zzmd zzb;
    private int zzd;
    private zzmg zze;
    private zzacc zzf = zzacc.zzb;

    static {
        zzmd zzmdVar = new zzmd();
        zzb = zzmdVar;
        zzadf.zzG(zzmd.class, zzmdVar);
    }

    private zzmd() {
    }

    public static zzmc zzb() {
        return (zzmc) zzb.zzt();
    }

    public static zzmd zzd(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zzmd) zzadf.zzx(zzb, zzaccVar, zzacsVar);
    }

    public static /* synthetic */ void zzh(zzmd zzmdVar, zzmg zzmgVar) {
        zzmgVar.getClass();
        zzmdVar.zze = zzmgVar;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzmg zze() {
        zzmg zzmgVar = this.zze;
        return zzmgVar == null ? zzmg.zze() : zzmgVar;
    }

    public final zzacc zzf() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzadf
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzD(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zzd", "zze", "zzf"});
        }
        if (i2 == 3) {
            return new zzmd();
        }
        if (i2 == 4) {
            return new zzmc(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
