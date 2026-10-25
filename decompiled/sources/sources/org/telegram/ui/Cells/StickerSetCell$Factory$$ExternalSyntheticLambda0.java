package org.telegram.ui.Cells;

import android.view.MotionEvent;
import android.view.View;
import org.telegram.ui.Cells.StickerSetCell;
import org.telegram.ui.Components.UniversalRecyclerView;

/* loaded from: classes6.dex */
public final /* synthetic */ class StickerSetCell$Factory$$ExternalSyntheticLambda0 implements View.OnTouchListener {
    public final /* synthetic */ UniversalRecyclerView f$0;
    public final /* synthetic */ StickerSetCell f$1;

    public /* synthetic */ StickerSetCell$Factory$$ExternalSyntheticLambda0(UniversalRecyclerView r1, StickerSetCell r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View r2, MotionEvent r3) {
        return StickerSetCell.Factory.$r8$lambda$N3qnKGflVcLtawK7SCRoWHhosIQ(this.f$0, this.f$1, r2, r3);
    }
}
