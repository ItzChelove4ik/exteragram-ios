package org.telegram.messenger;

import android.app.Activity;
import android.view.View;
import com.exteragram.messenger.proxy.ProxyController;
import org.telegram.messenger.SharedConfig;
import org.telegram.utils.proxy.ProxySettings;

/* loaded from: classes5.dex */
public final /* synthetic */ class AndroidUtilities$$ExternalSyntheticLambda29 implements View.OnClickListener {
    public final /* synthetic */ ProxySettings f$0;
    public final /* synthetic */ ProxyController f$1;
    public final /* synthetic */ SharedConfig.ProxyInfo f$2;
    public final /* synthetic */ Activity f$3;
    public final /* synthetic */ Runnable f$4;

    public /* synthetic */ AndroidUtilities$$ExternalSyntheticLambda29(ProxySettings r1, ProxyController r2, SharedConfig.ProxyInfo r3, Activity r4, Runnable r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r7) {
        AndroidUtilities.m3871$r8$lambda$AXWadX_SG4DsFFzsoANjNwcQcI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r7);
    }
}
