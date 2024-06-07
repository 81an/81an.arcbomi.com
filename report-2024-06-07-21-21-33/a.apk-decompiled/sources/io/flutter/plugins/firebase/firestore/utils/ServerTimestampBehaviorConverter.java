package io.flutter.plugins.firebase.firestore.utils;

import com.google.android.gms.fido.fido2.api.common.DevicePublicKeyStringDef;
import com.google.firebase.firestore.DocumentSnapshot;

/* loaded from: classes2.dex */
public class ServerTimestampBehaviorConverter {
    public static DocumentSnapshot.ServerTimestampBehavior toServerTimestampBehavior(String str) {
        if (str == null) {
            return DocumentSnapshot.ServerTimestampBehavior.NONE;
        }
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != -1959779032) {
            if (hashCode != -1273775369) {
                if (hashCode == 3387192 && str.equals(DevicePublicKeyStringDef.NONE)) {
                    c = 2;
                }
            } else if (str.equals("previous")) {
                c = 1;
            }
        } else if (str.equals("estimate")) {
            c = 0;
        }
        if (c == 0) {
            return DocumentSnapshot.ServerTimestampBehavior.ESTIMATE;
        }
        if (c == 1) {
            return DocumentSnapshot.ServerTimestampBehavior.PREVIOUS;
        }
        return DocumentSnapshot.ServerTimestampBehavior.NONE;
    }
}
