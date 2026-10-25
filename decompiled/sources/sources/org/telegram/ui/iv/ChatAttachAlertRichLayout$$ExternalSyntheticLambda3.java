package org.telegram.ui.iv;

import android.content.DialogInterface;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Cells.EditTextCell;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertRichLayout$$ExternalSyntheticLambda3 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ EditTextCell f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ boolean[] f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ String[] f$4;
    public final /* synthetic */ Utilities.Callback f$5;

    public /* synthetic */ ChatAttachAlertRichLayout$$ExternalSyntheticLambda3(EditTextCell r1, boolean[] r2, boolean[] r3, String r4, String[] r5, Utilities.Callback r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface r8) {
        ChatAttachAlertRichLayout.$r8$lambda$T_iX90XDQb6L6BZzm8gNZjs1QW0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r8);
    }
}
