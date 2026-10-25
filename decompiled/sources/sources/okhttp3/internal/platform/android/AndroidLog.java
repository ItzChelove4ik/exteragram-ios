package okhttp3.internal.platform.android;

import android.util.Log;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.text.StringsKt;
import okhttp3.OkHttpClient;
import okhttp3.internal.SuppressSignatureCheck;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.http2.Http2;
import okhttp3.internal.url._UrlKt;

@SuppressSignatureCheck
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J1\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u000b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0000¢\u0006\u0002\b\u0013J\u0010\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000bH\u0002J\u0006\u0010\u0015\u001a\u00020\rJ\u0018\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lokhttp3/internal/platform/android/AndroidLog;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "MAX_LOG_LENGTH", _UrlKt.FRAGMENT_ENCODE_SET, "configuredLoggers", "Ljava/util/concurrent/CopyOnWriteArraySet;", "Ljava/util/logging/Logger;", "knownLoggers", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "androidLog", _UrlKt.FRAGMENT_ENCODE_SET, "loggerName", "logLevel", "message", "t", _UrlKt.FRAGMENT_ENCODE_SET, "androidLog$okhttp", "loggerTag", "enable", "enableLogging", "logger", "tag", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AndroidLog {
    public static final AndroidLog INSTANCE = null;
    private static final int MAX_LOG_LENGTH = 4000;
    private static final CopyOnWriteArraySet<Logger> configuredLoggers = null;
    private static final Map<String, String> knownLoggers = null;

    static {
        INSTANCE = new AndroidLog();
        configuredLoggers = new CopyOnWriteArraySet();
        LinkedHashMap r0 = new LinkedHashMap();
        Package r2 = OkHttpClient.class.getPackage();
        if (r2 == null) goto L5;
        String r22 = r2.getName();
    L6:
        if (r22 == null) goto L8;
        r0.put(r22, "OkHttp");
    L8:
        r0.put(OkHttpClient.class.getName(), "okhttp.OkHttpClient");
        r0.put(Http2.class.getName(), "okhttp.Http2");
        r0.put(TaskRunner.class.getName(), "okhttp.TaskRunner");
        r0.put("okhttp3.mockwebserver.MockWebServer", "okhttp.MockWebServer");
        knownLoggers = MapsKt.toMap(r0);
        return;
    L5:
        r22 = null;
        goto L6
    }

    private AndroidLog() {
    }

    public static /* synthetic */ void androidLog$okhttp$default(AndroidLog r0, String r1, int r2, String r3, Throwable r4, int r5, Object r6) {
        if ((r5 & 8) == 0) goto L5;
        r4 = null;
    L5:
        r0.androidLog$okhttp(r1, r2, r3, r4);
    }

    private final void enableLogging(String r1, String r2) {
        Logger r0 = Logger.getLogger(r1);
        if (configuredLoggers.add(r0) == false) goto L13;
        r0.setUseParentHandlers(false);
        if (Log.isLoggable(r2, 3) == false) goto L8;
        Level r12 = Level.FINE;
    L11:
        r0.setLevel(r12);
        r0.addHandler(AndroidLogHandler.INSTANCE);
        return;
    L8:
        if (Log.isLoggable(r2, 4) == false) goto L10;
        r12 = Level.INFO;
        goto L11
    L10:
        r12 = Level.WARNING;
        goto L11
    }

    private final String loggerTag(String r1) {
        String r0 = knownLoggers.get(r1);
        if (r0 == null) goto L5;
        return r0;
    L5:
        return StringsKt.take(r1, 23);
    }

    public final void androidLog$okhttp(String r7, int r8, String r9, Throwable r10) {
        String r6 = loggerTag(r7);
        if (Log.isLoggable(r6, r8) == false) goto L16;
        if (r10 == null) goto L6;
        r9 = r9 + '\n' + Log.getStackTraceString(r10);
    L6:
        String r0 = r9;
        int r72 = r0.length();
        int r2 = 0;
    L7:
        if (r2 >= r72) goto L20;
        int r92 = StringsKt.indexOf$default(r0, '\n', r2, false, 4, null);
        if (r92 != (-1)) goto L12;
        r92 = r72;
    L12:
        int r102 = Math.min(r92, r2 + MAX_LOG_LENGTH);
        Log.println(r8, r6, r0.substring(r2, r102));
        if (r102 >= r92) goto L14;
        r2 = r102;
        goto L12
    L14:
        r2 = r102 + 1;
        goto L7
    L20:
        return;
    }

    public final void enable() {
        Iterator<Map.Entry<String, String>> r1 = knownLoggers.entrySet().iterator();     // Catch: UnsatisfiedLinkError -> L8 RuntimeException -> L10
    L4:
        if (r1.hasNext() == false) goto L19;
        Map.Entry<String, String> r2 = r1.next();     // Catch: UnsatisfiedLinkError -> L8 RuntimeException -> L10
        enableLogging(r2.getKey(), r2.getValue());     // Catch: UnsatisfiedLinkError -> L8 RuntimeException -> L10
        goto L4
    L19:
        return;
    L10:
        e = move-exception;
        System.err.println("Possibly running android unit test without robolectric");
        e.printStackTrace();
        return;
    L8:
        e = move-exception;
        System.err.println("Possibly running android unit test without robolectric");
        e.printStackTrace();
    }
}
