package org.commonmark.parser.block;

import org.commonmark.node.Block;
import org.commonmark.parser.InlineParser;

/* loaded from: classes5.dex */
public interface BlockParser {
    void addLine(CharSequence r1);

    boolean canContain(Block r1);

    boolean canHaveLazyContinuationLines();

    void closeBlock();

    Block getBlock();

    boolean isContainer();

    void parseInlines(InlineParser r1);

    BlockContinue tryContinue(ParserState r1);
}
