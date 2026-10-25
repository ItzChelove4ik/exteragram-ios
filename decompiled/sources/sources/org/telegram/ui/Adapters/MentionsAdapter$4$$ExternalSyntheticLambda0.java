package org.telegram.ui.Adapters;

import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Adapters.MentionsAdapter;

/* loaded from: classes6.dex */
public final /* synthetic */ class MentionsAdapter$4$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ MentionsAdapter.AnonymousClass4 f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ MessagesController f$2;
    public final /* synthetic */ MessagesStorage f$3;

    public /* synthetic */ MentionsAdapter$4$$ExternalSyntheticLambda0(MentionsAdapter.AnonymousClass4 r1, String r2, MessagesController r3, MessagesStorage r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        MentionsAdapter.AnonymousClass4.$r8$lambda$cFRs7nZxM1EZW_MoWKyLPas_nwA(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
