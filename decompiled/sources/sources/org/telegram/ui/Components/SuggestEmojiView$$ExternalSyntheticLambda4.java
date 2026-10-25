package org.telegram.ui.Components;

import android.view.MotionEvent;
import android.view.View;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public final /* synthetic */ class SuggestEmojiView$$ExternalSyntheticLambda4 implements View.OnTouchListener {
    public final /* synthetic */ SuggestEmojiView f$0;
    public final /* synthetic */ RecyclerListView.OnItemClickListener f$1;

    public /* synthetic */ SuggestEmojiView$$ExternalSyntheticLambda4(SuggestEmojiView r1, RecyclerListView.OnItemClickListener r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View r2, MotionEvent r3) {
        return SuggestEmojiView.$r8$lambda$C1naNxm_fsEssW0zpv6NWQz_3gw(this.f$0, this.f$1, r2, r3);
    }
}
