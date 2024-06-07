package com.google.android.gms.internal.p002firebaseauthapi;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zzdc implements zzap {
    private static final byte[] zza = new byte[0];
    private final zznx zzb;
    private final zzap zzc;

    public zzdc(zznx zznxVar, zzap zzapVar) {
        this.zzb = zznxVar;
        this.zzc = zzapVar;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzap
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        try {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            int i = wrap.getInt();
            if (i <= 0 || i > bArr.length - 4) {
                throw new GeneralSecurityException("invalid ciphertext");
            }
            byte[] bArr3 = new byte[i];
            wrap.get(bArr3, 0, i);
            byte[] bArr4 = new byte[wrap.remaining()];
            wrap.get(bArr4, 0, wrap.remaining());
            return ((zzap) zzbz.zzj(this.zzb.zzf(), this.zzc.zza(bArr3, zza), zzap.class)).zza(bArr4, bArr2);
        } catch (IndexOutOfBoundsException | NegativeArraySizeException | BufferUnderflowException e) {
            throw new GeneralSecurityException("invalid ciphertext", e);
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzap
    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        throw null;
    }
}
