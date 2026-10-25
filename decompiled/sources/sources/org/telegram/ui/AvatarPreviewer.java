package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.math.MathUtils;
import androidx.core.util.Consumer;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.mediarouter.media.PlatformMediaRouter1RouteProvider;
import com.google.android.gms.cast.MediaError;
import java.util.Objects;
import kotlin.time.DurationKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.utils.ViewOutlineProviderImpl;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFileDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadialProgress2;
import org.telegram.ui.Components.ScrimOptions;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceBitmap;
import org.telegram.ui.Components.blur3.utils.Blur3Utils;
import org.telegram.ui.Components.chat.ViewPositionWatcher;

/* loaded from: classes6.dex */
public class AvatarPreviewer {

    @SuppressLint({"StaticFieldLeak"})
    private static AvatarPreviewer INSTANCE;
    private Layout layout;
    private ViewGroup view;
    private boolean visible;
    private WindowManager windowManager;

    /* renamed from: org.telegram.ui.AvatarPreviewer$1, reason: invalid class name */
    public class AnonymousClass1 extends Layout {
        final /* synthetic */ AvatarPreviewer this$0;

        public AnonymousClass1(AvatarPreviewer r1, Context r2, Theme.ResourcesProvider r3, Callback r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.AvatarPreviewer.Layout
        public void onHideFinish() {
            if (AvatarPreviewer.m6250$$Nest$fgetvisible(this.this$0) == false) goto L9;
            AvatarPreviewer.m6254$$Nest$fputvisible(this.this$0, false);
            if (AvatarPreviewer.m6248$$Nest$fgetlayout(this.this$0).getParent() == null) goto L7;
            AvatarPreviewer.m6251$$Nest$fgetwindowManager(this.this$0).removeView(AvatarPreviewer.m6248$$Nest$fgetlayout(this.this$0));
        L7:
            AvatarPreviewer.m6248$$Nest$fgetlayout(this.this$0).recycle();
            AvatarPreviewer.m6252$$Nest$fputlayout(this.this$0, null);
            AvatarPreviewer.m6249$$Nest$fgetview(this.this$0).requestDisallowInterceptTouchEvent(false);
            AvatarPreviewer.m6253$$Nest$fputview(this.this$0, null);
            AvatarPreviewer.m6255$$Nest$fputwindowManager(this.this$0, null);
            return;
        }
    }

    public static class AvatarView extends FrameLayout {
        private BackupImageView backupImageView;
        private ValueAnimator progressHideAnimator;
        private ValueAnimator progressShowAnimator;
        private final RadialProgress2 radialProgress;
        private final int radialProgressSize;
        private boolean showProgress;

        /* renamed from: org.telegram.ui.AvatarPreviewer$AvatarView$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AvatarView this$0;

            public AnonymousClass1(AvatarView r1) {
                this.this$0 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                AvatarView.m6257$$Nest$fputshowProgress(this.this$0, false);
                this.this$0.invalidate();
            }
        }

        /* renamed from: $r8$lambda$-dvxMSzf7-rSVyJM8bB2s_BYP6o, reason: not valid java name */
        public static /* synthetic */ void m6256$r8$lambda$dvxMSzf7rSVyJM8bB2s_BYP6o(AvatarView r0, ValueAnimator r1) {
            r0.lambda$dispatchDraw$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$aFqddiyUWjJJe1DTiEnarALinSc(AvatarView r0, ValueAnimator r1) {
            r0.lambda$dispatchDraw$1(r1);
        }

        /* renamed from: -$$Nest$fputshowProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m6257$$Nest$fputshowProgress(AvatarView r0, boolean r1) {
            r0.showProgress = r1;
        }

        public AvatarView(Context r3, Theme.ResourcesProvider r4) {
            super(r3);
            this.radialProgressSize = AndroidUtilities.dp(64.0f);
            BackupImageView r0 = new BackupImageView(r3);
            this.backupImageView = r0;
            r0.setAspectFit(true);
            this.backupImageView.setRoundRadius(AndroidUtilities.dp(12.0f));
            addView(this.backupImageView, LayoutHelper.createFrame(-1, -1.0f));
            RadialProgress2 r32 = new RadialProgress2(this, r4);
            this.radialProgress = r32;
            r32.setOverrideAlpha(0.0f);
            r32.setIcon(10, false, false);
            r32.setColors(1107296256, 1107296256, -1, -1);
        }

        private /* synthetic */ void lambda$dispatchDraw$0(ValueAnimator r1) {
            invalidate();
        }

        private /* synthetic */ void lambda$dispatchDraw$1(ValueAnimator r1) {
            invalidate();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r8) {
            super.dispatchDraw(r8);
            if (this.showProgress == false) goto L26;
            Drawable r0 = this.backupImageView.getImageReceiver().getDrawable();
            if ((r0 instanceof AnimatedFileDrawable) == false) goto L16;
            if (((AnimatedFileDrawable) r0).getDurationMs() <= 0) goto L16;
            ValueAnimator r02 = this.progressShowAnimator;
            if (r02 == null) goto L14;
            r02.cancel();
            if (this.radialProgress.getProgress() >= 1.0f) goto L13;
            this.radialProgress.setProgress(1.0f, true);
        L13:
            ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{((Float) this.progressShowAnimator.getAnimatedValue()).floatValue(), 0.0f});
            this.progressHideAnimator = r03;
            r03.addListener(new AnonymousClass1(this));
            this.progressHideAnimator.addUpdateListener(new AvatarPreviewer$AvatarView$$ExternalSyntheticLambda0(this));
            this.progressHideAnimator.setDuration(250);
            this.progressHideAnimator.start();
        L18:
            ValueAnimator r04 = this.progressHideAnimator;
            if (r04 == null) goto L22;
            this.radialProgress.setOverrideAlpha(((Float) r04.getAnimatedValue()).floatValue());
            this.radialProgress.draw(r8);
            return;
        L22:
            ValueAnimator r05 = this.progressShowAnimator;
            if (r05 == null) goto L27;
            this.radialProgress.setOverrideAlpha(((Float) r05.getAnimatedValue()).floatValue());
            this.radialProgress.draw(r8);
            return;
        L27:
            return;
        L14:
            this.showProgress = false;
        L16:
            if (this.progressShowAnimator != null) goto L18;
            ValueAnimator r06 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.progressShowAnimator = r06;
            r06.addUpdateListener(new AvatarPreviewer$AvatarView$$ExternalSyntheticLambda1(this));
            this.progressShowAnimator.setStartDelay(250);
            this.progressShowAnimator.setDuration(250);
            this.progressShowAnimator.start();
            goto L18
        }

        public boolean getShowProgress() {
            return this.showProgress;
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            int r12 = getWidth() / 2;
            int r22 = getHeight() / 2;
            RadialProgress2 r32 = this.radialProgress;
            int r0 = this.radialProgressSize;
            r32.setProgressRect(r12 - r0, r22 - r0, r12 + r0, r22 + r0);
        }

        public void setImage(int r15, ImageLocation r16, String r17, ImageLocation r18, String r19, ImageLocation r20, String r21, BitmapDrawable r22, Object r23) {
            this.backupImageView.getImageReceiver().setCurrentAccount(r15);
            this.backupImageView.getImageReceiver().setImage(r16, r17, r18, r19, r20, r21, r22, 0, null, r23, 1);
            this.backupImageView.onNewImageSet();
        }

        public void setProgress(float r2) {
            this.radialProgress.setProgress(r2, true);
        }

        public void setShowProgress(boolean r1) {
            this.showProgress = r1;
            invalidate();
        }
    }

    public interface Callback {
        void onMenuClick(MenuItem r1);
    }

    public static class ChatInfoLoadTask extends InfoLoadTask<TLRPC.Chat, TLRPC.ChatFull> {
        public ChatInfoLoadTask(TLRPC.Chat r2, int r3) {
            super(r2, r3, NotificationCenter.chatInfoDidLoad);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.telegram.ui.AvatarPreviewer.InfoLoadTask
        public void load() {
            MessagesController.getInstance(UserConfig.selectedAccount).loadFullChat(((TLRPC.Chat) this.argument).id, this.classGuid, false);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.telegram.ui.AvatarPreviewer.InfoLoadTask
        public void onReceiveNotification(Object... r5) {
            TLRPC.ChatFull r52 = (TLRPC.ChatFull) r5[0];
            if (r52 != null) goto L5;
            return;
        L5:
            if (r52.id != ((TLRPC.Chat) this.argument).id) goto L9;
            onResult(r52);
            return;
        }
    }

    public static class Data {
        private final String imageFilter;
        private final ImageLocation imageLocation;
        private final InfoLoadTask<?, ?> infoLoadTask;
        private final MenuItem[] menuItems;
        private final Object parentObject;
        private final BitmapDrawable thumb;
        private final String thumbImageFilter;
        private final ImageLocation thumbImageLocation;
        private final String videoFileName;
        private final String videoFilter;
        private final ImageLocation videoLocation;

        /* renamed from: -$$Nest$fgetimageFilter, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m6258$$Nest$fgetimageFilter(Data r0) {
            return r0.imageFilter;
        }

        /* renamed from: -$$Nest$fgetimageLocation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ImageLocation m6259$$Nest$fgetimageLocation(Data r0) {
            return r0.imageLocation;
        }

        /* renamed from: -$$Nest$fgetinfoLoadTask, reason: not valid java name */
        public static /* bridge */ /* synthetic */ InfoLoadTask m6260$$Nest$fgetinfoLoadTask(Data r0) {
            return r0.infoLoadTask;
        }

        /* renamed from: -$$Nest$fgetmenuItems, reason: not valid java name */
        public static /* bridge */ /* synthetic */ MenuItem[] m6261$$Nest$fgetmenuItems(Data r0) {
            return r0.menuItems;
        }

        /* renamed from: -$$Nest$fgetparentObject, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Object m6262$$Nest$fgetparentObject(Data r0) {
            return r0.parentObject;
        }

        /* renamed from: -$$Nest$fgetthumb, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BitmapDrawable m6263$$Nest$fgetthumb(Data r0) {
            return r0.thumb;
        }

        /* renamed from: -$$Nest$fgetthumbImageFilter, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m6264$$Nest$fgetthumbImageFilter(Data r0) {
            return r0.thumbImageFilter;
        }

        /* renamed from: -$$Nest$fgetthumbImageLocation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ImageLocation m6265$$Nest$fgetthumbImageLocation(Data r0) {
            return r0.thumbImageLocation;
        }

        /* renamed from: -$$Nest$fgetvideoFileName, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m6266$$Nest$fgetvideoFileName(Data r0) {
            return r0.videoFileName;
        }

        /* renamed from: -$$Nest$fgetvideoFilter, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m6267$$Nest$fgetvideoFilter(Data r0) {
            return r0.videoFilter;
        }

        /* renamed from: -$$Nest$fgetvideoLocation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ImageLocation m6268$$Nest$fgetvideoLocation(Data r0) {
            return r0.videoLocation;
        }

        private Data(ImageLocation r1, ImageLocation r2, ImageLocation r3, String r4, String r5, String r6, String r7, BitmapDrawable r8, Object r9, MenuItem[] r10, InfoLoadTask<?, ?> r11) {
            this.imageLocation = r1;
            this.thumbImageLocation = r2;
            this.videoLocation = r3;
            this.imageFilter = r4;
            this.thumbImageFilter = r5;
            this.videoFilter = r6;
            this.videoFileName = r7;
            this.thumb = r8;
            this.parentObject = r9;
            this.menuItems = r10;
            this.infoLoadTask = r11;
        }

        public static Data of(TLRPC.Chat r13, int r14, MenuItem... r15) {
            ImageLocation r2 = ImageLocation.getForUserOrChat(r13, 0);
            ImageLocation r3 = ImageLocation.getForUserOrChat(r13, 1);
            BitmapDrawable r0 = null;
            if (r3 != null) goto L5;
        L7:
            String r6 = null;
        L8:
            if (r13 == null) goto L13;
            TLRPC.ChatPhoto r1 = r13.photo;
            if (r1 == null) goto L13;
            r0 = r1.strippedBitmap;
        L13:
            return new Data(r2, r3, null, null, r6, null, null, r0, r13, r15, new ChatInfoLoadTask(r13, r14));
        L5:
            if ((r3.photoSize instanceof TLRPC.TL_photoStrippedSize) == false) goto L7;
            r6 = "b";
            goto L8
        }

        public static Data of(TLRPC.Chat r13, TLRPC.ChatFull r14, MenuItem... r15) {
            ImageLocation r2 = ImageLocation.getForUserOrChat(r13, 0);
            ImageLocation r3 = ImageLocation.getForUserOrChat(r13, 1);
            String r0 = null;
            if (r3 != null) goto L5;
        L7:
            String r6 = null;
        L8:
            if (r13 == null) goto L12;
            TLRPC.ChatPhoto r1 = r13.photo;
            if (r1 == null) goto L12;
            BitmapDrawable r9 = r1.strippedBitmap;
        L13:
            TLRPC.Photo r12 = r14.chat_photo;
            if (r12 != null) goto L16;
        L18:
            ImageLocation r4 = null;
            String r8 = null;
        L19:
            if (r4 == null) goto L24;
            if (r4.imageType != 2) goto L24;
            r0 = ImageLoader.AUTOPLAY_FILTER;
        L24:
            return new Data(r2, r3, r4, null, r6, r0, r8, r9, r13, r15, null);
        L16:
            if (r12.video_sizes.isEmpty() == true) goto L18;
            TLRPC.VideoSize r16 = FileLoader.getClosestVideoSizeWithSize(r14.chat_photo.video_sizes, 1000);
            r4 = ImageLocation.getForPhoto(r16, r14.chat_photo);
            r8 = FileLoader.getAttachFileName(r16);
        L12:
            r9 = null;
            goto L13
        L5:
            if ((r3.photoSize instanceof TLRPC.TL_photoStrippedSize) == false) goto L7;
            r6 = "b";
            goto L8
        }

        public static Data of(TLRPC.User r13, int r14, MenuItem... r15) {
            ImageLocation r2 = ImageLocation.getForUserOrChat(r13, 0);
            ImageLocation r3 = ImageLocation.getForUserOrChat(r13, 1);
            BitmapDrawable r0 = null;
            if (r3 != null) goto L5;
        L7:
            String r6 = null;
        L8:
            if (r13 == null) goto L13;
            TLRPC.UserProfilePhoto r1 = r13.photo;
            if (r1 == null) goto L13;
            r0 = r1.strippedBitmap;
        L13:
            return new Data(r2, r3, null, null, r6, null, null, r0, r13, r15, new UserInfoLoadTask(r13, r14));
        L5:
            if ((r3.photoSize instanceof TLRPC.TL_photoStrippedSize) == false) goto L7;
            r6 = "b";
            goto L8
        }

        public static Data of(TLRPC.User r12, TLRPC.UserFull r13, MenuItem... r14) {
            if (r12 == null) goto L4;
        L3:
            TLRPC.User r9 = r12;
            ImageLocation r122 = ImageLocation.getForUserOrChat(r9, 0);
            if (r122 != null) goto L11;
            if (r13 == null) goto L11;
            TLRPC.Photo r0 = r13.profile_photo;
            if (r0 == null) goto L11;
            r122 = ImageLocation.getForPhoto(FileLoader.getClosestPhotoSizeWithSize(r0.sizes, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN), r13.profile_photo);
        L11:
            ImageLocation r1 = r122;
            ImageLocation r2 = ImageLocation.getForUserOrChat(r9, 1);
            String r123 = null;
            if (r2 != null) goto L14;
        L16:
            String r5 = null;
        L17:
            if (r9 == null) goto L21;
            TLRPC.UserProfilePhoto r02 = r9.photo;
            if (r02 == null) goto L21;
            BitmapDrawable r8 = r02.strippedBitmap;
        L22:
            if (r13 == null) goto L28;
            TLRPC.Photo r03 = r13.profile_photo;
            if (r03 == null) goto L28;
            if (r03.video_sizes.isEmpty() == true) goto L28;
            TLRPC.VideoSize r04 = FileLoader.getClosestVideoSizeWithSize(r13.profile_photo.video_sizes, 1000);
            ImageLocation r3 = ImageLocation.getForPhoto(r04, r13.profile_photo);
            String r7 = FileLoader.getAttachFileName(r04);
        L29:
            if (r3 == null) goto L34;
            if (r3.imageType != 2) goto L34;
            r123 = ImageLoader.AUTOPLAY_FILTER;
        L34:
            return new Data(r1, r2, r3, null, r5, r123, r7, r8, r9, r14, null);
        L28:
            r3 = null;
            r7 = null;
        L21:
            r8 = null;
            goto L22
        L14:
            if ((r2.photoSize instanceof TLRPC.TL_photoStrippedSize) == false) goto L16;
            r5 = "b";
            goto L17
        L4:
            r12 = r13.user;
            goto L3
        }
    }

    public static abstract class InfoLoadTask<A, B> {
        protected final A argument;
        protected final int classGuid;
        private boolean loading;
        private final NotificationCenter notificationCenter;
        private final int notificationId;
        private final NotificationCenter.NotificationCenterDelegate observer;
        private Consumer<B> onResult;

        /* renamed from: org.telegram.ui.AvatarPreviewer$InfoLoadTask$1, reason: invalid class name */
        public class AnonymousClass1 implements NotificationCenter.NotificationCenterDelegate {
            final /* synthetic */ InfoLoadTask this$0;

            public AnonymousClass1(InfoLoadTask r1) {
                this.this$0 = r1;
            }

            @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
            public void didReceivedNotification(int r1, int r2, Object... r3) {
                if (InfoLoadTask.m6269$$Nest$fgetloading(this.this$0) == true) goto L5;
                return;
            L5:
                if (r1 != InfoLoadTask.m6270$$Nest$fgetnotificationId(this.this$0)) goto L9;
                this.this$0.onReceiveNotification(r3);
                return;
            }
        }

        /* renamed from: -$$Nest$fgetloading, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m6269$$Nest$fgetloading(InfoLoadTask r0) {
            return r0.loading;
        }

        /* renamed from: -$$Nest$fgetnotificationId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m6270$$Nest$fgetnotificationId(InfoLoadTask r0) {
            return r0.notificationId;
        }

        public InfoLoadTask(A r2, int r3, int r4) {
            this.observer = new AnonymousClass1(this);
            this.argument = r2;
            this.classGuid = r3;
            this.notificationId = r4;
            this.notificationCenter = NotificationCenter.getInstance(UserConfig.selectedAccount);
        }

        public final void cancel() {
            if (this.loading == false) goto L6;
            this.loading = false;
            this.notificationCenter.removeObserver(this.observer, this.notificationId);
            return;
        }

        public abstract void load();

        public final void load(Consumer<B> r3) {
            if (this.loading == true) goto L6;
            this.loading = true;
            this.onResult = r3;
            this.notificationCenter.addObserver(this.observer, this.notificationId);
            load();
            return;
        }

        public abstract void onReceiveNotification(Object... r1);

        public final void onResult(B r2) {
            if (this.loading == false) goto L6;
            cancel();
            this.onResult.accept(r2);
            return;
        }
    }

    public static abstract class Layout extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
        private final AvatarView avatarView;
        private Bitmap blurBitmap;
        private final Matrix blurMatrix;
        private View blurView;
        private final Callback callback;
        private final FrameLayout container;
        private final BlurredBackgroundDrawableViewFactory iBlur3Factory;
        private final BlurredBackgroundSourceBitmap iBlur3SourceBitmap;
        private InfoLoadTask<?, ?> infoLoadTask;
        private final ActionBarPopupWindow.ActionBarPopupWindowLayout menu;
        private MenuItem[] menuItems;
        private OnBackInvokedCallback onBackInvokedCallback;
        private OnBackInvokedDispatcher onBackInvokedDispatcher;
        private AnimatorSet openAnimator;
        private final Interpolator openInterpolator;
        private boolean preparingBlur;
        private boolean recycled;
        private final Theme.ResourcesProvider resourcesProvider;
        private boolean showing;
        private String videoFileName;

        /* renamed from: org.telegram.ui.AvatarPreviewer$Layout$1, reason: invalid class name */
        public class AnonymousClass1 extends FrameLayout {
            final /* synthetic */ Layout this$0;

            public AnonymousClass1(Layout r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
            public void onLayout(boolean r9, int r10, int r11, int r12, int r13) {
                int r0 = ((r12 - r10) - getPaddingLeft()) - getPaddingRight();
                int r1 = ((r13 - r11) - getPaddingTop()) - getPaddingBottom();
                int r2 = Math.min(r0, r1) - AndroidUtilities.dp(24.0f);
                int r3 = Math.min(AndroidUtilities.dp(60.0f), r2);
                int r4 = (r1 - r3) - AndroidUtilities.dp(48.0f);
                Layout.m6277$$Nest$fgetmenu(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r0, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(r4, Integer.MIN_VALUE));
                int r02 = MathUtils.clamp((r1 - Layout.m6277$$Nest$fgetmenu(this.this$0).getMeasuredHeight()) - AndroidUtilities.dp(48.0f), r3, r2);
                Layout.m6275$$Nest$fgetavatarView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r02, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r02, TLObject.FLAG_30));
                int r14 = (((r1 - r02) - Layout.m6277$$Nest$fgetmenu(this.this$0).getMeasuredHeight()) - AndroidUtilities.dp(48.0f)) / 2;
                FrameLayout.LayoutParams r22 = (FrameLayout.LayoutParams) Layout.m6275$$Nest$fgetavatarView(this.this$0).getLayoutParams();
                FrameLayout.LayoutParams r32 = (FrameLayout.LayoutParams) Layout.m6277$$Nest$fgetmenu(this.this$0).getLayoutParams();
                r22.topMargin = AndroidUtilities.dp(8.0f) + r14;
                r32.topMargin = ((r14 + AndroidUtilities.dp(8.0f)) + r02) + AndroidUtilities.dp(4.0f);
                r32.leftMargin = AndroidUtilities.dp(4.0f);
                r32.rightMargin = AndroidUtilities.dp(4.0f);
                super.onLayout(r9, r10, r11, r12, r13);
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r1, int r2) {
                setMeasuredDimension(View.MeasureSpec.getSize(r1), View.MeasureSpec.getSize(r2));
            }
        }

        /* renamed from: org.telegram.ui.AvatarPreviewer$Layout$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ Layout this$0;
            final /* synthetic */ boolean val$showing;

            public AnonymousClass2(Layout r1, boolean r2) {
                this.this$0 = r1;
                this.val$showing = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                if (this.val$showing == true) goto L6;
                this.this$0.setVisibility(4);
                this.this$0.onHideFinish();
                return;
            }
        }

        /* renamed from: $r8$lambda$5IGL66pgClQo6Ew50_U0ip3j-1s, reason: not valid java name */
        public static /* synthetic */ void m6271$r8$lambda$5IGL66pgClQo6Ew50_U0ip3j1s(Layout r0, MenuItem r1, View r2) {
            r0.lambda$setData$3(r1, r2);
        }

        /* renamed from: $r8$lambda$FEyVlxgpidTBlTTAmoX-9zU9EVw, reason: not valid java name */
        public static /* synthetic */ void m6272$r8$lambda$FEyVlxgpidTBlTTAmoX9zU9EVw(Layout r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$G9Y7oAPV0KRYqMPimvouiYG1ntM(Layout r0) {
            r0.lambda$registerBackCallback$6();
        }

        public static /* synthetic */ void $r8$lambda$U7uTpjdezzE8UZkVOyusjEJkn1Y(Layout r0, boolean r1, ValueAnimator r2) {
            r0.lambda$setShowing$5(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$d4rQO7kYWqxxpAvYXaWvAygj4xU(Layout r0, Bitmap r1, Bitmap r2) {
            r0.lambda$prepareBlurBitmap$1(r1, r2);
        }

        /* renamed from: $r8$lambda$dLpIB-RpmpWrOjtN4C_ExDsvZFo, reason: not valid java name */
        public static /* synthetic */ void m6273$r8$lambda$dLpIBRpmpWrOjtN4C_ExDsvZFo(Layout r0, Data r1, Object r2) {
            r0.lambda$setData$2(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$gMvFuJx99e8zvH4K7od7hyZ3Vd4(Layout r0) {
            r0.prepareBlurBitmap();
        }

        /* renamed from: $r8$lambda$qNYBwyv-pfpAGxWll-bYlt8vuQg, reason: not valid java name */
        public static /* synthetic */ void m6274$r8$lambda$qNYBwyvpfpAGxWllbYlt8vuQg(Layout r0, boolean r1, ValueAnimator r2) {
            r0.lambda$setShowing$4(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$xn1TE5sejJmF37jBuNvL9dmEoZ8(Layout r0) {
            r0.registerBackCallback();
        }

        /* renamed from: -$$Nest$fgetavatarView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AvatarView m6275$$Nest$fgetavatarView(Layout r0) {
            return r0.avatarView;
        }

        /* renamed from: -$$Nest$fgetcontainer, reason: not valid java name */
        public static /* bridge */ /* synthetic */ FrameLayout m6276$$Nest$fgetcontainer(Layout r0) {
            return r0.container;
        }

        /* renamed from: -$$Nest$fgetmenu, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ActionBarPopupWindow.ActionBarPopupWindowLayout m6277$$Nest$fgetmenu(Layout r0) {
            return r0.menu;
        }

        /* renamed from: -$$Nest$msetShowing, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m6278$$Nest$msetShowing(Layout r0, boolean r1) {
            r0.setShowing(r1);
        }

        public Layout(Context r8, Theme.ResourcesProvider r9, Callback r10) {
            super(r8);
            this.openInterpolator = new OvershootInterpolator(1.02f);
            BlurredBackgroundSourceBitmap r0 = new BlurredBackgroundSourceBitmap();
            this.iBlur3SourceBitmap = r0;
            BlurredBackgroundDrawableViewFactory r1 = new BlurredBackgroundDrawableViewFactory(r0);
            this.iBlur3Factory = r1;
            this.blurMatrix = new Matrix();
            this.callback = r10;
            this.resourcesProvider = r9;
            r1.setSourceRootView(new ViewPositionWatcher(this), this);
            View r102 = new View(r8);
            this.blurView = r102;
            r102.setOnClickListener(new AvatarPreviewer$Layout$$ExternalSyntheticLambda6(this));
            addView(this.blurView, LayoutHelper.createFrame(-1, -1.0f));
            AnonymousClass1 r103 = new AnonymousClass1(this, r8);
            this.container = r103;
            addView(r103, LayoutHelper.createFrame(-1, -1.0f));
            AvatarView r02 = new AvatarView(r8, r9);
            this.avatarView = r02;
            r02.setOutlineProvider(ViewOutlineProviderImpl.boundsWithPaddingRoundRect(0, AndroidUtilities.dp(12.0f)));
            r02.setElevation(AndroidUtilities.dp(4.0f));
            int r3 = 1;
            r02.setClipToOutline(true);
            r103.addView(r02, LayoutHelper.createFrame(0, 0, 1));
            if (Build.VERSION.SDK_INT < 28) goto L5;
            r02.setOutlineSpotShadowColor(Integer.MIN_VALUE);
            r02.setOutlineAmbientShadowColor(Integer.MIN_VALUE);
        L5:
            ActionBarPopupWindow.ActionBarPopupWindowLayout r03 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(r8, R.drawable.popup_fixed_alert, r9, 0);
            this.menu = r03;
            r03.setBackground(r1.create(r03).setColorProvider(BlurredBackgroundProviderImpl.scrimMenuBackground(r9)).setPadding(AndroidUtilities.dp(8.0f)).setHasPadding(true).setRadius(AndroidUtilities.dp(12.0f)));
            if (AndroidUtilities.isTablet() == true) goto L9;
            r3 = PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES;
        L9:
            r103.addView(r03, LayoutHelper.createFrameRelatively(-2.0f, -2.0f, r3));
        }

        private void checkBitmapMatrix() {
            Blur3Utils.checkBitmapSourceMatrixScale(this.iBlur3SourceBitmap, this);
            ActionBarPopupWindow.ActionBarPopupWindowLayout r1 = this.menu;
            if (r1 == null) goto L6;
            r1.invalidate();
            return;
        }

        private /* synthetic */ void lambda$new$0(View r1) {
            setShowing(false);
        }

        private /* synthetic */ void lambda$prepareBlurBitmap$1(Bitmap r3, Bitmap r4) {
            this.blurBitmap = r3;
            this.blurView.setBackground(new BitmapDrawable(r3));
            this.preparingBlur = false;
            this.iBlur3SourceBitmap.setBitmap(r4);
            checkBitmapMatrix();
        }

        private /* synthetic */ void lambda$registerBackCallback$6() {
            setShowing(false);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private /* synthetic */ void lambda$setData$2(Data r2, Object r3) {
            if (this.recycled == false) goto L5;
            return;
        L5:
            if ((r3 instanceof TLRPC.UserFull) == false) goto L9;
            setData(Data.of((TLRPC.User) Data.m6260$$Nest$fgetinfoLoadTask(r2).argument, (TLRPC.UserFull) r3, Data.m6261$$Nest$fgetmenuItems(r2)));
            return;
        L9:
            if ((r3 instanceof TLRPC.ChatFull) == false) goto L13;
            setData(Data.of((TLRPC.Chat) Data.m6260$$Nest$fgetinfoLoadTask(r2).argument, (TLRPC.ChatFull) r3, Data.m6261$$Nest$fgetmenuItems(r2)));
            return;
        }

        private /* synthetic */ void lambda$setData$3(MenuItem r1, View r2) {
            setShowing(false);
            this.callback.onMenuClick(r1);
        }

        private /* synthetic */ void lambda$setShowing$4(boolean r5, ValueAnimator r6) {
            float r62 = ((Float) r6.getAnimatedValue()).floatValue();
            if (r5 == true) goto L5;
            r62 = 1.0f - r62;
        L5:
            float r52 = MathUtils.clamp(r62, 0.0f, 1.0f);
            float r2 = (0.3f * r62) + 0.7f;
            this.container.setScaleX(r2);
            this.container.setScaleY(r2);
            this.container.setAlpha(r52);
            float r0 = 1.0f - r62;
            this.avatarView.setTranslationY(AndroidUtilities.dp(40.0f) * r0);
            this.menu.setTranslationY((-AndroidUtilities.dp(70.0f)) * r0);
            float r63 = (r62 * 0.05f) + 0.95f;
            this.menu.setScaleX(r63);
            this.menu.setScaleY(r63);
        }

        private /* synthetic */ void lambda$setShowing$5(boolean r1, ValueAnimator r2) {
            float r22 = ((Float) r2.getAnimatedValue()).floatValue();
            if (r1 == true) goto L5;
            r22 = 1.0f - r22;
        L5:
            this.blurView.setAlpha(r22);
            invalidate();
        }

        private void prepareBlurBitmap() {
            if (this.preparingBlur == false) goto L5;
            return;
        L5:
            this.preparingBlur = true;
            ScrimOptions.makeGlobalBlurBitmaps(new AvatarPreviewer$Layout$$ExternalSyntheticLambda8(this));
        }

        private void recycleInfoLoadTask() {
            InfoLoadTask<?, ?> r0 = this.infoLoadTask;
            if (r0 == null) goto L6;
            r0.cancel();
            this.infoLoadTask = null;
            return;
        }

        private void registerBackCallback() {
            if (Build.VERSION.SDK_INT >= 33) goto L5;
            return;
        L5:
            if (this.onBackInvokedCallback != null) goto L14;
            OnBackInvokedDispatcher r0 = findOnBackInvokedDispatcher();
            this.onBackInvokedDispatcher = r0;
            if (r0 != null) goto L11;
            post(new AvatarPreviewer$Layout$$ExternalSyntheticLambda4(this));
            return;
        L11:
            AvatarPreviewer$Layout$$ExternalSyntheticLambda5 r02 = new AvatarPreviewer$Layout$$ExternalSyntheticLambda5(this);
            this.onBackInvokedCallback = r02;
            this.onBackInvokedDispatcher.registerOnBackInvokedCallback(DurationKt.NANOS_IN_MILLIS, r02);
            return;
        }

        private void setShowing(boolean r7) {
            if (this.showing != r7) goto L5;
            return;
        L5:
            this.showing = r7;
            ValueAnimator r1 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            if (r7 == false) goto L8;
            TimeInterpolator r2 = this.openInterpolator;
        L9:
            r1.setInterpolator(r2);
            r1.addUpdateListener(new AvatarPreviewer$Layout$$ExternalSyntheticLambda0(this, r7));
            ValueAnimator r22 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            r22.addUpdateListener(new AvatarPreviewer$Layout$$ExternalSyntheticLambda1(this, r7));
            AnimatorSet r3 = this.openAnimator;
            if (r3 == null) goto L12;
            r3.cancel();
        L12:
            AnimatorSet r32 = new AnimatorSet();
            this.openAnimator = r32;
            if (r7 == false) goto L15;
            long r4 = 190;
        L16:
            r32.setDuration(r4);
            this.openAnimator.playTogether(new Animator[]{r1, r22});
            this.openAnimator.addListener(new AnonymousClass2(this, r7));
            this.openAnimator.start();
            return;
        L15:
            r4 = 150;
            goto L16
        L8:
            r2 = CubicBezierInterpolator.EASE_OUT_QUINT;
            goto L9
        }

        private void unregisterBackCallback() {
            if (Build.VERSION.SDK_INT < 33) goto L11;
            OnBackInvokedDispatcher r0 = this.onBackInvokedDispatcher;
            if (r0 == null) goto L12;
            OnBackInvokedCallback r1 = this.onBackInvokedCallback;
            if (r1 == null) goto L13;
            r0.unregisterOnBackInvokedCallback(r1);
            this.onBackInvokedDispatcher = null;
            this.onBackInvokedCallback = null;
            return;
        L13:
            return;
        L12:
            return;
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r4, int r5, Object... r6) {
            if (this.avatarView.getShowProgress() == true) goto L5;
            return;
        L5:
            if (TextUtils.isEmpty(this.videoFileName) == false) goto L8;
            return;
        L8:
            if (r4 != NotificationCenter.fileLoaded) goto L14;
            if (TextUtils.equals((String) r6[0], this.videoFileName) == false) goto L20;
            this.avatarView.setProgress(1.0f);
            return;
        L20:
            return;
        L14:
            if (r4 == NotificationCenter.fileLoadProgressChanged) goto L16;
            return;
        L16:
            if (TextUtils.equals((String) r6[0], this.videoFileName) == false) goto L22;
            Long r42 = (Long) r6[1];
            Long r52 = (Long) r6[2];
            this.avatarView.setProgress(Math.min(1.0f, r42.longValue() / r52.longValue()));
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent r3) {
            if (r3.getKeyCode() == 4) goto L10;
            if (r3.getKeyCode() == 111) goto L10;
            return super.dispatchKeyEvent(r3);
        L10:
            if (getKeyDispatcherState() != null) goto L14;
            return super.dispatchKeyEvent(r3);
        L14:
            if (r3.getAction() != 0) goto L22;
            if (r3.getRepeatCount() != 0) goto L22;
            KeyEvent.DispatcherState r0 = getKeyDispatcherState();
            if (r0 == null) goto L20;
            r0.startTracking(r3, this);
        L20:
            return true;
        L22:
            if (r3.getAction() != 1) goto L32;
            KeyEvent.DispatcherState r02 = getKeyDispatcherState();
            if (r02 == null) goto L32;
            if (r02.isTracking(r3) == false) goto L32;
            if (r3.isCanceled() == true) goto L32;
            setShowing(false);
            return true;
        L32:
            return super.dispatchKeyEvent(r3);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            NotificationCenter.getInstance(UserConfig.selectedAccount).addObserver(this, NotificationCenter.fileLoaded);
            NotificationCenter.getInstance(UserConfig.selectedAccount).addObserver(this, NotificationCenter.fileLoadProgressChanged);
            registerBackCallback();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            NotificationCenter.getInstance(UserConfig.selectedAccount).removeObserver(this, NotificationCenter.fileLoaded);
            NotificationCenter.getInstance(UserConfig.selectedAccount).removeObserver(this, NotificationCenter.fileLoadProgressChanged);
            unregisterBackCallback();
        }

        public abstract void onHideFinish();

        @Override // android.view.View
        public void onSizeChanged(int r1, int r2, int r3, int r4) {
            if (r1 == 0) goto L7;
            if (r2 == 0) goto L7;
            if (this.showing == false) goto L7;
            this.blurView.setBackground(null);
            AndroidUtilities.runOnUIThread(new AvatarPreviewer$Layout$$ExternalSyntheticLambda7(this));
        L7:
            checkBitmapMatrix();
        }

        public void recycle() {
            this.recycled = true;
            recycleInfoLoadTask();
        }

        public void setData(Data r15) {
            this.menuItems = Data.m6261$$Nest$fgetmenuItems(r15);
            AvatarView r0 = this.avatarView;
            if (Data.m6268$$Nest$fgetvideoLocation(r15) == null) goto L5;
            boolean r1 = true;
        L6:
            r0.setShowProgress(r1);
            this.videoFileName = Data.m6266$$Nest$fgetvideoFileName(r15);
            recycleInfoLoadTask();
            if (Data.m6260$$Nest$fgetinfoLoadTask(r15) == null) goto L9;
            InfoLoadTask<?, ?> r02 = Data.m6260$$Nest$fgetinfoLoadTask(r15);
            this.infoLoadTask = r02;
            r02.load(new AvatarPreviewer$Layout$$ExternalSyntheticLambda2(this, r15));
        L9:
            this.avatarView.setImage(UserConfig.selectedAccount, Data.m6268$$Nest$fgetvideoLocation(r15), Data.m6267$$Nest$fgetvideoFilter(r15), Data.m6259$$Nest$fgetimageLocation(r15), Data.m6258$$Nest$fgetimageFilter(r15), Data.m6265$$Nest$fgetthumbImageLocation(r15), Data.m6264$$Nest$fgetthumbImageFilter(r15), Data.m6263$$Nest$fgetthumb(r15), Data.m6262$$Nest$fgetparentObject(r15));
            this.menu.removeInnerViews();
            int r152 = 0;
        L10:
            MenuItem[] r03 = this.menuItems;
            if (r152 >= r03.length) goto L21;
            MenuItem r04 = r03[r152];
            String r9 = LocaleController.getString(MenuItem.m6280$$Nest$fgetlabelKey(r04), MenuItem.m6281$$Nest$fgetlabelResId(r04));
            if (r152 != 0) goto L15;
            boolean r5 = true;
        L17:
            if (r152 != (this.menuItems.length - 1)) goto L19;
            boolean r6 = true;
        L20:
            ActionBarMenuSubItem r12 = ActionBarMenuItem.addItem(r5, r6, this.menu, MenuItem.m6279$$Nest$fgeticonResId(r04), r9, false, this.resourcesProvider);
            r12.setTag(Integer.valueOf(r152));
            r12.setOnClickListener(new AvatarPreviewer$Layout$$ExternalSyntheticLambda3(this, r04));
            r152 = r152 + 1;
            goto L10
        L19:
            r6 = false;
            goto L20
        L15:
            r5 = false;
            goto L17
        L21:
            setShowing(true);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public enum MenuItem extends Enum<MenuItem> {
        private static final /* synthetic */ MenuItem[] $VALUES = null;
        public static final MenuItem MENTION = null;
        public static final MenuItem OPEN_CHANNEL = null;
        public static final MenuItem OPEN_GROUP = null;
        public static final MenuItem OPEN_PROFILE = null;
        public static final MenuItem SEARCH_MESSAGES = null;
        public static final MenuItem SEND_MESSAGE = null;
        private final int iconResId;
        private final String labelKey;
        private final int labelResId;

        private static /* synthetic */ MenuItem[] $values() {
            return new MenuItem[]{OPEN_PROFILE, OPEN_CHANNEL, OPEN_GROUP, SEND_MESSAGE, MENTION, SEARCH_MESSAGES};
        }

        /* renamed from: -$$Nest$fgeticonResId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m6279$$Nest$fgeticonResId(MenuItem r0) {
            return r0.iconResId;
        }

        /* renamed from: -$$Nest$fgetlabelKey, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m6280$$Nest$fgetlabelKey(MenuItem r0) {
            return r0.labelKey;
        }

        /* renamed from: -$$Nest$fgetlabelResId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m6281$$Nest$fgetlabelResId(MenuItem r0) {
            return r0.labelResId;
        }

        static {
            OPEN_PROFILE = new MenuItem("OPEN_PROFILE", 0, "OpenProfile", R.string.OpenProfile, R.drawable.msg_openprofile);
            OPEN_CHANNEL = new MenuItem("OPEN_CHANNEL", 1, "OpenChannel2", R.string.OpenChannel2, R.drawable.msg_channel);
            OPEN_GROUP = new MenuItem("OPEN_GROUP", 2, "OpenGroup2", R.string.OpenGroup2, R.drawable.msg_discussion);
            SEND_MESSAGE = new MenuItem("SEND_MESSAGE", 3, "SendMessage", R.string.SendMessage, R.drawable.msg_discussion);
            MENTION = new MenuItem("MENTION", 4, "Mention", R.string.Mention, R.drawable.msg_mention);
            SEARCH_MESSAGES = new MenuItem("SEARCH_MESSAGES", 5, "AvatarPreviewSearchMessages", R.string.AvatarPreviewSearchMessages, R.drawable.msg_search);
            $VALUES = $values();
        }

        MenuItem(String r1, int r2, String r3, int r4, int r5) {
            this.labelKey = r3;
            this.labelResId = r4;
            this.iconResId = r5;
        }

        public static MenuItem valueOf(String r1) {
            return (MenuItem) Enum.valueOf(MenuItem.class, r1);
        }

        public static MenuItem[] values() {
            return (MenuItem[]) $VALUES.clone();
        }
    }

    public static class UserInfoLoadTask extends InfoLoadTask<TLRPC.User, TLRPC.UserFull> {
        public UserInfoLoadTask(TLRPC.User r2, int r3) {
            super(r2, r3, NotificationCenter.userInfoDidLoad);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.telegram.ui.AvatarPreviewer.InfoLoadTask
        public void load() {
            MessagesController.getInstance(UserConfig.selectedAccount).loadUserInfo((TLRPC.User) this.argument, false, this.classGuid);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.telegram.ui.AvatarPreviewer.InfoLoadTask
        public void onReceiveNotification(Object... r5) {
            if (((Long) r5[0]).longValue() != ((TLRPC.User) this.argument).id) goto L6;
            onResult((TLRPC.UserFull) r5[1]);
            return;
        }
    }

    public static /* synthetic */ WindowInsetsCompat $r8$lambda$A9C8ymR00A15z93uAmpDax6vFUM(AvatarPreviewer r0, View r1, WindowInsetsCompat r2) {
        return r0.lambda$show$0(r1, r2);
    }

    /* renamed from: -$$Nest$fgetlayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Layout m6248$$Nest$fgetlayout(AvatarPreviewer r0) {
        return r0.layout;
    }

    /* renamed from: -$$Nest$fgetview, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewGroup m6249$$Nest$fgetview(AvatarPreviewer r0) {
        return r0.view;
    }

    /* renamed from: -$$Nest$fgetvisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m6250$$Nest$fgetvisible(AvatarPreviewer r0) {
        return r0.visible;
    }

    /* renamed from: -$$Nest$fgetwindowManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WindowManager m6251$$Nest$fgetwindowManager(AvatarPreviewer r0) {
        return r0.windowManager;
    }

    /* renamed from: -$$Nest$fputlayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6252$$Nest$fputlayout(AvatarPreviewer r0, Layout r1) {
        r0.layout = r1;
    }

    /* renamed from: -$$Nest$fputview, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6253$$Nest$fputview(AvatarPreviewer r0, ViewGroup r1) {
        r0.view = r1;
    }

    /* renamed from: -$$Nest$fputvisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6254$$Nest$fputvisible(AvatarPreviewer r0, boolean r1) {
        r0.visible = r1;
    }

    /* renamed from: -$$Nest$fputwindowManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6255$$Nest$fputwindowManager(AvatarPreviewer r0, WindowManager r1) {
        r0.windowManager = r1;
    }

    public AvatarPreviewer() {
    }

    public static boolean canPreview(Data r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (Data.m6259$$Nest$fgetimageLocation(r1) == null) goto L6;
        return true;
    L6:
        if (Data.m6265$$Nest$fgetthumbImageLocation(r1) == null) goto L12;
        return true;
    L12:
        return false;
    }

    public static AvatarPreviewer getInstance() {
        if (INSTANCE != null) goto L6;
        INSTANCE = new AvatarPreviewer();
    L6:
        return INSTANCE;
    }

    public static boolean hasVisibleInstance() {
        AvatarPreviewer r0 = INSTANCE;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.visible == false) goto L10;
        return true;
    L10:
        return false;
    }

    private /* synthetic */ WindowInsetsCompat lambda$show$0(View r3, WindowInsetsCompat r4) {
        Insets r42 = AndroidUtilities.getDefaultWindowInsets(r4, false);
        Layout r0 = this.layout;
        if (r0 != r3) goto L8;
        if (Layout.m6276$$Nest$fgetcontainer(r0) == null) goto L8;
        Layout.m6276$$Nest$fgetcontainer(this.layout).setPadding(r42.left, r42.top, r42.right, r42.bottom);
    L8:
        return WindowInsetsCompat.CONSUMED;
    }

    public void close() {
        if (this.visible == false) goto L6;
        Layout.m6278$$Nest$msetShowing(this.layout, false);
        return;
    }

    public void onTouchEvent(MotionEvent r1) {
        Layout r0 = this.layout;
        if (r0 == null) goto L6;
        r0.onTouchEvent(r1);
        return;
    }

    public void show(ViewGroup r7, Theme.ResourcesProvider r8, Data r9, Callback r10) {
        Objects.requireNonNull(r7);
        Objects.requireNonNull(r9);
        Objects.requireNonNull(r10);
        Context r0 = r7.getContext();
        if (this.view == r7) goto L5;
        close();
        this.view = r7;
        this.windowManager = (WindowManager) ContextCompat.getSystemService(r0, WindowManager.class);
        AnonymousClass1 r1 = new AnonymousClass1(this, r0, r8, r10);
        this.layout = r1;
        ViewCompat.setOnApplyWindowInsetsListener(r1, new AvatarPreviewer$$ExternalSyntheticLambda0(this));
    L5:
        this.layout.setData(r9);
        if (this.visible == false) goto L8;
        return;
    L8:
        if (this.layout.getParent() == null) goto L10;
        this.windowManager.removeView(this.layout);
    L10:
        WindowManager.LayoutParams r02 = new WindowManager.LayoutParams(-1, -1, 1000, 0, -3);
        r02.softInputMode = 16;
        r02.flags |= -1945959040;
        AndroidUtilities.applyEdgeToEdgeLayoutParams(r02);
        AndroidUtilities.setPreferredMaxRefreshRate(this.windowManager, this.layout, r02);
        this.windowManager.addView(this.layout, r02);
        r7.requestDisallowInterceptTouchEvent(true);
        this.visible = true;
    }
}
