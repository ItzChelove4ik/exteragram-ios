package org.telegram.ui.iv;

/* loaded from: classes7.dex */
public final /* synthetic */ class RichEditorListView$2$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ RichEditText f$0;

    public /* synthetic */ RichEditorListView$2$$ExternalSyntheticLambda2(RichEditText r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.requestEditFocusRebuild();
    }
}
