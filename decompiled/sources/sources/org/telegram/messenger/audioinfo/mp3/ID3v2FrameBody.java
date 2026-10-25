package org.telegram.messenger.audioinfo.mp3;

import java.io.InputStream;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.audioinfo.util.RangeInputStream;

/* loaded from: classes5.dex */
public class ID3v2FrameBody {
    static final ThreadLocal<Buffer> textBuffer = null;
    private final ID3v2DataInput data;
    private final ID3v2FrameHeader frameHeader;
    private final RangeInputStream input;
    private final ID3v2TagHeader tagHeader;

    /* renamed from: org.telegram.messenger.audioinfo.mp3.ID3v2FrameBody$1, reason: invalid class name */
    public class AnonymousClass1 extends ThreadLocal<Buffer> {
        public AnonymousClass1() {
        }

        @Override // java.lang.ThreadLocal
        public /* bridge */ /* synthetic */ Buffer initialValue() {
            return initialValue2();
        }

        @Override // java.lang.ThreadLocal
        /* renamed from: initialValue, reason: avoid collision after fix types in other method */
        public Buffer initialValue2() {
            return new Buffer(4096);
        }
    }

    public static final class Buffer {
        byte[] bytes;

        public Buffer(int r1) {
            this.bytes = new byte[r1];
        }

        public byte[] bytes(int r3) {
            byte[] r0 = this.bytes;
            if (r3 <= r0.length) goto L10;
            int r02 = r0.length;
        L5:
            r02 = r02 * 2;
            if (r3 > r02) goto L5;
            this.bytes = new byte[r02];
        L10:
            return this.bytes;
        }
    }

    static {
        textBuffer = new AnonymousClass1();
    }

    public ID3v2FrameBody(InputStream r7, long r8, int r10, ID3v2TagHeader r11, ID3v2FrameHeader r12) {
        RangeInputStream r0 = new RangeInputStream(r7, r8, r10);
        this.input = r0;
        this.data = new ID3v2DataInput(r0);
        this.tagHeader = r11;
        this.frameHeader = r12;
    }

    private String extractString(byte[] r5, int r6, int r7, ID3v2Encoding r8, boolean r9) {
        if (r9 == false) goto L27;
        int r92 = 0;
        int r1 = 0;
    L5:
        if (r92 >= r7) goto L27;
        int r2 = r6 + r92;
        if (r5[r2] == 0) goto L9;
    L16:
        r1 = 0;
    L17:
        r92 = r92 + 1;
        goto L5
    L9:
        if (r8 != ID3v2Encoding.UTF_16) goto L13;
        if (r1 != 0) goto L13;
        if ((r2 % 2) != 0) goto L16;
    L13:
        r1 = r1 + 1;
        if (r1 != r8.getZeroBytes()) goto L17;
        r7 = (r92 + 1) - r8.getZeroBytes();
    L27:
        String r93 = new String(r5, r6, r7, r8.getCharset().name());     // Catch: Exception -> L25
        if (r93.length() > 0) goto L21;
    L24:
        return r93;
    L21:
        if (r93.charAt(0) != 65279) goto L24;
        return r93.substring(1);
    L25:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public ID3v2DataInput getData() {
        return this.data;
    }

    public ID3v2FrameHeader getFrameHeader() {
        return this.frameHeader;
    }

    public long getPosition() {
        return this.input.getPosition();
    }

    public long getRemainingLength() {
        return this.input.getRemainingLength();
    }

    public ID3v2TagHeader getTagHeader() {
        return this.tagHeader;
    }

    public ID3v2Encoding readEncoding() {
        byte r3 = this.data.readByte();
        if (r3 == 0) goto L19;
        if (r3 == 1) goto L17;
        if (r3 == 2) goto L15;
        if (r3 != 3) goto L13;
        return ID3v2Encoding.UTF_8;
    L13:
        throw new ID3v2Exception("Invalid encoding: " + r3);
    L15:
        return ID3v2Encoding.UTF_16BE;
    L17:
        return ID3v2Encoding.UTF_16;
    L19:
        return ID3v2Encoding.ISO_8859_1;
    }

    public String readFixedLengthString(int r8, ID3v2Encoding r9) {
        if (r8 > getRemainingLength()) goto L7;
        byte[] r2 = textBuffer.get().bytes(r8);
        this.data.readFully(r2, 0, r8);
        return extractString(r2, 0, r8, r9, true);
    L7:
        throw new ID3v2Exception("Could not read fixed-length string of length: " + r8);
    }

    public String readZeroTerminatedString(int r8, ID3v2Encoding r9) {
        int r82 = Math.min(r8, (int) getRemainingLength());
        byte[] r2 = textBuffer.get().bytes(r82);
        int r1 = 0;
        int r3 = 0;
    L3:
        if (r1 >= r82) goto L21;
        byte r4 = this.data.readByte();
        r2[r1] = r4;
        if (r4 == 0) goto L7;
    L12:
        ID3v2Encoding r5 = r9;
        r3 = 0;
    L19:
        r1 = r1 + 1;
        r9 = r5;
        goto L3
    L7:
        if (r9 != ID3v2Encoding.UTF_16) goto L13;
        if (r3 != 0) goto L13;
        if ((r1 % 2) != 0) goto L12;
    L13:
        r3 = r3 + 1;
        if (r3 == r9.getZeroBytes()) goto L16;
        r5 = r9;
        goto L19
    L16:
        return extractString(r2, 0, (r1 + 1) - r9.getZeroBytes(), r9, false);
    L21:
        throw new ID3v2Exception("Could not read zero-termiated string");
    }

    public String toString() {
        return "id3v2frame[pos=" + getPosition() + ", " + getRemainingLength() + " left]";
    }
}
