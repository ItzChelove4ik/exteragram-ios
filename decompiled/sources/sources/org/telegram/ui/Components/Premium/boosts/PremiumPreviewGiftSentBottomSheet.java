package org.telegram.ui.Components.Premium.boosts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.Premium.PremiumGradient;
import org.telegram.ui.Components.Premium.PremiumPreviewBottomSheet;
import org.telegram.ui.Components.Premium.boosts.cells.ActionBtnCell;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes7.dex */
public class PremiumPreviewGiftSentBottomSheet extends PremiumPreviewBottomSheet {
    private final List<TLRPC.User> selectedUsers;

    /* renamed from: org.telegram.ui.Components.Premium.boosts.PremiumPreviewGiftSentBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends ViewOutlineProvider {
        final /* synthetic */ PremiumPreviewGiftSentBottomSheet this$0;

        public AnonymousClass1(PremiumPreviewGiftSentBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r7, Outline r8) {
            float r5 = AndroidUtilities.dp(12.0f);
            r8.setRoundRect(0, 0, r7.getWidth(), (int) (r7.getHeight() + r5), r5);
        }
    }

    public static class AdditionalCounterView extends View {
        int count;
        TextPaint paint;

        public AdditionalCounterView(Context r2) {
            super(r2);
            TextPaint r22 = new TextPaint(1);
            this.paint = r22;
            r22.setTextAlign(Paint.Align.CENTER);
            this.paint.setColor(Theme.getColor(Theme.key_windowBackgroundGray));
            this.paint.setTextSize(AndroidUtilities.dp(11.5f));
            this.paint.setTypeface(AndroidUtilities.bold());
        }

        @Override // android.view.View
        public void onDraw(Canvas r13) {
            float r0 = getMeasuredWidth() / 2.0f;
            float r2 = getMeasuredHeight() / 2.0f;
            r13.drawCircle(r0, r2, getMeasuredWidth() / 2.0f, this.paint);
            PremiumGradient.getInstance().updateMainGradientMatrix(0, 0, getMeasuredWidth(), getMeasuredHeight(), -AndroidUtilities.dp(10.0f), 0.0f);
            r13.drawCircle(r0, r2, (getMeasuredWidth() / 2.0f) - AndroidUtilities.dp(1.5f), PremiumGradient.getInstance().getMainGradientPaint());
            r13.drawText("+" + this.count, r0, (int) (r2 - ((this.paint.descent() + this.paint.ascent()) / 2.0f)), this.paint);
        }
    }

    @SuppressLint({"ViewConstructor"})
    public static class AvatarHolderView extends FrameLayout {
        private final Paint bgPaint;
        public boolean drawCycle;
        AvatarDrawable fromAvatarDrawable;
        protected final AdditionalCounterView iconView;
        private final BackupImageView imageView;
        public TLRPC.User user;

        public AvatarHolderView(Context r10, float r11) {
            super(r10);
            Paint r0 = new Paint(1);
            this.bgPaint = r0;
            this.drawCycle = true;
            this.fromAvatarDrawable = new AvatarDrawable();
            BackupImageView r1 = new BackupImageView(getContext());
            this.imageView = r1;
            r1.setRoundRadius(AndroidUtilities.dp(r11));
            AdditionalCounterView r112 = new AdditionalCounterView(r10);
            this.iconView = r112;
            r112.setAlpha(0.0f);
            addView(r1, LayoutHelper.createFrame(-1, -1.0f, 0, 5.0f, 5.0f, 5.0f, 5.0f));
            addView(r112, LayoutHelper.createFrame(26, 26.0f, 85, 0.0f, 0.0f, 1.0f, 3.0f));
            r0.setColor(Theme.getColor(Theme.key_windowBackgroundGray));
        }

        public static View createAvatarsContainer(Context r13, List<TLRPC.User> r14) {
            FrameLayout r0 = new FrameLayout(r13);
            r0.setClipChildren(false);
            FrameLayout r2 = new FrameLayout(r13);
            r2.setClipChildren(false);
            if (r14.size() != 1) goto L6;
            r0.addView(r2, LayoutHelper.createFrame(-1, 94.0f, 0, 0.0f, 0.0f, 0.0f, 0.0f));
            AvatarHolderView r3 = new AvatarHolderView(r13, 47.0f);
            r3.drawCycle = false;
            r3.setUser(r14.get(0));
            r2.addView(r3, 0, LayoutHelper.createFrame(94, 94, 17));
            return r0;
        L6:
            r0.addView(r2, LayoutHelper.createFrame(-1, 83.0f, 0, 0.0f, 0.0f, 0.0f, 0.0f));
            int r32 = 0;
            int r6 = 0;
        L8:
            if (r32 >= r14.size()) goto L18;
            TLRPC.User r7 = r14.get(r32);
            AvatarHolderView r8 = new AvatarHolderView(r13, 41.5f);
            r8.setUser(r7);
            r2.addView(r8, 0, LayoutHelper.createFrame(83, 83, 17));
            r8.setTranslationX((-r32) * AndroidUtilities.dp(29.0f));
            if (r32 == 0) goto L12;
        L14:
            r6 = r6 + 1;
            if (r32 == 2) goto L18;
            r32 = r32 + 1;
            goto L8
        L12:
            if (r14.size() <= 3) goto L14;
            r8.iconView.setAlpha(1.0f);
            r8.iconView.count = r14.size() - 3;
        L18:
            r0.setTranslationX(AndroidUtilities.dp(14.5f) * (r6 - 1));
            return r0;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r5) {
            if (this.drawCycle == false) goto L5;
            r5.drawCircle(getMeasuredWidth() / 2.0f, getMeasuredHeight() / 2.0f, (getMeasuredHeight() / 2.0f) - AndroidUtilities.dp(2.0f), this.bgPaint);
        L5:
            super.dispatchDraw(r5);
        }

        public void setUser(TLRPC.User r2) {
            this.user = r2;
            this.fromAvatarDrawable.setInfo(r2);
            this.imageView.setForUserOrChat(r2, this.fromAvatarDrawable);
        }
    }

    public static /* synthetic */ void $r8$lambda$LcQSkiAylSpe6nmzLdPMvJzDDlw(PremiumPreviewGiftSentBottomSheet r0, View r1) {
        r0.lambda$init$0(r1);
    }

    public PremiumPreviewGiftSentBottomSheet(BaseFragment r8, int r9, List<TLRPC.User> r10, Theme.ResourcesProvider r11) {
        super(r8, r9, null, null, null, r11);
        ArrayList r7 = new ArrayList();
        this.selectedUsers = r7;
        r7.addAll(r10);
        init();
    }

    private void init() {
        updateRows();
        this.useBackgroundTopPadding = false;
        setApplyTopPadding(false);
        this.backgroundPaddingTop = 0;
        ActionBtnCell r1 = new ActionBtnCell(getContext(), this.resourcesProvider);
        r1.setOnClickListener(new PremiumPreviewGiftSentBottomSheet$$ExternalSyntheticLambda0(this));
        r1.setCloseStyle(true);
        this.containerView.addView(r1, LayoutHelper.createFrame(-1, 64.0f, 80, 0.0f, 0.0f, 0.0f, 0.0f));
        RecyclerListView r12 = this.recyclerListView;
        int r2 = this.backgroundPaddingLeft;
        r12.setPadding(r2, 0, r2, AndroidUtilities.dp(64.0f));
        this.overrideTitleIcon = AvatarHolderView.createAvatarsContainer(getContext(), this.selectedUsers);
        fixNavigationBar();
    }

    private /* synthetic */ void lambda$init$0(View r1) {
        dismiss();
    }

    public static void show(List<TLRPC.User> r4) {
        BaseFragment r0 = LaunchActivity.getLastFragment();
        if (r0 != null) goto L5;
        return;
    L5:
        PremiumPreviewGiftSentBottomSheet r1 = new PremiumPreviewGiftSentBottomSheet(r0, UserConfig.selectedAccount, r4, r0.getResourceProvider());
        r1.setAnimateConfetti(true);
        r1.setAnimateConfettiWithStars(true);
        r1.show();
    }

    @Override // org.telegram.ui.Components.Premium.PremiumPreviewBottomSheet
    public void afterCellCreated(int r1, View r2) {
        if (r1 != 0) goto L5;
        r2.setOutlineProvider(new AnonymousClass1(this));
        r2.setClipToOutline(true);
        r2.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray, this.resourcesProvider));
        ViewGroup.MarginLayoutParams r0 = (ViewGroup.MarginLayoutParams) r2.getLayoutParams();
        r0.topMargin = -AndroidUtilities.dp(6.0f);
        return;
    }

    @Override // org.telegram.ui.Components.Premium.PremiumPreviewBottomSheet
    public void attachIconContainer(LinearLayout r10) {
        View r0 = this.overrideTitleIcon;
        if (this.selectedUsers.size() != 1) goto L6;
        int r1 = 94;
    L5:
        int r4 = r1;
        if (this.selectedUsers.size() != 1) goto L11;
        float r12 = 28.0f;
    L10:
        float r6 = r12;
        if (this.selectedUsers.size() != 1) goto L16;
        float r9 = 9.0f;
    L17:
        r10.addView(r0, LayoutHelper.createLinear(-1, r4, 0.0f, r6, 0.0f, r9));
        return;
    L16:
        r9 = 14.0f;
        goto L17
    L11:
        r12 = 34.0f;
        goto L10
    L6:
        r1 = 83;
        goto L5
    }

    @Override // org.telegram.ui.Components.Premium.PremiumPreviewBottomSheet
    public boolean needDefaultPremiumBtn() {
        return false;
    }

    @Override // org.telegram.ui.Components.Premium.PremiumPreviewBottomSheet
    public void setTitle(boolean r9) {
        this.titleView[0].setTextSize(1, 20.0f);
        this.subtitleView.setPadding(AndroidUtilities.dp(30.0f), 0, AndroidUtilities.dp(30.0f), 0);
        this.subtitleView.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        this.titleView[0].setText(LocaleController.getPluralString("GiftPremiumGiftsSent", this.selectedUsers.size()));
        ((ViewGroup.MarginLayoutParams) this.subtitleView.getLayoutParams()).bottomMargin = AndroidUtilities.dp(16.0f);
        ((ViewGroup.MarginLayoutParams) this.subtitleView.getLayoutParams()).topMargin = AndroidUtilities.dp(4.0f);
        int r92 = this.selectedUsers.size();
        if (r92 != 1) goto L5;
        String r93 = LocaleController.formatString(R.string.GiftPremiumUsersPurchasedManyZero, new Object[]{LocaleController.formatString(R.string.GiftPremiumUsersOne, new Object[]{UserObject.getFirstName(this.selectedUsers.get(0))})});
    L12:
        this.subtitleView.setText(AndroidUtilities.replaceTags(r93));
        this.subtitleView.append("\n");
        this.subtitleView.append("\n");
        int r94 = this.selectedUsers.size();
        LinkSpanDrawable.LinksTextView r1 = this.subtitleView;
        if (r94 != 1) goto L16;
        r1.append(AndroidUtilities.replaceTags(LocaleController.formatString("GiftPremiumGiftsSentStatusForUser", R.string.GiftPremiumGiftsSentStatusForUser, new Object[]{UserObject.getFirstName(this.selectedUsers.get(0))})));
        return;
    L16:
        r1.append(AndroidUtilities.replaceTags(LocaleController.getString("GiftPremiumGiftsSentStatus", R.string.GiftPremiumGiftsSentStatus)));
        return;
    L5:
        if (r92 == 2) goto L10;
        List<TLRPC.User> r4 = this.selectedUsers;
        if (r92 == 3) goto L9;
        r93 = LocaleController.formatPluralString("GiftPremiumUsersPurchasedMany", this.selectedUsers.size() - 3, new Object[]{LocaleController.formatString("GiftPremiumUsersThree", R.string.GiftPremiumUsersThree, new Object[]{UserObject.getFirstName(r4.get(0)), UserObject.getFirstName(this.selectedUsers.get(1)), UserObject.getFirstName(this.selectedUsers.get(2))})});
        goto L12
    L9:
        r93 = LocaleController.formatString("GiftPremiumUsersPurchasedManyZero", R.string.GiftPremiumUsersPurchasedManyZero, new Object[]{LocaleController.formatString("GiftPremiumUsersThree", R.string.GiftPremiumUsersThree, new Object[]{UserObject.getFirstName(r4.get(0)), UserObject.getFirstName(this.selectedUsers.get(1)), UserObject.getFirstName(this.selectedUsers.get(2))})});
        goto L12
    L10:
        r93 = LocaleController.formatString("GiftPremiumUsersPurchasedManyZero", R.string.GiftPremiumUsersPurchasedManyZero, new Object[]{LocaleController.formatString("GiftPremiumUsersTwo", R.string.GiftPremiumUsersTwo, new Object[]{UserObject.getFirstName(this.selectedUsers.get(0)), UserObject.getFirstName(this.selectedUsers.get(1))})});
        goto L12
    }

    @Override // org.telegram.ui.Components.Premium.PremiumPreviewBottomSheet
    public void updateRows() {
        this.rowCount = 1;
        this.paddingRow = 0;
        this.featuresStartRow = 1;
        int r1 = this.premiumFeatures.size();
        int r0 = 1 + r1;
        this.featuresEndRow = r0;
        this.rowCount = r1 + 2;
        this.termsRow = r0;
    }
}
