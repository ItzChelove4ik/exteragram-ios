package org.commonmark.ext.gfm.tables.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TableBody;
import org.commonmark.ext.gfm.tables.TableCell;
import org.commonmark.ext.gfm.tables.TableHead;
import org.commonmark.ext.gfm.tables.TableRow;
import org.commonmark.node.Block;
import org.commonmark.parser.InlineParser;
import org.commonmark.parser.block.AbstractBlockParser;
import org.commonmark.parser.block.AbstractBlockParserFactory;
import org.commonmark.parser.block.BlockContinue;
import org.commonmark.parser.block.BlockParser;
import org.commonmark.parser.block.BlockStart;
import org.commonmark.parser.block.MatchedBlockParser;
import org.commonmark.parser.block.ParserState;

/* loaded from: classes5.dex */
public class TableBlockParser extends AbstractBlockParser {
    private final TableBlock block;
    private final List<CharSequence> bodyLines;
    private final List<TableCell.Alignment> columns;
    private final List<String> headerCells;
    private boolean nextIsSeparatorLine;

    /* renamed from: org.commonmark.ext.gfm.tables.internal.TableBlockParser$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class Factory extends AbstractBlockParserFactory {
        public Factory() {
        }

        @Override // org.commonmark.parser.block.BlockParserFactory
        public BlockStart tryStart(ParserState r3, MatchedBlockParser r4) {
            CharSequence r2 = r3.getLine();
            CharSequence r42 = r4.getParagraphContent();
            if (r42 == null) goto L17;
            if (r42.toString().contains("|") == false) goto L17;
            if (r42.toString().contains("\n") == true) goto L17;
            List r22 = TableBlockParser.access$000(r2.subSequence(r3.getIndex(), r2.length()));
            if (r22 == null) goto L17;
            if (r22.isEmpty() == true) goto L17;
            List r43 = TableBlockParser.access$100(r42);
            if (r22.size() < r43.size()) goto L17;
            return BlockStart.of(new BlockParser[]{new TableBlockParser(r22, r43, null)}).atIndex(r3.getIndex()).replaceActiveBlockParser();
        L17:
            return BlockStart.none();
        }
    }

    private TableBlockParser(List<TableCell.Alignment> r2, List<String> r3) {
        this.block = new TableBlock();
        this.bodyLines = new ArrayList();
        this.nextIsSeparatorLine = true;
        this.columns = r2;
        this.headerCells = r3;
    }

    public /* synthetic */ TableBlockParser(List r1, List r2, AnonymousClass1 r3) {
        this(r1, r2);
    }

    public static /* synthetic */ List access$000(CharSequence r0) {
        return parseSeparator(r0);
    }

    public static /* synthetic */ List access$100(CharSequence r0) {
        return split(r0);
    }

    private static TableCell.Alignment getAlignment(boolean r0, boolean r1) {
        if (r0 == false) goto L6;
        if (r1 == false) goto L6;
        return TableCell.Alignment.CENTER;
    L6:
        if (r0 == true) goto L8;
        if (r1 == true) goto L11;
        return null;
    L11:
        return TableCell.Alignment.RIGHT;
    L8:
        return TableCell.Alignment.LEFT;
    }

    private TableCell parseCell(String r3, int r4, InlineParser r5) {
        TableCell r0 = new TableCell();
        if (r4 >= this.columns.size()) goto L5;
        r0.setAlignment(this.columns.get(r4));
    L5:
        r5.parse(r3.trim(), r0);
        return r0;
    }

    private static List<TableCell.Alignment> parseSeparator(CharSequence r11) {
        ArrayList r0 = new ArrayList();
        int r2 = 0;
        boolean r3 = false;
        int r4 = 0;
    L4:
        if (r2 >= r11.length()) goto L42;
        char r5 = r11.charAt(r2);
        if (r5 == '\t') goto L41;
        if (r5 == ' ') goto L41;
        boolean r9 = true;
        if (r5 == '-') goto L19;
        if (r5 == ':') goto L19;
        if (r5 != '|') goto L14;
        r2 = r2 + 1;
        r4 = r4 + 1;
        if (r4 > 1) goto L17;
        r3 = true;
        goto L4
    L17:
        return null;
    L14:
        return null;
    L19:
        if (r4 == 0) goto L21;
    L23:
        if (r5 != ':') goto L25;
        r2 = r2 + 1;
        boolean r42 = true;
    L26:
        boolean r52 = false;
    L28:
        if (r2 >= r11.length()) goto L32;
        if (r11.charAt(r2) != '-') goto L32;
        r2 = r2 + 1;
        r52 = true;
    L32:
        if (r52 == false) goto L33;
        if (r2 < r11.length()) goto L37;
    L39:
        r9 = false;
    L40:
        r0.add(getAlignment(r42, r9));
        r4 = 0;
        goto L4
    L37:
        if (r11.charAt(r2) != ':') goto L39;
        r2 = r2 + 1;
        goto L40
    L33:
        return null;
    L25:
        r42 = false;
        goto L26
    L21:
        if (r0.isEmpty() == true) goto L23;
        return null;
    L41:
        r2 = r2 + 1;
        goto L4
    L42:
        if (r3 == true) goto L44;
        return null;
    L44:
        return r0;
    }

    private static List<String> split(CharSequence r9) {
        String r92 = r9.toString().trim();
        if (r92.startsWith("|") == false) goto L5;
        r92 = r92.substring(1);
    L5:
        ArrayList r0 = new ArrayList();
        StringBuilder r2 = new StringBuilder();
        int r4 = 0;
    L7:
        if (r4 >= r92.length()) goto L21;
        char r5 = r92.charAt(r4);
        if (r5 == '\\') goto L13;
        if (r5 == '|') goto L12;
        r2.append(r5);
    L19:
        r4 = r4 + 1;
        goto L7
    L12:
        r0.add(r2.toString());
        r2.setLength(0);
        goto L19
    L13:
        int r52 = r4 + 1;
        if (r52 < r92.length()) goto L16;
    L18:
        r2.append('\\');
        goto L19
    L16:
        if (r92.charAt(r52) != '|') goto L18;
        r2.append('|');
        r4 = r52;
        goto L19
    L21:
        if (r2.length() <= 0) goto L23;
        r0.add(r2.toString());
    L23:
        return r0;
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public void addLine(CharSequence r2) {
        if (this.nextIsSeparatorLine == false) goto L6;
        this.nextIsSeparatorLine = false;
        return;
    L6:
        this.bodyLines.add(r2);
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public boolean canHaveLazyContinuationLines() {
        return true;
    }

    @Override // org.commonmark.parser.block.BlockParser
    public Block getBlock() {
        return this.block;
    }

    @Override // org.commonmark.parser.block.AbstractBlockParser, org.commonmark.parser.block.BlockParser
    public void parseInlines(InlineParser r9) {
        int r0 = this.headerCells.size();
        TableHead r1 = new TableHead();
        this.block.appendChild(r1);
        TableRow r2 = new TableRow();
        r1.appendChild(r2);
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L5;
        TableCell r4 = parseCell(this.headerCells.get(r3), r3, r9);
        r4.setHeader(true);
        r2.appendChild(r4);
        r3 = r3 + 1;
        goto L3
    L5:
        Iterator<CharSequence> r22 = this.bodyLines.iterator();
        TableBody r32 = null;
    L7:
        if (r22.hasNext() == false) goto L18;
        List<String> r42 = split(r22.next());
        TableRow r5 = new TableRow();
        int r6 = 0;
    L9:
        if (r6 >= r0) goto L15;
        if (r6 >= r42.size()) goto L13;
        String r7 = r42.get(r6);
    L14:
        r5.appendChild(parseCell(r7, r6, r9));
        r6 = r6 + 1;
        goto L9
    L13:
        r7 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L14
    L15:
        if (r32 != null) goto L17;
        r32 = new TableBody();
        this.block.appendChild(r32);
    L17:
        r32.appendChild(r5);
        goto L7
    }

    @Override // org.commonmark.parser.block.BlockParser
    public BlockContinue tryContinue(ParserState r2) {
        if (r2.getLine().toString().contains("|") == false) goto L7;
        return BlockContinue.atIndex(r2.getIndex());
    L7:
        return BlockContinue.none();
    }
}
