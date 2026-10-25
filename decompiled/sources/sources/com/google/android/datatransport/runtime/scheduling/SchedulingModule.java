package com.google.android.datatransport.runtime.scheduling;

import android.content.Context;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.JobInfoScheduler;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.time.Clock;

/* loaded from: classes.dex */
public abstract class SchedulingModule {
    public static WorkScheduler workScheduler(Context r0, EventStore r1, SchedulerConfig r2, Clock r3) {
        return new JobInfoScheduler(r0, r1, r2);
    }
}
