package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.logging.Logger;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
final class zzys extends zzxa {
    final /* synthetic */ zzyv zza;
    private final String zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzys(zzyv zzyvVar, zzxa zzxaVar, String str) {
        super(zzxaVar);
        this.zza = zzyvVar;
        this.zzb = str;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzxa
    public final void zzb(String str) {
        Logger logger;
        HashMap hashMap;
        logger = zzyv.zza;
        logger.d("onCodeSent", new Object[0]);
        hashMap = this.zza.zzd;
        zzyu zzyuVar = (zzyu) hashMap.get(this.zzb);
        if (zzyuVar == null) {
            return;
        }
        Iterator it = zzyuVar.zzb.iterator();
        while (it.hasNext()) {
            ((zzxa) it.next()).zzb(str);
        }
        zzyuVar.zzg = true;
        zzyuVar.zzd = str;
        if (zzyuVar.zza <= 0) {
            this.zza.zzh(this.zzb);
        } else if (!zzyuVar.zzc) {
            this.zza.zzn(this.zzb);
        } else {
            if (zzag.zzd(zzyuVar.zze)) {
                return;
            }
            zzyv.zze(this.zza, this.zzb);
        }
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzxa
    public final void zzh(Status status) {
        Logger logger;
        HashMap hashMap;
        logger = zzyv.zza;
        logger.e("SMS verification code request failed: " + CommonStatusCodes.getStatusCodeString(status.getStatusCode()) + " " + status.getStatusMessage(), new Object[0]);
        hashMap = this.zza.zzd;
        zzyu zzyuVar = (zzyu) hashMap.get(this.zzb);
        if (zzyuVar == null) {
            return;
        }
        Iterator it = zzyuVar.zzb.iterator();
        while (it.hasNext()) {
            ((zzxa) it.next()).zzh(status);
        }
        this.zza.zzj(this.zzb);
    }
}
