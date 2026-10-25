package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$7$$ExternalSyntheticLambda11 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ ProfileActivity.AnonymousClass7 f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ DialogsActivity f$2;
    public final /* synthetic */ TLRPC.User f$3;

    public /* synthetic */ ProfileActivity$7$$ExternalSyntheticLambda11(ProfileActivity.AnonymousClass7 r1, long r2, DialogsActivity r4, TLRPC.User r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r8, int r9) {
        ProfileActivity.AnonymousClass7.$r8$lambda$rpBlm8gJa5p0eRS7oLWeqDZwzUs(this.f$0, this.f$1, this.f$2, this.f$3, r8, r9);
    }
}
