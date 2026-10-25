package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;
import android.util.Base64;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.util.PriorityMapping;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.zip.Adler32;

/* loaded from: classes.dex */
public class JobInfoScheduler implements WorkScheduler {
    private final SchedulerConfig config;
    private final Context context;
    private final EventStore eventStore;

    public JobInfoScheduler(Context r1, EventStore r2, SchedulerConfig r3) {
        this.context = r1;
        this.eventStore = r2;
        this.config = r3;
    }

    private boolean isJobServiceOn(JobScheduler r4, int r5, int r6) {
        Iterator<JobInfo> r3 = r4.getAllPendingJobs().iterator();
    L4:
        if (r3.hasNext() == false) goto L10;
        JobInfo r42 = r3.next();
        int r1 = r42.getExtras().getInt("attemptNumber");
        if (r42.getId() != r5) goto L4;
        if (r1 < r6) goto L10;
        return true;
    L10:
        return false;
    }

    public int getJobId(TransportContext r4) {
        Adler32 r0 = new Adler32();
        r0.update(this.context.getPackageName().getBytes(Charset.forName("UTF-8")));
        r0.update(r4.getBackendName().getBytes(Charset.forName("UTF-8")));
        r0.update(ByteBuffer.allocate(4).putInt(PriorityMapping.toInt(r4.getPriority())).array());
        if (r4.getExtras() == null) goto L6;
        r0.update(r4.getExtras());
    L6:
        return (int) r0.getValue();
    }

    @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler
    public void schedule(TransportContext r2, int r3) {
        schedule(r2, r3, false);
    }

    @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler
    public void schedule(TransportContext r11, int r12, boolean r13) {
        ComponentName r0 = new ComponentName(this.context, JobInfoSchedulerService.class);
        JobScheduler r1 = (JobScheduler) this.context.getSystemService("jobscheduler");
        int r2 = getJobId(r11);
        if (r13 == false) goto L5;
    L8:
        long r7 = this.eventStore.getNextCallTime(r11);
        JobInfo.Builder r122 = this.config.configureJob(new JobInfo.Builder(r2, r0), r11.getPriority(), r7, r12);
        PersistableBundle r132 = new PersistableBundle();
        r132.putInt("attemptNumber", r12);
        r132.putString("backendName", r11.getBackendName());
        r132.putInt(PluginsConstants.MenuItemProperties.PRIORITY, PriorityMapping.toInt(r11.getPriority()));
        if (r11.getExtras() == null) goto L11;
        r132.putString("extras", Base64.encodeToString(r11.getExtras(), 0));
    L11:
        r122.setExtras(r132);
        Logging.d("JobInfoScheduler", "Scheduling upload for context %s with jobId=%d in %dms(Backend next call timestamp %d). Attempt %d", new Object[]{r11, Integer.valueOf(r2), Long.valueOf(this.config.getScheduleDelay(r11.getPriority(), r7, r12)), Long.valueOf(r7), Integer.valueOf(r12)});
        r1.schedule(r122.build());
        return;
    L5:
        if (isJobServiceOn(r1, r2, r12) == false) goto L8;
        Logging.d("JobInfoScheduler", "Upload for context %s is already scheduled. Returning...", r11);
    }
}
