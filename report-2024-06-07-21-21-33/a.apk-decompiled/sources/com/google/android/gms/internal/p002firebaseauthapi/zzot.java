package com.google.android.gms.internal.p002firebaseauthapi;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zzot extends zzadf implements zzael {
    private static final zzot zzb;
    private int zzd;
    private zzow zze;

    static {
        zzot zzotVar = new zzot();
        zzb = zzotVar;
        zzadf.zzG(zzot.class, zzotVar);
    }

    private zzot() {
    }

    public static zzos zzb() {
        return (zzos) zzb.zzt();
    }

    public static zzot zzd(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zzot) zzadf.zzx(zzb, zzaccVar, zzacsVar);
    }

    public static /* synthetic */ void zzg(zzot zzotVar, zzow zzowVar) {
        zzowVar.getClass();
        zzotVar.zze = zzowVar;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzow zze() {
        zzow zzowVar = this.zze;
        return zzowVar == null ? zzow.zzc() : zzowVar;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzadf
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\t", new Object[]{"zzd", "zze"});
        }
        if (i2 == 3) {
            return new zzot();
        }
        if (i2 == 4) {
            return new zzos(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
