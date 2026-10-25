package org.webrtc;

import android.graphics.ImageFormat;
import com.android.dx.io.Opcodes;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes6.dex */
public class CameraEnumerationAndroid {
    static final ArrayList<Size> COMMON_RESOLUTIONS = null;
    private static final String TAG = "CameraEnumerationAndroid";

    /* renamed from: org.webrtc.CameraEnumerationAndroid$1, reason: invalid class name */
    public class AnonymousClass1 extends ClosestComparator<CaptureFormat.FramerateRange> {
        private static final int MAX_FPS_DIFF_THRESHOLD = 5000;
        private static final int MAX_FPS_HIGH_DIFF_WEIGHT = 3;
        private static final int MAX_FPS_LOW_DIFF_WEIGHT = 1;
        private static final int MIN_FPS_HIGH_VALUE_WEIGHT = 4;
        private static final int MIN_FPS_LOW_VALUE_WEIGHT = 1;
        private static final int MIN_FPS_THRESHOLD = 8000;
        final /* synthetic */ int val$requestedFps;

        public AnonymousClass1(int r1) {
            this.val$requestedFps = r1;
            super(null);
        }

        private int progressivePenalty(int r1, int r2, int r3, int r4) {
            if (r1 >= r2) goto L6;
            return r1 * r3;
        L6:
            return (r3 * r2) + ((r1 - r2) * r4);
        }

        @Override // org.webrtc.CameraEnumerationAndroid.ClosestComparator
        public /* bridge */ /* synthetic */ int diff(CaptureFormat.FramerateRange r1) {
            return diff2(r1);
        }

        /* renamed from: diff, reason: avoid collision after fix types in other method */
        public int diff2(CaptureFormat.FramerateRange r5) {
            return progressivePenalty(r5.min, MIN_FPS_THRESHOLD, 1, 4) + progressivePenalty(Math.abs((this.val$requestedFps * 1000) - r5.max), MAX_FPS_DIFF_THRESHOLD, 1, 3);
        }
    }

    /* renamed from: org.webrtc.CameraEnumerationAndroid$2, reason: invalid class name */
    public class AnonymousClass2 extends ClosestComparator<Size> {
        final /* synthetic */ int val$requestedHeight;
        final /* synthetic */ int val$requestedWidth;

        public AnonymousClass2(int r1, int r2) {
            this.val$requestedWidth = r1;
            this.val$requestedHeight = r2;
            super(null);
        }

        @Override // org.webrtc.CameraEnumerationAndroid.ClosestComparator
        public /* bridge */ /* synthetic */ int diff(Size r1) {
            return diff2(r1);
        }

        /* renamed from: diff, reason: avoid collision after fix types in other method */
        public int diff2(Size r3) {
            return Math.abs(this.val$requestedWidth - r3.width) + Math.abs(this.val$requestedHeight - r3.height);
        }
    }

    public static class CaptureFormat {
        public final FramerateRange framerate;
        public final int height;
        public final int imageFormat;
        public final int width;

        public static class FramerateRange {
            public int max;
            public int min;

            public FramerateRange(int r1, int r2) {
                this.min = r1;
                this.max = r2;
            }

            public boolean equals(Object r4) {
                if ((r4 instanceof FramerateRange) == true) goto L5;
                return false;
            L5:
                FramerateRange r42 = (FramerateRange) r4;
                if (this.min == r42.min) goto L8;
            L11:
                return false;
            L8:
                if (this.max != r42.max) goto L11;
                return true;
            }

            public int hashCode() {
                return ((this.min * 65537) + 1) + this.max;
            }

            public String toString() {
                return "[" + (this.min / 1000.0f) + ":" + (this.max / 1000.0f) + "]";
            }
        }

        public CaptureFormat(int r2, int r3, int r4, int r5) {
            this.imageFormat = 17;
            this.width = r2;
            this.height = r3;
            this.framerate = new FramerateRange(r4, r5);
        }

        public CaptureFormat(int r2, int r3, FramerateRange r4) {
            this.imageFormat = 17;
            this.width = r2;
            this.height = r3;
            this.framerate = r4;
        }

        public static int frameSize(int r1, int r2, int r3) {
            if (r3 == 17) goto L5;
            ByteString$$ExternalSyntheticBUOutline0.m("Don't know how to calculate the frame size of non-NV21 image formats.");
            return 0;
        L5:
            return ((r1 * r2) * ImageFormat.getBitsPerPixel(r3)) / 8;
        }

        public boolean equals(Object r4) {
            if ((r4 instanceof CaptureFormat) == true) goto L5;
            return false;
        L5:
            CaptureFormat r42 = (CaptureFormat) r4;
            if (this.width == r42.width) goto L8;
        L13:
            return false;
        L8:
            if (this.height != r42.height) goto L13;
            if (this.framerate.equals(r42.framerate) == false) goto L13;
            return true;
        }

        public int frameSize() {
            return frameSize(this.width, this.height, 17);
        }

        public int hashCode() {
            return ((((this.width * 65497) + this.height) * Opcodes.INVOKE_POLYMORPHIC_RANGE) + 1) + this.framerate.hashCode();
        }

        public String toString() {
            return this.width + "x" + this.height + "@" + this.framerate;
        }
    }

    public static abstract class ClosestComparator<T> implements Comparator<T> {
        private ClosestComparator() {
        }

        public /* synthetic */ ClosestComparator(CameraEnumerationAndroidIA r1) {
            this();
        }

        @Override // java.util.Comparator
        public int compare(T r1, T r2) {
            return diff(r1) - diff(r2);
        }

        public abstract int diff(T r1);
    }

    static {
        COMMON_RESOLUTIONS = new ArrayList(Arrays.asList(new Size[]{new Size(160, 120), new Size(240, 160), new Size(320, 240), new Size(MediaError.DetailedErrorCode.MANIFEST_UNKNOWN, 240), new Size(480, 320), new Size(640, 360), new Size(640, 480), new Size(768, 480), new Size(854, 480), new Size(800, MediaError.DetailedErrorCode.TEXT_UNKNOWN), new Size(960, 540), new Size(960, 640), new Size(1024, 576), new Size(1024, MediaError.DetailedErrorCode.TEXT_UNKNOWN), new Size(1280, 720), new Size(1280, 1024), new Size(1920, 1080), new Size(1920, 1440), new Size(2560, 1440), new Size(3840, 2160)}));
    }

    public CameraEnumerationAndroid() {
    }

    public static CaptureFormat.FramerateRange getClosestSupportedFramerateRange(List<CaptureFormat.FramerateRange> r1, int r2) {
        return (CaptureFormat.FramerateRange) Collections.min(r1, new AnonymousClass1(r2));
    }

    public static Size getClosestSupportedSize(List<Size> r1, int r2, int r3) {
        return (Size) Collections.min(r1, new AnonymousClass2(r2, r3));
    }

    public static void reportCameraResolution(Histogram r1, Size r2) {
        r1.addSample(COMMON_RESOLUTIONS.indexOf(r2) + 1);
    }
}
