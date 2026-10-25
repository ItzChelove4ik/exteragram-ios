package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.ExceptionsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a!\u0010\u0004\u001a\u00020\u0003*\u0006\u0012\u0002\b\u00030\u00002\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u0001¢\u0006\u0004\b\u0004\u0010\u0005\"\u0014\u0010\u0007\u001a\u00020\u00068\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/channels/ReceiveChannel;", _UrlKt.FRAGMENT_ENCODE_SET, "cause", _UrlKt.FRAGMENT_ENCODE_SET, "cancelConsumed", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Throwable;)V", _UrlKt.FRAGMENT_ENCODE_SET, "DEFAULT_CLOSE_MESSAGE", "Ljava/lang/String;", "kotlinx-coroutines-core"}, k = 5, mv = {2, 2, 0}, xi = 48, xs = "kotlinx/coroutines/channels/ChannelsKt")
@SourceDebugExtension({"SMAP\nChannels.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Channels.common.kt\nkotlinx/coroutines/channels/ChannelsKt__Channels_commonKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,272:1\n97#1,8:273\n156#1:281\n97#1,3:282\n157#1,2:285\n104#1:287\n100#1,3:288\n156#1:291\n97#1,3:292\n157#1,2:295\n104#1:297\n100#1,3:298\n1#2:301\n*S KotlinDebug\n*F\n+ 1 Channels.common.kt\nkotlinx/coroutines/channels/ChannelsKt__Channels_commonKt\n*L\n156#1:273,8\n199#1:281\n199#1:282,3\n199#1:285,2\n199#1:287\n199#1:288,3\n262#1:291\n262#1:292,3\n262#1:295,2\n262#1:297\n262#1:298,3\n*E\n"})
/* loaded from: classes5.dex */
abstract /* synthetic */ class ChannelsKt__Channels_commonKt {
    @PublishedApi
    public static final void cancelConsumed(ReceiveChannel<?> r2, Throwable r3) {
        CancellationException r0 = null;
        if (r3 != null) goto L5;
    L9:
        r2.cancel(r0);
        return;
    L5:
        if ((r3 instanceof CancellationException) == false) goto L7;
        r0 = (CancellationException) r3;
    L7:
        if (r0 != null) goto L9;
        r0 = ExceptionsKt.CancellationException("Channel was consumed, consumer had failed", r3);
        goto L9
    }
}
