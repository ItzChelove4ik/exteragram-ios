package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.widget.FrameLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.BadWayToMakeButtonRound;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ScaleStateListAnimator;

/* loaded from: classes6.dex */
public class LocationDirectionCell extends FrameLayout {
    private SimpleTextView buttonTextView;
    private FrameLayout frameLayout;
    private final Theme.ResourcesProvider resourcesProvider;

    public LocationDirectionCell(Context r12, Theme.ResourcesProvider r13) {
        super(r12);
        this.resourcesProvider = r13;
        FrameLayout r132 = new FrameLayout(r12);
        this.frameLayout = r132;
        r132.setBackground(Theme.AdaptiveRipple.filledRect(getThemedColor(Theme.key_featuredStickers_addButton), new float[]{8.0f}));
        addView(this.frameLayout, LayoutHelper.createFrame(-1, 48.0f, 51, 16.0f, 10.0f, 16.0f, 0.0f));
        SimpleTextView r133 = new SimpleTextView(r12);
        this.buttonTextView = r133;
        r133.setPadding(AndroidUtilities.dp(34.0f), 0, AndroidUtilities.dp(34.0f), 0);
        this.buttonTextView.setGravity(17);
        this.buttonTextView.setDrawablePadding(AndroidUtilities.dp(8.0f));
        this.buttonTextView.setTextColor(getThemedColor(Theme.key_featuredStickers_buttonText));
        this.buttonTextView.setTextSize(14);
        this.buttonTextView.setText(LocaleController.getString(R.string.Directions));
        this.buttonTextView.setLeftDrawable(R.drawable.filled_directions);
        if (Theme.isCurrentThemeMonet() == false) goto L5;
        this.buttonTextView.getLeftDrawable().setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chats_actionIcon), PorterDuff.Mode.SRC_IN));
    L5:
        this.buttonTextView.setTypeface(AndroidUtilities.bold());
        this.frameLayout.addView(this.buttonTextView, LayoutHelper.createFrame(-1, -1.0f));
        BadWayToMakeButtonRound.round(this.frameLayout);
        ScaleStateListAnimator.apply(this.frameLayout, 0.02f, 1.2f);
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(73.0f), TLObject.FLAG_30));
    }

    public void setOnButtonClick(View.OnClickListener r1) {
        this.frameLayout.setOnClickListener(r1);
    }
}
