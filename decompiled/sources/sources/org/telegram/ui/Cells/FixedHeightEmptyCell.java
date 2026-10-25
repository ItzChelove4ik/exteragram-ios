package org.telegram.ui.Cells;

import android.content.Context;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLObject;

/* loaded from: classes6.dex */
public class FixedHeightEmptyCell extends View {
    int heightInDp;

    public FixedHeightEmptyCell(Context r1, int r2) {
        super(r1);
        this.heightInDp = r2;
    }

    @Override // android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(this.heightInDp), TLObject.FLAG_30));
    }
}
