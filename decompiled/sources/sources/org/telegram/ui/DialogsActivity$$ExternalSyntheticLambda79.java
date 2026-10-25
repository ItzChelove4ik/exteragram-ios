package org.telegram.ui;

import org.telegram.ui.Cells.DialogsHintCell;

/* loaded from: classes6.dex */
public final /* synthetic */ class DialogsActivity$$ExternalSyntheticLambda79 implements Runnable {
    public final /* synthetic */ DialogsHintCell f$0;

    public /* synthetic */ DialogsActivity$$ExternalSyntheticLambda79(DialogsHintCell r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.updateColors();
    }
}
