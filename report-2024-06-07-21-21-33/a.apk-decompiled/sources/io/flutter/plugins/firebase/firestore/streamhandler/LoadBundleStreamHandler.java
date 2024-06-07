package io.flutter.plugins.firebase.firestore.streamhandler;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.LoadBundleTask;
import com.google.firebase.firestore.LoadBundleTaskProgress;
import com.google.firebase.firestore.OnProgressListener;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestorePlugin;
import io.flutter.plugins.firebase.firestore.utils.ExceptionConverter;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes2.dex */
public class LoadBundleStreamHandler implements EventChannel.StreamHandler {
    private EventChannel.EventSink eventSink;

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onListen(Object obj, final EventChannel.EventSink eventSink) {
        this.eventSink = eventSink;
        Map map = (Map) obj;
        Object obj2 = map.get("bundle");
        Objects.requireNonNull(obj2);
        Object obj3 = map.get("firestore");
        Objects.requireNonNull(obj3);
        LoadBundleTask loadBundle = ((FirebaseFirestore) obj3).loadBundle((byte[]) obj2);
        loadBundle.addOnProgressListener(new OnProgressListener() { // from class: io.flutter.plugins.firebase.firestore.streamhandler.LoadBundleStreamHandler$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.OnProgressListener
            public final void onProgress(Object obj4) {
                EventChannel.EventSink.this.success((LoadBundleTaskProgress) obj4);
            }
        });
        loadBundle.addOnFailureListener(new OnFailureListener() { // from class: io.flutter.plugins.firebase.firestore.streamhandler.LoadBundleStreamHandler$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                LoadBundleStreamHandler.this.m355x80fc3288(eventSink, exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$onListen$1$io-flutter-plugins-firebase-firestore-streamhandler-LoadBundleStreamHandler, reason: not valid java name */
    public /* synthetic */ void m355x80fc3288(EventChannel.EventSink eventSink, Exception exc) {
        eventSink.error(FlutterFirebaseFirestorePlugin.DEFAULT_ERROR_CODE, exc.getMessage(), ExceptionConverter.createDetails(exc));
        onCancel(null);
    }

    @Override // io.flutter.plugin.common.EventChannel.StreamHandler
    public void onCancel(Object obj) {
        this.eventSink.endOfStream();
    }
}
