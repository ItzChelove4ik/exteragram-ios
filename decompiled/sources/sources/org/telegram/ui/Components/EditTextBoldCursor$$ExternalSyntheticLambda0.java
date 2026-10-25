package org.telegram.ui.Components;

import android.view.ViewTreeObserver;

/* loaded from: classes7.dex */
public final /* synthetic */ class EditTextBoldCursor$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ViewTreeObserver.OnPreDrawListener f$0;

    public /* synthetic */ EditTextBoldCursor$$ExternalSyntheticLambda0(ViewTreeObserver.OnPreDrawListener r1) {
        this.f$0 = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.onPreDraw();
    }
}
