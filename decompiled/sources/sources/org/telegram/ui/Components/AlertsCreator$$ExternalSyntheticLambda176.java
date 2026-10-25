package org.telegram.ui.Components;

import android.view.KeyEvent;
import android.widget.TextView;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda176 implements TextView.OnEditorActionListener {
    public final /* synthetic */ long f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ AlertDialog.OnButtonClickListener f$2;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda176(long r1, AlertDialog r3, AlertDialog.OnButtonClickListener r4) {
        this.f$0 = r1;
        this.f$1 = r3;
        this.f$2 = r4;
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public final boolean onEditorAction(TextView r8, int r9, KeyEvent r10) {
        return AlertsCreator.$r8$lambda$70kgG05GPY4bbiUAQmjvxShXoBI(this.f$0, this.f$1, this.f$2, r8, r9, r10);
    }
}
