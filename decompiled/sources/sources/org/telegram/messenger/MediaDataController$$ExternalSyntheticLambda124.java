package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;

/* loaded from: classes5.dex */
public final /* synthetic */ class MediaDataController$$ExternalSyntheticLambda124 implements RequestDelegate {
    public final /* synthetic */ MediaDataController f$0;
    public final /* synthetic */ TLRPC.TL_messages_search f$1;
    public final /* synthetic */ int f$10;
    public final /* synthetic */ long f$11;
    public final /* synthetic */ long f$12;
    public final /* synthetic */ TLRPC.User f$13;
    public final /* synthetic */ TLRPC.Chat f$14;
    public final /* synthetic */ ReactionsLayoutInBubble.VisibleReaction f$15;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ int f$5;
    public final /* synthetic */ boolean f$6;
    public final /* synthetic */ long f$7;
    public final /* synthetic */ long f$8;
    public final /* synthetic */ int f$9;

    public /* synthetic */ MediaDataController$$ExternalSyntheticLambda124(MediaDataController r1, TLRPC.TL_messages_search r2, boolean r3, String r4, boolean r5, int r6, boolean r7, long r8, long r10, int r12, int r13, long r14, long r16, TLRPC.User r18, TLRPC.Chat r19, ReactionsLayoutInBubble.VisibleReaction r20) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r10;
        this.f$9 = r12;
        this.f$10 = r13;
        this.f$11 = r14;
        this.f$12 = r16;
        this.f$13 = r18;
        this.f$14 = r19;
        this.f$15 = r20;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r25, TLRPC.TL_error r26) {
        MediaDataController.$r8$lambda$1Ac5ipYTAwGLgQhnDgy2KP_KB3Y(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, this.f$12, this.f$13, this.f$14, this.f$15, r25, r26);
    }
}
