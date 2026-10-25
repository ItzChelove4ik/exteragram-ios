package com.google.android.recaptcha.internal;

import android.app.Dialog;
import android.view.Window;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/* loaded from: classes5.dex */
public final class zzat extends FragmentManager.FragmentLifecycleCallbacks {
    private final zzav zza;
    private zzbd zzb;

    public zzat(zzav r1) {
        this.zza = r1;
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public final void onFragmentDestroyed(FragmentManager r1, Fragment r2) {
        super.onFragmentDestroyed(r1, r2);
        this.zzb = null;
    }

    @Override // androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
    public final void onFragmentResumed(FragmentManager r3, Fragment r4) {
        super.onFragmentResumed(r3, r4);
        if ((r4 instanceof DialogFragment) == false) goto L5;
        DialogFragment r32 = (DialogFragment) r4;
    L6:
        if (r32 == null) goto L14;
        Dialog r33 = r32.getDialog();
        if (r33 == null) goto L15;
        Window r34 = r33.getWindow();
        if (r34 == null) goto L16;
        zzbd r0 = new zzbd(r34, r4.getClass().getSimpleName(), this.zza);
        this.zzb = r0;
        r0.zza();
        return;
    L16:
        return;
    L15:
        return;
    L14:
        return;
    L5:
        r32 = null;
        goto L6
    }
}
