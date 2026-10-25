package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;

/* loaded from: classes5.dex */
abstract class AggregateFuture<InputT, OutputT> extends AggregateFutureState<OutputT> {
    private static final LazyLogger logger = null;
    private final boolean allMustSucceed;
    private final boolean collectsValues;
    private ImmutableCollection<? extends ListenableFuture<? extends InputT>> futures;

    public enum ReleaseResourcesReason extends Enum<ReleaseResourcesReason> {
        private static final /* synthetic */ ReleaseResourcesReason[] $VALUES = null;
        public static final ReleaseResourcesReason ALL_INPUT_FUTURES_PROCESSED = null;
        public static final ReleaseResourcesReason OUTPUT_FUTURE_DONE = null;

        private static /* synthetic */ ReleaseResourcesReason[] $values() {
            return new ReleaseResourcesReason[]{OUTPUT_FUTURE_DONE, ALL_INPUT_FUTURES_PROCESSED};
        }

        static {
            OUTPUT_FUTURE_DONE = new ReleaseResourcesReason("OUTPUT_FUTURE_DONE", 0);
            ALL_INPUT_FUTURES_PROCESSED = new ReleaseResourcesReason("ALL_INPUT_FUTURES_PROCESSED", 1);
            $VALUES = $values();
        }

        ReleaseResourcesReason(String r1, int r2) {
        }

        public static ReleaseResourcesReason valueOf(String r1) {
            return (ReleaseResourcesReason) Enum.valueOf(ReleaseResourcesReason.class, r1);
        }

        public static ReleaseResourcesReason[] values() {
            return (ReleaseResourcesReason[]) $VALUES.clone();
        }
    }

    public static /* synthetic */ void $r8$lambda$ZovyBvFdRfrc58YvO1Ygd_lePSs(AggregateFuture r0, ImmutableCollection r1) {
        r0.decrementCountAndMaybeComplete(r1);
    }

    public static /* synthetic */ void $r8$lambda$b6iaIjtd22FkSuzCmWyTO5V1cdM(AggregateFuture r0, int r1, ListenableFuture r2) {
        r0.processAllMustSucceedDoneFuture(r1, r2);
    }

    static {
        logger = new LazyLogger(AggregateFuture.class);
    }

    public AggregateFuture(ImmutableCollection<? extends ListenableFuture<? extends InputT>> r2, boolean r3, boolean r4) {
        super(r2.size());
        this.futures = (ImmutableCollection) Preconditions.checkNotNull(r2);
        this.allMustSucceed = r3;
        this.collectsValues = r4;
    }

    private static boolean addCausalChain(Set<Throwable> r1, Throwable r2) {
    L2:
        if (r2 == null) goto L8;
        if (r1.add(r2) == false) goto L5;
        r2 = r2.getCause();
        goto L2
    L5:
        return false;
    L8:
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void collectValueFromNonCancelledFuture(int r1, ListenableFuture<? extends InputT> r2) {
        collectOneValue(r1, Uninterruptibles.getUninterruptibly(r2));     // Catch: Throwable -> L4 ExecutionException -> L6
        return;
    L6:
        e = move-exception;
        handleException(e.getCause());
        return;
    L4:
        th = move-exception;
        handleException(th);
    }

    private void decrementCountAndMaybeComplete(ImmutableCollection<? extends ListenableFuture<? extends InputT>> r4) {
        int r0 = decrementRemainingAndGet();
        if (r0 < 0) goto L5;
        boolean r1 = true;
    L6:
        Preconditions.checkState(r1, "Less than 0 remaining futures");
        if (r0 != 0) goto L10;
        processCompleted(r4);
        return;
    L10:
        return;
    L5:
        r1 = false;
        goto L6
    }

    private void handleException(Throwable r2) {
        Preconditions.checkNotNull(r2);
        if (this.allMustSucceed == false) goto L11;
        if (setException(r2) == true) goto L11;
        if (addCausalChain(getOrInitSeenExceptions(), r2) == false) goto L11;
        log(r2);
        return;
    L11:
        if ((r2 instanceof Error) == false) goto L14;
        log(r2);
        return;
    }

    private static void log(Throwable r3) {
        if ((r3 instanceof Error) == false) goto L5;
        String r0 = "Input Future failed with Error";
    L6:
        logger.get().log(Level.SEVERE, r0, r3);
        return;
    L5:
        r0 = "Got more than one input Future failure. Logging failures after the first";
        goto L6
    }

    private void processAllMustSucceedDoneFuture(int r3, ListenableFuture<? extends InputT> r4) {
    L6:
        th = move-exception;
        decrementCountAndMaybeComplete(null);
        throw th;
    L4:
        if (r4.isCancelled() == false) goto L8;
        this.futures = null;     // Catch: Throwable -> L6
        cancel(false);     // Catch: Throwable -> L6
    L9:
        decrementCountAndMaybeComplete(null);
        return;
    L8:
        collectValueFromNonCancelledFuture(r3, r4);     // Catch: Throwable -> L6
        goto L9
    }

    private void processCompleted(ImmutableCollection<? extends ListenableFuture<? extends InputT>> r4) {
        if (r4 == null) goto L10;
        UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> r42 = r4.iterator();
        int r0 = 0;
    L5:
        if (r42.hasNext() == false) goto L10;
        ListenableFuture<? extends InputT> r1 = r42.next();
        if (r1.isCancelled() == true) goto L9;
        collectValueFromNonCancelledFuture(r0, r1);
    L9:
        r0 = r0 + 1;
    L10:
        clearSeenExceptions();
        handleAllCompleted();
        releaseResources(ReleaseResourcesReason.ALL_INPUT_FUTURES_PROCESSED);
    }

    @Override // com.google.common.util.concurrent.AggregateFutureState
    public final void addInitialException(Set<Throwable> r2) {
        Preconditions.checkNotNull(r2);
        if (isCancelled() == true) goto L6;
        Throwable r1 = tryInternalFastPathGetFailure();
        Objects.requireNonNull(r1);
        addCausalChain(r2, r1);
        return;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final void afterDone() {
        super.afterDone();
        ImmutableCollection<? extends ListenableFuture<? extends InputT>> r0 = this.futures;
        releaseResources(ReleaseResourcesReason.OUTPUT_FUTURE_DONE);
        boolean r1 = isCancelled();
        if (r0 == null) goto L5;
        boolean r2 = true;
    L7:
        if ((r1 & r2) == false) goto L12;
        boolean r3 = wasInterrupted();
        UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> r02 = r0.iterator();
    L10:
        if (r02.hasNext() == false) goto L14;
        r02.next().cancel(r3);
        goto L10
    L14:
        return;
    L12:
        return;
    L5:
        r2 = false;
        goto L7
    }

    public abstract void collectOneValue(int r1, InputT r2);

    public abstract void handleAllCompleted();

    public final void init() {
        Objects.requireNonNull(this.futures);
        if (this.futures.isEmpty() == false) goto L6;
        handleAllCompleted();
        return;
    L6:
        boolean r0 = this.allMustSucceed;
        ImmutableCollection<? extends ListenableFuture<? extends InputT>> r1 = this.futures;
        if (r0 == false) goto L17;
        UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> r02 = r1.iterator();
        int r12 = 0;
    L10:
        if (r02.hasNext() == false) goto L27;
        ListenableFuture<? extends InputT> r2 = r02.next();
        int r3 = r12 + 1;
        if (r2.isDone() == false) goto L14;
        processAllMustSucceedDoneFuture(r12, r2);
    L15:
        r12 = r3;
        goto L10
    L14:
        r2.addListener(new AggregateFuture$$ExternalSyntheticLambda0(this, r12, r2), MoreExecutors.directExecutor());
        goto L15
    L27:
        return;
    L17:
        if (this.collectsValues == false) goto L19;
        ImmutableCollection<? extends ListenableFuture<? extends InputT>> r03 = r1;
    L20:
        AggregateFuture$$ExternalSyntheticLambda1 r22 = new AggregateFuture$$ExternalSyntheticLambda1(this, r03);
        UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> r13 = r1.iterator();
    L22:
        if (r13.hasNext() == false) goto L37;
        ListenableFuture<? extends InputT> r32 = r13.next();
        if (r32.isDone() == true) goto L25;
        r32.addListener(r22, MoreExecutors.directExecutor());
        goto L22
    L25:
        decrementCountAndMaybeComplete(r03);
        goto L22
    L37:
        return;
    L19:
        r03 = null;
        goto L20
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final String pendingToString() {
        ImmutableCollection<? extends ListenableFuture<? extends InputT>> r0 = this.futures;
        if (r0 == null) goto L7;
        return "futures=" + r0;
    L7:
        return super.pendingToString();
    }

    public void releaseResources(ReleaseResourcesReason r1) {
        Preconditions.checkNotNull(r1);
        this.futures = null;
    }
}
