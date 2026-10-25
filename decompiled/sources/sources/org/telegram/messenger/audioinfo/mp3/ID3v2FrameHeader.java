package org.telegram.messenger.audioinfo.mp3;

import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes5.dex */
public class ID3v2FrameHeader {
    private int bodySize;
    private boolean compression;
    private int dataLengthIndicator;
    private boolean encryption;
    private String frameId;
    private int headerSize;
    private boolean unsynchronization;

    public ID3v2FrameHeader(ID3v2TagBody r14) {
        long r0 = r14.getPosition();
        ID3v2DataInput r2 = r14.getData();
        byte r7 = 2;
        if (r14.getTagHeader().getVersion() != 2) goto L5;
        this.frameId = new String(r2.readFully(3), "ISO-8859-1");
    L6:
        byte r4 = 8;
        if (r14.getTagHeader().getVersion() != 2) goto L10;
        this.bodySize = (((r2.readByte() & UByte.MAX_VALUE) << 16) | ((r2.readByte() & UByte.MAX_VALUE) << 8)) | (r2.readByte() & UByte.MAX_VALUE);
    L14:
        if (r14.getTagHeader().getVersion() <= 2) goto L50;
        r2.readByte();
        byte r3 = r2.readByte();
        byte r9 = 64;
        boolean r10 = false;
        if (r14.getTagHeader().getVersion() != 3) goto L18;
        r4 = ByteCompanionObject.MIN_VALUE;
        byte r8 = 32;
        r7 = 0;
        byte r12 = 0;
    L20:
        if ((r4 & r3) == 0) goto L22;
        boolean r42 = true;
    L23:
        this.compression = r42;
        if ((r3 & r7) == 0) goto L26;
        boolean r43 = true;
    L27:
        this.unsynchronization = r43;
        if ((r3 & r9) == 0) goto L30;
        r10 = true;
    L30:
        this.encryption = r10;
        if (r14.getTagHeader().getVersion() != 3) goto L42;
        if (this.compression == false) goto L36;
        this.dataLengthIndicator = r2.readInt();
        this.bodySize -= 4;
    L36:
        if (this.encryption == false) goto L39;
        r2.readByte();
        this.bodySize--;
    L39:
        if ((r3 & r8) == 0) goto L50;
        r2.readByte();
        this.bodySize--;
        goto L50
    L42:
        if ((r3 & r8) == 0) goto L45;
        r2.readByte();
        this.bodySize--;
    L45:
        if (this.encryption == false) goto L48;
        r2.readByte();
        this.bodySize--;
    L48:
        if ((r3 & r12) == 0) goto L50;
        this.dataLengthIndicator = r2.readSyncsafeInt();
        this.bodySize -= 4;
        goto L50
    L26:
        r43 = false;
        goto L27
    L22:
        r42 = false;
        goto L23
    L18:
        r8 = 64;
        r12 = 1;
        r9 = 4;
    L50:
        this.headerSize = (int) (r14.getPosition() - r0);
        return;
    L10:
        if (r14.getTagHeader().getVersion() != 3) goto L12;
        this.bodySize = r2.readInt();
        goto L14
    L12:
        this.bodySize = r2.readSyncsafeInt();
        goto L14
    L5:
        this.frameId = new String(r2.readFully(4), "ISO-8859-1");
        goto L6
    }

    public int getBodySize() {
        return this.bodySize;
    }

    public int getDataLengthIndicator() {
        return this.dataLengthIndicator;
    }

    public String getFrameId() {
        return this.frameId;
    }

    public int getHeaderSize() {
        return this.headerSize;
    }

    public boolean isCompression() {
        return this.compression;
    }

    public boolean isEncryption() {
        return this.encryption;
    }

    public boolean isPadding() {
        int r1 = 0;
    L4:
        if (r1 >= this.frameId.length()) goto L10;
        if (this.frameId.charAt(0) != 0) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return false;
    L10:
        if (this.bodySize != 0) goto L13;
        return true;
    L13:
        return false;
    }

    public boolean isUnsynchronization() {
        return this.unsynchronization;
    }

    public boolean isValid() {
        int r1 = 0;
    L4:
        if (r1 >= this.frameId.length()) goto L17;
        if (this.frameId.charAt(r1) < 'A') goto L10;
        if (this.frameId.charAt(r1) > 'Z') goto L10;
    L14:
        r1 = r1 + 1;
    L10:
        if (this.frameId.charAt(r1) < '0') goto L15;
        if (this.frameId.charAt(r1) <= '9') goto L14;
    L15:
        return false;
    L17:
        if (this.bodySize <= 0) goto L20;
        return true;
    L20:
        return false;
    }

    public String toString() {
        return String.format("%s[id=%s, bodysize=%d]", new Object[]{getClass().getSimpleName(), this.frameId, Integer.valueOf(this.bodySize)});
    }
}
