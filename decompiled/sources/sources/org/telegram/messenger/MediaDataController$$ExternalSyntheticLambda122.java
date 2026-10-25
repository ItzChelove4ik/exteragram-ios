package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda122 implements RequestDelegate {
    public final /* synthetic */ MediaDataController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ ReactionsLayoutInBubble.VisibleReaction f$10;
    public final /* synthetic */ TLRPC.TL_messages_search f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ int f$5;
    public final /* synthetic */ long f$6;
    public final /* synthetic */ TLRPC.User f$7;
    public final /* synthetic */ TLRPC.Chat f$8;
    public final /* synthetic */ boolean f$9;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda122(MediaDataController r1, long r2, TLRPC.TL_messages_search r4, long r5, int r7, int r8, long r9, TLRPC.User r11, TLRPC.Chat r12, boolean r13, ReactionsLayoutInBubble.VisibleReaction r14) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r7;
        this.f$5 = r8;
        this.f$6 = r9;
        this.f$7 = r11;
        this.f$8 = r12;
        this.f$9 = r13;
        this.f$10 = r14;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r17, TLRPC.TL_error r18) {
        MediaDataController.$r8$lambda$_wg_9CBiATJuYEa4BRradvrp8hI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, r17, r18);
    }
}
