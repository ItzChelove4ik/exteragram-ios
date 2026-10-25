package org.telegram.ui.Cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.TopicSeparator;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes6.dex */
public abstract class BotAskCell extends View {
    private final TopicSeparator askBotForumSeparator;
    private int backgroundHeight;
    private final BotAskCellDrawable drawable;
    private final Theme.ResourcesProvider resourcesProvider;
    private float viewTop;

    public BotAskCell(Context r2, int r3, Theme.ResourcesProvider r4) {
        super(r2);
        this.resourcesProvider = r4;
        this.drawable = new BotAskCellDrawable(r2, r3, r4);
        TopicSeparator r22 = new TopicSeparator(r3, this, r4, true);
        this.askBotForumSeparator = r22;
        r22.setText(_UrlKt.FRAGMENT_ENCODE_SET);
    }

    private void applyServiceShaderMatrix(int r3, float r4) {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        int r1 = this.backgroundHeight;
        if (r0 == null) goto L6;
        r0.applyServiceShaderMatrix(r3, r1, r4, this.viewTop);
        return;
    L6:
        Theme.applyServiceShaderMatrix(r3, r1, r4, this.viewTop);
    }

    public int getSideMenuWidth() {
        return 0;
    }

    @Override // android.view.View
    public void onDraw(Canvas r13) {
        super.onDraw(r13);
        int r0 = getSideMenuWidth();
        int r1 = ((getMeasuredWidth() - this.drawable.getBubbleWidth()) + r0) / 2;
        int r2 = AndroidUtilities.dp(34.0f);
        float r7 = r0;
        applyServiceShaderMatrix(getMeasuredWidth(), r7 / 2.0f);
        this.askBotForumSeparator.draw(r13, getWidth(), r7, 0.0f, 1.0f, 1.0f, false);
        BotAskCellDrawable r132 = this.drawable;
        r132.setBounds(r1, r2, r132.getBubbleWidth() + r1, this.drawable.getBubbleHeight() + r2);
        this.drawable.draw(r13);
    }

    @Override // android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(LayoutHelper.measureSpecExactly(View.MeasureSpec.getSize(r2)), LayoutHelper.measureSpecExactly(this.drawable.getBubbleHeight() + AndroidUtilities.dp(40.0f)));
    }

    public void setDialogId(long r1) {
        this.drawable.set(r1);
    }

    public void setVisiblePart(float r1, int r2) {
        this.backgroundHeight = r2;
        this.viewTop = r1;
    }
}
