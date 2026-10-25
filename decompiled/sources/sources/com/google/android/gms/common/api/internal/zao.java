package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zao implements Runnable {
    final /* synthetic */ zap zaa;
    private final zam zab;

    public zao(zap r1, zam r2) {
        Objects.requireNonNull(r1);
        this.zaa = r1;
        this.zab = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zap r5 = this.zaa;
        if (r5.zaa == true) goto L5;
        return;
    L5:
        zam r0 = this.zab;
        ConnectionResult r1 = r0.zab();
        if (r1.hasResolution() == false) goto L9;
        r5.mLifecycleFragment.startActivityForResult(GoogleApiActivity.zaa(r5.getActivity(), (PendingIntent) Preconditions.checkNotNull(r1.getResolution()), r0.zaa(), false), 1);
        return;
    L9:
        Activity r2 = r5.getActivity();
        int r3 = r1.getErrorCode();
        GoogleApiAvailability r02 = r5.zac;
        if (r02.getErrorResolutionIntent(r2, r3, null) == null) goto L14;
        r02.zab(r5.getActivity(), r5.mLifecycleFragment, r1.getErrorCode(), 2, r5);
        return;
    L14:
        if (r1.getErrorCode() != 18) goto L17;
        Dialog r12 = r02.zaf(r5.getActivity(), r5);
        r02.zag(r5.getActivity().getApplicationContext(), new zan(this, r12));
        return;
    L17:
        r5.zah(r1, r0.zaa());
    }
}
