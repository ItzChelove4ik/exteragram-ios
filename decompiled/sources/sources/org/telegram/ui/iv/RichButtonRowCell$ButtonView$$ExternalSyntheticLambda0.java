package org.telegram.ui.iv;

import org.telegram.ui.iv.RichButtonRowCell;

/* loaded from: classes7.dex */
public final /* synthetic */ class RichButtonRowCell$ButtonView$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ RichButtonRowCell.ButtonView f$0;

    public /* synthetic */ RichButtonRowCell$ButtonView$$ExternalSyntheticLambda0(RichButtonRowCell.ButtonView r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.invalidate();
    }
}
