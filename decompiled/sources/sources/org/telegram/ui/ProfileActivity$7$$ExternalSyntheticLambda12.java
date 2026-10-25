package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$7$$ExternalSyntheticLambda12 implements Runnable {
    public final /* synthetic */ ProfileActivity.AnonymousClass7 f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ TLRPC.TL_chatAdminRights f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ DialogsActivity f$5;

    public /* synthetic */ ProfileActivity$7$$ExternalSyntheticLambda12(ProfileActivity.AnonymousClass7 r1, long r2, TLRPC.TL_chatAdminRights r4, String r5, boolean r6, DialogsActivity r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ProfileActivity.AnonymousClass7.$r8$lambda$rmSKbjlNWAaFIE2jv_SjOY3GeoM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
