package org.telegram.messenger;

import android.text.Spanned;
import java.util.Comparator;
import org.telegram.messenger.RichMessageLayout;

/* loaded from: classes5.dex */
public final /* synthetic */ class RichMessageLayout$Text$$ExternalSyntheticLambda3 implements Comparator {
    public final /* synthetic */ Spanned f$0;

    public /* synthetic */ RichMessageLayout$Text$$ExternalSyntheticLambda3(Spanned r1) {
        this.f$0 = r1;
    }

    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return RichMessageLayout.Text.$r8$lambda$8kZ87wDke8KEzxFP1RMnCjBCZ3k(this.f$0, (RichMessageLayout.RichButtonSpan) r1, (RichMessageLayout.RichButtonSpan) r2);
    }
}
