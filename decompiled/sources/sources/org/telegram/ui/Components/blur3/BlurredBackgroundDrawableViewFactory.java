package org.telegram.ui.Components.blur3;

import android.graphics.RectF;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import me.vkryl.core.reference.ReferenceList;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawableRenderNode;
import org.telegram.ui.Components.blur3.drawable.color.BlurredBackgroundColorProvider;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSource;
import org.telegram.ui.Components.chat.ViewPositionWatcher;
import org.telegram.utils.glass.GlassEngine;

/* loaded from: classes3.dex */
public class BlurredBackgroundDrawableViewFactory {
    private GlassEngine engine;
    private boolean isLiquidGlassEffectAllowed;
    private ReferenceList<BlurredBackgroundDrawable> linkedDrawables;
    private ReferenceList<View> linkedViews;
    private int outsetX;
    private int outsetY;
    private ViewGroup parent;
    private final BlurredBackgroundSource source;
    private boolean trackReattach;
    private ViewPositionWatcher viewPositionWatcher;

    public static /* synthetic */ void $r8$lambda$zQYw2Ior9IE1MD7jp9GiuMfrb8I(WeakReference r1, View r2, RectF r3) {
        BlurredBackgroundDrawable r12 = (BlurredBackgroundDrawable) r1.get();
        if (r12 != null) goto L5;
        return;
    L5:
        r12.setSourceOffset(r3.left, r3.top);
        r2.invalidate();
    }

    public BlurredBackgroundDrawableViewFactory(BlurredBackgroundSource r1) {
        this.source = r1;
    }

    public BlurredBackgroundDrawableViewFactory(ViewPositionWatcher r1, ViewGroup r2, BlurredBackgroundSource r3) {
        this(r3);
        setSourceRootView(r1, r2);
    }

    public BlurredBackgroundDrawable create() {
        return create(null);
    }

    public BlurredBackgroundDrawable create(View r2) {
        return create(r2, null);
    }

    public BlurredBackgroundDrawable create(View r2, BlurredBackgroundColorProvider r3) {
        return create(r2, r3, false);
    }

    public BlurredBackgroundDrawable create(View r8, BlurredBackgroundColorProvider r9, boolean r10) {
        BlurredBackgroundDrawable r0 = this.source.createDrawable();
        if (this.isLiquidGlassEffectAllowed == true) goto L5;
    L9:
        r0.setColorProvider(r9);
        r0.setOutset(this.outsetX, this.outsetY);
        ReferenceList<View> r92 = this.linkedViews;
        if (r92 == null) goto L13;
        if (r8 == null) goto L13;
        r92.add(r8);
    L13:
        GlassEngine r93 = this.engine;
        if (r93 == null) goto L18;
        if (r8 == null) goto L18;
        r93.registerDrawable(r8, r0);
    L18:
        if (this.viewPositionWatcher != null) goto L20;
    L23:
        ReferenceList<BlurredBackgroundDrawable> r7 = this.linkedDrawables;
        if (r7 == null) goto L26;
        r7.add(r0);
    L26:
        return r0;
    L20:
        if (this.parent == null) goto L23;
        if (r8 == null) goto L23;
        WeakReference r94 = new WeakReference(r0);
        this.viewPositionWatcher.subscribe(r8, this.parent, new BlurredBackgroundDrawableViewFactory$$ExternalSyntheticLambda0(r94), r10, this.trackReattach);
        goto L23
    L5:
        if (Build.VERSION.SDK_INT < 33) goto L9;
        if ((r0 instanceof BlurredBackgroundDrawableRenderNode) == false) goto L9;
        ((BlurredBackgroundDrawableRenderNode) r0).setLiquidGlassEffectAllowed();
        goto L9
    }

    public BlurredBackgroundDrawable create(View r2, boolean r3) {
        return create(r2, null, r3);
    }

    public void invalidateAllLinkedViews() {
        ReferenceList<View> r1 = this.linkedViews;
        if (r1 == null) goto L8;
        Iterator<View> r12 = r1.iterator();
    L6:
        if (r12.hasNext() == false) goto L10;
        r12.next().invalidate();
        goto L6
    L10:
        return;
    }

    public void setGlassEngine(GlassEngine r1) {
        this.engine = r1;
    }

    public void setLinkedDrawablesRef(ReferenceList<BlurredBackgroundDrawable> r1) {
        this.linkedDrawables = r1;
    }

    public void setLinkedViewsRef(ReferenceList<View> r1) {
        this.linkedViews = r1;
    }

    public void setLiquidGlassEffectAllowed(boolean r1) {
        this.isLiquidGlassEffectAllowed = r1;
    }

    public void setOutset(int r1) {
        setOutset(r1, r1);
    }

    public void setOutset(int r1, int r2) {
        this.outsetX = r1;
        this.outsetY = r2;
    }

    public void setSourceRootView(ViewPositionWatcher r1, ViewGroup r2) {
        this.viewPositionWatcher = r1;
        this.parent = r2;
    }

    public void setTrackReattach(boolean r1) {
        this.trackReattach = r1;
    }
}
