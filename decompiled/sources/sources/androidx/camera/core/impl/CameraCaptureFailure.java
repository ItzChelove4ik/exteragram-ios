package androidx.camera.core.impl;

import com.google.android.gms.cast.MediaError;

/* loaded from: classes3.dex */
public class CameraCaptureFailure {
    private final Reason mReason;

    public enum Reason extends Enum<Reason> {
        private static final /* synthetic */ Reason[] $VALUES = null;
        public static final Reason ERROR = null;

        private static /* synthetic */ Reason[] $values() {
            return new Reason[]{ERROR};
        }

        static {
            ERROR = new Reason(MediaError.ERROR_TYPE_ERROR, 0);
            $VALUES = $values();
        }

        Reason(String r1, int r2) {
        }

        public static Reason valueOf(String r1) {
            return (Reason) Enum.valueOf(Reason.class, r1);
        }

        public static Reason[] values() {
            return (Reason[]) $VALUES.clone();
        }
    }

    public CameraCaptureFailure(Reason r1) {
        this.mReason = r1;
    }
}
