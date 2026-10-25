package kotlin;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import kotlin.internal.HidesMembers;
import kotlin.internal.InlineOnly;
import kotlin.internal.PlatformImplementationsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\u0088\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0088\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0087\u0088\u0004\u001a\u000e\u0010\u000e\u001a\u00020\u000f*\u00020\u0002H\u0087\u0080\u0004\u001a\u0016\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0002H\u0087\u0080\u0004\"%\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b*\u00020\u00028FX\u0086\u0084\b¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"%\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u0013*\u00020\u00028FX\u0087\u0084\b¢\u0006\f\u0012\u0004\b\u0014\u0010\u000b\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"printStackTrace", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "writer", "Ljava/io/PrintWriter;", "stream", "Ljava/io/PrintStream;", "stackTrace", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/lang/StackTraceElement;", "getStackTrace$annotations", "(Ljava/lang/Throwable;)V", "getStackTrace", "(Ljava/lang/Throwable;)[Ljava/lang/StackTraceElement;", "stackTraceToString", _UrlKt.FRAGMENT_ENCODE_SET, "addSuppressed", "exception", "suppressedExceptions", _UrlKt.FRAGMENT_ENCODE_SET, "getSuppressedExceptions$annotations", "getSuppressedExceptions", "(Ljava/lang/Throwable;)Ljava/util/List;", "kotlin-stdlib"}, k = 5, mv = {2, 3, 0}, xi = 49, xs = "kotlin/ExceptionsKt")
/* loaded from: classes5.dex */
class ExceptionsKt__ExceptionsKt {
    public ExceptionsKt__ExceptionsKt() {
    }

    @SinceKotlin(version = "1.1")
    @HidesMembers
    public static void addSuppressed(Throwable r1, Throwable r2) {
        if (r1 == r2) goto L5;
        PlatformImplementationsKt.IMPLEMENTATIONS.addSuppressed(r1, r2);
        return;
    }

    public static final StackTraceElement[] getStackTrace(Throwable r0) {
        return r0.getStackTrace();
    }

    public static /* synthetic */ void getStackTrace$annotations(Throwable r0) {
    }

    public static final List<Throwable> getSuppressedExceptions(Throwable r1) {
        return PlatformImplementationsKt.IMPLEMENTATIONS.getSuppressed(r1);
    }

    @SinceKotlin(version = "1.4")
    public static /* synthetic */ void getSuppressedExceptions$annotations(Throwable r0) {
    }

    @InlineOnly
    private static final void printStackTrace(Throwable r0) {
        r0.printStackTrace();
    }

    @InlineOnly
    private static final void printStackTrace(Throwable r0, PrintStream r1) {
        r0.printStackTrace(r1);
    }

    @InlineOnly
    private static final void printStackTrace(Throwable r0, PrintWriter r1) {
        r0.printStackTrace(r1);
    }

    @SinceKotlin(version = "1.4")
    public static String stackTraceToString(Throwable r2) {
        StringWriter r0 = new StringWriter();
        PrintWriter r1 = new PrintWriter(r0);
        r2.printStackTrace(r1);
        r1.flush();
        return r0.toString();
    }
}
