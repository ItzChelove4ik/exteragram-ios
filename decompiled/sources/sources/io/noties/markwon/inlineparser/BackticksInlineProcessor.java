package io.noties.markwon.inlineparser;

import java.util.regex.Pattern;
import org.commonmark.internal.util.Parsing;
import org.commonmark.node.Code;
import org.commonmark.node.Node;

/* loaded from: classes5.dex */
public class BackticksInlineProcessor extends InlineProcessor {
    private static final Pattern TICKS = null;
    private static final Pattern TICKS_HERE = null;

    static {
        TICKS = Pattern.compile("`+");
        TICKS_HERE = Pattern.compile("^`+");
    }

    public BackticksInlineProcessor() {
    }

    @Override // io.noties.markwon.inlineparser.InlineProcessor
    public Node parse() {
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

    @Override // io.noties.markwon.inlineparser.InlineProcessor
    public char specialCharacter() {
        return '`';
    }
}
