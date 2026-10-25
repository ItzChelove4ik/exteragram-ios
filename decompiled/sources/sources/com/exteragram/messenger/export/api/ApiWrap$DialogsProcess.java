package com.exteragram.messenger.export.api;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public class ApiWrap$DialogsProcess extends ApiWrap$ChatsProcess {
    public int offsetDate;
    public int offsetId;
    public TLRPC.InputPeer offsetPeer;
    public int splitIndexPlusOne;

    public ApiWrap$DialogsProcess() {
        this.splitIndexPlusOne = 0;
        this.offsetDate = 0;
        this.offsetId = 0;
        this.offsetPeer = new TLRPC.TL_inputPeerEmpty();
    }
}
