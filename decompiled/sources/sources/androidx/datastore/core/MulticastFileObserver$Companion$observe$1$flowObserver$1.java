package androidx.datastore.core;

import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ProducerScope;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "fileName", _UrlKt.FRAGMENT_ENCODE_SET, "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MulticastFileObserver$Companion$observe$1$flowObserver$1 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ ProducerScope<Unit> $$this$channelFlow;
    final /* synthetic */ File $file;

    /* JADX WARN: Multi-variable type inference failed */
    public MulticastFileObserver$Companion$observe$1$flowObserver$1(File r1, ProducerScope<? super Unit> r2) {
        this.$file = r1;
        this.$$this$channelFlow = r2;
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String r1) {
        invoke2(r1);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(String r2) {
        if (Intrinsics.areEqual(r2, this.$file.getName()) == false) goto L6;
        ChannelsKt.trySendBlocking(this.$$this$channelFlow, Unit.INSTANCE);
        return;
    }
}
