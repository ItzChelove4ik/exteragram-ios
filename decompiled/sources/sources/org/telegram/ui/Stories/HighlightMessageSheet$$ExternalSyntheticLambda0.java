package org.telegram.ui.Stories;

import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Stars.StarsReactionsSheet;
import org.telegram.ui.Stories.HighlightMessageSheet;
import org.telegram.ui.Stories.LiveCommentsView;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public final /* synthetic */ class HighlightMessageSheet$$ExternalSyntheticLambda0 implements Utilities.Callback {
    public final /* synthetic */ long[] f$0;
    public final /* synthetic */ ButtonWithCounterView f$1;
    public final /* synthetic */ boolean[] f$10;
    public final /* synthetic */ ColoredImageSpan[] f$2;
    public final /* synthetic */ LiveCommentsView.Message f$3;
    public final /* synthetic */ LiveCommentsView.LiveCommentView f$4;
    public final /* synthetic */ int f$5;
    public final /* synthetic */ HighlightMessageSheet.TierValueView f$6;
    public final /* synthetic */ HighlightMessageSheet.TierValueView f$7;
    public final /* synthetic */ HighlightMessageSheet.TierValueView f$8;
    public final /* synthetic */ StarsReactionsSheet.StarsSlider f$9;

    public /* synthetic */ HighlightMessageSheet$$ExternalSyntheticLambda0(long[] r1, ButtonWithCounterView r2, ColoredImageSpan[] r3, LiveCommentsView.Message r4, LiveCommentsView.LiveCommentView r5, int r6, HighlightMessageSheet.TierValueView r7, HighlightMessageSheet.TierValueView r8, HighlightMessageSheet.TierValueView r9, StarsReactionsSheet.StarsSlider r10, boolean[] r11) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
        this.f$9 = r10;
        this.f$10 = r11;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r13) {
        HighlightMessageSheet.$r8$lambda$AMz9hzLpiy1t41hnyTvTz8oks24(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, (Integer) r13);
    }
}
