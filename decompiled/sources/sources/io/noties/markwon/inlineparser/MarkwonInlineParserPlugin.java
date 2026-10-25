package io.noties.markwon.inlineparser;

import io.noties.markwon.AbstractMarkwonPlugin;
import io.noties.markwon.inlineparser.MarkwonInlineParser;
import org.commonmark.parser.Parser;

/* loaded from: classes5.dex */
public class MarkwonInlineParserPlugin extends AbstractMarkwonPlugin {
    private final MarkwonInlineParser.FactoryBuilder factoryBuilder;

    public MarkwonInlineParserPlugin(MarkwonInlineParser.FactoryBuilder r1) {
        this.factoryBuilder = r1;
    }

    public static MarkwonInlineParserPlugin create() {
        return create(MarkwonInlineParser.factoryBuilder());
    }

    public static MarkwonInlineParserPlugin create(MarkwonInlineParser.FactoryBuilder r1) {
        return new MarkwonInlineParserPlugin(r1);
    }

    public void configureParser(Parser.Builder r1) {
        r1.inlineParserFactory(this.factoryBuilder.build());
    }

    public MarkwonInlineParser.FactoryBuilder factoryBuilder() {
        return this.factoryBuilder;
    }
}
