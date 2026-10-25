package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class LaunchActivity$$ExternalSyntheticLambda67 implements Utilities.Callback2 {
    public final /* synthetic */ LaunchActivity f$0;
    public final /* synthetic */ Runnable f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ LaunchActivity$$ExternalSyntheticLambda67(LaunchActivity r1, Runnable r2, int r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r3, Object r4) {
        LaunchActivity.m15618$r8$lambda$_CoK5frPQdm6MXs4fJk5DpVheM(this.f$0, this.f$1, this.f$2, (TLRPC.ChatInviteJoinResult) r3, (TLRPC.TL_error) r4);
    }
}
