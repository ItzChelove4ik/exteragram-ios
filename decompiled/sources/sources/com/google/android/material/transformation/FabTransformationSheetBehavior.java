package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.R;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.animation.Positioning;
import com.google.android.material.transformation.FabTransformationBehavior;
import java.util.HashMap;
import java.util.Map;

@Deprecated
/* loaded from: classes5.dex */
public class FabTransformationSheetBehavior extends FabTransformationBehavior {
    private Map<View, Integer> importantForAccessibilityMap;

    public FabTransformationSheetBehavior() {
    }

    public FabTransformationSheetBehavior(Context r1, AttributeSet r2) {
        super(r1, r2);
    }

    private void updateImportantForAccessibility(View r8, boolean r9) {
        ViewParent r0 = r8.getParent();
        if ((r0 instanceof CoordinatorLayout) == false) goto L38;
        CoordinatorLayout r02 = (CoordinatorLayout) r0;
        int r1 = r02.getChildCount();
        if (r9 == false) goto L8;
        this.importantForAccessibilityMap = new HashMap(r1);
    L8:
        int r3 = 0;
    L9:
        if (r3 >= r1) goto L27;
        View r4 = r02.getChildAt(r3);
        if ((r4.getLayoutParams() instanceof CoordinatorLayout.LayoutParams) == true) goto L13;
    L15:
        boolean r5 = false;
    L16:
        if (r4 == r8) goto L26;
        if (r5 == true) goto L26;
        Map<View, Integer> r52 = this.importantForAccessibilityMap;
        if (r9 == true) goto L25;
        if (r52 == null) goto L26;
        if (r52.containsKey(r4) == false) goto L26;
        r4.setImportantForAccessibility(this.importantForAccessibilityMap.get(r4).intValue());
        goto L26
    L25:
        r52.put(r4, Integer.valueOf(r4.getImportantForAccessibility()));
        r4.setImportantForAccessibility(4);
    L26:
        r3 = r3 + 1;
        goto L9
    L13:
        if ((((CoordinatorLayout.LayoutParams) r4.getLayoutParams()).getBehavior() instanceof FabTransformationScrimBehavior) == false) goto L15;
        r5 = true;
        goto L16
    L27:
        if (r9 == true) goto L37;
        this.importantForAccessibilityMap = null;
        return;
    L37:
        return;
    }

    @Override // com.google.android.material.transformation.FabTransformationBehavior
    public FabTransformationBehavior.FabTransformationSpec onCreateMotionSpec(Context r2, boolean r3) {
        if (r3 == false) goto L4;
        int r1 = R.animator.mtrl_fab_transformation_sheet_expand_spec;
    L5:
        FabTransformationBehavior.FabTransformationSpec r32 = new FabTransformationBehavior.FabTransformationSpec();
        r32.timings = MotionSpec.createFromResource(r2, r1);
        r32.positioning = new Positioning(17, 0.0f, 0.0f);
        return r32;
    L4:
        r1 = R.animator.mtrl_fab_transformation_sheet_collapse_spec;
        goto L5
    }

    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior, com.google.android.material.transformation.ExpandableBehavior
    public boolean onExpandedStateChange(View r1, View r2, boolean r3, boolean r4) {
        updateImportantForAccessibility(r2, r3);
        return super.onExpandedStateChange(r1, r2, r3, r4);
    }
}
