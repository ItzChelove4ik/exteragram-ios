package org.mvel2.ast;

import org.mvel2.MVEL;
import org.mvel2.MVELInterpretedRuntime;
import org.mvel2.ParserContext;
import org.mvel2.PropertyAccessor;
import org.mvel2.asm.signature.SignatureVisitor;
import org.mvel2.compiler.CompiledAccExpression;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.ArrayTools;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class AssignmentNode extends ASTNode implements Assignment {
    private transient CompiledAccExpression accExpr;
    private String assignmentVar;
    private boolean col;
    private String index;
    private char[] indexTarget;
    private ExecutableStatement statement;
    private String varName;

    public AssignmentNode(char[] r6, int r7, int r8, int r9, ParserContext r10) {
        super(r10);
        boolean r0 = false;
        this.col = false;
        this.expr = r6;
        this.start = r7;
        this.offset = r8;
        int r1 = ParseTools.find(r6, r7, r8, SignatureVisitor.INSTANCEOF);
        if (r1 == (-1)) goto L35;
        String r2 = ParseTools.createStringTrimmed(r6, r7, r1 - r7);
        this.varName = r2;
        this.assignmentVar = r2;
        int r12 = r1 + 1;
        int r3 = ParseTools.skipWhitespace(r6, r12);
        this.start = r3;
        if (r3 >= (r7 + r8)) goto L23;
        int r82 = r8 - (r3 - r7);
        this.offset = r82;
        if ((r9 & 16) == 0) goto L9;
        ExecutableStatement r83 = (ExecutableStatement) ParseTools.subCompileExpression(r6, r3, r82, r10);
        this.statement = r83;
        this.egressType = r83.getKnownEgressType();
    L9:
        int r84 = this.varName.length();
        char[] r13 = this.varName.toCharArray();
        this.indexTarget = r13;
        int r85 = ArrayTools.findFirst('[', 0, r84, r13);
        this.endOfName = r85;
        if (r85 <= 0) goto L12;
        r0 = true;
    L12:
        this.col = r0;
        if (r0 == false) goto L33;
        int r86 = this.fields | 256;
        this.fields = r86;
        if ((r86 & 16) == 0) goto L17;
        this.accExpr = (CompiledAccExpression) MVEL.compileSetExpression(this.indexTarget, r10);
    L17:
        this.varName = new String(r6, r7, this.endOfName);
        char[] r02 = this.indexTarget;
        int r14 = this.endOfName;
        this.index = new String(r02, r14, r02.length - r14);
    L33:
        ParseTools.checkNameSafety(this.varName);     // Catch: RuntimeException -> L20
    L27:
        if ((r9 & 16) == 0) goto L37;
        r10.addVariable(this.varName, this.egressType);
        return;
    L37:
        return;
    L20:
        e = move-exception;
        Sign$$ExternalSyntheticBUOutline0.m(e.getMessage(), r6, r7);
        throw null;
    L23:
        Sign$$ExternalSyntheticBUOutline0.m("unexpected end of statement", r6, r12);
        throw null;
    L35:
        String r03 = new String(r6, r7, r8);     // Catch: RuntimeException -> L30
        this.varName = r03;     // Catch: RuntimeException -> L30
        ParseTools.checkNameSafety(r03);     // Catch: RuntimeException -> L30
        this.assignmentVar = this.varName;     // Catch: RuntimeException -> L30
    L30:
        e = move-exception;
        Sign$$ExternalSyntheticBUOutline0.m(e.getMessage(), r6, r7);
        throw null;
    }

    @Override // org.mvel2.ast.Assignment
    public String getAssignmentVar() {
        return this.assignmentVar;
    }

    @Override // org.mvel2.ast.Assignment
    public char[] getExpression() {
        return ParseTools.subset(this.expr, this.start, this.offset);
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r8, Object r9, VariableResolverFactory r10) {
        ParseTools.checkNameSafety(this.varName);
        MVELInterpretedRuntime r0 = new MVELInterpretedRuntime(this.expr, this.start, this.offset, r8, r10, this.pCtx);
        boolean r82 = this.col;
        String r92 = this.varName;
        if (r82 == false) goto L7;
        Object r83 = r10.getVariableResolver(r92).getValue();
        String r93 = this.index;
        Object r102 = r0.parse();
        PropertyAccessor.set(r83, r10, r93, r102, this.pCtx);
        return r102;
    L7:
        return r10.createVariable(r92, r0.parse()).getValue();
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r4, Object r5, VariableResolverFactory r6) {
        if (this.accExpr != null) goto L8;
        char[] r0 = this.indexTarget;
        if (r0 == null) goto L8;
        this.accExpr = (CompiledAccExpression) MVEL.compileSetExpression(r0);
    L8:
        if (this.col == true) goto L10;
        ExecutableStatement r02 = this.statement;
        if (r02 == null) goto L18;
        if (r6 != null) goto L15;
        Sign$$ExternalSyntheticBUOutline0.m("cannot assign variables; no variable resolver factory available", this.expr, this.start);
        return null;
    L15:
        return r6.createVariable(this.varName, r02.getValue(r4, r5, r6)).getValue();
    L18:
        if (r6 == null) goto L21;
        r6.createVariable(this.varName, null);
        return null;
    L21:
        Sign$$ExternalSyntheticBUOutline0.m("cannot assign variables; no variable resolver factory available", this.expr, this.start);
        return null;
    L10:
        return this.accExpr.setValue(r4, r5, r6, this.statement.getValue(r4, r5, r6));
    }

    @Override // org.mvel2.ast.Assignment
    public boolean isNewDeclaration() {
        return false;
    }

    @Override // org.mvel2.ast.Assignment
    public void setValueStatement(ExecutableStatement r1) {
        this.statement = r1;
    }

    @Override // org.mvel2.ast.ASTNode
    public String toString() {
        return this.assignmentVar + " = " + new String(this.expr, this.start, this.offset);
    }
}
