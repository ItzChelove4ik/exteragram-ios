package org.mvel2.ast;

import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.asm.signature.SignatureVisitor;
import org.mvel2.ast.Proto;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class ProtoVarNode extends ASTNode implements Assignment {
    private String name;
    private ExecutableStatement statement;

    public ProtoVarNode(char[] r3, int r4, int r5, int r6, Proto r7, ParserContext r8) {
        super(r8);
        this.egressType = Proto.ProtoInstance.class;
        this.start = r4;
        this.offset = r5;
        this.fields = r6;
        this.expr = r3;
        int r72 = ParseTools.find(r3, r4, r5, SignatureVisitor.INSTANCEOF);
        if (r72 == (-1)) goto L7;
        String r42 = ParseTools.createStringTrimmed(r3, 0, r72);
        this.name = r42;
        ParseTools.checkNameSafety(r42);
        r6 = r6 | 128;
        if ((r6 & 16) == 0) goto L9;
        this.statement = (ExecutableStatement) ParseTools.subCompileExpression(r3, r72 + 1, r5, r8);
    L9:
        if ((r6 & 16) == 0) goto L12;
        r8.addVariable(this.name, this.egressType, true);
        return;
    L12:
        return;
    L7:
        String r73 = new String(r3, r4, r5);
        this.name = r73;
        ParseTools.checkNameSafety(r73);
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
