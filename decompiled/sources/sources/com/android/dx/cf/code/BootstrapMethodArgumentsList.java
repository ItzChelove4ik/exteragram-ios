package com.android.dx.cf.code;

import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstDouble;
import com.android.dx.rop.cst.CstFloat;
import com.android.dx.rop.cst.CstInteger;
import com.android.dx.rop.cst.CstLong;
import com.android.dx.rop.cst.CstMethodHandle;
import com.android.dx.rop.cst.CstProtoRef;
import com.android.dx.rop.cst.CstString;
import com.android.dx.rop.cst.CstType;
import com.android.dx.util.FixedSizeList;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;

/* loaded from: classes4.dex */
public class BootstrapMethodArgumentsList extends FixedSizeList {
    public BootstrapMethodArgumentsList(int r1) {
        super(r1);
    }

    public Constant get(int r1) {
        return (Constant) get0(r1);
    }

    public void set(int r2, Constant r3) {
        if ((r3 instanceof CstString) == false) goto L5;
    L21:
        set0(r2, r3);
        return;
    L5:
        if ((r3 instanceof CstType) == true) goto L21;
        if ((r3 instanceof CstInteger) == true) goto L21;
        if ((r3 instanceof CstLong) == true) goto L21;
        if ((r3 instanceof CstFloat) == true) goto L21;
        if ((r3 instanceof CstDouble) == true) goto L21;
        if ((r3 instanceof CstMethodHandle) == true) goto L21;
        if ((r3 instanceof CstProtoRef) == true) goto L21;
        Native$$ExternalSyntheticBUOutline5.m("bad type for bootstrap argument: ", r3.getClass());
    }
}
