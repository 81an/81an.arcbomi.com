package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
final class zzcf implements zzap {
    private final zzbu zza;
    private final zzjd zzb;
    private final zzjd zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzcf(zzbu zzbuVar, zzce zzceVar) {
        zzjd zzjdVar;
        this.zza = zzbuVar;
        if (zzbuVar.zzf()) {
            zzje zzb = zzgm.zza().zzb();
            zzjj zza = zzgj.zza(zzbuVar);
            this.zzb = zzb.zza(zza, "aead", "encrypt");
            zzjdVar = zzb.zza(zza, "aead", "decrypt");
        } else {
            zzjdVar = zzgj.zza;
            this.zzb = zzjdVar;
        }
        this.zzc = zzjdVar;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzap
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        Logger logger;
        int length = bArr.length;
        if (length > 5) {
            byte[] copyOf = Arrays.copyOf(bArr, 5);
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 5, length);
            for (zzbq zzbqVar : this.zza.zze(copyOf)) {
                try {
                    byte[] zza = ((zzap) zzbqVar.zze()).zza(copyOfRange, bArr2);
                    zzbqVar.zza();
                    int length2 = copyOfRange.length;
                    return zza;
                } catch (GeneralSecurityException e) {
                    logger = zzcg.zza;
                    logger.logp(Level.INFO, "com.google.crypto.tink.aead.AeadWrapper$WrappedAead", "decrypt", "ciphertext prefix matches a key, but cannot decrypt: ".concat(e.toString()));
                }
            }
        }
        for (zzbq zzbqVar2 : this.zza.zze(zzas.zza)) {
            try {
                byte[] zza2 = ((zzap) zzbqVar2.zze()).zza(bArr, bArr2);
                zzbqVar2.zza();
                int length3 = bArr.length;
                return zza2;
            } catch (GeneralSecurityException unused) {
            }
        }
        throw new GeneralSecurityException("decryption failed");
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzap
    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        throw null;
    }
}
