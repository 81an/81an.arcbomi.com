package com.google.android.gms.internal.p002firebaseauthapi;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
@Deprecated
/* loaded from: classes.dex */
public final class zzen {
    public static final zznx zza;
    public static final zznx zzb;
    public static final zznx zzc;
    private static final byte[] zzd;

    static {
        byte[] bArr = new byte[0];
        zzd = bArr;
        zza = zza(4, 5, 3, zzcd.zza, zzoy.TINK, bArr);
        zzb = zza(4, 5, 4, zzcd.zza, zzoy.RAW, bArr);
        zzc = zza(4, 5, 3, zzcd.zze, zzoy.TINK, bArr);
    }

    public static zznx zza(int i, int i2, int i3, zznx zznxVar, zzoy zzoyVar, byte[] bArr) {
        zzlw zza2 = zzlx.zza();
        zzmi zza3 = zzmj.zza();
        zza3.zzb(4);
        zza3.zzc(5);
        zza3.zza(zzacc.zzn(bArr));
        zzmj zzmjVar = (zzmj) zza3.zzi();
        zzlt zza4 = zzlu.zza();
        zza4.zza(zznxVar);
        zzlu zzluVar = (zzlu) zza4.zzi();
        zzlz zzb2 = zzma.zzb();
        zzb2.zzb(zzmjVar);
        zzb2.zza(zzluVar);
        zzb2.zzc(i3);
        zza2.zza((zzma) zzb2.zzi());
        zzlx zzlxVar = (zzlx) zza2.zzi();
        zznw zza5 = zznx.zza();
        new zzef();
        zza5.zzb("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey");
        zza5.zza(zzoyVar);
        zza5.zzc(zzlxVar.zzo());
        return (zznx) zza5.zzi();
    }
}
