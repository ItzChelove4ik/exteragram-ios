package io.noties.markwon.inlineparser;

import java.util.regex.Pattern;
import org.commonmark.node.HardLineBreak;
import org.commonmark.node.Node;
import org.commonmark.node.Text;

/* loaded from: classes5.dex */
public class BackslashInlineProcessor extends InlineProcessor {
    private static final Pattern ESCAPABLE = null;

    static {
        ESCAPABLE = MarkwonInlineParser.ESCAPABLE;
    }

    public BackslashInlineProcessor() {
    }

    @Override // io.noties.markwon.inlineparser.InlineProcessor
    public Node parse() {
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

    @Override // io.noties.markwon.inlineparser.InlineProcessor
    public char specialCharacter() {
        return '\\';
    }
}
