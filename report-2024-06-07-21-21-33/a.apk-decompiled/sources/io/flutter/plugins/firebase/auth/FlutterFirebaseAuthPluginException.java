package io.flutter.plugins.firebase.auth;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes2.dex */
public class FlutterFirebaseAuthPluginException extends Exception {
    private Map<String, Object> additionalData;
    private final String code;
    private final String message;

    FlutterFirebaseAuthPluginException(String str, String str2) {
        super(str2, null);
        this.additionalData = new HashMap();
        this.code = str;
        this.message = str2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlutterFirebaseAuthPluginException(String str, String str2, Map<String, Object> map) {
        super(str2, null);
        new HashMap();
        this.code = str;
        this.message = str2;
        this.additionalData = map;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlutterFirebaseAuthPluginException(Exception exc, Throwable th) {
        super(exc.getMessage(), th);
        this.additionalData = new HashMap();
        String message = exc.getMessage();
        HashMap hashMap = new HashMap();
        String errorCode = exc instanceof FirebaseAuthException ? ((FirebaseAuthException) exc).getErrorCode() : "UNKNOWN";
        message = exc instanceof FirebaseAuthWeakPasswordException ? ((FirebaseAuthWeakPasswordException) exc).getReason() : message;
        if (exc instanceof FirebaseAuthUserCollisionException) {
            FirebaseAuthUserCollisionException firebaseAuthUserCollisionException = (FirebaseAuthUserCollisionException) exc;
            String email = firebaseAuthUserCollisionException.getEmail();
            if (email != null) {
                hashMap.put("email", email);
            }
            AuthCredential updatedCredential = firebaseAuthUserCollisionException.getUpdatedCredential();
            if (updatedCredential != null) {
                hashMap.put(Constants.AUTH_CREDENTIAL, FlutterFirebaseAuthPlugin.parseAuthCredential(updatedCredential));
            }
        }
        this.code = errorCode;
        this.message = message;
        this.additionalData = hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FlutterFirebaseAuthPluginException noUser() {
        return new FlutterFirebaseAuthPluginException("NO_CURRENT_USER", "No user currently signed in.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FlutterFirebaseAuthPluginException invalidCredential() {
        return new FlutterFirebaseAuthPluginException("INVALID_CREDENTIAL", "The supplied auth credential is malformed, has expired or is not currently supported.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FlutterFirebaseAuthPluginException noSuchProvider() {
        return new FlutterFirebaseAuthPluginException("NO_SUCH_PROVIDER", "User was not linked to an account with the given provider.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FlutterFirebaseAuthPluginException alreadyLinkedProvider() {
        return new FlutterFirebaseAuthPluginException("PROVIDER_ALREADY_LINKED", "User has already been linked to the given provider.");
    }

    public String getCode() {
        return this.code.toLowerCase(Locale.ROOT).replace("error_", "").replace("_", "-");
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    public Map<String, Object> getAdditionalData() {
        return this.additionalData;
    }
}
