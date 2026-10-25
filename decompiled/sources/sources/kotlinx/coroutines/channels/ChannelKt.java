package kotlinx.coroutines.channels;

import c.f$$ExternalSyntheticBUOutline1;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aE\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\b\"\u0004\b\u0000\u0010\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"E", _UrlKt.FRAGMENT_ENCODE_SET, "capacity", "Lkotlinx/coroutines/channels/BufferOverflow;", "onBufferOverflow", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "onUndeliveredElement", "Lkotlinx/coroutines/channels/Channel;", "Channel", "(ILkotlinx/coroutines/channels/BufferOverflow;Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/channels/Channel;", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class ChannelKt {
    public static final <E> Channel<E> Channel(int r2, BufferOverflow r3, Function1<? super E, Unit> r4) {
        if (r2 == (-2)) goto L30;
        if (r2 == (-1)) goto L24;
        if (r2 == 0) goto L18;
        if (r2 == Integer.MAX_VALUE) goto L16;
        if (r3 != BufferOverflow.SUSPEND) goto L14;
        return new BufferedChannel(r2, r4);
    L14:
        return new ConflatedBufferedChannel(r2, r3, r4);
    L16:
        return new BufferedChannel(Integer.MAX_VALUE, r4);
    L18:
        if (r3 != BufferOverflow.SUSPEND) goto L22;
        return new BufferedChannel(0, r4);
    L22:
        return new ConflatedBufferedChannel(1, r3, r4);
    L24:
        if (r3 == BufferOverflow.SUSPEND) goto L26;
        f$$ExternalSyntheticBUOutline1.m("CONFLATED capacity cannot be used with non-default onBufferOverflow");
        return null;
    L26:
        return new ConflatedBufferedChannel(1, BufferOverflow.DROP_OLDEST, r4);
    L30:
        if (r3 != BufferOverflow.SUSPEND) goto L34;
        return new BufferedChannel(Channel.Factory.getCHANNEL_DEFAULT_CAPACITY$kotlinx_coroutines_core(), r4);
    L34:
        return new ConflatedBufferedChannel(1, r3, r4);
    }

    public static /* synthetic */ Channel Channel$default(int r0, BufferOverflow r1, Function1 r2, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r0 = 0;
    L6:
        if ((r3 & 2) == 0) goto L9;
        r1 = BufferOverflow.SUSPEND;
    L9:
        if ((r3 & 4) == 0) goto L12;
        r2 = null;
    L12:
        return Channel(r0, r1, r2);
    }
}
