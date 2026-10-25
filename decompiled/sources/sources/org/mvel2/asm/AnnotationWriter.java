package org.mvel2.asm;

import com.android.dx.cf.attrib.AttRuntimeInvisibleAnnotations;
import com.android.dx.cf.attrib.AttRuntimeVisibleAnnotations;

/* loaded from: classes5.dex */
final class AnnotationWriter extends AnnotationVisitor {
    private final ByteVector annotation;
    private AnnotationWriter nextAnnotation;
    private int numElementValuePairs;
    private final int numElementValuePairsOffset;
    private final AnnotationWriter previousAnnotation;
    private final SymbolTable symbolTable;
    private final boolean useNamedValues;

    public AnnotationWriter(SymbolTable r2, boolean r3, ByteVector r4, AnnotationWriter r5) {
        super(Opcodes.ASM9);
        this.symbolTable = r2;
        this.useNamedValues = r3;
        this.annotation = r4;
        int r22 = r4.length;
        if (r22 != 0) goto L5;
        int r23 = -1;
    L6:
        this.numElementValuePairsOffset = r23;
        this.previousAnnotation = r5;
        if (r5 == null) goto L10;
        r5.nextAnnotation = this;
        return;
    L10:
        return;
    L5:
        r23 = r22 - 2;
        goto L6
    }

    public static int computeAnnotationsSize(AnnotationWriter r1, AnnotationWriter r2, AnnotationWriter r3, AnnotationWriter r4) {
        if (r1 == null) goto L4;
        int r12 = r1.computeAnnotationsSize(AttRuntimeVisibleAnnotations.ATTRIBUTE_NAME);
    L5:
        if (r2 == null) goto L7;
        r12 = r12 + r2.computeAnnotationsSize(AttRuntimeInvisibleAnnotations.ATTRIBUTE_NAME);
    L7:
        if (r3 == null) goto L9;
        r12 = r12 + r3.computeAnnotationsSize("RuntimeVisibleTypeAnnotations");
    L9:
        if (r4 != null) goto L11;
        return r12;
    L11:
        return r12 + r4.computeAnnotationsSize("RuntimeInvisibleTypeAnnotations");
    L4:
        r12 = 0;
        goto L5
    }

    public static int computeParameterAnnotationsSize(String r4, AnnotationWriter[] r5, int r6) {
        int r0 = (r6 * 2) + 7;
        int r2 = 0;
    L3:
        if (r2 >= r6) goto L9;
        AnnotationWriter r3 = r5[r2];
        if (r3 != null) goto L7;
        int r32 = 0;
    L8:
        r0 = r0 + r32;
        r2 = r2 + 1;
        goto L3
    L7:
        r32 = r3.computeAnnotationsSize(r4) - 8;
        goto L8
    L9:
        return r0;
    }

    public static AnnotationWriter create(SymbolTable r1, int r2, TypePath r3, String r4, AnnotationWriter r5) {
        ByteVector r0 = new ByteVector();
        TypeReference.putTarget(r2, r0);
        TypePath.put(r3, r0);
        r0.putShort(r1.addConstantUtf8(r4)).putShort(0);
        return new AnnotationWriter(r1, true, r0, r5);
    }

    public static AnnotationWriter create(SymbolTable r2, String r3, AnnotationWriter r4) {
        ByteVector r0 = new ByteVector();
        r0.putShort(r2.addConstantUtf8(r3)).putShort(0);
        return new AnnotationWriter(r2, true, r0, r4);
    }

    public static void putAnnotations(SymbolTable r1, AnnotationWriter r2, AnnotationWriter r3, AnnotationWriter r4, AnnotationWriter r5, ByteVector r6) {
        if (r2 == null) goto L4;
        r2.putAnnotations(r1.addConstantUtf8(AttRuntimeVisibleAnnotations.ATTRIBUTE_NAME), r6);
    L4:
        if (r3 == null) goto L6;
        r3.putAnnotations(r1.addConstantUtf8(AttRuntimeInvisibleAnnotations.ATTRIBUTE_NAME), r6);
    L6:
        if (r4 == null) goto L8;
        r4.putAnnotations(r1.addConstantUtf8("RuntimeVisibleTypeAnnotations"), r6);
    L8:
        if (r5 == null) goto L11;
        r5.putAnnotations(r1.addConstantUtf8("RuntimeInvisibleTypeAnnotations"), r6);
        return;
    }

    public static void putParameterAnnotations(int r6, AnnotationWriter[] r7, int r8, ByteVector r9) {
        int r0 = (r8 * 2) + 1;
        int r2 = 0;
    L4:
        if (r2 >= r8) goto L10;
        AnnotationWriter r4 = r7[r2];
        if (r4 != null) goto L8;
        int r3 = 0;
    L9:
        r0 = r0 + r3;
        r2 = r2 + 1;
        goto L4
    L8:
        r3 = r4.computeAnnotationsSize(null) - 8;
        goto L9
    L10:
        r9.putShort(r6);
        r9.putInt(r0);
        r9.putByte(r8);
        int r62 = 0;
    L11:
        if (r62 >= r8) goto L19;
        AnnotationWriter r02 = r7[r62];
        int r22 = 0;
        AnnotationWriter r42 = null;
    L13:
        if (r02 == null) goto L15;
        r02.visitEnd();
        r22 = r22 + 1;
        r42 = r02;
        r02 = r02.previousAnnotation;
        goto L13
    L15:
        r9.putShort(r22);
    L16:
        if (r42 == null) goto L18;
        ByteVector r03 = r42.annotation;
        r9.putByteArray(r03.data, 0, r03.length);
        r42 = r42.nextAnnotation;
        goto L16
    L18:
        r62 = r62 + 1;
        goto L11
    }

    public int computeAnnotationsSize(String r2) {
        if (r2 == null) goto L4;
        this.symbolTable.addConstantUtf8(r2);
    L4:
        int r22 = 8;
    L5:
        if (this == null) goto L7;
        r22 = r22 + this.annotation.length;
        AnnotationWriter this = this.previousAnnotation;
        goto L5
    L7:
        return r22;
    }

    public void putAnnotations(int r6, ByteVector r7) {
        int r0 = 2;
        AnnotationWriter r2 = null;
        int r3 = 0;
    L3:
        if (this == null) goto L5;
        this.visitEnd();
        r0 = r0 + this.annotation.length;
        r3 = r3 + 1;
        r2 = this;
        AnnotationWriter this = this.previousAnnotation;
        goto L3
    L5:
        r7.putShort(r6);
        r7.putInt(r0);
        r7.putShort(r3);
    L6:
        if (r2 == null) goto L8;
        ByteVector r5 = r2.annotation;
        r7.putByteArray(r5.data, 0, r5.length);
        r2 = r2.nextAnnotation;
        goto L6
    }

    @Override // org.mvel2.asm.AnnotationVisitor
    public void visit(String r7, Object r8) {
        this.numElementValuePairs++;
        if (this.useNamedValues == false) goto L6;
        this.annotation.putShort(this.symbolTable.addConstantUtf8(r7));
    L6:
        if ((r8 instanceof String) == false) goto L10;
        this.annotation.put12(115, this.symbolTable.addConstantUtf8((String) r8));
        return;
    L10:
        if ((r8 instanceof Byte) == false) goto L14;
        this.annotation.put12(66, this.symbolTable.addConstantInteger(((Byte) r8).byteValue()).index);
        return;
    L14:
        if ((r8 instanceof Boolean) == false) goto L18;
        this.annotation.put12(90, this.symbolTable.addConstantInteger(((Boolean) r8).booleanValue() ? 1 : 0).index);
        return;
    L18:
        if ((r8 instanceof Character) == false) goto L22;
        this.annotation.put12(67, this.symbolTable.addConstantInteger(((Character) r8).charValue()).index);
        return;
    L22:
        if ((r8 instanceof Short) == false) goto L26;
        this.annotation.put12(83, this.symbolTable.addConstantInteger(((Short) r8).shortValue()).index);
        return;
    L26:
        if ((r8 instanceof Type) == false) goto L29;
        this.annotation.put12(99, this.symbolTable.addConstantUtf8(((Type) r8).getDescriptor()));
        return;
    L29:
        int r4 = 0;
        if ((r8 instanceof byte[]) == false) goto L35;
        byte[] r82 = (byte[]) r8;
        this.annotation.put12(91, r82.length);
        int r72 = r82.length;
    L32:
        if (r4 >= r72) goto L69;
        this.annotation.put12(66, this.symbolTable.addConstantInteger(r82[r4]).index);
        r4 = r4 + 1;
        goto L32
    L69:
        return;
    L35:
        if ((r8 instanceof boolean[]) == false) goto L40;
        boolean[] r83 = (boolean[]) r8;
        this.annotation.put12(91, r83.length);
        int r73 = r83.length;
    L37:
        if (r4 >= r73) goto L80;
        this.annotation.put12(90, this.symbolTable.addConstantInteger(r83[r4] ? 1 : 0).index);
        r4 = r4 + 1;
        goto L37
    L80:
        return;
    L40:
        if ((r8 instanceof short[]) == false) goto L45;
        short[] r84 = (short[]) r8;
        this.annotation.put12(91, r84.length);
        int r74 = r84.length;
    L42:
        if (r4 >= r74) goto L81;
        this.annotation.put12(83, this.symbolTable.addConstantInteger(r84[r4]).index);
        r4 = r4 + 1;
        goto L42
    L81:
        return;
    L45:
        if ((r8 instanceof char[]) == false) goto L50;
        char[] r85 = (char[]) r8;
        this.annotation.put12(91, r85.length);
        int r75 = r85.length;
    L47:
        if (r4 >= r75) goto L82;
        this.annotation.put12(67, this.symbolTable.addConstantInteger(r85[r4]).index);
        r4 = r4 + 1;
        goto L47
    L82:
        return;
    L50:
        if ((r8 instanceof int[]) == false) goto L55;
        int[] r86 = (int[]) r8;
        this.annotation.put12(91, r86.length);
        int r76 = r86.length;
    L52:
        if (r4 >= r76) goto L83;
        this.annotation.put12(73, this.symbolTable.addConstantInteger(r86[r4]).index);
        r4 = r4 + 1;
        goto L52
    L83:
        return;
    L55:
        if ((r8 instanceof long[]) == false) goto L60;
        long[] r87 = (long[]) r8;
        this.annotation.put12(91, r87.length);
        int r77 = r87.length;
    L57:
        if (r4 >= r77) goto L84;
        this.annotation.put12(74, this.symbolTable.addConstantLong(r87[r4]).index);
        r4 = r4 + 1;
        goto L57
    L84:
        return;
    L60:
        if ((r8 instanceof float[]) == false) goto L65;
        float[] r88 = (float[]) r8;
        this.annotation.put12(91, r88.length);
        int r78 = r88.length;
    L62:
        if (r4 >= r78) goto L85;
        this.annotation.put12(70, this.symbolTable.addConstantFloat(r88[r4]).index);
        r4 = r4 + 1;
        goto L62
    L85:
        return;
    L65:
        if ((r8 instanceof double[]) == false) goto L70;
        double[] r89 = (double[]) r8;
        this.annotation.put12(91, r89.length);
        int r79 = r89.length;
    L67:
        if (r4 >= r79) goto L86;
        this.annotation.put12(68, this.symbolTable.addConstantDouble(r89[r4]).index);
        r4 = r4 + 1;
        goto L67
    L86:
        return;
    L70:
        Symbol r710 = this.symbolTable.addConstant(r8);
        this.annotation.put12(".s.IFJDCS".charAt(r710.tag), r710.index);
    }

    @Override // org.mvel2.asm.AnnotationVisitor
    public AnnotationVisitor visitAnnotation(String r4, String r5) {
        this.numElementValuePairs++;
        if (this.useNamedValues == false) goto L5;
        this.annotation.putShort(this.symbolTable.addConstantUtf8(r4));
    L5:
        this.annotation.put12(64, this.symbolTable.addConstantUtf8(r5)).putShort(0);
        return new AnnotationWriter(this.symbolTable, true, this.annotation, null);
    }

    @Override // org.mvel2.asm.AnnotationVisitor
    public AnnotationVisitor visitArray(String r4) {
        this.numElementValuePairs++;
        if (this.useNamedValues == false) goto L5;
        this.annotation.putShort(this.symbolTable.addConstantUtf8(r4));
    L5:
        this.annotation.put12(91, 0);
        return new AnnotationWriter(this.symbolTable, false, this.annotation, null);
    }

    @Override // org.mvel2.asm.AnnotationVisitor
    public void visitEnd() {
        int r0 = this.numElementValuePairsOffset;
        if (r0 == (-1)) goto L6;
        byte[] r1 = this.annotation.data;
        int r3 = this.numElementValuePairs;
        r1[r0] = (byte) (r3 >>> 8);
        r1[r0 + 1] = (byte) r3;
        return;
    }

    @Override // org.mvel2.asm.AnnotationVisitor
    public void visitEnum(String r3, String r4, String r5) {
        this.numElementValuePairs++;
        if (this.useNamedValues == false) goto L5;
        this.annotation.putShort(this.symbolTable.addConstantUtf8(r3));
    L5:
        this.annotation.put12(101, this.symbolTable.addConstantUtf8(r4)).putShort(this.symbolTable.addConstantUtf8(r5));
    }
}
