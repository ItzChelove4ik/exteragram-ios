package org.mvel2.templates.res;

import java.io.Serializable;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.templates.CompiledTemplate;
import org.mvel2.templates.SimpleTemplateRegistry;
import org.mvel2.templates.TemplateRuntime;
import org.mvel2.templates.util.TemplateOutputStream;

/* loaded from: classes5.dex */
public class CompiledDeclareNode extends Node {
    private Serializable ce;
    private Node nestedNode;

    public CompiledDeclareNode(int r1, String r2, char[] r3, int r4, int r5, ParserContext r6) {
        this.begin = r1;
        this.name = r2;
        this.contents = r3;
        this.cStart = r4;
        int r12 = r5 - 1;
        this.cEnd = r12;
        this.end = r5;
        this.ce = MVEL.compileExpression(r3, r4, r12 - r4, r6);
    }

    @Override // org.mvel2.templates.res.Node
    public boolean demarcate(Node r1, char[] r2) {
        Node r12 = this.next;
        this.nestedNode = r12;
    L4:
        if (r12.getNext() == null) goto L6;
        r12 = r12.next;
        goto L4
    L6:
        r12.next = new EndNode();
        this.next = this.terminus;
        return false;
    }

    @Override // org.mvel2.templates.res.Node
    public Object eval(TemplateRuntime r6, TemplateOutputStream r7, Object r8, VariableResolverFactory r9) {
        if (r6.getNamedTemplateRegistry() != null) goto L5;
        r6.setNamedTemplateRegistry(new SimpleTemplateRegistry());
    L5:
        r6.getNamedTemplateRegistry().addNamedTemplate((String) MVEL.executeExpression(this.ce, r8, r9, String.class), new CompiledTemplate(r6.getTemplate(), this.nestedNode));
        Node r5 = this.next;
        if (r5 != null) goto L8;
        return null;
    L8:
        return r5.eval(r6, r7, r8, r9);
    }
}
