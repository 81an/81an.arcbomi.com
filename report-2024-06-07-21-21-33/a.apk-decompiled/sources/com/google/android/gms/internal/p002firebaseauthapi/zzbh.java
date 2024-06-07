package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;
import java.security.GeneralSecurityException;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zzbh {
    private final zzof zza;
    private final zzjc zzb = zzjc.zza;

    private zzbh(zzof zzofVar) {
        this.zza = zzofVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final zzbh zza(zzof zzofVar) throws GeneralSecurityException {
        zzi(zzofVar);
        return new zzbh(zzofVar);
    }

    public static final zzbh zzh(zzfq zzfqVar, zzap zzapVar) throws GeneralSecurityException, IOException {
        byte[] bArr = new byte[0];
        zzmo zza = zzfqVar.zza();
        if (zza == null || zza.zzd().zzd() == 0) {
            throw new GeneralSecurityException("empty keyset");
        }
        try {
            zzof zzf = zzof.zzf(zzapVar.zza(zza.zzd().zzt(), bArr), zzacs.zza());
            zzi(zzf);
            return new zzbh(zzf);
        } catch (zzadn unused) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    private static void zzi(zzof zzofVar) throws GeneralSecurityException {
        if (zzofVar == null || zzofVar.zza() <= 0) {
            throw new GeneralSecurityException("empty keyset");
        }
    }

    public final String toString() {
        return zzcb.zza(this.zza).toString();
    }

    public final zzbh zzb() throws GeneralSecurityException {
        if (this.zza == null) {
            throw new GeneralSecurityException("cleartext keyset is not available");
        }
        zzoc zzc = zzof.zzc();
        for (zzoe zzoeVar : this.zza.zzg()) {
            zzns zzb = zzoeVar.zzb();
            if (zzb.zzb() == zznr.ASYMMETRIC_PRIVATE) {
                String zzf = zzb.zzf();
                zzacc zze = zzb.zze();
                zzax zza = zzbz.zza(zzf);
                if (zza instanceof zzbw) {
                    zzns zzf2 = ((zzbw) zza).zzf(zze);
                    zzbz.zzf(zzf2);
                    zzod zzodVar = (zzod) zzoeVar.zzu();
                    zzodVar.zza(zzf2);
                    zzc.zzb((zzoe) zzodVar.zzi());
                } else {
                    throw new GeneralSecurityException("manager for key type " + zzf + " is not a PrivateKeyManager");
                }
            } else {
                throw new GeneralSecurityException("The keyset contains a non-private key");
            }
        }
        zzc.zzc(this.zza.zzb());
        return new zzbh((zzof) zzc.zzi());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzof zzc() {
        return this.zza;
    }

    public final zzok zzd() {
        return zzcb.zza(this.zza);
    }

    public final Object zze(Class cls) throws GeneralSecurityException {
        Class zze = zzbz.zze(cls);
        if (zze == null) {
            throw new GeneralSecurityException("No wrapper found for ".concat(String.valueOf(cls.getName())));
        }
        zzcb.zzb(this.zza);
        zzbp zzbpVar = new zzbp(zze, null);
        zzbpVar.zzc(this.zzb);
        for (zzoe zzoeVar : this.zza.zzg()) {
            if (zzoeVar.zzk() == 3) {
                Object zzg = zzbz.zzg(zzoeVar.zzb(), zze);
                if (zzoeVar.zza() == this.zza.zzb()) {
                    zzbpVar.zza(zzg, zzoeVar);
                } else {
                    zzbpVar.zzb(zzg, zzoeVar);
                }
            }
        }
        return zzbz.zzk(zzbpVar.zzd(), cls);
    }

    public final void zzf(zzbj zzbjVar, zzap zzapVar) throws GeneralSecurityException, IOException {
        byte[] bArr = new byte[0];
        zzof zzofVar = this.zza;
        byte[] zzb = zzapVar.zzb(zzofVar.zzq(), bArr);
        try {
            if (zzof.zzf(zzapVar.zza(zzb, bArr), zzacs.zza()).equals(zzofVar)) {
                zzmn zza = zzmo.zza();
                zza.zza(zzacc.zzn(zzb));
                zza.zzb(zzcb.zza(zzofVar));
                zzbjVar.zzb((zzmo) zza.zzi());
                return;
            }
            throw new GeneralSecurityException("cannot encrypt keyset");
        } catch (zzadn unused) {
            throw new GeneralSecurityException("invalid keyset, corrupted key material");
        }
    }

    public final void zzg(zzbj zzbjVar) throws GeneralSecurityException, IOException {
        for (zzoe zzoeVar : this.zza.zzg()) {
            if (zzoeVar.zzb().zzb() == zznr.UNKNOWN_KEYMATERIAL || zzoeVar.zzb().zzb() == zznr.SYMMETRIC || zzoeVar.zzb().zzb() == zznr.ASYMMETRIC_PRIVATE) {
                throw new GeneralSecurityException(String.format("keyset contains key material of type %s for type url %s", zzoeVar.zzb().zzb().name(), zzoeVar.zzb().zzf()));
            }
        }
        zzbjVar.zzc(this.zza);
    }
}
