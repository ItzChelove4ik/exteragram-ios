package com.exteragram.messenger.feed.ads;

import java.util.ArrayList;
import java.util.Set;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final class FeedAd {
    public String additionalInfo;
    public int badge;
    int bodyMessageId;
    public CharSequence bodyText;
    public String buttonText;
    public int colorId;
    public ArrayList<TLRPC.MessageEntity> entities;
    public String id;
    public Set<String> locales;
    public TLRPC.MessageMedia media;
    int mediaMessageId;
    public int premium;
    public boolean recommended;
    public String sponsorInfo;
    public String title;
    public String url;
    public int weight;

    public FeedAd() {
        this.weight = 1;
        this.colorId = -1;
        this.premium = 0;
        this.badge = 0;
    }

    public boolean isDisplayable() {
        String r0 = this.id;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isEmpty() == false) goto L7;
        return false;
    L7:
        if (this.bodyText == null) goto L9;
        return true;
    L9:
        if (this.title == null) goto L11;
        return true;
    L11:
        if (this.media == null) goto L19;
        return true;
    L19:
        return false;
    }
}
