package org.telegram.ui.Components;

import android.view.KeyEvent;
import android.widget.TextView;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda171 implements TextView.OnEditorActionListener {
    public final /* synthetic */ AlertDialog f$0;
    public final /* synthetic */ AlertDialog.OnButtonClickListener f$1;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda171(AlertDialog r1, AlertDialog.OnButtonClickListener r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public final boolean onEditorAction(TextView r2, int r3, KeyEvent r4) {
        return AlertsCreator.$r8$lambda$wH5vMMAp3IINl6iHFFPefOnAVXs(this.f$0, this.f$1, r2, r3, r4);
    }
}
