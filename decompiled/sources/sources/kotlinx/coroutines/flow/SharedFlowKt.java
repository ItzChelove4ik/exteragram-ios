package kotlinx.coroutines.flow;

import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.ChannelFlowOperatorImpl;
import kotlinx.coroutines.internal.Symbol;
import okhttp3.internal.url._UrlKt;
import okio.Options$Companion$$ExternalSyntheticBUOutline0;
import okio.Utf8$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a0\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u001a#\u0010\n\u001a\u0004\u0018\u00010\u000b*\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002¢\u0006\u0002\u0010\u000f\u001a+\u0010\u0010\u001a\u00020\u0011*\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000bH\u0002¢\u0006\u0002\u0010\u0013\u001a6\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0015\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\"\u0010\u0010\b\u001a\u00020\t8\u0000X\u0081\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"MutableSharedFlow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "T", "replay", _UrlKt.FRAGMENT_ENCODE_SET, "extraBufferCapacity", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "NO_VALUE", "Lkotlinx/coroutines/internal/Symbol;", "getBufferAt", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "index", _UrlKt.FRAGMENT_ENCODE_SET, "([Ljava/lang/Object;J)Ljava/lang/Object;", "setBufferAt", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.ITEM, "([Ljava/lang/Object;JLjava/lang/Object;)V", "fuseSharedFlow", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/SharedFlow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSharedFlow.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SharedFlow.kt\nkotlinx/coroutines/flow/SharedFlowKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,741:1\n1#2:742\n*E\n"})
/* loaded from: classes5.dex */
public abstract class SharedFlowKt {

    @JvmField
    public static final Symbol NO_VALUE = null;

    static {
        NO_VALUE = new Symbol("NO_VALUE");
    }

    public static final <T> MutableSharedFlow<T> MutableSharedFlow(int r1, int r2, BufferOverflow r3) {
        if (r1 < 0) goto L18;
        if (r2 < 0) goto L17;
        if (r1 > 0) goto L12;
        if (r2 > 0) goto L12;
        if (r3 == BufferOverflow.SUSPEND) goto L12;
        Options$Companion$$ExternalSyntheticBUOutline0.m("replay or extraBufferCapacity must be positive with non-default onBufferOverflow strategy ", r3);
        return null;
    L12:
        int r22 = r2 + r1;
        if (r22 >= 0) goto L16;
        r22 = Integer.MAX_VALUE;
    L16:
        return new SharedFlowImpl(r1, r22, r3);
    L17:
        Utf8$$ExternalSyntheticBUOutline1.m("extraBufferCapacity cannot be negative, but was ", r2);
        return null;
    L18:
        Utf8$$ExternalSyntheticBUOutline1.m("replay cannot be negative, but was ", r1);
        return null;
    }

    public static /* synthetic */ MutableSharedFlow MutableSharedFlow$default(int r1, int r2, BufferOverflow r3, int r4, Object r5) {
        if ((r4 & 1) == 0) goto L6;
        r1 = 0;
    L6:
        if ((r4 & 2) == 0) goto L9;
        r2 = 0;
    L9:
        if ((r4 & 4) == 0) goto L12;
        r3 = BufferOverflow.SUSPEND;
    L12:
        return MutableSharedFlow(r1, r2, r3);
    }

    public static final /* synthetic */ Object access$getBufferAt(Object[] r0, long r1) {
        return getBufferAt(r0, r1);
    }

    public static final /* synthetic */ void access$setBufferAt(Object[] r0, long r1, Object r3) {
        setBufferAt(r0, r1, r3);
    }

    public static final <T> Flow<T> fuseSharedFlow(SharedFlow<? extends T> r1, CoroutineContext r2, int r3, BufferOverflow r4) {
        if (r3 == 0) goto L6;
        if (r3 == (-3)) goto L6;
    L9:
        return new ChannelFlowOperatorImpl(r1, r2, r3, r4);
    L6:
        if (r4 != BufferOverflow.SUSPEND) goto L9;
        return r1;
    }

    private static final Object getBufferAt(Object[] r0, long r1) {
        return r0[((int) r1) & (r0.length - 1)];
    }

    private static final void setBufferAt(Object[] r0, long r1, Object r3) {
        r0[((int) r1) & (r0.length - 1)] = r3;
    }
}
