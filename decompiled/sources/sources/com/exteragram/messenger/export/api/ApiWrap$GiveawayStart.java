package com.exteragram.messenger.export.api;

import java.util.ArrayList;

/* loaded from: classes4.dex */
public class ApiWrap$GiveawayStart {
    public String additionalPrize;
    public boolean all;
    public ArrayList<Long> channels;
    public ArrayList<String> countries;
    public long credits;
    public int months;
    public int quantity;
    public int untilDate;

    public ApiWrap$GiveawayStart(int r1, long r2, int r4, int r5, boolean r6) {
        this.untilDate = r1;
        this.credits = r2;
        this.quantity = r4;
        this.months = r5;
        this.all = r6;
        this.countries = new ArrayList();
        this.channels = new ArrayList();
    }
}
