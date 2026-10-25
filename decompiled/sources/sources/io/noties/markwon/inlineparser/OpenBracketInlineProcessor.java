package io.noties.markwon.inlineparser;

import org.commonmark.internal.Bracket;
import org.commonmark.node.Node;
import org.commonmark.node.Text;

/* loaded from: classes5.dex */
public class OpenBracketInlineProcessor extends InlineProcessor {
    public OpenBracketInlineProcessor() {
    }

    @Override // io.noties.markwon.inlineparser.InlineProcessor
    public Node parse() {
        int r0 = this.index;
        this.index = r0 + 1;
        Text r1 = text("[");
        addBracket(Bracket.link(r1, r0, lastBracket(), lastDelimiter()));
        return r1;
    }

    @Override // io.noties.markwon.inlineparser.InlineProcessor
    public char specialCharacter() {
        return '[';
    }
}
