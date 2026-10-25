package org.mvel2.ast;

import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.asm.signature.SignatureVisitor;
import org.mvel2.compiler.CompiledAccExpression;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.ArrayTools;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class IndexedAssignmentNode extends ASTNode implements Assignment {
    private transient CompiledAccExpression accExpr;
    private String assignmentVar;
    private boolean col;
    private char[] index;
    private char[] indexTarget;
    private String name;
    private int register;
    private ExecutableStatement statement;
    private char[] stmt;

    public IndexedAssignmentNode(char[] r4, int r5, int r6, int r7, int r8, String r9, int r10, ParserContext r11) {
        super(r11);
        this.col = false;
        this.expr = r4;
        this.start = r5;
        this.offset = r6;
        this.register = r10;
        if (r8 == (-1)) goto L5;
        this.name = r9;
        ParseTools.checkNameSafety(r9);
        char[] r42 = ParseTools.createShortFormOperativeAssignment(r9, r4, r5, r6, r8);
        this.stmt = r42;
        ExecutableStatement r43 = (ExecutableStatement) ParseTools.subCompileExpression(r42, r11);
        this.statement = r43;
        this.egressType = r43.getKnownEgressType();
    L24:
        if ((r7 & 16) == 0) goto L27;
        r11.addVariable(r9, this.egressType);
        return;
    L27:
        return;
    L5:
        int r82 = ParseTools.find(r4, r5, r6, SignatureVisitor.INSTANCEOF);
        if (r82 == (-1)) goto L22;
        this.name = ParseTools.createStringTrimmed(r4, r5, r82 - r5);
        this.assignmentVar = r9;
        boolean r102 = true;
        int r83 = r82 + 1;
        int r1 = ParseTools.skipWhitespace(r4, r83);
        this.start = r1;
        if (r1 >= (r5 + r6)) goto L20;
        int r62 = r6 - (r1 - r5);
        this.offset = r62;
        this.stmt = ParseTools.subset(r4, r1, r62);
        ExecutableStatement r44 = (ExecutableStatement) ParseTools.subCompileExpression(r4, this.start, this.offset, r11);
        this.statement = r44;
        this.egressType = r44.getKnownEgressType();
        int r45 = this.name.length();
        char[] r52 = this.name.toCharArray();
        this.indexTarget = r52;
        short r46 = (short) ArrayTools.findFirst('[', 0, r45, r52);
        this.endOfName = r46;
        if (r46 > 0) goto L13;
        r102 = false;
    L13:
        this.col = r102;
        if (r102 == false) goto L19;
        int r47 = this.fields | 256;
        this.fields = r47;
        if ((r47 & 16) == 0) goto L18;
        this.accExpr = (CompiledAccExpression) MVEL.compileSetExpression(this.indexTarget, r11);
    L18:
        this.name = this.name.substring(0, this.endOfName);
        char[] r48 = this.indexTarget;
        int r53 = this.endOfName;
        this.index = ParseTools.subset(r48, r53, r48.length - r53);
    L19:
        ParseTools.checkNameSafety(this.name);
        goto L24
    L20:
        Sign$$ExternalSyntheticBUOutline0.m("unexpected end of statement", r4, r83);
        throw null;
    L22:
        String r54 = new String(r4);
        this.name = r54;
        ParseTools.checkNameSafety(r54);
        this.assignmentVar = r9;
        goto L24
    }

    public IndexedAssignmentNode(char[] r10, int r11, int r12, int r13, int r14, ParserContext r15) {
        this(r10, r11, r12, r13, -1, null, r14, r15);
    }

    @Override // org.mvel2.ast.ASTNode
    public String getAbsoluteName() {
        return this.name;
    }

    @Override // org.mvel2.ast.Assignment
    public String getAssignmentVar() {
        return this.assignmentVar;
    }

    @Override // org.mvel2.ast.Assignment
    public char[] getExpression() {
        return this.stmt;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r3, Object r4, VariableResolverFactory r5) {
        ParseTools.checkNameSafety(this.name);
        boolean r42 = this.col;
        int r0 = this.register;
        if (r42 == false) goto L6;
        Object r43 = r5.getIndexedVariableResolver(r0).getValue();
        String r02 = new String(this.index);
        Object r2 = MVEL.eval(this.stmt, r3, r5);
        MVEL.setProperty(r43, r02, r2);
        return r2;
    L6:
        String r44 = this.name;
        Object r22 = MVEL.eval(this.stmt, r3, r5);
        r5.createIndexedVariable(r0, r44, r22);
        return r22;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r3, Object r4, VariableResolverFactory r5) {
        if (this.accExpr != null) goto L8;
        char[] r0 = this.indexTarget;
        if (r0 == null) goto L8;
        this.accExpr = (CompiledAccExpression) MVEL.compileSetExpression(r0);
    L8:
        if (this.col == false) goto L12;
        CompiledAccExpression r02 = this.accExpr;
        Object r2 = this.statement.getValue(r3, r4, r5);
        r02.setValue(r3, r4, r5, r2);
        return r2;
    L12:
        if (this.statement == null) goto L20;
        if (r5.isIndexedFactory() == false) goto L17;
        int r03 = this.register;
        String r1 = this.name;
        Object r22 = this.statement.getValue(r3, r4, r5);
        r5.createIndexedVariable(r03, r1, r22);
        return r22;
    L17:
        String r04 = this.name;
        Object r23 = this.statement.getValue(r3, r4, r5);
        r5.createVariable(r04, r23);
        return r23;
    L20:
        if (r5.isIndexedFactory() == false) goto L22;
        r5.createIndexedVariable(this.register, this.name, null);
        return Void.class;
    L22:
        r5.createVariable(this.name, this.statement.getValue(r3, r4, r5));
        return Void.class;
    }

    public int getRegister() {
        return this.register;
    }

    public String getVarName() {
        return this.name;
    }

    @Override // org.mvel2.ast.ASTNode
    public boolean isAssignment() {
        return true;
    }

    @Override // org.mvel2.ast.Assignment
    public boolean isNewDeclaration() {
        return false;
    }

    public void setRegister(int r1) {
        this.register = r1;
    }

    @Override // org.mvel2.ast.Assignment
    public void setValueStatement(ExecutableStatement r1) {
        this.statement = r1;
    }
}
