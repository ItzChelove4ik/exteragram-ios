package androidx.media3.extractor.text.pgs;

import android.graphics.Bitmap;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.Inflater;

/* loaded from: classes4.dex */
public final class PgsParser implements SubtitleParser {
    private final ParsableByteArray buffer;
    private final CueBuilder cueBuilder;
    private final ParsableByteArray inflatedBuffer;
    private Inflater inflater;

    public static final class CueBuilder {
        private final ParsableByteArray bitmapData;
        private int bitmapHeight;
        private int bitmapWidth;
        private int bitmapX;
        private int bitmapY;
        private final int[] colors;
        private boolean colorsSet;
        private int planeHeight;
        private int planeWidth;

        public CueBuilder() {
            this.bitmapData = new ParsableByteArray();
            this.colors = new int[256];
        }

        public static /* synthetic */ void access$000(CueBuilder r0, ParsableByteArray r1, int r2) {
            r0.parsePaletteSection(r1, r2);
        }

        public static /* synthetic */ void access$100(CueBuilder r0, ParsableByteArray r1, int r2) {
            r0.parseBitmapSection(r1, r2);
        }

        public static /* synthetic */ void access$200(CueBuilder r0, ParsableByteArray r1, int r2) {
            r0.parseIdentifierSection(r1, r2);
        }

        private void parseBitmapSection(ParsableByteArray r4, int r5) {
            if (r5 < 4) goto L25;
            r4.skipBytes(3);
            if ((r4.readUnsignedByte() & 128) == 0) goto L8;
            boolean r1 = true;
        L9:
            int r2 = r5 - 4;
            if (r1 == true) goto L12;
        L18:
            int r52 = this.bitmapData.getPosition();
            int r0 = this.bitmapData.limit();
            if (r52 >= r0) goto L23;
            if (r2 <= 0) goto L24;
            int r02 = Math.min(r2, r0 - r52);
            r4.readBytes(this.bitmapData.getData(), r52, r02);
            this.bitmapData.setPosition(r52 + r02);
            return;
        L24:
            return;
        L23:
            return;
        L12:
            if (r2 < 7) goto L26;
            int r12 = r4.readUnsignedInt24();
            if (r12 < 4) goto L27;
            this.bitmapWidth = r4.readUnsignedShort();
            this.bitmapHeight = r4.readUnsignedShort();
            this.bitmapData.reset(r12 - 4);
            r2 = r5 - 11;
            goto L18
        L27:
            return;
        L26:
            return;
        L8:
            r1 = false;
            goto L9
        }

        private void parseIdentifierSection(ParsableByteArray r2, int r3) {
            if (r3 >= 19) goto L5;
            return;
        L5:
            this.planeWidth = r2.readUnsignedShort();
            this.planeHeight = r2.readUnsignedShort();
            r2.skipBytes(11);
            this.bitmapX = r2.readUnsignedShort();
            this.bitmapY = r2.readUnsignedShort();
        }

        private void parsePaletteSection(ParsableByteArray r20, int r21) {
            if ((r21 % 5) == 2) goto L5;
            return;
        L5:
            r20.skipBytes(2);
            Arrays.fill(this.colors, 0);
            int r2 = r21 / 5;
            int r4 = 0;
        L6:
            if (r4 >= r2) goto L8;
            int r5 = r20.readUnsignedByte();
            int r6 = r20.readUnsignedByte();
            int r7 = r20.readUnsignedByte();
            int r8 = r20.readUnsignedByte();
            int r9 = r20.readUnsignedByte();
            double r10 = r6;
            double r62 = r7 - 128;
            int r12 = (int) ((1.402d * r62) + r10);
            double r13 = r8 - 128;
            int[] r82 = this.colors;
            int r92 = (r9 << 24) | (Util.constrainValue(r12, 0, 255) << 16);
            r82[r5] = ((Util.constrainValue((int) ((r10 - (0.34414d * r13)) - (r62 * 0.71414d)), 0, 255) << 8) | r92) | Util.constrainValue((int) (r10 + (r13 * 1.772d)), 0, 255);
            r4 = r4 + 1;
            goto L6
        L8:
            this.colorsSet = true;
        }

        public Cue build() {
            if (this.planeWidth != 0) goto L5;
            return null;
        L5:
            if (this.planeHeight != 0) goto L7;
            return null;
        L7:
            if (this.bitmapWidth != 0) goto L9;
            return null;
        L9:
            if (this.bitmapHeight != 0) goto L11;
            return null;
        L11:
            if (this.bitmapData.limit() != 0) goto L13;
            return null;
        L13:
            if (this.bitmapData.getPosition() == this.bitmapData.limit()) goto L15;
            return null;
        L15:
            if (this.colorsSet == false) goto L49;
            this.bitmapData.setPosition(0);
            int r0 = this.bitmapWidth * this.bitmapHeight;
            int[] r2 = new int[r0];
            int r3 = 0;
        L18:
            if (r3 >= r0) goto L35;
            int r4 = this.bitmapData.readUnsignedByte();
            if (r4 != 0) goto L21;
            int r42 = this.bitmapData.readUnsignedByte();
            if (r42 == 0) goto L18;
            if ((r42 & 64) != 0) goto L28;
            int r5 = r42 & 63;
        L29:
            int r43 = r42 & 128;
            int[] r6 = this.colors;
            if (r43 != 0) goto L32;
            int r44 = r6[0];
        L33:
            int r52 = r5 + r3;
            Arrays.fill(r2, r3, r52, r44);
        L22:
            r3 = r52;
            goto L18
        L32:
            r44 = r6[this.bitmapData.readUnsignedByte()];
            goto L33
        L28:
            r5 = ((r42 & 63) << 8) | this.bitmapData.readUnsignedByte();
            goto L29
        L21:
            r52 = r3 + 1;
            r2[r3] = this.colors[r4];
            goto L22
        L35:
            return new Cue.Builder().setBitmap(Bitmap.createBitmap(r2, this.bitmapWidth, this.bitmapHeight, Bitmap.Config.ARGB_8888)).setPosition(this.bitmapX / this.planeWidth).setPositionAnchor(0).setLine(this.bitmapY / this.planeHeight, 0).setLineAnchor(0).setSize(this.bitmapWidth / this.planeWidth).setBitmapHeight(this.bitmapHeight / this.planeHeight).build();
        L49:
            return null;
        }

        public void reset() {
            this.planeWidth = 0;
            this.planeHeight = 0;
            this.bitmapX = 0;
            this.bitmapY = 0;
            this.bitmapWidth = 0;
            this.bitmapHeight = 0;
            this.bitmapData.reset(0);
            this.colorsSet = false;
        }
    }

    public PgsParser() {
        this.buffer = new ParsableByteArray();
        this.inflatedBuffer = new ParsableByteArray();
        this.cueBuilder = new CueBuilder();
    }

    private static Cue readNextSection(ParsableByteArray r5, CueBuilder r6) {
        int r0 = r5.limit();
        int r1 = r5.readUnsignedByte();
        int r2 = r5.readUnsignedShort();
        int r3 = r5.getPosition() + r2;
        Cue r4 = null;
        if (r3 <= r0) goto L7;
        r5.setPosition(r0);
        return null;
    L7:
        if (r1 == 128) goto L13;
        switch(r1) {
            case 20: goto L12;
            case 21: goto L11;
            case 22: goto L10;
            default: goto L14;
        };
    L10:
        CueBuilder.access$200(r6, r5, r2);
        goto L14
    L11:
        CueBuilder.access$100(r6, r5, r2);
        goto L14
    L12:
        CueBuilder.access$000(r6, r5, r2);
    L14:
        r5.setPosition(r3);
        return r4;
    L13:
        r4 = r6.build();
        r6.reset();
        goto L14
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public int getCueReplacementBehavior() {
        return 2;
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public void parse(byte[] r7, int r8, int r9, SubtitleParser.OutputOptions r10, Consumer<CuesWithTiming> r11) {
        this.buffer.reset(r7, r9 + r8);
        this.buffer.setPosition(r8);
        if (this.inflater != null) goto L6;
        this.inflater = new Inflater();
    L6:
        if (Util.maybeInflate(this.buffer, this.inflatedBuffer, this.inflater) == false) goto L8;
        this.buffer.reset(this.inflatedBuffer.getData(), this.inflatedBuffer.limit());
    L8:
        this.cueBuilder.reset();
        ArrayList r1 = new ArrayList();
    L10:
        if (this.buffer.bytesLeft() < 3) goto L14;
        Cue r72 = readNextSection(this.buffer, this.cueBuilder);
        if (r72 == null) goto L10;
        r1.add(r72);
        goto L10
    L14:
        r11.accept(new CuesWithTiming(r1, -9223372036854775807L, -9223372036854775807L));
    }
}
