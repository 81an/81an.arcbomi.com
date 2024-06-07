package io.flutter.plugins.firebase.firestore.streamhandler;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.MetadataChanges;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin;
import io.flutter.plugins.firebase.firestore.utils.ExceptionConverter;
import io.flutter.plugins.firebase.firestore.utils.ServerTimestampBehaviorConverter;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes2.dex */
public class QuerySnapshotsStreamHandler implements EventChannel.StreamHandler {
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
        Query query = (Query) map.get("query");
        final DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior = ServerTimestampBehaviorConverter.toServerTimestampBehavior((String) map.get("serverTimestampBehavior"));
        if (query == null) {
            throw new IllegalArgumentException("An error occurred while parsing query arguments, see native logs for more information. Please report this issue.");
        }
        this.listenerRegistration = query.addSnapshotListener(metadataChanges, new EventListener() { // from class: io.flutter.plugins.firebase.firestore.streamhandler.QuerySnapshotsStreamHandler$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj3, FirebaseFirestoreException firebaseFirestoreException) {
                QuerySnapshotsStreamHandler.this.m356x353c19a8(eventSink, serverTimestampBehavior, (QuerySnapshot) obj3, firebaseFirestoreException);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onListen$0$io-flutter-plugins-firebase-firestore-streamhandler-QuerySnapshotsStreamHandler, reason: not valid java name */
    public /* synthetic */ void m356x353c19a8(EventChannel.EventSink eventSink, DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior, QuerySnapshot querySnapshot, FirebaseFirestoreException firebaseFirestoreException) {
        if (firebaseFirestoreException != null) {
            eventSink.error(FlutterFirebaseFirestorePlugin.DEFAULT_ERROR_CODE, firebaseFirestoreException.getMessage(), ExceptionConverter.createDetails(firebaseFirestoreException));
            eventSink.endOfStream();
            onCancel(null);
            return;
        }
        if (querySnapshot != null) {
            FlutterFirebaseFirestorePlugin.serverTimestampBehaviorHashMap.put(Integer.valueOf(querySnapshot.hashCode()), serverTimestampBehavior);
        }
        eventSink.success(querySnapshot);
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
