package io.flutter.plugins.firebase.auth;

import android.app.Activity;
import android.net.Uri;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.api.Service;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.ActionCodeEmailInfo;
import com.google.firebase.auth.ActionCodeInfo;
import com.google.firebase.auth.ActionCodeResult;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AdditionalUserInfo;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthMultiFactorException;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.GithubAuthProvider;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.MultiFactor;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.MultiFactorResolver;
import com.google.firebase.auth.MultiFactorSession;
import com.google.firebase.auth.OAuthCredential;
import com.google.firebase.auth.OAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.PhoneMultiFactorGenerator;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.auth.TwitterAuthProvider;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;
import com.google.protobuf.DescriptorProtos;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth;
import io.flutter.plugins.firebase.auth.PhoneNumberVerificationStreamHandler;
import io.flutter.plugins.firebase.core.FlutterFirebasePlugin;
import io.flutter.plugins.firebase.core.FlutterFirebasePluginRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import kotlin.text.Typography;

/* loaded from: classes2.dex */
public class FlutterFirebaseAuthPlugin implements FlutterFirebasePlugin, MethodChannel.MethodCallHandler, FlutterPlugin, ActivityAware, GeneratedAndroidFirebaseAuth.MultiFactorUserHostApi, GeneratedAndroidFirebaseAuth.MultiFactoResolverHostApi {
    private static final String METHOD_CHANNEL_NAME = "plugins.flutter.io/firebase_auth";
    static final HashMap<Integer, AuthCredential> authCredentials = new HashMap<>();
    private Activity activity;
    private MethodChannel channel;
    private BinaryMessenger messenger;
    private final Map<EventChannel, EventChannel.StreamHandler> streamHandlers = new HashMap();
    private final Map<String, Map<String, MultiFactor>> multiFactorUserMap = new HashMap();
    private final Map<String, MultiFactorSession> multiFactorSessionMap = new HashMap();
    private final Map<String, MultiFactorResolver> multiFactorResolverMap = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, Object> parseAuthCredential(AuthCredential authCredential) {
        if (authCredential == null) {
            return null;
        }
        int hashCode = authCredential.hashCode();
        authCredentials.put(Integer.valueOf(hashCode), authCredential);
        HashMap hashMap = new HashMap();
        hashMap.put(Constants.PROVIDER_ID, authCredential.getProvider());
        hashMap.put(Constants.SIGN_IN_METHOD, authCredential.getSignInMethod());
        hashMap.put(Constants.TOKEN, Integer.valueOf(hashCode));
        if (authCredential instanceof OAuthCredential) {
            hashMap.put(Constants.ACCESS_TOKEN, ((OAuthCredential) authCredential).getAccessToken());
        }
        return hashMap;
    }

    private void initInstance(BinaryMessenger binaryMessenger) {
        FlutterFirebasePluginRegistry.registerPlugin(METHOD_CHANNEL_NAME, this);
        MethodChannel methodChannel = new MethodChannel(binaryMessenger, METHOD_CHANNEL_NAME);
        this.channel = methodChannel;
        methodChannel.setMethodCallHandler(this);
        GeneratedAndroidFirebaseAuth.MultiFactorUserHostApi.CC.setup(binaryMessenger, this);
        GeneratedAndroidFirebaseAuth.MultiFactoResolverHostApi.CC.setup(binaryMessenger, this);
        this.messenger = binaryMessenger;
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        initInstance(flutterPluginBinding.getBinaryMessenger());
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        this.channel.setMethodCallHandler(null);
        this.channel = null;
        this.messenger = null;
        GeneratedAndroidFirebaseAuth.MultiFactorUserHostApi.CC.setup(flutterPluginBinding.getBinaryMessenger(), null);
        GeneratedAndroidFirebaseAuth.MultiFactoResolverHostApi.CC.setup(flutterPluginBinding.getBinaryMessenger(), null);
        removeEventListeners();
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        this.activity = activityPluginBinding.getActivity();
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
        this.activity = null;
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
        this.activity = activityPluginBinding.getActivity();
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
        this.activity = null;
    }

    private Activity getActivity() {
        return this.activity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FirebaseAuth getAuth(Map<String, Object> map) {
        Object obj = map.get("appName");
        Objects.requireNonNull(obj);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(FirebaseApp.getInstance((String) obj));
        String str = (String) map.get(Constants.TENANT_ID);
        if (str != null) {
            firebaseAuth.setTenantId(str);
        }
        return firebaseAuth;
    }

    private FirebaseUser getCurrentUser(Map<String, Object> map) {
        Object obj = map.get("appName");
        Objects.requireNonNull(obj);
        return FirebaseAuth.getInstance(FirebaseApp.getInstance((String) obj)).getCurrentUser();
    }

    private FirebaseUser getCurrentUser(String str) {
        return FirebaseAuth.getInstance(FirebaseApp.getInstance(str)).getCurrentUser();
    }

    private AuthCredential getCredential(Map<String, Object> map) throws FlutterFirebaseAuthPluginException {
        Object obj = map.get(Constants.CREDENTIAL);
        Objects.requireNonNull(obj);
        Map map2 = (Map) obj;
        if (map2.get(Constants.TOKEN) != null) {
            AuthCredential authCredential = authCredentials.get(Integer.valueOf(((Integer) map2.get(Constants.TOKEN)).intValue()));
            if (authCredential != null) {
                return authCredential;
            }
            throw FlutterFirebaseAuthPluginException.invalidCredential();
        }
        Object obj2 = map2.get(Constants.SIGN_IN_METHOD);
        Objects.requireNonNull(obj2);
        String str = (String) obj2;
        String str2 = (String) map2.get(Constants.SECRET);
        String str3 = (String) map2.get(Constants.ID_TOKEN);
        String str4 = (String) map2.get(Constants.ACCESS_TOKEN);
        String str5 = (String) map2.get(Constants.RAW_NONCE);
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1830313082:
                if (str.equals("twitter.com")) {
                    c = 0;
                    break;
                }
                break;
            case -1536293812:
                if (str.equals("google.com")) {
                    c = 1;
                    break;
                }
                break;
            case -364826023:
                if (str.equals("facebook.com")) {
                    c = 2;
                    break;
                }
                break;
            case 105516695:
                if (str.equals(Constants.SIGN_IN_METHOD_OAUTH)) {
                    c = 3;
                    break;
                }
                break;
            case 106642798:
                if (str.equals("phone")) {
                    c = 4;
                    break;
                }
                break;
            case 1216985755:
                if (str.equals("password")) {
                    c = 5;
                    break;
                }
                break;
            case 1985010934:
                if (str.equals("github.com")) {
                    c = 6;
                    break;
                }
                break;
            case 2120171958:
                if (str.equals("emailLink")) {
                    c = 7;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                Objects.requireNonNull(str4);
                Objects.requireNonNull(str2);
                return TwitterAuthProvider.getCredential(str4, str2);
            case 1:
                return GoogleAuthProvider.getCredential(str3, str4);
            case 2:
                Objects.requireNonNull(str4);
                return FacebookAuthProvider.getCredential(str4);
            case 3:
                Object obj3 = map2.get(Constants.PROVIDER_ID);
                Objects.requireNonNull(obj3);
                OAuthProvider.CredentialBuilder newCredentialBuilder = OAuthProvider.newCredentialBuilder((String) obj3);
                Objects.requireNonNull(str4);
                newCredentialBuilder.setAccessToken(str4);
                if (str5 == null) {
                    Objects.requireNonNull(str3);
                    newCredentialBuilder.setIdToken(str3);
                } else {
                    Objects.requireNonNull(str3);
                    newCredentialBuilder.setIdTokenWithRawNonce(str3, str5);
                }
                return newCredentialBuilder.build();
            case 4:
                Object obj4 = map2.get(Constants.VERIFICATION_ID);
                Objects.requireNonNull(obj4);
                Object obj5 = map2.get(Constants.SMS_CODE);
                Objects.requireNonNull(obj5);
                return PhoneAuthProvider.getCredential((String) obj4, (String) obj5);
            case 5:
                Object obj6 = map2.get("email");
                Objects.requireNonNull(obj6);
                Objects.requireNonNull(str2);
                return EmailAuthProvider.getCredential((String) obj6, str2);
            case 6:
                Objects.requireNonNull(str4);
                return GithubAuthProvider.getCredential(str4);
            case 7:
                Object obj7 = map2.get("email");
                Objects.requireNonNull(obj7);
                Object obj8 = map2.get("emailLink");
                Objects.requireNonNull(obj8);
                return EmailAuthProvider.getCredentialWithLink((String) obj7, (String) obj8);
            default:
                return null;
        }
    }

    private Map<String, Object> parseActionCodeResult(ActionCodeResult actionCodeResult) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        int operation = actionCodeResult.getOperation();
        if (operation == 0) {
            hashMap.put("operation", 1);
        } else if (operation == 1) {
            hashMap.put("operation", 2);
        } else if (operation == 2) {
            hashMap.put("operation", 3);
        } else if (operation == 4) {
            hashMap.put("operation", 4);
        } else if (operation == 5) {
            hashMap.put("operation", 5);
        } else if (operation == 6) {
            hashMap.put("operation", 6);
        } else {
            hashMap.put("operation", 0);
        }
        ActionCodeInfo info = actionCodeResult.getInfo();
        if ((info != null && operation == 1) || operation == 0) {
            hashMap2.put("email", info.getEmail());
            hashMap2.put(Constants.PREVIOUS_EMAIL, null);
        } else if (operation == 6) {
            hashMap2.put("email", null);
            hashMap2.put(Constants.PREVIOUS_EMAIL, null);
        } else if (operation == 2 || operation == 5) {
            Objects.requireNonNull(info);
            ActionCodeEmailInfo actionCodeEmailInfo = (ActionCodeEmailInfo) info;
            hashMap2.put("email", actionCodeEmailInfo.getEmail());
            hashMap2.put(Constants.PREVIOUS_EMAIL, actionCodeEmailInfo.getPreviousEmail());
        }
        hashMap.put("data", hashMap2);
        return hashMap;
    }

    private Map<String, Object> parseAuthResult(AuthResult authResult) {
        HashMap hashMap = new HashMap();
        hashMap.put(Constants.ADDITIONAL_USER_INFO, parseAdditionalUserInfo(authResult.getAdditionalUserInfo()));
        hashMap.put(Constants.AUTH_CREDENTIAL, parseAuthCredential(authResult.getCredential()));
        hashMap.put(Constants.USER, parseFirebaseUser(authResult.getUser()));
        return hashMap;
    }

    private Map<String, Object> parseAdditionalUserInfo(AdditionalUserInfo additionalUserInfo) {
        if (additionalUserInfo == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(Constants.IS_NEW_USER, Boolean.valueOf(additionalUserInfo.isNewUser()));
        hashMap.put("profile", additionalUserInfo.getProfile());
        hashMap.put(Constants.PROVIDER_ID, additionalUserInfo.getProviderId());
        hashMap.put(Constants.USERNAME, additionalUserInfo.getUsername());
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, Object> parseFirebaseUser(FirebaseUser firebaseUser) {
        if (firebaseUser == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap.put(Constants.DISPLAY_NAME, firebaseUser.getDisplayName());
        hashMap.put("email", firebaseUser.getEmail());
        hashMap.put(Constants.EMAIL_VERIFIED, Boolean.valueOf(firebaseUser.isEmailVerified()));
        hashMap.put(Constants.IS_ANONYMOUS, Boolean.valueOf(firebaseUser.isAnonymous()));
        if (firebaseUser.getMetadata() != null) {
            hashMap2.put(Constants.CREATION_TIME, Long.valueOf(firebaseUser.getMetadata().getCreationTimestamp()));
            hashMap2.put(Constants.LAST_SIGN_IN_TIME, Long.valueOf(firebaseUser.getMetadata().getLastSignInTimestamp()));
        }
        hashMap.put(Constants.METADATA, hashMap2);
        hashMap.put(Constants.PHONE_NUMBER, firebaseUser.getPhoneNumber());
        hashMap.put(Constants.PHOTO_URL, parsePhotoUrl(firebaseUser.getPhotoUrl()));
        hashMap.put(Constants.PROVIDER_DATA, parseUserInfoList(firebaseUser.getProviderData()));
        hashMap.put(Constants.REFRESH_TOKEN, "");
        hashMap.put(Constants.UID, firebaseUser.getUid());
        hashMap.put(Constants.TENANT_ID, firebaseUser.getTenantId());
        return hashMap;
    }

    private static List<Map<String, Object>> parseUserInfoList(List<? extends UserInfo> list) {
        ArrayList arrayList = new ArrayList();
        if (list == null) {
            return arrayList;
        }
        Iterator it = new ArrayList(list).iterator();
        while (it.hasNext()) {
            UserInfo userInfo = (UserInfo) it.next();
            if (userInfo != null && !FirebaseAuthProvider.PROVIDER_ID.equals(userInfo.getProviderId())) {
                arrayList.add(parseUserInfo(userInfo));
            }
        }
        return arrayList;
    }

    private static Map<String, Object> parseUserInfo(UserInfo userInfo) {
        HashMap hashMap = new HashMap();
        hashMap.put(Constants.DISPLAY_NAME, userInfo.getDisplayName());
        hashMap.put("email", userInfo.getEmail());
        hashMap.put(Constants.PHONE_NUMBER, userInfo.getPhoneNumber());
        hashMap.put(Constants.PHOTO_URL, parsePhotoUrl(userInfo.getPhotoUrl()));
        hashMap.put(Constants.PROVIDER_ID, userInfo.getProviderId());
        hashMap.put(Constants.UID, userInfo.getUid());
        return hashMap;
    }

    private static String parsePhotoUrl(Uri uri) {
        if (uri == null) {
            return null;
        }
        String uri2 = uri.toString();
        if ("".equals(uri2)) {
            return null;
        }
        return uri2;
    }

    private ActionCodeSettings getActionCodeSettings(Map<String, Object> map) {
        ActionCodeSettings.Builder newBuilder = ActionCodeSettings.newBuilder();
        Object obj = map.get("url");
        Objects.requireNonNull(obj);
        newBuilder.setUrl((String) obj);
        if (map.get(Constants.DYNAMIC_LINK_DOMAIN) != null) {
            Object obj2 = map.get(Constants.DYNAMIC_LINK_DOMAIN);
            Objects.requireNonNull(obj2);
            newBuilder.setDynamicLinkDomain((String) obj2);
        }
        if (map.get(Constants.HANDLE_CODE_IN_APP) != null) {
            Object obj3 = map.get(Constants.HANDLE_CODE_IN_APP);
            Objects.requireNonNull(obj3);
            newBuilder.setHandleCodeInApp(((Boolean) obj3).booleanValue());
        }
        if (map.get(Constants.ANDROID) != null) {
            Object obj4 = map.get(Constants.ANDROID);
            Objects.requireNonNull(obj4);
            Map map2 = (Map) obj4;
            boolean z = false;
            if (map2.get(Constants.INSTALL_APP) != null) {
                Object obj5 = map2.get(Constants.INSTALL_APP);
                Objects.requireNonNull(obj5);
                z = ((Boolean) obj5).booleanValue();
            }
            String str = map2.get(Constants.MINIMUM_VERSION) != null ? (String) map2.get(Constants.MINIMUM_VERSION) : null;
            Object obj6 = map2.get(Constants.PACKAGE_NAME);
            Objects.requireNonNull(obj6);
            newBuilder.setAndroidPackageName((String) obj6, z, str);
        }
        if (map.get(Constants.IOS) != null) {
            Object obj7 = map.get(Constants.IOS);
            Objects.requireNonNull(obj7);
            Object obj8 = ((Map) obj7).get(Constants.BUNDLE_ID);
            Objects.requireNonNull(obj8);
            newBuilder.setIOSBundleId((String) obj8);
        }
        return newBuilder.build();
    }

    private Map<String, Object> parseTokenResult(GetTokenResult getTokenResult) {
        HashMap hashMap = new HashMap();
        hashMap.put(Constants.AUTH_TIMESTAMP, Long.valueOf(getTokenResult.getAuthTimestamp() * 1000));
        hashMap.put(Constants.CLAIMS, getTokenResult.getClaims());
        hashMap.put(Constants.EXPIRATION_TIMESTAMP, Long.valueOf(getTokenResult.getExpirationTimestamp() * 1000));
        hashMap.put(Constants.ISSUED_AT_TIMESTAMP, Long.valueOf(getTokenResult.getIssuedAtTimestamp() * 1000));
        hashMap.put(Constants.SIGN_IN_PROVIDER, getTokenResult.getSignInProvider());
        hashMap.put(Constants.SIGN_IN_SECOND_FACTOR, getTokenResult.getSignInSecondFactor());
        hashMap.put(Constants.TOKEN, getTokenResult.getToken());
        return hashMap;
    }

    private Task<String> registerIdTokenListener(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m301x4c305080(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$registerIdTokenListener$0$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m301x4c305080(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseAuth auth = getAuth(map);
            IdTokenChannelStreamHandler idTokenChannelStreamHandler = new IdTokenChannelStreamHandler(auth);
            String str = "plugins.flutter.io/firebase_auth/id-token/" + auth.getApp().getName();
            EventChannel eventChannel = new EventChannel(this.messenger, str);
            eventChannel.setStreamHandler(idTokenChannelStreamHandler);
            this.streamHandlers.put(eventChannel, idTokenChannelStreamHandler);
            taskCompletionSource.setResult(str);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<String> registerAuthStateListener(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m300x91d04c94(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$registerAuthStateListener$1$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m300x91d04c94(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseAuth auth = getAuth(map);
            AuthStateChannelStreamHandler authStateChannelStreamHandler = new AuthStateChannelStreamHandler(auth);
            String str = "plugins.flutter.io/firebase_auth/auth-state/" + auth.getApp().getName();
            EventChannel eventChannel = new EventChannel(this.messenger, str);
            eventChannel.setStreamHandler(authStateChannelStreamHandler);
            this.streamHandlers.put(eventChannel, authStateChannelStreamHandler);
            taskCompletionSource.setResult(str);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> applyActionCode(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda29
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.lambda$applyActionCode$2(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$applyActionCode$2(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseAuth auth = getAuth(map);
            Object obj = map.get("code");
            Objects.requireNonNull(obj);
            Tasks.await(auth.applyActionCode((String) obj));
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> checkActionCode(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda41
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m291x698bd801(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$checkActionCode$3$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m291x698bd801(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseAuth auth = getAuth(map);
            Object obj = map.get("code");
            Objects.requireNonNull(obj);
            taskCompletionSource.setResult(parseActionCodeResult((ActionCodeResult) Tasks.await(auth.checkActionCode((String) obj))));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> confirmPasswordReset(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda30
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.lambda$confirmPasswordReset$4(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$confirmPasswordReset$4(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseAuth auth = getAuth(map);
            Object obj = map.get("code");
            Objects.requireNonNull(obj);
            Object obj2 = map.get(Constants.NEW_PASSWORD);
            Objects.requireNonNull(obj2);
            Tasks.await(auth.confirmPasswordReset((String) obj, (String) obj2));
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> createUserWithEmailAndPassword(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda42
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m292x75dbba4d(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$createUserWithEmailAndPassword$5$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m292x75dbba4d(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseAuth auth = getAuth(map);
            Object obj = map.get("email");
            Objects.requireNonNull(obj);
            Object obj2 = map.get("password");
            Objects.requireNonNull(obj2);
            taskCompletionSource.setResult(parseAuthResult((AuthResult) Tasks.await(auth.createUserWithEmailAndPassword((String) obj, (String) obj2))));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> fetchSignInMethodsForEmail(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda31
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.lambda$fetchSignInMethodsForEmail$6(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$fetchSignInMethodsForEmail$6(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseAuth auth = getAuth(map);
            Object obj = map.get("email");
            Objects.requireNonNull(obj);
            SignInMethodQueryResult signInMethodQueryResult = (SignInMethodQueryResult) Tasks.await(auth.fetchSignInMethodsForEmail((String) obj));
            HashMap hashMap = new HashMap();
            hashMap.put(Constants.PROVIDERS, signInMethodQueryResult.getSignInMethods());
            taskCompletionSource.setResult(hashMap);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> sendPasswordResetEmail(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m305x9752e3be(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$sendPasswordResetEmail$7$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m305x9752e3be(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseAuth auth = getAuth(map);
            Object obj = map.get("email");
            Objects.requireNonNull(obj);
            String str = (String) obj;
            Object obj2 = map.get(Constants.ACTION_CODE_SETTINGS);
            if (obj2 == null) {
                Tasks.await(auth.sendPasswordResetEmail(str));
                taskCompletionSource.setResult(null);
            } else {
                Tasks.await(auth.sendPasswordResetEmail(str, getActionCodeSettings((Map) obj2)));
                taskCompletionSource.setResult(null);
            }
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> sendSignInLinkToEmail(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m306xc3c0200a(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$sendSignInLinkToEmail$8$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m306xc3c0200a(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseAuth auth = getAuth(map);
            Object obj = map.get("email");
            Objects.requireNonNull(obj);
            Object obj2 = map.get(Constants.ACTION_CODE_SETTINGS);
            Objects.requireNonNull(obj2);
            Tasks.await(auth.sendSignInLinkToEmail((String) obj, getActionCodeSettings((Map) obj2)));
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> setLanguageCode(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m307xee06c33f(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$setLanguageCode$9$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m307xee06c33f(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseAuth auth = getAuth(map);
            String str = (String) map.get(Constants.LANGUAGE_CODE);
            if (str == null) {
                auth.useAppLanguage();
            } else {
                auth.setLanguageCode(str);
            }
            taskCompletionSource.setResult(new HashMap<String, Object>(auth) { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin.1
                final /* synthetic */ FirebaseAuth val$firebaseAuth;

                {
                    this.val$firebaseAuth = auth;
                    put(Constants.LANGUAGE_CODE, auth.getLanguageCode());
                }
            });
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> setSettings(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda32
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.lambda$setSettings$10(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setSettings$10(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseAuth auth = getAuth(map);
            Boolean bool = (Boolean) map.get(Constants.APP_VERIFICATION_DISABLED_FOR_TESTING);
            Boolean bool2 = (Boolean) map.get(Constants.FORCE_RECAPTCHA_FLOW);
            String str = (String) map.get(Constants.PHONE_NUMBER);
            String str2 = (String) map.get(Constants.SMS_CODE);
            if (bool != null) {
                auth.getFirebaseAuthSettings().setAppVerificationDisabledForTesting(bool.booleanValue());
            }
            if (bool2 != null) {
                auth.getFirebaseAuthSettings().forceRecaptchaFlowForTesting(bool2.booleanValue());
            }
            if (str != null && str2 != null) {
                auth.getFirebaseAuthSettings().setAutoRetrievedSmsCodeForPhoneNumber(str, str2);
            }
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> signInAnonymously(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m308xee1e5b5(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$signInAnonymously$11$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m308xee1e5b5(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            taskCompletionSource.setResult(parseAuthResult((AuthResult) Tasks.await(getAuth(map).signInAnonymously())));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> signInWithCredential(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda15
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m309x437f1e35(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$signInWithCredential$12$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m309x437f1e35(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseAuth auth = getAuth(map);
            AuthCredential credential = getCredential(map);
            if (credential == null) {
                throw FlutterFirebaseAuthPluginException.invalidCredential();
            }
            taskCompletionSource.setResult(parseAuthResult((AuthResult) Tasks.await(auth.signInWithCredential(credential))));
        } catch (Exception e) {
            if (e.getCause() instanceof FirebaseAuthMultiFactorException) {
                handleMultiFactorException(map, taskCompletionSource, e);
            } else {
                taskCompletionSource.setException(e);
            }
        }
    }

    private Task<Map<String, Object>> signInWithCustomToken(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda16
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m310x871f5f9b(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$signInWithCustomToken$13$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m310x871f5f9b(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseAuth auth = getAuth(map);
            Object obj = map.get(Constants.TOKEN);
            Objects.requireNonNull(obj);
            taskCompletionSource.setResult(parseAuthResult((AuthResult) Tasks.await(auth.signInWithCustomToken((String) obj))));
        } catch (Exception e) {
            if (e.getCause() instanceof FirebaseAuthMultiFactorException) {
                handleMultiFactorException(map, taskCompletionSource, e);
            } else {
                taskCompletionSource.setException(e);
            }
        }
    }

    private Task<Map<String, Object>> signInWithEmailAndPassword(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda17
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m311xa444b4f2(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$signInWithEmailAndPassword$14$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m311xa444b4f2(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseAuth auth = getAuth(map);
            Object obj = map.get("email");
            Objects.requireNonNull(obj);
            Object obj2 = map.get("password");
            Objects.requireNonNull(obj2);
            taskCompletionSource.setResult(parseAuthResult((AuthResult) Tasks.await(auth.signInWithEmailAndPassword((String) obj, (String) obj2))));
        } catch (Exception e) {
            if (e.getCause() instanceof FirebaseAuthMultiFactorException) {
                handleMultiFactorException(map, taskCompletionSource, e);
            } else {
                taskCompletionSource.setException(e);
            }
        }
    }

    private void handleMultiFactorException(Map<String, Object> map, TaskCompletionSource<Map<String, Object>> taskCompletionSource, Exception exc) {
        FirebaseAuthMultiFactorException firebaseAuthMultiFactorException = (FirebaseAuthMultiFactorException) exc.getCause();
        HashMap hashMap = new HashMap();
        MultiFactorResolver resolver = firebaseAuthMultiFactorException.getResolver();
        List<MultiFactorInfo> hints = resolver.getHints();
        MultiFactorSession session = resolver.getSession();
        String uuid = UUID.randomUUID().toString();
        this.multiFactorSessionMap.put(uuid, session);
        String uuid2 = UUID.randomUUID().toString();
        this.multiFactorResolverMap.put(uuid2, resolver);
        List<Map<String, Object>> multiFactorInfoToMap = multiFactorInfoToMap(hints);
        hashMap.put("appName", getAuth(map).getApp().getName());
        hashMap.put(Constants.MULTI_FACTOR_HINTS, multiFactorInfoToMap);
        hashMap.put(Constants.MULTI_FACTOR_SESSION_ID, uuid);
        hashMap.put(Constants.MULTI_FACTOR_RESOLVER_ID, uuid2);
        taskCompletionSource.setException(new FlutterFirebaseAuthPluginException(firebaseAuthMultiFactorException.getErrorCode(), firebaseAuthMultiFactorException.getLocalizedMessage(), hashMap));
    }

    private List<GeneratedAndroidFirebaseAuth.PigeonMultiFactorInfo> multiFactorInfoToPigeon(List<MultiFactorInfo> list) {
        ArrayList arrayList = new ArrayList();
        for (MultiFactorInfo multiFactorInfo : list) {
            if (multiFactorInfo instanceof PhoneMultiFactorInfo) {
                arrayList.add(new GeneratedAndroidFirebaseAuth.PigeonMultiFactorInfo.Builder().setPhoneNumber(((PhoneMultiFactorInfo) multiFactorInfo).getPhoneNumber()).setDisplayName(multiFactorInfo.getDisplayName()).setEnrollmentTimestamp(Double.valueOf(multiFactorInfo.getEnrollmentTimestamp())).setUid(multiFactorInfo.getUid()).setFactorId(multiFactorInfo.getFactorId()).build());
            } else {
                arrayList.add(new GeneratedAndroidFirebaseAuth.PigeonMultiFactorInfo.Builder().setDisplayName(multiFactorInfo.getDisplayName()).setEnrollmentTimestamp(Double.valueOf(multiFactorInfo.getEnrollmentTimestamp())).setUid(multiFactorInfo.getUid()).setFactorId(multiFactorInfo.getFactorId()).build());
            }
        }
        return arrayList;
    }

    private List<Map<String, Object>> multiFactorInfoToMap(List<MultiFactorInfo> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<GeneratedAndroidFirebaseAuth.PigeonMultiFactorInfo> it = multiFactorInfoToPigeon(list).iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().toMap());
        }
        return arrayList;
    }

    private Task<Map<String, Object>> signInWithEmailLink(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda18
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m312xb868d947(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$signInWithEmailLink$15$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m312xb868d947(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseAuth auth = getAuth(map);
            Object obj = map.get("email");
            Objects.requireNonNull(obj);
            Object obj2 = map.get("emailLink");
            Objects.requireNonNull(obj2);
            taskCompletionSource.setResult(parseAuthResult((AuthResult) Tasks.await(auth.signInWithEmailLink((String) obj, (String) obj2))));
        } catch (Exception e) {
            if (e.getCause() instanceof FirebaseAuthMultiFactorException) {
                handleMultiFactorException(map, taskCompletionSource, e);
            } else {
                taskCompletionSource.setException(e);
            }
        }
    }

    private Task<Void> signOut(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda34
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.lambda$signOut$16(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$signOut$16(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            getAuth(map).signOut();
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> useEmulator(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda35
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.lambda$useEmulator$17(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$useEmulator$17(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            getAuth(map).useEmulator((String) map.get(Constants.HOST), ((Integer) map.get(Constants.PORT)).intValue());
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> verifyPasswordResetCode(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda36
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.lambda$verifyPasswordResetCode$18(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$verifyPasswordResetCode$18(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseAuth auth = getAuth(map);
            Object obj = map.get("code");
            Objects.requireNonNull(obj);
            HashMap hashMap = new HashMap();
            hashMap.put("email", Tasks.await(auth.verifyPasswordResetCode((String) obj)));
            taskCompletionSource.setResult(hashMap);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<String> verifyPhoneNumber(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda28
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m321x8ecdaedd(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$verifyPhoneNumber$20$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m321x8ecdaedd(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            String str = "plugins.flutter.io/firebase_auth/phone/" + UUID.randomUUID().toString();
            EventChannel eventChannel = new EventChannel(this.messenger, str);
            String str2 = (String) map.get(Constants.MULTI_FACTOR_SESSION_ID);
            PhoneMultiFactorInfo phoneMultiFactorInfo = null;
            MultiFactorSession multiFactorSession = str2 != null ? this.multiFactorSessionMap.get(str2) : null;
            String str3 = (String) map.get(Constants.MULTI_FACTOR_INFO);
            if (str3 != null) {
                Iterator<String> it = this.multiFactorResolverMap.keySet().iterator();
                while (it.hasNext()) {
                    Iterator<MultiFactorInfo> it2 = this.multiFactorResolverMap.get(it.next()).getHints().iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            MultiFactorInfo next = it2.next();
                            if (next.getUid().equals(str3) && (next instanceof PhoneMultiFactorInfo)) {
                                phoneMultiFactorInfo = (PhoneMultiFactorInfo) next;
                                break;
                            }
                        }
                    }
                }
            }
            PhoneNumberVerificationStreamHandler phoneNumberVerificationStreamHandler = new PhoneNumberVerificationStreamHandler(getActivity(), map, multiFactorSession, phoneMultiFactorInfo, new PhoneNumberVerificationStreamHandler.OnCredentialsListener() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda38
                @Override // io.flutter.plugins.firebase.auth.PhoneNumberVerificationStreamHandler.OnCredentialsListener
                public final void onCredentialsReceived(PhoneAuthCredential phoneAuthCredential) {
                    FlutterFirebaseAuthPlugin.authCredentials.put(Integer.valueOf(phoneAuthCredential.hashCode()), phoneAuthCredential);
                }
            });
            eventChannel.setStreamHandler(phoneNumberVerificationStreamHandler);
            this.streamHandlers.put(eventChannel, phoneNumberVerificationStreamHandler);
            taskCompletionSource.setResult(str);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> deleteUser(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m293xfe231b4e(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$deleteUser$21$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m293xfe231b4e(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseUser currentUser = getCurrentUser((Map<String, Object>) map);
            if (currentUser == null) {
                taskCompletionSource.setException(FlutterFirebaseAuthPluginException.noUser());
            } else {
                Tasks.await(currentUser.delete());
                taskCompletionSource.setResult(null);
            }
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> getIdToken(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m295x1aff5c7f(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getIdToken$22$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m295x1aff5c7f(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseUser currentUser = getCurrentUser((Map<String, Object>) map);
            Object obj = map.get(Constants.FORCE_REFRESH);
            Objects.requireNonNull(obj);
            Boolean bool = (Boolean) obj;
            Object obj2 = map.get(Constants.TOKEN_ONLY);
            Objects.requireNonNull(obj2);
            Boolean bool2 = (Boolean) obj2;
            if (currentUser == null) {
                taskCompletionSource.setException(FlutterFirebaseAuthPluginException.noUser());
                return;
            }
            GetTokenResult getTokenResult = (GetTokenResult) Tasks.await(currentUser.getIdToken(bool.booleanValue()));
            if (bool2.booleanValue()) {
                HashMap hashMap = new HashMap();
                hashMap.put(Constants.TOKEN, getTokenResult.getToken());
                taskCompletionSource.setResult(hashMap);
                return;
            }
            taskCompletionSource.setResult(parseTokenResult(getTokenResult));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> linkUserWithCredential(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m297x53f86178(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$linkUserWithCredential$23$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m297x53f86178(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseUser currentUser = getCurrentUser((Map<String, Object>) map);
            AuthCredential credential = getCredential(map);
            if (currentUser == null) {
                taskCompletionSource.setException(FlutterFirebaseAuthPluginException.noUser());
            } else if (credential == null) {
                taskCompletionSource.setException(FlutterFirebaseAuthPluginException.invalidCredential());
            } else {
                taskCompletionSource.setResult(parseAuthResult((AuthResult) Tasks.await(currentUser.linkWithCredential(credential))));
            }
        } catch (Exception e) {
            if (e.getCause() instanceof FirebaseAuthMultiFactorException) {
                handleMultiFactorException(map, taskCompletionSource, e);
                return;
            }
            String message = e.getMessage();
            if (message != null && message.contains("User has already been linked to the given provider.")) {
                taskCompletionSource.setException(FlutterFirebaseAuthPluginException.alreadyLinkedProvider());
            } else {
                taskCompletionSource.setException(e);
            }
        }
    }

    private Task<Map<String, Object>> reauthenticateUserWithCredential(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m298x329fa925(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$reauthenticateUserWithCredential$24$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m298x329fa925(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseUser currentUser = getCurrentUser((Map<String, Object>) map);
            AuthCredential credential = getCredential(map);
            if (currentUser == null) {
                taskCompletionSource.setException(FlutterFirebaseAuthPluginException.noUser());
            } else if (credential == null) {
                taskCompletionSource.setException(FlutterFirebaseAuthPluginException.invalidCredential());
            } else {
                taskCompletionSource.setResult(parseAuthResult((AuthResult) Tasks.await(currentUser.reauthenticateAndRetrieveData(credential))));
            }
        } catch (Exception e) {
            if (e.getCause() instanceof FirebaseAuthMultiFactorException) {
                handleMultiFactorException(map, taskCompletionSource, e);
            } else {
                taskCompletionSource.setException(e);
            }
        }
    }

    private Task<Map<String, Object>> reloadUser(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m302x2b8c9138(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$reloadUser$25$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m302x2b8c9138(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseUser currentUser = getCurrentUser((Map<String, Object>) map);
            if (currentUser == null) {
                taskCompletionSource.setException(FlutterFirebaseAuthPluginException.noUser());
            } else {
                Tasks.await(currentUser.reload());
                taskCompletionSource.setResult(parseFirebaseUser(getCurrentUser((Map<String, Object>) map)));
            }
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> sendEmailVerification(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m304x22448ae8(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$sendEmailVerification$26$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m304x22448ae8(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseUser currentUser = getCurrentUser((Map<String, Object>) map);
            if (currentUser == null) {
                taskCompletionSource.setException(FlutterFirebaseAuthPluginException.noUser());
                return;
            }
            Object obj = map.get(Constants.ACTION_CODE_SETTINGS);
            if (obj == null) {
                Tasks.await(currentUser.sendEmailVerification());
                taskCompletionSource.setResult(null);
            } else {
                Tasks.await(currentUser.sendEmailVerification(getActionCodeSettings((Map) obj)));
                taskCompletionSource.setResult(null);
            }
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> unlinkUserProvider(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda21
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m315x4f52b141(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$unlinkUserProvider$27$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m315x4f52b141(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseUser currentUser = getCurrentUser((Map<String, Object>) map);
            if (currentUser == null) {
                taskCompletionSource.setException(FlutterFirebaseAuthPluginException.noUser());
                return;
            }
            Object obj = map.get(Constants.PROVIDER_ID);
            Objects.requireNonNull(obj);
            taskCompletionSource.setResult(parseAuthResult((AuthResult) Tasks.await(currentUser.unlink((String) obj))));
        } catch (ExecutionException unused) {
            taskCompletionSource.setException(FlutterFirebaseAuthPluginException.noSuchProvider());
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> updateEmail(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda23
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m316x3f53f58a(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$updateEmail$28$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m316x3f53f58a(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseUser currentUser = getCurrentUser((Map<String, Object>) map);
            if (currentUser == null) {
                taskCompletionSource.setException(FlutterFirebaseAuthPluginException.noUser());
                return;
            }
            Object obj = map.get(Constants.NEW_EMAIL);
            Objects.requireNonNull(obj);
            Tasks.await(currentUser.updateEmail((String) obj));
            Tasks.await(currentUser.reload());
            taskCompletionSource.setResult(parseFirebaseUser(currentUser));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> updatePassword(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda24
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m317x3adac9b4(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$updatePassword$29$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m317x3adac9b4(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseUser currentUser = getCurrentUser((Map<String, Object>) map);
            if (currentUser == null) {
                taskCompletionSource.setException(FlutterFirebaseAuthPluginException.noUser());
                return;
            }
            Object obj = map.get(Constants.NEW_PASSWORD);
            Objects.requireNonNull(obj);
            Tasks.await(currentUser.updatePassword((String) obj));
            Tasks.await(currentUser.reload());
            taskCompletionSource.setResult(parseFirebaseUser(currentUser));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> updatePhoneNumber(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda25
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m318xfbd7790e(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$updatePhoneNumber$30$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m318xfbd7790e(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseUser currentUser = getCurrentUser((Map<String, Object>) map);
            if (currentUser == null) {
                taskCompletionSource.setException(FlutterFirebaseAuthPluginException.noUser());
                return;
            }
            PhoneAuthCredential phoneAuthCredential = (PhoneAuthCredential) getCredential(map);
            if (phoneAuthCredential == null) {
                taskCompletionSource.setException(FlutterFirebaseAuthPluginException.invalidCredential());
                return;
            }
            Tasks.await(currentUser.updatePhoneNumber(phoneAuthCredential));
            Tasks.await(currentUser.reload());
            taskCompletionSource.setResult(parseFirebaseUser(currentUser));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Map<String, Object>> updateProfile(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda26
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m319x95f06d3f(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$updateProfile$31$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m319x95f06d3f(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseUser currentUser = getCurrentUser((Map<String, Object>) map);
            if (currentUser == null) {
                taskCompletionSource.setException(FlutterFirebaseAuthPluginException.noUser());
                return;
            }
            Object obj = map.get("profile");
            Objects.requireNonNull(obj);
            Map map2 = (Map) obj;
            UserProfileChangeRequest.Builder builder = new UserProfileChangeRequest.Builder();
            if (map2.containsKey(Constants.DISPLAY_NAME)) {
                builder.setDisplayName((String) map2.get(Constants.DISPLAY_NAME));
            }
            if (map2.containsKey(Constants.PHOTO_URL)) {
                String str = (String) map2.get(Constants.PHOTO_URL);
                if (str != null) {
                    builder.setPhotoUri(Uri.parse(str));
                } else {
                    builder.setPhotoUri(null);
                }
            }
            Tasks.await(currentUser.updateProfile(builder.build()));
            Tasks.await(currentUser.reload());
            taskCompletionSource.setResult(parseFirebaseUser(currentUser));
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private Task<Void> verifyBeforeUpdateEmail(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda27
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m320xc66482d9(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$verifyBeforeUpdateEmail$32$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m320xc66482d9(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseUser currentUser = getCurrentUser((Map<String, Object>) map);
            if (currentUser == null) {
                taskCompletionSource.setException(FlutterFirebaseAuthPluginException.noUser());
            }
            Object obj = map.get(Constants.NEW_EMAIL);
            Objects.requireNonNull(obj);
            String str = (String) obj;
            Object obj2 = map.get(Constants.ACTION_CODE_SETTINGS);
            if (obj2 == null) {
                Tasks.await(currentUser.verifyBeforeUpdateEmail(str));
                taskCompletionSource.setResult(null);
            } else {
                Tasks.await(currentUser.verifyBeforeUpdateEmail(str, getActionCodeSettings((Map) obj2)));
                taskCompletionSource.setResult(null);
            }
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, final MethodChannel.Result result) {
        Task verifyBeforeUpdateEmail;
        String str = methodCall.method;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2057012413:
                if (str.equals("User#verifyBeforeUpdateEmail")) {
                    c = 0;
                    break;
                }
                break;
            case -1780708429:
                if (str.equals("Auth#signInWithEmailLink")) {
                    c = 1;
                    break;
                }
                break;
            case -1752633812:
                if (str.equals("Auth#setLanguageCode")) {
                    c = 2;
                    break;
                }
                break;
            case -1684941144:
                if (str.equals("User#reauthenticateUserWithCredential")) {
                    c = 3;
                    break;
                }
                break;
            case -1644801898:
                if (str.equals("Auth#signOut")) {
                    c = 4;
                    break;
                }
                break;
            case -1598142666:
                if (str.equals("User#updatePhoneNumber")) {
                    c = 5;
                    break;
                }
                break;
            case -1568968164:
                if (str.equals("User#updatePassword")) {
                    c = 6;
                    break;
                }
                break;
            case -1529680830:
                if (str.equals("Auth#sendSignInLinkToEmail")) {
                    c = 7;
                    break;
                }
                break;
            case -1451942929:
                if (str.equals("User#linkWithCredential")) {
                    c = '\b';
                    break;
                }
                break;
            case -1351623174:
                if (str.equals("Auth#signInWithCredential")) {
                    c = '\t';
                    break;
                }
                break;
            case -1292431612:
                if (str.equals("Auth#fetchSignInMethodsForEmail")) {
                    c = '\n';
                    break;
                }
                break;
            case -1230437447:
                if (str.equals("Auth#signInWithEmailAndPassword")) {
                    c = 11;
                    break;
                }
                break;
            case -1027441723:
                if (str.equals("Auth#signInWithCustomToken")) {
                    c = '\f';
                    break;
                }
                break;
            case -834572032:
                if (str.equals("User#getIdToken")) {
                    c = '\r';
                    break;
                }
                break;
            case -695049397:
                if (str.equals("Auth#sendPasswordResetEmail")) {
                    c = 14;
                    break;
                }
                break;
            case -684675433:
                if (str.equals("User#sendEmailVerification")) {
                    c = 15;
                    break;
                }
                break;
            case -636251837:
                if (str.equals("User#delete")) {
                    c = 16;
                    break;
                }
                break;
            case -396665309:
                if (str.equals("Auth#verifyPhoneNumber")) {
                    c = 17;
                    break;
                }
                break;
            case -290623266:
                if (str.equals("Auth#createUserWithEmailAndPassword")) {
                    c = 18;
                    break;
                }
                break;
            case -235434703:
                if (str.equals("User#reload")) {
                    c = 19;
                    break;
                }
                break;
            case -141240917:
                if (str.equals("User#unlink")) {
                    c = 20;
                    break;
                }
                break;
            case -122200568:
                if (str.equals("User#updateProfile")) {
                    c = 21;
                    break;
                }
                break;
            case 116859805:
                if (str.equals("Auth#signInAnonymously")) {
                    c = 22;
                    break;
                }
                break;
            case 281593967:
                if (str.equals("Auth#confirmPasswordReset")) {
                    c = 23;
                    break;
                }
                break;
            case 415055753:
                if (str.equals("User#linkWithProvider")) {
                    c = 24;
                    break;
                }
                break;
            case 422677783:
                if (str.equals("User#reauthenticateWithProvider")) {
                    c = 25;
                    break;
                }
                break;
            case 506585151:
                if (str.equals("Auth#registerAuthStateListener")) {
                    c = 26;
                    break;
                }
                break;
            case 857654192:
                if (str.equals("Auth#checkActionCode")) {
                    c = 27;
                    break;
                }
                break;
            case 934812310:
                if (str.equals("Auth#applyActionCode")) {
                    c = 28;
                    break;
                }
                break;
            case 1045882753:
                if (str.equals("Auth#useEmulator")) {
                    c = 29;
                    break;
                }
                break;
            case 1241974868:
                if (str.equals("Auth#signInWithProvider")) {
                    c = 30;
                    break;
                }
                break;
            case 1511616916:
                if (str.equals("Auth#registerIdTokenListener")) {
                    c = 31;
                    break;
                }
                break;
            case 1852431466:
                if (str.equals("Auth#setSettings")) {
                    c = ' ';
                    break;
                }
                break;
            case 1953611341:
                if (str.equals("Auth#verifyPasswordResetCode")) {
                    c = '!';
                    break;
                }
                break;
            case 2139270075:
                if (str.equals("User#updateEmail")) {
                    c = Typography.quote;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                verifyBeforeUpdateEmail = verifyBeforeUpdateEmail((Map) methodCall.arguments());
                break;
            case 1:
                verifyBeforeUpdateEmail = signInWithEmailLink((Map) methodCall.arguments());
                break;
            case 2:
                verifyBeforeUpdateEmail = setLanguageCode((Map) methodCall.arguments());
                break;
            case 3:
                verifyBeforeUpdateEmail = reauthenticateUserWithCredential((Map) methodCall.arguments());
                break;
            case 4:
                verifyBeforeUpdateEmail = signOut((Map) methodCall.arguments());
                break;
            case 5:
                verifyBeforeUpdateEmail = updatePhoneNumber((Map) methodCall.arguments());
                break;
            case 6:
                verifyBeforeUpdateEmail = updatePassword((Map) methodCall.arguments());
                break;
            case 7:
                verifyBeforeUpdateEmail = sendSignInLinkToEmail((Map) methodCall.arguments());
                break;
            case '\b':
                verifyBeforeUpdateEmail = linkUserWithCredential((Map) methodCall.arguments());
                break;
            case '\t':
                verifyBeforeUpdateEmail = signInWithCredential((Map) methodCall.arguments());
                break;
            case '\n':
                verifyBeforeUpdateEmail = fetchSignInMethodsForEmail((Map) methodCall.arguments());
                break;
            case 11:
                verifyBeforeUpdateEmail = signInWithEmailAndPassword((Map) methodCall.arguments());
                break;
            case '\f':
                verifyBeforeUpdateEmail = signInWithCustomToken((Map) methodCall.arguments());
                break;
            case '\r':
                verifyBeforeUpdateEmail = getIdToken((Map) methodCall.arguments());
                break;
            case 14:
                verifyBeforeUpdateEmail = sendPasswordResetEmail((Map) methodCall.arguments());
                break;
            case 15:
                verifyBeforeUpdateEmail = sendEmailVerification((Map) methodCall.arguments());
                break;
            case 16:
                verifyBeforeUpdateEmail = deleteUser((Map) methodCall.arguments());
                break;
            case 17:
                verifyBeforeUpdateEmail = verifyPhoneNumber((Map) methodCall.arguments());
                break;
            case 18:
                verifyBeforeUpdateEmail = createUserWithEmailAndPassword((Map) methodCall.arguments());
                break;
            case 19:
                verifyBeforeUpdateEmail = reloadUser((Map) methodCall.arguments());
                break;
            case 20:
                verifyBeforeUpdateEmail = unlinkUserProvider((Map) methodCall.arguments());
                break;
            case 21:
                verifyBeforeUpdateEmail = updateProfile((Map) methodCall.arguments());
                break;
            case 22:
                verifyBeforeUpdateEmail = signInAnonymously((Map) methodCall.arguments());
                break;
            case 23:
                verifyBeforeUpdateEmail = confirmPasswordReset((Map) methodCall.arguments());
                break;
            case 24:
                verifyBeforeUpdateEmail = startActivityForLinkWithProvider((Map) methodCall.arguments());
                break;
            case 25:
                verifyBeforeUpdateEmail = reauthenticateWithProvider((Map) methodCall.arguments());
                break;
            case 26:
                verifyBeforeUpdateEmail = registerAuthStateListener((Map) methodCall.arguments());
                break;
            case 27:
                verifyBeforeUpdateEmail = checkActionCode((Map) methodCall.arguments());
                break;
            case 28:
                verifyBeforeUpdateEmail = applyActionCode((Map) methodCall.arguments());
                break;
            case Service.SYSTEM_PARAMETERS_FIELD_NUMBER /* 29 */:
                verifyBeforeUpdateEmail = useEmulator((Map) methodCall.arguments());
                break;
            case 30:
                verifyBeforeUpdateEmail = signInWithProvider((Map) methodCall.arguments());
                break;
            case DescriptorProtos.FileOptions.CC_ENABLE_ARENAS_FIELD_NUMBER /* 31 */:
                verifyBeforeUpdateEmail = registerIdTokenListener((Map) methodCall.arguments());
                break;
            case ' ':
                verifyBeforeUpdateEmail = setSettings((Map) methodCall.arguments());
                break;
            case '!':
                verifyBeforeUpdateEmail = verifyPasswordResetCode((Map) methodCall.arguments());
                break;
            case '\"':
                verifyBeforeUpdateEmail = updateEmail((Map) methodCall.arguments());
                break;
            default:
                result.notImplemented();
                return;
        }
        verifyBeforeUpdateEmail.addOnCompleteListener(new OnCompleteListener() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                FlutterFirebaseAuthPlugin.lambda$onMethodCall$33(MethodChannel.Result.this, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onMethodCall$33(MethodChannel.Result result, Task task) {
        if (task.isSuccessful()) {
            result.success(task.getResult());
        } else {
            Exception exception = task.getException();
            result.error("firebase_auth", exception != null ? exception.getMessage() : null, getExceptionDetails(exception));
        }
    }

    private Task<Map<String, Object>> startActivityForLinkWithProvider(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda20
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m314xbb9f9e5f(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$startActivityForLinkWithProvider$34$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m314xbb9f9e5f(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseUser currentUser = getCurrentUser((Map<String, Object>) map);
            Object obj = map.get(Constants.SIGN_IN_PROVIDER);
            Objects.requireNonNull(obj);
            List<String> list = (List) map.get(Constants.SIGN_IN_PROVIDER_SCOPE);
            Map<String, String> map2 = (Map) map.get(Constants.SIGN_IN_PROVIDER_CUSTOM_PARAMETERS);
            OAuthProvider.Builder newBuilder = OAuthProvider.newBuilder((String) obj);
            if (list != null) {
                newBuilder.setScopes(list);
            }
            if (map2 != null) {
                newBuilder.addCustomParameters(map2);
            }
            taskCompletionSource.setResult(parseAuthResult((AuthResult) Tasks.await(currentUser.startActivityForLinkWithProvider(this.activity, newBuilder.build()))));
        } catch (Exception e) {
            if (e.getCause() instanceof FirebaseAuthMultiFactorException) {
                handleMultiFactorException(map, taskCompletionSource, e);
            } else {
                taskCompletionSource.setException(e);
            }
        }
    }

    private Task<Map<String, Object>> reauthenticateWithProvider(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m299xfa3472f4(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$reauthenticateWithProvider$35$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m299xfa3472f4(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseUser currentUser = getCurrentUser((Map<String, Object>) map);
            Object obj = map.get(Constants.SIGN_IN_PROVIDER);
            Objects.requireNonNull(obj);
            List<String> list = (List) map.get(Constants.SIGN_IN_PROVIDER_SCOPE);
            Map<String, String> map2 = (Map) map.get(Constants.SIGN_IN_PROVIDER_CUSTOM_PARAMETERS);
            OAuthProvider.Builder newBuilder = OAuthProvider.newBuilder((String) obj);
            if (list != null) {
                newBuilder.setScopes(list);
            }
            if (map2 != null) {
                newBuilder.addCustomParameters(map2);
            }
            taskCompletionSource.setResult(parseAuthResult((AuthResult) Tasks.await(currentUser.startActivityForReauthenticateWithProvider(this.activity, newBuilder.build()))));
        } catch (Exception e) {
            if (e.getCause() instanceof FirebaseAuthMultiFactorException) {
                handleMultiFactorException(map, taskCompletionSource, e);
            } else {
                taskCompletionSource.setException(e);
            }
        }
    }

    private Task<Map<String, Object>> signInWithProvider(final Map<String, Object> map) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda19
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m313xe9daa60d(map, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$signInWithProvider$36$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m313xe9daa60d(Map map, TaskCompletionSource taskCompletionSource) {
        try {
            FirebaseAuth auth = getAuth(map);
            Object obj = map.get(Constants.SIGN_IN_PROVIDER);
            Objects.requireNonNull(obj);
            List<String> list = (List) map.get(Constants.SIGN_IN_PROVIDER_SCOPE);
            Map<String, String> map2 = (Map) map.get(Constants.SIGN_IN_PROVIDER_CUSTOM_PARAMETERS);
            OAuthProvider.Builder newBuilder = OAuthProvider.newBuilder((String) obj);
            if (list != null) {
                newBuilder.setScopes(list);
            }
            if (map2 != null) {
                newBuilder.addCustomParameters(map2);
            }
            taskCompletionSource.setResult(parseAuthResult((AuthResult) Tasks.await(auth.startActivityForSignInWithProvider(this.activity, newBuilder.build()))));
        } catch (Exception e) {
            if (e.getCause() instanceof FirebaseAuthMultiFactorException) {
                handleMultiFactorException(map, taskCompletionSource, e);
            } else {
                taskCompletionSource.setException(e);
            }
        }
    }

    @Override // io.flutter.plugins.firebase.core.FlutterFirebasePlugin
    public Task<Map<String, Object>> getPluginConstantsForFirebaseApp(final FirebaseApp firebaseApp) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda39
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.lambda$getPluginConstantsForFirebaseApp$37(FirebaseApp.this, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getPluginConstantsForFirebaseApp$37(FirebaseApp firebaseApp, TaskCompletionSource taskCompletionSource) {
        try {
            HashMap hashMap = new HashMap();
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(firebaseApp);
            FirebaseUser currentUser = firebaseAuth.getCurrentUser();
            String languageCode = firebaseAuth.getLanguageCode();
            Map<String, Object> parseFirebaseUser = currentUser == null ? null : parseFirebaseUser(currentUser);
            if (languageCode != null) {
                hashMap.put("APP_LANGUAGE_CODE", languageCode);
            }
            if (parseFirebaseUser != null) {
                hashMap.put("APP_CURRENT_USER", parseFirebaseUser);
            }
            taskCompletionSource.setResult(hashMap);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, Object> getExceptionDetails(Exception exc) {
        Throwable cause;
        HashMap hashMap = new HashMap();
        if (exc == null) {
            return hashMap;
        }
        FlutterFirebaseAuthPluginException flutterFirebaseAuthPluginException = null;
        if (exc instanceof FirebaseAuthException) {
            flutterFirebaseAuthPluginException = new FlutterFirebaseAuthPluginException(exc, exc.getCause());
        } else if (exc.getCause() != null && (exc.getCause() instanceof FirebaseAuthException)) {
            FirebaseAuthException firebaseAuthException = (FirebaseAuthException) exc.getCause();
            if (exc.getCause().getCause() != null) {
                cause = exc.getCause().getCause();
            } else {
                cause = exc.getCause();
            }
            flutterFirebaseAuthPluginException = new FlutterFirebaseAuthPluginException(firebaseAuthException, cause);
        } else if (exc instanceof FlutterFirebaseAuthPluginException) {
            flutterFirebaseAuthPluginException = (FlutterFirebaseAuthPluginException) exc;
        }
        if (flutterFirebaseAuthPluginException != null) {
            hashMap.put("code", flutterFirebaseAuthPluginException.getCode());
            hashMap.put("message", flutterFirebaseAuthPluginException.getMessage());
            hashMap.put("additionalData", flutterFirebaseAuthPluginException.getAdditionalData());
            return hashMap;
        }
        if ((exc instanceof FirebaseNetworkException) || (exc.getCause() != null && (exc.getCause() instanceof FirebaseNetworkException))) {
            hashMap.put("code", "network-request-failed");
            hashMap.put("message", "A network error (such as timeout, interrupted connection or unreachable host) has occurred.");
            hashMap.put("additionalData", new HashMap());
            return hashMap;
        }
        if ((exc instanceof FirebaseApiNotAvailableException) || (exc.getCause() != null && (exc.getCause() instanceof FirebaseApiNotAvailableException))) {
            hashMap.put("code", "api-not-available");
            hashMap.put("message", "The requested API is not available.");
            hashMap.put("additionalData", new HashMap());
            return hashMap;
        }
        if ((exc instanceof FirebaseTooManyRequestsException) || (exc.getCause() != null && (exc.getCause() instanceof FirebaseTooManyRequestsException))) {
            hashMap.put("code", "too-many-requests");
            hashMap.put("message", "We have blocked all requests from this device due to unusual activity. Try again later.");
            hashMap.put("additionalData", new HashMap());
            return hashMap;
        }
        if (exc.getMessage() != null && exc.getMessage().startsWith("Cannot create PhoneAuthCredential without either verificationProof")) {
            hashMap.put("code", "invalid-verification-id");
            hashMap.put("message", "The verification ID used to create the phone auth credential is invalid.");
            hashMap.put("additionalData", new HashMap());
        }
        return hashMap;
    }

    @Override // io.flutter.plugins.firebase.core.FlutterFirebasePlugin
    public Task<Void> didReinitializeFirebaseCore() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        cachedThreadPool.execute(new Runnable() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda40
            @Override // java.lang.Runnable
            public final void run() {
                FlutterFirebaseAuthPlugin.this.m294x91524c80(taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$didReinitializeFirebaseCore$38$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m294x91524c80(TaskCompletionSource taskCompletionSource) {
        try {
            removeEventListeners();
            authCredentials.clear();
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    private void removeEventListeners() {
        for (EventChannel eventChannel : this.streamHandlers.keySet()) {
            this.streamHandlers.get(eventChannel).onCancel(null);
            eventChannel.setStreamHandler(null);
        }
        this.streamHandlers.clear();
    }

    private MultiFactor getAppMultiFactor(String str) throws FirebaseNoSignedInUserException {
        FirebaseUser currentUser = getCurrentUser(str);
        if (currentUser == null) {
            throw new FirebaseNoSignedInUserException("No user is signed in");
        }
        if (this.multiFactorUserMap.get(str) == null) {
            this.multiFactorUserMap.put(str, new HashMap());
        }
        Map<String, MultiFactor> map = this.multiFactorUserMap.get(str);
        if (map.get(currentUser.getUid()) == null) {
            map.put(currentUser.getUid(), currentUser.getMultiFactor());
        }
        return map.get(currentUser.getUid());
    }

    @Override // io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth.MultiFactorUserHostApi
    public void enrollPhone(String str, GeneratedAndroidFirebaseAuth.PigeonPhoneMultiFactorAssertion pigeonPhoneMultiFactorAssertion, String str2, final GeneratedAndroidFirebaseAuth.Result<Void> result) {
        try {
            getAppMultiFactor(str).enroll(PhoneMultiFactorGenerator.getAssertion(PhoneAuthProvider.getCredential(pigeonPhoneMultiFactorAssertion.getVerificationId(), pigeonPhoneMultiFactorAssertion.getVerificationCode())), str2).addOnCompleteListener(new OnCompleteListener() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda33
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    FlutterFirebaseAuthPlugin.lambda$enrollPhone$39(GeneratedAndroidFirebaseAuth.Result.this, task);
                }
            });
        } catch (FirebaseNoSignedInUserException e) {
            result.error(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$enrollPhone$39(GeneratedAndroidFirebaseAuth.Result result, Task task) {
        if (task.isSuccessful()) {
            result.success(null);
        } else {
            result.error(task.getException());
        }
    }

    @Override // io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth.MultiFactorUserHostApi
    public void getSession(String str, final GeneratedAndroidFirebaseAuth.Result<GeneratedAndroidFirebaseAuth.PigeonMultiFactorSession> result) {
        try {
            getAppMultiFactor(str).getSession().addOnCompleteListener(new OnCompleteListener() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda11
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    FlutterFirebaseAuthPlugin.this.m296x26f7f1bb(result, task);
                }
            });
        } catch (FirebaseNoSignedInUserException e) {
            result.error(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$getSession$40$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m296x26f7f1bb(GeneratedAndroidFirebaseAuth.Result result, Task task) {
        if (task.isSuccessful()) {
            MultiFactorSession multiFactorSession = (MultiFactorSession) task.getResult();
            String uuid = UUID.randomUUID().toString();
            this.multiFactorSessionMap.put(uuid, multiFactorSession);
            result.success(new GeneratedAndroidFirebaseAuth.PigeonMultiFactorSession.Builder().setId(uuid).build());
            return;
        }
        result.error(task.getException());
    }

    @Override // io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth.MultiFactorUserHostApi
    public void unenroll(String str, String str2, final GeneratedAndroidFirebaseAuth.Result<Void> result) {
        try {
            getAppMultiFactor(str).unenroll(str2).addOnCompleteListener(new OnCompleteListener() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda37
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    FlutterFirebaseAuthPlugin.lambda$unenroll$41(GeneratedAndroidFirebaseAuth.Result.this, task);
                }
            });
        } catch (FirebaseNoSignedInUserException e) {
            result.error(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$unenroll$41(GeneratedAndroidFirebaseAuth.Result result, Task task) {
        if (task.isSuccessful()) {
            result.success(null);
        } else {
            result.error(task.getException());
        }
    }

    @Override // io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth.MultiFactorUserHostApi
    public void getEnrolledFactors(String str, GeneratedAndroidFirebaseAuth.Result<List<GeneratedAndroidFirebaseAuth.PigeonMultiFactorInfo>> result) {
        try {
            result.success(multiFactorInfoToPigeon(getAppMultiFactor(str).getEnrolledFactors()));
        } catch (FirebaseNoSignedInUserException e) {
            result.error(e);
        }
    }

    @Override // io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth.MultiFactoResolverHostApi
    public void resolveSignIn(String str, GeneratedAndroidFirebaseAuth.PigeonPhoneMultiFactorAssertion pigeonPhoneMultiFactorAssertion, final GeneratedAndroidFirebaseAuth.Result<Map<String, Object>> result) {
        this.multiFactorResolverMap.get(str).resolveSignIn(PhoneMultiFactorGenerator.getAssertion(PhoneAuthProvider.getCredential(pigeonPhoneMultiFactorAssertion.getVerificationId(), pigeonPhoneMultiFactorAssertion.getVerificationCode()))).addOnCompleteListener(new OnCompleteListener() { // from class: io.flutter.plugins.firebase.auth.FlutterFirebaseAuthPlugin$$ExternalSyntheticLambda22
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                FlutterFirebaseAuthPlugin.this.m303xfb6130cd(result, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$resolveSignIn$42$io-flutter-plugins-firebase-auth-FlutterFirebaseAuthPlugin, reason: not valid java name */
    public /* synthetic */ void m303xfb6130cd(GeneratedAndroidFirebaseAuth.Result result, Task task) {
        if (task.isSuccessful()) {
            result.success(parseAuthResult((AuthResult) task.getResult()));
        } else {
            result.error(task.getException());
        }
    }
}
