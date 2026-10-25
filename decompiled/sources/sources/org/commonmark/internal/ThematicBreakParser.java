package org.commonmark.internal;

import org.commonmark.node.Block;
import org.commonmark.node.ThematicBreak;
import org.commonmark.parser.block.AbstractBlockParser;
import org.commonmark.parser.block.AbstractBlockParserFactory;
import org.commonmark.parser.block.BlockContinue;
import org.commonmark.parser.block.BlockParser;
import org.commonmark.parser.block.BlockStart;
import org.commonmark.parser.block.MatchedBlockParser;
import org.commonmark.parser.block.ParserState;

/* loaded from: classes5.dex */
public class ThematicBreakParser extends AbstractBlockParser {
    private final ThematicBreak block;

    public static class Factory extends AbstractBlockParserFactory {
        public Factory() {
        }

        @Override // org.commonmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState r2, MatchedBlockParser r3) {
            if (r2.getIndent() >= 4) goto L5;
            int r1 = r2.getNextNonSpaceIndex();
            CharSequence r22 = r2.getLine();
            if (ThematicBreakParser.access$000(r22, r1) == false) goto L11;
            return BlockStart.of(new BlockParser[]{new ThematicBreakParser()}).atIndex(r22.length());
        L11:
            return BlockStart.none();
        L5:
            return BlockStart.none();
        }
    }

    public ThematicBreakParser() {
        this.block = new ThematicBreak();
    }

    public static /* synthetic */ boolean access$000(CharSequence r0, int r1) {
        return isThematicBreak(r0, r1);
    }

    private static boolean isThematicBreak(CharSequence r7, int r8) {
        int r0 = r7.length();
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r8 >= r0) goto L20;
        char r5 = r7.charAt(r8);
        if (r5 == '\t') goto L18;
        if (r5 == ' ') goto L18;
        if (r5 != '*') goto L11;
        r4 = r4 + 1;
        goto L18
    L11:
        if (r5 != '-') goto L13;
        r2 = r2 + 1;
        goto L18
    L13:
        if (r5 != '_') goto L14;
        r3 = r3 + 1;
        goto L18
    L14:
        return false;
    L18:
        r8 = r8 + 1;
        goto L3
    L20:
        if (r2 < 3) goto L23;
        if (r3 != 0) goto L23;
        if (r4 != 0) goto L23;
        return true;
    L23:
        if (r3 < 3) goto L26;
        if (r2 != 0) goto L26;
        if (r4 != 0) goto L26;
        return true;
    L26:
        if (r4 < 3) goto L31;
        if (r2 != 0) goto L31;
        if (r3 != 0) goto L31;
        return true;
    L31:
        return false;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public Block getBlock() {
        return this.block;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState r1) {
        return BlockContinue.none();
    }
}
