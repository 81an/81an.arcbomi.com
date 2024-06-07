package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Comparator;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
final class zzabu implements Comparator {
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        zzacc zzaccVar = (zzacc) obj;
        zzacc zzaccVar2 = (zzacc) obj2;
        zzabt zzabtVar = new zzabt(zzaccVar);
        zzabt zzabtVar2 = new zzabt(zzaccVar2);
        while (zzabtVar.hasNext() && zzabtVar2.hasNext()) {
            int compareTo = Integer.valueOf(zzabtVar.zza() & 255).compareTo(Integer.valueOf(zzabtVar2.zza() & 255));
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return Integer.valueOf(zzaccVar.zzd()).compareTo(Integer.valueOf(zzaccVar2.zzd()));
    }
}
