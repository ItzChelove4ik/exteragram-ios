package com.google.android.gms.cast.framework.media.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.internal.Objects;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class CastSeekBar extends View {
    public zze zza;
    public zzb zzb;
    public List zzc;
    public zzd zzd;
    private boolean zze;
    private Integer zzf;
    private final float zzg;
    private final float zzh;
    private final float zzi;
    private final float zzj;
    private final float zzk;
    private final Paint zzl;
    private final int zzm;
    private final int zzn;
    private final int zzo;
    private final int zzp;
    private int[] zzq;
    private Point zzr;
    private Runnable zzs;

    public CastSeekBar(Context r2) {
        this(r2, null);
    }

    public CastSeekBar(Context r2, AttributeSet r3) {
        this(r2, r3, 0);
    }

    public CastSeekBar(Context r5, AttributeSet r6, int r7) {
        super(r5, r6, r7);
        this.zzc = new ArrayList();
        setAccessibilityDelegate(new zzf(this, null));
        Paint r62 = new Paint(1);
        this.zzl = r62;
        r62.setStyle(Paint.Style.FILL);
        this.zzg = r5.getResources().getDimension(R.dimen.cast_seek_bar_minimum_width);
        this.zzh = r5.getResources().getDimension(R.dimen.cast_seek_bar_minimum_height);
        this.zzi = r5.getResources().getDimension(R.dimen.cast_seek_bar_progress_height) / 2.0f;
        this.zzj = r5.getResources().getDimension(R.dimen.cast_seek_bar_thumb_size) / 2.0f;
        this.zzk = r5.getResources().getDimension(R.dimen.cast_seek_bar_ad_break_minimum_width);
        zze r63 = new zze();
        this.zza = r63;
        r63.zzb = 1;
        TypedArray r64 = r5.obtainStyledAttributes(null, R.styleable.CastExpandedController, R.attr.castExpandedControllerStyle, R.style.CastExpandedController);
        int r72 = r64.getResourceId(R.styleable.CastExpandedController_castSeekBarProgressAndThumbColor, 0);
        int r1 = r64.getResourceId(R.styleable.CastExpandedController_castSeekBarSecondaryProgressColor, 0);
        int r2 = r64.getResourceId(R.styleable.CastExpandedController_castSeekBarUnseekableProgressColor, 0);
        int r0 = r64.getResourceId(R.styleable.CastExpandedController_castAdBreakMarkerColor, 0);
        this.zzm = r5.getResources().getColor(r72);
        this.zzn = r5.getResources().getColor(r1);
        this.zzo = r5.getResources().getColor(r2);
        this.zzp = r5.getResources().getColor(r0);
        r64.recycle();
    }

    private final void zzf(Canvas r2, int r3, int r4, int r5, int r6, int r7) {
        Paint r62 = this.zzl;
        r62.setColor(r7);
        float r52 = r5;
        float r42 = r4 / r52;
        float r32 = r3 / r52;
        float r53 = r6;
        float r1 = this.zzi;
        r2.drawRect(r32 * r53, -r1, r42 * r53, r1, r62);
    }

    private final void zzg(int r4) {
        zze r0 = this.zza;
        if (r0.zzf == true) goto L5;
        return;
    L5:
        int r1 = r0.zzd;
        int r02 = r0.zze;
        int r2 = CastUtils.zza;
        this.zzf = Integer.valueOf(Math.min(Math.max(r4, r1), r02));
        zzd r42 = this.zzd;
        if (r42 == null) goto L8;
        r42.zzc(this, getProgress(), true);
    L8:
        Runnable r43 = this.zzs;
        if (r43 != null) goto L11;
        this.zzs = new zzc(this);
    L12:
        postDelayed(this.zzs, 200);
        postInvalidate();
        return;
    L11:
        removeCallbacks(r43);
        goto L12
    }

    private final void zzh() {
        this.zze = true;
        zzd r0 = this.zzd;
        if (r0 == null) goto L6;
        r0.zzb(this);
        return;
    }

    private final void zzi() {
        this.zze = false;
        zzd r0 = this.zzd;
        if (r0 == null) goto L6;
        r0.zza(this);
        return;
    }

    private final int zzj(int r6) {
        int r0 = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        return (int) ((r6 / r0) * this.zza.zzb);
    }

    public int getMaxProgress() {
        return this.zza.zzb;
    }

    public int getProgress() {
        Integer r0 = this.zzf;
        if (r0 == null) goto L7;
        return r0.intValue();
    L7:
        return this.zza.zza;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        Runnable r0 = this.zzs;
        if (r0 == null) goto L5;
        removeCallbacks(r0);
    L5:
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas r13) {
        int r7 = r13.save();
        r13.translate(getPaddingLeft(), getPaddingTop());
        zzb r2 = this.zzb;
        if (r2 != null) goto L60;
        int r5 = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        int r22 = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int r9 = getProgress();
        int r10 = r13.save();
        r13.translate(0.0f, r22 / 2);
        zze r23 = this.zza;
        if (r23.zzf == false) goto L19;
        int r3 = r23.zzd;
        if (r3 <= 0) goto L9;
        zzf(r13, 0, r3, r23.zzb, r5, this.zzo);
    L9:
        zze r1 = this.zza;
        int r24 = r1.zzd;
        if (r9 <= r24) goto L12;
        zzf(r13, r24, r9, r1.zzb, r5, this.zzm);
        int r25 = r9;
    L13:
        zze r12 = this.zza;
        int r32 = r12.zze;
        if (r32 <= r25) goto L16;
        zzf(r13, r25, r32, r12.zzb, r5, this.zzn);
    L16:
        zze r14 = this.zza;
        int r33 = r14.zzb;
        int r26 = r14.zze;
        if (r33 <= r26) goto L29;
        zzf(r13, r26, r33, r33, r5, this.zzo);
    L29:
        r13.restoreToCount(r10);
        List r0 = this.zzc;
        if (r0 == null) goto L56;
        if (r0.isEmpty() == true) goto L56;
        Paint r52 = this.zzl;
        r52.setColor(this.zzp);
        int r92 = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        int r27 = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int r102 = r13.save();
        r13.translate(0.0f, r27 / 2);
        Iterator r8 = r0.iterator();
    L36:
        if (r8.hasNext() == false) goto L54;
        zza r02 = (zza) r8.next();
        if (r02 == null) goto L36;
        int r28 = Math.min(r02.zza, this.zza.zzb);
        if (r02.zzc == false) goto L42;
        int r03 = r02.zzb;
    L43:
        float r34 = r92;
        float r4 = this.zza.zzb;
        float r11 = this.zzk;
        float r04 = ((r03 + r28) * r34) / r4;
        float r29 = (r28 * r34) / r4;
        if ((r04 - r29) >= r11) goto L47;
        r04 = r29 + r11;
    L47:
        if (r04 > r34) goto L51;
        r34 = r04;
    L51:
        if ((r34 - r29) >= r11) goto L53;
        r29 = r34 - r11;
    L53:
        float r42 = this.zzi;
        r13.drawRect(r29, -r42, r34, r42, r52);
        goto L36
    L42:
        r03 = 1;
        goto L43
    L54:
        r13.restoreToCount(r102);
    L56:
        if (isEnabled() == true) goto L58;
    L61:
        r13.restoreToCount(r7);
        return;
    L58:
        if (this.zza.zzf == false) goto L61;
        Paint r05 = this.zzl;
        r05.setColor(this.zzm);
        int r210 = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        int r35 = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        double r43 = getProgress();
        double r82 = this.zza.zzb;
        int r103 = r13.save();
        r13.drawCircle((int) ((r43 / r82) * r210), r35 / 2.0f, this.zzj, r05);
        r13.restoreToCount(r103);
        goto L61
    L12:
        r25 = r9;
        goto L13
    L19:
        int r211 = Math.max(r23.zzc, 0);
        if (r211 <= 0) goto L22;
        int r36 = r211;
        zzf(r13, 0, r36, this.zza.zzb, r5, this.zzo);
    L23:
        if (r9 <= r36) goto L25;
        zzf(r13, r36, r9, this.zza.zzb, r5, this.zzm);
        int r212 = r9;
    L26:
        int r37 = this.zza.zzb;
        if (r37 <= r212) goto L29;
        zzf(r13, r212, r37, r37, r5, this.zzo);
        goto L29
    L25:
        r212 = r9;
        goto L26
    L22:
        r36 = r211;
        goto L23
    L60:
        int r53 = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        int r06 = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int r93 = r13.save();
        r13.translate(0.0f, r06 / 2);
        int r6 = this.zzp;
        int r38 = r2.zza;
        int r44 = r2.zzb;
        zzf(r13, 0, r38, r44, r53, r6);
        zzf(r13, r38, r44, r44, r53, this.zzo);
        r13.restoreToCount(r93);
        goto L61
    }

    @Override // android.view.View
    public synchronized void onMeasure(int r6, int r7) {
        monitor-enter(this);
        float r0 = getPaddingLeft();     // Catch: Throwable -> L6
        float r1 = getPaddingRight();     // Catch: Throwable -> L6
        float r2 = getPaddingTop();     // Catch: Throwable -> L6
        setMeasuredDimension(View.resolveSizeAndState((int) ((this.zzg + r0) + r1), r6, 0), View.resolveSizeAndState((int) ((this.zzh + r2) + getPaddingBottom()), r7, 0));     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r8) {
        if (isEnabled() == true) goto L5;
    L31:
        return false;
    L5:
        if (this.zza.zzf == false) goto L31;
        if (this.zzr != null) goto L11;
        this.zzr = new Point();
    L11:
        if (this.zzq != null) goto L13;
        this.zzq = new int[2];
    L13:
        getLocationOnScreen(this.zzq);
        this.zzr.set((((int) r8.getRawX()) - this.zzq[0]) - getPaddingLeft(), ((int) r8.getRawY()) - this.zzq[1]);
        int r82 = r8.getAction();
        if (r82 == 0) goto L29;
        if (r82 == 1) goto L27;
        if (r82 != 2) goto L18;
        zzg(zzj(this.zzr.x));
        return true;
    L18:
        if (r82 != 3) goto L31;
        this.zze = false;
        this.zzf = null;
        zzd r83 = this.zzd;
        if (r83 == null) goto L23;
        r83.zzc(this, getProgress(), true);
        this.zzd.zza(this);
    L23:
        postInvalidate();
        return true;
    L27:
        zzg(zzj(this.zzr.x));
        zzi();
        return true;
    L29:
        zzh();
        zzg(zzj(this.zzr.x));
        return true;
    }

    public final void zza(List r2) {
        if (Objects.equal(this.zzc, r2) == false) goto L5;
        return;
    L5:
        if (r2 != null) goto L7;
        ArrayList r22 = null;
    L8:
        this.zzc = r22;
        postInvalidate();
        return;
    L7:
        r22 = new ArrayList(r2);
        goto L8
    }

    public final void zzb(zze r3) {
        if (this.zze == true) goto L9;
        zze r0 = new zze();
        r0.zza = r3.zza;
        r0.zzb = r3.zzb;
        r0.zzc = r3.zzc;
        r0.zzd = r3.zzd;
        r0.zze = r3.zze;
        r0.zzf = r3.zzf;
        this.zza = r0;
        this.zzf = null;
        zzd r32 = this.zzd;
        if (r32 == null) goto L7;
        r32.zzc(this, getProgress(), false);
    L7:
        postInvalidate();
        return;
    }

    public final /* synthetic */ void zzc(int r1) {
        zzg(r1);
    }

    public final /* synthetic */ void zzd() {
        zzh();
    }

    public final /* synthetic */ void zze() {
        zzi();
    }
}
