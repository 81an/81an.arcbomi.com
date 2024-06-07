package io.flutter.plugins.pathprovider;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugins.pathprovider.Messages;
import io.flutter.util.PathUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class PathProviderPlugin implements FlutterPlugin, Messages.PathProviderApi {
    static final String TAG = "PathProviderPlugin";
    private Context context;

    private void setup(BinaryMessenger binaryMessenger, Context context) {
        binaryMessenger.makeBackgroundTaskQueue();
        try {
            Messages.PathProviderApi.CC.setup(binaryMessenger, this);
        } catch (Exception e) {
            Log.e(TAG, "Received exception while setting up PathProviderPlugin", e);
        }
        this.context = context;
    }

    public static void registerWith(PluginRegistry.Registrar registrar) {
        new PathProviderPlugin().setup(registrar.messenger(), registrar.context());
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onAttachedToEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        setup(flutterPluginBinding.getBinaryMessenger(), flutterPluginBinding.getApplicationContext());
    }

    @Override // io.flutter.embedding.engine.plugins.FlutterPlugin
    public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding flutterPluginBinding) {
        Messages.PathProviderApi.CC.setup(flutterPluginBinding.getBinaryMessenger(), null);
    }

    @Override // io.flutter.plugins.pathprovider.Messages.PathProviderApi
    public String getTemporaryPath() {
        return getPathProviderTemporaryDirectory();
    }

    @Override // io.flutter.plugins.pathprovider.Messages.PathProviderApi
    public String getApplicationSupportPath() {
        return getApplicationSupportDirectory();
    }

    @Override // io.flutter.plugins.pathprovider.Messages.PathProviderApi
    public String getApplicationDocumentsPath() {
        return getPathProviderApplicationDocumentsDirectory();
    }

    @Override // io.flutter.plugins.pathprovider.Messages.PathProviderApi
    public String getExternalStoragePath() {
        return getPathProviderStorageDirectory();
    }

    @Override // io.flutter.plugins.pathprovider.Messages.PathProviderApi
    public List<String> getExternalCachePaths() {
        return getPathProviderExternalCacheDirectories();
    }

    @Override // io.flutter.plugins.pathprovider.Messages.PathProviderApi
    public List<String> getExternalStoragePaths(Messages.StorageDirectory storageDirectory) {
        return getPathProviderExternalStorageDirectories(storageDirectory);
    }

    private String getPathProviderTemporaryDirectory() {
        return this.context.getCacheDir().getPath();
    }

    private String getApplicationSupportDirectory() {
        return PathUtils.getFilesDir(this.context);
    }

    private String getPathProviderApplicationDocumentsDirectory() {
        return PathUtils.getDataDirectory(this.context);
    }

    private String getPathProviderStorageDirectory() {
        File externalFilesDir = this.context.getExternalFilesDir(null);
        if (externalFilesDir == null) {
            return null;
        }
        return externalFilesDir.getAbsolutePath();
    }

    private List<String> getPathProviderExternalCacheDirectories() {
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 19) {
            for (File file : this.context.getExternalCacheDirs()) {
                if (file != null) {
                    arrayList.add(file.getAbsolutePath());
                }
            }
        } else {
            File externalCacheDir = this.context.getExternalCacheDir();
            if (externalCacheDir != null) {
                arrayList.add(externalCacheDir.getAbsolutePath());
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: io.flutter.plugins.pathprovider.PathProviderPlugin$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$flutter$plugins$pathprovider$Messages$StorageDirectory;

        static {
            int[] iArr = new int[Messages.StorageDirectory.values().length];
            $SwitchMap$io$flutter$plugins$pathprovider$Messages$StorageDirectory = iArr;
            try {
                iArr[Messages.StorageDirectory.root.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$flutter$plugins$pathprovider$Messages$StorageDirectory[Messages.StorageDirectory.music.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$flutter$plugins$pathprovider$Messages$StorageDirectory[Messages.StorageDirectory.podcasts.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$flutter$plugins$pathprovider$Messages$StorageDirectory[Messages.StorageDirectory.ringtones.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$flutter$plugins$pathprovider$Messages$StorageDirectory[Messages.StorageDirectory.alarms.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$flutter$plugins$pathprovider$Messages$StorageDirectory[Messages.StorageDirectory.notifications.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$io$flutter$plugins$pathprovider$Messages$StorageDirectory[Messages.StorageDirectory.pictures.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$io$flutter$plugins$pathprovider$Messages$StorageDirectory[Messages.StorageDirectory.movies.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$io$flutter$plugins$pathprovider$Messages$StorageDirectory[Messages.StorageDirectory.downloads.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$io$flutter$plugins$pathprovider$Messages$StorageDirectory[Messages.StorageDirectory.dcim.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$io$flutter$plugins$pathprovider$Messages$StorageDirectory[Messages.StorageDirectory.documents.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    private String getStorageDirectoryString(Messages.StorageDirectory storageDirectory) {
        switch (AnonymousClass1.$SwitchMap$io$flutter$plugins$pathprovider$Messages$StorageDirectory[storageDirectory.ordinal()]) {
            case 1:
                return null;
            case 2:
                return "music";
            case 3:
                return "podcasts";
            case 4:
                return "ringtones";
            case 5:
                return "alarms";
            case 6:
                return "notifications";
            case 7:
                return "pictures";
            case 8:
                return "movies";
            case 9:
                return "downloads";
            case 10:
                return "dcim";
            case 11:
                return "documents";
            default:
                throw new RuntimeException("Unrecognized directory: " + storageDirectory);
        }
    }

    private List<String> getPathProviderExternalStorageDirectories(Messages.StorageDirectory storageDirectory) {
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 19) {
            for (File file : this.context.getExternalFilesDirs(getStorageDirectoryString(storageDirectory))) {
                if (file != null) {
                    arrayList.add(file.getAbsolutePath());
                }
            }
        } else {
            File externalFilesDir = this.context.getExternalFilesDir(getStorageDirectoryString(storageDirectory));
            if (externalFilesDir != null) {
                arrayList.add(externalFilesDir.getAbsolutePath());
            }
        }
        return arrayList;
    }
}
