package org.telegram.ui;

import android.view.View;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$ListAdapter$$ExternalSyntheticLambda5 implements View.OnClickListener {
    public final /* synthetic */ ProfileActivity.ListAdapter f$0;
    public final /* synthetic */ TLRPC.User f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ boolean f$5;

    public /* synthetic */ ProfileActivity$ListAdapter$$ExternalSyntheticLambda5(ProfileActivity.ListAdapter r1, TLRPC.User r2, String r3, boolean r4, boolean r5, boolean r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r8) {
        ProfileActivity.ListAdapter.m18230$r8$lambda$XYQ_VUzLB5kZ_JzEIHwq3ladcQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r8);
    }
}
