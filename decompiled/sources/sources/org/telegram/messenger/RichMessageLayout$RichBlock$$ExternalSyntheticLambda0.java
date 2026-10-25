package org.telegram.messenger;

import android.text.Spanned;
import java.util.Comparator;
import org.telegram.messenger.RichMessageLayout;
import org.telegram.ui.Cells.TextSelectionHelper;

/* loaded from: classes5.dex */
public final /* synthetic */ class RichMessageLayout$RichBlock$$ExternalSyntheticLambda0 implements Comparator {
    public final /* synthetic */ Spanned f$0;

    public /* synthetic */ RichMessageLayout$RichBlock$$ExternalSyntheticLambda0(Spanned r1) {
        this.f$0 = r1;
    }

    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return RichMessageLayout.RichBlock.$r8$lambda$Eafq4HGhd5N7a_h3ExMSg505OHs(this.f$0, (TextSelectionHelper.ReplaceCopyTextSpannable) r1, (TextSelectionHelper.ReplaceCopyTextSpannable) r2);
    }
}
