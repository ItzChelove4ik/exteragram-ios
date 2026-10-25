package com.exteragram.messenger.export.api;

import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public class ApiWrap$Peer {
    public ApiWrap$Chat chat;
    public ApiWrap$User user;

    public ApiWrap$Peer(ApiWrap$Chat r1) {
        this.chat = r1;
    }

    public ApiWrap$Peer(ApiWrap$User r1) {
        this.user = r1;
    }

    public int colorIndex() {
        ApiWrap$User r0 = this.user;
        if (r0 != null) goto L5;
        ApiWrap$Chat r1 = this.chat;
        if (r1 != null) goto L9;
        Segment$$ExternalSyntheticBUOutline1.m("both user and chat are null");
        return 0;
    L9:
        return r1.colorIndex;
    L5:
        return r0.colorIndex;
    }

    public TLRPC.InputPeer getInput() {
        ApiWrap$User r0 = this.user;
        if (r0 == null) goto L9;
        TLRPC.InputUser r02 = r0.input;
        if ((r02 instanceof TLRPC.TL_inputUser) == false) goto L9;
        TLRPC.TL_inputUser r03 = (TLRPC.TL_inputUser) r02;
        TLRPC.TL_inputPeerUser r3 = new TLRPC.TL_inputPeerUser();
        r3.user_id = r03.user_id;
        r3.access_hash = r03.access_hash;
        return r3;
    L9:
        return this.chat.input;
    }

    public long id() {
        ApiWrap$User r0 = this.user;
        if (r0 != null) goto L5;
        ApiWrap$Chat r2 = this.chat;
        if (r2 != null) goto L9;
        Segment$$ExternalSyntheticBUOutline1.m("both user and chat are null");
        return 0;
    L9:
        return r2.bareId;
    L5:
        return r0.info.userId.longValue();
    }

    public String name() {
        ApiWrap$User r0 = this.user;
        if (r0 != null) goto L5;
        ApiWrap$Chat r1 = this.chat;
        if (r1 != null) goto L9;
        Segment$$ExternalSyntheticBUOutline1.m("both user and chat are null");
        return null;
    L9:
        return r1.title;
    L5:
        return r0.name();
    }
}
