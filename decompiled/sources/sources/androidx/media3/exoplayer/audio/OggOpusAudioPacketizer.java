package androidx.media3.exoplayer.audio;

import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.extractor.OpusUtil;
import com.google.common.primitives.UnsignedBytes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes4.dex */
public final class OggOpusAudioPacketizer {
    private static final byte[] OGG_DEFAULT_COMMENT_HEADER_PAGE = null;
    private static final byte[] OGG_DEFAULT_ID_HEADER_PAGE = null;
    private int granulePosition;
    private ByteBuffer outputBuffer;
    private int pageSequenceNumber;

    static {
        OGG_DEFAULT_ID_HEADER_PAGE = new byte[]{79, 103, 103, 83, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 28, -43, -59, -9, 1, 19, 79, 112, 117, 115, 72, 101, 97, 100, 1, 2, 56, 1, ByteCompanionObject.MIN_VALUE, -69, 0, 0, 0, 0, 0};
        OGG_DEFAULT_COMMENT_HEADER_PAGE = new byte[]{79, 103, 103, 83, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 11, -103, 87, 83, 1, 16, 79, 112, 117, 115, 84, 97, 103, 115, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    public OggOpusAudioPacketizer() {
        this.outputBuffer = AudioProcessor.EMPTY_BUFFER;
        this.granulePosition = 0;
        this.pageSequenceNumber = 2;
    }

    private ByteBuffer packetizeInternal(ByteBuffer r16, byte[] r17) {
        int r8 = r16.position();
        int r9 = r16.limit();
        int r10 = r9 - r8;
        int r5 = (r10 + 255) / 255;
        int r2 = (r5 + 27) + r10;
        if (this.pageSequenceNumber != 2) goto L8;
        if (r17 == null) goto L6;
        int r3 = r17.length + 28;
    L7:
        r2 = r2 + (OGG_DEFAULT_COMMENT_HEADER_PAGE.length + r3);
        int r14 = r3;
    L9:
        ByteBuffer r22 = replaceOutputBuffer(r2);
        if (this.pageSequenceNumber != 2) goto L15;
        if (r17 == null) goto L13;
        writeOggIdHeaderPage(r22, r17);
    L14:
        r22.put(OGG_DEFAULT_COMMENT_HEADER_PAGE);
        goto L15
    L13:
        r22.put(OGG_DEFAULT_ID_HEADER_PAGE);
    L15:
        int r32 = this.granulePosition + OpusUtil.parsePacketAudioSampleCount(r16);
        this.granulePosition = r32;
        writeOggPacketHeader(r22, r32, this.pageSequenceNumber, r5, false);
        int r23 = 0;
    L16:
        if (r23 >= r5) goto L21;
        if (r10 < 255) goto L19;
        r22.put((byte) -1);
        r10 = r10 - 255;
    L20:
        r23 = r23 + 1;
        goto L16
    L19:
        r22.put((byte) r10);
        r10 = 0;
    L21:
        if (r8 >= r9) goto L23;
        r22.put(r16.get(r8));
        r8 = r8 + 1;
        goto L21
    L23:
        r16.position(r16.limit());
        r22.flip();
        if (this.pageSequenceNumber != 2) goto L26;
        byte[] r24 = r22.array();
        int r4 = r22.arrayOffset() + r14;
        byte[] r52 = OGG_DEFAULT_COMMENT_HEADER_PAGE;
        r22.putInt((r14 + r52.length) + 22, Util.crc32(r24, r4 + r52.length, r22.limit() - r22.position(), 0));
    L27:
        this.pageSequenceNumber++;
        return r22;
    L26:
        r22.putInt(22, Util.crc32(r22.array(), r22.arrayOffset(), r22.limit() - r22.position(), 0));
        goto L27
    L6:
        r3 = OGG_DEFAULT_ID_HEADER_PAGE.length;
        goto L7
    L8:
        r14 = 0;
        goto L9
    }

    private ByteBuffer replaceOutputBuffer(int r2) {
        if (this.outputBuffer.capacity() >= r2) goto L5;
        this.outputBuffer = ByteBuffer.allocate(r2).order(ByteOrder.LITTLE_ENDIAN);
    L7:
        return this.outputBuffer;
    L5:
        this.outputBuffer.clear();
        goto L7
    }

    private void writeOggIdHeaderPage(ByteBuffer r8, byte[] r9) {
        writeOggPacketHeader(r8, 0, 0, 1, true);
        r8.put(UnsignedBytes.checkedCast(r9.length));
        r8.put(r9);
        r8.putInt(22, Util.crc32(r8.array(), r8.arrayOffset(), r9.length + 28, 0));
        r8.position(r9.length + 28);
    }

    private void writeOggPacketHeader(ByteBuffer r1, long r2, int r4, int r5, boolean r6) {
        r1.put((byte) 79);
        r1.put((byte) 103);
        r1.put((byte) 103);
        r1.put((byte) 83);
        r1.put((byte) 0);
        if (r6 == false) goto L5;
        byte r62 = 2;
    L6:
        r1.put(r62);
        r1.putLong(r2);
        r1.putInt(0);
        r1.putInt(r4);
        r1.putInt(0);
        r1.put(UnsignedBytes.checkedCast(r5));
        return;
    L5:
        r62 = 0;
        goto L6
    }

    public void packetize(DecoderInputBuffer r3, List<byte[]> r4) {
        Assertions.checkNotNull(r3.data);
        if ((r3.data.limit() - r3.data.position()) != 0) goto L6;
        return;
    L6:
        if (this.pageSequenceNumber == 2) goto L8;
    L12:
        byte[] r42 = null;
    L13:
        this.outputBuffer = packetizeInternal(r3.data, r42);
        r3.clear();
        r3.ensureSpaceForWrite(this.outputBuffer.remaining());
        r3.data.put(this.outputBuffer);
        r3.flip();
        return;
    L8:
        if (r4.size() != 1) goto L10;
    L11:
        r42 = r4.get(0);
        goto L13
    L10:
        if (r4.size() != 3) goto L12;
        goto L11
    }

    public void reset() {
        this.outputBuffer = AudioProcessor.EMPTY_BUFFER;
        this.granulePosition = 0;
        this.pageSequenceNumber = 2;
    }
}
