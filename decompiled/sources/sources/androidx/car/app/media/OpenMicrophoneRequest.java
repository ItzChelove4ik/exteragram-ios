package androidx.car.app.media;

import android.annotation.SuppressLint;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class OpenMicrophoneRequest {
    private final CarAudioCallbackDelegate mCarAudioCallbackDelegate;

    public static final class Builder {
        final CarAudioCallbackDelegate mCarAudioCallbackDelegate;

        @SuppressLint({"ExecutorRegistration"})
        public Builder(CarAudioCallback r1) {
            Objects.requireNonNull(r1);
            this.mCarAudioCallbackDelegate = CarAudioCallbackDelegate.create(r1);
        }

        public OpenMicrophoneRequest build() {
            return new OpenMicrophoneRequest(this);
        }
    }

    private OpenMicrophoneRequest() {
        this.mCarAudioCallbackDelegate = null;
    }

    public OpenMicrophoneRequest(Builder r1) {
        this.mCarAudioCallbackDelegate = r1.mCarAudioCallbackDelegate;
    }

    public CarAudioCallbackDelegate getCarAudioCallbackDelegate() {
        CarAudioCallbackDelegate r0 = this.mCarAudioCallbackDelegate;
        Objects.requireNonNull(r0);
        return r0;
    }
}
