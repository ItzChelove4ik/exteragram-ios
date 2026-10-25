package com.google.protobuf;

import java.util.List;

/* loaded from: classes5.dex */
public interface LazyStringList extends List {
    void add(ByteString r1);

    Object getRaw(int r1);

    List<?> getUnderlyingElements();

    LazyStringList getUnmodifiableView();
}
