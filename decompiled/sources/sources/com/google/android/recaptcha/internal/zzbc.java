package com.google.android.recaptcha.internal;

import android.annotation.NonNull;
import android.annotation.Nullable;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import kotlin.jvm.functions.Function2;

/* loaded from: classes5.dex */
public final class zzbc implements Window.Callback {
    final /* synthetic */ Window.Callback zza;
    final /* synthetic */ zzbd zzb;
    private final /* synthetic */ Window.Callback zzc;

    public zzbc(Window.Callback r1, zzbd r2) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r1;
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchGenericMotionEvent(MotionEvent r1) {
        return this.zzc.dispatchGenericMotionEvent(r1);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent r1) {
        return this.zzc.dispatchKeyEvent(r1);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchKeyShortcutEvent(KeyEvent r1) {
        return this.zzc.dispatchKeyShortcutEvent(r1);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent r1) {
        return this.zzc.dispatchPopulateAccessibilityEvent(r1);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchTouchEvent(MotionEvent r3) {
        zzbd r0 = this.zzb;
        Function2 r1 = zzbd.zzb(r0).zza();
        if (r1 == null) goto L6;
        r1.invoke(zzbd.zzc(r0), r3);
    L6:
        return this.zza.dispatchTouchEvent(r3);
    }

    @Override // android.view.Window.Callback
    public final boolean dispatchTrackballEvent(MotionEvent r1) {
        return this.zzc.dispatchTrackballEvent(r1);
    }

    @Override // android.view.Window.Callback
    public final void onActionModeFinished(ActionMode r1) {
        this.zzc.onActionModeFinished(r1);
    }

    @Override // android.view.Window.Callback
    public final void onActionModeStarted(ActionMode r1) {
        this.zzc.onActionModeStarted(r1);
    }

    @Override // android.view.Window.Callback
    public final void onAttachedToWindow() {
        this.zzc.onAttachedToWindow();
    }

    @Override // android.view.Window.Callback
    public final void onContentChanged() {
        this.zzc.onContentChanged();
    }

    @Override // android.view.Window.Callback
    public final boolean onCreatePanelMenu(int r1, @NonNull Menu r2) {
        return this.zzc.onCreatePanelMenu(r1, r2);
    }

    @Override // android.view.Window.Callback
    @Nullable
    public final View onCreatePanelView(int r1) {
        return this.zzc.onCreatePanelView(r1);
    }

    @Override // android.view.Window.Callback
    public final void onDetachedFromWindow() {
        this.zzc.onDetachedFromWindow();
    }

    @Override // android.view.Window.Callback
    public final boolean onMenuItemSelected(int r1, @NonNull MenuItem r2) {
        return this.zzc.onMenuItemSelected(r1, r2);
    }

    @Override // android.view.Window.Callback
    public final boolean onMenuOpened(int r1, @NonNull Menu r2) {
        return this.zzc.onMenuOpened(r1, r2);
    }

    @Override // android.view.Window.Callback
    public final void onPanelClosed(int r1, @NonNull Menu r2) {
        this.zzc.onPanelClosed(r1, r2);
    }

    @Override // android.view.Window.Callback
    public final boolean onPreparePanel(int r1, @Nullable View r2, @NonNull Menu r3) {
        return this.zzc.onPreparePanel(r1, r2, r3);
    }

    @Override // android.view.Window.Callback
    public final boolean onSearchRequested() {
        return this.zzc.onSearchRequested();
    }

    @Override // android.view.Window.Callback
    public final boolean onSearchRequested(SearchEvent r1) {
        return this.zzc.onSearchRequested(r1);
    }

    @Override // android.view.Window.Callback
    public final void onWindowAttributesChanged(WindowManager.LayoutParams r1) {
        this.zzc.onWindowAttributesChanged(r1);
    }

    @Override // android.view.Window.Callback
    public final void onWindowFocusChanged(boolean r1) {
        this.zzc.onWindowFocusChanged(r1);
    }

    @Override // android.view.Window.Callback
    @Nullable
    public final ActionMode onWindowStartingActionMode(ActionMode.Callback r1) {
        return this.zzc.onWindowStartingActionMode(r1);
    }

    @Override // android.view.Window.Callback
    @Nullable
    public final ActionMode onWindowStartingActionMode(ActionMode.Callback r1, int r2) {
        return this.zzc.onWindowStartingActionMode(r1, r2);
    }
}
