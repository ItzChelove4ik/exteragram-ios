package androidx.camera.core.impl;

import android.content.Context;

/* loaded from: classes3.dex */
public interface UseCaseConfigFactory {
    public static final UseCaseConfigFactory EMPTY_INSTANCE = null;

    /* renamed from: androidx.camera.core.impl.UseCaseConfigFactory$1, reason: invalid class name */
    public class AnonymousClass1 implements UseCaseConfigFactory {
        public AnonymousClass1() {
        }

        @Override // androidx.camera.core.impl.UseCaseConfigFactory
        public Config getConfig(CaptureType r1, int r2) {
            return null;
        }
    }

    public enum CaptureType extends Enum<CaptureType> {
        private static final /* synthetic */ CaptureType[] $VALUES = null;
        public static final CaptureType IMAGE_ANALYSIS = null;
        public static final CaptureType IMAGE_CAPTURE = null;
        public static final CaptureType METERING_REPEATING = null;
        public static final CaptureType PREVIEW = null;
        public static final CaptureType STREAM_SHARING = null;
        public static final CaptureType VIDEO_CAPTURE = null;

        private static /* synthetic */ CaptureType[] $values() {
            return new CaptureType[]{IMAGE_CAPTURE, PREVIEW, IMAGE_ANALYSIS, VIDEO_CAPTURE, STREAM_SHARING, METERING_REPEATING};
        }

        static {
            IMAGE_CAPTURE = new CaptureType("IMAGE_CAPTURE", 0);
            PREVIEW = new CaptureType("PREVIEW", 1);
            IMAGE_ANALYSIS = new CaptureType("IMAGE_ANALYSIS", 2);
            VIDEO_CAPTURE = new CaptureType("VIDEO_CAPTURE", 3);
            STREAM_SHARING = new CaptureType("STREAM_SHARING", 4);
            METERING_REPEATING = new CaptureType("METERING_REPEATING", 5);
            $VALUES = $values();
        }

        CaptureType(String r1, int r2) {
        }

        public static CaptureType valueOf(String r1) {
            return (CaptureType) Enum.valueOf(CaptureType.class, r1);
        }

        public static CaptureType[] values() {
            return (CaptureType[]) $VALUES.clone();
        }
    }

    public interface Provider {
        UseCaseConfigFactory newInstance(Context r1);
    }

    static {
        EMPTY_INSTANCE = new AnonymousClass1();
    }

    Config getConfig(CaptureType r1, int r2);
}
