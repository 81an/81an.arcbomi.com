package com.google.android.gms.internal.p002firebaseauthapi;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
@Deprecated
/* loaded from: classes.dex */
public final class zzcd {
    public static final zznx zza = zzb(16);
    public static final zznx zzb = zzb(32);
    public static final zznx zzc = zza(16, 16);
    public static final zznx zzd = zza(32, 16);
    public static final zznx zze = zzc(16, 16, 32, 16, 5);
    public static final zznx zzf = zzc(32, 16, 32, 32, 5);
    public static final zznx zzg;
    public static final zznx zzh;

    static {
        zznw zza2 = zznx.zza();
        new zzcy();
        zza2.zzb("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key");
        zza2.zza(zzoy.TINK);
        zzg = (zznx) zza2.zzi();
        zznw zza3 = zznx.zza();
        new zzdi();
        zza3.zzb("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key");
        zza3.zza(zzoy.TINK);
        zzh = (zznx) zza3.zzi();
    }

    public static zznx zza(int i, int i2) {
        zzkn zzb2 = zzko.zzb();
        zzb2.zza(i);
        zzkq zzb3 = zzkr.zzb();
        zzb3.zza(16);
        zzb2.zzb((zzkr) zzb3.zzi());
        zzko zzkoVar = (zzko) zzb2.zzi();
        zznw zza2 = zznx.zza();
        zza2.zzc(zzkoVar.zzo());
        new zzcp();
        zza2.zzb("type.googleapis.com/google.crypto.tink.AesEaxKey");
        zza2.zza(zzoy.TINK);
        return (zznx) zza2.zzi();
    }

    public static zznx zzb(int i) {
        zzkw zzb2 = zzkx.zzb();
        zzb2.zza(i);
        zzkx zzkxVar = (zzkx) zzb2.zzi();
        zznw zza2 = zznx.zza();
        zza2.zzc(zzkxVar.zzo());
        new zzcs();
        zza2.zzb("type.googleapis.com/google.crypto.tink.AesGcmKey");
        zza2.zza(zzoy.TINK);
        return (zznx) zza2.zzi();
    }

    public static zznx zzc(int i, int i2, int i3, int i4, int i5) {
        zzke zzb2 = zzkf.zzb();
        zzkh zzb3 = zzki.zzb();
        zzb3.zza(16);
        zzb2.zzb((zzki) zzb3.zzi());
        zzb2.zza(i);
        zzkf zzkfVar = (zzkf) zzb2.zzi();
        zzmv zzb4 = zzmw.zzb();
        zzmy zzb5 = zzmz.zzb();
        zzb5.zzb(5);
        zzb5.zza(i4);
        zzb4.zzb((zzmz) zzb5.zzi());
        zzb4.zza(32);
        zzmw zzmwVar = (zzmw) zzb4.zzi();
        zzjy zza2 = zzjz.zza();
        zza2.zza(zzkfVar);
        zza2.zzb(zzmwVar);
        zzjz zzjzVar = (zzjz) zza2.zzi();
        zznw zza3 = zznx.zza();
        zza3.zzc(zzjzVar.zzo());
        new zzcj();
        zza3.zzb("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey");
        zza3.zza(zzoy.TINK);
        return (zznx) zza3.zzi();
    }
}
