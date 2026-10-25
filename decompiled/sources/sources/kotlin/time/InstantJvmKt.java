package kotlin.time;

import kotlin.Metadata;
import kotlin.internal.PlatformImplementationsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a\n\u0010\u0002\u001a\u00020\u0003H\u0080\u0080\u0004\u001a\u0012\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H\u0080\u0080\u0004\"\u000f\u0010\u0000\u001a\u00020\u0001X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"systemClock", "Lkotlin/time/Clock;", "systemClockNow", "Lkotlin/time/Instant;", "serializedInstant", _UrlKt.FRAGMENT_ENCODE_SET, "instant", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class InstantJvmKt {
    private static final Clock systemClock = null;

    static {
        systemClock = PlatformImplementationsKt.IMPLEMENTATIONS.getSystemClock();
    }

    public static final Object serializedInstant(Instant r3) {
        return new InstantSerialized(r3.getEpochSeconds(), r3.getNanosecondsOfSecond());
    }

    public static final Instant systemClockNow() {
        return systemClock.now();
    }
}
