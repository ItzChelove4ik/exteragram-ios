package org.webrtc;

import android.media.MediaCodecInfo;
import org.webrtc.EglBase;

/* loaded from: classes6.dex */
public class PlatformSoftwareVideoDecoderFactory extends MediaCodecVideoDecoderFactory {
    private static final Predicate<MediaCodecInfo> defaultAllowedPredicate = null;

    /* renamed from: org.webrtc.PlatformSoftwareVideoDecoderFactory$1, reason: invalid class name */
    public class AnonymousClass1 implements Predicate<MediaCodecInfo> {
        public AnonymousClass1() {
        }

        /* renamed from: test, reason: avoid collision after fix types in other method */
        public boolean test2(MediaCodecInfo r1) {
            return MediaCodecUtils.isSoftwareOnly(r1);
        }

        @Override // org.webrtc.Predicate
        public /* bridge */ /* synthetic */ boolean test(MediaCodecInfo r1) {
            return test2(r1);
        }
    }

    static {
        defaultAllowedPredicate = new AnonymousClass1();
    }

    public PlatformSoftwareVideoDecoderFactory(EglBase.Context r2) {
        super(r2, defaultAllowedPredicate);
    }

    @Override // org.webrtc.MediaCodecVideoDecoderFactory, org.webrtc.VideoDecoderFactory
    public /* bridge */ /* synthetic */ VideoDecoder createDecoder(VideoCodecInfo r1) {
        return super.createDecoder(r1);
    }

    @Override // org.webrtc.MediaCodecVideoDecoderFactory, org.webrtc.VideoDecoderFactory
    public /* bridge */ /* synthetic */ VideoCodecInfo[] getSupportedCodecs() {
        return super.getSupportedCodecs();
    }
}
