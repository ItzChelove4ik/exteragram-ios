package com.exteragram.messenger.utils.ui;

import android.widget.LinearLayout;
import com.exteragram.messenger.utils.ui.PopupUtils;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes4.dex */
public final /* synthetic */ class PopupUtils$$ExternalSyntheticLambda4 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ LinearLayout f$0;
    public final /* synthetic */ PopupUtils.OnMultiSelectListener f$1;

    public /* synthetic */ PopupUtils$$ExternalSyntheticLambda4(LinearLayout r1, PopupUtils.OnMultiSelectListener r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r2, int r3) {
        PopupUtils.$r8$lambda$jpgBnZwRK_QOSpnMgBY4clbw2fE(this.f$0, this.f$1, r2, r3);
    }
}
