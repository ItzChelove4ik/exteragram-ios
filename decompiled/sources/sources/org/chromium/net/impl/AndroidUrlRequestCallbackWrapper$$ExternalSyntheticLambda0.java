package org.chromium.net.impl;

import android.net.http.UrlResponseInfo;
import org.chromium.net.impl.CronetExceptionTranslationUtils;

/* loaded from: classes5.dex */
public final /* synthetic */ class AndroidUrlRequestCallbackWrapper$$ExternalSyntheticLambda0 implements CronetExceptionTranslationUtils.CronetWork {
    public final /* synthetic */ AndroidUrlRequestCallbackWrapper f$0;
    public final /* synthetic */ UrlResponseInfo f$1;

    public /* synthetic */ AndroidUrlRequestCallbackWrapper$$ExternalSyntheticLambda0(AndroidUrlRequestCallbackWrapper r1, UrlResponseInfo r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.chromium.net.impl.CronetExceptionTranslationUtils.CronetWork
    public final Object run() {
        return AndroidUrlRequestCallbackWrapper.$r8$lambda$g4UEVjfEGVPQs1LW7r7pKONVtkI(this.f$0, this.f$1);
    }
}
