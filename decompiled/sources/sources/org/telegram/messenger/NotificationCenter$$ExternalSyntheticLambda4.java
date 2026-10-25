package org.telegram.messenger;

import android.view.View;
import org.telegram.messenger.NotificationCenter;

/* loaded from: classes.dex */
public final /* synthetic */ class NotificationCenter$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ NotificationCenter f$0;
    public final /* synthetic */ View f$1;
    public final /* synthetic */ View.OnAttachStateChangeListener f$2;
    public final /* synthetic */ NotificationCenter.NotificationCenterDelegate f$3;
    public final /* synthetic */ int f$4;

    public /* synthetic */ NotificationCenter$$ExternalSyntheticLambda4(NotificationCenter r1, View r2, View.OnAttachStateChangeListener r3, NotificationCenter.NotificationCenterDelegate r4, int r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        NotificationCenter.m4785$r8$lambda$0kUClKQNrpk_aXMibpcEvSXelk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
