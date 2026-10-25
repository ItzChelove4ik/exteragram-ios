package androidx.media3.common.audio;

import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Build;
import android.os.Handler;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.util.Objects;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public final class AudioFocusRequestCompat {
    private final AudioAttributes audioAttributes;
    private final Handler focusChangeHandler;
    private final int focusGain;
    private final Object frameworkAudioFocusRequest;
    private final AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener;
    private final boolean pauseOnDuck;

    /* renamed from: androidx.media3.common.audio.AudioFocusRequestCompat$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder {
        private AudioAttributes audioAttributes;
        private Handler focusChangeHandler;
        private int focusGain;
        private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener;
        private boolean pauseOnDuck;

        public Builder(int r2) {
            this.audioAttributes = AudioAttributes.DEFAULT;
            this.focusGain = r2;
        }

        private Builder(AudioFocusRequestCompat r2) {
            this.focusGain = r2.getFocusGain();
            this.onAudioFocusChangeListener = r2.getOnAudioFocusChangeListener();
            this.focusChangeHandler = r2.getFocusChangeHandler();
            this.audioAttributes = r2.getAudioAttributes();
            this.pauseOnDuck = r2.willPauseWhenDucked();
        }

        public /* synthetic */ Builder(AudioFocusRequestCompat r1, AnonymousClass1 r2) {
            this(r1);
        }

        public AudioFocusRequestCompat build() {
            AudioManager.OnAudioFocusChangeListener r2 = this.onAudioFocusChangeListener;
            if (r2 != null) goto L5;
            Segment$$ExternalSyntheticBUOutline1.m("Can't build an AudioFocusRequestCompat instance without a listener");
            return null;
        L5:
            return new AudioFocusRequestCompat(this.focusGain, r2, (Handler) Assertions.checkNotNull(this.focusChangeHandler), this.audioAttributes, this.pauseOnDuck);
        }

        public Builder setAudioAttributes(AudioAttributes r1) {
            Assertions.checkNotNull(r1);
            this.audioAttributes = r1;
            return this;
        }

        public Builder setOnAudioFocusChangeListener(AudioManager.OnAudioFocusChangeListener r1, Handler r2) {
            Assertions.checkNotNull(r1);
            Assertions.checkNotNull(r2);
            this.onAudioFocusChangeListener = r1;
            this.focusChangeHandler = r2;
            return this;
        }

        public Builder setWillPauseWhenDucked(boolean r1) {
            this.pauseOnDuck = r1;
            return this;
        }
    }

    public static class OnAudioFocusChangeListenerHandlerCompat implements AudioManager.OnAudioFocusChangeListener {
        private final Handler handler;
        private final AudioManager.OnAudioFocusChangeListener listener;

        /* renamed from: $r8$lambda$YUeAc2aZawVKNcshnp4-D5wsHzQ, reason: not valid java name */
        public static /* synthetic */ void m753$r8$lambda$YUeAc2aZawVKNcshnp4D5wsHzQ(OnAudioFocusChangeListenerHandlerCompat r0, int r1) {
            r0.listener.onAudioFocusChange(r1);
        }

        public OnAudioFocusChangeListenerHandlerCompat(AudioManager.OnAudioFocusChangeListener r1, Handler r2) {
            this.listener = r1;
            this.handler = Util.createHandler(r2.getLooper(), null);
        }

        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int r3) {
            Util.postOrRun(this.handler, new AudioFocusRequestCompat$OnAudioFocusChangeListenerHandlerCompat$$ExternalSyntheticLambda0(this, r3));
        }
    }

    public AudioFocusRequestCompat(int r4, AudioManager.OnAudioFocusChangeListener r5, Handler r6, AudioAttributes r7, boolean r8) {
        this.focusGain = r4;
        this.focusChangeHandler = r6;
        this.audioAttributes = r7;
        this.pauseOnDuck = r8;
        int r0 = Build.VERSION.SDK_INT;
        if (r0 >= 26) goto L5;
        this.onAudioFocusChangeListener = new OnAudioFocusChangeListenerHandlerCompat(r5, r6);
    L6:
        if (r0 < 26) goto L9;
        this.frameworkAudioFocusRequest = AudioFocusRequestCompat$$ExternalSyntheticApiModelOutline0.m(r4).setAudioAttributes(r7.getAudioAttributesV21().audioAttributes).setWillPauseWhenDucked(r8).setOnAudioFocusChangeListener(r5, r6).build();
        return;
    L9:
        this.frameworkAudioFocusRequest = null;
        return;
    L5:
        this.onAudioFocusChangeListener = r5;
        goto L6
    }

    public Builder buildUpon() {
        return new Builder(this, null);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof AudioFocusRequestCompat) == true) goto L8;
        return false;
    L8:
        AudioFocusRequestCompat r52 = (AudioFocusRequestCompat) r5;
        if (this.focusGain == r52.focusGain) goto L11;
    L19:
        return false;
    L11:
        if (this.pauseOnDuck != r52.pauseOnDuck) goto L19;
        if (Objects.equals(this.onAudioFocusChangeListener, r52.onAudioFocusChangeListener) == false) goto L19;
        if (Objects.equals(this.focusChangeHandler, r52.focusChangeHandler) == false) goto L19;
        if (Objects.equals(this.audioAttributes, r52.audioAttributes) == false) goto L19;
        return true;
    }

    public AudioAttributes getAudioAttributes() {
        return this.audioAttributes;
    }

    public AudioFocusRequest getAudioFocusRequest() {
        return AudioFocusRequestCompat$$ExternalSyntheticApiModelOutline1.m(Assertions.checkNotNull(this.frameworkAudioFocusRequest));
    }

    public Handler getFocusChangeHandler() {
        return this.focusChangeHandler;
    }

    public int getFocusGain() {
        return this.focusGain;
    }

    public AudioManager.OnAudioFocusChangeListener getOnAudioFocusChangeListener() {
        return this.onAudioFocusChangeListener;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.focusGain), this.onAudioFocusChangeListener, this.focusChangeHandler, this.audioAttributes, Boolean.valueOf(this.pauseOnDuck)});
    }

    public boolean willPauseWhenDucked() {
        return this.pauseOnDuck;
    }
}
