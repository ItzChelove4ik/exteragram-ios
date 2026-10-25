package org.telegram.ui.Cells;

import android.content.Context;
import android.view.View;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLObject;

/* loaded from: classes6.dex */
public class PhotoAttachCameraCell extends View {
    private int itemSize;

    public PhotoAttachCameraCell(Context r1) {
        super(r1);
        setFocusable(true);
        this.itemSize = AndroidUtilities.dp(0.0f);
    }

    @Override // android.view.View
    public void onMeasure(int r3, int r4) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(this.itemSize + AndroidUtilities.dp(2.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(this.itemSize + AndroidUtilities.dp(2.0f), TLObject.FLAG_30));
    }

    public void setItemSize(int r1) {
        this.itemSize = r1;
    }
}
