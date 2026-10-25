package org.telegram.ui.Stories.recorder;

import android.location.Location;
import org.telegram.messenger.Utilities;

/* loaded from: classes3.dex */
public final /* synthetic */ class Weather$$ExternalSyntheticLambda2 implements Utilities.Callback {
    public final /* synthetic */ Utilities.Callback f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ Weather$$ExternalSyntheticLambda2(Utilities.Callback r1, boolean r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        Weather.m20596$r8$lambda$t3ZkHizLhHzQ8gS5sVl0ruSWok(this.f$0, this.f$1, (Location) r2);
    }
}
