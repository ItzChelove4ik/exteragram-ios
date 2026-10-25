package org.mvel2.templates.res;

import java.io.Serializable;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.templates.TemplateRuntime;
import org.mvel2.templates.util.TemplateOutputStream;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class CompiledIfNode extends IfNode {
    private Serializable ce;

    public CompiledIfNode(int r1, String r2, char[] r3, int r4, int r5, ParserContext r6) {
        super(r1, r2, r3, r4, r5);
    L3:
        int r12 = this.cEnd;
        if (r12 <= this.cStart) goto L8;
        if (ParseTools.isWhitespace(r3[r12]) == false) goto L8;
        this.cEnd--;
    L8:
        int r13 = this.cStart;
        int r22 = this.cEnd;
        if (r13 == r22) goto L14;
        this.ce = MVEL.compileExpression(r3, r13, r22 - r4, r6);
        return;
    }

    @Override // org.mvel2.templates.res.IfNode, org.mvel2.templates.res.Node
    public Object eval(TemplateRuntime r3, TemplateOutputStream r4, Object r5, VariableResolverFactory r6) {
        Serializable r0 = this.ce;
        if (r0 == null) goto L14;
        if (((Boolean) MVEL.executeExpression(r0, r5, r6, Boolean.class)).booleanValue() == true) goto L14;
        Node r2 = this.next;
        if (r2 != null) goto L10;
        return null;
    L10:
        return r2.eval(r3, r4, r5, r6);
    L14:
        return this.trueNode.eval(r3, r4, r5, r6);
    }
}
