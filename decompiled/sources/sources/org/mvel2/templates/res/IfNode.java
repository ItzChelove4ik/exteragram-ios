package org.mvel2.templates.res;

import org.mvel2.MVEL;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.templates.TemplateRuntime;
import org.mvel2.templates.util.TemplateOutputStream;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class IfNode extends Node {
    protected Node elseNode;
    protected Node trueNode;

    public IfNode(int r1, String r2, char[] r3, int r4, int r5) {
        super(r1, r2, r3, r4, r5);
    L3:
        int r12 = this.cEnd;
        if (r12 <= this.cStart) goto L8;
        if (ParseTools.isWhitespace(r3[r12]) == false) goto L11;
        this.cEnd--;
        goto L3
    L11:
        return;
    }

    @Override // org.mvel2.templates.res.Node
    public boolean demarcate(Node r1, char[] r2) {
        this.trueNode = this.next;
        this.next = this.terminus;
        return true;
    }

    @Override // org.mvel2.templates.res.Node
    public Object eval(TemplateRuntime r8, TemplateOutputStream r9, Object r10, VariableResolverFactory r11) {
        int r0 = this.cEnd;
        int r2 = this.cStart;
        if (r0 == r2) goto L13;
        Object r4 = r10;
        VariableResolverFactory r5 = r11;
        if (((Boolean) MVEL.eval(this.contents, r2, r0 - r2, r4, r5, Boolean.class)).booleanValue() == true) goto L15;
        Node r7 = this.next;
        if (r7 != null) goto L10;
        return null;
    L10:
        return r7.eval(r8, r9, r4, r5);
    L15:
        return this.trueNode.eval(r8, r9, r4, r5);
    L13:
        r4 = r10;
        r5 = r11;
        goto L15
    }

    public Node getElseNode() {
        return this.elseNode;
    }

    public Node getTrueNode() {
        return this.trueNode;
    }

    public void setElseNode(ExpressionNode r1) {
        this.elseNode = r1;
    }

    public void setTrueNode(ExpressionNode r1) {
        this.trueNode = r1;
    }
}
