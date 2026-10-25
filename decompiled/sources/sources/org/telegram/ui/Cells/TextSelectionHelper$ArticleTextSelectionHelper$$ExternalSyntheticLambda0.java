package org.telegram.ui.Cells;

import android.text.SpannableStringBuilder;
import java.util.Comparator;
import org.telegram.ui.Cells.TextSelectionHelper;

/* loaded from: classes6.dex */
public final /* synthetic */ class TextSelectionHelper$ArticleTextSelectionHelper$$ExternalSyntheticLambda0 implements Comparator {
    public final /* synthetic */ SpannableStringBuilder f$0;

    public /* synthetic */ TextSelectionHelper$ArticleTextSelectionHelper$$ExternalSyntheticLambda0(SpannableStringBuilder r1) {
        this.f$0 = r1;
    }

    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return TextSelectionHelper.ArticleTextSelectionHelper.$r8$lambda$uquVZVcR300YvrtjLb0hkpKCzWo(this.f$0, (TextSelectionHelper.ReplaceCopyTextSpannable) r1, (TextSelectionHelper.ReplaceCopyTextSpannable) r2);
    }
}
