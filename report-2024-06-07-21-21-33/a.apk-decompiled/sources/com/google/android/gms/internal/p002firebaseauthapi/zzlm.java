package com.google.android.gms.internal.p002firebaseauthapi;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zzlm extends zzadf implements zzael {
    private static final zzlm zzb;
    private int zzd;
    private zzacc zze = zzacc.zzb;

    static {
        zzlm zzlmVar = new zzlm();
        zzb = zzlmVar;
        zzadf.zzG(zzlm.class, zzlmVar);
    }

    private zzlm() {
    }

    public static zzll zzb() {
        return (zzll) zzb.zzt();
    }

    public static zzlm zzd(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zzlm) zzadf.zzx(zzb, zzaccVar, zzacsVar);
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
            return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\n", new Object[]{"zzd", "zze"});
        }
        if (i2 == 3) {
            return new zzlm();
        }
        zzlk zzlkVar = null;
        if (i2 == 4) {
            return new zzll(zzlkVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
