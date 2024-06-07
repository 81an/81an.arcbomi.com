package io.flutter.plugins.firebase.firestore;

import android.util.Log;
import com.google.firebase.FirebaseApp;
import com.google.firebase.Timestamp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.Blob;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.LoadBundleTaskProgress;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SnapshotMetadata;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugins.firebase.database.Constants;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes2.dex */
class FlutterFirebaseFirestoreMessageCodec extends StandardMessageCodec {
    private static final byte DATA_TYPE_ARRAY_REMOVE = -123;
    private static final byte DATA_TYPE_ARRAY_UNION = -124;
    private static final byte DATA_TYPE_BLOB = -125;
    private static final byte DATA_TYPE_DATE_TIME = Byte.MIN_VALUE;
    private static final byte DATA_TYPE_DELETE = -122;
    private static final byte DATA_TYPE_DOCUMENT_ID = -117;
    private static final byte DATA_TYPE_DOCUMENT_REFERENCE = -126;
    private static final byte DATA_TYPE_FIELD_PATH = -116;
    private static final byte DATA_TYPE_FIRESTORE_INSTANCE = -112;
    private static final byte DATA_TYPE_FIRESTORE_QUERY = -111;
    private static final byte DATA_TYPE_FIRESTORE_SETTINGS = -110;
    private static final byte DATA_TYPE_GEO_POINT = -127;
    private static final byte DATA_TYPE_INCREMENT_DOUBLE = -119;
    private static final byte DATA_TYPE_INCREMENT_INTEGER = -118;
    private static final byte DATA_TYPE_INFINITY = -114;
    private static final byte DATA_TYPE_NAN = -115;
    private static final byte DATA_TYPE_NEGATIVE_INFINITY = -113;
    private static final byte DATA_TYPE_SERVER_TIMESTAMP = -121;
    private static final byte DATA_TYPE_TIMESTAMP = -120;
    public static final FlutterFirebaseFirestoreMessageCodec INSTANCE = new FlutterFirebaseFirestoreMessageCodec();

    FlutterFirebaseFirestoreMessageCodec() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.flutter.plugin.common.StandardMessageCodec
    public void writeValue(ByteArrayOutputStream byteArrayOutputStream, Object obj) {
        if (obj instanceof Date) {
            byteArrayOutputStream.write(-128);
            writeLong(byteArrayOutputStream, ((Date) obj).getTime());
            return;
        }
        if (obj instanceof Timestamp) {
            byteArrayOutputStream.write(-120);
            Timestamp timestamp = (Timestamp) obj;
            writeLong(byteArrayOutputStream, timestamp.getSeconds());
            writeInt(byteArrayOutputStream, timestamp.getNanoseconds());
            return;
        }
        if (obj instanceof GeoPoint) {
            byteArrayOutputStream.write(-127);
            writeAlignment(byteArrayOutputStream, 8);
            GeoPoint geoPoint = (GeoPoint) obj;
            writeDouble(byteArrayOutputStream, geoPoint.getLatitude());
            writeDouble(byteArrayOutputStream, geoPoint.getLongitude());
            return;
        }
        if (obj instanceof DocumentReference) {
            byteArrayOutputStream.write(-126);
            DocumentReference documentReference = (DocumentReference) obj;
            writeValue(byteArrayOutputStream, documentReference.getFirestore().getApp().getName());
            writeValue(byteArrayOutputStream, documentReference.getPath());
            return;
        }
        if (obj instanceof DocumentSnapshot) {
            writeDocumentSnapshot(byteArrayOutputStream, (DocumentSnapshot) obj);
            return;
        }
        if (obj instanceof QuerySnapshot) {
            writeQuerySnapshot(byteArrayOutputStream, (QuerySnapshot) obj);
            return;
        }
        if (obj instanceof DocumentChange) {
            writeDocumentChange(byteArrayOutputStream, (DocumentChange) obj);
            return;
        }
        if (obj instanceof LoadBundleTaskProgress) {
            writeLoadBundleTaskProgress(byteArrayOutputStream, (LoadBundleTaskProgress) obj);
            return;
        }
        if (obj instanceof SnapshotMetadata) {
            writeSnapshotMetadata(byteArrayOutputStream, (SnapshotMetadata) obj);
            return;
        }
        if (obj instanceof Blob) {
            byteArrayOutputStream.write(-125);
            writeBytes(byteArrayOutputStream, ((Blob) obj).toBytes());
            return;
        }
        if (obj instanceof Double) {
            Double d = (Double) obj;
            if (Double.isNaN(d.doubleValue())) {
                byteArrayOutputStream.write(-115);
                return;
            }
            if (d.equals(Double.valueOf(Double.NEGATIVE_INFINITY))) {
                byteArrayOutputStream.write(-113);
                return;
            } else if (d.equals(Double.valueOf(Double.POSITIVE_INFINITY))) {
                byteArrayOutputStream.write(-114);
                return;
            } else {
                super.writeValue(byteArrayOutputStream, obj);
                return;
            }
        }
        super.writeValue(byteArrayOutputStream, obj);
    }

    private void writeSnapshotMetadata(ByteArrayOutputStream byteArrayOutputStream, SnapshotMetadata snapshotMetadata) {
        HashMap hashMap = new HashMap();
        hashMap.put("hasPendingWrites", Boolean.valueOf(snapshotMetadata.hasPendingWrites()));
        hashMap.put("isFromCache", Boolean.valueOf(snapshotMetadata.isFromCache()));
        writeValue(byteArrayOutputStream, hashMap);
    }

    private void writeDocumentChange(ByteArrayOutputStream byteArrayOutputStream, DocumentChange documentChange) {
        HashMap hashMap = new HashMap();
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$DocumentChange$Type[documentChange.getType().ordinal()];
        hashMap.put("type", i != 1 ? i != 2 ? i != 3 ? null : "DocumentChangeType.removed" : "DocumentChangeType.modified" : "DocumentChangeType.added");
        hashMap.put("data", documentChange.getDocument().getData());
        hashMap.put(Constants.PATH, documentChange.getDocument().getReference().getPath());
        hashMap.put("oldIndex", Integer.valueOf(documentChange.getOldIndex()));
        hashMap.put("newIndex", Integer.valueOf(documentChange.getNewIndex()));
        hashMap.put(io.flutter.plugins.firebase.auth.Constants.METADATA, documentChange.getDocument().getMetadata());
        writeValue(byteArrayOutputStream, hashMap);
    }

    private void writeQuerySnapshot(ByteArrayOutputStream byteArrayOutputStream, QuerySnapshot querySnapshot) {
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior = FlutterFirebaseFirestorePlugin.serverTimestampBehaviorHashMap.get(Integer.valueOf(querySnapshot.hashCode()));
        for (DocumentSnapshot documentSnapshot : querySnapshot.getDocuments()) {
            arrayList.add(documentSnapshot.getReference().getPath());
            if (serverTimestampBehavior != null) {
                arrayList2.add(documentSnapshot.getData(serverTimestampBehavior));
            } else {
                arrayList2.add(documentSnapshot.getData());
            }
            arrayList3.add(documentSnapshot.getMetadata());
        }
        hashMap.put("paths", arrayList);
        hashMap.put("documents", arrayList2);
        hashMap.put("metadatas", arrayList3);
        hashMap.put("documentChanges", querySnapshot.getDocumentChanges());
        hashMap.put(io.flutter.plugins.firebase.auth.Constants.METADATA, querySnapshot.getMetadata());
        FlutterFirebaseFirestorePlugin.serverTimestampBehaviorHashMap.remove(Integer.valueOf(querySnapshot.hashCode()));
        writeValue(byteArrayOutputStream, hashMap);
    }

    private void writeLoadBundleTaskProgress(ByteArrayOutputStream byteArrayOutputStream, LoadBundleTaskProgress loadBundleTaskProgress) {
        HashMap hashMap = new HashMap();
        hashMap.put("bytesLoaded", Long.valueOf(loadBundleTaskProgress.getBytesLoaded()));
        hashMap.put("documentsLoaded", Integer.valueOf(loadBundleTaskProgress.getDocumentsLoaded()));
        hashMap.put("totalBytes", Long.valueOf(loadBundleTaskProgress.getTotalBytes()));
        hashMap.put("totalDocuments", Integer.valueOf(loadBundleTaskProgress.getTotalDocuments()));
        int i = AnonymousClass1.$SwitchMap$com$google$firebase$firestore$LoadBundleTaskProgress$TaskState[loadBundleTaskProgress.getTaskState().ordinal()];
        String str = "running";
        if (i != 1) {
            if (i == 2) {
                str = FirebaseAnalytics.Param.SUCCESS;
            } else if (i == 3) {
                str = "error";
            }
        }
        hashMap.put("taskState", str);
        writeValue(byteArrayOutputStream, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: io.flutter.plugins.firebase.firestore.FlutterFirebaseFirestoreMessageCodec$1, reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$DocumentChange$Type;
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$LoadBundleTaskProgress$TaskState;

        static {
            int[] iArr = new int[LoadBundleTaskProgress.TaskState.values().length];
            $SwitchMap$com$google$firebase$firestore$LoadBundleTaskProgress$TaskState = iArr;
            try {
                iArr[LoadBundleTaskProgress.TaskState.RUNNING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$LoadBundleTaskProgress$TaskState[LoadBundleTaskProgress.TaskState.SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$LoadBundleTaskProgress$TaskState[LoadBundleTaskProgress.TaskState.ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[DocumentChange.Type.values().length];
            $SwitchMap$com$google$firebase$firestore$DocumentChange$Type = iArr2;
            try {
                iArr2[DocumentChange.Type.ADDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$DocumentChange$Type[DocumentChange.Type.MODIFIED.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$DocumentChange$Type[DocumentChange.Type.REMOVED.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private void writeDocumentSnapshot(ByteArrayOutputStream byteArrayOutputStream, DocumentSnapshot documentSnapshot) {
        HashMap hashMap = new HashMap();
        hashMap.put(Constants.PATH, documentSnapshot.getReference().getPath());
        if (documentSnapshot.exists()) {
            DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior = FlutterFirebaseFirestorePlugin.serverTimestampBehaviorHashMap.get(Integer.valueOf(documentSnapshot.hashCode()));
            if (serverTimestampBehavior != null) {
                hashMap.put("data", documentSnapshot.getData(serverTimestampBehavior));
            } else {
                hashMap.put("data", documentSnapshot.getData());
            }
        } else {
            hashMap.put("data", null);
        }
        hashMap.put(io.flutter.plugins.firebase.auth.Constants.METADATA, documentSnapshot.getMetadata());
        FlutterFirebaseFirestorePlugin.serverTimestampBehaviorHashMap.remove(Integer.valueOf(documentSnapshot.hashCode()));
        writeValue(byteArrayOutputStream, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.flutter.plugin.common.StandardMessageCodec
    public Object readValueOfType(byte b, ByteBuffer byteBuffer) {
        switch (b) {
            case Byte.MIN_VALUE:
                return new Date(byteBuffer.getLong());
            case -127:
                readAlignment(byteBuffer, 8);
                return new GeoPoint(byteBuffer.getDouble(), byteBuffer.getDouble());
            case -126:
                return ((FirebaseFirestore) readValue(byteBuffer)).document((String) readValue(byteBuffer));
            case -125:
                return Blob.fromBytes(readBytes(byteBuffer));
            case -124:
                return FieldValue.arrayUnion(toArray(readValue(byteBuffer)));
            case -123:
                return FieldValue.arrayRemove(toArray(readValue(byteBuffer)));
            case -122:
                return FieldValue.delete();
            case -121:
                return FieldValue.serverTimestamp();
            case -120:
                return new Timestamp(byteBuffer.getLong(), byteBuffer.getInt());
            case -119:
                return FieldValue.increment(((Number) readValue(byteBuffer)).doubleValue());
            case -118:
                return FieldValue.increment(((Number) readValue(byteBuffer)).intValue());
            case -117:
                return FieldPath.documentId();
            case -116:
                int readSize = readSize(byteBuffer);
                ArrayList arrayList = new ArrayList(readSize);
                for (int i = 0; i < readSize; i++) {
                    arrayList.add(readValue(byteBuffer));
                }
                return FieldPath.of((String[]) arrayList.toArray(new String[0]));
            case -115:
                return Double.valueOf(Double.NaN);
            case -114:
                return Double.valueOf(Double.POSITIVE_INFINITY);
            case -113:
                return Double.valueOf(Double.NEGATIVE_INFINITY);
            case -112:
                return readFirestoreInstance(byteBuffer);
            case -111:
                return readFirestoreQuery(byteBuffer);
            case -110:
                return readFirestoreSettings(byteBuffer);
            default:
                return super.readValueOfType(b, byteBuffer);
        }
    }

    private FirebaseFirestore readFirestoreInstance(ByteBuffer byteBuffer) {
        String str = (String) readValue(byteBuffer);
        FirebaseFirestoreSettings firebaseFirestoreSettings = (FirebaseFirestoreSettings) readValue(byteBuffer);
        synchronized (FlutterFirebaseFirestorePlugin.firestoreInstanceCache) {
            if (FlutterFirebaseFirestorePlugin.getCachedFirebaseFirestoreInstanceForKey(str) != null) {
                return FlutterFirebaseFirestorePlugin.getCachedFirebaseFirestoreInstanceForKey(str);
            }
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance(FirebaseApp.getInstance(str));
            firebaseFirestore.setFirestoreSettings(firebaseFirestoreSettings);
            FlutterFirebaseFirestorePlugin.setCachedFirebaseFirestoreInstanceForKey(firebaseFirestore, str);
            return firebaseFirestore;
        }
    }

    private FirebaseFirestoreSettings readFirestoreSettings(ByteBuffer byteBuffer) {
        Map map = (Map) readValue(byteBuffer);
        FirebaseFirestoreSettings.Builder builder = new FirebaseFirestoreSettings.Builder();
        if (map.get(Constants.DATABASE_PERSISTENCE_ENABLED) != null) {
            Object obj = map.get(Constants.DATABASE_PERSISTENCE_ENABLED);
            Objects.requireNonNull(obj);
            builder.setPersistenceEnabled(((Boolean) obj).booleanValue());
        }
        if (map.get(io.flutter.plugins.firebase.auth.Constants.HOST) != null) {
            Object obj2 = map.get(io.flutter.plugins.firebase.auth.Constants.HOST);
            Objects.requireNonNull(obj2);
            builder.setHost((String) obj2);
            if (map.get("sslEnabled") != null) {
                Object obj3 = map.get("sslEnabled");
                Objects.requireNonNull(obj3);
                builder.setSslEnabled(((Boolean) obj3).booleanValue());
            }
        }
        if (map.get(Constants.DATABASE_CACHE_SIZE_BYTES) != null) {
            Long l = 104857600L;
            Object obj4 = map.get(Constants.DATABASE_CACHE_SIZE_BYTES);
            if (obj4 instanceof Long) {
                l = (Long) obj4;
            } else if (obj4 instanceof Integer) {
                l = Long.valueOf(((Integer) obj4).intValue());
            }
            if (l.longValue() == -1) {
                builder.setCacheSizeBytes(-1L);
            } else {
                builder.setCacheSizeBytes(l.longValue());
            }
        }
        return builder.build();
    }

    private Query readFirestoreQuery(ByteBuffer byteBuffer) {
        Query collection;
        try {
            Map map = (Map) readValue(byteBuffer);
            Object obj = map.get("firestore");
            Objects.requireNonNull(obj);
            FirebaseFirestore firebaseFirestore = (FirebaseFirestore) obj;
            Object obj2 = map.get(Constants.PATH);
            Objects.requireNonNull(obj2);
            String str = (String) obj2;
            boolean booleanValue = ((Boolean) map.get("isCollectionGroup")).booleanValue();
            Map map2 = (Map) map.get(io.flutter.plugins.firebase.analytics.Constants.PARAMETERS);
            if (booleanValue) {
                collection = firebaseFirestore.collectionGroup(str);
            } else {
                collection = firebaseFirestore.collection(str);
            }
            if (map2 == null) {
                return collection;
            }
            Object obj3 = map2.get("where");
            Objects.requireNonNull(obj3);
            for (List list : (List) obj3) {
                FieldPath fieldPath = (FieldPath) list.get(0);
                String str2 = (String) list.get(1);
                Object obj4 = list.get(2);
                if ("==".equals(str2)) {
                    collection = collection.whereEqualTo(fieldPath, obj4);
                } else if ("!=".equals(str2)) {
                    collection = collection.whereNotEqualTo(fieldPath, obj4);
                } else if ("<".equals(str2)) {
                    collection = collection.whereLessThan(fieldPath, obj4);
                } else if ("<=".equals(str2)) {
                    collection = collection.whereLessThanOrEqualTo(fieldPath, obj4);
                } else if (">".equals(str2)) {
                    collection = collection.whereGreaterThan(fieldPath, obj4);
                } else if (">=".equals(str2)) {
                    collection = collection.whereGreaterThanOrEqualTo(fieldPath, obj4);
                } else if ("array-contains".equals(str2)) {
                    collection = collection.whereArrayContains(fieldPath, obj4);
                } else if ("array-contains-any".equals(str2)) {
                    collection = collection.whereArrayContainsAny(fieldPath, (List<? extends Object>) obj4);
                } else if ("in".equals(str2)) {
                    collection = collection.whereIn(fieldPath, (List<? extends Object>) obj4);
                } else if ("not-in".equals(str2)) {
                    collection = collection.whereNotIn(fieldPath, (List<? extends Object>) obj4);
                } else {
                    Log.w("FLTFirestoreMsgCodec", "An invalid query operator " + str2 + " was received but not handled.");
                }
            }
            Number number = (Number) map2.get(Constants.LIMIT);
            if (number != null) {
                collection = collection.limit(number.longValue());
            }
            Number number2 = (Number) map2.get(Constants.LIMIT_TO_LAST);
            if (number2 != null) {
                collection = collection.limitToLast(number2.longValue());
            }
            List<List> list2 = (List) map2.get(Constants.ORDER_BY);
            if (list2 == null) {
                return collection;
            }
            for (List list3 : list2) {
                collection = collection.orderBy((FieldPath) list3.get(0), ((Boolean) list3.get(1)).booleanValue() ? Query.Direction.DESCENDING : Query.Direction.ASCENDING);
            }
            List list4 = (List) map2.get(Constants.START_AT);
            if (list4 != null) {
                Object[] array = list4.toArray();
                Objects.requireNonNull(array);
                collection = collection.startAt(array);
            }
            List list5 = (List) map2.get(Constants.START_AFTER);
            if (list5 != null) {
                Object[] array2 = list5.toArray();
                Objects.requireNonNull(array2);
                collection = collection.startAfter(array2);
            }
            List list6 = (List) map2.get(Constants.END_AT);
            if (list6 != null) {
                Object[] array3 = list6.toArray();
                Objects.requireNonNull(array3);
                collection = collection.endAt(array3);
            }
            List list7 = (List) map2.get(Constants.END_BEFORE);
            if (list7 == null) {
                return collection;
            }
            Object[] array4 = list7.toArray();
            Objects.requireNonNull(array4);
            return collection.endBefore(array4);
        } catch (Exception e) {
            Log.e("FLTFirestoreMsgCodec", "An error occurred while parsing query arguments, this is most likely an error with this SDK.", e);
            return null;
        }
    }

    private Object[] toArray(Object obj) {
        if (obj instanceof List) {
            return ((List) obj).toArray();
        }
        if (obj == null) {
            return new ArrayList().toArray();
        }
        throw new IllegalArgumentException(String.format("java.util.List was expected, unable to convert '%s' to an object array", obj.getClass().getCanonicalName()));
    }
}
