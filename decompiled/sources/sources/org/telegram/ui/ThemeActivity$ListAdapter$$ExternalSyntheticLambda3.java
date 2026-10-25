package org.telegram.ui;

import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ThemeActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ThemeActivity$ListAdapter$$ExternalSyntheticLambda3 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ ThemeActivity.ListAdapter f$0;
    public final /* synthetic */ ThemeActivity.ThemeAccentsListAdapter f$1;
    public final /* synthetic */ Theme.ThemeAccent f$2;

    public /* synthetic */ ThemeActivity$ListAdapter$$ExternalSyntheticLambda3(ThemeActivity.ListAdapter r1, ThemeActivity.ThemeAccentsListAdapter r2, Theme.ThemeAccent r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r3, int r4) {
        ThemeActivity.ListAdapter.$r8$lambda$l3usnw9I_Zngp45BQhqlHNnbPqM(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
