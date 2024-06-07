package io.flutter.plugins.firebase.auth;

import android.util.Log;
import com.tekartik.sqflite.Constant;
import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MessageCodec;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class GeneratedAndroidFirebaseAuth {

    /* loaded from: classes2.dex */
    public interface Result<T> {
        void error(Throwable th);

        void success(T t);
    }

    /* loaded from: classes2.dex */
    public static class PigeonMultiFactorSession {
        private String id;

        public String getId() {
            return this.id;
        }

        public void setId(String str) {
            if (str == null) {
                throw new IllegalStateException("Nonnull field \"id\" is null.");
            }
            this.id = str;
        }

        private PigeonMultiFactorSession() {
        }

        /* loaded from: classes2.dex */
        public static final class Builder {
            private String id;

            public Builder setId(String str) {
                this.id = str;
                return this;
            }

            public PigeonMultiFactorSession build() {
                PigeonMultiFactorSession pigeonMultiFactorSession = new PigeonMultiFactorSession();
                pigeonMultiFactorSession.setId(this.id);
                return pigeonMultiFactorSession;
            }
        }

        Map<String, Object> toMap() {
            HashMap hashMap = new HashMap();
            hashMap.put("id", this.id);
            return hashMap;
        }

        static PigeonMultiFactorSession fromMap(Map<String, Object> map) {
            PigeonMultiFactorSession pigeonMultiFactorSession = new PigeonMultiFactorSession();
            pigeonMultiFactorSession.setId((String) map.get("id"));
            return pigeonMultiFactorSession;
        }
    }

    /* loaded from: classes2.dex */
    public static class PigeonPhoneMultiFactorAssertion {
        private String verificationCode;
        private String verificationId;

        public String getVerificationId() {
            return this.verificationId;
        }

        public void setVerificationId(String str) {
            if (str == null) {
                throw new IllegalStateException("Nonnull field \"verificationId\" is null.");
            }
            this.verificationId = str;
        }

        public String getVerificationCode() {
            return this.verificationCode;
        }

        public void setVerificationCode(String str) {
            if (str == null) {
                throw new IllegalStateException("Nonnull field \"verificationCode\" is null.");
            }
            this.verificationCode = str;
        }

        private PigeonPhoneMultiFactorAssertion() {
        }

        /* loaded from: classes2.dex */
        public static final class Builder {
            private String verificationCode;
            private String verificationId;

            public Builder setVerificationId(String str) {
                this.verificationId = str;
                return this;
            }

            public Builder setVerificationCode(String str) {
                this.verificationCode = str;
                return this;
            }

            public PigeonPhoneMultiFactorAssertion build() {
                PigeonPhoneMultiFactorAssertion pigeonPhoneMultiFactorAssertion = new PigeonPhoneMultiFactorAssertion();
                pigeonPhoneMultiFactorAssertion.setVerificationId(this.verificationId);
                pigeonPhoneMultiFactorAssertion.setVerificationCode(this.verificationCode);
                return pigeonPhoneMultiFactorAssertion;
            }
        }

        Map<String, Object> toMap() {
            HashMap hashMap = new HashMap();
            hashMap.put(Constants.VERIFICATION_ID, this.verificationId);
            hashMap.put("verificationCode", this.verificationCode);
            return hashMap;
        }

        static PigeonPhoneMultiFactorAssertion fromMap(Map<String, Object> map) {
            PigeonPhoneMultiFactorAssertion pigeonPhoneMultiFactorAssertion = new PigeonPhoneMultiFactorAssertion();
            pigeonPhoneMultiFactorAssertion.setVerificationId((String) map.get(Constants.VERIFICATION_ID));
            pigeonPhoneMultiFactorAssertion.setVerificationCode((String) map.get("verificationCode"));
            return pigeonPhoneMultiFactorAssertion;
        }
    }

    /* loaded from: classes2.dex */
    public static class PigeonMultiFactorInfo {
        private String displayName;
        private Double enrollmentTimestamp;
        private String factorId;
        private String phoneNumber;
        private String uid;

        public String getDisplayName() {
            return this.displayName;
        }

        public void setDisplayName(String str) {
            this.displayName = str;
        }

        public Double getEnrollmentTimestamp() {
            return this.enrollmentTimestamp;
        }

        public void setEnrollmentTimestamp(Double d) {
            if (d == null) {
                throw new IllegalStateException("Nonnull field \"enrollmentTimestamp\" is null.");
            }
            this.enrollmentTimestamp = d;
        }

        public String getFactorId() {
            return this.factorId;
        }

        public void setFactorId(String str) {
            this.factorId = str;
        }

        public String getUid() {
            return this.uid;
        }

        public void setUid(String str) {
            if (str == null) {
                throw new IllegalStateException("Nonnull field \"uid\" is null.");
            }
            this.uid = str;
        }

        public String getPhoneNumber() {
            return this.phoneNumber;
        }

        public void setPhoneNumber(String str) {
            this.phoneNumber = str;
        }

        private PigeonMultiFactorInfo() {
        }

        /* loaded from: classes2.dex */
        public static final class Builder {
            private String displayName;
            private Double enrollmentTimestamp;
            private String factorId;
            private String phoneNumber;
            private String uid;

            public Builder setDisplayName(String str) {
                this.displayName = str;
                return this;
            }

            public Builder setEnrollmentTimestamp(Double d) {
                this.enrollmentTimestamp = d;
                return this;
            }

            public Builder setFactorId(String str) {
                this.factorId = str;
                return this;
            }

            public Builder setUid(String str) {
                this.uid = str;
                return this;
            }

            public Builder setPhoneNumber(String str) {
                this.phoneNumber = str;
                return this;
            }

            public PigeonMultiFactorInfo build() {
                PigeonMultiFactorInfo pigeonMultiFactorInfo = new PigeonMultiFactorInfo();
                pigeonMultiFactorInfo.setDisplayName(this.displayName);
                pigeonMultiFactorInfo.setEnrollmentTimestamp(this.enrollmentTimestamp);
                pigeonMultiFactorInfo.setFactorId(this.factorId);
                pigeonMultiFactorInfo.setUid(this.uid);
                pigeonMultiFactorInfo.setPhoneNumber(this.phoneNumber);
                return pigeonMultiFactorInfo;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Map<String, Object> toMap() {
            HashMap hashMap = new HashMap();
            hashMap.put(Constants.DISPLAY_NAME, this.displayName);
            hashMap.put("enrollmentTimestamp", this.enrollmentTimestamp);
            hashMap.put("factorId", this.factorId);
            hashMap.put(Constants.UID, this.uid);
            hashMap.put(Constants.PHONE_NUMBER, this.phoneNumber);
            return hashMap;
        }

        static PigeonMultiFactorInfo fromMap(Map<String, Object> map) {
            PigeonMultiFactorInfo pigeonMultiFactorInfo = new PigeonMultiFactorInfo();
            pigeonMultiFactorInfo.setDisplayName((String) map.get(Constants.DISPLAY_NAME));
            pigeonMultiFactorInfo.setEnrollmentTimestamp((Double) map.get("enrollmentTimestamp"));
            pigeonMultiFactorInfo.setFactorId((String) map.get("factorId"));
            pigeonMultiFactorInfo.setUid((String) map.get(Constants.UID));
            pigeonMultiFactorInfo.setPhoneNumber((String) map.get(Constants.PHONE_NUMBER));
            return pigeonMultiFactorInfo;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class MultiFactorUserHostApiCodec extends StandardMessageCodec {
        public static final MultiFactorUserHostApiCodec INSTANCE = new MultiFactorUserHostApiCodec();

        private MultiFactorUserHostApiCodec() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.flutter.plugin.common.StandardMessageCodec
        public Object readValueOfType(byte b, ByteBuffer byteBuffer) {
            switch (b) {
                case Byte.MIN_VALUE:
                    return PigeonMultiFactorInfo.fromMap((Map) readValue(byteBuffer));
                case -127:
                    return PigeonMultiFactorSession.fromMap((Map) readValue(byteBuffer));
                case -126:
                    return PigeonPhoneMultiFactorAssertion.fromMap((Map) readValue(byteBuffer));
                default:
                    return super.readValueOfType(b, byteBuffer);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.flutter.plugin.common.StandardMessageCodec
        public void writeValue(ByteArrayOutputStream byteArrayOutputStream, Object obj) {
            if (obj instanceof PigeonMultiFactorInfo) {
                byteArrayOutputStream.write(128);
                writeValue(byteArrayOutputStream, ((PigeonMultiFactorInfo) obj).toMap());
            } else if (obj instanceof PigeonMultiFactorSession) {
                byteArrayOutputStream.write(129);
                writeValue(byteArrayOutputStream, ((PigeonMultiFactorSession) obj).toMap());
            } else if (obj instanceof PigeonPhoneMultiFactorAssertion) {
                byteArrayOutputStream.write(130);
                writeValue(byteArrayOutputStream, ((PigeonPhoneMultiFactorAssertion) obj).toMap());
            } else {
                super.writeValue(byteArrayOutputStream, obj);
            }
        }
    }

    /* loaded from: classes2.dex */
    public interface MultiFactorUserHostApi {
        void enrollPhone(String str, PigeonPhoneMultiFactorAssertion pigeonPhoneMultiFactorAssertion, String str2, Result<Void> result);

        void getEnrolledFactors(String str, Result<List<PigeonMultiFactorInfo>> result);

        void getSession(String str, Result<PigeonMultiFactorSession> result);

        void unenroll(String str, String str2, Result<Void> result);

        /* renamed from: io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth$MultiFactorUserHostApi$-CC, reason: invalid class name */
        /* loaded from: classes2.dex */
        public final /* synthetic */ class CC {
            public static MessageCodec<Object> getCodec() {
                return MultiFactorUserHostApiCodec.INSTANCE;
            }

            public static void setup(BinaryMessenger binaryMessenger, final MultiFactorUserHostApi multiFactorUserHostApi) {
                BasicMessageChannel basicMessageChannel = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.MultiFactorUserHostApi.enrollPhone", getCodec());
                if (multiFactorUserHostApi != null) {
                    basicMessageChannel.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth$MultiFactorUserHostApi$$ExternalSyntheticLambda0
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidFirebaseAuth.MultiFactorUserHostApi.CC.lambda$setup$0(GeneratedAndroidFirebaseAuth.MultiFactorUserHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel2 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.MultiFactorUserHostApi.getSession", getCodec());
                if (multiFactorUserHostApi != null) {
                    basicMessageChannel2.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth$MultiFactorUserHostApi$$ExternalSyntheticLambda1
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidFirebaseAuth.MultiFactorUserHostApi.CC.lambda$setup$1(GeneratedAndroidFirebaseAuth.MultiFactorUserHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel2.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel3 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.MultiFactorUserHostApi.unenroll", getCodec());
                if (multiFactorUserHostApi != null) {
                    basicMessageChannel3.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth$MultiFactorUserHostApi$$ExternalSyntheticLambda2
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidFirebaseAuth.MultiFactorUserHostApi.CC.lambda$setup$2(GeneratedAndroidFirebaseAuth.MultiFactorUserHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel3.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel4 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.MultiFactorUserHostApi.getEnrolledFactors", getCodec());
                if (multiFactorUserHostApi != null) {
                    basicMessageChannel4.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth$MultiFactorUserHostApi$$ExternalSyntheticLambda3
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidFirebaseAuth.MultiFactorUserHostApi.CC.lambda$setup$3(GeneratedAndroidFirebaseAuth.MultiFactorUserHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel4.setMessageHandler(null);
                }
            }

            public static /* synthetic */ void lambda$setup$0(MultiFactorUserHostApi multiFactorUserHostApi, Object obj, final BasicMessageChannel.Reply reply) {
                final HashMap hashMap = new HashMap();
                try {
                    ArrayList arrayList = (ArrayList) obj;
                    String str = (String) arrayList.get(0);
                    if (str == null) {
                        throw new NullPointerException("appNameArg unexpectedly null.");
                    }
                    PigeonPhoneMultiFactorAssertion pigeonPhoneMultiFactorAssertion = (PigeonPhoneMultiFactorAssertion) arrayList.get(1);
                    if (pigeonPhoneMultiFactorAssertion == null) {
                        throw new NullPointerException("assertionArg unexpectedly null.");
                    }
                    multiFactorUserHostApi.enrollPhone(str, pigeonPhoneMultiFactorAssertion, (String) arrayList.get(2), new Result<Void>() { // from class: io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth.MultiFactorUserHostApi.1
                        @Override // io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth.Result
                        public void success(Void r3) {
                            hashMap.put(Constant.PARAM_RESULT, null);
                            reply.reply(hashMap);
                        }

                        @Override // io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth.Result
                        public void error(Throwable th) {
                            hashMap.put("error", GeneratedAndroidFirebaseAuth.wrapError(th));
                            reply.reply(hashMap);
                        }
                    });
                } catch (Error | RuntimeException e) {
                    hashMap.put("error", GeneratedAndroidFirebaseAuth.wrapError(e));
                    reply.reply(hashMap);
                }
            }

            public static /* synthetic */ void lambda$setup$1(MultiFactorUserHostApi multiFactorUserHostApi, Object obj, final BasicMessageChannel.Reply reply) {
                final HashMap hashMap = new HashMap();
                try {
                    String str = (String) ((ArrayList) obj).get(0);
                    if (str == null) {
                        throw new NullPointerException("appNameArg unexpectedly null.");
                    }
                    multiFactorUserHostApi.getSession(str, new Result<PigeonMultiFactorSession>() { // from class: io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth.MultiFactorUserHostApi.2
                        @Override // io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth.Result
                        public void success(PigeonMultiFactorSession pigeonMultiFactorSession) {
                            hashMap.put(Constant.PARAM_RESULT, pigeonMultiFactorSession);
                            reply.reply(hashMap);
                        }

                        @Override // io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth.Result
                        public void error(Throwable th) {
                            hashMap.put("error", GeneratedAndroidFirebaseAuth.wrapError(th));
                            reply.reply(hashMap);
                        }
                    });
                } catch (Error | RuntimeException e) {
                    hashMap.put("error", GeneratedAndroidFirebaseAuth.wrapError(e));
                    reply.reply(hashMap);
                }
            }

            public static /* synthetic */ void lambda$setup$2(MultiFactorUserHostApi multiFactorUserHostApi, Object obj, final BasicMessageChannel.Reply reply) {
                final HashMap hashMap = new HashMap();
                try {
                    ArrayList arrayList = (ArrayList) obj;
                    String str = (String) arrayList.get(0);
                    if (str == null) {
                        throw new NullPointerException("appNameArg unexpectedly null.");
                    }
                    multiFactorUserHostApi.unenroll(str, (String) arrayList.get(1), new Result<Void>() { // from class: io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth.MultiFactorUserHostApi.3
                        @Override // io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth.Result
                        public void success(Void r3) {
                            hashMap.put(Constant.PARAM_RESULT, null);
                            reply.reply(hashMap);
                        }

                        @Override // io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth.Result
                        public void error(Throwable th) {
                            hashMap.put("error", GeneratedAndroidFirebaseAuth.wrapError(th));
                            reply.reply(hashMap);
                        }
                    });
                } catch (Error | RuntimeException e) {
                    hashMap.put("error", GeneratedAndroidFirebaseAuth.wrapError(e));
                    reply.reply(hashMap);
                }
            }

            public static /* synthetic */ void lambda$setup$3(MultiFactorUserHostApi multiFactorUserHostApi, Object obj, final BasicMessageChannel.Reply reply) {
                final HashMap hashMap = new HashMap();
                try {
                    String str = (String) ((ArrayList) obj).get(0);
                    if (str == null) {
                        throw new NullPointerException("appNameArg unexpectedly null.");
                    }
                    multiFactorUserHostApi.getEnrolledFactors(str, new Result<List<PigeonMultiFactorInfo>>() { // from class: io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth.MultiFactorUserHostApi.4
                        @Override // io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth.Result
                        public void success(List<PigeonMultiFactorInfo> list) {
                            hashMap.put(Constant.PARAM_RESULT, list);
                            reply.reply(hashMap);
                        }

                        @Override // io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth.Result
                        public void error(Throwable th) {
                            hashMap.put("error", GeneratedAndroidFirebaseAuth.wrapError(th));
                            reply.reply(hashMap);
                        }
                    });
                } catch (Error | RuntimeException e) {
                    hashMap.put("error", GeneratedAndroidFirebaseAuth.wrapError(e));
                    reply.reply(hashMap);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class MultiFactoResolverHostApiCodec extends StandardMessageCodec {
        public static final MultiFactoResolverHostApiCodec INSTANCE = new MultiFactoResolverHostApiCodec();

        private MultiFactoResolverHostApiCodec() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.flutter.plugin.common.StandardMessageCodec
        public Object readValueOfType(byte b, ByteBuffer byteBuffer) {
            switch (b) {
                case Byte.MIN_VALUE:
                    return PigeonMultiFactorInfo.fromMap((Map) readValue(byteBuffer));
                case -127:
                    return PigeonMultiFactorSession.fromMap((Map) readValue(byteBuffer));
                case -126:
                    return PigeonPhoneMultiFactorAssertion.fromMap((Map) readValue(byteBuffer));
                default:
                    return super.readValueOfType(b, byteBuffer);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.flutter.plugin.common.StandardMessageCodec
        public void writeValue(ByteArrayOutputStream byteArrayOutputStream, Object obj) {
            if (obj instanceof PigeonMultiFactorInfo) {
                byteArrayOutputStream.write(128);
                writeValue(byteArrayOutputStream, ((PigeonMultiFactorInfo) obj).toMap());
            } else if (obj instanceof PigeonMultiFactorSession) {
                byteArrayOutputStream.write(129);
                writeValue(byteArrayOutputStream, ((PigeonMultiFactorSession) obj).toMap());
            } else if (obj instanceof PigeonPhoneMultiFactorAssertion) {
                byteArrayOutputStream.write(130);
                writeValue(byteArrayOutputStream, ((PigeonPhoneMultiFactorAssertion) obj).toMap());
            } else {
                super.writeValue(byteArrayOutputStream, obj);
            }
        }
    }

    /* loaded from: classes2.dex */
    public interface MultiFactoResolverHostApi {
        void resolveSignIn(String str, PigeonPhoneMultiFactorAssertion pigeonPhoneMultiFactorAssertion, Result<Map<String, Object>> result);

        /* renamed from: io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth$MultiFactoResolverHostApi$-CC, reason: invalid class name */
        /* loaded from: classes2.dex */
        public final /* synthetic */ class CC {
            public static MessageCodec<Object> getCodec() {
                return MultiFactoResolverHostApiCodec.INSTANCE;
            }

            public static void setup(BinaryMessenger binaryMessenger, final MultiFactoResolverHostApi multiFactoResolverHostApi) {
                BasicMessageChannel basicMessageChannel = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.MultiFactoResolverHostApi.resolveSignIn", getCodec());
                if (multiFactoResolverHostApi != null) {
                    basicMessageChannel.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth$MultiFactoResolverHostApi$$ExternalSyntheticLambda0
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidFirebaseAuth.MultiFactoResolverHostApi.CC.lambda$setup$0(GeneratedAndroidFirebaseAuth.MultiFactoResolverHostApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel.setMessageHandler(null);
                }
            }

            public static /* synthetic */ void lambda$setup$0(MultiFactoResolverHostApi multiFactoResolverHostApi, Object obj, final BasicMessageChannel.Reply reply) {
                final HashMap hashMap = new HashMap();
                try {
                    ArrayList arrayList = (ArrayList) obj;
                    String str = (String) arrayList.get(0);
                    if (str == null) {
                        throw new NullPointerException("resolverIdArg unexpectedly null.");
                    }
                    PigeonPhoneMultiFactorAssertion pigeonPhoneMultiFactorAssertion = (PigeonPhoneMultiFactorAssertion) arrayList.get(1);
                    if (pigeonPhoneMultiFactorAssertion == null) {
                        throw new NullPointerException("assertionArg unexpectedly null.");
                    }
                    multiFactoResolverHostApi.resolveSignIn(str, pigeonPhoneMultiFactorAssertion, new Result<Map<String, Object>>() { // from class: io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth.MultiFactoResolverHostApi.1
                        @Override // io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth.Result
                        public void success(Map<String, Object> map) {
                            hashMap.put(Constant.PARAM_RESULT, map);
                            reply.reply(hashMap);
                        }

                        @Override // io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth.Result
                        public void error(Throwable th) {
                            hashMap.put("error", GeneratedAndroidFirebaseAuth.wrapError(th));
                            reply.reply(hashMap);
                        }
                    });
                } catch (Error | RuntimeException e) {
                    hashMap.put("error", GeneratedAndroidFirebaseAuth.wrapError(e));
                    reply.reply(hashMap);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class GenerateInterfacesCodec extends StandardMessageCodec {
        public static final GenerateInterfacesCodec INSTANCE = new GenerateInterfacesCodec();

        private GenerateInterfacesCodec() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.flutter.plugin.common.StandardMessageCodec
        public Object readValueOfType(byte b, ByteBuffer byteBuffer) {
            if (b == Byte.MIN_VALUE) {
                return PigeonMultiFactorInfo.fromMap((Map) readValue(byteBuffer));
            }
            return super.readValueOfType(b, byteBuffer);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.flutter.plugin.common.StandardMessageCodec
        public void writeValue(ByteArrayOutputStream byteArrayOutputStream, Object obj) {
            if (obj instanceof PigeonMultiFactorInfo) {
                byteArrayOutputStream.write(128);
                writeValue(byteArrayOutputStream, ((PigeonMultiFactorInfo) obj).toMap());
            } else {
                super.writeValue(byteArrayOutputStream, obj);
            }
        }
    }

    /* loaded from: classes2.dex */
    public interface GenerateInterfaces {
        void generateInterfaces(PigeonMultiFactorInfo pigeonMultiFactorInfo);

        /* renamed from: io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth$GenerateInterfaces$-CC, reason: invalid class name */
        /* loaded from: classes2.dex */
        public final /* synthetic */ class CC {
            public static MessageCodec<Object> getCodec() {
                return GenerateInterfacesCodec.INSTANCE;
            }

            public static void setup(BinaryMessenger binaryMessenger, final GenerateInterfaces generateInterfaces) {
                BasicMessageChannel basicMessageChannel = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.GenerateInterfaces.generateInterfaces", getCodec());
                if (generateInterfaces != null) {
                    basicMessageChannel.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.firebase.auth.GeneratedAndroidFirebaseAuth$GenerateInterfaces$$ExternalSyntheticLambda0
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            GeneratedAndroidFirebaseAuth.GenerateInterfaces.CC.lambda$setup$0(GeneratedAndroidFirebaseAuth.GenerateInterfaces.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel.setMessageHandler(null);
                }
            }

            public static /* synthetic */ void lambda$setup$0(GenerateInterfaces generateInterfaces, Object obj, BasicMessageChannel.Reply reply) {
                PigeonMultiFactorInfo pigeonMultiFactorInfo;
                HashMap hashMap = new HashMap();
                try {
                    pigeonMultiFactorInfo = (PigeonMultiFactorInfo) ((ArrayList) obj).get(0);
                } catch (Error | RuntimeException e) {
                    hashMap.put("error", GeneratedAndroidFirebaseAuth.wrapError(e));
                }
                if (pigeonMultiFactorInfo == null) {
                    throw new NullPointerException("infoArg unexpectedly null.");
                }
                generateInterfaces.generateInterfaces(pigeonMultiFactorInfo);
                hashMap.put(Constant.PARAM_RESULT, null);
                reply.reply(hashMap);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Map<String, Object> wrapError(Throwable th) {
        HashMap hashMap = new HashMap();
        hashMap.put("message", th.toString());
        hashMap.put("code", th.getClass().getSimpleName());
        hashMap.put(io.flutter.plugins.firebase.database.Constants.ERROR_DETAILS, "Cause: " + th.getCause() + ", Stacktrace: " + Log.getStackTraceString(th));
        return hashMap;
    }
}
