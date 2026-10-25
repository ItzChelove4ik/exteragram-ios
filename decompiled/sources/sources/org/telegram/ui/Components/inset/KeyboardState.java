package org.telegram.ui.Components.inset;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;

/* loaded from: classes3.dex */
class KeyboardState {
    private final Runnable applyPendingStateR;
    private final long keyboardDuration;
    private final Utilities.Callback<State> onUpdateListener;
    private State state;

    public enum State extends Enum<State> {
        private static final /* synthetic */ State[] $VALUES = null;
        public static final State STATE_ANIMATING_TO_FULLY_HIDDEN = null;
        public static final State STATE_ANIMATING_TO_FULLY_VISIBLE = null;
        public static final State STATE_FULLY_HIDDEN = null;
        public static final State STATE_FULLY_VISIBLE = null;

        private static /* synthetic */ State[] $values() {
            return new State[]{STATE_FULLY_HIDDEN, STATE_ANIMATING_TO_FULLY_HIDDEN, STATE_ANIMATING_TO_FULLY_VISIBLE, STATE_FULLY_VISIBLE};
        }

        static {
            STATE_FULLY_HIDDEN = new State("STATE_FULLY_HIDDEN", 0);
            STATE_ANIMATING_TO_FULLY_HIDDEN = new State("STATE_ANIMATING_TO_FULLY_HIDDEN", 1);
            STATE_ANIMATING_TO_FULLY_VISIBLE = new State("STATE_ANIMATING_TO_FULLY_VISIBLE", 2);
            STATE_FULLY_VISIBLE = new State("STATE_FULLY_VISIBLE", 3);
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

    public static /* synthetic */ void $r8$lambda$ACN9KHn6rt427LiSraGF9D_7bYs(KeyboardState r0) {
        r0.applyPendingState();
    }

    public KeyboardState(Utilities.Callback<State> r3) {
        this.state = State.STATE_FULLY_HIDDEN;
        this.applyPendingStateR = new KeyboardState$$ExternalSyntheticLambda0(this);
        this.onUpdateListener = r3;
        this.keyboardDuration = (long) ((AndroidUtilities.getAnimatorDurationScale() * 250.0f) * 1.1f);
    }

    private void applyPendingState() {
        State r0 = this.state;
        if (r0 != State.STATE_ANIMATING_TO_FULLY_HIDDEN) goto L7;
        setState(State.STATE_FULLY_HIDDEN, true);
        return;
    L7:
        if (r0 != State.STATE_ANIMATING_TO_FULLY_VISIBLE) goto L10;
        setState(State.STATE_FULLY_VISIBLE, true);
        return;
    }

    private void setState(State r3, boolean r4) {
        if (this.state == r3) goto L13;
        AndroidUtilities.cancelRunOnUIThread(this.applyPendingStateR);
        this.state = r3;
        if (r4 == false) goto L8;
        this.onUpdateListener.run(r3);
    L8:
        if (r3 != State.STATE_ANIMATING_TO_FULLY_HIDDEN) goto L10;
    L11:
        AndroidUtilities.runOnUIThread(this.applyPendingStateR, this.keyboardDuration);
        return;
    L10:
        if (r3 == State.STATE_ANIMATING_TO_FULLY_VISIBLE) goto L11;
        return;
    }

    public State getState() {
        return this.state;
    }

    public State setKeyboardVisibility(boolean r1, boolean r2, boolean r3) {
        if (r2 == false) goto L6;
        if (r1 == false) goto L5;
        State r12 = State.STATE_FULLY_VISIBLE;
    L10:
        if (this.state == r12) goto L12;
        setState(r12, r3);
    L12:
        return r12;
    L5:
        r12 = State.STATE_FULLY_HIDDEN;
        goto L10
    L6:
        if (r1 == false) goto L8;
        r12 = State.STATE_ANIMATING_TO_FULLY_VISIBLE;
        goto L10
    L8:
        r12 = State.STATE_ANIMATING_TO_FULLY_HIDDEN;
        goto L10
    }
}
