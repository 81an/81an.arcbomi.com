package com.google.android.gms.internal.p002firebaseauthapi;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zzne extends zzadf implements zzael {
    private static final zzne zzb;
    private zznh zzd;

    static {
        zzne zzneVar = new zzne();
        zzb = zzneVar;
        zzadf.zzG(zzne.class, zzneVar);
    }

    private zzne() {
    }

    public static zznd zza() {
        return (zznd) zzb.zzt();
    }

    public static zzne zzc(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zzne) zzadf.zzx(zzb, zzaccVar, zzacsVar);
    }

    public static /* synthetic */ void zze(zzne zzneVar, zznh zznhVar) {
        zznhVar.getClass();
        zzneVar.zzd = zznhVar;
    }

    public final zznh zzd() {
        zznh zznhVar = this.zzd;
        return zznhVar == null ? zznh.zzc() : zznhVar;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzadf
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzD(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", new Object[]{"zzd"});
        }
        if (i2 == 3) {
            return new zzne();
        }
        if (i2 == 4) {
            return new zznd(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
