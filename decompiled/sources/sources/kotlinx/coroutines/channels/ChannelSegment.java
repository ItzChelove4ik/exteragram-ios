package kotlinx.coroutines.channels;

import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.internal.measurement.zzpo$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.time.DurationKt$$ExternalSyntheticBUOutline0;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.internal.OnUndeliveredElementKt;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.Symbol;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00000\u0002B7\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ!\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u001f\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00028\u0000H\u0000¢\u0006\u0004\b\u0013\u0010\u0011J\u0017\u0010\u0017\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\bH\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0019\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\bH\u0000¢\u0006\u0004\b\u0018\u0010\u0016J\u0017\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\bH\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ\u0019\u0010\u001e\u001a\u0004\u0018\u00010\r2\u0006\u0010\f\u001a\u00020\bH\u0000¢\u0006\u0004\b\u001d\u0010\u0016J!\u0010 \u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0000¢\u0006\u0004\b\u001f\u0010\u0011J+\u0010&\u001a\u00020#2\u0006\u0010\f\u001a\u00020\b2\b\u0010!\u001a\u0004\u0018\u00010\r2\b\u0010\"\u001a\u0004\u0018\u00010\rH\u0000¢\u0006\u0004\b$\u0010%J#\u0010*\u001a\u0004\u0018\u00010\r2\u0006\u0010\f\u001a\u00020\b2\b\u0010'\u001a\u0004\u0018\u00010\rH\u0000¢\u0006\u0004\b(\u0010)J)\u0010/\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\b2\b\u0010,\u001a\u0004\u0018\u00010+2\u0006\u0010.\u001a\u00020-H\u0016¢\u0006\u0004\b/\u00100J\u001d\u00102\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\b2\u0006\u00101\u001a\u00020#¢\u0006\u0004\b2\u00103R\u001c\u00104\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00105R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u00068F¢\u0006\u0006\u001a\u0004\b6\u00107R\u0014\u0010:\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b8\u00109R\u0013\u0010;\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\r8\u0002X\u0082\u0004¨\u0006<"}, d2 = {"Lkotlinx/coroutines/channels/ChannelSegment;", "E", "Lkotlinx/coroutines/internal/Segment;", _UrlKt.FRAGMENT_ENCODE_SET, "id", "prev", "Lkotlinx/coroutines/channels/BufferedChannel;", "channel", _UrlKt.FRAGMENT_ENCODE_SET, "pointers", "<init>", "(JLkotlinx/coroutines/channels/ChannelSegment;Lkotlinx/coroutines/channels/BufferedChannel;I)V", "index", _UrlKt.FRAGMENT_ENCODE_SET, "value", _UrlKt.FRAGMENT_ENCODE_SET, "setElementLazy", "(ILjava/lang/Object;)V", "element", "storeElement$kotlinx_coroutines_core", "storeElement", "getElement$kotlinx_coroutines_core", "(I)Ljava/lang/Object;", "getElement", "retrieveElement$kotlinx_coroutines_core", "retrieveElement", "cleanElement$kotlinx_coroutines_core", "(I)V", "cleanElement", "getState$kotlinx_coroutines_core", "getState", "setState$kotlinx_coroutines_core", "setState", "from", "to", _UrlKt.FRAGMENT_ENCODE_SET, "casState$kotlinx_coroutines_core", "(ILjava/lang/Object;Ljava/lang/Object;)Z", "casState", PluginsConstants.UPDATE, "getAndSetState$kotlinx_coroutines_core", "(ILjava/lang/Object;)Ljava/lang/Object;", "getAndSetState", _UrlKt.FRAGMENT_ENCODE_SET, "cause", "Lkotlin/coroutines/CoroutineContext;", "context", "onCancellation", "(ILjava/lang/Throwable;Lkotlin/coroutines/CoroutineContext;)V", "receiver", "onCancelledRequest", "(IZ)V", "_channel", "Lkotlinx/coroutines/channels/BufferedChannel;", "getChannel", "()Lkotlinx/coroutines/channels/BufferedChannel;", "getNumberOfSlots", "()I", "numberOfSlots", "data", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nBufferedChannel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BufferedChannel.kt\nkotlinx/coroutines/channels/ChannelSegment\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,3092:1\n1#2:3093\n*E\n"})
/* loaded from: classes.dex */
public final class ChannelSegment<E> extends Segment<ChannelSegment<E>> {
    private final BufferedChannel<E> _channel;
    private final /* synthetic */ AtomicReferenceArray data;

    public ChannelSegment(long r1, ChannelSegment<E> r3, BufferedChannel<E> r4, int r5) {
        super(r1, r3, r5);
        this._channel = r4;
        this.data = new AtomicReferenceArray(BufferedChannelKt.SEGMENT_SIZE * 2);
    }

    private final /* synthetic */ AtomicReferenceArray getData() {
        return this.data;
    }

    private final void setElementLazy(int r1, Object r2) {
        getData().set(r1 * 2, r2);
    }

    public final boolean casState$kotlinx_coroutines_core(int r1, Object r2, Object r3) {
        return zzpo$$ExternalSyntheticBackportWithForwarding0.m(getData(), (r1 * 2) + 1, r2, r3);
    }

    public final void cleanElement$kotlinx_coroutines_core(int r2) {
        setElementLazy(r2, null);
    }

    public final Object getAndSetState$kotlinx_coroutines_core(int r1, Object r2) {
        return getData().getAndSet((r1 * 2) + 1, r2);
    }

    public final BufferedChannel<E> getChannel() {
        return this._channel;
    }

    public final E getElement$kotlinx_coroutines_core(int r1) {
        return (E) getData().get(r1 * 2);
    }

    @Override // kotlinx.coroutines.internal.Segment
    public int getNumberOfSlots() {
        return BufferedChannelKt.SEGMENT_SIZE;
    }

    public final Object getState$kotlinx_coroutines_core(int r1) {
        return getData().get((r1 * 2) + 1);
    }

    @Override // kotlinx.coroutines.internal.Segment
    public void onCancellation(int r4, Throwable r5, CoroutineContext r6) {
        int r52 = BufferedChannelKt.SEGMENT_SIZE;
        if (r4 < r52) goto L5;
        boolean r0 = true;
    L6:
        if (r0 == false) goto L8;
        r4 = r4 - r52;
    L8:
        E r53 = getElement$kotlinx_coroutines_core(r4);
    L9:
        Object r1 = getState$kotlinx_coroutines_core(r4);
        if ((r1 instanceof Waiter) == true) goto L40;
        if ((r1 instanceof WaiterEB) == true) goto L40;
        if (r1 == BufferedChannelKt.access$getINTERRUPTED_SEND$p()) goto L34;
        if (r1 == BufferedChannelKt.access$getINTERRUPTED_RCV$p()) goto L34;
        if (r1 == BufferedChannelKt.access$getRESUMING_BY_EB$p()) goto L9;
        if (r1 == BufferedChannelKt.access$getRESUMING_BY_RCV$p()) goto L9;
        if (r1 != BufferedChannelKt.access$getDONE_RCV$p()) goto L27;
        return;
    L27:
        if (r1 != BufferedChannelKt.BUFFERED) goto L30;
        return;
    L30:
        if (r1 == BufferedChannelKt.getCHANNEL_CLOSED()) goto L69;
        DurationKt$$ExternalSyntheticBUOutline0.m("unexpected state: ", r1);
        return;
    L69:
        return;
    L34:
        cleanElement$kotlinx_coroutines_core(r4);
        if (r0 == false) goto L64;
        Function1<E, Unit> r3 = getChannel().onUndeliveredElement;
        if (r3 == null) goto L65;
        OnUndeliveredElementKt.callUndeliveredElement(r3, r53, r6);
        return;
    L65:
        return;
    L64:
        return;
    L40:
        if (r0 == false) goto L42;
        Symbol r2 = BufferedChannelKt.access$getINTERRUPTED_SEND$p();
    L44:
        if (casState$kotlinx_coroutines_core(r4, r1, r2) == false) goto L9;
        cleanElement$kotlinx_coroutines_core(r4);
        onCancelledRequest(r4, !r0);
        if (r0 == false) goto L66;
        Function1<E, Unit> r32 = getChannel().onUndeliveredElement;
        if (r32 == null) goto L67;
        OnUndeliveredElementKt.callUndeliveredElement(r32, r53, r6);
        return;
    L67:
        return;
    L66:
        return;
    L42:
        r2 = BufferedChannelKt.access$getINTERRUPTED_RCV$p();
        goto L44
    L5:
        r0 = false;
        goto L6
    }

    public final void onCancelledRequest(int r5, boolean r6) {
        if (r6 == false) goto L4;
        getChannel().waitExpandBufferCompletion$kotlinx_coroutines_core((this.id * BufferedChannelKt.SEGMENT_SIZE) + r5);
    L4:
        onSlotCleaned();
    }

    public final E retrieveElement$kotlinx_coroutines_core(int r2) {
        E r0 = getElement$kotlinx_coroutines_core(r2);
        cleanElement$kotlinx_coroutines_core(r2);
        return r0;
    }

    public final void setState$kotlinx_coroutines_core(int r1, Object r2) {
        getData().set((r1 * 2) + 1, r2);
    }

    public final void storeElement$kotlinx_coroutines_core(int r1, E r2) {
        setElementLazy(r1, r2);
    }
}
