package com.google.android.material.transition.platform;

import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.transition.Transition;
import android.view.View;
import android.view.Window;
import androidx.core.graphics.BlendModeColorFilterCompat;
import androidx.core.graphics.BlendModeCompat;
import com.google.android.material.R;
import com.google.android.material.internal.ContextUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
public class MaterialContainerTransformSharedElementCallback extends SharedElementCallback {
    private static WeakReference<View> capturedSharedElement;
    private boolean entering;
    private Rect returnEndBounds;
    private ShapeProvider shapeProvider;
    private boolean sharedElementReenterTransitionEnabled;
    private boolean transparentWindowBackgroundEnabled;

    /* renamed from: com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback$1, reason: invalid class name */
    public class AnonymousClass1 extends TransitionListenerAdapter {
        final /* synthetic */ MaterialContainerTransformSharedElementCallback this$0;
        final /* synthetic */ Window val$window;

        public AnonymousClass1(MaterialContainerTransformSharedElementCallback r1, Window r2) {
            this.this$0 = r1;
            this.val$window = r2;
        }

        @Override // com.google.android.material.transition.platform.TransitionListenerAdapter, android.transition.Transition.TransitionListener
        public void onTransitionEnd(Transition r1) {
            MaterialContainerTransformSharedElementCallback.access$100(this.val$window);
        }

        @Override // com.google.android.material.transition.platform.TransitionListenerAdapter, android.transition.Transition.TransitionListener
        public void onTransitionStart(Transition r1) {
            MaterialContainerTransformSharedElementCallback.access$000(this.val$window);
        }
    }

    /* renamed from: com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback$2, reason: invalid class name */
    public class AnonymousClass2 extends TransitionListenerAdapter {
        final /* synthetic */ MaterialContainerTransformSharedElementCallback this$0;
        final /* synthetic */ Activity val$activity;

        public AnonymousClass2(MaterialContainerTransformSharedElementCallback r1, Activity r2) {
            this.this$0 = r1;
            this.val$activity = r2;
        }

        @Override // com.google.android.material.transition.platform.TransitionListenerAdapter, android.transition.Transition.TransitionListener
        public void onTransitionEnd(Transition r2) {
            if (MaterialContainerTransformSharedElementCallback.access$200() == null) goto L7;
            View r22 = (View) MaterialContainerTransformSharedElementCallback.access$200().get();
            if (r22 == null) goto L7;
            r22.setAlpha(1.0f);
            MaterialContainerTransformSharedElementCallback.access$202(null);
        L7:
            this.val$activity.finish();
            this.val$activity.overridePendingTransition(0, 0);
        }
    }

    /* renamed from: com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback$3, reason: invalid class name */
    public class AnonymousClass3 extends TransitionListenerAdapter {
        final /* synthetic */ MaterialContainerTransformSharedElementCallback this$0;
        final /* synthetic */ Window val$window;

        public AnonymousClass3(MaterialContainerTransformSharedElementCallback r1, Window r2) {
            this.this$0 = r1;
            this.val$window = r2;
        }

        @Override // com.google.android.material.transition.platform.TransitionListenerAdapter, android.transition.Transition.TransitionListener
        public void onTransitionStart(Transition r1) {
            MaterialContainerTransformSharedElementCallback.access$000(this.val$window);
        }
    }

    public interface ShapeProvider {
        ShapeAppearanceModel provideShape(View r1);
    }

    public static class ShapeableViewShapeProvider implements ShapeProvider {
        public ShapeableViewShapeProvider() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback.ShapeProvider
        public ShapeAppearanceModel provideShape(View r1) {
            if ((r1 instanceof Shapeable) == true) goto L5;
            return null;
        L5:
            return ((Shapeable) r1).getShapeAppearanceModel();
        }
    }

    public MaterialContainerTransformSharedElementCallback() {
        this.entering = true;
        this.transparentWindowBackgroundEnabled = true;
        this.sharedElementReenterTransitionEnabled = false;
        this.shapeProvider = new ShapeableViewShapeProvider();
    }

    public static /* synthetic */ void access$000(Window r0) {
        removeWindowBackground(r0);
    }

    public static /* synthetic */ void access$100(Window r0) {
        restoreWindowBackground(r0);
    }

    public static /* synthetic */ WeakReference access$200() {
        return capturedSharedElement;
    }

    public static /* synthetic */ WeakReference access$202(WeakReference r0) {
        capturedSharedElement = r0;
        return r0;
    }

    private static Drawable getWindowBackground(Window r0) {
        return r0.getDecorView().getBackground();
    }

    private static void removeWindowBackground(Window r2) {
        Drawable r22 = getWindowBackground(r2);
        if (r22 != null) goto L5;
        return;
    L5:
        r22.mutate().setColorFilter(BlendModeColorFilterCompat.createBlendModeColorFilterCompat(0, BlendModeCompat.CLEAR));
    }

    private static void restoreWindowBackground(Window r0) {
        Drawable r02 = getWindowBackground(r0);
        if (r02 != null) goto L5;
        return;
    L5:
        r02.mutate().clearColorFilter();
    }

    private void setUpEnterTransform(Window r3) {
        Transition r0 = r3.getSharedElementEnterTransition();
        if ((r0 instanceof MaterialContainerTransform) == false) goto L11;
        MaterialContainerTransform r02 = (MaterialContainerTransform) r0;
        if (this.sharedElementReenterTransitionEnabled == true) goto L8;
        r3.setSharedElementReenterTransition(null);
    L8:
        if (this.transparentWindowBackgroundEnabled == false) goto L12;
        updateBackgroundFadeDuration(r3, r02);
        r02.addListener(new AnonymousClass1(this, r3));
        return;
    L12:
        return;
    }

    private void setUpReturnTransform(Activity r3, Window r4) {
        Transition r0 = r4.getSharedElementReturnTransition();
        if ((r0 instanceof MaterialContainerTransform) == false) goto L8;
        MaterialContainerTransform r02 = (MaterialContainerTransform) r0;
        r02.setHoldAtEndEnabled(true);
        r02.addListener(new AnonymousClass2(this, r3));
        if (this.transparentWindowBackgroundEnabled == false) goto L9;
        updateBackgroundFadeDuration(r4, r02);
        r02.addListener(new AnonymousClass3(this, r4));
        return;
    L9:
        return;
    }

    private static void updateBackgroundFadeDuration(Window r4, MaterialContainerTransform r5) {
        if (r5.getDuration() < 0) goto L6;
        r4.setTransitionBackgroundFadeDuration(r5.getDuration());
        return;
    }

    public ShapeProvider getShapeProvider() {
        return this.shapeProvider;
    }

    public boolean isSharedElementReenterTransitionEnabled() {
        return this.sharedElementReenterTransitionEnabled;
    }

    public boolean isTransparentWindowBackgroundEnabled() {
        return this.transparentWindowBackgroundEnabled;
    }

    @Override // android.app.SharedElementCallback
    public Parcelable onCaptureSharedElementSnapshot(View r2, Matrix r3, RectF r4) {
        capturedSharedElement = new WeakReference(r2);
        return super.onCaptureSharedElementSnapshot(r2, r3, r4);
    }

    @Override // android.app.SharedElementCallback
    public View onCreateSnapshotView(Context r2, Parcelable r3) {
        View r22 = super.onCreateSnapshotView(r2, r3);
        if (r22 == null) goto L13;
        WeakReference<View> r32 = capturedSharedElement;
        if (r32 == null) goto L13;
        if (this.shapeProvider == null) goto L13;
        View r33 = r32.get();
        if (r33 == null) goto L13;
        ShapeAppearanceModel r1 = this.shapeProvider.provideShape(r33);
        if (r1 == null) goto L13;
        r22.setTag(R.id.mtrl_motion_snapshot_view, r1);
    L13:
        return r22;
    }

    @Override // android.app.SharedElementCallback
    public void onMapSharedElements(List<String> r2, Map<String, View> r3) {
        if (r2.isEmpty() == false) goto L5;
        return;
    L5:
        if (r3.isEmpty() == true) goto L17;
        View r22 = r3.get(r2.get(0));
        if (r22 == null) goto L18;
        Activity r23 = ContextUtils.getActivity(r22.getContext());
        if (r23 == null) goto L19;
        Window r32 = r23.getWindow();
        if (this.entering == false) goto L14;
        setUpEnterTransform(r32);
        return;
    L14:
        setUpReturnTransform(r23, r32);
        return;
    L19:
        return;
    L18:
        return;
    }

    @Override // android.app.SharedElementCallback
    public void onSharedElementEnd(List<String> r3, List<View> r4, List<View> r5) {
        if (r4.isEmpty() == true) goto L8;
        if ((r4.get(0).getTag(R.id.mtrl_motion_snapshot_view) instanceof View) == false) goto L8;
        r4.get(0).setTag(R.id.mtrl_motion_snapshot_view, null);
    L8:
        if (this.entering == false) goto L10;
    L12:
        this.entering = false;
        return;
    L10:
        if (r4.isEmpty() == true) goto L12;
        this.returnEndBounds = TransitionUtils.getRelativeBoundsRect(r4.get(0));
        goto L12
    }

    @Override // android.app.SharedElementCallback
    public void onSharedElementStart(List<String> r3, List<View> r4, List<View> r5) {
        if (r4.isEmpty() == true) goto L8;
        if (r5.isEmpty() == true) goto L8;
        r4.get(0).setTag(R.id.mtrl_motion_snapshot_view, r5.get(0));
    L8:
        if (this.entering == false) goto L10;
        return;
    L10:
        if (r4.isEmpty() == false) goto L12;
        return;
    L12:
        if (this.returnEndBounds == null) goto L17;
        View r32 = r4.get(0);
        r32.measure(View.MeasureSpec.makeMeasureSpec(this.returnEndBounds.width(), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(this.returnEndBounds.height(), TLObject.FLAG_30));
        Rect r2 = this.returnEndBounds;
        r32.layout(r2.left, r2.top, r2.right, r2.bottom);
        return;
    }

    public void setShapeProvider(ShapeProvider r1) {
        this.shapeProvider = r1;
    }

    public void setSharedElementReenterTransitionEnabled(boolean r1) {
        this.sharedElementReenterTransitionEnabled = r1;
    }

    public void setTransparentWindowBackgroundEnabled(boolean r1) {
        this.transparentWindowBackgroundEnabled = r1;
    }
}
