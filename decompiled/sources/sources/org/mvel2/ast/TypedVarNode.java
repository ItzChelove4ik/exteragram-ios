package org.mvel2.ast;

import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.asm.signature.SignatureVisitor;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class TypedVarNode extends ASTNode implements Assignment {
    private String name;
    private ExecutableStatement statement;

    public TypedVarNode(char[] r2, int r3, int r4, int r5, Class r6, ParserContext r7) {
        super(r7);
        this.egressType = r6;
        this.fields = r5;
        this.start = r3;
        this.offset = r4;
        this.expr = r2;
        int r62 = ParseTools.find(r2, r3, r4, SignatureVisitor.INSTANCEOF);
        if (r62 == (-1)) goto L7;
        int r42 = r62 - r3;
        String r32 = ParseTools.createStringTrimmed(r2, r3, r42);
        this.name = r32;
        ParseTools.checkNameSafety(r32);
        int r33 = this.offset - r42;
        this.offset = r33;
        int r63 = r62 + 1;
        this.start = r63;
        r5 = r5 | 128;
        if ((r5 & 16) == 0) goto L9;
        this.statement = (ExecutableStatement) ParseTools.subCompileExpression(r2, r63, r33, r7);
    L9:
        if ((r5 & 16) == 0) goto L21;
        Class r22 = r7.getVarOrInputType(this.name);
        if (r22 != null) goto L13;
    L19:
        r7.addVariable(this.name, this.egressType, false);
        return;
    L13:
        if (r22 == Object.class) goto L19;
        if (r22.isAssignableFrom(this.egressType) == true) goto L19;
        Instance$$ExternalSyntheticBUOutline0.m("statically-typed variable already defined in scope: ", this.name);
        throw null;
    L21:
        return;
    L7:
        String r64 = new String(r2, r3, r4);
        this.name = r64;
        ParseTools.checkNameSafety(r64);
        goto L9
    }

    @Override // org.mvel2.ast.Assignment
    public String getAssignmentVar() {
        return this.name;
    }

    @Override // org.mvel2.ast.Assignment
    public char[] getExpression() {
        return this.expr;
    }

    @Override // org.mvel2.ast.ASTNode
    public String getName() {
        return this.name;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r4, Object r5, VariableResolverFactory r6) {
        String r42 = this.name;
        Object r52 = MVEL.eval(this.expr, this.start, this.offset, r5, r6);
        r6.createVariable(r42, r52, this.egressType);
        return r52;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r5, Object r6, VariableResolverFactory r7) {
        if (this.statement != null) goto L5;
        this.statement = (ExecutableStatement) ParseTools.subCompileExpression(this.expr, this.start, this.offset, this.pCtx);
    L5:
        String r0 = this.name;
        Object r52 = this.statement.getValue(r5, r6, r7);
        r7.createVariable(r0, r52, this.egressType);
        return r52;
    }

    @Override // org.mvel2.ast.Assignment
    public boolean isNewDeclaration() {
        return true;
    }

    @Override // org.mvel2.ast.Assignment
    public void setValueStatement(ExecutableStatement r1) {
        this.statement = r1;
    }
}
