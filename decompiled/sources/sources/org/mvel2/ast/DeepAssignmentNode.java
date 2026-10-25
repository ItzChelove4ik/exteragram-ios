package org.mvel2.ast;

import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.PropertyAccessor;
import org.mvel2.asm.signature.SignatureVisitor;
import org.mvel2.compiler.CompiledAccExpression;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class DeepAssignmentNode extends ASTNode implements Assignment {
    protected CompiledAccExpression acc;
    protected String property;
    protected ExecutableStatement statement;

    public DeepAssignmentNode(char[] r3, int r4, int r5, int r6, int r7, String r8, ParserContext r9) {
        super(r9);
        this.fields |= r6 | 2;
        this.expr = r3;
        this.start = r4;
        this.offset = r5;
        if (r7 == (-1)) goto L5;
        this.property = r8;
        ExecutableStatement r32 = (ExecutableStatement) ParseTools.subCompileExpression(ParseTools.createShortFormOperativeAssignment(r8, r3, r4, r5, r7), r9);
        this.statement = r32;
        this.egressType = r32.getKnownEgressType();
    L16:
        if ((r6 & 16) == 0) goto L19;
        this.acc = (CompiledAccExpression) MVEL.compileSetExpression(this.property.toCharArray(), r4, r5, r9);
        return;
    L19:
        return;
    L5:
        int r72 = ParseTools.find(r3, r4, r5, SignatureVisitor.INSTANCEOF);
        if (r72 == (-1)) goto L14;
        this.property = ParseTools.createStringTrimmed(r3, r4, r72 - r4);
        int r73 = r72 + 1;
        int r82 = ParseTools.skipWhitespace(r3, r73);
        this.start = r82;
        if (r82 >= (r4 + r5)) goto L12;
        int r74 = r5 - (r82 - r4);
        this.offset = r74;
        if ((r6 & 16) == 0) goto L16;
        this.statement = (ExecutableStatement) ParseTools.subCompileExpression(r3, r82, r74, r9);
        goto L16
    L12:
        Sign$$ExternalSyntheticBUOutline0.m("unexpected end of statement", r3, r73);
        throw null;
    L14:
        this.property = new String(r3);
        goto L16
    }

    public DeepAssignmentNode(char[] r9, int r10, int r11, int r12, ParserContext r13) {
        this(r9, r10, r11, r12, -1, null, r13);
    }

    @Override // org.mvel2.ast.ASTNode
    public String getAbsoluteName() {
        String r2 = this.property;
        return r2.substring(0, r2.indexOf(46));
    }

    @Override // org.mvel2.ast.Assignment
    public String getAssignmentVar() {
        return this.property;
    }

    @Override // org.mvel2.ast.Assignment
    public char[] getExpression() {
        return ParseTools.subArray(this.expr, this.start, this.offset);
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r4, Object r5, VariableResolverFactory r6) {
        String r52 = this.property;
        Object r0 = MVEL.eval(this.expr, this.start, this.offset, r4, r6);
        PropertyAccessor.set(r4, r6, r52, r0, this.pCtx);
        return r0;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r5, Object r6, VariableResolverFactory r7) {
        if (this.statement != null) goto L5;
        this.statement = (ExecutableStatement) ParseTools.subCompileExpression(this.expr, this.start, this.offset, this.pCtx);
        this.acc = (CompiledAccExpression) MVEL.compileSetExpression(this.property.toCharArray(), this.statement.getKnownEgressType(), this.pCtx);
    L5:
        CompiledAccExpression r0 = this.acc;
        Object r4 = this.statement.getValue(r5, r6, r7);
        r0.setValue(r5, r6, r7, r4);
        return r4;
    }

    @Override // org.mvel2.ast.ASTNode
    public boolean isAssignment() {
        return true;
    }

    @Override // org.mvel2.ast.Assignment
    public boolean isNewDeclaration() {
        return false;
    }

    @Override // org.mvel2.ast.Assignment
    public void setValueStatement(ExecutableStatement r1) {
        this.statement = r1;
    }
}
