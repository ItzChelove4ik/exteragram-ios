package io.noties.markwon.inlineparser;

import java.util.regex.Pattern;
import kotlin.text.Typography;
import org.commonmark.node.HtmlInline;
import org.commonmark.node.Node;

/* loaded from: classes5.dex */
public class HtmlInlineProcessor extends InlineProcessor {
    private static final Pattern HTML_TAG = null;

    static {
        HTML_TAG = Pattern.compile("^(?:<[A-Za-z][A-Za-z0-9-]*(?:\\s+[a-zA-Z_:][a-zA-Z0-9:._-]*(?:\\s*=\\s*(?:[^\"'=<>`\\x00-\\x20]+|'[^']*'|\"[^\"]*\"))?)*\\s*/?>|</[A-Za-z][A-Za-z0-9-]*\\s*[>]|<!---->|<!--(?:-?[^>-])(?:-?[^-])*-->|[<][?].*?[?][>]|<![A-Z]+\\s+[^>]*>|<!\\[CDATA\\[[\\s\\S]*?\\]\\]>)", 2);
    }

    public HtmlInlineProcessor() {
    }

    @Override // io.noties.markwon.inlineparser.InlineProcessor
    public Node parse() {
        String r1 = match(HTML_TAG);
        if (r1 == null) goto L6;
        HtmlInline r0 = new HtmlInline();
        r0.setLiteral(r1);
        return r0;
    L6:
        return null;
    }

    @Override // io.noties.markwon.inlineparser.InlineProcessor
    public char specialCharacter() {
        return Typography.less;
    }
}
