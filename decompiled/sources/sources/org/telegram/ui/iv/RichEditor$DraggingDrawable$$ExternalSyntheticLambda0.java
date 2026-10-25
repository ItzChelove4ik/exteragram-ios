package org.telegram.ui.iv;

import org.telegram.ui.iv.RichEditor;

/* loaded from: classes7.dex */
public final /* synthetic */ class RichEditor$DraggingDrawable$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ RichEditor.DraggingDrawable f$0;

    public /* synthetic */ RichEditor$DraggingDrawable$$ExternalSyntheticLambda0(RichEditor.DraggingDrawable r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.invalidateSelf();
    }
}
