package okio;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.JvmName;
import okhttp3.internal.url._UrlKt;
import okio.ByteString;

@Metadata(d1 = {"\u0000\f\n\u0002\u0010\u000e\n\u0002\u0010\u0012\n\u0002\b\f\u001a\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u0000¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u001d\u0010\u0005\u001a\u00020\u0000*\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u0001H\u0000¢\u0006\u0004\b\u0005\u0010\u0006\"\u001a\u0010\u0007\u001a\u00020\u00018\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\"\u001a\u0010\u000b\u001a\u00020\u00018\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u000b\u0010\b\u001a\u0004\b\f\u0010\n¨\u0006\r"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "decodeBase64ToArray", "(Ljava/lang/String;)[B", "map", "encodeBase64", "([B[B)Ljava/lang/String;", "BASE64", "[B", "getBASE64", "()[B", "BASE64_URL_SAFE", "getBASE64_URL_SAFE", "okio"}, k = 2, mv = {2, 2, 0}, xi = 48)
@JvmName(name = "-Base64")
/* renamed from: okio.-Base64, reason: invalid class name */
/* loaded from: classes5.dex */
public abstract class Base64 {
    private static final byte[] BASE64 = null;
    private static final byte[] BASE64_URL_SAFE = null;

    static {
        ByteString.Companion r0 = ByteString.Companion;
        BASE64 = r0.encodeUtf8("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/").getData$okio();
        BASE64_URL_SAFE = r0.encodeUtf8("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_").getData$okio();
    }

    public static final byte[] decodeBase64ToArray(String r14) {
        int r0 = r14.length();
    L4:
        if (r0 <= 0) goto L13;
        char r5 = r14.charAt(r0 - 1);
        if (r5 == '=') goto L12;
        if (r5 == '\n') goto L12;
        if (r5 == '\r') goto L12;
        if (r5 == ' ') goto L12;
        if (r5 != '\t') goto L13;
    L12:
        r0 = r0 - 1;
    L13:
        int r52 = (int) ((r0 * 6) / 8);
        byte[] r6 = new byte[r52];
        int r7 = 0;
        int r8 = 0;
        int r9 = 0;
        int r10 = 0;
    L15:
        if (r7 >= r0) goto L53;
        char r12 = r14.charAt(r7);
        if ('A' > r12) goto L22;
        if (r12 >= '[') goto L22;
        int r122 = r12 - 'A';
    L49:
        r9 = (r9 << 6) | r122;
        r8 = r8 + 1;
        if ((r8 % 4) != 0) goto L52;
        r6[r10] = (byte) (r9 >> 16);
        int r123 = r10 + 2;
        r6[r10 + 1] = (byte) (r9 >> 8);
        r10 = r10 + 3;
        r6[r123] = (byte) r9;
    L52:
        r7 = r7 + 1;
    L22:
        if ('a' > r12) goto L27;
        if (r12 >= '{') goto L27;
        r122 = r12 - 'G';
    L27:
        if ('0' > r12) goto L32;
        if (r12 >= ':') goto L32;
        r122 = r12 + 4;
    L32:
        if (r12 != '+') goto L34;
    L48:
        r122 = 62;
        goto L49
    L34:
        if (r12 == '-') goto L48;
        if (r12 != '/') goto L39;
    L47:
        r122 = 63;
        goto L49
    L39:
        if (r12 == '_') goto L47;
        if (r12 == '\n') goto L52;
        if (r12 == '\r') goto L52;
        if (r12 == ' ') goto L52;
        if (r12 == '\t') goto L52;
        return null;
    L53:
        int r82 = r8 % 4;
        if (r82 != 1) goto L56;
        return null;
    L56:
        if (r82 != 2) goto L58;
        r6[r10] = (byte) ((r9 << 12) >> 16);
        r10 = r10 + 1;
    L62:
        if (r10 != r52) goto L65;
        return r6;
    L65:
        return Arrays.copyOf(r6, r10);
    L58:
        if (r82 != 3) goto L62;
        int r142 = r9 << 6;
        int r02 = r10 + 1;
        r6[r10] = (byte) (r142 >> 16);
        r10 = r10 + 2;
        r6[r02] = (byte) (r142 >> 8);
        goto L62
    }

    public static final String encodeBase64(byte[] r11, byte[] r12) {
        byte[] r0 = new byte[((r11.length + 2) / 3) * 4];
        int r2 = r11.length - (r11.length % 3);
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r3 >= r2) goto L5;
        byte r6 = r11[r3];
        int r7 = r3 + 2;
        byte r5 = r11[r3 + 1];
        r3 = r3 + 3;
        byte r72 = r11[r7];
        r0[r4] = r12[(r6 & UByte.MAX_VALUE) >> 2];
        r0[r4 + 1] = r12[((r6 & 3) << 4) | ((r5 & UByte.MAX_VALUE) >> 4)];
        int r62 = r4 + 3;
        r0[r4 + 2] = r12[((r5 & 15) << 2) | ((r72 & UByte.MAX_VALUE) >> 6)];
        r4 = r4 + 4;
        r0[r62] = r12[r72 & 63];
        goto L3
    L5:
        int r52 = r11.length - r2;
        if (r52 == 1) goto L10;
        if (r52 != 2) goto L12;
        int r53 = r3 + 1;
        byte r32 = r11[r3];
        byte r112 = r11[r53];
        r0[r4] = r12[(r32 & UByte.MAX_VALUE) >> 2];
        r0[r4 + 1] = r12[((r32 & 3) << 4) | ((r112 & UByte.MAX_VALUE) >> 4)];
        r0[r4 + 2] = r12[(r112 & 15) << 2];
        r0[r4 + 3] = kotlin.io.encoding.Base64.padSymbol;
    L12:
        return _JvmPlatformKt.toUtf8String(r0);
    L10:
        byte r113 = r11[r3];
        r0[r4] = r12[(r113 & UByte.MAX_VALUE) >> 2];
        r0[r4 + 1] = r12[(r113 & 3) << 4];
        r0[r4 + 2] = kotlin.io.encoding.Base64.padSymbol;
        r0[r4 + 3] = kotlin.io.encoding.Base64.padSymbol;
        goto L12
    }

    public static /* synthetic */ String encodeBase64$default(byte[] r0, byte[] r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = BASE64;
    L6:
        return encodeBase64(r0, r1);
    }
}
