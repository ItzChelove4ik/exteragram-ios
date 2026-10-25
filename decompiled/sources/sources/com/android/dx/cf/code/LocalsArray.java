package com.android.dx.cf.code;

import com.android.dex.util.ExceptionWithContext;
import com.android.dx.rop.code.RegisterSpec;
import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeBearer;
import com.android.dx.util.MutabilityControl;
import com.android.dx.util.ToHuman;

/* loaded from: classes4.dex */
public abstract class LocalsArray extends MutabilityControl implements ToHuman {
    public LocalsArray(boolean r1) {
        super(r1);
    }

    public abstract void annotate(ExceptionWithContext r1);

    public abstract LocalsArray copy();

    public abstract TypeBearer get(int r1);

    public abstract TypeBearer getCategory1(int r1);

    public abstract TypeBearer getCategory2(int r1);

    public abstract int getMaxLocals();

    public abstract TypeBearer getOrNull(int r1);

    public abstract OneLocalsArray getPrimary();

    public abstract void invalidate(int r1);

    public abstract void makeInitialized(Type r1);

    public abstract LocalsArray merge(LocalsArray r1);

    public abstract LocalsArraySet mergeWithSubroutineCaller(LocalsArray r1, int r2);

    public abstract void set(int r1, TypeBearer r2);

    public abstract void set(RegisterSpec r1);
}
