package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.SeekBarView;

/* loaded from: classes6.dex */
public abstract class BrightnessControlCell extends FrameLayout {
    private ImageView leftImageView;
    Theme.ResourcesProvider resourcesProvider;
    private ImageView rightImageView;
    public final SeekBarView seekBarView;
    private final int size;
    private int type;

    /* renamed from: org.telegram.ui.Cells.BrightnessControlCell$1, reason: invalid class name */
    public class AnonymousClass1 extends SeekBarView {
        final /* synthetic */ BrightnessControlCell this$0;

        public AnonymousClass1(BrightnessControlCell r1, Context r2, boolean r3, Theme.ResourcesProvider r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.SeekBarView, android.view.View
        public boolean onTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L6;
            getParent().requestDisallowInterceptTouchEvent(true);
        L6:
            return super.onTouchEvent(r3);
        }
    }

    /* renamed from: org.telegram.ui.Cells.BrightnessControlCell$2, reason: invalid class name */
    public class AnonymousClass2 implements SeekBarView.SeekBarViewDelegate {
        final /* synthetic */ BrightnessControlCell this$0;

        public AnonymousClass2(BrightnessControlCell r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public CharSequence getContentDescription() {
            return " ";
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarDrag(boolean r1, float r2) {
            this.this$0.didChangedValue(r2);
        }

        @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
        public void onSeekBarPressed(boolean r1) {
        }
    }

    public BrightnessControlCell(Context r2, int r3) {
        this(r2, r3, null);
    }

    public BrightnessControlCell(Context r9, int r10, Theme.ResourcesProvider r11) {
        super(r9);
        this.type = r10;
        this.resourcesProvider = r11;
        ImageView r0 = new ImageView(r9);
        this.leftImageView = r0;
        addView(r0, LayoutHelper.createFrame(24, 24.0f, 51, 17.0f, 12.0f, 0.0f, 0.0f));
        AnonymousClass1 r02 = new AnonymousClass1(this, r9, true, r11);
        this.seekBarView = r02;
        r02.setReportChanges(true);
        r02.setDelegate(new AnonymousClass2(this));
        r02.setImportantForAccessibility(2);
        addView(r02, LayoutHelper.createFrame(-1, 38.0f, 51, 54.0f, 5.0f, 54.0f, 0.0f));
        ImageView r112 = new ImageView(r9);
        this.rightImageView = r112;
        addView(r112, LayoutHelper.createFrame(24, 24.0f, 53, 0.0f, 12.0f, 17.0f, 0.0f));
        ImageView r92 = this.leftImageView;
        if (r10 != 0) goto L6;
        r92.setImageResource(R.drawable.msg_brightness_low);
        this.rightImageView.setImageResource(R.drawable.msg_brightness_high);
        this.size = 48;
        return;
    L6:
        r92.setImageResource(R.drawable.msg_brightness_high);
        this.rightImageView.setImageResource(R.drawable.msg_brightness_low);
        this.size = 43;
    }

    public abstract void didChangedValue(float r1);

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ImageView r0 = this.leftImageView;
        int r2 = Theme.key_windowBackgroundWhiteGrayIcon;
        int r3 = Theme.getColor(r2, this.resourcesProvider);
        PorterDuff.Mode r4 = PorterDuff.Mode.MULTIPLY;
        r0.setColorFilter(new PorterDuffColorFilter(r3, r4));
        this.rightImageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r2, this.resourcesProvider), r4));
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
        super.onInitializeAccessibilityNodeInfo(r2);
        this.seekBarView.getSeekBarAccessibilityDelegate().onInitializeAccessibilityNodeInfoInternal(this, r2);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(this.size), TLObject.FLAG_30));
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int r2, Bundle r3) {
        if (super.performAccessibilityAction(r2, r3) == false) goto L5;
        return true;
    L5:
        if (this.seekBarView.getSeekBarAccessibilityDelegate().performAccessibilityActionInternal(this, r2, r3) == true) goto L11;
        return false;
    L11:
        return true;
    }

    public void setProgress(float r1) {
        this.seekBarView.setProgress(r1);
    }
}
