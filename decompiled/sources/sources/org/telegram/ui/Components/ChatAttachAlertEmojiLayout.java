package org.telegram.ui.Components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.view.View;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.EmojiView;
import org.telegram.ui.Components.RecyclerListView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class ChatAttachAlertEmojiLayout extends ChatAttachAlert.AttachAlertLayout {
    public int currentItemTop;
    private final EmojiView emojiView;
    private final RecyclerListView gridView;
    private final LinearLayoutManager layoutManager;
    private final boolean sticker;
    private final View tabsView;

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertEmojiLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ChatAttachAlertEmojiLayout this$0;

        public AnonymousClass1(ChatAttachAlertEmojiLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r4, int r5) {
            if (r5 != 0) goto L15;
            int r42 = AndroidUtilities.dp(13.0f);
            ActionBarMenuItem r52 = this.this$0.parentAlert.selectedMenuItem;
            if (r52 == null) goto L6;
            int r53 = AndroidUtilities.dp(r52.getAlpha() * 26.0f);
        L7:
            int r43 = r42 + r53;
            int r54 = this.this$0.parentAlert.getBackgroundPaddingTop();
            if ((((this.this$0.parentAlert.scrollOffsetY[0] - r54) - r43) + r54) >= ActionBar.getCurrentActionBarHeight()) goto L16;
            RecyclerListView.Holder r44 = (RecyclerListView.Holder) ChatAttachAlertEmojiLayout.m9623$$Nest$fgetgridView(this.this$0).findViewHolderForAdapterPosition(0);
            if (r44 != null) goto L12;
            return;
        L12:
            if (r44.itemView.getTop() <= AndroidUtilities.dp(7.0f)) goto L18;
            ChatAttachAlertEmojiLayout.m9623$$Nest$fgetgridView(this.this$0).smoothScrollBy(0, r44.itemView.getTop() - AndroidUtilities.dp(7.0f));
            return;
        L18:
            return;
        L16:
            return;
        L6:
            r53 = 0;
            goto L7
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r2, int r3, int r4) {
            ChatAttachAlertEmojiLayout r22 = this.this$0;
            r22.parentAlert.updateLayout(r22, true, r4);
            ChatAttachAlertEmojiLayout.m9624$$Nest$mcheckTopTabPosition(this.this$0);
        }
    }

    /* renamed from: -$$Nest$fgetgridView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m9623$$Nest$fgetgridView(ChatAttachAlertEmojiLayout r0) {
        return r0.gridView;
    }

    /* renamed from: -$$Nest$mcheckTopTabPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9624$$Nest$mcheckTopTabPosition(ChatAttachAlertEmojiLayout r0) {
        r0.checkTopTabPosition();
    }

    public ChatAttachAlertEmojiLayout(ChatAttachAlert r17, Context r18, Theme.ResourcesProvider r19, boolean r20) {
        super(r17, r18, r19);
        this.currentItemTop = 0;
        this.sticker = r20;
        this.occupyNavigationBar = true;
        EmojiView r1 = new EmojiView(r17.baseFragment, !r20, r20, false, getContext(), true, null, null, false, r19, false, true);
        this.emojiView = r1;
        r1.shouldLightenBackground = false;
        r1.setAllow(!r20, r20, false, false);
        r1.forceHideBackspaceButton();
        r1.forceHideSettingsButton();
        r1.setDisableStickerEditor();
        addView(r1, LayoutHelper.createFrame(-1, -1.0f));
        this.tabsView = r1.getTabsForType(!r20 ? 1 : 0);
        RecyclerListView r12 = r1.getListViewForType(!r20 ? 1 : 0);
        this.gridView = r12;
        r12.addOnScrollListener(new AnonymousClass1(this));
        this.layoutManager = (LinearLayoutManager) r12.getLayoutManager();
        checkTopTabPosition();
    }

    private void checkTopTabPosition() {
        int r0 = getCurrentItemTop();
        this.tabsView.setTranslationY(Math.max(0, r0));
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getCurrentItemTop() {
        int r0 = this.gridView.getChildCount();
        RecyclerListView r1 = this.gridView;
        if (r0 > 0) goto L6;
        int r02 = r1.getPaddingTop();
        this.currentItemTop = r02;
        r1.setTopGlowOffset(r02);
        return Integer.MAX_VALUE;
    L6:
        View r03 = r1.getChildAt(0);
        RecyclerListView.Holder r12 = (RecyclerListView.Holder) this.gridView.findContainingViewHolder(r03);
        int r04 = r03.getTop() - AndroidUtilities.dp(36.0f);
        int r3 = AndroidUtilities.dp(7.0f);
        if (r04 < AndroidUtilities.dp(7.0f)) goto L12;
        if (r12 == null) goto L12;
        if (r12.getAdapterPosition() != 0) goto L12;
    L13:
        this.gridView.setTopGlowOffset(r04);
        this.currentItemTop = r04;
        return r04;
    L12:
        r04 = r3;
        goto L13
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getFirstOffset() {
        return getListTopPadding() + AndroidUtilities.dp(56.0f);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getListTopPadding() {
        return this.gridView.getPaddingTop();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int needsActionBar() {
        return 1;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        checkTopTabPosition();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onPreMeasure(int r3, int r4) {
        ((FrameLayout.LayoutParams) getLayoutParams()).topMargin = ActionBar.getCurrentActionBarHeight();
        if (AndroidUtilities.isTablet() == true) goto L7;
        Point r32 = AndroidUtilities.displaySize;
        if (r32.x <= r32.y) goto L7;
        int r33 = (int) (r4 / 3.5f);
    L8:
        int r34 = r33 - AndroidUtilities.dp(52.0f);
        if (r34 >= 0) goto L11;
        r34 = 0;
    L11:
        int r35 = r34 + AndroidUtilities.dp(36.0f);
        if (this.gridView.getPaddingTop() == r35) goto L15;
        this.gridView.setPadding(AndroidUtilities.dp(6.0f), r35, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(48.0f));
        return;
    L15:
        return;
    L7:
        r33 = (r4 / 5) * 2;
        goto L8
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onShow(ChatAttachAlert.AttachAlertLayout r2) {
        this.parentAlert.actionBar.getTitleTextView().setBuildFullLayout(true);     // Catch: Exception -> L9
    L3:
        ActionBar r22 = this.parentAlert.actionBar;
        if (this.sticker == false) goto L6;
        int r0 = R.string.SelectSticker;
    L7:
        r22.setTitle(LocaleController.getString(r0));
        this.layoutManager.scrollToPositionWithOffset(0, 0);
        return;
    L6:
        r0 = R.string.SelectEmoji;
        goto L7
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void scrollToTop() {
        this.gridView.smoothScrollToPosition(0);
    }

    public void setDelegate(EmojiView.EmojiViewDelegate r1) {
        this.emojiView.setDelegate(r1);
    }

    @Override // android.view.View
    public void setTranslationY(float r1) {
        super.setTranslationY(r1);
        this.parentAlert.getSheetContainer().invalidate();
        invalidate();
    }
}
