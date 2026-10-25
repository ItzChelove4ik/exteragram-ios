package com.android.dx.rop.code;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeBearer;
import com.android.dx.util.ToHuman;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public final class RegisterSpec implements TypeBearer, ToHuman, Comparable<RegisterSpec> {
    public static final String PREFIX = "v";
    private static final ThreadLocal<ForComparison> theInterningItem = null;
    private static final ConcurrentHashMap<Object, RegisterSpec> theInterns = null;
    private final LocalItem local;
    private final int reg;
    private final TypeBearer type;

    /* renamed from: com.android.dx.rop.code.RegisterSpec$1, reason: invalid class name */
    public static class AnonymousClass1 extends ThreadLocal<ForComparison> {
        public AnonymousClass1() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public ForComparison initialValue() {
            return new ForComparison(null);
        }

        @Override // java.lang.ThreadLocal
        public /* bridge */ /* synthetic */ ForComparison initialValue() {
            return initialValue();
        }
    }

    public static class ForComparison {
        private LocalItem local;
        private int reg;
        private TypeBearer type;

        private ForComparison() {
        }

        public /* synthetic */ ForComparison(AnonymousClass1 r1) {
            this();
        }

        public static /* synthetic */ int access$100(ForComparison r0) {
            return r0.reg;
        }

        public static /* synthetic */ TypeBearer access$200(ForComparison r0) {
            return r0.type;
        }

        public static /* synthetic */ LocalItem access$300(ForComparison r0) {
            return r0.local;
        }

        public boolean equals(Object r3) {
            if ((r3 instanceof RegisterSpec) == true) goto L7;
            return false;
        L7:
            return RegisterSpec.access$500((RegisterSpec) r3, this.reg, this.type, this.local);
        }

        public int hashCode() {
            return RegisterSpec.access$600(this.reg, this.type, this.local);
        }

        public void set(int r1, TypeBearer r2, LocalItem r3) {
            this.reg = r1;
            this.type = r2;
            this.local = r3;
        }

        public RegisterSpec toRegisterSpec() {
            return new RegisterSpec(this.reg, this.type, this.local, null);
        }
    }

    static {
        theInterns = new ConcurrentHashMap(10000, 0.75f);
        theInterningItem = new AnonymousClass1();
    }

    private RegisterSpec(int r2, TypeBearer r3, LocalItem r4) {
        if (r2 < 0) goto L9;
        if (r3 == null) goto L7;
        this.reg = r2;
        this.type = r3;
        this.local = r4;
        return;
    L7:
        f$$ExternalSyntheticBUOutline2.m("type == null");
        throw null;
    L9:
        f$$ExternalSyntheticBUOutline1.m("reg < 0");
        throw null;
    }

    public /* synthetic */ RegisterSpec(int r1, TypeBearer r2, LocalItem r3, AnonymousClass1 r4) {
        this(r1, r2, r3);
    }

    public static /* synthetic */ boolean access$500(RegisterSpec r0, int r1, TypeBearer r2, LocalItem r3) {
        return r0.equals(r1, r2, r3);
    }

    public static /* synthetic */ int access$600(int r0, TypeBearer r1, LocalItem r2) {
        return hashCodeOf(r0, r1, r2);
    }

    public static void clearInternTable() {
        theInterns.clear();
    }

    private boolean equals(int r2, TypeBearer r3, LocalItem r4) {
        if (this.reg == r2) goto L5;
        return false;
    L5:
        if (this.type.equals(r3) == false) goto L16;
        LocalItem r1 = this.local;
        if (r1 == r4) goto L11;
        if (r1 != null) goto L10;
        return false;
    L10:
        if (r1.equals(r4) == false) goto L18;
        return true;
    L18:
        return false;
    L11:
        return true;
    L16:
        return false;
    }

    private static int hashCodeOf(int r0, TypeBearer r1, LocalItem r2) {
        if (r2 == null) goto L4;
        int r22 = r2.hashCode();
    L6:
        return (((r22 * 31) + r1.hashCode()) * 31) + r0;
    L4:
        r22 = 0;
        goto L6
    }

    private static RegisterSpec intern(int r1, TypeBearer r2, LocalItem r3) {
        ForComparison r0 = theInterningItem.get();
        r0.set(r1, r2, r3);
        ConcurrentHashMap<Object, RegisterSpec> r12 = theInterns;
        RegisterSpec r22 = r12.get(r0);
        if (r22 != null) goto L7;
        r22 = r0.toRegisterSpec();
        RegisterSpec r13 = r12.putIfAbsent(r22, r22);
        if (r13 == null) goto L7;
        return r13;
    L7:
        return r22;
    }

    public static RegisterSpec make(int r1, TypeBearer r2) {
        return intern(r1, r2, null);
    }

    public static RegisterSpec make(int r0, TypeBearer r1, LocalItem r2) {
        if (r2 != null) goto L4;
        f$$ExternalSyntheticBUOutline2.m("local  == null");
        return null;
    L4:
        return intern(r0, r1, r2);
    }

    public static RegisterSpec makeLocalOptional(int r0, TypeBearer r1, LocalItem r2) {
        return intern(r0, r1, r2);
    }

    public static String regString(int r2) {
        return PREFIX + r2;
    }

    private String toString0(boolean r4) {
        StringBuilder r0 = new StringBuilder(40);
        r0.append(regString());
        r0.append(":");
        LocalItem r1 = this.local;
        if (r1 == null) goto L5;
        r0.append(r1.toString());
    L5:
        Type r12 = this.type.getType();
        r0.append(r12);
        if (r12 == this.type) goto L18;
        r0.append("=");
        if (r4 == false) goto L12;
        TypeBearer r13 = this.type;
        if ((r13 instanceof CstString) == false) goto L12;
        r0.append(((CstString) r13).toQuoted());
    L12:
        if (r4 == false) goto L16;
        TypeBearer r42 = this.type;
        if ((r42 instanceof Constant) == false) goto L16;
        r0.append(r42.toHuman());
    L16:
        r0.append(this.type);
    L18:
        return r0.toString();
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(RegisterSpec r6) {
        int r0 = this.reg;
        int r1 = r6.reg;
        if (r0 >= r1) goto L6;
        return -1;
    L6:
        if (r0 <= r1) goto L9;
        return 1;
    L9:
        if (this != r6) goto L11;
        return 0;
    L11:
        int r12 = this.type.getType().compareTo2(r6.type.getType());
        if (r12 == 0) goto L14;
        return r12;
    L14:
        LocalItem r5 = this.local;
        LocalItem r62 = r6.local;
        if (r5 != null) goto L19;
        if (r62 != null) goto L18;
        return 0;
    L18:
        return -1;
    L19:
        if (r62 != null) goto L22;
        return 1;
    L22:
        return r5.compareTo2(r62);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(RegisterSpec r1) {
        return compareTo2(r1);
    }

    public boolean equals(Object r3) {
        if (this != r3) goto L6;
        return true;
    L6:
        if ((r3 instanceof RegisterSpec) == false) goto L8;
        RegisterSpec r32 = (RegisterSpec) r3;
        return equals(r32.reg, r32.type, r32.local);
    L8:
        if ((r3 instanceof ForComparison) == false) goto L11;
        ForComparison r33 = (ForComparison) r3;
        return equals(ForComparison.access$100(r33), ForComparison.access$200(r33), ForComparison.access$300(r33));
    L11:
        return false;
    }

    public boolean equalsUsingSimpleType(RegisterSpec r3) {
        if (matchesVariable(r3) == true) goto L6;
        return false;
    L6:
        if (this.reg != r3.reg) goto L9;
        return true;
    L9:
        return false;
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public final int getBasicFrameType() {
        return this.type.getBasicFrameType();
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public final int getBasicType() {
        return this.type.getBasicType();
    }

    public int getCategory() {
        return this.type.getType().getCategory();
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public TypeBearer getFrameType() {
        return this.type.getFrameType();
    }

    public LocalItem getLocalItem() {
        return this.local;
    }

    public int getNextReg() {
        return this.reg + getCategory();
    }

    public int getReg() {
        return this.reg;
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public Type getType() {
        return this.type.getType();
    }

    public TypeBearer getTypeBearer() {
        return this.type;
    }

    public int hashCode() {
        return hashCodeOf(this.reg, this.type, this.local);
    }

    public RegisterSpec intersect(RegisterSpec r5, boolean r6) {
        if (this != r5) goto L5;
    L32:
        return this;
    L5:
        if (r5 != null) goto L7;
    L39:
        return null;
    L7:
        if (this.reg != r5.getReg()) goto L39;
        LocalItem r1 = this.local;
        if (r1 != null) goto L12;
    L15:
        LocalItem r12 = null;
    L17:
        if (r12 != this.local) goto L19;
        boolean r2 = true;
    L20:
        if (r6 == false) goto L23;
        if (r2 == true) goto L23;
        return null;
    L23:
        TypeBearer r62 = getType();
        if (r62 == r5.getType()) goto L27;
        return null;
    L27:
        if (this.type.equals(r5.getTypeBearer()) == false) goto L30;
        r62 = this.type;
    L30:
        if (r62 != this.type) goto L33;
        if (r2 == true) goto L32;
    L33:
        int r4 = this.reg;
        if (r12 != null) goto L38;
        return make(r4, r62);
    L38:
        return make(r4, r62, r12);
    L19:
        r2 = false;
        goto L20
    L12:
        if (r1.equals(r5.getLocalItem()) == false) goto L15;
        r12 = this.local;
        goto L17
    }

    public boolean isCategory1() {
        return this.type.getType().isCategory1();
    }

    public boolean isCategory2() {
        return this.type.getType().isCategory2();
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public final boolean isConstant() {
        return false;
    }

    public boolean isEvenRegister() {
        if ((getReg() & 1) != 0) goto L5;
        return true;
    L5:
        return false;
    }

    public boolean matchesVariable(RegisterSpec r4) {
        if (r4 != null) goto L6;
        return false;
    L6:
        if (this.type.getType().equals(r4.type.getType()) == false) goto L14;
        LocalItem r3 = this.local;
        LocalItem r42 = r4.local;
        if (r3 == r42) goto L12;
        if (r3 == null) goto L14;
        if (r3.equals(r42) == false) goto L14;
        return true;
    L12:
        return true;
    L14:
        return false;
    }

    public String regString() {
        return regString(this.reg);
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        return toString0(true);
    }

    public String toString() {
        return toString0(false);
    }

    public RegisterSpec withLocalItem(LocalItem r2) {
        LocalItem r0 = this.local;
        if (r0 == r2) goto L10;
        if (r0 == null) goto L9;
        if (r0.equals(r2) == false) goto L9;
        return this;
    L9:
        return makeLocalOptional(this.reg, this.type, r2);
    L10:
        return this;
    }

    public RegisterSpec withOffset(int r2) {
        if (r2 != 0) goto L5;
        return this;
    L5:
        return withReg(this.reg + r2);
    }

    public RegisterSpec withReg(int r2) {
        if (this.reg != r2) goto L6;
        return this;
    L6:
        return makeLocalOptional(r2, this.type, this.local);
    }

    public RegisterSpec withSimpleType() {
        TypeBearer r0 = this.type;
        if ((r0 instanceof Type) == false) goto L5;
        Type r1 = (Type) r0;
    L7:
        if (r1.isUninitialized() == false) goto L9;
        r1 = r1.getInitializedType();
    L9:
        if (r1 != r0) goto L12;
        return this;
    L12:
        return makeLocalOptional(this.reg, r1, this.local);
    L5:
        r1 = r0.getType();
        goto L7
    }

    public RegisterSpec withType(TypeBearer r2) {
        return makeLocalOptional(this.reg, r2, this.local);
    }
}
