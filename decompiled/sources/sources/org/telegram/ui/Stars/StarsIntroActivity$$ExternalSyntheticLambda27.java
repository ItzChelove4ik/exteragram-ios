package org.telegram.ui.Stars;

import android.content.Context;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ButtonSpan;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsIntroActivity$$ExternalSyntheticLambda27 implements Utilities.Callback {
    public final /* synthetic */ ButtonSpan.TextViewButtons f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ Context f$2;
    public final /* synthetic */ Theme.ResourcesProvider f$3;

    public /* synthetic */ StarsIntroActivity$$ExternalSyntheticLambda27(ButtonSpan.TextViewButtons r1, int r2, Context r3, Theme.ResourcesProvider r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r4) {
        StarsIntroActivity.$r8$lambda$fQMhWWgBnGNgmShghlmbb77XPAg(this.f$0, this.f$1, this.f$2, this.f$3, (TL_stars.SavedStarGift) r4);
    }
}
