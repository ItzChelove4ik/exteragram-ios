package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarGiftSheet$$ExternalSyntheticLambda8 implements Utilities.Callback {
    public final /* synthetic */ StarGiftSheet f$0;
    public final /* synthetic */ AlertDialog f$1;

    public /* synthetic */ StarGiftSheet$$ExternalSyntheticLambda8(StarGiftSheet r1, AlertDialog r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        StarGiftSheet.m18974$r8$lambda$VUo9_44WPzhd8PfDz5nWs0pCCg(this.f$0, this.f$1, (TL_stars.SavedStarGift) r2);
    }
}
