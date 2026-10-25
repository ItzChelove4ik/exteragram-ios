package com.android.dx.rop.code;

import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstCallSiteRef;
import com.android.dx.rop.cst.CstMethodRef;
import com.android.dx.rop.type.Prototype;
import com.android.dx.rop.type.StdTypeList;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeBearer;
import com.android.dx.rop.type.TypeList;
import com.exteragram.messenger.plugins.PluginsConstants;
import org.mvel2.ast.Instance$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class Rops {
    public static final Rop ADD_CONST_DOUBLE = null;
    public static final Rop ADD_CONST_FLOAT = null;
    public static final Rop ADD_CONST_INT = null;
    public static final Rop ADD_CONST_LONG = null;
    public static final Rop ADD_DOUBLE = null;
    public static final Rop ADD_FLOAT = null;
    public static final Rop ADD_INT = null;
    public static final Rop ADD_LONG = null;
    public static final Rop AGET_BOOLEAN = null;
    public static final Rop AGET_BYTE = null;
    public static final Rop AGET_CHAR = null;
    public static final Rop AGET_DOUBLE = null;
    public static final Rop AGET_FLOAT = null;
    public static final Rop AGET_INT = null;
    public static final Rop AGET_LONG = null;
    public static final Rop AGET_OBJECT = null;
    public static final Rop AGET_SHORT = null;
    public static final Rop AND_CONST_INT = null;
    public static final Rop AND_CONST_LONG = null;
    public static final Rop AND_INT = null;
    public static final Rop AND_LONG = null;
    public static final Rop APUT_BOOLEAN = null;
    public static final Rop APUT_BYTE = null;
    public static final Rop APUT_CHAR = null;
    public static final Rop APUT_DOUBLE = null;
    public static final Rop APUT_FLOAT = null;
    public static final Rop APUT_INT = null;
    public static final Rop APUT_LONG = null;
    public static final Rop APUT_OBJECT = null;
    public static final Rop APUT_SHORT = null;
    public static final Rop ARRAY_LENGTH = null;
    public static final Rop CHECK_CAST = null;
    public static final Rop CMPG_DOUBLE = null;
    public static final Rop CMPG_FLOAT = null;
    public static final Rop CMPL_DOUBLE = null;
    public static final Rop CMPL_FLOAT = null;
    public static final Rop CMPL_LONG = null;
    public static final Rop CONST_DOUBLE = null;
    public static final Rop CONST_FLOAT = null;
    public static final Rop CONST_INT = null;
    public static final Rop CONST_LONG = null;
    public static final Rop CONST_OBJECT = null;
    public static final Rop CONST_OBJECT_NOTHROW = null;
    public static final Rop CONV_D2F = null;
    public static final Rop CONV_D2I = null;
    public static final Rop CONV_D2L = null;
    public static final Rop CONV_F2D = null;
    public static final Rop CONV_F2I = null;
    public static final Rop CONV_F2L = null;
    public static final Rop CONV_I2D = null;
    public static final Rop CONV_I2F = null;
    public static final Rop CONV_I2L = null;
    public static final Rop CONV_L2D = null;
    public static final Rop CONV_L2F = null;
    public static final Rop CONV_L2I = null;
    public static final Rop DIV_CONST_DOUBLE = null;
    public static final Rop DIV_CONST_FLOAT = null;
    public static final Rop DIV_CONST_INT = null;
    public static final Rop DIV_CONST_LONG = null;
    public static final Rop DIV_DOUBLE = null;
    public static final Rop DIV_FLOAT = null;
    public static final Rop DIV_INT = null;
    public static final Rop DIV_LONG = null;
    public static final Rop FILL_ARRAY_DATA = null;
    public static final Rop GET_FIELD_BOOLEAN = null;
    public static final Rop GET_FIELD_BYTE = null;
    public static final Rop GET_FIELD_CHAR = null;
    public static final Rop GET_FIELD_DOUBLE = null;
    public static final Rop GET_FIELD_FLOAT = null;
    public static final Rop GET_FIELD_INT = null;
    public static final Rop GET_FIELD_LONG = null;
    public static final Rop GET_FIELD_OBJECT = null;
    public static final Rop GET_FIELD_SHORT = null;
    public static final Rop GET_STATIC_BOOLEAN = null;
    public static final Rop GET_STATIC_BYTE = null;
    public static final Rop GET_STATIC_CHAR = null;
    public static final Rop GET_STATIC_DOUBLE = null;
    public static final Rop GET_STATIC_FLOAT = null;
    public static final Rop GET_STATIC_INT = null;
    public static final Rop GET_STATIC_LONG = null;
    public static final Rop GET_STATIC_OBJECT = null;
    public static final Rop GET_STATIC_SHORT = null;
    public static final Rop GOTO = null;
    public static final Rop IF_EQZ_INT = null;
    public static final Rop IF_EQZ_OBJECT = null;
    public static final Rop IF_EQ_INT = null;
    public static final Rop IF_EQ_OBJECT = null;
    public static final Rop IF_GEZ_INT = null;
    public static final Rop IF_GE_INT = null;
    public static final Rop IF_GTZ_INT = null;
    public static final Rop IF_GT_INT = null;
    public static final Rop IF_LEZ_INT = null;
    public static final Rop IF_LE_INT = null;
    public static final Rop IF_LTZ_INT = null;
    public static final Rop IF_LT_INT = null;
    public static final Rop IF_NEZ_INT = null;
    public static final Rop IF_NEZ_OBJECT = null;
    public static final Rop IF_NE_INT = null;
    public static final Rop IF_NE_OBJECT = null;
    public static final Rop INSTANCE_OF = null;
    public static final Rop MARK_LOCAL_DOUBLE = null;
    public static final Rop MARK_LOCAL_FLOAT = null;
    public static final Rop MARK_LOCAL_INT = null;
    public static final Rop MARK_LOCAL_LONG = null;
    public static final Rop MARK_LOCAL_OBJECT = null;
    public static final Rop MONITOR_ENTER = null;
    public static final Rop MONITOR_EXIT = null;
    public static final Rop MOVE_DOUBLE = null;
    public static final Rop MOVE_FLOAT = null;
    public static final Rop MOVE_INT = null;
    public static final Rop MOVE_LONG = null;
    public static final Rop MOVE_OBJECT = null;
    public static final Rop MOVE_PARAM_DOUBLE = null;
    public static final Rop MOVE_PARAM_FLOAT = null;
    public static final Rop MOVE_PARAM_INT = null;
    public static final Rop MOVE_PARAM_LONG = null;
    public static final Rop MOVE_PARAM_OBJECT = null;
    public static final Rop MOVE_RETURN_ADDRESS = null;
    public static final Rop MUL_CONST_DOUBLE = null;
    public static final Rop MUL_CONST_FLOAT = null;
    public static final Rop MUL_CONST_INT = null;
    public static final Rop MUL_CONST_LONG = null;
    public static final Rop MUL_DOUBLE = null;
    public static final Rop MUL_FLOAT = null;
    public static final Rop MUL_INT = null;
    public static final Rop MUL_LONG = null;
    public static final Rop NEG_DOUBLE = null;
    public static final Rop NEG_FLOAT = null;
    public static final Rop NEG_INT = null;
    public static final Rop NEG_LONG = null;
    public static final Rop NEW_ARRAY_BOOLEAN = null;
    public static final Rop NEW_ARRAY_BYTE = null;
    public static final Rop NEW_ARRAY_CHAR = null;
    public static final Rop NEW_ARRAY_DOUBLE = null;
    public static final Rop NEW_ARRAY_FLOAT = null;
    public static final Rop NEW_ARRAY_INT = null;
    public static final Rop NEW_ARRAY_LONG = null;
    public static final Rop NEW_ARRAY_SHORT = null;
    public static final Rop NEW_INSTANCE = null;
    public static final Rop NOP = null;
    public static final Rop NOT_INT = null;
    public static final Rop NOT_LONG = null;
    public static final Rop OR_CONST_INT = null;
    public static final Rop OR_CONST_LONG = null;
    public static final Rop OR_INT = null;
    public static final Rop OR_LONG = null;
    public static final Rop PUT_FIELD_BOOLEAN = null;
    public static final Rop PUT_FIELD_BYTE = null;
    public static final Rop PUT_FIELD_CHAR = null;
    public static final Rop PUT_FIELD_DOUBLE = null;
    public static final Rop PUT_FIELD_FLOAT = null;
    public static final Rop PUT_FIELD_INT = null;
    public static final Rop PUT_FIELD_LONG = null;
    public static final Rop PUT_FIELD_OBJECT = null;
    public static final Rop PUT_FIELD_SHORT = null;
    public static final Rop PUT_STATIC_BOOLEAN = null;
    public static final Rop PUT_STATIC_BYTE = null;
    public static final Rop PUT_STATIC_CHAR = null;
    public static final Rop PUT_STATIC_DOUBLE = null;
    public static final Rop PUT_STATIC_FLOAT = null;
    public static final Rop PUT_STATIC_INT = null;
    public static final Rop PUT_STATIC_LONG = null;
    public static final Rop PUT_STATIC_OBJECT = null;
    public static final Rop PUT_STATIC_SHORT = null;
    public static final Rop REM_CONST_DOUBLE = null;
    public static final Rop REM_CONST_FLOAT = null;
    public static final Rop REM_CONST_INT = null;
    public static final Rop REM_CONST_LONG = null;
    public static final Rop REM_DOUBLE = null;
    public static final Rop REM_FLOAT = null;
    public static final Rop REM_INT = null;
    public static final Rop REM_LONG = null;
    public static final Rop RETURN_DOUBLE = null;
    public static final Rop RETURN_FLOAT = null;
    public static final Rop RETURN_INT = null;
    public static final Rop RETURN_LONG = null;
    public static final Rop RETURN_OBJECT = null;
    public static final Rop RETURN_VOID = null;
    public static final Rop SHL_CONST_INT = null;
    public static final Rop SHL_CONST_LONG = null;
    public static final Rop SHL_INT = null;
    public static final Rop SHL_LONG = null;
    public static final Rop SHR_CONST_INT = null;
    public static final Rop SHR_CONST_LONG = null;
    public static final Rop SHR_INT = null;
    public static final Rop SHR_LONG = null;
    public static final Rop SUB_CONST_DOUBLE = null;
    public static final Rop SUB_CONST_FLOAT = null;
    public static final Rop SUB_CONST_INT = null;
    public static final Rop SUB_CONST_LONG = null;
    public static final Rop SUB_DOUBLE = null;
    public static final Rop SUB_FLOAT = null;
    public static final Rop SUB_INT = null;
    public static final Rop SUB_LONG = null;
    public static final Rop SWITCH = null;
    public static final Rop THROW = null;
    public static final Rop TO_BYTE = null;
    public static final Rop TO_CHAR = null;
    public static final Rop TO_SHORT = null;
    public static final Rop USHR_CONST_INT = null;
    public static final Rop USHR_CONST_LONG = null;
    public static final Rop USHR_INT = null;
    public static final Rop USHR_LONG = null;
    public static final Rop XOR_CONST_INT = null;
    public static final Rop XOR_CONST_LONG = null;
    public static final Rop XOR_INT = null;
    public static final Rop XOR_LONG = null;

    static {
        Type r3 = Type.VOID;
        StdTypeList r7 = StdTypeList.EMPTY;
        NOP = new Rop(1, r3, r7, "nop");
        Type r10 = Type.INT;
        StdTypeList r14 = StdTypeList.INT;
        MOVE_INT = new Rop(2, r10, r14, "move-int");
        Type r15 = Type.LONG;
        StdTypeList r11 = StdTypeList.LONG;
        MOVE_LONG = new Rop(2, r15, r11, "move-long");
        Type r12 = Type.FLOAT;
        StdTypeList r13 = StdTypeList.FLOAT;
        MOVE_FLOAT = new Rop(2, r12, r13, "move-float");
        Type r1 = Type.DOUBLE;
        StdTypeList r4 = StdTypeList.DOUBLE;
        MOVE_DOUBLE = new Rop(2, r1, r4, "move-double");
        Type r6 = Type.OBJECT;
        StdTypeList r5 = StdTypeList.OBJECT;
        MOVE_OBJECT = new Rop(2, r6, r5, "move-object");
        MOVE_RETURN_ADDRESS = new Rop(2, Type.RETURN_ADDRESS, StdTypeList.RETURN_ADDRESS, "move-return-address");
        MOVE_PARAM_INT = new Rop(3, r10, r7, "move-param-int");
        MOVE_PARAM_LONG = new Rop(3, r15, r7, "move-param-long");
        MOVE_PARAM_FLOAT = new Rop(3, r12, r7, "move-param-float");
        MOVE_PARAM_DOUBLE = new Rop(3, r1, r7, "move-param-double");
        MOVE_PARAM_OBJECT = new Rop(3, r6, r7, "move-param-object");
        CONST_INT = new Rop(5, r10, r7, "const-int");
        CONST_LONG = new Rop(5, r15, r7, "const-long");
        CONST_FLOAT = new Rop(5, r12, r7, "const-float");
        CONST_DOUBLE = new Rop(5, r1, r7, "const-double");
        StdTypeList r8 = Exceptions.LIST_Error;
        CONST_OBJECT = new Rop(5, r6, r7, r8, "const-object");
        CONST_OBJECT_NOTHROW = new Rop(5, r6, r7, "const-object-nothrow");
        GOTO = new Rop(6, r3, r7, 3, "goto");
        IF_EQZ_INT = new Rop(7, r3, r14, 4, "if-eqz-int");
        IF_NEZ_INT = new Rop(8, r3, r14, 4, "if-nez-int");
        IF_LTZ_INT = new Rop(9, r3, r14, 4, "if-ltz-int");
        IF_GEZ_INT = new Rop(10, r3, r14, 4, "if-gez-int");
        IF_LEZ_INT = new Rop(11, r3, r14, 4, "if-lez-int");
        IF_GTZ_INT = new Rop(12, r3, r14, 4, "if-gtz-int");
        IF_EQZ_OBJECT = new Rop(7, r3, r5, 4, "if-eqz-object");
        IF_NEZ_OBJECT = new Rop(8, r3, r5, 4, "if-nez-object");
        StdTypeList r42 = StdTypeList.INT_INT;
        IF_EQ_INT = new Rop(7, r3, r42, 4, "if-eq-int");
        IF_NE_INT = new Rop(8, r3, r42, 4, "if-ne-int");
        IF_LT_INT = new Rop(9, r3, r42, 4, "if-lt-int");
        IF_GE_INT = new Rop(10, r3, r42, 4, "if-ge-int");
        IF_LE_INT = new Rop(11, r3, r42, 4, "if-le-int");
        IF_GT_INT = new Rop(12, r3, r42, 4, "if-gt-int");
        StdTypeList r43 = StdTypeList.OBJECT_OBJECT;
        IF_EQ_OBJECT = new Rop(7, r3, r43, 4, "if-eq-object");
        IF_NE_OBJECT = new Rop(8, r3, r43, 4, "if-ne-object");
        SWITCH = new Rop(13, r3, r14, 5, PluginsConstants.Settings.TYPE_SWITCH);
        ADD_INT = new Rop(14, r10, r42, "add-int");
        StdTypeList r2 = StdTypeList.LONG_LONG;
        ADD_LONG = new Rop(14, r15, r2, "add-long");
        StdTypeList r62 = StdTypeList.FLOAT_FLOAT;
        ADD_FLOAT = new Rop(14, r12, r62, "add-float");
        StdTypeList r19 = StdTypeList.DOUBLE_DOUBLE;
        ADD_DOUBLE = new Rop(14, r1, r19, 1, "add-double");
        SUB_INT = new Rop(15, r10, r42, "sub-int");
        SUB_LONG = new Rop(15, r15, r2, "sub-long");
        SUB_FLOAT = new Rop(15, r12, r62, "sub-float");
        SUB_DOUBLE = new Rop(15, r1, r19, 1, "sub-double");
        MUL_INT = new Rop(16, r10, r42, "mul-int");
        MUL_LONG = new Rop(16, r15, r2, "mul-long");
        MUL_FLOAT = new Rop(16, r12, r62, "mul-float");
        MUL_DOUBLE = new Rop(16, r1, r19, 1, "mul-double");
        StdTypeList r192 = Exceptions.LIST_Error_ArithmeticException;
        DIV_INT = new Rop(17, r10, r42, r192, "div-int");
        DIV_LONG = new Rop(17, r15, r2, r192, "div-long");
        DIV_FLOAT = new Rop(17, r12, r62, "div-float");
        DIV_DOUBLE = new Rop(17, r1, r19, "div-double");
        REM_INT = new Rop(18, r10, r42, r192, "rem-int");
        REM_LONG = new Rop(18, r15, r2, r192, "rem-long");
        REM_FLOAT = new Rop(18, r12, r62, "rem-float");
        REM_DOUBLE = new Rop(18, r1, r19, "rem-double");
        NEG_INT = new Rop(19, r10, r14, "neg-int");
        NEG_LONG = new Rop(19, r15, r11, "neg-long");
        NEG_FLOAT = new Rop(19, r12, r13, "neg-float");
        NEG_DOUBLE = new Rop(19, r1, r4, "neg-double");
        AND_INT = new Rop(20, r10, r42, "and-int");
        AND_LONG = new Rop(20, r15, r2, "and-long");
        OR_INT = new Rop(21, r10, r42, "or-int");
        OR_LONG = new Rop(21, r15, r2, "or-long");
        XOR_INT = new Rop(22, r10, r42, "xor-int");
        XOR_LONG = new Rop(22, r15, r2, "xor-long");
        SHL_INT = new Rop(23, r10, r42, "shl-int");
        StdTypeList r122 = StdTypeList.LONG_INT;
        SHL_LONG = new Rop(23, r15, r122, "shl-long");
        SHR_INT = new Rop(24, r10, r42, "shr-int");
        SHR_LONG = new Rop(24, r15, r122, "shr-long");
        USHR_INT = new Rop(25, r10, r42, "ushr-int");
        USHR_LONG = new Rop(25, r15, r122, "ushr-long");
        NOT_INT = new Rop(26, r10, r14, "not-int");
        NOT_LONG = new Rop(26, r15, r11, "not-long");
        ADD_CONST_INT = new Rop(14, r10, r14, "add-const-int");
        ADD_CONST_LONG = new Rop(14, r15, r11, "add-const-long");
        ADD_CONST_FLOAT = new Rop(14, r12, r13, "add-const-float");
        ADD_CONST_DOUBLE = new Rop(14, r1, r4, "add-const-double");
        SUB_CONST_INT = new Rop(15, r10, r14, "sub-const-int");
        SUB_CONST_LONG = new Rop(15, r15, r11, "sub-const-long");
        SUB_CONST_FLOAT = new Rop(15, r12, r13, "sub-const-float");
        SUB_CONST_DOUBLE = new Rop(15, r1, r4, "sub-const-double");
        MUL_CONST_INT = new Rop(16, r10, r14, "mul-const-int");
        MUL_CONST_LONG = new Rop(16, r15, r11, "mul-const-long");
        MUL_CONST_FLOAT = new Rop(16, r12, r13, "mul-const-float");
        MUL_CONST_DOUBLE = new Rop(16, r1, r4, "mul-const-double");
        DIV_CONST_INT = new Rop(17, r10, r14, r192, "div-const-int");
        DIV_CONST_LONG = new Rop(17, r15, r11, r192, "div-const-long");
        DIV_CONST_FLOAT = new Rop(17, r12, r13, "div-const-float");
        DIV_CONST_DOUBLE = new Rop(17, r1, r4, "div-const-double");
        REM_CONST_INT = new Rop(18, r10, r14, r192, "rem-const-int");
        REM_CONST_LONG = new Rop(18, r15, r11, r192, "rem-const-long");
        REM_CONST_FLOAT = new Rop(18, r12, r13, "rem-const-float");
        REM_CONST_DOUBLE = new Rop(18, r1, r4, "rem-const-double");
        AND_CONST_INT = new Rop(20, r10, r14, "and-const-int");
        AND_CONST_LONG = new Rop(20, r15, r11, "and-const-long");
        OR_CONST_INT = new Rop(21, r10, r14, "or-const-int");
        OR_CONST_LONG = new Rop(21, r15, r11, "or-const-long");
        XOR_CONST_INT = new Rop(22, r10, r14, "xor-const-int");
        XOR_CONST_LONG = new Rop(22, r15, r11, "xor-const-long");
        SHL_CONST_INT = new Rop(23, r10, r14, "shl-const-int");
        SHL_CONST_LONG = new Rop(23, r15, r14, "shl-const-long");
        SHR_CONST_INT = new Rop(24, r10, r14, "shr-const-int");
        SHR_CONST_LONG = new Rop(24, r15, r14, "shr-const-long");
        USHR_CONST_INT = new Rop(25, r10, r14, "ushr-const-int");
        USHR_CONST_LONG = new Rop(25, r15, r14, "ushr-const-long");
        CMPL_LONG = new Rop(27, r10, r2, "cmpl-long");
        CMPL_FLOAT = new Rop(27, r10, r62, "cmpl-float");
        CMPL_DOUBLE = new Rop(27, r10, r19, "cmpl-double");
        CMPG_FLOAT = new Rop(28, r10, r62, "cmpg-float");
        CMPG_DOUBLE = new Rop(28, r10, r19, "cmpg-double");
        CONV_L2I = new Rop(29, r10, r11, "conv-l2i");
        CONV_F2I = new Rop(29, r10, r13, "conv-f2i");
        CONV_D2I = new Rop(29, r10, r4, "conv-d2i");
        CONV_I2L = new Rop(29, r15, r14, "conv-i2l");
        CONV_F2L = new Rop(29, r15, r13, "conv-f2l");
        CONV_D2L = new Rop(29, r15, r4, "conv-d2l");
        CONV_I2F = new Rop(29, r12, r14, "conv-i2f");
        CONV_L2F = new Rop(29, r12, r11, "conv-l2f");
        CONV_D2F = new Rop(29, r12, r4, "conv-d2f");
        CONV_I2D = new Rop(29, r1, r14, "conv-i2d");
        CONV_L2D = new Rop(29, r1, r11, "conv-l2d");
        CONV_F2D = new Rop(29, r1, r13, "conv-f2d");
        TO_BYTE = new Rop(30, r10, r14, "to-byte");
        TO_CHAR = new Rop(31, r10, r14, "to-char");
        TO_SHORT = new Rop(32, r10, r14, "to-short");
        RETURN_VOID = new Rop(33, r3, r7, 2, "return-void");
        RETURN_INT = new Rop(33, r3, r14, 2, "return-int");
        RETURN_LONG = new Rop(33, r3, r11, 2, "return-long");
        RETURN_FLOAT = new Rop(33, r3, r13, 2, "return-float");
        RETURN_DOUBLE = new Rop(33, r3, r4, 2, "return-double");
        RETURN_OBJECT = new Rop(33, r3, r5, 2, "return-object");
        StdTypeList r52 = Exceptions.LIST_Error_NullPointerException;
        ARRAY_LENGTH = new Rop(34, r10, r5, r52, "array-length");
        StdTypeList r44 = StdTypeList.THROWABLE;
        THROW = new Rop(35, r3, r44, r44, "throw");
        MONITOR_ENTER = new Rop(36, r3, r5, r52, "monitor-enter");
        MONITOR_EXIT = new Rop(37, r3, r5, Exceptions.LIST_Error_Null_IllegalMonitorStateException, "monitor-exit");
        StdTypeList r112 = StdTypeList.INTARR_INT;
        StdTypeList r123 = Exceptions.LIST_Error_Null_ArrayIndexOutOfBounds;
        AGET_INT = new Rop(38, r10, r112, r123, "aget-int");
        AGET_LONG = new Rop(38, r15, StdTypeList.LONGARR_INT, r123, "aget-long");
        AGET_FLOAT = new Rop(38, r12, StdTypeList.FLOATARR_INT, r123, "aget-float");
        AGET_DOUBLE = new Rop(38, r1, StdTypeList.DOUBLEARR_INT, r123, "aget-double");
        AGET_OBJECT = new Rop(38, r6, StdTypeList.OBJECTARR_INT, r123, "aget-object");
        AGET_BOOLEAN = new Rop(38, r10, StdTypeList.BOOLEANARR_INT, r123, "aget-boolean");
        AGET_BYTE = new Rop(38, r10, StdTypeList.BYTEARR_INT, r123, "aget-byte");
        AGET_CHAR = new Rop(38, r10, StdTypeList.CHARARR_INT, r123, "aget-char");
        AGET_SHORT = new Rop(38, r10, StdTypeList.SHORTARR_INT, r123, "aget-short");
        APUT_INT = new Rop(39, r3, StdTypeList.INT_INTARR_INT, r123, "aput-int");
        APUT_LONG = new Rop(39, r3, StdTypeList.LONG_LONGARR_INT, r123, "aput-long");
        APUT_FLOAT = new Rop(39, r3, StdTypeList.FLOAT_FLOATARR_INT, r123, "aput-float");
        APUT_DOUBLE = new Rop(39, r3, StdTypeList.DOUBLE_DOUBLEARR_INT, r123, "aput-double");
        StdTypeList r45 = StdTypeList.OBJECT_OBJECTARR_INT;
        StdTypeList r53 = Exceptions.LIST_Error_Null_ArrayIndex_ArrayStore;
        APUT_OBJECT = new Rop(39, r3, r45, r53, "aput-object");
        APUT_BOOLEAN = new Rop(39, r3, StdTypeList.INT_BOOLEANARR_INT, r53, "aput-boolean");
        APUT_BYTE = new Rop(39, r3, StdTypeList.INT_BYTEARR_INT, r53, "aput-byte");
        APUT_CHAR = new Rop(39, r3, StdTypeList.INT_CHARARR_INT, r53, "aput-char");
        APUT_SHORT = new Rop(39, r3, StdTypeList.INT_SHORTARR_INT, r53, "aput-short");
        NEW_INSTANCE = new Rop(40, r6, r7, r8, "new-instance");
        Type r132 = Type.INT_ARRAY;
        StdTypeList r152 = Exceptions.LIST_Error_NegativeArraySizeException;
        NEW_ARRAY_INT = new Rop(41, r132, r14, r152, "new-array-int");
        NEW_ARRAY_LONG = new Rop(41, Type.LONG_ARRAY, r14, r152, "new-array-long");
        NEW_ARRAY_FLOAT = new Rop(41, Type.FLOAT_ARRAY, r14, r152, "new-array-float");
        NEW_ARRAY_DOUBLE = new Rop(41, Type.DOUBLE_ARRAY, r14, r152, "new-array-double");
        NEW_ARRAY_BOOLEAN = new Rop(41, Type.BOOLEAN_ARRAY, r14, r152, "new-array-boolean");
        NEW_ARRAY_BYTE = new Rop(41, Type.BYTE_ARRAY, r14, r152, "new-array-byte");
        NEW_ARRAY_CHAR = new Rop(41, Type.CHAR_ARRAY, r14, r152, "new-array-char");
        NEW_ARRAY_SHORT = new Rop(41, Type.SHORT_ARRAY, r14, r152, "new-array-short");
        CHECK_CAST = new Rop(43, r3, r5, Exceptions.LIST_Error_ClassCastException, "check-cast");
        INSTANCE_OF = new Rop(44, r10, r5, r8, "instance-of");
        GET_FIELD_INT = new Rop(45, r10, r5, r52, "get-field-int");
        GET_FIELD_LONG = new Rop(45, r15, r5, r52, "get-field-long");
        GET_FIELD_FLOAT = new Rop(45, r12, r5, r52, "get-field-float");
        GET_FIELD_DOUBLE = new Rop(45, r1, r5, r52, "get-field-double");
        GET_FIELD_OBJECT = new Rop(45, r6, r5, r52, "get-field-object");
        GET_FIELD_BOOLEAN = new Rop(45, r10, r5, r52, "get-field-boolean");
        GET_FIELD_BYTE = new Rop(45, r10, r5, r52, "get-field-byte");
        GET_FIELD_CHAR = new Rop(45, r10, r5, r52, "get-field-char");
        GET_FIELD_SHORT = new Rop(45, r10, r5, r52, "get-field-short");
        GET_STATIC_INT = new Rop(46, r10, r7, r8, "get-static-int");
        GET_STATIC_LONG = new Rop(46, r15, r7, r8, "get-static-long");
        GET_STATIC_FLOAT = new Rop(46, r12, r7, r8, "get-static-float");
        GET_STATIC_DOUBLE = new Rop(46, r1, r7, r8, "get-static-double");
        GET_STATIC_OBJECT = new Rop(46, r6, r7, r8, "get-static-object");
        GET_STATIC_BOOLEAN = new Rop(46, r10, r7, r8, "get-field-boolean");
        GET_STATIC_BYTE = new Rop(46, r10, r7, r8, "get-field-byte");
        GET_STATIC_CHAR = new Rop(46, r10, r7, r8, "get-field-char");
        GET_STATIC_SHORT = new Rop(46, r10, r7, r8, "get-field-short");
        StdTypeList r46 = StdTypeList.INT_OBJECT;
        PUT_FIELD_INT = new Rop(47, r3, r46, r52, "put-field-int");
        PUT_FIELD_LONG = new Rop(47, r3, StdTypeList.LONG_OBJECT, r52, "put-field-long");
        PUT_FIELD_FLOAT = new Rop(47, r3, StdTypeList.FLOAT_OBJECT, r52, "put-field-float");
        PUT_FIELD_DOUBLE = new Rop(47, r3, StdTypeList.DOUBLE_OBJECT, r52, "put-field-double");
        PUT_FIELD_OBJECT = new Rop(47, r3, r43, r52, "put-field-object");
        PUT_FIELD_BOOLEAN = new Rop(47, r3, r46, r52, "put-field-boolean");
        PUT_FIELD_BYTE = new Rop(47, r3, r46, r52, "put-field-byte");
        PUT_FIELD_CHAR = new Rop(47, r3, r46, r52, "put-field-char");
        PUT_FIELD_SHORT = new Rop(47, r3, r46, r52, "put-field-short");
        PUT_STATIC_INT = new Rop(48, r3, r14, r8, "put-static-int");
        PUT_STATIC_LONG = new Rop(48, r3, r11, r8, "put-static-long");
        PUT_STATIC_FLOAT = new Rop(48, r3, r13, r8, "put-static-float");
        PUT_STATIC_DOUBLE = new Rop(48, r3, r4, r8, "put-static-double");
        PUT_STATIC_OBJECT = new Rop(48, r3, r5, r8, "put-static-object");
        PUT_STATIC_BOOLEAN = new Rop(48, r3, r14, r8, "put-static-boolean");
        PUT_STATIC_BYTE = new Rop(48, r3, r14, r8, "put-static-byte");
        PUT_STATIC_CHAR = new Rop(48, r3, r14, r8, "put-static-char");
        PUT_STATIC_SHORT = new Rop(48, r3, r14, r8, "put-static-short");
        MARK_LOCAL_INT = new Rop(54, r3, r14, "mark-local-int");
        MARK_LOCAL_LONG = new Rop(54, r3, r11, "mark-local-long");
        MARK_LOCAL_FLOAT = new Rop(54, r3, r13, "mark-local-float");
        MARK_LOCAL_DOUBLE = new Rop(54, r3, r4, "mark-local-double");
        MARK_LOCAL_OBJECT = new Rop(54, r3, r5, "mark-local-object");
        FILL_ARRAY_DATA = new Rop(57, r3, r7, "fill-array-data");
    }

    private Rops() {
    }

    public static Rop opAdd(TypeList r9) {
        return pickBinaryOp(r9, ADD_CONST_INT, ADD_CONST_LONG, ADD_CONST_FLOAT, ADD_CONST_DOUBLE, ADD_INT, ADD_LONG, ADD_FLOAT, ADD_DOUBLE);
    }

    public static Rop opAget(TypeBearer r1) {
        switch(r1.getBasicType()) {
            case 1: goto L23;
            case 2: goto L21;
            case 3: goto L19;
            case 4: goto L17;
            case 5: goto L15;
            case 6: goto L13;
            case 7: goto L11;
            case 8: goto L9;
            case 9: goto L7;
            default: goto L5;
        };
    L5:
        return throwBadType(r1);
    L7:
        return AGET_OBJECT;
    L9:
        return AGET_SHORT;
    L11:
        return AGET_LONG;
    L13:
        return AGET_INT;
    L15:
        return AGET_FLOAT;
    L17:
        return AGET_DOUBLE;
    L19:
        return AGET_CHAR;
    L21:
        return AGET_BYTE;
    L23:
        return AGET_BOOLEAN;
    }

    public static Rop opAnd(TypeList r9) {
        return pickBinaryOp(r9, AND_CONST_INT, AND_CONST_LONG, null, null, AND_INT, AND_LONG, null, null);
    }

    public static Rop opAput(TypeBearer r1) {
        switch(r1.getBasicType()) {
            case 1: goto L23;
            case 2: goto L21;
            case 3: goto L19;
            case 4: goto L17;
            case 5: goto L15;
            case 6: goto L13;
            case 7: goto L11;
            case 8: goto L9;
            case 9: goto L7;
            default: goto L5;
        };
    L5:
        return throwBadType(r1);
    L7:
        return APUT_OBJECT;
    L9:
        return APUT_SHORT;
    L11:
        return APUT_LONG;
    L13:
        return APUT_INT;
    L15:
        return APUT_FLOAT;
    L17:
        return APUT_DOUBLE;
    L19:
        return APUT_CHAR;
    L21:
        return APUT_BYTE;
    L23:
        return APUT_BOOLEAN;
    }

    public static Rop opCmpg(TypeBearer r2) {
        int r0 = r2.getBasicType();
        if (r0 == 4) goto L11;
        if (r0 == 5) goto L9;
        return throwBadType(r2);
    L9:
        return CMPG_FLOAT;
    L11:
        return CMPG_DOUBLE;
    }

    public static Rop opCmpl(TypeBearer r2) {
        int r0 = r2.getBasicType();
        if (r0 == 4) goto L15;
        if (r0 == 5) goto L13;
        if (r0 == 7) goto L11;
        return throwBadType(r2);
    L11:
        return CMPL_LONG;
    L13:
        return CMPL_FLOAT;
    L15:
        return CMPL_DOUBLE;
    }

    public static Rop opConst(TypeBearer r2) {
        if (r2.getType() == Type.KNOWN_NULL) goto L5;
        int r0 = r2.getBasicFrameType();
        if (r0 == 4) goto L27;
        if (r0 == 5) goto L25;
        if (r0 == 6) goto L23;
        if (r0 == 7) goto L21;
        if (r0 == 9) goto L19;
        return throwBadType(r2);
    L19:
        return CONST_OBJECT;
    L21:
        return CONST_LONG;
    L23:
        return CONST_INT;
    L25:
        return CONST_FLOAT;
    L27:
        return CONST_DOUBLE;
    L5:
        return CONST_OBJECT_NOTHROW;
    }

    public static Rop opConv(TypeBearer r6, TypeBearer r7) {
        int r0 = r6.getBasicFrameType();
        int r1 = r7.getBasicFrameType();
        if (r1 == 4) goto L37;
        if (r1 == 5) goto L27;
        if (r1 == 6) goto L8;
        if (r1 != 7) goto L41;
    L11:
        if (r0 == 4) goto L20;
        if (r0 == 5) goto L18;
        if (r0 != 6) goto L27;
        return CONV_L2I;
    L18:
        return CONV_L2F;
    L20:
        return CONV_L2D;
    L41:
        return throwBadTypes(StdTypeList.make(r6.getType(), r7.getType()));
    L8:
        if (r0 == 4) goto L26;
        if (r0 == 5) goto L24;
        if (r0 != 7) goto L11;
        return CONV_I2L;
    L24:
        return CONV_I2F;
    L26:
        return CONV_I2D;
    L27:
        if (r0 == 4) goto L36;
        if (r0 == 6) goto L34;
        if (r0 != 7) goto L37;
        return CONV_F2L;
    L34:
        return CONV_F2I;
    L36:
        return CONV_F2D;
    L37:
        if (r0 == 5) goto L47;
        if (r0 == 6) goto L45;
        if (r0 != 7) goto L41;
        return CONV_D2L;
    L45:
        return CONV_D2I;
    L47:
        return CONV_D2F;
    }

    public static Rop opDiv(TypeList r9) {
        return pickBinaryOp(r9, DIV_CONST_INT, DIV_CONST_LONG, DIV_CONST_FLOAT, DIV_CONST_DOUBLE, DIV_INT, DIV_LONG, DIV_FLOAT, DIV_DOUBLE);
    }

    public static Rop opFilledNewArray(TypeBearer r2, int r3) {
        Type r0 = r2.getType().getComponentType();
        if (r0.isCategory2() == true) goto L5;
        if (r3 < 0) goto L12;
        StdTypeList r22 = new StdTypeList(r3);
        int r1 = 0;
    L8:
        if (r1 >= r3) goto L11;
        r22.set(r1, r0);
        r1 = r1 + 1;
        goto L8
    L11:
        return new Rop(42, r22, Exceptions.LIST_Error);
    L12:
        f$$ExternalSyntheticBUOutline1.m("count < 0");
        return null;
    L5:
        return throwBadType(r2);
    }

    public static Rop opGetField(TypeBearer r1) {
        switch(r1.getBasicType()) {
            case 1: goto L23;
            case 2: goto L21;
            case 3: goto L19;
            case 4: goto L17;
            case 5: goto L15;
            case 6: goto L13;
            case 7: goto L11;
            case 8: goto L9;
            case 9: goto L7;
            default: goto L5;
        };
    L5:
        return throwBadType(r1);
    L7:
        return GET_FIELD_OBJECT;
    L9:
        return GET_FIELD_SHORT;
    L11:
        return GET_FIELD_LONG;
    L13:
        return GET_FIELD_INT;
    L15:
        return GET_FIELD_FLOAT;
    L17:
        return GET_FIELD_DOUBLE;
    L19:
        return GET_FIELD_CHAR;
    L21:
        return GET_FIELD_BYTE;
    L23:
        return GET_FIELD_BOOLEAN;
    }

    public static Rop opGetStatic(TypeBearer r1) {
        switch(r1.getBasicType()) {
            case 1: goto L23;
            case 2: goto L21;
            case 3: goto L19;
            case 4: goto L17;
            case 5: goto L15;
            case 6: goto L13;
            case 7: goto L11;
            case 8: goto L9;
            case 9: goto L7;
            default: goto L5;
        };
    L5:
        return throwBadType(r1);
    L7:
        return GET_STATIC_OBJECT;
    L9:
        return GET_STATIC_SHORT;
    L11:
        return GET_STATIC_LONG;
    L13:
        return GET_STATIC_INT;
    L15:
        return GET_STATIC_FLOAT;
    L17:
        return GET_STATIC_DOUBLE;
    L19:
        return GET_STATIC_CHAR;
    L21:
        return GET_STATIC_BYTE;
    L23:
        return GET_STATIC_BOOLEAN;
    }

    public static Rop opIfEq(TypeList r4) {
        return pickIf(r4, IF_EQZ_INT, IF_EQZ_OBJECT, IF_EQ_INT, IF_EQ_OBJECT);
    }

    public static Rop opIfGe(TypeList r3) {
        return pickIf(r3, IF_GEZ_INT, null, IF_GE_INT, null);
    }

    public static Rop opIfGt(TypeList r3) {
        return pickIf(r3, IF_GTZ_INT, null, IF_GT_INT, null);
    }

    public static Rop opIfLe(TypeList r3) {
        return pickIf(r3, IF_LEZ_INT, null, IF_LE_INT, null);
    }

    public static Rop opIfLt(TypeList r3) {
        return pickIf(r3, IF_LTZ_INT, null, IF_LT_INT, null);
    }

    public static Rop opIfNe(TypeList r4) {
        return pickIf(r4, IF_NEZ_INT, IF_NEZ_OBJECT, IF_NE_INT, IF_NE_OBJECT);
    }

    private static Rop opInvokeCustom(Prototype r3) {
        return new Rop(59, r3.getParameterFrameTypes(), StdTypeList.THROWABLE);
    }

    public static Rop opInvokeDirect(Prototype r3) {
        return new Rop(52, r3.getParameterFrameTypes(), StdTypeList.THROWABLE);
    }

    public static Rop opInvokeInterface(Prototype r3) {
        return new Rop(53, r3.getParameterFrameTypes(), StdTypeList.THROWABLE);
    }

    public static Rop opInvokePolymorphic(Prototype r3) {
        return new Rop(58, r3.getParameterFrameTypes(), StdTypeList.THROWABLE);
    }

    public static Rop opInvokeStatic(Prototype r3) {
        return new Rop(49, r3.getParameterFrameTypes(), StdTypeList.THROWABLE);
    }

    public static Rop opInvokeSuper(Prototype r3) {
        return new Rop(51, r3.getParameterFrameTypes(), StdTypeList.THROWABLE);
    }

    public static Rop opInvokeVirtual(Prototype r3) {
        return new Rop(50, r3.getParameterFrameTypes(), StdTypeList.THROWABLE);
    }

    public static Rop opMarkLocal(TypeBearer r2) {
        int r0 = r2.getBasicFrameType();
        if (r0 == 4) goto L23;
        if (r0 == 5) goto L21;
        if (r0 == 6) goto L19;
        if (r0 == 7) goto L17;
        if (r0 == 9) goto L15;
        return throwBadType(r2);
    L15:
        return MARK_LOCAL_OBJECT;
    L17:
        return MARK_LOCAL_LONG;
    L19:
        return MARK_LOCAL_INT;
    L21:
        return MARK_LOCAL_FLOAT;
    L23:
        return MARK_LOCAL_DOUBLE;
    }

    public static Rop opMove(TypeBearer r1) {
        switch(r1.getBasicFrameType()) {
            case 4: goto L17;
            case 5: goto L15;
            case 6: goto L13;
            case 7: goto L11;
            case 8: goto L5;
            case 9: goto L9;
            case 10: goto L7;
            default: goto L5;
        };
    L5:
        return throwBadType(r1);
    L7:
        return MOVE_RETURN_ADDRESS;
    L9:
        return MOVE_OBJECT;
    L11:
        return MOVE_LONG;
    L13:
        return MOVE_INT;
    L15:
        return MOVE_FLOAT;
    L17:
        return MOVE_DOUBLE;
    }

    public static Rop opMoveException(TypeBearer r4) {
        return new Rop(4, r4.getType(), StdTypeList.EMPTY, null);
    }

    public static Rop opMoveParam(TypeBearer r2) {
        int r0 = r2.getBasicFrameType();
        if (r0 == 4) goto L23;
        if (r0 == 5) goto L21;
        if (r0 == 6) goto L19;
        if (r0 == 7) goto L17;
        if (r0 == 9) goto L15;
        return throwBadType(r2);
    L15:
        return MOVE_PARAM_OBJECT;
    L17:
        return MOVE_PARAM_LONG;
    L19:
        return MOVE_PARAM_INT;
    L21:
        return MOVE_PARAM_FLOAT;
    L23:
        return MOVE_PARAM_DOUBLE;
    }

    public static Rop opMoveResult(TypeBearer r4) {
        return new Rop(55, r4.getType(), StdTypeList.EMPTY, null);
    }

    public static Rop opMoveResultPseudo(TypeBearer r4) {
        return new Rop(56, r4.getType(), StdTypeList.EMPTY, null);
    }

    public static Rop opMul(TypeList r9) {
        return pickBinaryOp(r9, MUL_CONST_INT, MUL_CONST_LONG, MUL_CONST_FLOAT, MUL_CONST_DOUBLE, MUL_INT, MUL_LONG, MUL_FLOAT, MUL_DOUBLE);
    }

    public static Rop opNeg(TypeBearer r2) {
        int r0 = r2.getBasicFrameType();
        if (r0 == 4) goto L19;
        if (r0 == 5) goto L17;
        if (r0 == 6) goto L15;
        if (r0 == 7) goto L13;
        return throwBadType(r2);
    L13:
        return NEG_LONG;
    L15:
        return NEG_INT;
    L17:
        return NEG_FLOAT;
    L19:
        return NEG_DOUBLE;
    }

    public static Rop opNewArray(TypeBearer r6) {
        Type r2 = r6.getType();
        switch(r2.getComponentType().getBasicType()) {
            case 1: goto L23;
            case 2: goto L21;
            case 3: goto L19;
            case 4: goto L17;
            case 5: goto L15;
            case 6: goto L13;
            case 7: goto L11;
            case 8: goto L9;
            case 9: goto L7;
            default: goto L5;
        };
    L5:
        return throwBadType(r2);
    L7:
        return new Rop(41, r2, StdTypeList.INT, Exceptions.LIST_Error_NegativeArraySizeException, "new-array-object");
    L9:
        return NEW_ARRAY_SHORT;
    L11:
        return NEW_ARRAY_LONG;
    L13:
        return NEW_ARRAY_INT;
    L15:
        return NEW_ARRAY_FLOAT;
    L17:
        return NEW_ARRAY_DOUBLE;
    L19:
        return NEW_ARRAY_CHAR;
    L21:
        return NEW_ARRAY_BYTE;
    L23:
        return NEW_ARRAY_BOOLEAN;
    }

    public static Rop opNot(TypeBearer r2) {
        int r0 = r2.getBasicFrameType();
        if (r0 == 6) goto L11;
        if (r0 == 7) goto L9;
        return throwBadType(r2);
    L9:
        return NOT_LONG;
    L11:
        return NOT_INT;
    }

    public static Rop opOr(TypeList r9) {
        return pickBinaryOp(r9, OR_CONST_INT, OR_CONST_LONG, null, null, OR_INT, OR_LONG, null, null);
    }

    public static Rop opPutField(TypeBearer r1) {
        switch(r1.getBasicType()) {
            case 1: goto L23;
            case 2: goto L21;
            case 3: goto L19;
            case 4: goto L17;
            case 5: goto L15;
            case 6: goto L13;
            case 7: goto L11;
            case 8: goto L9;
            case 9: goto L7;
            default: goto L5;
        };
    L5:
        return throwBadType(r1);
    L7:
        return PUT_FIELD_OBJECT;
    L9:
        return PUT_FIELD_SHORT;
    L11:
        return PUT_FIELD_LONG;
    L13:
        return PUT_FIELD_INT;
    L15:
        return PUT_FIELD_FLOAT;
    L17:
        return PUT_FIELD_DOUBLE;
    L19:
        return PUT_FIELD_CHAR;
    L21:
        return PUT_FIELD_BYTE;
    L23:
        return PUT_FIELD_BOOLEAN;
    }

    public static Rop opPutStatic(TypeBearer r1) {
        switch(r1.getBasicType()) {
            case 1: goto L23;
            case 2: goto L21;
            case 3: goto L19;
            case 4: goto L17;
            case 5: goto L15;
            case 6: goto L13;
            case 7: goto L11;
            case 8: goto L9;
            case 9: goto L7;
            default: goto L5;
        };
    L5:
        return throwBadType(r1);
    L7:
        return PUT_STATIC_OBJECT;
    L9:
        return PUT_STATIC_SHORT;
    L11:
        return PUT_STATIC_LONG;
    L13:
        return PUT_STATIC_INT;
    L15:
        return PUT_STATIC_FLOAT;
    L17:
        return PUT_STATIC_DOUBLE;
    L19:
        return PUT_STATIC_CHAR;
    L21:
        return PUT_STATIC_BYTE;
    L23:
        return PUT_STATIC_BOOLEAN;
    }

    public static Rop opRem(TypeList r9) {
        return pickBinaryOp(r9, REM_CONST_INT, REM_CONST_LONG, REM_CONST_FLOAT, REM_CONST_DOUBLE, REM_INT, REM_LONG, REM_FLOAT, REM_DOUBLE);
    }

    public static Rop opReturn(TypeBearer r2) {
        int r0 = r2.getBasicFrameType();
        if (r0 == 0) goto L27;
        if (r0 == 9) goto L25;
        if (r0 == 4) goto L23;
        if (r0 == 5) goto L21;
        if (r0 == 6) goto L19;
        if (r0 == 7) goto L17;
        return throwBadType(r2);
    L17:
        return RETURN_LONG;
    L19:
        return RETURN_INT;
    L21:
        return RETURN_FLOAT;
    L23:
        return RETURN_DOUBLE;
    L25:
        return RETURN_OBJECT;
    L27:
        return RETURN_VOID;
    }

    public static Rop opShl(TypeList r9) {
        return pickBinaryOp(r9, SHL_CONST_INT, SHL_CONST_LONG, null, null, SHL_INT, SHL_LONG, null, null);
    }

    public static Rop opShr(TypeList r9) {
        return pickBinaryOp(r9, SHR_CONST_INT, SHR_CONST_LONG, null, null, SHR_INT, SHR_LONG, null, null);
    }

    public static Rop opSub(TypeList r9) {
        return pickBinaryOp(r9, SUB_CONST_INT, SUB_CONST_LONG, SUB_CONST_FLOAT, SUB_CONST_DOUBLE, SUB_INT, SUB_LONG, SUB_FLOAT, SUB_DOUBLE);
    }

    public static Rop opUshr(TypeList r9) {
        return pickBinaryOp(r9, USHR_CONST_INT, USHR_CONST_LONG, null, null, USHR_INT, USHR_LONG, null, null);
    }

    public static Rop opXor(TypeList r9) {
        return pickBinaryOp(r9, XOR_CONST_INT, XOR_CONST_LONG, null, null, XOR_INT, XOR_LONG, null, null);
    }

    private static Rop pickBinaryOp(TypeList r8, Rop r9, Rop r10, Rop r11, Rop r12, Rop r13, Rop r14, Rop r15, Rop r16) {
        int r0 = r8.getType(0).getBasicFrameType();
        int r1 = r8.size();
        if (r1 != 1) goto L5;
        if (r0 == 4) goto L22;
        if (r0 == 5) goto L23;
        if (r0 == 6) goto L21;
        if (r0 == 7) goto L20;
        r11 = null;
        goto L23
    L20:
        return r10;
    L21:
        return r9;
    L23:
        r15 = r11;
    L24:
        if (r15 == null) goto L26;
        return r15;
    L26:
        return throwBadTypes(r8);
    L22:
        r11 = r12;
        goto L23
    L5:
        if (r1 != 2) goto L11;
        if (r0 == 4) goto L14;
        if (r0 == 5) goto L24;
        if (r0 == 6) goto L13;
        if (r0 != 7) goto L11;
        return r14;
    L13:
        return r13;
    L14:
        r15 = r16;
    L11:
        r15 = null;
        goto L24
    }

    private static Rop pickIf(TypeList r5, Rop r6, Rop r7, Rop r8, Rop r9) {
        int r0 = r5.size();
        if (r0 != 1) goto L5;
        int r82 = r5.getType(0).getBasicFrameType();
        if (r82 == 6) goto L23;
        if (r82 != 9) goto L22;
        if (r7 == null) goto L22;
        return r7;
    L22:
        return throwBadTypes(r5);
    L23:
        return r6;
    L5:
        if (r0 != 2) goto L22;
        int r62 = r5.getType(0).getBasicFrameType();
        if (r62 != r5.getType(1).getBasicFrameType()) goto L22;
        if (r62 == 6) goto L14;
        if (r62 != 9) goto L22;
        if (r9 == null) goto L22;
        return r9;
    L14:
        return r8;
    }

    public static Rop ropFor(int r1, TypeBearer r2, TypeList r3, Constant r4) {
        switch(r1) {
            case 1: goto L125;
            case 2: goto L123;
            case 3: goto L121;
            case 4: goto L119;
            case 5: goto L117;
            case 6: goto L115;
            case 7: goto L113;
            case 8: goto L111;
            case 9: goto L109;
            case 10: goto L107;
            case 11: goto L105;
            case 12: goto L103;
            case 13: goto L101;
            case 14: goto L99;
            case 15: goto L97;
            case 16: goto L95;
            case 17: goto L93;
            case 18: goto L91;
            case 19: goto L89;
            case 20: goto L87;
            case 21: goto L85;
            case 22: goto L83;
            case 23: goto L81;
            case 24: goto L79;
            case 25: goto L77;
            case 26: goto L75;
            case 27: goto L73;
            case 28: goto L71;
            case 29: goto L69;
            case 30: goto L67;
            case 31: goto L65;
            case 32: goto L63;
            case 33: goto L57;
            case 34: goto L55;
            case 35: goto L53;
            case 36: goto L51;
            case 37: goto L49;
            case 38: goto L42;
            case 39: goto L36;
            case 40: goto L35;
            case 41: goto L33;
            case 42: goto L4;
            case 43: goto L31;
            case 44: goto L29;
            case 45: goto L27;
            case 46: goto L25;
            case 47: goto L23;
            case 48: goto L21;
            case 49: goto L19;
            case 50: goto L16;
            case 51: goto L14;
            case 52: goto L12;
            case 53: goto L10;
            case 54: goto L4;
            case 55: goto L4;
            case 56: goto L4;
            case 57: goto L4;
            case 58: goto L8;
            case 59: goto L7;
            default: goto L4;
        };
    L4:
        Instance$$ExternalSyntheticBUOutline0.m("unknown opcode ", RegOps.opName(r1));
        return null;
    L8:
        CstMethodRef r42 = (CstMethodRef) r4;
        return opInvokePolymorphic(r42.getPrototype().withFirstParameter(r42.getDefiningClass().getClassType()));
    L10:
        CstMethodRef r43 = (CstMethodRef) r4;
        return opInvokeInterface(r43.getPrototype().withFirstParameter(r43.getDefiningClass().getClassType()));
    L12:
        CstMethodRef r44 = (CstMethodRef) r4;
        return opInvokeDirect(r44.getPrototype().withFirstParameter(r44.getDefiningClass().getClassType()));
    L14:
        CstMethodRef r45 = (CstMethodRef) r4;
        return opInvokeSuper(r45.getPrototype().withFirstParameter(r45.getDefiningClass().getClassType()));
    L16:
        CstMethodRef r46 = (CstMethodRef) r4;
        return opInvokeVirtual(r46.getPrototype().withFirstParameter(r46.getDefiningClass().getClassType()));
    L36:
        Type r12 = r3.getType(1);
        if (r12 != Type.KNOWN_NULL) goto L39;
        Type r13 = r3.getType(0);
    L41:
        return opAput(r13);
    L39:
        r13 = r12.getComponentType();
        goto L41
    L42:
        Type r14 = r3.getType(0);
        if (r14 != Type.KNOWN_NULL) goto L45;
        Type r15 = r2.getType();
    L47:
        return opAget(r15);
    L45:
        r15 = r14.getComponentType();
        goto L47
    L7:
        return opInvokeCustom(((CstCallSiteRef) r4).getPrototype());
    L19:
        return opInvokeStatic(((CstMethodRef) r4).getPrototype());
    L21:
        return opPutStatic(r3.getType(0));
    L23:
        return opPutField(r3.getType(0));
    L25:
        return opGetStatic(r2);
    L27:
        return opGetField(r2);
    L29:
        return INSTANCE_OF;
    L31:
        return CHECK_CAST;
    L33:
        return opNewArray(r2.getType());
    L35:
        return NEW_INSTANCE;
    L49:
        return MONITOR_EXIT;
    L51:
        return MONITOR_ENTER;
    L53:
        return THROW;
    L55:
        return ARRAY_LENGTH;
    L57:
        if (r3.size() != 0) goto L61;
        return RETURN_VOID;
    L61:
        return opReturn(r3.getType(0));
    L63:
        return TO_SHORT;
    L65:
        return TO_CHAR;
    L67:
        return TO_BYTE;
    L69:
        return opConv(r2, r3.getType(0));
    L71:
        return opCmpg(r3.getType(0));
    L73:
        return opCmpl(r3.getType(0));
    L75:
        return opNot(r2);
    L77:
        return opUshr(r3);
    L79:
        return opShr(r3);
    L81:
        return opShl(r3);
    L83:
        return opXor(r3);
    L85:
        return opOr(r3);
    L87:
        return opAnd(r3);
    L89:
        return opNeg(r2);
    L91:
        return opRem(r3);
    L93:
        return opDiv(r3);
    L95:
        return opMul(r3);
    L97:
        return opSub(r3);
    L99:
        return opAdd(r3);
    L101:
        return SWITCH;
    L103:
        return opIfGt(r3);
    L105:
        return opIfLe(r3);
    L107:
        return opIfGe(r3);
    L109:
        return opIfLt(r3);
    L111:
        return opIfNe(r3);
    L113:
        return opIfEq(r3);
    L115:
        return GOTO;
    L117:
        return opConst(r2);
    L119:
        return opMoveException(r2);
    L121:
        return opMoveParam(r2);
    L123:
        return opMove(r2);
    L125:
        return NOP;
    }

    private static Rop throwBadType(TypeBearer r3) {
        throw new IllegalArgumentException("bad type: " + r3);
    }

    private static Rop throwBadTypes(TypeList r3) {
        throw new IllegalArgumentException("bad types: " + r3);
    }
}
