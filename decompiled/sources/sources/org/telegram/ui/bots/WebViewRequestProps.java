package org.telegram.ui.bots;

import android.net.Uri;
import android.text.TextUtils;
import org.telegram.messenger.FileLog;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public class WebViewRequestProps {
    public boolean allowWrite;
    public TLRPC.BotApp app;
    public long botId;
    public TLRPC.User botUser;
    public String buttonText;
    public String buttonUrl;
    public boolean compact;
    public int currentAccount;
    public int flags;
    public boolean fullscreen;
    public long monoforumTopicId;
    public long peerId;
    public long queryId;
    public int replyToMsgId;
    public TLObject response;
    public long responseTime;
    public boolean silent;
    public String startParam;
    public int type;

    public WebViewRequestProps() {
    }

    public static WebViewRequestProps of(int r4, long r5, long r7, String r9, String r10, int r11, int r12, long r13, boolean r15, TLRPC.BotApp r16, boolean r17, String r18, TLRPC.User r19, int r20, boolean r21, boolean r22) {
        WebViewRequestProps r3 = new WebViewRequestProps();
        r3.currentAccount = r4;
        r3.peerId = r5;
        r3.botId = r7;
        r3.buttonText = r9;
        r3.buttonUrl = r10;
        r3.type = r11;
        r3.replyToMsgId = r12;
        r3.monoforumTopicId = r13;
        r3.silent = r15;
        r3.app = r16;
        r3.allowWrite = r17;
        r3.startParam = r18;
        r3.botUser = r19;
        r3.flags = r20;
        r3.compact = r21;
        r3.fullscreen = r22;
        if (r21 == true) goto L11;
        if (r22 == true) goto L11;
        if (TextUtils.isEmpty(r10) == true) goto L11;
        Uri r42 = Uri.parse(r10);     // Catch: Exception -> L9
        r3.compact = TextUtils.equals(r42.getQueryParameter("mode"), "compact");     // Catch: Exception -> L9
        r3.fullscreen = TextUtils.equals(r42.getQueryParameter("mode"), "fullscreen");     // Catch: Exception -> L9
        return r3;
    L9:
        e = move-exception;
        FileLog.e(e);
    L11:
        return r3;
    }

    public void applyResponse(TLObject r3) {
        this.response = r3;
        this.responseTime = System.currentTimeMillis();
    }

    public boolean equals(Object r9) {
        if ((r9 instanceof WebViewRequestProps) == true) goto L5;
        return false;
    L5:
        WebViewRequestProps r92 = (WebViewRequestProps) r9;
        if (this.currentAccount == r92.currentAccount) goto L8;
    L47:
        return false;
    L8:
        if (this.peerId != r92.peerId) goto L47;
        if (this.botId != r92.botId) goto L47;
        if (TextUtils.equals(this.buttonUrl, r92.buttonUrl) == false) goto L47;
        if (this.type != r92.type) goto L47;
        if (this.replyToMsgId != r92.replyToMsgId) goto L47;
        if (this.silent != r92.silent) goto L47;
        TLRPC.BotApp r0 = this.app;
        long r2 = 0;
        if (r0 != null) goto L22;
        long r4 = 0;
    L23:
        TLRPC.BotApp r02 = r92.app;
        if (r02 != null) goto L26;
        long r6 = 0;
    L28:
        if (r4 != r6) goto L47;
        if (this.allowWrite != r92.allowWrite) goto L47;
        if (TextUtils.equals(this.startParam, r92.startParam) == false) goto L47;
        TLRPC.User r03 = this.botUser;
        if (r03 != null) goto L36;
        long r42 = 0;
    L37:
        TLRPC.User r04 = r92.botUser;
        if (r04 == null) goto L42;
        r2 = r04.id;
    L42:
        if (r42 != r2) goto L47;
        if (this.flags != r92.flags) goto L47;
        return true;
    L36:
        r42 = r03.id;
        goto L37
    L26:
        r6 = r02.id;
        goto L28
    L22:
        r4 = r0.id;
        goto L23
    }
}
