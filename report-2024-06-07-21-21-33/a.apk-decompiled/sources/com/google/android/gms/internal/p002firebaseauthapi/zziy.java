package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
final class zziy implements zzbv {
    private static final Logger zza = Logger.getLogger(zziy.class.getName());
    private static final byte[] zzb = {0};

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzbv
    public final Class zza() {
        return zzbm.class;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzbv
    public final Class zzb() {
        return zzbm.class;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzbv
    public final /* bridge */ /* synthetic */ Object zzc(zzbu zzbuVar) throws GeneralSecurityException {
        Iterator it = zzbuVar.zzd().iterator();
        while (it.hasNext()) {
            for (zzbq zzbqVar : (List) it.next()) {
                if (zzbqVar.zzb() instanceof zziu) {
                    zziu zziuVar = (zziu) zzbqVar.zzb();
                    zzqv zzb2 = zzqv.zzb(zzbqVar.zzf());
                    if (!zzb2.equals(zziuVar.zzc())) {
                        throw new GeneralSecurityException("Mac Key with parameters " + String.valueOf(zziuVar.zzb()) + " has wrong output prefix (" + zziuVar.zzc().toString() + ") instead of (" + zzb2.toString() + ")");
                    }
                }
            }
        }
        return new zzix(zzbuVar, null);
    }
}
