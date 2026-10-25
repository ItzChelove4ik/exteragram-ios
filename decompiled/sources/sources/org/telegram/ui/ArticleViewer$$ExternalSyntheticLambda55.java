package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ArticleViewer;

/* loaded from: classes6.dex */
public final /* synthetic */ class ArticleViewer$$ExternalSyntheticLambda55 implements Utilities.Callback2 {
    public final /* synthetic */ ArticleViewer.BlockChannelCell f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TLRPC.TL_channels_joinChannel f$2;
    public final /* synthetic */ TLRPC.Chat f$3;

    public /* synthetic */ ArticleViewer$$ExternalSyntheticLambda55(ArticleViewer.BlockChannelCell r1, int r2, TLRPC.TL_channels_joinChannel r3, TLRPC.Chat r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r7, Object r8) {
        ArticleViewer.$r8$lambda$aQ8sRKaQB_Wa760bRPk_fb89_P4(this.f$0, this.f$1, this.f$2, this.f$3, (TLRPC.ChatInviteJoinResult) r7, (TLRPC.TL_error) r8);
    }
}
