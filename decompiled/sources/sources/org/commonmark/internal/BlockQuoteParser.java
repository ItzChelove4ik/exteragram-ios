package org.commonmark.internal;

import org.commonmark.internal.util.Parsing;
import org.commonmark.node.Block;
import org.commonmark.node.BlockQuote;
import org.commonmark.parser.block.AbstractBlockParser;
import org.commonmark.parser.block.AbstractBlockParserFactory;
import org.commonmark.parser.block.BlockContinue;
import org.commonmark.parser.block.BlockParser;
import org.commonmark.parser.block.BlockStart;
import org.commonmark.parser.block.MatchedBlockParser;
import org.commonmark.parser.block.ParserState;

/* loaded from: classes5.dex */
public class BlockQuoteParser extends AbstractBlockParser {
    private final BlockQuote block;

    public static class Factory extends AbstractBlockParserFactory {
        public Factory() {
        }

        @Override // org.commonmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState r3, MatchedBlockParser r4) {
            int r2 = r3.getNextNonSpaceIndex();
            if (BlockQuoteParser.access$000(r3, r2) == false) goto L10;
            int r42 = r3.getColumn() + r3.getIndent();
            int r0 = r42 + 1;
            if (Parsing.isSpaceOrTab(r3.getLine(), r2 + 1) == false) goto L8;
            r0 = r42 + 2;
        L8:
            return BlockStart.of(new BlockParser[]{new BlockQuoteParser()}).atColumn(r0);
        L10:
            return BlockStart.none();
        }
    }

    public BlockQuoteParser() {
        this.block = new BlockQuote();
    }

    public static /* synthetic */ boolean access$000(ParserState r0, int r1) {
        return isMarker(r0, r1);
    }

    private static boolean isMarker(ParserState r2, int r3) {
        CharSequence r0 = r2.getLine();
        if (r2.getIndent() < Parsing.CODE_BLOCK_INDENT) goto L5;
        return false;
    L5:
        if (r3 < r0.length()) goto L7;
        return false;
    L7:
        if (r0.charAt(r3) != '>') goto L13;
        return true;
    L13:
        return false;
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public boolean canContain(Block r1) {
        return true;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public /* bridge */ /* synthetic */ Block getBlock() {
        return getBlock();
    }

    @Override // org.commonmark.parser.block.BlockParser
    public BlockQuote getBlock() {
        return this.block;
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public boolean isContainer() {
        return true;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState r3) {
        int r2 = r3.getNextNonSpaceIndex();
        if (isMarker(r3, r2) == false) goto L10;
        int r0 = r3.getColumn() + r3.getIndent();
        int r1 = r0 + 1;
        if (Parsing.isSpaceOrTab(r3.getLine(), r2 + 1) == false) goto L8;
        r1 = r0 + 2;
    L8:
        return BlockContinue.atColumn(r1);
    L10:
        return BlockContinue.none();
    }
}
