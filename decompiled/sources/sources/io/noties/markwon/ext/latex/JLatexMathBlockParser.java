package io.noties.markwon.ext.latex;

import kotlin.text.Typography;
import org.commonmark.internal.util.Parsing;
import org.commonmark.node.Block;
import org.commonmark.parser.block.AbstractBlockParser;
import org.commonmark.parser.block.AbstractBlockParserFactory;
import org.commonmark.parser.block.BlockContinue;
import org.commonmark.parser.block.BlockParser;
import org.commonmark.parser.block.BlockStart;
import org.commonmark.parser.block.MatchedBlockParser;
import org.commonmark.parser.block.ParserState;

/* loaded from: classes5.dex */
class JLatexMathBlockParser extends AbstractBlockParser {
    private final JLatexMathBlock block;
    private final StringBuilder builder;
    private final int signs;

    public static class Factory extends AbstractBlockParserFactory {
        public Factory() {
        }

        @Override // org.commonmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState r3, MatchedBlockParser r4) {
            if (r3.getIndent() >= Parsing.CODE_BLOCK_INDENT) goto L5;
            int r2 = r3.getNextNonSpaceIndex();
            CharSequence r32 = r3.getLine();
            int r42 = r32.length();
            int r0 = JLatexMathBlockParser.access$000(Typography.dollar, r32, r2, r42);
            if (r0 >= 2) goto L11;
            return BlockStart.none();
        L11:
            if (Parsing.skip(' ', r32, r2 + r0, r42) == r42) goto L15;
            return BlockStart.none();
        L15:
            return BlockStart.of(new BlockParser[]{new JLatexMathBlockParser(r0)}).atIndex(r42 + 1);
        L5:
            return BlockStart.none();
        }
    }

    public JLatexMathBlockParser(int r2) {
        this.block = new JLatexMathBlock();
        this.builder = new StringBuilder();
        this.signs = r2;
    }

    public static /* synthetic */ int access$000(char r0, CharSequence r1, int r2, int r3) {
        return consume(r0, r1, r2, r3);
    }

    private static int consume(char r2, CharSequence r3, int r4, int r5) {
        int r0 = r4;
    L3:
        if (r0 >= r5) goto L10;
        if (r2 != r3.charAt(r0)) goto L7;
        r0 = r0 + 1;
        goto L3
    L7:
        return r0 - r4;
    L10:
        return r5 - r4;
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public void addLine(CharSequence r2) {
        this.builder.append(r2);
        this.builder.append('\n');
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public void closeBlock() {
        this.block.latex(this.builder.toString());
    }

    @Override // org.commonmark.parser.block.BlockParser
    public Block getBlock() {
        return this.block;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState r6) {
        int r0 = r6.getNextNonSpaceIndex();
        CharSequence r1 = r6.getLine();
        int r2 = r1.length();
        if (r6.getIndent() >= Parsing.CODE_BLOCK_INDENT) goto L11;
        int r3 = consume(Typography.dollar, r1, r0, r2);
        int r5 = this.signs;
        if (r3 != r5) goto L11;
        if (Parsing.skip(' ', r1, r0 + r5, r2) != r2) goto L11;
        return BlockContinue.finished();
    L11:
        return BlockContinue.atIndex(r6.getIndex());
    }
}
