package org.telegram.messenger;

import android.content.SharedPreferences;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes5.dex */
public final /* synthetic */ class AndroidUtilities$$ExternalSyntheticLambda10 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ SharedPreferences f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ AndroidUtilities$$ExternalSyntheticLambda10(SharedPreferences r1, Runnable r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r2, int r3) {
        AndroidUtilities.$r8$lambda$aGRwEkVibMbJIvp3dF5n8rdo7tg(this.f$0, this.f$1, r2, r3);
    }
}
