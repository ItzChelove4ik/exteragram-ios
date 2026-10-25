package androidx.appcompat.view;

import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class WindowCallbackWrapper implements Window.Callback {
    final Window.Callback mWrapped;

    public static class Api23Impl {
        public static boolean onSearchRequested(Window.Callback r0, SearchEvent r1) {
            return r0.onSearchRequested(r1);
        }

        public static android.view.ActionMode onWindowStartingActionMode(Window.Callback r0, ActionMode.Callback r1, int r2) {
            return r0.onWindowStartingActionMode(r1, r2);
        }
    }

    public static class Api24Impl {
        public static void onProvideKeyboardShortcuts(Window.Callback r0, List<KeyboardShortcutGroup> r1, Menu r2, int r3) {
            r0.onProvideKeyboardShortcuts(r1, r2, r3);
        }
    }

    public static class Api26Impl {
        public static void onPointerCaptureChanged(Window.Callback r0, boolean r1) {
            r0.onPointerCaptureChanged(r1);
        }
    }

    public WindowCallbackWrapper(Window.Callback r1) {
        if (r1 == null) goto L6;
        this.mWrapped = r1;
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Window callback may not be null");
        throw null;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchGenericMotionEvent(MotionEvent r1) {
        return this.mWrapped.dispatchGenericMotionEvent(r1);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent r1) {
        return this.mWrapped.dispatchKeyEvent(r1);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent r1) {
        return this.mWrapped.dispatchKeyShortcutEvent(r1);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent r1) {
        return this.mWrapped.dispatchPopulateAccessibilityEvent(r1);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent r1) {
        return this.mWrapped.dispatchTouchEvent(r1);
    }

    @Override // android.view.Window.Callback
    public boolean dispatchTrackballEvent(MotionEvent r1) {
        return this.mWrapped.dispatchTrackballEvent(r1);
    }

    public final Window.Callback getWrapped() {
        return this.mWrapped;
    }

    @Override // android.view.Window.Callback
    public void onActionModeFinished(android.view.ActionMode r1) {
        this.mWrapped.onActionModeFinished(r1);
    }

    @Override // android.view.Window.Callback
    public void onActionModeStarted(android.view.ActionMode r1) {
        this.mWrapped.onActionModeStarted(r1);
    }

    @Override // android.view.Window.Callback
    public void onAttachedToWindow() {
        this.mWrapped.onAttachedToWindow();
    }

    @Override // android.view.Window.Callback
    public boolean onCreatePanelMenu(int r1, Menu r2) {
        return this.mWrapped.onCreatePanelMenu(r1, r2);
    }

    @Override // android.view.Window.Callback
    public View onCreatePanelView(int r1) {
        return this.mWrapped.onCreatePanelView(r1);
    }

    @Override // android.view.Window.Callback
    public void onDetachedFromWindow() {
        this.mWrapped.onDetachedFromWindow();
    }

    @Override // android.view.Window.Callback
    public boolean onMenuItemSelected(int r1, MenuItem r2) {
        return this.mWrapped.onMenuItemSelected(r1, r2);
    }

    @Override // android.view.Window.Callback
    public boolean onMenuOpened(int r1, Menu r2) {
        return this.mWrapped.onMenuOpened(r1, r2);
    }

    @Override // android.view.Window.Callback
    public void onPanelClosed(int r1, Menu r2) {
        this.mWrapped.onPanelClosed(r1, r2);
    }

    @Override // android.view.Window.Callback
    public void onPointerCaptureChanged(boolean r1) {
        Api26Impl.onPointerCaptureChanged(this.mWrapped, r1);
    }

    @Override // android.view.Window.Callback
    public boolean onPreparePanel(int r1, View r2, Menu r3) {
        return this.mWrapped.onPreparePanel(r1, r2, r3);
    }

    @Override // android.view.Window.Callback
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> r1, Menu r2, int r3) {
        Api24Impl.onProvideKeyboardShortcuts(this.mWrapped, r1, r2, r3);
    }

    @Override // android.view.Window.Callback
    public boolean onSearchRequested() {
        return this.mWrapped.onSearchRequested();
    }

    @Override // android.view.Window.Callback
    public boolean onSearchRequested(SearchEvent r1) {
        return Api23Impl.onSearchRequested(this.mWrapped, r1);
    }

    @Override // android.view.Window.Callback
    public void onWindowAttributesChanged(WindowManager.LayoutParams r1) {
        this.mWrapped.onWindowAttributesChanged(r1);
    }

    @Override // android.view.Window.Callback
    public void onWindowFocusChanged(boolean r1) {
        this.mWrapped.onWindowFocusChanged(r1);
    }

    @Override // android.view.Window.Callback
    public android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback r1, int r2) {
        return Api23Impl.onWindowStartingActionMode(this.mWrapped, r1, r2);
    }
}
