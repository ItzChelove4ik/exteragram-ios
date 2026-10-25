package androidx.media3.extractor.flv;

import androidx.media3.common.Format;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.flv.TagPayloadReader;
import java.util.Collections;
import org.telegram.messenger.MediaController;

/* loaded from: classes4.dex */
final class AudioTagPayloadReader extends TagPayloadReader {
    private static final int[] AUDIO_SAMPLING_RATE_TABLE = null;
    private int audioFormat;
    private boolean hasOutputFormat;
    private boolean hasParsedAudioDataHeader;

    static {
        AUDIO_SAMPLING_RATE_TABLE = new int[]{5512, 11025, 22050, 44100};
    }

    public AudioTagPayloadReader(TrackOutput r1) {
        super(r1);
    }

    @Override // androidx.media3.extractor.flv.TagPayloadReader
    public boolean parseHeader(ParsableByteArray r5) {
        if (this.hasParsedAudioDataHeader == true) goto L22;
        int r52 = r5.readUnsignedByte();
        int r0 = (r52 >> 4) & 15;
        this.audioFormat = r0;
        if (r0 != 2) goto L8;
        this.output.format(new Format.Builder().setContainerMimeType("video/x-flv").setSampleMimeType("audio/mpeg").setChannelCount(1).setSampleRate(AUDIO_SAMPLING_RATE_TABLE[(r52 >> 2) & 3]).build());
        this.hasOutputFormat = true;
    L21:
        this.hasParsedAudioDataHeader = true;
    L23:
        return true;
    L8:
        if (r0 != 7) goto L10;
    L17:
        if (r0 != 7) goto L19;
        String r53 = "audio/g711-alaw";
    L20:
        this.output.format(new Format.Builder().setContainerMimeType("video/x-flv").setSampleMimeType(r53).setChannelCount(1).setSampleRate(8000).build());
        this.hasOutputFormat = true;
        goto L21
    L19:
        r53 = "audio/g711-mlaw";
        goto L20
    L10:
        if (r0 == 8) goto L17;
        if (r0 == 10) goto L21;
        throw new TagPayloadReader.UnsupportedFormatException("Audio format not supported: " + this.audioFormat);
    L22:
        r5.skipBytes(1);
        goto L23
    }

    @Override // androidx.media3.extractor.flv.TagPayloadReader
    public boolean parsePayload(ParsableByteArray r20, long r21) {
        if (this.audioFormat != 2) goto L6;
        int r9 = r20.bytesLeft();
        this.output.sampleData(r20, r9);
        this.output.sampleMetadata(r21, 1, r9, 0, null);
        return true;
    L6:
        int r2 = r20.readUnsignedByte();
        if (r2 != 0) goto L13;
        if (this.hasOutputFormat == true) goto L13;
        int r22 = r20.bytesLeft();
        byte[] r5 = new byte[r22];
        r20.readBytes(r5, 0, r22);
        AacUtil.Config r1 = AacUtil.parseAudioSpecificConfig(r5);
        Format r12 = new Format.Builder().setContainerMimeType("video/x-flv").setSampleMimeType(MediaController.AUDIO_MIME_TYPE).setCodecs(r1.codecs).setChannelCount(r1.channelCount).setSampleRate(r1.sampleRateHz).setInitializationData(Collections.singletonList(r5)).build();
        this.output.format(r12);
        this.hasOutputFormat = true;
        return false;
    L13:
        if (this.audioFormat != 10) goto L17;
        if (r2 == 1) goto L17;
        return false;
    L17:
        int r23 = r20.bytesLeft();
        this.output.sampleData(r20, r23);
        this.output.sampleMetadata(r21, 1, r23, 0, null);
        return true;
    }
}
