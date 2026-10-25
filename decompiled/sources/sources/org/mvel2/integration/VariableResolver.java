package org.mvel2.integration;

import java.io.Serializable;

/* loaded from: classes5.dex */
public interface VariableResolver extends Serializable {
    int getFlags();

    String getName();

    Class getType();

    Object getValue();

    void setStaticType(Class r1);

    void setValue(Object r1);
}
