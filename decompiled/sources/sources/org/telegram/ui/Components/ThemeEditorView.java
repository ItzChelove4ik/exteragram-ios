package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Property;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeColors;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.TextColorThemeCell;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.WallpaperUpdater;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes3.dex */
public class ThemeEditorView {

    @SuppressLint({"StaticFieldLeak"})
    private static volatile ThemeEditorView Instance;
    private ArrayList<ThemeDescription> currentThemeDesription;
    private int currentThemeDesriptionPosition;
    private DecelerateInterpolator decelerateInterpolator;
    private EditorAlert editorAlert;
    private final int editorHeight;
    private final int editorWidth;
    private boolean hidden;
    private Activity parentActivity;
    private SharedPreferences preferences;
    private Theme.ThemeInfo themeInfo;
    private WallpaperUpdater wallpaperUpdater;
    private WindowManager.LayoutParams windowLayoutParams;
    private WindowManager windowManager;
    private FrameLayout windowView;

    /* renamed from: org.telegram.ui.Components.ThemeEditorView$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends FrameLayout {
        private boolean dragging;
        private float startX;
        private float startY;
        final /* synthetic */ ThemeEditorView this$0;

        public static /* synthetic */ void $r8$lambda$5ikbHm7YUzFlf00sfJ_spED4__Y(AnonymousClass1 r0, DialogInterface r1) {
            r0.lambda$onTouchEvent$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$oc9I2dK8GKQWkypXPayXK8aXBxc(DialogInterface r0) {
        }

        public AnonymousClass1(ThemeEditorView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        private /* synthetic */ void lambda$onTouchEvent$1(DialogInterface r2) {
            ThemeEditorView.m13264$$Nest$fputeditorAlert(this.this$0, null);
            ThemeEditorView.m13267$$Nest$mshow(this.this$0);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r1) {
            return true;
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r11) {
            float r0 = r11.getRawX();
            float r1 = r11.getRawY();
            if (r11.getAction() != 0) goto L6;
            this.startX = r0;
            this.startY = r1;
        L44:
            if (this.dragging == true) goto L46;
        L72:
            return true;
        L46:
            if (r11.getAction() != 2) goto L70;
            float r112 = r0 - this.startX;
            float r2 = r1 - this.startY;
            ThemeEditorView.m13259$$Nest$fgetwindowLayoutParams(this.this$0).x = (int) (r6.x + r112);
            ThemeEditorView.m13259$$Nest$fgetwindowLayoutParams(this.this$0).y = (int) (r11.y + r2);
            int r113 = ThemeEditorView.m13255$$Nest$fgeteditorWidth(this.this$0) / 2;
            int r22 = ThemeEditorView.m13259$$Nest$fgetwindowLayoutParams(this.this$0).x;
            int r4 = -r113;
            ThemeEditorView r6 = this.this$0;
            if (r22 >= r4) goto L51;
            ThemeEditorView.m13259$$Nest$fgetwindowLayoutParams(r6).x = r4;
        L53:
            int r23 = ThemeEditorView.m13259$$Nest$fgetwindowLayoutParams(this.this$0).x;
            ThemeEditorView r42 = this.this$0;
            float r7 = 1.0f;
            if (r23 >= 0) goto L57;
            r7 = 1.0f + ((ThemeEditorView.m13259$$Nest$fgetwindowLayoutParams(r42).x / r113) * 0.5f);
        L60:
            if (ThemeEditorView.m13261$$Nest$fgetwindowView(this.this$0).getAlpha() == r7) goto L62;
            ThemeEditorView.m13261$$Nest$fgetwindowView(this.this$0).setAlpha(r7);
        L62:
            int r114 = ThemeEditorView.m13259$$Nest$fgetwindowLayoutParams(this.this$0).y;
            ThemeEditorView r24 = this.this$0;
            if (r114 >= 0) goto L66;
            ThemeEditorView.m13259$$Nest$fgetwindowLayoutParams(r24).y = 0;
        L68:
            ThemeEditorView.m13260$$Nest$fgetwindowManager(this.this$0).updateViewLayout(ThemeEditorView.m13261$$Nest$fgetwindowView(this.this$0), ThemeEditorView.m13259$$Nest$fgetwindowLayoutParams(this.this$0));
            this.startX = r0;
            this.startY = r1;
            goto L72
        L66:
            if (ThemeEditorView.m13259$$Nest$fgetwindowLayoutParams(r24).y <= (AndroidUtilities.displaySize.y - ThemeEditorView.m13259$$Nest$fgetwindowLayoutParams(this.this$0).height)) goto L68;
            ThemeEditorView.m13259$$Nest$fgetwindowLayoutParams(this.this$0).y = AndroidUtilities.displaySize.y - ThemeEditorView.m13259$$Nest$fgetwindowLayoutParams(this.this$0).height;
            goto L68
        L57:
            if (ThemeEditorView.m13259$$Nest$fgetwindowLayoutParams(r42).x <= (AndroidUtilities.displaySize.x - ThemeEditorView.m13259$$Nest$fgetwindowLayoutParams(this.this$0).width)) goto L60;
            r7 = 1.0f - ((((ThemeEditorView.m13259$$Nest$fgetwindowLayoutParams(this.this$0).x - AndroidUtilities.displaySize.x) + ThemeEditorView.m13259$$Nest$fgetwindowLayoutParams(this.this$0).width) / r113) * 0.5f);
            goto L60
        L51:
            if (ThemeEditorView.m13259$$Nest$fgetwindowLayoutParams(r6).x <= ((AndroidUtilities.displaySize.x - ThemeEditorView.m13259$$Nest$fgetwindowLayoutParams(this.this$0).width) + r113)) goto L53;
            ThemeEditorView.m13259$$Nest$fgetwindowLayoutParams(this.this$0).x = (AndroidUtilities.displaySize.x - ThemeEditorView.m13259$$Nest$fgetwindowLayoutParams(this.this$0).width) + r113;
            goto L53
        L70:
            if (r11.getAction() != 1) goto L72;
            this.dragging = false;
            ThemeEditorView.m13265$$Nest$manimateToBoundsMaybe(this.this$0);
            goto L72
        L6:
            if (r11.getAction() != 2) goto L15;
            if (this.dragging == true) goto L15;
            if (Math.abs(this.startX - r0) < AndroidUtilities.getPixelsInCM(0.3f, true)) goto L12;
        L13:
            this.dragging = true;
            this.startX = r0;
            this.startY = r1;
            goto L44
        L12:
            if (Math.abs(this.startY - r1) < AndroidUtilities.getPixelsInCM(0.3f, false)) goto L44;
        L15:
            if (r11.getAction() != 1) goto L44;
            if (this.dragging == true) goto L44;
            if (ThemeEditorView.m13254$$Nest$fgeteditorAlert(this.this$0) != null) goto L44;
            LaunchActivity r25 = (LaunchActivity) ThemeEditorView.m13256$$Nest$fgetparentActivity(this.this$0);
            BaseFragment r72 = null;
            if (AndroidUtilities.isTablet() == false) goto L32;
            INavigationLayout r62 = r25.getLayersActionBarLayout();
            if (r62 != null) goto L25;
        L27:
            if (r62 != null) goto L33;
            r62 = r25.getRightActionBarLayout();
            if (r62 == null) goto L33;
            if (r62.getFragmentStack().isEmpty() == true) goto L32;
        L33:
            if (r62 != null) goto L35;
            r62 = r25.getActionBarLayout();
        L35:
            if (r62 == null) goto L44;
            if (r62.getFragmentStack().isEmpty() == true) goto L39;
            r72 = r62.getFragmentStack().get(r62.getFragmentStack().size() - 1);
        L39:
            if (r72 == null) goto L44;
            ArrayList<ThemeDescription> r26 = r72.getThemeDescriptions();
            if (r26 == null) goto L44;
            ThemeEditorView r63 = this.this$0;
            ThemeEditorView r8 = this.this$0;
            ThemeEditorView.m13264$$Nest$fputeditorAlert(r63, new EditorAlert(r8, ThemeEditorView.m13256$$Nest$fgetparentActivity(r8), r26));
            ThemeEditorView.m13254$$Nest$fgeteditorAlert(this.this$0).setOnDismissListener(new ThemeEditorView$1$$ExternalSyntheticLambda0());
            ThemeEditorView.m13254$$Nest$fgeteditorAlert(this.this$0).setOnDismissListener(new ThemeEditorView$1$$ExternalSyntheticLambda1(this));
            ThemeEditorView.m13254$$Nest$fgeteditorAlert(this.this$0).show();
            ThemeEditorView.m13266$$Nest$mhide(this.this$0);
            goto L44
        L25:
            if (r62.getFragmentStack().isEmpty() == false) goto L27;
            r62 = null;
        L32:
            r62 = null;
            goto L33
        }
    }

    /* renamed from: org.telegram.ui.Components.ThemeEditorView$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 implements WallpaperUpdater.WallpaperUpdaterDelegate {
        final /* synthetic */ ThemeEditorView this$0;

        public AnonymousClass2(ThemeEditorView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.WallpaperUpdater.WallpaperUpdaterDelegate
        public void didSelectWallpaper(File r1, Bitmap r2, boolean r3) {
            Theme.setThemeWallpaper(ThemeEditorView.m13257$$Nest$fgetthemeInfo(this.this$0), r2, r1);
        }

        @Override // org.telegram.ui.Components.WallpaperUpdater.WallpaperUpdaterDelegate
        public void needOpenColorPicker() {
            int r0 = 0;
        L3:
            int r1 = ThemeEditorView.m13251$$Nest$fgetcurrentThemeDesription(this.this$0).size();
            ThemeEditorView r2 = this.this$0;
            if (r0 >= r1) goto L9;
            ThemeDescription r12 = (ThemeDescription) ThemeEditorView.m13251$$Nest$fgetcurrentThemeDesription(r2).get(r0);
            r12.startEditing();
            if (r0 != 0) goto L8;
            EditorAlert.m13273$$Nest$fgetcolorPicker(ThemeEditorView.m13254$$Nest$fgeteditorAlert(this.this$0)).setColor(r12.getCurrentColor());
        L8:
            r0 = r0 + 1;
            goto L3
        L9:
            EditorAlert.m13294$$Nest$msetColorPickerVisible(ThemeEditorView.m13254$$Nest$fgeteditorAlert(r2), true);
        }
    }

    /* renamed from: org.telegram.ui.Components.ThemeEditorView$3, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ ThemeEditorView this$0;

        public AnonymousClass3(ThemeEditorView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (ThemeEditorView.m13261$$Nest$fgetwindowView(this.this$0) == null) goto L6;
            ThemeEditorView.m13261$$Nest$fgetwindowView(this.this$0).setBackground(null);
            ThemeEditorView.m13260$$Nest$fgetwindowManager(this.this$0).removeView(ThemeEditorView.m13261$$Nest$fgetwindowView(this.this$0));
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ThemeEditorView$4, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ ThemeEditorView this$0;

        public AnonymousClass4(ThemeEditorView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            Theme.saveCurrentTheme(ThemeEditorView.m13257$$Nest$fgetthemeInfo(this.this$0), true, false, false);
            this.this$0.destroy();
        }
    }

    /* loaded from: classes7.dex */
    public class EditorAlert extends BottomSheet {
        private boolean animationInProgress;
        private FrameLayout bottomLayout;
        private FrameLayout bottomSaveLayout;
        private AnimatorSet colorChangeAnimation;
        private ColorPicker colorPicker;
        private FrameLayout frameLayout;
        private boolean ignoreTextChange;
        private LinearLayoutManager layoutManager;
        private ListAdapter listAdapter;
        private RecyclerListView listView;
        private int previousScrollPosition;
        private int scrollOffsetY;
        private SearchAdapter searchAdapter;
        private EmptyTextProgressView searchEmptyView;
        private SearchField searchField;
        private View[] shadow;
        private AnimatorSet[] shadowAnimation;
        private Drawable shadowDrawable;
        private boolean startedColorChange;
        final /* synthetic */ ThemeEditorView this$0;
        private int topBeforeSwitch;

        /* renamed from: org.telegram.ui.Components.ThemeEditorView$EditorAlert$1, reason: invalid class name */
        public class AnonymousClass1 extends FrameLayout {
            private boolean ignoreLayout;
            private RectF rect1;
            private Boolean statusBarOpen;
            final /* synthetic */ EditorAlert this$1;
            final /* synthetic */ ThemeEditorView val$this$0;

            public AnonymousClass1(EditorAlert r1, Context r2, ThemeEditorView r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
                this.ignoreLayout = false;
                this.rect1 = new RectF();
            }

            private void updateLightStatusBar(boolean r7) {
                Boolean r0 = this.statusBarOpen;
                if (r0 != null) goto L5;
            L7:
                boolean r2 = false;
                if (AndroidUtilities.computePerceivedBrightness(EditorAlert.access$2000(this.this$1, Theme.key_dialogBackground)) <= 0.721f) goto L10;
                boolean r02 = true;
            L12:
                if (AndroidUtilities.computePerceivedBrightness(Theme.blendOver(EditorAlert.access$2100(this.this$1, Theme.key_actionBarDefault), 855638016)) <= 0.721f) goto L14;
                r2 = true;
            L14:
                this.statusBarOpen = Boolean.valueOf(r7);
                if (r7 == true) goto L18;
                r02 = r2;
            L18:
                AndroidUtilities.setLightStatusBar(this.this$1.getWindow(), r02);
                return;
            L10:
                r02 = false;
                goto L12
            L5:
                if (r0.booleanValue() != r7) goto L7;
            }

            @Override // android.view.View
            public void onDraw(Canvas r14) {
                int r0 = (EditorAlert.m13278$$Nest$fgetscrollOffsetY(this.this$1) - EditorAlert.access$600(this.this$1)) + AndroidUtilities.dp(6.0f);
                int r1 = (EditorAlert.m13278$$Nest$fgetscrollOffsetY(this.this$1) - EditorAlert.access$700(this.this$1)) - AndroidUtilities.dp(8.0f);
                int r2 = (getMeasuredHeight() + AndroidUtilities.dp(30.0f)) + EditorAlert.access$800(this.this$1);
                boolean r4 = false;
                if (EditorAlert.access$900(this.this$1) == true) goto L12;
                int r3 = AndroidUtilities.statusBarHeight;
                r1 = r1 + r3;
                r0 = r0 + r3;
                r2 = r2 - r3;
                int r32 = EditorAlert.access$1000(this.this$1) + r1;
                int r6 = AndroidUtilities.statusBarHeight;
                if (r32 >= (r6 * 2)) goto L7;
                int r33 = Math.min(r6, ((r6 * 2) - r1) - EditorAlert.access$1100(this.this$1));
                r1 = r1 - r33;
                r2 = r2 + r33;
                float r34 = 1.0f - Math.min(1.0f, (r33 * 2) / AndroidUtilities.statusBarHeight);
            L8:
                int r62 = EditorAlert.access$1200(this.this$1) + r1;
                int r7 = AndroidUtilities.statusBarHeight;
                if (r62 >= r7) goto L11;
                int r63 = Math.min(r7, (r7 - r1) - EditorAlert.access$1300(this.this$1));
            L13:
                EditorAlert.m13284$$Nest$fgetshadowDrawable(this.this$1).setBounds(0, r1, getMeasuredWidth(), r2);
                EditorAlert.m13284$$Nest$fgetshadowDrawable(this.this$1).draw(r14);
                if (r34 == 1.0f) goto L16;
                Theme.dialogs_onlineCirclePaint.setColor(-1);
                this.rect1.set(EditorAlert.access$1400(this.this$1), EditorAlert.access$1500(this.this$1) + r1, getMeasuredWidth() - EditorAlert.access$1600(this.this$1), (EditorAlert.access$1700(this.this$1) + r1) + AndroidUtilities.dp(24.0f));
                r14.drawRoundRect(this.rect1, AndroidUtilities.dp(12.0f) * r34, AndroidUtilities.dp(12.0f) * r34, Theme.dialogs_onlineCirclePaint);
            L16:
                int r12 = AndroidUtilities.dp(36.0f);
                this.rect1.set((getMeasuredWidth() - r12) / 2, r0, (getMeasuredWidth() + r12) / 2, r0 + AndroidUtilities.dp(4.0f));
                Theme.dialogs_onlineCirclePaint.setColor(-1973016);
                Theme.dialogs_onlineCirclePaint.setAlpha((int) (EditorAlert.m13277$$Nest$fgetlistView(this.this$1).getAlpha() * 255.0f));
                r14.drawRoundRect(this.rect1, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), Theme.dialogs_onlineCirclePaint);
                if (r63 <= 0) goto L20;
                Theme.dialogs_onlineCirclePaint.setColor(Theme.getColor(Theme.key_dialogBackground));
                r14.drawRect(EditorAlert.access$1800(this.this$1), AndroidUtilities.statusBarHeight - r63, getMeasuredWidth() - EditorAlert.access$1900(this.this$1), AndroidUtilities.statusBarHeight, Theme.dialogs_onlineCirclePaint);
            L20:
                if (r63 <= (AndroidUtilities.statusBarHeight / 2)) goto L22;
                r4 = true;
            L22:
                updateLightStatusBar(r4);
                return;
            L11:
                r63 = 0;
                goto L13
            L7:
                r34 = 1.0f;
                goto L8
            L12:
                r63 = 0;
                r34 = 1.0f;
                goto L13
            }

            @Override // android.view.ViewGroup
            public boolean onInterceptTouchEvent(MotionEvent r3) {
                if (r3.getAction() != 0) goto L11;
                if (EditorAlert.m13278$$Nest$fgetscrollOffsetY(this.this$1) == 0) goto L11;
                if (r3.getY() >= EditorAlert.m13278$$Nest$fgetscrollOffsetY(this.this$1)) goto L11;
                this.this$1.dismiss();
                return true;
            L11:
                return super.onInterceptTouchEvent(r3);
            }

            @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
            public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
                super.onLayout(r1, r2, r3, r4, r5);
                EditorAlert.m13295$$Nest$mupdateLayout(this.this$1);
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r7, int r8) {
                int r0 = View.MeasureSpec.getSize(r7);
                int r82 = View.MeasureSpec.getSize(r8);
                if (EditorAlert.access$300(this.this$1) == true) goto L5;
                this.ignoreLayout = true;
                setPadding(EditorAlert.access$400(this.this$1), AndroidUtilities.statusBarHeight, EditorAlert.access$500(this.this$1), 0);
                this.ignoreLayout = false;
            L5:
                int r02 = Math.min(r0, r82 - AndroidUtilities.statusBarHeight);
                int r1 = ((r82 - AndroidUtilities.statusBarHeight) + AndroidUtilities.dp(8.0f)) - r02;
                if (EditorAlert.m13277$$Nest$fgetlistView(this.this$1).getPaddingTop() == r1) goto L11;
                this.ignoreLayout = true;
                EditorAlert.m13277$$Nest$fgetlistView(this.this$1).getPaddingTop();
                EditorAlert.m13277$$Nest$fgetlistView(this.this$1).setPadding(0, r1, 0, AndroidUtilities.dp(48.0f));
                if (EditorAlert.m13273$$Nest$fgetcolorPicker(this.this$1).getVisibility() != 0) goto L10;
                EditorAlert r03 = this.this$1;
                r03.setScrollOffsetY(EditorAlert.m13277$$Nest$fgetlistView(r03).getPaddingTop());
                EditorAlert.m13290$$Nest$fputpreviousScrollPosition(this.this$1, 0);
            L10:
                this.ignoreLayout = false;
            L11:
                super.onMeasure(r7, View.MeasureSpec.makeMeasureSpec(r82, TLObject.FLAG_30));
            }

            @Override // android.view.View
            public boolean onTouchEvent(MotionEvent r2) {
                if (this.this$1.isDismissed() == false) goto L5;
                return false;
            L5:
                if (super.onTouchEvent(r2) == false) goto L10;
                return true;
            L10:
                return false;
            }

            @Override // android.view.View, android.view.ViewParent
            public void requestLayout() {
                if (this.ignoreLayout == false) goto L5;
                return;
            L5:
                super.requestLayout();
            }
        }

        /* renamed from: org.telegram.ui.Components.ThemeEditorView$EditorAlert$2, reason: invalid class name */
        public class AnonymousClass2 extends RecyclerListView {
            final /* synthetic */ EditorAlert this$1;
            final /* synthetic */ ThemeEditorView val$this$0;

            public AnonymousClass2(EditorAlert r1, Context r2, ThemeEditorView r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // org.telegram.ui.Components.RecyclerListView
            public boolean allowSelectChildAtPosition(float r1, float r2) {
                if (r2 < ((EditorAlert.m13278$$Nest$fgetscrollOffsetY(this.this$1) + AndroidUtilities.dp(48.0f)) + AndroidUtilities.statusBarHeight)) goto L6;
                return true;
            L6:
                return false;
            }
        }

        /* renamed from: org.telegram.ui.Components.ThemeEditorView$EditorAlert$3, reason: invalid class name */
        public class AnonymousClass3 extends RecyclerView.OnScrollListener {
            final /* synthetic */ EditorAlert this$1;
            final /* synthetic */ ThemeEditorView val$this$0;

            public AnonymousClass3(EditorAlert r1, ThemeEditorView r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView r1, int r2, int r3) {
                EditorAlert.m13295$$Nest$mupdateLayout(this.this$1);
            }
        }

        /* renamed from: org.telegram.ui.Components.ThemeEditorView$EditorAlert$4, reason: invalid class name */
        public class AnonymousClass4 extends AnimatorListenerAdapter {
            final /* synthetic */ EditorAlert this$1;
            final /* synthetic */ int val$num;
            final /* synthetic */ boolean val$show;

            public AnonymousClass4(EditorAlert r1, int r2, boolean r3) {
                this.this$1 = r1;
                this.val$num = r2;
                this.val$show = r3;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator r3) {
                if (EditorAlert.m13283$$Nest$fgetshadowAnimation(this.this$1)[this.val$num] != null) goto L5;
                return;
            L5:
                if (EditorAlert.m13283$$Nest$fgetshadowAnimation(this.this$1)[this.val$num].equals(r3) == false) goto L9;
                EditorAlert.m13283$$Nest$fgetshadowAnimation(this.this$1)[this.val$num] = null;
                return;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r3) {
                if (EditorAlert.m13283$$Nest$fgetshadowAnimation(this.this$1)[this.val$num] != null) goto L5;
                return;
            L5:
                if (EditorAlert.m13283$$Nest$fgetshadowAnimation(this.this$1)[this.val$num].equals(r3) == true) goto L7;
                return;
            L7:
                if (this.val$show == true) goto L9;
                EditorAlert.m13282$$Nest$fgetshadow(this.this$1)[this.val$num].setVisibility(4);
            L9:
                EditorAlert.m13283$$Nest$fgetshadowAnimation(this.this$1)[this.val$num] = null;
            }
        }

        /* renamed from: org.telegram.ui.Components.ThemeEditorView$EditorAlert$5, reason: invalid class name */
        public class AnonymousClass5 extends AnimatorListenerAdapter {
            final /* synthetic */ EditorAlert this$1;

            public AnonymousClass5(EditorAlert r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                EditorAlert.m13277$$Nest$fgetlistView(this.this$1).setVisibility(4);
                EditorAlert.m13281$$Nest$fgetsearchField(this.this$1).setVisibility(4);
                EditorAlert.m13271$$Nest$fgetbottomSaveLayout(this.this$1).setVisibility(4);
                EditorAlert.m13287$$Nest$fputanimationInProgress(this.this$1, false);
            }
        }

        /* renamed from: org.telegram.ui.Components.ThemeEditorView$EditorAlert$6, reason: invalid class name */
        public class AnonymousClass6 extends AnimatorListenerAdapter {
            final /* synthetic */ EditorAlert this$1;

            public AnonymousClass6(EditorAlert r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                if (EditorAlert.m13277$$Nest$fgetlistView(this.this$1).getAdapter() != EditorAlert.m13279$$Nest$fgetsearchAdapter(this.this$1)) goto L5;
                EditorAlert.m13281$$Nest$fgetsearchField(this.this$1).showKeyboard();
            L5:
                EditorAlert.m13273$$Nest$fgetcolorPicker(this.this$1).setVisibility(8);
                EditorAlert.m13270$$Nest$fgetbottomLayout(this.this$1).setVisibility(8);
                EditorAlert.m13287$$Nest$fputanimationInProgress(this.this$1, false);
            }
        }

        public class ColorPicker extends FrameLayout {
            private float alpha;
            private LinearGradient alphaGradient;
            private boolean alphaPressed;
            private Paint circlePaint;
            private boolean circlePressed;
            private final EditTextBoldCursor[] colorEditText;
            private LinearGradient colorGradient;
            private float[] colorHSV;
            private boolean colorPressed;
            private Bitmap colorWheelBitmap;
            private Paint colorWheelPaint;
            private int colorWheelRadius;
            private DecelerateInterpolator decelerateInterpolator;
            private float[] hsvTemp;
            private LinearLayout linearLayout;
            private final OutlineTextContainerView[] outlineContainer;
            private final int paramValueSliderWidth;
            final /* synthetic */ EditorAlert this$1;
            private Paint valueSliderPaint;

            /* renamed from: org.telegram.ui.Components.ThemeEditorView$EditorAlert$ColorPicker$1, reason: invalid class name */
            public class AnonymousClass1 implements TextWatcher {
                final /* synthetic */ ColorPicker this$2;
                final /* synthetic */ int val$num;
                final /* synthetic */ EditorAlert val$this$1;

                public AnonymousClass1(ColorPicker r1, EditorAlert r2, int r3) {
                    this.this$2 = r1;
                    this.val$this$1 = r2;
                    this.val$num = r3;
                }

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable r10) {
                    if (EditorAlert.m13274$$Nest$fgetignoreTextChange(this.this$2.this$1) == false) goto L5;
                    return;
                L5:
                    EditorAlert.m13289$$Nest$fputignoreTextChange(this.this$2.this$1, true);
                    if (this.val$num != 0) goto L39;
                    int r0 = 0;
                L9:
                    if (r0 >= r10.length()) goto L28;
                    char r6 = r10.charAt(r0);
                    if (r6 != '#') goto L14;
                    if (r0 != 0) goto L14;
                L26:
                    r0 = r0 + 1;
                L14:
                    if (r6 < '0') goto L18;
                    if (r6 <= '9') goto L26;
                L18:
                    if (r6 < 'a') goto L22;
                    if (r6 <= 'f') goto L26;
                L22:
                    if (r6 >= 'A') goto L24;
                L25:
                    r10.replace(r0, r0 + 1, _UrlKt.FRAGMENT_ENCODE_SET);
                    r0 = r0 - 1;
                    goto L26
                L24:
                    if (r6 <= 'F') goto L26;
                L28:
                    if (r10.length() != 0) goto L30;
                L31:
                    r10.insert(0, "#");
                L32:
                    int r02 = r10.length();
                    ColorPicker r4 = this.this$2;
                    if (r02 == 9) goto L53;
                    EditorAlert.m13289$$Nest$fputignoreTextChange(r4.this$1, false);
                    return;
                L53:
                    r4.setColor(Color.parseColor(r10.toString()));     // Catch: IllegalArgumentException -> L38
                L47:
                    int r102 = this.this$2.getColor();
                    ColorPicker.m13296$$Nest$fgetcolorEditText(this.this$2)[0].setTextKeepState(String.format("#%02x%02x%02x%02x", new Object[]{Byte.valueOf((byte) Color.alpha(r102)), Byte.valueOf((byte) Color.red(r102)), Byte.valueOf((byte) Color.green(r102)), Byte.valueOf((byte) Color.blue(r102))}).toUpperCase());
                    ColorPicker.m13296$$Nest$fgetcolorEditText(this.this$2)[1].setTextKeepState(String.valueOf((int) (ColorPicker.m13297$$Nest$fgetcolorHSV(this.this$2)[2] * 100.0f)));
                    this.this$2.setColor(r102);
                    int r103 = 0;
                L48:
                    int r03 = ThemeEditorView.m13251$$Nest$fgetcurrentThemeDesription(this.this$2.this$1.this$0).size();
                    ColorPicker r1 = this.this$2;
                    if (r103 >= r03) goto L51;
                    ((ThemeDescription) ThemeEditorView.m13251$$Nest$fgetcurrentThemeDesription(r1.this$1.this$0).get(r103)).setColor(this.this$2.getColor(), false);
                    r103 = r103 + 1;
                    goto L48
                L51:
                    EditorAlert.m13289$$Nest$fputignoreTextChange(r1.this$1, false);
                    return;
                L38:
                    this.this$2.setColor(-1);
                    goto L47
                L30:
                    if (r10.charAt(0) == '#') goto L32;
                L39:
                    int r04 = Utilities.parseInt(r10.toString());
                    if (r04 > 100) goto L42;
                    if (r04 < 0) goto L42;
                L46:
                    ColorPicker.m13297$$Nest$fgetcolorHSV(this.this$2)[2] = r04 / 100.0f;
                L42:
                    if (r04 <= 100) goto L44;
                    r04 = 100;
                L45:
                    r10.replace(0, r10.length(), r04 + _UrlKt.FRAGMENT_ENCODE_SET);
                    goto L46
                L44:
                    r04 = 0;
                    goto L45
                }

                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
                }
            }

            public static /* synthetic */ void $r8$lambda$BWAgYfPp5uyYjPiG0e11afmbNnY(ColorPicker r0, int r1, View r2, boolean r3) {
                r0.lambda$new$0(r1, r2, r3);
            }

            public static /* synthetic */ boolean $r8$lambda$HYq2xFJx5fikv5JF6WhAv7tzfx8(TextView r0, int r1, KeyEvent r2) {
                if (r1 != 6) goto L6;
                AndroidUtilities.hideKeyboard(r0);
                return true;
            L6:
                return false;
            }

            /* renamed from: -$$Nest$fgetcolorEditText, reason: not valid java name */
            public static /* bridge */ /* synthetic */ EditTextBoldCursor[] m13296$$Nest$fgetcolorEditText(ColorPicker r0) {
                return r0.colorEditText;
            }

            /* renamed from: -$$Nest$fgetcolorHSV, reason: not valid java name */
            public static /* bridge */ /* synthetic */ float[] m13297$$Nest$fgetcolorHSV(ColorPicker r0) {
                return r0.colorHSV;
            }

            public ColorPicker(EditorAlert r19, Context r20) {
                this.this$1 = r19;
                super(r20);
                this.paramValueSliderWidth = AndroidUtilities.dp(20.0f);
                this.colorEditText = new EditTextBoldCursor[2];
                this.outlineContainer = new OutlineTextContainerView[2];
                this.colorHSV = new float[]{0.0f, 0.0f, 1.0f};
                this.alpha = 1.0f;
                this.hsvTemp = new float[3];
                this.decelerateInterpolator = new DecelerateInterpolator();
                setWillNotDraw(false);
                this.circlePaint = new Paint(1);
                Paint r7 = new Paint();
                this.colorWheelPaint = r7;
                r7.setAntiAlias(true);
                this.colorWheelPaint.setDither(true);
                Paint r72 = new Paint();
                this.valueSliderPaint = r72;
                r72.setAntiAlias(true);
                this.valueSliderPaint.setDither(true);
                LinearLayout r73 = new LinearLayout(r20);
                this.linearLayout = r73;
                r73.setOrientation(0);
                addView(this.linearLayout, LayoutHelper.createFrame(-2, -2, 49));
                int r74 = 0;
            L3:
                if (r74 >= 2) goto L30;
                this.outlineContainer[r74] = new OutlineTextContainerView(r20);
                this.outlineContainer[r74].setUseDefaultColor(true);
                this.outlineContainer[r74].updateColor();
                OutlineTextContainerView r9 = this.outlineContainer[r74];
                if (r74 != 0) goto L7;
                String r10 = "HEX";
            L8:
                r9.setText(r10);
                LinearLayout r92 = this.linearLayout;
                OutlineTextContainerView r102 = this.outlineContainer[r74];
                if (r74 != 0) goto L12;
                int r11 = 125;
            L11:
                int r12 = r11;
                if (r74 != 0) goto L16;
                float r112 = 16.0f;
            L17:
                r92.addView(r102, LayoutHelper.createLinear(r12, 48, 0.0f, 0.0f, r112, 0.0f));
                this.colorEditText[r74] = new EditTextBoldCursor(r20);
                this.colorEditText[r74].setTextColor(-14606047);
                this.colorEditText[r74].setCursorColor(-14606047);
                this.colorEditText[r74].setCursorSize(AndroidUtilities.dp(20.0f));
                this.colorEditText[r74].setCursorWidth(1.5f);
                this.colorEditText[r74].setTextSize(1, 18.0f);
                this.colorEditText[r74].setBackground(null);
                this.colorEditText[r74].setMaxLines(1);
                this.colorEditText[r74].setTag(Integer.valueOf(r74));
                int r93 = AndroidUtilities.dp(2.0f);
                this.colorEditText[r74].setPadding(r93, r93, r93, r93);
                this.colorEditText[r74].setGravity(17);
                EditTextBoldCursor[] r94 = this.colorEditText;
                if (r74 != 0) goto L20;
                r94[r74].setInputType(1);
            L21:
                EditTextBoldCursor r95 = this.colorEditText[r74];
                if (r74 != 1) goto L24;
                int r103 = 6;
            L25:
                r95.setImeOptions(r103 | 268435456);
                if (r74 != 0) goto L28;
                int r104 = 9;
            L29:
                this.colorEditText[r74].setFilters(new InputFilter[]{new InputFilter.LengthFilter(r104)});
                this.colorEditText[r74].setOnFocusChangeListener(new ThemeEditorView$EditorAlert$ColorPicker$$ExternalSyntheticLambda0(this, r74));
                this.outlineContainer[r74].addView(this.colorEditText[r74], LayoutHelper.createFrame(-1, 42.0f));
                this.outlineContainer[r74].attachEditText(this.colorEditText[r74]);
                this.colorEditText[r74].addTextChangedListener(new AnonymousClass1(this, r19, r74));
                this.colorEditText[r74].setOnEditorActionListener(new ThemeEditorView$EditorAlert$ColorPicker$$ExternalSyntheticLambda1());
                r74 = r74 + 1;
                goto L3
            L28:
                r104 = 3;
                goto L29
            L24:
                r103 = 5;
                goto L25
            L20:
                r94[r74].setInputType(2);
                goto L21
            L16:
                r112 = 0.0f;
                goto L17
            L12:
                r11 = 65;
                goto L11
            L7:
                r10 = "B%";
                goto L8
            }

            private Bitmap createColorWheelBitmap(int r13, int r14) {
                Bitmap r0 = Bitmap.createBitmap(r13, r14, Bitmap.Config.ARGB_8888);
                int[] r2 = new int[13];
                float[] r3 = {0.0f, 1.0f, 1.0f};
                int r6 = 0;
            L3:
                if (r6 >= 13) goto L5;
                r3[0] = ((r6 * 30) + 180) % 360;
                r2[r6] = Color.HSVToColor(r3);
                r6 = r6 + 1;
                goto L3
            L5:
                r2[12] = r2[0];
                float r62 = r13 / 2;
                float r7 = r14 / 2;
                this.colorWheelPaint.setShader(new ComposeShader(new SweepGradient(r62, r7, r2, null), new RadialGradient(r62, r7, this.colorWheelRadius, -1, 16777215, Shader.TileMode.CLAMP), PorterDuff.Mode.SRC_OVER));
                new Canvas(r0).drawCircle(r62, r7, this.colorWheelRadius, this.colorWheelPaint);
                return r0;
            }

            private void drawPointerArrow(Canvas r3, int r4, int r5, int r6) {
                AndroidUtilities.dp(13.0f);
                this.circlePaint.setColor(-9408400);
                float r42 = r4;
                float r52 = r5;
                r3.drawCircle(r42, r52, AndroidUtilities.dp(11.5f), this.circlePaint);
                this.circlePaint.setColor(-1);
                r3.drawCircle(r42, r52, AndroidUtilities.dp(11.0f), this.circlePaint);
                this.circlePaint.setColor(r6);
                r3.drawCircle(r42, r52, AndroidUtilities.dp(9.0f), this.circlePaint);
            }

            private /* synthetic */ void lambda$new$0(int r1, View r2, boolean r3) {
                OutlineTextContainerView r0 = this.outlineContainer[r1];
                if (r3 == false) goto L5;
                float r12 = 1.0f;
            L6:
                r0.animateSelection(r12);
                return;
            L5:
                r12 = 0.0f;
                goto L6
            }

            private void startColorChange(boolean r7) {
                if (EditorAlert.m13285$$Nest$fgetstartedColorChange(this.this$1) != r7) goto L6;
                return;
            L6:
                if (EditorAlert.m13272$$Nest$fgetcolorChangeAnimation(this.this$1) == null) goto L8;
                EditorAlert.m13272$$Nest$fgetcolorChangeAnimation(this.this$1).cancel();
            L8:
                EditorAlert.m13291$$Nest$fputstartedColorChange(this.this$1, r7);
                EditorAlert.m13288$$Nest$fputcolorChangeAnimation(this.this$1, new AnimatorSet());
                AnimatorSet r0 = EditorAlert.m13272$$Nest$fgetcolorChangeAnimation(this.this$1);
                BottomSheet.SheetBackDrawable r1 = EditorAlert.access$100(this.this$1);
                Property<Drawable, Integer> r2 = AnimationProperties.COLOR_DRAWABLE_ALPHA;
                if (r7 == false) goto L11;
                int r4 = 0;
            L12:
                ObjectAnimator r12 = ObjectAnimator.ofInt(r1, r2, new int[]{r4});
                ViewGroup r22 = EditorAlert.access$200(this.this$1);
                if (r7 == false) goto L15;
                float r72 = 0.2f;
            L16:
                r0.playTogether(new Animator[]{r12, ObjectAnimator.ofFloat(r22, View.ALPHA, new float[]{r72})});
                EditorAlert.m13272$$Nest$fgetcolorChangeAnimation(this.this$1).setDuration(150);
                EditorAlert.m13272$$Nest$fgetcolorChangeAnimation(this.this$1).setInterpolator(this.decelerateInterpolator);
                EditorAlert.m13272$$Nest$fgetcolorChangeAnimation(this.this$1).start();
                return;
            L15:
                r72 = 1.0f;
                goto L16
            L11:
                r4 = 51;
                goto L12
            }

            public int getColor() {
                int r0 = Color.HSVToColor(this.colorHSV) & 16777215;
                return (((int) (this.alpha * 255.0f)) << 24) | r0;
            }

            @Override // android.view.View
            public void onDraw(Canvas r25) {
                int r2 = (getWidth() / 2) - (this.paramValueSliderWidth * 2);
                int r3 = getHeight() / 2;
                Bitmap r4 = this.colorWheelBitmap;
                int r5 = this.colorWheelRadius;
                r25.drawBitmap(r4, r2 - r5, r3 - r5, null);
                double r6 = (float) Math.toRadians(this.colorHSV[0]);
                int r42 = ((int) (((-Math.cos(r6)) * this.colorHSV[1]) * this.colorWheelRadius)) + r2;
                double r62 = -Math.sin(r6);
                float[] r10 = this.colorHSV;
                float r11 = r10[1];
                float[] r7 = this.hsvTemp;
                r7[0] = r10[0];
                r7[1] = r11;
                r7[2] = 1.0f;
                drawPointerArrow(r25, r42, ((int) ((r62 * r11) * this.colorWheelRadius)) + r3, Color.HSVToColor(r7));
                int r43 = this.colorWheelRadius;
                int r112 = (r2 + r43) + this.paramValueSliderWidth;
                int r32 = r3 - r43;
                int r12 = AndroidUtilities.dp(9.0f);
                int r13 = this.colorWheelRadius * 2;
                if (this.colorGradient != null) goto L5;
                this.colorGradient = new LinearGradient(r112, r32, r112 + r12, r32 + r13, new int[]{-16777216, Color.HSVToColor(this.hsvTemp)}, null, Shader.TileMode.CLAMP);
            L5:
                this.valueSliderPaint.setShader(this.colorGradient);
                float r33 = r32;
                float r44 = r32 + r13;
                r25.drawRoundRect(r112, r33, r112 + r12, r44, 100.0f, 100.0f, this.valueSliderPaint);
                int r22 = r12 / 2;
                float[] r45 = this.colorHSV;
                float r132 = r13;
                drawPointerArrow(r25, r112 + r22, (int) ((r45[2] * r132) + r33), Color.HSVToColor(r45));
                int r113 = r112 + (this.paramValueSliderWidth * 2);
                if (this.alphaGradient != null) goto L8;
                int r23 = Color.HSVToColor(this.hsvTemp);
                this.alphaGradient = new LinearGradient(r113, r33, r113 + r12, r44, new int[]{r23, r23 & 16777215}, null, Shader.TileMode.CLAMP);
            L8:
                this.valueSliderPaint.setShader(this.alphaGradient);
                r25.drawRoundRect(r113, r33, r12 + r113, r44, 100.0f, 100.0f, this.valueSliderPaint);
                drawPointerArrow(r25, r113 + r22, (int) (r33 + ((1.0f - this.alpha) * r132)), (Color.HSVToColor(this.colorHSV) & 16777215) | (((int) (this.alpha * 255.0f)) << 24));
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r4, int r5) {
                int r42 = View.MeasureSpec.getSize(r4);
                int r0 = Math.min(r42, View.MeasureSpec.getSize(r5));
                measureChild(this.linearLayout, View.MeasureSpec.makeMeasureSpec(r42 - AndroidUtilities.dp(42.0f), TLObject.FLAG_30), r5);
                setMeasuredDimension(r0, r0);
            }

            @Override // android.view.View
            public void onSizeChanged(int r1, int r2, int r3, int r4) {
                int r12 = Math.max(1, ((r1 / 2) - (this.paramValueSliderWidth * 2)) - AndroidUtilities.dp(20.0f));
                this.colorWheelRadius = r12;
                this.colorWheelBitmap = createColorWheelBitmap(r12 * 2, r12 * 2);
                this.colorGradient = null;
                this.alphaGradient = null;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.View
            public boolean onTouchEvent(MotionEvent r18) {
                int r1 = r18.getAction();
                if (r1 == 0) goto L10;
                if (r1 == 1) goto L7;
                if (r1 == 2) goto L10;
            L9:
                return super.onTouchEvent(r18);
            L7:
                this.alphaPressed = false;
                this.colorPressed = false;
                this.circlePressed = false;
                startColorChange(false);
            L10:
                int r12 = (int) r18.getX();
                int r5 = (int) r18.getY();
                int r6 = (getWidth() / 2) - (this.paramValueSliderWidth * 2);
                int r7 = (getHeight() / 2) - AndroidUtilities.dp(8.0f);
                int r8 = r12 - r6;
                int r9 = r5 - r7;
                double r10 = Math.sqrt((r8 * r8) + (r9 * r9));
                if (this.circlePressed == false) goto L13;
                int r15 = 2;
                int r16 = 0;
            L21:
                int r2 = this.colorWheelRadius;
                if (r10 <= r2) goto L24;
                r10 = r2;
            L24:
                this.circlePressed = true;
                this.colorHSV[r16] = (float) (Math.toDegrees(Math.atan2(r9, r8)) + 180.0d);
                this.colorHSV[1] = Math.max(0.0f, Math.min(1.0f, (float) (r10 / this.colorWheelRadius)));
                this.colorGradient = null;
                this.alphaGradient = null;
            L26:
                if (this.colorPressed == false) goto L28;
            L39:
                float r22 = (r5 - (r7 - r2)) / (this.colorWheelRadius * 2.0f);
                if (r22 >= 0.0f) goto L43;
                r22 = 0.0f;
            L45:
                this.colorHSV[r15] = r22;
                this.colorPressed = true;
            L47:
                if (this.alphaPressed == false) goto L49;
            L60:
                float r14 = 1.0f - ((r5 - (r7 - r1)) / (this.colorWheelRadius * 2.0f));
                this.alpha = r14;
                if (r14 >= 0.0f) goto L64;
                this.alpha = 0.0f;
            L66:
                this.alphaPressed = true;
            L68:
                if (this.alphaPressed == false) goto L70;
            L73:
                startColorChange(true);
                int r13 = getColor();
                int r23 = r16;
            L74:
                int r3 = ThemeEditorView.m13251$$Nest$fgetcurrentThemeDesription(this.this$1.this$0).size();
                EditorAlert r52 = this.this$1;
                if (r23 >= r3) goto L92;
                int r32 = ((ThemeDescription) ThemeEditorView.m13251$$Nest$fgetcurrentThemeDesription(r52.this$0).get(r23)).getCurrentKey();
                if (r23 != 0) goto L81;
                if (r32 != Theme.key_chat_wallpaper) goto L81;
            L90:
                r13 = r13 | (-16777216);
            L91:
                ((ThemeDescription) ThemeEditorView.m13251$$Nest$fgetcurrentThemeDesription(this.this$1.this$0).get(r23)).setColor(r13, r16);
                r23 = r23 + 1;
            L81:
                if (r32 == Theme.key_chat_wallpaper_gradient_to1) goto L90;
                if (r32 == Theme.key_chat_wallpaper_gradient_to2) goto L90;
                if (r32 == Theme.key_chat_wallpaper_gradient_to3) goto L90;
                if (r32 == Theme.key_windowBackgroundWhite) goto L90;
                if (r32 != Theme.key_windowBackgroundGray) goto L91;
            L92:
                int r62 = r16;
                if (EditorAlert.m13274$$Nest$fgetignoreTextChange(r52) == true) goto L98;
                EditorAlert.m13289$$Nest$fputignoreTextChange(this.this$1, true);
                this.colorEditText[r62].setTextKeepState(String.format("#%02x%02x%02x%02x", new Object[]{Byte.valueOf((byte) Color.alpha(r13)), Byte.valueOf((byte) Color.red(r13)), Byte.valueOf((byte) Color.green(r13)), Byte.valueOf((byte) Color.blue(r13))}).toUpperCase());
                this.colorEditText[1].setTextKeepState(String.valueOf((int) (this.colorHSV[r15] * 100.0f)));
                int r63 = 0;
            L95:
                if (r63 >= r15) goto L97;
                EditTextBoldCursor r17 = this.colorEditText[r63];
                r17.setSelection(r17.length());
                r63 = r63 + 1;
                goto L95
            L97:
                EditorAlert.m13289$$Nest$fputignoreTextChange(this.this$1, false);
            L98:
                invalidate();
            L99:
                return true;
            L70:
                if (this.colorPressed == true) goto L73;
                if (this.circlePressed == false) goto L99;
            L64:
                if (r14 <= 1.0f) goto L66;
                this.alpha = 1.0f;
                goto L66
            L49:
                if (this.circlePressed == true) goto L68;
                if (this.colorPressed == true) goto L68;
                int r24 = this.colorWheelRadius;
                int r102 = this.paramValueSliderWidth;
                if (r12 < ((r6 + r24) + (r102 * 3))) goto L68;
                if (r12 > ((r6 + r24) + (r102 * 4))) goto L68;
                if (r5 < (r7 - r24)) goto L68;
                if (r5 > (r24 + r7)) goto L68;
            L43:
                if (r22 <= 1.0f) goto L45;
                r22 = 1.0f;
                goto L45
            L28:
                if (this.circlePressed == true) goto L47;
                if (this.alphaPressed == true) goto L47;
                int r25 = this.colorWheelRadius;
                int r103 = this.paramValueSliderWidth;
                if (r12 < ((r6 + r25) + r103)) goto L47;
                if (r12 > ((r6 + r25) + (r103 * r15))) goto L47;
                if (r5 < (r7 - r25)) goto L47;
                if (r5 > (r25 + r7)) goto L47;
            L13:
                if (this.alphaPressed == false) goto L15;
            L19:
                r15 = 2;
                r16 = 0;
                goto L26
            L15:
                if (this.colorPressed == true) goto L19;
                r15 = 2;
                r16 = 0;
                if (r10 > this.colorWheelRadius) goto L26;
                goto L21
            }

            public void setColor(int r9) {
                int r0 = Color.alpha(r9);
                if (EditorAlert.m13274$$Nest$fgetignoreTextChange(this.this$1) == true) goto L8;
                EditorAlert.m13289$$Nest$fputignoreTextChange(this.this$1, true);
                this.colorEditText[0].setTextKeepState(String.format("#%02x%02x%02x%02x", new Object[]{Byte.valueOf((byte) Color.alpha(r9)), Byte.valueOf((byte) Color.red(r9)), Byte.valueOf((byte) Color.green(r9)), Byte.valueOf((byte) Color.blue(r9))}).toUpperCase());
                this.colorEditText[1].setTextKeepState(String.valueOf((int) (this.colorHSV[2] * 100.0f)));
                int r1 = 0;
            L5:
                if (r1 >= 2) goto L7;
                EditTextBoldCursor r2 = this.colorEditText[r1];
                r2.setSelection(r2.length());
                r1 = r1 + 1;
                goto L5
            L7:
                EditorAlert.m13289$$Nest$fputignoreTextChange(this.this$1, false);
            L8:
                this.alphaGradient = null;
                this.colorGradient = null;
                this.alpha = r0 / 255.0f;
                Color.colorToHSV(r9, this.colorHSV);
                invalidate();
            }
        }

        public class ListAdapter extends RecyclerListView.SelectionAdapter {
            private Context context;
            private ArrayList<ArrayList<ThemeDescription>> items;
            final /* synthetic */ EditorAlert this$1;

            /* renamed from: -$$Nest$fgetitems, reason: not valid java name */
            public static /* bridge */ /* synthetic */ ArrayList m13298$$Nest$fgetitems(ListAdapter r0) {
                return r0.items;
            }

            public ListAdapter(EditorAlert r9, Context r10, ArrayList<ThemeDescription> r11) {
                this.this$1 = r9;
                this.items = new ArrayList();
                this.context = r10;
                HashMap r92 = new HashMap();
                int r102 = r11.size();
                int r0 = 0;
            L3:
                if (r0 >= r102) goto L9;
                ThemeDescription r1 = r11.get(r0);
                int r2 = r1.getCurrentKey();
                ArrayList<ThemeDescription> r3 = (ArrayList) r92.get(Integer.valueOf(r2));
                if (r3 != null) goto L7;
                r3 = new ArrayList();
                r92.put(Integer.valueOf(r2), r3);
                this.items.add(r3);
            L7:
                r3.add(r1);
                r0 = r0 + 1;
                goto L3
            L9:
                if (Build.VERSION.SDK_INT < 26) goto L17;
                int r7 = Theme.key_windowBackgroundGray;
                if (r92.containsKey(Integer.valueOf(r7)) == true) goto L18;
                ArrayList<ThemeDescription> r93 = new ArrayList();
                r93.add(new ThemeDescription(null, 0, null, null, null, null, r7));
                this.items.add(r93);
                return;
            L18:
                return;
            }

            public ArrayList<ThemeDescription> getItem(int r2) {
                if (r2 >= 0) goto L4;
                return null;
            L4:
                if (r2 < this.items.size()) goto L7;
                return null;
            L7:
                return this.items.get(r2);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                if (this.items.isEmpty() == false) goto L7;
                return 0;
            L7:
                return this.items.size() + 1;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemViewType(int r1) {
                if (r1 != 0) goto L5;
                return 1;
            L5:
                return 0;
            }

            @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
            public boolean isEnabled(RecyclerView.ViewHolder r1) {
                return true;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onBindViewHolder(RecyclerView.ViewHolder r3, int r4) {
                if (r3.getItemViewType() != 0) goto L10;
                ArrayList<ThemeDescription> r2 = this.items.get(r4 - 1);
                int r42 = 0;
                ThemeDescription r22 = r2.get(0);
                if (r22.getCurrentKey() == Theme.key_chat_wallpaper) goto L8;
                r42 = r22.getSetColor();
            L8:
                ((TextColorThemeCell) r3.itemView).setTextAndColor(r22.getTitle(), r42);
                return;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
                if (r3 == 0) goto L5;
                View r32 = new View(this.context);
                r32.setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(56.0f)));
            L7:
                return new RecyclerListView.Holder(r32);
            L5:
                r32 = new TextColorThemeCell(this.context);
                r32.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
                goto L7
            }
        }

        public class SearchAdapter extends RecyclerListView.SelectionAdapter {
            private Context context;
            private int lastSearchId;
            private String lastSearchText;
            private ArrayList<CharSequence> searchNames;
            private ArrayList<ArrayList<ThemeDescription>> searchResult;
            private Runnable searchRunnable;
            final /* synthetic */ EditorAlert this$1;

            public static /* synthetic */ void $r8$lambda$eU0IrvMZvDV_uDCnPQsbuSTEvvg(SearchAdapter r0, String r1, int r2) {
                r0.lambda$searchDialogs$1(r1, r2);
            }

            public static /* synthetic */ void $r8$lambda$oV60268meO8UPrP7cxRR8lNLjbA(SearchAdapter r0, int r1, ArrayList r2, ArrayList r3) {
                r0.lambda$updateSearchResults$0(r1, r2, r3);
            }

            public SearchAdapter(EditorAlert r1, Context r2) {
                this.this$1 = r1;
                this.searchResult = new ArrayList();
                this.searchNames = new ArrayList();
                this.context = r2;
            }

            private /* synthetic */ void lambda$searchDialogs$1(String r1, int r2) {
                searchDialogsInternal(r1, r2);
            }

            private /* synthetic */ void lambda$updateSearchResults$0(int r5, ArrayList r6, ArrayList r7) {
                if (r5 == this.lastSearchId) goto L6;
                return;
            L6:
                if (EditorAlert.m13277$$Nest$fgetlistView(this.this$1).getAdapter() == EditorAlert.m13279$$Nest$fgetsearchAdapter(this.this$1)) goto L8;
                EditorAlert r52 = this.this$1;
                EditorAlert.m13292$$Nest$fputtopBeforeSwitch(r52, EditorAlert.m13293$$Nest$mgetCurrentTop(r52));
                EditorAlert.m13277$$Nest$fgetlistView(this.this$1).setAdapter(EditorAlert.m13279$$Nest$fgetsearchAdapter(this.this$1));
                EditorAlert.m13279$$Nest$fgetsearchAdapter(this.this$1).notifyDataSetChanged();
            L8:
                boolean r0 = true;
                if (this.searchResult.isEmpty() == false) goto L11;
            L13:
                boolean r53 = false;
            L15:
                if (this.searchResult.isEmpty() == true) goto L17;
            L19:
                r0 = false;
            L20:
                if (r53 == false) goto L22;
                EditorAlert r2 = this.this$1;
                EditorAlert.m13292$$Nest$fputtopBeforeSwitch(r2, EditorAlert.m13293$$Nest$mgetCurrentTop(r2));
            L22:
                this.searchResult = r6;
                this.searchNames = r7;
                notifyDataSetChanged();
                if (r0 == true) goto L28;
                if (r53 == true) goto L28;
                if (EditorAlert.m13286$$Nest$fgettopBeforeSwitch(this.this$1) <= 0) goto L28;
                EditorAlert.m13275$$Nest$fgetlayoutManager(this.this$1).scrollToPositionWithOffset(0, -EditorAlert.m13286$$Nest$fgettopBeforeSwitch(this.this$1));
                EditorAlert.m13292$$Nest$fputtopBeforeSwitch(this.this$1, -1000);
            L28:
                EditorAlert.m13280$$Nest$fgetsearchEmptyView(this.this$1).showTextView();
                return;
            L17:
                if (r6.isEmpty() == false) goto L19;
            L11:
                if (r6.isEmpty() == false) goto L13;
                r53 = true;
                goto L15
            }

            private void searchDialogsInternal(String r13, int r14) {
                String r132 = r13.trim().toLowerCase();     // Catch: Exception -> L29
                if (r132.length() != 0) goto L6;
                this.lastSearchId = -1;     // Catch: Exception -> L29
                updateSearchResults(new ArrayList(), new ArrayList(), this.lastSearchId);     // Catch: Exception -> L29
                return;
            L6:
                String r0 = LocaleController.getInstance().getTranslitString(r132);     // Catch: Exception -> L29
                if (r132.equals(r0) == false) goto L9;
            L10:
                r0 = null;
            L12:
                if (r0 == null) goto L14;
                int r3 = 1;
            L15:
                int r32 = r3 + 1;     // Catch: Exception -> L29
                String[] r4 = new String[r32];     // Catch: Exception -> L29
                r4[0] = r132;     // Catch: Exception -> L29
                if (r0 == null) goto L18;
                r4[1] = r0;     // Catch: Exception -> L29
            L18:
                ArrayList<ArrayList<ThemeDescription>> r133 = new ArrayList();     // Catch: Exception -> L29
                ArrayList<CharSequence> r02 = new ArrayList();     // Catch: Exception -> L29
                int r2 = ListAdapter.m13298$$Nest$fgetitems(EditorAlert.m13276$$Nest$fgetlistAdapter(this.this$1)).size();     // Catch: Exception -> L29
                int r5 = 0;
            L19:
                if (r5 >= r2) goto L27;
                ArrayList<ThemeDescription> r6 = (ArrayList) ListAdapter.m13298$$Nest$fgetitems(EditorAlert.m13276$$Nest$fgetlistAdapter(this.this$1)).get(r5);     // Catch: Exception -> L29
                String r7 = ThemeColors.getStringName(r6.get(0).getCurrentKey());     // Catch: Exception -> L29
                String r8 = r7.toLowerCase();     // Catch: Exception -> L29
                int r9 = 0;
            L21:
                if (r9 >= r32) goto L26;
                String r10 = r4[r9];     // Catch: Exception -> L29
                if (r8.contains(r10) == true) goto L24;
                r9 = r9 + 1;     // Catch: Exception -> L29
                goto L21
            L24:
                r133.add(r6);     // Catch: Exception -> L29
                r02.add(generateSearchName(r7, r10));     // Catch: Exception -> L29
            L26:
                r5 = r5 + 1;     // Catch: Exception -> L29
                goto L19
            L27:
                updateSearchResults(r133, r02, r14);     // Catch: Exception -> L29
                return;
            L14:
                r3 = 0;
                goto L15
            L9:
                if (r0.length() != 0) goto L12;
            L29:
                e = move-exception;
                FileLog.e(e);
            }

            private void updateSearchResults(ArrayList<ArrayList<ThemeDescription>> r2, ArrayList<CharSequence> r3, int r4) {
                AndroidUtilities.runOnUIThread(new ThemeEditorView$EditorAlert$SearchAdapter$$ExternalSyntheticLambda1(this, r4, r2, r3));
            }

            public CharSequence generateSearchName(String r8, String r9) {
                if (TextUtils.isEmpty(r8) == false) goto L6;
                return _UrlKt.FRAGMENT_ENCODE_SET;
            L6:
                SpannableStringBuilder r7 = new SpannableStringBuilder();
                String r82 = r8.trim();
                String r0 = r82.toLowerCase();
                int r2 = 0;
            L7:
                int r3 = r0.indexOf(r9, r2);
                if (r3 == (-1)) goto L21;
                int r4 = r9.length() + r3;
                if (r2 != 0) goto L12;
            L14:
                if (r2 != 0) goto L17;
                if (r3 == 0) goto L17;
                r7.append(r82.substring(0, r3));
            L17:
                String r22 = r82.substring(r3, Math.min(r82.length(), r4));
                if (r22.startsWith(" ") == false) goto L20;
                r7.append(" ");
            L20:
                String r23 = r22.trim();
                int r32 = r7.length();
                r7.append(r23);
                r7.setSpan(new ForegroundColorSpan(-11697229), r32, r23.length() + r32, 33);
                r2 = r4;
                goto L7
            L12:
                if (r2 == (r3 + 1)) goto L14;
                r7.append(r82.substring(r2, r3));
                goto L17
            L21:
                if (r2 != (-1)) goto L23;
            L25:
                return r7;
            L23:
                if (r2 >= r82.length()) goto L25;
                r7.append(r82.substring(r2));
                goto L25
            }

            public ArrayList<ThemeDescription> getItem(int r2) {
                if (r2 >= 0) goto L4;
                return null;
            L4:
                if (r2 < this.searchResult.size()) goto L7;
                return null;
            L7:
                return this.searchResult.get(r2);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                if (this.searchResult.isEmpty() == false) goto L7;
                return 0;
            L7:
                return this.searchResult.size() + 1;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemViewType(int r1) {
                if (r1 != 0) goto L5;
                return 1;
            L5:
                return 0;
            }

            @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
            public boolean isEnabled(RecyclerView.ViewHolder r1) {
                return true;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onBindViewHolder(RecyclerView.ViewHolder r5, int r6) {
                if (r5.getItemViewType() != 0) goto L10;
                int r62 = r6 - 1;
                int r1 = 0;
                ThemeDescription r0 = this.searchResult.get(r62).get(0);
                if (r0.getCurrentKey() == Theme.key_chat_wallpaper) goto L8;
                r1 = r0.getSetColor();
            L8:
                ((TextColorThemeCell) r5.itemView).setTextAndColor(this.searchNames.get(r62), r1);
                return;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
                if (r3 == 0) goto L5;
                View r32 = new View(this.context);
                r32.setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(56.0f)));
            L7:
                return new RecyclerListView.Holder(r32);
            L5:
                r32 = new TextColorThemeCell(this.context);
                r32.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
                goto L7
            }

            public void searchDialogs(String r3) {
                if (r3 != null) goto L4;
            L6:
                this.lastSearchText = r3;
                if (this.searchRunnable == null) goto L9;
                Utilities.searchQueue.cancelRunnable(this.searchRunnable);
                this.searchRunnable = null;
            L9:
                if (r3 != null) goto L11;
            L15:
                this.searchResult.clear();
                EditorAlert r32 = this.this$1;
                EditorAlert.m13292$$Nest$fputtopBeforeSwitch(r32, EditorAlert.m13293$$Nest$mgetCurrentTop(r32));
                this.lastSearchId = -1;
                notifyDataSetChanged();
                return;
            L11:
                if (r3.length() == 0) goto L15;
                int r0 = this.lastSearchId + 1;
                this.lastSearchId = r0;
                this.searchRunnable = new ThemeEditorView$EditorAlert$SearchAdapter$$ExternalSyntheticLambda0(this, r3, r0);
                Utilities.searchQueue.postRunnable(this.searchRunnable, 300);
                return;
            L4:
                if (r3.equals(this.lastSearchText) == false) goto L6;
            }
        }

        public class SearchField extends FrameLayout {
            private ImageView clearSearchImageView;
            private EditTextBoldCursor searchEditText;
            final /* synthetic */ EditorAlert this$1;

            /* renamed from: org.telegram.ui.Components.ThemeEditorView$EditorAlert$SearchField$1, reason: invalid class name */
            public class AnonymousClass1 extends CloseProgressDrawable2 {
                final /* synthetic */ SearchField this$2;
                final /* synthetic */ EditorAlert val$this$1;

                public AnonymousClass1(SearchField r1, EditorAlert r2) {
                    this.this$2 = r1;
                    this.val$this$1 = r2;
                }

                @Override // org.telegram.ui.Components.CloseProgressDrawable2
                public int getCurrentColor() {
                    return -6182737;
                }
            }

            /* renamed from: org.telegram.ui.Components.ThemeEditorView$EditorAlert$SearchField$2, reason: invalid class name */
            public class AnonymousClass2 extends EditTextBoldCursor {
                final /* synthetic */ SearchField this$2;
                final /* synthetic */ EditorAlert val$this$1;

                public AnonymousClass2(SearchField r1, Context r2, EditorAlert r3) {
                    this.this$2 = r1;
                    this.val$this$1 = r3;
                    super(r2);
                }

                @Override // org.telegram.ui.Components.EditTextEffects, android.view.View
                public boolean dispatchTouchEvent(MotionEvent r5) {
                    MotionEvent r0 = MotionEvent.obtain(r5);
                    r0.setLocation(r0.getRawX(), r0.getRawY() - EditorAlert.access$000(this.this$2.this$1).getTranslationY());
                    EditorAlert.m13277$$Nest$fgetlistView(this.this$2.this$1).dispatchTouchEvent(r0);
                    r0.recycle();
                    return super.dispatchTouchEvent(r5);
                }
            }

            /* renamed from: org.telegram.ui.Components.ThemeEditorView$EditorAlert$SearchField$3, reason: invalid class name */
            public class AnonymousClass3 implements TextWatcher {
                final /* synthetic */ SearchField this$2;
                final /* synthetic */ EditorAlert val$this$1;

                public AnonymousClass3(SearchField r1, EditorAlert r2) {
                    this.this$2 = r1;
                    this.val$this$1 = r2;
                }

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable r6) {
                    boolean r0 = true;
                    if (SearchField.m13300$$Nest$fgetsearchEditText(this.this$2).length() <= 0) goto L5;
                    boolean r62 = true;
                L6:
                    float r3 = 0.0f;
                    if (SearchField.m13299$$Nest$fgetclearSearchImageView(this.this$2).getAlpha() != 0.0f) goto L10;
                    r0 = false;
                L10:
                    if (r62 == r0) goto L23;
                    ViewPropertyAnimator r02 = SearchField.m13299$$Nest$fgetclearSearchImageView(this.this$2).animate();
                    float r2 = 1.0f;
                    if (r62 == false) goto L14;
                    r3 = 1.0f;
                L14:
                    ViewPropertyAnimator r03 = r02.alpha(r3).setDuration(150);
                    if (r62 == false) goto L17;
                    float r4 = 1.0f;
                L18:
                    ViewPropertyAnimator r04 = r03.scaleX(r4);
                    if (r62 == true) goto L22;
                    r2 = 0.1f;
                L22:
                    r04.scaleY(r2).start();
                    goto L23
                L17:
                    r4 = 0.1f;
                L23:
                    String r63 = SearchField.m13300$$Nest$fgetsearchEditText(this.this$2).getText().toString();
                    int r05 = r63.length();
                    SearchField r22 = this.this$2;
                    if (r05 == 0) goto L29;
                    if (EditorAlert.m13280$$Nest$fgetsearchEmptyView(r22.this$1) == null) goto L34;
                    EditorAlert.m13280$$Nest$fgetsearchEmptyView(this.this$2.this$1).setText(LocaleController.getString(R.string.NoResult));
                L34:
                    if (EditorAlert.m13279$$Nest$fgetsearchAdapter(this.this$2.this$1) == null) goto L37;
                    EditorAlert.m13279$$Nest$fgetsearchAdapter(this.this$2.this$1).searchDialogs(r63);
                    return;
                L37:
                    return;
                L29:
                    if (EditorAlert.m13277$$Nest$fgetlistView(r22.this$1).getAdapter() == EditorAlert.m13276$$Nest$fgetlistAdapter(this.this$2.this$1)) goto L34;
                    int r06 = EditorAlert.m13293$$Nest$mgetCurrentTop(this.this$2.this$1);
                    EditorAlert.m13280$$Nest$fgetsearchEmptyView(this.this$2.this$1).setText(LocaleController.getString(R.string.NoChats));
                    EditorAlert.m13280$$Nest$fgetsearchEmptyView(this.this$2.this$1).showTextView();
                    EditorAlert.m13277$$Nest$fgetlistView(this.this$2.this$1).setAdapter(EditorAlert.m13276$$Nest$fgetlistAdapter(this.this$2.this$1));
                    EditorAlert.m13276$$Nest$fgetlistAdapter(this.this$2.this$1).notifyDataSetChanged();
                    if (r06 <= 0) goto L34;
                    EditorAlert.m13275$$Nest$fgetlayoutManager(this.this$2.this$1).scrollToPositionWithOffset(0, -r06);
                    goto L34
                L5:
                    r62 = false;
                    goto L6
                }

                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
                }
            }

            public static /* synthetic */ void $r8$lambda$4E17TM7jGu8XMoEJDGM9H3C72ck(SearchField r0, View r1) {
                r0.lambda$new$0(r1);
            }

            public static /* synthetic */ boolean $r8$lambda$jP8xc39SVSKUO9J8RSfptQQBwPI(SearchField r0, TextView r1, int r2, KeyEvent r3) {
                return r0.lambda$new$1(r1, r2, r3);
            }

            /* renamed from: -$$Nest$fgetclearSearchImageView, reason: not valid java name */
            public static /* bridge */ /* synthetic */ ImageView m13299$$Nest$fgetclearSearchImageView(SearchField r0) {
                return r0.clearSearchImageView;
            }

            /* renamed from: -$$Nest$fgetsearchEditText, reason: not valid java name */
            public static /* bridge */ /* synthetic */ EditTextBoldCursor m13300$$Nest$fgetsearchEditText(SearchField r0) {
                return r0.searchEditText;
            }

            public SearchField(EditorAlert r13, Context r14) {
                this.this$1 = r13;
                super(r14);
                View r0 = new View(r14);
                r0.setBackgroundDrawable(Theme.createRoundRectDrawable(AndroidUtilities.dp(18.0f), -854795));
                addView(r0, LayoutHelper.createFrame(-1, 36.0f, 51, 14.0f, 11.0f, 14.0f, 0.0f));
                ImageView r02 = new ImageView(r14);
                ImageView.ScaleType r1 = ImageView.ScaleType.CENTER;
                r02.setScaleType(r1);
                r02.setImageResource(R.drawable.smiles_inputsearch);
                r02.setColorFilter(new PorterDuffColorFilter(-6182737, PorterDuff.Mode.MULTIPLY));
                addView(r02, LayoutHelper.createFrame(36, 36.0f, 51, 16.0f, 11.0f, 0.0f, 0.0f));
                ImageView r03 = new ImageView(r14);
                this.clearSearchImageView = r03;
                r03.setScaleType(r1);
                ImageView r04 = this.clearSearchImageView;
                AnonymousClass1 r12 = new AnonymousClass1(this, r13);
                r04.setImageDrawable(r12);
                r12.setSide(AndroidUtilities.dp(7.0f));
                this.clearSearchImageView.setScaleX(0.1f);
                this.clearSearchImageView.setScaleY(0.1f);
                this.clearSearchImageView.setAlpha(0.0f);
                addView(this.clearSearchImageView, LayoutHelper.createFrame(36, 36.0f, 53, 14.0f, 11.0f, 14.0f, 0.0f));
                this.clearSearchImageView.setOnClickListener(new ThemeEditorView$EditorAlert$SearchField$$ExternalSyntheticLambda0(this));
                AnonymousClass2 r05 = new AnonymousClass2(this, r14, r13);
                this.searchEditText = r05;
                r05.setTextSize(1, 16.0f);
                this.searchEditText.setHintTextColor(-6774617);
                this.searchEditText.setTextColor(-14540254);
                this.searchEditText.setBackgroundDrawable(null);
                this.searchEditText.setPadding(0, 0, 0, 0);
                this.searchEditText.setMaxLines(1);
                this.searchEditText.setLines(1);
                this.searchEditText.setSingleLine(true);
                this.searchEditText.setImeOptions(268435459);
                this.searchEditText.setHint(LocaleController.getString(R.string.Search));
                this.searchEditText.setCursorColor(-11491093);
                this.searchEditText.setCursorSize(AndroidUtilities.dp(20.0f));
                this.searchEditText.setCursorWidth(1.5f);
                addView(this.searchEditText, LayoutHelper.createFrame(-1, 40.0f, 51, 54.0f, 9.0f, 46.0f, 0.0f));
                this.searchEditText.addTextChangedListener(new AnonymousClass3(this, r13));
                this.searchEditText.setOnEditorActionListener(new ThemeEditorView$EditorAlert$SearchField$$ExternalSyntheticLambda1(this));
            }

            private /* synthetic */ void lambda$new$0(View r2) {
                this.searchEditText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
                AndroidUtilities.showKeyboard(this.searchEditText);
            }

            private /* synthetic */ boolean lambda$new$1(TextView r1, int r2, KeyEvent r3) {
                if (r3 != null) goto L4;
                return false;
            L4:
                if (r3.getAction() != 1) goto L8;
                if (r3.getKeyCode() != 84) goto L8;
            L11:
                AndroidUtilities.hideKeyboard(this.searchEditText);
                return false;
            L8:
                if (r3.getAction() == 0) goto L10;
                return false;
            L10:
                if (r3.getKeyCode() == 66) goto L11;
                return false;
            }

            @Override // android.view.ViewGroup, android.view.ViewParent
            public void requestDisallowInterceptTouchEvent(boolean r1) {
                super.requestDisallowInterceptTouchEvent(r1);
            }

            public void showKeyboard() {
                this.searchEditText.requestFocus();
                AndroidUtilities.showKeyboard(this.searchEditText);
            }
        }

        public static /* synthetic */ void $r8$lambda$B1a34L3JQR8ZMzmz3sI5HVvBJ10(EditorAlert r0, View r1) {
            r0.lambda$new$5(r1);
        }

        /* renamed from: $r8$lambda$Hkj0ZSIlTXXj-SMo-TkL9GfUZjQ, reason: not valid java name */
        public static /* synthetic */ void m13268$r8$lambda$Hkj0ZSIlTXXjSMoTkL9GfUZjQ(EditorAlert r0, View r1, int r2) {
            r0.lambda$new$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$QchWs2z6Jlu1vdcj0jrC7rOAmFc(EditorAlert r0, View r1) {
            r0.lambda$new$1(r1);
        }

        /* renamed from: $r8$lambda$a-A-1YQdzvNKDPYSODnd5sPFrbg, reason: not valid java name */
        public static /* synthetic */ void m13269$r8$lambda$aA1YQdzvNKDPYSODnd5sPFrbg(EditorAlert r0, View r1) {
            r0.lambda$new$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$hW0ZrhTU8EqoIQA323zDH4lhNT4(EditorAlert r0, View r1) {
            r0.lambda$new$4(r1);
        }

        public static /* synthetic */ void $r8$lambda$iGzlCFmanW0IBKfUKrbooeNdArQ(EditorAlert r0, View r1) {
            r0.lambda$new$3(r1);
        }

        /* renamed from: -$$Nest$fgetbottomLayout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ FrameLayout m13270$$Nest$fgetbottomLayout(EditorAlert r0) {
            return r0.bottomLayout;
        }

        /* renamed from: -$$Nest$fgetbottomSaveLayout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ FrameLayout m13271$$Nest$fgetbottomSaveLayout(EditorAlert r0) {
            return r0.bottomSaveLayout;
        }

        /* renamed from: -$$Nest$fgetcolorChangeAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatorSet m13272$$Nest$fgetcolorChangeAnimation(EditorAlert r0) {
            return r0.colorChangeAnimation;
        }

        /* renamed from: -$$Nest$fgetcolorPicker, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ColorPicker m13273$$Nest$fgetcolorPicker(EditorAlert r0) {
            return r0.colorPicker;
        }

        /* renamed from: -$$Nest$fgetignoreTextChange, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m13274$$Nest$fgetignoreTextChange(EditorAlert r0) {
            return r0.ignoreTextChange;
        }

        /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
        public static /* bridge */ /* synthetic */ LinearLayoutManager m13275$$Nest$fgetlayoutManager(EditorAlert r0) {
            return r0.layoutManager;
        }

        /* renamed from: -$$Nest$fgetlistAdapter, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ListAdapter m13276$$Nest$fgetlistAdapter(EditorAlert r0) {
            return r0.listAdapter;
        }

        /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RecyclerListView m13277$$Nest$fgetlistView(EditorAlert r0) {
            return r0.listView;
        }

        /* renamed from: -$$Nest$fgetscrollOffsetY, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13278$$Nest$fgetscrollOffsetY(EditorAlert r0) {
            return r0.scrollOffsetY;
        }

        /* renamed from: -$$Nest$fgetsearchAdapter, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SearchAdapter m13279$$Nest$fgetsearchAdapter(EditorAlert r0) {
            return r0.searchAdapter;
        }

        /* renamed from: -$$Nest$fgetsearchEmptyView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ EmptyTextProgressView m13280$$Nest$fgetsearchEmptyView(EditorAlert r0) {
            return r0.searchEmptyView;
        }

        /* renamed from: -$$Nest$fgetsearchField, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SearchField m13281$$Nest$fgetsearchField(EditorAlert r0) {
            return r0.searchField;
        }

        /* renamed from: -$$Nest$fgetshadow, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View[] m13282$$Nest$fgetshadow(EditorAlert r0) {
            return r0.shadow;
        }

        /* renamed from: -$$Nest$fgetshadowAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatorSet[] m13283$$Nest$fgetshadowAnimation(EditorAlert r0) {
            return r0.shadowAnimation;
        }

        /* renamed from: -$$Nest$fgetshadowDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Drawable m13284$$Nest$fgetshadowDrawable(EditorAlert r0) {
            return r0.shadowDrawable;
        }

        /* renamed from: -$$Nest$fgetstartedColorChange, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m13285$$Nest$fgetstartedColorChange(EditorAlert r0) {
            return r0.startedColorChange;
        }

        /* renamed from: -$$Nest$fgettopBeforeSwitch, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13286$$Nest$fgettopBeforeSwitch(EditorAlert r0) {
            return r0.topBeforeSwitch;
        }

        /* renamed from: -$$Nest$fputanimationInProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13287$$Nest$fputanimationInProgress(EditorAlert r0, boolean r1) {
            r0.animationInProgress = r1;
        }

        /* renamed from: -$$Nest$fputcolorChangeAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13288$$Nest$fputcolorChangeAnimation(EditorAlert r0, AnimatorSet r1) {
            r0.colorChangeAnimation = r1;
        }

        /* renamed from: -$$Nest$fputignoreTextChange, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13289$$Nest$fputignoreTextChange(EditorAlert r0, boolean r1) {
            r0.ignoreTextChange = r1;
        }

        /* renamed from: -$$Nest$fputpreviousScrollPosition, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13290$$Nest$fputpreviousScrollPosition(EditorAlert r0, int r1) {
            r0.previousScrollPosition = r1;
        }

        /* renamed from: -$$Nest$fputstartedColorChange, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13291$$Nest$fputstartedColorChange(EditorAlert r0, boolean r1) {
            r0.startedColorChange = r1;
        }

        /* renamed from: -$$Nest$fputtopBeforeSwitch, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13292$$Nest$fputtopBeforeSwitch(EditorAlert r0, int r1) {
            r0.topBeforeSwitch = r1;
        }

        /* renamed from: -$$Nest$mgetCurrentTop, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m13293$$Nest$mgetCurrentTop(EditorAlert r0) {
            return r0.getCurrentTop();
        }

        /* renamed from: -$$Nest$msetColorPickerVisible, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13294$$Nest$msetColorPickerVisible(EditorAlert r0, boolean r1) {
            r0.setColorPickerVisible(r1);
        }

        /* renamed from: -$$Nest$mupdateLayout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m13295$$Nest$mupdateLayout(EditorAlert r0) {
            r0.updateLayout();
        }

        public EditorAlert(ThemeEditorView r17, Context r18, ArrayList<ThemeDescription> r19) {
            this.this$0 = r17;
            super(r18, true);
            this.shadow = new View[2];
            this.shadowAnimation = new AnimatorSet[2];
            this.shadowDrawable = r18.getResources().getDrawable(R.drawable.sheet_shadow_round).mutate();
            AnonymousClass1 r4 = new AnonymousClass1(this, r18, r17);
            this.containerView = r4;
            r4.setWillNotDraw(false);
            ViewGroup r42 = this.containerView;
            int r6 = this.backgroundPaddingLeft;
            r42.setPadding(r6, 0, r6, 0);
            FrameLayout r43 = new FrameLayout(r18);
            this.frameLayout = r43;
            r43.setBackgroundColor(-1);
            SearchField r44 = new SearchField(this, r18);
            this.searchField = r44;
            this.frameLayout.addView(r44, LayoutHelper.createFrame(-1, -1, 51));
            AnonymousClass2 r45 = new AnonymousClass2(this, r18, r17);
            this.listView = r45;
            r45.setSelectorDrawableColor(251658240);
            this.listView.setPadding(0, 0, 0, AndroidUtilities.dp(48.0f));
            this.listView.setClipToPadding(false);
            RecyclerListView r46 = this.listView;
            LinearLayoutManager r9 = new LinearLayoutManager(getContext());
            this.layoutManager = r9;
            r46.setLayoutManager(r9);
            this.listView.setHorizontalScrollBarEnabled(false);
            this.listView.setVerticalScrollBarEnabled(false);
            this.containerView.addView(this.listView, LayoutHelper.createFrame(-1, -1, 51));
            RecyclerListView r47 = this.listView;
            ListAdapter r92 = new ListAdapter(this, r18, r19);
            this.listAdapter = r92;
            r47.setAdapter(r92);
            this.searchAdapter = new SearchAdapter(this, r18);
            this.listView.setGlowColor(-657673);
            this.listView.setItemAnimator(null);
            this.listView.setLayoutAnimation(null);
            this.listView.setOnItemClickListener(new ThemeEditorView$EditorAlert$$ExternalSyntheticLambda0(this));
            this.listView.setOnScrollListener(new AnonymousClass3(this, r17));
            EmptyTextProgressView r1 = new EmptyTextProgressView(r18);
            this.searchEmptyView = r1;
            r1.setShowAtCenter(true);
            this.searchEmptyView.showTextView();
            this.searchEmptyView.setText(LocaleController.getString(R.string.NoResult));
            this.listView.setEmptyView(this.searchEmptyView);
            this.containerView.addView(this.searchEmptyView, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 52.0f, 0.0f, 0.0f));
            FrameLayout.LayoutParams r12 = new FrameLayout.LayoutParams(-1, AndroidUtilities.getShadowHeight(), 51);
            r12.topMargin = AndroidUtilities.dp(58.0f);
            this.shadow[0] = new View(r18);
            this.shadow[0].setBackgroundColor(301989888);
            this.shadow[0].setAlpha(0.0f);
            this.shadow[0].setTag(1);
            this.containerView.addView(this.shadow[0], r12);
            this.containerView.addView(this.frameLayout, LayoutHelper.createFrame(-1, 58, 51));
            ColorPicker r13 = new ColorPicker(this, r18);
            this.colorPicker = r13;
            r13.setVisibility(8);
            this.containerView.addView(this.colorPicker, LayoutHelper.createFrame(-1, -1, 1));
            FrameLayout.LayoutParams r14 = new FrameLayout.LayoutParams(-1, AndroidUtilities.getShadowHeight(), 83);
            r14.bottomMargin = AndroidUtilities.dp(48.0f);
            this.shadow[1] = new View(r18);
            this.shadow[1].setBackgroundColor(301989888);
            this.containerView.addView(this.shadow[1], r14);
            FrameLayout r15 = new FrameLayout(r18);
            this.bottomSaveLayout = r15;
            r15.setBackgroundColor(-1);
            this.containerView.addView(this.bottomSaveLayout, LayoutHelper.createFrame(-1, 48, 83));
            TextView r16 = new TextView(r18);
            r16.setTextSize(1, 14.0f);
            r16.setTextColor(-15095832);
            r16.setGravity(17);
            r16.setBackgroundDrawable(Theme.createSelectorDrawable(788529152, 0));
            r16.setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
            r16.setText(LocaleController.getString(R.string.CloseEditor).toUpperCase());
            r16.setTypeface(AndroidUtilities.bold());
            this.bottomSaveLayout.addView(r16, LayoutHelper.createFrame(-2, -1, 51));
            r16.setOnClickListener(new ThemeEditorView$EditorAlert$$ExternalSyntheticLambda1(this));
            TextView r110 = new TextView(r18);
            r110.setTextSize(1, 14.0f);
            r110.setTextColor(-15095832);
            r110.setGravity(17);
            r110.setBackgroundDrawable(Theme.createSelectorDrawable(788529152, 0));
            r110.setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
            r110.setText(LocaleController.getString(R.string.SaveTheme).toUpperCase());
            r110.setTypeface(AndroidUtilities.bold());
            this.bottomSaveLayout.addView(r110, LayoutHelper.createFrame(-2, -1, 53));
            r110.setOnClickListener(new ThemeEditorView$EditorAlert$$ExternalSyntheticLambda2(this));
            FrameLayout r111 = new FrameLayout(r18);
            this.bottomLayout = r111;
            r111.setVisibility(8);
            this.bottomLayout.setBackgroundColor(-1);
            this.containerView.addView(this.bottomLayout, LayoutHelper.createFrame(-1, 48, 83));
            TextView r112 = new TextView(r18);
            r112.setTextSize(1, 14.0f);
            r112.setTextColor(-15095832);
            r112.setGravity(17);
            r112.setBackgroundDrawable(Theme.createSelectorDrawable(788529152, 0));
            r112.setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
            r112.setText(LocaleController.getString(R.string.Cancel).toUpperCase());
            r112.setTypeface(AndroidUtilities.bold());
            this.bottomLayout.addView(r112, LayoutHelper.createFrame(-2, -1, 51));
            r112.setOnClickListener(new ThemeEditorView$EditorAlert$$ExternalSyntheticLambda3(this));
            LinearLayout r113 = new LinearLayout(r18);
            r113.setOrientation(0);
            this.bottomLayout.addView(r113, LayoutHelper.createFrame(-2, -1, 53));
            TextView r48 = new TextView(r18);
            r48.setTextSize(1, 14.0f);
            r48.setTextColor(-15095832);
            r48.setGravity(17);
            r48.setBackgroundDrawable(Theme.createSelectorDrawable(788529152, 0));
            r48.setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
            r48.setText(LocaleController.getString(R.string.Default).toUpperCase());
            r48.setTypeface(AndroidUtilities.bold());
            r113.addView(r48, LayoutHelper.createFrame(-2, -1, 51));
            r48.setOnClickListener(new ThemeEditorView$EditorAlert$$ExternalSyntheticLambda4(this));
            TextView r49 = new TextView(r18);
            r49.setTextSize(1, 14.0f);
            r49.setTextColor(-15095832);
            r49.setGravity(17);
            r49.setBackgroundDrawable(Theme.createSelectorDrawable(788529152, 0));
            r49.setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
            r49.setText(LocaleController.getString(R.string.Save).toUpperCase());
            r49.setTypeface(AndroidUtilities.bold());
            r113.addView(r49, LayoutHelper.createFrame(-2, -1, 51));
            r49.setOnClickListener(new ThemeEditorView$EditorAlert$$ExternalSyntheticLambda5(this));
        }

        public static /* synthetic */ ViewGroup access$000(EditorAlert r0) {
            return r0.containerView;
        }

        public static /* synthetic */ BottomSheet.SheetBackDrawable access$100(EditorAlert r0) {
            return r0.backDrawable;
        }

        public static /* synthetic */ int access$1000(EditorAlert r0) {
            return r0.backgroundPaddingTop;
        }

        public static /* synthetic */ int access$1100(EditorAlert r0) {
            return r0.backgroundPaddingTop;
        }

        public static /* synthetic */ int access$1200(EditorAlert r0) {
            return r0.backgroundPaddingTop;
        }

        public static /* synthetic */ int access$1300(EditorAlert r0) {
            return r0.backgroundPaddingTop;
        }

        public static /* synthetic */ int access$1400(EditorAlert r0) {
            return r0.backgroundPaddingLeft;
        }

        public static /* synthetic */ int access$1500(EditorAlert r0) {
            return r0.backgroundPaddingTop;
        }

        public static /* synthetic */ int access$1600(EditorAlert r0) {
            return r0.backgroundPaddingLeft;
        }

        public static /* synthetic */ int access$1700(EditorAlert r0) {
            return r0.backgroundPaddingTop;
        }

        public static /* synthetic */ int access$1800(EditorAlert r0) {
            return r0.backgroundPaddingLeft;
        }

        public static /* synthetic */ int access$1900(EditorAlert r0) {
            return r0.backgroundPaddingLeft;
        }

        public static /* synthetic */ ViewGroup access$200(EditorAlert r0) {
            return r0.containerView;
        }

        public static /* synthetic */ int access$2000(EditorAlert r0, int r1) {
            return r0.getThemedColor(r1);
        }

        public static /* synthetic */ int access$2100(EditorAlert r0, int r1) {
            return r0.getThemedColor(r1);
        }

        public static /* synthetic */ boolean access$300(EditorAlert r0) {
            return r0.isFullscreen;
        }

        public static /* synthetic */ int access$400(EditorAlert r0) {
            return r0.backgroundPaddingLeft;
        }

        public static /* synthetic */ int access$500(EditorAlert r0) {
            return r0.backgroundPaddingLeft;
        }

        public static /* synthetic */ int access$600(EditorAlert r0) {
            return r0.backgroundPaddingTop;
        }

        public static /* synthetic */ int access$700(EditorAlert r0) {
            return r0.backgroundPaddingTop;
        }

        public static /* synthetic */ int access$800(EditorAlert r0) {
            return r0.backgroundPaddingTop;
        }

        public static /* synthetic */ boolean access$900(EditorAlert r0) {
            return r0.isFullscreen;
        }

        private int getCurrentTop() {
            if (this.listView.getChildCount() == 0) goto L13;
            int r1 = 0;
            View r0 = this.listView.getChildAt(0);
            RecyclerListView.Holder r2 = (RecyclerListView.Holder) this.listView.findContainingViewHolder(r0);
            if (r2 == null) goto L15;
            int r3 = this.listView.getPaddingTop();
            if (r2.getAdapterPosition() != 0) goto L12;
            if (r0.getTop() < 0) goto L12;
            r1 = r0.getTop();
        L12:
            return r3 - r1;
        L15:
            return -1000;
        L13:
            return -1000;
        }

        private /* synthetic */ void lambda$new$0(View r4, int r5) {
            if (r5 != 0) goto L4;
            return;
        L4:
            RecyclerView.Adapter r42 = this.listView.getAdapter();
            ListAdapter r0 = this.listAdapter;
            ThemeEditorView r1 = this.this$0;
            if (r42 != r0) goto L7;
            ThemeEditorView.m13262$$Nest$fputcurrentThemeDesription(r1, r0.getItem(r5 - 1));
        L8:
            ThemeEditorView.m13263$$Nest$fputcurrentThemeDesriptionPosition(this.this$0, r5);
            int r43 = 0;
        L10:
            if (r43 >= ThemeEditorView.m13251$$Nest$fgetcurrentThemeDesription(this.this$0).size()) goto L19;
            ThemeDescription r52 = (ThemeDescription) ThemeEditorView.m13251$$Nest$fgetcurrentThemeDesription(this.this$0).get(r43);
            if (r52.getCurrentKey() == Theme.key_chat_wallpaper) goto L13;
            r52.startEditing();
            if (r43 != 0) goto L18;
            this.colorPicker.setColor(r52.getCurrentColor());
        L18:
            r43 = r43 + 1;
            goto L10
        L13:
            ThemeEditorView.m13258$$Nest$fgetwallpaperUpdater(this.this$0).showAlert(true);
            return;
        L19:
            setColorPickerVisible(true);
            return;
        L7:
            ThemeEditorView.m13262$$Nest$fputcurrentThemeDesription(r1, this.searchAdapter.getItem(r5 - 1));
            goto L8
        }

        private /* synthetic */ void lambda$new$1(View r1) {
            dismiss();
        }

        private /* synthetic */ void lambda$new$2(View r3) {
            Theme.saveCurrentTheme(ThemeEditorView.m13257$$Nest$fgetthemeInfo(this.this$0), true, false, false);
            setOnDismissListener(null);
            dismiss();
            this.this$0.close();
        }

        private /* synthetic */ void lambda$new$3(View r3) {
            int r0 = 0;
        L4:
            if (r0 >= ThemeEditorView.m13251$$Nest$fgetcurrentThemeDesription(this.this$0).size()) goto L6;
            ((ThemeDescription) ThemeEditorView.m13251$$Nest$fgetcurrentThemeDesription(this.this$0).get(r0)).setPreviousColor();
            r0 = r0 + 1;
            goto L4
        L6:
            setColorPickerVisible(false);
        }

        private /* synthetic */ void lambda$new$4(View r3) {
            int r0 = 0;
        L4:
            if (r0 >= ThemeEditorView.m13251$$Nest$fgetcurrentThemeDesription(this.this$0).size()) goto L6;
            ((ThemeDescription) ThemeEditorView.m13251$$Nest$fgetcurrentThemeDesription(this.this$0).get(r0)).setDefaultColor();
            r0 = r0 + 1;
            goto L4
        L6:
            setColorPickerVisible(false);
        }

        private /* synthetic */ void lambda$new$5(View r1) {
            setColorPickerVisible(false);
        }

        private void runShadowAnimation(int r8, boolean r9) {
            if (r9 == true) goto L4;
        L5:
            if (r9 == false) goto L7;
            return;
        L7:
            if (this.shadow[r8].getTag() != null) goto L25;
        L8:
            View r0 = this.shadow[r8];
            if (r9 == false) goto L11;
            Integer r2 = null;
        L12:
            r0.setTag(r2);
            if (r9 == false) goto L15;
            this.shadow[r8].setVisibility(0);
        L15:
            AnimatorSet r22 = this.shadowAnimation[r8];
            if (r22 == null) goto L18;
            r22.cancel();
        L18:
            this.shadowAnimation[r8] = new AnimatorSet();
            AnimatorSet r23 = this.shadowAnimation[r8];
            View r3 = this.shadow[r8];
            Property r4 = View.ALPHA;
            if (r9 == false) goto L21;
            float r5 = 1.0f;
        L22:
            r23.playTogether(new Animator[]{ObjectAnimator.ofFloat(r3, r4, new float[]{r5})});
            this.shadowAnimation[r8].setDuration(150);
            this.shadowAnimation[r8].addListener(new AnonymousClass4(this, r8, r9));
            this.shadowAnimation[r8].start();
            return;
        L21:
            r5 = 0.0f;
            goto L22
        L11:
            r2 = 1;
            goto L12
        L25:
            return;
        L4:
            if (this.shadow[r8].getTag() != null) goto L8;
            goto L5
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v0 */
        /* JADX WARN: Type inference failed for: r2v1, types: [boolean, int] */
        /* JADX WARN: Type inference failed for: r2v7 */
        private void setColorPickerVisible(boolean r25) {
            if (r25 == false) goto L7;
            this.animationInProgress = true;
            this.colorPicker.setVisibility(0);
            this.bottomLayout.setVisibility(0);
            this.colorPicker.setAlpha(0.0f);
            this.bottomLayout.setAlpha(0.0f);
            this.previousScrollPosition = this.scrollOffsetY;
            AnimatorSet r15 = new AnimatorSet();
            ColorPicker r1 = this.colorPicker;
            Property r2 = View.ALPHA;
            r15.playTogether(new Animator[]{ObjectAnimator.ofFloat(r1, r2, new float[]{1.0f}), ObjectAnimator.ofFloat(this.bottomLayout, r2, new float[]{1.0f}), ObjectAnimator.ofFloat(this.listView, r2, new float[]{0.0f}), ObjectAnimator.ofFloat(this.frameLayout, r2, new float[]{0.0f}), ObjectAnimator.ofFloat(this.shadow[0], r2, new float[]{0.0f}), ObjectAnimator.ofFloat(this.searchEmptyView, r2, new float[]{0.0f}), ObjectAnimator.ofFloat(this.bottomSaveLayout, r2, new float[]{0.0f}), ObjectAnimator.ofInt(this, "scrollOffsetY", new int[]{this.listView.getPaddingTop()})});
            r15.setDuration(150);
            r15.setInterpolator(ThemeEditorView.m13253$$Nest$fgetdecelerateInterpolator(this.this$0));
            r15.addListener(new AnonymousClass5(this));
            r15.start();
            return;
        L7:
            if (ThemeEditorView.m13256$$Nest$fgetparentActivity(this.this$0) == null) goto L9;
            ?? r22 = 0;
            ((LaunchActivity) ThemeEditorView.m13256$$Nest$fgetparentActivity(this.this$0)).rebuildAllFragments(false);
        L10:
            Theme.saveCurrentTheme(ThemeEditorView.m13257$$Nest$fgetthemeInfo(this.this$0), r22, r22, r22);
            if (this.listView.getAdapter() != this.listAdapter) goto L13;
            AndroidUtilities.hideKeyboard(getCurrentFocus());
        L13:
            this.animationInProgress = true;
            this.listView.setVisibility(r22);
            this.bottomSaveLayout.setVisibility(r22);
            this.searchField.setVisibility(r22);
            float r3 = 0.0f;
            this.listView.setAlpha(0.0f);
            AnimatorSet r12 = new AnimatorSet();
            ColorPicker r4 = this.colorPicker;
            Property r5 = View.ALPHA;
            float[] r6 = new float[1];
            r6[r22] = 0.0f;
            ObjectAnimator r42 = ObjectAnimator.ofFloat(r4, r5, r6);
            FrameLayout r62 = this.bottomLayout;
            float[] r13 = new float[1];
            r13[r22] = 0.0f;
            ObjectAnimator r63 = ObjectAnimator.ofFloat(r62, r5, r13);
            RecyclerListView r132 = this.listView;
            float[] r14 = new float[1];
            r14[r22] = 1.0f;
            ObjectAnimator r133 = ObjectAnimator.ofFloat(r132, r5, r14);
            FrameLayout r142 = this.frameLayout;
            float[] r152 = new float[1];
            r152[r22] = 1.0f;
            ObjectAnimator r143 = ObjectAnimator.ofFloat(r142, r5, r152);
            View r153 = this.shadow[r22];
            if (r153.getTag() == null) goto L16;
        L15:
            char r23 = r22;
            float[] r24 = new float[1];
            r24[r23] = r3;
            ObjectAnimator r26 = ObjectAnimator.ofFloat(r153, r5, r24);
            EmptyTextProgressView r32 = this.searchEmptyView;
            float[] r154 = new float[1];
            r154[r23] = 1.0f;
            ObjectAnimator r33 = ObjectAnimator.ofFloat(r32, r5, r154);
            FrameLayout r155 = this.bottomSaveLayout;
            float[] r122 = new float[1];
            r122[r23] = 1.0f;
            ObjectAnimator r52 = ObjectAnimator.ofFloat(r155, r5, r122);
            ObjectAnimator r10 = ObjectAnimator.ofInt(this, "scrollOffsetY", new int[]{this.previousScrollPosition});
            Animator[] r7 = new Animator[8];
            r7[r23] = r42;
            r7[1] = r63;
            r7[2] = r133;
            r7[3] = r143;
            r7[4] = r26;
            r7[5] = r33;
            r7[6] = r52;
            r7[7] = r10;
            r12.playTogether(r7);
            r12.setDuration(150);
            r12.setInterpolator(ThemeEditorView.m13253$$Nest$fgetdecelerateInterpolator(this.this$0));
            r12.addListener(new AnonymousClass6(this));
            r12.start();
            this.listView.getAdapter().notifyItemChanged(ThemeEditorView.m13252$$Nest$fgetcurrentThemeDesriptionPosition(this.this$0));
            return;
        L16:
            r3 = 1.0f;
            goto L15
        L9:
            r22 = 0;
            goto L10
        }

        @SuppressLint({"NewApi"})
        private void updateLayout() {
            if (this.listView.getChildCount() > 0) goto L5;
            return;
        L5:
            if (this.listView.getVisibility() == 0) goto L7;
            return;
        L7:
            if (this.animationInProgress == true) goto L30;
            int r1 = 0;
            View r0 = this.listView.getChildAt(0);
            RecyclerListView.Holder r2 = (RecyclerListView.Holder) this.listView.findContainingViewHolder(r0);
            if (this.listView.getVisibility() == 0) goto L12;
        L15:
            int r02 = this.listView.getPaddingTop();
        L17:
            if (r02 <= (-AndroidUtilities.dp(1.0f))) goto L22;
            if (r2 == null) goto L22;
            if (r2.getAdapterPosition() != 0) goto L22;
            runShadowAnimation(0, false);
            r1 = r02;
        L24:
            if (this.scrollOffsetY == r1) goto L29;
            setScrollOffsetY(r1);
            return;
        L29:
            return;
        L22:
            runShadowAnimation(0, true);
            goto L24
        L12:
            if (this.animationInProgress == true) goto L15;
            r02 = r0.getTop() - AndroidUtilities.dp(8.0f);
            goto L17
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet
        public boolean canDismissWithSwipe() {
            return false;
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet
        public void dismissInternal() {
            super.dismissInternal();
            if (SearchField.m13300$$Nest$fgetsearchEditText(this.searchField).isFocused() == false) goto L6;
            AndroidUtilities.hideKeyboard(SearchField.m13300$$Nest$fgetsearchEditText(this.searchField));
            return;
        }

        @Keep
        public int getScrollOffsetY() {
            return this.scrollOffsetY;
        }

        @Keep
        public void setScrollOffsetY(int r2) {
            RecyclerListView r0 = this.listView;
            this.scrollOffsetY = r2;
            r0.setTopGlowOffset(r2);
            this.frameLayout.setTranslationY(this.scrollOffsetY);
            this.colorPicker.setTranslationY(this.scrollOffsetY);
            this.searchEmptyView.setTranslationY(this.scrollOffsetY);
            this.containerView.invalidate();
        }
    }

    /* renamed from: -$$Nest$fgetcurrentThemeDesription, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m13251$$Nest$fgetcurrentThemeDesription(ThemeEditorView r0) {
        return r0.currentThemeDesription;
    }

    /* renamed from: -$$Nest$fgetcurrentThemeDesriptionPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13252$$Nest$fgetcurrentThemeDesriptionPosition(ThemeEditorView r0) {
        return r0.currentThemeDesriptionPosition;
    }

    /* renamed from: -$$Nest$fgetdecelerateInterpolator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DecelerateInterpolator m13253$$Nest$fgetdecelerateInterpolator(ThemeEditorView r0) {
        return r0.decelerateInterpolator;
    }

    /* renamed from: -$$Nest$fgeteditorAlert, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditorAlert m13254$$Nest$fgeteditorAlert(ThemeEditorView r0) {
        return r0.editorAlert;
    }

    /* renamed from: -$$Nest$fgeteditorWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13255$$Nest$fgeteditorWidth(ThemeEditorView r0) {
        return r0.editorWidth;
    }

    /* renamed from: -$$Nest$fgetparentActivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Activity m13256$$Nest$fgetparentActivity(ThemeEditorView r0) {
        return r0.parentActivity;
    }

    /* renamed from: -$$Nest$fgetthemeInfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ThemeInfo m13257$$Nest$fgetthemeInfo(ThemeEditorView r0) {
        return r0.themeInfo;
    }

    /* renamed from: -$$Nest$fgetwallpaperUpdater, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WallpaperUpdater m13258$$Nest$fgetwallpaperUpdater(ThemeEditorView r0) {
        return r0.wallpaperUpdater;
    }

    /* renamed from: -$$Nest$fgetwindowLayoutParams, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WindowManager.LayoutParams m13259$$Nest$fgetwindowLayoutParams(ThemeEditorView r0) {
        return r0.windowLayoutParams;
    }

    /* renamed from: -$$Nest$fgetwindowManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WindowManager m13260$$Nest$fgetwindowManager(ThemeEditorView r0) {
        return r0.windowManager;
    }

    /* renamed from: -$$Nest$fgetwindowView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m13261$$Nest$fgetwindowView(ThemeEditorView r0) {
        return r0.windowView;
    }

    /* renamed from: -$$Nest$fputcurrentThemeDesription, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13262$$Nest$fputcurrentThemeDesription(ThemeEditorView r0, ArrayList r1) {
        r0.currentThemeDesription = r1;
    }

    /* renamed from: -$$Nest$fputcurrentThemeDesriptionPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13263$$Nest$fputcurrentThemeDesriptionPosition(ThemeEditorView r0, int r1) {
        r0.currentThemeDesriptionPosition = r1;
    }

    /* renamed from: -$$Nest$fputeditorAlert, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13264$$Nest$fputeditorAlert(ThemeEditorView r0, EditorAlert r1) {
        r0.editorAlert = r1;
    }

    /* renamed from: -$$Nest$manimateToBoundsMaybe, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13265$$Nest$manimateToBoundsMaybe(ThemeEditorView r0) {
        r0.animateToBoundsMaybe();
    }

    /* renamed from: -$$Nest$mhide, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13266$$Nest$mhide(ThemeEditorView r0) {
        r0.hide();
    }

    /* renamed from: -$$Nest$mshow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13267$$Nest$mshow(ThemeEditorView r0) {
        r0.show();
    }

    static {
    }

    public ThemeEditorView() {
        this.editorWidth = AndroidUtilities.dp(54.0f);
        this.editorHeight = AndroidUtilities.dp(54.0f);
    }

    private void animateToBoundsMaybe() {
        int r1 = getSideCoord(true, 0, 0.0f, this.editorWidth);
        int r5 = getSideCoord(true, 1, 0.0f, this.editorWidth);
        int r6 = getSideCoord(false, 0, 0.0f, this.editorHeight);
        int r7 = getSideCoord(false, 1, 0.0f, this.editorHeight);
        SharedPreferences.Editor r8 = this.preferences.edit();
        int r9 = AndroidUtilities.dp(20.0f);
        if (Math.abs(r1 - this.windowLayoutParams.x) <= r9) goto L8;
        int r10 = this.windowLayoutParams.x;
        if (r10 >= 0) goto L10;
        if (r10 > ((-this.editorWidth) / 4)) goto L8;
    L10:
        if (Math.abs(r5 - r10) <= r9) goto L25;
        int r102 = this.windowLayoutParams.x;
        int r15 = AndroidUtilities.displaySize.x;
        float r16 = 0.0f;
        int r4 = this.editorWidth;
        float r17 = 1.0f;
        if (r102 <= (r15 - r4)) goto L17;
        if (r102 >= (r15 - ((r4 / 4) * 3))) goto L17;
    L26:
        ArrayList r12 = new ArrayList();
        r8.putInt("sidex", 1);
        if (this.windowView.getAlpha() == r17) goto L29;
        r12.add(ObjectAnimator.ofFloat(this.windowView, View.ALPHA, new float[]{r17}));
    L29:
        r12.add(ObjectAnimator.ofInt(this, "x", new int[]{r5}));
    L24:
        boolean r42 = false;
    L34:
        if (r42 == false) goto L36;
    L50:
        if (r12 != null) goto L52;
        return;
    L52:
        if (this.decelerateInterpolator != null) goto L54;
        this.decelerateInterpolator = new DecelerateInterpolator();
    L54:
        AnimatorSet r52 = new AnimatorSet();
        r52.setInterpolator(this.decelerateInterpolator);
        r52.setDuration(150);
        if (r42 == false) goto L57;
        r12.add(ObjectAnimator.ofFloat(this.windowView, View.ALPHA, new float[]{r16}));
        r52.addListener(new AnonymousClass4(this));
    L57:
        r52.playTogether(r12);
        r52.start();
        return;
    L36:
        if (Math.abs(r6 - this.windowLayoutParams.y) > r9) goto L38;
    L46:
        if (r12 != null) goto L48;
        r12 = new ArrayList();
    L48:
        r8.putInt("sidey", 0);
        r12.add(ObjectAnimator.ofInt(this, "y", new int[]{r6}));
    L49:
        r8.apply();
        goto L50
    L38:
        if (this.windowLayoutParams.y <= ActionBar.getCurrentActionBarHeight()) goto L46;
        if (Math.abs(r7 - this.windowLayoutParams.y) > r9) goto L45;
        if (r12 != null) goto L44;
        r12 = new ArrayList();
    L44:
        r8.putInt("sidey", 1);
        r12.add(ObjectAnimator.ofInt(this, "y", new int[]{r7}));
        goto L49
    L45:
        r8.putFloat("py", (this.windowLayoutParams.y - r6) / (r7 - r6));
        r8.putInt("sidey", 2);
    L17:
        if (this.windowView.getAlpha() == 1.0f) goto L23;
        r12 = new ArrayList();
        if (this.windowLayoutParams.x >= 0) goto L21;
        r12.add(ObjectAnimator.ofInt(this, "x", new int[]{-this.editorWidth}));
    L22:
        r42 = true;
        goto L34
    L21:
        r12.add(ObjectAnimator.ofInt(this, "x", new int[]{AndroidUtilities.displaySize.x}));
        goto L22
    L23:
        r8.putFloat("px", (this.windowLayoutParams.x - r1) / (r5 - r1));
        r8.putInt("sidex", 2);
        r12 = null;
        goto L24
    L25:
        r16 = 0.0f;
        r17 = 1.0f;
    L8:
        r16 = 0.0f;
        ArrayList r43 = new ArrayList();
        r8.putInt("sidex", 0);
        if (this.windowView.getAlpha() == 1.0f) goto L33;
        r43.add(ObjectAnimator.ofFloat(this.windowView, View.ALPHA, new float[]{1.0f}));
    L33:
        r43.add(ObjectAnimator.ofInt(this, "x", new int[]{r1}));
        r12 = r43;
        goto L24
    }

    public static ThemeEditorView getInstance() {
        return Instance;
    }

    private static int getSideCoord(boolean r2, int r3, float r4, int r5) {
        if (r2 == false) goto L5;
        int r0 = AndroidUtilities.displaySize.x;
    L4:
        int r02 = r0 - r5;
        if (r3 != 0) goto L10;
        int r32 = AndroidUtilities.dp(10.0f);
    L13:
        if (r2 == false) goto L15;
        return r32;
    L15:
        return r32 + ActionBar.getCurrentActionBarHeight();
    L10:
        if (r3 != 1) goto L12;
        r32 = r02 - AndroidUtilities.dp(10.0f);
        goto L13
    L12:
        r32 = Math.round((r02 - AndroidUtilities.dp(20.0f)) * r4) + AndroidUtilities.dp(10.0f);
        goto L13
    L5:
        r0 = AndroidUtilities.displaySize.y - r5;
        r5 = ActionBar.getCurrentActionBarHeight();
        goto L4
    }

    private void hide() {
        if (this.parentActivity == null) goto L10;
        AnimatorSet r0 = new AnimatorSet();     // Catch: Exception -> L7
        r0.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.windowView, View.ALPHA, new float[]{1.0f, 0.0f}), ObjectAnimator.ofFloat(this.windowView, View.SCALE_X, new float[]{1.0f, 0.0f}), ObjectAnimator.ofFloat(this.windowView, View.SCALE_Y, new float[]{1.0f, 0.0f})});     // Catch: Exception -> L7
        r0.setInterpolator(this.decelerateInterpolator);     // Catch: Exception -> L7
        r0.setDuration(150);     // Catch: Exception -> L7
        r0.addListener(new AnonymousClass3(this));     // Catch: Exception -> L7
        r0.start();     // Catch: Exception -> L7
        this.hidden = true;     // Catch: Exception -> L7
        return;
    L11:
        return;
    }

    private void show() {
        if (this.parentActivity == null) goto L11;
        AndroidUtilities.setPreferredMaxRefreshRate(this.windowManager, this.windowView, this.windowLayoutParams);
        this.windowManager.addView(this.windowView, this.windowLayoutParams);     // Catch: Exception -> L8
        this.hidden = false;     // Catch: Exception -> L8
        showWithAnimation();     // Catch: Exception -> L8
        return;
    L12:
        return;
    }

    private void showWithAnimation() {
        this.windowView.setBackgroundResource(R.drawable.theme_picker);
        AnimatorSet r0 = new AnimatorSet();
        r0.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.windowView, View.ALPHA, new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(this.windowView, View.SCALE_X, new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(this.windowView, View.SCALE_Y, new float[]{0.0f, 1.0f})});
        r0.setInterpolator(this.decelerateInterpolator);
        r0.setDuration(150);
        r0.start();
    }

    public void close() {
        this.windowManager.removeView(this.windowView);     // Catch: Exception -> L5
    L3:
        this.parentActivity = null;
    }

    public void destroy() {
        this.wallpaperUpdater.cleanup();
        if (this.parentActivity == null) goto L24;
        FrameLayout r0 = this.windowView;
        if (r0 != null) goto L20;
        return;
    L20:
        this.windowManager.removeViewImmediate(r0);     // Catch: Exception -> L10
        this.windowView = null;     // Catch: Exception -> L10
    L22:
        EditorAlert r02 = this.editorAlert;     // Catch: Exception -> L16
        if (r02 == null) goto L18;
        r02.dismiss();     // Catch: Exception -> L16
        this.editorAlert = null;     // Catch: Exception -> L16
    L18:
        this.parentActivity = null;
        Instance = null;
        return;
    L16:
        e = move-exception;
        FileLog.e(e);
    L10:
        e = move-exception;
        FileLog.e(e);
        goto L22
    }

    @Keep
    public int getX() {
        return this.windowLayoutParams.x;
    }

    @Keep
    public int getY() {
        return this.windowLayoutParams.y;
    }

    public void onActivityResult(int r1, int r2, Intent r3) {
        WallpaperUpdater r0 = this.wallpaperUpdater;
        if (r0 == null) goto L6;
        r0.onActivityResult(r1, r2, r3);
        return;
    }

    public void onConfigurationChanged() {
        int r0 = this.preferences.getInt("sidex", 1);
        int r1 = this.preferences.getInt("sidey", 0);
        float r3 = this.preferences.getFloat("px", 0.0f);
        float r5 = this.preferences.getFloat("py", 0.0f);
        WindowManager.LayoutParams r6 = this.windowLayoutParams;
        r6.x = getSideCoord(true, r0, r3, this.editorWidth);
        WindowManager.LayoutParams r02 = this.windowLayoutParams;
        r02.y = getSideCoord(false, r1, r5, this.editorHeight);
    L7:
        e = move-exception;
        FileLog.e(e);
        return;
    L4:
        if (this.windowView.getParent() == null) goto L12;
        this.windowManager.updateViewLayout(this.windowView, this.windowLayoutParams);     // Catch: Exception -> L7
        return;
    }

    @Keep
    public void setX(int r2) {
        WindowManager.LayoutParams r0 = this.windowLayoutParams;
        r0.x = r2;
        this.windowManager.updateViewLayout(this.windowView, r0);
    }

    @Keep
    public void setY(int r2) {
        WindowManager.LayoutParams r0 = this.windowLayoutParams;
        r0.y = r2;
        this.windowManager.updateViewLayout(this.windowView, r0);
    }

    public void show(Activity r9, Theme.ThemeInfo r10) {
        if (Instance == null) goto L5;
        Instance.destroy();
    L5:
        this.hidden = false;
        this.themeInfo = r10;
        this.windowView = new AnonymousClass1(this, r9);
        this.windowManager = (WindowManager) r9.getSystemService("window");
        SharedPreferences r102 = ApplicationLoader.applicationContext.getSharedPreferences("themeconfig", 0);
        this.preferences = r102;
        int r103 = r102.getInt("sidex", 1);
        int r1 = this.preferences.getInt("sidey", 0);
        float r3 = this.preferences.getFloat("px", 0.0f);
        float r4 = this.preferences.getFloat("py", 0.0f);
        WindowManager.LayoutParams r5 = new WindowManager.LayoutParams();     // Catch: Exception -> L9
        this.windowLayoutParams = r5;     // Catch: Exception -> L9
        int r6 = this.editorWidth;     // Catch: Exception -> L9
        r5.width = r6;     // Catch: Exception -> L9
        r5.height = this.editorHeight;     // Catch: Exception -> L9
        r5.x = getSideCoord(true, r103, r3, r6);     // Catch: Exception -> L9
        WindowManager.LayoutParams r104 = this.windowLayoutParams;     // Catch: Exception -> L9
        r104.y = getSideCoord(false, r1, r4, this.editorHeight);     // Catch: Exception -> L9
        WindowManager.LayoutParams r105 = this.windowLayoutParams;     // Catch: Exception -> L9
        r105.format = -3;     // Catch: Exception -> L9
        r105.gravity = 51;     // Catch: Exception -> L9
        r105.type = 99;     // Catch: Exception -> L9
        r105.flags = 16777736;     // Catch: Exception -> L9
        AndroidUtilities.setPreferredMaxRefreshRate(this.windowManager, this.windowView, r105);     // Catch: Exception -> L9
        this.windowManager.addView(this.windowView, this.windowLayoutParams);     // Catch: Exception -> L9
        this.wallpaperUpdater = new WallpaperUpdater(r9, null, new AnonymousClass2(this));
        Instance = this;
        this.parentActivity = r9;
        showWithAnimation();
        return;
    L9:
        e = move-exception;
        FileLog.e(e);
    }
}
