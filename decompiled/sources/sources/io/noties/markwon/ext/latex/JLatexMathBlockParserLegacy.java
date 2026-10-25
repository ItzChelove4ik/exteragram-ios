package io.noties.markwon.ext.latex;

import okhttp3.internal.url._UrlKt;
import org.commonmark.node.Block;
import org.commonmark.parser.block.AbstractBlockParser;
import org.commonmark.parser.block.AbstractBlockParserFactory;
import org.commonmark.parser.block.BlockContinue;
import org.commonmark.parser.block.BlockParser;
import org.commonmark.parser.block.BlockStart;
import org.commonmark.parser.block.MatchedBlockParser;
import org.commonmark.parser.block.ParserState;

/* loaded from: classes5.dex */
class JLatexMathBlockParserLegacy extends AbstractBlockParser {
    private final JLatexMathBlock block;
    private final StringBuilder builder;
    private boolean isClosed;

    public static class Factory extends AbstractBlockParserFactory {
        public Factory() {
        }

        @Override // org.commonmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState r4, MatchedBlockParser r5) {
            CharSequence r3 = r4.getLine();
            if (r3 == null) goto L5;
            int r0 = r3.length();
        L7:
            if (r0 <= 1) goto L15;
            if ('$' != r3.charAt(0)) goto L15;
            if ('$' != r3.charAt(1)) goto L15;
            return BlockStart.of(new BlockParser[]{new JLatexMathBlockParserLegacy()}).atIndex(r4.getIndex() + 2);
        L15:
            return BlockStart.none();
        L5:
            r0 = 0;
            goto L7
        }
    }

    public JLatexMathBlockParserLegacy() {
        this.block = new JLatexMathBlock();
        this.builder = new StringBuilder();
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public void addLine(CharSequence r5) {
        if (this.builder.length() <= 0) goto L5;
        this.builder.append('\n');
    L5:
        this.builder.append(r5);
        int r52 = this.builder.length();
        boolean r0 = true;
        if (r52 > 1) goto L8;
        return;
    L8:
        if ('$' == this.builder.charAt(r52 - 1)) goto L10;
    L12:
        r0 = false;
    L13:
        this.isClosed = r0;
        if (r0 == false) goto L18;
        this.builder.replace(r52 - 2, r52, _UrlKt.FRAGMENT_ENCODE_SET);
        return;
    L18:
        return;
    L10:
        if ('$' != this.builder.charAt(r52 - 2)) goto L12;
        goto L12
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
    public BlockContinue tryContinue(ParserState r1) {
        if (this.isClosed == false) goto L7;
        return BlockContinue.finished();
    L7:
        return BlockContinue.atIndex(r1.getIndex());
    }
}
