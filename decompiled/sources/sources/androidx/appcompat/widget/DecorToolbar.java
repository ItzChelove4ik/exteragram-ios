package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.core.view.ViewPropertyAnimatorCompat;

/* loaded from: classes3.dex */
public interface DecorToolbar {
    boolean canShowOverflowMenu();

    void collapseActionView();

    void dismissPopupMenus();

    Context getContext();

    int getDisplayOptions();

    Menu getMenu();

    int getNavigationMode();

    CharSequence getTitle();

    ViewGroup getViewGroup();

    boolean hasExpandedActionView();

    boolean hideOverflowMenu();

    void initIndeterminateProgress();

    void initProgress();

    boolean isOverflowMenuShowPending();

    boolean isOverflowMenuShowing();

    void setCollapsible(boolean r1);

    void setDisplayOptions(int r1);

    void setEmbeddedTabView(ScrollingTabContainerView r1);

    void setHomeButtonEnabled(boolean r1);

    void setIcon(int r1);

    void setIcon(Drawable r1);

    void setLogo(int r1);

    void setMenu(Menu r1, MenuPresenter.Callback r2);

    void setMenuCallbacks(MenuPresenter.Callback r1, MenuBuilder.Callback r2);

    void setMenuPrepared();

    void setNavigationIcon(int r1);

    void setSubtitle(CharSequence r1);

    void setTitle(CharSequence r1);

    void setVisibility(int r1);

    void setWindowCallback(Window.Callback r1);

    void setWindowTitle(CharSequence r1);

    ViewPropertyAnimatorCompat setupAnimatorToVisibility(int r1, long r2);

    boolean showOverflowMenu();
}
