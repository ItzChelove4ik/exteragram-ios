package org.webrtc;

import java.util.Arrays;
import java.util.LinkedHashSet;
import org.webrtc.EglBase;

/* loaded from: classes6.dex */
public class DefaultVideoEncoderFactory implements VideoEncoderFactory {
    private final VideoEncoderFactory hardwareVideoEncoderFactory;
    private final VideoEncoderFactory softwareVideoEncoderFactory;

    public DefaultVideoEncoderFactory(EglBase.Context r2, boolean r3, boolean r4) {
        this.softwareVideoEncoderFactory = new SoftwareVideoEncoderFactory();
        this.hardwareVideoEncoderFactory = new HardwareVideoEncoderFactory(r2, r3, r4);
    }

    public DefaultVideoEncoderFactory(VideoEncoderFactory r2) {
        this.softwareVideoEncoderFactory = new SoftwareVideoEncoderFactory();
        this.hardwareVideoEncoderFactory = r2;
    }

    @Override // org.webrtc.VideoEncoderFactory
    public VideoEncoder createEncoder(VideoCodecInfo r2) {
        VideoEncoder r0 = this.softwareVideoEncoderFactory.createEncoder(r2);
        VideoEncoder r1 = this.hardwareVideoEncoderFactory.createEncoder(r2);
        if (r1 == null) goto L7;
        if (r0 == null) goto L7;
        return new VideoEncoderFallback(r0, r1);
    L7:
        if (r1 == null) goto L9;
        return r1;
    L9:
        return r0;
    }

    @Override // org.webrtc.VideoEncoderFactory
    public VideoCodecInfo[] getSupportedCodecs() {
        LinkedHashSet r0 = new LinkedHashSet();
        r0.addAll(Arrays.asList(this.softwareVideoEncoderFactory.getSupportedCodecs()));
        r0.addAll(Arrays.asList(this.hardwareVideoEncoderFactory.getSupportedCodecs()));
        return (VideoCodecInfo[]) r0.toArray(new VideoCodecInfo[r0.size()]);
    }
}
