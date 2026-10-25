package org.telegram.ui.iv;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.ui.iv.RichEditorListView;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertRichLayout$2$$ExternalSyntheticLambda0 implements Utilities.Callback {
    public final /* synthetic */ RichEditorListView.SelectionEdit f$0;

    public /* synthetic */ ChatAttachAlertRichLayout$2$$ExternalSyntheticLambda0(RichEditorListView.SelectionEdit r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r1) {
        this.f$0.replaceWith((TL_iv.RichMessage) r1);
    }
}
