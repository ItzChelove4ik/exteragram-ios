package kotlin.uuid;

import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.time.Clock;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0087\u0080\u0004R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0006\u001a\u00020\u0005X\u0082Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\u00020\bX\u0082Ô\b¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\nX\u0082\u0084\b¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006\u0010"}, d2 = {"Lkotlin/uuid/UuidV7Generator;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TIMESTAMP_BIAS_BITS", _UrlKt.FRAGMENT_ENCODE_SET, "VERSION_MASK", "OVERFLOW_MASK", _UrlKt.FRAGMENT_ENCODE_SET, "timestampAndCounter", "Lkotlin/concurrent/atomics/AtomicLong;", "Ljava/util/concurrent/atomic/AtomicLong;", "generate", "Lkotlin/uuid/Uuid;", "clock", "Lkotlin/time/Clock;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
final class UuidV7Generator {
    public static final UuidV7Generator INSTANCE = null;
    private static final long OVERFLOW_MASK = 32768;
    private static final int TIMESTAMP_BIAS_BITS = 16;
    private static final int VERSION_MASK = 28672;
    private static final AtomicLong timestampAndCounter = null;

    static {
        INSTANCE = new UuidV7Generator();
        timestampAndCounter = new AtomicLong(0);
    }

    private UuidV7Generator() {
    }

    @ExperimentalUuidApi
    public final Uuid generate(Clock r17) {
        byte[] r0 = new byte[10];
        UuidKt__UuidJVMKt.secureRandomBytes(r0);
        int r1 = (((r0[8] & 7) << 8) | (r0[9] & UByte.MAX_VALUE)) | VERSION_MASK;
    L3:
        AtomicLong r2 = timestampAndCounter;
        long r3 = r2.get();
        long r5 = r17.now().toEpochMilliseconds();
        long r8 = r3 >>> 16;
        if (r8 < r5) goto L5;
        long r10 = r3 + 1;
        if ((32768 & r10) == 0) goto L11;
        long r52 = ((r8 + 1) << 16) | r1;
    L13:
        if (r2.compareAndSet(r3, r52) == false) goto L3;
    L14:
        r0[0] = (byte) (((byte) (r0[0] & 63)) | ByteCompanionObject.MIN_VALUE);
        return Uuid.Companion.fromLongs(r52, UuidKt__UuidJVMKt.getLongAt(r0, 0));
    L11:
        r52 = r10;
        goto L13
    L5:
        r52 = (r5 << 16) | r1;
        if (r2.compareAndSet(r3, r52) == false) goto L3;
        goto L14
    }
}
