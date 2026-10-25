package org.telegram.ui;

import java.util.Comparator;
import org.telegram.messenger.MessagesController;
import org.telegram.ui.PremiumPreviewFragment;

/* loaded from: classes6.dex */
public final /* synthetic */ class PremiumPreviewFragment$$ExternalSyntheticLambda10 implements Comparator {
    public final /* synthetic */ MessagesController f$0;

    public /* synthetic */ PremiumPreviewFragment$$ExternalSyntheticLambda10(MessagesController r1) {
        this.f$0 = r1;
    }

    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return PremiumPreviewFragment.$r8$lambda$VGbvV68_UIGPz2pW65LE7kxOmig(this.f$0, (PremiumPreviewFragment.PremiumFeatureData) r1, (PremiumPreviewFragment.PremiumFeatureData) r2);
    }
}
