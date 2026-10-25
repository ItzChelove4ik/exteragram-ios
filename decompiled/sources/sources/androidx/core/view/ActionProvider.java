package androidx.core.view;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* loaded from: classes3.dex */
public abstract class ActionProvider {
    private static final String TAG = "ActionProvider(support)";
    private final Context mContext;
    private SubUiVisibilityListener mSubUiVisibilityListener;
    private VisibilityListener mVisibilityListener;

    public interface SubUiVisibilityListener {
        void onSubUiVisibilityChanged(boolean r1);
    }

    public interface VisibilityListener {
        void onActionProviderVisibilityChanged(boolean r1);
    }

    public ActionProvider(Context r1) {
        this.mContext = r1;
    }

    public Context getContext() {
        return this.mContext;
    }

    public boolean hasSubMenu() {
        return false;
    }

    public boolean isVisible() {
        return true;
    }

    public abstract View onCreateActionView();

    public View onCreateActionView(MenuItem r1) {
        return onCreateActionView();
    }

    public abstract boolean onPerformDefaultAction();

    public void onPrepareSubMenu(SubMenu r1) {
    }

    public boolean overridesItemVisibility() {
        return false;
    }

    public void refreshVisibility() {
        if (this.mVisibilityListener != null) goto L5;
        return;
    L5:
        if (overridesItemVisibility() == false) goto L9;
        this.mVisibilityListener.onActionProviderVisibilityChanged(isVisible());
        return;
    }

    public void reset() {
        this.mVisibilityListener = null;
        this.mSubUiVisibilityListener = null;
    }

    public void setSubUiVisibilityListener(SubUiVisibilityListener r1) {
        this.mSubUiVisibilityListener = r1;
    }

    public void setVisibilityListener(VisibilityListener r3) {
        if (this.mVisibilityListener == null) goto L6;
        if (r3 == null) goto L6;
        Log.w(TAG, "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + getClass().getSimpleName() + " instance while it is still in use somewhere else?");
    L6:
        this.mVisibilityListener = r3;
    }

    public void subUiVisibilityChanged(boolean r1) {
        SubUiVisibilityListener r0 = this.mSubUiVisibilityListener;
        if (r0 == null) goto L6;
        r0.onSubUiVisibilityChanged(r1);
        return;
    }
}
