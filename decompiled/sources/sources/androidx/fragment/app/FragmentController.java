package androidx.fragment.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import androidx.core.util.Preconditions;

/* loaded from: classes.dex */
public class FragmentController {
    private final FragmentHostCallback<?> mHost;

    private FragmentController(FragmentHostCallback<?> r1) {
        this.mHost = r1;
    }

    public static FragmentController createController(FragmentHostCallback<?> r2) {
        return new FragmentController((FragmentHostCallback) Preconditions.checkNotNull(r2, "callbacks == null"));
    }

    public void attachHost(Fragment r2) {
        FragmentHostCallback<?> r1 = this.mHost;
        r1.mFragmentManager.attachController(r1, r1, r2);
    }

    public void dispatchActivityCreated() {
        this.mHost.mFragmentManager.dispatchActivityCreated();
    }

    public boolean dispatchContextItemSelected(MenuItem r1) {
        return this.mHost.mFragmentManager.dispatchContextItemSelected(r1);
    }

    public void dispatchCreate() {
        this.mHost.mFragmentManager.dispatchCreate();
    }

    public void dispatchDestroy() {
        this.mHost.mFragmentManager.dispatchDestroy();
    }

    public void dispatchPause() {
        this.mHost.mFragmentManager.dispatchPause();
    }

    public void dispatchResume() {
        this.mHost.mFragmentManager.dispatchResume();
    }

    public void dispatchStart() {
        this.mHost.mFragmentManager.dispatchStart();
    }

    public void dispatchStop() {
        this.mHost.mFragmentManager.dispatchStop();
    }

    public boolean execPendingActions() {
        return this.mHost.mFragmentManager.execPendingActions(true);
    }

    public FragmentManager getSupportFragmentManager() {
        return this.mHost.mFragmentManager;
    }

    public void noteStateNotSaved() {
        this.mHost.mFragmentManager.noteStateNotSaved();
    }

    public View onCreateView(View r1, String r2, Context r3, AttributeSet r4) {
        return this.mHost.mFragmentManager.getLayoutInflaterFactory().onCreateView(r1, r2, r3, r4);
    }
}
