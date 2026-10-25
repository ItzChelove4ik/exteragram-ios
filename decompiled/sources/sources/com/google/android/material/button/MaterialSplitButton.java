package com.google.android.material.button;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.core.view.ViewCompat;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.material.R;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

/* loaded from: classes5.dex */
public class MaterialSplitButton extends MaterialButtonGroup {
    private static final int DEF_STYLE_RES = 0;
    private static final int REQUIRED_BUTTON_COUNT = 2;

    public static /* synthetic */ void $r8$lambda$0AaOSuB0iM3T9__daCv7yo8umdw(MaterialSplitButton r0, MaterialButton r1, MaterialButton r2, boolean r3) {
        Resources r02 = r0.getResources();
        if (r3 == false) goto L5;
        int r22 = R.string.mtrl_button_expanded_content_description;
    L6:
        ViewCompat.setStateDescription(r1, r02.getString(r22));
        return;
    L5:
        r22 = R.string.mtrl_button_collapsed_content_description;
        goto L6
    }

    static {
        DEF_STYLE_RES = R.style.Widget_Material3_MaterialSplitButton;
    }

    public MaterialSplitButton(Context r2) {
        this(r2, null);
    }

    public MaterialSplitButton(Context r2, AttributeSet r3) {
        this(r2, r3, R.attr.materialSplitButtonStyle);
    }

    public MaterialSplitButton(Context r2, AttributeSet r3, int r4) {
        super(MaterialThemeOverlay.wrap(r2, r3, r4, DEF_STYLE_RES), r3, r4);
    }

    @Override // com.google.android.material.button.MaterialButtonGroup, android.view.ViewGroup
    public void addView(View r3, int r4, ViewGroup.LayoutParams r5) {
        if ((r3 instanceof MaterialButton) == true) goto L5;
        f$$ExternalSyntheticBUOutline1.m("MaterialSplitButton can only hold MaterialButtons.");
        return;
    L5:
        if (getChildCount() > 2) goto L14;
        MaterialButton r0 = (MaterialButton) r3;
        super.addView(r3, r4, r5);
        if (indexOfChild(r3) != 1) goto L18;
        r0.setCheckable(true);
        r0.setA11yClassName(Button.class.getName());
        Resources r32 = getResources();
        if (r0.isChecked() == false) goto L11;
        int r42 = R.string.mtrl_button_expanded_content_description;
    L12:
        ViewCompat.setStateDescription(r0, r32.getString(r42));
        r0.addOnCheckedChangeListener(new MaterialSplitButton$$ExternalSyntheticLambda0(this, r0));
        return;
    L11:
        r42 = R.string.mtrl_button_collapsed_content_description;
        goto L12
    L18:
        return;
    L14:
        f$$ExternalSyntheticBUOutline1.m("MaterialSplitButton can only hold two MaterialButtons.");
    }
}
