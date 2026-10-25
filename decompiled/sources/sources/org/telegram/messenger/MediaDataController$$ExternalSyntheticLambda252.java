package org.telegram.messenger;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda252 implements Runnable {
    public final /* synthetic */ MediaDataController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ boolean f$10;
    public final /* synthetic */ ReactionsLayoutInBubble.VisibleReaction f$11;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TLRPC.TL_messages_search f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ int f$5;
    public final /* synthetic */ int f$6;
    public final /* synthetic */ long f$7;
    public final /* synthetic */ TLRPC.User f$8;
    public final /* synthetic */ TLRPC.Chat f$9;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda252(MediaDataController r1, long r2, TLObject r4, TLRPC.TL_messages_search r5, long r6, int r8, int r9, long r10, TLRPC.User r12, TLRPC.Chat r13, boolean r14, ReactionsLayoutInBubble.VisibleReaction r15) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r8;
        this.f$6 = r9;
        this.f$7 = r10;
        this.f$8 = r12;
        this.f$9 = r13;
        this.f$10 = r14;
        this.f$11 = r15;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaDataController.$r8$lambda$5xyB2T9b3dGaP_bEgHoNPULCccc(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11);
    }
}
