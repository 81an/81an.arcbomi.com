package com.google.android.gms.internal.p002firebaseauthapi;

import com.google.android.gms.common.util.Strings;
import io.flutter.plugins.firebase.auth.Constants;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zzaap implements zzxn {
    private static final String zza = "zzaap";
    private String zzb;
    private String zzc;
    private Boolean zzd;
    private String zze;
    private String zzf;
    private zzaag zzg;
    private String zzh;
    private String zzi;
    private long zzj;

    @Override // com.google.android.gms.internal.p002firebaseauthapi.zzxn
    public final /* bridge */ /* synthetic */ zzxn zza(String str) throws zzvg {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.zzb = Strings.emptyToNull(jSONObject.optString("email", null));
            this.zzc = Strings.emptyToNull(jSONObject.optString("passwordHash", null));
            this.zzd = Boolean.valueOf(jSONObject.optBoolean(Constants.EMAIL_VERIFIED, false));
            this.zze = Strings.emptyToNull(jSONObject.optString(Constants.DISPLAY_NAME, null));
            this.zzf = Strings.emptyToNull(jSONObject.optString("photoUrl", null));
            this.zzg = zzaag.zza(jSONObject.optJSONArray("providerUserInfo"));
            this.zzh = Strings.emptyToNull(jSONObject.optString(Constants.ID_TOKEN, null));
            this.zzi = Strings.emptyToNull(jSONObject.optString(Constants.REFRESH_TOKEN, null));
            this.zzj = jSONObject.optLong("expiresIn", 0L);
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzabk.zza(e, zza, str);
        }
    }

    public final long zzb() {
        return this.zzj;
    }

    public final String zzc() {
        return this.zzb;
    }

    public final String zzd() {
        return this.zzh;
    }

    public final String zze() {
        return this.zzi;
    }

    public final List zzf() {
        zzaag zzaagVar = this.zzg;
        if (zzaagVar != null) {
            return zzaagVar.zzc();
        }
        return null;
    }
}
