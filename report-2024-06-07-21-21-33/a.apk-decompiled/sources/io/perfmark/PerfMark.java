package io.perfmark;

/* loaded from: classes2.dex */
public final class PerfMark {
    private static final Impl impl;

    public static void setEnabled(boolean z) {
        impl.setEnabled(z);
    }

    public static void startTask(String str, Tag tag) {
        impl.startTask(str, tag);
    }

    public static void startTask(String str) {
        impl.startTask(str);
    }

    public static <T> void startTask(T t, StringFunction<? super T> stringFunction) {
        impl.startTask((Impl) t, (StringFunction<? super Impl>) stringFunction);
    }

    public static void startTask(String str, String str2) {
        impl.startTask(str, str2);
    }

    public static TaskCloseable traceTask(String str) {
        impl.startTask(str);
        return TaskCloseable.INSTANCE;
    }

    public static <T> TaskCloseable traceTask(T t, StringFunction<? super T> stringFunction) {
        impl.startTask((Impl) t, (StringFunction<? super Impl>) stringFunction);
        return TaskCloseable.INSTANCE;
    }

    public static void event(String str, Tag tag) {
        impl.event(str, tag);
    }

    public static void event(String str) {
        impl.event(str);
    }

    public static void event(String str, String str2) {
        impl.event(str, str2);
    }

    public static void stopTask() {
        impl.stopTask();
    }

    public static void stopTask(String str, Tag tag) {
        impl.stopTask(str, tag);
    }

    public static void stopTask(String str) {
        impl.stopTask(str);
    }

    public static void stopTask(String str, String str2) {
        impl.stopTask(str, str2);
    }

    public static Tag createTag() {
        return Impl.NO_TAG;
    }

    public static Tag createTag(long j) {
        return impl.createTag("", j);
    }

    public static Tag createTag(String str) {
        return impl.createTag(str, Long.MIN_VALUE);
    }

    public static Tag createTag(String str, long j) {
        return impl.createTag(str, j);
    }

    @Deprecated
    public static Link link() {
        return Impl.NO_LINK;
    }

    public static Link linkOut() {
        return impl.linkOut();
    }

    public static void linkIn(Link link) {
        impl.linkIn(link);
    }

    public static void attachTag(Tag tag) {
        impl.attachTag(tag);
    }

    public static void attachTag(String str, String str2) {
        impl.attachTag(str, str2);
    }

    public static void attachTag(String str, long j) {
        impl.attachTag(str, j);
    }

    public static void attachTag(String str, long j, long j2) {
        impl.attachTag(str, j, j2);
    }

    public static <T> void attachTag(String str, T t, StringFunction<? super T> stringFunction) {
        impl.attachTag(str, (String) t, (StringFunction<? super String>) stringFunction);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0040 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0032  */
    static {
        /*
            r0 = 0
            java.lang.String r1 = "io.perfmark.impl.SecretPerfMarkImpl$PerfMarkImpl"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch: java.lang.Throwable -> L9
            r2 = r0
            goto Lc
        L9:
            r1 = move-exception
            r2 = r1
            r1 = r0
        Lc:
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L2f
            java.lang.Class<io.perfmark.Impl> r5 = io.perfmark.Impl.class
            java.lang.Class r1 = r1.asSubclass(r5)     // Catch: java.lang.Throwable -> L2d
            java.lang.Class[] r5 = new java.lang.Class[r4]     // Catch: java.lang.Throwable -> L2d
            java.lang.Class<io.perfmark.Tag> r6 = io.perfmark.Tag.class
            r5[r3] = r6     // Catch: java.lang.Throwable -> L2d
            java.lang.reflect.Constructor r1 = r1.getConstructor(r5)     // Catch: java.lang.Throwable -> L2d
            java.lang.Object[] r5 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L2d
            io.perfmark.Tag r6 = io.perfmark.Impl.NO_TAG     // Catch: java.lang.Throwable -> L2d
            r5[r3] = r6     // Catch: java.lang.Throwable -> L2d
            java.lang.Object r1 = r1.newInstance(r5)     // Catch: java.lang.Throwable -> L2d
            io.perfmark.Impl r1 = (io.perfmark.Impl) r1     // Catch: java.lang.Throwable -> L2d
            goto L30
        L2d:
            r1 = move-exception
            r2 = r1
        L2f:
            r1 = r0
        L30:
            if (r1 == 0) goto L35
            io.perfmark.PerfMark.impl = r1
            goto L3e
        L35:
            io.perfmark.Impl r1 = new io.perfmark.Impl
            io.perfmark.Tag r5 = io.perfmark.Impl.NO_TAG
            r1.<init>(r5)
            io.perfmark.PerfMark.impl = r1
        L3e:
            if (r2 == 0) goto L99
            java.lang.String r1 = "io.perfmark.PerfMark.debug"
            boolean r1 = java.lang.Boolean.getBoolean(r1)     // Catch: java.lang.Throwable -> L99
            if (r1 == 0) goto L99
            java.lang.String r1 = "java.util.logging.Logger"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch: java.lang.Throwable -> L99
            java.lang.String r5 = "getLogger"
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch: java.lang.Throwable -> L99
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r6[r3] = r7     // Catch: java.lang.Throwable -> L99
            java.lang.reflect.Method r5 = r1.getMethod(r5, r6)     // Catch: java.lang.Throwable -> L99
            java.lang.Object[] r6 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L99
            java.lang.Class<io.perfmark.PerfMark> r7 = io.perfmark.PerfMark.class
            java.lang.String r7 = r7.getName()     // Catch: java.lang.Throwable -> L99
            r6[r3] = r7     // Catch: java.lang.Throwable -> L99
            java.lang.Object r5 = r5.invoke(r0, r6)     // Catch: java.lang.Throwable -> L99
            java.lang.String r6 = "java.util.logging.Level"
            java.lang.Class r6 = java.lang.Class.forName(r6)     // Catch: java.lang.Throwable -> L99
            java.lang.String r7 = "FINE"
            java.lang.reflect.Field r7 = r6.getField(r7)     // Catch: java.lang.Throwable -> L99
            java.lang.Object r0 = r7.get(r0)     // Catch: java.lang.Throwable -> L99
            java.lang.String r7 = "log"
            r8 = 3
            java.lang.Class[] r9 = new java.lang.Class[r8]     // Catch: java.lang.Throwable -> L99
            r9[r3] = r6     // Catch: java.lang.Throwable -> L99
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r9[r4] = r6     // Catch: java.lang.Throwable -> L99
            java.lang.Class<java.lang.Throwable> r6 = java.lang.Throwable.class
            r10 = 2
            r9[r10] = r6     // Catch: java.lang.Throwable -> L99
            java.lang.reflect.Method r1 = r1.getMethod(r7, r9)     // Catch: java.lang.Throwable -> L99
            java.lang.Object[] r6 = new java.lang.Object[r8]     // Catch: java.lang.Throwable -> L99
            r6[r3] = r0     // Catch: java.lang.Throwable -> L99
            java.lang.String r0 = "Error during PerfMark.<clinit>"
            r6[r4] = r0     // Catch: java.lang.Throwable -> L99
            r6[r10] = r2     // Catch: java.lang.Throwable -> L99
            r1.invoke(r5, r6)     // Catch: java.lang.Throwable -> L99
        L99:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.perfmark.PerfMark.<clinit>():void");
    }

    private PerfMark() {
    }
}
