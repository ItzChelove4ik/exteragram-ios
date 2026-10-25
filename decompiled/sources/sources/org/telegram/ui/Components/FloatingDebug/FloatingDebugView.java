package org.telegram.ui.Components.FloatingDebug;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.core.view.GestureDetectorCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.BlurSettingsBottomSheet;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Components.AnimationProperties;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.FloatingDebug.FloatingDebugController;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SeekBarView;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes7.dex */
public class FloatingDebugView extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    private LinearLayout bigLayout;
    private List<FloatingDebugController.DebugItem> debugItems;
    private SpringAnimation fabXSpring;
    private SpringAnimation fabYSpring;
    private Drawable floatingButtonBackground;
    private FrameLayout floatingButtonContainer;
    private GestureDetector.OnGestureListener gestureListener;
    private boolean inLongPress;
    private boolean isBigMenuShown;
    private boolean isFromFling;
    private boolean isScrollDisallowed;
    private boolean isScrolling;
    private RecyclerListView listView;
    private SharedPreferences mPrefs;
    private Runnable onLongPress;
    private TextView titleView;
    private int touchSlop;
    private int wasStatusBar;

    /* renamed from: org.telegram.ui.Components.FloatingDebug.FloatingDebugView$1, reason: invalid class name */
    public class AnonymousClass1 extends GestureDetector.SimpleOnGestureListener {
        private float startX;
        private float startY;
        final /* synthetic */ FloatingDebugView this$0;

        public AnonymousClass1(FloatingDebugView r1) {
            this.this$0 = r1;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent r1) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent r3, MotionEvent r4, float r5, float r6) {
            if (FloatingDebugView.m10686$$Nest$fgetisScrolling(this.this$0) == true) goto L5;
            return false;
        L5:
            if (FloatingDebugView.m10682$$Nest$fgetinLongPress(this.this$0) == true) goto L15;
            SpringForce r32 = FloatingDebugView.m10680$$Nest$fgetfabXSpring(this.this$0).getSpring();
            float r42 = FloatingDebugView.m10680$$Nest$fgetfabXSpring(this.this$0).getSpring().getFinalPosition() + (r5 / 7.0f);
            float r52 = this.this$0.getWidth() / 2.0f;
            FloatingDebugView r53 = this.this$0;
            if (r42 < r52) goto L10;
            DisplayMetrics r43 = r53.getResources().getDisplayMetrics();
            float r0 = 2.1474836E9f;
        L11:
            r32.setFinalPosition(FloatingDebugView.m10693$$Nest$mclampX(r53, r43, r0));
            SpringForce r33 = FloatingDebugView.m10681$$Nest$fgetfabYSpring(this.this$0).getSpring();
            FloatingDebugView r44 = this.this$0;
            r33.setFinalPosition(FloatingDebugView.m10694$$Nest$mclampY(r44, r44.getResources().getDisplayMetrics(), FloatingDebugView.m10681$$Nest$fgetfabYSpring(this.this$0).getSpring().getFinalPosition() + (r6 / 10.0f)));
            FloatingDebugView.m10680$$Nest$fgetfabXSpring(this.this$0).start();
            FloatingDebugView.m10681$$Nest$fgetfabYSpring(this.this$0).start();
            FloatingDebugView.m10690$$Nest$fputisFromFling(this.this$0, true);
            return true;
        L10:
            r43 = r53.getResources().getDisplayMetrics();
            r0 = -2.1474836E9f;
            goto L11
        L15:
            return false;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent r2, MotionEvent r3, float r4, float r5) {
            if (FloatingDebugView.m10682$$Nest$fgetinLongPress(this.this$0) == true) goto L6;
            AndroidUtilities.cancelRunOnUIThread(FloatingDebugView.m10687$$Nest$fgetonLongPress(this.this$0));
        L6:
            if (FloatingDebugView.m10686$$Nest$fgetisScrolling(this.this$0) == true) goto L17;
            if (FloatingDebugView.m10685$$Nest$fgetisScrollDisallowed(this.this$0) == true) goto L17;
            if (Math.abs(r4) < FloatingDebugView.m10688$$Nest$fgettouchSlop(this.this$0)) goto L12;
        L15:
            this.startX = FloatingDebugView.m10680$$Nest$fgetfabXSpring(this.this$0).getSpring().getFinalPosition();
            this.startY = FloatingDebugView.m10681$$Nest$fgetfabYSpring(this.this$0).getSpring().getFinalPosition();
            FloatingDebugView.m10692$$Nest$fputisScrolling(this.this$0, true);
            goto L17
        L12:
            if (Math.abs(r5) >= FloatingDebugView.m10688$$Nest$fgettouchSlop(this.this$0)) goto L15;
            FloatingDebugView.m10691$$Nest$fputisScrollDisallowed(this.this$0, false);
        L17:
            if (FloatingDebugView.m10686$$Nest$fgetisScrolling(this.this$0) == false) goto L23;
            if (FloatingDebugView.m10682$$Nest$fgetinLongPress(this.this$0) == true) goto L23;
            FloatingDebugView.m10680$$Nest$fgetfabXSpring(this.this$0).getSpring().setFinalPosition((this.startX + r3.getRawX()) - r2.getRawX());
            FloatingDebugView.m10681$$Nest$fgetfabYSpring(this.this$0).getSpring().setFinalPosition((this.startY + r3.getRawY()) - r2.getRawY());
            FloatingDebugView.m10680$$Nest$fgetfabXSpring(this.this$0).start();
            FloatingDebugView.m10681$$Nest$fgetfabYSpring(this.this$0).start();
        L23:
            return FloatingDebugView.m10686$$Nest$fgetisScrolling(this.this$0);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent r2) {
            if (FloatingDebugView.m10682$$Nest$fgetinLongPress(this.this$0) == false) goto L6;
            return false;
        L6:
            if (FloatingDebugView.m10683$$Nest$fgetisBigMenuShown(this.this$0) == true) goto L9;
            this.this$0.showBigMenu(true);
            return true;
        L9:
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.FloatingDebug.FloatingDebugView$2, reason: invalid class name */
    public class AnonymousClass2 extends FrameLayout {
        final /* synthetic */ FloatingDebugView this$0;
        final /* synthetic */ GestureDetectorCompat val$gestureDetector;

        public AnonymousClass2(FloatingDebugView r1, Context r2, GestureDetectorCompat r3) {
            this.this$0 = r1;
            this.val$gestureDetector = r3;
            super(r2);
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            this.this$0.invalidate();
        }

        @Override // android.view.View
        @SuppressLint({"ClickableViewAccessibility"})
        public boolean onTouchEvent(MotionEvent r5) {
            boolean r0 = this.val$gestureDetector.onTouchEvent(r5);
            if (r5.getAction() != 0) goto L7;
            AndroidUtilities.runOnUIThread(FloatingDebugView.m10687$$Nest$fgetonLongPress(this.this$0), 200);
            return r0;
        L7:
            if (r5.getAction() != 1) goto L9;
        L12:
            AndroidUtilities.cancelRunOnUIThread(FloatingDebugView.m10687$$Nest$fgetonLongPress(this.this$0));
            if (FloatingDebugView.m10684$$Nest$fgetisFromFling(this.this$0) == true) goto L20;
            SpringForce r52 = FloatingDebugView.m10680$$Nest$fgetfabXSpring(this.this$0).getSpring();
            float r1 = FloatingDebugView.m10680$$Nest$fgetfabXSpring(this.this$0).getSpring().getFinalPosition();
            float r2 = getWidth() / 2.0f;
            FloatingDebugView r22 = this.this$0;
            if (r1 < r2) goto L18;
            DisplayMetrics r12 = getResources().getDisplayMetrics();
            float r3 = 2.1474836E9f;
        L19:
            r52.setFinalPosition(FloatingDebugView.m10693$$Nest$mclampX(r22, r12, r3));
            FloatingDebugView.m10681$$Nest$fgetfabYSpring(this.this$0).getSpring().setFinalPosition(FloatingDebugView.m10694$$Nest$mclampY(this.this$0, getResources().getDisplayMetrics(), FloatingDebugView.m10681$$Nest$fgetfabYSpring(this.this$0).getSpring().getFinalPosition()));
            FloatingDebugView.m10680$$Nest$fgetfabXSpring(this.this$0).start();
            FloatingDebugView.m10681$$Nest$fgetfabYSpring(this.this$0).start();
            goto L20
        L18:
            r12 = getResources().getDisplayMetrics();
            r3 = -2.1474836E9f;
        L20:
            FloatingDebugView.m10689$$Nest$fputinLongPress(this.this$0, false);
            FloatingDebugView.m10692$$Nest$fputisScrolling(this.this$0, false);
            FloatingDebugView.m10691$$Nest$fputisScrollDisallowed(this.this$0, false);
            FloatingDebugView.m10690$$Nest$fputisFromFling(this.this$0, false);
            return r0;
        L9:
            if (r5.getAction() == 3) goto L12;
            return r0;
        }

        @Override // android.view.View
        public void setTranslationX(float r1) {
            super.setTranslationX(r1);
            this.this$0.invalidate();
        }

        @Override // android.view.View
        public void setTranslationY(float r1) {
            super.setTranslationY(r1);
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.FloatingDebug.FloatingDebugView$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ FloatingDebugView this$0;
        final /* synthetic */ Context val$context;

        public AnonymousClass3(FloatingDebugView r1, Context r2) {
            this.this$0 = r1;
            this.val$context = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return FloatingDebugView.m10679$$Nest$fgetdebugItems(this.this$0).size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return ((FloatingDebugController.DebugItem) FloatingDebugView.m10679$$Nest$fgetdebugItems(this.this$0).get(r1)).type.ordinal();
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (FloatingDebugController.DebugItemType.values()[r1.getItemViewType()] != FloatingDebugController.DebugItemType.SIMPLE) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r2, int r3) {
            FloatingDebugController.DebugItem r1 = (FloatingDebugController.DebugItem) FloatingDebugView.m10679$$Nest$fgetdebugItems(this.this$0).get(r3);
            int r32 = AnonymousClass4.$SwitchMap$org$telegram$ui$Components$FloatingDebug$FloatingDebugController$DebugItemType[r1.type.ordinal()];
            if (r32 != 1) goto L5;
            AlertDialog.AlertDialogCell r22 = (AlertDialog.AlertDialogCell) r2.itemView;
            r22.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
            r22.setTextAndIcon(r1.title, 0);
            return;
        L5:
            if (r32 != 2) goto L7;
            HeaderCell r23 = (HeaderCell) r2.itemView;
            r23.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueHeader));
            r23.setText(r1.title);
            return;
        L7:
            if (r32 == 3) goto L9;
            return;
        L9:
            SeekBarCell r24 = (SeekBarCell) r2.itemView;
            SeekBarCell.m10703$$Nest$fputtitle(r24, r1.title.toString());
            SeekBarCell.m10704$$Nest$fputvalue(r24, r1.floatProperty.get(null).floatValue());
            SeekBarCell.m10702$$Nest$fputmin(r24, r1.from);
            SeekBarCell.m10701$$Nest$fputmax(r24, r1.to);
            SeekBarCell.m10700$$Nest$fputcallback(r24, r1.floatProperty);
            r24.invalidate();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            int r22 = AnonymousClass4.$SwitchMap$org$telegram$ui$Components$FloatingDebug$FloatingDebugController$DebugItemType[FloatingDebugController.DebugItemType.values()[r3].ordinal()];
            if (r22 != 2) goto L5;
            View r23 = new HeaderCell(this.val$context);
        L9:
            r23.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r23);
        L5:
            if (r22 == 3) goto L7;
            r23 = new AlertDialog.AlertDialogCell(this.val$context, null);
            goto L9
        L7:
            r23 = new SeekBarCell(this.this$0, this.val$context);
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.Components.FloatingDebug.FloatingDebugView$4, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$org$telegram$ui$Components$FloatingDebug$FloatingDebugController$DebugItemType = null;

        static {
            int[] r0 = new int[FloatingDebugController.DebugItemType.values().length];
            $SwitchMap$org$telegram$ui$Components$FloatingDebug$FloatingDebugController$DebugItemType = r0;
            r0[FloatingDebugController.DebugItemType.SIMPLE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L7
        L10:
            $SwitchMap$org$telegram$ui$Components$FloatingDebug$FloatingDebugController$DebugItemType[FloatingDebugController.DebugItemType.HEADER.ordinal()] = 2;     // Catch: NoSuchFieldError -> L8
        L12:
            $SwitchMap$org$telegram$ui$Components$FloatingDebug$FloatingDebugController$DebugItemType[FloatingDebugController.DebugItemType.SEEKBAR.ordinal()] = 3;     // Catch: NoSuchFieldError -> L9
            return;
        }
    }

    public class SeekBarCell extends FrameLayout {
        private AnimationProperties.FloatProperty callback;
        private int lastWidth;
        private float max;
        private float min;
        private SeekBarView seekBar;
        private TextPaint textPaint;
        final /* synthetic */ FloatingDebugView this$0;
        private String title;
        private float value;

        /* renamed from: org.telegram.ui.Components.FloatingDebug.FloatingDebugView$SeekBarCell$1, reason: invalid class name */
        public class AnonymousClass1 implements SeekBarView.SeekBarViewDelegate {
            final /* synthetic */ SeekBarCell this$1;
            final /* synthetic */ FloatingDebugView val$this$0;

            public AnonymousClass1(SeekBarCell r1, FloatingDebugView r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
            public CharSequence getContentDescription() {
                return String.valueOf(Math.round(SeekBarCell.m10697$$Nest$fgetmin(this.this$1) + ((SeekBarCell.m10696$$Nest$fgetmax(this.this$1) - SeekBarCell.m10697$$Nest$fgetmin(this.this$1)) * SeekBarCell.m10698$$Nest$fgetseekBar(this.this$1).getProgress())));
            }

            @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
            public void onSeekBarDrag(boolean r5, float r6) {
                SeekBarCell r0 = this.this$1;
                SeekBarCell.m10704$$Nest$fputvalue(r0, SeekBarCell.m10697$$Nest$fgetmin(r0) + ((SeekBarCell.m10696$$Nest$fgetmax(this.this$1) - SeekBarCell.m10697$$Nest$fgetmin(this.this$1)) * r6));
                if (r5 == false) goto L5;
                SeekBarCell.m10695$$Nest$fgetcallback(this.this$1).set2(null, Float.valueOf(SeekBarCell.m10699$$Nest$fgetvalue(this.this$1)));
            L5:
                this.this$1.invalidate();
            }

            @Override // org.telegram.ui.Components.SeekBarView.SeekBarViewDelegate
            public void onSeekBarPressed(boolean r1) {
            }
        }

        /* renamed from: -$$Nest$fgetcallback, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimationProperties.FloatProperty m10695$$Nest$fgetcallback(SeekBarCell r0) {
            return r0.callback;
        }

        /* renamed from: -$$Nest$fgetmax, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m10696$$Nest$fgetmax(SeekBarCell r0) {
            return r0.max;
        }

        /* renamed from: -$$Nest$fgetmin, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m10697$$Nest$fgetmin(SeekBarCell r0) {
            return r0.min;
        }

        /* renamed from: -$$Nest$fgetseekBar, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SeekBarView m10698$$Nest$fgetseekBar(SeekBarCell r0) {
            return r0.seekBar;
        }

        /* renamed from: -$$Nest$fgetvalue, reason: not valid java name */
        public static /* bridge */ /* synthetic */ float m10699$$Nest$fgetvalue(SeekBarCell r0) {
            return r0.value;
        }

        /* renamed from: -$$Nest$fputcallback, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10700$$Nest$fputcallback(SeekBarCell r0, AnimationProperties.FloatProperty r1) {
            r0.callback = r1;
        }

        /* renamed from: -$$Nest$fputmax, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10701$$Nest$fputmax(SeekBarCell r0, float r1) {
            r0.max = r1;
        }

        /* renamed from: -$$Nest$fputmin, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10702$$Nest$fputmin(SeekBarCell r0, float r1) {
            r0.min = r1;
        }

        /* renamed from: -$$Nest$fputtitle, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10703$$Nest$fputtitle(SeekBarCell r0, String r1) {
            r0.title = r1;
        }

        /* renamed from: -$$Nest$fputvalue, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m10704$$Nest$fputvalue(SeekBarCell r0, float r1) {
            r0.value = r1;
        }

        public SeekBarCell(FloatingDebugView r8, Context r9) {
            this.this$0 = r8;
            super(r9);
            setWillNotDraw(false);
            TextPaint r0 = new TextPaint(1);
            this.textPaint = r0;
            r0.setTextSize(AndroidUtilities.dp(16.0f));
            SeekBarView r02 = new SeekBarView(r9);
            this.seekBar = r02;
            r02.setReportChanges(true);
            this.seekBar.setDelegate(new AnonymousClass1(this, r8));
            this.seekBar.setImportantForAccessibility(2);
            addView(this.seekBar, LayoutHelper.createFrame(-1, 38.0f, 83, 5.0f, 29.0f, 47.0f, 0.0f));
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            this.seekBar.invalidate();
        }

        @Override // android.view.View
        public void onDraw(Canvas r5) {
            this.textPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            r5.drawText(this.title, AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f), this.textPaint);
            this.textPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteValueText));
            String r0 = String.format(Locale.ROOT, "%.2f", new Object[]{Float.valueOf(this.value)});
            r5.drawText(r0, (getMeasuredWidth() - AndroidUtilities.dp(8.0f)) - this.textPaint.measureText(r0), AndroidUtilities.dp(23.0f) + this.seekBar.getY(), this.textPaint);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
            super.onInitializeAccessibilityNodeInfo(r2);
            this.seekBar.getSeekBarAccessibilityDelegate().onInitializeAccessibilityNodeInfoInternal(this, r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            super.onMeasure(r4, r5);
            int r42 = View.MeasureSpec.getSize(r4);
            if (this.lastWidth == r42) goto L6;
            SeekBarView r52 = this.seekBar;
            float r0 = this.callback.get(null).floatValue();
            float r1 = this.min;
            r52.setProgress((r0 - r1) / (this.max - r1));
            this.lastWidth = r42;
            return;
        }

        @Override // android.view.View
        public boolean performAccessibilityAction(int r2, Bundle r3) {
            if (super.performAccessibilityAction(r2, r3) == false) goto L5;
            return true;
        L5:
            if (this.seekBar.getSeekBarAccessibilityDelegate().performAccessibilityActionInternal(this, r2, r3) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    /* renamed from: $r8$lambda$2HZIdSpxP-aTzastKQznCf8ldpI, reason: not valid java name */
    public static /* synthetic */ void m10674$r8$lambda$2HZIdSpxPaTzastKQznCf8ldpI(FloatingDebugView r0, DynamicAnimation r1, float r2, float r3) {
        r0.lambda$showFab$10(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$7rGL8vxku9nYAnhXlfQN2DXNXpk(FloatingDebugView r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$8UcaEejjL5v0GDHWquNuVEtnvNg(FloatingDebugView r0) {
        r0.lambda$getBuiltInDebugItems$5();
    }

    /* renamed from: $r8$lambda$8_MUVG-bGGL4kMp8iACSoBLL1NU, reason: not valid java name */
    public static /* synthetic */ void m10675$r8$lambda$8_MUVGbGGL4kMp8iACSoBLL1NU() {
        SharedPreferences r0 = ApplicationLoader.applicationContext.getSharedPreferences("themeconfig", 0);
        String r2 = "Blue";
        String r1 = r0.getString("lastDayTheme", "Blue");
        if (Theme.getTheme(r1) != null) goto L5;
    L6:
        r1 = "Blue";
    L7:
        String r4 = "Dark Blue";
        String r02 = r0.getString("lastDarkTheme", "Dark Blue");
        if (Theme.getTheme(r02) != null) goto L10;
    L11:
        r02 = "Dark Blue";
    L12:
        Theme.ThemeInfo r3 = Theme.getActiveTheme();
        if (r1.equals(r02) == true) goto L15;
        r4 = r02;
    L21:
        r2 = r1;
    L25:
        if (Theme.isCurrentThemeDark() == true) goto L27;
        Theme.ThemeInfo r03 = Theme.getTheme(r4);
    L28:
        AndroidUtilities.runOnUIThread(new FloatingDebugView$$ExternalSyntheticLambda11(r03), 200);
        return;
    L27:
        r03 = Theme.getTheme(r2);
        goto L28
    L15:
        if (r3.isDark() == false) goto L17;
    L22:
        r4 = r02;
        goto L25
    L17:
        if (r1.equals("Dark Blue") == true) goto L22;
        if (r1.equals("Night") == false) goto L21;
    L10:
        if (Theme.getTheme(r02).isDark() == true) goto L12;
    L5:
        if (Theme.getTheme(r1).isDark() == false) goto L7;
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$LO0DW_E5DsLiLP8ZOey1cRwIDv4(FloatingDebugView r0, float r1, float r2, Window r3, DynamicAnimation r4, float r5, float r6) {
        r0.lambda$showBigMenu$2(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$OCcpbABQyUyck_lIiFgTyuoRqgE() {
        SharedConfig.drawActionBarShadow = !SharedConfig.drawActionBarShadow;
        SharedConfig.saveDebugConfig();
        AndroidUtilities.forEachViews(LaunchActivity.instance.drawerLayoutContainer.getRootView(), new FloatingDebugView$$ExternalSyntheticLambda10());
    }

    public static /* synthetic */ void $r8$lambda$Reka41J3in5Vr0MMyL564Awn68c(FloatingDebugView r0) {
        r0.lambda$getBuiltInDebugItems$9();
    }

    public static /* synthetic */ void $r8$lambda$biCjL9fH_cErEL91CMJWWZQ_drc(FloatingDebugView r0, View r1, int r2) {
        r0.lambda$new$1(r1, r2);
    }

    /* renamed from: $r8$lambda$eTz5rW_v9SqieK7n-_FMb5J-mLI, reason: not valid java name */
    public static /* synthetic */ void m10676$r8$lambda$eTz5rW_v9SqieK7n_FMb5JmLI(FloatingDebugView r0, float r1, float r2, boolean r3, DynamicAnimation r4, boolean r5, float r6, float r7) {
        r0.lambda$showBigMenu$3(r1, r2, r3, r4, r5, r6, r7);
    }

    /* renamed from: $r8$lambda$kxQvSNloLFPfPu6vMFyW5JP-20s, reason: not valid java name */
    public static /* synthetic */ void m10677$r8$lambda$kxQvSNloLFPfPu6vMFyW5JP20s(FloatingDebugView r0) {
        r0.lambda$getBuiltInDebugItems$6();
    }

    /* renamed from: $r8$lambda$waPbn-BzI0OtmmOTmp3ON72RrIQ, reason: not valid java name */
    public static /* synthetic */ void m10678$r8$lambda$waPbnBzI0OtmmOTmp3ON72RrIQ(Theme.ThemeInfo r5) {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needSetDayNightTheme, new Object[]{r5, Boolean.TRUE, null, -1});
    }

    /* renamed from: -$$Nest$fgetdebugItems, reason: not valid java name */
    public static /* bridge */ /* synthetic */ List m10679$$Nest$fgetdebugItems(FloatingDebugView r0) {
        return r0.debugItems;
    }

    /* renamed from: -$$Nest$fgetfabXSpring, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SpringAnimation m10680$$Nest$fgetfabXSpring(FloatingDebugView r0) {
        return r0.fabXSpring;
    }

    /* renamed from: -$$Nest$fgetfabYSpring, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SpringAnimation m10681$$Nest$fgetfabYSpring(FloatingDebugView r0) {
        return r0.fabYSpring;
    }

    /* renamed from: -$$Nest$fgetinLongPress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10682$$Nest$fgetinLongPress(FloatingDebugView r0) {
        return r0.inLongPress;
    }

    /* renamed from: -$$Nest$fgetisBigMenuShown, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10683$$Nest$fgetisBigMenuShown(FloatingDebugView r0) {
        return r0.isBigMenuShown;
    }

    /* renamed from: -$$Nest$fgetisFromFling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10684$$Nest$fgetisFromFling(FloatingDebugView r0) {
        return r0.isFromFling;
    }

    /* renamed from: -$$Nest$fgetisScrollDisallowed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10685$$Nest$fgetisScrollDisallowed(FloatingDebugView r0) {
        return r0.isScrollDisallowed;
    }

    /* renamed from: -$$Nest$fgetisScrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10686$$Nest$fgetisScrolling(FloatingDebugView r0) {
        return r0.isScrolling;
    }

    /* renamed from: -$$Nest$fgetonLongPress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m10687$$Nest$fgetonLongPress(FloatingDebugView r0) {
        return r0.onLongPress;
    }

    /* renamed from: -$$Nest$fgettouchSlop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10688$$Nest$fgettouchSlop(FloatingDebugView r0) {
        return r0.touchSlop;
    }

    /* renamed from: -$$Nest$fputinLongPress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10689$$Nest$fputinLongPress(FloatingDebugView r0, boolean r1) {
        r0.inLongPress = r1;
    }

    /* renamed from: -$$Nest$fputisFromFling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10690$$Nest$fputisFromFling(FloatingDebugView r0, boolean r1) {
        r0.isFromFling = r1;
    }

    /* renamed from: -$$Nest$fputisScrollDisallowed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10691$$Nest$fputisScrollDisallowed(FloatingDebugView r0, boolean r1) {
        r0.isScrollDisallowed = r1;
    }

    /* renamed from: -$$Nest$fputisScrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10692$$Nest$fputisScrolling(FloatingDebugView r0, boolean r1) {
        r0.isScrolling = r1;
    }

    /* renamed from: -$$Nest$mclampX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m10693$$Nest$mclampX(FloatingDebugView r0, DisplayMetrics r1, float r2) {
        return r0.clampX(r1, r2);
    }

    /* renamed from: -$$Nest$mclampY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m10694$$Nest$mclampY(FloatingDebugView r0, DisplayMetrics r1, float r2) {
        return r0.clampY(r1, r2);
    }

    public FloatingDebugView(Context r12) {
        super(r12);
        this.onLongPress = new FloatingDebugView$$ExternalSyntheticLambda6(this);
        this.debugItems = new ArrayList();
        this.gestureListener = new AnonymousClass1(this);
        this.mPrefs = r12.getSharedPreferences("floating_debug", 0);
        this.touchSlop = ViewConfiguration.get(r12).getScaledTouchSlop();
        GestureDetectorCompat r0 = new GestureDetectorCompat(r12, this.gestureListener);
        r0.setIsLongpressEnabled(false);
        this.floatingButtonContainer = new AnonymousClass2(this, r12, r0);
        ImageView r02 = new ImageView(r12);
        r02.setImageResource(R.drawable.device_phone_android);
        r02.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chats_actionIcon), PorterDuff.Mode.SRC_IN));
        this.floatingButtonContainer.addView(r02);
        this.floatingButtonContainer.setVisibility(8);
        addView(this.floatingButtonContainer, LayoutHelper.createFrame(56, 56.0f));
        LinearLayout r03 = new LinearLayout(r12);
        this.bigLayout = r03;
        r03.setOrientation(1);
        this.bigLayout.setVisibility(8);
        TextView r04 = new TextView(r12);
        this.titleView = r04;
        r04.setTextSize(1, 20.0f);
        this.titleView.setText(LocaleController.getString(R.string.DebugMenu));
        this.titleView.setTypeface(AndroidUtilities.bold());
        this.titleView.setPadding(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(19.0f), AndroidUtilities.dp(24.0f), AndroidUtilities.dp(19.0f));
        this.bigLayout.addView(this.titleView, LayoutHelper.createLinear(-1, -2));
        RecyclerListView r05 = new RecyclerListView(r12);
        this.listView = r05;
        r05.setLayoutManager(new LinearLayoutManager(r12));
        this.listView.setAdapter(new AnonymousClass3(this, r12));
        this.listView.setOnItemClickListener(new FloatingDebugView$$ExternalSyntheticLambda7(this));
        this.bigLayout.addView(this.listView, LayoutHelper.createLinear(-1, 0, 1.0f));
        addView(this.bigLayout, LayoutHelper.createFrame(-1, -1.0f, 0, 8.0f, 8.0f, 8.0f, 8.0f));
        updateDrawables();
        setFitsSystemWindows(true);
        setWillNotDraw(false);
    }

    private float clampX(DisplayMetrics r2, float r3) {
        return MathUtils.clamp(r3, AndroidUtilities.dp(16.0f), r2.widthPixels - AndroidUtilities.dp(72.0f));
    }

    private float clampY(DisplayMetrics r2, float r3) {
        return MathUtils.clamp(r3, AndroidUtilities.dp(16.0f), r2.heightPixels - AndroidUtilities.dp(72.0f));
    }

    private List<FloatingDebugController.DebugItem> getBuiltInDebugItems() {
        ArrayList r0 = new ArrayList();
        r0.add(new FloatingDebugController.DebugItem("Theme"));
        r0.add(new FloatingDebugController.DebugItem("Draw action bar shadow", new FloatingDebugView$$ExternalSyntheticLambda1()));
        r0.add(new FloatingDebugController.DebugItem("Show blur settings", new FloatingDebugView$$ExternalSyntheticLambda2(this)));
        r0.add(new FloatingDebugController.DebugItem(LocaleController.getString(R.string.DebugGeneral)));
        if (SharedConfig.debugWebView == false) goto L5;
        int r2 = R.string.DebugMenuDisableWebViewDebug;
    L6:
        r0.add(new FloatingDebugController.DebugItem(LocaleController.getString(r2), new FloatingDebugView$$ExternalSyntheticLambda3(this)));
        if (Theme.isCurrentThemeDark() == false) goto L9;
        String r22 = "Switch to day theme";
    L10:
        r0.add(new FloatingDebugController.DebugItem(r22, new FloatingDebugView$$ExternalSyntheticLambda4()));
        r0.add(new FloatingDebugController.DebugItem(LocaleController.getString(R.string.DebugSendLogs), new FloatingDebugView$$ExternalSyntheticLambda5(this)));
        return r0;
    L9:
        r22 = "Switch to dark theme";
        goto L10
    L5:
        r2 = R.string.DebugMenuEnableWebViewDebug;
        goto L6
    }

    private /* synthetic */ void lambda$getBuiltInDebugItems$5() {
        BlurSettingsBottomSheet.show(LaunchActivity.getLastFragment());
        showBigMenu(false);
    }

    private /* synthetic */ void lambda$getBuiltInDebugItems$6() {
        SharedConfig.toggleDebugWebView();
        Context r2 = getContext();
        if (SharedConfig.debugWebView == false) goto L5;
        int r0 = R.string.DebugMenuWebViewDebugEnabled;
    L6:
        Toast.makeText(r2, LocaleController.getString(r0), 0).show();
        return;
    L5:
        r0 = R.string.DebugMenuWebViewDebugDisabled;
        goto L6
    }

    private /* synthetic */ void lambda$getBuiltInDebugItems$9() {
        ProfileActivity.sendLogs((Activity) getContext(), false);
    }

    private /* synthetic */ void lambda$new$0() {
        this.inLongPress = true;
        performHapticFeedback(0);     // Catch: Exception -> L5
        return;
    }

    private /* synthetic */ void lambda$new$1(View r1, int r2) {
        Runnable r12 = this.debugItems.get(r2).action;
        if (r12 == null) goto L6;
        r12.run();
        showBigMenu(false);
        return;
    }

    private /* synthetic */ void lambda$showBigMenu$2(float r5, float r6, Window r7, DynamicAnimation r8, float r9, float r10) {
        float r92 = r9 / 1000.0f;
        this.bigLayout.setAlpha(r92);
        this.bigLayout.setTranslationX(AndroidUtilities.lerp(r5 - AndroidUtilities.dp(8.0f), 0.0f, r92));
        this.bigLayout.setTranslationY(AndroidUtilities.lerp(r6 - AndroidUtilities.dp(8.0f), 0.0f, r92));
        this.bigLayout.setPivotX(this.floatingButtonContainer.getTranslationX() + AndroidUtilities.dp(28.0f));
        this.bigLayout.setPivotY(this.floatingButtonContainer.getTranslationY() + AndroidUtilities.dp(28.0f));
        if (this.bigLayout.getWidth() == 0) goto L6;
        this.bigLayout.setScaleX(AndroidUtilities.lerp(this.floatingButtonContainer.getWidth() / this.bigLayout.getWidth(), 1.0f, r92));
    L6:
        if (this.bigLayout.getHeight() == 0) goto L8;
        this.bigLayout.setScaleY(AndroidUtilities.lerp(this.floatingButtonContainer.getHeight() / this.bigLayout.getHeight(), 1.0f, r92));
    L8:
        this.floatingButtonContainer.setTranslationX(AndroidUtilities.lerp(r5, (getWidth() / 2.0f) - AndroidUtilities.dp(28.0f), r92));
        this.floatingButtonContainer.setTranslationY(AndroidUtilities.lerp(r6, (getHeight() / 2.0f) - AndroidUtilities.dp(28.0f), r92));
        this.floatingButtonContainer.setAlpha(1.0f - r92);
        r7.setStatusBarColor(ColorUtils.blendARGB(this.wasStatusBar, 2046820352, r92));
        invalidate();
    }

    private /* synthetic */ void lambda$showBigMenu$3(float r1, float r2, boolean r3, DynamicAnimation r4, boolean r5, float r6, float r7) {
        this.floatingButtonContainer.setTranslationX(r1);
        this.floatingButtonContainer.setTranslationY(r2);
        if (r3 == true) goto L6;
        this.bigLayout.setVisibility(8);
        return;
    }

    private /* synthetic */ void lambda$showFab$10(DynamicAnimation r2, float r3, float r4) {
        float r32 = r3 / 1000.0f;
        this.floatingButtonContainer.setPivotX(AndroidUtilities.dp(28.0f));
        this.floatingButtonContainer.setPivotY(AndroidUtilities.dp(28.0f));
        this.floatingButtonContainer.setScaleX(r32);
        this.floatingButtonContainer.setScaleY(r32);
        this.floatingButtonContainer.setAlpha(MathUtils.clamp(r32, 0.0f, 1.0f));
        invalidate();
    }

    private void updateDrawables() {
        Drawable r1 = Theme.createSimpleSelectorCircleDrawable(AndroidUtilities.dp(56.0f), Theme.getColor(Theme.key_chats_actionBackground), Theme.getColor(Theme.key_chats_actionPressedBackground));
        Drawable r2 = getResources().getDrawable(R.drawable.floating_shadow).mutate();
        PorterDuff.Mode r4 = PorterDuff.Mode.MULTIPLY;
        r2.setColorFilter(new PorterDuffColorFilter(-16777216, r4));
        CombinedDrawable r3 = new CombinedDrawable(r2, r1, 0, 0);
        r3.setIconSize(AndroidUtilities.dp(56.0f), AndroidUtilities.dp(56.0f));
        this.floatingButtonBackground = r3;
        Drawable r0 = getResources().getDrawable(R.drawable.popup_fixed_alert3);
        r0.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogBackground), r4));
        this.bigLayout.setBackground(r0);
        this.titleView.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
        invalidate();
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    @SuppressLint({"NotifyDataSetChanged"})
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.didSetNewTheme) goto L6;
        updateDrawables();
        this.listView.getAdapter().notifyDataSetChanged();
        return;
    }

    public void dismiss(Runnable r1) {
        r1.run();
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r3, View r4, long r5) {
        LinearLayout r0 = this.bigLayout;
        if (r4 != r0) goto L6;
        r3.drawColor(Color.argb((int) (r0.getAlpha() * 122.0f), 0, 0, 0));
    L6:
        return super.drawChild(r3, r4, r5);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        float r0 = this.mPrefs.getFloat("x", -1.0f);
        float r1 = this.mPrefs.getFloat("y", -1.0f);
        DisplayMetrics r3 = getResources().getDisplayMetrics();
        FrameLayout r4 = this.floatingButtonContainer;
        if (r0 != (-1.0f)) goto L5;
    L8:
        float r02 = clampX(r3, 2.1474836E9f);
    L9:
        r4.setTranslationX(r02);
        FrameLayout r03 = this.floatingButtonContainer;
        if (r1 != (-1.0f)) goto L12;
        float r12 = clampY(r3, 2.1474836E9f);
    L13:
        r03.setTranslationY(r12);
        FrameLayout r13 = this.floatingButtonContainer;
        this.fabXSpring = new SpringAnimation(r13, DynamicAnimation.TRANSLATION_X, r13.getTranslationX()).setSpring(new SpringForce(this.floatingButtonContainer.getTranslationX()).setStiffness(650.0f).setDampingRatio(0.75f));
        FrameLayout r14 = this.floatingButtonContainer;
        this.fabYSpring = new SpringAnimation(r14, DynamicAnimation.TRANSLATION_Y, r14.getTranslationY()).setSpring(new SpringForce(this.floatingButtonContainer.getTranslationY()).setStiffness(650.0f).setDampingRatio(0.75f));
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didSetNewTheme);
        return;
    L12:
        r12 = clampY(r3, r1);
        goto L13
    L5:
        if (r0 >= (r3.widthPixels / 2.0f)) goto L8;
        r02 = clampX(r3, -2.1474836E9f);
        goto L9
    }

    public boolean onBackPressed(boolean r2) {
        if (this.isBigMenuShown == true) goto L5;
        return true;
    L5:
        if (r2 == false) goto L7;
        showBigMenu(false);
    L7:
        return false;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration r5) {
        super.onConfigurationChanged(r5);
        this.fabXSpring.cancel();
        this.fabYSpring.cancel();
        DisplayMetrics r52 = getResources().getDisplayMetrics();
        FrameLayout r0 = this.floatingButtonContainer;
        if (r0.getTranslationX() < (r52.widthPixels / 2.0f)) goto L6;
        float r1 = 2.1474836E9f;
    L7:
        r0.setTranslationX(clampX(r52, r1));
        FrameLayout r02 = this.floatingButtonContainer;
        r02.setTranslationY(clampY(r52, r02.getTranslationY()));
        this.fabXSpring.getSpring().setFinalPosition(this.floatingButtonContainer.getTranslationX());
        this.fabYSpring.getSpring().setFinalPosition(this.floatingButtonContainer.getTranslationY());
        return;
    L6:
        r1 = -2.1474836E9f;
        goto L7
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.fabXSpring.cancel();
        this.fabYSpring.cancel();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.didSetNewTheme);
    }

    @Override // android.view.View
    public void onDraw(Canvas r6) {
        super.onDraw(r6);
        r6.save();
        r6.translate(this.floatingButtonContainer.getTranslationX(), this.floatingButtonContainer.getTranslationY());
        r6.scale(this.floatingButtonContainer.getScaleX(), this.floatingButtonContainer.getScaleY(), this.floatingButtonContainer.getPivotX(), this.floatingButtonContainer.getPivotY());
        this.floatingButtonBackground.setAlpha((int) (this.floatingButtonContainer.getAlpha() * 255.0f));
        this.floatingButtonBackground.setBounds(this.floatingButtonContainer.getLeft(), this.floatingButtonContainer.getTop(), this.floatingButtonContainer.getRight(), this.floatingButtonContainer.getBottom());
        this.floatingButtonBackground.draw(r6);
        r6.restore();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r1) {
        return this.isBigMenuShown;
    }

    @SuppressLint({"ApplySharedPref"})
    public void saveConfig() {
        this.mPrefs.edit().putFloat("x", this.fabXSpring.getSpring().getFinalPosition()).putFloat("y", this.fabYSpring.getSpring().getFinalPosition()).apply();
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public void showBigMenu(boolean r9) {
        if (this.isBigMenuShown != r9) goto L5;
        return;
    L5:
        this.isBigMenuShown = r9;
        if (r9 == false) goto L19;
        this.bigLayout.setVisibility(0);
        this.debugItems.clear();
        if ((getContext() instanceof LaunchActivity) == false) goto L18;
        INavigationLayout r0 = ((LaunchActivity) getContext()).getActionBarLayout();
        if ((r0 instanceof FloatingDebugProvider) == false) goto L12;
        this.debugItems.addAll(((FloatingDebugProvider) r0).onGetDebugItems());
    L12:
        INavigationLayout r02 = ((LaunchActivity) getContext()).getRightActionBarLayout();
        if ((r02 instanceof FloatingDebugProvider) == false) goto L15;
        this.debugItems.addAll(((FloatingDebugProvider) r02).onGetDebugItems());
    L15:
        INavigationLayout r03 = ((LaunchActivity) getContext()).getLayersActionBarLayout();
        if ((r03 instanceof FloatingDebugProvider) == false) goto L18;
        this.debugItems.addAll(((FloatingDebugProvider) r03).onGetDebugItems());
    L18:
        this.debugItems.addAll(getBuiltInDebugItems());
        this.listView.getAdapter().notifyDataSetChanged();
    L19:
        Window r04 = ((Activity) getContext()).getWindow();
        if (r9 == false) goto L22;
        this.wasStatusBar = r04.getStatusBarColor();
    L22:
        float r1 = this.floatingButtonContainer.getTranslationX();
        float r2 = this.floatingButtonContainer.getTranslationY();
        float r5 = 0.0f;
        if (r9 == false) goto L25;
        float r7 = 0.0f;
    L26:
        SpringAnimation r3 = new SpringAnimation(new FloatValueHolder(r7));
        SpringForce r4 = new SpringForce(1000.0f).setStiffness(900.0f).setDampingRatio(1.0f);
        if (r9 == false) goto L29;
        r5 = 1000.0f;
    L29:
        r3.setSpring(r4.setFinalPosition(r5)).addUpdateListener(new FloatingDebugView$$ExternalSyntheticLambda8(this, r1, r2, r04)).addEndListener(new FloatingDebugView$$ExternalSyntheticLambda9(this, r1, r2, r9)).start();
        return;
    L25:
        r7 = 1000.0f;
        goto L26
    }

    public void showFab() {
        this.floatingButtonContainer.setVisibility(0);
        new SpringAnimation(new FloatValueHolder(0.0f)).setSpring(new SpringForce(1000.0f).setStiffness(750.0f).setDampingRatio(0.75f)).addUpdateListener(new FloatingDebugView$$ExternalSyntheticLambda0(this)).start();
    }
}
