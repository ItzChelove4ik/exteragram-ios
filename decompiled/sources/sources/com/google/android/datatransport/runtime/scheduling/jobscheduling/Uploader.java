package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.runtime.EncodedPayload;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.backends.BackendRequest;
import com.google.android.datatransport.runtime.backends.BackendResponse;
import com.google.android.datatransport.runtime.backends.TransportBackend;
import com.google.android.datatransport.runtime.firebase.transport.ClientMetrics;
import com.google.android.datatransport.runtime.firebase.transport.LogEventDropped;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.scheduling.persistence.ClientHealthMetricsStore;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.scheduling.persistence.PersistedEvent;
import com.google.android.datatransport.runtime.synchronization.SynchronizationException;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import com.google.android.datatransport.runtime.time.Clock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public class Uploader {
    private final BackendRegistry backendRegistry;
    private final ClientHealthMetricsStore clientHealthMetricsStore;
    private final Clock clock;
    private final Context context;
    private final EventStore eventStore;
    private final Executor executor;
    private final SynchronizationGuard guard;
    private final Clock uptimeClock;
    private final WorkScheduler workScheduler;

    /* renamed from: $r8$lambda$-N0gxM4A_MEjjD9_WiaibfgKGfU, reason: not valid java name */
    public static /* synthetic */ Iterable m1804$r8$lambda$N0gxM4A_MEjjD9_WiaibfgKGfU(Uploader r0, TransportContext r1) {
        return r0.eventStore.loadBatch(r1);
    }

    public static /* synthetic */ Object $r8$lambda$KczLz_Q_NejuT7e4eizXNubl_f4(Uploader r2, Iterable r3, TransportContext r4, long r5) {
        r2.eventStore.recordFailure(r3);
        r2.eventStore.recordNextCallTime(r4, r2.clock.getTime() + r5);
        return null;
    }

    /* renamed from: $r8$lambda$S382DjgDn_y-vJnuiSFvtp3hSXI, reason: not valid java name */
    public static /* synthetic */ Object m1805$r8$lambda$S382DjgDn_yvJnuiSFvtp3hSXI(Uploader r0) {
        r0.clientHealthMetricsStore.resetClientMetrics();
        return null;
    }

    /* renamed from: $r8$lambda$V6bMmfv-BBLM8AkTEtAvVSje3m4, reason: not valid java name */
    public static /* synthetic */ Boolean m1806$r8$lambda$V6bMmfvBBLM8AkTEtAvVSje3m4(Uploader r0, TransportContext r1) {
        return Boolean.valueOf(r0.eventStore.hasPendingEventsFor(r1));
    }

    public static /* synthetic */ Object $r8$lambda$WsHpQpWMGUydVPnrAR64q25St1g(Uploader r0, Iterable r1) {
        r0.eventStore.recordSuccess(r1);
        return null;
    }

    public static /* synthetic */ Object $r8$lambda$YYSBWTuDkulYc4ll_Q3G1rbN9O8(Uploader r0, TransportContext r1, int r2) {
        r0.workScheduler.schedule(r1, r2 + 1);
        return null;
    }

    public static /* synthetic */ Object $r8$lambda$aH8iv_GA6F4qxPrDvFygQzqqflI(Uploader r3, TransportContext r4, long r5) {
        r3.eventStore.recordNextCallTime(r4, r3.clock.getTime() + r5);
        return null;
    }

    public static /* synthetic */ Object $r8$lambda$agWmeBeuVmAYcPatmWqsXMsSeBk(Uploader r5, Map r6) {
        r5.getClass();
        Iterator r62 = r6.entrySet().iterator();
    L4:
        if (r62.hasNext() == false) goto L6;
        r5.clientHealthMetricsStore.recordLogEventDropped(((Integer) r0.getValue()).intValue(), LogEventDropped.Reason.INVALID_PAYLOD, (String) ((Map.Entry) r62.next()).getKey());
        goto L4
    L6:
        return null;
    }

    public static /* synthetic */ void $r8$lambda$eWdkU2LmywL4gF880jwxOwLkJfI(Uploader r3, TransportContext r4, int r5, Runnable r6) {
        r3.getClass();
        SynchronizationGuard r0 = r3.guard;     // Catch: Throwable -> L6 SynchronizationException -> L11
        EventStore r1 = r3.eventStore;     // Catch: Throwable -> L6 SynchronizationException -> L11
        Objects.requireNonNull(r1);     // Catch: Throwable -> L6 SynchronizationException -> L11
        r0.runCriticalSection(new Uploader$$ExternalSyntheticLambda1(r1));     // Catch: Throwable -> L6 SynchronizationException -> L11
        if (r3.isNetworkAvailable() == true) goto L8;
        r3.guard.runCriticalSection(new Uploader$$ExternalSyntheticLambda2(r3, r4, r5));     // Catch: Throwable -> L6 SynchronizationException -> L11
    L9:
        r6.run();
        return;
    L8:
        r3.logAndUpdateState(r4, r5);     // Catch: Throwable -> L6 SynchronizationException -> L11
    L11:
        r3.workScheduler.schedule(r4, r5 + 1);     // Catch: Throwable -> L6
        r6.run();
        return;
    L6:
        th = move-exception;
        r6.run();
        throw th;
    }

    public Uploader(Context r1, BackendRegistry r2, EventStore r3, WorkScheduler r4, Executor r5, SynchronizationGuard r6, Clock r7, Clock r8, ClientHealthMetricsStore r9) {
        this.context = r1;
        this.backendRegistry = r2;
        this.eventStore = r3;
        this.workScheduler = r4;
        this.executor = r5;
        this.guard = r6;
        this.clock = r7;
        this.uptimeClock = r8;
        this.clientHealthMetricsStore = r9;
    }

    public EventInternal createMetricsEvent(TransportBackend r5) {
        SynchronizationGuard r0 = this.guard;
        ClientHealthMetricsStore r1 = this.clientHealthMetricsStore;
        Objects.requireNonNull(r1);
        ClientMetrics r02 = (ClientMetrics) r0.runCriticalSection(new Uploader$$ExternalSyntheticLambda10(r1));
        return r5.decorate(EventInternal.builder().setEventMillis(this.clock.getTime()).setUptimeMillis(this.uptimeClock.getTime()).setTransportName("GDT_CLIENT_METRICS").setEncodedPayload(new EncodedPayload(Encoding.of("proto"), r02.toByteArray())).build());
    }

    public boolean isNetworkAvailable() {
        NetworkInfo r1 = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (r1 != null) goto L5;
        return false;
    L5:
        if (r1.isConnected() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public BackendResponse logAndUpdateState(TransportContext r13, int r14) {
        TransportBackend r0 = this.backendRegistry.get(r13.getBackendName());
        BackendResponse r3 = BackendResponse.ok(0);
        long r8 = 0;
    L3:
        boolean r1 = ((Boolean) this.guard.runCriticalSection(new Uploader$$ExternalSyntheticLambda3(this, r13))).booleanValue();
        SynchronizationGuard r2 = this.guard;
        if (r1 == false) goto L40;
        Iterable r6 = (Iterable) r2.runCriticalSection(new Uploader$$ExternalSyntheticLambda4(this, r13));
        if (r6.iterator().hasNext() == false) goto L7;
        if (r0 != null) goto L11;
        Logging.d("Uploader", "Unknown backend for %s, deleting event batch for it...", r13);
        BackendResponse r12 = BackendResponse.fatalError();
    L10:
        r3 = r12;
        BackendResponse.Status r15 = r3.getStatus();
        BackendResponse.Status r22 = BackendResponse.Status.TRANSIENT_ERROR;
        SynchronizationGuard r10 = this.guard;
        if (r15 == r22) goto L21;
        Uploader r5 = this;
        TransportContext r7 = r13;
        r10.runCriticalSection(new Uploader$$ExternalSyntheticLambda6(r5, r6));
        if (r3.getStatus() != BackendResponse.Status.OK) goto L30;
        long r122 = Math.max(r8, r3.getNextRequestWaitMillis());
        if (r7.shouldUploadClientHealthMetrics() == false) goto L28;
        r5.guard.runCriticalSection(new Uploader$$ExternalSyntheticLambda7(r5));
    L28:
        r8 = r122;
    L39:
        Uploader this = r5;
        r13 = r7;
        goto L3
    L30:
        if (r3.getStatus() != BackendResponse.Status.INVALID_PAYLOAD) goto L39;
        HashMap r123 = new HashMap();
        Iterator r132 = r6.iterator();
    L33:
        if (r132.hasNext() == false) goto L38;
        String r16 = ((PersistedEvent) r132.next()).getEvent().getTransportName();
        if (r123.containsKey(r16) == false) goto L36;
        r123.put(r16, Integer.valueOf(((Integer) r123.get(r16)).intValue() + 1));
        goto L33
    L36:
        r123.put(r16, 1);
        goto L33
    L38:
        r5.guard.runCriticalSection(new Uploader$$ExternalSyntheticLambda8(r5, r123));
        goto L39
    L21:
        Uploader r52 = this;
        TransportContext r72 = r13;
        r10.runCriticalSection(new Uploader$$ExternalSyntheticLambda5(r52, r6, r72, r8));
        r52.workScheduler.schedule(r72, r14 + 1, true);
        return r3;
    L11:
        ArrayList r17 = new ArrayList();
        Iterator r23 = r6.iterator();
    L13:
        if (r23.hasNext() == false) goto L16;
        r17.add(((PersistedEvent) r23.next()).getEvent());
        goto L13
    L16:
        if (r13.shouldUploadClientHealthMetrics() == false) goto L18;
        r17.add(this.createMetricsEvent(r0));
    L18:
        r12 = r0.send(BackendRequest.builder().setEvents(r17).setExtras(r13.getExtras()).build());
        goto L10
    L7:
        return r3;
    L40:
        r2.runCriticalSection(new Uploader$$ExternalSyntheticLambda9(this, r13, r8));
        return r3;
    }

    public void upload(TransportContext r3, int r4, Runnable r5) {
        this.executor.execute(new Uploader$$ExternalSyntheticLambda0(this, r3, r4, r5));
    }
}
