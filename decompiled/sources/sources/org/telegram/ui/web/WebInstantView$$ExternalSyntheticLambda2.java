package org.telegram.ui.web;

import android.graphics.Bitmap;
import org.telegram.messenger.Utilities;
import org.telegram.ui.web.WebInstantView;

/* loaded from: classes6.dex */
public final /* synthetic */ class WebInstantView$$ExternalSyntheticLambda2 implements Utilities.Callback {
    public final /* synthetic */ WebInstantView f$0;
    public final /* synthetic */ WebInstantView.WebPhoto f$1;

    public /* synthetic */ WebInstantView$$ExternalSyntheticLambda2(WebInstantView r1, WebInstantView.WebPhoto r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        WebInstantView.$r8$lambda$ZxGbwS_Ov01x4aNA2NENDWSdrrU(this.f$0, this.f$1, (Bitmap) r2);
    }
}
