package androidx.core.view;

import android.R;
import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.inputmethod.InputMethodManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes3.dex */
public final class SoftwareKeyboardControllerCompat {
    private final Impl mImpl;

    public static class Impl {
        public Impl() {
        }

        public abstract void hide();

        public abstract void show();
    }

    public static class Impl20 extends Impl {
        private final View mView;

        public static /* synthetic */ void $r8$lambda$98l8cXiiZXB7QmnhQ0N_RDQBWxQ(View r2) {
            ((InputMethodManager) r2.getContext().getSystemService("input_method")).showSoftInput(r2, 0);
        }

        public Impl20(View r1) {
            this.mView = r1;
        }

        @Override // androidx.core.view.SoftwareKeyboardControllerCompat.Impl
        public void hide() {
            View r0 = this.mView;
            if (r0 == null) goto L6;
            ((InputMethodManager) r0.getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.mView.getWindowToken(), 0);
            return;
        }

        @Override // androidx.core.view.SoftwareKeyboardControllerCompat.Impl
        public void show() {
            View r0 = this.mView;
            if (r0 != null) goto L6;
            return;
        L6:
            if (r0.isInEditMode() == false) goto L8;
        L11:
            r0.requestFocus();
        L12:
            if (r0 != null) goto L14;
            r0 = this.mView.getRootView().findViewById(R.id.content);
        L14:
            if (r0 != null) goto L16;
            return;
        L16:
            if (r0.hasWindowFocus() == false) goto L20;
            r0.post(new SoftwareKeyboardControllerCompat$Impl20$$ExternalSyntheticLambda0(r0));
            return;
        L20:
            return;
        L8:
            if (r0.onCheckIsTextEditor() == true) goto L11;
            r0 = r0.getRootView().findFocus();
            goto L12
        }
    }

    public static class Impl30 extends Impl20 {
        private View mView;
        private WindowInsetsController mWindowInsetsController;

        public static /* synthetic */ void $r8$lambda$VOpj9tbXd3J3YDLKSnzVcYMukjU(AtomicBoolean r0, WindowInsetsController r1, int r2) {
            if ((r2 & 8) == 0) goto L5;
            boolean r12 = true;
        L6:
            r0.set(r12);
            return;
        L5:
            r12 = false;
            goto L6
        }

        public Impl30(View r1) {
            super(r1);
            this.mView = r1;
        }

        public Impl30(WindowInsetsController r2) {
            super(null);
            this.mWindowInsetsController = r2;
        }

        @Override // androidx.core.view.SoftwareKeyboardControllerCompat.Impl20, androidx.core.view.SoftwareKeyboardControllerCompat.Impl
        public void hide() {
            WindowInsetsController r0 = this.mWindowInsetsController;
            if (r0 != null) goto L9;
            View r02 = this.mView;
            if (r02 == null) goto L8;
            r0 = r02.getWindowInsetsController();
            goto L9
        L8:
            r0 = null;
        L9:
            if (r0 == null) goto L17;
            AtomicBoolean r1 = new AtomicBoolean(false);
            SoftwareKeyboardControllerCompat$Impl30$$ExternalSyntheticLambda0 r3 = new SoftwareKeyboardControllerCompat$Impl30$$ExternalSyntheticLambda0(r1);
            r0.addOnControllableInsetsChangedListener(r3);
            if (r1.get() == true) goto L15;
            View r12 = this.mView;
            if (r12 == null) goto L15;
            ((InputMethodManager) r12.getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.mView.getWindowToken(), 0);
        L15:
            r0.removeOnControllableInsetsChangedListener(r3);
            r0.hide(WindowInsets.Type.ime());
            return;
        L17:
            super.hide();
        }

        @Override // androidx.core.view.SoftwareKeyboardControllerCompat.Impl20, androidx.core.view.SoftwareKeyboardControllerCompat.Impl
        public void show() {
            View r0 = this.mView;
            if (r0 != null) goto L5;
        L7:
            WindowInsetsController r02 = this.mWindowInsetsController;
            if (r02 != null) goto L14;
            View r03 = this.mView;
            if (r03 == null) goto L13;
            r02 = r03.getWindowInsetsController();
            goto L14
        L13:
            r02 = null;
        L14:
            if (r02 == null) goto L16;
            r02.show(WindowInsets.Type.ime());
        L16:
            super.show();
            return;
        L5:
            if (Build.VERSION.SDK_INT >= 33) goto L7;
            ((InputMethodManager) r0.getContext().getSystemService("input_method")).isActive();
            goto L7
        }
    }

    public SoftwareKeyboardControllerCompat(View r3) {
        if (Build.VERSION.SDK_INT < 30) goto L6;
        this.mImpl = new Impl30(r3);
        return;
    L6:
        this.mImpl = new Impl20(r3);
    }

    @Deprecated
    public SoftwareKeyboardControllerCompat(WindowInsetsController r2) {
        this.mImpl = new Impl30(r2);
    }

    public void hide() {
        this.mImpl.hide();
    }

    public void show() {
        this.mImpl.show();
    }
}
