package org.mvel2.optimizers;

import org.mvel2.ParserContext;
import org.mvel2.compiler.Accessor;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public interface AccessorOptimizer {
    Class getEgressType();

    Object getResultOptPass();

    void init();

    boolean isLiteralOnly();

    Accessor optimizeAccessor(ParserContext r1, char[] r2, int r3, int r4, Object r5, Object r6, VariableResolverFactory r7, boolean r8, Class r9);

    default Accessor optimizeAccessor(ParserContext r1, char[] r2, int r3, int r4, Object r5, Object r6, VariableResolverFactory r7, boolean r8, Class r9, boolean r10) {
        throw new UnsupportedOperationException();
    }

    Accessor optimizeCollection(ParserContext r1, Object r2, Class r3, char[] r4, int r5, int r6, Object r7, Object r8, VariableResolverFactory r9);

    Accessor optimizeObjectCreation(ParserContext r1, char[] r2, int r3, int r4, Object r5, Object r6, VariableResolverFactory r7);

    Accessor optimizeSetAccessor(ParserContext r1, char[] r2, int r3, int r4, Object r5, Object r6, VariableResolverFactory r7, boolean r8, Object r9, Class r10);
}
