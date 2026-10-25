package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.text.TextUtils;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewPropertyAnimator;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.collection.LongSparseArray;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.cast.MediaError;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.IMapsProvider;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.LocationController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.utils.ViewOutlineProviderImpl;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Adapters.LocationActivityAdapter;
import org.telegram.ui.Adapters.LocationActivitySearchAdapter;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.LocationCell;
import org.telegram.ui.Cells.LocationDirectionCell;
import org.telegram.ui.Cells.LocationLoadingCell;
import org.telegram.ui.Cells.LocationPoweredCell;
import org.telegram.ui.Cells.SendLocationCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.SharingLiveLocationCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.MapPlaceholderDrawable;
import org.telegram.ui.Components.ProximitySheet;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.SharedMediaLayout;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.UndoView;
import org.telegram.ui.Stories.recorder.HintView2;

/* loaded from: classes6.dex */
public class LocationActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private LocationActivityAdapter adapter;
    private AnimatorSet animatorSet;
    private int askWithRadius;
    private Bitmap[] bitmapCache;
    private boolean canUndo;
    private TLRPC.TL_channelLocation chatLocation;
    private boolean checkBackgroundPermission;
    private boolean checkGpsEnabled;
    private boolean checkPermission;
    private boolean currentMapStyleDark;
    private LocationActivityDelegate delegate;
    private long dialogId;
    private ImageView emptyImageView;
    private TextView emptySubtitleTextView;
    private TextView emptyTitleTextView;
    private LinearLayout emptyView;
    private boolean firstFocus;
    private boolean firstWas;
    private IMapsProvider.ICameraUpdate forceUpdate;
    public boolean fromStories;
    private boolean hasScreenshot;
    private HintView2 hintView;
    private TLRPC.TL_channelLocation initialLocation;
    private boolean initialMaxZoom;
    private boolean isFirstLocation;
    private boolean isSharingAllowed;
    private IMapsProvider.IMarker lastPressedMarker;
    private FrameLayout lastPressedMarkerView;
    private VenueLocation lastPressedVenue;
    private LinearLayoutManager layoutManager;
    private RecyclerListView listView;
    private ImageView locationButton;
    private boolean locationDenied;
    private int locationType;
    private IMapsProvider.IMap map;
    private ActionBarMenuItem mapTypeButton;
    private IMapsProvider.IMapView mapView;
    private FrameLayout mapViewClip;
    private boolean mapsInitialized;
    private Runnable markAsReadRunnable;
    private View markerImageView;
    private int markerTop;
    private ArrayList<LiveLocation> markers;
    private LongSparseArray<LiveLocation> markersMap;
    private MessageObject messageObject;
    private IMapsProvider.ICameraUpdate moveToBounds;
    private Location myLocation;
    private boolean onResumeCalled;
    private ActionBarMenuItem otherItem;
    private int overScrollHeight;
    private MapOverlayView overlayView;
    private ChatActivity parentFragment;
    private ArrayList<VenueLocation> placeMarkers;
    private ActionBarPopupWindow popupWindow;
    private double previousRadius;
    private boolean proximityAnimationInProgress;
    private ImageView proximityButton;
    private IMapsProvider.ICircle proximityCircle;
    private ProximitySheet proximitySheet;
    private boolean scrolling;
    private LocationActivitySearchAdapter searchAdapter;
    private SearchButton searchAreaButton;
    private boolean searchInProgress;
    private ActionBarMenuItem searchItem;
    private RecyclerListView searchListView;
    private TL_stories.MediaArea searchStoriesArea;
    private boolean searchWas;
    private boolean searchedForCustomLocations;
    private boolean searching;
    private long selectedMarkerId;
    private View shadow;
    private Drawable shadowDrawable;
    private GraySectionCell sharedMediaHeader;
    private SharedMediaLayout sharedMediaLayout;
    private TextView showAllButton;
    private boolean showAllMode;
    private Boolean shownShowAllButton;
    private UndoView[] undoView;
    private Runnable updateRunnable;
    private Location userLocation;
    private boolean userLocationMoved;
    private float yOffset;

    /* renamed from: org.telegram.ui.LocationActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ LocationActivity this$0;

        public AnonymousClass1(LocationActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r9) {
            if (r9 != (-1)) goto L7;
            this.this$0.finishFragment();
            return;
        L7:
            if (r9 != 1) goto L14;
            double r2 = LocationActivity.m15732$$Nest$fgetmessageObject(this.this$0).messageOwner.media.geo.lat;     // Catch: Exception -> L10
            double r4 = LocationActivity.m15732$$Nest$fgetmessageObject(this.this$0).messageOwner.media.geo._long;     // Catch: Exception -> L10
            this.this$0.getParentActivity().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("geo:" + r2 + "," + r4 + "?q=" + r2 + "," + r4)));     // Catch: Exception -> L10
            return;
        L10:
            e = move-exception;
            FileLog.e(e);
            return;
        L14:
            if (r9 != 5) goto L18;
            LocationActivity.m15763$$Nest$mopenShareLiveLocation(this.this$0, false, 0);
            return;
        L18:
            if (r9 != 6) goto L23;
            LocationActivity.m15762$$Nest$mopenDirections(this.this$0, null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.LocationActivity$10, reason: invalid class name */
    public class AnonymousClass10 extends RecyclerView.OnScrollListener {
        final /* synthetic */ LocationActivity this$0;

        public AnonymousClass10(LocationActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 == 1) goto L5;
            return;
        L5:
            if (LocationActivity.m15742$$Nest$fgetsearching(this.this$0) == true) goto L7;
            return;
        L7:
            if (LocationActivity.m15741$$Nest$fgetsearchWas(this.this$0) == false) goto L12;
            AndroidUtilities.hideKeyboard(this.this$0.getParentActivity().getCurrentFocus());
            return;
        }
    }

    /* renamed from: org.telegram.ui.LocationActivity$11, reason: invalid class name */
    public class AnonymousClass11 extends View {
        private RectF rect;
        final /* synthetic */ LocationActivity this$0;
        final /* synthetic */ Rect val$padding;

        public AnonymousClass11(LocationActivity r1, Context r2, Rect r3) {
            this.this$0 = r1;
            this.val$padding = r3;
            super(r2);
            this.rect = new RectF();
        }

        @Override // android.view.View
        public void onDraw(Canvas r7) {
            LocationActivity.m15743$$Nest$fgetshadowDrawable(this.this$0).setBounds(-this.val$padding.left, 0, getMeasuredWidth() + this.val$padding.right, getMeasuredHeight());
            LocationActivity.m15743$$Nest$fgetshadowDrawable(this.this$0).draw(r7);
            if (LocationActivity.m15729$$Nest$fgetlocationType(this.this$0) != 0) goto L5;
        L8:
            int r0 = AndroidUtilities.dp(36.0f);
            this.rect.set((getMeasuredWidth() - r0) / 2, this.val$padding.top + AndroidUtilities.dp(10.0f), (getMeasuredWidth() + r0) / 2, r1 + AndroidUtilities.dp(4.0f));
            int r02 = this.this$0.getThemedColor(Theme.key_sheet_scrollUp);
            Color.alpha(r02);
            Theme.dialogs_onlineCirclePaint.setColor(r02);
            r7.drawRoundRect(this.rect, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), Theme.dialogs_onlineCirclePaint);
            return;
        L5:
            if (LocationActivity.m15729$$Nest$fgetlocationType(this.this$0) == 1) goto L8;
        }
    }

    /* renamed from: org.telegram.ui.LocationActivity$12, reason: invalid class name */
    public class AnonymousClass12 extends AnimatorListenerAdapter {
        final /* synthetic */ LocationActivity this$0;

        public AnonymousClass12(LocationActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            LocationActivity.m15753$$Nest$fputproximityAnimationInProgress(this.this$0, false);
            LocationActivity.m15761$$Nest$mmaybeShowProximityHint(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.LocationActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        final /* synthetic */ LocationActivity this$0;

        public AnonymousClass2(LocationActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchCollapse() {
            LocationActivity.m15757$$Nest$fputsearching(this.this$0, false);
            LocationActivity.m15756$$Nest$fputsearchWas(this.this$0, false);
            LocationActivity.m15737$$Nest$fgetsearchAdapter(this.this$0).searchDelayed(null, null);
            LocationActivity.m15766$$Nest$mupdateEmptyView(this.this$0);
            if (LocationActivity.m15729$$Nest$fgetlocationType(this.this$0) == 8) goto L5;
            return;
        L5:
            if (LocationActivity.m15733$$Nest$fgetotherItem(this.this$0) == null) goto L7;
            LocationActivity.m15733$$Nest$fgetotherItem(this.this$0).setVisibility(0);
        L7:
            LocationActivity.m15728$$Nest$fgetlistView(this.this$0).setVisibility(0);
            LocationActivity.m15731$$Nest$fgetmapViewClip(this.this$0).setVisibility(0);
            LocationActivity.m15739$$Nest$fgetsearchListView(this.this$0).setAdapter(null);
            LocationActivity.m15739$$Nest$fgetsearchListView(this.this$0).setVisibility(8);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
            LocationActivity.m15757$$Nest$fputsearching(this.this$0, true);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r5) {
            if (LocationActivity.m15737$$Nest$fgetsearchAdapter(this.this$0) != null) goto L5;
            return;
        L5:
            String r52 = r5.getText().toString();
            int r0 = r52.length();
            LocationActivity r1 = this.this$0;
            boolean r3 = false;
            if (r0 == 0) goto L18;
            LocationActivity.m15756$$Nest$fputsearchWas(r1, true);
            LocationActivity.m15738$$Nest$fgetsearchItem(this.this$0).setShowSearchProgress(true);
            if (LocationActivity.m15733$$Nest$fgetotherItem(this.this$0) == null) goto L10;
            LocationActivity.m15733$$Nest$fgetotherItem(this.this$0).setVisibility(8);
        L10:
            LocationActivity.m15728$$Nest$fgetlistView(this.this$0).setVisibility(8);
            LocationActivity.m15731$$Nest$fgetmapViewClip(this.this$0).setVisibility(8);
            if (LocationActivity.m15739$$Nest$fgetsearchListView(this.this$0).getAdapter() == LocationActivity.m15737$$Nest$fgetsearchAdapter(this.this$0)) goto L13;
            LocationActivity.m15739$$Nest$fgetsearchListView(this.this$0).setAdapter(LocationActivity.m15737$$Nest$fgetsearchAdapter(this.this$0));
        L13:
            LocationActivity.m15739$$Nest$fgetsearchListView(this.this$0).setVisibility(0);
            LocationActivity r12 = this.this$0;
            if (LocationActivity.m15737$$Nest$fgetsearchAdapter(r12).getItemCount() != 0) goto L16;
            r3 = true;
        L16:
            LocationActivity.m15755$$Nest$fputsearchInProgress(r12, r3);
        L21:
            LocationActivity.m15766$$Nest$mupdateEmptyView(this.this$0);
            LocationActivity.m15737$$Nest$fgetsearchAdapter(this.this$0).searchDelayed(r52, LocationActivity.m15746$$Nest$fgetuserLocation(this.this$0));
            return;
        L18:
            if (LocationActivity.m15733$$Nest$fgetotherItem(r1) == null) goto L20;
            LocationActivity.m15733$$Nest$fgetotherItem(this.this$0).setVisibility(0);
        L20:
            LocationActivity.m15728$$Nest$fgetlistView(this.this$0).setVisibility(0);
            LocationActivity.m15731$$Nest$fgetmapViewClip(this.this$0).setVisibility(0);
            LocationActivity.m15739$$Nest$fgetsearchListView(this.this$0).setAdapter(null);
            LocationActivity.m15739$$Nest$fgetsearchListView(this.this$0).setVisibility(8);
            goto L21
        }
    }

    /* renamed from: org.telegram.ui.LocationActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends FrameLayout {
        final /* synthetic */ LocationActivity this$0;

        public AnonymousClass3(LocationActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            if (LocationActivity.m15734$$Nest$fgetoverlayView(this.this$0) == null) goto L6;
            LocationActivity.m15734$$Nest$fgetoverlayView(this.this$0).updatePositions();
            return;
        }
    }

    /* renamed from: org.telegram.ui.LocationActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends LocationActivityAdapter {
        private boolean firstSet;
        final /* synthetic */ LocationActivity this$0;

        public AnonymousClass4(LocationActivity r1, Context r2, int r3, long r4, boolean r6, Theme.ResourcesProvider r7, boolean r8, boolean r9, boolean r10) {
            this.this$0 = r1;
            super(r2, r3, r4, r6, r7, r8, r9, r10);
            this.firstSet = true;
        }

        @Override // org.telegram.ui.Adapters.LocationActivityAdapter
        public void onDirectionClick() {
            LocationActivity.m15762$$Nest$mopenDirections(this.this$0, null);
        }

        @Override // org.telegram.ui.Adapters.LocationActivityAdapter
        public void setLiveLocations(ArrayList<LiveLocation> r7) {
            if (LocationActivity.m15732$$Nest$fgetmessageObject(this.this$0) != null) goto L5;
        L27:
            super.setLiveLocations(r7);
            return;
        L5:
            if (LocationActivity.m15732$$Nest$fgetmessageObject(this.this$0).isLiveLocation() == false) goto L27;
            int r0 = 0;
            if (r7 == null) goto L17;
            int r1 = 0;
            int r2 = 0;
        L10:
            if (r1 >= r7.size()) goto L19;
            LiveLocation r3 = r7.get(r1);
            if (r3 == null) goto L16;
            if (UserObject.isUserSelf(r3.user) == true) goto L16;
            r2 = r2 + 1;
        L16:
            r1 = r1 + 1;
        L19:
            if (this.firstSet == false) goto L22;
            if (r2 != 1) goto L22;
            LocationActivity.m15758$$Nest$fputselectedMarkerId(this.this$0, r7.get(0).id);
        L22:
            this.firstSet = false;
            ActionBarMenuItem r12 = LocationActivity.m15733$$Nest$fgetotherItem(this.this$0);
            if (r2 == 1) goto L26;
            r0 = 8;
        L26:
            r12.setVisibility(r0);
            goto L27
        L17:
            r2 = 0;
            goto L19
        }
    }

    /* renamed from: org.telegram.ui.LocationActivity$5, reason: invalid class name */
    public class AnonymousClass5 implements SharedMediaLayout.Delegate {
        final /* synthetic */ LocationActivity this$0;

        public AnonymousClass5(LocationActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.Delegate
        public boolean canSearchMembers() {
            return false;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.Delegate
        public TLRPC.Chat getCurrentChat() {
            return null;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.Delegate
        public RecyclerListView getListView() {
            return LocationActivity.m15728$$Nest$fgetlistView(this.this$0);
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.Delegate
        public boolean isFragmentOpened() {
            return true;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.Delegate
        public boolean onMemberClick(TLRPC.ChatParticipant r1, boolean r2, boolean r3, View r4) {
            return false;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.Delegate
        public void scrollToSharedMedia() {
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.Delegate
        public void updateSelectedMediaTabText() {
            if (LocationActivity.m15745$$Nest$fgetsharedMediaLayout(this.this$0) != null) goto L5;
            int r0 = 0;
        L6:
            LocationActivity.m15744$$Nest$fgetsharedMediaHeader(this.this$0).setText(LocaleController.formatPluralString("LocationStories", r0, new Object[0]));
            LocationActivityAdapter r2 = LocationActivity.m15721$$Nest$fgetadapter(this.this$0);
            if (r0 <= 0) goto L9;
            boolean r02 = true;
        L11:
            if (r2.setSharedMediaLayoutVisible(r02) == false) goto L14;
            LocationActivity.m15728$$Nest$fgetlistView(this.this$0).smoothScrollBy(0, AndroidUtilities.dp(200.0f));
            return;
        L14:
            return;
        L9:
            r02 = false;
            goto L11
        L5:
            r0 = LocationActivity.m15745$$Nest$fgetsharedMediaLayout(this.this$0).getStoriesCount(8);
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.LocationActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends SharedMediaLayout {
        final /* synthetic */ LocationActivity this$0;

        public AnonymousClass6(LocationActivity r16, Context r17, long r18, SharedMediaLayout.SharedMediaPreloader r20, int r21, ArrayList r22, TLRPC.ChatFull r23, TLRPC.UserFull r24, int r25, int r26, BaseFragment r27, SharedMediaLayout.Delegate r28, int r29, Theme.ResourcesProvider r30) {
            this.this$0 = r16;
            super(r17, r18, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30);
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public boolean customTabs() {
            return true;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public TL_stories.MediaArea getStoriesArea() {
            return LocationActivity.m15740$$Nest$fgetsearchStoriesArea(this.this$0);
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public int mediaPageTopMargin() {
            return 32;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public int overrideColumnsCount() {
            return 3;
        }
    }

    /* renamed from: org.telegram.ui.LocationActivity$7, reason: invalid class name */
    public class AnonymousClass7 extends RecyclerView.OnScrollListener {
        final /* synthetic */ LocationActivity this$0;

        public AnonymousClass7(LocationActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            LocationActivity r12 = this.this$0;
            if (r2 == 0) goto L5;
            boolean r22 = true;
        L6:
            LocationActivity.m15754$$Nest$fputscrolling(r12, r22);
            if (LocationActivity.m15736$$Nest$fgetscrolling(this.this$0) == false) goto L9;
            return;
        L9:
            if (LocationActivity.m15724$$Nest$fgetforceUpdate(this.this$0) == null) goto L13;
            LocationActivity.m15748$$Nest$fputforceUpdate(this.this$0, null);
            return;
        L13:
            return;
        L5:
            r22 = false;
            goto L6
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            LocationActivity.m15765$$Nest$mupdateClipView(this.this$0, false);
            if (LocationActivity.m15724$$Nest$fgetforceUpdate(this.this$0) == null) goto L6;
            LocationActivity r0 = this.this$0;
            LocationActivity.m15759$$Nest$fputyOffset(r0, LocationActivity.m15747$$Nest$fgetyOffset(r0) + r3);
            return;
        }
    }

    /* renamed from: org.telegram.ui.LocationActivity$8, reason: invalid class name */
    public class AnonymousClass8 extends ActionBarPopupWindow {
        final /* synthetic */ LocationActivity this$0;

        public AnonymousClass8(LocationActivity r1, View r2, int r3, int r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarPopupWindow, android.widget.PopupWindow
        public void dismiss() {
            super.dismiss();
            LocationActivity.m15752$$Nest$fputpopupWindow(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.LocationActivity$9, reason: invalid class name */
    public class AnonymousClass9 extends LocationActivitySearchAdapter {
        final /* synthetic */ LocationActivity this$0;

        public AnonymousClass9(LocationActivity r1, Context r2, Theme.ResourcesProvider r3, boolean r4, boolean r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            if (LocationActivity.m15738$$Nest$fgetsearchItem(this.this$0) == null) goto L6;
            LocationActivity.m15738$$Nest$fgetsearchItem(this.this$0).setShowSearchProgress(LocationActivity.m15737$$Nest$fgetsearchAdapter(this.this$0).isSearching());
        L6:
            if (LocationActivity.m15723$$Nest$fgetemptySubtitleTextView(this.this$0) == null) goto L8;
            LocationActivity.m15723$$Nest$fgetemptySubtitleTextView(this.this$0).setText(AndroidUtilities.replaceTags(LocaleController.formatString("NoPlacesFoundInfo", R.string.NoPlacesFoundInfo, new Object[]{LocationActivity.m15737$$Nest$fgetsearchAdapter(this.this$0).getLastSearchString()})));
        L8:
            super.notifyDataSetChanged();
        }
    }

    public static class LiveLocation {
        public ImageReceiver avatarReceiver;
        public TLRPC.Chat chat;
        public IMapsProvider.IMarker directionMarker;
        public boolean hasRotation;
        public long id;
        public IMapsProvider.IMarker marker;
        public TLRPC.Message object;
        public TLRPC.User user;

        public LiveLocation() {
        }
    }

    /* loaded from: classes3.dex */
    public interface LocationActivityDelegate {
        void didSelectLocation(TLRPC.MessageMedia r1, int r2, boolean r3, int r4, long r5);
    }

    public class MapOverlayView extends FrameLayout {
        final /* synthetic */ LocationActivity this$0;
        private HashMap<IMapsProvider.IMarker, View> views;

        /* renamed from: org.telegram.ui.LocationActivity$MapOverlayView$1, reason: invalid class name */
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
                if (LocationActivity.m15726$$Nest$fgetlastPressedMarkerView(this.this$1.this$0) == null) goto L10;
                AnimatorSet r0 = new AnimatorSet();
                r0.playTogether(new Animator[]{ObjectAnimator.ofFloat(LocationActivity.m15726$$Nest$fgetlastPressedMarkerView(this.this$1.this$0), View.SCALE_X, new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(LocationActivity.m15726$$Nest$fgetlastPressedMarkerView(this.this$1.this$0), View.SCALE_Y, new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(LocationActivity.m15726$$Nest$fgetlastPressedMarkerView(this.this$1.this$0), View.ALPHA, new float[]{0.0f, 1.0f})});
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

        /* renamed from: $r8$lambda$LknAM6Li-X0VM0_90X2keWbe3e4, reason: not valid java name */
        public static /* synthetic */ void m15767$r8$lambda$LknAM6LiX0VM0_90X2keWbe3e4(MapOverlayView r0, VenueLocation r1, boolean r2, int r3, int r4) {
            r0.lambda$addInfoView$0(r1, r2, r3, r4);
        }

        /* renamed from: $r8$lambda$RSn-im1eVwqg43YRj1jI2wOwdh0, reason: not valid java name */
        public static /* synthetic */ void m15768$r8$lambda$RSnim1eVwqg43YRj1jI2wOwdh0(MapOverlayView r0, VenueLocation r1, View r2) {
            r0.lambda$addInfoView$1(r1, r2);
        }

        public MapOverlayView(LocationActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.views = new HashMap();
        }

        private /* synthetic */ void lambda$addInfoView$0(VenueLocation r8, boolean r9, int r10, int r11) {
            LocationActivity.m15722$$Nest$fgetdelegate(this.this$0).didSelectLocation(r8.venue, LocationActivity.m15729$$Nest$fgetlocationType(this.this$0), r9, r10, 0);
            this.this$0.finishFragment();
        }

        private /* synthetic */ void lambda$addInfoView$1(VenueLocation r8, View r9) {
            if (LocationActivity.m15735$$Nest$fgetparentFragment(this.this$0) != null) goto L5;
        L8:
            LocationActivity.m15722$$Nest$fgetdelegate(this.this$0).didSelectLocation(r8.venue, LocationActivity.m15729$$Nest$fgetlocationType(this.this$0), true, 0, 0);
            this.this$0.finishFragment();
            return;
        L5:
            if (LocationActivity.m15735$$Nest$fgetparentFragment(this.this$0).isInScheduleMode() == false) goto L8;
            AlertsCreator.createScheduleDatePickerDialog(this.this$0.getParentActivity(), LocationActivity.m15735$$Nest$fgetparentFragment(this.this$0).getDialogId(), new LocationActivity$MapOverlayView$$ExternalSyntheticLambda1(this, r8));
        }

        public void addInfoView(IMapsProvider.IMarker r21) {
            VenueLocation r2 = (VenueLocation) r21.getTag();
            if (r2 != null) goto L5;
            return;
        L5:
            if (LocationActivity.m15727$$Nest$fgetlastPressedVenue(this.this$0) == r2) goto L28;
            LocationActivity.m15764$$Nest$mshowSearchPlacesButton(this.this$0, false);
            if (LocationActivity.m15725$$Nest$fgetlastPressedMarker(this.this$0) == null) goto L10;
            removeInfoView(LocationActivity.m15725$$Nest$fgetlastPressedMarker(this.this$0));
            LocationActivity.m15749$$Nest$fputlastPressedMarker(this.this$0, null);
        L10:
            LocationActivity.m15751$$Nest$fputlastPressedVenue(this.this$0, r2);
            LocationActivity.m15749$$Nest$fputlastPressedMarker(this.this$0, r21);
            Context r3 = getContext();
            FrameLayout r5 = new FrameLayout(r3);
            addView(r5, LayoutHelper.createFrame(-2, 114.0f));
            LocationActivity.m15750$$Nest$fputlastPressedMarkerView(this.this$0, new FrameLayout(r3));
            LocationActivity.m15726$$Nest$fgetlastPressedMarkerView(this.this$0).setBackgroundResource(R.drawable.venue_tooltip);
            LocationActivity.m15726$$Nest$fgetlastPressedMarkerView(this.this$0).getBackground().setColorFilter(new PorterDuffColorFilter(this.this$0.getThemedColor(Theme.key_dialogBackground), PorterDuff.Mode.MULTIPLY));
            r5.addView(LocationActivity.m15726$$Nest$fgetlastPressedMarkerView(this.this$0), LayoutHelper.createFrame(-2, 71.0f));
            LocationActivity.m15726$$Nest$fgetlastPressedMarkerView(this.this$0).setAlpha(0.0f);
            LocationActivity.m15726$$Nest$fgetlastPressedMarkerView(this.this$0).setOnClickListener(new LocationActivity$MapOverlayView$$ExternalSyntheticLambda0(this, r2));
            TextView r6 = new TextView(r3);
            r6.setTextSize(1, 16.0f);
            r6.setMaxLines(1);
            TextUtils.TruncateAt r7 = TextUtils.TruncateAt.END;
            r6.setEllipsize(r7);
            r6.setSingleLine(true);
            r6.setTextColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
            r6.setTypeface(AndroidUtilities.bold());
            int r10 = 3;
            if (LocaleController.isRTL == false) goto L13;
            int r9 = 5;
        L14:
            r6.setGravity(r9);
            FrameLayout r92 = LocationActivity.m15726$$Nest$fgetlastPressedMarkerView(this.this$0);
            if (LocaleController.isRTL == false) goto L17;
            int r12 = 5;
        L18:
            r92.addView(r6, LayoutHelper.createFrame(-2, -2.0f, r12 | 48, 18.0f, 10.0f, 18.0f, 0.0f));
            TextView r93 = new TextView(r3);
            r93.setTextSize(1, 14.0f);
            r93.setMaxLines(1);
            r93.setEllipsize(r7);
            r93.setSingleLine(true);
            r93.setTextColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhiteGrayText3));
            if (LocaleController.isRTL == false) goto L21;
            int r72 = 5;
        L22:
            r93.setGravity(r72);
            FrameLayout r73 = LocationActivity.m15726$$Nest$fgetlastPressedMarkerView(this.this$0);
            if (LocaleController.isRTL == false) goto L25;
            r10 = 5;
        L25:
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
            LocationActivity.m15730$$Nest$fgetmap(this.this$0).animateCamera(ApplicationLoader.getMapsProvider().newCameraUpdateLatLng(r21.getPosition()), 300, null);
            return;
        L21:
            r72 = 3;
            goto L22
        L17:
            r12 = 3;
            goto L18
        L13:
            r9 = 3;
            goto L14
        }

        public void removeInfoView(IMapsProvider.IMarker r2) {
            View r0 = this.views.get(r2);
            if (r0 == null) goto L6;
            removeView(r0);
            this.views.remove(r2);
            return;
        }

        public void updatePositions() {
            if (LocationActivity.m15730$$Nest$fgetmap(this.this$0) == null) goto L9;
            IMapsProvider.IProjection r0 = LocationActivity.m15730$$Nest$fgetmap(this.this$0).getProjection();
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

    public class NestedFrameLayout extends SizeNotifierFrameLayout implements NestedScrollingParent3 {
        private boolean first;
        private NestedScrollingParentHelper nestedScrollingParentHelper;
        final /* synthetic */ LocationActivity this$0;

        /* renamed from: $r8$lambda$ynK0zIxOQI3TqZ-AoGfwUpNu_oY, reason: not valid java name */
        public static /* synthetic */ void m15769$r8$lambda$ynK0zIxOQI3TqZAoGfwUpNu_oY(NestedFrameLayout r0) {
            r0.lambda$onNestedScroll$0();
        }

        public NestedFrameLayout(LocationActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.first = true;
            this.nestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        }

        private /* synthetic */ void lambda$onNestedScroll$0() {
            RecyclerListView r1 = LocationActivity.m15745$$Nest$fgetsharedMediaLayout(this.this$0).getCurrentListView();     // Catch: Throwable -> L8
            if (r1 != null) goto L5;
            return;
        L5:
            if (r1.getAdapter() == null) goto L12;
            r1.getAdapter().notifyDataSetChanged();     // Catch: Throwable -> L8
            return;
        L12:
            return;
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r1, View r2, long r3) {
            boolean r32 = super.drawChild(r1, r2, r3);
            if (r2 == LocationActivity.access$000(this.this$0)) goto L5;
        L7:
            return r32;
        L5:
            if (LocationActivity.access$100(this.this$0) == null) goto L7;
            LocationActivity.access$300(this.this$0).drawHeaderShadow(r1, LocationActivity.access$200(this.this$0).getMeasuredHeight());
            goto L7
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public void drawList(Canvas r2, boolean r3, ArrayList<SizeNotifierFrameLayout.IViewWithInvalidateCallback> r4) {
            super.drawList(r2, r3, r4);
            if (LocationActivity.m15745$$Nest$fgetsharedMediaLayout(this.this$0) == null) goto L6;
            r2.save();
            r2.translate(0.0f, LocationActivity.m15728$$Nest$fgetlistView(this.this$0).getY());
            LocationActivity.m15745$$Nest$fgetsharedMediaLayout(this.this$0).drawListForBlur(r2, r4);
            r2.restore();
            return;
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            LocationActivity r22 = this.this$0;
            if (r1 == false) goto L6;
            LocationActivity.m15760$$Nest$mfixLayoutInternal(r22, this.first);
            this.first = false;
            return;
        L6:
            LocationActivity.m15765$$Nest$mupdateClipView(r22, true);
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public boolean onNestedPreFling(View r1, float r2, float r3) {
            return super.onNestedPreFling(r1, r2, r3);
        }

        @Override // androidx.core.view.NestedScrollingParent2
        public void onNestedPreScroll(View r5, int r6, int r7, int[] r8, int r9) {
            if (r5 == LocationActivity.m15728$$Nest$fgetlistView(this.this$0)) goto L5;
            return;
        L5:
            if (LocationActivity.m15745$$Nest$fgetsharedMediaLayout(this.this$0) != null) goto L7;
            return;
        L7:
            if (LocationActivity.m15745$$Nest$fgetsharedMediaLayout(this.this$0).isAttachedToWindow() == false) goto L43;
            boolean r52 = LocationActivity.access$400(this.this$0).isSearchFieldVisible();
            int r62 = LocationActivity.m15745$$Nest$fgetsharedMediaLayout(this.this$0).getTop();
            boolean r92 = false;
            if (r7 >= 0) goto L32;
            if (r62 > 0) goto L25;
            RecyclerListView r4 = LocationActivity.m15745$$Nest$fgetsharedMediaLayout(this.this$0).getCurrentListView();
            if (r4 == null) goto L25;
            int r1 = ((LinearLayoutManager) r4.getLayoutManager()).findFirstVisibleItemPosition();
            int r2 = -1;
            if (r1 == (-1)) goto L25;
            RecyclerView.ViewHolder r3 = r4.findViewHolderForAdapterPosition(r1);
            if (r3 == null) goto L18;
            r2 = r3.itemView.getTop();
        L18:
            int r32 = r4.getPaddingTop();
            if (r2 != r32) goto L21;
            if (r1 == 0) goto L25;
        L21:
            if (r1 == 0) goto L23;
            int r12 = r7;
        L24:
            r8[1] = r12;
            r4.scrollBy(0, r7);
            r92 = true;
            goto L25
        L23:
            r12 = Math.max(r7, r2 - r32);
        L25:
            if (r52 == false) goto L44;
            if (r92 == true) goto L30;
            if (r62 >= 0) goto L30;
            r8[1] = r7 - Math.max(r62, r7);
            return;
        L30:
            r8[1] = r7;
            return;
        L44:
            return;
        L32:
            if (r52 == false) goto L45;
            RecyclerListView r42 = LocationActivity.m15745$$Nest$fgetsharedMediaLayout(this.this$0).getCurrentListView();
            r8[1] = r7;
            if (r62 <= 0) goto L36;
            r8[1] = 0;
        L36:
            if (r42 == null) goto L46;
            int r53 = r8[1];
            if (r53 <= 0) goto L47;
            r42.scrollBy(0, r53);
            return;
        L47:
            return;
        L46:
            return;
        L45:
            return;
        }

        @Override // androidx.core.view.NestedScrollingParent2
        public void onNestedScroll(View r1, int r2, int r3, int r4, int r5, int r6) {
        }

        @Override // androidx.core.view.NestedScrollingParent3
        public void onNestedScroll(View r1, int r2, int r3, int r4, int r5, int r6, int[] r7) {
        L13:
            th = move-exception;
            FileLog.e(th);
            AndroidUtilities.runOnUIThread(new LocationActivity$NestedFrameLayout$$ExternalSyntheticLambda0(this));
            return;
        L3:
            if (r1 == LocationActivity.m15728$$Nest$fgetlistView(this.this$0)) goto L5;
            return;
        L5:
            if (LocationActivity.m15745$$Nest$fgetsharedMediaLayout(this.this$0) != null) goto L7;
            return;
        L7:
            if (LocationActivity.m15745$$Nest$fgetsharedMediaLayout(this.this$0).isAttachedToWindow() == false) goto L21;
            RecyclerListView r12 = LocationActivity.m15745$$Nest$fgetsharedMediaLayout(this.this$0).getCurrentListView();     // Catch: Throwable -> L13
            int r22 = LocationActivity.m15745$$Nest$fgetsharedMediaLayout(this.this$0).getTop();     // Catch: Throwable -> L13
            if (r12 == null) goto L22;
            if (r22 != 0) goto L23;
            r7[1] = r5;     // Catch: Throwable -> L13
            r12.scrollBy(0, r5);     // Catch: Throwable -> L13
            return;
        L23:
            return;
        L22:
            return;
        }

        @Override // androidx.core.view.NestedScrollingParent2
        public void onNestedScrollAccepted(View r1, View r2, int r3, int r4) {
            this.nestedScrollingParentHelper.onNestedScrollAccepted(r1, r2, r3);
        }

        @Override // androidx.core.view.NestedScrollingParent2
        public boolean onStartNestedScroll(View r1, View r2, int r3, int r4) {
            if (LocationActivity.m15745$$Nest$fgetsharedMediaLayout(this.this$0) != null) goto L5;
            return false;
        L5:
            if (r3 != 2) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public void onStopNestedScroll(View r1) {
        }

        @Override // androidx.core.view.NestedScrollingParent2
        public void onStopNestedScroll(View r1, int r2) {
            this.nestedScrollingParentHelper.onStopNestedScroll(r1);
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

    /* renamed from: $r8$lambda$-BkF2E2_Au-dA44_ASnEZagdIUw, reason: not valid java name */
    public static /* synthetic */ void m15704$r8$lambda$BkF2E2_AudA44_ASnEZagdIUw(LocationActivity r0) {
        r0.lambda$createView$22();
    }

    /* renamed from: $r8$lambda$-zZ8ZDmXkJEhdTJZt3iTNn04i-Q, reason: not valid java name */
    public static /* synthetic */ void m15705$r8$lambda$zZ8ZDmXkJEhdTJZt3iTNn04iQ(LocationActivity r0, IMapsProvider.IMap r1) {
        r0.lambda$createView$23(r1);
    }

    public static /* synthetic */ void $r8$lambda$1GSVn_xaLuYhMbPJc0IRKCfxRoM(LocationActivity r0) {
        r0.lambda$onMapInit$40();
    }

    /* renamed from: $r8$lambda$1jo6veZIuEQb8-FcCo03dVjOr04, reason: not valid java name */
    public static /* synthetic */ void m15706$r8$lambda$1jo6veZIuEQb8FcCo03dVjOr04(LocationActivity r0, ArrayList r1) {
        r0.lambda$createView$26(r1);
    }

    public static /* synthetic */ void $r8$lambda$3f6mSd7czT5zoHHDCRVt8WJVfcY(LocationActivity r0, boolean r1) {
        r0.lambda$openShareLiveLocation$34(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$4p0P_WQR4GqgIojJIyd6Ag1ZLVk(LocationActivity r0, MotionEvent r1, IMapsProvider.ICallableMethod r2) {
        return r0.lambda$createView$20(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$4rCqIktOw2ziA3iM0M03ya9Fajs(LocationActivity r0, View r1) {
        r0.lambda$createView$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$9GfvY6e3xPfKTEMqffwBHqsIt7w(LocationActivity r0, AlertDialog[] r1, TLRPC.TL_messageMediaVenue r2) {
        r0.lambda$createView$13(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$BJK_qNlRCe20ESGFRNo5R68XT2I(LocationActivity r0, int r1) {
        r0.lambda$onMapInit$37(r1);
    }

    public static /* synthetic */ void $r8$lambda$DHqxiAyrao1i85t_8QMrraOz3B4(LocationActivity r0) {
        r0.lambda$createView$10();
    }

    public static /* synthetic */ void $r8$lambda$DXEfCJWbv1DdTYbduIwJBN6tCoQ(LocationActivity r0) {
        r0.lambda$getThemeDescriptions$50();
    }

    public static /* synthetic */ void $r8$lambda$ENMWbTVW66m9W8lmQxIWurrnHt4(LocationActivity r0, TLRPC.User r1, int r2, AlertDialog r3, int r4) {
        r0.lambda$openProximityAlert$31(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$FjiCkRx0XlgRkEXMIFrkYU5pe4s(LocationActivity r0, View r1) {
        r0.lambda$createView$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$FjqhIWzTbC_WnO_AP8SHHTWrcUU(LocationActivity r0, long r1, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$getRecentLocations$46(r1, r3, r4);
    }

    /* renamed from: $r8$lambda$Gz3mXel64kbgKS78DEf-IxUobrM, reason: not valid java name */
    public static /* synthetic */ void m15707$r8$lambda$Gz3mXel64kbgKS78DEfIxUobrM(LocationActivity r0, Location r1) {
        r0.lambda$onMapInit$38(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$I07Zeh_ozgpBbvmvtKgP33kCXQo(LocationActivity r0, MotionEvent r1, IMapsProvider.ICallableMethod r2) {
        return r0.lambda$createView$19(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$JwTss6qTf_szkRi8z4WlKIHHZp0(LocationActivity r0, boolean r1, TLRPC.User r2, int r3, int r4) {
        r0.lambda$openShareLiveLocation$35(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$KLwJ8OCs6Ih8RCCELWlUuYC32Mg(LocationActivity r0, LocationController.SharingLocationInfo r1) {
        r0.lambda$createView$7(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$K_SblFMYpnuCjrLTuOQWSwldEmo(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ boolean $r8$lambda$KdoVnXnWdTLwT7IWRxQ4Zpg_o2Q(LocationActivity r0, TLRPC.User r1, boolean r2, int r3) {
        return r0.lambda$openProximityAlert$32(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$LBxxn3dyZg-JdgxSGerEb7SogNw, reason: not valid java name */
    public static /* synthetic */ boolean m15708$r8$lambda$LBxxn3dyZgJdgxSGerEb7SogNw(LocationActivity r0, boolean r1, int r2) {
        return r0.lambda$openProximityAlert$30(r1, r2);
    }

    /* renamed from: $r8$lambda$LEcMRFNYM4NOjuo24HUo0MhP-zQ, reason: not valid java name */
    public static /* synthetic */ void m15709$r8$lambda$LEcMRFNYM4NOjuo24HUo0MhPzQ(LocationActivity r0, IMapsProvider.IMapView r1) {
        r0.lambda$createView$24(r1);
    }

    public static /* synthetic */ void $r8$lambda$ObyKCmnOYspiJIZ6ON8_aOGReNE(LocationActivity r0, ViewGroup r1, GLSurfaceView r2) {
        r0.lambda$onCheckGlScreenshot$47(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$OmwMrekJidY1eCcjCzp9_hM5bmE(LocationActivity r0) {
        r0.lambda$getRecentLocations$44();
    }

    public static /* synthetic */ void $r8$lambda$P0bfHb3AHtHVRfsyT2SCF6miXw8(LocationActivity r0, LiveLocation r1, View r2) {
        r0.lambda$createView$11(r1, r2);
    }

    /* renamed from: $r8$lambda$PBwnHs7-onPTdm3kGQchwcYI1Fk, reason: not valid java name */
    public static /* synthetic */ void m15710$r8$lambda$PBwnHs7onPTdm3kGQchwcYI1Fk(LocationActivity r0, View r1) {
        r0.lambda$createView$4(r1);
    }

    /* renamed from: $r8$lambda$P_pH6FSGKWla3zrkzwZomEL--zM, reason: not valid java name */
    public static /* synthetic */ void m15711$r8$lambda$P_pH6FSGKWla3zrkzwZomELzM(LocationActivity r0, LiveLocation r1, ImageReceiver r2, boolean r3, boolean r4, boolean r5) {
        r0.lambda$setupAvatarReceiver$36(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$QMJk-QqWmue9TAgF1sZZ1FSmydE, reason: not valid java name */
    public static /* synthetic */ void m15712$r8$lambda$QMJkQqWmue9TAgF1sZZ1FSmydE(LocationActivity r0, AlertDialog r1, int r2) {
        r0.lambda$showPermissionAlert$42(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$R6YVxy1iFvXDhfiqtg80hU5A1dI(LocationActivity r0, AlertDialog[] r1, TLRPC.TL_messageMediaVenue r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$createView$14(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$RW3IksEb8JCmD1Uf-BtGY3_mObs, reason: not valid java name */
    public static /* synthetic */ void m15713$r8$lambda$RW3IksEb8JCmD1UfBtGY3_mObs(LocationActivity r0) {
        r0.lambda$createView$6();
    }

    public static /* synthetic */ void $r8$lambda$WW0Bmednbj64icr0bRuV9Mwu6ak(LocationActivity r0, View r1) {
        r0.lambda$createView$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$YGHBFcjHaSLo3TP0AogC99lqYQg(LocationActivity r0) {
        r0.lambda$createView$21();
    }

    public static /* synthetic */ void $r8$lambda$_X9jXwdnIa20bkFYS3JBGvZAqL8(LocationActivity r0, int r1, DialogInterface r2) {
        r0.lambda$createView$15(r1, r2);
    }

    /* renamed from: $r8$lambda$aY8unpu8t82aAHJzzmm6tGJB-ZQ, reason: not valid java name */
    public static /* synthetic */ void m15714$r8$lambda$aY8unpu8t82aAHJzzmm6tGJBZQ(LocationActivity r0, AlertDialog r1, int r2) {
        r0.lambda$checkGpsEnabled$41(r1, r2);
    }

    /* renamed from: $r8$lambda$dpAr7a_j-8rL9oEcxv7fGLyf_wc, reason: not valid java name */
    public static /* synthetic */ void m15715$r8$lambda$dpAr7a_j8rL9oEcxv7fGLyf_wc(LocationActivity r0, Object r1, boolean r2, int r3, int r4) {
        r0.lambda$createView$17(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$g7JBjbMlxRZM3nAs9W_28gc5cH0(LocationActivity r0, int r1) {
        r0.lambda$fixLayoutInternal$43(r1);
    }

    public static /* synthetic */ void $r8$lambda$ixplKdQey7KqVj7cRqhYSwNmI4I(LocationActivity r0, ArrayList r1) {
        r0.updatePlacesMarkers(r1);
    }

    public static /* synthetic */ void $r8$lambda$iyLh6SzVM5XN1f1npaqF358steM(LocationActivity r0, boolean r1) {
        r0.lambda$showShowAllButton$29(r1);
    }

    public static /* synthetic */ void $r8$lambda$kQRkiJpmTltlTZIHgO6FxDZ7OaQ(LocationActivity r0) {
        r0.lambda$openProximityAlert$33();
    }

    /* renamed from: $r8$lambda$mPNjgCqIHoA_szQtd2XiCCCY-Ew, reason: not valid java name */
    public static /* synthetic */ void m15716$r8$lambda$mPNjgCqIHoA_szQtd2XiCCCYEw(LocationActivity r0, TLRPC.TL_messageMediaVenue r1, boolean r2, int r3, int r4) {
        r0.lambda$createView$27(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$miQj1RxbPHyLBCweepAdis_A_dE(LocationActivity r0, TLRPC.TL_messageMediaGeo r1, boolean r2, int r3, int r4) {
        r0.lambda$createView$16(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$mx1XM0nyFokEVcKgIz8rk9-Hg9o, reason: not valid java name */
    public static /* synthetic */ boolean m15717$r8$lambda$mx1XM0nyFokEVcKgIz8rk9Hg9o(LocationActivity r0, Context r1, View r2, int r3) {
        return r0.lambda$createView$12(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$n8lIY9bdiZSE12H97J1WF5L79y0(LocationActivity r0, View r1) {
        r0.lambda$createView$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$qY1FbV7TI7BvW9fN45BPWXdYFAI(LocationActivity r0, GLSurfaceView r1) {
        r0.lambda$onCheckGlScreenshot$49(r1);
    }

    /* renamed from: $r8$lambda$riFNACJ3ZpqbkU-6YrsCxCEH1z4, reason: not valid java name */
    public static /* synthetic */ void m15718$r8$lambda$riFNACJ3ZpqbkU6YrsCxCEH1z4(LocationActivity r0, int r1) {
        r0.lambda$createView$3(r1);
    }

    /* renamed from: $r8$lambda$s-8FqHt5JK0_SqNE0bTatowCvYE, reason: not valid java name */
    public static /* synthetic */ void m15719$r8$lambda$s8FqHt5JK0_SqNE0bTatowCvYE(LocationActivity r0, View r1) {
        r0.lambda$createView$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$svz29IzLc4VWSEFTv3SoFqA5Q7g(LocationActivity r0, Bitmap r1, GLSurfaceView r2) {
        r0.lambda$onCheckGlScreenshot$48(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$v6yAldbvA2EC5h9Aa4uflFXkvZU(LocationActivity r0, ActionBarMenu r1, View r2, int r3) {
        r0.lambda$createView$28(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$vDOnuKu368TYMlFdbSP_NNKHykk(LocationActivity r0, TLObject r1, long r2) {
        r0.lambda$getRecentLocations$45(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$vG8yyGRK1oJGFyG9ni_lcRoeDw4(LocationActivity r0, View r1, int r2) {
        r0.lambda$createView$18(r1, r2);
    }

    /* renamed from: $r8$lambda$xGOyzXx-ltTVT84oEr12Hxo1jG0, reason: not valid java name */
    public static /* synthetic */ boolean m15720$r8$lambda$xGOyzXxltTVT84oEr12Hxo1jG0(LocationActivity r0, float r1, IMapsProvider.IMarker r2) {
        return r0.lambda$onMapInit$39(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$xpRWJ8USxwznVf6cdZcqvKfwxZs(LocationActivity r0, IMapsProvider.IMapView r1) {
        r0.lambda$createView$25(r1);
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LocationActivityAdapter m15721$$Nest$fgetadapter(LocationActivity r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LocationActivityDelegate m15722$$Nest$fgetdelegate(LocationActivity r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetemptySubtitleTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m15723$$Nest$fgetemptySubtitleTextView(LocationActivity r0) {
        return r0.emptySubtitleTextView;
    }

    /* renamed from: -$$Nest$fgetforceUpdate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ IMapsProvider.ICameraUpdate m15724$$Nest$fgetforceUpdate(LocationActivity r0) {
        return r0.forceUpdate;
    }

    /* renamed from: -$$Nest$fgetlastPressedMarker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ IMapsProvider.IMarker m15725$$Nest$fgetlastPressedMarker(LocationActivity r0) {
        return r0.lastPressedMarker;
    }

    /* renamed from: -$$Nest$fgetlastPressedMarkerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m15726$$Nest$fgetlastPressedMarkerView(LocationActivity r0) {
        return r0.lastPressedMarkerView;
    }

    /* renamed from: -$$Nest$fgetlastPressedVenue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VenueLocation m15727$$Nest$fgetlastPressedVenue(LocationActivity r0) {
        return r0.lastPressedVenue;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m15728$$Nest$fgetlistView(LocationActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetlocationType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15729$$Nest$fgetlocationType(LocationActivity r0) {
        return r0.locationType;
    }

    /* renamed from: -$$Nest$fgetmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ IMapsProvider.IMap m15730$$Nest$fgetmap(LocationActivity r0) {
        return r0.map;
    }

    /* renamed from: -$$Nest$fgetmapViewClip, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m15731$$Nest$fgetmapViewClip(LocationActivity r0) {
        return r0.mapViewClip;
    }

    /* renamed from: -$$Nest$fgetmessageObject, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageObject m15732$$Nest$fgetmessageObject(LocationActivity r0) {
        return r0.messageObject;
    }

    /* renamed from: -$$Nest$fgetotherItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m15733$$Nest$fgetotherItem(LocationActivity r0) {
        return r0.otherItem;
    }

    /* renamed from: -$$Nest$fgetoverlayView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MapOverlayView m15734$$Nest$fgetoverlayView(LocationActivity r0) {
        return r0.overlayView;
    }

    /* renamed from: -$$Nest$fgetparentFragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivity m15735$$Nest$fgetparentFragment(LocationActivity r0) {
        return r0.parentFragment;
    }

    /* renamed from: -$$Nest$fgetscrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15736$$Nest$fgetscrolling(LocationActivity r0) {
        return r0.scrolling;
    }

    /* renamed from: -$$Nest$fgetsearchAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LocationActivitySearchAdapter m15737$$Nest$fgetsearchAdapter(LocationActivity r0) {
        return r0.searchAdapter;
    }

    /* renamed from: -$$Nest$fgetsearchItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m15738$$Nest$fgetsearchItem(LocationActivity r0) {
        return r0.searchItem;
    }

    /* renamed from: -$$Nest$fgetsearchListView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m15739$$Nest$fgetsearchListView(LocationActivity r0) {
        return r0.searchListView;
    }

    /* renamed from: -$$Nest$fgetsearchStoriesArea, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TL_stories.MediaArea m15740$$Nest$fgetsearchStoriesArea(LocationActivity r0) {
        return r0.searchStoriesArea;
    }

    /* renamed from: -$$Nest$fgetsearchWas, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15741$$Nest$fgetsearchWas(LocationActivity r0) {
        return r0.searchWas;
    }

    /* renamed from: -$$Nest$fgetsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15742$$Nest$fgetsearching(LocationActivity r0) {
        return r0.searching;
    }

    /* renamed from: -$$Nest$fgetshadowDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m15743$$Nest$fgetshadowDrawable(LocationActivity r0) {
        return r0.shadowDrawable;
    }

    /* renamed from: -$$Nest$fgetsharedMediaHeader, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GraySectionCell m15744$$Nest$fgetsharedMediaHeader(LocationActivity r0) {
        return r0.sharedMediaHeader;
    }

    /* renamed from: -$$Nest$fgetsharedMediaLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SharedMediaLayout m15745$$Nest$fgetsharedMediaLayout(LocationActivity r0) {
        return r0.sharedMediaLayout;
    }

    /* renamed from: -$$Nest$fgetuserLocation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Location m15746$$Nest$fgetuserLocation(LocationActivity r0) {
        return r0.userLocation;
    }

    /* renamed from: -$$Nest$fgetyOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m15747$$Nest$fgetyOffset(LocationActivity r0) {
        return r0.yOffset;
    }

    /* renamed from: -$$Nest$fputforceUpdate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15748$$Nest$fputforceUpdate(LocationActivity r0, IMapsProvider.ICameraUpdate r1) {
        r0.forceUpdate = r1;
    }

    /* renamed from: -$$Nest$fputlastPressedMarker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15749$$Nest$fputlastPressedMarker(LocationActivity r0, IMapsProvider.IMarker r1) {
        r0.lastPressedMarker = r1;
    }

    /* renamed from: -$$Nest$fputlastPressedMarkerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15750$$Nest$fputlastPressedMarkerView(LocationActivity r0, FrameLayout r1) {
        r0.lastPressedMarkerView = r1;
    }

    /* renamed from: -$$Nest$fputlastPressedVenue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15751$$Nest$fputlastPressedVenue(LocationActivity r0, VenueLocation r1) {
        r0.lastPressedVenue = r1;
    }

    /* renamed from: -$$Nest$fputpopupWindow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15752$$Nest$fputpopupWindow(LocationActivity r0, ActionBarPopupWindow r1) {
        r0.popupWindow = r1;
    }

    /* renamed from: -$$Nest$fputproximityAnimationInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15753$$Nest$fputproximityAnimationInProgress(LocationActivity r0, boolean r1) {
        r0.proximityAnimationInProgress = r1;
    }

    /* renamed from: -$$Nest$fputscrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15754$$Nest$fputscrolling(LocationActivity r0, boolean r1) {
        r0.scrolling = r1;
    }

    /* renamed from: -$$Nest$fputsearchInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15755$$Nest$fputsearchInProgress(LocationActivity r0, boolean r1) {
        r0.searchInProgress = r1;
    }

    /* renamed from: -$$Nest$fputsearchWas, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15756$$Nest$fputsearchWas(LocationActivity r0, boolean r1) {
        r0.searchWas = r1;
    }

    /* renamed from: -$$Nest$fputsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15757$$Nest$fputsearching(LocationActivity r0, boolean r1) {
        r0.searching = r1;
    }

    /* renamed from: -$$Nest$fputselectedMarkerId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15758$$Nest$fputselectedMarkerId(LocationActivity r0, long r1) {
        r0.selectedMarkerId = r1;
    }

    /* renamed from: -$$Nest$fputyOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15759$$Nest$fputyOffset(LocationActivity r0, float r1) {
        r0.yOffset = r1;
    }

    /* renamed from: -$$Nest$mfixLayoutInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15760$$Nest$mfixLayoutInternal(LocationActivity r0, boolean r1) {
        r0.fixLayoutInternal(r1);
    }

    /* renamed from: -$$Nest$mmaybeShowProximityHint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15761$$Nest$mmaybeShowProximityHint(LocationActivity r0) {
        r0.maybeShowProximityHint();
    }

    /* renamed from: -$$Nest$mopenDirections, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15762$$Nest$mopenDirections(LocationActivity r0, LiveLocation r1) {
        r0.openDirections(r1);
    }

    /* renamed from: -$$Nest$mopenShareLiveLocation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15763$$Nest$mopenShareLiveLocation(LocationActivity r0, boolean r1, int r2) {
        r0.openShareLiveLocation(r1, r2);
    }

    /* renamed from: -$$Nest$mshowSearchPlacesButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15764$$Nest$mshowSearchPlacesButton(LocationActivity r0, boolean r1) {
        r0.showSearchPlacesButton(r1);
    }

    /* renamed from: -$$Nest$mupdateClipView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15765$$Nest$mupdateClipView(LocationActivity r0, boolean r1) {
        r0.updateClipView(r1);
    }

    /* renamed from: -$$Nest$mupdateEmptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15766$$Nest$mupdateEmptyView(LocationActivity r0) {
        r0.updateEmptyView();
    }

    public LocationActivity(int r4) {
        this.undoView = new UndoView[2];
        this.checkGpsEnabled = true;
        this.locationDenied = false;
        this.isFirstLocation = true;
        this.firstFocus = true;
        this.markers = new ArrayList();
        this.markersMap = new LongSparseArray();
        this.selectedMarkerId = -1;
        this.placeMarkers = new ArrayList();
        this.checkPermission = true;
        this.checkBackgroundPermission = true;
        this.overScrollHeight = (AndroidUtilities.displaySize.x - ActionBar.getCurrentActionBarHeight()) - AndroidUtilities.dp(66.0f);
        this.isSharingAllowed = true;
        this.bitmapCache = new Bitmap[7];
        this.locationType = r4;
        AndroidUtilities.fixGoogleMapsBug();
    }

    public static /* synthetic */ ActionBar access$000(LocationActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ INavigationLayout access$100(LocationActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ ActionBar access$200(LocationActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ INavigationLayout access$300(LocationActivity r0) {
        return r0.parentLayout;
    }

    public static /* synthetic */ ActionBar access$400(LocationActivity r0) {
        return r0.actionBar;
    }

    private LiveLocation addUserMarker(TLRPC.Message r9) {
        TLRPC.GeoPoint r1 = r9.media.geo;
        IMapsProvider.LatLng r0 = new IMapsProvider.LatLng(r1.lat, r1._long);
        LiveLocation r12 = this.markersMap.get(MessageObject.getFromChatId(r9));
        if (r12 != null) goto L33;
        r12 = new LiveLocation();
        r12.object = r9;
        if ((r9.from_id instanceof TLRPC.TL_peerUser) == false) goto L7;
        r12.user = getMessagesController().getUser(Long.valueOf(r12.object.from_id.user_id));
        r12.id = r12.object.from_id.user_id;
    L12:
        setupAvatarReceiver(r12);
        IMapsProvider.IMarkerOptions r3 = ApplicationLoader.getMapsProvider().onCreateMarkerOptions().position(r0);     // Catch: Exception -> L20
        Bitmap r4 = createUserBitmap(r12);     // Catch: Exception -> L20
        if (r4 == null) goto L36;
        r3.icon(r4);     // Catch: Exception -> L20
        r3.anchor(0.5f, 0.907f);     // Catch: Exception -> L20
        r12.marker = this.map.addMarker(r3);     // Catch: Exception -> L20
        if (UserObject.isUserSelf(r12.user) == true) goto L23;
        IMapsProvider.IMarkerOptions r02 = ApplicationLoader.getMapsProvider().onCreateMarkerOptions().position(r0).flat(true);     // Catch: Exception -> L20
        r02.anchor(0.5f, 0.5f);     // Catch: Exception -> L20
        IMapsProvider.IMarker r03 = this.map.addMarker(r02);     // Catch: Exception -> L20
        r12.directionMarker = r03;     // Catch: Exception -> L20
        int r92 = r9.media.heading;     // Catch: Exception -> L20
        if (r92 == 0) goto L22;
        r03.setRotation(r92);     // Catch: Exception -> L20
        r12.directionMarker.setIcon(R.drawable.map_pin_cone2);     // Catch: Exception -> L20
        r12.hasRotation = true;     // Catch: Exception -> L20
        goto L23
    L22:
        r03.setRotation(0);     // Catch: Exception -> L20
        r12.directionMarker.setIcon(R.drawable.map_pin_circle);     // Catch: Exception -> L20
        r12.hasRotation = false;     // Catch: Exception -> L20
    L23:
        this.markers.add(r12);     // Catch: Exception -> L20
        this.markersMap.put(r12.id, r12);     // Catch: Exception -> L20
        LocationController.SharingLocationInfo r93 = getLocationController().getSharingLocationInfo(this.dialogId);     // Catch: Exception -> L20
        if (r12.id != getUserConfig().getClientUserId()) goto L36;
        if (r93 == null) goto L36;
        if (r12.object.id != r93.mid) goto L36;
        Location r94 = this.myLocation;     // Catch: Exception -> L20
        if (r94 == null) goto L36;
        IMapsProvider.LatLng r04 = new IMapsProvider.LatLng(r94.getLatitude(), this.myLocation.getLongitude());     // Catch: Exception -> L20
        r12.marker.setPosition(r04);     // Catch: Exception -> L20
    L36:
        ProximitySheet r95 = this.proximitySheet;
        if (r95 == null) goto L39;
        r95.updateText(true, true);
    L39:
        updateShowAllButton();
        return r12;
    L20:
        e = move-exception;
        FileLog.e(e);
        goto L36
    L7:
        long r32 = MessageObject.getDialogId(r9);
        if (DialogObject.isUserDialog(r32) == false) goto L10;
        r12.user = getMessagesController().getUser(Long.valueOf(r32));
    L11:
        r12.id = r32;
        goto L12
    L10:
        r12.chat = getMessagesController().getChat(Long.valueOf(-r32));
        goto L11
    L33:
        r12.object = r9;
        r12.marker.setPosition(r0);
        if (this.selectedMarkerId != r12.id) goto L36;
        this.map.animateCamera(ApplicationLoader.getMapsProvider().newCameraUpdateLatLng(r12.marker.getPosition()));
        goto L36
    }

    private LiveLocation addUserMarker(TLRPC.TL_channelLocation r6) {
        TLRPC.GeoPoint r62 = r6.geo_point;
        IMapsProvider.LatLng r0 = new IMapsProvider.LatLng(r62.lat, r62._long);
        LiveLocation r63 = new LiveLocation();
        if (DialogObject.isUserDialog(this.dialogId) == false) goto L5;
        r63.user = getMessagesController().getUser(Long.valueOf(this.dialogId));
    L6:
        r63.id = this.dialogId;
        setupAvatarReceiver(r63);
        IMapsProvider.IMarkerOptions r1 = ApplicationLoader.getMapsProvider().onCreateMarkerOptions().position(r0);     // Catch: Exception -> L12
        Bitmap r2 = createUserBitmap(r63);     // Catch: Exception -> L12
        if (r2 == null) goto L15;
        r1.icon(r2);     // Catch: Exception -> L12
        r1.anchor(0.5f, 0.907f);     // Catch: Exception -> L12
        r63.marker = this.map.addMarker(r1);     // Catch: Exception -> L12
        if (UserObject.isUserSelf(r63.user) == true) goto L14;
        IMapsProvider.IMarkerOptions r02 = ApplicationLoader.getMapsProvider().onCreateMarkerOptions().position(r0).flat(true);     // Catch: Exception -> L12
        r02.icon(R.drawable.map_pin_circle);     // Catch: Exception -> L12
        r02.anchor(0.5f, 0.5f);     // Catch: Exception -> L12
        r63.directionMarker = this.map.addMarker(r02);     // Catch: Exception -> L12
    L14:
        this.markers.add(r63);     // Catch: Exception -> L12
        this.markersMap.put(r63.id, r63);     // Catch: Exception -> L12
    L15:
        return r63;
    L12:
        e = move-exception;
        FileLog.e(e);
        return r63;
    L5:
        r63.chat = getMessagesController().getChat(Long.valueOf(-this.dialogId));
        goto L6
    }

    private boolean checkGpsEnabled() {
        if (disablePermissionCheck() == false) goto L6;
        return false;
    L6:
        if (getParentActivity().getPackageManager().hasSystemFeature("android.hardware.location.gps") == true) goto L15;
        return true;
    L15:
    L12:
        e = move-exception;
        FileLog.e(e);
    L14:
        return true;
    L9:
        if (((LocationManager) ApplicationLoader.applicationContext.getSystemService("location")).isProviderEnabled("gps") == true) goto L14;
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());     // Catch: Exception -> L12
        r0.setTopAnimation(R.raw.permission_request_location, 72, false, getThemedColor(Theme.key_dialogTopBackground));     // Catch: Exception -> L12
        r0.setMessage(LocaleController.getString(R.string.GpsDisabledAlertText));     // Catch: Exception -> L12
        r0.setPositiveButton(LocaleController.getString(R.string.ConnectingToProxyEnable), new LocationActivity$$ExternalSyntheticLambda27(this));     // Catch: Exception -> L12
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);     // Catch: Exception -> L12
        showDialog(r0.create());     // Catch: Exception -> L12
        return false;
    }

    private void createCircle(int r9) {
        if (this.map != null) goto L5;
        return;
    L5:
        List<IMapsProvider.PatternItem> r0 = Arrays.asList(new IMapsProvider.PatternItem[]{new IMapsProvider.PatternItem.Gap(20), new IMapsProvider.PatternItem.Dash(20)});
        IMapsProvider.ICircleOptions r2 = ApplicationLoader.getMapsProvider().onCreateCircleOptions();
        r2.center(new IMapsProvider.LatLng(this.myLocation.getLatitude(), this.myLocation.getLongitude()));
        r2.radius(r9);
        if (isActiveThemeDark() == false) goto L8;
        r2.strokeColor(-1771658281);
        r2.fillColor(476488663);
    L9:
        r2.strokePattern(r0);
        r2.strokeWidth(2);
        this.proximityCircle = this.map.addCircle(r2);
        return;
    L8:
        r2.strokeColor(-1774024971);
        r2.fillColor(474121973);
        goto L9
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

    private Bitmap createUserBitmap(LiveLocation r11) {
        Bitmap r1 = null;
        Bitmap r2 = Bitmap.createBitmap(AndroidUtilities.dp(62.0f), AndroidUtilities.dp(85.0f), Bitmap.Config.ARGB_8888);     // Catch: Throwable -> L26
        r2.eraseColor(0);     // Catch: Throwable -> L8
        Canvas r5 = new Canvas(r2);     // Catch: Throwable -> L8
        Drawable r6 = ApplicationLoader.applicationContext.getResources().getDrawable(R.drawable.map_pin_photo);     // Catch: Throwable -> L8
        r6.setBounds(0, 0, AndroidUtilities.dp(62.0f), AndroidUtilities.dp(85.0f));     // Catch: Throwable -> L8
        r6.draw(r5);     // Catch: Throwable -> L8
        Paint r0 = new Paint(1);     // Catch: Throwable -> L8
        RectF r3 = new RectF();     // Catch: Throwable -> L8
        r5.save();     // Catch: Throwable -> L8
        r5.save();     // Catch: Throwable -> L8
        AvatarDrawable r62 = new AvatarDrawable();     // Catch: Throwable -> L8
        TLRPC.User r7 = r11.user;     // Catch: Throwable -> L8
        if (r7 == null) goto L10;
        r62.setInfo(this.currentAccount, r7);     // Catch: Throwable -> L8
    L13:
        r5.translate(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f));     // Catch: Throwable -> L8
        r62.setBounds(0, 0, AndroidUtilities.dp(50.0f), AndroidUtilities.dp(50.0f));     // Catch: Throwable -> L8
        r62.setRoundRadius(AndroidUtilities.dp(25.0f));     // Catch: Throwable -> L8
        r62.draw(r5);     // Catch: Throwable -> L8
        r5.restore();     // Catch: Throwable -> L8
        ImageReceiver r63 = r11.avatarReceiver;     // Catch: Throwable -> L8
        if (r63 != null) goto L16;
    L18:
        Bitmap r112 = null;
    L19:
        if (r112 != null) goto L21;
    L23:
        r5.restore();     // Catch: Throwable -> L8
        r5.setBitmap(null);     // Catch: Throwable -> L8 Exception -> L29
        goto L36
    L35:
        return r2;
    L36:
        return r2;
    L21:
        if (r112.isRecycled() == true) goto L23;
        Shader.TileMode r8 = Shader.TileMode.CLAMP;     // Catch: Throwable -> L8
        BitmapShader r64 = new BitmapShader(r112, r8, r8);     // Catch: Throwable -> L8
        Matrix r82 = new Matrix();     // Catch: Throwable -> L8
        float r72 = AndroidUtilities.dp(50.0f) / r112.getWidth();     // Catch: Throwable -> L8
        r82.postTranslate(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f));     // Catch: Throwable -> L8
        r82.postScale(r72, r72);     // Catch: Throwable -> L8
        r0.setShader(r64);     // Catch: Throwable -> L8
        r64.setLocalMatrix(r82);     // Catch: Throwable -> L8
        r3.set(AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(56.0f), AndroidUtilities.dp(56.0f));     // Catch: Throwable -> L8
        r5.drawRoundRect(r3, AndroidUtilities.dp(25.0f), AndroidUtilities.dp(25.0f), r0);     // Catch: Throwable -> L8
        goto L23
    L16:
        if (r63.hasImageLoaded() == false) goto L18;
        r112 = r11.avatarReceiver.getBitmap();     // Catch: Throwable -> L8
        goto L19
    L10:
        TLRPC.Chat r73 = r11.chat;     // Catch: Throwable -> L8
        if (r73 == null) goto L13;
        r62.setInfo(this.currentAccount, r73);     // Catch: Throwable -> L8
    L8:
        th = th;
        r1 = r2;
    L27:
        FileLog.e(th);
        return r1;
    L26:
        th = th;
        goto L27
    }

    private void fetchRecentLocations(ArrayList<TLRPC.Message> r14) {
        if (this.firstFocus == false) goto L5;
        IMapsProvider.ILatLngBoundsBuilder r0 = ApplicationLoader.getMapsProvider().onCreateLatLngBoundsBuilder();
    L6:
        int r2 = getConnectionsManager().getCurrentTime();
        int r4 = 0;
    L8:
        if (r4 >= r14.size()) goto L21;
        TLRPC.Message r5 = r14.get(r4);
        int r7 = r5.date;
        TLRPC.MessageMedia r8 = r5.media;
        int r9 = r8.period;
        if ((r7 + r9) <= r2) goto L12;
    L13:
        if (r0 == null) goto L15;
        TLRPC.GeoPoint r82 = r8.geo;
        r0.include(new IMapsProvider.LatLng(r82.lat, r82._long));
    L15:
        addUserMarker(r5);
        if (this.proximityButton.getVisibility() == 8) goto L20;
        if (MessageObject.getFromChatId(r5) == getUserConfig().getClientUserId()) goto L20;
        this.proximityButton.setVisibility(0);
        this.proximityAnimationInProgress = true;
        this.proximityButton.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(180).setListener(new AnonymousClass12(this)).start();
    L20:
        r4 = r4 + 1;
        goto L8
    L12:
        if (r9 != Integer.MAX_VALUE) goto L20;
    L21:
        if (r0 != null) goto L23;
        return;
    L23:
        if (this.firstFocus == false) goto L25;
        this.listView.smoothScrollBy(0, AndroidUtilities.dp(99.0f));
    L25:
        this.firstFocus = false;
        this.adapter.setLiveLocations(this.markers);
        if (this.messageObject.isLiveLocation() == false) goto L45;
        IMapsProvider.LatLng r22 = r0.build().getCenter();     // Catch: Exception -> L34
        IMapsProvider.LatLng r3 = move(r22, 100.0d, 100.0d);     // Catch: Exception -> L34
        r0.include(move(r22, -100.0d, -100.0d));     // Catch: Exception -> L34
        r0.include(r3);     // Catch: Exception -> L34
        IMapsProvider.ILatLngBounds r02 = r0.build();     // Catch: Exception -> L34
        if (r14.size() <= 1) goto L46;
        IMapsProvider.ICameraUpdate r142 = ApplicationLoader.getMapsProvider().newCameraUpdateLatLngBounds(r02, AndroidUtilities.dp(113.0f));     // Catch: Exception -> L31
        this.moveToBounds = r142;     // Catch: Exception -> L31
        this.map.moveCamera(r142);     // Catch: Exception -> L31
        this.moveToBounds = null;     // Catch: Exception -> L31
        goto L48
    L47:
        return;
    L31:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L34
        return;
    L46:
        return;
    L48:
        return;
    L45:
        return;
    L5:
        r0 = null;
        goto L6
    }

    private boolean fitAllLiveLocations(boolean r20) {
        if (this.map != null) goto L5;
        return false;
    L5:
        ArrayList r1 = new ArrayList();
        if (getConnectionsManager() == null) goto L8;
        int r3 = getConnectionsManager().getCurrentTime();
    L9:
        int r4 = this.markers.size();
        int r5 = 0;
    L10:
        if (r5 >= r4) goto L25;
        LiveLocation r6 = this.markers.get(r5);
        IMapsProvider.IMarker r7 = r6.marker;
        if (r7 == null) goto L23;
        TLRPC.Message r62 = r6.object;
        if (r62 == null) goto L23;
        TLRPC.MessageMedia r8 = r62.media;
        if (r8 == null) goto L23;
        int r82 = r8.period;
        if (r82 != Integer.MAX_VALUE) goto L21;
    L22:
        r1.add(r7.getPosition());
        goto L23
    L21:
        if ((r62.date + r82) > r3) goto L22;
    L23:
        r5 = r5 + 1;
        goto L10
    L25:
        if (this.markersMap.get(getUserConfig().getClientUserId()) == null) goto L27;
        boolean r32 = true;
    L28:
        Location r52 = this.myLocation;
        if (r52 == null) goto L33;
        if (r32 == true) goto L33;
        r1.add(new IMapsProvider.LatLng(r52.getLatitude(), this.myLocation.getLongitude()));
    L33:
        if (r1.size() >= 2) goto L66;
        return false;
    L66:
        IMapsProvider.ILatLngBoundsBuilder r33 = ApplicationLoader.getMapsProvider().onCreateLatLngBoundsBuilder();     // Catch: Exception -> L50
        int r53 = r1.size();     // Catch: Exception -> L50
        double r63 = -1.7976931348623157E308d;
        int r14 = 0;
        double r10 = Double.MAX_VALUE;
        double r12 = Double.MAX_VALUE;
        double r83 = -1.7976931348623157E308d;
    L36:
        if (r14 >= r53) goto L52;
        IMapsProvider.LatLng r15 = (IMapsProvider.LatLng) r1.get(r14);     // Catch: Exception -> L50
        r33.include(r15);     // Catch: Exception -> L50
        int r17 = r53;
        double r42 = r15.latitude;     // Catch: Exception -> L50
        if (r42 >= r10) goto L41;
        r10 = r42;
    L41:
        if (r42 <= r63) goto L43;
        r63 = r42;
    L43:
        double r43 = r15.longitude;     // Catch: Exception -> L50
        if (r43 >= r12) goto L47;
        r12 = r43;
    L47:
        if (r43 <= r83) goto L49;
        r83 = r43;
    L49:
        r14 = r14 + 1;     // Catch: Exception -> L50
        r53 = r17;
        goto L36
    L52:
        IMapsProvider.LatLng r13 = new IMapsProvider.LatLng((r10 + r63) / 2.0d, (r12 + r83) / 2.0d);     // Catch: Exception -> L50
        double r44 = Math.toRadians(r63 - r10) * 6366198.0d;     // Catch: Exception -> L50
        double r84 = (Math.toRadians(r83 - r12) * 6366198.0d) * Math.cos(Math.toRadians(r13.latitude));     // Catch: Exception -> L50
        if (r44 >= 30.0d) goto L55;
    L56:
        r33.include(move(r13, 15.0d, 15.0d));     // Catch: Exception -> L50
        r33.include(move(r13, -15.0d, -15.0d));     // Catch: Exception -> L50
    L57:
        IMapsProvider.ICameraUpdate r16 = ApplicationLoader.getMapsProvider().newCameraUpdateLatLngBounds(r33.build(), AndroidUtilities.dp(60.0f));     // Catch: Exception -> L50
        IMapsProvider.IMap r0 = this.map;
        if (r20 == true) goto L61;
        r0.moveCamera(r16);     // Catch: Exception -> L50
    L63:
        return true;
    L61:
        r0.animateCamera(r16, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, null);     // Catch: Exception -> L50
        goto L63
    L55:
        if (r84 >= 30.0d) goto L57;
    L50:
        e = move-exception;
        FileLog.e(e);
        return false;
    L27:
        r32 = false;
        goto L28
    L8:
        r3 = 0;
        goto L9
    }

    private void fixLayoutInternal(boolean r9) {
        if (this.listView != null) goto L5;
        return;
    L5:
        if (this.actionBar.getOccupyStatusBar() == false) goto L7;
        int r0 = AndroidUtilities.statusBarHeight;
    L8:
        int r02 = r0 + ActionBar.getCurrentActionBarHeight();
        int r2 = this.fragmentView.getMeasuredHeight();
        if (r2 == 0) goto L52;
        int r3 = this.locationType;
        if (r3 != 6) goto L14;
        this.overScrollHeight = (r2 - AndroidUtilities.dp(66.0f)) - r02;
    L17:
        SharedMediaLayout r22 = this.sharedMediaLayout;
        if (r22 != null) goto L20;
    L22:
        FrameLayout.LayoutParams r23 = (FrameLayout.LayoutParams) this.listView.getLayoutParams();
        r23.topMargin = r02;
        this.listView.setLayoutParams(r23);
        FrameLayout.LayoutParams r24 = (FrameLayout.LayoutParams) this.mapViewClip.getLayoutParams();
        r24.topMargin = r02;
        r24.height = this.overScrollHeight;
        this.mapViewClip.setLayoutParams(r24);
        RecyclerListView r25 = this.searchListView;
        if (r25 == null) goto L25;
        FrameLayout.LayoutParams r26 = (FrameLayout.LayoutParams) r25.getLayoutParams();
        r26.topMargin = r02;
        this.searchListView.setLayoutParams(r26);
    L25:
        this.adapter.setOverScrollHeight(this.overScrollHeight);
        FrameLayout.LayoutParams r03 = (FrameLayout.LayoutParams) this.mapView.getView().getLayoutParams();
        if (r03 == null) goto L31;
        r03.height = this.overScrollHeight + AndroidUtilities.dp(10.0f);
        IMapsProvider.IMap r32 = this.map;
        if (r32 == null) goto L30;
        r32.setPadding(AndroidUtilities.dp(70.0f), 0, AndroidUtilities.dp(70.0f), AndroidUtilities.dp(10.0f));
    L30:
        this.mapView.getView().setLayoutParams(r03);
    L31:
        MapOverlayView r04 = this.overlayView;
        if (r04 == null) goto L36;
        FrameLayout.LayoutParams r05 = (FrameLayout.LayoutParams) r04.getLayoutParams();
        if (r05 == null) goto L36;
        r05.height = this.overScrollHeight + AndroidUtilities.dp(10.0f);
        this.overlayView.setLayoutParams(r05);
    L36:
        this.adapter.notifyDataSetChanged();
        if (r9 == false) goto L49;
        int r92 = this.locationType;
        if (r92 != 3) goto L42;
        int r93 = 73;
    L47:
        this.layoutManager.scrollToPositionWithOffset(0, -AndroidUtilities.dp(r93));
        updateClipView(false);
        this.listView.post(new LocationActivity$$ExternalSyntheticLambda1(this, r93));
        return;
    L42:
        if (r92 == 1) goto L46;
        if (r92 == 2) goto L46;
        r93 = 0;
    L46:
        r93 = 66;
        goto L47
    L49:
        updateClipView(false);
        return;
    L20:
        if (r22.getStoriesCount(8) <= 0) goto L22;
        this.overScrollHeight -= AndroidUtilities.dp(200.0f);
        goto L22
    L14:
        if (r3 != 2) goto L16;
        this.overScrollHeight = (r2 - AndroidUtilities.dp(73.0f)) - r02;
        goto L17
    L16:
        this.overScrollHeight = (r2 - AndroidUtilities.dp(66.0f)) - r02;
        goto L17
    L52:
        return;
    L7:
        r0 = 0;
        goto L8
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

    private int getMapThemeResId() {
        if (AndroidUtilities.computePerceivedBrightness(getThemedColor(Theme.key_windowBackgroundWhite)) < 0.721f) goto L5;
        return 0;
    L5:
        return R.raw.mapstyle_night;
    }

    private long getMessageId(TLRPC.Message r1) {
        if (r1.from_id == null) goto L7;
        return MessageObject.getFromChatId(r1);
    L7:
        return MessageObject.getDialogId(r1);
    }

    private boolean getRecentLocations() {
        ArrayList<TLRPC.Message> r0 = getLocationController().locationsCache.get(this.messageObject.getDialogId());
        if (r0 != null) goto L5;
    L7:
        r0 = null;
    L9:
        if (DialogObject.isChatDialog(this.dialogId) == false) goto L15;
        TLRPC.Chat r1 = getMessagesController().getChat(Long.valueOf(-this.dialogId));
        if (ChatObject.isChannel(r1) == false) goto L15;
        if (r1.megagroup == true) goto L15;
        return false;
    L15:
        TLRPC.TL_messages_getRecentLocations r12 = new TLRPC.TL_messages_getRecentLocations();
        long r3 = this.messageObject.getDialogId();
        r12.peer = getMessagesController().getInputPeer(r3);
        r12.limit = 100;
        getConnectionsManager().sendRequest(r12, new LocationActivity$$ExternalSyntheticLambda49(this, r3));
        if (r0 == null) goto L19;
        return true;
    L19:
        return false;
    L5:
        if (r0.isEmpty() == false) goto L7;
        fetchRecentLocations(r0);
        goto L9
    }

    private UndoView getUndoView() {
        if (this.undoView[0].getVisibility() != 0) goto L6;
        UndoView[] r0 = this.undoView;
        UndoView r2 = r0[0];
        r0[0] = r0[1];
        r0[1] = r2;
        r2.hide(true, 2);
        this.mapViewClip.removeView(this.undoView[0]);
        this.mapViewClip.addView(this.undoView[0]);
    L6:
        return this.undoView[0];
    }

    private boolean isActiveThemeDark() {
        if (getResourceProvider() != null) goto L8;
        if (Theme.getActiveTheme().isDark() == false) goto L8;
        return true;
    L8:
        if (AndroidUtilities.computePerceivedBrightness(getThemedColor(Theme.key_windowBackgroundWhite)) >= 0.721f) goto L10;
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$checkGpsEnabled$41(AlertDialog r1, int r2) {
        if (getParentActivity() == null) goto L10;
        getParentActivity().startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));     // Catch: Exception -> L7
        return;
    L11:
        return;
    }

    private /* synthetic */ void lambda$createView$0(View r9) {
        TLRPC.GeoPoint r1 = this.messageObject.messageOwner.media.geo;     // Catch: Exception -> L5
        double r2 = r1.lat;     // Catch: Exception -> L5
        double r4 = r1._long;     // Catch: Exception -> L5
        getParentActivity().startActivity(new Intent("android.intent.action.VIEW", Uri.parse("geo:" + r2 + "," + r4 + "?q=" + r2 + "," + r4)));     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$createView$1(View r4) {
        showSearchPlacesButton(false);
        this.adapter.searchPlacesWithQuery(null, this.userLocation, true, true);
        this.searchedForCustomLocations = true;
        showResults();
    }

    private /* synthetic */ void lambda$createView$10() {
        updateClipView(false);
    }

    private /* synthetic */ void lambda$createView$11(LiveLocation r1, View r2) {
        openDirections(r1);
        ActionBarPopupWindow r0 = this.popupWindow;
        if (r0 == null) goto L6;
        r0.dismiss();
        return;
    }

    private /* synthetic */ boolean lambda$createView$12(Context r7, View r8, int r9) {
        if (this.locationType != 2) goto L8;
        Object r92 = this.adapter.getItem(r9);
        if ((r92 instanceof LiveLocation) == false) goto L8;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r0 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(r7);
        ActionBarMenuSubItem r72 = new ActionBarMenuSubItem(getParentActivity(), true, true, getResourceProvider());
        r72.setMinimumWidth(AndroidUtilities.dp(200.0f));
        r72.setTextAndIcon(LocaleController.getString(R.string.GetDirections), R.drawable.filled_directions);
        r72.setOnClickListener(new LocationActivity$$ExternalSyntheticLambda31(this, (LiveLocation) r92));
        r0.addView(r72);
        AnonymousClass8 r73 = new AnonymousClass8(this, r0, -2, -2);
        this.popupWindow = r73;
        r73.setOutsideTouchable(true);
        this.popupWindow.setClippingEnabled(true);
        this.popupWindow.setInputMethodMode(2);
        this.popupWindow.setSoftInputMode(0);
        int[] r74 = new int[2];
        r8.getLocationInWindow(r74);
        this.popupWindow.showAtLocation(r8, 48, 0, r74[1] - AndroidUtilities.dp(52.0f));
        this.popupWindow.dimBehind();
        return true;
    L8:
        return false;
    }

    private /* synthetic */ void lambda$createView$13(AlertDialog[] r10, TLRPC.TL_messageMediaVenue r11) {
        r10[0].dismiss();     // Catch: Throwable -> L6
    L4:
        r10[0] = null;
        this.delegate.didSelectLocation(r11, 4, true, 0, 0);
        finishFragment();
    }

    private /* synthetic */ void lambda$createView$14(AlertDialog[] r1, TLRPC.TL_messageMediaVenue r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new LocationActivity$$ExternalSyntheticLambda42(this, r1, r2));
    }

    private /* synthetic */ void lambda$createView$15(int r1, DialogInterface r2) {
        getConnectionsManager().cancelRequest(r1, true);
    }

    private /* synthetic */ void lambda$createView$16(TLRPC.TL_messageMediaGeo r8, boolean r9, int r10, int r11) {
        this.delegate.didSelectLocation(r8, this.locationType, r9, r10, 0);
        finishFragment();
    }

    private /* synthetic */ void lambda$createView$17(Object r8, boolean r9, int r10, int r11) {
        this.delegate.didSelectLocation((TLRPC.TL_messageMediaVenue) r8, this.locationType, r9, r10, 0);
        finishFragment();
    }

    private /* synthetic */ void lambda$createView$18(View r12, int r13) {
        this.selectedMarkerId = -1;
        int r122 = this.locationType;
        boolean r2 = true;
        if (r122 != 4) goto L15;
        if (r13 != 1) goto L104;
        TLRPC.TL_messageMediaVenue r5 = (TLRPC.TL_messageMediaVenue) this.adapter.getItem(r13);
        if (r5 != null) goto L9;
        return;
    L9:
        if (this.dialogId != 0) goto L12;
        this.delegate.didSelectLocation(r5, 4, true, 0, 0);
        finishFragment();
        return;
    L12:
        AlertDialog[] r123 = {new AlertDialog(getParentActivity(), 3)};
        TLRPC.TL_channels_editLocation r132 = new TLRPC.TL_channels_editLocation();
        r132.address = r5.address;
        r132.channel = getMessagesController().getInputChannel(-this.dialogId);
        TLRPC.TL_inputGeoPoint r0 = new TLRPC.TL_inputGeoPoint();
        r132.geo_point = r0;
        TLRPC.GeoPoint r1 = r5.geo;
        r0.lat = r1.lat;
        r0._long = r1._long;
        r123[0].setOnCancelListener(new LocationActivity$$ExternalSyntheticLambda35(this, getConnectionsManager().sendRequest(r132, new LocationActivity$$ExternalSyntheticLambda34(this, r123, r5))));
        showDialog(r123[0]);
        return;
    L104:
        return;
    L15:
        if (r122 != 5) goto L21;
        IMapsProvider.IMap r124 = this.map;
        if (r124 == null) goto L105;
        IMapsProvider r133 = ApplicationLoader.getMapsProvider();
        TLRPC.GeoPoint r14 = this.chatLocation.geo_point;
        r124.animateCamera(r133.newCameraUpdateLatLngZoom(new IMapsProvider.LatLng(r14.lat, r14._long), this.map.getMaxZoomLevel() - 4.0f));
        return;
    L105:
        return;
    L21:
        if (r13 != 1) goto L33;
        MessageObject r02 = this.messageObject;
        if (r02 == null) goto L33;
        if (r02.isLiveLocation() == true) goto L27;
    L28:
        IMapsProvider.IMap r125 = this.map;
        if (r125 == null) goto L106;
        IMapsProvider r134 = ApplicationLoader.getMapsProvider();
        TLRPC.GeoPoint r15 = this.messageObject.messageOwner.media.geo;
        r125.animateCamera(r134.newCameraUpdateLatLngZoom(new IMapsProvider.LatLng(r15.lat, r15._long), this.map.getMaxZoomLevel() - 4.0f));
        return;
    L106:
        return;
    L27:
        if (this.locationType == 6) goto L28;
    L33:
        if (r13 != 1) goto L53;
        if (this.locationType == 2) goto L53;
        if (this.delegate != null) goto L39;
        return;
    L39:
        if (this.userLocation == null) goto L108;
        FrameLayout r126 = this.lastPressedMarkerView;
        if (r126 == null) goto L44;
        r126.callOnClick();
        return;
    L44:
        TLRPC.TL_messageMediaGeo r16 = new TLRPC.TL_messageMediaGeo();
        TLRPC.TL_geoPoint r127 = new TLRPC.TL_geoPoint();
        r16.geo = r127;
        r127.lat = AndroidUtilities.fixLocationCoord(this.userLocation.getLatitude());
        r16.geo._long = AndroidUtilities.fixLocationCoord(this.userLocation.getLongitude());
        ChatActivity r128 = this.parentFragment;
        if (r128 != null) goto L47;
    L50:
        this.delegate.didSelectLocation(r16, this.locationType, true, 0, 0);
        finishFragment();
        return;
    L47:
        if (r128.isInScheduleMode() == false) goto L50;
        AlertsCreator.createScheduleDatePickerDialog(getParentActivity(), this.parentFragment.getDialogId(), new LocationActivity$$ExternalSyntheticLambda36(this, r16));
        return;
    L108:
        return;
    L53:
        if (this.locationType != 2) goto L61;
        if (getLocationController().isSharingLocation(this.dialogId) == false) goto L61;
        if (this.adapter.getItemViewType(r13) != 7) goto L61;
        getLocationController().removeSharingLocation(this.dialogId);
        this.adapter.notifyDataSetChanged();
        finishFragment();
        return;
    L61:
        if (this.locationType == 2) goto L63;
    L72:
        if (r13 == 2) goto L74;
    L75:
        if (r13 == 1) goto L77;
    L78:
        if (r13 == 3) goto L80;
    L87:
        Object r129 = this.adapter.getItem(r13);
        if ((r129 instanceof TLRPC.TL_messageMediaVenue) == false) goto L98;
        ChatActivity r135 = this.parentFragment;
        if (r135 != null) goto L92;
    L95:
        this.delegate.didSelectLocation((TLRPC.TL_messageMediaVenue) r129, this.locationType, true, 0, 0);
        finishFragment();
        return;
    L92:
        if (r135.isInScheduleMode() == false) goto L95;
        AlertsCreator.createScheduleDatePickerDialog(getParentActivity(), this.parentFragment.getDialogId(), new LocationActivity$$ExternalSyntheticLambda37(this, r129));
        return;
    L98:
        if ((r129 instanceof LiveLocation) == false) goto L109;
        LiveLocation r1210 = (LiveLocation) r129;
        this.selectedMarkerId = r1210.id;
        if (this.showAllMode == false) goto L102;
        this.showAllMode = false;
        updateShowAllButton();
    L102:
        this.map.animateCamera(ApplicationLoader.getMapsProvider().newCameraUpdateLatLngZoom(r1210.marker.getPosition(), this.map.getMaxZoomLevel() - 4.0f));
        return;
    L109:
        return;
    L80:
        if (this.locationType != 3) goto L87;
    L82:
        if (getLocationController().isSharingLocation(this.dialogId) == false) goto L85;
        getLocationController().removeSharingLocation(this.dialogId);
        this.adapter.notifyDataSetChanged();
        finishFragment();
        return;
    L85:
        openShareLiveLocation(false, 0);
        return;
    L77:
        if (this.locationType == 2) goto L82;
    L74:
        if (this.locationType == 1) goto L82;
    L63:
        if (getLocationController().isSharingLocation(this.dialogId) == false) goto L72;
        if (this.adapter.getItemViewType(r13) != 6) goto L72;
        if (getLocationController().getSharingLocationInfo(this.dialogId).period != Integer.MAX_VALUE) goto L70;
        r2 = false;
    L70:
        openShareLiveLocation(r2, 0);
    }

    private /* synthetic */ boolean lambda$createView$19(MotionEvent r3, IMapsProvider.ICallableMethod r4) {
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

    private /* synthetic */ void lambda$createView$2(View r1) {
        this.mapTypeButton.toggleSubMenu();
    }

    private /* synthetic */ boolean lambda$createView$20(MotionEvent r8, IMapsProvider.ICallableMethod r9) {
        if (this.messageObject != null) goto L30;
        if (this.chatLocation != null) goto L30;
        if (r8.getAction() != 0) goto L13;
        AnimatorSet r0 = this.animatorSet;
        if (r0 == null) goto L11;
        r0.cancel();
    L11:
        AnimatorSet r02 = new AnimatorSet();
        this.animatorSet = r02;
        r02.setDuration(200);
        this.animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.markerImageView, View.TRANSLATION_Y, new float[]{this.markerTop - AndroidUtilities.dp(10.0f)})});
        this.animatorSet.start();
    L19:
        if (r8.getAction() != 2) goto L30;
        if (this.userLocationMoved == true) goto L23;
        ImageView r03 = this.locationButton;
        int r3 = Theme.key_location_actionIcon;
        r03.setColorFilter(new PorterDuffColorFilter(getThemedColor(r3), PorterDuff.Mode.MULTIPLY));
        this.locationButton.setTag(Integer.valueOf(r3));
        this.userLocationMoved = true;
    L23:
        IMapsProvider.IMap r04 = this.map;
        if (r04 == null) goto L28;
        Location r1 = this.userLocation;
        if (r1 == null) goto L28;
        r1.setLatitude(r04.getCameraPosition().target.latitude);
        this.userLocation.setLongitude(this.map.getCameraPosition().target.longitude);
    L28:
        this.adapter.setCustomLocation(this.userLocation);
        goto L30
    L13:
        if (r8.getAction() != 1) goto L19;
        AnimatorSet r05 = this.animatorSet;
        if (r05 == null) goto L17;
        r05.cancel();
    L17:
        this.yOffset = 0.0f;
        AnimatorSet r06 = new AnimatorSet();
        this.animatorSet = r06;
        r06.setDuration(200);
        this.animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.markerImageView, View.TRANSLATION_Y, new float[]{this.markerTop})});
        this.animatorSet.start();
        this.adapter.fetchLocationAddress();
    L30:
        return ((Boolean) r9.call(r8)).booleanValue();
    }

    private /* synthetic */ void lambda$createView$21() {
        IMapsProvider.ICameraUpdate r0 = this.moveToBounds;
        if (r0 == null) goto L6;
        this.map.moveCamera(r0);
        this.moveToBounds = null;
        return;
    }

    private /* synthetic */ void lambda$createView$22() {
        AndroidUtilities.runOnUIThread(new LocationActivity$$ExternalSyntheticLambda28(this));
    }

    private /* synthetic */ void lambda$createView$23(IMapsProvider.IMap r5) {
        this.map = r5;
        int r52 = getMapThemeResId();
        if (r52 == 0) goto L5;
        this.currentMapStyleDark = true;
        IMapsProvider.IMapStyleOptions r53 = ApplicationLoader.getMapsProvider().loadRawResourceStyle(ApplicationLoader.applicationContext, r52);
        this.map.setMapStyle(r53);
    L5:
        this.map.setPadding(AndroidUtilities.dp(70.0f), 0, AndroidUtilities.dp(70.0f), AndroidUtilities.dp(10.0f));
        onMapInit();
    }

    private /* synthetic */ void lambda$createView$24(IMapsProvider.IMapView r2) {
        if (this.mapView != null) goto L5;
        return;
    L5:
        if (getParentActivity() != null) goto L14;
        return;
    L14:
        r2.onCreate(null);     // Catch: Exception -> L11
        ApplicationLoader.getMapsProvider().initializeMaps(ApplicationLoader.applicationContext);     // Catch: Exception -> L11
        this.mapView.getMapAsync(new LocationActivity$$ExternalSyntheticLambda43(this));     // Catch: Exception -> L11
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

    private /* synthetic */ void lambda$createView$25(IMapsProvider.IMapView r2) {
        r2.onCreate(null);     // Catch: Exception -> L6
    L4:
        AndroidUtilities.runOnUIThread(new LocationActivity$$ExternalSyntheticLambda30(this, r2));
    }

    private /* synthetic */ void lambda$createView$26(ArrayList r1) {
        this.searchInProgress = false;
        updateEmptyView();
    }

    private /* synthetic */ void lambda$createView$27(TLRPC.TL_messageMediaVenue r8, boolean r9, int r10, int r11) {
        this.delegate.didSelectLocation(r8, this.locationType, r9, r10, 0);
        finishFragment();
    }

    private /* synthetic */ void lambda$createView$28(ActionBarMenu r8, View r9, int r10) {
        TLRPC.TL_messageMediaVenue r1 = this.searchAdapter.getItem(r10);
        if (r1 != null) goto L5;
    L20:
        if (r1 != null) goto L22;
        return;
    L22:
        if (this.delegate == null) goto L32;
        ChatActivity r82 = this.parentFragment;
        if (r82 != null) goto L26;
    L29:
        this.delegate.didSelectLocation(r1, this.locationType, true, 0, 0);
        finishFragment();
        return;
    L26:
        if (r82.isInScheduleMode() == false) goto L29;
        AlertsCreator.createScheduleDatePickerDialog(getParentActivity(), this.parentFragment.getDialogId(), new LocationActivity$$ExternalSyntheticLambda29(this, r1));
        return;
    L32:
        return;
    L5:
        if (r1.icon == null) goto L20;
        if (this.locationType != 8) goto L20;
        if (this.map == null) goto L20;
        this.userLocationMoved = true;
        r8.closeSearchField(true);
        boolean r83 = "pin".equals(r1.icon);
        IMapsProvider.IMap r92 = this.map;
        if (r83 == false) goto L14;
        float r84 = r92.getMaxZoomLevel();
        float r93 = 4.0f;
    L13:
        float r85 = r84 - r93;
        IMapsProvider.IMap r94 = this.map;
        IMapsProvider r102 = ApplicationLoader.getMapsProvider();
        TLRPC.GeoPoint r2 = r1.geo;
        r94.animateCamera(r102.newCameraUpdateLatLngZoom(new IMapsProvider.LatLng(r2.lat, r2._long), r85));
        Location r86 = this.userLocation;
        if (r86 == null) goto L18;
        r86.setLatitude(r1.geo.lat);
        this.userLocation.setLongitude(r1.geo._long);
    L18:
        this.adapter.setCustomLocation(this.userLocation);
        return;
    L14:
        r84 = r92.getMaxZoomLevel();
        r93 = 9.0f;
        goto L13
    }

    private /* synthetic */ void lambda$createView$3(int r3) {
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

    private /* synthetic */ void lambda$createView$4(View r9) {
        Activity r92 = getParentActivity();
        if (r92 == null) goto L9;
        if (r92.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) goto L9;
        showPermissionAlert(false);
        return;
    L9:
        if (checkGpsEnabled() == true) goto L14;
        if (this.locationType == 3) goto L14;
        return;
    L14:
        if (this.messageObject == null) goto L18;
        if (this.locationType == 3) goto L18;
    L20:
        if (this.myLocation == null) goto L36;
        IMapsProvider.IMap r93 = this.map;
        if (r93 == null) goto L36;
        r93.animateCamera(ApplicationLoader.getMapsProvider().newCameraUpdateLatLngZoom(new IMapsProvider.LatLng(this.myLocation.getLatitude(), this.myLocation.getLongitude()), this.map.getMaxZoomLevel() - 4.0f));
    L36:
        removeInfoView();
        return;
    L18:
        if (this.chatLocation != null) goto L20;
        if (this.myLocation == null) goto L36;
        if (this.map == null) goto L36;
        ImageView r94 = this.locationButton;
        int r2 = Theme.key_location_actionActiveIcon;
        r94.setColorFilter(new PorterDuffColorFilter(getThemedColor(r2), PorterDuff.Mode.MULTIPLY));
        this.locationButton.setTag(Integer.valueOf(r2));
        this.adapter.setCustomLocation(null);
        this.userLocationMoved = false;
        showSearchPlacesButton(false);
        this.map.animateCamera(ApplicationLoader.getMapsProvider().newCameraUpdateLatLng(new IMapsProvider.LatLng(this.myLocation.getLatitude(), this.myLocation.getLongitude())));
        if (this.searchedForCustomLocations == false) goto L36;
        if (this.locationType == 8) goto L36;
        Location r95 = this.myLocation;
        if (r95 == null) goto L35;
        this.adapter.searchPlacesWithQuery(null, r95, true, true);
    L35:
        this.searchedForCustomLocations = false;
        showResults();
        goto L36
    }

    private /* synthetic */ void lambda$createView$5(View r3) {
        this.selectedMarkerId = -1;
        this.userLocationMoved = true;
        if (fitAllLiveLocations(true) == false) goto L6;
        this.showAllMode = true;
        showShowAllButton(false, true);
        return;
    }

    private /* synthetic */ void lambda$createView$6() {
        getLocationController().setProximityLocation(this.dialogId, 0, true);
        this.canUndo = false;
    }

    private /* synthetic */ void lambda$createView$7(LocationController.SharingLocationInfo r3) {
        this.proximityButton.setImageResource(R.drawable.msg_location_alert2);
        createCircle(r3.proximityMeters);
        this.canUndo = false;
    }

    private /* synthetic */ void lambda$createView$8(View r12) {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        if (this.myLocation != null) goto L7;
        return;
    L7:
        if (checkGpsEnabled() == true) goto L9;
        return;
    L9:
        if (this.map == null) goto L30;
        HintView2 r122 = this.hintView;
        if (r122 == null) goto L14;
        r122.hide();
    L14:
        MessagesController.getGlobalMainSettings().edit().putInt("proximityhint", 3).apply();
        LocationController.SharingLocationInfo r123 = getLocationController().getSharingLocationInfo(this.dialogId);
        if (this.canUndo == false) goto L17;
        this.undoView[0].hide(true, 1);
    L17:
        if (r123 != null) goto L19;
    L25:
        openProximityAlert();
        return;
    L19:
        if (r123.proximityMeters <= 0) goto L25;
        this.proximityButton.setImageResource(R.drawable.msg_location_alert);
        IMapsProvider.ICircle r0 = this.proximityCircle;
        if (r0 == null) goto L23;
        r0.remove();
        this.proximityCircle = null;
    L23:
        this.canUndo = true;
        getUndoView().showWithAction(0, 25, 0, null, new LocationActivity$$ExternalSyntheticLambda32(this), new LocationActivity$$ExternalSyntheticLambda33(this, r123));
        return;
    }

    private /* synthetic */ void lambda$fixLayoutInternal$43(int r3) {
        this.layoutManager.scrollToPositionWithOffset(0, -AndroidUtilities.dp(r3));
        updateClipView(false);
    }

    private /* synthetic */ void lambda$getRecentLocations$44() {
        getLocationController().markLiveLoactionsAsRead(this.dialogId);
        if (this.isPaused == true) goto L9;
        Runnable r3 = this.markAsReadRunnable;
        if (r3 == null) goto L10;
        AndroidUtilities.runOnUIThread(r3, 5000);
        return;
    L10:
        return;
    }

    private /* synthetic */ void lambda$getRecentLocations$45(TLObject r6, long r7) {
        if (this.map == null) goto L20;
        TLRPC.messages_Messages r62 = (TLRPC.messages_Messages) r6;
        int r1 = 0;
    L7:
        if (r1 >= r62.messages.size()) goto L12;
        if ((r62.messages.get(r1).media instanceof TLRPC.TL_messageMediaGeoLive) == true) goto L11;
        r62.messages.remove(r1);
        r1 = r1 - 1;
    L11:
        r1 = r1 + 1;
        goto L7
    L12:
        getMessagesStorage().putUsersAndChats(r62.users, r62.chats, true, true);
        getMessagesController().putUsers(r62.users, false);
        getMessagesController().putChats(r62.chats, false);
        getLocationController().locationsCache.put(r7, r62.messages);
        getNotificationCenter().postNotificationName(NotificationCenter.liveLocationsCacheChanged, new Object[]{Long.valueOf(r7)});
        fetchRecentLocations(r62.messages);
        getLocationController().markLiveLoactionsAsRead(this.dialogId);
        if (this.markAsReadRunnable != null) goto L19;
        LocationActivity$$ExternalSyntheticLambda51 r63 = new LocationActivity$$ExternalSyntheticLambda51(this);
        this.markAsReadRunnable = r63;
        AndroidUtilities.runOnUIThread(r63, 5000);
        return;
    L19:
        return;
    }

    private /* synthetic */ void lambda$getRecentLocations$46(long r1, TLObject r3, TLRPC.TL_error r4) {
        if (r3 == null) goto L5;
        AndroidUtilities.runOnUIThread(new LocationActivity$$ExternalSyntheticLambda50(this, r3, r1));
        return;
    }

    private /* synthetic */ void lambda$getThemeDescriptions$50() {
        this.mapTypeButton.setIconColor(getThemedColor(Theme.key_location_actionIcon));
        this.mapTypeButton.redrawPopup(getThemedColor(Theme.key_actionBarDefaultSubmenuBackground));
        this.mapTypeButton.setPopupItemsColor(getThemedColor(Theme.key_actionBarDefaultSubmenuItemIcon), true);
        this.mapTypeButton.setPopupItemsColor(getThemedColor(Theme.key_actionBarDefaultSubmenuItem), false);
        this.shadowDrawable.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_dialogBackground), PorterDuff.Mode.MULTIPLY));
        this.shadow.invalidate();
        if (this.map == null) goto L16;
        int r0 = getMapThemeResId();
        boolean r1 = this.currentMapStyleDark;
        if (r0 == 0) goto L11;
        if (r1 == true) goto L17;
        this.currentMapStyleDark = true;
        IMapsProvider.IMapStyleOptions r02 = ApplicationLoader.getMapsProvider().loadRawResourceStyle(ApplicationLoader.applicationContext, r0);
        this.map.setMapStyle(r02);
        IMapsProvider.ICircle r03 = this.proximityCircle;
        if (r03 == null) goto L18;
        r03.setStrokeColor(-1);
        this.proximityCircle.setFillColor(553648127);
        return;
    L18:
        return;
    L17:
        return;
    L11:
        if (r1 == false) goto L19;
        this.currentMapStyleDark = false;
        this.map.setMapStyle(null);
        IMapsProvider.ICircle r04 = this.proximityCircle;
        if (r04 == null) goto L20;
        r04.setStrokeColor(-16777216);
        this.proximityCircle.setFillColor(536870912);
        return;
    L20:
        return;
    L19:
        return;
    }

    private /* synthetic */ void lambda$onCheckGlScreenshot$47(ViewGroup r1, GLSurfaceView r2) {
        r1.removeView(r2);     // Catch: Exception -> L4
    L6:
        this.hasScreenshot = true;
        finishFragment();
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
        goto L6
    }

    private /* synthetic */ void lambda$onCheckGlScreenshot$48(Bitmap r3, GLSurfaceView r4) {
        ImageView r0 = new ImageView(getContext());
        r0.setImageBitmap(r3);
        ViewGroup r32 = (ViewGroup) r4.getParent();
        r32.addView(r0, r32.indexOfChild(r4));     // Catch: Exception -> L5
    L7:
        AndroidUtilities.runOnUIThread(new LocationActivity$$ExternalSyntheticLambda38(this, r32, r4), 100);
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
        goto L7
    }

    private /* synthetic */ void lambda$onCheckGlScreenshot$49(GLSurfaceView r16) {
        if (r16.getWidth() != 0) goto L5;
        return;
    L5:
        if (r16.getHeight() == 0) goto L10;
        ByteBuffer r7 = ByteBuffer.allocateDirect((r16.getWidth() * r16.getHeight()) * 4);
        GLES20.glReadPixels(0, 0, r16.getWidth(), r16.getHeight(), 6408, 5121, r7);
        Bitmap r8 = Bitmap.createBitmap(r16.getWidth(), r16.getHeight(), Bitmap.Config.ARGB_8888);
        r8.copyPixelsFromBuffer(r7);
        Matrix r13 = new Matrix();
        r13.preScale(1.0f, -1.0f);
        Bitmap r0 = Bitmap.createBitmap(r8, 0, 0, r8.getWidth(), r8.getHeight(), r13, false);
        r8.recycle();
        AndroidUtilities.runOnUIThread(new LocationActivity$$ExternalSyntheticLambda26(this, r0, r16));
        return;
    }

    private /* synthetic */ void lambda$onMapInit$37(int r6) {
        if (r6 != 1) goto L28;
        showSearchPlacesButton(true);
        removeInfoView();
        this.selectedMarkerId = -1;
        if (this.showAllMode == false) goto L8;
        this.showAllMode = false;
        updateShowAllButton();
    L8:
        if (this.scrolling == true) goto L29;
        int r62 = this.locationType;
        if (r62 == 0) goto L13;
        if (r62 == 1) goto L13;
        return;
    L13:
        if (this.listView.getChildCount() <= 0) goto L31;
        View r63 = this.listView.getChildAt(0);
        if (r63 == null) goto L32;
        RecyclerView.ViewHolder r0 = this.listView.findContainingViewHolder(r63);
        if (r0 != null) goto L19;
        return;
    L19:
        if (r0.getAdapterPosition() == 0) goto L21;
        return;
    L21:
        if (this.locationType != 0) goto L23;
        int r02 = 0;
    L24:
        int r64 = r63.getTop();
        if (r64 >= (-r02)) goto L35;
        IMapsProvider.CameraPosition r2 = this.map.getCameraPosition();
        this.forceUpdate = ApplicationLoader.getMapsProvider().newCameraUpdateLatLngZoom(r2.target, r2.zoom);
        this.listView.smoothScrollBy(0, r64 + r02);
        return;
    L35:
        return;
    L23:
        r02 = AndroidUtilities.dp(66.0f);
        goto L24
    L32:
        return;
    L31:
        return;
    L29:
        return;
    }

    private /* synthetic */ void lambda$onMapInit$38(Location r3) {
        positionMarker(r3);
        getLocationController().setMapLocation(r3, this.isFirstLocation);
        this.isFirstLocation = false;
    }

    private /* synthetic */ boolean lambda$onMapInit$39(float r7, IMapsProvider.IMarker r8) {
        if ((r8.getTag() instanceof VenueLocation) == true) goto L5;
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
        int r2 = 0;
    L10:
        if (r2 >= this.markers.size()) goto L20;
        LiveLocation r32 = this.markers.get(r2);
        if (r32 == null) goto L19;
        if (r32.marker != r8) goto L19;
        this.selectedMarkerId = r32.id;
        if (this.showAllMode == false) goto L18;
        this.showAllMode = false;
        updateShowAllButton();
    L18:
        this.map.animateCamera(ApplicationLoader.getMapsProvider().newCameraUpdateLatLngZoom(r32.marker.getPosition(), r7));
    L19:
        r2 = r2 + 1;
    L20:
        this.overlayView.addInfoView(r8);
        return true;
    }

    private /* synthetic */ void lambda$onMapInit$40() {
        MapOverlayView r0 = this.overlayView;
        if (r0 == null) goto L6;
        r0.updatePositions();
        return;
    }

    private /* synthetic */ boolean lambda$openProximityAlert$30(boolean r8, int r9) {
        IMapsProvider.ICircle r0 = this.proximityCircle;
        if (r0 == null) goto L8;
        r0.setRadius(r9);
        if (r8 == false) goto L8;
        moveToBounds(r9, true, true);
    L8:
        if (DialogObject.isChatDialog(this.dialogId) == false) goto L10;
        return true;
    L10:
        int r82 = this.markers.size();
        int r2 = 0;
    L11:
        if (r2 >= r82) goto L21;
        LiveLocation r3 = this.markers.get(r2);
        if (r3.object == null) goto L20;
        if (UserObject.isUserSelf(r3.user) == true) goto L20;
        TLRPC.GeoPoint r32 = r3.object.media.geo;
        Location r4 = new Location("network");
        r4.setLatitude(r32.lat);
        r4.setLongitude(r32._long);
        if (this.myLocation.distanceTo(r4) <= r9) goto L20;
        return true;
    L20:
        r2 = r2 + 1;
        goto L11
    L21:
        return false;
    }

    private /* synthetic */ void lambda$openProximityAlert$31(TLRPC.User r1, int r2, AlertDialog r3, int r4) {
        shareLiveLocation(r1, 900, r2);
    }

    private /* synthetic */ boolean lambda$openProximityAlert$32(TLRPC.User r10, boolean r11, int r12) {
        if (getLocationController().getSharingLocationInfo(this.dialogId) != null) goto L6;
        AlertDialog.Builder r112 = new AlertDialog.Builder(getParentActivity());
        r112.setTitle(LocaleController.getString(R.string.ShareLocationAlertTitle));
        r112.setMessage(LocaleController.getString(R.string.ShareLocationAlertText));
        r112.setPositiveButton(LocaleController.getString(R.string.ShareLocationAlertButton), new LocationActivity$$ExternalSyntheticLambda48(this, r10, r12));
        r112.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        showDialog(r112.create());
        return false;
    L6:
        this.proximitySheet.setRadiusSet();
        this.proximityButton.setImageResource(R.drawable.msg_location_alert2);
        getUndoView().showWithAction(0, 24, Integer.valueOf(r12), r10, null, null);
        getLocationController().setProximityLocation(this.dialogId, r12, true);
        return true;
    }

    private /* synthetic */ void lambda$openProximityAlert$33() {
        IMapsProvider.IMap r0 = this.map;
        if (r0 == null) goto L6;
        r0.setPadding(AndroidUtilities.dp(70.0f), 0, AndroidUtilities.dp(70.0f), AndroidUtilities.dp(10.0f));
    L6:
        if (this.proximitySheet.getRadiusSet() == true) goto L12;
        double r2 = this.previousRadius;
        IMapsProvider.ICircle r4 = this.proximityCircle;
        if (r2 <= 0.0d) goto L10;
        r4.setRadius(r2);
        goto L12
    L10:
        if (r4 == null) goto L12;
        r4.remove();
        this.proximityCircle = null;
    L12:
        this.proximitySheet = null;
    }

    private /* synthetic */ void lambda$openShareLiveLocation$34(boolean r2) {
        openShareLiveLocation(r2, this.askWithRadius);
    }

    private /* synthetic */ void lambda$openShareLiveLocation$35(boolean r6, TLRPC.User r7, int r8, int r9) {
        if (r6 == false) goto L28;
        LocationController.SharingLocationInfo r62 = getLocationController().getSharingLocationInfo(this.dialogId);
        if (r62 == null) goto L30;
        TLRPC.TL_messages_editMessage r72 = new TLRPC.TL_messages_editMessage();
        r72.peer = getMessagesController().getInputPeer(r62.did);
        r72.id = r62.mid;
        r72.flags |= 16384;
        TLRPC.TL_inputMediaGeoLive r82 = new TLRPC.TL_inputMediaGeoLive();
        r72.media = r82;
        r82.stopped = false;
        r82.geo_point = new TLRPC.TL_inputGeoPoint();
        Location r83 = LocationController.getInstance(this.currentAccount).getLastKnownLocation();
        r72.media.geo_point.lat = AndroidUtilities.fixLocationCoord(r83.getLatitude());
        r72.media.geo_point._long = AndroidUtilities.fixLocationCoord(r83.getLongitude());
        r72.media.geo_point.accuracy_radius = (int) r83.getAccuracy();
        TLRPC.InputMedia r1 = r72.media;
        TLRPC.InputGeoPoint r2 = r1.geo_point;
        if (r2.accuracy_radius == 0) goto L8;
        r2.flags |= 1;
    L8:
        int r22 = r62.lastSentProximityMeters;
        int r3 = r62.proximityMeters;
        if (r22 == r3) goto L11;
        r1.proximity_notification_radius = r3;
        r1.flags |= 8;
    L11:
        r1.heading = LocationController.getHeading(r83);
        TLRPC.InputMedia r84 = r72.media;
        int r12 = r84.flags;
        r84.flags = r12 | 4;
        int r23 = Integer.MAX_VALUE;
        if (r9 != Integer.MAX_VALUE) goto L14;
        int r32 = Integer.MAX_VALUE;
    L15:
        r62.period = r32;
        r84.period = r32;
        if (r9 == Integer.MAX_VALUE) goto L19;
        r23 = r62.stopTime + r9;
    L19:
        r62.stopTime = r23;
        r84.flags = r12 | 6;
        MessageObject r85 = r62.messageObject;
        if (r85 == null) goto L26;
        TLRPC.Message r86 = r85.messageOwner;
        if (r86 == null) goto L26;
        TLRPC.MessageMedia r87 = r86.media;
        if (r87 == null) goto L26;
        r87.period = r32;
        getMessagesStorage().replaceMessageIfExists(r62.messageObject.messageOwner, null, null, true);
    L26:
        getConnectionsManager().sendRequest(r72, null);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.liveLocationsChanged, new Object[0]);
        return;
    L14:
        r32 = r62.period + r9;
        goto L15
    L30:
        return;
    L28:
        shareLiveLocation(r7, r9, r8);
    }

    private /* synthetic */ void lambda$setupAvatarReceiver$36(LiveLocation r1, ImageReceiver r2, boolean r3, boolean r4, boolean r5) {
        if (r3 == false) goto L12;
        if (r4 == false) goto L6;
        return;
    L6:
        if (r1.marker == null) goto L15;
        Bitmap r0 = createUserBitmap(r1);
        if (r0 == null) goto L13;
        r1.marker.setIcon(r0);
        return;
    L13:
        return;
    L15:
        return;
    }

    private /* synthetic */ void lambda$showPermissionAlert$42(AlertDialog r2, int r3) {
        if (getParentActivity() != null) goto L10;
        return;
    L10:
        Intent r32 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");     // Catch: Exception -> L7
        r32.setData(Uri.parse("package:" + ApplicationLoader.applicationContext.getPackageName()));     // Catch: Exception -> L7
        getParentActivity().startActivity(r32);     // Catch: Exception -> L7
        return;
    L7:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$showShowAllButton$29(boolean r1) {
        if (r1 == true) goto L5;
        this.showAllButton.setVisibility(8);
        return;
    }

    private void maybeShowProximityHint() {
        ImageView r0 = this.proximityButton;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getVisibility() == 0) goto L7;
        return;
    L7:
        if (this.proximityAnimationInProgress == true) goto L20;
        SharedPreferences r02 = MessagesController.getGlobalMainSettings();
        int r1 = r02.getInt("proximityhint", 0);
        if (r1 >= 3) goto L19;
        r02.edit().putInt("proximityhint", r1 + 1).apply();
        if (DialogObject.isUserDialog(this.dialogId) == false) goto L14;
        TLRPC.User r03 = getMessagesController().getUser(Long.valueOf(this.dialogId));
        this.hintView.setText(LocaleController.formatString("ProximityTooltioUser", R.string.ProximityTooltioUser, new Object[]{UserObject.getFirstName(r03)}));
    L15:
        this.hintView.show();
        return;
    L14:
        this.hintView.setText(LocaleController.getString(R.string.ProximityTooltioGroup));
        goto L15
    L19:
        return;
    }

    private static double meterToLatitude(double r2) {
        return Math.toDegrees(r2 / 6366198.0d);
    }

    private static double meterToLongitude(double r2, double r4) {
        return Math.toDegrees(r2 / (Math.cos(Math.toRadians(r4)) * 6366198.0d));
    }

    private static IMapsProvider.LatLng move(IMapsProvider.LatLng r3, double r4, double r6) {
        double r62 = meterToLongitude(r6, r3.latitude);
        double r42 = meterToLatitude(r4);
        return new IMapsProvider.LatLng(r3.latitude + r42, r3.longitude + r62);
    }

    private void moveToBounds(int r10, boolean r11, boolean r12) {
        IMapsProvider.ILatLngBoundsBuilder r0 = ApplicationLoader.getMapsProvider().onCreateLatLngBoundsBuilder();
        r0.include(new IMapsProvider.LatLng(this.myLocation.getLatitude(), this.myLocation.getLongitude()));
        if (r11 == true) goto L5;
        int r102 = getConnectionsManager().getCurrentTime();
        int r112 = this.markers.size();
        int r122 = 0;
    L16:
        if (r122 >= r112) goto L21;
        TLRPC.Message r3 = this.markers.get(r122).object;
        int r4 = r3.date;
        TLRPC.MessageMedia r32 = r3.media;
        if ((r4 + r32.period) <= r102) goto L20;
        TLRPC.GeoPoint r33 = r32.geo;
        r0.include(new IMapsProvider.LatLng(r33.lat, r33._long));
    L20:
        r122 = r122 + 1;
        goto L16
    L21:
        IMapsProvider.LatLng r103 = r0.build().getCenter();     // Catch: Exception -> L27
        IMapsProvider.LatLng r113 = move(r103, 100.0d, 100.0d);     // Catch: Exception -> L27
        r0.include(move(r103, -100.0d, -100.0d));     // Catch: Exception -> L27
        r0.include(r113);     // Catch: Exception -> L27
        IMapsProvider.ILatLngBounds r104 = r0.build();     // Catch: Exception -> L27
        this.map.setPadding(AndroidUtilities.dp(70.0f), 0, AndroidUtilities.dp(70.0f), this.proximitySheet.getCustomView().getMeasuredHeight() - AndroidUtilities.dp(100.0f));     // Catch: Exception -> L24
        this.map.moveCamera(ApplicationLoader.getMapsProvider().newCameraUpdateLatLngBounds(r104, 0));     // Catch: Exception -> L24
    L31:
        return;
    L24:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L27
        goto L31
    L5:
        int r105 = Math.max(r10, 250);     // Catch: Exception -> L27
        IMapsProvider.LatLng r114 = r0.build().getCenter();     // Catch: Exception -> L27
        double r34 = r105;
        IMapsProvider.LatLng r35 = move(r114, r34, r34);     // Catch: Exception -> L27
        double r42 = -r105;
        r0.include(move(r114, r42, r42));     // Catch: Exception -> L27
        r0.include(r35);     // Catch: Exception -> L27
        IMapsProvider.ILatLngBounds r106 = r0.build();     // Catch: Exception -> L27
        this.map.setPadding(AndroidUtilities.dp(70.0f), 0, AndroidUtilities.dp(70.0f), (int) ((this.proximitySheet.getCustomView().getMeasuredHeight() - AndroidUtilities.dp(40.0f)) + this.mapViewClip.getTranslationY()));     // Catch: Exception -> L12
        IMapsProvider.IMap r9 = this.map;
        if (r12 == false) goto L10;
        r9.animateCamera(ApplicationLoader.getMapsProvider().newCameraUpdateLatLngBounds(r106, 0), MediaError.DetailedErrorCode.SEGMENT_UNKNOWN, null);     // Catch: Exception -> L12
    L28:
        goto L39
    L10:
        r9.moveCamera(ApplicationLoader.getMapsProvider().newCameraUpdateLatLngBounds(r106, 0));     // Catch: Exception -> L12
    L12:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L27
    }

    private boolean onCheckGlScreenshot() {
        IMapsProvider.IMapView r0 = this.mapView;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.getGlSurfaceView() != null) goto L7;
        return false;
    L7:
        if (this.hasScreenshot == true) goto L13;
        GLSurfaceView r02 = this.mapView.getGlSurfaceView();
        r02.queueEvent(new LocationActivity$$ExternalSyntheticLambda23(this, r02));
        return true;
    L13:
        return false;
    }

    private void onMapInit() {
        if (this.map == null) goto L56;
        this.mapView.getView().animate().alpha(1.0f).setStartDelay(200).setDuration(100).start();
        boolean r0 = this.initialMaxZoom;
        IMapsProvider.IMap r1 = this.map;
        if (r0 == false) goto L8;
        float r02 = r1.getMinZoomLevel() + 4.0f;
    L9:
        TLRPC.TL_channelLocation r12 = this.chatLocation;
        if (r12 == null) goto L12;
        this.map.moveCamera(ApplicationLoader.getMapsProvider().newCameraUpdateLatLngZoom(addUserMarker(r12).marker.getPosition(), r02));
    L50:
        this.map.setMyLocationEnabled(true);     // Catch: Exception -> L31
    L33:
        this.map.getUiSettings().setMyLocationButtonEnabled(false);
        this.map.getUiSettings().setZoomControlsEnabled(false);
        this.map.getUiSettings().setCompassEnabled(false);
        this.map.setOnCameraMoveStartedListener(new LocationActivity$$ExternalSyntheticLambda44(this));
        this.map.setOnMyLocationChangeListener(new LocationActivity$$ExternalSyntheticLambda45(this));
        this.map.setOnMarkerClickListener(new LocationActivity$$ExternalSyntheticLambda46(this, r02));
        this.map.setOnCameraMoveListener(new LocationActivity$$ExternalSyntheticLambda47(this));
        Location r03 = getLastLocation();
        this.myLocation = r03;
        positionMarker(r03);
        if (this.checkGpsEnabled == true) goto L36;
    L38:
        ImageView r04 = this.proximityButton;
        if (r04 != null) goto L41;
        return;
    L41:
        if (r04.getVisibility() != 0) goto L53;
        LocationController.SharingLocationInfo r05 = getLocationController().getSharingLocationInfo(this.dialogId);
        if (r05 == null) goto L54;
        int r06 = r05.proximityMeters;
        if (r06 <= 0) goto L55;
        createCircle(r06);
        return;
    L55:
        return;
    L54:
        return;
    L53:
        return;
    L36:
        if (getParentActivity() == null) goto L38;
        this.checkGpsEnabled = false;
        checkGpsEnabled();
    L31:
        e = move-exception;
        FileLog.e(e);
        goto L33
    L12:
        MessageObject r13 = this.messageObject;
        if (r13 != null) goto L15;
        Location r14 = new Location("network");
        this.userLocation = r14;
        TLRPC.TL_channelLocation r3 = this.initialLocation;
        if (r3 == null) goto L28;
        TLRPC.GeoPoint r32 = r3.geo_point;
        this.map.moveCamera(ApplicationLoader.getMapsProvider().newCameraUpdateLatLngZoom(new IMapsProvider.LatLng(r32.lat, r32._long), r02));
        this.userLocation.setLatitude(this.initialLocation.geo_point.lat);
        this.userLocation.setLongitude(this.initialLocation.geo_point._long);
        this.userLocation.setAccuracy(this.initialLocation.geo_point.accuracy_radius);
        this.adapter.setCustomLocation(this.userLocation);
        goto L50
    L28:
        r14.setLatitude(20.659322d);
        this.userLocation.setLongitude(-11.40625d);
        goto L50
    L15:
        if (r13.isLiveLocation() == false) goto L19;
        LiveLocation r15 = addUserMarker(this.messageObject.messageOwner);
        if (getRecentLocations() == true) goto L50;
        this.map.moveCamera(ApplicationLoader.getMapsProvider().newCameraUpdateLatLngZoom(r15.marker.getPosition(), r02));
        goto L50
    L19:
        IMapsProvider.LatLng r16 = new IMapsProvider.LatLng(this.userLocation.getLatitude(), this.userLocation.getLongitude());
        this.map.addMarker(ApplicationLoader.getMapsProvider().onCreateMarkerOptions().position(r16).icon(R.drawable.map_pin2));     // Catch: Exception -> L22
    L24:
        this.map.moveCamera(ApplicationLoader.getMapsProvider().newCameraUpdateLatLngZoom(r16, r02));
        this.firstFocus = false;
        getRecentLocations();
    L22:
        e = move-exception;
        FileLog.e(e);
        goto L24
    L8:
        r02 = r1.getMaxZoomLevel() - 4.0f;
        goto L9
    }

    private void openDirections(LiveLocation r11) {
        if (r11 == null) goto L6;
        TLRPC.Message r112 = r11.object;
        if (r112 == null) goto L6;
        TLRPC.GeoPoint r113 = r112.media.geo;
        double r0 = r113.lat;
        double r2 = r113._long;
    L11:
        if (this.myLocation != null) goto L23;
        Intent r114 = new Intent("android.intent.action.VIEW", Uri.parse(String.format(Locale.US, "http://maps.google.com/maps?saddr=&daddr=%f,%f", new Object[]{Double.valueOf(r0), Double.valueOf(r2)})));     // Catch: Exception -> L18
        getParentActivity().startActivity(r114);     // Catch: Exception -> L18
        return;
    L18:
        e = move-exception;
        FileLog.e(e);
        return;
    L23:
        Intent r115 = new Intent("android.intent.action.VIEW", Uri.parse(String.format(Locale.US, "http://maps.google.com/maps?saddr=%f,%f&daddr=%f,%f", new Object[]{Double.valueOf(this.myLocation.getLatitude()), Double.valueOf(this.myLocation.getLongitude()), Double.valueOf(r0), Double.valueOf(r2)})));     // Catch: Exception -> L14
        getParentActivity().startActivity(r115);     // Catch: Exception -> L14
        return;
    L14:
        e = move-exception;
        FileLog.e(e);
        return;
    L6:
        MessageObject r116 = this.messageObject;
        if (r116 == null) goto L9;
        TLRPC.GeoPoint r117 = r116.messageOwner.media.geo;
        r0 = r117.lat;
        r2 = r117._long;
        goto L11
    L9:
        TLRPC.GeoPoint r118 = this.chatLocation.geo_point;
        r0 = r118.lat;
        r2 = r118._long;
        goto L11
    }

    private void openProximityAlert() {
        IMapsProvider.ICircle r0 = this.proximityCircle;
        if (r0 != null) goto L5;
        createCircle(MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);
    L7:
        if (DialogObject.isUserDialog(this.dialogId) == false) goto L10;
        TLRPC.User r02 = getMessagesController().getUser(Long.valueOf(this.dialogId));
    L9:
        TLRPC.User r3 = r02;
        ProximitySheet r1 = new ProximitySheet(getParentActivity(), r3, new LocationActivity$$ExternalSyntheticLambda39(this), new LocationActivity$$ExternalSyntheticLambda40(this, r3), new LocationActivity$$ExternalSyntheticLambda41(this));
        this.proximitySheet = r1;
        ((FrameLayout) this.fragmentView).addView(r1, LayoutHelper.createFrame(-1, -1.0f));
        this.proximitySheet.show();
        return;
    L10:
        r02 = null;
        goto L9
    L5:
        this.previousRadius = r0.getRadius();
        goto L7
    }

    private void openShareLiveLocation(boolean r12, int r13) {
        if (this.delegate != null) goto L5;
        return;
    L5:
        if (disablePermissionCheck() == false) goto L7;
        return;
    L7:
        if (getParentActivity() != null) goto L9;
        return;
    L9:
        if (this.myLocation != null) goto L11;
        return;
    L11:
        if (checkGpsEnabled() == true) goto L14;
        return;
    L14:
        if (this.checkBackgroundPermission == false) goto L26;
        if (Build.VERSION.SDK_INT < 29) goto L26;
        Activity r0 = getParentActivity();
        if (r0 == null) goto L26;
        this.askWithRadius = r13;
        this.checkBackgroundPermission = false;
        SharedPreferences r3 = MessagesController.getGlobalMainSettings();
        if (Math.abs((System.currentTimeMillis() / 1000) - r3.getInt("backgroundloc", 0)) <= 86400) goto L26;
        if (r0.checkSelfPermission("android.permission.ACCESS_BACKGROUND_LOCATION") == 0) goto L26;
        r3.edit().putInt("backgroundloc", (int) (System.currentTimeMillis() / 1000)).apply();
        AlertsCreator.createBackgroundLocationPermissionDialog(r0, getMessagesController().getUser(Long.valueOf(getUserConfig().getClientUserId())), new LocationActivity$$ExternalSyntheticLambda20(this, r12), null).show();
        return;
    L26:
        if (DialogObject.isUserDialog(this.dialogId) == false) goto L28;
        TLRPC.User r02 = getMessagesController().getUser(Long.valueOf(this.dialogId));
    L29:
        showDialog(AlertsCreator.createLocationUpdateDialog(getParentActivity(), r12, r02, new LocationActivity$$ExternalSyntheticLambda21(this, r12, r02, r13), null));
        return;
    L28:
        r02 = null;
        goto L29
    }

    private void positionMarker(Location r7) {
        if (r7 != null) goto L4;
        return;
    L4:
        this.myLocation = new Location(r7);
        LiveLocation r0 = this.markersMap.get(getUserConfig().getClientUserId());
        LocationController.SharingLocationInfo r1 = getLocationController().getSharingLocationInfo(this.dialogId);
        if (r0 == null) goto L16;
        if (r1 == null) goto L16;
        if (r0.object.id != r1.mid) goto L16;
        IMapsProvider.LatLng r12 = new IMapsProvider.LatLng(r7.getLatitude(), r7.getLongitude());
        r0.marker.setPosition(r12);
        IMapsProvider.IMarker r2 = r0.directionMarker;
        if (r2 == null) goto L13;
        r2.setPosition(r12);
    L13:
        if (this.selectedMarkerId != r0.id) goto L16;
        this.map.animateCamera(ApplicationLoader.getMapsProvider().newCameraUpdateLatLng(r0.marker.getPosition()));
    L16:
        if (this.messageObject == null) goto L18;
    L37:
        this.adapter.setGpsLocation(this.myLocation);
    L38:
        ProximitySheet r72 = this.proximitySheet;
        if (r72 == null) goto L41;
        r72.updateText(true, true);
    L41:
        IMapsProvider.ICircle r73 = this.proximityCircle;
        if (r73 == null) goto L44;
        r73.setCenter(new IMapsProvider.LatLng(this.myLocation.getLatitude(), this.myLocation.getLongitude()));
    L44:
        updateShowAllButton();
        return;
    L18:
        if (this.chatLocation != null) goto L37;
        if (this.map == null) goto L37;
        IMapsProvider.LatLng r02 = new IMapsProvider.LatLng(r7.getLatitude(), r7.getLongitude());
        LocationActivityAdapter r22 = this.adapter;
        if (r22 == null) goto L32;
        if (this.searchedForCustomLocations == true) goto L30;
        int r3 = this.locationType;
        if (r3 == 4) goto L30;
        if (r3 == 8) goto L30;
        r22.searchPlacesWithQuery(null, this.myLocation, true);
    L30:
        this.adapter.setGpsLocation(this.myLocation);
    L32:
        if (this.userLocationMoved == true) goto L38;
        this.userLocation = new Location(r7);
        if (this.firstWas == false) goto L36;
        IMapsProvider.ICameraUpdate r74 = ApplicationLoader.getMapsProvider().newCameraUpdateLatLng(r02);
        this.map.animateCamera(r74);
        goto L38
    L36:
        this.firstWas = true;
        IMapsProvider.ICameraUpdate r75 = ApplicationLoader.getMapsProvider().newCameraUpdateLatLngZoom(r02, this.map.getMaxZoomLevel() - 4.0f);
        this.map.moveCamera(r75);
        goto L38
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

    /* JADX WARN: Multi-variable type inference failed */
    private void setupAvatarReceiver(LiveLocation r6) {
        if (r6.avatarReceiver != null) goto L19;
        TLRPC.User r0 = r6.user;
        TLRPC.Chat r1 = r6.chat;
        if (r0 != null) goto L9;
        if (r1 != 0) goto L9;
        return;
    L9:
        AvatarDrawable r2 = new AvatarDrawable();
        int r3 = this.currentAccount;
        if (r0 == null) goto L12;
        r2.setInfo(r3, r0);
    L13:
        ImageReceiver r32 = new ImageReceiver();
        r32.setCurrentAccount(this.currentAccount);
        r32.setDelegate(new LocationActivity$$ExternalSyntheticLambda24(this, r6));
        r32.onAttachedToWindow();
        if (r0 != null) goto L17;
        r0 = r1;
    L17:
        r32.setForUserOrChat(r0, r2);
        r6.avatarReceiver = r32;
        return;
    L12:
        r2.setInfo(r3, r1);
        goto L13
    }

    private void shareLiveLocation(TLRPC.User r9, int r10, int r11) {
        TLRPC.TL_messageMediaGeoLive r1 = new TLRPC.TL_messageMediaGeoLive();
        TLRPC.TL_geoPoint r0 = new TLRPC.TL_geoPoint();
        r1.geo = r0;
        r0.lat = AndroidUtilities.fixLocationCoord(this.myLocation.getLatitude());
        TLRPC.GeoPoint r02 = r1.geo;
        r02._long = AndroidUtilities.fixLocationCoord(this.myLocation.getLongitude());
        r1.heading = LocationController.getHeading(this.myLocation);
        int r03 = r1.flags;
        r1.period = r10;
        r1.proximity_notification_radius = r11;
        r1.flags = r03 | 9;
        this.delegate.didSelectLocation(r1, this.locationType, true, 0, 0);
        if (r11 <= 0) goto L9;
        this.proximitySheet.setRadiusSet();
        this.proximityButton.setImageResource(R.drawable.msg_location_alert2);
        ProximitySheet r102 = this.proximitySheet;
        if (r102 == null) goto L7;
        r102.dismiss();
    L7:
        getUndoView().showWithAction(0, 24, Integer.valueOf(r11), r9, null, null);
        return;
    L9:
        finishFragment();
    }

    private void showPermissionAlert(boolean r6) {
        if (getParentActivity() != null) goto L5;
        return;
    L5:
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        r0.setTopAnimation(R.raw.permission_request_location, 72, false, getThemedColor(Theme.key_dialogTopBackground));
        if (r6 == false) goto L8;
        r0.setMessage(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PermissionNoLocationNavigation)));
    L9:
        r0.setNegativeButton(LocaleController.getString(R.string.PermissionOpenSettings), new LocationActivity$$ExternalSyntheticLambda25(this));
        r0.setPositiveButton(LocaleController.getString(R.string.OK), null);
        showDialog(r0.create());
        return;
    L8:
        r0.setMessage(AndroidUtilities.replaceTags(LocaleController.getString(R.string.PermissionNoLocationFriends)));
        goto L9
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

    private void showSearchPlacesButton(boolean r6) {
        if (this.locationType != 3) goto L6;
        r6 = true;
    L6:
        if (r6 == false) goto L18;
        SearchButton r1 = this.searchAreaButton;
        if (r1 == null) goto L18;
        if (r1.getTag() != null) goto L18;
        Location r12 = this.myLocation;
        if (r12 == null) goto L17;
        Location r3 = this.userLocation;
        if (r3 == null) goto L17;
        if (r3.distanceTo(r12) >= 300.0f) goto L18;
    L17:
        r6 = false;
    L18:
        SearchButton r13 = this.searchAreaButton;
        if (r13 == null) goto L37;
        if (r6 == true) goto L22;
    L23:
        if (r6 == false) goto L25;
    L27:
        SearchButton r14 = this.searchAreaButton;
        if (r6 == false) goto L30;
        Integer r32 = 1;
    L31:
        r14.setTag(r32);
        AnimatorSet r15 = new AnimatorSet();
        SearchButton r5 = this.searchAreaButton;
        Property r33 = View.TRANSLATION_X;
        if (r6 == false) goto L34;
        float r62 = 0.0f;
    L35:
        r15.playTogether(new Animator[]{ObjectAnimator.ofFloat(r5, r33, new float[]{r62})});
        r15.setDuration(180);
        r15.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        r15.start();
        return;
    L34:
        r62 = -AndroidUtilities.dp(80.0f);
        goto L35
    L30:
        r32 = null;
        goto L31
    L25:
        if (this.searchAreaButton.getTag() != null) goto L27;
        return;
    L22:
        if (r13.getTag() == null) goto L23;
        return;
    }

    private void showShowAllButton(boolean r6, boolean r7) {
        Boolean r0 = this.shownShowAllButton;
        if (r0 != null) goto L5;
    L7:
        this.shownShowAllButton = Boolean.valueOf(r6);
        TextView r02 = this.showAllButton;
        float r1 = 0.0f;
        int r2 = 0;
        float r3 = 0.7f;
        if (r7 == true) goto L24;
        if (r6 == true) goto L12;
        r2 = 8;
    L12:
        r02.setVisibility(r2);
        TextView r72 = this.showAllButton;
        if (r6 == false) goto L15;
        r1 = 1.0f;
    L15:
        r72.setAlpha(r1);
        TextView r73 = this.showAllButton;
        if (r6 == false) goto L18;
        float r03 = 1.0f;
    L19:
        r73.setScaleX(r03);
        TextView r5 = this.showAllButton;
        if (r6 == false) goto L22;
        r3 = 1.0f;
    L22:
        r5.setScaleY(r3);
        return;
    L18:
        r03 = 0.7f;
        goto L19
    L24:
        r02.setVisibility(0);
        ViewPropertyAnimator r74 = this.showAllButton.animate();
        if (r6 == false) goto L27;
        r1 = 1.0f;
    L27:
        ViewPropertyAnimator r75 = r74.alpha(r1);
        if (r6 == false) goto L30;
        float r04 = 1.0f;
    L31:
        ViewPropertyAnimator r76 = r75.scaleX(r04);
        if (r6 == false) goto L34;
        r3 = 1.0f;
    L34:
        r76.scaleY(r3).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(420).withEndAction(new LocationActivity$$ExternalSyntheticLambda0(this, r6)).start();
        return;
    L30:
        r04 = 0.7f;
        goto L31
    L5:
        if (r0.booleanValue() != r6) goto L7;
    }

    private void updateClipView(boolean r7) {
        RecyclerView.ViewHolder r0 = this.listView.findViewHolderForAdapterPosition(0);
        if (r0 == null) goto L5;
        int r02 = (int) r0.itemView.getY();
        int r2 = this.overScrollHeight + Math.min(r02, 0);
    L7:
        if (((FrameLayout.LayoutParams) this.mapViewClip.getLayoutParams()) == null) goto L60;
        IMapsProvider.IMapView r3 = this.mapView;
        if (r2 > 0) goto L16;
        if (r3.getView().getVisibility() != 0) goto L20;
        this.mapView.getView().setVisibility(4);
        this.mapViewClip.setVisibility(4);
        MapOverlayView r32 = this.overlayView;
        if (r32 == null) goto L20;
        r32.setVisibility(4);
    L20:
        this.mapViewClip.setTranslationY(Math.min(0, r02));
        int r03 = -r02;
        int r4 = r03 / 2;
        this.mapView.getView().setTranslationY(Math.max(0, r4));
        MapOverlayView r33 = this.overlayView;
        if (r33 == null) goto L23;
        r33.setTranslationY(Math.max(0, r4));
    L23:
        int r34 = this.overScrollHeight - this.mapTypeButton.getMeasuredHeight();
        int r42 = this.locationType;
        if (r42 != 0) goto L26;
    L29:
        int r43 = 30;
    L30:
        float r35 = Math.min(r34 - AndroidUtilities.dp(64 + r43), r03);
        this.mapTypeButton.setTranslationY(r35);
        this.proximityButton.setTranslationY(r35);
        HintView2 r44 = this.hintView;
        if (r44 == null) goto L33;
        r44.setTranslationY(r35);
    L33:
        SearchButton r45 = this.searchAreaButton;
        if (r45 == null) goto L36;
        r45.setTranslation(r35);
    L36:
        View r36 = this.markerImageView;
        if (r36 != null) goto L39;
    L43:
        if (r7 == true) goto L61;
        FrameLayout.LayoutParams r72 = (FrameLayout.LayoutParams) this.mapView.getView().getLayoutParams();
        if (r72 != null) goto L47;
    L52:
        MapOverlayView r73 = this.overlayView;
        if (r73 == null) goto L62;
        FrameLayout.LayoutParams r74 = (FrameLayout.LayoutParams) r73.getLayoutParams();
        if (r74 != null) goto L57;
        return;
    L57:
        if (r74.height == (this.overScrollHeight + AndroidUtilities.dp(10.0f))) goto L64;
        r74.height = this.overScrollHeight + AndroidUtilities.dp(10.0f);
        this.overlayView.setLayoutParams(r74);
        return;
    L64:
        return;
    L62:
        return;
    L47:
        if (r72.height == (this.overScrollHeight + AndroidUtilities.dp(10.0f))) goto L52;
        r72.height = this.overScrollHeight + AndroidUtilities.dp(10.0f);
        IMapsProvider.IMap r22 = this.map;
        if (r22 == null) goto L51;
        r22.setPadding(AndroidUtilities.dp(70.0f), 0, AndroidUtilities.dp(70.0f), AndroidUtilities.dp(10.0f));
    L51:
        this.mapView.getView().setLayoutParams(r72);
        goto L52
    L61:
        return;
    L39:
        if (r36.getTag() != null) goto L41;
        float r46 = 48.0f;
    L42:
        int r04 = (r03 - AndroidUtilities.dp(r46)) + (r2 / 2);
        this.markerTop = r04;
        r36.setTranslationY(r04);
        goto L43
    L41:
        r46 = 69.0f;
        goto L42
    L26:
        if (r42 == 1) goto L29;
        r43 = 10;
        goto L30
    L16:
        if (r3.getView().getVisibility() != 4) goto L20;
        this.mapView.getView().setVisibility(0);
        this.mapViewClip.setVisibility(0);
        MapOverlayView r37 = this.overlayView;
        if (r37 == null) goto L20;
        r37.setVisibility(0);
        goto L20
    L60:
        return;
    L5:
        r02 = -this.mapViewClip.getMeasuredHeight();
        r2 = 0;
        goto L7
    }

    private void updateEmptyView() {
        if (this.searching == false) goto L10;
        boolean r0 = this.searchInProgress;
        RecyclerListView r2 = this.searchListView;
        if (r0 == false) goto L8;
        r2.setEmptyView(null);
        this.emptyView.setVisibility(8);
        this.searchListView.setVisibility(8);
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

    private void updateShowAllButton() {
        if (this.showAllButton != null) goto L5;
        return;
    L5:
        boolean r1 = false;
        if (this.showAllMode == false) goto L10;
        showShowAllButton(false, true);
        fitAllLiveLocations(true);
        return;
    L10:
        if (getConnectionsManager() == null) goto L12;
        int r0 = getConnectionsManager().getCurrentTime();
    L13:
        int r3 = this.markers.size();
        int r4 = 0;
        int r5 = 0;
    L14:
        if (r4 >= r3) goto L27;
        TLRPC.Message r6 = this.markers.get(r4).object;
        if (r6 == null) goto L25;
        TLRPC.MessageMedia r7 = r6.media;
        if (r7 == null) goto L25;
        int r72 = r7.period;
        if (r72 != Integer.MAX_VALUE) goto L23;
    L24:
        r5 = r5 + 1;
        goto L25
    L23:
        if ((r6.date + r72) > r0) goto L24;
    L25:
        r4 = r4 + 1;
        goto L14
    L27:
        if (this.markersMap.get(getUserConfig().getClientUserId()) == null) goto L29;
        boolean r02 = true;
    L31:
        if (this.myLocation == null) goto L35;
        if (r02 == true) goto L35;
        r5 = r5 + 1;
    L35:
        if (r5 < 2) goto L37;
        r1 = true;
    L37:
        showShowAllButton(r1, true);
        return;
    L29:
        r02 = false;
        goto L31
    L12:
        r0 = 0;
        goto L13
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r46) {
        this.searchWas = false;
        this.searching = false;
        this.searchInProgress = false;
        LocationActivityAdapter r0 = this.adapter;
        if (r0 == null) goto L5;
        r0.destroy();
    L5:
        LocationActivitySearchAdapter r02 = this.searchAdapter;
        if (r02 == null) goto L9;
        r02.destroy();
    L9:
        if (this.chatLocation == null) goto L12;
        Location r03 = new Location("network");
        this.userLocation = r03;
        r03.setLatitude(this.chatLocation.geo_point.lat);
        this.userLocation.setLongitude(this.chatLocation.geo_point._long);
    L15:
        if (getParentActivity() != null) goto L17;
    L19:
        boolean r04 = false;
    L20:
        this.locationDenied = r04;
        ActionBar r05 = this.actionBar;
        int r13 = Theme.key_dialogBackground;
        r05.setBackgroundColor(getThemedColor(r13));
        ActionBar r06 = this.actionBar;
        int r3 = Theme.key_dialogTextBlack;
        r06.setTitleColor(getThemedColor(r3));
        this.actionBar.setItemsColor(getThemedColor(r3), false);
        this.actionBar.setItemsBackgroundColor(getThemedColor(Theme.key_dialogButtonSelector), false);
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        INavigationLayout r07 = this.parentLayout;
        if (r07 != null) goto L23;
    L25:
        this.actionBar.setAddToContainer(false);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        ActionBarMenu r14 = this.actionBar.createMenu();
        if (this.chatLocation == null) goto L28;
        this.actionBar.setTitle(LocaleController.getString(R.string.ChatLocation));
    L50:
        NestedFrameLayout r08 = new NestedFrameLayout(this, r46);
        this.fragmentView = r08;
        NestedFrameLayout r10 = r08;
        r08.setBackgroundColor(getThemedColor(r13));
        Drawable r09 = r46.getResources().getDrawable(R.drawable.sheet_shadow_round).mutate();
        this.shadowDrawable = r09;
        int r4 = getThemedColor(r13);
        PorterDuff.Mode r5 = PorterDuff.Mode.MULTIPLY;
        r09.setColorFilter(new PorterDuffColorFilter(r4, r5));
        Rect r010 = new Rect();
        this.shadowDrawable.getPadding(r010);
        int r32 = this.locationType;
        if (r32 == 0) goto L55;
        if (r32 == 1) goto L55;
        FrameLayout.LayoutParams r33 = new FrameLayout.LayoutParams(-1, AndroidUtilities.dp(6.0f) + r010.top);
    L56:
        r33.gravity = 83;
        AnonymousClass3 r6 = new AnonymousClass3(this, r46);
        this.mapViewClip = r6;
        r6.setBackgroundDrawable(new MapPlaceholderDrawable(isActiveThemeDark()));
        MessageObject r42 = this.messageObject;
        if (r42 != null) goto L61;
        int r15 = this.locationType;
        if (r15 == 0) goto L64;
        if (r15 != 1) goto L61;
    L64:
        SearchButton r43 = new SearchButton(r46);
        this.searchAreaButton = r43;
        r43.setTranslationX(-AndroidUtilities.dp(80.0f));
        Drawable r44 = Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(40.0f), getThemedColor(Theme.key_location_actionBackground), getThemedColor(Theme.key_location_actionPressedBackground));
        ScaleStateListAnimator.apply(this.searchAreaButton);
        this.searchAreaButton.setTranslationZ(AndroidUtilities.dp(2.0f));
        this.searchAreaButton.setOutlineProvider(ViewOutlineProviderImpl.BOUNDS_ROUND_RECT);
        this.searchAreaButton.setBackgroundDrawable(r44);
        this.searchAreaButton.setTextColor(getThemedColor(Theme.key_location_actionActiveIcon));
        this.searchAreaButton.setTextSize(1, 14.0f);
        this.searchAreaButton.setTypeface(AndroidUtilities.bold());
        this.searchAreaButton.setGravity(17);
        this.searchAreaButton.setPadding(AndroidUtilities.dp(20.0f), 0, AndroidUtilities.dp(20.0f), 0);
        this.mapViewClip.addView(this.searchAreaButton, LayoutHelper.createFrame(-2, 40.0f, 49, 80.0f, 12.0f, 80.0f, 0.0f));
        int r45 = this.locationType;
        SearchButton r8 = this.searchAreaButton;
        if (r45 != 3) goto L67;
        r8.setText(LocaleController.getString(R.string.OpenInMaps));
        this.searchAreaButton.setOnClickListener(new LocationActivity$$ExternalSyntheticLambda2(this));
        this.searchAreaButton.setTranslationX(0.0f);
    L68:
        int r82 = Theme.key_location_actionIcon;
        FrameLayout.LayoutParams r142 = r33;
        ActionBarMenuItem r2 = new ActionBarMenuItem(r46, null, 0, getThemedColor(r82), getResourceProvider());
        this.mapTypeButton = r2;
        r2.setClickable(true);
        this.mapTypeButton.setSubMenuOpenSide(2);
        this.mapTypeButton.setAdditionalXOffset(AndroidUtilities.dp(10.0f));
        this.mapTypeButton.setAdditionalYOffset(-AndroidUtilities.dp(10.0f));
        this.mapTypeButton.addSubItem(2, R.drawable.msg_map, LocaleController.getString(R.string.Map), getResourceProvider());
        this.mapTypeButton.addSubItem(3, R.drawable.msg_satellite, LocaleController.getString(R.string.Satellite), getResourceProvider());
        this.mapTypeButton.addSubItem(4, R.drawable.msg_hybrid, LocaleController.getString(R.string.Hybrid), getResourceProvider());
        this.mapTypeButton.setContentDescription(LocaleController.getString(R.string.AccDescrMoreOptions));
        int r34 = AndroidUtilities.dp(40.0f);
        int r52 = Theme.key_location_actionBackground;
        int r62 = getThemedColor(r52);
        int r7 = Theme.key_location_actionPressedBackground;
        Drawable r35 = Theme.createSimpleSelectorCircleDrawable(r34, r62, getThemedColor(r7));
        ScaleStateListAnimator.apply(this.mapTypeButton);
        this.mapTypeButton.setTranslationZ(AndroidUtilities.dp(2.0f));
        ActionBarMenuItem r63 = this.mapTypeButton;
        ViewOutlineProvider r9 = ViewOutlineProviderImpl.BOUNDS_OVAL;
        r63.setOutlineProvider(r9);
        this.mapTypeButton.setBackgroundDrawable(r35);
        this.mapTypeButton.setIcon(R.drawable.msg_map_type);
        this.mapViewClip.addView(this.mapTypeButton, LayoutHelper.createFrame(40, 40.0f, 53, 0.0f, 12.0f, 12.0f, 0.0f));
        this.mapTypeButton.setOnClickListener(new LocationActivity$$ExternalSyntheticLambda12(this));
        this.mapTypeButton.setDelegate(new LocationActivity$$ExternalSyntheticLambda13(this));
        this.locationButton = new ImageView(r46);
        Drawable r36 = Theme.createSimpleSelectorCircleDrawable(AndroidUtilities.dp(40.0f), getThemedColor(r52), getThemedColor(r7));
        ScaleStateListAnimator.apply(this.locationButton);
        this.locationButton.setTranslationZ(AndroidUtilities.dp(2.0f));
        this.locationButton.setOutlineProvider(r9);
        this.locationButton.setBackground(r36);
        this.locationButton.setImageResource(R.drawable.msg_current_location);
        ImageView r37 = this.locationButton;
        ImageView.ScaleType r47 = ImageView.ScaleType.CENTER;
        r37.setScaleType(r47);
        ImageView r38 = this.locationButton;
        int r12 = Theme.key_location_actionActiveIcon;
        r38.setColorFilter(new PorterDuffColorFilter(getThemedColor(r12), r5));
        this.locationButton.setTag(Integer.valueOf(r12));
        this.locationButton.setContentDescription(LocaleController.getString(R.string.AccDescrMyLocation));
        FrameLayout.LayoutParams r39 = LayoutHelper.createFrame(40, 40.0f, 85, 0.0f, 0.0f, 12.0f, 12.0f);
        r39.bottomMargin += r142.height - r010.top;
        this.mapViewClip.addView(this.locationButton, r39);
        this.locationButton.setOnClickListener(new LocationActivity$$ExternalSyntheticLambda14(this));
        TextView r310 = new TextView(r46);
        this.showAllButton = r310;
        r310.setGravity(17);
        this.showAllButton.setTranslationZ(AndroidUtilities.dp(2.0f));
        this.showAllButton.setTextSize(1, 15.0f);
        this.showAllButton.setTextColor(Theme.getColor(Theme.key_featuredStickers_addButton, this.resourceProvider));
        this.showAllButton.setTypeface(AndroidUtilities.bold());
        this.showAllButton.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
        this.showAllButton.setText(LocaleController.getString(R.string.LocationsShowAll));
        this.showAllButton.setBackground(Theme.createRadSelectorDrawable(getThemedColor(r52), getThemedColor(r7), AndroidUtilities.dp(19.0f), AndroidUtilities.dp(19.0f)));
        FrameLayout.LayoutParams r311 = LayoutHelper.createFrame(-2, 38.0f, 81, 12.0f, 0.0f, 12.0f, 12.0f);
        r311.bottomMargin += r142.height - r010.top;
        this.mapViewClip.addView(this.showAllButton, r311);
        ScaleStateListAnimator.apply(this.showAllButton);
        this.showAllButton.setOnClickListener(new LocationActivity$$ExternalSyntheticLambda15(this));
        showShowAllButton(false, false);
        this.proximityButton = new ImageView(r46);
        Drawable r312 = Theme.createSimpleSelectorCircleDrawable(AndroidUtilities.dp(40.0f), getThemedColor(r52), getThemedColor(r7));
        ScaleStateListAnimator.apply(this.proximityButton);
        this.proximityButton.setTranslationZ(AndroidUtilities.dp(2.0f));
        this.proximityButton.setOutlineProvider(r9);
        this.proximityButton.setColorFilter(new PorterDuffColorFilter(getThemedColor(r82), r5));
        this.proximityButton.setBackgroundDrawable(r312);
        this.proximityButton.setScaleType(r47);
        this.proximityButton.setContentDescription(LocaleController.getString(R.string.AccDescrLocationNotify));
        this.mapViewClip.addView(this.proximityButton, LayoutHelper.createFrame(40, 40.0f, 53, 0.0f, 62.0f, 12.0f, 0.0f));
        this.proximityButton.setOnClickListener(new LocationActivity$$ExternalSyntheticLambda16(this));
        if (DialogObject.isChatDialog(this.dialogId) == false) goto L71;
        TLRPC.Chat r11 = getMessagesController().getChat(Long.valueOf(-this.dialogId));
    L72:
        MessageObject r313 = this.messageObject;
        if (r313 != null) goto L75;
    L82:
        char r92 = 4;
        boolean r132 = false;
        this.proximityButton.setVisibility(8);
        this.proximityButton.setImageResource(R.drawable.msg_location_alert);
    L96:
        HintView2 r314 = new HintView2(r46, 1);
        this.hintView = r314;
        r314.setLayerType(2, null);
        this.hintView.setDuration(4000);
        this.hintView.setJoint(1.0f, -25.0f);
        this.hintView.setPadding(0, AndroidUtilities.dp(4.0f), 0, 0);
        this.mapViewClip.addView(this.hintView, LayoutHelper.createFrame(-1, -2.0f, 51, 8.0f, 106.0f, 8.0f, 0.0f));
        LinearLayout r315 = new LinearLayout(r46);
        this.emptyView = r315;
        r315.setOrientation(1);
        this.emptyView.setGravity(1);
        this.emptyView.setPadding(0, AndroidUtilities.dp(160.0f), 0, 0);
        this.emptyView.setVisibility(8);
        r10.addView(this.emptyView, LayoutHelper.createFrame(-1, -1.0f));
        this.emptyView.setOnTouchListener(new LocationActivity$$ExternalSyntheticLambda17());
        ImageView r316 = new ImageView(r46);
        this.emptyImageView = r316;
        r316.setImageResource(R.drawable.location_empty);
        this.emptyImageView.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_dialogEmptyImage), r5));
        this.emptyView.addView(this.emptyImageView, LayoutHelper.createLinear(-2, -2));
        TextView r317 = new TextView(r46);
        this.emptyTitleTextView = r317;
        int r48 = Theme.key_dialogEmptyText;
        r317.setTextColor(getThemedColor(r48));
        this.emptyTitleTextView.setGravity(17);
        this.emptyTitleTextView.setTypeface(AndroidUtilities.bold());
        this.emptyTitleTextView.setTextSize(1, 17.0f);
        this.emptyTitleTextView.setText(LocaleController.getString(R.string.NoPlacesFound));
        this.emptyView.addView(this.emptyTitleTextView, LayoutHelper.createLinear(-2, -2, 17, 0, 11, 0, 0));
        TextView r318 = new TextView(r46);
        this.emptySubtitleTextView = r318;
        r318.setTextColor(getThemedColor(r48));
        this.emptySubtitleTextView.setGravity(17);
        this.emptySubtitleTextView.setTextSize(1, 15.0f);
        this.emptySubtitleTextView.setPadding(AndroidUtilities.dp(40.0f), 0, AndroidUtilities.dp(40.0f), 0);
        this.emptyView.addView(this.emptySubtitleTextView, LayoutHelper.createLinear(-2, -2, 17, 0, 6, 0, 0));
        RecyclerListView r152 = new RecyclerListView(r46);
        this.listView = r152;
        int r319 = this.locationType;
        long r49 = this.dialogId;
        Theme.ResourcesProvider r72 = getResourceProvider();
        boolean r93 = this.fromStories;
        if (this.locationType != 8) goto L99;
        NestedFrameLayout r64 = r10;
        boolean r102 = true;
    L100:
        NestedFrameLayout r382 = r64;
        AnonymousClass4 r011 = new AnonymousClass4(this, r46, r319, r49, false, r72, false, r93, r102);
        this.adapter = r011;
        r152.setAdapter(r011);
        RecyclerListView r012 = this.listView;
        LinearLayoutManager r320 = new LinearLayoutManager(r46, 1, false);
        this.layoutManager = r320;
        r012.setLayoutManager(r320);
        if (this.searchStoriesArea == null) goto L103;
        this.sharedMediaHeader = new GraySectionCell(r46, this.resourceProvider);
        int r24 = 1;
        ActionBarMenu r41 = r14;
        FrameLayout.LayoutParams r422 = r142;
        TLRPC.Chat r432 = r11;
        AnonymousClass6 r013 = new AnonymousClass6(this, r46, 0, new SharedMediaLayout.SharedMediaPreloader(this), 0, null, null, null, 8, 0, this, new AnonymousClass5(this), 0, getResourceProvider());
        this.sharedMediaLayout = r013;
        r013.setBackgroundColor(getThemedColor(r13));
        int r65 = -1;
        this.sharedMediaLayout.addView(this.sharedMediaHeader, LayoutHelper.createFrame(-1, 32, 55));
        this.adapter.setSharedMediaLayout(this.sharedMediaLayout);
        char r73 = 2;
        this.listView.setOverScrollMode(2);
        DefaultItemAnimator r014 = new DefaultItemAnimator();
        boolean r83 = false;
        r014.setSupportsChangeAnimations(false);
        r014.setDelayAnimations(false);
        r014.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r014.setDurations(350);
        this.listView.setItemAnimator(r014);
    L104:
        this.adapter.setMyLocationDenied(this.locationDenied, r83);
        this.adapter.setUpdateRunnable(new LocationActivity$$ExternalSyntheticLambda18(this));
        this.listView.setVerticalScrollBarEnabled(r83);
        r382.addView(this.listView, LayoutHelper.createFrame(r65, r65, 51));
        MessageObject r015 = this.messageObject;
        if (r015 == null) goto L113;
        TLRPC.Message r016 = r015.messageOwner;
        if (r016 == null) goto L113;
        TLRPC.MessageMedia r017 = r016.media;
        if (r017 == null) goto L113;
        if (TextUtils.isEmpty(r017.address) == true) goto L113;
        this.adapter.setAddressNameOverride(this.messageObject.messageOwner.media.address);
    L113:
        this.listView.setOnScrollListener(new AnonymousClass7(this));
        ((DefaultItemAnimator) this.listView.getItemAnimator()).setDelayAnimations(r83);
        this.listView.setOnItemLongClickListener(new LocationActivity$$ExternalSyntheticLambda19(this, r46));
        this.listView.setOnItemClickListener(new LocationActivity$$ExternalSyntheticLambda3(this));
        this.adapter.setDelegate(this.dialogId, new LocationActivity$$ExternalSyntheticLambda4(this));
        this.adapter.setOverScrollHeight(this.overScrollHeight);
        r382.addView(this.mapViewClip, LayoutHelper.createFrame(r65, r65, 51));
        IMapsProvider.IMapView r018 = ApplicationLoader.getMapsProvider().onCreateMapView(r46);
        this.mapView = r018;
        r018.getView().setAlpha(0.0f);
        this.mapView.setOnDispatchTouchEventInterceptor(new LocationActivity$$ExternalSyntheticLambda5(this));
        this.mapView.setOnInterceptTouchEventInterceptor(new LocationActivity$$ExternalSyntheticLambda6(this));
        this.mapView.setOnLayoutListener(new LocationActivity$$ExternalSyntheticLambda7(this));
        new Thread(new LocationActivity$$ExternalSyntheticLambda8(this, this.mapView)).start();
        MessageObject r019 = this.messageObject;
        if (r019 == null) goto L116;
    L133:
        int r133 = r24;
        if (r019 != null) goto L136;
    L137:
        boolean r134 = r133;
        if (this.chatLocation == null) goto L145;
    L139:
        TLRPC.TL_channelLocation r020 = this.chatLocation;
        if (r020 == null) goto L142;
        this.adapter.setChatLocation(r020);
        r134 = r133;
        goto L145
    L142:
        MessageObject r021 = this.messageObject;
        r134 = r133;
        if (r021 == null) goto L145;
        this.adapter.setMessageObject(r021);
        r134 = r133;
    L145:
        MessageObject r022 = this.messageObject;
        if (r022 != null) goto L148;
    L150:
        char r112 = r83;
    L151:
        if (r112 >= r73) goto L153;
        this.undoView[r112] = new UndoView(r46);
        this.undoView[r112].setAdditionalTranslationY(AndroidUtilities.dp(10.0f));
        this.undoView[r112].setTranslationZ(AndroidUtilities.dp(5.0f));
        this.mapViewClip.addView(this.undoView[r112], LayoutHelper.createFrame(-1, -2.0f, 83, 8.0f, 0.0f, 8.0f, 8.0f));
        r112 = r112 + 1;
        goto L151
    L153:
        AnonymousClass11 r023 = new AnonymousClass11(this, r46, r010);
        this.shadow = r023;
        r023.setTranslationZ(AndroidUtilities.dp(6.0f));
        this.mapViewClip.addView(this.shadow, r422);
        if (this.messageObject == null) goto L156;
    L160:
        r382.addView(this.actionBar);
        updateEmptyView();
        return this.fragmentView;
    L156:
        if (this.chatLocation != null) goto L160;
        if (this.initialLocation == null) goto L160;
        this.userLocationMoved = r134;
        ImageView r024 = this.locationButton;
        int r321 = Theme.key_location_actionIcon;
        r024.setColorFilter(new PorterDuffColorFilter(getThemedColor(r321), PorterDuff.Mode.MULTIPLY));
        this.locationButton.setTag(Integer.valueOf(r321));
        goto L160
    L148:
        if (this.locationType != 6) goto L150;
        this.adapter.setMessageObject(r022);
        goto L150
    L136:
        if (r019.isLiveLocation() == false) goto L139;
    L116:
        if (this.chatLocation != null) goto L133;
        TLRPC.Chat r322 = r432;
        if (r322 == null) goto L125;
        if (this.locationType != 4) goto L125;
        if (this.dialogId == 0) goto L125;
        FrameLayout r410 = new FrameLayout(r46);
        r410.setBackgroundResource(R.drawable.livepin);
        this.mapViewClip.addView(r410, LayoutHelper.createFrame(62, 76, 49));
        BackupImageView r53 = new BackupImageView(r46);
        r53.setRoundRadius(AndroidUtilities.dp(26.0f));
        AvatarDrawable r143 = new AvatarDrawable(r322);
        r143.setRoundRadius(AndroidUtilities.dp(26.0f));
        r53.setForUserOrChat(r322, r143);
        r410.addView(r53, LayoutHelper.createFrame(52, 52.0f, 51, 5.0f, 5.0f, 0.0f, 0.0f));
        this.markerImageView = r410;
        r410.setTag(Integer.valueOf(r24));
    L125:
        if (this.markerImageView != null) goto L127;
        ImageView r323 = new ImageView(r46);
        r323.setImageResource(R.drawable.map_pin2);
        this.mapViewClip.addView(r323, LayoutHelper.createFrame(28, 48, 49));
        this.markerImageView = r323;
    L127:
        RecyclerListView r025 = new RecyclerListView(r46);
        this.searchListView = r025;
        r025.setVisibility(8);
        int r135 = r24;
        this.searchListView.setLayoutManager(new LinearLayoutManager(r46, r135 == true ? 1 : 0, r83));
        Theme.ResourcesProvider r411 = getResourceProvider();
        if (this.locationType != 8) goto L131;
        boolean r54 = r135 == true ? 1 : 0;
    L132:
        AnonymousClass9 r026 = new AnonymousClass9(this, r46, r411, false, r54);
        this.searchAdapter = r026;
        r026.setDelegate(0, new LocationActivity$$ExternalSyntheticLambda9(this));
        r382.addView(this.searchListView, LayoutHelper.createFrame(r65, r65, 51));
        this.searchListView.setOnScrollListener(new AnonymousClass10(this));
        this.searchListView.setOnItemClickListener(new LocationActivity$$ExternalSyntheticLambda10(this, r41));
        r134 = r135;
        goto L145
    L131:
        r54 = r83;
        goto L132
    L103:
        r24 = 1;
        r432 = r11;
        r422 = r142;
        r41 = r14;
        r73 = 2;
        r83 = false;
        r65 = -1;
        goto L104
    L99:
        r64 = r10;
        r102 = false;
        goto L100
    L75:
        if (r313.isLiveLocation() == false) goto L82;
        if (this.messageObject.isExpiredLiveLocation(getConnectionsManager().getCurrentTime()) == true) goto L82;
        if (ChatObject.isChannel(r11) == true) goto L81;
    L83:
        LocationController.SharingLocationInfo r324 = getLocationController().getSharingLocationInfo(this.dialogId);
        if (r324 == null) goto L89;
        if (r324.proximityMeters <= 0) goto L89;
        this.proximityButton.setImageResource(R.drawable.msg_location_alert2);
        r92 = 4;
        r132 = false;
    L89:
        if (DialogObject.isUserDialog(this.dialogId) == true) goto L91;
    L93:
        r92 = 4;
        r132 = false;
    L94:
        this.proximityButton.setImageResource(R.drawable.msg_location_alert);
        goto L96
    L91:
        if (this.messageObject.getFromChatId() != getUserConfig().getClientUserId()) goto L93;
        r92 = 4;
        this.proximityButton.setVisibility(4);
        r132 = false;
        this.proximityButton.setAlpha(0.0f);
        this.proximityButton.setScaleX(0.4f);
        this.proximityButton.setScaleY(0.4f);
        goto L94
    L81:
        if (r11.megagroup == true) goto L83;
    L71:
        r11 = null;
        goto L72
    L67:
        r8.setText(LocaleController.getString(R.string.PlacesInThisArea));
        this.searchAreaButton.setOnClickListener(new LocationActivity$$ExternalSyntheticLambda11(this));
    L61:
        if (r42 == null) goto L68;
        if (this.locationType != 3) goto L68;
    L55:
        r33 = new FrameLayout.LayoutParams(-1, AndroidUtilities.dp(21.0f) + r010.top);
        goto L56
    L28:
        MessageObject r027 = this.messageObject;
        if (r027 != null) goto L31;
        this.actionBar.setTitle(LocaleController.getString(R.string.ShareLocation));
        if (this.locationType == 4) goto L50;
        this.overlayView = new MapOverlayView(this, r46);
        ActionBarMenuItem r028 = r14.addItem(0, R.drawable.outline_header_search, getResourceProvider()).setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass2(this));
        this.searchItem = r028;
        r028.setSearchFieldHint(LocaleController.getString(R.string.Search));
        this.searchItem.setContentDescription(LocaleController.getString(R.string.Search));
        EditTextBoldCursor r029 = this.searchItem.getSearchField();
        r029.setTextColor(getThemedColor(r3));
        r029.setCursorColor(getThemedColor(r3));
        r029.setHintTextColor(getThemedColor(Theme.key_chat_messagePanelHint));
        goto L50
    L31:
        if (r027.isLiveLocation() == false) goto L33;
        this.actionBar.setTitle(LocaleController.getString(R.string.AttachLiveLocation));
        ActionBarMenuItem r030 = r14.addItem(0, R.drawable.ic_ab_other, getResourceProvider());
        this.otherItem = r030;
        r030.addSubItem(6, R.drawable.filled_directions, LocaleController.getString(R.string.GetDirections));
        goto L50
    L33:
        String r031 = this.messageObject.messageOwner.media.title;
        if (r031 != null) goto L36;
    L38:
        this.actionBar.setTitle(LocaleController.getString(R.string.ChatLocation));
    L40:
        if (this.locationType == 3) goto L50;
        ActionBarMenuItem r032 = r14.addItem(0, R.drawable.ic_ab_other, getResourceProvider());
        this.otherItem = r032;
        r032.addSubItem(1, R.drawable.msg_openin, LocaleController.getString(R.string.OpenInExternalApp));
        if (getLocationController().isSharingLocation(this.dialogId) == false) goto L44;
    L46:
        this.otherItem.setContentDescription(LocaleController.getString(R.string.AccDescrMoreOptions));
        goto L50
    L44:
        if (this.isSharingAllowed == false) goto L46;
        this.otherItem.addSubItem(5, R.drawable.msg_location, LocaleController.getString(R.string.SendLiveLocationMenu));
        goto L46
    L36:
        if (r031.length() <= 0) goto L38;
        this.actionBar.setTitle(LocaleController.getString(R.string.SharedPlace));
        goto L40
    L23:
        if (r07.isLayersLayout() == false) goto L25;
        this.actionBar.setOccupyStatusBar(false);
        goto L25
    L17:
        if (getParentActivity().checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) goto L19;
        r04 = true;
        goto L20
    L12:
        if (this.messageObject == null) goto L15;
        Location r033 = new Location("network");
        this.userLocation = r033;
        r033.setLatitude(this.messageObject.messageOwner.media.geo.lat);
        this.userLocation.setLongitude(this.messageObject.messageOwner.media.geo._long);
        goto L15
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r12, int r13, Object... r14) {
        if (r12 != NotificationCenter.closeChats) goto L6;
        removeSelfFromStack(true);
        return;
    L6:
        int r1 = 0;
        if (r12 != NotificationCenter.locationPermissionGranted) goto L19;
        this.locationDenied = false;
        LocationActivityAdapter r122 = this.adapter;
        if (r122 == null) goto L11;
        r122.setMyLocationDenied(false, false);
    L11:
        IMapsProvider.IMap r11 = this.map;
        if (r11 == null) goto L115;
        r11.setMyLocationEnabled(true);     // Catch: Exception -> L15
        return;
    L15:
        e = move-exception;
        FileLog.e(e);
        return;
    L115:
        return;
    L19:
        if (r12 != NotificationCenter.locationPermissionDenied) goto L25;
        this.locationDenied = true;
        LocationActivityAdapter r112 = this.adapter;
        if (r112 == null) goto L116;
        r112.setMyLocationDenied(true, false);
        return;
    L116:
        return;
    L25:
        if (r12 != NotificationCenter.liveLocationsChanged) goto L32;
        LocationActivityAdapter r123 = this.adapter;
        if (r123 == null) goto L29;
        r123.notifyDataSetChanged();
    L29:
        updateShowAllButton();
        return;
    L32:
        if (r12 != NotificationCenter.didReceiveNewMessages) goto L61;
        if (((Boolean) r14[2]).booleanValue() == false) goto L37;
        return;
    L37:
        if (((Long) r14[0]).longValue() == this.dialogId) goto L39;
        return;
    L39:
        if (this.messageObject == null) goto L124;
        ArrayList r124 = (ArrayList) r14[1];
        boolean r132 = false;
    L43:
        if (r1 >= r124.size()) goto L55;
        MessageObject r142 = (MessageObject) r124.get(r1);
        boolean r2 = r142.isLiveLocation();
        TLRPC.Message r3 = r142.messageOwner;
        if (r2 == false) goto L48;
        addUserMarker(r3);
        r132 = true;
    L54:
        r1 = r1 + 1;
        goto L43
    L48:
        if ((r3.action instanceof TLRPC.TL_messageActionGeoProximityReached) == false) goto L54;
        if (DialogObject.isUserDialog(r142.getDialogId()) == false) goto L54;
        this.proximityButton.setImageResource(R.drawable.msg_location_alert);
        IMapsProvider.ICircle r143 = this.proximityCircle;
        if (r143 == null) goto L54;
        r143.remove();
        this.proximityCircle = null;
        goto L54
    L55:
        if (r132 == false) goto L118;
        LocationActivityAdapter r125 = this.adapter;
        if (r125 == null) goto L119;
        r125.setLiveLocations(this.markers);
        return;
    L119:
        return;
    L118:
        return;
    L124:
        return;
    L61:
        if (r12 != NotificationCenter.replaceMessagesObjects) goto L120;
        long r126 = ((Long) r14[0]).longValue();
        if (r126 == this.dialogId) goto L65;
        return;
    L65:
        if (this.messageObject == null) goto L125;
        ArrayList r144 = (ArrayList) r14[1];
        int r22 = 0;
        boolean r32 = false;
    L69:
        if (r22 >= r144.size()) goto L94;
        MessageObject r4 = (MessageObject) r144.get(r22);
        if (r4.isLiveLocation() == false) goto L93;
        LiveLocation r5 = this.markersMap.get(getMessageId(r4.messageOwner));
        if (r5 == null) goto L93;
        LocationController.SharingLocationInfo r33 = getLocationController().getSharingLocationInfo(r126);
        if (r33 != null) goto L78;
    L79:
        TLRPC.Message r34 = r4.messageOwner;
        r5.object = r34;
        TLRPC.GeoPoint r35 = r34.media.geo;
        IMapsProvider.LatLng r6 = new IMapsProvider.LatLng(r35.lat, r35._long);
        r5.marker.setPosition(r6);
        if (this.selectedMarkerId != r5.id) goto L82;
        this.map.animateCamera(ApplicationLoader.getMapsProvider().newCameraUpdateLatLng(r5.marker.getPosition()));
    L82:
        IMapsProvider.IMarker r36 = r5.directionMarker;
        if (r36 == null) goto L92;
        r36.getPosition();
        r5.directionMarker.setPosition(r6);
        int r37 = r4.messageOwner.media.heading;
        if (r37 == 0) goto L90;
        r5.directionMarker.setRotation(r37);
        if (r5.hasRotation == true) goto L92;
        r5.directionMarker.setIcon(R.drawable.map_pin_cone2);
        r5.hasRotation = true;
        goto L92
    L90:
        if (r5.hasRotation == false) goto L92;
        r5.directionMarker.setRotation(0);
        r5.directionMarker.setIcon(R.drawable.map_pin_circle);
        r5.hasRotation = false;
    L92:
        r32 = true;
        goto L93
    L78:
        if (r33.mid == r4.getId()) goto L92;
    L93:
        r22 = r22 + 1;
        goto L69
    L94:
        if (r32 == false) goto L100;
        LocationActivityAdapter r127 = this.adapter;
        if (r127 == null) goto L100;
        r127.notifyDataSetChanged();
        ProximitySheet r128 = this.proximitySheet;
        if (r128 == null) goto L100;
        r128.updateText(true, true);
    L100:
        if (r32 == false) goto L122;
        updateShowAllButton();
        return;
    L122:
        return;
    L125:
        return;
    }

    public boolean disablePermissionCheck() {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean finishFragment(boolean r2) {
        if (onCheckGlScreenshot() == false) goto L7;
        return false;
    L7:
        return super.finishFragment(r2);
    }

    public String getAddressName() {
        LocationActivityAdapter r0 = this.adapter;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getAddressName();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        LocationActivity$$ExternalSyntheticLambda22 r8 = new LocationActivity$$ExternalSyntheticLambda22(this);
        int r2 = 0;
    L4:
        if (r2 >= this.undoView.length) goto L6;
        UndoView r10 = this.undoView[r2];
        int r11 = ThemeDescription.FLAG_BACKGROUNDFILTER;
        int r18 = Theme.key_undo_background;
        r1.add(new ThemeDescription(r10, r11, null, null, null, null, r18));
        int r17 = Theme.key_undo_cancelColor;
        r1.add(new ThemeDescription(this.undoView[r2], 0, new Class[]{UndoView.class}, new String[]{"undoImageView"}, null, null, null, r17));
        r1.add(new ThemeDescription(this.undoView[r2], 0, new Class[]{UndoView.class}, new String[]{"undoTextView"}, null, null, null, r17));
        int r15 = Theme.key_undo_infoColor;
        r1.add(new ThemeDescription(this.undoView[r2], 0, new Class[]{UndoView.class}, new String[]{"infoTextView"}, null, null, null, r15));
        r1.add(new ThemeDescription(this.undoView[r2], 0, new Class[]{UndoView.class}, new String[]{"subinfoTextView"}, null, null, null, r15));
        r1.add(new ThemeDescription(this.undoView[r2], 0, new Class[]{UndoView.class}, new String[]{"textPaint"}, null, null, null, r15));
        r1.add(new ThemeDescription(this.undoView[r2], 0, new Class[]{UndoView.class}, new String[]{"progressPaint"}, null, null, null, r15));
        r1.add(new ThemeDescription(this.undoView[r2], 0, new Class[]{UndoView.class}, new String[]{"leftImageView"}, "BODY", r18));
        r1.add(new ThemeDescription(this.undoView[r2], 0, new Class[]{UndoView.class}, new String[]{"leftImageView"}, "Wibe Big", r18));
        r1.add(new ThemeDescription(this.undoView[r2], 0, new Class[]{UndoView.class}, new String[]{"leftImageView"}, "Wibe Big 3", r15));
        r1.add(new ThemeDescription(this.undoView[r2], 0, new Class[]{UndoView.class}, new String[]{"leftImageView"}, "Wibe Small", r15));
        r1.add(new ThemeDescription(this.undoView[r2], 0, new Class[]{UndoView.class}, new String[]{"leftImageView"}, "Body Main", r15));
        r1.add(new ThemeDescription(this.undoView[r2], 0, new Class[]{UndoView.class}, new String[]{"leftImageView"}, "Body Top", r15));
        r1.add(new ThemeDescription(this.undoView[r2], 0, new Class[]{UndoView.class}, new String[]{"leftImageView"}, "Line", r15));
        r1.add(new ThemeDescription(this.undoView[r2], 0, new Class[]{UndoView.class}, new String[]{"leftImageView"}, "Curve Big", r15));
        r1.add(new ThemeDescription(this.undoView[r2], 0, new Class[]{UndoView.class}, new String[]{"leftImageView"}, "Curve Small", r15));
        r2 = r2 + 1;
        goto L4
    L6:
        View r3 = this.fragmentView;
        int r4 = ThemeDescription.FLAG_BACKGROUND;
        int r16 = Theme.key_dialogBackground;
        r1.add(new ThemeDescription(r3, r4, null, null, null, r8, r16));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r16));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, r16));
        ActionBar r112 = this.actionBar;
        int r12 = ThemeDescription.FLAG_AB_ITEMSCOLOR;
        int r20 = Theme.key_dialogTextBlack;
        r1.add(new ThemeDescription(r112, r12, null, null, null, null, r20));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, r20));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_dialogButtonSelector));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SEARCH, null, null, null, null, r20));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SEARCHPLACEHOLDER, null, null, null, null, Theme.key_chat_messagePanelHint));
        ActionBarMenuItem r22 = this.searchItem;
        if (r22 == null) goto L10;
        EditTextBoldCursor r23 = r22.getSearchField();
    L9:
        EditTextBoldCursor r14 = r23;
        r1.add(new ThemeDescription(r14, ThemeDescription.FLAG_CURSORCOLOR, null, null, null, null, r20));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBMENUBACKGROUND, null, null, null, r8, Theme.key_actionBarDefaultSubmenuBackground));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBMENUITEM, null, null, null, r8, Theme.key_actionBarDefaultSubmenuItem));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SUBMENUITEM | ThemeDescription.FLAG_IMAGECOLOR, null, null, null, r8, Theme.key_actionBarDefaultSubmenuItemIcon));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        ImageView r122 = this.emptyImageView;
        int r13 = ThemeDescription.FLAG_IMAGECOLOR;
        int r182 = Theme.key_dialogEmptyImage;
        r1.add(new ThemeDescription(r122, r13, null, null, null, null, r182));
        TextView r24 = this.emptyTitleTextView;
        int r21 = ThemeDescription.FLAG_TEXTCOLOR;
        int r162 = Theme.key_dialogEmptyText;
        r1.add(new ThemeDescription(r24, r21, null, null, null, null, r162));
        r1.add(new ThemeDescription(this.emptySubtitleTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r162));
        r1.add(new ThemeDescription(this.shadow, 0, null, null, null, null, Theme.key_sheet_scrollUp));
        ImageView r25 = this.locationButton;
        int r29 = ThemeDescription.FLAG_IMAGECOLOR | ThemeDescription.FLAG_CHECKTAG;
        int r9 = Theme.key_location_actionIcon;
        r1.add(new ThemeDescription(r25, r29, null, null, null, null, r9));
        ImageView r113 = this.locationButton;
        int r123 = ThemeDescription.FLAG_IMAGECOLOR | ThemeDescription.FLAG_CHECKTAG;
        int r172 = Theme.key_location_actionActiveIcon;
        r1.add(new ThemeDescription(r113, r123, null, null, null, null, r172));
        ImageView r26 = this.locationButton;
        int r292 = ThemeDescription.FLAG_BACKGROUNDFILTER;
        int r37 = Theme.key_location_actionBackground;
        r1.add(new ThemeDescription(r26, r292, null, null, null, null, r37));
        ImageView r27 = this.locationButton;
        int r293 = ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE;
        int r45 = Theme.key_location_actionPressedBackground;
        r1.add(new ThemeDescription(r27, r293, null, null, null, null, r45));
        r1.add(new ThemeDescription(this.mapTypeButton, 0, null, null, null, r8, r9));
        r1.add(new ThemeDescription(this.mapTypeButton, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, r37));
        r1.add(new ThemeDescription(this.mapTypeButton, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, r45));
        r1.add(new ThemeDescription(this.proximityButton, 0, null, null, null, r8, r9));
        r1.add(new ThemeDescription(this.proximityButton, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, r37));
        r1.add(new ThemeDescription(this.proximityButton, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, r45));
        r1.add(new ThemeDescription(this.searchAreaButton, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r172));
        r1.add(new ThemeDescription(this.searchAreaButton, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, r37));
        r1.add(new ThemeDescription(this.searchAreaButton, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, r45));
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
        r1.add(new ThemeDescription(this.listView, (ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_USEBACKGROUNDDRAWABLE) | ThemeDescription.FLAG_CHECKTAG, new Class[]{SendLocationCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_location_sendLiveLocationBackground));
        int r132 = Theme.key_windowBackgroundWhiteGrayText3;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{SendLocationCell.class}, new String[]{"accurateTextView"}, null, null, null, r132));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{SendLocationCell.class}, new String[]{"titleTextView"}, null, null, null, Theme.key_location_sendLiveLocationText));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG, new Class[]{SendLocationCell.class}, new String[]{"titleTextView"}, null, null, null, Theme.key_location_sendLocationText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{LocationDirectionCell.class}, new String[]{"buttonTextView"}, null, null, null, Theme.key_featuredStickers_buttonText));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_USEBACKGROUNDDRAWABLE, new Class[]{LocationDirectionCell.class}, new String[]{"frameLayout"}, null, null, null, Theme.key_featuredStickers_addButton));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_USEBACKGROUNDDRAWABLE | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, new Class[]{LocationDirectionCell.class}, new String[]{"frameLayout"}, null, null, null, Theme.key_featuredStickers_addButtonPressed));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ShadowSectionCell.class}, null, null, null, Theme.key_windowBackgroundGrayShadow));
        RecyclerListView r6 = this.listView;
        int r42 = ThemeDescription.FLAG_BACKGROUNDFILTER;
        r1.add(new ThemeDescription(r6, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR | r42, new Class[]{ShadowSectionCell.class}, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_dialogTextBlue2));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{LocationCell.class}, new String[]{"imageView"}, null, null, null, r132));
        int r40 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{LocationCell.class}, new String[]{"nameTextView"}, null, null, null, r40));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{LocationCell.class}, new String[]{"addressTextView"}, null, null, null, r132));
        r1.add(new ThemeDescription(this.searchListView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{LocationCell.class}, new String[]{"imageView"}, null, null, null, r132));
        r1.add(new ThemeDescription(this.searchListView, 0, new Class[]{LocationCell.class}, new String[]{"nameTextView"}, null, null, null, r40));
        r1.add(new ThemeDescription(this.searchListView, 0, new Class[]{LocationCell.class}, new String[]{"addressTextView"}, null, null, null, r132));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{SharingLiveLocationCell.class}, new String[]{"nameTextView"}, null, null, null, r40));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{SharingLiveLocationCell.class}, new String[]{"distanceTextView"}, null, null, null, r132));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{LocationLoadingCell.class}, new String[]{"progressBar"}, null, null, null, Theme.key_progressCircle));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{LocationLoadingCell.class}, new String[]{"textView"}, null, null, null, r132));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{LocationLoadingCell.class}, new String[]{"imageView"}, null, null, null, r132));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{LocationPoweredCell.class}, new String[]{"textView"}, null, null, null, r132));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{LocationPoweredCell.class}, new String[]{"imageView"}, null, null, null, r182));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{LocationPoweredCell.class}, new String[]{"textView2"}, null, null, null, r162));
        return r1;
    L10:
        r23 = null;
        goto L9
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        if (ColorUtils.calculateLuminance(getThemedColor(Theme.key_dialogBackground)) <= 0.699999988079071d) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSwipeBackEnabled(MotionEvent r1) {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r3) {
        ProximitySheet r0 = this.proximitySheet;
        if (r0 == null) goto L7;
        if (r3 == false) goto L6;
        r0.dismiss();
    L6:
        return false;
    L7:
        IMapsProvider.IMapView r02 = this.mapView;
        if (r02 == null) goto L17;
        if (r02.getGlSurfaceView() == null) goto L17;
        if (this.hasScreenshot == true) goto L17;
        if (r3 == false) goto L15;
        onCheckGlScreenshot();
    L15:
        return false;
    L17:
        return super.onBackPressed(r3);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onBecomeFullyHidden() {
        UndoView r2 = this.undoView[0];
        if (r2 == null) goto L6;
        r2.hide(true, 0);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        getNotificationCenter().addObserver(this, NotificationCenter.closeChats);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.locationPermissionGranted);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.locationPermissionDenied);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.liveLocationsChanged);
        MessageObject r0 = this.messageObject;
        if (r0 != null) goto L5;
        return true;
    L5:
        if (r0.isLiveLocation() == false) goto L10;
        getNotificationCenter().addObserver(this, NotificationCenter.didReceiveNewMessages);
        getNotificationCenter().addObserver(this, NotificationCenter.replaceMessagesObjects);
        return true;
    L10:
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.locationPermissionGranted);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.locationPermissionDenied);
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.liveLocationsChanged);
        getNotificationCenter().removeObserver(this, NotificationCenter.closeChats);
        getNotificationCenter().removeObserver(this, NotificationCenter.didReceiveNewMessages);
        getNotificationCenter().removeObserver(this, NotificationCenter.replaceMessagesObjects);
        int r0 = 0;
        IMapsProvider.IMap r1 = this.map;     // Catch: Exception -> L7
        if (r1 == null) goto L37;
        r1.setMyLocationEnabled(false);     // Catch: Exception -> L7
    L37:
        IMapsProvider.IMapView r12 = this.mapView;     // Catch: Exception -> L13
        if (r12 == null) goto L15;
        r12.onDestroy();     // Catch: Exception -> L13
    L15:
        UndoView r13 = this.undoView[0];
        if (r13 == null) goto L18;
        r13.hide(true, 0);
    L18:
        LocationActivityAdapter r14 = this.adapter;
        if (r14 == null) goto L21;
        r14.destroy();
    L21:
        LocationActivitySearchAdapter r15 = this.searchAdapter;
        if (r15 == null) goto L24;
        r15.destroy();
    L24:
        Runnable r16 = this.updateRunnable;
        if (r16 == null) goto L27;
        AndroidUtilities.cancelRunOnUIThread(r16);
        this.updateRunnable = null;
    L27:
        Runnable r17 = this.markAsReadRunnable;
        if (r17 == null) goto L30;
        AndroidUtilities.cancelRunOnUIThread(r17);
        this.markAsReadRunnable = null;
    L30:
        int r18 = this.markers.size();
    L31:
        if (r0 >= r18) goto L36;
        LiveLocation r3 = this.markers.get(r0);
        ImageReceiver r4 = r3.avatarReceiver;
        if (r4 == null) goto L35;
        r4.onDetachedFromWindow();
        r3.avatarReceiver = null;
    L35:
        r0 = r0 + 1;
        goto L31
    L36:
        return;
    L13:
        e = move-exception;
        FileLog.e(e);
    L7:
        e = move-exception;
        FileLog.e(e);
        goto L37
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onLowMemory() {
        super.onLowMemory();
        IMapsProvider.IMapView r0 = this.mapView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.mapsInitialized == false) goto L9;
        r0.onLowMemory();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        IMapsProvider.IMapView r0 = this.mapView;
        if (r0 != null) goto L5;
    L10:
        UndoView r02 = this.undoView[0];
        if (r02 == null) goto L13;
        r02.hide(true, 0);
    L13:
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

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onRequestPermissionsResultFragment(int r1, String[] r2, int[] r3) {
        if (r1 != 30) goto L6;
        openShareLiveLocation(false, this.askWithRadius);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        AndroidUtilities.requestAdjustResize(getParentActivity(), this.classGuid);
        AndroidUtilities.removeAdjustResize(getParentActivity(), this.classGuid);
        IMapsProvider.IMapView r0 = this.mapView;
        if (r0 != null) goto L5;
    L10:
        this.onResumeCalled = true;
        IMapsProvider.IMap r1 = this.map;
        if (r1 != null) goto L30;
    L16:
        fixLayoutInternal(true);
        if (disablePermissionCheck() == false) goto L20;
        this.checkPermission = false;
    L26:
        Runnable r02 = this.markAsReadRunnable;
        if (r02 == null) goto L34;
        AndroidUtilities.cancelRunOnUIThread(r02);
        AndroidUtilities.runOnUIThread(this.markAsReadRunnable, 5000);
        return;
    L34:
        return;
    L20:
        if (this.checkPermission == false) goto L26;
        Activity r03 = getParentActivity();
        if (r03 == null) goto L26;
        this.checkPermission = false;
        if (r03.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0) goto L26;
        r03.requestPermissions(new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}, 2);
        goto L26
    L30:
        r1.setMyLocationEnabled(true);     // Catch: Exception -> L14
    L14:
        e = move-exception;
        FileLog.e(e);
        goto L16
    L5:
        if (this.mapsInitialized == false) goto L10;
        r0.onResume();     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        FileLog.e(th);
        goto L10
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r7, boolean r8) {
        if (r7 == false) goto L26;
        if (r8 == true) goto L27;
        if ((this.mapView.getView().getParent() instanceof ViewGroup) == false) goto L7;
        ((ViewGroup) this.mapView.getView().getParent()).removeView(this.mapView.getView());     // Catch: Exception -> L20
    L7:
        FrameLayout r72 = this.mapViewClip;
        if (r72 == null) goto L16;
        r72.addView(this.mapView.getView(), 0, LayoutHelper.createFrame(-1, this.overScrollHeight + AndroidUtilities.dp(10.0f), 51));
        MapOverlayView r73 = this.overlayView;
        if (r73 != null) goto L22;
    L15:
        updateClipView(false);
        maybeShowProximityHint();
        return;
    L22:
        if ((r73.getParent() instanceof ViewGroup) == false) goto L14;
        ((ViewGroup) this.overlayView.getParent()).removeView(this.overlayView);     // Catch: Exception -> L21
    L14:
        this.mapViewClip.addView(this.overlayView, 1, LayoutHelper.createFrame(-1, this.overScrollHeight + AndroidUtilities.dp(10.0f), 51));
        goto L15
    L16:
        View r74 = this.fragmentView;
        if (r74 == null) goto L29;
        ((FrameLayout) r74).addView(this.mapView.getView(), 0, LayoutHelper.createFrame(-1, -1, 51));
        return;
    L29:
        return;
    L27:
        return;
    }

    public LocationActivity searchStories(TL_stories.MediaArea r1) {
        this.searchStoriesArea = r1;
        return this;
    }

    public void setChatLocation(long r1, TLRPC.TL_channelLocation r3) {
        this.dialogId = -r1;
        this.chatLocation = r3;
    }

    public void setDelegate(LocationActivityDelegate r1) {
        this.delegate = r1;
    }

    public void setDialogId(long r1) {
        this.dialogId = r1;
    }

    public void setInitialLocation(TLRPC.TL_channelLocation r1) {
        this.initialLocation = r1;
    }

    public void setInitialMaxZoom(boolean r1) {
        this.initialMaxZoom = r1;
    }

    public void setMessageObject(MessageObject r3) {
        this.messageObject = r3;
        this.dialogId = r3.getDialogId();
    }

    public void setSharingAllowed(boolean r1) {
        this.isSharingAllowed = r1;
    }
}
