package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$7$$ExternalSyntheticLambda4 implements DialogsActivity.DialogsActivityDelegate {
    public final /* synthetic */ ProfileActivity.AnonymousClass7 f$0;
    public final /* synthetic */ TLRPC.User f$1;
    public final /* synthetic */ DialogsActivity f$2;

    public /* synthetic */ ProfileActivity$7$$ExternalSyntheticLambda4(ProfileActivity.AnonymousClass7 r1, TLRPC.User r2, DialogsActivity r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.DialogsActivity.DialogsActivityDelegate
    public final boolean didSelectDialogs(DialogsActivity r12, ArrayList r13, CharSequence r14, boolean r15, boolean r16, int r17, int r18, TopicsFragment r19) {
        return ProfileActivity.AnonymousClass7.$r8$lambda$Pr9vaL7sfJlJP8_Z3NA6HZLO7Ck(this.f$0, this.f$1, this.f$2, r12, r13, r14, r15, r16, r17, r18, r19);
    }
}
