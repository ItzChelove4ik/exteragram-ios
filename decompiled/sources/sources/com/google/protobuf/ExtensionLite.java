package com.google.protobuf;

import com.google.protobuf.MessageLite;

/* loaded from: classes5.dex */
public abstract class ExtensionLite<ContainingType extends MessageLite, Type> {
    public ExtensionLite() {
    }

    public boolean isLite() {
        return true;
    }
}
