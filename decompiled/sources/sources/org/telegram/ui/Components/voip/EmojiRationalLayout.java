package org.telegram.ui.Components.voip;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.widget.LinearLayout;
import org.telegram.messenger.AndroidUtilities;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class EmojiRationalLayout extends LinearLayout {
    private final VoIPBackgroundProvider backgroundProvider;
    private final RectF bgRect;

    public EmojiRationalLayout(Context r1, VoIPBackgroundProvider r2) {
        super(r1);
        this.bgRect = new RectF();
        this.backgroundProvider = r2;
        r2.attach(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r5) {
        this.bgRect.set(0.0f, 0.0f, getWidth(), getHeight());
        this.backgroundProvider.setDarkTranslation(getX(), getY());
        r5.drawRoundRect(this.bgRect, AndroidUtilities.dp(20.0f), AndroidUtilities.dp(20.0f), this.backgroundProvider.getDarkPaint());
        super.dispatchDraw(r5);
    }
}
