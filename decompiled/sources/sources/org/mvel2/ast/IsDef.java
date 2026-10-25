package org.mvel2.ast;

import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.PropertyTools;

/* loaded from: classes5.dex */
public class IsDef extends ASTNode {
    public IsDef(char[] r1, int r2, int r3, ParserContext r4) {
        super(r4);
        this.expr = r1;
        this.start = r2;
        this.offset = r3;
        this.nameCache = new String(r1, r2, r3);
    }

    @Override // org.mvel2.ast.ASTNode
    public Class getEgressType() {
        return Boolean.class;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r1, Object r2, VariableResolverFactory r3) {
        if (r3.isResolveable(this.nameCache) == true) goto L9;
        if (r2 != null) goto L6;
    L8:
        boolean r0 = false;
    L11:
        return Boolean.valueOf(r0);
    L6:
        if (PropertyTools.getFieldOrAccessor(r2.getClass(), this.nameCache) == null) goto L8;
    L9:
        r0 = true;
        goto L11
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r1, Object r2, VariableResolverFactory r3) {
        if (r3.isResolveable(this.nameCache) == true) goto L9;
        if (r2 != null) goto L6;
    L8:
        boolean r0 = false;
    L11:
        return Boolean.valueOf(r0);
    L6:
        if (PropertyTools.getFieldOrAccessor(r2.getClass(), this.nameCache) == null) goto L8;
    L9:
        r0 = true;
        goto L11
    }
}
