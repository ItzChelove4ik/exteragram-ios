package org.telegram.ui;

import android.content.DialogInterface;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ThemeActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ThemeActivity$ListAdapter$$ExternalSyntheticLambda2 implements DialogInterface.OnClickListener {
    public final /* synthetic */ ThemeActivity.ListAdapter f$0;
    public final /* synthetic */ Theme.ThemeAccent f$1;
    public final /* synthetic */ ThemeActivity.ThemeAccentsListAdapter f$2;

    public /* synthetic */ ThemeActivity$ListAdapter$$ExternalSyntheticLambda2(ThemeActivity.ListAdapter r1, Theme.ThemeAccent r2, ThemeActivity.ThemeAccentsListAdapter r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface r3, int r4) {
        ThemeActivity.ListAdapter.m20722$r8$lambda$OOom3wTiHn1NxdDNXCe8_9a_M(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
