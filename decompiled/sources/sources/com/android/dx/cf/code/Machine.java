package com.android.dx.cf.code;

import com.android.dx.rop.code.LocalItem;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.type.Prototype;
import com.android.dx.rop.type.Type;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public interface Machine {
    void auxCstArg(Constant r1);

    void auxInitValues(ArrayList<Constant> r1);

    void auxIntArg(int r1);

    void auxSwitchArg(SwitchList r1);

    void auxTargetArg(int r1);

    void auxType(Type r1);

    void clearArgs();

    Prototype getPrototype();

    void localArg(Frame r1, int r2);

    void localInfo(boolean r1);

    void localTarget(int r1, Type r2, LocalItem r3);

    void popArgs(Frame r1, int r2);

    void popArgs(Frame r1, Prototype r2);

    void popArgs(Frame r1, Type r2);

    void popArgs(Frame r1, Type r2, Type r3);

    void popArgs(Frame r1, Type r2, Type r3, Type r4);

    void run(Frame r1, int r2, int r3);
}
