package okhttp3.internal.concurrent;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.StringCompanionObject;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\t\n\u0000\u001a.\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0080\bø\u0001\u0000\u001a9\u0010\n\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u000b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\bH\u0080\bø\u0001\u0000¢\u0006\u0002\u0010\r\u001a$\u0010\u000e\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\tH\u0002\u001a\u000e\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0012\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0013"}, d2 = {"taskLog", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/util/logging/Logger;", "task", "Lokhttp3/internal/concurrent/Task;", "queue", "Lokhttp3/internal/concurrent/TaskQueue;", "messageBlock", "Lkotlin/Function0;", _UrlKt.FRAGMENT_ENCODE_SET, "logElapsed", "T", "block", "(Ljava/util/logging/Logger;Lokhttp3/internal/concurrent/Task;Lokhttp3/internal/concurrent/TaskQueue;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "log", "message", "formatDuration", "ns", _UrlKt.FRAGMENT_ENCODE_SET, "okhttp"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class TaskLoggerKt {
    public static final /* synthetic */ void access$log(Logger r0, Task r1, TaskQueue r2, String r3) {
        log(r0, r1, r2, r3);
    }

    public static final String formatDuration(long r18) {
        if (r18 > (-999500000)) goto L6;
        String r0 = ((r18 - 500000000) / 1000000000) + " s ";
    L18:
        StringCompanionObject r1 = StringCompanionObject.INSTANCE;
        return String.format("%6s", Arrays.copyOf(new Object[]{r0}, 1));
    L6:
        if (r18 > (-999500)) goto L9;
        r0 = ((r18 - 500000) / 1000000) + " ms";
        goto L18
    L9:
        if (r18 > 0) goto L12;
        r0 = ((r18 - 500) / 1000) + " µs";
        goto L18
    L12:
        if (r18 >= 999500) goto L15;
        r0 = ((r18 + 500) / 1000) + " µs";
        goto L18
    L15:
        if (r18 >= 999500000) goto L17;
        r0 = ((r18 + 500000) / 1000000) + " ms";
        goto L18
    L17:
        r0 = ((r18 + 500000000) / 1000000000) + " s ";
        goto L18
    }

    private static final void log(Logger r1, Task r2, TaskQueue r3, String r4) {
        StringBuilder r0 = new StringBuilder();
        r0.append(r3.getName$okhttp());
        r0.append(' ');
        StringCompanionObject r32 = StringCompanionObject.INSTANCE;
        r0.append(String.format("%-22s", Arrays.copyOf(new Object[]{r4}, 1)));
        r0.append(": ");
        r0.append(r2.getName());
        r1.fine(r0.toString());
    }

    public static final <T> T logElapsed(Logger r6, Task r7, TaskQueue r8, Function0<? extends T> r9) {
        boolean r0 = r6.isLoggable(Level.FINE);
        if (r0 == false) goto L5;
        long r1 = r8.getTaskRunner$okhttp().getBackend().nanoTime();
        access$log(r6, r7, r8, "starting");
    L19:
        T r92 = r9.invoke();     // Catch: Throwable -> L13
        InlineMarker.finallyStart(1);
        if (r0 == false) goto L11;
        access$log(r6, r7, r8, "finished run in " + formatDuration(r8.getTaskRunner$okhttp().getBackend().nanoTime() - r1));
    L11:
        InlineMarker.finallyEnd(1);
        return r92;
    L13:
        th = move-exception;
        InlineMarker.finallyStart(1);
        if (r0 == false) goto L17;
        access$log(r6, r7, r8, "failed a run in " + formatDuration(r8.getTaskRunner$okhttp().getBackend().nanoTime() - r1));
    L17:
        InlineMarker.finallyEnd(1);
        throw th;
    L5:
        r1 = -1;
        goto L19
    }

    public static final void taskLog(Logger r1, Task r2, TaskQueue r3, Function0<String> r4) {
        if (r1.isLoggable(Level.FINE) == false) goto L6;
        access$log(r1, r2, r3, r4.invoke());
        return;
    }
}
