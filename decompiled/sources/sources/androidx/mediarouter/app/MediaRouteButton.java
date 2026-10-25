package androidx.mediarouter.app;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import androidx.mediarouter.media.MediaRouterParams;
import c.f$$ExternalSyntheticBUOutline1;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public class MediaRouteButton extends View {
    private static final int[] CHECKABLE_STATE_SET = null;
    private static final int[] CHECKED_STATE_SET = null;
    private static final String CHOOSER_FRAGMENT_TAG = "android.support.v7.mediarouter:MediaRouteChooserDialogFragment";
    private static final int CONNECTION_STATE_CONNECTED = 2;
    private static final int CONNECTION_STATE_CONNECTING = 1;
    private static final int CONNECTION_STATE_DISCONNECTED = 0;
    private static final String CONTROLLER_FRAGMENT_TAG = "android.support.v7.mediarouter:MediaRouteControllerDialogFragment";
    private static final String TAG = "MediaRouteButton";
    static final SparseArray<Drawable.ConstantState> sRemoteIndicatorCache = null;
    private boolean mAttachedToWindow;
    private ColorStateList mButtonTint;
    private final MediaRouterCallback mCallback;
    private boolean mCheatSheetEnabled;
    private int mConnectionState;
    private MediaRouteDialogFactory mDialogFactory;
    boolean mIsFixedIcon;
    private int mLastConnectionState;
    private int mMinHeight;
    private int mMinWidth;
    private Drawable mRemoteIndicator;
    RemoteIndicatorLoader mRemoteIndicatorLoader;
    private int mRemoteIndicatorResIdToLoad;
    private final MediaRouter mRouter;
    private MediaRouteSelector mSelector;

    public final class MediaRouterCallback extends MediaRouter.Callback {
        final /* synthetic */ MediaRouteButton this$0;

        public MediaRouterCallback(MediaRouteButton r1) {
            this.this$0 = r1;
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onProviderAdded(MediaRouter r1, MediaRouter.ProviderInfo r2) {
            this.this$0.refreshRoute();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onProviderChanged(MediaRouter r1, MediaRouter.ProviderInfo r2) {
            this.this$0.refreshRoute();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onProviderRemoved(MediaRouter r1, MediaRouter.ProviderInfo r2) {
            this.this$0.refreshRoute();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteAdded(MediaRouter r1, MediaRouter.RouteInfo r2) {
            this.this$0.refreshRoute();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteChanged(MediaRouter r1, MediaRouter.RouteInfo r2) {
            this.this$0.refreshRoute();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteRemoved(MediaRouter r1, MediaRouter.RouteInfo r2) {
            this.this$0.refreshRoute();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteSelected(MediaRouter r1, MediaRouter.RouteInfo r2) {
            this.this$0.refreshRoute();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouteUnselected(MediaRouter r1, MediaRouter.RouteInfo r2) {
            this.this$0.refreshRoute();
        }

        @Override // androidx.mediarouter.media.MediaRouter.Callback
        public void onRouterParamsChanged(MediaRouter r1, MediaRouterParams r2) {
            if (r2 == null) goto L4;
            boolean r12 = r2.getExtras().getBoolean(MediaRouterParams.EXTRAS_KEY_FIXED_CAST_ICON);
        L5:
            MediaRouteButton r0 = this.this$0;
            if (r0.mIsFixedIcon == r12) goto L9;
            r0.mIsFixedIcon = r12;
            r0.refreshDrawableState();
            return;
        L9:
            return;
        L4:
            r12 = false;
            goto L5
        }
    }

    public final class RemoteIndicatorLoader extends AsyncTask<Void, Void, Drawable> {
        private final Context mContext;
        private final int mResId;
        final /* synthetic */ MediaRouteButton this$0;

        public RemoteIndicatorLoader(MediaRouteButton r1, int r2, Context r3) {
            this.this$0 = r1;
            this.mResId = r2;
            this.mContext = r3;
        }

        private void cacheAndReset(Drawable r3) {
            if (r3 == null) goto L4;
            MediaRouteButton.sRemoteIndicatorCache.put(this.mResId, r3.getConstantState());
        L4:
            this.this$0.mRemoteIndicatorLoader = null;
        }

        /* renamed from: doInBackground, reason: avoid collision after fix types in other method */
        public Drawable doInBackground2(Void... r2) {
            if (MediaRouteButton.sRemoteIndicatorCache.get(this.mResId) == null) goto L5;
            return null;
        L5:
            return AppCompatResources.getDrawable(this.mContext, this.mResId);
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ Drawable doInBackground(Void[] r1) {
            return doInBackground2(r1);
        }

        /* renamed from: onCancelled, reason: avoid collision after fix types in other method */
        public void onCancelled2(Drawable r1) {
            cacheAndReset(r1);
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ void onCancelled(Drawable r1) {
            onCancelled2(r1);
        }

        /* renamed from: onPostExecute, reason: avoid collision after fix types in other method */
        public void onPostExecute2(Drawable r3) {
            if (r3 == null) goto L4;
            cacheAndReset(r3);
        L8:
            this.this$0.setRemoteIndicatorDrawableInternal(r3);
            return;
        L4:
            Drawable.ConstantState r0 = MediaRouteButton.sRemoteIndicatorCache.get(this.mResId);
            if (r0 == null) goto L7;
            r3 = r0.newDrawable();
        L7:
            this.this$0.mRemoteIndicatorLoader = null;
            goto L8
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ void onPostExecute(Drawable r1) {
            onPostExecute2(r1);
        }
    }

    static {
        sRemoteIndicatorCache = new SparseArray(2);
        CHECKED_STATE_SET = new int[]{R.attr.state_checked};
        CHECKABLE_STATE_SET = new int[]{R.attr.state_checkable};
    }

    public MediaRouteButton(Context r2) {
        this(r2, null);
    }

    public MediaRouteButton(Context r2, AttributeSet r3) {
        this(r2, r3, androidx.mediarouter.R.attr.mediaRouteButtonStyle);
    }

    public MediaRouteButton(Context r9, AttributeSet r10, int r11) {
        super(MediaRouterThemeHelper.createThemedButtonContext(r9), r10, r11);
        this.mSelector = MediaRouteSelector.EMPTY;
        this.mDialogFactory = MediaRouteDialogFactory.getDefault();
        Context r1 = getContext();
        TypedArray r4 = r1.obtainStyledAttributes(r10, androidx.mediarouter.R.styleable.MediaRouteButton, r11, 0);
        ViewCompat.saveAttributeDataForStyleable(this, r1, androidx.mediarouter.R.styleable.MediaRouteButton, r10, r4, r11, 0);
        if (isInEditMode() == false) goto L6;
        this.mRouter = null;
        this.mCallback = null;
        this.mRemoteIndicator = AppCompatResources.getDrawable(r1, r4.getResourceId(androidx.mediarouter.R.styleable.MediaRouteButton_externalRouteEnabledDrawableStatic, 0));
        return;
    L6:
        MediaRouter r8 = MediaRouter.getInstance(r1);
        this.mRouter = r8;
        this.mCallback = new MediaRouterCallback(this);
        MediaRouter.RouteInfo r82 = r8.getSelectedRoute();
        if (r82.isDefaultOrBluetooth() == true) goto L9;
        int r83 = r82.getConnectionState();
    L10:
        this.mConnectionState = r83;
        this.mLastConnectionState = r83;
        this.mButtonTint = r4.getColorStateList(androidx.mediarouter.R.styleable.MediaRouteButton_mediaRouteButtonTint);
        this.mMinWidth = r4.getDimensionPixelSize(androidx.mediarouter.R.styleable.MediaRouteButton_android_minWidth, 0);
        this.mMinHeight = r4.getDimensionPixelSize(androidx.mediarouter.R.styleable.MediaRouteButton_android_minHeight, 0);
        int r84 = r4.getResourceId(androidx.mediarouter.R.styleable.MediaRouteButton_externalRouteEnabledDrawableStatic, 0);
        this.mRemoteIndicatorResIdToLoad = r4.getResourceId(androidx.mediarouter.R.styleable.MediaRouteButton_externalRouteEnabledDrawable, 0);
        r4.recycle();
        int r92 = this.mRemoteIndicatorResIdToLoad;
        if (r92 == 0) goto L16;
        Drawable.ConstantState r93 = sRemoteIndicatorCache.get(r92);
        if (r93 == null) goto L16;
        setRemoteIndicatorDrawable(r93.newDrawable());
    L16:
        if (this.mRemoteIndicator != null) goto L23;
        if (r84 == 0) goto L22;
        Drawable.ConstantState r94 = sRemoteIndicatorCache.get(r84);
        if (r94 == null) goto L21;
        setRemoteIndicatorDrawableInternal(r94.newDrawable());
        goto L23
    L21:
        RemoteIndicatorLoader r95 = new RemoteIndicatorLoader(this, r84, getContext());
        this.mRemoteIndicatorLoader = r95;
        r95.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
        goto L23
    L22:
        loadRemoteIndicatorIfNeeded();
    L23:
        updateContentDescription();
        setClickable(true);
        return;
    L9:
        r83 = 0;
        goto L10
    }

    private Activity getActivity() {
        Context r1 = getContext();
    L4:
        if ((r1 instanceof ContextWrapper) == false) goto L10;
        if ((r1 instanceof Activity) == true) goto L8;
        r1 = ((ContextWrapper) r1).getBaseContext();
        goto L4
    L8:
        return (Activity) r1;
    L10:
        return null;
    }

    private FragmentManager getFragmentManager() {
        Activity r1 = getActivity();
        if ((r1 instanceof FragmentActivity) == true) goto L5;
        return null;
    L5:
        return ((FragmentActivity) r1).getSupportFragmentManager();
    }

    private void loadRemoteIndicatorIfNeeded() {
        if (this.mRemoteIndicatorResIdToLoad <= 0) goto L9;
        RemoteIndicatorLoader r0 = this.mRemoteIndicatorLoader;
        if (r0 == null) goto L7;
        r0.cancel(false);
    L7:
        RemoteIndicatorLoader r02 = new RemoteIndicatorLoader(this, this.mRemoteIndicatorResIdToLoad, getContext());
        this.mRemoteIndicatorLoader = r02;
        this.mRemoteIndicatorResIdToLoad = 0;
        r02.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
        return;
    }

    private boolean showDialogForType(int r8) {
        FragmentManager r0 = getFragmentManager();
        if (r0 != null) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("The activity must be a subclass of FragmentActivity");
        return false;
    L5:
        if (this.mRouter.getSelectedRoute().isDefaultOrBluetooth() == false) goto L15;
        if (r0.findFragmentByTag(CHOOSER_FRAGMENT_TAG) == null) goto L10;
        Log.w(TAG, "showDialog(): Route chooser dialog already showing!");
        return false;
    L10:
        MediaRouteChooserDialogFragment r1 = this.mDialogFactory.onCreateChooserDialogFragment();
        r1.setRouteSelector(this.mSelector);
        if (r8 != 2) goto L13;
        r1.setUseDynamicGroup(true);
    L13:
        FragmentTransaction r7 = r0.beginTransaction();
        r7.add(r1, CHOOSER_FRAGMENT_TAG);
        r7.commitAllowingStateLoss();
    L22:
        return true;
    L15:
        if (r0.findFragmentByTag(CONTROLLER_FRAGMENT_TAG) == null) goto L18;
        Log.w(TAG, "showDialog(): Route controller dialog already showing!");
        return false;
    L18:
        MediaRouteControllerDialogFragment r12 = this.mDialogFactory.onCreateControllerDialogFragment();
        r12.setRouteSelector(this.mSelector);
        if (r8 != 2) goto L21;
        r12.setUseDynamicGroup(true);
    L21:
        FragmentTransaction r72 = r0.beginTransaction();
        r72.add(r12, CONTROLLER_FRAGMENT_TAG);
        r72.commitAllowingStateLoss();
        goto L22
    }

    private void updateContentDescription() {
        int r0 = this.mConnectionState;
        if (r0 != 1) goto L5;
        int r02 = androidx.mediarouter.R.string.mr_cast_button_connecting;
    L9:
        String r03 = getContext().getString(r02);
        setContentDescription(r03);
        if (this.mCheatSheetEnabled == true) goto L12;
    L14:
        r03 = null;
    L15:
        TooltipCompat.setTooltipText(this, r03);
        return;
    L12:
        if (TextUtils.isEmpty(r03) == true) goto L14;
    L5:
        if (r0 == 2) goto L7;
        r02 = androidx.mediarouter.R.string.mr_cast_button_disconnected;
        goto L9
    L7:
        r02 = androidx.mediarouter.R.string.mr_cast_button_connected;
        goto L9
    }

    @Override // android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mRemoteIndicator == null) goto L20;
        int[] r0 = getDrawableState();
        this.mRemoteIndicator.setState(r0);
        if ((this.mRemoteIndicator.getCurrent() instanceof AnimationDrawable) == false) goto L19;
        AnimationDrawable r02 = (AnimationDrawable) this.mRemoteIndicator.getCurrent();
        int r1 = this.mConnectionState;
        if (r1 == 1) goto L17;
        if (this.mLastConnectionState != r1) goto L17;
        if (r1 != 2) goto L19;
        if (r02.isRunning() == true) goto L19;
        r02.selectDrawable(r02.getNumberOfFrames() - 1);
    L17:
        if (r02.isRunning() == true) goto L19;
        r02.start();
    L19:
        invalidate();
    L20:
        this.mLastConnectionState = this.mConnectionState;
    }

    @Deprecated
    public void enableDynamicGroup() {
        MediaRouterParams r0 = this.mRouter.getRouterParams();
        if (r0 != null) goto L5;
        MediaRouterParams.Builder r02 = new MediaRouterParams.Builder();
    L6:
        r02.setDialogType(2);
        this.mRouter.setRouterParams(r02.build());
        return;
    L5:
        r02 = new MediaRouterParams.Builder(r0);
        goto L6
    }

    public MediaRouteDialogFactory getDialogFactory() {
        return this.mDialogFactory;
    }

    public MediaRouteSelector getRouteSelector() {
        return this.mSelector;
    }

    @Override // android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable r0 = this.mRemoteIndicator;
        if (r0 == null) goto L6;
        r0.jumpToCurrentState();
        return;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isInEditMode() == false) goto L5;
        return;
    L5:
        this.mAttachedToWindow = true;
        if (this.mSelector.isEmpty() == true) goto L8;
        this.mRouter.addCallback(this.mSelector, this.mCallback);
    L8:
        refreshRoute();
    }

    @Override // android.view.View
    public int[] onCreateDrawableState(int r3) {
        int[] r32 = super.onCreateDrawableState(r3 + 1);
        if (this.mRouter != null) goto L6;
    L12:
        return r32;
    L6:
        if (this.mIsFixedIcon == true) goto L12;
        int r2 = this.mConnectionState;
        if (r2 != 1) goto L11;
        View.mergeDrawableStates(r32, CHECKABLE_STATE_SET);
        return r32;
    L11:
        if (r2 != 2) goto L12;
        View.mergeDrawableStates(r32, CHECKED_STATE_SET);
        return r32;
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        if (isInEditMode() == true) goto L7;
        this.mAttachedToWindow = false;
        if (this.mSelector.isEmpty() == true) goto L7;
        this.mRouter.removeCallback(this.mCallback);
    L7:
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas r7) {
        super.onDraw(r7);
        if (this.mRemoteIndicator == null) goto L6;
        int r0 = getPaddingLeft();
        int r1 = getWidth() - getPaddingRight();
        int r2 = getPaddingTop();
        int r3 = getHeight() - getPaddingBottom();
        int r4 = this.mRemoteIndicator.getIntrinsicWidth();
        int r5 = this.mRemoteIndicator.getIntrinsicHeight();
        int r02 = r0 + (((r1 - r0) - r4) / 2);
        int r22 = r2 + (((r3 - r2) - r5) / 2);
        this.mRemoteIndicator.setBounds(r02, r22, r4 + r02, r5 + r22);
        this.mRemoteIndicator.draw(r7);
        return;
    }

    @Override // android.view.View
    public void onMeasure(int r7, int r8) {
        int r0 = View.MeasureSpec.getSize(r7);
        int r1 = View.MeasureSpec.getSize(r8);
        int r72 = View.MeasureSpec.getMode(r7);
        int r82 = View.MeasureSpec.getMode(r8);
        int r2 = this.mMinWidth;
        Drawable r3 = this.mRemoteIndicator;
        int r4 = 0;
        if (r3 == null) goto L5;
        int r32 = (r3.getIntrinsicWidth() + getPaddingLeft()) + getPaddingRight();
    L6:
        int r22 = Math.max(r2, r32);
        int r33 = this.mMinHeight;
        Drawable r5 = this.mRemoteIndicator;
        if (r5 == null) goto L9;
        r4 = (r5.getIntrinsicHeight() + getPaddingTop()) + getPaddingBottom();
    L9:
        int r34 = Math.max(r33, r4);
        if (r72 == Integer.MIN_VALUE) goto L13;
        if (r72 == 1073741824) goto L14;
        r0 = r22;
    L14:
        if (r82 == Integer.MIN_VALUE) goto L17;
        if (r82 == 1073741824) goto L18;
        r1 = r34;
    L18:
        setMeasuredDimension(r0, r1);
        return;
    L17:
        r1 = Math.min(r1, r34);
        goto L18
    L13:
        r0 = Math.min(r0, r22);
        goto L14
    L5:
        r32 = 0;
        goto L6
    }

    @Override // android.view.View
    public boolean performClick() {
        boolean r0 = super.performClick();
        if (r0 == true) goto L5;
        playSoundEffect(0);
    L5:
        loadRemoteIndicatorIfNeeded();
        if (showDialog() == true) goto L10;
        if (r0 == true) goto L12;
        return false;
    L12:
        return true;
    L10:
        return true;
    }

    public void refreshRoute() {
        MediaRouter.RouteInfo r0 = this.mRouter.getSelectedRoute();
        if (r0.isDefaultOrBluetooth() == true) goto L5;
        int r02 = r0.getConnectionState();
    L7:
        if (this.mConnectionState == r02) goto L10;
        this.mConnectionState = r02;
        updateContentDescription();
        refreshDrawableState();
    L10:
        if (r02 != 1) goto L13;
        loadRemoteIndicatorIfNeeded();
        return;
    L13:
        return;
    L5:
        r02 = 0;
        goto L7
    }

    @Deprecated
    public void setAlwaysVisible(boolean r1) {
    }

    public void setCheatSheetEnabled(boolean r2) {
        if (r2 == this.mCheatSheetEnabled) goto L6;
        this.mCheatSheetEnabled = r2;
        updateContentDescription();
        return;
    }

    public void setDialogFactory(MediaRouteDialogFactory r1) {
        if (r1 == null) goto L5;
        this.mDialogFactory = r1;
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("factory must not be null");
    }

    public void setRemoteIndicatorDrawable(Drawable r2) {
        this.mRemoteIndicatorResIdToLoad = 0;
        setRemoteIndicatorDrawableInternal(r2);
    }

    public void setRemoteIndicatorDrawableInternal(Drawable r4) {
        RemoteIndicatorLoader r0 = this.mRemoteIndicatorLoader;
        if (r0 == null) goto L5;
        r0.cancel(false);
    L5:
        Drawable r02 = this.mRemoteIndicator;
        if (r02 == null) goto L8;
        r02.setCallback(null);
        unscheduleDrawable(this.mRemoteIndicator);
    L8:
        if (r4 != null) goto L10;
    L17:
        this.mRemoteIndicator = r4;
        refreshDrawableState();
        return;
    L10:
        if (this.mButtonTint == null) goto L12;
        r4 = DrawableCompat.wrap(r4.mutate());
        DrawableCompat.setTintList(r4, this.mButtonTint);
    L12:
        r4.setCallback(this);
        r4.setState(getDrawableState());
        if (getVisibility() != 0) goto L15;
        boolean r03 = true;
    L16:
        r4.setVisible(r03, false);
        goto L17
    L15:
        r03 = false;
        goto L16
    }

    public void setRouteSelector(MediaRouteSelector r3) {
        if (r3 != null) goto L4;
        f$$ExternalSyntheticBUOutline1.m("selector must not be null");
        return;
    L4:
        if (this.mSelector.equals(r3) == false) goto L6;
        return;
    L6:
        if (this.mAttachedToWindow == true) goto L8;
    L13:
        this.mSelector = r3;
        refreshRoute();
        return;
    L8:
        if (this.mSelector.isEmpty() == true) goto L11;
        this.mRouter.removeCallback(this.mCallback);
    L11:
        if (r3.isEmpty() == true) goto L13;
        this.mRouter.addCallback(r3, this.mCallback);
        goto L13
    }

    @Override // android.view.View
    public void setVisibility(int r2) {
        super.setVisibility(r2);
        Drawable r1 = this.mRemoteIndicator;
        if (r1 != null) goto L5;
        return;
    L5:
        if (r2 != 0) goto L7;
        boolean r22 = true;
    L8:
        r1.setVisible(r22, false);
        return;
    L7:
        r22 = false;
        goto L8
    }

    public boolean showDialog() {
        if (this.mAttachedToWindow == true) goto L6;
        return false;
    L6:
        MediaRouterParams r0 = this.mRouter.getRouterParams();
        if (r0 == null) goto L18;
        if (r0.isOutputSwitcherEnabled() == false) goto L16;
        if (MediaRouter.isMediaTransferEnabled() == false) goto L16;
        if (SystemOutputSwitcherDialogController.showDialog(getContext()) == false) goto L16;
        return true;
    L16:
        return showDialogForType(r0.getDialogType());
    L18:
        return showDialogForType(1);
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable r2) {
        if (super.verifyDrawable(r2) == false) goto L5;
        return true;
    L5:
        if (r2 == this.mRemoteIndicator) goto L11;
        return false;
    L11:
        return true;
    }
}
