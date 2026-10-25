package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class LocationPoweredCell extends FrameLayout {
    private ImageView imageView;
    private final Theme.ResourcesProvider resourcesProvider;
    private TextView textView;
    private TextView textView2;

    public LocationPoweredCell(Context r9, Theme.ResourcesProvider r10) {
        super(r9);
        this.resourcesProvider = r10;
        LinearLayout r102 = new LinearLayout(r9);
        addView(r102, LayoutHelper.createFrame(-2, -2, 17));
        TextView r0 = new TextView(r9);
        this.textView = r0;
        r0.setTextSize(1, 16.0f);
        TextView r02 = this.textView;
        int r4 = Theme.key_windowBackgroundWhiteGrayText3;
        r02.setTextColor(getThemedColor(r4));
        this.textView.setText("Powered by");
        r102.addView(this.textView, LayoutHelper.createLinear(-2, -2));
        ImageView r03 = new ImageView(r9);
        this.imageView = r03;
        r03.setImageResource(R.drawable.foursquare);
        this.imageView.setColorFilter(new PorterDuffColorFilter(getThemedColor(r4), PorterDuff.Mode.MULTIPLY));
        this.imageView.setPadding(0, AndroidUtilities.dp(2.0f), 0, 0);
        r102.addView(this.imageView, LayoutHelper.createLinear(35, -2));
        TextView r04 = new TextView(r9);
        this.textView2 = r04;
        r04.setTextSize(1, 16.0f);
        this.textView2.setTextColor(getThemedColor(r4));
        this.textView2.setText("Foursquare");
        r102.addView(this.textView2, LayoutHelper.createLinear(-2, -2));
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(56.0f), TLObject.FLAG_30));
    }
}
