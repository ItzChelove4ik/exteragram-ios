package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.SaveToGallerySettingsHelper;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.SeekBarView;

/* loaded from: classes6.dex */
public abstract class MaxFileSizeCell extends FrameLayout {
    private long currentSize;
    private SeekBarView seekBarView;
    private TextView sizeTextView;
    private TextView textView;

    /* renamed from: org.telegram.ui.Cells.MaxFileSizeCell$1, reason: invalid class name */
    public class AnonymousClass1 extends SeekBarView {
        final /* synthetic */ MaxFileSizeCell this$0;

        public AnonymousClass1(MaxFileSizeCell r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.SeekBarView, android.view.View
        public boolean onTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L6;
            getParent().requestDisallowInterceptTouchEvent(true);
        L6:
            return super.onTouchEvent(r3);
        }
    }

    /* renamed from: org.telegram.ui.Cells.MaxFileSizeCell$2, reason: invalid class name */
    public class AnonymousClass2 implements SeekBarView.SeekBarViewDelegate {
        final /* synthetic */ MaxFileSizeCell this$0;

        public AnonymousClass2(MaxFileSizeCell r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public CharSequence getContentDescription() {
            return MaxFileSizeCell.m6962$$Nest$fgettextView(this.this$0).getText() + " " + MaxFileSizeCell.m6961$$Nest$fgetsizeTextView(this.this$0).getText();
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarDrag(boolean r6, float r7) {
            if (r7 > 0.25f) goto L7;
            float r72 = (r7 / 0.25f) * 536576.0f;
            float r62 = 512000.0f;
        L5:
            float r63 = r62 + r72;
        L6:
            int r64 = (int) r63;
            long r1 = r64;
            MaxFileSizeCell.m6961$$Nest$fgetsizeTextView(this.this$0).setText(LocaleController.formatString("AutodownloadSizeLimitUpTo", R.string.AutodownloadSizeLimitUpTo, new Object[]{AndroidUtilities.formatFileSize(r1)}));
            MaxFileSizeCell.m6963$$Nest$fputcurrentSize(this.this$0, r1);
            this.this$0.didChangedSizeValue(r64);
            return;
        L7:
            float r73 = r7 - 0.25f;
            if (r73 >= 0.25f) goto L10;
            r72 = (r73 / 0.25f) * 9437184.0f;
            r62 = 1048576.0f;
            goto L5
        L10:
            float r74 = r73 - 0.25f;
            if (r74 > 0.25f) goto L13;
            r72 = (r74 / 0.25f) * 9.437184E7f;
            r62 = 1.048576E7f;
            goto L5
        L13:
            r63 = 1.048576E8f + (1.993343E9f * ((r74 - 0.25f) / 0.25f));
            goto L6
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarPressed(boolean r1) {
        }
    }

    /* renamed from: -$$Nest$fgetsizeTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m6961$$Nest$fgetsizeTextView(MaxFileSizeCell r0) {
        return r0.sizeTextView;
    }

    /* renamed from: -$$Nest$fgettextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m6962$$Nest$fgettextView(MaxFileSizeCell r0) {
        return r0.textView;
    }

    /* renamed from: -$$Nest$fputcurrentSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6963$$Nest$fputcurrentSize(MaxFileSizeCell r0, long r1) {
        r0.currentSize = r1;
    }

    public MaxFileSizeCell(Context r15) {
        super(r15);
        setWillNotDraw(false);
        TextView r0 = new TextView(r15);
        this.textView = r0;
        r0.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        this.textView.setTextSize(1, 16.0f);
        this.textView.setLines(1);
        this.textView.setMaxLines(1);
        this.textView.setSingleLine(true);
        TextView r02 = this.textView;
        int r4 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r3 = 5;
    L6:
        r02.setGravity(r3 | 48);
        this.textView.setEllipsize(TextUtils.TruncateAt.END);
        this.textView.setImportantForAccessibility(2);
        TextView r03 = this.textView;
        if (LocaleController.isRTL == false) goto L9;
        int r6 = 5;
    L10:
        addView(r03, LayoutHelper.createFrame(-1, -1.0f, r6 | 48, 21.0f, 13.0f, 21.0f, 0.0f));
        TextView r04 = new TextView(r15);
        this.sizeTextView = r04;
        r04.setTextColor(Theme.getColor(Theme.key_dialogTextBlue2));
        this.sizeTextView.setTextSize(1, 16.0f);
        this.sizeTextView.setLines(1);
        this.sizeTextView.setMaxLines(1);
        this.sizeTextView.setSingleLine(true);
        TextView r05 = this.sizeTextView;
        if (LocaleController.isRTL == false) goto L13;
        int r2 = 3;
    L14:
        r05.setGravity(r2 | 48);
        this.sizeTextView.setImportantForAccessibility(2);
        TextView r06 = this.sizeTextView;
        if (LocaleController.isRTL == true) goto L18;
        r4 = 5;
    L18:
        addView(r06, LayoutHelper.createFrame(-2, -1.0f, r4 | 48, 21.0f, 13.0f, 21.0f, 0.0f));
        AnonymousClass1 r07 = new AnonymousClass1(this, r15);
        this.seekBarView = r07;
        r07.setReportChanges(true);
        this.seekBarView.setDelegate(new AnonymousClass2(this));
        this.seekBarView.setImportantForAccessibility(2);
        addView(this.seekBarView, LayoutHelper.createFrame(-1, 38.0f, 51, 6.0f, 36.0f, 6.0f, 0.0f));
        setImportantForAccessibility(1);
        setAccessibilityDelegate(this.seekBarView.getSeekBarAccessibilityDelegate());
        return;
    L13:
        r2 = 5;
        goto L14
    L9:
        r6 = 3;
        goto L10
    L5:
        r3 = 3;
        goto L6
    }

    public abstract void didChangedSizeValue(int r1);

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r2) {
        if (isEnabled() == true) goto L7;
        return true;
    L7:
        return super.dispatchTouchEvent(r2);
    }

    public long getSize() {
        return this.currentSize;
    }

    @Override // android.view.View
    public void onDraw(Canvas r9) {
        if (LocaleController.isRTL == false) goto L6;
        float r0 = 0.0f;
    L5:
        float r3 = r0;
        float r4 = getMeasuredHeight() - 1;
        int r02 = getMeasuredWidth();
        if (LocaleController.isRTL == false) goto L10;
        int r1 = AndroidUtilities.dp(20.0f);
    L11:
        r9.drawLine(r3, r4, r02 - r1, getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    L10:
        r1 = 0;
        goto L11
    L6:
        r0 = AndroidUtilities.dp(20.0f);
        goto L5
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r2) {
        if (isEnabled() == true) goto L7;
        return true;
    L7:
        return super.onInterceptTouchEvent(r2);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r5, int r6) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r5), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(80.0f), TLObject.FLAG_30));
        setMeasuredDimension(View.MeasureSpec.getSize(r5), AndroidUtilities.dp(80.0f));
        int r52 = getMeasuredWidth() - AndroidUtilities.dp(42.0f);
        this.sizeTextView.measure(View.MeasureSpec.makeMeasureSpec(r52, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(30.0f), TLObject.FLAG_30));
        int r53 = Math.max(AndroidUtilities.dp(10.0f), (r52 - this.sizeTextView.getMeasuredWidth()) - AndroidUtilities.dp(8.0f));
        this.textView.measure(View.MeasureSpec.makeMeasureSpec(r53, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(30.0f), TLObject.FLAG_30));
        this.seekBarView.measure(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth() - AndroidUtilities.dp(12.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(30.0f), TLObject.FLAG_30));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r2) {
        if (isEnabled() == true) goto L7;
        return true;
    L7:
        return super.onTouchEvent(r2);
    }

    public void setEnabled(boolean r9, ArrayList<Animator> r10) {
        super.setEnabled(r9);
        TextView r0 = this.textView;
        float r1 = 0.5f;
        if (r10 == null) goto L16;
        if (r9 == false) goto L6;
        float r3 = 1.0f;
    L7:
        r10.add(ObjectAnimator.ofFloat(r0, "alpha", new float[]{r3}));
        SeekBarView r02 = this.seekBarView;
        if (r9 == false) goto L10;
        float r5 = 1.0f;
    L11:
        r10.add(ObjectAnimator.ofFloat(r02, "alpha", new float[]{r5}));
        TextView r8 = this.sizeTextView;
        if (r9 == false) goto L14;
        r1 = 1.0f;
    L14:
        r10.add(ObjectAnimator.ofFloat(r8, "alpha", new float[]{r1}));
        return;
    L10:
        r5 = 0.5f;
        goto L11
    L6:
        r3 = 0.5f;
        goto L7
    L16:
        if (r9 == false) goto L18;
        float r102 = 1.0f;
    L19:
        r0.setAlpha(r102);
        SeekBarView r103 = this.seekBarView;
        if (r9 == false) goto L22;
        float r03 = 1.0f;
    L23:
        r103.setAlpha(r03);
        TextView r82 = this.sizeTextView;
        if (r9 == false) goto L26;
        r1 = 1.0f;
    L26:
        r82.setAlpha(r1);
        return;
    L22:
        r03 = 0.5f;
        goto L23
    L18:
        r102 = 0.5f;
        goto L19
    }

    public void setSize(long r8) {
        this.currentSize = r8;
        this.sizeTextView.setText(LocaleController.formatString("AutodownloadSizeLimitUpTo", R.string.AutodownloadSizeLimitUpTo, new Object[]{AndroidUtilities.formatFileSize(r8)}));
        long r0 = r8 - 512000;
        if (r0 >= 536576) goto L5;
        float r82 = Math.max(0.0f, r0 / 536576.0f) * 0.25f;
    L13:
        this.seekBarView.setProgress(Math.min(1.0f, r82));
        return;
    L5:
        long r02 = r8 - 1048576;
        if (r02 >= 9437184) goto L8;
        r82 = (Math.max(0.0f, r02 / 9437184.0f) * 0.25f) + 0.25f;
        goto L13
    L8:
        long r03 = r8 - 10485760;
        if (r03 >= 94371840) goto L12;
        float r83 = Math.max(0.0f, r03 / 9.437184E7f) * 0.25f;
        float r9 = 0.5f;
    L11:
        r82 = r83 + r9;
        goto L13
    L12:
        r83 = Math.max(0.0f, (r8 - SaveToGallerySettingsHelper.DEFAULT_VIDEO_LIMIT) / 1.993343E9f) * 0.25f;
        r9 = 0.75f;
        goto L11
    }

    public void setText(String r1) {
        this.textView.setText(r1);
    }
}
