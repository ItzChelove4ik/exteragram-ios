package org.telegram.messenger.audioinfo.mp3;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.zip.InflaterInputStream;
import org.telegram.messenger.audioinfo.util.RangeInputStream;

/* loaded from: classes5.dex */
public class ID3v2TagBody {
    private final ID3v2DataInput data;
    private final RangeInputStream input;
    private final ID3v2TagHeader tagHeader;

    public ID3v2TagBody(InputStream r7, long r8, int r10, ID3v2TagHeader r11) {
        RangeInputStream r0 = new RangeInputStream(r7, r8, r10);
        this.input = r0;
        this.data = new ID3v2DataInput(r0);
        this.tagHeader = r11;
    }

    public ID3v2FrameBody frameBody(ID3v2FrameHeader r11) {
        int r0 = r11.getBodySize();
        InputStream r1 = this.input;
        if (r11.isUnsynchronization() == false) goto L17;
        byte[] r02 = this.data.readFully(r11.getBodySize());
        int r12 = r02.length;
        int r3 = 0;
        int r4 = 0;
        boolean r5 = false;
    L5:
        if (r4 >= r12) goto L15;
        byte r6 = r02[r4];
        if (r5 == false) goto L9;
        if (r6 != 0) goto L9;
    L11:
        if (r6 != (-1)) goto L13;
        r5 = true;
    L14:
        r4 = r4 + 1;
        goto L5
    L13:
        r5 = false;
    L9:
        r02[r3] = r6;
        r3 = r3 + 1;
        goto L11
    L15:
        r1 = new ByteArrayInputStream(r02, 0, r3);
        r0 = r3;
    L17:
        if (r11.isEncryption() == true) goto L26;
        if (r11.isCompression() == false) goto L22;
        r0 = r11.getDataLengthIndicator();
        InputStream r42 = new InflaterInputStream(r1);
    L24:
        return new ID3v2FrameBody(r42, r11.getHeaderSize(), r0, this.tagHeader, r11);
    L22:
        r42 = r1;
        goto L24
    L26:
        throw new ID3v2Exception("Frame encryption is not supported");
    }

    public ID3v2DataInput getData() {
        return this.data;
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

    public String toString() {
        return "id3v2tag[pos=" + getPosition() + ", " + getRemainingLength() + " left]";
    }
}
