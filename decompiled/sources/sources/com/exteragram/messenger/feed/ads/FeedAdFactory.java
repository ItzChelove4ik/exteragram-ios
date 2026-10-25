package com.exteragram.messenger.feed.ads;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public abstract class FeedAdFactory {
    private static final AtomicInteger nextId = null;

    static {
        nextId = new AtomicInteger(-20000000);
    }

    private static TLRPC.PeerColor buildColor(int r2) {
        if (r2 >= 0) goto L5;
        return null;
    L5:
        TLRPC.TL_peerColor r0 = new TLRPC.TL_peerColor();
        r0.flags |= 1;
        r0.color = r2;
        return r0;
    }

    public static MessageObject createAdMessageObject(int r7, FeedAd r8) {
        TLRPC.TL_message r2 = new TLRPC.TL_message();
        r2.id = nextId.getAndDecrement();
        r2.date = ConnectionsManager.getInstance(r7).getCurrentTime();
        CharSequence r0 = r8.bodyText;
        if (r0 == null) goto L5;
        String r02 = r0.toString();
    L6:
        r2.message = r02;
        r2.peer_id = new TLRPC.TL_peerChannel();
        r2.flags |= 256;
        ArrayList<TLRPC.MessageEntity> r03 = r8.entities;
        if (r03 != null) goto L9;
    L11:
        TLRPC.MessageMedia r04 = r8.media;
        if (r04 == null) goto L14;
        r2.media = r04;
        r2.flags |= 512;
    L14:
        MessageObject r05 = new MessageObject(r7, r2, new HashMap(), new HashMap(), true, true);
        r05.searchType = 4;
        r05.sponsoredId = r8.id.getBytes(StandardCharsets.UTF_8);
        r05.sponsoredTitle = r8.title;
        r05.sponsoredUrl = r8.url;
        r05.sponsoredButtonText = r8.buttonText;
        r05.sponsoredInfo = r8.sponsorInfo;
        r05.sponsoredAdditionalInfo = r8.additionalInfo;
        r05.sponsoredRecommended = r8.recommended;
        r05.sponsoredCanReport = false;
        r05.sponsoredMedia = r8.media;
        r05.sponsoredColor = buildColor(r8.colorId);
        r05.setType();
        r05.textLayoutBlocks = new ArrayList();
        r05.generateThumbs(true);
        return r05;
    L9:
        if (r03.isEmpty() == true) goto L11;
        r2.entities = r8.entities;
        r2.flags |= 128;
        goto L11
    L5:
        r02 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }
}
