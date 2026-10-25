package com.android.dx.rop.type;

import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* loaded from: classes.dex */
public final class Prototype implements Comparable<Prototype> {
    private static final ConcurrentMap<String, Prototype> internTable = null;
    private final String descriptor;
    private StdTypeList parameterFrameTypes;
    private final StdTypeList parameterTypes;
    private final Type returnType;

    static {
        internTable = new ConcurrentHashMap(10000, 0.75f);
    }

    private Prototype(String r2, Type r3, StdTypeList r4) {
        if (r2 == null) goto L12;
        if (r3 == null) goto L10;
        if (r4 == null) goto L8;
        this.descriptor = r2;
        this.returnType = r3;
        this.parameterTypes = r4;
        this.parameterFrameTypes = null;
        return;
    L8:
        f$$ExternalSyntheticBUOutline2.m("parameterTypes == null");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline2.m("returnType == null");
        throw null;
    L12:
        f$$ExternalSyntheticBUOutline2.m("descriptor == null");
        throw null;
    }

    public static void clearInternTable() {
        internTable.clear();
    }

    public static Prototype fromDescriptor(String r8) {
        Prototype r0 = internTable.get(r8);
        if (r0 == null) goto L5;
        return r0;
    L5:
        Type[] r02 = makeParameterArray(r8);
        int r1 = 0;
        int r4 = 0;
        int r3 = 1;
    L6:
        char r5 = r8.charAt(r3);
        if (r5 == ')') goto L8;
        int r6 = r3;
    L15:
        if (r5 != '[') goto L18;
        r6 = r6 + 1;
        r5 = r8.charAt(r6);
        goto L15
    L18:
        if (r5 != 'L') goto L24;
        int r52 = r8.indexOf(59, r6);
        if (r52 == (-1)) goto L22;
        int r53 = r52 + 1;
    L25:
        r02[r4] = Type.intern(r8.substring(r3, r53));
        r4 = r4 + 1;
        r3 = r53;
        goto L6
    L22:
        f$$ExternalSyntheticBUOutline1.m("bad descriptor");
        return null;
    L24:
        r53 = r6 + 1;
        goto L25
    L8:
        Type r2 = Type.internReturnType(r8.substring(r3 + 1));
        StdTypeList r32 = new StdTypeList(r4);
    L9:
        if (r1 >= r4) goto L12;
        r32.set(r1, r02[r1]);
        r1 = r1 + 1;
        goto L9
    L12:
        return new Prototype(r8, r2, r32);
    }

    public static Prototype intern(String r1) {
        if (r1 == null) goto L8;
        Prototype r0 = internTable.get(r1);
        if (r0 == null) goto L7;
        return r0;
    L7:
        return putIntern(fromDescriptor(r1));
    L8:
        f$$ExternalSyntheticBUOutline2.m("descriptor == null");
        return null;
    }

    public static Prototype intern(String r0, Type r1, boolean r2, boolean r3) {
        Prototype r02 = intern(r0);
        if (r2 == false) goto L5;
        return r02;
    L5:
        if (r3 == false) goto L8;
        r1 = r1.asUninitialized(Integer.MAX_VALUE);
    L8:
        return r02.withFirstParameter(r1);
    }

    public static Prototype internInts(Type r3, int r4) {
        StringBuilder r0 = new StringBuilder(100);
        r0.append('(');
        int r1 = 0;
    L3:
        if (r1 >= r4) goto L5;
        r0.append('I');
        r1 = r1 + 1;
        goto L3
    L5:
        r0.append(')');
        r0.append(r3.getDescriptor());
        return intern(r0.toString());
    }

    private static Type[] makeParameterArray(String r9) {
        int r0 = r9.length();
        int r1 = 0;
        if (r9.charAt(0) != '(') goto L27;
        int r6 = 0;
        int r3 = 1;
    L6:
        if (r3 >= r0) goto L16;
        char r8 = r9.charAt(r3);
        if (r8 == ')') goto L9;
        if (r8 < 'A') goto L15;
        if (r8 > 'Z') goto L15;
        r6 = r6 + 1;
    L15:
        r3 = r3 + 1;
        goto L6
    L9:
        r1 = r3;
    L16:
        if (r1 != 0) goto L18;
    L25:
        f$$ExternalSyntheticBUOutline1.m("bad descriptor");
        return null;
    L18:
        if (r1 == (r0 - 1)) goto L25;
        if (r9.indexOf(41, r1 + 1) == (-1)) goto L22;
        f$$ExternalSyntheticBUOutline1.m("bad descriptor");
        return null;
    L22:
        return new Type[r6];
    L27:
        f$$ExternalSyntheticBUOutline1.m("bad descriptor");
        return null;
    }

    private static Prototype putIntern(Prototype r2) {
        Prototype r0 = internTable.putIfAbsent(r2.getDescriptor(), r2);
        if (r0 == null) goto L5;
        return r0;
    L5:
        return r2;
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(Prototype r8) {
        if (this != r8) goto L5;
        return 0;
    L5:
        int r1 = this.returnType.compareTo2(r8.returnType);
        if (r1 == 0) goto L8;
        return r1;
    L8:
        int r12 = this.parameterTypes.size();
        int r2 = r8.parameterTypes.size();
        int r3 = Math.min(r12, r2);
        int r4 = 0;
    L9:
        if (r4 >= r3) goto L14;
        int r5 = this.parameterTypes.get(r4).compareTo2(r8.parameterTypes.get(r4));
        if (r5 != 0) goto L12;
        r4 = r4 + 1;
        goto L9
    L12:
        return r5;
    L14:
        if (r12 >= r2) goto L17;
        return -1;
    L17:
        if (r12 <= r2) goto L20;
        return 1;
    L20:
        return 0;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Prototype r1) {
        return compareTo2(r1);
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof Prototype) == true) goto L10;
        return false;
    L10:
        return this.descriptor.equals(((Prototype) r2).descriptor);
    }

    public String getDescriptor() {
        return this.descriptor;
    }

    public StdTypeList getParameterFrameTypes() {
        if (this.parameterFrameTypes != null) goto L15;
        int r0 = this.parameterTypes.size();
        StdTypeList r1 = new StdTypeList(r0);
        int r2 = 0;
        boolean r3 = false;
    L5:
        if (r2 >= r0) goto L10;
        Type r4 = this.parameterTypes.get(r2);
        if (r4.isIntlike() == false) goto L9;
        r4 = Type.INT;
        r3 = true;
    L9:
        r1.set(r2, r4);
        r2 = r2 + 1;
        goto L5
    L10:
        if (r3 == true) goto L13;
        r1 = this.parameterTypes;
    L13:
        this.parameterFrameTypes = r1;
    L15:
        return this.parameterFrameTypes;
    }

    public StdTypeList getParameterTypes() {
        return this.parameterTypes;
    }

    public Type getReturnType() {
        return this.returnType;
    }

    public int hashCode() {
        return this.descriptor.hashCode();
    }

    public String toString() {
        return this.descriptor;
    }

    public Prototype withFirstParameter(Type r4) {
        String r0 = "(" + r4.getDescriptor() + this.descriptor.substring(1);
        StdTypeList r42 = this.parameterTypes.withFirst(r4);
        r42.setImmutable();
        return putIntern(new Prototype(r0, this.returnType, r42));
    }
}
