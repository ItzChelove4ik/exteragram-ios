package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.TransportScheduleCallback;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultScheduler$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ DefaultScheduler f$0;
    public final /* synthetic */ TransportContext f$1;
    public final /* synthetic */ TransportScheduleCallback f$2;
    public final /* synthetic */ EventInternal f$3;

    public /* synthetic */ DefaultScheduler$$ExternalSyntheticLambda0(DefaultScheduler r1, TransportContext r2, TransportScheduleCallback r3, EventInternal r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DefaultScheduler.$r8$lambda$e6JsNcl7eJ_uvseaJMOS9AGgb2A(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
