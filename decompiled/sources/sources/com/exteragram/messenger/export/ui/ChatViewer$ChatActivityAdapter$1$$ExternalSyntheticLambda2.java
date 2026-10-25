package com.exteragram.messenger.export.ui;

import com.exteragram.messenger.export.ui.ChatViewer;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.AvatarPreviewer;
import org.telegram.ui.Cells.ChatMessageCell;

/* loaded from: classes4.dex */
public final /* synthetic */ class ChatViewer$ChatActivityAdapter$1$$ExternalSyntheticLambda2 implements AvatarPreviewer.Callback {
    public final /* synthetic */ ChatViewer.ChatActivityAdapter.AnonymousClass1 f$0;
    public final /* synthetic */ ChatMessageCell f$1;
    public final /* synthetic */ TLRPC.User f$2;

    public /* synthetic */ ChatViewer$ChatActivityAdapter$1$$ExternalSyntheticLambda2(ChatViewer.ChatActivityAdapter.AnonymousClass1 r1, ChatMessageCell r2, TLRPC.User r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.AvatarPreviewer.Callback
    public final void onMenuClick(AvatarPreviewer.MenuItem r3) {
        ChatViewer.ChatActivityAdapter.AnonymousClass1.$r8$lambda$vztG8_418NVINPYO0rg_2cIfWe4(this.f$0, this.f$1, this.f$2, r3);
    }
}
