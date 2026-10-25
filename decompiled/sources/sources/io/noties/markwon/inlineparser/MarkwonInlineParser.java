package io.noties.markwon.inlineparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.commonmark.internal.Bracket;
import org.commonmark.internal.Delimiter;
import org.commonmark.internal.inline.AsteriskDelimiterProcessor;
import org.commonmark.internal.inline.EmphasisDelimiterProcessor;
import org.commonmark.internal.inline.UnderscoreDelimiterProcessor;
import org.commonmark.internal.util.Escaping;
import org.commonmark.internal.util.LinkScanner;
import org.commonmark.node.LinkReferenceDefinition;
import org.commonmark.node.Node;
import org.commonmark.node.Text;
import org.commonmark.parser.InlineParser;
import org.commonmark.parser.InlineParserContext;
import org.commonmark.parser.InlineParserFactory;
import org.commonmark.parser.delimiter.DelimiterProcessor;

/* loaded from: classes5.dex */
public class MarkwonInlineParser implements InlineParser, MarkwonInlineParserContext {
    static final Pattern ESCAPABLE = null;
    private static final Pattern PUNCTUATION = null;
    private static final Pattern SPNL = null;
    private static final Pattern UNICODE_WHITESPACE_CHAR = null;
    static final Pattern WHITESPACE = null;
    private Node block;
    private final Map<Character, DelimiterProcessor> delimiterProcessors;
    private int index;
    private final InlineParserContext inlineParserContext;
    private final Map<Character, List<InlineProcessor>> inlineProcessors;
    private String input;
    private Bracket lastBracket;
    private Delimiter lastDelimiter;
    private final boolean referencesEnabled;
    private final BitSet specialCharacters;

    public static class DelimiterData {
        final boolean canClose;
        final boolean canOpen;
        final int count;

        public DelimiterData(int r1, boolean r2, boolean r3) {
            this.count = r1;
            this.canOpen = r2;
            this.canClose = r3;
        }
    }

    public interface FactoryBuilder {
        FactoryBuilder addInlineProcessor(InlineProcessor r1);

        InlineParserFactory build();
    }

    public static class FactoryBuilderImpl implements FactoryBuilder {
        private final List<DelimiterProcessor> delimiterProcessors;
        private final List<InlineProcessor> inlineProcessors;
        private boolean referencesEnabled;

        public FactoryBuilderImpl() {
            this.inlineProcessors = new ArrayList(3);
            this.delimiterProcessors = new ArrayList(3);
        }

        @Override // io.noties.markwon.inlineparser.MarkwonInlineParser.FactoryBuilder
        public FactoryBuilder addInlineProcessor(InlineProcessor r2) {
            this.inlineProcessors.add(r2);
            return this;
        }

        @Override // io.noties.markwon.inlineparser.MarkwonInlineParser.FactoryBuilder
        public InlineParserFactory build() {
            return new InlineParserFactoryImpl(this.referencesEnabled, this.inlineProcessors, this.delimiterProcessors);
        }

        public FactoryBuilder includeDefaults() {
            this.referencesEnabled = true;
            this.inlineProcessors.addAll(Arrays.asList(new InlineProcessor[]{new AutolinkInlineProcessor(), new BackslashInlineProcessor(), new BackticksInlineProcessor(), new BangInlineProcessor(), new CloseBracketInlineProcessor(), new EntityInlineProcessor(), new HtmlInlineProcessor(), new NewLineInlineProcessor(), new OpenBracketInlineProcessor()}));
            this.delimiterProcessors.addAll(Arrays.asList(new EmphasisDelimiterProcessor[]{new AsteriskDelimiterProcessor(), new UnderscoreDelimiterProcessor()}));
            return this;
        }
    }

    public static class InlineParserFactoryImpl implements InlineParserFactory {
        private final List<DelimiterProcessor> delimiterProcessors;
        private final List<InlineProcessor> inlineProcessors;
        private final boolean referencesEnabled;

        public InlineParserFactoryImpl(boolean r1, List<InlineProcessor> r2, List<DelimiterProcessor> r3) {
            this.referencesEnabled = r1;
            this.inlineProcessors = r2;
            this.delimiterProcessors = r3;
        }

        @Override // org.commonmark.parser.InlineParserFactory
        public InlineParser create(InlineParserContext r5) {
            List<DelimiterProcessor> r0 = r5.getCustomDelimiterProcessors();
            if (r0 == null) goto L5;
            int r1 = r0.size();
        L6:
            if (r1 <= 0) goto L8;
            List r2 = new ArrayList(r1 + this.delimiterProcessors.size());
            r2.addAll(this.delimiterProcessors);
            r2.addAll(r0);
        L10:
            return new MarkwonInlineParser(r5, this.referencesEnabled, this.inlineProcessors, r2);
        L8:
            r2 = this.delimiterProcessors;
            goto L10
        L5:
            r1 = 0;
            goto L6
        }
    }

    static {
        PUNCTUATION = Pattern.compile("^[!\"#\\$%&'\\(\\)\\*\\+,\\-\\./:;<=>\\?@\\[\\\\\\]\\^_`\\{\\|\\}~\\p{Pc}\\p{Pd}\\p{Pe}\\p{Pf}\\p{Pi}\\p{Po}\\p{Ps}]");
        SPNL = Pattern.compile("^ *(?:\n *)?");
        UNICODE_WHITESPACE_CHAR = Pattern.compile("^[\\p{Zs}\t\r\n\f]");
        ESCAPABLE = Pattern.compile("^[!\"#$%&'()*+,./:;<=>?@\\[\\\\\\]^_`{|}~-]");
        WHITESPACE = Pattern.compile("\\s+");
    }

    public MarkwonInlineParser(InlineParserContext r1, boolean r2, List<InlineProcessor> r3, List<DelimiterProcessor> r4) {
        this.inlineParserContext = r1;
        this.referencesEnabled = r2;
        Map<Character, List<InlineProcessor>> r12 = calculateInlines(r3);
        this.inlineProcessors = r12;
        Map<Character, DelimiterProcessor> r22 = calculateDelimiterProcessors(r4);
        this.delimiterProcessors = r22;
        this.specialCharacters = calculateSpecialCharacters(r12.keySet(), r22.keySet());
    }

    private static void addDelimiterProcessorForChar(char r1, DelimiterProcessor r2, Map<Character, DelimiterProcessor> r3) {
        if (r3.put(Character.valueOf(r1), r2) != null) goto L6;
        return;
    L6:
        throw new IllegalArgumentException("Delimiter processor conflict with delimiter char '" + r1 + "'");
    }

    private static void addDelimiterProcessors(Iterable<DelimiterProcessor> r5, Map<Character, DelimiterProcessor> r6) {
        Iterator<DelimiterProcessor> r52 = r5.iterator();
    L4:
        if (r52.hasNext() == false) goto L18;
        DelimiterProcessor r0 = r52.next();
        char r1 = r0.getOpeningCharacter();
        char r2 = r0.getClosingCharacter();
        if (r1 == r2) goto L7;
        addDelimiterProcessorForChar(r1, r0, r6);
        addDelimiterProcessorForChar(r2, r0, r6);
        goto L4
    L7:
        DelimiterProcessor r22 = r6.get(Character.valueOf(r1));
        if (r22 == null) goto L16;
        if (r22.getOpeningCharacter() != r22.getClosingCharacter()) goto L16;
        if ((r22 instanceof StaggeredDelimiterProcessor) == false) goto L14;
        StaggeredDelimiterProcessor r23 = (StaggeredDelimiterProcessor) r22;
    L15:
        r23.add(r0);
        r6.put(Character.valueOf(r1), r23);
        goto L4
    L14:
        StaggeredDelimiterProcessor r3 = new StaggeredDelimiterProcessor(r1);
        r3.add(r22);
        r23 = r3;
    L16:
        addDelimiterProcessorForChar(r1, r0, r6);
        goto L4
    }

    private static Map<Character, DelimiterProcessor> calculateDelimiterProcessors(List<DelimiterProcessor> r1) {
        HashMap r0 = new HashMap();
        addDelimiterProcessors(r1, r0);
        return r0;
    }

    private static Map<Character, List<InlineProcessor>> calculateInlines(List<InlineProcessor> r5) {
        HashMap r0 = new HashMap(r5.size());
        Iterator<InlineProcessor> r52 = r5.iterator();
    L4:
        if (r52.hasNext() == false) goto L9;
        InlineProcessor r1 = r52.next();
        char r2 = r1.specialCharacter();
        List r3 = (List) r0.get(Character.valueOf(r2));
        if (r3 != null) goto L8;
        r3 = new ArrayList(1);
        r0.put(Character.valueOf(r2), r3);
    L8:
        r3.add(r1);
        goto L4
    L9:
        return r0;
    }

    private static BitSet calculateSpecialCharacters(Set<Character> r2, Set<Character> r3) {
        BitSet r0 = new BitSet();
        Iterator<Character> r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        r0.set(r22.next().charValue());
        goto L4
    L6:
        Iterator<Character> r23 = r3.iterator();
    L8:
        if (r23.hasNext() == false) goto L10;
        r0.set(r23.next().charValue());
        goto L8
    L10:
        return r0;
    }

    public static FactoryBuilder factoryBuilder() {
        return new FactoryBuilderImpl().includeDefaults();
    }

    private Node parseDelimiters(DelimiterProcessor r11, char r12) {
        DelimiterData r112 = scanDelimiters(r11, r12);
        if (r112 != null) goto L6;
        return null;
    L6:
        int r0 = r112.count;
        int r1 = this.index;
        int r2 = r1 + r0;
        this.index = r2;
        Text r5 = text(this.input, r1, r2);
        Delimiter r4 = new Delimiter(r5, r12, r112.canOpen, r112.canClose, this.lastDelimiter);
        this.lastDelimiter = r4;
        r4.length = r0;
        r4.originalLength = r0;
        Delimiter r10 = r4.previous;
        if (r10 == null) goto L9;
        r10.next = r4;
    L9:
        return r5;
    }

    private Node parseInline() {
        char r0 = peek();
        Node r1 = null;
        if (r0 != 0) goto L5;
        return null;
    L5:
        List<InlineProcessor> r2 = this.inlineProcessors.get(Character.valueOf(r0));
        if (r2 == null) goto L14;
        int r3 = this.index;
        Iterator<InlineProcessor> r22 = r2.iterator();
    L9:
        if (r22.hasNext() == false) goto L18;
        r1 = r22.next().parse(this);
        if (r1 != null) goto L18;
        this.index = r3;
    L18:
        if (r1 == null) goto L20;
        return r1;
    L20:
        this.index++;
        return text(String.valueOf(r0));
    L14:
        DelimiterProcessor r12 = this.delimiterProcessors.get(Character.valueOf(r0));
        if (r12 == null) goto L17;
        r1 = parseDelimiters(r12, r0);
        goto L18
    L17:
        r1 = parseString();
        goto L18
    }

    private Node parseString() {
        int r0 = this.index;
        int r1 = this.input.length();
    L3:
        int r2 = this.index;
        if (r2 == r1) goto L9;
        if (this.specialCharacters.get(this.input.charAt(r2)) == true) goto L9;
        this.index++;
    L9:
        int r12 = this.index;
        if (r0 != r12) goto L12;
        return null;
    L12:
        return text(this.input, r0, r12);
    }

    private void removeDelimiter(Delimiter r3) {
        Delimiter r0 = r3.previous;
        if (r0 == null) goto L5;
        r0.next = r3.next;
    L5:
        Delimiter r32 = r3.next;
        if (r32 != null) goto L9;
        this.lastDelimiter = r0;
        return;
    L9:
        r32.previous = r0;
    }

    private void removeDelimiterAndNode(Delimiter r2) {
        r2.node.unlink();
        removeDelimiter(r2);
    }

    private void removeDelimiterKeepNode(Delimiter r1) {
        removeDelimiter(r1);
    }

    private void removeDelimitersBetween(Delimiter r2, Delimiter r3) {
        Delimiter r32 = r3.previous;
    L3:
        if (r32 == null) goto L6;
        if (r32 == r2) goto L9;
        Delimiter r0 = r32.previous;
        removeDelimiterKeepNode(r32);
        r32 = r0;
        goto L3
    L9:
        return;
    }

    private void reset(String r1) {
        this.input = r1;
        this.index = 0;
        this.lastDelimiter = null;
        this.lastBracket = null;
    }

    private DelimiterData scanDelimiters(DelimiterProcessor r10, char r11) {
        int r0 = this.index;
        boolean r1 = false;
        int r2 = 0;
    L4:
        if (peek() != r11) goto L7;
        r2 = r2 + 1;
        this.index++;
        goto L4
    L7:
        if (r2 >= r10.getMinLength()) goto L10;
        this.index = r0;
        return null;
    L10:
        String r3 = "\n";
        if (r0 != 0) goto L13;
        String r5 = "\n";
    L14:
        char r6 = peek();
        if (r6 == 0) goto L18;
        r3 = String.valueOf(r6);
    L18:
        Pattern r62 = PUNCTUATION;
        boolean r7 = r62.matcher(r5).matches();
        Pattern r8 = UNICODE_WHITESPACE_CHAR;
        boolean r52 = r8.matcher(r5).matches();
        boolean r63 = r62.matcher(r3).matches();
        boolean r32 = r8.matcher(r3).matches();
        if (r32 == true) goto L24;
        if (r63 == false) goto L23;
        if (r52 == true) goto L23;
        if (r7 == false) goto L24;
    L23:
        boolean r82 = true;
    L25:
        if (r52 == true) goto L30;
        if (r7 == false) goto L29;
        if (r32 == true) goto L29;
        if (r63 == false) goto L30;
    L29:
        boolean r33 = true;
    L32:
        if (r11 != '_') goto L42;
        if (r82 == false) goto L37;
        if (r33 == false) goto L36;
        if (r7 == false) goto L37;
    L36:
        boolean r102 = true;
    L38:
        if (r33 == false) goto L52;
        if (r82 == false) goto L41;
        if (r63 == false) goto L52;
    L41:
        r1 = true;
    L52:
        this.index = r0;
        return new DelimiterData(r2, r102, r1);
    L37:
        r102 = false;
        goto L38
    L42:
        if (r82 == true) goto L44;
    L46:
        boolean r53 = false;
    L47:
        if (r33 == true) goto L49;
    L51:
        r102 = r53;
        goto L52
    L49:
        if (r11 != r10.getClosingCharacter()) goto L51;
        r1 = true;
        goto L51
    L44:
        if (r11 != r10.getOpeningCharacter()) goto L46;
        r53 = true;
    L30:
        r33 = false;
    L24:
        r82 = false;
        goto L25
    L13:
        r5 = this.input.substring(r0 - 1, r0);
        goto L14
    }

    @Override // io.noties.markwon.inlineparser.MarkwonInlineParserContext
    public void addBracket(Bracket r3) {
        Bracket r0 = this.lastBracket;
        if (r0 == null) goto L5;
        r0.bracketAfter = true;
    L5:
        this.lastBracket = r3;
    }

    @Override // io.noties.markwon.inlineparser.MarkwonInlineParserContext
    public Node block() {
        return this.block;
    }

    @Override // io.noties.markwon.inlineparser.MarkwonInlineParserContext
    public LinkReferenceDefinition getLinkReferenceDefinition(String r2) {
        if (this.referencesEnabled == true) goto L5;
        return null;
    L5:
        return this.inlineParserContext.getLinkReferenceDefinition(r2);
    }

    @Override // io.noties.markwon.inlineparser.MarkwonInlineParserContext
    public int index() {
        return this.index;
    }

    @Override // io.noties.markwon.inlineparser.MarkwonInlineParserContext
    public String input() {
        return this.input;
    }

    @Override // io.noties.markwon.inlineparser.MarkwonInlineParserContext
    public Bracket lastBracket() {
        return this.lastBracket;
    }

    @Override // io.noties.markwon.inlineparser.MarkwonInlineParserContext
    public Delimiter lastDelimiter() {
        return this.lastDelimiter;
    }

    @Override // io.noties.markwon.inlineparser.MarkwonInlineParserContext
    public String match(Pattern r4) {
        if (this.index < this.input.length()) goto L5;
        return null;
    L5:
        Matcher r42 = r4.matcher(this.input);
        r42.region(this.index, this.input.length());
        if (r42.find() == false) goto L9;
        this.index = r42.end();
        return r42.group();
    L9:
        return null;
    }

    @Override // org.commonmark.parser.InlineParser
    public void parse(String r1, Node r2) {
        reset(r1.trim());
        this.block = r2;
    L3:
        Node r12 = parseInline();
        if (r12 == null) goto L6;
        r2.appendChild(r12);
        goto L3
    L6:
        processDelimiters(null);
        InlineParserUtils.mergeChildTextNodes(r2);
    }

    @Override // io.noties.markwon.inlineparser.MarkwonInlineParserContext
    public String parseLinkDestination() {
        int r0 = LinkScanner.scanLinkDestination(this.input, this.index);
        if (r0 != (-1)) goto L6;
        return null;
    L6:
        char r1 = peek();
        String r2 = this.input;
        if (r1 != '<') goto L9;
        String r12 = r2.substring(this.index + 1, r0 - 1);
    L10:
        this.index = r0;
        return Escaping.unescapeString(r12);
    L9:
        r12 = r2.substring(this.index, r0);
        goto L10
    }

    @Override // io.noties.markwon.inlineparser.MarkwonInlineParserContext
    public int parseLinkLabel() {
        if (this.index < this.input.length()) goto L5;
    L19:
        return 0;
    L5:
        if (this.input.charAt(this.index) != '[') goto L19;
        int r0 = this.index + 1;
        int r1 = LinkScanner.scanLinkLabelContent(this.input, r0);
        int r02 = r1 - r0;
        if (r1 == (-1)) goto L19;
        if (r02 > 999) goto L19;
        if (r1 >= this.input.length()) goto L19;
        if (this.input.charAt(r1) != ']') goto L19;
        this.index = r1 + 1;
        return r02 + 2;
    }

    @Override // io.noties.markwon.inlineparser.MarkwonInlineParserContext
    public String parseLinkTitle() {
        int r0 = LinkScanner.scanLinkTitle(this.input, this.index);
        if (r0 != (-1)) goto L6;
        return null;
    L6:
        String r1 = this.input.substring(this.index + 1, r0 - 1);
        this.index = r0;
        return Escaping.unescapeString(r1);
    }

    @Override // io.noties.markwon.inlineparser.MarkwonInlineParserContext
    public char peek() {
        if (this.index < this.input.length()) goto L5;
        return 0;
    L5:
        return this.input.charAt(this.index);
    }

    @Override // io.noties.markwon.inlineparser.MarkwonInlineParserContext
    public void processDelimiters(Delimiter r11) {
        HashMap r0 = new HashMap();
        Delimiter r1 = this.lastDelimiter;
    L3:
        if (r1 == null) goto L7;
        Delimiter r2 = r1.previous;
        if (r2 == r11) goto L7;
        r1 = r2;
    L7:
        if (r1 == null) goto L39;
        char r22 = r1.delimiterChar;
        DelimiterProcessor r3 = this.delimiterProcessors.get(Character.valueOf(r22));
        if (r1.canClose == false) goto L38;
        if (r3 == null) goto L38;
        char r4 = r3.getOpeningCharacter();
        Delimiter r5 = r1.previous;
        int r7 = 0;
        boolean r8 = false;
    L13:
        if (r5 == null) goto L25;
        if (r5 == r11) goto L25;
        if (r5 == r0.get(Character.valueOf(r22))) goto L25;
        if (r5.canOpen == false) goto L24;
        if (r5.delimiterChar != r4) goto L24;
        r7 = r3.getDelimiterUse(r5, r1);
        r8 = true;
        if (r7 <= 0) goto L24;
        boolean r42 = true;
    L26:
        if (r8 == false) goto L27;
        Text r23 = r5.node;
        Text r43 = r1.node;
        r5.length -= r7;
        r1.length -= r7;
        r23.setLiteral(r23.getLiteral().substring(0, r23.getLiteral().length() - r7));
        r43.setLiteral(r43.getLiteral().substring(0, r43.getLiteral().length() - r7));
        removeDelimitersBetween(r5, r1);
        InlineParserUtils.mergeTextNodesBetweenExclusive(r23, r43);
        r3.process(r23, r43, r7);
        if (r5.length != 0) goto L36;
        removeDelimiterAndNode(r5);
    L36:
        if (r1.length != 0) goto L7;
        Delimiter r24 = r1.next;
        removeDelimiterAndNode(r1);
        r1 = r24;
        goto L7
    L27:
        if (r42 == true) goto L31;
        r0.put(Character.valueOf(r22), r1.previous);
        if (r1.canOpen == true) goto L31;
        removeDelimiterKeepNode(r1);
    L31:
        r1 = r1.next;
    L24:
        r5 = r5.previous;
    L25:
        r42 = r8;
        r8 = false;
    L38:
        r1 = r1.next;
    L39:
        Delimiter r02 = this.lastDelimiter;
        if (r02 == null) goto L43;
        if (r02 == r11) goto L68;
        removeDelimiterKeepNode(r02);
        goto L39
    L68:
        return;
    }

    @Override // io.noties.markwon.inlineparser.MarkwonInlineParserContext
    public void removeLastBracket() {
        this.lastBracket = this.lastBracket.previous;
    }

    @Override // io.noties.markwon.inlineparser.MarkwonInlineParserContext
    public void setIndex(int r1) {
        this.index = r1;
    }

    @Override // io.noties.markwon.inlineparser.MarkwonInlineParserContext
    public void spnl() {
        match(SPNL);
    }

    @Override // io.noties.markwon.inlineparser.MarkwonInlineParserContext
    public Text text(String r1) {
        return new Text(r1);
    }

    @Override // io.noties.markwon.inlineparser.MarkwonInlineParserContext
    public Text text(String r1, int r2, int r3) {
        return new Text(r1.substring(r2, r3));
    }
}
