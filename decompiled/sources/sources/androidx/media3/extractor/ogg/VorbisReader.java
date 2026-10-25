package androidx.media3.extractor.ogg;

import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.VorbisUtil;
import androidx.media3.extractor.ogg.StreamReader;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

/* loaded from: classes4.dex */
final class VorbisReader extends StreamReader {
    private VorbisUtil.CommentHeader commentHeader;
    private int previousPacketBlockSize;
    private boolean seenFirstAudioPacket;
    private VorbisUtil.VorbisIdHeader vorbisIdHeader;
    private VorbisSetup vorbisSetup;

    public static final class VorbisSetup {
        public final VorbisUtil.CommentHeader commentHeader;
        public final int iLogModes;
        public final VorbisUtil.VorbisIdHeader idHeader;
        public final VorbisUtil.Mode[] modes;
        public final byte[] setupHeaderData;

        public VorbisSetup(VorbisUtil.VorbisIdHeader r1, VorbisUtil.CommentHeader r2, byte[] r3, VorbisUtil.Mode[] r4, int r5) {
            this.idHeader = r1;
            this.commentHeader = r2;
            this.setupHeaderData = r3;
            this.modes = r4;
            this.iLogModes = r5;
        }
    }

    public VorbisReader() {
    }

    public static void appendNumberOfSamples(ParsableByteArray r6, long r7) {
        if (r6.capacity() >= (r6.limit() + 4)) goto L5;
        r6.reset(Arrays.copyOf(r6.getData(), r6.limit() + 4));
    L6:
        byte[] r0 = r6.getData();
        r0[r6.limit() - 4] = (byte) (r7 & 255);
        r0[r6.limit() - 3] = (byte) ((r7 >>> 8) & 255);
        r0[r6.limit() - 2] = (byte) ((r7 >>> 16) & 255);
        r0[r6.limit() - 1] = (byte) ((r7 >>> 24) & 255);
        return;
    L5:
        r6.setLimit(r6.limit() + 4);
        goto L6
    }

    private static int decodeBlockSize(byte r2, VorbisSetup r3) {
        int r22 = readBits(r2, r3.iLogModes, 1);
        boolean r23 = r3.modes[r22].blockFlag;
        VorbisUtil.VorbisIdHeader r32 = r3.idHeader;
        if (r23 == true) goto L7;
        return r32.blockSize0;
    L7:
        return r32.blockSize1;
    }

    public static int readBits(byte r0, int r1, int r2) {
        return (r0 >> r2) & (255 >>> (8 - r1));
    }

    public static boolean verifyBitstreamType(ParsableByteArray r1) {
        return VorbisUtil.verifyVorbisHeaderCapturePattern(1, r1, true);
    L5:
        return false;
    }

    @Override // androidx.media3.extractor.ogg.StreamReader
    public void onSeekEnd(long r3) {
        super.onSeekEnd(r3);
        int r4 = 0;
        if (r3 == 0) goto L5;
        boolean r32 = true;
    L6:
        this.seenFirstAudioPacket = r32;
        VorbisUtil.VorbisIdHeader r33 = this.vorbisIdHeader;
        if (r33 == null) goto L9;
        r4 = r33.blockSize0;
    L9:
        this.previousPacketBlockSize = r4;
        return;
    L5:
        r32 = false;
        goto L6
    }

    @Override // androidx.media3.extractor.ogg.StreamReader
    public long preparePayload(ParsableByteArray r6) {
        int r1 = 0;
        if ((r6.getData()[0] & 1) != 1) goto L6;
        return -1;
    L6:
        int r0 = decodeBlockSize(r6.getData()[0], (VorbisSetup) Assertions.checkStateNotNull(this.vorbisSetup));
        if (this.seenFirstAudioPacket == false) goto L9;
        r1 = (this.previousPacketBlockSize + r0) / 4;
    L9:
        long r3 = r1;
        appendNumberOfSamples(r6, r3);
        this.seenFirstAudioPacket = true;
        this.previousPacketBlockSize = r0;
        return r3;
    }

    @Override // androidx.media3.extractor.ogg.StreamReader
    @EnsuresNonNullIf(expression = {"#3.format"}, result = false)
    public boolean readHeaders(ParsableByteArray r3, long r4, StreamReader.SetupData r6) {
        if (this.vorbisSetup == null) goto L6;
        Assertions.checkNotNull(r6.format);
        return false;
    L6:
        VorbisSetup r32 = readSetupHeaders(r3);
        this.vorbisSetup = r32;
        if (r32 != null) goto L9;
        return true;
    L9:
        VorbisUtil.VorbisIdHeader r42 = r32.idHeader;
        ArrayList r5 = new ArrayList();
        r5.add(r42.data);
        r5.add(r32.setupHeaderData);
        Metadata r33 = VorbisUtil.parseVorbisComments(ImmutableList.copyOf(r32.commentHeader.comments));
        r6.format = new Format.Builder().setContainerMimeType("audio/ogg").setSampleMimeType("audio/vorbis").setAverageBitrate(r42.bitrateNominal).setPeakBitrate(r42.bitrateMaximum).setChannelCount(r42.channels).setSampleRate(r42.sampleRate).setInitializationData(r5).setMetadata(r33).build();
        return true;
    }

    public VorbisSetup readSetupHeaders(ParsableByteArray r7) {
        VorbisUtil.VorbisIdHeader r1 = this.vorbisIdHeader;
        if (r1 != null) goto L6;
        this.vorbisIdHeader = VorbisUtil.readVorbisIdentificationHeader(r7);
        return null;
    L6:
        VorbisUtil.CommentHeader r2 = this.commentHeader;
        if (r2 != null) goto L10;
        this.commentHeader = VorbisUtil.readVorbisCommentHeader(r7);
        return null;
    L10:
        byte[] r3 = new byte[r7.limit()];
        System.arraycopy(r7.getData(), 0, r3, 0, r7.limit());
        return new VorbisSetup(r1, r2, r3, VorbisUtil.readVorbisModes(r7, r1.channels), VorbisUtil.iLog(r4.length - 1));
    }

    @Override // androidx.media3.extractor.ogg.StreamReader
    public void reset(boolean r1) {
        super.reset(r1);
        if (r1 == false) goto L5;
        this.vorbisSetup = null;
        this.vorbisIdHeader = null;
        this.commentHeader = null;
    L5:
        this.previousPacketBlockSize = 0;
        this.seenFirstAudioPacket = false;
    }
}
