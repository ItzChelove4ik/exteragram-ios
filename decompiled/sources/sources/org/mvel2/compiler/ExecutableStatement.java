package org.mvel2.compiler;

import java.io.Serializable;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public interface ExecutableStatement extends Accessor, Serializable, Cloneable {
    void computeTypeConversionRule();

    @Override // org.mvel2.compiler.Accessor
    Class getKnownEgressType();

    Class getKnownIngressType();

    Object getValue(Object r1, VariableResolverFactory r2);

    boolean intOptimized();

    boolean isConvertableIngressEgress();

    boolean isEmptyStatement();

    boolean isExplicitCast();

    boolean isLiteralOnly();

    void setKnownEgressType(Class r1);

    void setKnownIngressType(Class r1);
}
