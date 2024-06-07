package com.google.android.gms.internal.p002firebaseauthapi;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zzlp extends zzadf implements zzael {
    private static final zzlp zzb;

    static {
        zzlp zzlpVar = new zzlp();
        zzb = zzlpVar;
        zzadf.zzG(zzlp.class, zzlpVar);
    }

    private zzlp() {
    }

    public static zzlp zzb() {
        return zzb;
    }

    public static zzlp zzc(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zzlp) zzadf.zzx(zzb, zzaccVar, zzacsVar);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzadf
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzD(zzb, "\u0000\u0000", null);
        }
        if (i2 == 3) {
            return new zzlp();
        }
        if (i2 == 4) {
            return new zzlo(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
