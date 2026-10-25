package androidx.media;

import android.media.AudioAttributes;

/* loaded from: classes3.dex */
public class AudioAttributesImplApi21 implements AudioAttributesImpl {
    public AudioAttributes mAudioAttributes;
    public int mLegacyStreamType;

    public AudioAttributesImplApi21() {
        this.mLegacyStreamType = -1;
    }

    public AudioAttributesImplApi21(AudioAttributes r2) {
        this(r2, -1);
    }

    public AudioAttributesImplApi21(AudioAttributes r1, int r2) {
        this.mAudioAttributes = r1;
        this.mLegacyStreamType = r2;
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof AudioAttributesImplApi21) == true) goto L7;
        return false;
    L7:
        return this.mAudioAttributes.equals(((AudioAttributesImplApi21) r2).mAudioAttributes);
    }

    public int hashCode() {
        return this.mAudioAttributes.hashCode();
    }

    public String toString() {
        return "AudioAttributesCompat: audioattributes=" + this.mAudioAttributes;
    }
}
