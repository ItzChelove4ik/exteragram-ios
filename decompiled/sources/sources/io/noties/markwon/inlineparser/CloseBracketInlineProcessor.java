package io.noties.markwon.inlineparser;

import java.util.regex.Pattern;
import org.commonmark.internal.Bracket;
import org.commonmark.internal.util.Escaping;
import org.commonmark.node.Image;
import org.commonmark.node.Link;
import org.commonmark.node.LinkReferenceDefinition;
import org.commonmark.node.Node;

/* loaded from: classes5.dex */
public class CloseBracketInlineProcessor extends InlineProcessor {
    private static final Pattern WHITESPACE = null;

    static {
        WHITESPACE = MarkwonInlineParser.WHITESPACE;
    }

    public CloseBracketInlineProcessor() {
    }

    @Override // io.noties.markwon.inlineparser.InlineProcessor
    public Node parse() {
        boolean r1 = true;
        int r0 = this.index + 1;
        this.index = r0;
        Bracket r2 = lastBracket();
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
        InlineParserUtils.mergeChildTextNodes(r02);
        r2.node.unlink();
        removeLastBracket();
        if (r2.image == true) goto L52;
        Bracket r122 = lastBracket();
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

    @Override // io.noties.markwon.inlineparser.InlineProcessor
    public char specialCharacter() {
        return ']';
    }
}
