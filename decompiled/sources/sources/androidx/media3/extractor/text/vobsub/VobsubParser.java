package androidx.media3.extractor.text.vobsub;

import android.graphics.Bitmap;
import android.graphics.Rect;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.SubtitleParser;
import com.google.common.collect.ImmutableList;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.zip.Inflater;

/* loaded from: classes4.dex */
public final class VobsubParser implements SubtitleParser {
    private final CueBuilder cueBuilder;
    private final ParsableByteArray inflatedScratch;
    private Inflater inflater;
    private final ParsableByteArray scratch;

    /* renamed from: androidx.media3.extractor.text.vobsub.VobsubParser$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class CueBuilder {
        private Rect boundingBox;
        private final int[] colors;
        private int dataOffset0;
        private int dataOffset1;
        private boolean hasColors;
        private boolean hasPlane;
        private int[] palette;
        private int planeHeight;
        private int planeWidth;

        public static final class Run {
            public int colorIndex;
            public int length;

            private Run() {
            }

            public /* synthetic */ Run(AnonymousClass1 r1) {
                this();
            }
        }

        public CueBuilder() {
            this.colors = new int[4];
            this.dataOffset0 = -1;
            this.dataOffset1 = -1;
        }

        private static int getColor(int[] r1, int r2) {
            if (r2 < 0) goto L8;
            if (r2 >= r1.length) goto L8;
            return r1[r2];
        L8:
            return r1[0];
        }

        private static int parseColor(String r1) {
            return Integer.parseInt(r1, 16);
        L5:
            return 0;
        }

        private void parseControl(int[] r2, ParsableByteArray r3, int r4) {
        L3:
            if (r3.getPosition() >= r4) goto L20;
            if (r3.bytesLeft() <= 0) goto L38;
            switch(r3.readUnsignedByte()) {
                case 0: goto L3;
                case 1: goto L3;
                case 2: goto L3;
                case 3: goto L19;
                case 4: goto L16;
                case 5: goto L13;
                case 6: goto L10;
                default: goto L39;
            };
        L10:
            if (parseControlOffsets(r3) == true) goto L3;
            return;
        L13:
            if (parseControlArea(r3) == true) goto L3;
            return;
        L16:
            if (parseControlAlpha(r3) == true) goto L3;
            return;
        L19:
            if (parseControlColors(r2, r3) == true) goto L3;
            return;
        L39:
            return;
        L38:
            return;
        }

        private boolean parseControlAlpha(ParsableByteArray r8) {
            if (r8.bytesLeft() >= 2) goto L5;
        L9:
            return false;
        L5:
            if (this.hasColors == false) goto L9;
            int r0 = r8.readUnsignedByte();
            int r82 = r8.readUnsignedByte();
            int[] r3 = this.colors;
            r3[3] = setAlpha(r3[3], r0 >> 4);
            int[] r32 = this.colors;
            r32[2] = setAlpha(r32[2], r0 & 15);
            int[] r02 = this.colors;
            r02[1] = setAlpha(r02[1], r82 >> 4);
            int[] r7 = this.colors;
            r7[0] = setAlpha(r7[0], r82 & 15);
            return true;
        }

        private boolean parseControlArea(ParsableByteArray r6) {
            if (r6.bytesLeft() >= 6) goto L6;
            return false;
        L6:
            int r0 = r6.readUnsignedByte();
            int r1 = r6.readUnsignedByte();
            int r02 = (r0 << 4) | (r1 >> 4);
            int r12 = (r1 & 15) << 8;
            int r13 = r12 | r6.readUnsignedByte();
            int r2 = r6.readUnsignedByte();
            int r3 = r6.readUnsignedByte();
            this.boundingBox = new Rect(r02, (r2 << 4) | (r3 >> 4), r13 + 1, (r6.readUnsignedByte() | ((r3 & 15) << 8)) + 1);
            return true;
        }

        private boolean parseControlColors(int[] r7, ParsableByteArray r8) {
            if (r8.bytesLeft() >= 2) goto L5;
            return false;
        L5:
            int r0 = r8.readUnsignedByte();
            int r82 = r8.readUnsignedByte();
            this.colors[3] = getColor(r7, r0 >> 4);
            this.colors[2] = getColor(r7, r0 & 15);
            this.colors[1] = getColor(r7, r82 >> 4);
            this.colors[0] = getColor(r7, r82 & 15);
            this.hasColors = true;
            return true;
        }

        private boolean parseControlOffsets(ParsableByteArray r3) {
            if (r3.bytesLeft() >= 4) goto L6;
            return false;
        L6:
            this.dataOffset0 = r3.readUnsignedShort();
            this.dataOffset1 = r3.readUnsignedShort();
            return true;
        }

        private void parseRleData(ParsableBitArray r10, boolean r11, Rect r12, int[] r13) {
            int r0 = r12.width();
            int r122 = r12.height();
            int r112 = !r11 ? 1 : 0;
            int r1 = r112 * r0;
            Run r2 = new Run(null);
        L3:
            int r4 = 0;
        L4:
            parseRun(r10, r0, r2);
            int r5 = Math.min(r2.length, r0 - r4);
            if (r5 <= 0) goto L7;
            int r6 = r1 + r5;
            Arrays.fill(r13, r1, r6, this.colors[r2.colorIndex]);
            r4 = r4 + r5;
            r1 = r6;
        L7:
            if (r4 < r0) goto L4;
            r112 = r112 + 2;
            if (r112 >= r122) goto L10;
            r1 = r112 * r0;
            r10.byteAlign();
            goto L3
        }

        private static void parseRun(ParsableBitArray r5, int r6, Run r7) {
            int r1 = 1;
            int r2 = 0;
        L4:
            if (r2 >= r1) goto L12;
            if (r1 > 64) goto L12;
            if (r5.bitsLeft() < 4) goto L9;
            r2 = (r2 << 4) | r5.readBits(4);
            r1 = r1 << 2;
            goto L4
        L9:
            r7.colorIndex = -1;
            r7.length = 0;
            return;
        L12:
            r7.colorIndex = r2 & 3;
            if (r2 < 4) goto L16;
            r6 = r2 >> 2;
        L16:
            r7.length = r6;
        }

        private static int setAlpha(int r1, int r2) {
            return (r1 & 16777215) | ((r2 * 17) << 24);
        }

        public Cue build(ParsableByteArray r6) {
            if (this.palette != null) goto L5;
            return null;
        L5:
            if (this.hasPlane == true) goto L7;
            return null;
        L7:
            if (this.hasColors == false) goto L24;
            Rect r0 = this.boundingBox;
            if (r0 != null) goto L11;
            return null;
        L11:
            if (this.dataOffset0 != (-1)) goto L13;
            return null;
        L13:
            if (this.dataOffset1 != (-1)) goto L15;
            return null;
        L15:
            if (r0.width() >= 2) goto L17;
            return null;
        L17:
            if (this.boundingBox.height() < 2) goto L29;
            Rect r02 = this.boundingBox;
            int[] r1 = new int[r02.width() * r02.height()];
            ParsableBitArray r2 = new ParsableBitArray();
            r6.setPosition(this.dataOffset0);
            r2.reset(r6);
            parseRleData(r2, true, r02, r1);
            r6.setPosition(this.dataOffset1);
            r2.reset(r6);
            parseRleData(r2, false, r02, r1);
            return new Cue.Builder().setBitmap(Bitmap.createBitmap(r1, r02.width(), r02.height(), Bitmap.Config.ARGB_8888)).setPosition(r02.left / this.planeWidth).setPositionAnchor(0).setLine(r02.top / this.planeHeight, 0).setLineAnchor(0).setSize(r02.width() / this.planeWidth).setBitmapHeight(r02.height() / this.planeHeight).build();
        L29:
            return null;
        L24:
            return null;
        }

        public void parseIdx(String r8) {
            String[] r82 = Util.split(r8.trim(), "\\r?\\n");
            int r0 = r82.length;
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L19;
            String r3 = r82[r2];
            if (r3.startsWith("palette: ") == false) goto L11;
            String[] r32 = Util.split(r3.substring(9), ",");
            this.palette = new int[r32.length];
            int r4 = 0;
        L8:
            if (r4 >= r32.length) goto L18;
            this.palette[r4] = parseColor(r32[r4].trim());
            r4 = r4 + 1;
        L18:
            r2 = r2 + 1;
            goto L3
        L11:
            if (r3.startsWith("size: ") == false) goto L18;
            String[] r33 = Util.split(r3.substring(6).trim(), "x");
            if (r33.length != 2) goto L18;
            this.planeWidth = Integer.parseInt(r33[0]);     // Catch: RuntimeException -> L16
            this.planeHeight = Integer.parseInt(r33[1]);     // Catch: RuntimeException -> L16
            this.hasPlane = true;     // Catch: RuntimeException -> L16
        L16:
            e = move-exception;
            Log.w("VobsubParser", "Parsing IDX failed", e);
            goto L18
        }

        public void parseSpu(ParsableByteArray r3) {
            int[] r0 = this.palette;
            if (r0 != null) goto L5;
            return;
        L5:
            if (this.hasPlane == false) goto L10;
            r3.skipBytes(r3.readUnsignedShort() - 2);
            parseControl(r0, r3, r3.readUnsignedShort());
            return;
        }

        public void reset() {
            this.hasColors = false;
            this.boundingBox = null;
            this.dataOffset0 = -1;
            this.dataOffset1 = -1;
        }
    }

    public VobsubParser(List<byte[]> r3) {
        this.scratch = new ParsableByteArray();
        this.inflatedScratch = new ParsableByteArray();
        CueBuilder r0 = new CueBuilder();
        this.cueBuilder = r0;
        r0.parseIdx(new String(r3.get(0), StandardCharsets.UTF_8));
    }

    private Cue parse() {
        if (this.inflater != null) goto L6;
        this.inflater = new Inflater();
    L6:
        if (Util.maybeInflate(this.scratch, this.inflatedScratch, this.inflater) == false) goto L8;
        this.scratch.reset(this.inflatedScratch.getData(), this.inflatedScratch.limit());
    L8:
        this.cueBuilder.reset();
        int r0 = this.scratch.bytesLeft();
        if (r0 >= 2) goto L11;
        return null;
    L11:
        if (this.scratch.readUnsignedShort() != r0) goto L17;
        this.cueBuilder.parseSpu(this.scratch);
        return this.cueBuilder.build(this.scratch);
    L17:
        return null;
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public int getCueReplacementBehavior() {
        return 2;
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public void parse(byte[] r7, int r8, int r9, SubtitleParser.OutputOptions r10, Consumer<CuesWithTiming> r11) {
        this.scratch.reset(r7, r9 + r8);
        this.scratch.setPosition(r8);
        Cue r6 = parse();
        if (r6 == null) goto L6;
        ImmutableList r62 = ImmutableList.of(r6);
    L7:
        r11.accept(new CuesWithTiming(r62, -9223372036854775807L, 5000000));
        return;
    L6:
        r62 = ImmutableList.of();
        goto L7
    }
}
