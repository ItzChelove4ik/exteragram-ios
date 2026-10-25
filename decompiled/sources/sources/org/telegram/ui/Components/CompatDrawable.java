package org.telegram.ui.Components;

import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;

/* loaded from: classes7.dex */
public abstract class CompatDrawable extends Drawable {
    public final Paint paint;

    /* renamed from: org.telegram.ui.Components.CompatDrawable$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnAttachStateChangeListener {
        final /* synthetic */ CompatDrawable this$0;

        public AnonymousClass1(CompatDrawable r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r1) {
            this.this$0.onAttachedToWindow();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r1) {
            this.this$0.onDetachedToWindow();
        }
    }

    public CompatDrawable(View r3) {
        this.paint = new Paint(1);
        if (r3 == null) goto L8;
        r3.addOnAttachStateChangeListener(new AnonymousClass1(this));
        if (r3.isAttachedToWindow() == false) goto L9;
        r3.post(new CompatDrawable$$ExternalSyntheticLambda0(this));
        return;
    L9:
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    public void onAttachedToWindow() {
    }

    public void onDetachedToWindow() {
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
        this.paint.setAlpha(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
        this.paint.setColorFilter(r1);
    }
}
