package org.telegram.ui.Components.Premium.boosts;

import android.app.Activity;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.DarkThemeResourceProvider;
import org.telegram.ui.WrappedResourceProvider;

/* loaded from: classes7.dex */
public class DarkFragmentWrapper extends BaseFragment {
    private final BaseFragment parentFragment;

    public DarkFragmentWrapper(BaseFragment r1) {
        this.parentFragment = r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public Activity getParentActivity() {
        return this.parentFragment.getParentActivity();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public Theme.ResourcesProvider getResourceProvider() {
        return new WrappedResourceProvider(new DarkThemeResourceProvider());
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean presentFragment(BaseFragment r1) {
        return false;
    }
}
