package androidx.work.impl.background.systemjob;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.Context;
import android.os.Build;
import androidx.work.Configuration;
import androidx.work.Logger;
import androidx.work.impl.WorkDatabase;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u001a'\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0007\u0010\b\"\u0014\u0010\t\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\n\"\u0018\u0010\u000e\u001a\u00020\u000b*\u00020\u00008@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r\"\u001d\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f*\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Landroid/content/Context;", "context", "Landroidx/work/impl/WorkDatabase;", "workDatabase", "Landroidx/work/Configuration;", "configuration", _UrlKt.FRAGMENT_ENCODE_SET, "createErrorMessage", "(Landroid/content/Context;Landroidx/work/impl/WorkDatabase;Landroidx/work/Configuration;)Ljava/lang/String;", "TAG", "Ljava/lang/String;", "Landroid/app/job/JobScheduler;", "getWmJobScheduler", "(Landroid/content/Context;)Landroid/app/job/JobScheduler;", "wmJobScheduler", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/app/job/JobInfo;", "getSafePendingJobs", "(Landroid/app/job/JobScheduler;)Ljava/util/List;", "safePendingJobs", "work-runtime_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nJobSchedulerExt.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JobSchedulerExt.kt\nandroidx/work/impl/background/systemjob/JobSchedulerExtKt\n+ 2 LoggerExt.kt\nandroidx/work/LoggerExtKt\n*L\n1#1,123:1\n32#2:124\n*S KotlinDebug\n*F\n+ 1 JobSchedulerExt.kt\nandroidx/work/impl/background/systemjob/JobSchedulerExtKt\n*L\n67#1:124\n*E\n"})
/* loaded from: classes.dex */
public abstract class JobSchedulerExtKt {
    private static final String TAG = null;

    static {
        TAG = Logger.tagWithPrefix("SystemJobScheduler");
    }

    public static final String createErrorMessage(Context r11, WorkDatabase r12, Configuration r13) {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 31) goto L5;
        int r1 = 150;
    L6:
        int r122 = r12.workSpecDao().getScheduledWork().size();
        String r3 = "<faulty JobScheduler failed to getPendingJobs>";
        if (r0 < 34) goto L25;
        JobScheduler r02 = getWmJobScheduler(r11);
        List<JobInfo> r2 = getSafePendingJobs(r02);
        if (r2 == null) goto L30;
        List<JobInfo> r03 = SystemJobScheduler.getPendingJobs(r11, r02);
        int r32 = 0;
        if (r03 == null) goto L13;
        int r4 = r2.size() - r03.size();
    L14:
        String r04 = null;
        if (r4 != 0) goto L17;
        String r42 = null;
    L18:
        List<JobInfo> r112 = SystemJobScheduler.getPendingJobs(r11, (JobScheduler) r11.getSystemService("jobscheduler"));
        if (r112 == null) goto L21;
        r32 = r112.size();
    L21:
        if (r32 == 0) goto L24;
        r04 = r32 + " from WorkManager in the default namespace";
    L24:
        r3 = CollectionsKt.joinToString$default(CollectionsKt.listOfNotNull(new String[]{r2.size() + " jobs in \"androidx.work.systemjobscheduler\" namespace", r42, r04}), ",\n", null, null, 0, null, null, 62, null);
        goto L30
    L17:
        r42 = r4 + " of which are not owned by WorkManager";
        goto L18
    L13:
        r4 = 0;
    L30:
        return "JobScheduler " + r1 + " job limit exceeded.\nIn JobScheduler there are " + r3 + ".\nThere are " + r122 + " jobs tracked by WorkManager's database;\nthe Configuration limit is " + r13.getMaxSchedulerLimit() + '.';
    L25:
        List<JobInfo> r113 = SystemJobScheduler.getPendingJobs(r11, getWmJobScheduler(r11));
        if (r113 == null) goto L30;
        r3 = r113.size() + " jobs from WorkManager";
        goto L30
    L5:
        r1 = 100;
        goto L6
    }

    public static final List<JobInfo> getSafePendingJobs(JobScheduler r3) {
        return JobScheduler21.INSTANCE.getAllPendingJobs(r3);
    L4:
        th = move-exception;
        String r0 = TAG;
        Logger.get().error(r0, "getAllPendingJobs() is not reliable on this device.", th);
        return null;
    }

    public static final JobScheduler getWmJobScheduler(Context r2) {
        JobScheduler r22 = (JobScheduler) r2.getSystemService("jobscheduler");
        if (Build.VERSION.SDK_INT >= 34) goto L5;
        return r22;
    L5:
        return JobScheduler34.INSTANCE.forNamespace(r22);
    }
}
