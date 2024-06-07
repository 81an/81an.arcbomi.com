package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zzac implements zzae {
    final /* synthetic */ zzq zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzac(zzq zzqVar) {
        this.zza = zzqVar;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzae
    public final /* bridge */ /* synthetic */ Iterator zza(zzaf zzafVar, CharSequence charSequence) {
        return new zzab(this, zzafVar, charSequence, this.zza.zza(charSequence));
    }
}
