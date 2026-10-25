package org.telegram.ui.Cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import com.google.android.material.navigation.NavigationBarView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes6.dex */
public class CollapseTextCell extends FrameLayout {
    private View collapsedArrow;
    private Theme.ResourcesProvider resourcesProvider;
    public final AnimatedTextView textView;

    public static /* synthetic */ void $r8$lambda$VJg6Lpb29BMwHoa_nbZvvIoioVk(CollapseTextCell r0) {
        r0.updateCollapseArrowTranslation();
    }

    @SuppressLint({"UseCompatLoadingForDrawables"})
    public CollapseTextCell(Context r11, Theme.ResourcesProvider r12) {
        super(r11);
        this.resourcesProvider = r12;
        AnimatedTextView r0 = new AnimatedTextView(r11);
        this.textView = r0;
        int r1 = Theme.key_windowBackgroundWhiteBlackText;
        r0.setTextColor(Theme.getColor(r1, r12));
        r0.setTextSize(AndroidUtilities.dp(14.0f));
        if (LocaleController.isRTL == false) goto L5;
        int r2 = 5;
    L6:
        r0.setGravity(r2);
        r0.setImportantForAccessibility(2);
        r0.setOnWidthUpdatedListener(new CollapseTextCell$$ExternalSyntheticLambda0(this));
        addView(r0, LayoutHelper.createFrameRelatively(-2.0f, -2.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 21.0f, 0.0f, 38.0f, 3.0f));
        this.collapsedArrow = new View(r11);
        Drawable r112 = getContext().getResources().getDrawable(R.drawable.arrow_more).mutate();
        r112.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r1, r12), PorterDuff.Mode.MULTIPLY));
        this.collapsedArrow.setBackground(r112);
        addView(this.collapsedArrow, LayoutHelper.createFrameRelatively(14.0f, 14.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 21.0f, 1.0f, 0.0f, 3.0f));
        return;
    L5:
        r2 = 3;
        goto L6
    }

    private void updateCollapseArrowTranslation() {
        float r0 = this.textView.getDrawable().getCurrentWidth() + AndroidUtilities.dp(1.0f);
        boolean r1 = LocaleController.isRTL;
        View r2 = this.collapsedArrow;
        if (r1 == false) goto L6;
        r2.setTranslationX(-r0);
        return;
    L6:
        r2.setTranslationX(r0);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(46.0f), TLObject.FLAG_30));
        updateCollapseArrowTranslation();
    }

    public void set(CharSequence r2, boolean r3) {
        this.textView.setText(r2);
        this.collapsedArrow.animate().cancel();
        ViewPropertyAnimator r1 = this.collapsedArrow.animate();
        if (r3 == false) goto L5;
        float r22 = 0.0f;
    L6:
        r1.rotation(r22).setDuration(340).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
        return;
    L5:
        r22 = 180.0f;
        goto L6
    }

    public void setColor(int r3) {
        int r32 = Theme.getColor(r3, this.resourcesProvider);
        this.textView.setTextColor(r32);
        this.collapsedArrow.getBackground().setColorFilter(new PorterDuffColorFilter(r32, PorterDuff.Mode.MULTIPLY));
    }
}
