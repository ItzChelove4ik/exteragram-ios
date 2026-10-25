package org.telegram.ui.Components.Premium.boosts;

import java.util.HashSet;
import java.util.List;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stories;

/* loaded from: classes7.dex */
public final /* synthetic */ class ReassignBoostBottomSheet$$ExternalSyntheticLambda4 implements Utilities.Callback {
    public final /* synthetic */ ReassignBoostBottomSheet f$0;
    public final /* synthetic */ TL_stories.TL_premium_myBoosts f$1;
    public final /* synthetic */ List f$2;
    public final /* synthetic */ HashSet f$3;

    public /* synthetic */ ReassignBoostBottomSheet$$ExternalSyntheticLambda4(ReassignBoostBottomSheet r1, TL_stories.TL_premium_myBoosts r2, List r3, HashSet r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r4) {
        ReassignBoostBottomSheet.$r8$lambda$Qr1BJi1w80YER5I7tKPJga8D9Yk(this.f$0, this.f$1, this.f$2, this.f$3, (TL_stories.TL_premium_boostsStatus) r4);
    }
}
