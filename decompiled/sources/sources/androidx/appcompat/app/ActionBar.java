package androidx.appcompat.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.ViewGroup;
import androidx.appcompat.R$styleable;
import androidx.appcompat.view.ActionMode;
import com.google.android.material.navigation.NavigationBarView;

/* loaded from: classes3.dex */
public abstract class ActionBar {

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int gravity;

        public LayoutParams(int r1, int r2) {
            super(r1, r2);
            this.gravity = NavigationBarView.ITEM_GRAVITY_START_CENTER;
        }

        public LayoutParams(Context r3, AttributeSet r4) {
            super(r3, r4);
            this.gravity = 0;
            TypedArray r32 = r3.obtainStyledAttributes(r4, R$styleable.ActionBarLayout);
            this.gravity = r32.getInt(R$styleable.ActionBarLayout_android_layout_gravity, 0);
            r32.recycle();
        }

        public LayoutParams(ViewGroup.LayoutParams r1) {
            super(r1);
            this.gravity = 0;
        }

        public LayoutParams(LayoutParams r2) {
            super(r2);
            this.gravity = 0;
            this.gravity = r2.gravity;
        }
    }

    public ActionBar() {
    }

    public boolean closeOptionsMenu() {
        return false;
    }

    public abstract boolean collapseActionView();

    public abstract void dispatchMenuVisibilityChanged(boolean r1);

    public abstract int getDisplayOptions();

    public abstract Context getThemedContext();

    public boolean invalidateOptionsMenu() {
        return false;
    }

    public void onConfigurationChanged(Configuration r1) {
    }

    public void onDestroy() {
    }

    public abstract boolean onKeyShortcut(int r1, KeyEvent r2);

    public boolean onMenuKeyEvent(KeyEvent r1) {
        return false;
    }

    public boolean openOptionsMenu() {
        return false;
    }

    public abstract void setDefaultDisplayHomeAsUpEnabled(boolean r1);

    public abstract void setDisplayHomeAsUpEnabled(boolean r1);

    public abstract void setHomeAsUpIndicator(int r1);

    public abstract void setShowHideAnimationEnabled(boolean r1);

    public abstract void setSubtitle(CharSequence r1);

    public abstract void setTitle(CharSequence r1);

    public abstract void setWindowTitle(CharSequence r1);

    public ActionMode startActionMode(ActionMode.Callback r1) {
        return null;
    }
}
