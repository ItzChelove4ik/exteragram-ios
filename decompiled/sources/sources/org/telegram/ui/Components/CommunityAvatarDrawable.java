package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.messenger.utils.DrawableUtils;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class CommunityAvatarDrawable extends Drawable {
    private final Drawable drawable;
    private final Paint paint;
    private float radius;

    public CommunityAvatarDrawable(Context r3, float r4) {
        Paint r0 = new Paint(1);
        this.paint = r0;
        this.drawable = r3.getResources().getDrawable(R.drawable.msg_filled_menu_groups);
        r0.setColor(Theme.multAlpha(-16777216, 0.1552f));
        this.radius = r4;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r10) {
        float r2 = getBounds().left;
        float r3 = getBounds().top;
        float r4 = getBounds().right;
        float r5 = getBounds().bottom;
        float r6 = this.radius;
        r10.drawRoundRect(r2, r3, r4, r5, r6, r6, this.paint);
        DrawableUtils.setBounds(this.drawable, getBounds().exactCenterX(), getBounds().exactCenterY(), AndroidUtilities.dp(36.0f), AndroidUtilities.dp(36.0f), 17);
        this.drawable.draw(r10);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }
}
