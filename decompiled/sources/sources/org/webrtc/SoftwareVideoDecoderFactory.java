package org.webrtc;

import java.util.List;

/* loaded from: classes6.dex */
public class SoftwareVideoDecoderFactory implements VideoDecoderFactory {
    private static final String TAG = "SoftwareVideoDecoderFactory";
    private final long nativeFactory;

    /* renamed from: org.webrtc.SoftwareVideoDecoderFactory$1, reason: invalid class name */
    public class AnonymousClass1 extends WrappedNativeVideoDecoder {
        final /* synthetic */ SoftwareVideoDecoderFactory this$0;
        final /* synthetic */ VideoCodecInfo val$info;

        public AnonymousClass1(SoftwareVideoDecoderFactory r1, VideoCodecInfo r2) {
            this.this$0 = r1;
            this.val$info = r2;
        }

        @Override // org.webrtc.WrappedNativeVideoDecoder, org.webrtc.VideoDecoder
        public long createNative(long r3) {
            return SoftwareVideoDecoderFactory.m22378$$Nest$smnativeCreate(SoftwareVideoDecoderFactory.m22377$$Nest$fgetnativeFactory(this.this$0), r3, this.val$info);
        }
    }

    /* renamed from: -$$Nest$fgetnativeFactory, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22377$$Nest$fgetnativeFactory(SoftwareVideoDecoderFactory r2) {
        return r2.nativeFactory;
    }

    /* renamed from: -$$Nest$smnativeCreate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22378$$Nest$smnativeCreate(long r0, long r2, VideoCodecInfo r4) {
        return nativeCreate(r0, r2, r4);
    }

    public SoftwareVideoDecoderFactory() {
        this.nativeFactory = nativeCreateFactory();
    }

    private static native long nativeCreate(long r0, long r2, VideoCodecInfo r4);

    private static native long nativeCreateFactory();

    private static native List<VideoCodecInfo> nativeGetSupportedCodecs(long r0);

    private static native boolean nativeIsSupported(long r0, VideoCodecInfo r2);

    @Override // org.webrtc.VideoDecoderFactory
    public VideoDecoder createDecoder(VideoCodecInfo r3) {
        if (nativeIsSupported(this.nativeFactory, r3) == true) goto L7;
        Logging.w(TAG, "Trying to create decoder for unsupported format. " + r3);
        return null;
    L7:
        return new AnonymousClass1(this, r3);
    }

    @Override // org.webrtc.VideoDecoderFactory
    public VideoCodecInfo[] getSupportedCodecs() {
        return (VideoCodecInfo[]) nativeGetSupportedCodecs(this.nativeFactory).toArray(new VideoCodecInfo[0]);
    }
}
