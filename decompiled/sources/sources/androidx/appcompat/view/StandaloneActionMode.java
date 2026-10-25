package androidx.appcompat.view;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.ref.WeakReference;

/* loaded from: classes3.dex */
public class StandaloneActionMode extends ActionMode implements MenuBuilder.Callback {
    private ActionMode.Callback mCallback;
    private Context mContext;
    private ActionBarContextView mContextView;
    private WeakReference<View> mCustomView;
    private boolean mFinished;
    private boolean mFocusable;
    private MenuBuilder mMenu;

    public StandaloneActionMode(Context r1, ActionBarContextView r2, ActionMode.Callback r3, boolean r4) {
        this.mContext = r1;
        this.mContextView = r2;
        this.mCallback = r3;
        MenuBuilder r12 = new MenuBuilder(r2.getContext()).setDefaultShowAsAction(1);
        this.mMenu = r12;
        r12.setCallback(this);
        this.mFocusable = r4;
    }

    @Override // androidx.appcompat.view.ActionMode
    public void finish() {
        if (this.mFinished == false) goto L5;
        return;
    L5:
        this.mFinished = true;
        this.mCallback.onDestroyActionMode(this);
    }

    @Override // androidx.appcompat.view.ActionMode
    public View getCustomView() {
        WeakReference<View> r0 = this.mCustomView;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.get();
    }

    @Override // androidx.appcompat.view.ActionMode
    public Menu getMenu() {
        return this.mMenu;
    }

    @Override // androidx.appcompat.view.ActionMode
    public MenuInflater getMenuInflater() {
        return new SupportMenuInflater(this.mContextView.getContext());
    }

    @Override // androidx.appcompat.view.ActionMode
    public CharSequence getSubtitle() {
        return this.mContextView.getSubtitle();
    }

    @Override // androidx.appcompat.view.ActionMode
    public CharSequence getTitle() {
        return this.mContextView.getTitle();
    }

    @Override // androidx.appcompat.view.ActionMode
    public void invalidate() {
        this.mCallback.onPrepareActionMode(this, this.mMenu);
    }

    @Override // androidx.appcompat.view.ActionMode
    public boolean isTitleOptional() {
        return this.mContextView.isTitleOptional();
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
    public boolean onMenuItemSelected(MenuBuilder r1, MenuItem r2) {
        return this.mCallback.onActionItemClicked(this, r2);
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
    public void onMenuModeChange(MenuBuilder r1) {
        invalidate();
        this.mContextView.showOverflowMenu();
    }

    @Override // androidx.appcompat.view.ActionMode
    public void setCustomView(View r2) {
        this.mContextView.setCustomView(r2);
        if (r2 == null) goto L5;
        WeakReference<View> r0 = new WeakReference(r2);
    L6:
        this.mCustomView = r0;
        return;
    L5:
        r0 = null;
        goto L6
    }

    @Override // androidx.appcompat.view.ActionMode
    public void setSubtitle(int r2) {
        setSubtitle(this.mContext.getString(r2));
    }

    @Override // androidx.appcompat.view.ActionMode
    public void setSubtitle(CharSequence r1) {
        this.mContextView.setSubtitle(r1);
    }

    @Override // androidx.appcompat.view.ActionMode
    public void setTitle(int r2) {
        setTitle(this.mContext.getString(r2));
    }

    @Override // androidx.appcompat.view.ActionMode
    public void setTitle(CharSequence r1) {
        this.mContextView.setTitle(r1);
    }

    @Override // androidx.appcompat.view.ActionMode
    public void setTitleOptionalHint(boolean r1) {
        super.setTitleOptionalHint(r1);
        this.mContextView.setTitleOptional(r1);
    }
}
