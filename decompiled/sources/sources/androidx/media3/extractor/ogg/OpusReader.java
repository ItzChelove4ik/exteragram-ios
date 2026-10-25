package androidx.media3.extractor.ogg;

import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.OpusUtil;
import androidx.media3.extractor.VorbisUtil;
import androidx.media3.extractor.ogg.StreamReader;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.List;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

/* loaded from: classes4.dex */
final class OpusReader extends StreamReader {
    private static final byte[] OPUS_COMMENT_HEADER_SIGNATURE = null;
    private static final byte[] OPUS_ID_HEADER_SIGNATURE = null;
    private boolean firstCommentHeaderSeen;

    static {
        OPUS_ID_HEADER_SIGNATURE = new byte[]{79, 112, 117, 115, 72, 101, 97, 100};
        OPUS_COMMENT_HEADER_SIGNATURE = new byte[]{79, 112, 117, 115, 84, 97, 103, 115};
    }

    public OpusReader() {
    }

    private static boolean peekPacketStartsWith(ParsableByteArray r4, byte[] r5) {
        if (r4.bytesLeft() >= r5.length) goto L5;
        return false;
    L5:
        int r0 = r4.getPosition();
        byte[] r1 = new byte[r5.length];
        r4.readBytes(r1, 0, r5.length);
        r4.setPosition(r0);
        return Arrays.equals(r1, r5);
    }

    public static boolean verifyBitstreamType(ParsableByteArray r1) {
        return peekPacketStartsWith(r1, OPUS_ID_HEADER_SIGNATURE);
    }

    @Override // androidx.media3.extractor.ogg.StreamReader
    public long preparePayload(ParsableByteArray r3) {
        return convertTimeToGranule(OpusUtil.getPacketDurationUs(r3.getData()));
    }

    @Override // androidx.media3.extractor.ogg.StreamReader
    @EnsuresNonNullIf(expression = {"#3.format"}, result = false)
    public boolean readHeaders(ParsableByteArray r3, long r4, StreamReader.SetupData r6) {
        if (peekPacketStartsWith(r3, OPUS_ID_HEADER_SIGNATURE) == false) goto L9;
        byte[] r2 = Arrays.copyOf(r3.getData(), r3.limit());
        int r32 = OpusUtil.getChannelCount(r2);
        List<byte[]> r22 = OpusUtil.buildInitializationData(r2);
        if (r6.format == null) goto L7;
        return true;
    L7:
        r6.format = new Format.Builder().setContainerMimeType("audio/ogg").setSampleMimeType("audio/opus").setChannelCount(r32).setSampleRate(48000).setInitializationData(r22).build();
        return true;
    L9:
        byte[] r42 = OPUS_COMMENT_HEADER_SIGNATURE;
        if (peekPacketStartsWith(r3, r42) == false) goto L19;
        Assertions.checkStateNotNull(r6.format);
        if (this.firstCommentHeaderSeen == false) goto L14;
        return true;
    L14:
        this.firstCommentHeaderSeen = true;
        r3.skipBytes(r42.length);
        Metadata r23 = VorbisUtil.parseVorbisComments(ImmutableList.copyOf(VorbisUtil.readVorbisCommentHeader(r3, false, false).comments));
        if (r23 != null) goto L17;
        return true;
    L17:
        r6.format = r6.format.buildUpon().setMetadata(r23.copyWithAppendedEntriesFrom(r6.format.metadata)).build();
        return true;
    L19:
        Assertions.checkStateNotNull(r6.format);
        return false;
    }

    @Override // androidx.media3.extractor.ogg.StreamReader
    public void reset(boolean r1) {
        super.reset(r1);
        if (r1 == false) goto L6;
        this.firstCommentHeaderSeen = false;
        return;
    }
}
