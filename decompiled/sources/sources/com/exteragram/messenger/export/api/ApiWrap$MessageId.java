package com.exteragram.messenger.export.api;

import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public class ApiWrap$MessageId {
    public String didAndMsgId;

    public ApiWrap$MessageId() {
        this.didAndMsgId = _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof ApiWrap$MessageId) == true) goto L5;
        return false;
    L5:
        return Objects.equals(((ApiWrap$MessageId) r2).didAndMsgId, this.didAndMsgId);
    }

    public int hashCode() {
        return this.didAndMsgId.hashCode();
    }
}
