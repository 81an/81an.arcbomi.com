package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.scheduling.DefaultScheduler;

/* compiled from: CoroutineContext.kt */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\b\u0010\u000b\u001a\u00020\fH\u0000\u001a8\u0010\r\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0014H\u0080\b¢\u0006\u0002\u0010\u0015\u001a4\u0010\u0016\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e2\u0006\u0010\u0017\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0014H\u0080\b¢\u0006\u0002\u0010\u0018\u001a\u0014\u0010\u0019\u001a\u00020\b*\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\bH\u0007\u001a\u0013\u0010\u001b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001c*\u00020\u001dH\u0080\u0010\u001a(\u0010\u001e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001c*\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\u0017\u001a\u00020\b2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0012H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0014\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u0001*\u00020\b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006 "}, d2 = {"COROUTINES_SCHEDULER_PROPERTY_NAME", "", "DEBUG_THREAD_NAME_SEPARATOR", "useCoroutinesScheduler", "", "getUseCoroutinesScheduler", "()Z", "coroutineName", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineName", "(Lkotlin/coroutines/CoroutineContext;)Ljava/lang/String;", "createDefaultDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "withContinuationContext", "T", "continuation", "Lkotlin/coroutines/Continuation;", "countOrElement", "", "block", "Lkotlin/Function0;", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withCoroutineContext", "context", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "newCoroutineContext", "Lkotlinx/coroutines/CoroutineScope;", "undispatchedCompletion", "Lkotlinx/coroutines/UndispatchedCoroutine;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "updateUndispatchedCompletion", "oldValue", "kotlinx-coroutines-core"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class CoroutineContextKt {
    public static final String COROUTINES_SCHEDULER_PROPERTY_NAME = "kotlinx.coroutines.scheduler";
    private static final String DEBUG_THREAD_NAME_SEPARATOR = " @";
    private static final boolean useCoroutinesScheduler;

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0027, code lost:
    
        if (r0.equals(kotlinx.coroutines.DebugKt.DEBUG_PROPERTY_VALUE_ON) != false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0030, code lost:
    
        if (r0.equals("") != false) goto L21;
     */
    static {
        /*
            java.lang.String r0 = "kotlinx.coroutines.scheduler"
            java.lang.String r0 = kotlinx.coroutines.internal.SystemPropsKt.systemProp(r0)
            if (r0 == 0) goto L55
            int r1 = r0.hashCode()
            if (r1 == 0) goto L2a
            r2 = 3551(0xddf, float:4.976E-42)
            if (r1 == r2) goto L21
            r2 = 109935(0x1ad6f, float:1.54052E-40)
            if (r1 != r2) goto L33
            java.lang.String r1 = "off"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L33
            r0 = 0
            goto L56
        L21:
            java.lang.String r1 = "on"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L33
            goto L55
        L2a:
            java.lang.String r1 = ""
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L33
            goto L55
        L33:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "System property 'kotlinx.coroutines.scheduler' has unrecognized value '"
            r1.append(r2)
            r1.append(r0)
            r0 = 39
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            throw r1
        L55:
            r0 = 1
        L56:
            kotlinx.coroutines.CoroutineContextKt.useCoroutinesScheduler = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.CoroutineContextKt.<clinit>():void");
    }

    public static final boolean getUseCoroutinesScheduler() {
        return useCoroutinesScheduler;
    }

    public static final CoroutineDispatcher createDefaultDispatcher() {
        return useCoroutinesScheduler ? DefaultScheduler.INSTANCE : CommonPool.INSTANCE;
    }

    public static final CoroutineContext newCoroutineContext(CoroutineScope coroutineScope, CoroutineContext coroutineContext) {
        CoroutineContext plus = coroutineScope.getCoroutineContext().plus(coroutineContext);
        CoroutineContext plus2 = DebugKt.getDEBUG() ? plus.plus(new CoroutineId(DebugKt.getCOROUTINE_ID().incrementAndGet())) : plus;
        return (plus == Dispatchers.getDefault() || plus.get(ContinuationInterceptor.INSTANCE) != null) ? plus2 : plus2.plus(Dispatchers.getDefault());
    }

    public static final <T> T withCoroutineContext(CoroutineContext coroutineContext, Object obj, Function0<? extends T> function0) {
        Object updateThreadContext = ThreadContextKt.updateThreadContext(coroutineContext, obj);
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            ThreadContextKt.restoreThreadContext(coroutineContext, updateThreadContext);
            InlineMarker.finallyEnd(1);
        }
    }

    public static final <T> T withContinuationContext(Continuation<?> continuation, Object obj, Function0<? extends T> function0) {
        UndispatchedCoroutine<?> undispatchedCoroutine;
        CoroutineContext context = continuation.getContext();
        Object updateThreadContext = ThreadContextKt.updateThreadContext(context, obj);
        if (updateThreadContext != ThreadContextKt.NO_THREAD_ELEMENTS) {
            undispatchedCoroutine = updateUndispatchedCompletion(continuation, context, updateThreadContext);
        } else {
            undispatchedCoroutine = null;
        }
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            if (undispatchedCoroutine == null || undispatchedCoroutine.clearThreadContext()) {
                ThreadContextKt.restoreThreadContext(context, updateThreadContext);
            }
            InlineMarker.finallyEnd(1);
        }
    }

    public static final UndispatchedCoroutine<?> updateUndispatchedCompletion(Continuation<?> continuation, CoroutineContext coroutineContext, Object obj) {
        if (!(continuation instanceof CoroutineStackFrame)) {
            return null;
        }
        if (!(coroutineContext.get(UndispatchedMarker.INSTANCE) != null)) {
            return null;
        }
        UndispatchedCoroutine<?> undispatchedCompletion = undispatchedCompletion((CoroutineStackFrame) continuation);
        if (undispatchedCompletion != null) {
            undispatchedCompletion.saveThreadContext(coroutineContext, obj);
        }
        return undispatchedCompletion;
    }

    public static final UndispatchedCoroutine<?> undispatchedCompletion(CoroutineStackFrame coroutineStackFrame) {
        while (!(coroutineStackFrame instanceof DispatchedCoroutine) && (coroutineStackFrame = coroutineStackFrame.getCallerFrame()) != null) {
            if (coroutineStackFrame instanceof UndispatchedCoroutine) {
                return (UndispatchedCoroutine) coroutineStackFrame;
            }
        }
        return null;
    }

    public static final String getCoroutineName(CoroutineContext coroutineContext) {
        CoroutineId coroutineId;
        String name;
        if (!DebugKt.getDEBUG() || (coroutineId = (CoroutineId) coroutineContext.get(CoroutineId.INSTANCE)) == null) {
            return null;
        }
        CoroutineName coroutineName = (CoroutineName) coroutineContext.get(CoroutineName.INSTANCE);
        String str = "coroutine";
        if (coroutineName != null && (name = coroutineName.getName()) != null) {
            str = name;
        }
        return str + '#' + coroutineId.getId();
    }
}
