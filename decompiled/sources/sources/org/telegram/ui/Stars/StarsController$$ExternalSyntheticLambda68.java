package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda68 implements Runnable {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TL_stars.InputSavedStarGift f$3;
    public final /* synthetic */ Utilities.Callback f$4;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda68(StarsController r1, AlertDialog r2, TLObject r3, TL_stars.InputSavedStarGift r4, Utilities.Callback r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StarsController.m19106$r8$lambda$752T9Mh6o4wMPwnBEBFH1HB7kY(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
