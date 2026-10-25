package org.telegram.ui;

import android.content.Context;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes6.dex */
public final /* synthetic */ class OAuthSheet$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ TLRPC.TL_urlAuthResultRequest f$0;
    public final /* synthetic */ String[] f$1;
    public final /* synthetic */ Context f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ Runnable f$4;
    public final /* synthetic */ BaseFragment f$5;

    public /* synthetic */ OAuthSheet$$ExternalSyntheticLambda9(TLRPC.TL_urlAuthResultRequest r1, String[] r2, Context r3, int r4, Runnable r5, BaseFragment r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        OAuthSheet.$r8$lambda$6xPbgwiQuEyzUNYJAAB_K6HFjFM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
