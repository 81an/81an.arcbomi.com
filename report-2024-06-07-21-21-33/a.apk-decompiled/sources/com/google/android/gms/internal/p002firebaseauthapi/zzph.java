package com.google.android.gms.internal.p002firebaseauthapi;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zzph extends zzadf implements zzael {
    private static final zzph zzb;
    private int zzd;

    static {
        zzph zzphVar = new zzph();
        zzb = zzphVar;
        zzadf.zzG(zzph.class, zzphVar);
    }

    private zzph() {
    }

    public static zzph zzb() {
        return zzb;
    }

    public static zzph zzc(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zzph) zzadf.zzx(zzb, zzaccVar, zzacsVar);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzadf
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzD(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u000b", new Object[]{"zzd"});
        }
        if (i2 == 3) {
            return new zzph();
        }
        if (i2 == 4) {
            return new zzpg(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
