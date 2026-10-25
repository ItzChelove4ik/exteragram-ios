package com.exteragram.messenger.export.api;

import java.util.ArrayList;

/* loaded from: classes4.dex */
public class ApiWrap$GiveawayResults {
    public int additionalPeersCount;
    public String additionalPrize;
    public boolean all;
    public long channel;
    public long credits;
    public int launchId;
    public int months;
    public boolean refunded;
    public int unclaimedCount;
    public int untilDate;
    public ArrayList<Long> winners;
    public int winnersCount;

    public ApiWrap$GiveawayResults(long r1, int r3, int r4, int r5, int r6, int r7, int r8, long r9, boolean r11, boolean r12) {
        this.channel = r1;
        this.untilDate = r3;
        this.launchId = r4;
        this.additionalPeersCount = r5;
        this.winnersCount = r6;
        this.unclaimedCount = r7;
        this.months = r8;
        this.credits = r9;
        this.refunded = r11;
        this.all = r12;
        this.winners = new ArrayList();
    }
}
