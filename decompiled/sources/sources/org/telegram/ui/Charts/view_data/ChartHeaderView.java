package org.telegram.ui.Charts.view_data;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.google.android.material.navigation.NavigationBarView;
import java.text.SimpleDateFormat;
import java.util.Date;
import kotlin.time.DurationKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Charts.BaseChartView;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class ChartHeaderView extends FrameLayout {
    public TextView back;
    private TextView dates;
    private TextView datesTmp;
    SimpleDateFormat formatter;
    private Theme.ResourcesProvider resourcesProvider;
    private boolean showDate;
    int textMargin;
    private TextView title;
    private boolean useWeekInterval;
    private Drawable zoomIcon;

    /* renamed from: $r8$lambda$Q2kHC4t65Y-VSqxnEtryDo2br5Q, reason: not valid java name */
    public static /* synthetic */ void m7375$r8$lambda$Q2kHC4t65YVSqxnEtryDo2br5Q(ChartHeaderView r0, View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
        r0.lambda$new$0(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public ChartHeaderView(Context r2) {
        this(r2, null);
    }

    public ChartHeaderView(Context r12, Theme.ResourcesProvider r13) {
        super(r12);
        this.showDate = true;
        this.formatter = new SimpleDateFormat("d MMM yyyy");
        this.resourcesProvider = r13;
        TextPaint r1 = new TextPaint();
        r1.setTextSize(14.0f);
        r1.setTypeface(AndroidUtilities.bold());
        this.textMargin = (int) r1.measureText("00 MMM 0000 - 00 MMM 000");
        TextView r14 = new TextView(r12);
        this.title = r14;
        r14.setTextSize(1, 15.0f);
        this.title.setTypeface(AndroidUtilities.bold());
        addView(this.title, LayoutHelper.createFrame(-2, -2.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 16.0f, 0.0f, this.textMargin, 0.0f));
        TextView r15 = new TextView(r12);
        this.back = r15;
        r15.setTextSize(1, 15.0f);
        this.back.setTypeface(Typeface.DEFAULT_BOLD);
        this.back.setGravity(NavigationBarView.ITEM_GRAVITY_START_CENTER);
        addView(this.back, LayoutHelper.createFrame(-2, -2.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 8.0f, 0.0f, 8.0f, 0.0f));
        TextView r16 = new TextView(r12);
        this.dates = r16;
        r16.setTextSize(1, 13.0f);
        this.dates.setTypeface(AndroidUtilities.bold());
        this.dates.setGravity(8388629);
        addView(this.dates, LayoutHelper.createFrame(-2, -2.0f, 8388629, 16.0f, 0.0f, 16.0f, 0.0f));
        TextView r17 = new TextView(r12);
        this.datesTmp = r17;
        r17.setTextSize(1, 13.0f);
        this.datesTmp.setTypeface(AndroidUtilities.bold());
        this.datesTmp.setGravity(8388629);
        addView(this.datesTmp, LayoutHelper.createFrame(-2, -2.0f, 8388629, 16.0f, 0.0f, 16.0f, 0.0f));
        this.datesTmp.setVisibility(8);
        this.back.setVisibility(8);
        this.back.setText(LocaleController.getString(R.string.ZoomOut));
        Drawable r122 = ContextCompat.getDrawable(getContext(), R.drawable.msg_zoomout_stats);
        this.zoomIcon = r122;
        this.back.setCompoundDrawablesWithIntrinsicBounds(r122, null, null, null);
        this.back.setCompoundDrawablePadding(AndroidUtilities.dp(4.0f));
        this.back.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(4.0f));
        this.back.setBackground(Theme.getRoundRectSelectorDrawable(Theme.getColor(Theme.key_featuredStickers_removeButtonText, r13)));
        this.datesTmp.addOnLayoutChangeListener(new ChartHeaderView$$ExternalSyntheticLambda0(this));
        recolor();
    }

    private /* synthetic */ void lambda$new$0(View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
        this.datesTmp.setPivotX(r1.getMeasuredWidth() * 0.7f);
        this.dates.setPivotX(r0.getMeasuredWidth() * 0.7f);
    }

    public void recolor() {
        TextView r0 = this.title;
        int r1 = Theme.key_dialogTextBlack;
        r0.setTextColor(Theme.getColor(r1, this.resourcesProvider));
        this.dates.setTextColor(Theme.getColor(r1, this.resourcesProvider));
        this.datesTmp.setTextColor(Theme.getColor(r1, this.resourcesProvider));
        TextView r02 = this.back;
        int r12 = Theme.key_statisticChartBackZoomColor;
        r02.setTextColor(Theme.getColor(r12, this.resourcesProvider));
        this.zoomIcon.setColorFilter(Theme.getColor(r12, this.resourcesProvider), PorterDuff.Mode.SRC_IN);
    }

    public void setDates(long r5, long r7) {
        if (this.showDate == true) goto L7;
        this.dates.setVisibility(8);
        this.datesTmp.setVisibility(8);
        return;
    L7:
        if (this.useWeekInterval == false) goto L10;
        r7 = r7 + 604800000;
    L10:
        if ((r7 - r5) < DurationKt.MILLIS_IN_DAY) goto L12;
        String r52 = LocaleController.getInstance().getFormatterYear().format(new Date(r5)) + " — " + LocaleController.getInstance().getFormatterYear().format(new Date(r7));
    L13:
        this.dates.setText(r52);
        this.dates.setVisibility(0);
        return;
    L12:
        r52 = LocaleController.getInstance().getFormatterYear().format(new Date(r5));
        goto L13
    }

    public void setTitle(String r1) {
        this.title.setText(r1);
    }

    public void setUseWeekInterval(boolean r1) {
        this.useWeekInterval = r1;
    }

    public void showDate(boolean r8) {
        this.showDate = r8;
        if (r8 == true) goto L6;
        this.datesTmp.setVisibility(8);
        this.dates.setVisibility(8);
        this.title.setLayoutParams(LayoutHelper.createFrame(-2, -2.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 16.0f, 0.0f, 16.0f, 0.0f));
        this.title.requestLayout();
        return;
    L6:
        this.title.setLayoutParams(LayoutHelper.createFrame(-2, -2.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 16.0f, 0.0f, this.textMargin, 0.0f));
    }

    public void zoomOut(BaseChartView r5, boolean r6) {
        setDates(r5.getStartDate(), r5.getEndDate());
        TextView r52 = this.title;
        if (r6 == false) goto L6;
        r52.setAlpha(0.0f);
        this.title.setScaleX(0.3f);
        this.title.setScaleY(0.3f);
        this.title.setPivotX(0.0f);
        this.title.setPivotY(0.0f);
        this.title.animate().alpha(1.0f).scaleY(1.0f).scaleX(1.0f).setDuration(200).start();
        this.back.setAlpha(1.0f);
        this.back.setTranslationX(0.0f);
        this.back.setTranslationY(0.0f);
        this.back.setScaleX(1.0f);
        this.back.setScaleY(1.0f);
        this.back.setPivotY(AndroidUtilities.dp(40.0f));
        this.back.animate().alpha(0.0f).scaleY(0.3f).scaleX(0.3f).setDuration(200).start();
        return;
    L6:
        r52.setAlpha(1.0f);
        this.title.setScaleX(1.0f);
        this.title.setScaleY(1.0f);
        this.back.setAlpha(0.0f);
    }

    public void zoomTo(BaseChartView r3, long r4, boolean r6) {
        setDates(r4, r4);
        this.back.setVisibility(0);
        TextView r32 = this.back;
        if (r6 == false) goto L6;
        r32.setAlpha(0.0f);
        this.back.setScaleX(0.3f);
        this.back.setScaleY(0.3f);
        this.back.setPivotX(0.0f);
        this.back.setPivotY(AndroidUtilities.dp(40.0f));
        this.back.animate().alpha(1.0f).scaleY(1.0f).scaleX(1.0f).setDuration(200).start();
        this.title.setAlpha(1.0f);
        this.title.setTranslationX(0.0f);
        this.title.setTranslationY(0.0f);
        this.title.setScaleX(1.0f);
        this.title.setScaleY(1.0f);
        this.title.setPivotX(0.0f);
        this.title.setPivotY(0.0f);
        this.title.animate().alpha(0.0f).scaleY(0.3f).scaleX(0.3f).setDuration(200).start();
        return;
    L6:
        r32.setAlpha(1.0f);
        this.back.setTranslationX(0.0f);
        this.back.setTranslationY(0.0f);
        this.back.setScaleX(1.0f);
        this.back.setScaleY(1.0f);
        this.title.setAlpha(0.0f);
    }
}
