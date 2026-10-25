package org.telegram.ui.community;

import org.telegram.messenger.MessagesStorage;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class CommunityCreateActivity$$ExternalSyntheticLambda8 implements MessagesStorage.LongCallback {
    public final /* synthetic */ CommunityCreateActivity f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ boolean f$3;

    public /* synthetic */ CommunityCreateActivity$$ExternalSyntheticLambda8(CommunityCreateActivity r1, AlertDialog r2, String r3, boolean r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.MessagesStorage.LongCallback
    public final void run(long r7) {
        CommunityCreateActivity.m21490$r8$lambda$3lvbWA7TkaQ0J3HyuD_ubgxY7s(this.f$0, this.f$1, this.f$2, this.f$3, r7);
    }
}
