package com.google.firebase.crashlytics.internal.metadata;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class RolloutAssignmentList {
    private final int maxEntries;
    private final List<RolloutAssignment> rolloutsState;

    public RolloutAssignmentList(int r2) {
        this.rolloutsState = new ArrayList();
        this.maxEntries = r2;
    }

    public List<CrashlyticsReport.Session.Event.RolloutAssignment> getReportRolloutsState() {
        List<RolloutAssignment> r3 = getRolloutAssignmentList();
        ArrayList r0 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= r3.size()) goto L6;
        r0.add(r3.get(r1).toReportProto());
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    public synchronized List<RolloutAssignment> getRolloutAssignmentList() {
        monitor-enter(this);
        List<RolloutAssignment> r0 = Collections.unmodifiableList(new ArrayList(this.rolloutsState));     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized boolean updateRolloutAssignmentList(List<RolloutAssignment> r4) {
        monitor-enter(this);
        this.rolloutsState.clear();     // Catch: Throwable -> L9
        if (r4.size() <= this.maxEntries) goto L11;
        Logger.getLogger().w("Ignored 0 entries when adding rollout assignments. Maximum allowable: " + this.maxEntries);     // Catch: Throwable -> L9
        boolean r42 = this.rolloutsState.addAll(r4.subList(0, this.maxEntries));     // Catch: Throwable -> L9
        monitor-exit(this);
        return r42;
    L11:
        boolean r43 = this.rolloutsState.addAll(r4);     // Catch: Throwable -> L9
        monitor-exit(this);
        return r43;
    L9:
        th = move-exception;
        throw th;
    }
}
