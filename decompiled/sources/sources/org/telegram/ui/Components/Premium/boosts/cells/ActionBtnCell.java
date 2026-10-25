package org.telegram.ui.Components.Premium.boosts.cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.FrameLayout;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class ActionBtnCell extends FrameLayout {
    private final View backgroundView;
    public final ButtonWithCounterView button;
    private final Paint dividerPaint;
    private boolean drawDivider;
    private final Theme.ResourcesProvider resourcesProvider;

    public ActionBtnCell(Context r9, Theme.ResourcesProvider r10) {
        super(r9);
        this.dividerPaint = new Paint(1);
        this.resourcesProvider = r10;
        View r0 = new View(r9);
        this.backgroundView = r0;
        addView(r0, LayoutHelper.createLinear(-1, -1));
        ButtonWithCounterView r02 = new ButtonWithCounterView(r9, r10);
        this.button = r02;
        addView(r02, LayoutHelper.createFrame(-1, 48.0f, 17, 14.0f, 0.0f, 14.0f, 0.0f));
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r8) {
        super.dispatchDraw(r8);
        if (this.drawDivider == false) goto L6;
        this.dividerPaint.setColor(Theme.getColor(Theme.key_windowBackgroundGray, this.resourcesProvider));
        this.dividerPaint.setAlpha(255);
        r8.drawRect(0.0f, 0.0f, getWidth(), 1.0f, this.dividerPaint);
        return;
    }

    public boolean isLoading() {
        return this.button.isLoading();
    }

    public void setActivateForFreeStyle() {
        this.drawDivider = true;
        this.button.setEnabled(true);
        this.button.setText(LocaleController.getString(R.string.GiftPremiumActivateForFree), false);
        this.backgroundView.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground, this.resourcesProvider));
    }

    public void setCloseStyle() {
        this.drawDivider = false;
        this.button.setShowZero(false);
        this.button.setEnabled(true);
        this.button.setText(LocaleController.formatString("Close", R.string.Close, new Object[0]), false);
    }

    public void setCloseStyle(boolean r1) {
        setCloseStyle();
        this.drawDivider = r1;
    }

    public void setGiftPremiumStyle(int r3, boolean r4, boolean r5) {
        this.drawDivider = true;
        this.button.withCounterIcon();
        this.button.setShowZero(true);
        this.button.setEnabled(r5);
        this.button.setCount(r3, r4);
        this.button.setText(LocaleController.getString(R.string.GiftPremium), r4);
        this.backgroundView.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground, this.resourcesProvider));
    }

    public void setOkStyle(boolean r4) {
        this.drawDivider = false;
        this.button.setShowZero(false);
        this.button.setEnabled(true);
        if (r4 == false) goto L5;
        String r42 = LocaleController.formatString("BoostingUseLink", R.string.BoostingUseLink, new Object[0]);
    L6:
        this.button.setText(r42, false);
        return;
    L5:
        r42 = LocaleController.formatString("OK", R.string.OK, new Object[0]);
        goto L6
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener r1) {
        this.button.setOnClickListener(r1);
    }

    public void setStartGiveAwayStyle(int r3, boolean r4) {
        this.drawDivider = true;
        this.button.withCounterIcon();
        this.button.setShowZero(true);
        this.button.setEnabled(true);
        this.button.setCount(r3, r4);
        this.button.setText(LocaleController.getString(R.string.BoostingStartGiveaway), r4);
        this.backgroundView.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground, this.resourcesProvider));
    }

    public void updateCounter(int r2) {
        this.button.setCount(r2, true);
    }

    public void updateLoading(boolean r1) {
        this.button.setLoading(r1);
    }
}
