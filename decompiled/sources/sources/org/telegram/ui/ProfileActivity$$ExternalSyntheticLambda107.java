package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ContactAddActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$$ExternalSyntheticLambda107 implements ContactAddActivity.ContactAddActivityDelegate {
    public final /* synthetic */ ProfileActivity f$0;
    public final /* synthetic */ TLRPC.User f$1;

    public /* synthetic */ ProfileActivity$$ExternalSyntheticLambda107(ProfileActivity r1, TLRPC.User r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ContactAddActivity.ContactAddActivityDelegate
    public final void didAddToContacts() {
        ProfileActivity.m17842$r8$lambda$r6J9qD0_NXhyDCMUavQQRrjwNg(this.f$0, this.f$1);
    }
}
