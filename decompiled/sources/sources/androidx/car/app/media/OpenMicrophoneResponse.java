package androidx.car.app.media;

import android.annotation.SuppressLint;
import android.os.ParcelFileDescriptor;
import com.google.protobuf.UnsafeUtil$$ExternalSyntheticBUOutline0;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class OpenMicrophoneResponse {
    private final CarAudioCallbackDelegate mCarAudioCallbackDelegate;
    private final ParcelFileDescriptor mCarMicrophoneDescriptor;

    public static final class Builder {
        final CarAudioCallbackDelegate mCarAudioCallbackDelegate;
        ParcelFileDescriptor mCarMicrophoneDescriptor;

        @SuppressLint({"ExecutorRegistration"})
        public Builder(CarAudioCallback r1) {
            Objects.requireNonNull(r1);
            this.mCarAudioCallbackDelegate = CarAudioCallbackDelegate.create(r1);
        }

        public OpenMicrophoneResponse build() {
            return new OpenMicrophoneResponse(this);
        }
    }

    private OpenMicrophoneResponse() {
        this.mCarMicrophoneDescriptor = null;
        this.mCarAudioCallbackDelegate = null;
    }

    public OpenMicrophoneResponse(Builder r2) {
        this.mCarAudioCallbackDelegate = r2.mCarAudioCallbackDelegate;
        this.mCarMicrophoneDescriptor = r2.mCarMicrophoneDescriptor;
    }

    public CarAudioCallbackDelegate getCarAudioCallback() {
        CarAudioCallbackDelegate r0 = this.mCarAudioCallbackDelegate;
        Objects.requireNonNull(r0);
        return r0;
    }

    public InputStream getCarMicrophoneInputStream() {
        ParcelFileDescriptor r1 = this.mCarMicrophoneDescriptor;
        if (r1 != null) goto L10;
        ParcelFileDescriptor[] r12 = ParcelFileDescriptor.createReliablePipe();     // Catch: IOException -> L6
        r12[1].close();     // Catch: IOException -> L6
        r1 = r12[0];     // Catch: IOException -> L6
    L6:
        e = move-exception;
        UnsafeUtil$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L10:
        return new ParcelFileDescriptor.AutoCloseInputStream(r1);
    }
}
