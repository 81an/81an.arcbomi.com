package com.google.android.gms.internal.p002firebaseauthapi;

import android.text.TextUtils;
import com.google.android.gms.common.util.Strings;
import io.flutter.plugins.firebase.auth.Constants;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zzabf implements zzxn {
    private static final String zza = "zzabf";
    private String zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private String zzf;
    private String zzg;
    private long zzh;
    private List zzi;
    private String zzj;

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzxn
    public final /* bridge */ /* synthetic */ zzxn zza(String str) throws zzvg {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = Strings.emptyToNull(jSONObject.optString("localId", null));
            this.zzc = Strings.emptyToNull(jSONObject.optString("email", null));
            this.zzd = Strings.emptyToNull(jSONObject.optString(Constants.DISPLAY_NAME, null));
            this.zze = Strings.emptyToNull(jSONObject.optString(Constants.ID_TOKEN, null));
            this.zzf = Strings.emptyToNull(jSONObject.optString("photoUrl", null));
            this.zzg = Strings.emptyToNull(jSONObject.optString(Constants.REFRESH_TOKEN, null));
            this.zzh = jSONObject.optLong("expiresIn", 0L);
            this.zzi = zzaac.zzf(jSONObject.optJSONArray("mfaInfo"));
            this.zzj = jSONObject.optString("mfaPendingCredential", null);
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzabk.zza(e, zza, str);
        }
    }

    public final long zzb() {
        return this.zzh;
    }

    public final String zzc() {
        return this.zze;
    }

    public final String zzd() {
        return this.zzj;
    }

    public final String zze() {
        return this.zzg;
    }

    public final List zzf() {
        return this.zzi;
    }

    public final boolean zzg() {
        return !TextUtils.isEmpty(this.zzj);
    }
}
