package org.telegram.ui.Adapters;

import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class MentionsAdapter$$ExternalSyntheticLambda8 implements RequestDelegate {
    public final /* synthetic */ MentionsAdapter f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ TLRPC.User f$3;
    public final /* synthetic */ String f$4;
    public final /* synthetic */ MessagesStorage f$5;
    public final /* synthetic */ String f$6;

    public /* synthetic */ MentionsAdapter$$ExternalSyntheticLambda8(MentionsAdapter r1, String r2, boolean r3, TLRPC.User r4, String r5, MessagesStorage r6, String r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r10, TLRPC.TL_error r11) {
        MentionsAdapter.m5906$r8$lambda$y15SNNPIAPqL9JKgBB4gxgjN0M(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r10, r11);
    }
}
