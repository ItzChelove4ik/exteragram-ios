package kotlin.time;

import com.sun.jna.Union$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.JvmInline;
import kotlin.time.ComparableTimeMark;
import okhttp3.internal.url._UrlKt;

@SinceKotlin(version = "1.9")
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u0000 \u00062\u00020\u0001:\u0003\u0004\u0005\u0006J\n\u0010\u0002\u001a\u00020\u0003H¦\u0080\u0004¨\u0006\u0007"}, d2 = {"Lkotlin/time/TimeSource;", _UrlKt.FRAGMENT_ENCODE_SET, "markNow", "Lkotlin/time/TimeMark;", "WithComparableMarks", "Monotonic", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@WasExperimental(markerClass = {ExperimentalTime.class})
/* loaded from: classes5.dex */
public interface TimeSource {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lkotlin/time/TimeSource$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = null;

        static {
            $$INSTANCE = new Companion();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\nB\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0004\u001a\u00020\u0005H\u0096\u0080\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\b\u001a\u00020\tH\u0096\u0080\u0004¨\u0006\u000b"}, d2 = {"Lkotlin/time/TimeSource$Monotonic;", "Lkotlin/time/TimeSource$WithComparableMarks;", "<init>", "()V", "markNow", "Lkotlin/time/TimeSource$Monotonic$ValueTimeMark;", "markNow-z9LOYto", "()J", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "ValueTimeMark", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Monotonic implements WithComparableMarks {
        public static final Monotonic INSTANCE = null;

        @SinceKotlin(version = "1.9")
        @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0087@\u0018\u00002\u00020\u0001B\u0015\b@\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\b\u001a\u00020\tH\u0096\u0080\u0004¢\u0006\u0004\b\n\u0010\u0006J\u0019\u0010\u000b\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\tH\u0096\u0082\u0004¢\u0006\u0004\b\r\u0010\u000eJ\u0019\u0010\u000f\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\tH\u0096\u0082\u0004¢\u0006\u0004\b\u0010\u0010\u000eJ\u0011\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004¢\u0006\u0004\b\u0013\u0010\u0014J\u0011\u0010\u0015\u001a\u00020\u0012H\u0096\u0080\u0004¢\u0006\u0004\b\u0016\u0010\u0014J\u0019\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0001H\u0096\u0082\u0004¢\u0006\u0004\b\u0018\u0010\u0019J\u0019\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0000H\u0086\u0082\u0004¢\u0006\u0004\b\u001a\u0010\u000eJ\u0019\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u0000H\u0086\u0082\u0004¢\u0006\u0004\b\u001d\u0010\u001eJ\u0014\u0010\u001f\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010 HÖ\u0083\u0004J\n\u0010!\u001a\u00020\u001cHÖ\u0081\u0004J\n\u0010\"\u001a\u00020#HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0080\u0084\b¢\u0006\u0004\n\u0002\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00060\u0003j\u0002`\u0004¨\u0006$"}, d2 = {"Lkotlin/time/TimeSource$Monotonic$ValueTimeMark;", "Lkotlin/time/ComparableTimeMark;", "reading", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/time/ValueTimeMarkReading;", "constructor-impl", "(J)J", "J", "elapsedNow", "Lkotlin/time/Duration;", "elapsedNow-UwyO8pc", "plus", "duration", "plus-LRDsOJo", "(JJ)J", "minus", "minus-LRDsOJo", "hasPassedNow", _UrlKt.FRAGMENT_ENCODE_SET, "hasPassedNow-impl", "(J)Z", "hasNotPassedNow", "hasNotPassedNow-impl", "other", "minus-UwyO8pc", "(JLkotlin/time/ComparableTimeMark;)J", "minus-6eNON_k", "compareTo", _UrlKt.FRAGMENT_ENCODE_SET, "compareTo-6eNON_k", "(JJ)I", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
        @JvmInline
        @WasExperimental(markerClass = {ExperimentalTime.class})
        public static final class ValueTimeMark implements ComparableTimeMark {
            private final long reading;

            private /* synthetic */ ValueTimeMark(long r1) {
                this.reading = r1;
            }

            /* renamed from: box-impl, reason: not valid java name */
            public static final /* synthetic */ ValueTimeMark m3494boximpl(long r1) {
                return new ValueTimeMark(r1);
            }

            /* renamed from: compareTo-6eNON_k, reason: not valid java name */
            public static final int m3495compareTo6eNON_k(long r0, long r2) {
                return Duration.m3374compareToLRDsOJo(m3504minus6eNON_k(r0, r2), Duration.Companion.m3470getZEROUwyO8pc());
            }

            /* renamed from: compareTo-impl, reason: not valid java name */
            public static int m3496compareToimpl(long r0, ComparableTimeMark r2) {
                return m3494boximpl(r0).compareTo2(r2);
            }

            /* renamed from: constructor-impl, reason: not valid java name */
            public static long m3497constructorimpl(long r0) {
                return r0;
            }

            /* renamed from: elapsedNow-UwyO8pc, reason: not valid java name */
            public static long m3498elapsedNowUwyO8pc(long r1) {
                return MonotonicTimeSource.INSTANCE.m3487elapsedFrom6eNON_k(r1);
            }

            /* renamed from: equals-impl, reason: not valid java name */
            public static boolean m3499equalsimpl(long r4, Object r6) {
                if ((r6 instanceof ValueTimeMark) == true) goto L6;
                return false;
            L6:
                if (r4 == ((ValueTimeMark) r6).m3511unboximpl()) goto L8;
                return false;
            L8:
                return true;
            }

            /* renamed from: equals-impl0, reason: not valid java name */
            public static final boolean m3500equalsimpl0(long r0, long r2) {
                if (r0 != r2) goto L6;
                return true;
            L6:
                return false;
            }

            /* renamed from: hasNotPassedNow-impl, reason: not valid java name */
            public static boolean m3501hasNotPassedNowimpl(long r0) {
                return Duration.m3401isNegativeimpl(m3498elapsedNowUwyO8pc(r0));
            }

            /* renamed from: hasPassedNow-impl, reason: not valid java name */
            public static boolean m3502hasPassedNowimpl(long r0) {
                return !Duration.m3401isNegativeimpl(m3498elapsedNowUwyO8pc(r0));
            }

            /* renamed from: hashCode-impl, reason: not valid java name */
            public static int m3503hashCodeimpl(long r0) {
                return Long.hashCode(r0);
            }

            /* renamed from: minus-6eNON_k, reason: not valid java name */
            public static final long m3504minus6eNON_k(long r1, long r3) {
                return MonotonicTimeSource.INSTANCE.m3486differenceBetweenfRLX17w(r1, r3);
            }

            /* renamed from: minus-LRDsOJo, reason: not valid java name */
            public static long m3505minusLRDsOJo(long r1, long r3) {
                return MonotonicTimeSource.INSTANCE.m3485adjustReading6QKq23U(r1, Duration.m3419unaryMinusUwyO8pc(r3));
            }

            /* renamed from: minus-UwyO8pc, reason: not valid java name */
            public static long m3506minusUwyO8pc(long r2, ComparableTimeMark r4) {
                if ((r4 instanceof ValueTimeMark) == true) goto L5;
                Union$$ExternalSyntheticBUOutline0.m("Subtracting or comparing time marks from different time sources is not possible: ", m3508toStringimpl(r2), " and ", r4);
                return 0;
            L5:
                return m3504minus6eNON_k(r2, ((ValueTimeMark) r4).m3511unboximpl());
            }

            /* renamed from: plus-LRDsOJo, reason: not valid java name */
            public static long m3507plusLRDsOJo(long r1, long r3) {
                return MonotonicTimeSource.INSTANCE.m3485adjustReading6QKq23U(r1, r3);
            }

            /* renamed from: toString-impl, reason: not valid java name */
            public static String m3508toStringimpl(long r2) {
                return "ValueTimeMark(reading=" + r2 + ')';
            }

            @Override // java.lang.Comparable
            public /* bridge */ /* synthetic */ int compareTo(ComparableTimeMark r1) {
                return compareTo2(r1);
            }

            @Override // kotlin.time.ComparableTimeMark
            /* renamed from: compareTo, reason: avoid collision after fix types in other method */
            public /* bridge */ int compareTo2(ComparableTimeMark r1) {
                return ComparableTimeMark.DefaultImpls.compareTo(this, r1);
            }

            @Override // kotlin.time.TimeMark
            /* renamed from: elapsedNow-UwyO8pc */
            public long mo3363elapsedNowUwyO8pc() {
                return m3498elapsedNowUwyO8pc(this.reading);
            }

            @Override // kotlin.time.ComparableTimeMark
            public boolean equals(Object r3) {
                return m3499equalsimpl(this.reading, r3);
            }

            @Override // kotlin.time.TimeMark
            public boolean hasNotPassedNow() {
                return m3501hasNotPassedNowimpl(this.reading);
            }

            @Override // kotlin.time.TimeMark
            public boolean hasPassedNow() {
                return m3502hasPassedNowimpl(this.reading);
            }

            @Override // kotlin.time.ComparableTimeMark
            public int hashCode() {
                return m3503hashCodeimpl(this.reading);
            }

            /* renamed from: minus-LRDsOJo, reason: not valid java name */
            public long m3509minusLRDsOJo(long r3) {
                return m3505minusLRDsOJo(this.reading, r3);
            }

            @Override // kotlin.time.ComparableTimeMark, kotlin.time.TimeMark
            /* renamed from: minus-LRDsOJo */
            public /* bridge */ /* synthetic */ ComparableTimeMark mo3364minusLRDsOJo(long r1) {
                return m3494boximpl(m3509minusLRDsOJo(r1));
            }

            @Override // kotlin.time.TimeMark
            /* renamed from: minus-LRDsOJo */
            public /* bridge */ /* synthetic */ TimeMark mo3364minusLRDsOJo(long r1) {
                return m3494boximpl(m3509minusLRDsOJo(r1));
            }

            @Override // kotlin.time.ComparableTimeMark
            /* renamed from: minus-UwyO8pc */
            public long mo3365minusUwyO8pc(ComparableTimeMark r3) {
                return m3506minusUwyO8pc(this.reading, r3);
            }

            /* renamed from: plus-LRDsOJo, reason: not valid java name */
            public long m3510plusLRDsOJo(long r3) {
                return m3507plusLRDsOJo(this.reading, r3);
            }

            @Override // kotlin.time.ComparableTimeMark, kotlin.time.TimeMark
            /* renamed from: plus-LRDsOJo */
            public /* bridge */ /* synthetic */ ComparableTimeMark mo3366plusLRDsOJo(long r1) {
                return m3494boximpl(m3510plusLRDsOJo(r1));
            }

            @Override // kotlin.time.TimeMark
            /* renamed from: plus-LRDsOJo */
            public /* bridge */ /* synthetic */ TimeMark mo3366plusLRDsOJo(long r1) {
                return m3494boximpl(m3510plusLRDsOJo(r1));
            }

            public String toString() {
                return m3508toStringimpl(this.reading);
            }

            /* renamed from: unbox-impl, reason: not valid java name */
            public final /* synthetic */ long m3511unboximpl() {
                return this.reading;
            }
        }

        static {
            INSTANCE = new Monotonic();
        }

        private Monotonic() {
        }

        @Override // kotlin.time.TimeSource.WithComparableMarks, kotlin.time.TimeSource
        public /* bridge */ /* synthetic */ ComparableTimeMark markNow() {
            return ValueTimeMark.m3494boximpl(m3493markNowz9LOYto());
        }

        @Override // kotlin.time.TimeSource
        public /* bridge */ /* synthetic */ TimeMark markNow() {
            return ValueTimeMark.m3494boximpl(m3493markNowz9LOYto());
        }

        /* renamed from: markNow-z9LOYto, reason: not valid java name */
        public long m3493markNowz9LOYto() {
            return MonotonicTimeSource.INSTANCE.m3488markNowz9LOYto();
        }

        public String toString() {
            return MonotonicTimeSource.INSTANCE.toString();
        }
    }

    @SinceKotlin(version = "1.9")
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u00020\u0003H¦\u0080\u0004¨\u0006\u0004"}, d2 = {"Lkotlin/time/TimeSource$WithComparableMarks;", "Lkotlin/time/TimeSource;", "markNow", "Lkotlin/time/ComparableTimeMark;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @WasExperimental(markerClass = {ExperimentalTime.class})
    public interface WithComparableMarks extends TimeSource {
        @Override // kotlin.time.TimeSource
        ComparableTimeMark markNow();
    }

    static {
        Companion = Companion.$$INSTANCE;
    }

    TimeMark markNow();
}
