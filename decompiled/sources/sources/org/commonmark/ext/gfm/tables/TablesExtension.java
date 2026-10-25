package org.commonmark.ext.gfm.tables;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.internal.TableBlockParser;
import org.commonmark.parser.Parser;

/* loaded from: classes5.dex */
public class TablesExtension implements Parser.ParserExtension, Extension {
    private TablesExtension() {
    }

    public static Extension create() {
        return new TablesExtension();
    }

    @Override // org.commonmark.parser.Parser.ParserExtension
    public void extend(Parser.Builder r1) {
        r1.customBlockParserFactory(new TableBlockParser.Factory());
    }
}
