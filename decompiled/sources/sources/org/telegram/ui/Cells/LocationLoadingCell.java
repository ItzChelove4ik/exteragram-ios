package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadialProgressView;

/* loaded from: classes6.dex */
public class LocationLoadingCell extends FrameLayout {
    private ImageView imageView;
    private RadialProgressView progressBar;
    private final Theme.ResourcesProvider resourcesProvider;
    private TextView textView;

    public LocationLoadingCell(Context r10, Theme.ResourcesProvider r11) {
        super(r10);
        this.resourcesProvider = r11;
        RadialProgressView r0 = new RadialProgressView(r10, r11);
        this.progressBar = r0;
        addView(r0, LayoutHelper.createFrame(-2, -2, 17));
        ImageView r112 = new ImageView(r10);
        this.imageView = r112;
        r112.setImageResource(R.drawable.location_empty);
        this.imageView.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_dialogEmptyImage), PorterDuff.Mode.MULTIPLY));
        addView(this.imageView, LayoutHelper.createFrame(-2, -2.0f, 17, 0.0f, 0.0f, 0.0f, 24.0f));
        TextView r113 = new TextView(r10);
        this.textView = r113;
        r113.setTextColor(getThemedColor(Theme.key_dialogEmptyText));
        this.textView.setGravity(17);
        this.textView.setTypeface(AndroidUtilities.bold());
        this.textView.setTextSize(1, 17.0f);
        this.textView.setText(LocaleController.getString(R.string.NoPlacesFound));
        addView(this.textView, LayoutHelper.createFrame(-2, -2.0f, 17, 0.0f, 34.0f, 0.0f, 0.0f));
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r3, int r4) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) (AndroidUtilities.dp(56.0f) * 2.5f), TLObject.FLAG_30));
    }

    public void setLoading(boolean r5) {
        RadialProgressView r0 = this.progressBar;
        int r1 = 4;
        if (r5 == false) goto L5;
        int r3 = 0;
    L6:
        r0.setVisibility(r3);
        TextView r02 = this.textView;
        if (r5 == false) goto L9;
        int r32 = 4;
    L10:
        r02.setVisibility(r32);
        ImageView r4 = this.imageView;
        if (r5 == true) goto L14;
        r1 = 0;
    L14:
        r4.setVisibility(r1);
        return;
    L9:
        r32 = 0;
        goto L10
    L5:
        r3 = 4;
        goto L6
    }
}
