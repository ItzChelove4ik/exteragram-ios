package org.telegram.ui.Components.Premium.boosts;

import androidx.core.util.Consumer;
import com.android.billingclient.api.BillingResult;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class BoostRepository$$ExternalSyntheticLambda39 implements Consumer {
    public final /* synthetic */ BillingResult f$0;
    public final /* synthetic */ Utilities.Callback f$1;

    public /* synthetic */ BoostRepository$$ExternalSyntheticLambda39(BillingResult r1, Utilities.Callback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.core.util.Consumer
    public final void accept(Object r2) {
        BoostRepository.$r8$lambda$_w_gaUlJUXZZLyliJu8K231X2jM(this.f$0, this.f$1, (BillingResult) r2);
    }
}
