package com.exteragram.messenger.adblock.data;

/* loaded from: classes.dex */
public class FilterListMetadata {
    private final Integer expires;
    private final String homepage;
    private final String redirect;
    private final String title;

    public FilterListMetadata(String r1, String r2, String r3, int r4) {
        this.homepage = r1;
        this.title = r2;
        this.redirect = r3;
        if (r4 != 0) goto L5;
        Integer r12 = null;
    L6:
        this.expires = r12;
        return;
    L5:
        r12 = Integer.valueOf(r4);
        goto L6
    }

    public Integer getExpires() {
        return this.expires;
    }

    public String getHomepage() {
        return this.homepage;
    }

    public String getRedirect() {
        return this.redirect;
    }

    public String getTitle() {
        return this.title;
    }
}
