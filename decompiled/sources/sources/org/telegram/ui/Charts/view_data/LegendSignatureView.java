package org.telegram.ui.Charts.view_data;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.mediarouter.media.PlatformMediaRouter1RouteProvider;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.material.timepicker.TimeModel;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChannelMonetizationLayout;
import org.telegram.ui.Charts.data.ChartData;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Stars.StarsIntroActivity;

/* loaded from: classes6.dex */
public class LegendSignatureView extends FrameLayout {
    Drawable backgroundDrawable;
    public boolean canGoZoom;
    public ImageView chevron;
    LinearLayout content;
    SimpleDateFormat format;
    SimpleDateFormat format2;
    SimpleDateFormat format3;
    SimpleDateFormat format4;
    private DecimalFormat formatterTON;
    Holder[] holders;
    SimpleDateFormat hourFormat;
    TextView hourTime;
    public boolean isTopHourChart;
    private RadialProgressView progressView;
    private Theme.ResourcesProvider resourcesProvider;
    Drawable shadowDrawable;
    public boolean showPercentage;
    Runnable showProgressRunnable;
    TextView time;
    public boolean useHour;
    public boolean useWeek;
    public boolean zoomEnabled;

    /* renamed from: org.telegram.ui.Charts.view_data.LegendSignatureView$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ LegendSignatureView this$0;

        public AnonymousClass1(LegendSignatureView r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.chevron.animate().setDuration(120).alpha(0.0f);
            LegendSignatureView.m7376$$Nest$fgetprogressView(this.this$0).animate().setListener(null).start();
            if (LegendSignatureView.m7376$$Nest$fgetprogressView(this.this$0).getVisibility() == 0) goto L5;
            LegendSignatureView.m7376$$Nest$fgetprogressView(this.this$0).setVisibility(0);
            LegendSignatureView.m7376$$Nest$fgetprogressView(this.this$0).setAlpha(0.0f);
        L5:
            LegendSignatureView.m7376$$Nest$fgetprogressView(this.this$0).animate().setDuration(120).alpha(1.0f).start();
        }
    }

    /* renamed from: org.telegram.ui.Charts.view_data.LegendSignatureView$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ LegendSignatureView this$0;

        public AnonymousClass2(LegendSignatureView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            LegendSignatureView.m7376$$Nest$fgetprogressView(this.this$0).setVisibility(8);
        }
    }

    public class Holder {
        TextView percentage;
        final LinearLayout root;
        final TextView signature;
        final /* synthetic */ LegendSignatureView this$0;
        final AnimatedEmojiSpan.TextViewEmojis value;

        public Holder(LegendSignatureView r12) {
            this.this$0 = r12;
            LinearLayout r0 = new LinearLayout(r12.getContext());
            this.root = r0;
            r0.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(2.0f));
            if (r12.showPercentage == false) goto L5;
            TextView r1 = new TextView(r12.getContext());
            this.percentage = r1;
            r0.addView(r1);
            ViewGroup.LayoutParams r13 = this.percentage.getLayoutParams();
            r13.width = AndroidUtilities.dp(36.0f);
            this.percentage.setVisibility(8);
            this.percentage.setTypeface(AndroidUtilities.bold());
            this.percentage.setTextSize(1, 13.0f);
        L5:
            TextView r14 = new TextView(r12.getContext());
            this.signature = r14;
            r0.addView(r14, LayoutHelper.createLinear(-2, -2, 0.0f, 0.0f, 20.0f, 0.0f));
            AnimatedEmojiSpan.TextViewEmojis r4 = new AnimatedEmojiSpan.TextViewEmojis(r12.getContext());
            this.value = r4;
            r0.addView(r4, LayoutHelper.createLinear(-1, -2));
            r14.setGravity(PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES);
            r4.setGravity(8388613);
            r4.setTypeface(AndroidUtilities.bold());
            r4.setTextSize(1, 13.0f);
            r14.setTextSize(1, 13.0f);
        }
    }

    /* renamed from: -$$Nest$fgetprogressView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RadialProgressView m7376$$Nest$fgetprogressView(LegendSignatureView r0) {
        return r0.progressView;
    }

    public LegendSignatureView(Context r2) {
        this(r2, null);
    }

    public LegendSignatureView(Context r8, Theme.ResourcesProvider r9) {
        super(r8);
        this.format = new SimpleDateFormat("E, ");
        this.format2 = new SimpleDateFormat("MMM dd");
        this.format3 = new SimpleDateFormat("d MMM yyyy");
        this.format4 = new SimpleDateFormat("d MMM");
        this.hourFormat = new SimpleDateFormat(" HH:mm");
        this.canGoZoom = true;
        this.showProgressRunnable = new AnonymousClass1(this);
        this.resourcesProvider = r9;
        setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        LinearLayout r92 = new LinearLayout(getContext());
        this.content = r92;
        r92.setOrientation(1);
        TextView r93 = new TextView(r8);
        this.time = r93;
        r93.setTextSize(1, 14.0f);
        this.time.setTypeface(AndroidUtilities.bold());
        TextView r94 = new TextView(r8);
        this.hourTime = r94;
        r94.setTextSize(1, 14.0f);
        this.hourTime.setTypeface(AndroidUtilities.bold());
        ImageView r95 = new ImageView(r8);
        this.chevron = r95;
        r95.setImageResource(R.drawable.ic_chevron_right_black_18dp);
        RadialProgressView r96 = new RadialProgressView(r8);
        this.progressView = r96;
        r96.setSize(AndroidUtilities.dp(12.0f));
        this.progressView.setStrokeWidth(AndroidUtilities.dp(0.5f));
        this.progressView.setVisibility(8);
        addView(this.content, LayoutHelper.createFrame(-2, -2.0f, 0, 0.0f, 22.0f, 0.0f, 0.0f));
        addView(this.time, LayoutHelper.createFrame(-2, -2.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 4.0f, 0.0f, 4.0f, 0.0f));
        addView(this.hourTime, LayoutHelper.createFrame(-2, -2.0f, 8388613, 4.0f, 0.0f, 4.0f, 0.0f));
        addView(this.chevron, LayoutHelper.createFrame(18, 18.0f, 8388661, 0.0f, 2.0f, 0.0f, 0.0f));
        addView(this.progressView, LayoutHelper.createFrame(18, 18.0f, 8388661, 0.0f, 2.0f, 0.0f, 0.0f));
        recolor();
    }

    private String capitalize(String r2) {
        if (r2.length() > 0) goto L5;
        return r2;
    L5:
        return Character.toUpperCase(r2.charAt(0)) + r2.substring(1);
    }

    private String formatData(Date r3) {
        if (this.useHour == false) goto L7;
        return capitalize(this.format2.format(r3));
    L7:
        return capitalize(this.format.format(r3)) + capitalize(this.format2.format(r3));
    }

    public CharSequence formatWholeNumber(long r6, int r8, int r9, TextView r10, float r11) {
        int r2 = 0;
        int r3 = 2;
        if (r8 != 1) goto L16;
        if (r9 != 0) goto L15;
        if (this.formatterTON != null) goto L8;
        DecimalFormatSymbols r82 = new DecimalFormatSymbols(Locale.US);
        r82.setDecimalSeparator('.');
        DecimalFormat r112 = new DecimalFormat("#.##", r82);
        this.formatterTON = r112;
        r112.setMinimumFractionDigits(2);
        this.formatterTON.setMaximumFractionDigits(6);
        this.formatterTON.setGroupingUsed(false);
    L8:
        DecimalFormat r83 = this.formatterTON;
        if (r6 > 1000000000) goto L12;
        r3 = 6;
    L12:
        r83.setMaximumFractionDigits(r3);
        return ChannelMonetizationLayout.replaceTON("TON " + this.formatterTON.format(r6 / 1.0E9d), r10.getPaint(), 0.82f, false);
    L15:
        return "≈" + BillingController.getInstance().formatCurrency((long) (r6 / r11), "USD");
    L16:
        if (r8 != 2) goto L22;
        if (r9 != 0) goto L21;
        return StarsIntroActivity.replaceStarsWithPlain("XTR " + LocaleController.formatNumber(r6, ' '), 0.7f);
    L21:
        return "≈" + BillingController.getInstance().formatCurrency((long) (r6 / r11), "USD");
    L22:
        float r5 = r6;
        if (r6 >= NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS) goto L27;
        return String.format(TimeModel.NUMBER_FORMAT, new Object[]{Long.valueOf(r6)});
    L27:
        if (r5 < 1000.0f) goto L32;
        if (r2 >= (AndroidUtilities.numbersSignatureArray.length - 1)) goto L32;
        r5 = r5 / 1000.0f;
        r2 = r2 + 1;
    L32:
        return String.format("%.2f", new Object[]{Float.valueOf(r5)}) + AndroidUtilities.numbersSignatureArray[r2];
    }

    public void recolor() {
        TextView r0 = this.time;
        int r1 = Theme.key_dialogTextBlack;
        r0.setTextColor(Theme.getColor(r1, this.resourcesProvider));
        this.hourTime.setTextColor(Theme.getColor(r1, this.resourcesProvider));
        ImageView r02 = this.chevron;
        int r12 = Theme.key_statisticChartChevronColor;
        r02.setColorFilter(Theme.getColor(r12, this.resourcesProvider));
        this.progressView.setProgressColor(Theme.getColor(r12, this.resourcesProvider));
        this.shadowDrawable = getContext().getResources().getDrawable(R.drawable.stats_tooltip).mutate();
        this.backgroundDrawable = Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(4.0f), Theme.getColor(Theme.key_dialogBackground, this.resourcesProvider), Theme.getColor(Theme.key_listSelector, this.resourcesProvider), -16777216);
        CombinedDrawable r03 = new CombinedDrawable(this.shadowDrawable, this.backgroundDrawable, AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f));
        r03.setFullsize(true);
        setBackground(r03);
    }

    public void setData(int r19, long r20, ArrayList<LineViewData> r22, boolean r23, int r24, float r25) {
        int r8 = this.holders.length;
        boolean r10 = true;
        int r11 = 0;
        if (r23 == false) goto L6;
        TransitionSet r4 = new TransitionSet();
        r4.addTransition(new Fade(2).setDuration(150)).addTransition(new ChangeBounds().setDuration(150)).addTransition(new Fade(1).setDuration(150));
        r4.setOrdering(0);
        TransitionManager.beginDelayedTransition(this, r4);
    L6:
        if (this.isTopHourChart == false) goto L8;
        this.time.setText(String.format(Locale.ENGLISH, "%02d:00", new Object[]{Long.valueOf(r20)}));
    L15:
        int r1 = 0;
        long r14 = 0;
    L17:
        if (r1 >= r22.size()) goto L22;
        if (r22.get(r1).enabled == false) goto L21;
        r14 = r14 + r22.get(r1).line.y[r19];
    L21:
        r1 = r1 + 1;
        goto L17
    L22:
        int r12 = 0;
    L24:
        if (r12 >= r8) goto L73;
        Holder r42 = this.holders[r12];
        int r5 = r12 % 2;
        if (r24 == 1) goto L30;
        if (r24 == 2) goto L30;
        int r6 = r12;
    L31:
        LineViewData r62 = r22.get(r6);
        if (r62.enabled == true) goto L35;
        r42.root.setVisibility(8);
        int r122 = r12;
    L71:
        r12 = r122 + 1;
        r11 = 0;
        goto L24
    L35:
        if (r42.root.getMeasuredHeight() != 0) goto L37;
        r42.root.requestLayout();
    L37:
        r42.root.setVisibility(r11);
        AnimatedEmojiSpan.TextViewEmojis r52 = r42.value;
        r122 = r12;
        r52.setText(formatWholeNumber(r62.line.y[r19], r24, r5, r52, r25));
        if (r24 != 1) goto L44;
        TextView r13 = r42.signature;
        if (r5 != 0) goto L42;
        int r2 = R.string.ChartInTON;
    L43:
        r13.setText(LocaleController.formatString(r2, new Object[]{r62.line.name}));
    L51:
        int r15 = r62.line.colorKey;
        if (r15 >= 0) goto L54;
    L56:
        AnimatedEmojiSpan.TextViewEmojis r16 = r42.value;
        boolean r26 = Theme.getCurrentTheme().isDark();
        ChartData.Line r43 = r62.line;
        if (r26 == false) goto L59;
        int r27 = r43.colorDark;
    L60:
        r16.setTextColor(r27);
    L61:
        TextView r17 = r42.signature;
        int r28 = Theme.key_dialogTextBlack;
        r17.setTextColor(Theme.getColor(r28, this.resourcesProvider));
        if (this.showPercentage == false) goto L71;
        TextView r18 = r42.percentage;
        if (r18 == null) goto L71;
        r18.setVisibility(0);
        r42.percentage.setTextColor(Theme.getColor(r28, this.resourcesProvider));
        float r110 = r22.get(r122).line.y[r19] / r14;
        if (r110 < 0.1f) goto L68;
    L70:
        r42.percentage.setText(String.format(Locale.ENGLISH, "%d%s", new Object[]{Integer.valueOf(Math.round(r110 * 100.0f)), "%"}));
        goto L71
    L68:
        if (r110 == 0.0f) goto L70;
        r42.percentage.setText(String.format(Locale.ENGLISH, "%.1f%s", new Object[]{Float.valueOf(r110 * 100.0f), "%"}));
        goto L71
    L59:
        r27 = r43.color;
        goto L60
    L54:
        if (Theme.hasThemeKey(r15) == false) goto L56;
        r42.value.setTextColor(Theme.getColor(r62.line.colorKey, this.resourcesProvider));
        goto L61
    L42:
        r2 = R.string.ChartInUSD;
        goto L43
    L44:
        TextView r111 = r42.signature;
        if (r24 != 2) goto L50;
        if (r5 != 0) goto L48;
        int r29 = R.string.ChartInXTR;
    L49:
        r111.setText(StarsIntroActivity.replaceStarsWithPlain(LocaleController.formatString(r29, new Object[]{r62.line.name}), 0.7f));
        goto L51
    L48:
        r29 = R.string.ChartInUSD;
        goto L49
    L50:
        r111.setText(r62.line.name);
    L30:
        r6 = r12 / 2;
        goto L31
    L73:
        if (this.zoomEnabled == true) goto L75;
        this.canGoZoom = false;
        this.chevron.setVisibility(8);
        return;
    L75:
        if (r14 > 0) goto L78;
        r10 = false;
    L78:
        this.canGoZoom = r10;
        ImageView r0 = this.chevron;
        if (r14 <= 0) goto L81;
        int r112 = 0;
    L82:
        r0.setVisibility(r112);
        return;
    L81:
        r112 = 8;
        goto L82
    L8:
        boolean r44 = this.useWeek;
        TextView r53 = this.time;
        if (r44 == false) goto L11;
        r53.setText(String.format("%s — %s", new Object[]{this.format4.format(new Date(r20)), this.format3.format(new Date(604800000 + r20))}));
    L13:
        if (this.useHour == false) goto L15;
        this.hourTime.setText(this.hourFormat.format(Long.valueOf(r20)));
        goto L15
    L11:
        r53.setText(formatData(new Date(r20)));
        goto L13
    }

    public void setSize(int r4) {
        this.content.removeAllViews();
        this.holders = new Holder[r4];
        int r0 = 0;
    L3:
        if (r0 >= r4) goto L5;
        this.holders[r0] = new Holder(this);
        this.content.addView(this.holders[r0].root);
        r0 = r0 + 1;
        goto L3
    }

    public void setUseWeek(boolean r1) {
        this.useWeek = r1;
    }

    public void showProgress(boolean r3, boolean r4) {
        Runnable r0 = this.showProgressRunnable;
        if (r3 == false) goto L6;
        AndroidUtilities.runOnUIThread(r0, 300);
        return;
    L6:
        AndroidUtilities.cancelRunOnUIThread(r0);
        if (r4 == false) goto L10;
        this.progressView.setVisibility(8);
        return;
    L10:
        this.chevron.animate().setDuration(80).alpha(1.0f).start();
        if (this.progressView.getVisibility() != 0) goto L14;
        this.progressView.animate().setDuration(80).alpha(0.0f).setListener(new AnonymousClass2(this)).start();
        return;
    }
}
