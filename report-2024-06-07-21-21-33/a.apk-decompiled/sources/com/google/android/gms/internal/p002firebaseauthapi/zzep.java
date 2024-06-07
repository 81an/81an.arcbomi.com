package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
final class zzep implements zzps {
    private final String zza;
    private final int zzb;
    private zzku zzc;
    private zzjw zzd;
    private int zze;
    private zzlg zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzep(zznx zznxVar) throws GeneralSecurityException {
        String zzf = zznxVar.zzf();
        this.zza = zzf;
        if (zzf.equals(zzcc.zzb)) {
            try {
                zzkx zzd = zzkx.zzd(zznxVar.zze(), zzacs.zza());
                this.zzc = (zzku) zzbz.zzd(zznxVar);
                this.zzb = zzd.zza();
                return;
            } catch (zzadn e) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesGcmKeyFormat", e);
            }
        }
        if (zzf.equals(zzcc.zza)) {
            try {
                zzjz zzc = zzjz.zzc(zznxVar.zze(), zzacs.zza());
                this.zzd = (zzjw) zzbz.zzd(zznxVar);
                this.zze = zzc.zzd().zza();
                this.zzb = this.zze + zzc.zze().zza();
                return;
            } catch (zzadn e2) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesCtrHmacAeadKeyFormat", e2);
            }
        }
        if (zzf.equals(zzea.zza)) {
            try {
                zzlj zzd2 = zzlj.zzd(zznxVar.zze(), zzacs.zza());
                this.zzf = (zzlg) zzbz.zzd(zznxVar);
                this.zzb = zzd2.zza();
                return;
            } catch (zzadn e3) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesCtrHmacAeadKeyFormat", e3);
            }
        }
        throw new GeneralSecurityException("unsupported AEAD DEM key type: ".concat(String.valueOf(zzf)));
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzps
    public final int zza() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzps
    public final zzfk zzb(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length != this.zzb) {
            throw new GeneralSecurityException("Symmetric key has incorrect length");
        }
        if (this.zza.equals(zzcc.zzb)) {
            zzkt zzb = zzku.zzb();
            zzb.zzh(this.zzc);
            zzb.zza(zzacc.zzo(bArr, 0, this.zzb));
            return new zzfk((zzap) zzbz.zzi(this.zza, (zzku) zzb.zzi(), zzap.class));
        }
        if (this.zza.equals(zzcc.zza)) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, this.zze);
            byte[] copyOfRange2 = Arrays.copyOfRange(bArr, this.zze, this.zzb);
            zzkb zzb2 = zzkc.zzb();
            zzb2.zzh(this.zzd.zze());
            zzb2.zza(zzacc.zzn(copyOfRange));
            zzkc zzkcVar = (zzkc) zzb2.zzi();
            zzms zzb3 = zzmt.zzb();
            zzb3.zzh(this.zzd.zzf());
            zzb3.zza(zzacc.zzn(copyOfRange2));
            zzmt zzmtVar = (zzmt) zzb3.zzi();
            zzjv zzb4 = zzjw.zzb();
            zzb4.zzc(this.zzd.zza());
            zzb4.zza(zzkcVar);
            zzb4.zzb(zzmtVar);
            return new zzfk((zzap) zzbz.zzi(this.zza, (zzjw) zzb4.zzi(), zzap.class));
        }
        if (this.zza.equals(zzea.zza)) {
            zzlf zzb5 = zzlg.zzb();
            zzb5.zzh(this.zzf);
            zzb5.zza(zzacc.zzo(bArr, 0, this.zzb));
            return new zzfk((zzat) zzbz.zzi(this.zza, (zzlg) zzb5.zzi(), zzat.class));
        }
        throw new GeneralSecurityException("unknown DEM key type");
    }
}
