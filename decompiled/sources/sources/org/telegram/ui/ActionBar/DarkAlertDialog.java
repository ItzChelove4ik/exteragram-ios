package org.telegram.ui.ActionBar;

import android.content.Context;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public class DarkAlertDialog extends AlertDialog {

    public static class Builder extends AlertDialog.Builder {
        public Builder(Context r3) {
            super(new DarkAlertDialog(r3, 0));
        }
    }

    public DarkAlertDialog(Context r1, int r2) {
        super(r1, r2);
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog
    public int getThemedColor(int r2) {
        if (r2 != Theme.key_dialogBackground) goto L7;
        return -14277082;
    L7:
        if (r2 != Theme.key_dialogTextBlack) goto L9;
        return -1;
    L9:
        if (r2 != Theme.key_dialogButton) goto L11;
        return -1;
    L11:
        if (r2 != Theme.key_dialogScrollGlow) goto L14;
        return -1;
    L14:
        return super.getThemedColor(r2);
    }
}
