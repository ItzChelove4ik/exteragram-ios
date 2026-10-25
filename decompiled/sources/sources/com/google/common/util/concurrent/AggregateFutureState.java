package com.google.common.util.concurrent;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.AbstractFuture;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* loaded from: classes5.dex */
abstract class AggregateFutureState<OutputT> extends AbstractFuture.TrustedFuture<OutputT> {
    private static final AtomicIntegerFieldUpdater<? super AggregateFutureState<?>> REMAINING_COUNT_UPDATER = null;
    private static final AtomicReferenceFieldUpdater<? super AggregateFutureState<?>, ? super Set<Throwable>> SEEN_EXCEPTIONS_UPDATER = null;
    volatile int remainingField;
    volatile Set<Throwable> seenExceptionsField;

    static {
        SEEN_EXCEPTIONS_UPDATER = AtomicReferenceFieldUpdater.newUpdater(AggregateFutureState.class, Set.class, "seenExceptionsField");
        REMAINING_COUNT_UPDATER = AtomicIntegerFieldUpdater.newUpdater(AggregateFutureState.class, "remainingField");
    }

    public AggregateFutureState(int r2) {
        this.seenExceptionsField = null;
        this.remainingField = r2;
    }

    public abstract void addInitialException(Set<Throwable> r1);

    public final void clearSeenExceptions() {
        this.seenExceptionsField = null;
    }

    public final int decrementRemainingAndGet() {
        return REMAINING_COUNT_UPDATER.decrementAndGet(this);
    }

    public final Set<Throwable> getOrInitSeenExceptions() {
        Set<Throwable> r0 = this.seenExceptionsField;
        if (r0 != null) goto L6;
        Set<Throwable> r02 = Sets.newConcurrentHashSet();
        addInitialException(r02);
        AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(SEEN_EXCEPTIONS_UPDATER, this, null, r02);
        Set<Throwable> r3 = this.seenExceptionsField;
        Objects.requireNonNull(r3);
        return r3;
    L6:
        return r0;
    }
}
