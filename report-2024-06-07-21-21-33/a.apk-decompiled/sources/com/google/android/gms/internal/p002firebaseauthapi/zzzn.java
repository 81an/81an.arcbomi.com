package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.internal.Preconditions;
import io.flutter.plugins.firebase.auth.Constants;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zzzn implements zzxm {
    private final String zza = zzzm.REFRESH_TOKEN.toString();
    private final String zzb;

    public zzzn(String str) {
        this.zzb = Preconditions.checkNotEmpty(str);
    }

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzxm
    public final String zza() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("grantType", this.zza);
        jSONObject.put(Constants.REFRESH_TOKEN, this.zzb);
        return jSONObject.toString();
    }
}
