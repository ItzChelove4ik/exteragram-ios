package io.noties.markwon.ext.latex;

import io.noties.markwon.inlineparser.InlineProcessor;
import java.util.regex.Pattern;
import kotlin.text.Typography;
import org.commonmark.node.Node;

/* loaded from: classes5.dex */
class JLatexMathInlineProcessor extends InlineProcessor {
    private static final Pattern RE = null;

    static {
        RE = Pattern.compile("(\\${2})([\\s\\S]+?)\\1");
    }

    public JLatexMathInlineProcessor() {
    }

    @Override // io.noties.markwon.inlineparser.InlineProcessor
    public Node parse() {
        String r3 = match(RE);
        if (r3 != null) goto L6;
        return null;
    L6:
        JLatexMathNode r0 = new JLatexMathNode();
        r0.latex(r3.substring(2, r3.length() - 2));
        return r0;
    }

    @Override // io.noties.markwon.inlineparser.InlineProcessor
    public char specialCharacter() {
        return Typography.dollar;
    }
}
