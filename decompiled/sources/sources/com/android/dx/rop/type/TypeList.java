package com.android.dx.rop.type;

/* loaded from: classes.dex */
public interface TypeList {
    Type getType(int r1);

    int getWordCount();

    boolean isMutable();

    int size();

    TypeList withAddedType(Type r1);
}
