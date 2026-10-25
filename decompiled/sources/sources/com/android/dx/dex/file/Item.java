package com.android.dx.dex.file;

import com.android.dx.util.AnnotatedOutput;

/* loaded from: classes.dex */
public abstract class Item {
    public Item() {
    }

    public abstract void addContents(DexFile r1);

    public abstract ItemType itemType();

    public final String typeName() {
        return itemType().toHuman();
    }

    public abstract int writeSize();

    public abstract void writeTo(DexFile r1, AnnotatedOutput r2);
}
