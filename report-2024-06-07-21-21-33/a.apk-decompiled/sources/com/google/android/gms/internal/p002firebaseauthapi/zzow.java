package com.google.android.gms.internal.p002firebaseauthapi;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zzow extends zzadf implements zzael {
    private static final zzow zzb;
    private String zzd = "";
    private zznx zze;

    static {
        zzow zzowVar = new zzow();
        zzb = zzowVar;
        zzadf.zzG(zzow.class, zzowVar);
    }

    private zzow() {
    }

    public static zzow zzc() {
        return zzb;
    }

    public static zzow zzd(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zzow) zzadf.zzx(zzb, zzaccVar, zzacsVar);
    }

    public final zznx zza() {
        zznx zznxVar = this.zze;
        return zznxVar == null ? zznx.zzc() : zznxVar;
    }

    public final String zze() {
        return this.zzd;
    }

    public final boolean zzf() {
        return this.zze != null;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzadf
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\t", new Object[]{"zzd", "zze"});
        }
        if (i2 == 3) {
            return new zzow();
        }
        if (i2 == 4) {
            return new zzov(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
