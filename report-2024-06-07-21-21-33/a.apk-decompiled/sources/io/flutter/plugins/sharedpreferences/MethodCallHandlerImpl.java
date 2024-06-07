package io.flutter.plugins.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
class MethodCallHandlerImpl implements MethodChannel.MethodCallHandler {
    private static final String BIG_INTEGER_PREFIX = "VGhpcyBpcyB0aGUgcHJlZml4IGZvciBCaWdJbnRlZ2Vy";
    private static final String DOUBLE_PREFIX = "VGhpcyBpcyB0aGUgcHJlZml4IGZvciBEb3VibGUu";
    private static final String LIST_IDENTIFIER = "VGhpcyBpcyB0aGUgcHJlZml4IGZvciBhIGxpc3Qu";
    private static final String SHARED_PREFERENCES_NAME = "FlutterSharedPreferences";
    private final ExecutorService executor = new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final SharedPreferences preferences;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MethodCallHandlerImpl(Context context) {
        this.preferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0);
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        String str = (String) methodCall.argument("key");
        try {
            String str2 = methodCall.method;
            char c = 65535;
            switch (str2.hashCode()) {
                case -1354815177:
                    if (str2.equals("commit")) {
                        c = 5;
                        break;
                    }
                    break;
                case -1249367445:
                    if (str2.equals("getAll")) {
                        c = 6;
                        break;
                    }
                    break;
                case -1096934831:
                    if (str2.equals("setStringList")) {
                        c = 4;
                        break;
                    }
                    break;
                case -934610812:
                    if (str2.equals("remove")) {
                        c = 7;
                        break;
                    }
                    break;
                case -905809875:
                    if (str2.equals("setInt")) {
                        c = 2;
                        break;
                    }
                    break;
                case 94746189:
                    if (str2.equals("clear")) {
                        c = '\b';
                        break;
                    }
                    break;
                case 155439827:
                    if (str2.equals("setDouble")) {
                        c = 1;
                        break;
                    }
                    break;
                case 589412115:
                    if (str2.equals("setString")) {
                        c = 3;
                        break;
                    }
                    break;
                case 1984457324:
                    if (str2.equals("setBool")) {
                        c = 0;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    commitAsync(this.preferences.edit().putBoolean(str, ((Boolean) methodCall.argument("value")).booleanValue()), result);
                    return;
                case 1:
                    String d = Double.toString(((Number) methodCall.argument("value")).doubleValue());
                    commitAsync(this.preferences.edit().putString(str, DOUBLE_PREFIX + d), result);
                    return;
                case 2:
                    Number number = (Number) methodCall.argument("value");
                    if (number instanceof BigInteger) {
                        commitAsync(this.preferences.edit().putString(str, BIG_INTEGER_PREFIX + ((BigInteger) number).toString(36)), result);
                        return;
                    }
                    commitAsync(this.preferences.edit().putLong(str, number.longValue()), result);
                    return;
                case 3:
                    String str3 = (String) methodCall.argument("value");
                    if (!str3.startsWith(LIST_IDENTIFIER) && !str3.startsWith(BIG_INTEGER_PREFIX) && !str3.startsWith(DOUBLE_PREFIX)) {
                        commitAsync(this.preferences.edit().putString(str, str3), result);
                        return;
                    }
                    result.error("StorageError", "This string cannot be stored as it clashes with special identifier prefixes.", null);
                    return;
                case 4:
                    List<String> list = (List) methodCall.argument("value");
                    commitAsync(this.preferences.edit().putString(str, LIST_IDENTIFIER + encodeList(list)), result);
                    return;
                case 5:
                    result.success(true);
                    return;
                case 6:
                    result.success(getAllPrefs());
                    return;
                case 7:
                    commitAsync(this.preferences.edit().remove(str), result);
                    return;
                case '\b':
                    Set<String> keySet = getAllPrefs().keySet();
                    SharedPreferences.Editor edit = this.preferences.edit();
                    Iterator<String> it = keySet.iterator();
                    while (it.hasNext()) {
                        edit.remove(it.next());
                    }
                    commitAsync(edit, result);
                    return;
                default:
                    result.notImplemented();
                    return;
            }
        } catch (IOException e) {
            result.error("IOException encountered", methodCall.method, e);
        }
    }

    public void teardown() {
        this.handler.removeCallbacksAndMessages(null);
        this.executor.shutdown();
    }

    private void commitAsync(final SharedPreferences.Editor editor, final MethodChannel.Result result) {
        this.executor.execute(new Runnable() { // from class: io.flutter.plugins.sharedpreferences.MethodCallHandlerImpl.1
            @Override // java.lang.Runnable
            public void run() {
                final boolean commit = editor.commit();
                MethodCallHandlerImpl.this.handler.post(new Runnable() { // from class: io.flutter.plugins.sharedpreferences.MethodCallHandlerImpl.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        result.success(Boolean.valueOf(commit));
                    }
                });
            }
        });
    }

    private List<String> decodeList(String str) throws IOException {
        ObjectInputStream objectInputStream;
        ObjectInputStream objectInputStream2 = null;
        try {
            try {
                objectInputStream = new ObjectInputStream(new ByteArrayInputStream(Base64.decode(str, 0)));
            } catch (Throwable th) {
                th = th;
            }
        } catch (ClassNotFoundException e) {
            e = e;
        }
        try {
            List<String> list = (List) objectInputStream.readObject();
            objectInputStream.close();
            return list;
        } catch (ClassNotFoundException e2) {
            e = e2;
            throw new IOException(e);
        } catch (Throwable th2) {
            th = th2;
            objectInputStream2 = objectInputStream;
            if (objectInputStream2 != null) {
                objectInputStream2.close();
            }
            throw th;
        }
    }

    private String encodeList(List<String> list) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        ObjectOutputStream objectOutputStream;
        ObjectOutputStream objectOutputStream2 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        } catch (Throwable th) {
            th = th;
        }
        try {
            objectOutputStream.writeObject(list);
            objectOutputStream.flush();
            String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
            objectOutputStream.close();
            return encodeToString;
        } catch (Throwable th2) {
            th = th2;
            objectOutputStream2 = objectOutputStream;
            if (objectOutputStream2 != null) {
                objectOutputStream2.close();
            }
            throw th;
        }
    }

    private Map<String, Object> getAllPrefs() throws IOException {
        Object obj;
        Map<String, ?> all = this.preferences.getAll();
        HashMap hashMap = new HashMap();
        for (String str : all.keySet()) {
            if (str.startsWith("flutter.")) {
                Object obj2 = all.get(str);
                if (obj2 instanceof String) {
                    String str2 = (String) obj2;
                    if (str2.startsWith(LIST_IDENTIFIER)) {
                        obj2 = decodeList(str2.substring(40));
                    } else if (str2.startsWith(BIG_INTEGER_PREFIX)) {
                        obj = new BigInteger(str2.substring(44), 36);
                        obj2 = obj;
                    } else if (str2.startsWith(DOUBLE_PREFIX)) {
                        obj2 = Double.valueOf(str2.substring(40));
                    }
                    hashMap.put(str, obj2);
                } else {
                    if (obj2 instanceof Set) {
                        ArrayList arrayList = new ArrayList((Set) obj2);
                        obj = arrayList;
                        if (!this.preferences.edit().remove(str).putString(str, LIST_IDENTIFIER + encodeList(arrayList)).commit()) {
                            throw new IOException("Could not migrate set to list");
                        }
                        obj2 = obj;
                    }
                    hashMap.put(str, obj2);
                }
            }
        }
        return hashMap;
    }
}
