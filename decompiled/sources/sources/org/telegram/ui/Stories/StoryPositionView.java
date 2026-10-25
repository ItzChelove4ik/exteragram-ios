package org.telegram.ui.Stories;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.SpannableStringBuilder;
import android.widget.FrameLayout;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Cells.DialogCell;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Stories.PeerStoriesView;

/* loaded from: classes7.dex */
public class StoryPositionView {
    Paint backgroundPaint;
    int lastHash;
    private final SpannableStringBuilder leftSpace;
    private final SpannableStringBuilder rightSpace;
    AnimatedTextView.AnimatedTextDrawable textDrawable;

    public StoryPositionView() {
        this.textDrawable = new AnimatedTextView.AnimatedTextDrawable(true, true, true);
        this.backgroundPaint = new Paint(1);
        this.textDrawable.setTextSize(AndroidUtilities.dp(13.0f));
        this.textDrawable.setTextColor(-1);
        this.textDrawable.setTypeface(AndroidUtilities.bold());
        this.backgroundPaint.setColor(ColorUtils.setAlphaComponent(-16777216, 58));
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        this.leftSpace = r0;
        r0.append(" ").setSpan(new DialogCell.FixedWidthSpan(AndroidUtilities.dp(1.0f)), 0, 1, 0);
        SpannableStringBuilder r02 = new SpannableStringBuilder();
        this.rightSpace = r02;
        r02.append(" ").setSpan(new DialogCell.FixedWidthSpan(AndroidUtilities.dp(1.0f)), 0, 1, 0);
    }

    public void draw(Canvas r5, float r6, int r7, int r8, FrameLayout r9, PeerStoriesView.PeerHeaderView r10) {
        int r92 = (r8 << 12) + r7;
        if (this.lastHash == r92) goto L5;
        this.lastHash = r92;
        SpannableStringBuilder r93 = new SpannableStringBuilder();
        r93.append(String.valueOf(r7 + 1)).append(this.leftSpace).append("/").append(this.rightSpace).append(String.valueOf(r8));
        this.textDrawable.setText(r93, false);
    L5:
        r5.save();
        float r72 = ((r10.getY() + r10.titleView.getTop()) + (this.textDrawable.getHeight() / 2.0f)) - 1.0f;
        r10.titleView.setRightPadding((int) this.textDrawable.getCurrentWidth());
        r5.translate(((((AndroidUtilities.dp(4.0f) + r10.getLeft()) + r10.titleView.getLeft()) + r10.titleView.getTextWidth()) + r10.titleView.getRightDrawableWidth()) - Utilities.clamp(((r10.titleView.getTextWidth() + r10.titleView.getRightDrawableWidth()) + r8) - r10.titleView.getWidth(), r8, 0), r72);
        float r73 = AndroidUtilities.dp(8.0f);
        float r82 = AndroidUtilities.dp(2.0f);
        AndroidUtilities.rectTmp.set(-r73, -r82, this.textDrawable.getCurrentWidth() + r73, this.textDrawable.getHeight() + r82);
        this.textDrawable.setAlpha((int) (r6 * 160.0f));
        this.textDrawable.draw(r5);
        r5.restore();
    }
}
