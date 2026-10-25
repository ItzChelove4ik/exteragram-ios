package com.google.common.base;

import java.util.Iterator;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
abstract class AbstractIterator<T> implements Iterator<T> {
    private T next;
    private State state;

    public enum State extends Enum<State> {
        private static final /* synthetic */ State[] $VALUES = null;
        public static final State DONE = null;
        public static final State FAILED = null;
        public static final State NOT_READY = null;
        public static final State READY = null;

        private static /* synthetic */ State[] $values() {
            return new State[]{READY, NOT_READY, DONE, FAILED};
        }

        static {
            READY = new State("READY", 0);
            NOT_READY = new State("NOT_READY", 1);
            DONE = new State("DONE", 2);
            FAILED = new State("FAILED", 3);
            $VALUES = $values();
        }

        State(String r1, int r2) {
        }

        public static State valueOf(String r1) {
            return (State) Enum.valueOf(State.class, r1);
        }

        public static State[] values() {
            return (State[]) $VALUES.clone();
        }
    }

    public AbstractIterator() {
        this.state = State.NOT_READY;
    }

    private boolean tryToComputeNext() {
        this.state = State.FAILED;
        this.next = computeNext();
        if (this.state == State.DONE) goto L6;
        this.state = State.READY;
        return true;
    L6:
        return false;
    }

    public abstract T computeNext();

    public final T endOfData() {
        this.state = State.DONE;
        return null;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.state == State.FAILED) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkState(r0);
        int r02 = this.state.ordinal();
        if (r02 != 0) goto L9;
        return true;
    L9:
        if (r02 != 2) goto L11;
        return false;
    L11:
        return tryToComputeNext();
    L5:
        r0 = false;
        goto L6
    }

    @Override // java.util.Iterator
    public final T next() {
        if (hasNext() == false) goto L6;
        this.state = State.NOT_READY;
        T r0 = (T) NullnessCasts.uncheckedCastNullableTToT(this.next);
        this.next = null;
        return r0;
    L6:
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
