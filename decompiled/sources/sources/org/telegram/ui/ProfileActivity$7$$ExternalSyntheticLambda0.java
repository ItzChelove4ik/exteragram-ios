package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$7$$ExternalSyntheticLambda0 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ ProfileActivity.AnonymousClass7 f$0;
    public final /* synthetic */ TLRPC.User f$1;

    public /* synthetic */ ProfileActivity$7$$ExternalSyntheticLambda0(ProfileActivity.AnonymousClass7 r1, TLRPC.User r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r2, int r3) {
        ProfileActivity.AnonymousClass7.m18226$r8$lambda$FVgxh_GEl5tsyTQtpqIdR8w3PQ(this.f$0, this.f$1, r2, r3);
    }
}
