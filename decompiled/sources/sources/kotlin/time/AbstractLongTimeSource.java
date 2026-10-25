package kotlin.time;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.math.MathKt;
import kotlin.time.ComparableTimeMark;
import kotlin.time.TimeSource;
import okhttp3.internal.url._UrlKt;
import retrofit2.Utils$$ExternalSyntheticBUOutline2;

@SinceKotlin(version = "1.9")
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u00002\u00020\u0001:\u0001\u0012B\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u00020\tH¤\u0080\u0004J\n\u0010\u000f\u001a\u00020\tH\u0082\u0080\u0004J\n\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004R\u0015\u0010\u0002\u001a\u00020\u0003X\u0084\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\t8BX\u0082\u0084\n¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u0013"}, d2 = {"Lkotlin/time/AbstractLongTimeSource;", "Lkotlin/time/TimeSource$WithComparableMarks;", "unit", "Lkotlin/time/DurationUnit;", "<init>", "(Lkotlin/time/DurationUnit;)V", "getUnit", "()Lkotlin/time/DurationUnit;", "read", _UrlKt.FRAGMENT_ENCODE_SET, "zero", "getZero", "()J", "zero$delegate", "Lkotlin/Lazy;", "adjustedRead", "markNow", "Lkotlin/time/ComparableTimeMark;", "LongTimeMark", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@WasExperimental(markerClass = {ExperimentalTime.class})
/* loaded from: classes5.dex */
public abstract class AbstractLongTimeSource implements TimeSource.WithComparableMarks {
    private final DurationUnit unit;
    private final Lazy zero$delegate;

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B!\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0011\u0010\u000b\u001a\u00020\u0007H\u0096\u0080\u0004¢\u0006\u0004\b\f\u0010\rJ\u0019\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0007H\u0096\u0082\u0004¢\u0006\u0004\b\u0010\u0010\u0011J\u0019\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0001H\u0096\u0082\u0004¢\u0006\u0004\b\u0014\u0010\u0015J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0013\u001a\u0004\u0018\u00010\u0018H\u0096\u0082\u0004J\n\u0010\u0019\u001a\u00020\u001aH\u0096\u0080\u0004J\n\u0010\u001b\u001a\u00020\u001cH\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007X\u0082\u0084\b¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u001d"}, d2 = {"Lkotlin/time/AbstractLongTimeSource$LongTimeMark;", "Lkotlin/time/ComparableTimeMark;", "startedAt", _UrlKt.FRAGMENT_ENCODE_SET, "timeSource", "Lkotlin/time/AbstractLongTimeSource;", "offset", "Lkotlin/time/Duration;", "<init>", "(JLkotlin/time/AbstractLongTimeSource;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "J", "elapsedNow", "elapsedNow-UwyO8pc", "()J", "plus", "duration", "plus-LRDsOJo", "(J)Lkotlin/time/ComparableTimeMark;", "minus", "other", "minus-UwyO8pc", "(Lkotlin/time/ComparableTimeMark;)J", "equals", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nTimeSources.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TimeSources.kt\nkotlin/time/AbstractLongTimeSource$LongTimeMark\n+ 2 longSaturatedMath.kt\nkotlin/time/LongSaturatedMathKt\n*L\n1#1,210:1\n80#2:211\n*S KotlinDebug\n*F\n+ 1 TimeSources.kt\nkotlin/time/AbstractLongTimeSource$LongTimeMark\n*L\n67#1:211\n*E\n"})
    public static final class LongTimeMark implements ComparableTimeMark {
        private final long offset;
        private final long startedAt;
        private final AbstractLongTimeSource timeSource;

        private LongTimeMark(long r1, AbstractLongTimeSource r3, long r4) {
            this.startedAt = r1;
            this.timeSource = r3;
            this.offset = r4;
        }

        public /* synthetic */ LongTimeMark(long r1, AbstractLongTimeSource r3, long r4, DefaultConstructorMarker r6) {
            this(r1, r3, r4);
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
            return Duration.m3403minusLRDsOJo(LongSaturatedMathKt.saturatingOriginsDiff(AbstractLongTimeSource.access$adjustedRead(this.timeSource), this.startedAt, this.timeSource.getUnit()), this.offset);
        }

        @Override // kotlin.time.ComparableTimeMark
        public boolean equals(Object r3) {
            if ((r3 instanceof LongTimeMark) == true) goto L5;
            return false;
        L5:
            if (Intrinsics.areEqual(this.timeSource, ((LongTimeMark) r3).timeSource) == true) goto L7;
            return false;
        L7:
            if (Duration.m3380equalsimpl0(mo3365minusUwyO8pc((ComparableTimeMark) r3), Duration.Companion.m3470getZEROUwyO8pc()) == false) goto L13;
            return true;
        L13:
            return false;
        }

        @Override // kotlin.time.TimeMark
        public /* bridge */ boolean hasNotPassedNow() {
            return ComparableTimeMark.DefaultImpls.hasNotPassedNow(this);
        }

        @Override // kotlin.time.TimeMark
        public /* bridge */ boolean hasPassedNow() {
            return ComparableTimeMark.DefaultImpls.hasPassedNow(this);
        }

        @Override // kotlin.time.ComparableTimeMark
        public int hashCode() {
            return (Duration.m3396hashCodeimpl(this.offset) * 37) + Long.hashCode(this.startedAt);
        }

        @Override // kotlin.time.ComparableTimeMark, kotlin.time.TimeMark
        /* renamed from: minus-LRDsOJo */
        public /* bridge */ ComparableTimeMark mo3364minusLRDsOJo(long r1) {
            return ComparableTimeMark.DefaultImpls.m3368minusLRDsOJo(this, r1);
        }

        @Override // kotlin.time.TimeMark
        /* renamed from: minus-LRDsOJo */
        public /* bridge */ /* synthetic */ TimeMark mo3364minusLRDsOJo(long r1) {
            return mo3364minusLRDsOJo(r1);
        }

        @Override // kotlin.time.ComparableTimeMark
        /* renamed from: minus-UwyO8pc */
        public long mo3365minusUwyO8pc(ComparableTimeMark r7) {
            if ((r7 instanceof LongTimeMark) == false) goto L8;
            LongTimeMark r1 = (LongTimeMark) r7;
            if (Intrinsics.areEqual(this.timeSource, r1.timeSource) == false) goto L8;
            return Duration.m3404plusLRDsOJo(LongSaturatedMathKt.saturatingOriginsDiff(this.startedAt, r1.startedAt, this.timeSource.getUnit()), Duration.m3403minusLRDsOJo(this.offset, r1.offset));
        L8:
            Utils$$ExternalSyntheticBUOutline2.m("Subtracting or comparing time marks from different time sources is not possible: ", this, " and ", r7);
            return 0;
        }

        @Override // kotlin.time.ComparableTimeMark, kotlin.time.TimeMark
        /* renamed from: plus-LRDsOJo */
        public ComparableTimeMark mo3366plusLRDsOJo(long r11) {
            DurationUnit r0 = this.timeSource.getUnit();
            if (Duration.m3400isInfiniteimpl(r11) == true) goto L5;
            long r1 = Duration.m3418truncateToUwyO8pc$kotlin_stdlib(r11, r0);
            long r112 = Duration.m3404plusLRDsOJo(Duration.m3403minusLRDsOJo(r11, r1), this.offset);
            long r12 = LongSaturatedMathKt.m3483saturatingAddNuflL3o(this.startedAt, r0, r1);
            long r3 = Duration.m3418truncateToUwyO8pc$kotlin_stdlib(r112, r0);
            long r13 = LongSaturatedMathKt.m3483saturatingAddNuflL3o(r12, r0, r3);
            long r113 = Duration.m3403minusLRDsOJo(r112, r3);
            long r32 = Duration.m3388getInWholeNanosecondsimpl(r113);
            if (r13 == 0) goto L14;
            if (r32 == 0) goto L14;
            if ((r13 ^ r32) >= 0) goto L14;
            long r33 = DurationKt.toDuration(MathKt.getSign(r32), r0);
            r13 = LongSaturatedMathKt.m3483saturatingAddNuflL3o(r13, r0, r33);
            r113 = Duration.m3403minusLRDsOJo(r113, r33);
        L14:
            if ((1 | (r13 - 1)) != LongCompanionObject.MAX_VALUE) goto L16;
            r113 = Duration.Companion.m3470getZEROUwyO8pc();
        L16:
            AbstractLongTimeSource r34 = this.timeSource;
            return new LongTimeMark(r13, r34, r113, null);
        L5:
            return new LongTimeMark(LongSaturatedMathKt.m3483saturatingAddNuflL3o(this.startedAt, r0, r11), this.timeSource, Duration.Companion.m3470getZEROUwyO8pc(), null);
        }

        @Override // kotlin.time.TimeMark
        /* renamed from: plus-LRDsOJo */
        public /* bridge */ /* synthetic */ TimeMark mo3366plusLRDsOJo(long r1) {
            return mo3366plusLRDsOJo(r1);
        }

        public String toString() {
            return "LongTimeMark(" + this.startedAt + DurationUnitKt__DurationUnitKt.shortName(this.timeSource.getUnit()) + " + " + Duration.m3415toStringimpl(this.offset) + ", " + this.timeSource + ')';
        }
    }

    public static long $r8$lambda$7_ZSZkpjYYSArLmwZ5AQLW4p4EQ(AbstractLongTimeSource r2) {
        return r2.read();
    }

    public AbstractLongTimeSource(DurationUnit r1) {
        this.unit = r1;
        this.zero$delegate = LazyKt.lazy(new AbstractLongTimeSource$$ExternalSyntheticLambda0(this));
    }

    public static final /* synthetic */ long access$adjustedRead(AbstractLongTimeSource r2) {
        return r2.adjustedRead();
    }

    private final long adjustedRead() {
        return read() - getZero();
    }

    private final long getZero() {
        return ((Number) this.zero$delegate.getValue()).longValue();
    }

    public final DurationUnit getUnit() {
        return this.unit;
    }

    @Override // kotlin.time.TimeSource.WithComparableMarks, kotlin.time.TimeSource
    public ComparableTimeMark markNow() {
        return new LongTimeMark(adjustedRead(), this, Duration.Companion.m3470getZEROUwyO8pc(), null);
    }

    @Override // kotlin.time.TimeSource
    public /* bridge */ /* synthetic */ TimeMark markNow() {
        return markNow();
    }

    public abstract long read();
}
