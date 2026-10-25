package io.noties.markwon.ext.latex;

import org.commonmark.node.CustomBlock;

/* loaded from: classes5.dex */
public class JLatexMathBlock extends CustomBlock {
    private String latex;

    public JLatexMathBlock() {
    }

    public String latex() {
        return this.latex;
    }

    public void latex(String r1) {
        this.latex = r1;
    }
}
