package com.exteragram.messenger.plugins.ui.components;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0002¨\u0006\u0012"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/UnknownSourcesBottomSheet;", "Lorg/telegram/ui/ActionBar/BottomSheet;", "fragment", "Lorg/telegram/ui/ActionBar/BaseFragment;", "onConfirm", "Lkotlin/Function0;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lorg/telegram/ui/ActionBar/BaseFragment;Lkotlin/jvm/functions/Function0;)V", "makeHint", "Landroid/view/View;", "context", "Landroid/content/Context;", "iconResId", _UrlKt.FRAGMENT_ENCODE_SET, "title", "info", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class UnknownSourcesBottomSheet extends BottomSheet {
    public static final Companion Companion = null;
    private static final float ICON_SIZE = 24.0f;
    private static final float ITEM_HORIZONTAL_PADDING = 27.0f;
    private static final float ITEM_TEXT_PADDING = 68.0f;
    private static final int READ_TIME = 30;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/UnknownSourcesBottomSheet$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "ITEM_HORIZONTAL_PADDING", _UrlKt.FRAGMENT_ENCODE_SET, "ITEM_TEXT_PADDING", "ICON_SIZE", "READ_TIME", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    public static void $r8$lambda$Qx_wXefC8pnxHnFFUAF9QhBqceI(ButtonWithCounterView r0, UnknownSourcesBottomSheet r1, Function0 r2, View r3) {
        if (r0.isTimerActive() == false) goto L6;
        AndroidUtilities.shakeViewSpring(r0, 3.0f);
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        return;
    L6:
        r1.dismiss();
        r2.invoke();
    }

    /* renamed from: $r8$lambda$kYyu-Rs3T12JOI5iHzuX2m85n6A, reason: not valid java name */
    public static void m1614$r8$lambda$kYyuRs3T12JOI5iHzuX2m85n6A(UnknownSourcesBottomSheet r0, View r1) {
        r0.dismiss();
    }

    static {
        Companion = new Companion(null);
    }

    public UnknownSourcesBottomSheet(BaseFragment r17, Function0<Unit> r18) {
        super(r17.getParentActivity(), false, r17.getResourceProvider());
        Context r1 = r17.getParentActivity();
        fixNavigationBar();
        FrameLayout r2 = new FrameLayout(r1);
        LinearLayout r4 = new LinearLayout(r1);
        r4.setOrientation(1);
        r2.addView(r4);
        RLottieImageView r6 = new RLottieImageView(r1);
        r6.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_chats_actionIcon), PorterDuff.Mode.SRC_IN));
        r6.setAnimation(R.raw.ic_ban, 50, 50);
        r6.playAnimation();
        r6.setScaleType(ImageView.ScaleType.CENTER);
        r6.setPadding(AndroidUtilities.dp(3.0f), 0, 0, AndroidUtilities.dp(3.0f));
        r6.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(80.0f), getThemedColor(Theme.key_windowBackgroundWhiteValueText)));
        r4.addView(r6, LayoutHelper.createLinear(80, 80, 17, 0.0f, 14.0f, 0.0f, 0.0f));
        TextView r62 = new TextView(r1);
        r62.setGravity(1);
        r62.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
        r62.setTextSize(1, 20.0f);
        r62.setTypeface(AndroidUtilities.bold());
        r62.setText(LocaleController.getString(R.string.PluginsUnknownSources));
        r4.addView(r62, LayoutHelper.createFrame(-1, -2.0f, 0, 40.0f, 20.0f, 40.0f, 0.0f));
        TextView r63 = new TextView(r1);
        r63.setGravity(1);
        r63.setTypeface(AndroidUtilities.regular());
        r63.setTextSize(1, 14.0f);
        r63.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText));
        r63.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PluginsUnknownSourcesInfo)));
        r4.addView(r63, LayoutHelper.createFrame(-1, -2.0f, 0, 21.0f, 8.0f, 21.0f, 0.0f));
        r4.addView(makeHint(r1, R.drawable.msg_permissions, R.string.PluginsUnknownSourcesAccount, R.string.PluginsUnknownSourcesAccountInfo), LayoutHelper.createFrame(-1, -2.0f, 0, 0.0f, 20.0f, 0.0f, 0.0f));
        r4.addView(makeHint(r1, R.drawable.msg_folders, R.string.PluginsUnknownSourcesFiles, R.string.PluginsUnknownSourcesFilesInfo), LayoutHelper.createFrame(-1, -2.0f, 0, 0.0f, 16.0f, 0.0f, 0.0f));
        r4.addView(makeHint(r1, R.drawable.msg_language, R.string.PluginsUnknownSourcesCode, R.string.PluginsUnknownSourcesCodeInfo), LayoutHelper.createFrame(-1, -2.0f, 0, 0.0f, 16.0f, 0.0f, 0.0f));
        int r64 = getThemedColor(Theme.key_text_RedBold);
        TextView r8 = new TextView(r1);
        r8.setGravity(1);
        r8.setTypeface(AndroidUtilities.bold());
        r8.setTextSize(1, 14.0f);
        r8.setTextColor(r64);
        r8.setPadding(AndroidUtilities.dp(21.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(21.0f), AndroidUtilities.dp(10.0f));
        int r7 = AndroidUtilities.dp(12.0f);
        if (Theme.isCurrentThemeDark() == false) goto L5;
        float r9 = 0.2f;
    L6:
        r8.setBackground(Theme.createRoundRectDrawable(r7, Theme.multAlpha(r64, r9)));
        r8.setText(LocaleController.getString(R.string.PluginsUnknownSourcesWarning));
        r4.addView(r8, LayoutHelper.createFrame(-1, -2.0f, 0, 16.0f, 20.0f, 16.0f, 0.0f));
        ButtonWithCounterView r65 = new ButtonWithCounterView(r1, true, this.resourcesProvider);
        r65.setRound();
        r65.setText(LocaleController.getString(R.string.Cancel), false);
        r65.setOnClickListener(new UnknownSourcesBottomSheet$$ExternalSyntheticLambda0(this));
        r4.addView(r65, LayoutHelper.createFrame(-1, 48.0f, 0, 16.0f, 15.0f, 16.0f, 8.0f));
        ButtonWithCounterView r5 = new ButtonWithCounterView(r1, false, this.resourcesProvider);
        r5.setRound();
        r5.setNeutral();
        r5.setText(LocaleController.getString(R.string.Allow), false);
        r4.addView(r5, LayoutHelper.createFrame(-1, 48.0f, 0, 16.0f, 0.0f, 16.0f, 0.0f));
        r5.setTimer(30, null);
        r5.setOnClickListener(new UnknownSourcesBottomSheet$$ExternalSyntheticLambda1(r5, this, r18));
        ScrollView r3 = new ScrollView(r1);
        r3.addView(r2);
        setCustomView(r3);
        return;
    L5:
        r9 = 0.15f;
        goto L6
    }

    private final View makeHint(Context r11, int r12, int r13, int r14) {
        FrameLayout r0 = new FrameLayout(r11);
        ImageView r1 = new ImageView(r11);
        r1.setImageResource(r12);
        int r122 = Theme.key_windowBackgroundWhiteBlackText;
        r1.setColorFilter(getThemedColor(r122));
        r0.addView(r1, LayoutHelper.createFrameRelatively(ICON_SIZE, ICON_SIZE, 8388659, ITEM_HORIZONTAL_PADDING, 6.0f, 0.0f, 0.0f));
        LinearLayout r15 = new LinearLayout(r11);
        r15.setOrientation(1);
        TextView r3 = new TextView(r11);
        r3.setGravity(LayoutHelper.getAbsoluteGravityStart());
        r3.setTypeface(AndroidUtilities.bold());
        r3.setTextSize(1, 14.0f);
        r3.setTextColor(getThemedColor(r122));
        r3.setText(LocaleController.getString(r13));
        r15.addView(r3, LayoutHelper.createLinear(-1, -2));
        TextView r123 = new TextView(r11);
        r123.setGravity(LayoutHelper.getAbsoluteGravityStart());
        r123.setTypeface(AndroidUtilities.regular());
        r123.setTextSize(1, 14.0f);
        r123.setTextColor(getThemedColor(Theme.key_dialogTextGray3));
        r123.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        r123.setText(LocaleController.getString(r14));
        r15.addView(r123, LayoutHelper.createLinear(-1, -2, 0.0f, 2.0f, 0.0f, 0.0f));
        r0.addView(r15, LayoutHelper.createFrameRelatively(-1.0f, -2.0f, 8388659, ITEM_TEXT_PADDING, 0.0f, ITEM_HORIZONTAL_PADDING, 0.0f));
        return r0;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public /* bridge */ /* synthetic */ void setLastVisible(boolean r1) {
        super.setLastVisible(r1);
    }
}
