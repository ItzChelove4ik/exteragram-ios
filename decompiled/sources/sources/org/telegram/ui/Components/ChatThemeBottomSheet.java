package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.graphics.ColorUtils;
import androidx.mediarouter.media.PlatformMediaRouter1RouteProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChannelBoostsController;
import org.telegram.messenger.ChatThemeController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.ResultCallback;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.EmojiThemes;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeColors;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.ActionBar.theme.ThemeKey;
import org.telegram.ui.Cells.ThemesHorizontalListCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.StatisticActivity;
import org.telegram.ui.ThemePreviewActivity;
import org.telegram.ui.WallpapersListActivity;

/* loaded from: classes7.dex */
public class ChatThemeBottomSheet extends BottomSheet implements NotificationCenter.NotificationCenterDelegate {
    private final Adapter adapter;
    private final View applyButton;
    private AnimatedTextView applySubTextView;
    private AnimatedTextView applyTextView;
    private final BackDrawable backButtonDrawable;
    private final ImageView backButtonView;
    private TL_stories.TL_premium_boostsStatus boostsStatus;
    private TextView cancelOrResetTextView;
    private View changeDayNightView;
    private ValueAnimator changeDayNightViewAnimator;
    private float changeDayNightViewProgress;
    private final ChatActivity chatActivity;
    public ChatAttachAlert chatAttachAlert;
    private FrameLayout chatAttachButton;
    private AnimatedTextView chatAttachButtonText;
    private boolean checkedBoostsLevel;
    private boolean checkingBoostsLevel;
    private TextView chooseBackgroundTextView;
    private EmojiThemes currentTheme;
    private TLRPC.WallPaper currentWallpaper;
    private final RLottieDrawable darkThemeDrawable;
    private final RLottieImageView darkThemeView;
    private boolean dataLoaded;
    private boolean forceDark;
    HintView hintView;
    private boolean isApplyClicked;
    private boolean isLightDarkChangeAnimation;
    private final LinearLayoutManager layoutManager;
    private ColoredImageSpan lockSpan;
    private final boolean originalIsDark;
    private final EmojiThemes originalTheme;
    BaseFragment overlayFragment;
    private int prevSelectedPosition;
    private final FlickerLoadingView progressView;
    private final RecyclerListView recyclerView;
    private FrameLayout rootLayout;
    private final LinearSmoothScroller scroller;
    private ChatThemeItem selectedItem;
    private float subTextTranslation;
    private ValueAnimator subTextTranslationAnimator;
    private final ChatActivity.ThemeDelegate themeDelegate;
    private TextView themeHintTextView;
    private boolean themesLoading;
    private final TextView titleView;

    /* renamed from: org.telegram.ui.Components.ChatThemeBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends RLottieImageView {
        final /* synthetic */ ChatThemeBottomSheet this$0;

        public AnonymousClass1(ChatThemeBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
            super.onInitializeAccessibilityNodeInfo(r1);
            if (ChatThemeBottomSheet.m9973$$Nest$fgetforceDark(this.this$0) == false) goto L6;
            r1.setText(LocaleController.getString(R.string.AccDescrSwitchToDayTheme));
            return;
        L6:
            r1.setText(LocaleController.getString(R.string.AccDescrSwitchToNightTheme));
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatThemeBottomSheet$10, reason: invalid class name */
    public class AnonymousClass10 implements ValueAnimator.AnimatorUpdateListener {
        boolean changedNavigationBarColor;
        final /* synthetic */ ChatThemeBottomSheet this$0;

        public AnonymousClass10(ChatThemeBottomSheet r1) {
            this.this$0 = r1;
            this.changedNavigationBarColor = false;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r2) {
            ChatThemeBottomSheet.m9984$$Nest$fputchangeDayNightViewProgress(this.this$0, ((Float) r2.getAnimatedValue()).floatValue());
            ChatThemeBottomSheet.m9966$$Nest$fgetchangeDayNightView(this.this$0).invalidate();
            if (this.changedNavigationBarColor == false) goto L5;
            return;
        L5:
            if (ChatThemeBottomSheet.m9967$$Nest$fgetchangeDayNightViewProgress(this.this$0) <= 0.5f) goto L9;
            this.changedNavigationBarColor = true;
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatThemeBottomSheet$11, reason: invalid class name */
    public class AnonymousClass11 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatThemeBottomSheet this$0;

        public AnonymousClass11(ChatThemeBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            if (ChatThemeBottomSheet.m9966$$Nest$fgetchangeDayNightView(this.this$0) != null) goto L5;
        L8:
            ChatThemeBottomSheet.m9983$$Nest$fputchangeDayNightViewAnimator(this.this$0, null);
            super.onAnimationEnd(r4);
            return;
        L5:
            if (ChatThemeBottomSheet.m9966$$Nest$fgetchangeDayNightView(this.this$0).getParent() == null) goto L7;
            ((ViewGroup) ChatThemeBottomSheet.m9966$$Nest$fgetchangeDayNightView(this.this$0).getParent()).removeView(ChatThemeBottomSheet.m9966$$Nest$fgetchangeDayNightView(this.this$0));
        L7:
            ChatThemeBottomSheet.m9982$$Nest$fputchangeDayNightView(this.this$0, null);
            goto L8
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatThemeBottomSheet$12, reason: invalid class name */
    public class AnonymousClass12 implements ChatAttachAlert.ChatAttachViewDelegate {
        long start;
        final /* synthetic */ TL_stories.TL_premium_boostsStatus val$cachedBoostsStatus;
        final /* synthetic */ ChatAttachAlert val$chatAttachAlert;
        final /* synthetic */ long val$dialogId;
        final /* synthetic */ BaseFragment val$fragment;
        final /* synthetic */ Utilities.Callback val$onSet;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;
        final /* synthetic */ ThemePreviewActivity.DayNightSwitchDelegate val$toggleTheme;

        /* renamed from: org.telegram.ui.Components.ChatThemeBottomSheet$12$1, reason: invalid class name */
        public class AnonymousClass1 extends ThemePreviewActivity {
            final /* synthetic */ AnonymousClass12 this$0;

            public AnonymousClass1(AnonymousClass12 r1, Object r2, Bitmap r3) {
                this.this$0 = r1;
                super(r2, r3);
            }

            @Override // org.telegram.ui.ThemePreviewActivity
            public boolean insideBottomSheet() {
                return true;
            }
        }

        /* renamed from: org.telegram.ui.Components.ChatThemeBottomSheet$12$2, reason: invalid class name */
        public class AnonymousClass2 extends ThemePreviewActivity {
            final /* synthetic */ AnonymousClass12 this$0;

            public AnonymousClass2(AnonymousClass12 r1, Object r2, Bitmap r3, boolean r4, boolean r5) {
                this.this$0 = r1;
                super(r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.ThemePreviewActivity
            public boolean insideBottomSheet() {
                return true;
            }
        }

        /* renamed from: $r8$lambda$6dRX3-czrr-yVZ6jB_jue3GVp8Y, reason: not valid java name */
        public static /* synthetic */ void m9998$r8$lambda$6dRX3czrryVZ6jB_jue3GVp8Y(ChatAttachAlert r0, Utilities.Callback r1, TLRPC.WallPaper r2) {
            r0.dismissInternal();
            if (r1 == null) goto L6;
            r1.run(r2);
            return;
        }

        public static /* synthetic */ void $r8$lambda$yCNJNz_wi4txAgZrLSpOt3PRB_4(ChatAttachAlert r0, Utilities.Callback r1, TLRPC.WallPaper r2) {
            r0.dismissInternal();
            if (r1 == null) goto L6;
            r1.run(r2);
            return;
        }

        public AnonymousClass12(ChatAttachAlert r1, TL_stories.TL_premium_boostsStatus r2, Theme.ResourcesProvider r3, ThemePreviewActivity.DayNightSwitchDelegate r4, long r5, Utilities.Callback r7, BaseFragment r8) {
            this.val$chatAttachAlert = r1;
            this.val$cachedBoostsStatus = r2;
            this.val$resourcesProvider = r3;
            this.val$toggleTheme = r4;
            this.val$dialogId = r5;
            this.val$onSet = r7;
            this.val$fragment = r8;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void didPressedButton(int r1, boolean r2, boolean r3, int r4, int r5, long r6, boolean r8, boolean r9, long r10) {
            HashMap<Object, Object> r12 = this.val$chatAttachAlert.getPhotoLayout().getSelectedPhotos();     // Catch: Throwable -> L11
            if (r12.isEmpty() == true) goto L16;
            MediaController.PhotoEntry r13 = (MediaController.PhotoEntry) r12.values().iterator().next();     // Catch: Throwable -> L11
            String r22 = r13.imagePath;     // Catch: Throwable -> L11
            if (r22 != null) goto L8;
            r22 = r13.path;     // Catch: Throwable -> L11
        L8:
            if (r22 == null) goto L17;
            File r14 = new File(FileLoader.getDirectory(4), Utilities.random.nextInt() + ".jpg");     // Catch: Throwable -> L11
            Point r32 = AndroidUtilities.getRealScreenSize();     // Catch: Throwable -> L11
            Bitmap r23 = ImageLoader.loadBitmap(r22, null, (float) r32.x, (float) r32.y, true);     // Catch: Throwable -> L11
            r23.compress(Bitmap.CompressFormat.JPEG, 87, new FileOutputStream(r14));     // Catch: Throwable -> L11
            AnonymousClass1 r33 = new AnonymousClass1(this, new WallpapersListActivity.FileWallpaper(_UrlKt.FRAGMENT_ENCODE_SET, r14, r14), r23);     // Catch: Throwable -> L11
            r33.boostsStatus = this.val$cachedBoostsStatus;     // Catch: Throwable -> L11
            r33.setResourceProvider(this.val$resourcesProvider);     // Catch: Throwable -> L11
            r33.setOnSwitchDayNightDelegate(this.val$toggleTheme);     // Catch: Throwable -> L11
            r33.setInitialModes(false, false, 0.2f);     // Catch: Throwable -> L11
            r33.setDialogId(this.val$dialogId);     // Catch: Throwable -> L11
            r33.setDelegate(new ChatThemeBottomSheet$12$$ExternalSyntheticLambda0(this.val$chatAttachAlert, this.val$onSet));     // Catch: Throwable -> L11
            BaseFragment.BottomSheetParams r15 = new BaseFragment.BottomSheetParams();     // Catch: Throwable -> L11
            r15.transitionFromLeft = true;     // Catch: Throwable -> L11
            r15.allowNestedScroll = false;     // Catch: Throwable -> L11
            r15.occupyNavigationBar = true;     // Catch: Throwable -> L11
            this.val$fragment.showAsSheet(r33, r15);     // Catch: Throwable -> L11
            this.val$chatAttachAlert.dismiss();     // Catch: Throwable -> L11
            return;
        L17:
            return;
        L16:
            return;
        L11:
            th = move-exception;
            FileLog.e(th);
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void onWallpaperSelected(Object r7) {
            AnonymousClass2 r0 = new AnonymousClass2(this, r7, null, true, false);
            r0.boostsStatus = this.val$cachedBoostsStatus;
            r0.setResourceProvider(this.val$resourcesProvider);
            r0.setOnSwitchDayNightDelegate(this.val$toggleTheme);
            r0.setDialogId(this.val$dialogId);
            r0.setDelegate(new ChatThemeBottomSheet$12$$ExternalSyntheticLambda1(this.val$chatAttachAlert, this.val$onSet));
            BaseFragment.BottomSheetParams r6 = new BaseFragment.BottomSheetParams();
            r6.transitionFromLeft = true;
            r6.allowNestedScroll = false;
            r6.occupyNavigationBar = true;
            this.val$fragment.showAsSheet(r0, r6);
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public boolean selectItemOnClicking() {
            this.start = System.currentTimeMillis();
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatThemeBottomSheet$13, reason: invalid class name */
    public class AnonymousClass13 implements ChatAttachAlert.ChatAttachViewDelegate {
        long start;
        final /* synthetic */ ChatThemeBottomSheet this$0;

        /* renamed from: org.telegram.ui.Components.ChatThemeBottomSheet$13$1, reason: invalid class name */
        public class AnonymousClass1 extends ThemePreviewActivity {
            final /* synthetic */ AnonymousClass13 this$1;

            public AnonymousClass1(AnonymousClass13 r1, Object r2, Bitmap r3) {
                this.this$1 = r1;
                super(r2, r3);
            }

            @Override // org.telegram.ui.ThemePreviewActivity
            public boolean insideBottomSheet() {
                return true;
            }
        }

        /* renamed from: org.telegram.ui.Components.ChatThemeBottomSheet$13$2, reason: invalid class name */
        public class AnonymousClass2 extends ThemePreviewActivity {
            final /* synthetic */ AnonymousClass13 this$1;

            public AnonymousClass2(AnonymousClass13 r1, Object r2, Bitmap r3, boolean r4, boolean r5) {
                this.this$1 = r1;
                super(r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.ThemePreviewActivity
            public boolean insideBottomSheet() {
                return true;
            }
        }

        public static /* synthetic */ void $r8$lambda$u2qOyqFFJX6pUCOBNMb4MZ0LNNA(AnonymousClass13 r0, TLRPC.WallPaper r1) {
            r0.lambda$didPressedButton$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$uGcFLdoJZ6kAYbPBJgQ8DMV7fsQ(AnonymousClass13 r0, TLRPC.WallPaper r1) {
            r0.lambda$onWallpaperSelected$1(r1);
        }

        public AnonymousClass13(ChatThemeBottomSheet r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$didPressedButton$0(TLRPC.WallPaper r1) {
            this.this$0.chatAttachAlert.dismissInternal();
            this.this$0.dismiss();
        }

        private /* synthetic */ void lambda$onWallpaperSelected$1(TLRPC.WallPaper r1) {
            this.this$0.chatAttachAlert.dismissInternal();
            this.this$0.dismiss();
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void didPressedButton(int r1, boolean r2, boolean r3, int r4, int r5, long r6, boolean r8, boolean r9, long r10) {
            HashMap<Object, Object> r12 = this.this$0.chatAttachAlert.getPhotoLayout().getSelectedPhotos();     // Catch: Throwable -> L11
            if (r12.isEmpty() == true) goto L16;
            MediaController.PhotoEntry r13 = (MediaController.PhotoEntry) r12.values().iterator().next();     // Catch: Throwable -> L11
            String r22 = r13.imagePath;     // Catch: Throwable -> L11
            if (r22 != null) goto L8;
            r22 = r13.path;     // Catch: Throwable -> L11
        L8:
            if (r22 == null) goto L17;
            File r14 = new File(FileLoader.getDirectory(4), Utilities.random.nextInt() + ".jpg");     // Catch: Throwable -> L11
            Point r32 = AndroidUtilities.getRealScreenSize();     // Catch: Throwable -> L11
            Bitmap r23 = ImageLoader.loadBitmap(r22, null, (float) r32.x, (float) r32.y, true);     // Catch: Throwable -> L11
            r23.compress(Bitmap.CompressFormat.JPEG, 87, new FileOutputStream(r14));     // Catch: Throwable -> L11
            AnonymousClass1 r33 = new AnonymousClass1(this, new WallpapersListActivity.FileWallpaper(_UrlKt.FRAGMENT_ENCODE_SET, r14, r14), r23);     // Catch: Throwable -> L11
            r33.boostsStatus = ChatThemeBottomSheet.m9965$$Nest$fgetboostsStatus(this.this$0);     // Catch: Throwable -> L11
            r33.setInitialModes(false, false, 0.2f);     // Catch: Throwable -> L11
            r33.setDialogId(ChatThemeBottomSheet.m9968$$Nest$fgetchatActivity(this.this$0).getDialogId());     // Catch: Throwable -> L11
            r33.setDelegate(new ChatThemeBottomSheet$13$$ExternalSyntheticLambda0(this));     // Catch: Throwable -> L11
            ChatThemeBottomSheet.m9996$$Nest$mshowAsSheet(this.this$0, r33);     // Catch: Throwable -> L11
            return;
        L17:
            return;
        L16:
            return;
        L11:
            th = move-exception;
            FileLog.e(th);
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void onWallpaperSelected(Object r7) {
            AnonymousClass2 r0 = new AnonymousClass2(this, r7, null, true, false);
            r0.boostsStatus = ChatThemeBottomSheet.m9965$$Nest$fgetboostsStatus(this.this$0);
            r0.setDialogId(ChatThemeBottomSheet.m9968$$Nest$fgetchatActivity(this.this$0).getDialogId());
            r0.setDelegate(new ChatThemeBottomSheet$13$$ExternalSyntheticLambda1(this));
            ChatThemeBottomSheet.m9996$$Nest$mshowAsSheet(this.this$0, r0);
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public boolean selectItemOnClicking() {
            this.start = System.currentTimeMillis();
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatThemeBottomSheet$14, reason: invalid class name */
    public class AnonymousClass14 extends FrameLayout {
        Paint paint;
        final /* synthetic */ ChatThemeBottomSheet this$0;

        public AnonymousClass14(ChatThemeBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.paint = new Paint();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r8) {
            super.dispatchDraw(r8);
            this.paint.setColor(ChatThemeBottomSheet.access$800(this.this$0, Theme.key_divider));
            r8.drawRect(0.0f, 0.0f, getMeasuredWidth(), 1.0f, this.paint);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatThemeBottomSheet$15, reason: invalid class name */
    public class AnonymousClass15 implements ThemePreviewActivity.DayNightSwitchDelegate {
        final /* synthetic */ ChatThemeBottomSheet this$0;

        public AnonymousClass15(ChatThemeBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ThemePreviewActivity.DayNightSwitchDelegate
        public boolean isDark() {
            return ChatThemeBottomSheet.m9973$$Nest$fgetforceDark(this.this$0);
        }

        @Override // org.telegram.ui.ThemePreviewActivity.DayNightSwitchDelegate
        public boolean supportsAnimation() {
            return true;
        }

        @Override // org.telegram.ui.ThemePreviewActivity.DayNightSwitchDelegate
        public void switchDayNight(boolean r5) {
            ChatThemeBottomSheet.m9985$$Nest$fputforceDark(this.this$0, !ChatThemeBottomSheet.m9973$$Nest$fgetforceDark(r0));
            if (ChatThemeBottomSheet.m9979$$Nest$fgetselectedItem(this.this$0) == null) goto L14;
            ChatThemeBottomSheet.m9986$$Nest$fputisLightDarkChangeAnimation(this.this$0, true);
            ChatThemeBottomSheet.m9968$$Nest$fgetchatActivity(this.this$0).forceDisallowRedrawThemeDescriptions = true;
            if (ChatThemeBottomSheet.m9990$$Nest$mhasChanges(this.this$0) == false) goto L7;
            TLRPC.WallPaper r0 = null;
        L8:
            boolean r2 = ChatThemeBottomSheet.m9979$$Nest$fgetselectedItem(this.this$0).chatTheme.showAsDefaultStub;
            ChatThemeBottomSheet r3 = this.this$0;
            if (r2 == false) goto L11;
            ChatThemeBottomSheet.m9981$$Nest$fgetthemeDelegate(r3).setCurrentTheme(null, r0, r5, Boolean.valueOf(ChatThemeBottomSheet.m9973$$Nest$fgetforceDark(this.this$0)));
        L12:
            ChatThemeBottomSheet.m9968$$Nest$fgetchatActivity(this.this$0).forceDisallowRedrawThemeDescriptions = false;
            return;
        L11:
            ChatThemeBottomSheet.m9981$$Nest$fgetthemeDelegate(r3).setCurrentTheme(ChatThemeBottomSheet.m9979$$Nest$fgetselectedItem(this.this$0).chatTheme, r0, r5, Boolean.valueOf(ChatThemeBottomSheet.m9973$$Nest$fgetforceDark(this.this$0)));
            goto L12
        L7:
            r0 = ChatThemeBottomSheet.m9981$$Nest$fgetthemeDelegate(this.this$0).getCurrentWallpaper();
            goto L8
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatThemeBottomSheet$2, reason: invalid class name */
    public class AnonymousClass2 extends LinearSmoothScroller {
        final /* synthetic */ ChatThemeBottomSheet this$0;

        public AnonymousClass2(ChatThemeBottomSheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        public int calculateTimeForScrolling(int r1) {
            return super.calculateTimeForScrolling(r1) * 6;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatThemeBottomSheet$3, reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ ChatThemeBottomSheet this$0;
        final /* synthetic */ int val$position;

        public AnonymousClass3(ChatThemeBottomSheet r1, int r2) {
            this.this$0 = r1;
            this.val$position = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            RecyclerView.LayoutManager r0 = ChatThemeBottomSheet.m9977$$Nest$fgetrecyclerView(this.this$0).getLayoutManager();
            if (r0 == null) goto L9;
            int r1 = this.val$position;
            int r2 = ChatThemeBottomSheet.m9976$$Nest$fgetprevSelectedPosition(this.this$0);
            int r3 = this.val$position;
            if (r1 <= r2) goto L7;
            int r12 = Math.min(r3 + 1, ChatThemeBottomSheet.m9963$$Nest$fgetadapter(this.this$0).items.size() - 1);
        L8:
            ChatThemeBottomSheet.m9978$$Nest$fgetscroller(this.this$0).setTargetPosition(r12);
            r0.startSmoothScroll(ChatThemeBottomSheet.m9978$$Nest$fgetscroller(this.this$0));
            goto L9
        L7:
            r12 = Math.max(r3 - 1, 0);
        L9:
            ChatThemeBottomSheet.m9987$$Nest$fputprevSelectedPosition(this.this$0, this.val$position);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatThemeBottomSheet$4, reason: invalid class name */
    public class AnonymousClass4 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ChatThemeBottomSheet this$0;

        public AnonymousClass4(ChatThemeBottomSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            super.onScrolled(r1, r2, r3);
            if ((ChatThemeBottomSheet.m9975$$Nest$fgetlayoutManager(this.this$0).findLastCompletelyVisibleItemPosition() + 10) < ChatThemeBottomSheet.m9963$$Nest$fgetadapter(this.this$0).getItemCount()) goto L6;
            ChatThemeBottomSheet.m9991$$Nest$mloadNext(this.this$0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatThemeBottomSheet$5, reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatThemeBottomSheet this$0;
        final /* synthetic */ boolean val$subtextShown;

        public AnonymousClass5(ChatThemeBottomSheet r1, boolean r2) {
            this.this$0 = r1;
            this.val$subtextShown = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            ChatThemeBottomSheet r22 = this.this$0;
            if (this.val$subtextShown == false) goto L5;
            float r0 = 1.0f;
        L6:
            ChatThemeBottomSheet.m9988$$Nest$fputsubTextTranslation(r22, r0);
            ChatThemeBottomSheet.m9964$$Nest$fgetapplyTextView(this.this$0).setTranslationY((-AndroidUtilities.dp(7.0f)) * ChatThemeBottomSheet.m9980$$Nest$fgetsubTextTranslation(this.this$0));
            return;
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatThemeBottomSheet$6, reason: invalid class name */
    public class AnonymousClass6 implements ResultCallback<List<EmojiThemes>> {
        final /* synthetic */ ChatThemeBottomSheet this$0;
        final /* synthetic */ ChatThemeController val$chatThemeController;

        /* renamed from: $r8$lambda$xwkZYh7mPxpjuQ7Gcug-rENma9o, reason: not valid java name */
        public static /* synthetic */ void m9999$r8$lambda$xwkZYh7mPxpjuQ7GcugrENma9o(AnonymousClass6 r0, List r1) {
            r0.lambda$onComplete$0(r1);
        }

        public AnonymousClass6(ChatThemeBottomSheet r1, ChatThemeController r2) {
            this.this$0 = r1;
            this.val$chatThemeController = r2;
        }

        private /* synthetic */ void lambda$onComplete$0(List r1) {
            ChatThemeBottomSheet.m9994$$Nest$monDataLoaded(this.this$0, r1);
        }

        @Override // org.telegram.tgnet.ResultCallback
        public /* bridge */ /* synthetic */ void onComplete(List<EmojiThemes> r1) {
            onComplete2(r1);
        }

        /* renamed from: onComplete, reason: avoid collision after fix types in other method */
        public void onComplete2(List<EmojiThemes> r3) {
            List<EmojiThemes> r32 = this.val$chatThemeController.getEmojiThemes(7);
            NotificationCenter.getInstance(ChatThemeBottomSheet.access$000(this.this$0)).doOnIdle(new ChatThemeBottomSheet$6$$ExternalSyntheticLambda0(this, r32));
            ChatThemeBottomSheet.m9989$$Nest$fputthemesLoading(this.this$0, false);
        }

        @Override // org.telegram.tgnet.ResultCallback
        public void onError(TLRPC.TL_error r2) {
            Toast.makeText(this.this$0.getContext(), r2.text, 0).show();
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatThemeBottomSheet$7, reason: invalid class name */
    public class AnonymousClass7 implements ResultCallback<Void> {
        final /* synthetic */ ChatThemeBottomSheet this$0;
        final /* synthetic */ ChatThemeController val$chatThemeController;

        public static /* synthetic */ void $r8$lambda$QoDNWGNt_IoK4MVypneFHwxoHr0(AnonymousClass7 r0, List r1) {
            r0.lambda$onComplete$0(r1);
        }

        public AnonymousClass7(ChatThemeBottomSheet r1, ChatThemeController r2) {
            this.this$0 = r1;
            this.val$chatThemeController = r2;
        }

        private /* synthetic */ void lambda$onComplete$0(List r1) {
            ChatThemeBottomSheet.m9994$$Nest$monDataLoaded(this.this$0, r1);
        }

        @Override // org.telegram.tgnet.ResultCallback
        public /* bridge */ /* synthetic */ void onComplete(Void r1) {
            onComplete2(r1);
        }

        /* renamed from: onComplete, reason: avoid collision after fix types in other method */
        public void onComplete2(Void r4) {
            ChatThemeController r42 = this.val$chatThemeController;
            if (r42.isGiftThemesFullyLoaded() == false) goto L5;
            int r0 = 2;
        L6:
            NotificationCenter.getInstance(ChatThemeBottomSheet.access$100(this.this$0)).doOnIdle(new ChatThemeBottomSheet$7$$ExternalSyntheticLambda0(this, r42.getEmojiThemes(r0 | 5)));
            ChatThemeBottomSheet.m9989$$Nest$fputthemesLoading(this.this$0, false);
            return;
        L5:
            r0 = 0;
            goto L6
        }

        @Override // org.telegram.tgnet.ResultCallback
        public void onError(TLRPC.TL_error r2) {
            Toast.makeText(this.this$0.getContext(), r2.text, 0).show();
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatThemeBottomSheet$8, reason: invalid class name */
    public class AnonymousClass8 implements ThemeDescription.ThemeDescriptionDelegate {
        private boolean isAnimationStarted;
        final /* synthetic */ ChatThemeBottomSheet this$0;

        public AnonymousClass8(ChatThemeBottomSheet r1) {
            this.this$0 = r1;
            this.isAnimationStarted = false;
        }

        @Override // org.telegram.ui.ActionBar.ThemeDescription.ThemeDescriptionDelegate
        public void didSetColor() {
        }

        @Override // org.telegram.ui.ActionBar.ThemeDescription.ThemeDescriptionDelegate
        public void onAnimationProgress(float r7) {
            if (r7 == 0.0f) goto L5;
        L7:
            RLottieDrawable r1 = ChatThemeBottomSheet.m9971$$Nest$fgetdarkThemeDrawable(this.this$0);
            ChatThemeBottomSheet r3 = this.this$0;
            int r4 = Theme.key_featuredStickers_addButton;
            r1.setColorFilter(new PorterDuffColorFilter(ChatThemeBottomSheet.access$200(r3, r4), PorterDuff.Mode.MULTIPLY));
            ChatThemeBottomSheet r12 = this.this$0;
            r12.setOverlayNavBarColor(ChatThemeBottomSheet.access$300(r12, Theme.key_windowBackgroundGray));
            if (ChatThemeBottomSheet.m9974$$Nest$fgetisLightDarkChangeAnimation(this.this$0) == false) goto L11;
            ChatThemeBottomSheet.m9995$$Nest$msetItemsAnimationProgress(this.this$0, r7);
        L11:
            if (r7 == 1.0f) goto L13;
        L15:
            ChatThemeBottomSheet.m9997$$Nest$mupdateButtonColors(this.this$0);
            if (ChatThemeBottomSheet.m9969$$Nest$fgetchatAttachButton(this.this$0) == null) goto L19;
            ChatThemeBottomSheet.m9969$$Nest$fgetchatAttachButton(this.this$0).setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(0.0f), ChatThemeBottomSheet.access$400(this.this$0, Theme.key_windowBackgroundWhite), ColorUtils.setAlphaComponent(ChatThemeBottomSheet.access$500(this.this$0, r4), 76)));
        L19:
            if (ChatThemeBottomSheet.m9970$$Nest$fgetchatAttachButtonText(this.this$0) == null) goto L21;
            ChatThemeBottomSheet.m9970$$Nest$fgetchatAttachButtonText(this.this$0).setTextColor(ChatThemeBottomSheet.access$600(this.this$0, r4));
        L21:
            ChatThemeBottomSheet r6 = this.this$0;
            r6.setBackgroundColor(ChatThemeBottomSheet.access$700(r6, Theme.key_dialogBackground));
            return;
        L13:
            if (this.isAnimationStarted == false) goto L15;
            ChatThemeBottomSheet.m9986$$Nest$fputisLightDarkChangeAnimation(this.this$0, false);
            ChatThemeBottomSheet.m9992$$Nest$monAnimationEnd(this.this$0);
            this.isAnimationStarted = false;
            goto L15
        L5:
            if (this.isAnimationStarted == true) goto L7;
            ChatThemeBottomSheet.m9993$$Nest$monAnimationStart(this.this$0);
            this.isAnimationStarted = true;
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatThemeBottomSheet$9, reason: invalid class name */
    public class AnonymousClass9 extends View {
        final /* synthetic */ ChatThemeBottomSheet this$0;
        final /* synthetic */ Bitmap val$bitmap;
        final /* synthetic */ Canvas val$bitmapCanvas;
        final /* synthetic */ Paint val$bitmapPaint;
        final /* synthetic */ float val$cx;
        final /* synthetic */ float val$cy;
        final /* synthetic */ boolean val$isDark;
        final /* synthetic */ float val$r;
        final /* synthetic */ float val$x;
        final /* synthetic */ Paint val$xRefPaint;
        final /* synthetic */ float val$y;

        public AnonymousClass9(ChatThemeBottomSheet r1, Context r2, boolean r3, Canvas r4, float r5, float r6, float r7, Paint r8, Bitmap r9, Paint r10, float r11, float r12) {
            this.this$0 = r1;
            this.val$isDark = r3;
            this.val$bitmapCanvas = r4;
            this.val$cx = r5;
            this.val$cy = r6;
            this.val$r = r7;
            this.val$xRefPaint = r8;
            this.val$bitmap = r9;
            this.val$bitmapPaint = r10;
            this.val$x = r11;
            this.val$y = r12;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r7) {
            super.onDraw(r7);
            if (this.val$isDark == true) goto L5;
            r7.drawCircle(this.val$cx, this.val$cy, this.val$r * (1.0f - ChatThemeBottomSheet.m9967$$Nest$fgetchangeDayNightViewProgress(this.this$0)), this.val$bitmapPaint);
        L9:
            r7.save();
            r7.translate(this.val$x, this.val$y);
            ChatThemeBottomSheet.m9972$$Nest$fgetdarkThemeView(this.this$0).draw(r7);
            r7.restore();
            return;
        L5:
            if (ChatThemeBottomSheet.m9967$$Nest$fgetchangeDayNightViewProgress(this.this$0) <= 0.0f) goto L7;
            this.val$bitmapCanvas.drawCircle(this.val$cx, this.val$cy, this.val$r * ChatThemeBottomSheet.m9967$$Nest$fgetchangeDayNightViewProgress(this.this$0), this.val$xRefPaint);
        L7:
            r7.drawBitmap(this.val$bitmap, 0.0f, 0.0f, this.val$bitmapPaint);
            goto L9
        }
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public static class Adapter extends RecyclerListView.SelectionAdapter {
        private final int currentAccount;
        private final int currentViewType;
        public List<ChatThemeItem> items;
        private HashMap<String, Theme.ThemeInfo> loadingThemes;
        private HashMap<Theme.ThemeInfo, String> loadingWallpapers;
        private final long parentDialogId;
        private final Theme.ResourcesProvider resourcesProvider;
        private int selectedItemPosition;
        private WeakReference<ThemeSmallPreviewView> selectedViewRef;

        public static /* synthetic */ void $r8$lambda$9DWJ44g3TEKQ6lAqQncTVAlsaHY(Adapter r0, TLObject r1, Theme.ThemeInfo r2) {
            r0.lambda$parseTheme$0(r1, r2);
        }

        /* renamed from: $r8$lambda$MUPCxfMvqH_LmOnCU-idlJeSbDo, reason: not valid java name */
        public static /* synthetic */ void m10000$r8$lambda$MUPCxfMvqH_LmOnCUidlJeSbDo(Adapter r0, Theme.ThemeInfo r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$parseTheme$1(r1, r2, r3);
        }

        public Adapter(int r2, long r3, Theme.ResourcesProvider r5, int r6) {
            this.selectedItemPosition = -1;
            this.loadingThemes = new HashMap();
            this.loadingWallpapers = new HashMap();
            this.currentViewType = r6;
            this.parentDialogId = r3;
            this.resourcesProvider = r5;
            this.currentAccount = r2;
        }

        public Adapter(int r7, Theme.ResourcesProvider r8, int r9) {
            this(r7, 0, r8, r9);
        }

        private /* synthetic */ void lambda$parseTheme$0(TLObject r4, Theme.ThemeInfo r5) {
            if ((r4 instanceof TLRPC.TL_wallPaper) == false) goto L8;
            TLRPC.WallPaper r42 = (TLRPC.WallPaper) r4;
            String r0 = FileLoader.getAttachFileName(r42.document);
            if (this.loadingThemes.containsKey(r0) == true) goto L10;
            this.loadingThemes.put(r0, r5);
            FileLoader.getInstance(r5.account).loadFile(r42.document, r42, 1, 1);
            return;
        L10:
            return;
        L8:
            r5.badWallpaper = true;
        }

        private /* synthetic */ void lambda$parseTheme$1(Theme.ThemeInfo r1, TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new ChatThemeBottomSheet$Adapter$$ExternalSyntheticLambda1(this, r2, r1));
        }

        private boolean parseTheme(Theme.ThemeInfo r19) {
            if (r19 != null) goto L5;
            return false;
        L5:
            if (r19.pathToFile == null) goto L162;
            boolean r4 = true;
            FileInputStream r5 = new FileInputStream(new File(r19.pathToFile));     // Catch: Throwable -> L116
            int r0 = 0;
            boolean r6 = false;
        L140:
            int r7 = r5.read(ThemesHorizontalListCell.bytes);     // Catch: Throwable -> L30
            char r8 = 65535;
            if (r7 == (-1)) goto L114;
            int r11 = r0;
            int r9 = 0;
            int r10 = 0;
        L13:
            if (r9 >= r7) goto L109;
            byte[] r12 = ThemesHorizontalListCell.bytes;     // Catch: Throwable -> L30
            if (r12[r9] != 10) goto L107;
            int r13 = r9 - r10;     // Catch: Throwable -> L30
            int r14 = r13 + 1;     // Catch: Throwable -> L30
            String r15 = new String(r12, r10, r13, "UTF-8");     // Catch: Throwable -> L30
            if (r15.startsWith("WLS=") == false) goto L63;
            String r82 = r15.substring(4);     // Catch: Throwable -> L30
            Uri r122 = Uri.parse(r82);     // Catch: Throwable -> L30
            r19.slug = r122.getQueryParameter("slug");     // Catch: Throwable -> L30
            r19.pathToWallpaper = new File(ApplicationLoader.getFilesDirFixed(), Utilities.MD5(r82) + ".wp").getAbsolutePath();     // Catch: Throwable -> L30
            String r3 = r122.getQueryParameter("mode");     // Catch: Throwable -> L30
            if (r3 == null) goto L34;
            String[] r32 = r3.toLowerCase().split(" ");     // Catch: Throwable -> L30
            if (r32 == null) goto L34;
            if (r32.length <= 0) goto L34;
            int r83 = 0;
        L26:
            if (r83 >= r32.length) goto L34;
            if ("blur".equals(r32[r83]) == true) goto L29;
            r83 = r83 + 1;     // Catch: Throwable -> L30
            goto L26
        L29:
            r19.isBlured = r4;     // Catch: Throwable -> L30
        L34:
            if (TextUtils.isEmpty(r122.getQueryParameter("pattern")) == false) goto L147;
        L61:
            char r42 = 65535;
        L106:
            r10 = r10 + r14;     // Catch: Throwable -> L30
            r11 = r11 + r14;     // Catch: Throwable -> L30
        L108:
            r9 = r9 + 1;     // Catch: Throwable -> L30
            r8 = r42;
            r4 = true;
            goto L13
        L147:
            String r33 = r122.getQueryParameter("bg_color");     // Catch: Throwable -> L30 Exception -> L138
            if (TextUtils.isEmpty(r33) == true) goto L143;
            r19.patternBgColor = Integer.parseInt(r33.substring(0, 6), 16) | (-16777216);     // Catch: Throwable -> L30 Exception -> L138
            if (r33.length() < 13) goto L43;
            if (AndroidUtilities.isValidWallChar(r33.charAt(6)) == false) goto L43;
            r19.patternBgGradientColor1 = Integer.parseInt(r33.substring(7, 13), 16) | (-16777216);     // Catch: Throwable -> L30 Exception -> L138
        L43:
            if (r33.length() < 20) goto L48;
            if (AndroidUtilities.isValidWallChar(r33.charAt(13)) == false) goto L48;
            r19.patternBgGradientColor2 = Integer.parseInt(r33.substring(14, 20), 16) | (-16777216);     // Catch: Throwable -> L30 Exception -> L138
        L48:
            if (r33.length() != 27) goto L143;
            if (AndroidUtilities.isValidWallChar(r33.charAt(20)) == false) goto L143;
            r19.patternBgGradientColor3 = Integer.parseInt(r33.substring(21), 16) | (-16777216);     // Catch: Throwable -> L30 Exception -> L138
        L143:
            String r34 = r122.getQueryParameter("rotation");     // Catch: Throwable -> L30 Exception -> L139
            if (TextUtils.isEmpty(r34) == true) goto L55;
            r19.patternBgGradientRotation = Utilities.parseInt(r34);     // Catch: Throwable -> L30 Exception -> L139
        L55:
            String r35 = r122.getQueryParameter("intensity");     // Catch: Throwable -> L30
            if (TextUtils.isEmpty(r35) == true) goto L59;
            r19.patternIntensity = Utilities.parseInt(r35);     // Catch: Throwable -> L30
        L59:
            if (r19.patternIntensity != 0) goto L61;
            r19.patternIntensity = 50;     // Catch: Throwable -> L30
            goto L61
        L63:
            if (r15.startsWith("WPS") == true) goto L64;
            int r36 = r15.indexOf(61);     // Catch: Throwable -> L30
            r42 = 65535;
            if (r36 == (-1)) goto L106;
            int r84 = ThemeColors.stringKeyToInt(r15.substring(0, r36));     // Catch: Throwable -> L30
            if (r84 != Theme.key_chat_inBubble) goto L70;
        L79:
            String r37 = r15.substring(r36 + 1);     // Catch: Throwable -> L30
            if (r37.length() > 0) goto L83;
        L87:
            int r38 = Utilities.parseInt(r37);     // Catch: Throwable -> L30
        L89:
            if (r84 != Theme.key_chat_inBubble) goto L92;
            r19.setPreviewInColor(r38);     // Catch: Throwable -> L30
            goto L106
        L92:
            if (r84 != Theme.key_chat_outBubble) goto L95;
            r19.setPreviewOutColor(r38);     // Catch: Throwable -> L30
            goto L106
        L95:
            if (r84 != Theme.key_chat_wallpaper) goto L98;
            r19.setPreviewBackgroundColor(r38);     // Catch: Throwable -> L30
            goto L106
        L98:
            if (r84 != Theme.key_chat_wallpaper_gradient_to1) goto L101;
            r19.previewBackgroundGradientColor1 = r38;     // Catch: Throwable -> L30
            goto L106
        L101:
            if (r84 != Theme.key_chat_wallpaper_gradient_to2) goto L104;
            r19.previewBackgroundGradientColor2 = r38;     // Catch: Throwable -> L30
            goto L106
        L104:
            if (r84 != Theme.key_chat_wallpaper_gradient_to3) goto L106;
            r19.previewBackgroundGradientColor3 = r38;     // Catch: Throwable -> L30
            goto L106
        L83:
            if (r37.charAt(0) != '#') goto L87;
            r38 = Color.parseColor(r37);     // Catch: Throwable -> L30 Exception -> L86
        L86:
            r38 = Utilities.parseInt(r37);     // Catch: Throwable -> L30
            goto L89
        L70:
            if (r84 == Theme.key_chat_outBubble) goto L79;
            if (r84 == Theme.key_chat_wallpaper) goto L79;
            if (r84 == Theme.key_chat_wallpaper_gradient_to1) goto L79;
            if (r84 == Theme.key_chat_wallpaper_gradient_to2) goto L79;
            if (r84 != Theme.key_chat_wallpaper_gradient_to3) goto L106;
        L64:
            r19.previewWallpaperOffset = r14 + r11;     // Catch: Throwable -> L30
            r6 = true;
            goto L109
        L107:
            r42 = r8;
        L109:
            if (r6 == true) goto L114;
            if (r0 == r11) goto L114;
            r5.getChannel().position(r11);     // Catch: Throwable -> L30
            r0 = r11;
            r4 = true;
        L114:
            r5.close();     // Catch: Throwable -> L116
        L125:
            if (r19.pathToWallpaper != null) goto L127;
        L135:
            r19.previewParsed = true;
            return true;
        L127:
            if (r19.badWallpaper == true) goto L135;
            if (new File(r19.pathToWallpaper).exists() == true) goto L135;
            if (this.loadingWallpapers.containsKey(r19) == true) goto L161;
            this.loadingWallpapers.put(r19, r19.slug);
            TL_account.getWallPaper r02 = new TL_account.getWallPaper();
            TLRPC.TL_inputWallPaperSlug r39 = new TLRPC.TL_inputWallPaperSlug();
            r39.slug = r19.slug;
            r02.wallpaper = r39;
            ConnectionsManager.getInstance(r19.account).sendRequest(r02, new ChatThemeBottomSheet$Adapter$$ExternalSyntheticLambda0(this, r19));
            return false;
        L161:
            return false;
        L30:
            th = move-exception;
            r5.close();     // Catch: Throwable -> L120
        L160:
            throw th;     // Catch: Throwable -> L116
        L120:
            th = move-exception;
            th.addSuppressed(th);     // Catch: Throwable -> L116
            throw th;     // Catch: Throwable -> L116
        L116:
            th = move-exception;
            FileLog.e(th);
            goto L125
        L162:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            List<ChatThemeItem> r0 = this.items;
            if (r0 != null) goto L7;
            return 0;
        L7:
            return r0.size();
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r7, int r8) {
            ThemeSmallPreviewView r72 = (ThemeSmallPreviewView) r7.itemView;
            Theme.ThemeInfo r0 = this.items.get(r8).chatTheme.getThemeInfo(this.items.get(r8).themeIndex);
            if (r0 != null) goto L5;
        L11:
            ChatThemeItem r02 = this.items.get(r8);
            ChatThemeItem r1 = r72.chatThemeItem;
            boolean r2 = false;
            if (r1 != null) goto L14;
        L21:
            boolean r12 = false;
        L22:
            r72.setFocusable(true);
            r72.setEnabled(true);
            r72.setBackgroundColor(Theme.getColor(Theme.key_dialogBackgroundGray));
            r72.setItem(r02, this.parentDialogId, r12);
            if (r8 != this.selectedItemPosition) goto L25;
            r2 = true;
        L25:
            r72.setSelected(r2, r12);
            if (r8 != this.selectedItemPosition) goto L29;
            this.selectedViewRef = new WeakReference(r72);
            return;
        L29:
            return;
        L14:
            if (ThemeKey.equals(r1.chatTheme.getThemeKey(), r02.chatTheme.getThemeKey()) == false) goto L21;
            if (DialogsActivity.switchingTheme == true) goto L21;
            if (r72.lastThemeIndex != r02.themeIndex) goto L21;
            r12 = true;
            goto L22
        L5:
            if (r0.pathToFile == null) goto L11;
            if (r0.previewParsed == true) goto L11;
            if (new File(r0.pathToFile).exists() == false) goto L11;
            parseTheme(r0);
            goto L11
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r4, int r5) {
            return new RecyclerListView.Holder(new ThemeSmallPreviewView(r4.getContext(), this.currentAccount, this.resourcesProvider, this.currentViewType));
        }

        public void setItems(List<ChatThemeItem> r1) {
            this.items = r1;
            notifyDataSetChanged();
        }

        public void setSelectedItem(int r3) {
            int r0 = this.selectedItemPosition;
            if (r0 != r3) goto L5;
            return;
        L5:
            if (r0 < 0) goto L12;
            notifyItemChanged(r0);
            WeakReference<ThemeSmallPreviewView> r02 = this.selectedViewRef;
            if (r02 != null) goto L9;
            ThemeSmallPreviewView r03 = null;
        L10:
            if (r03 == null) goto L12;
            r03.setSelected(false);
            goto L12
        L9:
            r03 = r02.get();
        L12:
            this.selectedItemPosition = r3;
            notifyItemChanged(r3);
        }
    }

    /* loaded from: classes3.dex */
    public static class ChatThemeItem {
        public float animationProgress;
        public final EmojiThemes chatTheme;
        public Bitmap icon;
        public boolean isSelected;
        public Drawable previewDrawable;
        public int themeIndex;

        public ChatThemeItem(EmojiThemes r2) {
            this.animationProgress = 1.0f;
            this.chatTheme = r2;
        }

        @Deprecated
        public String getEmoticon() {
            EmojiThemes r1 = this.chatTheme;
            if (r1 != null) goto L5;
            return null;
        L5:
            if (r1.showAsDefaultStub == false) goto L8;
            return null;
        L8:
            return r1.getEmoticon();
        }
    }

    public static /* synthetic */ void $r8$lambda$06QQcjCWll9lKQk0IsmJ3f7SQ2k(ChatThemeBottomSheet r0) {
        r0.lambda$onCreate$8();
    }

    public static /* synthetic */ void $r8$lambda$5Tss_D1bzwvuwkhKF0vmGmVvCus(ChatThemeBottomSheet r0, TL_stories.TL_premium_boostsStatus r1) {
        r0.lambda$checkBoostsLevel$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$736k_GWVmQWpmfwzGOEGiCzCRsA(ChatThemeBottomSheet r0, ChannelBoostsController.CanApplyBoost r1) {
        r0.lambda$applySelectedTheme$15(r1);
    }

    public static /* synthetic */ void $r8$lambda$Dag9oBkLtGlpe6unI6cIRZ79QF4(ChatThemeBottomSheet r0) {
        r0.lambda$didReceivedNotification$11();
    }

    public static /* synthetic */ void $r8$lambda$Dj3wJNIPmLJ_H8QzntTr06T2KsY(ChatThemeBottomSheet r0, View r1, int r2) {
        r0.lambda$new$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$DkbR9b9Hm1HozbIsmH5knXkxorg() {
        PhotoViewer.getInstance().closePhoto(false, false);
    }

    /* renamed from: $r8$lambda$G2rrvkcZMkikIemPP6cGio_-ELo, reason: not valid java name */
    public static /* synthetic */ void m9958$r8$lambda$G2rrvkcZMkikIemPP6cGio_ELo(ChatThemeBottomSheet r0, ChatActivity r1, View r2) {
        r0.lambda$new$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$GX3Fk6sk2Mxqraxu06FKiihgPmA(ChatThemeBottomSheet r0) {
        r0.lambda$showAsSheet$23();
    }

    /* renamed from: $r8$lambda$IrH6IemNi4uY6ec3Lk-iphros_g, reason: not valid java name */
    public static /* synthetic */ void m9959$r8$lambda$IrH6IemNi4uY6ec3Lkiphros_g(ChatThemeBottomSheet r0, boolean r1) {
        r0.lambda$setupLightDarkTheme$13(r1);
    }

    /* renamed from: $r8$lambda$JB__MdjtKrZpufl_ojVysH-lT4o, reason: not valid java name */
    public static /* synthetic */ void m9960$r8$lambda$JB__MdjtKrZpufl_ojVysHlT4o(ChatThemeBottomSheet r0) {
        r0.lambda$applySelectedTheme$16();
    }

    public static /* synthetic */ void $r8$lambda$NAiawmvl_3NALIErMst90uOYgls() {
        PhotoViewer.getInstance().closePhoto(false, false);
    }

    public static /* synthetic */ boolean $r8$lambda$ObZt5PHkbkm9vSU41M0zz7Isy4M(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$ROEzBjoZgGMMdcnoLU7eDyjWKM8(ChatThemeBottomSheet r0) {
        r0.lambda$showAsSheet$22();
    }

    public static /* synthetic */ void $r8$lambda$SXQrHtH0YP99NwWcGqO5BXcVSx0(ChatThemeBottomSheet r0) {
        r0.lambda$showAsSheet$19();
    }

    /* renamed from: $r8$lambda$UXY0p50q6zqfwuLWH_-D-Q7Wo4E, reason: not valid java name */
    public static /* synthetic */ void m9961$r8$lambda$UXY0p50q6zqfwuLWH_DQ7Wo4E(ChatThemeBottomSheet r0) {
        r0.lambda$showAsSheet$20();
    }

    public static /* synthetic */ void $r8$lambda$WX5IYq9f7aDF9BuQPBYC277x9Rk(ChatThemeBottomSheet r0, View r1) {
        r0.lambda$openGalleryForBackground$17(r1);
    }

    /* renamed from: $r8$lambda$WlLh-QSO8i9uVF-825XH9l2Z0Bg, reason: not valid java name */
    public static /* synthetic */ void m9962$r8$lambda$WlLhQSO8i9uVF825XH9l2Z0Bg(ChatThemeBottomSheet r0, AlertDialog r1, int r2) {
        r0.lambda$close$10(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Yvc8sxJRRxe_8NE0DlKO7lpsvNI(ChatThemeBottomSheet r0, ValueAnimator r1) {
        r0.lambda$updateApplySubTextTranslation$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$ZQvG4rtW61LP8_tWnM9dW6dWH6s(ChatThemeBottomSheet r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$gGTV2E0psYyrPQv2dVbbRSVnoGw(ChatThemeBottomSheet r0, AlertDialog r1, int r2) {
        r0.lambda$close$9(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$o5QD6KiP540o47GcWC_Ff5zINos(ChatThemeBottomSheet r0) {
        r0.lambda$applySelectedTheme$14();
    }

    public static /* synthetic */ void $r8$lambda$ors18CpUaPsa7zvJtdYi9MVY9cQ(ChatThemeBottomSheet r0, View r1) {
        r0.lambda$new$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$vPucxooQI_spa3y1O2NEELG4FB8(ChatThemeBottomSheet r0, View r1) {
        r0.lambda$new$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$zhGclt5QqAqYhn8_Ky_mMmi9Iu8(ChatThemeBottomSheet r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Adapter m9963$$Nest$fgetadapter(ChatThemeBottomSheet r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetapplyTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedTextView m9964$$Nest$fgetapplyTextView(ChatThemeBottomSheet r0) {
        return r0.applyTextView;
    }

    /* renamed from: -$$Nest$fgetboostsStatus, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TL_stories.TL_premium_boostsStatus m9965$$Nest$fgetboostsStatus(ChatThemeBottomSheet r0) {
        return r0.boostsStatus;
    }

    /* renamed from: -$$Nest$fgetchangeDayNightView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m9966$$Nest$fgetchangeDayNightView(ChatThemeBottomSheet r0) {
        return r0.changeDayNightView;
    }

    /* renamed from: -$$Nest$fgetchangeDayNightViewProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m9967$$Nest$fgetchangeDayNightViewProgress(ChatThemeBottomSheet r0) {
        return r0.changeDayNightViewProgress;
    }

    /* renamed from: -$$Nest$fgetchatActivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivity m9968$$Nest$fgetchatActivity(ChatThemeBottomSheet r0) {
        return r0.chatActivity;
    }

    /* renamed from: -$$Nest$fgetchatAttachButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m9969$$Nest$fgetchatAttachButton(ChatThemeBottomSheet r0) {
        return r0.chatAttachButton;
    }

    /* renamed from: -$$Nest$fgetchatAttachButtonText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedTextView m9970$$Nest$fgetchatAttachButtonText(ChatThemeBottomSheet r0) {
        return r0.chatAttachButtonText;
    }

    /* renamed from: -$$Nest$fgetdarkThemeDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RLottieDrawable m9971$$Nest$fgetdarkThemeDrawable(ChatThemeBottomSheet r0) {
        return r0.darkThemeDrawable;
    }

    /* renamed from: -$$Nest$fgetdarkThemeView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RLottieImageView m9972$$Nest$fgetdarkThemeView(ChatThemeBottomSheet r0) {
        return r0.darkThemeView;
    }

    /* renamed from: -$$Nest$fgetforceDark, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9973$$Nest$fgetforceDark(ChatThemeBottomSheet r0) {
        return r0.forceDark;
    }

    /* renamed from: -$$Nest$fgetisLightDarkChangeAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9974$$Nest$fgetisLightDarkChangeAnimation(ChatThemeBottomSheet r0) {
        return r0.isLightDarkChangeAnimation;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayoutManager m9975$$Nest$fgetlayoutManager(ChatThemeBottomSheet r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetprevSelectedPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9976$$Nest$fgetprevSelectedPosition(ChatThemeBottomSheet r0) {
        return r0.prevSelectedPosition;
    }

    /* renamed from: -$$Nest$fgetrecyclerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m9977$$Nest$fgetrecyclerView(ChatThemeBottomSheet r0) {
        return r0.recyclerView;
    }

    /* renamed from: -$$Nest$fgetscroller, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearSmoothScroller m9978$$Nest$fgetscroller(ChatThemeBottomSheet r0) {
        return r0.scroller;
    }

    /* renamed from: -$$Nest$fgetselectedItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatThemeItem m9979$$Nest$fgetselectedItem(ChatThemeBottomSheet r0) {
        return r0.selectedItem;
    }

    /* renamed from: -$$Nest$fgetsubTextTranslation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m9980$$Nest$fgetsubTextTranslation(ChatThemeBottomSheet r0) {
        return r0.subTextTranslation;
    }

    /* renamed from: -$$Nest$fgetthemeDelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivity.ThemeDelegate m9981$$Nest$fgetthemeDelegate(ChatThemeBottomSheet r0) {
        return r0.themeDelegate;
    }

    /* renamed from: -$$Nest$fputchangeDayNightView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9982$$Nest$fputchangeDayNightView(ChatThemeBottomSheet r0, View r1) {
        r0.changeDayNightView = r1;
    }

    /* renamed from: -$$Nest$fputchangeDayNightViewAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9983$$Nest$fputchangeDayNightViewAnimator(ChatThemeBottomSheet r0, ValueAnimator r1) {
        r0.changeDayNightViewAnimator = r1;
    }

    /* renamed from: -$$Nest$fputchangeDayNightViewProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9984$$Nest$fputchangeDayNightViewProgress(ChatThemeBottomSheet r0, float r1) {
        r0.changeDayNightViewProgress = r1;
    }

    /* renamed from: -$$Nest$fputforceDark, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9985$$Nest$fputforceDark(ChatThemeBottomSheet r0, boolean r1) {
        r0.forceDark = r1;
    }

    /* renamed from: -$$Nest$fputisLightDarkChangeAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9986$$Nest$fputisLightDarkChangeAnimation(ChatThemeBottomSheet r0, boolean r1) {
        r0.isLightDarkChangeAnimation = r1;
    }

    /* renamed from: -$$Nest$fputprevSelectedPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9987$$Nest$fputprevSelectedPosition(ChatThemeBottomSheet r0, int r1) {
        r0.prevSelectedPosition = r1;
    }

    /* renamed from: -$$Nest$fputsubTextTranslation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9988$$Nest$fputsubTextTranslation(ChatThemeBottomSheet r0, float r1) {
        r0.subTextTranslation = r1;
    }

    /* renamed from: -$$Nest$fputthemesLoading, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9989$$Nest$fputthemesLoading(ChatThemeBottomSheet r0, boolean r1) {
        r0.themesLoading = r1;
    }

    /* renamed from: -$$Nest$mhasChanges, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9990$$Nest$mhasChanges(ChatThemeBottomSheet r0) {
        return r0.hasChanges();
    }

    /* renamed from: -$$Nest$mloadNext, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9991$$Nest$mloadNext(ChatThemeBottomSheet r0) {
        r0.loadNext();
    }

    /* renamed from: -$$Nest$monAnimationEnd, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9992$$Nest$monAnimationEnd(ChatThemeBottomSheet r0) {
        r0.onAnimationEnd();
    }

    /* renamed from: -$$Nest$monAnimationStart, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9993$$Nest$monAnimationStart(ChatThemeBottomSheet r0) {
        r0.onAnimationStart();
    }

    /* renamed from: -$$Nest$monDataLoaded, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9994$$Nest$monDataLoaded(ChatThemeBottomSheet r0, List r1) {
        r0.onDataLoaded(r1);
    }

    /* renamed from: -$$Nest$msetItemsAnimationProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9995$$Nest$msetItemsAnimationProgress(ChatThemeBottomSheet r0, float r1) {
        r0.setItemsAnimationProgress(r1);
    }

    /* renamed from: -$$Nest$mshowAsSheet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9996$$Nest$mshowAsSheet(ChatThemeBottomSheet r0, ThemePreviewActivity r1) {
        r0.showAsSheet(r1);
    }

    /* renamed from: -$$Nest$mupdateButtonColors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9997$$Nest$mupdateButtonColors(ChatThemeBottomSheet r0) {
        r0.updateButtonColors();
    }

    public ChatThemeBottomSheet(ChatActivity r20, ChatActivity.ThemeDelegate r21) {
        super(r20.getParentActivity(), true, r21);
        this.prevSelectedPosition = -1;
        this.checkingBoostsLevel = false;
        this.checkedBoostsLevel = false;
        this.subTextTranslation = 0.0f;
        this.chatActivity = r20;
        this.themeDelegate = r21;
        this.originalTheme = r21.getCurrentTheme();
        this.currentWallpaper = r21.getCurrentWallpaper();
        this.originalIsDark = Theme.getActiveTheme().isDark();
        Adapter r2 = new Adapter(this.currentAccount, r20.getDialogId(), r21, 0);
        this.adapter = r2;
        setDimBehind(false);
        setCanDismissWithSwipe(false);
        setApplyBottomPadding(false);
        if (Build.VERSION.SDK_INT < 30) goto L9;
        this.navBarColorKey = -1;
        int r3 = Theme.key_dialogBackgroundGray;
        this.navBarColor = getThemedColor(r3);
        AndroidUtilities.setNavigationBarColor(this, getThemedColor(r3), false);
        if (AndroidUtilities.computePerceivedBrightness(this.navBarColor) <= 0.721d) goto L7;
        boolean r32 = true;
    L8:
        AndroidUtilities.setLightNavigationBar(this, r32);
    L10:
        FrameLayout r33 = new FrameLayout(getContext());
        this.rootLayout = r33;
        setCustomView(r33);
        TextView r34 = new TextView(getContext());
        this.titleView = r34;
        r34.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        r34.setLines(1);
        r34.setSingleLine(true);
        r34.setText(LocaleController.getString(R.string.SelectTheme));
        r34.setTextColor(getThemedColor(Theme.key_dialogTextBlack));
        r34.setTextSize(1, 20.0f);
        r34.setTypeface(AndroidUtilities.bold());
        r34.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(8.0f));
        ImageView r5 = new ImageView(getContext());
        this.backButtonView = r5;
        int r7 = AndroidUtilities.dp(10.0f);
        r5.setPadding(r7, r7, r7, r7);
        BackDrawable r72 = new BackDrawable(false);
        this.backButtonDrawable = r72;
        r5.setImageDrawable(r72);
        r5.setOnClickListener(new ChatThemeBottomSheet$$ExternalSyntheticLambda4(this));
        this.rootLayout.addView(r5, LayoutHelper.createFrame(44, 44.0f, 8388659, 4.0f, -2.0f, 62.0f, 12.0f));
        this.rootLayout.addView(r34, LayoutHelper.createFrame(-1, -2.0f, 8388659, 44.0f, 0.0f, 62.0f, 0.0f));
        int r35 = Theme.key_featuredStickers_addButton;
        int r52 = getThemedColor(r35);
        int r14 = AndroidUtilities.dp(28.0f);
        RLottieDrawable r12 = new RLottieDrawable(R.raw.sun_outline, r14, r14, false, null);
        this.darkThemeDrawable = r12;
        this.forceDark = !Theme.getActiveTheme().isDark();
        setForceDark(Theme.getActiveTheme().isDark(), false);
        r12.setAllowDecodeSingleFrame(true);
        r12.setPlayInDirectionOfCustomEndFrame(true);
        r12.setColorFilter(new PorterDuffColorFilter(r52, PorterDuff.Mode.MULTIPLY));
        AnonymousClass1 r53 = new AnonymousClass1(this, getContext());
        this.darkThemeView = r53;
        r53.setAnimation(r12);
        r53.setScaleType(ImageView.ScaleType.CENTER);
        r53.setOnClickListener(new ChatThemeBottomSheet$$ExternalSyntheticLambda5(this));
        this.rootLayout.addView(r53, LayoutHelper.createFrame(44, 44.0f, 8388661, 0.0f, -2.0f, 7.0f, 0.0f));
        this.scroller = new AnonymousClass2(this, getContext());
        RecyclerListView r54 = new RecyclerListView(getContext());
        this.recyclerView = r54;
        r54.setAdapter(r2);
        r54.setDrawSelection(false);
        r54.setClipChildren(false);
        r54.setClipToPadding(false);
        r54.setHasFixedSize(true);
        r54.setItemAnimator(null);
        r54.setNestedScrollingEnabled(false);
        LinearLayoutManager r22 = new LinearLayoutManager(getContext(), 0, false);
        this.layoutManager = r22;
        r54.setLayoutManager(r22);
        r54.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
        r54.setOnItemClickListener(new ChatThemeBottomSheet$$ExternalSyntheticLambda6(this));
        r54.setOnScrollListener(new AnonymousClass4(this));
        FlickerLoadingView r23 = new FlickerLoadingView(getContext(), this.resourcesProvider);
        this.progressView = r23;
        r23.setViewType(14);
        r23.setVisibility(0);
        this.rootLayout.addView(r23, LayoutHelper.createFrame(-1, 104.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 0.0f, 44.0f, 0.0f, 0.0f));
        this.rootLayout.addView(r54, LayoutHelper.createFrame(-1, 104.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 0.0f, 44.0f, 0.0f, 0.0f));
        View r24 = new View(getContext());
        this.applyButton = r24;
        r24.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(6.0f), getThemedColor(r35), getThemedColor(Theme.key_featuredStickers_addButtonPressed)));
        r24.setOnClickListener(new ChatThemeBottomSheet$$ExternalSyntheticLambda7(this));
        this.rootLayout.addView(r24, LayoutHelper.createFrame(-1, 48.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 16.0f, 162.0f, 16.0f, 16.0f));
        TextView r25 = new TextView(getContext());
        this.chooseBackgroundTextView = r25;
        TextUtils.TruncateAt r36 = TextUtils.TruncateAt.END;
        r25.setEllipsize(r36);
        this.chooseBackgroundTextView.setGravity(17);
        this.chooseBackgroundTextView.setLines(1);
        this.chooseBackgroundTextView.setSingleLine(true);
        TLRPC.WallPaper r26 = this.currentWallpaper;
        TextView r6 = this.chooseBackgroundTextView;
        if (r26 != null) goto L13;
        r6.setText(LocaleController.getString(R.string.ChooseBackgroundFromGallery));
    L14:
        this.chooseBackgroundTextView.setTextSize(1, 15.0f);
        this.chooseBackgroundTextView.setOnClickListener(new ChatThemeBottomSheet$$ExternalSyntheticLambda8(this));
        this.rootLayout.addView(this.chooseBackgroundTextView, LayoutHelper.createFrame(-1, 48.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 16.0f, 162.0f, 16.0f, 16.0f));
        AnimatedTextView r27 = new AnimatedTextView(getContext(), true, true, true);
        this.applyTextView = r27;
        r27.getDrawable().setEllipsizeByGradient(true);
        AnimatedTextView r28 = this.applyTextView;
        r28.adaptWidth = false;
        r28.setGravity(17);
        AnimatedTextView r29 = this.applyTextView;
        int r73 = Theme.key_featuredStickers_buttonText;
        r29.setTextColor(getThemedColor(r73));
        this.applyTextView.setTextSize(AndroidUtilities.dp(15.0f));
        this.applyTextView.setTypeface(AndroidUtilities.bold());
        this.rootLayout.addView(this.applyTextView, LayoutHelper.createFrame(-1, 48.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 16.0f, 162.0f, 16.0f, 16.0f));
        AnimatedTextView r210 = new AnimatedTextView(getContext(), true, true, true);
        this.applySubTextView = r210;
        r210.getDrawable().setEllipsizeByGradient(true);
        AnimatedTextView r211 = this.applySubTextView;
        r211.adaptWidth = false;
        r211.setGravity(17);
        this.applySubTextView.setTextColor(getThemedColor(r73));
        this.applySubTextView.setTextSize(AndroidUtilities.dp(12.0f));
        this.applySubTextView.setAlpha(0.0f);
        this.applySubTextView.setTranslationY(AndroidUtilities.dp(11.0f));
        this.rootLayout.addView(this.applySubTextView, LayoutHelper.createFrame(-1, 48.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 16.0f, 162.0f, 16.0f, 16.0f));
        if (this.currentWallpaper == null) goto L24;
        TextView r212 = new TextView(getContext());
        this.cancelOrResetTextView = r212;
        r212.setEllipsize(r36);
        this.cancelOrResetTextView.setGravity(17);
        this.cancelOrResetTextView.setLines(1);
        this.cancelOrResetTextView.setSingleLine(true);
        this.cancelOrResetTextView.setText(LocaleController.getString(R.string.RestToDefaultBackground));
        this.cancelOrResetTextView.setTextSize(1, 15.0f);
        this.cancelOrResetTextView.setOnClickListener(new ChatThemeBottomSheet$$ExternalSyntheticLambda9(this, r20));
        this.rootLayout.addView(this.cancelOrResetTextView, LayoutHelper.createFrame(-1, 48.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 16.0f, 214.0f, 16.0f, 12.0f));
        TextView r213 = new TextView(getContext());
        this.themeHintTextView = r213;
        r213.setEllipsize(r36);
        this.themeHintTextView.setGravity(17);
        this.themeHintTextView.setLines(1);
        this.themeHintTextView.setSingleLine(true);
        if (r20.getCurrentUser() == null) goto L20;
        String r1 = UserObject.getFirstName(r20.getCurrentUser());
    L23:
        this.themeHintTextView.setText(LocaleController.formatString("ChatThemeApplyHint", R.string.ChatThemeApplyHint, new Object[]{r1}));
        this.themeHintTextView.setTextSize(1, 15.0f);
        this.rootLayout.addView(this.themeHintTextView, LayoutHelper.createFrame(-1, 48.0f, PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES, 16.0f, 214.0f, 16.0f, 12.0f));
        goto L24
    L20:
        if (r20.getCurrentChat() == null) goto L22;
        r1 = r20.getCurrentChat().title;
        goto L23
    L22:
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L24:
        updateButtonColors();
        updateState(false);
        return;
    L13:
        r6.setText(LocaleController.getString(R.string.ChooseANewWallpaper));
        goto L14
    L7:
        r32 = false;
        goto L8
    L9:
        fixNavigationBar(getThemedColor(Theme.key_dialogBackgroundGray));
        goto L10
    }

    public static /* synthetic */ int access$000(ChatThemeBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$100(ChatThemeBottomSheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$200(ChatThemeBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$300(ChatThemeBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$400(ChatThemeBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$500(ChatThemeBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$600(ChatThemeBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$700(ChatThemeBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$800(ChatThemeBottomSheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    private void applySelectedTheme() {
        applySelectedTheme(false);
    }

    private void applySelectedTheme(boolean r11) {
        if (this.checkingBoostsLevel == true) goto L47;
        TL_stories.TL_premium_boostsStatus r0 = this.boostsStatus;
        if (r0 != null) goto L8;
    L11:
        ChatThemeItem r02 = this.selectedItem;
        EmojiThemes r1 = r02.chatTheme;
        Bulletin r2 = null;
        if (r02 != null) goto L14;
    L42:
        dismiss();
        if (r2 == null) goto L46;
        r2.show();
        return;
    L46:
        return;
    L14:
        if (r1 == this.currentTheme) goto L42;
        if (r1.showAsDefaultStub == true) goto L18;
        TLRPC.ChatTheme r03 = r1.getChatTheme();
    L19:
        long r7 = r1.getBusyByUserId();
        TL_stars.TL_starGiftUnique r6 = r1.getThemeGift();
        if (r7 == 0) goto L25;
        if (r6 == null) goto L25;
        if (r11 == true) goto L25;
        AlertsCreator.showGiftThemeApplyConfirm(getContext(), this.resourcesProvider, this.currentAccount, r6, r7, new ChatThemeBottomSheet$$ExternalSyntheticLambda16(this));
        return;
    L25:
        ChatThemeController.getInstance(this.currentAccount).clearWallpaper(this.chatActivity.getDialogId(), false);
        ChatThemeController.getInstance(this.currentAccount).setDialogTheme(this.chatActivity.getDialogId(), r03, true);
        if (hasChanges() == false) goto L28;
        TLRPC.WallPaper r112 = null;
    L29:
        boolean r04 = r1.showAsDefaultStub;
        ChatActivity.ThemeDelegate r3 = this.themeDelegate;
        if (r04 == true) goto L32;
        r3.setCurrentTheme(r1, r112, true, Boolean.valueOf(this.originalIsDark));
    L33:
        this.isApplyClicked = true;
        TLRPC.User r113 = this.chatActivity.getCurrentUser();
        if (r113 == null) goto L42;
        if (r113.self == true) goto L42;
        boolean r05 = r1.showAsDefaultStub;
        StickerSetBulletinLayout r32 = new StickerSetBulletinLayout(getContext(), null, -1, r1.getEmojiAnimatedSticker(), this.chatActivity.getResourceProvider());
        r32.subtitleTextView.setVisibility(8);
        TextView r12 = r32.titleTextView;
        if (r05 == false) goto L40;
        r12.setText(AndroidUtilities.replaceTags(LocaleController.formatString("ThemeAlsoDisabledForHint", R.string.ThemeAlsoDisabledForHint, new Object[]{r113.first_name})));
    L41:
        r32.titleTextView.setTypeface(null);
        r2 = Bulletin.make(this.chatActivity, r32, 2750);
        goto L42
    L40:
        r12.setText(AndroidUtilities.replaceTags(LocaleController.formatString("ThemeAlsoAppliedForHint", R.string.ThemeAlsoAppliedForHint, new Object[]{r113.first_name})));
        goto L41
    L32:
        r3.setCurrentTheme(null, r112, true, Boolean.valueOf(this.originalIsDark));
        goto L33
    L28:
        r112 = this.themeDelegate.getCurrentWallpaper();
        goto L29
    L18:
        r03 = null;
        goto L19
    L8:
        if (r0.level >= this.chatActivity.getMessagesController().channelWallpaperLevelMin) goto L11;
        this.chatActivity.getMessagesController().getBoostsController().userCanBoostChannel(this.chatActivity.getDialogId(), this.boostsStatus, new ChatThemeBottomSheet$$ExternalSyntheticLambda15(this));
        return;
    }

    private void checkBoostsLevel() {
        ChatActivity r0 = this.chatActivity;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.checkingBoostsLevel == false) goto L7;
        return;
    L7:
        if (this.checkedBoostsLevel == false) goto L9;
        return;
    L9:
        if (this.boostsStatus != null) goto L16;
        this.checkingBoostsLevel = true;
        r0.getMessagesController().getBoostsController().getBoostsStats(this.chatActivity.getDialogId(), new ChatThemeBottomSheet$$ExternalSyntheticLambda13(this));
        return;
    }

    private void fixColorsAfterAnotherWindow() {
        if (isDismissed() == false) goto L5;
        return;
    L5:
        if (this.isApplyClicked == true) goto L35;
        int r0 = 0;
        Theme.disallowChangeServiceMessageColor = false;
        if (hasChanges() == false) goto L11;
        TLRPC.WallPaper r1 = null;
    L10:
        TLRPC.WallPaper r4 = r1;
        EmojiThemes r3 = this.selectedItem.chatTheme;
        boolean r12 = r3.showAsDefaultStub;
        ChatActivity.ThemeDelegate r2 = this.themeDelegate;
        if (r12 == false) goto L15;
        r2.setCurrentTheme(null, r4, false, Boolean.valueOf(this.forceDark), true);
    L16:
        ChatAttachAlert r13 = this.chatAttachAlert;
        if (r13 == null) goto L22;
        ChatAttachAlertColorsLayout r14 = r13.colorsLayout;
        if (r14 == null) goto L21;
        r14.updateColors(this.forceDark);
    L21:
        this.chatAttachAlert.checkColors();
    L22:
        Adapter r15 = this.adapter;
        if (r15 != null) goto L25;
        return;
    L25:
        if (r15.items == null) goto L34;
    L26:
        int r16 = this.adapter.items.size();
        Adapter r22 = this.adapter;
        if (r0 >= r16) goto L29;
        ChatThemeItem r17 = r22.items.get(r0);
        r17.themeIndex = this.forceDark ? 1 : 0;
        r0 = r0 + 1;
        goto L26
    L29:
        r22.notifyDataSetChanged();
        return;
    L34:
        return;
    L15:
        r2.setCurrentTheme(r3, r4, false, Boolean.valueOf(this.forceDark), true);
        goto L16
    L11:
        r1 = this.themeDelegate.getCurrentWallpaper();
        goto L10
    }

    private boolean hasChanges() {
        if (this.selectedItem != null) goto L6;
        return false;
    L6:
        EmojiThemes r0 = this.currentTheme;
        ThemeKey r1 = null;
        if (r0 == null) goto L9;
        ThemeKey r02 = r0.getThemeKey();
    L11:
        if (r02 != null) goto L13;
        r02 = ThemeKey.ofEmoticon("❌");
    L13:
        EmojiThemes r3 = this.selectedItem.chatTheme;
        if (r3 == null) goto L16;
        r1 = r3.getThemeKey();
    L16:
        if (r1 != null) goto L19;
        r1 = ThemeKey.ofEmoticon("❌");
    L19:
        return !ThemeKey.equals(r02, r1);
    L9:
        r02 = null;
        goto L11
    }

    private /* synthetic */ void lambda$applySelectedTheme$14() {
        showAsSheet(StatisticActivity.create(this.chatActivity.getMessagesController().getChat(Long.valueOf(-this.chatActivity.getDialogId()))));
    }

    private /* synthetic */ void lambda$applySelectedTheme$15(ChannelBoostsController.CanApplyBoost r8) {
        if (getContext() != null) goto L5;
        return;
    L5:
        LimitReachedBottomSheet r1 = new LimitReachedBottomSheet(this.chatActivity, getContext(), 22, this.currentAccount, this.resourcesProvider);
        r1.setCanApplyBoost(r8);
        r1.setBoostsStats(this.boostsStatus, true);
        r1.setDialogId(this.chatActivity.getDialogId());
        r1.showStatisticButtonInLink(new ChatThemeBottomSheet$$ExternalSyntheticLambda17(this));
        r1.show();
    }

    private /* synthetic */ void lambda$applySelectedTheme$16() {
        applySelectedTheme(true);
    }

    private /* synthetic */ void lambda$checkBoostsLevel$6(TL_stories.TL_premium_boostsStatus r1) {
        this.boostsStatus = r1;
        this.checkedBoostsLevel = true;
        updateState(true);
        this.checkingBoostsLevel = false;
    }

    private /* synthetic */ void lambda$close$10(AlertDialog r1, int r2) {
        dismiss();
    }

    private /* synthetic */ void lambda$close$9(AlertDialog r1, int r2) {
        applySelectedTheme();
    }

    private /* synthetic */ void lambda$didReceivedNotification$11() {
        this.adapter.notifyDataSetChanged();
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        if (hasChanges() == false) goto L6;
        resetToPrimaryState(true);
        updateState(true);
        return;
    L6:
        dismiss();
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        if (this.changeDayNightViewAnimator == null) goto L5;
        return;
    L5:
        setupLightDarkTheme(!this.forceDark);
    }

    private /* synthetic */ void lambda$new$2(View r5, int r6) {
        if (this.adapter.items.get(r6) != this.selectedItem) goto L5;
        return;
    L5:
        if (this.changeDayNightView != null) goto L23;
        this.selectedItem = this.adapter.items.get(r6);
        previewSelectedTheme();
        this.adapter.setSelectedItem(r6);
        this.containerView.postDelayed(new AnonymousClass3(this, r6), 100);
        int r0 = 0;
    L9:
        if (r0 >= this.recyclerView.getChildCount()) goto L15;
        ThemeSmallPreviewView r1 = (ThemeSmallPreviewView) this.recyclerView.getChildAt(r0);
        if (r1 == r5) goto L13;
        r1.cancelAnimation();
    L13:
        r0 = r0 + 1;
        goto L9
    L15:
        if (this.adapter.items.get(r6).chatTheme.showAsDefaultStub == true) goto L17;
        ((ThemeSmallPreviewView) r5).playEmojiAnimation();
    L17:
        updateState(true);
        return;
    }

    private /* synthetic */ void lambda$new$3(View r1) {
        applySelectedTheme();
    }

    private /* synthetic */ void lambda$new$4(View r1) {
        openGalleryForBackground();
    }

    private /* synthetic */ void lambda$new$5(ChatActivity r2, View r3) {
        if (this.currentWallpaper == null) goto L6;
        this.currentWallpaper = null;
        dismiss();
        ChatThemeController.getInstance(this.currentAccount).clearWallpaper(r2.getDialogId(), true);
        return;
    L6:
        dismiss();
    }

    private /* synthetic */ void lambda$onCreate$8() {
        this.hintView.showForView(this.darkThemeView, true);
    }

    private /* synthetic */ void lambda$openGalleryForBackground$17(View r3) {
        ChatAttachAlert.AttachAlertLayout r32 = this.chatAttachAlert.getCurrentAttachLayout();
        ChatAttachAlertPhotoLayout r0 = this.chatAttachAlert.getPhotoLayout();
        AnimatedTextView r1 = this.chatAttachButtonText;
        if (r32 != r0) goto L6;
        r1.setText(LocaleController.getString(R.string.ChooseBackgroundFromGallery));
        this.chatAttachAlert.openColorsLayout();
        this.chatAttachAlert.colorsLayout.updateColors(this.forceDark);
        return;
    L6:
        r1.setText(LocaleController.getString(R.string.SetColorAsBackground));
        ChatAttachAlert r2 = this.chatAttachAlert;
        r2.showLayout(r2.getPhotoLayout());
    }

    private /* synthetic */ void lambda$setupLightDarkTheme$13(boolean r7) {
        Adapter r0 = this.adapter;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.items != null) goto L7;
        return;
    L7:
        if (isDismissed() == true) goto L33;
        setForceDark(r7, true);
        int r2 = 0;
        if (this.selectedItem == null) goto L19;
        this.isLightDarkChangeAnimation = true;
        if (hasChanges() == false) goto L14;
        TLRPC.WallPaper r02 = null;
    L15:
        EmojiThemes r3 = this.selectedItem.chatTheme;
        boolean r4 = r3.showAsDefaultStub;
        ChatActivity.ThemeDelegate r5 = this.themeDelegate;
        if (r4 == false) goto L18;
        r5.setCurrentTheme(null, r02, false, Boolean.valueOf(r7));
        goto L19
    L18:
        r5.setCurrentTheme(r3, r02, false, Boolean.valueOf(r7));
        goto L19
    L14:
        r02 = this.themeDelegate.getCurrentWallpaper();
    L19:
        Adapter r03 = this.adapter;
        if (r03 != null) goto L22;
        return;
    L22:
        if (r03.items == null) goto L32;
    L23:
        int r04 = this.adapter.items.size();
        Adapter r1 = this.adapter;
        if (r2 >= r04) goto L26;
        r1.items.get(r2).themeIndex = r7 ? 1 : 0;
        r2 = r2 + 1;
        goto L23
    L26:
        r1.notifyDataSetChanged();
        return;
    L32:
        return;
    }

    private /* synthetic */ void lambda$showAsSheet$19() {
        fixColorsAfterAnotherWindow();
    }

    private /* synthetic */ void lambda$showAsSheet$20() {
        this.overlayFragment = null;
    }

    private /* synthetic */ void lambda$showAsSheet$22() {
        fixColorsAfterAnotherWindow();
    }

    private /* synthetic */ void lambda$showAsSheet$23() {
        this.overlayFragment = null;
    }

    private /* synthetic */ void lambda$updateApplySubTextTranslation$7(ValueAnimator r2) {
        this.subTextTranslation = ((Float) r2.getAnimatedValue()).floatValue();
        this.applyTextView.setTranslationY((-AndroidUtilities.dp(7.0f)) * this.subTextTranslation);
    }

    private void loadNext() {
        if (this.themesLoading == true) goto L14;
        ChatThemeController r0 = ChatThemeController.getInstance(this.currentAccount);
        if (r0.isAllThemesFullyLoaded() == false) goto L8;
        return;
    L8:
        this.themesLoading = true;
        if (r0.isGiftThemesFullyLoaded() == false) goto L12;
        r0.requestAllChatThemes(new AnonymousClass6(this, r0), false);
        return;
    L12:
        r0.loadNextChatThemes(new AnonymousClass7(this, r0));
        return;
    }

    private void onAnimationEnd() {
        this.isLightDarkChangeAnimation = false;
    }

    private void onAnimationStart() {
        Adapter r0 = this.adapter;
        if (r0 == null) goto L11;
        List<ChatThemeItem> r02 = r0.items;
        if (r02 == null) goto L11;
        Iterator<ChatThemeItem> r03 = r02.iterator();
    L8:
        if (r03.hasNext() == false) goto L11;
        ChatThemeItem r1 = r03.next();
        r1.themeIndex = this.forceDark ? 1 : 0;
    L11:
        if (this.isLightDarkChangeAnimation == true) goto L15;
        setItemsAnimationProgress(1.0f);
        return;
    }

    private void onDataLoaded(List<EmojiThemes> r10) {
        if (r10 != null) goto L4;
        return;
    L4:
        if (r10.isEmpty() == true) goto L38;
        ChatThemeItem r0 = new ChatThemeItem(r10.get(0));
        ArrayList r2 = new ArrayList(r10.size());
        ThemeKey r4 = null;
        if (this.dataLoaded == true) goto L11;
        EmojiThemes r3 = this.themeDelegate.getCurrentTheme();
        this.currentTheme = r3;
        if (r3 == null) goto L11;
        r3.initColors(null);
    L11:
        r2.add(0, r0);
        if (this.dataLoaded == true) goto L14;
        this.selectedItem = r0;
    L14:
        EmojiThemes r02 = this.currentTheme;
        if (r02 == null) goto L17;
        r4 = r02.getThemeKey();
    L17:
        int r32 = 1;
        boolean r5 = false;
    L19:
        if (r32 >= r10.size()) goto L25;
        EmojiThemes r6 = r10.get(r32);
        ChatThemeItem r7 = new ChatThemeItem(r6);
        r6.loadPreviewColors(this.currentAccount);
        r7.themeIndex = this.forceDark ? 1 : 0;
        if (ThemeKey.equals(r6.getThemeKey(), r4) == false) goto L23;
        r2.add(1, r7);
        r5 = true;
    L24:
        r32 = r32 + 1;
        r5 = r5;
        goto L19
    L23:
        r2.add(r7);
        goto L24
    L25:
        EmojiThemes r102 = this.currentTheme;
        if (r102 == null) goto L29;
        if (r5 == true) goto L29;
        ChatThemeItem r33 = new ChatThemeItem(r102);
        this.currentTheme.loadPreviewColors(this.currentAccount);
        r33.themeIndex = this.forceDark ? 1 : 0;
        r2.add(1, r33);
    L29:
        this.adapter.setItems(r2);
        this.darkThemeView.setVisibility(0);
        if (this.dataLoaded == true) goto L32;
        resetToPrimaryState(false);
        this.recyclerView.animate().alpha(1.0f).setDuration(150).start();
    L32:
        this.dataLoaded = true;
        updateState(true);
        return;
    }

    private void openGalleryForBackground() {
        Activity r1 = this.chatActivity.getParentActivity();
        ChatActivity r2 = this.chatActivity;
        ChatAttachAlert r0 = new ChatAttachAlert(r1, r2, false, false, false, r2.getResourceProvider());
        this.chatAttachAlert = r0;
        r0.drawNavigationBar = true;
        r0.setupPhotoPicker(LocaleController.getString(R.string.ChooseBackground));
        this.chatAttachAlert.setDelegate(new AnonymousClass13(this));
        this.chatAttachAlert.setMaxSelectedPhotos(1, false);
        this.chatAttachAlert.init();
        this.chatAttachAlert.getPhotoLayout().loadGalleryPhotos();
        this.chatAttachAlert.show();
        this.chatAttachButton = new AnonymousClass14(this, getContext());
        AnimatedTextView r02 = new AnimatedTextView(getContext(), true, true, true);
        this.chatAttachButtonText = r02;
        r02.setTextSize(AndroidUtilities.dp(14.0f));
        this.chatAttachButtonText.setText(LocaleController.getString(R.string.SetColorAsBackground));
        this.chatAttachButtonText.setGravity(17);
        AnimatedTextView r03 = this.chatAttachButtonText;
        int r22 = Theme.key_featuredStickers_addButton;
        r03.setTextColor(getThemedColor(r22));
        this.chatAttachButton.addView(this.chatAttachButtonText, LayoutHelper.createFrame(-1, -2, 17));
        this.chatAttachButton.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(0.0f), getThemedColor(Theme.key_windowBackgroundWhite), ColorUtils.setAlphaComponent(getThemedColor(r22), 76)));
        this.chatAttachButton.setOnClickListener(new ChatThemeBottomSheet$$ExternalSyntheticLambda12(this));
        this.chatAttachAlert.sizeNotifierFrameLayout.addView(this.chatAttachButton, LayoutHelper.createFrame(-1, -2, 80));
    }

    public static void openGalleryForBackground(Activity r9, BaseFragment r10, long r11, Theme.ResourcesProvider r13, Utilities.Callback<TLRPC.WallPaper> r14, ThemePreviewActivity.DayNightSwitchDelegate r15, TL_stories.TL_premium_boostsStatus r16) {
        ChatAttachAlert r0 = new ChatAttachAlert(r9, r10, false, false, false, r13);
        r0.drawNavigationBar = true;
        r0.setupPhotoPicker(LocaleController.getString(R.string.ChooseBackground));
        r0.setDelegate(new AnonymousClass12(r0, r16, r13, r15, r11, r14, r10));
        r0.setMaxSelectedPhotos(1, false);
        r0.init();
        r0.getPhotoLayout().loadGalleryPhotos();
        r0.show();
    }

    private void previewSelectedTheme() {
        if (isDismissed() == false) goto L5;
        return;
    L5:
        if (this.isApplyClicked == true) goto L18;
        this.isLightDarkChangeAnimation = false;
        this.chatActivity.forceDisallowApplyWallpeper = false;
        if (hasChanges() == false) goto L10;
        TLRPC.WallPaper r0 = null;
    L11:
        EmojiThemes r2 = this.selectedItem.chatTheme;
        boolean r3 = r2.showAsDefaultStub;
        ChatActivity.ThemeDelegate r4 = this.themeDelegate;
        if (r3 == false) goto L15;
        r4.setCurrentTheme(null, r0, true, Boolean.valueOf(this.forceDark));
        return;
    L15:
        r4.setCurrentTheme(r2, r0, true, Boolean.valueOf(this.forceDark));
        return;
    L10:
        r0 = this.currentWallpaper;
        goto L11
    }

    private void resetToPrimaryState(boolean r7) {
        List<ChatThemeItem> r0 = this.adapter.items;
        if (this.currentTheme == null) goto L22;
        int r1 = 0;
    L6:
        if (r1 == r0.size()) goto L11;
        if (ThemeKey.equals(r0.get(r1).chatTheme.getThemeKey(), this.currentTheme.getThemeKey()) == true) goto L9;
        r1 = r1 + 1;
        goto L6
    L9:
        this.selectedItem = r0.get(r1);
    L12:
        if (r1 == (-1)) goto L26;
        this.prevSelectedPosition = r1;
        this.adapter.setSelectedItem(r1);
        if (r1 > 0) goto L16;
    L18:
        int r02 = Math.min(r1, this.adapter.items.size() - 1);
        if (r7 == false) goto L21;
        this.recyclerView.smoothScrollToPosition(r02);
        goto L26
    L21:
        this.layoutManager.scrollToPositionWithOffset(r02, 0);
        goto L26
    L16:
        if (r1 >= (r0.size() / 2)) goto L18;
        r1 = r1 - 1;
    L26:
        previewSelectedTheme();
        return;
    L11:
        r1 = -1;
        goto L12
    L22:
        this.selectedItem = r0.get(0);
        this.adapter.setSelectedItem(0);
        if (r7 == false) goto L25;
        this.recyclerView.smoothScrollToPosition(0);
        goto L26
    L25:
        this.layoutManager.scrollToPositionWithOffset(0, 0);
        goto L26
    }

    private void setForceDark(boolean r3, boolean r4) {
        if (this.forceDark == r3) goto L24;
        this.forceDark = r3;
        int r0 = 0;
        if (r4 == false) goto L15;
        RLottieDrawable r42 = this.darkThemeDrawable;
        if (r3 == false) goto L10;
        r0 = r42.getFramesCount();
    L10:
        r42.setCustomEndFrame(r0);
        RLottieImageView r2 = this.darkThemeView;
        if (r2 == null) goto L22;
        r2.playAnimation();
        return;
    L22:
        return;
    L15:
        if (r3 == false) goto L17;
        int r32 = this.darkThemeDrawable.getFramesCount() - 1;
    L18:
        this.darkThemeDrawable.setCurrentFrame(r32, false, true);
        this.darkThemeDrawable.setCustomEndFrame(r32);
        RLottieImageView r22 = this.darkThemeView;
        if (r22 == null) goto L23;
        r22.invalidate();
        return;
    L23:
        return;
    L17:
        r32 = 0;
        goto L18
    }

    private void setItemsAnimationProgress(float r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.adapter.getItemCount()) goto L6;
        this.adapter.items.get(r0).animationProgress = r3;
        r0 = r0 + 1;
        goto L4
    }

    private void showAsSheet(BaseFragment r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        BaseFragment.BottomSheetParams r0 = new BaseFragment.BottomSheetParams();
        r0.transitionFromLeft = true;
        r0.allowNestedScroll = false;
        r4.setResourceProvider(this.chatActivity.getResourceProvider());
        r0.onOpenAnimationFinished = new ChatThemeBottomSheet$$ExternalSyntheticLambda18();
        r0.onPreFinished = new ChatThemeBottomSheet$$ExternalSyntheticLambda19(this);
        r0.onDismiss = new ChatThemeBottomSheet$$ExternalSyntheticLambda20(this);
        r0.occupyNavigationBar = true;
        ChatActivity r1 = this.chatActivity;
        this.overlayFragment = r4;
        r1.showAsSheet(r4, r0);
    }

    private void showAsSheet(ThemePreviewActivity r4) {
        BaseFragment.BottomSheetParams r0 = new BaseFragment.BottomSheetParams();
        r0.transitionFromLeft = true;
        r0.allowNestedScroll = false;
        r4.setResourceProvider(this.chatActivity.getResourceProvider());
        r4.setOnSwitchDayNightDelegate(new AnonymousClass15(this));
        r0.onOpenAnimationFinished = new ChatThemeBottomSheet$$ExternalSyntheticLambda21();
        r0.onPreFinished = new ChatThemeBottomSheet$$ExternalSyntheticLambda22(this);
        r0.onDismiss = new ChatThemeBottomSheet$$ExternalSyntheticLambda23(this);
        r0.occupyNavigationBar = true;
        this.overlayFragment = r4;
        this.chatActivity.showAsSheet(r4, r0);
    }

    private void updateApplySubTextTranslation(boolean r4, boolean r5) {
        ValueAnimator r0 = this.subTextTranslationAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.subTextTranslationAnimator = null;
    L5:
        float r02 = 0.0f;
        if (r5 == false) goto L12;
        float r52 = this.subTextTranslation;
        if (r4 == false) goto L10;
        r02 = 1.0f;
    L10:
        ValueAnimator r53 = ValueAnimator.ofFloat(new float[]{r52, r02});
        this.subTextTranslationAnimator = r53;
        r53.addUpdateListener(new ChatThemeBottomSheet$$ExternalSyntheticLambda14(this));
        this.subTextTranslationAnimator.addListener(new AnonymousClass5(this, r4));
        this.subTextTranslationAnimator.start();
        return;
    L12:
        if (r4 == false) goto L14;
        r02 = 1.0f;
    L14:
        this.subTextTranslation = r02;
        this.applyTextView.setTranslationY((-AndroidUtilities.dp(7.0f)) * this.subTextTranslation);
    }

    private void updateButtonColors() {
        TextView r0 = this.themeHintTextView;
        if (r0 == null) goto L5;
        r0.setTextColor(getThemedColor(Theme.key_dialogTextGray));
        this.themeHintTextView.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(6.0f), 0, ColorUtils.setAlphaComponent(getThemedColor(Theme.key_featuredStickers_addButton), 76)));
    L5:
        TextView r02 = this.cancelOrResetTextView;
        if (r02 == null) goto L8;
        int r4 = Theme.key_text_RedRegular;
        r02.setTextColor(getThemedColor(r4));
        this.cancelOrResetTextView.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(6.0f), 0, ColorUtils.setAlphaComponent(getThemedColor(r4), 76)));
    L8:
        ImageView r03 = this.backButtonView;
        int r42 = Theme.key_dialogTextBlack;
        r03.setBackground(Theme.createSelectorDrawable(ColorUtils.setAlphaComponent(getThemedColor(r42), 30), 1));
        this.backButtonDrawable.setColor(getThemedColor(r42));
        this.backButtonDrawable.setRotatedColor(getThemedColor(r42));
        this.backButtonView.invalidate();
        RLottieImageView r04 = this.darkThemeView;
        int r43 = Theme.key_featuredStickers_addButton;
        r04.setBackground(Theme.createSelectorDrawable(ColorUtils.setAlphaComponent(getThemedColor(r43), 30), 1));
        this.chooseBackgroundTextView.setTextColor(getThemedColor(Theme.key_dialogTextBlue));
        this.chooseBackgroundTextView.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(6.0f), 0, ColorUtils.setAlphaComponent(getThemedColor(r43), 76)));
    }

    private void updateState(boolean r14) {
        TLRPC.Chat r0 = this.chatActivity.getCurrentChat();
        if (r0 == null) goto L6;
        checkBoostsLevel();
    L6:
        if (this.dataLoaded == true) goto L9;
        this.backButtonDrawable.setRotation(1.0f, r14);
        this.applyButton.setEnabled(false);
        AndroidUtilities.updateViewVisibilityAnimated(this.chooseBackgroundTextView, false, 0.9f, false, r14);
        AndroidUtilities.updateViewVisibilityAnimated(this.cancelOrResetTextView, false, 0.9f, false, r14);
        AndroidUtilities.updateViewVisibilityAnimated(this.applyButton, false, 1.0f, false, r14);
        AndroidUtilities.updateViewVisibilityAnimated(this.applyTextView, false, 0.9f, false, r14);
        AndroidUtilities.updateViewVisibilityAnimated(this.applySubTextView, false, 0.9f, false, r14);
        AndroidUtilities.updateViewVisibilityAnimated(this.themeHintTextView, false, 0.9f, false, r14);
        AndroidUtilities.updateViewVisibilityAnimated(this.progressView, true, 1.0f, true, r14);
        return;
    L9:
        AndroidUtilities.updateViewVisibilityAnimated(this.progressView, false, 1.0f, true, r14);
        boolean r1 = hasChanges();
        BackDrawable r6 = this.backButtonDrawable;
        if (r1 == false) goto L38;
        r6.setRotation(0.0f, r14);
        this.applyButton.setEnabled(true);
        ChatThemeItem r12 = this.selectedItem;
        if (r12 == null) goto L20;
        EmojiThemes r13 = r12.chatTheme;
        if (r13 == null) goto L20;
        if (r13.showAsDefaultStub == false) goto L20;
        if (r13.wallpaper != null) goto L20;
        this.applyTextView.setText(LocaleController.getString(R.string.ChatResetTheme));
    L30:
        boolean r7 = false;
    L31:
        if (r14 == true) goto L33;
    L35:
        boolean r02 = false;
    L36:
        updateApplySubTextTranslation(r7, r02);
        AndroidUtilities.updateViewVisibilityAnimated(this.chooseBackgroundTextView, false, 0.9f, false, r14);
        AndroidUtilities.updateViewVisibilityAnimated(this.cancelOrResetTextView, false, 0.9f, false, r14);
        AndroidUtilities.updateViewVisibilityAnimated(this.applyButton, true, 1.0f, false, r14);
        AndroidUtilities.updateViewVisibilityAnimated(this.applyTextView, true, 0.9f, false, r14);
        AndroidUtilities.updateViewVisibilityAnimated(this.applySubTextView, r7, 0.9f, false, 0.7f, r14, null);
        AndroidUtilities.updateViewVisibilityAnimated(this.themeHintTextView, true, 0.9f, false, r14);
        return;
    L33:
        if (this.applyTextView.getAlpha() <= 0.8f) goto L35;
        r02 = true;
    L20:
        this.applyTextView.setText(LocaleController.getString(R.string.ChatApplyTheme));
        if (r0 == null) goto L30;
        TL_stories.TL_premium_boostsStatus r03 = this.boostsStatus;
        if (r03 == null) goto L30;
        if (r03.level >= this.chatActivity.getMessagesController().channelWallpaperLevelMin) goto L30;
        SpannableStringBuilder r04 = new SpannableStringBuilder("l");
        if (this.lockSpan != null) goto L29;
        ColoredImageSpan r15 = new ColoredImageSpan(R.drawable.mini_switch_lock);
        this.lockSpan = r15;
        r15.setTopOffset(1);
    L29:
        r04.setSpan(this.lockSpan, 0, 1, 33);
        r04.append(" ").append(LocaleController.formatPluralString("ReactionLevelRequiredBtn", this.chatActivity.getMessagesController().channelWallpaperLevelMin, new Object[0]));
        this.applySubTextView.setText(r04);
        r7 = true;
        goto L31
    L38:
        r6.setRotation(1.0f, r14);
        this.applyButton.setEnabled(false);
        AndroidUtilities.updateViewVisibilityAnimated(this.chooseBackgroundTextView, true, 0.9f, false, r14);
        AndroidUtilities.updateViewVisibilityAnimated(this.cancelOrResetTextView, true, 0.9f, false, r14);
        AndroidUtilities.updateViewVisibilityAnimated(this.applyButton, false, 1.0f, false, r14);
        AndroidUtilities.updateViewVisibilityAnimated(this.applyTextView, false, 0.9f, false, r14);
        AndroidUtilities.updateViewVisibilityAnimated(this.applySubTextView, false, 0.9f, false, r14);
        AndroidUtilities.updateViewVisibilityAnimated(this.themeHintTextView, false, 0.9f, false, r14);
    }

    public void close() {
        if (hasChanges() == false) goto L6;
        AlertDialog.Builder r0 = new AlertDialog.Builder(getContext(), this.resourcesProvider);
        r0.setTitle(LocaleController.getString(R.string.ChatThemeSaveDialogTitle));
        r0.setMessage(LocaleController.getString(R.string.ChatThemeSaveDialogText));
        r0.setPositiveButton(LocaleController.getString(R.string.ChatThemeSaveDialogApply), new ChatThemeBottomSheet$$ExternalSyntheticLambda1(this));
        r0.setNegativeButton(LocaleController.getString(R.string.ChatThemeSaveDialogDiscard), new ChatThemeBottomSheet$$ExternalSyntheticLambda2(this));
        r0.show();
        return;
    L6:
        dismiss();
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    @SuppressLint({"NotifyDataSetChanged"})
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.emojiLoaded) goto L6;
        NotificationCenter.getInstance(this.currentAccount).doOnIdle(new ChatThemeBottomSheet$$ExternalSyntheticLambda0(this));
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
        super.dismiss();
        this.chatActivity.forceDisallowApplyWallpeper = false;
        if (this.isApplyClicked == true) goto L9;
        TLRPC.WallPaper r0 = this.themeDelegate.getCurrentWallpaper();
        if (r0 != null) goto L7;
        r0 = this.currentWallpaper;
    L7:
        this.themeDelegate.setCurrentTheme(this.originalTheme, r0, true, Boolean.valueOf(this.originalIsDark));
    L9:
        if (this.forceDark != this.originalIsDark) goto L11;
        return;
    L11:
        if (Theme.getActiveTheme().isDark() != this.originalIsDark) goto L13;
        Theme.ThemeInfo r02 = Theme.getActiveTheme();
    L29:
        Theme.applyTheme(r02, false, this.originalIsDark);
        return;
    L13:
        SharedPreferences r03 = ApplicationLoader.applicationContext.getSharedPreferences("themeconfig", 0);
        String r3 = "Blue";
        String r2 = r03.getString("lastDayTheme", "Blue");
        if (Theme.getTheme(r2) != null) goto L16;
    L19:
        String r4 = "Dark Blue";
        String r04 = r03.getString("lastDarkTheme", "Dark Blue");
        if (Theme.getTheme(r04) == null) goto L26;
        if (Theme.getTheme(r04).isDark() == false) goto L26;
        r4 = r04;
    L26:
        if (this.originalIsDark == false) goto L28;
        r02 = Theme.getTheme(r4);
        goto L29
    L28:
        r02 = Theme.getTheme(r3);
        goto L29
    L16:
        if (Theme.getTheme(r2).isDark() == true) goto L19;
        r3 = r2;
        goto L19
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        AnonymousClass8 r7 = new AnonymousClass8(this);
        ArrayList<ThemeDescription> r9 = new ArrayList();
        if (this.chatActivity.forceDisallowRedrawThemeDescriptions == false) goto L8;
        BaseFragment r1 = this.overlayFragment;
        if ((r1 instanceof ThemePreviewActivity) == false) goto L8;
        r9.addAll(((ThemePreviewActivity) r1).getThemeDescriptionsInternal());
        return r9;
    L8:
        ChatAttachAlert r12 = this.chatAttachAlert;
        if (r12 == null) goto L11;
        r9.addAll(r12.getThemeDescriptions());
    L11:
        r9.add(new ThemeDescription(null, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, new Drawable[]{this.shadowDrawable}, r7, Theme.key_dialogBackground));
        r9.add(new ThemeDescription(this.titleView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_dialogTextBlack));
        r9.add(new ThemeDescription(this.recyclerView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{ThemeSmallPreviewView.class}, null, null, null, Theme.key_dialogBackgroundGray));
        r9.add(new ThemeDescription(this.applyButton, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_featuredStickers_addButton));
        r9.add(new ThemeDescription(this.applyButton, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, Theme.key_featuredStickers_addButtonPressed));
        int r13 = r9.size();
        int r2 = 0;
    L12:
        if (r2 >= r13) goto L14;
        ThemeDescription r3 = r9.get(r2);
        r2 = r2 + 1;
        ChatActivity.ThemeDelegate r4 = this.themeDelegate;
        r3.resourcesProvider = r4;
        goto L12
    L14:
        return r9;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void onBackPressed() {
        close();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean onContainerTouchEvent(MotionEvent r5) {
        if (r5 != null) goto L5;
    L16:
        return false;
    L5:
        if (hasChanges() == false) goto L16;
        int r1 = (int) r5.getX();
        if (((int) r5.getY()) >= this.containerView.getTop()) goto L10;
    L14:
        this.chatActivity.getFragmentView().dispatchTouchEvent(r5);
        return true;
    L10:
        if (r1 < this.containerView.getLeft()) goto L14;
        if (r1 > this.containerView.getRight()) goto L14;
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void onContainerTranslationYChanged(float r1) {
        HintView r0 = this.hintView;
        if (r0 == null) goto L6;
        r0.hide();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void onCreate(Bundle r8) {
        super.onCreate(r8);
        ChatThemeController r82 = ChatThemeController.getInstance(this.currentAccount);
        r82.preloadAllWallpaperThumbs(true);
        r82.preloadAllWallpaperThumbs(false);
        r82.preloadAllWallpaperImages(true);
        r82.preloadAllWallpaperImages(false);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
        this.isApplyClicked = false;
        if (r82.isAllThemesFullyLoaded() == false) goto L5;
        onDataLoaded(r82.getEmojiThemes(7));
    L7:
        if (this.chatActivity.getCurrentUser() != null) goto L9;
        return;
    L9:
        if (SharedConfig.dayNightThemeSwitchHintCount > 0) goto L11;
        return;
    L11:
        if (this.chatActivity.getCurrentUser().self == true) goto L20;
        SharedConfig.updateDayNightThemeSwitchHintCount(SharedConfig.dayNightThemeSwitchHintCount - 1);
        HintView r83 = new HintView(getContext(), 9, this.chatActivity.getResourceProvider());
        this.hintView = r83;
        r83.setVisibility(4);
        this.hintView.setShowingDuration(5000);
        this.hintView.setBottomOffset(-AndroidUtilities.dp(8.0f));
        boolean r84 = this.forceDark;
        HintView r0 = this.hintView;
        if (r84 == false) goto L15;
        r0.setText(AndroidUtilities.replaceTags(LocaleController.formatString("ChatThemeDaySwitchTooltip", R.string.ChatThemeDaySwitchTooltip, new Object[0])));
    L16:
        AndroidUtilities.runOnUIThread(new ChatThemeBottomSheet$$ExternalSyntheticLambda3(this), 1500);
        this.container.addView(this.hintView, LayoutHelper.createFrame(-2, -2.0f, 51, 10.0f, 0.0f, 10.0f, 0.0f));
        return;
    L15:
        r0.setText(AndroidUtilities.replaceTags(LocaleController.formatString("ChatThemeNightSwitchTooltip", R.string.ChatThemeNightSwitchTooltip, new Object[0])));
        goto L16
    L20:
        return;
    L5:
        loadNext();
        goto L7
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public void setupLightDarkTheme(boolean r17) {
        if (isDismissed() == false) goto L5;
        return;
    L5:
        ValueAnimator r0 = this.changeDayNightViewAnimator;
        if (r0 == null) goto L8;
        r0.cancel();
    L8:
        FrameLayout r02 = (FrameLayout) this.chatActivity.getParentActivity().getWindow().getDecorView();
        FrameLayout r13 = (FrameLayout) getWindow().getDecorView();
        Bitmap r9 = Bitmap.createBitmap(r13.getWidth(), r13.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas r4 = new Canvas(r9);
        this.darkThemeView.setAlpha(0.0f);
        r02.draw(r4);
        r13.draw(r4);
        this.darkThemeView.setAlpha(1.0f);
        Paint r8 = new Paint(1);
        r8.setColor(-16777216);
        r8.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Paint r10 = new Paint(1);
        r10.setFilterBitmap(true);
        int[] r2 = new int[2];
        this.darkThemeView.getLocationInWindow(r2);
        float r11 = r2[0];
        float r12 = r2[1];
        Shader.TileMode r22 = Shader.TileMode.CLAMP;
        r10.setShader(new BitmapShader(r9, r22, r22));
        AnonymousClass9 r03 = new AnonymousClass9(this, getContext(), r17, r4, r11 + (this.darkThemeView.getMeasuredWidth() / 2.0f), r12 + (this.darkThemeView.getMeasuredHeight() / 2.0f), Math.max(r9.getHeight(), r9.getWidth()) * 0.9f, r8, r9, r10, r11, r12);
        this.changeDayNightView = r03;
        r03.setOnTouchListener(new ChatThemeBottomSheet$$ExternalSyntheticLambda10());
        this.changeDayNightViewProgress = 0.0f;
        ValueAnimator r04 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.changeDayNightViewAnimator = r04;
        r04.addUpdateListener(new AnonymousClass10(this));
        this.changeDayNightViewAnimator.addListener(new AnonymousClass11(this));
        this.changeDayNightViewAnimator.setDuration(400);
        this.changeDayNightViewAnimator.setInterpolator(Easings.easeInOutQuad);
        this.changeDayNightViewAnimator.start();
        r13.addView(this.changeDayNightView, new ViewGroup.LayoutParams(-1, -1));
        AndroidUtilities.runOnUIThread(new ChatThemeBottomSheet$$ExternalSyntheticLambda11(this, r17));
    }
}
