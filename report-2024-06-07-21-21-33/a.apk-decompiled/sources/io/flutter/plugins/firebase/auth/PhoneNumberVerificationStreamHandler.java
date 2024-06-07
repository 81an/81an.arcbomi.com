package io.flutter.plugins.firebase.auth;

import android.app.Activity;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.MultiFactorSession;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import io.flutter.plugin.common.EventChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
public class PhoneNumberVerificationStreamHandler implements EventChannel.StreamHandler {
    private static final HashMap<Integer, PhoneAuthProvider.ForceResendingToken> forceResendingTokens = new HashMap<>();
    final AtomicReference<Activity> activityRef;
    String autoRetrievedSmsCodeForTesting;
    private EventChannel.EventSink eventSink;
    final FirebaseAuth firebaseAuth;
    Integer forceResendingToken;
    final PhoneMultiFactorInfo multiFactorInfo;
    final MultiFactorSession multiFactorSession;
    final OnCredentialsListener onCredentialsListener;
    final String phoneNumber;
    final int timeout;

    /* loaded from: classes2.dex */
    interface OnCredentialsListener {
        void onCredentialsReceived(PhoneAuthCredential phoneAuthCredential);
    }

    public PhoneNumberVerificationStreamHandler(Activity activity, Map<String, Object> map, MultiFactorSession multiFactorSession, PhoneMultiFactorInfo phoneMultiFactorInfo, OnCredentialsListener onCredentialsListener) {
        AtomicReference<Activity> atomicReference = new AtomicReference<>(null);
        this.activityRef = atomicReference;
        atomicReference.set(activity);
        this.multiFactorSession = multiFactorSession;
        this.multiFactorInfo = phoneMultiFactorInfo;
        this.firebaseAuth = FlutterFirebaseAuthPlugin.getAuth(map);
        this.phoneNumber = (String) map.get(Constants.PHONE_NUMBER);
        Object obj = map.get(Constants.TIMEOUT);
        Objects.requireNonNull(obj);
        this.timeout = ((Integer) obj).intValue();
        if (map.containsKey(Constants.AUTO_RETRIEVED_SMS_CODE_FOR_TESTING)) {
            this.autoRetrievedSmsCodeForTesting = (String) map.get(Constants.AUTO_RETRIEVED_SMS_CODE_FOR_TESTING);
        }
        if (map.containsKey(Constants.FORCE_RESENDING_TOKEN)) {
            this.forceResendingToken = (Integer) map.get(Constants.FORCE_RESENDING_TOKEN);
        }
        this.onCredentialsListener = onCredentialsListener;
    }

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onListen(Object obj, EventChannel.EventSink eventSink) {
        PhoneAuthProvider.ForceResendingToken forceResendingToken;
        this.eventSink = eventSink;
        PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() { // from class: io.flutter.plugins.firebase.auth.PhoneNumberVerificationStreamHandler.1
            @Override // com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                int hashCode = phoneAuthCredential.hashCode();
                PhoneNumberVerificationStreamHandler.this.onCredentialsListener.onCredentialsReceived(phoneAuthCredential);
                HashMap hashMap = new HashMap();
                hashMap.put(Constants.TOKEN, Integer.valueOf(hashCode));
                if (phoneAuthCredential.getSmsCode() != null) {
                    hashMap.put(Constants.SMS_CODE, phoneAuthCredential.getSmsCode());
                }
                hashMap.put("name", "Auth#phoneVerificationCompleted");
                if (PhoneNumberVerificationStreamHandler.this.eventSink != null) {
                    PhoneNumberVerificationStreamHandler.this.eventSink.success(hashMap);
                }
            }

            @Override // com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
            public void onVerificationFailed(FirebaseException firebaseException) {
                HashMap hashMap = new HashMap();
                hashMap.put("message", firebaseException.getLocalizedMessage());
                hashMap.put(io.flutter.plugins.firebase.database.Constants.ERROR_DETAILS, FlutterFirebaseAuthPlugin.getExceptionDetails(firebaseException));
                HashMap hashMap2 = new HashMap();
                hashMap2.put("error", hashMap);
                hashMap2.put("name", "Auth#phoneVerificationFailed");
                if (PhoneNumberVerificationStreamHandler.this.eventSink != null) {
                    PhoneNumberVerificationStreamHandler.this.eventSink.success(hashMap2);
                }
            }

            @Override // com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
            public void onCodeSent(String str, PhoneAuthProvider.ForceResendingToken forceResendingToken2) {
                int hashCode = forceResendingToken2.hashCode();
                PhoneNumberVerificationStreamHandler.forceResendingTokens.put(Integer.valueOf(hashCode), forceResendingToken2);
                HashMap hashMap = new HashMap();
                hashMap.put(Constants.VERIFICATION_ID, str);
                hashMap.put(Constants.FORCE_RESENDING_TOKEN, Integer.valueOf(hashCode));
                hashMap.put("name", "Auth#phoneCodeSent");
                if (PhoneNumberVerificationStreamHandler.this.eventSink != null) {
                    PhoneNumberVerificationStreamHandler.this.eventSink.success(hashMap);
                }
            }

            @Override // com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
            public void onCodeAutoRetrievalTimeOut(String str) {
                HashMap hashMap = new HashMap();
                hashMap.put(Constants.VERIFICATION_ID, str);
                hashMap.put("name", "Auth#phoneCodeAutoRetrievalTimeout");
                if (PhoneNumberVerificationStreamHandler.this.eventSink != null) {
                    PhoneNumberVerificationStreamHandler.this.eventSink.success(hashMap);
                }
            }
        };
        if (this.autoRetrievedSmsCodeForTesting != null) {
            this.firebaseAuth.getFirebaseAuthSettings().setAutoRetrievedSmsCodeForPhoneNumber(this.phoneNumber, this.autoRetrievedSmsCodeForTesting);
        }
        PhoneAuthOptions.Builder builder = new PhoneAuthOptions.Builder(this.firebaseAuth);
        builder.setActivity(this.activityRef.get());
        builder.setCallbacks(onVerificationStateChangedCallbacks);
        String str = this.phoneNumber;
        if (str != null) {
            builder.setPhoneNumber(str);
        }
        MultiFactorSession multiFactorSession = this.multiFactorSession;
        if (multiFactorSession != null) {
            builder.setMultiFactorSession(multiFactorSession);
        }
        PhoneMultiFactorInfo phoneMultiFactorInfo = this.multiFactorInfo;
        if (phoneMultiFactorInfo != null) {
            builder.setMultiFactorHint(phoneMultiFactorInfo);
        }
        builder.setTimeout(Long.valueOf(this.timeout), TimeUnit.MILLISECONDS);
        Integer num = this.forceResendingToken;
        if (num != null && (forceResendingToken = forceResendingTokens.get(num)) != null) {
            builder.setForceResendingToken(forceResendingToken);
        }
        PhoneAuthProvider.verifyPhoneNumber(builder.build());
    }

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onCancel(Object obj) {
        this.eventSink = null;
        this.activityRef.set(null);
    }
}
