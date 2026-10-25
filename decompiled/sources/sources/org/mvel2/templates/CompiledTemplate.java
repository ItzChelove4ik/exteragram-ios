package org.mvel2.templates;

import java.io.Serializable;
import org.mvel2.templates.res.Node;

/* loaded from: classes5.dex */
public class CompiledTemplate implements Serializable {
    private Node root;
    private char[] template;

    public CompiledTemplate(char[] r1, Node r2) {
        this.template = r1;
        this.root = r2;
    }

    public Node getRoot() {
        return this.root;
    }

    public char[] getTemplate() {
        return this.template;
    }

    public void setRoot(Node r1) {
        this.root = r1;
    }

    public void setTemplate(char[] r1) {
        this.template = r1;
    }
}
