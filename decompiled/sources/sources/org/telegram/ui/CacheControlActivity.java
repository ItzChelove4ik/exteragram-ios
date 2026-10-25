package org.telegram.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.pillstack.core.PillStackConfig;
import com.exteragram.messenger.pillstack.core.PillType;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.CacheByChatsController;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.FilePathDatabase;
import org.telegram.messenger.FilesMigrationService;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.BottomSheet$$ExternalSyntheticLambda7;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.CachedMediaLayout;
import org.telegram.ui.Cells.CheckBoxCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextCheckBoxCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CacheChart;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.HideViewAfterAnimation;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ListView.AdapterWithDiffUtils;
import org.telegram.ui.Components.LoadingDrawable;
import org.telegram.ui.Components.NestedSizeNotifierLayout;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.SlideChooseView;
import org.telegram.ui.Components.StorageDiagramView;
import org.telegram.ui.Components.StorageUsageView;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.DialogCacheBottomSheet;
import org.telegram.ui.Storage.CacheModel;

/* loaded from: classes.dex */
public class CacheControlActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private static int LISTDIR_DOCTYPE2_EMOJI = 3;
    private static int LISTDIR_DOCTYPE2_OTHER = 5;
    private static int LISTDIR_DOCTYPE2_TEMP = 4;
    private static int LISTDIR_DOCTYPE_MUSIC = 2;
    private static int LISTDIR_DOCTYPE_OTHER_THAN_MUSIC = 1;
    public static volatile boolean canceled = false;
    private static Long lastDeviceTotalFreeSize;
    private static Long lastDeviceTotalSize;
    private static Long lastTotalSizeCalculated;
    private static long lastTotalSizeCalculatedTime;
    private ValueAnimator actionBarAnimator;
    private float actionBarShadowAlpha;
    private boolean actionBarShown;
    private float actionBarShownT;
    private ActionBarMenu actionMode;
    private TextView actionModeClearButton;
    private AnimatedTextView actionModeSubtitle;
    private AnimatedTextView actionModeTitle;
    private View actionTextView;
    private long audioSize;
    private BottomSheet bottomSheet;
    private View bottomSheetView;
    private CacheChart cacheChart;
    private CacheChartHeader cacheChartHeader;
    private long cacheEmojiSize;
    CacheModel cacheModel;
    private long cacheSize;
    private long cacheTempSize;
    private CachedMediaLayout cachedMediaLayout;
    private boolean calculating;
    private boolean changeStatusBar;
    private ClearCacheButtonInternal clearCacheButton;
    private ActionBarMenuSubItem clearDatabaseItem;
    private boolean collapsed;
    private long databaseSize;
    private long documentsSize;
    long fragmentCreateTime;
    private ArrayList<ItemInner> itemInners;
    private LinearLayoutManager layoutManager;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private boolean loadingDialogs;
    private long logsSize;
    private long migrateOldFolderRow;
    private long musicSize;
    private NestedSizeNotifierLayout nestedSizeNotifierLayout;
    private ArrayList<ItemInner> oldItems;
    private int[] percents;
    private long photoSize;
    AlertDialog progressDialog;
    private ActionBarMenuSubItem resetDatabaseItem;
    private int sectionsEndRow;
    private int sectionsStartRow;
    private boolean[] selected;
    private long stickersCacheSize;
    private long storiesSize;
    private float[] tempSizes;
    private long totalDeviceFreeSize;
    private long totalDeviceSize;
    private long totalSize;
    private boolean updateDatabaseSize;
    private long videoSize;

    /* renamed from: org.telegram.ui.CacheControlActivity$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ CacheControlActivity this$0;

        public AnonymousClass1(CacheControlActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r4) {
            if (r4 != (-1)) goto L16;
            boolean r42 = CacheControlActivity.access$000(this.this$0).isActionModeShowed();
            CacheControlActivity r0 = this.this$0;
            if (r42 == false) goto L13;
            CacheModel r43 = r0.cacheModel;
            if (r43 == null) goto L10;
            r43.clearSelection();
        L10:
            if (CacheControlActivity.m6427$$Nest$fgetcachedMediaLayout(this.this$0) == null) goto L27;
            CacheControlActivity.m6427$$Nest$fgetcachedMediaLayout(this.this$0).showActionMode(false);
            CacheControlActivity.m6427$$Nest$fgetcachedMediaLayout(this.this$0).updateVisibleRows();
            return;
        L27:
            return;
        L13:
            r0.finishFragment();
            return;
        L16:
            if (r4 != 1) goto L20;
            CacheControlActivity.m6457$$Nest$mclearSelectedFiles(this.this$0);
            return;
        L20:
            if (r4 != 3) goto L24;
            CacheControlActivity.m6456$$Nest$mclearDatabase(this.this$0, false);
            return;
        L24:
            if (r4 != 4) goto L28;
            CacheControlActivity.m6456$$Nest$mclearDatabase(this.this$0, true);
            return;
        }
    }

    /* renamed from: org.telegram.ui.CacheControlActivity$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 extends NestedSizeNotifierLayout {
        final /* synthetic */ CacheControlActivity this$0;

        public AnonymousClass2(CacheControlActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r6) {
            super.dispatchDraw(r6);
            boolean r0 = isPinnedToTop();
            if (r0 == true) goto L5;
        L7:
            if (r0 == false) goto L9;
        L11:
            CacheControlActivity r02 = this.this$0;
            CacheControlActivity.m6448$$Nest$fputactionBarShadowAlpha(r02, Utilities.clamp(CacheControlActivity.m6419$$Nest$fgetactionBarShadowAlpha(r02), 1.0f, 0.0f));
            if (CacheControlActivity.access$100(this.this$0) == null) goto L15;
            CacheControlActivity.access$200(this.this$0).drawHeaderShadow(r6, (int) ((CacheControlActivity.m6420$$Nest$fgetactionBarShownT(this.this$0) * 255.0f) * CacheControlActivity.m6419$$Nest$fgetactionBarShadowAlpha(this.this$0)), AndroidUtilities.statusBarHeight + ActionBar.getCurrentActionBarHeight());
            return;
        L15:
            return;
        L9:
            if (CacheControlActivity.m6419$$Nest$fgetactionBarShadowAlpha(this.this$0) == 1.0f) goto L11;
            CacheControlActivity r03 = this.this$0;
            CacheControlActivity.m6448$$Nest$fputactionBarShadowAlpha(r03, CacheControlActivity.m6419$$Nest$fgetactionBarShadowAlpha(r03) + 0.16f);
            invalidate();
            goto L11
        L5:
            if (CacheControlActivity.m6419$$Nest$fgetactionBarShadowAlpha(this.this$0) == 0.0f) goto L7;
            CacheControlActivity r04 = this.this$0;
            CacheControlActivity.m6448$$Nest$fputactionBarShadowAlpha(r04, CacheControlActivity.m6419$$Nest$fgetactionBarShadowAlpha(r04) - 0.16f);
            invalidate();
            goto L11
        }
    }

    /* renamed from: org.telegram.ui.CacheControlActivity$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 extends RecyclerListView {
        final /* synthetic */ CacheControlActivity this$0;

        public AnonymousClass3(CacheControlActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public boolean allowSelectChildAtPosition(View r1) {
            if (r1 == CacheControlActivity.m6423$$Nest$fgetcacheChart(this.this$0)) goto L6;
            return true;
        L6:
            return false;
        }
    }

    /* renamed from: org.telegram.ui.CacheControlActivity$4, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass4 extends DefaultItemAnimator {
        final /* synthetic */ CacheControlActivity this$0;

        public AnonymousClass4(CacheControlActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onMoveAnimationUpdate(RecyclerView.ViewHolder r1) {
            CacheControlActivity.m6434$$Nest$fgetlistView(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.CacheControlActivity$5, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass5 extends RecyclerView.OnScrollListener {
        boolean pinned;
        final /* synthetic */ CacheControlActivity this$0;

        public AnonymousClass5(CacheControlActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            super.onScrolled(r1, r2, r3);
            CacheControlActivity r12 = this.this$0;
            if (CacheControlActivity.m6433$$Nest$fgetlayoutManager(r12).findFirstVisibleItemPosition() <= 0) goto L5;
        L8:
            boolean r22 = true;
        L9:
            CacheControlActivity.m6464$$Nest$mupdateActionBar(r12, r22);
            if (this.pinned == CacheControlActivity.m6438$$Nest$fgetnestedSizeNotifierLayout(this.this$0).isPinnedToTop()) goto L13;
            this.pinned = CacheControlActivity.m6438$$Nest$fgetnestedSizeNotifierLayout(this.this$0).isPinnedToTop();
            CacheControlActivity.m6438$$Nest$fgetnestedSizeNotifierLayout(this.this$0).invalidate();
            return;
        L13:
            return;
        L5:
            if (CacheControlActivity.access$300(this.this$0).isActionModeShowed() == true) goto L8;
            r22 = false;
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.CacheControlActivity$6, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass6 implements DialogCacheBottomSheet.Delegate {
        final /* synthetic */ CacheControlActivity this$0;
        final /* synthetic */ DialogFileEntities val$entities;

        public AnonymousClass6(CacheControlActivity r1, DialogFileEntities r2) {
            this.this$0 = r1;
            this.val$entities = r2;
        }

        @Override // org.telegram.ui.DialogCacheBottomSheet.Delegate
        public void cleanupDialogFiles(DialogFileEntities r1, StorageDiagramView.ClearViewData[] r2, CacheModel r3) {
            CacheControlActivity.m6454$$Nest$mcleanupDialogFiles(this.this$0, r1, r2, r3);
        }

        @Override // org.telegram.ui.DialogCacheBottomSheet.Delegate
        public void onAvatarClick() {
            CacheControlActivity.m6422$$Nest$fgetbottomSheet(this.this$0).dismiss();
            Bundle r0 = new Bundle();
            long r1 = this.val$entities.dialogId;
            if (r1 <= 0) goto L5;
            r0.putLong("user_id", r1);
        L6:
            this.this$0.presentFragment(new ProfileActivity(r0, null));
            return;
        L5:
            r0.putLong("chat_id", -r1);
            goto L6
        }
    }

    /* loaded from: classes6.dex */
    public class CacheChartHeader extends FrameLayout {
        boolean firstSet;
        Paint loadingBackgroundPaint;
        LoadingDrawable loadingDrawable;
        AnimatedFloat loadingFloat;
        Float percent;
        AnimatedFloat percentAnimated;
        Paint percentPaint;
        RectF progressRect;
        private float[] radii;
        private Path roundPath;
        TextView[] subtitle;
        final /* synthetic */ CacheControlActivity this$0;
        AnimatedTextView title;
        Float usedPercent;
        AnimatedFloat usedPercentAnimated;
        Paint usedPercentPaint;

        public static /* synthetic */ void $r8$lambda$TyVroJ31C7BuRPreL3GMCRQnpTk(CacheChartHeader r0, ValueAnimator r1) {
            r0.lambda$updateViewVisible$0(r1);
        }

        /* renamed from: $r8$lambda$iAc-MLi5MM_Fmjnpd7ATYouZvxM, reason: not valid java name */
        public static /* synthetic */ void m6468$r8$lambda$iAcMLi5MM_Fmjnpd7ATYouZvxM(CacheChartHeader r0, ValueAnimator r1) {
            r0.lambda$updateViewVisible$1(r1);
        }

        public CacheChartHeader(CacheControlActivity r20, Context r21) {
            this.this$0 = r20;
            super(r21);
            this.subtitle = new TextView[3];
            this.progressRect = new RectF();
            this.loadingDrawable = new LoadingDrawable();
            CubicBezierInterpolator r10 = CubicBezierInterpolator.EASE_OUT_QUINT;
            this.percentAnimated = new AnimatedFloat(this, 450, r10);
            this.usedPercentAnimated = new AnimatedFloat(this, 450, r10);
            this.loadingFloat = new AnimatedFloat(this, 450, r10);
            this.loadingBackgroundPaint = new Paint(1);
            this.percentPaint = new Paint(1);
            this.usedPercentPaint = new Paint(1);
            this.firstSet = true;
            AnimatedTextView r4 = new AnimatedTextView(r21);
            this.title = r4;
            r4.setAnimationProperties(0.35f, 0, 350, r10);
            this.title.setTypeface(AndroidUtilities.bold());
            this.title.setTextSize(AndroidUtilities.dp(20.0f));
            this.title.setText(LocaleController.getString(R.string.StorageUsage));
            this.title.setGravity(17);
            this.title.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            addView(this.title, LayoutHelper.createFrame(-2, 26, 49));
            int r5 = 0;
        L3:
            if (r5 >= 3) goto L18;
            this.subtitle[r5] = new TextView(r21);
            this.subtitle[r5].setTextSize(1, 13.0f);
            this.subtitle[r5].setGravity(17);
            this.subtitle[r5].setPadding(AndroidUtilities.dp(24.0f), 0, AndroidUtilities.dp(24.0f), 0);
            if (r5 != 0) goto L8;
            this.subtitle[r5].setText(LocaleController.getString(R.string.StorageUsageCalculating));
        L12:
            this.subtitle[r5].setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText4));
            TextView r7 = this.subtitle[r5];
            if (r5 != 2) goto L16;
            float r6 = 12.0f;
        L17:
            addView(r7, LayoutHelper.createFrame(-2, -2.0f, 17, 0.0f, r6, 0.0f, 0.0f));
            r5 = r5 + 1;
            goto L3
        L16:
            r6 = -6.0f;
            goto L17
        L8:
            if (r5 != 1) goto L10;
            this.subtitle[r5].setAlpha(0.0f);
            this.subtitle[r5].setText(LocaleController.getString(R.string.StorageUsageTelegram));
            this.subtitle[r5].setVisibility(4);
            goto L12
        L10:
            if (r5 != 2) goto L12;
            this.subtitle[r5].setText(LocaleController.getString(R.string.StorageCleared2));
            this.subtitle[r5].setAlpha(0.0f);
            this.subtitle[r5].setVisibility(4);
            goto L12
        L18:
            this.loadingDrawable.setColors(Theme.getColor(Theme.key_actionBarActionModeDefaultSelector), Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText4), 0.2f));
            this.loadingDrawable.setRadiiDp(4.0f);
            this.loadingDrawable.setCallback(this);
        }

        private void drawRoundRect(Canvas r3, RectF r4, float r5, float r6, Paint r7) {
            Path r0 = this.roundPath;
            if (r0 != null) goto L5;
            this.roundPath = new Path();
        L7:
            if (this.radii != null) goto L9;
            this.radii = new float[8];
        L9:
            float[] r02 = this.radii;
            r02[7] = r5;
            r02[6] = r5;
            r02[1] = r5;
            r02[0] = r5;
            r02[5] = r6;
            r02[4] = r6;
            r02[3] = r6;
            r02[2] = r6;
            this.roundPath.addRoundRect(r4, r02, Path.Direction.CW);
            r3.drawPath(this.roundPath, r7);
            return;
        L5:
            r0.rewind();
            goto L7
        }

        private /* synthetic */ void lambda$updateViewVisible$0(ValueAnimator r1) {
            invalidate();
        }

        private /* synthetic */ void lambda$updateViewVisible$1(ValueAnimator r1) {
            invalidate();
        }

        private void switchSubtitle(int r6) {
            boolean r1 = false;
            if ((System.currentTimeMillis() - this.this$0.fragmentCreateTime) <= 40) goto L5;
            boolean r0 = true;
        L6:
            TextView r3 = this.subtitle[0];
            if (r6 != 0) goto L9;
            boolean r4 = true;
        L10:
            updateViewVisible(r3, r4, r0);
            TextView r32 = this.subtitle[1];
            if (r6 != 1) goto L13;
            boolean r42 = true;
        L14:
            updateViewVisible(r32, r42, r0);
            TextView r33 = this.subtitle[2];
            if (r6 != 2) goto L17;
            r1 = true;
        L17:
            updateViewVisible(r33, r1, r0);
            return;
        L13:
            r42 = false;
            goto L14
        L9:
            r4 = false;
            goto L10
        L5:
            r0 = false;
            goto L6
        }

        private void updateViewVisible(View r8, boolean r9, boolean r10) {
            if (r8 != null) goto L4;
            return;
        L4:
            int r1 = 0;
            if (r8.getParent() != null) goto L7;
            r10 = false;
        L7:
            Integer r2 = null;
            r8.animate().setListener(null).cancel();
            float r0 = 1.0f;
            float r4 = 0.0f;
            if (r10 == true) goto L26;
            if (r9 == true) goto L12;
            r1 = 4;
        L12:
            r8.setVisibility(r1);
            if (r9 == false) goto L15;
            r2 = 1;
        L15:
            r8.setTag(r2);
            if (r9 == true) goto L19;
            r0 = 0.0f;
        L19:
            r8.setAlpha(r0);
            if (r9 == true) goto L23;
            r4 = AndroidUtilities.dp(8.0f);
        L23:
            r8.setTranslationY(r4);
            invalidate();
            return;
        L26:
            if (r9 == true) goto L28;
            r8.animate().alpha(0.0f).translationY(AndroidUtilities.dp(8.0f)).setListener(new HideViewAfterAnimation(r8)).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(340).setUpdateListener(new CacheControlActivity$CacheChartHeader$$ExternalSyntheticLambda1(this)).start();
            return;
        L28:
            if (r8.getVisibility() == 0) goto L30;
            r8.setVisibility(0);
            r8.setAlpha(0.0f);
            r8.setTranslationY(AndroidUtilities.dp(8.0f));
        L30:
            r8.animate().alpha(1.0f).translationY(0.0f).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(340).setUpdateListener(new CacheControlActivity$CacheChartHeader$$ExternalSyntheticLambda0(this)).start();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r20) {
            float r7 = 1.0f - this.subtitle[2].getAlpha();
            AnimatedFloat r1 = this.loadingFloat;
            if (this.percent != null) goto L5;
            float r3 = 1.0f;
        L6:
            float r9 = r1.set(r3);
            AnimatedFloat r12 = this.percentAnimated;
            Float r32 = this.percent;
            if (r32 != null) goto L9;
            float r33 = 0.0f;
        L10:
            float r10 = r12.set(r33);
            AnimatedFloat r13 = this.usedPercentAnimated;
            Float r34 = this.usedPercent;
            if (r34 != null) goto L13;
            float r35 = 0.0f;
        L14:
            float r11 = r13.set(r35);
            Paint r14 = this.loadingBackgroundPaint;
            int r122 = Theme.key_actionBarActionModeDefaultSelector;
            r14.setColor(Theme.getColor(r122));
            this.loadingBackgroundPaint.setAlpha((int) (r1.getAlpha() * r7));
            RectF r15 = AndroidUtilities.rectTmp;
            float r132 = 1.0f - r9;
            float r36 = Math.max(this.progressRect.left + (Math.max(AndroidUtilities.dp(4.0f), this.progressRect.width() * r11) * r132), this.progressRect.left + (Math.max(AndroidUtilities.dp(4.0f), this.progressRect.width() * r10) * r132)) + AndroidUtilities.dp(1.0f);
            RectF r4 = this.progressRect;
            r15.set(r36, r4.top, r4.right, r4.bottom);
            if (r15.left < r15.right) goto L17;
        L19:
            RectF r2 = r15;
        L20:
            this.loadingDrawable.setBounds(this.progressRect);
            this.loadingDrawable.setAlpha((int) ((255.0f * r7) * r9));
            this.loadingDrawable.draw(r20);
            Paint r16 = this.usedPercentPaint;
            if (Theme.isCurrentThemeMonet() == false) goto L23;
            int r42 = Theme.getColor(Theme.key_statisticChartLine_red);
        L24:
            r16.setColor(r42);
            this.usedPercentPaint.setAlpha((int) (r1.getAlpha() * r7));
            float r17 = (this.progressRect.left + (Math.max(AndroidUtilities.dp(4.0f), this.progressRect.width() * r10) * r132)) + AndroidUtilities.dp(1.0f);
            RectF r43 = this.progressRect;
            r2.set(r17, r43.top, r43.left + (Math.max(AndroidUtilities.dp(4.0f), this.progressRect.width() * r11) * r132), this.progressRect.bottom);
            if (r2.width() <= AndroidUtilities.dp(3.0f)) goto L31;
            float r18 = AndroidUtilities.dp(1.0f);
            if (r11 <= 0.97f) goto L29;
            float r44 = 2.0f;
        L30:
            drawRoundRect(r20, r2, r18, AndroidUtilities.dp(r44), this.usedPercentPaint);
            goto L31
        L29:
            r44 = 1.0f;
        L31:
            this.percentPaint.setColor(Theme.getColor(Theme.key_radioBackgroundChecked));
            this.percentPaint.setAlpha((int) (r1.getAlpha() * r7));
            RectF r19 = this.progressRect;
            float r37 = r19.left;
            r2.set(r37, r19.top, (r132 * Math.max(AndroidUtilities.dp(4.0f), this.progressRect.width() * r10)) + r37, this.progressRect.bottom);
            float r38 = AndroidUtilities.dp(2.0f);
            if (r10 <= 0.97f) goto L34;
            float r6 = 2.0f;
        L35:
            drawRoundRect(r20, r2, r38, AndroidUtilities.dp(r6), this.percentPaint);
            if (r9 <= 0.0f) goto L38;
        L39:
            invalidate();
        L40:
            super.dispatchDraw(r20);
            return;
        L38:
            if (this.percentAnimated.isInProgress() == false) goto L40;
        L34:
            r6 = 1.0f;
            goto L35
        L23:
            r42 = ColorUtils.blendARGB(Theme.getColor(Theme.key_radioBackgroundChecked), Theme.getColor(r122), 0.75f);
            goto L24
        L17:
            if (r15.width() <= AndroidUtilities.dp(3.0f)) goto L19;
            r2 = r15;
            drawRoundRect(r20, r2, AndroidUtilities.dp(AndroidUtilities.lerp(1, 2, r9)), AndroidUtilities.dp(2.0f), this.loadingBackgroundPaint);
            goto L20
        L13:
            r35 = r34.floatValue();
            goto L14
        L9:
            r33 = r32.floatValue();
            goto L10
        L5:
            r3 = 0.0f;
            goto L6
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r7, int r8) {
            int r72 = View.MeasureSpec.getSize(r7);
            int r0 = (int) Math.min(AndroidUtilities.dp(174.0f), r72 * 0.8d);
            super.measureChildren(View.MeasureSpec.makeMeasureSpec(r72, TLObject.FLAG_30), r8);
            int r82 = AndroidUtilities.dp(72.0f);
            int r2 = 0;
            int r3 = 0;
        L3:
            TextView[] r4 = this.subtitle;
            if (r2 >= r4.length) goto L10;
            int r42 = r4[r2].getMeasuredHeight();
            if (r2 != 2) goto L8;
            int r5 = AndroidUtilities.dp(16.0f);
        L9:
            r3 = Math.max(r3, r42 - r5);
            r2 = r2 + 1;
            goto L3
        L8:
            r5 = 0;
            goto L9
        L10:
            setMeasuredDimension(r72, r82 + r3);
            this.progressRect.set((r72 - r0) / 2.0f, r8 - AndroidUtilities.dp(30.0f), (r72 + r0) / 2.0f, r8 - AndroidUtilities.dp(26.0f));
        }

        public void setData(boolean r4, float r5, float r6) {
            AnimatedTextView r0 = this.title;
            if (r4 == false) goto L5;
            String r1 = LocaleController.getString(R.string.StorageUsage);
        L6:
            r0.setText(r1);
            if (r4 == false) goto L13;
            TextView[] r02 = this.subtitle;
            if (r5 >= 0.01f) goto L11;
            r02[1].setText(LocaleController.formatString(R.string.StorageUsageTelegramLess, new Object[]{CacheControlActivity.m6458$$Nest$mformatPercent(this.this$0, r5)}));
        L12:
            switchSubtitle(1);
        L14:
            this.firstSet = false;
            this.percent = Float.valueOf(r5);
            this.usedPercent = Float.valueOf(r6);
            invalidate();
            return;
        L11:
            r02[1].setText(LocaleController.formatString(R.string.StorageUsageTelegram, new Object[]{CacheControlActivity.m6458$$Nest$mformatPercent(this.this$0, r5)}));
            goto L12
        L13:
            switchSubtitle(2);
            goto L14
        L5:
            r1 = LocaleController.getString(R.string.StorageCleared);
            goto L6
        }
    }

    /* loaded from: classes6.dex */
    public static class ClearCacheButton extends FrameLayout {
        FrameLayout button;
        TextView rtlTextView;
        AnimatedTextView.AnimatedTextDrawable textView;
        AnimatedTextView.AnimatedTextDrawable valueTextView;

        /* renamed from: org.telegram.ui.CacheControlActivity$ClearCacheButton$1, reason: invalid class name */
        public class AnonymousClass1 extends FrameLayout {
            final /* synthetic */ ClearCacheButton this$0;

            public AnonymousClass1(ClearCacheButton r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // android.view.ViewGroup, android.view.View
            public void dispatchDraw(Canvas r6) {
                int r1 = AndroidUtilities.dp(8.0f);
                int r2 = (((getMeasuredWidth() - r1) - ((int) this.this$0.valueTextView.getCurrentWidth())) + ((int) this.this$0.textView.getCurrentWidth())) / 2;
                if (LocaleController.isRTL == false) goto L6;
                super.dispatchDraw(r6);
                return;
            L6:
                this.this$0.textView.setBounds(0, 0, r2, getHeight());
                this.this$0.textView.draw(r6);
                this.this$0.valueTextView.setBounds(r2 + AndroidUtilities.dp(8.0f), 0, getWidth(), getHeight());
                this.this$0.valueTextView.draw(r6);
            }

            @Override // android.view.View
            public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
                super.onInitializeAccessibilityNodeInfo(r1);
                r1.setClassName("android.widget.Button");
            }

            @Override // android.view.ViewGroup
            public boolean onInterceptTouchEvent(MotionEvent r1) {
                super.onInterceptTouchEvent(r1);
                return true;
            }

            @Override // android.view.View
            public boolean verifyDrawable(Drawable r3) {
                ClearCacheButton r0 = this.this$0;
                if (r3 != r0.valueTextView) goto L5;
                return true;
            L5:
                if (r3 != r0.textView) goto L7;
                return true;
            L7:
                if (super.verifyDrawable(r3) == true) goto L14;
                return false;
            L14:
                return true;
            }
        }

        public ClearCacheButton(Context r15) {
            super(r15);
            AnonymousClass1 r0 = new AnonymousClass1(this, r15);
            this.button = r0;
            int r1 = Theme.key_featuredStickers_addButton;
            r0.setBackground(Theme.AdaptiveRipple.filledRectByKey(r1, new float[]{24.0f}));
            this.button.setImportantForAccessibility(1);
            ScaleStateListAnimator.apply(this.button, 0.02f, 1.2f);
            if (LocaleController.isRTL == false) goto L5;
            TextView r02 = new TextView(r15);
            this.rtlTextView = r02;
            r02.setText(LocaleController.getString(R.string.ClearCache));
            this.rtlTextView.setGravity(17);
            this.rtlTextView.setTextSize(1, 14.0f);
            this.rtlTextView.setTypeface(AndroidUtilities.bold());
            this.rtlTextView.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
            this.button.addView(this.rtlTextView, LayoutHelper.createFrame(-2, -1, 17));
        L5:
            AnimatedTextView.AnimatedTextDrawable r6 = new AnimatedTextView.AnimatedTextDrawable(true, true, true);
            this.textView = r6;
            CubicBezierInterpolator r12 = CubicBezierInterpolator.EASE_OUT_QUINT;
            r6.setAnimationProperties(0.25f, 0, 300, r12);
            this.textView.setCallback(this.button);
            this.textView.setTextSize(AndroidUtilities.dp(14.0f));
            this.textView.setText(LocaleController.getString(R.string.ClearCache));
            this.textView.setGravity(5);
            this.textView.setTypeface(AndroidUtilities.bold());
            AnimatedTextView.AnimatedTextDrawable r152 = this.textView;
            int r03 = Theme.key_featuredStickers_buttonText;
            r152.setTextColor(Theme.getColor(r03));
            AnimatedTextView.AnimatedTextDrawable r7 = new AnimatedTextView.AnimatedTextDrawable(true, true, true);
            this.valueTextView = r7;
            r7.setAnimationProperties(0.25f, 0, 300, r12);
            this.valueTextView.setCallback(this.button);
            this.valueTextView.setTextSize(AndroidUtilities.dp(14.0f));
            this.valueTextView.setTypeface(AndroidUtilities.bold());
            this.valueTextView.setTextColor(Theme.blendOver(Theme.getColor(r1), Theme.multAlpha(Theme.getColor(r03), 0.7f)));
            this.valueTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.button.setContentDescription(TextUtils.concat(new CharSequence[]{this.textView.getText(), "\t", this.valueTextView.getText()}));
            addView(this.button, LayoutHelper.createFrame(-1, 48.0f, 119, 16.0f, 16.0f, 16.0f, 16.0f));
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }

        public void setDisabled(boolean r3) {
            this.button.animate().cancel();
            ViewPropertyAnimator r0 = this.button.animate();
            if (r3 == false) goto L5;
            float r1 = 0.65f;
        L6:
            r0.alpha(r1).start();
            this.button.setClickable(!r3);
            return;
        L5:
            r1 = 1.0f;
            goto L6
        }

        public void setSize(boolean r3, long r4) {
            AnimatedTextView.AnimatedTextDrawable r0 = this.textView;
            if (r3 == false) goto L5;
            String r32 = LocaleController.getString(R.string.ClearCache);
        L6:
            r0.setText(r32);
            AnimatedTextView.AnimatedTextDrawable r33 = this.valueTextView;
            if (r4 > 0) goto L9;
            String r42 = _UrlKt.FRAGMENT_ENCODE_SET;
        L10:
            r33.setText(r42);
            if (r4 > 0) goto L13;
            boolean r5 = true;
        L14:
            setDisabled(r5);
            this.button.invalidate();
            this.button.setContentDescription(TextUtils.concat(new CharSequence[]{this.textView.getText(), "\t", this.valueTextView.getText()}));
            return;
        L13:
            r5 = false;
            goto L14
        L9:
            r42 = AndroidUtilities.formatFileSize(r4);
            goto L10
        L5:
            r32 = LocaleController.getString(R.string.ClearSelectedCache);
            goto L6
        }
    }

    /* loaded from: classes6.dex */
    public class ClearCacheButtonInternal extends ClearCacheButton {
        final /* synthetic */ CacheControlActivity this$0;

        /* renamed from: org.telegram.ui.CacheControlActivity$ClearCacheButtonInternal$1, reason: invalid class name */
        public class AnonymousClass1 extends BottomSheet {
            final /* synthetic */ ClearCacheButtonInternal this$1;

            public AnonymousClass1(ClearCacheButtonInternal r1, Context r2, boolean r3) {
                this.this$1 = r1;
                super(r2, r3);
            }

            @Override // org.telegram.ui.ActionBar.BottomSheet
            public boolean canDismissWithTouchOutside() {
                return false;
            }
        }

        /* renamed from: $r8$lambda$Q2qiZJnTaE1r-t28iBHcxpgjtYg, reason: not valid java name */
        public static /* synthetic */ void m6469$r8$lambda$Q2qiZJnTaE1rt28iBHcxpgjtYg(ClearCacheButtonInternal r0, View r1) {
            r0.lambda$new$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$UDpypPP37Y0E3UtNqXWab46cjrU(boolean[] r6, ClearingCacheView r7, long[] r8, BottomSheet r9) {
            r6[0] = true;
            r7.setProgress(1.0f);
            if (r8[0] <= 0) goto L6;
            Objects.requireNonNull(r9);
            AndroidUtilities.runOnUIThread(new BottomSheet$$ExternalSyntheticLambda7(r9), Math.max(0, 1000 - (System.currentTimeMillis() - r8[0])));
            return;
        L6:
            r9.dismiss();
        }

        public static /* synthetic */ void $r8$lambda$Wq5VCzl9dill7kIUgZx1oEPBBJo(boolean[] r1, ClearingCacheView r2, long[] r3, BottomSheet r4) {
            AndroidUtilities.runOnUIThread(new CacheControlActivity$ClearCacheButtonInternal$$ExternalSyntheticLambda6(r1, r2, r3, r4));
        }

        public static /* synthetic */ void $r8$lambda$a5_97Qauo7OxuDNZNYJJBiLWacg(ClearCacheButtonInternal r0, AlertDialog r1, int r2) {
            r0.lambda$new$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$jiEY1mweDAysKlRZVBoQ2OpeaxU(float[] r1, boolean[] r2, Runnable r3, Float r4, Boolean r5) {
            r1[0] = r4.floatValue();
            r2[0] = r5.booleanValue();
            AndroidUtilities.cancelRunOnUIThread(r3);
            AndroidUtilities.runOnUIThread(r3);
        }

        /* renamed from: $r8$lambda$lE6E-1c88fKccJusyoSWEQ7BiaY, reason: not valid java name */
        public static /* synthetic */ void m6470$r8$lambda$lE6E1c88fKccJusyoSWEQ7BiaY(ClearCacheButtonInternal r0, ClearingCacheView r1, float[] r2, boolean[] r3) {
            r0.lambda$doClearCache$2(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$tCRNsSAkL6wUQY-gkXA4JGbgVco, reason: not valid java name */
        public static /* synthetic */ void m6471$r8$lambda$tCRNsSAkL6wUQYgkXA4JGbgVco(ClearCacheButtonInternal r0, boolean[] r1, long[] r2, BottomSheet r3) {
            r0.lambda$doClearCache$3(r1, r2, r3);
        }

        public ClearCacheButtonInternal(CacheControlActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            ViewGroup.MarginLayoutParams r12 = (ViewGroup.MarginLayoutParams) this.button.getLayoutParams();
            r12.topMargin = AndroidUtilities.dp(5.0f);
            this.button.setOnClickListener(new CacheControlActivity$ClearCacheButtonInternal$$ExternalSyntheticLambda0(this));
        }

        private void doClearCache() {
            AnonymousClass1 r0 = new AnonymousClass1(this, getContext(), false);
            r0.fixNavigationBar();
            r0.setCanDismissWithSwipe(false);
            r0.setCancelable(false);
            ClearingCacheView r1 = new ClearingCacheView(this.this$0, getContext());
            r0.setCustomView(r1);
            boolean[] r4 = {false};
            float[] r5 = {0.0f};
            boolean[] r6 = {false};
            CacheControlActivity$ClearCacheButtonInternal$$ExternalSyntheticLambda2 r7 = new CacheControlActivity$ClearCacheButtonInternal$$ExternalSyntheticLambda2(this, r1, r5, r6);
            long[] r3 = {-1};
            AndroidUtilities.runOnUIThread(new CacheControlActivity$ClearCacheButtonInternal$$ExternalSyntheticLambda3(this, r4, r3, r0), 150);
            CacheControlActivity.m6455$$Nest$mcleanupFolders(this.this$0, new CacheControlActivity$ClearCacheButtonInternal$$ExternalSyntheticLambda4(r5, r6, r7), new CacheControlActivity$ClearCacheButtonInternal$$ExternalSyntheticLambda5(r4, r1, r3, r0));
        }

        private /* synthetic */ void lambda$doClearCache$2(ClearingCacheView r2, float[] r3, boolean[] r4) {
            r2.setProgress(r3[0]);
            if (r4[0] == false) goto L6;
            CacheControlActivity.m6467$$Nest$mupdateRows(this.this$0);
            return;
        }

        private /* synthetic */ void lambda$doClearCache$3(boolean[] r4, long[] r5, BottomSheet r6) {
            if (r4[0] == true) goto L6;
            r5[0] = System.currentTimeMillis();
            this.this$0.showDialog(r6);
            return;
        }

        private /* synthetic */ void lambda$new$0(AlertDialog r1, int r2) {
            doClearCache();
        }

        private /* synthetic */ void lambda$new$1(View r4) {
            AlertDialog.Builder r42 = new AlertDialog.Builder(getContext());
            StringBuilder r0 = new StringBuilder();
            r0.append(LocaleController.getString(R.string.ClearCache));
            if (TextUtils.isEmpty(this.valueTextView.getText()) == false) goto L5;
            String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        L6:
            r0.append(r1);
            AlertDialog r43 = r42.setTitle(r0.toString()).setMessage(LocaleController.getString(R.string.StorageUsageInfo)).setPositiveButton(this.textView.getText(), new CacheControlActivity$ClearCacheButtonInternal$$ExternalSyntheticLambda1(this)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).create();
            this.this$0.showDialog(r43);
            View r3 = r43.getButton(-1);
            if ((r3 instanceof TextView) == false) goto L10;
            int r02 = Theme.key_text_RedRegular;
            ((TextView) r3).setTextColor(Theme.getColor(r02));
            r3.setBackground(Theme.getRoundRectSelectorDrawable(AndroidUtilities.dp(6.0f), Theme.multAlpha(Theme.getColor(r02), 0.12f)));
            return;
        L10:
            return;
        L5:
            r1 = " (" + this.valueTextView.getText() + ")";
            goto L6
        }

        public void updateSize() {
            long r1 = 0;
            if (CacheControlActivity.m6441$$Nest$fgetselected(this.this$0)[0] == false) goto L5;
            long r3 = CacheControlActivity.m6440$$Nest$fgetphotoSize(this.this$0);
        L7:
            if (CacheControlActivity.m6441$$Nest$fgetselected(this.this$0)[1] == false) goto L9;
            long r5 = CacheControlActivity.m6447$$Nest$fgetvideoSize(this.this$0);
        L10:
            long r32 = r3 + r5;
            if (CacheControlActivity.m6441$$Nest$fgetselected(this.this$0)[2] == false) goto L13;
            long r52 = CacheControlActivity.m6431$$Nest$fgetdocumentsSize(this.this$0);
        L14:
            long r33 = r32 + r52;
            if (CacheControlActivity.m6441$$Nest$fgetselected(this.this$0)[3] == false) goto L17;
            long r53 = CacheControlActivity.m6437$$Nest$fgetmusicSize(this.this$0);
        L18:
            long r34 = r33 + r53;
            if (CacheControlActivity.m6441$$Nest$fgetselected(this.this$0)[4] == false) goto L21;
            long r54 = CacheControlActivity.m6421$$Nest$fgetaudioSize(this.this$0);
        L22:
            long r35 = r34 + r54;
            if (CacheControlActivity.m6441$$Nest$fgetselected(this.this$0)[5] == false) goto L25;
            long r55 = CacheControlActivity.m6443$$Nest$fgetstoriesSize(this.this$0);
        L26:
            long r36 = r35 + r55;
            if (CacheControlActivity.m6441$$Nest$fgetselected(this.this$0)[6] == false) goto L29;
            long r56 = CacheControlActivity.m6442$$Nest$fgetstickersCacheSize(this.this$0);
        L30:
            long r37 = r36 + r56;
            if (CacheControlActivity.m6441$$Nest$fgetselected(this.this$0)[7] == false) goto L33;
            long r57 = CacheControlActivity.m6425$$Nest$fgetcacheSize(this.this$0);
        L34:
            long r38 = r37 + r57;
            if (CacheControlActivity.m6441$$Nest$fgetselected(this.this$0)[8] == false) goto L37;
            long r58 = CacheControlActivity.m6426$$Nest$fgetcacheTempSize(this.this$0);
        L38:
            long r39 = r38 + r58;
            if (CacheControlActivity.m6441$$Nest$fgetselected(this.this$0)[9] == false) goto L41;
            r1 = CacheControlActivity.m6435$$Nest$fgetlogsSize(this.this$0);
        L41:
            setSize(CacheControlActivity.m6460$$Nest$misAllSectionsSelected(this.this$0), r39 + r1);
            return;
        L37:
            r58 = 0;
            goto L38
        L33:
            r57 = 0;
            goto L34
        L29:
            r56 = 0;
            goto L30
        L25:
            r55 = 0;
            goto L26
        L21:
            r54 = 0;
            goto L22
        L17:
            r53 = 0;
            goto L18
        L13:
            r52 = 0;
            goto L14
        L9:
            r5 = 0;
            goto L10
        L5:
            r3 = 0;
            goto L7
        }
    }

    /* loaded from: classes6.dex */
    public class ClearingCacheView extends FrameLayout {
        RLottieImageView imageView;
        AnimatedTextView percentsTextView;
        ProgressView progressView;
        TextView subtitle;
        final /* synthetic */ CacheControlActivity this$0;
        TextView title;

        public class ProgressView extends View {
            Paint in;
            Paint out;
            float progress;
            AnimatedFloat progressT;
            final /* synthetic */ ClearingCacheView this$1;

            public ProgressView(ClearingCacheView r3, Context r4) {
                this.this$1 = r3;
                super(r4);
                this.in = new Paint(1);
                this.out = new Paint(1);
                this.progressT = new AnimatedFloat(this, 350, CubicBezierInterpolator.EASE_OUT);
                Paint r32 = this.in;
                int r42 = Theme.key_switchTrackChecked;
                r32.setColor(Theme.getColor(r42));
                this.out.setColor(Theme.multAlpha(Theme.getColor(r42), 0.2f));
            }

            @Override // android.view.View
            public void onDraw(Canvas r7) {
                super.onDraw(r7);
                RectF r0 = AndroidUtilities.rectTmp;
                r0.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
                r7.drawRoundRect(r0, AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), this.out);
                r0.set(0.0f, 0.0f, getMeasuredWidth() * this.progressT.set(this.progress), getMeasuredHeight());
                r7.drawRoundRect(r0, AndroidUtilities.dp(3.0f), AndroidUtilities.dp(3.0f), this.in);
            }

            public void setProgress(float r1) {
                this.progress = r1;
                invalidate();
            }
        }

        public ClearingCacheView(CacheControlActivity r10, Context r11) {
            this.this$0 = r10;
            super(r11);
            RLottieImageView r102 = new RLottieImageView(r11);
            this.imageView = r102;
            r102.setAutoRepeat(true);
            this.imageView.setAnimation(R.raw.utyan_cache, 150, 150);
            addView(this.imageView, LayoutHelper.createFrame(150, 150.0f, 49, 0.0f, 16.0f, 0.0f, 0.0f));
            this.imageView.playAnimation();
            AnimatedTextView r1 = new AnimatedTextView(r11, false, true, true);
            this.percentsTextView = r1;
            r1.setAnimationProperties(0.35f, 0, 120, CubicBezierInterpolator.EASE_OUT);
            this.percentsTextView.setGravity(1);
            AnimatedTextView r103 = this.percentsTextView;
            int r12 = Theme.key_dialogTextBlack;
            r103.setTextColor(Theme.getColor(r12));
            this.percentsTextView.setTextSize(AndroidUtilities.dp(24.0f));
            this.percentsTextView.setTypeface(AndroidUtilities.bold());
            addView(this.percentsTextView, LayoutHelper.createFrame(-1, 32.0f, 49, 0.0f, 176.0f, 0.0f, 0.0f));
            ProgressView r104 = new ProgressView(this, r11);
            this.progressView = r104;
            addView(r104, LayoutHelper.createFrame(240, 5.0f, 49, 0.0f, 226.0f, 0.0f, 0.0f));
            TextView r105 = new TextView(r11);
            this.title = r105;
            r105.setGravity(1);
            this.title.setTextColor(Theme.getColor(r12));
            this.title.setTextSize(1, 16.0f);
            this.title.setTypeface(AndroidUtilities.bold());
            this.title.setText(LocaleController.getString(R.string.ClearingCache));
            addView(this.title, LayoutHelper.createFrame(-1, -2.0f, 49, 0.0f, 261.0f, 0.0f, 0.0f));
            TextView r106 = new TextView(r11);
            this.subtitle = r106;
            r106.setGravity(1);
            this.subtitle.setTextColor(Theme.getColor(r12));
            this.subtitle.setTextSize(1, 14.0f);
            this.subtitle.setText(LocaleController.getString(R.string.ClearingCacheDescription));
            addView(this.subtitle, LayoutHelper.createFrame(240, -2.0f, 49, 0.0f, 289.0f, 0.0f, 0.0f));
            setProgress(0.0f);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(350.0f), TLObject.FLAG_30));
        }

        public void setProgress(float r4) {
            this.percentsTextView.cancelAnimation();
            this.percentsTextView.setText(String.format("%d%%", new Object[]{Integer.valueOf((int) Math.ceil(MathUtils.clamp(r4, 0.0f, 1.0f) * 100.0f))}), !LocaleController.isRTL);
            this.progressView.setProgress(r4);
        }
    }

    /* loaded from: classes6.dex */
    public static class DialogFileEntities {
        public long dialogId;
        public final SparseArray<FileEntities> entitiesByType;
        int filesCount;
        long totalSize;

        public DialogFileEntities(long r2) {
            this.entitiesByType = new SparseArray();
            this.dialogId = r2;
        }

        public void addFile(CacheModel.FileInfo r6, int r7) {
            FileEntities r0 = this.entitiesByType.get(r7, null);
            if (r0 != null) goto L5;
            r0 = new FileEntities();
            this.entitiesByType.put(r7, r0);
        L5:
            r0.count++;
            long r1 = r6.size;
            r0.totalSize += r1;
            this.totalSize += r1;
            this.filesCount++;
            r0.files.add(r6);
        }

        public CacheModel createCacheModel() {
            CacheModel r0 = new CacheModel(true);
            if (this.entitiesByType.get(0) == null) goto L6;
            r0.media.addAll(this.entitiesByType.get(0).files);
        L6:
            if (this.entitiesByType.get(1) == null) goto L9;
            r0.media.addAll(this.entitiesByType.get(1).files);
        L9:
            if (this.entitiesByType.get(2) == null) goto L12;
            r0.documents.addAll(this.entitiesByType.get(2).files);
        L12:
            if (this.entitiesByType.get(3) == null) goto L15;
            r0.music.addAll(this.entitiesByType.get(3).files);
        L15:
            if (this.entitiesByType.get(4) == null) goto L17;
            r0.voice.addAll(this.entitiesByType.get(4).files);
        L17:
            r0.selectAllFiles();
            r0.sortBySize();
            return r0;
        }

        public boolean isEmpty() {
            if (this.totalSize > 0) goto L6;
            return true;
        L6:
            return false;
        }

        public void merge(DialogFileEntities r9) {
            int r0 = 0;
        L4:
            if (r0 >= r9.entitiesByType.size()) goto L9;
            int r1 = r9.entitiesByType.keyAt(r0);
            FileEntities r2 = r9.entitiesByType.valueAt(r0);
            FileEntities r3 = this.entitiesByType.get(r1, null);
            if (r3 != null) goto L8;
            r3 = new FileEntities();
            this.entitiesByType.put(r1, r3);
        L8:
            r3.count += r2.count;
            r3.totalSize += r2.totalSize;
            this.totalSize += r2.totalSize;
            r3.files.addAll(r2.files);
            r0 = r0 + 1;
            goto L4
        L9:
            this.filesCount += r9.filesCount;
        }

        public void removeFile(CacheModel.FileInfo r6) {
            FileEntities r0 = this.entitiesByType.get(r6.type, null);
            if (r0 != null) goto L6;
            return;
        L6:
            if (r0.files.remove(r6) == false) goto L9;
            r0.count--;
            long r1 = r0.totalSize;
            long r3 = r6.size;
            r0.totalSize = r1 - r3;
            this.totalSize -= r3;
            this.filesCount--;
            return;
        }
    }

    /* loaded from: classes6.dex */
    public static class FileEntities {
        public int count;
        public ArrayList<CacheModel.FileInfo> files;
        public long totalSize;

        public FileEntities() {
            this.files = new ArrayList();
        }
    }

    /* loaded from: classes6.dex */
    public static class ItemInner extends AdapterWithDiffUtils.Item {
        int colorKey;
        DialogFileEntities entities;
        int headerBottomMargin;
        CharSequence headerName;
        int headerTopMargin;
        public int index;
        int keepMediaType;
        boolean last;
        public boolean pad;
        public long size;
        String text;

        private ItemInner(int r2) {
            super(r2, true);
            this.headerTopMargin = 15;
            this.headerBottomMargin = 0;
            this.keepMediaType = -1;
        }

        public ItemInner(int r2, int r3) {
            super(r2, true);
            this.headerTopMargin = 15;
            this.headerBottomMargin = 0;
            this.keepMediaType = r3;
        }

        public ItemInner(int r2, String r3, DialogFileEntities r4) {
            super(r2, true);
            this.headerTopMargin = 15;
            this.headerBottomMargin = 0;
            this.keepMediaType = -1;
            this.headerName = r3;
            this.entities = r4;
        }

        public /* synthetic */ ItemInner(int r1, CacheControlActivityIA r2) {
            this(r1);
        }

        public static ItemInner asCheckBox(CharSequence r6, int r7, long r8, int r10) {
            return asCheckBox(r6, r7, r8, r10, false);
        }

        public static ItemInner asCheckBox(CharSequence r2, int r3, long r4, int r6, boolean r7) {
            ItemInner r0 = new ItemInner(11);
            r0.index = r3;
            r0.headerName = r2;
            r0.size = r4;
            r0.colorKey = r6;
            r0.last = r7;
            return r0;
        }

        public static ItemInner asInfo(String r2) {
            ItemInner r0 = new ItemInner(1);
            r0.text = r2;
            return r0;
        }

        public boolean equals(Object r6) {
            if (this != r6) goto L6;
            return true;
        L6:
            if (r6 != null) goto L8;
        L59:
            return false;
        L8:
            if (getClass() != r6.getClass()) goto L59;
            ItemInner r62 = (ItemInner) r6;
            int r2 = this.viewType;
            if (r2 != r62.viewType) goto L59;
            if (r2 != 9) goto L15;
        L58:
            return true;
        L15:
            if (r2 == 10) goto L58;
            if (r2 != 5) goto L28;
            DialogFileEntities r3 = this.entities;
            if (r3 == null) goto L28;
            DialogFileEntities r4 = r62.entities;
            if (r4 == null) goto L28;
            if (r3.dialogId != r4.dialogId) goto L26;
            return true;
        L26:
            return false;
        L28:
            if (r2 == 8) goto L58;
            if (r2 == 4) goto L58;
            if (r2 == 2) goto L58;
            if (r2 == 0) goto L58;
            if (r2 == 13) goto L58;
            if (r2 == 3) goto L40;
            if (r2 != 1) goto L45;
            return Objects.equals(this.text, r62.text);
        L45:
            if (r2 != 11) goto L53;
            if (this.index == r62.index) goto L49;
        L51:
            return false;
        L49:
            if (this.size != r62.size) goto L51;
            return true;
        L53:
            if (r2 == 7) goto L55;
        L57:
            return false;
        L55:
            if (this.keepMediaType != r62.keepMediaType) goto L57;
            return true;
        L40:
            return Objects.equals(this.headerName, r62.headerName);
        }
    }

    /* loaded from: classes6.dex */
    public class ListAdapter extends AdapterWithDiffUtils {
        private Context mContext;
        final /* synthetic */ CacheControlActivity this$0;

        /* renamed from: org.telegram.ui.CacheControlActivity$ListAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends CacheChart {
            final /* synthetic */ ListAdapter this$1;

            public static /* synthetic */ int $r8$lambda$iMXUJM708KRukDjxcnPvpwfmnk0(int r0) {
                return r0;
            }

            public AnonymousClass1(ListAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Components.CacheChart
            public void onSectionClick(int r1) {
            }

            @Override // org.telegram.ui.Components.CacheChart
            public void onSectionDown(int r6, boolean r7) {
                if (r7 == true) goto L5;
                CacheControlActivity.m6434$$Nest$fgetlistView(this.this$1.this$0).removeHighlightRow();
                return;
            L5:
                int r0 = -1;
                if (r6 != 8) goto L8;
                r6 = -1;
            L8:
                int r1 = 0;
            L10:
                if (r1 >= CacheControlActivity.m6432$$Nest$fgetitemInners(this.this$1.this$0).size()) goto L19;
                ItemInner r2 = (ItemInner) CacheControlActivity.m6432$$Nest$fgetitemInners(this.this$1.this$0).get(r1);
                if (r2 == null) goto L18;
                if (r2.viewType != 11) goto L18;
                if (r2.index != r6) goto L18;
                r0 = r1;
            L18:
                r1 = r1 + 1;
            L19:
                ListAdapter r5 = this.this$1;
                if (r0 < 0) goto L23;
                CacheControlActivity.m6434$$Nest$fgetlistView(r5.this$0).highlightRow(new CacheControlActivity$ListAdapter$1$$ExternalSyntheticLambda0(r0), 0);
                return;
            L23:
                CacheControlActivity.m6434$$Nest$fgetlistView(r5.this$0).removeHighlightRow();
            }
        }

        /* renamed from: org.telegram.ui.CacheControlActivity$ListAdapter$2, reason: invalid class name */
        public class AnonymousClass2 extends CachedMediaLayout {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass2(ListAdapter r1, Context r2, BaseFragment r3) {
                this.this$1 = r1;
                super(r2, r3);
            }

            @Override // org.telegram.ui.CachedMediaLayout, android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec((View.MeasureSpec.getSize(r3) - (ActionBar.getCurrentActionBarHeight() / 2)) - AndroidUtilities.dp(12.0f), TLObject.FLAG_30));
            }

            @Override // org.telegram.ui.CachedMediaLayout
            public void showActionMode(boolean r2) {
                ListAdapter r0 = this.this$1;
                if (r2 == false) goto L6;
                CacheControlActivity.m6464$$Nest$mupdateActionBar(r0.this$0, true);
                CacheControlActivity.access$400(this.this$1.this$0).showActionMode();
                return;
            L6:
                CacheControlActivity.access$500(r0.this$0).hideActionMode();
            }
        }

        /* renamed from: org.telegram.ui.CacheControlActivity$ListAdapter$3, reason: invalid class name */
        public class AnonymousClass3 implements CachedMediaLayout.Delegate {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass3(ListAdapter r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.ui.CachedMediaLayout.Delegate
            public void clear() {
                CacheControlActivity.m6457$$Nest$mclearSelectedFiles(this.this$1.this$0);
            }

            @Override // org.telegram.ui.CachedMediaLayout.Delegate
            public void clearSelection() {
                CacheModel r0 = this.this$1.this$0.cacheModel;
                if (r0 != null) goto L5;
                return;
            L5:
                if (r0.getSelectedFiles() <= 0) goto L11;
                this.this$1.this$0.cacheModel.clearSelection();
                if (CacheControlActivity.m6427$$Nest$fgetcachedMediaLayout(this.this$1.this$0) == null) goto L12;
                CacheControlActivity.m6427$$Nest$fgetcachedMediaLayout(this.this$1.this$0).showActionMode(false);
                CacheControlActivity.m6427$$Nest$fgetcachedMediaLayout(this.this$1.this$0).updateVisibleRows();
                return;
            L12:
                return;
            }

            @Override // org.telegram.ui.CachedMediaLayout.Delegate
            public void onItemSelected(DialogFileEntities r1, CacheModel.FileInfo r2, boolean r3) {
                if (r1 != null) goto L4;
                if (r2 == null) goto L14;
                this.this$1.this$0.cacheModel.toggleSelect(r2);
                CacheControlActivity.m6427$$Nest$fgetcachedMediaLayout(this.this$1.this$0).updateVisibleRows();
                CacheControlActivity.m6465$$Nest$mupdateActionMode(this.this$1.this$0);
                return;
            L14:
                return;
            L4:
                if (this.this$1.this$0.cacheModel.getSelectedFiles() > 0) goto L9;
                if (r3 == true) goto L9;
                CacheControlActivity.m6462$$Nest$mshowClearCacheDialog(this.this$1.this$0, r1);
                return;
            L9:
                this.this$1.this$0.cacheModel.toggleSelect(r1);
                CacheControlActivity.m6427$$Nest$fgetcachedMediaLayout(this.this$1.this$0).updateVisibleRows();
                CacheControlActivity.m6465$$Nest$mupdateActionMode(this.this$1.this$0);
            }
        }

        public static /* synthetic */ void $r8$lambda$4SsvlYEjOKdNTKghAfeVzWEXVUI(ListAdapter r0, CheckBoxCell r1, View r2) {
            r0.lambda$onBindViewHolder$3(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$Erwz0ZPdBvlYN4mLMjR8yJofczM(ListAdapter r0, View r1) {
            r0.lambda$onBindViewHolder$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$MneyMouiRs9T1ldLrg7wDq6TCTc(int r4) {
            if (r4 != 0) goto L7;
            SharedConfig.setKeepMedia(4);
            return;
        L7:
            if (r4 != 1) goto L11;
            SharedConfig.setKeepMedia(3);
            return;
        L11:
            if (r4 != 2) goto L14;
            SharedConfig.setKeepMedia(0);
            return;
        L14:
            if (r4 != 3) goto L17;
            SharedConfig.setKeepMedia(1);
            return;
        L17:
            if (r4 != 4) goto L20;
            SharedConfig.setKeepMedia(2);
            return;
        }

        public static /* synthetic */ void $r8$lambda$uJ09_w0dSreeYuysTksjPAI4Ng0(ArrayList r1, int r2) {
            SharedConfig.getPreferences().edit().putInt("cache_limit", ((Integer) r1.get(r2)).intValue()).apply();
        }

        public ListAdapter(CacheControlActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        private /* synthetic */ void lambda$onBindViewHolder$2(View r2) {
            CacheControlActivity.m6453$$Nest$fputcollapsed(this.this$0, !CacheControlActivity.m6429$$Nest$fgetcollapsed(r2));
            CacheControlActivity.m6467$$Nest$mupdateRows(this.this$0);
            CacheControlActivity.m6466$$Nest$mupdateChart(this.this$0);
        }

        private /* synthetic */ void lambda$onBindViewHolder$3(CheckBoxCell r1, View r2) {
            CacheControlActivity.m6463$$Nest$mtoggleOtherSelected(this.this$0, r1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return CacheControlActivity.m6432$$Nest$fgetitemInners(this.this$0).size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return ((ItemInner) CacheControlActivity.m6432$$Nest$fgetitemInners(this.this$0).get(r1)).viewType;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r5) {
            if (r5.getAdapterPosition() != CacheControlActivity.m6436$$Nest$fgetmigrateOldFolderRow(this.this$0)) goto L5;
            return true;
        L5:
            if (r5.getItemViewType() != 2) goto L11;
            if (CacheControlActivity.m6446$$Nest$fgettotalSize(this.this$0) <= 0) goto L11;
            if (CacheControlActivity.m6428$$Nest$fgetcalculating(this.this$0) == true) goto L11;
            return true;
        L11:
            if (r5.getItemViewType() != 5) goto L13;
            return true;
        L13:
            if (r5.getItemViewType() != 7) goto L15;
            return true;
        L15:
            if (r5.getItemViewType() == 11) goto L24;
            return false;
        L24:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r19, int r20) {
            ItemInner r3 = (ItemInner) CacheControlActivity.m6432$$Nest$fgetitemInners(this.this$0).get(r20);
            int r4 = r19.getItemViewType();
            String r5 = null;
            boolean r6 = false;
            if (r4 != 0) goto L5;
            TextSettingsCell r1 = (TextSettingsCell) r19.itemView;
            if (r20 != CacheControlActivity.m6436$$Nest$fgetmigrateOldFolderRow(this.this$0)) goto L94;
            r1.setTextAndValue(LocaleController.getString(R.string.MigrateOldFolder), null, false);
            return;
        L94:
            return;
        L5:
            if (r4 != 1) goto L7;
            ((TextInfoPrivacyCell) r19.itemView).setText(AndroidUtilities.replaceTags(r3.text));
            return;
        L7:
            if (r4 != 2) goto L9;
            ((StorageUsageView) r19.itemView).setStorageUsage(CacheControlActivity.m6428$$Nest$fgetcalculating(this.this$0), CacheControlActivity.m6430$$Nest$fgetdatabaseSize(this.this$0), CacheControlActivity.m6446$$Nest$fgettotalSize(this.this$0), CacheControlActivity.m6444$$Nest$fgettotalDeviceFreeSize(this.this$0), CacheControlActivity.m6445$$Nest$fgettotalDeviceSize(this.this$0));
            return;
        L9:
            if (r4 != 3) goto L11;
            HeaderCell r12 = (HeaderCell) r19.itemView;
            r12.setText(((ItemInner) CacheControlActivity.m6432$$Nest$fgetitemInners(this.this$0).get(r20)).headerName);
            r12.setTopMargin(((ItemInner) CacheControlActivity.m6432$$Nest$fgetitemInners(this.this$0).get(r20)).headerTopMargin);
            r12.setBottomMargin(((ItemInner) CacheControlActivity.m6432$$Nest$fgetitemInners(this.this$0).get(r20)).headerBottomMargin);
            return;
        L11:
            if (r4 == 7) goto L65;
            switch(r4) {
                case 9: goto L63;
                case 10: goto L45;
                case 11: goto L14;
                default: goto L95;
            };
        L14:
            CheckBoxCell r13 = (CheckBoxCell) r19.itemView;
            int r2 = r3.index;
            CacheControlActivity r42 = this.this$0;
            if (r2 >= 0) goto L17;
            boolean r22 = CacheControlActivity.m6461$$Nest$misOtherSelected(r42);
        L18:
            CacheControlActivity r43 = this.this$0;
            CharSequence r8 = r3.headerName;
            int[] r9 = CacheControlActivity.m6439$$Nest$fgetpercents(r43);
            int r10 = r3.index;
            if (r10 >= 0) goto L21;
            int r11 = 9;
        L22:
            int r92 = r9[r11];
            if (r10 >= 0) goto L25;
            boolean r102 = true;
        L26:
            CharSequence r44 = CacheControlActivity.m6459$$Nest$mgetCheckBoxTitle(r43, r8, r92, r102);
            String r82 = AndroidUtilities.formatFileSize(r3.size);
            if (r3.index >= 0) goto L32;
            if (CacheControlActivity.m6429$$Nest$fgetcollapsed(this.this$0) == true) goto L34;
        L30:
            r6 = true;
        L34:
            r13.setText(r44, r82, r22, r6);
            r13.setCheckBoxColor(r3.colorKey, Theme.key_windowBackgroundWhiteGrayIcon, Theme.key_checkboxCheck);
            if (r3.index >= 0) goto L37;
            Boolean r23 = Boolean.valueOf(CacheControlActivity.m6429$$Nest$fgetcollapsed(this.this$0));
        L38:
            r13.setCollapsed(r23);
            if (r3.index != (-1)) goto L41;
            r13.setOnSectionsClickListener(new CacheControlActivity$ListAdapter$$ExternalSyntheticLambda0(this), new CacheControlActivity$ListAdapter$$ExternalSyntheticLambda1(this, r13));
        L42:
            r13.setPad(r3.pad ? 1 : 0);
            return;
        L41:
            r13.setOnSectionsClickListener(null, null);
            goto L42
        L37:
            r23 = null;
            goto L38
        L32:
            if (r3.last == true) goto L34;
        L25:
            r102 = false;
            goto L26
        L21:
            r11 = r10;
            goto L22
        L17:
            r22 = CacheControlActivity.m6441$$Nest$fgetselected(r42)[r3.index];
            goto L18
        L63:
            CacheControlActivity.m6466$$Nest$mupdateChart(this.this$0);
            return;
        L95:
            return;
        L45:
            if (CacheControlActivity.m6424$$Nest$fgetcacheChartHeader(this.this$0) != null) goto L47;
            return;
        L47:
            if (CacheControlActivity.m6428$$Nest$fgetcalculating(this.this$0) == true) goto L93;
            CacheChartHeader r14 = CacheControlActivity.m6424$$Nest$fgetcacheChartHeader(this.this$0);
            if (CacheControlActivity.m6446$$Nest$fgettotalSize(this.this$0) <= 0) goto L51;
            r6 = true;
        L51:
            float r32 = 0.0f;
            if (CacheControlActivity.m6445$$Nest$fgettotalDeviceSize(this.this$0) > 0) goto L54;
            float r24 = 0.0f;
        L56:
            if (CacheControlActivity.m6444$$Nest$fgettotalDeviceFreeSize(this.this$0) > 0) goto L58;
        L61:
            r14.setData(r6, r24, r32);
            return;
        L58:
            if (CacheControlActivity.m6445$$Nest$fgettotalDeviceSize(this.this$0) <= 0) goto L61;
            r32 = (CacheControlActivity.m6445$$Nest$fgettotalDeviceSize(this.this$0) - CacheControlActivity.m6444$$Nest$fgettotalDeviceFreeSize(this.this$0)) / CacheControlActivity.m6445$$Nest$fgettotalDeviceSize(this.this$0);
            goto L61
        L54:
            r24 = CacheControlActivity.m6446$$Nest$fgettotalSize(this.this$0) / CacheControlActivity.m6445$$Nest$fgettotalDeviceSize(this.this$0);
            goto L56
        L93:
            return;
        L65:
            TextCell r103 = (TextCell) r19.itemView;
            CacheByChatsController r15 = this.this$0.getMessagesController().getCacheByChatsController();
            int r33 = r3.keepMediaType;
            int r45 = r15.getKeepMediaExceptions(((ItemInner) CacheControlActivity.m6432$$Nest$fgetitemInners(this.this$0).get(r20)).keepMediaType).size();
            if (r45 <= 0) goto L68;
            r5 = LocaleController.formatPluralString("ExceptionShort", r45, new Object[]{Integer.valueOf(r45)});
        L68:
            String r122 = CacheByChatsController.getKeepMediaString(r15.getKeepMedia(r33));
            if (((ItemInner) CacheControlActivity.m6432$$Nest$fgetitemInners(this.this$0).get(r20)).keepMediaType != 0) goto L72;
            r103.setTextAndValueAndColorfulIcon(LocaleController.getString(R.string.PrivateChats), r122, true, R.drawable.msg_filled_menu_users, -11565578, -13276952, true);
        L80:
            r103.setSubtitle(r5);
            return;
        L72:
            if (((ItemInner) CacheControlActivity.m6432$$Nest$fgetitemInners(this.this$0).get(r20)).keepMediaType != 1) goto L75;
            r103.setTextAndValueAndColorfulIcon(LocaleController.getString(R.string.GroupChats), r122, true, R.drawable.msg_filled_menu_groups, -11154873, -14175180, true);
            goto L80
        L75:
            if (((ItemInner) CacheControlActivity.m6432$$Nest$fgetitemInners(this.this$0).get(r20)).keepMediaType != 2) goto L78;
            r103.setTextAndValueAndColorfulIcon(LocaleController.getString(R.string.CacheChannels), r122, true, R.drawable.msg_filled_menu_channels, -1007845, -1996271, true);
            goto L80
        L78:
            if (((ItemInner) CacheControlActivity.m6432$$Nest$fgetitemInners(this.this$0).get(r20)).keepMediaType != 3) goto L80;
            r103.setTextAndValueAndColorfulIcon(LocaleController.getString(R.string.CacheStories), r122, false, R.drawable.msg_filled_stories, -765355, -2148011, false);
            goto L80
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v10, types: [android.view.View, org.telegram.ui.CacheControlActivity$ListAdapter$1, org.telegram.ui.Components.CacheChart] */
        /* JADX WARN: Type inference failed for: r10v21, types: [org.telegram.ui.Components.FlickerLoadingView] */
        /* JADX WARN: Type inference failed for: r10v9, types: [org.telegram.ui.Components.FlickerLoadingView] */
        /* JADX WARN: Type inference failed for: r11v11, types: [org.telegram.ui.CacheControlActivity$ClearCacheButtonInternal] */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r10, int r11) {
            if (r11 != 0) goto L5;
            FrameLayout r102 = new TextSettingsCell(this.mContext);
        L56:
            return new RecyclerListView.Holder(r102);
        L5:
            switch(r11) {
                case 2: goto L53;
                case 3: goto L52;
                case 4: goto L45;
                case 5: goto L44;
                case 6: goto L43;
                case 7: goto L42;
                case 8: goto L41;
                case 9: goto L40;
                case 10: goto L38;
                case 11: goto L37;
                case 12: goto L36;
                case 13: goto L34;
                case 14: goto L7;
                default: goto L6;
            };
        L6:
            r102 = new TextInfoPrivacyCell(this.mContext);
            goto L56
        L7:
            SlideChooseView r103 = new SlideChooseView(this.mContext);
            float r9 = ((int) ((CacheControlActivity.m6445$$Nest$fgettotalDeviceSize(this.this$0) / 1024) / 1024)) / 1000.0f;
            ArrayList r112 = new ArrayList();
            if (r9 > 17.0f) goto L11;
            r112.add(2);
        L11:
            if (r9 <= 5.0f) goto L14;
            r112.add(5);
        L14:
            if (r9 <= 16.0f) goto L17;
            r112.add(16);
        L17:
            if (r9 <= 32.0f) goto L19;
            r112.add(32);
        L19:
            r112.add(Integer.MAX_VALUE);
            String[] r0 = new String[r112.size()];
            int r1 = 0;
        L21:
            if (r1 >= r112.size()) goto L30;
            if (((Integer) r112.get(r1)).intValue() != 1) goto L26;
            r0[r1] = String.format("300 MB", new Object[0]);
        L29:
            r1 = r1 + 1;
            goto L21
        L26:
            if (((Integer) r112.get(r1)).intValue() != Integer.MAX_VALUE) goto L28;
            r0[r1] = LocaleController.getString(R.string.NoLimit);
            goto L29
        L28:
            r0[r1] = String.format("%d GB", new Object[]{r112.get(r1)});
            goto L29
        L30:
            r103.setCallback(new CacheControlActivity$ListAdapter$$ExternalSyntheticLambda3(r112));
            int r92 = r112.indexOf(Integer.valueOf(SharedConfig.getPreferences().getInt("cache_limit", Integer.MAX_VALUE)));
            if (r92 >= 0) goto L33;
            r92 = r112.size() - 1;
        L33:
            r103.setOptions(r92, r0);
            r102 = r103;
            goto L56
        L34:
            CacheControlActivity r104 = this.this$0;
            ?? r113 = new ClearCacheButtonInternal(this.this$0, this.mContext);
            CacheControlActivity.m6452$$Nest$fputclearCacheButton(r104, r113);
            AnonymousClass2 r114 = r113;
        L35:
            r102 = r114;
            goto L56
        L36:
            ?? r105 = new FlickerLoadingView(this.this$0.getContext());
            r105.setIsSingleCell(true);
            r105.setItemsCount(1);
            r105.setIgnoreHeightCheck(true);
            r105.setViewType(26);
            r102 = r105;
            goto L56
        L37:
            r102 = new CheckBoxCell(this.mContext, 4, 21, this.this$0.getResourceProvider());
            goto L56
        L38:
            CacheControlActivity r115 = this.this$0;
            CacheChartHeader r02 = new CacheChartHeader(this.this$0, this.mContext);
            CacheControlActivity.m6450$$Nest$fputcacheChartHeader(r115, r02);
            r02.setTag(-33024);
            CacheChartHeader r03 = r02;
        L39:
            r102 = r03;
            goto L56
        L40:
            CacheControlActivity r116 = this.this$0;
            ?? r04 = new AnonymousClass1(this, this.mContext);
            CacheControlActivity.m6449$$Nest$fputcacheChart(r116, r04);
            r04.setTag(-33024);
            r03 = r04;
            goto L39
        L41:
            CacheControlActivity r106 = this.this$0;
            AnonymousClass2 r117 = new AnonymousClass2(this, this.mContext, this.this$0);
            CacheControlActivity.m6451$$Nest$fputcachedMediaLayout(r106, r117);
            CacheControlActivity.m6427$$Nest$fgetcachedMediaLayout(this.this$0).setDelegate(new AnonymousClass3(this));
            CacheControlActivity.m6427$$Nest$fgetcachedMediaLayout(this.this$0).setCacheModel(this.this$0.cacheModel);
            CacheControlActivity.m6438$$Nest$fgetnestedSizeNotifierLayout(this.this$0).setChildLayout(CacheControlActivity.m6427$$Nest$fgetcachedMediaLayout(this.this$0), AndroidUtilities.dp(40.0f));
            r117.setLayoutParams(new RecyclerView.LayoutParams(-1, -1));
            r114 = r117;
            goto L35
        L42:
            r102 = new TextCell(this.mContext);
            goto L56
        L43:
            ?? r107 = new FlickerLoadingView(this.this$0.getContext());
            r107.setIsSingleCell(true);
            r107.setItemsCount(3);
            r107.setIgnoreHeightCheck(true);
            r107.setViewType(25);
            r102 = r107;
            goto L56
        L44:
            r102 = new UserCell(this.this$0.getContext(), this.this$0.getResourceProvider());
            goto L56
        L45:
            SlideChooseView r108 = new SlideChooseView(this.mContext);
            r108.setCallback(new CacheControlActivity$ListAdapter$$ExternalSyntheticLambda2());
            int r93 = SharedConfig.keepMedia;
            if (r93 != 3) goto L48;
            int r94 = 1;
        L51:
            r108.setOptions(r94, new String[]{LocaleController.formatPluralString("Days", 3, new Object[0]), LocaleController.formatPluralString("Weeks", 1, new Object[0]), LocaleController.formatPluralString("Months", 1, new Object[0]), LocaleController.getString(R.string.KeepMediaForever)});
            r102 = r108;
            goto L56
        L48:
            if (r93 != 4) goto L50;
            r94 = 0;
            goto L51
        L50:
            r94 = r93 + 2;
            goto L51
        L52:
            r102 = new HeaderCell(this.mContext);
            goto L56
        L53:
            r102 = new StorageUsageView(this.mContext);
            goto L56
        }
    }

    /* loaded from: classes6.dex */
    public static class UserCell extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
        private boolean canDisable;
        protected CheckBox2 checkBox;
        public DialogFileEntities dialogFileEntities;
        private BackupImageView imageView;
        private boolean needDivider;
        private Theme.ResourcesProvider resourcesProvider;
        private TextView textView;
        private AnimatedTextView valueTextView;

        public UserCell(Context r19, Theme.ResourcesProvider r20) {
            super(r19);
            this.resourcesProvider = r20;
            TextView r3 = new TextView(r19);
            this.textView = r3;
            r3.setSingleLine();
            this.textView.setLines(1);
            this.textView.setMaxLines(1);
            this.textView.setTextSize(1, 16.0f);
            this.textView.setEllipsize(TextUtils.TruncateAt.END);
            TextView r32 = this.textView;
            int r7 = 3;
            if (LocaleController.isRTL == false) goto L5;
            int r6 = 5;
        L6:
            r32.setGravity(r6 | 16);
            this.textView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r20));
            TextView r33 = this.textView;
            boolean r62 = LocaleController.isRTL;
            if (r62 == false) goto L9;
            int r9 = 5;
        L10:
            int r12 = r9 | 48;
            if (r62 == false) goto L13;
            float r13 = 21.0f;
        L14:
            if (r62 == false) goto L16;
            float r15 = 72.0f;
        L17:
            addView(r33, LayoutHelper.createFrame(-1, -1.0f, r12, r13, 0.0f, r15, 0.0f));
            AnimatedTextView r10 = new AnimatedTextView(r19, true, true, !LocaleController.isRTL);
            this.valueTextView = r10;
            r10.setAnimationProperties(0.55f, 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.valueTextView.setTextSize(AndroidUtilities.dp(16.0f));
            AnimatedTextView r34 = this.valueTextView;
            if (LocaleController.isRTL == false) goto L20;
            int r4 = 3;
        L21:
            r34.setGravity(r4 | 16);
            this.valueTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteValueText, r20));
            AnimatedTextView r2 = this.valueTextView;
            boolean r35 = LocaleController.isRTL;
            if (r35 == false) goto L24;
            int r42 = 3;
        L25:
            int r122 = r42 | 48;
            if (r35 == false) goto L28;
            float r132 = 21.0f;
        L29:
            if (r35 == false) goto L31;
            float r152 = 72.0f;
        L32:
            addView(r2, LayoutHelper.createFrame(-2, -1.0f, r122, r132, 0.0f, r152, 0.0f));
            BackupImageView r22 = new BackupImageView(r19);
            this.imageView = r22;
            r22.getAvatarDrawable().setScaleSize(0.8f);
            BackupImageView r1 = this.imageView;
            if (LocaleController.isRTL == false) goto L35;
            r7 = 5;
        L35:
            addView(r1, LayoutHelper.createFrame(38, 38.0f, r7 | 16, 17.0f, 0.0f, 17.0f, 0.0f));
            return;
        L31:
            r152 = 21.0f;
            goto L32
        L28:
            r132 = 72.0f;
            goto L29
        L24:
            r42 = 5;
            goto L25
        L20:
            r4 = 5;
            goto L21
        L16:
            r15 = 21.0f;
            goto L17
        L13:
            r13 = 72.0f;
            goto L14
        L9:
            r9 = 3;
            goto L10
        L5:
            r6 = 3;
            goto L6
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r1, int r2, Object... r3) {
            if (r1 != NotificationCenter.emojiLoaded) goto L8;
            TextView r0 = this.textView;
            if (r0 == null) goto L9;
            r0.invalidate();
            return;
        L9:
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r9) {
            super.dispatchDraw(r9);
            if (this.needDivider == true) goto L5;
            return;
        L5:
            if (LocaleController.isRTL == false) goto L8;
            float r0 = 0.0f;
        L7:
            float r3 = r0;
            float r4 = getMeasuredHeight() - 1;
            int r02 = getMeasuredWidth();
            if (LocaleController.isRTL == false) goto L12;
            int r1 = AndroidUtilities.dp(72.0f);
        L13:
            r9.drawLine(r3, r4, r02 - r1, getMeasuredHeight() - 1, Theme.dividerPaint);
            return;
        L12:
            r1 = 0;
            goto L13
        L8:
            r0 = AndroidUtilities.dp(72.0f);
            goto L7
        }

        public BackupImageView getImageView() {
            return this.imageView;
        }

        public TextView getTextView() {
            return this.textView;
        }

        public AnimatedTextView getValueTextView() {
            return this.valueTextView;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r4) {
            super.onInitializeAccessibilityNodeInfo(r4);
            StringBuilder r0 = new StringBuilder();
            r0.append(this.textView.getText());
            AnimatedTextView r1 = this.valueTextView;
            if (r1 != null) goto L5;
        L7:
            String r12 = _UrlKt.FRAGMENT_ENCODE_SET;
        L8:
            r0.append(r12);
            r4.setText(r0.toString());
            r4.setEnabled(isEnabled());
            return;
        L5:
            if (r1.getVisibility() != 0) goto L7;
            r12 = "\n" + this.valueTextView.getText();
            goto L8
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r5, int r6) {
            setMeasuredDimension(View.MeasureSpec.getSize(r5), AndroidUtilities.dp(50.0f) + (this.needDivider ? 1 : 0));
            int r52 = ((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()) - AndroidUtilities.dp(34.0f);
            int r62 = r52 / 2;
            if (this.imageView.getVisibility() != 0) goto L6;
            this.imageView.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(38.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(38.0f), TLObject.FLAG_30));
        L6:
            if (this.valueTextView.getVisibility() != 0) goto L8;
            this.valueTextView.measure(View.MeasureSpec.makeMeasureSpec(r62, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TLObject.FLAG_30));
            r52 = (r52 - this.valueTextView.getMeasuredWidth()) - AndroidUtilities.dp(8.0f);
        L8:
            int r63 = this.valueTextView.getMeasuredWidth() + AndroidUtilities.dp(12.0f);
            boolean r0 = LocaleController.isRTL;
            TextView r2 = this.textView;
            if (r0 == false) goto L11;
            ((ViewGroup.MarginLayoutParams) r2.getLayoutParams()).leftMargin = r63;
        L12:
            this.textView.measure(View.MeasureSpec.makeMeasureSpec(r52 - r63, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TLObject.FLAG_30));
            CheckBox2 r4 = this.checkBox;
            if (r4 == null) goto L16;
            r4.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(24.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(24.0f), TLObject.FLAG_30));
            return;
        L16:
            return;
        L11:
            ((ViewGroup.MarginLayoutParams) r2.getLayoutParams()).rightMargin = r63;
            goto L12
        }

        public void setCanDisable(boolean r1) {
            this.canDisable = r1;
        }

        public void setChecked(boolean r10, boolean r11) {
            CheckBox2 r0 = this.checkBox;
            if (r0 != null) goto L6;
            if (r10 == true) goto L6;
            return;
        L6:
            if (r0 != null) goto L11;
            CheckBox2 r02 = new CheckBox2(getContext(), 21, this.resourcesProvider);
            this.checkBox = r02;
            r02.setColor(-1, Theme.key_windowBackgroundWhite, Theme.key_checkboxCheck);
            this.checkBox.setDrawUnchecked(false);
            int r1 = 3;
            this.checkBox.setDrawBackgroundAsArc(3);
            CheckBox2 r03 = this.checkBox;
            if (LocaleController.isRTL == false) goto L10;
            r1 = 5;
        L10:
            addView(r03, LayoutHelper.createFrame(24, 24.0f, r1 | 48, 38.0f, 25.0f, 38.0f, 0.0f));
        L11:
            this.checkBox.setChecked(r10, r11);
        }

        @Override // android.view.View
        public void setEnabled(boolean r5) {
            super.setEnabled(r5);
            TextView r0 = this.textView;
            float r1 = 1.0f;
            if (r5 == false) goto L5;
        L8:
            float r3 = 1.0f;
        L9:
            r0.setAlpha(r3);
            if (this.valueTextView.getVisibility() != 0) goto L19;
            AnimatedTextView r02 = this.valueTextView;
            if (r5 == false) goto L14;
        L17:
            r02.setAlpha(r1);
            return;
        L14:
            if (this.canDisable == false) goto L17;
            r1 = 0.5f;
            goto L17
        L19:
            return;
        L5:
            if (this.canDisable == false) goto L8;
            r3 = 0.5f;
            goto L9
        }

        public void setTextAndValue(CharSequence r2, CharSequence r3, boolean r4) {
            setTextAndValue(r2, r3, false, r4);
        }

        public void setTextAndValue(CharSequence r3, CharSequence r4, boolean r5, boolean r6) {
            this.textView.setText(Emoji.replaceEmoji(r3, this.textView.getPaint().getFontMetricsInt(), false));
            AnimatedTextView r32 = this.valueTextView;
            if (r4 == null) goto L5;
            r32.setText(r4, r5);
            this.valueTextView.setVisibility(0);
        L6:
            this.needDivider = r6;
            setWillNotDraw(!r6);
            requestLayout();
            return;
        L5:
            r32.setVisibility(4);
            goto L6
        }

        public void setTextColor(int r1) {
            this.textView.setTextColor(r1);
        }

        public void setTextValueColor(int r1) {
            this.valueTextView.setTextColor(r1);
        }
    }

    public static /* synthetic */ void $r8$lambda$2FY2SgcX80W7mTC6XPwuE7aWzo8(CacheControlActivity r0) {
        r0.lambda$getThemeDescriptions$25();
    }

    public static /* synthetic */ void $r8$lambda$2ePNjvfGiOSD6jg_QbTLX5RMf8Y(CacheControlActivity r0, View r1) {
        r0.lambda$createView$17(r1);
    }

    /* renamed from: $r8$lambda$4Cc8Mdegz7SWXFH-3JUhV088FDE, reason: not valid java name */
    public static /* synthetic */ void m6412$r8$lambda$4Cc8Mdegz7SWXFH3JUhV088FDE(CacheControlActivity r0, int r1, int r2) {
        r0.lambda$createView$18(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$DteALnOkKovTEQO_Bh8QxAAwQwQ(CacheControlActivity r0, View r1, int r2, float r3, float r4) {
        r0.lambda$createView$19(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$EBBcra-pyt3AtJvMCQ8LPyhTChE, reason: not valid java name */
    public static /* synthetic */ void m6413$r8$lambda$EBBcrapyt3AtJvMCQ8LPyhTChE(Utilities.Callback r0, long r1) {
        r0.run(Long.valueOf(r1));
    }

    /* renamed from: $r8$lambda$GfHBaCjk8NKKW7gyA0DAG8-2eFI, reason: not valid java name */
    public static /* synthetic */ void m6414$r8$lambda$GfHBaCjk8NKKW7gyA0DAG82eFI(CacheControlActivity r0) {
        r0.lambda$loadDialogEntities$8();
    }

    public static /* synthetic */ void $r8$lambda$H3h8uMq7DAEib02WcGFjpYjku3w(CacheControlActivity r0, AlertDialog r1) {
        r0.lambda$cleanupDialogFiles$22(r1);
    }

    /* renamed from: $r8$lambda$I5aTkQfi8mZ4pln1Hu-y_E_vZ_g, reason: not valid java name */
    public static /* synthetic */ void m6415$r8$lambda$I5aTkQfi8mZ4pln1Huy_E_vZ_g(CacheControlActivity r0, Utilities.Callback2 r1, Runnable r2) {
        r0.lambda$cleanupFolders$11(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$IdWc5CO7rxuAs1Jpx9IxM5ueG7I(Utilities.Callback2 r2, int[] r3, int r4, Float r5) {
        float r42 = r4;
        r2.run(Float.valueOf((r3[0] / r42) + ((1.0f / r42) * MathUtils.clamp(r5.floatValue(), 0.0f, 1.0f))), Boolean.FALSE);
    }

    public static /* synthetic */ void $r8$lambda$ImYtktw0UQIsMW3jophDp7UYDxY(CacheControlActivity r0) {
        r0.lambda$onFragmentCreate$4();
    }

    /* renamed from: $r8$lambda$KUzywUVuCKXD_kfrFQdoO6Gx1-0, reason: not valid java name */
    public static /* synthetic */ void m6416$r8$lambda$KUzywUVuCKXD_kfrFQdoO6Gx10(CacheControlActivity r0, boolean r1, AlertDialog r2, int r3) {
        r0.lambda$clearDatabase$24(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$KdMOqnRdy9q2Wn3ecDWB80n3ucc(CacheControlActivity r0) {
        r0.lambda$onFragmentCreate$5();
    }

    public static /* synthetic */ void $r8$lambda$McYV9DSNm5gk3U2h_3xgIdWdumQ(CacheControlActivity r0, ArrayList r1, ArrayList r2, ArrayList r3, CacheModel r4) {
        r0.lambda$loadDialogEntities$7(r1, r2, r3, r4);
    }

    public static /* synthetic */ int $r8$lambda$RqrFx06wc9E0AZr9SO9vAVjU2Gk(DialogFileEntities r2, DialogFileEntities r3) {
        long r0 = r3.totalSize;
        long r22 = r2.totalSize;
        if (r0 <= r22) goto L6;
        return 1;
    L6:
        if (r0 >= r22) goto L9;
        return -1;
    L9:
        return 0;
    }

    public static /* synthetic */ void $r8$lambda$YnZ3smiJlyPWZmZdeJlPkHWuXDM(CacheControlActivity r0, boolean r1, long r2, Runnable r4) {
        r0.lambda$cleanupFoldersInternal$16(r1, r2, r4);
    }

    public static /* synthetic */ void $r8$lambda$YuDtCW7LlM10Tebxg0eXEKA3Rfs(CacheControlActivity r0, ArrayList r1, AlertDialog r2) {
        r0.lambda$cleanupDialogFiles$23(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$bUnnJwTW1bNRfzdk641lAiszbCw(CacheControlActivity r0, Utilities.Callback2 r1, Runnable r2) {
        r0.lambda$cleanupFolders$12(r1, r2);
    }

    /* renamed from: $r8$lambda$fNB1ypvT1aLKISN4x-QOCLaHAGk, reason: not valid java name */
    public static /* synthetic */ void m6417$r8$lambda$fNB1ypvT1aLKISN4xQOCLaHAGk(Utilities.Callback r23) {
        canceled = false;
        long r4 = getDirectorySize(FileLoader.checkDirectory(4), 5);
        long r6 = getDirectorySize(FileLoader.checkDirectory(4), 4);
        long r8 = getDirectorySize(FileLoader.checkDirectory(0), 0) + getDirectorySize(FileLoader.checkDirectory(100), 0);
        long r10 = getDirectorySize(FileLoader.checkDirectory(2), 0) + getDirectorySize(FileLoader.checkDirectory(101), 0);
        long r15 = getDirectorySize(FileLoader.checkDirectory(3), 1) + getDirectorySize(FileLoader.checkDirectory(5), 1);
        long r17 = getDirectorySize(FileLoader.checkDirectory(3), 2) + getDirectorySize(FileLoader.checkDirectory(5), 2);
        long r2 = getDirectorySize(new File(FileLoader.checkDirectory(4), "acache"), 0) + getDirectorySize(FileLoader.checkDirectory(4), 3);
        long r12 = getDirectorySize(FileLoader.checkDirectory(1), 0);
        long r0 = getDirectorySize(FileLoader.checkDirectory(6), 0);
        long r02 = getDirectorySize(AndroidUtilities.getLogsDir(), 1);
        if (BuildVars.DEBUG_VERSION == false) goto L5;
    L7:
        long r42 = ((((((((r4 + r6) + r10) + r12) + r8) + r15) + r17) + r2) + r0) + r02;
        lastTotalSizeCalculated = Long.valueOf(r42);
        lastTotalSizeCalculatedTime = System.currentTimeMillis();
        if (canceled == true) goto L11;
        AndroidUtilities.runOnUIThread(new CacheControlActivity$$ExternalSyntheticLambda7(r23, r42));
        return;
    L11:
        return;
    L5:
        if (r02 >= 268435456) goto L7;
        r02 = 0;
        goto L7
    }

    public static /* synthetic */ void $r8$lambda$grxg2OghdSPw3knSzVegOKrV24A(Utilities.Callback2 r10) {
        ArrayList<File> r0 = AndroidUtilities.getRootDirs();
        int r1 = 0;
        File r2 = r0.get(0);
        r2.getAbsolutePath();
        if (TextUtils.isEmpty(SharedConfig.storageCacheDir) == true) goto L17;
        int r3 = r0.size();
    L5:
        if (r1 >= r3) goto L17;
        File r4 = r0.get(r1);
        if (r4.getAbsolutePath().startsWith(SharedConfig.storageCacheDir) == false) goto L11;
        if (r4.canWrite() == false) goto L11;
        r2 = r4;
    L11:
        r1 = r1 + 1;
    L17:
        StatFs r02 = new StatFs(r2.getPath());     // Catch: Exception -> L14
        long r5 = r02.getBlockSizeLong();     // Catch: Exception -> L14
        long r7 = r02.getAvailableBlocksLong();     // Catch: Exception -> L14
        AndroidUtilities.runOnUIThread(new CacheControlActivity$$ExternalSyntheticLambda16(r02.getBlockCountLong(), r5, r7, r10));     // Catch: Exception -> L14
        return;
    L14:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ void $r8$lambda$ivwrlQn3ABaDxM0KDHchsj8Y_c4(CacheControlActivity r0, long r1) {
        r0.lambda$cleanupFoldersInternal$15(r1);
    }

    /* renamed from: $r8$lambda$j86wjd84-2ZUtavpwPZS27j2fKc, reason: not valid java name */
    public static /* synthetic */ int m6418$r8$lambda$j86wjd842ZUtavpwPZS27j2fKc(ItemInner r2, ItemInner r3) {
        return Long.compare(r3.size, r2.size);
    }

    public static /* synthetic */ void $r8$lambda$lUCQY1FVKLfoB8YXalotnZNOkYA(long r0, long r2, long r4, Utilities.Callback2 r6) {
        lastDeviceTotalSize = Long.valueOf(r0 * r2);
        Long r02 = Long.valueOf(r4 * r2);
        lastDeviceTotalFreeSize = r02;
        if (r6 == null) goto L6;
        r6.run(lastDeviceTotalSize, r02);
        return;
    }

    public static /* synthetic */ void $r8$lambda$qBw7DSsnu84m5OlzuNK7troImk0(CacheControlActivity r0, ArrayList r1, ArrayList r2, ArrayList r3, CacheModel r4) {
        r0.lambda$loadDialogEntities$6(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$rZDN0MmSaWi9_7Sh_JPa0QlqyVA(Utilities.Callback2 r3, int[] r4, int r5, long r6) {
        long r0 = System.currentTimeMillis();
        boolean r2 = false;
        Float r42 = Float.valueOf(r4[0] / r5);
        if ((r0 - r6) <= 250) goto L5;
        r2 = true;
    L5:
        r3.run(r42, Boolean.valueOf(r2));
    }

    public static /* synthetic */ void $r8$lambda$vxzKXzbzVHt4STqlMiDJUVKmj6I(CacheControlActivity r0, AlertDialog r1, int r2) {
        r0.lambda$clearSelectedFiles$20(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$wjrzm28S63z4RoMEwv6xOlGHLVw(CacheControlActivity r0, ValueAnimator r1) {
        r0.lambda$updateActionBar$21(r1);
    }

    /* renamed from: -$$Nest$fgetactionBarShadowAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m6419$$Nest$fgetactionBarShadowAlpha(CacheControlActivity r0) {
        return r0.actionBarShadowAlpha;
    }

    /* renamed from: -$$Nest$fgetactionBarShownT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m6420$$Nest$fgetactionBarShownT(CacheControlActivity r0) {
        return r0.actionBarShownT;
    }

    /* renamed from: -$$Nest$fgetaudioSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m6421$$Nest$fgetaudioSize(CacheControlActivity r2) {
        return r2.audioSize;
    }

    /* renamed from: -$$Nest$fgetbottomSheet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BottomSheet m6422$$Nest$fgetbottomSheet(CacheControlActivity r0) {
        return r0.bottomSheet;
    }

    /* renamed from: -$$Nest$fgetcacheChart, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CacheChart m6423$$Nest$fgetcacheChart(CacheControlActivity r0) {
        return r0.cacheChart;
    }

    /* renamed from: -$$Nest$fgetcacheChartHeader, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CacheChartHeader m6424$$Nest$fgetcacheChartHeader(CacheControlActivity r0) {
        return r0.cacheChartHeader;
    }

    /* renamed from: -$$Nest$fgetcacheSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m6425$$Nest$fgetcacheSize(CacheControlActivity r2) {
        return r2.cacheSize;
    }

    /* renamed from: -$$Nest$fgetcacheTempSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m6426$$Nest$fgetcacheTempSize(CacheControlActivity r2) {
        return r2.cacheTempSize;
    }

    /* renamed from: -$$Nest$fgetcachedMediaLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CachedMediaLayout m6427$$Nest$fgetcachedMediaLayout(CacheControlActivity r0) {
        return r0.cachedMediaLayout;
    }

    /* renamed from: -$$Nest$fgetcalculating, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6428$$Nest$fgetcalculating(CacheControlActivity r0) {
        return r0.calculating;
    }

    /* renamed from: -$$Nest$fgetcollapsed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6429$$Nest$fgetcollapsed(CacheControlActivity r0) {
        return r0.collapsed;
    }

    /* renamed from: -$$Nest$fgetdatabaseSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m6430$$Nest$fgetdatabaseSize(CacheControlActivity r2) {
        return r2.databaseSize;
    }

    /* renamed from: -$$Nest$fgetdocumentsSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m6431$$Nest$fgetdocumentsSize(CacheControlActivity r2) {
        return r2.documentsSize;
    }

    /* renamed from: -$$Nest$fgetitemInners, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m6432$$Nest$fgetitemInners(CacheControlActivity r0) {
        return r0.itemInners;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayoutManager m6433$$Nest$fgetlayoutManager(CacheControlActivity r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m6434$$Nest$fgetlistView(CacheControlActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetlogsSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m6435$$Nest$fgetlogsSize(CacheControlActivity r2) {
        return r2.logsSize;
    }

    /* renamed from: -$$Nest$fgetmigrateOldFolderRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m6436$$Nest$fgetmigrateOldFolderRow(CacheControlActivity r2) {
        return r2.migrateOldFolderRow;
    }

    /* renamed from: -$$Nest$fgetmusicSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m6437$$Nest$fgetmusicSize(CacheControlActivity r2) {
        return r2.musicSize;
    }

    /* renamed from: -$$Nest$fgetnestedSizeNotifierLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ NestedSizeNotifierLayout m6438$$Nest$fgetnestedSizeNotifierLayout(CacheControlActivity r0) {
        return r0.nestedSizeNotifierLayout;
    }

    /* renamed from: -$$Nest$fgetpercents, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m6439$$Nest$fgetpercents(CacheControlActivity r0) {
        return r0.percents;
    }

    /* renamed from: -$$Nest$fgetphotoSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m6440$$Nest$fgetphotoSize(CacheControlActivity r2) {
        return r2.photoSize;
    }

    /* renamed from: -$$Nest$fgetselected, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean[] m6441$$Nest$fgetselected(CacheControlActivity r0) {
        return r0.selected;
    }

    /* renamed from: -$$Nest$fgetstickersCacheSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m6442$$Nest$fgetstickersCacheSize(CacheControlActivity r2) {
        return r2.stickersCacheSize;
    }

    /* renamed from: -$$Nest$fgetstoriesSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m6443$$Nest$fgetstoriesSize(CacheControlActivity r2) {
        return r2.storiesSize;
    }

    /* renamed from: -$$Nest$fgettotalDeviceFreeSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m6444$$Nest$fgettotalDeviceFreeSize(CacheControlActivity r2) {
        return r2.totalDeviceFreeSize;
    }

    /* renamed from: -$$Nest$fgettotalDeviceSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m6445$$Nest$fgettotalDeviceSize(CacheControlActivity r2) {
        return r2.totalDeviceSize;
    }

    /* renamed from: -$$Nest$fgettotalSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m6446$$Nest$fgettotalSize(CacheControlActivity r2) {
        return r2.totalSize;
    }

    /* renamed from: -$$Nest$fgetvideoSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m6447$$Nest$fgetvideoSize(CacheControlActivity r2) {
        return r2.videoSize;
    }

    /* renamed from: -$$Nest$fputactionBarShadowAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6448$$Nest$fputactionBarShadowAlpha(CacheControlActivity r0, float r1) {
        r0.actionBarShadowAlpha = r1;
    }

    /* renamed from: -$$Nest$fputcacheChart, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6449$$Nest$fputcacheChart(CacheControlActivity r0, CacheChart r1) {
        r0.cacheChart = r1;
    }

    /* renamed from: -$$Nest$fputcacheChartHeader, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6450$$Nest$fputcacheChartHeader(CacheControlActivity r0, CacheChartHeader r1) {
        r0.cacheChartHeader = r1;
    }

    /* renamed from: -$$Nest$fputcachedMediaLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6451$$Nest$fputcachedMediaLayout(CacheControlActivity r0, CachedMediaLayout r1) {
        r0.cachedMediaLayout = r1;
    }

    /* renamed from: -$$Nest$fputclearCacheButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6452$$Nest$fputclearCacheButton(CacheControlActivity r0, ClearCacheButtonInternal r1) {
        r0.clearCacheButton = r1;
    }

    /* renamed from: -$$Nest$fputcollapsed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6453$$Nest$fputcollapsed(CacheControlActivity r0, boolean r1) {
        r0.collapsed = r1;
    }

    /* renamed from: -$$Nest$mcleanupDialogFiles, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6454$$Nest$mcleanupDialogFiles(CacheControlActivity r0, DialogFileEntities r1, StorageDiagramView.ClearViewData[] r2, CacheModel r3) {
        r0.cleanupDialogFiles(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mcleanupFolders, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6455$$Nest$mcleanupFolders(CacheControlActivity r0, Utilities.Callback2 r1, Runnable r2) {
        r0.cleanupFolders(r1, r2);
    }

    /* renamed from: -$$Nest$mclearDatabase, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6456$$Nest$mclearDatabase(CacheControlActivity r0, boolean r1) {
        r0.clearDatabase(r1);
    }

    /* renamed from: -$$Nest$mclearSelectedFiles, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6457$$Nest$mclearSelectedFiles(CacheControlActivity r0) {
        r0.clearSelectedFiles();
    }

    /* renamed from: -$$Nest$mformatPercent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m6458$$Nest$mformatPercent(CacheControlActivity r0, float r1) {
        return r0.formatPercent(r1);
    }

    /* renamed from: -$$Nest$mgetCheckBoxTitle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CharSequence m6459$$Nest$mgetCheckBoxTitle(CacheControlActivity r0, CharSequence r1, int r2, boolean r3) {
        return r0.getCheckBoxTitle(r1, r2, r3);
    }

    /* renamed from: -$$Nest$misAllSectionsSelected, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6460$$Nest$misAllSectionsSelected(CacheControlActivity r0) {
        return r0.isAllSectionsSelected();
    }

    /* renamed from: -$$Nest$misOtherSelected, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6461$$Nest$misOtherSelected(CacheControlActivity r0) {
        return r0.isOtherSelected();
    }

    /* renamed from: -$$Nest$mshowClearCacheDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6462$$Nest$mshowClearCacheDialog(CacheControlActivity r0, DialogFileEntities r1) {
        r0.showClearCacheDialog(r1);
    }

    /* renamed from: -$$Nest$mtoggleOtherSelected, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6463$$Nest$mtoggleOtherSelected(CacheControlActivity r0, View r1) {
        r0.toggleOtherSelected(r1);
    }

    /* renamed from: -$$Nest$mupdateActionBar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6464$$Nest$mupdateActionBar(CacheControlActivity r0, boolean r1) {
        r0.updateActionBar(r1);
    }

    /* renamed from: -$$Nest$mupdateActionMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6465$$Nest$mupdateActionMode(CacheControlActivity r0) {
        r0.updateActionMode();
    }

    /* renamed from: -$$Nest$mupdateChart, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6466$$Nest$mupdateChart(CacheControlActivity r0) {
        r0.updateChart();
    }

    /* renamed from: -$$Nest$mupdateRows, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6467$$Nest$mupdateRows(CacheControlActivity r0) {
        r0.updateRows();
    }

    static {
    }

    public CacheControlActivity() {
        this.selected = new boolean[]{true, true, true, true, true, true, true, true, true, true, true};
        this.databaseSize = -1;
        this.cacheSize = -1;
        this.cacheEmojiSize = -1;
        this.cacheTempSize = -1;
        this.documentsSize = -1;
        this.audioSize = -1;
        this.storiesSize = -1;
        this.musicSize = -1;
        this.photoSize = -1;
        this.videoSize = -1;
        this.logsSize = -1;
        this.stickersCacheSize = -1;
        this.totalSize = -1;
        this.totalDeviceSize = -1;
        this.totalDeviceFreeSize = -1;
        this.migrateOldFolderRow = -1;
        this.calculating = true;
        this.collapsed = true;
        this.sectionsStartRow = -1;
        this.sectionsEndRow = -1;
        this.oldItems = new ArrayList();
        this.itemInners = new ArrayList();
        this.actionBarShadowAlpha = 1.0f;
    }

    public static /* synthetic */ ActionBar access$000(CacheControlActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ INavigationLayout access$100(CacheControlActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ INavigationLayout access$200(CacheControlActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ ActionBar access$300(CacheControlActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$400(CacheControlActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$500(CacheControlActivity r0) {
        return r0.actionBar;
    }

    public static void calculateTotalSize(Utilities.Callback<Long> r4) {
        if (r4 == null) goto L11;
        Long r0 = lastTotalSizeCalculated;
        if (r0 == null) goto L9;
        r4.run(r0);
        if ((System.currentTimeMillis() - lastTotalSizeCalculatedTime) >= 5000) goto L9;
        return;
    L9:
        Utilities.cacheClearQueue.postRunnable(new CacheControlActivity$$ExternalSyntheticLambda3(r4));
        return;
    }

    public static void cleanDirJava(String r11, int r12, int[] r13, Utilities.Callback<Float> r14) {
        int r0 = countDirJava(r11, r12);
        if (r13 != null) goto L5;
        r13 = new int[]{0};
    L5:
        File r3 = new File(r11);
        if (r3.exists() == false) goto L71;
        File[] r32 = r3.listFiles();
        if (r32 == null) goto L84;
        int r4 = 0;
    L12:
        if (r4 >= r32.length) goto L85;
        File r5 = r32[r4];
        String r6 = r5.getName();
        if (".".equals(r6) == true) goto L70;
        if (r12 <= 0) goto L62;
        if (r6.length() < 4) goto L62;
        String r7 = r6.toLowerCase();
        if (r7.endsWith(".mp3") == false) goto L22;
    L25:
        boolean r8 = true;
    L27:
        if (r7.endsWith(".tgs") == false) goto L29;
    L32:
        boolean r9 = true;
    L34:
        if (r7.endsWith(".tmp") == false) goto L36;
    L41:
        boolean r72 = true;
    L42:
        if (r8 == true) goto L44;
    L45:
        if (r8 == false) goto L47;
    L48:
        if (r9 == true) goto L50;
    L51:
        if (r9 == false) goto L53;
    L54:
        if (r72 == true) goto L56;
    L57:
        if (r72 == true) goto L62;
        if (r12 != LISTDIR_DOCTYPE2_TEMP) goto L62;
    L56:
        if (r12 == LISTDIR_DOCTYPE2_OTHER) goto L70;
    L53:
        if (r12 == LISTDIR_DOCTYPE2_EMOJI) goto L70;
    L50:
        if (r12 == LISTDIR_DOCTYPE2_OTHER) goto L70;
    L47:
        if (r12 == LISTDIR_DOCTYPE_MUSIC) goto L70;
    L44:
        if (r12 == LISTDIR_DOCTYPE_OTHER_THAN_MUSIC) goto L70;
    L36:
        if (r7.endsWith(".temp") == true) goto L41;
        if (r7.endsWith(".preload") == true) goto L41;
        r72 = false;
        goto L42
    L29:
        if (r7.endsWith(".webm") == true) goto L32;
        r9 = false;
        goto L34
    L22:
        if (r7.endsWith(".m4a") == true) goto L25;
        r8 = false;
    L62:
        if (r5.isDirectory() == true) goto L64;
        r5.delete();
        int r52 = r13[0] + 1;
        r13[0] = r52;
        if (r14 == null) goto L70;
        r14.run(Float.valueOf(r52 / r0));
        goto L70
    L64:
        if ("drafts".equals(r5.getName()) == true) goto L70;
        cleanDirJava(r11 + "/" + r6, r12, r13, r14);
    L70:
        r4 = r4 + 1;
        goto L12
    L85:
        return;
    L84:
        return;
    }

    private void cleanupDialogFiles(DialogFileEntities r17, StorageDiagramView.ClearViewData[] r18, CacheModel r19) {
        AlertDialog r3 = new AlertDialog(getParentActivity(), 3);
        r3.setCanCancel(false);
        r3.showDelayed(500);
        HashSet r6 = new HashSet();
        long r7 = this.totalSize;
        int r9 = 0;
    L4:
        if (r9 >= 8) goto L46;
        if (r18 == null) goto L11;
        StorageDiagramView.ClearViewData r10 = r18[r9];
        if (r10 == null) goto L44;
        if (r10.clear == true) goto L11;
    L44:
        r9 = r9 + 1;
    L11:
        FileEntities r102 = r17.entitiesByType.get(r9);
        if (r102 == null) goto L44;
        r6.addAll(r102.files);
        long r14 = r17.totalSize;
        long r4 = r102.totalSize;
        r17.totalSize = r14 - r4;
        this.totalSize -= r4;
        this.totalDeviceFreeSize += r4;
        r17.entitiesByType.delete(r9);
        if (r9 != 0) goto L17;
        this.photoSize -= r102.totalSize;
        goto L44
    L17:
        if (r9 != 1) goto L19;
        this.videoSize -= r102.totalSize;
        goto L44
    L19:
        if (r9 != 2) goto L22;
        this.documentsSize -= r102.totalSize;
        goto L44
    L22:
        if (r9 != 3) goto L24;
        this.musicSize -= r102.totalSize;
        goto L44
    L24:
        if (r9 != 4) goto L27;
        this.audioSize -= r102.totalSize;
        goto L44
    L27:
        if (r9 != 5) goto L30;
        this.stickersCacheSize -= r102.totalSize;
        goto L44
    L30:
        if (r9 != 7) goto L43;
        int r5 = 0;
    L33:
        if (r5 >= r102.files.size()) goto L44;
        CacheModel.FileInfo r11 = r102.files.get(r5);
        int r12 = getTypeByPath(r102.files.get(r5).file.getAbsolutePath());
        if (r12 != 7) goto L37;
        this.storiesSize -= r11.size;
    L42:
        r5 = r5 + 1;
        goto L33
    L37:
        if (r12 != 0) goto L39;
        this.photoSize -= r11.size;
        goto L42
    L39:
        if (r12 != 1) goto L41;
        this.videoSize -= r11.size;
        goto L42
    L41:
        this.cacheSize -= r11.size;
        goto L42
    L43:
        this.cacheSize -= r102.totalSize;
        goto L44
    L46:
        if (r17.entitiesByType.size() != 0) goto L48;
        this.cacheModel.remove(r17);
    L48:
        updateRows();
        if (r19 == null) goto L68;
        Iterator<CacheModel.FileInfo> r2 = r19.selectedFiles.iterator();
    L52:
        if (r2.hasNext() == false) goto L68;
        CacheModel.FileInfo r42 = r2.next();
        if (r6.contains(r42) == true) goto L52;
        long r92 = this.totalSize;
        long r142 = r42.size;
        this.totalSize = r92 - r142;
        this.totalDeviceFreeSize += r142;
        r6.add(r42);
        r17.removeFile(r42);
        int r52 = r42.type;
        if (r52 == 0) goto L57;
        if (r52 == 1) goto L60;
        if (r52 != 2) goto L64;
        this.documentsSize -= r42.size;
        goto L52
    L64:
        if (r52 == 3) goto L65;
        if (r52 != 4) goto L52;
        this.audioSize -= r42.size;
        goto L52
    L65:
        this.musicSize -= r42.size;
        goto L52
    L60:
        this.videoSize -= r42.size;
        goto L52
    L57:
        this.photoSize -= r42.size;
    L68:
        Iterator r1 = r6.iterator();
    L70:
        if (r1.hasNext() == false) goto L72;
        this.cacheModel.onFileDeleted((CacheModel.FileInfo) r1.next());
        goto L70
    L72:
        Bulletin r13 = BulletinFactory.of(this).createSimpleBulletin(R.raw.ic_delete, LocaleController.formatString(R.string.CacheWasCleared, new Object[]{AndroidUtilities.formatFileSize(r7 - this.totalSize)}));
        r13.hideAfterBottomSheet = false;
        r13.show();
        ArrayList r15 = new ArrayList(r6);
        getFileLoader().getFileDatabase().removeFiles(r15);
        getFileLoader().cancelLoadAllFiles();
        getFileLoader().getFileLoaderQueue().postRunnable(new CacheControlActivity$$ExternalSyntheticLambda15(this, r15, r3));
    }

    private void cleanupFolders(Utilities.Callback2<Float, Boolean> r3, Runnable r4) {
        CacheModel r0 = this.cacheModel;
        if (r0 == null) goto L5;
        r0.clearSelection();
    L5:
        CachedMediaLayout r02 = this.cachedMediaLayout;
        if (r02 == null) goto L8;
        r02.updateVisibleRows();
        this.cachedMediaLayout.showActionMode(false);
    L8:
        getFileLoader().cancelLoadAllFiles();
        getFileLoader().getFileLoaderQueue().postRunnable(new CacheControlActivity$$ExternalSyntheticLambda20(this, r3, r4));
        setCacheModel(null);
        this.loadingDialogs = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void cleanupFoldersInternal(Utilities.Callback2<Float, Boolean> r23, Runnable r24) {
        int r2 = 1;
        int[] r5 = {0};
        boolean[] r0 = this.selected;
        int r10 = 2;
        if (r0[0] == false) goto L5;
        int r3 = 2;
    L7:
        if (r0[1] == false) goto L9;
        int r4 = 2;
    L10:
        int r32 = r3 + r4;
        if (r0[2] == false) goto L13;
        int r42 = 2;
    L14:
        int r33 = r32 + r42;
        int r11 = 3;
        if (r0[3] == false) goto L17;
        int r43 = 2;
    L18:
        int r12 = 4;
        int r34 = (r33 + r43) + (r0[4] ? 1 : 0);
        int r13 = 5;
        if (r0[5] == false) goto L21;
        int r44 = 2;
    L22:
        int r35 = ((((r34 + r44) + (r0[6] ? 1 : 0)) + (r0[7] ? 1 : 0)) + (r0[8] ? 1 : 0)) + (r0[9] ? 1 : 0);
        long r7 = System.currentTimeMillis();
        CacheControlActivity$$ExternalSyntheticLambda23 r9 = new CacheControlActivity$$ExternalSyntheticLambda23(r23, r5, r35);
        CacheControlActivity$$ExternalSyntheticLambda24 r36 = new CacheControlActivity$$ExternalSyntheticLambda24(r23, r5, r35, r7);
        long r6 = 0;
        boolean r17 = true;
        int r45 = 0;
        int r8 = 0;
    L24:
        if (r45 >= 10) goto L125;
        if (this.selected[r45] == true) goto L29;
        int r15 = r12;
        int r18 = r13;
        r17 = false;
    L124:
        r45 = r45 + 1;
        r12 = r15;
        r13 = r18;
        r2 = 1;
        r10 = 2;
        r11 = 3;
        goto L24
    L29:
        if (r45 != 0) goto L33;
        r6 = r6 + this.photoSize;
        r15 = r12;
        r18 = r13;
        r12 = 0;
    L31:
        r13 = 0;
    L32:
        int r14 = 9;
    L57:
        if (r12 == (-1)) goto L124;
        int r19 = r15;
        int r20 = r2;
        if (r45 != 7) goto L66;
        cleanDirJava(ApplicationLoader.getFilesDirFixed("rasterized/wallpaper").getAbsolutePath(), 0, null, null);     // Catch: Exception -> L63
    L63:
        e = move-exception;
        FileLog.e(e);
    L66:
        if (r45 != r14) goto L69;
        File r22 = AndroidUtilities.getLogsDir();
    L72:
        if (r22 == null) goto L74;
        cleanDirJava(r22.getAbsolutePath(), r13, null, r9);
    L74:
        r5[0] = r5[0] + 1;
        r36.run();
        if (r12 != 100) goto L80;
        File r25 = FileLoader.checkDirectory(r19);
        if (r25 == null) goto L79;
        cleanDirJava(r25.getAbsolutePath(), r11, null, r9);
    L79:
        r5[0] = r5[0] + 1;
        r36.run();
    L80:
        if (r12 == 0) goto L84;
        if (r12 == r10) goto L84;
        char r16 = 0;
    L91:
        if (r12 != r11) goto L97;
        File r142 = FileLoader.checkDirectory(r18);
        if (r142 == null) goto L95;
        cleanDirJava(r142.getAbsolutePath(), r13, null, r9);
    L95:
        r5[r16] = r5[r16] + 1;
        r36.run();
    L97:
        if (r45 != 9) goto L100;
        this.logsSize = getDirectorySize(AndroidUtilities.getLogsDir(), r20);
        r15 = r19;
        goto L124
    L100:
        if (r12 != r19) goto L104;
        this.cacheSize = getDirectorySize(FileLoader.checkDirectory(r19), r18);
        this.cacheTempSize = getDirectorySize(FileLoader.checkDirectory(r19), r19);
        r8 = r20;
    L102:
        r15 = 4;
        r18 = 5;
        goto L124
    L104:
        if (r12 != r20) goto L107;
        this.audioSize = getDirectorySize(FileLoader.checkDirectory(r20), r13);
        goto L102
    L107:
        if (r12 != 6) goto L109;
        this.storiesSize = getDirectorySize(FileLoader.checkDirectory(6), r13);
        goto L102
    L109:
        if (r12 != r11) goto L114;
        if (r13 != r20) goto L113;
        long r143 = getDirectorySize(FileLoader.checkDirectory(r11), r13);
        this.documentsSize = r143;
        r18 = 5;
        this.documentsSize = r143 + getDirectorySize(FileLoader.checkDirectory(5), r13);
    L112:
        r15 = 4;
        goto L124
    L113:
        r18 = 5;
        long r144 = getDirectorySize(FileLoader.checkDirectory(r11), r13);
        this.musicSize = r144;
        this.musicSize = r144 + getDirectorySize(FileLoader.checkDirectory(5), r13);
        goto L112
    L114:
        r18 = 5;
        if (r12 != 0) goto L119;
        long r145 = getDirectorySize(FileLoader.checkDirectory(0), r13);
        this.photoSize = r145;
        this.photoSize = r145 + getDirectorySize(FileLoader.checkDirectory(100), r13);
        r8 = 1;
    L117:
        r15 = 4;
        goto L124
    L119:
        if (r12 != r10) goto L122;
        long r146 = getDirectorySize(FileLoader.checkDirectory(r10), r13);
        this.videoSize = r146;
        this.videoSize = r146 + getDirectorySize(FileLoader.checkDirectory(101), r13);
        goto L117
    L122:
        if (r12 != 100) goto L117;
        r15 = 4;
        this.stickersCacheSize = getDirectorySize(new File(FileLoader.checkDirectory(4), "acache"), r13);
        long r122 = getDirectorySize(FileLoader.checkDirectory(4), r11);
        this.cacheEmojiSize = r122;
        this.stickersCacheSize += r122;
        r8 = 1;
    L84:
        if (r12 != 0) goto L86;
        int r147 = 100;
    L87:
        File r148 = FileLoader.checkDirectory(r147);
        if (r148 == null) goto L90;
        cleanDirJava(r148.getAbsolutePath(), r13, null, r9);
    L90:
        r16 = 0;
        r5[0] = r5[0] + 1;
        r36.run();
        goto L91
    L86:
        r147 = 101;
        goto L87
    L69:
        if (r12 != 100) goto L71;
        r22 = new File(FileLoader.checkDirectory(r19), "acache");
        goto L72
    L71:
        r22 = FileLoader.checkDirectory(r12);
        goto L72
    L33:
        if (r45 != r2) goto L35;
        r6 = r6 + this.videoSize;
        r15 = r12;
        r18 = r13;
        r13 = 0;
        r14 = 9;
        r12 = r10;
        goto L57
    L35:
        if (r45 != r10) goto L38;
        r6 = r6 + this.documentsSize;
        r15 = r12;
        r18 = r13;
        r14 = 9;
        r13 = r2;
    L37:
        r12 = r11;
        goto L57
    L38:
        if (r45 != r11) goto L40;
        r6 = r6 + this.musicSize;
        r15 = r12;
        r18 = r13;
        r14 = 9;
        r13 = r10;
        goto L37
    L40:
        if (r45 != r12) goto L42;
        r6 = r6 + this.audioSize;
        r15 = r12;
        r18 = r13;
        r13 = 0;
        r14 = 9;
        r12 = r2;
        goto L57
    L42:
        if (r45 != r13) goto L45;
        r6 = r6 + this.storiesSize;
        r15 = r12;
        r18 = r13;
        r12 = 6;
        goto L31
    L45:
        if (r45 != 6) goto L48;
        r6 = r6 + this.stickersCacheSize;
        r15 = r12;
        r18 = r13;
        r12 = 100;
        goto L31
    L48:
        if (r45 != 7) goto L51;
        r6 = r6 + this.cacheSize;
        r15 = r12;
        r18 = r13;
        goto L32
    L51:
        if (r45 != 8) goto L53;
        r6 = r6 + this.cacheTempSize;
        r15 = r12;
        r18 = r13;
        r14 = 9;
        r13 = r15;
        goto L57
    L53:
        r14 = 9;
        r15 = r12;
        r18 = r13;
        if (r45 != 9) goto L56;
        r6 = r6 + this.logsSize;
        r13 = r2;
        r12 = 0;
        goto L57
    L56:
        r12 = -1;
        r13 = 0;
        goto L57
    L125:
        long r26 = ((((((((this.cacheSize + this.cacheTempSize) + this.logsSize) + this.videoSize) + this.audioSize) + this.photoSize) + this.documentsSize) + this.musicSize) + this.stickersCacheSize) + this.storiesSize;
        lastTotalSizeCalculated = Long.valueOf(r26);
        this.totalSize = r26;
        lastTotalSizeCalculatedTime = System.currentTimeMillis();
        Arrays.fill(this.selected, true);
        if (BuildVars.LOGS_ENABLED == false) goto L128;
        FileLog.cleanupLogs();
    L128:
        StatFs r27 = new StatFs(Environment.getDataDirectory().getPath());
        long r37 = r27.getBlockSizeLong();
        long r92 = r27.getAvailableBlocksLong();
        this.totalDeviceSize = r27.getBlockCountLong() * r37;
        this.totalDeviceFreeSize = r92 * r37;
        if (r17 == false) goto L131;
        FileLoader.getInstance(this.currentAccount).clearFilePaths();
    L131:
        FileLoader.getInstance(this.currentAccount).checkCurrentDownloadsFiles();
        AndroidUtilities.runOnUIThread(new CacheControlActivity$$ExternalSyntheticLambda25(this, r8, r6, r24));
        return;
    L21:
        r44 = 0;
        goto L22
    L17:
        r43 = 0;
        goto L18
    L13:
        r42 = 0;
        goto L14
    L9:
        r4 = 0;
        goto L10
    L5:
        r3 = 0;
        goto L7
    }

    private void clearDatabase(boolean r6) {
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        r0.setTitle(LocaleController.getString(R.string.LocalDatabaseClearTextTitle));
        SpannableStringBuilder r1 = new SpannableStringBuilder();
        r1.append(LocaleController.getString(R.string.LocalDatabaseClearText));
        r1.append("\n\n");
        r1.append(AndroidUtilities.replaceTags(LocaleController.formatString("LocalDatabaseClearText2", R.string.LocalDatabaseClearText2, new Object[]{AndroidUtilities.formatFileSize(this.databaseSize)})));
        r0.setMessage(r1);
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r0.setPositiveButton(LocaleController.getString(R.string.CacheClear), new CacheControlActivity$$ExternalSyntheticLambda13(this, r6));
        AlertDialog r62 = r0.create();
        showDialog(r62);
        TextView r5 = (TextView) r62.getButton(-1);
        if (r5 == null) goto L6;
        r5.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    }

    private void clearSelectedFiles() {
        if (this.cacheModel.getSelectedFiles() != 0) goto L5;
        return;
    L5:
        if (getParentActivity() == null) goto L13;
        AlertDialog.Builder r0 = new AlertDialog.Builder(getContext());
        r0.setTitle(LocaleController.getString(R.string.ClearCache));
        r0.setMessage(LocaleController.getString(R.string.ClearCacheForChats));
        r0.setPositiveButton(LocaleController.getString(R.string.Clear), new CacheControlActivity$$ExternalSyntheticLambda11(this));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        AlertDialog r02 = r0.create();
        showDialog(r02);
        TextView r3 = (TextView) r02.getButton(-1);
        if (r3 == null) goto L12;
        r3.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L12:
        return;
    }

    public static int countDirJava(String r11, int r12) {
        File r0 = new File(r11);
        if (r0.exists() == false) goto L63;
        File[] r02 = r0.listFiles();
        if (r02 == null) goto L63;
        int r1 = 0;
        int r3 = 0;
    L9:
        if (r1 >= r02.length) goto L62;
        File r4 = r02[r1];
        String r5 = r4.getName();
        if (".".equals(r5) == true) goto L61;
        if (r12 <= 0) goto L58;
        if (r5.length() < 4) goto L58;
        String r6 = r5.toLowerCase();
        boolean r8 = true;
        if (r6.endsWith(".mp3") == false) goto L19;
    L22:
        boolean r7 = true;
    L24:
        if (r6.endsWith(".tgs") == false) goto L26;
    L29:
        boolean r9 = true;
    L31:
        if (r6.endsWith(".tmp") == false) goto L33;
    L38:
        if (r7 == true) goto L40;
    L41:
        if (r7 == false) goto L43;
    L44:
        if (r9 == true) goto L46;
    L47:
        if (r9 == false) goto L49;
    L50:
        if (r8 == true) goto L52;
    L53:
        if (r8 == true) goto L58;
        if (r12 != LISTDIR_DOCTYPE2_TEMP) goto L58;
    L52:
        if (r12 == LISTDIR_DOCTYPE2_OTHER) goto L61;
    L49:
        if (r12 == LISTDIR_DOCTYPE2_EMOJI) goto L61;
    L46:
        if (r12 == LISTDIR_DOCTYPE2_OTHER) goto L61;
    L43:
        if (r12 == LISTDIR_DOCTYPE_MUSIC) goto L61;
    L40:
        if (r12 == LISTDIR_DOCTYPE_OTHER_THAN_MUSIC) goto L61;
    L33:
        if (r6.endsWith(".temp") == true) goto L38;
        if (r6.endsWith(".preload") == true) goto L38;
        r8 = false;
        goto L38
    L26:
        if (r6.endsWith(".webm") == true) goto L29;
        r9 = false;
        goto L31
    L19:
        if (r6.endsWith(".m4a") == true) goto L22;
        r7 = false;
    L58:
        if (r4.isDirectory() == false) goto L60;
        r3 = r3 + countDirJava(r11 + "/" + r5, r12);
        goto L61
    L60:
        r3 = r3 + 1;
    L61:
        r1 = r1 + 1;
        goto L9
    L62:
        return r3;
    L63:
        return 0;
    }

    private String formatPercent(float r2) {
        return formatPercent(r2, true);
    }

    private String formatPercent(float r1, boolean r2) {
        if (r2 == true) goto L4;
    L7:
        float r0 = Math.round(r1 * 100.0f);
        if (r2 == false) goto L14;
        if (r0 > 0.0f) goto L14;
        return String.format("<%d%%", new Object[]{1});
    L14:
        return String.format("%d%%", new Object[]{Integer.valueOf((int) r0)});
    L4:
        if (r1 >= 0.001f) goto L7;
        return String.format("<%.1f%%", new Object[]{Float.valueOf(0.1f)});
    }

    private CharSequence getCheckBoxTitle(CharSequence r3, int r4, boolean r5) {
        if (r4 > 0) goto L4;
        String r2 = String.format("<%.1f%%", new Object[]{Float.valueOf(1.0f)});
    L5:
        SpannableString r42 = new SpannableString(r2);
        r42.setSpan(new RelativeSizeSpan(0.834f), 0, r42.length(), 33);
        r42.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r42.length(), 33);
        SpannableStringBuilder r22 = new SpannableStringBuilder(r3);
        r22.append("  ");
        r22.append(r42);
        return r22;
    L4:
        r2 = String.format("%d%%", new Object[]{Integer.valueOf(r4)});
        goto L5
    }

    public static void getDeviceTotalSize(Utilities.Callback2<Long, Long> r2) {
        Long r0 = lastDeviceTotalSize;
        if (r0 == null) goto L9;
        Long r1 = lastDeviceTotalFreeSize;
        if (r1 == null) goto L9;
        if (r2 == null) goto L11;
        r2.run(r0, r1);
        return;
    L11:
        return;
    L9:
        Utilities.cacheClearQueue.postRunnable(new CacheControlActivity$$ExternalSyntheticLambda8(r2));
    }

    private static long getDirectorySize(File r3, int r4) {
        if (r3 != null) goto L5;
    L15:
        return 0;
    L5:
        if (canceled == true) goto L15;
        if (r3.isDirectory() == false) goto L12;
        return Utilities.getDirSize(r3.getAbsolutePath(), r4, false);
    L12:
        if (r3.isFile() == false) goto L15;
        return r3.length();
    }

    private int getTypeByPath(String r4) {
        if (pathContains(r4, 6) == false) goto L7;
        return 7;
    L7:
        if (pathContains(r4, 0) == false) goto L10;
        return 0;
    L10:
        if (pathContains(r4, 100) == false) goto L13;
        return 0;
    L13:
        if (pathContains(r4, 2) == false) goto L16;
        return 1;
    L16:
        if (pathContains(r4, 101) == false) goto L18;
        return 1;
    L18:
        return 6;
    }

    private boolean isAllSectionsSelected() {
        int r1 = 0;
    L4:
        if (r1 >= this.itemInners.size()) goto L15;
        ItemInner r2 = this.itemInners.get(r1);
        if (r2.viewType != 11) goto L14;
        int r22 = r2.index;
        if (r22 >= 0) goto L12;
        r22 = this.selected.length - 1;
    L12:
        if (this.selected[r22] == true) goto L14;
        return false;
    L14:
        r1 = r1 + 1;
        goto L4
    L15:
        return true;
    }

    private boolean isOtherSelected() {
        int r0 = this.selected.length;
        boolean[] r1 = new boolean[r0];
        int r3 = 0;
    L4:
        if (r3 >= this.itemInners.size()) goto L13;
        ItemInner r4 = this.itemInners.get(r3);
        if (r4.viewType != 11) goto L12;
        if (r4.pad == true) goto L12;
        int r42 = r4.index;
        if (r42 < 0) goto L12;
        r1[r42] = true;
    L12:
        r3 = r3 + 1;
        goto L4
    L13:
        int r32 = 0;
    L14:
        if (r32 >= r0) goto L21;
        if (r1[r32] == true) goto L20;
        if (this.selected[r32] == true) goto L20;
        return false;
    L20:
        r32 = r32 + 1;
        goto L14
    L21:
        return true;
    }

    private /* synthetic */ void lambda$cleanupDialogFiles$22(AlertDialog r1) {
        FileLoader.getInstance(this.currentAccount).checkCurrentDownloadsFiles();
        r1.dismiss();     // Catch: Exception -> L5
    L7:
        PillStackConfig.notifySettingsChanged(new int[]{PillType.CACHE.getId()});
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
        goto L7
    }

    private /* synthetic */ void lambda$cleanupDialogFiles$23(ArrayList r3, AlertDialog r4) {
        int r0 = 0;
    L4:
        if (r0 >= r3.size()) goto L6;
        ((CacheModel.FileInfo) r3.get(r0)).file.delete();
        r0 = r0 + 1;
        goto L4
    L6:
        AndroidUtilities.runOnUIThread(new CacheControlActivity$$ExternalSyntheticLambda17(this, r4));
    }

    private /* synthetic */ void lambda$cleanupFolders$11(Utilities.Callback2 r1, Runnable r2) {
        cleanupFoldersInternal(r1, r2);
    }

    private /* synthetic */ void lambda$cleanupFolders$12(Utilities.Callback2 r3, Runnable r4) {
        Utilities.globalQueue.postRunnable(new CacheControlActivity$$ExternalSyntheticLambda22(this, r3, r4));
    }

    private /* synthetic */ void lambda$cleanupFoldersInternal$15(long r3) {
        Bulletin r2 = BulletinFactory.of(this).createSimpleBulletin(R.raw.ic_delete, LocaleController.formatString(R.string.CacheWasCleared, new Object[]{AndroidUtilities.formatFileSize(r3)}));
        r2.hideAfterBottomSheet = false;
        r2.show();
    }

    private /* synthetic */ void lambda$cleanupFoldersInternal$16(boolean r1, long r2, Runnable r4) {
        if (r1 == false) goto L14;
        ImageLoader.getInstance().clearMemory();
    L14:
        AlertDialog r12 = this.progressDialog;     // Catch: Exception -> L8
        if (r12 == null) goto L10;
        r12.dismiss();     // Catch: Exception -> L8
        this.progressDialog = null;     // Catch: Exception -> L8
    L10:
        getMediaDataController().ringtoneDataStore.checkRingtoneSoundsLoaded();
        AndroidUtilities.runOnUIThread(new CacheControlActivity$$ExternalSyntheticLambda0(this, r2), 150);
        MediaDataController.getInstance(this.currentAccount).checkAllMedia(true);
        loadDialogEntities();
        PillStackConfig.notifySettingsChanged(new int[]{PillType.CACHE.getId()});
        if (r4 == null) goto L16;
        r4.run();
        return;
    L16:
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
        goto L10
    }

    private /* synthetic */ void lambda$clearDatabase$24(boolean r3, AlertDialog r4, int r5) {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        AlertDialog r42 = new AlertDialog(getParentActivity(), 3);
        this.progressDialog = r42;
        r42.setCanCancel(false);
        this.progressDialog.showDelayed(500);
        MessagesController.getInstance(this.currentAccount).clearQueryTime();
        if (r3 == false) goto L9;
        getMessagesStorage().fullReset();
        return;
    L9:
        getMessagesStorage().clearLocalDatabase();
    }

    private /* synthetic */ void lambda$clearSelectedFiles$20(AlertDialog r5, int r6) {
        DialogFileEntities r52 = this.cacheModel.removeSelectedFiles();
        if (r52.totalSize <= 0) goto L5;
        cleanupDialogFiles(r52, null, null);
    L5:
        this.cacheModel.clearSelection();
        CachedMediaLayout r53 = this.cachedMediaLayout;
        if (r53 == null) goto L8;
        r53.update();
        this.cachedMediaLayout.showActionMode(false);
    L8:
        updateRows();
        updateChart();
    }

    private /* synthetic */ void lambda$createView$17(View r1) {
        clearSelectedFiles();
    }

    private /* synthetic */ void lambda$createView$18(int r1, int r2) {
        AndroidUtilities.updateVisibleRows(this.listView);
    }

    private /* synthetic */ void lambda$createView$19(View r4, int r5, float r6, float r7) {
        if (getParentActivity() == null) goto L29;
        if (r5 >= 0) goto L7;
        return;
    L7:
        if (r5 >= this.itemInners.size()) goto L30;
        ItemInner r0 = this.itemInners.get(r5);
        if (r0.viewType == 11) goto L12;
    L19:
        DialogFileEntities r1 = r0.entities;
        if (r1 == null) goto L24;
        showClearCacheDialog(r1);
        return;
    L24:
        if (r0.keepMediaType < 0) goto L28;
        KeepMediaPopupView r02 = new KeepMediaPopupView(this, r4.getContext());
        ActionBarPopupWindow r42 = AlertsCreator.createSimplePopup(this, r02, r4, r6, r7);
        r02.update(this.itemInners.get(r5).keepMediaType);
        r02.setParentWindow(r42);
        r02.setCallback(new CacheControlActivity$$ExternalSyntheticLambda9(this));
        return;
    L28:
        return;
    L12:
        if ((r4 instanceof CheckBoxCell) == false) goto L19;
        if (r0.index >= 0) goto L17;
        this.collapsed = !this.collapsed;
        updateRows();
        updateChart();
        return;
    L17:
        toggleSection(r0, r4);
        return;
    L30:
        return;
    }

    private /* synthetic */ void lambda$getThemeDescriptions$25() {
        BottomSheet r0 = this.bottomSheet;
        if (r0 == null) goto L5;
        r0.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground));
    L5:
        View r4 = this.actionTextView;
        if (r4 == null) goto L9;
        r4.setBackground(Theme.AdaptiveRipple.filledRectByKey(Theme.key_featuredStickers_addButton, new float[]{4.0f}));
        return;
    }

    private /* synthetic */ void lambda$loadDialogEntities$6(ArrayList r7, ArrayList r8, ArrayList r9, CacheModel r10) {
        boolean r0 = false;
        this.loadingDialogs = false;
        getMessagesController().putUsers(r7, true);
        getMessagesController().putChats(r8, true);
        DialogFileEntities r72 = null;
        int r82 = 0;
    L4:
        if (r82 >= r9.size()) goto L14;
        DialogFileEntities r1 = (DialogFileEntities) r9.get(r82);
        if (getMessagesController().getUserOrChat(r1.dialogId) != null) goto L13;
        r1.dialogId = LongCompanionObject.MAX_VALUE;
        if (r72 == null) goto L10;
        r72.merge(r1);
        r9.remove(r82);
        r82 = r82 - 1;
        boolean r12 = true;
    L11:
        if (r12 == false) goto L13;
        sort(r9);
        goto L13
    L10:
        r72 = r1;
        r12 = false;
    L13:
        r82 = r82 + 1;
        goto L4
    L14:
        r10.setEntities(r9);
        if (canceled == true) goto L41;
        setCacheModel(r10);
        updateRows();
        updateChart();
        if (this.cacheChartHeader != null) goto L19;
        return;
    L19:
        if (this.calculating == false) goto L21;
        return;
    L21:
        if ((System.currentTimeMillis() - this.fragmentCreateTime) <= 120) goto L44;
        CacheChartHeader r73 = this.cacheChartHeader;
        long r83 = this.totalSize;
        if (r83 <= 0) goto L25;
        r0 = true;
    L25:
        long r13 = this.totalDeviceSize;
        float r5 = 0.0f;
        if (r13 > 0) goto L28;
        float r84 = 0.0f;
    L30:
        if (this.totalDeviceFreeSize > 0) goto L32;
    L35:
        r73.setData(r0, r84, r5);
        return;
    L32:
        if (r13 <= 0) goto L35;
        r5 = (r13 - r9) / r13;
        goto L35
    L28:
        r84 = r83 / r13;
        goto L30
    L44:
        return;
    }

    private /* synthetic */ void lambda$loadDialogEntities$7(ArrayList r7, ArrayList r8, ArrayList r9, CacheModel r10) {
        ArrayList<TLRPC.User> r2 = new ArrayList();
        ArrayList<TLRPC.Chat> r3 = new ArrayList();
        if (r7.isEmpty() == true) goto L9;
        getMessagesStorage().getUsersInternal(r7, r2);     // Catch: Exception -> L6
    L6:
        e = move-exception;
        FileLog.e(e);
    L9:
        if (r8.isEmpty() == false) goto L25;
    L14:
        int r72 = 0;
    L16:
        if (r72 >= r9.size()) goto L21;
        if (((DialogFileEntities) r9.get(r72)).totalSize > 0) goto L20;
        r9.remove(r72);
        r72 = r72 - 1;
    L20:
        r72 = r72 + 1;
        goto L16
    L21:
        sort(r9);
        AndroidUtilities.runOnUIThread(new CacheControlActivity$$ExternalSyntheticLambda19(this, r2, r3, r9, r10));
        return;
    L25:
        getMessagesStorage().getChatsInternal(TextUtils.join(",", r8), r3);     // Catch: Exception -> L12
    L12:
        e = move-exception;
        FileLog.e(e);
        goto L14
    }

    private /* synthetic */ void lambda$loadDialogEntities$8() {
        getFileLoader().getFileDatabase().ensureDatabaseCreated();
        int r0 = 0;
        CacheModel r6 = new CacheModel(false);
        LongSparseArray<DialogFileEntities> r1 = new LongSparseArray();
        fillDialogsEntitiesRecursive(FileLoader.checkDirectory(4), 6, r1, r6);
        fillDialogsEntitiesRecursive(FileLoader.checkDirectory(0), 0, r1, r6);
        fillDialogsEntitiesRecursive(FileLoader.checkDirectory(100), 0, r1, r6);
        fillDialogsEntitiesRecursive(FileLoader.checkDirectory(2), 1, r1, r6);
        fillDialogsEntitiesRecursive(FileLoader.checkDirectory(101), 1, r1, r6);
        fillDialogsEntitiesRecursive(FileLoader.checkDirectory(1), 4, r1, r6);
        fillDialogsEntitiesRecursive(FileLoader.checkDirectory(6), 6, r1, r6);
        fillDialogsEntitiesRecursive(FileLoader.checkDirectory(3), 2, r1, r6);
        fillDialogsEntitiesRecursive(FileLoader.checkDirectory(5), 2, r1, r6);
        ArrayList r5 = new ArrayList();
        ArrayList r3 = new ArrayList();
        ArrayList r4 = new ArrayList();
    L4:
        if (r0 >= r1.size()) goto L12;
        DialogFileEntities r2 = r1.valueAt(r0);
        r5.add(r2);
        if (getMessagesController().getUserOrChat(((DialogFileEntities) r5.get(r0)).dialogId) != null) goto L11;
        long r7 = r2.dialogId;
        if (r7 <= 0) goto L10;
        r3.add(Long.valueOf(r7));
        goto L11
    L10:
        r4.add(Long.valueOf(r7));
    L11:
        r0 = r0 + 1;
        goto L4
    L12:
        r6.sortBySize();
        getMessagesStorage().getStorageQueue().postRunnable(new CacheControlActivity$$ExternalSyntheticLambda18(this, r3, r4, r5, r6));
    }

    private /* synthetic */ void lambda$onFragmentCreate$4() {
        resumeDelayedFragmentAnimation();
        this.calculating = false;
        updateRows(true);
        updateChart();
    }

    private /* synthetic */ void lambda$onFragmentCreate$5() {
        this.cacheSize = getDirectorySize(FileLoader.checkDirectory(4), 5);
        if (canceled == true) goto L54;
        this.cacheTempSize = getDirectorySize(FileLoader.checkDirectory(4), 4);
        if (canceled == true) goto L55;
        int r1 = 0;
        long r3 = getDirectorySize(FileLoader.checkDirectory(0), 0);
        this.photoSize = r3;
        this.photoSize = r3 + getDirectorySize(FileLoader.checkDirectory(100), 0);
        if (canceled == true) goto L56;
        long r4 = getDirectorySize(FileLoader.checkDirectory(2), 0);
        this.videoSize = r4;
        this.videoSize = r4 + getDirectorySize(FileLoader.checkDirectory(101), 0);
        if (canceled == true) goto L57;
        long r6 = getDirectorySize(AndroidUtilities.getLogsDir(), 1);
        this.logsSize = r6;
        if (BuildVars.DEBUG_VERSION == true) goto L20;
        if (r6 >= 268435456) goto L20;
        this.logsSize = 0;
    L20:
        if (canceled == true) goto L58;
        long r62 = getDirectorySize(FileLoader.checkDirectory(3), 1);
        this.documentsSize = r62;
        this.documentsSize = r62 + getDirectorySize(FileLoader.checkDirectory(5), 1);
        if (canceled == true) goto L59;
        long r63 = getDirectorySize(FileLoader.checkDirectory(3), 2);
        this.musicSize = r63;
        this.musicSize = r63 + getDirectorySize(FileLoader.checkDirectory(5), 2);
        if (canceled == true) goto L60;
        this.stickersCacheSize = getDirectorySize(new File(FileLoader.checkDirectory(4), "acache"), 0);
        if (canceled == true) goto L61;
        this.cacheEmojiSize = getDirectorySize(FileLoader.checkDirectory(4), 3);
        if (canceled == true) goto L62;
        this.stickersCacheSize += this.cacheEmojiSize;
        this.audioSize = getDirectorySize(FileLoader.checkDirectory(1), 0);
        this.storiesSize = getDirectorySize(FileLoader.checkDirectory(6), 0);
        if (canceled == false) goto L37;
        return;
    L37:
        long r2 = ((((((((this.cacheSize + this.cacheTempSize) + this.videoSize) + this.logsSize) + this.audioSize) + this.photoSize) + this.documentsSize) + this.musicSize) + this.storiesSize) + this.stickersCacheSize;
        lastTotalSizeCalculated = Long.valueOf(r2);
        this.totalSize = r2;
        lastTotalSizeCalculatedTime = System.currentTimeMillis();
        ArrayList<File> r0 = AndroidUtilities.getRootDirs();
        File r22 = r0.get(0);
        r22.getAbsolutePath();
        if (TextUtils.isEmpty(SharedConfig.storageCacheDir) == true) goto L51;
        int r32 = r0.size();
    L40:
        if (r1 >= r32) goto L51;
        File r42 = r0.get(r1);
        if (r42.getAbsolutePath().startsWith(SharedConfig.storageCacheDir) == true) goto L43;
        r1 = r1 + 1;
        goto L40
    L43:
        r22 = r42;
    L51:
        StatFs r02 = new StatFs(r22.getPath());     // Catch: Exception -> L47
        long r12 = r02.getBlockSizeLong();     // Catch: Exception -> L47
        long r33 = r02.getAvailableBlocksLong();     // Catch: Exception -> L47
        this.totalDeviceSize = r02.getBlockCountLong() * r12;     // Catch: Exception -> L47
        this.totalDeviceFreeSize = r33 * r12;     // Catch: Exception -> L47
    L49:
        AndroidUtilities.runOnUIThread(new CacheControlActivity$$ExternalSyntheticLambda10(this));
        loadDialogEntities();
        return;
    L47:
        e = move-exception;
        FileLog.e(e);
        goto L49
    L62:
        return;
    L61:
        return;
    L60:
        return;
    L59:
        return;
    L58:
        return;
    L57:
        return;
    L56:
        return;
    L55:
        return;
    }

    private /* synthetic */ void lambda$updateActionBar$21(ValueAnimator r4) {
        this.actionBarShownT = ((Float) r4.getAnimatedValue()).floatValue();
        this.actionBar.setTitleColor(ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText), (int) (this.actionBarShownT * 255.0f)));
        this.actionBar.setBackgroundColor(ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_windowBackgroundWhite), (int) (this.actionBarShownT * 255.0f)));
        this.fragmentView.invalidate();
    }

    private void loadDialogEntities() {
        getFileLoader().getFileDatabase().getQueue().postRunnable(new CacheControlActivity$$ExternalSyntheticLambda12(this));
    }

    private boolean pathContains(String r1, int r2) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (FileLoader.checkDirectory(r2) != null) goto L7;
        return false;
    L7:
        return r1.contains(FileLoader.checkDirectory(r2).getAbsolutePath());
    }

    public static void resetCalculatedTotalSIze() {
        lastTotalSizeCalculated = null;
        lastDeviceTotalSize = null;
        lastDeviceTotalFreeSize = null;
    }

    private int sectionsSelected() {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r0 >= 10) goto L11;
        if (this.selected[r0] == false) goto L10;
        if (size(r0) <= 0) goto L10;
        r1 = r1 + 1;
    L10:
        r0 = r0 + 1;
        goto L4
    L11:
        return r1;
    }

    private void showClearCacheDialog(DialogFileEntities r5) {
        if (this.totalSize > 0) goto L5;
        return;
    L5:
        if (getParentActivity() == null) goto L10;
        DialogCacheBottomSheet r0 = new DialogCacheBottomSheet(this, r5, r5.createCacheModel(), new AnonymousClass6(this, r5));
        this.bottomSheet = r0;
        showDialog(r0);
        return;
    }

    private long size(int r1) {
        switch(r1) {
            case 0: goto L24;
            case 1: goto L22;
            case 2: goto L20;
            case 3: goto L18;
            case 4: goto L16;
            case 5: goto L14;
            case 6: goto L12;
            case 7: goto L10;
            case 8: goto L8;
            case 9: goto L6;
            default: goto L3;
        };
    L3:
        return 0;
    L6:
        return this.logsSize;
    L8:
        return this.cacheTempSize;
    L10:
        return this.cacheSize;
    L12:
        return this.stickersCacheSize;
    L14:
        return this.storiesSize;
    L16:
        return this.audioSize;
    L18:
        return this.musicSize;
    L20:
        return this.documentsSize;
    L22:
        return this.videoSize;
    L24:
        return this.photoSize;
    }

    private void sort(ArrayList<DialogFileEntities> r1) {
        Collections.sort(r1, new CacheControlActivity$$ExternalSyntheticLambda21());
    }

    private void toggleOtherSelected(View r9) {
        boolean r0 = isOtherSelected();
        int r2 = 0;
        if (r0 == false) goto L22;
        int r3 = 0;
    L6:
        if (r3 >= this.itemInners.size()) goto L17;
        ItemInner r4 = this.itemInners.get(r3);
        if (r4.viewType != 11) goto L16;
        if (r4.pad == true) goto L16;
        int r42 = r4.index;
        if (r42 < 0) goto L16;
        if (this.selected[r42] == true) goto L22;
    L16:
        r3 = r3 + 1;
        goto L6
    L17:
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        if (r9 == null) goto L91;
        AndroidUtilities.shakeViewSpring(r9, -3.0f);
        return;
    L91:
        return;
    L22:
        if (this.collapsed == false) goto L40;
        int r92 = this.selected.length;
        boolean[] r43 = new boolean[r92];
        int r5 = 0;
    L25:
        if (r5 >= this.itemInners.size()) goto L34;
        ItemInner r6 = this.itemInners.get(r5);
        if (r6.viewType != 11) goto L33;
        if (r6.pad == true) goto L33;
        int r62 = r6.index;
        if (r62 < 0) goto L33;
        r43[r62] = true;
    L33:
        r5 = r5 + 1;
        goto L25
    L34:
        int r52 = 0;
    L35:
        if (r52 >= r92) goto L52;
        if (r43[r52] == true) goto L39;
        this.selected[r52] = !r0;
    L39:
        r52 = r52 + 1;
    L52:
        if (r2 >= this.listView.getChildCount()) goto L64;
        View r93 = this.listView.getChildAt(r2);
        if ((r93 instanceof CheckBoxCell) == false) goto L63;
        int r44 = this.listView.getChildAdapterPosition(r93);
        if (r44 < 0) goto L63;
        ItemInner r45 = this.itemInners.get(r44);
        if (r45.viewType != 11) goto L63;
        int r46 = r45.index;
        if (r46 >= 0) goto L62;
        ((CheckBoxCell) r93).setChecked(!r0, true);
        goto L63
    L62:
        ((CheckBoxCell) r93).setChecked(this.selected[r46], true);
    L63:
        r2 = r2 + 1;
        goto L52
    L64:
        updateChart();
        return;
    L40:
        int r94 = 0;
    L42:
        if (r94 >= this.itemInners.size()) goto L52;
        ItemInner r47 = this.itemInners.get(r94);
        if (r47.viewType != 11) goto L50;
        if (r47.pad == false) goto L50;
        int r48 = r47.index;
        if (r48 < 0) goto L50;
        this.selected[r48] = !r0;
    L50:
        r94 = r94 + 1;
        goto L42
    }

    private void toggleSection(ItemInner r7, View r8) {
        int r0 = r7.index;
        if (r0 >= 0) goto L7;
        toggleOtherSelected(r8);
        return;
    L7:
        if (this.selected[r0] == true) goto L9;
    L14:
        boolean r02 = r8 instanceof CheckBoxCell;
        boolean[] r2 = this.selected;
        int r3 = 0;
        if (r02 == false) goto L17;
        int r03 = r7.index;
        boolean r4 = !r2[r03];
        r2[r03] = r4;
        ((CheckBoxCell) r8).setChecked(r4, true);
    L29:
        if (r7.pad == true) goto L31;
    L42:
        updateChart();
        return;
    L31:
        if (r3 >= this.listView.getChildCount()) goto L42;
        View r72 = this.listView.getChildAt(r3);
        if ((r72 instanceof CheckBoxCell) == false) goto L41;
        int r82 = this.listView.getChildAdapterPosition(r72);
        if (r82 < 0) goto L41;
        if (r82 >= this.itemInners.size()) goto L41;
        if (this.itemInners.get(r82).index >= 0) goto L41;
        ((CheckBoxCell) r72).setChecked(isOtherSelected(), true);
    L41:
        r3 = r3 + 1;
        goto L31
    L17:
        r2[r7.index] = !r2[r8];
        int r83 = this.itemInners.indexOf(r7);
        if (r83 < 0) goto L29;
        int r04 = 0;
    L21:
        if (r04 >= this.listView.getChildCount()) goto L29;
        View r22 = this.listView.getChildAt(r04);
        if ((r22 instanceof CheckBoxCell) == false) goto L27;
        if (r83 != this.listView.getChildAdapterPosition(r22)) goto L27;
        ((CheckBoxCell) r22).setChecked(this.selected[r7.index], true);
    L27:
        r04 = r04 + 1;
        goto L21
    L9:
        if (sectionsSelected() > 1) goto L14;
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        if (r8 == null) goto L54;
        AndroidUtilities.shakeViewSpring(r8, -3.0f);
        return;
    }

    private void updateActionBar(boolean r4) {
        if (r4 == this.actionBarShown) goto L13;
        ValueAnimator r0 = this.actionBarAnimator;
        if (r0 == null) goto L7;
        r0.cancel();
    L7:
        float r02 = this.actionBarShownT;
        this.actionBarShown = r4;
        if (r4 == false) goto L10;
        float r42 = 1.0f;
    L11:
        ValueAnimator r43 = ValueAnimator.ofFloat(new float[]{r02, r42});
        this.actionBarAnimator = r43;
        r43.addUpdateListener(new CacheControlActivity$$ExternalSyntheticLambda14(this));
        this.actionBarAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.actionBarAnimator.setDuration(380);
        this.actionBarAnimator.start();
        return;
    L10:
        r42 = 0.0f;
        goto L11
    }

    private void updateActionMode() {
        int r0 = this.cacheModel.getSelectedFiles();
        CachedMediaLayout r1 = this.cachedMediaLayout;
        int r2 = 0;
        if (r0 <= 0) goto L19;
        if (r1 == null) goto L26;
        boolean r02 = this.cacheModel.selectedDialogs.isEmpty();
        CacheModel r12 = this.cacheModel;
        if (r02 == true) goto L16;
        ArrayList<DialogFileEntities> r03 = r12.entities;
        int r13 = r03.size();
        int r4 = 0;
    L8:
        if (r4 >= r13) goto L12;
        DialogFileEntities r5 = r03.get(r4);
        r4 = r4 + 1;
        DialogFileEntities r52 = r5;
        if (this.cacheModel.selectedDialogs.contains(Long.valueOf(r52.dialogId)) == false) goto L8;
        r2 = r2 + r52.filesCount;
        goto L8
    L12:
        int r04 = this.cacheModel.getSelectedFiles() - r2;
        CacheModel r14 = this.cacheModel;
        if (r04 <= 0) goto L15;
        String r05 = String.format("%s, %s", new Object[]{LocaleController.formatPluralString("Chats", r14.selectedDialogs.size(), new Object[]{Integer.valueOf(this.cacheModel.selectedDialogs.size())}), LocaleController.formatPluralString("Files", r04, new Object[]{Integer.valueOf(r04)})});
    L17:
        this.actionModeTitle.setText(AndroidUtilities.formatFileSize(this.cacheModel.getSelectedFilesSize()), !LocaleController.isRTL);
        this.actionModeSubtitle.setText(r05, !LocaleController.isRTL);
        this.cachedMediaLayout.showActionMode(true);
        return;
    L15:
        r05 = LocaleController.formatPluralString("Chats", r14.selectedDialogs.size(), new Object[]{Integer.valueOf(this.cacheModel.selectedDialogs.size())});
        goto L17
    L16:
        r05 = LocaleController.formatPluralString("Files", r12.getSelectedFiles(), new Object[]{Integer.valueOf(this.cacheModel.getSelectedFiles())});
        goto L17
    L26:
        return;
    L19:
        r1.showActionMode(false);
    }

    private void updateChart() {
        CacheChart r0 = this.cacheChart;
        if (r0 == null) goto L27;
        boolean r1 = this.calculating;
        int r4 = 0;
        if (r1 == false) goto L7;
    L24:
        if (r1 == false) goto L26;
        r0.setSegments(-1, true, new CacheChart.SegmentSize[0]);
        goto L27
    L26:
        r0.setSegments(0, true, new CacheChart.SegmentSize[0]);
        goto L27
    L7:
        if (this.totalSize <= 0) goto L24;
        CacheChart.SegmentSize[] r12 = new CacheChart.SegmentSize[11];
    L10:
        if (r4 >= this.itemInners.size()) goto L21;
        ItemInner r2 = this.itemInners.get(r4);
        if (r2.viewType != 11) goto L19;
        int r3 = r2.index;
        if (r3 < 0) goto L16;
        r12[r3] = CacheChart.SegmentSize.of(r2.size, this.selected[r3]);
        goto L19
    L16:
        if (this.collapsed == false) goto L19;
        r12[10] = CacheChart.SegmentSize.of(r2.size, this.selected[10]);
    L19:
        r4 = r4 + 1;
        goto L10
    L21:
        if ((System.currentTimeMillis() - this.fragmentCreateTime) >= 80) goto L23;
        this.cacheChart.loadingFloat.set(0.0f, true);
    L23:
        this.cacheChart.setSegments(this.totalSize, true, r12);
    L27:
        ClearCacheButtonInternal r02 = this.clearCacheButton;
        if (r02 != null) goto L30;
        return;
    L30:
        if (this.calculating == true) goto L39;
        r02.updateSize();
        return;
    }

    private void updateDatabaseItemSize() {
        if (this.clearDatabaseItem == null) goto L6;
        SpannableStringBuilder r0 = new SpannableStringBuilder();
        r0.append(LocaleController.getString(R.string.ClearLocalDatabase));
        this.clearDatabaseItem.setText(r0);
        return;
    }

    private void updateRows() {
        updateRows(true);
    }

    private void updateRows(boolean r21) {
        if (r21 == true) goto L5;
    L7:
        boolean r2 = r21;
    L8:
        this.oldItems.clear();
        this.oldItems.addAll(this.itemInners);
        this.itemInners.clear();
        this.itemInners.add(new ItemInner(9, null, null));
        this.itemInners.add(new ItemInner(10, null, null));
        this.sectionsStartRow = this.itemInners.size();
        if (this.calculating == false) goto L11;
        this.itemInners.add(new ItemInner(12, null, null));
        this.itemInners.add(new ItemInner(12, null, null));
        this.itemInners.add(new ItemInner(12, null, null));
        this.itemInners.add(new ItemInner(12, null, null));
        this.itemInners.add(new ItemInner(12, null, null));
        boolean r3 = true;
        long r18 = 0;
    L67:
        if (r3 == false) goto L69;
        this.sectionsEndRow = this.itemInners.size();
        this.itemInners.add(new ItemInner(13, null, null));
        this.itemInners.add(ItemInner.asInfo(LocaleController.getString(R.string.StorageUsageInfo)));
    L70:
        this.itemInners.add(new ItemInner(3, LocaleController.getString(R.string.AutoDeleteCachedMedia), null));
        this.itemInners.add(new ItemInner(7, 0));
        this.itemInners.add(new ItemInner(7, 1));
        this.itemInners.add(new ItemInner(7, 2));
        this.itemInners.add(new ItemInner(7, 3));
        this.itemInners.add(ItemInner.asInfo(LocaleController.getString(R.string.KeepMediaInfoPart)));
        if (this.totalDeviceSize <= r18) goto L73;
        this.itemInners.add(new ItemInner(3, LocaleController.getString(R.string.MaxCacheSize), null));
        this.itemInners.add(new ItemInner(14, null));
        this.itemInners.add(ItemInner.asInfo(LocaleController.getString(R.string.MaxCacheSizeInfo)));
    L73:
        if (r3 == false) goto L79;
        CacheModel r1 = this.cacheModel;
        if (r1 == null) goto L79;
        if (r1.isEmpty() == true) goto L79;
        this.itemInners.add(new ItemInner(8, null, null));
    L79:
        ListAdapter r12 = this.listAdapter;
        if (r12 == null) goto L84;
        if (r2 == false) goto L83;
        r12.setItems(this.oldItems, this.itemInners);
        goto L84
    L83:
        r12.notifyDataSetChanged();
    L84:
        CachedMediaLayout r0 = this.cachedMediaLayout;
        if (r0 == null) goto L90;
        r0.update();
        return;
    L90:
        return;
    L69:
        this.sectionsEndRow = -1;
        goto L70
    L11:
        ArrayList r32 = new ArrayList();
        if (this.photoSize <= 0) goto L14;
        char r17 = '\n';
        r32.add(ItemInner.asCheckBox(LocaleController.getString(R.string.LocalPhotoCache), 0, this.photoSize, Theme.key_statisticChartLine_lightblue));
    L16:
        if (this.videoSize <= 0) goto L19;
        r32.add(ItemInner.asCheckBox(LocaleController.getString(R.string.LocalVideoCache), 1, this.videoSize, Theme.key_statisticChartLine_blue));
    L19:
        if (this.documentsSize <= 0) goto L22;
        r32.add(ItemInner.asCheckBox(LocaleController.getString(R.string.LocalDocumentCache), 2, this.documentsSize, Theme.key_statisticChartLine_green));
    L22:
        if (this.musicSize <= 0) goto L25;
        r32.add(ItemInner.asCheckBox(LocaleController.getString(R.string.LocalMusicCache), 3, this.musicSize, Theme.key_statisticChartLine_purple));
    L25:
        if (this.audioSize <= 0) goto L27;
        r18 = 0;
        r32.add(ItemInner.asCheckBox(LocaleController.getString(R.string.LocalAudioCache), 4, this.audioSize, Theme.key_statisticChartLine_lightgreen));
    L29:
        if (this.storiesSize <= r18) goto L32;
        r32.add(ItemInner.asCheckBox(LocaleController.getString(R.string.LocalStoriesCache), 5, this.storiesSize, Theme.key_statisticChartLine_red));
    L32:
        if (this.stickersCacheSize <= r18) goto L35;
        r32.add(ItemInner.asCheckBox(LocaleController.getString(R.string.LocalStickersCache), 6, this.stickersCacheSize, Theme.key_statisticChartLine_orange));
    L35:
        if (this.cacheSize <= r18) goto L38;
        r32.add(ItemInner.asCheckBox(LocaleController.getString(R.string.LocalProfilePhotosCache), 7, this.cacheSize, Theme.key_statisticChartLine_cyan));
    L38:
        if (this.cacheTempSize <= r18) goto L41;
        r32.add(ItemInner.asCheckBox(LocaleController.getString(R.string.LocalMiscellaneousCache), 8, this.cacheTempSize, Theme.key_statisticChartLine_purple));
    L41:
        if (this.logsSize <= r18) goto L44;
        r32.add(ItemInner.asCheckBox(LocaleController.getString(R.string.LocalLogsCache), 9, this.logsSize, Theme.key_statisticChartLine_golden));
    L44:
        if (r32.isEmpty() == true) goto L66;
        Collections.sort(r32, new CacheControlActivity$$ExternalSyntheticLambda2());
        ((ItemInner) r32.get(r32.size() - 1)).last = true;
        if (this.tempSizes != null) goto L48;
        this.tempSizes = new float[11];
    L48:
        int r5 = 0;
    L49:
        float[] r9 = this.tempSizes;
        if (r5 >= r9.length) goto L53;
        r9[r5] = size(r5);
        r5 = r5 + 1;
        goto L49
    L53:
        if (this.percents != null) goto L55;
        this.percents = new int[11];
    L55:
        AndroidUtilities.roundPercents(r9, this.percents);
        int r52 = r32.size();
        ArrayList<ItemInner> r6 = this.itemInners;
        if (r52 <= 5) goto L64;
        r6.addAll(r32.subList(0, 4));
        int r62 = 0;
        int r53 = 4;
        long r92 = r18;
    L59:
        if (r53 >= r32.size()) goto L61;
        ((ItemInner) r32.get(r53)).pad = true;
        r92 = r92 + ((ItemInner) r32.get(r53)).size;
        r62 = r62 + this.percents[((ItemInner) r32.get(r53)).index];
        r53 = r53 + 1;
        goto L59
    L61:
        this.percents[r17] = r62;
        this.itemInners.add(ItemInner.asCheckBox(LocaleController.getString(R.string.LocalOther), -1, r92, Theme.key_statisticChartLine_golden));
        if (this.collapsed == true) goto L65;
        this.itemInners.addAll(r32.subList(4, r32.size()));
    L65:
        r3 = true;
        goto L67
    L64:
        r6.addAll(r32);
        goto L65
    L66:
        r3 = false;
        goto L67
    L27:
        r18 = 0;
        goto L29
    L14:
        r17 = '\n';
        goto L16
    L5:
        if ((System.currentTimeMillis() - this.fragmentCreateTime) >= 80) goto L7;
        r2 = false;
        goto L8
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r15) {
        this.actionBar.setBackgroundDrawable(null);
        this.actionBar.setCastShadows(false);
        this.actionBar.setAddToContainer(false);
        this.actionBar.setOccupyStatusBar(true);
        ActionBar r0 = this.actionBar;
        int r3 = Theme.key_windowBackgroundWhiteBlackText;
        r0.setTitleColor(ColorUtils.setAlphaComponent(Theme.getColor(r3), 0));
        this.actionBar.setItemsColor(Theme.getColor(r3), false);
        this.actionBar.setItemsBackgroundColor(Theme.getColor(Theme.key_listSelector), false);
        this.actionBar.setBackButtonDrawable(new BackDrawable(false));
        this.actionBar.setAllowOverlayTitle(false);
        this.actionBar.setTitle(LocaleController.getString(R.string.StorageUsage));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        this.actionMode = this.actionBar.createActionMode();
        FrameLayout r02 = new FrameLayout(r15);
        this.actionMode.addView(r02, LayoutHelper.createLinear(0, -1, 1.0f, 72, 0, 0, 0));
        AnimatedTextView r6 = new AnimatedTextView(r15, true, true, true);
        this.actionModeTitle = r6;
        CubicBezierInterpolator r12 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r6.setAnimationProperties(0.35f, 0, 350, r12);
        this.actionModeTitle.setTextSize(AndroidUtilities.dp(18.0f));
        this.actionModeTitle.setTypeface(AndroidUtilities.bold());
        this.actionModeTitle.setTextColor(Theme.getColor(r3));
        r02.addView(this.actionModeTitle, LayoutHelper.createFrame(-1, 18.0f, 19, 0.0f, -11.0f, 18.0f, 0.0f));
        AnimatedTextView r7 = new AnimatedTextView(r15, true, true, true);
        this.actionModeSubtitle = r7;
        r7.setAnimationProperties(0.35f, 0, 350, r12);
        this.actionModeSubtitle.setTextSize(AndroidUtilities.dp(14.0f));
        this.actionModeSubtitle.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
        r02.addView(this.actionModeSubtitle, LayoutHelper.createFrame(-1, 18.0f, 19, 0.0f, 10.0f, 18.0f, 0.0f));
        TextView r32 = new TextView(r15);
        this.actionModeClearButton = r32;
        r32.setTextSize(1, 14.0f);
        this.actionModeClearButton.setPadding(AndroidUtilities.dp(14.0f), 0, AndroidUtilities.dp(14.0f), 0);
        this.actionModeClearButton.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
        this.actionModeClearButton.setBackground(Theme.AdaptiveRipple.filledRectByKey(Theme.key_featuredStickers_addButton, new float[]{6.0f}));
        this.actionModeClearButton.setTypeface(AndroidUtilities.bold());
        this.actionModeClearButton.setGravity(17);
        this.actionModeClearButton.setText(LocaleController.getString(R.string.CacheClear));
        this.actionModeClearButton.setOnClickListener(new CacheControlActivity$$ExternalSyntheticLambda5(this));
        boolean r33 = LocaleController.isRTL;
        TextView r4 = this.actionModeClearButton;
        if (r33 == false) goto L5;
        r02.addView(r4, LayoutHelper.createFrame(-2, 28.0f, 19, 0.0f, 0.0f, 0.0f, 0.0f));
    L6:
        ActionBarMenuItem r03 = this.actionBar.createMenu().addItem(2, R.drawable.ic_ab_other);
        ActionBarMenuSubItem r34 = r03.addSubItem(3, R.drawable.msg_delete, LocaleController.getString(R.string.ClearLocalDatabase));
        this.clearDatabaseItem = r34;
        int r5 = Theme.key_text_RedRegular;
        r34.setIconColor(Theme.getColor(r5));
        ActionBarMenuSubItem r35 = this.clearDatabaseItem;
        int r62 = Theme.key_text_RedBold;
        r35.setTextColor(Theme.getColor(r62));
        this.clearDatabaseItem.setSelectorColor(Theme.multAlpha(Theme.getColor(r5), 0.12f));
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L9;
        ActionBarMenuSubItem r04 = r03.addSubItem(4, R.drawable.msg_delete, "Full Reset Database");
        this.resetDatabaseItem = r04;
        r04.setIconColor(Theme.getColor(r5));
        this.resetDatabaseItem.setTextColor(Theme.getColor(r62));
        this.resetDatabaseItem.setSelectorColor(Theme.multAlpha(Theme.getColor(r5), 0.12f));
    L9:
        updateDatabaseItemSize();
        this.listAdapter = new ListAdapter(this, r15);
        AnonymousClass2 r05 = new AnonymousClass2(this, r15);
        this.nestedSizeNotifierLayout = r05;
        this.fragmentView = r05;
        r05.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        AnonymousClass3 r36 = new AnonymousClass3(this, r15);
        this.listView = r36;
        r36.setSections();
        this.listView.setVerticalScrollBarEnabled(false);
        this.listView.setPadding(0, AndroidUtilities.statusBarHeight + (ActionBar.getCurrentActionBarHeight() / 2), 0, 0);
        this.listView.setClipToPadding(false);
        RecyclerListView r37 = this.listView;
        LinearLayoutManager r42 = new LinearLayoutManager(r15, 1, false);
        this.layoutManager = r42;
        r37.setLayoutManager(r42);
        r05.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.listView.setAdapter(this.listAdapter);
        AnonymousClass4 r152 = new AnonymousClass4(this);
        r152.setDurations(350);
        r152.setInterpolator(r12);
        r152.setDelayAnimations(false);
        r152.setSupportsChangeAnimations(false);
        this.listView.setItemAnimator(r152);
        this.listView.setOnItemClickListener(new CacheControlActivity$$ExternalSyntheticLambda6(this));
        this.listView.addOnScrollListener(new AnonymousClass5(this));
        r05.addView(this.actionBar, LayoutHelper.createFrame(-1, -2.0f));
        this.nestedSizeNotifierLayout.setTargetListView(this.listView);
        return this.fragmentView;
    L5:
        r02.addView(r4, LayoutHelper.createFrame(-2, 28.0f, 21, 0.0f, 0.0f, 14.0f, 0.0f));
        goto L6
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.didClearDatabase) goto L16;
        AlertDialog r12 = this.progressDialog;     // Catch: Exception -> L8
        if (r12 == null) goto L10;
        r12.dismiss();     // Catch: Exception -> L8
    L10:
        this.progressDialog = null;
        if (this.listAdapter == null) goto L17;
        this.databaseSize = MessagesStorage.getInstance(this.currentAccount).getDatabaseSize();
        this.updateDatabaseSize = true;
        updateDatabaseItemSize();
        updateRows();
        return;
    L17:
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
        goto L10
    }

    public void fillDialogsEntitiesRecursive(File r19, int r20, LongSparseArray<DialogFileEntities> r21, CacheModel r22) {
        if (r19 == null) goto L44;
        File[] r2 = r19.listFiles();
        if (r2 == null) goto L52;
        int r3 = r2.length;
        int r4 = 0;
    L9:
        if (r4 >= r3) goto L53;
        File r5 = r2[r4];
        if (canceled == true) goto L54;
        if (r5.isDirectory() == false) goto L17;
        fillDialogsEntitiesRecursive(r5, r20, r21, r22);
    L43:
        r4 = r4 + 1;
        goto L9
    L17:
        if (r5.getName().equals(".nomedia") == true) goto L43;
        FilePathDatabase.FileMeta r8 = getFileLoader().getFileDatabase().getFileDialogId(r5, null);
        String r10 = r5.getName().toLowerCase();
        if (r10.endsWith(".mp3") == false) goto L22;
    L25:
        int r102 = 3;
    L26:
        CacheModel.FileInfo r11 = new CacheModel.FileInfo(r5);
        long r12 = r5.length();
        r11.size = r12;
        if (r8 == null) goto L33;
        r11.dialogId = r8.dialogId;
        r11.messageId = r8.messageId;
        int r52 = r8.messageType;
        r11.messageType = r52;
        if (r52 != 23) goto L33;
        if (r12 <= 0) goto L33;
        r102 = 7;
    L33:
        r11.type = r102;
        long r122 = r11.dialogId;
        if (r122 == 0) goto L39;
        DialogFileEntities r53 = r21.get(r122, null);
        if (r53 != null) goto L38;
        r53 = new DialogFileEntities(r11.dialogId);
        r21.put(r11.dialogId, r53);
    L38:
        r53.addFile(r11, r102);
    L39:
        if (r22 == null) goto L43;
        if (r102 == 6) goto L43;
        r22.add(r102, r11);
        goto L43
    L22:
        if (r10.endsWith(".m4a") == true) goto L25;
        r102 = r20;
        goto L26
    L54:
        return;
    L53:
        return;
    L52:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        CacheControlActivity$$ExternalSyntheticLambda4 r7 = new CacheControlActivity$$ExternalSyntheticLambda4(this);
        ArrayList<ThemeDescription> r9 = new ArrayList();
        r9.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{TextSettingsCell.class, SlideChooseView.class, StorageUsageView.class, HeaderCell.class}, null, null, null, Theme.key_windowBackgroundWhite));
        r9.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        r9.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r9.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r9.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r9.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r9.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        int r23 = Theme.key_windowBackgroundWhiteBlackText;
        r9.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, r23));
        int r32 = Theme.key_windowBackgroundWhiteValueText;
        r9.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"valueTextView"}, null, null, null, r32));
        r9.add(new ThemeDescription(this.listView, 0, new Class[]{TextInfoPrivacyCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        r9.add(new ThemeDescription(this.listView, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        r9.add(new ThemeDescription(this.listView, 0, new Class[]{StorageUsageView.class}, new String[]{"paintFill"}, null, null, null, Theme.key_player_progressBackground));
        r9.add(new ThemeDescription(this.listView, 0, new Class[]{StorageUsageView.class}, new String[]{"paintProgress"}, null, null, null, Theme.key_player_progress));
        int r22 = Theme.key_windowBackgroundWhiteGrayText;
        r9.add(new ThemeDescription(this.listView, 0, new Class[]{StorageUsageView.class}, new String[]{"telegramCacheTextView"}, null, null, null, r22));
        r9.add(new ThemeDescription(this.listView, 0, new Class[]{StorageUsageView.class}, new String[]{"freeSizeTextView"}, null, null, null, r22));
        r9.add(new ThemeDescription(this.listView, 0, new Class[]{StorageUsageView.class}, new String[]{"calculationgTextView"}, null, null, null, r22));
        r9.add(new ThemeDescription(this.listView, 0, new Class[]{SlideChooseView.class}, null, null, null, Theme.key_switchTrack));
        r9.add(new ThemeDescription(this.listView, 0, new Class[]{SlideChooseView.class}, null, null, null, Theme.key_switchTrackChecked));
        r9.add(new ThemeDescription(this.listView, 0, new Class[]{SlideChooseView.class}, null, null, null, r22));
        r9.add(new ThemeDescription(this.bottomSheetView, 0, null, null, null, null, r22));
        r9.add(new ThemeDescription(this.bottomSheetView, 0, new Class[]{CheckBoxCell.class}, new String[]{"textView"}, null, null, null, r23));
        r9.add(new ThemeDescription(this.bottomSheetView, 0, new Class[]{CheckBoxCell.class}, new String[]{"valueTextView"}, null, null, null, r32));
        r9.add(new ThemeDescription(this.bottomSheetView, 0, new Class[]{CheckBoxCell.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r9.add(new ThemeDescription(this.bottomSheetView, 0, new Class[]{StorageDiagramView.class}, null, null, null, r23));
        r9.add(new ThemeDescription(null, 0, new Class[]{TextCheckBoxCell.class}, new String[]{"textView"}, null, null, null, r23));
        r9.add(new ThemeDescription(null, 0, null, null, null, r7, Theme.key_dialogBackground));
        r9.add(new ThemeDescription(this.bottomSheetView, 0, null, null, null, null, Theme.key_statisticChartLine_blue));
        r9.add(new ThemeDescription(this.bottomSheetView, 0, null, null, null, null, Theme.key_statisticChartLine_green));
        r9.add(new ThemeDescription(this.bottomSheetView, 0, null, null, null, null, Theme.key_statisticChartLine_red));
        r9.add(new ThemeDescription(this.bottomSheetView, 0, null, null, null, null, Theme.key_statisticChartLine_golden));
        r9.add(new ThemeDescription(this.bottomSheetView, 0, null, null, null, null, Theme.key_statisticChartLine_lightblue));
        r9.add(new ThemeDescription(this.bottomSheetView, 0, null, null, null, null, Theme.key_statisticChartLine_lightgreen));
        r9.add(new ThemeDescription(this.bottomSheetView, 0, null, null, null, null, Theme.key_statisticChartLine_orange));
        r9.add(new ThemeDescription(this.bottomSheetView, 0, null, null, null, null, Theme.key_statisticChartLine_indigo));
        return r9;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        if (this.changeStatusBar == true) goto L7;
        return super.isLightStatusBar();
    L7:
        if (AndroidUtilities.computePerceivedBrightness(Theme.getColor(Theme.key_windowBackgroundGray)) <= 0.721f) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSwipeBackEnabled(MotionEvent r5) {
        CachedMediaLayout r0 = this.cachedMediaLayout;
        if (r0 == null) goto L10;
        if (r5 == null) goto L10;
        Rect r2 = AndroidUtilities.rectTmp2;
        r0.getHitRect(r2);
        if (r2.contains((int) r5.getX(), ((int) r5.getY()) - this.actionBar.getMeasuredHeight()) == true) goto L9;
        return true;
    L9:
        return this.cachedMediaLayout.viewPagerFixed.isCurrentTabFirst();
    L10:
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean needDelayOpenAnimation() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r2) {
        CacheModel r0 = this.cacheModel;
        if (r0 == null) goto L13;
        if (r0.selectedFiles.isEmpty() == true) goto L13;
        if (r2 == false) goto L11;
        this.cacheModel.clearSelection();
        CachedMediaLayout r22 = this.cachedMediaLayout;
        if (r22 == null) goto L11;
        r22.showActionMode(false);
        this.cachedMediaLayout.updateVisibleRows();
    L11:
        return false;
    L13:
        return super.onBackPressed(r2);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        canceled = false;
        getNotificationCenter().addObserver(this, NotificationCenter.didClearDatabase);
        this.databaseSize = MessagesStorage.getInstance(this.currentAccount).getDatabaseSize();
        this.loadingDialogs = true;
        Utilities.globalQueue.postRunnable(new CacheControlActivity$$ExternalSyntheticLambda1(this));
        this.fragmentCreateTime = System.currentTimeMillis();
        updateRows(false);
        updateChart();
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        getNotificationCenter().removeObserver(this, NotificationCenter.didClearDatabase);
        AlertDialog r0 = this.progressDialog;     // Catch: Exception -> L8
        if (r0 == null) goto L6;
        r0.dismiss();     // Catch: Exception -> L8
    L6:
        this.progressDialog = null;
        canceled = true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.listView.setPadding(0, AndroidUtilities.statusBarHeight + (ActionBar.getCurrentActionBarHeight() / 2), 0, r4);
        this.listView.setClipToPadding(false);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onRequestPermissionsResultFragment(int r1, String[] r2, int[] r3) {
        if (r1 != 4) goto L19;
        int r0 = 0;
    L6:
        if (r0 >= r3.length) goto L12;
        if (r3[r0] != 0) goto L9;
        r0 = r0 + 1;
        goto L6
    L9:
        return;
    L12:
        if (Build.VERSION.SDK_INT < 30) goto L20;
        FilesMigrationService.FilesMigrationBottomSheet r02 = FilesMigrationService.filesMigrationBottomSheet;
        if (r02 == null) goto L21;
        r02.migrateOldFolder();
        return;
    L21:
        return;
    L20:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        this.listAdapter.notifyDataSetChanged();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationProgress(boolean r4, float r5) {
        if (r5 > 0.5f) goto L5;
    L7:
        super.onTransitionAnimationProgress(r4, r5);
        return;
    L5:
        if (this.changeStatusBar == true) goto L7;
        this.changeStatusBar = true;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.needCheckSystemBarColors, new Object[0]);
        goto L7
    }

    public void setCacheModel(CacheModel r1) {
        this.cacheModel = r1;
        CachedMediaLayout r0 = this.cachedMediaLayout;
        if (r0 == null) goto L6;
        r0.setCacheModel(r1);
        return;
    }
}
