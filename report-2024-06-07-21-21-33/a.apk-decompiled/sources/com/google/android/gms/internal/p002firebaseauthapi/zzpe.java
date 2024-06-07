package com.google.android.gms.internal.p002firebaseauthapi;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zzpe extends zzadf implements zzael {
    private static final zzpe zzb;
    private int zzd;
    private zzacc zze = zzacc.zzb;

    static {
        zzpe zzpeVar = new zzpe();
        zzb = zzpeVar;
        zzadf.zzG(zzpe.class, zzpeVar);
    }

    private zzpe() {
    }

    public static zzpd zzb() {
        return (zzpd) zzb.zzt();
    }

    public static zzpe zzd(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zzpe) zzadf.zzx(zzb, zzaccVar, zzacsVar);
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzacc zze() {
        return this.zze;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzadf
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0003\u0002\u0000\u0000\u0000\u0001\u000b\u0003\n", new Object[]{"zzd", "zze"});
        }
        if (i2 == 3) {
            return new zzpe();
        }
        zzpc zzpcVar = null;
        if (i2 == 4) {
            return new zzpd(zzpcVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
