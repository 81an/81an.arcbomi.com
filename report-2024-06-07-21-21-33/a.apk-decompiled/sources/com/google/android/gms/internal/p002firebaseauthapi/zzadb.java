package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzadb;
import com.google.android.gms.internal.p002firebaseauthapi.zzadf;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public class zzadb<MessageType extends zzadf<MessageType, BuilderType>, BuilderType extends zzadb<MessageType, BuilderType>> extends zzabl<MessageType, BuilderType> {
    protected zzadf zza;
    private final zzadf zzb;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzadb(MessageType messagetype) {
        this.zzb = messagetype;
        if (!messagetype.zzK()) {
            this.zza = messagetype.zzw();
            return;
        }
        throw new IllegalArgumentException("Default instance must be immutable.");
    }

    private static void zza(Object obj, Object obj2) {
        zzaes.zza().zzb(obj.getClass()).zzg(obj, obj2);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzael
    public final /* synthetic */ zzaek zzL() {
        throw null;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzabl
    /* renamed from: zzg, reason: merged with bridge method [inline-methods] */
    public final zzadb clone() {
        zzadb zzadbVar = (zzadb) this.zzb.zzj(5, null, null);
        zzadbVar.zza = zzk();
        return zzadbVar;
    }

    public final zzadb zzh(zzadf zzadfVar) {
        if (!this.zzb.equals(zzadfVar)) {
            if (!this.zza.zzK()) {
                zzn();
            }
            zza(this.zza, zzadfVar);
        }
        return this;
    }

    public final MessageType zzi() {
        MessageType zzk = zzk();
        if (zzk.zzJ()) {
            return zzk;
        }
        throw new zzafm(zzk);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaej
    /* renamed from: zzj, reason: merged with bridge method [inline-methods] */
    public MessageType zzk() {
        if (!this.zza.zzK()) {
            return (MessageType) this.zza;
        }
        this.zza.zzE();
        return (MessageType) this.zza;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzm() {
        if (this.zza.zzK()) {
            return;
        }
        zzn();
    }

    protected void zzn() {
        zzadf zzw = this.zzb.zzw();
        zza(zzw, this.zza);
        this.zza = zzw;
    }
}
