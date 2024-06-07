package io.flutter.plugins.firebase.firestore.streamhandler;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.MetadataChanges;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin;
import io.flutter.plugins.firebase.firestore.utils.ExceptionConverter;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes2.dex */
public class DocumentSnapshotsStreamHandler implements EventChannel.StreamHandler {
    ListenerRegistration listenerRegistration;

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onListen(Object obj, final EventChannel.EventSink eventSink) {
        MetadataChanges metadataChanges;
        Map map = (Map) obj;
        Object obj2 = map.get("includeMetadataChanges");
        Objects.requireNonNull(obj2);
        if (((Boolean) obj2).booleanValue()) {
            metadataChanges = MetadataChanges.INCLUDE;
        } else {
            metadataChanges = MetadataChanges.EXCLUDE;
        }
        Object obj3 = map.get("reference");
        Objects.requireNonNull(obj3);
        this.listenerRegistration = ((DocumentReference) obj3).addSnapshotListener(metadataChanges, new EventListener() { // from class: io.flutter.plugins.firebase.firestore.streamhandler.DocumentSnapshotsStreamHandler$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj4, FirebaseFirestoreException firebaseFirestoreException) {
                DocumentSnapshotsStreamHandler.this.m354xe9f41391(eventSink, (DocumentSnapshot) obj4, firebaseFirestoreException);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onListen$0$io-flutter-plugins-firebase-firestore-streamhandler-DocumentSnapshotsStreamHandler, reason: not valid java name */
    public /* synthetic */ void m354xe9f41391(EventChannel.EventSink eventSink, DocumentSnapshot documentSnapshot, FirebaseFirestoreException firebaseFirestoreException) {
        if (firebaseFirestoreException != null) {
            eventSink.error(FlutterFirebaseFirestorePlugin.DEFAULT_ERROR_CODE, firebaseFirestoreException.getMessage(), ExceptionConverter.createDetails(firebaseFirestoreException));
            eventSink.endOfStream();
            onCancel(null);
            return;
        }
        eventSink.success(documentSnapshot);
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
