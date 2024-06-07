package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
final class zzaef {
    public static final int zza(int i, Object obj, Object obj2) {
        zzaee zzaeeVar = (zzaee) obj;
        if (zzaeeVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzaeeVar.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw null;
    }

    public static final boolean zzb(Object obj) {
        return !((zzaee) obj).zze();
    }

    public static final Object zzc(Object obj, Object obj2) {
        zzaee zzaeeVar = (zzaee) obj;
        zzaee zzaeeVar2 = (zzaee) obj2;
        if (!zzaeeVar2.isEmpty()) {
            if (!zzaeeVar.zze()) {
                zzaeeVar = zzaeeVar.zzb();
            }
            zzaeeVar.zzd(zzaeeVar2);
        }
        return zzaeeVar;
    }
}
