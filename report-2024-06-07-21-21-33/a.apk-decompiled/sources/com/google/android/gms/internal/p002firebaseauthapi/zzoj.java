package com.google.android.gms.internal.p002firebaseauthapi;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zzoj extends zzadf implements zzael {
    private static final zzoj zzb;
    private String zzd = "";
    private int zze;
    private int zzf;
    private int zzg;

    static {
        zzoj zzojVar = new zzoj();
        zzb = zzojVar;
        zzadf.zzG(zzoj.class, zzojVar);
    }

    private zzoj() {
    }

    public static zzoi zzb() {
        return (zzoi) zzb.zzt();
    }

    public static /* synthetic */ void zzd(zzoj zzojVar, String str) {
        str.getClass();
        zzojVar.zzd = str;
    }

    public final int zza() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzadf
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzD(zzb, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002\f\u0003\u000b\u0004\f", new Object[]{"zzd", "zze", "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzoj();
        }
        if (i2 == 4) {
            return new zzoi(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
