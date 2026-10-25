package okhttp3.internal.platform.android;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"androidLevel", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/util/logging/LogRecord;", "getAndroidLevel", "(Ljava/util/logging/LogRecord;)I", "okhttp"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class AndroidLogKt {
    public static final /* synthetic */ int access$getAndroidLevel(LogRecord r0) {
        return getAndroidLevel(r0);
    }

    private static final int getAndroidLevel(LogRecord r3) {
        int r0 = r3.getLevel().intValue();
        Level r1 = Level.INFO;
        if (r0 <= r1.intValue()) goto L7;
        return 5;
    L7:
        if (r3.getLevel().intValue() != r1.intValue()) goto L10;
        return 4;
    L10:
        return 3;
    }
}
