package org.telegram.ui.Components.Paint;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.LongSparseArray;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.core.util.Consumer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import org.mvel2.MVEL;
import org.mvel2.asm.Opcodes;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Paint.Views.PaintColorsListView;
import org.telegram.ui.Components.Paint.Views.PipettePickerView;
import org.telegram.ui.Components.ViewPagerFixed;

/* loaded from: classes7.dex */
public class ColorPickerBottomSheet extends BottomSheet {
    private AlphaPickerView alphaPickerView;
    private Consumer<Integer> colorListener;
    private ImageView doneView;
    private boolean initialized;
    private int mColor;
    private android.graphics.Path path;
    private ColorPickerView pickerView;
    private PipetteDelegate pipetteDelegate;
    private ImageView pipetteView;

    /* renamed from: org.telegram.ui.Components.Paint.ColorPickerBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends PipettePickerView {
        final /* synthetic */ ColorPickerBottomSheet this$0;

        public AnonymousClass1(ColorPickerBottomSheet r1, Context r2, Bitmap r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.Paint.Views.PipettePickerView
        public void onStartPipette() {
            ColorPickerBottomSheet.m11333$$Nest$fgetpipetteDelegate(this.this$0).onStartColorPipette();
        }

        @Override // org.telegram.ui.Components.Paint.Views.PipettePickerView
        public void onStopPipette() {
            ColorPickerBottomSheet.m11333$$Nest$fgetpipetteDelegate(this.this$0).onStopColorPipette();
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.ColorPickerBottomSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends ScrollView {
        final /* synthetic */ ColorPickerBottomSheet this$0;
        final /* synthetic */ LinearLayout val$linearLayout;

        public AnonymousClass2(ColorPickerBottomSheet r1, Context r2, LinearLayout r3) {
            this.this$0 = r1;
            this.val$linearLayout = r3;
            super(r2);
            setWillNotDraw(false);
        }

        @Override // android.view.View
        public void onDraw(Canvas r6) {
            super.onDraw(r6);
            float r0 = this.val$linearLayout.getY() + AndroidUtilities.dp(1.0f);
            int r1 = AndroidUtilities.dp(36.0f);
            RectF r2 = AndroidUtilities.rectTmp;
            r2.set((getMeasuredWidth() - r1) / 2.0f, r0, (getMeasuredWidth() + r1) / 2.0f, AndroidUtilities.dp(4.0f) + r0);
            Theme.dialogs_onlineCirclePaint.setColor(-10790053);
            r6.drawRoundRect(r2, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), Theme.dialogs_onlineCirclePaint);
        }

        @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            ((LinearLayout.LayoutParams) ColorPickerBottomSheet.m11332$$Nest$fgetpickerView(this.this$0).getLayoutParams()).height = (int) (((View.MeasureSpec.getSize(r1) - AndroidUtilities.dp(24.0f)) * 0.8333333f) + AndroidUtilities.dp(88.0f));
        }
    }

    public final class AlphaPickerView extends View {
        private float alpha;
        private Paint colorPaint;
        private Paint outlinePaint;
        final /* synthetic */ ColorPickerBottomSheet this$0;

        public AlphaPickerView(ColorPickerBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.colorPaint = new Paint(1);
            Paint r12 = new Paint(1);
            this.outlinePaint = r12;
            r12.setColor(-1);
            this.outlinePaint.setStyle(Paint.Style.FILL_AND_STROKE);
            this.outlinePaint.setStrokeWidth(AndroidUtilities.dp(3.0f));
        }

        private void invalidateShader() {
            this.colorPaint.setShader(new LinearGradient(0.0f, 0.0f, getWidth(), 0.0f, new int[]{0, ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$0)}, null, Shader.TileMode.CLAMP));
        }

        private void updatePosition(float r5) {
            float r0 = AndroidUtilities.dp(6.0f);
            this.alpha = MathUtils.clamp(((r5 - r0) + (AndroidUtilities.dp(13.0f) - (this.outlinePaint.getStrokeWidth() / 2.0f))) / (getWidth() - (r0 * 2.0f)), 0.0f, 1.0f);
            ColorPickerBottomSheet r52 = this.this$0;
            ColorPickerBottomSheet.m11336$$Nest$monSetColor(r52, ColorUtils.setAlphaComponent(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(r52), (int) (this.alpha * 255.0f)), 1);
            invalidate();
        }

        @Override // android.view.View
        public void onDraw(Canvas r13) {
            super.onDraw(r13);
            float r0 = getHeight() / 2.0f;
            float r3 = AndroidUtilities.dp(6.0f);
            RectF r4 = AndroidUtilities.rectTmp;
            float r5 = r0 - r3;
            float r7 = r0 + r3;
            r4.set(r3, r5, getWidth() - r3, r7);
            r13.save();
            ColorPickerBottomSheet.m11331$$Nest$fgetpath(this.this$0).rewind();
            ColorPickerBottomSheet.m11331$$Nest$fgetpath(this.this$0).addRoundRect(r4, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), Path.Direction.CW);
            r13.clipPath(ColorPickerBottomSheet.m11331$$Nest$fgetpath(this.this$0));
            PaintColorsListView.drawCheckerboard(r13, r4, AndroidUtilities.dp(6.0f));
            r13.restore();
            r4.set(r3, r5, getWidth() - r3, r7);
            r13.drawRoundRect(r4, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), this.colorPaint);
            float r2 = AndroidUtilities.dp(13.0f);
            float r42 = r2 - (this.outlinePaint.getStrokeWidth() / 2.0f);
            float r1 = Math.max(r3 + r42, (r3 + ((getWidth() - (2.0f * r3)) * this.alpha)) - r42);
            r13.drawCircle(r1, r0, r2, this.outlinePaint);
            PaintColorsListView.drawColorCircle(r13, r1, r0, r42, ColorUtils.setAlphaComponent(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$0), (int) (this.alpha * 255.0f)));
        }

        @Override // android.view.View
        public void onSizeChanged(int r1, int r2, int r3, int r4) {
            super.onSizeChanged(r1, r2, r3, r4);
            invalidateShader();
        }

        @Override // android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent r5) {
            int r0 = r5.getActionMasked();
            if (r0 != 0) goto L5;
            getParent().requestDisallowInterceptTouchEvent(true);
        L14:
            updatePosition(r5.getX());
        L15:
            return true;
        L5:
            if (r0 != 1) goto L7;
            updatePosition(r5.getX());
            getParent().requestDisallowInterceptTouchEvent(false);
            goto L15
        L7:
            if (r0 == 2) goto L14;
            if (r0 != 3) goto L15;
            getParent().requestDisallowInterceptTouchEvent(false);
            goto L15
        }

        public void setColor(int r2) {
            this.alpha = Color.alpha(r2) / 255.0f;
            invalidateShader();
            invalidate();
        }
    }

    public final class ColorPickerView extends LinearLayout {
        private GradientPickerView gradientPickerView;
        private GridPickerView gridPickerView;
        private SlidersPickerView slidersPickerView;
        private ViewPagerFixed.TabsView tabsView;
        final /* synthetic */ ColorPickerBottomSheet this$0;

        /* renamed from: org.telegram.ui.Components.Paint.ColorPickerBottomSheet$ColorPickerView$1, reason: invalid class name */
        public class AnonymousClass1 extends ViewPagerFixed {
            final /* synthetic */ ColorPickerView this$1;
            final /* synthetic */ ColorPickerBottomSheet val$this$0;

            public AnonymousClass1(ColorPickerView r1, Context r2, Theme.ResourcesProvider r3, ColorPickerBottomSheet r4) {
                this.this$1 = r1;
                this.val$this$0 = r4;
                super(r2, r3);
            }

            @Override // org.telegram.ui.Components.ViewPagerFixed
            public int tabMarginDp() {
                return 0;
            }
        }

        /* renamed from: org.telegram.ui.Components.Paint.ColorPickerBottomSheet$ColorPickerView$2, reason: invalid class name */
        public class AnonymousClass2 extends ViewPagerFixed.Adapter {
            final /* synthetic */ ColorPickerView this$1;
            final /* synthetic */ ColorPickerBottomSheet val$this$0;

            public AnonymousClass2(ColorPickerView r1, ColorPickerBottomSheet r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
            public void bindView(View r1, int r2, int r3) {
            }

            @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
            public View createView(int r2) {
                if (r2 == 1) goto L11;
                ColorPickerView r1 = this.this$1;
                if (r2 == 2) goto L9;
                return ColorPickerView.m11338$$Nest$fgetgridPickerView(r1);
            L9:
                return ColorPickerView.m11339$$Nest$fgetslidersPickerView(r1);
            L11:
                return ColorPickerView.m11337$$Nest$fgetgradientPickerView(this.this$1);
            }

            @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
            public int getItemCount() {
                return 3;
            }

            @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
            public /* bridge */ /* synthetic */ CharSequence getItemTitle(int r1) {
                return getItemTitle(r1);
            }

            @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
            public String getItemTitle(int r1) {
                if (r1 == 1) goto L11;
                if (r1 == 2) goto L9;
                return LocaleController.getString(R.string.PaintPaletteGrid).toUpperCase();
            L9:
                return LocaleController.getString(R.string.PaintPaletteSliders).toUpperCase();
            L11:
                return LocaleController.getString(R.string.PaintPaletteSpectrum).toUpperCase();
            }

            @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
            public int getItemViewType(int r1) {
                return r1;
            }
        }

        /* renamed from: -$$Nest$fgetgradientPickerView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ GradientPickerView m11337$$Nest$fgetgradientPickerView(ColorPickerView r0) {
            return r0.gradientPickerView;
        }

        /* renamed from: -$$Nest$fgetgridPickerView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ GridPickerView m11338$$Nest$fgetgridPickerView(ColorPickerView r0) {
            return r0.gridPickerView;
        }

        /* renamed from: -$$Nest$fgetslidersPickerView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SlidersPickerView m11339$$Nest$fgetslidersPickerView(ColorPickerView r0) {
            return r0.slidersPickerView;
        }

        public ColorPickerView(ColorPickerBottomSheet r12, Context r13) {
            this.this$0 = r12;
            super(r13);
            setOrientation(1);
            GridPickerView r0 = new GridPickerView(r12, r13);
            this.gridPickerView = r0;
            r0.setCurrentColor(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(r12));
            this.gradientPickerView = new GradientPickerView(r12, r13);
            this.slidersPickerView = new SlidersPickerView(r12, r13);
            AnonymousClass1 r02 = new AnonymousClass1(this, r13, ColorPickerBottomSheet.access$000(r12), r12);
            r02.setAdapter(new AnonymousClass2(this, r12));
            addView(r02, LayoutHelper.createLinear(-1, 0, 1.0f));
            addView(ColorPickerBottomSheet.m11328$$Nest$fgetalphaPickerView(r12), LayoutHelper.createLinear(-1, 48, 12.0f, 0.0f, 12.0f, 0.0f));
            LinearLayout r1 = new LinearLayout(r13);
            r1.setOrientation(0);
            r1.setGravity(16);
            r1.addView(ColorPickerBottomSheet.m11334$$Nest$fgetpipetteView(r12), LayoutHelper.createLinear(28, 28));
            ViewPagerFixed.TabsView r132 = r02.createTabsView(false, 8);
            this.tabsView = r132;
            r1.addView(r132, LayoutHelper.createLinear(-1, 40, 1.0f, 16, 12, 0, 12, 0));
            r1.addView(ColorPickerBottomSheet.m11329$$Nest$fgetdoneView(r12), LayoutHelper.createLinear(28, 28));
            addView(r1, LayoutHelper.createLinear(-1, 48, 14.0f, 0.0f, 14.0f, 0.0f));
        }
    }

    public final class ColorSliderView extends View {
        private Paint colorPaint;
        private int filledColor;
        private int mode;
        private Paint outlinePaint;
        final /* synthetic */ ColorPickerBottomSheet this$0;

        public ColorSliderView(ColorPickerBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.colorPaint = new Paint(1);
            Paint r12 = new Paint(1);
            this.outlinePaint = r12;
            r12.setColor(-1);
            this.outlinePaint.setStyle(Paint.Style.FILL_AND_STROKE);
            this.outlinePaint.setStrokeWidth(AndroidUtilities.dp(3.0f));
        }

        private void invalidateShader() {
            int r0 = this.mode;
            if (r0 == 1) goto L8;
            ColorPickerBottomSheet r1 = this.this$0;
            if (r0 == 2) goto L7;
            int r02 = Color.argb(255, 0, Color.green(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(r1)), Color.blue(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$0)));
            int r12 = Color.argb(255, 255, Color.green(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$0)), Color.blue(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$0)));
        L9:
            this.colorPaint.setShader(new LinearGradient(0.0f, 0.0f, getWidth(), 0.0f, new int[]{r02, r12}, null, Shader.TileMode.CLAMP));
            return;
        L7:
            r02 = Color.argb(255, Color.red(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(r1)), Color.green(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$0)), 0);
            r12 = Color.argb(255, Color.red(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$0)), Color.green(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$0)), 255);
            goto L9
        L8:
            r02 = Color.argb(255, Color.red(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$0)), 0, Color.blue(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$0)));
            r12 = Color.argb(255, Color.red(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$0)), 255, Color.blue(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$0)));
            goto L9
        }

        private void updatePosition(float r6) {
            float r0 = AndroidUtilities.dp(6.0f);
            float r62 = MathUtils.clamp(((r6 - r0) + (AndroidUtilities.dp(13.0f) - (this.outlinePaint.getStrokeWidth() / 2.0f))) / (getWidth() - (r0 * 2.0f)), 0.0f, 1.0f);
            int r02 = this.mode;
            if (r02 == 1) goto L8;
            ColorPickerBottomSheet r1 = this.this$0;
            if (r02 == 2) goto L7;
            int r63 = Color.argb(255, (int) (r62 * 255.0f), Color.green(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(r1)), Color.blue(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$0)));
        L9:
            ColorPickerBottomSheet r03 = this.this$0;
            ColorPickerBottomSheet.m11336$$Nest$monSetColor(r03, ColorUtils.setAlphaComponent(r63, Color.alpha(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(r03))), 4);
            invalidate();
            return;
        L7:
            r63 = Color.argb(255, Color.red(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(r1)), Color.green(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$0)), (int) (r62 * 255.0f));
            goto L9
        L8:
            r63 = Color.argb(255, Color.red(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$0)), (int) (r62 * 255.0f), Color.blue(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$0)));
            goto L9
        }

        public void invalidateColor() {
            this.filledColor = ColorUtils.setAlphaComponent(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$0), 255);
            invalidateShader();
            invalidate();
        }

        @Override // android.view.View
        public void onDraw(Canvas r9) {
            super.onDraw(r9);
            float r0 = getHeight() / 2.0f;
            float r2 = AndroidUtilities.dp(6.0f);
            RectF r3 = AndroidUtilities.rectTmp;
            r3.set(r2, r0 - r2, getWidth() - r2, r0 + r2);
            r9.drawRoundRect(r3, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), this.colorPaint);
            int r32 = this.mode;
            if (r32 == 1) goto L9;
            ColorPickerBottomSheet r4 = this.this$0;
            if (r32 == 2) goto L8;
            int r33 = Color.red(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(r4));
        L7:
            float r34 = r33 / 255.0f;
            float r42 = AndroidUtilities.dp(13.0f);
            float r5 = r42 - (this.outlinePaint.getStrokeWidth() / 2.0f);
            float r1 = Math.max(r2 + r5, (r2 + ((getWidth() - (2.0f * r2)) * r34)) - r5);
            r9.drawCircle(r1, r0, r42, this.outlinePaint);
            PaintColorsListView.drawColorCircle(r9, r1, r0, r5, this.filledColor);
            return;
        L8:
            r33 = Color.blue(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(r4));
            goto L7
        L9:
            r33 = Color.green(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$0));
            goto L7
        }

        @Override // android.view.View
        public void onSizeChanged(int r1, int r2, int r3, int r4) {
            super.onSizeChanged(r1, r2, r3, r4);
            invalidateShader();
        }

        @Override // android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent r5) {
            int r0 = r5.getActionMasked();
            if (r0 != 0) goto L5;
            getParent().requestDisallowInterceptTouchEvent(true);
        L14:
            updatePosition(r5.getX());
        L15:
            return true;
        L5:
            if (r0 != 1) goto L7;
            updatePosition(r5.getX());
            getParent().requestDisallowInterceptTouchEvent(false);
            goto L15
        L7:
            if (r0 == 2) goto L14;
            if (r0 != 3) goto L15;
            getParent().requestDisallowInterceptTouchEvent(false);
            goto L15
        }

        public void setMode(int r1) {
            this.mode = r1;
        }
    }

    public final class GradientPickerView extends View {
        private Paint gradientPaint;
        private float[] hsv;
        private Paint outlinePaint;
        private float positionX;
        private float positionY;
        final /* synthetic */ ColorPickerBottomSheet this$0;
        private Paint whiteBlackPaint;

        public GradientPickerView(ColorPickerBottomSheet r4, Context r5) {
            this.this$0 = r4;
            super(r5);
            this.gradientPaint = new Paint(1);
            this.whiteBlackPaint = new Paint(1);
            this.outlinePaint = new Paint(1);
            this.hsv = new float[3];
            setPadding(AndroidUtilities.dp(14.0f), AndroidUtilities.dp(3.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(3.0f));
            this.outlinePaint.setColor(-1);
            this.outlinePaint.setStyle(Paint.Style.FILL_AND_STROKE);
            this.outlinePaint.setStrokeWidth(AndroidUtilities.dp(3.0f));
        }

        private void updatePosition(MotionEvent r11) {
            this.positionX = (r11.getX() - getPaddingLeft()) / ((getWidth() - getPaddingLeft()) - getPaddingRight());
            float r112 = (r11.getY() - getPaddingTop()) / ((getHeight() - getPaddingTop()) - getPaddingBottom());
            this.positionY = r112;
            float[] r0 = this.hsv;
            r0[0] = r112 * 360.0f;
            float r113 = this.positionX;
            float r7 = 1.0f;
            if (r113 <= 0.22f) goto L9;
            if (r113 >= 0.78f) goto L9;
            r0[1] = 1.0f;
            r0[2] = 1.0f;
        L18:
            ColorPickerBottomSheet.m11335$$Nest$fputmColor(this.this$0, Color.HSVToColor(this.hsv));
            ColorPickerBottomSheet r114 = this.this$0;
            ColorPickerBottomSheet.m11336$$Nest$monSetColor(r114, ColorPickerBottomSheet.m11330$$Nest$fgetmColor(r114), 0);
            invalidate();
            return;
        L9:
            if (r113 > 0.22f) goto L12;
            float r115 = 1.0f - (r113 / 0.22f);
        L13:
            r0[1] = AndroidUtilities.lerp(1.0f, 0.0f, r115);
            float[] r116 = this.hsv;
            float r02 = this.positionX;
            if (r02 <= 0.22f) goto L17;
            r7 = AndroidUtilities.lerp(1.0f, 0.0f, (r02 - 0.78f) / 0.22000003f);
        L17:
            r116[2] = r7;
            goto L18
        L12:
            r115 = (r113 - 0.78f) / 0.22000003f;
            goto L13
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            super.onDraw(r8);
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
            r8.drawRoundRect(r0, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), this.gradientPaint);
            r8.drawRoundRect(r0, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), this.whiteBlackPaint);
            float r02 = AndroidUtilities.dp(13.0f);
            float r1 = r02 - (this.outlinePaint.getStrokeWidth() / 2.0f);
            float r2 = AndroidUtilities.dp(16.0f);
            int r3 = (getWidth() - getPaddingLeft()) - getPaddingRight();
            int r4 = (getHeight() - getPaddingTop()) - getPaddingBottom();
            float r32 = r3;
            float r5 = getPaddingLeft() + MathUtils.clamp(this.positionX * r32, r2, r32 - r2);
            float r42 = r4;
            float r33 = getPaddingTop() + MathUtils.clamp(this.positionY * r42, r2, r42 - r2);
            r8.drawCircle(r5, r33, r02, this.outlinePaint);
            PaintColorsListView.drawColorCircle(r8, r5, r33, r1, ColorUtils.setAlphaComponent(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$0), 255));
        }

        @Override // android.view.View
        public void onSizeChanged(int r9, int r10, int r11, int r12) {
            super.onSizeChanged(r9, r10, r11, r12);
            this.gradientPaint.setShader(new LinearGradient(0.0f, getPaddingTop(), 0.0f, r10 - getPaddingBottom(), new int[]{Opcodes.V_PREVIEW, -256, -16711936, -16711681, -16776961, -65281, Opcodes.V_PREVIEW}, null, Shader.TileMode.CLAMP));
            this.whiteBlackPaint.setShader(new LinearGradient(getPaddingLeft(), 0.0f, r9 - getPaddingRight(), 0.0f, new int[]{-1, 0, 0, -16777216}, new float[]{0.06f, 0.22f, 0.78f, 0.94f}, Shader.TileMode.MIRROR));
        }

        @Override // android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent r5) {
            int r0 = r5.getActionMasked();
            if (r0 != 0) goto L5;
            getParent().requestDisallowInterceptTouchEvent(true);
            updatePosition(r5);
        L15:
            return true;
        L5:
            if (r0 != 1) goto L7;
            getParent().requestDisallowInterceptTouchEvent(false);
            updatePosition(r5);
            goto L15
        L7:
            if (r0 != 2) goto L9;
            updatePosition(r5);
            goto L15
        L9:
            if (r0 != 3) goto L15;
            getParent().requestDisallowInterceptTouchEvent(false);
            goto L15
        }

        public void setColor(int r4, boolean r5) {
            ColorPickerBottomSheet.m11335$$Nest$fputmColor(this.this$0, r4);
            Color.colorToHSV(r4, this.hsv);
            if (r5 == false) goto L10;
            float[] r42 = this.hsv;
            float r52 = (r42[1] * 0.5f) + 1.0f;
            float r2 = r42[2];
            if (r2 > 0.5f) goto L8;
            float r0 = ((1.0f - r2) * 0.22000003f) + 0.78f;
        L9:
            this.positionX = r52 - (1.0f - r0);
            this.positionY = r42[0] / 360.0f;
            goto L10
        L8:
            r0 = (1.0f - r2) * 0.22f;
        L10:
            invalidate();
        }
    }

    public final class GridPickerView extends View {
        private Map<Long, Integer> colorMap;
        private final int[] colors;
        private Paint paint;
        private float[] radii;
        private long selected;
        private Paint selectorPaint;
        private android.graphics.Path selectorPath;
        private LongSparseArray<Float> selectors;
        final /* synthetic */ ColorPickerBottomSheet this$0;

        public GridPickerView(ColorPickerBottomSheet r10, Context r11) {
            this.this$0 = r10;
            super(r11);
            this.paint = new Paint(1);
            this.colors = new int[]{-16735784, -16752387, -11788361, -6804548, -4707235, -180718, -38656, -152832, -211200, -198077, -2495689, -8996289};
            this.selectorPaint = new Paint(1);
            this.selectors = new LongSparseArray();
            this.selected = Long.MIN_VALUE;
            this.selectorPath = new android.graphics.Path();
            this.radii = new float[8];
            this.colorMap = new HashMap();
            setPadding(AndroidUtilities.dp(14.0f), AndroidUtilities.dp(3.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(3.0f));
            this.selectorPaint.setColor(-1);
            this.selectorPaint.setStyle(Paint.Style.STROKE);
            this.selectorPaint.setStrokeCap(Paint.Cap.ROUND);
            this.selectorPaint.setStrokeJoin(Paint.Join.ROUND);
            int r1 = 0;
        L3:
            if (r1 >= 12) goto L17;
            int r2 = 0;
        L6:
            if (r2 >= 10) goto L16;
            if (r2 != 0) goto L10;
            this.colorMap.put(Long.valueOf((r1 << 16) + r2), Integer.valueOf(ColorUtils.blendARGB(-1, -16777216, r1 / 11.0f)));
        L15:
            r2 = r2 + 1;
            goto L6
        L10:
            int[] r4 = this.colors;
            if (r2 >= 6) goto L13;
            int r3 = ColorUtils.blendARGB(r4[r1], -16777216, ((5 - r2) / 4.0f) * 0.5f);
        L14:
            this.colorMap.put(Long.valueOf((r1 << 16) + r2), Integer.valueOf(r3));
            goto L15
        L13:
            r3 = ColorUtils.blendARGB(r4[r1], -1, 0.5f - (((9 - r2) / 5.0f) * 0.5f));
            goto L14
        L16:
            r1 = r1 + 1;
            goto L3
        }

        private void updatePosition(MotionEvent r7) {
            int r0 = ((getWidth() - getPaddingLeft()) - getPaddingRight()) / 12;
            int r1 = ((getHeight() - getPaddingTop()) - getPaddingBottom()) / 10;
            int r02 = (int) ((r7.getX() - getPaddingLeft()) / r0);
            int r72 = (int) (r7.getY() / r1);
            Integer r12 = this.colorMap.get(Long.valueOf((r02 << 16) + r72));
            if (r12 == null) goto L6;
            ColorPickerBottomSheet.m11336$$Nest$monSetColor(this.this$0, r12.intValue(), 3);
            setCurrentColor(r02, r72);
            return;
        }

        @Override // android.view.View
        public void onDraw(Canvas r21) {
            super.onDraw(r21);
            RectF r2 = AndroidUtilities.rectTmp;
            r2.set(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
            r21.save();
            ColorPickerBottomSheet.m11331$$Nest$fgetpath(this.this$0).rewind();
            float r4 = 10.0f;
            ColorPickerBottomSheet.m11331$$Nest$fgetpath(this.this$0).addRoundRect(r2, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), Path.Direction.CW);
            r21.clipPath(ColorPickerBottomSheet.m11331$$Nest$fgetpath(this.this$0));
            float r22 = ((getWidth() - getPaddingLeft()) - getPaddingRight()) / 12.0f;
            float r3 = ((getHeight() - getPaddingTop()) - getPaddingBottom()) / 10.0f;
            int r6 = 0;
        L4:
            if (r6 >= 12) goto L14;
            int r7 = 0;
        L7:
            if (r7 >= 10) goto L13;
            Integer r8 = this.colorMap.get(Long.valueOf((r6 << 16) + r7));
            if (r8 == null) goto L12;
            this.paint.setColor(r8.intValue());
            RectF r82 = AndroidUtilities.rectTmp;
            r82.set(getPaddingLeft() + (r6 * r22), getPaddingTop() + (r7 * r3), getPaddingLeft() + ((r6 + 1) * r22), getPaddingTop() + ((r7 + 1) * r3));
            r21.drawRect(r82, this.paint);
        L12:
            r7 = r7 + 1;
            goto L7
        L13:
            r6 = r6 + 1;
            goto L4
        L14:
            r21.restore();
            int r62 = 0;
        L16:
            if (r62 >= this.selectors.size()) goto L58;
            long r72 = this.selectors.keyAt(r62);
            float r9 = this.selectors.valueAt(r62).floatValue();
            if (this.selected != r72) goto L20;
            float r92 = Math.min(1.0f, r9 + 0.045714285f);
        L21:
            int r10 = (int) (r72 >> 16);
            int r13 = (int) (r72 - (r10 << 16));
            Integer r14 = this.colorMap.get(Long.valueOf(r72));
            if (r14 == null) goto L28;
            Paint r15 = this.selectorPaint;
            if (AndroidUtilities.computePerceivedBrightness(r14.intValue()) <= 0.721f) goto L26;
            int r142 = -15658735;
        L27:
            r15.setColor(r142);
            goto L28
        L26:
            r142 = -1;
        L28:
            float r17 = r4;
            this.selectorPaint.setStrokeWidth(CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation(r92) * AndroidUtilities.dp(3.0f));
            this.selectorPath.rewind();
            RectF r42 = AndroidUtilities.rectTmp;
            r42.set(getPaddingLeft() + (r10 * r22), getPaddingTop() + (r13 * r3), getPaddingLeft() + ((r10 + 1) * r22), getPaddingTop() + ((r13 + 1) * r3));
            float[] r5 = this.radii;
            if (r10 != 0) goto L32;
            if (r13 != 0) goto L32;
            float r11 = AndroidUtilities.dp(r17);
        L33:
            r5[1] = r11;
            r5[0] = r11;
            float[] r52 = this.radii;
            if (r10 != 11) goto L37;
            if (r13 != 0) goto L37;
            float r143 = AndroidUtilities.dp(r17);
        L38:
            r52[3] = r143;
            r52[2] = r143;
            float[] r53 = this.radii;
            if (r10 != 11) goto L42;
            if (r13 != 9) goto L42;
            float r112 = AndroidUtilities.dp(r17);
        L43:
            r53[5] = r112;
            r53[4] = r112;
            float[] r54 = this.radii;
            if (r10 != 0) goto L47;
            if (r13 != 9) goto L47;
            float r102 = AndroidUtilities.dp(r17);
        L48:
            r54[7] = r102;
            r54[6] = r102;
            this.selectorPath.addRoundRect(r42, this.radii, Path.Direction.CW);
            r21.drawPath(this.selectorPath, this.selectorPaint);
            if (r92 > 0.0f) goto L54;
            if (this.selected == r72) goto L54;
            this.selectors.removeAt(r62);
            r62 = r62 - 1;
            invalidate();
        L57:
            r62 = r62 + 1;
            r4 = r17;
        L54:
            if (r92 >= 1.0f) goto L56;
            invalidate();
        L56:
            this.selectors.setValueAt(r62, Float.valueOf(r92));
        L47:
            r102 = 0.0f;
        L42:
            r112 = 0.0f;
        L37:
            r143 = 0.0f;
        L32:
            r11 = 0.0f;
            goto L33
        L20:
            r92 = Math.max(0.0f, r9 - 0.10666667f);
            goto L21
        }

        @Override // android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent r4) {
            int r0 = r4.getActionMasked();
            if (r0 == 0) goto L13;
            if (r0 != 1) goto L6;
            updatePosition(r4);
        L12:
            getParent().requestDisallowInterceptTouchEvent(false);
        L14:
            return true;
        L6:
            if (r0 != 2) goto L8;
            updatePosition(r4);
            goto L14
        L8:
            if (r0 == 3) goto L12;
        L13:
            updatePosition(r4);
            getParent().requestDisallowInterceptTouchEvent(true);
            goto L14
        }

        public void setCurrentColor(int r5) {
            Iterator<Map.Entry<Long, Integer>> r0 = this.colorMap.entrySet().iterator();
        L4:
            if (r0.hasNext() == false) goto L9;
            Map.Entry<Long, Integer> r1 = r0.next();
            if (r1.getValue().intValue() != r5) goto L4;
            long r02 = r1.getKey().longValue();
            setCurrentColor((int) (r02 >> 16), (int) (r02 - (r5 << 16)));
            return;
        L9:
            this.selected = Long.MIN_VALUE;
            invalidate();
        }

        public void setCurrentColor(int r3, int r4) {
            long r0 = (r3 << 16) + r4;
            this.selected = r0;
            if (this.selectors.get(r0) != null) goto L5;
            this.selectors.put(this.selected, Float.valueOf(0.0f));
        L5:
            invalidate();
        }
    }

    public interface PipetteDelegate {
        ViewGroup getContainerView();

        View getSnapshotDrawingView();

        boolean isPipetteAvailable();

        boolean isPipetteVisible();

        void onColorSelected(int r1);

        void onDrawImageOverCanvas(Bitmap r1, Canvas r2);

        void onStartColorPipette();

        void onStopColorPipette();
    }

    public final class SliderCell extends FrameLayout {
        private boolean isInvalidatingColor;
        private int mode;
        private ColorSliderView sliderView;
        final /* synthetic */ ColorPickerBottomSheet this$0;
        private TextView titleView;
        private EditText valueView;

        /* renamed from: org.telegram.ui.Components.Paint.ColorPickerBottomSheet$SliderCell$1, reason: invalid class name */
        public class AnonymousClass1 implements TextWatcher {
            private CharSequence previous;
            final /* synthetic */ SliderCell this$1;
            final /* synthetic */ ColorPickerBottomSheet val$this$0;

            public AnonymousClass1(SliderCell r1, ColorPickerBottomSheet r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r4) {
                if (SliderCell.m11341$$Nest$fgetisInvalidatingColor(this.this$1) == false) goto L6;
                return;
            L6:
                if (this.previous == null) goto L21;
                if (r4 != null) goto L9;
                return;
            L9:
                if (TextUtils.isEmpty(r4) == false) goto L11;
                return;
            L11:
                if (Objects.equals(this.previous.toString(), r4.toString()) == true) goto L24;
                int r42 = MathUtils.clamp(Integer.parseInt(r4.toString()), 0, 255);
                int r0 = SliderCell.m11342$$Nest$fgetmode(this.this$1);
                if (r0 == 1) goto L18;
                SliderCell r1 = this.this$1;
                if (r0 == 2) goto L17;
                int r43 = Color.argb(Color.alpha(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(r1.this$0)), r42, Color.green(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$1.this$0)), Color.blue(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$1.this$0)));
            L19:
                ColorPickerBottomSheet.m11336$$Nest$monSetColor(this.this$1.this$0, r43, 5);
                return;
            L17:
                r43 = Color.argb(Color.alpha(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(r1.this$0)), Color.red(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$1.this$0)), Color.green(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$1.this$0)), r42);
                goto L19
            L18:
                r43 = Color.argb(Color.alpha(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$1.this$0)), Color.red(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$1.this$0)), r42, Color.blue(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$1.this$0)));
                goto L19
            L24:
                return;
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
                this.previous = r1.toString();
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        public static /* synthetic */ boolean $r8$lambda$eQW2gVHHzA80k4NfetuASsF7oAA(TextView r0, int r1, KeyEvent r2) {
            if (r1 != 6) goto L7;
            r0.clearFocus();
            AndroidUtilities.hideKeyboard(r0);
            return false;
        L7:
            return false;
        }

        /* renamed from: $r8$lambda$u8e7uYqoGEfoqGQTBZtKPpx-OAo, reason: not valid java name */
        public static /* synthetic */ void m11340$r8$lambda$u8e7uYqoGEfoqGQTBZtKPpxOAo(SliderCell r0, View r1, boolean r2) {
            r0.lambda$new$0(r1, r2);
        }

        /* renamed from: -$$Nest$fgetisInvalidatingColor, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m11341$$Nest$fgetisInvalidatingColor(SliderCell r0) {
            return r0.isInvalidatingColor;
        }

        /* renamed from: -$$Nest$fgetmode, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m11342$$Nest$fgetmode(SliderCell r0) {
            return r0.mode;
        }

        public SliderCell(ColorPickerBottomSheet r11, Context r12) {
            this.this$0 = r11;
            super(r12);
            TextView r0 = new TextView(r12);
            this.titleView = r0;
            r0.setTextColor(-1711276033);
            this.titleView.setTextSize(1, 14.0f);
            this.titleView.setTypeface(AndroidUtilities.bold());
            addView(this.titleView, LayoutHelper.createFrame(-2, -2.0f, 3, 8.0f, 0.0f, 8.0f, 0.0f));
            ColorSliderView r02 = new ColorSliderView(r11, r12);
            this.sliderView = r02;
            addView(r02, LayoutHelper.createFrame(-1, -1.0f, 3, 0.0f, 16.0f, 78.0f, 0.0f));
            EditTextBoldCursor r03 = new EditTextBoldCursor(r12);
            this.valueView = r03;
            r03.setTextSize(1, 16.0f);
            this.valueView.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(10.0f), 436207615));
            this.valueView.setPadding(0, 0, 0, 0);
            this.valueView.setTextColor(-1);
            this.valueView.setGravity(17);
            this.valueView.setSingleLine();
            this.valueView.setImeOptions(6);
            this.valueView.setImeActionLabel(LocaleController.getString(R.string.Done), 6);
            this.valueView.setInputType(2);
            this.valueView.setTypeface(AndroidUtilities.bold());
            this.valueView.addTextChangedListener(new AnonymousClass1(this, r11));
            this.valueView.setOnFocusChangeListener(new ColorPickerBottomSheet$SliderCell$$ExternalSyntheticLambda0(this));
            this.valueView.setOnEditorActionListener(new ColorPickerBottomSheet$SliderCell$$ExternalSyntheticLambda1());
            addView(this.valueView, LayoutHelper.createFrame(72, 36, 85));
        }

        private /* synthetic */ void lambda$new$0(View r1, boolean r2) {
            if (r2 == false) goto L4;
            return;
        L4:
            if (TextUtils.isEmpty(this.valueView.getText()) == false) goto L8;
            this.valueView.setText(MVEL.VERSION_SUB);
            return;
        }

        public void bind(int r2) {
            this.mode = r2;
            this.sliderView.setMode(r2);
            if (r2 != 0) goto L5;
            this.titleView.setText(LocaleController.getString(R.string.PaintPaletteSlidersRed).toUpperCase());
        L12:
            invalidateColor();
            return;
        L5:
            if (r2 != 1) goto L7;
            this.titleView.setText(LocaleController.getString(R.string.PaintPaletteSlidersGreen).toUpperCase());
            goto L12
        L7:
            if (r2 != 2) goto L12;
            this.titleView.setText(LocaleController.getString(R.string.PaintPaletteSlidersBlue).toUpperCase());
            goto L12
        }

        public void invalidateColor() {
            this.isInvalidatingColor = true;
            this.sliderView.invalidateColor();
            int r1 = this.valueView.getSelectionStart();
            int r2 = this.valueView.getSelectionEnd();
            int r3 = this.mode;
            if (r3 == 0) goto L10;
            if (r3 != 1) goto L6;
            this.valueView.setText(String.valueOf(Color.green(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$0))));
        L11:
            this.valueView.setSelection(r1, r2);
            this.isInvalidatingColor = false;
            return;
        L6:
            if (r3 != 2) goto L11;
            this.valueView.setText(String.valueOf(Color.blue(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$0))));
            goto L11
        L10:
            this.valueView.setText(String.valueOf(Color.red(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$0))));
            goto L11
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(52.0f), TLObject.FLAG_30));
        }
    }

    public final class SlidersPickerView extends LinearLayout {
        private SliderCell blue;
        private SliderCell green;
        private EditText hexEdit;
        private boolean isInvalidatingColor;
        private SliderCell red;
        final /* synthetic */ ColorPickerBottomSheet this$0;

        /* renamed from: org.telegram.ui.Components.Paint.ColorPickerBottomSheet$SlidersPickerView$1, reason: invalid class name */
        public class AnonymousClass1 implements TextWatcher {
            private Pattern pattern;
            private CharSequence previous;
            final /* synthetic */ SlidersPickerView this$1;
            final /* synthetic */ ColorPickerBottomSheet val$this$0;

            public AnonymousClass1(SlidersPickerView r1, ColorPickerBottomSheet r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
                this.pattern = Pattern.compile("^[0-9a-fA-F]*$");
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r6) {
                if (SlidersPickerView.m11345$$Nest$fgetisInvalidatingColor(this.this$1) == false) goto L6;
                return;
            L6:
                if (this.previous == null) goto L34;
                if (r6 != null) goto L9;
                return;
            L9:
                if (TextUtils.isEmpty(r6) == false) goto L11;
                return;
            L11:
                if (Objects.equals(this.previous.toString(), r6.toString()) == true) goto L37;
                String r0 = r6.toString();
                if (r0.length() <= 8) goto L17;
                SlidersPickerView.m11344$$Nest$fgethexEdit(this.this$1).setText(r0.substring(2, 8).toUpperCase());
                SlidersPickerView.m11344$$Nest$fgethexEdit(this.this$1).setSelection(8);
                return;
            L17:
                if (this.pattern.matcher(r6).find() == false) goto L39;
                int r62 = r0.length();
                if (r62 != 3) goto L22;
                long r02 = Long.parseLong("FF" + r0.charAt(0) + r0.charAt(0) + r0.charAt(1) + r0.charAt(1) + r0.charAt(2) + r0.charAt(2), 16);
            L26:
                int r63 = (int) r02;
            L30:
                if (r63 == ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$1.this$0)) goto L40;
                ColorPickerBottomSheet.m11336$$Nest$monSetColor(this.this$1.this$0, r63, 5);
                return;
            L40:
                return;
            L22:
                if (r62 == 6) goto L27;
                if (r62 == 8) goto L25;
                r63 = ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$1.this$0);
                goto L30
            L25:
                r02 = Long.parseLong(r0, 16);
                goto L26
            L27:
                r63 = ((int) Long.parseLong(r0, 16)) - 16777216;
                goto L30
            L39:
                return;
            L37:
                return;
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
                this.previous = r1.toString();
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        /* renamed from: $r8$lambda$dgJL_v5dE_Z0OrXHOIdPq-_i8J4, reason: not valid java name */
        public static /* synthetic */ void m11343$r8$lambda$dgJL_v5dE_Z0OrXHOIdPq_i8J4(SlidersPickerView r0, View r1, boolean r2) {
            r0.lambda$new$0(r1, r2);
        }

        public static /* synthetic */ boolean $r8$lambda$sB_NLW7ur4GL9oonFr5FtyFSJtw(TextView r0, int r1, KeyEvent r2) {
            if (r1 != 6) goto L7;
            r0.clearFocus();
            AndroidUtilities.hideKeyboard(r0);
            return false;
        L7:
            return false;
        }

        /* renamed from: -$$Nest$fgethexEdit, reason: not valid java name */
        public static /* bridge */ /* synthetic */ EditText m11344$$Nest$fgethexEdit(SlidersPickerView r0) {
            return r0.hexEdit;
        }

        /* renamed from: -$$Nest$fgetisInvalidatingColor, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m11345$$Nest$fgetisInvalidatingColor(SlidersPickerView r0) {
            return r0.isInvalidatingColor;
        }

        public SlidersPickerView(ColorPickerBottomSheet r14, Context r15) {
            this.this$0 = r14;
            super(r15);
            setOrientation(1);
            setPadding(AndroidUtilities.dp(14.0f), 0, AndroidUtilities.dp(14.0f), 0);
            SliderCell r1 = new SliderCell(r14, r15);
            this.red = r1;
            r1.bind(0);
            addView(this.red, LayoutHelper.createLinear(-1, -2, 0.0f, 0, 0, 0, 0, 16));
            SliderCell r12 = new SliderCell(r14, r15);
            this.green = r12;
            r12.bind(1);
            addView(this.green, LayoutHelper.createLinear(-1, -2, 0.0f, 0, 0, 0, 0, 16));
            SliderCell r13 = new SliderCell(r14, r15);
            this.blue = r13;
            r13.bind(2);
            addView(this.blue, LayoutHelper.createLinear(-1, -2, 0.0f, 0, 0, 0, 0, 16));
            LinearLayout r16 = new LinearLayout(r15);
            r16.setOrientation(0);
            r16.setGravity(21);
            addView(r16, LayoutHelper.createFrame(-1, 64.0f));
            TextView r2 = new TextView(r15);
            r2.setTextColor(-1711276033);
            r2.setTextSize(1, 16.0f);
            r2.setText(LocaleController.getString(R.string.PaintPaletteSlidersHexColor).toUpperCase());
            r2.setTypeface(AndroidUtilities.bold());
            r16.addView(r2, LayoutHelper.createLinear(-2, -2, 0.0f, 0.0f, 8.0f, 0.0f));
            EditTextBoldCursor r22 = new EditTextBoldCursor(r15);
            this.hexEdit = r22;
            r22.setTextSize(1, 16.0f);
            this.hexEdit.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(10.0f), 436207615));
            this.hexEdit.setPadding(0, 0, 0, 0);
            this.hexEdit.setTextColor(-1);
            this.hexEdit.setGravity(17);
            this.hexEdit.setSingleLine();
            this.hexEdit.setImeOptions(6);
            this.hexEdit.setImeActionLabel(LocaleController.getString(R.string.Done), 6);
            this.hexEdit.setTypeface(AndroidUtilities.bold());
            this.hexEdit.addTextChangedListener(new AnonymousClass1(this, r14));
            this.hexEdit.setOnFocusChangeListener(new ColorPickerBottomSheet$SlidersPickerView$$ExternalSyntheticLambda0(this));
            this.hexEdit.setOnEditorActionListener(new ColorPickerBottomSheet$SlidersPickerView$$ExternalSyntheticLambda1());
            r16.addView(this.hexEdit, LayoutHelper.createLinear(72, 36));
        }

        private /* synthetic */ void lambda$new$0(View r1, boolean r2) {
            if (r2 == false) goto L4;
            return;
        L4:
            if (TextUtils.isEmpty(this.hexEdit.getText()) == false) goto L8;
            this.hexEdit.setText(MVEL.VERSION_SUB);
            return;
        }

        public void invalidateColor() {
            this.isInvalidatingColor = true;
            this.red.invalidateColor();
            this.green.invalidateColor();
            this.blue.invalidateColor();
            if (this.hexEdit.isFocused() == true) goto L9;
            int r0 = this.hexEdit.getSelectionStart();
            int r2 = this.hexEdit.getSelectionEnd();
            StringBuilder r3 = new StringBuilder(Integer.toHexString(ColorPickerBottomSheet.m11330$$Nest$fgetmColor(this.this$0)));
        L6:
            if (r3.length() >= 8) goto L8;
            r3.insert(0, MVEL.VERSION_SUB);
            goto L6
        L8:
            this.hexEdit.setText(r3.toString().toUpperCase().substring(2));
            this.hexEdit.setSelection(r0, r2);
        L9:
            this.isInvalidatingColor = false;
        }
    }

    public static /* synthetic */ void $r8$lambda$IZwLGiqnu6nATpssuiyZVVKtspc(ColorPickerBottomSheet r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$lRPjzFP6AbqG2P4KMHCn1s6b0II(ColorPickerBottomSheet r0, Context r1, View r2) {
        r0.lambda$new$0(r1, r2);
    }

    /* renamed from: -$$Nest$fgetalphaPickerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AlphaPickerView m11328$$Nest$fgetalphaPickerView(ColorPickerBottomSheet r0) {
        return r0.alphaPickerView;
    }

    /* renamed from: -$$Nest$fgetdoneView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m11329$$Nest$fgetdoneView(ColorPickerBottomSheet r0) {
        return r0.doneView;
    }

    /* renamed from: -$$Nest$fgetmColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11330$$Nest$fgetmColor(ColorPickerBottomSheet r0) {
        return r0.mColor;
    }

    /* renamed from: -$$Nest$fgetpath, reason: not valid java name */
    public static /* bridge */ /* synthetic */ android.graphics.Path m11331$$Nest$fgetpath(ColorPickerBottomSheet r0) {
        return r0.path;
    }

    /* renamed from: -$$Nest$fgetpickerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ColorPickerView m11332$$Nest$fgetpickerView(ColorPickerBottomSheet r0) {
        return r0.pickerView;
    }

    /* renamed from: -$$Nest$fgetpipetteDelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PipetteDelegate m11333$$Nest$fgetpipetteDelegate(ColorPickerBottomSheet r0) {
        return r0.pipetteDelegate;
    }

    /* renamed from: -$$Nest$fgetpipetteView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m11334$$Nest$fgetpipetteView(ColorPickerBottomSheet r0) {
        return r0.pipetteView;
    }

    /* renamed from: -$$Nest$fputmColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11335$$Nest$fputmColor(ColorPickerBottomSheet r0, int r1) {
        r0.mColor = r1;
    }

    /* renamed from: -$$Nest$monSetColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11336$$Nest$monSetColor(ColorPickerBottomSheet r0, int r1, int r2) {
        r0.onSetColor(r1, r2);
    }

    public ColorPickerBottomSheet(Context r7, Theme.ResourcesProvider r8) {
        super(r7, true, r8);
        this.path = new android.graphics.Path();
        fixNavigationBar(-14342875);
        Drawable r1 = r7.getResources().getDrawable(R.drawable.sheet_shadow_round).mutate();
        this.shadowDrawable = r1;
        r1.setColorFilter(new PorterDuffColorFilter(-14342875, PorterDuff.Mode.MULTIPLY));
        LinearLayout r82 = new LinearLayout(r7);
        r82.setOrientation(1);
        r82.setPadding(0, AndroidUtilities.dp(16.0f), 0, 0);
        ImageView r0 = new ImageView(r7);
        this.pipetteView = r0;
        r0.setImageResource(R.drawable.picker);
        ImageView r02 = this.pipetteView;
        PorterDuff.Mode r3 = PorterDuff.Mode.SRC_IN;
        r02.setColorFilter(new PorterDuffColorFilter(-1, r3));
        this.pipetteView.setBackground(Theme.createSelectorDrawable(1090519039));
        this.pipetteView.setOnClickListener(new ColorPickerBottomSheet$$ExternalSyntheticLambda0(this, r7));
        ImageView r03 = new ImageView(r7);
        this.doneView = r03;
        r03.setImageResource(R.drawable.ic_ab_done);
        this.doneView.setColorFilter(new PorterDuffColorFilter(-1, r3));
        this.doneView.setBackground(Theme.createSelectorDrawable(1090519039));
        this.doneView.setOnClickListener(new ColorPickerBottomSheet$$ExternalSyntheticLambda1(this));
        AlphaPickerView r04 = new AlphaPickerView(this, r7);
        this.alphaPickerView = r04;
        r04.setColor(Opcodes.V_PREVIEW);
        ColorPickerView r05 = new ColorPickerView(this, r7);
        this.pickerView = r05;
        r82.addView(r05, LayoutHelper.createLinear(-1, 0));
        AnonymousClass2 r06 = new AnonymousClass2(this, r7, r82);
        r06.addView(r82);
        setCustomView(r06);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$000(ColorPickerBottomSheet r0) {
        return r0.resourcesProvider;
    }

    private /* synthetic */ void lambda$new$0(Context r5, View r6) {
        if (this.pipetteDelegate.isPipetteVisible() == false) goto L5;
        return;
    L5:
        Bitmap r62 = AndroidUtilities.snapshotView(this.pipetteDelegate.getSnapshotDrawingView());
        Bitmap r0 = Bitmap.createBitmap(r62.getWidth(), r62.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas r1 = new Canvas(r0);
        r1.drawColor(-16777216);
        this.pipetteDelegate.onDrawImageOverCanvas(r0, r1);
        r1.drawBitmap(r62, 0.0f, 0.0f, null);
        r62.recycle();
        AnonymousClass1 r63 = new AnonymousClass1(this, r5, r0);
        this.pipetteDelegate.getContainerView().addView(r63, LayoutHelper.createFrame(-1, -1.0f));
        PipetteDelegate r52 = this.pipetteDelegate;
        Objects.requireNonNull(r52);
        r63.setColorListener(new ColorPickerBottomSheet$$ExternalSyntheticLambda2(r52));
        r63.animateShow();
        dismiss();
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        dismiss();
    }

    private void onSetColor(int r4, int r5) {
        if (this.initialized == true) goto L9;
        if (r5 == 2) goto L7;
        return;
    L7:
        this.initialized = true;
    L9:
        if (r5 == 5) goto L14;
        View r0 = this.pickerView.findFocus();
        if (r0 == null) goto L14;
        r0.clearFocus();
        AndroidUtilities.hideKeyboard(r0);
    L14:
        if (r5 == 3) goto L16;
        ColorPickerView.m11338$$Nest$fgetgridPickerView(this.pickerView).setCurrentColor(r4);
    L16:
        if (r5 == 0) goto L22;
        GradientPickerView r02 = ColorPickerView.m11337$$Nest$fgetgradientPickerView(this.pickerView);
        if (r5 == 1) goto L20;
        boolean r2 = true;
    L21:
        r02.setColor(r4, r2);
        goto L22
    L20:
        r2 = false;
    L22:
        if (r5 == 1) goto L24;
        this.alphaPickerView.setColor(r4);
    L24:
        ColorPickerView.m11339$$Nest$fgetslidersPickerView(this.pickerView).invalidateColor();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        super.dismiss();
        Consumer<Integer> r0 = this.colorListener;
        if (r0 == null) goto L6;
        r0.accept(Integer.valueOf(this.mColor));
        return;
    }

    public ColorPickerBottomSheet setColor(int r2) {
        onSetColor(r2, 2);
        return this;
    }

    public ColorPickerBottomSheet setColorListener(Consumer<Integer> r1) {
        this.colorListener = r1;
        return this;
    }

    public ColorPickerBottomSheet setPipetteDelegate(PipetteDelegate r1) {
        this.pipetteDelegate = r1;
        return this;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        if (this.pipetteDelegate.isPipetteAvailable() == true) goto L5;
        this.pipetteView.setVisibility(8);
    L5:
        super.show();
    }
}
