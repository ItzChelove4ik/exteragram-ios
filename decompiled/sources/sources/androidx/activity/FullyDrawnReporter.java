package androidx.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000bR\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\fR\u0014\u0010\r\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0016\u0010\u0010\u001a\u00020\u000f8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0013\u001a\u00020\u00128\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0015\u001a\u00020\u00128\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010\u0014R \u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00168\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Landroidx/activity/FullyDrawnReporter;", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/util/concurrent/Executor;", "executor", "Lkotlin/Function0;", _UrlKt.FRAGMENT_ENCODE_SET, "reportFullyDrawn", "<init>", "(Ljava/util/concurrent/Executor;Lkotlin/jvm/functions/Function0;)V", "fullyDrawnReported", "()V", "Ljava/util/concurrent/Executor;", "Lkotlin/jvm/functions/Function0;", "lock", "Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "reporterCount", "I", _UrlKt.FRAGMENT_ENCODE_SET, "reportPosted", "Z", "reportedFullyDrawn", _UrlKt.FRAGMENT_ENCODE_SET, "onReportCallbacks", "Ljava/util/List;", "Ljava/lang/Runnable;", "reportRunnable", "Ljava/lang/Runnable;", "isFullyDrawnReported", "()Z", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFullyDrawnReporter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FullyDrawnReporter.kt\nandroidx/activity/FullyDrawnReporter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,190:1\n1#2:191\n1855#3,2:192\n*S KotlinDebug\n*F\n+ 1 FullyDrawnReporter.kt\nandroidx/activity/FullyDrawnReporter\n*L\n154#1:192,2\n*E\n"})
/* loaded from: classes.dex */
public final class FullyDrawnReporter {
    private final Executor executor;
    private final Object lock;
    private final List<Function0<Unit>> onReportCallbacks;
    private final Function0<Unit> reportFullyDrawn;
    private boolean reportPosted;
    private final Runnable reportRunnable;
    private boolean reportedFullyDrawn;
    private int reporterCount;

    /* renamed from: $r8$lambda$A0RwxxT-QIMFOsDA3Nv48auR1K4, reason: not valid java name */
    public static void m1$r8$lambda$A0RwxxTQIMFOsDA3Nv48auR1K4(FullyDrawnReporter r2) {
        Object r0 = r2.lock;
        monitor-enter(r0);
        r2.reportPosted = false;     // Catch: Throwable -> L10
        if (r2.reporterCount == 0) goto L8;
    L12:
        Unit r22 = Unit.INSTANCE;     // Catch: Throwable -> L10
        monitor-exit(r0);
        return;
    L8:
        if (r2.reportedFullyDrawn == true) goto L12;
        r2.reportFullyDrawn.invoke();     // Catch: Throwable -> L10
        r2.fullyDrawnReported();     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        throw th;
    }

    public FullyDrawnReporter(Executor r1, Function0<Unit> r2) {
        this.executor = r1;
        this.reportFullyDrawn = r2;
        this.lock = new Object();
        this.onReportCallbacks = new ArrayList();
        this.reportRunnable = new FullyDrawnReporter$$ExternalSyntheticLambda0(this);
    }

    public final void fullyDrawnReported() {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.reportedFullyDrawn = true;     // Catch: Throwable -> L9
        Iterator<T> r1 = this.onReportCallbacks.iterator();     // Catch: Throwable -> L9
    L7:
        if (r1.hasNext() == false) goto L11;
        ((Function0) r1.next()).invoke();     // Catch: Throwable -> L9
        goto L7
    L11:
        this.onReportCallbacks.clear();     // Catch: Throwable -> L9
        Unit r3 = Unit.INSTANCE;     // Catch: Throwable -> L9
        monitor-exit(r0);
        return;
    L9:
        th = move-exception;
        throw th;
    }

    public final boolean isFullyDrawnReported() {
        Object r0 = this.lock;
        monitor-enter(r0);
        boolean r1 = this.reportedFullyDrawn;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return r1;
    L7:
        th = move-exception;
        throw th;
    }
}
