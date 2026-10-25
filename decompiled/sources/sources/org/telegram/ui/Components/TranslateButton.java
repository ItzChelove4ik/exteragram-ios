package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.translator.TranslationProviders;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.TranslateController;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChannelMonetizationLayout;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.RestrictedLanguagesSelectActivity;
import org.telegram.ui.Stars.StarGiftSheet;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.Stories.recorder.HintView2;

/* loaded from: classes7.dex */
public abstract class TranslateButton extends FrameLayout implements Theme.Colorable {
    private boolean[] accusative;
    private final int currentAccount;
    private final long dialogId;
    private final BaseFragment fragment;
    private ImageView menuView;
    private Theme.ResourcesProvider resourcesProvider;
    private AnimatedTextView textView;
    private final Drawable translateDrawable;
    public final SpannableString translateIcon;

    /* renamed from: org.telegram.ui.Components.TranslateButton$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatedTextView {
        final /* synthetic */ TranslateButton this$0;

        public AnonymousClass1(TranslateButton r1, Context r2, boolean r3, boolean r4, boolean r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.AnimatedTextView, android.view.View
        public void onDraw(Canvas r3) {
            r3.save();
            r3.translate(AndroidUtilities.dp(17.0f), 0.0f);
            super.onDraw(r3);
            r3.restore();
        }
    }

    /* renamed from: org.telegram.ui.Components.TranslateButton$2, reason: invalid class name */
    public class AnonymousClass2 extends ScrollView {
        AnimatedFloat alphaFloat;
        final /* synthetic */ TranslateButton this$0;
        Drawable topShadowDrawable;
        private boolean wasCanScrollVertically;

        public AnonymousClass2(TranslateButton r3, Context r4) {
            this.this$0 = r3;
            super(r4);
            this.alphaFloat = new AnimatedFloat(this, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r7) {
            super.dispatchDraw(r7);
            AnimatedFloat r0 = this.alphaFloat;
            if (canScrollVertically(-1) == false) goto L5;
            float r1 = 1.0f;
        L6:
            float r02 = r0.set(r1) * 0.5f;
            if (r02 > 0.0f) goto L9;
            return;
        L9:
            if (this.topShadowDrawable != null) goto L11;
            this.topShadowDrawable = getContext().getResources().getDrawable(R.drawable.header_shadow);
        L11:
            this.topShadowDrawable.setBounds(0, getScrollY(), getWidth(), getScrollY() + this.topShadowDrawable.getIntrinsicHeight());
            this.topShadowDrawable.setAlpha((int) (r02 * 255.0f));
            this.topShadowDrawable.draw(r7);
            return;
        L5:
            r1 = 0.0f;
            goto L6
        }

        @Override // android.widget.ScrollView, android.view.ViewGroup, android.view.ViewParent
        public void onNestedScroll(View r1, int r2, int r3, int r4, int r5) {
            super.onNestedScroll(r1, r2, r3, r4, r5);
            boolean r12 = canScrollVertically(-1);
            if (this.wasCanScrollVertically == r12) goto L6;
            invalidate();
            this.wasCanScrollVertically = r12;
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$7IrBAcGxwA9QBQqZsA5EUoMbDQM(BottomSheet[] r1, Context r2) {
        r1[0].dismiss();
        Browser.openUrl(r2, LocaleController.getString(R.string.CocoonFeature1TextLink));
    }

    public static /* synthetic */ void $r8$lambda$9w0XAaZqX1OQYTCOJuAp6kUhyMQ(TranslateButton r0, ActionBarPopupWindow r1) {
        r0.lambda$onMenuClick$11(r1);
    }

    public static /* synthetic */ void $r8$lambda$JatsdPFLV35vQgFerZd29lewOgU(TranslateButton r0, int r1, long r2, View r4) {
        r0.lambda$new$1(r1, r2, r4);
    }

    public static /* synthetic */ void $r8$lambda$Jt6yGtoCbiYFXlVmmJak6tlzyI0(TranslateButton r0, TranslateController r1) {
        r0.lambda$onMenuClick$9(r1);
    }

    /* renamed from: $r8$lambda$PJdC9EYRvEPLSh10Nu-P4BFrUTA, reason: not valid java name */
    public static /* synthetic */ void m13425$r8$lambda$PJdC9EYRvEPLSh10NuP4BFrUTA(ActionBarPopupWindow.ActionBarPopupWindowLayout r0, View r1) {
        r0.getSwipeBack().closeForeground();
    }

    public static /* synthetic */ void $r8$lambda$QOPZCN4BYYZwvBbhxHNM7hs5CeI(TranslateButton r0, TranslateController r1, String r2, ActionBarPopupWindow r3, View r4) {
        r0.lambda$onMenuClick$3(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$TD2I6POaZ7CKHW15KPwYGNPXWkY(BottomSheet[] r1, Context r2) {
        r1[0].dismiss();
        Browser.openUrl(r2, LocaleController.getString(R.string.CocoonFooterLink));
    }

    public static /* synthetic */ void $r8$lambda$UN7KHYS9rI_6wPq2Azm3zssQWAw(TranslateButton r0) {
        r0.lambda$onMenuClick$7();
    }

    /* renamed from: $r8$lambda$WGN10rdPjHfyFHF5TwR_-w1DlS4, reason: not valid java name */
    public static /* synthetic */ void m13426$r8$lambda$WGN10rdPjHfyFHF5TwR_w1DlS4(TranslateButton r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$cNB5kJGCsckrsDzpNWtSzmssJ-c, reason: not valid java name */
    public static /* synthetic */ void m13427$r8$lambda$cNB5kJGCsckrsDzpNWtSzmssJc(TranslateButton r0, String r1, TranslateController r2, String r3, ActionBarPopupWindow r4, View r5) {
        r0.lambda$onMenuClick$8(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$eTgrLI6_9iOaXRFEUubSLGZRyWA(TranslateButton r0, boolean[] r1, String r2, LinearLayout r3, ArrayList r4, String r5, TranslateController r6, ActionBarPopupWindow r7, ArrayList r8) {
        r0.lambda$onMenuClick$5(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$eXRBulNztwdcklNHccX9znXgCqQ(TranslateButton r0, ActionBarPopupWindow r1, View r2) {
        r0.lambda$onMenuClick$12(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$esQe9JtamP7M9KjqxifYQ15wmgQ(Runnable r0, ActionBarPopupWindow.ActionBarPopupWindowLayout r1, int r2, View r3) {
        r0.run();
        r1.getSwipeBack().openForeground(r2);
    }

    public static /* synthetic */ void $r8$lambda$o3ZwMH9GdLWJXTgzAhqM4vHZ2KE(TranslateButton r0, TranslateController r1, ActionBarPopupWindow r2, View r3) {
        r0.lambda$onMenuClick$10(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$t6Z43_oSqRjcaerI7uTXt81ruec(TranslateButton r0, TranslateController r1, String r2, ActionBarPopupWindow r3, View r4) {
        r0.lambda$onMenuClick$4(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$vq1vT0FsDR-8gKIoa_OEDM4oa24, reason: not valid java name */
    public static /* synthetic */ void m13428$r8$lambda$vq1vT0FsDR8gKIoa_OEDM4oa24(BottomSheet[] r0, View r1) {
        r0[0].dismiss();
    }

    /* renamed from: $r8$lambda$wIFbv-Scs9SbeMJEoA7JlxUZaR8, reason: not valid java name */
    public static /* synthetic */ void m13429$r8$lambda$wIFbvScs9SbeMJEoA7JlxUZaR8(BottomSheet[] r1, Context r2) {
        r1[0].dismiss();
        Browser.openUrlInSystemBrowser(r2, LocaleController.getString(R.string.CocoonFeature3TextLink));
    }

    public TranslateButton(Context r18, int r19, long r20, BaseFragment r22, Theme.ResourcesProvider r23) {
        super(r18);
        this.accusative = new boolean[1];
        this.currentAccount = r19;
        this.dialogId = r20;
        this.fragment = r22;
        this.resourcesProvider = r23;
        AnonymousClass1 r10 = new AnonymousClass1(this, r18, true, true, false);
        this.textView = r10;
        r10.setAnimationProperties(0.3f, 0, 450, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.textView.setTextSize(AndroidUtilities.dp(14.0f));
        this.textView.setTypeface(AndroidUtilities.bold());
        this.textView.setPadding(AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f), 0);
        this.textView.setGravity(1);
        this.textView.setIgnoreRTL(!LocaleController.isRTL);
        AnimatedTextView r0 = this.textView;
        r0.adaptWidth = false;
        r0.setOnClickListener(new TranslateButton$$ExternalSyntheticLambda0(this));
        addView(this.textView, LayoutHelper.createFrame(-1, -1.0f, 3, 0.0f, 0.0f, 34.0f, 0.0f));
        Drawable r02 = getContext().getResources().getDrawable(R.drawable.msg_translate).mutate();
        this.translateDrawable = r02;
        r02.setBounds(0, AndroidUtilities.dp(-6.0f), AndroidUtilities.dp(20.0f), AndroidUtilities.dp(14.0f));
        SpannableString r2 = new SpannableString("x");
        this.translateIcon = r2;
        r2.setSpan(new ImageSpan(r02, 0), 0, 1, 33);
        ImageView r03 = new ImageView(r18);
        this.menuView = r03;
        r03.setScaleType(ImageView.ScaleType.CENTER);
        this.menuView.setImageResource(R.drawable.msg_mini_customize);
        this.menuView.setOnClickListener(new TranslateButton$$ExternalSyntheticLambda1(this, r19, r20));
        addView(this.menuView, LayoutHelper.createFrame(30, 30.0f, 21, 0.0f, 0.0f, 7.0f, 0.0f));
        updateColors();
    }

    public TranslateButton(Context r8, ChatActivity r9, Theme.ResourcesProvider r10) {
        this(r8, r9.getCurrentAccount(), r9.getDialogId(), r9, r10);
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        onButtonClick();
    }

    private /* synthetic */ void lambda$new$1(int r1, long r2, View r4) {
        if (TranslationProviders.isChatTranslationUnlocked(r1, r2) == false) goto L6;
        onMenuClick();
        return;
    L6:
        onCloseClick();
    }

    private /* synthetic */ void lambda$onMenuClick$10(TranslateController r5, ActionBarPopupWindow r6, View r7) {
        r5.setHideTranslateDialog(this.dialogId, true);
        TLRPC.Chat r72 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId));
        if (r72 != null) goto L5;
    L7:
        if (r72 == null) goto L9;
        String r73 = LocaleController.getString(R.string.TranslationBarHiddenForGroup);
    L10:
        SpannableStringBuilder r74 = AndroidUtilities.replaceTags(r73);
        BulletinFactory.of(this.fragment).createSimpleBulletin(R.raw.msg_translate, r74, LocaleController.getString(R.string.UndoNoCaps), new TranslateButton$$ExternalSyntheticLambda9(this, r5)).show();
        r6.dismiss();
        return;
    L9:
        r73 = LocaleController.getString(R.string.TranslationBarHiddenForChat);
        goto L10
    L5:
        if (ChatObject.isChannelAndNotMegaGroup(r72) == false) goto L7;
        r73 = LocaleController.getString(R.string.TranslationBarHiddenForChannel);
        goto L10
    }

    private /* synthetic */ void lambda$onMenuClick$11(ActionBarPopupWindow r1) {
        r1.dismiss();
        showCocoonAlert(getContext(), this.resourcesProvider);
    }

    private /* synthetic */ void lambda$onMenuClick$12(ActionBarPopupWindow r1, View r2) {
        r1.dismiss();
        showCocoonAlert(getContext(), this.resourcesProvider);
    }

    private /* synthetic */ void lambda$onMenuClick$3(TranslateController r3, String r4, ActionBarPopupWindow r5, View r6) {
        r3.setDialogTranslateTo(this.dialogId, r4);
        r5.dismiss();
        updateText();
    }

    private /* synthetic */ void lambda$onMenuClick$4(TranslateController r3, String r4, ActionBarPopupWindow r5, View r6) {
        r3.setDialogTranslateTo(this.dialogId, r4);
        r5.dismiss();
        updateText();
    }

    private /* synthetic */ void lambda$onMenuClick$5(boolean[] r22, String r23, LinearLayout r24, ArrayList r25, String r26, TranslateController r27, ActionBarPopupWindow r28, ArrayList r29) {
        boolean r6 = false;
        if (r22[0] == false) goto L6;
        return;
    L6:
        if (r23 == null) goto L10;
        String r8 = TranslateAlert2.capitalFirst(TranslateAlert2.languageName(r23));
        if (r8 == null) goto L10;
        ActionBarMenuSubItem r9 = new ActionBarMenuSubItem(getContext(), 2, false, false, this.resourcesProvider);
        r9.setChecked(true);
        r9.setText(r8);
        r24.addView(r9);
    L10:
        int r82 = r25.size();
        int r92 = 0;
    L11:
        if (r92 >= r82) goto L25;
        Object r11 = r25.get(r92);
        r92 = r92 + 1;
        TranslateController.Language r112 = (TranslateController.Language) r11;
        String r12 = r112.code;
        if (TextUtils.equals(r12, r26) == true) goto L11;
        ActionBarMenuSubItem r14 = new ActionBarMenuSubItem(getContext(), 2, false, false, this.resourcesProvider);
        if (r23 != null) goto L18;
    L20:
        boolean r13 = false;
    L21:
        r14.setChecked(r13);
        r14.setText(r112.displayName);
        if (r13 == true) goto L24;
        r14.setOnClickListener(new TranslateButton$$ExternalSyntheticLambda15(this, r27, r12, r28));
    L24:
        r24.addView(r14);
        goto L11
    L18:
        if (r23.equals(r12) == false) goto L20;
        r13 = true;
        goto L21
    L25:
        r24.addView(new ActionBarPopupWindow.GapView(getContext(), this.resourcesProvider), LayoutHelper.createLinear(-1, 8));
        int r83 = r29.size();
        int r93 = 0;
    L26:
        if (r93 >= r83) goto L39;
        Object r113 = r29.get(r93);
        r93 = r93 + 1;
        TranslateController.Language r114 = (TranslateController.Language) r113;
        String r122 = r114.code;
        if (TextUtils.equals(r122, r26) == true) goto L26;
        if (r23 != null) goto L32;
    L34:
        boolean r132 = r6;
    L35:
        boolean r20 = r6;
        ActionBarMenuSubItem r142 = new ActionBarMenuSubItem(getContext(), 2, false, false, this.resourcesProvider);
        r142.setChecked(r132);
        r142.setText(r114.displayName);
        if (r132 == true) goto L38;
        r142.setOnClickListener(new TranslateButton$$ExternalSyntheticLambda16(this, r27, r122, r28));
    L38:
        r24.addView(r142);
        r6 = r20;
        goto L26
    L32:
        if (r23.equals(r122) == false) goto L34;
        r132 = true;
        goto L35
    L39:
        r22[r6 ? 1 : 0] = true;
    }

    private /* synthetic */ void lambda$onMenuClick$7() {
        this.fragment.presentFragment(new RestrictedLanguagesSelectActivity());
    }

    private /* synthetic */ void lambda$onMenuClick$8(String r3, TranslateController r4, String r5, ActionBarPopupWindow r6, View r7) {
        RestrictedLanguagesSelectActivity.toggleLanguage(r3, true);
        r4.checkRestrictedLanguagesUpdate();
        r4.setHideTranslateDialog(this.dialogId, true);
        if (this.accusative[0] == false) goto L5;
        String r32 = LocaleController.formatString(R.string.AddedToDoNotTranslate, new Object[]{r5});
    L6:
        CharSequence r33 = TranslateAlert2.capitalFirst(AndroidUtilities.replaceTags(r32));
        BulletinFactory.of(this.fragment).createSimpleBulletin(R.raw.msg_translate, r33, LocaleController.getString(R.string.Settings), new TranslateButton$$ExternalSyntheticLambda14(this)).show();
        r6.dismiss();
        return;
    L5:
        r32 = LocaleController.formatString(R.string.AddedToDoNotTranslateOther, new Object[]{r5});
        goto L6
    }

    private /* synthetic */ void lambda$onMenuClick$9(TranslateController r3) {
        r3.setHideTranslateDialog(this.dialogId, false);
    }

    public static void showCocoonAlert(Context r27, Theme.ResourcesProvider r28) {
        BottomSheet.Builder r6 = new BottomSheet.Builder(r27, false, r28);
        r6.setApplyBottomPadding(false);
        r6.setApplyTopPadding(false);
        BottomSheet[] r9 = new BottomSheet[1];
        LinearLayout r10 = new LinearLayout(r27);
        r10.setOrientation(1);
        FrameLayout r0 = new FrameLayout(r27);
        GradientDrawable r2 = new GradientDrawable();
        r2.setGradientType(1);
        r2.setColors(new int[]{-15982491, -16379606});
        r2.setGradientRadius(AndroidUtilities.dp(150.0f));
        float r3 = AndroidUtilities.dp(12.0f);
        r2.setCornerRadii(new float[]{r3, r3, r3, r3, 0.0f, 0.0f, 0.0f, 0.0f});
        r0.setBackground(r2);
        LinearLayout r22 = new LinearLayout(r27);
        r22.setOrientation(1);
        r0.addView(r22, LayoutHelper.createFrame(-1, -1, 119));
        ImageView r32 = new ImageView(r27);
        r32.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        r32.setImageResource(R.drawable.cocoon_logo);
        r22.addView(r32, LayoutHelper.createLinear(132, 132, 49, 0, 33, 0, 0));
        ImageView r33 = new ImageView(r27);
        r33.setImageResource(R.drawable.cocoon_text);
        r22.addView(r33, LayoutHelper.createLinear(-2, -2, 49, 32, 12, 32, 0));
        TextView r34 = new TextView(r27);
        r34.setTextColor(-4666897);
        r34.setTextSize(1, 13.0f);
        r34.setGravity(17);
        SpannableStringBuilder r13 = AndroidUtilities.replaceTags(LocaleController.getString(R.string.CocoonSubtitle));
        TypefaceSpan[] r14 = (TypefaceSpan[]) r13.getSpans(0, r13.length(), TypefaceSpan.class);
        int r15 = 0;
    L4:
        if (r15 >= r14.length) goto L6;
        r13.setSpan(new ForegroundColorSpan(-1), r13.getSpanStart(r14[r15]), r13.getSpanEnd(r14[r15]), r13.getSpanFlags(r14[r15]));
        r15 = r15 + 1;
        goto L4
    L6:
        r34.setText(r13);
        r22.addView(r34, LayoutHelper.createFrame(-1, -2.0f, 49, 32.0f, 14.0f, 32.0f, 20.0f));
        r10.addView(r0, LayoutHelper.createFrame(-1, -2.0f));
        r10.addView(new ChannelMonetizationLayout.FeatureCell(r27, R.drawable.menu_privacy, LocaleController.getString(R.string.CocoonFeature1Title), AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.CocoonFeature1Text), new TranslateButton$$ExternalSyntheticLambda10(r9, r27)), r28), LayoutHelper.createLinear(-1, -2, 49, 32, 16, 32, 16));
        r10.addView(new ChannelMonetizationLayout.FeatureCell(r27, R.drawable.msg_stats, LocaleController.getString(R.string.CocoonFeature2Title), LocaleController.getString(R.string.CocoonFeature2Text), r28), LayoutHelper.createLinear(-1, -2, 49, 32, 0, 32, 16));
        r10.addView(new ChannelMonetizationLayout.FeatureCell(r27, R.drawable.menu_gift, LocaleController.getString(R.string.CocoonFeature3Title), AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.CocoonFeature3Text), new TranslateButton$$ExternalSyntheticLambda11(r9, r27)), r28), LayoutHelper.createLinear(-1, -2, 49, 32, 0, 32, 16));
        View r02 = new View(r27);
        r02.setBackgroundColor(Theme.getColor(Theme.key_divider, r28));
        r10.addView(r02, LayoutHelper.createLinear(-1, 1.0f / AndroidUtilities.density, 7, 24, 0, 24, 0));
        LinkSpanDrawable.LinksTextView r03 = new LinkSpanDrawable.LinksTextView(r27);
        r03.setTextSize(1, 12.0f);
        r03.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, r28));
        r03.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r28));
        r03.setGravity(17);
        r03.setText(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.CocoonFooter), new TranslateButton$$ExternalSyntheticLambda12(r9, r27)));
        r03.setPadding(0, AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f));
        r03.setDisablePaddingsOffsetY(true);
        r10.addView(r03, LayoutHelper.createLinear(-1, -2, 7, 32, 0, 32, 0));
        ButtonWithCounterView r04 = new ButtonWithCounterView(r27, r28).setRound();
        r04.setText(StarGiftSheet.replaceUnderstood(LocaleController.getString(R.string.Understood)));
        r04.setOnClickListener(new TranslateButton$$ExternalSyntheticLambda13(r9));
        r10.addView(r04, LayoutHelper.createLinear(-1, 48, 7, 16, 0, 16, 16));
        r6.setCustomView(r10);
        BottomSheet r05 = r6.create();
        r9[0] = r05;
        r05.fixNavigationBar();
        r9[0].show();
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    public abstract void onButtonClick();

    public abstract void onCloseClick();

    public void onMenuClick() {
        TranslateController r3 = MessagesController.getInstance(this.currentAccount).getTranslateController();
        ActionBarPopupWindow.ActionBarPopupWindowLayout r10 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(getContext(), R.drawable.popup_fixed_alert4, this.resourcesProvider, 1);
        ActionBarPopupWindow r5 = new ActionBarPopupWindow(r10, -2, -2);
        r10.setBackgroundColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground, this.resourcesProvider));
        LinearLayout r0 = new LinearLayout(getContext());
        r0.setOrientation(1);
        AnonymousClass2 r2 = new AnonymousClass2(this, getContext());
        LinearLayout r4 = new LinearLayout(getContext());
        r2.addView(r4);
        r4.setOrientation(1);
        r10.swipeBackGravityRight = true;
        int r12 = r10.addViewToSwipeBack(r0);
        ActionBarMenuSubItem r13 = new ActionBarMenuSubItem(getContext(), true, false, this.resourcesProvider);
        r13.setTextAndIcon(LocaleController.getString(R.string.TranslateTo), R.drawable.msg_translate);
        r13.setSubtext(TranslateAlert2.capitalFirst(TranslateAlert2.languageName(r3.getDialogTranslateTo(this.dialogId))));
        r13.setItemHeight(56);
        r10.addView(r13);
        ActionBarMenuSubItem r6 = new ActionBarMenuSubItem(getContext(), true, false, this.resourcesProvider);
        r6.setTextAndIcon(LocaleController.getString(R.string.Back), R.drawable.ic_ab_back);
        r6.setOnClickListener(new TranslateButton$$ExternalSyntheticLambda2(r10));
        r0.addView(r6);
        r0.addView(r2, LayoutHelper.createLinear(-1, MediaError.DetailedErrorCode.DASH_MANIFEST_UNKNOWN));
        String r22 = r3.getDialogDetectedLanguage(this.dialogId);
        TranslateAlert2.languageName(r22);
        String r16 = TranslateAlert2.languageName(r22, this.accusative);
        String r02 = r3.getDialogTranslateTo(this.dialogId);
        ArrayList<TranslateController.Language> r52 = TranslateController.getSuggestedLanguages(r02);
        ArrayList<TranslateController.Language> r9 = TranslateController.getLanguages();
        r4.addView(new ActionBarPopupWindow.GapView(getContext(), this.resourcesProvider), LayoutHelper.createLinear(-1, 8));
        r13.setOnClickListener(new TranslateButton$$ExternalSyntheticLambda4(new TranslateButton$$ExternalSyntheticLambda3(this, new boolean[1], r02, r4, r52, r22, r3, r5, r9), r10, r12));
        if (TranslationProviders.isChatTranslationUnlocked(this.currentAccount, this.dialogId) == false) goto L10;
        if (r16 == null) goto L10;
        ActionBarMenuSubItem r92 = new ActionBarMenuSubItem(getContext(), false, false, this.resourcesProvider);
        if (this.accusative[0] == false) goto L8;
        String r03 = LocaleController.formatString(R.string.DoNotTranslateLanguage, new Object[]{r16});
    L9:
        r92.setMultiline(false);
        r92.setTextAndIcon(HintView2.cutInFancyHalfText(r03, r92.getTextView().getPaint()), R.drawable.msg_block2);
        r92.setOnClickListener(new TranslateButton$$ExternalSyntheticLambda5(this, r22, r3, r16, r5));
        r10.addView(r92);
        goto L10
    L8:
        r03 = LocaleController.formatString(R.string.DoNotTranslateLanguageOther, new Object[]{r16});
    L10:
        ActionBarMenuSubItem r04 = new ActionBarMenuSubItem(getContext(), false, false, this.resourcesProvider);
        r04.setTextAndIcon(LocaleController.getString(R.string.Hide), R.drawable.msg_cancel);
        r04.setOnClickListener(new TranslateButton$$ExternalSyntheticLambda6(this, r3, r5));
        r10.addView(r04);
        boolean r05 = TranslationProviders.isTelegram();
        if (r05 == false) goto L13;
        r10.addView(new ActionBarPopupWindow.GapView(getContext(), this.resourcesProvider), LayoutHelper.createLinear(-1, 8));
    L13:
        LinkSpanDrawable.LinksTextView r23 = new LinkSpanDrawable.LinksTextView(getContext());
        r23.setPadding(AndroidUtilities.dp(13.0f), AndroidUtilities.dp(8.33f), AndroidUtilities.dp(13.0f), AndroidUtilities.dp(8.33f));
        r23.setDisablePaddingsOffsetY(true);
        int r32 = Theme.key_dialogTextBlack;
        r23.setTextColor(Theme.getColor(r32, this.resourcesProvider));
        r23.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, this.resourcesProvider));
        r23.setEmojiColor(Theme.getColor(r32, this.resourcesProvider));
        CharSequence r42 = TextUtils.concat(new CharSequence[]{AndroidUtilities.replaceTags(LocaleController.getString(R.string.CocoonPoweredBy)), " ", AndroidUtilities.premiumText(LocaleController.getString(R.string.CocoonPoweredByLink), new TranslateButton$$ExternalSyntheticLambda7(this, r5))});
        SpannableStringBuilder r53 = new SpannableStringBuilder("🥚");
        r53.setSpan(new AnimatedEmojiSpan(5197252827247841976L, r23.getPaint().getFontMetricsInt()), 0, r53.length(), 33);
        SpannableStringBuilder r7 = new SpannableStringBuilder(r53);
        r7.append(" ");
        r23.setText(HintView2.cutInFancyHalfText(AndroidUtilities.replaceCharSequence("🥚", AndroidUtilities.replaceCharSequence("🥚 ", r42, r7), r53), r23.getPaint()));
        r23.setBackground(Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_listSelector, this.resourcesProvider), 0, 12));
        r23.setOnClickListener(new TranslateButton$$ExternalSyntheticLambda8(this, r5));
        if (r05 == false) goto L16;
        r10.addView(r23);
    L16:
        r5.setPauseNotifications(true);
        r5.setDismissAnimationDuration(Opcodes.REM_INT_LIT8);
        r5.setOutsideTouchable(true);
        r5.setClippingEnabled(true);
        r5.setAnimationStyle(R.style.PopupContextAnimation);
        r5.setFocusable(true);
        r5.setInputMethodMode(2);
        r5.setSoftInputMode(0);
        ImageView r06 = this.menuView;
        r5.showAsDropDown(r06, 0, (-r06.getMeasuredHeight()) - AndroidUtilities.dp(8.0f));
    }

    public void setLeftMargin(float r2) {
        this.textView.setTranslationX(r2 / 2.0f);
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        AnimatedTextView r0 = this.textView;
        int r1 = Theme.key_chat_addContact;
        r0.setTextColor(Theme.getColor(r1, this.resourcesProvider));
        this.textView.setBackground(Theme.createInsetRoundRectDrawable(Theme.getColor(r1, this.resourcesProvider) & 436207615, AndroidUtilities.dp(15.0f), AndroidUtilities.dp(3.0f)));
        this.menuView.setBackground(Theme.createCircleSelectorDrawable(Theme.getColor(Theme.key_listSelector, this.resourcesProvider), 0, 0));
        ImageView r02 = this.menuView;
        int r3 = Theme.getColor(Theme.key_chat_topPanelClose, this.resourcesProvider);
        PorterDuff.Mode r4 = PorterDuff.Mode.MULTIPLY;
        r02.setColorFilter(new PorterDuffColorFilter(r3, r4));
        this.translateDrawable.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r1, this.resourcesProvider), r4));
    }

    public void updateText() {
        TranslateController r0 = MessagesController.getInstance(this.currentAccount).getTranslateController();
        MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId));
        boolean r1 = r0.isTranslatingDialog(this.dialogId);
        long r2 = this.dialogId;
        if (r1 == false) goto L8;
        String r02 = TranslateAlert2.languageName(r0.getDialogDetectedLanguage(r2));
        boolean r12 = TextUtils.isEmpty(r02);
        AnimatedTextView r22 = this.textView;
        if (r12 == true) goto L7;
        r22.setText(TextUtils.concat(new CharSequence[]{this.translateIcon, " ", LocaleController.formatString(R.string.ShowOriginalButtonLanguage, new Object[]{r02})}));
    L16:
        ImageView r03 = this.menuView;
        if (TranslationProviders.isChatTranslationUnlocked(this.currentAccount, this.dialogId) == false) goto L19;
        int r9 = R.drawable.msg_mini_customize;
    L20:
        r03.setImageResource(r9);
        return;
    L19:
        r9 = R.drawable.msg_close;
        goto L20
    L7:
        r22.setText(TextUtils.concat(new CharSequence[]{this.translateIcon, " ", LocaleController.getString(R.string.ShowOriginalButton)}));
        goto L16
    L8:
        String r04 = r0.getDialogTranslateTo(r2);
        if (r04 != null) goto L11;
        r04 = "en";
    L11:
        String r05 = TranslateAlert2.languageName(r04, this.accusative);
        if (this.accusative[0] == false) goto L14;
        String r06 = LocaleController.formatString(R.string.TranslateToButton, new Object[]{r05});
    L15:
        this.textView.setText(TextUtils.concat(new CharSequence[]{this.translateIcon, " ", r06}));
        goto L16
    L14:
        r06 = LocaleController.formatString(R.string.TranslateToButtonOther, new Object[]{r05});
        goto L15
    }
}
