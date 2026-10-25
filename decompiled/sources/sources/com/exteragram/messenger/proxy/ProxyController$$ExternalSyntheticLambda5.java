package com.exteragram.messenger.proxy;

import com.exteragram.messenger.proxy.IpAddressInfoController;
import com.exteragram.messenger.proxy.ProxyController;
import org.telegram.messenger.Utilities;

/* loaded from: classes4.dex */
public final /* synthetic */ class ProxyController$$ExternalSyntheticLambda5 implements Utilities.Callback {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ ProxyController.ProxyCountryCallback f$1;

    public /* synthetic */ ProxyController$$ExternalSyntheticLambda5(String r1, ProxyController.ProxyCountryCallback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        ProxyController.$r8$lambda$oRbqtd6yYrrPwcGrsMaifSCNU90(this.f$0, this.f$1, (IpAddressInfoController.IpAddressInfo) r2);
    }
}
