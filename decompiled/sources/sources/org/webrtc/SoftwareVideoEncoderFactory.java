package org.webrtc;

import java.util.List;

/* loaded from: classes6.dex */
public class SoftwareVideoEncoderFactory implements VideoEncoderFactory {
    private static final String TAG = "SoftwareVideoEncoderFactory";
    private final long nativeFactory;

    /* renamed from: org.webrtc.SoftwareVideoEncoderFactory$1, reason: invalid class name */
    public class AnonymousClass1 extends WrappedNativeVideoEncoder {
        final /* synthetic */ SoftwareVideoEncoderFactory this$0;
        final /* synthetic */ long val$nativeEncoder;

        public AnonymousClass1(SoftwareVideoEncoderFactory r1, long r2) {
            this.this$0 = r1;
            this.val$nativeEncoder = r2;
        }

        @Override // org.webrtc.WrappedNativeVideoEncoder, org.webrtc.VideoEncoder
        public long createNativeVideoEncoder() {
            return this.val$nativeEncoder;
        }

        @Override // org.webrtc.WrappedNativeVideoEncoder, org.webrtc.VideoEncoder
        public boolean isHardwareEncoder() {
            return false;
        }
    }

    public SoftwareVideoEncoderFactory() {
        this.nativeFactory = nativeCreateFactory();
    }

    private static native long nativeCreateEncoder(long r0, VideoCodecInfo r2);

    private static native long nativeCreateFactory();

    private static native List<VideoCodecInfo> nativeGetSupportedCodecs(long r0);

    @Override // org.webrtc.VideoEncoderFactory
    public VideoEncoder createEncoder(VideoCodecInfo r5) {
        long r0 = nativeCreateEncoder(this.nativeFactory, r5);
        if (r0 != 0) goto L7;
        Logging.w(TAG, "Trying to create encoder for unsupported format. " + r5);
        return null;
    L7:
        return new AnonymousClass1(this, r0);
    }

    @Override // org.webrtc.VideoEncoderFactory
    public VideoCodecInfo[] getSupportedCodecs() {
        return (VideoCodecInfo[]) nativeGetSupportedCodecs(this.nativeFactory).toArray(new VideoCodecInfo[0]);
    }
}
