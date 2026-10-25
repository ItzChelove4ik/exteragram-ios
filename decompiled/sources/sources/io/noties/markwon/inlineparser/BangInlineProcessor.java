package io.noties.markwon.inlineparser;

import org.commonmark.internal.Bracket;
import org.commonmark.node.Node;
import org.commonmark.node.Text;

/* loaded from: classes5.dex */
public class BangInlineProcessor extends InlineProcessor {
    public BangInlineProcessor() {
    }

    @Override // io.noties.markwon.inlineparser.InlineProcessor
    public Node parse() {
        int r0 = this.index;
        this.index = r0 + 1;
        if (peek() != '[') goto L6;
        this.index++;
        Text r1 = text("![");
        addBracket(Bracket.image(r1, r0 + 1, lastBracket(), lastDelimiter()));
        return r1;
    L6:
        return null;
    }

    @Override // io.noties.markwon.inlineparser.InlineProcessor
    public char specialCharacter() {
        return '!';
    }
}
