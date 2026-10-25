package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.SessionBottomSheet;

/* loaded from: classes6.dex */
public final /* synthetic */ class SessionBottomSheet$8$$ExternalSyntheticLambda0 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ SessionBottomSheet.AnonymousClass8 f$0;
    public final /* synthetic */ SessionBottomSheet.Callback f$1;
    public final /* synthetic */ TLRPC.TL_authorization f$2;

    public /* synthetic */ SessionBottomSheet$8$$ExternalSyntheticLambda0(SessionBottomSheet.AnonymousClass8 r1, SessionBottomSheet.Callback r2, TLRPC.TL_authorization r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r3, int r4) {
        SessionBottomSheet.AnonymousClass8.$r8$lambda$nEa_QGrQN0IxNu6MfEnLyBzA3jw(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
