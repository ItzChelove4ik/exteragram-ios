package kotlin.time;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.WasExperimental;
import okhttp3.internal.url._UrlKt;

@SinceKotlin(version = "1.9")
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0011\u0010\u0002\u001a\u00020\u0003H¦\u0080\u0004¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0003H\u0096\u0082\u0004¢\u0006\u0004\b\b\u0010\tJ\u0019\u0010\n\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0003H\u0096\u0082\u0004¢\u0006\u0004\b\u000b\u0010\tJ\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004J\n\u0010\u000e\u001a\u00020\rH\u0096\u0080\u0004¨\u0006\u000f"}, d2 = {"Lkotlin/time/TimeMark;", _UrlKt.FRAGMENT_ENCODE_SET, "elapsedNow", "Lkotlin/time/Duration;", "elapsedNow-UwyO8pc", "()J", "plus", "duration", "plus-LRDsOJo", "(J)Lkotlin/time/TimeMark;", "minus", "minus-LRDsOJo", "hasPassedNow", _UrlKt.FRAGMENT_ENCODE_SET, "hasNotPassedNow", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@WasExperimental(markerClass = {ExperimentalTime.class})
/* loaded from: classes5.dex */
public interface TimeMark {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
        public static boolean hasNotPassedNow(TimeMark r2) {
            return Duration.m3401isNegativeimpl(r2.mo3363elapsedNowUwyO8pc());
        }

        public static boolean hasPassedNow(TimeMark r2) {
            return !Duration.m3401isNegativeimpl(r2.mo3363elapsedNowUwyO8pc());
        }

        /* renamed from: minus-LRDsOJo, reason: not valid java name */
        public static TimeMark m3491minusLRDsOJo(TimeMark r0, long r1) {
            return r0.mo3366plusLRDsOJo(Duration.m3419unaryMinusUwyO8pc(r1));
        }

        /* renamed from: plus-LRDsOJo, reason: not valid java name */
        public static TimeMark m3492plusLRDsOJo(TimeMark r2, long r3) {
            return new AdjustedTimeMark(r2, r3, null);
        }
    }

    /* renamed from: elapsedNow-UwyO8pc */
    long mo3363elapsedNowUwyO8pc();

    boolean hasNotPassedNow();

    boolean hasPassedNow();

    /* renamed from: minus-LRDsOJo */
    TimeMark mo3364minusLRDsOJo(long r1);

    /* renamed from: plus-LRDsOJo */
    TimeMark mo3366plusLRDsOJo(long r1);
}
