package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class RoundVideoPlayingDrawable extends Drawable {
    int alpha;
    public float colorProgress;
    private long lastUpdateTime;
    private Paint paint;
    private View parentView;
    private float progress1;
    private int progress1Direction;
    private float progress2;
    private int progress2Direction;
    private float progress3;
    private int progress3Direction;
    private Theme.ResourcesProvider resourcesProvider;
    private boolean started;
    public int timeColor;

    public RoundVideoPlayingDrawable(View r3, Theme.ResourcesProvider r4) {
        this.lastUpdateTime = 0;
        this.started = false;
        this.paint = new Paint(1);
        this.progress1 = 0.47f;
        this.progress2 = 0.0f;
        this.progress3 = 0.32f;
        this.progress1Direction = 1;
        this.progress2Direction = 1;
        this.progress3Direction = 1;
        this.alpha = 255;
        this.resourcesProvider = r4;
        this.parentView = r3;
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private void update() {
        long r0 = System.currentTimeMillis();
        long r2 = r0 - this.lastUpdateTime;
        this.lastUpdateTime = r0;
        if (r2 <= 50) goto L5;
        r2 = 50;
    L5:
        float r1 = r2;
        float r02 = this.progress1 + ((r1 / 300.0f) * this.progress1Direction);
        this.progress1 = r02;
        if (r02 <= 1.0f) goto L9;
        this.progress1Direction = -1;
        this.progress1 = 1.0f;
    L11:
        float r03 = this.progress2 + ((r1 / 310.0f) * this.progress2Direction);
        this.progress2 = r03;
        if (r03 <= 1.0f) goto L15;
        this.progress2Direction = -1;
        this.progress2 = 1.0f;
    L17:
        float r04 = this.progress3 + ((r1 / 320.0f) * this.progress3Direction);
        this.progress3 = r04;
        if (r04 <= 1.0f) goto L21;
        this.progress3Direction = -1;
        this.progress3 = 1.0f;
    L23:
        this.parentView.invalidate();
        return;
    L21:
        if (r04 >= 0.0f) goto L23;
        this.progress3Direction = 1;
        this.progress3 = 0.0f;
        goto L23
    L15:
        if (r03 >= 0.0f) goto L17;
        this.progress2Direction = 1;
        this.progress2 = 0.0f;
        goto L17
    L9:
        if (r02 >= 0.0f) goto L11;
        this.progress1Direction = 1;
        this.progress1 = 0.0f;
        goto L11
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r20) {
        this.paint.setColor(ColorUtils.blendARGB(getThemedColor(Theme.key_chat_serviceText), this.timeColor, this.colorProgress));
        int r1 = this.alpha;
        if (r1 == 255) goto L5;
        this.paint.setAlpha((int) (r1 * (r2.getAlpha() / 255.0f)));
    L5:
        int r12 = getBounds().left;
        int r2 = getBounds().top;
        int r3 = 0;
    L7:
        if (r3 >= 3) goto L10;
        r20.drawRect(AndroidUtilities.dp(2.0f) + r12, AndroidUtilities.dp((this.progress1 * 7.0f) + 2.0f) + r2, AndroidUtilities.dp(4.0f) + r12, AndroidUtilities.dp(10.0f) + r2, this.paint);
        r20.drawRect(AndroidUtilities.dp(5.0f) + r12, AndroidUtilities.dp((this.progress2 * 7.0f) + 2.0f) + r2, AndroidUtilities.dp(7.0f) + r12, AndroidUtilities.dp(10.0f) + r2, this.paint);
        r20.drawRect(AndroidUtilities.dp(8.0f) + r12, AndroidUtilities.dp((this.progress3 * 7.0f) + 2.0f) + r2, AndroidUtilities.dp(10.0f) + r12, AndroidUtilities.dp(10.0f) + r2, this.paint);
        r3 = r3 + 1;
        goto L7
    L10:
        if (this.started == false) goto L14;
        update();
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(12.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return AndroidUtilities.dp(12.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        this.alpha = r1;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }

    public void setResourcesProvider(Theme.ResourcesProvider r1) {
        this.resourcesProvider = r1;
    }

    public void start() {
        if (this.started == false) goto L5;
        return;
    L5:
        this.lastUpdateTime = System.currentTimeMillis();
        this.started = true;
        this.parentView.invalidate();
    }

    public void stop() {
        if (this.started == true) goto L5;
        return;
    L5:
        this.started = false;
    }
}
