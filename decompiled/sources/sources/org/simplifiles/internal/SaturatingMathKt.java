package org.simplifiles.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0000¨\u0006\u0004"}, d2 = {"saturatingPlus", _UrlKt.FRAGMENT_ENCODE_SET, "current", "added", "simplifiles"}, k = 2, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public abstract class SaturatingMathKt {
    public static final long saturatingPlus(long r4, long r6) {
        if ((LongCompanionObject.MAX_VALUE - r4) >= r6) goto L6;
        return LongCompanionObject.MAX_VALUE;
    L6:
        return r4 + r6;
    }
}
