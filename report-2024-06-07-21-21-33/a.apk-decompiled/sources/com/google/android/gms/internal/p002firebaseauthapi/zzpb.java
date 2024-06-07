package com.google.android.gms.internal.p002firebaseauthapi;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
@Deprecated
/* loaded from: classes.dex */
public final class zzpb extends zzadf implements zzael {
    private static final zzpb zzb;
    private String zzd = "";
    private zzadk zze = zzz();

    static {
        zzpb zzpbVar = new zzpb();
        zzb = zzpbVar;
        zzadf.zzG(zzpb.class, zzpbVar);
    }

    private zzpb() {
    }

    public static zzpb zzb() {
        return zzb;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzadf
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzD(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002\u001b", new Object[]{"zzd", "zze", zzoa.class});
        }
        if (i2 == 3) {
            return new zzpb();
        }
        if (i2 == 4) {
            return new zzpa(null);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
