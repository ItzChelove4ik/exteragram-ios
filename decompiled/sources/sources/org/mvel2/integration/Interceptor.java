package org.mvel2.integration;

import org.mvel2.ast.ASTNode;

/* loaded from: classes5.dex */
public interface Interceptor {
    public static final int END = 2;
    public static final int NORMAL_FLOW = 0;
    public static final int SKIP = 1;

    int doAfter(Object r1, ASTNode r2, VariableResolverFactory r3);

    int doBefore(ASTNode r1, VariableResolverFactory r2);
}
