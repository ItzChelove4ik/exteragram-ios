package org.telegram.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import java.io.File;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Bitmaps;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes6.dex */
public class PhotoCropActivity extends BaseFragment {
    private String bitmapKey;
    private PhotoEditActivityDelegate delegate;
    private boolean doneButtonPressed;
    private BitmapDrawable drawable;
    private Bitmap imageToCrop;
    private boolean sameBitmap;
    private PhotoCropView view;

    /* renamed from: org.telegram.ui.PhotoCropActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ PhotoCropActivity this$0;

        public AnonymousClass1(PhotoCropActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r3) {
            if (r3 != (-1)) goto L7;
            this.this$0.finishFragment();
            return;
        L7:
            if (r3 == 1) goto L9;
            return;
        L9:
            if (PhotoCropActivity.m16788$$Nest$fgetdelegate(this.this$0) != null) goto L11;
        L16:
            this.this$0.finishFragment();
            return;
        L11:
            if (PhotoCropActivity.m16789$$Nest$fgetdoneButtonPressed(this.this$0) == true) goto L16;
            Bitmap r32 = PhotoCropActivity.m16792$$Nest$fgetview(this.this$0).getBitmap();
            if (r32 != PhotoCropActivity.m16791$$Nest$fgetimageToCrop(this.this$0)) goto L15;
            PhotoCropActivity.m16794$$Nest$fputsameBitmap(this.this$0, true);
        L15:
            PhotoCropActivity.m16788$$Nest$fgetdelegate(this.this$0).didFinishEdit(r32);
            PhotoCropActivity.m16793$$Nest$fputdoneButtonPressed(this.this$0, true);
            goto L16
        }
    }

    public class PhotoCropView extends FrameLayout {
        int bitmapHeight;
        int bitmapWidth;
        int bitmapX;
        int bitmapY;
        Paint circlePaint;
        int draggingState;
        boolean freeform;
        Paint halfPaint;
        float oldX;
        float oldY;
        Paint rectPaint;
        float rectSizeX;
        float rectSizeY;
        float rectX;
        float rectY;
        final /* synthetic */ PhotoCropActivity this$0;
        int viewHeight;
        int viewWidth;

        public static /* synthetic */ boolean $r8$lambda$4Phhqo_m3sGJtnUggCNxPfub4FE(PhotoCropView r0, View r1, MotionEvent r2) {
            return r0.lambda$init$0(r1, r2);
        }

        public PhotoCropView(PhotoCropActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.rectPaint = null;
            this.circlePaint = null;
            this.halfPaint = null;
            this.rectSizeX = 600.0f;
            this.rectSizeY = 600.0f;
            this.rectX = -1.0f;
            this.rectY = -1.0f;
            this.draggingState = 0;
            this.oldX = 0.0f;
            this.oldY = 0.0f;
            init();
        }

        private void init() {
            Paint r0 = new Paint();
            this.rectPaint = r0;
            r0.setColor(1073412858);
            this.rectPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
            this.rectPaint.setStyle(Paint.Style.STROKE);
            Paint r02 = new Paint();
            this.circlePaint = r02;
            r02.setColor(-1);
            Paint r03 = new Paint();
            this.halfPaint = r03;
            r03.setColor(-939524096);
            setBackgroundColor(-13421773);
            setOnTouchListener(new PhotoCropActivity$PhotoCropView$$ExternalSyntheticLambda0(this));
        }

        private /* synthetic */ boolean lambda$init$0(View r13, MotionEvent r14) {
            float r132 = r14.getX();
            float r0 = r14.getY();
            int r1 = AndroidUtilities.dp(14.0f);
            if (r14.getAction() != 0) goto L55;
            float r142 = this.rectX;
            float r12 = r1;
            if ((r142 - r12) < r132) goto L7;
        L13:
            float r9 = this.rectSizeX;
            if (((r142 - r12) + r9) >= r132) goto L23;
            if (((r142 + r12) + r9) <= r132) goto L23;
            float r2 = this.rectY;
            if ((r2 - r12) >= r0) goto L23;
            if ((r2 + r12) <= r0) goto L23;
            this.draggingState = 2;
        L51:
            if (this.draggingState == 0) goto L53;
            requestDisallowInterceptTouchEvent(true);
        L53:
            this.oldX = r132;
            this.oldY = r0;
        L156:
            return true;
        L23:
            if ((r142 - r12) >= r132) goto L32;
            if ((r142 + r12) <= r132) goto L32;
            float r22 = this.rectY;
            float r10 = this.rectSizeY;
            if (((r22 - r12) + r10) >= r0) goto L32;
            if (((r22 + r12) + r10) <= r0) goto L32;
            this.draggingState = 3;
        L32:
            if (((r142 - r12) + r9) >= r132) goto L41;
            if (((r142 + r12) + r9) <= r132) goto L41;
            float r23 = this.rectY;
            float r7 = this.rectSizeY;
            if (((r23 - r12) + r7) >= r0) goto L41;
            if (((r23 + r12) + r7) <= r0) goto L41;
            this.draggingState = 4;
        L41:
            if (r142 < r132) goto L43;
        L49:
            this.draggingState = 0;
            goto L51
        L43:
            if ((r142 + r9) <= r132) goto L49;
            float r143 = this.rectY;
            if (r143 >= r0) goto L49;
            if ((r143 + this.rectSizeY) <= r0) goto L49;
            this.draggingState = 5;
            goto L51
        L7:
            if ((r142 + r12) <= r132) goto L13;
            float r24 = this.rectY;
            if ((r24 - r12) >= r0) goto L13;
            if ((r24 + r12) <= r0) goto L13;
            this.draggingState = 1;
            goto L51
        L55:
            if (r14.getAction() != 1) goto L58;
            this.draggingState = 0;
            goto L156
        L58:
            if (r14.getAction() != 2) goto L156;
            int r144 = this.draggingState;
            if (r144 == 0) goto L156;
            float r15 = r132 - this.oldX;
            float r25 = r0 - this.oldY;
            if (r144 != 5) goto L76;
            float r145 = this.rectX + r15;
            this.rectX = r145;
            float r16 = this.rectY + r25;
            this.rectY = r16;
            int r26 = this.bitmapX;
            if (r145 >= r26) goto L66;
            this.rectX = r26;
        L69:
            int r146 = this.bitmapY;
            if (r16 >= r146) goto L72;
            this.rectY = r146;
        L155:
            this.oldX = r132;
            this.oldY = r0;
            invalidate();
            goto L156
        L72:
            float r27 = this.rectSizeY;
            float r17 = r16 + r27;
            int r3 = this.bitmapHeight;
            if (r17 <= (r146 + r3)) goto L155;
            this.rectY = (r146 + r3) - r27;
            goto L155
        L66:
            float r32 = this.rectSizeX;
            float r147 = r145 + r32;
            int r4 = this.bitmapWidth;
            if (r147 <= (r26 + r4)) goto L69;
            this.rectX = (r26 + r4) - r32;
            goto L69
        L76:
            if (r144 != 1) goto L96;
            float r148 = this.rectSizeX;
            if ((r148 - r15) >= 160.0f) goto L80;
            r15 = r148 - 160.0f;
        L80:
            float r42 = this.rectX;
            float r5 = r42 + r15;
            int r6 = this.bitmapX;
            if (r5 >= r6) goto L84;
            r15 = r6 - r42;
        L84:
            if (this.freeform == true) goto L89;
            float r28 = this.rectY;
            float r33 = r28 + r15;
            int r52 = this.bitmapY;
            if (r33 >= r52) goto L88;
            r15 = r52 - r28;
        L88:
            this.rectX = r42 + r15;
            this.rectY = r28 + r15;
            this.rectSizeX = r148 - r15;
            this.rectSizeY -= r15;
            goto L155
        L89:
            float r53 = this.rectSizeY;
            if ((r53 - r25) >= 160.0f) goto L92;
            r25 = r53 - 160.0f;
        L92:
            float r34 = this.rectY;
            float r62 = r34 + r25;
            int r72 = this.bitmapY;
            if (r62 >= r72) goto L95;
            r25 = r72 - r34;
        L95:
            this.rectX = r42 + r15;
            this.rectY = r34 + r25;
            this.rectSizeX = r148 - r15;
            this.rectSizeY = r53 - r25;
            goto L155
        L96:
            if (r144 != 2) goto L116;
            float r149 = this.rectSizeX;
            if ((r149 + r15) >= 160.0f) goto L100;
            r15 = -(r149 - 160.0f);
        L100:
            float r43 = this.rectX;
            float r54 = (r43 + r149) + r15;
            int r63 = this.bitmapX;
            int r73 = this.bitmapWidth;
            if (r54 <= (r63 + r73)) goto L104;
            r15 = ((r63 + r73) - r43) - r149;
        L104:
            if (this.freeform == true) goto L109;
            float r29 = this.rectY;
            float r35 = r29 - r15;
            int r44 = this.bitmapY;
            if (r35 >= r44) goto L108;
            r15 = r29 - r44;
        L108:
            this.rectY = r29 - r15;
            this.rectSizeX = r149 + r15;
            this.rectSizeY += r15;
            goto L155
        L109:
            float r45 = this.rectSizeY;
            if ((r45 - r25) >= 160.0f) goto L112;
            r25 = r45 - 160.0f;
        L112:
            float r36 = this.rectY;
            float r55 = r36 + r25;
            int r64 = this.bitmapY;
            if (r55 >= r64) goto L115;
            r25 = r64 - r36;
        L115:
            this.rectY = r36 + r25;
            this.rectSizeX = r149 + r15;
            this.rectSizeY = r45 - r25;
            goto L155
        L116:
            if (r144 != 3) goto L135;
            float r1410 = this.rectSizeX;
            if ((r1410 - r15) >= 160.0f) goto L120;
            r15 = r1410 - 160.0f;
        L120:
            float r46 = this.rectX;
            float r56 = r46 + r15;
            int r65 = this.bitmapX;
            if (r56 >= r65) goto L123;
            r15 = r65 - r46;
        L123:
            boolean r57 = this.freeform;
            float r66 = this.rectY;
            if (r57 == true) goto L129;
            float r210 = (r66 + r1410) - r15;
            int r37 = this.bitmapY;
            int r58 = this.bitmapHeight;
            if (r210 <= (r37 + r58)) goto L128;
            r15 = ((r66 + r1410) - r37) - r58;
        L128:
            this.rectX = r46 + r15;
            this.rectSizeX = r1410 - r15;
            this.rectSizeY -= r15;
            goto L155
        L129:
            float r59 = this.rectSizeY;
            float r74 = (r66 + r59) + r25;
            int r92 = this.bitmapY;
            int r102 = this.bitmapHeight;
            if (r74 <= (r92 + r102)) goto L132;
            r25 = ((r92 + r102) - r66) - r59;
        L132:
            this.rectX = r46 + r15;
            this.rectSizeX = r1410 - r15;
            float r510 = r59 + r25;
            this.rectSizeY = r510;
            if (r510 >= 160.0f) goto L155;
            this.rectSizeY = 160.0f;
            goto L155
        L135:
            if (r144 != 4) goto L155;
            float r1411 = this.rectX;
            float r47 = this.rectSizeX;
            float r511 = (r1411 + r47) + r15;
            int r67 = this.bitmapX;
            int r75 = this.bitmapWidth;
            if (r511 <= (r67 + r75)) goto L139;
            r15 = ((r67 + r75) - r1411) - r47;
        L139:
            boolean r1412 = this.freeform;
            float r512 = this.rectY;
            if (r1412 == true) goto L145;
            float r1413 = (r512 + r47) + r15;
            int r211 = this.bitmapY;
            int r68 = this.bitmapHeight;
            if (r1413 <= (r211 + r68)) goto L144;
            r15 = ((r211 + r68) - r512) - r47;
        L144:
            this.rectSizeX = r47 + r15;
            this.rectSizeY += r15;
        L150:
            if (this.rectSizeX >= 160.0f) goto L153;
            this.rectSizeX = 160.0f;
        L153:
            if (this.rectSizeY >= 160.0f) goto L155;
            this.rectSizeY = 160.0f;
            goto L155
        L145:
            float r1414 = this.rectSizeY;
            float r69 = (r512 + r1414) + r25;
            int r76 = this.bitmapY;
            int r93 = this.bitmapHeight;
            if (r69 <= (r76 + r93)) goto L148;
            r25 = ((r76 + r93) - r512) - r1414;
        L148:
            this.rectSizeX = r47 + r15;
            this.rectSizeY = r1414 + r25;
            goto L150
        }

        private void updateBitmapSize() {
            if (this.viewWidth != 0) goto L5;
            return;
        L5:
            if (this.viewHeight != 0) goto L7;
            return;
        L7:
            if (PhotoCropActivity.m16791$$Nest$fgetimageToCrop(this.this$0) == null) goto L29;
            float r0 = this.rectX - this.bitmapX;
            int r1 = this.bitmapWidth;
            float r02 = r0 / r1;
            float r2 = this.rectY - this.bitmapY;
            int r3 = this.bitmapHeight;
            float r22 = r2 / r3;
            float r4 = this.rectSizeX / r1;
            float r12 = this.rectSizeY / r3;
            float r32 = PhotoCropActivity.m16791$$Nest$fgetimageToCrop(this.this$0).getWidth();
            float r5 = PhotoCropActivity.m16791$$Nest$fgetimageToCrop(this.this$0).getHeight();
            int r6 = this.viewWidth;
            float r7 = r6 / r32;
            int r8 = this.viewHeight;
            if (r7 <= (r8 / r5)) goto L12;
            this.bitmapHeight = r8;
            this.bitmapWidth = (int) Math.ceil(r32 * r9);
        L13:
            this.bitmapX = ((this.viewWidth - this.bitmapWidth) / 2) + AndroidUtilities.dp(14.0f);
            int r33 = ((this.viewHeight - this.bitmapHeight) / 2) + AndroidUtilities.dp(14.0f);
            this.bitmapY = r33;
            if (this.rectX == (-1.0f)) goto L16;
        L24:
            int r52 = this.bitmapWidth;
            this.rectX = (r02 * r52) + this.bitmapX;
            int r03 = this.bitmapHeight;
            this.rectY = (r22 * r03) + r33;
            this.rectSizeX = r4 * r52;
            this.rectSizeY = r12 * r03;
        L25:
            invalidate();
            return;
        L16:
            if (this.rectY != (-1.0f)) goto L24;
            if (this.freeform == false) goto L21;
            this.rectY = r33;
            this.rectX = this.bitmapX;
            this.rectSizeX = this.bitmapWidth;
            this.rectSizeY = this.bitmapHeight;
            goto L25
        L21:
            if (this.bitmapWidth <= this.bitmapHeight) goto L23;
            this.rectY = r33;
            this.rectX = ((this.viewWidth - r1) / 2) + AndroidUtilities.dp(14.0f);
            int r04 = this.bitmapHeight;
            this.rectSizeX = r04;
            this.rectSizeY = r04;
            goto L25
        L23:
            this.rectX = this.bitmapX;
            this.rectY = ((this.viewHeight - r0) / 2) + AndroidUtilities.dp(14.0f);
            int r05 = this.bitmapWidth;
            this.rectSizeX = r05;
            this.rectSizeY = r05;
            goto L25
        L12:
            this.bitmapWidth = r6;
            this.bitmapHeight = (int) Math.ceil(r5 * r7);
            goto L13
        }

        public Bitmap getBitmap() {
            float r0 = this.rectX - this.bitmapX;
            int r1 = this.bitmapWidth;
            float r2 = (this.rectY - this.bitmapY) / this.bitmapHeight;
            float r3 = this.rectSizeX / r1;
            float r4 = this.rectSizeY / r1;
            int r02 = (int) ((r0 / r1) * PhotoCropActivity.m16791$$Nest$fgetimageToCrop(this.this$0).getWidth());
            int r12 = (int) (r2 * PhotoCropActivity.m16791$$Nest$fgetimageToCrop(this.this$0).getHeight());
            int r22 = (int) (r3 * PhotoCropActivity.m16791$$Nest$fgetimageToCrop(this.this$0).getWidth());
            int r32 = (int) (r4 * PhotoCropActivity.m16791$$Nest$fgetimageToCrop(this.this$0).getWidth());
            if (r02 >= 0) goto L5;
            r02 = 0;
        L5:
            if (r12 >= 0) goto L8;
            r12 = 0;
        L8:
            if ((r02 + r22) <= PhotoCropActivity.m16791$$Nest$fgetimageToCrop(this.this$0).getWidth()) goto L11;
            r22 = PhotoCropActivity.m16791$$Nest$fgetimageToCrop(this.this$0).getWidth() - r02;
        L11:
            if ((r12 + r32) <= PhotoCropActivity.m16791$$Nest$fgetimageToCrop(this.this$0).getHeight()) goto L18;
            r32 = PhotoCropActivity.m16791$$Nest$fgetimageToCrop(this.this$0).getHeight() - r12;
        L18:
            return Bitmaps.createBitmap(PhotoCropActivity.m16791$$Nest$fgetimageToCrop(this.this$0), r02, r12, r22, r32);
        L15:
            th = move-exception;
            FileLog.e(th);
            return null;
        }

        @Override // android.view.View
        public void onDraw(Canvas r14) {
            if (PhotoCropActivity.m16790$$Nest$fgetdrawable(this.this$0) != null) goto L12;
        L8:
            r14.drawRect(this.bitmapX, this.bitmapY, r0 + this.bitmapWidth, this.rectY, this.halfPaint);
            float r8 = this.bitmapX;
            float r9 = this.rectY;
            r14.drawRect(r8, r9, this.rectX, r9 + this.rectSizeY, this.halfPaint);
            float r82 = this.rectX + this.rectSizeX;
            float r92 = this.rectY;
            r14.drawRect(r82, r92, this.bitmapX + this.bitmapWidth, r92 + this.rectSizeY, this.halfPaint);
            r14.drawRect(this.bitmapX, this.rectY + this.rectSizeY, r14 + this.bitmapWidth, this.bitmapY + this.bitmapHeight, this.halfPaint);
            float r83 = this.rectX;
            float r93 = this.rectY;
            r14.drawRect(r83, r93, r83 + this.rectSizeX, r93 + this.rectSizeY, this.rectPaint);
            int r142 = AndroidUtilities.dp(1.0f);
            float r0 = this.rectX;
            float r1 = r142;
            float r143 = r142 * 3;
            r14.drawRect(r0 + r1, this.rectY + r1, (r0 + r1) + AndroidUtilities.dp(20.0f), this.rectY + r143, this.circlePaint);
            float r02 = this.rectX;
            float r4 = this.rectY;
            r14.drawRect(r02 + r1, r4 + r1, r02 + r143, (r4 + r1) + AndroidUtilities.dp(20.0f), this.circlePaint);
            float r84 = ((this.rectX + this.rectSizeX) - r1) - AndroidUtilities.dp(20.0f);
            float r03 = this.rectY;
            r14.drawRect(r84, r03 + r1, (this.rectX + this.rectSizeX) - r1, r03 + r143, this.circlePaint);
            float r04 = this.rectX;
            float r42 = this.rectSizeX;
            float r5 = this.rectY;
            r14.drawRect((r04 + r42) - r143, r5 + r1, (r04 + r42) - r1, (r5 + r1) + AndroidUtilities.dp(20.0f), this.circlePaint);
            r14.drawRect(this.rectX + r1, ((this.rectY + this.rectSizeY) - r1) - AndroidUtilities.dp(20.0f), this.rectX + r143, (this.rectY + this.rectSizeY) - r1, this.circlePaint);
            float r05 = this.rectX;
            r14.drawRect(r05 + r1, (this.rectY + this.rectSizeY) - r143, (r05 + r1) + AndroidUtilities.dp(20.0f), (this.rectY + this.rectSizeY) - r1, this.circlePaint);
            float r85 = ((this.rectX + this.rectSizeX) - r1) - AndroidUtilities.dp(20.0f);
            float r06 = this.rectY;
            float r43 = this.rectSizeY;
            r14.drawRect(r85, (r06 + r43) - r143, (this.rectX + this.rectSizeX) - r1, (r06 + r43) - r1, this.circlePaint);
            r14.drawRect((this.rectX + this.rectSizeX) - r143, ((this.rectY + this.rectSizeY) - r1) - AndroidUtilities.dp(20.0f), (this.rectX + this.rectSizeX) - r1, (this.rectY + this.rectSizeY) - r1, this.circlePaint);
            int r144 = 1;
        L9:
            if (r144 >= 3) goto L11;
            float r07 = this.rectX;
            float r2 = this.rectSizeX;
            float r6 = r144;
            float r52 = this.rectY;
            r14.drawRect(r07 + ((r2 / 3.0f) * r6), r52 + r1, (r07 + r1) + ((r2 / 3.0f) * r6), (r52 + this.rectSizeY) - r1, this.circlePaint);
            float r08 = this.rectX;
            float r22 = this.rectY;
            float r53 = this.rectSizeY;
            r14.drawRect(r08 + r1, ((r53 / 3.0f) * r6) + r22, this.rectSizeX + (r08 - r1), (r22 + ((r53 / 3.0f) * r6)) + r1, this.circlePaint);
            r144 = r144 + 1;
            goto L9
        L11:
            return;
        L12:
            BitmapDrawable r09 = PhotoCropActivity.m16790$$Nest$fgetdrawable(this.this$0);     // Catch: Throwable -> L6
            int r12 = this.bitmapX;     // Catch: Throwable -> L6
            int r23 = this.bitmapY;     // Catch: Throwable -> L6
            r09.setBounds(r12, r23, this.bitmapWidth + r12, this.bitmapHeight + r23);     // Catch: Throwable -> L6
            PhotoCropActivity.m16790$$Nest$fgetdrawable(this.this$0).draw(r14);     // Catch: Throwable -> L6
        L6:
            th = move-exception;
            FileLog.e(th);
            goto L8
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            this.viewWidth = (r4 - r2) - AndroidUtilities.dp(28.0f);
            this.viewHeight = (r5 - r3) - AndroidUtilities.dp(28.0f);
            updateBitmapSize();
        }
    }

    /* loaded from: classes3.dex */
    public interface PhotoEditActivityDelegate {
        void didFinishEdit(Bitmap r1);
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PhotoEditActivityDelegate m16788$$Nest$fgetdelegate(PhotoCropActivity r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetdoneButtonPressed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16789$$Nest$fgetdoneButtonPressed(PhotoCropActivity r0) {
        return r0.doneButtonPressed;
    }

    /* renamed from: -$$Nest$fgetdrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BitmapDrawable m16790$$Nest$fgetdrawable(PhotoCropActivity r0) {
        return r0.drawable;
    }

    /* renamed from: -$$Nest$fgetimageToCrop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Bitmap m16791$$Nest$fgetimageToCrop(PhotoCropActivity r0) {
        return r0.imageToCrop;
    }

    /* renamed from: -$$Nest$fgetview, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PhotoCropView m16792$$Nest$fgetview(PhotoCropActivity r0) {
        return r0.view;
    }

    /* renamed from: -$$Nest$fputdoneButtonPressed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16793$$Nest$fputdoneButtonPressed(PhotoCropActivity r0, boolean r1) {
        r0.doneButtonPressed = r1;
    }

    /* renamed from: -$$Nest$fputsameBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16794$$Nest$fputsameBitmap(PhotoCropActivity r0, boolean r1) {
        r0.sameBitmap = r1;
    }

    public PhotoCropActivity(Bundle r1) {
        super(r1);
        this.delegate = null;
        this.sameBitmap = false;
        this.doneButtonPressed = false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r8) {
        this.actionBar.setBackgroundColor(-13421773);
        this.actionBar.setItemsBackgroundColor(-12763843, false);
        this.actionBar.setTitleColor(-1);
        this.actionBar.setItemsColor(-1, false);
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.CropImage));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        this.actionBar.createMenu().addItemWithWidth(1, R.drawable.ic_ab_done, AndroidUtilities.dp(56.0f), LocaleController.getString(R.string.Done));
        PhotoCropView r0 = new PhotoCropView(this, r8);
        this.view = r0;
        this.fragmentView = r0;
        r0.freeform = getArguments().getBoolean("freeform", false);
        this.fragmentView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        return this.fragmentView;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSwipeBackEnabled(MotionEvent r1) {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        if (this.imageToCrop != null) goto L19;
        String r0 = getArguments().getString("photoPath");
        Uri r2 = (Uri) getArguments().getParcelable("photoUri");
        if (r0 != null) goto L8;
        if (r2 != null) goto L8;
        return false;
    L8:
        if (r0 == null) goto L13;
        if (new File(r0).exists() == true) goto L13;
        return false;
    L13:
        if (AndroidUtilities.isTablet() == false) goto L15;
        int r4 = AndroidUtilities.dp(520.0f);
    L16:
        float r42 = r4;
        Bitmap r02 = ImageLoader.loadBitmap(r0, r2, r42, r42, true);
        this.imageToCrop = r02;
        if (r02 != null) goto L19;
        return false;
    L15:
        Point r43 = AndroidUtilities.displaySize;
        r4 = Math.max(r43.x, r43.y);
    L19:
        this.drawable = new BitmapDrawable(this.imageToCrop);
        super.onFragmentCreate();
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        if (this.bitmapKey == null) goto L10;
        if (ImageLoader.getInstance().decrementUseCount(this.bitmapKey) == false) goto L10;
        if (ImageLoader.getInstance().isInMemCache(this.bitmapKey, false) == true) goto L10;
        this.bitmapKey = null;
    L10:
        if (this.bitmapKey != null) goto L16;
        Bitmap r0 = this.imageToCrop;
        if (r0 == null) goto L16;
        if (this.sameBitmap == true) goto L16;
        r0.recycle();
        this.imageToCrop = null;
    L16:
        this.drawable = null;
    }

    public void setDelegate(PhotoEditActivityDelegate r1) {
        this.delegate = r1;
    }
}
