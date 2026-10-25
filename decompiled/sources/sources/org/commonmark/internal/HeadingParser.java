package org.commonmark.internal;

import okhttp3.internal.url._UrlKt;
import org.commonmark.internal.util.Parsing;
import org.commonmark.node.Block;
import org.commonmark.node.Heading;
import org.commonmark.parser.InlineParser;
import org.commonmark.parser.block.AbstractBlockParser;
import org.commonmark.parser.block.AbstractBlockParserFactory;
import org.commonmark.parser.block.BlockContinue;
import org.commonmark.parser.block.BlockParser;
import org.commonmark.parser.block.BlockStart;
import org.commonmark.parser.block.MatchedBlockParser;
import org.commonmark.parser.block.ParserState;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes5.dex */
public class HeadingParser extends AbstractBlockParser {
    private final Heading block;
    private final String content;

    public static class Factory extends AbstractBlockParserFactory {
        public Factory() {
        }

        @Override // org.commonmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState r4, MatchedBlockParser r5) {
            if (r4.getIndent() >= Parsing.CODE_BLOCK_INDENT) goto L5;
            CharSequence r3 = r4.getLine();
            int r42 = r4.getNextNonSpaceIndex();
            HeadingParser r0 = HeadingParser.access$000(r3, r42);
            if (r0 != null) goto L9;
            int r43 = HeadingParser.access$100(r3, r42);
            if (r43 <= 0) goto L17;
            CharSequence r52 = r5.getParagraphContent();
            if (r52 == null) goto L17;
            return BlockStart.of(new BlockParser[]{new HeadingParser(r43, r52.toString())}).atIndex(r3.length()).replaceActiveBlockParser();
        L17:
            return BlockStart.none();
        L9:
            return BlockStart.of(new BlockParser[]{r0}).atIndex(r3.length());
        L5:
            return BlockStart.none();
        }
    }

    public HeadingParser(int r2, String r3) {
        Heading r0 = new Heading();
        this.block = r0;
        r0.setLevel(r2);
        this.content = r3;
    }

    public static /* synthetic */ HeadingParser access$000(CharSequence r0, int r1) {
        return getAtxHeading(r0, r1);
    }

    public static /* synthetic */ int access$100(CharSequence r0, int r1) {
        return getSetextHeadingLevel(r0, r1);
    }

    private static HeadingParser getAtxHeading(CharSequence r5, int r6) {
        int r0 = Parsing.skip('#', r5, r6, r5.length()) - r6;
        if (r0 != 0) goto L5;
    L22:
        return null;
    L5:
        if (r0 > 6) goto L22;
        int r62 = r6 + r0;
        if (r62 >= r5.length()) goto L10;
        char r3 = r5.charAt(r62);
        if (r3 != ' ') goto L14;
    L16:
        int r2 = Parsing.skipSpaceTabBackwards(r5, r5.length() - 1, r62);
        int r1 = Parsing.skipBackwards('#', r5, r2, r62);
        int r32 = Parsing.skipSpaceTabBackwards(r5, r1, r62);
        if (r32 == r1) goto L21;
        return new HeadingParser(r0, r5.subSequence(r62, r32 + 1).toString());
    L21:
        return new HeadingParser(r0, r5.subSequence(r62, r2 + 1).toString());
    L14:
        if (r3 == '\t') goto L16;
        return null;
    L10:
        return new HeadingParser(r0, _UrlKt.FRAGMENT_ENCODE_SET);
    }

    private static int getSetextHeadingLevel(CharSequence r4, int r5) {
        char r0 = r4.charAt(r5);
        if (r0 == '-') goto L11;
        if (r0 == '=') goto L8;
        return 0;
    L8:
        if (isSetextHeadingRest(r4, r5 + 1, SignatureVisitor.INSTANCEOF) == false) goto L11;
        return 1;
    L11:
        if (isSetextHeadingRest(r4, r5 + 1, SignatureVisitor.SUPER) == false) goto L14;
        return 2;
    L14:
        return 0;
    }

    private static boolean isSetextHeadingRest(CharSequence r1, int r2, char r3) {
        if (Parsing.skipSpaceTab(r1, Parsing.skip(r3, r1, r2, r1.length()), r1.length()) < r1.length()) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public Block getBlock() {
        return this.block;
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public void parseInlines(InlineParser r2) {
        r2.parse(this.content, this.block);
    }

    @Override // org.commonmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState r1) {
        return BlockContinue.none();
    }
}
