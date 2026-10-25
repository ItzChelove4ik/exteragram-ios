package com.google.android.datatransport.runtime;

import android.content.Context;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.TransportFactory;
import com.google.android.datatransport.TransportScheduleCallback;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.scheduling.Scheduler;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer;
import com.google.android.datatransport.runtime.time.Clock;
import java.util.Collections;
import java.util.Set;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public class TransportRuntime implements TransportInternal {
    private static volatile TransportRuntimeComponent instance;
    private final Clock eventClock;
    private final Scheduler scheduler;
    private final Uploader uploader;
    private final Clock uptimeClock;

    static {
    }

    public TransportRuntime(Clock r1, Clock r2, Scheduler r3, Uploader r4, WorkInitializer r5) {
        this.eventClock = r1;
        this.uptimeClock = r2;
        this.scheduler = r3;
        this.uploader = r4;
        r5.ensureContextsScheduled();
    }

    private EventInternal convert(SendRequest r4) {
        EventInternal.Builder r3 = EventInternal.builder().setEventMillis(this.eventClock.getTime()).setUptimeMillis(this.uptimeClock.getTime()).setTransportName(r4.getTransportName()).setEncodedPayload(new EncodedPayload(r4.getEncoding(), r4.getPayload())).setCode(r4.getEvent().getCode());
        if (r4.getEvent().getProductData() != null) goto L5;
    L7:
        r4.getEvent().getEventContext();
        return r3.build();
    L5:
        if (r4.getEvent().getProductData().getProductId() == null) goto L7;
        r3.setProductId(r4.getEvent().getProductData().getProductId());
        goto L7
    }

    public static TransportRuntime getInstance() {
        TransportRuntimeComponent r0 = instance;
        if (r0 != null) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Not initialized!");
        return null;
    L5:
        return r0.getTransportRuntime();
    }

    private static Set<Encoding> getSupportedEncodings(Destination r1) {
        if ((r1 instanceof EncodedDestination) == false) goto L7;
        return Collections.unmodifiableSet(((EncodedDestination) r1).getSupportedEncodings());
    L7:
        return Collections.singleton(Encoding.of("proto"));
    }

    public static void initialize(Context r2) {
        if (instance == null) goto L5;
        return;
    L5:
        monitor-enter(TransportRuntime.class);
    L9:
        th = move-exception;
        throw th;
    L7:
        if (instance != null) goto L11;
        instance = DaggerTransportRuntimeComponent.builder().setApplicationContext(r2).build();     // Catch: Throwable -> L9
    L11:
        monitor-exit(TransportRuntime.class);     // Catch: Throwable -> L9
    }

    public Uploader getUploader() {
        return this.uploader;
    }

    public TransportFactory newFactory(Destination r5) {
        return new TransportFactoryImpl(getSupportedEncodings(r5), TransportContext.builder().setBackendName(r5.getName()).setExtras(r5.getExtras()).build(), this);
    }

    @Override // com.google.android.datatransport.runtime.TransportInternal
    public void send(SendRequest r4, TransportScheduleCallback r5) {
        this.scheduler.schedule(r4.getTransportContext().withPriority(r4.getEvent().getPriority()), convert(r4), r5);
    }
}
