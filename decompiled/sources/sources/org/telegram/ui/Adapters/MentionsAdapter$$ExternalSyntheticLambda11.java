package org.telegram.ui.Adapters;

import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class MentionsAdapter$$ExternalSyntheticLambda11 implements Runnable {
    public final /* synthetic */ MentionsAdapter f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ TLObject f$3;
    public final /* synthetic */ TLRPC.User f$4;
    public final /* synthetic */ String f$5;
    public final /* synthetic */ MessagesStorage f$6;
    public final /* synthetic */ String f$7;

    public /* synthetic */ MentionsAdapter$$ExternalSyntheticLambda11(MentionsAdapter r1, String r2, boolean r3, TLObject r4, TLRPC.User r5, String r6, MessagesStorage r7, String r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MentionsAdapter.$r8$lambda$cGyIfPlwaPcg3JLejiBMS9VV2_k(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}
