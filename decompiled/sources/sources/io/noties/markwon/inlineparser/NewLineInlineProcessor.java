package io.noties.markwon.inlineparser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.commonmark.node.HardLineBreak;
import org.commonmark.node.Node;
import org.commonmark.node.SoftLineBreak;
import org.commonmark.node.Text;

/* loaded from: classes5.dex */
public class NewLineInlineProcessor extends InlineProcessor {
    private static final Pattern FINAL_SPACE = null;

    static {
        FINAL_SPACE = Pattern.compile(" *$");
    }

    public NewLineInlineProcessor() {
    }

    @Override // io.noties.markwon.inlineparser.InlineProcessor
    public Node parse() {
        this.index++;
        Node r4 = this.block.getLastChild();
        if ((r4 instanceof Text) == false) goto L19;
        Text r42 = (Text) r4;
        if (r42.getLiteral().endsWith(" ") == false) goto L19;
        String r0 = r42.getLiteral();
        Matcher r1 = FINAL_SPACE.matcher(r0);
        if (r1.find() == false) goto L9;
        int r2 = r1.end() - r1.start();
    L10:
        if (r2 <= 0) goto L13;
        r42.setLiteral(r0.substring(0, r0.length() - r2));
    L13:
        if (r2 < 2) goto L17;
        return new HardLineBreak();
    L17:
        return new SoftLineBreak();
    L9:
        r2 = 0;
    L19:
        return new SoftLineBreak();
    }

    @Override // io.noties.markwon.inlineparser.InlineProcessor
    public char specialCharacter() {
        return '\n';
    }
}
