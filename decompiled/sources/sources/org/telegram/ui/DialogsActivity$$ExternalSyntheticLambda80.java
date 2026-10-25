package org.telegram.ui;

import org.telegram.ui.Cells.UnconfirmedAuthHintCell;

/* loaded from: classes6.dex */
public final /* synthetic */ class DialogsActivity$$ExternalSyntheticLambda80 implements Runnable {
    public final /* synthetic */ UnconfirmedAuthHintCell f$0;

    public /* synthetic */ DialogsActivity$$ExternalSyntheticLambda80(UnconfirmedAuthHintCell r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.updateColors();
    }
}
