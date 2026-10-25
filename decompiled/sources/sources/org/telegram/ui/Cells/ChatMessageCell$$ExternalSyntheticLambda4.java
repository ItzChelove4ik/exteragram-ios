package org.telegram.ui.Cells;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatMessageCell$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ ChatMessageCell f$0;

    public /* synthetic */ ChatMessageCell$$ExternalSyntheticLambda4(ChatMessageCell r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.invalidateOutbounds();
    }
}
