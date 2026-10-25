package com.android.dx.dex.file;

import com.android.dx.rop.cst.CstString;
import com.android.dx.util.AnnotatedOutput;
import com.android.dx.util.ToHuman;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public abstract class EncodedMember implements ToHuman {
    private final int accessFlags;

    public EncodedMember(int r1) {
        this.accessFlags = r1;
    }

    public abstract void addContents(DexFile r1);

    public abstract void debugPrint(PrintWriter r1, boolean r2);

    public abstract int encode(DexFile r1, AnnotatedOutput r2, int r3, int r4);

    public final int getAccessFlags() {
        return this.accessFlags;
    }

    public abstract CstString getName();
}
