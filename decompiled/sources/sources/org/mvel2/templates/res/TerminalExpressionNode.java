package org.mvel2.templates.res;

import org.mvel2.MVEL;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.templates.TemplateRuntime;
import org.mvel2.templates.util.TemplateOutputStream;

/* loaded from: classes5.dex */
public class TerminalExpressionNode extends Node {
    public TerminalExpressionNode() {
    }

    public TerminalExpressionNode(Node r2) {
        this.begin = r2.begin;
        this.name = r2.name;
        this.contents = r2.contents;
        this.cStart = r2.cStart;
        this.cEnd = r2.cEnd;
    }

    @Override // org.mvel2.templates.res.Node
    public boolean demarcate(Node r1, char[] r2) {
        return false;
    }

    @Override // org.mvel2.templates.res.Node
    public Object eval(TemplateRuntime r1, TemplateOutputStream r2, Object r3, VariableResolverFactory r4) {
        char[] r12 = this.contents;
        int r22 = this.cStart;
        return MVEL.eval(r12, r22, this.cEnd - r22, r3, r4);
    }
}
