package androidx.media3.container;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableBitArray;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes3.dex */
public abstract class ObuParser {

    /* renamed from: androidx.media3.container.ObuParser$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class FrameHeader {
        private final boolean isDependedOn;

        private FrameHeader(SequenceHeader r8, Obu r9) {
            int r0 = r9.type;
            boolean r2 = false;
            if (r0 == 6) goto L7;
            if (r0 == 3) goto L7;
            boolean r02 = false;
        L8:
            Assertions.checkArgument(r02);
            byte[] r03 = new byte[Math.min(4, r9.payload.remaining())];
            r9.payload.asReadOnlyBuffer().get(r03);
            ParsableBitArray r92 = new ParsableBitArray(r03);
            ObuParser.access$200(r8.reducedStillPictureHeader);
            if (r92.readBit() == false) goto L12;
            this.isDependedOn = false;
            return;
        L12:
            int r1 = r92.readBits(2);
            boolean r5 = r92.readBit();
            ObuParser.access$200(r8.decoderModelInfoPresentFlag);
            if (r5 == true) goto L16;
            this.isDependedOn = true;
            return;
        L16:
            if (r1 == 3) goto L20;
            if (r1 == 0) goto L20;
            boolean r52 = r92.readBit();
        L21:
            r92.skipBit();
            ObuParser.access$200(!r8.seqForceScreenContentTools);
            if (r92.readBit() == false) goto L24;
            ObuParser.access$200(!r8.seqForceIntegerMv);
            r92.skipBit();
        L24:
            ObuParser.access$200(r8.frameIdNumbersPresentFlag);
            if (r1 == 3) goto L27;
            r92.skipBit();
        L27:
            r92.skipBits(r8.orderHintBits);
            if (r1 == 2) goto L32;
            if (r1 == 0) goto L32;
            if (r52 == true) goto L32;
            r92.skipBits(3);
        L32:
            if (r1 == 3) goto L36;
            if (r1 == 0) goto L36;
            int r82 = r92.readBits(8);
        L37:
            if (r82 == 0) goto L39;
            r2 = true;
        L39:
            this.isDependedOn = r2;
            return;
        L36:
            r82 = 255;
        L20:
            r52 = true;
        L7:
            r02 = true;
            goto L8
        }

        public static FrameHeader parse(SequenceHeader r1, Obu r2) {
            return new FrameHeader(r1, r2);
        L4:
            return null;
        }

        public boolean isDependedOn() {
            return this.isDependedOn;
        }
    }

    public static class NotYetImplementedException extends Exception {
        private NotYetImplementedException() {
        }

        public /* synthetic */ NotYetImplementedException(AnonymousClass1 r1) {
            this();
        }
    }

    public static final class Obu {
        public final ByteBuffer payload;
        public final int type;

        private Obu(int r1, ByteBuffer r2) {
            this.type = r1;
            this.payload = r2;
        }

        public /* synthetic */ Obu(int r1, ByteBuffer r2, AnonymousClass1 r3) {
            this(r1, r2);
        }
    }

    public static final class SequenceHeader {
        public final int chromaSamplePosition;
        public final byte colorPrimaries;
        public final boolean decoderModelInfoPresentFlag;
        public final boolean frameIdNumbersPresentFlag;
        public final boolean highBitdepth;
        public final int initialDisplayDelayMinus1;
        public final boolean initialDisplayDelayPresentFlag;
        public final byte matrixCoefficients;
        public final boolean monochrome;
        public final int orderHintBits;
        public final boolean reducedStillPictureHeader;
        public final boolean seqForceIntegerMv;
        public final boolean seqForceScreenContentTools;
        public final int seqLevelIdx0;
        public final int seqProfile;
        public final int seqTier0;
        public final boolean subsamplingX;
        public final boolean subsamplingY;
        public final byte transferCharacteristics;
        public final boolean twelveBit;

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r8v4, types: [int] */
        /* JADX WARN: Type inference failed for: r8v5 */
        /* JADX WARN: Type inference failed for: r8v6 */
        private SequenceHeader(Obu r13) {
            if (r13.type != 1) goto L5;
            boolean r0 = true;
        L6:
            Assertions.checkArgument(r0);
            byte[] r02 = new byte[r13.payload.remaining()];
            r13.payload.asReadOnlyBuffer().get(r02);
            ParsableBitArray r132 = new ParsableBitArray(r02);
            this.seqProfile = r132.readBits(3);
            r132.skipBit();
            boolean r3 = r132.readBit();
            this.reducedStillPictureHeader = r3;
            if (r3 == false) goto L10;
            int r32 = r132.readBits(5);
            this.decoderModelInfoPresentFlag = false;
            this.initialDisplayDelayPresentFlag = false;
            ?? r8 = 0;
            int r9 = 0;
        L37:
            int r4 = r132.readBits(4);
            int r6 = r132.readBits(4);
            r132.skipBits(r4 + 1);
            r132.skipBits(r6 + 1);
            if (this.reducedStillPictureHeader == true) goto L40;
            this.frameIdNumbersPresentFlag = r132.readBit();
        L42:
            if (this.frameIdNumbersPresentFlag == false) goto L44;
            r132.skipBits(4);
            r132.skipBits(3);
        L44:
            r132.skipBits(3);
            if (this.reducedStillPictureHeader == false) goto L47;
            this.seqForceIntegerMv = true;
            this.seqForceScreenContentTools = true;
            this.orderHintBits = 0;
        L64:
            this.seqLevelIdx0 = r32;
            this.seqTier0 = r8;
            this.initialDisplayDelayMinus1 = r9;
            r132.skipBits(3);
            boolean r03 = r132.readBit();
            this.highBitdepth = r03;
            if (this.seqProfile != 2) goto L68;
            if (r03 == false) goto L68;
            this.twelveBit = r132.readBit();
        L70:
            if (this.seqProfile == 1) goto L72;
            this.monochrome = r132.readBit();
        L74:
            if (r132.readBit() == false) goto L76;
            this.colorPrimaries = (byte) r132.readBits(8);
            this.transferCharacteristics = (byte) r132.readBits(8);
            this.matrixCoefficients = (byte) r132.readBits(8);
        L78:
            if (this.monochrome == false) goto L81;
            r132.skipBit();
            this.subsamplingX = false;
            this.subsamplingY = false;
            this.chromaSamplePosition = 0;
        L105:
            r132.skipBit();
            return;
        L81:
            if (this.colorPrimaries == 1) goto L83;
        L87:
            r132.skipBit();
            int r04 = this.seqProfile;
            if (r04 != 0) goto L90;
            this.subsamplingX = true;
            this.subsamplingY = true;
        L100:
            if (this.subsamplingX == true) goto L102;
        L104:
            this.chromaSamplePosition = 0;
            goto L105
        L102:
            if (this.subsamplingY == false) goto L104;
            this.chromaSamplePosition = r132.readBits(2);
            goto L105
        L90:
            if (r04 != 1) goto L93;
            this.subsamplingX = false;
            this.subsamplingY = false;
            goto L100
        L93:
            if (this.twelveBit == false) goto L98;
            boolean r05 = r132.readBit();
            this.subsamplingX = r05;
            if (r05 == false) goto L97;
            this.subsamplingY = r132.readBit();
            goto L100
        L97:
            this.subsamplingY = false;
            goto L100
        L98:
            this.subsamplingX = true;
            this.subsamplingY = false;
            goto L100
        L83:
            if (this.transferCharacteristics != 13) goto L87;
            if (this.matrixCoefficients != 0) goto L87;
            this.subsamplingX = false;
            this.subsamplingY = false;
            this.chromaSamplePosition = 0;
            goto L105
        L76:
            this.colorPrimaries = 0;
            this.transferCharacteristics = 0;
            this.matrixCoefficients = 0;
            goto L78
        L72:
            this.monochrome = false;
        L68:
            this.twelveBit = false;
            goto L70
        L47:
            r132.skipBits(4);
            boolean r42 = r132.readBit();
            if (r42 == false) goto L51;
            r132.skipBits(2);
        L51:
            if (r132.readBit() == false) goto L53;
            this.seqForceScreenContentTools = true;
        L55:
            if (this.seqForceScreenContentTools == true) goto L57;
            this.seqForceIntegerMv = true;
        L61:
            if (r42 == false) goto L63;
            this.orderHintBits = r132.readBits(3) + 1;
            goto L64
        L63:
            this.orderHintBits = 0;
            goto L64
        L57:
            if (r132.readBit() == false) goto L59;
            this.seqForceIntegerMv = true;
            goto L61
        L59:
            this.seqForceIntegerMv = r132.readBit();
            goto L61
        L53:
            this.seqForceScreenContentTools = r132.readBit();
            goto L55
        L40:
            this.frameIdNumbersPresentFlag = false;
            goto L42
        L10:
            if (r132.readBit() == false) goto L14;
            skipTimingInfo(r132);
            boolean r33 = r132.readBit();
            this.decoderModelInfoPresentFlag = r33;
            if (r33 == false) goto L15;
            r132.skipBits(47);
        L15:
            this.initialDisplayDelayPresentFlag = r132.readBit();
            int r34 = r132.readBits(5);
            int r62 = 0;
            int r7 = 0;
            boolean r82 = false;
            r9 = 0;
        L16:
            if (r7 > r34) goto L36;
            r132.skipBits(12);
            if (r7 != 0) goto L22;
            r62 = r132.readBits(5);
            boolean r83 = r82;
            if (r62 <= 7) goto L26;
            r83 = r132.readBit();
        L26:
            if (this.decoderModelInfoPresentFlag == false) goto L29;
            r132.skipBit();
        L29:
            if (this.initialDisplayDelayPresentFlag == false) goto L35;
            if (r132.readBit() == false) goto L35;
            if (r7 != 0) goto L34;
            r9 = r132.readBits(4);
            goto L35
        L34:
            r132.skipBits(4);
        L35:
            r7 = r7 + 1;
            r82 = r83;
            goto L16
        L22:
            r83 = r82;
            if (r132.readBits(5) <= 7) goto L26;
            r132.skipBit();
            r83 = r82;
            goto L26
        L36:
            r32 = r62;
            r8 = r82;
            goto L37
        L14:
            this.decoderModelInfoPresentFlag = false;
            goto L15
        L5:
            r0 = false;
            goto L6
        }

        public static SequenceHeader parse(Obu r1) {
            return new SequenceHeader(r1);
        L4:
            return null;
        }

        private static void skipTimingInfo(ParsableBitArray r1) {
            r1.skipBits(64);
            if (r1.readBit() == false) goto L6;
            ObuParser.access$100(r1);
            return;
        }
    }

    public static /* synthetic */ void access$100(ParsableBitArray r0) {
        skipUvlc(r0);
    }

    public static /* synthetic */ void access$200(boolean r0) {
        throwWhenFeatureRequired(r0);
    }

    private static int leb128(ByteBuffer r5) {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r0 >= 8) goto L9;
        byte r2 = r5.get();
        r1 = r1 | ((r2 & ByteCompanionObject.MAX_VALUE) << (r0 * 7));
        if ((r2 & ByteCompanionObject.MIN_VALUE) == 0) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r1;
    L9:
        return r1;
    }

    private static void skipUvlc(ParsableBitArray r2) {
        int r0 = 0;
    L4:
        if (r2.readBit() == true) goto L6;
        r0 = r0 + 1;
        goto L4
    L6:
        if (r0 >= 32) goto L11;
        r2.skipBits(r0);
        return;
    }

    public static List<Obu> split(ByteBuffer r6) {
        ByteBuffer r62 = r6.asReadOnlyBuffer();
        ArrayList r0 = new ArrayList();
    L4:
        if (r62.hasRemaining() == false) goto L13;
        byte r1 = r62.get();
        int r2 = (r1 >> 3) & 15;
        if (((r1 >> 2) & 1) == 0) goto L9;
        r62.get();
    L9:
        if (((r1 >> 1) & 1) == 0) goto L11;
        int r12 = leb128(r62);
    L12:
        ByteBuffer r3 = r62.duplicate();
        r3.limit(r62.position() + r12);
        r0.add(new Obu(r2, r3, null));
        r62.position(r62.position() + r12);
        goto L4
    L11:
        r12 = r62.remaining();
        goto L12
    L13:
        return r0;
    }

    private static void throwWhenFeatureRequired(boolean r1) {
        if (r1 == true) goto L5;
        return;
    L5:
        throw new NotYetImplementedException(null);
    }
}
