package org.commonmark.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.commonmark.internal.BlockQuoteParser;
import org.commonmark.internal.FencedCodeBlockParser;
import org.commonmark.internal.HeadingParser;
import org.commonmark.internal.HtmlBlockParser;
import org.commonmark.internal.IndentedCodeBlockParser;
import org.commonmark.internal.ListBlockParser;
import org.commonmark.internal.ThematicBreakParser;
import org.commonmark.internal.util.Parsing;
import org.commonmark.node.Block;
import org.commonmark.node.BlockQuote;
import org.commonmark.node.Document;
import org.commonmark.node.FencedCodeBlock;
import org.commonmark.node.Heading;
import org.commonmark.node.HtmlBlock;
import org.commonmark.node.IndentedCodeBlock;
import org.commonmark.node.LinkReferenceDefinition;
import org.commonmark.node.ListBlock;
import org.commonmark.node.Paragraph;
import org.commonmark.node.ThematicBreak;
import org.commonmark.parser.InlineParser;
import org.commonmark.parser.InlineParserFactory;
import org.commonmark.parser.block.BlockContinue;
import org.commonmark.parser.block.BlockParser;
import org.commonmark.parser.block.BlockParserFactory;
import org.commonmark.parser.block.BlockStart;
import org.commonmark.parser.block.MatchedBlockParser;
import org.commonmark.parser.block.ParserState;
import org.commonmark.parser.delimiter.DelimiterProcessor;

/* loaded from: classes5.dex */
public class DocumentParser implements ParserState {
    private static final Set<Class<? extends Block>> CORE_FACTORY_TYPES = null;
    private static final Map<Class<? extends Block>, BlockParserFactory> NODES_TO_CORE_FACTORIES = null;
    private List<BlockParser> activeBlockParsers;
    private Set<BlockParser> allBlockParsers;
    private boolean blank;
    private final List<BlockParserFactory> blockParserFactories;
    private int column;
    private boolean columnIsInTab;
    private final Map<String, LinkReferenceDefinition> definitions;
    private final List<DelimiterProcessor> delimiterProcessors;
    private final DocumentBlockParser documentBlockParser;
    private int indent;
    private int index;
    private final InlineParserFactory inlineParserFactory;
    private CharSequence line;
    private int nextNonSpace;
    private int nextNonSpaceColumn;

    public static class MatchedBlockParserImpl implements MatchedBlockParser {
        private final BlockParser matchedBlockParser;

        public MatchedBlockParserImpl(BlockParser r1) {
            this.matchedBlockParser = r1;
        }

        @Override // org.commonmark.parser.block.MatchedBlockParser
        public BlockParser getMatchedBlockParser() {
            return this.matchedBlockParser;
        }

        @Override // org.commonmark.parser.block.MatchedBlockParser
        public CharSequence getParagraphContent() {
            BlockParser r2 = this.matchedBlockParser;
            if ((r2 instanceof ParagraphParser) == false) goto L8;
            CharSequence r22 = ((ParagraphParser) r2).getContentString();
            if (r22.length() != 0) goto L7;
            return null;
        L7:
            return r22;
        L8:
            return null;
        }
    }

    static {
        CORE_FACTORY_TYPES = new LinkedHashSet(Arrays.asList(new Class[]{BlockQuote.class, Heading.class, FencedCodeBlock.class, HtmlBlock.class, ThematicBreak.class, ListBlock.class, IndentedCodeBlock.class}));
        HashMap r0 = new HashMap();
        r0.put(BlockQuote.class, new BlockQuoteParser.Factory());
        r0.put(Heading.class, new HeadingParser.Factory());
        r0.put(FencedCodeBlock.class, new FencedCodeBlockParser.Factory());
        r0.put(HtmlBlock.class, new HtmlBlockParser.Factory());
        r0.put(ThematicBreak.class, new ThematicBreakParser.Factory());
        r0.put(ListBlock.class, new ListBlockParser.Factory());
        r0.put(IndentedCodeBlock.class, new IndentedCodeBlockParser.Factory());
        NODES_TO_CORE_FACTORIES = Collections.unmodifiableMap(r0);
    }

    public DocumentParser(List<BlockParserFactory> r2, InlineParserFactory r3, List<DelimiterProcessor> r4) {
        this.index = 0;
        this.column = 0;
        this.nextNonSpace = 0;
        this.nextNonSpaceColumn = 0;
        this.indent = 0;
        this.definitions = new LinkedHashMap();
        this.activeBlockParsers = new ArrayList();
        this.allBlockParsers = new LinkedHashSet();
        this.blockParserFactories = r2;
        this.inlineParserFactory = r3;
        this.delimiterProcessors = r4;
        DocumentBlockParser r22 = new DocumentBlockParser();
        this.documentBlockParser = r22;
        activateBlockParser(r22);
    }

    private void activateBlockParser(BlockParser r2) {
        this.activeBlockParsers.add(r2);
        this.allBlockParsers.add(r2);
    }

    private <T extends BlockParser> T addChild(T r3) {
    L3:
        if (getActiveBlockParser().canContain(r3.getBlock()) == true) goto L5;
        finalize(getActiveBlockParser());
        goto L3
    L5:
        getActiveBlockParser().getBlock().appendChild(r3.getBlock());
        activateBlockParser(r3);
        return r3;
    }

    private void addDefinitionsFrom(ParagraphParser r5) {
        Iterator<LinkReferenceDefinition> r0 = r5.getDefinitions().iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        LinkReferenceDefinition r1 = r0.next();
        r5.getBlock().insertBefore(r1);
        String r2 = r1.getLabel();
        if (this.definitions.containsKey(r2) == true) goto L4;
        this.definitions.put(r2, r1);
        goto L4
    }

    private void addLine() {
        if (this.columnIsInTab == false) goto L8;
        int r0 = this.index + 1;
        CharSequence r1 = this.line;
        CharSequence r02 = r1.subSequence(r0, r1.length());
        int r12 = Parsing.columnsToNextTabStop(this.column);
        StringBuilder r2 = new StringBuilder(r02.length() + r12);
        int r3 = 0;
    L5:
        if (r3 >= r12) goto L7;
        r2.append(' ');
        r3 = r3 + 1;
        goto L5
    L7:
        r2.append(r02);
        CharSequence r03 = r2.toString();
    L9:
        getActiveBlockParser().addLine(r03);
        return;
    L8:
        CharSequence r04 = this.line;
        r03 = r04.subSequence(this.index, r04.length());
        goto L9
    }

    private void advance() {
        char r0 = this.line.charAt(this.index);
        int r1 = this.index;
        if (r0 != '\t') goto L6;
        this.index = r1 + 1;
        int r02 = this.column;
        this.column = r02 + Parsing.columnsToNextTabStop(r02);
        return;
    L6:
        this.index = r1 + 1;
        this.column++;
    }

    public static List<BlockParserFactory> calculateBlockParserFactories(List<BlockParserFactory> r2, Set<Class<? extends Block>> r3) {
        ArrayList r0 = new ArrayList();
        r0.addAll(r2);
        Iterator<Class<? extends Block>> r22 = r3.iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        Class<? extends Block> r32 = r22.next();
        r0.add(NODES_TO_CORE_FACTORIES.get(r32));
        goto L4
    L6:
        return r0;
    }

    private void deactivateBlockParser() {
        this.activeBlockParsers.remove(r1.size() - 1);
    }

    private void finalize(BlockParser r2) {
        if (getActiveBlockParser() != r2) goto L6;
        deactivateBlockParser();
    L6:
        if ((r2 instanceof ParagraphParser) == false) goto L8;
        addDefinitionsFrom((ParagraphParser) r2);
    L8:
        r2.closeBlock();
    }

    private Document finalizeAndProcess() {
        finalizeBlocks(this.activeBlockParsers);
        processInlines();
        return this.documentBlockParser.getBlock();
    }

    private void finalizeBlocks(List<BlockParser> r3) {
        int r0 = r3.size() - 1;
    L3:
        if (r0 < 0) goto L5;
        finalize(r3.get(r0));
        r0 = r0 - 1;
        goto L3
    }

    private BlockStartImpl findBlockStart(BlockParser r4) {
        MatchedBlockParserImpl r0 = new MatchedBlockParserImpl(r4);
        Iterator<BlockParserFactory> r42 = this.blockParserFactories.iterator();
    L4:
        if (r42.hasNext() == false) goto L9;
        BlockStart r1 = r42.next().tryStart(this, r0);
        if ((r1 instanceof BlockStartImpl) == false) goto L4;
        return (BlockStartImpl) r1;
    L9:
        return null;
    }

    private void findNextNonSpace() {
        int r0 = this.index;
        int r1 = this.column;
        this.blank = true;
        int r2 = this.line.length();
    L3:
        if (r0 >= r2) goto L11;
        char r3 = this.line.charAt(r0);
        if (r3 != '\t') goto L7;
        r0 = r0 + 1;
        r1 = r1 + (4 - (r1 % 4));
        goto L3
    L7:
        if (r3 != ' ') goto L8;
        r0 = r0 + 1;
        r1 = r1 + 1;
        goto L3
    L8:
        this.blank = false;
    L11:
        this.nextNonSpace = r0;
        this.nextNonSpaceColumn = r1;
        this.indent = r1 - this.column;
    }

    public static Set<Class<? extends Block>> getDefaultBlockParserTypes() {
        return CORE_FACTORY_TYPES;
    }

    private void incorporateLine(CharSequence r11) {
        this.line = Parsing.prepareLine(r11);
        this.index = 0;
        this.column = 0;
        this.columnIsInTab = false;
        List<BlockParser> r0 = this.activeBlockParsers;
        Iterator<BlockParser> r02 = r0.subList(1, r0.size()).iterator();
        int r1 = 1;
    L4:
        if (r02.hasNext() == false) goto L18;
        BlockParser r3 = r02.next();
        findNextNonSpace();
        BlockContinue r5 = r3.tryContinue(this);
        if ((r5 instanceof BlockContinueImpl) == false) goto L18;
        BlockContinueImpl r52 = (BlockContinueImpl) r5;
        if (r52.isFinalize() == true) goto L9;
        if (r52.getNewIndex() == (-1)) goto L15;
        setNewIndex(r52.getNewIndex());
    L17:
        r1 = r1 + 1;
        goto L4
    L15:
        if (r52.getNewColumn() == (-1)) goto L17;
        setNewColumn(r52.getNewColumn());
        goto L17
    L9:
        finalize(r3);
        return;
    L18:
        List<BlockParser> r32 = this.activeBlockParsers;
        ArrayList r03 = new ArrayList(r32.subList(r1, r32.size()));
        BlockParser r12 = this.activeBlockParsers.get(r1 - 1);
        boolean r33 = r03.isEmpty();
        if ((r12.getBlock() instanceof Paragraph) == false) goto L21;
    L24:
        boolean r53 = true;
    L25:
        if (r53 == false) goto L51;
        findNextNonSpace();
        if (isBlank() == true) goto L50;
        if (this.indent < Parsing.CODE_BLOCK_INDENT) goto L31;
    L33:
        BlockStartImpl r6 = findBlockStart(r12);
        if (r6 == null) goto L35;
        if (r33 == true) goto L39;
        finalizeBlocks(r03);
        r33 = true;
    L39:
        if (r6.getNewIndex() == (-1)) goto L42;
        setNewIndex(r6.getNewIndex());
    L45:
        if (r6.isReplaceActiveBlockParser() == false) goto L47;
        prepareActiveBlockParserForReplacement();
    L47:
        BlockParser[] r62 = r6.getBlockParsers();
        int r7 = r62.length;
        int r8 = 0;
    L48:
        if (r8 >= r7) goto L25;
        BlockParser r13 = r62[r8];
        BlockParser r54 = addChild(r13);
        r8 = r8 + 1;
        r53 = r13.isContainer();
        r12 = r54;
        goto L48
    L42:
        if (r6.getNewColumn() == (-1)) goto L45;
        setNewColumn(r6.getNewColumn());
        goto L45
    L35:
        setNewIndex(this.nextNonSpace);
        goto L51
    L31:
        if (Parsing.isLetter(this.line, this.nextNonSpace) == false) goto L33;
    L50:
        setNewIndex(this.nextNonSpace);
    L51:
        if (r33 == false) goto L53;
    L58:
        if (r33 == true) goto L61;
        finalizeBlocks(r03);
    L61:
        if (r12.isContainer() == true) goto L65;
        addLine();
        return;
    L65:
        if (isBlank() == true) goto L82;
        addChild(new ParagraphParser());
        addLine();
        return;
    L82:
        return;
    L53:
        if (isBlank() == true) goto L58;
        if (getActiveBlockParser().canHaveLazyContinuationLines() == false) goto L58;
        addLine();
        return;
    L21:
        if (r12.isContainer() == true) goto L24;
        r53 = false;
        goto L25
    }

    private void prepareActiveBlockParserForReplacement() {
        BlockParser r0 = getActiveBlockParser();
        deactivateBlockParser();
        this.allBlockParsers.remove(r0);
        if ((r0 instanceof ParagraphParser) == false) goto L5;
        addDefinitionsFrom((ParagraphParser) r0);
    L5:
        r0.getBlock().unlink();
    }

    private void processInlines() {
        InlineParserContextImpl r0 = new InlineParserContextImpl(this.delimiterProcessors, this.definitions);
        InlineParser r02 = this.inlineParserFactory.create(r0);
        Iterator<BlockParser> r3 = this.allBlockParsers.iterator();
    L4:
        if (r3.hasNext() == false) goto L6;
        r3.next().parseInlines(r02);
        goto L4
    }

    private void setNewColumn(int r4) {
        int r0 = this.nextNonSpaceColumn;
        if (r4 < r0) goto L5;
        this.index = this.nextNonSpace;
        this.column = r0;
    L5:
        int r02 = this.line.length();
    L6:
        int r1 = this.column;
        if (r1 >= r4) goto L11;
        if (this.index == r02) goto L11;
        advance();
    L11:
        if (r1 <= r4) goto L14;
        this.index--;
        this.column = r4;
        this.columnIsInTab = true;
        return;
    L14:
        this.columnIsInTab = false;
    }

    private void setNewIndex(int r3) {
        int r0 = this.nextNonSpace;
        if (r3 < r0) goto L5;
        this.index = r0;
        this.column = this.nextNonSpaceColumn;
    L5:
        int r02 = this.line.length();
    L6:
        int r1 = this.index;
        if (r1 >= r3) goto L10;
        if (r1 == r02) goto L10;
        advance();
    L10:
        this.columnIsInTab = false;
    }

    @Override // org.commonmark.parser.block.ParserState
    public BlockParser getActiveBlockParser() {
        return this.activeBlockParsers.get(r1.size() - 1);
    }

    @Override // org.commonmark.parser.block.ParserState
    public int getColumn() {
        return this.column;
    }

    @Override // org.commonmark.parser.block.ParserState
    public int getIndent() {
        return this.indent;
    }

    @Override // org.commonmark.parser.block.ParserState
    public int getIndex() {
        return this.index;
    }

    @Override // org.commonmark.parser.block.ParserState
    public CharSequence getLine() {
        return this.line;
    }

    @Override // org.commonmark.parser.block.ParserState
    public int getNextNonSpaceIndex() {
        return this.nextNonSpace;
    }

    @Override // org.commonmark.parser.block.ParserState
    public boolean isBlank() {
        return this.blank;
    }

    public Document parse(String r5) {
        int r0 = 0;
    L3:
        int r1 = Parsing.findLineBreak(r5, r0);
        if (r1 == (-1)) goto L13;
        incorporateLine(r5.substring(r0, r1));
        r0 = r1 + 1;
        if (r0 >= r5.length()) goto L3;
        if (r5.charAt(r1) != '\r') goto L3;
        if (r5.charAt(r0) != '\n') goto L3;
        r0 = r1 + 2;
        goto L3
    L13:
        if (r5.length() <= 0) goto L19;
        if (r0 != 0) goto L16;
    L17:
        incorporateLine(r5.substring(r0));
        goto L19
    L16:
        if (r0 < r5.length()) goto L17;
    L19:
        return finalizeAndProcess();
    }
}
