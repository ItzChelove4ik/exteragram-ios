package androidx.media3.common;

import android.annotation.SuppressLint;
import android.media.AudioAttributes;
import android.os.Build;
import android.os.Bundle;
import androidx.media3.common.util.Util;

/* loaded from: classes3.dex */
public final class AudioAttributes {
    public static final AudioAttributes DEFAULT = null;
    private static final String FIELD_ALLOWED_CAPTURE_POLICY = null;
    private static final String FIELD_CONTENT_TYPE = null;
    private static final String FIELD_FLAGS = null;
    private static final String FIELD_IS_CONTENT_SPATIALIZED = null;
    private static final String FIELD_SPATIALIZATION_BEHAVIOR = null;
    private static final String FIELD_USAGE = null;
    public final int allowedCapturePolicy;
    private AudioAttributesV21 audioAttributesV21;
    public final int contentType;
    public final int flags;
    public final boolean isContentSpatialized;
    public final int spatializationBehavior;
    public final int usage;

    /* renamed from: androidx.media3.common.AudioAttributes$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Api29 {
        @SuppressLint({"WrongConstant"})
        public static void setAllowedCapturePolicy(AudioAttributes.Builder r0, int r1) {
            r0.setAllowedCapturePolicy(r1);
        }
    }

    public static final class Api32 {
        public static void setIsContentSpatialized(AudioAttributes.Builder r0, boolean r1) {
            r0.setIsContentSpatialized(r1);
        }

        @SuppressLint({"WrongConstant"})
        public static void setSpatializationBehavior(AudioAttributes.Builder r0, int r1) {
            r0.setSpatializationBehavior(r1);
        }
    }

    public static final class AudioAttributesV21 {
        public final android.media.AudioAttributes audioAttributes;

        private AudioAttributesV21(AudioAttributes r4) {
            AudioAttributes.Builder r0 = new AudioAttributes.Builder().setContentType(r4.contentType).setFlags(r4.flags).setUsage(r4.usage);
            int r1 = Build.VERSION.SDK_INT;
            if (r1 < 29) goto L6;
            Api29.setAllowedCapturePolicy(r0, r4.allowedCapturePolicy);
        L6:
            if (r1 < 32) goto L8;
            Api32.setSpatializationBehavior(r0, r4.spatializationBehavior);
            Api32.setIsContentSpatialized(r0, r4.isContentSpatialized);
        L8:
            this.audioAttributes = r0.build();
        }

        public /* synthetic */ AudioAttributesV21(AudioAttributes r1, AnonymousClass1 r2) {
            this(r1);
        }
    }

    public static final class Builder {
        private int allowedCapturePolicy;
        private int contentType;
        private int flags;
        private boolean isContentSpatialized;
        private int spatializationBehavior;
        private int usage;

        public Builder() {
            this.contentType = 0;
            this.flags = 0;
            this.usage = 1;
            this.allowedCapturePolicy = 1;
            this.spatializationBehavior = 0;
            this.isContentSpatialized = false;
        }

        public AudioAttributes build() {
            return new AudioAttributes(this.contentType, this.flags, this.usage, this.allowedCapturePolicy, this.spatializationBehavior, this.isContentSpatialized, null);
        }

        public Builder setAllowedCapturePolicy(int r1) {
            this.allowedCapturePolicy = r1;
            return this;
        }

        public Builder setContentType(int r1) {
            this.contentType = r1;
            return this;
        }

        public Builder setFlags(int r1) {
            this.flags = r1;
            return this;
        }

        public Builder setIsContentSpatialized(boolean r1) {
            this.isContentSpatialized = r1;
            return this;
        }

        public Builder setSpatializationBehavior(int r1) {
            this.spatializationBehavior = r1;
            return this;
        }

        public Builder setUsage(int r1) {
            this.usage = r1;
            return this;
        }
    }

    static {
        DEFAULT = new Builder().build();
        FIELD_CONTENT_TYPE = Util.intToStringMaxRadix(0);
        FIELD_FLAGS = Util.intToStringMaxRadix(1);
        FIELD_USAGE = Util.intToStringMaxRadix(2);
        FIELD_ALLOWED_CAPTURE_POLICY = Util.intToStringMaxRadix(3);
        FIELD_SPATIALIZATION_BEHAVIOR = Util.intToStringMaxRadix(4);
        FIELD_IS_CONTENT_SPATIALIZED = Util.intToStringMaxRadix(5);
    }

    private AudioAttributes(int r1, int r2, int r3, int r4, int r5, boolean r6) {
        this.contentType = r1;
        this.flags = r2;
        this.usage = r3;
        this.allowedCapturePolicy = r4;
        this.spatializationBehavior = r5;
        this.isContentSpatialized = r6;
    }

    public /* synthetic */ AudioAttributes(int r1, int r2, int r3, int r4, int r5, boolean r6, AnonymousClass1 r7) {
        this(r1, r2, r3, r4, r5, r6);
    }

    public static AudioAttributes fromBundle(Bundle r3) {
        Builder r0 = new Builder();
        String r1 = FIELD_CONTENT_TYPE;
        if (r3.containsKey(r1) == false) goto L5;
        r0.setContentType(r3.getInt(r1));
    L5:
        String r12 = FIELD_FLAGS;
        if (r3.containsKey(r12) == false) goto L8;
        r0.setFlags(r3.getInt(r12));
    L8:
        String r13 = FIELD_USAGE;
        if (r3.containsKey(r13) == false) goto L11;
        r0.setUsage(r3.getInt(r13));
    L11:
        String r14 = FIELD_ALLOWED_CAPTURE_POLICY;
        if (r3.containsKey(r14) == false) goto L14;
        r0.setAllowedCapturePolicy(r3.getInt(r14));
    L14:
        String r15 = FIELD_SPATIALIZATION_BEHAVIOR;
        if (r3.containsKey(r15) == false) goto L17;
        r0.setSpatializationBehavior(r3.getInt(r15));
    L17:
        String r16 = FIELD_IS_CONTENT_SPATIALIZED;
        if (r3.containsKey(r16) == false) goto L21;
        r0.setIsContentSpatialized(r3.getBoolean(r16));
    L21:
        return r0.build();
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L23:
        return false;
    L8:
        if (AudioAttributes.class != r5.getClass()) goto L23;
        AudioAttributes r52 = (AudioAttributes) r5;
        if (this.contentType != r52.contentType) goto L23;
        if (this.flags != r52.flags) goto L23;
        if (this.usage != r52.usage) goto L23;
        if (this.allowedCapturePolicy != r52.allowedCapturePolicy) goto L23;
        if (this.spatializationBehavior != r52.spatializationBehavior) goto L23;
        if (this.isContentSpatialized != r52.isContentSpatialized) goto L23;
        return true;
    }

    public AudioAttributesV21 getAudioAttributesV21() {
        if (this.audioAttributesV21 != null) goto L6;
        this.audioAttributesV21 = new AudioAttributesV21(this, null);
    L6:
        return this.audioAttributesV21;
    }

    public int getStreamType() {
        if ((this.flags & 1) != 1) goto L6;
        return 1;
    L6:
        switch(this.usage) {
            case 2: goto L20;
            case 3: goto L18;
            case 4: goto L16;
            case 5: goto L14;
            case 6: goto L12;
            case 7: goto L14;
            case 8: goto L14;
            case 9: goto L14;
            case 10: goto L14;
            case 11: goto L10;
            case 12: goto L7;
            case 13: goto L9;
            default: goto L7;
        };
    L7:
        return 3;
    L9:
        return 1;
    L10:
        return 10;
    L12:
        return 2;
    L14:
        return 5;
    L16:
        return 4;
    L18:
        return 8;
    L20:
        return 0;
    }

    public int hashCode() {
        return ((((((((((527 + this.contentType) * 31) + this.flags) * 31) + this.usage) * 31) + this.allowedCapturePolicy) * 31) + this.spatializationBehavior) * 31) + (this.isContentSpatialized ? 1 : 0);
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        r0.putInt(FIELD_CONTENT_TYPE, this.contentType);
        r0.putInt(FIELD_FLAGS, this.flags);
        r0.putInt(FIELD_USAGE, this.usage);
        r0.putInt(FIELD_ALLOWED_CAPTURE_POLICY, this.allowedCapturePolicy);
        r0.putInt(FIELD_SPATIALIZATION_BEHAVIOR, this.spatializationBehavior);
        r0.putBoolean(FIELD_IS_CONTENT_SPATIALIZED, this.isContentSpatialized);
        return r0;
    }
}
