package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ChangeUsernameActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChangeUsernameActivity$2$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ ChangeUsernameActivity.AnonymousClass2 f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ TLRPC.TL_username f$4;
    public final /* synthetic */ boolean f$5;

    public /* synthetic */ ChangeUsernameActivity$2$$ExternalSyntheticLambda2(ChangeUsernameActivity.AnonymousClass2 r1, String r2, int r3, boolean r4, TLRPC.TL_username r5, boolean r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        ChangeUsernameActivity.AnonymousClass2.$r8$lambda$C_NFFtMcIqXgs_PPt7G0oCrwrxg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9, r10);
    }
}
