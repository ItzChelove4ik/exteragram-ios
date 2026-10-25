package kotlinx.coroutines.channels;

import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.UndeliveredElementException;
import okhttp3.Request$Builder$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import okio.SegmentedByteString$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B;\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\"\b\u0002\u0010\n\u001a\u001c\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\t¢\u0006\u0004\b\u000b\u0010\fJ%\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u00102\u0006\u0010\r\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J%\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u00102\u0006\u0010\r\u001a\u00028\u00002\u0006\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u0014\u0010\u0012J\u001d\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u00102\u0006\u0010\r\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u0018\u0010\u0019\u001a\u00020\b2\u0006\u0010\r\u001a\u00028\u0000H\u0096@¢\u0006\u0004\b\u0019\u0010\u001aJ\u001d\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u00102\u0006\u0010\r\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u001b\u0010\u0017R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u001dR\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u000e8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 ¨\u0006!"}, d2 = {"Lkotlinx/coroutines/channels/ConflatedBufferedChannel;", "E", "Lkotlinx/coroutines/channels/BufferedChannel;", _UrlKt.FRAGMENT_ENCODE_SET, "capacity", "Lkotlinx/coroutines/channels/BufferOverflow;", "onBufferOverflow", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/internal/OnUndeliveredElement;", "onUndeliveredElement", "<init>", "(ILkotlinx/coroutines/channels/BufferOverflow;Lkotlin/jvm/functions/Function1;)V", "element", _UrlKt.FRAGMENT_ENCODE_SET, "isSendOp", "Lkotlinx/coroutines/channels/ChannelResult;", "trySendImpl-Mj0NB7M", "(Ljava/lang/Object;Z)Ljava/lang/Object;", "trySendImpl", "trySendDropLatest-Mj0NB7M", "trySendDropLatest", "trySendDropOldest-JP2dKIU", "(Ljava/lang/Object;)Ljava/lang/Object;", "trySendDropOldest", "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trySend-JP2dKIU", "trySend", "I", "Lkotlinx/coroutines/channels/BufferOverflow;", "isConflatedDropOldest", "()Z", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nConflatedBufferedChannel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConflatedBufferedChannel.kt\nkotlinx/coroutines/channels/ConflatedBufferedChannel\n+ 2 Channel.kt\nkotlinx/coroutines/channels/ChannelKt\n+ 3 BufferedChannel.kt\nkotlinx/coroutines/channels/BufferedChannel\n+ 4 BufferedChannel.kt\nkotlinx/coroutines/channels/BufferedChannelKt\n+ 5 BufferedChannel.kt\nkotlinx/coroutines/channels/BufferedChannel$sendImpl$1\n*L\n1#1,112:1\n1049#2,2:113\n1011#2,2:115\n1011#2,2:198\n1049#2,2:200\n241#3:117\n266#3,10:118\n277#3,68:129\n3075#4:128\n266#5:197\n*S KotlinDebug\n*F\n+ 1 ConflatedBufferedChannel.kt\nkotlinx/coroutines/channels/ConflatedBufferedChannel\n*L\n34#1:113,2\n46#1:115,2\n99#1:198,2\n102#1:200,2\n73#1:117\n73#1:118,10\n73#1:129,68\n73#1:128\n73#1:197\n*E\n"})
/* loaded from: classes.dex */
public class ConflatedBufferedChannel<E> extends BufferedChannel<E> {
    private final int capacity;
    private final BufferOverflow onBufferOverflow;

    public ConflatedBufferedChannel(int r1, BufferOverflow r2, Function1<? super E, Unit> r3) {
        super(r1, r3);
        this.capacity = r1;
        this.onBufferOverflow = r2;
        if (r2 != BufferOverflow.SUSPEND) goto L5;
        Request$Builder$$ExternalSyntheticBUOutline0.m("This implementation does not support suspension for senders, use ", Reflection.getOrCreateKotlinClass(BufferedChannel.class).getSimpleName(), " instead");
        throw null;
    L5:
        if (r1 < 1) goto L7;
        return;
    L7:
        SegmentedByteString$$ExternalSyntheticBUOutline0.m("Buffered channel capacity must be at least 1, but ", r1, " was specified");
        throw null;
    }

    public static /* synthetic */ <E> Object send$suspendImpl(ConflatedBufferedChannel<E> r2, E r3, Continuation<? super Unit> r4) {
        Object r42 = r2.m3550trySendImplMj0NB7M(r3, true);
        if ((r42 instanceof ChannelResult.Closed) == false) goto L13;
        ChannelResult.m3537exceptionOrNullimpl(r42);
        Function1<E, Unit> r43 = r2.onUndeliveredElement;
        if (r43 == null) goto L11;
        UndeliveredElementException r32 = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(r43, r3, null, 2, null);
        if (r32 == null) goto L11;
        ExceptionsKt.addSuppressed(r32, r2.getSendException());
        throw r32;
    L11:
        throw r2.getSendException();
    L13:
        return Unit.INSTANCE;
    }

    /* renamed from: trySendDropLatest-Mj0NB7M, reason: not valid java name */
    private final Object m3548trySendDropLatestMj0NB7M(E r3, boolean r4) {
        Object r0 = super.mo3532trySendJP2dKIU(r3);
        if (ChannelResult.m3542isSuccessimpl(r0) == false) goto L5;
    L16:
        return r0;
    L5:
        if (ChannelResult.m3541isClosedimpl(r0) == true) goto L16;
        if (r4 == false) goto L15;
        Function1<E, Unit> r2 = this.onUndeliveredElement;
        if (r2 == null) goto L15;
        UndeliveredElementException r22 = OnUndeliveredElementKt.callUndeliveredElementCatchingException$default(r2, r3, null, 2, null);
        if (r22 == null) goto L15;
        throw r22;
    L15:
        return ChannelResult.Companion.m3547successJP2dKIU(Unit.INSTANCE);
    }

    /* renamed from: trySendDropOldest-JP2dKIU, reason: not valid java name */
    private final Object m3549trySendDropOldestJP2dKIU(E r12) {
        Object r6 = BufferedChannelKt.BUFFERED;
        ChannelSegment r0 = (ChannelSegment) BufferedChannel.access$getSendSegment$volatile$FU().get(this);
    L3:
        long r1 = BufferedChannel.access$getSendersAndCloseStatus$volatile$FU().getAndIncrement(this);
        long r4 = r1 & 1152921504606846975L;
        boolean r7 = BufferedChannel.access$isClosedForSend0(this, r1);
        int r8 = BufferedChannelKt.SEGMENT_SIZE;
        long r13 = r4 / r8;
        int r3 = (int) (r4 % r8);
        if (r0.id == r13) goto L12;
        ChannelSegment r14 = BufferedChannel.access$findSegmentSend(this, r13, r0);
        if (r14 == null) goto L7;
        ConflatedBufferedChannel<E> r02 = this;
        int r2 = r3;
    L11:
        E r32 = r12;
        int r11 = BufferedChannel.access$updateCellSend(r02, r14, r2, r32, r4, r6, r7);
        ConflatedBufferedChannel<E> r122 = r02;
        r0 = r14;
        if (r11 == 0) goto L47;
        if (r11 == 1) goto L46;
        Waiter r9 = null;
        if (r11 == 2) goto L35;
        if (r11 == 3) goto L33;
        if (r11 == 4) goto L29;
        if (r11 != 5) goto L27;
        r0.cleanPrev();
    L27:
        ConflatedBufferedChannel<E> this = r122;
        r12 = r32;
        goto L3
    L29:
        if (r4 >= r122.getReceiversCounter$kotlinx_coroutines_core()) goto L32;
        r0.cleanPrev();
    L32:
        return ChannelResult.Companion.m3545closedJP2dKIU(r122.getSendException());
    L33:
        Segment$$ExternalSyntheticBUOutline1.m("unexpected");
        return null;
    L35:
        if (r7 == false) goto L39;
        r0.onSlotCleaned();
        return ChannelResult.Companion.m3545closedJP2dKIU(r122.getSendException());
    L39:
        if ((r6 instanceof Waiter) == false) goto L41;
        r9 = (Waiter) r6;
    L41:
        if (r9 == null) goto L43;
        BufferedChannel.access$prepareSenderForSuspension(r122, r9, r0, r2);
    L43:
        r122.dropFirstElementUntilTheSpecifiedCellIsInTheBuffer((r0.id * r8) + r2);
        return ChannelResult.Companion.m3547successJP2dKIU(Unit.INSTANCE);
    L46:
        return ChannelResult.Companion.m3547successJP2dKIU(Unit.INSTANCE);
    L47:
        r0.cleanPrev();
        return ChannelResult.Companion.m3547successJP2dKIU(Unit.INSTANCE);
    L7:
        if (r7 == false) goto L3;
        return ChannelResult.Companion.m3545closedJP2dKIU(this.getSendException());
    L12:
        r14 = r0;
        r2 = r3;
        r02 = this;
        goto L11
    }

    /* renamed from: trySendImpl-Mj0NB7M, reason: not valid java name */
    private final Object m3550trySendImplMj0NB7M(E r3, boolean r4) {
        if (this.onBufferOverflow != BufferOverflow.DROP_LATEST) goto L7;
        return m3548trySendDropLatestMj0NB7M(r3, r4);
    L7:
        return m3549trySendDropOldestJP2dKIU(r3);
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel
    public boolean isConflatedDropOldest() {
        if (this.onBufferOverflow != BufferOverflow.DROP_OLDEST) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel, kotlinx.coroutines.channels.SendChannel
    public Object send(E r1, Continuation<? super Unit> r2) {
        return send$suspendImpl(this, r1, r2);
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel, kotlinx.coroutines.channels.SendChannel
    /* renamed from: trySend-JP2dKIU */
    public Object mo3532trySendJP2dKIU(E r2) {
        return m3550trySendImplMj0NB7M(r2, false);
    }
}
