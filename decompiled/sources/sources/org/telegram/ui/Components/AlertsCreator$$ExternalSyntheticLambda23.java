package org.telegram.ui.Components;

import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.Components.AlertsCreator;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda23 implements ActionBarMenuItem.ActionBarMenuItemDelegate {
    public final /* synthetic */ AlertsCreator.ScheduleDatePickerDelegate f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ BottomSheet.Builder f$2;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda23(AlertsCreator.ScheduleDatePickerDelegate r1, boolean[] r2, BottomSheet.Builder r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemDelegate
    public final void onItemClick(int r3) {
        AlertsCreator.$r8$lambda$byCCrsBM7UcMsJG8ZA2yYWdYxkE(this.f$0, this.f$1, this.f$2, r3);
    }
}
