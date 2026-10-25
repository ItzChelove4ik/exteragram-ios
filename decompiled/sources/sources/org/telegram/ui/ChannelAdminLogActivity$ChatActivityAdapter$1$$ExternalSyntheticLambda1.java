package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.AvatarPreviewer;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.ChannelAdminLogActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChannelAdminLogActivity$ChatActivityAdapter$1$$ExternalSyntheticLambda1 implements AvatarPreviewer.Callback {
    public final /* synthetic */ ChannelAdminLogActivity.ChatActivityAdapter.AnonymousClass1 f$0;
    public final /* synthetic */ ChatMessageCell f$1;
    public final /* synthetic */ TLRPC.User f$2;

    public /* synthetic */ ChannelAdminLogActivity$ChatActivityAdapter$1$$ExternalSyntheticLambda1(ChannelAdminLogActivity.ChatActivityAdapter.AnonymousClass1 r1, ChatMessageCell r2, TLRPC.User r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.AvatarPreviewer.Callback
    public final void onMenuClick(AvatarPreviewer.MenuItem r3) {
        ChannelAdminLogActivity.ChatActivityAdapter.AnonymousClass1.m7249$r8$lambda$gPv5BnIYihmo5uh74rTygcoy_Y(this.f$0, this.f$1, this.f$2, r3);
    }
}
