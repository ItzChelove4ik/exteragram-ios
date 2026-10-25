package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0010\b\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0002\b\u0005\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0003\u0010\u0004\"(\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0012\n\u0004\b\u0007\u0010\b\u0012\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n\"\u001a\u0010\u000e\u001a\u00020\r8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, "i", _UrlKt.FRAGMENT_ENCODE_SET, "toHexChar", "(I)C", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "ESCAPE_STRINGS", "[Ljava/lang/String;", "getESCAPE_STRINGS", "()[Ljava/lang/String;", "getESCAPE_STRINGS$annotations", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "ESCAPE_MARKERS", "[B", "getESCAPE_MARKERS", "()[B", "kotlinx-serialization-json"}, k = 2, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class StringOpsKt {
    private static final byte[] ESCAPE_MARKERS = null;
    private static final String[] ESCAPE_STRINGS = null;

    static {
        String[] r1 = new String[93];
        int r2 = 0;
        int r3 = 0;
    L4:
        if (r3 >= 32) goto L6;
        r1[r3] = "\\u" + toHexChar(r3 >> 12) + toHexChar(r3 >> 8) + toHexChar(r3 >> 4) + toHexChar(r3);
        r3 = r3 + 1;
        goto L4
    L6:
        r1[34] = "\\\"";
        r1[92] = "\\\\";
        r1[9] = "\\t";
        r1[8] = "\\b";
        r1[10] = "\\n";
        r1[13] = "\\r";
        r1[12] = "\\f";
        ESCAPE_STRINGS = r1;
        byte[] r0 = new byte[93];
    L7:
        if (r2 >= 32) goto L9;
        r0[r2] = 1;
        r2 = r2 + 1;
        goto L7
    L9:
        r0[34] = 34;
        r0[92] = 92;
        r0[9] = 116;
        r0[8] = 98;
        r0[10] = 110;
        r0[13] = 114;
        r0[12] = 102;
        ESCAPE_MARKERS = r0;
    }

    public static final byte[] getESCAPE_MARKERS() {
        return ESCAPE_MARKERS;
    }

    public static final String[] getESCAPE_STRINGS() {
        return ESCAPE_STRINGS;
    }

    private static final char toHexChar(int r1) {
        int r12 = r1 & 15;
        if (r12 >= 10) goto L7;
        int r13 = r12 + 48;
    L6:
        return (char) r13;
    L7:
        r13 = r12 + 87;
        goto L6
    }
}
