package org.commonmark.internal;

import java.util.ArrayList;
import java.util.List;
import org.commonmark.internal.util.Parsing;
import org.commonmark.node.Block;
import org.commonmark.node.IndentedCodeBlock;
import org.commonmark.node.Paragraph;
import org.commonmark.parser.block.AbstractBlockParser;
import org.commonmark.parser.block.AbstractBlockParserFactory;
import org.commonmark.parser.block.BlockContinue;
import org.commonmark.parser.block.BlockParser;
import org.commonmark.parser.block.BlockStart;
import org.commonmark.parser.block.MatchedBlockParser;
import org.commonmark.parser.block.ParserState;

/* loaded from: classes5.dex */
public class IndentedCodeBlockParser extends AbstractBlockParser {
    private final IndentedCodeBlock block;
    private final List<CharSequence> lines;

    public static class Factory extends AbstractBlockParserFactory {
        public Factory() {
        }

        @Override // org.commonmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState r2, MatchedBlockParser r3) {
            if (r2.getIndent() < Parsing.CODE_BLOCK_INDENT) goto L11;
            if (r2.isBlank() == true) goto L11;
            if ((r2.getActiveBlockParser().getBlock() instanceof Paragraph) == true) goto L11;
            return BlockStart.of(new BlockParser[]{new IndentedCodeBlockParser()}).atColumn(r2.getColumn() + Parsing.CODE_BLOCK_INDENT);
        L11:
            return BlockStart.none();
        }
    }

    public IndentedCodeBlockParser() {
        this.block = new IndentedCodeBlock();
        this.lines = new ArrayList();
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public void addLine(CharSequence r1) {
        this.lines.add(r1);
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public void closeBlock() {
        int r0 = this.lines.size() - 1;
    L3:
        if (r0 < 0) goto L8;
        if (Parsing.isBlank(this.lines.get(r0)) == false) goto L8;
        r0 = r0 - 1;
    L8:
        StringBuilder r1 = new StringBuilder();
        int r2 = 0;
    L10:
        if (r2 >= (r0 + 1)) goto L12;
        r1.append(this.lines.get(r2));
        r1.append('\n');
        r2 = r2 + 1;
        goto L10
    L12:
        this.block.setLiteral(r1.toString());
    }

    @Override // org.commonmark.parser.block.BlockParser
    public Block getBlock() {
        return this.block;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState r2) {
        if (r2.getIndent() < Parsing.CODE_BLOCK_INDENT) goto L7;
        return BlockContinue.atColumn(r2.getColumn() + Parsing.CODE_BLOCK_INDENT);
    L7:
        if (r2.isBlank() == false) goto L11;
        return BlockContinue.atIndex(r2.getNextNonSpaceIndex());
    L11:
        return BlockContinue.none();
    }
}
