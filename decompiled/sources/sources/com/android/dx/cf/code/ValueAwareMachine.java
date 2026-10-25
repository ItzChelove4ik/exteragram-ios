package com.android.dx.cf.code;

import com.android.dx.rop.cst.CstCallSiteRef;
import com.android.dx.rop.cst.CstType;
import com.android.dx.rop.type.Prototype;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeBearer;
import com.android.dx.util.Hex;
import org.mvel2.ast.Instance$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public class ValueAwareMachine extends BaseMachine {
    public ValueAwareMachine(Prototype r1) {
        super(r1);
    }

    @Override // com.android.dx.cf.code.Machine
    public void run(Frame r3, int r4, int r5) {
        if (r5 != 0) goto L4;
    L44:
        clearResult();
    L45:
        storeResults(r3);
        return;
    L4:
        if (r5 != 20) goto L6;
    L43:
        setResult((TypeBearer) getAuxCst());
        goto L45
    L6:
        if (r5 != 21) goto L8;
    L42:
        setResult(arg(0));
        goto L45
    L8:
        if (r5 == 171) goto L44;
        if (r5 == 172) goto L44;
        switch(r5) {
            case 0: goto L44;
            case 18: goto L43;
            case 46: goto L41;
            case 54: goto L42;
            case 79: goto L44;
            case 100: goto L41;
            case 104: goto L41;
            case 108: goto L41;
            case 112: goto L41;
            case 116: goto L41;
            case 120: goto L41;
            case 122: goto L41;
            case 124: goto L41;
            case 126: goto L41;
            case 128: goto L41;
            case 130: goto L41;
            default: goto L12;
        };
    L12:
        switch(r5) {
            case 87: goto L44;
            case 88: goto L44;
            case 89: goto L38;
            case 90: goto L38;
            case 91: goto L38;
            case 92: goto L38;
            case 93: goto L38;
            case 94: goto L38;
            case 95: goto L38;
            case 96: goto L41;
            default: goto L13;
        };
    L13:
        switch(r5) {
            case 132: goto L41;
            case 133: goto L41;
            case 134: goto L41;
            case 135: goto L41;
            case 136: goto L41;
            case 137: goto L41;
            case 138: goto L41;
            case 139: goto L41;
            case 140: goto L41;
            case 141: goto L41;
            case 142: goto L41;
            case 143: goto L41;
            case 144: goto L41;
            case 145: goto L41;
            case 146: goto L41;
            case 147: goto L41;
            case 148: goto L41;
            case 149: goto L41;
            case 150: goto L41;
            case 151: goto L41;
            case 152: goto L41;
            case 153: goto L44;
            case 154: goto L44;
            case 155: goto L44;
            case 156: goto L44;
            case 157: goto L44;
            case 158: goto L44;
            case 159: goto L44;
            case 160: goto L44;
            case 161: goto L44;
            case 162: goto L44;
            case 163: goto L44;
            case 164: goto L44;
            case 165: goto L44;
            case 166: goto L44;
            case 167: goto L44;
            case 168: goto L37;
            case 169: goto L44;
            default: goto L14;
        };
    L14:
        switch(r5) {
            case 177: goto L44;
            case 178: goto L33;
            case 179: goto L44;
            case 180: goto L33;
            case 181: goto L44;
            case 182: goto L33;
            case 183: goto L26;
            case 184: goto L33;
            case 185: goto L33;
            case 186: goto L22;
            case 187: goto L21;
            case 188: goto L20;
            case 189: goto L19;
            case 190: goto L41;
            case 191: goto L44;
            case 192: goto L20;
            case 193: goto L18;
            case 194: goto L44;
            case 195: goto L44;
            default: goto L15;
        };
    L15:
        switch(r5) {
            case 197: goto L20;
            case 198: goto L44;
            case 199: goto L44;
            default: goto L16;
        };
    L16:
        Instance$$ExternalSyntheticBUOutline0.m("shouldn't happen: ", Hex.u1(r5));
        return;
    L18:
        setResult(Type.INT);
        goto L45
    L19:
        setResult(((CstType) getAuxCst()).getClassType().getArrayType());
    L20:
        setResult(((CstType) getAuxCst()).getClassType());
        goto L45
    L21:
        setResult(((CstType) getAuxCst()).getClassType().asUninitialized(r4));
        goto L45
    L22:
        Type r42 = ((CstCallSiteRef) getAuxCst()).getReturnType();
        if (r42 != Type.VOID) goto L25;
        clearResult();
        goto L45
    L25:
        setResult(r42);
        goto L45
    L26:
        Type r43 = arg(0).getType();
        if (r43.isUninitialized() == false) goto L29;
        r3.makeInitialized(r43);
    L29:
        Type r44 = ((TypeBearer) getAuxCst()).getType();
        if (r44 != Type.VOID) goto L32;
        clearResult();
        goto L45
    L32:
        setResult(r44);
        goto L45
    L33:
        Type r45 = ((TypeBearer) getAuxCst()).getType();
        if (r45 != Type.VOID) goto L36;
        clearResult();
        goto L45
    L36:
        setResult(r45);
        goto L45
    L37:
        setResult(new ReturnAddress(getAuxTarget()));
        goto L45
    L38:
        clearResult();
        int r46 = getAuxInt();
    L39:
        if (r46 == 0) goto L45;
        addResult(arg((r46 & 15) - 1));
        r46 = r46 >> 4;
    L41:
        setResult(getAuxType());
        goto L45
    }
}
