package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes.dex */
public final class MetadataList extends Table {
    public MetadataList() {
    }

    public static MetadataList getRootAsMetadataList(ByteBuffer r1) {
        return getRootAsMetadataList(r1, new MetadataList());
    }

    public static MetadataList getRootAsMetadataList(ByteBuffer r2, MetadataList r3) {
        r2.order(ByteOrder.LITTLE_ENDIAN);
        return r3.__assign(r2.getInt(r2.position()) + r2.position(), r2);
    }

    public MetadataList __assign(int r1, ByteBuffer r2) {
        __init(r1, r2);
        return this;
    }

    public void __init(int r1, ByteBuffer r2) {
        __reset(r1, r2);
    }

    public MetadataItem list(MetadataItem r2, int r3) {
        int r0 = __offset(6);
        if (r0 != 0) goto L5;
        return null;
    L5:
        return r2.__assign(__indirect(__vector(r0) + (r3 * 4)), this.bb);
    }

    public int listLength() {
        int r0 = __offset(6);
        if (r0 != 0) goto L5;
        return 0;
    L5:
        return __vector_len(r0);
    }

    public int version() {
        int r0 = __offset(4);
        if (r0 != 0) goto L5;
        return 0;
    L5:
        return this.bb.getInt(r0 + this.bb_pos);
    }
}
