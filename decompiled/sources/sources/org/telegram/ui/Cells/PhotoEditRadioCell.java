package org.telegram.ui.Cells;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadioButton;

/* loaded from: classes6.dex */
public class PhotoEditRadioCell extends FrameLayout {
    private int currentColor;
    private int currentType;
    private TextView nameTextView;
    private View.OnClickListener onClickListener;
    private LinearLayout tintButtonsContainer;
    private final int[] tintHighlighsColors;
    private final int[] tintShadowColors;

    /* renamed from: $r8$lambda$8-8QKdeTu71tsFo9T8fkvTpsUrM, reason: not valid java name */
    public static /* synthetic */ void m6989$r8$lambda$88QKdeTu71tsFo9T8fkvTpsUrM(PhotoEditRadioCell r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public PhotoEditRadioCell(Context r10) {
        super(r10);
        this.tintShadowColors = new int[]{0, -45747, -753630, -13056, -8269183, -9321002, -16747844, -10080879};
        this.tintHighlighsColors = new int[]{0, -1076602, -1388894, -859780, -5968466, -7742235, -13726776, -3303195};
        TextView r0 = new TextView(r10);
        this.nameTextView = r0;
        r0.setGravity(5);
        this.nameTextView.setTextColor(-1);
        this.nameTextView.setTextSize(1, 12.0f);
        this.nameTextView.setMaxLines(1);
        this.nameTextView.setSingleLine(true);
        this.nameTextView.setEllipsize(TextUtils.TruncateAt.END);
        addView(this.nameTextView, LayoutHelper.createFrame(80, -2.0f, 19, 0.0f, 0.0f, 0.0f, 0.0f));
        LinearLayout r02 = new LinearLayout(r10);
        this.tintButtonsContainer = r02;
        r02.setOrientation(0);
        int r03 = 0;
    L4:
        if (r03 >= this.tintShadowColors.length) goto L6;
        RadioButton r3 = new RadioButton(r10);
        r3.setSize(AndroidUtilities.dp(20.0f));
        r3.setTag(Integer.valueOf(r03));
        this.tintButtonsContainer.addView(r3, LayoutHelper.createLinear(0, -1, 1.0f / this.tintShadowColors.length));
        r3.setOnClickListener(new PhotoEditRadioCell$$ExternalSyntheticLambda0(this));
        r03 = r03 + 1;
        goto L4
    L6:
        addView(this.tintButtonsContainer, LayoutHelper.createFrame(-1, 40.0f, 51, 96.0f, 0.0f, 24.0f, 0.0f));
    }

    private /* synthetic */ void lambda$new$0(View r2) {
        RadioButton r22 = (RadioButton) r2;
        if (this.currentType != 0) goto L5;
        this.currentColor = this.tintShadowColors[((Integer) r22.getTag()).intValue()];
    L6:
        updateSelectedTintButton(true);
        this.onClickListener.onClick(this);
        return;
    L5:
        this.currentColor = this.tintHighlighsColors[((Integer) r22.getTag()).intValue()];
        goto L6
    }

    private void updateSelectedTintButton(boolean r8) {
        int r0 = this.tintButtonsContainer.getChildCount();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L29;
        View r3 = this.tintButtonsContainer.getChildAt(r2);
        if ((r3 instanceof RadioButton) == false) goto L28;
        RadioButton r32 = (RadioButton) r3;
        int r4 = ((Integer) r32.getTag()).intValue();
        if (this.currentType != 0) goto L9;
        int r5 = this.tintShadowColors[r4];
    L11:
        if (this.currentColor != r5) goto L13;
        boolean r52 = true;
    L14:
        r32.setChecked(r52, r8);
        int r53 = -1;
        if (r4 != 0) goto L18;
        int r6 = -1;
    L21:
        if (r4 != 0) goto L24;
    L27:
        r32.setColor(r6, r53);
        goto L28
    L24:
        if (this.currentType != 0) goto L26;
        r53 = this.tintShadowColors[r4];
        goto L27
    L26:
        r53 = this.tintHighlighsColors[r4];
        goto L27
    L18:
        if (this.currentType != 0) goto L20;
        r6 = this.tintShadowColors[r4];
        goto L21
    L20:
        r6 = this.tintHighlighsColors[r4];
        goto L21
    L13:
        r52 = false;
        goto L14
    L9:
        r5 = this.tintHighlighsColors[r4];
    L28:
        r2 = r2 + 1;
        goto L3
    }

    public int getCurrentColor() {
        return this.currentColor;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(40.0f), TLObject.FLAG_30));
    }

    public void setIconAndTextAndValue(String r4, int r5, int r6) {
        this.currentType = r5;
        this.currentColor = r6;
        this.nameTextView.setText(r4.substring(0, 1).toUpperCase() + r4.substring(1).toLowerCase());
        updateSelectedTintButton(false);
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener r1) {
        this.onClickListener = r1;
    }
}
