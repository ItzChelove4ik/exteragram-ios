package org.telegram.ui.Components;

import java.util.List;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatActivityEnterView$$ExternalSyntheticLambda124 implements Utilities.Callback {
    public final /* synthetic */ ChatActivityEnterView f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ TLRPC.User f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ ChatActivityEnterView$$ExternalSyntheticLambda124(ChatActivityEnterView r1, AlertDialog r2, TLRPC.User r3, boolean r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r4) {
        ChatActivityEnterView.m9135$r8$lambda$YhdNK8MNgZuqisgE4q859d2UY(this.f$0, this.f$1, this.f$2, this.f$3, (List) r4);
    }
}
