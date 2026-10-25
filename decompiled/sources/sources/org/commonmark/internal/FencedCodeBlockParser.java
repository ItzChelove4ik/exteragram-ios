package org.commonmark.internal;

import org.commonmark.internal.util.Escaping;
import org.commonmark.internal.util.Parsing;
import org.commonmark.node.Block;
import org.commonmark.node.FencedCodeBlock;
import org.commonmark.parser.block.AbstractBlockParser;
import org.commonmark.parser.block.AbstractBlockParserFactory;
import org.commonmark.parser.block.BlockContinue;
import org.commonmark.parser.block.BlockParser;
import org.commonmark.parser.block.BlockStart;
import org.commonmark.parser.block.MatchedBlockParser;
import org.commonmark.parser.block.ParserState;

/* loaded from: classes5.dex */
public class FencedCodeBlockParser extends AbstractBlockParser {
    private final FencedCodeBlock block;
    private String firstLine;
    private StringBuilder otherLines;

    public static class Factory extends AbstractBlockParserFactory {
        public Factory() {
        }

        @Override // org.commonmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState r2, MatchedBlockParser r3) {
            int r1 = r2.getIndent();
            if (r1 >= Parsing.CODE_BLOCK_INDENT) goto L5;
            int r32 = r2.getNextNonSpaceIndex();
            FencedCodeBlockParser r12 = FencedCodeBlockParser.access$000(r2.getLine(), r32, r1);
            if (r12 == null) goto L11;
            return BlockStart.of(new BlockParser[]{r12}).atIndex(r32 + FencedCodeBlockParser.access$100(r12).getFenceLength());
        L11:
            return BlockStart.none();
        L5:
            return BlockStart.none();
        }
    }

    public FencedCodeBlockParser(char r3, int r4, int r5) {
        FencedCodeBlock r0 = new FencedCodeBlock();
        this.block = r0;
        this.otherLines = new StringBuilder();
        r0.setFenceChar(r3);
        r0.setFenceLength(r4);
        r0.setFenceIndent(r5);
    }

    public static /* synthetic */ FencedCodeBlockParser access$000(CharSequence r0, int r1, int r2) {
        return checkOpener(r0, r1, r2);
    }

    public static /* synthetic */ FencedCodeBlock access$100(FencedCodeBlockParser r0) {
        return r0.block;
    }

    private static FencedCodeBlockParser checkOpener(CharSequence r7, int r8, int r9) {
        int r0 = r7.length();
        int r1 = 0;
        int r3 = r8;
        int r2 = 0;
    L4:
        if (r3 >= r0) goto L13;
        char r6 = r7.charAt(r3);
        if (r6 == '`') goto L10;
        if (r6 != '~') goto L13;
        r2 = r2 + 1;
    L11:
        r3 = r3 + 1;
        goto L4
    L10:
        r1 = r1 + 1;
    L13:
        if (r1 < 3) goto L20;
        if (r2 != 0) goto L20;
        if (Parsing.find('`', r7, r8 + r1) == (-1)) goto L19;
        return null;
    L19:
        return new FencedCodeBlockParser('`', r1, r9);
    L20:
        if (r2 < 3) goto L24;
        if (r1 != 0) goto L24;
        return new FencedCodeBlockParser('~', r2, r9);
    L24:
        return null;
    }

    private boolean isClosing(CharSequence r3, int r4) {
        char r0 = this.block.getFenceChar();
        int r2 = this.block.getFenceLength();
        int r02 = Parsing.skip(r0, r3, r4, r3.length()) - r4;
        if (r02 >= r2) goto L6;
        return false;
    L6:
        if (Parsing.skipSpaceTab(r3, r4 + r02, r3.length()) != r3.length()) goto L9;
        return true;
    L9:
        return false;
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public void addLine(CharSequence r2) {
        if (this.firstLine != null) goto L6;
        this.firstLine = r2.toString();
        return;
    L6:
        this.otherLines.append(r2);
        this.otherLines.append('\n');
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public void closeBlock() {
        this.block.setInfo(Escaping.unescapeString(this.firstLine.trim()));
        this.block.setLiteral(this.otherLines.toString());
    }

    @Override // org.commonmark.parser.block.BlockParser
    public Block getBlock() {
        return this.block;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState r5) {
        int r0 = r5.getNextNonSpaceIndex();
        int r1 = r5.getIndex();
        CharSequence r2 = r5.getLine();
        if (r5.getIndent() < Parsing.CODE_BLOCK_INDENT) goto L5;
    L8:
        int r4 = this.block.getFenceIndent();
        int r52 = r2.length();
    L9:
        if (r4 <= 0) goto L15;
        if (r1 >= r52) goto L15;
        if (r2.charAt(r1) != ' ') goto L15;
        r1 = r1 + 1;
        r4 = r4 - 1;
    L15:
        return BlockContinue.atIndex(r1);
    L5:
        if (isClosing(r2, r0) == false) goto L8;
        return BlockContinue.finished();
    }
}
