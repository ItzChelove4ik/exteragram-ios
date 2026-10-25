package org.commonmark.internal;

import org.commonmark.internal.util.Parsing;
import org.commonmark.node.Block;
import org.commonmark.node.BulletList;
import org.commonmark.node.ListBlock;
import org.commonmark.node.ListItem;
import org.commonmark.node.OrderedList;
import org.commonmark.parser.block.AbstractBlockParser;
import org.commonmark.parser.block.AbstractBlockParserFactory;
import org.commonmark.parser.block.BlockContinue;
import org.commonmark.parser.block.BlockParser;
import org.commonmark.parser.block.BlockStart;
import org.commonmark.parser.block.MatchedBlockParser;
import org.commonmark.parser.block.ParserState;

/* loaded from: classes5.dex */
public class ListBlockParser extends AbstractBlockParser {
    private final ListBlock block;
    private boolean hadBlankLine;
    private int linesAfterBlank;

    public static class Factory extends AbstractBlockParserFactory {
        public Factory() {
        }

        @Override // org.commonmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState r6, MatchedBlockParser r7) {
            BlockParser r5 = r7.getMatchedBlockParser();
            if (r6.getIndent() >= Parsing.CODE_BLOCK_INDENT) goto L5;
            int r0 = r6.getNextNonSpaceIndex();
            int r1 = r6.getColumn() + r6.getIndent();
            if (r7.getParagraphContent() == null) goto L9;
            boolean r72 = true;
        L10:
            ListData r73 = ListBlockParser.access$000(r6.getLine(), r0, r1, r72);
            if (r73 == null) goto L13;
            int r02 = r73.contentColumn;
            ListItemParser r12 = new ListItemParser(r02 - r6.getColumn());
            if ((r5 instanceof ListBlockParser) == true) goto L17;
        L21:
            ListBlockParser r52 = new ListBlockParser(r73.listBlock);
            r73.listBlock.setTight(true);
            return BlockStart.of(new BlockParser[]{r52, r12}).atColumn(r02);
        L17:
            if (ListBlockParser.access$100((ListBlock) r5.getBlock(), r73.listBlock) == false) goto L21;
            return BlockStart.of(new BlockParser[]{r12}).atColumn(r02);
        L13:
            return BlockStart.none();
        L9:
            r72 = false;
            goto L10
        L5:
            return BlockStart.none();
        }
    }

    public static class ListData {
        final int contentColumn;
        final ListBlock listBlock;

        public ListData(ListBlock r1, int r2) {
            this.listBlock = r1;
            this.contentColumn = r2;
        }
    }

    public static class ListMarkerData {
        final int indexAfterMarker;
        final ListBlock listBlock;

        public ListMarkerData(ListBlock r1, int r2) {
            this.listBlock = r1;
            this.indexAfterMarker = r2;
        }
    }

    public ListBlockParser(ListBlock r1) {
        this.block = r1;
    }

    public static /* synthetic */ ListData access$000(CharSequence r0, int r1, int r2, boolean r3) {
        return parseList(r0, r1, r2, r3);
    }

    public static /* synthetic */ boolean access$100(ListBlock r0, ListBlock r1) {
        return listsMatch(r0, r1);
    }

    private static boolean equals(Object r0, Object r1) {
        if (r0 != null) goto L9;
        if (r1 != null) goto L6;
        return true;
    L6:
        return false;
    L9:
        return r0.equals(r1);
    }

    private static boolean isSpaceTabOrEnd(CharSequence r2, int r3) {
        if (r3 >= r2.length()) goto L10;
        char r22 = r2.charAt(r3);
        if (r22 == '\t') goto L10;
        if (r22 == ' ') goto L10;
        return false;
    L10:
        return true;
    }

    private static boolean listsMatch(ListBlock r1, ListBlock r2) {
        if ((r1 instanceof BulletList) == false) goto L9;
        if ((r2 instanceof BulletList) == false) goto L9;
        return equals(Character.valueOf(((BulletList) r1).getBulletMarker()), Character.valueOf(((BulletList) r2).getBulletMarker()));
    L9:
        if ((r1 instanceof OrderedList) == true) goto L11;
        return false;
    L11:
        if ((r2 instanceof OrderedList) == true) goto L13;
        return false;
    L13:
        return equals(Character.valueOf(((OrderedList) r1).getDelimiter()), Character.valueOf(((OrderedList) r2).getDelimiter()));
    }

    private static ListData parseList(CharSequence r7, int r8, int r9, boolean r10) {
        ListMarkerData r0 = parseListMarker(r7, r8);
        if (r0 != null) goto L5;
        return null;
    L5:
        ListBlock r2 = r0.listBlock;
        int r02 = r0.indexAfterMarker;
        int r92 = r9 + (r02 - r8);
        int r82 = r7.length();
        int r3 = r92;
    L7:
        if (r02 >= r82) goto L16;
        char r5 = r7.charAt(r02);
        if (r5 != '\t') goto L12;
        r3 = r3 + Parsing.columnsToNextTabStop(r3);
    L14:
        r02 = r02 + 1;
        goto L7
    L12:
        if (r5 != ' ') goto L15;
        r3 = r3 + 1;
        goto L14
    L15:
        boolean r72 = true;
    L17:
        if (r10 == true) goto L19;
    L25:
        if (r72 == true) goto L27;
    L28:
        r3 = r92 + 1;
    L30:
        return new ListData(r2, r3);
    L27:
        if ((r3 - r92) <= Parsing.CODE_BLOCK_INDENT) goto L30;
    L19:
        if ((r2 instanceof OrderedList) == true) goto L21;
    L23:
        if (r72 == true) goto L25;
        return null;
    L21:
        if (((OrderedList) r2).getStartNumber() == 1) goto L23;
        return null;
    L16:
        r72 = false;
        goto L17
    }

    private static ListMarkerData parseListMarker(CharSequence r2, int r3) {
        char r0 = r2.charAt(r3);
        if (r0 != '*') goto L5;
    L10:
        int r32 = r3 + 1;
        if (isSpaceTabOrEnd(r2, r32) == false) goto L14;
        BulletList r22 = new BulletList();
        r22.setBulletMarker(r0);
        return new ListMarkerData(r22, r32);
    L14:
        return null;
    L5:
        if (r0 == '+') goto L10;
        if (r0 == '-') goto L10;
        return parseOrderedList(r2, r3);
    }

    private static ListMarkerData parseOrderedList(CharSequence r6, int r7) {
        int r0 = r6.length();
        int r1 = 0;
        int r2 = r7;
    L4:
        if (r2 >= r0) goto L21;
        char r4 = r6.charAt(r2);
        if (r4 == ')') goto L16;
        if (r4 == '.') goto L16;
        switch(r4) {
            case 48: goto L11;
            case 49: goto L11;
            case 50: goto L11;
            case 51: goto L11;
            case 52: goto L11;
            case 53: goto L11;
            case 54: goto L11;
            case 55: goto L11;
            case 56: goto L11;
            case 57: goto L11;
            default: goto L10;
        };
    L11:
        r1 = r1 + 1;
        if (r1 > 9) goto L13;
        r2 = r2 + 1;
        goto L4
    L13:
        return null;
    L10:
        return null;
    L16:
        if (r1 < 1) goto L21;
        int r02 = r2 + 1;
        if (isSpaceTabOrEnd(r6, r02) == false) goto L21;
        String r62 = r6.subSequence(r7, r2).toString();
        OrderedList r72 = new OrderedList();
        r72.setStartNumber(Integer.parseInt(r62));
        r72.setDelimiter(r4);
        return new ListMarkerData(r72, r02);
    L21:
        return null;
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public boolean canContain(Block r3) {
        if ((r3 instanceof ListItem) == true) goto L5;
        return false;
    L5:
        if (this.hadBlankLine == true) goto L7;
    L9:
        return true;
    L7:
        if (this.linesAfterBlank != 1) goto L9;
        this.block.setTight(false);
        this.hadBlankLine = false;
        goto L9
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
        if (r3.isBlank() == false) goto L6;
        this.hadBlankLine = true;
        this.linesAfterBlank = 0;
    L9:
        return BlockContinue.atIndex(r3.getIndex());
    L6:
        if (this.hadBlankLine == false) goto L9;
        this.linesAfterBlank++;
        goto L9
    }
}
