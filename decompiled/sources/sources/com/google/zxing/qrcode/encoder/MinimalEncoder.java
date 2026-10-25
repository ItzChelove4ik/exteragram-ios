package com.google.zxing.qrcode.encoder;

import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.ECIEncoderSet;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Mode;
import com.google.zxing.qrcode.decoder.Version;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
final class MinimalEncoder {
    private final ErrorCorrectionLevel ecLevel;
    private final ECIEncoderSet encoders;
    private final boolean isGS1;
    private final String stringToEncode;

    /* renamed from: com.google.zxing.qrcode.encoder.MinimalEncoder$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$qrcode$decoder$Mode = null;

        static {
            int[] r0 = new int[Mode.values().length];
            $SwitchMap$com$google$zxing$qrcode$decoder$Mode = r0;
            r0[Mode.KANJI.ordinal()] = 1;     // Catch: NoSuchFieldError -> L9
        L14:
            $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.ALPHANUMERIC.ordinal()] = 2;     // Catch: NoSuchFieldError -> L10
        L18:
            $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.NUMERIC.ordinal()] = 3;     // Catch: NoSuchFieldError -> L11
        L22:
            $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.BYTE.ordinal()] = 4;     // Catch: NoSuchFieldError -> L12
        L16:
            $SwitchMap$com$google$zxing$qrcode$decoder$Mode[Mode.ECI.ordinal()] = 5;     // Catch: NoSuchFieldError -> L13
            return;
        }
    }

    public final class Edge {
        private final int cachedTotalSize;
        private final int characterLength;
        private final int charsetEncoderIndex;
        private final int fromPosition;
        private final Mode mode;
        private final Edge previous;
        final /* synthetic */ MinimalEncoder this$0;

        private Edge(MinimalEncoder r6, Mode r7, int r8, int r9, int r10, Edge r11, Version r12) {
            this.this$0 = r6;
            this.mode = r7;
            this.fromPosition = r8;
            Mode r0 = Mode.BYTE;
            if (r7 == r0) goto L7;
            if (r11 == null) goto L7;
            int r1 = r11.charsetEncoderIndex;
        L8:
            this.charsetEncoderIndex = r1;
            this.characterLength = r10;
            this.previous = r11;
            boolean r2 = false;
            if (r11 == null) goto L11;
            int r3 = r11.cachedTotalSize;
        L13:
            if (r7 != r0) goto L16;
            if (r11 != null) goto L16;
            if (r1 == 0) goto L16;
        L19:
            r2 = true;
        L20:
            int r02 = 4;
            if (r11 != null) goto L23;
        L25:
            r3 = r3 + (r7.getCharacterCountBits(r12) + 4);
        L26:
            int r72 = AnonymousClass1.$SwitchMap$com$google$zxing$qrcode$decoder$Mode[r7.ordinal()];
            if (r72 != 1) goto L29;
            r3 = r3 + 13;
        L48:
            this.cachedTotalSize = r3;
            return;
        L29:
            if (r72 != 2) goto L31;
            if (r10 != 1) goto L45;
            int r62 = 6;
        L46:
            r3 = r3 + r62;
            goto L48
        L45:
            r62 = 11;
            goto L46
        L31:
            if (r72 == 3) goto L37;
            if (r72 != 4) goto L48;
            r3 = r3 + (MinimalEncoder.access$600(r6).encode(MinimalEncoder.access$500(r6).substring(r8, r10 + r8), r9).length * 8);
            if (r2 == false) goto L48;
            r3 = r3 + 12;
            goto L48
        L37:
            if (r10 == 1) goto L42;
            if (r10 != 2) goto L41;
            r02 = 7;
            goto L42
        L41:
            r02 = 10;
        L42:
            r3 = r3 + r02;
            goto L48
        L23:
            if (r7 != r11.mode) goto L25;
            if (r2 == false) goto L26;
        L16:
            if (r11 == null) goto L20;
            if (r1 == r11.charsetEncoderIndex) goto L20;
        L11:
            r3 = 0;
        L7:
            r1 = r9;
            goto L8
        }

        public /* synthetic */ Edge(MinimalEncoder r1, Mode r2, int r3, int r4, int r5, Edge r6, Version r7, AnonymousClass1 r8) {
            this(r1, r2, r3, r4, r5, r6, r7);
        }

        public static /* synthetic */ int access$000(Edge r0) {
            return r0.characterLength;
        }

        public static /* synthetic */ int access$100(Edge r0) {
            return r0.charsetEncoderIndex;
        }

        public static /* synthetic */ Mode access$200(Edge r0) {
            return r0.mode;
        }

        public static /* synthetic */ int access$300(Edge r0) {
            return r0.cachedTotalSize;
        }

        public static /* synthetic */ Edge access$700(Edge r0) {
            return r0.previous;
        }

        public static /* synthetic */ int access$800(Edge r0) {
            return r0.fromPosition;
        }
    }

    public final class ResultList {
        private final List<ResultNode> list;
        final /* synthetic */ MinimalEncoder this$0;
        private final Version version;

        public final class ResultNode {
            private final int characterLength;
            private final int charsetEncoderIndex;
            private final int fromPosition;
            private final Mode mode;
            final /* synthetic */ ResultList this$1;

            public ResultNode(ResultList r1, Mode r2, int r3, int r4, int r5) {
                this.this$1 = r1;
                this.mode = r2;
                this.fromPosition = r3;
                this.charsetEncoderIndex = r4;
                this.characterLength = r5;
            }

            public static /* synthetic */ Mode access$1000(ResultNode r0) {
                return r0.mode;
            }

            public static /* synthetic */ int access$1200(ResultNode r0, Version r1) {
                return r0.getSize(r1);
            }

            public static /* synthetic */ void access$1300(ResultNode r0, BitArray r1) {
                r0.getBits(r1);
            }

            private void getBits(BitArray r4) {
                r4.appendBits(this.mode.getBits(), 4);
                if (this.characterLength <= 0) goto L6;
                r4.appendBits(getCharacterCountIndicator(), this.mode.getCharacterCountBits(ResultList.access$1400(this.this$1)));
            L6:
                if (this.mode != Mode.ECI) goto L10;
                r4.appendBits(MinimalEncoder.access$600(this.this$1.this$0).getECIValue(this.charsetEncoderIndex), 8);
                return;
            L10:
                if (this.characterLength <= 0) goto L13;
                String r0 = MinimalEncoder.access$500(this.this$1.this$0);
                int r1 = this.fromPosition;
                Encoder.appendBytes(r0.substring(r1, this.characterLength + r1), this.mode, r4, MinimalEncoder.access$600(this.this$1.this$0).getCharset(this.charsetEncoderIndex));
                return;
            }

            private int getCharacterCountIndicator() {
                if (this.mode != Mode.BYTE) goto L7;
                ECIEncoderSet r0 = MinimalEncoder.access$600(this.this$1.this$0);
                String r1 = MinimalEncoder.access$500(this.this$1.this$0);
                int r2 = this.fromPosition;
                return r0.encode(r1.substring(r2, this.characterLength + r2), this.charsetEncoderIndex).length;
            L7:
                return this.characterLength;
            }

            private int getSize(Version r8) {
                int r82 = this.mode.getCharacterCountBits(r8);
                int r0 = r82 + 4;
                int r1 = AnonymousClass1.$SwitchMap$com$google$zxing$qrcode$decoder$Mode[this.mode.ordinal()];
                if (r1 == 1) goto L29;
                int r3 = 0;
                if (r1 != 2) goto L7;
                int r7 = this.characterLength;
                int r02 = r0 + ((r7 / 2) * 11);
                if ((r7 % 2) != 1) goto L28;
                r3 = 6;
            L28:
                return r02 + r3;
            L7:
                if (r1 == 3) goto L17;
                if (r1 != 4) goto L10;
                int r72 = getCharacterCountIndicator() * 8;
            L16:
                return r0 + r72;
            L10:
                if (r1 == 5) goto L13;
                return r0;
            L13:
                return r82 + 12;
            L17:
                int r73 = this.characterLength;
                int r03 = r0 + ((r73 / 3) * 10);
                int r74 = r73 % 3;
                if (r74 != 1) goto L20;
                r3 = 4;
            L23:
                return r03 + r3;
            L20:
                if (r74 != 2) goto L23;
                r3 = 7;
                goto L23
            L29:
                r72 = this.characterLength * 13;
                goto L16
            }

            private String makePrintable(String r4) {
                StringBuilder r3 = new StringBuilder();
                int r0 = 0;
            L4:
                if (r0 >= r4.length()) goto L14;
                if (r4.charAt(r0) >= ' ') goto L8;
            L11:
                r3.append('.');
            L12:
                r0 = r0 + 1;
                goto L4
            L8:
                if (r4.charAt(r0) > '~') goto L11;
                r3.append(r4.charAt(r0));
                goto L12
            L14:
                return r3.toString();
            }

            public String toString() {
                StringBuilder r0 = new StringBuilder();
                r0.append(this.mode);
                r0.append('(');
                Mode r1 = this.mode;
                Mode r2 = Mode.ECI;
                ResultList r3 = this.this$1;
                if (r1 != r2) goto L5;
                r0.append(MinimalEncoder.access$600(r3.this$0).getCharset(this.charsetEncoderIndex).displayName());
            L6:
                r0.append(')');
                return r0.toString();
            L5:
                String r12 = MinimalEncoder.access$500(r3.this$0);
                int r22 = this.fromPosition;
                r0.append(makePrintable(r12.substring(r22, this.characterLength + r22)));
                goto L6
            }
        }

        public ResultList(MinimalEncoder r13, Version r14, Edge r15) {
            this.this$0 = r13;
            this.list = new ArrayList();
            int r6 = 0;
            Edge r7 = r15;
            int r0 = 0;
            int r2 = 0;
        L3:
            int r8 = 1;
            if (r7 == null) goto L29;
            int r5 = r0 + Edge.access$000(r7);
            Edge r9 = Edge.access$700(r7);
            if (Edge.access$200(r7) != Mode.BYTE) goto L10;
            if (r9 != null) goto L10;
            if (Edge.access$100(r7) == 0) goto L10;
        L13:
            boolean r10 = true;
        L15:
            if (r10 == true) goto L18;
            r8 = r2;
        L18:
            if (r9 != null) goto L20;
        L24:
            this.list.add(0, new ResultNode(this, Edge.access$200(r7), Edge.access$800(r7), Edge.access$100(r7), r5));
            int r11 = 0;
        L25:
            if (r10 == false) goto L27;
            this.list.add(0, new ResultNode(this, Mode.ECI, Edge.access$800(r7), Edge.access$100(r7), 0));
        L27:
            r2 = r8;
            r7 = r9;
            r0 = r11;
            goto L3
        L20:
            if (Edge.access$200(r9) != Edge.access$200(r7)) goto L24;
            if (r10 == true) goto L24;
            r11 = r5;
        L10:
            if (r9 != null) goto L12;
        L14:
            r10 = false;
            goto L15
        L12:
            if (Edge.access$100(r7) == Edge.access$100(r9)) goto L14;
        L29:
            if (MinimalEncoder.access$900(r13) == false) goto L41;
            ResultNode r02 = this.list.get(0);
            if (r02 == null) goto L36;
            Mode r03 = ResultNode.access$1000(r02);
            int r3 = r2;
            Mode r22 = Mode.ECI;
            if (r03 == r22) goto L36;
            if (r3 == 0) goto L36;
            this.list.add(0, new ResultNode(this, r22, 0, 0, 0));
        L36:
            ResultNode r04 = this.list.get(0);
            List<ResultNode> r72 = this.list;
            if (ResultNode.access$1000(r04) != Mode.ECI) goto L40;
            r6 = 1;
        L40:
            r72.add(r6, new ResultNode(this, Mode.FNC1_FIRST_POSITION, 0, 0, 0));
        L41:
            int r05 = r14.getVersionNumber();
            int r23 = MinimalEncoder.getVersionSize(r14).ordinal();
            if (r23 == 0) goto L46;
            if (r23 == 1) goto L45;
            r8 = 27;
            int r24 = 40;
        L47:
            int r32 = getSize(r14);
        L48:
            if (r05 >= r24) goto L52;
            if (Encoder.willFit(r32, Version.getVersionForNumber(r05), MinimalEncoder.access$1100(r13)) == true) goto L52;
            r05 = r05 + 1;
        L52:
            if (r05 <= r8) goto L56;
            if (Encoder.willFit(r32, Version.getVersionForNumber(r05 - 1), MinimalEncoder.access$1100(r13)) == false) goto L56;
            r05 = r05 - 1;
        L56:
            this.version = Version.getVersionForNumber(r05);
            return;
        L45:
            r8 = 10;
            r24 = 26;
            goto L47
        L46:
            r24 = 9;
            goto L47
        }

        public static /* synthetic */ Version access$1400(ResultList r0) {
            return r0.version;
        }

        private int getSize(Version r3) {
            Iterator<ResultNode> r2 = this.list.iterator();
            int r0 = 0;
        L4:
            if (r2.hasNext() == false) goto L6;
            r0 = r0 + ResultNode.access$1200(r2.next(), r3);
            goto L4
        L6:
            return r0;
        }

        public void getBits(BitArray r2) {
            Iterator<ResultNode> r1 = this.list.iterator();
        L4:
            if (r1.hasNext() == false) goto L6;
            ResultNode.access$1300(r1.next(), r2);
            goto L4
        }

        public int getSize() {
            return getSize(this.version);
        }

        public Version getVersion() {
            return this.version;
        }

        public String toString() {
            StringBuilder r0 = new StringBuilder();
            Iterator<ResultNode> r3 = this.list.iterator();
            ResultNode r1 = null;
        L4:
            if (r3.hasNext() == false) goto L10;
            ResultNode r2 = r3.next();
            if (r1 == null) goto L8;
            r0.append(",");
        L8:
            r0.append(r2.toString());
            r1 = r2;
            goto L4
        L10:
            return r0.toString();
        }
    }

    public enum VersionSize extends Enum<VersionSize> {
        private static final /* synthetic */ VersionSize[] $VALUES = null;
        public static final VersionSize LARGE = null;
        public static final VersionSize MEDIUM = null;
        public static final VersionSize SMALL = null;
        private final String description;

        private static /* synthetic */ VersionSize[] $values() {
            return new VersionSize[]{SMALL, MEDIUM, LARGE};
        }

        static {
            SMALL = new VersionSize("SMALL", 0, "version 1-9");
            MEDIUM = new VersionSize("MEDIUM", 1, "version 10-26");
            LARGE = new VersionSize("LARGE", 2, "version 27-40");
            $VALUES = $values();
        }

        VersionSize(String r1, int r2, String r3) {
            this.description = r3;
        }

        public static VersionSize valueOf(String r1) {
            return (VersionSize) Enum.valueOf(VersionSize.class, r1);
        }

        public static VersionSize[] values() {
            return (VersionSize[]) $VALUES.clone();
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.description;
        }
    }

    public MinimalEncoder(String r2, Charset r3, boolean r4, ErrorCorrectionLevel r5) {
        this.stringToEncode = r2;
        this.isGS1 = r4;
        this.encoders = new ECIEncoderSet(r2, r3, -1);
        this.ecLevel = r5;
    }

    public static /* synthetic */ ErrorCorrectionLevel access$1100(MinimalEncoder r0) {
        return r0.ecLevel;
    }

    public static /* synthetic */ String access$500(MinimalEncoder r0) {
        return r0.stringToEncode;
    }

    public static /* synthetic */ ECIEncoderSet access$600(MinimalEncoder r0) {
        return r0.encoders;
    }

    public static /* synthetic */ boolean access$900(MinimalEncoder r0) {
        return r0.isGS1;
    }

    public static ResultList encode(String r1, Version r2, Charset r3, boolean r4, ErrorCorrectionLevel r5) {
        return new MinimalEncoder(r1, r3, r4, r5).encode(r2);
    }

    public static int getCompactedOrdinal(Mode r4) {
        if (r4 != null) goto L5;
        return 0;
    L5:
        int r1 = AnonymousClass1.$SwitchMap$com$google$zxing$qrcode$decoder$Mode[r4.ordinal()];
        if (r1 != 1) goto L8;
        return 0;
    L8:
        if (r1 != 2) goto L10;
        return 1;
    L10:
        if (r1 != 3) goto L12;
        return 2;
    L12:
        if (r1 != 4) goto L14;
        return 3;
    L14:
        DexMaker$$ExternalSyntheticBUOutline0.m("Illegal mode ", r4);
        return 0;
    }

    public static Version getVersion(VersionSize r1) {
        int r12 = r1.ordinal();
        if (r12 == 0) goto L11;
        if (r12 == 1) goto L9;
        return Version.getVersionForNumber(40);
    L9:
        return Version.getVersionForNumber(26);
    L11:
        return Version.getVersionForNumber(9);
    }

    public static VersionSize getVersionSize(Version r2) {
        if (r2.getVersionNumber() > 9) goto L7;
        return VersionSize.SMALL;
    L7:
        if (r2.getVersionNumber() > 26) goto L11;
        return VersionSize.MEDIUM;
    L11:
        return VersionSize.LARGE;
    }

    public static boolean isAlphanumeric(char r1) {
        if (Encoder.getAlphanumericCode(r1) == (-1)) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isDoubleByteKanji(char r0) {
        return Encoder.isOnlyDoubleByteKanji(String.valueOf(r0));
    }

    public static boolean isNumeric(char r1) {
        if (r1 >= '0') goto L5;
        return false;
    L5:
        if (r1 > '9') goto L10;
        return true;
    L10:
        return false;
    }

    public void addEdge(Edge[][][] r2, int r3, Edge r4) {
        Edge[] r1 = r2[r3 + Edge.access$000(r4)][Edge.access$100(r4)];
        int r22 = getCompactedOrdinal(Edge.access$200(r4));
        Edge r32 = r1[r22];
        if (r32 != null) goto L5;
    L8:
        r1[r22] = r4;
        return;
    L5:
        if (Edge.access$300(r32) > Edge.access$300(r4)) goto L8;
    }

    public void addEdges(Version r13, Edge[][][] r14, int r15, Edge r16) {
        int r0 = this.encoders.length();
        int r2 = this.encoders.getPriorityEncoderIndex();
        if (r2 >= 0) goto L5;
    L8:
        r2 = 0;
    L7:
        int r9 = r0;
        int r4 = r2;
    L10:
        if (r4 >= r9) goto L15;
        if (this.encoders.canEncode(this.stringToEncode.charAt(r15), r4) == false) goto L14;
        addEdge(r14, r15, new Edge(this, Mode.BYTE, r15, r4, 1, r16, r13, null));
    L14:
        r4 = r4 + 1;
        goto L10
    L15:
        Mode r22 = Mode.KANJI;
        if (canEncode(r22, this.stringToEncode.charAt(r15)) == false) goto L18;
        addEdge(r14, r15, new Edge(this, r22, r15, 0, 1, r16, r13, null));
    L18:
        int r92 = this.stringToEncode.length();
        Mode r23 = Mode.ALPHANUMERIC;
        int r10 = 2;
        if (canEncode(r23, this.stringToEncode.charAt(r15)) == false) goto L28;
        int r42 = r15 + 1;
        if (r42 < r92) goto L23;
    L26:
        int r5 = 1;
    L27:
        addEdge(r14, r15, new Edge(this, r23, r15, 0, r5, r16, r13, null));
        goto L28
    L23:
        if (canEncode(r23, this.stringToEncode.charAt(r42)) == false) goto L26;
        r5 = 2;
    L28:
        Mode r24 = Mode.NUMERIC;
        if (canEncode(r24, this.stringToEncode.charAt(r15)) == false) goto L48;
        int r43 = r15 + 1;
        if (r43 < r92) goto L33;
    L42:
        int r52 = 1;
    L43:
        addEdge(r14, r15, new Edge(this, r24, r15, 0, r52, r16, r13, null));
        return;
    L33:
        if (canEncode(r24, this.stringToEncode.charAt(r43)) == false) goto L42;
        int r44 = r15 + 2;
        if (r44 < r92) goto L38;
    L41:
        r52 = r10;
        goto L43
    L38:
        if (canEncode(r24, this.stringToEncode.charAt(r44)) == false) goto L41;
        r10 = 3;
        goto L41
    L48:
        return;
    L5:
        if (this.encoders.canEncode(this.stringToEncode.charAt(r15), r2) == false) goto L8;
        r0 = r2 + 1;
        goto L7
    }

    public boolean canEncode(Mode r2, char r3) {
        int r1 = AnonymousClass1.$SwitchMap$com$google$zxing$qrcode$decoder$Mode[r2.ordinal()];
        if (r1 == 1) goto L18;
        if (r1 == 2) goto L16;
        if (r1 == 3) goto L14;
        if (r1 == 4) goto L12;
        return false;
    L12:
        return true;
    L14:
        return isNumeric(r3);
    L16:
        return isAlphanumeric(r3);
    L18:
        return isDoubleByteKanji(r3);
    }

    public ResultList encode(Version r8) {
        if (r8 != null) goto L16;
        Version[] r82 = {getVersion(VersionSize.SMALL), getVersion(VersionSize.MEDIUM), getVersion(VersionSize.LARGE)};
        int r0 = 0;
        ResultList[] r1 = {encodeSpecificVersion(r82[0]), encodeSpecificVersion(r82[1]), encodeSpecificVersion(r82[2])};
        int r2 = Integer.MAX_VALUE;
        int r3 = -1;
    L5:
        if (r0 >= 3) goto L11;
        int r4 = r1[r0].getSize();
        if (Encoder.willFit(r4, r82[r0], this.ecLevel) == false) goto L10;
        if (r4 >= r2) goto L10;
        r3 = r0;
        r2 = r4;
    L10:
        r0 = r0 + 1;
        goto L5
    L11:
        if (r3 < 0) goto L15;
        return r1[r3];
    L15:
        throw new WriterException("Data too big for any version");
    L16:
        ResultList r02 = encodeSpecificVersion(r8);
        if (Encoder.willFit(r02.getSize(), getVersion(getVersionSize(r02.getVersion())), this.ecLevel) == false) goto L20;
        return r02;
    L20:
        throw new WriterException("Data too big for version" + r8);
    }

    public ResultList encodeSpecificVersion(Version r12) {
        int r0 = this.stringToEncode.length();
        int r4 = 1;
        Edge[][][] r1 = (Edge[][][]) Array.newInstance(Edge.class, new int[]{r0 + 1, this.encoders.length(), 4});
        addEdges(r12, r1, 0, null);
    L3:
        if (r4 > r0) goto L16;
        int r3 = 0;
    L6:
        if (r3 >= this.encoders.length()) goto L15;
        int r6 = 0;
    L8:
        if (r6 >= 4) goto L14;
        Edge r7 = r1[r4][r3][r6];
        if (r7 == null) goto L13;
        if (r4 >= r0) goto L13;
        addEdges(r12, r1, r4, r7);
    L13:
        r6 = r6 + 1;
        goto L8
    L14:
        r3 = r3 + 1;
        goto L6
    L15:
        r4 = r4 + 1;
        goto L3
    L16:
        int r32 = -1;
        int r72 = 0;
        int r62 = Integer.MAX_VALUE;
        int r42 = -1;
    L18:
        if (r72 >= this.encoders.length()) goto L28;
        int r8 = 0;
    L20:
        if (r8 >= 4) goto L27;
        Edge r9 = r1[r0][r72][r8];
        if (r9 == null) goto L26;
        if (Edge.access$300(r9) >= r62) goto L26;
        r62 = Edge.access$300(r9);
        r32 = r72;
        r42 = r8;
    L26:
        r8 = r8 + 1;
        goto L20
    L27:
        r72 = r72 + 1;
        goto L18
    L28:
        if (r32 < 0) goto L32;
        return new ResultList(this, r12, r1[r0][r32][r42]);
    L32:
        throw new WriterException("Internal error: failed to encode \"" + this.stringToEncode + "\"");
    }
}
