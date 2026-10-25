package com.android.dx.ssa;

import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.code.RegisterSpecList;
import com.android.dx.rop.code.RegisterSpecSet;

/* loaded from: classes4.dex */
public abstract class RegisterMapper {
    public RegisterMapper() {
    }

    public abstract int getNewRegisterCount();

    public abstract RegisterSpec map(RegisterSpec r1);

    public final RegisterSpecList map(RegisterSpecList r5) {
        int r0 = r5.size();
        RegisterSpecList r1 = new RegisterSpecList(r0);
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        r1.set(r2, map(r5.get(r2)));
        r2 = r2 + 1;
        goto L3
    L5:
        r1.setImmutable();
        if (r1.equals(r5) == false) goto L8;
        return r5;
    L8:
        return r1;
    }

    public final RegisterSpecSet map(RegisterSpecSet r5) {
        int r0 = r5.getMaxSize();
        RegisterSpecSet r1 = new RegisterSpecSet(getNewRegisterCount());
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        RegisterSpec r3 = r5.get(r2);
        if (r3 == null) goto L7;
        r1.put(map(r3));
    L7:
        r2 = r2 + 1;
        goto L3
    L8:
        r1.setImmutable();
        if (r1.equals(r5) == false) goto L11;
        return r5;
    L11:
        return r1;
    }
}
