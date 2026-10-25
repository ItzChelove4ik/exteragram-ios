package org.telegram.ui.Components.Premium.boosts;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BulletinFactory;

/* loaded from: classes7.dex */
public final /* synthetic */ class BoostDialogs$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ BulletinFactory f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ TLRPC.Chat f$2;
    public final /* synthetic */ Theme.ResourcesProvider f$3;

    public /* synthetic */ BoostDialogs$$ExternalSyntheticLambda0(BulletinFactory r1, boolean r2, TLRPC.Chat r3, Theme.ResourcesProvider r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BoostDialogs.$r8$lambda$7DNp19UP3N1YlcuhgNvNug5jSww(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
