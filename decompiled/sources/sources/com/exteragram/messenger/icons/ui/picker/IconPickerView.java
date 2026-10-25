package com.exteragram.messenger.icons.ui.picker;

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
import android.graphics.drawable.GradientDrawable;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.icons.IconManager;
import com.exteragram.messenger.icons.IconPack;
import com.exteragram.messenger.icons.ui.IconPacksEditorActivity;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.utils.ViewOutlineProviderImpl;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes4.dex */
public class IconPickerView extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    private final ActionBar actionBar;
    private final ImageView actionIcon;
    private final LinearLayout bigLayout;
    private SpringAnimation fabXSpring;
    private SpringAnimation fabYSpring;
    private Drawable floatingButtonBackground;
    private final FrameLayout floatingButtonContainer;
    private boolean inLongPress;
    private boolean isBigMenuShown;
    private boolean isFromFling;
    private boolean isScrollDisallowed;
    private boolean isScrolling;
    private final UniversalRecyclerView listView;
    private final SharedPreferences mPrefs;
    private final Runnable onLongPress;
    private final ActionBarMenuItem otherButton;
    private String query;
    private final ActionBarMenuSubItem saveItem;
    private boolean searching;
    private int systemBottomInset;
    private int systemTopInset;
    private final int touchSlop;
    private int wasStatusBar;

    /* renamed from: com.exteragram.messenger.icons.ui.picker.IconPickerView$1, reason: invalid class name */
    public class AnonymousClass1 extends GestureDetector.SimpleOnGestureListener {
        private float startX;
        private float startY;
        final /* synthetic */ IconPickerView this$0;

        public AnonymousClass1(IconPickerView r1) {
            this.this$0 = r1;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent r1) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent r3, MotionEvent r4, float r5, float r6) {
            if (IconPickerView.m1476$$Nest$fgetisScrolling(this.this$0) == true) goto L5;
            return false;
        L5:
            if (IconPickerView.m1472$$Nest$fgetinLongPress(this.this$0) == true) goto L15;
            SpringForce r32 = IconPickerView.m1470$$Nest$fgetfabXSpring(this.this$0).getSpring();
            float r42 = IconPickerView.m1470$$Nest$fgetfabXSpring(this.this$0).getSpring().getFinalPosition() + (r5 / 7.0f);
            float r52 = this.this$0.getWidth() / 2.0f;
            IconPickerView r53 = this.this$0;
            if (r42 < r52) goto L10;
            DisplayMetrics r43 = r53.getResources().getDisplayMetrics();
            float r0 = 2.1474836E9f;
        L11:
            r32.setFinalPosition(IconPickerView.m1487$$Nest$mclampX(r53, r43, r0));
            SpringForce r33 = IconPickerView.m1471$$Nest$fgetfabYSpring(this.this$0).getSpring();
            IconPickerView r44 = this.this$0;
            r33.setFinalPosition(IconPickerView.m1488$$Nest$mclampY(r44, r44.getResources().getDisplayMetrics(), IconPickerView.m1471$$Nest$fgetfabYSpring(this.this$0).getSpring().getFinalPosition() + (r6 / 10.0f)));
            IconPickerView.m1470$$Nest$fgetfabXSpring(this.this$0).start();
            IconPickerView.m1471$$Nest$fgetfabYSpring(this.this$0).start();
            IconPickerView.m1482$$Nest$fputisFromFling(this.this$0, true);
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
            if (IconPickerView.m1472$$Nest$fgetinLongPress(this.this$0) == true) goto L6;
            AndroidUtilities.cancelRunOnUIThread(IconPickerView.m1478$$Nest$fgetonLongPress(this.this$0));
        L6:
            if (IconPickerView.m1476$$Nest$fgetisScrolling(this.this$0) == true) goto L17;
            if (IconPickerView.m1475$$Nest$fgetisScrollDisallowed(this.this$0) == true) goto L17;
            if (Math.abs(r4) < IconPickerView.m1480$$Nest$fgettouchSlop(this.this$0)) goto L12;
        L15:
            this.startX = IconPickerView.m1470$$Nest$fgetfabXSpring(this.this$0).getSpring().getFinalPosition();
            this.startY = IconPickerView.m1471$$Nest$fgetfabYSpring(this.this$0).getSpring().getFinalPosition();
            IconPickerView.m1484$$Nest$fputisScrolling(this.this$0, true);
            goto L17
        L12:
            if (Math.abs(r5) >= IconPickerView.m1480$$Nest$fgettouchSlop(this.this$0)) goto L15;
            IconPickerView.m1483$$Nest$fputisScrollDisallowed(this.this$0, false);
        L17:
            if (IconPickerView.m1476$$Nest$fgetisScrolling(this.this$0) == false) goto L22;
            if (IconPickerView.m1472$$Nest$fgetinLongPress(this.this$0) == true) goto L22;
            IconPickerView.m1470$$Nest$fgetfabXSpring(this.this$0).getSpring().setFinalPosition((this.startX + r3.getRawX()) - r2.getRawX());
            IconPickerView.m1471$$Nest$fgetfabYSpring(this.this$0).getSpring().setFinalPosition((this.startY + r3.getRawY()) - r2.getRawY());
            IconPickerView.m1470$$Nest$fgetfabXSpring(this.this$0).start();
            IconPickerView.m1471$$Nest$fgetfabYSpring(this.this$0).start();
        L22:
            return IconPickerView.m1476$$Nest$fgetisScrolling(this.this$0);
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent r2) {
            if (IconPickerView.m1472$$Nest$fgetinLongPress(this.this$0) == false) goto L6;
            return false;
        L6:
            if (IconPickerView.m1473$$Nest$fgetisBigMenuShown(this.this$0) == true) goto L9;
            this.this$0.showIconList(true);
            return true;
        L9:
            return false;
        }
    }

    /* renamed from: com.exteragram.messenger.icons.ui.picker.IconPickerView$2, reason: invalid class name */
    public class AnonymousClass2 extends FrameLayout {
        final /* synthetic */ IconPickerView this$0;
        final /* synthetic */ GestureDetector val$gestureDetector;

        public AnonymousClass2(IconPickerView r1, Context r2, GestureDetector r3) {
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
        public boolean onTouchEvent(MotionEvent r4) {
            boolean r0 = this.val$gestureDetector.onTouchEvent(r4);
            if (r4.getAction() != 0) goto L7;
            AndroidUtilities.runOnUIThread(IconPickerView.m1478$$Nest$fgetonLongPress(this.this$0), 200);
            return r0;
        L7:
            if (r4.getAction() != 1) goto L9;
        L12:
            AndroidUtilities.cancelRunOnUIThread(IconPickerView.m1478$$Nest$fgetonLongPress(this.this$0));
            if (IconPickerView.m1474$$Nest$fgetisFromFling(this.this$0) == true) goto L15;
            IconPickerView.m1489$$Nest$mupdateSpringPositions(this.this$0);
        L15:
            IconPickerView.m1481$$Nest$fputinLongPress(this.this$0, false);
            IconPickerView.m1484$$Nest$fputisScrolling(this.this$0, false);
            IconPickerView.m1483$$Nest$fputisScrollDisallowed(this.this$0, false);
            IconPickerView.m1482$$Nest$fputisFromFling(this.this$0, false);
            return r0;
        L9:
            if (r4.getAction() == 3) goto L12;
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

    /* renamed from: com.exteragram.messenger.icons.ui.picker.IconPickerView$3, reason: invalid class name */
    public class AnonymousClass3 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        final /* synthetic */ IconPickerView this$0;

        public AnonymousClass3(IconPickerView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchCollapse() {
            IconPickerView.m1486$$Nest$fputsearching(this.this$0, false);
            IconPickerView.m1485$$Nest$fputquery(this.this$0, null);
            if (IconPickerView.m1479$$Nest$fgetotherButton(this.this$0) == null) goto L5;
            IconPickerView.m1479$$Nest$fgetotherButton(this.this$0).setVisibility(0);
        L5:
            IconPickerView.m1477$$Nest$fgetlistView(this.this$0).adapter.update(true);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
            IconPickerView.m1486$$Nest$fputsearching(this.this$0, true);
            if (IconPickerView.m1479$$Nest$fgetotherButton(this.this$0) == null) goto L6;
            IconPickerView.m1479$$Nest$fgetotherButton(this.this$0).setVisibility(8);
            return;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r2) {
            IconPickerView.m1485$$Nest$fputquery(this.this$0, r2.getText().toString());
            IconPickerView.m1477$$Nest$fgetlistView(this.this$0).adapter.update(true);
        }
    }

    /* renamed from: com.exteragram.messenger.icons.ui.picker.IconPickerView$4, reason: invalid class name */
    public class AnonymousClass4 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ IconPickerView this$0;
        final /* synthetic */ Context val$context;

        public AnonymousClass4(IconPickerView r1, Context r2) {
            this.this$0 = r1;
            this.val$context = r2;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r4) {
            if (r4 != (-1)) goto L7;
            this.this$0.showIconList(false);
            return;
        L7:
            if (r4 != 3) goto L13;
            ExteraConfig.setEditingIconPackId(null);
            Context r3 = this.val$context;
            if ((r3 instanceof LaunchActivity) == false) goto L22;
            IconPickerController.setActive((LaunchActivity) r3, false);
            return;
        L22:
            return;
        L13:
            if (r4 == 2) goto L15;
            return;
        L15:
            if (ExteraConfig.getEditingIconPackId() == null) goto L24;
            IconPack r42 = IconManager.INSTANCE.findPackById(ExteraConfig.getEditingIconPackId());
            if (r42 == null) goto L25;
            Context r0 = this.val$context;
            if ((r0 instanceof LaunchActivity) == false) goto L26;
            ((LaunchActivity) r0).presentFragment(new IconPacksEditorActivity(r42));
            this.this$0.showIconList(false);
            return;
        L26:
            return;
        L25:
            return;
        }
    }

    public static /* synthetic */ String $r8$lambda$9KNXcO5xG1SFpJr8gzsy1mFT0U0(UItem r0) {
        return r0.text.toString();
    }

    public static /* synthetic */ void $r8$lambda$A_Yxv5lzSL6CccxpuyKh8kWRVBQ(IconPickerView r0, DynamicAnimation r1, float r2, float r3) {
        r0.lambda$showFab$6(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$B5pkN9s-JrzkxFOMcwJGG6qMjdk, reason: not valid java name */
    public static /* synthetic */ void m1466$r8$lambda$B5pkN9sJrzkxFOMcwJGG6qMjdk(IconPickerView r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ UItem $r8$lambda$cQZoWGUdKqEVRnI_c34JnKiZ7fM(IconPickerView r0, String r1, IconPack r2, Integer r3) {
        return r0.lambda$fillItems$2(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$hkLwRvPJsye1Aotoav5y_LcC-XM, reason: not valid java name */
    public static /* synthetic */ void m1467$r8$lambda$hkLwRvPJsye1Aotoav5y_LcCXM(IconPickerView r0, float r1, float r2, float r3, float r4, Window r5, DynamicAnimation r6, float r7, float r8) {
        r0.lambda$showIconList$4(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$lgZj6EqC_dxJQ98FWlfNf2W7ZHI(IconPickerView r0) {
        r0.lambda$new$0();
    }

    /* renamed from: $r8$lambda$nzlC5FD_mODwW-XeFY7Q61Lc9AA, reason: not valid java name */
    public static /* synthetic */ void m1468$r8$lambda$nzlC5FD_mODwWXeFY7Q61Lc9AA(IconPickerView r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onClick(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ WindowInsets $r8$lambda$vRKuUaxTdELbb353FD1H4UtEt6k(IconPickerView r0, View r1, WindowInsets r2) {
        return r0.lambda$new$1(r1, r2);
    }

    /* renamed from: $r8$lambda$wl-Czihd1EZZLOu6bvVXE3yJwqA, reason: not valid java name */
    public static /* synthetic */ void m1469$r8$lambda$wlCzihd1EZZLOu6bvVXE3yJwqA(IconPickerView r0, float r1, float r2, float r3, float r4, boolean r5, DynamicAnimation r6, boolean r7, float r8, float r9) {
        r0.lambda$showIconList$5(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    /* renamed from: -$$Nest$fgetfabXSpring, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SpringAnimation m1470$$Nest$fgetfabXSpring(IconPickerView r0) {
        return r0.fabXSpring;
    }

    /* renamed from: -$$Nest$fgetfabYSpring, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SpringAnimation m1471$$Nest$fgetfabYSpring(IconPickerView r0) {
        return r0.fabYSpring;
    }

    /* renamed from: -$$Nest$fgetinLongPress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1472$$Nest$fgetinLongPress(IconPickerView r0) {
        return r0.inLongPress;
    }

    /* renamed from: -$$Nest$fgetisBigMenuShown, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1473$$Nest$fgetisBigMenuShown(IconPickerView r0) {
        return r0.isBigMenuShown;
    }

    /* renamed from: -$$Nest$fgetisFromFling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1474$$Nest$fgetisFromFling(IconPickerView r0) {
        return r0.isFromFling;
    }

    /* renamed from: -$$Nest$fgetisScrollDisallowed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1475$$Nest$fgetisScrollDisallowed(IconPickerView r0) {
        return r0.isScrollDisallowed;
    }

    /* renamed from: -$$Nest$fgetisScrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m1476$$Nest$fgetisScrolling(IconPickerView r0) {
        return r0.isScrolling;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UniversalRecyclerView m1477$$Nest$fgetlistView(IconPickerView r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetonLongPress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m1478$$Nest$fgetonLongPress(IconPickerView r0) {
        return r0.onLongPress;
    }

    /* renamed from: -$$Nest$fgetotherButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m1479$$Nest$fgetotherButton(IconPickerView r0) {
        return r0.otherButton;
    }

    /* renamed from: -$$Nest$fgettouchSlop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m1480$$Nest$fgettouchSlop(IconPickerView r0) {
        return r0.touchSlop;
    }

    /* renamed from: -$$Nest$fputinLongPress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1481$$Nest$fputinLongPress(IconPickerView r0, boolean r1) {
        r0.inLongPress = r1;
    }

    /* renamed from: -$$Nest$fputisFromFling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1482$$Nest$fputisFromFling(IconPickerView r0, boolean r1) {
        r0.isFromFling = r1;
    }

    /* renamed from: -$$Nest$fputisScrollDisallowed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1483$$Nest$fputisScrollDisallowed(IconPickerView r0, boolean r1) {
        r0.isScrollDisallowed = r1;
    }

    /* renamed from: -$$Nest$fputisScrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1484$$Nest$fputisScrolling(IconPickerView r0, boolean r1) {
        r0.isScrolling = r1;
    }

    /* renamed from: -$$Nest$fputquery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1485$$Nest$fputquery(IconPickerView r0, String r1) {
        r0.query = r1;
    }

    /* renamed from: -$$Nest$fputsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1486$$Nest$fputsearching(IconPickerView r0, boolean r1) {
        r0.searching = r1;
    }

    /* renamed from: -$$Nest$mclampX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m1487$$Nest$mclampX(IconPickerView r0, DisplayMetrics r1, float r2) {
        return r0.clampX(r1, r2);
    }

    /* renamed from: -$$Nest$mclampY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m1488$$Nest$mclampY(IconPickerView r0, DisplayMetrics r1, float r2) {
        return r0.clampY(r1, r2);
    }

    /* renamed from: -$$Nest$mupdateSpringPositions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m1489$$Nest$mupdateSpringPositions(IconPickerView r0) {
        r0.updateSpringPositions();
    }

    public IconPickerView(Context r15) {
        super(r15);
        this.onLongPress = new IconPickerView$$ExternalSyntheticLambda1(this);
        this.systemTopInset = 0;
        this.systemBottomInset = 0;
        this.mPrefs = r15.getSharedPreferences("icon_picker", 0);
        this.touchSlop = ViewConfiguration.get(r15).getScaledTouchSlop();
        GestureDetector r2 = new GestureDetector(r15, new AnonymousClass1(this));
        r2.setIsLongpressEnabled(false);
        AnonymousClass2 r1 = new AnonymousClass2(this, r15, r2);
        this.floatingButtonContainer = r1;
        ImageView r22 = new ImageView(r15);
        this.actionIcon = r22;
        r22.setImageResource(R.drawable.msg_palette);
        r1.addView(r22, LayoutHelper.createFrame(24, 24, 17));
        r1.setVisibility(8);
        addView(r1, LayoutHelper.createFrame(56, 56.0f));
        LinearLayout r12 = new LinearLayout(r15);
        this.bigLayout = r12;
        r12.setOrientation(1);
        r12.setVisibility(8);
        ActionBar r23 = new ActionBar(r15);
        this.actionBar = r23;
        r23.setOccupyStatusBar(false);
        updateTitle();
        r23.setBackButtonImage(R.drawable.ic_ab_back);
        r23.setAllowOverlayTitle(false);
        ActionBarMenu r4 = r23.createMenu();
        r4.addItem(0, R.drawable.outline_header_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass3(this));
        ActionBarMenuItem r42 = r4.addItem(1, R.drawable.ic_ab_other);
        this.otherButton = r42;
        r42.addSubItem(2, R.drawable.msg_media, LocaleController.getString(R.string.IconPickerAllIcons));
        ActionBarMenuSubItem r43 = r42.addSubItem(3, R.drawable.ic_ab_done, LocaleController.getString(R.string.IconPickerSaveAndExit));
        this.saveItem = r43;
        int r5 = Theme.key_featuredStickers_addButtonPressed;
        r43.setColors(Theme.getColor(r5), Theme.getColor(r5));
        r23.setActionBarMenuOnItemClick(new AnonymousClass4(this, r15));
        r12.addView(r23, LayoutHelper.createLinear(-1, -2));
        UniversalRecyclerView r6 = new UniversalRecyclerView(r15, UserConfig.selectedAccount, 0, new IconPickerView$$ExternalSyntheticLambda2(this), new IconPickerView$$ExternalSyntheticLambda3(this), null, null);
        this.listView = r6;
        r6.setLayoutManager(new LinearLayoutManager(r15));
        r12.addView(r6, LayoutHelper.createLinear(-1, 0, 1.0f));
        addView(r12, LayoutHelper.createFrame(-1, -1.0f, 0, 8.0f, 8.0f, 8.0f, 8.0f));
        r12.setClipToOutline(true);
        r12.setOutlineProvider(ViewOutlineProviderImpl.boundsWithRoundRect(AndroidUtilities.dp(10.0f)));
        setOnApplyWindowInsetsListener(new IconPickerView$$ExternalSyntheticLambda4(this));
        updateDrawables();
        setWillNotDraw(false);
    }

    private float clampX(DisplayMetrics r2, float r3) {
        return MathUtils.clamp(r3, AndroidUtilities.dp(16.0f), r2.widthPixels - AndroidUtilities.dp(72.0f));
    }

    private float clampY(DisplayMetrics r3, float r4) {
        return MathUtils.clamp(r4, this.systemTopInset + AndroidUtilities.dp(16.0f), (r3.heightPixels - this.systemBottomInset) - AndroidUtilities.dp(72.0f));
    }

    private void fillItems(ArrayList<UItem> r4, UniversalAdapter r5) {
        IconPack r0 = null;
        if (this.searching == true) goto L5;
    L7:
        String r52 = null;
    L9:
        if (ExteraConfig.getEditingIconPackId() == null) goto L11;
        r0 = IconManager.INSTANCE.findPackById(ExteraConfig.getEditingIconPackId());
    L11:
        Stream r3 = IconObserver.INSTANCE.getUsedIcons().stream().map(new IconPickerView$$ExternalSyntheticLambda7(this, r52, r0)).filter(new IconPickerView$$ExternalSyntheticLambda8()).sorted(Comparator.comparing(new IconPickerView$$ExternalSyntheticLambda9()));
        Objects.requireNonNull(r4);
        r3.forEach(new IconPickerView$$ExternalSyntheticLambda10(r4));
        return;
    L5:
        if (TextUtils.isEmpty(this.query) == true) goto L7;
        r52 = this.query.toLowerCase();
        goto L9
    }

    private /* synthetic */ UItem lambda$fillItems$2(String r3, IconPack r4, Integer r5) {
        String r2 = getResources().getResourceEntryName(r5.intValue());     // Catch: Exception -> L13
        if (r3 == null) goto L9;
        if (r2.toLowerCase().contains(r3) == true) goto L9;
        return null;
    L9:
        if (IconManager.INSTANCE.isBlacklisted(r2) == false) goto L11;
        return null;
    L11:
        UItem r22 = IconPacksEditorActivity.EditorIconCell.Factory.asIcon(r5.intValue(), r2, r4);     // Catch: Exception -> L13
        r22.transparent = true;     // Catch: Exception -> L13
        return r22;
    L13:
        return null;
    }

    private /* synthetic */ void lambda$new$0() {
        this.inLongPress = true;
        performHapticFeedback(0);     // Catch: Exception -> L5
        return;
    }

    private /* synthetic */ WindowInsets lambda$new$1(View r4, WindowInsets r5) {
        this.systemTopInset = r5.getSystemWindowInsetTop();
        this.systemBottomInset = r5.getSystemWindowInsetBottom();
        FrameLayout.LayoutParams r42 = (FrameLayout.LayoutParams) this.bigLayout.getLayoutParams();
        if (r42 == null) goto L6;
        r42.topMargin = AndroidUtilities.dp(8.0f) + this.systemTopInset;
        r42.bottomMargin = AndroidUtilities.dp(8.0f) + this.systemBottomInset;
        this.bigLayout.setLayoutParams(r42);
    L6:
        if (this.isScrolling == false) goto L8;
    L10:
        return r5;
    L8:
        if (this.isBigMenuShown == true) goto L10;
        updateSpringPositions();
        goto L10
    }

    private /* synthetic */ void lambda$showFab$6(DynamicAnimation r2, float r3, float r4) {
        float r32 = r3 / 1000.0f;
        this.floatingButtonContainer.setPivotX(AndroidUtilities.dp(28.0f));
        this.floatingButtonContainer.setPivotY(AndroidUtilities.dp(28.0f));
        this.floatingButtonContainer.setScaleX(r32);
        this.floatingButtonContainer.setScaleY(r32);
        this.floatingButtonContainer.setAlpha(MathUtils.clamp(r32, 0.0f, 1.0f));
        invalidate();
    }

    private /* synthetic */ void lambda$showIconList$4(float r4, float r5, float r6, float r7, Window r8, DynamicAnimation r9, float r10, float r11) {
        float r102 = r10 / 1000.0f;
        this.bigLayout.setAlpha(r102);
        this.bigLayout.setTranslationX(AndroidUtilities.lerp(r4, 0.0f, r102));
        this.bigLayout.setTranslationY(AndroidUtilities.lerp(r5, 0.0f, r102));
        this.bigLayout.setPivotX((this.floatingButtonContainer.getTranslationX() - r6) + AndroidUtilities.dp(28.0f));
        this.bigLayout.setPivotY((this.floatingButtonContainer.getTranslationY() - r7) + AndroidUtilities.dp(28.0f));
        if (this.bigLayout.getWidth() == 0) goto L6;
        this.bigLayout.setScaleX(AndroidUtilities.lerp(this.floatingButtonContainer.getWidth() / this.bigLayout.getWidth(), 1.0f, r102));
    L6:
        if (this.bigLayout.getHeight() == 0) goto L8;
        this.bigLayout.setScaleY(AndroidUtilities.lerp(this.floatingButtonContainer.getHeight() / this.bigLayout.getHeight(), 1.0f, r102));
    L8:
        this.floatingButtonContainer.setTranslationX(AndroidUtilities.lerp(r4 + r6, (getWidth() / 2.0f) - AndroidUtilities.dp(28.0f), r102));
        this.floatingButtonContainer.setTranslationY(AndroidUtilities.lerp(r5 + r7, (getHeight() / 2.0f) - AndroidUtilities.dp(28.0f), r102));
        this.floatingButtonContainer.setAlpha(1.0f - r102);
        r8.setStatusBarColor(ColorUtils.blendARGB(this.wasStatusBar, 2046820352, r102));
        invalidate();
    }

    private /* synthetic */ void lambda$showIconList$5(float r1, float r2, float r3, float r4, boolean r5, DynamicAnimation r6, boolean r7, float r8, float r9) {
        this.floatingButtonContainer.setTranslationX(r1 + r2);
        this.floatingButtonContainer.setTranslationY(r3 + r4);
        if (r5 == true) goto L6;
        this.bigLayout.setVisibility(8);
        return;
    }

    private void onClick(UItem r1, View r2, int r3, float r4, float r5) {
        IconManager.INSTANCE.showReplaceAlert(getContext(), r1.id);
    }

    private void updateDrawables() {
        Drawable r1 = Theme.createSimpleSelectorCircleDrawable(AndroidUtilities.dp(56.0f), Theme.getColor(Theme.key_chats_actionBackground), Theme.getColor(Theme.key_chats_actionPressedBackground));
        Drawable r2 = ResourcesCompat.getDrawable(getResources(), R.drawable.floating_shadow, getContext().getTheme()).mutate();
        r2.setColorFilter(new PorterDuffColorFilter(-16777216, PorterDuff.Mode.MULTIPLY));
        CombinedDrawable r3 = new CombinedDrawable(r2, r1, 0, 0);
        r3.setIconSize(AndroidUtilities.dp(56.0f), AndroidUtilities.dp(56.0f));
        this.floatingButtonBackground = r3;
        GradientDrawable r0 = new GradientDrawable();
        int r12 = Theme.key_dialogBackground;
        r0.setColor(Theme.getColor(r12));
        r0.setCornerRadius(AndroidUtilities.dp(10.0f));
        this.bigLayout.setBackground(r0);
        this.bigLayout.setElevation(AndroidUtilities.dp(4.0f));
        ImageView r02 = this.actionIcon;
        if (r02 == null) goto L5;
        r02.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chats_actionIcon), PorterDuff.Mode.SRC_IN));
    L5:
        ActionBar r03 = this.actionBar;
        if (r03 == null) goto L8;
        r03.setBackgroundColor(Theme.getColor(r12));
        ActionBar r04 = this.actionBar;
        int r13 = Theme.key_dialogTextBlack;
        r04.setTitleColor(Theme.getColor(r13));
        this.actionBar.setItemsColor(Theme.getColor(r13), false);
        this.actionBar.setItemsBackgroundColor(Theme.getColor(Theme.key_dialogButtonSelector), false);
    L8:
        ActionBarMenuItem r05 = this.otherButton;
        if (r05 == null) goto L13;
        r05.setPopupItemsColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem), false);
        this.otherButton.setPopupItemsColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItemIcon), true);
        this.otherButton.redrawPopup(Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground));
        ActionBarMenuSubItem r06 = this.saveItem;
        if (r06 == null) goto L13;
        int r14 = Theme.key_featuredStickers_addButtonPressed;
        r06.setColors(Theme.getColor(r14), Theme.getColor(r14));
    L13:
        invalidate();
    }

    private void updateSpringPositions() {
        SpringForce r0 = this.fabXSpring.getSpring();
        if (this.fabXSpring.getSpring().getFinalPosition() < (getWidth() / 2.0f)) goto L6;
        DisplayMetrics r1 = getResources().getDisplayMetrics();
        float r2 = 2.1474836E9f;
    L7:
        r0.setFinalPosition(clampX(r1, r2));
        this.fabYSpring.getSpring().setFinalPosition(clampY(getResources().getDisplayMetrics(), this.fabYSpring.getSpring().getFinalPosition()));
        this.fabXSpring.start();
        this.fabYSpring.start();
        return;
    L6:
        r1 = getResources().getDisplayMetrics();
        r2 = -2.1474836E9f;
        goto L7
    }

    private void updateTitle() {
        if (this.actionBar != null) goto L6;
        return;
    L6:
        if (ExteraConfig.getEditingIconPackId() == null) goto L11;
        IconPack r0 = IconManager.INSTANCE.findPackById(ExteraConfig.getEditingIconPackId());
        if (r0 == null) goto L12;
        this.actionBar.setTitle(r0.getName());
        return;
    L12:
        return;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.iconPackUpdated) goto L13;
        updateTitle();
        if (this.listView.getLayoutManager() == null) goto L7;
        Parcelable r12 = this.listView.getLayoutManager().onSaveInstanceState();
    L8:
        this.listView.adapter.update(true);
        if (r12 == null) goto L16;
        this.listView.getLayoutManager().onRestoreInstanceState(r12);
        return;
    L16:
        return;
    L7:
        r12 = null;
        goto L8
    L13:
        if (r1 != NotificationCenter.didSetNewTheme) goto L17;
        updateDrawables();
        this.listView.adapter.update(false);
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
        WindowInsets r0 = getRootWindowInsets();
        if (r0 == null) goto L7;
        this.systemTopInset = r0.getSystemWindowInsetTop();
        this.systemBottomInset = r0.getSystemWindowInsetBottom();
        FrameLayout.LayoutParams r02 = (FrameLayout.LayoutParams) this.bigLayout.getLayoutParams();
        if (r02 == null) goto L7;
        r02.topMargin = AndroidUtilities.dp(8.0f) + this.systemTopInset;
        r02.bottomMargin = AndroidUtilities.dp(8.0f) + this.systemBottomInset;
        this.bigLayout.setLayoutParams(r02);
    L7:
        float r03 = this.mPrefs.getFloat("x", -1.0f);
        float r1 = this.mPrefs.getFloat("y", -1.0f);
        DisplayMetrics r3 = getResources().getDisplayMetrics();
        FrameLayout r4 = this.floatingButtonContainer;
        if (r03 != (-1.0f)) goto L10;
    L14:
        float r04 = 2.1474836E9f;
    L15:
        r4.setTranslationX(clampX(r3, r04));
        FrameLayout r05 = this.floatingButtonContainer;
        if (r1 != (-1.0f)) goto L18;
        r1 = r3.heightPixels / 2.0f;
    L18:
        r05.setTranslationY(clampY(r3, r1));
        FrameLayout r12 = this.floatingButtonContainer;
        this.fabXSpring = new SpringAnimation(r12, DynamicAnimation.TRANSLATION_X, r12.getTranslationX()).setSpring(new SpringForce(this.floatingButtonContainer.getTranslationX()).setStiffness(650.0f).setDampingRatio(0.75f));
        FrameLayout r13 = this.floatingButtonContainer;
        this.fabYSpring = new SpringAnimation(r13, DynamicAnimation.TRANSLATION_Y, r13.getTranslationY()).setSpring(new SpringForce(this.floatingButtonContainer.getTranslationY()).setStiffness(650.0f).setDampingRatio(0.75f));
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.iconPackUpdated);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.didSetNewTheme);
        return;
    L10:
        if (r03 >= (r3.widthPixels / 2.0f)) goto L14;
        r04 = -2.1474836E9f;
        goto L15
    }

    public boolean onBackPressed(boolean r3) {
        if (this.isBigMenuShown == true) goto L5;
        return false;
    L5:
        if (r3 == true) goto L7;
    L11:
        return true;
    L7:
        if (this.searching == false) goto L10;
        this.actionBar.closeSearchField();
        return true;
    L10:
        showIconList(false);
        goto L11
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
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.iconPackUpdated);
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
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent r1) {
        return this.isBigMenuShown;
    }

    @SuppressLint({"ApplySharedPref"})
    public void saveConfig() {
        this.mPrefs.edit().putFloat("x", this.fabXSpring.getSpring().getFinalPosition()).putFloat("y", this.fabYSpring.getSpring().getFinalPosition()).apply();
    }

    public void showFab() {
        this.floatingButtonContainer.setVisibility(0);
        new SpringAnimation(new FloatValueHolder(0.0f)).setSpring(new SpringForce(1000.0f).setStiffness(750.0f).setDampingRatio(0.75f)).addUpdateListener(new IconPickerView$$ExternalSyntheticLambda0(this)).start();
    }

    public void showIconList(boolean r13) {
        if (this.isBigMenuShown != r13) goto L5;
        return;
    L5:
        this.isBigMenuShown = r13;
        if (r13 == false) goto L8;
        this.bigLayout.setVisibility(0);
        this.listView.adapter.update(true);
    L8:
        Window r6 = ((Activity) getContext()).getWindow();
        if (r13 == false) goto L11;
        this.wasStatusBar = r6.getStatusBarColor();
    L11:
        FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) this.bigLayout.getLayoutParams();
        float r5 = r0.topMargin;
        float r3 = r0.leftMargin;
        float r2 = this.floatingButtonContainer.getTranslationX() - r3;
        float r4 = this.floatingButtonContainer.getTranslationY() - r5;
        float r8 = 0.0f;
        if (r13 == false) goto L14;
        float r10 = 0.0f;
    L15:
        SpringAnimation r02 = new SpringAnimation(new FloatValueHolder(r10));
        SpringForce r7 = new SpringForce(1000.0f).setStiffness(900.0f).setDampingRatio(1.0f);
        if (r13 == false) goto L18;
        r8 = 1000.0f;
    L18:
        r02.setSpring(r7.setFinalPosition(r8)).addUpdateListener(new IconPickerView$$ExternalSyntheticLambda5(this, r2, r4, r3, r5, r6)).addEndListener(new IconPickerView$$ExternalSyntheticLambda6(this, r2, r3, r4, r5, r13)).start();
        return;
    L14:
        r10 = 1000.0f;
        goto L15
    }
}
