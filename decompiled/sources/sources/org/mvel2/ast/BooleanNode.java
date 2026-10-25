package org.mvel2.ast;

import org.mvel2.ParserContext;

/* loaded from: classes5.dex */
public abstract class BooleanNode extends ASTNode {
    protected ASTNode left;
    protected ASTNode right;

    public BooleanNode(ParserContext r1) {
        super(r1);
    }

    public ASTNode getLeft() {
        return this.left;
    }

    public ASTNode getRight() {
        return this.right;
    }

    public abstract ASTNode getRightMost();

    public void setLeft(ASTNode r1) {
        this.left = r1;
    }

    public void setRight(ASTNode r1) {
        this.right = r1;
    }

    public abstract void setRightMost(ASTNode r1);
}
