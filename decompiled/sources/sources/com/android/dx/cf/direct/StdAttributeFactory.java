package com.android.dx.cf.direct;

import com.android.dx.cf.attrib.AttAnnotationDefault;
import com.android.dx.cf.attrib.AttBootstrapMethods;
import com.android.dx.cf.attrib.AttCode;
import com.android.dx.cf.attrib.AttConstantValue;
import com.android.dx.cf.attrib.AttDeprecated;
import com.android.dx.cf.attrib.AttEnclosingMethod;
import com.android.dx.cf.attrib.AttExceptions;
import com.android.dx.cf.attrib.AttInnerClasses;
import com.android.dx.cf.attrib.AttLineNumberTable;
import com.android.dx.cf.attrib.AttLocalVariableTable;
import com.android.dx.cf.attrib.AttLocalVariableTypeTable;
import com.android.dx.cf.attrib.AttRuntimeInvisibleAnnotations;
import com.android.dx.cf.attrib.AttRuntimeInvisibleParameterAnnotations;
import com.android.dx.cf.attrib.AttRuntimeVisibleAnnotations;
import com.android.dx.cf.attrib.AttRuntimeVisibleParameterAnnotations;
import com.android.dx.cf.attrib.AttSignature;
import com.android.dx.cf.attrib.AttSourceDebugExtension;
import com.android.dx.cf.attrib.AttSourceFile;
import com.android.dx.cf.attrib.AttSynthetic;
import com.android.dx.cf.attrib.InnerClassList;
import com.android.dx.cf.code.BootstrapMethodArgumentsList;
import com.android.dx.cf.code.BootstrapMethodsList;
import com.android.dx.cf.code.ByteCatchList;
import com.android.dx.cf.code.BytecodeArray;
import com.android.dx.cf.code.LineNumberList;
import com.android.dx.cf.code.LocalVariableList;
import com.android.dx.cf.iface.Attribute;
import com.android.dx.cf.iface.ParseException;
import com.android.dx.cf.iface.ParseObserver;
import com.android.dx.cf.iface.StdAttributeList;
import com.android.dx.rop.annotation.AnnotationVisibility;
import com.android.dx.rop.code.AccessFlags;
import com.android.dx.rop.cst.ConstantPool;
import com.android.dx.rop.cst.CstMethodHandle;
import com.android.dx.rop.cst.CstNat;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.cst.TypedConstant;
import com.android.dx.util.ByteArray;
import com.android.dx.util.Hex;
import java.io.IOException;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public class StdAttributeFactory extends AttributeFactory {
    public static final StdAttributeFactory THE_ONE = null;

    static {
        THE_ONE = new StdAttributeFactory();
    }

    public StdAttributeFactory() {
    }

    private Attribute annotationDefault(DirectClassFile r1, int r2, int r3, ParseObserver r4) {
        if (r3 >= 2) goto L6;
        throwSeverelyTruncated();
    L6:
        return new AttAnnotationDefault(new AnnotationParser(r1, r2, r3, r4).parseValueAttribute(), r3);
    }

    private Attribute bootstrapMethods(DirectClassFile r9, int r10, int r11, ParseObserver r12) {
        if (r11 < 2) goto L5;
        ByteArray r1 = r9.getBytes();
        int r4 = r1.getUnsignedShort(r10);
        if (r12 == null) goto L10;
        r12.parsed(r1, r10, 2, "num_boostrap_methods: " + Hex.u2(r4));
    L10:
        return new AttBootstrapMethods(parseBootstrapMethods(r1, r9.getConstantPool(), r9.getThisClass(), r4, r10 + 2, r11 - 2, r12));
    L5:
        return throwSeverelyTruncated();
    }

    private Attribute code(DirectClassFile r19, int r20, int r21, ParseObserver r22) {
        if (r21 < 12) goto L5;
        ByteArray r3 = r19.getBytes();
        ConstantPool r4 = r19.getConstantPool();
        int r6 = r3.getUnsignedShort(r20);
        int r5 = r20 + 2;
        int r7 = r3.getUnsignedShort(r5);
        int r8 = r20 + 4;
        int r9 = r3.getInt(r8);
        if (r22 == null) goto L9;
        r22.parsed(r3, r20, 2, "max_stack: " + Hex.u2(r6));
        r22.parsed(r3, r5, 2, "max_locals: " + Hex.u2(r7));
        r22.parsed(r3, r8, 4, "code_length: " + Hex.u4(r9));
    L9:
        int r52 = r20 + 8;
        int r1 = r21 - 8;
        if (r1 < (r9 + 4)) goto L12;
        int r82 = r52 + r9;
        int r12 = r1 - r9;
        BytecodeArray r92 = new BytecodeArray(r3.slice(r52, r82), r4);
        if (r22 == null) goto L16;
        r92.forEach(new CodeObserver(r92.getBytes(), r22));
    L16:
        int r53 = r3.getUnsignedShort(r82);
        if (r53 != 0) goto L20;
        ByteCatchList r11 = ByteCatchList.EMPTY;
    L19:
        ByteCatchList r122 = r11;
        if (r22 == null) goto L23;
        r22.parsed(r3, r82, 2, "exception_table_length: " + Hex.u2(r53));
    L23:
        int r83 = r82 + 2;
        int r13 = r12 - 2;
        if (r13 < ((r53 * 8) + 2)) goto L26;
        int r132 = 0;
    L28:
        if (r132 >= r53) goto L42;
        if (r22 == null) goto L31;
        r22.changeIndent(1);
    L31:
        int r14 = r3.getUnsignedShort(r83);
        int r15 = r3.getUnsignedShort(r83 + 2);
        int r16 = r3.getUnsignedShort(r83 + 4);
        CstType r17 = (CstType) r4.get0Ok(r3.getUnsignedShort(r83 + 6));
        r122.set(r132, r14, r15, r16, r17);
        if (r22 == null) goto L38;
        StringBuilder r10 = new StringBuilder();
        r10.append(Hex.u2(r14));
        r10.append("..");
        r10.append(Hex.u2(r15));
        r10.append(" -> ");
        r10.append(Hex.u2(r16));
        r10.append(" ");
        if (r17 != null) goto L36;
        String r112 = "<any>";
    L37:
        r10.append(r112);
        r22.parsed(r3, r83, 8, r10.toString());
        goto L38
    L36:
        r112 = r17.toHuman();
    L38:
        r83 = r83 + 8;
        r13 = r13 - 8;
        if (r22 == null) goto L41;
        r22.changeIndent(-1);
    L41:
        r132 = r132 + 1;
        goto L28
    L42:
        r122.setImmutable();
        AttributeListParser r32 = new AttributeListParser(r19, 3, r83, this);
        r32.setObserver(r22);
        StdAttributeList r102 = r32.getList();
        r102.setImmutable();
        int r2 = r32.getEndOffset() - r83;
        if (r2 == r13) goto L47;
        return throwBadLength(r2 + (r83 - r20));
    L47:
        return new AttCode(r6, r7, r92, r122, r102);
    L26:
        return throwTruncated();
    L20:
        r11 = new ByteCatchList(r53);
        goto L19
    L12:
        return throwTruncated();
    L5:
        return throwSeverelyTruncated();
    }

    private Attribute constantValue(DirectClassFile r4, int r5, int r6, ParseObserver r7) {
        if (r6 != 2) goto L5;
        ByteArray r62 = r4.getBytes();
        TypedConstant r42 = (TypedConstant) r4.getConstantPool().get(r62.getUnsignedShort(r5));
        AttConstantValue r0 = new AttConstantValue(r42);
        if (r7 == null) goto L9;
        r7.parsed(r62, r5, 2, "value: " + r42);
    L9:
        return r0;
    L5:
        return throwBadLength(2);
    }

    private Attribute deprecated(DirectClassFile r1, int r2, int r3, ParseObserver r4) {
        if (r3 == 0) goto L6;
        return throwBadLength(0);
    L6:
        return new AttDeprecated();
    }

    private Attribute enclosingMethod(DirectClassFile r5, int r6, int r7, ParseObserver r8) {
        if (r7 == 4) goto L5;
        throwBadLength(4);
    L5:
        ByteArray r4 = r5.getBytes();
        ConstantPool r52 = r5.getConstantPool();
        CstType r72 = (CstType) r52.get(r4.getUnsignedShort(r6));
        int r0 = r6 + 2;
        CstNat r53 = (CstNat) r52.get0Ok(r4.getUnsignedShort(r0));
        AttEnclosingMethod r1 = new AttEnclosingMethod(r72, r53);
        if (r8 == null) goto L8;
        r8.parsed(r4, r6, 2, "class: " + r72);
        r8.parsed(r4, r0, 2, "method: " + DirectClassFile.stringOrNone(r53));
    L8:
        return r1;
    }

    private Attribute exceptions(DirectClassFile r5, int r6, int r7, ParseObserver r8) {
        if (r7 < 2) goto L5;
        ByteArray r0 = r5.getBytes();
        int r1 = r0.getUnsignedShort(r6);
        if (r8 == null) goto L9;
        r8.parsed(r0, r6, 2, "number_of_exceptions: " + Hex.u2(r1));
    L9:
        int r62 = r6 + 2;
        int r82 = r1 * 2;
        if ((r7 - 2) == r82) goto L13;
        throwBadLength(r82 + 2);
    L13:
        return new AttExceptions(r5.makeTypeList(r62, r1));
    L5:
        return throwSeverelyTruncated();
    }

    private Attribute innerClasses(DirectClassFile r17, int r18, int r19, ParseObserver r20) {
        if (r19 < 2) goto L5;
        ByteArray r4 = r17.getBytes();
        ConstantPool r5 = r17.getConstantPool();
        int r6 = r4.getUnsignedShort(r18);
        if (r20 == null) goto L9;
        r20.parsed(r4, r18, 2, "number_of_classes: " + Hex.u2(r6));
    L9:
        int r0 = r18 + 2;
        int r7 = r6 * 8;
        if ((r19 - 2) == r7) goto L12;
        throwBadLength(r7 + 2);
    L12:
        InnerClassList r8 = new InnerClassList(r6);
        int r9 = 0;
    L13:
        if (r9 >= r6) goto L18;
        int r1 = r4.getUnsignedShort(r0);
        int r72 = r0 + 2;
        int r10 = r4.getUnsignedShort(r72);
        int r14 = r0 + 4;
        int r11 = r4.getUnsignedShort(r14);
        int r15 = r0 + 6;
        int r13 = r4.getUnsignedShort(r15);
        CstType r12 = (CstType) r5.get(r1);
        CstType r102 = (CstType) r5.get0Ok(r10);
        CstString r122 = (CstString) r5.get0Ok(r11);
        r8.set(r9, r12, r102, r122, r13);
        if (r20 == null) goto L17;
        r20.parsed(r4, r0, 2, "inner_class: " + DirectClassFile.stringOrNone(r12));
        r20.parsed(r4, r72, 2, "  outer_class: " + DirectClassFile.stringOrNone(r102));
        r20.parsed(r4, r14, 2, "  name: " + DirectClassFile.stringOrNone(r122));
        r20.parsed(r4, r15, 2, "  access_flags: " + AccessFlags.innerClassString(r13));
    L17:
        r0 = r0 + 8;
        r9 = r9 + 1;
        goto L13
    L18:
        r8.setImmutable();
        return new AttInnerClasses(r8);
    L5:
        return throwSeverelyTruncated();
    }

    private Attribute lineNumberTable(DirectClassFile r5, int r6, int r7, ParseObserver r8) {
        if (r7 < 2) goto L5;
        ByteArray r52 = r5.getBytes();
        int r0 = r52.getUnsignedShort(r6);
        if (r8 == null) goto L9;
        r8.parsed(r52, r6, 2, "line_number_table_length: " + Hex.u2(r0));
    L9:
        int r62 = r6 + 2;
        int r1 = r0 * 4;
        if ((r7 - 2) == r1) goto L12;
        throwBadLength(r1 + 2);
    L12:
        LineNumberList r4 = new LineNumberList(r0);
        int r72 = 0;
    L13:
        if (r72 >= r0) goto L18;
        int r12 = r52.getUnsignedShort(r62);
        int r2 = r52.getUnsignedShort(r62 + 2);
        r4.set(r72, r12, r2);
        if (r8 == null) goto L17;
        r8.parsed(r52, r62, 4, Hex.u2(r12) + " " + r2);
    L17:
        r62 = r62 + 4;
        r72 = r72 + 1;
        goto L13
    L18:
        r4.setImmutable();
        return new AttLineNumberTable(r4);
    L5:
        return throwSeverelyTruncated();
    }

    private Attribute localVariableTable(DirectClassFile r9, int r10, int r11, ParseObserver r12) {
        if (r11 < 2) goto L5;
        ByteArray r1 = r9.getBytes();
        int r6 = r1.getUnsignedShort(r10);
        if (r12 == null) goto L10;
        r12.parsed(r1, r10, 2, "local_variable_table_length: " + Hex.u2(r6));
    L10:
        return new AttLocalVariableTable(parseLocalVariables(r1.slice(r10 + 2, r10 + r11), r9.getConstantPool(), r12, r6, false));
    L5:
        return throwSeverelyTruncated();
    }

    private Attribute localVariableTypeTable(DirectClassFile r9, int r10, int r11, ParseObserver r12) {
        if (r11 < 2) goto L5;
        ByteArray r1 = r9.getBytes();
        int r6 = r1.getUnsignedShort(r10);
        if (r12 == null) goto L10;
        r12.parsed(r1, r10, 2, "local_variable_type_table_length: " + Hex.u2(r6));
    L10:
        return new AttLocalVariableTypeTable(parseLocalVariables(r1.slice(r10 + 2, r10 + r11), r9.getConstantPool(), r12, r6, true));
    L5:
        return throwSeverelyTruncated();
    }

    private BootstrapMethodsList parseBootstrapMethods(ByteArray r17, ConstantPool r18, CstType r19, int r20, int r21, int r22, ParseObserver r23) {
        BootstrapMethodsList r4 = new BootstrapMethodsList(r20);
        int r6 = r21;
        int r7 = r22;
        int r8 = 0;
    L3:
        if (r8 >= r20) goto L20;
        if (r7 >= 4) goto L7;
        throwTruncated();
    L7:
        int r9 = r17.getUnsignedShort(r6);
        int r10 = r6 + 2;
        int r11 = r17.getUnsignedShort(r10);
        if (r23 == null) goto L10;
        r23.parsed(r17, r6, 2, "bootstrap_method_ref: " + Hex.u2(r9));
        r23.parsed(r17, r10, 2, "num_bootstrap_arguments: " + Hex.u2(r11));
    L10:
        r6 = r6 + 4;
        r7 = r7 - 4;
        if (r7 >= (r11 * 2)) goto L13;
        throwTruncated();
    L13:
        BootstrapMethodArgumentsList r102 = new BootstrapMethodArgumentsList(r11);
        int r13 = 0;
    L14:
        if (r13 >= r11) goto L19;
        int r14 = r17.getUnsignedShort(r6);
        if (r23 == null) goto L18;
        r23.parsed(r17, r6, 2, "bootstrap_arguments[" + r13 + "]" + Hex.u2(r14));
    L18:
        r102.set(r13, r18.get(r14));
        r13 = r13 + 1;
        r6 = r6 + 2;
        r7 = r7 - 2;
        goto L14
    L19:
        r102.setImmutable();
        r4.set(r8, r19, (CstMethodHandle) r18.get(r9), r102);
        r8 = r8 + 1;
        goto L3
    L20:
        r4.setImmutable();
        if (r7 == 0) goto L23;
        throwBadLength(r7);
    L23:
        return r4;
    }

    private LocalVariableList parseLocalVariables(ByteArray r17, ConstantPool r18, ParseObserver r19, int r20, boolean r21) {
        int r5 = r20 * 10;
        if (r17.size() == r5) goto L5;
        throwBadLength(r5 + 2);
    L5:
        ByteArray.MyDataInputStream r4 = r17.makeDataInputStream();
        LocalVariableList r52 = new LocalVariableList(r20);
        int r6 = 0;
    L6:
        if (r6 >= r20) goto L22;
        int r7 = r4.readUnsignedShort();     // Catch: IOException -> L16
        int r8 = r4.readUnsignedShort();     // Catch: IOException -> L16
        int r9 = r4.readUnsignedShort();     // Catch: IOException -> L16
        int r10 = r4.readUnsignedShort();     // Catch: IOException -> L16
        int r12 = r4.readUnsignedShort();     // Catch: IOException -> L16
        CstString r92 = (CstString) r18.get(r9);     // Catch: IOException -> L16
        CstString r14 = (CstString) r18.get(r10);     // Catch: IOException -> L16
        if (r21 == false) goto L11;
        CstString r102 = null;
        CstString r11 = r14;
    L12:
        r52.set(r6, r7, r8, r92, r102, r11, r12);     // Catch: IOException -> L16
        if (r19 == null) goto L19;
        r19.parsed(r17, r6 * 10, 10, Hex.u2(r7) + ".." + Hex.u2(r7 + r8) + " " + Hex.u2(r12) + " " + r92.toHuman() + " " + r14.toHuman());     // Catch: IOException -> L16
    L19:
        r6 = r6 + 1;
        goto L6
    L11:
        r11 = null;
        r102 = r14;
    L16:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("shouldn't happen", e);
        return null;
    L22:
        r52.setImmutable();
        return r52;
    }

    private Attribute runtimeInvisibleAnnotations(DirectClassFile r1, int r2, int r3, ParseObserver r4) {
        if (r3 >= 2) goto L6;
        throwSeverelyTruncated();
    L6:
        return new AttRuntimeInvisibleAnnotations(new AnnotationParser(r1, r2, r3, r4).parseAnnotationAttribute(AnnotationVisibility.BUILD), r3);
    }

    private Attribute runtimeInvisibleParameterAnnotations(DirectClassFile r1, int r2, int r3, ParseObserver r4) {
        if (r3 >= 2) goto L6;
        throwSeverelyTruncated();
    L6:
        return new AttRuntimeInvisibleParameterAnnotations(new AnnotationParser(r1, r2, r3, r4).parseParameterAttribute(AnnotationVisibility.BUILD), r3);
    }

    private Attribute runtimeVisibleAnnotations(DirectClassFile r1, int r2, int r3, ParseObserver r4) {
        if (r3 >= 2) goto L6;
        throwSeverelyTruncated();
    L6:
        return new AttRuntimeVisibleAnnotations(new AnnotationParser(r1, r2, r3, r4).parseAnnotationAttribute(AnnotationVisibility.RUNTIME), r3);
    }

    private Attribute runtimeVisibleParameterAnnotations(DirectClassFile r1, int r2, int r3, ParseObserver r4) {
        if (r3 >= 2) goto L6;
        throwSeverelyTruncated();
    L6:
        return new AttRuntimeVisibleParameterAnnotations(new AnnotationParser(r1, r2, r3, r4).parseParameterAttribute(AnnotationVisibility.RUNTIME), r3);
    }

    private Attribute signature(DirectClassFile r4, int r5, int r6, ParseObserver r7) {
        if (r6 == 2) goto L5;
        throwBadLength(2);
    L5:
        ByteArray r62 = r4.getBytes();
        CstString r42 = (CstString) r4.getConstantPool().get(r62.getUnsignedShort(r5));
        AttSignature r0 = new AttSignature(r42);
        if (r7 == null) goto L8;
        r7.parsed(r62, r5, 2, "signature: " + r42);
    L8:
        return r0;
    }

    private Attribute sourceDebugExtension(DirectClassFile r4, int r5, int r6, ParseObserver r7) {
        ByteArray r3 = r4.getBytes().slice(r5, r5 + r6);
        CstString r42 = new CstString(r3);
        AttSourceDebugExtension r0 = new AttSourceDebugExtension(r42);
        if (r7 == null) goto L5;
        r7.parsed(r3, r5, r6, "sourceDebugExtension: " + r42.getString());
    L5:
        return r0;
    }

    private Attribute sourceFile(DirectClassFile r4, int r5, int r6, ParseObserver r7) {
        if (r6 == 2) goto L5;
        throwBadLength(2);
    L5:
        ByteArray r62 = r4.getBytes();
        CstString r42 = (CstString) r4.getConstantPool().get(r62.getUnsignedShort(r5));
        AttSourceFile r0 = new AttSourceFile(r42);
        if (r7 == null) goto L8;
        r7.parsed(r62, r5, 2, "source: " + r42);
    L8:
        return r0;
    }

    private Attribute synthetic(DirectClassFile r1, int r2, int r3, ParseObserver r4) {
        if (r3 == 0) goto L6;
        return throwBadLength(0);
    L6:
        return new AttSynthetic();
    }

    private static Attribute throwBadLength(int r3) {
        throw new ParseException("bad attribute length; expected length " + Hex.u4(r3));
    }

    private static Attribute throwSeverelyTruncated() {
        throw new ParseException("severely truncated attribute");
    }

    private static Attribute throwTruncated() {
        throw new ParseException("truncated attribute");
    }

    @Override // com.android.dx.cf.direct.AttributeFactory
    public Attribute parse0(DirectClassFile r8, int r9, String r10, int r11, int r12, ParseObserver r13) {
        if (r9 == 0) goto L78;
        if (r9 == 1) goto L59;
        if (r9 == 2) goto L24;
        if (r9 == 3) goto L12;
    L113:
        return super.parse0(r8, r9, r10, r11, r12, r13);
    L12:
        if (r10 != AttLineNumberTable.ATTRIBUTE_NAME) goto L16;
        return lineNumberTable(r8, r11, r12, r13);
    L16:
        if (r10 != AttLocalVariableTable.ATTRIBUTE_NAME) goto L20;
        return localVariableTable(r8, r11, r12, r13);
    L20:
        if (r10 != AttLocalVariableTypeTable.ATTRIBUTE_NAME) goto L113;
        return localVariableTypeTable(r8, r11, r12, r13);
    L24:
        if (r10 != AttAnnotationDefault.ATTRIBUTE_NAME) goto L28;
        return annotationDefault(r8, r11, r12, r13);
    L28:
        if (r10 == AttCode.ATTRIBUTE_NAME) goto L30;
        if (r10 != AttDeprecated.ATTRIBUTE_NAME) goto L35;
        return deprecated(r8, r11, r12, r13);
    L35:
        if (r10 == AttExceptions.ATTRIBUTE_NAME) goto L37;
        if (r10 == AttRuntimeInvisibleAnnotations.ATTRIBUTE_NAME) goto L40;
        if (r10 != AttRuntimeVisibleAnnotations.ATTRIBUTE_NAME) goto L45;
        return runtimeVisibleAnnotations(r8, r11, r12, r13);
    L45:
        if (r10 != AttRuntimeInvisibleParameterAnnotations.ATTRIBUTE_NAME) goto L49;
        return runtimeInvisibleParameterAnnotations(r8, r11, r12, r13);
    L49:
        if (r10 == AttRuntimeVisibleParameterAnnotations.ATTRIBUTE_NAME) goto L51;
        if (r10 == AttSignature.ATTRIBUTE_NAME) goto L54;
        if (r10 != AttSynthetic.ATTRIBUTE_NAME) goto L113;
        return synthetic(r8, r11, r12, r13);
    L54:
        return signature(r8, r11, r12, r13);
    L51:
        return runtimeVisibleParameterAnnotations(r8, r11, r12, r13);
    L40:
        return runtimeInvisibleAnnotations(r8, r11, r12, r13);
    L37:
        return exceptions(r8, r11, r12, r13);
    L30:
        return code(r8, r11, r12, r13);
    L59:
        if (r10 == AttConstantValue.ATTRIBUTE_NAME) goto L61;
        if (r10 == AttDeprecated.ATTRIBUTE_NAME) goto L64;
        if (r10 == AttRuntimeInvisibleAnnotations.ATTRIBUTE_NAME) goto L67;
        if (r10 == AttRuntimeVisibleAnnotations.ATTRIBUTE_NAME) goto L70;
        if (r10 == AttSignature.ATTRIBUTE_NAME) goto L73;
        if (r10 != AttSynthetic.ATTRIBUTE_NAME) goto L113;
        return synthetic(r8, r11, r12, r13);
    L73:
        return signature(r8, r11, r12, r13);
    L70:
        return runtimeVisibleAnnotations(r8, r11, r12, r13);
    L67:
        return runtimeInvisibleAnnotations(r8, r11, r12, r13);
    L64:
        return deprecated(r8, r11, r12, r13);
    L61:
        return constantValue(r8, r11, r12, r13);
    L78:
        if (r10 == AttBootstrapMethods.ATTRIBUTE_NAME) goto L80;
        if (r10 != AttDeprecated.ATTRIBUTE_NAME) goto L85;
        return deprecated(r8, r11, r12, r13);
    L85:
        if (r10 != AttEnclosingMethod.ATTRIBUTE_NAME) goto L89;
        return enclosingMethod(r8, r11, r12, r13);
    L89:
        if (r10 == AttInnerClasses.ATTRIBUTE_NAME) goto L91;
        if (r10 == AttRuntimeInvisibleAnnotations.ATTRIBUTE_NAME) goto L94;
        if (r10 == AttRuntimeVisibleAnnotations.ATTRIBUTE_NAME) goto L97;
        if (r10 == AttSynthetic.ATTRIBUTE_NAME) goto L100;
        if (r10 != AttSignature.ATTRIBUTE_NAME) goto L105;
        return signature(r8, r11, r12, r13);
    L105:
        if (r10 != AttSourceDebugExtension.ATTRIBUTE_NAME) goto L109;
        return sourceDebugExtension(r8, r11, r12, r13);
    L109:
        if (r10 != AttSourceFile.ATTRIBUTE_NAME) goto L113;
        return sourceFile(r8, r11, r12, r13);
    L100:
        return synthetic(r8, r11, r12, r13);
    L97:
        return runtimeVisibleAnnotations(r8, r11, r12, r13);
    L94:
        return runtimeInvisibleAnnotations(r8, r11, r12, r13);
    L91:
        return innerClasses(r8, r11, r12, r13);
    L80:
        return bootstrapMethods(r8, r11, r12, r13);
    }
}
