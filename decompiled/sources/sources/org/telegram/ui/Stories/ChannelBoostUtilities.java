package org.telegram.ui.Stories;

import android.text.TextUtils;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public abstract class ChannelBoostUtilities {
    public static String createLink(int r1, long r2) {
        long r22 = -r2;
        TLRPC.Chat r12 = MessagesController.getInstance(r1).getChat(Long.valueOf(r22));
        if (TextUtils.isEmpty(ChatObject.getPublicUsername(r12)) == true) goto L7;
        return "https://t.me/boost/" + ChatObject.getPublicUsername(r12);
    L7:
        return "https://t.me/boost/?c=" + r22;
    }
}
