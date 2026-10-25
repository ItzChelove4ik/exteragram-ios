package org.telegram.ui;

import android.view.View;
import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class DialogsActivity$$ExternalSyntheticLambda54 implements View.OnClickListener {
    public final /* synthetic */ DialogsActivity f$0;
    public final /* synthetic */ MessagesController.DialogFilter f$1;
    public final /* synthetic */ TLRPC.Dialog f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ DialogsActivity$$ExternalSyntheticLambda54(DialogsActivity r1, MessagesController.DialogFilter r2, TLRPC.Dialog r3, long r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r7) {
        DialogsActivity.$r8$lambda$ACll39VR8LZrdOijXMA05aPNkEo(this.f$0, this.f$1, this.f$2, this.f$3, r7);
    }
}
