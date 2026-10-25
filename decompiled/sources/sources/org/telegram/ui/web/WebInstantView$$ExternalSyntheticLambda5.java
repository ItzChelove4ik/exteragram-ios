package org.telegram.ui.web;

import android.webkit.ValueCallback;
import android.webkit.WebView;
import java.io.File;
import org.telegram.messenger.Utilities;

/* loaded from: classes6.dex */
public final /* synthetic */ class WebInstantView$$ExternalSyntheticLambda5 implements ValueCallback {
    public final /* synthetic */ WebInstantView f$0;
    public final /* synthetic */ WebView f$1;
    public final /* synthetic */ File f$2;
    public final /* synthetic */ Utilities.Callback f$3;

    public /* synthetic */ WebInstantView$$ExternalSyntheticLambda5(WebInstantView r1, WebView r2, File r3, Utilities.Callback r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // android.webkit.ValueCallback
    public final void onReceiveValue(Object r4) {
        WebInstantView.m22064$r8$lambda$8ciLZdT3_99FwTX3vjpVThD_Gc(this.f$0, this.f$1, this.f$2, this.f$3, (String) r4);
    }
}
