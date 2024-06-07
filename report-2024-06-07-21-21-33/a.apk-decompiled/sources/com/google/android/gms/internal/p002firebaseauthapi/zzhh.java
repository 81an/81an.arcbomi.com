package com.google.android.gms.internal.p002firebaseauthapi;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zzhh {
    private final Map zza;
    private final Map zzb;
    private final Map zzc;
    private final Map zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzhh(zzhb zzhbVar, zzhg zzhgVar) {
        this.zza = new HashMap(zzhb.zzf(zzhbVar));
        this.zzb = new HashMap(zzhb.zze(zzhbVar));
        this.zzc = new HashMap(zzhb.zzh(zzhbVar));
        this.zzd = new HashMap(zzhb.zzg(zzhbVar));
    }

    public final zzaw zza(zzha zzhaVar, @Nullable zzca zzcaVar) throws GeneralSecurityException {
        zzhd zzhdVar = new zzhd(zzhaVar.getClass(), zzhaVar.zzd(), null);
        if (!this.zzb.containsKey(zzhdVar)) {
            throw new GeneralSecurityException("No Key Parser for requested key type " + zzhdVar.toString() + " available");
        }
        return ((zzfv) this.zzb.get(zzhdVar)).zza(zzhaVar, zzcaVar);
    }
}
