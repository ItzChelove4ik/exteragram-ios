package com.android.dx.dex.code;

import com.android.dx.rop.code.Insn;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.Rop;
import com.android.dx.rop.code.Rops;
import com.android.dx.rop.code.ThrowingCstInsn;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstFieldRef;
import com.android.dx.rop.cst.CstMethodHandle;
import com.android.dx.rop.cst.CstProtoRef;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.google.android.gms.cast.MediaError;
import java.util.HashMap;
import org.mvel2.MVEL$$ExternalSyntheticBUOutline0;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public final class RopToDop {
    private static final HashMap<Rop, Dop> MAP = null;

    static {
        HashMap<Rop, Dop> r0 = new HashMap(MediaError.DetailedErrorCode.MANIFEST_UNKNOWN);
        MAP = r0;
        r0.put(Rops.NOP, Dops.NOP);
        Rop r1 = Rops.MOVE_INT;
        Dop r2 = Dops.MOVE;
        r0.put(r1, r2);
        Rop r12 = Rops.MOVE_LONG;
        Dop r3 = Dops.MOVE_WIDE;
        r0.put(r12, r3);
        r0.put(Rops.MOVE_FLOAT, r2);
        r0.put(Rops.MOVE_DOUBLE, r3);
        Rop r13 = Rops.MOVE_OBJECT;
        Dop r4 = Dops.MOVE_OBJECT;
        r0.put(r13, r4);
        r0.put(Rops.MOVE_PARAM_INT, r2);
        r0.put(Rops.MOVE_PARAM_LONG, r3);
        r0.put(Rops.MOVE_PARAM_FLOAT, r2);
        r0.put(Rops.MOVE_PARAM_DOUBLE, r3);
        r0.put(Rops.MOVE_PARAM_OBJECT, r4);
        Rop r14 = Rops.CONST_INT;
        Dop r22 = Dops.CONST_4;
        r0.put(r14, r22);
        Rop r15 = Rops.CONST_LONG;
        Dop r32 = Dops.CONST_WIDE_16;
        r0.put(r15, r32);
        r0.put(Rops.CONST_FLOAT, r22);
        r0.put(Rops.CONST_DOUBLE, r32);
        r0.put(Rops.CONST_OBJECT_NOTHROW, r22);
        r0.put(Rops.GOTO, Dops.GOTO);
        Rop r16 = Rops.IF_EQZ_INT;
        Dop r23 = Dops.IF_EQZ;
        r0.put(r16, r23);
        Rop r17 = Rops.IF_NEZ_INT;
        Dop r33 = Dops.IF_NEZ;
        r0.put(r17, r33);
        r0.put(Rops.IF_LTZ_INT, Dops.IF_LTZ);
        r0.put(Rops.IF_GEZ_INT, Dops.IF_GEZ);
        r0.put(Rops.IF_LEZ_INT, Dops.IF_LEZ);
        r0.put(Rops.IF_GTZ_INT, Dops.IF_GTZ);
        r0.put(Rops.IF_EQZ_OBJECT, r23);
        r0.put(Rops.IF_NEZ_OBJECT, r33);
        Rop r18 = Rops.IF_EQ_INT;
        Dop r24 = Dops.IF_EQ;
        r0.put(r18, r24);
        Rop r19 = Rops.IF_NE_INT;
        Dop r34 = Dops.IF_NE;
        r0.put(r19, r34);
        r0.put(Rops.IF_LT_INT, Dops.IF_LT);
        r0.put(Rops.IF_GE_INT, Dops.IF_GE);
        r0.put(Rops.IF_LE_INT, Dops.IF_LE);
        r0.put(Rops.IF_GT_INT, Dops.IF_GT);
        r0.put(Rops.IF_EQ_OBJECT, r24);
        r0.put(Rops.IF_NE_OBJECT, r34);
        r0.put(Rops.SWITCH, Dops.SPARSE_SWITCH);
        r0.put(Rops.ADD_INT, Dops.ADD_INT_2ADDR);
        r0.put(Rops.ADD_LONG, Dops.ADD_LONG_2ADDR);
        r0.put(Rops.ADD_FLOAT, Dops.ADD_FLOAT_2ADDR);
        r0.put(Rops.ADD_DOUBLE, Dops.ADD_DOUBLE_2ADDR);
        r0.put(Rops.SUB_INT, Dops.SUB_INT_2ADDR);
        r0.put(Rops.SUB_LONG, Dops.SUB_LONG_2ADDR);
        r0.put(Rops.SUB_FLOAT, Dops.SUB_FLOAT_2ADDR);
        r0.put(Rops.SUB_DOUBLE, Dops.SUB_DOUBLE_2ADDR);
        r0.put(Rops.MUL_INT, Dops.MUL_INT_2ADDR);
        r0.put(Rops.MUL_LONG, Dops.MUL_LONG_2ADDR);
        r0.put(Rops.MUL_FLOAT, Dops.MUL_FLOAT_2ADDR);
        r0.put(Rops.MUL_DOUBLE, Dops.MUL_DOUBLE_2ADDR);
        r0.put(Rops.DIV_INT, Dops.DIV_INT_2ADDR);
        r0.put(Rops.DIV_LONG, Dops.DIV_LONG_2ADDR);
        r0.put(Rops.DIV_FLOAT, Dops.DIV_FLOAT_2ADDR);
        r0.put(Rops.DIV_DOUBLE, Dops.DIV_DOUBLE_2ADDR);
        r0.put(Rops.REM_INT, Dops.REM_INT_2ADDR);
        r0.put(Rops.REM_LONG, Dops.REM_LONG_2ADDR);
        r0.put(Rops.REM_FLOAT, Dops.REM_FLOAT_2ADDR);
        r0.put(Rops.REM_DOUBLE, Dops.REM_DOUBLE_2ADDR);
        r0.put(Rops.NEG_INT, Dops.NEG_INT);
        r0.put(Rops.NEG_LONG, Dops.NEG_LONG);
        r0.put(Rops.NEG_FLOAT, Dops.NEG_FLOAT);
        r0.put(Rops.NEG_DOUBLE, Dops.NEG_DOUBLE);
        r0.put(Rops.AND_INT, Dops.AND_INT_2ADDR);
        r0.put(Rops.AND_LONG, Dops.AND_LONG_2ADDR);
        r0.put(Rops.OR_INT, Dops.OR_INT_2ADDR);
        r0.put(Rops.OR_LONG, Dops.OR_LONG_2ADDR);
        r0.put(Rops.XOR_INT, Dops.XOR_INT_2ADDR);
        r0.put(Rops.XOR_LONG, Dops.XOR_LONG_2ADDR);
        r0.put(Rops.SHL_INT, Dops.SHL_INT_2ADDR);
        r0.put(Rops.SHL_LONG, Dops.SHL_LONG_2ADDR);
        r0.put(Rops.SHR_INT, Dops.SHR_INT_2ADDR);
        r0.put(Rops.SHR_LONG, Dops.SHR_LONG_2ADDR);
        r0.put(Rops.USHR_INT, Dops.USHR_INT_2ADDR);
        r0.put(Rops.USHR_LONG, Dops.USHR_LONG_2ADDR);
        r0.put(Rops.NOT_INT, Dops.NOT_INT);
        r0.put(Rops.NOT_LONG, Dops.NOT_LONG);
        r0.put(Rops.ADD_CONST_INT, Dops.ADD_INT_LIT8);
        r0.put(Rops.SUB_CONST_INT, Dops.RSUB_INT_LIT8);
        r0.put(Rops.MUL_CONST_INT, Dops.MUL_INT_LIT8);
        r0.put(Rops.DIV_CONST_INT, Dops.DIV_INT_LIT8);
        r0.put(Rops.REM_CONST_INT, Dops.REM_INT_LIT8);
        r0.put(Rops.AND_CONST_INT, Dops.AND_INT_LIT8);
        r0.put(Rops.OR_CONST_INT, Dops.OR_INT_LIT8);
        r0.put(Rops.XOR_CONST_INT, Dops.XOR_INT_LIT8);
        r0.put(Rops.SHL_CONST_INT, Dops.SHL_INT_LIT8);
        r0.put(Rops.SHR_CONST_INT, Dops.SHR_INT_LIT8);
        r0.put(Rops.USHR_CONST_INT, Dops.USHR_INT_LIT8);
        r0.put(Rops.CMPL_LONG, Dops.CMP_LONG);
        r0.put(Rops.CMPL_FLOAT, Dops.CMPL_FLOAT);
        r0.put(Rops.CMPL_DOUBLE, Dops.CMPL_DOUBLE);
        r0.put(Rops.CMPG_FLOAT, Dops.CMPG_FLOAT);
        r0.put(Rops.CMPG_DOUBLE, Dops.CMPG_DOUBLE);
        r0.put(Rops.CONV_L2I, Dops.LONG_TO_INT);
        r0.put(Rops.CONV_F2I, Dops.FLOAT_TO_INT);
        r0.put(Rops.CONV_D2I, Dops.DOUBLE_TO_INT);
        r0.put(Rops.CONV_I2L, Dops.INT_TO_LONG);
        r0.put(Rops.CONV_F2L, Dops.FLOAT_TO_LONG);
        r0.put(Rops.CONV_D2L, Dops.DOUBLE_TO_LONG);
        r0.put(Rops.CONV_I2F, Dops.INT_TO_FLOAT);
        r0.put(Rops.CONV_L2F, Dops.LONG_TO_FLOAT);
        r0.put(Rops.CONV_D2F, Dops.DOUBLE_TO_FLOAT);
        r0.put(Rops.CONV_I2D, Dops.INT_TO_DOUBLE);
        r0.put(Rops.CONV_L2D, Dops.LONG_TO_DOUBLE);
        r0.put(Rops.CONV_F2D, Dops.FLOAT_TO_DOUBLE);
        r0.put(Rops.TO_BYTE, Dops.INT_TO_BYTE);
        r0.put(Rops.TO_CHAR, Dops.INT_TO_CHAR);
        r0.put(Rops.TO_SHORT, Dops.INT_TO_SHORT);
        r0.put(Rops.RETURN_VOID, Dops.RETURN_VOID);
        Rop r110 = Rops.RETURN_INT;
        Dop r25 = Dops.RETURN;
        r0.put(r110, r25);
        Rop r111 = Rops.RETURN_LONG;
        Dop r35 = Dops.RETURN_WIDE;
        r0.put(r111, r35);
        r0.put(Rops.RETURN_FLOAT, r25);
        r0.put(Rops.RETURN_DOUBLE, r35);
        r0.put(Rops.RETURN_OBJECT, Dops.RETURN_OBJECT);
        r0.put(Rops.ARRAY_LENGTH, Dops.ARRAY_LENGTH);
        r0.put(Rops.THROW, Dops.THROW);
        r0.put(Rops.MONITOR_ENTER, Dops.MONITOR_ENTER);
        r0.put(Rops.MONITOR_EXIT, Dops.MONITOR_EXIT);
        Rop r112 = Rops.AGET_INT;
        Dop r26 = Dops.AGET;
        r0.put(r112, r26);
        Rop r113 = Rops.AGET_LONG;
        Dop r36 = Dops.AGET_WIDE;
        r0.put(r113, r36);
        r0.put(Rops.AGET_FLOAT, r26);
        r0.put(Rops.AGET_DOUBLE, r36);
        r0.put(Rops.AGET_OBJECT, Dops.AGET_OBJECT);
        r0.put(Rops.AGET_BOOLEAN, Dops.AGET_BOOLEAN);
        r0.put(Rops.AGET_BYTE, Dops.AGET_BYTE);
        r0.put(Rops.AGET_CHAR, Dops.AGET_CHAR);
        r0.put(Rops.AGET_SHORT, Dops.AGET_SHORT);
        Rop r114 = Rops.APUT_INT;
        Dop r27 = Dops.APUT;
        r0.put(r114, r27);
        Rop r115 = Rops.APUT_LONG;
        Dop r37 = Dops.APUT_WIDE;
        r0.put(r115, r37);
        r0.put(Rops.APUT_FLOAT, r27);
        r0.put(Rops.APUT_DOUBLE, r37);
        r0.put(Rops.APUT_OBJECT, Dops.APUT_OBJECT);
        r0.put(Rops.APUT_BOOLEAN, Dops.APUT_BOOLEAN);
        r0.put(Rops.APUT_BYTE, Dops.APUT_BYTE);
        r0.put(Rops.APUT_CHAR, Dops.APUT_CHAR);
        r0.put(Rops.APUT_SHORT, Dops.APUT_SHORT);
        r0.put(Rops.NEW_INSTANCE, Dops.NEW_INSTANCE);
        r0.put(Rops.CHECK_CAST, Dops.CHECK_CAST);
        r0.put(Rops.INSTANCE_OF, Dops.INSTANCE_OF);
        Rop r116 = Rops.GET_FIELD_LONG;
        Dop r28 = Dops.IGET_WIDE;
        r0.put(r116, r28);
        r0.put(Rops.GET_FIELD_FLOAT, Dops.IGET);
        r0.put(Rops.GET_FIELD_DOUBLE, r28);
        r0.put(Rops.GET_FIELD_OBJECT, Dops.IGET_OBJECT);
        Rop r117 = Rops.GET_STATIC_LONG;
        Dop r29 = Dops.SGET_WIDE;
        r0.put(r117, r29);
        r0.put(Rops.GET_STATIC_FLOAT, Dops.SGET);
        r0.put(Rops.GET_STATIC_DOUBLE, r29);
        r0.put(Rops.GET_STATIC_OBJECT, Dops.SGET_OBJECT);
        Rop r118 = Rops.PUT_FIELD_LONG;
        Dop r210 = Dops.IPUT_WIDE;
        r0.put(r118, r210);
        r0.put(Rops.PUT_FIELD_FLOAT, Dops.IPUT);
        r0.put(Rops.PUT_FIELD_DOUBLE, r210);
        r0.put(Rops.PUT_FIELD_OBJECT, Dops.IPUT_OBJECT);
        Rop r119 = Rops.PUT_STATIC_LONG;
        Dop r211 = Dops.SPUT_WIDE;
        r0.put(r119, r211);
        r0.put(Rops.PUT_STATIC_FLOAT, Dops.SPUT);
        r0.put(Rops.PUT_STATIC_DOUBLE, r211);
        r0.put(Rops.PUT_STATIC_OBJECT, Dops.SPUT_OBJECT);
    }

    private RopToDop() {
    }

    public static Dop dopFor(Insn r8) {
        Rop r0 = r8.getOpcode();
        Dop r1 = MAP.get(r0);
        if (r1 == null) goto L5;
        return r1;
    L5:
        int r12 = r0.getOpcode();
        if (r12 == 4) goto L138;
        if (r12 != 5) goto L10;
        Constant r82 = ((ThrowingCstInsn) r8).getConstant();
        if ((r82 instanceof CstType) == false) goto L124;
        return Dops.CONST_CLASS;
    L124:
        if ((r82 instanceof CstString) == false) goto L128;
        return Dops.CONST_STRING;
    L128:
        if ((r82 instanceof CstMethodHandle) == false) goto L132;
        return Dops.CONST_METHOD_HANDLE;
    L132:
        if ((r82 instanceof CstProtoRef) == true) goto L134;
        GlShader$$ExternalSyntheticBUOutline1.m("Unexpected constant type");
        return null;
    L134:
        return Dops.CONST_METHOD_TYPE;
    L10:
        if (r12 == 41) goto L118;
        if (r12 == 42) goto L116;
        if (r12 != 55) goto L16;
        RegisterSpec r83 = r8.getResult();
        if (r83 != null) goto L106;
        return Dops.NOP;
    L106:
        switch(r83.getBasicType()) {
            case 1: goto L114;
            case 2: goto L114;
            case 3: goto L114;
            case 4: goto L112;
            case 5: goto L114;
            case 6: goto L114;
            case 7: goto L112;
            case 8: goto L114;
            case 9: goto L110;
            default: goto L107;
        };
    L107:
        GlShader$$ExternalSyntheticBUOutline1.m("Unexpected basic type");
        return null;
    L110:
        return Dops.MOVE_RESULT_OBJECT;
    L112:
        return Dops.MOVE_RESULT_WIDE;
    L114:
        return Dops.MOVE_RESULT;
    L16:
        switch(r12) {
            case 45: goto L83;
            case 46: goto L67;
            case 47: goto L51;
            case 48: goto L35;
            case 49: goto L34;
            case 50: goto L32;
            case 51: goto L30;
            case 52: goto L28;
            case 53: goto L26;
            default: goto L17;
        };
    L17:
        switch(r12) {
            case 57: goto L24;
            case 58: goto L22;
            case 59: goto L20;
            default: goto L91;
        };
    L91:
        MVEL$$ExternalSyntheticBUOutline0.m("unknown rop: ", r0);
        return null;
    L20:
        return Dops.INVOKE_CUSTOM;
    L22:
        return Dops.INVOKE_POLYMORPHIC;
    L24:
        return Dops.FILL_ARRAY_DATA;
    L35:
        int r84 = ((CstFieldRef) ((ThrowingCstInsn) r8).getConstant()).getBasicType();
        if (r84 == 1) goto L50;
        if (r84 == 2) goto L48;
        if (r84 == 3) goto L46;
        if (r84 == 6) goto L44;
        if (r84 != 8) goto L91;
        return Dops.SPUT_SHORT;
    L44:
        return Dops.SPUT;
    L46:
        return Dops.SPUT_CHAR;
    L48:
        return Dops.SPUT_BYTE;
    L50:
        return Dops.SPUT_BOOLEAN;
    L51:
        int r85 = ((CstFieldRef) ((ThrowingCstInsn) r8).getConstant()).getBasicType();
        if (r85 == 1) goto L66;
        if (r85 == 2) goto L64;
        if (r85 == 3) goto L62;
        if (r85 == 6) goto L60;
        if (r85 != 8) goto L91;
        return Dops.IPUT_SHORT;
    L60:
        return Dops.IPUT;
    L62:
        return Dops.IPUT_CHAR;
    L64:
        return Dops.IPUT_BYTE;
    L66:
        return Dops.IPUT_BOOLEAN;
    L67:
        int r86 = ((CstFieldRef) ((ThrowingCstInsn) r8).getConstant()).getBasicType();
        if (r86 == 1) goto L82;
        if (r86 == 2) goto L80;
        if (r86 == 3) goto L78;
        if (r86 == 6) goto L76;
        if (r86 != 8) goto L91;
        return Dops.SGET_SHORT;
    L76:
        return Dops.SGET;
    L78:
        return Dops.SGET_CHAR;
    L80:
        return Dops.SGET_BYTE;
    L82:
        return Dops.SGET_BOOLEAN;
    L83:
        int r87 = ((CstFieldRef) ((ThrowingCstInsn) r8).getConstant()).getBasicType();
        if (r87 == 1) goto L100;
        if (r87 == 2) goto L98;
        if (r87 == 3) goto L96;
        if (r87 == 6) goto L94;
        if (r87 != 8) goto L91;
        return Dops.IGET_SHORT;
    L94:
        return Dops.IGET;
    L96:
        return Dops.IGET_CHAR;
    L98:
        return Dops.IGET_BYTE;
    L100:
        return Dops.IGET_BOOLEAN;
    L26:
        return Dops.INVOKE_INTERFACE;
    L28:
        return Dops.INVOKE_DIRECT;
    L30:
        return Dops.INVOKE_SUPER;
    L32:
        return Dops.INVOKE_VIRTUAL;
    L34:
        return Dops.INVOKE_STATIC;
    L116:
        return Dops.FILLED_NEW_ARRAY;
    L118:
        return Dops.NEW_ARRAY;
    L138:
        return Dops.MOVE_EXCEPTION;
    }
}
