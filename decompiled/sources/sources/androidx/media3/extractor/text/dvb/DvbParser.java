package androidx.media3.extractor.text.dvb;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.SparseArray;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.SubtitleParser;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public final class DvbParser implements SubtitleParser {
    private static final byte[] defaultMap2To4 = null;
    private static final byte[] defaultMap2To8 = null;
    private static final byte[] defaultMap4To8 = null;
    private Bitmap bitmap;
    private final Canvas canvas;
    private final ClutDefinition defaultClutDefinition;
    private final DisplayDefinition defaultDisplayDefinition;
    private final Paint defaultPaint;
    private final Paint fillRegionPaint;
    private final SubtitleService subtitleService;

    public static final class ClutDefinition {
        public final int[] clutEntries2Bit;
        public final int[] clutEntries4Bit;
        public final int[] clutEntries8Bit;
        public final int id;

        public ClutDefinition(int r1, int[] r2, int[] r3, int[] r4) {
            this.id = r1;
            this.clutEntries2Bit = r2;
            this.clutEntries4Bit = r3;
            this.clutEntries8Bit = r4;
        }
    }

    public static final class DisplayDefinition {
        public final int height;
        public final int horizontalPositionMaximum;
        public final int horizontalPositionMinimum;
        public final int verticalPositionMaximum;
        public final int verticalPositionMinimum;
        public final int width;

        public DisplayDefinition(int r1, int r2, int r3, int r4, int r5, int r6) {
            this.width = r1;
            this.height = r2;
            this.horizontalPositionMinimum = r3;
            this.horizontalPositionMaximum = r4;
            this.verticalPositionMinimum = r5;
            this.verticalPositionMaximum = r6;
        }
    }

    public static final class ObjectData {
        public final byte[] bottomFieldData;
        public final int id;
        public final boolean nonModifyingColorFlag;
        public final byte[] topFieldData;

        public ObjectData(int r1, boolean r2, byte[] r3, byte[] r4) {
            this.id = r1;
            this.nonModifyingColorFlag = r2;
            this.topFieldData = r3;
            this.bottomFieldData = r4;
        }
    }

    public static final class PageComposition {
        public final SparseArray<PageRegion> regions;
        public final int state;
        public final int timeOutSecs;
        public final int version;

        public PageComposition(int r1, int r2, int r3, SparseArray<PageRegion> r4) {
            this.timeOutSecs = r1;
            this.version = r2;
            this.state = r3;
            this.regions = r4;
        }
    }

    public static final class PageRegion {
        public final int horizontalAddress;
        public final int verticalAddress;

        public PageRegion(int r1, int r2) {
            this.horizontalAddress = r1;
            this.verticalAddress = r2;
        }
    }

    public static final class RegionComposition {
        public final int clutId;
        public final int depth;
        public final boolean fillFlag;
        public final int height;
        public final int id;
        public final int levelOfCompatibility;
        public final int pixelCode2Bit;
        public final int pixelCode4Bit;
        public final int pixelCode8Bit;
        public final SparseArray<RegionObject> regionObjects;
        public final int width;

        public RegionComposition(int r1, boolean r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9, int r10, SparseArray<RegionObject> r11) {
            this.id = r1;
            this.fillFlag = r2;
            this.width = r3;
            this.height = r4;
            this.levelOfCompatibility = r5;
            this.depth = r6;
            this.clutId = r7;
            this.pixelCode8Bit = r8;
            this.pixelCode4Bit = r9;
            this.pixelCode2Bit = r10;
            this.regionObjects = r11;
        }

        public void mergeFrom(RegionComposition r5) {
            SparseArray<RegionObject> r52 = r5.regionObjects;
            int r0 = 0;
        L4:
            if (r0 >= r52.size()) goto L6;
            this.regionObjects.put(r52.keyAt(r0), r52.valueAt(r0));
            r0 = r0 + 1;
            goto L4
        }
    }

    public static final class RegionObject {
        public final int backgroundPixelCode;
        public final int foregroundPixelCode;
        public final int horizontalPosition;
        public final int provider;
        public final int type;
        public final int verticalPosition;

        public RegionObject(int r1, int r2, int r3, int r4, int r5, int r6) {
            this.type = r1;
            this.provider = r2;
            this.horizontalPosition = r3;
            this.verticalPosition = r4;
            this.foregroundPixelCode = r5;
            this.backgroundPixelCode = r6;
        }
    }

    public static final class SubtitleService {
        public final SparseArray<ClutDefinition> ancillaryCluts;
        public final SparseArray<ObjectData> ancillaryObjects;
        public final int ancillaryPageId;
        public final SparseArray<ClutDefinition> cluts;
        public DisplayDefinition displayDefinition;
        public final SparseArray<ObjectData> objects;
        public PageComposition pageComposition;
        public final SparseArray<RegionComposition> regions;
        public final int subtitlePageId;

        public SubtitleService(int r1, int r2) {
            this.subtitlePageId = r1;
            this.ancillaryPageId = r2;
            this.regions = new SparseArray();
            this.cluts = new SparseArray();
            this.objects = new SparseArray();
            this.ancillaryCluts = new SparseArray();
            this.ancillaryObjects = new SparseArray();
        }

        public void reset() {
            this.regions.clear();
            this.cluts.clear();
            this.objects.clear();
            this.ancillaryCluts.clear();
            this.ancillaryObjects.clear();
            this.displayDefinition = null;
            this.pageComposition = null;
        }
    }

    static {
        defaultMap2To4 = new byte[]{0, 7, 8, 15};
        defaultMap2To8 = new byte[]{0, 119, -120, -1};
        defaultMap4To8 = new byte[]{0, 17, 34, 51, 68, 85, 102, 119, -120, -103, -86, -69, -52, -35, -18, -1};
    }

    public DvbParser(List<byte[]> r11) {
        ParsableByteArray r0 = new ParsableByteArray(r11.get(0));
        int r112 = r0.readUnsignedShort();
        int r02 = r0.readUnsignedShort();
        Paint r2 = new Paint();
        this.defaultPaint = r2;
        r2.setStyle(Paint.Style.FILL_AND_STROKE);
        r2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        r2.setPathEffect(null);
        Paint r22 = new Paint();
        this.fillRegionPaint = r22;
        r22.setStyle(Paint.Style.FILL);
        r22.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
        r22.setPathEffect(null);
        this.canvas = new Canvas();
        this.defaultDisplayDefinition = new DisplayDefinition(719, 575, 0, 719, 0, 575);
        this.defaultClutDefinition = new ClutDefinition(0, generateDefault2BitClutEntries(), generateDefault4BitClutEntries(), generateDefault8BitClutEntries());
        this.subtitleService = new SubtitleService(r112, r02);
    }

    private static byte[] buildClutMapTable(int r3, int r4, ParsableBitArray r5) {
        byte[] r0 = new byte[r3];
        int r1 = 0;
    L3:
        if (r1 >= r3) goto L5;
        r0[r1] = (byte) r5.readBits(r4);
        r1 = r1 + 1;
        goto L3
    L5:
        return r0;
    }

    private static int[] generateDefault2BitClutEntries() {
        return new int[]{0, -1, -16777216, -8421505};
    }

    private static int[] generateDefault4BitClutEntries() {
        int[] r1 = new int[16];
        r1[0] = 0;
        int r3 = 1;
    L3:
        if (r3 >= 16) goto L33;
        if (r3 < 8) goto L7;
        int r6 = 127;
        if ((r3 & 1) == 0) goto L22;
        int r4 = 127;
    L24:
        if ((r3 & 2) == 0) goto L26;
        int r7 = 127;
    L28:
        if ((r3 & 4) != 0) goto L31;
        r6 = 0;
    L31:
        r1[r3] = getColor(255, r4, r7, r6);
    L32:
        r3 = r3 + 1;
        goto L3
    L26:
        r7 = 0;
        goto L28
    L22:
        r4 = 0;
        goto L24
    L7:
        if ((r3 & 1) == 0) goto L9;
        int r42 = 255;
    L11:
        if ((r3 & 2) == 0) goto L13;
        int r62 = 255;
    L15:
        if ((r3 & 4) == 0) goto L17;
        int r72 = 255;
    L18:
        r1[r3] = getColor(255, r42, r62, r72);
        goto L32
    L17:
        r72 = 0;
        goto L18
    L13:
        r62 = 0;
        goto L15
    L9:
        r42 = 0;
        goto L11
    L33:
        return r1;
    }

    private static int[] generateDefault8BitClutEntries() {
        int[] r1 = new int[256];
        r1[0] = 0;
        int r3 = 0;
    L3:
        if (r3 >= 256) goto L129;
        int r5 = 255;
        if (r3 < 8) goto L7;
        int r6 = r3 & 136;
        int r7 = 170;
        int r8 = 85;
        if (r6 == 0) goto L104;
        if (r6 == 8) goto L79;
        int r72 = 43;
        if (r6 == 128) goto L54;
        if (r6 != 136) goto L128;
        if ((r3 & 1) == 0) goto L31;
        int r4 = 43;
    L33:
        if ((r3 & 16) == 0) goto L35;
        int r62 = 85;
    L36:
        int r42 = r4 + r62;
        if ((r3 & 2) == 0) goto L39;
        int r63 = 43;
    L41:
        if ((r3 & 32) == 0) goto L43;
        int r9 = 85;
    L44:
        int r64 = r63 + r9;
        if ((r3 & 4) != 0) goto L49;
        r72 = 0;
    L49:
        if ((r3 & 64) != 0) goto L52;
        r8 = 0;
    L52:
        r1[r3] = getColor(255, r42, r64, r72 + r8);
        goto L128
    L43:
        r9 = 0;
        goto L44
    L39:
        r63 = 0;
        goto L41
    L35:
        r62 = 0;
        goto L36
    L31:
        r4 = 0;
    L128:
        r3 = r3 + 1;
        goto L3
    L54:
        if ((r3 & 1) == 0) goto L56;
        int r43 = 43;
    L57:
        int r44 = r43 + 127;
        if ((r3 & 16) == 0) goto L60;
        int r65 = 85;
    L61:
        int r45 = r44 + r65;
        if ((r3 & 2) == 0) goto L64;
        int r66 = 43;
    L65:
        int r67 = r66 + 127;
        if ((r3 & 32) == 0) goto L68;
        int r10 = 85;
    L69:
        int r68 = r67 + r10;
        if ((r3 & 4) != 0) goto L73;
        r72 = 0;
    L73:
        int r73 = r72 + 127;
        if ((r3 & 64) != 0) goto L77;
        r8 = 0;
    L77:
        r1[r3] = getColor(255, r45, r68, r73 + r8);
        goto L128
    L68:
        r10 = 0;
        goto L69
    L64:
        r66 = 0;
        goto L65
    L60:
        r65 = 0;
        goto L61
    L56:
        r43 = 0;
        goto L57
    L79:
        if ((r3 & 1) == 0) goto L81;
        int r46 = 85;
    L83:
        if ((r3 & 16) == 0) goto L85;
        int r52 = 170;
    L86:
        int r47 = r46 + r52;
        if ((r3 & 2) == 0) goto L89;
        int r53 = 85;
    L91:
        if ((r3 & 32) == 0) goto L93;
        int r69 = 170;
    L94:
        int r54 = r53 + r69;
        if ((r3 & 4) != 0) goto L99;
        r8 = 0;
    L99:
        if ((r3 & 64) != 0) goto L102;
        r7 = 0;
    L102:
        r1[r3] = getColor(127, r47, r54, r8 + r7);
        goto L128
    L93:
        r69 = 0;
        goto L94
    L89:
        r53 = 0;
        goto L91
    L85:
        r52 = 0;
        goto L86
    L81:
        r46 = 0;
        goto L83
    L104:
        if ((r3 & 1) == 0) goto L106;
        int r48 = 85;
    L108:
        if ((r3 & 16) == 0) goto L110;
        int r610 = 170;
    L111:
        int r49 = r48 + r610;
        if ((r3 & 2) == 0) goto L114;
        int r611 = 85;
    L116:
        if ((r3 & 32) == 0) goto L118;
        int r92 = 170;
    L119:
        int r612 = r611 + r92;
        if ((r3 & 4) != 0) goto L124;
        r8 = 0;
    L124:
        if ((r3 & 64) != 0) goto L127;
        r7 = 0;
    L127:
        r1[r3] = getColor(255, r49, r612, r8 + r7);
        goto L128
    L118:
        r92 = 0;
        goto L119
    L114:
        r611 = 0;
        goto L116
    L110:
        r610 = 0;
        goto L111
    L106:
        r48 = 0;
        goto L108
    L7:
        if ((r3 & 1) == 0) goto L9;
        int r410 = 255;
    L11:
        if ((r3 & 2) == 0) goto L13;
        int r613 = 255;
    L15:
        if ((r3 & 4) != 0) goto L18;
        r5 = 0;
    L18:
        r1[r3] = getColor(63, r410, r613, r5);
        goto L128
    L13:
        r613 = 0;
        goto L15
    L9:
        r410 = 0;
        goto L11
    L129:
        return r1;
    }

    private static int getColor(int r0, int r1, int r2, int r3) {
        return (((r0 << 24) | (r1 << 16)) | (r2 << 8)) | r3;
    }

    private static int paint2BitPixelCodeString(ParsableBitArray r9, int[] r10, byte[] r11, int r12, int r13, Paint r14, Canvas r15) {
        boolean r0 = false;
    L3:
        int r2 = r9.readBits(2);
        if (r2 == 0) goto L7;
        boolean r7 = r0;
        int r8 = 1;
    L25:
        if (r8 == 0) goto L30;
        if (r14 == null) goto L30;
        if (r11 == null) goto L29;
        r2 = r11[r2];
    L29:
        r14.setColor(r10[r2]);
        r15.drawRect(r12, r13, r12 + r8, 1 + r13, r14);
    L30:
        r12 = r12 + r8;
        if (r7 == true) goto L32;
        r0 = r7;
        goto L3
    L32:
        return r12;
    L7:
        if (r9.readBit() == false) goto L11;
        int r22 = r9.readBits(3) + 3;
        int r1 = r9.readBits(2);
    L9:
        r7 = r0;
        r8 = r22;
        r2 = r1;
        goto L25
    L11:
        if (r9.readBit() == false) goto L14;
        r7 = r0;
        r8 = 1;
    L13:
        r2 = 0;
        goto L25
    L14:
        int r23 = r9.readBits(2);
        if (r23 == 0) goto L24;
        if (r23 == 1) goto L23;
        if (r23 == 2) goto L22;
        if (r23 == 3) goto L21;
        r7 = r0;
    L20:
        r2 = 0;
        r8 = 0;
        goto L25
    L21:
        r22 = r9.readBits(8) + 29;
        r1 = r9.readBits(2);
        goto L9
    L22:
        r22 = r9.readBits(4) + 12;
        r1 = r9.readBits(2);
        goto L9
    L23:
        r7 = r0;
        r8 = 2;
        goto L13
    L24:
        r7 = true;
        goto L20
    }

    private static int paint4BitPixelCodeString(ParsableBitArray r9, int[] r10, byte[] r11, int r12, int r13, Paint r14, Canvas r15) {
        boolean r0 = false;
    L3:
        int r2 = r9.readBits(4);
        if (r2 == 0) goto L7;
        boolean r7 = r0;
        int r8 = 1;
    L28:
        if (r8 == 0) goto L33;
        if (r14 == null) goto L33;
        if (r11 == null) goto L32;
        r2 = r11[r2];
    L32:
        r14.setColor(r10[r2]);
        r15.drawRect(r12, r13, r12 + r8, 1 + r13, r14);
    L33:
        r12 = r12 + r8;
        if (r7 == true) goto L35;
        r0 = r7;
        goto L3
    L35:
        return r12;
    L7:
        if (r9.readBit() == true) goto L15;
        int r1 = r9.readBits(3);
        if (r1 == 0) goto L12;
        r7 = r0;
        r8 = r1 + 2;
    L11:
        r2 = 0;
        goto L28
    L12:
        r7 = true;
    L13:
        r2 = 0;
        r8 = 0;
        goto L28
    L15:
        if (r9.readBit() == true) goto L18;
        int r22 = r9.readBits(2) + 4;
        int r16 = r9.readBits(4);
    L17:
        r7 = r0;
        r8 = r22;
        r2 = r16;
        goto L28
    L18:
        int r23 = r9.readBits(2);
        if (r23 == 0) goto L27;
        if (r23 == 1) goto L26;
        if (r23 == 2) goto L25;
        if (r23 == 3) goto L24;
        r7 = r0;
        goto L13
    L24:
        r22 = r9.readBits(8) + 25;
        r16 = r9.readBits(4);
        goto L17
    L25:
        r22 = r9.readBits(4) + 9;
        r16 = r9.readBits(4);
        goto L17
    L26:
        r2 = 0;
        r8 = 2;
        r7 = r0;
        goto L28
    L27:
        r7 = r0;
        r8 = 1;
        goto L11
    }

    private static int paint8BitPixelCodeString(ParsableBitArray r9, int[] r10, byte[] r11, int r12, int r13, Paint r14, Canvas r15) {
        boolean r0 = false;
    L3:
        int r2 = r9.readBits(8);
        if (r2 == 0) goto L7;
        boolean r7 = r0;
        int r8 = 1;
    L13:
        if (r8 == 0) goto L18;
        if (r14 == null) goto L18;
        if (r11 == null) goto L17;
        r2 = r11[r2];
    L17:
        r14.setColor(r10[r2]);
        r15.drawRect(r12, r13, r12 + r8, 1 + r13, r14);
    L18:
        r12 = r12 + r8;
        if (r7 == true) goto L20;
        r0 = r7;
        goto L3
    L20:
        return r12;
    L7:
        if (r9.readBit() == true) goto L12;
        int r1 = r9.readBits(7);
        if (r1 == 0) goto L11;
        r7 = r0;
        r8 = r1;
        r2 = 0;
        goto L13
    L11:
        r7 = true;
        r2 = 0;
        r8 = 0;
        goto L13
    L12:
        r7 = r0;
        r8 = r9.readBits(7);
        r2 = r9.readBits(8);
        goto L13
    }

    private static void paintPixelDataSubBlock(byte[] r9, int[] r10, int r11, int r12, int r13, Paint r14, Canvas r15) {
        ParsableBitArray r0 = new ParsableBitArray(r9);
        byte[] r7 = null;
        byte[] r8 = null;
        int r3 = r12;
        int r4 = r13;
        byte[] r132 = null;
    L4:
        if (r0.bitsLeft() == 0) goto L40;
        int r2 = r0.readBits(8);
        if (r2 != 240) goto L8;
        int[] r1 = r10;
        Paint r5 = r14;
        Canvas r6 = r15;
        r4 = r4 + 2;
        r3 = r12;
    L39:
        r10 = r1;
        r14 = r5;
        r15 = r6;
        goto L4
    L8:
        switch(r2) {
            case 16: goto L25;
            case 17: goto L16;
            case 18: goto L15;
            default: goto L10;
        };
    L15:
        r1 = r10;
        r5 = r14;
        r6 = r15;
        r3 = paint8BitPixelCodeString(r0, r1, null, r3, r4, r5, r6);
        goto L39
    L16:
        r1 = r10;
        Paint r102 = r14;
        r6 = r15;
        if (r11 != 3) goto L23;
        if (r7 != null) goto L20;
        byte[] r142 = defaultMap4To8;
    L21:
        byte[] r22 = r142;
    L22:
        r5 = r102;
        r3 = paint4BitPixelCodeString(r0, r1, r22, r3, r4, r5, r6);
        r0.byteAlign();
        goto L39
    L20:
        r142 = r7;
        goto L21
    L23:
        r22 = null;
        goto L22
    L25:
        r1 = r10;
        Paint r103 = r14;
        r6 = r15;
        if (r11 != 3) goto L32;
        if (r132 != null) goto L29;
        byte[] r143 = defaultMap2To8;
    L30:
        r5 = r103;
        byte[] r23 = r143;
    L37:
        r3 = paint2BitPixelCodeString(r0, r1, r23, r3, r4, r5, r6);
        r0.byteAlign();
        goto L39
    L29:
        r143 = r132;
        goto L30
    L32:
        if (r11 != 2) goto L36;
        if (r8 != null) goto L35;
        r143 = defaultMap2To4;
        goto L30
    L35:
        r143 = r8;
        goto L30
    L36:
        r23 = null;
        r5 = r103;
        goto L37
    L10:
        switch(r2) {
            case 32: goto L14;
            case 33: goto L13;
            case 34: goto L12;
            default: goto L11;
        };
    L11:
        r1 = r10;
        r5 = r14;
        r6 = r15;
        goto L39
    L12:
        r7 = buildClutMapTable(16, 8, r0);
        goto L11
    L13:
        r132 = buildClutMapTable(4, 8, r0);
        goto L11
    L14:
        r8 = buildClutMapTable(4, 4, r0);
        goto L11
    }

    private static void paintPixelDataSubBlocks(ObjectData r7, ClutDefinition r8, int r9, int r10, int r11, Paint r12, Canvas r13) {
        if (r9 != 3) goto L7;
        int[] r82 = r8.clutEntries8Bit;
    L5:
        int[] r1 = r82;
        paintPixelDataSubBlock(r7.topFieldData, r1, r9, r10, r11, r12, r13);
        paintPixelDataSubBlock(r7.bottomFieldData, r1, r9, r10, r11 + 1, r12, r13);
        return;
    L7:
        if (r9 != 2) goto L9;
        r82 = r8.clutEntries4Bit;
        goto L5
    L9:
        r82 = r8.clutEntries2Bit;
        goto L5
    }

    private CuesWithTiming parse(ParsableBitArray r19) {
    L4:
        if (r19.bitsLeft() < 48) goto L8;
        if (r19.readBits(8) != 15) goto L8;
        parseSubtitlingSegment(r19, this.subtitleService);
    L8:
        SubtitleService r1 = this.subtitleService;
        PageComposition r2 = r1.pageComposition;
        if (r2 == null) goto L11;
        DisplayDefinition r12 = r1.displayDefinition;
        if (r12 != null) goto L16;
        r12 = this.defaultDisplayDefinition;
    L16:
        Bitmap r3 = this.bitmap;
        if (r3 != null) goto L19;
    L22:
        Bitmap r32 = Bitmap.createBitmap(r12.width + 1, r12.height + 1, Bitmap.Config.ARGB_8888);
        this.bitmap = r32;
        this.canvas.setBitmap(r32);
    L23:
        ArrayList r6 = new ArrayList();
        SparseArray<PageRegion> r22 = r2.regions;
        int r4 = 0;
    L25:
        if (r4 >= r22.size()) goto L58;
        this.canvas.save();
        PageRegion r5 = r22.valueAt(r4);
        RegionComposition r7 = this.subtitleService.regions.get(r22.keyAt(r4));
        int r8 = r5.horizontalAddress + r12.horizontalPositionMinimum;
        int r52 = r5.verticalAddress + r12.verticalPositionMinimum;
        this.canvas.clipRect(r8, r52, Math.min(r7.width + r8, r12.horizontalPositionMaximum), Math.min(r7.height + r52, r12.verticalPositionMaximum));
        ClutDefinition r9 = this.subtitleService.cluts.get(r7.clutId);
        if (r9 != null) goto L31;
        r9 = this.subtitleService.ancillaryCluts.get(r7.clutId);
        if (r9 != null) goto L31;
        r9 = this.defaultClutDefinition;
    L31:
        ClutDefinition r11 = r9;
        SparseArray<RegionObject> r92 = r7.regionObjects;
        int r10 = 0;
    L33:
        if (r10 >= r92.size()) goto L46;
        int r122 = r92.keyAt(r10);
        RegionObject r13 = r92.valueAt(r10);
        ObjectData r14 = this.subtitleService.objects.get(r122);
        if (r14 != null) goto L37;
        r14 = this.subtitleService.ancillaryObjects.get(r122);
    L37:
        if (r14 != null) goto L39;
        SparseArray<PageRegion> r17 = r22;
        int r33 = r10;
    L45:
        r10 = r33 + 1;
        r22 = r17;
        goto L33
    L39:
        if (r14.nonModifyingColorFlag == false) goto L42;
        Paint r123 = null;
    L43:
        r17 = r22;
        r33 = r10;
        paintPixelDataSubBlocks(r14, r11, r7.depth, r13.horizontalPosition + r8, r13.verticalPosition + r52, r123, this.canvas);
        goto L45
    L42:
        r123 = this.defaultPaint;
        goto L43
    L46:
        SparseArray<PageRegion> r172 = r22;
        if (r7.fillFlag == false) goto L56;
        int r23 = r7.depth;
        if (r23 != 3) goto L52;
        int r24 = r11.clutEntries8Bit[r7.pixelCode8Bit];
    L55:
        this.fillRegionPaint.setColor(r24);
        this.canvas.drawRect(r8, r52, r7.width + r8, r7.height + r52, this.fillRegionPaint);
        goto L56
    L52:
        if (r23 != 2) goto L54;
        r24 = r11.clutEntries4Bit[r7.pixelCode4Bit];
        goto L55
    L54:
        r24 = r11.clutEntries2Bit[r7.pixelCode2Bit];
    L56:
        r6.add(new Cue.Builder().setBitmap(Bitmap.createBitmap(this.bitmap, r8, r52, r7.width, r7.height)).setPosition(r8 / r12.width).setPositionAnchor(0).setLine(r52 / r12.height, 0).setLineAnchor(0).setSize(r7.width / r12.width).setBitmapHeight(r7.height / r12.height).build());
        this.canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        this.canvas.restore();
        r4 = r4 + 1;
        r22 = r172;
        goto L25
    L58:
        return new CuesWithTiming(r6, -9223372036854775807L, -9223372036854775807L);
    L19:
        if ((r12.width + 1) != r3.getWidth()) goto L22;
        if ((r12.height + 1) == this.bitmap.getHeight()) goto L23;
    L11:
        return new CuesWithTiming(ImmutableList.of(), -9223372036854775807L, -9223372036854775807L);
    }

    private static ClutDefinition parseClutDefinition(ParsableBitArray r24, int r25) {
        int r1 = 8;
        int r2 = r24.readBits(8);
        r24.skipBits(8);
        int r3 = 2;
        int r4 = r25 - 2;
        int[] r5 = generateDefault2BitClutEntries();
        int[] r6 = generateDefault4BitClutEntries();
        int[] r7 = generateDefault8BitClutEntries();
    L3:
        if (r4 <= 0) goto L20;
        int r8 = r24.readBits(r1);
        int r9 = r24.readBits(r1);
        if ((r9 & 128) == 0) goto L8;
        int[] r10 = r5;
    L12:
        if ((r9 & 1) == 0) goto L14;
        int r92 = r24.readBits(r1);
        int r11 = r24.readBits(r1);
        int r12 = r24.readBits(r1);
        int r13 = r24.readBits(r1);
        int r42 = r4 - 6;
    L16:
        if (r92 != 0) goto L18;
        r13 = 255;
        r11 = 0;
        r12 = 0;
    L18:
        double r32 = r92;
        double r14 = r11 - 128;
        double r142 = r12 - 128;
        r10[r8] = getColor((byte) (255 - (r13 & 255)), Util.constrainValue((int) (r32 + (1.402d * r14)), 0, 255), Util.constrainValue((int) ((r32 - (0.34414d * r142)) - (r14 * 0.71414d)), 0, 255), Util.constrainValue((int) (r32 + (r142 * 1.772d)), 0, 255));
        r4 = r42;
        r2 = r2;
        r1 = 8;
        r3 = 2;
        goto L3
    L14:
        int r112 = r24.readBits(6) << r3;
        int r132 = r24.readBits(4) << 4;
        r12 = r24.readBits(4) << 4;
        r42 = r4 - 4;
        r13 = r24.readBits(r3) << 6;
        r92 = r112;
        r11 = r132;
        goto L16
    L8:
        if ((r9 & 64) == 0) goto L10;
        r10 = r6;
        goto L12
    L10:
        r10 = r7;
        goto L12
    L20:
        return new ClutDefinition(r2, r5, r6, r7);
    }

    private static DisplayDefinition parseDisplayDefinition(ParsableBitArray r9) {
        r9.skipBits(4);
        boolean r0 = r9.readBit();
        r9.skipBits(3);
        int r3 = r9.readBits(16);
        int r4 = r9.readBits(16);
        if (r0 == false) goto L5;
        int r02 = r9.readBits(16);
        int r2 = r9.readBits(16);
        int r5 = r9.readBits(16);
        int r8 = r9.readBits(16);
        int r6 = r2;
        int r7 = r5;
        int r52 = r02;
    L7:
        return new DisplayDefinition(r3, r4, r52, r6, r7, r8);
    L5:
        r52 = 0;
        r7 = 0;
        r6 = r3;
        r8 = r4;
        goto L7
    }

    private static ObjectData parseObjectData(ParsableBitArray r6) {
        int r1 = r6.readBits(16);
        r6.skipBits(4);
        int r2 = r6.readBits(2);
        boolean r3 = r6.readBit();
        r6.skipBits(1);
        byte[] r5 = Util.EMPTY_BYTE_ARRAY;
        if (r2 != 1) goto L5;
        r6.skipBits(r6.readBits(8) * 16);
    L11:
        byte[] r22 = r5;
    L13:
        return new ObjectData(r1, r3, r5, r22);
    L5:
        if (r2 != 0) goto L11;
        int r23 = r6.readBits(16);
        int r0 = r6.readBits(16);
        if (r23 <= 0) goto L9;
        r5 = new byte[r23];
        r6.readBytes(r5, 0, r23);
    L9:
        if (r0 <= 0) goto L11;
        r22 = new byte[r0];
        r6.readBytes(r22, 0, r0);
        goto L13
    }

    private static PageComposition parsePageComposition(ParsableBitArray r9, int r10) {
        int r1 = r9.readBits(8);
        int r2 = r9.readBits(4);
        int r4 = r9.readBits(2);
        r9.skipBits(2);
        int r102 = r10 - 2;
        SparseArray r3 = new SparseArray();
    L3:
        if (r102 <= 0) goto L6;
        int r5 = r9.readBits(8);
        r9.skipBits(8);
        r102 = r102 - 6;
        r3.put(r5, new PageRegion(r9.readBits(16), r9.readBits(16)));
        goto L3
    L6:
        return new PageComposition(r1, r2, r4, r3);
    }

    private static RegionComposition parseRegionComposition(ParsableBitArray r25, int r26) {
        int r3 = r25.readBits(8);
        int r2 = 4;
        r25.skipBits(4);
        boolean r4 = r25.readBit();
        r25.skipBits(3);
        int r6 = 16;
        int r7 = r25.readBits(16);
        int r8 = r25.readBits(16);
        int r72 = r25.readBits(3);
        int r5 = r25.readBits(3);
        int r10 = 2;
        r25.skipBits(2);
        int r9 = r25.readBits(8);
        int r12 = r25.readBits(8);
        int r11 = r25.readBits(4);
        int r122 = r25.readBits(2);
        r25.skipBits(2);
        int r15 = r26 - 10;
        SparseArray r13 = new SparseArray();
    L3:
        if (r15 <= 0) goto L14;
        int r1 = r25.readBits(r6);
        int r62 = r25.readBits(r10);
        int r20 = r25.readBits(r10);
        int r21 = r25.readBits(12);
        r25.skipBits(r2);
        int r22 = r25.readBits(12);
        int r102 = r15 - 6;
        if (r62 == 1) goto L10;
        int r23 = 2;
        if (r62 != 2) goto L9;
    L8:
        char r103 = '\b';
        r15 = r15 - 8;
        int r232 = r25.readBits(8);
        int r24 = r25.readBits(8);
    L12:
        r13.put(r1, new RegionObject(r62, r20, r21, r22, r232, r24));
        r6 = 16;
        r10 = r23;
        r2 = 4;
        goto L3
    L9:
        r232 = 0;
        r24 = 0;
        r15 = r102;
        r103 = '\b';
        goto L12
    L10:
        r23 = 2;
        goto L8
    L14:
        return new RegionComposition(r3, r4, r7, r8, r72, r5, r9, r12, r11, r122, r13);
    }

    private static void parseSubtitlingSegment(ParsableBitArray r6, SubtitleService r7) {
        int r0 = r6.readBits(8);
        int r2 = r6.readBits(16);
        int r1 = r6.readBits(16);
        int r3 = r6.getBytePosition() + r1;
        if ((r1 * 8) <= r6.bitsLeft()) goto L6;
        Log.w("DvbParser", "Data field length exceeds limit");
        r6.skipBits(r6.bitsLeft());
        return;
    L6:
        switch(r0) {
            case 16: goto L33;
            case 17: goto L23;
            case 18: goto L18;
            case 19: goto L12;
            case 20: goto L9;
            default: goto L41;
        };
    L23:
        PageComposition r02 = r7.pageComposition;
        if (r2 != r7.subtitlePageId) goto L41;
        if (r02 == null) goto L41;
        RegionComposition r12 = parseRegionComposition(r6, r1);
        if (r02.state != 0) goto L31;
        RegionComposition r03 = r7.regions.get(r12.id);
        if (r03 == null) goto L31;
        r12.mergeFrom(r03);
    L31:
        r7.regions.put(r12.id, r12);
    L41:
        r6.skipBytes(r3 - r6.getBytePosition());
        return;
    L9:
        if (r2 != r7.subtitlePageId) goto L41;
        r7.displayDefinition = parseDisplayDefinition(r6);
        goto L41
    L12:
        if (r2 != r7.subtitlePageId) goto L15;
        ObjectData r04 = parseObjectData(r6);
        r7.objects.put(r04.id, r04);
        goto L41
    L15:
        if (r2 != r7.ancillaryPageId) goto L41;
        ObjectData r05 = parseObjectData(r6);
        r7.ancillaryObjects.put(r05.id, r05);
        goto L41
    L18:
        if (r2 != r7.subtitlePageId) goto L21;
        ClutDefinition r06 = parseClutDefinition(r6, r1);
        r7.cluts.put(r06.id, r06);
        goto L41
    L21:
        if (r2 != r7.ancillaryPageId) goto L41;
        ClutDefinition r07 = parseClutDefinition(r6, r1);
        r7.ancillaryCluts.put(r07.id, r07);
        goto L41
    L33:
        if (r2 != r7.subtitlePageId) goto L41;
        PageComposition r08 = r7.pageComposition;
        PageComposition r13 = parsePageComposition(r6, r1);
        if (r13.state == 0) goto L37;
        r7.pageComposition = r13;
        r7.regions.clear();
        r7.cluts.clear();
        r7.objects.clear();
        goto L41
    L37:
        if (r08 == null) goto L41;
        if (r08.version == r13.version) goto L41;
        r7.pageComposition = r13;
        goto L41
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public int getCueReplacementBehavior() {
        return 2;
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public void parse(byte[] r1, int r2, int r3, SubtitleParser.OutputOptions r4, Consumer<CuesWithTiming> r5) {
        ParsableBitArray r42 = new ParsableBitArray(r1, r3 + r2);
        r42.setPosition(r2);
        r5.accept(parse(r42));
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public void reset() {
        this.subtitleService.reset();
    }
}
