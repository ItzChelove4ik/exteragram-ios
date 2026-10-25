package com.android.dx.dex.file;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dex.EncodedValueCodec;
import com.android.dx.rop.annotation.Annotation;
import com.android.dx.rop.annotation.NameValuePair;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstAnnotation;
import com.android.dx.rop.cst.CstArray;
import com.android.dx.rop.cst.CstBoolean;
import com.android.dx.rop.cst.CstByte;
import com.android.dx.rop.cst.CstChar;
import com.android.dx.rop.cst.CstDouble;
import com.android.dx.rop.cst.CstEnumRef;
import com.android.dx.rop.cst.CstFieldRef;
import com.android.dx.rop.cst.CstFloat;
import com.android.dx.rop.cst.CstInteger;
import com.android.dx.rop.cst.CstKnownNull;
import com.android.dx.rop.cst.CstLiteralBits;
import com.android.dx.rop.cst.CstLong;
import com.android.dx.rop.cst.CstMethodHandle;
import com.android.dx.rop.cst.CstMethodRef;
import com.android.dx.rop.cst.CstProtoRef;
import com.android.dx.rop.cst.CstShort;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.Hex;
import java.util.Collection;
import java.util.Iterator;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class ValueEncoder {
    private static final int VALUE_ANNOTATION = 29;
    private static final int VALUE_ARRAY = 28;
    private static final int VALUE_BOOLEAN = 31;
    private static final int VALUE_BYTE = 0;
    private static final int VALUE_CHAR = 3;
    private static final int VALUE_DOUBLE = 17;
    private static final int VALUE_ENUM = 27;
    private static final int VALUE_FIELD = 25;
    private static final int VALUE_FLOAT = 16;
    private static final int VALUE_INT = 4;
    private static final int VALUE_LONG = 6;
    private static final int VALUE_METHOD = 26;
    private static final int VALUE_METHOD_HANDLE = 22;
    private static final int VALUE_METHOD_TYPE = 21;
    private static final int VALUE_NULL = 30;
    private static final int VALUE_SHORT = 2;
    private static final int VALUE_STRING = 23;
    private static final int VALUE_TYPE = 24;
    private final DexFile file;
    private final AnnotatedOutput out;

    public ValueEncoder(DexFile r2, AnnotatedOutput r3) {
        if (r2 == null) goto L9;
        if (r3 == null) goto L7;
        this.file = r2;
        this.out = r3;
        return;
    L7:
        f$$ExternalSyntheticBUOutline2.m("out == null");
        throw null;
    L9:
        f$$ExternalSyntheticBUOutline2.m("file == null");
        throw null;
    }

    public static void addContents(DexFile r3, Annotation r4) {
        TypeIdsSection r0 = r3.getTypeIds();
        StringIdsSection r1 = r3.getStringIds();
        r0.intern(r4.getType());
        Iterator<NameValuePair> r42 = r4.getNameValuePairs().iterator();
    L4:
        if (r42.hasNext() == false) goto L6;
        NameValuePair r02 = r42.next();
        r1.intern(r02.getName());
        addContents(r3, r02.getValue());
        goto L4
    }

    public static void addContents(DexFile r3, Constant r4) {
        if ((r4 instanceof CstAnnotation) == false) goto L7;
        addContents(r3, ((CstAnnotation) r4).getAnnotation());
        return;
    L7:
        if ((r4 instanceof CstArray) == false) goto L12;
        CstArray.List r42 = ((CstArray) r4).getList();
        int r0 = r42.size();
        int r1 = 0;
    L9:
        if (r1 >= r0) goto L11;
        addContents(r3, r42.get(r1));
        r1 = r1 + 1;
        goto L9
    L11:
        return;
    L12:
        r3.internIfAppropriate(r4);
    }

    public static String constantToHuman(Constant r2) {
        if (constantToValueType(r2) != 30) goto L7;
        return "null";
    L7:
        return r2.typeName() + ' ' + r2.toHuman();
    }

    private static int constantToValueType(Constant r1) {
        if ((r1 instanceof CstByte) == false) goto L7;
        return 0;
    L7:
        if ((r1 instanceof CstShort) == false) goto L11;
        return 2;
    L11:
        if ((r1 instanceof CstChar) == false) goto L15;
        return 3;
    L15:
        if ((r1 instanceof CstInteger) == false) goto L19;
        return 4;
    L19:
        if ((r1 instanceof CstLong) == false) goto L23;
        return 6;
    L23:
        if ((r1 instanceof CstFloat) == false) goto L27;
        return 16;
    L27:
        if ((r1 instanceof CstDouble) == false) goto L31;
        return 17;
    L31:
        if ((r1 instanceof CstProtoRef) == false) goto L35;
        return 21;
    L35:
        if ((r1 instanceof CstMethodHandle) == false) goto L39;
        return 22;
    L39:
        if ((r1 instanceof CstString) == false) goto L43;
        return 23;
    L43:
        if ((r1 instanceof CstType) == false) goto L47;
        return 24;
    L47:
        if ((r1 instanceof CstFieldRef) == false) goto L51;
        return 25;
    L51:
        if ((r1 instanceof CstMethodRef) == false) goto L55;
        return 26;
    L55:
        if ((r1 instanceof CstEnumRef) == false) goto L59;
        return 27;
    L59:
        if ((r1 instanceof CstArray) == false) goto L63;
        return 28;
    L63:
        if ((r1 instanceof CstAnnotation) == false) goto L67;
        return 29;
    L67:
        if ((r1 instanceof CstKnownNull) == false) goto L71;
        return 30;
    L71:
        if ((r1 instanceof CstBoolean) == false) goto L74;
        return 31;
    L74:
        GlShader$$ExternalSyntheticBUOutline1.m("Shouldn't happen");
        return 0;
    }

    public void writeAnnotation(Annotation r11, boolean r12) {
        if (r12 == true) goto L5;
    L7:
        boolean r122 = false;
    L8:
        StringIdsSection r1 = this.file.getStringIds();
        TypeIdsSection r2 = this.file.getTypeIds();
        CstType r3 = r11.getType();
        int r4 = r2.indexOf(r3);
        if (r122 == false) goto L11;
        this.out.annotate("  type_idx: " + Hex.u4(r4) + " // " + r3.toHuman());
    L11:
        this.out.writeUleb128(r2.indexOf(r11.getType()));
        Collection<NameValuePair> r112 = r11.getNameValuePairs();
        int r22 = r112.size();
        if (r122 == false) goto L14;
        this.out.annotate("  size: " + Hex.u4(r22));
    L14:
        this.out.writeUleb128(r22);
        Iterator<NameValuePair> r113 = r112.iterator();
        int r23 = 0;
    L16:
        if (r113.hasNext() == false) goto L24;
        NameValuePair r32 = r113.next();
        CstString r42 = r32.getName();
        int r6 = r1.indexOf(r42);
        Constant r33 = r32.getValue();
        if (r122 == false) goto L20;
        this.out.annotate(0, "  elements[" + r23 + "]:");
        r23 = r23 + 1;
        this.out.annotate("    name_idx: " + Hex.u4(r6) + " // " + r42.toHuman());
    L20:
        this.out.writeUleb128(r6);
        if (r122 == false) goto L23;
        this.out.annotate("    value: " + constantToHuman(r33));
    L23:
        writeConstant(r33);
        goto L16
    L24:
        if (r122 == false) goto L30;
        this.out.endAnnotation();
        return;
    L30:
        return;
    L5:
        if (this.out.annotates() == false) goto L7;
        r122 = true;
        goto L8
    }

    public void writeArray(CstArray r7, boolean r8) {
        int r0 = 0;
        if (r8 == true) goto L5;
    L7:
        boolean r82 = false;
    L8:
        CstArray.List r72 = r7.getList();
        int r1 = r72.size();
        if (r82 == false) goto L11;
        this.out.annotate("  size: " + Hex.u4(r1));
    L11:
        this.out.writeUleb128(r1);
    L12:
        if (r0 >= r1) goto L17;
        Constant r2 = r72.get(r0);
        if (r82 == false) goto L16;
        this.out.annotate("  [" + Integer.toHexString(r0) + "] " + constantToHuman(r2));
    L16:
        writeConstant(r2);
        r0 = r0 + 1;
        goto L12
    L17:
        if (r82 == false) goto L23;
        this.out.endAnnotation();
        return;
    L23:
        return;
    L5:
        if (this.out.annotates() == false) goto L7;
        r82 = true;
        goto L8
    }

    public void writeConstant(Constant r4) {
        int r0 = constantToValueType(r4);
        if (r0 != 0) goto L5;
    L48:
        EncodedValueCodec.writeSignedIntegralValue(this.out, r0, ((CstLiteralBits) r4).getLongBits());
        return;
    L5:
        if (r0 == 6) goto L48;
        if (r0 == 2) goto L48;
        if (r0 != 3) goto L11;
        EncodedValueCodec.writeUnsignedIntegralValue(this.out, r0, ((CstLiteralBits) r4).getLongBits());
        return;
    L11:
        if (r0 == 4) goto L48;
        if (r0 != 16) goto L15;
        EncodedValueCodec.writeRightZeroExtendedValue(this.out, r0, ((CstFloat) r4).getLongBits() << 32);
        return;
    L15:
        if (r0 != 17) goto L17;
        EncodedValueCodec.writeRightZeroExtendedValue(this.out, r0, ((CstDouble) r4).getLongBits());
        return;
    L17:
        switch(r0) {
            case 21: goto L40;
            case 22: goto L38;
            case 23: goto L36;
            case 24: goto L34;
            case 25: goto L32;
            case 26: goto L30;
            case 27: goto L28;
            case 28: goto L26;
            case 29: goto L24;
            case 30: goto L22;
            case 31: goto L20;
            default: goto L18;
        };
    L18:
        GlShader$$ExternalSyntheticBUOutline1.m("Shouldn't happen");
        return;
    L20:
        this.out.writeByte((((CstBoolean) r4).getIntBits() << 5) | r0);
        return;
    L22:
        this.out.writeByte(r0);
        return;
    L24:
        this.out.writeByte(r0);
        writeAnnotation(((CstAnnotation) r4).getAnnotation(), false);
        return;
    L26:
        this.out.writeByte(r0);
        writeArray((CstArray) r4, false);
        return;
    L28:
        EncodedValueCodec.writeUnsignedIntegralValue(this.out, r0, this.file.getFieldIds().indexOf(((CstEnumRef) r4).getFieldRef()));
        return;
    L30:
        EncodedValueCodec.writeUnsignedIntegralValue(this.out, r0, this.file.getMethodIds().indexOf((CstMethodRef) r4));
        return;
    L32:
        EncodedValueCodec.writeUnsignedIntegralValue(this.out, r0, this.file.getFieldIds().indexOf((CstFieldRef) r4));
        return;
    L34:
        EncodedValueCodec.writeUnsignedIntegralValue(this.out, r0, this.file.getTypeIds().indexOf((CstType) r4));
        return;
    L36:
        EncodedValueCodec.writeUnsignedIntegralValue(this.out, r0, this.file.getStringIds().indexOf((CstString) r4));
        return;
    L38:
        EncodedValueCodec.writeUnsignedIntegralValue(this.out, r0, this.file.getMethodHandles().indexOf((CstMethodHandle) r4));
        return;
    L40:
        EncodedValueCodec.writeUnsignedIntegralValue(this.out, r0, this.file.getProtoIds().indexOf(((CstProtoRef) r4).getPrototype()));
    }
}
