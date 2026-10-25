package org.telegram.ui.Stories.recorder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.GradientClip;
import org.telegram.ui.Stories.recorder.CollageLayout;

/* loaded from: classes7.dex */
public class CollageLayoutButton extends ToggleButton2 {

    public static class CollageLayoutDrawable extends Drawable {
        private boolean cross;
        public final Paint crossPaint;
        public final Paint crossXferPaint;
        public final Paint paint;
        public final Path path;
        private final float[] radii;

        public CollageLayoutDrawable(CollageLayout r2) {
            this(r2, false);
        }

        public CollageLayoutDrawable(CollageLayout r25, boolean r26) {
            Paint r2 = new Paint(1);
            this.paint = r2;
            this.crossXferPaint = new Paint(1);
            this.crossPaint = new Paint(1);
            Path r4 = new Path();
            this.path = r4;
            this.radii = new float[8];
            this.cross = r26;
            r2.setColor(-1);
            float r22 = AndroidUtilities.dpf2(13.333333f);
            float r6 = AndroidUtilities.dpf2(18.666666f);
            float r7 = AndroidUtilities.dpf2(3.0f);
            float r8 = AndroidUtilities.dpf2(10.0f);
            float r9 = AndroidUtilities.dpf2(15.333333f);
            float r10 = AndroidUtilities.dpf2(1.0f);
            float r11 = 1.33f;
            float r12 = AndroidUtilities.dpf2(1.33f);
            r4.setFillType(Path.FillType.EVEN_ODD);
            RectF r13 = AndroidUtilities.rectTmp;
            float r15 = 2.0f;
            r13.set((-r22) / 2.0f, (-r6) / 2.0f, r22 / 2.0f, r6 / 2.0f);
            r4.addRoundRect(r13, r7, r7, Path.Direction.CW);
            ArrayList<CollageLayout.Part> r23 = r25.parts;
            int r3 = r23.size();
            int r42 = 0;
            int r62 = 0;
        L3:
            if (r62 >= r3) goto L28;
            CollageLayout.Part r72 = r23.get(r62);
            int r63 = r62 + 1;
            CollageLayout.Part r73 = r72;
            int r132 = r25.columns[r73.y];
            int r14 = r132 - 1;
            float r262 = r11;
            float r112 = (r8 - (Math.max(r42, r14) * r12)) / r132;
            int r17 = r42;
            float r133 = (r9 - (Math.max(r42, r25.h - 1) * r12)) / r25.h;
            RectF r43 = AndroidUtilities.rectTmp;
            float r18 = r15;
            float r152 = (-r8) / r18;
            int r5 = r73.x;
            ArrayList<CollageLayout.Part> r19 = r23;
            int r20 = r3;
            float r32 = (-r9) / r18;
            int r33 = r73.y;
            float r232 = r8;
            r43.set(((r5 * r112) + r152) + (r5 * r12), (r32 + (r33 * r133)) + (r33 * r12), (r152 + (r112 * (r5 + 1))) + (r5 * r12), (r32 + (r133 * (r33 + 1))) + (r33 * r12));
            float[] r24 = this.radii;
            int r34 = r73.x;
            float r52 = 0.0f;
            if (r34 == 0) goto L7;
        L9:
            float r64 = 0.0f;
        L10:
            r24[1] = r64;
            r24[r17] = r64;
            if (r34 == r14) goto L13;
        L15:
            float r65 = 0.0f;
        L16:
            r24[3] = r65;
            r24[2] = r65;
            if (r34 == r14) goto L19;
        L21:
            float r66 = 0.0f;
        L22:
            r24[5] = r66;
            r24[4] = r66;
            if (r34 != 0) goto L27;
            if (r73.y != (r25.h - 1)) goto L27;
            r52 = r10;
        L27:
            r24[7] = r52;
            r24[6] = r52;
            this.path.addRoundRect(r43, r24, Path.Direction.CW);
            r11 = r262;
            r42 = r17;
            r15 = r18;
            r23 = r19;
            r3 = r20;
            r62 = r63;
            r8 = r232;
            goto L3
        L19:
            if (r73.y != (r25.h - 1)) goto L21;
            r66 = r10;
            goto L22
        L13:
            if (r73.y != 0) goto L15;
            r65 = r10;
            goto L16
        L7:
            if (r73.y != 0) goto L9;
            r64 = r10;
            goto L10
        L28:
            Paint r1 = this.crossXferPaint;
            Paint.Style r27 = Paint.Style.STROKE;
            r1.setStyle(r27);
            this.crossXferPaint.setStrokeWidth(AndroidUtilities.dp(3.33f));
            this.crossXferPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            this.crossPaint.setStyle(r27);
            this.crossPaint.setStrokeWidth(AndroidUtilities.dp(r11));
            this.crossPaint.setColor(-1);
            this.crossPaint.setStrokeCap(Paint.Cap.ROUND);
            this.crossPaint.setStrokeJoin(Paint.Join.ROUND);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r15) {
            if (this.cross == false) goto L5;
            r15.saveLayerAlpha(getBounds().left, getBounds().top, getBounds().right, getBounds().bottom, 255, 31);
            Canvas r8 = r15;
        L6:
            r8.translate(getBounds().centerX(), getBounds().centerY());
            r8.drawPath(this.path, this.paint);
            if (this.cross == false) goto L9;
            r8.drawLine(-AndroidUtilities.dp(8.66f), -AndroidUtilities.dp(8.66f), AndroidUtilities.dp(8.66f), AndroidUtilities.dp(8.66f), this.crossXferPaint);
            r8.drawLine(-AndroidUtilities.dp(8.66f), -AndroidUtilities.dp(8.66f), AndroidUtilities.dp(8.66f), AndroidUtilities.dp(8.66f), this.crossPaint);
        L9:
            r8.restore();
            return;
        L5:
            r8 = r15;
            r8.save();
            goto L6
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return AndroidUtilities.dp(32.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return AndroidUtilities.dp(32.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
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

    public static class CollageLayoutListView extends FrameLayout {
        public final RecyclerListView listView;
        private Utilities.Callback<CollageLayout> onLayoutClick;
        private CollageLayout selectedLayout;
        private boolean visible;
        private ValueAnimator visibleAnimator;
        private float visibleProgress;

        /* renamed from: org.telegram.ui.Stories.recorder.CollageLayoutButton$CollageLayoutListView$1, reason: invalid class name */
        public class AnonymousClass1 extends RecyclerListView {
            private final GradientClip clip;
            final /* synthetic */ CollageLayoutListView this$0;

            public AnonymousClass1(CollageLayoutListView r1, Context r2) {
                this.this$0 = r1;
                super(r2);
                this.clip = new GradientClip();
            }

            @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
            public void dispatchDraw(Canvas r9) {
                r9.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), (int) (CollageLayoutListView.m19904$$Nest$fgetvisibleProgress(this.this$0) * 255.0f), 31);
                r9.save();
                float r92 = getPaddingLeft();
                float r0 = getWidth() - getPaddingRight();
                r9.clipRect(r92, 0.0f, r0, getHeight());
                r9.translate((1.0f - CollageLayoutListView.m19904$$Nest$fgetvisibleProgress(this.this$0)) * r0, 0.0f);
                super.dispatchDraw(r9);
                r9.restore();
                r9.save();
                RectF r2 = AndroidUtilities.rectTmp;
                r2.set(r92, 0.0f, AndroidUtilities.dp(12.0f) + r92, getHeight());
                this.clip.draw(r9, r2, 0, CollageLayoutListView.m19904$$Nest$fgetvisibleProgress(this.this$0));
                r2.set(r0 - AndroidUtilities.dp(12.0f), 0.0f, r0, getHeight());
                this.clip.draw(r9, r2, 2, CollageLayoutListView.m19904$$Nest$fgetvisibleProgress(this.this$0));
                r9.restore();
                r9.restore();
            }

            @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
            public boolean dispatchTouchEvent(MotionEvent r4) {
                if (r4.getX() > getPaddingLeft()) goto L5;
                return false;
            L5:
                if (r4.getX() < (getWidth() - getPaddingRight())) goto L8;
                return false;
            L8:
                return super.dispatchTouchEvent(r4);
            }

            @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
            public boolean onInterceptTouchEvent(MotionEvent r4) {
                if (r4.getX() > getPaddingLeft()) goto L5;
                return false;
            L5:
                if (r4.getX() >= (getWidth() - getPaddingRight())) goto L11;
                getParent().requestDisallowInterceptTouchEvent(true);
                return super.onInterceptTouchEvent(r4);
            L11:
                return false;
            }
        }

        /* renamed from: org.telegram.ui.Stories.recorder.CollageLayoutButton$CollageLayoutListView$2, reason: invalid class name */
        public class AnonymousClass2 extends RecyclerView.Adapter {
            final /* synthetic */ CollageLayoutListView this$0;
            final /* synthetic */ Context val$context;
            final /* synthetic */ FlashViews val$flashViews;

            public AnonymousClass2(CollageLayoutListView r1, Context r2, FlashViews r3) {
                this.this$0 = r1;
                this.val$context = r2;
                this.val$flashViews = r3;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                return CollageLayout.getLayouts().size();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onBindViewHolder(RecyclerView.ViewHolder r4, int r5) {
                Button r42 = (Button) r4.itemView;
                CollageLayout r0 = CollageLayout.getLayouts().get(r5);
                if (r5 != r42.position) goto L5;
                boolean r1 = true;
            L6:
                r42.setDrawable(new CollageLayoutDrawable(r0));
                r42.setSelected(r0.equals(CollageLayoutListView.m19903$$Nest$fgetselectedLayout(this.this$0)), r1);
                r42.position = r5;
                return;
            L5:
                r1 = false;
                goto L6
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
                Button r22 = new Button(this.val$context);
                r22.setLayoutParams(new RecyclerView.LayoutParams(AndroidUtilities.dp(46.0f), AndroidUtilities.dp(56.0f)));
                r22.setBackground(Theme.createSelectorDrawable(553648127));
                return new RecyclerListView.Holder(r22);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onViewAttachedToWindow(RecyclerView.ViewHolder r4) {
                Button r0 = (Button) r4.itemView;
                this.val$flashViews.add(r0);
                int r1 = r0.position;
                if (r1 >= 0) goto L5;
            L7:
                super.onViewAttachedToWindow(r4);
                return;
            L5:
                if (r1 >= CollageLayout.getLayouts().size()) goto L7;
                CollageLayout r12 = CollageLayout.getLayouts().get(r0.position);
                r0.setDrawable(new CollageLayoutDrawable(r12));
                r0.setSelected(r12.equals(CollageLayoutListView.m19903$$Nest$fgetselectedLayout(this.this$0)), false);
                goto L7
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onViewDetachedFromWindow(RecyclerView.ViewHolder r3) {
                this.val$flashViews.remove((Button) r3.itemView);
                super.onViewDetachedFromWindow(r3);
            }
        }

        /* renamed from: org.telegram.ui.Stories.recorder.CollageLayoutButton$CollageLayoutListView$3, reason: invalid class name */
        public class AnonymousClass3 extends AnimatorListenerAdapter {
            final /* synthetic */ CollageLayoutListView this$0;
            final /* synthetic */ boolean val$visible;

            public AnonymousClass3(CollageLayoutListView r1, boolean r2) {
                this.this$0 = r1;
                this.val$visible = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                CollageLayoutListView r22 = this.this$0;
                if (this.val$visible == false) goto L5;
                float r0 = 1.0f;
            L6:
                CollageLayoutListView.m19905$$Nest$fputvisibleProgress(r22, r0);
                this.this$0.listView.invalidate();
                RecyclerListView r23 = this.this$0.listView;
                if (this.val$visible == false) goto L9;
                int r1 = 0;
            L10:
                r23.setVisibility(r1);
                return;
            L9:
                r1 = 8;
                goto L10
            L5:
                r0 = 0.0f;
                goto L6
            }
        }

        public static class Button extends ToggleButton2 {
            public int position;

            public Button(Context r1) {
                super(r1);
            }
        }

        /* renamed from: $r8$lambda$l13PScWi4dK2-TbPSqwousuHFR0, reason: not valid java name */
        public static /* synthetic */ void m19902$r8$lambda$l13PScWi4dK2TbPSqwousuHFR0(CollageLayoutListView r0, ValueAnimator r1) {
            r0.lambda$setVisible$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$xw2rE59qgKAdAbJFbAVFlCSoeLQ(CollageLayoutListView r0, View r1, int r2) {
            r0.lambda$new$0(r1, r2);
        }

        /* renamed from: -$$Nest$fgetselectedLayout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ CollageLayout m19903$$Nest$fgetselectedLayout(CollageLayoutListView r0) {
            return r0.selectedLayout;
        }

        /* renamed from: -$$Nest$fgetvisibleProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m19904$$Nest$fgetvisibleProgress(CollageLayoutListView r0) {
            return r0.visibleProgress;
        }

        /* renamed from: -$$Nest$fputvisibleProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m19905$$Nest$fputvisibleProgress(CollageLayoutListView r0, float r1) {
            r0.visibleProgress = r1;
        }

        public CollageLayoutListView(Context r3, FlashViews r4) {
            super(r3);
            AnonymousClass1 r0 = new AnonymousClass1(this, r3);
            this.listView = r0;
            r0.setAdapter(new AnonymousClass2(this, r3, r4));
            r0.setLayoutManager(new LinearLayoutManager(r3, 0, false));
            r0.setClipToPadding(false);
            r0.setVisibility(8);
            r0.setWillNotDraw(false);
            r0.setOnItemClickListener(new CollageLayoutButton$CollageLayoutListView$$ExternalSyntheticLambda1(this));
            addView(r0, LayoutHelper.createFrame(-1, 56.0f));
        }

        private /* synthetic */ void lambda$new$0(View r1, int r2) {
            Utilities.Callback<CollageLayout> r0 = this.onLayoutClick;
            if (r0 == null) goto L6;
            r0.run(CollageLayout.getLayouts().get(r2));
            return;
        }

        private /* synthetic */ void lambda$setVisible$1(ValueAnimator r1) {
            this.visibleProgress = ((Float) r1.getAnimatedValue()).floatValue();
            this.listView.invalidate();
        }

        public boolean isVisible() {
            return this.visible;
        }

        public void setBounds(float r3, float r4) {
            this.listView.setPadding((int) r3, 0, (int) r4, 0);
            this.listView.invalidate();
        }

        public void setOnLayoutClick(Utilities.Callback<CollageLayout> r1) {
            this.onLayoutClick = r1;
        }

        public void setSelected(CollageLayout r1) {
            this.selectedLayout = r1;
            AndroidUtilities.updateVisibleRows(this.listView);
        }

        public void setVisible(boolean r4, boolean r5) {
            ValueAnimator r0 = this.visibleAnimator;
            if (r0 == null) goto L6;
            r0.cancel();
        L6:
            if (this.visible != r4) goto L8;
            return;
        L8:
            this.visible = r4;
            float r02 = 0.0f;
            int r2 = 0;
            if (r5 == false) goto L15;
            this.listView.setVisibility(0);
            float r52 = this.visibleProgress;
            if (r4 == false) goto L13;
            r02 = 1.0f;
        L13:
            ValueAnimator r53 = ValueAnimator.ofFloat(new float[]{r52, r02});
            this.visibleAnimator = r53;
            r53.addUpdateListener(new CollageLayoutButton$CollageLayoutListView$$ExternalSyntheticLambda0(this));
            this.visibleAnimator.addListener(new AnonymousClass3(this, r4));
            this.visibleAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.visibleAnimator.setDuration(340);
            this.visibleAnimator.start();
            return;
        L15:
            if (r4 == false) goto L17;
            r02 = 1.0f;
        L17:
            this.visibleProgress = r02;
            this.listView.invalidate();
            RecyclerListView r3 = this.listView;
            if (r4 == true) goto L21;
            r2 = 8;
        L21:
            r3.setVisibility(r2);
        }
    }

    public CollageLayoutButton(Context r1) {
        super(r1);
    }
}
