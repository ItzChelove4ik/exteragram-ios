package androidx.media3.datasource;

import android.text.TextUtils;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public abstract class HttpUtil {
    private static final Pattern CONTENT_RANGE_WITH_SIZE = null;
    private static final Pattern CONTENT_RANGE_WITH_START_AND_END = null;

    static {
        CONTENT_RANGE_WITH_START_AND_END = Pattern.compile("bytes (\\d+)-(\\d+)/(?:\\d+|\\*)");
        CONTENT_RANGE_WITH_SIZE = Pattern.compile("bytes (?:(?:\\d+-\\d+)|\\*)/(\\d+)");
    }

    public static String buildRangeRequestHeader(long r4, long r6) {
        if (r4 == 0) goto L5;
    L8:
        StringBuilder r0 = new StringBuilder("bytes=");
        r0.append(r4);
        r0.append("-");
        if (r6 == (-1)) goto L12;
        r0.append((r4 + r6) - 1);
    L12:
        return r0.toString();
    L5:
        if (r6 != (-1)) goto L8;
        return null;
    }

    public static long getContentLength(String r10, String r11) {
        if (TextUtils.isEmpty(r10) == false) goto L24;
    L7:
        long r4 = -1;
    L9:
        if (TextUtils.isEmpty(r11) == true) goto L26;
        Matcher r1 = CONTENT_RANGE_WITH_START_AND_END.matcher(r11);
        if (r1.matches() == true) goto L22;
        return r4;
    L22:
        long r6 = (Long.parseLong((String) Assertions.checkNotNull(r1.group(2))) - Long.parseLong((String) Assertions.checkNotNull(r1.group(1)))) + 1;     // Catch: NumberFormatException -> L20
        if (r4 >= 0) goto L17;
        return r6;
    L17:
        if (r4 == r6) goto L29;
        Log.w("HttpUtil", "Inconsistent headers [" + r10 + "] [" + r11 + "]");     // Catch: NumberFormatException -> L20
        return Math.max(r4, r6);
    L29:
        return r4;
    L20:
        Log.e("HttpUtil", "Unexpected Content-Range [" + r11 + "]");
        return r4;
    L26:
        return r4;
    L24:
        r4 = Long.parseLong(r10);     // Catch: NumberFormatException -> L6
    L6:
        Log.e("HttpUtil", "Unexpected Content-Length [" + r10 + "]");
        goto L7
    }

    public static long getDocumentSize(String r3) {
        if (TextUtils.isEmpty(r3) == false) goto L5;
        return -1;
    L5:
        Matcher r32 = CONTENT_RANGE_WITH_SIZE.matcher(r3);
        if (r32.matches() == true) goto L8;
        return -1;
    L8:
        return Long.parseLong((String) Assertions.checkNotNull(r32.group(1)));
    }
}
