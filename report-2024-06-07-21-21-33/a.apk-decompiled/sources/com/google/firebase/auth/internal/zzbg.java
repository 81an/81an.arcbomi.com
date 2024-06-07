package com.google.firebase.auth.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.p002firebaseauthapi.zzqx;
import com.google.android.gms.internal.p002firebaseauthapi.zzzy;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.MultiFactorInfo;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
/* loaded from: classes.dex */
public final class zzbg {
    private final Context zza;
    private final String zzb;
    private final SharedPreferences zzc;
    private final Logger zzd;

    public zzbg(Context context, String str) {
        Preconditions.checkNotNull(context);
        String checkNotEmpty = Preconditions.checkNotEmpty(str);
        this.zzb = checkNotEmpty;
        Context applicationContext = context.getApplicationContext();
        this.zza = applicationContext;
        this.zzc = applicationContext.getSharedPreferences(String.format("com.google.firebase.auth.api.Store.%s", checkNotEmpty), 0);
        this.zzd = new Logger("StorageHelpers", new String[0]);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00ce A[Catch: zzqx -> 0x0135, IllegalArgumentException -> 0x0137, ArrayIndexOutOfBoundsException -> 0x0139, JSONException -> 0x013b, TRY_ENTER, TryCatch #2 {JSONException -> 0x013b, blocks: (B:3:0x0008, B:6:0x0025, B:10:0x003b, B:13:0x0079, B:16:0x0080, B:17:0x008c, B:20:0x008d, B:22:0x009c, B:24:0x00a5, B:25:0x00a8, B:27:0x00b1, B:31:0x00ce, B:36:0x00d1, B:38:0x00d7, B:40:0x00dd, B:41:0x00e3, B:43:0x00e9, B:45:0x0100, B:47:0x0108, B:49:0x012b, B:51:0x0122, B:52:0x0129, B:56:0x0131), top: B:2:0x0008 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.google.firebase.auth.internal.zzx zzf(org.json.JSONObject r27) {
        /*
            Method dump skipped, instructions count: 325
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.internal.zzbg.zzf(org.json.JSONObject):com.google.firebase.auth.internal.zzx");
    }

    public final FirebaseUser zza() {
        String string = this.zzc.getString("com.google.firebase.auth.FIREBASE_USER", null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            if (jSONObject.has("type") && "com.google.firebase.auth.internal.DefaultFirebaseUser".equalsIgnoreCase(jSONObject.optString("type"))) {
                return zzf(jSONObject);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public final zzzy zzb(FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(firebaseUser);
        String string = this.zzc.getString(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", firebaseUser.getUid()), null);
        if (string != null) {
            return zzzy.zzd(string);
        }
        return null;
    }

    public final void zzc(String str) {
        this.zzc.edit().remove(str).apply();
    }

    public final void zzd(FirebaseUser firebaseUser) {
        String str;
        Preconditions.checkNotNull(firebaseUser);
        JSONObject jSONObject = new JSONObject();
        if (zzx.class.isAssignableFrom(firebaseUser.getClass())) {
            zzx zzxVar = (zzx) firebaseUser;
            try {
                jSONObject.put("cachedTokenState", zzxVar.zzf());
                jSONObject.put("applicationName", zzxVar.zza().getName());
                jSONObject.put("type", "com.google.firebase.auth.internal.DefaultFirebaseUser");
                if (zzxVar.zzo() != null) {
                    JSONArray jSONArray = new JSONArray();
                    List zzo = zzxVar.zzo();
                    int size = zzo.size();
                    if (zzo.size() > 30) {
                        this.zzd.w("Provider user info list size larger than max size, truncating list to %d. Actual list size: %d", 30, Integer.valueOf(zzo.size()));
                        size = 30;
                    }
                    for (int i = 0; i < size; i++) {
                        jSONArray.put(((zzt) zzo.get(i)).zzb());
                    }
                    jSONObject.put("userInfos", jSONArray);
                }
                jSONObject.put("anonymous", zzxVar.isAnonymous());
                jSONObject.put("version", "2");
                if (zzxVar.getMetadata() != null) {
                    jSONObject.put("userMetadata", ((zzz) zzxVar.getMetadata()).zza());
                }
                List<MultiFactorInfo> enrolledFactors = new zzac(zzxVar).getEnrolledFactors();
                if (!enrolledFactors.isEmpty()) {
                    JSONArray jSONArray2 = new JSONArray();
                    for (int i2 = 0; i2 < enrolledFactors.size(); i2++) {
                        jSONArray2.put(enrolledFactors.get(i2).toJson());
                    }
                    jSONObject.put("userMultiFactorInfo", jSONArray2);
                }
                str = jSONObject.toString();
            } catch (Exception e) {
                this.zzd.wtf("Failed to turn object into JSON", e, new Object[0]);
                throw new zzqx(e);
            }
        } else {
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.zzc.edit().putString("com.google.firebase.auth.FIREBASE_USER", str).apply();
    }

    public final void zze(FirebaseUser firebaseUser, zzzy zzzyVar) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzzyVar);
        this.zzc.edit().putString(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", firebaseUser.getUid()), zzzyVar.zzh()).apply();
    }
}
