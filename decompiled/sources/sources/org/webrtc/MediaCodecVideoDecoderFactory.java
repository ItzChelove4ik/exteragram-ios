package org.webrtc;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import java.util.ArrayList;
import org.webrtc.EglBase;

/* loaded from: classes6.dex */
class MediaCodecVideoDecoderFactory implements VideoDecoderFactory {
    private static final String TAG = "MediaCodecVideoDecoderFactory";
    private final Predicate<MediaCodecInfo> codecAllowedPredicate;
    private final EglBase.Context sharedContext;

    public MediaCodecVideoDecoderFactory(EglBase.Context r1, Predicate<MediaCodecInfo> r2) {
        this.sharedContext = r1;
        this.codecAllowedPredicate = r2;
    }

    private MediaCodecInfo findCodecForType(VideoCodecMimeType r6) {
        int r0 = 0;
    L3:
        MediaCodecInfo r2 = null;
        if (r0 >= MediaCodecList.getCodecCount()) goto L17;
        r2 = MediaCodecList.getCodecInfoAt(r0);     // Catch: IllegalArgumentException -> L7
    L9:
        if (r2 == null) goto L16;
        if (r2.isEncoder() == true) goto L16;
        if (isSupportedCodec(r2, r6) == false) goto L16;
        return r2;
    L16:
        r0 = r0 + 1;
    L7:
        e = move-exception;
        Logging.e(TAG, "Cannot retrieve decoder codec info", e);
        goto L9
    L17:
        return null;
    }

    private boolean isCodecAllowed(MediaCodecInfo r1) {
        Predicate<MediaCodecInfo> r0 = this.codecAllowedPredicate;
        if (r0 != null) goto L7;
        return true;
    L7:
        return r0.test(r1);
    }

    private boolean isH264HighProfileSupported(MediaCodecInfo r2) {
        String r1 = r2.getName();
        if (r1.startsWith("OMX.qcom.") == false) goto L6;
        return true;
    L6:
        if (r1.startsWith("OMX.Exynos.") == false) goto L8;
        return true;
    L8:
        return false;
    }

    private boolean isSupportedCodec(MediaCodecInfo r3, VideoCodecMimeType r4) {
        if (MediaCodecUtils.codecSupportsType(r3, r4) == true) goto L6;
        return false;
    L6:
        if (MediaCodecUtils.selectColorFormat(MediaCodecUtils.DECODER_COLOR_FORMATS, r3.getCapabilitiesForType(r4.mimeType())) != null) goto L9;
        return false;
    L9:
        return isCodecAllowed(r3);
    }

    @Override // org.webrtc.VideoDecoderFactory
    public VideoDecoder createDecoder(VideoCodecInfo r7) {
        VideoCodecMimeType r3 = VideoCodecMimeType.valueOf(r7.getName());
        MediaCodecInfo r72 = findCodecForType(r3);
        if (r72 != null) goto L6;
        return null;
    L6:
        MediaCodecInfo.CodecCapabilities r0 = r72.getCapabilitiesForType(r3.mimeType());
        return new AndroidVideoDecoder(new MediaCodecWrapperFactoryImpl(), r72.getName(), r3, MediaCodecUtils.selectColorFormat(MediaCodecUtils.DECODER_COLOR_FORMATS, r0).intValue(), this.sharedContext);
    }

    @Override // org.webrtc.VideoDecoderFactory
    public VideoCodecInfo[] getSupportedCodecs() {
        ArrayList r0 = new ArrayList();
        VideoCodecMimeType[] r1 = {VideoCodecMimeType.VP8, VideoCodecMimeType.VP9, VideoCodecMimeType.H264, VideoCodecMimeType.AV1, VideoCodecMimeType.H265};
        int r3 = 0;
    L4:
        if (r3 >= 5) goto L15;
        VideoCodecMimeType r4 = r1[r3];
        MediaCodecInfo r5 = findCodecForType(r4);
        if (r5 == null) goto L13;
        String r6 = r4.name();
        if (r4 == VideoCodecMimeType.H264) goto L10;
    L12:
        r0.add(new VideoCodecInfo(r6, MediaCodecUtils.getCodecProperties(r4, false)));
        goto L13
    L10:
        if (isH264HighProfileSupported(r5) == false) goto L12;
        r0.add(new VideoCodecInfo(r6, MediaCodecUtils.getCodecProperties(r4, true)));
    L13:
        r3 = r3 + 1;
        goto L4
    L15:
        return (VideoCodecInfo[]) r0.toArray(new VideoCodecInfo[r0.size()]);
    }
}
