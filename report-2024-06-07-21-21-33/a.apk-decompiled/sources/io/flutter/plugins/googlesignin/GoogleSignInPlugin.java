package io.flutter.plugins.googlesignin;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.RuntimeExecutionException;
import com.google.android.gms.tasks.Task;
import com.google.common.base.Joiner;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugins.firebase.auth.Constants;
import io.flutter.plugins.googlesignin.BackgroundTaskRunner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* loaded from: classes2.dex */
public class GoogleSignInPlugin implements MethodChannel.MethodCallHandler, FlutterPlugin, ActivityAware {
    private static final String CHANNEL_NAME = "plugins.flutter.io/google_sign_in_android";
    private static final String METHOD_CLEAR_AUTH_CACHE = "clearAuthCache";
    private static final String METHOD_DISCONNECT = "disconnect";
    private static final String METHOD_GET_TOKENS = "getTokens";
    private static final String METHOD_INIT = "init";
    private static final String METHOD_IS_SIGNED_IN = "isSignedIn";
    private static final String METHOD_REQUEST_SCOPES = "requestScopes";
    private static final String METHOD_SIGN_IN = "signIn";
    private static final String METHOD_SIGN_IN_SILENTLY = "signInSilently";
    private static final String METHOD_SIGN_OUT = "signOut";
    private ActivityPluginBinding activityPluginBinding;
    private MethodChannel channel;
    private Delegate delegate;

    /* loaded from: classes2.dex */
    public interface IDelegate {
        void clearAuthCache(MethodChannel.Result result, String str);

        void disconnect(MethodChannel.Result result);

        void getTokens(MethodChannel.Result result, String str, boolean z);

        void init(MethodChannel.Result result, String str, List<String> list, String str2, String str3, String str4, boolean z);

        void isSignedIn(MethodChannel.Result result);

        void requestScopes(MethodChannel.Result result, List<String> list);

        void signIn(MethodChannel.Result result);

        void signInSilently(MethodChannel.Result result);

        void signOut(MethodChannel.Result result);
    }

    public static void registerWith(PluginRegistry.Registrar registrar) {
        GoogleSignInPlugin googleSignInPlugin = new GoogleSignInPlugin();
        googleSignInPlugin.initInstance(registrar.messenger(), registrar.context(), new GoogleSignInWrapper());
        googleSignInPlugin.setUpRegistrar(registrar);
    }

    public void initInstance(BinaryMessenger binaryMessenger, Context context, GoogleSignInWrapper googleSignInWrapper) {
        this.channel = new MethodChannel(binaryMessenger, CHANNEL_NAME);
        this.delegate = new Delegate(context, googleSignInWrapper);
        this.channel.setMethodCallHandler(this);
    }

    public void setUpRegistrar(PluginRegistry.Registrar registrar) {
        this.delegate.setUpRegistrar(registrar);
    }

    private void dispose() {
        this.delegate = null;
        this.channel.setMethodCallHandler(null);
        this.channel = null;
    }

    private void attachToActivity(ActivityPluginBinding activityPluginBinding) {
        this.activityPluginBinding = activityPluginBinding;
        activityPluginBinding.addActivityResultListener(this.delegate);
        this.delegate.setActivity(activityPluginBinding.getActivity());
    }

    private void disposeActivity() {
        this.activityPluginBinding.removeActivityResultListener(this.delegate);
        this.delegate.setActivity(null);
        this.activityPluginBinding = null;
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        initInstance(flutterPluginBinding.getBinaryMessenger(), flutterPluginBinding.getApplicationContext(), new GoogleSignInWrapper());
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        dispose();
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onAttachedToActivity(ActivityPluginBinding activityPluginBinding) {
        attachToActivity(activityPluginBinding);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivityForConfigChanges() {
        disposeActivity();
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onReattachedToActivityForConfigChanges(ActivityPluginBinding activityPluginBinding) {
        attachToActivity(activityPluginBinding);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityAware
    public void onDetachedFromActivity() {
        disposeActivity();
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        String str = methodCall.method;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -902468670:
                if (str.equals(METHOD_SIGN_IN)) {
                    c = 0;
                    break;
                }
                break;
            case -638267772:
                if (str.equals(METHOD_SIGN_IN_SILENTLY)) {
                    c = 1;
                    break;
                }
                break;
            case -481441621:
                if (str.equals(METHOD_IS_SIGNED_IN)) {
                    c = 2;
                    break;
                }
                break;
            case 3237136:
                if (str.equals(METHOD_INIT)) {
                    c = 3;
                    break;
                }
                break;
            case 24140525:
                if (str.equals(METHOD_CLEAR_AUTH_CACHE)) {
                    c = 4;
                    break;
                }
                break;
            case 530405532:
                if (str.equals(METHOD_DISCONNECT)) {
                    c = 5;
                    break;
                }
                break;
            case 827828368:
                if (str.equals(METHOD_GET_TOKENS)) {
                    c = 6;
                    break;
                }
                break;
            case 1387660302:
                if (str.equals(METHOD_REQUEST_SCOPES)) {
                    c = 7;
                    break;
                }
                break;
            case 2088248401:
                if (str.equals(METHOD_SIGN_OUT)) {
                    c = '\b';
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.delegate.signIn(result);
                return;
            case 1:
                this.delegate.signInSilently(result);
                return;
            case 2:
                this.delegate.isSignedIn(result);
                return;
            case 3:
                this.delegate.init(result, (String) methodCall.argument("signInOption"), (List) methodCall.argument(Constants.SIGN_IN_PROVIDER_SCOPE), (String) methodCall.argument("hostedDomain"), (String) methodCall.argument("clientId"), (String) methodCall.argument("serverClientId"), ((Boolean) methodCall.argument("forceCodeForRefreshToken")).booleanValue());
                return;
            case 4:
                this.delegate.clearAuthCache(result, (String) methodCall.argument(Constants.TOKEN));
                return;
            case 5:
                this.delegate.disconnect(result);
                return;
            case 6:
                this.delegate.getTokens(result, (String) methodCall.argument("email"), ((Boolean) methodCall.argument("shouldRecoverAuth")).booleanValue());
                return;
            case 7:
                this.delegate.requestScopes(result, (List) methodCall.argument(Constants.SIGN_IN_PROVIDER_SCOPE));
                return;
            case '\b':
                this.delegate.signOut(result);
                return;
            default:
                result.notImplemented();
                return;
        }
    }

    /* loaded from: classes2.dex */
    public static class Delegate implements IDelegate, PluginRegistry.ActivityResultListener {
        private static final String DEFAULT_GAMES_SIGN_IN = "SignInOption.games";
        private static final String DEFAULT_SIGN_IN = "SignInOption.standard";
        private static final String ERROR_FAILURE_TO_RECOVER_AUTH = "failed_to_recover_auth";
        private static final String ERROR_REASON_EXCEPTION = "exception";
        private static final String ERROR_REASON_NETWORK_ERROR = "network_error";
        private static final String ERROR_REASON_SIGN_IN_CANCELED = "sign_in_canceled";
        private static final String ERROR_REASON_SIGN_IN_FAILED = "sign_in_failed";
        private static final String ERROR_REASON_SIGN_IN_REQUIRED = "sign_in_required";
        private static final String ERROR_REASON_STATUS = "status";
        private static final String ERROR_USER_RECOVERABLE_AUTH = "user_recoverable_auth";
        private static final int REQUEST_CODE_RECOVER_AUTH = 53294;
        static final int REQUEST_CODE_REQUEST_SCOPE = 53295;
        private static final int REQUEST_CODE_SIGNIN = 53293;
        private Activity activity;
        private final BackgroundTaskRunner backgroundTaskRunner = new BackgroundTaskRunner(1);
        private final Context context;
        private final GoogleSignInWrapper googleSignInWrapper;
        private PendingOperation pendingOperation;
        private PluginRegistry.Registrar registrar;
        private List<String> requestedScopes;
        private GoogleSignInClient signInClient;

        private String errorCodeForStatus(int i) {
            return i != 4 ? i != 7 ? i != 12501 ? ERROR_REASON_SIGN_IN_FAILED : ERROR_REASON_SIGN_IN_CANCELED : ERROR_REASON_NETWORK_ERROR : ERROR_REASON_SIGN_IN_REQUIRED;
        }

        public Delegate(Context context, GoogleSignInWrapper googleSignInWrapper) {
            this.context = context;
            this.googleSignInWrapper = googleSignInWrapper;
        }

        public void setUpRegistrar(PluginRegistry.Registrar registrar) {
            this.registrar = registrar;
            registrar.addActivityResultListener(this);
        }

        public void setActivity(Activity activity) {
            this.activity = activity;
        }

        public Activity getActivity() {
            PluginRegistry.Registrar registrar = this.registrar;
            return registrar != null ? registrar.activity() : this.activity;
        }

        private void checkAndSetPendingOperation(String str, MethodChannel.Result result) {
            checkAndSetPendingOperation(str, result, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void checkAndSetPendingOperation(String str, MethodChannel.Result result, Object obj) {
            if (this.pendingOperation != null) {
                throw new IllegalStateException("Concurrent operations detected: " + this.pendingOperation.method + ", " + str);
            }
            this.pendingOperation = new PendingOperation(str, result, obj);
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x002a, code lost:
        
            r8 = new com.google.android.gms.auth.api.signin.GoogleSignInOptions.Builder(com.google.android.gms.auth.api.signin.GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail();
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x003d, code lost:
        
            throw new java.lang.IllegalStateException("Unknown signInOption");
         */
        /* JADX WARN: Code restructure failed: missing block: B:9:0x0028, code lost:
        
            if (r0 != 1) goto L17;
         */
        @Override // io.flutter.plugins.googlesignin.GoogleSignInPlugin.IDelegate
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void init(io.flutter.plugin.common.MethodChannel.Result r7, java.lang.String r8, java.util.List<java.lang.String> r9, java.lang.String r10, java.lang.String r11, java.lang.String r12, boolean r13) {
            /*
                r6 = this;
                r0 = -1
                r1 = 0
                int r2 = r8.hashCode()     // Catch: java.lang.Exception -> Lc0
                r3 = 849126666(0x329ca50a, float:1.8235841E-8)
                r4 = 0
                r5 = 1
                if (r2 == r3) goto L1d
                r3 = 2056100820(0x7a8d9bd4, float:3.676372E35)
                if (r2 == r3) goto L13
                goto L26
            L13:
                java.lang.String r2 = "SignInOption.standard"
                boolean r8 = r8.equals(r2)     // Catch: java.lang.Exception -> Lc0
                if (r8 == 0) goto L26
                r0 = 1
                goto L26
            L1d:
                java.lang.String r2 = "SignInOption.games"
                boolean r8 = r8.equals(r2)     // Catch: java.lang.Exception -> Lc0
                if (r8 == 0) goto L26
                r0 = 0
            L26:
                if (r0 == 0) goto L3e
                if (r0 != r5) goto L36
                com.google.android.gms.auth.api.signin.GoogleSignInOptions$Builder r8 = new com.google.android.gms.auth.api.signin.GoogleSignInOptions$Builder     // Catch: java.lang.Exception -> Lc0
                com.google.android.gms.auth.api.signin.GoogleSignInOptions r0 = com.google.android.gms.auth.api.signin.GoogleSignInOptions.DEFAULT_SIGN_IN     // Catch: java.lang.Exception -> Lc0
                r8.<init>(r0)     // Catch: java.lang.Exception -> Lc0
                com.google.android.gms.auth.api.signin.GoogleSignInOptions$Builder r8 = r8.requestEmail()     // Catch: java.lang.Exception -> Lc0
                goto L45
            L36:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException     // Catch: java.lang.Exception -> Lc0
                java.lang.String r9 = "Unknown signInOption"
                r8.<init>(r9)     // Catch: java.lang.Exception -> Lc0
                throw r8     // Catch: java.lang.Exception -> Lc0
            L3e:
                com.google.android.gms.auth.api.signin.GoogleSignInOptions$Builder r8 = new com.google.android.gms.auth.api.signin.GoogleSignInOptions$Builder     // Catch: java.lang.Exception -> Lc0
                com.google.android.gms.auth.api.signin.GoogleSignInOptions r0 = com.google.android.gms.auth.api.signin.GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN     // Catch: java.lang.Exception -> Lc0
                r8.<init>(r0)     // Catch: java.lang.Exception -> Lc0
            L45:
                boolean r0 = com.google.common.base.Strings.isNullOrEmpty(r11)     // Catch: java.lang.Exception -> Lc0
                if (r0 != 0) goto L59
                boolean r0 = com.google.common.base.Strings.isNullOrEmpty(r12)     // Catch: java.lang.Exception -> Lc0
                if (r0 == 0) goto L59
                java.lang.String r12 = "google_sign_in"
                java.lang.String r0 = "clientId is not supported on Android and is interpreted as serverClientId. Use serverClientId instead to suppress this warning."
                android.util.Log.w(r12, r0)     // Catch: java.lang.Exception -> Lc0
                goto L5a
            L59:
                r11 = r12
            L5a:
                boolean r12 = com.google.common.base.Strings.isNullOrEmpty(r11)     // Catch: java.lang.Exception -> Lc0
                if (r12 == 0) goto L7c
                android.content.Context r12 = r6.context     // Catch: java.lang.Exception -> Lc0
                android.content.res.Resources r12 = r12.getResources()     // Catch: java.lang.Exception -> Lc0
                java.lang.String r0 = "default_web_client_id"
                java.lang.String r2 = "string"
                android.content.Context r3 = r6.context     // Catch: java.lang.Exception -> Lc0
                java.lang.String r3 = r3.getPackageName()     // Catch: java.lang.Exception -> Lc0
                int r12 = r12.getIdentifier(r0, r2, r3)     // Catch: java.lang.Exception -> Lc0
                if (r12 == 0) goto L7c
                android.content.Context r11 = r6.context     // Catch: java.lang.Exception -> Lc0
                java.lang.String r11 = r11.getString(r12)     // Catch: java.lang.Exception -> Lc0
            L7c:
                boolean r12 = com.google.common.base.Strings.isNullOrEmpty(r11)     // Catch: java.lang.Exception -> Lc0
                if (r12 != 0) goto L88
                r8.requestIdToken(r11)     // Catch: java.lang.Exception -> Lc0
                r8.requestServerAuthCode(r11, r13)     // Catch: java.lang.Exception -> Lc0
            L88:
                java.util.Iterator r11 = r9.iterator()     // Catch: java.lang.Exception -> Lc0
            L8c:
                boolean r12 = r11.hasNext()     // Catch: java.lang.Exception -> Lc0
                if (r12 == 0) goto La3
                java.lang.Object r12 = r11.next()     // Catch: java.lang.Exception -> Lc0
                java.lang.String r12 = (java.lang.String) r12     // Catch: java.lang.Exception -> Lc0
                com.google.android.gms.common.api.Scope r13 = new com.google.android.gms.common.api.Scope     // Catch: java.lang.Exception -> Lc0
                r13.<init>(r12)     // Catch: java.lang.Exception -> Lc0
                com.google.android.gms.common.api.Scope[] r12 = new com.google.android.gms.common.api.Scope[r4]     // Catch: java.lang.Exception -> Lc0
                r8.requestScopes(r13, r12)     // Catch: java.lang.Exception -> Lc0
                goto L8c
            La3:
                boolean r11 = com.google.common.base.Strings.isNullOrEmpty(r10)     // Catch: java.lang.Exception -> Lc0
                if (r11 != 0) goto Lac
                r8.setHostedDomain(r10)     // Catch: java.lang.Exception -> Lc0
            Lac:
                r6.requestedScopes = r9     // Catch: java.lang.Exception -> Lc0
                io.flutter.plugins.googlesignin.GoogleSignInWrapper r9 = r6.googleSignInWrapper     // Catch: java.lang.Exception -> Lc0
                android.content.Context r10 = r6.context     // Catch: java.lang.Exception -> Lc0
                com.google.android.gms.auth.api.signin.GoogleSignInOptions r8 = r8.build()     // Catch: java.lang.Exception -> Lc0
                com.google.android.gms.auth.api.signin.GoogleSignInClient r8 = r9.getClient(r10, r8)     // Catch: java.lang.Exception -> Lc0
                r6.signInClient = r8     // Catch: java.lang.Exception -> Lc0
                r7.success(r1)     // Catch: java.lang.Exception -> Lc0
                goto Lca
            Lc0:
                r8 = move-exception
                java.lang.String r8 = r8.getMessage()
                java.lang.String r9 = "exception"
                r7.error(r9, r8, r1)
            Lca:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.flutter.plugins.googlesignin.GoogleSignInPlugin.Delegate.init(io.flutter.plugin.common.MethodChannel$Result, java.lang.String, java.util.List, java.lang.String, java.lang.String, java.lang.String, boolean):void");
        }

        @Override // io.flutter.plugins.googlesignin.GoogleSignInPlugin.IDelegate
        public void signInSilently(MethodChannel.Result result) {
            checkAndSetPendingOperation(GoogleSignInPlugin.METHOD_SIGN_IN_SILENTLY, result);
            Task<GoogleSignInAccount> silentSignIn = this.signInClient.silentSignIn();
            if (silentSignIn.isComplete()) {
                onSignInResult(silentSignIn);
            } else {
                silentSignIn.addOnCompleteListener(new OnCompleteListener<GoogleSignInAccount>() { // from class: io.flutter.plugins.googlesignin.GoogleSignInPlugin.Delegate.1
                    @Override // com.google.android.gms.tasks.OnCompleteListener
                    public void onComplete(Task<GoogleSignInAccount> task) {
                        Delegate.this.onSignInResult(task);
                    }
                });
            }
        }

        @Override // io.flutter.plugins.googlesignin.GoogleSignInPlugin.IDelegate
        public void signIn(MethodChannel.Result result) {
            if (getActivity() == null) {
                throw new IllegalStateException("signIn needs a foreground activity");
            }
            checkAndSetPendingOperation(GoogleSignInPlugin.METHOD_SIGN_IN, result);
            getActivity().startActivityForResult(this.signInClient.getSignInIntent(), REQUEST_CODE_SIGNIN);
        }

        @Override // io.flutter.plugins.googlesignin.GoogleSignInPlugin.IDelegate
        public void signOut(MethodChannel.Result result) {
            checkAndSetPendingOperation(GoogleSignInPlugin.METHOD_SIGN_OUT, result);
            this.signInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() { // from class: io.flutter.plugins.googlesignin.GoogleSignInPlugin.Delegate.2
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public void onComplete(Task<Void> task) {
                    if (task.isSuccessful()) {
                        Delegate.this.finishWithSuccess(null);
                    } else {
                        Delegate.this.finishWithError("status", "Failed to signout.");
                    }
                }
            });
        }

        @Override // io.flutter.plugins.googlesignin.GoogleSignInPlugin.IDelegate
        public void disconnect(MethodChannel.Result result) {
            checkAndSetPendingOperation(GoogleSignInPlugin.METHOD_DISCONNECT, result);
            this.signInClient.revokeAccess().addOnCompleteListener(new OnCompleteListener<Void>() { // from class: io.flutter.plugins.googlesignin.GoogleSignInPlugin.Delegate.3
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public void onComplete(Task<Void> task) {
                    if (task.isSuccessful()) {
                        Delegate.this.finishWithSuccess(null);
                    } else {
                        Delegate.this.finishWithError("status", "Failed to disconnect.");
                    }
                }
            });
        }

        @Override // io.flutter.plugins.googlesignin.GoogleSignInPlugin.IDelegate
        public void isSignedIn(MethodChannel.Result result) {
            result.success(Boolean.valueOf(GoogleSignIn.getLastSignedInAccount(this.context) != null));
        }

        @Override // io.flutter.plugins.googlesignin.GoogleSignInPlugin.IDelegate
        public void requestScopes(MethodChannel.Result result, List<String> list) {
            checkAndSetPendingOperation(GoogleSignInPlugin.METHOD_REQUEST_SCOPES, result);
            GoogleSignInAccount lastSignedInAccount = this.googleSignInWrapper.getLastSignedInAccount(this.context);
            if (lastSignedInAccount == null) {
                finishWithError(ERROR_REASON_SIGN_IN_REQUIRED, "No account to grant scopes.");
                return;
            }
            ArrayList arrayList = new ArrayList();
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                Scope scope = new Scope(it.next());
                if (!this.googleSignInWrapper.hasPermissions(lastSignedInAccount, scope)) {
                    arrayList.add(scope);
                }
            }
            if (arrayList.isEmpty()) {
                finishWithSuccess(true);
            } else {
                this.googleSignInWrapper.requestPermissions(getActivity(), REQUEST_CODE_REQUEST_SCOPE, lastSignedInAccount, (Scope[]) arrayList.toArray(new Scope[0]));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onSignInResult(Task<GoogleSignInAccount> task) {
            try {
                onSignInAccount(task.getResult(ApiException.class));
            } catch (ApiException e) {
                finishWithError(errorCodeForStatus(e.getStatusCode()), e.toString());
            } catch (RuntimeExecutionException e2) {
                finishWithError("exception", e2.toString());
            }
        }

        private void onSignInAccount(GoogleSignInAccount googleSignInAccount) {
            HashMap hashMap = new HashMap();
            hashMap.put("email", googleSignInAccount.getEmail());
            hashMap.put("id", googleSignInAccount.getId());
            hashMap.put(Constants.ID_TOKEN, googleSignInAccount.getIdToken());
            hashMap.put("serverAuthCode", googleSignInAccount.getServerAuthCode());
            hashMap.put(Constants.DISPLAY_NAME, googleSignInAccount.getDisplayName());
            if (googleSignInAccount.getPhotoUrl() != null) {
                hashMap.put("photoUrl", googleSignInAccount.getPhotoUrl().toString());
            }
            finishWithSuccess(hashMap);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void finishWithSuccess(Object obj) {
            this.pendingOperation.result.success(obj);
            this.pendingOperation = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void finishWithError(String str, String str2) {
            this.pendingOperation.result.error(str, str2, null);
            this.pendingOperation = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class PendingOperation {
            final Object data;
            final String method;
            final MethodChannel.Result result;

            PendingOperation(String str, MethodChannel.Result result, Object obj) {
                this.method = str;
                this.result = result;
                this.data = obj;
            }
        }

        @Override // io.flutter.plugins.googlesignin.GoogleSignInPlugin.IDelegate
        public void clearAuthCache(final MethodChannel.Result result, final String str) {
            this.backgroundTaskRunner.runInBackground(new Callable<Void>() { // from class: io.flutter.plugins.googlesignin.GoogleSignInPlugin.Delegate.4
                @Override // java.util.concurrent.Callable
                public Void call() throws Exception {
                    GoogleAuthUtil.clearToken(Delegate.this.context, str);
                    return null;
                }
            }, new BackgroundTaskRunner.Callback<Void>() { // from class: io.flutter.plugins.googlesignin.GoogleSignInPlugin.Delegate.5
                @Override // io.flutter.plugins.googlesignin.BackgroundTaskRunner.Callback
                public void run(Future<Void> future) {
                    try {
                        result.success(future.get());
                    } catch (InterruptedException e) {
                        result.error("exception", e.getMessage(), null);
                        Thread.currentThread().interrupt();
                    } catch (ExecutionException e2) {
                        result.error("exception", e2.getCause().getMessage(), null);
                    }
                }
            });
        }

        @Override // io.flutter.plugins.googlesignin.GoogleSignInPlugin.IDelegate
        public void getTokens(final MethodChannel.Result result, final String str, final boolean z) {
            if (str == null) {
                result.error("exception", "Email is null", null);
            } else {
                this.backgroundTaskRunner.runInBackground(new Callable<String>() { // from class: io.flutter.plugins.googlesignin.GoogleSignInPlugin.Delegate.6
                    @Override // java.util.concurrent.Callable
                    public String call() throws Exception {
                        return GoogleAuthUtil.getToken(Delegate.this.context, new Account(str, "com.google"), "oauth2:" + Joiner.on(' ').join(Delegate.this.requestedScopes));
                    }
                }, new BackgroundTaskRunner.Callback<String>() { // from class: io.flutter.plugins.googlesignin.GoogleSignInPlugin.Delegate.7
                    @Override // io.flutter.plugins.googlesignin.BackgroundTaskRunner.Callback
                    public void run(Future<String> future) {
                        try {
                            String str2 = future.get();
                            HashMap hashMap = new HashMap();
                            hashMap.put(Constants.ACCESS_TOKEN, str2);
                            result.success(hashMap);
                        } catch (InterruptedException e) {
                            result.error("exception", e.getMessage(), null);
                            Thread.currentThread().interrupt();
                        } catch (ExecutionException e2) {
                            if (e2.getCause() instanceof UserRecoverableAuthException) {
                                if (z && Delegate.this.pendingOperation == null) {
                                    Activity activity = Delegate.this.getActivity();
                                    if (activity != null) {
                                        Delegate.this.checkAndSetPendingOperation(GoogleSignInPlugin.METHOD_GET_TOKENS, result, str);
                                        activity.startActivityForResult(((UserRecoverableAuthException) e2.getCause()).getIntent(), Delegate.REQUEST_CODE_RECOVER_AUTH);
                                        return;
                                    }
                                    result.error(Delegate.ERROR_USER_RECOVERABLE_AUTH, "Cannot recover auth because app is not in foreground. " + e2.getLocalizedMessage(), null);
                                    return;
                                }
                                result.error(Delegate.ERROR_USER_RECOVERABLE_AUTH, e2.getLocalizedMessage(), null);
                                return;
                            }
                            result.error("exception", e2.getCause().getMessage(), null);
                        }
                    }
                });
            }
        }

        @Override // io.flutter.plugin.common.PluginRegistry.ActivityResultListener
        public boolean onActivityResult(int i, int i2, Intent intent) {
            PendingOperation pendingOperation = this.pendingOperation;
            if (pendingOperation == null) {
                return false;
            }
            switch (i) {
                case REQUEST_CODE_SIGNIN /* 53293 */:
                    if (intent != null) {
                        onSignInResult(GoogleSignIn.getSignedInAccountFromIntent(intent));
                    } else {
                        finishWithError(ERROR_REASON_SIGN_IN_FAILED, "Signin failed");
                    }
                    return true;
                case REQUEST_CODE_RECOVER_AUTH /* 53294 */:
                    if (i2 == -1) {
                        MethodChannel.Result result = pendingOperation.result;
                        String str = (String) this.pendingOperation.data;
                        this.pendingOperation = null;
                        getTokens(result, str, false);
                    } else {
                        finishWithError(ERROR_FAILURE_TO_RECOVER_AUTH, "Failed attempt to recover authentication");
                    }
                    return true;
                case REQUEST_CODE_REQUEST_SCOPE /* 53295 */:
                    finishWithSuccess(Boolean.valueOf(i2 == -1));
                    return true;
                default:
                    return false;
            }
        }
    }
}
