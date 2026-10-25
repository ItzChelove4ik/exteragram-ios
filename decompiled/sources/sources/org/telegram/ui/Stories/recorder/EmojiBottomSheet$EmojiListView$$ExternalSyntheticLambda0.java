package org.telegram.ui.Stories.recorder;

import org.telegram.ui.Components.RecyclerAnimationScrollHelper;
import org.telegram.ui.Stories.recorder.EmojiBottomSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class EmojiBottomSheet$EmojiListView$$ExternalSyntheticLambda0 implements RecyclerAnimationScrollHelper.ScrollListener {
    public final /* synthetic */ EmojiBottomSheet.EmojiListView f$0;

    public /* synthetic */ EmojiBottomSheet$EmojiListView$$ExternalSyntheticLambda0(EmojiBottomSheet.EmojiListView r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.Components.RecyclerAnimationScrollHelper.ScrollListener
    public final void onScroll() {
        this.f$0.invalidate();
    }
}
