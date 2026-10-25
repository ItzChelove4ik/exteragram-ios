package org.mvel2.ast;

import org.mvel2.MVEL$$ExternalSyntheticBUOutline0;
import org.mvel2.ParserContext;
import org.mvel2.ast.Proto;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class DeclProtoVarNode extends ASTNode implements Assignment {
    private String name;

    public DeclProtoVarNode(String r1, Proto r2, int r3, ParserContext r4) {
        super(r4);
        this.egressType = Proto.ProtoInstance.class;
        this.name = r1;
        ParseTools.checkNameSafety(r1);
        if ((r3 & 16) == 0) goto L6;
        r4.addVariable(r1, this.egressType, true);
        return;
    }

    @Override // org.mvel2.ast.Assignment
    public String getAssignmentVar() {
        return this.name;
    }

    @Override // org.mvel2.ast.Assignment
    public char[] getExpression() {
        return new char[0];
    }

    @Override // org.mvel2.ast.ASTNode
    public String getName() {
        return this.name;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r2, Object r3, VariableResolverFactory r4) {
        boolean r22 = r4.isResolveable(this.name);
        String r32 = this.name;
        if (r22 == true) goto L6;
        r4.createVariable(r32, null, this.egressType);
        return null;
    L6:
        MVEL$$ExternalSyntheticBUOutline0.m("variable defined within scope: ", r32);
        return null;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r2, Object r3, VariableResolverFactory r4) {
        boolean r22 = r4.isResolveable(this.name);
        String r32 = this.name;
        if (r22 == true) goto L6;
        r4.createVariable(r32, null, this.egressType);
        return null;
    L6:
        MVEL$$ExternalSyntheticBUOutline0.m("variable defined within scope: ", r32);
        return null;
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

    @Override // org.mvel2.ast.ASTNode
    public String toString() {
        return "var:" + this.name;
    }
}
