package com.android.dx.cf.attrib;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.cf.code.LocalVariableList;
import com.android.dx.util.MutabilityException;

/* loaded from: classes4.dex */
public abstract class BaseLocalVariables extends BaseAttribute {
    private final LocalVariableList localVariables;

    public BaseLocalVariables(String r1, LocalVariableList r2) {
        super(r1);
    L9:
        f$$ExternalSyntheticBUOutline2.m("localVariables == null");
        throw null;
    L4:
        if (r2.isMutable() == true) goto L8;
        this.localVariables = r2;
        return;
    L8:
        throw new MutabilityException("localVariables.isMutable()");     // Catch: NullPointerException -> L9
    }

    @Override // com.android.dx.cf.iface.Attribute
    public final int byteLength() {
        return (this.localVariables.size() * 10) + 8;
    }

    public final LocalVariableList getLocalVariables() {
        return this.localVariables;
    }
}
