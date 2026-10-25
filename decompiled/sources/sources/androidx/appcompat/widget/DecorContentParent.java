package androidx.appcompat.widget;

import android.view.Menu;
import android.view.Window;
import androidx.appcompat.view.menu.MenuPresenter;

/* loaded from: classes3.dex */
public interface DecorContentParent {
    boolean canShowOverflowMenu();

    void dismissPopups();

    boolean hideOverflowMenu();

    void initFeature(int r1);

    boolean isOverflowMenuShowPending();

    boolean isOverflowMenuShowing();

    void setMenu(Menu r1, MenuPresenter.Callback r2);

    void setMenuPrepared();

    void setWindowCallback(Window.Callback r1);

    void setWindowTitle(CharSequence r1);

    boolean showOverflowMenu();
}
