package androidx.emoji2.text;

import androidx.emoji2.text.flatbuffer.MetadataList;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.UShort;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
abstract class MetadataListReader {

    public static class ByteBufferReader implements OpenTypeReader {
        private final ByteBuffer mByteBuffer;

        public ByteBufferReader(ByteBuffer r1) {
            this.mByteBuffer = r1;
            r1.order(ByteOrder.BIG_ENDIAN);
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public long getPosition() {
            return this.mByteBuffer.position();
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public int readTag() {
            return this.mByteBuffer.getInt();
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public long readUnsignedInt() {
            return MetadataListReader.toUnsignedInt(this.mByteBuffer.getInt());
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public int readUnsignedShort() {
            return MetadataListReader.toUnsignedShort(this.mByteBuffer.getShort());
        }

        @Override // androidx.emoji2.text.MetadataListReader.OpenTypeReader
        public void skip(int r2) {
            ByteBuffer r1 = this.mByteBuffer;
            r1.position(r1.position() + r2);
        }
    }

    public static class OffsetInfo {
        private final long mLength;
        private final long mStartOffset;

        public OffsetInfo(long r1, long r3) {
            this.mStartOffset = r1;
            this.mLength = r3;
        }

        public long getStartOffset() {
            return this.mStartOffset;
        }
    }

    public interface OpenTypeReader {
        long getPosition();

        int readTag();

        long readUnsignedInt();

        int readUnsignedShort();

        void skip(int r1);
    }

    private static OffsetInfo findOffsetInfo(OpenTypeReader r13) {
        r13.skip(4);
        int r1 = r13.readUnsignedShort();
        if (r1 > 100) goto L27;
        r13.skip(6);
        int r2 = 0;
        int r5 = 0;
    L6:
        if (r5 >= r1) goto L11;
        int r8 = r13.readTag();
        r13.skip(4);
        long r9 = r13.readUnsignedInt();
        r13.skip(4);
        if (1835365473 == r8) goto L13;
        r5 = r5 + 1;
    L13:
        if (r9 == (-1)) goto L25;
        r13.skip((int) (r9 - r13.getPosition()));
        r13.skip(12);
        long r0 = r13.readUnsignedInt();
    L16:
        if (r2 >= r0) goto L25;
        int r52 = r13.readTag();
        long r6 = r13.readUnsignedInt();
        long r11 = r13.readUnsignedInt();
        if (1164798569 == r52) goto L24;
        if (1701669481 == r52) goto L24;
        r2 = r2 + 1;
    L24:
        return new OffsetInfo(r6 + r9, r11);
    L25:
        Model$$ExternalSyntheticBUOutline0.m("Cannot read metadata.");
        return null;
    L11:
        r9 = -1;
        goto L13
    L27:
        Model$$ExternalSyntheticBUOutline0.m("Cannot read metadata.");
        return null;
    }

    public static MetadataList read(ByteBuffer r2) {
        ByteBuffer r22 = r2.duplicate();
        r22.position((int) findOffsetInfo(new ByteBufferReader(r22)).getStartOffset());
        return MetadataList.getRootAsMetadataList(r22);
    }

    public static long toUnsignedInt(int r4) {
        return r4 & 4294967295L;
    }

    public static int toUnsignedShort(short r1) {
        return r1 & UShort.MAX_VALUE;
    }
}
