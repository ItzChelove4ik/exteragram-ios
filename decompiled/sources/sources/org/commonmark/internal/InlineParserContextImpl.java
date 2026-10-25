package org.commonmark.internal;

import java.util.List;
import java.util.Map;
import org.commonmark.node.LinkReferenceDefinition;
import org.commonmark.parser.InlineParserContext;
import org.commonmark.parser.delimiter.DelimiterProcessor;

/* loaded from: classes5.dex */
public class InlineParserContextImpl implements InlineParserContext {
    private final List<DelimiterProcessor> delimiterProcessors;
    private final Map<String, LinkReferenceDefinition> linkReferenceDefinitions;

    public InlineParserContextImpl(List<DelimiterProcessor> r1, Map<String, LinkReferenceDefinition> r2) {
        this.delimiterProcessors = r1;
        this.linkReferenceDefinitions = r2;
    }

    @Override // org.commonmark.parser.InlineParserContext
    public List<DelimiterProcessor> getCustomDelimiterProcessors() {
        return this.delimiterProcessors;
    }

    @Override // org.commonmark.parser.InlineParserContext
    public LinkReferenceDefinition getLinkReferenceDefinition(String r1) {
        return this.linkReferenceDefinitions.get(r1);
    }
}
