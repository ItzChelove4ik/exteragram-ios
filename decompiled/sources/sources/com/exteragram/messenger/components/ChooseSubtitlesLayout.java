package com.exteragram.messenger.components;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.Components.PopupSwipeBackLayout;

/* loaded from: classes4.dex */
public class ChooseSubtitlesLayout {
    private final ActionBarMenuSubItem disableItem;
    public final ActionBarPopupWindow.ActionBarPopupWindowLayout layout;

    public interface Callback {
        void onChooseSubtitles();

        void onDisableSubtitles();
    }

    public static /* synthetic */ void $r8$lambda$0yVeJzQvLQHFRaQWpHObAIGmF8U(Callback r0, View r1) {
        r0.onDisableSubtitles();
    }

    /* renamed from: $r8$lambda$PppzbAw_C9cR-krQIKN6Twluxvw, reason: not valid java name */
    public static /* synthetic */ void m1198$r8$lambda$PppzbAw_C9cRkrQIKN6Twluxvw(PopupSwipeBackLayout r0, View r1) {
        r0.closeForeground();
    }

    public static /* synthetic */ void $r8$lambda$gh3i3vL9oIDmkDMqyOeiPc_jHkU(Callback r0, View r1) {
        r0.onChooseSubtitles();
    }

    public ChooseSubtitlesLayout(Context r7, PopupSwipeBackLayout r8, Callback r9) {
        ActionBarPopupWindow.ActionBarPopupWindowLayout r0 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(r7, 0, null);
        this.layout = r0;
        r0.setFitItems(true);
        ActionBarMenuSubItem r3 = ActionBarMenuItem.addItem(r0, R.drawable.msg_arrow_back, LocaleController.getString(R.string.Back), false, null);
        r3.setOnClickListener(new ChooseSubtitlesLayout$$ExternalSyntheticLambda0(r8));
        r3.setColors(-328966, -328966);
        r3.setSelectorColor(268435455);
        View r32 = new FrameLayout(r7);
        r32.setMinimumWidth(AndroidUtilities.dp(196.0f));
        r32.setBackgroundColor(-15198184);
        r0.addView(r32);
        LinearLayout.LayoutParams r72 = (LinearLayout.LayoutParams) r32.getLayoutParams();
        if (LocaleController.isRTL == false) goto L5;
        int r5 = 5;
    L6:
        r72.gravity = r5;
        r72.width = -1;
        r72.height = AndroidUtilities.dp(8.0f);
        r32.setLayoutParams(r72);
        ActionBarMenuSubItem r73 = ActionBarMenuItem.addItem(r0, R.drawable.msg_folders, LocaleController.getString(R.string.ChooseSubtitles), false, null);
        r73.setColors(-328966, -328966);
        r73.setSelectorColor(268435455);
        r73.setOnClickListener(new ChooseSubtitlesLayout$$ExternalSyntheticLambda1(r9));
        ActionBarMenuSubItem r74 = ActionBarMenuItem.addItem(r0, R.drawable.msg_cancel, LocaleController.getString(R.string.DisableSubtitles), false, null);
        this.disableItem = r74;
        r74.setColors(-328966, -328966);
        r74.setSelectorColor(268435455);
        r74.setOnClickListener(new ChooseSubtitlesLayout$$ExternalSyntheticLambda2(r9));
        return;
    L5:
        r5 = 3;
        goto L6
    }

    public void update(boolean r1) {
        ActionBarMenuSubItem r0 = this.disableItem;
        if (r1 == false) goto L5;
        int r12 = 0;
    L6:
        r0.setVisibility(r12);
        return;
    L5:
        r12 = 8;
        goto L6
    }
}
