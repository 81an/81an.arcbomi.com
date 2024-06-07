package io.flutter.plugins.webviewflutter;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;

/* loaded from: classes2.dex */
public class InstanceManager {
    private static final long CLEAR_FINALIZED_WEAK_REFERENCES_INTERVAL = 30000;
    private static final String CLOSED_WARNING = "Method was called while the manager was closed.";
    public static final int INSTANCE_CLOSED = -1;
    private static final long MIN_HOST_CREATED_IDENTIFIER = 65536;
    private static final String TAG = "InstanceManager";
    private final FinalizationListener finalizationListener;
    private final Handler handler;
    private boolean isClosed;
    private long nextIdentifier;
    private final WeakHashMap<Object, Long> identifiers = new WeakHashMap<>();
    private final HashMap<Long, WeakReference<Object>> weakInstances = new HashMap<>();
    private final HashMap<Long, Object> strongInstances = new HashMap<>();
    private final ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
    private final HashMap<WeakReference<Object>, Long> weakReferencesToIdentifiers = new HashMap<>();

    /* loaded from: classes2.dex */
    public interface FinalizationListener {
        void onFinalize(long j);
    }

    public static InstanceManager open(FinalizationListener finalizationListener) {
        return new InstanceManager(finalizationListener);
    }

    private InstanceManager(FinalizationListener finalizationListener) {
        Handler handler = new Handler(Looper.getMainLooper());
        this.handler = handler;
        this.nextIdentifier = MIN_HOST_CREATED_IDENTIFIER;
        this.isClosed = false;
        this.finalizationListener = finalizationListener;
        handler.postDelayed(new InstanceManager$$ExternalSyntheticLambda0(this), CLEAR_FINALIZED_WEAK_REFERENCES_INTERVAL);
    }

    public <T> T remove(long j) {
        if (assertNotClosed()) {
            return null;
        }
        return (T) this.strongInstances.remove(Long.valueOf(j));
    }

    public Long getIdentifierForStrongReference(Object obj) {
        if (assertNotClosed()) {
            return null;
        }
        Long l = this.identifiers.get(obj);
        if (l != null) {
            this.strongInstances.put(l, obj);
        }
        return l;
    }

    public void addDartCreatedInstance(Object obj, long j) {
        if (assertNotClosed()) {
            return;
        }
        addInstance(obj, j);
    }

    public long addHostCreatedInstance(Object obj) {
        if (assertNotClosed()) {
            return -1L;
        }
        if (containsInstance(obj)) {
            throw new IllegalArgumentException(String.format("Instance of `%s` has already been added.", obj.getClass()));
        }
        long j = this.nextIdentifier;
        this.nextIdentifier = 1 + j;
        addInstance(obj, j);
        return j;
    }

    public <T> T getInstance(long j) {
        WeakReference<Object> weakReference;
        if (assertNotClosed() || (weakReference = this.weakInstances.get(Long.valueOf(j))) == null) {
            return null;
        }
        return (T) weakReference.get();
    }

    public boolean containsInstance(Object obj) {
        if (assertNotClosed()) {
            return false;
        }
        return this.identifiers.containsKey(obj);
    }

    public void close() {
        this.handler.removeCallbacks(new InstanceManager$$ExternalSyntheticLambda0(this));
        this.isClosed = true;
        clear();
    }

    public void clear() {
        this.identifiers.clear();
        this.weakInstances.clear();
        this.strongInstances.clear();
        this.weakReferencesToIdentifiers.clear();
    }

    public boolean isClosed() {
        return this.isClosed;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseAllFinalizedInstances() {
        while (true) {
            WeakReference weakReference = (WeakReference) this.referenceQueue.poll();
            if (weakReference != null) {
                Long remove = this.weakReferencesToIdentifiers.remove(weakReference);
                if (remove != null) {
                    this.weakInstances.remove(remove);
                    this.strongInstances.remove(remove);
                    this.finalizationListener.onFinalize(remove.longValue());
                }
            } else {
                this.handler.postDelayed(new InstanceManager$$ExternalSyntheticLambda0(this), CLEAR_FINALIZED_WEAK_REFERENCES_INTERVAL);
                return;
            }
        }
    }

    private void addInstance(Object obj, long j) {
        if (j < 0) {
            throw new IllegalArgumentException(String.format("Identifier must be >= 0: %d", Long.valueOf(j)));
        }
        if (this.weakInstances.containsKey(Long.valueOf(j))) {
            throw new IllegalArgumentException(String.format("Identifier has already been added: %d", Long.valueOf(j)));
        }
        WeakReference<Object> weakReference = new WeakReference<>(obj, this.referenceQueue);
        this.identifiers.put(obj, Long.valueOf(j));
        this.weakInstances.put(Long.valueOf(j), weakReference);
        this.weakReferencesToIdentifiers.put(weakReference, Long.valueOf(j));
        this.strongInstances.put(Long.valueOf(j), obj);
    }

    private boolean assertNotClosed() {
        if (!isClosed()) {
            return false;
        }
        Log.w(TAG, CLOSED_WARNING);
        return true;
    }
}
