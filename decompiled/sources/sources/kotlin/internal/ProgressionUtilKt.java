package kotlin.internal;

import c.f$$ExternalSyntheticBUOutline1;
import kotlin.Metadata;
import kotlin.PublishedApi;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0082\u0080\u0004\u001a\u001a\u0010\u0000\u001a\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004H\u0082\u0080\u0004\u001a\"\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001H\u0082\u0080\u0004\u001a\"\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0082\u0080\u0004\u001a\"\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u0001H\u0081\u0080\u0004\u001a\"\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004H\u0081\u0080\u0004¨\u0006\u000b"}, d2 = {"mod", _UrlKt.FRAGMENT_ENCODE_SET, "a", "b", _UrlKt.FRAGMENT_ENCODE_SET, "differenceModulo", "c", "getProgressionLastElement", "start", "end", "step", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ProgressionUtilKt {
    private static final int differenceModulo(int r0, int r1, int r2) {
        return mod(mod(r0, r2) - mod(r1, r2), r2);
    }

    private static final long differenceModulo(long r0, long r2, long r4) {
        return mod(mod(r0, r4) - mod(r2, r4), r4);
    }

    @PublishedApi
    public static final int getProgressionLastElement(int r0, int r1, int r2) {
        if (r2 <= 0) goto L7;
        if (r0 < r1) goto L6;
    L9:
        return r1;
    L6:
        return r1 - differenceModulo(r1, r0, r2);
    L7:
        if (r2 >= 0) goto L12;
        if (r0 <= r1) goto L9;
        return r1 + differenceModulo(r0, r1, -r2);
    L12:
        f$$ExternalSyntheticBUOutline1.m("Step is zero.");
        return 0;
    }

    @PublishedApi
    public static final long getProgressionLastElement(long r4, long r6, long r8) {
        if (r8 <= 0) goto L10;
        if (r4 < r6) goto L8;
        return r6;
    L8:
        return r6 - differenceModulo(r6, r4, r8);
    L10:
        if (r8 < 0) goto L12;
        f$$ExternalSyntheticBUOutline1.m("Step is zero.");
        return 0;
    L12:
        if (r4 > r6) goto L15;
        return r6;
    L15:
        return r6 + differenceModulo(r4, r6, -r8);
    }

    private static final int mod(int r0, int r1) {
        int r02 = r0 % r1;
        if (r02 < 0) goto L6;
        return r02;
    L6:
        return r02 + r1;
    }

    private static final long mod(long r2, long r4) {
        long r22 = r2 % r4;
        if (r22 < 0) goto L6;
        return r22;
    L6:
        return r22 + r4;
    }
}
