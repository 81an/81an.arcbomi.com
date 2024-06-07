package io.flutter.plugins.firebase.firestore.streamhandler;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import io.flutter.plugin.common.EventChannel;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes2.dex */
public class SnapshotsInSyncStreamHandler implements EventChannel.StreamHandler {
    ListenerRegistration listenerRegistration;

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onListen(Object obj, final EventChannel.EventSink eventSink) {
        Object obj2 = ((Map) obj).get("firestore");
        Objects.requireNonNull(obj2);
        this.listenerRegistration = ((FirebaseFirestore) obj2).addSnapshotsInSyncListener(new Runnable() { // from class: io.flutter.plugins.firebase.firestore.streamhandler.SnapshotsInSyncStreamHandler$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                EventChannel.EventSink.this.success(null);
            }
        });
    }

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onCancel(Object obj) {
        ListenerRegistration listenerRegistration = this.listenerRegistration;
        if (listenerRegistration != null) {
            listenerRegistration.remove();
            this.listenerRegistration = null;
        }
    }
}
