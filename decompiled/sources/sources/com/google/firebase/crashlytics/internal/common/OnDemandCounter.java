package com.google.firebase.crashlytics.internal.common;

import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public final class OnDemandCounter {
    private final AtomicInteger droppedOnDemandExceptions;
    private final AtomicInteger recordedOnDemandExceptions;

    public OnDemandCounter() {
        this.recordedOnDemandExceptions = new AtomicInteger();
        this.droppedOnDemandExceptions = new AtomicInteger();
    }

    public void incrementDroppedOnDemandExceptions() {
        this.droppedOnDemandExceptions.getAndIncrement();
    }

    public void incrementRecordedOnDemandExceptions() {
        this.recordedOnDemandExceptions.getAndIncrement();
    }

    public void resetDroppedOnDemandExceptions() {
        this.droppedOnDemandExceptions.set(0);
    }
}
