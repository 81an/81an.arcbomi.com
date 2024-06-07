package com.google.android.gms.internal.p002firebaseauthapi;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
final class zzaj extends zzah {
    private final zzal zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaj(zzal zzalVar, int i) {
        super(zzalVar.size(), i);
        this.zza = zzalVar;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzah
    protected final Object zza(int i) {
        return this.zza.get(i);
    }
}
