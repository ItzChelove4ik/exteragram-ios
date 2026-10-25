package io.noties.markwon.inlineparser;

import java.util.regex.Pattern;
import kotlin.text.Typography;
import org.commonmark.internal.util.Html5Entities;
import org.commonmark.node.Node;

/* loaded from: classes5.dex */
public class EntityInlineProcessor extends InlineProcessor {
    private static final Pattern ENTITY_HERE = null;

    static {
        ENTITY_HERE = Pattern.compile("^&(?:#x[a-f0-9]{1,6}|#[0-9]{1,7}|[a-z][a-z0-9]{1,31});", 2);
    }

    public EntityInlineProcessor() {
    }

    @Override // io.noties.markwon.inlineparser.InlineProcessor
    public Node parse() {
        String r0 = match(ENTITY_HERE);
        if (r0 != null) goto L5;
        return null;
    L5:
        return text(Html5Entities.entityToString(r0));
    }

    @Override // io.noties.markwon.inlineparser.InlineProcessor
    public char specialCharacter() {
        return Typography.amp;
    }
}
