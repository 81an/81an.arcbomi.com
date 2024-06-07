package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.util.Strings;
import io.flutter.plugins.firebase.auth.Constants;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zzaar implements zzxn {
    private static final String zza = "zzaar";
    private String zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private long zzf;

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzxn
    public final /* bridge */ /* synthetic */ zzxn zza(String str) throws zzvg {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = Strings.emptyToNull(jSONObject.optString(Constants.ID_TOKEN, null));
            this.zzc = Strings.emptyToNull(jSONObject.optString(Constants.DISPLAY_NAME, null));
            this.zzd = Strings.emptyToNull(jSONObject.optString("email", null));
            this.zze = Strings.emptyToNull(jSONObject.optString(Constants.REFRESH_TOKEN, null));
            this.zzf = jSONObject.optLong("expiresIn", 0L);
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzabk.zza(e, zza, str);
        }
    }

    public final long zzb() {
        return this.zzf;
    }

    public final String zzc() {
        return this.zzb;
    }

    public final String zzd() {
        return this.zze;
    }
}
