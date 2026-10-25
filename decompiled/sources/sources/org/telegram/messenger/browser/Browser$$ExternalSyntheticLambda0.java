package org.telegram.messenger.browser;

import android.content.Context;
import android.net.Uri;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes5.dex */
public final /* synthetic */ class Browser$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ Browser.Progress f$0;
    public final /* synthetic */ AlertDialog[] f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ Uri f$3;
    public final /* synthetic */ Context f$4;
    public final /* synthetic */ boolean f$5;

    public /* synthetic */ Browser$$ExternalSyntheticLambda0(Browser.Progress r1, AlertDialog[] r2, int r3, Uri r4, Context r5, boolean r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        Browser.$r8$lambda$I69t2FOnvIYhXPGI7rWZYPnBJms(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9, r10);
    }
}
