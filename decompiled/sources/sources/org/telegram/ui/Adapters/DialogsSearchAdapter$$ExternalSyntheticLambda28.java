package org.telegram.ui.Adapters;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes3.dex */
public final /* synthetic */ class DialogsSearchAdapter$$ExternalSyntheticLambda28 implements RequestDelegate {
    public final /* synthetic */ DialogsSearchAdapter f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ TLRPC.TL_messages_search f$4;

    public /* synthetic */ DialogsSearchAdapter$$ExternalSyntheticLambda28(DialogsSearchAdapter r1, String r2, int r3, int r4, TLRPC.TL_messages_search r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        DialogsSearchAdapter.$r8$lambda$bLtq8pS1k8Bu5WHu4RNqb1k6Kew(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
