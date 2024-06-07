package com.google.android.gms.internal.p002firebaseauthapi;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zzns extends zzadf implements zzael {
    private static final zzns zzb;
    private String zzd = "";
    private zzacc zze = zzacc.zzb;
    private int zzf;

    static {
        zzns zznsVar = new zzns();
        zzb = zznsVar;
        zzadf.zzG(zzns.class, zznsVar);
    }

    private zzns() {
    }

    public static zznp zza() {
        return (zznp) zzb.zzt();
    }

    public static zzns zzd() {
        return zzb;
    }

    public final zznr zzb() {
        zznr zzb2 = zznr.zzb(this.zzf);
        return zzb2 == null ? zznr.UNRECOGNIZED : zzb2;
    }

    public final zzacc zze() {
        return this.zze;
    }

    public final String zzf() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzadf
    protected final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzD(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"zzd", "zze", "zzf"});
        }
        if (i2 == 3) {
            return new zzns();
        }
        zzno zznoVar = null;
        if (i2 == 4) {
            return new zznp(zznoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
