package org.telegram.ui.Components.blur3;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import org.telegram.ui.Components.blur3.capture.IBlur3Capture;
import org.telegram.ui.Components.blur3.capture.IBlur3Hash;
import org.telegram.ui.Components.chat.ViewPositionWatcher;

/* loaded from: classes7.dex */
public class ViewGroupPartRenderer implements IBlur3Capture {
    public boolean ignoreBlurCap;
    private final ViewGroup listView;
    private final DrawChildMethod listViewDrawChildMethod;
    private final ViewGroup listViewParent;
    private final RectF savedPos;
    private final PointF tmpDrawListViewPointF;
    private final RectF tmpDrawListViewRectF;

    public interface DrawChildMethod {
        boolean drawChild(Canvas r1, View r2, long r3);
    }

    public ViewGroupPartRenderer(ViewGroup r2, ViewGroup r3, DrawChildMethod r4) {
        this.tmpDrawListViewRectF = new RectF();
        this.tmpDrawListViewPointF = new PointF();
        this.savedPos = new RectF();
        this.listView = r2;
        this.listViewDrawChildMethod = r4;
        this.listViewParent = r3;
    }

    @Override // org.telegram.ui.Components.blur3.capture.IBlur3Capture
    public void capture(Canvas r7, RectF r8) {
        long r0 = SystemClock.uptimeMillis();
        if (ViewPositionWatcher.computeCoordinatesInParent(this.listView, this.listViewParent, this.tmpDrawListViewPointF) == true) goto L5;
        return;
    L5:
        r7.save();
        r7.clipRect(r8);
        PointF r2 = this.tmpDrawListViewPointF;
        r7.translate(r2.x, r2.y);
        ViewParent r22 = this.listView;
        if ((r22 instanceof IBlur3Capture) == true) goto L8;
    L10:
        int r23 = 0;
    L12:
        if (r23 >= this.listView.getChildCount()) goto L21;
        View r3 = this.listView.getChildAt(r23);
        if (ViewPositionWatcher.computeRectInParent(r3, this.listViewParent, this.tmpDrawListViewRectF) == false) goto L20;
        if (this.tmpDrawListViewRectF.intersect(r8) == false) goto L20;
        this.listViewDrawChildMethod.drawChild(r7, r3, r0);
    L20:
        r23 = r23 + 1;
    L21:
        r7.restore();
        return;
    L8:
        if (this.ignoreBlurCap == true) goto L10;
        this.savedPos.set(r8);
        PointF r02 = this.tmpDrawListViewPointF;
        r8.offset(-r02.x, -r02.y);
        ((IBlur3Capture) r22).capture(r7, r8);
        r8.set(this.savedPos);
        goto L21
    }

    @Override // org.telegram.ui.Components.blur3.capture.IBlur3Capture
    public void captureCalculateHash(IBlur3Hash r4, RectF r5) {
        if (ViewPositionWatcher.computeCoordinatesInParent(this.listView, this.listViewParent, this.tmpDrawListViewPointF) == true) goto L7;
        r4.unsupported();
        return;
    L7:
        if ((this.listView instanceof IBlur3Capture) == true) goto L9;
    L12:
        r4.unsupported();
        return;
    L9:
        if (this.ignoreBlurCap == true) goto L12;
        r4.addF(this.tmpDrawListViewPointF.x);
        r4.addF(this.tmpDrawListViewPointF.y);
        IBlur3Capture r0 = (IBlur3Capture) this.listView;
        this.savedPos.set(r5);
        PointF r1 = this.tmpDrawListViewPointF;
        r5.offset(-r1.x, -r1.y);
        r0.captureCalculateHash(r4, r5);
        r5.set(this.savedPos);
    }
}
