package com.android.dx;

import com.android.dx.rop.code.Rop;
import com.android.dx.rop.code.Rops;

/* loaded from: classes.dex */
public enum BinaryOp extends Enum<BinaryOp> {
    private static final /* synthetic */ BinaryOp[] $VALUES = null;
    public static final BinaryOp ADD = null;
    public static final BinaryOp AND = null;
    public static final BinaryOp DIVIDE = null;
    public static final BinaryOp MULTIPLY = null;
    public static final BinaryOp OR = null;
    public static final BinaryOp REMAINDER = null;
    public static final BinaryOp SHIFT_LEFT = null;
    public static final BinaryOp SHIFT_RIGHT = null;
    public static final BinaryOp SUBTRACT = null;
    public static final BinaryOp UNSIGNED_SHIFT_RIGHT = null;
    public static final BinaryOp XOR = null;

    /* renamed from: com.android.dx.BinaryOp$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public enum AnonymousClass1 extends BinaryOp {
        public AnonymousClass1(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.BinaryOp
        public Rop rop(com.android.dx.rop.type.TypeList r1) {
            return Rops.opAdd(r1);
        }
    }

    /* renamed from: com.android.dx.BinaryOp$10, reason: invalid class name */
    /* loaded from: classes4.dex */
    public enum AnonymousClass10 extends BinaryOp {
        public AnonymousClass10(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.BinaryOp
        public Rop rop(com.android.dx.rop.type.TypeList r1) {
            return Rops.opShr(r1);
        }
    }

    /* renamed from: com.android.dx.BinaryOp$11, reason: invalid class name */
    /* loaded from: classes4.dex */
    public enum AnonymousClass11 extends BinaryOp {
        public AnonymousClass11(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.BinaryOp
        public Rop rop(com.android.dx.rop.type.TypeList r1) {
            return Rops.opUshr(r1);
        }
    }

    /* renamed from: com.android.dx.BinaryOp$2, reason: invalid class name */
    /* loaded from: classes4.dex */
    public enum AnonymousClass2 extends BinaryOp {
        public AnonymousClass2(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.BinaryOp
        public Rop rop(com.android.dx.rop.type.TypeList r1) {
            return Rops.opSub(r1);
        }
    }

    /* renamed from: com.android.dx.BinaryOp$3, reason: invalid class name */
    /* loaded from: classes4.dex */
    public enum AnonymousClass3 extends BinaryOp {
        public AnonymousClass3(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.BinaryOp
        public Rop rop(com.android.dx.rop.type.TypeList r1) {
            return Rops.opMul(r1);
        }
    }

    /* renamed from: com.android.dx.BinaryOp$4, reason: invalid class name */
    /* loaded from: classes4.dex */
    public enum AnonymousClass4 extends BinaryOp {
        public AnonymousClass4(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.BinaryOp
        public Rop rop(com.android.dx.rop.type.TypeList r1) {
            return Rops.opDiv(r1);
        }
    }

    /* renamed from: com.android.dx.BinaryOp$5, reason: invalid class name */
    /* loaded from: classes4.dex */
    public enum AnonymousClass5 extends BinaryOp {
        public AnonymousClass5(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.BinaryOp
        public Rop rop(com.android.dx.rop.type.TypeList r1) {
            return Rops.opRem(r1);
        }
    }

    /* renamed from: com.android.dx.BinaryOp$6, reason: invalid class name */
    /* loaded from: classes4.dex */
    public enum AnonymousClass6 extends BinaryOp {
        public AnonymousClass6(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.BinaryOp
        public Rop rop(com.android.dx.rop.type.TypeList r1) {
            return Rops.opAnd(r1);
        }
    }

    /* renamed from: com.android.dx.BinaryOp$7, reason: invalid class name */
    /* loaded from: classes4.dex */
    public enum AnonymousClass7 extends BinaryOp {
        public AnonymousClass7(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.BinaryOp
        public Rop rop(com.android.dx.rop.type.TypeList r1) {
            return Rops.opOr(r1);
        }
    }

    /* renamed from: com.android.dx.BinaryOp$8, reason: invalid class name */
    /* loaded from: classes4.dex */
    public enum AnonymousClass8 extends BinaryOp {
        public AnonymousClass8(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.BinaryOp
        public Rop rop(com.android.dx.rop.type.TypeList r1) {
            return Rops.opXor(r1);
        }
    }

    /* renamed from: com.android.dx.BinaryOp$9, reason: invalid class name */
    /* loaded from: classes4.dex */
    public enum AnonymousClass9 extends BinaryOp {
        public AnonymousClass9(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.BinaryOp
        public Rop rop(com.android.dx.rop.type.TypeList r1) {
            return Rops.opShl(r1);
        }
    }

    private static /* synthetic */ BinaryOp[] $values() {
        return new BinaryOp[]{ADD, SUBTRACT, MULTIPLY, DIVIDE, REMAINDER, AND, OR, XOR, SHIFT_LEFT, SHIFT_RIGHT, UNSIGNED_SHIFT_RIGHT};
    }

    static {
        ADD = new AnonymousClass1("ADD", 0);
        SUBTRACT = new AnonymousClass2("SUBTRACT", 1);
        MULTIPLY = new AnonymousClass3("MULTIPLY", 2);
        DIVIDE = new AnonymousClass4("DIVIDE", 3);
        REMAINDER = new AnonymousClass5("REMAINDER", 4);
        AND = new AnonymousClass6("AND", 5);
        OR = new AnonymousClass7("OR", 6);
        XOR = new AnonymousClass8("XOR", 7);
        SHIFT_LEFT = new AnonymousClass9("SHIFT_LEFT", 8);
        SHIFT_RIGHT = new AnonymousClass10("SHIFT_RIGHT", 9);
        UNSIGNED_SHIFT_RIGHT = new AnonymousClass11("UNSIGNED_SHIFT_RIGHT", 10);
        $VALUES = $values();
    }

    BinaryOp(String r1, int r2) {
    }

    /* synthetic */ BinaryOp(String r1, int r2, AnonymousClass1 r3) {
        this(r1, r2);
    }

    public static BinaryOp valueOf(String r1) {
        return (BinaryOp) Enum.valueOf(BinaryOp.class, r1);
    }

    public static BinaryOp[] values() {
        return (BinaryOp[]) $VALUES.clone();
    }

    public abstract Rop rop(com.android.dx.rop.type.TypeList r1);
}
