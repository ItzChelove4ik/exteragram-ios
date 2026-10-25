package androidx.work;

import android.content.Context;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J \u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007¨\u0006\r"}, d2 = {"Landroidx/work/WorkerFactory;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "createWorker", "Landroidx/work/ListenableWorker;", "appContext", "Landroid/content/Context;", "workerClassName", _UrlKt.FRAGMENT_ENCODE_SET, "workerParameters", "Landroidx/work/WorkerParameters;", "createWorkerWithDefaultFallback", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class WorkerFactory {
    public WorkerFactory() {
    }

    private static final ListenableWorker createWorkerWithDefaultFallback$fallbackToReflection(Context r3, String r4, WorkerParameters r5) {
        return createWorkerWithDefaultFallback$getWorkerClass(r4).getDeclaredConstructor(new Class[]{Context.class, WorkerParameters.class}).newInstance(new Object[]{r3, r5});
    L5:
        th = move-exception;
        Logger.get().error(WorkerFactoryKt.access$getTAG$p(), "Could not instantiate " + r4, th);
        throw th;
    }

    private static final Class<? extends ListenableWorker> createWorkerWithDefaultFallback$getWorkerClass(String r5) {
        return Class.forName(r5).asSubclass(ListenableWorker.class);
    L4:
        th = move-exception;
        Logger.get().error(WorkerFactoryKt.access$getTAG$p(), "Invalid class: " + r5, th);
        throw th;
    }

    public abstract ListenableWorker createWorker(Context r1, String r2, WorkerParameters r3);

    public final ListenableWorker createWorkerWithDefaultFallback(Context r2, String r3, WorkerParameters r4) {
        ListenableWorker r0 = createWorker(r2, r3, r4);
        if (r0 != null) goto L6;
        r0 = createWorkerWithDefaultFallback$fallbackToReflection(r2, r3, r4);
    L6:
        if (r0.isUsed() == true) goto L8;
        return r0;
    L8:
        WorkerFactory$$ExternalSyntheticBUOutline0.m("WorkerFactory (", getClass().getName(), ") returned an instance of a ListenableWorker (", r3, ") which has already been invoked. createWorker() must always return a new instance of a ListenableWorker.");
        return null;
    }
}
