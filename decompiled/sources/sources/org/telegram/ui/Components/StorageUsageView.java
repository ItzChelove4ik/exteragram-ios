package org.telegram.ui.Components;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import com.android.dx.io.Opcodes;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.voip.CellFlickerDrawable;

/* loaded from: classes7.dex */
public class StorageUsageView extends FrameLayout {
    private Paint bgPaint;
    private boolean calculating;
    float calculatingProgress;
    boolean calculatingProgressIncrement;
    TextView calculatingTextView;
    CellFlickerDrawable cellFlickerDrawable;
    View divider;
    EllipsizeSpanAnimator ellipsizeSpanAnimator;
    TextView freeSizeTextView;
    int lastProgressColor;
    public ViewGroup legendLayout;
    private Paint paintCalculcating;
    private Paint paintFill;
    private Paint paintProgress;
    private Paint paintProgress2;
    float progress;
    float progress2;
    ProgressView progressView;
    TextView telegramCacheTextView;
    TextView telegramDatabaseTextView;
    TextSettingsCell textSettingsCell;
    private long totalDeviceFreeSize;
    private long totalDeviceSize;
    private long totalSize;
    TextView totlaSizeTextView;
    ValueAnimator valueAnimator;
    ValueAnimator valueAnimator2;

    /* renamed from: org.telegram.ui.Components.StorageUsageView$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ StorageUsageView this$0;

        public AnonymousClass1(StorageUsageView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r4, int r5, int r6, int r7, int r8) {
            int r42 = getChildCount();
            int r62 = 0;
            int r72 = 0;
            int r82 = 0;
        L3:
            if (r62 >= r42) goto L12;
            if (getChildAt(r62).getVisibility() == 8) goto L11;
            if ((getChildAt(r62).getMeasuredWidth() + r72) <= getMeasuredWidth()) goto L10;
            r82 = r82 + (getChildAt(r62).getMeasuredHeight() + AndroidUtilities.dp(8.0f));
            r72 = 0;
        L10:
            getChildAt(r62).layout(r72, r82, getChildAt(r62).getMeasuredWidth() + r72, getChildAt(r62).getMeasuredHeight() + r82);
            r72 = r72 + (getChildAt(r62).getMeasuredWidth() + AndroidUtilities.dp(16.0f));
        L11:
            r62 = r62 + 1;
            goto L3
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r8, int r9) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r8), TLObject.FLAG_30), r9);
            int r92 = getChildCount();
            int r1 = 0;
            int r2 = 0;
            int r3 = 0;
            int r4 = 0;
        L3:
            if (r1 >= r92) goto L12;
            if (getChildAt(r1).getVisibility() == 8) goto L11;
            if ((getChildAt(r1).getMeasuredWidth() + r3) <= View.MeasureSpec.getSize(r8)) goto L10;
            r4 = r4 + (getChildAt(r1).getMeasuredHeight() + AndroidUtilities.dp(8.0f));
            r3 = 0;
        L10:
            r3 = r3 + (getChildAt(r1).getMeasuredWidth() + AndroidUtilities.dp(16.0f));
            r2 = getChildAt(r1).getMeasuredHeight() + r4;
        L11:
            r1 = r1 + 1;
            goto L3
        L12:
            setMeasuredDimension(getMeasuredWidth(), r2);
        }
    }

    public class ProgressView extends View {
        final /* synthetic */ StorageUsageView this$0;

        public ProgressView(StorageUsageView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r15) {
            super.onDraw(r15);
            int r0 = Theme.getColor(Theme.key_player_progress);
            StorageUsageView.m13142$$Nest$fgetpaintFill(this.this$0).setColor(r0);
            StorageUsageView.m13143$$Nest$fgetpaintProgress(this.this$0).setColor(r0);
            StorageUsageView.m13144$$Nest$fgetpaintProgress2(this.this$0).setColor(r0);
            StorageUsageView.m13143$$Nest$fgetpaintProgress(this.this$0).setAlpha(255);
            StorageUsageView.m13144$$Nest$fgetpaintProgress2(this.this$0).setAlpha(82);
            StorageUsageView.m13142$$Nest$fgetpaintFill(this.this$0).setAlpha(46);
            StorageUsageView.m13140$$Nest$fgetbgPaint(this.this$0).setColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            r15.drawLine(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(20.0f), getMeasuredWidth() - AndroidUtilities.dp(24.0f), AndroidUtilities.dp(20.0f), StorageUsageView.m13142$$Nest$fgetpaintFill(this.this$0));
            if (StorageUsageView.m13141$$Nest$fgetcalculating(this.this$0) == false) goto L5;
        L6:
            boolean r152 = StorageUsageView.m13141$$Nest$fgetcalculating(this.this$0);
            StorageUsageView r5 = this.this$0;
            if (r152 == true) goto L9;
            float r153 = r5.calculatingProgress - 0.10666667f;
            r5.calculatingProgress = r153;
            if (r153 >= 0.0f) goto L19;
            r5.calculatingProgress = 0.0f;
        L19:
            invalidate();
            RectF r154 = AndroidUtilities.rectTmp;
            r154.set(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(17.0f), getMeasuredWidth() - AndroidUtilities.dp(24.0f), AndroidUtilities.dp(23.0f));
            this.this$0.cellFlickerDrawable.setParentWidth(getMeasuredWidth());
            this.this$0.cellFlickerDrawable.draw(r15, r154, AndroidUtilities.dp(3.0f), null);
        L20:
            int r155 = AndroidUtilities.dp(24.0f);
            if (StorageUsageView.m13141$$Nest$fgetcalculating(this.this$0) == true) goto L24;
            int r52 = AndroidUtilities.dp(24.0f) + ((int) ((getMeasuredWidth() - (AndroidUtilities.dp(24.0f) * 2)) * this.this$0.progress2));
            r15.drawLine(r155, AndroidUtilities.dp(20.0f), AndroidUtilities.dp(24.0f) + r3, AndroidUtilities.dp(20.0f), StorageUsageView.m13144$$Nest$fgetpaintProgress2(this.this$0));
            r15.drawRect(r52, AndroidUtilities.dp(20.0f) - AndroidUtilities.dp(3.0f), r52 + AndroidUtilities.dp(3.0f), AndroidUtilities.dp(20.0f) + AndroidUtilities.dp(3.0f), StorageUsageView.m13140$$Nest$fgetbgPaint(this.this$0));
        L24:
            if (StorageUsageView.m13141$$Nest$fgetcalculating(this.this$0) == true) goto L30;
            int r3 = (int) ((getMeasuredWidth() - (AndroidUtilities.dp(24.0f) * 2)) * this.this$0.progress);
            if (r3 >= AndroidUtilities.dp(1.0f)) goto L28;
            r3 = AndroidUtilities.dp(1.0f);
        L28:
            int r2 = AndroidUtilities.dp(24.0f) + r3;
            r15.drawLine(r155, AndroidUtilities.dp(20.0f), AndroidUtilities.dp(24.0f) + r3, AndroidUtilities.dp(20.0f), StorageUsageView.m13143$$Nest$fgetpaintProgress(this.this$0));
            r15.drawRect(r2, AndroidUtilities.dp(20.0f) - AndroidUtilities.dp(3.0f), r2 + AndroidUtilities.dp(3.0f), AndroidUtilities.dp(20.0f) + AndroidUtilities.dp(3.0f), StorageUsageView.m13140$$Nest$fgetbgPaint(this.this$0));
            return;
        L30:
            return;
        L9:
            if (r5.calculatingProgressIncrement == false) goto L13;
            float r156 = r5.calculatingProgress + 0.024615385f;
            r5.calculatingProgress = r156;
            if (r156 <= 1.0f) goto L19;
            r5.calculatingProgress = 1.0f;
            r5.calculatingProgressIncrement = false;
            goto L19
        L13:
            float r157 = r5.calculatingProgress - 0.024615385f;
            r5.calculatingProgress = r157;
            if (r157 >= 0.0f) goto L19;
            r5.calculatingProgress = 0.0f;
            r5.calculatingProgressIncrement = true;
            goto L19
        L5:
            if (this.this$0.calculatingProgress == 0.0f) goto L20;
            goto L6
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(40.0f), TLObject.FLAG_30));
        }
    }

    /* renamed from: $r8$lambda$NrW0ywTRjFQfFKVFuuT0xn05-0M, reason: not valid java name */
    public static /* synthetic */ void m13138$r8$lambda$NrW0ywTRjFQfFKVFuuT0xn050M(StorageUsageView r0, ValueAnimator r1) {
        r0.lambda$setStorageUsage$1(r1);
    }

    /* renamed from: $r8$lambda$nM8RBafH0-a1kQGe36bZZ_d7EKQ, reason: not valid java name */
    public static /* synthetic */ void m13139$r8$lambda$nM8RBafH0a1kQGe36bZZ_d7EKQ(StorageUsageView r0, ValueAnimator r1) {
        r0.lambda$setStorageUsage$0(r1);
    }

    /* renamed from: -$$Nest$fgetbgPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m13140$$Nest$fgetbgPaint(StorageUsageView r0) {
        return r0.bgPaint;
    }

    /* renamed from: -$$Nest$fgetcalculating, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m13141$$Nest$fgetcalculating(StorageUsageView r0) {
        return r0.calculating;
    }

    /* renamed from: -$$Nest$fgetpaintFill, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m13142$$Nest$fgetpaintFill(StorageUsageView r0) {
        return r0.paintFill;
    }

    /* renamed from: -$$Nest$fgetpaintProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m13143$$Nest$fgetpaintProgress(StorageUsageView r0) {
        return r0.paintProgress;
    }

    /* renamed from: -$$Nest$fgetpaintProgress2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m13144$$Nest$fgetpaintProgress2(StorageUsageView r0) {
        return r0.paintProgress2;
    }

    public StorageUsageView(Context r14) {
        super(r14);
        this.paintFill = new Paint(1);
        this.paintCalculcating = new Paint(1);
        this.paintProgress = new Paint(1);
        this.paintProgress2 = new Paint(1);
        this.bgPaint = new Paint();
        this.cellFlickerDrawable = new CellFlickerDrawable(Opcodes.REM_INT_LIT8, 255);
        setWillNotDraw(false);
        this.cellFlickerDrawable.drawFrame = false;
        this.paintFill.setStrokeWidth(AndroidUtilities.dp(6.0f));
        this.paintCalculcating.setStrokeWidth(AndroidUtilities.dp(6.0f));
        this.paintProgress.setStrokeWidth(AndroidUtilities.dp(6.0f));
        this.paintProgress2.setStrokeWidth(AndroidUtilities.dp(6.0f));
        Paint r0 = this.paintFill;
        Paint.Cap r3 = Paint.Cap.ROUND;
        r0.setStrokeCap(r3);
        this.paintCalculcating.setStrokeCap(r3);
        this.paintProgress.setStrokeCap(r3);
        this.paintProgress2.setStrokeCap(r3);
        ProgressView r02 = new ProgressView(this, r14);
        this.progressView = r02;
        addView(r02, LayoutHelper.createFrame(-1, -2.0f));
        LinearLayout r03 = new LinearLayout(r14);
        r03.setOrientation(1);
        addView(r03, LayoutHelper.createFrame(-1, -2.0f));
        AnonymousClass1 r5 = new AnonymousClass1(this, r14);
        this.legendLayout = r5;
        r03.addView(r5, LayoutHelper.createLinear(-1, -2, 21.0f, 40.0f, 21.0f, 16.0f));
        TextView r52 = new TextView(r14);
        this.calculatingTextView = r52;
        int r6 = Theme.key_windowBackgroundWhiteGrayText;
        r52.setTextColor(Theme.getColor(r6));
        String r53 = LocaleController.getString("CalculatingSize", R.string.CalculatingSize);
        int r7 = r53.indexOf("...");
        if (r7 < 0) goto L5;
        SpannableString r8 = new SpannableString(r53);
        EllipsizeSpanAnimator r54 = new EllipsizeSpanAnimator(this.calculatingTextView);
        this.ellipsizeSpanAnimator = r54;
        r54.wrap(r8, r7);
        this.calculatingTextView.setText(r8);
    L6:
        TextView r55 = new TextView(r14);
        this.telegramCacheTextView = r55;
        r55.setCompoundDrawablePadding(AndroidUtilities.dp(6.0f));
        this.telegramCacheTextView.setTextColor(Theme.getColor(r6));
        TextView r56 = new TextView(r14);
        this.telegramDatabaseTextView = r56;
        r56.setCompoundDrawablePadding(AndroidUtilities.dp(6.0f));
        this.telegramDatabaseTextView.setTextColor(Theme.getColor(r6));
        TextView r57 = new TextView(r14);
        this.freeSizeTextView = r57;
        r57.setCompoundDrawablePadding(AndroidUtilities.dp(6.0f));
        this.freeSizeTextView.setTextColor(Theme.getColor(r6));
        TextView r58 = new TextView(r14);
        this.totlaSizeTextView = r58;
        r58.setCompoundDrawablePadding(AndroidUtilities.dp(6.0f));
        this.totlaSizeTextView.setTextColor(Theme.getColor(r6));
        this.lastProgressColor = Theme.getColor(Theme.key_player_progress);
        this.telegramCacheTextView.setCompoundDrawablesWithIntrinsicBounds(Theme.createCircleDrawable(AndroidUtilities.dp(10.0f), this.lastProgressColor), null, null, null);
        this.telegramCacheTextView.setCompoundDrawablePadding(AndroidUtilities.dp(6.0f));
        this.freeSizeTextView.setCompoundDrawablesWithIntrinsicBounds(Theme.createCircleDrawable(AndroidUtilities.dp(10.0f), ColorUtils.setAlphaComponent(this.lastProgressColor, 64)), null, null, null);
        this.freeSizeTextView.setCompoundDrawablePadding(AndroidUtilities.dp(6.0f));
        this.totlaSizeTextView.setCompoundDrawablesWithIntrinsicBounds(Theme.createCircleDrawable(AndroidUtilities.dp(10.0f), ColorUtils.setAlphaComponent(this.lastProgressColor, 127)), null, null, null);
        this.totlaSizeTextView.setCompoundDrawablePadding(AndroidUtilities.dp(6.0f));
        this.telegramDatabaseTextView.setCompoundDrawablesWithIntrinsicBounds(Theme.createCircleDrawable(AndroidUtilities.dp(10.0f), this.lastProgressColor), null, null, null);
        this.telegramDatabaseTextView.setCompoundDrawablePadding(AndroidUtilities.dp(6.0f));
        this.legendLayout.addView(this.calculatingTextView, LayoutHelper.createFrame(-2, -2.0f));
        this.legendLayout.addView(this.telegramDatabaseTextView, LayoutHelper.createFrame(-2, -2.0f));
        this.legendLayout.addView(this.telegramCacheTextView, LayoutHelper.createFrame(-2, -2.0f));
        this.legendLayout.addView(this.totlaSizeTextView, LayoutHelper.createFrame(-2, -2.0f));
        this.legendLayout.addView(this.freeSizeTextView, LayoutHelper.createFrame(-2, -2.0f));
        View r142 = new View(getContext());
        this.divider = r142;
        r03.addView(r142, LayoutHelper.createLinear(-1, -2, 0, 21, 0, 0, 0));
        this.divider.getLayoutParams().height = 1;
        this.divider.setBackgroundColor(Theme.getColor(Theme.key_divider));
        TextSettingsCell r143 = new TextSettingsCell(getContext());
        this.textSettingsCell = r143;
        r03.addView(r143, LayoutHelper.createLinear(-1, -2));
        return;
    L5:
        this.calculatingTextView.setText(r53);
        goto L6
    }

    private /* synthetic */ void lambda$setStorageUsage$0(ValueAnimator r1) {
        this.progress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private /* synthetic */ void lambda$setStorageUsage$1(ValueAnimator r1) {
        this.progress2 = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        this.progressView.invalidate();
        int r0 = this.lastProgressColor;
        int r1 = Theme.key_player_progress;
        if (r0 == Theme.getColor(r1)) goto L5;
        this.lastProgressColor = Theme.getColor(r1);
        this.telegramCacheTextView.setCompoundDrawablesWithIntrinsicBounds(Theme.createCircleDrawable(AndroidUtilities.dp(10.0f), this.lastProgressColor), null, null, null);
        this.telegramCacheTextView.setCompoundDrawablePadding(AndroidUtilities.dp(6.0f));
        this.telegramDatabaseTextView.setCompoundDrawablesWithIntrinsicBounds(Theme.createCircleDrawable(AndroidUtilities.dp(10.0f), this.lastProgressColor), null, null, null);
        this.telegramDatabaseTextView.setCompoundDrawablePadding(AndroidUtilities.dp(6.0f));
        this.freeSizeTextView.setCompoundDrawablesWithIntrinsicBounds(Theme.createCircleDrawable(AndroidUtilities.dp(10.0f), ColorUtils.setAlphaComponent(this.lastProgressColor, 64)), null, null, null);
        this.freeSizeTextView.setCompoundDrawablePadding(AndroidUtilities.dp(6.0f));
        this.totlaSizeTextView.setCompoundDrawablesWithIntrinsicBounds(Theme.createCircleDrawable(AndroidUtilities.dp(10.0f), ColorUtils.setAlphaComponent(this.lastProgressColor, 127)), null, null, null);
        this.totlaSizeTextView.setCompoundDrawablePadding(AndroidUtilities.dp(6.0f));
    L5:
        this.textSettingsCell.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.divider.setBackgroundColor(Theme.getColor(Theme.key_divider));
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        EllipsizeSpanAnimator r0 = this.ellipsizeSpanAnimator;
        if (r0 == null) goto L6;
        r0.onAttachedToWindow();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        EllipsizeSpanAnimator r0 = this.ellipsizeSpanAnimator;
        if (r0 == null) goto L6;
        r0.onDetachedFromWindow();
        return;
    }

    public void setStorageUsage(boolean r7, long r8, long r10, long r12, long r14) {
        this.calculating = r7;
        this.totalSize = r10;
        this.totalDeviceFreeSize = r12;
        this.totalDeviceSize = r14;
        this.freeSizeTextView.setText(LocaleController.formatString("TotalDeviceFreeSize", R.string.TotalDeviceFreeSize, new Object[]{AndroidUtilities.formatFileSize(r12)}));
        long r122 = r14 - r12;
        this.totlaSizeTextView.setText(LocaleController.formatString("TotalDeviceSize", R.string.TotalDeviceSize, new Object[]{AndroidUtilities.formatFileSize(r122)}));
        if (r7 == false) goto L7;
        this.calculatingTextView.setVisibility(0);
        this.telegramCacheTextView.setVisibility(8);
        this.freeSizeTextView.setVisibility(8);
        this.totlaSizeTextView.setVisibility(8);
        this.telegramDatabaseTextView.setVisibility(8);
        this.divider.setVisibility(8);
        this.textSettingsCell.setVisibility(8);
        this.progress = 0.0f;
        this.progress2 = 0.0f;
        EllipsizeSpanAnimator r72 = this.ellipsizeSpanAnimator;
        if (r72 == null) goto L26;
        r72.addView(this.calculatingTextView);
    L26:
        this.textSettingsCell.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        requestLayout();
        return;
    L7:
        EllipsizeSpanAnimator r73 = this.ellipsizeSpanAnimator;
        if (r73 == null) goto L10;
        r73.removeView(this.calculatingTextView);
    L10:
        this.calculatingTextView.setVisibility(8);
        if (r10 <= 0) goto L13;
        this.divider.setVisibility(0);
        this.textSettingsCell.setVisibility(0);
        this.telegramCacheTextView.setVisibility(0);
        this.telegramDatabaseTextView.setVisibility(8);
        this.textSettingsCell.setTextAndValue(LocaleController.getString(R.string.ClearTelegramCache), AndroidUtilities.formatFileSize(r10), true);
        this.telegramCacheTextView.setText(LocaleController.formatString("TelegramCacheSize", R.string.TelegramCacheSize, new Object[]{AndroidUtilities.formatFileSize(r10 + r8)}));
    L14:
        this.freeSizeTextView.setVisibility(0);
        this.totlaSizeTextView.setVisibility(0);
        float r74 = r10 + r8;
        float r82 = r14;
        float r75 = r74 / r82;
        float r9 = r122 / r82;
        if (this.progress == r75) goto L21;
        ValueAnimator r83 = this.valueAnimator;
        if (r83 == null) goto L19;
        r83.cancel();
    L19:
        ValueAnimator r76 = ValueAnimator.ofFloat(new float[]{this.progress, r75});
        this.valueAnimator = r76;
        r76.addUpdateListener(new StorageUsageView$$ExternalSyntheticLambda0(this));
        this.valueAnimator.start();
    L21:
        if (this.progress2 == r9) goto L26;
        ValueAnimator r77 = this.valueAnimator2;
        if (r77 == null) goto L25;
        r77.cancel();
    L25:
        ValueAnimator r78 = ValueAnimator.ofFloat(new float[]{this.progress2, r9});
        this.valueAnimator2 = r78;
        r78.addUpdateListener(new StorageUsageView$$ExternalSyntheticLambda1(this));
        this.valueAnimator2.start();
        goto L26
    L13:
        this.telegramCacheTextView.setVisibility(8);
        this.telegramDatabaseTextView.setVisibility(0);
        this.telegramDatabaseTextView.setText(LocaleController.formatString("LocalDatabaseSize", R.string.LocalDatabaseSize, new Object[]{AndroidUtilities.formatFileSize(r8)}));
        this.divider.setVisibility(8);
        this.textSettingsCell.setVisibility(8);
        goto L14
    }
}
