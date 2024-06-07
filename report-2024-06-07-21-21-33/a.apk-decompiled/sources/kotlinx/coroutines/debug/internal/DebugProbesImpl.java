package kotlinx.coroutines.debug.internal;

import com.tekartik.sqflite.Constant;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.KotlinVersion;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.concurrent.ThreadsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.JobSupport;
import kotlinx.coroutines.debug.AgentPremain;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;

/* compiled from: DebugProbesImpl.kt */
@Metadata(d1 = {"\u0000Ò\u0001\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\bÀ\u0002\u0018\u00002\u00020\u0013:\u0002\u008f\u0001B\t\b\u0002¢\u0006\u0004\b\u0001\u0010\u0002J3\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\u0004\b\u0011\u0010\u0012J>\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f\"\b\b\u0000\u0010\u0014*\u00020\u00132\u001c\u0010\u0018\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0016\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00028\u00000\u0015H\u0082\b¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001b\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u001b\u0010\u000eJ\u0013\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u000f¢\u0006\u0004\b\u001d\u0010\u0012J)\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001f0\u000f2\u0006\u0010\u001e\u001a\u00020\u00102\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u000f¢\u0006\u0004\b!\u0010\"J5\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001f0\u000f2\u0006\u0010$\u001a\u00020#2\b\u0010&\u001a\u0004\u0018\u00010%2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u000fH\u0002¢\u0006\u0004\b'\u0010(J?\u0010.\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020)0-2\u0006\u0010*\u001a\u00020)2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u001f0+2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u000fH\u0002¢\u0006\u0004\b.\u0010/J3\u00101\u001a\u00020)2\u0006\u00100\u001a\u00020)2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u001f0+2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u000fH\u0002¢\u0006\u0004\b1\u00102J\u001d\u00105\u001a\u0010\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020\f\u0018\u000103H\u0002¢\u0006\u0004\b5\u00106J\u0015\u00109\u001a\u00020#2\u0006\u00108\u001a\u000207¢\u0006\u0004\b9\u0010:J\r\u0010;\u001a\u00020\f¢\u0006\u0004\b;\u0010\u0002J%\u0010=\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u001f0\u000fH\u0002¢\u0006\u0004\b=\u0010>J\u001b\u0010@\u001a\u00020\f2\n\u0010?\u001a\u0006\u0012\u0002\b\u00030\u0016H\u0002¢\u0006\u0004\b@\u0010AJ)\u0010D\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\u0004\b\u0000\u0010\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0000¢\u0006\u0004\bB\u0010CJ\u001b\u0010G\u001a\u00020\f2\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0000¢\u0006\u0004\bE\u0010FJ\u001b\u0010I\u001a\u00020\f2\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0000¢\u0006\u0004\bH\u0010FJ'\u0010L\u001a\b\u0012\u0004\u0012\u00020\u001f0\u000f\"\b\b\u0000\u0010\u0003*\u00020J2\u0006\u0010K\u001a\u00028\u0000H\u0002¢\u0006\u0004\bL\u0010MJ\u000f\u0010N\u001a\u00020\fH\u0002¢\u0006\u0004\bN\u0010\u0002J\u000f\u0010O\u001a\u00020\fH\u0002¢\u0006\u0004\bO\u0010\u0002J\r\u0010P\u001a\u00020\f¢\u0006\u0004\bP\u0010\u0002J\u001f\u0010R\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020Q2\u0006\u0010$\u001a\u00020#H\u0002¢\u0006\u0004\bR\u0010SJ#\u0010T\u001a\u00020\f2\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010$\u001a\u00020#H\u0002¢\u0006\u0004\bT\u0010UJ/\u0010T\u001a\u00020\f2\n\u0010?\u001a\u0006\u0012\u0002\b\u00030\u00162\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010$\u001a\u00020#H\u0002¢\u0006\u0004\bT\u0010VJ;\u0010^\u001a\u00020\f*\u0002072\u0012\u0010Y\u001a\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020X0W2\n\u0010\\\u001a\u00060Zj\u0002`[2\u0006\u0010]\u001a\u00020#H\u0002¢\u0006\u0004\b^\u0010_J\u0017\u0010`\u001a\u000204*\u0006\u0012\u0002\b\u00030\u0016H\u0002¢\u0006\u0004\b`\u0010aJ\u001d\u0010?\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0016*\u0006\u0012\u0002\b\u00030\u0004H\u0002¢\u0006\u0004\b?\u0010bJ\u001a\u0010?\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0016*\u00020QH\u0082\u0010¢\u0006\u0004\b?\u0010cJ\u0016\u0010d\u001a\u0004\u0018\u00010Q*\u00020QH\u0082\u0010¢\u0006\u0004\bd\u0010eJ\u001b\u0010f\u001a\u0004\u0018\u00010\u0006*\b\u0012\u0004\u0012\u00020\u001f0\u000fH\u0002¢\u0006\u0004\bf\u0010gR\u0014\u0010h\u001a\u00020#8\u0002X\u0082T¢\u0006\u0006\n\u0004\bh\u0010iR \u0010k\u001a\u000e\u0012\u0004\u0012\u00020Q\u0012\u0004\u0012\u00020X0j8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bk\u0010lR\u001e\u0010p\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00160m8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bn\u0010oR$\u0010q\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0016\u0012\u0004\u0012\u0002040j8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bq\u0010lR\u0014\u0010s\u001a\u00020r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bs\u0010tR\u0014\u0010v\u001a\u00020u8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bv\u0010wR\"\u0010x\u001a\u0010\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u00020\f\u0018\u0001038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bx\u0010yR\"\u0010z\u001a\u0002048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bz\u0010{\u001a\u0004\b|\u0010}\"\u0004\b~\u0010\u007fR\u0019\u0010\u0080\u0001\u001a\u00020)8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0080\u0001\u0010\u0081\u0001R\u0016\u0010\u0083\u0001\u001a\u0002048@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b\u0082\u0001\u0010}R&\u0010\u0084\u0001\u001a\u0002048\u0006@\u0006X\u0086\u000e¢\u0006\u0015\n\u0005\b\u0084\u0001\u0010{\u001a\u0005\b\u0085\u0001\u0010}\"\u0005\b\u0086\u0001\u0010\u007fR\u001b\u0010\u0087\u0001\u001a\u0004\u0018\u00010%8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0087\u0001\u0010\u0088\u0001R\"\u0010\u008c\u0001\u001a\u00020#*\u0002078BX\u0082\u0004¢\u0006\u000f\u0012\u0006\b\u008a\u0001\u0010\u008b\u0001\u001a\u0005\b\u0089\u0001\u0010:R\u001b\u0010\u008d\u0001\u001a\u000204*\u00020\u001f8BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u008d\u0001\u0010\u008e\u0001¨\u0006\u0090\u0001"}, d2 = {"Lkotlinx/coroutines/debug/internal/DebugProbesImpl;", "<init>", "()V", "T", "Lkotlin/coroutines/Continuation;", "completion", "Lkotlinx/coroutines/debug/internal/StackTraceFrame;", "frame", "createOwner", "(Lkotlin/coroutines/Continuation;Lkotlinx/coroutines/debug/internal/StackTraceFrame;)Lkotlin/coroutines/Continuation;", "Ljava/io/PrintStream;", "out", "", "dumpCoroutines", "(Ljava/io/PrintStream;)V", "", "Lkotlinx/coroutines/debug/internal/DebugCoroutineInfo;", "dumpCoroutinesInfo", "()Ljava/util/List;", "", "R", "Lkotlin/Function2;", "Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;", "Lkotlin/coroutines/CoroutineContext;", "create", "dumpCoroutinesInfoImpl", "(Lkotlin/jvm/functions/Function2;)Ljava/util/List;", "dumpCoroutinesSynchronized", "Lkotlinx/coroutines/debug/internal/DebuggerInfo;", "dumpDebuggerInfo", "info", "Ljava/lang/StackTraceElement;", "coroutineTrace", "enhanceStackTraceWithThreadDump", "(Lkotlinx/coroutines/debug/internal/DebugCoroutineInfo;Ljava/util/List;)Ljava/util/List;", "", "state", "Ljava/lang/Thread;", "thread", "enhanceStackTraceWithThreadDumpImpl", "(Ljava/lang/String;Ljava/lang/Thread;Ljava/util/List;)Ljava/util/List;", "", "indexOfResumeWith", "", "actualTrace", "Lkotlin/Pair;", "findContinuationStartIndex", "(I[Ljava/lang/StackTraceElement;Ljava/util/List;)Lkotlin/Pair;", "frameIndex", "findIndexOfFrame", "(I[Ljava/lang/StackTraceElement;Ljava/util/List;)I", "Lkotlin/Function1;", "", "getDynamicAttach", "()Lkotlin/jvm/functions/Function1;", "Lkotlinx/coroutines/Job;", "job", "hierarchyToString", "(Lkotlinx/coroutines/Job;)Ljava/lang/String;", "install", "frames", "printStackTrace", "(Ljava/io/PrintStream;Ljava/util/List;)V", "owner", "probeCoroutineCompleted", "(Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;)V", "probeCoroutineCreated$kotlinx_coroutines_core", "(Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "probeCoroutineCreated", "probeCoroutineResumed$kotlinx_coroutines_core", "(Lkotlin/coroutines/Continuation;)V", "probeCoroutineResumed", "probeCoroutineSuspended$kotlinx_coroutines_core", "probeCoroutineSuspended", "", "throwable", "sanitizeStackTrace", "(Ljava/lang/Throwable;)Ljava/util/List;", "startWeakRefCleanerThread", "stopWeakRefCleanerThread", "uninstall", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "updateRunningState", "(Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;Ljava/lang/String;)V", "updateState", "(Lkotlin/coroutines/Continuation;Ljava/lang/String;)V", "(Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;Lkotlin/coroutines/Continuation;Ljava/lang/String;)V", "", "Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;", "map", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "builder", "indent", "build", "(Lkotlinx/coroutines/Job;Ljava/util/Map;Ljava/lang/StringBuilder;Ljava/lang/String;)V", "isFinished", "(Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;)Z", "(Lkotlin/coroutines/Continuation;)Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;", "(Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;)Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;", "realCaller", "(Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;)Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "toStackTraceFrame", "(Ljava/util/List;)Lkotlinx/coroutines/debug/internal/StackTraceFrame;", "ARTIFICIAL_FRAME_MESSAGE", "Ljava/lang/String;", "Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap;", "callerInfoCache", "Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap;", "", "getCapturedCoroutines", "()Ljava/util/Set;", "capturedCoroutines", "capturedCoroutinesMap", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "coroutineStateLock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "Ljava/text/SimpleDateFormat;", "dateFormat", "Ljava/text/SimpleDateFormat;", "dynamicAttach", "Lkotlin/jvm/functions/Function1;", "enableCreationStackTraces", "Z", "getEnableCreationStackTraces", "()Z", "setEnableCreationStackTraces", "(Z)V", "installations", "I", "isInstalled$kotlinx_coroutines_core", "isInstalled", "sanitizeStackTraces", "getSanitizeStackTraces", "setSanitizeStackTraces", "weakRefCleanerThread", "Ljava/lang/Thread;", "getDebugString", "getDebugString$annotations", "(Lkotlinx/coroutines/Job;)V", "debugString", "isInternalMethod", "(Ljava/lang/StackTraceElement;)Z", "CoroutineOwner", "kotlinx-coroutines-core"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class DebugProbesImpl {
    private static final String ARTIFICIAL_FRAME_MESSAGE = "Coroutine creation stacktrace";
    public static final DebugProbesImpl INSTANCE;
    private static final ConcurrentWeakMap<CoroutineStackFrame, DebugCoroutineInfo> callerInfoCache;
    private static final ConcurrentWeakMap<CoroutineOwner<?>, Boolean> capturedCoroutinesMap;
    private static final ReentrantReadWriteLock coroutineStateLock;
    private static final SimpleDateFormat dateFormat;
    private static final /* synthetic */ SequenceNumberRefVolatile debugProbesImpl$SequenceNumberRefVolatile;
    private static final Function1<Boolean, Unit> dynamicAttach;
    private static boolean enableCreationStackTraces;
    private static volatile int installations;
    private static boolean sanitizeStackTraces;
    private static final /* synthetic */ AtomicLongFieldUpdater sequenceNumber$FU;
    private static Thread weakRefCleanerThread;

    private static /* synthetic */ void getDebugString$annotations(Job job) {
    }

    private DebugProbesImpl() {
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [kotlinx.coroutines.debug.internal.DebugProbesImpl$SequenceNumberRefVolatile] */
    static {
        DebugProbesImpl debugProbesImpl = new DebugProbesImpl();
        INSTANCE = debugProbesImpl;
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        capturedCoroutinesMap = new ConcurrentWeakMap<>(false, 1, null);
        debugProbesImpl$SequenceNumberRefVolatile = new Object(0L) { // from class: kotlinx.coroutines.debug.internal.DebugProbesImpl.SequenceNumberRefVolatile
            volatile long sequenceNumber;

            {
                this.sequenceNumber = r1;
            }
        };
        coroutineStateLock = new ReentrantReadWriteLock();
        sanitizeStackTraces = true;
        enableCreationStackTraces = true;
        dynamicAttach = debugProbesImpl.getDynamicAttach();
        callerInfoCache = new ConcurrentWeakMap<>(true);
        sequenceNumber$FU = AtomicLongFieldUpdater.newUpdater(SequenceNumberRefVolatile.class, "sequenceNumber");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<CoroutineOwner<?>> getCapturedCoroutines() {
        return capturedCoroutinesMap.keySet();
    }

    public final boolean isInstalled$kotlinx_coroutines_core() {
        return installations > 0;
    }

    public final boolean getSanitizeStackTraces() {
        return sanitizeStackTraces;
    }

    public final void setSanitizeStackTraces(boolean z) {
        sanitizeStackTraces = z;
    }

    public final boolean getEnableCreationStackTraces() {
        return enableCreationStackTraces;
    }

    public final void setEnableCreationStackTraces(boolean z) {
        enableCreationStackTraces = z;
    }

    private final Function1<Boolean, Unit> getDynamicAttach() {
        Object m416constructorimpl;
        Object newInstance;
        try {
            Result.Companion companion = Result.INSTANCE;
            DebugProbesImpl debugProbesImpl = this;
            newInstance = Class.forName("kotlinx.coroutines.debug.internal.ByteBuddyDynamicAttach").getConstructors()[0].newInstance(new Object[0]);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m416constructorimpl = Result.m416constructorimpl(ResultKt.createFailure(th));
        }
        if (newInstance != null) {
            m416constructorimpl = Result.m416constructorimpl((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(newInstance, 1));
            if (Result.m422isFailureimpl(m416constructorimpl)) {
                m416constructorimpl = null;
            }
            return (Function1) m416constructorimpl;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Function1<kotlin.Boolean, kotlin.Unit>");
    }

    public final void install() {
        ReentrantReadWriteLock reentrantReadWriteLock = coroutineStateLock;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            installations++;
            if (installations > 1) {
                return;
            }
            debugProbesImpl.startWeakRefCleanerThread();
            if (AgentPremain.INSTANCE.isInstalledStatically()) {
                while (i < readHoldCount) {
                    readLock.lock();
                    i++;
                }
                writeLock.unlock();
                return;
            }
            Function1<Boolean, Unit> function1 = dynamicAttach;
            if (function1 != null) {
                function1.invoke(true);
            }
            Unit unit = Unit.INSTANCE;
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
        } finally {
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
        }
    }

    public final void uninstall() {
        ReentrantReadWriteLock reentrantReadWriteLock = coroutineStateLock;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            if (!debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                throw new IllegalStateException("Agent was not installed".toString());
            }
            installations--;
            if (installations != 0) {
                return;
            }
            debugProbesImpl.stopWeakRefCleanerThread();
            capturedCoroutinesMap.clear();
            callerInfoCache.clear();
            if (AgentPremain.INSTANCE.isInstalledStatically()) {
                while (i < readHoldCount) {
                    readLock.lock();
                    i++;
                }
                writeLock.unlock();
                return;
            }
            Function1<Boolean, Unit> function1 = dynamicAttach;
            if (function1 != null) {
                function1.invoke(false);
            }
            Unit unit = Unit.INSTANCE;
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
        } finally {
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
        }
    }

    private final void startWeakRefCleanerThread() {
        Thread thread;
        thread = ThreadsKt.thread((r12 & 1) != 0, (r12 & 2) != 0 ? false : true, (r12 & 4) != 0 ? null : null, (r12 & 8) != 0 ? null : "Coroutines Debugger Cleaner", (r12 & 16) != 0 ? -1 : 0, new Function0<Unit>() { // from class: kotlinx.coroutines.debug.internal.DebugProbesImpl$startWeakRefCleanerThread$1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                ConcurrentWeakMap concurrentWeakMap;
                concurrentWeakMap = DebugProbesImpl.callerInfoCache;
                concurrentWeakMap.runWeakRefQueueCleaningLoopUntilInterrupted();
            }
        });
        weakRefCleanerThread = thread;
    }

    private final void stopWeakRefCleanerThread() {
        Thread thread = weakRefCleanerThread;
        if (thread != null) {
            thread.interrupt();
        }
        weakRefCleanerThread = null;
    }

    public final String hierarchyToString(Job job) {
        ReentrantReadWriteLock reentrantReadWriteLock = coroutineStateLock;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            if (!debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                throw new IllegalStateException("Debug probes are not installed".toString());
            }
            Set<CoroutineOwner<?>> capturedCoroutines = debugProbesImpl.getCapturedCoroutines();
            ArrayList arrayList = new ArrayList();
            for (Object obj : capturedCoroutines) {
                if (((CoroutineOwner) obj).delegate.get$context().get(Job.INSTANCE) != null) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = arrayList;
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList2, 10)), 16));
            for (Object obj2 : arrayList2) {
                linkedHashMap.put(JobKt.getJob(((CoroutineOwner) obj2).delegate.get$context()), ((CoroutineOwner) obj2).info);
            }
            StringBuilder sb = new StringBuilder();
            INSTANCE.build(job, linkedHashMap, sb, "");
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
            return sb2;
        } finally {
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
        }
    }

    private final void build(Job job, Map<Job, DebugCoroutineInfo> map, StringBuilder sb, String str) {
        DebugCoroutineInfo debugCoroutineInfo = map.get(job);
        if (debugCoroutineInfo == null) {
            if (!(job instanceof ScopeCoroutine)) {
                sb.append(str + getDebugString(job) + '\n');
                str = Intrinsics.stringPlus(str, "\t");
            }
        } else {
            StackTraceElement stackTraceElement = (StackTraceElement) CollectionsKt.firstOrNull((List) debugCoroutineInfo.lastObservedStackTrace());
            sb.append(str + getDebugString(job) + ", continuation is " + debugCoroutineInfo.get_state() + " at line " + stackTraceElement + '\n');
            str = Intrinsics.stringPlus(str, "\t");
        }
        Iterator<Job> it = job.getChildren().iterator();
        while (it.hasNext()) {
            build(it.next(), map, sb, str);
        }
    }

    private final String getDebugString(Job job) {
        return job instanceof JobSupport ? ((JobSupport) job).toDebugString() : job.toString();
    }

    private final <R> List<R> dumpCoroutinesInfoImpl(Function2<? super CoroutineOwner<?>, ? super CoroutineContext, ? extends R> create) {
        CoroutineContext context;
        ReentrantReadWriteLock reentrantReadWriteLock = coroutineStateLock;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            if (!debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                throw new IllegalStateException("Debug probes are not installed".toString());
            }
            List<CoroutineOwner> sortedWith = CollectionsKt.sortedWith(debugProbesImpl.getCapturedCoroutines(), new DebugProbesImpl$dumpCoroutinesInfoImpl$lambda14$$inlined$sortedBy$1());
            ArrayList arrayList = new ArrayList();
            for (CoroutineOwner coroutineOwner : sortedWith) {
                R r = null;
                if (!INSTANCE.isFinished(coroutineOwner) && (context = coroutineOwner.info.getContext()) != null) {
                    r = create.invoke(coroutineOwner, context);
                }
                if (r != null) {
                    arrayList.add(r);
                }
            }
            return arrayList;
        } finally {
            InlineMarker.finallyStart(1);
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
            InlineMarker.finallyEnd(1);
        }
    }

    public final void dumpCoroutines(PrintStream out) {
        synchronized (out) {
            INSTANCE.dumpCoroutinesSynchronized(out);
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isFinished(CoroutineOwner<?> coroutineOwner) {
        CoroutineContext context = coroutineOwner.info.getContext();
        Job job = context == null ? null : (Job) context.get(Job.INSTANCE);
        if (job == null || !job.isCompleted()) {
            return false;
        }
        capturedCoroutinesMap.remove(coroutineOwner);
        return true;
    }

    private final void dumpCoroutinesSynchronized(PrintStream out) {
        String str;
        ReentrantReadWriteLock reentrantReadWriteLock = coroutineStateLock;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            if (!debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                throw new IllegalStateException("Debug probes are not installed".toString());
            }
            out.print(Intrinsics.stringPlus("Coroutines dump ", dateFormat.format(Long.valueOf(System.currentTimeMillis()))));
            for (CoroutineOwner coroutineOwner : SequencesKt.sortedWith(SequencesKt.filter(CollectionsKt.asSequence(debugProbesImpl.getCapturedCoroutines()), new Function1<CoroutineOwner<?>, Boolean>() { // from class: kotlinx.coroutines.debug.internal.DebugProbesImpl$dumpCoroutinesSynchronized$1$2
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(DebugProbesImpl.CoroutineOwner<?> coroutineOwner2) {
                    return Boolean.valueOf(!DebugProbesImpl.INSTANCE.isFinished(coroutineOwner2));
                }
            }), new Comparator() { // from class: kotlinx.coroutines.debug.internal.DebugProbesImpl$dumpCoroutinesSynchronized$lambda-21$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Long.valueOf(((DebugProbesImpl.CoroutineOwner) t).info.sequenceNumber), Long.valueOf(((DebugProbesImpl.CoroutineOwner) t2).info.sequenceNumber));
                }
            })) {
                DebugCoroutineInfo debugCoroutineInfo = coroutineOwner.info;
                List<StackTraceElement> lastObservedStackTrace = debugCoroutineInfo.lastObservedStackTrace();
                DebugProbesImpl debugProbesImpl2 = INSTANCE;
                List<StackTraceElement> enhanceStackTraceWithThreadDumpImpl = debugProbesImpl2.enhanceStackTraceWithThreadDumpImpl(debugCoroutineInfo.get_state(), debugCoroutineInfo.lastObservedThread, lastObservedStackTrace);
                if (Intrinsics.areEqual(debugCoroutineInfo.get_state(), DebugCoroutineInfoImplKt.RUNNING) && enhanceStackTraceWithThreadDumpImpl == lastObservedStackTrace) {
                    str = Intrinsics.stringPlus(debugCoroutineInfo.get_state(), " (Last suspension stacktrace, not an actual stacktrace)");
                } else {
                    str = debugCoroutineInfo.get_state();
                }
                out.print("\n\nCoroutine " + coroutineOwner.delegate + ", state: " + str);
                if (lastObservedStackTrace.isEmpty()) {
                    out.print(Intrinsics.stringPlus("\n\tat ", StackTraceRecoveryKt.artificialFrame(ARTIFICIAL_FRAME_MESSAGE)));
                    debugProbesImpl2.printStackTrace(out, debugCoroutineInfo.getCreationStackTrace());
                } else {
                    debugProbesImpl2.printStackTrace(out, enhanceStackTraceWithThreadDumpImpl);
                }
            }
            Unit unit = Unit.INSTANCE;
        } finally {
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
        }
    }

    private final void printStackTrace(PrintStream out, List<StackTraceElement> frames) {
        Iterator<T> it = frames.iterator();
        while (it.hasNext()) {
            out.print(Intrinsics.stringPlus("\n\tat ", (StackTraceElement) it.next()));
        }
    }

    public final List<StackTraceElement> enhanceStackTraceWithThreadDump(DebugCoroutineInfo info, List<StackTraceElement> coroutineTrace) {
        return enhanceStackTraceWithThreadDumpImpl(info.getState(), info.getLastObservedThread(), coroutineTrace);
    }

    private final List<StackTraceElement> enhanceStackTraceWithThreadDumpImpl(String state, Thread thread, List<StackTraceElement> coroutineTrace) {
        Object m416constructorimpl;
        if (!Intrinsics.areEqual(state, DebugCoroutineInfoImplKt.RUNNING) || thread == null) {
            return coroutineTrace;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            DebugProbesImpl debugProbesImpl = this;
            m416constructorimpl = Result.m416constructorimpl(thread.getStackTrace());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m416constructorimpl = Result.m416constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m422isFailureimpl(m416constructorimpl)) {
            m416constructorimpl = null;
        }
        StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) m416constructorimpl;
        if (stackTraceElementArr == null) {
            return coroutineTrace;
        }
        int length = stackTraceElementArr.length;
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                i2 = -1;
                break;
            }
            StackTraceElement stackTraceElement = stackTraceElementArr[i2];
            if (Intrinsics.areEqual(stackTraceElement.getClassName(), "kotlin.coroutines.jvm.internal.BaseContinuationImpl") && Intrinsics.areEqual(stackTraceElement.getMethodName(), "resumeWith") && Intrinsics.areEqual(stackTraceElement.getFileName(), "ContinuationImpl.kt")) {
                break;
            }
            i2++;
        }
        Pair<Integer, Integer> findContinuationStartIndex = findContinuationStartIndex(i2, stackTraceElementArr, coroutineTrace);
        int intValue = findContinuationStartIndex.component1().intValue();
        int intValue2 = findContinuationStartIndex.component2().intValue();
        if (intValue == -1) {
            return coroutineTrace;
        }
        ArrayList arrayList = new ArrayList((((coroutineTrace.size() + i2) - intValue) - 1) - intValue2);
        int i3 = i2 - intValue2;
        if (i3 > 0) {
            while (true) {
                int i4 = i + 1;
                arrayList.add(stackTraceElementArr[i]);
                if (i4 >= i3) {
                    break;
                }
                i = i4;
            }
        }
        int i5 = intValue + 1;
        int size = coroutineTrace.size();
        if (i5 < size) {
            while (true) {
                int i6 = i5 + 1;
                arrayList.add(coroutineTrace.get(i5));
                if (i6 >= size) {
                    break;
                }
                i5 = i6;
            }
        }
        return arrayList;
    }

    private final Pair<Integer, Integer> findContinuationStartIndex(int indexOfResumeWith, StackTraceElement[] actualTrace, List<StackTraceElement> coroutineTrace) {
        for (int i = 0; i < 3; i++) {
            int findIndexOfFrame = INSTANCE.findIndexOfFrame((indexOfResumeWith - 1) - i, actualTrace, coroutineTrace);
            if (findIndexOfFrame != -1) {
                return TuplesKt.to(Integer.valueOf(findIndexOfFrame), Integer.valueOf(i));
            }
        }
        return TuplesKt.to(-1, 0);
    }

    private final int findIndexOfFrame(int frameIndex, StackTraceElement[] actualTrace, List<StackTraceElement> coroutineTrace) {
        StackTraceElement stackTraceElement = (StackTraceElement) ArraysKt.getOrNull(actualTrace, frameIndex);
        if (stackTraceElement == null) {
            return -1;
        }
        int i = 0;
        for (StackTraceElement stackTraceElement2 : coroutineTrace) {
            if (Intrinsics.areEqual(stackTraceElement2.getFileName(), stackTraceElement.getFileName()) && Intrinsics.areEqual(stackTraceElement2.getClassName(), stackTraceElement.getClassName()) && Intrinsics.areEqual(stackTraceElement2.getMethodName(), stackTraceElement.getMethodName())) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public final void probeCoroutineResumed$kotlinx_coroutines_core(Continuation<?> frame) {
        updateState(frame, DebugCoroutineInfoImplKt.RUNNING);
    }

    public final void probeCoroutineSuspended$kotlinx_coroutines_core(Continuation<?> frame) {
        updateState(frame, DebugCoroutineInfoImplKt.SUSPENDED);
    }

    private final void updateState(Continuation<?> frame, String state) {
        if (isInstalled$kotlinx_coroutines_core()) {
            if (Intrinsics.areEqual(state, DebugCoroutineInfoImplKt.RUNNING) && KotlinVersion.CURRENT.isAtLeast(1, 3, 30)) {
                CoroutineStackFrame coroutineStackFrame = frame instanceof CoroutineStackFrame ? (CoroutineStackFrame) frame : null;
                if (coroutineStackFrame == null) {
                    return;
                }
                updateRunningState(coroutineStackFrame, state);
                return;
            }
            CoroutineOwner<?> owner = owner(frame);
            if (owner == null) {
                return;
            }
            updateState(owner, frame, state);
        }
    }

    private final void updateRunningState(CoroutineStackFrame frame, String state) {
        ReentrantReadWriteLock.ReadLock readLock = coroutineStateLock.readLock();
        readLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            if (debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                ConcurrentWeakMap<CoroutineStackFrame, DebugCoroutineInfo> concurrentWeakMap = callerInfoCache;
                DebugCoroutineInfo remove = concurrentWeakMap.remove(frame);
                if (remove == null) {
                    CoroutineOwner<?> owner = debugProbesImpl.owner(frame);
                    if (owner == null) {
                        return;
                    }
                    remove = owner.info;
                    CoroutineStackFrame lastObservedFrame$kotlinx_coroutines_core = remove.getLastObservedFrame$kotlinx_coroutines_core();
                    CoroutineStackFrame realCaller = lastObservedFrame$kotlinx_coroutines_core == null ? null : debugProbesImpl.realCaller(lastObservedFrame$kotlinx_coroutines_core);
                    if (realCaller != null) {
                        concurrentWeakMap.remove(realCaller);
                    }
                }
                remove.updateState$kotlinx_coroutines_core(state, (Continuation) frame);
                CoroutineStackFrame realCaller2 = debugProbesImpl.realCaller(frame);
                if (realCaller2 == null) {
                    return;
                }
                concurrentWeakMap.put(realCaller2, remove);
                Unit unit = Unit.INSTANCE;
            }
        } finally {
            readLock.unlock();
        }
    }

    private final CoroutineStackFrame realCaller(CoroutineStackFrame coroutineStackFrame) {
        do {
            coroutineStackFrame = coroutineStackFrame.getCallerFrame();
            if (coroutineStackFrame == null) {
                return null;
            }
        } while (coroutineStackFrame.getStackTraceElement() == null);
        return coroutineStackFrame;
    }

    private final void updateState(CoroutineOwner<?> owner, Continuation<?> frame, String state) {
        ReentrantReadWriteLock.ReadLock readLock = coroutineStateLock.readLock();
        readLock.lock();
        try {
            if (INSTANCE.isInstalled$kotlinx_coroutines_core()) {
                owner.info.updateState$kotlinx_coroutines_core(state, frame);
                Unit unit = Unit.INSTANCE;
            }
        } finally {
            readLock.unlock();
        }
    }

    private final CoroutineOwner<?> owner(Continuation<?> continuation) {
        CoroutineStackFrame coroutineStackFrame = continuation instanceof CoroutineStackFrame ? (CoroutineStackFrame) continuation : null;
        if (coroutineStackFrame == null) {
            return null;
        }
        return owner(coroutineStackFrame);
    }

    private final CoroutineOwner<?> owner(CoroutineStackFrame coroutineStackFrame) {
        while (!(coroutineStackFrame instanceof CoroutineOwner)) {
            coroutineStackFrame = coroutineStackFrame.getCallerFrame();
            if (coroutineStackFrame == null) {
                return null;
            }
        }
        return (CoroutineOwner) coroutineStackFrame;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T> Continuation<T> probeCoroutineCreated$kotlinx_coroutines_core(Continuation<? super T> completion) {
        StackTraceFrame stackTraceFrame;
        if (!isInstalled$kotlinx_coroutines_core() || owner(completion) != null) {
            return completion;
        }
        if (enableCreationStackTraces) {
            stackTraceFrame = toStackTraceFrame(sanitizeStackTrace(new Exception()));
        } else {
            stackTraceFrame = null;
        }
        return createOwner(completion, stackTraceFrame);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final <T> Continuation<T> createOwner(Continuation<? super T> completion, StackTraceFrame frame) {
        if (!isInstalled$kotlinx_coroutines_core()) {
            return completion;
        }
        CoroutineOwner<?> coroutineOwner = new CoroutineOwner<>(completion, new DebugCoroutineInfo(completion.get$context(), frame, sequenceNumber$FU.incrementAndGet(debugProbesImpl$SequenceNumberRefVolatile)), frame);
        ConcurrentWeakMap<CoroutineOwner<?>, Boolean> concurrentWeakMap = capturedCoroutinesMap;
        concurrentWeakMap.put(coroutineOwner, true);
        if (!isInstalled$kotlinx_coroutines_core()) {
            concurrentWeakMap.clear();
        }
        return coroutineOwner;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void probeCoroutineCompleted(CoroutineOwner<?> owner) {
        capturedCoroutinesMap.remove(owner);
        CoroutineStackFrame lastObservedFrame$kotlinx_coroutines_core = owner.info.getLastObservedFrame$kotlinx_coroutines_core();
        CoroutineStackFrame realCaller = lastObservedFrame$kotlinx_coroutines_core == null ? null : realCaller(lastObservedFrame$kotlinx_coroutines_core);
        if (realCaller == null) {
            return;
        }
        callerInfoCache.remove(realCaller);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DebugProbesImpl.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B%\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\n\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u001e\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0015H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u0016\u0010\t\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\rX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00028\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"Lkotlinx/coroutines/debug/internal/DebugProbesImpl$CoroutineOwner;", "T", "Lkotlin/coroutines/Continuation;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "delegate", "info", "Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;", "frame", "(Lkotlin/coroutines/Continuation;Lkotlinx/coroutines/debug/internal/DebugCoroutineInfoImpl;Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;)V", "callerFrame", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "getStackTraceElement", "Ljava/lang/StackTraceElement;", "resumeWith", "", Constant.PARAM_RESULT, "Lkotlin/Result;", "(Ljava/lang/Object;)V", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class CoroutineOwner<T> implements Continuation<T>, CoroutineStackFrame {
        public final Continuation<T> delegate;
        private final CoroutineStackFrame frame;
        public final DebugCoroutineInfo info;

        @Override // kotlin.coroutines.Continuation
        /* renamed from: getContext */
        public CoroutineContext get$context() {
            return this.delegate.get$context();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CoroutineOwner(Continuation<? super T> continuation, DebugCoroutineInfo debugCoroutineInfo, CoroutineStackFrame coroutineStackFrame) {
            this.delegate = continuation;
            this.info = debugCoroutineInfo;
            this.frame = coroutineStackFrame;
        }

        @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
        public CoroutineStackFrame getCallerFrame() {
            CoroutineStackFrame coroutineStackFrame = this.frame;
            if (coroutineStackFrame == null) {
                return null;
            }
            return coroutineStackFrame.getCallerFrame();
        }

        @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
        public StackTraceElement getStackTraceElement() {
            CoroutineStackFrame coroutineStackFrame = this.frame;
            if (coroutineStackFrame == null) {
                return null;
            }
            return coroutineStackFrame.getStackTraceElement();
        }

        @Override // kotlin.coroutines.Continuation
        public void resumeWith(Object result) {
            DebugProbesImpl.INSTANCE.probeCoroutineCompleted(this);
            this.delegate.resumeWith(result);
        }

        public String toString() {
            return this.delegate.toString();
        }
    }

    private final <T extends Throwable> List<StackTraceElement> sanitizeStackTrace(T throwable) {
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        int length = stackTrace.length;
        int i = -1;
        int length2 = stackTrace.length - 1;
        while (true) {
            if (length2 < 0) {
                break;
            }
            if (Intrinsics.areEqual(stackTrace[length2].getClassName(), "kotlin.coroutines.jvm.internal.DebugProbesKt")) {
                i = length2;
                break;
            }
            length2--;
        }
        if (!sanitizeStackTraces) {
            int i2 = length - i;
            ArrayList arrayList = new ArrayList(i2);
            int i3 = 0;
            while (i3 < i2) {
                arrayList.add(i3 == 0 ? StackTraceRecoveryKt.artificialFrame(ARTIFICIAL_FRAME_MESSAGE) : stackTrace[i3 + i]);
                i3++;
            }
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList((length - i) + 1);
        ArrayList arrayList3 = arrayList2;
        arrayList3.add(StackTraceRecoveryKt.artificialFrame(ARTIFICIAL_FRAME_MESSAGE));
        while (true) {
            i++;
            while (i < length) {
                if (isInternalMethod(stackTrace[i])) {
                    arrayList3.add(stackTrace[i]);
                    int i4 = i + 1;
                    while (i4 < length && isInternalMethod(stackTrace[i4])) {
                        i4++;
                    }
                    int i5 = i4 - 1;
                    int i6 = i5;
                    while (i6 > i && stackTrace[i6].getFileName() == null) {
                        i6--;
                    }
                    if (i6 > i && i6 < i5) {
                        arrayList3.add(stackTrace[i6]);
                    }
                    arrayList3.add(stackTrace[i5]);
                    i = i4;
                }
            }
            return arrayList2;
            arrayList3.add(stackTrace[i]);
        }
    }

    private final boolean isInternalMethod(StackTraceElement stackTraceElement) {
        return StringsKt.startsWith$default(stackTraceElement.getClassName(), "kotlinx.coroutines", false, 2, (Object) null);
    }

    public final List<DebugCoroutineInfo> dumpCoroutinesInfo() {
        CoroutineContext context;
        ReentrantReadWriteLock reentrantReadWriteLock = coroutineStateLock;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            if (!debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                throw new IllegalStateException("Debug probes are not installed".toString());
            }
            List<CoroutineOwner> sortedWith = CollectionsKt.sortedWith(debugProbesImpl.getCapturedCoroutines(), new DebugProbesImpl$dumpCoroutinesInfoImpl$lambda14$$inlined$sortedBy$1());
            ArrayList arrayList = new ArrayList();
            for (CoroutineOwner coroutineOwner : sortedWith) {
                DebugCoroutineInfo debugCoroutineInfo = null;
                if (!INSTANCE.isFinished(coroutineOwner) && (context = coroutineOwner.info.getContext()) != null) {
                    debugCoroutineInfo = new DebugCoroutineInfo(coroutineOwner.info, context);
                }
                if (debugCoroutineInfo != null) {
                    arrayList.add(debugCoroutineInfo);
                }
            }
            return arrayList;
        } finally {
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
        }
    }

    public final List<DebuggerInfo> dumpDebuggerInfo() {
        CoroutineContext context;
        ReentrantReadWriteLock reentrantReadWriteLock = coroutineStateLock;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            DebugProbesImpl debugProbesImpl = INSTANCE;
            if (!debugProbesImpl.isInstalled$kotlinx_coroutines_core()) {
                throw new IllegalStateException("Debug probes are not installed".toString());
            }
            List<CoroutineOwner> sortedWith = CollectionsKt.sortedWith(debugProbesImpl.getCapturedCoroutines(), new DebugProbesImpl$dumpCoroutinesInfoImpl$lambda14$$inlined$sortedBy$1());
            ArrayList arrayList = new ArrayList();
            for (CoroutineOwner coroutineOwner : sortedWith) {
                DebuggerInfo debuggerInfo = null;
                if (!INSTANCE.isFinished(coroutineOwner) && (context = coroutineOwner.info.getContext()) != null) {
                    debuggerInfo = new DebuggerInfo(coroutineOwner.info, context);
                }
                if (debuggerInfo != null) {
                    arrayList.add(debuggerInfo);
                }
            }
            return arrayList;
        } finally {
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
        }
    }

    private final StackTraceFrame toStackTraceFrame(List<StackTraceElement> list) {
        StackTraceFrame stackTraceFrame = null;
        if (!list.isEmpty()) {
            ListIterator<StackTraceElement> listIterator = list.listIterator(list.size());
            while (listIterator.hasPrevious()) {
                stackTraceFrame = new StackTraceFrame(stackTraceFrame, listIterator.previous());
            }
        }
        return stackTraceFrame;
    }
}
