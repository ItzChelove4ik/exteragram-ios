package org.commonmark.node;

/* loaded from: classes5.dex */
public class FencedCodeBlock extends Block {
    private char fenceChar;
    private int fenceIndent;
    private int fenceLength;
    private String info;
    private String literal;

    public FencedCodeBlock() {
    }

    @Override // org.commonmark.node.Node
    public void accept(Visitor r1) {
        r1.visit(this);
    }

    public char getFenceChar() {
        return this.fenceChar;
    }

    public int getFenceIndent() {
        return this.fenceIndent;
    }

    public int getFenceLength() {
        return this.fenceLength;
    }

    public String getInfo() {
        return this.info;
    }

    public String getLiteral() {
        return this.literal;
    }

    public void setFenceChar(char r1) {
        this.fenceChar = r1;
    }

    public void setFenceIndent(int r1) {
        this.fenceIndent = r1;
    }

    public void setFenceLength(int r1) {
        this.fenceLength = r1;
    }

    public void setInfo(String r1) {
        this.info = r1;
    }

    public void setLiteral(String r1) {
        this.literal = r1;
    }
}
