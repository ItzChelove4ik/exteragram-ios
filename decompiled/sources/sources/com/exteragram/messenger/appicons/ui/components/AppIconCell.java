package com.exteragram.messenger.appicons.ui.components;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.appicons.AppIcon;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes4.dex */
public class AppIconCell extends LinearLayout implements Theme.Colorable {
    private final CheckBox2 checkBox;
    private AppIcon icon;
    private final FrameLayout previewContainer;
    private final AppIconPreviewView previewView;
    private final Theme.ResourcesProvider resourcesProvider;
    private float selection;
    private ValueAnimator selectionAnimator;
    private final Paint selectionPaint;
    private final TextView titleView;

    public static class Factory extends UItem.UItemFactory<AppIconCell> {
        static {
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        public static UItem asAppIcon(AppIcon r1, boolean r2) {
            UItem r0 = UItem.ofFactory(Factory.class);
            r0.object = r1;
            r0.checked = r2;
            r0.spanCount = 1;
            r0.transparent = true;
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
            if ((r1 instanceof AppIconCell) == false) goto L12;
            AppIconCell r12 = (AppIconCell) r1;
            Object r0 = r2.object;
            if ((r0 instanceof AppIcon) == false) goto L13;
            AppIcon r02 = (AppIcon) r0;
            boolean r22 = r2.checked;
            if (r12.getIcon() != r02) goto L9;
            boolean r32 = true;
        L10:
            r12.set(r02, r22, r32);
            return;
        L9:
            r32 = false;
            goto L10
        L13:
            return;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean contentsEquals(UItem r2, UItem r3) {
            if (r2.object == r3.object) goto L5;
            return false;
        L5:
            if (r2.checked != r3.checked) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public AppIconCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return new AppIconCell(r1, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public boolean equals(UItem r1, UItem r2) {
            if (r1.object != r2.object) goto L6;
            return true;
        L6:
            return false;
        }
    }

    public static /* synthetic */ void $r8$lambda$AMSOy8IGUwrkkFcf7Pf71b4lJ1E(AppIconCell r0, ValueAnimator r1) {
        r0.lambda$setSelected$2(r1);
    }

    /* renamed from: $r8$lambda$gq6lU44ZBGP-3hgfP-Qptu0AVw4, reason: not valid java name */
    public static /* synthetic */ void m1133$r8$lambda$gq6lU44ZBGP3hgfPQptu0AVw4(AppIconCell r0, float r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: $r8$lambda$ifd4l_PSgiwEjkHXYS77Zlt-VrQ, reason: not valid java name */
    public static /* synthetic */ int m1134$r8$lambda$ifd4l_PSgiwEjkHXYS77ZltVrQ(AppIconCell r0, Theme.ResourcesProvider r1, int r2) {
        return r0.lambda$new$0(r1, r2);
    }

    public AppIconCell(Context r12, Theme.ResourcesProvider r13) {
        super(r12);
        this.selectionPaint = new Paint(1);
        this.resourcesProvider = r13;
        setWillNotDraw(false);
        setClipChildren(false);
        setOrientation(1);
        setGravity(1);
        setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(12.0f));
        FrameLayout r2 = new FrameLayout(r12);
        this.previewContainer = r2;
        r2.setClipChildren(false);
        AppIconPreviewView r3 = new AppIconPreviewView(r12, r13);
        this.previewView = r3;
        r2.addView(r3, LayoutHelper.createFrame(-1, -1.0f));
        CheckBox2 r32 = new CheckBox2(r12, 21, new AppIconCell$$ExternalSyntheticLambda0(this, r13));
        this.checkBox = r32;
        r32.setColor(Theme.key_featuredStickers_addButton, Theme.key_windowBackgroundWhite, Theme.key_checkboxCheck);
        r32.setDrawUnchecked(false);
        r32.setDrawBackgroundAsArc(4);
        r32.setProgressDelegate(new AppIconCell$$ExternalSyntheticLambda1(this));
        r2.addView(r32, LayoutHelper.createFrame(24, 24.0f, 85, 0.0f, 0.0f, 1.0f, 1.0f));
        addView(r2, LayoutHelper.createLinear(68, 68, 1));
        TextView r0 = new TextView(r12);
        this.titleView = r0;
        r0.setMaxLines(2);
        r0.setGravity(17);
        r0.setEllipsize(TextUtils.TruncateAt.END);
        r0.setTypeface(AndroidUtilities.regular());
        r0.setTextSize(1, 13.0f);
        r0.setLineSpacing(0.0f, 0.95f);
        r0.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r13));
        addView(r0, LayoutHelper.createLinear(-1, -2, 1, 0, 8, 0, 0));
        ScaleStateListAnimator.apply(this, 0.05f, 1.2f);
    }

    private int getSelectionColor() {
        return ColorUtils.blendARGB(Theme.getColor(Theme.key_windowBackgroundWhite, this.resourcesProvider), Theme.getColor(Theme.key_featuredStickers_addButton, this.resourcesProvider), this.selection * 0.07f);
    }

    private /* synthetic */ int lambda$new$0(Theme.ResourcesProvider r2, int r3) {
        if (r3 != Theme.key_windowBackgroundWhite) goto L7;
        return getSelectionColor();
    L7:
        return Theme.getColor(r3, r2);
    }

    private /* synthetic */ void lambda$new$1(float r2) {
        this.previewView.setPreviewScale(1.0f - (r2 * 0.08f));
    }

    private /* synthetic */ void lambda$setSelected$2(ValueAnimator r1) {
        this.selection = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private void setSelected(boolean r3, boolean r4) {
        this.checkBox.setChecked(r3, r4);
        if (r3 == false) goto L5;
        float r32 = 1.0f;
    L6:
        ValueAnimator r0 = this.selectionAnimator;
        if (r0 == null) goto L9;
        r0.cancel();
        this.selectionAnimator = null;
    L9:
        if (r4 == true) goto L12;
        this.selection = r32;
        invalidate();
        return;
    L12:
        float r42 = this.selection;
        if (r42 != r32) goto L15;
        return;
    L15:
        ValueAnimator r33 = ValueAnimator.ofFloat(new float[]{r42, r32}).setDuration(220);
        this.selectionAnimator = r33;
        r33.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.selectionAnimator.addUpdateListener(new AppIconCell$$ExternalSyntheticLambda2(this));
        this.selectionAnimator.start();
        return;
    L5:
        r32 = 0.0f;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    public AppIcon getIcon() {
        return this.icon;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onDraw(Canvas r7) {
        if (this.selection > 0.0f) goto L5;
        return;
    L5:
        this.selectionPaint.setColor(getSelectionColor());
        RectF r0 = AndroidUtilities.rectTmp;
        r0.set(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), getWidth() - AndroidUtilities.dp(2.0f), getHeight() - AndroidUtilities.dp(2.0f));
        r7.drawRoundRect(r0, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), this.selectionPaint);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r4, int r5) {
        int r0 = (View.MeasureSpec.getSize(r4) - getPaddingLeft()) - getPaddingRight();
        int r02 = Math.max(AndroidUtilities.dp(40.0f), Math.min(AndroidUtilities.dp(68.0f), r0));
        LinearLayout.LayoutParams r1 = (LinearLayout.LayoutParams) this.previewContainer.getLayoutParams();
        if (r1.width == r02) goto L5;
        r1.height = r02;
        r1.width = r02;
    L5:
        super.onMeasure(r4, r5);
    }

    public void set(AppIcon r2, boolean r3, boolean r4) {
        this.icon = r2;
        this.previewView.setIcon(r2);
        this.titleView.setText(r2.getTitle());
        setSelected(r3, r4);
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        this.titleView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
        invalidate();
    }
}
