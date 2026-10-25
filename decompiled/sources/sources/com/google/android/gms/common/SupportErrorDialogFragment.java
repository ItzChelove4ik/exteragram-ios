package com.google.android.gms.common;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes4.dex */
public class SupportErrorDialogFragment extends DialogFragment {
    private Dialog zaa;
    private DialogInterface.OnCancelListener zab;
    private Dialog zac;

    public SupportErrorDialogFragment() {
    }

    public static SupportErrorDialogFragment newInstance(Dialog r2, DialogInterface.OnCancelListener r3) {
        SupportErrorDialogFragment r0 = new SupportErrorDialogFragment();
        Dialog r22 = (Dialog) Preconditions.checkNotNull(r2, "Cannot display null dialog");
        r22.setOnCancelListener(null);
        r22.setOnDismissListener(null);
        r0.zaa = r22;
        if (r3 == null) goto L5;
        r0.zab = r3;
    L5:
        return r0;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface r1) {
        DialogInterface.OnCancelListener r0 = this.zab;
        if (r0 == null) goto L6;
        r0.onCancel(r1);
        return;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle r2) {
        Dialog r22 = this.zaa;
        if (r22 != null) goto L9;
        setShowsDialog(false);
        if (this.zac != null) goto L8;
        this.zac = new AlertDialog.Builder((Context) Preconditions.checkNotNull(getContext())).create();
    L8:
        return this.zac;
    L9:
        return r22;
    }

    @Override // androidx.fragment.app.DialogFragment
    public void show(FragmentManager r1, String r2) {
        super.show(r1, r2);
    }
}
