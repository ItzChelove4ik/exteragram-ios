package org.telegram.messenger.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import me.vkryl.core.reference.ReferenceMap;
import org.telegram.messenger.AndroidUtilities;

/* loaded from: classes6.dex */
public final class LeakDetector {
    private static volatile LeakDetector instance;
    private final Runnable checkRunnable;
    private final Map<Class<?>, Integer> pendingRecheck;
    private final ReferenceMap<Class<?>, Object> registry;
    private final Set<Class<?>> reportedLeaks;
    private boolean running;

    /* renamed from: org.telegram.messenger.utils.LeakDetector$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ LeakDetector this$0;

        public AnonymousClass1(LeakDetector r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            LeakDetector.m5196$$Nest$mcheck(this.this$0);
            if (LeakDetector.m5195$$Nest$fgetrunning(this.this$0) == false) goto L6;
            AndroidUtilities.runOnUIThread(this, 1000);
            return;
        }
    }

    /* renamed from: $r8$lambda$BLt-Fp7qXK7gcUBSz7s2wbGpwoU, reason: not valid java name */
    public static /* synthetic */ void m5194$r8$lambda$BLtFp7qXK7gcUBSz7s2wbGpwoU(LeakDetector r0, Class r1) {
        r0.lambda$check$0(r1);
    }

    /* renamed from: -$$Nest$fgetrunning, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5195$$Nest$fgetrunning(LeakDetector r0) {
        return r0.running;
    }

    /* renamed from: -$$Nest$mcheck, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m5196$$Nest$mcheck(LeakDetector r0) {
        r0.check();
    }

    private LeakDetector() {
        this.registry = new ReferenceMap(false);
        this.reportedLeaks = new HashSet();
        this.pendingRecheck = new HashMap();
        this.checkRunnable = new AnonymousClass1(this);
    }

    private void check() {
        Set<Class<?>> r0 = this.registry.keySetUnchecked();
        if (r0 == null) goto L16;
        ArrayList r1 = new ArrayList(r0);
        int r02 = r1.size();
        int r2 = 0;
    L6:
        if (r2 >= r02) goto L27;
        Object r3 = r1.get(r2);
        r2 = r2 + 1;
        Class<?> r32 = (Class) r3;
        if (this.reportedLeaks.contains(r32) == true) goto L6;
        int r4 = countLiveInstances(r32);
        Map<Class<?>, Integer> r5 = this.pendingRecheck;
        if (r4 >= 5) goto L13;
        r5.remove(r32);
        goto L6
    L13:
        if (r5.containsKey(r32) == true) goto L6;
        this.pendingRecheck.put(r32, Integer.valueOf(r4));
        System.gc();
        AndroidUtilities.runOnUIThread(new LeakDetector$$ExternalSyntheticLambda0(this, r32), 2000);
        goto L6
    L27:
        return;
    }

    private void confirmLeak(Class<?> r3) {
        this.pendingRecheck.remove(r3);
        if (this.reportedLeaks.contains(r3) == false) goto L6;
        return;
    L6:
        if (countLiveInstances(r3) < 5) goto L9;
        this.reportedLeaks.add(r3);
        return;
    }

    private int countLiveInstances(Class<?> r2) {
        Iterator<Object> r1 = this.registry.iterator(r2);
        int r22 = 0;
        if (r1 != null) goto L6;
        return 0;
    L6:
        if (r1.hasNext() == false) goto L8;
        r1.next();
        r22 = r22 + 1;
        goto L6
    L8:
        return r22;
    }

    public static LeakDetector getInstance() {
        if (instance != null) goto L16;
        monitor-enter(LeakDetector.class);
    L9:
        th = move-exception;
        throw th;
    L7:
        if (instance != null) goto L11;
        instance = new LeakDetector();     // Catch: Throwable -> L9
    L11:
        monitor-exit(LeakDetector.class);     // Catch: Throwable -> L9
    L16:
        return instance;
    }

    private /* synthetic */ void lambda$check$0(Class r1) {
        confirmLeak(r1);
    }

    public <T> void add(T r2) {
        this.registry.add(r2.getClass(), r2);
    }

    public void start() {
        if (this.running == false) goto L5;
        return;
    L5:
        this.running = true;
        AndroidUtilities.runOnUIThread(this.checkRunnable, 1000);
    }

    public void stop() {
        if (this.running == true) goto L5;
        return;
    L5:
        this.running = false;
        AndroidUtilities.cancelRunOnUIThread(this.checkRunnable);
    }
}
