package com.google.android.gms.internal.p002firebaseauthapi;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
@Deprecated
/* loaded from: classes.dex */
public final class zzoa extends zzadf implements zzael {
    private static final zzoa zzb;
    private int zzf;
    private boolean zzg;
    private String zzd = "";
    private String zze = "";
    private String zzh = "";

    static {
        zzoa zzoaVar = new zzoa();
        zzb = zzoaVar;
        zzadf.zzG(zzoa.class, zzoaVar);
    }

    private zzoa() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzadf
    public final Object zzj(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzD(zzb, "\u0000\u0005\u0000\u0000\u0001\u0005\u0005\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\u000b\u0004\u0007\u0005Ȉ", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh"});
        }
        if (i2 == 3) {
            return new zzoa();
        }
        zzny zznyVar = null;
        if (i2 == 4) {
            return new zznz(zznyVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
