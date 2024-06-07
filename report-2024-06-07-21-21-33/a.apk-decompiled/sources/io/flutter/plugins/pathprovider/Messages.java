package io.flutter.plugins.pathprovider;

import android.util.Log;
import com.tekartik.sqflite.Constant;
import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MessageCodec;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugins.firebase.database.Constants;
import io.flutter.plugins.pathprovider.Messages;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class Messages {

    /* loaded from: classes2.dex */
    public enum StorageDirectory {
        root(0),
        music(1),
        podcasts(2),
        ringtones(3),
        alarms(4),
        notifications(5),
        pictures(6),
        movies(7),
        downloads(8),
        dcim(9),
        documents(10);

        private int index;

        StorageDirectory(int i) {
            this.index = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class PathProviderApiCodec extends StandardMessageCodec {
        public static final PathProviderApiCodec INSTANCE = new PathProviderApiCodec();

        private PathProviderApiCodec() {
        }
    }

    /* loaded from: classes2.dex */
    public interface PathProviderApi {
        String getApplicationDocumentsPath();

        String getApplicationSupportPath();

        List<String> getExternalCachePaths();

        String getExternalStoragePath();

        List<String> getExternalStoragePaths(StorageDirectory storageDirectory);

        String getTemporaryPath();

        /* renamed from: io.flutter.plugins.pathprovider.Messages$PathProviderApi$-CC, reason: invalid class name */
        /* loaded from: classes2.dex */
        public final /* synthetic */ class CC {
            public static MessageCodec<Object> getCodec() {
                return PathProviderApiCodec.INSTANCE;
            }

            public static void setup(BinaryMessenger binaryMessenger, final PathProviderApi pathProviderApi) {
                BasicMessageChannel basicMessageChannel = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.PathProviderApi.getTemporaryPath", getCodec(), binaryMessenger.makeBackgroundTaskQueue());
                if (pathProviderApi != null) {
                    basicMessageChannel.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.pathprovider.Messages$PathProviderApi$$ExternalSyntheticLambda0
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            Messages.PathProviderApi.CC.lambda$setup$0(Messages.PathProviderApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel2 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.PathProviderApi.getApplicationSupportPath", getCodec(), binaryMessenger.makeBackgroundTaskQueue());
                if (pathProviderApi != null) {
                    basicMessageChannel2.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.pathprovider.Messages$PathProviderApi$$ExternalSyntheticLambda1
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            Messages.PathProviderApi.CC.lambda$setup$1(Messages.PathProviderApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel2.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel3 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.PathProviderApi.getApplicationDocumentsPath", getCodec(), binaryMessenger.makeBackgroundTaskQueue());
                if (pathProviderApi != null) {
                    basicMessageChannel3.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.pathprovider.Messages$PathProviderApi$$ExternalSyntheticLambda2
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            Messages.PathProviderApi.CC.lambda$setup$2(Messages.PathProviderApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel3.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel4 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.PathProviderApi.getExternalStoragePath", getCodec(), binaryMessenger.makeBackgroundTaskQueue());
                if (pathProviderApi != null) {
                    basicMessageChannel4.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.pathprovider.Messages$PathProviderApi$$ExternalSyntheticLambda3
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            Messages.PathProviderApi.CC.lambda$setup$3(Messages.PathProviderApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel4.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel5 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.PathProviderApi.getExternalCachePaths", getCodec(), binaryMessenger.makeBackgroundTaskQueue());
                if (pathProviderApi != null) {
                    basicMessageChannel5.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.pathprovider.Messages$PathProviderApi$$ExternalSyntheticLambda4
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            Messages.PathProviderApi.CC.lambda$setup$4(Messages.PathProviderApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel5.setMessageHandler(null);
                }
                BasicMessageChannel basicMessageChannel6 = new BasicMessageChannel(binaryMessenger, "dev.flutter.pigeon.PathProviderApi.getExternalStoragePaths", getCodec(), binaryMessenger.makeBackgroundTaskQueue());
                if (pathProviderApi != null) {
                    basicMessageChannel6.setMessageHandler(new BasicMessageChannel.MessageHandler() { // from class: io.flutter.plugins.pathprovider.Messages$PathProviderApi$$ExternalSyntheticLambda5
                        @Override // io.flutter.plugin.common.BasicMessageChannel.MessageHandler
                        public final void onMessage(Object obj, BasicMessageChannel.Reply reply) {
                            Messages.PathProviderApi.CC.lambda$setup$5(Messages.PathProviderApi.this, obj, reply);
                        }
                    });
                } else {
                    basicMessageChannel6.setMessageHandler(null);
                }
            }

            public static /* synthetic */ void lambda$setup$0(PathProviderApi pathProviderApi, Object obj, BasicMessageChannel.Reply reply) {
                HashMap hashMap = new HashMap();
                try {
                    hashMap.put(Constant.PARAM_RESULT, pathProviderApi.getTemporaryPath());
                } catch (Error | RuntimeException e) {
                    hashMap.put("error", Messages.wrapError(e));
                }
                reply.reply(hashMap);
            }

            public static /* synthetic */ void lambda$setup$1(PathProviderApi pathProviderApi, Object obj, BasicMessageChannel.Reply reply) {
                HashMap hashMap = new HashMap();
                try {
                    hashMap.put(Constant.PARAM_RESULT, pathProviderApi.getApplicationSupportPath());
                } catch (Error | RuntimeException e) {
                    hashMap.put("error", Messages.wrapError(e));
                }
                reply.reply(hashMap);
            }

            public static /* synthetic */ void lambda$setup$2(PathProviderApi pathProviderApi, Object obj, BasicMessageChannel.Reply reply) {
                HashMap hashMap = new HashMap();
                try {
                    hashMap.put(Constant.PARAM_RESULT, pathProviderApi.getApplicationDocumentsPath());
                } catch (Error | RuntimeException e) {
                    hashMap.put("error", Messages.wrapError(e));
                }
                reply.reply(hashMap);
            }

            public static /* synthetic */ void lambda$setup$3(PathProviderApi pathProviderApi, Object obj, BasicMessageChannel.Reply reply) {
                HashMap hashMap = new HashMap();
                try {
                    hashMap.put(Constant.PARAM_RESULT, pathProviderApi.getExternalStoragePath());
                } catch (Error | RuntimeException e) {
                    hashMap.put("error", Messages.wrapError(e));
                }
                reply.reply(hashMap);
            }

            public static /* synthetic */ void lambda$setup$4(PathProviderApi pathProviderApi, Object obj, BasicMessageChannel.Reply reply) {
                HashMap hashMap = new HashMap();
                try {
                    hashMap.put(Constant.PARAM_RESULT, pathProviderApi.getExternalCachePaths());
                } catch (Error | RuntimeException e) {
                    hashMap.put("error", Messages.wrapError(e));
                }
                reply.reply(hashMap);
            }

            public static /* synthetic */ void lambda$setup$5(PathProviderApi pathProviderApi, Object obj, BasicMessageChannel.Reply reply) {
                StorageDirectory storageDirectory;
                HashMap hashMap = new HashMap();
                try {
                    ArrayList arrayList = (ArrayList) obj;
                    storageDirectory = arrayList.get(0) == null ? null : StorageDirectory.values()[((Integer) arrayList.get(0)).intValue()];
                } catch (Error | RuntimeException e) {
                    hashMap.put("error", Messages.wrapError(e));
                }
                if (storageDirectory == null) {
                    throw new NullPointerException("directoryArg unexpectedly null.");
                }
                hashMap.put(Constant.PARAM_RESULT, pathProviderApi.getExternalStoragePaths(storageDirectory));
                reply.reply(hashMap);
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
