package androidx.appcompat.app;

import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

/* loaded from: classes3.dex */
public class AppCompatDialogFragment extends DialogFragment {
    public AppCompatDialogFragment() {
    }

    public AppCompatDialogFragment(int r1) {
        super(r1);
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle r2) {
        return new AppCompatDialog(getContext(), getTheme());
    }

    @Override // androidx.fragment.app.DialogFragment
    public void setupDialog(Dialog r3, int r4) {
        if ((r3 instanceof AppCompatDialog) == false) goto L14;
        AppCompatDialog r2 = (AppCompatDialog) r3;
        if (r4 != 1) goto L7;
    L12:
        r2.supportRequestWindowFeature(1);
        return;
    L7:
        if (r4 == 2) goto L12;
        if (r4 == 3) goto L11;
        return;
    L11:
        r3.getWindow().addFlags(24);
        goto L12
    L14:
        super.setupDialog(r3, r4);
    }
}
