package io.noties.markwon.ext.latex;

import org.commonmark.node.CustomNode;

/* loaded from: classes5.dex */
public class JLatexMathNode extends CustomNode {
    private String latex;

    public JLatexMathNode() {
    }

    public String latex() {
        return this.latex;
    }

    public void latex(String r1) {
        this.latex = r1;
    }
}
