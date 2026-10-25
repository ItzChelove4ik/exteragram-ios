package org.telegram.messenger.video.audio_input;

/* loaded from: classes6.dex */
public abstract class AudioInput {
    private boolean loopingEnabled;
    private float volume;

    public AudioInput() {
        this.volume = 1.0f;
    }

    public abstract short getNext();

    public abstract int getSampleRate();

    public float getVolume() {
        return this.volume;
    }

    public abstract boolean hasRemaining();

    public boolean isLoopingEnabled() {
        return this.loopingEnabled;
    }

    public abstract void release();

    public void setVolume(float r2) {
        this.volume = Math.max(0.0f, Math.min(r2, 1.0f));
    }

    public abstract void start(int r1, int r2);
}
