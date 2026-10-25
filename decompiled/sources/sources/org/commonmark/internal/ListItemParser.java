package org.commonmark.internal;

import org.commonmark.node.Block;
import org.commonmark.node.ListBlock;
import org.commonmark.node.ListItem;
import org.commonmark.node.Paragraph;
import org.commonmark.parser.block.AbstractBlockParser;
import org.commonmark.parser.block.BlockContinue;
import org.commonmark.parser.block.ParserState;

/* loaded from: classes5.dex */
public class ListItemParser extends AbstractBlockParser {
    private final ListItem block;
    private int contentIndent;
    private boolean hadBlankLine;

    public ListItemParser(int r2) {
        this.block = new ListItem();
        this.contentIndent = r2;
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public boolean canContain(Block r1) {
        if (this.hadBlankLine == false) goto L9;
        Block r0 = this.block.getParent();
        if ((r0 instanceof ListBlock) == false) goto L10;
        ((ListBlock) r0).setTight(false);
        return true;
    L10:
        return true;
    L9:
        return true;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public Block getBlock() {
        return this.block;
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public boolean isContainer() {
        return true;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState r3) {
        if (r3.isBlank() == false) goto L18;
        if (this.block.getFirstChild() == null) goto L7;
        Block r0 = r3.getActiveBlockParser().getBlock();
        if ((r0 instanceof Paragraph) == false) goto L11;
    L14:
        boolean r02 = true;
    L15:
        this.hadBlankLine = r02;
        return BlockContinue.atIndex(r3.getNextNonSpaceIndex());
    L11:
        if ((r0 instanceof ListItem) == true) goto L14;
        r02 = false;
        goto L15
    L7:
        return BlockContinue.none();
    L18:
        if (r3.getIndent() < this.contentIndent) goto L22;
        return BlockContinue.atColumn(r3.getColumn() + this.contentIndent);
    L22:
        return BlockContinue.none();
    }
}
