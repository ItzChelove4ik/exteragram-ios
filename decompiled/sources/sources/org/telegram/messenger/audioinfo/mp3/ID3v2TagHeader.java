package org.telegram.messenger.audioinfo.mp3;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import kotlin.jvm.internal.ByteCompanionObject;
import org.telegram.messenger.audioinfo.util.PositionInputStream;

/* loaded from: classes5.dex */
public class ID3v2TagHeader {
    private boolean compression;
    private int footerSize;
    private int headerSize;
    private int paddingSize;
    private int revision;
    private int totalTagSize;
    private boolean unsynchronization;
    private int version;

    public ID3v2TagHeader(InputStream r2) {
        this(new PositionInputStream(r2));
    }

    public ID3v2TagHeader(PositionInputStream r12) {
        boolean r0 = false;
        this.version = 0;
        this.revision = 0;
        this.headerSize = 0;
        this.totalTagSize = 0;
        this.paddingSize = 0;
        this.footerSize = 0;
        long r1 = r12.getPosition();
        ID3v2DataInput r3 = new ID3v2DataInput(r12);
        String r4 = new String(r3.readFully(3), "ISO-8859-1");
        if ("ID3".equals(r4) == false) goto L37;
        byte r42 = r3.readByte();
        this.version = r42;
        if (r42 == 2) goto L11;
        if (r42 == 3) goto L11;
        if (r42 == 4) goto L11;
        throw new ID3v2Exception("Unsupported ID3v2 version: " + this.version);
    L11:
        this.revision = r3.readByte();
        byte r43 = r3.readByte();
        this.totalTagSize = r3.readSyncsafeInt() + 10;
        int r8 = this.version;
        if (r8 != 2) goto L22;
        if ((r43 & ByteCompanionObject.MIN_VALUE) == 0) goto L16;
        boolean r32 = true;
    L17:
        this.unsynchronization = r32;
        if ((r43 & 64) == 0) goto L20;
        r0 = true;
    L20:
        this.compression = r0;
    L34:
        this.headerSize = (int) (r12.getPosition() - r1);
        return;
    L16:
        r32 = false;
        goto L17
    L22:
        if ((r43 & ByteCompanionObject.MIN_VALUE) == 0) goto L24;
        r0 = true;
    L24:
        this.unsynchronization = r0;
        if ((r43 & 64) == 0) goto L30;
        if (r8 != 3) goto L28;
        int r02 = r3.readInt();
        r3.readByte();
        r3.readByte();
        this.paddingSize = r3.readInt();
        r3.skipFully(r02 - 6);
        goto L30
    L28:
        r3.skipFully(r3.readSyncsafeInt() - 4);
    L30:
        if (this.version < 4) goto L34;
        if ((r43 & 16) == 0) goto L34;
        this.footerSize = 10;
        this.totalTagSize += 10;
        goto L34
    L37:
        throw new ID3v2Exception("Invalid ID3 identifier: ".concat(r4));
    }

    public int getFooterSize() {
        return this.footerSize;
    }

    public int getRevision() {
        return this.revision;
    }

    public int getVersion() {
        return this.version;
    }

    public ID3v2TagBody tagBody(InputStream r11) {
        if (this.compression == true) goto L24;
        if (this.version < 4) goto L7;
    L21:
        int r10 = this.headerSize;
        return new ID3v2TagBody(r11, r10, (this.totalTagSize - r10) - this.footerSize, this);
    L7:
        if (this.unsynchronization == false) goto L21;
        byte[] r112 = new ID3v2DataInput(r11).readFully(this.totalTagSize - this.headerSize);
        int r0 = r112.length;
        int r2 = 0;
        boolean r3 = false;
        int r7 = 0;
    L9:
        if (r2 >= r0) goto L20;
        byte r4 = r112[r2];
        if (r3 == false) goto L13;
        if (r4 != 0) goto L13;
    L15:
        if (r4 != (-1)) goto L17;
        r3 = true;
    L18:
        r2 = r2 + 1;
        goto L9
    L17:
        r3 = false;
    L13:
        r112[r7] = r4;
        r7 = r7 + 1;
        goto L15
    L20:
        return new ID3v2TagBody(new ByteArrayInputStream(r112, 0, r7), this.headerSize, r7, this);
    L24:
        throw new ID3v2Exception("Tag compression is not supported");
    }

    public String toString() {
        return String.format("%s[version=%s, totalTagSize=%d]", new Object[]{getClass().getSimpleName(), Integer.valueOf(this.version), Integer.valueOf(this.totalTagSize)});
    }
}
