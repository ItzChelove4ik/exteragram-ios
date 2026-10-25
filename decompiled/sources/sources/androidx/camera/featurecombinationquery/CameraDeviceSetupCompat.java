package androidx.camera.featurecombinationquery;

import android.hardware.camera2.params.SessionConfiguration;

/* loaded from: classes3.dex */
public interface CameraDeviceSetupCompat {

    public static final class SupportQueryResult {
        private final int mSource;
        private final int mSupported;
        private final long mTimestampMillis;

        public SupportQueryResult(int r1, int r2, long r3) {
            this.mSupported = r1;
            this.mSource = r2;
            this.mTimestampMillis = r3;
        }

        public int getSupported() {
            return this.mSupported;
        }
    }

    SupportQueryResult isSessionConfigurationSupported(SessionConfiguration r1);
}
