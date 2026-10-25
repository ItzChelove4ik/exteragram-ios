package org.webrtc;

import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes6.dex */
public class MediaSource {
    private long nativeSource;
    private final RefCountDelegate refCountDelegate;

    public enum State extends Enum<State> {
        private static final /* synthetic */ State[] $VALUES = null;
        public static final State ENDED = null;
        public static final State INITIALIZING = null;
        public static final State LIVE = null;
        public static final State MUTED = null;

        private static /* synthetic */ State[] $values() {
            return new State[]{INITIALIZING, LIVE, ENDED, MUTED};
        }

        static {
            INITIALIZING = new State("INITIALIZING", 0);
            LIVE = new State("LIVE", 1);
            ENDED = new State("ENDED", 2);
            MUTED = new State("MUTED", 3);
            $VALUES = $values();
        }

        State(String r1, int r2) {
        }

        @CalledByNative("State")
        public static State fromNativeIndex(int r1) {
            return values()[r1];
        }

        public static State valueOf(String r1) {
            return (State) Enum.valueOf(State.class, r1);
        }

        public static State[] values() {
            return (State[]) $VALUES.clone();
        }
    }

    /* renamed from: $r8$lambda$VoDVU367OlBFP-7vCYXNA_qIrxk, reason: not valid java name */
    public static /* synthetic */ void m22357$r8$lambda$VoDVU367OlBFP7vCYXNA_qIrxk(long r0) {
        JniCommon.nativeReleaseRef(r0);
    }

    public MediaSource(long r3) {
        this.refCountDelegate = new RefCountDelegate(new MediaSource$$ExternalSyntheticLambda0(r3));
        this.nativeSource = r3;
    }

    private void checkMediaSourceExists() {
        if (this.nativeSource == 0) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("MediaSource has been disposed.");
    }

    private static native State nativeGetState(long r0);

    public void dispose() {
        checkMediaSourceExists();
        this.refCountDelegate.release();
        this.nativeSource = 0;
    }

    public long getNativeMediaSource() {
        checkMediaSourceExists();
        return this.nativeSource;
    }

    public void runWithReference(Runnable r2) {
        if (this.refCountDelegate.safeRetain() == true) goto L11;
        return;
    L11:
        r2.run();     // Catch: Throwable -> L7
        this.refCountDelegate.release();
        return;
    L7:
        th = move-exception;
        this.refCountDelegate.release();
        throw th;
    }

    public State state() {
        checkMediaSourceExists();
        return nativeGetState(this.nativeSource);
    }
}
