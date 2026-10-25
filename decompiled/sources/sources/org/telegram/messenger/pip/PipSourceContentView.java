package org.telegram.messenger.pip;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import org.telegram.messenger.pip.source.PipSourceHandlerState2;
import org.telegram.tgnet.TLObject;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes5.dex */
public class PipSourceContentView extends ViewGroup {
    private final PipSourceHandlerState2 state;

    /* renamed from: $r8$lambda$0lqZEg-MlnUrbhtSoo33RPFozJo, reason: not valid java name */
    public static /* synthetic */ void m5163$r8$lambda$0lqZEgMlnUrbhtSoo33RPFozJo(PipSourceContentView r0, Canvas r1) {
        r0.lambda$dispatchDraw$0(r1);
    }

    public PipSourceContentView(Context r1, PipSourceHandlerState2 r2) {
        super(r1);
        this.state = r2;
    }

    private /* synthetic */ void lambda$dispatchDraw$0(Canvas r1) {
        super.dispatchDraw(r1);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r3) {
        this.state.draw(r3, new PipSourceContentView$$ExternalSyntheticLambda0(this));
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
        int r22 = 0;
    L4:
        if (r22 >= getChildCount()) goto L6;
        View r32 = getChildAt(r22);
        Rect r42 = this.state.position;
        r32.layout(r42.left, r42.top, r42.right, r42.bottom);
        r22 = r22 + 1;
        goto L4
    }

    @Override // android.view.View
    public void onMeasure(int r4, int r5) {
        int r42 = View.MeasureSpec.getSize(r4);
        int r52 = View.MeasureSpec.getSize(r5);
        setMeasuredDimension(View.MeasureSpec.makeMeasureSpec(r42, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r52, TLObject.FLAG_30));
        this.state.updatePositionViewRect(r42, r52, ((PipActivityContentLayout) getParent()).isViewInPip());
        int r43 = 0;
    L4:
        if (r43 >= getChildCount()) goto L6;
        getChildAt(r43).measure(View.MeasureSpec.makeMeasureSpec(this.state.position.width(), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(this.state.position.height(), TLObject.FLAG_30));
        r43 = r43 + 1;
        goto L4
    }
}
