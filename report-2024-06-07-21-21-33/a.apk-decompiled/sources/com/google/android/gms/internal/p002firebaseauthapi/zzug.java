package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.firebase.auth.internal.zzai;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zzug implements zzyg {
    final /* synthetic */ zzaay zza;
    final /* synthetic */ zzxa zzb;
    final /* synthetic */ zzvf zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzug(zzvf zzvfVar, zzaay zzaayVar, zzxa zzxaVar) {
        this.zzc = zzvfVar;
        this.zza = zzaayVar;
        this.zzb = zzxaVar;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzyf
    public final void zza(String str) {
        this.zzb.zzh(zzai.zza(str));
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzyg
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzyh zzyhVar;
        this.zza.zzd(true);
        this.zza.zzc(((zzzy) obj).zze());
        zzyhVar = this.zzc.zza;
        zzyhVar.zzq(this.zza, new zzuf(this, this));
    }
}
