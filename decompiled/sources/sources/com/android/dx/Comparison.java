package com.android.dx;

import com.android.dx.rop.code.Rop;
import com.android.dx.rop.code.Rops;

/* loaded from: classes.dex */
public enum Comparison extends Enum<Comparison> {
    private static final /* synthetic */ Comparison[] $VALUES = null;
    public static final Comparison EQ = null;
    public static final Comparison GE = null;
    public static final Comparison GT = null;
    public static final Comparison LE = null;
    public static final Comparison LT = null;
    public static final Comparison NE = null;

    /* renamed from: com.android.dx.Comparison$1, reason: invalid class name */
    public enum AnonymousClass1 extends Comparison {
        public AnonymousClass1(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.Comparison
        public Rop rop(com.android.dx.rop.type.TypeList r1) {
            return Rops.opIfLt(r1);
        }
    }

    /* renamed from: com.android.dx.Comparison$2, reason: invalid class name */
    public enum AnonymousClass2 extends Comparison {
        public AnonymousClass2(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.Comparison
        public Rop rop(com.android.dx.rop.type.TypeList r1) {
            return Rops.opIfLe(r1);
        }
    }

    /* renamed from: com.android.dx.Comparison$3, reason: invalid class name */
    public enum AnonymousClass3 extends Comparison {
        public AnonymousClass3(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.Comparison
        public Rop rop(com.android.dx.rop.type.TypeList r1) {
            return Rops.opIfEq(r1);
        }
    }

    /* renamed from: com.android.dx.Comparison$4, reason: invalid class name */
    public enum AnonymousClass4 extends Comparison {
        public AnonymousClass4(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.Comparison
        public Rop rop(com.android.dx.rop.type.TypeList r1) {
            return Rops.opIfGe(r1);
        }
    }

    /* renamed from: com.android.dx.Comparison$5, reason: invalid class name */
    public enum AnonymousClass5 extends Comparison {
        public AnonymousClass5(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.Comparison
        public Rop rop(com.android.dx.rop.type.TypeList r1) {
            return Rops.opIfGt(r1);
        }
    }

    /* renamed from: com.android.dx.Comparison$6, reason: invalid class name */
    public enum AnonymousClass6 extends Comparison {
        public AnonymousClass6(String r2, int r3) {
            super(r2, r3, null);
        }

        @Override // com.android.dx.Comparison
        public Rop rop(com.android.dx.rop.type.TypeList r1) {
            return Rops.opIfNe(r1);
        }
    }

    private static /* synthetic */ Comparison[] $values() {
        return new Comparison[]{LT, LE, EQ, GE, GT, NE};
    }

    static {
        LT = new AnonymousClass1("LT", 0);
        LE = new AnonymousClass2("LE", 1);
        EQ = new AnonymousClass3("EQ", 2);
        GE = new AnonymousClass4("GE", 3);
        GT = new AnonymousClass5("GT", 4);
        NE = new AnonymousClass6("NE", 5);
        $VALUES = $values();
    }

    Comparison(String r1, int r2) {
    }

    /* synthetic */ Comparison(String r1, int r2, AnonymousClass1 r3) {
        this(r1, r2);
    }

    public static Comparison valueOf(String r1) {
        return (Comparison) Enum.valueOf(Comparison.class, r1);
    }

    public static Comparison[] values() {
        return (Comparison[]) $VALUES.clone();
    }

    public abstract Rop rop(com.android.dx.rop.type.TypeList r1);
}
