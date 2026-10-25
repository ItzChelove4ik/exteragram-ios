package com.exteragram.messenger.export.api;

import okhttp3.internal.url._UrlKt;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public class ApiWrap$Chat {
    public long bareId;
    public int colorIndex;
    public boolean hasMonoforumAdminRights;
    public TLRPC.InputPeer input;
    public boolean isBroadcast;
    public boolean isMonoforum;
    public boolean isMonoforumAdmin;
    public boolean isMonoforumOfPublicBroadcast;
    public boolean isSupergroup;
    public long migratedToChannelId;
    public TLRPC.InputPeer monoforumBroadcastInput;
    public long monoforumLinkId;
    public String title;
    public String username;

    public ApiWrap$Chat() {
        this.bareId = 0;
        this.migratedToChannelId = 0;
        this.title = _UrlKt.FRAGMENT_ENCODE_SET;
        this.username = _UrlKt.FRAGMENT_ENCODE_SET;
        this.colorIndex = 0;
        this.isBroadcast = false;
        this.isSupergroup = false;
        this.isMonoforum = false;
        this.input = new TLRPC.TL_inputPeerEmpty();
        this.monoforumBroadcastInput = new TLRPC.TL_inputPeerEmpty();
    }
}
