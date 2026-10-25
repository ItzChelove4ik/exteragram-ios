package com.exteragram.messenger.utils.ui;

import android.content.DialogInterface;
import com.exteragram.messenger.utils.ui.PopupUtils;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes4.dex */
public final /* synthetic */ class PopupUtils$$ExternalSyntheticLambda1 implements DialogInterface.OnClickListener {
    public final /* synthetic */ AlertDialog.Builder f$0;
    public final /* synthetic */ PopupUtils.OnItemClickListener f$1;

    public /* synthetic */ PopupUtils$$ExternalSyntheticLambda1(AlertDialog.Builder r1, PopupUtils.OnItemClickListener r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface r2, int r3) {
        PopupUtils.$r8$lambda$82yGHJGHEfp_DRvEkaoSvyOG5NY(this.f$0, this.f$1, r2, r3);
    }
}
