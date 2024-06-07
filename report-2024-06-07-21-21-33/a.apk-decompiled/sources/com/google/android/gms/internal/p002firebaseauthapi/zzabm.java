package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.internal.p002firebaseauthapi.zzabl;
import com.google.android.gms.internal.p002firebaseauthapi.zzabm;
import java.io.IOException;
import java.io.OutputStream;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public abstract class zzabm<MessageType extends zzabm<MessageType, BuilderType>, BuilderType extends zzabl<MessageType, BuilderType>> implements zzaek {
    protected int zza = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public int zzn(zzaew zzaewVar) {
        throw null;
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaek
    public final zzacc zzo() {
        try {
            int zzs = zzs();
            zzacc zzaccVar = zzacc.zzb;
            byte[] bArr = new byte[zzs];
            zzacn zzG = zzacn.zzG(bArr);
            zzI(zzG);
            zzG.zzI();
            return new zzabz(bArr);
        } catch (IOException e) {
            throw new RuntimeException("Serializing " + getClass().getName() + " to a ByteString threw an IOException (should never happen).", e);
        }
    }

    public final void zzp(OutputStream outputStream) throws IOException {
        zzacn zzH = zzacn.zzH(outputStream, zzacn.zzB(zzs()));
        zzI(zzH);
        zzH.zzN();
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzaek
    public final byte[] zzq() {
        try {
            byte[] bArr = new byte[zzs()];
            zzacn zzG = zzacn.zzG(bArr);
            zzI(zzG);
            zzG.zzI();
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException("Serializing " + getClass().getName() + " to a byte array threw an IOException (should never happen).", e);
        }
    }
}
