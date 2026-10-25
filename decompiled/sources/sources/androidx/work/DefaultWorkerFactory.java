package androidx.work;

import android.content.Context;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Landroidx/work/DefaultWorkerFactory;", "Landroidx/work/WorkerFactory;", "<init>", "()V", "createWorker", _UrlKt.FRAGMENT_ENCODE_SET, "appContext", "Landroid/content/Context;", "workerClassName", _UrlKt.FRAGMENT_ENCODE_SET, "workerParameters", "Landroidx/work/WorkerParameters;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class DefaultWorkerFactory extends WorkerFactory {
    public static final DefaultWorkerFactory INSTANCE = null;

    static {
        INSTANCE = new DefaultWorkerFactory();
    }

    private DefaultWorkerFactory() {
    }

    @Override // androidx.work.WorkerFactory
    public /* bridge */ /* synthetic */ ListenableWorker createWorker(Context r1, String r2, WorkerParameters r3) {
        return (ListenableWorker) m967createWorker(r1, r2, r3);
    }

    /* renamed from: createWorker, reason: collision with other method in class */
    public Void m967createWorker(Context r1, String r2, WorkerParameters r3) {
        return null;
    }
}
