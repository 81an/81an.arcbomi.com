package com.google.android.gms.internal.p002firebaseauthapi;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zzon extends zzadf implements zzael {
    private static final zzon zzb;
    private int zzd;
    private zzoq zze;

    static {
        zzon zzonVar = new zzon();
        zzb = zzonVar;
        zzadf.zzG(zzon.class, zzonVar);
    }

    private zzon() {
    }

    public static zzom zzb() {
        return (zzom) zzb.zzt();
    }

    public static zzon zzd(zzacc zzaccVar, zzacs zzacsVar) throws zzadn {
        return (zzon) zzadf.zzx(zzb, zzaccVar, zzacsVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzg(zzon zzonVar, zzoq zzoqVar) {
        zzoqVar.getClass();
        zzonVar.zze = zzoqVar;
    }

    public final int zza() {
        return this.zzd;
    }

    public final zzoq zze() {
        zzoq zzoqVar = this.zze;
        return zzoqVar == null ? zzoq.zzb() : zzoqVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
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
            return new zzon();
        }
        zzol zzolVar = null;
        if (i2 == 4) {
            return new zzom(zzolVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
