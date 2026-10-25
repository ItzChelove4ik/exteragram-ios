package androidx.drawerlayout.widget;

import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes3.dex */
public abstract class DrawerLayout extends ViewGroup {

    public interface DrawerListener {
    }

    public static abstract class SimpleDrawerListener implements DrawerListener {
        public SimpleDrawerListener() {
        }

        public void onDrawerSlide(View r1, float r2) {
        }

        public void onDrawerStateChanged(int r1) {
        }
    }
}
