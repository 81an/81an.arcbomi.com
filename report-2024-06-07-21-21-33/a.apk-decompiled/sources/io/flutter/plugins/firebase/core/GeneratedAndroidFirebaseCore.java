package io.flutter.plugins.firebase.core;

import android.util.Log;
import com.tekartik.sqflite.Constant;
import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MessageCodec;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore;
import io.flutter.plugins.firebase.database.Constants;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class GeneratedAndroidFirebaseCore {

    /* loaded from: classes2.dex */
    public interface Result<T> {
        void error(Throwable th);

        void success(T t);
    }

    /* loaded from: classes2.dex */
    public static class PigeonFirebaseOptions {
        private String androidClientId;
        private String apiKey;
        private String appGroupId;
        private String appId;
        private String authDomain;
        private String databaseURL;
        private String deepLinkURLScheme;
        private String iosBundleId;
        private String iosClientId;
        private String measurementId;
        private String messagingSenderId;
        private String projectId;
        private String storageBucket;
        private String trackingId;

        public String getApiKey() {
            return this.apiKey;
        }

        public void setApiKey(String str) {
            if (str == null) {
                throw new IllegalStateException("Nonnull field \"apiKey\" is null.");
            }
            this.apiKey = str;
        }

        public String getAppId() {
            return this.appId;
        }

        public void setAppId(String str) {
            if (str == null) {
                throw new IllegalStateException("Nonnull field \"appId\" is null.");
            }
            this.appId = str;
        }

        public String getMessagingSenderId() {
            return this.messagingSenderId;
        }

        public void setMessagingSenderId(String str) {
            if (str == null) {
                throw new IllegalStateException("Nonnull field \"messagingSenderId\" is null.");
            }
            this.messagingSenderId = str;
        }

        public String getProjectId() {
            return this.projectId;
        }

        public void setProjectId(String str) {
            if (str == null) {
                throw new IllegalStateException("Nonnull field \"projectId\" is null.");
            }
            this.projectId = str;
        }

        public String getAuthDomain() {
            return this.authDomain;
        }

        public void setAuthDomain(String str) {
            this.authDomain = str;
        }

        public String getDatabaseURL() {
            return this.databaseURL;
        }

        public void setDatabaseURL(String str) {
            this.databaseURL = str;
        }

        public String getStorageBucket() {
            return this.storageBucket;
        }

        public void setStorageBucket(String str) {
            this.storageBucket = str;
        }

        public String getMeasurementId() {
            return this.measurementId;
        }

        public void setMeasurementId(String str) {
            this.measurementId = str;
        }

        public String getTrackingId() {
            return this.trackingId;
        }

        public void setTrackingId(String str) {
            this.trackingId = str;
        }

        public String getDeepLinkURLScheme() {
            return this.deepLinkURLScheme;
        }

        public void setDeepLinkURLScheme(String str) {
            this.deepLinkURLScheme = str;
        }

        public String getAndroidClientId() {
            return this.androidClientId;
        }

        public void setAndroidClientId(String str) {
            this.androidClientId = str;
        }

        public String getIosClientId() {
            return this.iosClientId;
        }

        public void setIosClientId(String str) {
            this.iosClientId = str;
        }

        public String getIosBundleId() {
            return this.iosBundleId;
        }

        public void setIosBundleId(String str) {
            this.iosBundleId = str;
        }

        public String getAppGroupId() {
            return this.appGroupId;
        }

        public void setAppGroupId(String str) {
            this.appGroupId = str;
        }

        private PigeonFirebaseOptions() {
        }

        /* loaded from: classes2.dex */
        public static final class Builder {
            private String androidClientId;
            private String apiKey;
            private String appGroupId;
            private String appId;
            private String authDomain;
            private String databaseURL;
            private String deepLinkURLScheme;
            private String iosBundleId;
            private String iosClientId;
            private String measurementId;
            private String messagingSenderId;
            private String projectId;
            private String storageBucket;
            private String trackingId;

            public Builder setApiKey(String str) {
                this.apiKey = str;
                return this;
            }

            public Builder setAppId(String str) {
                this.appId = str;
                return this;
            }

            public Builder setMessagingSenderId(String str) {
                this.messagingSenderId = str;
                return this;
            }

            public Builder setProjectId(String str) {
                this.projectId = str;
                return this;
            }

            public Builder setAuthDomain(String str) {
                this.authDomain = str;
                return this;
            }

            public Builder setDatabaseURL(String str) {
                this.databaseURL = str;
                return this;
            }

            public Builder setStorageBucket(String str) {
                this.storageBucket = str;
                return this;
            }

            public Builder setMeasurementId(String str) {
                this.measurementId = str;
                return this;
            }

            public Builder setTrackingId(String str) {
                this.trackingId = str;
                return this;
            }

            public Builder setDeepLinkURLScheme(String str) {
                this.deepLinkURLScheme = str;
                return this;
            }

            public Builder setAndroidClientId(String str) {
                this.androidClientId = str;
                return this;
            }

            public Builder setIosClientId(String str) {
                this.iosClientId = str;
                return this;
            }

            public Builder setIosBundleId(String str) {
                this.iosBundleId = str;
                return this;
            }

            public Builder setAppGroupId(String str) {
                this.appGroupId = str;
                return this;
            }

            public PigeonFirebaseOptions build() {
                PigeonFirebaseOptions pigeonFirebaseOptions = new PigeonFirebaseOptions();
                pigeonFirebaseOptions.setApiKey(this.apiKey);
                pigeonFirebaseOptions.setAppId(this.appId);
                pigeonFirebaseOptions.setMessagingSenderId(this.messagingSenderId);
                pigeonFirebaseOptions.setProjectId(this.projectId);
                pigeonFirebaseOptions.setAuthDomain(this.authDomain);
                pigeonFirebaseOptions.setDatabaseURL(this.databaseURL);
                pigeonFirebaseOptions.setStorageBucket(this.storageBucket);
                pigeonFirebaseOptions.setMeasurementId(this.measurementId);
                pigeonFirebaseOptions.setTrackingId(this.trackingId);
                pigeonFirebaseOptions.setDeepLinkURLScheme(this.deepLinkURLScheme);
                pigeonFirebaseOptions.setAndroidClientId(this.androidClientId);
                pigeonFirebaseOptions.setIosClientId(this.iosClientId);
                pigeonFirebaseOptions.setIosBundleId(this.iosBundleId);
                pigeonFirebaseOptions.setAppGroupId(this.appGroupId);
                return pigeonFirebaseOptions;
            }
        }

        Map<String, Object> toMap() {
            HashMap hashMap = new HashMap();
            hashMap.put("apiKey", this.apiKey);
            hashMap.put("appId", this.appId);
            hashMap.put("messagingSenderId", this.messagingSenderId);
            hashMap.put("projectId", this.projectId);
            hashMap.put("authDomain", this.authDomain);
            hashMap.put(Constants.DATABASE_URL, this.databaseURL);
            hashMap.put("storageBucket", this.storageBucket);
            hashMap.put("measurementId", this.measurementId);
            hashMap.put("trackingId", this.trackingId);
            hashMap.put("deepLinkURLScheme", this.deepLinkURLScheme);
            hashMap.put("androidClientId", this.androidClientId);
            hashMap.put("iosClientId", this.iosClientId);
            hashMap.put("iosBundleId", this.iosBundleId);
            hashMap.put("appGroupId", this.appGroupId);
            return hashMap;
        }

        static PigeonFirebaseOptions fromMap(Map<String, Object> map) {
            PigeonFirebaseOptions pigeonFirebaseOptions = new PigeonFirebaseOptions();
            pigeonFirebaseOptions.setApiKey((String) map.get("apiKey"));
            pigeonFirebaseOptions.setAppId((String) map.get("appId"));
            pigeonFirebaseOptions.setMessagingSenderId((String) map.get("messagingSenderId"));
            pigeonFirebaseOptions.setProjectId((String) map.get("projectId"));
            pigeonFirebaseOptions.setAuthDomain((String) map.get("authDomain"));
            pigeonFirebaseOptions.setDatabaseURL((String) map.get(Constants.DATABASE_URL));
            pigeonFirebaseOptions.setStorageBucket((String) map.get("storageBucket"));
            pigeonFirebaseOptions.setMeasurementId((String) map.get("measurementId"));
            pigeonFirebaseOptions.setTrackingId((String) map.get("trackingId"));
            pigeonFirebaseOptions.setDeepLinkURLScheme((String) map.get("deepLinkURLScheme"));
            pigeonFirebaseOptions.setAndroidClientId((String) map.get("androidClientId"));
            pigeonFirebaseOptions.setIosClientId((String) map.get("iosClientId"));
            pigeonFirebaseOptions.setIosBundleId((String) map.get("iosBundleId"));
            pigeonFirebaseOptions.setAppGroupId((String) map.get("appGroupId"));
            return pigeonFirebaseOptions;
        }
    }

    /* loaded from: classes2.dex */
    public static class PigeonInitializeResponse {
        private Boolean isAutomaticDataCollectionEnabled;
        private String name;
        private PigeonFirebaseOptions options;
        private Map<String, Object> pluginConstants;

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            if (str == null) {
                throw new IllegalStateException("Nonnull field \"name\" is null.");
            }
            this.name = str;
        }

        public PigeonFirebaseOptions getOptions() {
            return this.options;
        }

        public void setOptions(PigeonFirebaseOptions pigeonFirebaseOptions) {
            if (pigeonFirebaseOptions == null) {
                throw new IllegalStateException("Nonnull field \"options\" is null.");
            }
            this.options = pigeonFirebaseOptions;
        }

        public Boolean getIsAutomaticDataCollectionEnabled() {
            return this.isAutomaticDataCollectionEnabled;
        }

        public void setIsAutomaticDataCollectionEnabled(Boolean bool) {
            this.isAutomaticDataCollectionEnabled = bool;
        }

        public Map<String, Object> getPluginConstants() {
            return this.pluginConstants;
        }

        public void setPluginConstants(Map<String, Object> map) {
            if (map == null) {
                throw new IllegalStateException("Nonnull field \"pluginConstants\" is null.");
            }
            this.pluginConstants = map;
        }

        private PigeonInitializeResponse() {
        }

        /* loaded from: classes2.dex */
        public static final class Builder {
            private Boolean isAutomaticDataCollectionEnabled;
            private String name;
            private PigeonFirebaseOptions options;
            private Map<String, Object> pluginConstants;

            public Builder setName(String str) {
                this.name = str;
                return this;
            }

            public Builder setOptions(PigeonFirebaseOptions pigeonFirebaseOptions) {
                this.options = pigeonFirebaseOptions;
                return this;
            }

            public Builder setIsAutomaticDataCollectionEnabled(Boolean bool) {
                this.isAutomaticDataCollectionEnabled = bool;
                return this;
            }

            public Builder setPluginConstants(Map<String, Object> map) {
                this.pluginConstants = map;
                return this;
            }

            public PigeonInitializeResponse build() {
                PigeonInitializeResponse pigeonInitializeResponse = new PigeonInitializeResponse();
                pigeonInitializeResponse.setName(this.name);
                pigeonInitializeResponse.setOptions(this.options);
                pigeonInitializeResponse.setIsAutomaticDataCollectionEnabled(this.isAutomaticDataCollectionEnabled);
                pigeonInitializeResponse.setPluginConstants(this.pluginConstants);
                return pigeonInitializeResponse;
            }
        }

        Map<String, Object> toMap() {
            HashMap hashMap = new HashMap();
            hashMap.put("name", this.name);
            PigeonFirebaseOptions pigeonFirebaseOptions = this.options;
            hashMap.put(Constant.METHOD_OPTIONS, pigeonFirebaseOptions == null ? null : pigeonFirebaseOptions.toMap());
            hashMap.put("isAutomaticDataCollectionEnabled", this.isAutomaticDataCollectionEnabled);
            hashMap.put("pluginConstants", this.pluginConstants);
            return hashMap;
        }

        static PigeonInitializeResponse fromMap(Map<String, Object> map) {
            PigeonInitializeResponse pigeonInitializeResponse = new PigeonInitializeResponse();
            pigeonInitializeResponse.setName((String) map.get("name"));
            Object obj = map.get(Constant.METHOD_OPTIONS);
            pigeonInitializeResponse.setOptions(obj == null ? null : PigeonFirebaseOptions.fromMap((Map) obj));
            pigeonInitializeResponse.setIsAutomaticDataCollectionEnabled((Boolean) map.get("isAutomaticDataCollectionEnabled"));
            pigeonInitializeResponse.setPluginConstants((Map) map.get("pluginConstants"));
            return pigeonInitializeResponse;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class FirebaseCoreHostApiCodec extends StandardMessageCodec {
        public static final FirebaseCoreHostApiCodec INSTANCE = new FirebaseCoreHostApiCodec();

        private FirebaseCoreHostApiCodec() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.flutter.plugin.common.StandardMessageCodec
        public Object readValueOfType(byte b, ByteBuffer byteBuffer) {
            if (b == Byte.MIN_VALUE) {
                return PigeonFirebaseOptions.fromMap((Map) readValue(byteBuffer));
            }
            if (b == -127) {
                return PigeonInitializeResponse.fromMap((Map) readValue(byteBuffer));
            }
            return super.readValueOfType(b, byteBuffer);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.flutter.plugin.common.StandardMessageCodec
        public void writeValue(ByteArrayOutputStream byteArrayOutputStream, Object obj) {
            if (obj instanceof PigeonFirebaseOptions) {
                byteArrayOutputStream.write(128);
                writeValue(byteArrayOutputStream, ((PigeonFirebaseOptions) obj).toMap());
            } else if (obj instanceof PigeonInitializeResponse) {
                byteArrayOutputStream.write(129);
                writeValue(byteArrayOutputStream, ((PigeonInitializeResponse) obj).toMap());
            } else {
                super.writeValue(byteArrayOutputStream, obj);
            }
        }
    }

    /* loaded from: classes2.dex */
    public interface FirebaseCoreHostApi {
        void initializeApp(String str, PigeonFirebaseOptions pigeonFirebaseOptions, Result<PigeonInitializeResponse> result);

        void initializeCore(Result<List<PigeonInitializeResponse>> result);

        void optionsFromResource(Result<PigeonFirebaseOptions> result);

        /* renamed from: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore$FirebaseCoreHostApi$-CC, reason: invalid class name */
        /* loaded from: classes2.dex */
        public final /* synthetic */ class CC {
            public static MessageCodec<Object> getCodec() {
                return FirebaseCoreHostApiCodec.INSTANCE;
            }

            public static void setup(BinaryMessenger binaryMessenger, final FirebaseCoreHostApi firebaseCoreHostApi) {
                BasicMessageChannel basicMessageChannel = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.FirebaseCoreHostApi.initializeApp", getCodec());
                if (firebaseCoreHostApi != null) {
                    basicMessageChannel.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore$FirebaseCoreHostApi$$ExternalSyntheticLambda0
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidFirebaseCore.FirebaseCoreHostApi.CC.lambda$setup$0(GeneratedAndroidFirebaseCore.FirebaseCoreHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel2 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.FirebaseCoreHostApi.initializeCore", getCodec());
                if (firebaseCoreHostApi != null) {
                    basicMessageChannel2.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore$FirebaseCoreHostApi$$ExternalSyntheticLambda1
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidFirebaseCore.FirebaseCoreHostApi.CC.lambda$setup$1(GeneratedAndroidFirebaseCore.FirebaseCoreHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel2.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel3 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.FirebaseCoreHostApi.optionsFromResource", getCodec());
                if (firebaseCoreHostApi != null) {
                    basicMessageChannel3.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore$FirebaseCoreHostApi$$ExternalSyntheticLambda2
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidFirebaseCore.FirebaseCoreHostApi.CC.lambda$setup$2(GeneratedAndroidFirebaseCore.FirebaseCoreHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel3.setMessageHandler(null);
                }
            }

            public static /* synthetic */ void lambda$setup$0(FirebaseCoreHostApi firebaseCoreHostApi, Object obj, final BasicMessageChannel.Reply reply) {
                final HashMap hashMap = new HashMap();
                try {
                    ArrayList arrayList = (ArrayList) obj;
                    String str = (String) arrayList.get(0);
                    if (str == null) {
                        throw new NullPointerException("appNameArg unexpectedly null.");
                    }
                    PigeonFirebaseOptions pigeonFirebaseOptions = (PigeonFirebaseOptions) arrayList.get(1);
                    if (pigeonFirebaseOptions == null) {
                        throw new NullPointerException("initializeAppRequestArg unexpectedly null.");
                    }
                    firebaseCoreHostApi.initializeApp(str, pigeonFirebaseOptions, new Result<PigeonInitializeResponse>() { // from class: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.FirebaseCoreHostApi.1
                        @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.Result
                        public void success(PigeonInitializeResponse pigeonInitializeResponse) {
                            hashMap.put(Constant.PARAM_RESULT, pigeonInitializeResponse);
                            reply.reply(hashMap);
                        }

                        @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.Result
                        public void error(Throwable th) {
                            hashMap.put("error", GeneratedAndroidFirebaseCore.wrapError(th));
                            reply.reply(hashMap);
                        }
                    });
                } catch (Error | RuntimeException e) {
                    hashMap.put("error", GeneratedAndroidFirebaseCore.wrapError(e));
                    reply.reply(hashMap);
                }
            }

            public static /* synthetic */ void lambda$setup$1(FirebaseCoreHostApi firebaseCoreHostApi, Object obj, final BasicMessageChannel.Reply reply) {
                final HashMap hashMap = new HashMap();
                try {
                    firebaseCoreHostApi.initializeCore(new Result<List<PigeonInitializeResponse>>() { // from class: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.FirebaseCoreHostApi.2
                        @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.Result
                        public void success(List<PigeonInitializeResponse> list) {
                            hashMap.put(Constant.PARAM_RESULT, list);
                            reply.reply(hashMap);
                        }

                        @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.Result
                        public void error(Throwable th) {
                            hashMap.put("error", GeneratedAndroidFirebaseCore.wrapError(th));
                            reply.reply(hashMap);
                        }
                    });
                } catch (Error | RuntimeException e) {
                    hashMap.put("error", GeneratedAndroidFirebaseCore.wrapError(e));
                    reply.reply(hashMap);
                }
            }

            public static /* synthetic */ void lambda$setup$2(FirebaseCoreHostApi firebaseCoreHostApi, Object obj, final BasicMessageChannel.Reply reply) {
                final HashMap hashMap = new HashMap();
                try {
                    firebaseCoreHostApi.optionsFromResource(new Result<PigeonFirebaseOptions>() { // from class: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.FirebaseCoreHostApi.3
                        @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.Result
                        public void success(PigeonFirebaseOptions pigeonFirebaseOptions) {
                            hashMap.put(Constant.PARAM_RESULT, pigeonFirebaseOptions);
                            reply.reply(hashMap);
                        }

                        @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.Result
                        public void error(Throwable th) {
                            hashMap.put("error", GeneratedAndroidFirebaseCore.wrapError(th));
                            reply.reply(hashMap);
                        }
                    });
                } catch (Error | RuntimeException e) {
                    hashMap.put("error", GeneratedAndroidFirebaseCore.wrapError(e));
                    reply.reply(hashMap);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class FirebaseAppHostApiCodec extends StandardMessageCodec {
        public static final FirebaseAppHostApiCodec INSTANCE = new FirebaseAppHostApiCodec();

        private FirebaseAppHostApiCodec() {
        }
    }

    /* loaded from: classes2.dex */
    public interface FirebaseAppHostApi {
        void delete(String str, Result<Void> result);

        void setAutomaticDataCollectionEnabled(String str, Boolean bool, Result<Void> result);

        void setAutomaticResourceManagementEnabled(String str, Boolean bool, Result<Void> result);

        /* renamed from: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore$FirebaseAppHostApi$-CC, reason: invalid class name */
        /* loaded from: classes2.dex */
        public final /* synthetic */ class CC {
            public static MessageCodec<Object> getCodec() {
                return FirebaseAppHostApiCodec.INSTANCE;
            }

            public static void setup(BinaryMessenger binaryMessenger, final FirebaseAppHostApi firebaseAppHostApi) {
                BasicMessageChannel basicMessageChannel = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.FirebaseAppHostApi.setAutomaticDataCollectionEnabled", getCodec());
                if (firebaseAppHostApi != null) {
                    basicMessageChannel.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore$FirebaseAppHostApi$$ExternalSyntheticLambda0
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidFirebaseCore.FirebaseAppHostApi.CC.lambda$setup$0(GeneratedAndroidFirebaseCore.FirebaseAppHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel2 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.FirebaseAppHostApi.setAutomaticResourceManagementEnabled", getCodec());
                if (firebaseAppHostApi != null) {
                    basicMessageChannel2.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore$FirebaseAppHostApi$$ExternalSyntheticLambda1
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidFirebaseCore.FirebaseAppHostApi.CC.lambda$setup$1(GeneratedAndroidFirebaseCore.FirebaseAppHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel2.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel3 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.FirebaseAppHostApi.delete", getCodec());
                if (firebaseAppHostApi != null) {
                    basicMessageChannel3.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore$FirebaseAppHostApi$$ExternalSyntheticLambda2
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidFirebaseCore.FirebaseAppHostApi.CC.lambda$setup$2(GeneratedAndroidFirebaseCore.FirebaseAppHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel3.setMessageHandler(null);
                }
            }

            public static /* synthetic */ void lambda$setup$0(FirebaseAppHostApi firebaseAppHostApi, Object obj, final BasicMessageChannel.Reply reply) {
                final HashMap hashMap = new HashMap();
                try {
                    ArrayList arrayList = (ArrayList) obj;
                    String str = (String) arrayList.get(0);
                    if (str == null) {
                        throw new NullPointerException("appNameArg unexpectedly null.");
                    }
                    Boolean bool = (Boolean) arrayList.get(1);
                    if (bool == null) {
                        throw new NullPointerException("enabledArg unexpectedly null.");
                    }
                    firebaseAppHostApi.setAutomaticDataCollectionEnabled(str, bool, new Result<Void>() { // from class: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.FirebaseAppHostApi.1
                        @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.Result
                        public void success(Void r3) {
                            hashMap.put(Constant.PARAM_RESULT, null);
                            reply.reply(hashMap);
                        }

                        @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.Result
                        public void error(Throwable th) {
                            hashMap.put("error", GeneratedAndroidFirebaseCore.wrapError(th));
                            reply.reply(hashMap);
                        }
                    });
                } catch (Error | RuntimeException e) {
                    hashMap.put("error", GeneratedAndroidFirebaseCore.wrapError(e));
                    reply.reply(hashMap);
                }
            }

            public static /* synthetic */ void lambda$setup$1(FirebaseAppHostApi firebaseAppHostApi, Object obj, final BasicMessageChannel.Reply reply) {
                final HashMap hashMap = new HashMap();
                try {
                    ArrayList arrayList = (ArrayList) obj;
                    String str = (String) arrayList.get(0);
                    if (str == null) {
                        throw new NullPointerException("appNameArg unexpectedly null.");
                    }
                    Boolean bool = (Boolean) arrayList.get(1);
                    if (bool == null) {
                        throw new NullPointerException("enabledArg unexpectedly null.");
                    }
                    firebaseAppHostApi.setAutomaticResourceManagementEnabled(str, bool, new Result<Void>() { // from class: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.FirebaseAppHostApi.2
                        @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.Result
                        public void success(Void r3) {
                            hashMap.put(Constant.PARAM_RESULT, null);
                            reply.reply(hashMap);
                        }

                        @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.Result
                        public void error(Throwable th) {
                            hashMap.put("error", GeneratedAndroidFirebaseCore.wrapError(th));
                            reply.reply(hashMap);
                        }
                    });
                } catch (Error | RuntimeException e) {
                    hashMap.put("error", GeneratedAndroidFirebaseCore.wrapError(e));
                    reply.reply(hashMap);
                }
            }

            public static /* synthetic */ void lambda$setup$2(FirebaseAppHostApi firebaseAppHostApi, Object obj, final BasicMessageChannel.Reply reply) {
                final HashMap hashMap = new HashMap();
                try {
                    String str = (String) ((ArrayList) obj).get(0);
                    if (str == null) {
                        throw new NullPointerException("appNameArg unexpectedly null.");
                    }
                    firebaseAppHostApi.delete(str, new Result<Void>() { // from class: io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.FirebaseAppHostApi.3
                        @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.Result
                        public void success(Void r3) {
                            hashMap.put(Constant.PARAM_RESULT, null);
                            reply.reply(hashMap);
                        }

                        @Override // io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.Result
                        public void error(Throwable th) {
                            hashMap.put("error", GeneratedAndroidFirebaseCore.wrapError(th));
                            reply.reply(hashMap);
                        }
                    });
                } catch (Error | RuntimeException e) {
                    hashMap.put("error", GeneratedAndroidFirebaseCore.wrapError(e));
                    reply.reply(hashMap);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<String, Object> wrapError(Throwable th) {
        HashMap hashMap = new HashMap();
        hashMap.put("message", th.toString());
        hashMap.put("code", th.getClass().getSimpleName());
        hashMap.put(Constants.ERROR_DETAILS, "Cause: " + th.getCause() + ", Stacktrace: " + Log.getStackTraceString(th));
        return hashMap;
    }
}
