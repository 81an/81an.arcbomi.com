package io.flutter.plugins.firebase.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import io.flutter.plugin.common.EventChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes2.dex */
public class IdTokenChannelStreamHandler implements EventChannel.StreamHandler {
    private final FirebaseAuth firebaseAuth;
    private FirebaseAuth.IdTokenListener idTokenListener;

    public IdTokenChannelStreamHandler(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onListen(Object obj, final EventChannel.EventSink eventSink) {
        final HashMap hashMap = new HashMap();
        hashMap.put("appName", this.firebaseAuth.getApp().getName());
        final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        FirebaseAuth.IdTokenListener idTokenListener = new FirebaseAuth.IdTokenListener() { // from class: io.flutter.plugins.firebase.auth.IdTokenChannelStreamHandler$$ExternalSyntheticLambda0
            @Override // com.google.firebase.auth.FirebaseAuth.IdTokenListener
            public final void onIdTokenChanged(FirebaseAuth firebaseAuth) {
                IdTokenChannelStreamHandler.lambda$onListen$0(atomicBoolean, hashMap, eventSink, firebaseAuth);
            }
        };
        this.idTokenListener = idTokenListener;
        this.firebaseAuth.addIdTokenListener(idTokenListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onListen$0(AtomicBoolean atomicBoolean, Map map, EventChannel.EventSink eventSink, FirebaseAuth firebaseAuth) {
        if (atomicBoolean.get()) {
            atomicBoolean.set(false);
            return;
        }
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null) {
            map.put(Constants.USER, null);
        } else {
            map.put(Constants.USER, FlutterFirebaseAuthPlugin.parseFirebaseUser(currentUser));
        }
        eventSink.success(map);
    }

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onCancel(Object obj) {
        FirebaseAuth.IdTokenListener idTokenListener = this.idTokenListener;
        if (idTokenListener != null) {
            this.firebaseAuth.removeIdTokenListener(idTokenListener);
            this.idTokenListener = null;
        }
    }
}
