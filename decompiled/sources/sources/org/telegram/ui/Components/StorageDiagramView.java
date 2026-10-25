package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import kotlin.jvm.internal.LongCompanionObject;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Storage.CacheModel;

/* loaded from: classes7.dex */
public abstract class StorageDiagramView extends View implements NotificationCenter.NotificationCenterDelegate {
    private float[] animateToPercentage;
    private AvatarDrawable avatarDrawable;
    private ImageReceiver avatarImageReceiver;
    ValueAnimator backAnimator;
    CacheModel cacheModel;
    private ClearViewData[] data;
    private Long dialogId;
    CharSequence dialogText;
    StaticLayout dialogTextLayout;
    TextPaint dialogTextPaint;
    private float[] drawingPercentage;
    int enabledCount;
    float pressedProgress;
    private RectF rectF;
    private float singleProgress;
    private float[] startFromPercentage;
    AnimatedTextView.AnimatedTextDrawable text1;
    AnimatedTextView.AnimatedTextDrawable text2;
    ValueAnimator valueAnimator;

    /* renamed from: org.telegram.ui.Components.StorageDiagramView$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ StorageDiagramView this$0;
        final /* synthetic */ ClearViewData[] val$data;

        public AnonymousClass1(StorageDiagramView r1, ClearViewData[] r2) {
            this.this$0 = r1;
            this.val$data = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            int r0 = 0;
        L3:
            ClearViewData[] r1 = this.val$data;
            if (r0 >= r1.length) goto L9;
            ClearViewData r12 = r1[r0];
            if (r12 == null) goto L8;
            r12.firstDraw = false;
        L8:
            r0 = r0 + 1;
            goto L3
        }
    }

    /* renamed from: org.telegram.ui.Components.StorageDiagramView$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ StorageDiagramView this$0;

        public AnonymousClass2(StorageDiagramView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            super.onAnimationEnd(r1);
            this.this$0.backAnimator = null;
        }
    }

    public static class ClearViewData {
        public boolean clear;
        public int colorKey;
        boolean firstDraw;
        Paint paint;
        private final StorageDiagramView parentView;
        public long size;

        public ClearViewData(StorageDiagramView r3) {
            Paint r0 = new Paint(1);
            this.paint = r0;
            this.clear = true;
            this.firstDraw = false;
            this.parentView = r3;
            r0.setStyle(Paint.Style.STROKE);
            this.paint.setStrokeWidth(AndroidUtilities.dp(5.0f));
            this.paint.setStrokeCap(Paint.Cap.ROUND);
            this.paint.setStrokeJoin(Paint.Join.ROUND);
        }

        public void setClear(boolean r2) {
            if (this.clear == r2) goto L6;
            this.clear = r2;
            this.firstDraw = true;
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$6aW9SeHIpFu7rRvlzncRLe0Zb38(StorageDiagramView r0, ClearViewData[] r1, ValueAnimator r2) {
        r0.lambda$update$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$r4rkcG5aGrReKsLCltfFM_5qSME(StorageDiagramView r0, ValueAnimator r1) {
        r0.lambda$setPressed$1(r1);
    }

    public StorageDiagramView(Context r3) {
        super(r3);
        this.rectF = new RectF();
        this.singleProgress = 0.0f;
        this.text1 = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
        this.text2 = new AnimatedTextView.AnimatedTextDrawable(false, true, false);
        this.text1.setCallback(this);
        this.text2.setCallback(this);
    }

    public StorageDiagramView(Context r3, long r4) {
        this(r3);
        this.dialogId = Long.valueOf(r4);
        AvatarDrawable r32 = new AvatarDrawable();
        this.avatarDrawable = r32;
        r32.setScaleSize(1.5f);
        ImageReceiver r33 = new ImageReceiver();
        this.avatarImageReceiver = r33;
        r33.setParentView(this);
        if (r4 != LongCompanionObject.MAX_VALUE) goto L6;
        this.dialogText = LocaleController.getString(R.string.CacheOtherChats);
        this.avatarDrawable.setAvatarType(14);
        this.avatarImageReceiver.setForUserOrChat(null, this.avatarDrawable);
        return;
    L6:
        TLObject r34 = MessagesController.getInstance(UserConfig.selectedAccount).getUserOrChat(r4);
        String r35 = DialogObject.setDialogPhotoTitle(this.avatarImageReceiver, this.avatarDrawable, r34);
        this.dialogText = r35;
        this.dialogText = Emoji.replaceEmoji(r35, null, false);
    }

    private /* synthetic */ void lambda$setPressed$1(ValueAnimator r1) {
        this.pressedProgress = ((Float) r1.getAnimatedValue()).floatValue();
        invalidate();
    }

    private /* synthetic */ void lambda$update$0(ClearViewData[] r5, ValueAnimator r6) {
        float r62 = ((Float) r6.getAnimatedValue()).floatValue();
        int r0 = 0;
    L4:
        if (r0 >= r5.length) goto L6;
        this.drawingPercentage[r0] = (this.startFromPercentage[r0] * (1.0f - r62)) + (this.animateToPercentage[r0] * r62);
        r0 = r0 + 1;
        goto L4
    L6:
        invalidate();
    }

    public long calculateSize() {
        if (this.data != null) goto L5;
        return 0;
    L5:
        int r0 = 0;
        long r3 = 0;
    L7:
        if (r0 >= this.data.length) goto L21;
        long r5 = this.cacheModel.getSelectedFilesSize(r0);
        ClearViewData r7 = this.data[r0];
        if (r7 == null) goto L20;
        if (r7.clear == true) goto L16;
        if (r5 <= 0) goto L20;
    L16:
        if (r5 > 0) goto L19;
        r5 = r7.size;
    L19:
        r3 = r3 + r5;
    L20:
        r0 = r0 + 1;
        goto L7
    L21:
        return r3;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.emojiLoaded) goto L6;
        invalidate();
        return;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ImageReceiver r0 = this.avatarImageReceiver;
        if (r0 == null) goto L5;
        r0.onAttachedToWindow();
    L5:
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
    }

    public abstract void onAvatarClick();

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ImageReceiver r0 = this.avatarImageReceiver;
        if (r0 == null) goto L5;
        r0.onDetachedFromWindow();
    L5:
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
    }

    @Override // android.view.View
    public void onDraw(Canvas r29) {
        if (this.data != null) goto L6;
        return;
    L6:
        if (this.avatarImageReceiver == null) goto L13;
        r29.save();
        if (isPressed() == false) goto L12;
        float r2 = this.pressedProgress;
        if (r2 == 1.0f) goto L12;
        float r22 = r2 + (Math.min(40.0f, 1000.0f / AndroidUtilities.screenRefreshRate) / 100.0f);
        this.pressedProgress = r22;
        this.pressedProgress = Utilities.clamp(r22, 1.0f, 0.0f);
        invalidate();
    L12:
        float r23 = ((1.0f - this.pressedProgress) * 0.15f) + 0.85f;
        r29.scale(r23, r23, this.avatarImageReceiver.getCenterX(), this.avatarImageReceiver.getCenterY());
    L13:
        int r24 = this.enabledCount;
        float r3 = this.singleProgress;
        if (r24 <= 1) goto L21;
        if (r3 <= 0.0f) goto L25;
        float r25 = (float) (r3 - 0.04d);
        this.singleProgress = r25;
        if (r25 >= 0.0f) goto L25;
        this.singleProgress = 0.0f;
    L25:
        float r11 = 0.0f;
        int r10 = 0;
    L26:
        ClearViewData[] r26 = this.data;
        int r4 = 255;
        if (r10 >= r26.length) goto L45;
        ClearViewData r27 = r26[r10];
        if (r27 == null) goto L44;
        float r21 = this.drawingPercentage[r10];
        if (r21 == 0.0f) goto L44;
        if (r27.firstDraw == false) goto L43;
        float r17 = ((-360.0f) * r21) + ((1.0f - this.singleProgress) * 10.0f);
        if (r17 <= 0.0f) goto L38;
        float r32 = 0.0f;
    L39:
        r27.paint.setColor(Theme.getColor(r27.colorKey));
        this.data[r10].paint.setAlpha(255);
        double r42 = this.rectF.width() / 2.0f;
        if (Math.abs((float) (((3.141592653589793d * r42) / 180.0d) * r32)) > 1.0f) goto L42;
        double r12 = (-90.0f) - (360.0f * r11);
        r29.drawPoint(this.rectF.centerX() + ((float) (Math.cos(Math.toRadians(r12)) * r42)), this.rectF.centerY() + ((float) (r42 * Math.sin(Math.toRadians(r12)))), this.data[r10].paint);
        goto L43
    L42:
        this.data[r10].paint.setStyle(Paint.Style.STROKE);
        r29.drawArc(this.rectF, (-90.0f) - (360.0f * r11), r32, false, this.data[r10].paint);
        goto L43
    L38:
        r32 = r17;
    L43:
        r11 = r11 + r21;
    L44:
        r10 = r10 + 1;
        goto L26
    L45:
        float r112 = 0.0f;
        int r102 = 0;
    L46:
        ClearViewData[] r28 = this.data;
        if (r102 >= r28.length) goto L65;
        ClearViewData r210 = r28[r102];
        if (r210 == null) goto L52;
        float r212 = this.drawingPercentage[r102];
        if (r212 == 0.0f) goto L52;
        if (r210.firstDraw == true) goto L62;
        float r33 = (r212 * (-360.0f)) + ((1.0f - this.singleProgress) * 10.0f);
        if (r33 <= 0.0f) goto L58;
        r33 = 0.0f;
    L58:
        r210.paint.setColor(Theme.getColor(r210.colorKey));
        this.data[r102].paint.setAlpha(r4);
        double r5 = this.rectF.width() / 2.0f;
        if (Math.abs((float) (r33 * ((r5 * 3.141592653589793d) / 180.0d))) > 1.0f) goto L61;
        double r43 = (-90.0f) - (r112 * 360.0f);
        r29.drawPoint(this.rectF.centerX() + ((float) (r5 * Math.cos(Math.toRadians(r43)))), this.rectF.centerY() + ((float) (Math.sin(Math.toRadians(r43)) * r5)), this.data[r102].paint);
        int r222 = 255;
    L63:
        r112 = r112 + r212;
    L64:
        r102 = r102 + 1;
        r4 = r222;
        goto L46
    L61:
        this.data[r102].paint.setStyle(Paint.Style.STROKE);
        r222 = 255;
        r29.drawArc(this.rectF, (-90.0f) - (r112 * 360.0f), r33, false, this.data[r102].paint);
        goto L63
    L62:
        r222 = r4;
    L52:
        r222 = r4;
        goto L64
    L65:
        ImageReceiver r211 = this.avatarImageReceiver;
        if (r211 == null) goto L68;
        r211.draw(r29);
        r29.restore();
    L68:
        AnimatedTextView.AnimatedTextDrawable r213 = this.text1;
        if (r213 == null) goto L75;
        int r44 = Theme.key_dialogTextBlack;
        r213.setTextColor(Theme.getColor(r44));
        this.text2.setTextColor(Theme.getColor(r44));
        if (this.dialogId == null) goto L73;
        float r214 = (this.text1.getCurrentWidth() + AndroidUtilities.dp(4.0f)) + this.text2.getCurrentWidth();
        float r45 = (getWidth() - r214) / 2.0f;
        this.text1.setBounds(0, AndroidUtilities.dp(115.0f), (int) (this.text1.getCurrentWidth() + r45), AndroidUtilities.dp(145.0f));
        AnimatedTextView.AnimatedTextDrawable r52 = this.text2;
        r52.setBounds((int) ((r45 + r214) - r52.getCurrentWidth()), AndroidUtilities.dp(118.0f), getWidth(), AndroidUtilities.dp(148.0f));
    L73:
        this.text1.draw(r29);
        this.text2.draw(r29);
    L75:
        if (this.dialogTextLayout == null) goto L85;
        r29.save();
        r29.translate(AndroidUtilities.dp(30.0f), AndroidUtilities.dp(148.0f) - ((this.dialogTextLayout.getHeight() - AndroidUtilities.dp(13.0f)) / 2.0f));
        this.dialogTextPaint.setColor(Theme.getColor(Theme.key_dialogTextBlack));
        this.dialogTextLayout.draw(r29);
        r29.restore();
        return;
    L85:
        return;
    L21:
        if (r3 >= 1.0f) goto L25;
        float r215 = (float) (r3 + 0.04d);
        this.singleProgress = r215;
        if (r215 <= 1.0f) goto L25;
        this.singleProgress = 1.0f;
        goto L25
    }

    @Override // android.view.View
    public void onMeasure(int r14, int r15) {
        if (this.dialogId == null) goto L5;
        super.onMeasure(r14, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(166.0f), TLObject.FLAG_30));
        int r152 = (View.MeasureSpec.getSize(r14) - AndroidUtilities.dp(110.0f)) / 2;
        this.rectF.set(AndroidUtilities.dp(3.0f) + r152, AndroidUtilities.dp(3.0f), AndroidUtilities.dp(107.0f) + r152, AndroidUtilities.dp(107.0f));
    L6:
        AnimatedTextView.AnimatedTextDrawable r5 = this.text1;
        CubicBezierInterpolator r11 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r5.setAnimationProperties(0.18f, 0, 300, r11);
        this.text1.setTextSize(AndroidUtilities.dp(24.0f));
        this.text1.setTypeface(AndroidUtilities.bold());
        this.text2.setAnimationProperties(0.18f, 0, 300, r11);
        Long r1 = this.dialogId;
        AnimatedTextView.AnimatedTextDrawable r2 = this.text2;
        if (r1 == null) goto L9;
        r2.setTextSize(AndroidUtilities.dp(16.0f));
        this.text1.setGravity(5);
        this.text2.setGravity(3);
    L11:
        if (this.dialogText != null) goto L13;
    L16:
        ImageReceiver r142 = this.avatarImageReceiver;
        if (r142 == null) goto L19;
        r142.setImageCoords(r152 + AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(90.0f), AndroidUtilities.dp(90.0f));
        this.avatarImageReceiver.setRoundRadius(AndroidUtilities.dp(45.0f));
    L19:
        updateDescription();
        return;
    L13:
        if (this.dialogTextPaint != null) goto L15;
        this.dialogTextPaint = new TextPaint(1);
    L15:
        this.dialogTextPaint.setTextSize(AndroidUtilities.dp(13.0f));
        int r3 = View.MeasureSpec.getSize(r14) - AndroidUtilities.dp(60.0f);
        this.dialogTextLayout = StaticLayoutEx.createStaticLayout2(this.dialogText, this.dialogTextPaint, r3, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false, TextUtils.TruncateAt.END, r3, 1);
        goto L16
    L9:
        r2.setTextSize(AndroidUtilities.dp(13.0f));
        int r12 = (int) this.text1.getTextSize();
        int r22 = (int) this.text2.getTextSize();
        int r4 = ((AndroidUtilities.dp(110.0f) - r12) - r22) / 2;
        int r13 = r12 + r4;
        this.text1.setBounds(0, r4, getMeasuredWidth(), r13);
        this.text2.setBounds(0, AndroidUtilities.dp(2.0f) + r13, getMeasuredWidth(), (r13 + r22) + AndroidUtilities.dp(2.0f));
        this.text1.setGravity(17);
        this.text2.setGravity(17);
        goto L11
    L5:
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(110.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(110.0f), TLObject.FLAG_30));
        this.rectF.set(AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), AndroidUtilities.dp(107.0f), AndroidUtilities.dp(107.0f));
        r152 = 0;
        goto L6
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r8) {
        if (this.avatarImageReceiver == null) goto L17;
        Long r0 = this.dialogId;
        if (r0 == null) goto L17;
        if (r0.longValue() == LongCompanionObject.MAX_VALUE) goto L17;
        if (r8.getX() <= this.avatarImageReceiver.getImageX()) goto L17;
        if (r8.getX() > this.avatarImageReceiver.getImageX2()) goto L17;
        if (r8.getY() <= this.avatarImageReceiver.getImageY()) goto L17;
        if (r8.getY() > this.avatarImageReceiver.getImageY2()) goto L17;
        boolean r02 = true;
    L19:
        if (r8.getAction() != 0) goto L24;
        if (r02 == false) goto L29;
        setPressed(true);
        return true;
    L29:
        return super.onTouchEvent(r8);
    L24:
        if (r8.getAction() != 1) goto L26;
    L30:
        if (r02 == true) goto L32;
    L34:
        setPressed(false);
        return true;
    L32:
        if (r8.getAction() == 3) goto L34;
        AndroidUtilities.runOnUIThread(new StorageDiagramView$$ExternalSyntheticLambda0(this), 80);
        goto L34
    L26:
        if (r8.getAction() != 3) goto L29;
    L17:
        r02 = false;
        goto L19
    }

    public void setCacheModel(CacheModel r1) {
        this.cacheModel = r1;
    }

    public void setData(CacheModel r1, ClearViewData[] r2) {
        this.data = r2;
        this.cacheModel = r1;
        invalidate();
        this.drawingPercentage = new float[r2.length];
        this.animateToPercentage = new float[r2.length];
        this.startFromPercentage = new float[r2.length];
        update(false);
        if (this.enabledCount <= 1) goto L6;
        this.singleProgress = 0.0f;
        return;
    L6:
        this.singleProgress = 1.0f;
    }

    @Override // android.view.View
    public void setPressed(boolean r4) {
        if (isPressed() == r4) goto L14;
        super.setPressed(r4);
        invalidate();
        if (r4 == false) goto L9;
        ValueAnimator r0 = this.backAnimator;
        if (r0 == null) goto L9;
        r0.removeAllListeners();
        this.backAnimator.cancel();
    L9:
        if (r4 == true) goto L15;
        float r42 = this.pressedProgress;
        if (r42 == 0.0f) goto L16;
        ValueAnimator r43 = ValueAnimator.ofFloat(new float[]{r42, 0.0f});
        this.backAnimator = r43;
        r43.addUpdateListener(new StorageDiagramView$$ExternalSyntheticLambda1(this));
        this.backAnimator.addListener(new AnonymousClass2(this));
        this.backAnimator.setInterpolator(new OvershootInterpolator(2.0f));
        this.backAnimator.setDuration(350);
        this.backAnimator.start();
        return;
    L16:
        return;
    L15:
        return;
    }

    public void update(boolean r17) {
        ClearViewData[] r1 = this.data;
        if (r1 != null) goto L5;
        return;
    L5:
        int r5 = 0;
        long r6 = 0;
    L7:
        if (r5 >= r1.length) goto L21;
        long r8 = this.cacheModel.getSelectedFilesSize(r5);
        ClearViewData r10 = r1[r5];
        if (r10 == null) goto L20;
        if (r10.clear == true) goto L16;
        if (r8 <= 0) goto L20;
    L16:
        if (r8 > 0) goto L19;
        r8 = r10.size;
    L19:
        r6 = r6 + r8;
    L20:
        r5 = r5 + 1;
        goto L7
    L21:
        this.enabledCount = 0;
        int r82 = 0;
        float r9 = 0.0f;
        float r102 = 0.0f;
    L23:
        if (r82 >= r1.length) goto L53;
        long r11 = this.cacheModel.getSelectedFilesSize(r82);
        ClearViewData r13 = r1[r82];
        if (r13 != null) goto L27;
    L31:
        if (r13 == null) goto L50;
        boolean r14 = r13.clear;
        if (r14 == true) goto L38;
        if (r11 <= 0) goto L50;
    L38:
        if (r11 > 0) goto L41;
        r11 = r13.size;
    L41:
        float r112 = r11 / r6;
        if (r112 >= 0.02777f) goto L44;
        r112 = 0.02777f;
    L44:
        r9 = r9 + r112;
        if (r112 <= r102) goto L49;
        if (r14 == true) goto L48;
        if (r11 <= 0) goto L49;
    L48:
        r102 = r112;
    L49:
        this.animateToPercentage[r82] = r112;
    L51:
        r82 = r82 + 1;
    L50:
        this.animateToPercentage[r82] = 0.0f;
        goto L51
    L27:
        if (r13.clear == false) goto L29;
    L30:
        this.enabledCount++;
        goto L31
    L29:
        if (r11 <= 0) goto L31;
    L53:
        if (r9 <= 1.0f) goto L62;
        float r3 = 1.0f / r9;
        int r4 = 0;
    L56:
        if (r4 >= r1.length) goto L62;
        if (r1[r4] == null) goto L61;
        float[] r52 = this.animateToPercentage;
        r52[r4] = r52[r4] * r3;
    L61:
        r4 = r4 + 1;
    L62:
        if (r17 == true) goto L65;
        System.arraycopy(this.animateToPercentage, 0, this.drawingPercentage, 0, r1.length);
        return;
    L65:
        System.arraycopy(this.drawingPercentage, 0, this.startFromPercentage, 0, r1.length);
        ValueAnimator r2 = this.valueAnimator;
        if (r2 == null) goto L68;
        r2.removeAllListeners();
        this.valueAnimator.cancel();
    L68:
        ValueAnimator r22 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.valueAnimator = r22;
        r22.addUpdateListener(new StorageDiagramView$$ExternalSyntheticLambda2(this, r1));
        this.valueAnimator.addListener(new AnonymousClass1(this, r1));
        this.valueAnimator.setDuration(450);
        this.valueAnimator.setInterpolator(new FastOutSlowInInterpolator());
        this.valueAnimator.start();
    }

    public long updateDescription() {
        long r0 = calculateSize();
        String r3 = " ";
        String[] r2 = AndroidUtilities.formatFileSize(r0).split(" ");
        if (r2.length <= 1) goto L13;
        AnimatedTextView.AnimatedTextDrawable r4 = this.text1;
        if (r0 != 0) goto L7;
        String r8 = " ";
    L8:
        r4.setText(r8, true, false);
        AnimatedTextView.AnimatedTextDrawable r9 = this.text2;
        if (r0 == 0) goto L12;
        r3 = r2[1];
    L12:
        r9.setText(r3, true, false);
        goto L13
    L7:
        r8 = r2[0];
    L13:
        return r0;
    }
}
