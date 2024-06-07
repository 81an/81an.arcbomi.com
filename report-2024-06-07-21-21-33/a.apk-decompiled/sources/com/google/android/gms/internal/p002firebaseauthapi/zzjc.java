package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.Map;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zzjc {
    public static final zzjc zza = new zzja().zza();
    private final Map zzb;

    public final boolean equals(Object obj) {
        if (obj instanceof zzjc) {
            return this.zzb.equals(((zzjc) obj).zzb);
        }
        return false;
    }

    public final int hashCode() {
        return this.zzb.hashCode();
    }

    public final String toString() {
        return this.zzb.toString();
    }

    public final Map zza() {
        return this.zzb;
    }
}
