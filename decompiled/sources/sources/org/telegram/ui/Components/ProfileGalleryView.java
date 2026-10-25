package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.Components.CircularViewPager;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.PinchToZoomHelper;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes7.dex */
public class ProfileGalleryView extends CircularViewPager implements NotificationCenter.NotificationCenterDelegate {
    private ViewPagerAdapter adapter;
    private ProfileGalleryBlurView blurView;
    private final Callback callback;
    private TLRPC.ChatFull chatInfo;
    private boolean createThumbFromParent;
    ImageLocation curreantUploadingThumbLocation;
    private int currentAccount;
    ImageLocation currentUploadingImageLocation;
    private int customAvatarIndex;
    private long dialogId;
    private MessagesController.DialogPhotos dialogPhotos;
    private final PointF downPoint;
    private int fallbackPhotoIndex;
    private boolean forceResetPosition;
    private boolean hasActiveVideo;
    private int imagesLayerNum;
    private ArrayList<ImageLocation> imagesLocations;
    private ArrayList<Integer> imagesLocationsSizes;
    private ArrayList<Float> imagesUploadProgress;
    private boolean invalidateWithParent;
    private boolean isDownReleased;
    private final boolean isProfileFragment;
    private boolean isScrollingListView;
    private boolean isSwipingViewPager;
    private final ActionBar parentActionBar;
    private final int parentClassGuid;
    private final RecyclerListView parentListView;
    Path path;
    private ArrayList<TLRPC.Photo> photos;
    PinchToZoomHelper pinchToZoomHelper;
    private ImageLocation prevImageLocation;
    int prevPage;
    private ImageLocation prevThumbLocation;
    private VectorAvatarThumbDrawable prevVectorAvatarThumbDrawable;
    private final SparseArray<RadialProgress2> radialProgresses;
    float[] radii;
    RectF rect;
    private int roundBottomRadius;
    private int roundTopRadius;
    public boolean scrolledByUser;
    int selectedPage;
    private int settingMainPhoto;
    private ArrayList<String> thumbsFileNames;
    private ArrayList<ImageLocation> thumbsLocations;
    private final int touchSlop;
    private ImageLocation uploadingImageLocation;
    private ArrayList<VectorAvatarThumbDrawable> vectorAvatars;
    private ArrayList<String> videoFileNames;
    private ArrayList<ImageLocation> videoLocations;

    /* renamed from: org.telegram.ui.Components.ProfileGalleryView$1, reason: invalid class name */
    public class AnonymousClass1 implements ViewPager.OnPageChangeListener {
        final /* synthetic */ ProfileGalleryView this$0;

        public AnonymousClass1(ProfileGalleryView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int r1) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int r8, float r9, int r10) {
            ProfileGalleryView.m12238$$Nest$mcheckCustomAvatar(this.this$0, r8, r9);
            if (r10 != 0) goto L34;
            int r82 = ProfileGalleryView.m12217$$Nest$fgetadapter(this.this$0).getRealPosition(r8);
            if (ProfileGalleryView.m12223$$Nest$fgethasActiveVideo(this.this$0) == false) goto L7;
            r82 = r82 - 1;
        L7:
            this.this$0.getCurrentItemView();
            int r92 = this.this$0.getChildCount();
            int r0 = 0;
        L8:
            if (r0 >= r92) goto L43;
            View r1 = this.this$0.getChildAt(r0);
            if ((r1 instanceof BackupImageView) == false) goto L33;
            int r2 = ProfileGalleryView.m12217$$Nest$fgetadapter(this.this$0).getRealPosition(ViewPagerAdapter.m12245$$Nest$fgetimageViews(ProfileGalleryView.m12217$$Nest$fgetadapter(this.this$0)).indexOf(r1));
            if (ProfileGalleryView.m12223$$Nest$fgethasActiveVideo(this.this$0) == false) goto L15;
            r2 = r2 - 1;
        L15:
            ImageReceiver r12 = ((BackupImageView) r1).getImageReceiver();
            boolean r3 = r12.getAllowStartAnimation();
            if (r2 < 0) goto L33;
            if (r2 >= ProfileGalleryView.m12237$$Nest$fgetvideoLocations(this.this$0).size()) goto L33;
            if (r2 != r82) goto L26;
            if (r3 == true) goto L23;
            r12.setAllowStartAnimation(true);
            r12.startAnimation();
        L23:
            ImageLocation r13 = (ImageLocation) ProfileGalleryView.m12237$$Nest$fgetvideoLocations(this.this$0).get(r2);
            if (r13 == null) goto L33;
            FileLoader.getInstance(ProfileGalleryView.m12220$$Nest$fgetcurrentAccount(this.this$0)).setForceStreamLoadingFile(r13.location, "mp4");
            goto L33
        L26:
            if (r3 == false) goto L33;
            AnimatedFileDrawable r32 = r12.getAnimation();
            if (r32 == null) goto L32;
            ImageLocation r22 = (ImageLocation) ProfileGalleryView.m12237$$Nest$fgetvideoLocations(this.this$0).get(r2);
            if (r22 == null) goto L32;
            r32.seekTo(r22.videoSeekTo, false, true);
        L32:
            r12.setAllowStartAnimation(false);
            r12.stopAnimation();
        L33:
            r0 = r0 + 1;
            goto L8
        L43:
            return;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int r5) {
            ProfileGalleryView r0 = this.this$0;
            int r1 = r0.selectedPage;
            int r2 = 0;
            if (r5 < r1) goto L5;
            boolean r3 = true;
        L6:
            if (r5 == r1) goto L9;
            r0.prevPage = r1;
            r0.selectedPage = r5;
        L9:
            if (ProfileGalleryView.m12222$$Nest$fgetdialogPhotos(r0) == null) goto L15;
            MessagesController.DialogPhotos r02 = ProfileGalleryView.m12222$$Nest$fgetdialogPhotos(this.this$0);
            if (ProfileGalleryView.m12217$$Nest$fgetadapter(this.this$0) == null) goto L13;
            r2 = ProfileGalleryView.m12217$$Nest$fgetadapter(this.this$0).getExtraCount();
        L13:
            r02.loadAfter(r5 - r2, r3);
            return;
        L15:
            return;
        L5:
            r3 = false;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.ProfileGalleryView$2, reason: invalid class name */
    public class AnonymousClass2 implements ViewPager.OnPageChangeListener {
        final /* synthetic */ ProfileGalleryView this$0;

        public AnonymousClass2(ProfileGalleryView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int r1) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int r8, float r9, int r10) {
            ProfileGalleryView.m12238$$Nest$mcheckCustomAvatar(this.this$0, r8, r9);
            if (r10 != 0) goto L28;
            int r82 = ProfileGalleryView.m12217$$Nest$fgetadapter(this.this$0).getRealPosition(r8);
            this.this$0.getCurrentItemView();
            int r92 = this.this$0.getChildCount();
            int r0 = 0;
        L5:
            if (r0 >= r92) goto L37;
            View r1 = this.this$0.getChildAt(r0);
            if ((r1 instanceof BackupImageView) == false) goto L27;
            int r2 = ProfileGalleryView.m12217$$Nest$fgetadapter(this.this$0).getRealPosition(ViewPagerAdapter.m12245$$Nest$fgetimageViews(ProfileGalleryView.m12217$$Nest$fgetadapter(this.this$0)).indexOf(r1));
            ImageReceiver r12 = ((BackupImageView) r1).getImageReceiver();
            boolean r3 = r12.getAllowStartAnimation();
            if (r2 < 0) goto L27;
            if (r2 >= ProfileGalleryView.m12237$$Nest$fgetvideoLocations(this.this$0).size()) goto L27;
            if (r2 != r82) goto L20;
            if (r3 == true) goto L17;
            r12.setAllowStartAnimation(true);
            r12.startAnimation();
        L17:
            ImageLocation r13 = (ImageLocation) ProfileGalleryView.m12237$$Nest$fgetvideoLocations(this.this$0).get(r2);
            if (r13 == null) goto L27;
            FileLoader.getInstance(ProfileGalleryView.m12220$$Nest$fgetcurrentAccount(this.this$0)).setForceStreamLoadingFile(r13.location, "mp4");
            goto L27
        L20:
            if (r3 == false) goto L27;
            AnimatedFileDrawable r32 = r12.getAnimation();
            if (r32 == null) goto L26;
            ImageLocation r22 = (ImageLocation) ProfileGalleryView.m12237$$Nest$fgetvideoLocations(this.this$0).get(r2);
            if (r22 == null) goto L26;
            r32.seekTo(r22.videoSeekTo, false, true);
        L26:
            r12.setAllowStartAnimation(false);
            r12.stopAnimation();
        L27:
            r0 = r0 + 1;
            goto L5
        L37:
            return;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int r5) {
            ProfileGalleryView r0 = this.this$0;
            int r1 = r0.selectedPage;
            int r2 = 0;
            if (r5 < r1) goto L5;
            boolean r3 = true;
        L6:
            if (r5 == r1) goto L9;
            r0.prevPage = r1;
            r0.selectedPage = r5;
        L9:
            if (ProfileGalleryView.m12222$$Nest$fgetdialogPhotos(r0) == null) goto L15;
            MessagesController.DialogPhotos r02 = ProfileGalleryView.m12222$$Nest$fgetdialogPhotos(this.this$0);
            if (ProfileGalleryView.m12217$$Nest$fgetadapter(this.this$0) == null) goto L13;
            r2 = ProfileGalleryView.m12217$$Nest$fgetadapter(this.this$0).getExtraCount();
        L13:
            r02.loadAfter(r5 - r2, r3);
            return;
        L15:
            return;
        L5:
            r3 = false;
            goto L6
        }
    }

    public class AvatarImageView extends BackupImageView implements SizeNotifierFrameLayout.IViewWithInvalidateCallback {
        private long firstDrawTime;
        Runnable invalidateCallback;
        public boolean isVideo;
        private final Paint placeholderPaint;
        private final int position;
        private RadialProgress2 radialProgress;
        private ValueAnimator radialProgressHideAnimator;
        private float radialProgressHideAnimatorStartValue;
        private final int radialProgressSize;
        final /* synthetic */ ProfileGalleryView this$0;

        /* renamed from: org.telegram.ui.Components.ProfileGalleryView$AvatarImageView$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AvatarImageView this$1;
            final /* synthetic */ int val$finalRealPosition;

            public AnonymousClass1(AvatarImageView r1, int r2) {
                this.this$1 = r1;
                this.val$finalRealPosition = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                AvatarImageView.m12240$$Nest$fputradialProgress(this.this$1, null);
                ProfileGalleryView.m12231$$Nest$fgetradialProgresses(this.this$1.this$0).delete(this.val$finalRealPosition);
            }
        }

        public static /* synthetic */ void $r8$lambda$Snks8vqgZ30tHFuPPu96KsTUPhw(AvatarImageView r0, ValueAnimator r1) {
            r0.lambda$onDraw$0(r1);
        }

        /* renamed from: -$$Nest$fgetradialProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RadialProgress2 m12239$$Nest$fgetradialProgress(AvatarImageView r0) {
            return r0.radialProgress;
        }

        /* renamed from: -$$Nest$fputradialProgress, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12240$$Nest$fputradialProgress(AvatarImageView r0, RadialProgress2 r1) {
            r0.radialProgress = r1;
        }

        public AvatarImageView(ProfileGalleryView r3, Context r4, int r5, Paint r6) {
            this.this$0 = r3;
            super(r4);
            this.radialProgressSize = AndroidUtilities.dp(64.0f);
            this.firstDrawTime = -1;
            this.position = r5;
            this.placeholderPaint = r6;
            setLayerNum(ProfileGalleryView.m12224$$Nest$fgetimagesLayerNum(r3));
        }

        private /* synthetic */ void lambda$onDraw$0(ValueAnimator r3) {
            this.radialProgress.setOverrideAlpha(AndroidUtilities.lerp(this.radialProgressHideAnimatorStartValue, 0.0f, r3.getAnimatedFraction()));
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            if (ProfileGalleryView.m12228$$Nest$fgetinvalidateWithParent(this.this$0) == false) goto L5;
            this.this$0.invalidate();
        L5:
            Runnable r1 = this.invalidateCallback;
            if (r1 == null) goto L9;
            r1.run();
            return;
        }

        @Override // android.view.View
        public void invalidate(int r1, int r2, int r3, int r4) {
            super.invalidate(r1, r2, r3, r4);
            Runnable r0 = this.invalidateCallback;
            if (r0 == null) goto L6;
            r0.run();
            return;
        }

        @Override // android.view.View
        public void invalidate(Rect r1) {
            super.invalidate(r1);
            Runnable r0 = this.invalidateCallback;
            if (r0 == null) goto L6;
            r0.run();
            return;
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout.IViewWithInvalidateCallback
        public void listenInvalidate(Runnable r1) {
            this.invalidateCallback = r1;
        }

        @Override // org.telegram.ui.Components.BackupImageView, android.view.View
        public void onDraw(Canvas r10) {
            PinchToZoomHelper r0 = this.this$0.pinchToZoomHelper;
            if (r0 == null) goto L8;
            if (r0.isInOverlayMode() == false) goto L8;
            return;
        L8:
            if (this.radialProgress == null) goto L62;
            int r02 = this.this$0.getRealPosition(this.position);
            if (ProfileGalleryView.m12223$$Nest$fgethasActiveVideo(this.this$0) == false) goto L12;
            r02 = r02 - 1;
        L12:
            Drawable r2 = getImageReceiver().getDrawable();
            long r5 = 0;
            if (r02 < ProfileGalleryView.m12227$$Nest$fgetimagesUploadProgress(this.this$0).size()) goto L15;
        L19:
            if (r2 != null) goto L21;
        L33:
            if (this.firstDrawTime >= 0) goto L35;
            this.firstDrawTime = System.currentTimeMillis();
        L45:
            if (ProfileGalleryView.m12228$$Nest$fgetinvalidateWithParent(this.this$0) == false) goto L47;
            invalidate();
        L48:
            invalidate();
        L50:
            if (ProfileGalleryView.m12233$$Nest$fgetroundTopRadius(this.this$0) == 0) goto L52;
        L54:
            Canvas r22 = r10;
            int r102 = ProfileGalleryView.m12233$$Nest$fgetroundTopRadius(this.this$0);
            int r03 = ProfileGalleryView.m12232$$Nest$fgetroundBottomRadius(this.this$0);
            ProfileGalleryView r3 = this.this$0;
            if (r102 != r03) goto L57;
            r3.rect.set(0.0f, 0.0f, getWidth(), getHeight());
            r22.drawRoundRect(this.this$0.rect, ProfileGalleryView.m12233$$Nest$fgetroundTopRadius(r10), ProfileGalleryView.m12233$$Nest$fgetroundTopRadius(this.this$0), this.placeholderPaint);
        L63:
            super.onDraw(r22);
            RadialProgress2 r103 = this.radialProgress;
            if (r103 != null) goto L66;
            return;
        L66:
            if (r103.getOverrideAlpha() <= 0.0f) goto L71;
            this.radialProgress.draw(r22);
            return;
        L71:
            return;
        L57:
            r3.path.reset();
            this.this$0.rect.set(0.0f, 0.0f, getWidth(), getHeight());
            int r104 = 0;
        L58:
            ProfileGalleryView r04 = this.this$0;
            if (r104 >= 4) goto L61;
            r04.radii[r104] = ProfileGalleryView.m12233$$Nest$fgetroundTopRadius(r04);
            this.this$0.radii[r104 + 4] = ProfileGalleryView.m12232$$Nest$fgetroundBottomRadius(r0);
            r104 = r104 + 1;
            goto L58
        L61:
            r04.path.addRoundRect(r04.rect, r04.radii, Path.Direction.CW);
            r22.drawPath(this.this$0.path, this.placeholderPaint);
            goto L63
        L52:
            if (ProfileGalleryView.m12232$$Nest$fgetroundBottomRadius(this.this$0) != 0) goto L54;
            r22 = r10;
            r22.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.placeholderPaint);
            goto L63
        L47:
            postInvalidateOnAnimation();
            goto L48
        L35:
            long r23 = System.currentTimeMillis() - this.firstDrawTime;
            if (this.isVideo == false) goto L38;
            long r7 = 250;
        L40:
            if (r23 > (250 + r7)) goto L45;
            if (r23 <= r7) goto L45;
            this.radialProgress.setOverrideAlpha(CubicBezierInterpolator.DEFAULT.getInterpolation((r23 - r7) / 250.0f));
            goto L45
        L38:
            r7 = 750;
            goto L40
        L21:
            if (this.isVideo == true) goto L23;
        L27:
            if (this.radialProgressHideAnimator != null) goto L50;
            if (this.radialProgress.getProgress() >= 1.0f) goto L31;
            this.radialProgress.setProgress(1.0f, true);
            r5 = 100;
        L31:
            this.radialProgressHideAnimatorStartValue = this.radialProgress.getOverrideAlpha();
            ValueAnimator r24 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.radialProgressHideAnimator = r24;
            r24.setStartDelay(r5);
            this.radialProgressHideAnimator.setDuration((long) (this.radialProgressHideAnimatorStartValue * 250.0f));
            this.radialProgressHideAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
            this.radialProgressHideAnimator.addUpdateListener(new ProfileGalleryView$AvatarImageView$$ExternalSyntheticLambda0(this));
            this.radialProgressHideAnimator.addListener(new AnonymousClass1(this, r02));
            this.radialProgressHideAnimator.start();
            goto L50
        L23:
            if ((r2 instanceof AnimatedFileDrawable) == false) goto L33;
            if (((AnimatedFileDrawable) r2).getDurationMs() <= 0) goto L33;
        L15:
            if (ProfileGalleryView.m12227$$Nest$fgetimagesUploadProgress(this.this$0).get(r02) == null) goto L19;
            if (((Float) ProfileGalleryView.m12227$$Nest$fgetimagesUploadProgress(this.this$0).get(r02)).floatValue() < 1.0f) goto L33;
        L62:
            r22 = r10;
            goto L63
        }

        @Override // android.view.View
        public void onSizeChanged(int r3, int r4, int r5, int r6) {
            super.onSizeChanged(r3, r4, r5, r6);
            if (this.radialProgress != null) goto L5;
            return;
        L5:
            if (ProfileGalleryView.m12230$$Nest$fgetparentActionBar(this.this$0).getOccupyStatusBar() == false) goto L7;
            int r52 = AndroidUtilities.statusBarHeight;
        L8:
            int r53 = r52 + ActionBar.getCurrentActionBarHeight();
            int r62 = AndroidUtilities.dp2(80.0f);
            RadialProgress2 r0 = this.radialProgress;
            int r2 = this.radialProgressSize;
            int r42 = (r4 - r53) - r62;
            r0.setProgressRect((r3 - r2) / 2, ((r42 - r2) / 2) + r53, (r3 + r2) / 2, r53 + ((r42 + r2) / 2));
            return;
        L7:
            r52 = 0;
            goto L8
        }
    }

    public interface Callback {
        void onDown(boolean r1);

        void onPhotosLoaded();

        void onRelease();

        void onVideoSet();
    }

    public static class Item {
        private AvatarImageView imageView;
        boolean isActiveVideo;
        private View textureViewStubView;

        /* renamed from: -$$Nest$fgetimageView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AvatarImageView m12241$$Nest$fgetimageView(Item r0) {
            return r0.imageView;
        }

        /* renamed from: -$$Nest$fgettextureViewStubView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m12242$$Nest$fgettextureViewStubView(Item r0) {
            return r0.textureViewStubView;
        }

        /* renamed from: -$$Nest$fputimageView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12243$$Nest$fputimageView(Item r0, AvatarImageView r1) {
            r0.imageView = r1;
        }

        /* renamed from: -$$Nest$fputtextureViewStubView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12244$$Nest$fputtextureViewStubView(Item r0, View r1) {
            r0.textureViewStubView = r1;
        }

        private Item() {
        }

        public /* synthetic */ Item(ProfileGalleryViewIA r1) {
            this();
        }
    }

    public class TextureStubView extends View {
        final /* synthetic */ ProfileGalleryView this$0;

        public TextureStubView(ProfileGalleryView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }
    }

    public class ViewPagerAdapter extends CircularViewPager.Adapter {
        private final Context context;
        private final ArrayList<BackupImageView> imageViews;
        private final ArrayList<Item> objects;
        private final ActionBar parentActionBar;
        private BackupImageView parentAvatarImageView;
        private final Paint placeholderPaint;
        final /* synthetic */ ProfileGalleryView this$0;

        /* renamed from: org.telegram.ui.Components.ProfileGalleryView$ViewPagerAdapter$1, reason: invalid class name */
        public class AnonymousClass1 implements ImageReceiver.ImageReceiverDelegate {
            final /* synthetic */ ViewPagerAdapter this$1;

            public AnonymousClass1(ViewPagerAdapter r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.messenger.ImageReceiver.ImageReceiverDelegate
            public void didSetImage(ImageReceiver r1, boolean r2, boolean r3, boolean r4) {
            }

            @Override // org.telegram.messenger.ImageReceiver.ImageReceiverDelegate
            public void onAnimationReady(ImageReceiver r1) {
                if (ProfileGalleryView.m12218$$Nest$fgetcallback(this.this$1.this$0) == null) goto L6;
                ProfileGalleryView.m12218$$Nest$fgetcallback(this.this$1.this$0).onVideoSet();
                return;
            }
        }

        /* renamed from: -$$Nest$fgetimageViews, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m12245$$Nest$fgetimageViews(ViewPagerAdapter r0) {
            return r0.imageViews;
        }

        /* renamed from: -$$Nest$fgetobjects, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m12246$$Nest$fgetobjects(ViewPagerAdapter r0) {
            return r0.objects;
        }

        /* renamed from: -$$Nest$fputparentAvatarImageView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12247$$Nest$fputparentAvatarImageView(ViewPagerAdapter r0, BackupImageView r1) {
            r0.parentAvatarImageView = r1;
        }

        public ViewPagerAdapter(ProfileGalleryView r1, Context r2, ProfileActivity.AvatarImageView r3, ActionBar r4) {
            this.this$0 = r1;
            this.objects = new ArrayList();
            this.imageViews = new ArrayList();
            this.context = r2;
            this.parentAvatarImageView = r3;
            this.parentActionBar = r4;
            Paint r12 = new Paint(1);
            this.placeholderPaint = r12;
            r12.setColor(-16777216);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup r1, int r2, Object r3) {
            Item r32 = (Item) r3;
            if (Item.m12242$$Nest$fgettextureViewStubView(r32) == null) goto L6;
            r1.removeView(Item.m12242$$Nest$fgettextureViewStubView(r32));
        L6:
            if (r32.isActiveVideo == false) goto L8;
            return;
        L8:
            AvatarImageView r0 = Item.m12241$$Nest$fgetimageView(r32);
            if (r0.getImageReceiver().hasStaticThumb() == false) goto L13;
            Drawable r22 = r0.getImageReceiver().getDrawable();
            if ((r22 instanceof AnimatedFileDrawable) == false) goto L13;
            ((AnimatedFileDrawable) r22).removeSecondParentView(r0);
        L13:
            r0.setRoundRadius(0);
            r1.removeView(r0);
            r0.getImageReceiver().cancelLoadImage();
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return this.objects.size();
        }

        @Override // org.telegram.ui.Components.CircularViewPager.Adapter
        public int getExtraCount() {
            int r0 = ProfileGalleryView.m12225$$Nest$fgetimagesLocations(this.this$0).size();
            if (ProfileGalleryView.m12223$$Nest$fgethasActiveVideo(this.this$0) == false) goto L6;
            r0 = r0 + 1;
        L6:
            if (r0 >= 2) goto L8;
            return 0;
        L8:
            return this.this$0.getOffscreenPageLimit();
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getItemPosition(Object r1) {
            int r0 = this.objects.indexOf((Item) r1);
            if (r0 != (-1)) goto L6;
            return -2;
        L6:
            return r0;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public CharSequence getPageTitle(int r2) {
            StringBuilder r0 = new StringBuilder();
            r0.append(getRealPosition(r2) + 1);
            r0.append("/");
            if (ProfileGalleryView.m12222$$Nest$fgetdialogPhotos(this.this$0) != null) goto L5;
            int r1 = 0;
        L6:
            r0.append(r1);
            return r0.toString();
        L5:
            r1 = ProfileGalleryView.m12222$$Nest$fgetdialogPhotos(this.this$0).getCount();
            goto L6
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public /* bridge */ /* synthetic */ Object instantiateItem(ViewGroup r1, int r2) {
            return instantiateItem(r1, r2);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Item instantiateItem(ViewGroup r27, int r28) {
            Item r3 = this.objects.get(r28);
            int r4 = getRealPosition(r28);
            boolean r6 = true;
            if (ProfileGalleryView.m12223$$Nest$fgethasActiveVideo(this.this$0) == false) goto L12;
            if (r4 != 0) goto L12;
            r3.isActiveVideo = true;
            if (Item.m12242$$Nest$fgettextureViewStubView(r3) != null) goto L9;
            Item.m12244$$Nest$fputtextureViewStubView(r3, new TextureStubView(this.this$0, this.context));
        L9:
            if (Item.m12242$$Nest$fgettextureViewStubView(r3).getParent() != null) goto L11;
            r27.addView(Item.m12242$$Nest$fgettextureViewStubView(r3));
        L11:
            return r3;
        L12:
            r3.isActiveVideo = false;
            if (Item.m12242$$Nest$fgettextureViewStubView(r3) == null) goto L18;
            if (Item.m12242$$Nest$fgettextureViewStubView(r3).getParent() == null) goto L18;
            r27.removeView(Item.m12242$$Nest$fgettextureViewStubView(r3));
        L18:
            if (Item.m12241$$Nest$fgetimageView(r3) != null) goto L21;
            Item.m12243$$Nest$fputimageView(r3, new AvatarImageView(this.this$0, this.context, r28, this.placeholderPaint));
            this.imageViews.set(r28, Item.m12241$$Nest$fgetimageView(r3));
        L21:
            if (Item.m12241$$Nest$fgetimageView(r3).getParent() != null) goto L23;
            r27.addView(Item.m12241$$Nest$fgetimageView(r3));
        L23:
            Item.m12241$$Nest$fgetimageView(r3).getImageReceiver().setAllowDecodeSingleFrame(true);
            if (ProfileGalleryView.m12223$$Nest$fgethasActiveVideo(this.this$0) == false) goto L26;
            int r1 = r4 - 1;
        L28:
            if (r1 != 0) goto L77;
            BackupImageView r10 = this.parentAvatarImageView;
            if (r10 != null) goto L32;
            Drawable r102 = null;
        L34:
            if ((r102 instanceof AnimatedFileDrawable) == false) goto L38;
            AnimatedFileDrawable r11 = (AnimatedFileDrawable) r102;
            if (r11.hasBitmap() == false) goto L38;
            Item.m12241$$Nest$fgetimageView(r3).setImageDrawable(r102);
            r11.addSecondParentView(Item.m12241$$Nest$fgetimageView(r3));
            r11.setInvalidateParentViewWithSecond(true);
        L95:
            boolean r103 = false;
        L96:
            if (r1 >= 0) goto L98;
        L102:
            r6 = r103;
        L103:
            if (r6 == false) goto L111;
            AvatarImageView.m12240$$Nest$fputradialProgress(Item.m12241$$Nest$fgetimageView(r3), (RadialProgress2) ProfileGalleryView.m12231$$Nest$fgetradialProgresses(this.this$0).get(r1));
            if (AvatarImageView.m12239$$Nest$fgetradialProgress(Item.m12241$$Nest$fgetimageView(r3)) != null) goto L107;
            AvatarImageView.m12240$$Nest$fputradialProgress(Item.m12241$$Nest$fgetimageView(r3), new RadialProgress2(Item.m12241$$Nest$fgetimageView(r3)));
            AvatarImageView.m12239$$Nest$fgetradialProgress(Item.m12241$$Nest$fgetimageView(r3)).setOverrideAlpha(0.0f);
            AvatarImageView.m12239$$Nest$fgetradialProgress(Item.m12241$$Nest$fgetimageView(r3)).setIcon(10, false, false);
            AvatarImageView.m12239$$Nest$fgetradialProgress(Item.m12241$$Nest$fgetimageView(r3)).setColors(1107296256, 1107296256, -1, -1);
            ProfileGalleryView.m12231$$Nest$fgetradialProgresses(this.this$0).append(r1, AvatarImageView.m12239$$Nest$fgetradialProgress(Item.m12241$$Nest$fgetimageView(r3)));
        L107:
            boolean r12 = ProfileGalleryView.m12228$$Nest$fgetinvalidateWithParent(this.this$0);
            ProfileGalleryView r2 = this.this$0;
            if (r12 == false) goto L110;
            r2.invalidate();
            goto L111
        L110:
            r2.postInvalidateOnAnimation();
        L111:
            Item.m12241$$Nest$fgetimageView(r3).getImageReceiver().setDelegate(new AnonymousClass1(this));
            Item.m12241$$Nest$fgetimageView(r3).getImageReceiver().setCrossfadeAlpha((byte) 2);
            Item.m12241$$Nest$fgetimageView(r3).setRoundRadius(ProfileGalleryView.m12233$$Nest$fgetroundTopRadius(this.this$0), ProfileGalleryView.m12233$$Nest$fgetroundTopRadius(this.this$0), ProfileGalleryView.m12232$$Nest$fgetroundBottomRadius(this.this$0), ProfileGalleryView.m12232$$Nest$fgetroundBottomRadius(this.this$0));
            Item.m12241$$Nest$fgetimageView(r3).setTag(Integer.valueOf(r4));
            return r3;
        L98:
            if (r1 >= ProfileGalleryView.m12227$$Nest$fgetimagesUploadProgress(this.this$0).size()) goto L102;
            if (ProfileGalleryView.m12227$$Nest$fgetimagesUploadProgress(this.this$0).get(r1) == null) goto L102;
        L38:
            if (r1 < 0) goto L95;
            if (r1 >= ProfileGalleryView.m12237$$Nest$fgetvideoLocations(this.this$0).size()) goto L95;
            ImageLocation r13 = (ImageLocation) ProfileGalleryView.m12237$$Nest$fgetvideoLocations(this.this$0).get(r1);
            AvatarImageView r104 = Item.m12241$$Nest$fgetimageView(r3);
            if (r13 == null) goto L44;
            boolean r112 = true;
        L45:
            r104.isVideo = r112;
            if (ProfileGalleryView.m12236$$Nest$fgetvectorAvatars(this.this$0).get(r1) != null) goto L48;
            r103 = true;
        L50:
            if (ProfileGalleryView.m12229$$Nest$fgetisProfileFragment(this.this$0) == false) goto L55;
            if (r13 == null) goto L55;
            if (r13.imageType != 2) goto L55;
            String r16 = "avatar";
        L56:
            ImageLocation r113 = (ImageLocation) ProfileGalleryView.m12234$$Nest$fgetthumbsLocations(this.this$0).get(r1);
            if (this.parentAvatarImageView != null) goto L59;
        L62:
            Bitmap r19 = null;
        L63:
            String r22 = "avatar_" + ProfileGalleryView.m12221$$Nest$fgetdialogId(this.this$0);
            if (r19 == null) goto L69;
            if (ProfileGalleryView.m12236$$Nest$fgetvectorAvatars(this.this$0).get(r1) != null) goto L69;
            Item.m12241$$Nest$fgetimageView(r3).setImageMedia((ImageLocation) ProfileGalleryView.m12237$$Nest$fgetvideoLocations(this.this$0).get(r1), r16, (ImageLocation) ProfileGalleryView.m12225$$Nest$fgetimagesLocations(this.this$0).get(r1), null, r19, ((Integer) ProfileGalleryView.m12226$$Nest$fgetimagesLocationsSizes(this.this$0).get(r1)).intValue(), 1, r22);
        L69:
            if (ProfileGalleryView.m12235$$Nest$fgetuploadingImageLocation(this.this$0) == null) goto L71;
            Item.m12241$$Nest$fgetimageView(r3).setImageMedia((VectorAvatarThumbDrawable) ProfileGalleryView.m12236$$Nest$fgetvectorAvatars(this.this$0).get(r1), (ImageLocation) ProfileGalleryView.m12237$$Nest$fgetvideoLocations(this.this$0).get(r1), r16, (ImageLocation) ProfileGalleryView.m12225$$Nest$fgetimagesLocations(this.this$0).get(r1), null, ProfileGalleryView.m12235$$Nest$fgetuploadingImageLocation(this.this$0), null, null, ((Integer) ProfileGalleryView.m12226$$Nest$fgetimagesLocationsSizes(this.this$0).get(r1)).intValue(), 1, r22);
            goto L96
        L71:
            if (r113 != null) goto L73;
        L75:
            String r18 = null;
        L76:
            Item.m12241$$Nest$fgetimageView(r3).setImageMedia((VectorAvatarThumbDrawable) ProfileGalleryView.m12236$$Nest$fgetvectorAvatars(this.this$0).get(r1), r13, null, (ImageLocation) ProfileGalleryView.m12225$$Nest$fgetimagesLocations(this.this$0).get(r1), null, (ImageLocation) ProfileGalleryView.m12234$$Nest$fgetthumbsLocations(this.this$0).get(r1), r18, null, ((Integer) ProfileGalleryView.m12226$$Nest$fgetimagesLocationsSizes(this.this$0).get(r1)).intValue(), 1, r22);
            goto L96
        L73:
            if ((r113.photoSize instanceof TLRPC.TL_photoStrippedSize) == false) goto L75;
            r18 = "b";
            goto L76
        L59:
            if (ProfileGalleryView.m12219$$Nest$fgetcreateThumbFromParent(this.this$0) == false) goto L62;
            r19 = this.parentAvatarImageView.getImageReceiver().getBitmap();
        L55:
            r16 = null;
            goto L56
        L48:
            r103 = false;
            goto L50
        L44:
            r112 = false;
            goto L45
        L32:
            r102 = r10.getImageReceiver().getDrawable();
            goto L34
        L77:
            if (r1 < 0) goto L95;
            if (r1 >= ProfileGalleryView.m12237$$Nest$fgetvideoLocations(this.this$0).size()) goto L95;
            ImageLocation r132 = (ImageLocation) ProfileGalleryView.m12237$$Nest$fgetvideoLocations(this.this$0).get(r1);
            AvatarImageView r105 = Item.m12241$$Nest$fgetimageView(r3);
            if (r132 == null) goto L83;
            boolean r114 = true;
        L84:
            r105.isVideo = r114;
            if (ProfileGalleryView.m12236$$Nest$fgetvectorAvatars(this.this$0).get(r1) != null) goto L87;
            r103 = true;
        L88:
            ImageLocation r115 = (ImageLocation) ProfileGalleryView.m12234$$Nest$fgetthumbsLocations(this.this$0).get(r1);
            if (r115 != null) goto L91;
        L93:
            String r182 = null;
        L94:
            Item.m12241$$Nest$fgetimageView(r3).setImageMedia((VectorAvatarThumbDrawable) ProfileGalleryView.m12236$$Nest$fgetvectorAvatars(this.this$0).get(r1), r132, null, (ImageLocation) ProfileGalleryView.m12225$$Nest$fgetimagesLocations(this.this$0).get(r1), null, (ImageLocation) ProfileGalleryView.m12234$$Nest$fgetthumbsLocations(this.this$0).get(r1), r182, null, ((Integer) ProfileGalleryView.m12226$$Nest$fgetimagesLocationsSizes(this.this$0).get(r1)).intValue(), 1, "avatar_" + ProfileGalleryView.m12221$$Nest$fgetdialogId(this.this$0));
            goto L96
        L91:
            if ((r115.photoSize instanceof TLRPC.TL_photoStrippedSize) == false) goto L93;
            r182 = "b";
            goto L94
        L87:
            r103 = false;
            goto L88
        L83:
            r114 = false;
            goto L84
        L26:
            r1 = r4;
            goto L28
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View r3, Object r4) {
            Item r42 = (Item) r4;
            if (r42.isActiveVideo == false) goto L9;
            if (r3 != Item.m12242$$Nest$fgettextureViewStubView(r42)) goto L7;
            return true;
        L7:
            return false;
        L9:
            if (r3 != Item.m12241$$Nest$fgetimageView(r42)) goto L11;
            return true;
        L11:
            return false;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void notifyDataSetChanged() {
            int r0 = 0;
            int r1 = 0;
        L4:
            if (r1 >= this.imageViews.size()) goto L9;
            if (this.imageViews.get(r1) == null) goto L8;
            this.imageViews.get(r1).getImageReceiver().cancelLoadImage();
        L8:
            r1 = r1 + 1;
            goto L4
        L9:
            this.objects.clear();
            this.imageViews.clear();
            int r12 = ProfileGalleryView.m12225$$Nest$fgetimagesLocations(this.this$0).size();
            if (ProfileGalleryView.m12223$$Nest$fgethasActiveVideo(this.this$0) == false) goto L13;
            r12 = r12 + 1;
        L13:
            if (ProfileGalleryView.m12222$$Nest$fgetdialogPhotos(this.this$0) != null) goto L15;
            int r2 = 0;
        L16:
            int r13 = Math.max(r2, r12) + (getExtraCount() * 2);
        L17:
            if (r0 >= r13) goto L19;
            this.objects.add(new Item(null));
            this.imageViews.add(null);
            r0 = r0 + 1;
            goto L17
        L19:
            super.notifyDataSetChanged();
            return;
        L15:
            r2 = ProfileGalleryView.m12222$$Nest$fgetdialogPhotos(this.this$0).getCount();
            goto L16
        }
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewPagerAdapter m12217$$Nest$fgetadapter(ProfileGalleryView r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetcallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Callback m12218$$Nest$fgetcallback(ProfileGalleryView r0) {
        return r0.callback;
    }

    /* renamed from: -$$Nest$fgetcreateThumbFromParent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12219$$Nest$fgetcreateThumbFromParent(ProfileGalleryView r0) {
        return r0.createThumbFromParent;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12220$$Nest$fgetcurrentAccount(ProfileGalleryView r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetdialogId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m12221$$Nest$fgetdialogId(ProfileGalleryView r2) {
        return r2.dialogId;
    }

    /* renamed from: -$$Nest$fgetdialogPhotos, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessagesController.DialogPhotos m12222$$Nest$fgetdialogPhotos(ProfileGalleryView r0) {
        return r0.dialogPhotos;
    }

    /* renamed from: -$$Nest$fgethasActiveVideo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12223$$Nest$fgethasActiveVideo(ProfileGalleryView r0) {
        return r0.hasActiveVideo;
    }

    /* renamed from: -$$Nest$fgetimagesLayerNum, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12224$$Nest$fgetimagesLayerNum(ProfileGalleryView r0) {
        return r0.imagesLayerNum;
    }

    /* renamed from: -$$Nest$fgetimagesLocations, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m12225$$Nest$fgetimagesLocations(ProfileGalleryView r0) {
        return r0.imagesLocations;
    }

    /* renamed from: -$$Nest$fgetimagesLocationsSizes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m12226$$Nest$fgetimagesLocationsSizes(ProfileGalleryView r0) {
        return r0.imagesLocationsSizes;
    }

    /* renamed from: -$$Nest$fgetimagesUploadProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m12227$$Nest$fgetimagesUploadProgress(ProfileGalleryView r0) {
        return r0.imagesUploadProgress;
    }

    /* renamed from: -$$Nest$fgetinvalidateWithParent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12228$$Nest$fgetinvalidateWithParent(ProfileGalleryView r0) {
        return r0.invalidateWithParent;
    }

    /* renamed from: -$$Nest$fgetisProfileFragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12229$$Nest$fgetisProfileFragment(ProfileGalleryView r0) {
        return r0.isProfileFragment;
    }

    /* renamed from: -$$Nest$fgetparentActionBar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBar m12230$$Nest$fgetparentActionBar(ProfileGalleryView r0) {
        return r0.parentActionBar;
    }

    /* renamed from: -$$Nest$fgetradialProgresses, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SparseArray m12231$$Nest$fgetradialProgresses(ProfileGalleryView r0) {
        return r0.radialProgresses;
    }

    /* renamed from: -$$Nest$fgetroundBottomRadius, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12232$$Nest$fgetroundBottomRadius(ProfileGalleryView r0) {
        return r0.roundBottomRadius;
    }

    /* renamed from: -$$Nest$fgetroundTopRadius, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12233$$Nest$fgetroundTopRadius(ProfileGalleryView r0) {
        return r0.roundTopRadius;
    }

    /* renamed from: -$$Nest$fgetthumbsLocations, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m12234$$Nest$fgetthumbsLocations(ProfileGalleryView r0) {
        return r0.thumbsLocations;
    }

    /* renamed from: -$$Nest$fgetuploadingImageLocation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageLocation m12235$$Nest$fgetuploadingImageLocation(ProfileGalleryView r0) {
        return r0.uploadingImageLocation;
    }

    /* renamed from: -$$Nest$fgetvectorAvatars, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m12236$$Nest$fgetvectorAvatars(ProfileGalleryView r0) {
        return r0.vectorAvatars;
    }

    /* renamed from: -$$Nest$fgetvideoLocations, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m12237$$Nest$fgetvideoLocations(ProfileGalleryView r0) {
        return r0.videoLocations;
    }

    /* renamed from: -$$Nest$mcheckCustomAvatar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12238$$Nest$mcheckCustomAvatar(ProfileGalleryView r0, int r1, float r2) {
        r0.checkCustomAvatar(r1, r2);
    }

    public ProfileGalleryView(Context r5, long r6, ActionBar r8, RecyclerListView r9, ProfileActivity.AvatarImageView r10, int r11, Callback r12, ProfileGalleryBlurView r13) {
        super(r5);
        this.downPoint = new PointF();
        this.isScrollingListView = true;
        this.isSwipingViewPager = true;
        this.currentAccount = UserConfig.selectedAccount;
        this.path = new Path();
        this.rect = new RectF();
        this.radii = new float[8];
        this.videoFileNames = new ArrayList();
        this.thumbsFileNames = new ArrayList();
        this.photos = new ArrayList();
        this.videoLocations = new ArrayList();
        this.imagesLocations = new ArrayList();
        this.thumbsLocations = new ArrayList();
        this.vectorAvatars = new ArrayList();
        this.imagesLocationsSizes = new ArrayList();
        this.imagesUploadProgress = new ArrayList();
        this.radialProgresses = new SparseArray();
        this.createThumbFromParent = true;
        this.customAvatarIndex = -1;
        this.fallbackPhotoIndex = -1;
        this.blurView = r13;
        if (r13 != null) goto L5;
        int r3 = 0;
    L6:
        setPadding(0, 0, 0, r3);
        if (r13 == null) goto L9;
        r13.setView(this);
    L9:
        setVisibility(8);
        setOverScrollMode(2);
        setOffscreenPageLimit(2);
        this.isProfileFragment = true;
        this.dialogId = r6;
        this.parentListView = r9;
        this.parentClassGuid = r11;
        this.parentActionBar = r8;
        ViewPagerAdapter r92 = new ViewPagerAdapter(this, getContext(), r10, r8);
        this.adapter = r92;
        setAdapter(r92);
        this.touchSlop = ViewConfiguration.get(r5).getScaledTouchSlop();
        this.callback = r12;
        addOnPageChangeListener(new AnonymousClass2(this));
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.dialogPhotosLoaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileLoaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileLoadProgressChanged);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.reloadDialogPhotos);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.dialogPhotosUpdate);
        MessagesController.DialogPhotos r52 = MessagesController.getInstance(this.currentAccount).getDialogPhotos(r6);
        this.dialogPhotos = r52;
        r52.loadCache();
        return;
    L5:
        r3 = r13.actionSize;
        goto L6
    }

    public ProfileGalleryView(Context r3, ActionBar r4, RecyclerListView r5, Callback r6) {
        super(r3);
        this.downPoint = new PointF();
        this.isScrollingListView = true;
        this.isSwipingViewPager = true;
        this.currentAccount = UserConfig.selectedAccount;
        this.path = new Path();
        this.rect = new RectF();
        this.radii = new float[8];
        this.videoFileNames = new ArrayList();
        this.thumbsFileNames = new ArrayList();
        this.photos = new ArrayList();
        this.videoLocations = new ArrayList();
        this.imagesLocations = new ArrayList();
        this.thumbsLocations = new ArrayList();
        this.vectorAvatars = new ArrayList();
        this.imagesLocationsSizes = new ArrayList();
        this.imagesUploadProgress = new ArrayList();
        this.radialProgresses = new SparseArray();
        this.createThumbFromParent = true;
        this.customAvatarIndex = -1;
        this.fallbackPhotoIndex = -1;
        setOffscreenPageLimit(2);
        this.blurView = null;
        this.isProfileFragment = false;
        this.parentListView = r5;
        this.parentClassGuid = ConnectionsManager.generateClassGuid();
        this.parentActionBar = r4;
        this.touchSlop = ViewConfiguration.get(r3).getScaledTouchSlop();
        this.callback = r6;
        addOnPageChangeListener(new AnonymousClass1(this));
        ViewPagerAdapter r32 = new ViewPagerAdapter(this, getContext(), null, r4);
        this.adapter = r32;
        setAdapter(r32);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.dialogPhotosLoaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileLoaded);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.fileLoadProgressChanged);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.reloadDialogPhotos);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.dialogPhotosUpdate);
        this.dialogPhotos = null;
    }

    private void checkCustomAvatar(int r6, float r7) {
        int r0 = this.customAvatarIndex;
        float r1 = 0.0f;
        if (r0 < 0) goto L5;
    L6:
        if (r0 >= 0) goto L9;
        r0 = this.fallbackPhotoIndex;
    L9:
        int r62 = this.adapter.getRealPosition(r6);
        if (this.hasActiveVideo == false) goto L13;
        r62 = r62 - 1;
    L13:
        if (r62 != r0) goto L16;
        float r63 = 1.0f - r7;
    L27:
        if (r63 <= 1.0f) goto L29;
        r63 = 2.0f - r63;
    L29:
        r1 = Utilities.clamp(r63, 1.0f, 0.0f);
    L30:
        setCustomAvatarProgress(r1);
        return;
    L16:
        if (getRealCount() <= 0) goto L21;
        if (((r62 - 1) % getRealCount()) != r0) goto L21;
        r63 = (1.0f - r7) - 1.0f;
    L21:
        if (getRealCount() > 0) goto L23;
    L25:
        r63 = 0.0f;
        goto L27
    L23:
        if (((r62 + 1) % getRealCount()) != r0) goto L25;
        r63 = (1.0f - r7) + 1.0f;
        goto L27
    L5:
        if (this.fallbackPhotoIndex < 0) goto L30;
        goto L6
    }

    private int getCurrentAvatarPage() {
        int r0 = this.adapter.getCount();
        if (r0 > 0) goto L6;
        return -1;
    L6:
        int r1 = getCurrentAvatarPhotoIndex();
        if (r1 >= 0) goto L9;
        int r12 = getCurrentItem();
        if (r12 < 0) goto L15;
        if (r12 >= r0) goto L15;
        return r12;
    L15:
        return this.adapter.getExtraCount();
    L9:
        return this.adapter.getExtraCount() + r1;
    }

    private TLRPC.Photo getCurrentAvatarPhoto() {
        if (DialogObject.isChatDialog(this.dialogId) == false) goto L9;
        TLRPC.ChatFull r5 = this.chatInfo;
        if (r5 != null) goto L7;
        return null;
    L7:
        return r5.chat_photo;
    L9:
        MessagesController r0 = MessagesController.getInstance(this.currentAccount);
        TLRPC.User r2 = r0.getUser(Long.valueOf(this.dialogId));
        TLRPC.UserFull r52 = r0.getUserFull(this.dialogId);
        if (r2 == null) goto L22;
        if (r52 == null) goto L22;
        TLRPC.UserProfilePhoto r02 = r2.photo;
        if (r02 == null) goto L21;
        if (r02.personal == false) goto L21;
        TLRPC.Photo r03 = r52.personal_photo;
        if (r03 == null) goto L21;
        return r03;
    L21:
        return r52.profile_photo;
    L22:
        return null;
    }

    private int getCurrentAvatarPhotoIndex() {
        TLRPC.Photo r0 = getCurrentAvatarPhoto();
        if (r0 != null) goto L5;
        return -1;
    L5:
        int r2 = 0;
    L7:
        if (r2 >= this.photos.size()) goto L14;
        TLRPC.Photo r3 = this.photos.get(r2);
        if (r3 == null) goto L13;
        if (r3.id != r0.id) goto L13;
        return r2;
    L13:
        r2 = r2 + 1;
        goto L7
    L14:
        return -1;
    }

    private void loadNeighboringThumbs() {
        int r0 = this.thumbsLocations.size();
        if (r0 <= 1) goto L14;
        int r2 = 0;
    L5:
        int r3 = 2;
        if (r0 > 2) goto L9;
        r3 = 1;
    L9:
        if (r2 >= r3) goto L18;
        if (r2 != 0) goto L12;
        int r32 = 1;
    L13:
        FileLoader.getInstance(this.currentAccount).loadFile(this.thumbsLocations.get(r32), null, null, 0, 1);
        r2 = r2 + 1;
        goto L5
    L12:
        r32 = r0 - 1;
        goto L13
    L18:
        return;
    }

    private void reset() {
        this.videoFileNames.clear();
        this.thumbsFileNames.clear();
        this.photos.clear();
        this.videoLocations.clear();
        this.imagesLocations.clear();
        this.thumbsLocations.clear();
        this.imagesLocationsSizes.clear();
        this.imagesUploadProgress.clear();
        this.adapter.notifyDataSetChanged();
        setCurrentItem(0, false);
        this.selectedPage = 0;
        this.uploadingImageLocation = null;
        this.prevImageLocation = null;
    }

    public void addUploadingImage(ImageLocation r4, ImageLocation r5) {
        this.prevImageLocation = r4;
        this.thumbsFileNames.add(0, null);
        this.videoFileNames.add(0, null);
        this.imagesLocations.add(0, r4);
        this.thumbsLocations.add(0, r5);
        this.vectorAvatars.add(0, null);
        this.videoLocations.add(0, null);
        this.photos.add(0, null);
        this.imagesLocationsSizes.add(0, -1);
        this.imagesUploadProgress.add(0, Float.valueOf(0.0f));
        this.adapter.notifyDataSetChanged();
        resetCurrentItem();
        this.currentUploadingImageLocation = r4;
        this.curreantUploadingThumbLocation = r5;
    }

    public void commitMoveToBegin() {
        this.adapter.notifyDataSetChanged();
        resetCurrentItem();
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r18, int r19, Object... r20) {
        int r7 = 0;
        if (r18 != NotificationCenter.dialogPhotosUpdate) goto L145;
        MessagesController.DialogPhotos r1 = (MessagesController.DialogPhotos) r20[0];
        if (this.dialogPhotos != r1) goto L209;
        ArrayList r4 = new ArrayList(r1.photos);
        if (r4.isEmpty() == true) goto L9;
    L11:
        this.customAvatarIndex = -1;
        this.fallbackPhotoIndex = -1;
        TLRPC.User r12 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.dialogId));
        TLRPC.UserFull r2 = MessagesController.getInstance(this.currentAccount).getUserFull(this.dialogId);
        if (r2 == null) goto L16;
        TLRPC.Photo r8 = r2.personal_photo;
        if (r8 == null) goto L16;
        r4.add(0, r8);
        this.customAvatarIndex = 0;
    L16:
        if (r12 != null) goto L18;
    L22:
        this.thumbsFileNames.clear();
        this.videoFileNames.clear();
        this.imagesLocations.clear();
        this.videoLocations.clear();
        this.thumbsLocations.clear();
        this.vectorAvatars.clear();
        this.photos.clear();
        this.imagesLocationsSizes.clear();
        this.imagesUploadProgress.clear();
        TLRPC.Photo r9 = null;
        if (DialogObject.isChatDialog(this.dialogId) == false) goto L36;
        TLRPC.Chat r22 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-this.dialogId));
        ImageLocation r10 = ImageLocation.getForUserOrChat(this.currentAccount, r22, 0);
        if (r10 == null) goto L37;
        this.imagesLocations.add(r10);
        this.thumbsLocations.add(ImageLocation.getForUserOrChat(this.currentAccount, r22, 1));
        this.vectorAvatars.add(null);
        this.thumbsFileNames.add(null);
        TLRPC.ChatFull r23 = this.chatInfo;
        if (r23 != null) goto L29;
    L34:
        this.photos.add(null);
        this.videoFileNames.add(null);
        this.videoLocations.add(null);
    L35:
        this.imagesLocationsSizes.add(-1);
        this.imagesUploadProgress.add(null);
        goto L37
    L29:
        if (FileLoader.isSamePhoto(r10.location, r23.chat_photo) == false) goto L34;
        this.photos.add(this.chatInfo.chat_photo);
        if (this.chatInfo.chat_photo.video_sizes.isEmpty() == true) goto L33;
        TLRPC.VideoSize r24 = FileLoader.getClosestVideoSizeWithSize(this.chatInfo.chat_photo.video_sizes, 1000);
        this.videoLocations.add(ImageLocation.getForPhoto(r24, this.chatInfo.chat_photo));
        this.videoFileNames.add(FileLoader.getAttachFileName(r24));
        goto L35
    L33:
        this.videoLocations.add(null);
        this.videoFileNames.add(null);
    L37:
        int r25 = 0;
    L39:
        if (r25 >= r4.size()) goto L122;
        TLRPC.Photo r11 = (TLRPC.Photo) r4.get(r25);
        if (r11 != null) goto L43;
    L120:
        this.photos.add(r9);
        this.imagesLocations.add(r9);
        this.thumbsLocations.add(r9);
        this.vectorAvatars.add(r9);
        this.thumbsFileNames.add(r9);
        this.videoLocations.add(r9);
        this.videoFileNames.add(r9);
        this.imagesLocationsSizes.add(-1);
        this.imagesUploadProgress.add(r9);
    L121:
        r25 = r25 + 1;
        goto L39
    L43:
        if ((r11 instanceof TLRPC.TL_photoEmpty) == true) goto L120;
        ArrayList<TLRPC.PhotoSize> r122 = r11.sizes;
        if (r122 == null) goto L120;
        TLRPC.PhotoSize r123 = FileLoader.getClosestPhotoSizeWithSize(r122, 50);
        int r13 = r11.sizes.size();
        int r14 = 0;
    L48:
        if (r14 >= r13) goto L53;
        TLRPC.PhotoSize r15 = r11.sizes.get(r14);
        if ((r15 instanceof TLRPC.TL_photoStrippedSize) == true) goto L51;
        r14 = r14 + 1;
        goto L48
    L51:
        r123 = r15;
    L53:
        if (r10 == null) goto L67;
        int r6 = r11.sizes.size();
        int r132 = 0;
    L55:
        if (r132 >= r6) goto L67;
        TLRPC.FileLocation r142 = r11.sizes.get(r132).location;
        if (r142 == null) goto L66;
        int r152 = r142.local_id;
        TLRPC.TL_fileLocationToBeDeprecated r5 = r10.location;
        if (r152 != r5.local_id) goto L66;
        if (r142.volume_id != r5.volume_id) goto L66;
        this.photos.set(0, r11);
        if (r11.video_sizes.isEmpty() == true) goto L65;
        this.videoLocations.set(0, ImageLocation.getForPhoto(FileLoader.getClosestVideoSizeWithSize(r11.video_sizes, 1000), r11));
    L65:
        r9 = null;
    L66:
        r132 = r132 + 1;
    L67:
        TLRPC.PhotoSize r52 = FileLoader.getClosestPhotoSizeWithSize(r11.sizes, 640);
        if (r52 == null) goto L65;
        int r62 = r11.dc_id;
        if (r62 == 0) goto L72;
        TLRPC.FileLocation r82 = r52.location;
        r82.dc_id = r62;
        r82.file_reference = r11.file_reference;
    L72:
        ImageLocation r63 = ImageLocation.getForPhoto(r52, r11);
        if (r63 == null) goto L65;
        ImageLocation r83 = this.prevImageLocation;
        if (r83 != null) goto L77;
    L101:
        this.imagesLocations.add(r63);
        ArrayList<String> r64 = this.thumbsFileNames;
        if ((r123 instanceof TLRPC.TL_photoStrippedSize) == false) goto L104;
        TLRPC.PhotoSize r84 = r52;
    L105:
        r64.add(FileLoader.getAttachFileName(r84));
        this.thumbsLocations.add(ImageLocation.getForPhoto(r123, r11));
        if (r11.video_sizes.isEmpty() == true) goto L118;
        TLRPC.VideoSize r65 = FileLoader.getClosestVideoSizeWithSize(r11.video_sizes, 1000);
        TLRPC.VideoSize r92 = FileLoader.getVectorMarkupVideoSize(r11);
        ArrayList<VectorAvatarThumbDrawable> r124 = this.vectorAvatars;
        if (r92 != null) goto L110;
        r9 = null;
        r124.add(null);
        this.videoLocations.add(ImageLocation.getForPhoto(r65, r11));
        this.videoFileNames.add(FileLoader.getAttachFileName(r65));
    L119:
        this.photos.add(r11);
        this.imagesLocationsSizes.add(Integer.valueOf(r52.size));
        this.imagesUploadProgress.add(r9);
        goto L121
    L110:
        if (r12 != null) goto L112;
    L115:
        boolean r133 = false;
    L116:
        r124.add(new VectorAvatarThumbDrawable(r92, r133, 2));
        r9 = null;
        this.videoLocations.add(null);
        this.videoFileNames.add(null);
        goto L119
    L112:
        if (r12.premium == false) goto L115;
        r133 = true;
        goto L116
    L118:
        r9 = null;
        this.videoLocations.add(null);
        this.videoFileNames.add(null);
        this.vectorAvatars.add(null);
        goto L119
    L104:
        r84 = r123;
        goto L105
    L77:
        if (r83.photoId != r63.photoId) goto L101;
        if (this.isProfileFragment == true) goto L101;
        if (this.dialogId == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L101;
        this.thumbsFileNames.add(null);
        this.imagesLocations.add(this.prevImageLocation);
        ImageLocation r53 = this.prevThumbLocation;
        if (r53 != null) goto L85;
        r53 = ImageLocation.getForPhoto(r123, r11);
    L85:
        this.thumbsLocations.add(r53);
        if (r11.video_sizes.isEmpty() == true) goto L99;
        TLRPC.VideoSize r54 = FileLoader.getClosestVideoSizeWithSize(r11.video_sizes, 1000);
        TLRPC.VideoSize r66 = FileLoader.getVectorMarkupVideoSize(r11);
        ArrayList<VectorAvatarThumbDrawable> r85 = this.vectorAvatars;
        if (r66 != null) goto L90;
        r85.add(null);
        this.videoLocations.add(ImageLocation.getForPhoto(r54, r11));
        this.videoFileNames.add(FileLoader.getAttachFileName(r54));
    L98:
        TLRPC.Photo r67 = null;
    L100:
        this.photos.add(r67);
        this.imagesLocationsSizes.add(-1);
        this.imagesUploadProgress.add(r67);
        r9 = r67;
        goto L121
    L90:
        if (r12 != null) goto L92;
    L95:
        boolean r93 = false;
    L96:
        r85.add(new VectorAvatarThumbDrawable(r66, r93, 2));
        this.videoLocations.add(null);
        this.videoFileNames.add(null);
        goto L98
    L92:
        if (r12.premium == false) goto L95;
        r93 = true;
        goto L96
    L99:
        this.vectorAvatars.add(this.prevVectorAvatarThumbDrawable);
        r67 = null;
        this.videoLocations.add(null);
        this.videoFileNames.add(null);
        goto L100
    L122:
        loadNeighboringThumbs();
        getAdapter().notifyDataSetChanged();
        boolean r16 = this.isProfileFragment;
        boolean r26 = this.scrolledByUser;
        if (r16 == false) goto L128;
        if (r26 == true) goto L126;
    L127:
        resetCurrentItem();
    L133:
        if (this.fallbackPhotoIndex < 0) goto L135;
    L137:
        this.forceResetPosition = false;
        Callback r17 = this.callback;
        if (r17 == null) goto L140;
        r17.onPhotosLoaded();
    L140:
        ImageLocation r110 = this.currentUploadingImageLocation;
        if (r110 == null) goto L210;
        addUploadingImage(r110, this.curreantUploadingThumbLocation);
        return;
    L210:
        return;
    L135:
        if (this.customAvatarIndex >= 0) goto L137;
        checkCustomAvatar(0, 0.0f);
        goto L137
    L126:
        if (this.forceResetPosition == false) goto L133;
    L128:
        if (r26 == true) goto L130;
    L131:
        resetCurrentItem();
        getAdapter().notifyDataSetChanged();
        checkCustomAvatar(getRealPosition(), 0.0f);
        goto L133
    L130:
        if (this.forceResetPosition == false) goto L133;
    L36:
        r10 = null;
        goto L37
    L18:
        if (r12.self == false) goto L22;
        if (UserObject.hasFallbackPhoto(r2) == false) goto L22;
        r4.add(r2.fallback_photo);
        this.fallbackPhotoIndex = r4.size() - 1;
        goto L22
    L9:
        if (r1.fromCache == false) goto L11;
        return;
    L209:
        return;
    L145:
        if (r18 != NotificationCenter.fileLoaded) goto L161;
        String r111 = (String) r20[0];
    L148:
        if (r7 >= this.thumbsFileNames.size()) goto L215;
        String r27 = this.videoFileNames.get(r7);
        if (r27 != null) goto L152;
        r27 = this.thumbsFileNames.get(r7);
    L152:
        if (r27 == null) goto L159;
        if (TextUtils.equals(r111, r27) == false) goto L159;
        RadialProgress2 r28 = this.radialProgresses.get(r7);
        if (r28 == null) goto L158;
        r28.setProgress(1.0f, true);
    L158:
        invalidate();
    L159:
        r7 = r7 + 1;
        goto L148
    L215:
        return;
    L161:
        if (r18 != NotificationCenter.fileLoadProgressChanged) goto L179;
        String r112 = (String) r20[0];
    L164:
        if (r7 >= this.thumbsFileNames.size()) goto L216;
        String r29 = this.videoFileNames.get(r7);
        if (r29 != null) goto L168;
        r29 = this.thumbsFileNames.get(r7);
    L168:
        if (r29 == null) goto L177;
        if (TextUtils.equals(r112, r29) == false) goto L177;
        RadialProgress2 r210 = this.radialProgresses.get(r7);
        if (r210 == null) goto L175;
        Long r55 = (Long) r20[1];
        Long r68 = (Long) r20[2];
        r210.setProgress(Math.min(1.0f, r55.longValue() / r68.longValue()), true);
    L175:
        invalidate();
    L177:
        r7 = r7 + 1;
        goto L164
    L216:
        return;
    L179:
        if (r18 == NotificationCenter.reloadDialogPhotos) goto L181;
        return;
    L181:
        if (this.settingMainPhoto != 0) goto L214;
        MessagesController.DialogPhotos r113 = this.dialogPhotos;
        if (r113 == null) goto L212;
        r113.reset();
        MessagesController.DialogPhotos r114 = this.dialogPhotos;
        int r211 = getCurrentItem();
        ViewPagerAdapter r0 = this.adapter;
        if (r0 == null) goto L188;
        r7 = r0.getExtraCount();
    L188:
        r114.loadAfter(r211 - r7, true);
        return;
    L212:
        return;
    }

    public View findVideoActiveView() {
        if (this.hasActiveVideo == true) goto L5;
        return null;
    L5:
        int r0 = 0;
    L7:
        if (r0 >= getChildCount()) goto L12;
        View r2 = getChildAt(r0);
        if ((r2 instanceof TextureStubView) == true) goto L10;
        r0 = r0 + 1;
        goto L7
    L10:
        return r2;
    L12:
        return null;
    }

    public void finishSettingMainPhoto() {
        this.settingMainPhoto--;
    }

    public ProfileGalleryBlurView getBlurDrawer() {
        return this.blurView;
    }

    public ImageLocation getCurrentAvatarImageLocation() {
        int r0 = getCurrentAvatarPhotoIndex();
        if (r0 >= 0) goto L5;
        int r02 = getRealPosition();
        if (r02 >= 0) goto L9;
        return null;
    L9:
        return getImageLocation(r02);
    L5:
        return getImageLocation(r0);
    }

    public float getCurrentItemProgress() {
        BackupImageView r1 = getCurrentItemView();
        if (r1 != null) goto L5;
        return 0.0f;
    L5:
        AnimatedFileDrawable r12 = r1.getImageReceiver().getAnimation();
        if (r12 != null) goto L9;
        return 0.0f;
    L9:
        return r12.getCurrentProgress();
    }

    public BackupImageView getCurrentItemView() {
        ViewPagerAdapter r0 = this.adapter;
        if (r0 != null) goto L5;
        return null;
    L5:
        if (ViewPagerAdapter.m12246$$Nest$fgetobjects(r0).isEmpty() == false) goto L7;
        return null;
    L7:
        return Item.m12241$$Nest$fgetimageView((Item) ViewPagerAdapter.m12246$$Nest$fgetobjects(this.adapter).get(getCurrentItem()));
    }

    public ImageLocation getCurrentVideoLocation(ImageLocation r13, ImageLocation r14) {
        if (r13 != null) goto L5;
        return null;
    L5:
        int r2 = 0;
    L7:
        if (r2 >= 2) goto L34;
        if (r2 != 0) goto L10;
        ArrayList<ImageLocation> r3 = this.thumbsLocations;
    L11:
        int r4 = r3.size();
        int r5 = 0;
    L12:
        if (r5 >= r4) goto L33;
        ImageLocation r6 = r3.get(r5);
        if (r6 == null) goto L32;
        TLRPC.TL_fileLocationToBeDeprecated r7 = r6.location;
        if (r7 == null) goto L32;
        int r62 = r6.dc_id;
        if (r62 != r13.dc_id) goto L25;
        int r8 = r7.local_id;
        TLRPC.TL_fileLocationToBeDeprecated r9 = r13.location;
        if (r8 != r9.local_id) goto L25;
        if (r7.volume_id != r9.volume_id) goto L25;
    L31:
        return this.videoLocations.get(r5);
    L25:
        if (r62 != r14.dc_id) goto L32;
        int r63 = r7.local_id;
        TLRPC.TL_fileLocationToBeDeprecated r82 = r14.location;
        if (r63 != r82.local_id) goto L32;
        if (r7.volume_id == r82.volume_id) goto L31;
    L32:
        r5 = r5 + 1;
        goto L12
    L33:
        r2 = r2 + 1;
        goto L7
    L10:
        r3 = this.imagesLocations;
        goto L11
    L34:
        return null;
    }

    public long getDialogId() {
        return this.dialogId;
    }

    public ImageLocation getImageLocation(int r2) {
        if (r2 >= 0) goto L4;
        return null;
    L4:
        if (r2 >= this.imagesLocations.size()) goto L13;
        ImageLocation r0 = this.videoLocations.get(r2);
        if (r0 == null) goto L10;
        return r0;
    L10:
        return this.imagesLocations.get(r2);
    L13:
        return null;
    }

    public View getItemViewAt(int r2) {
        ViewPagerAdapter r0 = this.adapter;
        if (r0 != null) goto L5;
        return null;
    L5:
        if (ViewPagerAdapter.m12246$$Nest$fgetobjects(r0).size() <= r2) goto L14;
        if (r2 < 0) goto L15;
        Item r1 = (Item) ViewPagerAdapter.m12246$$Nest$fgetobjects(this.adapter).get(r2);
        View r22 = Item.m12242$$Nest$fgettextureViewStubView(r1);
        if (r22 == null) goto L10;
        return r22;
    L10:
        return Item.m12241$$Nest$fgetimageView(r1);
    L15:
        return null;
    L14:
        return null;
    }

    public TLRPC.Photo getPhoto(int r2) {
        if (r2 >= 0) goto L4;
        return null;
    L4:
        if (r2 < this.photos.size()) goto L7;
        return null;
    L7:
        return this.photos.get(r2);
    }

    public int getRealCount() {
        int r0 = this.photos.size();
        if (this.hasActiveVideo == true) goto L5;
        return r0;
    L5:
        return r0 + 1;
    }

    public ImageLocation getRealImageLocation(int r2) {
        if (r2 >= 0) goto L4;
        return null;
    L4:
        if (r2 < this.imagesLocations.size()) goto L7;
        return null;
    L7:
        return this.imagesLocations.get(r2);
    }

    public int getRealPosition() {
        return this.adapter.getRealPosition(getCurrentItem());
    }

    public int getRealPosition(int r1) {
        return this.adapter.getRealPosition(r1);
    }

    public boolean hasImages() {
        return !this.imagesLocations.isEmpty();
    }

    public boolean initIfEmpty(VectorAvatarThumbDrawable r5, ImageLocation r6, ImageLocation r7, boolean r8) {
        int r0 = 0;
        if (r6 == null) goto L36;
        if (r7 == null) goto L36;
        if (this.settingMainPhoto != 0) goto L36;
        ImageLocation r1 = this.prevImageLocation;
        if (r1 == null) goto L13;
        if (r1.location.local_id != r6.location.local_id) goto L13;
    L32:
        if (this.imagesLocations.isEmpty() == true) goto L34;
        return false;
    L34:
        this.prevImageLocation = r6;
        this.prevThumbLocation = r7;
        this.prevVectorAvatarThumbDrawable = r5;
        this.thumbsFileNames.add(null);
        this.videoFileNames.add(null);
        this.imagesLocations.add(r6);
        this.thumbsLocations.add(r7);
        this.vectorAvatars.add(r5);
        this.videoLocations.add(null);
        this.photos.add(null);
        this.imagesLocationsSizes.add(-1);
        this.imagesUploadProgress.add(null);
        getAdapter().notifyDataSetChanged();
        resetCurrentItem();
        return true;
    L13:
        if (this.imagesLocations.isEmpty() == true) goto L23;
        this.prevImageLocation = r6;
        if (r8 == false) goto L22;
        MessagesController.DialogPhotos r52 = this.dialogPhotos;
        if (r52 == null) goto L22;
        r52.reset();
        MessagesController.DialogPhotos r53 = this.dialogPhotos;
        int r62 = getCurrentItem();
        ViewPagerAdapter r4 = this.adapter;
        if (r4 == null) goto L21;
        r0 = r4.getExtraCount();
    L21:
        r53.loadAfter(r62 - r0, true);
    L22:
        return true;
    L23:
        if (r8 == false) goto L32;
        MessagesController.DialogPhotos r82 = this.dialogPhotos;
        if (r82 == null) goto L32;
        r82.reset();
        MessagesController.DialogPhotos r83 = this.dialogPhotos;
        int r12 = getCurrentItem();
        ViewPagerAdapter r3 = this.adapter;
        if (r3 == null) goto L29;
        int r32 = r3.getExtraCount();
    L30:
        r83.loadAfter(r12 - r32, true);
        goto L32
    L29:
        r32 = 0;
    L36:
        return false;
    }

    public boolean isCurrentItemVideo() {
        int r0 = getRealPosition();
        if (this.hasActiveVideo == false) goto L7;
        if (r0 != 0) goto L6;
        return false;
    L6:
        r0 = r0 - 1;
    L7:
        if (r0 >= 0) goto L9;
    L15:
        return false;
    L9:
        if (r0 >= this.videoLocations.size()) goto L15;
        if (this.videoLocations.get(r0) == null) goto L15;
        return true;
    }

    public boolean isLoadingCurrentVideo() {
        ArrayList<ImageLocation> r0 = this.videoLocations;
        if (this.hasActiveVideo == false) goto L5;
        int r1 = getRealPosition() - 1;
    L7:
        if (r0.get(r1) != null) goto L9;
        return false;
    L9:
        BackupImageView r3 = getCurrentItemView();
        if (r3 != null) goto L12;
        return false;
    L12:
        AnimatedFileDrawable r32 = r3.getImageReceiver().getAnimation();
        if (r32 != null) goto L15;
    L18:
        return true;
    L15:
        if (r32.hasBitmap() == false) goto L18;
        return false;
    L5:
        r1 = getRealPosition();
        goto L7
    }

    public boolean isZooming() {
        PinchToZoomHelper r0 = this.pinchToZoomHelper;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isInOverlayMode() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public void onDestroy() {
        this.blurView = null;
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.dialogPhotosLoaded);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileLoaded);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.fileLoadProgressChanged);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.reloadDialogPhotos);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.dialogPhotosUpdate);
        int r0 = getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L13;
        View r2 = getChildAt(r1);
        if ((r2 instanceof BackupImageView) == false) goto L12;
        BackupImageView r22 = (BackupImageView) r2;
        if (r22.getImageReceiver().hasStaticThumb() == false) goto L12;
        Drawable r3 = r22.getImageReceiver().getDrawable();
        if ((r3 instanceof AnimatedFileDrawable) == false) goto L12;
        ((AnimatedFileDrawable) r3).removeSecondParentView(r22);
    L12:
        r1 = r1 + 1;
        goto L3
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r3) {
        if (this.parentListView.getScrollState() == 0) goto L7;
        return false;
    L7:
        if (getParent() == null) goto L12;
        if (getParent().getParent() == null) goto L12;
        getParent().getParent().requestDisallowInterceptTouchEvent(canScrollHorizontally(-1));
    L12:
        return super.onInterceptTouchEvent(r3);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        ProfileGalleryBlurView r12 = this.blurView;
        if (r12 == null) goto L6;
        r12.setTranslationY(getHeight() - this.blurView.getMeasuredHeight());
        return;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public void onSizeChanged(int r1, int r2, int r3, int r4) {
        super.onSizeChanged(r1, r2, r3, r4);
        ProfileGalleryBlurView r0 = this.blurView;
        if (r0 == null) goto L6;
        r0.notifyUpdateSize();
        return;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent r20) {
        if (this.adapter != null) goto L6;
        return false;
    L6:
        if (this.parentListView.getScrollState() != 0) goto L8;
    L13:
        int r9 = r20.getAction();
        if (this.pinchToZoomHelper == null) goto L32;
        if (getCurrentItemView() == null) goto L32;
        if (r9 == 0) goto L24;
        if (this.isDownReleased == false) goto L24;
        if (this.pinchToZoomHelper.isInOverlayMode() == true) goto L24;
        this.pinchToZoomHelper.checkPinchToZoom(MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0), this, getCurrentItemView().getImageReceiver(), null, null, null);
    L24:
        if (this.pinchToZoomHelper.checkPinchToZoom(r20, this, getCurrentItemView().getImageReceiver(), null, null, null) == false) goto L32;
        if (this.isDownReleased == true) goto L30;
        this.isDownReleased = true;
        Callback r0 = this.callback;
        if (r0 == null) goto L30;
        r0.onRelease();
    L30:
        return true;
    L32:
        if (r9 != 0) goto L44;
        this.isScrollingListView = true;
        this.isSwipingViewPager = true;
        this.scrolledByUser = true;
        this.downPoint.set(r20.getX(), r20.getY());
        if (this.adapter.getCount() <= 1) goto L42;
        Callback r1 = this.callback;
        if (r1 == null) goto L42;
        if (r20.getX() >= (getWidth() / 3.0f)) goto L40;
        boolean r02 = true;
    L41:
        r1.onDown(r02);
        goto L42
    L40:
        r02 = false;
    L42:
        this.isDownReleased = false;
    L92:
        if (this.isScrollingListView == false) goto L94;
        boolean r3 = this.parentListView.onTouchEvent(r20);
    L96:
        if (this.isSwipingViewPager == true) goto L105;
    L101:
        if (r9 == 1) goto L103;
        if (r9 == 3) goto L103;
    L104:
        return r3;
    L103:
        this.isScrollingListView = false;
        this.isSwipingViewPager = false;
        goto L104
    L105:
        r3 = r3 | super.onTouchEvent(r20);
    L99:
        e = move-exception;
        FileLog.e(e);
        goto L101
    L94:
        r3 = false;
        goto L96
    L44:
        if (r9 != 1) goto L64;
        if (this.isDownReleased == true) goto L92;
        int r32 = getRealCount();
        int r4 = getCurrentItem();
        if (r32 <= 1) goto L92;
        float r5 = r20.getX();
        float r6 = getWidth() / 3.0f;
        ViewPagerAdapter r52 = this.adapter;
        if (r5 <= r6) goto L55;
        int r03 = r52.getExtraCount();
        int r42 = r4 + 1;
        if (r42 >= (r32 + r03)) goto L59;
        r03 = r42;
    L59:
        Callback r12 = this.callback;
        if (r12 == null) goto L62;
        r12.onRelease();
    L62:
        setCurrentItem(r03, false);
        goto L92
    L55:
        int r04 = r52.getExtraCount();
        int r13 = (-1) + r4;
        if (r13 >= r04) goto L58;
        r03 = (r32 + r04) - 1;
        goto L59
    L58:
        r03 = r13;
        goto L59
    L64:
        if (r9 != 2) goto L92;
        float r05 = r20.getX() - this.downPoint.x;
        float r33 = r20.getY() - this.downPoint.y;
        if (Math.abs(r33) < this.touchSlop) goto L68;
    L71:
        boolean r43 = true;
    L72:
        if (r43 == false) goto L76;
        this.isDownReleased = true;
        Callback r53 = this.callback;
        if (r53 == null) goto L76;
        r53.onRelease();
    L76:
        boolean r54 = this.isSwipingViewPager;
        if (r54 == true) goto L79;
    L85:
        if (r54 == false) goto L92;
        if (canScrollHorizontally(-1) == true) goto L92;
        if (r05 <= this.touchSlop) goto L92;
        return false;
    L79:
        if (this.isScrollingListView == false) goto L85;
        if (r43 == false) goto L92;
        if (Math.abs(r33) <= Math.abs(r05)) goto L84;
        this.isSwipingViewPager = false;
        MotionEvent r06 = MotionEvent.obtain(r20);
        r06.setAction(3);
        super.onTouchEvent(r06);
        r06.recycle();
        goto L92
    L84:
        this.isScrollingListView = false;
        MotionEvent r07 = MotionEvent.obtain(r20);
        r07.setAction(3);
        this.parentListView.onTouchEvent(r07);
        r07.recycle();
        goto L92
    L68:
        if (Math.abs(r05) >= this.touchSlop) goto L71;
        r43 = false;
        goto L72
    L8:
        if (this.isScrollingListView == true) goto L13;
        if (this.isSwipingViewPager == false) goto L13;
        this.isSwipingViewPager = false;
        MotionEvent r08 = MotionEvent.obtain(r20);
        r08.setAction(3);
        super.onTouchEvent(r08);
        r08.recycle();
        return false;
    }

    public boolean removePhotoAtIndex(int r4) {
        if (r4 >= 0) goto L5;
    L20:
        return false;
    L5:
        if (r4 >= this.photos.size()) goto L20;
        TLRPC.Photo r1 = this.photos.get(r4);
        if (r1 == null) goto L13;
        MessagesController.DialogPhotos r2 = this.dialogPhotos;
        if (r2 == null) goto L13;
        r2.removePhoto(r1.id);
        return true;
    L13:
        this.photos.remove(r4);
        this.thumbsFileNames.remove(r4);
        this.videoFileNames.remove(r4);
        this.videoLocations.remove(r4);
        this.imagesLocations.remove(r4);
        this.thumbsLocations.remove(r4);
        this.vectorAvatars.remove(r4);
        this.imagesLocationsSizes.remove(r4);
        this.radialProgresses.delete(r4);
        this.imagesUploadProgress.remove(r4);
        if (r4 == 0) goto L16;
    L18:
        this.adapter.notifyDataSetChanged();
        return this.photos.isEmpty();
    L16:
        if (this.imagesLocations.isEmpty() == true) goto L18;
        this.prevImageLocation = this.imagesLocations.get(0);
        this.prevThumbLocation = null;
        this.prevVectorAvatarThumbDrawable = null;
        goto L18
    }

    public void removeUploadingImage(ImageLocation r1) {
        this.uploadingImageLocation = r1;
        this.currentUploadingImageLocation = null;
        this.curreantUploadingThumbLocation = null;
    }

    public void replaceFirstPhoto(TLRPC.Photo r2, TLRPC.Photo r3) {
        if (this.photos.isEmpty() == true) goto L10;
        int r22 = this.photos.indexOf(r2);
        if (r22 >= 0) goto L8;
        return;
    L8:
        this.photos.set(r22, r3);
        return;
    }

    public void resetCurrentItem() {
        if (this.adapter == null) goto L13;
        int r0 = getCurrentAvatarPage();
        if (r0 >= 0) goto L9;
        return;
    L9:
        if (getCurrentItem() == r0) goto L12;
        setCurrentItem(r0, false);
        return;
    L12:
        return;
    }

    public void scrollToLastItem() {
        int r0 = 0;
    L4:
        if (getRealPosition(r0) == (getRealCount() - 1)) goto L6;
        r0 = r0 + 1;
        goto L4
    L6:
        setCurrentItem(r0, true);
    }

    @Override // android.view.View
    public void setAlpha(float r1) {
        super.setAlpha(r1);
        ProfileGalleryBlurView r0 = this.blurView;
        if (r0 == null) goto L6;
        r0.setAlpha(r1);
        return;
    }

    public void setAnimatedFileMaybe(AnimatedFileDrawable r6) {
        if (r6 != null) goto L4;
        return;
    L4:
        if (this.adapter == null) goto L27;
        int r0 = getChildCount();
        int r1 = 0;
    L7:
        if (r1 >= r0) goto L28;
        View r2 = getChildAt(r1);
        if ((r2 instanceof BackupImageView) == false) goto L20;
        ViewPagerAdapter r3 = this.adapter;
        if (r3.getRealPosition(ViewPagerAdapter.m12245$$Nest$fgetimageViews(r3).indexOf(r2)) != 0) goto L20;
        BackupImageView r22 = (BackupImageView) r2;
        AnimatedFileDrawable r32 = r22.getImageReceiver().getAnimation();
        if (r32 == r6) goto L20;
        if (r32 == null) goto L19;
        r32.removeSecondParentView(r22);
    L19:
        r22.setImageDrawable(r6);
        r6.addSecondParentView(this);
        r6.setInvalidateParentViewWithSecond(true);
    L20:
        r1 = r1 + 1;
        goto L7
    L28:
        return;
    }

    public void setChatInfo(TLRPC.ChatFull r5) {
        this.chatInfo = r5;
        if (this.photos.isEmpty() == false) goto L5;
        return;
    L5:
        if (this.photos.get(0) == null) goto L7;
        return;
    L7:
        if (this.chatInfo != null) goto L9;
        return;
    L9:
        if (this.imagesLocations.get(0) != null) goto L11;
        return;
    L11:
        if (FileLoader.isSamePhoto(this.imagesLocations.get(0).location, this.chatInfo.chat_photo) == false) goto L24;
        this.photos.set(0, this.chatInfo.chat_photo);
        if (this.chatInfo.chat_photo.video_sizes.isEmpty() == true) goto L17;
        TLRPC.VideoSize r52 = FileLoader.getClosestVideoSizeWithSize(this.chatInfo.chat_photo.video_sizes, 1000);
        this.videoLocations.set(0, ImageLocation.getForPhoto(r52, this.chatInfo.chat_photo));
        this.videoFileNames.set(0, FileLoader.getAttachFileName(r52));
        Callback r53 = this.callback;
        if (r53 == null) goto L18;
        r53.onPhotosLoaded();
    L18:
        this.imagesUploadProgress.set(0, null);
        this.adapter.notifyDataSetChanged();
        return;
    L17:
        this.videoLocations.set(0, null);
        this.videoFileNames.add(0, null);
        goto L18
    }

    public void setCreateThumbFromParent(boolean r1) {
        this.createThumbFromParent = r1;
    }

    public void setCurrentRealPosition(int r2, boolean r3) {
        ViewPagerAdapter r0 = this.adapter;
        if (r0 != null) goto L5;
        return;
    L5:
        setCurrentItem(r2 + r0.getExtraCount(), r3);
    }

    public void setCustomAvatarProgress(float r1) {
    }

    public void setData(long r2) {
        setData(r2, false);
    }

    public void setData(long r3, boolean r5) {
        if (this.dialogId != r3) goto L7;
        if (r5 == true) goto L7;
        resetCurrentItem();
        return;
    L7:
        this.forceResetPosition = true;
        reset();
        this.dialogId = r3;
        MessagesController.DialogPhotos r32 = MessagesController.getInstance(this.currentAccount).getDialogPhotos(r3);
        this.dialogPhotos = r32;
        r32.loadCache();
    }

    public void setHasActiveVideo(boolean r1) {
        this.hasActiveVideo = r1;
    }

    public void setImagesLayerNum(int r1) {
        this.imagesLayerNum = r1;
    }

    public void setInvalidateWithParent(boolean r1) {
        this.invalidateWithParent = r1;
    }

    public void setParentAvatarImage(BackupImageView r1) {
        ViewPagerAdapter r0 = this.adapter;
        if (r0 == null) goto L6;
        ViewPagerAdapter.m12247$$Nest$fputparentAvatarImageView(r0, r1);
        return;
    }

    public void setPinchToZoomHelper(PinchToZoomHelper r1) {
        this.pinchToZoomHelper = r1;
    }

    public void setRoundRadius(int r3, int r4) {
        this.roundTopRadius = r3;
        this.roundBottomRadius = r4;
        if (this.adapter == null) goto L11;
        int r32 = 0;
    L6:
        if (r32 >= ViewPagerAdapter.m12246$$Nest$fgetobjects(this.adapter).size()) goto L15;
        if (Item.m12241$$Nest$fgetimageView((Item) ViewPagerAdapter.m12246$$Nest$fgetobjects(this.adapter).get(r32)) == null) goto L10;
        AvatarImageView r42 = Item.m12241$$Nest$fgetimageView((Item) ViewPagerAdapter.m12246$$Nest$fgetobjects(this.adapter).get(r32));
        int r0 = this.roundTopRadius;
        int r1 = this.roundBottomRadius;
        r42.setRoundRadius(r0, r0, r1, r1);
    L10:
        r32 = r32 + 1;
        goto L6
    L15:
        return;
    }

    public void setUploadProgress(ImageLocation r4, float r5) {
        if (r4 == null) goto L16;
        int r0 = 0;
        int r1 = 0;
    L6:
        if (r1 >= this.imagesLocations.size()) goto L14;
        if (this.imagesLocations.get(r1) == r4) goto L9;
        r1 = r1 + 1;
        goto L6
    L9:
        this.imagesUploadProgress.set(r1, Float.valueOf(r5));
        if (this.radialProgresses.get(r1) == null) goto L14;
        this.radialProgresses.get(r1).setProgress(r5, true);
    L14:
        if (r0 >= getChildCount()) goto L21;
        getChildAt(r0).invalidate();
        r0 = r0 + 1;
        goto L14
    L21:
        return;
    }

    @Override // android.view.View
    public void setVisibility(int r1) {
        super.setVisibility(r1);
        ProfileGalleryBlurView r0 = this.blurView;
        if (r0 == null) goto L6;
        r0.setVisibility(r1);
        return;
    }

    public void startMovePhotoToBegin(int r4) {
        MessagesController.DialogPhotos r0 = this.dialogPhotos;
        if (r0 == null) goto L6;
        r0.moveToStart(r4);
        return;
    L6:
        if (r4 > 0) goto L8;
        return;
    L8:
        if (r4 >= this.photos.size()) goto L13;
        this.settingMainPhoto++;
        TLRPC.Photo r02 = this.photos.get(r4);
        this.photos.remove(r4);
        this.photos.add(0, r02);
        String r03 = this.thumbsFileNames.get(r4);
        this.thumbsFileNames.remove(r4);
        this.thumbsFileNames.add(0, r03);
        ArrayList<String> r04 = this.videoFileNames;
        r04.add(0, r04.remove(r4));
        ImageLocation r05 = this.videoLocations.get(r4);
        this.videoLocations.remove(r4);
        this.videoLocations.add(0, r05);
        ImageLocation r06 = this.imagesLocations.get(r4);
        this.imagesLocations.remove(r4);
        this.imagesLocations.add(0, r06);
        ImageLocation r07 = this.thumbsLocations.get(r4);
        this.thumbsLocations.remove(r4);
        this.thumbsLocations.add(0, r07);
        VectorAvatarThumbDrawable r08 = this.vectorAvatars.get(r4);
        this.vectorAvatars.remove(r4);
        this.vectorAvatars.add(0, r08);
        Integer r09 = this.imagesLocationsSizes.get(r4);
        this.imagesLocationsSizes.remove(r4);
        this.imagesLocationsSizes.add(0, r09);
        Float r010 = this.imagesUploadProgress.get(r4);
        this.imagesUploadProgress.remove(r4);
        this.imagesUploadProgress.add(0, r010);
        this.prevImageLocation = this.imagesLocations.get(0);
        return;
    }
}
