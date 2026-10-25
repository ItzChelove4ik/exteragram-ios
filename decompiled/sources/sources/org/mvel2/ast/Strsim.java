package org.mvel2.ast;

import org.mvel2.CompileException;
import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.CompilerTools;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class Strsim extends ASTNode {
    private ASTNode soundslike;
    private ASTNode stmt;

    public Strsim(ASTNode r1, ASTNode r2, ParserContext r3) {
        super(r3);
        this.stmt = r1;
        this.soundslike = r2;
        CompilerTools.expectType(r3, r2, String.class, true);
    }

    @Override // org.mvel2.ast.ASTNode
    public Class getEgressType() {
        return Boolean.class;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r4, Object r5, VariableResolverFactory r6) {
        String r1 = String.valueOf(this.soundslike.getReducedValue(r4, r5, r6));     // Catch: ClassCastException -> L9
        String r42 = (String) this.stmt.getReducedValue(r4, r5, r6);     // Catch: ClassCastException -> L9
        if (r42 == null) goto L8;
        return Float.valueOf(ParseTools.similarity(r1, r42));
    L8:
        throw new CompileException("not a string: " + this.stmt.getName(), this.stmt.getExpr(), getStart());     // Catch: ClassCastException -> L9
    L9:
        Sign$$ExternalSyntheticBUOutline0.m("not a string: " + this.soundslike.getName(), this.soundslike.getExpr(), this.soundslike.getStart());
        return null;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r2, Object r3, VariableResolverFactory r4) {
        return Float.valueOf(ParseTools.similarity(String.valueOf(this.soundslike.getReducedValueAccelerated(r2, r3, r4)), (String) this.stmt.getReducedValueAccelerated(r2, r3, r4)));
    }
}
