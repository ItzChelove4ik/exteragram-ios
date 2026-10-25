package io.noties.markwon.html.jsoup.parser;

/* loaded from: classes5.dex */
public class ParseError {
    private String errorMsg;
    private int pos;

    public ParseError(int r1, String r2) {
        this.pos = r1;
        this.errorMsg = r2;
    }

    public ParseError(int r1, String r2, Object... r3) {
        this.errorMsg = String.format(r2, r3);
        this.pos = r1;
    }

    public String toString() {
        return this.pos + ": " + this.errorMsg;
    }
}
