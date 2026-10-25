package org.telegram.ui;

import android.view.View;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.Premium.LimitPreviewView;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$$ExternalSyntheticLambda55 implements View.OnClickListener {
    public final /* synthetic */ LimitPreviewView f$0;
    public final /* synthetic */ TLRPC.UserFull f$1;
    public final /* synthetic */ Utilities.Callback f$2;

    public /* synthetic */ ProfileActivity$$ExternalSyntheticLambda55(LimitPreviewView r1, TLRPC.UserFull r2, Utilities.Callback r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r3) {
        ProfileActivity.$r8$lambda$CD6AUrgfcjJUSlEmlnEnQGD1Qgk(this.f$0, this.f$1, this.f$2, r3);
    }
}
