package com.google.firebase.remoteconfig.interop.rollouts;

import java.util.Set;

/* loaded from: classes.dex */
public abstract class RolloutsState {
    public RolloutsState() {
    }

    public static RolloutsState create(Set<RolloutAssignment> r1) {
        return new AutoValue_RolloutsState(r1);
    }

    public abstract Set<RolloutAssignment> getRolloutAssignments();
}
