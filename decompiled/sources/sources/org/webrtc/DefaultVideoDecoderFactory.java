package org.webrtc;

import java.util.Arrays;
import java.util.LinkedHashSet;
import org.webrtc.EglBase;

/* loaded from: classes6.dex */
public class DefaultVideoDecoderFactory implements VideoDecoderFactory {
    private final VideoDecoderFactory hardwareVideoDecoderFactory;
    private final VideoDecoderFactory platformSoftwareVideoDecoderFactory;
    private final VideoDecoderFactory softwareVideoDecoderFactory;

    public DefaultVideoDecoderFactory(EglBase.Context r2) {
        this.softwareVideoDecoderFactory = new SoftwareVideoDecoderFactory();
        this.hardwareVideoDecoderFactory = new HardwareVideoDecoderFactory(r2);
        this.platformSoftwareVideoDecoderFactory = new PlatformSoftwareVideoDecoderFactory(r2);
    }

    public DefaultVideoDecoderFactory(VideoDecoderFactory r2) {
        this.softwareVideoDecoderFactory = new SoftwareVideoDecoderFactory();
        this.hardwareVideoDecoderFactory = r2;
        this.platformSoftwareVideoDecoderFactory = null;
    }

    @Override // org.webrtc.VideoDecoderFactory
    public VideoDecoder createDecoder(VideoCodecInfo r3) {
        VideoDecoder r0 = this.softwareVideoDecoderFactory.createDecoder(r3);
        VideoDecoder r1 = this.hardwareVideoDecoderFactory.createDecoder(r3);
        if (r0 != null) goto L7;
        VideoDecoderFactory r2 = this.platformSoftwareVideoDecoderFactory;
        if (r2 == null) goto L7;
        r0 = r2.createDecoder(r3);
    L7:
        if (r1 == null) goto L11;
        if (r0 == null) goto L11;
        return new VideoDecoderFallback(r0, r1);
    L11:
        if (r1 == null) goto L13;
        return r1;
    L13:
        return r0;
    }

    @Override // org.webrtc.VideoDecoderFactory
    public VideoCodecInfo[] getSupportedCodecs() {
        LinkedHashSet r0 = new LinkedHashSet();
        r0.addAll(Arrays.asList(this.softwareVideoDecoderFactory.getSupportedCodecs()));
        r0.addAll(Arrays.asList(this.hardwareVideoDecoderFactory.getSupportedCodecs()));
        VideoDecoderFactory r2 = this.platformSoftwareVideoDecoderFactory;
        if (r2 == null) goto L6;
        r0.addAll(Arrays.asList(r2.getSupportedCodecs()));
    L6:
        return (VideoCodecInfo[]) r0.toArray(new VideoCodecInfo[r0.size()]);
    }
}
