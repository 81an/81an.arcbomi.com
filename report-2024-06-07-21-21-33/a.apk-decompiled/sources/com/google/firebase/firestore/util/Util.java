package com.google.firebase.firestore.util;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.cloud.datastore.core.number.NumberComparisonHelper;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.protobuf.ByteString;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.StatusRuntimeException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedSet;

/* loaded from: classes2.dex */
public class Util {
    private static final String AUTO_ID_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int AUTO_ID_LENGTH = 20;
    private static final Random rand = new SecureRandom();
    private static final Continuation<Void, Void> VOID_ERROR_TRANSFORMER = new Continuation() { // from class: com.google.firebase.firestore.util.Util$$ExternalSyntheticLambda0
        @Override // com.google.android.gms.tasks.Continuation
        public final Object then(Task task) {
            return Util.lambda$static$0(task);
        }
    };

    public static int compareBooleans(boolean z, boolean z2) {
        if (z == z2) {
            return 0;
        }
        return z ? 1 : -1;
    }

    public static int compareIntegers(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i > i2 ? 1 : 0;
    }

    public static String autoId() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            sb.append(AUTO_ID_ALPHABET.charAt(rand.nextInt(62)));
        }
        return sb.toString();
    }

    public static int compareLongs(long j, long j2) {
        return NumberComparisonHelper.compareLongs(j, j2);
    }

    public static int compareDoubles(double d, double d2) {
        return NumberComparisonHelper.firestoreCompareDoubles(d, d2);
    }

    public static int compareMixed(double d, long j) {
        return NumberComparisonHelper.firestoreCompareDoubleWithLong(d, j);
    }

    public static <T extends Comparable<T>> Comparator<T> comparator() {
        return new Comparator() { // from class: com.google.firebase.firestore.util.Util$$ExternalSyntheticLambda5
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return ((Comparable) obj).compareTo((Comparable) obj2);
            }
        };
    }

    public static FirebaseFirestoreException exceptionFromStatus(Status status) {
        StatusException asException = status.asException();
        return new FirebaseFirestoreException(asException.getMessage(), FirebaseFirestoreException.Code.fromValue(status.getCode().value()), asException);
    }

    private static Exception convertStatusException(Exception exc) {
        if (exc instanceof StatusException) {
            return exceptionFromStatus(((StatusException) exc).getStatus());
        }
        return exc instanceof StatusRuntimeException ? exceptionFromStatus(((StatusRuntimeException) exc).getStatus()) : exc;
    }

    public static Exception convertThrowableToException(Throwable th) {
        if (th instanceof Exception) {
            return convertStatusException((Exception) th);
        }
        return new Exception(th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Void lambda$static$0(Task task) throws Exception {
        if (task.isSuccessful()) {
            return (Void) task.getResult();
        }
        Exception convertStatusException = convertStatusException(task.getException());
        if (convertStatusException instanceof FirebaseFirestoreException) {
            throw convertStatusException;
        }
        throw new FirebaseFirestoreException(convertStatusException.getMessage(), FirebaseFirestoreException.Code.UNKNOWN, convertStatusException);
    }

    public static Continuation<Void, Void> voidErrorTransformer() {
        return VOID_ERROR_TRANSFORMER;
    }

    public static List<Object> collectUpdateArguments(int i, Object obj, Object obj2, Object... objArr) {
        if (objArr.length % 2 == 1) {
            throw new IllegalArgumentException("Missing value in call to update().  There must be an even number of arguments that alternate between field names and values");
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(obj);
        arrayList.add(obj2);
        Collections.addAll(arrayList, objArr);
        for (int i2 = 0; i2 < arrayList.size(); i2 += 2) {
            Object obj3 = arrayList.get(i2);
            if (!(obj3 instanceof String) && !(obj3 instanceof FieldPath)) {
                throw new IllegalArgumentException("Excepted field name at argument position " + (i2 + i + 1) + " but got " + obj3 + " in call to update.  The arguments to update should alternate between field names and values");
            }
        }
        return arrayList;
    }

    public static String toDebugString(ByteString byteString) {
        int size = byteString.size();
        StringBuilder sb = new StringBuilder(size * 2);
        for (int i = 0; i < size; i++) {
            int byteAt = byteString.byteAt(i) & 255;
            sb.append(Character.forDigit(byteAt >>> 4, 16));
            sb.append(Character.forDigit(byteAt & 15, 16));
        }
        return sb.toString();
    }

    public static String typeName(Object obj) {
        return obj == null ? "null" : obj.getClass().getName();
    }

    public static void crashMainThread(final RuntimeException runtimeException) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.google.firebase.firestore.util.Util$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                Util.lambda$crashMainThread$1(runtimeException);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$crashMainThread$1(RuntimeException runtimeException) {
        throw runtimeException;
    }

    public static int compareByteArrays(byte[] bArr, byte[] bArr2) {
        int min = Math.min(bArr.length, bArr2.length);
        for (int i = 0; i < min; i++) {
            int i2 = bArr[i] & 255;
            int i3 = bArr2[i] & 255;
            if (i2 < i3) {
                return -1;
            }
            if (i2 > i3) {
                return 1;
            }
        }
        return compareIntegers(bArr.length, bArr2.length);
    }

    public static int compareByteStrings(ByteString byteString, ByteString byteString2) {
        int min = Math.min(byteString.size(), byteString2.size());
        for (int i = 0; i < min; i++) {
            int byteAt = byteString.byteAt(i) & 255;
            int byteAt2 = byteString2.byteAt(i) & 255;
            if (byteAt < byteAt2) {
                return -1;
            }
            if (byteAt > byteAt2) {
                return 1;
            }
        }
        return compareIntegers(byteString.size(), byteString2.size());
    }

    public static StringBuilder repeatSequence(CharSequence charSequence, int i, CharSequence charSequence2) {
        StringBuilder sb = new StringBuilder();
        if (i != 0) {
            sb.append(charSequence);
            for (int i2 = 1; i2 < i; i2++) {
                sb.append(charSequence2);
                sb.append(charSequence);
            }
        }
        return sb;
    }

    public static <T> void diffCollections(Collection<T> collection, Collection<T> collection2, Comparator<T> comparator, Consumer<T> consumer, Consumer<T> consumer2) {
        ArrayList arrayList = new ArrayList(collection);
        Collections.sort(arrayList, comparator);
        ArrayList arrayList2 = new ArrayList(collection2);
        Collections.sort(arrayList2, comparator);
        diffCollections(arrayList.iterator(), arrayList2.iterator(), comparator, consumer, consumer2);
    }

    public static <T extends Comparable<T>> void diffCollections(SortedSet<T> sortedSet, SortedSet<T> sortedSet2, Consumer<T> consumer, Consumer<T> consumer2) {
        diffCollections(sortedSet.iterator(), sortedSet2.iterator(), sortedSet.comparator() != null ? sortedSet.comparator() : new Comparator() { // from class: com.google.firebase.firestore.util.Util$$ExternalSyntheticLambda4
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compareTo;
                compareTo = ((Comparable) obj).compareTo((Comparable) obj2);
                return compareTo;
            }
        }, consumer, consumer2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001b, code lost:
    
        if (r4 > 0) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0021, code lost:
    
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x001e, code lost:
    
        if (r0 != null) goto L19;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x002d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0025 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static <T> void diffCollections(java.util.Iterator<T> r5, java.util.Iterator<T> r6, java.util.Comparator<? super T> r7, com.google.firebase.firestore.util.Consumer<T> r8, com.google.firebase.firestore.util.Consumer<T> r9) {
        /*
            java.lang.Object r0 = advanceIterator(r5)
            java.lang.Object r1 = advanceIterator(r6)
        L8:
            if (r0 != 0) goto Le
            if (r1 == 0) goto Ld
            goto Le
        Ld:
            return
        Le:
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L1e
            if (r1 == 0) goto L1e
            int r4 = r7.compare(r0, r1)
            if (r4 >= 0) goto L1b
            goto L23
        L1b:
            if (r4 <= 0) goto L22
            goto L21
        L1e:
            if (r0 == 0) goto L21
            goto L23
        L21:
            r2 = 1
        L22:
            r3 = 0
        L23:
            if (r2 == 0) goto L2d
            r8.accept(r1)
            java.lang.Object r1 = advanceIterator(r6)
            goto L8
        L2d:
            if (r3 == 0) goto L37
            r9.accept(r0)
            java.lang.Object r0 = advanceIterator(r5)
            goto L8
        L37:
            java.lang.Object r0 = advanceIterator(r5)
            java.lang.Object r1 = advanceIterator(r6)
            goto L8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.util.Util.diffCollections(java.util.Iterator, java.util.Iterator, java.util.Comparator, com.google.firebase.firestore.util.Consumer, com.google.firebase.firestore.util.Consumer):void");
    }

    private static <T> T advanceIterator(Iterator<T> it) {
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    public static <K, V> Iterable<V> values(final Iterable<Map.Entry<K, V>> iterable) {
        return new Iterable() { // from class: com.google.firebase.firestore.util.Util$$ExternalSyntheticLambda1
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return Util.lambda$values$3(iterable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Iterator lambda$values$3(Iterable iterable) {
        final Iterator it = iterable.iterator();
        return new Iterator<V>() { // from class: com.google.firebase.firestore.util.Util.1
            @Override // java.util.Iterator
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override // java.util.Iterator
            public V next() {
                return (V) ((Map.Entry) it.next()).getValue();
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <K, V> Map<K, V> firstNEntries(Map<K, V> map, int i, final Comparator<V> comparator) {
        if (map.size() <= i) {
            return map;
        }
        ArrayList arrayList = new ArrayList(map.entrySet());
        Collections.sort(arrayList, new Comparator() { // from class: com.google.firebase.firestore.util.Util$$ExternalSyntheticLambda3
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = comparator.compare(((Map.Entry) obj).getValue(), ((Map.Entry) obj2).getValue());
                return compare;
            }
        });
        HashMap hashMap = new HashMap();
        for (int i2 = 0; i2 < i; i2++) {
            hashMap.put(((Map.Entry) arrayList.get(i2)).getKey(), ((Map.Entry) arrayList.get(i2)).getValue());
        }
        return hashMap;
    }
}
