package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\u0000\u001a,\u0010\u0000\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005H\u0000\u001a,\u0010\u0000\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\b2\b\b\u0002\u0010\u0006\u001a\u00020\b2\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0000¨\u0006\t"}, d2 = {"systemProp", _UrlKt.FRAGMENT_ENCODE_SET, "propertyName", _UrlKt.FRAGMENT_ENCODE_SET, "defaultValue", _UrlKt.FRAGMENT_ENCODE_SET, "minValue", "maxValue", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-coroutines-core"}, k = 5, mv = {2, 2, 0}, xi = 48, xs = "kotlinx/coroutines/internal/SystemPropsKt")
/* loaded from: classes.dex */
abstract /* synthetic */ class SystemPropsKt__SystemProps_commonKt {
    public static final int systemProp(String r7, int r8, int r9, int r10) {
        return (int) SystemPropsKt.systemProp(r7, r8, r9, r10);
    }

    public static final long systemProp(String r4, long r5, long r7, long r9) {
        String r0 = SystemPropsKt.systemProp(r4);
        if (r0 != null) goto L5;
        return r5;
    L5:
        Long r52 = StringsKt.toLongOrNull(r0);
        if (r52 == null) goto L15;
        long r2 = r52.longValue();
        if (r7 > r2) goto L13;
        if (r2 > r9) goto L13;
        return r2;
    L13:
        throw new IllegalStateException(("System property '" + r4 + "' should be in range " + r7 + ".." + r9 + ", but is '" + r2 + '\'').toString());
    L15:
        throw new IllegalStateException(("System property '" + r4 + "' has unrecognized value '" + r0 + '\'').toString());
    }

    public static final String systemProp(String r0, String r1) {
        String r02 = SystemPropsKt.systemProp(r0);
        if (r02 != null) goto L5;
        return r1;
    L5:
        return r02;
    }

    public static final boolean systemProp(String r0, boolean r1) {
        String r02 = SystemPropsKt.systemProp(r0);
        if (r02 != null) goto L5;
        return r1;
    L5:
        return Boolean.parseBoolean(r02);
    }

    public static /* synthetic */ int systemProp$default(String r0, int r1, int r2, int r3, int r4, Object r5) {
        if ((r4 & 4) == 0) goto L6;
        r2 = 1;
    L6:
        if ((r4 & 8) == 0) goto L9;
        r3 = Integer.MAX_VALUE;
    L9:
        return SystemPropsKt.systemProp(r0, r1, r2, r3);
    }

    public static /* synthetic */ long systemProp$default(String r7, long r8, long r10, long r12, int r14, Object r15) {
        if ((r14 & 4) == 0) goto L5;
        r10 = 1;
    L5:
        long r3 = r10;
        if ((r14 & 8) == 0) goto L9;
        r12 = LongCompanionObject.MAX_VALUE;
    L9:
        return SystemPropsKt.systemProp(r7, r8, r3, r12);
    }
}
