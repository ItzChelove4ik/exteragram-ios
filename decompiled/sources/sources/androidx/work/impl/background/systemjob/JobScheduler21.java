package androidx.work.impl.background.systemjob;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import java.util.List;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Landroidx/work/impl/background/systemjob/JobScheduler21;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getAllPendingJobs", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/app/job/JobInfo;", "jobScheduler", "Landroid/app/job/JobScheduler;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
final class JobScheduler21 {
    public static final JobScheduler21 INSTANCE = null;

    static {
        INSTANCE = new JobScheduler21();
    }

    private JobScheduler21() {
    }

    public final List<JobInfo> getAllPendingJobs(JobScheduler r1) {
        return r1.getAllPendingJobs();
    }
}
