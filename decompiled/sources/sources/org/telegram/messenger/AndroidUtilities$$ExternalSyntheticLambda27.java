package org.telegram.messenger;

import com.exteragram.messenger.proxy.ProxyController;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public final /* synthetic */ class AndroidUtilities$$ExternalSyntheticLambda27 implements ProxyController.ProxyCountryCallback {
    public final /* synthetic */ WeakReference f$0;

    public /* synthetic */ AndroidUtilities$$ExternalSyntheticLambda27(WeakReference r1) {
        this.f$0 = r1;
    }

    @Override // com.exteragram.messenger.proxy.ProxyController.ProxyCountryCallback
    public final void onCountryResolved(String r1) {
        AndroidUtilities.$r8$lambda$YNeOyFI20rZ0iTOmA2utyLFP_fE(this.f$0, r1);
    }
}
