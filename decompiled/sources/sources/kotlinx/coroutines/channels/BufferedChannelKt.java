package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.reflect.KFunction;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.SystemPropsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0014\u001a3\u0010\u0004\u001a \u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u0001\"\u0004\b\u0000\u0010\u0000H\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a1\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0006\u001a\u00020\u00022\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0002¢\u0006\u0004\b\b\u0010\t\u001ax\u0010\u0017\u001a\u00020\u0016\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u00028\u00000\u000b2\u0006\u0010\f\u001a\u00028\u00002O\b\u0002\u0010\u0015\u001aI\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u0014\u0018\u00010\rH\u0002¢\u0006\u0004\b\u0017\u0010\u0018\u001a\u0017\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0019H\u0002¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u001f\u0010\u001f\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u0019H\u0002¢\u0006\u0004\b\u001f\u0010 \u001a\u001f\u0010\"\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u0016H\u0002¢\u0006\u0004\b\"\u0010#\"\u001c\u0010%\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010$0\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&\"\u0014\u0010'\u001a\u00020\u00198\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b'\u0010(\"\u0014\u0010)\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010(\"\u0014\u0010+\u001a\u00020*8\u0000X\u0081\u0004¢\u0006\u0006\n\u0004\b+\u0010,\"\u0014\u0010-\u001a\u00020*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010,\"\u0014\u0010.\u001a\u00020*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010,\"\u0014\u0010/\u001a\u00020*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u0010,\"\u0014\u00100\u001a\u00020*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u0010,\"\u0014\u00101\u001a\u00020*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u0010,\"\u0014\u00102\u001a\u00020*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u0010,\"\u0014\u00103\u001a\u00020*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u0010,\"\u001a\u00104\u001a\u00020*8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b4\u0010,\u001a\u0004\b5\u00106\"\u0014\u00107\u001a\u00020*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u0010,\"\u0014\u00108\u001a\u00020*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b8\u0010,\"\u0014\u00109\u001a\u00020*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b9\u0010,\"\u0014\u0010:\u001a\u00020*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010,\"\u0014\u0010;\u001a\u00020*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b;\u0010,\"\u0014\u0010<\u001a\u00020*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u0010,\"\u0014\u0010=\u001a\u00020*8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b=\u0010,¨\u0006>"}, d2 = {"E", "Lkotlin/reflect/KFunction2;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/channels/ChannelSegment;", "createSegmentFunction", "()Lkotlin/reflect/KFunction;", "id", "prev", "createSegment", "(JLkotlinx/coroutines/channels/ChannelSegment;)Lkotlinx/coroutines/channels/ChannelSegment;", "T", "Lkotlinx/coroutines/CancellableContinuation;", "value", "Lkotlin/Function3;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ParameterName;", "name", "cause", "Lkotlin/coroutines/CoroutineContext;", "context", _UrlKt.FRAGMENT_ENCODE_SET, "onCancellation", _UrlKt.FRAGMENT_ENCODE_SET, "tryResume0", "(Lkotlinx/coroutines/CancellableContinuation;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "capacity", "initialBufferEnd", "(I)J", "counter", "closeStatus", "constructSendersAndCloseStatus", "(JI)J", "pauseEB", "constructEBCompletedAndPauseFlag", "(JZ)J", _UrlKt.FRAGMENT_ENCODE_SET, "NULL_SEGMENT", "Lkotlinx/coroutines/channels/ChannelSegment;", "SEGMENT_SIZE", "I", "EXPAND_BUFFER_COMPLETION_WAIT_ITERATIONS", "Lkotlinx/coroutines/internal/Symbol;", "BUFFERED", "Lkotlinx/coroutines/internal/Symbol;", "IN_BUFFER", "RESUMING_BY_RCV", "RESUMING_BY_EB", "POISONED", "DONE_RCV", "INTERRUPTED_SEND", "INTERRUPTED_RCV", "CHANNEL_CLOSED", "getCHANNEL_CLOSED", "()Lkotlinx/coroutines/internal/Symbol;", "SUSPEND", "SUSPEND_NO_WAITER", "FAILED", "NO_RECEIVE_RESULT", "CLOSE_HANDLER_CLOSED", "CLOSE_HANDLER_INVOKED", "NO_CLOSE_CAUSE", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class BufferedChannelKt {

    @JvmField
    public static final Symbol BUFFERED = null;
    private static final Symbol CHANNEL_CLOSED = null;
    private static final Symbol CLOSE_HANDLER_CLOSED = null;
    private static final Symbol CLOSE_HANDLER_INVOKED = null;
    private static final Symbol DONE_RCV = null;
    private static final int EXPAND_BUFFER_COMPLETION_WAIT_ITERATIONS = 0;
    private static final Symbol FAILED = null;
    private static final Symbol INTERRUPTED_RCV = null;
    private static final Symbol INTERRUPTED_SEND = null;
    private static final Symbol IN_BUFFER = null;
    private static final Symbol NO_CLOSE_CAUSE = null;
    private static final Symbol NO_RECEIVE_RESULT = null;
    private static final ChannelSegment<Object> NULL_SEGMENT = null;
    private static final Symbol POISONED = null;
    private static final Symbol RESUMING_BY_EB = null;
    private static final Symbol RESUMING_BY_RCV = null;

    @JvmField
    public static final int SEGMENT_SIZE = 0;
    private static final Symbol SUSPEND = null;
    private static final Symbol SUSPEND_NO_WAITER = null;

    /* JADX INFO: Add missing generic type declarations: [E] */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* renamed from: kotlinx.coroutines.channels.BufferedChannelKt$createSegmentFunction$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final /* synthetic */ class AnonymousClass1<E> extends FunctionReferenceImpl implements Function2<Long, ChannelSegment<E>, ChannelSegment<E>> {
        public static final AnonymousClass1 INSTANCE = null;

        static {
            INSTANCE = new AnonymousClass1();
        }

        public AnonymousClass1() {
            super(2, BufferedChannelKt.class, "createSegment", "createSegment(JLkotlinx/coroutines/channels/ChannelSegment;)Lkotlinx/coroutines/channels/ChannelSegment;", 1);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Long r3, Object r4) {
            return invoke(r3.longValue(), (ChannelSegment) r4);
        }

        public final ChannelSegment<E> invoke(long r1, ChannelSegment<E> r3) {
            return BufferedChannelKt.access$createSegment(r1, r3);
        }
    }

    static {
        NULL_SEGMENT = new ChannelSegment(-1, null, null, 0);
        SEGMENT_SIZE = SystemPropsKt.systemProp$default("kotlinx.coroutines.bufferedChannel.segmentSize", 32, 0, 0, 12, null);
        EXPAND_BUFFER_COMPLETION_WAIT_ITERATIONS = SystemPropsKt.systemProp$default("kotlinx.coroutines.bufferedChannel.expandBufferCompletionWaitIterations", 10000, 0, 0, 12, null);
        BUFFERED = new Symbol("BUFFERED");
        IN_BUFFER = new Symbol("SHOULD_BUFFER");
        RESUMING_BY_RCV = new Symbol("S_RESUMING_BY_RCV");
        RESUMING_BY_EB = new Symbol("RESUMING_BY_EB");
        POISONED = new Symbol("POISONED");
        DONE_RCV = new Symbol("DONE_RCV");
        INTERRUPTED_SEND = new Symbol("INTERRUPTED_SEND");
        INTERRUPTED_RCV = new Symbol("INTERRUPTED_RCV");
        CHANNEL_CLOSED = new Symbol("CHANNEL_CLOSED");
        SUSPEND = new Symbol("SUSPEND");
        SUSPEND_NO_WAITER = new Symbol("SUSPEND_NO_WAITER");
        FAILED = new Symbol("FAILED");
        NO_RECEIVE_RESULT = new Symbol("NO_RECEIVE_RESULT");
        CLOSE_HANDLER_CLOSED = new Symbol("CLOSE_HANDLER_CLOSED");
        CLOSE_HANDLER_INVOKED = new Symbol("CLOSE_HANDLER_INVOKED");
        NO_CLOSE_CAUSE = new Symbol("NO_CLOSE_CAUSE");
    }

    public static final /* synthetic */ long access$constructEBCompletedAndPauseFlag(long r0, boolean r2) {
        return constructEBCompletedAndPauseFlag(r0, r2);
    }

    public static final /* synthetic */ long access$constructSendersAndCloseStatus(long r0, int r2) {
        return constructSendersAndCloseStatus(r0, r2);
    }

    public static final /* synthetic */ ChannelSegment access$createSegment(long r0, ChannelSegment r2) {
        return createSegment(r0, r2);
    }

    public static final /* synthetic */ Symbol access$getCLOSE_HANDLER_CLOSED$p() {
        return CLOSE_HANDLER_CLOSED;
    }

    public static final /* synthetic */ Symbol access$getCLOSE_HANDLER_INVOKED$p() {
        return CLOSE_HANDLER_INVOKED;
    }

    public static final /* synthetic */ Symbol access$getDONE_RCV$p() {
        return DONE_RCV;
    }

    public static final /* synthetic */ int access$getEXPAND_BUFFER_COMPLETION_WAIT_ITERATIONS$p() {
        return EXPAND_BUFFER_COMPLETION_WAIT_ITERATIONS;
    }

    public static final /* synthetic */ Symbol access$getFAILED$p() {
        return FAILED;
    }

    public static final /* synthetic */ Symbol access$getINTERRUPTED_RCV$p() {
        return INTERRUPTED_RCV;
    }

    public static final /* synthetic */ Symbol access$getINTERRUPTED_SEND$p() {
        return INTERRUPTED_SEND;
    }

    public static final /* synthetic */ Symbol access$getIN_BUFFER$p() {
        return IN_BUFFER;
    }

    public static final /* synthetic */ Symbol access$getNO_CLOSE_CAUSE$p() {
        return NO_CLOSE_CAUSE;
    }

    public static final /* synthetic */ Symbol access$getNO_RECEIVE_RESULT$p() {
        return NO_RECEIVE_RESULT;
    }

    public static final /* synthetic */ ChannelSegment access$getNULL_SEGMENT$p() {
        return NULL_SEGMENT;
    }

    public static final /* synthetic */ Symbol access$getPOISONED$p() {
        return POISONED;
    }

    public static final /* synthetic */ Symbol access$getRESUMING_BY_EB$p() {
        return RESUMING_BY_EB;
    }

    public static final /* synthetic */ Symbol access$getRESUMING_BY_RCV$p() {
        return RESUMING_BY_RCV;
    }

    public static final /* synthetic */ Symbol access$getSUSPEND$p() {
        return SUSPEND;
    }

    public static final /* synthetic */ Symbol access$getSUSPEND_NO_WAITER$p() {
        return SUSPEND_NO_WAITER;
    }

    public static final /* synthetic */ long access$initialBufferEnd(int r2) {
        return initialBufferEnd(r2);
    }

    public static final /* synthetic */ boolean access$tryResume0(CancellableContinuation r0, Object r1, Function3 r2) {
        return tryResume0(r0, r1, r2);
    }

    private static final long constructEBCompletedAndPauseFlag(long r2, boolean r4) {
        if (r4 == false) goto L4;
        long r0 = 4611686018427387904L;
    L6:
        return r0 + r2;
    L4:
        r0 = 0;
        goto L6
    }

    private static final long constructSendersAndCloseStatus(long r2, int r4) {
        return (r4 << 60) + r2;
    }

    private static final <E> ChannelSegment<E> createSegment(long r6, ChannelSegment<E> r8) {
        return new ChannelSegment(r6, r8, r8.getChannel(), 0);
    }

    public static final <E> KFunction<ChannelSegment<E>> createSegmentFunction() {
        return AnonymousClass1.INSTANCE;
    }

    public static final Symbol getCHANNEL_CLOSED() {
        return CHANNEL_CLOSED;
    }

    private static final long initialBufferEnd(int r2) {
        if (r2 != 0) goto L4;
        return 0;
    L4:
        if (r2 != Integer.MAX_VALUE) goto L6;
        return LongCompanionObject.MAX_VALUE;
    L6:
        return r2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> boolean tryResume0(CancellableContinuation<? super T> r1, T r2, Function3<? super Throwable, ? super T, ? super CoroutineContext, Unit> r3) {
        Object r22 = r1.tryResume(r2, null, r3);
        if (r22 == null) goto L6;
        r1.completeResume(r22);
        return true;
    L6:
        return false;
    }

    public static /* synthetic */ boolean tryResume0$default(CancellableContinuation r0, Object r1, Function3 r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = null;
    L6:
        return tryResume0(r0, r1, r2);
    }
}
