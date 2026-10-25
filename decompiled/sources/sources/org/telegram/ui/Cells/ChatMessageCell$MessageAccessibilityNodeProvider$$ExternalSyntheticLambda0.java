package org.telegram.ui.Cells;

import android.text.Spanned;
import java.util.Comparator;
import org.telegram.messenger.CodeHighlighting;
import org.telegram.ui.Cells.ChatMessageCell;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatMessageCell$MessageAccessibilityNodeProvider$$ExternalSyntheticLambda0 implements Comparator {
    public final /* synthetic */ Spanned f$0;

    public /* synthetic */ ChatMessageCell$MessageAccessibilityNodeProvider$$ExternalSyntheticLambda0(Spanned r1) {
        this.f$0 = r1;
    }

    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return ChatMessageCell.MessageAccessibilityNodeProvider.$r8$lambda$ys4Wv4_xsobPzyL8iEli8AfAHao(this.f$0, (CodeHighlighting.Span) r1, (CodeHighlighting.Span) r2);
    }
}
