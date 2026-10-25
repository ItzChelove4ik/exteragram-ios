package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.core.graphics.ColorUtils;
import androidx.mediarouter.media.PlatformMediaRouter1RouteProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.EmojiThemes;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Components.ChatThemeBottomSheet;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ThemeSmallPreviewView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes6.dex */
public class DefaultThemesPreviewCell extends LinearLayout implements Theme.Colorable {
    private final ChatThemeBottomSheet.Adapter adapter;
    int currentType;
    RLottieDrawable darkThemeDrawable;
    TextCell dayNightCell;
    private LinearLayoutManager layoutManager;
    private ValueAnimator navBarAnimator;
    private int navBarColor;
    BaseFragment parentFragment;
    private final FlickerLoadingView progressView;
    private final RecyclerListView recyclerView;
    private int selectedPosition;
    int themeIndex;
    private Boolean wasPortrait;

    /* renamed from: org.telegram.ui.DefaultThemesPreviewCell$1, reason: invalid class name */
    public class AnonymousClass1 extends RecyclerListView {
        final /* synthetic */ DefaultThemesPreviewCell this$0;

        public AnonymousClass1(DefaultThemesPreviewCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public Integer getSelectorColor(int r1) {
            return 0;
        }
    }

    /* renamed from: org.telegram.ui.DefaultThemesPreviewCell$2, reason: invalid class name */
    public class AnonymousClass2 implements View.OnClickListener {
        final /* synthetic */ DefaultThemesPreviewCell this$0;
        final /* synthetic */ Context val$context;
        final /* synthetic */ BaseFragment val$parentFragment;

        /* renamed from: org.telegram.ui.DefaultThemesPreviewCell$2$1, reason: invalid class name */
        public class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
            final /* synthetic */ AnonymousClass2 this$1;
            final /* synthetic */ Activity val$activity;
            final /* synthetic */ float val$duration;
            final /* synthetic */ float val$fullDuration;
            final /* synthetic */ int val$navBarFromColor;
            final /* synthetic */ int val$navBarNewColor;
            final /* synthetic */ float val$startDelay;

            public AnonymousClass1(AnonymousClass2 r1, float r2, float r3, float r4, int r5, int r6, Activity r7) {
                this.this$1 = r1;
                this.val$fullDuration = r2;
                this.val$startDelay = r3;
                this.val$duration = r4;
                this.val$navBarFromColor = r5;
                this.val$navBarNewColor = r6;
                this.val$activity = r7;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator r4) {
                float r42 = Math.max(0.0f, Math.min(1.0f, ((((Float) r4.getAnimatedValue()).floatValue() * this.val$fullDuration) - this.val$startDelay) / this.val$duration));
                DefaultThemesPreviewCell.m14273$$Nest$fputnavBarColor(this.this$1.this$0, ColorUtils.blendARGB(this.val$navBarFromColor, this.val$navBarNewColor, r42));
                boolean r1 = false;
                AndroidUtilities.setNavigationBarColor(this.val$activity, DefaultThemesPreviewCell.m14271$$Nest$fgetnavBarColor(this.this$1.this$0), false);
                Activity r43 = this.val$activity;
                if (AndroidUtilities.computePerceivedBrightness(DefaultThemesPreviewCell.m14271$$Nest$fgetnavBarColor(this.this$1.this$0)) < 0.721f) goto L5;
                r1 = true;
            L5:
                AndroidUtilities.setLightNavigationBar(r43, r1);
            }
        }

        /* renamed from: org.telegram.ui.DefaultThemesPreviewCell$2$2, reason: invalid class name and collision with other inner class name */
        public class C00642 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass2 this$1;
            final /* synthetic */ Activity val$activity;
            final /* synthetic */ int val$navBarNewColor;

            public C00642(AnonymousClass2 r1, Activity r2, int r3) {
                this.this$1 = r1;
                this.val$activity = r2;
                this.val$navBarNewColor = r3;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r3) {
                boolean r1 = false;
                AndroidUtilities.setNavigationBarColor(this.val$activity, this.val$navBarNewColor, false);
                Activity r32 = this.val$activity;
                if (AndroidUtilities.computePerceivedBrightness(this.val$navBarNewColor) < 0.721f) goto L5;
                r1 = true;
            L5:
                AndroidUtilities.setLightNavigationBar(r32, r1);
            }
        }

        /* renamed from: $r8$lambda$pMtX-nAQK94I7kOZkjAPL-yE1vY, reason: not valid java name */
        public static /* synthetic */ void m14275$r8$lambda$pMtXnAQK94I7kOZkjAPLyE1vY(AnonymousClass2 r0, Context r1, int r2, boolean r3, BaseFragment r4) {
            r0.lambda$onClick$0(r1, r2, r3, r4);
        }

        public AnonymousClass2(DefaultThemesPreviewCell r1, Context r2, BaseFragment r3) {
            this.this$0 = r1;
            this.val$context = r2;
            this.val$parentFragment = r3;
        }

        private /* synthetic */ void lambda$onClick$0(Context r10, int r11, boolean r12, BaseFragment r13) {
            this.this$0.updateDayNightMode();
            DefaultThemesPreviewCell.m14274$$Nest$mupdateSelectedPosition(this.this$0);
            boolean r0 = Theme.isCurrentThemeDay();
            DefaultThemesPreviewCell r1 = this.this$0;
            if (r0 == false) goto L5;
            r1.dayNightCell.textView.setText(LocaleController.getString(R.string.SettingsSwitchToNightMode));
        L6:
            PorterDuffColorFilter r14 = new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4), PorterDuff.Mode.SRC_IN);
            this.this$0.darkThemeDrawable.setColorFilter(r14);
            this.this$0.dayNightCell.getImageView().setColorFilter(r14);
            this.this$0.dayNightCell.getImageView().setImageDrawable(this.this$0.darkThemeDrawable);
            this.this$0.dayNightCell.getImageView().setVisibility(0);
            int r7 = Theme.getColor(Theme.key_windowBackgroundGray);
            Window r15 = null;
            if ((r10 instanceof Activity) == false) goto L9;
            Activity r8 = (Activity) r10;
        L10:
            if (r8 == null) goto L12;
            r15 = r8.getWindow();
        L12:
            if (r15 != null) goto L14;
        L29:
            Theme.turnOffAutoNight(r13);
            return;
        L14:
            if (DefaultThemesPreviewCell.m14270$$Nest$fgetnavBarAnimator(this.this$0) == null) goto L19;
            if (DefaultThemesPreviewCell.m14270$$Nest$fgetnavBarAnimator(this.this$0).isRunning() == false) goto L19;
            DefaultThemesPreviewCell.m14270$$Nest$fgetnavBarAnimator(this.this$0).cancel();
        L19:
            if (DefaultThemesPreviewCell.m14270$$Nest$fgetnavBarAnimator(this.this$0) != null) goto L21;
        L23:
            int r6 = r11;
            DefaultThemesPreviewCell.m14272$$Nest$fputnavBarAnimator(this.this$0, ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}));
            if (r12 == false) goto L27;
            float r102 = 50.0f;
        L28:
            DefaultThemesPreviewCell.m14270$$Nest$fgetnavBarAnimator(this.this$0).addUpdateListener(new AnonymousClass1(this, 350.0f, r102, 150.0f, r6, r7, r8));
            DefaultThemesPreviewCell.m14270$$Nest$fgetnavBarAnimator(this.this$0).addListener(new C00642(this, r8, r7));
            DefaultThemesPreviewCell.m14270$$Nest$fgetnavBarAnimator(this.this$0).setDuration(350);
            DefaultThemesPreviewCell.m14270$$Nest$fgetnavBarAnimator(this.this$0).start();
            goto L29
        L27:
            r102 = 200.0f;
            goto L28
        L21:
            if (DefaultThemesPreviewCell.m14270$$Nest$fgetnavBarAnimator(this.this$0).isRunning() == false) goto L23;
            r11 = DefaultThemesPreviewCell.m14271$$Nest$fgetnavBarColor(this.this$0);
            goto L23
        L9:
            r8 = null;
            goto L10
        L5:
            r1.dayNightCell.textView.setText(LocaleController.getString(R.string.SettingsSwitchToDayMode));
            goto L6
        }

        @Override // android.view.View.OnClickListener
        @SuppressLint({"NotifyDataSetChanged"})
        public void onClick(View r14) {
            if (DialogsActivity.switchingTheme == false) goto L5;
            return;
        L5:
            int r3 = Theme.getColor(Theme.key_windowBackgroundGray);
            DialogsActivity.switchingTheme = true;
            SharedPreferences r0 = ApplicationLoader.applicationContext.getSharedPreferences("themeconfig", 0);
            String r4 = "Blue";
            String r1 = r0.getString("lastDayTheme", "Blue");
            if (Theme.getTheme(r1) != null) goto L8;
        L9:
            r1 = "Blue";
        L10:
            String r6 = "Dark Blue";
            String r02 = r0.getString("lastDarkTheme", "Dark Blue");
            if (Theme.getTheme(r02) != null) goto L13;
        L14:
            r02 = "Dark Blue";
        L15:
            Theme.ThemeInfo r5 = Theme.getActiveTheme();
            if (r1.equals(r02) == true) goto L18;
            r6 = r02;
        L24:
            r4 = r1;
        L27:
            boolean r03 = Theme.isCurrentThemeDark();
            String r12 = r4;
            boolean r42 = !r03;
            if (r03 == true) goto L31;
            Theme.ThemeInfo r13 = Theme.getTheme(r6);
        L30:
            Theme.ThemeInfo r62 = r13;
            RLottieDrawable r15 = this.this$0.darkThemeDrawable;
            if (r03 == true) goto L35;
            int r04 = r15.getFramesCount() - 1;
        L36:
            r15.setCustomEndFrame(r04);
            this.this$0.dayNightCell.getImageView().playAnimation();
            int[] r7 = new int[2];
            this.this$0.dayNightCell.getImageView().getLocationInWindow(r7);
            r7[0] = r7[0] + (this.this$0.dayNightCell.getImageView().getMeasuredWidth() / 2);
            r7[1] = r7[1] + ((this.this$0.dayNightCell.getImageView().getMeasuredHeight() / 2) + AndroidUtilities.dp(3.0f));
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needSetDayNightTheme, new Object[]{r62, Boolean.FALSE, r7, -1, Boolean.valueOf(r42), this.this$0.dayNightCell.getImageView(), this.this$0.dayNightCell, new DefaultThemesPreviewCell$2$$ExternalSyntheticLambda0(this, this.val$context, r3, r42, this.val$parentFragment)});
            return;
        L35:
            r04 = 0;
            goto L36
        L31:
            r13 = Theme.getTheme(r12);
            goto L30
        L18:
            if (r5.isDark() == false) goto L20;
        L25:
            r6 = r02;
            goto L27
        L20:
            if (r1.equals("Dark Blue") == true) goto L25;
            if (r1.equals("Night") == false) goto L24;
        L13:
            if (Theme.getTheme(r02).isDark() == true) goto L15;
        L8:
            if (Theme.getTheme(r1).isDark() == false) goto L10;
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.DefaultThemesPreviewCell$3, reason: invalid class name */
    public class AnonymousClass3 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ DefaultThemesPreviewCell this$0;

        public AnonymousClass3(DefaultThemesPreviewCell r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r1) {
            return 1;
        }
    }

    /* renamed from: $r8$lambda$Q8wN6hVIZ-NxU7A4iAIcFJj4wfY, reason: not valid java name */
    public static /* synthetic */ void m14269$r8$lambda$Q8wN6hVIZNxU7A4iAIcFJj4wfY(DefaultThemesPreviewCell r0, BaseFragment r1, View r2, int r3) {
        r0.lambda$new$0(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetnavBarAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m14270$$Nest$fgetnavBarAnimator(DefaultThemesPreviewCell r0) {
        return r0.navBarAnimator;
    }

    /* renamed from: -$$Nest$fgetnavBarColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14271$$Nest$fgetnavBarColor(DefaultThemesPreviewCell r0) {
        return r0.navBarColor;
    }

    /* renamed from: -$$Nest$fputnavBarAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14272$$Nest$fputnavBarAnimator(DefaultThemesPreviewCell r0, ValueAnimator r1) {
        r0.navBarAnimator = r1;
    }

    /* renamed from: -$$Nest$fputnavBarColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14273$$Nest$fputnavBarColor(DefaultThemesPreviewCell r0, int r1) {
        r0.navBarColor = r1;
    }

    /* renamed from: -$$Nest$mupdateSelectedPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14274$$Nest$mupdateSelectedPosition(DefaultThemesPreviewCell r0) {
        r0.updateSelectedPosition();
    }

    public DefaultThemesPreviewCell(Context r20, BaseFragment r21, int r22) {
        super(r20);
        this.layoutManager = null;
        this.selectedPosition = -1;
        this.wasPortrait = null;
        this.currentType = r22;
        this.parentFragment = r21;
        setOrientation(1);
        FrameLayout r6 = new FrameLayout(r20);
        addView(r6, LayoutHelper.createFrame(-1, -2.0f));
        int r9 = r21.getCurrentAccount();
        int r10 = this.currentType;
        int r11 = 0;
        if (r10 == 0) goto L7;
        if (r10 == (-1)) goto L7;
        int r102 = 1;
    L8:
        ChatThemeBottomSheet.Adapter r8 = new ChatThemeBottomSheet.Adapter(r9, null, r102);
        this.adapter = r8;
        AnonymousClass1 r92 = new AnonymousClass1(this, getContext());
        this.recyclerView = r92;
        r92.setAdapter(r8);
        r92.setSelectorDrawableColor(0);
        r92.setClipChildren(false);
        r92.setClipToPadding(false);
        r92.setHasFixedSize(true);
        r92.setItemAnimator(null);
        r92.setNestedScrollingEnabled(false);
        updateLayoutManager();
        r92.setFocusable(false);
        r92.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
        r92.setOnItemClickListener(new DefaultThemesPreviewCell$$ExternalSyntheticLambda0(this, r21));
        FlickerLoadingView r103 = new FlickerLoadingView(getContext(), null);
        this.progressView = r103;
        r103.setViewType(14);
        r103.setVisibility(0);
        int r3 = this.currentType;
        if (r3 == 0) goto L13;
        if (r3 == (-1)) goto L13;
        r6.addView(r103, LayoutHelper.createFrame(-1, 104.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 0.0f, 8.0f, 0.0f, 8.0f));
        r6.addView(r92, LayoutHelper.createFrame(-1, -2.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 0.0f, 8.0f, 0.0f, 8.0f));
    L14:
        r92.setEmptyView(r103);
        r92.setAnimateEmptyView(true, 0);
        if (this.currentType != 0) goto L21;
        RLottieDrawable r12 = new RLottieDrawable(R.raw.sun_outline, AndroidUtilities.dp(28.0f), AndroidUtilities.dp(28.0f), true, null);
        this.darkThemeDrawable = r12;
        r12.setPlayInDirectionOfCustomEndFrame(true);
        this.darkThemeDrawable.beginApplyLayerColors();
        this.darkThemeDrawable.commitApplyLayerColors();
        TextCell r32 = new TextCell(r20);
        this.dayNightCell = r32;
        r32.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector), 2));
        TextCell r33 = this.dayNightCell;
        r33.imageLeft = 21;
        addView(r33, LayoutHelper.createFrame(-1, -2.0f));
        this.dayNightCell.setOnClickListener(new AnonymousClass2(this, r20, r21));
        this.darkThemeDrawable.setPlayInDirectionOfCustomEndFrame(true);
        if (Theme.isCurrentThemeDay() == true) goto L19;
        RLottieDrawable r1 = this.darkThemeDrawable;
        r1.setCurrentFrame(r1.getFramesCount() - 1);
        this.dayNightCell.setTextAndIcon(LocaleController.getString(R.string.SettingsSwitchToDayMode), this.darkThemeDrawable, true);
        goto L21
    L19:
        this.dayNightCell.setTextAndIcon(LocaleController.getString(R.string.SettingsSwitchToNightMode), this.darkThemeDrawable, true);
    L21:
        if (MediaDataController.getInstance(r21.getCurrentAccount()).defaultEmojiThemes.isEmpty() == true) goto L29;
        ArrayList r13 = new ArrayList(MediaDataController.getInstance(r21.getCurrentAccount()).defaultEmojiThemes);
        if (this.currentType != 0) goto L28;
        EmojiThemes r34 = EmojiThemes.createPreviewCustom(r21.getCurrentAccount());
        r34.loadPreviewColors(r21.getCurrentAccount());
        ChatThemeBottomSheet.ChatThemeItem r2 = new ChatThemeBottomSheet.ChatThemeItem(r34);
        if (Theme.isCurrentThemeDay() == true) goto L27;
        r11 = 2;
    L27:
        r2.themeIndex = r11;
        r13.add(r2);
    L28:
        r8.setItems(r13);
    L29:
        updateDayNightMode();
        updateSelectedPosition();
        updateColors();
        int r14 = this.selectedPosition;
        if (r14 < 0) goto L35;
        LinearLayoutManager r0 = this.layoutManager;
        if (r0 == null) goto L36;
        r0.scrollToPositionWithOffset(r14, AndroidUtilities.dp(16.0f));
        return;
    L36:
        return;
    L35:
        return;
    L13:
        r6.addView(r103, LayoutHelper.createFrame(-1, 104.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 0.0f, 8.0f, 0.0f, 8.0f));
        r6.addView(r92, LayoutHelper.createFrame(-1, 104.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 0.0f, 8.0f, 0.0f, 8.0f));
    L7:
        r102 = 0;
        goto L8
    }

    private /* synthetic */ void lambda$new$0(BaseFragment r7, View r8, int r9) {
        ChatThemeBottomSheet.ChatThemeItem r0 = this.adapter.items.get(r9);
        Theme.ThemeInfo r1 = r0.chatTheme.getThemeInfo(this.themeIndex);
        if (r0.chatTheme.getEmoticonOrSlug().equals("🏠") == false) goto L5;
    L8:
        int r2 = r0.chatTheme.getAccentId(this.themeIndex);
    L9:
        if (r1 != null) goto L17;
        TLRPC.TL_theme r12 = r0.chatTheme.getTlTheme(this.themeIndex);
        Theme.ThemeInfo r02 = Theme.getTheme(Theme.getBaseThemeKey(r12.settings.get(r0.chatTheme.getSettingsIndex(this.themeIndex))));
        if (r02 == null) goto L16;
        Theme.ThemeAccent r22 = r02.accentsByThemeId.get(r12.id);
        if (r22 != null) goto L15;
        r22 = r02.createNewAccent(r12, r7.getCurrentAccount());
    L15:
        r2 = r22.id;
        r02.setCurrentAccentId(r2);
    L16:
        r1 = r02;
    L17:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needSetDayNightTheme, new Object[]{r1, Boolean.FALSE, null, Integer.valueOf(r2)});
        this.selectedPosition = r9;
        int r03 = 0;
    L18:
        int r23 = this.adapter.items.size();
        ChatThemeBottomSheet.Adapter r3 = this.adapter;
        boolean r4 = true;
        if (r03 >= r23) goto L25;
        ChatThemeBottomSheet.ChatThemeItem r24 = r3.items.get(r03);
        if (r03 == this.selectedPosition) goto L24;
        r4 = false;
    L24:
        r24.isSelected = r4;
        r03 = r03 + 1;
        goto L18
    L25:
        r3.setSelectedItem(this.selectedPosition);
        int r04 = 0;
    L27:
        if (r04 >= this.recyclerView.getChildCount()) goto L32;
        ThemeSmallPreviewView r25 = (ThemeSmallPreviewView) this.recyclerView.getChildAt(r04);
        if (r25 == r8) goto L31;
        r25.cancelAnimation();
    L31:
        r04 = r04 + 1;
        goto L27
    L32:
        ((ThemeSmallPreviewView) r8).playEmojiAnimation();
        if (r1 == null) goto L42;
        SharedPreferences.Editor r82 = ApplicationLoader.applicationContext.getSharedPreferences("themeconfig", 0).edit();
        if (this.currentType != 1) goto L37;
    L40:
        String r6 = "lastDarkTheme";
    L41:
        r82.putString(r6, r1.getKey());
        r82.apply();
        goto L42
    L37:
        if (r1.isDark() == true) goto L40;
        r6 = "lastDayTheme";
    L42:
        Theme.turnOffAutoNight(r7);
        return;
    L5:
        if (r0.chatTheme.getEmoticonOrSlug().equals("🎨") == true) goto L8;
        r2 = -1;
        goto L9
    }

    private void updateSelectedPosition() {
        if (this.adapter.items != null) goto L5;
        return;
    L5:
        this.selectedPosition = -1;
        int r2 = 0;
    L7:
        if (r2 >= this.adapter.items.size()) goto L28;
        TLRPC.TL_theme r3 = this.adapter.items.get(r2).chatTheme.getTlTheme(this.themeIndex);
        Theme.ThemeInfo r4 = this.adapter.items.get(r2).chatTheme.getThemeInfo(this.themeIndex);
        if (r3 != null) goto L11;
        if (r4 == null) goto L26;
        if (Theme.getActiveTheme().name.equals(r4.getKey()) == false) goto L26;
        if (this.adapter.items.get(r2).chatTheme.getAccentId(this.themeIndex) != Theme.getActiveTheme().currentAccentId) goto L26;
        this.selectedPosition = r2;
    L26:
        r2 = r2 + 1;
        goto L7
    L11:
        if (Theme.getActiveTheme().name.equals(Theme.getBaseThemeKey(r3.settings.get(this.adapter.items.get(r2).chatTheme.getSettingsIndex(this.themeIndex)))) == false) goto L26;
        if (Theme.getActiveTheme().accentsByThemeId == null) goto L14;
        Theme.ThemeAccent r32 = Theme.getActiveTheme().accentsByThemeId.get(r3.id);
        if (r32 == null) goto L26;
        if (r32.id != Theme.getActiveTheme().currentAccentId) goto L26;
        this.selectedPosition = r2;
        goto L28
    L14:
        this.selectedPosition = r2;
    L28:
        if (this.selectedPosition == (-1)) goto L30;
    L32:
        int r0 = 0;
    L33:
        int r22 = this.adapter.items.size();
        ChatThemeBottomSheet.Adapter r42 = this.adapter;
        if (r0 >= r22) goto L40;
        ChatThemeBottomSheet.ChatThemeItem r23 = r42.items.get(r0);
        if (r0 != this.selectedPosition) goto L38;
        boolean r43 = true;
    L39:
        r23.isSelected = r43;
        r0 = r0 + 1;
        goto L33
    L38:
        r43 = false;
        goto L39
    L40:
        r42.setSelectedItem(this.selectedPosition);
        return;
    L30:
        if (this.currentType == 3) goto L32;
        this.selectedPosition = this.adapter.items.size() - 1;
        goto L32
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r1, int r2) {
        updateLayoutManager();
        super.onMeasure(r1, r2);
    }

    @Override // android.view.View
    public void setBackgroundColor(int r1) {
        super.setBackgroundColor(r1);
        updateColors();
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        int r0 = this.currentType;
        if (r0 == 0) goto L5;
        if (r0 == (-1)) goto L5;
        return;
    L5:
        RLottieDrawable r02 = this.darkThemeDrawable;
        if (r02 != null) goto L8;
    L10:
        TextCell r03 = this.dayNightCell;
        if (r03 == null) goto L15;
        Theme.setSelectorDrawableColor(r03.getBackground(), Theme.getColor(Theme.key_listSelector), true);
        this.dayNightCell.setColors(-1, Theme.key_windowBackgroundWhiteBlueText4);
        return;
    L15:
        return;
    L8:
        if (DialogsActivity.switchingTheme == true) goto L10;
        r02.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4), PorterDuff.Mode.SRC_IN));
        goto L10
    }

    public void updateDayNightMode() {
        int r0 = this.currentType;
        int r1 = 2;
        if (r0 == 0) goto L32;
        if (r0 == (-1)) goto L32;
        if (Theme.getActiveTheme().getKey().equals("Blue") == false) goto L11;
        this.themeIndex = 0;
    L37:
        if (this.adapter.items == null) goto L43;
        int r02 = 0;
    L39:
        int r12 = this.adapter.items.size();
        ChatThemeBottomSheet.Adapter r3 = this.adapter;
        if (r02 >= r12) goto L42;
        r3.items.get(r02).themeIndex = this.themeIndex;
        r02 = r02 + 1;
        goto L39
    L42:
        r3.notifyItemRangeChanged(0, r3.items.size());
    L43:
        updateSelectedPosition();
        return;
    L11:
        if (Theme.getActiveTheme().getKey().equals("Day") == false) goto L14;
        this.themeIndex = 1;
        goto L37
    L14:
        if (Theme.getActiveTheme().getKey().equals("Night") == false) goto L17;
        this.themeIndex = 2;
        goto L37
    L17:
        if (Theme.getActiveTheme().getKey().equals("Dark Blue") == false) goto L20;
        this.themeIndex = 3;
        goto L37
    L20:
        if (Theme.isCurrentThemeDay() == false) goto L26;
        int r03 = this.themeIndex;
        if (r03 == 2) goto L24;
        if (r03 != 3) goto L26;
    L24:
        this.themeIndex = 0;
    L26:
        if (Theme.isCurrentThemeDay() == true) goto L37;
        int r04 = this.themeIndex;
        if (r04 == 0) goto L30;
        if (r04 != 1) goto L37;
    L30:
        this.themeIndex = 2;
    L32:
        if (Theme.isCurrentThemeDay() == false) goto L35;
        r1 = 0;
    L35:
        this.themeIndex = r1;
        goto L37
    }

    public void updateLayoutManager() {
        Point r0 = AndroidUtilities.displaySize;
        if (r0.y <= r0.x) goto L5;
        boolean r02 = true;
    L6:
        Boolean r1 = this.wasPortrait;
        if (r1 != null) goto L9;
    L11:
        int r12 = this.currentType;
        if (r12 == 0) goto L24;
        if (r12 == (-1)) goto L24;
        if (r02 == false) goto L18;
        int r13 = 3;
    L19:
        LinearLayoutManager r3 = this.layoutManager;
        if ((r3 instanceof GridLayoutManager) == false) goto L22;
        ((GridLayoutManager) r3).setSpanCount(r13);
    L26:
        this.wasPortrait = Boolean.valueOf(r02);
        return;
    L22:
        this.recyclerView.setHasFixedSize(false);
        GridLayoutManager r2 = new GridLayoutManager(getContext(), r13);
        r2.setSpanSizeLookup(new AnonymousClass3(this));
        RecyclerListView r14 = this.recyclerView;
        this.layoutManager = r2;
        r14.setLayoutManager(r2);
        goto L26
    L18:
        r13 = 9;
    L24:
        if (this.layoutManager != null) goto L26;
        RecyclerListView r15 = this.recyclerView;
        LinearLayoutManager r32 = new LinearLayoutManager(getContext(), 0, false);
        this.layoutManager = r32;
        r15.setLayoutManager(r32);
        goto L26
    L9:
        if (r1.booleanValue() != r02) goto L11;
        return;
    L5:
        r02 = false;
        goto L6
    }
}
