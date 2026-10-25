package org.telegram.ui.Stories;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public final /* synthetic */ class StoriesController$$ExternalSyntheticLambda29 implements Runnable {
    public final /* synthetic */ StoriesController f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ Utilities.Callback f$4;
    public final /* synthetic */ Theme.ResourcesProvider f$5;

    public /* synthetic */ StoriesController$$ExternalSyntheticLambda29(StoriesController r1, TLRPC.TL_error r2, boolean r3, long r4, Utilities.Callback r6, Theme.ResourcesProvider r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StoriesController.m19698$r8$lambda$trdgmIXA0feSLBYcFeHbyrRTmU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
