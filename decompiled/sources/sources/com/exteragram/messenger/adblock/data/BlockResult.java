package com.exteragram.messenger.adblock.data;

/* loaded from: classes4.dex */
public class BlockResult {
    private final String exception;
    private final String filter;
    private final boolean important;
    private final boolean matched;
    private final String redirect;
    private final String rewrittenUrl;

    public BlockResult(boolean r1, boolean r2, String r3, String r4, String r5, String r6) {
        this.matched = r1;
        this.important = r2;
        this.redirect = r3;
        this.rewrittenUrl = r4;
        this.exception = r5;
        this.filter = r6;
    }

    public String getException() {
        return this.exception;
    }

    public String getFilter() {
        return this.filter;
    }

    public String getRedirect() {
        return this.redirect;
    }

    public String getRewrittenUrl() {
        return this.rewrittenUrl;
    }

    public boolean isImportant() {
        return this.important;
    }

    public boolean isMatched() {
        return this.matched;
    }
}
