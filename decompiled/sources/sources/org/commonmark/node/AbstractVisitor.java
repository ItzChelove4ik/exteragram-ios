package org.commonmark.node;

/* loaded from: classes5.dex */
public abstract class AbstractVisitor implements Visitor {
    public AbstractVisitor() {
    }

    @Override // org.commonmark.node.Visitor
    public void visit(Code r1) {
        visitChildren(r1);
    }

    @Override // org.commonmark.node.Visitor
    public void visit(CustomBlock r1) {
        visitChildren(r1);
    }

    @Override // org.commonmark.node.Visitor
    public void visit(CustomNode r1) {
        visitChildren(r1);
    }

    @Override // org.commonmark.node.Visitor
    public void visit(Document r1) {
        visitChildren(r1);
    }

    @Override // org.commonmark.node.Visitor
    public void visit(Emphasis r1) {
        visitChildren(r1);
    }

    @Override // org.commonmark.node.Visitor
    public void visit(FencedCodeBlock r1) {
        visitChildren(r1);
    }

    @Override // org.commonmark.node.Visitor
    public void visit(HardLineBreak r1) {
        visitChildren(r1);
    }

    @Override // org.commonmark.node.Visitor
    public void visit(Heading r1) {
        visitChildren(r1);
    }

    @Override // org.commonmark.node.Visitor
    public void visit(HtmlBlock r1) {
        visitChildren(r1);
    }

    @Override // org.commonmark.node.Visitor
    public void visit(HtmlInline r1) {
        visitChildren(r1);
    }

    @Override // org.commonmark.node.Visitor
    public void visit(Image r1) {
        visitChildren(r1);
    }

    @Override // org.commonmark.node.Visitor
    public void visit(IndentedCodeBlock r1) {
        visitChildren(r1);
    }

    @Override // org.commonmark.node.Visitor
    public void visit(Link r1) {
        visitChildren(r1);
    }

    @Override // org.commonmark.node.Visitor
    public void visit(LinkReferenceDefinition r1) {
        visitChildren(r1);
    }

    @Override // org.commonmark.node.Visitor
    public void visit(ListItem r1) {
        visitChildren(r1);
    }

    @Override // org.commonmark.node.Visitor
    public void visit(SoftLineBreak r1) {
        visitChildren(r1);
    }

    @Override // org.commonmark.node.Visitor
    public void visit(StrongEmphasis r1) {
        visitChildren(r1);
    }

    @Override // org.commonmark.node.Visitor
    public void visit(Text r1) {
        visitChildren(r1);
    }

    @Override // org.commonmark.node.Visitor
    public void visit(ThematicBreak r1) {
        visitChildren(r1);
    }

    public void visitChildren(Node r2) {
        Node r22 = r2.getFirstChild();
    L3:
        if (r22 == null) goto L5;
        Node r0 = r22.getNext();
        r22.accept(this);
        r22 = r0;
        goto L3
    }
}
