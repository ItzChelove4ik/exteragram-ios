package org.telegram.ui.Cells;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActionCell$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ ChatActionCell f$0;

    public /* synthetic */ ChatActionCell$$ExternalSyntheticLambda2(ChatActionCell r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.invalidateOutbounds();
    }
}
