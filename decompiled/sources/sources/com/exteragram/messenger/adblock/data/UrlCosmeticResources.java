package com.exteragram.messenger.adblock.data;

/* loaded from: classes4.dex */
public class UrlCosmeticResources {
    private final String[] exceptions;
    private final boolean genericHide;
    private final String[] hideSelectors;
    private final String injectedScript;
    private final String[] proceduralActions;

    public UrlCosmeticResources(String[] r1, String[] r2, String[] r3, String r4, boolean r5) {
        this.hideSelectors = r1;
        this.proceduralActions = r2;
        this.exceptions = r3;
        this.injectedScript = r4;
        this.genericHide = r5;
    }

    public String[] getExceptions() {
        return this.exceptions;
    }

    public String[] getHideSelectors() {
        return this.hideSelectors;
    }

    public String getInjectedScript() {
        return this.injectedScript;
    }

    public String[] getProceduralActions() {
        return this.proceduralActions;
    }

    public boolean isGenericHide() {
        return this.genericHide;
    }
}
