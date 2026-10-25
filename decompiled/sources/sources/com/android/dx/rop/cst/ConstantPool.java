package com.android.dx.rop.cst;

/* loaded from: classes4.dex */
public interface ConstantPool {
    Constant get(int r1);

    Constant get0Ok(int r1);

    Constant[] getEntries();

    Constant getOrNull(int r1);

    int size();
}
