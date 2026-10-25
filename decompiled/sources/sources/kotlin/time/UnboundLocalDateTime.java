package kotlin.time;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.signature.SignatureVisitor;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0002\u0018\u0000 !2\u00020\u0001:\u0001!BA\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJX\u0010\u0014\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\u00152\u0006\u0010\u0016\u001a\u00020\u000326\u0010\u0017\u001a2\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u0002H\u00150\u0018H\u0086\u0088\u0004ø\u0001\u0000¢\u0006\u0002\u0010\u001eJ\n\u0010\u001f\u001a\u00020 H\u0096\u0080\u0004R\u0015\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0004\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0015\u0010\u0005\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0015\u0010\u0006\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0015\u0010\u0007\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0015\u0010\b\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0015\u0010\t\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\""}, d2 = {"Lkotlin/time/UnboundLocalDateTime;", _UrlKt.FRAGMENT_ENCODE_SET, "year", _UrlKt.FRAGMENT_ENCODE_SET, "month", "day", "hour", "minute", "second", "nanosecond", "<init>", "(IIIIIII)V", "getYear", "()I", "getMonth", "getDay", "getHour", "getMinute", "getSecond", "getNanosecond", "toInstant", "T", "offsetSeconds", "buildInstant", "Lkotlin/Function2;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ParameterName;", "name", "epochSeconds", "nanosecondOfSecond", "(ILkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
final class UnboundLocalDateTime {
    public static final Companion Companion = null;
    private final int day;
    private final int hour;
    private final int minute;
    private final int month;
    private final int nanosecond;
    private final int second;
    private final int year;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0086\u0080\u0004¨\u0006\b"}, d2 = {"Lkotlin/time/UnboundLocalDateTime$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "fromInstant", "Lkotlin/time/UnboundLocalDateTime;", "instant", "Lkotlin/time/Instant;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final UnboundLocalDateTime fromInstant(Instant r25) {
            long r0 = r25.getEpochSeconds();
            long r4 = r0 / 86400;
            if ((r0 ^ 86400) < 0) goto L5;
        L7:
            long r02 = r0 % 86400;
            int r03 = (int) (r02 + (86400 & (((r02 ^ 86400) & ((-r02) | r02)) >> 63)));
            long r1 = 719468 + r4;
            if (r1 >= 0) goto L10;
            long r42 = ((r4 + 719469) / 146097) - 1;
            long r14 = r42 * 400;
            r1 = r1 + ((-r42) * 146097);
        L11:
            long r3 = ((400 * r1) + 591) / 146097;
            long r16 = r1 - ((((365 * r3) + (r3 / 4)) - (r3 / 100)) + (r3 / 400));
            if (r16 >= 0) goto L14;
            r3 = r3 - 1;
            r16 = r1 - ((((365 * r3) + (r3 / 4)) - (r3 / 100)) + (r3 / 400));
        L14:
            int r12 = (int) r16;
            int r2 = ((r12 * 5) + 2) / 153;
            int r10 = r03 / 3600;
            int r04 = r03 - (r10 * 3600);
            int r11 = r04 / 60;
            return new UnboundLocalDateTime((int) ((r3 + r14) + (r2 / 10)), ((r2 + 2) % 12) + 1, (r12 - (((r2 * 306) + 5) / 10)) + 1, r10, r11, r04 - (r11 * 60), r25.getNanosecondsOfSecond());
        L10:
            r14 = 0;
            goto L11
        L5:
            if ((r4 * 86400) == r0) goto L7;
            r4 = r4 - 1;
            goto L7
        }
    }

    static {
        Companion = new Companion(null);
    }

    public UnboundLocalDateTime(int r1, int r2, int r3, int r4, int r5, int r6, int r7) {
        this.year = r1;
        this.month = r2;
        this.day = r3;
        this.hour = r4;
        this.minute = r5;
        this.second = r6;
        this.nanosecond = r7;
    }

    public final int getDay() {
        return this.day;
    }

    public final int getHour() {
        return this.hour;
    }

    public final int getMinute() {
        return this.minute;
    }

    public final int getMonth() {
        return this.month;
    }

    public final int getNanosecond() {
        return this.nanosecond;
    }

    public final int getSecond() {
        return this.second;
    }

    public final int getYear() {
        return this.year;
    }

    public final <T> T toInstant(int r11, Function2<? super Long, ? super Integer, ? extends T> r12) {
        long r0 = getYear();
        long r2 = 365 * r0;
        if (r0 < 0) goto L5;
        long r22 = r2 + ((((3 + r0) / 4) - ((99 + r0) / 100)) + ((r0 + 399) / 400));
    L6:
        long r23 = (r22 + (((getMonth() * 367) - 362) / 12)) + (getDay() - 1);
        if (getMonth() <= 2) goto L13;
        long r02 = (-1) + r23;
        if (InstantKt.isLeapYear(getYear()) == true) goto L11;
        r23 = r23 - 2;
        goto L13
    L11:
        r23 = r02;
    L13:
        return r12.invoke(Long.valueOf((((r23 - 719528) * 86400) + (((getHour() * 3600) + (getMinute() * 60)) + getSecond())) - r11), Integer.valueOf(getNanosecond()));
    L5:
        r22 = r2 - (((r0 / (-4)) - (r0 / (-100))) + (r0 / (-400)));
        goto L6
    }

    public String toString() {
        return "UnboundLocalDateTime(" + this.year + SignatureVisitor.SUPER + this.month + SignatureVisitor.SUPER + this.day + ' ' + this.hour + ':' + this.minute + ':' + this.second + '.' + this.nanosecond + ')';
    }
}
