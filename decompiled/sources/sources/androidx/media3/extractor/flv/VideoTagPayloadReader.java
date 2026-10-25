package androidx.media3.extractor.flv;

import androidx.media3.common.Format;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.AvcConfig;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.flv.TagPayloadReader;
import org.telegram.messenger.MediaController;

/* loaded from: classes4.dex */
final class VideoTagPayloadReader extends TagPayloadReader {
    private int frameType;
    private boolean hasOutputFormat;
    private boolean hasOutputKeyframe;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private int nalUnitLengthFieldLength;

    public VideoTagPayloadReader(TrackOutput r2) {
        super(r2);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
    }

    @Override // androidx.media3.extractor.flv.TagPayloadReader
    public boolean parseHeader(ParsableByteArray r3) {
        int r32 = r3.readUnsignedByte();
        int r0 = (r32 >> 4) & 15;
        int r33 = r32 & 15;
        if (r33 != 7) goto L11;
        this.frameType = r0;
        if (r0 == 5) goto L8;
        return true;
    L8:
        return false;
    L11:
        throw new TagPayloadReader.UnsupportedFormatException("Video format not supported: " + r33);
    }

    @Override // androidx.media3.extractor.flv.TagPayloadReader
    public boolean parsePayload(ParsableByteArray r11, long r12) {
        int r0 = r11.readUnsignedByte();
        long r4 = r12 + (r11.readInt24() * 1000);
        if (r0 == 0) goto L5;
    L8:
        if (r0 == 1) goto L10;
    L25:
        return false;
    L10:
        if (this.hasOutputFormat == false) goto L25;
        if (this.frameType != 1) goto L14;
        int r6 = 1;
    L16:
        if (this.hasOutputKeyframe == true) goto L19;
        if (r6 != 0) goto L19;
        return false;
    L19:
        byte[] r02 = this.nalLength.getData();
        r02[0] = 0;
        r02[1] = 0;
        r02[2] = 0;
        int r03 = 4 - this.nalUnitLengthFieldLength;
        int r7 = 0;
    L21:
        if (r11.bytesLeft() <= 0) goto L23;
        r11.readBytes(this.nalLength.getData(), r03, this.nalUnitLengthFieldLength);
        this.nalLength.setPosition(0);
        int r2 = this.nalLength.readUnsignedIntToInt();
        this.nalStartCode.setPosition(0);
        this.output.sampleData(this.nalStartCode, 4);
        this.output.sampleData(r11, r2);
        r7 = (r7 + 4) + r2;
        goto L21
    L23:
        this.output.sampleMetadata(r4, r6, r7, 0, null);
        this.hasOutputKeyframe = true;
        return true;
    L14:
        r6 = 0;
        goto L16
    L5:
        if (this.hasOutputFormat == true) goto L8;
        ParsableByteArray r04 = new ParsableByteArray(new byte[r11.bytesLeft()]);
        r11.readBytes(r04.getData(), 0, r11.bytesLeft());
        AvcConfig r112 = AvcConfig.parse(r04);
        this.nalUnitLengthFieldLength = r112.nalUnitLengthFieldLength;
        this.output.format(new Format.Builder().setContainerMimeType("video/x-flv").setSampleMimeType(MediaController.VIDEO_MIME_TYPE).setCodecs(r112.codecs).setWidth(r112.width).setHeight(r112.height).setPixelWidthHeightRatio(r112.pixelWidthHeightRatio).setInitializationData(r112.initializationData).build());
        this.hasOutputFormat = true;
        return false;
    }
}
