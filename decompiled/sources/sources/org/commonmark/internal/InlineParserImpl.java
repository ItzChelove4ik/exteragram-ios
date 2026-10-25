package org.commonmark.internal;

import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.commonmark.internal.inline.AsteriskDelimiterProcessor;
import org.commonmark.internal.inline.UnderscoreDelimiterProcessor;
import org.commonmark.internal.util.Escaping;
import org.commonmark.internal.util.Html5Entities;
import org.commonmark.internal.util.LinkScanner;
import org.commonmark.internal.util.Parsing;
import org.commonmark.node.Code;
import org.commonmark.node.HardLineBreak;
import org.commonmark.node.HtmlInline;
import org.commonmark.node.Image;
import org.commonmark.node.Link;
import org.commonmark.node.LinkReferenceDefinition;
import org.commonmark.node.Node;
import org.commonmark.node.SoftLineBreak;
import org.commonmark.node.Text;
import org.commonmark.parser.InlineParser;
import org.commonmark.parser.InlineParserContext;
import org.commonmark.parser.delimiter.DelimiterProcessor;

/* loaded from: classes5.dex */
public class InlineParserImpl implements InlineParser {
    private static final Pattern AUTOLINK = null;
    private static final Pattern EMAIL_AUTOLINK = null;
    private static final Pattern ENTITY_HERE = null;
    private static final Pattern ESCAPABLE = null;
    private static final Pattern FINAL_SPACE = null;
    private static final Pattern HTML_TAG = null;
    private static final Pattern PUNCTUATION = null;
    private static final Pattern SPNL = null;
    private static final Pattern TICKS = null;
    private static final Pattern TICKS_HERE = null;
    private static final Pattern UNICODE_WHITESPACE_CHAR = null;
    private static final Pattern WHITESPACE = null;
    private final InlineParserContext context;
    private final BitSet delimiterCharacters;
    private final Map<Character, DelimiterProcessor> delimiterProcessors;
    private int index;
    private String input;
    private Bracket lastBracket;
    private Delimiter lastDelimiter;
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

    static {
        PUNCTUATION = Pattern.compile("^[!\"#\\$%&'\\(\\)\\*\\+,\\-\\./:;<=>\\?@\\[\\\\\\]\\^_`\\{\\|\\}~\\p{Pc}\\p{Pd}\\p{Pe}\\p{Pf}\\p{Pi}\\p{Po}\\p{Ps}]");
        HTML_TAG = Pattern.compile("^(?:<[A-Za-z][A-Za-z0-9-]*(?:\\s+[a-zA-Z_:][a-zA-Z0-9:._-]*(?:\\s*=\\s*(?:[^\"'=<>`\\x00-\\x20]+|'[^']*'|\"[^\"]*\"))?)*\\s*/?>|</[A-Za-z][A-Za-z0-9-]*\\s*[>]|<!---->|<!--(?:-?[^>-])(?:-?[^-])*-->|[<][?].*?[?][>]|<![A-Z]+\\s+[^>]*>|<!\\[CDATA\\[[\\s\\S]*?\\]\\]>)", 2);
        ESCAPABLE = Pattern.compile("^[!\"#$%&'()*+,./:;<=>?@\\[\\\\\\]^_`{|}~-]");
        ENTITY_HERE = Pattern.compile("^&(?:#x[a-f0-9]{1,6}|#[0-9]{1,7}|[a-z][a-z0-9]{1,31});", 2);
        TICKS = Pattern.compile("`+");
        TICKS_HERE = Pattern.compile("^`+");
        EMAIL_AUTOLINK = Pattern.compile("^<([a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*)>");
        AUTOLINK = Pattern.compile("^<[a-zA-Z][a-zA-Z0-9.+-]{1,31}:[^<>\u0000- ]*>");
        SPNL = Pattern.compile("^ *(?:\n *)?");
        UNICODE_WHITESPACE_CHAR = Pattern.compile("^[\\p{Zs}\t\r\n\f]");
        WHITESPACE = Pattern.compile("\\s+");
        FINAL_SPACE = Pattern.compile(" *$");
    }

    public InlineParserImpl(InlineParserContext r2) {
        Map<Character, DelimiterProcessor> r0 = calculateDelimiterProcessors(r2.getCustomDelimiterProcessors());
        this.delimiterProcessors = r0;
        BitSet r02 = calculateDelimiterCharacters(r0.keySet());
        this.delimiterCharacters = r02;
        this.specialCharacters = calculateSpecialCharacters(r02);
        this.context = r2;
    }

    private void addBracket(Bracket r3) {
        Bracket r0 = this.lastBracket;
        if (r0 == null) goto L5;
        r0.bracketAfter = true;
    L5:
        this.lastBracket = r3;
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

    public static BitSet calculateDelimiterCharacters(Set<Character> r2) {
        BitSet r0 = new BitSet();
        Iterator<Character> r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        r0.set(r22.next().charValue());
        goto L4
    L6:
        return r0;
    }

    public static Map<Character, DelimiterProcessor> calculateDelimiterProcessors(List<DelimiterProcessor> r5) {
        HashMap r0 = new HashMap();
        addDelimiterProcessors(Arrays.asList(new DelimiterProcessor[]{new AsteriskDelimiterProcessor(), new UnderscoreDelimiterProcessor()}), r0);
        addDelimiterProcessors(r5, r0);
        return r0;
    }

    public static BitSet calculateSpecialCharacters(BitSet r1) {
        BitSet r0 = new BitSet();
        r0.or(r1);
        r0.set(10);
        r0.set(96);
        r0.set(91);
        r0.set(93);
        r0.set(92);
        r0.set(33);
        r0.set(60);
        r0.set(38);
        return r0;
    }

    private String match(Pattern r4) {
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

    private void mergeChildTextNodes(Node r3) {
        if (r3.getFirstChild() != r3.getLastChild()) goto L5;
        return;
    L5:
        mergeTextNodesInclusive(r3.getFirstChild(), r3.getLastChild());
    }

    private void mergeIfNeeded(Text r2, Text r3, int r4) {
        if (r2 == null) goto L11;
        if (r3 == null) goto L12;
        if (r2 == r3) goto L13;
        StringBuilder r1 = new StringBuilder(r4);
        r1.append(r2.getLiteral());
        Node r42 = r2.getNext();
        Node r32 = r3.getNext();
    L6:
        if (r42 == r32) goto L8;
        r1.append(((Text) r42).getLiteral());
        Node r0 = r42.getNext();
        r42.unlink();
        r42 = r0;
        goto L6
    L8:
        r2.setLiteral(r1.toString());
        return;
    L13:
        return;
    L12:
        return;
    }

    private void mergeTextNodesBetweenExclusive(Node r2, Node r3) {
        if (r2 != r3) goto L4;
        return;
    L4:
        if (r2.getNext() == r3) goto L9;
        mergeTextNodesInclusive(r2.getNext(), r3.getPrevious());
        return;
    }

    private void mergeTextNodesInclusive(Node r7, Node r8) {
        Text r2 = null;
        Text r3 = null;
        int r4 = 0;
    L3:
        if (r7 == null) goto L14;
        if ((r7 instanceof Text) == false) goto L10;
        r3 = (Text) r7;
        if (r2 != null) goto L9;
        r2 = r3;
    L9:
        r4 = r4 + r3.getLiteral().length();
    L11:
        if (r7 == r8) goto L14;
        r7 = r7.getNext();
        goto L3
    L10:
        mergeIfNeeded(r2, r3, r4);
        r2 = null;
        r3 = null;
        r4 = 0;
    L14:
        mergeIfNeeded(r2, r3, r4);
    }

    private Node parseAutolink() {
        String r0 = match(EMAIL_AUTOLINK);
        if (r0 == null) goto L6;
        String r3 = r0.substring(1, r0.length() - 1);
        Link r02 = new Link("mailto:".concat(r3), null);
        r02.appendChild(new Text(r3));
        return r02;
    L6:
        String r32 = match(AUTOLINK);
        if (r32 == null) goto L10;
        String r33 = r32.substring(1, r32.length() - 1);
        Link r03 = new Link(r33, null);
        r03.appendChild(new Text(r33));
        return r03;
    L10:
        return null;
    }

    private Node parseBackslash() {
        this.index++;
        if (peek() != '\n') goto L7;
        HardLineBreak r0 = new HardLineBreak();
        this.index++;
        return r0;
    L7:
        if (this.index >= this.input.length()) goto L13;
        Pattern r02 = ESCAPABLE;
        String r1 = this.input;
        int r2 = this.index;
        if (r02.matcher(r1.substring(r2, r2 + 1)).matches() == false) goto L13;
        String r03 = this.input;
        int r12 = this.index;
        Text r04 = text(r03, r12, r12 + 1);
        this.index++;
        return r04;
    L13:
        return text("\\");
    }

    private Node parseBackticks() {
        String r0 = match(TICKS_HERE);
        if (r0 != null) goto L6;
        return null;
    L6:
        int r1 = this.index;
    L7:
        String r2 = match(TICKS);
        if (r2 == null) goto L22;
        if (r2.equals(r0) == false) goto L7;
        Code r22 = new Code();
        String r4 = this.input.substring(r1, this.index - r0.length()).replace('\n', ' ');
        if (r4.length() >= 3) goto L14;
    L20:
        r22.setLiteral(r4);
        return r22;
    L14:
        if (r4.charAt(0) != ' ') goto L20;
        if (r4.charAt(r4.length() - 1) != ' ') goto L20;
        if (Parsing.hasNonSpace(r4) == false) goto L20;
        r4 = r4.substring(1, r4.length() - 1);
        goto L20
    L22:
        this.index = r1;
        return text(r0);
    }

    private Node parseBang() {
        int r0 = this.index;
        this.index = r0 + 1;
        if (peek() != '[') goto L7;
        this.index++;
        Text r1 = text("![");
        addBracket(Bracket.image(r1, r0 + 1, this.lastBracket, this.lastDelimiter));
        return r1;
    L7:
        return text("!");
    }

    private Node parseCloseBracket() {
        boolean r1 = true;
        int r0 = this.index + 1;
        this.index = r0;
        Bracket r2 = this.lastBracket;
        if (r2 != null) goto L7;
        return text("]");
    L7:
        if (r2.allowed == true) goto L10;
        removeLastBracket();
        return text("]");
    L10:
        String r7 = null;
        if (peek() != '(') goto L23;
        this.index++;
        spnl();
        String r4 = parseLinkDestination();
        if (r4 == null) goto L22;
        spnl();
        Pattern r5 = WHITESPACE;
        String r8 = this.input;
        int r9 = this.index;
        if (r5.matcher(r8.substring(r9 - 1, r9)).matches() == false) goto L17;
        String r52 = parseLinkTitle();
        spnl();
    L19:
        if (peek() != ')') goto L21;
        this.index++;
        boolean r82 = true;
    L24:
        if (r82 == true) goto L35;
        int r92 = this.index;
        parseLinkLabel();
        int r10 = this.index - r92;
        if (r10 <= 2) goto L29;
        r7 = this.input.substring(r92, r10 + r92);
    L31:
        if (r7 == null) goto L35;
        LinkReferenceDefinition r72 = this.context.getLinkReferenceDefinition(Escaping.normalizeReference(r7));
        if (r72 == null) goto L35;
        r4 = r72.getDestination();
        r52 = r72.getTitle();
    L36:
        if (r1 == true) goto L38;
        this.index = r0;
        removeLastBracket();
        return text("]");
    L38:
        if (r2.image == false) goto L40;
        Node r02 = new Image(r4, r52);
    L41:
        Node r12 = r2.node.getNext();
    L42:
        if (r12 == null) goto L44;
        Node r3 = r12.getNext();
        r02.appendChild(r12);
        r12 = r3;
        goto L42
    L44:
        processDelimiters(r2.previousDelimiter);
        mergeChildTextNodes(r02);
        r2.node.unlink();
        removeLastBracket();
        if (r2.image == true) goto L52;
        Bracket r122 = this.lastBracket;
    L47:
        if (r122 == null) goto L52;
        if (r122.image == true) goto L51;
        r122.allowed = false;
    L51:
        r122 = r122.previous;
    L52:
        return r02;
    L40:
        r02 = new Link(r4, r52);
        goto L41
    L29:
        if (r2.bracketAfter == true) goto L31;
        r7 = this.input.substring(r2.index, r0);
    L35:
        r1 = r82;
        goto L36
    L21:
        this.index = r0;
        r82 = false;
        goto L24
    L17:
        r52 = null;
        goto L19
    L22:
        r82 = false;
        r52 = null;
        goto L24
    L23:
        r82 = false;
        r4 = null;
        r52 = null;
        goto L24
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

    private Node parseEntity() {
        String r0 = match(ENTITY_HERE);
        if (r0 != null) goto L5;
        return null;
    L5:
        return text(Html5Entities.entityToString(r0));
    }

    private Node parseHtmlInline() {
        String r1 = match(HTML_TAG);
        if (r1 == null) goto L6;
        HtmlInline r0 = new HtmlInline();
        r0.setLiteral(r1);
        return r0;
    L6:
        return null;
    }

    private Node parseInline(Node r3) {
        char r0 = peek();
        if (r0 != 0) goto L7;
        return null;
    L7:
        if (r0 != '\n') goto L9;
        Node r32 = parseNewline(r3);
    L31:
        if (r32 == null) goto L33;
        return r32;
    L33:
        this.index++;
        return text(String.valueOf(r0));
    L9:
        if (r0 != '!') goto L11;
        r32 = parseBang();
        goto L31
    L11:
        if (r0 != '&') goto L13;
        r32 = parseEntity();
        goto L31
    L13:
        if (r0 != '<') goto L15;
        r32 = parseAutolink();
        if (r32 != null) goto L31;
        r32 = parseHtmlInline();
        goto L31
    L15:
        if (r0 == '`') goto L24;
        switch(r0) {
            case 91: goto L23;
            case 92: goto L22;
            case 93: goto L21;
            default: goto L18;
        };
    L21:
        r32 = parseCloseBracket();
        goto L31
    L22:
        r32 = parseBackslash();
        goto L31
    L23:
        r32 = parseOpenBracket();
        goto L31
    L18:
        if (this.delimiterCharacters.get(r0) == false) goto L20;
        r32 = parseDelimiters(this.delimiterProcessors.get(Character.valueOf(r0)), r0);
        goto L31
    L20:
        r32 = parseString();
        goto L31
    L24:
        r32 = parseBackticks();
        goto L31
    }

    private String parseLinkDestination() {
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

    private String parseLinkTitle() {
        int r0 = LinkScanner.scanLinkTitle(this.input, this.index);
        if (r0 != (-1)) goto L6;
        return null;
    L6:
        String r1 = this.input.substring(this.index + 1, r0 - 1);
        this.index = r0;
        return Escaping.unescapeString(r1);
    }

    private Node parseNewline(Node r4) {
        this.index++;
        if ((r4 instanceof Text) == false) goto L19;
        Text r42 = (Text) r4;
        if (r42.getLiteral().endsWith(" ") == false) goto L19;
        String r3 = r42.getLiteral();
        Matcher r0 = FINAL_SPACE.matcher(r3);
        if (r0.find() == false) goto L9;
        int r1 = r0.end() - r0.start();
    L10:
        if (r1 <= 0) goto L13;
        r42.setLiteral(r3.substring(0, r3.length() - r1));
    L13:
        if (r1 < 2) goto L17;
        return new HardLineBreak();
    L17:
        return new SoftLineBreak();
    L9:
        r1 = 0;
    L19:
        return new SoftLineBreak();
    }

    private Node parseOpenBracket() {
        int r0 = this.index;
        this.index = r0 + 1;
        Text r1 = text("[");
        addBracket(Bracket.link(r1, r0, this.lastBracket, this.lastDelimiter));
        return r1;
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

    private char peek() {
        if (this.index < this.input.length()) goto L5;
        return 0;
    L5:
        return this.input.charAt(this.index);
    }

    private void processDelimiters(Delimiter r11) {
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
        mergeTextNodesBetweenExclusive(r23, r43);
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

    private void removeLastBracket() {
        this.lastBracket = this.lastBracket.previous;
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

    private void spnl() {
        match(SPNL);
    }

    private Text text(String r1) {
        return new Text(r1);
    }

    private Text text(String r1, int r2, int r3) {
        return new Text(r1.substring(r2, r3));
    }

    @Override // org.commonmark.parser.InlineParser
    public void parse(String r2, Node r3) {
        reset(r2.trim());
        Node r0 = null;
    L3:
        r0 = parseInline(r0);
        if (r0 == null) goto L6;
        r3.appendChild(r0);
        goto L3
    L6:
        processDelimiters(null);
        mergeChildTextNodes(r3);
    }

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

    public void reset(String r1) {
        this.input = r1;
        this.index = 0;
        this.lastDelimiter = null;
        this.lastBracket = null;
    }
}
