package com.google.firebase.analytics;

import com.google.android.gms.internal.measurement.zzef;
import java.util.concurrent.Callable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-api@@21.2.0 */
/* loaded from: classes.dex */
public final class zzb implements Callable {
    final /* synthetic */ FirebaseAnalytics zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzb(FirebaseAnalytics firebaseAnalytics) {
        this.zza = firebaseAnalytics;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() throws Exception {
        zzef zzefVar;
        zzefVar = this.zza.zzb;
        return zzefVar.zzl();
    }
}
