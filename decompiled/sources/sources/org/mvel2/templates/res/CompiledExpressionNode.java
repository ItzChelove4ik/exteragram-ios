package org.mvel2.templates.res;

import java.io.Serializable;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.templates.TemplateRuntime;
import org.mvel2.templates.util.TemplateOutputStream;

/* loaded from: classes5.dex */
public class CompiledExpressionNode extends ExpressionNode {
    private Serializable ce;

    public CompiledExpressionNode(int r1, String r2, char[] r3, int r4, int r5, ParserContext r6) {
        this.begin = r1;
        this.name = r2;
        this.contents = r3;
        this.cStart = r4;
        int r12 = r5 - 1;
        this.cEnd = r12;
        this.end = r5;
        this.ce = MVEL.compileExpression(r3, r4, r12 - r4, r6);
    }

    @Override // org.mvel2.templates.res.ExpressionNode, org.mvel2.templates.res.Node
    public Object eval(TemplateRuntime r2, TemplateOutputStream r3, Object r4, VariableResolverFactory r5) {
        r3.append(String.valueOf(MVEL.executeExpression(this.ce, r4, r5)));
        Node r1 = this.next;
        if (r1 != null) goto L5;
        return null;
    L5:
        return r1.eval(r2, r3, r4, r5);
    }

    @Override // org.mvel2.templates.res.ExpressionNode
    public String toString() {
        StringBuilder r0 = new StringBuilder("ExpressionNode:");
        r0.append(this.name);
        r0.append("{");
        char[] r1 = this.contents;
        if (r1 != null) goto L5;
        String r12 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        r0.append(r12);
        r0.append("} (start=");
        r0.append(this.begin);
        r0.append(";end=");
        r0.append(this.end);
        r0.append(")");
        return r0.toString();
    L5:
        r12 = new String(r1);
        goto L6
    }
}
