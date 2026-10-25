package org.telegram.messenger;

import android.text.SpannableStringBuilder;
import java.util.function.ToIntFunction;
import org.telegram.messenger.RichMessageLayout;

/* loaded from: classes5.dex */
public final /* synthetic */ class RichMessageLayout$$ExternalSyntheticLambda1 implements ToIntFunction {
    public final /* synthetic */ SpannableStringBuilder f$0;

    public /* synthetic */ RichMessageLayout$$ExternalSyntheticLambda1(SpannableStringBuilder r1) {
        this.f$0 = r1;
    }

    @Override // java.util.function.ToIntFunction
    public final int applyAsInt(Object r1) {
        return this.f$0.getSpanStart((RichMessageLayout.StyleSpan) r1);
    }
}
