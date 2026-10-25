package org.telegram.ui.iv;

import org.telegram.ui.Cells.TextSelectionHelper;
import org.telegram.ui.iv.RichDividerCell;

/* loaded from: classes7.dex */
public final /* synthetic */ class RichEditorListView$$ExternalSyntheticLambda37 implements RichDividerCell.Delegate {
    public final /* synthetic */ RichEditorListView f$0;

    public /* synthetic */ RichEditorListView$$ExternalSyntheticLambda37(RichEditorListView r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.iv.RichDividerCell.Delegate
    public final TextSelectionHelper.ArticleTextSelectionHelper getSelectionHelper() {
        return this.f$0.getTextSelectionHelper();
    }
}
