package org.mvel2.compiler;

import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public interface Accessor {
    Class getKnownEgressType();

    Object getValue(Object r1, Object r2, VariableResolverFactory r3);

    Object setValue(Object r1, Object r2, VariableResolverFactory r3, Object r4);
}
