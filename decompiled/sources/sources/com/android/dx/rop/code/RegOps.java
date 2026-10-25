package com.android.dx.rop.code;

import com.android.dx.util.Hex;
import com.exteragram.messenger.plugins.PluginsConstants;
import org.mvel2.math.MathProcessor$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class RegOps {
    public static final int ADD = 14;
    public static final int AGET = 38;
    public static final int AND = 20;
    public static final int APUT = 39;
    public static final int ARRAY_LENGTH = 34;
    public static final int CHECK_CAST = 43;
    public static final int CMPG = 28;
    public static final int CMPL = 27;
    public static final int CONST = 5;
    public static final int CONV = 29;
    public static final int DIV = 17;
    public static final int FILLED_NEW_ARRAY = 42;
    public static final int FILL_ARRAY_DATA = 57;
    public static final int GET_FIELD = 45;
    public static final int GET_STATIC = 46;
    public static final int GOTO = 6;
    public static final int IF_EQ = 7;
    public static final int IF_GE = 10;
    public static final int IF_GT = 12;
    public static final int IF_LE = 11;
    public static final int IF_LT = 9;
    public static final int IF_NE = 8;
    public static final int INSTANCE_OF = 44;
    public static final int INVOKE_CUSTOM = 59;
    public static final int INVOKE_DIRECT = 52;
    public static final int INVOKE_INTERFACE = 53;
    public static final int INVOKE_POLYMORPHIC = 58;
    public static final int INVOKE_STATIC = 49;
    public static final int INVOKE_SUPER = 51;
    public static final int INVOKE_VIRTUAL = 50;
    public static final int MARK_LOCAL = 54;
    public static final int MONITOR_ENTER = 36;
    public static final int MONITOR_EXIT = 37;
    public static final int MOVE = 2;
    public static final int MOVE_EXCEPTION = 4;
    public static final int MOVE_PARAM = 3;
    public static final int MOVE_RESULT = 55;
    public static final int MOVE_RESULT_PSEUDO = 56;
    public static final int MUL = 16;
    public static final int NEG = 19;
    public static final int NEW_ARRAY = 41;
    public static final int NEW_INSTANCE = 40;
    public static final int NOP = 1;
    public static final int NOT = 26;
    public static final int OR = 21;
    public static final int PUT_FIELD = 47;
    public static final int PUT_STATIC = 48;
    public static final int REM = 18;
    public static final int RETURN = 33;
    public static final int SHL = 23;
    public static final int SHR = 24;
    public static final int SUB = 15;
    public static final int SWITCH = 13;
    public static final int THROW = 35;
    public static final int TO_BYTE = 30;
    public static final int TO_CHAR = 31;
    public static final int TO_SHORT = 32;
    public static final int USHR = 25;
    public static final int XOR = 22;

    private RegOps() {
    }

    public static int flippedIfOpcode(int r1) {
        switch(r1) {
            case 7: goto L13;
            case 8: goto L13;
            case 9: goto L11;
            case 10: goto L9;
            case 11: goto L7;
            case 12: goto L5;
            default: goto L3;
        };
    L3:
        MathProcessor$$ExternalSyntheticBUOutline0.m("Unrecognized IF regop: ", r1);
        return 0;
    L5:
        return 9;
    L7:
        return 10;
    L9:
        return 11;
    L11:
        return 12;
    L13:
        return r1;
    }

    public static String opName(int r2) {
        switch(r2) {
            case 1: goto L119;
            case 2: goto L117;
            case 3: goto L115;
            case 4: goto L113;
            case 5: goto L111;
            case 6: goto L109;
            case 7: goto L107;
            case 8: goto L105;
            case 9: goto L103;
            case 10: goto L101;
            case 11: goto L99;
            case 12: goto L97;
            case 13: goto L95;
            case 14: goto L93;
            case 15: goto L91;
            case 16: goto L89;
            case 17: goto L87;
            case 18: goto L85;
            case 19: goto L83;
            case 20: goto L81;
            case 21: goto L79;
            case 22: goto L77;
            case 23: goto L75;
            case 24: goto L73;
            case 25: goto L71;
            case 26: goto L69;
            case 27: goto L67;
            case 28: goto L65;
            case 29: goto L63;
            case 30: goto L61;
            case 31: goto L59;
            case 32: goto L57;
            case 33: goto L55;
            case 34: goto L53;
            case 35: goto L51;
            case 36: goto L49;
            case 37: goto L47;
            case 38: goto L45;
            case 39: goto L43;
            case 40: goto L41;
            case 41: goto L39;
            case 42: goto L37;
            case 43: goto L35;
            case 44: goto L33;
            case 45: goto L31;
            case 46: goto L29;
            case 47: goto L27;
            case 48: goto L25;
            case 49: goto L23;
            case 50: goto L21;
            case 51: goto L19;
            case 52: goto L17;
            case 53: goto L15;
            case 54: goto L4;
            case 55: goto L13;
            case 56: goto L11;
            case 57: goto L9;
            case 58: goto L7;
            case 59: goto L5;
            default: goto L4;
        };
    L5:
        return "invoke-custom";
    L7:
        return "invoke-polymorphic";
    L9:
        return "fill-array-data";
    L11:
        return "move-result-pseudo";
    L13:
        return "move-result";
    L15:
        return "invoke-interface";
    L17:
        return "invoke-direct";
    L19:
        return "invoke-super";
    L21:
        return "invoke-virtual";
    L23:
        return "invoke-static";
    L25:
        return "put-static";
    L27:
        return "put-field";
    L29:
        return "get-static";
    L31:
        return "get-field";
    L33:
        return "instance-of";
    L35:
        return "check-cast";
    L37:
        return "filled-new-array";
    L39:
        return "new-array";
    L41:
        return "new-instance";
    L43:
        return "aput";
    L45:
        return "aget";
    L47:
        return "monitor-exit";
    L49:
        return "monitor-enter";
    L51:
        return "throw";
    L53:
        return "array-length";
    L55:
        return "return";
    L57:
        return "to-short";
    L59:
        return "to-char";
    L61:
        return "to-byte";
    L63:
        return "conv";
    L65:
        return "cmpg";
    L67:
        return "cmpl";
    L69:
        return "not";
    L71:
        return "ushr";
    L73:
        return "shr";
    L75:
        return "shl";
    L77:
        return "xor";
    L79:
        return PluginsConstants.HookFilterTypes.OR;
    L81:
        return "and";
    L83:
        return "neg";
    L85:
        return "rem";
    L87:
        return "div";
    L89:
        return "mul";
    L91:
        return "sub";
    L93:
        return "add";
    L95:
        return PluginsConstants.Settings.TYPE_SWITCH;
    L97:
        return "if-gt";
    L99:
        return "if-le";
    L101:
        return "if-ge";
    L103:
        return "if-lt";
    L105:
        return "if-ne";
    L107:
        return "if-eq";
    L109:
        return "goto";
    L111:
        return "const";
    L113:
        return "move-exception";
    L115:
        return "move-param";
    L117:
        return "move";
    L119:
        return "nop";
    L4:
        return "unknown-" + Hex.u1(r2);
    }
}
