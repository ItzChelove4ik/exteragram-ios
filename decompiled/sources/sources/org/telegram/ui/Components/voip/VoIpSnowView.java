package org.telegram.ui.Components.voip;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import org.telegram.messenger.LiteMode;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.SnowflakesEffect;

/* loaded from: classes7.dex */
public class VoIpSnowView extends View {
    private boolean isPaused;
    private SnowflakesEffect snowflakesEffect;

    public VoIpSnowView(Context r2) {
        super(r2);
        if (LiteMode.isEnabled(512) == true) goto L5;
        return;
    L5:
        if (Theme.getEventType() != 0) goto L9;
        this.snowflakesEffect = new SnowflakesEffect(0);
        return;
    }

    @Override // android.view.View
    public void onDraw(Canvas r2) {
        if (this.isPaused == true) goto L10;
        SnowflakesEffect r0 = this.snowflakesEffect;
        if (r0 == null) goto L9;
        r0.onDraw(this, r2);
        return;
    L9:
        return;
    }

    public void setState(boolean r1) {
        this.isPaused = r1;
        invalidate();
    }
}
