package org.telegram.ui.Components;

import android.app.Activity;
import android.content.DialogInterface;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda203 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ EditTextBoldCursor f$0;
    public final /* synthetic */ BaseFragment f$1;
    public final /* synthetic */ Activity f$2;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda203(EditTextBoldCursor r1, BaseFragment r2, Activity r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface r3) {
        AlertsCreator.m8698$r8$lambda$0gG6wS6rVuahA1HzAWarVn01Bo(this.f$0, this.f$1, this.f$2, r3);
    }
}
