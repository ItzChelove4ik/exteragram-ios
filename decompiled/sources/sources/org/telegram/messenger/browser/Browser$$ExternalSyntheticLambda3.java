package org.telegram.messenger.browser;

import android.content.Context;
import android.net.Uri;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes5.dex */
public final /* synthetic */ class Browser$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ Browser.Progress f$0;
    public final /* synthetic */ AlertDialog[] f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ Uri f$4;
    public final /* synthetic */ Context f$5;
    public final /* synthetic */ boolean f$6;

    public /* synthetic */ Browser$$ExternalSyntheticLambda3(Browser.Progress r1, AlertDialog[] r2, TLObject r3, int r4, Uri r5, Context r6, boolean r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Browser.m5054$r8$lambda$r0CCMiFnrGCwmVQpGqtZgRrTxo(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
