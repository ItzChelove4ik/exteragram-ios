package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class MetadataItem extends Table {
    public MetadataItem() {
    }

    public MetadataItem __assign(int r1, ByteBuffer r2) {
        __init(r1, r2);
        return this;
    }

    public void __init(int r1, ByteBuffer r2) {
        __reset(r1, r2);
    }

    public int codepoints(int r3) {
        int r0 = __offset(16);
        if (r0 != 0) goto L5;
        return 0;
    L5:
        return this.bb.getInt(__vector(r0) + (r3 * 4));
    }

    public int codepointsLength() {
        int r0 = __offset(16);
        if (r0 != 0) goto L5;
        return 0;
    L5:
        return __vector_len(r0);
    }

    public boolean emojiStyle() {
        int r0 = __offset(6);
        if (r0 != 0) goto L5;
    L8:
        return false;
    L5:
        if (this.bb.get(r0 + this.bb_pos) == 0) goto L8;
        return true;
    }

    public short height() {
        int r0 = __offset(14);
        if (r0 != 0) goto L5;
        return 0;
    L5:
        return this.bb.getShort(r0 + this.bb_pos);
    }

    public int id() {
        int r0 = __offset(4);
        if (r0 != 0) goto L5;
        return 0;
    L5:
        return this.bb.getInt(r0 + this.bb_pos);
    }

    public short sdkAdded() {
        int r0 = __offset(8);
        if (r0 != 0) goto L5;
        return 0;
    L5:
        return this.bb.getShort(r0 + this.bb_pos);
    }

    public short width() {
        int r0 = __offset(12);
        if (r0 != 0) goto L5;
        return 0;
    L5:
        return this.bb.getShort(r0 + this.bb_pos);
    }
}
