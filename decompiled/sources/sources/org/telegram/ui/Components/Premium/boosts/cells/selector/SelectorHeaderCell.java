package org.telegram.ui.Components.Premium.boosts.cells.selector;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class SelectorHeaderCell extends FrameLayout {
    public BackDrawable backDrawable;
    private final ImageView closeView;
    private final Paint dividerPaint;
    private Runnable onCloseClickListener;
    private final Theme.ResourcesProvider resourcesProvider;
    private final TextView textView;

    /* renamed from: $r8$lambda$JomjPzG-aT3SRLigaXIRUhJ6gwM, reason: not valid java name */
    public static /* synthetic */ void m12174$r8$lambda$JomjPzGaT3SRLigaXIRUhJ6gwM(SelectorHeaderCell r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public SelectorHeaderCell(Context r18, Theme.ResourcesProvider r19) {
        super(r18);
        this.dividerPaint = new Paint(1);
        this.resourcesProvider = r19;
        TextView r3 = new TextView(r18);
        this.textView = r3;
        r3.setTypeface(AndroidUtilities.bold());
        r3.setTextSize(1, 20.0f);
        int r5 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r4 = 5;
    L6:
        r3.setGravity(r4);
        int r42 = Theme.key_dialogTextBlack;
        r3.setTextColor(Theme.getColor(r42, r19));
        boolean r7 = LocaleController.isRTL;
        if (r7 == false) goto L9;
        float r13 = 16.0f;
    L10:
        if (r7 == false) goto L12;
        float r15 = 53.0f;
    L13:
        addView(r3, LayoutHelper.createFrame(-1, -2.0f, 23, r13, 0.0f, r15, 0.0f));
        ImageView r32 = new ImageView(r18);
        this.closeView = r32;
        BackDrawable r1 = new BackDrawable(false);
        this.backDrawable = r1;
        r32.setImageDrawable(r1);
        this.backDrawable.setColor(Theme.getColor(r42, r19));
        this.backDrawable.setRotatedColor(Theme.getColor(r42, r19));
        this.backDrawable.setAnimationTime(220.0f);
        if (LocaleController.isRTL == false) goto L16;
        r5 = 5;
    L16:
        addView(r32, LayoutHelper.createFrame(24, 24.0f, r5 | 16, 16.0f, 0.0f, 16.0f, 0.0f));
        r32.setOnClickListener(new SelectorHeaderCell$$ExternalSyntheticLambda0(this));
        return;
    L12:
        r15 = 16.0f;
        goto L13
    L9:
        r13 = 53.0f;
        goto L10
    L5:
        r4 = 3;
        goto L6
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        Runnable r0 = this.onCloseClickListener;
        if (r0 == null) goto L6;
        r0.run();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r8) {
        super.dispatchDraw(r8);
        this.dividerPaint.setColor(Theme.getColor(Theme.key_divider, this.resourcesProvider));
        r8.drawRect(0.0f, getHeight() - AndroidUtilities.getShadowHeight(), getWidth(), getHeight(), this.dividerPaint);
    }

    public int getHeaderHeight() {
        return AndroidUtilities.dp(56.0f);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(getHeaderHeight(), TLObject.FLAG_30));
    }

    public void setBackImage(int r1) {
        this.closeView.setImageResource(r1);
    }

    public void setCloseImageVisible(boolean r11) {
        ImageView r0 = this.closeView;
        if (r11 == false) goto L5;
        int r1 = 0;
    L6:
        r0.setVisibility(r1);
        TextView r10 = this.textView;
        boolean r02 = LocaleController.isRTL;
        if (r02 == true) goto L11;
        if (r11 == false) goto L11;
        float r6 = 53.0f;
    L12:
        if (r02 == false) goto L15;
        if (r11 == false) goto L15;
        float r8 = 53.0f;
    L16:
        r10.setLayoutParams(LayoutHelper.createFrame(-1, -2.0f, 23, r6, 0.0f, r8, 0.0f));
        return;
    L15:
        r8 = 22.0f;
    L11:
        r6 = 22.0f;
        goto L12
    L5:
        r1 = 8;
        goto L6
    }

    public void setOnCloseClickListener(Runnable r1) {
        this.onCloseClickListener = r1;
    }

    public void setText(CharSequence r1) {
        this.textView.setText(r1);
    }
}
