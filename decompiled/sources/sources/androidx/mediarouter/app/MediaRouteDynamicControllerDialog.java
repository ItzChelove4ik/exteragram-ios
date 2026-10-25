package androidx.mediarouter.app;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.util.ObjectsCompat;
import androidx.mediarouter.R;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import c.f$$ExternalSyntheticBUOutline1;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public class MediaRouteDynamicControllerDialog extends AppCompatDialog {
    private static final int BLUR_RADIUS = 10;
    private static final int COLOR_WHITE_ON_DARK_BACKGROUND = -1;
    private static final int CONNECTION_TIMEOUT_MS = 30000;
    static final boolean DEBUG = false;
    private static final int MIN_UNMUTED_VOLUME = 1;
    private static final int MSG_UPDATE_ROUTES_VIEW = 1;
    private static final int MSG_UPDATE_ROUTE_VOLUME_BY_USER = 2;
    private static final int MUTED_VOLUME = 0;
    private static final String TAG = "MediaRouteCtrlDialog";
    private static final int UPDATE_ROUTES_VIEW_DELAY_MS = 300;
    private static final int UPDATE_VOLUME_DELAY_MS = 500;
    RecyclerAdapter mAdapter;
    int mArtIconBackgroundColor;
    Bitmap mArtIconBitmap;
    boolean mArtIconIsLoaded;
    Bitmap mArtIconLoadedBitmap;
    Uri mArtIconUri;
    ImageView mArtView;
    private boolean mAttachedToWindow;
    private final MediaRouterCallback mCallback;
    private ImageButton mCloseButton;
    Context mContext;
    MediaControllerCallback mControllerCallback;
    private boolean mCreated;
    MediaDescriptionCompat mDescription;
    final boolean mEnableGroupVolumeUX;
    FetchArtTask mFetchArtTask;
    final List<MediaRouter.RouteInfo> mGroupableRoutes;
    final Handler mHandler;
    boolean mIsAnimatingVolumeSliderLayout;
    boolean mIsSelectingRoute;
    private long mLastUpdateTime;
    MediaControllerCompat mMediaController;
    final List<MediaRouter.RouteInfo> mMemberRoutes;
    private ImageView mMetadataBackground;
    private View mMetadataBlackScrim;
    RecyclerView mRecyclerView;
    MediaRouter.RouteInfo mRouteForVolumeUpdatingByUser;
    final MediaRouter mRouter;
    MediaRouter.RouteInfo mSelectedRoute;
    private MediaRouteSelector mSelector;
    private Button mStopCastingButton;
    private TextView mSubtitleView;
    private String mTitlePlaceholder;
    private TextView mTitleView;
    final List<MediaRouter.RouteInfo> mTransferableRoutes;
    final List<MediaRouter.RouteInfo> mUngroupableRoutes;
    Map<String, Integer> mUnmutedVolumeMap;
    private boolean mUpdateMetadataViewsDeferred;
    private boolean mUpdateRoutesViewDeferred;
    VolumeChangeListener mVolumeChangeListener;
    Map<String, MediaRouteVolumeSliderHolder> mVolumeSliderHolderMap;

    /* renamed from: androidx.mediarouter.app.MediaRouteDynamicControllerDialog$1, reason: invalid class name */
    public class AnonymousClass1 extends Handler {
        final /* synthetic */ MediaRouteDynamicControllerDialog this$0;

        public AnonymousClass1(MediaRouteDynamicControllerDialog r1) {
            this.this$0 = r1;
        }

        @Override // android.os.Handler
        public void handleMessage(Message r2) {
            int r22 = r2.what;
            if (r22 != 1) goto L5;
            this.this$0.updateRoutesView();
            return;
        L5:
            if (r22 != 2) goto L14;
            MediaRouteDynamicControllerDialog r1 = this.this$0;
            if (r1.mRouteForVolumeUpdatingByUser == null) goto L13;
            r1.mRouteForVolumeUpdatingByUser = null;
            r1.updateViewsIfNeeded();
            return;
        L13:
            return;
        }
    }

    /* renamed from: androidx.mediarouter.app.MediaRouteDynamicControllerDialog$2, reason: invalid class name */
    public class AnonymousClass2 implements View.OnClickListener {
        final /* synthetic */ MediaRouteDynamicControllerDialog this$0;

        public AnonymousClass2(MediaRouteDynamicControllerDialog r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r1) {
            this.this$0.dismiss();
        }
    }

    /* renamed from: androidx.mediarouter.app.MediaRouteDynamicControllerDialog$3, reason: invalid class name */
    public class AnonymousClass3 implements View.OnClickListener {
        final /* synthetic */ MediaRouteDynamicControllerDialog this$0;

        public AnonymousClass3(MediaRouteDynamicControllerDialog r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r2) {
            if (this.this$0.mSelectedRoute.isSelected() == false) goto L5;
            this.this$0.mRouter.unselect(2);
        L5:
            this.this$0.dismiss();
        }
    }

    public class FetchArtTask extends AsyncTask<Void, Void, Bitmap> {
        private int mBackgroundColor;
        private final Bitmap mIconBitmap;
        private final Uri mIconUri;
        final /* synthetic */ MediaRouteDynamicControllerDialog this$0;

        public FetchArtTask(MediaRouteDynamicControllerDialog r4) {
            this.this$0 = r4;
            MediaDescriptionCompat r0 = r4.mDescription;
            Uri r1 = null;
            if (r0 != null) goto L5;
            Bitmap r02 = null;
        L7:
            if (MediaRouteDynamicControllerDialog.isBitmapRecycled(r02) == false) goto L9;
            Log.w(MediaRouteDynamicControllerDialog.TAG, "Can't fetch the given art bitmap because it's already recycled.");
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
            r22.setConnectTimeout(MediaRouteDynamicControllerDialog.CONNECTION_TIMEOUT_MS);
            r22.setReadTimeout(MediaRouteDynamicControllerDialog.CONNECTION_TIMEOUT_MS);
            r2 = r22.getInputStream();
            goto L11
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v0 */
        /* JADX WARN: Type inference failed for: r4v1 */
        /* JADX WARN: Type inference failed for: r4v2, types: [java.io.InputStream] */
        /* renamed from: doInBackground, reason: avoid collision after fix types in other method */
        public Bitmap doInBackground2(Void... r9) {
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
            Log.w(MediaRouteDynamicControllerDialog.TAG, "Unable to open: " + this.mIconUri);     // Catch: Throwable -> L13 IOException -> L15
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
        L75:
            r03.close();     // Catch: IOException -> L68
        L38:
            return null;
        L20:
            if (r5.outHeight == 0) goto L75;
            r03.reset();     // Catch: Throwable -> L13 IOException -> L24
        L30:
            r5.inJustDecodeBounds = false;     // Catch: Throwable -> L13 IOException -> L15
            r5.inSampleSize = Math.max(1, Integer.highestOneBit(r5.outHeight / this.this$0.mContext.getResources().getDimensionPixelSize(R.dimen.mr_cast_meta_art_size)));     // Catch: Throwable -> L13 IOException -> L15
            if (isCancelled() == true) goto L73;
            Bitmap r92 = BitmapFactory.decodeStream(r03, null, r5);     // Catch: Throwable -> L13 IOException -> L15
            r03.close();     // Catch: IOException -> L65
        L36:
            r0 = r92;
            goto L52
        L73:
            r03.close();     // Catch: IOException -> L66
        L33:
            return null;
        L24:
            r03.close();     // Catch: Throwable -> L13 IOException -> L15
            r03 = openInputStreamByScheme(this.mIconUri);     // Catch: Throwable -> L13 IOException -> L15
            if (r03 != null) goto L30;
            Log.w(MediaRouteDynamicControllerDialog.TAG, "Unable to open: " + this.mIconUri);     // Catch: Throwable -> L13 IOException -> L15
            if (r03 != null) goto L80;
        L29:
            return null;
        L80:
            r03.close();     // Catch: IOException -> L67
        L15:
            e = e;
        L43:
            Log.w(MediaRouteDynamicControllerDialog.TAG, "Unable to open: " + this.mIconUri, e);     // Catch: Throwable -> L13
            if (r03 == null) goto L50;
            r03.close();     // Catch: IOException -> L70
            goto L50
        L41:
            e = e;
            r03 = null;
        L39:
            th = th;
        L47:
            if (r4 != 0) goto L77;
        L49:
            throw th;
        L77:
            r4.close();     // Catch: IOException -> L69
        L13:
            th = th;
            r4 = r02;
        L52:
            if (MediaRouteDynamicControllerDialog.isBitmapRecycled(r0) == false) goto L55;
            Log.w(MediaRouteDynamicControllerDialog.TAG, "Can't use recycled bitmap: " + r0);
            return null;
        L55:
            if (r0 != null) goto L57;
        L63:
            return r0;
        L57:
            if (r0.getWidth() >= r0.getHeight()) goto L63;
            Palette r93 = new Palette.Builder(r0).maximumColorCount(1).generate();
            if (r93.getSwatches().isEmpty() == true) goto L62;
            r1 = r93.getSwatches().get(0).getRgb();
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
        public void onPostExecute2(Bitmap r3) {
            MediaRouteDynamicControllerDialog r0 = this.this$0;
            r0.mFetchArtTask = null;
            if (ObjectsCompat.equals(r0.mArtIconBitmap, this.mIconBitmap) == true) goto L5;
        L8:
            MediaRouteDynamicControllerDialog r02 = this.this$0;
            r02.mArtIconBitmap = this.mIconBitmap;
            r02.mArtIconLoadedBitmap = r3;
            r02.mArtIconUri = this.mIconUri;
            r02.mArtIconBackgroundColor = this.mBackgroundColor;
            r02.mArtIconIsLoaded = true;
            r02.updateMetadataViews();
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
            this.this$0.clearLoadedBitmap();
        }
    }

    public final class MediaControllerCallback extends MediaControllerCompat.Callback {
        final /* synthetic */ MediaRouteDynamicControllerDialog this$0;

        public MediaControllerCallback(MediaRouteDynamicControllerDialog r1) {
            this.this$0 = r1;
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onMetadataChanged(MediaMetadataCompat r2) {
            MediaRouteDynamicControllerDialog r0 = this.this$0;
            if (r2 != null) goto L5;
            MediaDescriptionCompat r22 = null;
        L6:
            r0.mDescription = r22;
            this.this$0.reloadIconIfNeeded();
            this.this$0.updateMetadataViews();
            return;
        L5:
            r22 = r2.getDescription();
            goto L6
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.Callback
        public void onSessionDestroyed() {
            MediaRouteDynamicControllerDialog r0 = this.this$0;
            MediaControllerCompat r1 = r0.mMediaController;
            if (r1 == null) goto L6;
            r1.unregisterCallback(r0.mControllerCallback);
            this.this$0.mMediaController = null;
            return;
        }
    }

    public abstract class MediaRouteVolumeSliderHolder extends RecyclerView.ViewHolder {
        final ImageButton mMuteButton;
        MediaRouter.RouteInfo mRoute;
        final MediaRouteVolumeSlider mVolumeSlider;
        final /* synthetic */ MediaRouteDynamicControllerDialog this$0;

        /* renamed from: androidx.mediarouter.app.MediaRouteDynamicControllerDialog$MediaRouteVolumeSliderHolder$1, reason: invalid class name */
        public class AnonymousClass1 implements View.OnClickListener {
            final /* synthetic */ MediaRouteVolumeSliderHolder this$1;

            public AnonymousClass1(MediaRouteVolumeSliderHolder r1) {
                this.this$1 = r1;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View r4) {
                MediaRouteDynamicControllerDialog r0 = this.this$1.this$0;
                if (r0.mRouteForVolumeUpdatingByUser == null) goto L5;
                r0.mHandler.removeMessages(2);
            L5:
                MediaRouteVolumeSliderHolder r02 = this.this$1;
                r02.this$0.mRouteForVolumeUpdatingByUser = r02.mRoute;
                boolean r42 = r4.isActivated();
                boolean r03 = !r42;
                if (r42 == true) goto L8;
                int r43 = 0;
            L9:
                this.this$1.setMute(r03);
                this.this$1.mVolumeSlider.setProgress(r43);
                this.this$1.mRoute.requestSetVolume(r43);
                this.this$1.this$0.mHandler.sendEmptyMessageDelayed(2, 500);
                return;
            L8:
                r43 = this.this$1.getUnmutedVolume();
                goto L9
            }
        }

        public MediaRouteVolumeSliderHolder(MediaRouteDynamicControllerDialog r1, View r2, ImageButton r3, MediaRouteVolumeSlider r4) {
            this.this$0 = r1;
            super(r2);
            this.mMuteButton = r3;
            this.mVolumeSlider = r4;
            r3.setImageDrawable(MediaRouterThemeHelper.getMuteButtonDrawableIcon(r1.mContext));
            MediaRouterThemeHelper.setVolumeSliderColor(r1.mContext, r4);
        }

        public void bindRouteVolumeSliderHolder(MediaRouter.RouteInfo r4) {
            this.mRoute = r4;
            int r0 = r4.getVolume();
            if (r0 != 0) goto L5;
            boolean r1 = true;
        L6:
            this.mMuteButton.setActivated(r1);
            this.mMuteButton.setOnClickListener(new AnonymousClass1(this));
            this.mVolumeSlider.setTag(this.mRoute);
            this.mVolumeSlider.setMax(r4.getVolumeMax());
            this.mVolumeSlider.setProgress(r0);
            this.mVolumeSlider.setOnSeekBarChangeListener(this.this$0.mVolumeChangeListener);
            return;
        L5:
            r1 = false;
            goto L6
        }

        public int getUnmutedVolume() {
            Integer r1 = this.this$0.mUnmutedVolumeMap.get(this.mRoute.getId());
            if (r1 != null) goto L6;
            return 1;
        L6:
            return Math.max(1, r1.intValue());
        }

        public void setMute(boolean r2) {
            if (this.mMuteButton.isActivated() != r2) goto L5;
            return;
        L5:
            this.mMuteButton.setActivated(r2);
            MediaRouteDynamicControllerDialog r0 = this.this$0;
            if (r2 == false) goto L9;
            r0.mUnmutedVolumeMap.put(this.mRoute.getId(), Integer.valueOf(this.mVolumeSlider.getProgress()));
            return;
        L9:
            r0.mUnmutedVolumeMap.remove(this.mRoute.getId());
        }

        public void updateVolume() {
            int r0 = this.mRoute.getVolume();
            if (r0 != 0) goto L5;
            boolean r1 = true;
        L6:
            setMute(r1);
            this.mVolumeSlider.setProgress(r0);
            return;
        L5:
            r1 = false;
            goto L6
        }
    }

    public final class MediaRouterCallback extends MediaRouter.Callback {
        final /* synthetic */ MediaRouteDynamicControllerDialog this$0;

        public MediaRouterCallback(MediaRouteDynamicControllerDialog r1) {
            this.this$0 = r1;
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteAdded(MediaRouter r1, MediaRouter.RouteInfo r2) {
            this.this$0.updateRoutesView();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteChanged(MediaRouter r2, MediaRouter.RouteInfo r3) {
            if (r3 == this.this$0.mSelectedRoute) goto L5;
        L21:
            this.this$0.updateRoutesView();
            return;
        L5:
            if (r3.getDynamicGroupController() == null) goto L21;
            Iterator<MediaRouter.RouteInfo> r22 = r3.getProvider().getRoutes().iterator();
        L8:
            if (r22.hasNext() == false) goto L21;
            MediaRouter.RouteInfo r32 = r22.next();
            if (this.this$0.mSelectedRoute.getSelectedRoutesInGroup().contains(r32) == true) goto L8;
            MediaRouter.GroupRouteInfo r0 = this.this$0.mSelectedRoute.asGroup();
            if (r0 == null) goto L8;
            if (r0.isGroupable(r32) == false) goto L8;
            if (this.this$0.mGroupableRoutes.contains(r32) == true) goto L8;
            this.this$0.updateViewsIfNeeded();
            this.this$0.updateRoutes();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteRemoved(MediaRouter r1, MediaRouter.RouteInfo r2) {
            this.this$0.updateRoutesView();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteSelected(MediaRouter r1, MediaRouter.RouteInfo r2) {
            MediaRouteDynamicControllerDialog r12 = this.this$0;
            r12.mSelectedRoute = r2;
            r12.mIsSelectingRoute = false;
            r12.updateViewsIfNeeded();
            this.this$0.updateRoutes();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteUnselected(MediaRouter r1, MediaRouter.RouteInfo r2) {
            this.this$0.updateRoutesView();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteVolumeChanged(MediaRouter r1, MediaRouter.RouteInfo r2) {
            r2.getVolume();
            boolean r12 = MediaRouteDynamicControllerDialog.DEBUG;
            MediaRouteDynamicControllerDialog r0 = this.this$0;
            if (r0.mRouteForVolumeUpdatingByUser == r2) goto L8;
            MediaRouteVolumeSliderHolder r02 = r0.mVolumeSliderHolderMap.get(r2.getId());
            if (r02 == null) goto L9;
            r02.updateVolume();
            return;
        L9:
            return;
        }
    }

    public final class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int ITEM_TYPE_GROUP = 4;
        private static final int ITEM_TYPE_GROUP_VOLUME = 1;
        private static final int ITEM_TYPE_HEADER = 2;
        private static final int ITEM_TYPE_ROUTE = 3;
        private final Interpolator mAccelerateDecelerateInterpolator;
        private final Drawable mDefaultIcon;
        private Item mGroupVolumeItem;
        private final LayoutInflater mInflater;
        private final ArrayList<Item> mItems;
        private final int mLayoutAnimationDurationMs;
        private final Drawable mSpeakerGroupIcon;
        private final Drawable mSpeakerIcon;
        private final Drawable mTvIcon;
        final /* synthetic */ MediaRouteDynamicControllerDialog this$0;

        /* renamed from: androidx.mediarouter.app.MediaRouteDynamicControllerDialog$RecyclerAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends Animation {
            final /* synthetic */ RecyclerAdapter this$1;
            final /* synthetic */ int val$endValue;
            final /* synthetic */ int val$startValue;
            final /* synthetic */ View val$view;

            public AnonymousClass1(RecyclerAdapter r1, int r2, int r3, View r4) {
                this.this$1 = r1;
                this.val$endValue = r2;
                this.val$startValue = r3;
                this.val$view = r4;
            }

            @Override // android.view.animation.Animation
            public void applyTransformation(float r2, Transformation r3) {
                int r32 = this.val$endValue;
                MediaRouteDynamicControllerDialog.setLayoutHeight(this.val$view, this.val$startValue + ((int) ((r32 - r0) * r2)));
            }
        }

        /* renamed from: androidx.mediarouter.app.MediaRouteDynamicControllerDialog$RecyclerAdapter$2, reason: invalid class name */
        public class AnonymousClass2 implements Animation.AnimationListener {
            final /* synthetic */ RecyclerAdapter this$1;

            public AnonymousClass2(RecyclerAdapter r1) {
                this.this$1 = r1;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation r1) {
                MediaRouteDynamicControllerDialog r0 = this.this$1.this$0;
                r0.mIsAnimatingVolumeSliderLayout = false;
                r0.updateViewsIfNeeded();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation r1) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation r1) {
                this.this$1.this$0.mIsAnimatingVolumeSliderLayout = true;
            }
        }

        public class GroupViewHolder extends RecyclerView.ViewHolder {
            final float mDisabledAlpha;
            final ImageView mImageView;
            final View mItemView;
            final ProgressBar mProgressBar;
            MediaRouter.RouteInfo mRoute;
            final TextView mTextView;
            final /* synthetic */ RecyclerAdapter this$1;

            /* renamed from: androidx.mediarouter.app.MediaRouteDynamicControllerDialog$RecyclerAdapter$GroupViewHolder$1, reason: invalid class name */
            public class AnonymousClass1 implements View.OnClickListener {
                final /* synthetic */ GroupViewHolder this$2;

                public AnonymousClass1(GroupViewHolder r1) {
                    this.this$2 = r1;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View r2) {
                    GroupViewHolder r22 = this.this$2;
                    r22.this$1.this$0.mRouter.transferToRoute(r22.mRoute);
                    this.this$2.mImageView.setVisibility(4);
                    this.this$2.mProgressBar.setVisibility(0);
                }
            }

            public GroupViewHolder(RecyclerAdapter r3, View r4) {
                this.this$1 = r3;
                super(r4);
                this.mItemView = r4;
                this.mImageView = (ImageView) r4.findViewById(R.id.mr_cast_group_icon);
                ProgressBar r0 = (ProgressBar) r4.findViewById(R.id.mr_cast_group_progress_bar);
                this.mProgressBar = r0;
                this.mTextView = (TextView) r4.findViewById(R.id.mr_cast_group_name);
                this.mDisabledAlpha = MediaRouterThemeHelper.getDisabledAlpha(r3.this$0.mContext);
                MediaRouterThemeHelper.setIndeterminateProgressBarColor(r3.this$0.mContext, r0);
            }

            private boolean isEnabled(MediaRouter.RouteInfo r3) {
                List<MediaRouter.RouteInfo> r2 = this.this$1.this$0.mSelectedRoute.getSelectedRoutesInGroup();
                if (r2.size() == 1) goto L5;
            L7:
                return true;
            L5:
                if (r2.get(0) != r3) goto L7;
                return false;
            }

            public void bindGroupViewHolder(Item r3) {
                MediaRouter.RouteInfo r32 = (MediaRouter.RouteInfo) r3.getData();
                this.mRoute = r32;
                this.mImageView.setVisibility(0);
                this.mProgressBar.setVisibility(4);
                boolean r0 = isEnabled(r32);
                View r1 = this.mItemView;
                if (r0 == false) goto L5;
                float r02 = 1.0f;
            L6:
                r1.setAlpha(r02);
                this.mItemView.setOnClickListener(new AnonymousClass1(this));
                this.mImageView.setImageDrawable(this.this$1.getIconDrawable(r32));
                this.mTextView.setText(r32.getName());
                return;
            L5:
                r02 = this.mDisabledAlpha;
                goto L6
            }
        }

        public class GroupVolumeViewHolder extends MediaRouteVolumeSliderHolder {
            private final int mExpandedHeight;
            private final TextView mTextView;
            final /* synthetic */ RecyclerAdapter this$1;

            public GroupVolumeViewHolder(RecyclerAdapter r4, View r5) {
                this.this$1 = r4;
                super(r4.this$0, r5, (ImageButton) r5.findViewById(R.id.mr_cast_mute_button), (MediaRouteVolumeSlider) r5.findViewById(R.id.mr_cast_volume_slider));
                this.mTextView = (TextView) r5.findViewById(R.id.mr_group_volume_route_name);
                Resources r42 = r4.this$0.mContext.getResources();
                DisplayMetrics r52 = r42.getDisplayMetrics();
                TypedValue r0 = new TypedValue();
                r42.getValue(R.dimen.mr_dynamic_volume_group_list_item_height, r0, true);
                this.mExpandedHeight = (int) r0.getDimension(r52);
            }

            public void bindGroupVolumeViewHolder(Item r3) {
                View r0 = this.itemView;
                if (this.this$1.isGroupVolumeNeeded() == false) goto L5;
                int r1 = this.mExpandedHeight;
            L6:
                MediaRouteDynamicControllerDialog.setLayoutHeight(r0, r1);
                MediaRouter.RouteInfo r32 = (MediaRouter.RouteInfo) r3.getData();
                super.bindRouteVolumeSliderHolder(r32);
                this.mTextView.setText(r32.getName());
                return;
            L5:
                r1 = 0;
                goto L6
            }

            public int getExpandedHeight() {
                return this.mExpandedHeight;
            }
        }

        public class HeaderViewHolder extends RecyclerView.ViewHolder {
            private final TextView mTextView;
            final /* synthetic */ RecyclerAdapter this$1;

            public HeaderViewHolder(RecyclerAdapter r1, View r2) {
                this.this$1 = r1;
                super(r2);
                this.mTextView = (TextView) r2.findViewById(R.id.mr_cast_header_name);
            }

            public void bindHeaderViewHolder(Item r1) {
                String r12 = r1.getData().toString();
                this.mTextView.setText(r12);
            }
        }

        public class Item {
            private final Object mData;
            private final int mType;
            final /* synthetic */ RecyclerAdapter this$1;

            public Item(RecyclerAdapter r1, Object r2, int r3) {
                this.this$1 = r1;
                this.mData = r2;
                this.mType = r3;
            }

            public Object getData() {
                return this.mData;
            }

            public int getType() {
                return this.mType;
            }
        }

        public class RouteViewHolder extends MediaRouteVolumeSliderHolder {
            final CheckBox mCheckBox;
            final int mCollapsedLayoutHeight;
            final float mDisabledAlpha;
            final int mExpandedLayoutHeight;
            final ImageView mImageView;
            final View mItemView;
            final ProgressBar mProgressBar;
            final TextView mTextView;
            final View.OnClickListener mViewClickListener;
            final RelativeLayout mVolumeSliderLayout;
            final /* synthetic */ RecyclerAdapter this$1;

            /* renamed from: androidx.mediarouter.app.MediaRouteDynamicControllerDialog$RecyclerAdapter$RouteViewHolder$1, reason: invalid class name */
            public class AnonymousClass1 implements View.OnClickListener {
                final /* synthetic */ RouteViewHolder this$2;

                public AnonymousClass1(RouteViewHolder r1) {
                    this.this$2 = r1;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View r5) {
                    RouteViewHolder r52 = this.this$2;
                    boolean r53 = r52.isSelected(r52.mRoute);
                    boolean r0 = !r53;
                    boolean r1 = this.this$2.mRoute.isGroup();
                    RouteViewHolder r2 = this.this$2;
                    if (r53 == true) goto L5;
                    r2.this$1.this$0.mRouter.addRouteToSelectedGroup(r2.mRoute);
                L6:
                    this.this$2.showSelectingProgress(r0, !r1);
                    if (r1 == false) goto L16;
                    List<MediaRouter.RouteInfo> r54 = this.this$2.this$1.this$0.mSelectedRoute.getSelectedRoutesInGroup();
                    Iterator<MediaRouter.RouteInfo> r12 = this.this$2.mRoute.getSelectedRoutesInGroup().iterator();
                L10:
                    if (r12.hasNext() == false) goto L16;
                    MediaRouter.RouteInfo r22 = r12.next();
                    if (r54.contains(r22) == r0) goto L10;
                    MediaRouteVolumeSliderHolder r23 = this.this$2.this$1.this$0.mVolumeSliderHolderMap.get(r22.getId());
                    if ((r23 instanceof RouteViewHolder) == false) goto L10;
                    ((RouteViewHolder) r23).showSelectingProgress(r0, true);
                L16:
                    RouteViewHolder r4 = this.this$2;
                    r4.this$1.mayUpdateGroupVolume(r4.mRoute, r0);
                    return;
                L5:
                    r2.this$1.this$0.mRouter.removeRouteFromSelectedGroup(r2.mRoute);
                    goto L6
                }
            }

            public RouteViewHolder(RecyclerAdapter r4, View r5) {
                this.this$1 = r4;
                super(r4.this$0, r5, (ImageButton) r5.findViewById(R.id.mr_cast_mute_button), (MediaRouteVolumeSlider) r5.findViewById(R.id.mr_cast_volume_slider));
                this.mViewClickListener = new AnonymousClass1(this);
                this.mItemView = r5;
                this.mImageView = (ImageView) r5.findViewById(R.id.mr_cast_route_icon);
                ProgressBar r0 = (ProgressBar) r5.findViewById(R.id.mr_cast_route_progress_bar);
                this.mProgressBar = r0;
                this.mTextView = (TextView) r5.findViewById(R.id.mr_cast_route_name);
                this.mVolumeSliderLayout = (RelativeLayout) r5.findViewById(R.id.mr_cast_volume_layout);
                CheckBox r52 = (CheckBox) r5.findViewById(R.id.mr_cast_checkbox);
                this.mCheckBox = r52;
                r52.setButtonDrawable(MediaRouterThemeHelper.getCheckBoxDrawableIcon(r4.this$0.mContext));
                MediaRouterThemeHelper.setIndeterminateProgressBarColor(r4.this$0.mContext, r0);
                this.mDisabledAlpha = MediaRouterThemeHelper.getDisabledAlpha(r4.this$0.mContext);
                Resources r42 = r4.this$0.mContext.getResources();
                DisplayMetrics r53 = r42.getDisplayMetrics();
                TypedValue r02 = new TypedValue();
                r42.getValue(R.dimen.mr_dynamic_dialog_row_height, r02, true);
                this.mExpandedLayoutHeight = (int) r02.getDimension(r53);
                this.mCollapsedLayoutHeight = 0;
            }

            private boolean isEnabled(MediaRouter.RouteInfo r4) {
                if (this.this$1.this$0.mUngroupableRoutes.contains(r4) == false) goto L6;
                return false;
            L6:
                if (isSelected(r4) == false) goto L11;
                if (this.this$1.this$0.mSelectedRoute.getSelectedRoutesInGroup().size() >= 2) goto L11;
                return false;
            L11:
                if (isSelected(r4) == false) goto L18;
                MediaRouter.GroupRouteInfo r3 = this.this$1.this$0.mSelectedRoute.asGroup();
                if (r3 != null) goto L15;
            L17:
                return false;
            L15:
                if (r3.isUnselectable(r4) == false) goto L17;
                return true;
            L18:
                return true;
            }

            public void bindRouteViewHolder(Item r6) {
                MediaRouter.RouteInfo r62 = (MediaRouter.RouteInfo) r6.getData();
                if (r62 == this.this$1.this$0.mSelectedRoute) goto L5;
            L12:
                bindRouteVolumeSliderHolder(r62);
                this.mImageView.setImageDrawable(this.this$1.getIconDrawable(r62));
                this.mTextView.setText(r62.getName());
                boolean r1 = false;
                this.mCheckBox.setVisibility(0);
                boolean r0 = isSelected(r62);
                boolean r63 = isEnabled(r62);
                this.mCheckBox.setChecked(r0);
                this.mProgressBar.setVisibility(4);
                this.mImageView.setVisibility(0);
                this.mItemView.setEnabled(r63);
                this.mCheckBox.setEnabled(r63);
                ImageButton r2 = this.mMuteButton;
                if (r63 == true) goto L17;
                if (r0 == true) goto L17;
                boolean r4 = false;
            L18:
                r2.setEnabled(r4);
                MediaRouteVolumeSlider r22 = this.mVolumeSlider;
                if (r63 == true) goto L21;
                if (r0 == true) goto L21;
            L22:
                r22.setEnabled(r1);
                this.mItemView.setOnClickListener(this.mViewClickListener);
                this.mCheckBox.setOnClickListener(this.mViewClickListener);
                RelativeLayout r12 = this.mVolumeSliderLayout;
                if (r0 == true) goto L25;
            L27:
                int r23 = this.mCollapsedLayoutHeight;
            L28:
                MediaRouteDynamicControllerDialog.setLayoutHeight(r12, r23);
                View r13 = this.mItemView;
                float r24 = 1.0f;
                if (r63 == true) goto L33;
                if (r0 == true) goto L33;
                float r3 = this.mDisabledAlpha;
            L34:
                r13.setAlpha(r3);
                CheckBox r14 = this.mCheckBox;
                if (r63 == true) goto L39;
                if (r0 == false) goto L39;
                r24 = this.mDisabledAlpha;
            L39:
                r14.setAlpha(r24);
                return;
            L33:
                r3 = 1.0f;
                goto L34
            L25:
                if (this.mRoute.isGroup() == true) goto L27;
                r23 = this.mExpandedLayoutHeight;
            L21:
                r1 = true;
            L17:
                r4 = true;
                goto L18
            L5:
                if (r62.getSelectedRoutesInGroup().size() <= 0) goto L12;
                Iterator<MediaRouter.RouteInfo> r02 = r62.getSelectedRoutesInGroup().iterator();
            L8:
                if (r02.hasNext() == false) goto L12;
                MediaRouter.RouteInfo r15 = r02.next();
                if (this.this$1.this$0.mGroupableRoutes.contains(r15) == true) goto L8;
                r62 = r15;
                goto L12
            }

            public boolean isSelected(MediaRouter.RouteInfo r3) {
                if (r3.isSelected() == false) goto L5;
                return true;
            L5:
                MediaRouter.GroupRouteInfo r2 = this.this$1.this$0.mSelectedRoute.asGroup();
                if (r2 != null) goto L8;
                return false;
            L8:
                if (r2.getSelectionState(r3) != 3) goto L12;
                return true;
            L12:
                return false;
            }

            public void showSelectingProgress(boolean r4, boolean r5) {
                this.mCheckBox.setEnabled(false);
                this.mItemView.setEnabled(false);
                this.mCheckBox.setChecked(r4);
                if (r4 == false) goto L5;
                this.mImageView.setVisibility(4);
                this.mProgressBar.setVisibility(0);
            L5:
                if (r5 == false) goto L12;
                RecyclerAdapter r52 = this.this$1;
                RelativeLayout r0 = this.mVolumeSliderLayout;
                if (r4 == false) goto L9;
                int r3 = this.mExpandedLayoutHeight;
            L10:
                r52.animateLayoutHeight(r0, r3);
                return;
            L9:
                r3 = this.mCollapsedLayoutHeight;
                goto L10
            }
        }

        public RecyclerAdapter(MediaRouteDynamicControllerDialog r2) {
            this.this$0 = r2;
            this.mItems = new ArrayList();
            this.mInflater = LayoutInflater.from(r2.mContext);
            this.mDefaultIcon = MediaRouterThemeHelper.getDefaultDrawableIcon(r2.mContext);
            this.mTvIcon = MediaRouterThemeHelper.getTvDrawableIcon(r2.mContext);
            this.mSpeakerIcon = MediaRouterThemeHelper.getSpeakerDrawableIcon(r2.mContext);
            this.mSpeakerGroupIcon = MediaRouterThemeHelper.getSpeakerGroupDrawableIcon(r2.mContext);
            this.mLayoutAnimationDurationMs = r2.mContext.getResources().getInteger(R.integer.mr_cast_volume_slider_layout_animation_duration_ms);
            this.mAccelerateDecelerateInterpolator = new AccelerateDecelerateInterpolator();
            updateItems();
        }

        private Drawable getDefaultIconDrawable(MediaRouter.RouteInfo r3) {
            int r0 = r3.getDeviceType();
            if (r0 == 1) goto L15;
            if (r0 == 2) goto L13;
            if (r3.isGroup() == false) goto L11;
            return this.mSpeakerGroupIcon;
        L11:
            return this.mDefaultIcon;
        L13:
            return this.mSpeakerIcon;
        L15:
            return this.mTvIcon;
        }

        public void animateLayoutHeight(View r5, int r6) {
            AnonymousClass1 r1 = new AnonymousClass1(this, r6, r5.getLayoutParams().height, r5);
            r1.setAnimationListener(new AnonymousClass2(this));
            r1.setDuration(this.mLayoutAnimationDurationMs);
            r1.setInterpolator(this.mAccelerateDecelerateInterpolator);
            r5.startAnimation(r1);
        }

        public Drawable getIconDrawable(MediaRouter.RouteInfo r5) {
            Uri r0 = r5.getIconUri();
            if (r0 == null) goto L10;
            Drawable r02 = Drawable.createFromStream(this.this$0.mContext.getContentResolver().openInputStream(r0), null);     // Catch: IOException -> L7
            if (r02 == null) goto L10;
            return r02;
        L7:
            e = move-exception;
            Log.w(MediaRouteDynamicControllerDialog.TAG, "Failed to load " + r0, e);
        L10:
            return getDefaultIconDrawable(r5);
        }

        public Item getItem(int r1) {
            if (r1 != 0) goto L6;
            return this.mGroupVolumeItem;
        L6:
            return this.mItems.get(r1 - 1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.mItems.size() + 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return getItem(r1).getType();
        }

        public boolean isGroupVolumeNeeded() {
            MediaRouteDynamicControllerDialog r1 = this.this$0;
            if (r1.mEnableGroupVolumeUX == true) goto L5;
            return false;
        L5:
            if (r1.mSelectedRoute.getSelectedRoutesInGroup().size() <= 1) goto L9;
            return true;
        L9:
            return false;
        }

        public void mayUpdateGroupVolume(MediaRouter.RouteInfo r6, boolean r7) {
            List<MediaRouter.RouteInfo> r0 = this.this$0.mSelectedRoute.getSelectedRoutesInGroup();
            boolean r2 = true;
            int r1 = Math.max(1, r0.size());
            int r4 = -1;
            if (r6.isGroup() == false) goto L13;
            Iterator<MediaRouter.RouteInfo> r62 = r6.getSelectedRoutesInGroup().iterator();
        L6:
            if (r62.hasNext() == false) goto L16;
            if (r0.contains(r62.next()) == r7) goto L6;
            if (r7 == false) goto L11;
            int r3 = 1;
        L12:
            r1 = r1 + r3;
            goto L6
        L11:
            r3 = -1;
        L16:
            boolean r63 = isGroupVolumeNeeded();
            MediaRouteDynamicControllerDialog r72 = this.this$0;
            int r32 = 0;
            if (r72.mEnableGroupVolumeUX == true) goto L19;
        L21:
            r2 = false;
        L22:
            if (r63 == r2) goto L35;
            RecyclerView.ViewHolder r64 = r72.mRecyclerView.findViewHolderForAdapterPosition(0);
            if ((r64 instanceof GroupVolumeViewHolder) == false) goto L36;
            GroupVolumeViewHolder r65 = (GroupVolumeViewHolder) r64;
            View r73 = r65.itemView;
            if (r2 == false) goto L28;
            r32 = r65.getExpandedHeight();
        L28:
            animateLayoutHeight(r73, r32);
            return;
        L36:
            return;
        L35:
            return;
        L19:
            if (r1 < 2) goto L21;
        L13:
            if (r7 == false) goto L15;
            r4 = 1;
        L15:
            r1 = r1 + r4;
            goto L16
        }

        public void notifyAdapterDataSetChanged() {
            this.this$0.mUngroupableRoutes.clear();
            MediaRouteDynamicControllerDialog r0 = this.this$0;
            r0.mUngroupableRoutes.addAll(MediaRouteDialogHelper.getItemsRemoved(r0.mGroupableRoutes, r0.getCurrentGroupableRoutes()));
            notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r3, int r4) {
            int r0 = getItemViewType(r4);
            Item r42 = getItem(r4);
            if (r0 != 1) goto L5;
            MediaRouter.RouteInfo r02 = (MediaRouter.RouteInfo) r42.getData();
            this.this$0.mVolumeSliderHolderMap.put(r02.getId(), (MediaRouteVolumeSliderHolder) r3);
            ((GroupVolumeViewHolder) r3).bindGroupVolumeViewHolder(r42);
            return;
        L5:
            if (r0 != 2) goto L7;
            ((HeaderViewHolder) r3).bindHeaderViewHolder(r42);
            return;
        L7:
            if (r0 != 3) goto L9;
            MediaRouter.RouteInfo r03 = (MediaRouter.RouteInfo) r42.getData();
            this.this$0.mVolumeSliderHolderMap.put(r03.getId(), (MediaRouteVolumeSliderHolder) r3);
            ((RouteViewHolder) r3).bindRouteViewHolder(r42);
            return;
        L9:
            if (r0 != 4) goto L12;
            ((GroupViewHolder) r3).bindGroupViewHolder(r42);
            return;
        L12:
            MethodWriter$$ExternalSyntheticBUOutline0.m();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            if (r4 == 1) goto L19;
            if (r4 == 2) goto L17;
            if (r4 == 3) goto L15;
            if (r4 == 4) goto L11;
            MethodWriter$$ExternalSyntheticBUOutline0.m();
            return null;
        L11:
            return new GroupViewHolder(this, this.mInflater.inflate(R.layout.mr_cast_group_item, r3, false));
        L15:
            return new RouteViewHolder(this, this.mInflater.inflate(R.layout.mr_cast_route_item, r3, false));
        L17:
            return new HeaderViewHolder(this, this.mInflater.inflate(R.layout.mr_cast_header_item, r3, false));
        L19:
            return new GroupVolumeViewHolder(this, this.mInflater.inflate(R.layout.mr_cast_group_volume_item, r3, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewRecycled(RecyclerView.ViewHolder r1) {
            super.onViewRecycled(r1);
            this.this$0.mVolumeSliderHolderMap.values().remove(r1);
        }

        public void updateItems() {
            this.mItems.clear();
            this.mGroupVolumeItem = new Item(this, this.this$0.mSelectedRoute, 1);
            if (this.this$0.mMemberRoutes.isEmpty() == true) goto L8;
            Iterator<MediaRouter.RouteInfo> r0 = this.this$0.mMemberRoutes.iterator();
        L6:
            if (r0.hasNext() == false) goto L9;
            this.mItems.add(new Item(this, r0.next(), 3));
        L9:
            boolean r5 = false;
            if (this.this$0.mGroupableRoutes.isEmpty() == true) goto L27;
            Iterator<MediaRouter.RouteInfo> r02 = this.this$0.mGroupableRoutes.iterator();
            boolean r6 = false;
        L13:
            if (r02.hasNext() == false) goto L27;
            MediaRouter.RouteInfo r7 = r02.next();
            if (this.this$0.mMemberRoutes.contains(r7) == true) goto L13;
            if (r6 == true) goto L25;
            MediaRouteProvider.DynamicGroupRouteController r62 = this.this$0.mSelectedRoute.getDynamicGroupController();
            if (r62 == null) goto L20;
            String r63 = r62.getGroupableSelectionTitle();
        L22:
            if (TextUtils.isEmpty(r63) == false) goto L24;
            r63 = this.this$0.mContext.getString(R.string.mr_dialog_groupable_header);
        L24:
            this.mItems.add(new Item(this, r63, 2));
            r6 = true;
            goto L25
        L20:
            r63 = null;
        L25:
            this.mItems.add(new Item(this, r7, 3));
        L27:
            if (this.this$0.mTransferableRoutes.isEmpty() == true) goto L43;
            Iterator<MediaRouter.RouteInfo> r03 = this.this$0.mTransferableRoutes.iterator();
        L30:
            if (r03.hasNext() == false) goto L43;
            MediaRouter.RouteInfo r1 = r03.next();
            MediaRouter.RouteInfo r64 = this.this$0.mSelectedRoute;
            if (r64 == r1) goto L30;
            if (r5 == true) goto L42;
            MediaRouteProvider.DynamicGroupRouteController r52 = r64.getDynamicGroupController();
            if (r52 == null) goto L37;
            String r53 = r52.getTransferableSectionTitle();
        L39:
            if (TextUtils.isEmpty(r53) == false) goto L41;
            r53 = this.this$0.mContext.getString(R.string.mr_dialog_transferable_header);
        L41:
            this.mItems.add(new Item(this, r53, 2));
            r5 = true;
            goto L42
        L37:
            r53 = null;
        L42:
            this.mItems.add(new Item(this, r1, 4));
        L43:
            notifyAdapterDataSetChanged();
            return;
        L8:
            this.mItems.add(new Item(this, this.this$0.mSelectedRoute, 3));
            goto L9
        }
    }

    public static final class RouteComparator implements Comparator<MediaRouter.RouteInfo> {
        static final RouteComparator sInstance = null;

        static {
            sInstance = new RouteComparator();
        }

        public RouteComparator() {
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(MediaRouter.RouteInfo r1, MediaRouter.RouteInfo r2) {
            return r1.getName().compareToIgnoreCase(r2.getName());
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(MediaRouter.RouteInfo r1, MediaRouter.RouteInfo r2) {
            return compare2(r1, r2);
        }
    }

    public class VolumeChangeListener implements SeekBar.OnSeekBarChangeListener {
        final /* synthetic */ MediaRouteDynamicControllerDialog this$0;

        public VolumeChangeListener(MediaRouteDynamicControllerDialog r1) {
            this.this$0 = r1;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar r1, int r2, boolean r3) {
            if (r3 == false) goto L11;
            MediaRouter.RouteInfo r12 = (MediaRouter.RouteInfo) r1.getTag();
            MediaRouteVolumeSliderHolder r0 = this.this$0.mVolumeSliderHolderMap.get(r12.getId());
            if (r0 == null) goto L9;
            if (r2 != 0) goto L7;
            boolean r32 = true;
        L8:
            r0.setMute(r32);
            goto L9
        L7:
            r32 = false;
        L9:
            r12.requestSetVolume(r2);
            return;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar r3) {
            MediaRouteDynamicControllerDialog r0 = this.this$0;
            if (r0.mRouteForVolumeUpdatingByUser == null) goto L5;
            r0.mHandler.removeMessages(2);
        L5:
            MediaRouteDynamicControllerDialog r2 = this.this$0;
            r2.mRouteForVolumeUpdatingByUser = (MediaRouter.RouteInfo) r3.getTag();
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar r3) {
            this.this$0.mHandler.sendEmptyMessageDelayed(2, 500);
        }
    }

    static {
        Log.isLoggable(TAG, 3);
    }

    public MediaRouteDynamicControllerDialog(Context r2) {
        this(r2, 0);
    }

    public MediaRouteDynamicControllerDialog(Context r2, int r3) {
        Context r22 = MediaRouterThemeHelper.createThemedDialogContext(r2, r3, false);
        super(r22, MediaRouterThemeHelper.createThemedDialogStyle(r22));
        this.mSelector = MediaRouteSelector.EMPTY;
        this.mMemberRoutes = new ArrayList();
        this.mGroupableRoutes = new ArrayList();
        this.mTransferableRoutes = new ArrayList();
        this.mUngroupableRoutes = new ArrayList();
        this.mHandler = new AnonymousClass1(this);
        Context r23 = getContext();
        this.mContext = r23;
        MediaRouter r24 = MediaRouter.getInstance(r23);
        this.mRouter = r24;
        this.mEnableGroupVolumeUX = MediaRouter.isGroupVolumeUxEnabled();
        this.mCallback = new MediaRouterCallback(this);
        this.mSelectedRoute = r24.getSelectedRoute();
        this.mControllerCallback = new MediaControllerCallback(this);
        setMediaSession(r24.getMediaSessionToken());
    }

    private static Bitmap blurBitmap(Bitmap r4, float r5, Context r6) {
        RenderScript r62 = RenderScript.create(r6);
        Allocation r0 = Allocation.createFromBitmap(r62, r4);
        Allocation r1 = Allocation.createTyped(r62, r0.getType());
        ScriptIntrinsicBlur r2 = ScriptIntrinsicBlur.create(r62, Element.U8_4(r62));
        r2.setRadius(r5);
        r2.setInput(r0);
        r2.forEach(r1);
        Bitmap r42 = r4.copy(r4.getConfig(), true);
        r1.copyTo(r42);
        r0.destroy();
        r1.destroy();
        r2.destroy();
        r62.destroy();
        return r42;
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
        reloadIconIfNeeded();
        updateMetadataViews();
    }

    private boolean shouldDeferUpdateViews() {
        if (this.mRouteForVolumeUpdatingByUser == null) goto L5;
    L11:
        return true;
    L5:
        if (this.mIsSelectingRoute == true) goto L11;
        if (this.mIsAnimatingVolumeSliderLayout == true) goto L11;
        return !this.mCreated;
    }

    public void clearLoadedBitmap() {
        this.mArtIconIsLoaded = false;
        this.mArtIconLoadedBitmap = null;
        this.mArtIconBackgroundColor = 0;
    }

    public List<MediaRouter.RouteInfo> getCurrentGroupableRoutes() {
        ArrayList r0 = new ArrayList();
        MediaRouter.GroupRouteInfo r1 = this.mSelectedRoute.asGroup();
        if (r1 == null) goto L10;
        Iterator<MediaRouter.RouteInfo> r4 = this.mSelectedRoute.getProvider().getRoutes().iterator();
    L6:
        if (r4.hasNext() == false) goto L10;
        MediaRouter.RouteInfo r2 = r4.next();
        if (r1.isGroupable(r2) == false) goto L6;
        r0.add(r2);
    L10:
        return r0;
    }

    public MediaSessionCompat.Token getMediaSession() {
        MediaControllerCompat r0 = this.mMediaController;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.getSessionToken();
    }

    public MediaRouteSelector getRouteSelector() {
        return this.mSelector;
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAttachedToWindow = true;
        this.mRouter.addCallback(this.mSelector, this.mCallback, 1);
        updateRoutes();
        setMediaSession(this.mRouter.getMediaSessionToken());
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    public void onCreate(Bundle r4) {
        super.onCreate(r4);
        setContentView(R.layout.mr_cast_dialog);
        MediaRouterThemeHelper.setDialogBackgroundColor(this.mContext, this);
        ImageButton r42 = (ImageButton) findViewById(R.id.mr_cast_close_button);
        this.mCloseButton = r42;
        r42.setColorFilter(-1);
        this.mCloseButton.setOnClickListener(new AnonymousClass2(this));
        Button r43 = (Button) findViewById(R.id.mr_cast_stop_button);
        this.mStopCastingButton = r43;
        r43.setTextColor(-1);
        this.mStopCastingButton.setOnClickListener(new AnonymousClass3(this));
        this.mAdapter = new RecyclerAdapter(this);
        RecyclerView r44 = (RecyclerView) findViewById(R.id.mr_cast_list);
        this.mRecyclerView = r44;
        r44.setAdapter(this.mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mVolumeChangeListener = new VolumeChangeListener(this);
        this.mVolumeSliderHolderMap = new HashMap();
        this.mUnmutedVolumeMap = new HashMap();
        this.mMetadataBackground = (ImageView) findViewById(R.id.mr_cast_meta_background);
        this.mMetadataBlackScrim = findViewById(R.id.mr_cast_meta_black_scrim);
        this.mArtView = (ImageView) findViewById(R.id.mr_cast_meta_art);
        TextView r45 = (TextView) findViewById(R.id.mr_cast_meta_title);
        this.mTitleView = r45;
        r45.setTextColor(-1);
        TextView r46 = (TextView) findViewById(R.id.mr_cast_meta_subtitle);
        this.mSubtitleView = r46;
        r46.setTextColor(-1);
        this.mTitlePlaceholder = this.mContext.getResources().getString(R.string.mr_cast_dialog_title_view_placeholder);
        this.mCreated = true;
        updateLayout();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mAttachedToWindow = false;
        this.mRouter.removeCallback(this.mCallback);
        this.mHandler.removeCallbacksAndMessages(null);
        setMediaSession(null);
    }

    public boolean onFilterRoute(MediaRouter.RouteInfo r2) {
        if (r2.isDefaultOrBluetooth() == false) goto L5;
        return false;
    L5:
        if (r2.isEnabled() == true) goto L7;
        return false;
    L7:
        if (r2.matchesSelector(this.mSelector) == true) goto L9;
        return false;
    L9:
        if (this.mSelectedRoute == r2) goto L16;
        return true;
    L16:
        return false;
    }

    public void onFilterRoutes(List<MediaRouter.RouteInfo> r3) {
        int r0 = r3.size() - 1;
    L3:
        if (r0 < 0) goto L8;
        if (onFilterRoute(r3.get(r0)) == true) goto L7;
        r3.remove(r0);
    L7:
        r0 = r0 - 1;
        goto L3
    }

    public void reloadIconIfNeeded() {
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
        Uri r32 = this.mArtIconUri;
    L18:
        if (r23 != r02) goto L23;
        if (r23 == null) goto L21;
        return;
    L21:
        if (ObjectsCompat.equals(r32, r1) == false) goto L23;
        return;
    L23:
        FetchArtTask r03 = this.mFetchArtTask;
        if (r03 == null) goto L26;
        r03.cancel(true);
    L26:
        FetchArtTask r04 = new FetchArtTask(this);
        this.mFetchArtTask = r04;
        r04.execute(new Void[0]);
        return;
    L17:
        r32 = r3.getIconUri();
        goto L18
    L13:
        r23 = r22.getIconBitmap();
        goto L14
    L5:
        r02 = r0.getIconBitmap();
        goto L6
    }

    public void setRouteSelector(MediaRouteSelector r4) {
        if (r4 != null) goto L4;
        f$$ExternalSyntheticBUOutline1.m("selector must not be null");
        return;
    L4:
        if (this.mSelector.equals(r4) == true) goto L11;
        this.mSelector = r4;
        if (this.mAttachedToWindow == false) goto L12;
        this.mRouter.removeCallback(this.mCallback);
        this.mRouter.addCallback(r4, this.mCallback, 1);
        updateRoutes();
        return;
    L12:
        return;
    }

    public void updateLayout() {
        int r0 = MediaRouteDialogHelper.getDialogWidthForDynamicGroup(this.mContext);
        int r1 = MediaRouteDialogHelper.getDialogHeight(this.mContext);
        getWindow().setLayout(r0, r1);
        this.mArtIconBitmap = null;
        this.mArtIconUri = null;
        reloadIconIfNeeded();
        updateMetadataViews();
        updateRoutesView();
    }

    public void updateMetadataViews() {
        if (shouldDeferUpdateViews() == false) goto L6;
        this.mUpdateMetadataViewsDeferred = true;
        return;
    L6:
        this.mUpdateMetadataViewsDeferred = false;
        if (this.mSelectedRoute.isSelected() == true) goto L9;
    L10:
        dismiss();
    L11:
        CharSequence r2 = null;
        if (this.mArtIconIsLoaded == false) goto L19;
        if (isBitmapRecycled(this.mArtIconLoadedBitmap) == true) goto L19;
        if (this.mArtIconLoadedBitmap == null) goto L19;
        this.mArtView.setVisibility(0);
        this.mArtView.setImageBitmap(this.mArtIconLoadedBitmap);
        this.mArtView.setBackgroundColor(this.mArtIconBackgroundColor);
        this.mMetadataBlackScrim.setVisibility(0);
        this.mMetadataBackground.setImageBitmap(blurBitmap(this.mArtIconLoadedBitmap, 10.0f, this.mContext));
    L22:
        clearLoadedBitmap();
        MediaDescriptionCompat r1 = this.mDescription;
        if (r1 != null) goto L25;
        CharSequence r12 = null;
    L26:
        boolean r4 = TextUtils.isEmpty(r12);
        MediaDescriptionCompat r5 = this.mDescription;
        if (r5 == null) goto L30;
        r2 = r5.getSubtitle();
    L30:
        boolean r52 = TextUtils.isEmpty(r2);
        TextView r6 = this.mTitleView;
        if (r4 == true) goto L33;
        r6.setText(r12);
    L34:
        TextView r13 = this.mSubtitleView;
        if (r52 == true) goto L38;
        r13.setText(r2);
        this.mSubtitleView.setVisibility(0);
        return;
    L38:
        r13.setVisibility(8);
        return;
    L33:
        r6.setText(this.mTitlePlaceholder);
        goto L34
    L25:
        r12 = r1.getTitle();
    L19:
        if (isBitmapRecycled(this.mArtIconLoadedBitmap) == false) goto L21;
        Log.w(TAG, "Can't set artwork image with recycled bitmap: " + this.mArtIconLoadedBitmap);
    L21:
        this.mArtView.setVisibility(8);
        this.mMetadataBlackScrim.setVisibility(8);
        this.mMetadataBackground.setImageBitmap(null);
        goto L22
    L9:
        if (this.mSelectedRoute.isDefaultOrBluetooth() == false) goto L11;
        goto L10
    }

    public void updateRoutes() {
        this.mMemberRoutes.clear();
        this.mGroupableRoutes.clear();
        this.mTransferableRoutes.clear();
        this.mMemberRoutes.addAll(this.mSelectedRoute.getSelectedRoutesInGroup());
        MediaRouter.GroupRouteInfo r0 = this.mSelectedRoute.asGroup();
        if (r0 == null) goto L13;
        Iterator<MediaRouter.RouteInfo> r1 = this.mSelectedRoute.getProvider().getRoutes().iterator();
    L6:
        if (r1.hasNext() == false) goto L13;
        MediaRouter.RouteInfo r2 = r1.next();
        if (r0.isGroupable(r2) == false) goto L11;
        this.mGroupableRoutes.add(r2);
    L11:
        if (r0.isTransferable(r2) == false) goto L6;
        this.mTransferableRoutes.add(r2);
    L13:
        onFilterRoutes(this.mGroupableRoutes);
        onFilterRoutes(this.mTransferableRoutes);
        List<MediaRouter.RouteInfo> r02 = this.mMemberRoutes;
        RouteComparator r12 = RouteComparator.sInstance;
        Collections.sort(r02, r12);
        Collections.sort(this.mGroupableRoutes, r12);
        Collections.sort(this.mTransferableRoutes, r12);
        this.mAdapter.updateItems();
    }

    public void updateRoutesView() {
        if (this.mAttachedToWindow == true) goto L5;
        return;
    L5:
        if ((SystemClock.uptimeMillis() - this.mLastUpdateTime) >= 300) goto L7;
        this.mHandler.removeMessages(1);
        this.mHandler.sendEmptyMessageAtTime(1, this.mLastUpdateTime + 300);
        return;
    L7:
        if (shouldDeferUpdateViews() == false) goto L10;
        this.mUpdateRoutesViewDeferred = true;
        return;
    L10:
        this.mUpdateRoutesViewDeferred = false;
        if (this.mSelectedRoute.isSelected() == true) goto L13;
    L14:
        dismiss();
    L15:
        this.mLastUpdateTime = SystemClock.uptimeMillis();
        this.mAdapter.notifyAdapterDataSetChanged();
        return;
    L13:
        if (this.mSelectedRoute.isDefaultOrBluetooth() == false) goto L15;
        goto L14
    }

    public void updateViewsIfNeeded() {
        if (this.mUpdateRoutesViewDeferred == false) goto L6;
        updateRoutesView();
    L6:
        if (this.mUpdateMetadataViewsDeferred == false) goto L9;
        updateMetadataViews();
        return;
    }
}
