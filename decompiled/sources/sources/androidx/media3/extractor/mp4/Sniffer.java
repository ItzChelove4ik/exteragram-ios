package androidx.media3.extractor.mp4;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.SniffFailure;
import com.google.android.gms.cast.MediaStatus;

/* loaded from: classes4.dex */
public abstract class Sniffer {
    private static final int[] COMPATIBLE_BRANDS = null;

    static {
        COMPATIBLE_BRANDS = new int[]{1769172845, 1769172786, 1769172787, 1769172788, 1769172789, 1769172790, 1769172793, 1635148593, 1752589105, 1751479857, 1635135537, 1836069937, 1836069938, 862401121, 862401122, 862417462, 862417718, 862414134, 862414646, 1295275552, 1295270176, 1714714144, 1801741417, 1295275600, 1903435808, 1297305174, 1684175153, 1769172332, 1885955686};
    }

    private static boolean isCompatibleBrand(int r5, boolean r6) {
        if ((r5 >>> 8) != 3368816) goto L6;
        return true;
    L6:
        if (r5 != 1751476579) goto L9;
        if (r6 == false) goto L9;
        return true;
    L9:
        int[] r62 = COMPATIBLE_BRANDS;
        int r0 = r62.length;
        int r3 = 0;
    L10:
        if (r3 >= r0) goto L15;
        if (r62[r3] == r5) goto L13;
        r3 = r3 + 1;
        goto L10
    L13:
        return true;
    L15:
        return false;
    }

    public static SniffFailure sniffFragmented(ExtractorInput r2) {
        return sniffInternal(r2, true, false);
    }

    private static SniffFailure sniffInternal(ExtractorInput r26, boolean r27, boolean r28) {
        long r2 = r26.getLength();
        long r4 = -1;
        int r6 = (r2 > (-1) ? 1 : (r2 == (-1) ? 0 : -1));
        long r7 = MediaStatus.COMMAND_EDIT_TRACKS;
        if (r6 != 0) goto L5;
    L8:
        int r72 = (int) r7;
        ParsableByteArray r8 = new ParsableByteArray(64);
        int r9 = 0;
        int r10 = 0;
        boolean r11 = false;
    L9:
        if (r10 >= r72) goto L12;
        r8.reset(8);
        boolean r15 = true;
        if (r26.peekFully(r8.getData(), r9, 8, true) == false) goto L12;
        long r16 = r8.readUnsignedInt();
        int r14 = r8.readInt();
        if (r16 != 1) goto L16;
        long r18 = r4;
        r26.peekFully(r8.getData(), 8, 8);
        int r42 = 16;
        r8.setLimit(16);
        int r162 = r10;
        long r92 = r8.readLong();
        int r17 = r6;
    L22:
        long r5 = r42;
        if (r92 < r5) goto L25;
        int r43 = r162 + r42;
        SniffFailure r163 = null;
        if (r14 == 1836019574) goto L28;
        if (r14 == 1836019558) goto L73;
        if (r14 == 1836475768) goto L73;
        if (r14 != 1835295092) goto L42;
        r11 = true;
    L42:
        long r22 = r2;
        if (((r43 + r92) - r5) >= r72) goto L44;
        int r23 = (int) (r92 - r5);
        r10 = r43 + r23;
        if (r14 == 1718909296) goto L48;
        int r44 = 0;
        if (r23 == 0) goto L72;
        r26.advancePeekPosition(r23);
    L72:
        r9 = r44;
        r6 = r17;
        r4 = r18;
        r2 = r22;
        goto L9
    L48:
        if (r23 < 8) goto L50;
        r8.reset(r23);
        r44 = 0;
        r26.peekFully(r8.getData(), 0, r23);
        int r24 = r8.readInt();
        if (isCompatibleBrand(r24, r28) == false) goto L54;
        r11 = true;
    L54:
        r8.skipBytes(4);
        int r52 = r8.bytesLeft() / 4;
        if (r11 == true) goto L64;
        if (r52 <= 0) goto L64;
        int[] r12 = new int[r52];
        int r3 = 0;
    L58:
        if (r3 >= r52) goto L63;
        int r62 = r8.readInt();
        r12[r3] = r62;
        if (isCompatibleBrand(r62, r28) == true) goto L65;
        r3 = r3 + 1;
    L65:
        if (r15 == false) goto L67;
        r11 = r15;
        goto L72
    L67:
        return new UnsupportedBrandsSniffFailure(r24, r12);
    L63:
        r15 = r11;
    L64:
        r15 = r11;
        r12 = null;
        goto L65
    L50:
        return new AtomSizeTooSmallSniffFailure(r14, r23, 8);
    L44:
        int r93 = 0;
    L75:
        if (r11 == true) goto L79;
        return NoDeclaredBrandSniffFailure.INSTANCE;
    L79:
        if (r27 == r93) goto L85;
        if (r93 == 0) goto L84;
        return IncorrectFragmentationSniffFailure.FILE_FRAGMENTED;
    L84:
        return IncorrectFragmentationSniffFailure.FILE_NOT_FRAGMENTED;
    L85:
        return r163;
    L73:
        r93 = 1;
        goto L75
    L28:
        r72 = r72 + ((int) r92);
        if (r17 != 0) goto L31;
    L33:
        r10 = r43;
        r6 = r17;
        r4 = r18;
        r9 = 0;
        goto L9
    L31:
        if (r72 <= r2) goto L33;
        r72 = (int) r2;
        goto L33
    L25:
        return new AtomSizeTooSmallSniffFailure(r14, r92, r42);
    L16:
        r18 = r4;
        if (r16 != 0) goto L21;
        long r45 = r26.getLength();
        if (r45 == r18) goto L21;
        r16 = (r45 - r26.getPeekPosition()) + 8;
    L21:
        long r242 = r16;
        r162 = r10;
        r92 = r242;
        r17 = r6;
        r42 = 8;
    L12:
        r163 = null;
        r93 = r9;
        goto L75
    L5:
        if (r2 > MediaStatus.COMMAND_EDIT_TRACKS) goto L8;
        r7 = r2;
        goto L8
    }

    public static SniffFailure sniffUnfragmented(ExtractorInput r1, boolean r2) {
        return sniffInternal(r1, false, r2);
    }
}
