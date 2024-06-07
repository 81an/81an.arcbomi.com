package com.google.android.gms.internal.p002firebaseauthapi;

import android.os.Build;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
final class zza {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zza() {
        return Build.VERSION.SDK_INT >= 33 || Build.VERSION.CODENAME.charAt(0) == 'T';
    }
}
