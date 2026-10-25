package org.telegram.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChannelBoostsController;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChannelColorActivity;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes6.dex */
public class GroupColorActivity extends ChannelColorActivity {
    private boolean isLoading;
    private ChannelColorActivity.ProfilePreview profilePreview;
    private float profilePreviewPercent;

    /* renamed from: org.telegram.ui.GroupColorActivity$1, reason: invalid class name */
    public class AnonymousClass1 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ GroupColorActivity this$0;
        final /* synthetic */ View val$view;

        /* renamed from: $r8$lambda$0lOyfjQtGzB3KQpcqn5kQ-AybBs, reason: not valid java name */
        public static /* synthetic */ void m15345$r8$lambda$0lOyfjQtGzB3KQpcqn5kQAybBs(AnonymousClass1 r0, View r1) {
            r0.lambda$onGlobalLayout$0(r1);
        }

        public AnonymousClass1(GroupColorActivity r1, View r2) {
            this.this$0 = r1;
            this.val$view = r2;
        }

        private /* synthetic */ void lambda$onGlobalLayout$0(View r1) {
            this.this$0.openBoostDialog(19);
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            this.val$view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            GroupColorActivity.m15344$$Nest$minitProfilePreview(this.this$0);
            GroupColorActivity.m15340$$Nest$fgetprofilePreview(this.this$0).infoLayout.setOnClickListener(new GroupColorActivity$1$$ExternalSyntheticLambda0(this));
        }
    }

    /* renamed from: org.telegram.ui.GroupColorActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerListView {
        final /* synthetic */ GroupColorActivity this$0;

        public AnonymousClass2(GroupColorActivity r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r3) {
            super.dispatchDraw(r3);
            if (GroupColorActivity.m15340$$Nest$fgetprofilePreview(this.this$0) != null) goto L5;
            return;
        L5:
            if (GroupColorActivity.m15341$$Nest$fgetprofilePreviewPercent(this.this$0) < 1.0f) goto L9;
            r3.save();
            r3.translate(0.0f, -(GroupColorActivity.m15340$$Nest$fgetprofilePreview(this.this$0).getMeasuredHeight() - GroupColorActivity.access$000(this.this$0).getMeasuredHeight()));
            GroupColorActivity.m15340$$Nest$fgetprofilePreview(this.this$0).draw(r3);
            r3.restore();
            return;
        }
    }

    /* renamed from: org.telegram.ui.GroupColorActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ GroupColorActivity this$0;

        public AnonymousClass3(GroupColorActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r3, int r4) {
            super.onScrollStateChanged(r3, r4);
            if (r4 == 0) goto L5;
            return;
        L5:
            if (GroupColorActivity.m15341$$Nest$fgetprofilePreviewPercent(this.this$0) < 0.5f) goto L15;
            if (GroupColorActivity.m15341$$Nest$fgetprofilePreviewPercent(this.this$0) >= 1.0f) goto L15;
            int r32 = GroupColorActivity.access$200(this.this$0).getBottom();
            RecyclerView.LayoutManager r42 = this.this$0.listView.getLayoutManager();
            if (r42 == null) goto L26;
            View r43 = r42.findViewByPosition(0);
            if (r43 == null) goto L27;
            this.this$0.listView.smoothScrollBy(0, r43.getBottom() - r32);
            return;
        L27:
            return;
        L26:
            return;
        L15:
            if (GroupColorActivity.m15341$$Nest$fgetprofilePreviewPercent(this.this$0) < 0.5f) goto L17;
            return;
        L17:
            if (this.this$0.listView.getLayoutManager() == null) goto L19;
            View r33 = this.this$0.listView.getLayoutManager().findViewByPosition(0);
        L20:
            if (r33 != null) goto L22;
            return;
        L22:
            if (r33.getTop() >= 0) goto L30;
            this.this$0.listView.smoothScrollBy(0, r33.getTop());
            return;
        L30:
            return;
        L19:
            r33 = null;
            goto L20
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r6, int r7, int r8) {
            GroupColorActivity.m15344$$Nest$minitProfilePreview(this.this$0);
            int r62 = GroupColorActivity.m15340$$Nest$fgetprofilePreview(this.this$0).getMeasuredHeight() - GroupColorActivity.access$100(this.this$0).getMeasuredHeight();
            float r72 = GroupColorActivity.m15340$$Nest$fgetprofilePreview(this.this$0).getTop() * (-1);
            float r63 = r62;
            GroupColorActivity.m15343$$Nest$fputprofilePreviewPercent(this.this$0, Math.max(Math.min(1.0f, r72 / r63), 0.0f));
            float r82 = Math.min(GroupColorActivity.m15341$$Nest$fgetprofilePreviewPercent(this.this$0) * 2.0f, 1.0f);
            float r0 = Math.min(Math.max(GroupColorActivity.m15341$$Nest$fgetprofilePreviewPercent(this.this$0) - 0.45f, 0.0f) * 2.0f, 1.0f);
            GroupColorActivity.m15340$$Nest$fgetprofilePreview(this.this$0).profileView.setAlpha(AndroidUtilities.lerp(1.0f, 0.0f, r82));
            GroupColorActivity.m15340$$Nest$fgetprofilePreview(this.this$0).infoLayout.setAlpha(AndroidUtilities.lerp(1.0f, 0.0f, r82));
            GroupColorActivity.m15340$$Nest$fgetprofilePreview(this.this$0).title.setAlpha(AndroidUtilities.lerp(0.0f, 1.0f, r0));
            float r83 = GroupColorActivity.m15341$$Nest$fgetprofilePreviewPercent(this.this$0);
            GroupColorActivity r5 = this.this$0;
            if (r83 < 1.0f) goto L6;
            GroupColorActivity.m15340$$Nest$fgetprofilePreview(r5).setTranslationY(r72 - r63);
            return;
        L6:
            GroupColorActivity.m15340$$Nest$fgetprofilePreview(r5).setTranslationY(0.0f);
        }
    }

    /* renamed from: org.telegram.ui.GroupColorActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends LimitReachedBottomSheet {
        final /* synthetic */ GroupColorActivity this$0;

        public AnonymousClass4(GroupColorActivity r1, BaseFragment r2, Context r3, int r4, int r5, Theme.ResourcesProvider r6) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6);
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
        public void dismiss() {
            super.dismiss();
            GroupColorActivity.m15342$$Nest$fputisLoading(this.this$0, false);
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet
        public void onOpenAnimationEnd() {
            GroupColorActivity.m15342$$Nest$fputisLoading(this.this$0, false);
        }
    }

    public static /* synthetic */ void $r8$lambda$5NrFdWDn9zaywLoCZdZg4oG97vQ(GroupColorActivity r0, int r1, ChannelBoostsController.CanApplyBoost r2) {
        r0.lambda$openBoostDialog$0(r1, r2);
    }

    /* renamed from: -$$Nest$fgetprofilePreview, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChannelColorActivity.ProfilePreview m15340$$Nest$fgetprofilePreview(GroupColorActivity r0) {
        return r0.profilePreview;
    }

    /* renamed from: -$$Nest$fgetprofilePreviewPercent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m15341$$Nest$fgetprofilePreviewPercent(GroupColorActivity r0) {
        return r0.profilePreviewPercent;
    }

    /* renamed from: -$$Nest$fputisLoading, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15342$$Nest$fputisLoading(GroupColorActivity r0, boolean r1) {
        r0.isLoading = r1;
    }

    /* renamed from: -$$Nest$fputprofilePreviewPercent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15343$$Nest$fputprofilePreviewPercent(GroupColorActivity r0, float r1) {
        r0.profilePreviewPercent = r1;
    }

    /* renamed from: -$$Nest$minitProfilePreview, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15344$$Nest$minitProfilePreview(GroupColorActivity r0) {
        r0.initProfilePreview();
    }

    public GroupColorActivity(long r1) {
        super(r1);
        this.isGroup = true;
    }

    public static /* synthetic */ ActionBar access$000(GroupColorActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$100(GroupColorActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$200(GroupColorActivity r0) {
        return r0.actionBar;
    }

    private void initProfilePreview() {
        if (this.profilePreview != null) goto L6;
        this.profilePreview = (ChannelColorActivity.ProfilePreview) findChildAt(this.profilePreviewRow);
        return;
    }

    private /* synthetic */ void lambda$openBoostDialog$0(int r9, ChannelBoostsController.CanApplyBoost r10) {
        if (r10 == null) goto L8;
        if (getContext() == null) goto L8;
        AnonymousClass4 r1 = new AnonymousClass4(this, this, getContext(), r9, this.currentAccount, this.resourceProvider);
        r1.setCanApplyBoost(r10);
        r1.setBoostsStats(this.boostsStatus, true);
        r1.setDialogId(this.dialogId);
        r1.show();
        return;
    L8:
        this.isLoading = false;
    }

    @Override // org.telegram.ui.ChannelColorActivity
    public void createListView() {
        AnonymousClass2 r0 = new AnonymousClass2(this, getContext(), this.resourceProvider);
        this.listView = r0;
        r0.setOnScrollListener(new AnonymousClass3(this));
        this.listView.setSections(true);
    }

    @Override // org.telegram.ui.ChannelColorActivity, org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r3) {
        View r32 = super.createView(r3);
        updateColors(false);
        this.actionBar.setAddToContainer(false);
        this.actionBar.setTitle(_UrlKt.FRAGMENT_ENCODE_SET);
        ((ViewGroup) r32).addView(this.actionBar);
        r32.getViewTreeObserver().addOnGlobalLayoutListener(new AnonymousClass1(this, r32));
        return r32;
    }

    @Override // org.telegram.ui.ChannelColorActivity, org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        super.didReceivedNotification(r3, r4, r5);
        if (r3 == NotificationCenter.chatInfoDidLoad) goto L5;
        return;
    L5:
        if (((TLRPC.ChatFull) r5[0]).id != (-this.dialogId)) goto L9;
        updateProfilePreview(true);
        return;
    }

    @Override // org.telegram.ui.ChannelColorActivity
    public int getCustomWallpaperLevelMin() {
        return getMessagesController().groupCustomWallpaperLevelMin;
    }

    @Override // org.telegram.ui.ChannelColorActivity
    public int getEmojiPackInfoStrRes() {
        return R.string.GroupEmojiPackInfo;
    }

    @Override // org.telegram.ui.ChannelColorActivity
    public int getEmojiPackStrRes() {
        return R.string.GroupEmojiPack;
    }

    @Override // org.telegram.ui.ChannelColorActivity
    public int getEmojiStatusInfoStrRes() {
        return R.string.GroupEmojiStatusInfo;
    }

    @Override // org.telegram.ui.ChannelColorActivity
    public int getEmojiStatusLevelMin() {
        return getMessagesController().groupEmojiStatusLevelMin;
    }

    @Override // org.telegram.ui.ChannelColorActivity
    public int getEmojiStatusStrRes() {
        return R.string.GroupEmojiStatus;
    }

    @Override // org.telegram.ui.ChannelColorActivity
    public int getEmojiStickersLevelMin() {
        return getMessagesController().groupEmojiStickersLevelMin;
    }

    @Override // org.telegram.ui.ChannelColorActivity
    public int getMessagePreviewType() {
        return 4;
    }

    @Override // org.telegram.ui.ChannelColorActivity
    public int getProfileIconLevelMin() {
        return getMessagesController().groupProfileBgIconLevelMin;
    }

    @Override // org.telegram.ui.ChannelColorActivity
    public int getProfileInfoStrRes() {
        return R.string.GroupProfileInfo;
    }

    @Override // org.telegram.ui.ChannelColorActivity
    public int getStickerPackInfoStrRes() {
        return R.string.GroupStickerPackInfo;
    }

    @Override // org.telegram.ui.ChannelColorActivity
    public int getStickerPackStrRes() {
        return R.string.GroupStickerPack;
    }

    @Override // org.telegram.ui.ChannelColorActivity
    public int getWallpaper2InfoStrRes() {
        return R.string.GroupWallpaper2Info;
    }

    @Override // org.telegram.ui.ChannelColorActivity
    public int getWallpaperLevelMin() {
        return getMessagesController().groupWallpaperLevelMin;
    }

    @Override // org.telegram.ui.ChannelColorActivity
    public int getWallpaperStrRes() {
        return R.string.GroupWallpaper;
    }

    @Override // org.telegram.ui.ChannelColorActivity
    public boolean isForum() {
        return ChatObject.isForum(getMessagesController().getChat(Long.valueOf(-this.dialogId)));
    }

    @Override // org.telegram.ui.ChannelColorActivity
    public boolean needBoostInfoSection() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onConfigurationChanged(Configuration r1) {
        super.onConfigurationChanged(r1);
        ChannelColorActivity.ProfilePreview r0 = this.profilePreview;
        if (r0 == null) goto L6;
        r0.setTitleSize();
        return;
    }

    @Override // org.telegram.ui.ChannelColorActivity, org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.chatInfoDidLoad);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ChannelColorActivity, org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.chatInfoDidLoad);
    }

    @Override // org.telegram.ui.ChannelColorActivity
    public void openBoostDialog(int r6) {
        if (this.boostsStatus != null) goto L5;
        return;
    L5:
        if (this.isLoading == true) goto L10;
        this.isLoading = true;
        MessagesController.getInstance(this.currentAccount).getBoostsController().userCanBoostChannel(this.dialogId, this.boostsStatus, new GroupColorActivity$$ExternalSyntheticLambda0(this, r6));
        return;
    }

    @Override // org.telegram.ui.ChannelColorActivity
    public void updateButton(boolean r3) {
        super.updateButton(r3);
        ChannelColorActivity.ProfilePreview r32 = this.profilePreview;
        if (r32 == null) goto L10;
        TextView r33 = r32.textInfo1;
        TL_stories.TL_premium_boostsStatus r2 = this.boostsStatus;
        if (r2 == null) goto L7;
        int r22 = r2.boosts;
    L8:
        r33.setText(AndroidUtilities.replaceTags(LocaleController.formatPluralString("BoostingGroupBoostCount", r22, new Object[0])));
        return;
    L7:
        r22 = 0;
        goto L8
    }

    @Override // org.telegram.ui.ChannelColorActivity
    public void updateColors(boolean r6) {
        super.updateColors(r6);
        this.actionBar.setBackgroundColor(0);
        Drawable r0 = Theme.getThemedDrawableByKey(getContext(), R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow);
        CombinedDrawable r3 = new CombinedDrawable(new ColorDrawable(Theme.getColor(Theme.key_windowBackgroundWhite, this.resourceProvider)), r0, 0, 0);
        r3.setFullsize(true);
        this.buttonContainer.setBackground(r3);
        ChannelColorActivity.ProfilePreview r02 = this.profilePreview;
        if (r02 == null) goto L7;
        if (r6 == true) goto L8;
        r02.backgroundView.setColor(this.currentAccount, this.selectedProfileColor, false);
        this.profilePreview.profileView.setColor(this.selectedProfileColor, false);
        this.profilePreview.updateColors();
        return;
    L8:
        return;
    }

    @Override // org.telegram.ui.ChannelColorActivity
    public void updateRows() {
        this.profilePreviewRow = 0;
        boolean r1 = true;
        int r2 = 1 + 1;
        this.emptyRow = 1;
        this.profileColorGridRow = r2;
        int r4 = r2 + 2;
        this.rowsCount = r4;
        this.profileEmojiRow = r2 + 1;
        if (this.selectedProfileEmoji != 0) goto L13;
        if (this.selectedProfileColor >= 0) goto L13;
        int r0 = this.removeProfileColorRow;
        this.removeProfileColorRow = -1;
        if (r0 < 0) goto L21;
        ChannelColorActivity.Adapter r12 = this.adapter;
        if (r12 == null) goto L21;
        r12.notifyItemRemoved(r0);
        this.adapter.notifyItemChanged(this.profileEmojiRow);
    L21:
        int r02 = this.rowsCount;
        this.profileHintRow = r02;
        this.packEmojiRow = r02 + 1;
        this.packEmojiHintRow = r02 + 2;
        this.statusEmojiRow = r02 + 3;
        this.rowsCount = r02 + 5;
        this.statusHintRow = r02 + 4;
        TLRPC.ChatFull r03 = getMessagesController().getChatFull(-this.dialogId);
        if (r03 != null) goto L24;
    L26:
        this.packStickerRow = -1;
        this.packStickerHintRow = -1;
    L27:
        int r04 = this.rowsCount;
        this.messagesPreviewRow = r04;
        this.wallpaperThemesRow = r04 + 1;
        this.wallpaperRow = r04 + 2;
        this.rowsCount = r04 + 4;
        this.wallpaperHintRow = r04 + 3;
        return;
    L24:
        if (r03.can_set_stickers == false) goto L26;
        int r05 = this.rowsCount;
        this.packStickerRow = r05;
        this.rowsCount = r05 + 2;
        this.packStickerHintRow = r05 + 1;
    L13:
        if (this.removeProfileColorRow >= 0) goto L16;
        r1 = false;
    L16:
        this.rowsCount = r2 + 3;
        this.removeProfileColorRow = r4;
        if (r1 == true) goto L21;
        ChannelColorActivity.Adapter r13 = this.adapter;
        if (r13 == null) goto L21;
        r13.notifyItemInserted(r4);
        this.adapter.notifyItemChanged(this.profileEmojiRow);
        this.listView.scrollToPosition(0);
        goto L21
    }
}
