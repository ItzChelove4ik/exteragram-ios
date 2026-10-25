package org.telegram.ui.Components.Premium.boosts;

import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public final /* synthetic */ class UserSelectorBottomSheet$$ExternalSyntheticLambda12 implements Utilities.Callback {
    public final /* synthetic */ UserSelectorBottomSheet f$0;

    public /* synthetic */ UserSelectorBottomSheet$$ExternalSyntheticLambda12(UserSelectorBottomSheet r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        this.f$0.dismiss(((Boolean) r1).booleanValue());
    }
}
