package com.google.firebase.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.internal.IdTokenListener;
import com.google.firebase.internal.InternalTokenResult;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zzl implements Runnable {
    final /* synthetic */ FirebaseAuth zza;
    final /* synthetic */ InternalTokenResult zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzl(FirebaseAuth firebaseAuth, InternalTokenResult internalTokenResult) {
        this.zza = firebaseAuth;
        this.zzb = internalTokenResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        List list;
        List list2;
        list = this.zza.zzc;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((IdTokenListener) it.next()).onIdTokenChanged(this.zzb);
        }
        list2 = this.zza.zzb;
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            ((FirebaseAuth.IdTokenListener) it2.next()).onIdTokenChanged(this.zza);
        }
    }
}
