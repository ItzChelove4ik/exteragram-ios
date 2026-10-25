package org.telegram.ui.Components;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class PostsSearchContainer$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ PostsSearchContainer f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ MessagesController f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ TLRPC.TL_channels_searchPosts f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ long f$6;
    public final /* synthetic */ TLRPC.TL_error f$7;
    public final /* synthetic */ ConnectionsManager f$8;

    public /* synthetic */ PostsSearchContainer$$ExternalSyntheticLambda8(PostsSearchContainer r1, TLObject r2, MessagesController r3, boolean r4, TLRPC.TL_channels_searchPosts r5, boolean r6, long r7, TLRPC.TL_error r9, ConnectionsManager r10) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r9;
        this.f$8 = r10;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PostsSearchContainer.$r8$lambda$ds6j5BLpUM23OTvpAHI3j57zNF4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8);
    }
}
