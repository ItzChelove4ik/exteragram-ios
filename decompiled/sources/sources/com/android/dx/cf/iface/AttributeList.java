package com.android.dx.cf.iface;

/* loaded from: classes4.dex */
public interface AttributeList {
    int byteLength();

    Attribute findFirst(String r1);

    Attribute findNext(Attribute r1);

    Attribute get(int r1);

    boolean isMutable();

    int size();
}
