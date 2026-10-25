package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* loaded from: classes4.dex */
public abstract class zai {
    public final int zac;

    public zai(int r1) {
        this.zac = r1;
    }

    public static /* synthetic */ Status zai(RemoteException r2) {
        return new Status(19, r2.getClass().getSimpleName() + ": " + r2.getLocalizedMessage());
    }

    public abstract void zad(Status r1);

    public abstract void zae(Exception r1);

    public abstract void zaf(zaaa r1, boolean r2);

    public abstract void zag(zabk r1);
}
