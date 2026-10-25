package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bæ\u0080\u0001\u0018\u0000 \b2\u00020\u0001:\u0001\bJ\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H&¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lkotlinx/coroutines/flow/SharingStarted;", _UrlKt.FRAGMENT_ENCODE_SET, "command", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/SharingCommand;", "subscriptionCount", "Lkotlinx/coroutines/flow/StateFlow;", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public interface SharingStarted {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/flow/SharingStarted$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Eagerly", "Lkotlinx/coroutines/flow/SharingStarted;", "getEagerly", "()Lkotlinx/coroutines/flow/SharingStarted;", "Lazily", "getLazily", "WhileSubscribed", "stopTimeoutMillis", _UrlKt.FRAGMENT_ENCODE_SET, "replayExpirationMillis", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = null;
        private static final SharingStarted Eagerly = null;
        private static final SharingStarted Lazily = null;

        static {
            $$INSTANCE = new Companion();
            Eagerly = new StartedEagerly();
            Lazily = new StartedLazily();
        }

        private Companion() {
        }

        public static /* synthetic */ SharingStarted WhileSubscribed$default(Companion r0, long r1, long r3, int r5, Object r6) {
            if ((r5 & 1) == 0) goto L6;
            r1 = 0;
        L6:
            if ((r5 & 2) == 0) goto L9;
            r3 = LongCompanionObject.MAX_VALUE;
        L9:
            return r0.WhileSubscribed(r1, r3);
        }

        public final SharingStarted WhileSubscribed(long r1, long r3) {
            return new StartedWhileSubscribed(r1, r3);
        }

        public final SharingStarted getEagerly() {
            return Eagerly;
        }

        public final SharingStarted getLazily() {
            return Lazily;
        }
    }

    static {
        Companion = Companion.$$INSTANCE;
    }

    Flow<SharingCommand> command(StateFlow<Integer> r1);
}
