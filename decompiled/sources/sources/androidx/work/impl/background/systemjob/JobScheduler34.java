package androidx.work.impl.background.systemjob;

import android.app.job.JobScheduler;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005¨\u0006\u0007"}, d2 = {"Landroidx/work/impl/background/systemjob/JobScheduler34;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "forNamespace", "Landroid/app/job/JobScheduler;", "jobScheduler", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
final class JobScheduler34 {
    public static final JobScheduler34 INSTANCE = null;

    static {
        INSTANCE = new JobScheduler34();
    }

    private JobScheduler34() {
    }

    public final JobScheduler forNamespace(JobScheduler r1) {
        return r1.forNamespace("androidx.work.systemjobscheduler");
    }
}
