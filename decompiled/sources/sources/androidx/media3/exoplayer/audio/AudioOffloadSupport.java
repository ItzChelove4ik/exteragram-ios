package androidx.media3.exoplayer.audio;

import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class AudioOffloadSupport {
    public static final AudioOffloadSupport DEFAULT_UNSUPPORTED = null;
    public final boolean isFormatSupported;
    public final boolean isGaplessSupported;
    public final boolean isSpeedChangeSupported;

    /* renamed from: androidx.media3.exoplayer.audio.AudioOffloadSupport$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder {
        private boolean isFormatSupported;
        private boolean isGaplessSupported;
        private boolean isSpeedChangeSupported;

        public Builder() {
        }

        public static /* synthetic */ boolean access$100(Builder r0) {
            return r0.isFormatSupported;
        }

        public static /* synthetic */ boolean access$200(Builder r0) {
            return r0.isGaplessSupported;
        }

        public static /* synthetic */ boolean access$300(Builder r0) {
            return r0.isSpeedChangeSupported;
        }

        public AudioOffloadSupport build() {
            if (this.isFormatSupported == true) goto L12;
            if (this.isGaplessSupported == false) goto L7;
        L9:
            Segment$$ExternalSyntheticBUOutline1.m("Secondary offload attribute fields are true but primary isFormatSupported is false");
            return null;
        L7:
            if (this.isSpeedChangeSupported == true) goto L9;
        L12:
            return new AudioOffloadSupport(this, null);
        }

        public Builder setIsFormatSupported(boolean r1) {
            this.isFormatSupported = r1;
            return this;
        }

        public Builder setIsGaplessSupported(boolean r1) {
            this.isGaplessSupported = r1;
            return this;
        }

        public Builder setIsSpeedChangeSupported(boolean r1) {
            this.isSpeedChangeSupported = r1;
            return this;
        }
    }

    static {
        DEFAULT_UNSUPPORTED = new Builder().build();
    }

    private AudioOffloadSupport(Builder r2) {
        this.isFormatSupported = Builder.access$100(r2);
        this.isGaplessSupported = Builder.access$200(r2);
        this.isSpeedChangeSupported = Builder.access$300(r2);
    }

    public /* synthetic */ AudioOffloadSupport(Builder r1, AnonymousClass1 r2) {
        this(r1);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L17:
        return false;
    L8:
        if (AudioOffloadSupport.class != r5.getClass()) goto L17;
        AudioOffloadSupport r52 = (AudioOffloadSupport) r5;
        if (this.isFormatSupported != r52.isFormatSupported) goto L17;
        if (this.isGaplessSupported != r52.isGaplessSupported) goto L17;
        if (this.isSpeedChangeSupported != r52.isSpeedChangeSupported) goto L17;
        return true;
    }

    public int hashCode() {
        return (((this.isFormatSupported ? 1 : 0) << 2) + ((this.isGaplessSupported ? 1 : 0) << 1)) + (this.isSpeedChangeSupported ? 1 : 0);
    }
}
