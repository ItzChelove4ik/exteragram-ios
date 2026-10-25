package androidx.core.view;

import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;
import androidx.collection.SimpleArrayMap;

/* loaded from: classes3.dex */
public final class WindowInsetsControllerCompat {
    private final Impl mImpl;

    public static class Impl {
        public Impl() {
        }

        public abstract void hide(int r1);

        public abstract boolean isAppearanceLightStatusBars();

        public void setAppearanceLightNavigationBars(boolean r1) {
        }

        public abstract void setAppearanceLightStatusBars(boolean r1);

        public abstract void show(int r1);
    }

    public static class Impl20 extends Impl {
        private final SoftwareKeyboardControllerCompat mSoftwareKeyboardControllerCompat;
        protected final Window mWindow;

        public Impl20(Window r1, SoftwareKeyboardControllerCompat r2) {
            this.mWindow = r1;
            this.mSoftwareKeyboardControllerCompat = r2;
        }

        private void hideForType(int r2) {
            if (r2 != 1) goto L5;
            setSystemUiFlag(4);
            return;
        L5:
            if (r2 != 2) goto L7;
            setSystemUiFlag(2);
            return;
        L7:
            if (r2 == 8) goto L9;
            return;
        L9:
            this.mSoftwareKeyboardControllerCompat.hide();
        }

        private void showForType(int r2) {
            if (r2 != 1) goto L5;
            unsetSystemUiFlag(4);
            unsetWindowFlag(1024);
            return;
        L5:
            if (r2 != 2) goto L7;
            unsetSystemUiFlag(2);
            return;
        L7:
            if (r2 == 8) goto L9;
            return;
        L9:
            this.mSoftwareKeyboardControllerCompat.show();
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void hide(int r3) {
            int r0 = 1;
        L4:
            if (r0 > 512) goto L10;
            if ((r3 & r0) == 0) goto L9;
            hideForType(r0);
        L9:
            r0 = r0 << 1;
            goto L4
        }

        public void setSystemUiFlag(int r2) {
            View r1 = this.mWindow.getDecorView();
            r1.setSystemUiVisibility(r2 | r1.getSystemUiVisibility());
        }

        public void setWindowFlag(int r1) {
            this.mWindow.addFlags(r1);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void show(int r3) {
            int r0 = 1;
        L4:
            if (r0 > 512) goto L10;
            if ((r3 & r0) == 0) goto L9;
            showForType(r0);
        L9:
            r0 = r0 << 1;
            goto L4
        }

        public void unsetSystemUiFlag(int r2) {
            View r1 = this.mWindow.getDecorView();
            int r0 = r1.getSystemUiVisibility();
            r1.setSystemUiVisibility((~r2) & r0);
        }

        public void unsetWindowFlag(int r1) {
            this.mWindow.clearFlags(r1);
        }
    }

    public static class Impl23 extends Impl20 {
        public Impl23(Window r1, SoftwareKeyboardControllerCompat r2) {
            super(r1, r2);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public boolean isAppearanceLightStatusBars() {
            if ((this.mWindow.getDecorView().getSystemUiVisibility() & 8192) == 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void setAppearanceLightStatusBars(boolean r2) {
            if (r2 == false) goto L6;
            unsetWindowFlag(67108864);
            setWindowFlag(Integer.MIN_VALUE);
            setSystemUiFlag(8192);
            return;
        L6:
            unsetSystemUiFlag(8192);
        }
    }

    public static class Impl26 extends Impl23 {
        public Impl26(Window r1, SoftwareKeyboardControllerCompat r2) {
            super(r1, r2);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void setAppearanceLightNavigationBars(boolean r2) {
            if (r2 == false) goto L6;
            unsetWindowFlag(134217728);
            setWindowFlag(Integer.MIN_VALUE);
            setSystemUiFlag(16);
            return;
        L6:
            unsetSystemUiFlag(16);
        }
    }

    public static class Impl30 extends Impl {
        final WindowInsetsControllerCompat mCompatController;
        final WindowInsetsController mInsetsController;
        private final SimpleArrayMap<Object, WindowInsetsController.OnControllableInsetsChangedListener> mListeners;
        final SoftwareKeyboardControllerCompat mSoftwareKeyboardControllerCompat;
        protected Window mWindow;

        public Impl30(Window r2, WindowInsetsControllerCompat r3, SoftwareKeyboardControllerCompat r4) {
            this(r2.getInsetsController(), r3, r4);
            this.mWindow = r2;
        }

        public Impl30(WindowInsetsController r2, WindowInsetsControllerCompat r3, SoftwareKeyboardControllerCompat r4) {
            this.mListeners = new SimpleArrayMap();
            this.mInsetsController = r2;
            this.mCompatController = r3;
            this.mSoftwareKeyboardControllerCompat = r4;
        }

        private boolean isAppearanceLight(int r4, int r5) {
            Window r0 = this.mWindow;
            if (r0 != null) goto L5;
            this.mInsetsController.setSystemBarsAppearance(0, 0);
            if ((this.mInsetsController.getSystemBarsAppearance() & r5) == 0) goto L11;
            return true;
        L11:
            return false;
        L5:
            if ((r0.getDecorView().getSystemUiVisibility() & r4) == 0) goto L7;
            return true;
        L7:
            return false;
        }

        private void setAppearanceLight(boolean r2, int r3, int r4) {
            if (this.mWindow == null) goto L9;
            if (r2 == false) goto L7;
            setSystemUiFlag(r3);
            return;
        L7:
            unsetSystemUiFlag(r3);
            return;
        L9:
            WindowInsetsController r1 = this.mInsetsController;
            if (r2 == false) goto L13;
            r1.setSystemBarsAppearance(r4, r4);
            return;
        L13:
            r1.setSystemBarsAppearance(0, r4);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void hide(int r2) {
            if ((r2 & 8) == 0) goto L5;
            this.mSoftwareKeyboardControllerCompat.hide();
        L5:
            this.mInsetsController.hide(r2 & (-9));
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public boolean isAppearanceLightStatusBars() {
            return isAppearanceLight(8192, 8);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void setAppearanceLightNavigationBars(boolean r2) {
            setAppearanceLight(r2, 16, 16);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void setAppearanceLightStatusBars(boolean r3) {
            setAppearanceLight(r3, 8192, 8);
        }

        public void setSystemUiFlag(int r2) {
            View r1 = this.mWindow.getDecorView();
            r1.setSystemUiVisibility(r2 | r1.getSystemUiVisibility());
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl
        public void show(int r2) {
            if ((r2 & 8) == 0) goto L5;
            this.mSoftwareKeyboardControllerCompat.show();
        L5:
            this.mInsetsController.show(r2 & (-9));
        }

        public void unsetSystemUiFlag(int r2) {
            View r1 = this.mWindow.getDecorView();
            int r0 = r1.getSystemUiVisibility();
            r1.setSystemUiVisibility((~r2) & r0);
        }
    }

    public static class Impl31 extends Impl30 {
        public Impl31(Window r1, WindowInsetsControllerCompat r2, SoftwareKeyboardControllerCompat r3) {
            super(r1, r2, r3);
        }

        public Impl31(WindowInsetsController r1, WindowInsetsControllerCompat r2, SoftwareKeyboardControllerCompat r3) {
            super(r1, r2, r3);
        }
    }

    public static class Impl35 extends Impl31 {
        public Impl35(Window r1, WindowInsetsControllerCompat r2, SoftwareKeyboardControllerCompat r3) {
            super(r1, r2, r3);
        }

        public Impl35(WindowInsetsController r1, WindowInsetsControllerCompat r2, SoftwareKeyboardControllerCompat r3) {
            super(r1, r2, r3);
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl30, androidx.core.view.WindowInsetsControllerCompat.Impl
        public boolean isAppearanceLightStatusBars() {
            if ((this.mInsetsController.getSystemBarsAppearance() & 8) == 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl30, androidx.core.view.WindowInsetsControllerCompat.Impl
        public void setAppearanceLightNavigationBars(boolean r2) {
            WindowInsetsController r1 = this.mInsetsController;
            if (r2 == false) goto L5;
            int r22 = 16;
        L6:
            r1.setSystemBarsAppearance(r22, 16);
            return;
        L5:
            r22 = 0;
            goto L6
        }

        @Override // androidx.core.view.WindowInsetsControllerCompat.Impl30, androidx.core.view.WindowInsetsControllerCompat.Impl
        public void setAppearanceLightStatusBars(boolean r2) {
            WindowInsetsController r1 = this.mInsetsController;
            if (r2 == false) goto L5;
            int r22 = 8;
        L6:
            r1.setSystemBarsAppearance(r22, 8);
            return;
        L5:
            r22 = 0;
            goto L6
        }
    }

    public WindowInsetsControllerCompat(Window r3, View r4) {
        SoftwareKeyboardControllerCompat r0 = new SoftwareKeyboardControllerCompat(r4);
        int r42 = Build.VERSION.SDK_INT;
        if (r42 < 35) goto L7;
        this.mImpl = new Impl35(r3, this, r0);
        return;
    L7:
        if (r42 < 30) goto L11;
        this.mImpl = new Impl30(r3, this, r0);
        return;
    L11:
        if (r42 < 26) goto L14;
        this.mImpl = new Impl26(r3, r0);
        return;
    L14:
        this.mImpl = new Impl23(r3, r0);
    }

    @Deprecated
    private WindowInsetsControllerCompat(WindowInsetsController r3) {
        if (Build.VERSION.SDK_INT < 35) goto L6;
        this.mImpl = new Impl35(r3, this, new SoftwareKeyboardControllerCompat(r3));
        return;
    L6:
        this.mImpl = new Impl30(r3, this, new SoftwareKeyboardControllerCompat(r3));
    }

    @Deprecated
    public static WindowInsetsControllerCompat toWindowInsetsControllerCompat(WindowInsetsController r1) {
        return new WindowInsetsControllerCompat(r1);
    }

    public void hide(int r1) {
        this.mImpl.hide(r1);
    }

    public boolean isAppearanceLightStatusBars() {
        return this.mImpl.isAppearanceLightStatusBars();
    }

    public void setAppearanceLightNavigationBars(boolean r1) {
        this.mImpl.setAppearanceLightNavigationBars(r1);
    }

    public void setAppearanceLightStatusBars(boolean r1) {
        this.mImpl.setAppearanceLightStatusBars(r1);
    }

    public void show(int r1) {
        this.mImpl.show(r1);
    }
}
