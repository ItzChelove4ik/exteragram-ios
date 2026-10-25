package androidx.media3.extractor.avi;

import androidx.media3.common.util.ParsableByteArray;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;

/* loaded from: classes4.dex */
final class ListChunk implements AviChunk {
    public final ImmutableList<AviChunk> children;
    private final int type;

    private ListChunk(int r1, ImmutableList<AviChunk> r2) {
        this.type = r1;
        this.children = r2;
    }

    private static AviChunk createBox(int r0, int r1, ParsableByteArray r2) {
        switch(r0) {
            case 1718776947: goto L12;
            case 1751742049: goto L10;
            case 1752331379: goto L8;
            case 1852994675: goto L6;
            default: goto L3;
        };
    L3:
        return null;
    L6:
        return StreamNameChunk.parseFrom(r2);
    L8:
        return AviStreamHeaderChunk.parseFrom(r2);
    L10:
        return AviMainHeaderChunk.parseFrom(r2);
    L12:
        return StreamFormatChunk.parseFrom(r1, r2);
    }

    public static ListChunk parseFrom(int r7, ParsableByteArray r8) {
        ImmutableList.Builder r0 = new ImmutableList.Builder();
        int r1 = r8.limit();
        int r2 = -2;
    L4:
        if (r8.bytesLeft() <= 8) goto L16;
        int r3 = r8.readLittleEndianInt();
        int r4 = r8.readLittleEndianInt();
        int r5 = r8.getPosition() + r4;
        r8.setLimit(r5);
        if (r3 != 1414744396) goto L8;
        AviChunk r32 = parseFrom(r8.readLittleEndianInt(), r8);
    L9:
        if (r32 == null) goto L14;
        if (r32.getType() != 1752331379) goto L13;
        r2 = ((AviStreamHeaderChunk) r32).getTrackType();
    L13:
        r0.add(r32);
    L14:
        r8.setPosition(r5);
        r8.setLimit(r1);
        goto L4
    L8:
        r32 = createBox(r3, r2, r8);
        goto L9
    L16:
        return new ListChunk(r7, r0.build());
    }

    public <T extends AviChunk> T getChild(Class<T> r3) {
        UnmodifiableIterator<AviChunk> r2 = this.children.iterator();
    L4:
        if (r2.hasNext() == false) goto L8;
        T r0 = (T) r2.next();
        if (r0.getClass() != r3) goto L4;
        return r0;
    L8:
        return null;
    }

    @Override // androidx.media3.extractor.avi.AviChunk
    public int getType() {
        return this.type;
    }
}
