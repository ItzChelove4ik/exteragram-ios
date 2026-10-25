package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.PublishedApi;

@Metadata(d1 = {"kotlinx/coroutines/channels/ChannelsKt__ChannelsKt", "kotlinx/coroutines/channels/ChannelsKt__Channels_commonKt"}, d2 = {}, k = 4, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class ChannelsKt {
    @PublishedApi
    public static final void cancelConsumed(ReceiveChannel<?> r0, Throwable r1) {
        ChannelsKt__Channels_commonKt.cancelConsumed(r0, r1);
    }

    public static final <E> Object trySendBlocking(SendChannel<? super E> r0, E r1) {
        return ChannelsKt__ChannelsKt.trySendBlocking(r0, r1);
    }
}
