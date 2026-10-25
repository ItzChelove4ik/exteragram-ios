package com.exteragram.messenger.drawer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.MainMenuItem;
import com.exteragram.messenger.utils.chats.MainMenuHelper;
import java.util.Iterator;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes.dex */
public class DrawerMenuView extends ScrollView {
    private static final int COLOR_KEY_BACKGROUND = 0;
    private static final float DIVIDER_HEIGHT_DP = 0.0f;
    private final LinearLayout container;
    private int lastGradientColor;
    private Runnable onItemClick;
    private LinearGradient topGradient;
    private final Paint topGradientPaint;

    public static /* synthetic */ boolean $r8$lambda$67DyIkXy74lmoz0FO69Zsh0gCuA(DrawerMenuView r0, MainMenuHelper.MenuItemInfo r1, View r2) {
        return r0.lambda$rebuildMenu$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$8wSkjVV5oylxumaOp4jBAoBRlok(DrawerMenuView r0, MainMenuHelper.MenuItemInfo r1, View r2) {
        r0.lambda$rebuildMenu$0(r1, r2);
    }

    static {
        DIVIDER_HEIGHT_DP = 1.0f / AndroidUtilities.density;
        COLOR_KEY_BACKGROUND = Theme.key_windowBackgroundWhite;
    }

    public DrawerMenuView(Context r5) {
        super(r5);
        this.topGradientPaint = new Paint();
        setVerticalScrollBarEnabled(false);
        LinearLayout r1 = new LinearLayout(r5);
        this.container = r1;
        r1.setOrientation(1);
        r1.setPadding(0, AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f) + AndroidUtilities.navigationBarHeight);
        addView(r1, new FrameLayout.LayoutParams(-1, -2));
        updateGradient();
    }

    private static LinearLayout.LayoutParams createDividerLayoutParams() {
        return LayoutHelper.createLinear(-1, DIVIDER_HEIGHT_DP, 87, 12, 8, 12, 8);
    }

    private /* synthetic */ void lambda$rebuildMenu$0(MainMenuHelper.MenuItemInfo r1, View r2) {
        Runnable r0 = this.onItemClick;
        if (r0 == null) goto L6;
        r0.run();
    L6:
        if (r1.onClick() == null) goto L9;
        r1.onClick().run();
        return;
    }

    private /* synthetic */ boolean lambda$rebuildMenu$1(MainMenuHelper.MenuItemInfo r1, View r2) {
        Runnable r0 = this.onItemClick;
        if (r0 == null) goto L5;
        r0.run();
    L5:
        r1.onLongClick().run();
        return true;
    }

    private void updateGradient() {
        int r0 = Theme.getColor(COLOR_KEY_BACKGROUND);
        if (this.topGradient != null) goto L5;
    L8:
        this.lastGradientColor = r0;
        LinearGradient r2 = new LinearGradient(0.0f, 0.0f, 0.0f, AndroidUtilities.dp(16.0f), new int[]{r0, 16777215 & r0}, null, Shader.TileMode.CLAMP);
        this.topGradient = r2;
        this.topGradientPaint.setShader(r2);
        invalidate();
        return;
    L5:
        if (r0 != this.lastGradientColor) goto L8;
    }

    public void clearMenu() {
        this.container.removeAllViews();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r8) {
        super.dispatchDraw(r8);
        if (getScrollY() <= 0) goto L6;
        r8.save();
        r8.translate(0.0f, getScrollY());
        r8.drawRect(0.0f, 0.0f, getWidth(), AndroidUtilities.dp(16.0f), this.topGradientPaint);
        r8.restore();
        return;
    }

    public void rebuildMenu(int r12, BaseFragment r13) {
        clearMenu();
        MainMenuHelper.MenuContext r132 = MainMenuHelper.createMenuContext(r12, r13);
        int r1 = 0;
        boolean r2 = false;
        boolean r3 = false;
    L4:
        if (r1 >= ExteraConfig.getMainMenuLayout().size()) goto L23;
        Integer r4 = ExteraConfig.getMainMenuLayout().get(r1);
        if (r4.intValue() != MainMenuItem.DIVIDER.getId()) goto L9;
        if (r2 == false) goto L22;
        r3 = true;
    L22:
        r1 = r1 + 1;
        goto L4
    L9:
        List<MainMenuHelper.MenuItemInfo> r5 = MainMenuHelper.resolveDrawerMenuItems(r4.intValue(), r132);
        if (r5.isEmpty() == true) goto L22;
        if (r3 == false) goto L14;
        View r22 = new View(getContext());
        r22.setBackgroundColor(Theme.getDividerColor(null));
        this.container.addView(r22, createDividerLayoutParams());
        r3 = false;
    L14:
        Iterator<MainMenuHelper.MenuItemInfo> r23 = r5.iterator();
    L16:
        if (r23.hasNext() == false) goto L21;
        MainMenuHelper.MenuItemInfo r52 = r23.next();
        DrawerMenuItemView r6 = new DrawerMenuItemView(getContext());
        r6.setMenuItem(r4.intValue(), r12, r52.iconRes(), r52.text());
        r6.setOnClickListener(new DrawerMenuView$$ExternalSyntheticLambda0(this, r52));
        if (r52.onLongClick() == null) goto L20;
        r6.setOnLongClickListener(new DrawerMenuView$$ExternalSyntheticLambda1(this, r52));
    L20:
        this.container.addView(r6);
        goto L16
    L21:
        r2 = true;
        goto L22
    }

    public void setOnItemClick(Runnable r1) {
        this.onItemClick = r1;
    }

    public void updateColors() {
        int r0 = 0;
    L4:
        if (r0 >= this.container.getChildCount()) goto L10;
        View r1 = this.container.getChildAt(r0);
        if ((r1 instanceof DrawerMenuItemView) == false) goto L8;
        ((DrawerMenuItemView) r1).updateColors();
    L9:
        r0 = r0 + 1;
        goto L4
    L8:
        r1.setBackgroundColor(Theme.getDividerColor(null));
        goto L9
    L10:
        updateGradient();
    }

    public void updateUnreadCounters(int r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.container.getChildCount()) goto L9;
        View r1 = this.container.getChildAt(r0);
        if ((r1 instanceof DrawerMenuItemView) == false) goto L8;
        ((DrawerMenuItemView) r1).updateUnreadCounter(r4);
    L8:
        r0 = r0 + 1;
        goto L4
    }
}
