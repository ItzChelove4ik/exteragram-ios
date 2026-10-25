package okhttp3.internal.http;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000+\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0003\u001a\f\u0010\f\u001a\u0004\u0018\u00010\r*\u00020\u0007\u001a\n\u0010\u000e\u001a\u00020\u0007*\u00020\r\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\"\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b\"\u0018\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006\u000f"}, d2 = {"MAX_DATE", _UrlKt.FRAGMENT_ENCODE_SET, "STANDARD_DATE_FORMAT", "okhttp3/internal/http/DateFormattingKt$STANDARD_DATE_FORMAT$1", "Lokhttp3/internal/http/DateFormattingKt$STANDARD_DATE_FORMAT$1;", "BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "[Ljava/lang/String;", "BROWSER_COMPATIBLE_DATE_FORMATS", "Ljava/text/DateFormat;", "[Ljava/text/DateFormat;", "toHttpDateOrNull", "Ljava/util/Date;", "toHttpDateString", "okhttp"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class DateFormattingKt {
    private static final DateFormat[] BROWSER_COMPATIBLE_DATE_FORMATS = null;
    private static final String[] BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS = null;
    public static final long MAX_DATE = 253402300799999L;
    private static final DateFormattingKt$STANDARD_DATE_FORMAT$1 STANDARD_DATE_FORMAT = null;

    static {
        STANDARD_DATE_FORMAT = new DateFormattingKt$STANDARD_DATE_FORMAT$1();
        String[] r0 = {"EEE, dd MMM yyyy HH:mm:ss zzz", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z", "EEE MMM d yyyy HH:mm:ss z"};
        BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS = r0;
        BROWSER_COMPATIBLE_DATE_FORMATS = new DateFormat[r0.length];
    }

    public static final Date toHttpDateOrNull(String r10) {
        if (r10.length() != 0) goto L5;
        return null;
    L5:
        ParsePosition r0 = new ParsePosition(0);
        Date r3 = STANDARD_DATE_FORMAT.get().parse(r10, r0);
        if (r0.getIndex() != r10.length()) goto L8;
        return r3;
    L8:
        String[] r32 = BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS;
        monitor-enter(r32);
        int r4 = r32.length;     // Catch: Throwable -> L15
        int r5 = 0;
    L11:
        if (r5 >= r4) goto L22;
        DateFormat[] r6 = BROWSER_COMPATIBLE_DATE_FORMATS;     // Catch: Throwable -> L15
        DateFormat r7 = r6[r5];     // Catch: Throwable -> L15
        if (r7 != null) goto L17;
        r7 = new SimpleDateFormat(BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS[r5], Locale.US);     // Catch: Throwable -> L15
        r7.setTimeZone(_UtilJvmKt.UTC);     // Catch: Throwable -> L15
        r6[r5] = r7;     // Catch: Throwable -> L15
    L17:
        r0.setIndex(0);     // Catch: Throwable -> L15
        Date r62 = r7.parse(r10, r0);     // Catch: Throwable -> L15
        if (r0.getIndex() != 0) goto L19;
        r5 = r5 + 1;
        goto L11
    L19:
        monitor-exit(r32);
        return r62;
    L22:
        Unit r102 = Unit.INSTANCE;     // Catch: Throwable -> L15
        monitor-exit(r32);
        return null;
    L15:
        th = move-exception;
        throw th;
    }

    public static final String toHttpDateString(Date r1) {
        return STANDARD_DATE_FORMAT.get().format(r1);
    }
}
