package org.mvel2.ast;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.compiler.ExecutableLiteral;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class RegExMatch extends ASTNode {
    private Pattern p;
    private int patternOffset;
    private int patternStart;
    private ExecutableStatement patternStmt;
    private ExecutableStatement stmt;

    public RegExMatch(char[] r1, int r2, int r3, int r4, int r5, int r6, ParserContext r7) {
        super(r7);
        this.expr = r1;
        this.start = r2;
        this.offset = r3;
        this.patternStart = r5;
        this.patternOffset = r6;
        if ((r4 & 16) == 0) goto L12;
        this.stmt = (ExecutableStatement) ParseTools.subCompileExpression(r1, r2, r3, r7);
        ExecutableStatement r22 = (ExecutableStatement) ParseTools.subCompileExpression(r1, r5, r6, r7);
        this.patternStmt = r22;
        if ((r22 instanceof ExecutableLiteral) == true) goto L13;
        return;
    L13:
        this.p = Pattern.compile(String.valueOf(r22.getValue(null, null)));     // Catch: PatternSyntaxException -> L9
        return;
    L9:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m("bad regular expression", r1, r5, e);
        throw null;
    }

    @Override // org.mvel2.ast.ASTNode
    public Class getEgressType() {
        return Boolean.class;
    }

    public Pattern getPattern() {
        return this.p;
    }

    public ExecutableStatement getPatternStatement() {
        return this.patternStmt;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r4, Object r5, VariableResolverFactory r6) {
        return Boolean.valueOf(Pattern.compile(String.valueOf(MVEL.eval(this.expr, this.patternStart, this.patternOffset, r4, r6))).matcher(String.valueOf(MVEL.eval(this.expr, this.start, this.offset, r4, r6))).matches());
    L4:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m("bad regular expression", this.expr, this.patternStart, e);
        return null;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r2, Object r3, VariableResolverFactory r4) {
        Pattern r0 = this.p;
        if (r0 != null) goto L7;
        return Boolean.valueOf(Pattern.compile(String.valueOf(this.patternStmt.getValue(r2, r3, r4))).matcher(String.valueOf(this.stmt.getValue(r2, r3, r4))).matches());
    L7:
        return Boolean.valueOf(r0.matcher(String.valueOf(this.stmt.getValue(r2, r3, r4))).matches());
    }

    public ExecutableStatement getStatement() {
        return this.stmt;
    }
}
