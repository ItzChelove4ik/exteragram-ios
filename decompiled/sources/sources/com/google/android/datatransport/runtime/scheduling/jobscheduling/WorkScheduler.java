package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.TransportContext;

/* loaded from: classes.dex */
public interface WorkScheduler {
    void schedule(TransportContext r1, int r2);

    void schedule(TransportContext r1, int r2, boolean r3);
}
