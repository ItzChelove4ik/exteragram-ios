package org.telegram.ui.Adapters;

import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Adapters.MentionsAdapter;

/* loaded from: classes6.dex */
public final /* synthetic */ class MentionsAdapter$4$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ MentionsAdapter.AnonymousClass4 f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ TLRPC.TL_error f$2;
    public final /* synthetic */ TLObject f$3;
    public final /* synthetic */ MessagesController f$4;
    public final /* synthetic */ MessagesStorage f$5;

    public /* synthetic */ MentionsAdapter$4$$ExternalSyntheticLambda1(MentionsAdapter.AnonymousClass4 r1, String r2, TLRPC.TL_error r3, TLObject r4, MessagesController r5, MessagesStorage r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MentionsAdapter.AnonymousClass4.$r8$lambda$j5OOHN7dE9rTtizfKFMcUZy8t6g(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
