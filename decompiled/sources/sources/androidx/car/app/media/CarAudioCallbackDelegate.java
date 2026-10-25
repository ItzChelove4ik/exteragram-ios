package androidx.car.app.media;

import android.annotation.SuppressLint;
import android.os.RemoteException;
import androidx.car.app.media.ICarAudioCallback;
import java.util.Objects;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public class CarAudioCallbackDelegate {
    private final ICarAudioCallback mCallback;

    public static class CarAudioCallbackStub extends ICarAudioCallback.Stub {
        private final CarAudioCallback mCarAudioCallback;

        public CarAudioCallbackStub() {
            this.mCarAudioCallback = null;
        }

        public CarAudioCallbackStub(CarAudioCallback r1) {
            this.mCarAudioCallback = r1;
        }

        @Override // androidx.car.app.media.ICarAudioCallback
        public void onStopRecording() {
            CarAudioCallback r0 = this.mCarAudioCallback;
            Objects.requireNonNull(r0);
            r0.onStopRecording();
        }
    }

    private CarAudioCallbackDelegate() {
        this.mCallback = null;
    }

    private CarAudioCallbackDelegate(CarAudioCallback r2) {
        this.mCallback = new CarAudioCallbackStub(r2);
    }

    @SuppressLint({"ExecutorRegistration"})
    public static CarAudioCallbackDelegate create(CarAudioCallback r1) {
        return new CarAudioCallbackDelegate(r1);
    }

    public void onStopRecording() {
        ICarAudioCallback r0 = this.mCallback;     // Catch: RemoteException -> L4
        Objects.requireNonNull(r0);     // Catch: RemoteException -> L4
        r0.onStopRecording();     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    }
}
