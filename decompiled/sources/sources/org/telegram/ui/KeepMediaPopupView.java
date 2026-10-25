package org.telegram.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.CacheByChatsController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarsImageView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;

/* loaded from: classes6.dex */
public class KeepMediaPopupView extends ActionBarPopupWindow.ActionBarPopupWindowLayout {
    private final CacheByChatsController cacheByChatsController;
    Callback callback;
    ArrayList<CheckItem> checkItems;
    int currentType;
    ActionBarMenuSubItem delete;
    private final TextView description;
    private ArrayList<CacheByChatsController.KeepMediaException> exceptions;
    ExceptionsView exceptionsView;
    ActionBarMenuSubItem forever;
    FrameLayout gap;
    ActionBarMenuSubItem oneDay;
    ActionBarMenuSubItem oneMonth;
    ActionBarMenuSubItem oneWeek;
    BaseFragment parentFragment;
    ActionBarMenuSubItem twoDay;

    /* renamed from: org.telegram.ui.KeepMediaPopupView$1, reason: invalid class name */
    public class AnonymousClass1 extends CacheChatsExceptionsFragment {
        final /* synthetic */ KeepMediaPopupView this$0;
        final /* synthetic */ DialogsActivity val$activity;

        public AnonymousClass1(KeepMediaPopupView r1, Bundle r2, DialogsActivity r3) {
            this.this$0 = r1;
            this.val$activity = r3;
            super(r2);
        }

        @Override // org.telegram.ui.ActionBar.BaseFragment
        public void onTransitionAnimationEnd(boolean r1, boolean r2) {
            super.onTransitionAnimationEnd(r1, r2);
            if (r1 == false) goto L7;
            if (r2 == true) goto L8;
            this.val$activity.removeSelfFromStack();
            return;
        L8:
            return;
        }
    }

    public interface Callback {
        void onKeepMediaChange(int r1, int r2);
    }

    public static class CheckItem {
        final ActionBarMenuSubItem item;
        final int type;

        private CheckItem(ActionBarMenuSubItem r1, int r2) {
            this.item = r1;
            this.type = r2;
        }

        public /* synthetic */ CheckItem(ActionBarMenuSubItem r1, int r2, KeepMediaPopupViewIA r3) {
            this(r1, r2);
        }
    }

    public class ExceptionsView extends FrameLayout {
        AvatarsImageView avatarsImageView;
        boolean ignoreLayout;
        final /* synthetic */ KeepMediaPopupView this$0;
        SimpleTextView titleView;

        public ExceptionsView(KeepMediaPopupView r10, Context r11) {
            this.this$0 = r10;
            super(r11);
            SimpleTextView r102 = new SimpleTextView(r11);
            this.titleView = r102;
            r102.setTextSize(16);
            this.titleView.setEllipsizeByGradient(true);
            this.titleView.setRightPadding(AndroidUtilities.dp(68.0f));
            this.titleView.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
            addView(this.titleView, LayoutHelper.createFrame(0, -2.0f, 19, 19.0f, 0.0f, 19.0f, 0.0f));
            AvatarsImageView r103 = new AvatarsImageView(r11, false);
            this.avatarsImageView = r103;
            r103.avatarsDrawable.setShowSavedMessages(true);
            this.avatarsImageView.setStyle(11);
            this.avatarsImageView.setAvatarsTextSize(AndroidUtilities.dp(22.0f));
            addView(this.avatarsImageView, LayoutHelper.createFrame(56, -1.0f, 21, 0.0f, 0.0f, 4.0f, 0.0f));
            setBackground(Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_listSelector), 0, 4));
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            View r0 = (View) getParent();
            if (r0 != null) goto L5;
        L7:
            this.ignoreLayout = true;
            this.titleView.setVisibility(8);
            super.onMeasure(r4, r5);
            this.titleView.setVisibility(0);
            ViewGroup.LayoutParams r02 = this.titleView.getLayoutParams();
            r02.width = getMeasuredWidth();
            this.ignoreLayout = false;
            KeepMediaPopupView.m15568$$Nest$mupdateAvatarsPosition(this.this$0);
            super.onMeasure(r4, r5);
            return;
        L5:
            if (r0.getWidth() <= 0) goto L7;
            r4 = View.MeasureSpec.makeMeasureSpec(r0.getWidth(), TLObject.FLAG_30);
            goto L7
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: $r8$lambda$-JpW7IY7fX0bJRjXxdbF_TS0UDs, reason: not valid java name */
    public static /* synthetic */ boolean m15567$r8$lambda$JpW7IY7fX0bJRjXxdbF_TS0UDs(KeepMediaPopupView r0, DialogsActivity r1, DialogsActivity r2, ArrayList r3, CharSequence r4, boolean r5, boolean r6, int r7, int r8, TopicsFragment r9) {
        return r0.lambda$new$1(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$4ItnIRKITb0QAkPvMu2CL3JDPpQ(KeepMediaPopupView r0, int r1, View r2) {
        r0.lambda$new$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$IbGE_VWp1JOjLKmlr34FzN_Uf_0(CacheChatsExceptionsFragment r0, CacheByChatsController.KeepMediaException r1) {
        r0.showPopupFor(r1);
    }

    public static /* synthetic */ void $r8$lambda$nOGrq1lPNTaSXlyuo3FvdCSSY94(KeepMediaPopupView r0, BaseFragment r1, View r2) {
        r0.lambda$new$2(r1, r2);
    }

    /* renamed from: -$$Nest$mupdateAvatarsPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15568$$Nest$mupdateAvatarsPosition(KeepMediaPopupView r0) {
        r0.updateAvatarsPosition();
    }

    public KeepMediaPopupView(BaseFragment r9, Context r10) {
        super(r10, null);
        this.checkItems = new ArrayList();
        this.parentFragment = r9;
        this.cacheByChatsController = r9.getMessagesController().getCacheByChatsController();
        setFitItems(true);
        this.oneDay = ActionBarMenuItem.addItem(this, R.drawable.msg_autodelete_1d, LocaleController.formatPluralString("Days", 1, new Object[0]), false, null);
        this.twoDay = ActionBarMenuItem.addItem(this, R.drawable.msg_autodelete_2d, LocaleController.formatPluralString("Days", 2, new Object[0]), false, null);
        this.oneWeek = ActionBarMenuItem.addItem(this, R.drawable.msg_autodelete_1w, LocaleController.formatPluralString("Weeks", 1, new Object[0]), false, null);
        this.oneMonth = ActionBarMenuItem.addItem(this, R.drawable.msg_autodelete_1m, LocaleController.formatPluralString("Months", 1, new Object[0]), false, null);
        this.forever = ActionBarMenuItem.addItem(this, R.drawable.msg_cancel, LocaleController.getString(R.string.AutoDeleteMediaNever), false, null);
        ActionBarMenuSubItem r3 = ActionBarMenuItem.addItem(this, R.drawable.msg_delete, LocaleController.getString(R.string.DeleteException), false, null);
        this.delete = r3;
        int r5 = Theme.key_text_RedRegular;
        r3.setColors(Theme.getColor(r5), Theme.getColor(r5));
        this.checkItems.add(new CheckItem(this.oneDay, CacheByChatsController.KEEP_MEDIA_ONE_DAY, null));
        this.checkItems.add(new CheckItem(this.twoDay, CacheByChatsController.KEEP_MEDIA_TWO_DAY, null));
        this.checkItems.add(new CheckItem(this.oneWeek, CacheByChatsController.KEEP_MEDIA_ONE_WEEK, null));
        this.checkItems.add(new CheckItem(this.oneMonth, CacheByChatsController.KEEP_MEDIA_ONE_MONTH, null));
        this.checkItems.add(new CheckItem(this.forever, CacheByChatsController.KEEP_MEDIA_FOREVER, null));
        this.checkItems.add(new CheckItem(this.delete, CacheByChatsController.KEEP_MEDIA_DELETE, null));
        FrameLayout r32 = new FrameLayout(r10);
        this.gap = r32;
        r32.setBackgroundColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuSeparator));
        View r33 = new View(r10);
        r33.setBackground(Theme.getThemedDrawableByKey(r10, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow, null));
        this.gap.addView(r33, LayoutHelper.createFrame(-1, -1.0f));
        this.gap.setTag(R.id.fit_width_tag, 1);
        addView(this.gap, LayoutHelper.createLinear(-1, 8));
        ExceptionsView r0 = new ExceptionsView(this, r10);
        this.exceptionsView = r0;
        addView(r0, LayoutHelper.createLinear(-1, 48));
        this.exceptionsView.setOnClickListener(new KeepMediaPopupView$$ExternalSyntheticLambda0(this, r9));
        int r92 = 0;
    L4:
        if (r92 >= this.checkItems.size()) goto L6;
        this.checkItems.get(r92).item.setOnClickListener(new KeepMediaPopupView$$ExternalSyntheticLambda1(this, this.checkItems.get(r92).type));
        r92 = r92 + 1;
        goto L4
    L6:
        LinkSpanDrawable.LinksTextView r93 = new LinkSpanDrawable.LinksTextView(r10);
        this.description = r93;
        r93.setTag(R.id.fit_width_tag, 1);
        r93.setPadding(AndroidUtilities.dp(13.0f), 0, AndroidUtilities.dp(13.0f), AndroidUtilities.dp(8.0f));
        r93.setTextSize(1, 13.0f);
        r93.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem));
        r93.setMovementMethod(LinkMovementMethod.getInstance());
        r93.setLinkTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText));
        r93.setText(LocaleController.getString(R.string.KeepMediaPopupDescription));
        addView(r93, LayoutHelper.createLinear(-1, -2, 0.0f, 0, 0, 8, 0, 0));
    }

    private /* synthetic */ boolean lambda$new$1(DialogsActivity r1, DialogsActivity r2, ArrayList r3, CharSequence r4, boolean r5, boolean r6, int r7, int r8, TopicsFragment r9) {
        CacheByChatsController.KeepMediaException r22 = null;
        int r42 = 0;
    L4:
        if (r42 >= r3.size()) goto L6;
        ArrayList<CacheByChatsController.KeepMediaException> r23 = this.exceptions;
        CacheByChatsController.KeepMediaException r52 = new CacheByChatsController.KeepMediaException(((MessagesStorage.TopicKey) r3.get(r42)).dialogId, CacheByChatsController.KEEP_MEDIA_ONE_DAY);
        r23.add(r52);
        r42 = r42 + 1;
        r22 = r52;
        goto L4
    L6:
        this.cacheByChatsController.saveKeepMediaExceptions(this.currentType, this.exceptions);
        Bundle r32 = new Bundle();
        r32.putInt("type", this.currentType);
        AnonymousClass1 r43 = new AnonymousClass1(this, r32, r1);
        r43.setExceptions(this.exceptions);
        this.parentFragment.presentFragment(r43);
        AndroidUtilities.runOnUIThread(new KeepMediaPopupView$$ExternalSyntheticLambda3(r43, r22), 150);
        return true;
    }

    private /* synthetic */ void lambda$new$2(BaseFragment r5, View r6) {
        this.window.dismiss();
        if (this.exceptions.isEmpty() == false) goto L13;
        Bundle r62 = new Bundle();
        r62.putBoolean("onlySelect", true);
        r62.putBoolean("onlySelect", true);
        r62.putBoolean("checkCanWrite", false);
        int r0 = this.currentType;
        if (r0 != 1) goto L8;
        r62.putInt("dialogsType", 6);
    L11:
        r62.putBoolean("allowGlobalSearch", false);
        DialogsActivity r02 = new DialogsActivity(r62);
        r02.setDelegate(new KeepMediaPopupView$$ExternalSyntheticLambda2(this, r02));
        r5.presentFragment(r02);
        return;
    L8:
        if (r0 != 2) goto L10;
        r62.putInt("dialogsType", 5);
        goto L11
    L10:
        r62.putInt("dialogsType", 4);
        goto L11
    L13:
        Bundle r63 = new Bundle();
        r63.putInt("type", this.currentType);
        CacheChatsExceptionsFragment r03 = new CacheChatsExceptionsFragment(r63);
        r03.setExceptions(this.exceptions);
        r5.presentFragment(r03);
    }

    private /* synthetic */ void lambda$new$3(int r2, View r3) {
        this.window.dismiss();
        int r32 = this.currentType;
        if (r32 < 0) goto L8;
        this.cacheByChatsController.setKeepMedia(r32, r2);
        Callback r33 = this.callback;
        if (r33 == null) goto L12;
        r33.onKeepMediaChange(this.currentType, r2);
        return;
    L12:
        return;
    L8:
        Callback r1 = this.callback;
        if (r1 == null) goto L13;
        r1.onKeepMediaChange(r32, r2);
        return;
    }

    private void updateAvatarsPosition() {
        if (this.exceptions == null) goto L6;
        this.exceptionsView.avatarsImageView.setTranslationX(AndroidUtilities.dp(12.0f) * (3 - Math.min(3, this.exceptions.size())));
        return;
    }

    public void setCallback(Callback r1) {
        this.callback = r1;
    }

    public void update(int r9) {
        this.currentType = r9;
        ActionBarMenuSubItem r0 = this.twoDay;
        if (r9 != 3) goto L5;
        r0.setVisibility(0);
        this.oneMonth.setVisibility(8);
        this.gap.setVisibility(8);
        this.exceptionsView.setVisibility(8);
        this.description.setVisibility(8);
    L6:
        ArrayList<CacheByChatsController.KeepMediaException> r92 = this.cacheByChatsController.getKeepMediaExceptions(r9);
        this.exceptions = r92;
        if (r92.isEmpty() == false) goto L9;
        this.exceptionsView.titleView.setText(LocaleController.getString(R.string.AddAnException));
        this.exceptionsView.titleView.setRightPadding(AndroidUtilities.dp(8.0f));
        this.exceptionsView.avatarsImageView.setObject(0, this.parentFragment.getCurrentAccount(), null);
        this.exceptionsView.avatarsImageView.setObject(1, this.parentFragment.getCurrentAccount(), null);
        this.exceptionsView.avatarsImageView.setObject(2, this.parentFragment.getCurrentAccount(), null);
        this.exceptionsView.avatarsImageView.commitTransition(false);
    L14:
        this.delete.setVisibility(8);
        this.description.setVisibility(8);
        updateAvatarsPosition();
        return;
    L9:
        int r93 = Math.min(3, this.exceptions.size());
        this.exceptionsView.titleView.setRightPadding(AndroidUtilities.dp((Math.max(0, r93 - 1) * 12) + 64));
        this.exceptionsView.titleView.setText(LocaleController.formatPluralString("ExceptionShort", this.exceptions.size(), new Object[]{Integer.valueOf(this.exceptions.size())}));
        int r02 = 0;
    L10:
        ExceptionsView r3 = this.exceptionsView;
        if (r02 >= r93) goto L13;
        r3.avatarsImageView.setObject(r02, this.parentFragment.getCurrentAccount(), this.parentFragment.getMessagesController().getUserOrChat(this.exceptions.get(r02).dialogId));
        r02 = r02 + 1;
        goto L10
    L13:
        r3.avatarsImageView.commitTransition(false);
        goto L14
    L5:
        r0.setVisibility(8);
        this.oneMonth.setVisibility(0);
        this.gap.setVisibility(0);
        this.exceptionsView.setVisibility(0);
        this.description.setVisibility(0);
        goto L6
    }

    public void updateForDialog(boolean r4) {
        this.currentType = -1;
        this.gap.setVisibility(0);
        ActionBarMenuSubItem r0 = this.delete;
        if (r4 == false) goto L5;
        int r42 = 8;
    L6:
        r0.setVisibility(r42);
        this.description.setVisibility(0);
        this.exceptionsView.setVisibility(8);
        return;
    L5:
        r42 = 0;
        goto L6
    }
}
