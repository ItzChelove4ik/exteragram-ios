package com.android.dx.cf.direct;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.cf.iface.ParseException;
import com.android.dx.cf.iface.ParseObserver;
import com.android.dx.rop.annotation.Annotation;
import com.android.dx.rop.annotation.AnnotationVisibility;
import com.android.dx.rop.annotation.Annotations;
import com.android.dx.rop.annotation.AnnotationsList;
import com.android.dx.rop.annotation.NameValuePair;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.ConstantPool;
import com.android.dx.rop.cst.CstAnnotation;
import com.android.dx.rop.cst.CstArray;
import com.android.dx.rop.cst.CstBoolean;
import com.android.dx.rop.cst.CstByte;
import com.android.dx.rop.cst.CstChar;
import com.android.dx.rop.cst.CstDouble;
import com.android.dx.rop.cst.CstEnumRef;
import com.android.dx.rop.cst.CstFloat;
import com.android.dx.rop.cst.CstInteger;
import com.android.dx.rop.cst.CstLong;
import com.android.dx.rop.cst.CstNat;
import com.android.dx.rop.cst.CstShort;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.Type;
import com.android.dx.util.ByteArray;
import com.android.dx.util.Hex;
import java.io.IOException;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class AnnotationParser {
    private final ByteArray bytes;
    private final DirectClassFile cf;
    private final ByteArray.MyDataInputStream input;
    private final ParseObserver observer;
    private int parseCursor;
    private final ConstantPool pool;

    public AnnotationParser(DirectClassFile r2, int r3, int r4, ParseObserver r5) {
        if (r2 == null) goto L6;
        this.cf = r2;
        this.pool = r2.getConstantPool();
        this.observer = r5;
        ByteArray r22 = r2.getBytes().slice(r3, r4 + r3);
        this.bytes = r22;
        this.input = r22.makeDataInputStream();
        this.parseCursor = 0;
        return;
    L6:
        f$$ExternalSyntheticBUOutline2.m("cf == null");
        throw null;
    }

    private void changeIndent(int r1) {
        this.observer.changeIndent(r1);
    }

    private Annotation parseAnnotation(AnnotationVisibility r6) {
        requireLength(4);
        int r0 = this.input.readUnsignedShort();
        int r1 = this.input.readUnsignedShort();
        CstType r2 = new CstType(Type.intern(((CstString) this.pool.get(r0)).getString()));
        if (this.observer == null) goto L5;
        parsed(2, "type: " + r2.toHuman());
        parsed(2, "num_elements: " + r1);
    L5:
        Annotation r02 = new Annotation(r2, r6);
        int r22 = 0;
    L6:
        if (r22 >= r1) goto L14;
        if (this.observer == null) goto L10;
        parsed(0, "elements[" + r22 + "]:");
        changeIndent(1);
    L10:
        r02.add(parseElement());
        if (this.observer == null) goto L13;
        changeIndent(-1);
    L13:
        r22 = r22 + 1;
        goto L6
    L14:
        r02.setImmutable();
        return r02;
    }

    private Annotations parseAnnotations(AnnotationVisibility r7) {
        int r0 = this.input.readUnsignedShort();
        if (this.observer == null) goto L5;
        parsed(2, "num_annotations: " + Hex.u2(r0));
    L5:
        Annotations r1 = new Annotations();
        int r3 = 0;
    L6:
        if (r3 >= r0) goto L14;
        if (this.observer == null) goto L10;
        parsed(0, "annotations[" + r3 + "]:");
        changeIndent(1);
    L10:
        r1.add(parseAnnotation(r7));
        ParseObserver r4 = this.observer;
        if (r4 == null) goto L13;
        r4.changeIndent(-1);
    L13:
        r3 = r3 + 1;
        goto L6
    L14:
        r1.setImmutable();
        return r1;
    }

    private AnnotationsList parseAnnotationsList(AnnotationVisibility r8) {
        int r0 = this.input.readUnsignedByte();
        if (this.observer == null) goto L5;
        parsed(1, "num_parameters: " + Hex.u1(r0));
    L5:
        AnnotationsList r1 = new AnnotationsList(r0);
        int r4 = 0;
    L6:
        if (r4 >= r0) goto L14;
        if (this.observer == null) goto L10;
        parsed(0, "parameter_annotations[" + r4 + "]:");
        changeIndent(1);
    L10:
        r1.set(r4, parseAnnotations(r8));
        ParseObserver r5 = this.observer;
        if (r5 == null) goto L13;
        r5.changeIndent(-1);
    L13:
        r4 = r4 + 1;
        goto L6
    L14:
        r1.setImmutable();
        return r1;
    }

    private Constant parseConstant() {
        Constant r0 = this.pool.get(this.input.readUnsignedShort());
        if (this.observer != null) goto L5;
    L9:
        return r0;
    L5:
        if ((r0 instanceof CstString) == false) goto L7;
        String r1 = ((CstString) r0).toQuoted();
    L8:
        parsed(2, "constant_value: " + r1);
        goto L9
    L7:
        r1 = r0.toHuman();
        goto L8
    }

    private NameValuePair parseElement() {
        requireLength(5);
        CstString r0 = (CstString) this.pool.get(this.input.readUnsignedShort());
        if (this.observer == null) goto L5;
        parsed(2, "element_name: " + r0.toHuman());
        parsed(0, "value: ");
        changeIndent(1);
    L5:
        Constant r1 = parseValue();
        if (this.observer == null) goto L9;
        changeIndent(-1);
    L9:
        return new NameValuePair(r0, r1);
    }

    private Constant parseValue() {
        int r0 = this.input.readUnsignedByte();
        if (this.observer == null) goto L6;
        parsed(1, "tag: " + new CstString(Character.toString((char) r0)).toQuoted());
    L6:
        if (r0 == 64) goto L70;
        if (r0 == 70) goto L68;
        if (r0 == 83) goto L66;
        if (r0 != 99) goto L14;
        Type r02 = Type.internReturnType(((CstString) this.pool.get(this.input.readUnsignedShort())).getString());
        if (this.observer == null) goto L64;
        parsed(2, "class_info: " + r02.toHuman());
    L64:
        return new CstType(r02);
    L14:
        if (r0 != 101) goto L16;
        requireLength(4);
        int r03 = this.input.readUnsignedShort();
        int r1 = this.input.readUnsignedShort();
        CstString r04 = (CstString) this.pool.get(r03);
        CstString r12 = (CstString) this.pool.get(r1);
        if (this.observer == null) goto L59;
        parsed(2, "type_name: " + r04.toHuman());
        parsed(2, "const_name: " + r12.toHuman());
    L59:
        return new CstEnumRef(new CstNat(r12, r04));
    L16:
        if (r0 == 115) goto L54;
        if (r0 == 73) goto L52;
        if (r0 == 74) goto L50;
        if (r0 == 90) goto L48;
        if (r0 == 91) goto L34;
        switch(r0) {
            case 66: goto L33;
            case 67: goto L30;
            case 68: goto L29;
            default: goto L27;
        };
    L30:
        CstInteger r7 = (CstInteger) parseConstant();
        r7.getValue();
        return CstChar.make(r7.getValue());
    L27:
        throw new ParseException("unknown annotation tag: " + Hex.u1(r0));
    L29:
        return (CstDouble) parseConstant();
    L33:
        return CstByte.make(((CstInteger) parseConstant()).getValue());
    L34:
        requireLength(2);
        int r05 = this.input.readUnsignedShort();
        CstArray.List r13 = new CstArray.List(r05);
        if (this.observer == null) goto L37;
        parsed(2, "num_values: " + r05);
        changeIndent(1);
    L37:
        int r4 = 0;
    L38:
        ParseObserver r5 = this.observer;
        if (r4 >= r05) goto L43;
        if (r5 == null) goto L42;
        changeIndent(-1);
        parsed(0, "element_value[" + r4 + "]:");
        changeIndent(1);
    L42:
        r13.set(r4, parseValue());
        r4 = r4 + 1;
        goto L38
    L43:
        if (r5 == null) goto L45;
        changeIndent(-1);
    L45:
        r13.setImmutable();
        return new CstArray(r13);
    L48:
        return CstBoolean.make(((CstInteger) parseConstant()).getValue());
    L50:
        return (CstLong) parseConstant();
    L52:
        return (CstInteger) parseConstant();
    L54:
        return parseConstant();
    L66:
        return CstShort.make(((CstInteger) parseConstant()).getValue());
    L68:
        return (CstFloat) parseConstant();
    L70:
        return new CstAnnotation(parseAnnotation(AnnotationVisibility.EMBEDDED));
    }

    private void parsed(int r4, String r5) {
        this.observer.parsed(this.bytes, this.parseCursor, r4, r5);
        this.parseCursor += r4;
    }

    private void requireLength(int r1) {
        if (this.input.available() < r1) goto L6;
        return;
    L6:
        throw new ParseException("truncated annotation attribute");
    }

    public Annotations parseAnnotationAttribute(AnnotationVisibility r1) {
        Annotations r12 = parseAnnotations(r1);     // Catch: IOException -> L7
        if (this.input.available() != 0) goto L6;
        return r12;
    L6:
        throw new ParseException("extra data in attribute");     // Catch: IOException -> L7
    L7:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("shouldn't happen", e);
        return null;
    }

    public AnnotationsList parseParameterAttribute(AnnotationVisibility r1) {
        AnnotationsList r12 = parseAnnotationsList(r1);     // Catch: IOException -> L7
        if (this.input.available() != 0) goto L6;
        return r12;
    L6:
        throw new ParseException("extra data in attribute");     // Catch: IOException -> L7
    L7:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("shouldn't happen", e);
        return null;
    }

    public Constant parseValueAttribute() {
        Constant r0 = parseValue();     // Catch: IOException -> L7
        if (this.input.available() != 0) goto L6;
        return r0;
    L6:
        throw new ParseException("extra data in attribute");     // Catch: IOException -> L7
    L7:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("shouldn't happen", e);
        return null;
    }
}
