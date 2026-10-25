package org.telegram.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.view.TextureView;
import android.widget.FrameLayout;
import org.telegram.messenger.ImageReceiver;

/* loaded from: classes6.dex */
public class TextureViewContainer extends FrameLayout {
    boolean firstFrameRendered;
    ImageReceiver imageReceiver;
    TextureView textureView;

    public TextureViewContainer(Context r2) {
        super(r2);
        this.imageReceiver = new ImageReceiver(this);
        TextureView r0 = new TextureView(r2);
        this.textureView = r0;
        addView(r0);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r5) {
        if (this.firstFrameRendered == true) goto L5;
        this.imageReceiver.setImageCoords(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
        this.imageReceiver.draw(r5);
    L5:
        super.dispatchDraw(r5);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.imageReceiver.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.imageReceiver.onDetachedFromWindow();
    }
}
