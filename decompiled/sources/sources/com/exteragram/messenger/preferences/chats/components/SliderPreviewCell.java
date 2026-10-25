package com.exteragram.messenger.preferences.chats.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.preferences.components.AltSeekbar;
import com.exteragram.messenger.preferences.components.CustomPreferenceCell;
import java.util.Objects;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes4.dex */
public class SliderPreviewCell extends FrameLayout implements CustomPreferenceCell {
    private final int cellId;
    private int lastWidth;
    private OnSliderChangedListener listener;
    private final MessagesPreviewCell messagesCell;
    public final AltSeekbar seekBar;

    public interface OnSliderChangedListener {
        void onChanged(float r1);
    }

    public static /* synthetic */ void $r8$lambda$ASsv_R1ynh012zWUYbci3KAxqjI(SliderPreviewCell r0, float r1) {
        r0.lambda$new$0(r1);
    }

    public SliderPreviewCell(INavigationLayout r12, Context r13, int r14, int r15, int r16, float r17, String r18, String r19, String r20, boolean r21) {
        super(r13);
        this.cellId = r14;
        setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        setWillNotDraw(false);
        AltSeekbar r1 = new AltSeekbar(r13, new SliderPreviewCell$$ExternalSyntheticLambda0(this), r15, r16, r18, r19, r20);
        this.seekBar = r1;
        addView(r1, LayoutHelper.createFrame(-1, -2.0f));
        if (r21 == false) goto L5;
        this.messagesCell = new MessagesPreviewCell(r13, r12, 1);
    L6:
        this.messagesCell.setImportantForAccessibility(4);
        View r122 = this.messagesCell;
        if (ExteraConfig.getNewSliderStyle() == false) goto L10;
        float r132 = 120.0f;
    L11:
        addView(r122, LayoutHelper.createFrame(-1, -2.0f, 51, 0.0f, r132, 0.0f, 0.0f));
        setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
        r1.setProgress(r17);
        return;
    L10:
        r132 = 112.0f;
        goto L11
    L5:
        this.messagesCell = new MessagesPreviewCell(r13, r12);
        goto L6
    }

    private /* synthetic */ void lambda$new$0(float r2) {
        OnSliderChangedListener r0 = this.listener;
        if (r0 == null) goto L5;
        r0.onChanged(r2);
    L5:
        invalidate();
    }

    @Override // com.exteragram.messenger.preferences.components.CustomPreferenceCell
    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof SliderPreviewCell) == false) goto L16;
        SliderPreviewCell r52 = (SliderPreviewCell) r5;
        if (this.cellId != r52.cellId) goto L16;
        if (Objects.equals(this.messagesCell, r52.messagesCell) == false) goto L16;
        if (Objects.equals(this.seekBar, r52.seekBar) == false) goto L16;
        if (this.lastWidth != r52.lastWidth) goto L16;
        return true;
    L16:
        return false;
    }

    public OnSliderChangedListener getListener() {
        return this.listener;
    }

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        this.lastWidth = -1;
        this.messagesCell.refreshMessages();
        this.seekBar.invalidate();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r1, int r2) {
        super.onMeasure(r1, r2);
        int r12 = View.MeasureSpec.getSize(r1);
        if (this.lastWidth == r12) goto L6;
        this.lastWidth = r12;
        return;
    }

    public SliderPreviewCell setListener(OnSliderChangedListener r1) {
        this.listener = r1;
        return this;
    }
}
