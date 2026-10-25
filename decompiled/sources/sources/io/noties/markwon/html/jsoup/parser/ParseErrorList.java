package io.noties.markwon.html.jsoup.parser;

import java.util.ArrayList;

/* loaded from: classes5.dex */
public class ParseErrorList extends ArrayList<ParseError> {
    private final int maxSize;

    public ParseErrorList(int r1, int r2) {
        super(r1);
        this.maxSize = r2;
    }

    public static ParseErrorList noTracking() {
        return new ParseErrorList(0, 0);
    }

    public boolean canAddError() {
        if (size() >= this.maxSize) goto L6;
        return true;
    L6:
        return false;
    }
}
