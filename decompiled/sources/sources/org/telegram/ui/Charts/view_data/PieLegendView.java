package org.telegram.ui.Charts.view_data;

import android.content.Context;
import android.graphics.Typeface;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes6.dex */
public class PieLegendView extends LegendSignatureView {
    TextView signature;
    TextView value;

    public PieLegendView(Context r5) {
        super(r5);
        LinearLayout r52 = new LinearLayout(getContext());
        r52.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(2.0f));
        TextView r0 = new TextView(getContext());
        this.signature = r0;
        r52.addView(r0);
        ViewGroup.LayoutParams r02 = this.signature.getLayoutParams();
        r02.width = AndroidUtilities.dp(96.0f);
        TextView r03 = new TextView(getContext());
        this.value = r03;
        r52.addView(r03);
        addView(r52);
        this.value.setTypeface(Typeface.create("sans-serif-medium", 0));
        setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f));
        this.chevron.setVisibility(8);
        this.zoomEnabled = false;
    }

    @Override // org.telegram.ui.Charts.view_data.LegendSignatureView
    public void recolor() {
        if (this.signature != null) goto L5;
        return;
    L5:
        super.recolor();
        this.signature.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
    }

    public void setData(String r2, int r3, int r4) {
        this.signature.setText(r2);
        this.value.setText(Integer.toString(r3));
        this.value.setTextColor(r4);
    }

    @Override // org.telegram.ui.Charts.view_data.LegendSignatureView
    public void setSize(int r1) {
    }
}
