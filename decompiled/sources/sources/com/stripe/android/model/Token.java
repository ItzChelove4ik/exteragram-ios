package com.stripe.android.model;

import java.util.Date;

/* loaded from: classes5.dex */
public class Token {
    private final Card mCard;
    private final Date mCreated;
    private final String mId;
    private final boolean mLivemode;
    private final String mType;
    private final boolean mUsed;

    public Token(String r1, boolean r2, Date r3, Boolean r4, Card r5, String r6) {
        this.mId = r1;
        this.mType = r6;
        this.mCreated = r3;
        this.mLivemode = r2;
        this.mCard = r5;
        this.mUsed = r4.booleanValue();
    }

    public Card getCard() {
        return this.mCard;
    }

    public String getId() {
        return this.mId;
    }

    public String getType() {
        return this.mType;
    }
}
