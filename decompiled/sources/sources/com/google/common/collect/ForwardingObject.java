package com.google.common.collect;

/* loaded from: classes5.dex */
public abstract class ForwardingObject {
    public ForwardingObject() {
    }

    public abstract Object delegate();

    public String toString() {
        return delegate().toString();
    }
}
