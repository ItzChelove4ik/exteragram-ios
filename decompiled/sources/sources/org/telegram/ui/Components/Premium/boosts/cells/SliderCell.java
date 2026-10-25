package org.telegram.ui.Components.Premium.boosts.cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import java.util.List;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.SlideChooseView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class SliderCell extends FrameLayout {
    protected View backgroundView;
    private final Theme.ResourcesProvider resourcesProvider;
    private final SlideChooseView slideChooseView;

    public SliderCell(Context r9, Theme.ResourcesProvider r10) {
        super(r9);
        this.resourcesProvider = r10;
        View r0 = new View(r9);
        this.backgroundView = r0;
        addView(r0, LayoutHelper.createLinear(-1, -1));
        this.backgroundView.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground, r10));
        SlideChooseView r02 = new SlideChooseView(r9, r10);
        this.slideChooseView = r02;
        addView(r02, LayoutHelper.createFrame(-1, -1.0f, 48, 0.0f, 0.0f, 0.0f, 0.0f));
        setBackground(Theme.getThemedDrawableByKey(getContext(), R.drawable.greydivider_top, Theme.key_windowBackgroundGrayShadow));
    }

    public void setCallBack(SlideChooseView.Callback r1) {
        this.slideChooseView.setCallback(r1);
    }

    public void setValues(List<Integer> r4, int r5) {
        String[] r0 = new String[r4.size()];
        int r1 = 0;
    L4:
        if (r1 >= r4.size()) goto L6;
        r0[r1] = String.valueOf(r4.get(r1));
        r1 = r1 + 1;
        goto L4
    L6:
        this.slideChooseView.setOptions(r5, r0);
    }
}
