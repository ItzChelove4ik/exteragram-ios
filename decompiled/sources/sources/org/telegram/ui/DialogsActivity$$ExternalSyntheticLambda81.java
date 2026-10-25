package org.telegram.ui;

import org.telegram.ui.Cells.ActiveGiftAuctionsHintCell;

/* loaded from: classes6.dex */
public final /* synthetic */ class DialogsActivity$$ExternalSyntheticLambda81 implements Runnable {
    public final /* synthetic */ ActiveGiftAuctionsHintCell f$0;

    public /* synthetic */ DialogsActivity$$ExternalSyntheticLambda81(ActiveGiftAuctionsHintCell r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.updateColors();
    }
}
