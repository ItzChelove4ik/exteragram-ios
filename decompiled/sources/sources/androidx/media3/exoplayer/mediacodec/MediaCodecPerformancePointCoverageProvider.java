package androidx.media3.exoplayer.mediacodec;

import android.media.MediaCodecInfo;
import android.os.Build;
import androidx.media3.common.Format;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import java.util.List;
import org.telegram.messenger.MediaController;

/* loaded from: classes4.dex */
abstract class MediaCodecPerformancePointCoverageProvider {
    private static Boolean shouldIgnorePerformancePoints;

    public static final class Api29 {
        public static int areResolutionAndFrameRateCovered(MediaCodecInfo.VideoCapabilities r2, int r3, int r4, double r5) {
            List<MediaCodecInfo.VideoCapabilities.PerformancePoint> r22 = r2.getSupportedPerformancePoints();
            if (r22 != null) goto L5;
        L15:
            return 0;
        L5:
            if (r22.isEmpty() == true) goto L15;
            MediaCodecPerformancePointCoverageProvider$Api29$$ExternalSyntheticApiModelOutline1.m();
            int r23 = evaluatePerformancePointCoverage(r22, MediaCodecPerformancePointCoverageProvider$Api29$$ExternalSyntheticApiModelOutline0.m(r3, r4, (int) r5));
            if (r23 == 1) goto L10;
        L14:
            return r23;
        L10:
            if (MediaCodecPerformancePointCoverageProvider.access$000() != null) goto L14;
            MediaCodecPerformancePointCoverageProvider.access$002(Boolean.valueOf(shouldIgnorePerformancePoints()));
            if (MediaCodecPerformancePointCoverageProvider.access$000().booleanValue() == false) goto L14;
            return 0;
        }

        private static int evaluateH264RequiredSupport(boolean r4) {
            Format r1 = new Format.Builder().setSampleMimeType(MediaController.VIDEO_MIME_TYPE).build();     // Catch: MediaCodecUtil.DecoderQueryException -> L20
            if (r1.sampleMimeType == null) goto L19;
            List<MediaCodecInfo> r42 = MediaCodecUtil.getDecoderInfosSoftMatch(MediaCodecSelector.DEFAULT, r1, r4, false);     // Catch: MediaCodecUtil.DecoderQueryException -> L20
            int r12 = 0;
        L7:
            if (r12 >= r42.size()) goto L19;
            if (r42.get(r12).capabilities == null) goto L18;
            MediaCodecInfo.VideoCapabilities r2 = r42.get(r12).capabilities.getVideoCapabilities();     // Catch: MediaCodecUtil.DecoderQueryException -> L20
            if (r2 == null) goto L18;
            List<MediaCodecInfo.VideoCapabilities.PerformancePoint> r22 = r2.getSupportedPerformancePoints();     // Catch: MediaCodecUtil.DecoderQueryException -> L20
            if (r22 == null) goto L18;
            if (r22.isEmpty() == true) goto L18;
            MediaCodecPerformancePointCoverageProvider$Api29$$ExternalSyntheticApiModelOutline1.m();     // Catch: MediaCodecUtil.DecoderQueryException -> L20
            return evaluatePerformancePointCoverage(r22, MediaCodecPerformancePointCoverageProvider$Api29$$ExternalSyntheticApiModelOutline0.m(1280, 720, 60));
        L18:
            r12 = r12 + 1;
        L19:
            return 0;
        }

        private static int evaluatePerformancePointCoverage(List<MediaCodecInfo.VideoCapabilities.PerformancePoint> r2, MediaCodecInfo.VideoCapabilities.PerformancePoint r3) {
            int r0 = 0;
        L4:
            if (r0 >= r2.size()) goto L10;
            if (MediaCodecPerformancePointCoverageProvider$Api29$$ExternalSyntheticApiModelOutline2.m(r2.get(r0)).covers(r3) == true) goto L7;
            r0 = r0 + 1;
            goto L4
        L7:
            return 2;
        L10:
            return 1;
        }

        private static boolean shouldIgnorePerformancePoints() {
            if (Build.VERSION.SDK_INT < 35) goto L5;
            return false;
        L5:
            int r0 = evaluateH264RequiredSupport(false);
            int r3 = evaluateH264RequiredSupport(true);
            if (r0 != 0) goto L9;
            return true;
        L9:
            if (r3 != 0) goto L13;
            if (r0 == 2) goto L12;
            return true;
        L12:
            return false;
        L13:
            if (r0 != 2) goto L17;
            if (r3 != 2) goto L17;
            return false;
        L17:
            return true;
        }
    }

    public static /* synthetic */ Boolean access$000() {
        return shouldIgnorePerformancePoints;
    }

    public static /* synthetic */ Boolean access$002(Boolean r0) {
        shouldIgnorePerformancePoints = r0;
        return r0;
    }

    public static int areResolutionAndFrameRateCovered(MediaCodecInfo.VideoCapabilities r2, int r3, int r4, double r5) {
        if (Build.VERSION.SDK_INT < 29) goto L11;
        Boolean r0 = shouldIgnorePerformancePoints;
        if (r0 == null) goto L10;
        if (r0.booleanValue() == false) goto L10;
        return 0;
    L10:
        return Api29.areResolutionAndFrameRateCovered(r2, r3, r4, r5);
    L11:
        return 0;
    }
}
