package com.android.dx.io.instructions;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.io.IndexType;
import com.android.dx.io.OpcodeInfo;
import com.android.dx.merge.DexMerger$$ExternalSyntheticBUOutline0;
import com.android.dx.util.Hex;
import java.util.Arrays;
import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public enum InstructionCodec extends Enum<InstructionCodec> {
    private static final /* synthetic */ InstructionCodec[] $VALUES = null;
    public static final InstructionCodec FORMAT_00X = null;
    public static final InstructionCodec FORMAT_10T = null;
    public static final InstructionCodec FORMAT_10X = null;
    public static final InstructionCodec FORMAT_11N = null;
    public static final InstructionCodec FORMAT_11X = null;
    public static final InstructionCodec FORMAT_12X = null;
    public static final InstructionCodec FORMAT_20BC = null;
    public static final InstructionCodec FORMAT_20T = null;
    public static final InstructionCodec FORMAT_21C = null;
    public static final InstructionCodec FORMAT_21H = null;
    public static final InstructionCodec FORMAT_21S = null;
    public static final InstructionCodec FORMAT_21T = null;
    public static final InstructionCodec FORMAT_22B = null;
    public static final InstructionCodec FORMAT_22C = null;
    public static final InstructionCodec FORMAT_22CS = null;
    public static final InstructionCodec FORMAT_22S = null;
    public static final InstructionCodec FORMAT_22T = null;
    public static final InstructionCodec FORMAT_22X = null;
    public static final InstructionCodec FORMAT_23X = null;
    public static final InstructionCodec FORMAT_30T = null;
    public static final InstructionCodec FORMAT_31C = null;
    public static final InstructionCodec FORMAT_31I = null;
    public static final InstructionCodec FORMAT_31T = null;
    public static final InstructionCodec FORMAT_32X = null;
    public static final InstructionCodec FORMAT_35C = null;
    public static final InstructionCodec FORMAT_35MI = null;
    public static final InstructionCodec FORMAT_35MS = null;
    public static final InstructionCodec FORMAT_3RC = null;
    public static final InstructionCodec FORMAT_3RMI = null;
    public static final InstructionCodec FORMAT_3RMS = null;
    public static final InstructionCodec FORMAT_45CC = null;
    public static final InstructionCodec FORMAT_4RCC = null;
    public static final InstructionCodec FORMAT_51L = null;
    public static final InstructionCodec FORMAT_FILL_ARRAY_DATA_PAYLOAD = null;
    public static final InstructionCodec FORMAT_PACKED_SWITCH_PAYLOAD = null;
    public static final InstructionCodec FORMAT_SPARSE_SWITCH_PAYLOAD = null;

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$1, reason: invalid class name */
    public enum AnonymousClass1 extends InstructionCodec {
        public AnonymousClass1(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r9, CodeInput r10) {
            return new ZeroRegisterDecodedInstruction(this, r9, 0, null, 0, 0);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r1, CodeOutput r2) {
            r2.write(r1.getOpcodeUnit());
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$10, reason: invalid class name */
    public enum AnonymousClass10 extends InstructionCodec {
        public AnonymousClass10(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r11, CodeInput r12) {
            int r0 = r12.cursor() - 1;
            return new OneRegisterDecodedInstruction(this, InstructionCodec.access$100(r11), 0, null, r0 + ((short) r12.read()), 0, InstructionCodec.access$200(r11));
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r2, CodeOutput r3) {
            short r1 = r2.getTargetUnit(r3.cursor());
            r3.write(InstructionCodec.access$600(r2.getOpcode(), r2.getA()), r1);
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$11, reason: invalid class name */
    public enum AnonymousClass11 extends InstructionCodec {
        public AnonymousClass11(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r10, CodeInput r11) {
            return new OneRegisterDecodedInstruction(this, InstructionCodec.access$100(r10), 0, null, 0, (short) r11.read(), InstructionCodec.access$200(r10));
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r2, CodeOutput r3) {
            r3.write(InstructionCodec.access$600(r2.getOpcode(), r2.getA()), r2.getLiteralUnit());
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$12, reason: invalid class name */
    public enum AnonymousClass12 extends InstructionCodec {
        public AnonymousClass12(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r10, CodeInput r11) {
            int r2 = InstructionCodec.access$100(r10);
            int r8 = InstructionCodec.access$200(r10);
            long r102 = (short) r11.read();
            if (r2 != 21) goto L5;
            char r0 = 16;
        L7:
            return new OneRegisterDecodedInstruction(this, r2, 0, null, 0, r102 << r0, r8);
        L5:
            r0 = '0';
            goto L7
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r4, CodeOutput r5) {
            int r3 = r4.getOpcode();
            if (r3 != 21) goto L5;
            char r0 = 16;
        L6:
            r5.write(InstructionCodec.access$600(r3, r4.getA()), (short) (r4.getLiteral() >> r0));
            return;
        L5:
            r0 = '0';
            goto L6
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$13, reason: invalid class name */
    public enum AnonymousClass13 extends InstructionCodec {
        public AnonymousClass13(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r10, CodeInput r11) {
            int r2 = InstructionCodec.access$100(r10);
            int r8 = InstructionCodec.access$200(r10);
            return new OneRegisterDecodedInstruction(this, r2, r11.read(), OpcodeInfo.getIndexType(r2), 0, 0, r8);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r2, CodeOutput r3) {
            r3.write(InstructionCodec.access$600(r2.getOpcode(), r2.getA()), r2.getIndexUnit());
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$14, reason: invalid class name */
    public enum AnonymousClass14 extends InstructionCodec {
        public AnonymousClass14(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r12, CodeInput r13) {
            int r2 = InstructionCodec.access$100(r12);
            int r8 = InstructionCodec.access$200(r12);
            int r122 = r13.read();
            return new ThreeRegisterDecodedInstruction(this, r2, 0, null, 0, 0, r8, InstructionCodec.access$100(r122), InstructionCodec.access$200(r122));
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r2, CodeOutput r3) {
            r3.write(InstructionCodec.access$600(r2.getOpcode(), r2.getA()), InstructionCodec.access$600(r2.getB(), r2.getC()));
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$15, reason: invalid class name */
    public enum AnonymousClass15 extends InstructionCodec {
        public AnonymousClass15(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r11, CodeInput r12) {
            return new TwoRegisterDecodedInstruction(this, InstructionCodec.access$100(r11), 0, null, 0, (byte) InstructionCodec.access$200(r11), InstructionCodec.access$200(r11), InstructionCodec.access$100(r12.read()));
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r2, CodeOutput r3) {
            r3.write(InstructionCodec.access$600(r2.getOpcode(), r2.getA()), InstructionCodec.access$600(r2.getB(), r2.getLiteralByte()));
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$16, reason: invalid class name */
    public enum AnonymousClass16 extends InstructionCodec {
        public AnonymousClass16(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r12, CodeInput r13) {
            int r0 = r13.cursor() - 1;
            return new TwoRegisterDecodedInstruction(this, InstructionCodec.access$100(r12), 0, null, r0 + ((short) r13.read()), 0, InstructionCodec.access$300(r12), InstructionCodec.access$400(r12));
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r3, CodeOutput r4) {
            short r2 = r3.getTargetUnit(r4.cursor());
            r4.write(InstructionCodec.access$600(r3.getOpcode(), InstructionCodec.access$500(r3.getA(), r3.getB())), r2);
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$17, reason: invalid class name */
    public enum AnonymousClass17 extends InstructionCodec {
        public AnonymousClass17(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r11, CodeInput r12) {
            return new TwoRegisterDecodedInstruction(this, InstructionCodec.access$100(r11), 0, null, 0, (short) r12.read(), InstructionCodec.access$300(r11), InstructionCodec.access$400(r11));
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r3, CodeOutput r4) {
            r4.write(InstructionCodec.access$600(r3.getOpcode(), InstructionCodec.access$500(r3.getA(), r3.getB())), r3.getLiteralUnit());
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$18, reason: invalid class name */
    public enum AnonymousClass18 extends InstructionCodec {
        public AnonymousClass18(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r11, CodeInput r12) {
            int r2 = InstructionCodec.access$100(r11);
            int r8 = InstructionCodec.access$300(r11);
            int r9 = InstructionCodec.access$400(r11);
            return new TwoRegisterDecodedInstruction(this, r2, r12.read(), OpcodeInfo.getIndexType(r2), 0, 0, r8, r9);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r3, CodeOutput r4) {
            r4.write(InstructionCodec.access$600(r3.getOpcode(), InstructionCodec.access$500(r3.getA(), r3.getB())), r3.getIndexUnit());
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$19, reason: invalid class name */
    public enum AnonymousClass19 extends InstructionCodec {
        public AnonymousClass19(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r11, CodeInput r12) {
            int r2 = InstructionCodec.access$100(r11);
            int r8 = InstructionCodec.access$300(r11);
            int r9 = InstructionCodec.access$400(r11);
            return new TwoRegisterDecodedInstruction(this, r2, r12.read(), IndexType.FIELD_OFFSET, 0, 0, r8, r9);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r3, CodeOutput r4) {
            r4.write(InstructionCodec.access$600(r3.getOpcode(), InstructionCodec.access$500(r3.getA(), r3.getB())), r3.getIndexUnit());
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$2, reason: invalid class name */
    public enum AnonymousClass2 extends InstructionCodec {
        public AnonymousClass2(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r9, CodeInput r10) {
            return new ZeroRegisterDecodedInstruction(this, InstructionCodec.access$100(r9), 0, null, 0, InstructionCodec.access$200(r9));
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r1, CodeOutput r2) {
            r2.write(r1.getOpcodeUnit());
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$20, reason: invalid class name */
    public enum AnonymousClass20 extends InstructionCodec {
        public AnonymousClass20(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r10, CodeInput r11) {
            int r0 = r11.cursor() - 1;
            return new ZeroRegisterDecodedInstruction(this, InstructionCodec.access$100(r10), 0, null, r0 + r11.readInt(), InstructionCodec.access$200(r10));
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r2, CodeOutput r3) {
            int r1 = r2.getTarget(r3.cursor());
            r3.write(r2.getOpcodeUnit(), InstructionCodec.access$700(r1), InstructionCodec.access$800(r1));
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$21, reason: invalid class name */
    public enum AnonymousClass21 extends InstructionCodec {
        public AnonymousClass21(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r11, CodeInput r12) {
            return new TwoRegisterDecodedInstruction(this, InstructionCodec.access$100(r11), 0, null, 0, InstructionCodec.access$200(r11), r12.read(), r12.read());
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r2, CodeOutput r3) {
            r3.write(r2.getOpcodeUnit(), r2.getAUnit(), r2.getBUnit());
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$22, reason: invalid class name */
    public enum AnonymousClass22 extends InstructionCodec {
        public AnonymousClass22(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r10, CodeInput r11) {
            return new OneRegisterDecodedInstruction(this, InstructionCodec.access$100(r10), 0, null, 0, r11.readInt(), InstructionCodec.access$200(r10));
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r2, CodeOutput r3) {
            int r1 = r2.getLiteralInt();
            r3.write(InstructionCodec.access$600(r2.getOpcode(), r2.getA()), InstructionCodec.access$700(r1), InstructionCodec.access$800(r1));
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$23, reason: invalid class name */
    public enum AnonymousClass23 extends InstructionCodec {
        public AnonymousClass23(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r11, CodeInput r12) {
            int r0 = r12.cursor() - 1;
            int r3 = InstructionCodec.access$100(r11);
            int r9 = InstructionCodec.access$200(r11);
            int r6 = r0 + r12.readInt();
            if (r3 != 43) goto L5;
        L7:
            r12.setBaseAddress(r6, r0);
        L9:
            return new OneRegisterDecodedInstruction(this, r3, 0, null, r6, 0, r9);
        L5:
            if (r3 == 44) goto L7;
            goto L7
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r2, CodeOutput r3) {
            int r1 = r2.getTarget(r3.cursor());
            r3.write(InstructionCodec.access$600(r2.getOpcode(), r2.getA()), InstructionCodec.access$700(r1), InstructionCodec.access$800(r1));
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$24, reason: invalid class name */
    public enum AnonymousClass24 extends InstructionCodec {
        public AnonymousClass24(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r10, CodeInput r11) {
            int r2 = InstructionCodec.access$100(r10);
            int r8 = InstructionCodec.access$200(r10);
            return new OneRegisterDecodedInstruction(this, r2, r11.readInt(), OpcodeInfo.getIndexType(r2), 0, 0, r8);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r2, CodeOutput r3) {
            int r1 = r2.getIndex();
            r3.write(InstructionCodec.access$600(r2.getOpcode(), r2.getA()), InstructionCodec.access$700(r1), InstructionCodec.access$800(r1));
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$25, reason: invalid class name */
    public enum AnonymousClass25 extends InstructionCodec {
        public AnonymousClass25(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r1, CodeInput r2) {
            return InstructionCodec.access$900(this, r1, r2);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r1, CodeOutput r2) {
            InstructionCodec.access$1000(r1, r2);
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$26, reason: invalid class name */
    public enum AnonymousClass26 extends InstructionCodec {
        public AnonymousClass26(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r1, CodeInput r2) {
            return InstructionCodec.access$900(this, r1, r2);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r1, CodeOutput r2) {
            InstructionCodec.access$1000(r1, r2);
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$27, reason: invalid class name */
    public enum AnonymousClass27 extends InstructionCodec {
        public AnonymousClass27(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r1, CodeInput r2) {
            return InstructionCodec.access$900(this, r1, r2);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r1, CodeOutput r2) {
            InstructionCodec.access$1000(r1, r2);
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$28, reason: invalid class name */
    public enum AnonymousClass28 extends InstructionCodec {
        public AnonymousClass28(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r1, CodeInput r2) {
            return InstructionCodec.access$1100(this, r1, r2);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r1, CodeOutput r2) {
            InstructionCodec.access$1200(r1, r2);
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$29, reason: invalid class name */
    public enum AnonymousClass29 extends InstructionCodec {
        public AnonymousClass29(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r1, CodeInput r2) {
            return InstructionCodec.access$1100(this, r1, r2);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r1, CodeOutput r2) {
            InstructionCodec.access$1200(r1, r2);
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$3, reason: invalid class name */
    public enum AnonymousClass3 extends InstructionCodec {
        public AnonymousClass3(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r11, CodeInput r12) {
            return new TwoRegisterDecodedInstruction(this, InstructionCodec.access$100(r11), 0, null, 0, 0, InstructionCodec.access$300(r11), InstructionCodec.access$400(r11));
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r2, CodeOutput r3) {
            r3.write(InstructionCodec.access$600(r2.getOpcodeUnit(), InstructionCodec.access$500(r2.getA(), r2.getB())));
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$30, reason: invalid class name */
    public enum AnonymousClass30 extends InstructionCodec {
        public AnonymousClass30(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r1, CodeInput r2) {
            return InstructionCodec.access$1100(this, r1, r2);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r1, CodeOutput r2) {
            InstructionCodec.access$1200(r1, r2);
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$31, reason: invalid class name */
    public enum AnonymousClass31 extends InstructionCodec {
        public AnonymousClass31(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r10, CodeInput r11) {
            int r2 = InstructionCodec.access$100(r10);
            int r8 = InstructionCodec.access$200(r10);
            return new OneRegisterDecodedInstruction(this, r2, 0, null, 0, r11.readLong(), r8);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r9, CodeOutput r10) {
            long r0 = r9.getLiteral();
            r10.write(InstructionCodec.access$600(r9.getOpcode(), r9.getA()), InstructionCodec.access$1300(r0), InstructionCodec.access$1400(r0), InstructionCodec.access$1500(r0), InstructionCodec.access$1600(r0));
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$32, reason: invalid class name */
    public enum AnonymousClass32 extends InstructionCodec {
        public AnonymousClass32(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r11, CodeInput r12) {
            int r2 = InstructionCodec.access$100(r11);
            if (r2 != 250) goto L12;
            int r0 = InstructionCodec.access$300(r11);
            int r112 = InstructionCodec.access$400(r11);
            int r3 = r12.read();
            int r4 = r12.read();
            int r5 = InstructionCodec.access$1700(r4);
            int r6 = InstructionCodec.access$1800(r4);
            int r7 = InstructionCodec.access$300(r4);
            int r42 = InstructionCodec.access$400(r4);
            int r122 = r12.read();
            IndexType r43 = OpcodeInfo.getIndexType(r2);
            if (r112 >= 1) goto L7;
        L10:
            DexMerger$$ExternalSyntheticBUOutline0.m("bogus registerCount: ", Hex.uNibble(r112));
            return null;
        L7:
            if (r112 > 5) goto L10;
            return new InvokePolymorphicDecodedInstruction(this, r2, r3, r43, r122, Arrays.copyOfRange(new int[]{r5, r6, r7, r42, r0}, 0, r112));
        L12:
            ByteString$$ExternalSyntheticBUOutline0.m(String.valueOf(r2));
            return null;
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r6, CodeOutput r7) {
            InvokePolymorphicDecodedInstruction r62 = (InvokePolymorphicDecodedInstruction) r6;
            r7.write(InstructionCodec.access$600(r62.getOpcode(), InstructionCodec.access$500(r62.getG(), r62.getRegisterCount())), r62.getIndexUnit(), InstructionCodec.access$1900(r62.getC(), r62.getD(), r62.getE(), r62.getF()), r62.getProtoIndex());
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$33, reason: invalid class name */
    public enum AnonymousClass33 extends InstructionCodec {
        public AnonymousClass33(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r9, CodeInput r10) {
            int r2 = InstructionCodec.access$100(r9);
            if (r2 != 251) goto L6;
            int r6 = InstructionCodec.access$200(r9);
            int r3 = r10.read();
            int r5 = r10.read();
            int r7 = r10.read();
            return new InvokePolymorphicRangeDecodedInstruction(this, r2, r3, OpcodeInfo.getIndexType(r2), r5, r6, r7);
        L6:
            ByteString$$ExternalSyntheticBUOutline0.m(String.valueOf(r2));
            return null;
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r3, CodeOutput r4) {
            r4.write(InstructionCodec.access$600(r3.getOpcode(), r3.getRegisterCount()), r3.getIndexUnit(), r3.getCUnit(), r3.getProtoIndex());
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$34, reason: invalid class name */
    public enum AnonymousClass34 extends InstructionCodec {
        public AnonymousClass34(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r7, CodeInput r8) {
            int r0 = r8.baseAddressForCursor() - 1;
            int r1 = r8.read();
            int r2 = r8.readInt();
            int[] r3 = new int[r1];
            int r4 = 0;
        L3:
            if (r4 >= r1) goto L6;
            r3[r4] = r8.readInt() + r0;
            r4 = r4 + 1;
            goto L3
        L6:
            return new PackedSwitchPayloadDecodedInstruction(this, r7, r2, r3);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r4, CodeOutput r5) {
            PackedSwitchPayloadDecodedInstruction r42 = (PackedSwitchPayloadDecodedInstruction) r4;
            int[] r3 = r42.getTargets();
            int r0 = r5.baseAddressForCursor();
            r5.write(r42.getOpcodeUnit());
            r5.write(InstructionCodec.access$2000(r3.length));
            r5.writeInt(r42.getFirstKey());
            int r43 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r43) goto L5;
            r5.writeInt(r3[r1] - r0);
            r1 = r1 + 1;
            goto L3
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$35, reason: invalid class name */
    public enum AnonymousClass35 extends InstructionCodec {
        public AnonymousClass35(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r8, CodeInput r9) {
            int r0 = r9.baseAddressForCursor() - 1;
            int r1 = r9.read();
            int[] r2 = new int[r1];
            int[] r3 = new int[r1];
            int r4 = 0;
            int r5 = 0;
        L3:
            if (r5 >= r1) goto L5;
            r2[r5] = r9.readInt();
            r5 = r5 + 1;
        L5:
            if (r4 >= r1) goto L8;
            r3[r4] = r9.readInt() + r0;
            r4 = r4 + 1;
            goto L5
        L8:
            return new SparseSwitchPayloadDecodedInstruction(this, r8, r2, r3);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r6, CodeOutput r7) {
            SparseSwitchPayloadDecodedInstruction r62 = (SparseSwitchPayloadDecodedInstruction) r6;
            int[] r5 = r62.getKeys();
            int[] r0 = r62.getTargets();
            int r1 = r7.baseAddressForCursor();
            r7.write(r62.getOpcodeUnit());
            r7.write(InstructionCodec.access$2000(r0.length));
            int r63 = r5.length;
            int r2 = 0;
            int r3 = 0;
        L3:
            if (r3 >= r63) goto L5;
            r7.writeInt(r5[r3]);
            r3 = r3 + 1;
            goto L3
        L5:
            int r52 = r0.length;
        L6:
            if (r2 >= r52) goto L8;
            r7.writeInt(r0[r2] - r1);
            r2 = r2 + 1;
            goto L6
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$36, reason: invalid class name */
    public enum AnonymousClass36 extends InstructionCodec {
        public AnonymousClass36(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r8, CodeInput r9) {
            int r0 = r9.read();
            int r1 = r9.readInt();
            int r3 = 0;
            if (r0 != 1) goto L5;
            byte[] r02 = new byte[r1];
            boolean r5 = true;
            int r4 = 0;
        L27:
            if (r3 >= r1) goto L32;
            if (r5 == false) goto L30;
            r4 = r9.read();
        L30:
            r02[r3] = (byte) (r4 & 255);
            r4 = r4 >> 8;
            r3 = r3 + 1;
            r5 = !r5;
            goto L27
        L32:
            return new FillArrayDataPayloadDecodedInstruction(this, r8, r02);
        L5:
            if (r0 != 2) goto L7;
            short[] r03 = new short[r1];
        L22:
            if (r3 >= r1) goto L25;
            r03[r3] = (short) r9.read();
            r3 = r3 + 1;
            goto L22
        L25:
            return new FillArrayDataPayloadDecodedInstruction(this, r8, r03);
        L7:
            if (r0 == 4) goto L16;
            if (r0 != 8) goto L14;
            long[] r04 = new long[r1];
        L10:
            if (r3 >= r1) goto L13;
            r04[r3] = r9.readLong();
            r3 = r3 + 1;
            goto L10
        L13:
            return new FillArrayDataPayloadDecodedInstruction(this, r8, r04);
        L14:
            DexMerger$$ExternalSyntheticBUOutline0.m("bogus element_width: ", Hex.u2(r0));
            return null;
        L16:
            int[] r05 = new int[r1];
        L17:
            if (r3 >= r1) goto L20;
            r05[r3] = r9.readInt();
            r3 = r3 + 1;
            goto L17
        L20:
            return new FillArrayDataPayloadDecodedInstruction(this, r8, r05);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r3, CodeOutput r4) {
            FillArrayDataPayloadDecodedInstruction r32 = (FillArrayDataPayloadDecodedInstruction) r3;
            short r2 = r32.getElementWidthUnit();
            Object r0 = r32.getData();
            r4.write(r32.getOpcodeUnit());
            r4.write(r2);
            r4.writeInt(r32.getSize());
            if (r2 != 1) goto L5;
            r4.write((byte[]) r0);
            return;
        L5:
            if (r2 != 2) goto L7;
            r4.write((short[]) r0);
            return;
        L7:
            if (r2 != 4) goto L9;
            r4.write((int[]) r0);
            return;
        L9:
            if (r2 != 8) goto L12;
            r4.write((long[]) r0);
            return;
        L12:
            DexMerger$$ExternalSyntheticBUOutline0.m("bogus element_width: ", Hex.u2(r2));
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$4, reason: invalid class name */
    public enum AnonymousClass4 extends InstructionCodec {
        public AnonymousClass4(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r10, CodeInput r11) {
            return new OneRegisterDecodedInstruction(this, InstructionCodec.access$100(r10), 0, null, 0, (InstructionCodec.access$400(r10) << 28) >> 28, InstructionCodec.access$300(r10));
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r2, CodeOutput r3) {
            r3.write(InstructionCodec.access$600(r2.getOpcodeUnit(), InstructionCodec.access$500(r2.getA(), r2.getLiteralNibble())));
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$5, reason: invalid class name */
    public enum AnonymousClass5 extends InstructionCodec {
        public AnonymousClass5(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r10, CodeInput r11) {
            return new OneRegisterDecodedInstruction(this, InstructionCodec.access$100(r10), 0, null, 0, 0, InstructionCodec.access$200(r10));
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r1, CodeOutput r2) {
            r2.write(InstructionCodec.access$600(r1.getOpcode(), r1.getA()));
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$6, reason: invalid class name */
    public enum AnonymousClass6 extends InstructionCodec {
        public AnonymousClass6(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r9, CodeInput r10) {
            return new ZeroRegisterDecodedInstruction(this, InstructionCodec.access$100(r9), 0, null, (r10.cursor() - 1) + ((byte) InstructionCodec.access$200(r9)), 0);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r1, CodeOutput r2) {
            int r0 = r1.getTargetByte(r2.cursor());
            r2.write(InstructionCodec.access$600(r1.getOpcode(), r0));
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$7, reason: invalid class name */
    public enum AnonymousClass7 extends InstructionCodec {
        public AnonymousClass7(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r10, CodeInput r11) {
            int r0 = r11.cursor() - 1;
            return new ZeroRegisterDecodedInstruction(this, InstructionCodec.access$100(r10), 0, null, r0 + ((short) r11.read()), InstructionCodec.access$200(r10));
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r1, CodeOutput r2) {
            short r0 = r1.getTargetUnit(r2.cursor());
            r2.write(r1.getOpcodeUnit(), r0);
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$8, reason: invalid class name */
    public enum AnonymousClass8 extends InstructionCodec {
        public AnonymousClass8(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r9, CodeInput r10) {
            return new ZeroRegisterDecodedInstruction(this, InstructionCodec.access$100(r9), r10.read(), IndexType.VARIES, 0, InstructionCodec.access$200(r9));
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r2, CodeOutput r3) {
            r3.write(InstructionCodec.access$600(r2.getOpcode(), r2.getLiteralByte()), r2.getIndexUnit());
        }
    }

    /* renamed from: com.android.dx.io.instructions.InstructionCodec$9, reason: invalid class name */
    public enum AnonymousClass9 extends InstructionCodec {
        public AnonymousClass9(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public DecodedInstruction decode(int r11, CodeInput r12) {
            return new TwoRegisterDecodedInstruction(this, InstructionCodec.access$100(r11), 0, null, 0, 0, InstructionCodec.access$200(r11), r12.read());
        }

        @Override // com.android.dx.io.instructions.InstructionCodec
        public void encode(DecodedInstruction r2, CodeOutput r3) {
            r3.write(InstructionCodec.access$600(r2.getOpcode(), r2.getA()), r2.getBUnit());
        }
    }

    static {
        AnonymousClass1 r0 = new AnonymousClass1("FORMAT_00X", 0);
        FORMAT_00X = r0;
        AnonymousClass2 r1 = new AnonymousClass2("FORMAT_10X", 1);
        FORMAT_10X = r1;
        AnonymousClass3 r3 = new AnonymousClass3("FORMAT_12X", 2);
        FORMAT_12X = r3;
        AnonymousClass4 r5 = new AnonymousClass4("FORMAT_11N", 3);
        FORMAT_11N = r5;
        AnonymousClass5 r7 = new AnonymousClass5("FORMAT_11X", 4);
        FORMAT_11X = r7;
        AnonymousClass6 r9 = new AnonymousClass6("FORMAT_10T", 5);
        FORMAT_10T = r9;
        AnonymousClass7 r11 = new AnonymousClass7("FORMAT_20T", 6);
        FORMAT_20T = r11;
        AnonymousClass8 r13 = new AnonymousClass8("FORMAT_20BC", 7);
        FORMAT_20BC = r13;
        AnonymousClass9 r15 = new AnonymousClass9("FORMAT_22X", 8);
        FORMAT_22X = r15;
        AnonymousClass10 r2 = new AnonymousClass10("FORMAT_21T", 9);
        FORMAT_21T = r2;
        AnonymousClass11 r4 = new AnonymousClass11("FORMAT_21S", 10);
        FORMAT_21S = r4;
        AnonymousClass12 r6 = new AnonymousClass12("FORMAT_21H", 11);
        FORMAT_21H = r6;
        AnonymousClass13 r8 = new AnonymousClass13("FORMAT_21C", 12);
        FORMAT_21C = r8;
        AnonymousClass14 r10 = new AnonymousClass14("FORMAT_23X", 13);
        FORMAT_23X = r10;
        AnonymousClass15 r12 = new AnonymousClass15("FORMAT_22B", 14);
        FORMAT_22B = r12;
        AnonymousClass16 r14 = new AnonymousClass16("FORMAT_22T", 15);
        FORMAT_22T = r14;
        AnonymousClass17 r02 = new AnonymousClass17("FORMAT_22S", 16);
        FORMAT_22S = r02;
        AnonymousClass18 r16 = new AnonymousClass18("FORMAT_22C", 17);
        FORMAT_22C = r16;
        AnonymousClass19 r22 = new AnonymousClass19("FORMAT_22CS", 18);
        FORMAT_22CS = r22;
        AnonymousClass20 r03 = new AnonymousClass20("FORMAT_30T", 19);
        FORMAT_30T = r03;
        AnonymousClass21 r17 = new AnonymousClass21("FORMAT_32X", 20);
        FORMAT_32X = r17;
        AnonymousClass22 r23 = new AnonymousClass22("FORMAT_31I", 21);
        FORMAT_31I = r23;
        AnonymousClass23 r04 = new AnonymousClass23("FORMAT_31T", 22);
        FORMAT_31T = r04;
        AnonymousClass24 r18 = new AnonymousClass24("FORMAT_31C", 23);
        FORMAT_31C = r18;
        AnonymousClass25 r05 = new AnonymousClass25("FORMAT_35C", 24);
        FORMAT_35C = r05;
        AnonymousClass26 r19 = new AnonymousClass26("FORMAT_35MS", 25);
        FORMAT_35MS = r19;
        AnonymousClass27 r06 = new AnonymousClass27("FORMAT_35MI", 26);
        FORMAT_35MI = r06;
        AnonymousClass28 r110 = new AnonymousClass28("FORMAT_3RC", 27);
        FORMAT_3RC = r110;
        AnonymousClass29 r07 = new AnonymousClass29("FORMAT_3RMS", 28);
        FORMAT_3RMS = r07;
        AnonymousClass30 r111 = new AnonymousClass30("FORMAT_3RMI", 29);
        FORMAT_3RMI = r111;
        AnonymousClass31 r08 = new AnonymousClass31("FORMAT_51L", 30);
        FORMAT_51L = r08;
        AnonymousClass32 r112 = new AnonymousClass32("FORMAT_45CC", 31);
        FORMAT_45CC = r112;
        AnonymousClass33 r09 = new AnonymousClass33("FORMAT_4RCC", 32);
        FORMAT_4RCC = r09;
        AnonymousClass34 r113 = new AnonymousClass34("FORMAT_PACKED_SWITCH_PAYLOAD", 33);
        FORMAT_PACKED_SWITCH_PAYLOAD = r113;
        AnonymousClass35 r010 = new AnonymousClass35("FORMAT_SPARSE_SWITCH_PAYLOAD", 34);
        FORMAT_SPARSE_SWITCH_PAYLOAD = r010;
        AnonymousClass36 r114 = new AnonymousClass36("FORMAT_FILL_ARRAY_DATA_PAYLOAD", 35);
        FORMAT_FILL_ARRAY_DATA_PAYLOAD = r114;
        $VALUES = new InstructionCodec[]{r0, r1, r3, r5, r7, r9, r11, r13, r15, r2, r4, r6, r8, r10, r12, r14, r02, r16, r22, r03, r17, r23, r04, r18, r05, r19, r06, r110, r07, r111, r08, r112, r09, r113, r010, r114};
    }

    InstructionCodec(String r1, int r2) {
    }

    /* synthetic */ InstructionCodec(String r1, int r2, AnonymousClass1 r3) {
        this(r1, r2);
    }

    public static /* synthetic */ int access$100(int r0) {
        return byte0(r0);
    }

    public static /* synthetic */ void access$1000(DecodedInstruction r0, CodeOutput r1) {
        encodeRegisterList(r0, r1);
    }

    public static /* synthetic */ DecodedInstruction access$1100(InstructionCodec r0, int r1, CodeInput r2) {
        return decodeRegisterRange(r0, r1, r2);
    }

    public static /* synthetic */ void access$1200(DecodedInstruction r0, CodeOutput r1) {
        encodeRegisterRange(r0, r1);
    }

    public static /* synthetic */ short access$1300(long r0) {
        return unit0(r0);
    }

    public static /* synthetic */ short access$1400(long r0) {
        return unit1(r0);
    }

    public static /* synthetic */ short access$1500(long r0) {
        return unit2(r0);
    }

    public static /* synthetic */ short access$1600(long r0) {
        return unit3(r0);
    }

    public static /* synthetic */ int access$1700(int r0) {
        return nibble0(r0);
    }

    public static /* synthetic */ int access$1800(int r0) {
        return nibble1(r0);
    }

    public static /* synthetic */ short access$1900(int r0, int r1, int r2, int r3) {
        return codeUnit(r0, r1, r2, r3);
    }

    public static /* synthetic */ int access$200(int r0) {
        return byte1(r0);
    }

    public static /* synthetic */ short access$2000(int r0) {
        return asUnsignedUnit(r0);
    }

    public static /* synthetic */ int access$300(int r0) {
        return nibble2(r0);
    }

    public static /* synthetic */ int access$400(int r0) {
        return nibble3(r0);
    }

    public static /* synthetic */ int access$500(int r0, int r1) {
        return makeByte(r0, r1);
    }

    public static /* synthetic */ short access$600(int r0, int r1) {
        return codeUnit(r0, r1);
    }

    public static /* synthetic */ short access$700(int r0) {
        return unit0(r0);
    }

    public static /* synthetic */ short access$800(int r0) {
        return unit1(r0);
    }

    public static /* synthetic */ DecodedInstruction access$900(InstructionCodec r0, int r1, CodeInput r2) {
        return decodeRegisterList(r0, r1, r2);
    }

    private static short asUnsignedUnit(int r1) {
        if (((-65536) & r1) == 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m("bogus unsigned code unit");
        return 0;
    L5:
        return (short) r1;
    }

    private static int byte0(int r0) {
        return r0 & 255;
    }

    private static int byte1(int r0) {
        return (r0 >> 8) & 255;
    }

    private static int byte2(int r0) {
        return (r0 >> 16) & 255;
    }

    private static int byte3(int r0) {
        return r0 >>> 24;
    }

    private static short codeUnit(int r1, int r2) {
        if ((r1 & (-256)) == 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m("bogus lowByte");
        return 0;
    L5:
        if ((r2 & (-256)) == 0) goto L7;
        f$$ExternalSyntheticBUOutline1.m("bogus highByte");
        return 0;
    L7:
        return (short) (r1 | (r2 << 8));
    }

    private static short codeUnit(int r1, int r2, int r3, int r4) {
        if ((r1 & (-16)) == 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m("bogus nibble0");
        return 0;
    L5:
        if ((r2 & (-16)) == 0) goto L7;
        f$$ExternalSyntheticBUOutline1.m("bogus nibble1");
        return 0;
    L7:
        if ((r3 & (-16)) == 0) goto L9;
        f$$ExternalSyntheticBUOutline1.m("bogus nibble2");
        return 0;
    L9:
        if ((r4 & (-16)) == 0) goto L11;
        f$$ExternalSyntheticBUOutline1.m("bogus nibble3");
        return 0;
    L11:
        return (short) (((r1 | (r2 << 4)) | (r3 << 8)) | (r4 << 12));
    }

    private static DecodedInstruction decodeRegisterList(InstructionCodec r13, int r14, CodeInput r15) {
        int r2 = byte0(r14);
        int r12 = nibble2(r14);
        int r142 = nibble3(r14);
        int r3 = r15.read();
        int r152 = r15.read();
        int r8 = nibble0(r152);
        int r9 = nibble1(r152);
        int r10 = nibble2(r152);
        int r11 = nibble3(r152);
        IndexType r4 = OpcodeInfo.getIndexType(r2);
        if (r142 == 0) goto L27;
        if (r142 == 1) goto L25;
        if (r142 == 2) goto L23;
        if (r142 == 3) goto L21;
        if (r142 == 4) goto L19;
        if (r142 == 5) goto L15;
        DexMerger$$ExternalSyntheticBUOutline0.m("bogus registerCount: ", Hex.uNibble(r142));
        return null;
    L15:
        return new FiveRegisterDecodedInstruction(r13, r2, r3, r4, 0, 0, r8, r9, r10, r11, r12);
    L19:
        return new FourRegisterDecodedInstruction(r13, r2, r3, r4, 0, 0, r8, r9, r10, r11);
    L21:
        return new ThreeRegisterDecodedInstruction(r13, r2, r3, r4, 0, 0, r8, r9, r10);
    L23:
        return new TwoRegisterDecodedInstruction(r13, r2, r3, r4, 0, 0, r8, r9);
    L25:
        return new OneRegisterDecodedInstruction(r13, r2, r3, r4, 0, 0, r8);
    L27:
        return new ZeroRegisterDecodedInstruction(r13, r2, r3, r4, 0, 0);
    }

    private static DecodedInstruction decodeRegisterRange(InstructionCodec r10, int r11, CodeInput r12) {
        int r2 = byte0(r11);
        int r9 = byte1(r11);
        int r3 = r12.read();
        int r8 = r12.read();
        return new RegisterRangeDecodedInstruction(r10, r2, r3, OpcodeInfo.getIndexType(r2), 0, 0, r8, r9);
    }

    private static void encodeRegisterList(DecodedInstruction r5, CodeOutput r6) {
        r6.write(codeUnit(r5.getOpcode(), makeByte(r5.getE(), r5.getRegisterCount())), r5.getIndexUnit(), codeUnit(r5.getA(), r5.getB(), r5.getC(), r5.getD()));
    }

    private static void encodeRegisterRange(DecodedInstruction r2, CodeOutput r3) {
        r3.write(codeUnit(r2.getOpcode(), r2.getRegisterCount()), r2.getIndexUnit(), r2.getAUnit());
    }

    private static int makeByte(int r1, int r2) {
        if ((r1 & (-16)) == 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m("bogus lowNibble");
        return 0;
    L5:
        if ((r2 & (-16)) == 0) goto L7;
        f$$ExternalSyntheticBUOutline1.m("bogus highNibble");
        return 0;
    L7:
        return r1 | (r2 << 4);
    }

    private static int nibble0(int r0) {
        return r0 & 15;
    }

    private static int nibble1(int r0) {
        return (r0 >> 4) & 15;
    }

    private static int nibble2(int r0) {
        return (r0 >> 8) & 15;
    }

    private static int nibble3(int r0) {
        return (r0 >> 12) & 15;
    }

    private static short unit0(int r0) {
        return (short) r0;
    }

    private static short unit0(long r0) {
        return (short) r0;
    }

    private static short unit1(int r0) {
        return (short) (r0 >> 16);
    }

    private static short unit1(long r1) {
        return (short) (r1 >> 16);
    }

    private static short unit2(long r1) {
        return (short) (r1 >> 32);
    }

    private static short unit3(long r1) {
        return (short) (r1 >> 48);
    }

    public static InstructionCodec valueOf(String r1) {
        return (InstructionCodec) Enum.valueOf(InstructionCodec.class, r1);
    }

    public static InstructionCodec[] values() {
        return (InstructionCodec[]) $VALUES.clone();
    }

    public abstract DecodedInstruction decode(int r1, CodeInput r2);

    public abstract void encode(DecodedInstruction r1, CodeOutput r2);
}
