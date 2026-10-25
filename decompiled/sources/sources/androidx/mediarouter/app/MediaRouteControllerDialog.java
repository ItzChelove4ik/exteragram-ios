package androidx.mediarouter.app;

import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.core.util.ObjectsCompat;
import androidx.mediarouter.R;
import androidx.mediarouter.app.OverlayListView;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import androidx.palette.graphics.Palette;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.telegram.tgnet.TLObject;

/* loaded from: classes4.dex */
public class MediaRouteControllerDialog extends AlertDialog {
    static final int BUTTON_DISCONNECT_RES_ID = 16908314;
    private static final int BUTTON_NEUTRAL_RES_ID = 16908315;
    static final int BUTTON_STOP_RES_ID = 16908313;
    static final int CONNECTION_TIMEOUT_MILLIS = 0;
    static final boolean DEBUG = false;
    static final String TAG = "MediaRouteCtrlDialog";
    static final int VOLUME_UPDATE_DELAY_MILLIS = 500;
    final AccessibilityManager mAccessibilityManager;
    int mArtIconBackgroundColor;
    Bitmap mArtIconBitmap;
    boolean mArtIconIsLoaded;
    Bitmap mArtIconLoadedBitmap;
    Uri mArtIconUri;
    private ImageView mArtView;
    private boolean mAttachedToWindow;
    private final MediaRouterCallback mCallback;
    private ImageButton mCloseButton;
    Context mContext;
    MediaControllerCallback mControllerCallback;
    private boolean mCreated;
    private FrameLayout mCustomControlLayout;
    private View mCustomControlView;
    FrameLayout mDefaultControlLayout;
    MediaDescriptionCompat mDescription;
    private LinearLayout mDialogAreaLayout;
    private int mDialogContentWidth;
    private Button mDisconnectButton;
    private View mDividerView;
    final boolean mEnableGroupVolumeUX;
    private FrameLayout mExpandableAreaLayout;
    private Interpolator mFastOutSlowInInterpolator;
    FetchArtTask mFetchArtTask;
    private MediaRouteExpandCollapseButton mGroupExpandCollapseButton;
    int mGroupListAnimationDurationMs;
    Runnable mGroupListFadeInAnimation;
    private int mGroupListFadeInDurationMs;
    private int mGroupListFadeOutDurationMs;
    private List<MediaRouter.RouteInfo> mGroupMemberRoutes;
    Set<MediaRouter.RouteInfo> mGroupMemberRoutesAdded;
    Set<MediaRouter.RouteInfo> mGroupMemberRoutesAnimatingWithBitmap;
    private Set<MediaRouter.RouteInfo> mGroupMemberRoutesRemoved;
    boolean mHasPendingUpdate;
    private Interpolator mInterpolator;
    boolean mIsGroupExpanded;
    boolean mIsGroupListAnimating;
    boolean mIsGroupListAnimationPending;
    private Interpolator mLinearOutSlowInInterpolator;
    MediaControllerCompat mMediaController;
    private LinearLayout mMediaMainControlLayout;
    boolean mPendingUpdateAnimationNeeded;
    private ImageButton mPlaybackControlButton;
    private RelativeLayout mPlaybackControlLayout;
    final MediaRouter.RouteInfo mRoute;
    MediaRouter.RouteInfo mRouteInVolumeSliderTouched;
    private TextView mRouteNameTextView;
    final MediaRouter mRouter;
    PlaybackStateCompat mState;
    private Button mStopCastingButton;
    private TextView mSubtitleView;
    private TextView mTitleView;
    VolumeChangeListener mVolumeChangeListener;
    private boolean mVolumeControlEnabled;
    LinearLayout mVolumeControlLayout;
    VolumeGroupAdapter mVolumeGroupAdapter;
    OverlayListView mVolumeGroupList;
    private int mVolumeGroupListItemHeight;
    private int mVolumeGroupListItemIconSize;
    private int mVolumeGroupListMaxHeight;
    private final int mVolumeGroupListPaddingTop;
    SeekBar mVolumeSlider;
    Map<MediaRouter.RouteInfo, SeekBar> mVolumeSliderMap;

    /* renamed from: androidx.mediarouter.app.MediaRouteControllerDialog$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ MediaRouteControllerDialog this$0;

        public AnonymousClass1(MediaRouteControllerDialog r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.startGroupListFadeInAnimation();
        }
    }

    /* renamed from: androidx.mediarouter.app.MediaRouteControllerDialog$10, reason: invalid class name */
    public class AnonymousClass10 implements OverlayListView.OverlayObject.OnAnimationEndListener {
        final /* synthetic */ MediaRouteControllerDialog this$0;
        final /* synthetic */ MediaRouter.RouteInfo val$route;

        public AnonymousClass10(MediaRouteControllerDialog r1, MediaRouter.RouteInfo r2) {
            this.this$0 = r1;
            this.val$route = r2;
        }

        @Override // androidx.mediarouter.app.OverlayListView.OverlayObject.OnAnimationEndListener
        public void onAnimationEnd() {
            this.this$0.mGroupMemberRoutesAnimatingWithBitmap.remove(this.val$route);
            this.this$0.mVolumeGroupAdapter.notifyDataSetChanged();
        }
    }

    /* renamed from: androidx.mediarouter.app.MediaRouteControllerDialog$11, reason: invalid class name */
    public class AnonymousClass11 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ MediaRouteControllerDialog this$0;

        public AnonymousClass11(MediaRouteControllerDialog r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            this.this$0.mVolumeGroupList.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            this.this$0.startGroupListFadeInAnimationInternal();
        }
    }

    /* renamed from: androidx.mediarouter.app.MediaRouteControllerDialog$12, reason: invalid class name */
    public class AnonymousClass12 implements Animation.AnimationListener {
        final /* synthetic */ MediaRouteControllerDialog this$0;

        public AnonymousClass12(MediaRouteControllerDialog r1) {
            this.this$0 = r1;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation r1) {
            this.this$0.finishAnimation(true);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation r1) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation r1) {
        }
    }

    /* renamed from: androidx.mediarouter.app.MediaRouteControllerDialog$2, reason: invalid class name */
    public class AnonymousClass2 implements View.OnClickListener {
        final /* synthetic */ MediaRouteControllerDialog this$0;

        public AnonymousClass2(MediaRouteControllerDialog r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r1) {
            this.this$0.dismiss();
        }
    }

    /* renamed from: androidx.mediarouter.app.MediaRouteControllerDialog$3, reason: invalid class name */
    public class AnonymousClass3 implements View.OnClickListener {
        final /* synthetic */ MediaRouteControllerDialog this$0;

        public AnonymousClass3(MediaRouteControllerDialog r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r1) {
        }
    }

    /* renamed from: androidx.mediarouter.app.MediaRouteControllerDialog$4, reason: invalid class name */
    public class AnonymousClass4 implements View.OnClickListener {
        final /* synthetic */ MediaRouteControllerDialog this$0;

        public AnonymousClass4(MediaRouteControllerDialog r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r1) {
            MediaControllerCompat r12 = this.this$0.mMediaController;
            if (r12 == null) goto L12;
            PendingIntent r13 = r12.getSessionActivity();
            if (r13 == null) goto L13;
            r13.send();     // Catch: PendingIntent.CanceledException -> L8
            this.this$0.dismiss();     // Catch: PendingIntent.CanceledException -> L8
            return;
        L8:
            Log.e("MediaRouteCtrlDialog", r13 + " was not sent, it had been canceled.");
            return;
        L13:
            return;
        }
    }

    /* renamed from: androidx.mediarouter.app.MediaRouteControllerDialog$5, reason: invalid class name */
    public class AnonymousClass5 implements View.OnClickListener {
        final /* synthetic */ MediaRouteControllerDialog this$0;

        public AnonymousClass5(MediaRouteControllerDialog r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r3) {
            MediaRouteControllerDialog r32 = this.this$0;
            boolean r0 = r32.mIsGroupExpanded;
            r32.mIsGroupExpanded = !r0;
            if (r0 == true) goto L5;
            r32.mVolumeGroupList.setVisibility(0);
        L5:
            this.this$0.loadInterpolator();
            this.this$0.updateLayoutHeight(true);
        }
    }

    /* renamed from: androidx.mediarouter.app.MediaRouteControllerDialog$6, reason: invalid class name */
    public class AnonymousClass6 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ MediaRouteControllerDialog this$0;
        final /* synthetic */ boolean val$animate;

        public AnonymousClass6(MediaRouteControllerDialog r1, boolean r2) {
            this.this$0 = r1;
            this.val$animate = r2;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            this.this$0.mDefaultControlLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            MediaRouteControllerDialog r0 = this.this$0;
            if (r0.mIsGroupListAnimating == false) goto L6;
            r0.mIsGroupListAnimationPending = true;
            return;
        L6:
            r0.updateLayoutHeightInternal(this.val$animate);
        }
    }

    /* renamed from: androidx.mediarouter.app.MediaRouteControllerDialog$7, reason: invalid class name */
    public class AnonymousClass7 extends Animation {
        final /* synthetic */ MediaRouteControllerDialog this$0;
        final /* synthetic */ int val$endValue;
        final /* synthetic */ int val$startValue;
        final /* synthetic */ View val$view;

        public AnonymousClass7(MediaRouteControllerDialog r1, int r2, int r3, View r4) {
            this.this$0 = r1;
            this.val$startValue = r2;
            this.val$endValue = r3;
            this.val$view = r4;
        }

        @Override // android.view.animation.Animation
        public void applyTransformation(float r2, Transformation r3) {
            MediaRouteControllerDialog.setLayoutHeight(this.val$view, this.val$startValue - ((int) ((r3 - this.val$endValue) * r2)));
        }
    }

    /* renamed from: androidx.mediarouter.app.MediaRouteControllerDialog$8, reason: invalid class name */
    public class AnonymousClass8 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ MediaRouteControllerDialog this$0;
        final /* synthetic */ Map val$previousRouteBitmapMap;
        final /* synthetic */ Map val$previousRouteBoundMap;

        public AnonymousClass8(MediaRouteControllerDialog r1, Map r2, Map r3) {
            this.this$0 = r1;
            this.val$previousRouteBoundMap = r2;
            this.val$previousRouteBitmapMap = r3;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            this.this$0.mVolumeGroupList.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            this.this$0.animateGroupListItemsInternal(this.val$previousRouteBoundMap, this.val$previousRouteBitmapMap);
        }
    }

    /* renamed from: androidx.mediarouter.app.MediaRouteControllerDialog$9, reason: invalid class name */
    public class AnonymousClass9 implements Animation.AnimationListener {
        final /* synthetic */ MediaRouteControllerDialog this$0;

        public AnonymousClass9(MediaRouteControllerDialog r1) {
            this.this$0 = r1;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation r1) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation r1) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation r4) {
            this.this$0.mVolumeGroupList.startAnimationAll();
            MediaRouteControllerDialog r3 = this.this$0;
            r3.mVolumeGroupList.postDelayed(r3.mGroupListFadeInAnimation, r3.mGroupListAnimationDurationMs);
        }
    }

    public final class ClickListener implements View.OnClickListener {
        final /* synthetic */ MediaRouteControllerDialog this$0;

        public ClickListener(MediaRouteControllerDialog r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r4) {
            int r42 = r4.getId();
            int r0 = 1;
            if (r42 == 16908313) goto L41;
            if (r42 == 16908314) goto L41;
            if (r42 != R.id.mr_control_playback_ctrl) goto L37;
            MediaRouteControllerDialog r43 = this.this$0;
            if (r43.mMediaController == null) goto L48;
            PlaybackStateCompat r44 = r43.mState;
            if (r44 == null) goto L49;
            int r2 = 0;
            if (r44.getState() == 3) goto L17;
            r0 = 0;
        L17:
            if (r0 != 0) goto L19;
        L21:
            if (r0 != 0) goto L23;
        L25:
            if (r0 == 0) goto L27;
        L29:
            AccessibilityManager r45 = this.this$0.mAccessibilityManager;
            if (r45 != null) goto L32;
            return;
        L32:
            if (r45.isEnabled() == false) goto L51;
            if (r2 == 0) goto L52;
            AccessibilityEvent r46 = AccessibilityEvent.obtain(16384);
            r46.setPackageName(this.this$0.mContext.getPackageName());
            r46.setClassName(ClickListener.class.getName());
            r46.getText().add(this.this$0.mContext.getString(r2));
            this.this$0.mAccessibilityManager.sendAccessibilityEvent(r46);
            return;
        L52:
            return;
        L51:
            return;
        L27:
            if (this.this$0.isPlayActionSupported() == false) goto L29;
            this.this$0.mMediaController.getTransportControls().play();
            r2 = R.string.mr_controller_play;
            goto L29
        L23:
            if (this.this$0.isStopActionSupported() == false) goto L25;
            this.this$0.mMediaController.getTransportControls().stop();
            r2 = R.string.mr_controller_stop;
            goto L29
        L19:
            if (this.this$0.isPauseActionSupported() == false) goto L21;
            this.this$0.mMediaController.getTransportControls().pause();
            r2 = R.string.mr_controller_pause;
            goto L29
        L49:
            return;
        L48:
            return;
        L37:
            if (r42 != R.id.mr_close) goto L53;
            this.this$0.dismiss();
            return;
        L53:
            return;
        L41:
            if (this.this$0.mRoute.isSelected() == false) goto L46;
            MediaRouter r22 = this.this$0.mRouter;
            if (r42 != 16908313) goto L45;
            r0 = 2;
        L45:
            r22.unselect(r0);
        L46:
            this.this$0.dismiss();
        }
    }

    public class FetchArtTask extends AsyncTask<Void, Void, Bitmap> {
        private static final long SHOW_ANIM_TIME_THRESHOLD_MILLIS = 120;
        private int mBackgroundColor;
        private final Bitmap mIconBitmap;
        private final Uri mIconUri;
        private long mStartTimeMillis;
        final /* synthetic */ MediaRouteControllerDialog this$0;

        public FetchArtTask(MediaRouteControllerDialog r4) {
            this.this$0 = r4;
            MediaDescriptionCompat r0 = r4.mDescription;
            Uri r1 = null;
            if (r0 != null) goto L5;
            Bitmap r02 = null;
        L7:
            if (MediaRouteControllerDialog.isBitmapRecycled(r02) == false) goto L9;
            Log.w("MediaRouteCtrlDialog", "Can't fetch the given art bitmap because it's already recycled.");
            r02 = null;
        L9:
            this.mIconBitmap = r02;
            MediaDescriptionCompat r42 = r4.mDescription;
            if (r42 == null) goto L13;
            r1 = r42.getIconUri();
        L13:
            this.mIconUri = r1;
            return;
        L5:
            r02 = r0.getIconBitmap();
            goto L7
        }

        private InputStream openInputStreamByScheme(Uri r3) {
            String r0 = r3.getScheme().toLowerCase();
            if ("android.resource".equals(r0) == false) goto L5;
        L10:
            InputStream r2 = this.this$0.mContext.getContentResolver().openInputStream(r3);
        L11:
            if (r2 != null) goto L15;
            return null;
        L15:
            return new BufferedInputStream(r2);
        L5:
            if ("content".equals(r0) == true) goto L10;
            if ("file".equals(r0) == true) goto L10;
            URLConnection r22 = new URL(r3.toString()).openConnection();
            int r32 = MediaRouteControllerDialog.CONNECTION_TIMEOUT_MILLIS;
            r22.setConnectTimeout(r32);
            r22.setReadTimeout(r32);
            r2 = r22.getInputStream();
            goto L11
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v0 */
        /* JADX WARN: Type inference failed for: r4v1 */
        /* JADX WARN: Type inference failed for: r4v2, types: [java.io.InputStream] */
        /* renamed from: doInBackground, reason: avoid collision after fix types in other method */
        public Bitmap doInBackground2(Void... r10) {
            Bitmap r0 = this.mIconBitmap;
            int r1 = 0;
            ?? r4 = 0;
            if (r0 != null) goto L52;
            Uri r02 = this.mIconUri;
            if (r02 != null) goto L89;
        L50:
            r0 = null;
            goto L52
        L89:
            InputStream r03 = openInputStreamByScheme(r02);     // Catch: Throwable -> L39 IOException -> L41
            if (r03 != null) goto L17;
            Log.w("MediaRouteCtrlDialog", "Unable to open: " + this.mIconUri);     // Catch: Throwable -> L13 IOException -> L15
            if (r03 != null) goto L82;
        L12:
            return null;
        L82:
            r03.close();     // Catch: IOException -> L64
            goto L12
        L17:
            BitmapFactory.Options r5 = new BitmapFactory.Options();     // Catch: Throwable -> L13 IOException -> L15
            r5.inJustDecodeBounds = true;     // Catch: Throwable -> L13 IOException -> L15
            BitmapFactory.decodeStream(r03, null, r5);     // Catch: Throwable -> L13 IOException -> L15
            if (r5.outWidth != 0) goto L20;
        L78:
            r03.close();     // Catch: IOException -> L68
        L38:
            return null;
        L20:
            if (r5.outHeight == 0) goto L78;
            r03.reset();     // Catch: Throwable -> L13 IOException -> L24
        L30:
            r5.inJustDecodeBounds = false;     // Catch: Throwable -> L13 IOException -> L15
            r5.inSampleSize = Math.max(1, Integer.highestOneBit(r5.outHeight / this.this$0.getDesiredArtHeight(r5.outWidth, r5.outHeight)));     // Catch: Throwable -> L13 IOException -> L15
            if (isCancelled() == true) goto L76;
            Bitmap r102 = BitmapFactory.decodeStream(r03, null, r5);     // Catch: Throwable -> L13 IOException -> L15
            r03.close();     // Catch: IOException -> L65
        L36:
            r0 = r102;
            goto L52
        L76:
            r03.close();     // Catch: IOException -> L66
        L33:
            return null;
        L24:
            r03.close();     // Catch: Throwable -> L13 IOException -> L15
            r03 = openInputStreamByScheme(this.mIconUri);     // Catch: Throwable -> L13 IOException -> L15
            if (r03 != null) goto L30;
            Log.w("MediaRouteCtrlDialog", "Unable to open: " + this.mIconUri);     // Catch: Throwable -> L13 IOException -> L15
            if (r03 != null) goto L80;
        L29:
            return null;
        L80:
            r03.close();     // Catch: IOException -> L67
        L15:
            e = e;
        L43:
            Log.w("MediaRouteCtrlDialog", "Unable to open: " + this.mIconUri, e);     // Catch: Throwable -> L13
            if (r03 == null) goto L50;
            r03.close();     // Catch: IOException -> L70
            goto L50
        L41:
            e = e;
            r03 = null;
        L39:
            th = th;
        L47:
            if (r4 != 0) goto L84;
        L49:
            throw th;
        L84:
            r4.close();     // Catch: IOException -> L69
        L13:
            th = th;
            r4 = r02;
        L52:
            if (MediaRouteControllerDialog.isBitmapRecycled(r0) == false) goto L55;
            Log.w("MediaRouteCtrlDialog", "Can't use recycled bitmap: " + r0);
            return null;
        L55:
            if (r0 != null) goto L57;
        L63:
            return r0;
        L57:
            if (r0.getWidth() >= r0.getHeight()) goto L63;
            Palette r103 = new Palette.Builder(r0).maximumColorCount(1).generate();
            if (r103.getSwatches().isEmpty() == true) goto L62;
            r1 = r103.getSwatches().get(0).getRgb();
        L62:
            this.mBackgroundColor = r1;
            goto L63
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ Bitmap doInBackground(Void[] r1) {
            return doInBackground2(r1);
        }

        public Bitmap getIconBitmap() {
            return this.mIconBitmap;
        }

        public Uri getIconUri() {
            return this.mIconUri;
        }

        /* renamed from: onPostExecute, reason: avoid collision after fix types in other method */
        public void onPostExecute2(Bitmap r5) {
            MediaRouteControllerDialog r0 = this.this$0;
            r0.mFetchArtTask = null;
            if (ObjectsCompat.equals(r0.mArtIconBitmap, this.mIconBitmap) == true) goto L5;
        L8:
            MediaRouteControllerDialog r02 = this.this$0;
            r02.mArtIconBitmap = this.mIconBitmap;
            r02.mArtIconLoadedBitmap = r5;
            r02.mArtIconUri = this.mIconUri;
            r02.mArtIconBackgroundColor = this.mBackgroundColor;
            boolean r52 = true;
            r02.mArtIconIsLoaded = true;
            long r03 = SystemClock.uptimeMillis() - this.mStartTimeMillis;
            MediaRouteControllerDialog r4 = this.this$0;
            if (r03 > SHOW_ANIM_TIME_THRESHOLD_MILLIS) goto L12;
            r52 = false;
        L12:
            r4.update(r52);
            return;
        L5:
            if (ObjectsCompat.equals(this.this$0.mArtIconUri, this.mIconUri) == false) goto L8;
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ void onPostExecute(Bitmap r1) {
            onPostExecute2(r1);
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            this.mStartTimeMillis = SystemClock.uptimeMillis();
            this.this$0.clearLoadedBitmap();
        }
    }

    public final class MediaControllerCallback extends MediaControllerCompat.Callback {
        final /* synthetic */ MediaRouteControllerDialog this$0;

        public MediaControllerCallback(MediaRouteControllerDialog r1) {
            this.this$0 = r1;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onMetadataChanged(MediaMetadataCompat r2) {
            MediaRouteControllerDialog r0 = this.this$0;
            if (r2 != null) goto L5;
            MediaDescriptionCompat r22 = null;
        L6:
            r0.mDescription = r22;
            this.this$0.updateArtIconIfNeeded();
            this.this$0.update(false);
            return;
        L5:
            r22 = r2.getDescription();
            goto L6
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onPlaybackStateChanged(PlaybackStateCompat r1) {
            MediaRouteControllerDialog r0 = this.this$0;
            r0.mState = r1;
            r0.update(false);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onSessionDestroyed() {
            MediaRouteControllerDialog r0 = this.this$0;
            MediaControllerCompat r1 = r0.mMediaController;
            if (r1 == null) goto L6;
            r1.unregisterCallback(r0.mControllerCallback);
            this.this$0.mMediaController = null;
            return;
        }
    }

    public final class MediaRouterCallback extends MediaRouter.Callback {
        final /* synthetic */ MediaRouteControllerDialog this$0;

        public MediaRouterCallback(MediaRouteControllerDialog r1) {
            this.this$0 = r1;
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteChanged(MediaRouter r1, MediaRouter.RouteInfo r2) {
            this.this$0.update(true);
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteUnselected(MediaRouter r1, MediaRouter.RouteInfo r2) {
            this.this$0.update(false);
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteVolumeChanged(MediaRouter r3, MediaRouter.RouteInfo r4) {
            SeekBar r32 = this.this$0.mVolumeSliderMap.get(r4);
            int r0 = r4.getVolume();
            String r1 = MediaRouteControllerDialog.TAG;
            if (r32 != null) goto L5;
            return;
        L5:
            if (this.this$0.mRouteInVolumeSliderTouched == r4) goto L9;
            r32.setProgress(r0);
            return;
        }
    }

    public class VolumeChangeListener implements SeekBar.OnSeekBarChangeListener {
        private final Runnable mStopTrackingTouch;
        final /* synthetic */ MediaRouteControllerDialog this$0;

        /* renamed from: androidx.mediarouter.app.MediaRouteControllerDialog$VolumeChangeListener$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ VolumeChangeListener this$1;

            public AnonymousClass1(VolumeChangeListener r1) {
                this.this$1 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                MediaRouteControllerDialog r1 = this.this$1.this$0;
                if (r1.mRouteInVolumeSliderTouched == null) goto L8;
                r1.mRouteInVolumeSliderTouched = null;
                if (r1.mHasPendingUpdate == false) goto L9;
                r1.update(r1.mPendingUpdateAnimationNeeded);
                return;
            L9:
                return;
            }
        }

        public VolumeChangeListener(MediaRouteControllerDialog r1) {
            this.this$0 = r1;
            this.mStopTrackingTouch = new AnonymousClass1(this);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar r1, int r2, boolean r3) {
            if (r3 == false) goto L5;
            MediaRouter.RouteInfo r0 = (MediaRouter.RouteInfo) r1.getTag();
            String r12 = MediaRouteControllerDialog.TAG;
            r0.requestSetVolume(r2);
            return;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar r3) {
            MediaRouteControllerDialog r0 = this.this$0;
            if (r0.mRouteInVolumeSliderTouched == null) goto L5;
            r0.mVolumeSlider.removeCallbacks(this.mStopTrackingTouch);
        L5:
            MediaRouteControllerDialog r2 = this.this$0;
            r2.mRouteInVolumeSliderTouched = (MediaRouter.RouteInfo) r3.getTag();
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar r3) {
            this.this$0.mVolumeSlider.postDelayed(this.mStopTrackingTouch, 500);
        }
    }

    public class VolumeGroupAdapter extends ArrayAdapter<MediaRouter.RouteInfo> {
        final float mDisabledAlpha;
        final /* synthetic */ MediaRouteControllerDialog this$0;

        public VolumeGroupAdapter(MediaRouteControllerDialog r1, Context r2, List<MediaRouter.RouteInfo> r3) {
            this.this$0 = r1;
            super(r2, 0, r3);
            this.mDisabledAlpha = MediaRouterThemeHelper.getDisabledAlpha(r2);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int r5, View r6, ViewGroup r7) {
            int r0 = 0;
            if (r6 != null) goto L5;
            r6 = LayoutInflater.from(r7.getContext()).inflate(R.layout.mr_controller_volume_item, r7, false);
        L6:
            MediaRouter.RouteInfo r52 = (MediaRouter.RouteInfo) getItem(r5);
            if (r52 == null) goto L26;
            boolean r1 = r52.isEnabled();
            TextView r2 = (TextView) r6.findViewById(R.id.mr_name);
            r2.setEnabled(r1);
            r2.setText(r52.getName());
            MediaRouteVolumeSlider r22 = (MediaRouteVolumeSlider) r6.findViewById(R.id.mr_volume_slider);
            MediaRouterThemeHelper.setVolumeSliderColor(r7.getContext(), r22, this.this$0.mVolumeGroupList);
            r22.setTag(r52);
            this.this$0.mVolumeSliderMap.put(r52, r22);
            r22.setHideThumb(!r1);
            r22.setEnabled(r1);
            if (r1 == true) goto L11;
        L14:
            ImageView r72 = (ImageView) r6.findViewById(R.id.mr_volume_item_icon);
            if (r1 == false) goto L17;
            int r12 = 255;
        L18:
            r72.setAlpha(r12);
            LinearLayout r73 = (LinearLayout) r6.findViewById(R.id.volume_item_container);
            if (this.this$0.mGroupMemberRoutesAnimatingWithBitmap.contains(r52) == false) goto L21;
            r0 = 4;
        L21:
            r73.setVisibility(r0);
            Set<MediaRouter.RouteInfo> r4 = this.this$0.mGroupMemberRoutesAdded;
            if (r4 == null) goto L26;
            if (r4.contains(r52) == false) goto L26;
            AlphaAnimation r42 = new AlphaAnimation(0.0f, 0.0f);
            r42.setDuration(0);
            r42.setFillEnabled(true);
            r42.setFillAfter(true);
            r6.clearAnimation();
            r6.startAnimation(r42);
            goto L26
        L17:
            r12 = (int) (this.mDisabledAlpha * 255.0f);
            goto L18
        L11:
            if (this.this$0.isVolumeControlAvailable(r52) == false) goto L13;
            r22.setMax(r52.getVolumeMax());
            r22.setProgress(r52.getVolume());
            r22.setOnSeekBarChangeListener(this.this$0.mVolumeChangeListener);
            goto L14
        L13:
            r22.setMax(100);
            r22.setProgress(100);
            r22.setEnabled(false);
        L26:
            return r6;
        L5:
            this.this$0.updateVolumeGroupItemHeight(r6);
            goto L6
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean isEnabled(int r1) {
            return false;
        }
    }

    static {
        Log.isLoggable("MediaRouteCtrlDialog", 3);
        CONNECTION_TIMEOUT_MILLIS = 30000;
    }

    public MediaRouteControllerDialog(Context r2) {
        this(r2, 0);
    }

    public MediaRouteControllerDialog(Context r2, int r3) {
        Context r22 = MediaRouterThemeHelper.createThemedDialogContext(r2, r3, true);
        super(r22, MediaRouterThemeHelper.createThemedDialogStyle(r22));
        this.mVolumeControlEnabled = true;
        this.mGroupListFadeInAnimation = new AnonymousClass1(this);
        this.mContext = getContext();
        this.mControllerCallback = new MediaControllerCallback(this);
        MediaRouter r32 = MediaRouter.getInstance(this.mContext);
        this.mRouter = r32;
        this.mEnableGroupVolumeUX = MediaRouter.isGroupVolumeUxEnabled();
        this.mCallback = new MediaRouterCallback(this);
        this.mRoute = r32.getSelectedRoute();
        setMediaSession(r32.getMediaSessionToken());
        this.mVolumeGroupListPaddingTop = this.mContext.getResources().getDimensionPixelSize(R.dimen.mr_controller_volume_group_list_padding_top);
        this.mAccessibilityManager = (AccessibilityManager) this.mContext.getSystemService("accessibility");
        this.mLinearOutSlowInInterpolator = AnimationUtils.loadInterpolator(r22, R.interpolator.mr_linear_out_slow_in);
        this.mFastOutSlowInInterpolator = AnimationUtils.loadInterpolator(r22, R.interpolator.mr_fast_out_slow_in);
    }

    private void animateGroupListItems(Map<MediaRouter.RouteInfo, Rect> r3, Map<MediaRouter.RouteInfo, BitmapDrawable> r4) {
        this.mVolumeGroupList.setEnabled(false);
        this.mVolumeGroupList.requestLayout();
        this.mIsGroupListAnimating = true;
        this.mVolumeGroupList.getViewTreeObserver().addOnGlobalLayoutListener(new AnonymousClass8(this, r3, r4));
    }

    private void animateLayoutHeight(View r5, int r6) {
        AnonymousClass7 r1 = new AnonymousClass7(this, getLayoutHeight(r5), r6, r5);
        r1.setDuration(this.mGroupListAnimationDurationMs);
        r1.setInterpolator(this.mInterpolator);
        r5.startAnimation(r1);
    }

    private boolean canShowPlaybackControlLayout() {
        if (this.mCustomControlView == null) goto L5;
        return false;
    L5:
        if (this.mDescription == null) goto L7;
        return true;
    L7:
        if (this.mState == null) goto L13;
        return true;
    L13:
        return false;
    }

    private void fadeInAddedRoutes() {
        AnonymousClass12 r0 = new AnonymousClass12(this);
        int r1 = this.mVolumeGroupList.getFirstVisiblePosition();
        int r2 = 0;
        boolean r3 = false;
    L4:
        if (r2 >= this.mVolumeGroupList.getChildCount()) goto L12;
        View r4 = this.mVolumeGroupList.getChildAt(r2);
        if (this.mGroupMemberRoutesAdded.contains((MediaRouter.RouteInfo) this.mVolumeGroupAdapter.getItem(r1 + r2)) == false) goto L11;
        AlphaAnimation r5 = new AlphaAnimation(0.0f, 1.0f);
        r5.setDuration(this.mGroupListFadeInDurationMs);
        r5.setFillEnabled(true);
        r5.setFillAfter(true);
        if (r3 == true) goto L10;
        r5.setAnimationListener(r0);
        r3 = true;
    L10:
        r4.clearAnimation();
        r4.startAnimation(r5);
    L11:
        r2 = r2 + 1;
        goto L4
    }

    private static int getLayoutHeight(View r0) {
        return r0.getLayoutParams().height;
    }

    private int getMainControllerHeight(boolean r3) {
        if (r3 == false) goto L4;
    L8:
        int r0 = this.mMediaMainControlLayout.getPaddingTop() + this.mMediaMainControlLayout.getPaddingBottom();
        if (r3 == false) goto L12;
        r0 = r0 + this.mPlaybackControlLayout.getMeasuredHeight();
    L12:
        if (this.mVolumeControlLayout.getVisibility() != 0) goto L14;
        r0 = r0 + this.mVolumeControlLayout.getMeasuredHeight();
    L14:
        if (r3 == true) goto L16;
        return r0;
    L16:
        if (this.mVolumeControlLayout.getVisibility() == 0) goto L18;
        return r0;
    L18:
        return r0 + this.mDividerView.getMeasuredHeight();
    L4:
        if (this.mVolumeControlLayout.getVisibility() == 0) goto L8;
        return 0;
    }

    public static boolean isBitmapRecycled(Bitmap r0) {
        if (r0 != null) goto L4;
        return false;
    L4:
        if (r0.isRecycled() == false) goto L9;
        return true;
    L9:
        return false;
    }

    private boolean isGroup() {
        if (this.mRoute.isGroup() == true) goto L5;
        return false;
    L5:
        if (this.mRoute.getSelectedRoutesInGroup().size() <= 1) goto L9;
        return true;
    L9:
        return false;
    }

    private boolean isIconChanged() {
        MediaDescriptionCompat r0 = this.mDescription;
        Uri r1 = null;
        if (r0 != null) goto L5;
        Bitmap r02 = null;
    L6:
        MediaDescriptionCompat r2 = this.mDescription;
        if (r2 == null) goto L10;
        r1 = r2.getIconUri();
    L10:
        FetchArtTask r22 = this.mFetchArtTask;
        if (r22 != null) goto L13;
        Bitmap r23 = this.mArtIconBitmap;
    L14:
        FetchArtTask r3 = this.mFetchArtTask;
        if (r3 != null) goto L17;
        Uri r4 = this.mArtIconUri;
    L19:
        if (r23 == r02) goto L21;
        return true;
    L21:
        if (r23 == null) goto L23;
        return false;
    L23:
        if (uriEquals(r4, r1) == true) goto L27;
        return true;
    L27:
        return false;
    L17:
        r4 = r3.getIconUri();
        goto L19
    L13:
        r23 = r22.getIconBitmap();
        goto L14
    L5:
        r02 = r0.getIconBitmap();
        goto L6
    }

    private void rebuildVolumeGroupList(boolean r7) {
        List<MediaRouter.RouteInfo> r0 = this.mRoute.getSelectedRoutesInGroup();
        boolean r1 = r0.isEmpty();
        List<MediaRouter.RouteInfo> r2 = this.mGroupMemberRoutes;
        if (r1 == false) goto L7;
        r2.clear();
        this.mVolumeGroupAdapter.notifyDataSetChanged();
        return;
    L7:
        if (MediaRouteDialogHelper.listUnorderedEquals(r2, r0) == false) goto L11;
        this.mVolumeGroupAdapter.notifyDataSetChanged();
        return;
    L11:
        if (r7 == false) goto L13;
        HashMap r22 = MediaRouteDialogHelper.getItemBoundMap(this.mVolumeGroupList, this.mVolumeGroupAdapter);
    L14:
        if (r7 == false) goto L16;
        HashMap r3 = MediaRouteDialogHelper.getItemBitmapMap(this.mContext, this.mVolumeGroupList, this.mVolumeGroupAdapter);
    L17:
        this.mGroupMemberRoutesAdded = MediaRouteDialogHelper.getItemsAdded(this.mGroupMemberRoutes, r0);
        this.mGroupMemberRoutesRemoved = MediaRouteDialogHelper.getItemsRemoved(this.mGroupMemberRoutes, r0);
        this.mGroupMemberRoutes.addAll(0, this.mGroupMemberRoutesAdded);
        this.mGroupMemberRoutes.removeAll(this.mGroupMemberRoutesRemoved);
        this.mVolumeGroupAdapter.notifyDataSetChanged();
        if (r7 == true) goto L20;
    L25:
        this.mGroupMemberRoutesAdded = null;
        this.mGroupMemberRoutesRemoved = null;
        return;
    L20:
        if (this.mIsGroupExpanded == false) goto L25;
        if ((this.mGroupMemberRoutesAdded.size() + this.mGroupMemberRoutesRemoved.size()) <= 0) goto L25;
        animateGroupListItems(r22, r3);
        return;
    L16:
        r3 = null;
        goto L17
    L13:
        r22 = null;
        goto L14
    }

    public static void setLayoutHeight(View r1, int r2) {
        ViewGroup.LayoutParams r0 = r1.getLayoutParams();
        r0.height = r2;
        r1.setLayoutParams(r0);
    }

    private void setMediaSession(MediaSessionCompat.Token r4) {
        MediaControllerCompat r0 = this.mMediaController;
        MediaDescriptionCompat r1 = null;
        if (r0 == null) goto L5;
        r0.unregisterCallback(this.mControllerCallback);
        this.mMediaController = null;
    L5:
        if (r4 != null) goto L8;
        return;
    L8:
        if (this.mAttachedToWindow == true) goto L10;
        return;
    L10:
        MediaControllerCompat r02 = new MediaControllerCompat(this.mContext, r4);
        this.mMediaController = r02;
        r02.registerCallback(this.mControllerCallback);
        MediaMetadataCompat r42 = this.mMediaController.getMetadata();
        if (r42 == null) goto L14;
        r1 = r42.getDescription();
    L14:
        this.mDescription = r1;
        this.mState = this.mMediaController.getPlaybackState();
        updateArtIconIfNeeded();
        update(false);
    }

    private void updateMediaControlVisibility(boolean r5) {
        View r0 = this.mDividerView;
        int r2 = 0;
        if (this.mVolumeControlLayout.getVisibility() != 0) goto L6;
        if (r5 == false) goto L6;
        int r1 = 0;
    L7:
        r0.setVisibility(r1);
        LinearLayout r02 = this.mMediaMainControlLayout;
        if (this.mVolumeControlLayout.getVisibility() != 8) goto L11;
        if (r5 == true) goto L11;
        r2 = 8;
    L11:
        r02.setVisibility(r2);
        return;
    L6:
        r1 = 8;
        goto L7
    }

    private void updatePlaybackControlLayout() {
        if (canShowPlaybackControlLayout() == false) goto L70;
        MediaDescriptionCompat r0 = this.mDescription;
        CharSequence r1 = null;
        if (r0 != null) goto L7;
        CharSequence r02 = null;
    L8:
        boolean r2 = TextUtils.isEmpty(r02);
        MediaDescriptionCompat r3 = this.mDescription;
        if (r3 == null) goto L12;
        r1 = r3.getSubtitle();
    L12:
        boolean r32 = TextUtils.isEmpty(r1);
        boolean r6 = true;
        int r7 = 0;
        if (this.mRoute.getPresentationDisplayId() == (-1)) goto L17;
        this.mTitleView.setText(R.string.mr_controller_casting_screen);
    L15:
        boolean r03 = true;
    L16:
        boolean r12 = false;
    L31:
        TextView r22 = this.mTitleView;
        if (r03 == false) goto L34;
        int r04 = 0;
    L35:
        r22.setVisibility(r04);
        TextView r05 = this.mSubtitleView;
        if (r12 == false) goto L38;
        int r13 = 0;
    L39:
        r05.setVisibility(r13);
        PlaybackStateCompat r06 = this.mState;
        if (r06 != null) goto L42;
        return;
    L42:
        if (r06.getState() != 6) goto L44;
    L47:
        boolean r07 = true;
    L48:
        Context r14 = this.mPlaybackControlButton.getContext();
        if (r07 == true) goto L51;
    L53:
        if (r07 == true) goto L55;
    L57:
        if (r07 == false) goto L59;
    L61:
        int r08 = 0;
        int r23 = 0;
        r6 = false;
    L62:
        ImageButton r4 = this.mPlaybackControlButton;
        if (r6 == true) goto L66;
        r7 = 8;
    L66:
        r4.setVisibility(r7);
        if (r6 == false) goto L72;
        this.mPlaybackControlButton.setImageResource(MediaRouterThemeHelper.getThemeResource(r14, r08));
        this.mPlaybackControlButton.setContentDescription(r14.getResources().getText(r23));
        return;
    L72:
        return;
    L59:
        if (isPlayActionSupported() == false) goto L61;
        r08 = R.attr.mediaRoutePlayDrawable;
        r23 = R.string.mr_controller_play;
        goto L62
    L55:
        if (isStopActionSupported() == false) goto L57;
        r08 = R.attr.mediaRouteStopDrawable;
        r23 = R.string.mr_controller_stop;
        goto L62
    L51:
        if (isPauseActionSupported() == false) goto L53;
        r08 = R.attr.mediaRoutePauseDrawable;
        r23 = R.string.mr_controller_pause;
        goto L62
    L44:
        if (this.mState.getState() == 3) goto L47;
        r07 = false;
        goto L48
    L38:
        r13 = 8;
        goto L39
    L34:
        r04 = 8;
        goto L35
    L17:
        PlaybackStateCompat r42 = this.mState;
        if (r42 != null) goto L20;
    L30:
        this.mTitleView.setText(R.string.mr_controller_no_media_selected);
        goto L15
    L20:
        if (r42.getState() == 0) goto L30;
        if (r2 == false) goto L25;
        if (r32 == false) goto L25;
        this.mTitleView.setText(R.string.mr_controller_no_info_available);
    L25:
        if (r2 == true) goto L27;
        this.mTitleView.setText(r02);
        r03 = true;
    L28:
        if (r32 == true) goto L16;
        this.mSubtitleView.setText(r1);
        r12 = true;
        goto L31
    L27:
        r03 = false;
        goto L28
    L7:
        r02 = r0.getTitle();
        goto L8
    }

    private void updateVolumeControlLayout() {
        int r1 = 0;
        if (this.mEnableGroupVolumeUX == true) goto L9;
        if (isGroup() == false) goto L9;
        this.mVolumeControlLayout.setVisibility(8);
        this.mIsGroupExpanded = true;
        this.mVolumeGroupList.setVisibility(0);
        loadInterpolator();
        updateLayoutHeight(false);
        return;
    L9:
        if (this.mIsGroupExpanded == false) goto L13;
        if (this.mEnableGroupVolumeUX == true) goto L13;
    L14:
        this.mVolumeControlLayout.setVisibility(8);
        return;
    L13:
        if (isVolumeControlAvailable(this.mRoute) == false) goto L14;
        if (this.mVolumeControlLayout.getVisibility() != 8) goto L24;
        this.mVolumeControlLayout.setVisibility(0);
        this.mVolumeSlider.setMax(this.mRoute.getVolumeMax());
        this.mVolumeSlider.setProgress(this.mRoute.getVolume());
        MediaRouteExpandCollapseButton r0 = this.mGroupExpandCollapseButton;
        if (isGroup() == true) goto L22;
        r1 = 8;
    L22:
        r0.setVisibility(r1);
        return;
    }

    private static boolean uriEquals(Uri r2, Uri r3) {
        if (r2 != null) goto L5;
    L7:
        if (r2 != null) goto L10;
        if (r3 != null) goto L12;
        return true;
    L12:
        return false;
    L10:
        return false;
    L5:
        if (r2.equals(r3) == false) goto L7;
        return true;
    }

    public void animateGroupListItemsInternal(Map<MediaRouter.RouteInfo, Rect> r17, Map<MediaRouter.RouteInfo, BitmapDrawable> r18) {
        Set<MediaRouter.RouteInfo> r2 = this.mGroupMemberRoutesAdded;
        if (r2 != null) goto L5;
        return;
    L5:
        if (this.mGroupMemberRoutesRemoved == null) goto L38;
        int r22 = r2.size() - this.mGroupMemberRoutesRemoved.size();
        AnonymousClass9 r3 = new AnonymousClass9(this);
        int r4 = this.mVolumeGroupList.getFirstVisiblePosition();
        int r5 = 0;
        boolean r6 = false;
    L9:
        if (r5 >= this.mVolumeGroupList.getChildCount()) goto L23;
        View r7 = this.mVolumeGroupList.getChildAt(r5);
        MediaRouter.RouteInfo r9 = (MediaRouter.RouteInfo) this.mVolumeGroupAdapter.getItem(r4 + r5);
        Rect r10 = r17.get(r9);
        int r11 = r7.getTop();
        if (r10 == null) goto L13;
        int r102 = r10.top;
    L14:
        AnimationSet r12 = new AnimationSet(true);
        Set<MediaRouter.RouteInfo> r14 = this.mGroupMemberRoutesAdded;
        if (r14 != null) goto L17;
    L19:
        TranslateAnimation r142 = new TranslateAnimation(0.0f, 0.0f, r102 - r11, 0.0f);
        r142.setDuration(this.mGroupListAnimationDurationMs);
        r12.addAnimation(r142);
        r12.setFillAfter(true);
        r12.setFillEnabled(true);
        r12.setInterpolator(this.mInterpolator);
        if (r6 == true) goto L22;
        r12.setAnimationListener(r3);
        r6 = true;
    L22:
        r7.clearAnimation();
        r7.startAnimation(r12);
        r17.remove(r9);
        r18.remove(r9);
        r5 = r5 + 1;
        goto L9
    L17:
        if (r14.contains(r9) == false) goto L19;
        AlphaAnimation r103 = new AlphaAnimation(0.0f, 0.0f);
        r103.setDuration(this.mGroupListFadeInDurationMs);
        r12.addAnimation(r103);
        r102 = r11;
        goto L19
    L13:
        r102 = (this.mVolumeGroupListItemHeight * r22) + r11;
        goto L14
    L23:
        Iterator<Map.Entry<MediaRouter.RouteInfo, BitmapDrawable>> r32 = r18.entrySet().iterator();
    L25:
        if (r32.hasNext() == false) goto L39;
        Map.Entry<MediaRouter.RouteInfo, BitmapDrawable> r42 = r32.next();
        MediaRouter.RouteInfo r52 = r42.getKey();
        BitmapDrawable r43 = r42.getValue();
        Rect r62 = r17.get(r52);
        if (this.mGroupMemberRoutesRemoved.contains(r52) == false) goto L29;
        OverlayListView.OverlayObject r44 = new OverlayListView.OverlayObject(r43, r62).setAlphaAnimation(1.0f, 0.0f).setDuration(this.mGroupListFadeOutDurationMs).setInterpolator(this.mInterpolator);
    L30:
        this.mVolumeGroupList.addOverlayObject(r44);
        goto L25
    L29:
        r44 = new OverlayListView.OverlayObject(r43, r62).setTranslateYAnimation(this.mVolumeGroupListItemHeight * r22).setDuration(this.mGroupListAnimationDurationMs).setInterpolator(this.mInterpolator).setAnimationEndListener(new AnonymousClass10(this, r52));
        this.mGroupMemberRoutesAnimatingWithBitmap.add(r52);
        goto L30
    L39:
        return;
    }

    public void clearGroupListAnimation(boolean r11) {
        int r0 = this.mVolumeGroupList.getFirstVisiblePosition();
        int r2 = 0;
    L3:
        int r3 = this.mVolumeGroupList.getChildCount();
        OverlayListView r4 = this.mVolumeGroupList;
        if (r2 >= r3) goto L14;
        View r32 = r4.getChildAt(r2);
        MediaRouter.RouteInfo r42 = (MediaRouter.RouteInfo) this.mVolumeGroupAdapter.getItem(r0 + r2);
        if (r11 == false) goto L12;
        Set<MediaRouter.RouteInfo> r5 = this.mGroupMemberRoutesAdded;
        if (r5 == null) goto L12;
        if (r5.contains(r42) == false) goto L12;
    L13:
        r2 = r2 + 1;
    L12:
        ((LinearLayout) r32.findViewById(R.id.volume_item_container)).setVisibility(0);
        AnimationSet r43 = new AnimationSet(true);
        AlphaAnimation r6 = new AlphaAnimation(1.0f, 1.0f);
        r6.setDuration(0);
        r43.addAnimation(r6);
        new TranslateAnimation(0.0f, 0.0f, 0.0f, 0.0f).setDuration(0);
        r43.setFillAfter(true);
        r43.setFillEnabled(true);
        r32.clearAnimation();
        r32.startAnimation(r43);
        goto L13
    L14:
        r4.stopAnimationAll();
        if (r11 == true) goto L21;
        finishAnimation(false);
        return;
    }

    public void clearLoadedBitmap() {
        this.mArtIconIsLoaded = false;
        this.mArtIconLoadedBitmap = null;
        this.mArtIconBackgroundColor = 0;
    }

    public void finishAnimation(boolean r3) {
        this.mGroupMemberRoutesAdded = null;
        this.mGroupMemberRoutesRemoved = null;
        this.mIsGroupListAnimating = false;
        if (this.mIsGroupListAnimationPending == false) goto L5;
        this.mIsGroupListAnimationPending = false;
        updateLayoutHeight(r3);
    L5:
        this.mVolumeGroupList.setEnabled(true);
    }

    public int getDesiredArtHeight(int r2, int r3) {
        int r1 = this.mDialogContentWidth;
        if (r2 < r3) goto L7;
        float r12 = r1 * r3;
        float r22 = r2;
    L6:
        return (int) ((r12 / r22) + 0.5f);
    L7:
        r12 = r1 * 9.0f;
        r22 = 16.0f;
        goto L6
    }

    public View getMediaControlView() {
        return this.mCustomControlView;
    }

    public MediaSessionCompat.Token getMediaSession() {
        MediaControllerCompat r0 = this.mMediaController;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.getSessionToken();
    }

    public MediaRouter.RouteInfo getRoute() {
        return this.mRoute;
    }

    public boolean isPauseActionSupported() {
        if ((this.mState.getActions() & 514) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isPlayActionSupported() {
        if ((this.mState.getActions() & 516) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isStopActionSupported() {
        if ((this.mState.getActions() & 1) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isVolumeControlAvailable(MediaRouter.RouteInfo r1) {
        if (this.mVolumeControlEnabled == true) goto L5;
        return false;
    L5:
        if (r1.getVolumeHandling() != 1) goto L9;
        return true;
    L9:
        return false;
    }

    public boolean isVolumeControlEnabled() {
        return this.mVolumeControlEnabled;
    }

    public void loadInterpolator() {
        if (this.mIsGroupExpanded == false) goto L5;
        Interpolator r0 = this.mLinearOutSlowInInterpolator;
    L6:
        this.mInterpolator = r0;
        return;
    L5:
        r0 = this.mFastOutSlowInInterpolator;
        goto L6
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAttachedToWindow = true;
        this.mRouter.addCallback(MediaRouteSelector.EMPTY, this.mCallback, 2);
        setMediaSession(this.mRouter.getMediaSessionToken());
    }

    @Override // androidx.appcompat.app.AlertDialog, androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void onCreate(Bundle r6) {
        super.onCreate(r6);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setContentView(R.layout.mr_controller_material_dialog_b);
        findViewById(16908315).setVisibility(8);
        ClickListener r0 = new ClickListener(this);
        FrameLayout r2 = (FrameLayout) findViewById(R.id.mr_expandable_area);
        this.mExpandableAreaLayout = r2;
        r2.setOnClickListener(new AnonymousClass2(this));
        LinearLayout r22 = (LinearLayout) findViewById(R.id.mr_dialog_area);
        this.mDialogAreaLayout = r22;
        r22.setOnClickListener(new AnonymousClass3(this));
        int r23 = MediaRouterThemeHelper.getButtonTextColor(this.mContext);
        Button r3 = (Button) findViewById(16908314);
        this.mDisconnectButton = r3;
        r3.setText(R.string.mr_controller_disconnect);
        this.mDisconnectButton.setTextColor(r23);
        this.mDisconnectButton.setOnClickListener(r0);
        Button r32 = (Button) findViewById(16908313);
        this.mStopCastingButton = r32;
        r32.setText(R.string.mr_controller_stop_casting);
        this.mStopCastingButton.setTextColor(r23);
        this.mStopCastingButton.setOnClickListener(r0);
        this.mRouteNameTextView = (TextView) findViewById(R.id.mr_name);
        ImageButton r24 = (ImageButton) findViewById(R.id.mr_close);
        this.mCloseButton = r24;
        r24.setOnClickListener(r0);
        this.mCustomControlLayout = (FrameLayout) findViewById(R.id.mr_custom_control);
        this.mDefaultControlLayout = (FrameLayout) findViewById(R.id.mr_default_control);
        AnonymousClass4 r25 = new AnonymousClass4(this);
        ImageView r33 = (ImageView) findViewById(R.id.mr_art);
        this.mArtView = r33;
        r33.setOnClickListener(r25);
        findViewById(R.id.mr_control_title_container).setOnClickListener(r25);
        this.mMediaMainControlLayout = (LinearLayout) findViewById(R.id.mr_media_main_control);
        this.mDividerView = findViewById(R.id.mr_control_divider);
        this.mPlaybackControlLayout = (RelativeLayout) findViewById(R.id.mr_playback_control);
        this.mTitleView = (TextView) findViewById(R.id.mr_control_title);
        this.mSubtitleView = (TextView) findViewById(R.id.mr_control_subtitle);
        ImageButton r26 = (ImageButton) findViewById(R.id.mr_control_playback_ctrl);
        this.mPlaybackControlButton = r26;
        r26.setOnClickListener(r0);
        LinearLayout r02 = (LinearLayout) findViewById(R.id.mr_volume_control);
        this.mVolumeControlLayout = r02;
        r02.setVisibility(8);
        SeekBar r03 = (SeekBar) findViewById(R.id.mr_volume_slider);
        this.mVolumeSlider = r03;
        r03.setTag(this.mRoute);
        VolumeChangeListener r04 = new VolumeChangeListener(this);
        this.mVolumeChangeListener = r04;
        this.mVolumeSlider.setOnSeekBarChangeListener(r04);
        this.mVolumeGroupList = (OverlayListView) findViewById(R.id.mr_volume_group_list);
        this.mGroupMemberRoutes = new ArrayList();
        VolumeGroupAdapter r05 = new VolumeGroupAdapter(this, this.mVolumeGroupList.getContext(), this.mGroupMemberRoutes);
        this.mVolumeGroupAdapter = r05;
        this.mVolumeGroupList.setAdapter(r05);
        this.mGroupMemberRoutesAnimatingWithBitmap = new HashSet();
        MediaRouterThemeHelper.setMediaControlsBackgroundColor(this.mContext, this.mMediaMainControlLayout, this.mVolumeGroupList, isGroup());
        MediaRouterThemeHelper.setVolumeSliderColor(this.mContext, (MediaRouteVolumeSlider) this.mVolumeSlider, this.mMediaMainControlLayout);
        HashMap r06 = new HashMap();
        this.mVolumeSliderMap = r06;
        r06.put(this.mRoute, this.mVolumeSlider);
        MediaRouteExpandCollapseButton r07 = (MediaRouteExpandCollapseButton) findViewById(R.id.mr_group_expand_collapse);
        this.mGroupExpandCollapseButton = r07;
        r07.setOnClickListener(new AnonymousClass5(this));
        loadInterpolator();
        this.mGroupListAnimationDurationMs = this.mContext.getResources().getInteger(R.integer.mr_controller_volume_group_list_animation_duration_ms);
        this.mGroupListFadeInDurationMs = this.mContext.getResources().getInteger(R.integer.mr_controller_volume_group_list_fade_in_duration_ms);
        this.mGroupListFadeOutDurationMs = this.mContext.getResources().getInteger(R.integer.mr_controller_volume_group_list_fade_out_duration_ms);
        View r62 = onCreateMediaControlView(r6);
        this.mCustomControlView = r62;
        if (r62 == null) goto L5;
        this.mCustomControlLayout.addView(r62);
        this.mCustomControlLayout.setVisibility(0);
    L5:
        this.mCreated = true;
        updateLayout();
    }

    public View onCreateMediaControlView(Bundle r1) {
        return null;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        this.mRouter.removeCallback(this.mCallback);
        setMediaSession(null);
        this.mAttachedToWindow = false;
        super.onDetachedFromWindow();
    }

    @Override // androidx.appcompat.app.AlertDialog, android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int r3, KeyEvent r4) {
        if (r3 == 25) goto L10;
        if (r3 == 24) goto L10;
        return super.onKeyDown(r3, r4);
    L10:
        if (this.mEnableGroupVolumeUX == false) goto L12;
    L13:
        MediaRouter.RouteInfo r2 = this.mRoute;
        if (r3 != 25) goto L16;
        int r32 = -1;
    L17:
        r2.requestUpdateVolume(r32);
    L18:
        return true;
    L16:
        r32 = 1;
        goto L17
    L12:
        if (this.mIsGroupExpanded == true) goto L18;
        goto L13
    }

    @Override // androidx.appcompat.app.AlertDialog, android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int r2, KeyEvent r3) {
        if (r2 != 25) goto L5;
        return true;
    L5:
        if (r2 != 24) goto L8;
        return true;
    L8:
        return super.onKeyUp(r2, r3);
    }

    public void setVolumeControlEnabled(boolean r2) {
        if (this.mVolumeControlEnabled == r2) goto L8;
        this.mVolumeControlEnabled = r2;
        if (this.mCreated == false) goto L9;
        update(false);
        return;
    L9:
        return;
    }

    public void startGroupListFadeInAnimation() {
        clearGroupListAnimation(true);
        this.mVolumeGroupList.requestLayout();
        this.mVolumeGroupList.getViewTreeObserver().addOnGlobalLayoutListener(new AnonymousClass11(this));
    }

    public void startGroupListFadeInAnimationInternal() {
        Set<MediaRouter.RouteInfo> r0 = this.mGroupMemberRoutesAdded;
        if (r0 != null) goto L5;
    L8:
        finishAnimation(true);
        return;
    L5:
        if (r0.size() == 0) goto L8;
        fadeInAddedRoutes();
    }

    public void update(boolean r4) {
        if (this.mRouteInVolumeSliderTouched == null) goto L6;
        this.mHasPendingUpdate = true;
        this.mPendingUpdateAnimationNeeded = r4 | this.mPendingUpdateAnimationNeeded;
        return;
    L6:
        int r0 = 0;
        this.mHasPendingUpdate = false;
        this.mPendingUpdateAnimationNeeded = false;
        if (this.mRoute.isSelected() == true) goto L9;
    L29:
        dismiss();
        return;
    L9:
        if (this.mRoute.isDefaultOrBluetooth() == true) goto L29;
        if (this.mCreated == true) goto L14;
        return;
    L14:
        this.mRouteNameTextView.setText(this.mRoute.getName());
        Button r1 = this.mDisconnectButton;
        if (this.mRoute.canDisconnect() == true) goto L18;
        r0 = 8;
    L18:
        r1.setVisibility(r0);
        if (this.mCustomControlView == null) goto L21;
    L27:
        updateVolumeControlLayout();
        updatePlaybackControlLayout();
        updateLayoutHeight(r4);
        return;
    L21:
        if (this.mArtIconIsLoaded == false) goto L27;
        if (isBitmapRecycled(this.mArtIconLoadedBitmap) == false) goto L25;
        Log.w("MediaRouteCtrlDialog", "Can't set artwork image with recycled bitmap: " + this.mArtIconLoadedBitmap);
    L26:
        clearLoadedBitmap();
        goto L27
    L25:
        this.mArtView.setImageBitmap(this.mArtIconLoadedBitmap);
        this.mArtView.setBackgroundColor(this.mArtIconBackgroundColor);
        goto L26
    }

    public void updateArtIconIfNeeded() {
        if (this.mCustomControlView == null) goto L5;
        return;
    L5:
        if (isIconChanged() == true) goto L7;
        return;
    L7:
        if (isGroup() == true) goto L9;
    L11:
        FetchArtTask r0 = this.mFetchArtTask;
        if (r0 == null) goto L14;
        r0.cancel(true);
    L14:
        FetchArtTask r02 = new FetchArtTask(this);
        this.mFetchArtTask = r02;
        r02.execute(new Void[0]);
        return;
    L9:
        if (this.mEnableGroupVolumeUX == true) goto L11;
    }

    public void updateLayout() {
        int r0 = MediaRouteDialogHelper.getDialogWidth(this.mContext);
        getWindow().setLayout(r0, -2);
        View r1 = getWindow().getDecorView();
        this.mDialogContentWidth = (r0 - r1.getPaddingLeft()) - r1.getPaddingRight();
        Resources r02 = this.mContext.getResources();
        this.mVolumeGroupListItemIconSize = r02.getDimensionPixelSize(R.dimen.mr_controller_volume_group_list_item_icon_size);
        this.mVolumeGroupListItemHeight = r02.getDimensionPixelSize(R.dimen.mr_controller_volume_group_list_item_height);
        this.mVolumeGroupListMaxHeight = r02.getDimensionPixelSize(R.dimen.mr_controller_volume_group_list_max_height);
        this.mArtIconBitmap = null;
        this.mArtIconUri = null;
        updateArtIconIfNeeded();
        update(false);
    }

    public void updateLayoutHeight(boolean r3) {
        this.mDefaultControlLayout.requestLayout();
        this.mDefaultControlLayout.getViewTreeObserver().addOnGlobalLayoutListener(new AnonymousClass6(this, r3));
    }

    public void updateLayoutHeightInternal(boolean r10) {
        int r0 = getLayoutHeight(this.mMediaMainControlLayout);
        setLayoutHeight(this.mMediaMainControlLayout, -1);
        updateMediaControlVisibility(canShowPlaybackControlLayout());
        View r1 = getWindow().getDecorView();
        boolean r3 = false;
        r1.measure(View.MeasureSpec.makeMeasureSpec(getWindow().getAttributes().width, TLObject.FLAG_30), 0);
        setLayoutHeight(this.mMediaMainControlLayout, r0);
        if (this.mCustomControlView == null) goto L5;
    L13:
        int r2 = 0;
    L14:
        int r02 = getMainControllerHeight(canShowPlaybackControlLayout());
        int r4 = this.mGroupMemberRoutes.size();
        if (isGroup() == false) goto L17;
        int r5 = this.mVolumeGroupListItemHeight * this.mRoute.getSelectedRoutesInGroup().size();
    L18:
        if (r4 <= 0) goto L20;
        r5 = r5 + this.mVolumeGroupListPaddingTop;
    L20:
        int r42 = Math.min(r5, this.mVolumeGroupListMaxHeight);
        if (this.mIsGroupExpanded == true) goto L24;
        r42 = 0;
    L24:
        int r52 = Math.max(r2, r42) + r02;
        Rect r6 = new Rect();
        r1.getWindowVisibleDisplayFrame(r6);
        int r7 = r6.height() - (this.mDialogAreaLayout.getMeasuredHeight() - this.mDefaultControlLayout.getMeasuredHeight());
        if (this.mCustomControlView != null) goto L30;
        if (r2 <= 0) goto L30;
        if (r52 > r7) goto L30;
        this.mArtView.setVisibility(0);
        setLayoutHeight(this.mArtView, r2);
    L34:
        if (canShowPlaybackControlLayout() == false) goto L37;
        if (r52 > r7) goto L37;
        this.mPlaybackControlLayout.setVisibility(0);
    L39:
        if (this.mPlaybackControlLayout.getVisibility() != 0) goto L41;
        boolean r03 = true;
    L42:
        updateMediaControlVisibility(r03);
        if (this.mPlaybackControlLayout.getVisibility() != 0) goto L45;
        r3 = true;
    L45:
        int r04 = getMainControllerHeight(r3);
        int r12 = Math.max(r2, r42) + r04;
        if (r12 <= r7) goto L48;
        r42 = r42 - (r12 - r7);
    L49:
        this.mMediaMainControlLayout.clearAnimation();
        this.mVolumeGroupList.clearAnimation();
        this.mDefaultControlLayout.clearAnimation();
        LinearLayout r13 = this.mMediaMainControlLayout;
        if (r10 == false) goto L52;
        animateLayoutHeight(r13, r04);
        animateLayoutHeight(this.mVolumeGroupList, r42);
        animateLayoutHeight(this.mDefaultControlLayout, r7);
    L53:
        setLayoutHeight(this.mExpandableAreaLayout, r6.height());
        rebuildVolumeGroupList(r10);
        return;
    L52:
        setLayoutHeight(r13, r04);
        setLayoutHeight(this.mVolumeGroupList, r42);
        setLayoutHeight(this.mDefaultControlLayout, r7);
        goto L53
    L48:
        r7 = r12;
        goto L49
    L41:
        r03 = false;
    L37:
        this.mPlaybackControlLayout.setVisibility(8);
    L30:
        if ((getLayoutHeight(this.mVolumeGroupList) + this.mMediaMainControlLayout.getMeasuredHeight()) < this.mDefaultControlLayout.getMeasuredHeight()) goto L32;
        this.mArtView.setVisibility(8);
    L32:
        r52 = r42 + r02;
        r2 = 0;
        goto L34
    L17:
        r5 = 0;
        goto L18
    L5:
        if ((this.mArtView.getDrawable() instanceof BitmapDrawable) == false) goto L13;
        Bitmap r05 = ((BitmapDrawable) this.mArtView.getDrawable()).getBitmap();
        if (r05 == null) goto L13;
        r2 = getDesiredArtHeight(r05.getWidth(), r05.getHeight());
        ImageView r43 = this.mArtView;
        if (r05.getWidth() < r05.getHeight()) goto L11;
        ImageView.ScaleType r06 = ImageView.ScaleType.FIT_XY;
    L12:
        r43.setScaleType(r06);
        goto L14
    L11:
        r06 = ImageView.ScaleType.FIT_CENTER;
        goto L12
    }

    public void updateVolumeGroupItemHeight(View r3) {
        setLayoutHeight((LinearLayout) r3.findViewById(R.id.volume_item_container), this.mVolumeGroupListItemHeight);
        View r32 = r3.findViewById(R.id.mr_volume_item_icon);
        ViewGroup.LayoutParams r0 = r32.getLayoutParams();
        int r2 = this.mVolumeGroupListItemIconSize;
        r0.width = r2;
        r0.height = r2;
        r32.setLayoutParams(r0);
    }
}
