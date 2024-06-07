package com.google.firebase.auth;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public interface AdditionalUserInfo extends SafeParcelable {
    Map<String, Object> getProfile();

    String getProviderId();

    String getUsername();

    boolean isNewUser();
}
