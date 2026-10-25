package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.GestureDetectorCompat;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.internal.cast.zzgp;
import com.google.android.gms.internal.cast.zzgy;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class zzh extends ViewGroup {
    Animator zza;
    private final int[] zzb;
    private final Rect zzc;
    private final Rect zzd;
    private final OuterHighlightDrawable zze;
    private final InnerZoneDrawable zzf;
    private View zzg;
    private final zzi zzh;
    private final GestureDetectorCompat zzi;
    private GestureDetectorCompat zzj;
    private zzg zzk;
    private boolean zzl;
    private HelpTextView zzm;

    public zzh(Context r4) {
        super(r4);
        this.zzb = new int[2];
        this.zzc = new Rect();
        this.zzd = new Rect();
        setId(R.id.cast_featurehighlight_view);
        setWillNotDraw(false);
        InnerZoneDrawable r1 = new InnerZoneDrawable(r4);
        this.zzf = r1;
        r1.setCallback(this);
        OuterHighlightDrawable r12 = new OuterHighlightDrawable(r4);
        this.zze = r12;
        r12.setCallback(this);
        this.zzh = new zzi(this);
        GestureDetectorCompat r2 = new GestureDetectorCompat(r4, new zza(this));
        this.zzi = r2;
        r2.setIsLongpressEnabled(false);
        setVisibility(8);
    }

    private final void zzo(Animator r2) {
        Animator r0 = this.zza;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        this.zza = r2;
        r2.start();
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams r1) {
        return r1 instanceof ViewGroup.MarginLayoutParams;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet r2) {
        return new ViewGroup.MarginLayoutParams(getContext(), r2);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams r1) {
        return new ViewGroup.MarginLayoutParams(r1);
    }

    @Override // android.view.View
    public final void onDraw(Canvas r10) {
        r10.save();
        OuterHighlightDrawable r0 = this.zze;
        r0.draw(r10);
        this.zzf.draw(r10);
        View r1 = this.zzg;
        if (r1 != null) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Neither target view nor drawable was set");
        return;
    L5:
        if (r1.getParent() == null) goto L18;
        Bitmap r12 = Bitmap.createBitmap(this.zzg.getWidth(), this.zzg.getHeight(), Bitmap.Config.ARGB_8888);
        this.zzg.draw(new Canvas(r12));
        int r02 = r0.zza();
        int r2 = Color.red(r02);
        int r3 = Color.green(r02);
        int r03 = Color.blue(r02);
        int r5 = 0;
    L8:
        if (r5 >= r12.getHeight()) goto L17;
        int r6 = 0;
    L11:
        if (r6 >= r12.getWidth()) goto L16;
        int r7 = r12.getPixel(r6, r5);
        if (Color.alpha(r7) == 0) goto L15;
        r12.setPixel(r6, r5, Color.argb(Color.alpha(r7), r2, r3, r03));
    L15:
        r6 = r6 + 1;
        goto L11
    L16:
        r5 = r5 + 1;
        goto L8
    L17:
        Rect r9 = this.zzc;
        r10.drawBitmap(r12, r9.left, r9.top, null);
    L18:
        r10.restore();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean r6, int r7, int r8, int r9, int r10) {
        View r62 = this.zzg;
        if (r62 != null) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Target view must be set before layout");
        return;
    L5:
        if (r62.getParent() == null) goto L7;
        int[] r63 = this.zzb;
        View r2 = this.zzg;
        getLocationInWindow(r63);
        int r3 = r63[0];
        int r4 = r63[1];
        r2.getLocationInWindow(r63);
        r63[0] = r63[0] - r3;
        r63[1] = r63[1] - r4;
    L7:
        Rect r64 = this.zzc;
        int[] r22 = this.zzb;
        int r0 = r22[0];
        r64.set(r0, r22[1], this.zzg.getWidth() + r0, r22[1] + this.zzg.getHeight());
        Rect r02 = this.zzd;
        r02.set(r7, r8, r9, r10);
        this.zze.setBounds(r02);
        this.zzf.setBounds(r02);
        this.zzh.zza(r64, r02);
    }

    @Override // android.view.View
    public final void onMeasure(int r3, int r4) {
        int r0 = View.MeasureSpec.getSize(r3);
        int r1 = View.MeasureSpec.getSize(r4);
        setMeasuredDimension(View.resolveSize(r0, r3), View.resolveSize(r1, r4));
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent r4) {
        int r0 = r4.getActionMasked();
        if (r0 != 0) goto L6;
        this.zzl = this.zzc.contains((int) r4.getX(), (int) r4.getY());
        r0 = 0;
    L6:
        if (this.zzl == false) goto L15;
        GestureDetectorCompat r1 = this.zzj;
        if (r1 == null) goto L13;
        r1.onTouchEvent(r4);
        if (r0 != 1) goto L13;
        r4 = MotionEvent.obtain(r4);
        r4.setAction(3);
    L13:
        if (this.zzg.getParent() == null) goto L16;
        this.zzg.onTouchEvent(r4);
    L16:
        return true;
    L15:
        this.zzi.onTouchEvent(r4);
        goto L16
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable r2) {
        if (super.verifyDrawable(r2) == false) goto L5;
        return true;
    L5:
        if (r2 != this.zze) goto L7;
        return true;
    L7:
        if (r2 == this.zzf) goto L15;
        if (r2 == null) goto L16;
        return false;
    L16:
        return true;
    L15:
        return true;
    }

    public final void zza(View r1, View r2, boolean r3, zzg r4) {
        this.zzg = r1;
        this.zzk = r4;
        zzb r22 = new zzb(this, r1, true, r4);
        GestureDetectorCompat r12 = new GestureDetectorCompat(getContext(), r22);
        this.zzj = r12;
        r12.setIsLongpressEnabled(false);
        setVisibility(4);
    }

    public final void zzb(Runnable r2) {
        addOnLayoutChangeListener(new zzc(this, null));
    }

    public final void zzc() {
        if (this.zzg == null) goto L6;
        setVisibility(0);
        ObjectAnimator r1 = ObjectAnimator.ofFloat(this.zzm.asView(), "alpha", new float[]{0.0f, 1.0f}).setDuration(350);
        r1.setInterpolator(zzgy.zza());
        Rect r3 = this.zzc;
        OuterHighlightDrawable r7 = this.zze;
        float r8 = r3.exactCenterX() - r7.zzd();
        float r32 = r3.exactCenterY() - r7.zze();
        InnerZoneDrawable r9 = this.zzf;
        Animator r33 = r7.zzg(r8, r32);
        ObjectAnimator r4 = ObjectAnimator.ofPropertyValuesHolder(r9, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("scale", new float[]{0.0f, 1.0f}), PropertyValuesHolder.ofInt("alpha", new int[]{0, 255})});
        r4.setInterpolator(zzgy.zza());
        Animator r42 = r4.setDuration(350);
        AnimatorSet r5 = new AnimatorSet();
        r5.playTogether(new Animator[]{r1, r33, r42});
        r5.addListener(new zzd(this));
        zzo(r5);
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("Target view must be set before animation");
    }

    public final void zzd(Runnable r14) {
        ObjectAnimator r0 = ObjectAnimator.ofFloat(this.zzm.asView(), "alpha", new float[]{0.0f}).setDuration(200);
        r0.setInterpolator(zzgy.zzb());
        OuterHighlightDrawable r2 = this.zze;
        Rect r8 = this.zzc;
        float r9 = r8.exactCenterX() - r2.zzd();
        float r82 = r8.exactCenterY() - r2.zze();
        PropertyValuesHolder r10 = PropertyValuesHolder.ofFloat("scale", new float[]{0.0f});
        PropertyValuesHolder r5 = PropertyValuesHolder.ofInt("alpha", new int[]{0});
        ObjectAnimator r22 = ObjectAnimator.ofPropertyValuesHolder(r2, new PropertyValuesHolder[]{r10, PropertyValuesHolder.ofFloat("translationX", new float[]{0.0f, r9}), PropertyValuesHolder.ofFloat("translationY", new float[]{0.0f, r82}), r5});
        r22.setInterpolator(zzgy.zzb());
        Animator r23 = r22.setDuration(200);
        Animator r4 = this.zzf.zzb();
        AnimatorSet r52 = new AnimatorSet();
        r52.playTogether(new Animator[]{r0, r23, r4});
        r52.addListener(new zzf(this, r14));
        zzo(r52);
    }

    public final void zze(Runnable r9) {
        ObjectAnimator r0 = ObjectAnimator.ofFloat(this.zzm.asView(), "alpha", new float[]{0.0f}).setDuration(200);
        r0.setInterpolator(zzgy.zzb());
        ObjectAnimator r2 = ObjectAnimator.ofPropertyValuesHolder(this.zze, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("scale", new float[]{1.125f}), PropertyValuesHolder.ofInt("alpha", new int[]{0})});
        r2.setInterpolator(zzgy.zzb());
        Animator r22 = r2.setDuration(200);
        Animator r4 = this.zzf.zzb();
        AnimatorSet r5 = new AnimatorSet();
        r5.playTogether(new Animator[]{r0, r22, r4});
        r5.addListener(new zze(this, r9));
        zzo(r5);
    }

    public final void zzf(int r1) {
        this.zze.zzb(r1);
    }

    public final View zzg() {
        return this.zzm.asView();
    }

    public final OuterHighlightDrawable zzh() {
        return this.zze;
    }

    public final InnerZoneDrawable zzi() {
        return this.zzf;
    }

    public final /* synthetic */ boolean zzj(float r1, float r2) {
        return this.zzd.contains(Math.round(r1), Math.round(r2));
    }

    public final /* synthetic */ Animator zzk() {
        AnimatorSet r0 = new AnimatorSet();
        InnerZoneDrawable r8 = this.zzf;
        ObjectAnimator r2 = ObjectAnimator.ofFloat(r8, "scale", new float[]{1.0f, 1.1f}).setDuration(500);
        ObjectAnimator r3 = ObjectAnimator.ofFloat(r8, "scale", new float[]{1.1f, 1.0f}).setDuration(500);
        ObjectAnimator r82 = ObjectAnimator.ofPropertyValuesHolder(r8, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("pulseScale", new float[]{1.1f, 2.0f}), PropertyValuesHolder.ofFloat("pulseAlpha", new float[]{1.0f, 0.0f})}).setDuration(500);
        r0.play(r2);
        r0.play(r3).with(r82).after(r2);
        r0.setInterpolator(zzgy.zzc());
        r0.setStartDelay(500);
        zzgp.zzb(r0, -1, null);
        return r0;
    }

    public final /* synthetic */ OuterHighlightDrawable zzl() {
        return this.zze;
    }

    public final /* synthetic */ zzg zzm() {
        return this.zzk;
    }

    public final void zzn(HelpTextView r2) {
        r2.getClass();
        this.zzm = r2;
        addView(r2.asView(), 0);
    }
}
