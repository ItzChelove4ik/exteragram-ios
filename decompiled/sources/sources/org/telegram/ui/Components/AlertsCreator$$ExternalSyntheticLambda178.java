package org.telegram.ui.Components;

import android.content.Context;
import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda178 implements Runnable {
    public final /* synthetic */ Context f$0;
    public final /* synthetic */ TLRPC.Chat f$1;
    public final /* synthetic */ TLRPC.User f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ MessagesStorage.BooleanCallback f$4;
    public final /* synthetic */ Theme.ResourcesProvider f$5;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda178(Context r1, TLRPC.Chat r2, TLRPC.User r3, int r4, MessagesStorage.BooleanCallback r5, Theme.ResourcesProvider r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AlertsCreator.m8785$r8$lambda$wEKpmet6YCIKSgDxPEtJPn_w1I(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
