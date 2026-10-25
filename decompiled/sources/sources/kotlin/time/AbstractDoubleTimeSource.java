package kotlin.time;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.ComparableTimeMark;
import kotlin.time.TimeSource;
import okhttp3.internal.url._UrlKt;
import retrofit2.Utils$$ExternalSyntheticBUOutline2;

@SinceKotlin(version = "1.3")
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u00002\u00020\u0001:\u0001\fB\u0011\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u00020\tH¤\u0080\u0004J\n\u0010\n\u001a\u00020\u000bH\u0096\u0080\u0004R\u0015\u0010\u0002\u001a\u00020\u0003X\u0084\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Lkotlin/time/AbstractDoubleTimeSource;", "Lkotlin/time/TimeSource$WithComparableMarks;", "unit", "Lkotlin/time/DurationUnit;", "<init>", "(Lkotlin/time/DurationUnit;)V", "getUnit", "()Lkotlin/time/DurationUnit;", "read", _UrlKt.FRAGMENT_ENCODE_SET, "markNow", "Lkotlin/time/ComparableTimeMark;", "DoubleTimeMark", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.ERROR, message = "Using AbstractDoubleTimeSource is no longer recommended, use AbstractLongTimeSource instead.")
@ExperimentalTime
/* loaded from: classes5.dex */
public abstract class AbstractDoubleTimeSource implements TimeSource.WithComparableMarks {
    private final DurationUnit unit;

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B!\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0011\u0010\u000b\u001a\u00020\u0007H\u0096\u0080\u0004¢\u0006\u0004\b\f\u0010\rJ\u0019\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0007H\u0096\u0082\u0004¢\u0006\u0004\b\u0010\u0010\u0011J\u0019\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0001H\u0096\u0082\u0004¢\u0006\u0004\b\u0014\u0010\u0015J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0013\u001a\u0004\u0018\u00010\u0018H\u0096\u0082\u0004J\n\u0010\u0019\u001a\u00020\u001aH\u0096\u0080\u0004J\n\u0010\u001b\u001a\u00020\u001cH\u0096\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0003X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007X\u0082\u0084\b¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u001d"}, d2 = {"Lkotlin/time/AbstractDoubleTimeSource$DoubleTimeMark;", "Lkotlin/time/ComparableTimeMark;", "startedAt", _UrlKt.FRAGMENT_ENCODE_SET, "timeSource", "Lkotlin/time/AbstractDoubleTimeSource;", "offset", "Lkotlin/time/Duration;", "<init>", "(DLkotlin/time/AbstractDoubleTimeSource;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "J", "elapsedNow", "elapsedNow-UwyO8pc", "()J", "plus", "duration", "plus-LRDsOJo", "(J)Lkotlin/time/ComparableTimeMark;", "minus", "other", "minus-UwyO8pc", "(Lkotlin/time/ComparableTimeMark;)J", "equals", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class DoubleTimeMark implements ComparableTimeMark {
        private final long offset;
        private final double startedAt;
        private final AbstractDoubleTimeSource timeSource;

        private DoubleTimeMark(double r1, AbstractDoubleTimeSource r3, long r4) {
            this.startedAt = r1;
            this.timeSource = r3;
            this.offset = r4;
        }

        public /* synthetic */ DoubleTimeMark(double r1, AbstractDoubleTimeSource r3, long r4, DefaultConstructorMarker r6) {
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
        /* renamed from: elapsedNow-UwyO8pc, reason: not valid java name */
        public long mo3363elapsedNowUwyO8pc() {
            return Duration.m3403minusLRDsOJo(DurationKt.toDuration(this.timeSource.read() - this.startedAt, this.timeSource.getUnit()), this.offset);
        }

        @Override // kotlin.time.ComparableTimeMark
        public boolean equals(Object r3) {
            if ((r3 instanceof DoubleTimeMark) == true) goto L5;
            return false;
        L5:
            if (Intrinsics.areEqual(this.timeSource, ((DoubleTimeMark) r3).timeSource) == true) goto L7;
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
            return Duration.m3396hashCodeimpl(Duration.m3404plusLRDsOJo(DurationKt.toDuration(this.startedAt, this.timeSource.getUnit()), this.offset));
        }

        @Override // kotlin.time.ComparableTimeMark, kotlin.time.TimeMark
        /* renamed from: minus-LRDsOJo, reason: not valid java name */
        public /* bridge */ ComparableTimeMark mo3364minusLRDsOJo(long r1) {
            return ComparableTimeMark.DefaultImpls.m3368minusLRDsOJo(this, r1);
        }

        @Override // kotlin.time.TimeMark
        /* renamed from: minus-LRDsOJo */
        public /* bridge */ /* synthetic */ TimeMark mo3364minusLRDsOJo(long r1) {
            return mo3364minusLRDsOJo(r1);
        }

        @Override // kotlin.time.ComparableTimeMark
        /* renamed from: minus-UwyO8pc, reason: not valid java name */
        public long mo3365minusUwyO8pc(ComparableTimeMark r7) {
            if ((r7 instanceof DoubleTimeMark) == false) goto L18;
            DoubleTimeMark r1 = (DoubleTimeMark) r7;
            if (Intrinsics.areEqual(this.timeSource, r1.timeSource) == false) goto L18;
            if (Duration.m3380equalsimpl0(this.offset, r1.offset) == true) goto L9;
        L12:
            long r2 = Duration.m3403minusLRDsOJo(this.offset, r1.offset);
            long r6 = DurationKt.toDuration(this.startedAt - r1.startedAt, this.timeSource.getUnit());
            if (Duration.m3380equalsimpl0(r6, Duration.m3419unaryMinusUwyO8pc(r2)) == false) goto L17;
            return Duration.Companion.m3470getZEROUwyO8pc();
        L17:
            return Duration.m3404plusLRDsOJo(r6, r2);
        L9:
            if (Duration.m3400isInfiniteimpl(this.offset) == false) goto L12;
            return Duration.Companion.m3470getZEROUwyO8pc();
        L18:
            Utils$$ExternalSyntheticBUOutline2.m("Subtracting or comparing time marks from different time sources is not possible: ", this, " and ", r7);
            return 0;
        }

        @Override // kotlin.time.ComparableTimeMark, kotlin.time.TimeMark
        /* renamed from: plus-LRDsOJo, reason: not valid java name */
        public ComparableTimeMark mo3366plusLRDsOJo(long r8) {
            return new DoubleTimeMark(this.startedAt, this.timeSource, Duration.m3404plusLRDsOJo(this.offset, r8), null);
        }

        @Override // kotlin.time.TimeMark
        /* renamed from: plus-LRDsOJo */
        public /* bridge */ /* synthetic */ TimeMark mo3366plusLRDsOJo(long r1) {
            return mo3366plusLRDsOJo(r1);
        }

        public String toString() {
            return "DoubleTimeMark(" + this.startedAt + DurationUnitKt__DurationUnitKt.shortName(this.timeSource.getUnit()) + " + " + Duration.m3415toStringimpl(this.offset) + ", " + this.timeSource + ')';
        }
    }

    public AbstractDoubleTimeSource(DurationUnit r1) {
        this.unit = r1;
    }

    public final DurationUnit getUnit() {
        return this.unit;
    }

    @Override // kotlin.time.TimeSource.WithComparableMarks, kotlin.time.TimeSource
    public ComparableTimeMark markNow() {
        return new DoubleTimeMark(read(), this, Duration.Companion.m3470getZEROUwyO8pc(), null);
    }

    @Override // kotlin.time.TimeSource
    public /* bridge */ /* synthetic */ TimeMark markNow() {
        return markNow();
    }

    public abstract double read();
}
