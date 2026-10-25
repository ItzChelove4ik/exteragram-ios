package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$$ExternalSyntheticLambda173 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ ProfileActivity f$0;
    public final /* synthetic */ TLRPC.UserFull f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ ProfileActivity$$ExternalSyntheticLambda173(ProfileActivity r1, TLRPC.UserFull r2, int r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r3, int r4) {
        ProfileActivity.$r8$lambda$RuuVoEuXv1j3n5wcgWygncnunFs(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
