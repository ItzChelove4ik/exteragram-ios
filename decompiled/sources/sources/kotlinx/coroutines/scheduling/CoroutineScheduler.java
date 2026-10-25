package kotlinx.coroutines.scheduling;

import androidx.core.os.ExecutorCompat$HandlerExecutor$$ExternalSyntheticBUOutline0;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.io.Closeable;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt__ArraysKt$$ExternalSyntheticBUOutline0;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.AbstractTimeSourceKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.internal.ResizableAtomicArray;
import kotlinx.coroutines.internal.Symbol;
import okhttp3.internal.url._UrlKt;
import okio.SegmentedByteString$$ExternalSyntheticBUOutline0;
import okio.Utf8$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 Q2\u00020\u00012\u00020\u0002:\u0003QRSB+\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\fH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0012\u001a\b\u0018\u00010\u0011R\u00020\u0000H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0015\u001a\u00020\u00032\n\u0010\u0014\u001a\u00060\u0011R\u00020\u0000H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u0019\u0010\u001c\u001a\u00020\u000e2\b\b\u0002\u0010\u001b\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001e\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010 \u001a\u00020\u0003H\u0002¢\u0006\u0004\b \u0010!J+\u0010#\u001a\u0004\u0018\u00010\f*\b\u0018\u00010\u0011R\u00020\u00002\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\u000eH\u0002¢\u0006\u0004\b#\u0010$J\u0015\u0010%\u001a\b\u0018\u00010\u0011R\u00020\u0000H\u0002¢\u0006\u0004\b%\u0010\u0013J)\u0010(\u001a\u00020\u00182\n\u0010\u0014\u001a\u00060\u0011R\u00020\u00002\u0006\u0010&\u001a\u00020\u00032\u0006\u0010'\u001a\u00020\u0003¢\u0006\u0004\b(\u0010)J\u0019\u0010*\u001a\u00020\u000e2\n\u0010\u0014\u001a\u00060\u0011R\u00020\u0000¢\u0006\u0004\b*\u0010+J\u001b\u0010/\u001a\u00020\u00182\n\u0010.\u001a\u00060,j\u0002`-H\u0016¢\u0006\u0004\b/\u00100J\u000f\u00101\u001a\u00020\u0018H\u0016¢\u0006\u0004\b1\u00102J\u0015\u00104\u001a\u00020\u00182\u0006\u00103\u001a\u00020\u0006¢\u0006\u0004\b4\u0010\u001aJ1\u00108\u001a\u00020\u00182\n\u00105\u001a\u00060,j\u0002`-2\f\b\u0002\u00107\u001a\u00060\u000ej\u0002`62\b\b\u0002\u0010\"\u001a\u00020\u000e¢\u0006\u0004\b8\u00109J%\u0010:\u001a\u00020\f2\n\u00105\u001a\u00060,j\u0002`-2\n\u00107\u001a\u00060\u000ej\u0002`6¢\u0006\u0004\b:\u0010;J\r\u0010<\u001a\u00020\u0018¢\u0006\u0004\b<\u00102J\u000f\u0010=\u001a\u00020\bH\u0016¢\u0006\u0004\b=\u0010>J\u0015\u0010?\u001a\u00020\u00182\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b?\u0010@R\u0014\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010AR\u0014\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010AR\u0014\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010BR\u0014\u0010\t\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\t\u0010CR\u0014\u0010E\u001a\u00020D8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\bE\u0010FR\u0014\u0010G\u001a\u00020D8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\bG\u0010FR\u001e\u0010I\u001a\f\u0012\b\u0012\u00060\u0011R\u00020\u00000H8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\bI\u0010JR\u0011\u0010K\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\bK\u0010\u001fR\u000b\u0010M\u001a\u00020L8\u0002X\u0082\u0004R\u000b\u0010N\u001a\u00020L8\u0002X\u0082\u0004R\u000b\u0010P\u001a\u00020O8\u0002X\u0082\u0004¨\u0006T"}, d2 = {"Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "Ljava/util/concurrent/Executor;", "Ljava/io/Closeable;", _UrlKt.FRAGMENT_ENCODE_SET, "corePoolSize", "maxPoolSize", _UrlKt.FRAGMENT_ENCODE_SET, "idleWorkerKeepAliveNs", _UrlKt.FRAGMENT_ENCODE_SET, "schedulerName", "<init>", "(IIJLjava/lang/String;)V", "Lkotlinx/coroutines/scheduling/Task;", "task", _UrlKt.FRAGMENT_ENCODE_SET, "addToGlobalQueue", "(Lkotlinx/coroutines/scheduling/Task;)Z", "Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;", "parkedWorkersStackPop", "()Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;", "worker", "parkedWorkersStackNextIndex", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;)I", "stateSnapshot", _UrlKt.FRAGMENT_ENCODE_SET, "signalBlockingWork", "(J)V", "state", "tryCreateWorker", "(J)Z", "tryUnpark", "()Z", "createNewWorker", "()I", "fair", "submitToLocalQueue", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;Lkotlinx/coroutines/scheduling/Task;Z)Lkotlinx/coroutines/scheduling/Task;", "currentWorker", "oldIndex", "newIndex", "parkedWorkersStackTopUpdate", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;II)V", "parkedWorkersStackPush", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;)Z", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "command", "execute", "(Ljava/lang/Runnable;)V", "close", "()V", "timeout", "shutdown", "block", "Lkotlinx/coroutines/scheduling/TaskContext;", "taskContext", "dispatch", "(Ljava/lang/Runnable;ZZ)V", "createTask", "(Ljava/lang/Runnable;Z)Lkotlinx/coroutines/scheduling/Task;", "signalCpuWork", "toString", "()Ljava/lang/String;", "runSafely", "(Lkotlinx/coroutines/scheduling/Task;)V", "I", "J", "Ljava/lang/String;", "Lkotlinx/coroutines/scheduling/GlobalQueue;", "globalCpuQueue", "Lkotlinx/coroutines/scheduling/GlobalQueue;", "globalBlockingQueue", "Lkotlinx/coroutines/internal/ResizableAtomicArray;", "workers", "Lkotlinx/coroutines/internal/ResizableAtomicArray;", "isTerminated", "Lkotlinx/atomicfu/AtomicLong;", "parkedWorkersStack", "controlState", "Lkotlinx/atomicfu/AtomicBoolean;", "_isTerminated", "Companion", "Worker", "WorkerState", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCoroutineScheduler.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CoroutineScheduler.kt\nkotlinx/coroutines/scheduling/CoroutineScheduler\n+ 2 Tasks.kt\nkotlinx/coroutines/scheduling/TasksKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Synchronized.common.kt\nkotlinx/coroutines/internal/Synchronized_commonKt\n+ 5 Synchronized.kt\nkotlinx/coroutines/internal/SynchronizedKt\n+ 6 CoroutineScheduler.kt\nkotlinx/coroutines/scheduling/CoroutineScheduler$Worker\n*L\n1#1,1011:1\n286#1:1014\n284#1:1015\n284#1:1016\n286#1:1017\n281#1:1020\n282#1,5:1021\n292#1:1027\n284#1:1028\n285#1:1029\n284#1:1032\n285#1:1033\n281#1:1034\n289#1:1035\n284#1:1036\n284#1:1039\n285#1:1040\n286#1:1041\n77#2:1012\n77#2:1026\n77#2:1037\n1#3:1013\n26#4:1018\n26#4:1030\n16#5:1019\n16#5:1031\n619#6:1038\n*S KotlinDebug\n*F\n+ 1 CoroutineScheduler.kt\nkotlinx/coroutines/scheduling/CoroutineScheduler\n*L\n282#1:1014\n289#1:1015\n290#1:1016\n299#1:1017\n348#1:1020\n377#1:1021,5\n400#1:1027\n444#1:1028\n445#1:1029\n481#1:1032\n482#1:1033\n488#1:1034\n497#1:1035\n497#1:1036\n578#1:1039\n579#1:1040\n580#1:1041\n120#1:1012\n397#1:1026\n514#1:1037\n348#1:1018\n477#1:1030\n348#1:1019\n477#1:1031\n521#1:1038\n*E\n"})
/* loaded from: classes.dex */
public final class CoroutineScheduler implements Executor, Closeable {
    public static final Companion Companion = null;

    @JvmField
    public static final Symbol NOT_IN_STACK = null;
    private static final /* synthetic */ AtomicIntegerFieldUpdater _isTerminated$volatile$FU = null;
    private static final /* synthetic */ AtomicLongFieldUpdater controlState$volatile$FU = null;
    private static final /* synthetic */ AtomicLongFieldUpdater parkedWorkersStack$volatile$FU = null;
    private volatile /* synthetic */ int _isTerminated$volatile;
    private volatile /* synthetic */ long controlState$volatile;

    @JvmField
    public final int corePoolSize;

    @JvmField
    public final GlobalQueue globalBlockingQueue;

    @JvmField
    public final GlobalQueue globalCpuQueue;

    @JvmField
    public final long idleWorkerKeepAliveNs;

    @JvmField
    public final int maxPoolSize;
    private volatile /* synthetic */ long parkedWorkersStack$volatile;

    @JvmField
    public final String schedulerName;

    @JvmField
    public final ResizableAtomicArray<Worker> workers;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lkotlinx/coroutines/scheduling/CoroutineScheduler$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "NOT_IN_STACK", "Lkotlinx/coroutines/internal/Symbol;", "PARKED", _UrlKt.FRAGMENT_ENCODE_SET, "CLAIMED", "TERMINATED", "BLOCKING_SHIFT", "CREATED_MASK", _UrlKt.FRAGMENT_ENCODE_SET, "BLOCKING_MASK", "CPU_PERMITS_SHIFT", "CPU_PERMITS_MASK", "MIN_SUPPORTED_POOL_SIZE", "MAX_SUPPORTED_POOL_SIZE", "PARKED_INDEX_MASK", "PARKED_VERSION_MASK", "PARKED_VERSION_INC", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[WorkerState.values().length];
            r0[WorkerState.PARKING.ordinal()] = 1;     // Catch: NoSuchFieldError -> L10
        L15:
            r0[WorkerState.BLOCKING.ordinal()] = 2;     // Catch: NoSuchFieldError -> L11
        L23:
            r0[WorkerState.CPU_ACQUIRED.ordinal()] = 3;     // Catch: NoSuchFieldError -> L12
        L17:
            r0[WorkerState.DORMANT.ordinal()] = 4;     // Catch: NoSuchFieldError -> L13
        L19:
            r0[WorkerState.TERMINATED.ordinal()] = 5;     // Catch: NoSuchFieldError -> L14
        L8:
            $EnumSwitchMapping$0 = r0;
        }
    }

    @Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003B\u0011\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0002\u0010\u0006J\u000f\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\nH\u0002¢\u0006\u0004\b\r\u0010\fJ\u000f\u0010\u000e\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u000e\u0010\tJ\u0017\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0013\u0010\fJ\u000f\u0010\u0014\u001a\u00020\nH\u0002¢\u0006\u0004\b\u0014\u0010\fJ\u0011\u0010\u0015\u001a\u0004\u0018\u00010\u000fH\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0019\u0010\u0018\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0017\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u0011\u0010\u001a\u001a\u0004\u0018\u00010\u000fH\u0002¢\u0006\u0004\b\u001a\u0010\u0016J\u001d\u0010\u001d\u001a\u0004\u0018\u00010\u000f2\n\u0010\u001c\u001a\u00060\u0004j\u0002`\u001bH\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u0015\u0010!\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u001f¢\u0006\u0004\b!\u0010\"J\u000f\u0010#\u001a\u00020\nH\u0016¢\u0006\u0004\b#\u0010\fJ\u0015\u0010%\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u0004¢\u0006\u0004\b%\u0010&J\u0017\u0010(\u001a\u0004\u0018\u00010\u000f2\u0006\u0010'\u001a\u00020\u0007¢\u0006\u0004\b(\u0010\u0019R*\u0010)\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00048\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u0014\u00100\u001a\u00020/8\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b0\u00101R\u001c\u00103\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u00104R\u0016\u00105\u001a\u00020\u001f8\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b5\u00106R\u0016\u00108\u001a\u0002078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b8\u00109R$\u0010;\u001a\u0004\u0018\u00010:8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u0016\u0010A\u001a\u0002078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bA\u00109R\u0016\u0010B\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bB\u0010*R\u0016\u0010'\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\b'\u0010CR\b\u0010E\u001a\u00020D8\u0006¨\u0006F"}, d2 = {"Lkotlinx/coroutines/scheduling/CoroutineScheduler$Worker;", "Ljava/lang/Thread;", "<init>", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler;)V", _UrlKt.FRAGMENT_ENCODE_SET, "index", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler;I)V", _UrlKt.FRAGMENT_ENCODE_SET, "tryAcquireCpuPermit", "()Z", _UrlKt.FRAGMENT_ENCODE_SET, "runWorker", "()V", "tryPark", "inStack", "Lkotlinx/coroutines/scheduling/Task;", "task", "executeTask", "(Lkotlinx/coroutines/scheduling/Task;)V", "park", "tryTerminateWorker", "findBlockingTask", "()Lkotlinx/coroutines/scheduling/Task;", "scanLocalQueue", "findAnyTask", "(Z)Lkotlinx/coroutines/scheduling/Task;", "pollGlobalQueues", "Lkotlinx/coroutines/scheduling/StealingMode;", "stealingMode", "trySteal", "(I)Lkotlinx/coroutines/scheduling/Task;", "Lkotlinx/coroutines/scheduling/CoroutineScheduler$WorkerState;", "newState", "tryReleaseCpu", "(Lkotlinx/coroutines/scheduling/CoroutineScheduler$WorkerState;)Z", "run", "upperBound", "nextInt", "(I)I", "mayHaveLocalTasks", "findTask", "indexInArray", "I", "getIndexInArray", "()I", "setIndexInArray", "(I)V", "Lkotlinx/coroutines/scheduling/WorkQueue;", "localQueue", "Lkotlinx/coroutines/scheduling/WorkQueue;", "Lkotlin/jvm/internal/Ref$ObjectRef;", "stolenTask", "Lkotlin/jvm/internal/Ref$ObjectRef;", "state", "Lkotlinx/coroutines/scheduling/CoroutineScheduler$WorkerState;", _UrlKt.FRAGMENT_ENCODE_SET, "terminationDeadline", "J", _UrlKt.FRAGMENT_ENCODE_SET, "nextParkedWorker", "Ljava/lang/Object;", "getNextParkedWorker", "()Ljava/lang/Object;", "setNextParkedWorker", "(Ljava/lang/Object;)V", "minDelayUntilStealableTaskNs", "rngState", "Z", "Lkotlinx/atomicfu/AtomicInt;", "workerCtl", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCoroutineScheduler.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CoroutineScheduler.kt\nkotlinx/coroutines/scheduling/CoroutineScheduler$Worker\n+ 2 CoroutineScheduler.kt\nkotlinx/coroutines/scheduling/CoroutineScheduler\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Tasks.kt\nkotlinx/coroutines/scheduling/TasksKt\n+ 5 Synchronized.common.kt\nkotlinx/coroutines/internal/Synchronized_commonKt\n+ 6 Synchronized.kt\nkotlinx/coroutines/internal/SynchronizedKt\n*L\n1#1,1011:1\n298#2,2:1012\n286#2:1014\n300#2,4:1015\n305#2:1019\n295#2,2:1023\n281#2:1027\n290#2:1028\n284#2:1029\n281#2:1030\n1#3:1020\n77#4:1021\n77#4:1022\n26#5:1025\n16#6:1026\n*S KotlinDebug\n*F\n+ 1 CoroutineScheduler.kt\nkotlinx/coroutines/scheduling/CoroutineScheduler$Worker\n*L\n684#1:1012,2\n684#1:1014\n684#1:1015,4\n699#1:1019\n799#1:1023,2\n850#1:1027\n876#1:1028\n876#1:1029\n941#1:1030\n790#1:1021\n793#1:1022\n846#1:1025\n846#1:1026\n*E\n"})
    public final class Worker extends Thread {
        private static final /* synthetic */ AtomicIntegerFieldUpdater workerCtl$volatile$FU = null;
        private volatile int indexInArray;

        @JvmField
        public final WorkQueue localQueue;

        @JvmField
        public boolean mayHaveLocalTasks;
        private long minDelayUntilStealableTaskNs;
        private volatile Object nextParkedWorker;
        private int rngState;

        @JvmField
        public WorkerState state;
        private final Ref.ObjectRef<Task> stolenTask;
        private long terminationDeadline;
        final /* synthetic */ CoroutineScheduler this$0;
        private volatile /* synthetic */ int workerCtl$volatile;

        static {
            workerCtl$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(Worker.class, "workerCtl$volatile");
        }

        private Worker(CoroutineScheduler r3) {
            this.this$0 = r3;
            setDaemon(true);
            setContextClassLoader(r3.getClass().getClassLoader());
            this.localQueue = new WorkQueue();
            this.stolenTask = new Ref.ObjectRef();
            this.state = WorkerState.DORMANT;
            this.nextParkedWorker = CoroutineScheduler.NOT_IN_STACK;
            int r32 = (int) System.nanoTime();
            if (r32 != 0) goto L6;
            r32 = 42;
        L6:
            this.rngState = r32;
        }

        public Worker(CoroutineScheduler r1, int r2) {
            this(r1);
            setIndexInArray(r2);
        }

        public static final /* synthetic */ CoroutineScheduler access$getThis$0$p(Worker r0) {
            return r0.this$0;
        }

        private final void executeTask(Task r4) {
            this.terminationDeadline = 0;
            if (this.state != WorkerState.PARKING) goto L6;
            this.state = WorkerState.BLOCKING;
        L6:
            if (r4.taskContext == true) goto L8;
            this.this$0.runSafely(r4);
            return;
        L8:
            if (tryReleaseCpu(WorkerState.BLOCKING) == false) goto L10;
            this.this$0.signalCpuWork();
        L10:
            this.this$0.runSafely(r4);
            CoroutineScheduler r42 = this.this$0;
            CoroutineScheduler.access$getControlState$volatile$FU().addAndGet(r42, -2097152);
            if (this.state == WorkerState.TERMINATED) goto L16;
            this.state = WorkerState.DORMANT;
            return;
        }

        private final Task findAnyTask(boolean r2) {
            if (r2 == true) goto L4;
            Task r22 = pollGlobalQueues();
            if (r22 == null) goto L22;
            return r22;
        L22:
            return trySteal(3);
        L4:
            if (nextInt(this.this$0.corePoolSize * 2) != 0) goto L6;
            boolean r23 = true;
        L7:
            if (r23 == false) goto L11;
            Task r0 = pollGlobalQueues();
            if (r0 == null) goto L11;
            return r0;
        L11:
            Task r02 = this.localQueue.poll();
            if (r02 == null) goto L14;
            return r02;
        L14:
            if (r23 == true) goto L22;
            Task r24 = pollGlobalQueues();
            if (r24 == null) goto L22;
            return r24;
        L6:
            r23 = false;
            goto L7
        }

        private final Task findBlockingTask() {
            Task r0 = this.localQueue.pollBlocking();
            if (r0 != null) goto L8;
            r0 = this.this$0.globalBlockingQueue.removeFirstOrNull();
            if (r0 != null) goto L8;
            return trySteal(1);
        L8:
            return r0;
        }

        public static final /* synthetic */ AtomicIntegerFieldUpdater getWorkerCtl$volatile$FU() {
            return workerCtl$volatile$FU;
        }

        private final boolean inStack() {
            if (this.nextParkedWorker == CoroutineScheduler.NOT_IN_STACK) goto L6;
            return true;
        L6:
            return false;
        }

        private final void park() {
            if (this.terminationDeadline != 0) goto L5;
            this.terminationDeadline = System.nanoTime() + this.this$0.idleWorkerKeepAliveNs;
        L5:
            LockSupport.parkNanos(this.this$0.idleWorkerKeepAliveNs);
            if ((System.nanoTime() - this.terminationDeadline) < 0) goto L9;
            this.terminationDeadline = 0;
            tryTerminateWorker();
            return;
        }

        private final Task pollGlobalQueues() {
            int r0 = nextInt(2);
            CoroutineScheduler r1 = this.this$0;
            if (r0 != 0) goto L9;
            Task r02 = r1.globalCpuQueue.removeFirstOrNull();
            if (r02 == null) goto L8;
            return r02;
        L8:
            return this.this$0.globalBlockingQueue.removeFirstOrNull();
        L9:
            Task r03 = r1.globalBlockingQueue.removeFirstOrNull();
            if (r03 == null) goto L13;
            return r03;
        L13:
            return this.this$0.globalCpuQueue.removeFirstOrNull();
        }

        private final void runWorker() {
        L3:
            boolean r1 = false;
        L5:
            if (this.this$0.isTerminated() == true) goto L17;
            if (this.state == WorkerState.TERMINATED) goto L17;
            Task r2 = findTask(this.mayHaveLocalTasks);
            if (r2 != null) goto L10;
            this.mayHaveLocalTasks = false;
            if (this.minDelayUntilStealableTaskNs != 0) goto L13;
            tryPark();
            goto L5
        L13:
            if (r1 == true) goto L15;
            r1 = true;
            goto L5
        L15:
            tryReleaseCpu(WorkerState.PARKING);
            Thread.interrupted();
            LockSupport.parkNanos(this.minDelayUntilStealableTaskNs);
            this.minDelayUntilStealableTaskNs = 0;
            goto L3
        L10:
            this.minDelayUntilStealableTaskNs = 0;
            executeTask(r2);
        L17:
            tryReleaseCpu(WorkerState.TERMINATED);
        }

        private final boolean tryAcquireCpuPermit() {
            if (this.state != WorkerState.CPU_ACQUIRED) goto L5;
            return true;
        L5:
            CoroutineScheduler r4 = this.this$0;
            AtomicLongFieldUpdater r0 = CoroutineScheduler.access$getControlState$volatile$FU();
        L6:
            long r5 = r0.get(r4);
            if (((int) ((9223367638808264704L & r5) >> 42)) == 0) goto L8;
            AtomicLongFieldUpdater r3 = CoroutineScheduler.access$getControlState$volatile$FU();
            if (r3.compareAndSet(r4, r5, r5 - 4398046511104L) == false) goto L6;
            this.state = WorkerState.CPU_ACQUIRED;
            return true;
        L8:
            return false;
        }

        private final void tryPark() {
            if (inStack() == true) goto L6;
            this.this$0.parkedWorkersStackPush(this);
            return;
        L6:
            getWorkerCtl$volatile$FU().set(this, -1);
        L8:
            if (inStack() == false) goto L17;
            if (getWorkerCtl$volatile$FU().get(this) != (-1)) goto L22;
            if (this.this$0.isTerminated() == true) goto L23;
            if (this.state == WorkerState.TERMINATED) goto L24;
            tryReleaseCpu(WorkerState.PARKING);
            Thread.interrupted();
            park();
            goto L8
        L24:
            return;
        L23:
            return;
        L22:
            return;
        }

        private final Task trySteal(int r18) {
            int r1 = (int) (CoroutineScheduler.access$getControlState$volatile$FU().get(this.this$0) & 2097151);
            if (r1 >= 2) goto L5;
            return null;
        L5:
            int r2 = nextInt(r1);
            CoroutineScheduler r4 = this.this$0;
            int r7 = 0;
            long r8 = Long.MAX_VALUE;
        L7:
            if (r7 >= r1) goto L24;
            r2 = r2 + 1;
            if (r2 <= r1) goto L11;
            r2 = 1;
        L11:
            Worker r12 = r4.workers.get(r2);
            if (r12 == null) goto L22;
            if (r12 == this) goto L22;
            long r122 = r12.localQueue.trySteal(r18, this.stolenTask);
            if (r122 == (-1)) goto L16;
            if (r122 <= 0) goto L22;
            r8 = Math.min(r8, r122);
            goto L22
        L16:
            Ref.ObjectRef<Task> r0 = this.stolenTask;
            Task r13 = r0.element;
            r0.element = null;
            return r13;
        L22:
            r7 = r7 + 1;
            goto L7
        L24:
            if (r8 != LongCompanionObject.MAX_VALUE) goto L27;
            r8 = 0;
        L27:
            this.minDelayUntilStealableTaskNs = r8;
            return null;
        }

        private final void tryTerminateWorker() {
            CoroutineScheduler r0 = this.this$0;
            ResizableAtomicArray<Worker> r1 = r0.workers;
            monitor-enter(r1);
        L19:
            th = move-exception;
            throw th;
        L5:
            if (r0.isTerminated() == false) goto L9;
            monitor-exit(r1);
            return;
        L9:
            if (((int) (CoroutineScheduler.access$getControlState$volatile$FU().get(r0) & 2097151)) > r0.corePoolSize) goto L13;
            monitor-exit(r1);
            return;
        L13:
            if (getWorkerCtl$volatile$FU().compareAndSet(this, -1, 1) == true) goto L16;
            monitor-exit(r1);
            return;
        L16:
            int r2 = this.indexInArray;     // Catch: Throwable -> L19
            setIndexInArray(0);     // Catch: Throwable -> L19
            r0.parkedWorkersStackTopUpdate(this, r2, 0);     // Catch: Throwable -> L19
            int r3 = (int) (CoroutineScheduler.access$getControlState$volatile$FU().getAndDecrement(r0) & 2097151);     // Catch: Throwable -> L19
            if (r3 == r2) goto L21;
            Worker r4 = r0.workers.get(r3);     // Catch: Throwable -> L19
            r0.workers.setSynchronized(r2, r4);     // Catch: Throwable -> L19
            r4.setIndexInArray(r2);     // Catch: Throwable -> L19
            r0.parkedWorkersStackTopUpdate(r4, r3, r2);     // Catch: Throwable -> L19
        L21:
            r0.workers.setSynchronized(r3, null);     // Catch: Throwable -> L19
            Unit r02 = Unit.INSTANCE;     // Catch: Throwable -> L19
            monitor-exit(r1);
            this.state = WorkerState.TERMINATED;
        }

        public final Task findTask(boolean r2) {
            if (tryAcquireCpuPermit() == false) goto L7;
            return findAnyTask(r2);
        L7:
            return findBlockingTask();
        }

        public final int getIndexInArray() {
            return this.indexInArray;
        }

        public final Object getNextParkedWorker() {
            return this.nextParkedWorker;
        }

        public final int nextInt(int r3) {
            int r0 = this.rngState;
            int r02 = r0 ^ (r0 << 13);
            int r03 = r02 ^ (r02 >> 17);
            int r04 = r03 ^ (r03 << 5);
            this.rngState = r04;
            int r2 = r3 - 1;
            if ((r2 & r3) != 0) goto L7;
            return r2 & r04;
        L7:
            return (Integer.MAX_VALUE & r04) % r3;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            runWorker();
        }

        public final void setIndexInArray(int r3) {
            StringBuilder r0 = new StringBuilder();
            r0.append(this.this$0.schedulerName);
            r0.append("-worker-");
            if (r3 != 0) goto L5;
            String r1 = "TERMINATED";
        L6:
            r0.append(r1);
            setName(r0.toString());
            this.indexInArray = r3;
            return;
        L5:
            r1 = String.valueOf(r3);
            goto L6
        }

        public final void setNextParkedWorker(Object r1) {
            this.nextParkedWorker = r1;
        }

        public final boolean tryReleaseCpu(WorkerState r7) {
            WorkerState r0 = this.state;
            if (r0 != WorkerState.CPU_ACQUIRED) goto L5;
            boolean r1 = true;
        L6:
            if (r1 == false) goto L8;
            CoroutineScheduler r2 = this.this$0;
            CoroutineScheduler.access$getControlState$volatile$FU().addAndGet(r2, 4398046511104L);
        L8:
            if (r0 == r7) goto L10;
            this.state = r7;
        L10:
            return r1;
        L5:
            r1 = false;
            goto L6
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/scheduling/CoroutineScheduler$WorkerState;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "CPU_ACQUIRED", "BLOCKING", "PARKING", "DORMANT", "TERMINATED", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum WorkerState extends Enum<WorkerState> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ WorkerState[] $VALUES = null;
        public static final WorkerState BLOCKING = null;
        public static final WorkerState CPU_ACQUIRED = null;
        public static final WorkerState DORMANT = null;
        public static final WorkerState PARKING = null;
        public static final WorkerState TERMINATED = null;

        private static final /* synthetic */ WorkerState[] $values() {
            return new WorkerState[]{CPU_ACQUIRED, BLOCKING, PARKING, DORMANT, TERMINATED};
        }

        static {
            CPU_ACQUIRED = new WorkerState("CPU_ACQUIRED", 0);
            BLOCKING = new WorkerState("BLOCKING", 1);
            PARKING = new WorkerState("PARKING", 2);
            DORMANT = new WorkerState("DORMANT", 3);
            TERMINATED = new WorkerState("TERMINATED", 4);
            WorkerState[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        WorkerState(String r1, int r2) {
        }

        public static WorkerState valueOf(String r1) {
            return (WorkerState) Enum.valueOf(WorkerState.class, r1);
        }

        public static WorkerState[] values() {
            return (WorkerState[]) $VALUES.clone();
        }
    }

    static {
        Companion = new Companion(null);
        parkedWorkersStack$volatile$FU = AtomicLongFieldUpdater.newUpdater(CoroutineScheduler.class, "parkedWorkersStack$volatile");
        controlState$volatile$FU = AtomicLongFieldUpdater.newUpdater(CoroutineScheduler.class, "controlState$volatile");
        _isTerminated$volatile$FU = AtomicIntegerFieldUpdater.newUpdater(CoroutineScheduler.class, "_isTerminated$volatile");
        NOT_IN_STACK = new Symbol("NOT_IN_STACK");
    }

    public CoroutineScheduler(int r3, int r4, long r5, String r7) {
        this.corePoolSize = r3;
        this.maxPoolSize = r4;
        this.idleWorkerKeepAliveNs = r5;
        this.schedulerName = r7;
        if (r3 >= 1) goto L5;
        SegmentedByteString$$ExternalSyntheticBUOutline0.m("Core pool size ", r3, " should be at least 1");
        throw null;
    L5:
        if (r4 >= r3) goto L7;
        Utf8$$ExternalSyntheticBUOutline0.m("Max pool size ", r4, " should be greater than or equals to core pool size ", r3);
        throw null;
    L7:
        if (r4 <= 2097150) goto L9;
        SegmentedByteString$$ExternalSyntheticBUOutline0.m("Max pool size ", r4, " should not exceed maximal supported number of threads 2097150");
        throw null;
    L9:
        if (r5 <= 0) goto L12;
        this.globalCpuQueue = new GlobalQueue();
        this.globalBlockingQueue = new GlobalQueue();
        this.workers = new ResizableAtomicArray((r3 + 1) * 2);
        this.controlState$volatile = r3 << 42;
        return;
    L12:
        ArraysKt__ArraysKt$$ExternalSyntheticBUOutline0.m("Idle worker keep alive time ", r5, " must be positive");
        throw null;
    }

    public static final /* synthetic */ AtomicLongFieldUpdater access$getControlState$volatile$FU() {
        return getControlState$volatile$FU();
    }

    private final boolean addToGlobalQueue(Task r2) {
        if (r2.taskContext == false) goto L7;
        return this.globalBlockingQueue.addLast(r2);
    L7:
        return this.globalCpuQueue.addLast(r2);
    }

    private final int createNewWorker() {
        ResizableAtomicArray<Worker> r0 = this.workers;
        monitor-enter(r0);
    L30:
        th = move-exception;
        throw th;
    L5:
        if (isTerminated() == false) goto L9;
        monitor-exit(r0);
        return -1;
    L9:
        long r1 = getControlState$volatile$FU().get(this);     // Catch: Throwable -> L30
        int r5 = (int) (r1 & 2097151);     // Catch: Throwable -> L30
        int r12 = RangesKt.coerceAtLeast(r5 - ((int) ((r1 & 4398044413952L) >> 21)), 0);     // Catch: Throwable -> L30
        if (r12 < this.corePoolSize) goto L14;
        monitor-exit(r0);
        return 0;
    L14:
        if (r5 < this.maxPoolSize) goto L17;
        monitor-exit(r0);
        return 0;
    L17:
        int r2 = ((int) (access$getControlState$volatile$FU().get(this) & 2097151)) + 1;     // Catch: Throwable -> L30
        if (r2 <= 0) goto L33;
        if (this.workers.get(r2) != null) goto L33;
        Worker r52 = new Worker(this, r2);     // Catch: Throwable -> L30
        this.workers.setSynchronized(r2, r52);     // Catch: Throwable -> L30
        if (r2 != ((int) (2097151 & getControlState$volatile$FU().incrementAndGet(this)))) goto L29;
        int r13 = r12 + 1;
        monitor-exit(r0);
        r52.start();
        return r13;
    L29:
        throw new IllegalArgumentException("Failed requirement.");     // Catch: Throwable -> L30
    L33:
        throw new IllegalArgumentException("Failed requirement.");     // Catch: Throwable -> L30
    }

    private final Worker currentWorker() {
        Thread r0 = Thread.currentThread();
        if ((r0 instanceof Worker) == false) goto L5;
        Worker r02 = (Worker) r0;
    L6:
        if (r02 != null) goto L8;
    L10:
        return null;
    L8:
        if (Intrinsics.areEqual(Worker.access$getThis$0$p(r02), this) == false) goto L10;
        return r02;
    L5:
        r02 = null;
        goto L6
    }

    public static /* synthetic */ void dispatch$default(CoroutineScheduler r1, Runnable r2, boolean r3, boolean r4, int r5, Object r6) {
        if ((r5 & 2) == 0) goto L6;
        r3 = false;
    L6:
        if ((r5 & 4) == 0) goto L8;
        r4 = false;
    L8:
        r1.dispatch(r2, r3, r4);
    }

    private static final /* synthetic */ AtomicLongFieldUpdater getControlState$volatile$FU() {
        return controlState$volatile$FU;
    }

    private static final /* synthetic */ AtomicLongFieldUpdater getParkedWorkersStack$volatile$FU() {
        return parkedWorkersStack$volatile$FU;
    }

    private static final /* synthetic */ AtomicIntegerFieldUpdater get_isTerminated$volatile$FU() {
        return _isTerminated$volatile$FU;
    }

    private final int parkedWorkersStackNextIndex(Worker r1) {
        Object r0 = r1.getNextParkedWorker();
    L4:
        if (r0 == NOT_IN_STACK) goto L5;
        if (r0 == null) goto L8;
        Worker r02 = (Worker) r0;
        int r12 = r02.getIndexInArray();
        if (r12 != 0) goto L12;
        r0 = r02.getNextParkedWorker();
        goto L4
    L12:
        return r12;
    L8:
        return 0;
    L5:
        return -1;
    }

    private final Worker parkedWorkersStackPop() {
        AtomicLongFieldUpdater r0 = getParkedWorkersStack$volatile$FU();
    L3:
        long r3 = r0.get(this);
        Worker r7 = this.workers.get((int) (2097151 & r3));
        if (r7 == null) goto L5;
        long r1 = (2097152 + r3) & (-2097152);
        int r5 = this.parkedWorkersStackNextIndex(r7);
        if (r5 < 0) goto L3;
        CoroutineScheduler r2 = this;
        if (getParkedWorkersStack$volatile$FU().compareAndSet(r2, r3, r5 | r1) == true) goto L11;
        CoroutineScheduler this = r2;
        goto L3
    L11:
        r7.setNextParkedWorker(NOT_IN_STACK);
        return r7;
    L5:
        return null;
    }

    private final void signalBlockingWork(long r2) {
        if (tryUnpark() == false) goto L6;
        return;
    L6:
        if (tryCreateWorker(r2) == false) goto L8;
        return;
    L8:
        tryUnpark();
    }

    private final Task submitToLocalQueue(Worker r2, Task r3, boolean r4) {
        if (r2 == null) goto L6;
        WorkerState r1 = r2.state;
        if (r1 == WorkerState.TERMINATED) goto L6;
        if (r3.taskContext == false) goto L10;
    L12:
        r2.mayHaveLocalTasks = true;
        return r2.localQueue.add(r3, r4);
    L10:
        if (r1 != WorkerState.BLOCKING) goto L12;
        return r3;
    L6:
        return r3;
    }

    private final boolean tryCreateWorker(long r4) {
        if (RangesKt.coerceAtLeast(((int) (2097151 & r4)) - ((int) ((r4 & 4398044413952L) >> 21)), 0) >= this.corePoolSize) goto L11;
        int r5 = createNewWorker();
        if (r5 == 1) goto L7;
    L9:
        if (r5 <= 0) goto L11;
        return true;
    L7:
        if (this.corePoolSize <= 1) goto L9;
        createNewWorker();
    L11:
        return false;
    }

    public static /* synthetic */ boolean tryCreateWorker$default(CoroutineScheduler r0, long r1, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r1 = getControlState$volatile$FU().get(r0);
    L6:
        return r0.tryCreateWorker(r1);
    }

    private final boolean tryUnpark() {
    L2:
        Worker r0 = parkedWorkersStackPop();
        if (r0 == null) goto L4;
        if (Worker.getWorkerCtl$volatile$FU().compareAndSet(r0, -1, 0) == false) goto L2;
        LockSupport.unpark(r0);
        return true;
    L4:
        return false;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        shutdown(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
    }

    public final Task createTask(Runnable r3, boolean r4) {
        long r0 = TasksKt.schedulerTimeSource.nanoTime();
        if ((r3 instanceof Task) == false) goto L7;
        Task r32 = (Task) r3;
        r32.submissionTime = r0;
        r32.taskContext = r4;
        return r32;
    L7:
        return TasksKt.asTask(r3, r0, r4);
    }

    public final void dispatch(Runnable r4, boolean r5, boolean r6) {
        AbstractTimeSourceKt.access$getTimeSource$p();
        Task r42 = createTask(r4, r5);
        boolean r52 = r42.taskContext;
        if (r52 == false) goto L5;
        long r0 = getControlState$volatile$FU().addAndGet(this, 2097152);
    L6:
        Task r43 = submitToLocalQueue(currentWorker(), r42, r6);
        if (r43 != null) goto L9;
    L13:
        if (r52 == false) goto L16;
        signalBlockingWork(r0);
        return;
    L16:
        signalCpuWork();
        return;
    L9:
        if (addToGlobalQueue(r43) == true) goto L13;
        ExecutorCompat$HandlerExecutor$$ExternalSyntheticBUOutline0.m(this.schedulerName, " was terminated");
        return;
    L5:
        r0 = 0;
        goto L6
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable r7) {
        dispatch$default(this, r7, false, false, 6, null);
    }

    public final boolean isTerminated() {
        if (get_isTerminated$volatile$FU().get(this) != 1) goto L5;
        return true;
    L5:
        return false;
    }

    public final boolean parkedWorkersStackPush(Worker r10) {
        if (r10.getNextParkedWorker() == NOT_IN_STACK) goto L6;
        return false;
    L6:
        AtomicLongFieldUpdater r0 = getParkedWorkersStack$volatile$FU();
    L7:
        long r3 = r0.get(this);
        int r2 = r10.getIndexInArray();
        r10.setNextParkedWorker(this.workers.get((int) (2097151 & r3)));
        long r5 = ((2097152 + r3) & (-2097152)) | r2;
        CoroutineScheduler r22 = this;
        if (getParkedWorkersStack$volatile$FU().compareAndSet(r22, r3, r5) == true) goto L9;
        CoroutineScheduler this = r22;
        goto L7
    L9:
        return true;
    }

    public final void parkedWorkersStackTopUpdate(Worker r10, int r11, int r12) {
        AtomicLongFieldUpdater r0 = getParkedWorkersStack$volatile$FU();
    L3:
        long r3 = r0.get(this);
        int r1 = (int) (2097151 & r3);
        long r5 = (2097152 + r3) & (-2097152);
        if (r1 != r11) goto L8;
        if (r12 != 0) goto L7;
        r1 = this.parkedWorkersStackNextIndex(r10);
        goto L8
    L7:
        r1 = r12;
    L8:
        if (r1 < 0) goto L3;
        CoroutineScheduler r2 = this;
        if (getParkedWorkersStack$volatile$FU().compareAndSet(r2, r3, r5 | r1) == true) goto L11;
        CoroutineScheduler this = r2;
        goto L3
    }

    public final void runSafely(Task r2) {
        r2.run();     // Catch: Throwable -> L5
        AbstractTimeSourceKt.access$getTimeSource$p();
        return;
    L5:
        th = move-exception;
        Thread r22 = Thread.currentThread();     // Catch: Throwable -> L9
        r22.getUncaughtExceptionHandler().uncaughtException(r22, th);     // Catch: Throwable -> L9
        AbstractTimeSourceKt.access$getTimeSource$p();
        return;
    L9:
        th = move-exception;
        AbstractTimeSourceKt.access$getTimeSource$p();
        throw th;
    }

    public final void shutdown(long r8) {
        if (get_isTerminated$volatile$FU().compareAndSet(this, 0, 1) == true) goto L5;
        return;
    L5:
        Worker r0 = currentWorker();
        ResizableAtomicArray<Worker> r1 = this.workers;
        monitor-enter(r1);
        int r3 = (int) (access$getControlState$volatile$FU().get(this) & 2097151);
        monitor-exit(r1);
        if (1 > r3) goto L20;
        int r12 = 1;
    L12:
        Worker r4 = this.workers.get(r12);
        if (r4 != r0) goto L15;
    L18:
        if (r12 == r3) goto L20;
        r12 = r12 + 1;
    L15:
        if (r4.getState() == Thread.State.TERMINATED) goto L17;
        LockSupport.unpark(r4);
        r4.join(r8);
        goto L15
    L17:
        r4.localQueue.offloadAllWorkTo(this.globalBlockingQueue);
    L20:
        this.globalBlockingQueue.close();
        this.globalCpuQueue.close();
    L21:
        if (r0 == null) goto L24;
        Task r82 = r0.findTask(true);
        if (r82 == null) goto L24;
    L32:
        runSafely(r82);
    L24:
        r82 = this.globalCpuQueue.removeFirstOrNull();
        if (r82 != null) goto L32;
        r82 = this.globalBlockingQueue.removeFirstOrNull();
        if (r82 != null) goto L32;
        if (r0 == null) goto L30;
        r0.tryReleaseCpu(WorkerState.TERMINATED);
    L30:
        getParkedWorkersStack$volatile$FU().set(this, 0);
        getControlState$volatile$FU().set(this, 0);
        return;
    L33:
        th = move-exception;
        throw th;
    }

    public final void signalCpuWork() {
        if (tryUnpark() == false) goto L6;
        return;
    L6:
        if (tryCreateWorker$default(this, 0, 1, null) == false) goto L8;
        return;
    L8:
        tryUnpark();
    }

    public String toString() {
        ArrayList r0 = new ArrayList();
        int r1 = this.workers.currentLength();
        int r2 = 0;
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
        int r7 = 0;
        int r8 = 1;
    L3:
        if (r8 >= r1) goto L27;
        Worker r9 = this.workers.get(r8);
        if (r9 == null) goto L26;
        int r10 = r9.localQueue.getSize$kotlinx_coroutines_core();
        int r92 = WhenMappings.$EnumSwitchMapping$0[r9.state.ordinal()];
        if (r92 != 1) goto L10;
        r5 = r5 + 1;
        goto L26
    L10:
        if (r92 != 2) goto L12;
        r4 = r4 + 1;
        StringBuilder r93 = new StringBuilder();
        r93.append(r10);
        r93.append('b');
        r0.add(r93.toString());
        goto L26
    L12:
        if (r92 != 3) goto L14;
        r2 = r2 + 1;
        StringBuilder r94 = new StringBuilder();
        r94.append(r10);
        r94.append('c');
        r0.add(r94.toString());
        goto L26
    L14:
        if (r92 != 4) goto L16;
        r6 = r6 + 1;
        if (r10 <= 0) goto L26;
        StringBuilder r95 = new StringBuilder();
        r95.append(r10);
        r95.append('d');
        r0.add(r95.toString());
        goto L26
    L16:
        if (r92 != 5) goto L18;
        r7 = r7 + 1;
        goto L26
    L18:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L26:
        r8 = r8 + 1;
        goto L3
    L27:
        long r82 = getControlState$volatile$FU().get(this);
        return this.schedulerName + '@' + DebugStringsKt.getHexAddress(this) + "[Pool Size {core = " + this.corePoolSize + ", max = " + this.maxPoolSize + "}, Worker States {CPU = " + r2 + ", blocking = " + r4 + ", parked = " + r5 + ", dormant = " + r6 + ", terminated = " + r7 + "}, running workers queues = " + r0 + ", global CPU queue size = " + this.globalCpuQueue.getSize() + ", global blocking queue size = " + this.globalBlockingQueue.getSize() + ", Control State {created workers= " + ((int) (2097151 & r82)) + ", blocking tasks = " + ((int) ((4398044413952L & r82) >> 21)) + ", CPUs acquired = " + (this.corePoolSize - ((int) ((9223367638808264704L & r82) >> 42))) + "}]";
    }
}
