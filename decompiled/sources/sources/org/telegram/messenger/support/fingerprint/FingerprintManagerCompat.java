package org.telegram.messenger.support.fingerprint;

import android.content.Context;

/* loaded from: classes3.dex */
public final class FingerprintManagerCompat {
    static final FingerprintManagerCompatImpl IMPL = null;
    private Context mContext;

    public static class Api23FingerprintManagerCompatImpl implements FingerprintManagerCompatImpl {
        public Api23FingerprintManagerCompatImpl() {
        }

        @Override // org.telegram.messenger.support.fingerprint.FingerprintManagerCompat.FingerprintManagerCompatImpl
        public boolean hasEnrolledFingerprints(Context r1) {
            return FingerprintManagerCompatApi23.hasEnrolledFingerprints(r1);
        }

        @Override // org.telegram.messenger.support.fingerprint.FingerprintManagerCompat.FingerprintManagerCompatImpl
        public boolean isHardwareDetected(Context r1) {
            return FingerprintManagerCompatApi23.isHardwareDetected(r1);
        }
    }

    public interface FingerprintManagerCompatImpl {
        boolean hasEnrolledFingerprints(Context r1);

        boolean isHardwareDetected(Context r1);
    }

    static {
        IMPL = new Api23FingerprintManagerCompatImpl();
    }

    private FingerprintManagerCompat(Context r1) {
        this.mContext = r1;
    }

    public static FingerprintManagerCompat from(Context r1) {
        return new FingerprintManagerCompat(r1);
    }

    public boolean hasEnrolledFingerprints() {
        return IMPL.hasEnrolledFingerprints(this.mContext);
    }

    public boolean isHardwareDetected() {
        return IMPL.isHardwareDetected(this.mContext);
    }
}
