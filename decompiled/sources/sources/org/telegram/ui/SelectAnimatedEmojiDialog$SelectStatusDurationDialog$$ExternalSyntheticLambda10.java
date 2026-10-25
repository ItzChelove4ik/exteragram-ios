package org.telegram.ui;

import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.SelectAnimatedEmojiDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class SelectAnimatedEmojiDialog$SelectStatusDurationDialog$$ExternalSyntheticLambda10 implements AlertsCreator.StatusUntilDatePickerDelegate {
    public final /* synthetic */ SelectAnimatedEmojiDialog.SelectStatusDurationDialog f$0;
    public final /* synthetic */ boolean[] f$1;

    public /* synthetic */ SelectAnimatedEmojiDialog$SelectStatusDurationDialog$$ExternalSyntheticLambda10(SelectAnimatedEmojiDialog.SelectStatusDurationDialog r1, boolean[] r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Components.AlertsCreator.StatusUntilDatePickerDelegate
    public final void didSelectDate(int r2) {
        SelectAnimatedEmojiDialog.SelectStatusDurationDialog.m18741$r8$lambda$_sLeparfCQmXeTxHFy60zi_Dxs(this.f$0, this.f$1, r2);
    }
}
