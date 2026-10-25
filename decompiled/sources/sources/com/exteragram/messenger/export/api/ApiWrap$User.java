package com.exteragram.messenger.export.api;

import okhttp3.internal.url._UrlKt;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public class ApiWrap$User {
    public Long bareId;
    public int colorIndex;
    public Long id;
    public ApiWrap$ContactInfo info;
    public TLRPC.InputUser input;
    public boolean isBot;
    public boolean isReplies;
    public boolean isSelf;
    public boolean isVerifyCodes;
    public String username;

    public ApiWrap$User() {
        this.bareId = 0L;
        this.colorIndex = 0;
        this.isBot = false;
        this.isSelf = false;
        this.isReplies = false;
        this.isVerifyCodes = false;
        this.input = new TLRPC.TL_inputUserEmpty();
    }

    public String name() {
        String r0 = this.info.firstName;
        if (r0 != null) goto L5;
    L16:
        String r02 = this.info.lastName;
        if (r02 != null) goto L19;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L19:
        if (r02.isEmpty() == false) goto L22;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L22:
        return this.info.lastName;
    L5:
        if (r0.isEmpty() == true) goto L16;
        String r03 = this.info.lastName;
        if (r03 == null) goto L15;
        if (r03.isEmpty() == true) goto L15;
        return this.info.firstName + " " + this.info.lastName;
    L15:
        return this.info.firstName;
    }
}
