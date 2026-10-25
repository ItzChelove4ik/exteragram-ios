package org.mvel2.templates.res;

import java.io.Serializable;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.templates.TemplateRuntime;
import org.mvel2.templates.util.TemplateOutputStream;

/* loaded from: classes5.dex */
public class CompiledTerminalExpressionNode extends TerminalExpressionNode {
    private Serializable ce;

    public CompiledTerminalExpressionNode(Node r3, ParserContext r4) {
        this.begin = r3.begin;
        this.name = r3.name;
        char[] r0 = r3.contents;
        int r1 = r3.cStart;
        this.ce = MVEL.compileExpression(r0, r1, r3.cEnd - r1, r4);
    }

    @Override // org.mvel2.templates.res.TerminalExpressionNode, org.mvel2.templates.res.Node
    public boolean demarcate(Node r1, char[] r2) {
        return false;
    }

    @Override // org.mvel2.templates.res.TerminalExpressionNode, org.mvel2.templates.res.Node
    public Object eval(TemplateRuntime r1, TemplateOutputStream r2, Object r3, VariableResolverFactory r4) {
        return MVEL.executeExpression(this.ce, r3, r4);
    }
}
