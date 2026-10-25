package org.commonmark.ext.gfm.strikethrough;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.strikethrough.internal.StrikethroughDelimiterProcessor;
import org.commonmark.parser.Parser;

/* loaded from: classes5.dex */
public class StrikethroughExtension implements Parser.ParserExtension, Extension {
    private StrikethroughExtension() {
    }

    public static Extension create() {
        return new StrikethroughExtension();
    }

    @Override // org.commonmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder r1) {
        r1.customDelimiterProcessor(new StrikethroughDelimiterProcessor());
    }
}
