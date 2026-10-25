package io.noties.markwon.inlineparser;

import java.util.regex.Pattern;
import kotlin.text.Typography;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.node.Text;

/* loaded from: classes5.dex */
public class AutolinkInlineProcessor extends InlineProcessor {
    private static final Pattern AUTOLINK = null;
    private static final Pattern EMAIL_AUTOLINK = null;

    static {
        EMAIL_AUTOLINK = Pattern.compile("^<([a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*)>");
        AUTOLINK = Pattern.compile("^<[a-zA-Z][a-zA-Z0-9.+-]{1,31}:[^<>\u0000- ]*>");
    }

    public AutolinkInlineProcessor() {
    }

    @Override // io.noties.markwon.inlineparser.InlineProcessor
    public Node parse() {
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

    @Override // io.noties.markwon.inlineparser.InlineProcessor
    public char specialCharacter() {
        return Typography.less;
    }
}
