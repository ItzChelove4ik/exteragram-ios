package org.telegram.ui.Components.poll;

import android.graphics.Canvas;
import android.view.View;
import org.telegram.messenger.ImageReceiver;

/* loaded from: classes3.dex */
public abstract class PollAttachedMedia {
    protected final ImageReceiver imageReceiver;

    public PollAttachedMedia() {
        this.imageReceiver = new ImageReceiver();
    }

    public void attach(View r2) {
        this.imageReceiver.setParentView(r2);
        this.imageReceiver.onAttachedToWindow();
    }

    public void detach() {
        this.imageReceiver.onDetachedFromWindow();
    }

    public abstract void draw(Canvas r1, int r2, int r3);
}
