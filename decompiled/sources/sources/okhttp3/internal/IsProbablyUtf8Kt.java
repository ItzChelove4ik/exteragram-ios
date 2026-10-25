package okhttp3.internal;

import java.io.EOFException;
import kotlin.Metadata;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;
import okio.BufferedSource;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0000¨\u0006\u0005"}, d2 = {"isProbablyUtf8", _UrlKt.FRAGMENT_ENCODE_SET, "Lokio/BufferedSource;", "codePointLimit", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class IsProbablyUtf8Kt {
    public static final boolean isProbablyUtf8(BufferedSource r5, long r6) {
        BufferedSource r52 = r5.peek();     // Catch: EOFException -> L17
        long r1 = 0;
    L5:
        if (r1 >= r6) goto L25;
        if (r52.exhausted() == true) goto L15;
        int r3 = r52.readUtf8CodePoint();     // Catch: EOFException -> L17
        if (Character.isISOControl(r3) == false) goto L14;
        if (Character.isWhitespace(r3) == true) goto L14;
        return false;
    L14:
        r1 = r1 + 1;
        goto L5
    L15:
        return true;
    L25:
        return true;
    L17:
        return false;
    }

    public static /* synthetic */ boolean isProbablyUtf8$default(BufferedSource r0, long r1, int r3, Object r4) {
        if ((r3 & 1) == 0) goto L6;
        r1 = LongCompanionObject.MAX_VALUE;
    L6:
        return isProbablyUtf8(r0, r1);
    }
}
