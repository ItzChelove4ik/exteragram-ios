package kotlin.comparisons;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\u001aI\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u00012\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u00012\u001a\u0010\u0005\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0006j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`\u0007H\u0087\u0080\u0004¢\u0006\u0002\u0010\b\u001aA\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u00012\u0006\u0010\u0003\u001a\u0002H\u00012\u001a\u0010\u0005\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0006j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`\u0007H\u0087\u0080\u0004¢\u0006\u0002\u0010\t\u001aM\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u00012\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00010\u000b\"\u0002H\u00012\u001a\u0010\u0005\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0006j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`\u0007H\u0087\u0080\u0004¢\u0006\u0002\u0010\f\u001aI\u0010\r\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u00012\u0006\u0010\u0003\u001a\u0002H\u00012\u0006\u0010\u0004\u001a\u0002H\u00012\u001a\u0010\u0005\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0006j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`\u0007H\u0087\u0080\u0004¢\u0006\u0002\u0010\b\u001aA\u0010\r\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u00012\u0006\u0010\u0003\u001a\u0002H\u00012\u001a\u0010\u0005\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0006j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`\u0007H\u0087\u0080\u0004¢\u0006\u0002\u0010\t\u001aM\u0010\r\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u00012\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00010\u000b\"\u0002H\u00012\u001a\u0010\u0005\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0006j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`\u0007H\u0087\u0080\u0004¢\u0006\u0002\u0010\f¨\u0006\u000e"}, d2 = {"maxOf", "T", "a", "b", "c", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Comparator;)Ljava/lang/Object;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Comparator;)Ljava/lang/Object;", "other", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/Object;[Ljava/lang/Object;Ljava/util/Comparator;)Ljava/lang/Object;", "minOf", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/comparisons/ComparisonsKt")
/* loaded from: classes.dex */
class ComparisonsKt___ComparisonsKt extends ComparisonsKt___ComparisonsJvmKt {
    public ComparisonsKt___ComparisonsKt() {
    }

    @SinceKotlin(version = "1.1")
    public static final <T> T maxOf(T r0, T r1, T r2, Comparator<? super T> r3) {
        return (T) maxOf(r0, maxOf(r1, r2, r3), r3);
    }

    @SinceKotlin(version = "1.1")
    public static final <T> T maxOf(T r0, T r1, Comparator<? super T> r2) {
        if (r2.compare(r0, r1) < 0) goto L5;
        return r0;
    L5:
        return r1;
    }

    @SinceKotlin(version = "1.4")
    public static final <T> T maxOf(T r4, T[] r5, Comparator<? super T> r6) {
        int r0 = r5.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        T r2 = r5[r1];
        if (r6.compare(r4, r2) >= 0) goto L7;
        r4 = r2;
    L7:
        r1 = r1 + 1;
        goto L3
    L8:
        return r4;
    }

    @SinceKotlin(version = "1.1")
    public static final <T> T minOf(T r0, T r1, T r2, Comparator<? super T> r3) {
        return (T) minOf(r0, minOf(r1, r2, r3), r3);
    }

    @SinceKotlin(version = "1.1")
    public static final <T> T minOf(T r0, T r1, Comparator<? super T> r2) {
        if (r2.compare(r0, r1) > 0) goto L5;
        return r0;
    L5:
        return r1;
    }

    @SinceKotlin(version = "1.4")
    public static final <T> T minOf(T r4, T[] r5, Comparator<? super T> r6) {
        int r0 = r5.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        T r2 = r5[r1];
        if (r6.compare(r4, r2) <= 0) goto L7;
        r4 = r2;
    L7:
        r1 = r1 + 1;
        goto L3
    L8:
        return r4;
    }
}
