package org.telegram.ui.Components;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class PostsSearchContainer$$ExternalSyntheticLambda4 implements RequestDelegate {
    public final /* synthetic */ PostsSearchContainer f$0;
    public final /* synthetic */ MessagesController f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ TLRPC.TL_channels_searchPosts f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ long f$5;
    public final /* synthetic */ ConnectionsManager f$6;

    public /* synthetic */ PostsSearchContainer$$ExternalSyntheticLambda4(PostsSearchContainer r1, MessagesController r2, boolean r3, TLRPC.TL_channels_searchPosts r4, boolean r5, long r6, ConnectionsManager r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r8;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r11, TLRPC.TL_error r12) {
        PostsSearchContainer.$r8$lambda$_j57DbqOh80NCH8c0tgeaFlGK1I(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r11, r12);
    }
}
