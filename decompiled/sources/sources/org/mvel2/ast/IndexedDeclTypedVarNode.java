package org.mvel2.ast;

import org.mvel2.ParserContext;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class IndexedDeclTypedVarNode extends ASTNode implements Assignment {
    private int register;

    public IndexedDeclTypedVarNode(int r1, int r2, int r3, Class r4, ParserContext r5) {
        super(r5);
        this.egressType = r4;
        this.start = r2;
        this.offset = r3;
        this.register = r1;
    }

    @Override // org.mvel2.ast.Assignment
    public String getAssignmentVar() {
        return null;
    }

    @Override // org.mvel2.ast.Assignment
    public char[] getExpression() {
        return new char[0];
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r1, Object r2, VariableResolverFactory r3) {
        r3.createIndexedVariable(this.register, null, this.egressType);
        return null;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r2, Object r3, VariableResolverFactory r4) {
        r4.createIndexedVariable(this.register, null, this.egressType);
        return r2;
    }

    @Override // org.mvel2.ast.ASTNode
    public boolean isAssignment() {
        return true;
    }

    @Override // org.mvel2.ast.Assignment
    public boolean isNewDeclaration() {
        return true;
    }

    @Override // org.mvel2.ast.Assignment
    public void setValueStatement(ExecutableStatement r1) {
        throw new RuntimeException("illegal operation");
    }
}
