package com.exteragram.messenger.components;

import com.exteragram.messenger.components.SupporterBottomSheet;
import com.exteragram.messenger.pillstack.ui.pills.crypto.utils.ExchangeRates;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes4.dex */
public final /* synthetic */ class SupporterBottomSheet$$ExternalSyntheticLambda0 implements Utilities.Callback {
    public final /* synthetic */ SupporterBottomSheet f$0;
    public final /* synthetic */ float f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ BaseFragment f$3;
    public final /* synthetic */ SupporterBottomSheet.FeatureCell f$4;

    public /* synthetic */ SupporterBottomSheet$$ExternalSyntheticLambda0(SupporterBottomSheet r1, float r2, String r3, BaseFragment r4, SupporterBottomSheet.FeatureCell r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r7) {
        SupporterBottomSheet.$r8$lambda$BDeKMIHChXU4nflyukFGpgGSFoc(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (ExchangeRates.State) r7);
    }
}
