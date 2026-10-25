package kotlin.ranges;

import java.lang.Comparable;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import okhttp3.internal.url._UrlKt;

@SinceKotlin(version = "1.1")
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\bg\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003J\u0017\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0007J\n\u0010\b\u001a\u00020\u0005H\u0096\u0080\u0004J\u001f\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00028\u0000H¦\u0080\u0004¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lkotlin/ranges/ClosedFloatingPointRange;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ranges/ClosedRange;", "contains", _UrlKt.FRAGMENT_ENCODE_SET, "value", "(Ljava/lang/Comparable;)Z", "isEmpty", "lessThanOrEquals", "a", "b", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Z", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public interface ClosedFloatingPointRange<T extends Comparable<? super T>> extends ClosedRange<T> {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
        public static <T extends Comparable<? super T>> boolean contains(ClosedFloatingPointRange<T> r1, T r2) {
            if (r1.lessThanOrEquals(r1.getStart(), r2) == true) goto L5;
            return false;
        L5:
            if (r1.lessThanOrEquals(r2, r1.getEndInclusive()) == false) goto L10;
            return true;
        L10:
            return false;
        }

        public static <T extends Comparable<? super T>> boolean isEmpty(ClosedFloatingPointRange<T> r2) {
            return !r2.lessThanOrEquals(r2.getStart(), r2.getEndInclusive());
        }
    }

    @Override // kotlin.ranges.ClosedRange, kotlin.ranges.OpenEndRange
    boolean contains(T r1);

    @Override // kotlin.ranges.ClosedRange, kotlin.ranges.OpenEndRange
    boolean isEmpty();

    boolean lessThanOrEquals(T r1, T r2);
}
