package org.telegram.ui.Cells;

import org.telegram.ui.Components.RLottieDrawable;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActionCell$$ExternalSyntheticLambda13 implements Runnable {
    public final /* synthetic */ RLottieDrawable f$0;

    public /* synthetic */ ChatActionCell$$ExternalSyntheticLambda13(RLottieDrawable r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.restart();
    }
}
