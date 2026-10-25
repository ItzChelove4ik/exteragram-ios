package io.noties.markwon.html;

import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.cast.MediaTrack;
import io.noties.markwon.html.HtmlTag;
import io.noties.markwon.html.HtmlTagImpl;
import io.noties.markwon.html.MarkwonHtmlParser;
import io.noties.markwon.html.jsoup.nodes.Attribute;
import io.noties.markwon.html.jsoup.nodes.Attributes;
import io.noties.markwon.html.jsoup.parser.CharacterReader;
import io.noties.markwon.html.jsoup.parser.ParseErrorList;
import io.noties.markwon.html.jsoup.parser.Token;
import io.noties.markwon.html.jsoup.parser.Tokeniser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.webrtc.MediaStreamTrack;

/* loaded from: classes5.dex */
public class MarkwonHtmlParserImpl extends MarkwonHtmlParser {
    private static final Set<String> BLOCK_TAGS = null;
    static final Set<String> INLINE_TAGS = null;
    private static final Set<String> VOID_TAGS = null;
    private HtmlTagImpl.BlockImpl currentBlock;
    private final HtmlEmptyTagReplacement emptyTagReplacement;
    private final List<HtmlTagImpl.InlineImpl> inlineTags;
    private boolean isInsidePreTag;
    private boolean previousIsBlock;
    private final TrimmingAppender trimmingAppender;

    /* renamed from: io.noties.markwon.html.MarkwonHtmlParserImpl$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$noties$markwon$html$jsoup$parser$Token$TokenType = null;

        static {
            int[] r0 = new int[Token.TokenType.values().length];
            $SwitchMap$io$noties$markwon$html$jsoup$parser$Token$TokenType = r0;
            r0[Token.TokenType.StartTag.ordinal()] = 1;     // Catch: NoSuchFieldError -> L7
        L10:
            $SwitchMap$io$noties$markwon$html$jsoup$parser$Token$TokenType[Token.TokenType.EndTag.ordinal()] = 2;     // Catch: NoSuchFieldError -> L8
        L12:
            $SwitchMap$io$noties$markwon$html$jsoup$parser$Token$TokenType[Token.TokenType.Character.ordinal()] = 3;     // Catch: NoSuchFieldError -> L9
            return;
        }
    }

    static {
        INLINE_TAGS = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"a", "abbr", "acronym", "b", "bdo", "big", "br", "button", "cite", "code", "dfn", "em", "i", "img", PluginsConstants.Settings.TYPE_INPUT, "kbd", "label", "map", "object", "q", "samp", "script", "select", "small", "span", "strong", "sub", "sup", "textarea", "time", "tt", "var"})));
        VOID_TAGS = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"area", "base", "br", "col", "embed", "hr", "img", PluginsConstants.Settings.TYPE_INPUT, "keygen", "link", "meta", "param", "source", "track", "wbr"})));
        BLOCK_TAGS = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"address", "article", "aside", "blockquote", "canvas", "dd", "div", "dl", "dt", "fieldset", "figcaption", "figure", "footer", "form", "h1", "h2", "h3", "h4", "h5", "h6", PluginsConstants.Settings.TYPE_HEADER, "hgroup", "hr", "li", MediaTrack.ROLE_MAIN, "nav", "noscript", "ol", "output", "p", "pre", "section", "table", "tfoot", "ul", MediaStreamTrack.VIDEO_TRACK_KIND})));
    }

    public MarkwonHtmlParserImpl(HtmlEmptyTagReplacement r3, TrimmingAppender r4) {
        this.inlineTags = new ArrayList(0);
        this.currentBlock = HtmlTagImpl.BlockImpl.root();
        this.emptyTagReplacement = r3;
        this.trimmingAppender = r4;
    }

    public static MarkwonHtmlParserImpl create() {
        return create(HtmlEmptyTagReplacement.create());
    }

    public static MarkwonHtmlParserImpl create(HtmlEmptyTagReplacement r2) {
        return new MarkwonHtmlParserImpl(r2, TrimmingAppender.create());
    }

    public static <T extends Appendable & CharSequence> void ensureNewLine(T r2) {
        T r0 = r2;
        int r1 = r0.length();
        if (r1 > 0) goto L5;
        return;
    L5:
        if ('\n' == r0.charAt(r1 - 1)) goto L9;
        AppendableUtils.appendQuietly(r2, '\n');
        return;
    }

    public static Map<String, String> extractAttributes(Token.StartTag r4) {
        Attributes r42 = r4.attributes;
        int r0 = r42.size();
        if (r0 <= 0) goto L11;
        HashMap r1 = new HashMap(r0);
        Iterator<Attribute> r43 = r42.iterator();
    L6:
        if (r43.hasNext() == false) goto L9;
        Attribute r02 = r43.next();
        r1.put(r02.getKey2().toLowerCase(Locale.US), r02.getValue2());
        goto L6
    L9:
        return Collections.unmodifiableMap(r1);
    L11:
        return Collections.EMPTY_MAP;
    }

    public static boolean isBlockTag(String r1) {
        return BLOCK_TAGS.contains(r1);
    }

    public static <T extends Appendable & CharSequence> boolean isEmpty(T r0, HtmlTagImpl r1) {
        if (r1.start != r0.length()) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isInlineTag(String r1) {
        return INLINE_TAGS.contains(r1);
    }

    public static boolean isVoidTag(String r1) {
        return VOID_TAGS.contains(r1);
    }

    public void appendBlockChild(HtmlTagImpl.BlockImpl r2, HtmlTagImpl.BlockImpl r3) {
        List r1 = r2.children;
        if (r1 != null) goto L5;
        r1 = new ArrayList(2);
        r2.children = r1;
    L5:
        r1.add(r3);
    }

    public <T extends Appendable & CharSequence> void appendEmptyTagReplacement(T r1, HtmlTagImpl r2) {
        String r0 = this.emptyTagReplacement.replace(r2);
        if (r0 == null) goto L6;
        AppendableUtils.appendQuietly(r1, r0);
        return;
    }

    public <T extends Appendable & CharSequence> void ensureNewLineIfPreviousWasBlock(T r2) {
        if (this.previousIsBlock == false) goto L6;
        ensureNewLine(r2);
        this.previousIsBlock = false;
        return;
    }

    public HtmlTagImpl.BlockImpl findOpenBlockTag(String r2) {
        HtmlTagImpl.BlockImpl r1 = this.currentBlock;
    L3:
        if (r1 == null) goto L9;
        if (r2.equals(r1.name) == true) goto L9;
        if (r1.isClosed() == true) goto L9;
        r1 = r1.parent;
    L9:
        return r1;
    }

    public HtmlTagImpl.InlineImpl findOpenInlineTag(String r4) {
        int r0 = this.inlineTags.size();
    L3:
        r0 = r0 - 1;
        if (r0 <= (-1)) goto L11;
        HtmlTagImpl.InlineImpl r1 = this.inlineTags.get(r0);
        if (r4.equals(r1.name) == false) goto L3;
        if (r1.end >= 0) goto L3;
        return r1;
    L11:
        return null;
    }

    @Override // io.noties.markwon.html.MarkwonHtmlParser
    public void flushBlockTags(int r3, MarkwonHtmlParser.FlushAction<HtmlTag.Block> r4) {
        HtmlTagImpl.BlockImpl r0 = this.currentBlock;
    L3:
        HtmlTagImpl.BlockImpl r1 = r0.parent;
        if (r1 == null) goto L7;
        r0 = r1;
        goto L3
    L7:
        if (r3 <= (-1)) goto L9;
        r0.closeAt(r3);
    L9:
        List<HtmlTag.Block> r32 = r0.children();
        if (r32.size() <= 0) goto L12;
        r4.apply(r32);
    L13:
        this.currentBlock = HtmlTagImpl.BlockImpl.root();
        return;
    L12:
        r4.apply(Collections.EMPTY_LIST);
        goto L13
    }

    @Override // io.noties.markwon.html.MarkwonHtmlParser
    public void flushInlineTags(int r3, MarkwonHtmlParser.FlushAction<Object> r4) {
        if (this.inlineTags.size() > 0) goto L5;
        r4.apply(Collections.EMPTY_LIST);
        return;
    L5:
        if (r3 <= (-1)) goto L10;
        Iterator<HtmlTagImpl.InlineImpl> r0 = this.inlineTags.iterator();
    L8:
        if (r0.hasNext() == false) goto L10;
        r0.next().closeAt(r3);
    L10:
        r4.apply(Collections.unmodifiableList(this.inlineTags));
        this.inlineTags.clear();
    }

    public <T extends Appendable & CharSequence> void processBlockTagEnd(T r3, Token.EndTag r4) {
        String r42 = r4.normalName;
        HtmlTagImpl.BlockImpl r0 = findOpenBlockTag(r42);
        if (r0 != null) goto L5;
        return;
    L5:
        if ("pre".equals(r42) == false) goto L8;
        this.isInsidePreTag = false;
    L8:
        if (isEmpty(r3, r0) == false) goto L10;
        appendEmptyTagReplacement(r3, r0);
    L10:
        r0.closeAt(r3.length());
        if (r0.isEmpty() == true) goto L14;
        this.previousIsBlock = isBlockTag(r0.name);
    L14:
        if ("p".equals(r42) == false) goto L16;
        AppendableUtils.appendQuietly(r3, '\n');
    L16:
        this.currentBlock = r0.parent;
    }

    public <T extends Appendable & CharSequence> void processBlockTagStart(T r6, Token.StartTag r7) {
        String r0 = r7.normalName;
        if ("p".equals(this.currentBlock.name) == false) goto L6;
        this.currentBlock.closeAt(r6.length());
        AppendableUtils.appendQuietly(r6, '\n');
        this.currentBlock = this.currentBlock.parent;
    L11:
        if (isBlockTag(r0) == false) goto L13;
        this.isInsidePreTag = "pre".equals(r0);
        ensureNewLine(r6);
    L14:
        T r1 = r6;
        HtmlTagImpl.BlockImpl r2 = HtmlTagImpl.BlockImpl.create(r0, r1.length(), extractAttributes(r7), this.currentBlock);
        if (isVoidTag(r0) == false) goto L17;
    L20:
        boolean r72 = true;
    L21:
        if (r72 == false) goto L28;
        String r02 = this.emptyTagReplacement.replace(r2);
        if (r02 != null) goto L25;
    L27:
        r2.closeAt(r1.length());
        goto L28
    L25:
        if (r02.length() <= 0) goto L27;
        AppendableUtils.appendQuietly(r6, r02);
    L28:
        appendBlockChild(r2.parent, r2);
        if (r72 == true) goto L32;
        this.currentBlock = r2;
        return;
    L32:
        return;
    L17:
        if (r7.selfClosing == true) goto L20;
        r72 = false;
        goto L21
    L13:
        ensureNewLineIfPreviousWasBlock(r6);
        goto L14
    L6:
        if ("li".equals(r0) == false) goto L11;
        if ("li".equals(this.currentBlock.name) == false) goto L11;
        this.currentBlock.closeAt(r6.length());
        this.currentBlock = this.currentBlock.parent;
        goto L11
    }

    public <T extends Appendable & CharSequence> void processCharacter(T r2, Token.Character r3) {
        if (this.isInsidePreTag == false) goto L6;
        AppendableUtils.appendQuietly(r2, r3.getData());
        return;
    L6:
        ensureNewLineIfPreviousWasBlock(r2);
        this.trimmingAppender.append(r2, r3.getData());
    }

    @Override // io.noties.markwon.html.MarkwonHtmlParser
    public <T extends Appendable & CharSequence> void processFragment(T r4, String r5) {
        Tokeniser r0 = new Tokeniser(new CharacterReader(r5), ParseErrorList.noTracking());
    L3:
        Token r52 = r0.read();
        Token.TokenType r1 = r52.type;
        if (Token.TokenType.EOF == r1) goto L5;
        int r12 = AnonymousClass1.$SwitchMap$io$noties$markwon$html$jsoup$parser$Token$TokenType[r1.ordinal()];
        if (r12 != 1) goto L9;
        Token.StartTag r13 = (Token.StartTag) r52;
        if (isInlineTag(r13.normalName) == false) goto L21;
        processInlineTagStart(r4, r13);
    L22:
        r52.reset();
        goto L3
    L21:
        processBlockTagStart(r4, r13);
        goto L22
    L9:
        if (r12 != 2) goto L11;
        Token.EndTag r14 = (Token.EndTag) r52;
        if (isInlineTag(r14.normalName) == false) goto L17;
        processInlineTagEnd(r4, r14);
        goto L22
    L17:
        processBlockTagEnd(r4, r14);
        goto L22
    L11:
        if (r12 != 3) goto L22;
        processCharacter(r4, (Token.Character) r52);
        goto L22
    }

    public <T extends Appendable & CharSequence> void processInlineTagEnd(T r2, Token.EndTag r3) {
        HtmlTagImpl.InlineImpl r32 = findOpenInlineTag(r3.normalName);
        if (r32 != null) goto L5;
        return;
    L5:
        if (isEmpty(r2, r32) == false) goto L7;
        appendEmptyTagReplacement(r2, r32);
    L7:
        r32.closeAt(r2.length());
    }

    public <T extends Appendable & CharSequence> void processInlineTagStart(T r6, Token.StartTag r7) {
        String r0 = r7.normalName;
        T r2 = r6;
        HtmlTagImpl.InlineImpl r1 = new HtmlTagImpl.InlineImpl(r0, r2.length(), extractAttributes(r7));
        ensureNewLineIfPreviousWasBlock(r6);
        if (isVoidTag(r0) == false) goto L5;
    L6:
        String r72 = this.emptyTagReplacement.replace(r1);
        if (r72 != null) goto L9;
    L11:
        r1.closeAt(r2.length());
    L12:
        this.inlineTags.add(r1);
        return;
    L9:
        if (r72.length() <= 0) goto L11;
        AppendableUtils.appendQuietly(r6, r72);
        goto L11
    L5:
        if (r7.selfClosing == false) goto L12;
        goto L6
    }
}
