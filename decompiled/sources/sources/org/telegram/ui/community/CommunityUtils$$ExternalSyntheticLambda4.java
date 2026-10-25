package org.telegram.ui.community;

import org.telegram.messenger.MessagesStorage;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class CommunityUtils$$ExternalSyntheticLambda4 implements MessagesStorage.LongCallback {
    public final /* synthetic */ AlertDialog f$0;
    public final /* synthetic */ BaseFragment f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ boolean f$4;

    public /* synthetic */ CommunityUtils$$ExternalSyntheticLambda4(AlertDialog r1, BaseFragment r2, int r3, long r4, boolean r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
    }

    @Override // org.telegram.messenger.MessagesStorage.LongCallback
    public final void run(long r9) {
        CommunityUtils.m21561$r8$lambda$L38f6i3Rz4OotbEnUhizO9g0eQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r9);
    }
}
