package com.android.dx;

import com.android.dx.rop.code.Rop;
import com.android.dx.rop.code.Rops;

/* loaded from: classes.dex */
public enum UnaryOp extends Enum<UnaryOp> {
    private static final /* synthetic */ UnaryOp[] $VALUES = null;
    public static final UnaryOp NEGATE = null;
    public static final UnaryOp NOT = null;

    /* renamed from: com.android.dx.UnaryOp$1, reason: invalid class name */
    /* loaded from: classes4.dex */
    public enum AnonymousClass1 extends UnaryOp {
        public AnonymousClass1(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.UnaryOp
        public Rop rop(TypeId<?> r1) {
            return Rops.opNot(r1.ropType);
        }
    }

    /* renamed from: com.android.dx.UnaryOp$2, reason: invalid class name */
    /* loaded from: classes4.dex */
    public enum AnonymousClass2 extends UnaryOp {
        public AnonymousClass2(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.UnaryOp
        public Rop rop(TypeId<?> r1) {
            return Rops.opNeg(r1.ropType);
        }
    }

    private static /* synthetic */ UnaryOp[] $values() {
        return new UnaryOp[]{NOT, NEGATE};
    }

    static {
        NOT = new AnonymousClass1("NOT", 0);
        NEGATE = new AnonymousClass2("NEGATE", 1);
        $VALUES = $values();
    }

    UnaryOp(String r1, int r2) {
    }

    /* synthetic */ UnaryOp(String r1, int r2, AnonymousClass1 r3) {
        this(r1, r2);
    }

    public static UnaryOp valueOf(String r1) {
        return (UnaryOp) Enum.valueOf(UnaryOp.class, r1);
    }

    public static UnaryOp[] values() {
        return (UnaryOp[]) $VALUES.clone();
    }

    public abstract Rop rop(TypeId<?> r1);
}
