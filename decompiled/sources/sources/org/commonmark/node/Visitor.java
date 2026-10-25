package org.commonmark.node;

/* loaded from: classes5.dex */
public interface Visitor {
    void visit(BlockQuote r1);

    void visit(BulletList r1);

    void visit(Code r1);

    void visit(CustomBlock r1);

    void visit(CustomNode r1);

    void visit(Document r1);

    void visit(Emphasis r1);

    void visit(FencedCodeBlock r1);

    void visit(HardLineBreak r1);

    void visit(Heading r1);

    void visit(HtmlBlock r1);

    void visit(HtmlInline r1);

    void visit(Image r1);

    void visit(IndentedCodeBlock r1);

    void visit(Link r1);

    void visit(LinkReferenceDefinition r1);

    void visit(ListItem r1);

    void visit(OrderedList r1);

    void visit(Paragraph r1);

    void visit(SoftLineBreak r1);

    void visit(StrongEmphasis r1);

    void visit(Text r1);

    void visit(ThematicBreak r1);
}
