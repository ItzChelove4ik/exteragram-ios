package kotlin.ranges;

import java.lang.Comparable;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000f\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003J\u0017\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\fJ\n\u0010\r\u001a\u00020\nH\u0096\u0080\u0004R\u0013\u0010\u0004\u001a\u00028\u0000X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0013\u0010\u0007\u001a\u00028\u0000X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006¨\u0006\u000e"}, d2 = {"Lkotlin/ranges/ClosedRange;", "T", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "start", "getStart", "()Ljava/lang/Comparable;", "endInclusive", "getEndInclusive", "contains", _UrlKt.FRAGMENT_ENCODE_SET, "value", "(Ljava/lang/Comparable;)Z", "isEmpty", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public interface ClosedRange<T extends Comparable<? super T>> {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final class DefaultImpls {
        public static <T extends Comparable<? super T>> boolean contains(ClosedRange<T> r1, T r2) {
            if (r2.compareTo(r1.getStart()) >= 0) goto L5;
            return false;
        L5:
            if (r2.compareTo(r1.getEndInclusive()) > 0) goto L10;
            return true;
        L10:
            return false;
        }

        public static <T extends Comparable<? super T>> boolean isEmpty(ClosedRange<T> r1) {
            if (r1.getStart().compareTo(r1.getEndInclusive()) <= 0) goto L6;
            return true;
        L6:
            return false;
        }
    }

    boolean contains(T r1);

    T getEndInclusive();

    T getStart();

    boolean isEmpty();
}
