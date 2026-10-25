package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0017\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005H\u0000¢\u0006\u0004\b\u0007\u0010\b\u001a\u0017\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\tH\u0000¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {_UrlKt.FRAGMENT_ENCODE_SET, "token", _UrlKt.FRAGMENT_ENCODE_SET, "tokenDescription", "(B)Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "c", "charToTokenClass", "(C)B", _UrlKt.FRAGMENT_ENCODE_SET, "escapeToChar", "(I)C", "kotlinx-serialization-json"}, k = 2, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class AbstractJsonLexerKt {
    public static final byte charToTokenClass(char r1) {
        if (r1 < '~') goto L5;
        return 0;
    L5:
        return CharMappings.CHAR_TO_TOKEN[r1];
    }

    public static final char escapeToChar(int r1) {
        if (r1 < 117) goto L5;
        return 0;
    L5:
        return CharMappings.ESCAPE_2_CHAR[r1];
    }

    public static final String tokenDescription(byte r1) {
        if (r1 != 1) goto L7;
        return "quotation mark '\"'";
    L7:
        if (r1 != 2) goto L11;
        return "string escape sequence '\\'";
    L11:
        if (r1 != 4) goto L15;
        return "comma ','";
    L15:
        if (r1 != 5) goto L19;
        return "colon ':'";
    L19:
        if (r1 != 6) goto L23;
        return "start of the object '{'";
    L23:
        if (r1 != 7) goto L27;
        return "end of the object '}'";
    L27:
        if (r1 != 8) goto L31;
        return "start of the array '['";
    L31:
        if (r1 != 9) goto L35;
        return "end of the array ']'";
    L35:
        if (r1 != 10) goto L39;
        return "end of the input";
    L39:
        if (r1 != Byte.MAX_VALUE) goto L42;
        return "invalid token";
    L42:
        return "valid token";
    }
}
