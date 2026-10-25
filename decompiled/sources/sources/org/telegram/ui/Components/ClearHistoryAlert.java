package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.CheckBoxCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.SlideChooseView;

/* loaded from: classes7.dex */
public class ClearHistoryAlert extends BottomSheet {
    private boolean autoDeleteOnly;
    private CheckBoxCell cell;
    private int currentTimer;
    private ClearHistoryAlertDelegate delegate;
    private boolean dismissedDelayed;
    private LinearLayout linearLayout;
    private int[] location;
    private int newTimer;
    private int scrollOffsetY;
    private BottomSheetCell setTimerButton;
    private Drawable shadowDrawable;

    /* renamed from: org.telegram.ui.Components.ClearHistoryAlert$1, reason: invalid class name */
    public class AnonymousClass1 extends NestedScrollView {
        private boolean ignoreLayout;
        final /* synthetic */ ClearHistoryAlert this$0;

        public AnonymousClass1(ClearHistoryAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r6) {
            int r0 = (int) (((ClearHistoryAlert.m10018$$Nest$fgetscrollOffsetY(this.this$0) - ClearHistoryAlert.access$000(this.this$0)) + getScrollY()) - getTranslationY());
            ClearHistoryAlert.m10019$$Nest$fgetshadowDrawable(this.this$0).setBounds(0, r0, getMeasuredWidth(), ((ClearHistoryAlert.m10017$$Nest$fgetlinearLayout(this.this$0).getMeasuredHeight() + r0) + ClearHistoryAlert.access$100(this.this$0)) + AndroidUtilities.dp(19.0f));
            ClearHistoryAlert.m10019$$Nest$fgetshadowDrawable(this.this$0).draw(r6);
        }

        @Override // androidx.core.widget.NestedScrollView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L11;
            if (ClearHistoryAlert.m10018$$Nest$fgetscrollOffsetY(this.this$0) == 0) goto L11;
            if (r3.getY() >= ClearHistoryAlert.m10018$$Nest$fgetscrollOffsetY(this.this$0)) goto L11;
            this.this$0.dismiss();
            return true;
        L11:
            return super.onInterceptTouchEvent(r3);
        }

        @Override // androidx.core.widget.NestedScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            ClearHistoryAlert.m10021$$Nest$mupdateLayout(this.this$0);
        }

        @Override // androidx.core.widget.NestedScrollView, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r9, int r10) {
            int r0 = View.MeasureSpec.getSize(r10);
            measureChildWithMargins(ClearHistoryAlert.m10017$$Nest$fgetlinearLayout(this.this$0), r9, 0, r10, 0);
            int r8 = ClearHistoryAlert.m10017$$Nest$fgetlinearLayout(this.this$0).getMeasuredHeight();
            int r92 = (r0 / 5) * 3;
            int r102 = r0 - r92;
            if (ClearHistoryAlert.m10016$$Nest$fgetautoDeleteOnly(this.this$0) == false) goto L5;
        L11:
            r92 = r0 - r8;
        L13:
            if (getPaddingTop() == r92) goto L15;
            this.ignoreLayout = true;
            setPadding(0, r92, 0, 0);
            this.ignoreLayout = false;
        L15:
            super.onMeasure(r9, View.MeasureSpec.makeMeasureSpec(r0, TLObject.FLAG_30));
            return;
        L5:
            if ((r8 - r102) < AndroidUtilities.dp(90.0f)) goto L11;
            if (r8 < ((r0 / 2) + AndroidUtilities.dp(90.0f))) goto L11;
            r8 = (r8 / 2) + AndroidUtilities.dp(108.0f);
            if (r102 >= r8) goto L13;
            goto L11
        }

        @Override // androidx.core.widget.NestedScrollView, android.view.View
        public void onScrollChanged(int r1, int r2, int r3, int r4) {
            super.onScrollChanged(r1, r2, r3, r4);
            ClearHistoryAlert.m10021$$Nest$mupdateLayout(this.this$0);
        }

        @Override // androidx.core.widget.NestedScrollView, android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (this.this$0.isDismissed() == false) goto L5;
            return false;
        L5:
            if (super.onTouchEvent(r2) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // androidx.core.widget.NestedScrollView, android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }

        @Override // android.view.View
        public void setTranslationY(float r1) {
            super.setTranslationY(r1);
            ClearHistoryAlert.m10021$$Nest$mupdateLayout(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.ClearHistoryAlert$2, reason: invalid class name */
    public class AnonymousClass2 extends LinearLayout {
        final /* synthetic */ ClearHistoryAlert this$0;

        public AnonymousClass2(ClearHistoryAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            ClearHistoryAlert.m10021$$Nest$mupdateLayout(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.ClearHistoryAlert$3, reason: invalid class name */
    public class AnonymousClass3 implements SlideChooseView.Callback {
        final /* synthetic */ ClearHistoryAlert this$0;
        final /* synthetic */ NestedScrollView val$scrollView;

        public AnonymousClass3(ClearHistoryAlert r1, NestedScrollView r2) {
            this.this$0 = r1;
            this.val$scrollView = r2;
        }

        @Override // org.telegram.ui.Components.SlideChooseView.Callback
        public void onOptionSelected(int r2) {
            ClearHistoryAlert.m10020$$Nest$fputnewTimer(this.this$0, r2);
            ClearHistoryAlert.m10022$$Nest$mupdateTimerButton(this.this$0, true);
        }

        @Override // org.telegram.ui.Components.SlideChooseView.Callback
        public void onTouchEnd() {
            this.val$scrollView.smoothScrollTo(0, ClearHistoryAlert.m10017$$Nest$fgetlinearLayout(this.this$0).getMeasuredHeight());
        }
    }

    public static class BottomSheetCell extends FrameLayout {
        private View background;
        private final Theme.ResourcesProvider resourcesProvider;
        private TextView textView;

        /* renamed from: -$$Nest$fgetbackground, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m10023$$Nest$fgetbackground(BottomSheetCell r0) {
            return r0.background;
        }

        public BottomSheetCell(Context r8, Theme.ResourcesProvider r9) {
            super(r8);
            this.resourcesProvider = r9;
            View r92 = new View(r8);
            this.background = r92;
            r92.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(4.0f), getThemedColor(Theme.key_featuredStickers_addButton), getThemedColor(Theme.key_featuredStickers_addButtonPressed)));
            addView(this.background, LayoutHelper.createFrame(-1, -1.0f, 0, 16.0f, 16.0f, 16.0f, 16.0f));
            TextView r93 = new TextView(r8);
            this.textView = r93;
            r93.setLines(1);
            this.textView.setSingleLine(true);
            this.textView.setGravity(1);
            this.textView.setEllipsize(TextUtils.TruncateAt.END);
            this.textView.setGravity(17);
            this.textView.setTextColor(getThemedColor(Theme.key_featuredStickers_buttonText));
            this.textView.setTextSize(1, 14.0f);
            this.textView.setTypeface(AndroidUtilities.bold());
            addView(this.textView, LayoutHelper.createFrame(-2, -2, 17));
        }

        public int getThemedColor(int r1) {
            return Theme.getColor(r1, this.resourcesProvider);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(80.0f), TLObject.FLAG_30));
        }

        public void setText(CharSequence r1) {
            this.textView.setText(r1);
        }
    }

    public interface ClearHistoryAlertDelegate {
        void onAutoDeleteHistory(int r1, int r2);

        default void onClearHistory(boolean r1) {
        }
    }

    public static /* synthetic */ void $r8$lambda$1ND7MYo2Kr5tsw8oCXneBpH6z4Q(ClearHistoryAlert r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$EZ1C5bzlmLE_6knIy35pvM4pXbU(boolean[] r3, View r4) {
        boolean r1 = !r3[0];
        r3[0] = r1;
        ((CheckBoxCell) r4).setChecked(r1, true);
    }

    public static /* synthetic */ void $r8$lambda$zBFwmyky2FwVAIdqrc2NGm5MW5A(ClearHistoryAlert r0, View r1) {
        r0.lambda$new$2(r1);
    }

    /* renamed from: -$$Nest$fgetautoDeleteOnly, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10016$$Nest$fgetautoDeleteOnly(ClearHistoryAlert r0) {
        return r0.autoDeleteOnly;
    }

    /* renamed from: -$$Nest$fgetlinearLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m10017$$Nest$fgetlinearLayout(ClearHistoryAlert r0) {
        return r0.linearLayout;
    }

    /* renamed from: -$$Nest$fgetscrollOffsetY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10018$$Nest$fgetscrollOffsetY(ClearHistoryAlert r0) {
        return r0.scrollOffsetY;
    }

    /* renamed from: -$$Nest$fgetshadowDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m10019$$Nest$fgetshadowDrawable(ClearHistoryAlert r0) {
        return r0.shadowDrawable;
    }

    /* renamed from: -$$Nest$fputnewTimer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10020$$Nest$fputnewTimer(ClearHistoryAlert r0, int r1) {
        r0.newTimer = r1;
    }

    /* renamed from: -$$Nest$mupdateLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10021$$Nest$mupdateLayout(ClearHistoryAlert r0) {
        r0.updateLayout();
    }

    /* renamed from: -$$Nest$mupdateTimerButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10022$$Nest$mupdateTimerButton(ClearHistoryAlert r0, boolean r1) {
        r0.updateTimerButton(r1);
    }

    public ClearHistoryAlert(Context r26, TLRPC.User r27, TLRPC.Chat r28, boolean r29, Theme.ResourcesProvider r30) {
        super(r26, false, r30);
        this.location = new int[2];
        this.autoDeleteOnly = !r29;
        setApplyBottomPadding(false);
        int r7 = this.currentAccount;
        if (r27 == null) goto L8;
        TLRPC.UserFull r72 = MessagesController.getInstance(r7).getUserFull(r27.id);
        if (r72 == null) goto L7;
        int r73 = r72.ttl_period;
    L12:
        if (r73 != 0) goto L15;
        this.currentTimer = 0;
        this.newTimer = 0;
    L21:
        Drawable r6 = r26.getResources().getDrawable(R.drawable.sheet_shadow_round).mutate();
        this.shadowDrawable = r6;
        int r10 = Theme.key_dialogBackground;
        r6.setColorFilter(new PorterDuffColorFilter(getThemedColor(r10), PorterDuff.Mode.MULTIPLY));
        AnonymousClass1 r62 = new AnonymousClass1(this, r26);
        r62.setFillViewport(true);
        r62.setWillNotDraw(false);
        r62.setClipToPadding(false);
        int r74 = this.backgroundPaddingLeft;
        r62.setPadding(r74, 0, r74, 0);
        this.containerView = r62;
        AnonymousClass2 r75 = new AnonymousClass2(this, r26);
        this.linearLayout = r75;
        r75.setOrientation(1);
        r62.addView(this.linearLayout, LayoutHelper.createScroll(-1, -2, 80));
        setCustomView(this.linearLayout);
        long r14 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        if (r27 != null) goto L24;
    L30:
        boolean r12 = false;
    L31:
        int r13 = this.currentAccount;
        if (r27 == null) goto L34;
        int r132 = MessagesController.getInstance(r13).revokeTimePmLimit;
    L35:
        if (r27 == null) goto L40;
        if (r12 == false) goto L40;
        if (r132 != Integer.MAX_VALUE) goto L40;
        boolean r122 = true;
    L41:
        boolean[] r133 = {false};
        float r15 = 20.0f;
        if (this.autoDeleteOnly == true) goto L79;
        TextView r142 = new TextView(r26);
        r142.setTypeface(AndroidUtilities.bold());
        r142.setTextSize(1, 20.0f);
        int r76 = Theme.key_dialogTextBlack;
        r142.setTextColor(getThemedColor(r76));
        r142.setText(LocaleController.getString(R.string.ClearHistory));
        r142.setSingleLine(true);
        r142.setEllipsize(TextUtils.TruncateAt.END);
        this.linearLayout.addView(r142, LayoutHelper.createLinear(-2, -2, 51, 23, 20, 23, 0));
        TextView r8 = new TextView(getContext());
        r8.setTextColor(getThemedColor(r76));
        r8.setTextSize(1, 16.0f);
        r8.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
        r8.setLinkTextColor(getThemedColor(Theme.key_dialogTextLink));
        if (LocaleController.isRTL == false) goto L46;
        int r11 = 5;
    L47:
        r8.setGravity(r11 | 48);
        this.linearLayout.addView(r8, LayoutHelper.createLinear(-2, -2, 51, 23, 16, 23, 5));
        if (r27 == null) goto L51;
        r8.setText(AndroidUtilities.replaceTags(LocaleController.formatString("AreYouSureClearHistoryWithUser", R.string.AreYouSureClearHistoryWithUser, new Object[]{UserObject.getUserName(r27)})));
    L62:
        if (r122 == true) goto L64;
    L74:
        BottomSheetCell r2 = new BottomSheetCell(r26, r30);
        r2.setBackground(null);
        r2.setText(LocaleController.getString(R.string.AlertClearHistory));
        BottomSheetCell.m10023$$Nest$fgetbackground(r2).setOnClickListener(new ClearHistoryAlert$$ExternalSyntheticLambda1(this));
        this.linearLayout.addView(r2, LayoutHelper.createLinear(-1, 50, 51, 0, 0, 0, 0));
        View r22 = new ShadowSectionCell(r26);
        CombinedDrawable r77 = new CombinedDrawable(new ColorDrawable(getThemedColor(Theme.key_windowBackgroundGray)), Theme.getThemedDrawableByKey(r26, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
        r77.setFullsize(true);
        r22.setBackgroundDrawable(r77);
        this.linearLayout.addView(r22, LayoutHelper.createLinear(-1, -2));
        HeaderCell r23 = new HeaderCell(r26, r30);
        r23.setText(LocaleController.getString(R.string.AutoDeleteHeader));
        LinearLayout r3 = this.linearLayout;
        if (this.autoDeleteOnly == true) goto L78;
        r15 = 0.0f;
    L78:
        r3.addView(r23, LayoutHelper.createLinear(-1, -2, 1.0f, r15, 1.0f, 0.0f));
    L89:
        SlideChooseView r24 = new SlideChooseView(r26, r30);
        r24.setCallback(new AnonymousClass3(this, r62));
        r24.setOptions(this.currentTimer, new String[]{LocaleController.getString(R.string.AutoDeleteNever), LocaleController.getString(R.string.AutoDelete24Hours), LocaleController.getString(R.string.AutoDelete7Days), LocaleController.getString(R.string.AutoDelete1Month)});
        this.linearLayout.addView(r24, LayoutHelper.createLinear(-1, -2, 0.0f, 8.0f, 0.0f, 0.0f));
        FrameLayout r25 = new FrameLayout(r26);
        CombinedDrawable r63 = new CombinedDrawable(new ColorDrawable(getThemedColor(Theme.key_windowBackgroundGray)), Theme.getThemedDrawableByKey(r26, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
        r63.setFullsize(true);
        r25.setBackgroundDrawable(r63);
        this.linearLayout.addView(r25, LayoutHelper.createLinear(-1, -2));
        TextInfoPrivacyCell r32 = new TextInfoPrivacyCell(r26, r30);
        r32.setText(LocaleController.getString(R.string.AutoDeleteInfo));
        r25.addView(r32);
        BottomSheetCell r33 = new BottomSheetCell(r26, r30);
        this.setTimerButton = r33;
        r33.setBackgroundColor(getThemedColor(r10));
        if (this.autoDeleteOnly == false) goto L92;
        this.setTimerButton.setText(LocaleController.getString(R.string.AutoDeleteSet));
    L97:
        BottomSheetCell.m10023$$Nest$fgetbackground(this.setTimerButton).setOnClickListener(new ClearHistoryAlert$$ExternalSyntheticLambda2(this));
        r25.addView(this.setTimerButton);
        updateTimerButton(false);
        return;
    L92:
        if (r29 == true) goto L94;
    L96:
        this.setTimerButton.setText(LocaleController.getString(R.string.AutoDeleteConfirm));
        goto L97
    L94:
        if (this.currentTimer != 0) goto L96;
        this.setTimerButton.setText(LocaleController.getString(R.string.EnableAutoDelete));
        goto L97
    L64:
        if (UserObject.isDeleted(r27) == true) goto L74;
        CheckBoxCell r34 = new CheckBoxCell(r26, 1, r30);
        this.cell = r34;
        r34.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        this.cell.setText(LocaleController.formatString("ClearHistoryOptionAlso", R.string.ClearHistoryOptionAlso, new Object[]{UserObject.getFirstName(r27)}), _UrlKt.FRAGMENT_ENCODE_SET, false, false);
        CheckBoxCell r210 = this.cell;
        if (LocaleController.isRTL == false) goto L68;
        int r35 = AndroidUtilities.dp(16.0f);
    L70:
        if (LocaleController.isRTL == false) goto L72;
        int r78 = AndroidUtilities.dp(5.0f);
    L73:
        r210.setPadding(r35, 0, r78, 0);
        this.linearLayout.addView(this.cell, LayoutHelper.createLinear(-1, 48, 51, 0, 0, 0, 0));
        this.cell.setOnClickListener(new ClearHistoryAlert$$ExternalSyntheticLambda0(r133));
        goto L74
    L72:
        r78 = AndroidUtilities.dp(16.0f);
        goto L73
    L68:
        r35 = AndroidUtilities.dp(5.0f);
        goto L70
    L51:
        if (ChatObject.isChannel(r28) == true) goto L53;
    L61:
        r8.setText(AndroidUtilities.replaceTags(LocaleController.formatString("AreYouSureClearHistoryWithChat", R.string.AreYouSureClearHistoryWithChat, new Object[]{r28.title})));
        goto L62
    L53:
        if (r28.megagroup == false) goto L58;
        if (ChatObject.isPublic(r28) == false) goto L61;
    L58:
        if (r28.megagroup == false) goto L60;
        r8.setText(LocaleController.getString(R.string.AreYouSureClearHistoryGroup));
        goto L62
    L60:
        r8.setText(LocaleController.getString(R.string.AreYouSureClearHistoryChannel));
        goto L62
    L46:
        r11 = 3;
        goto L47
    L79:
        RLottieImageView r82 = new RLottieImageView(r26);
        r82.setAutoRepeat(false);
        r82.setAnimation(R.raw.utyan_private, 120, 120);
        r82.setPadding(0, AndroidUtilities.dp(20.0f), 0, 0);
        r82.playAnimation();
        this.linearLayout.addView(r82, LayoutHelper.createLinear(160, 160, 49, 17, 0, 17, 0));
        TextView r83 = new TextView(r26);
        r83.setTypeface(AndroidUtilities.bold());
        r83.setTextSize(1, 24.0f);
        r83.setTextColor(getThemedColor(Theme.key_dialogTextBlack));
        r83.setText(LocaleController.getString(R.string.AutoDeleteAlertTitle));
        this.linearLayout.addView(r83, LayoutHelper.createLinear(-2, -2, 49, 17, 18, 17, 0));
        TextView r84 = new TextView(r26);
        r84.setTextSize(1, 14.0f);
        r84.setTextColor(getThemedColor(Theme.key_dialogTextGray3));
        r84.setGravity(1);
        if (r27 == null) goto L83;
        r84.setText(LocaleController.formatString("AutoDeleteAlertUserInfo", R.string.AutoDeleteAlertUserInfo, new Object[]{UserObject.getFirstName(r27)}));
    L88:
        this.linearLayout.addView(r84, LayoutHelper.createLinear(-2, -2, 49, 30, 22, 30, 20));
        goto L89
    L83:
        if (ChatObject.isChannel(r28) == true) goto L85;
    L87:
        r84.setText(LocaleController.getString(R.string.AutoDeleteAlertGroupInfo));
        goto L88
    L85:
        if (r28.megagroup == true) goto L87;
        r84.setText(LocaleController.getString(R.string.AutoDeleteAlertChannelInfo));
    L40:
        r122 = false;
        goto L41
    L34:
        r132 = MessagesController.getInstance(r13).revokeTimeLimit;
        goto L35
    L24:
        if (r27.bot == true) goto L30;
        if (r27.id == r14) goto L30;
        if (MessagesController.getInstance(this.currentAccount).canRevokePmInbox == false) goto L30;
        r12 = true;
        goto L31
    L15:
        if (r73 != 86400) goto L18;
        this.currentTimer = 1;
        this.newTimer = 1;
        goto L21
    L18:
        if (r73 != 604800) goto L20;
        this.currentTimer = 2;
        this.newTimer = 2;
        goto L21
    L20:
        this.currentTimer = 3;
        this.newTimer = 3;
    L7:
        r73 = 0;
        goto L12
    L8:
        TLRPC.ChatFull r79 = MessagesController.getInstance(r7).getChatFull(r28.id);
        if (r79 == null) goto L7;
        r73 = r79.ttl_period;
        goto L12
    }

    public static /* synthetic */ int access$000(ClearHistoryAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$100(ClearHistoryAlert r0) {
        return r0.backgroundPaddingTop;
    }

    private /* synthetic */ void lambda$new$1(View r2) {
        if (this.dismissedDelayed == false) goto L5;
        return;
    L5:
        ClearHistoryAlertDelegate r22 = this.delegate;
        CheckBoxCell r0 = this.cell;
        if (r0 != null) goto L8;
    L10:
        boolean r02 = false;
    L11:
        r22.onClearHistory(r02);
        dismiss();
        return;
    L8:
        if (r0.isChecked() == false) goto L10;
        r02 = true;
        goto L11
    }

    private /* synthetic */ void lambda$new$2(View r4) {
        if (this.dismissedDelayed == false) goto L5;
        return;
    L5:
        int r42 = this.newTimer;
        if (r42 == this.currentTimer) goto L18;
        this.dismissedDelayed = true;
        int r2 = 70;
        if (r42 != 3) goto L11;
        int r43 = 2678400;
    L16:
        this.delegate.onAutoDeleteHistory(r43, r2);
        goto L18
    L11:
        if (r42 != 2) goto L13;
        r43 = 604800;
        goto L16
    L13:
        if (r42 != 1) goto L15;
        r43 = 86400;
        goto L16
    L15:
        r43 = 0;
        r2 = 71;
    L18:
        if (this.dismissedDelayed == false) goto L21;
        AndroidUtilities.runOnUIThread(new ClearHistoryAlert$$ExternalSyntheticLambda3(this), 200);
        return;
    L21:
        dismiss();
    }

    private void updateLayout() {
        this.linearLayout.getChildAt(0).getLocationInWindow(this.location);
        int r0 = this.location[1];
        if (this.autoDeleteOnly == false) goto L5;
        float r2 = 6.0f;
    L6:
        int r02 = Math.max(r0 - AndroidUtilities.dp(r2), 0);
        if (this.scrollOffsetY == r02) goto L10;
        this.scrollOffsetY = r02;
        this.containerView.invalidate();
        return;
    L10:
        return;
    L5:
        r2 = 19.0f;
        goto L6
    }

    private void updateTimerButton(boolean r5) {
        if (this.currentTimer == this.newTimer) goto L5;
    L12:
        this.setTimerButton.setVisibility(0);
        BottomSheetCell r4 = this.setTimerButton;
        if (r5 == false) goto L16;
        r4.animate().alpha(1.0f).setDuration(180).start();
        return;
    L16:
        r4.setAlpha(1.0f);
        return;
    L5:
        if (this.autoDeleteOnly == true) goto L12;
        BottomSheetCell r0 = this.setTimerButton;
        if (r5 == false) goto L10;
        r0.animate().alpha(0.0f).setDuration(180).start();
        return;
    L10:
        r0.setVisibility(4);
        this.setTimerButton.setAlpha(0.0f);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        return false;
    }

    public void setDelegate(ClearHistoryAlertDelegate r1) {
        this.delegate = r1;
    }
}
