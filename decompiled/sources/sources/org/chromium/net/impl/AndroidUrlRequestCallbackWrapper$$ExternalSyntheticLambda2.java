package org.chromium.net.impl;

import android.net.http.UrlResponseInfo;
import org.chromium.net.impl.CronetExceptionTranslationUtils;

/* loaded from: classes5.dex */
public final /* synthetic */ class AndroidUrlRequestCallbackWrapper$$ExternalSyntheticLambda2 implements CronetExceptionTranslationUtils.CronetWork {
    public final /* synthetic */ AndroidUrlRequestCallbackWrapper f$0;
    public final /* synthetic */ UrlResponseInfo f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ AndroidUrlRequestCallbackWrapper$$ExternalSyntheticLambda2(AndroidUrlRequestCallbackWrapper r1, UrlResponseInfo r2, String r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.chromium.net.impl.CronetExceptionTranslationUtils.CronetWork
    public final Object run() {
        return AndroidUrlRequestCallbackWrapper.$r8$lambda$lfnCnZ7eGSbsnvDQaHpEwevkPZ0(this.f$0, this.f$1, this.f$2);
    }
}
