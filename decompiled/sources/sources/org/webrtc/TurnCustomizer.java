package org.webrtc;

import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes6.dex */
public class TurnCustomizer {
    private long nativeTurnCustomizer;

    public TurnCustomizer(long r1) {
        this.nativeTurnCustomizer = r1;
    }

    private void checkTurnCustomizerExists() {
        if (this.nativeTurnCustomizer == 0) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("TurnCustomizer has been disposed.");
    }

    private static native void nativeFreeTurnCustomizer(long r0);

    public void dispose() {
        checkTurnCustomizerExists();
        nativeFreeTurnCustomizer(this.nativeTurnCustomizer);
        this.nativeTurnCustomizer = 0;
    }

    @CalledByNative
    public long getNativeTurnCustomizer() {
        checkTurnCustomizerExists();
        return this.nativeTurnCustomizer;
    }
}
