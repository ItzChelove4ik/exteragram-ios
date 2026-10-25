package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ContactsActivity;

/* loaded from: classes3.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda23 implements ContactsActivity.ContactsActivityDelegate {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ int[] f$2;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda23(LaunchActivity r1, boolean r2, int[] r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.ContactsActivity.ContactsActivityDelegate
    public final void didSelectContact(TLRPC.User r7, String r8, ContactsActivity r9) {
        LaunchActivity.$r8$lambda$Qqa_1DC7uT7x8bPNwmjCh21txuU(this.f$0, this.f$1, this.f$2, r7, r8, r9);
    }
}
