package org.telegram.messenger.video.audio_input;

import org.telegram.messenger.video.AudioConversions;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes6.dex */
public class BlankAudioInput extends AudioInput {
    public final long durationUs;
    private int remainingShorts;
    private int requiredShortsForDuration;

    public BlankAudioInput(long r1) {
        this.durationUs = r1;
    }

    @Override // org.telegram.messenger.video.audio_input.AudioInput
    public short getNext() {
        if (hasRemaining() == false) goto L11;
        this.remainingShorts--;
        if (isLoopingEnabled() == true) goto L7;
        return 0;
    L7:
        if (this.remainingShorts != 0) goto L14;
        this.remainingShorts = this.requiredShortsForDuration;
        return 0;
    L14:
        return 0;
    L11:
        GlShader$$ExternalSyntheticBUOutline1.m("Audio input has no remaining value.");
        return 0;
    }

    @Override // org.telegram.messenger.video.audio_input.AudioInput
    public int getSampleRate() {
        return -1;
    }

    @Override // org.telegram.messenger.video.audio_input.AudioInput
    public boolean hasRemaining() {
        if (this.remainingShorts <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.telegram.messenger.video.audio_input.AudioInput
    public void release() {
        this.remainingShorts = 0;
    }

    @Override // org.telegram.messenger.video.audio_input.AudioInput
    public void start(int r3, int r4) {
        int r32 = AudioConversions.usToShorts(this.durationUs, r3, r4);
        this.requiredShortsForDuration = r32;
        this.remainingShorts = r32;
    }
}
