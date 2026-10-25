package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Base64;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.android.datatransport.runtime.util.PriorityMapping;

/* loaded from: classes4.dex */
public class JobInfoSchedulerService extends JobService {
    /* renamed from: $r8$lambda$BZ-xzjdP8f9ci1Poc-BjjP4jQ-k, reason: not valid java name */
    public static /* synthetic */ void m1803$r8$lambda$BZxzjdP8f9ci1PocBjjP4jQk(JobInfoSchedulerService r1, JobParameters r2) {
        r1.jobFinished(r2, false);
    }

    public JobInfoSchedulerService() {
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters r6) {
        String r0 = r6.getExtras().getString("backendName");
        String r1 = r6.getExtras().getString("extras");
        int r2 = r6.getExtras().getInt(PluginsConstants.MenuItemProperties.PRIORITY);
        int r3 = r6.getExtras().getInt("attemptNumber");
        TransportRuntime.initialize(getApplicationContext());
        TransportContext.Builder r02 = TransportContext.builder().setBackendName(r0).setPriority(PriorityMapping.valueOf(r2));
        if (r1 == null) goto L5;
        r02.setExtras(Base64.decode(r1, 0));
    L5:
        TransportRuntime.getInstance().getUploader().upload(r02.build(), r3, new JobInfoSchedulerService$$ExternalSyntheticLambda0(this, r6));
        return true;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters r1) {
        return true;
    }
}
