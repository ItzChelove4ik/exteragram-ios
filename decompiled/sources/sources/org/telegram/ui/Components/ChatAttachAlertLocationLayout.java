package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.text.TextUtils;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.android.dx.io.Opcodes;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.IMapsProvider;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.LocationController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.utils.ViewOutlineProviderImpl;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Adapters.LocationActivityAdapter;
import org.telegram.ui.Adapters.LocationActivitySearchAdapter;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.LocationCell;
import org.telegram.ui.Cells.LocationDirectionCell;
import org.telegram.ui.Cells.LocationLoadingCell;
import org.telegram.ui.Cells.LocationPoweredCell;
import org.telegram.ui.Cells.SendLocationCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.SharingLiveLocationCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public class ChatAttachAlertLocationLayout extends ChatAttachAlert.AttachAlertLayout implements NotificationCenter.NotificationCenterDelegate {
    private LocationActivityAdapter adapter;
    private AnimatorSet animatorSet;
    private boolean askedForLocation;
    private Paint backgroundPaint;
    private Bitmap[] bitmapCache;
    private boolean checkBackgroundPermission;
    private boolean checkGpsEnabled;
    private boolean checkPermission;
    private int clipSize;
    private boolean currentMapStyleDark;
    private LocationActivityDelegate delegate;
    private long dialogId;
    private boolean doNotDrawMap;
    private ImageView emptyImageView;
    private TextView emptySubtitleTextView;
    private TextView emptyTitleTextView;
    private LinearLayout emptyView;
    private boolean first;
    private boolean firstFocus;
    private boolean firstWas;
    private IMapsProvider.ICameraUpdate forceUpdate;
    private boolean ignoreIdleCamera;
    private boolean isFirstLocation;
    private IMapsProvider.IMarker lastPressedMarker;
    private FrameLayout lastPressedMarkerView;
    private VenueLocation lastPressedVenue;
    private FillLastLinearLayoutManager layoutManager;
    private RecyclerListView listView;
    private View loadingMapView;
    private ImageView locationButton;
    private boolean locationDenied;
    private int locationType;
    private IMapsProvider.IMap map;
    private int mapHeight;
    private ActionBarMenuItem mapTypeButton;
    private IMapsProvider.IMapView mapView;
    private FrameLayout mapViewClip;
    private boolean mapsInitialized;
    private ImageView markerImageView;
    private int markerTop;
    private Location myLocation;
    private int nonClipSize;
    private boolean onResumeCalled;
    private ActionBarMenuItem otherItem;
    private int overScrollHeight;
    private MapOverlayView overlayView;
    private ArrayList<VenueLocation> placeMarkers;
    private boolean scrolling;
    private LocationActivitySearchAdapter searchAdapter;
    private SearchButton searchAreaButton;
    private boolean searchInProgress;
    private ActionBarMenuItem searchItem;
    private RecyclerListView searchListView;
    private boolean searchWas;
    private boolean searchedForCustomLocations;
    private boolean searching;
    private Location userLocation;
    private boolean userLocationMoved;
    private float yOffset;

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertLocationLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        final /* synthetic */ ChatAttachAlertLocationLayout this$0;

        public AnonymousClass1(ChatAttachAlertLocationLayout r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchCollapse() {
            ChatAttachAlertLocationLayout.m9675$$Nest$fputsearching(this.this$0, false);
            ChatAttachAlertLocationLayout.m9674$$Nest$fputsearchWas(this.this$0, false);
            ChatAttachAlertLocationLayout.m9661$$Nest$fgetsearchAdapter(this.this$0).searchDelayed(null, null);
            ChatAttachAlertLocationLayout.m9680$$Nest$mupdateEmptyView(this.this$0);
            if (ChatAttachAlertLocationLayout.m9657$$Nest$fgetotherItem(this.this$0) == null) goto L5;
            ChatAttachAlertLocationLayout.m9657$$Nest$fgetotherItem(this.this$0).setVisibility(0);
        L5:
            ChatAttachAlertLocationLayout.m9652$$Nest$fgetlistView(this.this$0).setVisibility(0);
            ChatAttachAlertLocationLayout.m9656$$Nest$fgetmapViewClip(this.this$0).setVisibility(0);
            ChatAttachAlertLocationLayout.m9663$$Nest$fgetsearchListView(this.this$0).setVisibility(8);
            ChatAttachAlertLocationLayout.m9647$$Nest$fgetemptyView(this.this$0).setVisibility(8);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
            ChatAttachAlertLocationLayout.m9675$$Nest$fputsearching(this.this$0, true);
            ChatAttachAlertLocationLayout r2 = this.this$0;
            r2.parentAlert.makeFocusable(ChatAttachAlertLocationLayout.m9662$$Nest$fgetsearchItem(r2).getSearchField(), true);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r5) {
            if (ChatAttachAlertLocationLayout.m9661$$Nest$fgetsearchAdapter(this.this$0) != null) goto L5;
            return;
        L5:
            String r52 = r5.getText().toString();
            int r0 = r52.length();
            ChatAttachAlertLocationLayout r1 = this.this$0;
            if (r0 == 0) goto L15;
            ChatAttachAlertLocationLayout.m9674$$Nest$fputsearchWas(r1, true);
            ChatAttachAlertLocationLayout.m9662$$Nest$fgetsearchItem(this.this$0).setShowSearchProgress(true);
            if (ChatAttachAlertLocationLayout.m9657$$Nest$fgetotherItem(this.this$0) == null) goto L10;
            ChatAttachAlertLocationLayout.m9657$$Nest$fgetotherItem(this.this$0).setVisibility(8);
        L10:
            ChatAttachAlertLocationLayout.m9652$$Nest$fgetlistView(this.this$0).setVisibility(8);
            ChatAttachAlertLocationLayout.m9656$$Nest$fgetmapViewClip(this.this$0).setVisibility(8);
            if (ChatAttachAlertLocationLayout.m9663$$Nest$fgetsearchListView(this.this$0).getAdapter() == ChatAttachAlertLocationLayout.m9661$$Nest$fgetsearchAdapter(this.this$0)) goto L13;
            ChatAttachAlertLocationLayout.m9663$$Nest$fgetsearchListView(this.this$0).setAdapter(ChatAttachAlertLocationLayout.m9661$$Nest$fgetsearchAdapter(this.this$0));
        L13:
            ChatAttachAlertLocationLayout.m9663$$Nest$fgetsearchListView(this.this$0).setVisibility(0);
            ChatAttachAlertLocationLayout r02 = this.this$0;
            ChatAttachAlertLocationLayout.m9673$$Nest$fputsearchInProgress(r02, ChatAttachAlertLocationLayout.m9661$$Nest$fgetsearchAdapter(r02).isEmpty());
            ChatAttachAlertLocationLayout.m9680$$Nest$mupdateEmptyView(this.this$0);
        L18:
            ChatAttachAlertLocationLayout.m9661$$Nest$fgetsearchAdapter(this.this$0).searchDelayed(r52, ChatAttachAlertLocationLayout.m9666$$Nest$fgetuserLocation(this.this$0));
            return;
        L15:
            if (ChatAttachAlertLocationLayout.m9657$$Nest$fgetotherItem(r1) == null) goto L17;
            ChatAttachAlertLocationLayout.m9657$$Nest$fgetotherItem(this.this$0).setVisibility(0);
        L17:
            ChatAttachAlertLocationLayout.m9652$$Nest$fgetlistView(this.this$0).setVisibility(0);
            ChatAttachAlertLocationLayout.m9656$$Nest$fgetmapViewClip(this.this$0).setVisibility(0);
            ChatAttachAlertLocationLayout.m9663$$Nest$fgetsearchListView(this.this$0).setAdapter(null);
            ChatAttachAlertLocationLayout.m9663$$Nest$fgetsearchListView(this.this$0).setVisibility(8);
            ChatAttachAlertLocationLayout.m9647$$Nest$fgetemptyView(this.this$0).setVisibility(8);
            goto L18
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertLocationLayout$2, reason: invalid class name */
    public class AnonymousClass2 extends FrameLayout {
        final /* synthetic */ ChatAttachAlertLocationLayout this$0;

        public AnonymousClass2(ChatAttachAlertLocationLayout r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r4) {
            if (r4.getY() <= (getMeasuredHeight() - ChatAttachAlertLocationLayout.m9643$$Nest$fgetclipSize(this.this$0))) goto L7;
            return false;
        L7:
            return super.dispatchTouchEvent(r4);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r4, View r5, long r6) {
            r4.save();
            int r0 = getMeasuredWidth();
            int r1 = getMeasuredHeight() - ChatAttachAlertLocationLayout.m9643$$Nest$fgetclipSize(this.this$0);
            boolean r2 = false;
            r4.clipRect(0, 0, r0, r1);
            if (ChatAttachAlertLocationLayout.m9645$$Nest$fgetdoNotDrawMap(this.this$0) == true) goto L6;
            r2 = super.drawChild(r4, r5, r6);
        L6:
            r4.restore();
            return r2;
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            ChatAttachAlertLocationLayout.m9642$$Nest$fgetbackgroundPaint(this.this$0).setColor(this.this$0.getThemedColor(Theme.key_dialogBackground));
            r8.drawRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight() - ChatAttachAlertLocationLayout.m9643$$Nest$fgetclipSize(this.this$0), ChatAttachAlertLocationLayout.m9642$$Nest$fgetbackgroundPaint(this.this$0));
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r4) {
            if (r4.getY() <= (getMeasuredHeight() - ChatAttachAlertLocationLayout.m9643$$Nest$fgetclipSize(this.this$0))) goto L7;
            return false;
        L7:
            return super.onInterceptTouchEvent(r4);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            if (ChatAttachAlertLocationLayout.m9659$$Nest$fgetoverlayView(this.this$0) == null) goto L6;
            ChatAttachAlertLocationLayout.m9659$$Nest$fgetoverlayView(this.this$0).updatePositions();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertLocationLayout$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerListView {
        final /* synthetic */ ChatAttachAlertLocationLayout this$0;

        public AnonymousClass3(ChatAttachAlertLocationLayout r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            ChatAttachAlertLocationLayout.m9679$$Nest$mupdateClipView(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertLocationLayout$4, reason: invalid class name */
    public class AnonymousClass4 extends FillLastLinearLayoutManager {
        final /* synthetic */ ChatAttachAlertLocationLayout this$0;

        /* renamed from: org.telegram.ui.Components.ChatAttachAlertLocationLayout$4$1, reason: invalid class name */
        public class AnonymousClass1 extends LinearSmoothScroller {
            final /* synthetic */ AnonymousClass4 this$1;

            public AnonymousClass1(AnonymousClass4 r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            public int calculateDyToMakeVisible(View r2, int r3) {
                return super.calculateDyToMakeVisible(r2, r3) - (ChatAttachAlertLocationLayout.m9652$$Nest$fgetlistView(this.this$1.this$0).getPaddingTop() - (ChatAttachAlertLocationLayout.m9655$$Nest$fgetmapHeight(this.this$1.this$0) - ChatAttachAlertLocationLayout.m9658$$Nest$fgetoverScrollHeight(this.this$1.this$0)));
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            public int calculateTimeForDeceleration(int r1) {
                return super.calculateTimeForDeceleration(r1) * 4;
            }
        }

        public AnonymousClass4(ChatAttachAlertLocationLayout r1, Context r2, int r3, boolean r4, int r5, RecyclerView r6) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void smoothScrollToPosition(RecyclerView r1, RecyclerView.State r2, int r3) {
            AnonymousClass1 r22 = new AnonymousClass1(this, r1.getContext());
            r22.setTargetPosition(r3);
            startSmoothScroll(r22);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertLocationLayout$5, reason: invalid class name */
    public class AnonymousClass5 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ChatAttachAlertLocationLayout this$0;

        public AnonymousClass5(ChatAttachAlertLocationLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r4, int r5) {
            ChatAttachAlertLocationLayout r42 = this.this$0;
            if (r5 == 0) goto L5;
            boolean r1 = true;
        L6:
            ChatAttachAlertLocationLayout.m9672$$Nest$fputscrolling(r42, r1);
            if (ChatAttachAlertLocationLayout.m9660$$Nest$fgetscrolling(this.this$0) == false) goto L9;
        L11:
            if (r5 != 0) goto L20;
            int r43 = AndroidUtilities.dp(13.0f);
            int r52 = this.this$0.parentAlert.getBackgroundPaddingTop();
            if ((((this.this$0.parentAlert.scrollOffsetY[0] - r52) - r43) + r52) >= ActionBar.getCurrentActionBarHeight()) goto L21;
            RecyclerListView.Holder r44 = (RecyclerListView.Holder) ChatAttachAlertLocationLayout.m9652$$Nest$fgetlistView(this.this$0).findViewHolderForAdapterPosition(0);
            if (r44 != null) goto L17;
            return;
        L17:
            if (r44.itemView.getTop() <= (ChatAttachAlertLocationLayout.m9655$$Nest$fgetmapHeight(this.this$0) - ChatAttachAlertLocationLayout.m9658$$Nest$fgetoverScrollHeight(this.this$0))) goto L23;
            ChatAttachAlertLocationLayout.m9652$$Nest$fgetlistView(this.this$0).smoothScrollBy(0, r44.itemView.getTop() - (ChatAttachAlertLocationLayout.m9655$$Nest$fgetmapHeight(this.this$0) - ChatAttachAlertLocationLayout.m9658$$Nest$fgetoverScrollHeight(this.this$0)));
            return;
        L23:
            return;
        L21:
            return;
        L20:
            return;
        L9:
            if (ChatAttachAlertLocationLayout.m9648$$Nest$fgetforceUpdate(this.this$0) == null) goto L11;
            ChatAttachAlertLocationLayout.m9668$$Nest$fputforceUpdate(this.this$0, null);
            goto L11
        L5:
            r1 = false;
            goto L6
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r2, int r3, int r4) {
            ChatAttachAlertLocationLayout.m9679$$Nest$mupdateClipView(this.this$0);
            if (ChatAttachAlertLocationLayout.m9648$$Nest$fgetforceUpdate(this.this$0) == null) goto L5;
            ChatAttachAlertLocationLayout r22 = this.this$0;
            ChatAttachAlertLocationLayout.m9676$$Nest$fputyOffset(r22, ChatAttachAlertLocationLayout.m9667$$Nest$fgetyOffset(r22) + r4);
        L5:
            ChatAttachAlertLocationLayout r1 = this.this$0;
            r1.parentAlert.updateLayout(r1, true, r4);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertLocationLayout$6, reason: invalid class name */
    public class AnonymousClass6 extends LocationActivitySearchAdapter {
        final /* synthetic */ ChatAttachAlertLocationLayout this$0;

        public AnonymousClass6(ChatAttachAlertLocationLayout r1, Context r2, Theme.ResourcesProvider r3, boolean r4, boolean r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            if (ChatAttachAlertLocationLayout.m9662$$Nest$fgetsearchItem(this.this$0) == null) goto L6;
            ChatAttachAlertLocationLayout.m9662$$Nest$fgetsearchItem(this.this$0).setShowSearchProgress(ChatAttachAlertLocationLayout.m9661$$Nest$fgetsearchAdapter(this.this$0).isSearching());
        L6:
            if (ChatAttachAlertLocationLayout.m9646$$Nest$fgetemptySubtitleTextView(this.this$0) == null) goto L8;
            ChatAttachAlertLocationLayout.m9646$$Nest$fgetemptySubtitleTextView(this.this$0).setText(AndroidUtilities.replaceTags(LocaleController.formatString("NoPlacesFoundInfo", R.string.NoPlacesFoundInfo, new Object[]{ChatAttachAlertLocationLayout.m9661$$Nest$fgetsearchAdapter(this.this$0).getLastSearchString()})));
        L8:
            super.notifyDataSetChanged();
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertLocationLayout$7, reason: invalid class name */
    public class AnonymousClass7 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ChatAttachAlertLocationLayout this$0;

        public AnonymousClass7(ChatAttachAlertLocationLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 == 1) goto L5;
            return;
        L5:
            if (ChatAttachAlertLocationLayout.m9665$$Nest$fgetsearching(this.this$0) == true) goto L7;
            return;
        L7:
            if (ChatAttachAlertLocationLayout.m9664$$Nest$fgetsearchWas(this.this$0) == false) goto L12;
            AndroidUtilities.hideKeyboard(this.this$0.parentAlert.getCurrentFocus());
            return;
        }
    }

    public interface LocationActivityDelegate {
        void didSelectLocation(TLRPC.MessageMedia r1, int r2, boolean r3, int r4, long r5);
    }

    public class MapOverlayView extends FrameLayout {
        final /* synthetic */ ChatAttachAlertLocationLayout this$0;
        private HashMap<IMapsProvider.IMarker, View> views;

        /* renamed from: org.telegram.ui.Components.ChatAttachAlertLocationLayout$MapOverlayView$1, reason: invalid class name */
        public class AnonymousClass1 implements ValueAnimator.AnimatorUpdateListener {
            private final float[] animatorValues;
            private boolean startedInner;
            final /* synthetic */ MapOverlayView this$1;
            final /* synthetic */ FrameLayout val$iconLayout;

            public AnonymousClass1(MapOverlayView r1, FrameLayout r2) {
                this.this$1 = r1;
                this.val$iconLayout = r2;
                this.animatorValues = new float[]{0.0f, 1.0f};
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator r8) {
                float r82 = AndroidUtilities.lerp(this.animatorValues, r8.getAnimatedFraction());
                if (r82 < 0.7f) goto L10;
                if (this.startedInner == true) goto L10;
                if (ChatAttachAlertLocationLayout.m9650$$Nest$fgetlastPressedMarkerView(this.this$1.this$0) == null) goto L10;
                AnimatorSet r0 = new AnimatorSet();
                r0.playTogether(new Animator[]{ObjectAnimator.ofFloat(ChatAttachAlertLocationLayout.m9650$$Nest$fgetlastPressedMarkerView(this.this$1.this$0), View.SCALE_X, new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(ChatAttachAlertLocationLayout.m9650$$Nest$fgetlastPressedMarkerView(this.this$1.this$0), View.SCALE_Y, new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(ChatAttachAlertLocationLayout.m9650$$Nest$fgetlastPressedMarkerView(this.this$1.this$0), View.ALPHA, new float[]{0.0f, 1.0f})});
                r0.setInterpolator(new OvershootInterpolator(1.02f));
                r0.setDuration(250);
                r0.start();
                this.startedInner = true;
            L10:
                if (r82 > 0.5f) goto L13;
                float r83 = CubicBezierInterpolator.EASE_OUT.getInterpolation(r82 / 0.5f) * 1.1f;
            L16:
                this.val$iconLayout.setScaleX(r83);
                this.val$iconLayout.setScaleY(r83);
                return;
            L13:
                if (r82 > 0.75f) goto L15;
                r83 = 1.1f - (CubicBezierInterpolator.EASE_OUT.getInterpolation((r82 - 0.5f) / 0.25f) * 0.2f);
                goto L16
            L15:
                r83 = (CubicBezierInterpolator.EASE_OUT.getInterpolation((r82 - 0.75f) / 0.25f) * 0.1f) + 0.9f;
                goto L16
            }
        }

        /* renamed from: $r8$lambda$A6mSoeXDH8LyXuPa9k8WWUTOp-U, reason: not valid java name */
        public static /* synthetic */ void m9681$r8$lambda$A6mSoeXDH8LyXuPa9k8WWUTOpU(MapOverlayView r0, VenueLocation r1, boolean r2, int r3, int r4) {
            r0.lambda$addInfoView$0(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$BXuscDL4ExRiXZYbOWPC3YD5W3g(MapOverlayView r0, VenueLocation r1, View r2) {
            r0.lambda$addInfoView$2(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$eV3wqnoxyc3VTztgi9E6OkxiIBU(MapOverlayView r0, VenueLocation r1, Long r2) {
            r0.lambda$addInfoView$1(r1, r2);
        }

        public MapOverlayView(ChatAttachAlertLocationLayout r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.views = new HashMap();
        }

        private /* synthetic */ void lambda$addInfoView$0(VenueLocation r8, boolean r9, int r10, int r11) {
            ChatAttachAlertLocationLayout.m9644$$Nest$fgetdelegate(this.this$0).didSelectLocation(r8.venue, ChatAttachAlertLocationLayout.m9653$$Nest$fgetlocationType(this.this$0), r9, r10, 0);
            this.this$0.parentAlert.dismiss(true);
        }

        private /* synthetic */ void lambda$addInfoView$1(VenueLocation r9, Long r10) {
            ChatAttachAlertLocationLayout.m9644$$Nest$fgetdelegate(this.this$0).didSelectLocation(r9.venue, ChatAttachAlertLocationLayout.m9653$$Nest$fgetlocationType(this.this$0), true, 0, r10.longValue());
            this.this$0.parentAlert.dismiss(true);
        }

        private /* synthetic */ void lambda$addInfoView$2(VenueLocation r5, View r6) {
            ChatActivity r62 = (ChatActivity) this.this$0.parentAlert.baseFragment;
            boolean r0 = r62.isInScheduleMode();
            ChatAttachAlertLocationLayout r1 = this.this$0;
            if (r0 == false) goto L6;
            AlertsCreator.createScheduleDatePickerDialog(ChatAttachAlertLocationLayout.m9677$$Nest$mgetParentActivity(r1), r62.getDialogId(), new ChatAttachAlertLocationLayout$MapOverlayView$$ExternalSyntheticLambda0(this, r5), this.this$0.resourcesProvider);
            return;
        L6:
            ChatAttachAlert r63 = r1.parentAlert;
            AlertsCreator.ensurePaidMessageConfirmation(r63.currentAccount, r63.getDialogId(), this.this$0.parentAlert.getAdditionalMessagesCount() + 1, new ChatAttachAlertLocationLayout$MapOverlayView$$ExternalSyntheticLambda1(this, r5));
        }

        public void addInfoView(IMapsProvider.IMarker r21) {
            VenueLocation r2 = (VenueLocation) r21.getTag();
            if (ChatAttachAlertLocationLayout.m9651$$Nest$fgetlastPressedVenue(this.this$0) != r2) goto L5;
            return;
        L5:
            ChatAttachAlertLocationLayout.m9678$$Nest$mshowSearchPlacesButton(this.this$0, false);
            if (ChatAttachAlertLocationLayout.m9649$$Nest$fgetlastPressedMarker(this.this$0) == null) goto L8;
            removeInfoView(ChatAttachAlertLocationLayout.m9649$$Nest$fgetlastPressedMarker(this.this$0));
            ChatAttachAlertLocationLayout.m9669$$Nest$fputlastPressedMarker(this.this$0, null);
        L8:
            ChatAttachAlertLocationLayout.m9671$$Nest$fputlastPressedVenue(this.this$0, r2);
            ChatAttachAlertLocationLayout.m9669$$Nest$fputlastPressedMarker(this.this$0, r21);
            Context r3 = getContext();
            FrameLayout r5 = new FrameLayout(r3);
            addView(r5, LayoutHelper.createFrame(-2, 114.0f));
            ChatAttachAlertLocationLayout.m9670$$Nest$fputlastPressedMarkerView(this.this$0, new FrameLayout(r3));
            ChatAttachAlertLocationLayout.m9650$$Nest$fgetlastPressedMarkerView(this.this$0).setBackgroundResource(R.drawable.venue_tooltip);
            ChatAttachAlertLocationLayout.m9650$$Nest$fgetlastPressedMarkerView(this.this$0).getBackground().setColorFilter(new PorterDuffColorFilter(this.this$0.getThemedColor(Theme.key_dialogBackground), PorterDuff.Mode.MULTIPLY));
            r5.addView(ChatAttachAlertLocationLayout.m9650$$Nest$fgetlastPressedMarkerView(this.this$0), LayoutHelper.createFrame(-2, 71.0f));
            ChatAttachAlertLocationLayout.m9650$$Nest$fgetlastPressedMarkerView(this.this$0).setAlpha(0.0f);
            ChatAttachAlertLocationLayout.m9650$$Nest$fgetlastPressedMarkerView(this.this$0).setOnClickListener(new ChatAttachAlertLocationLayout$MapOverlayView$$ExternalSyntheticLambda2(this, r2));
            TextView r6 = new TextView(r3);
            r6.setTextSize(1, 16.0f);
            r6.setMaxLines(1);
            TextUtils.TruncateAt r7 = TextUtils.TruncateAt.END;
            r6.setEllipsize(r7);
            r6.setSingleLine(true);
            r6.setTextColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
            r6.setTypeface(AndroidUtilities.bold());
            int r10 = 3;
            if (LocaleController.isRTL == false) goto L11;
            int r9 = 5;
        L12:
            r6.setGravity(r9);
            FrameLayout r92 = ChatAttachAlertLocationLayout.m9650$$Nest$fgetlastPressedMarkerView(this.this$0);
            if (LocaleController.isRTL == false) goto L15;
            int r12 = 5;
        L16:
            r92.addView(r6, LayoutHelper.createFrame(-2, -2.0f, r12 | 48, 18.0f, 10.0f, 18.0f, 0.0f));
            TextView r93 = new TextView(r3);
            r93.setTextSize(1, 14.0f);
            r93.setMaxLines(1);
            r93.setEllipsize(r7);
            r93.setSingleLine(true);
            r93.setTextColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhiteGrayText3));
            if (LocaleController.isRTL == false) goto L19;
            int r72 = 5;
        L20:
            r93.setGravity(r72);
            FrameLayout r73 = ChatAttachAlertLocationLayout.m9650$$Nest$fgetlastPressedMarkerView(this.this$0);
            if (LocaleController.isRTL == false) goto L23;
            r10 = 5;
        L23:
            r73.addView(r93, LayoutHelper.createFrame(-2, -2.0f, r10 | 48, 18.0f, 32.0f, 18.0f, 0.0f));
            r6.setText(r2.venue.title);
            r93.setText(LocaleController.getString(R.string.TapToSendLocation));
            FrameLayout r62 = new FrameLayout(r3);
            r62.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(36.0f), LocationCell.getColorForIndex(r2.num)));
            r5.addView(r62, LayoutHelper.createFrame(36, 36.0f, 81, 0.0f, 0.0f, 0.0f, 4.0f));
            BackupImageView r74 = new BackupImageView(r3);
            r74.setImage("https://ss3.4sqi.net/img/categories_v2/" + r2.venue.venue_type + "_64.png", null, null);
            r62.addView(r74, LayoutHelper.createFrame(30, 30, 17));
            ValueAnimator r22 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            r22.addUpdateListener(new AnonymousClass1(this, r62));
            r22.setDuration(360);
            r22.start();
            this.views.put(r21, r5);
            ChatAttachAlertLocationLayout.m9654$$Nest$fgetmap(this.this$0).animateCamera(ApplicationLoader.getMapsProvider().newCameraUpdateLatLng(r21.getPosition()), 300, null);
            return;
        L19:
            r72 = 3;
            goto L20
        L15:
            r12 = 3;
            goto L16
        L11:
            r9 = 3;
            goto L12
        }

        public void removeInfoView(IMapsProvider.IMarker r2) {
            View r0 = this.views.get(r2);
            if (r0 == null) goto L6;
            removeView(r0);
            this.views.remove(r2);
            return;
        }

        public void updatePositions() {
            if (ChatAttachAlertLocationLayout.m9654$$Nest$fgetmap(this.this$0) == null) goto L9;
            IMapsProvider.IProjection r0 = ChatAttachAlertLocationLayout.m9654$$Nest$fgetmap(this.this$0).getProjection();
            Iterator<Map.Entry<IMapsProvider.IMarker, View>> r5 = this.views.entrySet().iterator();
        L7:
            if (r5.hasNext() == false) goto L11;
            Map.Entry<IMapsProvider.IMarker, View> r1 = r5.next();
            IMapsProvider.IMarker r2 = r1.getKey();
            View r12 = r1.getValue();
            Point r22 = r0.toScreenLocation(r2.getPosition());
            r12.setTranslationX(r22.x - (r12.getMeasuredWidth() / 2));
            r12.setTranslationY((r22.y - r12.getMeasuredHeight()) + AndroidUtilities.dp(22.0f));
            goto L7
        L11:
            return;
        }
    }

    public static class SearchButton extends TextView {
        private float additionanTranslationY;
        private float currentTranslationY;

        public SearchButton(Context r1) {
            super(r1);
        }

        private void updateTranslationY() {
            setTranslationY(this.currentTranslationY + this.additionanTranslationY);
        }

        @Override // android.view.View
        public float getTranslationX() {
            return this.additionanTranslationY;
        }

        public void setTranslation(float r1) {
            this.currentTranslationY = r1;
            updateTranslationY();
        }

        @Override // android.view.View
        public void setTranslationX(float r1) {
            this.additionanTranslationY = r1;
            updateTranslationY();
        }
    }

    public static class VenueLocation {
        public IMapsProvider.IMarker marker;
        public int num;
        public TLRPC.TL_messageMediaVenue venue;

        public VenueLocation() {
        }
    }

    /* renamed from: $r8$lambda$0uKlMUgNylfi_BjW-B5oRavDBQs, reason: not valid java name */
    public static /* synthetic */ boolean m9625$r8$lambda$0uKlMUgNylfi_BjWB5oRavDBQs(ChatAttachAlertLocationLayout r0, MotionEvent r1, IMapsProvider.ICallableMethod r2) {
        return r0.lambda$new$10(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$10xrJP4CBBunVJcogkklnvdyEJ0(ChatAttachAlertLocationLayout r0, int r1) {
        r0.lambda$new$2(r1);
    }

    /* renamed from: $r8$lambda$24aLXuWLg51MBUrsl-o5NrTdr80, reason: not valid java name */
    public static /* synthetic */ void m9626$r8$lambda$24aLXuWLg51MBUrslo5NrTdr80(ChatAttachAlertLocationLayout r0, TLRPC.TL_messageMediaVenue r1, boolean r2, int r3, int r4) {
        r0.lambda$new$18(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$61hgdZWxDMyX1MabO8X0m1AuOmA(ChatAttachAlertLocationLayout r0) {
        r0.lambda$positionMarker$29();
    }

    public static /* synthetic */ void $r8$lambda$6VI3OeINBZYqV_m2bF_ZO1SL94w(ChatAttachAlertLocationLayout r0, Location r1) {
        r0.lambda$onMapInit$24(r1);
    }

    public static /* synthetic */ void $r8$lambda$8wZBkAJBCs624bqWnPEFE_pBWkE(ChatAttachAlertLocationLayout r0, ChatActivity r1, Theme.ResourcesProvider r2, View r3, int r4) {
        r0.lambda$new$19(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$9Kpq-gmP6hy5tIaD-EOwTFhjsmo, reason: not valid java name */
    public static /* synthetic */ void m9627$r8$lambda$9KpqgmP6hy5tIaDEOwTFhjsmo(ChatAttachAlertLocationLayout r0, IMapsProvider.IMapView r1) {
        r0.lambda$new$15(r1);
    }

    public static /* synthetic */ void $r8$lambda$ACbiG_mZ8_JHmXaJn0Tdb1n4sIg(ChatAttachAlertLocationLayout r0, IMapsProvider.IMapView r1) {
        r0.lambda$new$16(r1);
    }

    public static /* synthetic */ void $r8$lambda$Cc9CNUyMmFaHm_fV_PKrXg03KkY(ChatAttachAlertLocationLayout r0) {
        r0.updateClipView();
    }

    /* renamed from: $r8$lambda$EFm6jAWE783NFtfrxjeX3O-eJls, reason: not valid java name */
    public static /* synthetic */ boolean m9628$r8$lambda$EFm6jAWE783NFtfrxjeX3OeJls(ChatAttachAlertLocationLayout r0, IMapsProvider.IMarker r1) {
        return r0.lambda$onMapInit$25(r1);
    }

    public static /* synthetic */ void $r8$lambda$Ix0deC2N2pU1eOZZ_gp900c8dlo(ChatAttachAlertLocationLayout r0, AlertDialog r1, int r2) {
        r0.lambda$onMapInit$28(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$JTTHtqiMfHrQVNPrn9YBUzNEE4c(ChatAttachAlertLocationLayout r0, float[] r1) {
        r0.lambda$positionMarker$31(r1);
    }

    public static /* synthetic */ void $r8$lambda$J_C7ZwBsPFRpr4plRzP4SMtoRhM(ChatAttachAlertLocationLayout r0) {
        r0.lambda$onMapInit$26();
    }

    /* renamed from: $r8$lambda$NTCfAX88wLiUF-DR0fp4KIrlbBQ, reason: not valid java name */
    public static /* synthetic */ void m9629$r8$lambda$NTCfAX88wLiUFDR0fp4KIrlbBQ(ChatAttachAlertLocationLayout r0, ChatActivity r1, Object r2, Theme.ResourcesProvider r3, Long r4) {
        r0.lambda$new$8(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$NV4s6CRnI_tkfHRJSUIsAp0RZ-4, reason: not valid java name */
    public static /* synthetic */ boolean m9630$r8$lambda$NV4s6CRnI_tkfHRJSUIsAp0RZ4(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$U43LLSKaG64PALNL5bPSOwl5zs4(ChatAttachAlertLocationLayout r0, ArrayList r1) {
        r0.updatePlacesMarkers(r1);
    }

    /* renamed from: $r8$lambda$U8ACH1_T51u4t6n-HxRzFj28wCY, reason: not valid java name */
    public static /* synthetic */ void m9631$r8$lambda$U8ACH1_T51u4t6nHxRzFj28wCY(ChatAttachAlertLocationLayout r0) {
        r0.lambda$onShow$33();
    }

    public static /* synthetic */ void $r8$lambda$WCeONHgleOnJEjD0ygYXI9U5Vbw(ChatAttachAlertLocationLayout r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$XkyMHlgF5J8dtumebWFvuEJGo7k(ChatAttachAlertLocationLayout r0) {
        r0.lambda$onMapInit$27();
    }

    /* renamed from: $r8$lambda$Ypb1NXoWWyGxig-6v9NamFoVADw, reason: not valid java name */
    public static /* synthetic */ void m9632$r8$lambda$Ypb1NXoWWyGxig6v9NamFoVADw(ChatAttachAlertLocationLayout r0, ChatActivity r1, TLRPC.TL_messageMediaGeo r2, Theme.ResourcesProvider r3, Long r4) {
        r0.lambda$new$6(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$b5ra_UHp19lcjxxjfAXic1nNJNw(ChatAttachAlertLocationLayout r0) {
        r0.lambda$new$12();
    }

    public static /* synthetic */ void $r8$lambda$biR_gk41diuDmFOyhjeAU5PhTeY(ChatAttachAlertLocationLayout r0) {
        r0.lambda$getThemeDescriptions$34();
    }

    /* renamed from: $r8$lambda$c_B2y0ZFhlkn-tZNIt5sGpG3FyQ, reason: not valid java name */
    public static /* synthetic */ void m9633$r8$lambda$c_B2y0ZFhlkntZNIt5sGpG3FyQ(ChatAttachAlertLocationLayout r0, int r1) {
        r0.lambda$openShareLiveLocation$21(r1);
    }

    public static /* synthetic */ void $r8$lambda$fGMi3g40e7yeE0M6aBMVhyrly2w(ChatAttachAlertLocationLayout r0, Object r1, boolean r2, int r3, int r4) {
        r0.lambda$new$7(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$gUpjtO_Ll77DLEWZ2DpRxShkyEI(ChatAttachAlertLocationLayout r0, ChatActivity r1, Theme.ResourcesProvider r2, View r3, int r4) {
        r0.lambda$new$9(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$gVzCNgQvtownV-pX5WQJ5-SEekU, reason: not valid java name */
    public static /* synthetic */ void m9634$r8$lambda$gVzCNgQvtownVpX5WQJ5SEekU(ChatAttachAlertLocationLayout r0, View r1) {
        r0.lambda$new$3(r1);
    }

    /* renamed from: $r8$lambda$gYAOmZJXm9xLwcgXFtr-CTBruxs, reason: not valid java name */
    public static /* synthetic */ void m9635$r8$lambda$gYAOmZJXm9xLwcgXFtrCTBruxs(ChatAttachAlertLocationLayout r0) {
        r0.lambda$new$13();
    }

    /* renamed from: $r8$lambda$mGsqhoU8p7H-N6p6ewG_Juq16WU, reason: not valid java name */
    public static /* synthetic */ void m9636$r8$lambda$mGsqhoU8p7HN6p6ewG_Juq16WU(ChatAttachAlertLocationLayout r0, int r1, Long r2) {
        r0.lambda$openShareLiveLocation$20(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$mytyMpzs4YEbi3RjGZmffrPPDv4(ChatAttachAlertLocationLayout r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$nO9V0sNlF_CWvKB457wJ6igpmAQ(ChatAttachAlertLocationLayout r0) {
        r0.lambda$positionMarker$32();
    }

    /* renamed from: $r8$lambda$nrKf-JcufyE6jCuFN3eG7eDyrAc, reason: not valid java name */
    public static /* synthetic */ void m9637$r8$lambda$nrKfJcufyE6jCuFN3eG7eDyrAc(ChatAttachAlertLocationLayout r0, TLRPC.TL_messageMediaGeo r1, Long r2, boolean r3, int r4, int r5) {
        r0.lambda$new$5(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$otikd_UDkTDqukUexH-ZGJa56cw, reason: not valid java name */
    public static /* synthetic */ void m9638$r8$lambda$otikd_UDkTDqukUexHZGJa56cw(ChatAttachAlertLocationLayout r0, double r1, double r3) {
        r0.lambda$positionMarker$30(r1, r3);
    }

    /* renamed from: $r8$lambda$pR6II3W-iF3JdNDHvhIO6RhCGy0, reason: not valid java name */
    public static /* synthetic */ void m9639$r8$lambda$pR6II3WiF3JdNDHvhIO6RhCGy0(ChatAttachAlertLocationLayout r0) {
        r0.lambda$onMapInit$23();
    }

    public static /* synthetic */ void $r8$lambda$tDGsq26QyDVls7jwXjG2epcpSUI(ChatAttachAlertLocationLayout r0, int r1) {
        r0.lambda$onMapInit$22(r1);
    }

    public static /* synthetic */ void $r8$lambda$uPM1bO6U7Y9Q9T1EDPr2q_DMtxY(ChatAttachAlertLocationLayout r0, ArrayList r1) {
        r0.lambda$new$17(r1);
    }

    /* renamed from: $r8$lambda$v6UqXuKZ-pZ_bklGxJrtOw9ZsZ8, reason: not valid java name */
    public static /* synthetic */ void m9640$r8$lambda$v6UqXuKZpZ_bklGxJrtOw9ZsZ8(ChatAttachAlertLocationLayout r0, IMapsProvider.IMap r1) {
        r0.lambda$new$14(r1);
    }

    /* renamed from: $r8$lambda$zLtF-gSlCW52Ht8XOjF2FgI43kk, reason: not valid java name */
    public static /* synthetic */ boolean m9641$r8$lambda$zLtFgSlCW52Ht8XOjF2FgI43kk(ChatAttachAlertLocationLayout r0, MotionEvent r1, IMapsProvider.ICallableMethod r2) {
        return r0.lambda$new$11(r1, r2);
    }

    /* renamed from: -$$Nest$fgetbackgroundPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m9642$$Nest$fgetbackgroundPaint(ChatAttachAlertLocationLayout r0) {
        return r0.backgroundPaint;
    }

    /* renamed from: -$$Nest$fgetclipSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9643$$Nest$fgetclipSize(ChatAttachAlertLocationLayout r0) {
        return r0.clipSize;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LocationActivityDelegate m9644$$Nest$fgetdelegate(ChatAttachAlertLocationLayout r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetdoNotDrawMap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9645$$Nest$fgetdoNotDrawMap(ChatAttachAlertLocationLayout r0) {
        return r0.doNotDrawMap;
    }

    /* renamed from: -$$Nest$fgetemptySubtitleTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m9646$$Nest$fgetemptySubtitleTextView(ChatAttachAlertLocationLayout r0) {
        return r0.emptySubtitleTextView;
    }

    /* renamed from: -$$Nest$fgetemptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m9647$$Nest$fgetemptyView(ChatAttachAlertLocationLayout r0) {
        return r0.emptyView;
    }

    /* renamed from: -$$Nest$fgetforceUpdate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ IMapsProvider.ICameraUpdate m9648$$Nest$fgetforceUpdate(ChatAttachAlertLocationLayout r0) {
        return r0.forceUpdate;
    }

    /* renamed from: -$$Nest$fgetlastPressedMarker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ IMapsProvider.IMarker m9649$$Nest$fgetlastPressedMarker(ChatAttachAlertLocationLayout r0) {
        return r0.lastPressedMarker;
    }

    /* renamed from: -$$Nest$fgetlastPressedMarkerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m9650$$Nest$fgetlastPressedMarkerView(ChatAttachAlertLocationLayout r0) {
        return r0.lastPressedMarkerView;
    }

    /* renamed from: -$$Nest$fgetlastPressedVenue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VenueLocation m9651$$Nest$fgetlastPressedVenue(ChatAttachAlertLocationLayout r0) {
        return r0.lastPressedVenue;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m9652$$Nest$fgetlistView(ChatAttachAlertLocationLayout r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetlocationType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9653$$Nest$fgetlocationType(ChatAttachAlertLocationLayout r0) {
        return r0.locationType;
    }

    /* renamed from: -$$Nest$fgetmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ IMapsProvider.IMap m9654$$Nest$fgetmap(ChatAttachAlertLocationLayout r0) {
        return r0.map;
    }

    /* renamed from: -$$Nest$fgetmapHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9655$$Nest$fgetmapHeight(ChatAttachAlertLocationLayout r0) {
        return r0.mapHeight;
    }

    /* renamed from: -$$Nest$fgetmapViewClip, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m9656$$Nest$fgetmapViewClip(ChatAttachAlertLocationLayout r0) {
        return r0.mapViewClip;
    }

    /* renamed from: -$$Nest$fgetotherItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m9657$$Nest$fgetotherItem(ChatAttachAlertLocationLayout r0) {
        return r0.otherItem;
    }

    /* renamed from: -$$Nest$fgetoverScrollHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9658$$Nest$fgetoverScrollHeight(ChatAttachAlertLocationLayout r0) {
        return r0.overScrollHeight;
    }

    /* renamed from: -$$Nest$fgetoverlayView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MapOverlayView m9659$$Nest$fgetoverlayView(ChatAttachAlertLocationLayout r0) {
        return r0.overlayView;
    }

    /* renamed from: -$$Nest$fgetscrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9660$$Nest$fgetscrolling(ChatAttachAlertLocationLayout r0) {
        return r0.scrolling;
    }

    /* renamed from: -$$Nest$fgetsearchAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LocationActivitySearchAdapter m9661$$Nest$fgetsearchAdapter(ChatAttachAlertLocationLayout r0) {
        return r0.searchAdapter;
    }

    /* renamed from: -$$Nest$fgetsearchItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m9662$$Nest$fgetsearchItem(ChatAttachAlertLocationLayout r0) {
        return r0.searchItem;
    }

    /* renamed from: -$$Nest$fgetsearchListView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m9663$$Nest$fgetsearchListView(ChatAttachAlertLocationLayout r0) {
        return r0.searchListView;
    }

    /* renamed from: -$$Nest$fgetsearchWas, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9664$$Nest$fgetsearchWas(ChatAttachAlertLocationLayout r0) {
        return r0.searchWas;
    }

    /* renamed from: -$$Nest$fgetsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9665$$Nest$fgetsearching(ChatAttachAlertLocationLayout r0) {
        return r0.searching;
    }

    /* renamed from: -$$Nest$fgetuserLocation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Location m9666$$Nest$fgetuserLocation(ChatAttachAlertLocationLayout r0) {
        return r0.userLocation;
    }

    /* renamed from: -$$Nest$fgetyOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m9667$$Nest$fgetyOffset(ChatAttachAlertLocationLayout r0) {
        return r0.yOffset;
    }

    /* renamed from: -$$Nest$fputforceUpdate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9668$$Nest$fputforceUpdate(ChatAttachAlertLocationLayout r0, IMapsProvider.ICameraUpdate r1) {
        r0.forceUpdate = r1;
    }

    /* renamed from: -$$Nest$fputlastPressedMarker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9669$$Nest$fputlastPressedMarker(ChatAttachAlertLocationLayout r0, IMapsProvider.IMarker r1) {
        r0.lastPressedMarker = r1;
    }

    /* renamed from: -$$Nest$fputlastPressedMarkerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9670$$Nest$fputlastPressedMarkerView(ChatAttachAlertLocationLayout r0, FrameLayout r1) {
        r0.lastPressedMarkerView = r1;
    }

    /* renamed from: -$$Nest$fputlastPressedVenue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9671$$Nest$fputlastPressedVenue(ChatAttachAlertLocationLayout r0, VenueLocation r1) {
        r0.lastPressedVenue = r1;
    }

    /* renamed from: -$$Nest$fputscrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9672$$Nest$fputscrolling(ChatAttachAlertLocationLayout r0, boolean r1) {
        r0.scrolling = r1;
    }

    /* renamed from: -$$Nest$fputsearchInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9673$$Nest$fputsearchInProgress(ChatAttachAlertLocationLayout r0, boolean r1) {
        r0.searchInProgress = r1;
    }

    /* renamed from: -$$Nest$fputsearchWas, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9674$$Nest$fputsearchWas(ChatAttachAlertLocationLayout r0, boolean r1) {
        r0.searchWas = r1;
    }

    /* renamed from: -$$Nest$fputsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9675$$Nest$fputsearching(ChatAttachAlertLocationLayout r0, boolean r1) {
        r0.searching = r1;
    }

    /* renamed from: -$$Nest$fputyOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9676$$Nest$fputyOffset(ChatAttachAlertLocationLayout r0, float r1) {
        r0.yOffset = r1;
    }

    /* renamed from: -$$Nest$mgetParentActivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Activity m9677$$Nest$mgetParentActivity(ChatAttachAlertLocationLayout r0) {
        return r0.getParentActivity();
    }

    /* renamed from: -$$Nest$mshowSearchPlacesButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9678$$Nest$mshowSearchPlacesButton(ChatAttachAlertLocationLayout r0, boolean r1) {
        r0.showSearchPlacesButton(r1);
    }

    /* renamed from: -$$Nest$mupdateClipView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9679$$Nest$mupdateClipView(ChatAttachAlertLocationLayout r0) {
        r0.updateClipView();
    }

    /* renamed from: -$$Nest$mupdateEmptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9680$$Nest$mupdateEmptyView(ChatAttachAlertLocationLayout r0) {
        r0.updateEmptyView();
    }

    public ChatAttachAlertLocationLayout(ChatAttachAlert r33, Context r34, Theme.ResourcesProvider r35, boolean r36) {
        super(r33, r34, r35);
        this.checkGpsEnabled = true;
        this.askedForLocation = false;
        this.locationDenied = false;
        this.isFirstLocation = true;
        this.firstFocus = true;
        this.backgroundPaint = new Paint();
        this.placeMarkers = new ArrayList();
        this.checkPermission = true;
        this.checkBackgroundPermission = true;
        int r0 = (AndroidUtilities.displaySize.x - ActionBar.getCurrentActionBarHeight()) - AndroidUtilities.dp(66.0f);
        this.overScrollHeight = r0;
        this.mapHeight = r0;
        this.first = true;
        this.bitmapCache = new Bitmap[7];
        AndroidUtilities.fixGoogleMapsBug();
        ChatAttachAlert r3 = this.parentAlert;
        BaseFragment r4 = r3.baseFragment;
        if ((r4 instanceof ChatActivity) == false) goto L5;
        ChatActivity r15 = (ChatActivity) r4;
    L6:
        this.dialogId = r3.getDialogId();
        ChatAttachAlert r32 = this.parentAlert;
        if (r32.isStoryLocationPicker == false) goto L10;
        this.locationType = 7;
    L22:
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.locationPermissionGranted);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.locationPermissionDenied);
        this.searchWas = false;
        this.searching = false;
        this.searchInProgress = false;
        LocationActivityAdapter r02 = this.adapter;
        if (r02 == null) goto L25;
        r02.destroy();
    L25:
        LocationActivitySearchAdapter r03 = this.searchAdapter;
        if (r03 == null) goto L29;
        r03.destroy();
    L29:
        if (getParentActivity() != null) goto L31;
    L33:
        boolean r04 = false;
    L34:
        this.locationDenied = r04;
        ActionBarMenu r05 = this.parentAlert.actionBar.createMenu();
        this.overlayView = new MapOverlayView(this, r34);
        ActionBarMenuItem r06 = r05.addItem(0, R.drawable.outline_header_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass1(this));
        this.searchItem = r06;
        if (this.locationDenied == false) goto L39;
        if (this.parentAlert.isStoryLocationPicker == true) goto L39;
    L40:
        int r37 = 8;
    L42:
        r06.setVisibility(r37);
        this.searchItem.setSearchFieldHint(LocaleController.getString(R.string.Search));
        this.searchItem.setContentDescription(LocaleController.getString(R.string.Search));
        EditTextBoldCursor r07 = this.searchItem.getSearchField();
        int r38 = Theme.key_dialogTextBlack;
        r07.setTextColor(getThemedColor(r38));
        r07.setCursorColor(getThemedColor(r38));
        r07.setHintTextColor(getThemedColor(Theme.key_chat_messagePanelHint));
        new FrameLayout.LayoutParams(-1, AndroidUtilities.dp(21.0f)).gravity = 83;
        AnonymousClass2 r08 = new AnonymousClass2(this, r34);
        this.mapViewClip = r08;
        r08.setWillNotDraw(false);
        View r09 = new View(r34);
        this.loadingMapView = r09;
        r09.setBackgroundDrawable(new MapPlaceholderDrawable());
        SearchButton r010 = new SearchButton(r34);
        this.searchAreaButton = r010;
        r010.setTranslationX(-AndroidUtilities.dp(80.0f));
        this.searchAreaButton.setVisibility(4);
        int r39 = AndroidUtilities.dp(40.0f);
        int r11 = Theme.key_location_actionBackground;
        int r42 = getThemedColor(r11);
        int r5 = Theme.key_location_actionPressedBackground;
        Drawable r310 = Theme.createSimpleSelectorRoundRectDrawable(r39, r42, getThemedColor(r5));
        ScaleStateListAnimator.apply(this.searchAreaButton);
        this.searchAreaButton.setTranslationZ(AndroidUtilities.dp(2.0f));
        SearchButton r43 = this.searchAreaButton;
        ViewOutlineProvider r6 = ViewOutlineProviderImpl.BOUNDS_OVAL;
        r43.setOutlineProvider(r6);
        this.searchAreaButton.setBackground(r310);
        SearchButton r311 = this.searchAreaButton;
        int r44 = Theme.key_location_actionActiveIcon;
        r311.setTextColor(getThemedColor(r44));
        this.searchAreaButton.setTextSize(1, 14.0f);
        this.searchAreaButton.setTypeface(AndroidUtilities.bold());
        this.searchAreaButton.setText(LocaleController.getString(R.string.PlacesInThisArea));
        this.searchAreaButton.setGravity(17);
        this.searchAreaButton.setPadding(AndroidUtilities.dp(20.0f), 0, AndroidUtilities.dp(20.0f), 0);
        this.mapViewClip.addView(this.searchAreaButton, LayoutHelper.createFrame(-2, 40.0f, 49, 80.0f, 12.0f, 80.0f, 0.0f));
        this.searchAreaButton.setOnClickListener(new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda5(this));
        ActionBarMenuItem r2 = new ActionBarMenuItem(r34, null, 0, getThemedColor(Theme.key_location_actionIcon), r35);
        this.mapTypeButton = r2;
        r2.setClickable(true);
        this.mapTypeButton.setSubMenuOpenSide(2);
        this.mapTypeButton.setAdditionalXOffset(AndroidUtilities.dp(10.0f));
        this.mapTypeButton.setAdditionalYOffset(-AndroidUtilities.dp(10.0f));
        this.mapTypeButton.addSubItem(2, R.drawable.msg_map, LocaleController.getString(R.string.Map), r35);
        this.mapTypeButton.addSubItem(3, R.drawable.msg_satellite, LocaleController.getString(R.string.Satellite), r35);
        this.mapTypeButton.addSubItem(4, R.drawable.msg_hybrid, LocaleController.getString(R.string.Hybrid), r35);
        this.mapTypeButton.setContentDescription(LocaleController.getString(R.string.AccDescrMoreOptions));
        Drawable r45 = Theme.createSimpleSelectorCircleDrawable(AndroidUtilities.dp(40.0f), getThemedColor(r11), getThemedColor(r5));
        ScaleStateListAnimator.apply(this.mapTypeButton);
        this.mapTypeButton.setTranslationZ(AndroidUtilities.dp(2.0f));
        this.mapTypeButton.setOutlineProvider(r6);
        this.mapTypeButton.setBackground(r45);
        this.mapTypeButton.setIcon(R.drawable.msg_map_type);
        this.mapViewClip.addView(this.mapTypeButton, LayoutHelper.createFrame(40, 40.0f, 53, 0.0f, 12.0f, 12.0f, 0.0f));
        this.mapTypeButton.setOnClickListener(new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda9(this));
        this.mapTypeButton.setDelegate(new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda10(this));
        this.locationButton = new ImageView(r34);
        Drawable r011 = Theme.createSimpleSelectorCircleDrawable(AndroidUtilities.dp(40.0f), getThemedColor(r11), getThemedColor(r5));
        ScaleStateListAnimator.apply(this.locationButton);
        this.locationButton.setTranslationZ(AndroidUtilities.dp(2.0f));
        this.locationButton.setOutlineProvider(r6);
        this.locationButton.setBackground(r011);
        this.locationButton.setImageResource(R.drawable.msg_current_location);
        this.locationButton.setScaleType(ImageView.ScaleType.CENTER);
        ImageView r012 = this.locationButton;
        int r52 = getThemedColor(r44);
        PorterDuff.Mode r62 = PorterDuff.Mode.MULTIPLY;
        r012.setColorFilter(new PorterDuffColorFilter(r52, r62));
        this.locationButton.setTag(Integer.valueOf(r44));
        this.locationButton.setContentDescription(LocaleController.getString(R.string.AccDescrMyLocation));
        this.mapViewClip.addView(this.locationButton, LayoutHelper.createFrame(40, 40.0f, 85, 0.0f, 0.0f, 12.0f, 12.0f));
        this.locationButton.setOnClickListener(new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda11(this));
        LinearLayout r013 = new LinearLayout(r34);
        this.emptyView = r013;
        r013.setOrientation(1);
        this.emptyView.setGravity(1);
        this.emptyView.setPadding(0, AndroidUtilities.dp(160.0f), 0, 0);
        this.emptyView.setVisibility(8);
        addView(this.emptyView, LayoutHelper.createFrame(-1, -1.0f));
        this.emptyView.setOnTouchListener(new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda12());
        ImageView r014 = new ImageView(r34);
        this.emptyImageView = r014;
        r014.setImageResource(R.drawable.location_empty);
        this.emptyImageView.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_dialogEmptyImage), r62));
        this.emptyView.addView(this.emptyImageView, LayoutHelper.createLinear(-2, -2));
        TextView r015 = new TextView(r34);
        this.emptyTitleTextView = r015;
        int r46 = Theme.key_dialogEmptyText;
        r015.setTextColor(getThemedColor(r46));
        this.emptyTitleTextView.setGravity(17);
        this.emptyTitleTextView.setTypeface(AndroidUtilities.bold());
        this.emptyTitleTextView.setTextSize(1, 17.0f);
        this.emptyTitleTextView.setText(LocaleController.getString(R.string.NoPlacesFound));
        this.emptyView.addView(this.emptyTitleTextView, LayoutHelper.createLinear(-2, -2, 17, 0, 11, 0, 0));
        TextView r016 = new TextView(r34);
        this.emptySubtitleTextView = r016;
        r016.setTextColor(getThemedColor(r46));
        this.emptySubtitleTextView.setGravity(17);
        this.emptySubtitleTextView.setTextSize(1, 15.0f);
        this.emptySubtitleTextView.setPadding(AndroidUtilities.dp(40.0f), 0, AndroidUtilities.dp(40.0f), 0);
        this.emptyView.addView(this.emptySubtitleTextView, LayoutHelper.createLinear(-2, -2, 17, 0, 6, 0, 0));
        AnonymousClass3 r017 = new AnonymousClass3(this, r34, r35);
        this.listView = r017;
        this.iBlur3Capture = r017;
        this.iBlur3CaptureView = r017;
        this.occupyNavigationBar = true;
        r017.setClipToPadding(false);
        RecyclerListView r018 = this.listView;
        int r47 = this.locationType;
        long r53 = this.dialogId;
        ChatAttachAlert r9 = this.parentAlert;
        LocationActivityAdapter r22 = new LocationActivityAdapter(r34, r47, r53, true, r35, r9.isStoryLocationPicker, false, r9.isBizLocationPicker);
        this.adapter = r22;
        r018.setAdapter(r22);
        LocationActivityAdapter r019 = this.adapter;
        ChatAttachAlert r23 = this.parentAlert;
        if (r23 != null) goto L45;
    L49:
        boolean r24 = false;
    L50:
        r019.isPollAttach = r24;
        DefaultItemAnimator r020 = new DefaultItemAnimator();
        r020.setDurations(350);
        r020.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r020.setDelayAnimations(false);
        r020.setSupportsChangeAnimations(false);
        this.listView.setItemAnimator(r020);
        this.adapter.setUpdateRunnable(new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda13(this));
        this.adapter.setMyLocationDenied(this.locationDenied, this.askedForLocation);
        this.listView.setVerticalScrollBarEnabled(false);
        this.listView.setSections();
        RecyclerListView r63 = this.listView;
        AnonymousClass4 r021 = new AnonymousClass4(this, r34, 1, false, 0, r63);
        this.layoutManager = r021;
        r63.setLayoutManager(r021);
        addView(this.listView, LayoutHelper.createFrame(-1, -1, 51));
        this.listView.setOnScrollListener(new AnonymousClass5(this));
        this.listView.setOnItemClickListener(new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda14(this, r15, r35));
        this.adapter.setDelegate(this.dialogId, new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda15(this));
        this.adapter.setOverScrollHeight(this.overScrollHeight + AndroidUtilities.dp(16.0f));
        addView(this.mapViewClip, LayoutHelper.createFrame(-1, -1, 51));
        IMapsProvider.IMapView r022 = ApplicationLoader.getMapsProvider().onCreateMapView(r34);
        this.mapView = r022;
        r022.setOnDispatchTouchEventInterceptor(new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda16(this));
        this.mapView.setOnInterceptTouchEventInterceptor(new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda17(this));
        new Thread(new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda6(this, this.mapView)).start();
        ImageView r023 = new ImageView(r34);
        this.markerImageView = r023;
        r023.setImageResource(R.drawable.map_pin2);
        this.mapViewClip.addView(this.markerImageView, LayoutHelper.createFrame(28, 48, 49));
        RecyclerListView r024 = new RecyclerListView(r34, r35);
        this.searchListView = r024;
        r024.setSections(true);
        this.searchListView.setClipToPadding(false);
        this.searchListView.setVisibility(8);
        this.searchListView.setLayoutManager(new LinearLayoutManager(r34, 1, false));
        ChatAttachAlert r312 = this.parentAlert;
        AnonymousClass6 r025 = new AnonymousClass6(this, r34, r35, r312.isStoryLocationPicker, r312.isBizLocationPicker);
        this.searchAdapter = r025;
        r025.setMyLocationDenied(this.locationDenied);
        this.searchAdapter.setDelegate(0, new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda7(this));
        this.searchListView.setItemAnimator(null);
        addView(this.searchListView, LayoutHelper.createFrame(-1, -1, 51));
        this.searchListView.setOnScrollListener(new AnonymousClass7(this));
        this.searchListView.setOnItemClickListener(new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda8(this, r15, r35));
        updateEmptyView();
        return;
    L45:
        if (r23.isPollAttach == false) goto L47;
    L48:
        r24 = true;
        goto L50
    L47:
        if (r23.isLocationPicker == false) goto L49;
    L39:
        if (this.parentAlert.isBizLocationPicker == true) goto L40;
        r37 = 0;
        goto L42
    L31:
        if (getParentActivity().checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) goto L33;
        r04 = true;
        goto L34
    L10:
        if (r32.isBizLocationPicker == false) goto L12;
        this.locationType = 8;
        goto L22
    L12:
        if (r36 == false) goto L21;
        if (r15 == null) goto L21;
        if (r15.getCurrentEncryptedChat() != null) goto L21;
        if (r15.isInScheduleMode() == true) goto L21;
        if (UserObject.isUserSelf(r15.getCurrentUser()) == true) goto L21;
        this.locationType = 1;
    L21:
        this.locationType = 0;
        goto L22
    L5:
        r15 = null;
        goto L6
    }

    private int buttonsHeight() {
        int r1 = AndroidUtilities.dp(66.0f);
        int r3 = this.locationType;
        if (r3 == 1) goto L11;
        if (r3 == 7) goto L11;
        if (r3 == 8) goto L11;
        return r1;
    L11:
        return r1 + AndroidUtilities.dp(66.0f);
    }

    private Bitmap createPlaceBitmap(int r9) {
        Bitmap r0 = this.bitmapCache[r9 % 7];
        if (r0 == null) goto L11;
        return r0;
    L11:
        Paint r1 = new Paint(1);     // Catch: Throwable -> L8
        r1.setColor(-1);     // Catch: Throwable -> L8
        Bitmap r2 = Bitmap.createBitmap(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L8
        Canvas r3 = new Canvas(r2);     // Catch: Throwable -> L8
        r3.drawCircle(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), r1);     // Catch: Throwable -> L8
        r1.setColor(LocationCell.getColorForIndex(r9));     // Catch: Throwable -> L8
        r3.drawCircle(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(5.0f), r1);     // Catch: Throwable -> L8
        r3.setBitmap(null);     // Catch: Throwable -> L8
        this.bitmapCache[r9 % 7] = r2;     // Catch: Throwable -> L8
        return r2;
    L8:
        th = move-exception;
        FileLog.e(th);
        return null;
    }

    private void fixLayoutInternal(boolean r4) {
        if (getMeasuredHeight() != 0) goto L5;
        return;
    L5:
        if (this.mapView == null) goto L35;
        int r42 = ActionBar.getCurrentActionBarHeight();
        int r0 = ((AndroidUtilities.displaySize.y - r42) - buttonsHeight()) - AndroidUtilities.dp(90.0f);
        int r1 = AndroidUtilities.dp(189.0f);
        this.overScrollHeight = r1;
        if (this.locationDenied == true) goto L10;
    L12:
        r0 = Math.min(AndroidUtilities.dp(310.0f), r0);
    L13:
        this.mapHeight = Math.max(r1, r0);
        if (this.locationDenied == true) goto L16;
    L18:
        FrameLayout.LayoutParams r02 = (FrameLayout.LayoutParams) this.listView.getLayoutParams();
        r02.topMargin = r42;
        this.listView.setLayoutParams(r02);
        FrameLayout.LayoutParams r03 = (FrameLayout.LayoutParams) this.mapViewClip.getLayoutParams();
        r03.topMargin = r42;
        r03.height = this.mapHeight;
        this.mapViewClip.setLayoutParams(r03);
        FrameLayout.LayoutParams r04 = (FrameLayout.LayoutParams) this.searchListView.getLayoutParams();
        r04.topMargin = r42;
        this.searchListView.setLayoutParams(r04);
        LocationActivityAdapter r43 = this.adapter;
        if (this.locationDenied == true) goto L21;
    L23:
        int r05 = this.overScrollHeight;
    L24:
        r43.setOverScrollHeight(r05 + AndroidUtilities.dp(16.0f));
        FrameLayout.LayoutParams r44 = (FrameLayout.LayoutParams) this.mapView.getView().getLayoutParams();
        if (r44 == null) goto L27;
        r44.height = this.mapHeight + AndroidUtilities.dp(10.0f);
        this.mapView.getView().setLayoutParams(r44);
    L27:
        MapOverlayView r45 = this.overlayView;
        if (r45 == null) goto L32;
        FrameLayout.LayoutParams r46 = (FrameLayout.LayoutParams) r45.getLayoutParams();
        if (r46 == null) goto L32;
        r46.height = this.mapHeight + AndroidUtilities.dp(10.0f);
        this.overlayView.setLayoutParams(r46);
    L32:
        this.adapter.notifyDataSetChanged();
        updateClipView();
        return;
    L21:
        if (isTypeSend() == false) goto L23;
        r05 = this.overScrollHeight - this.listView.getPaddingTop();
        goto L24
    L16:
        if (isTypeSend() == false) goto L18;
        this.overScrollHeight = this.mapHeight;
        goto L18
    L10:
        if (isTypeSend() == false) goto L12;
    }

    @SuppressLint({"MissingPermission"})
    private Location getLastLocation() {
        LocationManager r3 = (LocationManager) ApplicationLoader.applicationContext.getSystemService("location");
        List<String> r1 = r3.getProviders(true);
        int r2 = r1.size() - 1;
        Location r0 = null;
    L3:
        if (r2 < 0) goto L8;
        r0 = r3.getLastKnownLocation(r1.get(r2));
        if (r0 != null) goto L6;
        r2 = r2 - 1;
        goto L3
    L6:
        return r0;
    L8:
        return r0;
    }

    private LocationController getLocationController() {
        return this.parentAlert.baseFragment.getLocationController();
    }

    private MessagesController getMessagesController() {
        return this.parentAlert.baseFragment.getMessagesController();
    }

    private Activity getParentActivity() {
        ChatAttachAlert r0 = this.parentAlert;
        if (r0 == null) goto L8;
        BaseFragment r02 = r0.baseFragment;
        if (r02 != null) goto L7;
        return null;
    L7:
        return r02.getParentActivity();
    L8:
        return null;
    }

    private UserConfig getUserConfig() {
        return this.parentAlert.baseFragment.getUserConfig();
    }

    private boolean isActiveThemeDark() {
        if (Theme.getActiveTheme().isDark() == false) goto L6;
        return true;
    L6:
        if (AndroidUtilities.computePerceivedBrightness(getThemedColor(Theme.key_windowBackgroundWhite)) >= 0.721f) goto L8;
        return true;
    L8:
        return false;
    }

    private boolean isTypeSend() {
        int r1 = this.locationType;
        if (r1 == 0) goto L8;
        if (r1 == 1) goto L8;
        return false;
    L8:
        return true;
    }

    private /* synthetic */ void lambda$getThemeDescriptions$34() {
        this.mapTypeButton.setIconColor(getThemedColor(Theme.key_location_actionIcon));
        this.mapTypeButton.redrawPopup(getThemedColor(Theme.key_actionBarDefaultSubmenuBackground));
        this.mapTypeButton.setPopupItemsColor(getThemedColor(Theme.key_actionBarDefaultSubmenuItemIcon), true);
        this.mapTypeButton.setPopupItemsColor(getThemedColor(Theme.key_actionBarDefaultSubmenuItem), false);
        if (this.map == null) goto L12;
        boolean r0 = isActiveThemeDark();
        boolean r1 = this.currentMapStyleDark;
        if (r0 == false) goto L9;
        if (r1 == true) goto L13;
        this.currentMapStyleDark = true;
        IMapsProvider.IMapStyleOptions r02 = ApplicationLoader.getMapsProvider().loadRawResourceStyle(ApplicationLoader.applicationContext, R.raw.mapstyle_night);
        this.map.setMapStyle(r02);
        return;
    L13:
        return;
    L9:
        if (r1 == false) goto L14;
        this.currentMapStyleDark = false;
        this.map.setMapStyle(null);
        return;
    L14:
        return;
    }

    private /* synthetic */ void lambda$new$0(View r4) {
        showSearchPlacesButton(false);
        this.adapter.searchPlacesWithQuery(null, this.userLocation, true, true);
        this.searchedForCustomLocations = true;
        showResults();
    }

    private /* synthetic */ void lambda$new$1(View r1) {
        this.mapTypeButton.toggleSubMenu();
    }

    private /* synthetic */ boolean lambda$new$10(MotionEvent r3, IMapsProvider.ICallableMethod r4) {
        if (this.yOffset == 0.0f) goto L5;
        r3 = MotionEvent.obtain(r3);
        r3.offsetLocation(0.0f, (-this.yOffset) / 2.0f);
        MotionEvent r2 = r3;
    L6:
        boolean r32 = ((Boolean) r4.call(r3)).booleanValue();
        if (r2 == null) goto L9;
        r2.recycle();
    L9:
        return r32;
    L5:
        r2 = null;
        goto L6
    }

    private /* synthetic */ boolean lambda$new$11(MotionEvent r8, IMapsProvider.ICallableMethod r9) {
        int r0 = r8.getAction();
        Property r3 = View.TRANSLATION_Y;
        if (r0 != 0) goto L9;
        AnimatorSet r02 = this.animatorSet;
        if (r02 == null) goto L7;
        r02.cancel();
    L7:
        AnimatorSet r03 = new AnimatorSet();
        this.animatorSet = r03;
        r03.setDuration(200);
        this.animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.markerImageView, r3, new float[]{this.markerTop - AndroidUtilities.dp(10.0f)})});
        this.animatorSet.start();
    L17:
        if (r8.getAction() != 2) goto L28;
        if (this.userLocationMoved == true) goto L21;
        ImageView r04 = this.locationButton;
        int r32 = Theme.key_location_actionIcon;
        r04.setColorFilter(new PorterDuffColorFilter(getThemedColor(r32), PorterDuff.Mode.MULTIPLY));
        this.locationButton.setTag(Integer.valueOf(r32));
        this.userLocationMoved = true;
    L21:
        IMapsProvider.IMap r05 = this.map;
        if (r05 == null) goto L26;
        Location r1 = this.userLocation;
        if (r1 == null) goto L26;
        r1.setLatitude(r05.getCameraPosition().target.latitude);
        this.userLocation.setLongitude(this.map.getCameraPosition().target.longitude);
    L26:
        this.adapter.setCustomLocation(this.userLocation);
    L28:
        return ((Boolean) r9.call(r8)).booleanValue();
    L9:
        if (r8.getAction() != 1) goto L11;
    L12:
        AnimatorSet r06 = this.animatorSet;
        if (r06 == null) goto L15;
        r06.cancel();
    L15:
        this.yOffset = 0.0f;
        AnimatorSet r07 = new AnimatorSet();
        this.animatorSet = r07;
        r07.setDuration(200);
        this.animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.markerImageView, r3, new float[]{this.markerTop})});
        this.animatorSet.start();
        goto L17
    L11:
        if (r8.getAction() != 3) goto L17;
        goto L12
    }

    private /* synthetic */ void lambda$new$12() {
        this.loadingMapView.setTag(1);
        this.loadingMapView.animate().alpha(0.0f).setDuration(180).start();
    }

    private /* synthetic */ void lambda$new$13() {
        AndroidUtilities.runOnUIThread(new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda30(this));
    }

    private /* synthetic */ void lambda$new$14(IMapsProvider.IMap r3) {
        this.map = r3;
        r3.setOnMapLoadedCallback(new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda29(this));
        if (isActiveThemeDark() == false) goto L5;
        this.currentMapStyleDark = true;
        IMapsProvider.IMapStyleOptions r32 = ApplicationLoader.getMapsProvider().loadRawResourceStyle(ApplicationLoader.applicationContext, R.raw.mapstyle_night);
        this.map.setMapStyle(r32);
    L5:
        onMapInit();
    }

    private /* synthetic */ void lambda$new$15(IMapsProvider.IMapView r2) {
        if (this.mapView != null) goto L5;
        return;
    L5:
        if (getParentActivity() != null) goto L14;
        return;
    L14:
        r2.onCreate(null);     // Catch: Exception -> L11
        ApplicationLoader.getMapsProvider().initializeMaps(ApplicationLoader.applicationContext);     // Catch: Exception -> L11
        this.mapView.getMapAsync(new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda28(this));     // Catch: Exception -> L11
        this.mapsInitialized = true;     // Catch: Exception -> L11
        if (this.onResumeCalled == false) goto L18;
        this.mapView.onResume();     // Catch: Exception -> L11
        return;
    L18:
        return;
    L11:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$new$16(IMapsProvider.IMapView r2) {
        r2.onCreate(null);     // Catch: Exception -> L6
    L4:
        AndroidUtilities.runOnUIThread(new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda22(this, r2));
    }

    private /* synthetic */ void lambda$new$17(ArrayList r1) {
        this.searchInProgress = false;
        updateEmptyView();
    }

    private /* synthetic */ void lambda$new$18(TLRPC.TL_messageMediaVenue r8, boolean r9, int r10, int r11) {
        this.delegate.didSelectLocation(r8, this.locationType, r9, r10, 0);
        this.parentAlert.dismiss(true);
    }

    private /* synthetic */ void lambda$new$19(ChatActivity r8, Theme.ResourcesProvider r9, View r10, int r11) {
        TLRPC.TL_messageMediaVenue r1 = this.searchAdapter.getItem(r11);
        if (r1 != null) goto L5;
        return;
    L5:
        if (this.delegate == null) goto L14;
        if (r8 != null) goto L8;
    L11:
        this.delegate.didSelectLocation(r1, this.locationType, true, 0, 0);
        this.parentAlert.dismiss(true);
        return;
    L8:
        if (r8.isInScheduleMode() == false) goto L11;
        AlertsCreator.createScheduleDatePickerDialog(getParentActivity(), r8.getDialogId(), new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda23(this, r1), r9);
        return;
    }

    private /* synthetic */ void lambda$new$2(int r3) {
        IMapsProvider.IMap r2 = this.map;
        if (r2 != null) goto L6;
        return;
    L6:
        if (r3 != 2) goto L10;
        r2.setMapType(0);
        return;
    L10:
        if (r3 != 3) goto L14;
        r2.setMapType(1);
        return;
    L14:
        if (r3 != 4) goto L17;
        r2.setMapType(2);
        return;
    }

    private /* synthetic */ void lambda$new$3(View r10) {
        Activity r102 = getParentActivity();
        if (r102 == null) goto L9;
        if (r102.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) goto L9;
        AlertsCreator.createLocationRequiredDialog(getParentActivity(), true).show();
        return;
    L9:
        if (this.myLocation != null) goto L11;
    L20:
        removeInfoView();
        return;
    L11:
        if (this.map == null) goto L20;
        ImageView r103 = this.locationButton;
        int r2 = Theme.key_location_actionActiveIcon;
        r103.setColorFilter(new PorterDuffColorFilter(getThemedColor(r2), PorterDuff.Mode.MULTIPLY));
        this.locationButton.setTag(Integer.valueOf(r2));
        this.adapter.setCustomLocation(null);
        this.userLocationMoved = false;
        showSearchPlacesButton(false);
        this.map.animateCamera(ApplicationLoader.getMapsProvider().newCameraUpdateLatLng(new IMapsProvider.LatLng(this.myLocation.getLatitude(), this.myLocation.getLongitude())));
        if (this.searchedForCustomLocations == false) goto L20;
        Location r22 = this.myLocation;
        if (r22 != null) goto L17;
    L19:
        this.searchedForCustomLocations = false;
        showResults();
        goto L20
    L17:
        if (this.locationType == 8) goto L19;
        this.adapter.searchPlacesWithQuery(null, r22, true, true);
        goto L19
    }

    private /* synthetic */ void lambda$new$5(TLRPC.TL_messageMediaGeo r8, Long r9, boolean r10, int r11, int r12) {
        this.delegate.didSelectLocation(r8, this.locationType, r10, r11, r9.longValue());
        this.parentAlert.dismiss(true);
    }

    private /* synthetic */ void lambda$new$6(ChatActivity r11, TLRPC.TL_messageMediaGeo r12, Theme.ResourcesProvider r13, Long r14) {
        if (r11 != null) goto L4;
    L7:
        this.delegate.didSelectLocation(r12, this.locationType, true, 0, r14.longValue());
        this.parentAlert.dismiss(true);
        return;
    L4:
        if (r11.isInScheduleMode() == false) goto L7;
        AlertsCreator.createScheduleDatePickerDialog(getParentActivity(), r11.getDialogId(), new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda27(this, r12, r14), r13);
    }

    private /* synthetic */ void lambda$new$7(Object r8, boolean r9, int r10, int r11) {
        this.delegate.didSelectLocation((TLRPC.TL_messageMediaVenue) r8, this.locationType, r9, r10, 0);
        this.parentAlert.dismiss(true);
    }

    private /* synthetic */ void lambda$new$8(ChatActivity r10, Object r11, Theme.ResourcesProvider r12, Long r13) {
        if (r10 != null) goto L4;
    L7:
        this.delegate.didSelectLocation((TLRPC.TL_messageMediaVenue) r11, this.locationType, true, 0, 0);
        this.parentAlert.dismiss(true);
        return;
    L4:
        if (r10.isInScheduleMode() == false) goto L7;
        AlertsCreator.createScheduleDatePickerDialog(getParentActivity(), r10.getDialogId(), new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda26(this, r11), r12);
    }

    private /* synthetic */ void lambda$new$9(ChatActivity r9, Theme.ResourcesProvider r10, View r11, int r12) {
        int r2 = this.locationType;
        if (r2 != 7) goto L14;
        if (r12 != 1) goto L9;
        TLRPC.TL_messageMediaVenue r1 = this.adapter.city;
        if (r1 == null) goto L9;
        this.delegate.didSelectLocation(r1, r2, true, 0, 0);
        this.parentAlert.dismiss(true);
        return;
    L9:
        if (r12 != 2) goto L43;
        TLRPC.TL_messageMediaVenue r13 = this.adapter.street;
        if (r13 == null) goto L43;
        this.delegate.didSelectLocation(r13, r2, true, 0, 0);
        this.parentAlert.dismiss(true);
        return;
    L43:
        Object r112 = this.adapter.getItem(r12);
        if ((r112 instanceof TLRPC.TL_messageMediaVenue) == false) goto L48;
        ChatAttachAlert r122 = this.parentAlert;
        AlertsCreator.ensurePaidMessageConfirmation(r122.currentAccount, r122.getDialogId(), this.parentAlert.getAdditionalMessagesCount() + 1, new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda25(this, r9, r112, r10));
        return;
    L48:
        return;
    L14:
        if (r12 == 1) goto L16;
        if (r12 != 2) goto L43;
        if (r2 != 1) goto L43;
        if (getLocationController().isSharingLocation(this.dialogId) == false) goto L36;
        getLocationController().removeSharingLocation(this.dialogId);
        this.parentAlert.dismiss(true);
        return;
    L36:
        if (this.myLocation == null) goto L38;
    L41:
        openShareLiveLocation();
        return;
    L38:
        if (this.locationDenied == false) goto L41;
        AlertsCreator.createLocationRequiredDialog(getParentActivity(), true).show();
        return;
    L16:
        if (this.delegate == null) goto L26;
        if (this.userLocation == null) goto L26;
        FrameLayout r113 = this.lastPressedMarkerView;
        if (r113 == null) goto L23;
        r113.callOnClick();
        return;
    L23:
        TLRPC.TL_messageMediaGeo r114 = new TLRPC.TL_messageMediaGeo();
        TLRPC.TL_geoPoint r123 = new TLRPC.TL_geoPoint();
        r114.geo = r123;
        r123.lat = AndroidUtilities.fixLocationCoord(this.userLocation.getLatitude());
        TLRPC.GeoPoint r124 = r114.geo;
        r124._long = AndroidUtilities.fixLocationCoord(this.userLocation.getLongitude());
        ChatAttachAlert r125 = this.parentAlert;
        AlertsCreator.ensurePaidMessageConfirmation(r125.currentAccount, r125.getDialogId(), this.parentAlert.getAdditionalMessagesCount() + 1, new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda24(this, r9, r114, r10));
        return;
    L26:
        if (this.locationDenied == false) goto L47;
        AlertsCreator.createLocationRequiredDialog(getParentActivity(), true).show();
        return;
    }

    private /* synthetic */ void lambda$onMapInit$22(int r6) {
        if (r6 != 1) goto L22;
        showSearchPlacesButton(true);
        removeInfoView();
        if (this.scrolling == false) goto L7;
        return;
    L7:
        if (this.listView.getChildCount() <= 0) goto L24;
        View r62 = this.listView.getChildAt(0);
        if (r62 == null) goto L25;
        RecyclerView.ViewHolder r1 = this.listView.findContainingViewHolder(r62);
        if (r1 != null) goto L13;
        return;
    L13:
        if (r1.getAdapterPosition() == 0) goto L15;
        return;
    L15:
        if (this.locationType != 0) goto L17;
        int r12 = 0;
    L18:
        int r63 = r62.getTop();
        if (r63 >= (-r12)) goto L28;
        IMapsProvider.CameraPosition r2 = this.map.getCameraPosition();
        this.forceUpdate = ApplicationLoader.getMapsProvider().newCameraUpdateLatLngZoom(r2.target, r2.zoom);
        this.listView.smoothScrollBy(0, r63 + r12);
        return;
    L28:
        return;
    L17:
        r12 = AndroidUtilities.dp(66.0f);
        goto L18
    L25:
        return;
    L24:
        return;
    }

    private /* synthetic */ void lambda$onMapInit$23() {
        if (this.ignoreIdleCamera == false) goto L6;
        this.ignoreIdleCamera = false;
        return;
    L6:
        IMapsProvider.IMap r0 = this.map;
        if (r0 == null) goto L11;
        Location r1 = this.userLocation;
        if (r1 == null) goto L11;
        r1.setLatitude(r0.getCameraPosition().target.latitude);
        this.userLocation.setLongitude(this.map.getCameraPosition().target.longitude);
    L11:
        this.adapter.setCustomLocation(this.userLocation);
        this.adapter.fetchLocationAddress();
    }

    private /* synthetic */ void lambda$onMapInit$24(Location r4) {
        ChatAttachAlert r0 = this.parentAlert;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.baseFragment == null) goto L19;
        positionMarker(r4);
        LocationActivityAdapter r02 = this.adapter;
        if (r02 == null) goto L16;
        int r1 = this.locationType;
        if (r1 == 7) goto L14;
        if (r1 != 8) goto L16;
    L14:
        if (this.userLocationMoved == true) goto L16;
        r02.setCustomLocation(this.userLocation);
    L16:
        getLocationController().setMapLocation(r4, this.isFirstLocation);
        this.isFirstLocation = false;
        return;
    }

    private /* synthetic */ boolean lambda$onMapInit$25(IMapsProvider.IMarker r7) {
        if ((r7.getTag() instanceof VenueLocation) == true) goto L5;
        return true;
    L5:
        this.markerImageView.setVisibility(4);
        if (this.userLocationMoved == true) goto L8;
        ImageView r0 = this.locationButton;
        int r3 = Theme.key_location_actionIcon;
        r0.setColorFilter(new PorterDuffColorFilter(getThemedColor(r3), PorterDuff.Mode.MULTIPLY));
        this.locationButton.setTag(Integer.valueOf(r3));
        this.userLocationMoved = true;
    L8:
        this.overlayView.addInfoView(r7);
        return true;
    }

    private /* synthetic */ void lambda$onMapInit$26() {
        MapOverlayView r0 = this.overlayView;
        if (r0 == null) goto L6;
        r0.updatePositions();
        return;
    }

    private /* synthetic */ void lambda$onMapInit$27() {
        if (this.loadingMapView.getTag() != null) goto L6;
        this.loadingMapView.animate().alpha(0.0f).setDuration(180).start();
        return;
    }

    private /* synthetic */ void lambda$onMapInit$28(AlertDialog r1, int r2) {
        if (getParentActivity() == null) goto L10;
        getParentActivity().startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));     // Catch: Exception -> L7
        return;
    L11:
        return;
    }

    private /* synthetic */ void lambda$onShow$33() {
        if (this.checkPermission == false) goto L34;
        Activity r0 = getParentActivity();
        if (r0 == null) goto L35;
        this.checkPermission = false;
        if (r0.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) goto L22;
        ChatAttachAlert r3 = this.parentAlert;
        if (r3.isStoryLocationPicker == true) goto L11;
    L15:
        String[] r32 = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
    L16:
        this.askedForLocation = true;
        LocationActivityAdapter r1 = this.adapter;
        if (r1 == null) goto L19;
        r1.setMyLocationDenied(this.locationDenied, true);
    L19:
        r0.requestPermissions(r32, 2);
        return;
    L11:
        if (r3.storyLocationPickerPhotoFile == null) goto L15;
        if (Build.VERSION.SDK_INT < 29) goto L15;
        r32 = new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_MEDIA_LOCATION"};
        goto L16
    L22:
        if (Build.VERSION.SDK_INT < 29) goto L36;
        ChatAttachAlert r12 = this.parentAlert;
        if (r12.isStoryLocationPicker == true) goto L26;
        return;
    L26:
        if (r12.storyLocationPickerPhotoFile != null) goto L28;
        return;
    L28:
        if (r0.checkSelfPermission("android.permission.ACCESS_MEDIA_LOCATION") == 0) goto L39;
        this.askedForLocation = true;
        LocationActivityAdapter r13 = this.adapter;
        if (r13 == null) goto L32;
        r13.setMyLocationDenied(this.locationDenied, true);
    L32:
        r0.requestPermissions(new String[]{"android.permission.ACCESS_MEDIA_LOCATION"}, Opcodes.DIV_INT_LIT16);
        return;
    L39:
        return;
    L36:
        return;
    L35:
        return;
    }

    private /* synthetic */ void lambda$openShareLiveLocation$20(int r8, Long r9) {
        TLRPC.TL_messageMediaGeoLive r1 = new TLRPC.TL_messageMediaGeoLive();
        TLRPC.TL_geoPoint r0 = new TLRPC.TL_geoPoint();
        r1.geo = r0;
        r0.lat = AndroidUtilities.fixLocationCoord(this.myLocation.getLatitude());
        TLRPC.GeoPoint r02 = r1.geo;
        r02._long = AndroidUtilities.fixLocationCoord(this.myLocation.getLongitude());
        r1.period = r8;
        this.delegate.didSelectLocation(r1, this.locationType, true, 0, r9.longValue());
        this.parentAlert.dismiss(true);
    }

    private /* synthetic */ void lambda$openShareLiveLocation$21(int r6) {
        ChatAttachAlert r0 = this.parentAlert;
        AlertsCreator.ensurePaidMessageConfirmation(r0.currentAccount, r0.getDialogId(), this.parentAlert.getAdditionalMessagesCount() + 1, new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda21(this, r6));
    }

    private /* synthetic */ void lambda$positionMarker$29() {
        double[] r0 = this.parentAlert.storyLocationPickerLatLong;
        resetMapPosition(r0[0], r0[1]);
    }

    private /* synthetic */ void lambda$positionMarker$30(double r1, double r3) {
        resetMapPosition(r1, r3);
    }

    private /* synthetic */ void lambda$positionMarker$31(float[] r5) {
        resetMapPosition(r5[0], r5[1]);
    }

    private /* synthetic */ void lambda$positionMarker$32() {
        resetMapPosition(0.0d, 0.0d);
    }

    private void onMapInit() {
        if (this.map == null) goto L33;
        Location r0 = new Location("network");
        this.userLocation = r0;
        r0.setLatitude(20.659322d);
        this.userLocation.setLongitude(-11.40625d);
        this.map.setMyLocationEnabled(true);     // Catch: Exception -> L8
    L10:
        this.map.getUiSettings().setMyLocationButtonEnabled(false);
        this.map.getUiSettings().setZoomControlsEnabled(false);
        this.map.getUiSettings().setCompassEnabled(false);
        this.map.setOnCameraMoveStartedListener(new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda31(this));
        this.map.setOnCameraIdleListener(new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda32(this));
        this.map.setOnMyLocationChangeListener(new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda33(this));
        this.map.setOnMarkerClickListener(new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda34(this));
        this.map.setOnCameraMoveListener(new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda35(this));
        positionMarker();
        AndroidUtilities.runOnUIThread(new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda36(this), 200);
        if (this.checkGpsEnabled == true) goto L13;
    L27:
        updateClipView();
        return;
    L13:
        if (getParentActivity() == null) goto L27;
        this.checkGpsEnabled = false;
        Activity r02 = getParentActivity();
        if (r02 == null) goto L29;
        PackageManager r03 = r02.getPackageManager();
        if (r03 == null) goto L29;
        if (r03.hasSystemFeature("android.hardware.location.gps") == true) goto L29;
        return;
    L29:
    L25:
        e = move-exception;
        FileLog.e(e);
        goto L27
    L22:
        if (((LocationManager) ApplicationLoader.applicationContext.getSystemService("location")).isProviderEnabled("gps") == true) goto L27;
        AlertDialog.Builder r04 = new AlertDialog.Builder(getParentActivity(), this.resourcesProvider);     // Catch: Exception -> L25
        r04.setTopAnimation(R.raw.permission_request_location, 72, false, Theme.getColor(Theme.key_dialogTopBackground, this.resourcesProvider));     // Catch: Exception -> L25
        r04.setMessage(LocaleController.getString(R.string.GpsDisabledAlertText));     // Catch: Exception -> L25
        r04.setPositiveButton(LocaleController.getString(R.string.ConnectingToProxyEnable), new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda37(this));     // Catch: Exception -> L25
        r04.setNegativeButton(LocaleController.getString(R.string.Cancel), null);     // Catch: Exception -> L25
        r04.show();     // Catch: Exception -> L25
    L8:
        e = move-exception;
        FileLog.e(e);
        goto L10
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x0082 -> B:39:0x008b). Please report as a decompilation issue!!! */
    private void positionMarker() {
        ChatAttachAlert r0 = this.parentAlert;
        if (r0.isStoryLocationPicker == true) goto L5;
        Location r8 = getLastLocation();
        this.myLocation = r8;
        positionMarker(r8);
        return;
    L5:
        if (r0.storyLocationPickerLatLong == null) goto L9;
        AndroidUtilities.runOnUIThread(new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda0(this));
        return;
    L9:
        if (this.locationDenied == true) goto L33;
        File r1 = r0.storyLocationPickerPhotoFile;
        boolean r02 = r0.storyLocationPickerFileIsVideo;
        if (r1 != null) goto L40;
    L25:
        ChatAttachAlertLocationLayout r3 = this;
    L31:
        Location r82 = r3.getLastLocation();
        r3.myLocation = r82;
        r3.positionMarker(r82);
    L39:
        return;
    L40:
        if (r02 == true) goto L42;
        r3 = this;
        ExifInterface r83 = new ExifInterface(r1.getAbsolutePath());     // Catch: Throwable -> L37
        float[] r03 = new float[2];     // Catch: Throwable -> L37
        if (r83.getLatLong(r03) == false) goto L31;
        AndroidUtilities.runOnUIThread(new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda2(r3, r03));     // Catch: Throwable -> L37
        goto L39
    L42:
        MediaMetadataRetriever r04 = new MediaMetadataRetriever();     // Catch: Throwable -> L38
        r04.setDataSource(r1.getAbsolutePath());     // Catch: Throwable -> L38
        String r05 = r04.extractMetadata(23);     // Catch: Throwable -> L38
        if (r05 == null) goto L25;
        Matcher r06 = Pattern.compile("([+\\-][0-9.]+)([+\\-][0-9.]+)").matcher(r05);     // Catch: Throwable -> L38
        if (r06.find() == false) goto L25;
        if (r06.groupCount() != 2) goto L25;
        String r12 = r06.group(1);     // Catch: Throwable -> L38
        String r07 = r06.group(2);     // Catch: Throwable -> L38
        r3 = this;
        AndroidUtilities.runOnUIThread(new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda1(r3, Double.parseDouble(r12), Double.parseDouble(r07)));     // Catch: Throwable -> L37
        goto L39
    L33:
        AndroidUtilities.runOnUIThread(new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda3(this));
    }

    private void positionMarker(Location r6) {
        if (r6 == null) goto L25;
        Location r0 = new Location(r6);
        this.myLocation = r0;
        if (this.map == null) goto L22;
        IMapsProvider.LatLng r02 = new IMapsProvider.LatLng(r6.getLatitude(), r6.getLongitude());
        LocationActivityAdapter r1 = this.adapter;
        if (r1 == null) goto L15;
        if (this.searchedForCustomLocations == false) goto L11;
    L13:
        this.adapter.setGpsLocation(this.myLocation);
        goto L15
    L11:
        if (this.locationType == 8) goto L13;
        r1.searchPlacesWithQuery(null, this.myLocation, true);
    L15:
        if (this.userLocationMoved == true) goto L24;
        this.userLocation = new Location(r6);
        if (this.firstWas == false) goto L20;
        IMapsProvider.ICameraUpdate r62 = ApplicationLoader.getMapsProvider().newCameraUpdateLatLng(r02);
        this.map.animateCamera(r62);
        return;
    L20:
        this.firstWas = true;
        IMapsProvider.ICameraUpdate r63 = ApplicationLoader.getMapsProvider().newCameraUpdateLatLngZoom(r02, this.map.getMaxZoomLevel() - 4.0f);
        this.map.moveCamera(r63);
        return;
    L24:
        return;
    L22:
        this.adapter.setGpsLocation(r0);
        return;
    }

    private void removeInfoView() {
        if (this.lastPressedMarker == null) goto L6;
        this.markerImageView.setVisibility(0);
        this.overlayView.removeInfoView(this.lastPressedMarker);
        this.lastPressedMarker = null;
        this.lastPressedVenue = null;
        this.lastPressedMarkerView = null;
        return;
    }

    private void resetMapPosition(double r6, double r8) {
        if (this.map != null) goto L6;
        return;
    L6:
        if (r6 != 0.0d) goto L8;
    L10:
        Location r4 = new Location(_UrlKt.FRAGMENT_ENCODE_SET);
        this.myLocation = r4;
        r4.reset();
        this.myLocation.setLatitude(r6);
        this.myLocation.setLongitude(r8);
    L11:
        IMapsProvider.LatLng r3 = new IMapsProvider.LatLng(r6, r8);
        if (r6 != 0.0d) goto L14;
    L16:
        IMapsProvider.ICameraUpdate r62 = ApplicationLoader.getMapsProvider().newCameraUpdateLatLngZoom(r3, this.map.getMinZoomLevel());
    L17:
        this.forceUpdate = r62;
        this.map.moveCamera(r62);
        if (r6 != 0.0d) goto L20;
    L22:
        this.adapter.setGpsLocation(this.myLocation);
    L23:
        this.adapter.fetchLocationAddress();
        this.listView.smoothScrollBy(0, 1);
        this.ignoreIdleCamera = true;
        if (r6 != 0.0d) goto L26;
        return;
    L26:
        if (r8 == 0.0d) goto L33;
        this.userLocationMoved = true;
        showSearchPlacesButton(false);
        if (this.locationType == 8) goto L30;
        this.adapter.searchPlacesWithQuery(null, this.userLocation, true, true);
    L30:
        this.searchedForCustomLocations = true;
        showResults();
        return;
    L33:
        return;
    L20:
        if (r8 == 0.0d) goto L22;
        this.adapter.setCustomLocation(this.userLocation);
        goto L23
    L14:
        if (r8 == 0.0d) goto L16;
        r62 = ApplicationLoader.getMapsProvider().newCameraUpdateLatLngZoom(r3, this.map.getMaxZoomLevel() - 4.0f);
        goto L17
    L8:
        if (r8 == 0.0d) goto L10;
        Location r42 = new Location(_UrlKt.FRAGMENT_ENCODE_SET);
        this.userLocation = r42;
        r42.reset();
        this.userLocation.setLatitude(r6);
        this.userLocation.setLongitude(r8);
        goto L11
    }

    private void showResults() {
        if (this.adapter.getItemCount() != 0) goto L6;
        return;
    L6:
        if (this.layoutManager.findFirstVisibleItemPosition() != 0) goto L17;
        View r0 = this.listView.getChildAt(0);
        int r3 = AndroidUtilities.dp(258.0f) + r0.getTop();
        if (r3 >= 0) goto L11;
        return;
    L11:
        if (r3 > AndroidUtilities.dp(258.0f)) goto L18;
        this.listView.smoothScrollBy(0, r3);
        return;
    L18:
        return;
    }

    private void showSearchPlacesButton(boolean r5) {
        if (this.locationDenied == false) goto L5;
        r5 = false;
    L5:
        if (r5 == false) goto L18;
        SearchButton r0 = this.searchAreaButton;
        if (r0 == null) goto L18;
        if (r0.getTag() != null) goto L18;
        Location r02 = this.myLocation;
        if (r02 == null) goto L16;
        Location r2 = this.userLocation;
        if (r2 == null) goto L16;
        if (r2.distanceTo(r02) >= 300.0f) goto L18;
    L16:
        r5 = false;
    L18:
        if (this.locationType != 8) goto L20;
        r5 = false;
    L20:
        SearchButton r03 = this.searchAreaButton;
        if (r03 == null) goto L43;
        if (r5 == true) goto L24;
    L25:
        if (r5 == false) goto L27;
    L29:
        SearchButton r04 = this.searchAreaButton;
        if (r5 == false) goto L32;
        int r22 = 0;
    L33:
        r04.setVisibility(r22);
        SearchButton r05 = this.searchAreaButton;
        if (r5 == false) goto L36;
        Integer r3 = 1;
    L37:
        r05.setTag(r3);
        AnimatorSet r06 = new AnimatorSet();
        SearchButton r4 = this.searchAreaButton;
        if (r5 == false) goto L40;
        float r52 = 0.0f;
    L41:
        r06.playTogether(new Animator[]{ObjectAnimator.ofFloat(r4, View.TRANSLATION_X, new float[]{r52})});
        r06.setDuration(180);
        r06.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        r06.start();
        return;
    L40:
        r52 = -AndroidUtilities.dp(80.0f);
        goto L41
    L36:
        r3 = null;
        goto L37
    L32:
        r22 = 4;
        goto L33
    L27:
        if (this.searchAreaButton.getTag() != null) goto L29;
        return;
    L24:
        if (r03.getTag() == null) goto L25;
        return;
    }

    private void updateClipView() {
        if (this.mapView != null) goto L5;
        return;
    L5:
        if (this.mapViewClip == null) goto L73;
        RecyclerView.ViewHolder r0 = this.listView.findViewHolderForAdapterPosition(0);
        if (r0 == null) goto L10;
        int r02 = (int) r0.itemView.getY();
        int r2 = this.overScrollHeight + Math.min(r02, 0);
    L12:
        if (((FrameLayout.LayoutParams) this.mapViewClip.getLayoutParams()) == null) goto L70;
        IMapsProvider.IMapView r3 = this.mapView;
        if (r2 > 0) goto L23;
        if (r3.getView().getVisibility() != 0) goto L20;
        this.mapView.getView().setVisibility(4);
        this.mapViewClip.setVisibility(4);
        MapOverlayView r1 = this.overlayView;
        if (r1 == null) goto L20;
        r1.setVisibility(4);
    L20:
        this.mapView.getView().setTranslationY(r02);
        return;
    L23:
        if (r3.getView().getVisibility() != 4) goto L27;
        this.mapView.getView().setVisibility(0);
        this.mapViewClip.setVisibility(0);
        MapOverlayView r22 = this.overlayView;
        if (r22 == null) goto L27;
        r22.setVisibility(0);
    L27:
        int r23 = Math.max(0, (-((r02 - this.mapHeight) + this.overScrollHeight)) / 2);
        int r32 = this.mapHeight - this.overScrollHeight;
        float r4 = 1.0f - Math.max(0.0f, Math.min(1.0f, (this.listView.getPaddingTop() - r02) / (this.listView.getPaddingTop() - r32)));
        int r5 = this.clipSize;
        if (this.locationDenied == true) goto L30;
    L32:
        this.clipSize = (int) (r32 * r4);
        float r6 = r23;
        this.mapView.getView().setTranslationY(r6);
        this.nonClipSize = r32 - this.clipSize;
        this.mapViewClip.invalidate();
        this.mapViewClip.setTranslationY(r02 - this.nonClipSize);
        IMapsProvider.IMap r33 = this.map;
        if (r33 == null) goto L35;
        r33.setPadding(0, AndroidUtilities.dp(6.0f), 0, this.clipSize + AndroidUtilities.dp(6.0f));
    L35:
        MapOverlayView r34 = this.overlayView;
        if (r34 == null) goto L38;
        r34.setTranslationY(r6);
    L38:
        float r12 = Math.min(Math.max(this.nonClipSize - r02, 0), (this.mapHeight - this.mapTypeButton.getMeasuredHeight()) - AndroidUtilities.dp(80.0f));
        this.mapTypeButton.setTranslationY(r12);
        this.searchAreaButton.setTranslation(r12);
        this.locationButton.setTranslationY(-this.clipSize);
        ImageView r13 = this.markerImageView;
        int r35 = (((this.mapHeight - this.clipSize) / 2) - AndroidUtilities.dp(48.0f)) + r23;
        this.markerTop = r35;
        r13.setTranslationY(r35);
        if (r5 == this.clipSize) goto L57;
        IMapsProvider.IMarker r14 = this.lastPressedMarker;
        if (r14 == null) goto L44;
        IMapsProvider.LatLng r24 = new IMapsProvider.LatLng(r14.getPosition().latitude, this.lastPressedMarker.getPosition().longitude);
    L52:
        if (r24 == null) goto L57;
        IMapsProvider.IMap r15 = this.map;
        if (r15 == null) goto L57;
        r15.moveCamera(ApplicationLoader.getMapsProvider().newCameraUpdateLatLng(r24));
        goto L57
    L44:
        if (this.userLocationMoved == false) goto L48;
        Location r16 = this.userLocation;
        if (r16 == null) goto L48;
        r24 = new IMapsProvider.LatLng(r16.getLatitude(), this.userLocation.getLongitude());
    L48:
        Location r17 = this.myLocation;
        if (r17 == null) goto L51;
        r24 = new IMapsProvider.LatLng(r17.getLatitude(), this.myLocation.getLongitude());
        goto L52
    L51:
        r24 = null;
    L57:
        if (this.locationDenied == true) goto L59;
        return;
    L59:
        if (isTypeSend() == false) goto L72;
        int r18 = this.adapter.getItemCount();
        int r25 = 1;
    L61:
        if (r25 >= r18) goto L74;
        RecyclerView.ViewHolder r36 = this.listView.findViewHolderForAdapterPosition(r25);
        if (r36 == null) goto L65;
        r36.itemView.setTranslationY(this.listView.getPaddingTop() - r02);
    L65:
        r25 = r25 + 1;
        goto L61
    L74:
        return;
    L72:
        return;
    L30:
        if (isTypeSend() == false) goto L32;
        r32 = r32 + Math.min(r02, this.listView.getPaddingTop());
        goto L32
    L70:
        return;
    L10:
        r02 = -this.mapViewClip.getMeasuredHeight();
        r2 = 0;
        goto L12
    }

    private void updateEmptyView() {
        if (this.searching == false) goto L10;
        boolean r0 = this.searchInProgress;
        RecyclerListView r2 = this.searchListView;
        if (r0 == false) goto L8;
        r2.setEmptyView(null);
        this.emptyView.setVisibility(8);
        return;
    L8:
        r2.setEmptyView(this.emptyView);
        return;
    L10:
        this.emptyView.setVisibility(8);
    }

    private void updatePlacesMarkers(ArrayList<TLRPC.TL_messageMediaVenue> r11) {
        if (r11 == null) goto L16;
        int r0 = this.placeMarkers.size();
        int r1 = 0;
        int r2 = 0;
    L5:
        ArrayList<VenueLocation> r3 = this.placeMarkers;
        if (r2 >= r0) goto L8;
        r3.get(r2).marker.remove();
        r2 = r2 + 1;
        goto L5
    L8:
        r3.clear();
        int r02 = r11.size();
    L9:
        if (r1 >= r02) goto L23;
        TLRPC.TL_messageMediaVenue r22 = r11.get(r1);
        IMapsProvider.IMarkerOptions r32 = ApplicationLoader.getMapsProvider().onCreateMarkerOptions();     // Catch: Exception -> L13
        TLRPC.GeoPoint r5 = r22.geo;     // Catch: Exception -> L13
        IMapsProvider.IMarkerOptions r33 = r32.position(new IMapsProvider.LatLng(r5.lat, r5._long));     // Catch: Exception -> L13
        r33.icon(createPlaceBitmap(r1));     // Catch: Exception -> L13
        r33.anchor(0.5f, 0.5f);     // Catch: Exception -> L13
        r33.title(r22.title);     // Catch: Exception -> L13
        r33.snippet(r22.address);     // Catch: Exception -> L13
        VenueLocation r4 = new VenueLocation();     // Catch: Exception -> L13
        r4.num = r1;     // Catch: Exception -> L13
        IMapsProvider.IMarker r34 = this.map.addMarker(r33);     // Catch: Exception -> L13
        r4.marker = r34;     // Catch: Exception -> L13
        r4.venue = r22;     // Catch: Exception -> L13
        r34.setTag(r4);     // Catch: Exception -> L13
        this.placeMarkers.add(r4);     // Catch: Exception -> L13
    L15:
        r1 = r1 + 1;
    L13:
        e = move-exception;
        FileLog.e(e);
        goto L15
    L23:
        return;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        int r0 = 0;
        if (r3 != NotificationCenter.locationPermissionGranted) goto L17;
        this.locationDenied = false;
        this.askedForLocation = false;
        positionMarker();
        LocationActivityAdapter r32 = this.adapter;
        if (r32 == null) goto L7;
        r32.setMyLocationDenied(this.locationDenied, this.askedForLocation);
    L7:
        LocationActivitySearchAdapter r33 = this.searchAdapter;
        if (r33 == null) goto L10;
        r33.setMyLocationDenied(this.locationDenied);
    L10:
        IMapsProvider.IMap r34 = this.map;
        if (r34 != null) goto L33;
    L24:
        fixLayoutInternal(true);
        ActionBarMenuItem r35 = this.searchItem;
        if (this.locationDenied == false) goto L29;
        if (this.parentAlert.isStoryLocationPicker == true) goto L29;
    L30:
        r0 = 8;
    L31:
        r35.setVisibility(r0);
        return;
    L29:
        if (this.parentAlert.isBizLocationPicker == false) goto L31;
    L33:
        r34.setMyLocationEnabled(true);     // Catch: Exception -> L14
    L14:
        e = move-exception;
        FileLog.e(e);
        goto L24
    L17:
        if (r3 != NotificationCenter.locationPermissionDenied) goto L24;
        this.locationDenied = true;
        this.askedForLocation = false;
        LocationActivityAdapter r36 = this.adapter;
        if (r36 == null) goto L21;
        r36.setMyLocationDenied(true, false);
    L21:
        LocationActivitySearchAdapter r37 = this.searchAdapter;
        if (r37 == null) goto L24;
        r37.setMyLocationDenied(this.locationDenied);
        goto L24
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getButtonsHideOffset() {
        return AndroidUtilities.dp(56.0f);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getCurrentItemTop() {
        if (this.listView.getChildCount() > 0) goto L6;
        return Integer.MAX_VALUE;
    L6:
        int r1 = 0;
        RecyclerListView.Holder r0 = (RecyclerListView.Holder) this.listView.findViewHolderForAdapterPosition(0);
        if (r0 == null) goto L10;
        r1 = Math.max(((int) r0.itemView.getY()) - this.nonClipSize, 0);
    L10:
        return r1 + AndroidUtilities.dp(56.0f);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getFirstOffset() {
        return getListTopPadding() + AndroidUtilities.dp(56.0f);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getListTopPadding() {
        return this.listView.getPaddingTop();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        ChatAttachAlertLocationLayout$$ExternalSyntheticLambda4 r8 = new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda4(this);
        r1.add(new ThemeDescription(this.mapViewClip, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_dialogBackground));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_dialogScrollGlow));
        ActionBarMenuItem r2 = this.searchItem;
        if (r2 == null) goto L6;
        EditTextBoldCursor r22 = r2.getSearchField();
    L5:
        EditTextBoldCursor r12 = r22;
        r1.add(new ThemeDescription(r12, ThemeDescription.FLAG_CURSORCOLOR, null, null, null, null, Theme.key_dialogTextBlack));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        ImageView r15 = this.emptyImageView;
        int r16 = ThemeDescription.FLAG_IMAGECOLOR;
        int r21 = Theme.key_dialogEmptyImage;
        r1.add(new ThemeDescription(r15, r16, null, null, null, null, r21));
        TextView r23 = this.emptyTitleTextView;
        int r24 = ThemeDescription.FLAG_TEXTCOLOR;
        int r162 = Theme.key_dialogEmptyText;
        r1.add(new ThemeDescription(r23, r24, null, null, null, null, r162));
        r1.add(new ThemeDescription(this.emptySubtitleTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r162));
        ImageView r25 = this.locationButton;
        int r242 = ThemeDescription.FLAG_IMAGECOLOR | ThemeDescription.FLAG_CHECKTAG;
        int r9 = Theme.key_location_actionIcon;
        r1.add(new ThemeDescription(r25, r242, null, null, null, null, r9));
        ImageView r26 = this.locationButton;
        int r243 = ThemeDescription.FLAG_IMAGECOLOR | ThemeDescription.FLAG_CHECKTAG;
        int r32 = Theme.key_location_actionActiveIcon;
        r1.add(new ThemeDescription(r26, r243, null, null, null, null, r32));
        ImageView r27 = this.locationButton;
        int r244 = ThemeDescription.FLAG_BACKGROUNDFILTER;
        int r40 = Theme.key_location_actionBackground;
        r1.add(new ThemeDescription(r27, r244, null, null, null, null, r40));
        ImageView r28 = this.locationButton;
        int r245 = ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE;
        int r48 = Theme.key_location_actionPressedBackground;
        r1.add(new ThemeDescription(r28, r245, null, null, null, null, r48));
        r1.add(new ThemeDescription(this.mapTypeButton, 0, null, null, null, r8, r9));
        r1.add(new ThemeDescription(this.mapTypeButton, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, r40));
        r1.add(new ThemeDescription(this.mapTypeButton, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, r48));
        r1.add(new ThemeDescription(this.searchAreaButton, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r32));
        r1.add(new ThemeDescription(this.searchAreaButton, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, r40));
        r1.add(new ThemeDescription(this.searchAreaButton, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, r48));
        r1.add(new ThemeDescription(null, 0, null, null, Theme.avatarDrawables, r8, Theme.key_avatar_text));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundRed));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundOrange));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundViolet));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundGreen));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundCyan));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundBlue));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundPink));
        r1.add(new ThemeDescription(null, 0, null, null, null, null, Theme.key_location_liveLocationProgress));
        r1.add(new ThemeDescription(null, 0, null, null, null, null, Theme.key_location_placeLocationBackground));
        r1.add(new ThemeDescription(null, 0, null, null, null, null, Theme.key_dialog_liveLocationProgress));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_USEBACKGROUNDDRAWABLE | ThemeDescription.FLAG_CHECKTAG, new Class[]{SendLocationCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_location_sendLocationIcon));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_USEBACKGROUNDDRAWABLE | ThemeDescription.FLAG_CHECKTAG, new Class[]{SendLocationCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_location_sendLiveLocationIcon));
        r1.add(new ThemeDescription(this.listView, (ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_USEBACKGROUNDDRAWABLE) | ThemeDescription.FLAG_CHECKTAG, new Class[]{SendLocationCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_location_sendLocationBackground));
        RecyclerListView r6 = this.listView;
        int r4 = ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_USEBACKGROUNDDRAWABLE;
        r1.add(new ThemeDescription(r6, ThemeDescription.FLAG_CHECKTAG | r4, new Class[]{SendLocationCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_location_sendLiveLocationBackground));
        int r30 = Theme.key_windowBackgroundWhiteGrayText3;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{SendLocationCell.class}, new String[]{"accurateTextView"}, null, null, null, r30));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{SendLocationCell.class}, new String[]{"titleTextView"}, null, null, null, Theme.key_location_sendLiveLocationText));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{SendLocationCell.class}, new String[]{"titleTextView"}, null, null, null, Theme.key_location_sendLocationText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{LocationDirectionCell.class}, new String[]{"buttonTextView"}, null, null, null, Theme.key_featuredStickers_buttonText));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_USEBACKGROUNDDRAWABLE, new Class[]{LocationDirectionCell.class}, new String[]{"frameLayout"}, null, null, null, Theme.key_featuredStickers_addButton));
        RecyclerListView r7 = this.listView;
        int r5 = ThemeDescription.FLAG_USEBACKGROUNDDRAWABLE;
        r1.add(new ThemeDescription(r7, ThemeDescription.FLAG_DRAWABLESELECTEDSTATE | r5, new Class[]{LocationDirectionCell.class}, new String[]{"frameLayout"}, null, null, null, Theme.key_featuredStickers_addButtonPressed));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ShadowSectionCell.class}, null, null, null, Theme.key_windowBackgroundGrayShadow));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{ShadowSectionCell.class}, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_dialogTextBlue2));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{LocationCell.class}, new String[]{"imageView"}, null, null, null, r30));
        int r39 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{LocationCell.class}, new String[]{"nameTextView"}, null, null, null, r39));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{LocationCell.class}, new String[]{"addressTextView"}, null, null, null, r30));
        r1.add(new ThemeDescription(this.searchListView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{LocationCell.class}, new String[]{"imageView"}, null, null, null, r30));
        r1.add(new ThemeDescription(this.searchListView, 0, new Class[]{LocationCell.class}, new String[]{"nameTextView"}, null, null, null, r39));
        r1.add(new ThemeDescription(this.searchListView, 0, new Class[]{LocationCell.class}, new String[]{"addressTextView"}, null, null, null, r30));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{SharingLiveLocationCell.class}, new String[]{"nameTextView"}, null, null, null, r39));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{SharingLiveLocationCell.class}, new String[]{"distanceTextView"}, null, null, null, r30));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{LocationLoadingCell.class}, new String[]{"progressBar"}, null, null, null, Theme.key_progressCircle));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{LocationLoadingCell.class}, new String[]{"textView"}, null, null, null, r30));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{LocationLoadingCell.class}, new String[]{"imageView"}, null, null, null, r30));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{LocationPoweredCell.class}, new String[]{"textView"}, null, null, null, r30));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{LocationPoweredCell.class}, new String[]{"imageView"}, null, null, null, r21));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{LocationPoweredCell.class}, new String[]{"textView2"}, null, null, null, r162));
        return r1;
    L6:
        r22 = null;
        goto L5
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int needsActionBar() {
        return 1;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onDestroy() {
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.locationPermissionGranted);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.locationPermissionDenied);
        this.doNotDrawMap = true;
        FrameLayout r0 = this.mapViewClip;
        if (r0 == null) goto L32;
        r0.invalidate();
    L32:
        IMapsProvider.IMap r02 = this.map;     // Catch: Exception -> L9
        if (r02 == null) goto L11;
        r02.setMyLocationEnabled(false);     // Catch: Exception -> L9
    L11:
        IMapsProvider.IMapView r03 = this.mapView;
        if (r03 == null) goto L34;
        r03.getView().setTranslationY((-AndroidUtilities.displaySize.y) * 3);
    L34:
        IMapsProvider.IMapView r04 = this.mapView;     // Catch: Exception -> L28
        if (r04 == null) goto L30;
        r04.onPause();     // Catch: Exception -> L28
    L30:
        IMapsProvider.IMapView r05 = this.mapView;     // Catch: Exception -> L29
        if (r05 == null) goto L20;
        r05.onDestroy();     // Catch: Exception -> L29
        this.mapView = null;     // Catch: Exception -> L29
    L20:
        LocationActivityAdapter r06 = this.adapter;
        if (r06 == null) goto L23;
        r06.destroy();
    L23:
        LocationActivitySearchAdapter r07 = this.searchAdapter;
        if (r07 == null) goto L26;
        r07.destroy();
    L26:
        this.parentAlert.actionBar.closeSearchField();
        this.parentAlert.actionBar.createMenu().removeView(this.searchItem);
        return;
    L9:
        e = move-exception;
        FileLog.e(e);
        goto L11
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean onDismiss() {
        onDestroy();
        return false;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onHidden() {
        onPause();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onHide() {
        this.searchItem.setVisibility(8);
        this.onResumeCalled = false;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        if (r1 == false) goto L6;
        fixLayoutInternal(this.first);
        this.first = false;
        return;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onPanTransitionEnd() {
        LocationActivityAdapter r0 = this.adapter;
        ChatAttachAlert r1 = this.parentAlert;
        if (r1 != null) goto L5;
    L7:
        boolean r12 = false;
    L8:
        r0.animated = r12;
        return;
    L5:
        if (r1.isKeyboardVisible() == true) goto L7;
        r12 = true;
        goto L8
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onPanTransitionStart(boolean r1, int r2) {
        if (r1 == false) goto L5;
        this.adapter.animated = false;
        return;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onPause() {
        IMapsProvider.IMapView r0 = this.mapView;
        if (r0 != null) goto L5;
    L10:
        this.onResumeCalled = false;
        return;
    L5:
        if (this.mapsInitialized == false) goto L10;
        r0.onPause();     // Catch: Exception -> L8
    L8:
        e = move-exception;
        FileLog.e(e);
        goto L10
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onPreMeasure(int r3, int r4) {
        if (this.parentAlert.actionBar.isSearchFieldVisible() == false) goto L5;
    L17:
        int r32 = this.mapHeight - this.overScrollHeight;
        this.parentAlert.setAllowNestedScroll(false);
    L18:
        this.listView.setPaddingWithoutRequestLayout(0, r32, 0, this.listPaddingBottom);
        this.searchListView.setPaddingWithoutRequestLayout(0, 0, 0, this.listPaddingBottom);
        return;
    L5:
        if (this.parentAlert.sizeNotifierFrameLayout.measureKeyboardHeight() > AndroidUtilities.dp(20.0f)) goto L17;
        if (AndroidUtilities.isTablet() == true) goto L12;
        Point r33 = AndroidUtilities.displaySize;
        if (r33.x <= r33.y) goto L12;
        int r34 = (int) (r4 / 3.5f);
    L13:
        r32 = r34 - AndroidUtilities.dp(52.0f);
        if (r32 >= 0) goto L16;
        r32 = 0;
    L16:
        this.parentAlert.setAllowNestedScroll(true);
    L12:
        r34 = (r4 / 5) * 2;
        goto L13
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onResume() {
        if (this.parentAlert.isShowing() == true) goto L5;
    L19:
        this.onResumeCalled = false;
        return;
    L5:
        if (getVisibility() != 0) goto L19;
        if (getParent() == null) goto L19;
        IMapsProvider.IMapView r0 = this.mapView;
        if (r0 != null) goto L12;
    L17:
        this.onResumeCalled = true;
        return;
    L12:
        if (this.mapsInitialized == false) goto L17;
        r0.onResume();     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        FileLog.e(th);
        goto L17
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onShow(ChatAttachAlert.AttachAlertLayout r8) {
        this.parentAlert.actionBar.setTitle(LocaleController.getString(R.string.ShareLocation));
        if (this.mapView.getView().getParent() != null) goto L5;
        this.mapViewClip.addView(this.mapView.getView(), 0, LayoutHelper.createFrame(-1, this.overScrollHeight + AndroidUtilities.dp(10.0f), 51));
        this.mapViewClip.addView(this.overlayView, 1, LayoutHelper.createFrame(-1, this.overScrollHeight + AndroidUtilities.dp(10.0f), 51));
        this.mapViewClip.addView(this.loadingMapView, 2, LayoutHelper.createFrame(-1, -1.0f));
    L5:
        this.searchItem.setVisibility(0);
        IMapsProvider.IMapView r82 = this.mapView;
        if (r82 != null) goto L8;
    L13:
        this.onResumeCalled = true;
        IMapsProvider.IMap r83 = this.map;
        if (r83 != null) goto L27;
    L19:
        fixLayoutInternal(true);
        boolean r84 = this.parentAlert.delegate.needEnterComment();
        ChatAttachAlertLocationLayout$$ExternalSyntheticLambda18 r0 = new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda18(this);
        if (r84 == false) goto L22;
        long r2 = 200;
    L23:
        AndroidUtilities.runOnUIThread(r0, r2);
        this.layoutManager.scrollToPositionWithOffset(0, 0);
        updateClipView();
        return;
    L22:
        r2 = 0;
        goto L23
    L27:
        r83.setMyLocationEnabled(true);     // Catch: Exception -> L17
    L17:
        e = move-exception;
        FileLog.e(e);
        goto L19
    L8:
        if (this.mapsInitialized == false) goto L13;
        r82.onResume();     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        FileLog.e(th);
        goto L13
    }

    public void openShareLiveLocation() {
        if (this.delegate != null) goto L5;
        return;
    L5:
        if (getParentActivity() != null) goto L7;
        return;
    L7:
        if (this.myLocation != null) goto L10;
        return;
    L10:
        if (this.checkBackgroundPermission == false) goto L22;
        if (Build.VERSION.SDK_INT < 29) goto L22;
        Activity r0 = getParentActivity();
        if (r0 == null) goto L22;
        this.checkBackgroundPermission = false;
        SharedPreferences r2 = MessagesController.getGlobalMainSettings();
        if (Math.abs((System.currentTimeMillis() / 1000) - r2.getInt("backgroundloc", 0)) <= 86400) goto L22;
        if (r0.checkSelfPermission("android.permission.ACCESS_BACKGROUND_LOCATION") == 0) goto L22;
        r2.edit().putInt("backgroundloc", (int) (System.currentTimeMillis() / 1000)).apply();
        AlertsCreator.createBackgroundLocationPermissionDialog(r0, getMessagesController().getUser(Long.valueOf(getUserConfig().getClientUserId())), new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda19(this), this.resourcesProvider).show();
        return;
    L22:
        if (DialogObject.isUserDialog(this.dialogId) == false) goto L24;
        TLRPC.User r02 = this.parentAlert.baseFragment.getMessagesController().getUser(Long.valueOf(this.dialogId));
    L25:
        AlertsCreator.createLocationUpdateDialog(getParentActivity(), false, r02, new ChatAttachAlertLocationLayout$$ExternalSyntheticLambda20(this), this.resourcesProvider).show();
        return;
    L24:
        r02 = null;
        goto L25
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void scrollToTop() {
        this.listView.smoothScrollToPosition(0);
    }

    public void setDelegate(LocationActivityDelegate r1) {
        this.delegate = r1;
    }

    @Override // android.view.View
    public void setTranslationY(float r1) {
        super.setTranslationY(r1);
        this.parentAlert.getSheetContainer().invalidate();
        updateClipView();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean shouldHideBottomButtons() {
        return !this.locationDenied;
    }
}
