package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.Components.Premium.boosts.UserSelectorBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda81 implements Utilities.Callback {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ TL_stars.TL_starGiftUnique f$4;
    public final /* synthetic */ UserSelectorBottomSheet[] f$5;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda81(StarGiftSheet r1, int r2, int r3, int r4, TL_stars.TL_starGiftUnique r5, UserSelectorBottomSheet[] r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r8) {
        StarGiftSheet.$r8$lambda$7UEULjL5RtR1VVDLNoEMxOiLScQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, (Long) r8);
    }
}
