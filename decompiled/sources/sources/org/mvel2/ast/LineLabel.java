package org.mvel2.ast;

import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class LineLabel extends ASTNode {
    private int lineNumber;
    private String sourceFile;

    public LineLabel(String r1, int r2, ParserContext r3) {
        super(r3);
        this.lineNumber = r2;
        this.sourceFile = r1;
        this.fields = -1;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r1, Object r2, VariableResolverFactory r3) {
        return null;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r1, Object r2, VariableResolverFactory r3) {
        return null;
    }

    public String getSourceFile() {
        return this.sourceFile;
    }

    public void setLineNumber(int r1) {
        this.lineNumber = r1;
    }

    public void setSourceFile(String r1) {
        this.sourceFile = r1;
    }

    @Override // org.mvel2.ast.ASTNode
    public String toString() {
        return "[SourceLine:" + this.lineNumber + "]";
    }
}
