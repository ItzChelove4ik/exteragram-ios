package org.telegram.ui.Components;

import android.graphics.drawable.Drawable;
import org.telegram.messenger.utils.Choreographer60FpsContent;

/* loaded from: classes3.dex */
public abstract class StatusDrawable extends Drawable {
    public StatusDrawable() {
    }

    public void invalidateLimited() {
        Choreographer60FpsContent.getInstance().postInvalidateDrawable30fps(this);
    }

    public abstract void setColor(int r1);

    public abstract void setIsChat(boolean r1);

    public abstract void start();

    public abstract void stop();
}
