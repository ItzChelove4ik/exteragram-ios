package org.telegram.ui.iv;

/* loaded from: classes7.dex */
public final /* synthetic */ class RichEditorListView$$ExternalSyntheticLambda10 implements Runnable {
    public final /* synthetic */ RichTextCell f$0;

    public /* synthetic */ RichEditorListView$$ExternalSyntheticLambda10(RichTextCell r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.persistStyle();
    }
}
