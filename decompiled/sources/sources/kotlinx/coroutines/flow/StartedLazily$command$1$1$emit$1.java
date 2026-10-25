package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.telegram.messenger.NotificationBadge;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "kotlinx.coroutines.flow.StartedLazily$command$1$1", f = "SharingStarted.kt", i = {0}, l = {155}, m = "emit", n = {NotificationBadge.NewHtcHomeBadger.COUNT}, s = {"I$0"}, v = 1)
/* loaded from: classes5.dex */
public final class StartedLazily$command$1$1$emit$1 extends ContinuationImpl {
    int I$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ StartedLazily$command$1$1<T> this$0;

    /* JADX WARN: Multi-variable type inference failed */
    public StartedLazily$command$1$1$emit$1(StartedLazily$command$1$1<? super T> r1, Continuation<? super StartedLazily$command$1$1$emit$1> r2) {
        this.this$0 = r1;
        super(r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r2) {
        this.result = r2;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit(0, this);
    }
}
