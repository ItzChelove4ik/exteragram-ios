package org.commonmark.parser.block;

import org.commonmark.node.Block;
import org.commonmark.parser.InlineParser;

/* loaded from: classes5.dex */
public abstract class AbstractBlockParser implements BlockParser {
    public AbstractBlockParser() {
    }

    @Override // org.commonmark.parser.block.BlockParser
    public void addLine(CharSequence r1) {
    }

    @Override // org.commonmark.parser.block.BlockParser
    public boolean canContain(Block r1) {
        return false;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public boolean canHaveLazyContinuationLines() {
        return false;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public void closeBlock() {
    }

    @Override // org.commonmark.parser.block.BlockParser
    public boolean isContainer() {
        return false;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public void parseInlines(InlineParser r1) {
    }
}
