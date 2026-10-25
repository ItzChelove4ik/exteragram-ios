package com.google.firebase.remoteconfig.interop.rollouts;

import c.f$$ExternalSyntheticBUOutline2;
import java.util.Set;

/* loaded from: classes.dex */
final class AutoValue_RolloutsState extends RolloutsState {
    private final Set<RolloutAssignment> rolloutAssignments;

    public AutoValue_RolloutsState(Set<RolloutAssignment> r1) {
        if (r1 == null) goto L6;
        this.rolloutAssignments = r1;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("Null rolloutAssignments");
        throw null;
    }

    public boolean equals(Object r2) {
        if (r2 != this) goto L6;
        return true;
    L6:
        if ((r2 instanceof RolloutsState) == true) goto L8;
        return false;
    L8:
        return this.rolloutAssignments.equals(((RolloutsState) r2).getRolloutAssignments());
    }

    @Override // com.google.firebase.remoteconfig.interop.rollouts.RolloutsState
    public Set<RolloutAssignment> getRolloutAssignments() {
        return this.rolloutAssignments;
    }

    public int hashCode() {
        return this.rolloutAssignments.hashCode() ^ 1000003;
    }

    public String toString() {
        return "RolloutsState{rolloutAssignments=" + this.rolloutAssignments + "}";
    }
}
