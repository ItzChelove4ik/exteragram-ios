package okio.internal;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\f\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, "c", _UrlKt.FRAGMENT_ENCODE_SET, "decodeHexDigit", "(C)I", "okio"}, k = 2, mv = {2, 2, 0}, xi = 48)
@JvmName(name = "-ByteStringNonJs")
@SourceDebugExtension({"SMAP\nByteStringNonJs.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ByteStringNonJs.kt\nokio/internal/-ByteStringNonJs\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,44:1\n1#2:45\n*E\n"})
/* renamed from: okio.internal.-ByteStringNonJs, reason: invalid class name */
/* loaded from: classes.dex */
public abstract class ByteStringNonJs {
    public static final /* synthetic */ int access$decodeHexDigit(char r0) {
        return decodeHexDigit(r0);
    }

    private static final int decodeHexDigit(char r3) {
        if ('0' > r3) goto L9;
        if (r3 >= ':') goto L9;
        return r3 - '0';
    L9:
        if ('a' > r3) goto L15;
        if (r3 >= 'g') goto L15;
        return r3 - 'W';
    L15:
        if ('A' > r3) goto L21;
        if (r3 >= 'G') goto L21;
        return r3 - '7';
    L21:
        throw new IllegalArgumentException("Unexpected hex digit: " + r3);
    }
}
