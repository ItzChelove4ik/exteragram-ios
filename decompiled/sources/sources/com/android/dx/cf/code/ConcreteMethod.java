package com.android.dx.cf.code;

import com.android.dx.cf.attrib.AttCode;
import com.android.dx.cf.attrib.AttLineNumberTable;
import com.android.dx.cf.attrib.AttLocalVariableTable;
import com.android.dx.cf.attrib.AttLocalVariableTypeTable;
import com.android.dx.cf.iface.AttributeList;
import com.android.dx.cf.iface.ClassFile;
import com.android.dx.cf.iface.Method;
import com.android.dx.rop.code.SourcePosition;
import com.android.dx.rop.cst.CstNat;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.Prototype;

/* loaded from: classes4.dex */
public final class ConcreteMethod implements Method {
    private final AttCode attCode;
    private final ClassFile classFile;
    private final LineNumberList lineNumbers;
    private final LocalVariableList localVariables;
    private final Method method;

    public ConcreteMethod(Method r2, ClassFile r3, boolean r4, boolean r5) {
        this.method = r2;
        this.classFile = r3;
        AttCode r22 = (AttCode) r2.getAttributes().findFirst(AttCode.ATTRIBUTE_NAME);
        this.attCode = r22;
        AttributeList r23 = r22.getAttributes();
        LineNumberList r32 = LineNumberList.EMPTY;
        if (r4 == false) goto L7;
        AttLineNumberTable r42 = (AttLineNumberTable) r23.findFirst(AttLineNumberTable.ATTRIBUTE_NAME);
    L5:
        if (r42 == null) goto L7;
        r32 = LineNumberList.concat(r32, r42.getLineNumbers());
        r42 = (AttLineNumberTable) r23.findNext(r42);
    L7:
        this.lineNumbers = r32;
        LocalVariableList r33 = LocalVariableList.EMPTY;
        if (r5 == false) goto L18;
        AttLocalVariableTable r43 = (AttLocalVariableTable) r23.findFirst(AttLocalVariableTable.ATTRIBUTE_NAME);
    L10:
        if (r43 == null) goto L12;
        r33 = LocalVariableList.concat(r33, r43.getLocalVariables());
        r43 = (AttLocalVariableTable) r23.findNext(r43);
        goto L10
    L12:
        LocalVariableList r44 = LocalVariableList.EMPTY;
        AttLocalVariableTypeTable r52 = (AttLocalVariableTypeTable) r23.findFirst(AttLocalVariableTypeTable.ATTRIBUTE_NAME);
    L13:
        if (r52 == null) goto L16;
        r44 = LocalVariableList.concat(r44, r52.getLocalVariables());
        r52 = (AttLocalVariableTypeTable) r23.findNext(r52);
        goto L13
    L16:
        if (r44.size() == 0) goto L18;
        r33 = LocalVariableList.mergeDescriptorsAndSignatures(r33, r44);
    L18:
        this.localVariables = r33;
    }

    @Override // com.android.dx.cf.iface.Member
    public int getAccessFlags() {
        return this.method.getAccessFlags();
    }

    @Override // com.android.dx.cf.iface.Member, com.android.dx.cf.iface.HasAttribute
    public AttributeList getAttributes() {
        return this.method.getAttributes();
    }

    public ByteCatchList getCatches() {
        return this.attCode.getCatches();
    }

    public BytecodeArray getCode() {
        return this.attCode.getCode();
    }

    @Override // com.android.dx.cf.iface.Member
    public CstType getDefiningClass() {
        return this.method.getDefiningClass();
    }

    @Override // com.android.dx.cf.iface.Member
    public CstString getDescriptor() {
        return this.method.getDescriptor();
    }

    @Override // com.android.dx.cf.iface.Method
    public Prototype getEffectiveDescriptor() {
        return this.method.getEffectiveDescriptor();
    }

    public LineNumberList getLineNumbers() {
        return this.lineNumbers;
    }

    public LocalVariableList getLocalVariables() {
        return this.localVariables;
    }

    public int getMaxLocals() {
        return this.attCode.getMaxLocals();
    }

    public int getMaxStack() {
        return this.attCode.getMaxStack();
    }

    @Override // com.android.dx.cf.iface.Member
    public CstString getName() {
        return this.method.getName();
    }

    @Override // com.android.dx.cf.iface.Member
    public CstNat getNat() {
        return this.method.getNat();
    }

    public CstString getSourceFile() {
        return this.classFile.getSourceFile();
    }

    public final boolean isDefaultOrStaticInterfaceMethod() {
        if ((this.classFile.getAccessFlags() & 512) != 0) goto L5;
        return false;
    L5:
        if (getNat().isClassInit() == true) goto L10;
        return true;
    L10:
        return false;
    }

    public final boolean isStaticMethod() {
        if ((getAccessFlags() & 8) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public SourcePosition makeSourcePosistion(int r3) {
        return new SourcePosition(getSourceFile(), r3, this.lineNumbers.pcToLine(r3));
    }
}
