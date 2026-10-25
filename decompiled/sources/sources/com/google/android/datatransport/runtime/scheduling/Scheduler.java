package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.TransportScheduleCallback;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;

/* loaded from: classes.dex */
public interface Scheduler {
    void schedule(TransportContext r1, EventInternal r2, TransportScheduleCallback r3);
}
