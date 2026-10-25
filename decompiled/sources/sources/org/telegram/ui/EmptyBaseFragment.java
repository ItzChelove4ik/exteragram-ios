package org.telegram.ui;

import android.content.Context;
import android.view.View;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.SizeNotifierFrameLayout;

/* loaded from: classes3.dex */
public abstract class EmptyBaseFragment extends BaseFragment {
    public EmptyBaseFragment() {
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r2) {
        SizeNotifierFrameLayout r0 = new SizeNotifierFrameLayout(r2);
        this.fragmentView = r0;
        return r0;
    }
}
