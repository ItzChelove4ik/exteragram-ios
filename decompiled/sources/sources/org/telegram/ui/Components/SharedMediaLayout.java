package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.util.Pair;
import android.util.Property;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.collection.LongSparseArray;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.backup.BackupBottomSheet;
import com.exteragram.messenger.backup.PreferencesUtils;
import com.exteragram.messenger.icons.IconManager;
import com.exteragram.messenger.pillstack.ui.pills.crypto.RatePill$$ExternalSyntheticLambda1;
import com.exteragram.messenger.plugins.PluginsController;
import com.exteragram.messenger.utils.AppUtils;
import com.exteragram.messenger.utils.text.LocaleUtils;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.Opcodes;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatMessageSharedResources;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SavedMessagesController;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.messenger.utils.tlutils.TlUtils;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_bots;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Adapters.SearchAdapterHelper;
import org.telegram.ui.CalendarActivity;
import org.telegram.ui.Cells.ChatActionCell;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Cells.CheckBoxCell;
import org.telegram.ui.Cells.ContextLinkCell;
import org.telegram.ui.Cells.DialogCell;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.LoadingCell;
import org.telegram.ui.Cells.ManageChatUserCell;
import org.telegram.ui.Cells.ProfileSearchCell;
import org.telegram.ui.Cells.SharedAudioCell;
import org.telegram.ui.Cells.SharedDocumentCell;
import org.telegram.ui.Cells.SharedLinkCell;
import org.telegram.ui.Cells.SharedMediaSectionCell;
import org.telegram.ui.Cells.SharedPhotoVideoCell;
import org.telegram.ui.Cells.SharedPhotoVideoCell2;
import org.telegram.ui.Cells.UserCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.ChatActivityContainer;
import org.telegram.ui.Components.Forum.ForumUtilities;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScrollSlidingTextTabStrip;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.ViewGroupPartRenderer;
import org.telegram.ui.Components.blur3.capture.IBlur3Capture;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;
import org.telegram.ui.Components.spoilers.SpoilersTextView;
import org.telegram.ui.ContentPreviewViewer;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.Gifts.ProfileGiftsContainer;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.LocationActivity;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.PremiumPreviewFragment;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.ProfileStoriesCollectionTabs;
import org.telegram.ui.SelectStoriesBottomSheet;
import org.telegram.ui.Stars.StarsController;
import org.telegram.ui.Stories.StoriesController;
import org.telegram.ui.Stories.StoriesListPlaceProvider;
import org.telegram.ui.Stories.StoryViewer;
import org.telegram.ui.Stories.UserListPoller;
import org.telegram.ui.Stories.ViewsForPeerStoriesRequester;
import org.telegram.ui.Stories.bots.BotPreviewsEditContainer;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.Stories.recorder.PreviewView;
import org.telegram.ui.Stories.recorder.StoryRecorder;
import org.telegram.ui.ThemeActivity;
import org.telegram.ui.TopicsFragment;

/* loaded from: classes7.dex */
public abstract class SharedMediaLayout extends FrameLayout implements NotificationCenter.NotificationCenterDelegate, DialogCell.DialogCellDelegate {
    private static final Interpolator interpolator = null;
    private static final int[] supportedFastScrollTypes = null;
    private ActionBar actionBar;
    private AnimatorSet actionModeAnimation;
    private LinearLayout actionModeLayout;
    private ArrayList<View> actionModeViews;
    private SpannableStringBuilder addPostButton;
    private float additionalFloatingTranslation;
    private boolean allowStoriesSingleColumn;
    private int animateToColumnsCount;
    private boolean animatingForward;
    private boolean animatingToOptions;
    private StoriesAdapter animationSupportingArchivedStoriesAdapter;
    private SharedPhotoVideoAdapter animationSupportingPhotoVideoAdapter;
    private StoriesAdapter animationSupportingStoriesAdapter;
    private StoriesAdapter archivedStoriesAdapter;
    private SharedDocumentsAdapter audioAdapter;
    private ArrayList<SharedAudioCell> audioCache;
    private ArrayList<SharedAudioCell> audioCellCache;
    private MediaSearchAdapter audioSearchAdapter;
    private boolean backAnimation;
    private BackDrawable backDrawable;
    private Paint backgroundPaint;
    private BotPreviewsEditContainer botPreviewsContainer;
    private ArrayList<SharedPhotoVideoCell> cache;
    private int cantDeleteMessagesCount;
    private ArrayList<SharedPhotoVideoCell> cellCache;
    private int changeColumnsTab;
    private boolean changeTypeAnimation;
    private ChannelRecommendationsAdapter channelRecommendationsAdapter;
    private ChatUsersAdapter chatUsersAdapter;
    private ImageView closeButton;
    private CommonGroupsAdapter commonGroupsAdapter;
    final Delegate delegate;
    private ActionBarMenuItem deleteItem;
    private long dialog_id;
    private boolean disableScrolling;
    private SharedDocumentsAdapter documentsAdapter;
    private MediaSearchAdapter documentsSearchAdapter;
    private int firstTab;
    private AnimatorSet floatingDateAnimation;
    private ChatActionCell floatingDateView;
    private ActionBarMenuItem forwardItem;
    private FragmentContextView fragmentContextView;
    private FrameLayout fragmentContextViewWrapper;
    private HintView fwdRestrictedHint;
    private GifAdapter gifAdapter;
    public ProfileGiftsContainer giftsContainer;
    private long giftsLastHash;
    FlickerLoadingView globalGradientView;
    private ActionBarMenuItem gotoItem;
    private GroupUsersSearchAdapter groupUsersSearchAdapter;
    private int[] hasMedia;
    private Runnable hideFloatingDateRunnable;
    public final IBlur3Capture iBlur3Capture;
    private final BlurredBackgroundSourceColor iBlur3SourceColor;
    private boolean ignoreSearchCollapse;
    private TLRPC.ChatFull info;
    private int initialTab;
    protected boolean isActionModeShowed;
    boolean isInPinchToZoomTouchMode;
    boolean isPinnedToTop;
    Runnable jumpToRunnable;
    private int lastMainTabId;
    int lastMeasuredTopPadding;
    private int lastVisibleHeight;
    private SharedLinksAdapter linksAdapter;
    private MediaSearchAdapter linksSearchAdapter;
    private int maximumVelocity;
    boolean maybePinchToZoomTouchMode;
    boolean maybePinchToZoomTouchMode2;
    private boolean maybeStartTracking;
    private int[] mediaColumnsCount;
    private MediaPage[] mediaPages;
    private long mergeDialogId;
    SparseArray<Float> messageAlphaEnter;
    AnimationNotificationsLocker notificationsLocker;
    private final NotificationCenter.ObserversGroup observersGroup;
    private float optionsAlpha;
    private RLottieImageView optionsSearchImageView;
    private int pagesPaddingBottom;
    private SharedPhotoVideoAdapter photoVideoAdapter;
    private boolean photoVideoChangeColumnsAnimation;
    private float photoVideoChangeColumnsProgress;
    public ImageView photoVideoOptionsItem;
    private ActionBarMenuItem pinItem;
    int pinchCenterOffset;
    int pinchCenterPosition;
    int pinchCenterX;
    int pinchCenterY;
    float pinchScale;
    boolean pinchScaleUp;
    float pinchStartDistance;
    private Drawable pinnedHeaderShadowDrawable;
    private int pointerId1;
    private int pointerId2;
    private PollAdapter pollAdapter;
    private BaseFragment profileActivity;
    private PhotoViewer.PhotoViewerProvider provider;
    Rect rect;
    private Theme.ResourcesProvider resourcesProvider;
    public TextView saveItem;
    private SavedDialogsAdapter savedDialogsAdapter;
    private ChatActivityContainer savedMessagesContainer;
    private SavedMessagesSearchAdapter savedMessagesSearchAdapter;
    public ScrollSlidingTextTabStripInner scrollSlidingTextTabStrip;
    private boolean scrolling;
    public boolean scrollingByUser;
    private float searchAlpha;
    private ActionBarMenuItem searchItem;
    public ActionBarMenuItem searchItemIcon;
    private int searchItemState;
    public StoriesController.StoriesList searchStoriesList;
    public SearchTagsList searchTagsList;
    private boolean searchWas;
    private boolean searching;
    private ReactionsLayoutInBubble.VisibleReaction searchingReaction;
    private SparseArray<MessageObject>[] selectedFiles;
    private NumberTextView selectedMessagesCountTextView;
    SharedLinkCell.SharedLinkCellDelegate sharedLinkCellDelegate;
    private SharedMediaData[] sharedMediaData;
    private SharedMediaPreloader sharedMediaPreloader;
    private float shiftDp;
    private boolean startedTracking;
    private int startedTrackingPointerId;
    private int startedTrackingX;
    private int startedTrackingY;
    private StoriesAdapter storiesAdapter;
    private boolean storiesColumnsCountSet;
    public ProfileStoriesCollectionTabs storiesContainer;
    private ItemTouchHelper storiesReorder;
    private final HashMap<Integer, StoryAlbumData> storyAlbumsById;
    private final HashMap<Integer, Integer> storyAlbumsByTabType;
    private float subTabsVisibilityFactor;
    private int tabIndexCounter;
    private AnimatorSet tabsAnimation;
    private boolean tabsAnimationInProgress;
    private int topLayoutPadding;
    int topPadding;
    private DialogsActivityTopPanelLayout topPanelLayout;
    private long topicId;
    private ActionBarMenuItem unpinItem;
    private TLRPC.UserFull userInfo;
    private VelocityTracker velocityTracker;
    private final int viewType;
    private SharedDocumentsAdapter voiceAdapter;
    private boolean wasReordering;

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends PhotoViewer.EmptyPhotoViewerProvider {
        final /* synthetic */ SharedMediaLayout this$0;

        public AnonymousClass1(SharedMediaLayout r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public PhotoViewer.PlaceProviderObject getPlaceForPhoto(MessageObject r17, TLRPC.FileLocation r18, int r19, boolean r20, boolean r21) {
            if (r17 != null) goto L5;
        L98:
            return null;
        L5:
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0].selectedType != 0) goto L7;
        L13:
            InternalListView r2 = MediaPage.m12961$$Nest$fgetlistView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0]);
            int r5 = r2.getChildCount();
            int r7 = 0;
            int r8 = -1;
            int r9 = -1;
        L14:
            if (r7 >= r5) goto L89;
            View r10 = r2.getChildAt(r7);
            int r11 = MediaPage.m12961$$Nest$fgetlistView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0]).getMeasuredHeight();
            View r12 = (View) this.this$0.getParent();
            if (r12 == null) goto L21;
            if ((this.this$0.getY() + this.this$0.getMeasuredHeight()) <= r12.getMeasuredHeight()) goto L21;
            r11 = r11 - (this.this$0.getBottom() - r12.getMeasuredHeight());
        L21:
            if (r10.getTop() >= r11) goto L87;
            int r112 = r2.getChildAdapterPosition(r10);
            if (r112 < r8) goto L26;
            if (r8 == (-1)) goto L26;
        L27:
            if (r112 > r9) goto L29;
            if (r9 == (-1)) goto L29;
        L30:
            int[] r113 = new int[2];
            if ((r10 instanceof SharedPhotoVideoCell2) == false) goto L39;
            SharedPhotoVideoCell2 r122 = (SharedPhotoVideoCell2) r10;
            MessageObject r13 = r122.getMessageObject();
            if (r13 == null) goto L87;
            if (r13.getId() != r17.getId()) goto L57;
            ImageReceiver r132 = r122.imageReceiver;
            r122.getLocationInWindow(r113);
            r113[0] = r113[0] + Math.round(r122.imageReceiver.getImageX());
            r113[1] = r113[1] + Math.round(r122.imageReceiver.getImageY());
        L58:
            if (r132 == null) goto L87;
            PhotoViewer.PlaceProviderObject r1 = new PhotoViewer.PlaceProviderObject();
            r1.viewX = r113[0];
            r1.viewY = r113[1];
            r1.parentView = r2;
            r1.animatingImageView = MediaPage.m12954$$Nest$fgetanimatingImageView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0]);
            MediaPage.m12961$$Nest$fgetlistView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0]).getLocationInWindow(r113);
            r1.animatingImageViewYOffset = -r113[1];
            r1.imageReceiver = r132;
            r1.allowTakeAnimation = true;
            r1.radius = r132.getRoundRadius(true);
            r1.thumb = r1.imageReceiver.getBitmapSafe();
            r1.parentView.getLocationInWindow(r113);
            r1.clipTopAddition = 0;
            r1.starOffset = SharedMediaData.m12992$$Nest$fgetstartOffset(SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0]);
            if (SharedMediaLayout.m12816$$Nest$fgetfragmentContextView(this.this$0) == null) goto L65;
            if (SharedMediaLayout.m12816$$Nest$fgetfragmentContextView(this.this$0).getVisibility() != 0) goto L65;
            r1.clipTopAddition += AndroidUtilities.dp(36.0f);
        L65:
            if (PhotoViewer.isShowingImage(r17) == false) goto L86;
            View r4 = r2.getPinnedHeader();
            if (r4 == null) goto L86;
            if (SharedMediaLayout.m12816$$Nest$fgetfragmentContextView(this.this$0) != null) goto L71;
        L73:
            int r0 = 0;
        L74:
            boolean r52 = r10 instanceof SharedDocumentCell;
            if (r52 == false) goto L77;
            r0 = r0 + AndroidUtilities.dp(8.0f);
        L77:
            int r02 = r0 - r1.viewY;
            if (r02 <= r10.getHeight()) goto L81;
            r2.scrollBy(0, -(r02 + r4.getHeight()));
            return r1;
        L81:
            int r03 = r1.viewY - r2.getHeight();
            if (r52 == false) goto L84;
            r03 = r03 - AndroidUtilities.dp(8.0f);
        L84:
            if (r03 < 0) goto L86;
            r2.scrollBy(0, r03 + r10.getHeight());
            goto L86
        L71:
            if (SharedMediaLayout.m12816$$Nest$fgetfragmentContextView(this.this$0).getVisibility() != 0) goto L73;
            r0 = SharedMediaLayout.m12816$$Nest$fgetfragmentContextView(this.this$0).getHeight() - AndroidUtilities.dp(2.5f);
        L86:
            return r1;
        L57:
            r132 = null;
            goto L58
        L39:
            if ((r10 instanceof SharedDocumentCell) == false) goto L44;
            SharedDocumentCell r123 = (SharedDocumentCell) r10;
            if (r123.getMessage().getId() != r17.getId()) goto L57;
            BackupImageView r124 = r123.getImageView();
            r132 = r124.getImageReceiver();
            r124.getLocationInWindow(r113);
            goto L58
        L44:
            if ((r10 instanceof ContextLinkCell) == false) goto L51;
            ContextLinkCell r125 = (ContextLinkCell) r10;
            MessageObject r133 = (MessageObject) r125.getParentObject();
            if (r133 == null) goto L57;
            if (r133.getId() != r17.getId()) goto L57;
            r132 = r125.getPhotoImage();
            r125.getLocationInWindow(r113);
            goto L58
        L51:
            if ((r10 instanceof SharedLinkCell) == false) goto L57;
            SharedLinkCell r126 = (SharedLinkCell) r10;
            MessageObject r134 = r126.getMessage();
            if (r134 == null) goto L57;
            if (r134.getId() != r17.getId()) goto L57;
            r132 = r126.getLinkImageView();
            r126.getLocationInWindow(r113);
        L29:
            r9 = r112;
        L26:
            r8 = r112;
        L87:
            r7 = r7 + 1;
            goto L14
        L89:
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0].selectedType != 0) goto L98;
            if (r8 < 0) goto L98;
            if (r9 < 0) goto L98;
            int r22 = SharedMediaLayout.m12829$$Nest$fgetphotoVideoAdapter(this.this$0).getPositionForIndex(r19);
            if (r22 > r8) goto L95;
            MediaPage.m12960$$Nest$fgetlayoutManager(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0]).scrollToPositionWithOffset(r22, 0);
            this.this$0.delegate.scrollToSharedMedia();
            goto L98
        L95:
            if (r22 < r9) goto L98;
            if (r9 < 0) goto L98;
            MediaPage.m12960$$Nest$fgetlayoutManager(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0]).scrollToPositionWithOffset(r22, 0, true);
            this.this$0.delegate.scrollToSharedMedia();
            goto L98
        L7:
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0].selectedType == 1) goto L13;
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0].selectedType == 3) goto L13;
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0].selectedType == 5) goto L13;
            goto L13
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$10, reason: invalid class name */
    public class AnonymousClass10 extends ItemTouchHelper.Callback {
        private RecyclerListView listView;
        final /* synthetic */ SharedMediaLayout this$0;

        public AnonymousClass10(SharedMediaLayout r1) {
            this.this$0 = r1;
        }

        private StoriesAdapter getAdapter(RecyclerView r1) {
            RecyclerView.Adapter r0 = r1.getAdapter();
            if ((r0 instanceof StoriesAdapter) == true) goto L5;
            return null;
        L5:
            return (StoriesAdapter) r0;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void clearView(RecyclerView r1, RecyclerView.ViewHolder r2) {
            super.clearView(r1, r2);
            r2.itemView.setPressed(false);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public int getMovementFlags(RecyclerView r3, RecyclerView.ViewHolder r4) {
            StoriesAdapter r32 = getAdapter(r3);
            if (isLongPressDragEnabled() == false) goto L17;
            if (r32 == null) goto L17;
            if (r32.canReorder(r4.getAdapterPosition()) == false) goto L17;
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0] != null) goto L10;
            InternalListView r33 = null;
        L11:
            this.listView = r33;
            if (r33 == null) goto L15;
            r33.setItemAnimator(MediaPage.m12959$$Nest$fgetitemAnimator(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0]));
        L15:
            return ItemTouchHelper.Callback.makeMovementFlags(15, 0);
        L10:
            r33 = MediaPage.m12961$$Nest$fgetlistView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0]);
        L17:
            return ItemTouchHelper.Callback.makeMovementFlags(0, 0);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean isLongPressDragEnabled() {
            SharedMediaLayout r1 = this.this$0;
            if (r1.isActionModeShowed == true) goto L11;
            ProfileStoriesCollectionTabs r12 = r1.storiesContainer;
            if (r12 != null) goto L7;
            return false;
        L7:
            if (r12.isReordering() == true) goto L14;
            return false;
        L14:
            return true;
        L11:
            return true;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean onMove(RecyclerView r1, RecyclerView.ViewHolder r2, RecyclerView.ViewHolder r3) {
            StoriesAdapter r0 = getAdapter(r1);
            if (r0 != null) goto L5;
            return false;
        L5:
            if (r0.canReorder(r2.getAdapterPosition()) == true) goto L7;
            return false;
        L7:
            if (r0.canReorder(r3.getAdapterPosition()) == false) goto L14;
            r0.swapElements(r2.getAdapterPosition(), r3.getAdapterPosition());
            return true;
        L14:
            return false;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSelectedChanged(RecyclerView.ViewHolder r3, int r4) {
            RecyclerListView r0 = this.listView;
            if (r0 == null) goto L6;
            if (r3 == null) goto L6;
            r0.hideSelector(false);
        L6:
            RecyclerListView r02 = this.listView;
            if (r4 != 0) goto L15;
            if (r02 != null) goto L10;
        L12:
            RecyclerListView r03 = this.listView;
            if (r03 == null) goto L19;
            r03.setItemAnimator(null);
        L19:
            super.onSelectedChanged(r3, r4);
            return;
        L10:
            if ((r02.getAdapter() instanceof StoriesAdapter) == false) goto L12;
            ((StoriesAdapter) this.listView.getAdapter()).reorderDone();
            goto L12
        L15:
            if (r02 == null) goto L17;
            r02.cancelClickRunnables(false);
        L17:
            if (r3 == null) goto L19;
            r3.itemView.setPressed(true);
            goto L19
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSwiped(RecyclerView.ViewHolder r1, int r2) {
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$11, reason: invalid class name */
    public class AnonymousClass11 extends StoriesAdapter {
        final /* synthetic */ SharedMediaLayout this$0;

        public AnonymousClass11(SharedMediaLayout r1, Context r2, boolean r3) {
            this.this$0 = r1;
            super(r1, r2, r3);
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.StoriesAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            MediaPage r0 = SharedMediaLayout.m12893$$Nest$mgetMediaPage(this.this$0, 9);
            if (r0 != null) goto L5;
        L7:
            if (r0 == null) goto L20;
            StickerEmptyView r02 = MediaPage.m12957$$Nest$fgetemptyView(r0);
            StoriesController.StoriesList r1 = this.storiesList;
            if (r1 != null) goto L11;
        L17:
            boolean r2 = false;
        L18:
            r02.showProgress(r2);
            return;
        L11:
            if (r1.isLoading() == false) goto L13;
        L16:
            r2 = true;
            goto L18
        L13:
            if (this.this$0.hasInternet() == false) goto L17;
            if (this.storiesList.getCount() <= 0) goto L17;
        L20:
            return;
        L5:
            if (MediaPage.m12956$$Nest$fgetanimationSupportingListView(r0).getVisibility() != 0) goto L7;
            SharedMediaLayout.m12794$$Nest$fgetanimationSupportingArchivedStoriesAdapter(this.this$0).notifyDataSetChanged();
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$12, reason: invalid class name */
    public class AnonymousClass12 extends BotPreviewsEditContainer {
        final /* synthetic */ SharedMediaLayout this$0;

        public static /* synthetic */ void $r8$lambda$FhHw8UIem0P_qtOpYXJq6jXBD6g(AnonymousClass12 r0) {
            r0.lambda$unselect$0();
        }

        public AnonymousClass12(SharedMediaLayout r1, Context r2, BaseFragment r3, long r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        private /* synthetic */ void lambda$unselect$0() {
            SharedMediaLayout r1 = this.this$0;
            if (r1.isActionModeShowed == false) goto L6;
            r1.showActionMode(false);
            return;
        }

        @Override // org.telegram.ui.Stories.bots.BotPreviewsEditContainer
        public int getStartedTrackingX() {
            return SharedMediaLayout.m12848$$Nest$fgetstartedTrackingX(this.this$0);
        }

        @Override // org.telegram.ui.Stories.bots.BotPreviewsEditContainer
        public boolean isActionModeShowed() {
            return this.this$0.isActionModeShowed;
        }

        @Override // org.telegram.ui.Stories.bots.BotPreviewsEditContainer
        public boolean isSelected(MessageObject r6) {
            SparseArray[] r0 = SharedMediaLayout.m12844$$Nest$fgetselectedFiles(this.this$0);
            if (r6.getDialogId() != SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0)) goto L5;
            char r5 = 0;
        L7:
            if (r0[r5].indexOfKey(r6.getId()) < 0) goto L9;
            return true;
        L9:
            return false;
        L5:
            r5 = 1;
            goto L7
        }

        @Override // org.telegram.ui.Stories.bots.BotPreviewsEditContainer
        public void onSelectedTabChanged() {
            this.this$0.onSelectedTabChanged();
        }

        @Override // org.telegram.ui.Stories.bots.BotPreviewsEditContainer
        public boolean select(MessageObject r8) {
            if (r8 != null) goto L6;
            return false;
        L6:
            if (r8.getDialogId() != SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0)) goto L8;
            char r1 = 0;
        L10:
            if (SharedMediaLayout.m12844$$Nest$fgetselectedFiles(this.this$0)[r1].indexOfKey(r8.getId()) < 0) goto L12;
            return false;
        L12:
            if ((SharedMediaLayout.m12844$$Nest$fgetselectedFiles(this.this$0)[0].size() + SharedMediaLayout.m12844$$Nest$fgetselectedFiles(this.this$0)[1].size()) < 100) goto L14;
            return false;
        L14:
            SharedMediaLayout.m12844$$Nest$fgetselectedFiles(this.this$0)[r1].put(r8.getId(), r8);
            if (r8.canDeleteMessage(false, null) == true) goto L17;
            SharedMediaLayout r82 = this.this$0;
            SharedMediaLayout.m12861$$Nest$fputcantDeleteMessagesCount(r82, SharedMediaLayout.m12805$$Nest$fgetcantDeleteMessagesCount(r82) + 1);
        L17:
            SharedMediaLayout r83 = this.this$0;
            if (r83.isActionModeShowed == true) goto L51;
            AndroidUtilities.hideKeyboard(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(r83).getParentActivity().getCurrentFocus());
            ActionBarMenuItem r84 = SharedMediaLayout.m12810$$Nest$fgetdeleteItem(this.this$0);
            int r3 = 8;
            if (SharedMediaLayout.m12805$$Nest$fgetcantDeleteMessagesCount(this.this$0) != 0) goto L22;
            int r12 = 0;
        L23:
            r84.setVisibility(r12);
            if (SharedMediaLayout.m12819$$Nest$fgetgotoItem(this.this$0) == null) goto L33;
            ActionBarMenuItem r85 = SharedMediaLayout.m12819$$Nest$fgetgotoItem(this.this$0);
            if (this.this$0.getClosestTab() != 8) goto L28;
        L30:
            int r4 = 8;
        L31:
            r85.setVisibility(r4);
            goto L33
        L28:
            if (this.this$0.getClosestTab() == 13) goto L30;
            r4 = 0;
        L33:
            if (SharedMediaLayout.m12832$$Nest$fgetpinItem(this.this$0) == null) goto L36;
            SharedMediaLayout.m12832$$Nest$fgetpinItem(this.this$0).setVisibility(8);
        L36:
            if (SharedMediaLayout.m12855$$Nest$fgetunpinItem(this.this$0) == null) goto L39;
            SharedMediaLayout.m12855$$Nest$fgetunpinItem(this.this$0).setVisibility(8);
        L39:
            if (SharedMediaLayout.m12815$$Nest$fgetforwardItem(this.this$0) == null) goto L46;
            ActionBarMenuItem r86 = SharedMediaLayout.m12815$$Nest$fgetforwardItem(this.this$0);
            if (this.this$0.getClosestTab() != 8) goto L43;
        L45:
            r86.setVisibility(r3);
            goto L46
        L43:
            if (this.this$0.getClosestTab() == 13) goto L45;
            r3 = 0;
        L46:
            SharedMediaLayout.m12845$$Nest$fgetselectedMessagesCountTextView(this.this$0).setNumber(SharedMediaLayout.m12844$$Nest$fgetselectedFiles(this.this$0)[0].size() + SharedMediaLayout.m12844$$Nest$fgetselectedFiles(this.this$0)[1].size(), false);
            AnimatorSet r87 = new AnimatorSet();
            ArrayList r13 = new ArrayList();
            int r32 = 0;
        L48:
            if (r32 >= SharedMediaLayout.m12790$$Nest$fgetactionModeViews(this.this$0).size()) goto L50;
            View r42 = (View) SharedMediaLayout.m12790$$Nest$fgetactionModeViews(this.this$0).get(r32);
            AndroidUtilities.clearDrawableAnimation(r42);
            r13.add(ObjectAnimator.ofFloat(r42, View.SCALE_Y, new float[]{0.1f, 1.0f}));
            r32 = r32 + 1;
            goto L48
        L50:
            r87.playTogether(r13);
            r87.setDuration(250);
            r87.start();
            SharedMediaLayout.m12870$$Nest$fputscrolling(this.this$0, false);
            this.this$0.showActionMode(true);
        L52:
            updateSelection(true);
            return true;
        L22:
            r12 = 8;
            goto L23
        L51:
            SharedMediaLayout.m12845$$Nest$fgetselectedMessagesCountTextView(r83).setNumber(SharedMediaLayout.m12844$$Nest$fgetselectedFiles(this.this$0)[0].size() + SharedMediaLayout.m12844$$Nest$fgetselectedFiles(this.this$0)[1].size(), true);
            goto L52
        L8:
            r1 = 1;
            goto L10
        }

        @Override // org.telegram.ui.Stories.bots.BotPreviewsEditContainer
        public boolean unselect(MessageObject r8) {
            if (r8 != null) goto L6;
            return false;
        L6:
            if (r8.getDialogId() != SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0)) goto L8;
            char r1 = 0;
        L10:
            if (SharedMediaLayout.m12844$$Nest$fgetselectedFiles(this.this$0)[r1].indexOfKey(r8.getId()) < 0) goto L53;
            SharedMediaLayout.m12844$$Nest$fgetselectedFiles(this.this$0)[r1].remove(r8.getId());
            if (r8.canDeleteMessage(false, null) == true) goto L15;
            SharedMediaLayout r82 = this.this$0;
            SharedMediaLayout.m12861$$Nest$fputcantDeleteMessagesCount(r82, SharedMediaLayout.m12805$$Nest$fgetcantDeleteMessagesCount(r82) - 1);
        L15:
            if (SharedMediaLayout.m12844$$Nest$fgetselectedFiles(this.this$0)[0].size() == 0) goto L17;
        L50:
            SharedMediaLayout.m12845$$Nest$fgetselectedMessagesCountTextView(this.this$0).setNumber(SharedMediaLayout.m12844$$Nest$fgetselectedFiles(this.this$0)[0].size() + SharedMediaLayout.m12844$$Nest$fgetselectedFiles(this.this$0)[1].size(), true);
        L51:
            updateSelection(true);
            return true;
        L17:
            if (SharedMediaLayout.m12844$$Nest$fgetselectedFiles(this.this$0)[1].size() != 0) goto L50;
            AndroidUtilities.hideKeyboard(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getParentActivity().getCurrentFocus());
            SharedMediaLayout.m12844$$Nest$fgetselectedFiles(this.this$0)[0].clear();
            SharedMediaLayout.m12844$$Nest$fgetselectedFiles(this.this$0)[1].clear();
            ActionBarMenuItem r83 = SharedMediaLayout.m12810$$Nest$fgetdeleteItem(this.this$0);
            int r3 = 8;
            if (SharedMediaLayout.m12805$$Nest$fgetcantDeleteMessagesCount(this.this$0) != 0) goto L21;
            int r12 = 0;
        L22:
            r83.setVisibility(r12);
            if (SharedMediaLayout.m12819$$Nest$fgetgotoItem(this.this$0) == null) goto L32;
            ActionBarMenuItem r84 = SharedMediaLayout.m12819$$Nest$fgetgotoItem(this.this$0);
            if (this.this$0.getClosestTab() != 8) goto L27;
        L29:
            int r4 = 8;
        L30:
            r84.setVisibility(r4);
            goto L32
        L27:
            if (this.this$0.getClosestTab() == 13) goto L29;
            r4 = 0;
        L32:
            if (SharedMediaLayout.m12832$$Nest$fgetpinItem(this.this$0) == null) goto L35;
            SharedMediaLayout.m12832$$Nest$fgetpinItem(this.this$0).setVisibility(8);
        L35:
            if (SharedMediaLayout.m12855$$Nest$fgetunpinItem(this.this$0) == null) goto L38;
            SharedMediaLayout.m12855$$Nest$fgetunpinItem(this.this$0).setVisibility(8);
        L38:
            if (SharedMediaLayout.m12815$$Nest$fgetforwardItem(this.this$0) == null) goto L45;
            ActionBarMenuItem r85 = SharedMediaLayout.m12815$$Nest$fgetforwardItem(this.this$0);
            if (this.this$0.getClosestTab() != 8) goto L42;
        L44:
            r85.setVisibility(r3);
            goto L45
        L42:
            if (this.this$0.getClosestTab() == 13) goto L44;
            r3 = 0;
        L45:
            AnimatorSet r86 = new AnimatorSet();
            ArrayList r13 = new ArrayList();
            int r32 = 0;
        L47:
            if (r32 >= SharedMediaLayout.m12790$$Nest$fgetactionModeViews(this.this$0).size()) goto L49;
            View r42 = (View) SharedMediaLayout.m12790$$Nest$fgetactionModeViews(this.this$0).get(r32);
            AndroidUtilities.clearDrawableAnimation(r42);
            r13.add(ObjectAnimator.ofFloat(r42, View.SCALE_Y, new float[]{1.0f, 0.1f}));
            r32 = r32 + 1;
            goto L47
        L49:
            r86.playTogether(r13);
            r86.setDuration(250);
            r86.start();
            SharedMediaLayout.m12870$$Nest$fputscrolling(this.this$0, false);
            AndroidUtilities.runOnUIThread(new SharedMediaLayout$12$$ExternalSyntheticLambda0(this), 20);
            goto L51
        L21:
            r12 = 8;
            goto L22
        L53:
            return false;
        L8:
            r1 = 1;
            goto L10
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$13, reason: invalid class name */
    public class AnonymousClass13 extends ProfileGiftsContainer {
        final /* synthetic */ SharedMediaLayout this$0;

        public static /* synthetic */ void $r8$lambda$P3OAHXagfaZ4ijuXQJnAlEJCZps(AnonymousClass13 r0, boolean r1) {
            r0.lambda$updatedReordering$0(r1);
        }

        public AnonymousClass13(SharedMediaLayout r1, BaseFragment r2, Context r3, int r4, long r5, Theme.ResourcesProvider r7) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r7);
        }

        private /* synthetic */ void lambda$updatedReordering$0(boolean r1) {
            if (r1 == true) goto L5;
            this.this$0.saveItem.setVisibility(0);
            return;
        }

        @Override // org.telegram.ui.Gifts.ProfileGiftsContainer
        public int processColor(int r1) {
            return this.this$0.processColor(r1);
        }

        @Override // org.telegram.ui.Gifts.ProfileGiftsContainer
        public void updatedReordering(boolean r5) {
            this.this$0.saveItem.setVisibility(0);
            ViewPropertyAnimator r0 = this.this$0.saveItem.animate();
            float r1 = 1.0f;
            if (r5 == false) goto L5;
            float r2 = 1.0f;
        L6:
            ViewPropertyAnimator r02 = r0.alpha(r2);
            if (r5 == false) goto L9;
            float r3 = 1.0f;
        L10:
            ViewPropertyAnimator r03 = r02.scaleX(r3);
            if (r5 == true) goto L14;
            r1 = 0.4f;
        L14:
            r03.scaleY(r1).withEndAction(new SharedMediaLayout$13$$ExternalSyntheticLambda0(this, r5)).start();
            SharedMediaLayout.m12920$$Nest$mupdateOptionsSearch(this.this$0, true);
            return;
        L9:
            r3 = 0.4f;
            goto L10
        L5:
            r2 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$14, reason: invalid class name */
    public class AnonymousClass14 implements ProfileStoriesCollectionTabs.Delegate {
        final /* synthetic */ SharedMediaLayout this$0;
        final /* synthetic */ Context val$context;
        final /* synthetic */ BaseFragment val$parent;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        /* renamed from: org.telegram.ui.Components.SharedMediaLayout$14$1, reason: invalid class name */
        public class AnonymousClass1 extends Drawable {
            private final Drawable bg;
            private final Rect bgBounds;
            final /* synthetic */ AnonymousClass14 this$1;

            public AnonymousClass1(AnonymousClass14 r5) {
                this.this$1 = r5;
                this.bg = Theme.createRoundRectDrawable(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), Theme.blendOver(Theme.getColor(Theme.key_windowBackgroundWhite, r5.val$resourcesProvider), Theme.multAlpha(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r5.val$resourcesProvider), 0.04f)));
                this.bgBounds = new Rect();
            }

            @Override // android.graphics.drawable.Drawable
            public void draw(Canvas r4) {
                this.bgBounds.set(getBounds());
                this.bgBounds.inset(AndroidUtilities.dp(2.0f), AndroidUtilities.dp(8.0f));
                this.bg.setBounds(this.bgBounds);
                this.bg.draw(r4);
            }

            @Override // android.graphics.drawable.Drawable
            public int getOpacity() {
                return -2;
            }

            @Override // android.graphics.drawable.Drawable
            public void setAlpha(int r1) {
                this.bg.setAlpha(r1);
            }

            @Override // android.graphics.drawable.Drawable
            public void setColorFilter(ColorFilter r1) {
            }
        }

        /* renamed from: $r8$lambda$FnAE5Ah3g-lWXaDjNmNTcV8end4, reason: not valid java name */
        public static /* synthetic */ void m12924$r8$lambda$FnAE5Ah3glWXaDjNmNTcV8end4(AnonymousClass14 r0, int r1) {
            r0.lambda$onTabAlbumLongClick$4(r1);
        }

        /* renamed from: $r8$lambda$HBSRFha-uAiZ0cD7WiroJDnetY4, reason: not valid java name */
        public static /* synthetic */ void m12925$r8$lambda$HBSRFhauAiZ0cD7WiroJDnetY4(AnonymousClass14 r0, int r1) {
            r0.lambda$onTabAlbumLongClick$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$jrAISwrUusydTPifxtaPmXlY04E(AnonymousClass14 r0, int r1) {
            r0.lambda$onTabAlbumLongClick$3(r1);
        }

        public static /* synthetic */ void $r8$lambda$ndOqPO1cdw_xeHhRdrooNw7qUQk(SharedMediaLayout r0, StoriesController.StoryAlbum r1) {
            SharedMediaLayout.m12902$$Nest$monStoryAlbumCreate(r0, r1);
        }

        public static /* synthetic */ void $r8$lambda$qnmj3OESqZ4CbjZZBThLw9nA6l0(AnonymousClass14 r0, int r1) {
            r0.lambda$onTabAlbumLongClick$2(r1);
        }

        /* renamed from: $r8$lambda$s5TZDzoP2h-3bQ-8Z7Ewuj4zzAs, reason: not valid java name */
        public static /* synthetic */ void m12926$r8$lambda$s5TZDzoP2h3bQ8Z7Ewuj4zzAs(AnonymousClass14 r0, String r1) {
            r0.lambda$onTabAlbumCreateCollection$0(r1);
        }

        public AnonymousClass14(SharedMediaLayout r1, Context r2, BaseFragment r3, Theme.ResourcesProvider r4) {
            this.this$0 = r1;
            this.val$context = r2;
            this.val$parent = r3;
            this.val$resourcesProvider = r4;
        }

        private /* synthetic */ void lambda$onTabAlbumCreateCollection$0(String r5) {
            SharedMediaLayout.m12896$$Nest$mgetStoriesController(this.this$0).createAlbum(SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0), r5, new SharedMediaLayout$14$$ExternalSyntheticLambda5(this.this$0));
        }

        private /* synthetic */ void lambda$onTabAlbumLongClick$1(int r5) {
            SharedMediaLayout r0 = this.this$0;
            r0.openAddStoriesToAlbumSheet(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(r0), SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0), r5);
        }

        private /* synthetic */ void lambda$onTabAlbumLongClick$2(int r5) {
            SharedMediaLayout r0 = this.this$0;
            r0.openRenameStoriesAlbumAlert(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(r0), SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0), r5);
        }

        private /* synthetic */ void lambda$onTabAlbumLongClick$3(int r1) {
            this.this$0.startAlbumsReorder(r1);
        }

        private /* synthetic */ void lambda$onTabAlbumLongClick$4(int r5) {
            SharedMediaLayout r0 = this.this$0;
            r0.openDeleteStoriesAlbumAlert(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(r0), SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0), r5);
        }

        @Override // org.telegram.ui.ProfileStoriesCollectionTabs.Delegate
        public void onTabAlbumAnimationUpdate(float r1) {
            this.this$0.onPageMediaProgress(r1);
        }

        @Override // org.telegram.ui.ProfileStoriesCollectionTabs.Delegate
        public void onTabAlbumCreateCollection() {
            AlertsCreator.createStoriesAlbumEnterNameForCreate(this.val$context, this.val$parent, this.val$resourcesProvider, new SharedMediaLayout$14$$ExternalSyntheticLambda4(this));
        }

        @Override // org.telegram.ui.ProfileStoriesCollectionTabs.Delegate
        public void onTabAlbumLongClick(View r8, int r9) {
            if (SharedMediaLayout.m12896$$Nest$mgetStoriesController(this.this$0).canEditStoryAlbums(SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0)) == true) goto L5;
            return;
        L5:
            ItemOptions r2 = ItemOptions.makeOptions(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0), r8).setScrimViewBackground(new AnonymousClass1(this));
            r2.add(R.drawable.menu_add_stories, LocaleController.getString(R.string.StoriesAlbumMenuAddStories), new SharedMediaLayout$14$$ExternalSyntheticLambda0(this, r9));
            SharedMediaLayout r1 = this.this$0;
            SharedMediaLayout.m12882$$Nest$maddStoryAlbumShareItemOptions(r1, r2, SharedMediaLayout.m12834$$Nest$fgetprofileActivity(r1), SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0), r9);
            r2.add(R.drawable.msg_edit, LocaleController.getString(R.string.StoriesAlbumMenuEditName), new SharedMediaLayout$14$$ExternalSyntheticLambda1(this, r9));
            r2.add(R.drawable.tabs_reorder, LocaleController.getString(R.string.StoriesAlbumMenuReorder), new SharedMediaLayout$14$$ExternalSyntheticLambda2(this, r9));
            r2.add(R.drawable.msg_delete, LocaleController.getString(R.string.StoriesAlbumMenuDeleteAlbum), true, new SharedMediaLayout$14$$ExternalSyntheticLambda3(this, r9));
            r2.show();
        }

        @Override // org.telegram.ui.ProfileStoriesCollectionTabs.Delegate
        public void onTabAlbumScrollEnd(int r1) {
            this.this$0.onPageMediaProgress(1.0f);
        }

        @Override // org.telegram.ui.ProfileStoriesCollectionTabs.Delegate
        public void onTabAlbumSelected(int r2, boolean r3) {
            SharedMediaLayout r0 = this.this$0;
            if (r2 > 0) goto L6;
            SharedMediaLayout.m12903$$Nest$mopenStoryTabIdPage(r0, 8, r3);
            return;
        L6:
            StoryAlbumData r22 = SharedMediaLayout.m12912$$Nest$mstoryAlbums_getByAlbumId(r0, r2);
            SharedMediaLayout.m12903$$Nest$mopenStoryTabIdPage(this.this$0, r22.tabType, r3);
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$15, reason: invalid class name */
    public class AnonymousClass15 extends ProfileStoriesCollectionTabs {
        final /* synthetic */ SharedMediaLayout this$0;

        /* renamed from: $r8$lambda$p4Qac-H5Atr5_JgbuLsYFqZf3go, reason: not valid java name */
        public static /* synthetic */ void m12927$r8$lambda$p4QacH5Atr5_JgbuLsYFqZf3go(AnonymousClass15 r0, boolean r1) {
            r0.lambda$updatedReordering$0(r1);
        }

        public AnonymousClass15(SharedMediaLayout r1, Context r2, SizeNotifierFrameLayout r3, StoriesController.StoriesCollections r4, ProfileStoriesCollectionTabs.Delegate r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        private /* synthetic */ void lambda$updatedReordering$0(boolean r1) {
            if (r1 == true) goto L5;
            this.this$0.saveItem.setVisibility(0);
            return;
        }

        @Override // org.telegram.ui.ProfileStoriesCollectionTabs
        public void onVisibilityChange(float r10) {
            super.onVisibilityChange(r10);
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0) == null) goto L12;
            MediaPage[] r102 = SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0);
            int r0 = r102.length;
            int r1 = 0;
        L5:
            if (r1 >= r0) goto L12;
            MediaPage r2 = r102[r1];
            if (r2 == null) goto L11;
            if (MediaPage.m12961$$Nest$fgetlistView(r2) == null) goto L11;
            InternalListView r3 = MediaPage.m12961$$Nest$fgetlistView(r2);
            int r4 = MediaPage.m12961$$Nest$fgetlistView(r2).getPaddingLeft();
            int r5 = SharedMediaLayout.m12895$$Nest$mgetPagePaddingTop(this.this$0, r2.selectedType);
            int r6 = MediaPage.m12961$$Nest$fgetlistView(r2).getPaddingRight();
            InternalListView r22 = MediaPage.m12961$$Nest$fgetlistView(r2);
            SharedMediaLayout r7 = this.this$0;
            int r72 = SharedMediaLayout.m12894$$Nest$mgetPagePaddingBottom(r7, r7.isStoriesView());
            r22.hintPaddingBottom = r72;
            r3.setPadding(r4, r5, r6, r72);
        L11:
            r1 = r1 + 1;
        L12:
            SharedMediaLayout.m12890$$Nest$mcheckUi_topPanelLayoutY(this.this$0);
        }

        @Override // org.telegram.ui.ProfileStoriesCollectionTabs
        public void updatedReordering(boolean r5) {
            this.this$0.saveItem.setVisibility(0);
            ViewPropertyAnimator r0 = this.this$0.saveItem.animate();
            float r1 = 1.0f;
            if (r5 == false) goto L5;
            float r2 = 1.0f;
        L6:
            ViewPropertyAnimator r02 = r0.alpha(r2);
            if (r5 == false) goto L9;
            float r3 = 1.0f;
        L10:
            ViewPropertyAnimator r03 = r02.scaleX(r3);
            if (r5 == true) goto L14;
            r1 = 0.4f;
        L14:
            r03.scaleY(r1).withEndAction(new SharedMediaLayout$15$$ExternalSyntheticLambda0(this, r5)).start();
            SharedMediaLayout.m12920$$Nest$mupdateOptionsSearch(this.this$0, true);
            return;
        L9:
            r3 = 0.4f;
            goto L10
        L5:
            r2 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$16, reason: invalid class name */
    public class AnonymousClass16 extends MediaPage {
        final /* synthetic */ SharedMediaLayout this$0;

        public AnonymousClass16(SharedMediaLayout r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void setTranslationX(float r7) {
            super.setTranslationX(r7);
            if (SharedMediaLayout.m12852$$Nest$fgettabsAnimationInProgress(this.this$0) == false) goto L24;
            int r0 = 0;
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0] != this) goto L24;
            float r72 = Math.abs(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0].getTranslationX()) / SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0].getMeasuredWidth();
            SharedMediaLayout r1 = this.this$0;
            SharedMediaLayout.m12909$$Nest$mselectTabWithId(r1, SharedMediaLayout.m12825$$Nest$fgetmediaPages(r1)[1].selectedType, r72);
            boolean r12 = this.this$0.canShowSearchItem();
            SharedMediaLayout r2 = this.this$0;
            if (r12 == false) goto L22;
            int r13 = SharedMediaLayout.m12841$$Nest$fgetsearchItemState(r2);
            SharedMediaLayout r22 = this.this$0;
            if (r13 != 2) goto L12;
            SharedMediaLayout.m12871$$Nest$fputsearchAlpha(r22, 1.0f - r72);
        L14:
            this.this$0.updateSearchItemIcon(r72);
            SharedMediaLayout r14 = this.this$0;
            SharedMediaLayout.m12867$$Nest$fputoptionsAlpha(r14, r14.getPhotoVideoOptionsAlpha(r72));
            SharedMediaLayout r73 = this.this$0;
            ImageView r15 = r73.photoVideoOptionsItem;
            if (SharedMediaLayout.m12827$$Nest$fgetoptionsAlpha(r73) != 0.0f) goto L17;
        L20:
            r0 = 4;
        L21:
            r15.setVisibility(r0);
        L23:
            SharedMediaLayout.m12919$$Nest$mupdateOptionsSearch(this.this$0);
            goto L24
        L17:
            if (this.this$0.canShowSearchItem() == false) goto L20;
            if (this.this$0.isArchivedOnlyStoriesView() == false) goto L21;
        L12:
            if (SharedMediaLayout.m12841$$Nest$fgetsearchItemState(r22) != 1) goto L14;
            SharedMediaLayout.m12871$$Nest$fputsearchAlpha(this.this$0, r72);
            goto L14
        L22:
            SharedMediaLayout.m12871$$Nest$fputsearchAlpha(r2, 0.0f);
        L24:
            SharedMediaLayout.m12889$$Nest$mcheckStoriesTabsPosition(this.this$0);
            SharedMediaLayout.m12890$$Nest$mcheckUi_topPanelLayoutY(this.this$0);
            this.this$0.invalidateBlur();
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$17, reason: invalid class name */
    public class AnonymousClass17 extends ExtendedGridLayoutManager {
        private Size size;
        final /* synthetic */ SharedMediaLayout this$0;
        final /* synthetic */ MediaPage val$mediaPage;

        public AnonymousClass17(SharedMediaLayout r1, Context r2, int r3, MediaPage r4) {
            this.this$0 = r1;
            this.val$mediaPage = r4;
            super(r2, r3);
            this.size = new Size();
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public void calculateExtraLayoutSpace(RecyclerView.State r2, int[] r3) {
            super.calculateExtraLayoutSpace(r2, r3);
            int r22 = this.val$mediaPage.selectedType;
            if (r22 != 0) goto L5;
        L11:
            r3[1] = Math.max(r3[1], SharedPhotoVideoCell.getItemSize(1) * 2);
            return;
        L5:
            if (SharedMediaLayout.isAnyStoryPageType(r22) == true) goto L11;
            if (this.val$mediaPage.selectedType != 1) goto L13;
            r3[1] = Math.max(r3[1], AndroidUtilities.dp(56.0f) * 2);
            return;
        }

        @Override // org.telegram.ui.Components.ExtendedGridLayoutManager
        public int getFlowItemCount() {
            if (MediaPage.m12961$$Nest$fgetlistView(this.val$mediaPage).getAdapter() == SharedMediaLayout.m12818$$Nest$fgetgifAdapter(this.this$0)) goto L7;
            return 0;
        L7:
            return getItemCount();
        }

        @Override // org.telegram.ui.Components.ExtendedGridLayoutManager
        public Size getSizeForItem(int r4) {
            if (MediaPage.m12961$$Nest$fgetlistView(this.val$mediaPage).getAdapter() == SharedMediaLayout.m12818$$Nest$fgetgifAdapter(this.this$0)) goto L5;
        L7:
            TLRPC.Document r42 = null;
        L8:
            Size r0 = this.size;
            r0.height = 100.0f;
            r0.width = 100.0f;
            if (r42 == null) goto L28;
            TLRPC.PhotoSize r02 = FileLoader.getClosestPhotoSizeWithSize(r42.thumbs, 90);
            if (r02 == null) goto L17;
            int r1 = r02.w;
            if (r1 == 0) goto L17;
            int r03 = r02.h;
            if (r03 == 0) goto L17;
            Size r2 = this.size;
            r2.width = r1;
            r2.height = r03;
        L17:
            ArrayList<TLRPC.DocumentAttribute> r43 = r42.attributes;
            int r04 = 0;
        L19:
            if (r04 >= r43.size()) goto L28;
            TLRPC.DocumentAttribute r12 = r43.get(r04);
            if ((r12 instanceof TLRPC.TL_documentAttributeImageSize) == true) goto L26;
            if ((r12 instanceof TLRPC.TL_documentAttributeVideo) == true) goto L26;
            r04 = r04 + 1;
        L26:
            Size r44 = this.size;
            r44.width = r12.w;
            r44.height = r12.h;
        L28:
            return this.size;
        L5:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[5].messages.isEmpty() == true) goto L7;
            r42 = SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[5].messages.get(r4).getDocument();
            goto L8
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler r2, RecyclerView.State r3, View r4, AccessibilityNodeInfoCompat r5) {
            super.onInitializeAccessibilityNodeInfoForItem(r2, r3, r4, r5);
            AccessibilityNodeInfoCompat.CollectionItemInfoCompat r1 = r5.getCollectionItemInfo();
            if (r1 != null) goto L5;
            return;
        L5:
            if (r1.isHeading() == false) goto L9;
            r5.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(r1.getRowIndex(), r1.getRowSpan(), r1.getColumnIndex(), r1.getColumnSpan(), false));
            return;
        }

        @Override // org.telegram.ui.Components.ExtendedGridLayoutManager, androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$18, reason: invalid class name */
    public class AnonymousClass18 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ SharedMediaLayout this$0;
        final /* synthetic */ MediaPage val$mediaPage;

        public AnonymousClass18(SharedMediaLayout r1, MediaPage r2) {
            this.this$0 = r1;
            this.val$mediaPage = r2;
        }

        private int getSpanSizeInternal(int r7) {
            int r0 = SharedMediaLayout.m12824$$Nest$fgetmediaColumnsCount(this.this$0)[SharedMediaLayout.isAnyStoryPageType(this.val$mediaPage.selectedType) ? 1 : 0];
            RecyclerView.Adapter r1 = MediaPage.m12961$$Nest$fgetlistView(this.val$mediaPage).getAdapter();
            SharedPhotoVideoAdapter r2 = SharedMediaLayout.m12829$$Nest$fgetphotoVideoAdapter(this.this$0);
            SharedMediaLayout r3 = this.this$0;
            if (r1 == r2) goto L5;
            int r12 = SharedMediaLayout.m12916$$Nest$mstoryAlbums_getTabTypeByStoriesAdapter(r3, MediaPage.m12961$$Nest$fgetlistView(this.val$mediaPage).getAdapter());
            MediaPage r22 = this.val$mediaPage;
            if (r12 != (-1)) goto L11;
            RecyclerView.Adapter r02 = MediaPage.m12961$$Nest$fgetlistView(r22).getAdapter();
            GifAdapter r13 = SharedMediaLayout.m12818$$Nest$fgetgifAdapter(this.this$0);
            MediaPage r23 = this.val$mediaPage;
            if (r02 == r13) goto L19;
            return MediaPage.m12960$$Nest$fgetlayoutManager(r23).getSpanCount();
        L19:
            if (MediaPage.m12961$$Nest$fgetlistView(r23).getAdapter() != SharedMediaLayout.m12818$$Nest$fgetgifAdapter(this.this$0)) goto L25;
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[5].messages.isEmpty() == false) goto L25;
            return MediaPage.m12960$$Nest$fgetlayoutManager(this.val$mediaPage).getSpanCount();
        L25:
            return MediaPage.m12960$$Nest$fgetlayoutManager(this.val$mediaPage).getSpanSizeForItem(r7);
        L11:
            if (MediaPage.m12961$$Nest$fgetlistView(r22).getAdapter().getItemViewType(r7) != 2) goto L13;
        L12:
            return r0;
        L13:
            return 1;
        L5:
            if (SharedMediaLayout.m12829$$Nest$fgetphotoVideoAdapter(r3).getItemViewType(r7) == 2) goto L12;
            return 1;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r1) {
            return Math.min(getSpanSizeInternal(r1), MediaPage.m12960$$Nest$fgetlayoutManager(this.val$mediaPage).getSpanCount());
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$19, reason: invalid class name */
    public class AnonymousClass19 extends SharedMediaListView {
        float lastY;
        float startY;
        final /* synthetic */ SharedMediaLayout this$0;
        final /* synthetic */ ExtendedGridLayoutManager val$layoutManager;
        final /* synthetic */ MediaPage val$mediaPage;

        public AnonymousClass19(SharedMediaLayout r1, Context r2, MediaPage r3, ExtendedGridLayoutManager r4) {
            this.this$0 = r1;
            this.val$mediaPage = r3;
            this.val$layoutManager = r4;
            super(r2);
        }

        private View findOuterScrollingAncestor() {
            Object r0 = getParent();     // Catch: Exception -> L12
        L4:
            if ((r0 instanceof View) == false) goto L22;
            if (r0 == this) goto L10;
            if ((r0 instanceof RecyclerView) == false) goto L10;
            return (View) r0;
        L10:
            r0 = ((View) r0).getParent();     // Catch: Exception -> L12
            goto L4
        L22:
            return null;
        L12:
            e = move-exception;
            FileLog.e(e);
            return null;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.SharedMediaListView
        public void checkHighlightCell(SharedPhotoVideoCell2 r4) {
            if (r4.getMessageId() == this.val$mediaPage.highlightMessageId) goto L5;
        L18:
            r4.setHighlightProgress(0.0f);
            return;
        L5:
            if (r4.imageReceiver.hasBitmapImage() == false) goto L18;
            MediaPage r3 = this.val$mediaPage;
            if (r3.highlightAnimation == true) goto L9;
            r3.highlightProgress = 0.0f;
            r3.highlightAnimation = true;
        L9:
            float r32 = r3.highlightProgress;
            if (r32 >= 0.3f) goto L13;
            float r33 = r32 / 0.3f;
        L16:
            r4.setHighlightProgress(r33);
            return;
        L13:
            if (r32 <= 0.7f) goto L15;
            r33 = (1.0f - r32) / 0.3f;
            goto L16
        L15:
            r33 = 1.0f;
            goto L16
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.SharedMediaListView, org.telegram.ui.Components.BlurredRecyclerView, org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r15) {
            if (getAdapter() == SharedMediaLayout.m12797$$Nest$fgetarchivedStoriesAdapter(this.this$0)) goto L7;
            if (getAdapter() == SharedMediaLayout.m12849$$Nest$fgetstoriesAdapter(this.this$0)) goto L7;
        L53:
            super.dispatchDraw(r15);
            MediaPage r152 = this.val$mediaPage;
            if (r152.highlightAnimation == false) goto L60;
            float r0 = r152.highlightProgress + 0.010666667f;
            r152.highlightProgress = r0;
            if (r0 < 1.0f) goto L58;
            r152.highlightProgress = 0.0f;
            r152.highlightAnimation = false;
            r152.highlightMessageId = 0;
        L58:
            invalidate();
        L60:
            if (this.poller != null) goto L62;
            this.poller = UserListPoller.getInstance(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getCurrentAccount());
        L62:
            this.poller.checkList(this);
            if (isChangeColumnsAnimation() == true) goto L67;
            SharedMediaLayout.m12862$$Nest$fputchangeColumnsTab(this.this$0, -1);
            return;
        L67:
            return;
        L7:
            if (getChildCount() <= 0) goto L53;
            View r02 = getChildAt(0);
            if (r02 == null) goto L53;
            if (getChildAdapterPosition(r02) != 0) goto L53;
            int r03 = r02.getTop();
            if (SharedMediaLayout.m12830$$Nest$fgetphotoVideoChangeColumnsAnimation(this.this$0) == false) goto L27;
            int r1 = SharedMediaLayout.m12806$$Nest$fgetchangeColumnsTab(this.this$0);
            if (getAdapter() != SharedMediaLayout.m12849$$Nest$fgetstoriesAdapter(this.this$0)) goto L17;
            int r4 = 8;
        L18:
            if (r1 != r4) goto L27;
            if (MediaPage.m12956$$Nest$fgetanimationSupportingListView(this.val$mediaPage).getChildCount() <= 0) goto L27;
            View r12 = MediaPage.m12956$$Nest$fgetanimationSupportingListView(this.val$mediaPage).getChildAt(0);
            if (r12 == null) goto L27;
            if (MediaPage.m12956$$Nest$fgetanimationSupportingListView(this.val$mediaPage).getChildAdapterPosition(r12) != 0) goto L27;
            r03 = AndroidUtilities.lerp(r03, r12.getTop(), SharedMediaLayout.m12831$$Nest$fgetphotoVideoChangeColumnsProgress(this.this$0));
            goto L27
        L17:
            r4 = 9;
        L27:
            if (getAdapter() == SharedMediaLayout.m12849$$Nest$fgetstoriesAdapter(this.this$0)) goto L53;
            boolean r42 = true;
            if (this.archivedHintPaint != null) goto L32;
            TextPaint r13 = new TextPaint(1);
            this.archivedHintPaint = r13;
            r13.setTextSize(AndroidUtilities.dp(14.0f));
            this.archivedHintPaint.setColor(getThemedColor(Theme.key_windowBackgroundWhiteGrayText2));
        L32:
            int r9 = getMeasuredWidth() - AndroidUtilities.dp(60.0f);
            StaticLayout r14 = this.archivedHintLayout;
            if (r14 == null) goto L37;
            if (r14.getWidth() != r9) goto L37;
        L52:
            r15.save();
            r15.translate(((getWidth() - this.archivedHintLayoutWidth) / 2.0f) - this.archivedHintLayoutLeft, r03 - ((AndroidUtilities.dp(64.0f) + this.archivedHintLayout.getHeight()) / 2.0f));
            this.archivedHintLayout.draw(r15);
            r15.restore();
        L37:
            if (SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0) != null) goto L39;
        L41:
            r42 = false;
        L43:
            if (this.this$0.isArchivedOnlyStoriesView() == false) goto L47;
            if (r42 == false) goto L46;
            int r16 = R.string.ProfileStoriesArchiveChannelHint;
        L48:
            this.archivedHintLayout = new StaticLayout(LocaleController.getString(r16), this.archivedHintPaint, r9, Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            this.archivedHintLayoutWidth = 0.0f;
            this.archivedHintLayoutLeft = r9;
            int r17 = 0;
        L50:
            if (r17 >= this.archivedHintLayout.getLineCount()) goto L52;
            this.archivedHintLayoutWidth = Math.max(this.archivedHintLayoutWidth, this.archivedHintLayout.getLineWidth(r17));
            this.archivedHintLayoutLeft = Math.min(this.archivedHintLayoutLeft, this.archivedHintLayout.getLineLeft(r17));
            r17 = r17 + 1;
            goto L50
        L46:
            r16 = R.string.ProfileStoriesArchiveGroupHint;
            goto L48
        L47:
            r16 = R.string.ProfileStoriesArchiveHint;
            goto L48
        L39:
            if (ChatObject.isChannelAndNotMegaGroup(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getMessagesController().getChat(Long.valueOf(-SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0)))) == false) goto L41;
            goto L41
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r3) {
            if (SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0) == null) goto L16;
            if (SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).isInPreviewMode() == false) goto L16;
            this.lastY = r3.getY();
            if (r3.getAction() != 1) goto L10;
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).finishPreviewFragment();
        L14:
            return true;
        L10:
            if (r3.getAction() != 2) goto L14;
            float r32 = this.startY - this.lastY;
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).movePreviewFragment(r32);
            if (r32 >= 0.0f) goto L14;
            this.startY = this.lastY;
        L16:
            return super.dispatchTouchEvent(r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public void emptyViewUpdated(boolean r1, boolean r2) {
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.SharedMediaListView
        public int getAnimateToColumnsCount() {
            return SharedMediaLayout.m12792$$Nest$fgetanimateToColumnsCount(this.this$0);
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.SharedMediaListView
        public float getChangeColumnsProgress() {
            return SharedMediaLayout.m12831$$Nest$fgetphotoVideoChangeColumnsProgress(this.this$0);
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.SharedMediaListView
        public int getColumnsCount() {
            boolean r0 = SharedMediaLayout.isAnyStoryPageType(SharedMediaLayout.m12806$$Nest$fgetchangeColumnsTab(this.this$0));
            SharedMediaLayout r1 = this.this$0;
            if (r0 == false) goto L7;
            return SharedMediaLayout.m12824$$Nest$fgetmediaColumnsCount(r1)[1];
        L7:
            return SharedMediaLayout.m12824$$Nest$fgetmediaColumnsCount(r1)[0];
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.SharedMediaListView
        public SparseArray<Float> getMessageAlphaEnter() {
            return this.this$0.messageAlphaEnter;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.SharedMediaListView
        public RecyclerListView.FastScrollAdapter getMovingAdapter() {
            boolean r0 = SharedMediaLayout.isAnyStoryPageType(SharedMediaLayout.m12806$$Nest$fgetchangeColumnsTab(this.this$0));
            SharedMediaLayout r1 = this.this$0;
            if (r0 == false) goto L7;
            return SharedMediaLayout.m12914$$Nest$mstoryAlbums_getStoriesAdapterByTabType(r1, SharedMediaLayout.m12806$$Nest$fgetchangeColumnsTab(r1));
        L7:
            return SharedMediaLayout.m12829$$Nest$fgetphotoVideoAdapter(r1);
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public Integer getSelectorColor(int r3) {
            if (getAdapter() != SharedMediaLayout.m12808$$Nest$fgetchannelRecommendationsAdapter(this.this$0)) goto L11;
            if (ChannelRecommendationsAdapter.m12943$$Nest$fgetmore(SharedMediaLayout.m12808$$Nest$fgetchannelRecommendationsAdapter(this.this$0)) <= 0) goto L11;
            if (r3 != (SharedMediaLayout.m12808$$Nest$fgetchannelRecommendationsAdapter(this.this$0).getItemCount() - 1)) goto L11;
            return 0;
        L11:
            return super.getSelectorColor(r3);
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.SharedMediaListView
        public RecyclerListView.FastScrollAdapter getSupportingAdapter() {
            boolean r0 = SharedMediaLayout.isAnyStoryPageType(SharedMediaLayout.m12806$$Nest$fgetchangeColumnsTab(this.this$0));
            SharedMediaLayout r1 = this.this$0;
            if (r0 == false) goto L7;
            return SharedMediaLayout.m12915$$Nest$mstoryAlbums_getStoriesSupportingAdapterByTabType(r1, SharedMediaLayout.m12806$$Nest$fgetchangeColumnsTab(r1));
        L7:
            return SharedMediaLayout.m12795$$Nest$fgetanimationSupportingPhotoVideoAdapter(r1);
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.SharedMediaListView
        public InternalListView getSupportingListView() {
            return MediaPage.m12956$$Nest$fgetanimationSupportingListView(this.val$mediaPage);
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.SharedMediaListView
        public boolean isChangeColumnsAnimation() {
            return SharedMediaLayout.m12830$$Nest$fgetphotoVideoChangeColumnsAnimation(this.this$0);
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.SharedMediaListView
        public boolean isStories() {
            return SharedMediaLayout.isAnyStoryPageType(SharedMediaLayout.m12806$$Nest$fgetchangeColumnsTab(this.this$0));
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.SharedMediaListView
        public boolean isThisListView() {
            if (this != MediaPage.m12961$$Nest$fgetlistView(this.val$mediaPage)) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            SharedMediaLayout r12 = this.this$0;
            MediaPage r22 = this.val$mediaPage;
            SharedMediaLayout.m12888$$Nest$mcheckLoadMoreScroll(r12, r22, MediaPage.m12961$$Nest$fgetlistView(r22), this.val$layoutManager);
            if (this.val$mediaPage.selectedType != 0) goto L6;
            PhotoViewer.getInstance().checkCurrentImageVisibility();
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView
        public void onScrolled(int r1, int r2) {
            super.onScrolled(r1, r2);
            if (this.scrollingByUser == true) goto L5;
        L9:
            SharedMediaLayout.m12889$$Nest$mcheckStoriesTabsPosition(this.this$0);
            if (getAdapter() != SharedMediaLayout.m12833$$Nest$fgetpollAdapter(this.this$0)) goto L13;
            SharedMediaLayout.m12833$$Nest$fgetpollAdapter(this.this$0).onScrolled(this);
            return;
        L13:
            return;
        L5:
            if (this.this$0.getSelectedTab() != 11) goto L9;
            if (SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0) == null) goto L9;
            AndroidUtilities.hideKeyboard(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getParentActivity().getCurrentFocus());
            goto L9
        }

        @Override // android.view.View
        public boolean performAccessibilityAction(int r4, Bundle r5) {
            if (r4 != 4096) goto L14;
            View r0 = findOuterScrollingAncestor();     // Catch: Exception -> L11
            if (r0 != null) goto L7;
        L27:
        L26:
            return super.performAccessibilityAction(r4, r5);
        L7:
            if (r0.canScrollVertically(1) == false) goto L27;
            if (r0.performAccessibilityAction(r4, r5) == false) goto L27;
            return true;
        L14:
            if (r4 != 8192) goto L26;
            if (canScrollVertically(-1) == true) goto L27;
            View r2 = findOuterScrollingAncestor();     // Catch: Exception -> L11
            if (r2 == null) goto L27;
            if (r2.canScrollVertically(-1) == false) goto L27;
            if (r2.performAccessibilityAction(r4, r5) == false) goto L27;
            return true;
        L11:
            e = move-exception;
            FileLog.e(e);
            goto L26
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$2, reason: invalid class name */
    public class AnonymousClass2 extends SharedAudioCell {
        final /* synthetic */ SharedMediaLayout this$0;

        public AnonymousClass2(SharedMediaLayout r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Cells.SharedAudioCell
        public boolean needPlayMessage(MessageObject r5) {
            if (r5.isVoice() == false) goto L5;
        L12:
            boolean r52 = MediaController.getInstance().playMessage(r5);
            MediaController r0 = MediaController.getInstance();
            if (r52 == false) goto L15;
            ArrayList<MessageObject> r4 = SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[4].messages;
        L16:
            r0.setVoiceMessagesPlaylist(r4, false);
            return r52;
        L15:
            r4 = null;
            goto L16
        L5:
            if (r5.isRoundVideo() == true) goto L12;
            if (r5.isMusic() == true) goto L10;
            return false;
        L10:
            return MediaController.getInstance().setPlaylist(SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[4].messages, r5, SharedMediaLayout.m12826$$Nest$fgetmergeDialogId(this.this$0));
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$20, reason: invalid class name */
    public class AnonymousClass20 extends GridLayoutManager {
        final /* synthetic */ SharedMediaLayout this$0;

        public AnonymousClass20(SharedMediaLayout r1, Context r2, int r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public int scrollVerticallyBy(int r2, RecyclerView.Recycler r3, RecyclerView.State r4) {
            if (SharedMediaLayout.m12830$$Nest$fgetphotoVideoChangeColumnsAnimation(this.this$0) == false) goto L6;
            r2 = 0;
        L6:
            return super.scrollVerticallyBy(r2, r3, r4);
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$21, reason: invalid class name */
    public class AnonymousClass21 extends RecyclerView.ItemDecoration {
        final /* synthetic */ SharedMediaLayout this$0;
        final /* synthetic */ MediaPage val$mediaPage;

        public AnonymousClass21(SharedMediaLayout r1, MediaPage r2) {
            this.this$0 = r1;
            this.val$mediaPage = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r3, View r4, RecyclerView r5, RecyclerView.State r6) {
            if ((r4 instanceof SharedPhotoVideoCell2) == false) goto L18;
            SharedPhotoVideoCell2 r42 = (SharedPhotoVideoCell2) r4;
            int r52 = MediaPage.m12956$$Nest$fgetanimationSupportingListView(this.val$mediaPage).getChildAdapterPosition(r42);
            int r2 = MediaPage.m12955$$Nest$fgetanimationSupportingLayoutManager(this.val$mediaPage).getSpanCount();
            boolean r0 = true;
            if (r52 >= r2) goto L7;
            boolean r1 = true;
        L8:
            r42.isTop = r1;
            int r53 = r52 % r2;
            if (r53 != 0) goto L11;
            boolean r12 = true;
        L12:
            r42.isFirst = r12;
            if (r53 == (r2 - 1)) goto L16;
            r0 = false;
        L16:
            r42.isLast = r0;
            r3.left = 0;
            r3.top = 0;
            r3.bottom = 0;
            r3.right = 0;
            return;
        L11:
            r12 = false;
            goto L12
        L7:
            r1 = false;
            goto L8
        L18:
            r3.left = 0;
            r3.top = 0;
            r3.bottom = 0;
            r3.right = 0;
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$22, reason: invalid class name */
    public class AnonymousClass22 extends RecyclerView.ItemDecoration {
        final /* synthetic */ SharedMediaLayout this$0;
        final /* synthetic */ MediaPage val$mediaPage;

        public AnonymousClass22(SharedMediaLayout r1, MediaPage r2) {
            this.this$0 = r1;
            this.val$mediaPage = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r3, View r4, RecyclerView r5, RecyclerView.State r6) {
            int r1 = 0;
            if (MediaPage.m12961$$Nest$fgetlistView(this.val$mediaPage).getAdapter() != SharedMediaLayout.m12818$$Nest$fgetgifAdapter(this.this$0)) goto L15;
            int r42 = r5.getChildAdapterPosition(r4);
            r3.left = 0;
            r3.bottom = 0;
            if (MediaPage.m12960$$Nest$fgetlayoutManager(this.val$mediaPage).isFirstRow(r42) == true) goto L7;
            r3.top = AndroidUtilities.dp(2.0f);
        L9:
            if (MediaPage.m12960$$Nest$fgetlayoutManager(this.val$mediaPage).isLastInRow(r42) == true) goto L12;
            r1 = AndroidUtilities.dp(2.0f);
        L12:
            r3.right = r1;
            return;
        L7:
            r3.top = 0;
            goto L9
        L15:
            if ((r4 instanceof SharedPhotoVideoCell2) == false) goto L30;
            SharedPhotoVideoCell2 r43 = (SharedPhotoVideoCell2) r4;
            int r52 = MediaPage.m12961$$Nest$fgetlistView(this.val$mediaPage).getChildAdapterPosition(r43);
            int r2 = MediaPage.m12960$$Nest$fgetlayoutManager(this.val$mediaPage).getSpanCount();
            boolean r62 = true;
            if (r52 >= r2) goto L19;
            boolean r0 = true;
        L20:
            r43.isTop = r0;
            int r53 = r52 % r2;
            if (r53 != 0) goto L23;
            boolean r02 = true;
        L24:
            r43.isFirst = r02;
            if (r53 == (r2 - 1)) goto L28;
            r62 = false;
        L28:
            r43.isLast = r62;
            r3.left = 0;
            r3.top = 0;
            r3.bottom = 0;
            r3.right = 0;
            return;
        L23:
            r02 = false;
            goto L24
        L19:
            r0 = false;
            goto L20
        L30:
            r3.left = 0;
            r3.top = 0;
            r3.bottom = 0;
            r3.right = 0;
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$23, reason: invalid class name */
    public class AnonymousClass23 extends ChatActivity {
        boolean firstCreateView;
        final /* synthetic */ SharedMediaLayout this$0;
        final /* synthetic */ int val$pos;

        public AnonymousClass23(SharedMediaLayout r1, Bundle r2, int r3) {
            this.this$0 = r1;
            this.val$pos = r3;
            super(r2);
            this.firstCreateView = true;
        }

        @Override // org.telegram.ui.ChatActivity, org.telegram.ui.ActionBar.BaseFragment
        public void onTransitionAnimationStart(boolean r12, boolean r13) {
            if (this.firstCreateView == true) goto L5;
        L11:
            super.onTransitionAnimationStart(r12, r13);
            return;
        L5:
            if (this.searchItem == null) goto L7;
            openSearchWithText(_UrlKt.FRAGMENT_ENCODE_SET);
            this.searchItem.setSearchFieldText(SavedMessagesSearchAdapter.m12986$$Nest$fgetlastQuery(SharedMediaLayout.m12838$$Nest$fgetsavedMessagesSearchAdapter(this.this$0)), false);
        L7:
            SearchTagsList r0 = this.actionBarSearchTags;
            if (r0 == null) goto L10;
            r0.setChosen(SavedMessagesSearchAdapter.m12987$$Nest$fgetlastReaction(SharedMediaLayout.m12838$$Nest$fgetsavedMessagesSearchAdapter(this.this$0)), false);
        L10:
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getMediaDataController().portSavedSearchResults(getClassGuid(), SavedMessagesSearchAdapter.m12987$$Nest$fgetlastReaction(SharedMediaLayout.m12838$$Nest$fgetsavedMessagesSearchAdapter(this.this$0)), SavedMessagesSearchAdapter.m12986$$Nest$fgetlastQuery(SharedMediaLayout.m12838$$Nest$fgetsavedMessagesSearchAdapter(this.this$0)), SharedMediaLayout.m12838$$Nest$fgetsavedMessagesSearchAdapter(this.this$0).cachedMessages, SharedMediaLayout.m12838$$Nest$fgetsavedMessagesSearchAdapter(this.this$0).loadedMessages, this.val$pos, SavedMessagesSearchAdapter.m12984$$Nest$fgetcount(SharedMediaLayout.m12838$$Nest$fgetsavedMessagesSearchAdapter(this.this$0)), SavedMessagesSearchAdapter.m12985$$Nest$fgetendReached(SharedMediaLayout.m12838$$Nest$fgetsavedMessagesSearchAdapter(this.this$0)));
            this.firstCreateView = false;
            goto L11
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$24, reason: invalid class name */
    public class AnonymousClass24 extends RecyclerView.OnScrollListener {
        final /* synthetic */ SharedMediaLayout this$0;
        final /* synthetic */ ExtendedGridLayoutManager val$layoutManager;
        final /* synthetic */ MediaPage val$mediaPage;

        public AnonymousClass24(SharedMediaLayout r1, MediaPage r2, ExtendedGridLayoutManager r3) {
            this.this$0 = r1;
            this.val$mediaPage = r2;
            this.val$layoutManager = r3;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            SharedMediaLayout r0 = this.this$0;
            if (r2 == 0) goto L5;
            boolean r12 = true;
        L6:
            SharedMediaLayout.m12870$$Nest$fputscrolling(r0, r12);
            return;
        L5:
            r12 = false;
            goto L6
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r3, int r4, int r5) {
            SharedMediaLayout.m12888$$Nest$mcheckLoadMoreScroll(this.this$0, this.val$mediaPage, (RecyclerListView) r3, this.val$layoutManager);
            if (r5 == 0) goto L12;
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0].selectedType == 0) goto L9;
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0].selectedType != 5) goto L12;
        L9:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].messages.isEmpty() == true) goto L12;
            SharedMediaLayout.m12910$$Nest$mshowFloatingDateView(this.this$0);
        L12:
            if (r5 == 0) goto L18;
            int r42 = this.val$mediaPage.selectedType;
            if (r42 != 0) goto L16;
        L17:
            SharedMediaLayout.m12923$$Nest$smshowFastScrollHint(this.val$mediaPage, SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0), true);
            goto L18
        L16:
            if (SharedMediaLayout.isAnyStoryPageType(r42) == true) goto L17;
        L18:
            MediaPage.m12961$$Nest$fgetlistView(this.val$mediaPage).checkSection(true);
            MediaPage r32 = this.val$mediaPage;
            if (r32.fastScrollHintView == null) goto L21;
            r32.invalidate();
        L21:
            this.this$0.invalidateBlur();
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$25, reason: invalid class name */
    public class AnonymousClass25 implements RecyclerListView.OnItemLongClickListenerExtended {
        final /* synthetic */ SharedMediaLayout this$0;
        final /* synthetic */ MediaPage val$mediaPage;

        public AnonymousClass25(SharedMediaLayout r1, MediaPage r2) {
            this.this$0 = r1;
            this.val$mediaPage = r2;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.OnItemLongClickListenerExtended
        public boolean onItemClick(View r5, int r6, float r7, float r8) {
            int r82 = 0;
            if (SharedMediaLayout.m12830$$Nest$fgetphotoVideoChangeColumnsAnimation(this.this$0) == false) goto L6;
            return false;
        L6:
            if (MediaPage.m12961$$Nest$fgetlistView(this.val$mediaPage).getAdapter() != SharedMediaLayout.m12838$$Nest$fgetsavedMessagesSearchAdapter(this.this$0)) goto L8;
            return false;
        L8:
            SharedMediaLayout r72 = this.this$0;
            if (r72.isActionModeShowed == false) goto L14;
            MediaPage r0 = this.val$mediaPage;
            if (r0.selectedType == 11) goto L14;
            MediaPage.m12961$$Nest$fgetlistView(r0).clickItem(r5, r6);
            return true;
        L14:
            int r02 = this.val$mediaPage.selectedType;
            if (r02 == 7) goto L17;
        L39:
            if (r02 != 1) goto L45;
            if ((r5 instanceof SharedDocumentCell) == false) goto L45;
            return SharedMediaLayout.m12901$$Nest$monItemLongClick(r72, ((SharedDocumentCell) r5).getMessage(), r5, 0);
        L45:
            if (r02 != 3) goto L51;
            if ((r5 instanceof SharedLinkCell) == false) goto L51;
            return SharedMediaLayout.m12901$$Nest$monItemLongClick(r72, ((SharedLinkCell) r5).getMessage(), r5, 0);
        L51:
            if (r02 == 2) goto L55;
            if (r02 == 4) goto L55;
        L59:
            if (r02 == 5) goto L61;
        L64:
            if (r02 == 0) goto L70;
            if (SharedMediaLayout.isAnyStoryPageType(r02) == true) goto L68;
        L75:
            int r73 = this.val$mediaPage.selectedType;
            if (r73 != 10) goto L79;
            SharedMediaLayout.m12808$$Nest$fgetchannelRecommendationsAdapter(this.this$0).openPreview(r6);
            return true;
        L79:
            if (r73 != 11) goto L82;
            SharedMediaLayout.m12836$$Nest$fgetsavedDialogsAdapter(this.this$0).select(r5);
            return true;
        L82:
            return false;
        L68:
            if (this.this$0.canEditStories() == false) goto L75;
        L70:
            if ((r5 instanceof SharedPhotoVideoCell2) == false) goto L75;
            MessageObject r62 = ((SharedPhotoVideoCell2) r5).getMessageObject();
            if (r62 == null) goto L82;
            return SharedMediaLayout.m12901$$Nest$monItemLongClick(this.this$0, r62, r5, this.val$mediaPage.selectedType);
        L61:
            if ((r5 instanceof ContextLinkCell) == false) goto L64;
            return SharedMediaLayout.m12901$$Nest$monItemLongClick(r72, (MessageObject) ((ContextLinkCell) r5).getParentObject(), r5, 0);
        L55:
            if ((r5 instanceof SharedAudioCell) == false) goto L59;
            return SharedMediaLayout.m12901$$Nest$monItemLongClick(r72, ((SharedAudioCell) r5).getMessage(), r5, 0);
        L17:
            if ((r5 instanceof UserCell) == false) goto L39;
            if (ChatUsersAdapter.m12945$$Nest$fgetsortedUsers(SharedMediaLayout.m12809$$Nest$fgetchatUsersAdapter(r72)).isEmpty() == false) goto L21;
            int r74 = r6;
        L25:
            if (r74 >= 0) goto L27;
        L38:
            return false;
        L27:
            if (r74 >= ChatUsersAdapter.m12944$$Nest$fgetchatInfo(SharedMediaLayout.m12809$$Nest$fgetchatUsersAdapter(this.this$0)).participants.participants.size()) goto L38;
            TLRPC.ChatParticipant r75 = ChatUsersAdapter.m12944$$Nest$fgetchatInfo(SharedMediaLayout.m12809$$Nest$fgetchatUsersAdapter(this.this$0)).participants.participants.get(r74);
            RecyclerListView r03 = (RecyclerListView) r5.getParent();
        L31:
            if (r82 >= r03.getChildCount()) goto L37;
            View r1 = r03.getChildAt(r82);
            if (r03.getChildAdapterPosition(r1) == r6) goto L34;
            r82 = r82 + 1;
            goto L31
        L34:
            r5 = r1;
        L37:
            return this.this$0.onMemberClick(r75, true, r5);
        L21:
            if (r6 < ChatUsersAdapter.m12945$$Nest$fgetsortedUsers(SharedMediaLayout.m12809$$Nest$fgetchatUsersAdapter(this.this$0)).size()) goto L23;
            return false;
        L23:
            r74 = ((Integer) ChatUsersAdapter.m12945$$Nest$fgetsortedUsers(SharedMediaLayout.m12809$$Nest$fgetchatUsersAdapter(this.this$0)).get(r6)).intValue();
            goto L25
        }

        @Override // org.telegram.ui.Components.RecyclerListView.OnItemLongClickListenerExtended
        public void onLongClickRelease() {
            if (SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0) == null) goto L8;
            Point r0 = AndroidUtilities.displaySize;
            if (r0.x <= r0.y) goto L9;
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).finishPreviewFragment();
            return;
        L9:
            return;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.OnItemLongClickListenerExtended
        public void onMove(float r2, float r3) {
            if (SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0) == null) goto L8;
            Point r22 = AndroidUtilities.displaySize;
            if (r22.x <= r22.y) goto L9;
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).movePreviewFragment(r3);
            return;
        L9:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$26, reason: invalid class name */
    public class AnonymousClass26 extends ClippingImageView {
        final /* synthetic */ SharedMediaLayout this$0;
        final /* synthetic */ RecyclerListView val$listView;

        public AnonymousClass26(SharedMediaLayout r1, Context r2, RecyclerListView r3) {
            this.this$0 = r1;
            this.val$listView = r3;
            super(r2);
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            this.val$listView.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$27, reason: invalid class name */
    public class AnonymousClass27 extends FlickerLoadingView {
        final /* synthetic */ SharedMediaLayout this$0;
        final /* synthetic */ MediaPage val$mediaPage;

        public AnonymousClass27(SharedMediaLayout r1, Context r2, MediaPage r3) {
            this.this$0 = r1;
            this.val$mediaPage = r3;
            super(r2);
        }

        @Override // org.telegram.ui.Components.FlickerLoadingView
        public int getColumnsCount() {
            return SharedMediaLayout.m12824$$Nest$fgetmediaColumnsCount(this.this$0)[SharedMediaLayout.isAnyStoryPageType(this.val$mediaPage.selectedType) ? 1 : 0];
        }

        @Override // org.telegram.ui.Components.FlickerLoadingView
        public int getViewType() {
            setIsSingleCell(false);
            int r0 = this.val$mediaPage.selectedType;
            if (r0 != 0) goto L5;
        L31:
            return 2;
        L5:
            if (r0 == 5) goto L31;
            if (r0 != 1) goto L11;
            return 3;
        L11:
            if (r0 != 2) goto L13;
        L30:
            return 6;
        L13:
            if (r0 == 4) goto L30;
            if (r0 != 3) goto L18;
            return 5;
        L18:
            if (r0 != 7) goto L20;
            return 6;
        L20:
            if (r0 != 6) goto L26;
            if (this.this$0.scrollSlidingTextTabStrip.getTabsCount() != 1) goto L24;
            setIsSingleCell(true);
        L24:
            return 1;
        L26:
            if (SharedMediaLayout.isAnyStoryPageType(r0) == false) goto L29;
            return 27;
        L29:
            return 1;
        }

        @Override // org.telegram.ui.Components.FlickerLoadingView, android.view.View
        public void onDraw(Canvas r8) {
            SharedMediaLayout.m12803$$Nest$fgetbackgroundPaint(this.this$0).setColor(SharedMediaLayout.m12897$$Nest$mgetThemedColor(this.this$0, Theme.key_windowBackgroundWhite));
            r8.drawRect(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), SharedMediaLayout.m12803$$Nest$fgetbackgroundPaint(this.this$0));
            super.onDraw(r8);
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$28, reason: invalid class name */
    public class AnonymousClass28 extends ViewOutlineProvider {
        final /* synthetic */ SharedMediaLayout this$0;

        public AnonymousClass28(SharedMediaLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r7, Outline r8) {
            r8.setRoundRect(0, 0, r7.getWidth(), r7.getHeight(), AndroidUtilities.dp(16.0f));
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$29, reason: invalid class name */
    public class AnonymousClass29 extends StickerEmptyView {
        final /* synthetic */ SharedMediaLayout this$0;

        public AnonymousClass29(SharedMediaLayout r1, Context r2, View r3, int r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.StickerEmptyView
        public void onVisibilityChange(float r1) {
            super.onVisibilityChange(r1);
            this.this$0.onBottomButtonVisibilityChange();
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$3, reason: invalid class name */
    public class AnonymousClass3 implements View.OnLayoutChangeListener {
        final /* synthetic */ SharedMediaLayout this$0;

        public AnonymousClass3(SharedMediaLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, int r9) {
            if (SharedMediaLayout.m12840$$Nest$fgetsearchItem(this.this$0) != null) goto L5;
            return;
        L5:
            View r12 = (View) SharedMediaLayout.m12840$$Nest$fgetsearchItem(this.this$0).getParent();
            SharedMediaLayout.m12840$$Nest$fgetsearchItem(this.this$0).setTranslationX(r12.getMeasuredWidth() - SharedMediaLayout.m12840$$Nest$fgetsearchItem(this.this$0).getRight());
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$30, reason: invalid class name */
    public class AnonymousClass30 extends FragmentContextView {
        final /* synthetic */ SharedMediaLayout this$0;

        public AnonymousClass30(SharedMediaLayout r1, Context r2, BaseFragment r3, View r4, boolean r5, Theme.ResourcesProvider r6) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6);
        }

        @Override // org.telegram.ui.Components.FragmentContextView, android.view.View
        public void setVisibility(int r2) {
            DialogsActivityTopPanelLayout r0 = SharedMediaLayout.m12853$$Nest$fgettopPanelLayout(this.this$0);
            FrameLayout r1 = SharedMediaLayout.m12817$$Nest$fgetfragmentContextViewWrapper(this.this$0);
            if (r2 != 0) goto L5;
            boolean r22 = true;
        L6:
            r0.setViewVisible(r1, r22);
            return;
        L5:
            r22 = false;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$31, reason: invalid class name */
    public class AnonymousClass31 extends SearchTagsList {
        final /* synthetic */ SharedMediaLayout this$0;

        public AnonymousClass31(SharedMediaLayout r1, Context r2, BaseFragment r3, int r4, long r5, Theme.ResourcesProvider r7) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r7);
        }

        @Override // org.telegram.ui.Components.SearchTagsList
        public void onShownUpdate(boolean r5) {
            this.this$0.scrollSlidingTextTabStrip.setAlpha(1.0f - this.shownT);
            this.this$0.scrollSlidingTextTabStrip.setPivotX(r5.getWidth() / 2.0f);
            this.this$0.scrollSlidingTextTabStrip.setScaleX(((1.0f - this.shownT) * 0.2f) + 0.8f);
            this.this$0.scrollSlidingTextTabStrip.setPivotY(AndroidUtilities.dp(48.0f));
            this.this$0.scrollSlidingTextTabStrip.setScaleY(((1.0f - this.shownT) * 0.2f) + 0.8f);
        }

        @Override // org.telegram.ui.Components.SearchTagsList
        public boolean setFilter(ReactionsLayoutInBubble.VisibleReaction r7) {
            if (SharedMediaLayout.m12840$$Nest$fgetsearchItem(this.this$0) != null) goto L5;
            return false;
        L5:
            SharedMediaLayout.m12875$$Nest$fputsearchingReaction(this.this$0, r7);
            String r0 = SharedMediaLayout.m12840$$Nest$fgetsearchItem(this.this$0).getSearchField().getText().toString();
            SharedMediaLayout r2 = this.this$0;
            if (r0.length() == 0) goto L8;
        L11:
            boolean r3 = true;
        L12:
            SharedMediaLayout.m12873$$Nest$fputsearchWas(r2, r3);
            SharedMediaLayout.m12918$$Nest$mswitchToCurrentSelectedMode(this.this$0, false);
            int r22 = SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0].selectedType;
            SharedMediaLayout r32 = this.this$0;
            if (r22 != 11) goto L19;
            if (SharedMediaLayout.m12838$$Nest$fgetsavedMessagesSearchAdapter(r32) == null) goto L17;
            SharedMediaLayout.m12838$$Nest$fgetsavedMessagesSearchAdapter(this.this$0).search(r0, SharedMediaLayout.m12843$$Nest$fgetsearchingReaction(this.this$0));
        L17:
            AndroidUtilities.hideKeyboard(SharedMediaLayout.m12840$$Nest$fgetsearchItem(this.this$0).getSearchField());
        L23:
            return true;
        L19:
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(r32)[0].selectedType != 12) goto L23;
            if (SharedMediaLayout.m12837$$Nest$fgetsavedMessagesContainer(this.this$0) == null) goto L23;
            SharedMediaLayout.m12837$$Nest$fgetsavedMessagesContainer(this.this$0).chatActivity.setTagFilter(r7);
            goto L23
        L8:
            if (SharedMediaLayout.m12843$$Nest$fgetsearchingReaction(this.this$0) != null) goto L11;
            r3 = false;
            goto L12
        }

        @Override // org.telegram.ui.Components.SearchTagsList
        public void updateTags(boolean r4) {
            super.updateTags(r4);
            if (SharedMediaLayout.m12842$$Nest$fgetsearching(this.this$0) == true) goto L5;
        L11:
            boolean r0 = false;
        L12:
            show(r0);
            ActionBarMenuItem r02 = this.this$0.searchItemIcon;
            if (r02 == null) goto L22;
            if (hasFilters() == true) goto L17;
        L19:
            int r2 = R.drawable.outline_header_search;
        L20:
            r02.setIcon(r2, r4);
            goto L22
        L17:
            if (SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getUserConfig().isPremium() == false) goto L19;
            r2 = R.drawable.navbar_search_tag;
        L22:
            if (SharedMediaLayout.m12840$$Nest$fgetsearchItem(this.this$0) == null) goto L33;
            ActionBarMenuItem r42 = SharedMediaLayout.m12840$$Nest$fgetsearchItem(this.this$0);
            SearchTagsList r03 = this.this$0.searchTagsList;
            if (r03 != null) goto L26;
        L30:
            int r3 = R.string.Search;
        L31:
            r42.setSearchFieldHint(LocaleController.getString(r3));
            return;
        L26:
            if (r03.hasFilters() == false) goto L30;
            if (this.this$0.getSelectedTab() != 11) goto L30;
            r3 = R.string.SavedTagSearchHint;
            goto L31
        L33:
            return;
        L5:
            if (this.this$0.getSelectedTab() == 11) goto L9;
            if (this.this$0.getSelectedTab() != 12) goto L11;
        L9:
            if (this.this$0.searchTagsList.hasFilters() == false) goto L11;
            r0 = true;
            goto L12
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$32, reason: invalid class name */
    public class AnonymousClass32 extends AnimatorListenerAdapter {
        final /* synthetic */ SharedMediaLayout this$0;
        final /* synthetic */ Bitmap val$finalBitmap;
        final /* synthetic */ MediaPage val$mediaPage;
        final /* synthetic */ View val$view;

        public AnonymousClass32(SharedMediaLayout r1, View r2, MediaPage r3, Bitmap r4) {
            this.this$0 = r1;
            this.val$view = r2;
            this.val$mediaPage = r3;
            this.val$finalBitmap = r4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            SharedMediaLayout.m12863$$Nest$fputchangeTypeAnimation(this.this$0, false);
            if (this.val$view.getParent() == null) goto L6;
            this.val$mediaPage.removeView(this.val$view);
            this.val$finalBitmap.recycle();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$33, reason: invalid class name */
    public class AnonymousClass33 implements CalendarActivity.Callback {
        final /* synthetic */ SharedMediaLayout this$0;

        public AnonymousClass33(SharedMediaLayout r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.CalendarActivity.Callback
        public void onDateSelected(int r6, int r7) {
            int r0 = -1;
            int r2 = 0;
        L3:
            int r3 = SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].messages.size();
            SharedMediaLayout r4 = this.this$0;
            if (r2 >= r3) goto L9;
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(r4)[0].messages.get(r2).getId() != r6) goto L8;
            r0 = r2;
        L8:
            r2 = r2 + 1;
            goto L3
        L9:
            MediaPage r22 = SharedMediaLayout.m12893$$Nest$mgetMediaPage(r4, 0);
            if (r0 < 0) goto L13;
            if (r22 == null) goto L13;
            MediaPage.m12960$$Nest$fgetlayoutManager(r22).scrollToPositionWithOffset(r0, 0);
        L14:
            if (r22 == null) goto L20;
            r22.highlightMessageId = r6;
            r22.highlightAnimation = false;
            return;
        L20:
            return;
        L13:
            SharedMediaLayout.m12899$$Nest$mjumpToDate(this.this$0, 0, r6, r7, true);
            goto L14
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$34, reason: invalid class name */
    public class AnonymousClass34 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ SharedMediaLayout this$0;
        final /* synthetic */ MediaPage val$finalMediaPage;

        public AnonymousClass34(SharedMediaLayout r1, MediaPage r2) {
            this.this$0 = r1;
            this.val$finalMediaPage = r2;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r6) {
            RecyclerView.Adapter r0 = MediaPage.m12956$$Nest$fgetanimationSupportingListView(this.val$finalMediaPage).getAdapter();
            SharedPhotoVideoAdapter r1 = SharedMediaLayout.m12795$$Nest$fgetanimationSupportingPhotoVideoAdapter(this.this$0);
            SharedMediaLayout r2 = this.this$0;
            if (r0 != r1) goto L10;
            if (SharedMediaLayout.m12795$$Nest$fgetanimationSupportingPhotoVideoAdapter(r2).getItemViewType(r6) == 2) goto L7;
            return 1;
        L7:
            return MediaPage.m12955$$Nest$fgetanimationSupportingLayoutManager(this.val$finalMediaPage).getSpanCount();
        L10:
            if (SharedMediaLayout.m12917$$Nest$mstoryAlbums_getTabTypeByStoriesSupportingAdapter(r2, r0) != (-1)) goto L12;
        L15:
            return 1;
        L12:
            if (((StoriesAdapter) r0).getItemViewType(r6) != 2) goto L15;
            return MediaPage.m12955$$Nest$fgetanimationSupportingLayoutManager(this.val$finalMediaPage).getSpanCount();
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$35, reason: invalid class name */
    public class AnonymousClass35 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ SharedMediaLayout this$0;
        final /* synthetic */ MediaPage val$finalMediaPage;

        public AnonymousClass35(SharedMediaLayout r1, MediaPage r2) {
            this.this$0 = r1;
            this.val$finalMediaPage = r2;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r2) {
            SharedMediaLayout.m12869$$Nest$fputphotoVideoChangeColumnsProgress(this.this$0, ((Float) r2.getAnimatedValue()).floatValue());
            MediaPage.m12961$$Nest$fgetlistView(this.val$finalMediaPage).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$36, reason: invalid class name */
    public class AnonymousClass36 extends AnimatorListenerAdapter {
        final /* synthetic */ SharedMediaLayout this$0;
        final /* synthetic */ int val$ci;
        final /* synthetic */ MediaPage val$finalMediaPage;
        final /* synthetic */ boolean val$forward;

        public AnonymousClass36(SharedMediaLayout r1, boolean r2, int r3, MediaPage r4) {
            this.this$0 = r1;
            this.val$forward = r2;
            this.val$ci = r3;
            this.val$finalMediaPage = r4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r8) {
            int r1 = 0;
            SharedMediaLayout.m12868$$Nest$fputphotoVideoChangeColumnsAnimation(this.this$0, false);
            if (this.val$forward == false) goto L10;
            SharedMediaLayout.m12824$$Nest$fgetmediaColumnsCount(this.this$0)[this.val$ci] = SharedMediaLayout.m12792$$Nest$fgetanimateToColumnsCount(this.this$0);
            int r0 = this.val$ci;
            SharedMediaLayout r2 = this.this$0;
            if (r0 != 0) goto L8;
            SharedConfig.setMediaColumnsCount(SharedMediaLayout.m12792$$Nest$fgetanimateToColumnsCount(r2));
            goto L10
        L8:
            if (r2.getStoriesCount(this.val$finalMediaPage.selectedType) < 5) goto L10;
            SharedConfig.setStoriesColumnsCount(SharedMediaLayout.m12792$$Nest$fgetanimateToColumnsCount(this.this$0));
        L10:
            int r02 = 0;
        L11:
            int r22 = SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0).length;
            SharedMediaLayout r3 = this.this$0;
            if (r02 >= r22) goto L34;
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(r3)[r02] == null) goto L32;
            if (MediaPage.m12961$$Nest$fgetlistView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r02]) == null) goto L32;
            SharedMediaLayout r23 = this.this$0;
            if (r23.isTabZoomable(SharedMediaLayout.m12825$$Nest$fgetmediaPages(r23)[r02].selectedType) == false) goto L32;
            RecyclerView.Adapter r24 = MediaPage.m12961$$Nest$fgetlistView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r02]).getAdapter();
            if (r24 == null) goto L32;
            int r32 = r24.getItemCount();
            if (r02 != 0) goto L26;
            SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].setListFrozen(false);
        L26:
            if (this.val$forward == false) goto L31;
            MediaPage.m12960$$Nest$fgetlayoutManager(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r02]).setSpanCount(SharedMediaLayout.m12824$$Nest$fgetmediaColumnsCount(this.this$0)[this.val$ci]);
            MediaPage.m12961$$Nest$fgetlistView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r02]).invalidateItemDecorations();
            if (r24.getItemCount() != r32) goto L30;
            AndroidUtilities.updateVisibleRows(MediaPage.m12961$$Nest$fgetlistView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r02]));
            goto L31
        L30:
            r24.notifyDataSetChanged();
        L31:
            MediaPage.m12956$$Nest$fgetanimationSupportingListView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r02]).setVisibility(8);
        L32:
            r02 = r02 + 1;
            goto L11
        L34:
            if (r3.pinchCenterPosition >= 0) goto L36;
            SharedMediaLayout.m12907$$Nest$msaveScrollPosition(r3);
        L47:
            super.onAnimationEnd(r8);
            return;
        L36:
            if (r1 >= SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0).length) goto L47;
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r1].selectedType != SharedMediaLayout.m12806$$Nest$fgetchangeColumnsTab(this.this$0)) goto L45;
            if (this.val$forward == false) goto L44;
            View r03 = MediaPage.m12955$$Nest$fgetanimationSupportingLayoutManager(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r1]).findViewByPosition(this.this$0.pinchCenterPosition);
            if (r03 == null) goto L44;
            this.this$0.pinchCenterOffset = r03.getTop();
        L44:
            ExtendedGridLayoutManager r04 = MediaPage.m12960$$Nest$fgetlayoutManager(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r1]);
            SharedMediaLayout r25 = this.this$0;
            r04.scrollToPositionWithOffset(r25.pinchCenterPosition, (-MediaPage.m12961$$Nest$fgetlistView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(r25)[r1]).getPaddingTop()) + this.this$0.pinchCenterOffset);
        L45:
            r1 = r1 + 1;
            goto L36
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$37, reason: invalid class name */
    public class AnonymousClass37 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ SharedMediaLayout this$0;
        final /* synthetic */ MediaPage val$finalMediaPage;

        public AnonymousClass37(SharedMediaLayout r1, MediaPage r2) {
            this.this$0 = r1;
            this.val$finalMediaPage = r2;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r2) {
            SharedMediaLayout.m12869$$Nest$fputphotoVideoChangeColumnsProgress(this.this$0, ((Float) r2.getAnimatedValue()).floatValue());
            MediaPage.m12961$$Nest$fgetlistView(this.val$finalMediaPage).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$38, reason: invalid class name */
    public class AnonymousClass38 extends AnimatorListenerAdapter {
        final /* synthetic */ SharedMediaLayout this$0;
        final /* synthetic */ int val$ci;
        final /* synthetic */ int val$newColumnsCount;

        public AnonymousClass38(SharedMediaLayout r1, int r2, int r3) {
            this.this$0 = r1;
            this.val$ci = r2;
            this.val$newColumnsCount = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r7) {
            this.this$0.notificationsLocker.unlock();
            SharedMediaLayout.m12868$$Nest$fputphotoVideoChangeColumnsAnimation(this.this$0, false);
            SharedMediaLayout.m12824$$Nest$fgetmediaColumnsCount(this.this$0)[this.val$ci] = this.val$newColumnsCount;
            int r72 = 0;
        L3:
            int r1 = SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0).length;
            SharedMediaLayout r2 = this.this$0;
            if (r72 >= r1) goto L23;
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(r2)[r72] == null) goto L22;
            if (MediaPage.m12961$$Nest$fgetlistView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r72]) == null) goto L22;
            SharedMediaLayout r12 = this.this$0;
            if (r12.isTabZoomable(SharedMediaLayout.m12825$$Nest$fgetmediaPages(r12)[r72].selectedType) == false) goto L22;
            RecyclerView.Adapter r13 = MediaPage.m12961$$Nest$fgetlistView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r72]).getAdapter();
            if (r13 == null) goto L22;
            int r22 = r13.getItemCount();
            if (r72 != 0) goto L17;
            SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].setListFrozen(false);
        L17:
            MediaPage.m12960$$Nest$fgetlayoutManager(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r72]).setSpanCount(SharedMediaLayout.m12824$$Nest$fgetmediaColumnsCount(this.this$0)[this.val$ci]);
            MediaPage.m12961$$Nest$fgetlistView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r72]).invalidateItemDecorations();
            if (r13.getItemCount() != r22) goto L20;
            AndroidUtilities.updateVisibleRows(MediaPage.m12961$$Nest$fgetlistView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r72]));
        L21:
            MediaPage.m12956$$Nest$fgetanimationSupportingListView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r72]).setVisibility(8);
            goto L22
        L20:
            r13.notifyDataSetChanged();
        L22:
            r72 = r72 + 1;
            goto L3
        L23:
            SharedMediaLayout.m12907$$Nest$msaveScrollPosition(r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$39, reason: invalid class name */
    public class AnonymousClass39 extends ScrollSlidingTextTabStripInner {
        final /* synthetic */ SharedMediaLayout this$0;

        public AnonymousClass39(SharedMediaLayout r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r1, r2, r3);
        }

        @Override // org.telegram.ui.Components.ScrollSlidingTextTabStrip
        public int processColor(int r1) {
            return this.this$0.processColor(r1);
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$4, reason: invalid class name */
    public class AnonymousClass4 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        final /* synthetic */ SharedMediaLayout this$0;

        public static /* synthetic */ void $r8$lambda$goXQH_99js1KoL0TlRClMsqgd94(AnonymousClass4 r0) {
            r0.lambda$onTextChanged$0();
        }

        public AnonymousClass4(SharedMediaLayout r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onTextChanged$0() {
            SharedMediaLayout.m12918$$Nest$mswitchToCurrentSelectedMode(this.this$0, false);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onLayout(int r1, int r2, int r3, int r4) {
            View r12 = (View) SharedMediaLayout.m12840$$Nest$fgetsearchItem(this.this$0).getParent();
            SharedMediaLayout.m12840$$Nest$fgetsearchItem(this.this$0).setTranslationX(r12.getMeasuredWidth() - SharedMediaLayout.m12840$$Nest$fgetsearchItem(this.this$0).getRight());
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchCollapse() {
            SharedMediaLayout.m12874$$Nest$fputsearching(this.this$0, false);
            SharedMediaLayout.m12875$$Nest$fputsearchingReaction(this.this$0, null);
            ActionBarMenuItem r0 = this.this$0.searchItemIcon;
            if (r0 == null) goto L5;
            r0.setVisibility(0);
        L5:
            SharedMediaLayout r02 = this.this$0;
            if (r02.photoVideoOptionsItem != null) goto L8;
        L10:
            SearchTagsList r03 = this.this$0.searchTagsList;
            if (r03 == null) goto L14;
            r03.clear();
            this.this$0.searchTagsList.show(false);
        L14:
            if (SharedMediaLayout.m12837$$Nest$fgetsavedMessagesContainer(this.this$0) == null) goto L16;
            SharedMediaLayout.m12837$$Nest$fgetsavedMessagesContainer(this.this$0).chatActivity.clearSearch();
        L16:
            SharedMediaLayout.m12873$$Nest$fputsearchWas(this.this$0, false);
            SharedMediaLayout.m12840$$Nest$fgetsearchItem(this.this$0).setVisibility(0);
            SharedMediaLayout.m12813$$Nest$fgetdocumentsSearchAdapter(this.this$0).search(null, true);
            SharedMediaLayout.m12823$$Nest$fgetlinksSearchAdapter(this.this$0).search(null, true);
            SharedMediaLayout.m12801$$Nest$fgetaudioSearchAdapter(this.this$0).search(null, true);
            SharedMediaLayout.m12820$$Nest$fgetgroupUsersSearchAdapter(this.this$0).search(null, true);
            if (SharedMediaLayout.m12838$$Nest$fgetsavedMessagesSearchAdapter(this.this$0) == null) goto L19;
            SharedMediaLayout.m12838$$Nest$fgetsavedMessagesSearchAdapter(this.this$0).search(null, null);
        L19:
            this.this$0.onSearchStateChanged(false);
            if (SharedMediaLayout.m12828$$Nest$fgetoptionsSearchImageView(this.this$0) == null) goto L22;
            SharedMediaLayout.m12828$$Nest$fgetoptionsSearchImageView(this.this$0).animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setDuration(320).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
        L22:
            boolean r04 = SharedMediaLayout.m12821$$Nest$fgetignoreSearchCollapse(this.this$0);
            SharedMediaLayout r4 = this.this$0;
            if (r04 == false) goto L26;
            SharedMediaLayout.m12865$$Nest$fputignoreSearchCollapse(r4, false);
            return;
        L26:
            SharedMediaLayout.m12918$$Nest$mswitchToCurrentSelectedMode(r4, false);
            return;
        L8:
            if (r02.getPhotoVideoOptionsAlpha(0.0f) <= 0.5f) goto L10;
            this.this$0.photoVideoOptionsItem.setVisibility(0);
            goto L10
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
            SharedMediaLayout.m12874$$Nest$fputsearching(this.this$0, true);
            SharedMediaLayout r0 = this.this$0;
            SearchTagsList r2 = r0.searchTagsList;
            if (r2 != null) goto L5;
        L13:
            ImageView r02 = this.this$0.photoVideoOptionsItem;
            if (r02 == null) goto L16;
            r02.setVisibility(8);
        L16:
            ActionBarMenuItem r03 = this.this$0.searchItemIcon;
            if (r03 == null) goto L19;
            r03.setVisibility(8);
        L19:
            SharedMediaLayout.m12840$$Nest$fgetsearchItem(this.this$0).setVisibility(8);
            this.this$0.onSearchStateChanged(true);
            if (SharedMediaLayout.m12828$$Nest$fgetoptionsSearchImageView(this.this$0) == null) goto L23;
            SharedMediaLayout.m12828$$Nest$fgetoptionsSearchImageView(this.this$0).animate().scaleX(0.6f).scaleY(0.6f).alpha(0.0f).setDuration(320).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
            return;
        L23:
            return;
        L5:
            if (r0.getSelectedTab() == 11) goto L9;
            if (this.this$0.getSelectedTab() == 12) goto L9;
        L11:
            boolean r04 = false;
        L12:
            r2.show(r04);
        L9:
            if (this.this$0.searchTagsList.hasFilters() == false) goto L11;
            r04 = true;
            goto L12
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchPressed(EditText r1) {
            super.onSearchPressed(r1);
            if (SharedMediaLayout.m12837$$Nest$fgetsavedMessagesContainer(this.this$0) == null) goto L6;
            SharedMediaLayout.m12837$$Nest$fgetsavedMessagesContainer(this.this$0).chatActivity.hitSearch();
            return;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r6) {
            String r62 = r6.getText().toString();
            if (SharedMediaLayout.m12837$$Nest$fgetsavedMessagesContainer(this.this$0) == null) goto L9;
            SharedMediaLayout.m12837$$Nest$fgetsavedMessagesContainer(this.this$0).chatActivity.setSearchQuery(r62);
            if (TextUtils.isEmpty(r62) == false) goto L9;
            if (SharedMediaLayout.m12843$$Nest$fgetsearchingReaction(this.this$0) != null) goto L9;
            SharedMediaLayout.m12837$$Nest$fgetsavedMessagesContainer(this.this$0).chatActivity.clearSearch();
        L9:
            SharedMediaLayout.m12840$$Nest$fgetsearchItem(this.this$0).setVisibility(8);
            SharedMediaLayout r0 = this.this$0;
            if (r62.length() == 0) goto L12;
        L15:
            boolean r1 = true;
        L16:
            SharedMediaLayout.m12873$$Nest$fputsearchWas(r0, r1);
            this.this$0.post(new SharedMediaLayout$4$$ExternalSyntheticLambda0(this));
            int r02 = SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0].selectedType;
            SharedMediaLayout r12 = this.this$0;
            if (r02 == 1) goto L19;
            int r03 = SharedMediaLayout.m12825$$Nest$fgetmediaPages(r12)[0].selectedType;
            SharedMediaLayout r13 = this.this$0;
            if (r03 == 3) goto L26;
            int r04 = SharedMediaLayout.m12825$$Nest$fgetmediaPages(r13)[0].selectedType;
            SharedMediaLayout r14 = this.this$0;
            if (r04 == 4) goto L33;
            int r05 = SharedMediaLayout.m12825$$Nest$fgetmediaPages(r14)[0].selectedType;
            SharedMediaLayout r15 = this.this$0;
            if (r05 != 7) goto L45;
            if (SharedMediaLayout.m12820$$Nest$fgetgroupUsersSearchAdapter(r15) == null) goto L55;
            SharedMediaLayout.m12820$$Nest$fgetgroupUsersSearchAdapter(this.this$0).search(r62, true);
            return;
        L55:
            return;
        L45:
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(r15)[0].selectedType == 11) goto L47;
            return;
        L47:
            if (SharedMediaLayout.m12838$$Nest$fgetsavedMessagesSearchAdapter(this.this$0) == null) goto L56;
            SharedMediaLayout.m12838$$Nest$fgetsavedMessagesSearchAdapter(this.this$0).search(r62, SharedMediaLayout.m12843$$Nest$fgetsearchingReaction(this.this$0));
            return;
        L56:
            return;
        L33:
            if (SharedMediaLayout.m12801$$Nest$fgetaudioSearchAdapter(r14) == null) goto L54;
            SharedMediaLayout.m12801$$Nest$fgetaudioSearchAdapter(this.this$0).search(r62, true);
            return;
        L54:
            return;
        L26:
            if (SharedMediaLayout.m12823$$Nest$fgetlinksSearchAdapter(r13) == null) goto L53;
            SharedMediaLayout.m12823$$Nest$fgetlinksSearchAdapter(this.this$0).search(r62, true);
            return;
        L53:
            return;
        L19:
            if (SharedMediaLayout.m12813$$Nest$fgetdocumentsSearchAdapter(r12) == null) goto L52;
            SharedMediaLayout.m12813$$Nest$fgetdocumentsSearchAdapter(this.this$0).search(r62, true);
            return;
        L52:
            return;
        L12:
            if (SharedMediaLayout.m12843$$Nest$fgetsearchingReaction(this.this$0) != null) goto L15;
            r1 = false;
            goto L16
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$40, reason: invalid class name */
    public class AnonymousClass40 implements ScrollSlidingTextTabStrip.ScrollSlidingTabStripDelegate {
        final /* synthetic */ SharedMediaLayout this$0;

        public static /* synthetic */ void $r8$lambda$_Z7aEBXJncTkt21R1kQTT9h4zKU(AnonymousClass40 r0, int r1) {
            r0.lambda$showOptions$0(r1);
        }

        public AnonymousClass40(SharedMediaLayout r1) {
            this.this$0 = r1;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private /* synthetic */ void lambda$showOptions$0(int r5) {
            if (SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0) != null) goto L6;
            return;
        L6:
            if ((SharedMediaLayout.m12822$$Nest$fgetinfo(this.this$0) instanceof TLRPC.TL_channelFull) == false) goto L8;
            TLRPC.TL_channels_setMainProfileTab r0 = new TLRPC.TL_channels_setMainProfileTab();
            r0.tab = SharedMediaLayout.getTab(r5, true);
            r0.channel = SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getMessagesController().getInputChannel(SharedMediaLayout.m12822$$Nest$fgetinfo(this.this$0).id);
            SharedMediaLayout.m12822$$Nest$fgetinfo(this.this$0).flags2 |= 4194304;
            SharedMediaLayout.m12822$$Nest$fgetinfo(this.this$0).main_tab = r0.tab;
            TLRPC.TL_account_setMainProfileTab r02 = r0;
        L11:
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getConnectionsManager().sendRequest(r02, null);
            this.this$0.updateTabs(true);
            return;
        L8:
            TLRPC.TL_account_setMainProfileTab r03 = new TLRPC.TL_account_setMainProfileTab();
            r03.tab = SharedMediaLayout.getTab(r5, true);
            r02 = r03;
            if (SharedMediaLayout.m12856$$Nest$fgetuserInfo(this.this$0) == null) goto L11;
            SharedMediaLayout.m12856$$Nest$fgetuserInfo(this.this$0).flags2 |= 1048576;
            SharedMediaLayout.m12856$$Nest$fgetuserInfo(this.this$0).main_tab = r03.tab;
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getMessagesStorage().updateUserInfo(SharedMediaLayout.m12856$$Nest$fgetuserInfo(this.this$0), true);
            r02 = r03;
            goto L11
        }

        @Override // org.telegram.ui.Components.ScrollSlidingTextTabStrip.ScrollSlidingTabStripDelegate
        public boolean canReorder(int r1) {
            return false;
        }

        @Override // org.telegram.ui.Components.ScrollSlidingTextTabStrip.ScrollSlidingTabStripDelegate
        public void onPageScrolled(float r8) {
            if (r8 == 1.0f) goto L5;
        L7:
            boolean r2 = SharedMediaLayout.m12793$$Nest$fgetanimatingForward(this.this$0);
            SharedMediaLayout r3 = this.this$0;
            if (r2 == false) goto L10;
            SharedMediaLayout.m12825$$Nest$fgetmediaPages(r3)[0].setTranslationX((-r8) * SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0].getMeasuredWidth());
            SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[1].setTranslationX(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0].getMeasuredWidth() - (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0].getMeasuredWidth() * r8));
        L11:
            SharedMediaLayout r22 = this.this$0;
            r22.onTabProgress(r22.getTabProgress());
            SharedMediaLayout r23 = this.this$0;
            SharedMediaLayout.m12867$$Nest$fputoptionsAlpha(r23, r23.getPhotoVideoOptionsAlpha(r8));
            SharedMediaLayout r24 = this.this$0;
            ImageView r32 = r24.photoVideoOptionsItem;
            int r6 = 4;
            if (SharedMediaLayout.m12827$$Nest$fgetoptionsAlpha(r24) != 0.0f) goto L14;
        L19:
            int r25 = 4;
        L20:
            r32.setVisibility(r25);
            if (SharedMediaLayout.m12840$$Nest$fgetsearchItem(this.this$0) != null) goto L23;
        L29:
            SharedMediaLayout r26 = this.this$0;
            SharedMediaLayout.m12871$$Nest$fputsearchAlpha(r26, r26.getSearchAlpha(r8));
            this.this$0.updateSearchItemIconAnimated();
        L30:
            SharedMediaLayout.m12919$$Nest$mupdateOptionsSearch(this.this$0);
            if (r8 != 1.0f) goto L42;
            MediaPage r82 = SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0];
            SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0] = SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[1];
            SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[1] = r82;
            SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[1].setVisibility(8);
            if (SharedMediaLayout.m12840$$Nest$fgetsearchItem(this.this$0) != null) goto L35;
        L40:
            SharedMediaLayout.m12872$$Nest$fputsearchItemState(this.this$0, 0);
            SharedMediaLayout.m12911$$Nest$mstartStopVisibleGifs(this.this$0);
            return;
        L35:
            if (SharedMediaLayout.m12841$$Nest$fgetsearchItemState(this.this$0) != 2) goto L40;
            ActionBarMenuItem r83 = SharedMediaLayout.m12840$$Nest$fgetsearchItem(this.this$0);
            if (this.this$0.isStoriesView() == false) goto L39;
            r6 = 8;
        L39:
            r83.setVisibility(r6);
            goto L40
        L42:
            return;
        L23:
            if (this.this$0.canShowSearchItem() == true) goto L29;
            ActionBarMenuItem r84 = SharedMediaLayout.m12840$$Nest$fgetsearchItem(this.this$0);
            if (this.this$0.isStoriesView() == false) goto L27;
            int r27 = 8;
        L28:
            r84.setVisibility(r27);
            SharedMediaLayout.m12871$$Nest$fputsearchAlpha(this.this$0, 0.0f);
            goto L30
        L27:
            r27 = 4;
            goto L28
        L14:
            if (this.this$0.canShowSearchItem() == false) goto L19;
            if (this.this$0.isArchivedOnlyStoriesView() == true) goto L19;
            r25 = 0;
            goto L20
        L10:
            SharedMediaLayout.m12825$$Nest$fgetmediaPages(r3)[0].setTranslationX(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0].getMeasuredWidth() * r8);
            SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[1].setTranslationX((SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0].getMeasuredWidth() * r8) - SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0].getMeasuredWidth());
            goto L11
        L5:
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[1].getVisibility() == 0) goto L7;
        }

        @Override // org.telegram.ui.Components.ScrollSlidingTextTabStrip.ScrollSlidingTabStripDelegate
        public void onPageSelected(int r4, boolean r5) {
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0].selectedType != r4) goto L5;
            return;
        L5:
            ProfileStoriesCollectionTabs r0 = this.this$0.storiesContainer;
            if (r0 != null) goto L8;
        L10:
            SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[1].selectedType = r4;
            SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[1].setVisibility(0);
            SharedMediaLayout.m12898$$Nest$mhideFloatingDateView(this.this$0, true);
            SharedMediaLayout.m12918$$Nest$mswitchToCurrentSelectedMode(this.this$0, true);
            SharedMediaLayout.m12860$$Nest$fputanimatingForward(this.this$0, r5);
            this.this$0.onSelectedTabChanged();
            this.this$0.animateSearchToOptions(!r5.isSearchItemVisible(r4), true);
            SharedMediaLayout.m12920$$Nest$mupdateOptionsSearch(this.this$0, true);
            return;
        L8:
            if (r4 != 8) goto L10;
            r0.selectTabWithId(0, 1.0f);
            goto L10
        }

        @Override // org.telegram.ui.Components.ScrollSlidingTextTabStrip.ScrollSlidingTabStripDelegate
        public void onSamePageSelected() {
            SharedMediaLayout.m12908$$Nest$mscrollToTop(this.this$0);
        }

        @Override // org.telegram.ui.Components.ScrollSlidingTextTabStrip.ScrollSlidingTabStripDelegate
        public boolean showOptions(int r7, View r8) {
            if (SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0) != null) goto L6;
            return false;
        L6:
            if (SharedMediaLayout.getTab(r7, SharedMediaLayout.m12822$$Nest$fgetinfo(this.this$0) instanceof TLRPC.TL_channelFull) != null) goto L8;
            return false;
        L8:
            boolean r0 = SharedMediaLayout.m12822$$Nest$fgetinfo(this.this$0) instanceof TLRPC.TL_channelFull;
            SharedMediaLayout r2 = this.this$0;
            if (r0 == false) goto L15;
            if (ChatObject.canUserDoAction(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(r2).getMessagesController().getChat(Long.valueOf(SharedMediaLayout.m12822$$Nest$fgetinfo(this.this$0).id)), 5) == true) goto L13;
            return false;
        L13:
            TLRPC.ProfileTab r02 = SharedMediaLayout.m12822$$Nest$fgetinfo(this.this$0).main_tab;
        L20:
            if (r02 != null) goto L22;
        L26:
            ItemOptions r82 = ItemOptions.makeOptions(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0), r8);
            r82.setScrimViewBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(24.0f), Theme.getColor(Theme.key_windowBackgroundWhite)));
            r82.add(R.drawable.tabs_reorder, LocaleController.getString(R.string.ProfileTabSetAsMain), new SharedMediaLayout$40$$ExternalSyntheticLambda0(this, r7));
            r82.show();
            return true;
        L22:
            if (r7 != SharedMediaLayout.getTabId(r02)) goto L24;
        L25:
            return false;
        L24:
            if (SharedMediaLayout.m12814$$Nest$fgetfirstTab(this.this$0) != r7) goto L26;
        L15:
            if (SharedMediaLayout.m12811$$Nest$fgetdialog_id(r2) == SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getUserConfig().getClientUserId()) goto L17;
        L28:
            return false;
        L17:
            if (SharedMediaLayout.m12856$$Nest$fgetuserInfo(this.this$0) == null) goto L28;
            r02 = SharedMediaLayout.m12856$$Nest$fgetuserInfo(this.this$0).main_tab;
            goto L20
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$41, reason: invalid class name */
    public class AnonymousClass41 extends AnimatorListenerAdapter {
        final /* synthetic */ SharedMediaLayout this$0;

        public AnonymousClass41(SharedMediaLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            SharedMediaLayout.m12864$$Nest$fputfloatingDateAnimation(this.this$0, null);
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$42, reason: invalid class name */
    public class AnonymousClass42 extends AnimatorListenerAdapter {
        final /* synthetic */ SharedMediaFastScrollTooltip val$tooltip;

        public AnonymousClass42(SharedMediaFastScrollTooltip r1) {
            this.val$tooltip = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (this.val$tooltip.getParent() == null) goto L6;
            ((ViewGroup) this.val$tooltip.getParent()).removeView(this.val$tooltip);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$43, reason: invalid class name */
    public class AnonymousClass43 extends AnimatorListenerAdapter {
        final /* synthetic */ SharedMediaLayout this$0;

        public AnonymousClass43(SharedMediaLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r7) {
            SharedMediaLayout.m12880$$Nest$fputtabsAnimation(this.this$0, null);
            boolean r72 = SharedMediaLayout.m12802$$Nest$fgetbackAnimation(this.this$0);
            SharedMediaLayout r0 = this.this$0;
            int r1 = 4;
            if (r72 == false) goto L14;
            SharedMediaLayout.m12825$$Nest$fgetmediaPages(r0)[1].setVisibility(8);
            if (SharedMediaLayout.m12840$$Nest$fgetsearchItem(this.this$0) != null) goto L7;
        L12:
            SharedMediaLayout r73 = this.this$0;
            SharedMediaLayout.m12871$$Nest$fputsearchAlpha(r73, r73.getSearchAlpha(0.0f));
            this.this$0.updateSearchItemIcon(0.0f);
        L13:
            SharedMediaLayout.m12919$$Nest$mupdateOptionsSearch(this.this$0);
            SharedMediaLayout.m12872$$Nest$fputsearchItemState(this.this$0, 0);
        L23:
            SharedMediaLayout.m12881$$Nest$fputtabsAnimationInProgress(this.this$0, false);
            SharedMediaLayout.m12866$$Nest$fputmaybeStartTracking(this.this$0, false);
            SharedMediaLayout.m12877$$Nest$fputstartedTracking(this.this$0, false);
            this.this$0.onTabScroll(false);
            SharedMediaLayout.m12787$$Nest$fgetactionBar(this.this$0).setEnabled(true);
            this.this$0.scrollSlidingTextTabStrip.setEnabled(true);
            return;
        L7:
            if (this.this$0.canShowSearchItem() == true) goto L12;
            ActionBarMenuItem r74 = SharedMediaLayout.m12840$$Nest$fgetsearchItem(this.this$0);
            if (this.this$0.isStoriesView() == false) goto L11;
            r1 = 8;
        L11:
            r74.setVisibility(r1);
            SharedMediaLayout.m12871$$Nest$fputsearchAlpha(this.this$0, 0.0f);
            goto L13
        L14:
            MediaPage r75 = SharedMediaLayout.m12825$$Nest$fgetmediaPages(r0)[0];
            SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0] = SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[1];
            SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[1] = r75;
            SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[1].setVisibility(8);
            if (SharedMediaLayout.m12840$$Nest$fgetsearchItem(this.this$0) != null) goto L17;
        L22:
            SharedMediaLayout.m12872$$Nest$fputsearchItemState(this.this$0, 0);
            SharedMediaLayout r76 = this.this$0;
            SharedMediaLayout.m12909$$Nest$mselectTabWithId(r76, SharedMediaLayout.m12825$$Nest$fgetmediaPages(r76)[0].selectedType, 1.0f);
            this.this$0.onSelectedTabChanged();
            SharedMediaLayout.m12911$$Nest$mstartStopVisibleGifs(this.this$0);
            goto L23
        L17:
            if (SharedMediaLayout.m12841$$Nest$fgetsearchItemState(this.this$0) != 2) goto L22;
            ActionBarMenuItem r77 = SharedMediaLayout.m12840$$Nest$fgetsearchItem(this.this$0);
            if (this.this$0.isStoriesView() == false) goto L21;
            r1 = 8;
        L21:
            r77.setVisibility(r1);
            goto L22
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$44, reason: invalid class name */
    public class AnonymousClass44 extends AnimatorListenerAdapter {
        final /* synthetic */ SharedMediaLayout this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass44(SharedMediaLayout r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            SharedMediaLayout.m12858$$Nest$fputactionModeAnimation(this.this$0, null);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (SharedMediaLayout.m12788$$Nest$fgetactionModeAnimation(this.this$0) == null) goto L10;
            SharedMediaLayout.m12858$$Nest$fputactionModeAnimation(this.this$0, null);
            if (this.val$show == true) goto L9;
            SharedMediaLayout.m12789$$Nest$fgetactionModeLayout(this.this$0).setVisibility(4);
            return;
        L9:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$45, reason: invalid class name */
    public class AnonymousClass45 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ SharedMediaLayout this$0;
        final /* synthetic */ SparseBooleanArray val$addedMesages;
        final /* synthetic */ RecyclerListView val$finalListView;
        final /* synthetic */ View val$finalProgressView;
        final /* synthetic */ int val$oldItemCount;

        /* renamed from: org.telegram.ui.Components.SharedMediaLayout$45$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass45 this$1;
            final /* synthetic */ int val$messageId;

            public AnonymousClass1(AnonymousClass45 r1, int r2) {
                this.this$1 = r1;
                this.val$messageId = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                this.this$1.this$0.messageAlphaEnter.remove(this.val$messageId);
                this.this$1.val$finalListView.invalidate();
            }
        }

        /* renamed from: org.telegram.ui.Components.SharedMediaLayout$45$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass45 this$1;
            final /* synthetic */ RecyclerView.LayoutManager val$layoutManager;

            public AnonymousClass2(AnonymousClass45 r1, RecyclerView.LayoutManager r2) {
                this.this$1 = r1;
                this.val$layoutManager = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                this.this$1.val$finalProgressView.setAlpha(1.0f);
                this.val$layoutManager.stopIgnoringView(this.this$1.val$finalProgressView);
                AnonymousClass45 r1 = this.this$1;
                r1.val$finalListView.removeView(r1.val$finalProgressView);
            }
        }

        public static /* synthetic */ void $r8$lambda$9HP2m9yoRyge3uwExQb29jZ24es(RecyclerListView r0, ValueAnimator r1) {
            if (r0.hasSections() == false) goto L6;
            r0.invalidate();
            return;
        }

        /* renamed from: $r8$lambda$Y67-7mWGxnnRjtWwoEbZ6XmyO2U, reason: not valid java name */
        public static /* synthetic */ void m12928$r8$lambda$Y677mWGxnnRjtWwoEbZ6XmyO2U(RecyclerListView r0, ValueAnimator r1) {
            if (r0.hasSections() == false) goto L6;
            r0.invalidate();
            return;
        }

        /* renamed from: $r8$lambda$h78GCnjdnV3Mc-cEuarhmHPUoP4, reason: not valid java name */
        public static /* synthetic */ void m12929$r8$lambda$h78GCnjdnV3MccEuarhmHPUoP4(RecyclerListView r0, ValueAnimator r1) {
            if (r0.hasSections() == false) goto L6;
            r0.invalidate();
            return;
        }

        /* renamed from: $r8$lambda$jPU-PpyzykiFJ_OQ0VqefXg19AE, reason: not valid java name */
        public static /* synthetic */ void m12930$r8$lambda$jPUPpyzykiFJ_OQ0VqefXg19AE(AnonymousClass45 r0, int r1, RecyclerListView r2, ValueAnimator r3) {
            r0.lambda$onPreDraw$0(r1, r2, r3);
        }

        public AnonymousClass45(SharedMediaLayout r1, RecyclerListView r2, SparseBooleanArray r3, View r4, int r5) {
            this.this$0 = r1;
            this.val$finalListView = r2;
            this.val$addedMesages = r3;
            this.val$finalProgressView = r4;
            this.val$oldItemCount = r5;
        }

        private /* synthetic */ void lambda$onPreDraw$0(int r1, RecyclerListView r2, ValueAnimator r3) {
            this.this$0.messageAlphaEnter.put(r1, (Float) r3.getAnimatedValue());
            r2.invalidate();
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.this$0.getViewTreeObserver().removeOnPreDrawListener(this);
            RecyclerView.Adapter r0 = this.val$finalListView.getAdapter();
            if (r0 == SharedMediaLayout.m12829$$Nest$fgetphotoVideoAdapter(this.this$0)) goto L28;
            if (r0 == SharedMediaLayout.m12812$$Nest$fgetdocumentsAdapter(this.this$0)) goto L28;
            if (r0 == SharedMediaLayout.m12798$$Nest$fgetaudioAdapter(this.this$0)) goto L28;
            if (r0 == SharedMediaLayout.m12857$$Nest$fgetvoiceAdapter(this.this$0)) goto L28;
            int r02 = this.val$finalListView.getChildCount();
            AnimatorSet r1 = new AnimatorSet();
            int r7 = 0;
        L12:
            if (r7 >= r02) goto L26;
            View r8 = this.val$finalListView.getChildAt(r7);
            if (r8 != this.val$finalProgressView) goto L16;
        L18:
            View r82 = this.val$finalProgressView;
            if (r82 == null) goto L25;
            if (r82.getParent() != null) goto L25;
            this.val$finalListView.addView(this.val$finalProgressView);
            RecyclerView.LayoutManager r83 = this.val$finalListView.getLayoutManager();
            if (r83 == null) goto L25;
            r83.ignoreView(this.val$finalProgressView);
            View r9 = this.val$finalProgressView;
            ObjectAnimator r92 = ObjectAnimator.ofFloat(r9, View.ALPHA, new float[]{r9.getAlpha(), 0.0f});
            r92.addListener(new AnonymousClass2(this, r83));
            r92.addUpdateListener(new SharedMediaLayout$45$$ExternalSyntheticLambda3(this.val$finalListView));
            r92.start();
        L25:
            r7 = r7 + 1;
            goto L12
        L16:
            if (this.val$finalListView.getChildAdapterPosition(r8) < (this.val$oldItemCount - 1)) goto L18;
            r8.setAlpha(0.0f);
            int r93 = (int) ((Math.min(this.val$finalListView.getMeasuredHeight(), Math.max(0, r8.getTop())) / this.val$finalListView.getMeasuredHeight()) * 100.0f);
            ObjectAnimator r84 = ObjectAnimator.ofFloat(r8, View.ALPHA, new float[]{0.0f, 1.0f});
            r84.setStartDelay(r93);
            r84.setDuration(200);
            r84.addUpdateListener(new SharedMediaLayout$45$$ExternalSyntheticLambda2(this.val$finalListView));
            r1.playTogether(new Animator[]{r84});
            goto L18
        L26:
            r1.start();
        L37:
            return true;
        L28:
            if (this.val$addedMesages == null) goto L37;
            int r03 = this.val$finalListView.getChildCount();
            int r12 = 0;
        L30:
            if (r12 >= r03) goto L37;
            int r85 = SharedMediaLayout.m12921$$Nest$smgetMessageId(this.val$finalListView.getChildAt(r12));
            if (r85 == 0) goto L36;
            if (this.val$addedMesages.get(r85, false) == false) goto L36;
            this.this$0.messageAlphaEnter.put(r85, Float.valueOf(0.0f));
            ValueAnimator r94 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            r94.addUpdateListener(new SharedMediaLayout$45$$ExternalSyntheticLambda0(this, r85, this.val$finalListView));
            r94.addListener(new AnonymousClass1(this, r85));
            r94.setStartDelay((int) ((Math.min(this.val$finalListView.getMeasuredHeight(), Math.max(0, r7.getTop())) / this.val$finalListView.getMeasuredHeight()) * 100.0f));
            r94.setDuration(250);
            r94.addUpdateListener(new SharedMediaLayout$45$$ExternalSyntheticLambda1(this.val$finalListView));
            r94.start();
        L36:
            this.val$finalListView.invalidate();
            r12 = r12 + 1;
            goto L30
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$46, reason: invalid class name */
    public class AnonymousClass46 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ SharedMediaLayout this$0;
        final /* synthetic */ int val$num;

        public AnonymousClass46(SharedMediaLayout r1, int r2) {
            this.this$0 = r1;
            this.val$num = r2;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[this.val$num].getViewTreeObserver().removeOnPreDrawListener(this);
            SharedMediaLayout.m12892$$Nest$mfixLayoutInternal(this.this$0, this.val$num);
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$47, reason: invalid class name */
    public class AnonymousClass47 extends Visibility {
        final /* synthetic */ SharedMediaLayout this$0;

        public AnonymousClass47(SharedMediaLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.transition.Visibility
        public Animator onAppear(ViewGroup r3, View r4, TransitionValues r5, TransitionValues r6) {
            AnimatorSet r2 = new AnimatorSet();
            r2.playTogether(new Animator[]{ObjectAnimator.ofFloat(r4, View.ALPHA, new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(r4, View.SCALE_X, new float[]{0.5f, 1.0f}), ObjectAnimator.ofFloat(r4, View.SCALE_Y, new float[]{0.5f, 1.0f})});
            r2.setInterpolator(CubicBezierInterpolator.DEFAULT);
            return r2;
        }

        @Override // android.transition.Visibility
        public Animator onDisappear(ViewGroup r7, View r8, TransitionValues r9, TransitionValues r10) {
            AnimatorSet r6 = new AnimatorSet();
            r6.playTogether(new Animator[]{ObjectAnimator.ofFloat(r8, View.ALPHA, new float[]{r8.getAlpha(), 0.0f}), ObjectAnimator.ofFloat(r8, View.SCALE_X, new float[]{r8.getScaleX(), 0.5f}), ObjectAnimator.ofFloat(r8, View.SCALE_Y, new float[]{r8.getScaleX(), 0.5f})});
            r6.setInterpolator(CubicBezierInterpolator.DEFAULT);
            return r6;
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$48, reason: invalid class name */
    public class AnonymousClass48 extends ViewOutlineProvider {
        final /* synthetic */ SharedMediaLayout this$0;

        public AnonymousClass48(SharedMediaLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r7, Outline r8) {
            r8.setRoundRect(0, AndroidUtilities.dp(50.0f), r7.getWidth(), r7.getHeight() + AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f));
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$49, reason: invalid class name */
    public class AnonymousClass49 implements SharedLinkCell.SharedLinkCellDelegate {
        final /* synthetic */ SharedMediaLayout this$0;

        public static /* synthetic */ void $r8$lambda$CRd7cptuwiOHhOgRYTTIQ7vXvEI(AnonymousClass49 r0, String r1, DialogInterface r2, int r3) {
            r0.lambda$onLinkPress$0(r1, r2, r3);
        }

        public AnonymousClass49(SharedMediaLayout r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onLinkPress$0(String r1, DialogInterface r2, int r3) {
            if (r3 != 0) goto L6;
            SharedMediaLayout.m12904$$Nest$mopenUrl(this.this$0, r1);
            return;
        L6:
            if (r3 == 1) goto L8;
            return;
        L8:
            if (r1.startsWith("mailto:") == false) goto L11;
            r1 = r1.substring(7);
        L13:
            AndroidUtilities.addToClipboard(r1);
            return;
        L11:
            if (r1.startsWith("tel:") == false) goto L13;
            r1 = r1.substring(4);
            goto L13
        }

        @Override // org.telegram.ui.Cells.SharedLinkCell.SharedLinkCellDelegate
        public boolean canPerformActions() {
            return !this.this$0.isActionModeShowed;
        }

        @Override // org.telegram.ui.Cells.SharedLinkCell.SharedLinkCellDelegate
        public void needOpenWebView(TLRPC.WebPage r1, MessageObject r2) {
            SharedMediaLayout.m12905$$Nest$mopenWebView(this.this$0, r1, r2);
        }

        @Override // org.telegram.ui.Cells.SharedLinkCell.SharedLinkCellDelegate
        public void onLinkPress(String r5, boolean r6) {
            if (r6 == false) goto L5;
            BottomSheet.Builder r62 = new BottomSheet.Builder(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getParentActivity());
            r62.setTitle(r5);
            r62.setItems(new CharSequence[]{LocaleController.getString("Open", R.string.Open), LocaleController.getString("Copy", R.string.Copy)}, new SharedMediaLayout$49$$ExternalSyntheticLambda0(this, r5));
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).showDialog(r62.create());
            return;
        L5:
            SharedMediaLayout.m12904$$Nest$mopenUrl(this.this$0, r5);
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$5, reason: invalid class name */
    public class AnonymousClass5 implements View.OnClickListener {
        final /* synthetic */ SharedMediaLayout this$0;
        final /* synthetic */ Context val$context;
        final /* synthetic */ long val$did;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        /* renamed from: org.telegram.ui.Components.SharedMediaLayout$5$1, reason: invalid class name */
        public class AnonymousClass1 implements View.OnClickListener {
            final /* synthetic */ AnonymousClass5 this$1;
            final /* synthetic */ ActionBarMenuSubItem val$showPhotosItem;
            final /* synthetic */ ActionBarMenuSubItem val$showVideosItem;

            public AnonymousClass1(AnonymousClass5 r1, ActionBarMenuSubItem r2, ActionBarMenuSubItem r3) {
                this.this$1 = r1;
                this.val$showVideosItem = r2;
                this.val$showPhotosItem = r3;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View r2) {
                if (SharedMediaLayout.m12807$$Nest$fgetchangeTypeAnimation(this.this$1.this$0) == false) goto L6;
                return;
            L6:
                if (this.val$showVideosItem.getCheckView().isChecked() == false) goto L8;
            L11:
                this.val$showPhotosItem.setChecked(!r2.getCheckView().isChecked());
                if (this.val$showPhotosItem.getCheckView().isChecked() == true) goto L14;
            L16:
                SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$1.this$0)[0].filterType = 2;
            L17:
                SharedMediaLayout.m12886$$Nest$mchangeMediaFilterType(this.this$1.this$0);
                return;
            L14:
                if (this.val$showVideosItem.getCheckView().isChecked() == false) goto L16;
                SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$1.this$0)[0].filterType = 0;
                goto L17
            L8:
                if (this.val$showPhotosItem.getCheckView().isChecked() == false) goto L11;
                ActionBarMenuSubItem r22 = this.val$showPhotosItem;
                SharedMediaLayout r1 = this.this$1.this$0;
                float r0 = -SharedMediaLayout.m12847$$Nest$fgetshiftDp(r1);
                SharedMediaLayout.m12876$$Nest$fputshiftDp(r1, r0);
                AndroidUtilities.shakeViewSpring(r22, r0);
            }
        }

        /* renamed from: org.telegram.ui.Components.SharedMediaLayout$5$2, reason: invalid class name */
        public class AnonymousClass2 implements View.OnClickListener {
            final /* synthetic */ AnonymousClass5 this$1;
            final /* synthetic */ ActionBarMenuSubItem val$showPhotosItem;
            final /* synthetic */ ActionBarMenuSubItem val$showVideosItem;

            public AnonymousClass2(AnonymousClass5 r1, ActionBarMenuSubItem r2, ActionBarMenuSubItem r3) {
                this.this$1 = r1;
                this.val$showPhotosItem = r2;
                this.val$showVideosItem = r3;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View r3) {
                if (SharedMediaLayout.m12807$$Nest$fgetchangeTypeAnimation(this.this$1.this$0) == false) goto L6;
                return;
            L6:
                if (this.val$showPhotosItem.getCheckView().isChecked() == false) goto L8;
            L11:
                this.val$showVideosItem.setChecked(!r3.getCheckView().isChecked());
                if (this.val$showPhotosItem.getCheckView().isChecked() == true) goto L14;
            L16:
                SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$1.this$0)[0].filterType = 1;
            L17:
                SharedMediaLayout.m12886$$Nest$mchangeMediaFilterType(this.this$1.this$0);
                return;
            L14:
                if (this.val$showVideosItem.getCheckView().isChecked() == false) goto L16;
                SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$1.this$0)[0].filterType = 0;
                goto L17
            L8:
                if (this.val$showVideosItem.getCheckView().isChecked() == false) goto L11;
                ActionBarMenuSubItem r32 = this.val$showVideosItem;
                SharedMediaLayout r2 = this.this$1.this$0;
                float r0 = -SharedMediaLayout.m12847$$Nest$fgetshiftDp(r2);
                SharedMediaLayout.m12876$$Nest$fputshiftDp(r2, r0);
                AndroidUtilities.shakeViewSpring(r32, r0);
            }
        }

        /* renamed from: $r8$lambda$-3YThgjOtKyP0QekNGeAg0-yh-0, reason: not valid java name */
        public static /* synthetic */ void m12931$r8$lambda$3YThgjOtKyP0QekNGeAg0yh0(AnonymousClass5 r0) {
            r0.lambda$onClick$6();
        }

        public static /* synthetic */ void $r8$lambda$24ZE3DUZTPzVgNnvxzRtX1g6Dx4(AnonymousClass5 r0, String r1) {
            r0.lambda$onClick$12(r1);
        }

        /* renamed from: $r8$lambda$26Z-8qetgrM5pOeK6N3xmtdWj2U, reason: not valid java name */
        public static /* synthetic */ void m12932$r8$lambda$26Z8qetgrM5pOeK6N3xmtdWj2U(AnonymousClass5 r0, ItemOptions r1) {
            r0.lambda$onClick$15(r1);
        }

        public static /* synthetic */ void $r8$lambda$7bZxwSCWgD40e3qOFBCBsW2xxaA(AnonymousClass5 r0) {
            r0.lambda$onClick$11();
        }

        /* renamed from: $r8$lambda$AzT-XPn5lZLMZBWhuMvPla9POE4, reason: not valid java name */
        public static /* synthetic */ void m12933$r8$lambda$AzTXPn5lZLMZBWhuMvPla9POE4(AnonymousClass5 r0, ActionBarMenuSubItem r1, ActionBarMenuSubItem r2, StoriesAdapter r3, View r4) {
            r0.lambda$onClick$16(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$F3SgSd6tUFWmsMxvDiceHCz66lA(AnonymousClass5 r0) {
            r0.lambda$onClick$4();
        }

        /* renamed from: $r8$lambda$JBajY-HoPyOKkiPLAQC0Hm00xEc, reason: not valid java name */
        public static /* synthetic */ void m12934$r8$lambda$JBajYHoPyOKkiPLAQC0Hm00xEc(AnonymousClass5 r0) {
            r0.lambda$onClick$8();
        }

        public static /* synthetic */ void $r8$lambda$KImZ5nIKwbEFSI19OVp7uclJejI(AnonymousClass5 r0, ItemOptions r1) {
            r0.lambda$onClick$1(r1);
        }

        /* renamed from: $r8$lambda$NV-wxqOdBLGrapZvj1RW7iBFcnQ, reason: not valid java name */
        public static /* synthetic */ void m12935$r8$lambda$NVwxqOdBLGrapZvj1RW7iBFcnQ(AnonymousClass5 r0, ItemOptions r1) {
            r0.lambda$onClick$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$NuEGG7ukJe6k_dJ7KSJLrK3WDqQ(AnonymousClass5 r0) {
            r0.lambda$onClick$5();
        }

        /* renamed from: $r8$lambda$Oo-y6b6Rm4DLzXnLyQs6nDDs_M8, reason: not valid java name */
        public static /* synthetic */ void m12936$r8$lambda$Ooy6b6Rm4DLzXnLyQs6nDDs_M8(AnonymousClass5 r0) {
            r0.lambda$onClick$7();
        }

        /* renamed from: $r8$lambda$QoBLcpFC-7KXPjFJ4VZizTuExeM, reason: not valid java name */
        public static /* synthetic */ void m12937$r8$lambda$QoBLcpFC7KXPjFJ4VZizTuExeM(StarsController.GiftsList r1, Runnable r2, View r3) {
            r1.sort_by_date = !r1.sort_by_date;
            r2.run();
            r1.invalidate(true);
        }

        public static /* synthetic */ void $r8$lambda$SnfKaJ_hFOWoYueR4EwLh631Kjs(ActionBarMenuSubItem r2, StarsController.GiftsList r3, ActionBarMenuSubItem r4, ActionBarMenuSubItem r5, ActionBarMenuSubItem r6, ActionBarMenuSubItem r7, boolean r8, ActionBarMenuSubItem r9, ActionBarMenuSubItem r10) {
            if (r2 != null) goto L4;
        L12:
            r4.setChecked(r3.isInclude_unlimited());
            r5.setChecked(r3.isInclude_limited());
            r6.setChecked(r3.isInclude_upgradable());
            r7.setChecked(r3.isInclude_unique());
            if (r8 == false) goto L16;
            r9.setChecked(r3.isInclude_displayed());
            r10.setChecked(r3.isInclude_hidden());
            return;
        L16:
            return;
        L4:
            if (r3.sort_by_date == false) goto L6;
            int r0 = R.string.Gift2FilterSortByValue;
        L7:
            String r02 = LocaleController.getString(r0);
            if (r3.sort_by_date == false) goto L10;
            int r1 = R.drawable.menu_sort_value;
        L11:
            r2.setTextAndIcon(r02, r1);
            goto L12
        L10:
            r1 = R.drawable.menu_sort_date;
            goto L11
        L6:
            r0 = R.string.Gift2FilterSortByDate;
            goto L7
        }

        public static /* synthetic */ void $r8$lambda$WuNiIWKL30sYGMaNPSO2t3qGleY(AnonymousClass5 r0) {
            r0.lambda$onClick$9();
        }

        public static /* synthetic */ void $r8$lambda$bJhnDyIdyORZTL1guWFU4h1vo5A(AnonymousClass5 r0, int r1, ItemOptions r2) {
            r0.lambda$onClick$14(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$c75IZnt4ZgvxXUypuOJBPl4f9Yw(AnonymousClass5 r0, ActionBarMenuSubItem r1, ActionBarMenuSubItem r2, StoriesAdapter r3, View r4) {
            r0.lambda$onClick$17(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$jvRbsf1ymogqqNYxnuMM6jNLHGI(AnonymousClass5 r0, TLRPC.User r1, boolean r2) {
            r0.lambda$onClick$10(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$ndOqPO1cdw_xeHhRdrooNw7qUQk(SharedMediaLayout r0, StoriesController.StoryAlbum r1) {
            SharedMediaLayout.m12902$$Nest$monStoryAlbumCreate(r0, r1);
        }

        /* renamed from: $r8$lambda$y_zz1PqxOnHPnVGlAh9sw-PS5uQ, reason: not valid java name */
        public static /* synthetic */ void m12938$r8$lambda$y_zz1PqxOnHPnVGlAh9swPS5uQ(AnonymousClass5 r0, Theme.ResourcesProvider r1, ItemOptions r2) {
            r0.lambda$onClick$13(r1, r2);
        }

        public AnonymousClass5(SharedMediaLayout r1, long r2, Theme.ResourcesProvider r4, Context r5) {
            this.this$0 = r1;
            this.val$did = r2;
            this.val$resourcesProvider = r4;
            this.val$context = r5;
        }

        private /* synthetic */ void lambda$onClick$0(ItemOptions r1) {
            this.this$0.giftsContainer.createCollection();
            r1.dismiss();
        }

        private /* synthetic */ void lambda$onClick$1(ItemOptions r2) {
            this.this$0.giftsContainer.setReordering(true);
            r2.dismiss();
        }

        private /* synthetic */ void lambda$onClick$10(TLRPC.User r6, boolean r7) {
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).finishFragment();
            if ((SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0) instanceof NotificationCenter.NotificationCenterDelegate) == false) goto L5;
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getNotificationCenter().removeObserver((NotificationCenter.NotificationCenterDelegate) SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0), NotificationCenter.closeChats);
        L5:
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getNotificationCenter().postNotificationName(NotificationCenter.closeChats, new Object[0]);
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getNotificationCenter().postNotificationName(NotificationCenter.needDeleteDialog, new Object[]{Long.valueOf(SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0)), r6, null, Boolean.valueOf(r7)});
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getMessagesController().setSavedViewAs(false);
        }

        private /* synthetic */ void lambda$onClick$11() {
            TLRPC.User r4 = SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getUserConfig().getCurrentUser();
            AlertsCreator.createClearOrDeleteDialogAlert(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0), false, null, r4, false, true, false, true, new SharedMediaLayout$5$$ExternalSyntheticLambda17(this, r4));
        }

        private /* synthetic */ void lambda$onClick$12(String r5) {
            SharedMediaLayout.m12896$$Nest$mgetStoriesController(this.this$0).createAlbum(SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0), r5, new SharedMediaLayout$5$$ExternalSyntheticLambda18(this.this$0));
        }

        private /* synthetic */ void lambda$onClick$13(Theme.ResourcesProvider r4, ItemOptions r5) {
            AlertsCreator.createStoriesAlbumEnterNameForCreate(this.this$0.getContext(), SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0), r4, new SharedMediaLayout$5$$ExternalSyntheticLambda16(this));
            r5.dismiss();
        }

        private /* synthetic */ void lambda$onClick$14(int r2, ItemOptions r3) {
            this.this$0.showMediaCalendar(r2, false);
            r3.dismiss();
        }

        private /* synthetic */ void lambda$onClick$15(ItemOptions r5) {
            Bundle r0 = new Bundle();
            r0.putInt("type", 2);
            r0.putLong("dialog_id", -SharedMediaLayout.m12822$$Nest$fgetinfo(this.this$0).id);
            MediaActivity r1 = new MediaActivity(r0, null);
            r1.setChatInfo(SharedMediaLayout.m12822$$Nest$fgetinfo(this.this$0));
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).presentFragment(r1);
            r5.dismiss();
        }

        private /* synthetic */ void lambda$onClick$16(ActionBarMenuSubItem r2, ActionBarMenuSubItem r3, StoriesAdapter r4, View r5) {
            if (SharedMediaLayout.m12807$$Nest$fgetchangeTypeAnimation(this.this$0) == false) goto L6;
            return;
        L6:
            if (r2.getCheckView().isChecked() == false) goto L8;
        L11:
            r3.getCheckView().setChecked(!r3.getCheckView().isChecked(), true);
            StoriesController.StoriesList r1 = r4.storiesList;
            if (r1 != null) goto L14;
            return;
        L14:
            r1.updateFilters(r3.getCheckView().isChecked(), r2.getCheckView().isChecked());
            return;
        L8:
            if (r3.getCheckView().isChecked() == false) goto L11;
            SharedMediaLayout r12 = this.this$0;
            float r22 = -SharedMediaLayout.m12847$$Nest$fgetshiftDp(r12);
            SharedMediaLayout.m12876$$Nest$fputshiftDp(r12, r22);
            AndroidUtilities.shakeViewSpring(r5, r22);
        }

        private /* synthetic */ void lambda$onClick$17(ActionBarMenuSubItem r2, ActionBarMenuSubItem r3, StoriesAdapter r4, View r5) {
            if (SharedMediaLayout.m12807$$Nest$fgetchangeTypeAnimation(this.this$0) == false) goto L6;
            return;
        L6:
            if (r2.getCheckView().isChecked() == false) goto L8;
        L11:
            r3.getCheckView().setChecked(!r3.getCheckView().isChecked(), true);
            StoriesController.StoriesList r1 = r4.storiesList;
            if (r1 != null) goto L14;
            return;
        L14:
            r1.updateFilters(r2.getCheckView().isChecked(), r3.getCheckView().isChecked());
            return;
        L8:
            if (r3.getCheckView().isChecked() == false) goto L11;
            SharedMediaLayout r12 = this.this$0;
            float r22 = -SharedMediaLayout.m12847$$Nest$fgetshiftDp(r12);
            SharedMediaLayout.m12876$$Nest$fputshiftDp(r12, r22);
            AndroidUtilities.shakeViewSpring(r5, r22);
        }

        private /* synthetic */ void lambda$onClick$4() {
            StoryRecorder.getInstance(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getParentActivity(), SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getCurrentAccount()).openBot(SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0), SharedMediaLayout.m12804$$Nest$fgetbotPreviewsContainer(this.this$0).getCurrentLang(), null);
        }

        private /* synthetic */ void lambda$onClick$5() {
            SharedMediaLayout.m12804$$Nest$fgetbotPreviewsContainer(this.this$0).selectAll();
        }

        private /* synthetic */ void lambda$onClick$6() {
            boolean r0 = SharedMediaLayout.m12804$$Nest$fgetbotPreviewsContainer(this.this$0).isSelectedAll();
            SharedMediaLayout r1 = this.this$0;
            if (r0 == false) goto L6;
            SharedMediaLayout.m12804$$Nest$fgetbotPreviewsContainer(r1).unselectAll();
            return;
        L6:
            SharedMediaLayout.m12804$$Nest$fgetbotPreviewsContainer(r1).selectAll();
        }

        private /* synthetic */ void lambda$onClick$7() {
            SharedMediaLayout.m12804$$Nest$fgetbotPreviewsContainer(this.this$0).deleteLang(SharedMediaLayout.m12804$$Nest$fgetbotPreviewsContainer(this.this$0).getCurrentLang());
        }

        private /* synthetic */ void lambda$onClick$8() {
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getMessagesController().setSavedViewAs(false);
            Bundle r0 = new Bundle();
            r0.putLong("user_id", SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getUserConfig().getClientUserId());
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).presentFragment(new ChatActivity(r0), true);
        }

        private /* synthetic */ void lambda$onClick$9() {
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getMediaDataController().installShortcut(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getUserConfig().getClientUserId(), MediaDataController.SHORTCUT_TYPE_USER_OR_CHAT);     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r21) {
            int r1 = this.this$0.getClosestTab();
            boolean r2 = SharedMediaLayout.isAnyStoryPageType(r1);
            TLRPC.User r3 = MessagesController.getInstance(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getCurrentAccount()).getUser(Long.valueOf(SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0)));
            boolean r4 = SharedMediaLayout.m12896$$Nest$mgetStoriesController(this.this$0).canEditStoryAlbums(SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0));
            if (SharedMediaLayout.isStoryAlbumPageType(r1) == false) goto L9;
            if (r4 == false) goto L9;
            StoryAlbumData r5 = SharedMediaLayout.m12913$$Nest$mstoryAlbums_getByTabType(this.this$0, r1);
            if (r5 == null) goto L9;
            int r12 = r5.albumId;
            SharedMediaLayout r7 = this.this$0;
            SharedMediaLayout.m12885$$Nest$mbuildItemOptionsForStoryAlbumActionBar(r7, SharedMediaLayout.m12834$$Nest$fgetprofileActivity(r7), this.this$0.photoVideoOptionsItem, this.val$did, r12).setOnTopOfScrim().setDimAlpha(0).show();
            return;
        L9:
            boolean r9 = true;
            if (r1 != 14) goto L44;
            ProfileGiftsContainer.Page r13 = this.this$0.giftsContainer.getCurrentPage();
            StarsController.GiftsList r122 = r13.list;
            if (r122 != null) goto L14;
            return;
        L14:
            boolean r17 = this.this$0.giftsContainer.canFilterHidden();
            ItemOptions r22 = ItemOptions.makeOptions(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0), this.this$0.photoVideoOptionsItem);
            if (r122.isCollection == true) goto L17;
            ActionBarMenuSubItem r11 = r22.add();
            boolean r32 = true;
        L19:
            if (this.this$0.giftsContainer.canAdd() == false) goto L22;
            r22.add(R.drawable.menu_folder_add, LocaleController.getString(R.string.Gift2NewCollection), new SharedMediaLayout$5$$ExternalSyntheticLambda0(this, r22));
            r32 = true;
        L22:
            if (this.this$0.giftsContainer.collections.isMine() == true) goto L24;
        L29:
            if (r32 == false) goto L31;
            r22.addGap();
        L31:
            ActionBarMenuSubItem r132 = r22.addChecked();
            r132.setText(LocaleController.getString(R.string.Gift2FilterUnlimited));
            ActionBarMenuSubItem r14 = r22.addChecked();
            r14.setText(LocaleController.getString(R.string.Gift2FilterLimited));
            ActionBarMenuSubItem r15 = r22.addChecked();
            r15.setText(LocaleController.getString(R.string.Gift2FilterUpgradable));
            ActionBarMenuSubItem r0 = r22.addChecked();
            r0.setText(LocaleController.getString(R.string.Gift2FilterUnique));
            if (r17 == false) goto L34;
            r22.addGap();
            ActionBarMenuSubItem r42 = r22.addChecked();
            r42.setText(LocaleController.getString(R.string.Gift2FilterDisplayed));
            ActionBarMenuSubItem r16 = r22.addChecked();
            r16.setText(LocaleController.getString(R.string.Gift2FilterHidden));
            ActionBarMenuSubItem r19 = r16;
            ActionBarMenuSubItem r18 = r42;
        L35:
            SharedMediaLayout$5$$ExternalSyntheticLambda8 r10 = new SharedMediaLayout$5$$ExternalSyntheticLambda8(r11, r122, r132, r14, r15, r0, r17, r18, r19);
            ActionBarMenuSubItem r43 = r18;
            ActionBarMenuSubItem r110 = r19;
            r10.run();
            if (r11 == null) goto L38;
            r11.setOnClickListener(new SharedMediaLayout$5$$ExternalSyntheticLambda9(r122, r10));
        L38:
            ProfileGiftsContainer.setGiftFilterOptionsClickListeners(r132, r122, r10, 1);
            ProfileGiftsContainer.setGiftFilterOptionsClickListeners(r14, r122, r10, 2);
            ProfileGiftsContainer.setGiftFilterOptionsClickListeners(r15, r122, r10, 4);
            ProfileGiftsContainer.setGiftFilterOptionsClickListeners(r0, r122, r10, 8);
            if (r17 == false) goto L41;
            ProfileGiftsContainer.setGiftFilterOptionsClickListeners(r43, r122, r10, 256);
            ProfileGiftsContainer.setGiftFilterOptionsClickListeners(r110, r122, r10, 512);
        L41:
            r22.setOnTopOfScrim().setDismissWithButtons(false).setDimAlpha(0).show();
            return;
        L34:
            r18 = null;
            r19 = null;
            goto L35
        L24:
            if (r122.getPinned().isEmpty() == true) goto L26;
        L27:
            r22.add(R.drawable.tabs_reorder, LocaleController.getString(R.string.Gift2Reorder), new SharedMediaLayout$5$$ExternalSyntheticLambda7(this, r22));
        L28:
            r32 = true;
            goto L29
        L26:
            if (r13.isCollection == false) goto L28;
        L17:
            r11 = null;
            r32 = false;
            goto L19
        L44:
            if (r1 != 13) goto L74;
            if (r3 == null) goto L74;
            if (r3.bot == false) goto L74;
            if (r3.bot_has_main_app == false) goto L74;
            if (r3.bot_can_edit == false) goto L74;
            if (SharedMediaLayout.m12804$$Nest$fgetbotPreviewsContainer(this.this$0) == null) goto L74;
            ItemOptions r111 = ItemOptions.makeOptions(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0), this.this$0.photoVideoOptionsItem);
            if (SharedMediaLayout.m12804$$Nest$fgetbotPreviewsContainer(this.this$0).getItemsCount() >= SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getMessagesController().botPreviewMediasMax) goto L57;
            boolean r23 = true;
        L58:
            ItemOptions r112 = r111.addIf(r23, R.drawable.msg_addbot, LocaleController.getString(R.string.ProfileBotAddPreview), new SharedMediaLayout$5$$ExternalSyntheticLambda10(this));
            if (SharedMediaLayout.m12804$$Nest$fgetbotPreviewsContainer(this.this$0).getItemsCount() > 1) goto L61;
        L63:
            boolean r24 = false;
        L64:
            ItemOptions r113 = r112.addIf(r24, R.drawable.tabs_reorder, LocaleController.getString(R.string.ProfileBotReorder), new SharedMediaLayout$5$$ExternalSyntheticLambda11(this));
            if (SharedMediaLayout.m12804$$Nest$fgetbotPreviewsContainer(this.this$0).getItemsCount() <= 0) goto L67;
            boolean r25 = true;
        L68:
            int r33 = R.drawable.msg_select;
            if (SharedMediaLayout.m12804$$Nest$fgetbotPreviewsContainer(this.this$0).isSelectedAll() == false) goto L71;
            int r44 = R.string.ProfileBotUnSelect;
        L72:
            r113.addIf(r25, r33, LocaleController.getString(r44), new SharedMediaLayout$5$$ExternalSyntheticLambda12(this)).addIf(!TextUtils.isEmpty(SharedMediaLayout.m12804$$Nest$fgetbotPreviewsContainer(this.this$0).getCurrentLang()), R.drawable.msg_delete, LocaleController.formatString(R.string.ProfileBotRemoveLang, new Object[]{TranslateAlert2.languageName(SharedMediaLayout.m12804$$Nest$fgetbotPreviewsContainer(this.this$0).getCurrentLang())}), true, new SharedMediaLayout$5$$ExternalSyntheticLambda13(this)).translate(0.0f, -AndroidUtilities.dp(52.0f)).setDimAlpha(0).show();
            return;
        L71:
            r44 = R.string.ProfileBotSelect;
            goto L72
        L67:
            r25 = false;
            goto L68
        L61:
            if (SharedMediaLayout.m12804$$Nest$fgetbotPreviewsContainer(this.this$0).isSelectedAll() == true) goto L63;
            r24 = true;
            goto L64
        L57:
            r23 = false;
        L74:
            int r52 = this.this$0.getSelectedTab();
            SharedMediaLayout r123 = this.this$0;
            if (r52 != 11) goto L78;
            ItemOptions.makeOptions(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(r123), this.this$0.photoVideoOptionsItem).add(R.drawable.msg_discussion, LocaleController.getString(R.string.SavedViewAsMessages), new SharedMediaLayout$5$$ExternalSyntheticLambda14(this)).addGap().add(R.drawable.msg_home, LocaleController.getString(R.string.AddShortcut), new SharedMediaLayout$5$$ExternalSyntheticLambda15(this)).add(R.drawable.msg_delete, LocaleController.getString(R.string.DeleteAll), new SharedMediaLayout$5$$ExternalSyntheticLambda1(this)).translate(0.0f, -AndroidUtilities.dp(52.0f)).setDimAlpha(0).show();
            return;
        L78:
            ItemOptions r53 = ItemOptions.makeOptions(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(r123), this.this$0.photoVideoOptionsItem);
            if (r1 != 8) goto L81;
        L82:
            if (r4 == false) goto L84;
            r53.add(R.drawable.menu_album_add, LocaleController.getString(R.string.StoriesAlbumAddAlbum), new SharedMediaLayout$5$$ExternalSyntheticLambda2(this, this.val$resourcesProvider, r53));
            r53.addGap();
        L84:
            SharedMediaLayout.m12883$$Nest$maddZoomInZoomOutItemOptions(this.this$0, r53);
            if (r2 == false) goto L87;
        L98:
            boolean r45 = true;
        L100:
            if (DialogObject.isEncryptedDialog(SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0)) == true) goto L138;
            if (r3 != null) goto L103;
        L104:
            r53.add(R.drawable.msg_calendar2, LocaleController.getString(R.string.Calendar), new SharedMediaLayout$5$$ExternalSyntheticLambda3(this, r1, r53));
            if (SharedMediaLayout.m12822$$Nest$fgetinfo(this.this$0) != null) goto L107;
        L115:
            if (r45 == false) goto L138;
            r53.addGap();
            ActionBarMenuSubItem r114 = new ActionBarMenuSubItem(this.val$context, true, false, false, this.val$resourcesProvider);
            ActionBarMenuSubItem r124 = new ActionBarMenuSubItem(this.val$context, true, false, true, this.val$resourcesProvider);
            r114.setTextAndIcon(LocaleController.getString("MediaShowPhotos", R.string.MediaShowPhotos), 0);
            r53.getLayout().addView(r114);
            r124.setTextAndIcon(LocaleController.getString("MediaShowVideos", R.string.MediaShowVideos), 0);
            r53.getLayout().addView(r124);
            SharedMediaLayout r34 = this.this$0;
            if (r2 == false) goto L125;
            StoriesAdapter r115 = SharedMediaLayout.m12914$$Nest$mstoryAlbums_getStoriesAdapterByTabType(r34, r1);
            if (r115 == null) goto L123;
            StoriesController.StoriesList r26 = r115.storiesList;
            if (r26 == null) goto L123;
            r114.setChecked(r26.showPhotos());
            r124.setChecked(r115.storiesList.showVideos());
        L123:
            r114.setOnClickListener(new SharedMediaLayout$5$$ExternalSyntheticLambda5(this, r124, r114, r115));
            r124.setOnClickListener(new SharedMediaLayout$5$$ExternalSyntheticLambda6(this, r114, r124, r115));
            goto L138
        L125:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(r34)[0].filterType != 0) goto L127;
        L130:
            boolean r116 = true;
        L131:
            r114.setChecked(r116);
            r114.setOnClickListener(new AnonymousClass1(this, r124, r114));
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].filterType != 0) goto L134;
        L137:
            r124.setChecked(r9);
            r124.setOnClickListener(new AnonymousClass2(this, r114, r124));
            goto L138
        L134:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].filterType == 2) goto L137;
            r9 = false;
            goto L137
        L127:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].filterType == 1) goto L130;
            r116 = false;
            goto L131
        L107:
            if (this.this$0.isStoriesView() == true) goto L115;
            TLRPC.Chat r35 = MessagesController.getInstance(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getCurrentAccount()).getChat(Long.valueOf(SharedMediaLayout.m12822$$Nest$fgetinfo(this.this$0).id));
            if (r35 == null) goto L115;
            TLRPC.TL_chatAdminRights r36 = r35.admin_rights;
            if (r36 == null) goto L115;
            if (r36.edit_stories == false) goto L115;
            r53.add(R.drawable.msg_archive, LocaleController.getString(R.string.OpenChannelArchiveStories), new SharedMediaLayout$5$$ExternalSyntheticLambda4(this, r53));
            goto L115
        L103:
            if (r3.bot == false) goto L104;
        L138:
            r53.setDismissWithButtons(false).setOnTopOfScrim().setDimAlpha(0).show();
            return;
        L87:
            if (SharedMediaData.m12990$$Nest$fgethasPhotos(SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0]) == false) goto L91;
            if (SharedMediaData.m12991$$Nest$fgethasVideos(SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0]) == true) goto L98;
        L91:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].endReached[0] == false) goto L98;
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].endReached[1] == false) goto L98;
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].startReached == false) goto L98;
            r45 = false;
            goto L100
        L81:
            if (SharedMediaLayout.isStoryAlbumPageType(r1) == false) goto L84;
            goto L82
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$50, reason: invalid class name */
    public class AnonymousClass50 extends ShareAlert {
        final /* synthetic */ SharedMediaLayout this$0;
        final /* synthetic */ BaseFragment val$fragment;

        public static /* synthetic */ void $r8$lambda$_pN4bikBfutB9mKbWdNEFWB3QIo(BaseFragment r8, LongSparseArray r9, int r10) {
            if ((r8 instanceof ChatActivity) == false) goto L7;
            UndoView r82 = ((ChatActivity) r8).getUndoView();
        L5:
            UndoView r0 = r82;
            if (r0 != null) goto L12;
            return;
        L12:
            if (r9.size() != 1) goto L15;
            r0.showWithAction(((TLRPC.Dialog) r9.valueAt(0)).id, 53, Integer.valueOf(r10));
            return;
        L15:
            r0.showWithAction(0, 53, Integer.valueOf(r10), Integer.valueOf(r9.size()), null, null);
            return;
        L7:
            if ((r8 instanceof ProfileActivity) == false) goto L9;
            r82 = ((ProfileActivity) r8).getUndoView();
            goto L5
        L9:
            r82 = null;
            goto L5
        }

        public AnonymousClass50(SharedMediaLayout r1, Context r2, ArrayList r3, String r4, boolean r5, String r6, boolean r7, Theme.ResourcesProvider r8, BaseFragment r9) {
            this.this$0 = r1;
            this.val$fragment = r9;
            super(r2, r3, r4, r5, r6, r7, r8);
        }

        @Override // org.telegram.ui.Components.ShareAlert
        public void onSend(LongSparseArray<TLRPC.Dialog> r1, int r2, TLRPC.TL_forumTopic r3, boolean r4) {
            AndroidUtilities.runOnUIThread(new SharedMediaLayout$50$$ExternalSyntheticLambda0(this.val$fragment, r1, r2), 100);
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$6, reason: invalid class name */
    public class AnonymousClass6 extends SharedPhotoVideoAdapter {
        final /* synthetic */ SharedMediaLayout this$0;

        public AnonymousClass6(SharedMediaLayout r1, Context r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            if (SharedMediaLayout.m12906$$Nest$mpostNotifyDataSetChangedSafely(this.this$0, this) == true) goto L13;
            super.notifyDataSetChanged();
            MediaPage r0 = SharedMediaLayout.m12893$$Nest$mgetMediaPage(this.this$0, 0);
            if (r0 != null) goto L8;
            return;
        L8:
            if (MediaPage.m12956$$Nest$fgetanimationSupportingListView(r0).getVisibility() != 0) goto L12;
            SharedMediaLayout r2 = this.this$0;
            SharedMediaLayout.m12900$$Nest$mnotifyDataSetChangedSafely(r2, SharedMediaLayout.m12795$$Nest$fgetanimationSupportingPhotoVideoAdapter(r2));
            return;
        L12:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$7, reason: invalid class name */
    public class AnonymousClass7 extends ChatActivityContainer {
        final /* synthetic */ SharedMediaLayout this$0;

        public AnonymousClass7(SharedMediaLayout r1, Context r2, INavigationLayout r3, Bundle r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.ChatActivityContainer
        public void onSearchLoadingUpdate(boolean r2) {
            if (SharedMediaLayout.m12840$$Nest$fgetsearchItem(this.this$0) == null) goto L6;
            SharedMediaLayout.m12840$$Nest$fgetsearchItem(this.this$0).setShowSearchProgress(r2);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$8, reason: invalid class name */
    public class AnonymousClass8 extends ViewOutlineProvider {
        final /* synthetic */ SharedMediaLayout this$0;

        public AnonymousClass8(SharedMediaLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r7, Outline r8) {
            r8.setRoundRect(0, 0, r7.getWidth(), r7.getHeight() + AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f));
        }
    }

    /* renamed from: org.telegram.ui.Components.SharedMediaLayout$9, reason: invalid class name */
    public class AnonymousClass9 extends StoriesAdapter {
        final /* synthetic */ SharedMediaLayout this$0;

        public AnonymousClass9(SharedMediaLayout r1, Context r2, boolean r3) {
            this.this$0 = r1;
            super(r1, r2, r3);
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.StoriesAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            MediaPage r0 = SharedMediaLayout.m12893$$Nest$mgetMediaPage(this.this$0, 8);
            if (r0 != null) goto L5;
        L7:
            if (r0 == null) goto L20;
            StickerEmptyView r02 = MediaPage.m12957$$Nest$fgetemptyView(r0);
            StoriesController.StoriesList r1 = this.storiesList;
            if (r1 != null) goto L11;
        L17:
            boolean r2 = false;
        L18:
            r02.showProgress(r2);
            return;
        L11:
            if (r1.isLoading() == false) goto L13;
        L16:
            r2 = true;
            goto L18
        L13:
            if (this.this$0.hasInternet() == false) goto L17;
            if (this.storiesList.getCount() <= 0) goto L17;
        L20:
            return;
        L5:
            if (MediaPage.m12956$$Nest$fgetanimationSupportingListView(r0).getVisibility() != 0) goto L7;
            SharedMediaLayout.m12796$$Nest$fgetanimationSupportingStoriesAdapter(this.this$0).notifyDataSetChanged();
            goto L7
        }
    }

    public class ChannelRecommendationsAdapter extends RecyclerListView.SelectionAdapter {
        private final ArrayList<TLObject> chats;
        private final Context mContext;
        private int more;
        final /* synthetic */ SharedMediaLayout this$0;

        public static /* synthetic */ void $r8$lambda$D199DDwdgZYPcV4F_iQbQ3shxpE(ChannelRecommendationsAdapter r0, View r1) {
            r0.lambda$openPreview$1(r1);
        }

        /* renamed from: $r8$lambda$HI1a2_HBGTwup9G7_F8tiW4o-3A, reason: not valid java name */
        public static /* synthetic */ void m12939$r8$lambda$HI1a2_HBGTwup9G7_F8tiW4o3A(ChannelRecommendationsAdapter r0, TLRPC.Chat r1) {
            r0.lambda$openPreview$2(r1);
        }

        /* renamed from: $r8$lambda$fdN-ZEr6du8hCdyZ1uZWXe2VwC4, reason: not valid java name */
        public static /* synthetic */ void m12940$r8$lambda$fdNZEr6du8hCdyZ1uZWXe2VwC4(ChannelRecommendationsAdapter r0, TLRPC.Chat r1, int r2, View r3) {
            r0.lambda$openPreview$3(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$joquH4bmUk4dwv-n8Fpa0qmD0nU, reason: not valid java name */
        public static /* synthetic */ void m12941$r8$lambda$joquH4bmUk4dwvn8Fpa0qmD0nU(ChannelRecommendationsAdapter r0) {
            r0.lambda$onCreateViewHolder$0();
        }

        /* renamed from: -$$Nest$fgetchats, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m12942$$Nest$fgetchats(ChannelRecommendationsAdapter r0) {
            return r0.chats;
        }

        /* renamed from: -$$Nest$fgetmore, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m12943$$Nest$fgetmore(ChannelRecommendationsAdapter r0) {
            return r0.more;
        }

        public ChannelRecommendationsAdapter(SharedMediaLayout r1, Context r2) {
            this.this$0 = r1;
            this.chats = new ArrayList();
            this.mContext = r2;
            update(false);
        }

        private /* synthetic */ void lambda$onCreateViewHolder$0() {
            if (SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0) == null) goto L6;
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).presentFragment(new PremiumPreviewFragment("similar_channels"));
            return;
        }

        private /* synthetic */ void lambda$openPreview$1(View r1) {
            if (SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0) != null) goto L5;
            return;
        L5:
            if (SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getParentLayout() == null) goto L9;
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getParentLayout().expandPreviewFragment();
            return;
        }

        private /* synthetic */ void lambda$openPreview$2(TLRPC.Chat r3) {
            BulletinFactory r2 = BulletinFactory.of(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0));
            int r0 = R.raw.contact_check;
            int r1 = R.string.YouJoinedChannel;
            if (r3 != null) goto L5;
            String r32 = _UrlKt.FRAGMENT_ENCODE_SET;
        L6:
            r2.createSimpleBulletin(r0, LocaleController.formatString(r1, new Object[]{r32})).show(true);
            return;
        L5:
            r32 = r3.title;
            goto L6
        }

        private /* synthetic */ void lambda$openPreview$3(TLRPC.Chat r9, int r10, View r11) {
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).finishPreviewFragment();
            r9.left = false;
            update(false);
            notifyItemRemoved(r10);
            if (this.chats.isEmpty() == false) goto L5;
            this.this$0.updateTabs(true);
            SharedMediaLayout.m12887$$Nest$mcheckCurrentTabValid(this.this$0);
        L5:
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getNotificationCenter().postNotificationName(NotificationCenter.channelRecommendationsLoaded, new Object[]{Long.valueOf(-SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0))});
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getMessagesController().addUserToChat(r9.id, SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getUserConfig().getCurrentUser(), 0, null, SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0), new SharedMediaLayout$ChannelRecommendationsAdapter$$ExternalSyntheticLambda3(this, r9));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.chats.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (this.more > 0) goto L5;
            return 17;
        L5:
            if (r2 != (getItemCount() - 1)) goto L10;
            return 18;
        L10:
            return 17;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r8, int r9) {
            if (r8.getItemViewType() != 17) goto L10;
            View r82 = r8.itemView;
            if ((r82 instanceof ProfileSearchCell) == false) goto L24;
            ProfileSearchCell r83 = (ProfileSearchCell) r82;
        L8:
            ProfileSearchCell r0 = r83;
            if (r0 == null) goto L23;
            r0.setData(this.chats.get(r9), null, null, null, false, false);
            boolean r84 = true;
            if (r9 != (this.chats.size() - 1)) goto L21;
            r84 = false;
        L21:
            r0.useSeparator = r84;
            return;
        L23:
            return;
        L24:
            return;
        L10:
            if (r8.getItemViewType() != 18) goto L15;
            View r85 = r8.itemView;
            if ((r85 instanceof MoreRecommendationsCell) == false) goto L25;
            r83 = ((MoreRecommendationsCell) r85).channelCell;
            goto L8
        L25:
            return;
        L15:
            r83 = null;
            goto L8
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r7, int r8) {
            if (r8 == 18) goto L5;
            View r0 = new ProfileSearchCell(this.mContext, SharedMediaLayout.m12835$$Nest$fgetresourcesProvider(this.this$0));
        L16:
            r0.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r0);
        L5:
            if (SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0) != null) goto L8;
            int r72 = UserConfig.selectedAccount;
        L7:
            int r1 = r72;
            Context r2 = this.mContext;
            if (SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0) <= 0) goto L13;
            boolean r73 = true;
        L12:
            boolean r3 = r73;
            r0 = new MoreRecommendationsCell(r1, r2, r3, SharedMediaLayout.m12835$$Nest$fgetresourcesProvider(this.this$0), new SharedMediaLayout$ChannelRecommendationsAdapter$$ExternalSyntheticLambda0(this));
            goto L16
        L13:
            r73 = false;
            goto L12
        L8:
            r72 = SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getCurrentAccount();
            goto L7
        }

        public void openPreview(int r9) {
            if (r9 >= 0) goto L4;
            return;
        L4:
            if (r9 >= this.chats.size()) goto L26;
            TLObject r0 = this.chats.get(r9);
            Bundle r1 = new Bundle();
            boolean r2 = r0 instanceof TLRPC.Chat;
            if (r2 == false) goto L10;
            r1.putLong("chat_id", ((TLRPC.Chat) r0).id);
        L12:
            ChatActivity r3 = new ChatActivity(r1);
            if ((SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0) instanceof ProfileActivity) == false) goto L15;
            ((ProfileActivity) SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0)).prepareBlurBitmap();
        L15:
            ActionBarPopupWindow.ActionBarPopupWindowLayout r12 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(this.this$0.getContext(), R.drawable.popup_fixed_alert, SharedMediaLayout.m12835$$Nest$fgetresourcesProvider(this.this$0), 2);
            r12.setBackgroundColor(SharedMediaLayout.m12897$$Nest$mgetThemedColor(this.this$0, Theme.key_actionBarDefaultSubmenuBackground));
            if (r2 == false) goto L20;
            ActionBarMenuSubItem r22 = new ActionBarMenuSubItem(this.this$0.getContext(), false, false);
            r22.setTextAndIcon(LocaleController.getString(R.string.OpenChannel2), R.drawable.msg_channel);
            r22.setMinimumWidth(160);
            r22.setOnClickListener(new SharedMediaLayout$ChannelRecommendationsAdapter$$ExternalSyntheticLambda1(this));
            r12.addView(r22);
            ActionBarMenuSubItem r23 = new ActionBarMenuSubItem(this.this$0.getContext(), false, false);
            r23.setTextAndIcon(LocaleController.getString(R.string.ProfileJoinChannel), R.drawable.msg_addbot);
            r23.setMinimumWidth(160);
            r23.setOnClickListener(new SharedMediaLayout$ChannelRecommendationsAdapter$$ExternalSyntheticLambda2(this, (TLRPC.Chat) r0, r9));
            r12.addView(r23);
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).presentFragmentAsPreviewWithMenu(r3, r12);
            return;
        L20:
            if ((r0 instanceof TLRPC.User) == false) goto L25;
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).presentFragmentAsPreview(r3);
            return;
        L25:
            return;
        L10:
            if ((r0 instanceof TLRPC.User) == false) goto L24;
            r1.putLong("user_id", ((TLRPC.User) r0).id);
            goto L12
        L24:
            return;
        }

        public void update(boolean r6) {
            if (SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0) == null) goto L42;
            boolean r0 = DialogObject.isChatDialog(SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0));
            SharedMediaLayout r1 = this.this$0;
            if (r0 == false) goto L13;
            TLRPC.Chat r02 = MessagesController.getInstance(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(r1).getCurrentAccount()).getChat(Long.valueOf(-SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0)));
            if (r02 != null) goto L10;
            return;
        L10:
            if (ChatObject.isChannelAndNotMegaGroup(r02) == false) goto L43;
        L15:
            MessagesController.ChannelRecommendations r03 = MessagesController.getInstance(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getCurrentAccount()).getChannelRecommendations(SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0));
            this.chats.clear();
            int r12 = 0;
            if (r03 == null) goto L28;
            int r2 = 0;
        L19:
            if (r2 >= r03.chats.size()) goto L28;
            TLObject r3 = r03.chats.get(r2);
            if ((r3 instanceof TLRPC.Chat) == true) goto L23;
        L25:
            this.chats.add(r3);
        L26:
            r2 = r2 + 1;
            goto L19
        L23:
            if (ChatObject.isNotInChat((TLRPC.Chat) r3) == false) goto L25;
            this.chats.add(r3);
        L28:
            if (this.chats.isEmpty() == false) goto L30;
        L33:
            this.more = r12;
            if (r6 == false) goto L41;
            notifyDataSetChanged();
            return;
        L41:
            return;
        L30:
            if (UserConfig.getInstance(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getCurrentAccount()).isPremium() == true) goto L33;
            r12 = r03.more;
            goto L33
        L43:
            return;
        L13:
            if (MessagesController.getInstance(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(r1).getCurrentAccount()).getUser(Long.valueOf(SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0))) != null) goto L15;
            return;
        }
    }

    public class ChatUsersAdapter extends RecyclerListView.SelectionAdapter {
        private TLRPC.ChatFull chatInfo;
        private Context mContext;
        private ArrayList<Integer> sortedUsers;
        final /* synthetic */ SharedMediaLayout this$0;

        public static /* synthetic */ void $r8$lambda$2pYbEUPZfyuQhjSyq9J59xcQ0wE(ChatUsersAdapter r0, TLRPC.User r1, String r2, boolean r3, boolean r4, boolean r5, View r6) {
            r0.lambda$onBindViewHolder$0(r1, r2, r3, r4, r5, r6);
        }

        /* renamed from: -$$Nest$fgetchatInfo, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.ChatFull m12944$$Nest$fgetchatInfo(ChatUsersAdapter r0) {
            return r0.chatInfo;
        }

        /* renamed from: -$$Nest$fgetsortedUsers, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m12945$$Nest$fgetsortedUsers(ChatUsersAdapter r0) {
            return r0.sortedUsers;
        }

        /* renamed from: -$$Nest$fputchatInfo, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12946$$Nest$fputchatInfo(ChatUsersAdapter r0, TLRPC.ChatFull r1) {
            r0.chatInfo = r1;
        }

        /* renamed from: -$$Nest$fputsortedUsers, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12947$$Nest$fputsortedUsers(ChatUsersAdapter r0, ArrayList r1) {
            r0.sortedUsers = r1;
        }

        public ChatUsersAdapter(SharedMediaLayout r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        private /* synthetic */ void lambda$onBindViewHolder$0(TLRPC.User r12, String r13, boolean r14, boolean r15, boolean r16, View r17) {
            TagEditCell.showInfoSheet(this.this$0.getContext(), SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getCurrentAccount(), SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0), r12, r13, r14, r15, r16, SharedMediaLayout.m12835$$Nest$fgetresourcesProvider(this.this$0));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            TLRPC.ChatFull r0 = this.chatInfo;
            if (r0 != null) goto L5;
        L8:
            TLRPC.ChatFull r1 = this.chatInfo;
            if (r1 != null) goto L11;
            return 0;
        L11:
            return r1.participants.participants.size();
        L5:
            if (r0.participants.participants.isEmpty() == false) goto L8;
            return 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            TLRPC.ChatFull r0 = this.chatInfo;
            if (r0 != null) goto L5;
            return 21;
        L5:
            if (r0.participants.participants.isEmpty() == false) goto L10;
            return 20;
        L10:
            return 21;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r17, int r18) {
            View r0 = r17.itemView;
            if ((r0 instanceof UserCell) == false) goto L60;
            UserCell r8 = (UserCell) r0;
            boolean r02 = this.sortedUsers.isEmpty();
            TLRPC.ChatFull r2 = this.chatInfo;
            if (r02 == true) goto L8;
            TLRPC.ChatParticipant r03 = r2.participants.participants.get(this.sortedUsers.get(r18).intValue());
        L9:
            if (r03 != null) goto L11;
            return;
        L11:
            if ((r03 instanceof TLRPC.TL_chatChannelParticipant) == false) goto L30;
            TLRPC.ChannelParticipant r22 = ((TLRPC.TL_chatChannelParticipant) r03).channelParticipant;
            String r5 = r22.rank;
            if ((r22 instanceof TLRPC.TL_channelParticipantCreator) == false) goto L19;
            if (TextUtils.isEmpty(r5) == false) goto L17;
            r5 = LocaleController.getString("ChannelCreator", R.string.ChannelCreator);
        L17:
            boolean r3 = true;
            boolean r4 = true;
            boolean r23 = false;
        L29:
            boolean r6 = r4;
            boolean r42 = r3;
            String r32 = r5;
            boolean r52 = r6;
            boolean r62 = r23;
        L47:
            TLRPC.User r24 = SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getMessagesController().getUser(Long.valueOf(r03.user_id));
            if (UserObject.isUserSelf(r24) == true) goto L50;
        L52:
            boolean r12 = false;
        L53:
            r8.setAdminRole(r32, r42, r52, r12, new SharedMediaLayout$ChatUsersAdapter$$ExternalSyntheticLambda0(this, r24, r32, r42, r52, r62));
            if (r18 == (this.chatInfo.participants.participants.size() - 1)) goto L56;
            boolean r82 = true;
        L57:
            r8.setData(r24, null, null, 0, r82);
            return;
        L56:
            r82 = false;
            goto L57
        L50:
            if (ChatObject.canManageMyTag(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getMessagesController().getChat(Long.valueOf(-SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0)))) == false) goto L52;
            r12 = true;
            goto L53
        L19:
            if ((r22 instanceof TLRPC.TL_channelParticipantAdmin) == true) goto L21;
            r23 = false;
            r3 = false;
            r4 = false;
            goto L29
        L21:
            if (TextUtils.isEmpty(r5) == false) goto L24;
            r5 = LocaleController.getString("ChannelAdmin", R.string.ChannelAdmin);
        L24:
            if (r22.promoted_by != SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getUserConfig().getClientUserId()) goto L26;
            r23 = true;
        L27:
            r3 = true;
            r4 = false;
            goto L29
        L26:
            r23 = false;
            goto L27
        L30:
            String r53 = r03.rank;
            if ((r03 instanceof TLRPC.TL_chatParticipantCreator) == false) goto L37;
            if (TextUtils.isEmpty(r53) == false) goto L35;
            r53 = LocaleController.getString("ChannelCreator", R.string.ChannelCreator);
        L35:
            r32 = r53;
            r42 = true;
            r52 = true;
            r62 = false;
            goto L47
        L37:
            if ((r03 instanceof TLRPC.TL_chatParticipantAdmin) == true) goto L39;
            r32 = r53;
            r42 = false;
            r52 = false;
            r62 = false;
            goto L47
        L39:
            if (TextUtils.isEmpty(r53) == false) goto L42;
            r53 = LocaleController.getString("ChannelAdmin", R.string.ChannelAdmin);
        L42:
            if (r03.inviter_id != SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getUserConfig().getClientUserId()) goto L44;
            boolean r25 = true;
        L45:
            r62 = r25;
            r32 = r53;
            r42 = true;
            r52 = false;
            goto L47
        L44:
            r25 = false;
            goto L45
        L8:
            r03 = r2.participants.participants.get(r18);
            goto L9
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r9, int r10) {
            if (r10 != 20) goto L6;
            View r8 = SharedMediaLayout.createEmptyStubView(this.mContext, 7, SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0), SharedMediaLayout.m12835$$Nest$fgetresourcesProvider(this.this$0));
            r8.setLayoutParams(new RecyclerView.LayoutParams(-1, -1));
            return new RecyclerListView.Holder(r8);
        L6:
            UserCell r1 = new UserCell(this.mContext, 9, 0, true, false, SharedMediaLayout.m12835$$Nest$fgetresourcesProvider(this.this$0));
            r1.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r1);
        }

        public void updateRank(long r3, String r5) {
            TLRPC.ChatFull r0 = this.chatInfo;
            if (r0 != null) goto L6;
            return;
        L6:
            if (r0.participants == null) goto L14;
            int r02 = 0;
        L10:
            if (r02 >= this.chatInfo.participants.participants.size()) goto L15;
            this.chatInfo.participants.participants.get(r02).setRank(r3, r5);
            r02 = r02 + 1;
            goto L10
        L15:
            return;
        }
    }

    public class CommonGroupsAdapter extends RecyclerListView.SelectionAdapter {
        private ArrayList<TLRPC.Chat> chats;
        private boolean endReached;
        private boolean firstLoaded;
        private boolean loading;
        private Context mContext;
        final /* synthetic */ SharedMediaLayout this$0;

        public static /* synthetic */ void $r8$lambda$RngUuOCFVQMEfSI6a6CIpzQAlgg(CommonGroupsAdapter r0, int r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$getChats$1(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$zJeW8FJUhjM6DYp1hem7fvC2Kv0(CommonGroupsAdapter r0, TLRPC.TL_error r1, TLObject r2, int r3) {
            r0.lambda$getChats$0(r1, r2, r3);
        }

        /* renamed from: -$$Nest$fgetchats, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m12948$$Nest$fgetchats(CommonGroupsAdapter r0) {
            return r0.chats;
        }

        /* renamed from: -$$Nest$fgetendReached, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m12949$$Nest$fgetendReached(CommonGroupsAdapter r0) {
            return r0.endReached;
        }

        /* renamed from: -$$Nest$fgetloading, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m12950$$Nest$fgetloading(CommonGroupsAdapter r0) {
            return r0.loading;
        }

        /* renamed from: -$$Nest$mgetChats, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12951$$Nest$mgetChats(CommonGroupsAdapter r0, long r1, int r3) {
            r0.getChats(r1, r3);
        }

        public CommonGroupsAdapter(SharedMediaLayout r1, Context r2) {
            this.this$0 = r1;
            this.chats = new ArrayList();
            this.mContext = r2;
        }

        private void getChats(long r5, int r7) {
            if (this.loading == true) goto L14;
            TLRPC.TL_messages_getCommonChats r0 = new TLRPC.TL_messages_getCommonChats();
            boolean r1 = DialogObject.isEncryptedDialog(SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0));
            SharedMediaLayout r2 = this.this$0;
            if (r1 == false) goto L8;
            long r12 = SharedMediaLayout.m12834$$Nest$fgetprofileActivity(r2).getMessagesController().getEncryptedChat(Integer.valueOf(DialogObject.getEncryptedChatId(SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0)))).user_id;
        L9:
            TLRPC.InputUser r13 = SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getMessagesController().getInputUser(r12);
            r0.user_id = r13;
            if ((r13 instanceof TLRPC.TL_inputUserEmpty) == false) goto L12;
            return;
        L12:
            r0.limit = r7;
            r0.max_id = r5;
            this.loading = true;
            notifyDataSetChanged();
            int r52 = SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getConnectionsManager().sendRequest(r0, new SharedMediaLayout$CommonGroupsAdapter$$ExternalSyntheticLambda0(this, r7));
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getConnectionsManager().bindRequestToGuid(r52, SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getClassGuid());
            return;
        L8:
            r12 = SharedMediaLayout.m12811$$Nest$fgetdialog_id(r2);
            goto L9
        }

        private /* synthetic */ void lambda$getChats$0(TLRPC.TL_error r5, TLObject r6, int r7) {
            int r0 = getItemCount();
            if (r5 != null) goto L12;
            TLRPC.messages_Chats r62 = (TLRPC.messages_Chats) r6;
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getMessagesController().putChats(r62.chats, false);
            if (r62.chats.isEmpty() == false) goto L7;
        L10:
            boolean r52 = true;
        L11:
            this.endReached = r52;
            this.chats.addAll(r62.chats);
        L13:
            int r53 = 0;
        L15:
            if (r53 >= SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0).length) goto L25;
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r53].selectedType != 6) goto L24;
            if (MediaPage.m12961$$Nest$fgetlistView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r53]) == null) goto L24;
            InternalListView r63 = MediaPage.m12961$$Nest$fgetlistView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r53]);
            if (this.firstLoaded == true) goto L23;
            if (r0 != 0) goto L24;
        L23:
            SharedMediaLayout.m12884$$Nest$manimateItemsEnter(this.this$0, r63, 0, null);
        L24:
            r53 = r53 + 1;
            goto L15
        L25:
            this.loading = false;
            this.firstLoaded = true;
            notifyDataSetChanged();
            return;
        L7:
            if (r62.chats.size() != r7) goto L10;
            r52 = false;
            goto L11
        L12:
            this.endReached = true;
            goto L13
        }

        private /* synthetic */ void lambda$getChats$1(int r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new SharedMediaLayout$CommonGroupsAdapter$$ExternalSyntheticLambda1(this, r4, r3, r2));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (this.chats.isEmpty() == true) goto L5;
        L7:
            int r0 = this.chats.size();
            if (this.chats.isEmpty() == false) goto L10;
            return r0;
        L10:
            if (this.endReached == false) goto L12;
            return r0;
        L12:
            return r0 + 1;
        L5:
            if (this.loading == true) goto L7;
            return 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (this.chats.isEmpty() == false) goto L9;
            if (this.loading == true) goto L9;
            return 15;
        L9:
            if (r2 >= this.chats.size()) goto L12;
            return 14;
        L12:
            return 16;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getAdapterPosition() == this.chats.size()) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r9, int r10) {
            if (r9.getItemViewType() != 14) goto L15;
            View r92 = r9.itemView;
            if ((r92 instanceof ProfileSearchCell) == false) goto L16;
            ProfileSearchCell r1 = (ProfileSearchCell) r92;
            r1.setData(this.chats.get(r10), null, null, null, false, false);
            boolean r0 = true;
            if (r10 == (this.chats.size() - 1)) goto L10;
        L13:
            r1.useSeparator = r0;
            return;
        L10:
            if (this.endReached == false) goto L13;
            r0 = false;
            goto L13
        L16:
            return;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r4, int r5) {
            if (r5 != 14) goto L5;
            ProfileSearchCell r42 = new ProfileSearchCell(this.mContext, SharedMediaLayout.m12835$$Nest$fgetresourcesProvider(this.this$0));
        L10:
            r42.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r42);
        L5:
            if (r5 == 15) goto L7;
            FlickerLoadingView r43 = new FlickerLoadingView(this.mContext, SharedMediaLayout.m12835$$Nest$fgetresourcesProvider(this.this$0));
            r43.setIsSingleCell(true);
            r43.showDate(false);
            r43.setViewType(1);
            r42 = r43;
            goto L10
        L7:
            View r3 = SharedMediaLayout.createEmptyStubView(this.mContext, 6, SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0), SharedMediaLayout.m12835$$Nest$fgetresourcesProvider(this.this$0));
            r3.setLayoutParams(new RecyclerView.LayoutParams(-1, -1));
            return new RecyclerListView.Holder(r3);
        }
    }

    public interface Delegate {
        boolean canSearchMembers();

        TLRPC.Chat getCurrentChat();

        RecyclerListView getListView();

        boolean isFragmentOpened();

        boolean onMemberClick(TLRPC.ChatParticipant r1, boolean r2, boolean r3, View r4);

        void scrollToSharedMedia();

        void updateSelectedMediaTabText();
    }

    public static class EmptyStubView extends LinearLayout {
        final ImageView emptyImageView;
        final TextView emptyTextView;
        boolean ignoreRequestLayout;

        public EmptyStubView(Context r11, Theme.ResourcesProvider r12) {
            super(r11);
            TextView r0 = new TextView(r11);
            this.emptyTextView = r0;
            ImageView r1 = new ImageView(r11);
            this.emptyImageView = r1;
            setOrientation(1);
            setGravity(17);
            addView(r1, LayoutHelper.createLinear(-2, -2));
            r0.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r12));
            r0.setGravity(17);
            r0.setTextSize(1, 17.0f);
            r0.setPadding(AndroidUtilities.dp(40.0f), 0, AndroidUtilities.dp(40.0f), AndroidUtilities.dp(128.0f));
            addView(r0, LayoutHelper.createLinear(-2, -2, 17, 0, 24, 0, 0));
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r7, int r8) {
            int r0 = ((WindowManager) ApplicationLoader.applicationContext.getSystemService("window")).getDefaultDisplay().getRotation();
            this.ignoreRequestLayout = true;
            if (AndroidUtilities.isTablet() == false) goto L6;
            this.emptyTextView.setPadding(AndroidUtilities.dp(40.0f), 0, AndroidUtilities.dp(40.0f), AndroidUtilities.dp(128.0f));
        L11:
            this.ignoreRequestLayout = false;
            super.onMeasure(r7, r8);
            return;
        L6:
            if (r0 == 3) goto L10;
            if (r0 == 1) goto L10;
            this.emptyTextView.setPadding(AndroidUtilities.dp(40.0f), 0, AndroidUtilities.dp(40.0f), AndroidUtilities.dp(128.0f));
        L10:
            this.emptyTextView.setPadding(AndroidUtilities.dp(40.0f), 0, AndroidUtilities.dp(40.0f), 0);
            goto L11
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreRequestLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    public class GifAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        final /* synthetic */ SharedMediaLayout this$0;

        public GifAdapter(SharedMediaLayout r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[5].messages.size() != 0) goto L9;
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[5].loading == true) goto L9;
            return 1;
        L9:
            return SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[5].messages.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int r1) {
            return r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[5].messages.size() == 0) goto L5;
            return 12;
        L5:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[5].loading == true) goto L10;
            return 11;
        L10:
            return 12;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r2) {
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[5].messages.size() == 0) goto L5;
            return true;
        L5:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[5].loading == true) goto L10;
            return false;
        L10:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r9, int r10) {
            if (r9.getItemViewType() != 12) goto L22;
            MessageObject r102 = SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[5].messages.get(r10);
            TLRPC.Document r0 = r102.getDocument();
            if (r0 == null) goto L23;
            View r92 = r9.itemView;
            if ((r92 instanceof ContextLinkCell) == false) goto L24;
            ContextLinkCell r93 = (ContextLinkCell) r92;
            boolean r2 = false;
            r93.setGif(r0, r102, r102.messageOwner.date, false);
            SharedMediaLayout r02 = this.this$0;
            if (r02.isActionModeShowed == false) goto L20;
            SparseArray[] r03 = SharedMediaLayout.m12844$$Nest$fgetselectedFiles(r02);
            if (r102.getDialogId() != SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0)) goto L14;
            char r1 = 0;
        L16:
            if (r03[r1].indexOfKey(r102.getId()) < 0) goto L18;
            r2 = true;
        L18:
            r93.setChecked(r2, !SharedMediaLayout.m12839$$Nest$fgetscrolling(this.this$0));
            return;
        L14:
            r1 = 1;
            goto L16
        L20:
            r93.setChecked(false, !SharedMediaLayout.m12839$$Nest$fgetscrolling(r02));
            return;
        L24:
            return;
        L23:
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            if (r4 != 11) goto L6;
            View r2 = SharedMediaLayout.createEmptyStubView(this.mContext, 5, SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0), SharedMediaLayout.m12835$$Nest$fgetresourcesProvider(this.this$0));
            r2.setLayoutParams(new RecyclerView.LayoutParams(-1, -1));
            return new RecyclerListView.Holder(r2);
        L6:
            ContextLinkCell r32 = new ContextLinkCell(this.mContext, true, SharedMediaLayout.m12835$$Nest$fgetresourcesProvider(this.this$0));
            r32.setCanPreviewGif(true);
            return new RecyclerListView.Holder(r32);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewAttachedToWindow(RecyclerView.ViewHolder r3) {
            View r32 = r3.itemView;
            if ((r32 instanceof ContextLinkCell) == false) goto L10;
            ImageReceiver r33 = ((ContextLinkCell) r32).getPhotoImage();
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0].selectedType != 5) goto L8;
            r33.setAllowStartAnimation(true);
            r33.startAnimation();
            return;
        L8:
            r33.setAllowStartAnimation(false);
            r33.stopAnimation();
            return;
        }
    }

    public class GroupUsersSearchAdapter extends RecyclerListView.SelectionAdapter {
        private TLRPC.Chat currentChat;
        private Context mContext;
        private SearchAdapterHelper searchAdapterHelper;
        int searchCount;
        private ArrayList<CharSequence> searchResultNames;
        private Runnable searchRunnable;
        final /* synthetic */ SharedMediaLayout this$0;
        private int totalCount;

        /* renamed from: $r8$lambda$-3CpCAYWmZRbSeduixjqK5SAk0U, reason: not valid java name */
        public static /* synthetic */ boolean m12952$r8$lambda$3CpCAYWmZRbSeduixjqK5SAk0U(GroupUsersSearchAdapter r0, ManageChatUserCell r1, boolean r2) {
            return r0.lambda$onCreateViewHolder$5(r1, r2);
        }

        /* renamed from: $r8$lambda$8h1lic7vzgs53zsSQjVdO-i5bPc, reason: not valid java name */
        public static /* synthetic */ void m12953$r8$lambda$8h1lic7vzgs53zsSQjVdOi5bPc(GroupUsersSearchAdapter r0, String r1, ArrayList r2) {
            r0.lambda$processSearch$2(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$HSKJJKKEpMxdNloHZ7aeYg_jvgI(GroupUsersSearchAdapter r0, int r1) {
            r0.lambda$new$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$XwS97mvOYBkbKuQ1V9ZZhflBXzU(GroupUsersSearchAdapter r0, ArrayList r1, ArrayList r2) {
            r0.lambda$updateSearchResults$4(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$_969Cl3roOIiR0aQAc9t1EYn9L4(GroupUsersSearchAdapter r0, String r1) {
            r0.lambda$search$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$zh73rDQEnU3md40ojb610bmnc48(GroupUsersSearchAdapter r0, String r1) {
            r0.lambda$processSearch$3(r1);
        }

        public GroupUsersSearchAdapter(SharedMediaLayout r2, Context r3) {
            this.this$0 = r2;
            this.searchResultNames = new ArrayList();
            this.totalCount = 0;
            this.searchCount = 0;
            this.mContext = r3;
            SearchAdapterHelper r32 = new SearchAdapterHelper(true);
            this.searchAdapterHelper = r32;
            r32.setDelegate(new SharedMediaLayout$GroupUsersSearchAdapter$$ExternalSyntheticLambda0(this));
            this.currentChat = r2.delegate.getCurrentChat();
        }

        private boolean createMenuForParticipant(TLObject r4, boolean r5, View r6) {
            if ((r4 instanceof TLRPC.ChannelParticipant) == false) goto L6;
            TLRPC.ChannelParticipant r42 = (TLRPC.ChannelParticipant) r4;
            TLRPC.TL_chatChannelParticipant r0 = new TLRPC.TL_chatChannelParticipant();
            r0.channelParticipant = r42;
            r0.user_id = MessageObject.getPeerId(r42.peer);
            r0.inviter_id = r42.inviter_id;
            r0.date = r42.date;
            r4 = r0;
        L6:
            return this.this$0.delegate.onMemberClick((TLRPC.ChatParticipant) r4, true, r5, r6);
        }

        private /* synthetic */ void lambda$new$0(int r6) {
            notifyDataSetChanged();
            if (r6 != 1) goto L16;
            int r62 = this.searchCount - 1;
            this.searchCount = r62;
            if (r62 != 0) goto L21;
            int r1 = 0;
        L8:
            if (r1 >= SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0).length) goto L22;
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r1].selectedType != 7) goto L15;
            int r2 = getItemCount();
            SharedMediaLayout r3 = this.this$0;
            if (r2 != 0) goto L14;
            MediaPage.m12957$$Nest$fgetemptyView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(r3)[r1]).showProgress(false, true);
            goto L15
        L14:
            SharedMediaLayout.m12884$$Nest$manimateItemsEnter(r3, MediaPage.m12961$$Nest$fgetlistView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(r3)[r1]), 0, null);
        L15:
            r1 = r1 + 1;
            goto L8
        L22:
            return;
        L21:
            return;
        }

        private /* synthetic */ boolean lambda$onCreateViewHolder$5(ManageChatUserCell r3, boolean r4) {
            TLObject r0 = getItem(((Integer) r3.getTag()).intValue());
            if ((r0 instanceof TLRPC.ChannelParticipant) == true) goto L5;
            return false;
        L5:
            return createMenuForParticipant((TLRPC.ChannelParticipant) r0, !r4, r3);
        }

        private /* synthetic */ void lambda$processSearch$2(String r19, ArrayList r20) {
            String r1 = r19.trim().toLowerCase();
            if (r1.length() != 0) goto L6;
            updateSearchResults(new ArrayList(), new ArrayList());
            return;
        L6:
            String r2 = LocaleController.getInstance().getTranslitString(r1);
            if (r1.equals(r2) == false) goto L9;
        L10:
            r2 = null;
        L11:
            int r3 = 0;
            if (r2 == null) goto L14;
            int r6 = 1;
        L15:
            int r62 = r6 + 1;
            String[] r7 = new String[r62];
            r7[0] = r1;
            if (r2 == null) goto L18;
            r7[1] = r2;
        L18:
            ArrayList<CharSequence> r12 = new ArrayList();
            ArrayList<TLObject> r22 = new ArrayList();
            int r8 = r20.size();
            int r9 = 0;
        L19:
            if (r9 >= r8) goto L59;
            TLObject r11 = (TLObject) r20.get(r9);
            if ((r11 instanceof TLRPC.ChatParticipant) == false) goto L24;
            long r122 = ((TLRPC.ChatParticipant) r11).user_id;
        L26:
            TLRPC.User r123 = SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getMessagesController().getUser(Long.valueOf(r122));
            if (r123.id == SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getUserConfig().getClientUserId()) goto L58;
            String r13 = UserObject.getUserName(r123).toLowerCase();
            String r14 = LocaleController.getInstance().getTranslitString(r13);
            if (r13.equals(r14) == false) goto L32;
            r14 = null;
        L32:
            int r15 = r3;
            int r16 = r15;
        L33:
            if (r15 >= r62) goto L58;
            String r32 = r7[r15];
            if (r13.startsWith(r32) == false) goto L37;
        L50:
            int r4 = 1;
        L51:
            if (r4 != 0) goto L53;
            r15 = r15 + 1;
            r16 = r4;
            goto L33
        L53:
            if (r4 != 1) goto L55;
            r12.add(AndroidUtilities.generateSearchName(r123.first_name, r123.last_name, r32));
            Object r124 = null;
        L56:
            r22.add(r11);
            goto L58
        L55:
            r124 = null;
            r12.add(AndroidUtilities.generateSearchName("@" + UserObject.getPublicUsername(r123), null, "@" + r32));
            goto L56
        L37:
            if (r13.contains(" " + r32) == true) goto L50;
            if (r14 != null) goto L40;
        L44:
            String r42 = UserObject.getPublicUsername(r123);
            if (r42 != null) goto L47;
        L49:
            r4 = r16;
            goto L51
        L47:
            if (r42.startsWith(r32) == false) goto L49;
            r16 = 2;
            goto L49
        L40:
            if (r14.startsWith(r32) == true) goto L50;
            if (r14.contains(" " + r32) == false) goto L44;
        L58:
            r9 = r9 + 1;
            r3 = 0;
            goto L19
        L24:
            if ((r11 instanceof TLRPC.ChannelParticipant) == false) goto L58;
            r122 = MessageObject.getPeerId(((TLRPC.ChannelParticipant) r11).peer);
            goto L26
        L59:
            updateSearchResults(r12, r22);
            return;
        L14:
            r6 = 0;
            goto L15
        L9:
            if (r2.length() != 0) goto L11;
            goto L10
        }

        private /* synthetic */ void lambda$processSearch$3(String r15) {
            ArrayList r0 = null;
            this.searchRunnable = null;
            if (ChatObject.isChannel(this.currentChat) == false) goto L5;
        L7:
            this.searchCount = 2;
            if (r0 == null) goto L10;
            Utilities.searchQueue.postRunnable(new SharedMediaLayout$GroupUsersSearchAdapter$$ExternalSyntheticLambda4(this, r15, r0));
        L11:
            SearchAdapterHelper r2 = this.searchAdapterHelper;
            if (ChatObject.isChannel(this.currentChat) == false) goto L15;
            long r02 = this.currentChat.id;
        L16:
            r2.queryServerSearch(r15, false, false, true, false, false, r02, false, 2, 1);
            return;
        L15:
            r02 = 0;
            goto L16
        L10:
            this.searchCount = 2 - 1;
            goto L11
        L5:
            if (SharedMediaLayout.m12822$$Nest$fgetinfo(this.this$0) == null) goto L7;
            r0 = new ArrayList(SharedMediaLayout.m12822$$Nest$fgetinfo(this.this$0).participants.participants);
            goto L7
        }

        private /* synthetic */ void lambda$search$1(String r1) {
            processSearch(r1);
        }

        private /* synthetic */ void lambda$updateSearchResults$4(ArrayList r5, ArrayList r6) {
            if (SharedMediaLayout.m12842$$Nest$fgetsearching(this.this$0) == true) goto L5;
            return;
        L5:
            this.searchResultNames = r5;
            this.searchCount--;
            if (ChatObject.isChannel(this.currentChat) == true) goto L9;
            ArrayList<TLObject> r52 = this.searchAdapterHelper.getGroupSearch();
            r52.clear();
            r52.addAll(r6);
        L9:
            if (this.searchCount != 0) goto L20;
            int r62 = 0;
        L12:
            if (r62 >= SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0).length) goto L20;
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r62].selectedType != 7) goto L19;
            int r1 = getItemCount();
            SharedMediaLayout r2 = this.this$0;
            if (r1 != 0) goto L18;
            MediaPage.m12957$$Nest$fgetemptyView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(r2)[r62]).showProgress(false, true);
            goto L19
        L18:
            SharedMediaLayout.m12884$$Nest$manimateItemsEnter(r2, MediaPage.m12961$$Nest$fgetlistView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(r2)[r62]), 0, null);
        L19:
            r62 = r62 + 1;
        L20:
            notifyDataSetChanged();
        }

        private void processSearch(String r2) {
            AndroidUtilities.runOnUIThread(new SharedMediaLayout$GroupUsersSearchAdapter$$ExternalSyntheticLambda3(this, r2));
        }

        private void updateSearchResults(ArrayList<CharSequence> r2, ArrayList<TLObject> r3) {
            AndroidUtilities.runOnUIThread(new SharedMediaLayout$GroupUsersSearchAdapter$$ExternalSyntheticLambda5(this, r2, r3));
        }

        public TLObject getItem(int r2) {
            int r0 = this.searchAdapterHelper.getGroupSearch().size();
            if (r2 < 0) goto L8;
            if (r2 < r0) goto L7;
            return null;
        L7:
            return this.searchAdapterHelper.getGroupSearch().get(r2);
        L8:
            return null;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.totalCount;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return 22;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            int r0 = this.searchAdapterHelper.getGroupSearch().size();
            this.totalCount = r0;
            if (r0 > 0) goto L5;
        L11:
            super.notifyDataSetChanged();
            return;
        L5:
            if (SharedMediaLayout.m12842$$Nest$fgetsearching(this.this$0) == false) goto L11;
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0].selectedType != 7) goto L11;
            if (MediaPage.m12961$$Nest$fgetlistView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0]).getAdapter() == this) goto L11;
            SharedMediaLayout.m12918$$Nest$mswitchToCurrentSelectedMode(this.this$0, false);
            goto L11
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r8, int r9) {
            TLObject r0 = getItem(r9);
            if ((r0 instanceof TLRPC.ChannelParticipant) == false) goto L6;
            TLRPC.User r02 = SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getMessagesController().getUser(Long.valueOf(MessageObject.getPeerId(((TLRPC.ChannelParticipant) r0).peer)));
        L8:
            UserObject.getPublicUsername(r02);
            this.searchAdapterHelper.getGroupSearch().size();
            String r1 = this.searchAdapterHelper.getLastFoundChannel();
            if (r1 == null) goto L13;
            String r3 = UserObject.getUserName(r02);
            SpannableStringBuilder r4 = new SpannableStringBuilder(r3);
            int r32 = AndroidUtilities.indexOfIgnoreCase(r3, r1);
            if (r32 == (-1)) goto L14;
            r4.setSpan(new ForegroundColorSpan(SharedMediaLayout.m12897$$Nest$mgetThemedColor(this.this$0, Theme.key_windowBackgroundWhiteBlueText4)), r32, r1.length() + r32, 33);
        L14:
            View r7 = r8.itemView;
            if ((r7 instanceof ManageChatUserCell) == false) goto L20;
            ManageChatUserCell r72 = (ManageChatUserCell) r7;
            r72.setTag(Integer.valueOf(r9));
            r72.setData(r02, r4, null, false);
            return;
        L20:
            return;
        L13:
            r4 = null;
            goto L14
        L6:
            if ((r0 instanceof TLRPC.ChatParticipant) == false) goto L19;
            r02 = SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getMessagesController().getUser(Long.valueOf(((TLRPC.ChatParticipant) r0).user_id));
            goto L8
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r7, int r8) {
            ManageChatUserCell r0 = new ManageChatUserCell(this.mContext, 9, 5, true, SharedMediaLayout.m12835$$Nest$fgetresourcesProvider(this.this$0));
            r0.setBackgroundColor(SharedMediaLayout.m12897$$Nest$mgetThemedColor(this.this$0, Theme.key_windowBackgroundWhite));
            r0.setDelegate(new SharedMediaLayout$GroupUsersSearchAdapter$$ExternalSyntheticLambda2(this));
            return new RecyclerListView.Holder(r0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewRecycled(RecyclerView.ViewHolder r1) {
            View r0 = r1.itemView;
            if ((r0 instanceof ManageChatUserCell) == false) goto L6;
            ((ManageChatUserCell) r0).recycle();
            return;
        }

        public void search(String r15, boolean r16) {
            if (this.searchRunnable == null) goto L5;
            Utilities.searchQueue.cancelRunnable(this.searchRunnable);
            this.searchRunnable = null;
        L5:
            this.searchResultNames.clear();
            this.searchAdapterHelper.mergeResults(null);
            SearchAdapterHelper r2 = this.searchAdapterHelper;
            if (ChatObject.isChannel(this.currentChat) == false) goto L9;
            long r0 = this.currentChat.id;
        L10:
            r2.queryServerSearch(null, true, false, true, false, false, r0, false, 2, 0);
            notifyDataSetChanged();
            int r02 = 0;
        L12:
            if (r02 >= SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0).length) goto L21;
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r02].selectedType != 7) goto L19;
            if (TextUtils.isEmpty(r15) == true) goto L19;
            MediaPage.m12957$$Nest$fgetemptyView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r02]).showProgress(true, r16);
        L19:
            r02 = r02 + 1;
            goto L12
        L21:
            if (TextUtils.isEmpty(r15) == true) goto L27;
            DispatchQueue r03 = Utilities.searchQueue;
            SharedMediaLayout$GroupUsersSearchAdapter$$ExternalSyntheticLambda1 r1 = new SharedMediaLayout$GroupUsersSearchAdapter$$ExternalSyntheticLambda1(this, r15);
            this.searchRunnable = r1;
            r03.postRunnable(r1, 300);
            return;
        L27:
            return;
        L9:
            r0 = 0;
            goto L10
        }
    }

    public static class InternalListView extends BlurredRecyclerView implements StoriesListPlaceProvider.ClippedView {
        public int hintPaddingBottom;
        public int hintPaddingTop;

        public InternalListView(Context r1) {
            super(r1);
        }

        @Override // org.telegram.ui.Stories.StoriesListPlaceProvider.ClippedView
        public void updateClip(int[] r3) {
            r3[0] = (getPaddingTop() - AndroidUtilities.dp(2.0f)) - this.hintPaddingTop;
            r3[1] = (getMeasuredHeight() - getPaddingBottom()) - this.hintPaddingBottom;
        }
    }

    public static class MediaPage extends FrameLayout {
        private ClippingImageView animatingImageView;
        private GridLayoutManager animationSupportingLayoutManager;
        private InternalListView animationSupportingListView;
        private StickerEmptyView emptyView;
        public ObjectAnimator fastScrollAnimator;
        public boolean fastScrollEnabled;
        public Runnable fastScrollHideHintRunnable;
        public boolean fastScrollHinWasShown;
        public SharedMediaFastScrollTooltip fastScrollHintView;
        public boolean highlightAnimation;
        public int highlightMessageId;
        public float highlightProgress;
        private IBlur3Capture iBlur3Capture;
        private DefaultItemAnimator itemAnimator;
        public long lastCheckScrollTime;
        private ExtendedGridLayoutManager layoutManager;
        private InternalListView listView;
        private FlickerLoadingView progressView;
        private RecyclerAnimationScrollHelper scrollHelper;
        private RecyclerView.RecycledViewPool searchViewPool;
        public int selectedType;
        private RecyclerView.RecycledViewPool viewPool;

        /* renamed from: -$$Nest$fgetanimatingImageView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ClippingImageView m12954$$Nest$fgetanimatingImageView(MediaPage r0) {
            return r0.animatingImageView;
        }

        /* renamed from: -$$Nest$fgetanimationSupportingLayoutManager, reason: not valid java name */
        public static /* bridge */ /* synthetic */ GridLayoutManager m12955$$Nest$fgetanimationSupportingLayoutManager(MediaPage r0) {
            return r0.animationSupportingLayoutManager;
        }

        /* renamed from: -$$Nest$fgetanimationSupportingListView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ InternalListView m12956$$Nest$fgetanimationSupportingListView(MediaPage r0) {
            return r0.animationSupportingListView;
        }

        /* renamed from: -$$Nest$fgetemptyView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ StickerEmptyView m12957$$Nest$fgetemptyView(MediaPage r0) {
            return r0.emptyView;
        }

        /* renamed from: -$$Nest$fgetiBlur3Capture, reason: not valid java name */
        public static /* bridge */ /* synthetic */ IBlur3Capture m12958$$Nest$fgetiBlur3Capture(MediaPage r0) {
            return r0.iBlur3Capture;
        }

        /* renamed from: -$$Nest$fgetitemAnimator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ DefaultItemAnimator m12959$$Nest$fgetitemAnimator(MediaPage r0) {
            return r0.itemAnimator;
        }

        /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ExtendedGridLayoutManager m12960$$Nest$fgetlayoutManager(MediaPage r0) {
            return r0.layoutManager;
        }

        /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ InternalListView m12961$$Nest$fgetlistView(MediaPage r0) {
            return r0.listView;
        }

        /* renamed from: -$$Nest$fgetprogressView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ FlickerLoadingView m12962$$Nest$fgetprogressView(MediaPage r0) {
            return r0.progressView;
        }

        /* renamed from: -$$Nest$fgetscrollHelper, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RecyclerAnimationScrollHelper m12963$$Nest$fgetscrollHelper(MediaPage r0) {
            return r0.scrollHelper;
        }

        /* renamed from: -$$Nest$fgetsearchViewPool, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RecyclerView.RecycledViewPool m12964$$Nest$fgetsearchViewPool(MediaPage r0) {
            return r0.searchViewPool;
        }

        /* renamed from: -$$Nest$fgetviewPool, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RecyclerView.RecycledViewPool m12965$$Nest$fgetviewPool(MediaPage r0) {
            return r0.viewPool;
        }

        /* renamed from: -$$Nest$fputanimatingImageView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12966$$Nest$fputanimatingImageView(MediaPage r0, ClippingImageView r1) {
            r0.animatingImageView = r1;
        }

        /* renamed from: -$$Nest$fputanimationSupportingLayoutManager, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12967$$Nest$fputanimationSupportingLayoutManager(MediaPage r0, GridLayoutManager r1) {
            r0.animationSupportingLayoutManager = r1;
        }

        /* renamed from: -$$Nest$fputanimationSupportingListView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12968$$Nest$fputanimationSupportingListView(MediaPage r0, InternalListView r1) {
            r0.animationSupportingListView = r1;
        }

        /* renamed from: -$$Nest$fputemptyView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12969$$Nest$fputemptyView(MediaPage r0, StickerEmptyView r1) {
            r0.emptyView = r1;
        }

        /* renamed from: -$$Nest$fputiBlur3Capture, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12970$$Nest$fputiBlur3Capture(MediaPage r0, IBlur3Capture r1) {
            r0.iBlur3Capture = r1;
        }

        /* renamed from: -$$Nest$fputitemAnimator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12971$$Nest$fputitemAnimator(MediaPage r0, DefaultItemAnimator r1) {
            r0.itemAnimator = r1;
        }

        /* renamed from: -$$Nest$fputlayoutManager, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12972$$Nest$fputlayoutManager(MediaPage r0, ExtendedGridLayoutManager r1) {
            r0.layoutManager = r1;
        }

        /* renamed from: -$$Nest$fputlistView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12973$$Nest$fputlistView(MediaPage r0, InternalListView r1) {
            r0.listView = r1;
        }

        /* renamed from: -$$Nest$fputprogressView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12974$$Nest$fputprogressView(MediaPage r0, FlickerLoadingView r1) {
            r0.progressView = r1;
        }

        /* renamed from: -$$Nest$fputscrollHelper, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12975$$Nest$fputscrollHelper(MediaPage r0, RecyclerAnimationScrollHelper r1) {
            r0.scrollHelper = r1;
        }

        /* renamed from: -$$Nest$fputsearchViewPool, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12976$$Nest$fputsearchViewPool(MediaPage r0, RecyclerView.RecycledViewPool r1) {
            r0.searchViewPool = r1;
        }

        /* renamed from: -$$Nest$fputviewPool, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12977$$Nest$fputviewPool(MediaPage r0, RecyclerView.RecycledViewPool r1) {
            r0.viewPool = r1;
        }

        public MediaPage(Context r1) {
            super(r1);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r5) {
            super.dispatchDraw(r5);
            SharedMediaFastScrollTooltip r52 = this.fastScrollHintView;
            if (r52 != null) goto L5;
            return;
        L5:
            if (r52.getVisibility() != 0) goto L22;
            RecyclerListView.FastScroll r53 = this.listView.getFastScroll();
            if (r53 == null) goto L18;
            float r0 = r53.getScrollBarY() + AndroidUtilities.dp(36.0f);
            if (this.selectedType != 9) goto L11;
            r0 = r0 + AndroidUtilities.dp(64.0f);
        L11:
            int r1 = this.selectedType;
            if (r1 != 8) goto L14;
        L15:
            r0 = r0 + AndroidUtilities.dp(42.0f);
        L16:
            float r12 = (getMeasuredWidth() - this.fastScrollHintView.getMeasuredWidth()) - AndroidUtilities.dp(16.0f);
            this.fastScrollHintView.setPivotX(r2.getMeasuredWidth());
            this.fastScrollHintView.setPivotY(0.0f);
            this.fastScrollHintView.setTranslationX(r12);
            this.fastScrollHintView.setTranslationY(r0);
            goto L18
        L14:
            if (SharedMediaLayout.isStoryAlbumPageType(r1) == false) goto L16;
        L18:
            if (r53.getProgress() <= 0.85f) goto L23;
            SharedMediaLayout.m12923$$Nest$smshowFastScrollHint(this, null, false);
            return;
        L23:
            return;
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r2, View r3, long r4) {
            if (r3 != this.animationSupportingListView) goto L7;
            return true;
        L7:
            return super.drawChild(r2, r3, r4);
        }
    }

    public class MediaSearchAdapter extends RecyclerListView.SelectionAdapter {
        private int currentType;
        protected ArrayList<MessageObject> globalSearch;
        private int lastReqId;
        private Context mContext;
        private int reqId;
        private ArrayList<MessageObject> searchResult;
        private Runnable searchRunnable;
        private int searchesInProgress;
        final /* synthetic */ SharedMediaLayout this$0;

        /* renamed from: org.telegram.ui.Components.SharedMediaLayout$MediaSearchAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends SharedAudioCell {
            final /* synthetic */ MediaSearchAdapter this$1;

            public AnonymousClass1(MediaSearchAdapter r1, Context r2, int r3, Theme.ResourcesProvider r4) {
                this.this$1 = r1;
                super(r2, r3, r4);
            }

            @Override // org.telegram.ui.Cells.SharedAudioCell
            public boolean needPlayMessage(MessageObject r5) {
                if (r5.isVoice() == false) goto L5;
            L12:
                boolean r0 = MediaController.getInstance().playMessage(r5);
                MediaController r2 = MediaController.getInstance();
                if (r0 == false) goto L15;
                ArrayList<MessageObject> r4 = MediaSearchAdapter.m12979$$Nest$fgetsearchResult(this.this$1);
            L16:
                r2.setVoiceMessagesPlaylist(r4, false);
                if (r5.isRoundVideo() == false) goto L19;
                MediaController.getInstance().setCurrentVideoVisible(false);
            L19:
                return r0;
            L15:
                r4 = null;
                goto L16
            L5:
                if (r5.isRoundVideo() == true) goto L12;
                if (r5.isMusic() == true) goto L10;
                return false;
            L10:
                return MediaController.getInstance().setPlaylist(MediaSearchAdapter.m12979$$Nest$fgetsearchResult(this.this$1), r5, SharedMediaLayout.m12826$$Nest$fgetmergeDialogId(this.this$1.this$0));
            }
        }

        /* renamed from: $r8$lambda$0We15Y120cB8gSLTv8R6d0ad-2w, reason: not valid java name */
        public static /* synthetic */ void m12978$r8$lambda$0We15Y120cB8gSLTv8R6d0ad2w(MediaSearchAdapter r0, ArrayList r1) {
            r0.lambda$updateSearchResults$4(r1);
        }

        public static /* synthetic */ void $r8$lambda$CaLPcbF0S4QYGA3xopg6PBDJ6WE(MediaSearchAdapter r0, int r1, ArrayList r2, String r3) {
            r0.lambda$queryServerSearch$0(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$ZbC77wJ1UWmMyYNXltc7hnCESJw(MediaSearchAdapter r0, int r1, int r2, String r3, TLObject r4, TLRPC.TL_error r5) {
            r0.lambda$queryServerSearch$1(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$lOxDFCh2oTnbvUlgbsvtvk2nytU(MediaSearchAdapter r0, String r1) {
            r0.lambda$search$3(r1);
        }

        public static /* synthetic */ void $r8$lambda$uHHXJDlo_Q9esUeG9C7QJubTBAs(MediaSearchAdapter r0, String r1, ArrayList r2) {
            r0.lambda$search$2(r1, r2);
        }

        /* renamed from: -$$Nest$fgetsearchResult, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m12979$$Nest$fgetsearchResult(MediaSearchAdapter r0) {
            return r0.searchResult;
        }

        public MediaSearchAdapter(SharedMediaLayout r1, Context r2, int r3) {
            this.this$0 = r1;
            this.searchResult = new ArrayList();
            this.globalSearch = new ArrayList();
            this.reqId = 0;
            this.mContext = r2;
            this.currentType = r3;
        }

        private /* synthetic */ void lambda$queryServerSearch$0(int r8, ArrayList r9, String r10) {
            if (this.reqId != 0) goto L5;
            return;
        L5:
            if (r8 != this.lastReqId) goto L23;
            int r82 = getItemCount();
            this.globalSearch = r9;
            this.searchesInProgress--;
            int r92 = getItemCount();
            if (this.searchesInProgress == 0) goto L9;
            if (r92 != 0) goto L9;
        L10:
            int r2 = 0;
        L12:
            if (r2 >= SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0).length) goto L22;
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r2].selectedType != this.currentType) goto L21;
            if (this.searchesInProgress != 0) goto L19;
            if (r92 != 0) goto L19;
            MediaPage.m12957$$Nest$fgetemptyView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r2]).title.setText(LocaleController.formatString("NoResultFoundFor", R.string.NoResultFoundFor, new Object[]{r10}));
            MediaPage.m12957$$Nest$fgetemptyView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r2]).button.setVisibility(8);
            MediaPage.m12957$$Nest$fgetemptyView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r2]).showProgress(false, true);
        L19:
            if (r82 != 0) goto L21;
            SharedMediaLayout r3 = this.this$0;
            SharedMediaLayout.m12884$$Nest$manimateItemsEnter(r3, MediaPage.m12961$$Nest$fgetlistView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(r3)[r2]), 0, null);
        L21:
            r2 = r2 + 1;
            goto L12
        L22:
            notifyDataSetChanged();
        L9:
            SharedMediaLayout.m12918$$Nest$mswitchToCurrentSelectedMode(this.this$0, false);
        L23:
            this.reqId = 0;
        }

        private /* synthetic */ void lambda$queryServerSearch$1(int r7, int r8, String r9, TLObject r10, TLRPC.TL_error r11) {
            ArrayList r0 = new ArrayList();
            if (r11 != null) goto L17;
            TLRPC.messages_Messages r102 = (TLRPC.messages_Messages) r10;
            int r1 = 0;
        L6:
            if (r1 >= r102.messages.size()) goto L17;
            TLRPC.Message r2 = r102.messages.get(r1);
            if (r7 == 0) goto L13;
            if (r2.id <= r7) goto L13;
        L16:
            r1 = r1 + 1;
        L13:
            if (LocaleUtils.isCustomEmojiOnlyLinkMessage(r2) == true) goto L16;
            r0.add(new MessageObject(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getCurrentAccount(), r2, false, true));
        L17:
            AndroidUtilities.runOnUIThread(new SharedMediaLayout$MediaSearchAdapter$$ExternalSyntheticLambda4(this, r8, r0, r9));
        }

        private /* synthetic */ void lambda$search$2(String r12, ArrayList r13) {
            String r122 = r12.trim().toLowerCase();
            if (r122.length() != 0) goto L6;
            updateSearchResults(new ArrayList());
            return;
        L6:
            String r0 = LocaleController.getInstance().getTranslitString(r122);
            if (r122.equals(r0) == false) goto L9;
        L10:
            r0 = null;
        L12:
            if (r0 == null) goto L14;
            int r3 = 1;
        L15:
            int r32 = r3 + 1;
            String[] r4 = new String[r32];
            r4[0] = r122;
            if (r0 == null) goto L18;
            r4[1] = r0;
        L18:
            ArrayList<MessageObject> r123 = new ArrayList();
            int r02 = 0;
        L20:
            if (r02 >= r13.size()) goto L56;
            MessageObject r2 = (MessageObject) r13.get(r02);
            int r5 = 0;
        L22:
            if (r5 >= r32) goto L55;
            String r6 = r4[r5];
            String r7 = r2.getDocumentName();
            if (r7 == null) goto L54;
            if (r7.length() == 0) goto L54;
            if (r7.toLowerCase().contains(r6) == true) goto L30;
            if (this.currentType != 4) goto L54;
            int r72 = r2.type;
            TLRPC.Message r8 = r2.messageOwner;
            if (r72 != 0) goto L36;
            TLRPC.Document r73 = MessageObject.getMedia(r8).webpage.document;
        L37:
            int r82 = 0;
        L39:
            if (r82 >= r73.attributes.size()) goto L51;
            TLRPC.DocumentAttribute r9 = r73.attributes.get(r82);
            if ((r9 instanceof TLRPC.TL_documentAttributeAudio) == true) goto L42;
            r82 = r82 + 1;
            goto L39
        L42:
            String r74 = r9.performer;
            if (r74 == null) goto L45;
            boolean r75 = r74.toLowerCase().contains(r6);
        L46:
            if (r75 == true) goto L52;
            String r83 = r9.title;
            if (r83 == null) goto L52;
            r75 = r83.toLowerCase().contains(r6);
        L52:
            if (r75 == false) goto L54;
            r123.add(r2);
            goto L55
        L45:
            r75 = false;
            goto L46
        L51:
            r75 = false;
            goto L52
        L36:
            r73 = MessageObject.getMedia(r8).document;
            goto L37
        L30:
            r123.add(r2);
        L54:
            r5 = r5 + 1;
        L55:
            r02 = r02 + 1;
            goto L20
        L56:
            updateSearchResults(r123);
            return;
        L14:
            r3 = 0;
            goto L15
        L9:
            if (r0.length() != 0) goto L12;
            goto L10
        }

        private /* synthetic */ void lambda$search$3(String r12) {
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType].messages.isEmpty() == true) goto L14;
            int r1 = this.currentType;
            if (r1 == 1) goto L7;
            if (r1 != 4) goto L14;
        L7:
            MessageObject r13 = SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType].messages.get(SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType].messages.size() - 1);
            int r2 = r13.getId();
            long r3 = r13.getDialogId();
            if (SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0) != SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getUserConfig().getClientUserId()) goto L11;
            long r5 = r13.getSavedDialogId();
        L12:
            queryServerSearch(r12, r2, r3, r5);
        L16:
            int r14 = this.currentType;
            if (r14 == 1) goto L21;
            if (r14 == 4) goto L21;
            return;
        L21:
            ArrayList r15 = new ArrayList(SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType].messages);
            this.searchesInProgress++;
            Utilities.searchQueue.postRunnable(new SharedMediaLayout$MediaSearchAdapter$$ExternalSyntheticLambda1(this, r12, r15));
            return;
        L11:
            r5 = 0;
        L14:
            if (this.currentType != 3) goto L16;
            queryServerSearch(r12, 0, SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0), SharedMediaLayout.m12854$$Nest$fgettopicId(this.this$0));
            goto L16
        }

        private /* synthetic */ void lambda$updateSearchResults$4(ArrayList r8) {
            if (SharedMediaLayout.m12842$$Nest$fgetsearching(this.this$0) == true) goto L5;
            return;
        L5:
            this.searchesInProgress--;
            int r0 = getItemCount();
            this.searchResult = r8;
            int r82 = getItemCount();
            if (this.searchesInProgress == 0) goto L8;
            if (r82 != 0) goto L8;
        L9:
            int r2 = 0;
        L11:
            if (r2 >= SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0).length) goto L21;
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r2].selectedType != this.currentType) goto L20;
            if (this.searchesInProgress != 0) goto L18;
            if (r82 != 0) goto L18;
            MediaPage.m12957$$Nest$fgetemptyView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r2]).title.setText(LocaleController.getString("NoResult", R.string.NoResult));
            MediaPage.m12957$$Nest$fgetemptyView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r2]).button.setVisibility(8);
            MediaPage.m12957$$Nest$fgetemptyView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r2]).showProgress(false, true);
        L18:
            if (r0 != 0) goto L20;
            SharedMediaLayout r4 = this.this$0;
            SharedMediaLayout.m12884$$Nest$manimateItemsEnter(r4, MediaPage.m12961$$Nest$fgetlistView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(r4)[r2]), 0, null);
        L20:
            r2 = r2 + 1;
            goto L11
        L21:
            notifyDataSetChanged();
            return;
        L8:
            SharedMediaLayout.m12918$$Nest$mswitchToCurrentSelectedMode(this.this$0, false);
            goto L9
        }

        private void updateSearchResults(ArrayList<MessageObject> r2) {
            AndroidUtilities.runOnUIThread(new SharedMediaLayout$MediaSearchAdapter$$ExternalSyntheticLambda2(this, r2));
        }

        public MessageObject getItem(int r2) {
            if (r2 >= this.searchResult.size()) goto L7;
            return this.searchResult.get(r2);
        L7:
            return this.globalSearch.get(r2 - this.searchResult.size());
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            int r0 = this.searchResult.size();
            int r1 = this.globalSearch.size();
            if (r1 != 0) goto L5;
            return r0;
        L5:
            return r0 + r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return 24;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if ((this.searchResult.size() + this.globalSearch.size()) == 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r8, int r9) {
            int r0 = this.currentType;
            boolean r1 = false;
            if (r0 != 1) goto L25;
            View r82 = r8.itemView;
            if ((r82 instanceof SharedDocumentCell) == false) goto L69;
            SharedDocumentCell r83 = (SharedDocumentCell) r82;
            MessageObject r02 = getItem(r9);
            if (r9 == (getItemCount() - 1)) goto L10;
            boolean r92 = true;
        L11:
            r83.setDocument(r02, r92);
            SharedMediaLayout r93 = this.this$0;
            if (r93.isActionModeShowed == false) goto L22;
            SparseArray[] r94 = SharedMediaLayout.m12844$$Nest$fgetselectedFiles(r93);
            if (r02.getDialogId() != SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0)) goto L16;
            char r3 = 0;
        L18:
            if (r94[r3].indexOfKey(r02.getId()) < 0) goto L20;
            r1 = true;
        L20:
            r83.setChecked(r1, !SharedMediaLayout.m12839$$Nest$fgetscrolling(this.this$0));
            return;
        L16:
            r3 = 1;
            goto L18
        L22:
            r83.setChecked(false, !SharedMediaLayout.m12839$$Nest$fgetscrolling(r93));
            return;
        L10:
            r92 = false;
            goto L11
        L69:
            return;
        L25:
            if (r0 != 3) goto L47;
            View r84 = r8.itemView;
            if ((r84 instanceof SharedLinkCell) == false) goto L70;
            SharedLinkCell r85 = (SharedLinkCell) r84;
            MessageObject r03 = getItem(r9);
            if (r9 == (getItemCount() - 1)) goto L32;
            boolean r95 = true;
        L33:
            r85.setLink(r03, r95);
            SharedMediaLayout r96 = this.this$0;
            if (r96.isActionModeShowed == false) goto L44;
            SparseArray[] r97 = SharedMediaLayout.m12844$$Nest$fgetselectedFiles(r96);
            if (r03.getDialogId() != SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0)) goto L38;
            char r32 = 0;
        L40:
            if (r97[r32].indexOfKey(r03.getId()) < 0) goto L42;
            r1 = true;
        L42:
            r85.setChecked(r1, !SharedMediaLayout.m12839$$Nest$fgetscrolling(this.this$0));
            return;
        L38:
            r32 = 1;
            goto L40
        L44:
            r85.setChecked(false, !SharedMediaLayout.m12839$$Nest$fgetscrolling(r96));
            return;
        L32:
            r95 = false;
            goto L33
        L70:
            return;
        L47:
            if (r0 != 4) goto L68;
            View r86 = r8.itemView;
            if ((r86 instanceof SharedAudioCell) == false) goto L71;
            SharedAudioCell r87 = (SharedAudioCell) r86;
            MessageObject r04 = getItem(r9);
            if (r9 == (getItemCount() - 1)) goto L54;
            boolean r98 = true;
        L55:
            r87.setMessageObject(r04, r98);
            SharedMediaLayout r99 = this.this$0;
            if (r99.isActionModeShowed == false) goto L66;
            SparseArray[] r910 = SharedMediaLayout.m12844$$Nest$fgetselectedFiles(r99);
            if (r04.getDialogId() != SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0)) goto L60;
            char r33 = 0;
        L62:
            if (r910[r33].indexOfKey(r04.getId()) < 0) goto L64;
            r1 = true;
        L64:
            r87.setChecked(r1, !SharedMediaLayout.m12839$$Nest$fgetscrolling(this.this$0));
            return;
        L60:
            r33 = 1;
            goto L62
        L66:
            r87.setChecked(false, !SharedMediaLayout.m12839$$Nest$fgetscrolling(r99));
            return;
        L54:
            r98 = false;
            goto L55
        L71:
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            int r32 = this.currentType;
            if (r32 != 1) goto L6;
            FrameLayout r33 = new SharedDocumentCell(this.mContext, 0, SharedMediaLayout.m12835$$Nest$fgetresourcesProvider(this.this$0));
        L9:
            r33.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r33);
        L6:
            if (r32 != 4) goto L8;
            r33 = new AnonymousClass1(this, this.mContext, 0, SharedMediaLayout.m12835$$Nest$fgetresourcesProvider(this.this$0));
            goto L9
        L8:
            SharedLinkCell r34 = new SharedLinkCell(this.mContext, 0, SharedMediaLayout.m12835$$Nest$fgetresourcesProvider(this.this$0));
            r34.setDelegate(this.this$0.sharedLinkCellDelegate);
            r33 = r34;
            goto L9
        }

        public void queryServerSearch(String r8, int r9, long r10, long r12) {
            if (DialogObject.isEncryptedDialog(r10) == false) goto L6;
            return;
        L6:
            if (this.reqId == 0) goto L8;
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getConnectionsManager().cancelRequest(this.reqId, true);
            this.reqId = 0;
            this.searchesInProgress--;
        L8:
            if (r8 != null) goto L10;
        L31:
            this.globalSearch.clear();
            this.lastReqId = 0;
            notifyDataSetChanged();
            return;
        L10:
            if (r8.length() == 0) goto L31;
            TLRPC.TL_messages_search r0 = new TLRPC.TL_messages_search();
            r0.limit = 50;
            r0.offset_id = r9;
            int r1 = this.currentType;
            if (r1 != 1) goto L16;
            r0.filter = new TLRPC.TL_inputMessagesFilterDocument();
        L20:
            r0.q = r8;
            r0.peer = SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getMessagesController().getInputPeer(r10);
            if (r12 == 0) goto L27;
            long r5 = SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getUserConfig().getClientUserId();
            int r11 = r0.flags;
            if (r10 != r5) goto L25;
            r0.flags = r11 | 4;
            r0.saved_peer_id = SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getMessagesController().getInputPeer(r12);
            goto L27
        L25:
            r0.flags = r11 | 2;
            r0.top_msg_id = (int) r12;
        L27:
            if (r0.peer != null) goto L29;
            return;
        L29:
            int r102 = this.lastReqId + 1;
            this.lastReqId = r102;
            this.searchesInProgress++;
            this.reqId = SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getConnectionsManager().sendRequest(r0, new SharedMediaLayout$MediaSearchAdapter$$ExternalSyntheticLambda3(this, r9, r102, r8), 2);
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getConnectionsManager().bindRequestToGuid(this.reqId, SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getClassGuid());
            return;
        L16:
            if (r1 != 3) goto L18;
            r0.filter = new TLRPC.TL_inputMessagesFilterUrl();
            goto L20
        L18:
            if (r1 != 4) goto L20;
            r0.filter = new TLRPC.TL_inputMessagesFilterMusic();
            goto L20
        }

        public void search(String r5, boolean r6) {
            Runnable r0 = this.searchRunnable;
            if (r0 == null) goto L6;
            AndroidUtilities.cancelRunOnUIThread(r0);
            this.searchRunnable = null;
        L6:
            if (this.searchResult.isEmpty() == true) goto L8;
        L9:
            this.searchResult.clear();
            this.globalSearch.clear();
            notifyDataSetChanged();
        L10:
            int r1 = 0;
            if (TextUtils.isEmpty(r5) == false) goto L23;
            if (this.searchResult.isEmpty() == true) goto L15;
        L18:
            this.searchResult.clear();
            this.globalSearch.clear();
            if (this.reqId == 0) goto L34;
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getConnectionsManager().cancelRequest(this.reqId, true);
            this.reqId = 0;
            this.searchesInProgress--;
            return;
        L34:
            return;
        L15:
            if (this.globalSearch.isEmpty() == false) goto L18;
            if (this.searchesInProgress != 0) goto L18;
            return;
        L23:
            if (r1 >= SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0).length) goto L28;
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r1].selectedType != this.currentType) goto L27;
            MediaPage.m12957$$Nest$fgetemptyView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r1]).showProgress(true, r6);
        L27:
            r1 = r1 + 1;
            goto L23
        L28:
            SharedMediaLayout$MediaSearchAdapter$$ExternalSyntheticLambda0 r62 = new SharedMediaLayout$MediaSearchAdapter$$ExternalSyntheticLambda0(this, r5);
            this.searchRunnable = r62;
            AndroidUtilities.runOnUIThread(r62, 300);
            return;
        L8:
            if (this.globalSearch.isEmpty() == true) goto L10;
            goto L9
        }
    }

    public static class MoreRecommendationsCell extends FrameLayout {
        private final ButtonWithCounterView button;
        public final ProfileSearchCell channelCell;
        private final int currentAccount;
        private final View gradientView;
        private final Theme.ResourcesProvider resourcesProvider;
        private final LinkSpanDrawable.LinksTextView textView;

        public static /* synthetic */ void $r8$lambda$PF2whsReps3BoQqnyxJALG04ai8(Runnable r0) {
            if (r0 == null) goto L5;
            r0.run();
            return;
        }

        public static /* synthetic */ void $r8$lambda$dLoTI4WFYldylJ4gddpppZ5YISo(Runnable r0, View r1) {
            if (r0 == null) goto L5;
            r0.run();
            return;
        }

        public MoreRecommendationsCell(int r20, Context r21, boolean r22, Theme.ResourcesProvider r23, Runnable r24) {
            super(r21);
            this.currentAccount = r20;
            this.resourcesProvider = r23;
            ProfileSearchCell r5 = new ProfileSearchCell(r21, r23);
            this.channelCell = r5;
            r5.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector, r23), 2));
            addView(r5, LayoutHelper.createFrame(-1, -2.0f));
            View r52 = new View(r21);
            this.gradientView = r52;
            GradientDrawable.Orientation r8 = GradientDrawable.Orientation.TOP_BOTTOM;
            int r9 = Theme.key_windowBackgroundWhite;
            r52.setBackground(new GradientDrawable(r8, new int[]{Theme.multAlpha(Theme.getColor(r9, r23), 0.4f), Theme.getColor(r9, r23)}));
            addView(r52, LayoutHelper.createFrame(-1, 60.0f));
            ButtonWithCounterView r53 = new ButtonWithCounterView(r21, r23);
            this.button = r53;
            SpannableStringBuilder r6 = new SpannableStringBuilder();
            if (r22 == false) goto L5;
            int r7 = R.string.MoreSimilarBotsButton;
        L6:
            r6.append(LocaleController.getString(r7));
            r6.append(" ");
            SpannableString r72 = new SpannableString("l");
            r72.setSpan(new ColoredImageSpan(R.drawable.msg_mini_lock2), 0, 1, 33);
            r6.append(r72);
            r53.setText(r6, false);
            addView(r53, LayoutHelper.createFrame(-1, 48.0f, 48, 14.0f, 38.0f, 14.0f, 0.0f));
            r53.setOnClickListener(new SharedMediaLayout$MoreRecommendationsCell$$ExternalSyntheticLambda0(r24));
            LinkSpanDrawable.LinksTextView r54 = new LinkSpanDrawable.LinksTextView(r21, r23);
            this.textView = r54;
            r54.setTextSize(1, 13.0f);
            r54.setTextAlignment(4);
            r54.setGravity(17);
            r54.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r23));
            r54.setLinkTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText, r23));
            r54.setLineSpacing(AndroidUtilities.dp(3.0f), 1.0f);
            if (r22 == false) goto L9;
            int r1 = R.string.MoreSimilarBotsText;
        L10:
            SpannableStringBuilder r12 = AndroidUtilities.premiumText(LocaleController.getString(r1), new SharedMediaLayout$MoreRecommendationsCell$$ExternalSyntheticLambda1(r24));
            SpannableString r2 = new SpannableString(_UrlKt.FRAGMENT_ENCODE_SET + MessagesController.getInstance(r20).recommendedChannelsLimitPremium);
            r2.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r2.length(), 33);
            r54.setText(AndroidUtilities.replaceCharSequence("%s", r12, r2));
            addView(r54, LayoutHelper.createFrame(-1, -2.0f, 49, 24.0f, 96.0f, 24.0f, 12.0f));
            return;
        L9:
            r1 = R.string.MoreSimilarText;
            goto L10
        L5:
            r7 = R.string.MoreSimilarButton;
            goto L6
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(145.0f), TLObject.FLAG_30));
        }
    }

    public static class Period {
        int date;
        public String formatedDate;
        int maxId;
        public int startOffset;

        public Period(TLRPC.TL_searchResultPosition r3) {
            int r0 = r3.date;
            this.date = r0;
            this.maxId = r3.msg_id;
            this.startOffset = r3.offset;
            this.formatedDate = LocaleController.formatYearMont(r0, true);
        }
    }

    public class PollAdapter extends RecyclerListView.SelectionAdapter {
        private final int currentAccount;
        private final ArrayList<MessageObject> groupedByDay;
        public RecyclerListView listView;
        private final Context mContext;
        private final ChatMessageCell.ChatMessageCellDelegate messageDelegate;
        private final ArrayList<MessageObject> pollsToCheck;
        private final Theme.ResourcesProvider resourcesProvider;
        final /* synthetic */ SharedMediaLayout this$0;

        /* renamed from: org.telegram.ui.Components.SharedMediaLayout$PollAdapter$1, reason: invalid class name */
        public class AnonymousClass1 implements ChatMessageCell.ChatMessageCellDelegate {
            final /* synthetic */ PollAdapter this$1;
            final /* synthetic */ int val$currentAccount;
            final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;
            final /* synthetic */ SharedMediaLayout val$this$0;

            /* renamed from: org.telegram.ui.Components.SharedMediaLayout$PollAdapter$1$1, reason: invalid class name and collision with other inner class name */
            public class C00601 extends LocationActivity {
                final /* synthetic */ AnonymousClass1 this$2;

                public C00601(AnonymousClass1 r1, int r2) {
                    this.this$2 = r1;
                    super(r2);
                }

                @Override // org.telegram.ui.LocationActivity
                public boolean disablePermissionCheck() {
                    return true;
                }
            }

            /* renamed from: org.telegram.ui.Components.SharedMediaLayout$PollAdapter$1$2, reason: invalid class name */
            public class AnonymousClass2 implements ContentPreviewViewer.ContentPreviewViewerDelegate {
                final /* synthetic */ AnonymousClass1 this$2;
                final /* synthetic */ TLRPC.PollAnswer val$answer;
                final /* synthetic */ ChatMessageCell val$cell;
                final /* synthetic */ TLRPC.TL_messageMediaPoll val$messageMediaPoll;

                public AnonymousClass2(AnonymousClass1 r1, TLRPC.TL_messageMediaPoll r2, TLRPC.PollAnswer r3, ChatMessageCell r4) {
                    this.this$2 = r1;
                    this.val$messageMediaPoll = r2;
                    this.val$answer = r3;
                    this.val$cell = r4;
                }

                @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
                public boolean canSchedule() {
                    return false;
                }

                @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
                public long getDialogId() {
                    return SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$2.this$1.this$0);
                }

                @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
                public TLRPC.TL_messageMediaPoll getPoll() {
                    return this.val$messageMediaPoll;
                }

                @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
                public TLRPC.PollAnswer getPollAnswer() {
                    return this.val$answer;
                }

                @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
                public MessageObject getPollMessageObject() {
                    return this.val$cell.getMessageObject();
                }

                @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
                public boolean isInScheduleMode() {
                    return false;
                }

                @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
                public boolean needSend(int r1) {
                    return false;
                }

                @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
                public void openSet(TLRPC.InputStickerSet r10, boolean r11) {
                    if (r10 != null) goto L4;
                    return;
                L4:
                    if (this.this$2.this$1.this$0.getContext() == null) goto L9;
                    TLRPC.TL_inputStickerSetID r4 = new TLRPC.TL_inputStickerSetID();
                    r4.access_hash = r10.access_hash;
                    r4.id = r10.id;
                    StickersAlert r1 = new StickersAlert(this.this$2.this$1.this$0.getContext(), SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$2.this$1.this$0), r4, null, null, this.this$2.val$resourcesProvider, false);
                    r1.setCalcMandatoryInsets(true);
                    r1.setClearsInputField(r11);
                    r1.show();
                    return;
                }

                @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
                public void retractVote() {
                    SendMessagesHelper.getInstance(this.this$2.val$currentAccount).sendVote(this.val$cell.getMessageObject(), null, null);
                }

                @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
                public void sendSticker(TLRPC.Document r1, String r2, Object r3, boolean r4, int r5, int r6) {
                }

                @Override // org.telegram.ui.ContentPreviewViewer.ContentPreviewViewerDelegate
                public void sendVote() {
                    ArrayList<TLRPC.PollAnswer> r0 = new ArrayList(1);
                    r0.add(this.val$answer);
                    SendMessagesHelper.getInstance(this.this$2.val$currentAccount).sendVote(this.val$cell.getMessageObject(), r0, null);
                }
            }

            /* renamed from: org.telegram.ui.Components.SharedMediaLayout$PollAdapter$1$3, reason: invalid class name */
            public class AnonymousClass3 extends MessageObject {
                final /* synthetic */ AnonymousClass1 this$2;

                public AnonymousClass3(AnonymousClass1 r1, int r2, TLRPC.Message r3, boolean r4, boolean r5) {
                    this.this$2 = r1;
                    super(r2, r3, r4, r5);
                }

                @Override // org.telegram.messenger.MessageObject
                public boolean canDeleteMessage(boolean r1, TLRPC.Chat r2) {
                    return false;
                }
            }

            /* renamed from: org.telegram.ui.Components.SharedMediaLayout$PollAdapter$1$4, reason: invalid class name */
            public class AnonymousClass4 extends MessageObject {
                final /* synthetic */ AnonymousClass1 this$2;

                public AnonymousClass4(AnonymousClass1 r1, int r2, TLRPC.Message r3, boolean r4, boolean r5) {
                    this.this$2 = r1;
                    super(r2, r3, r4, r5);
                }

                @Override // org.telegram.messenger.MessageObject
                public boolean canDeleteMessage(boolean r1, TLRPC.Chat r2) {
                    return false;
                }
            }

            /* renamed from: org.telegram.ui.Components.SharedMediaLayout$PollAdapter$1$5, reason: invalid class name */
            public class AnonymousClass5 extends MessageObject {
                final /* synthetic */ AnonymousClass1 this$2;

                public AnonymousClass5(AnonymousClass1 r1, int r2, TLRPC.Message r3, boolean r4, boolean r5) {
                    this.this$2 = r1;
                    super(r2, r3, r4, r5);
                }

                @Override // org.telegram.messenger.MessageObject
                public boolean canDeleteMessage(boolean r1, TLRPC.Chat r2) {
                    return false;
                }
            }

            /* renamed from: org.telegram.ui.Components.SharedMediaLayout$PollAdapter$1$6, reason: invalid class name */
            public class AnonymousClass6 extends PhotoViewer.EmptyPhotoViewerProvider {
                final /* synthetic */ AnonymousClass1 this$2;

                public AnonymousClass6(AnonymousClass1 r1) {
                    this.this$2 = r1;
                }

                @Override // org.telegram.ui.PhotoViewer.PhotoViewerProvider
                public boolean forceAllInGroup() {
                    return true;
                }

                @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
                public PhotoViewer.PlaceProviderObject getPlaceForPhoto(MessageObject r8, TLRPC.FileLocation r9, int r10, boolean r11, boolean r12) {
                    RecyclerListView r92 = this.this$2.this$1.listView;
                    if (r92 != null) goto L5;
                    return null;
                L5:
                    int r93 = r92.getChildCount();
                    int r1 = 0;
                L6:
                    if (r1 >= r93) goto L29;
                    View r2 = this.this$2.this$1.listView.getChildAt(r1);
                    if ((r2 instanceof ChatMessageCell) == false) goto L21;
                    if (r8 == null) goto L21;
                    ChatMessageCell r3 = (ChatMessageCell) r2;
                    MessageObject r4 = r3.getMessageObject();
                    if (r4 == null) goto L21;
                    if (r4.getId() != r8.getId()) goto L21;
                    ArrayList<Integer> r5 = r4.pollMediaMapping;
                    if (r5 == null) goto L20;
                    if (r10 < 0) goto L20;
                    if (r10 >= r5.size()) goto L20;
                    ImageReceiver r32 = r3.getPhotoImage(r4.pollMediaMapping.get(r10).intValue());
                L22:
                    if (r32 != null) goto L23;
                    r1 = r1 + 1;
                    goto L6
                L23:
                    int[] r82 = new int[2];
                    r2.getLocationInWindow(r82);
                    PhotoViewer.PlaceProviderObject r94 = new PhotoViewer.PlaceProviderObject();
                    r94.viewX = r82[0];
                    r94.viewY = r82[1] + r2.getPaddingTop();
                    r94.parentView = this.this$2.this$1.listView;
                    r94.animatingImageView = null;
                    r94.imageReceiver = r32;
                    if (r11 == false) goto L26;
                    r94.thumb = r32.getBitmapSafe();
                L26:
                    r94.radius = r32.getRoundRadius(true);
                    r94.clipTopAddition = 0;
                    r94.clipBottomAddition = 0;
                    return r94;
                L20:
                    r32 = r3.getPhotoImage(r10);
                L21:
                    r32 = null;
                    goto L22
                L29:
                    return null;
                }
            }

            public AnonymousClass1(PollAdapter r1, SharedMediaLayout r2, int r3, Theme.ResourcesProvider r4) {
                this.this$1 = r1;
                this.val$this$0 = r2;
                this.val$currentAccount = r3;
                this.val$resourcesProvider = r4;
            }

            private TLRPC.TL_message copy(TLRPC.Message r3) {
                TLRPC.TL_message r2 = new TLRPC.TL_message();
                r2.id = r3.id;
                r2.from_id = r3.from_id;
                r2.from_boosts_applied = r3.from_boosts_applied;
                r2.peer_id = r3.peer_id;
                r2.saved_peer_id = r3.saved_peer_id;
                r2.date = r3.date;
                r2.expire_date = r3.expire_date;
                r2.action = r3.action;
                r2.message = r3.message;
                r2.flags = r3.flags;
                r2.flags2 = r3.flags2;
                r2.mentioned = r3.mentioned;
                r2.media_unread = r3.media_unread;
                r2.out = r3.out;
                r2.unread = r3.unread;
                r2.entities = r3.entities;
                r2.via_bot_name = r3.via_bot_name;
                r2.reply_markup = r3.reply_markup;
                r2.views = r3.views;
                r2.forwards = r3.forwards;
                r2.replies = r3.replies;
                r2.edit_date = r3.edit_date;
                r2.silent = r3.silent;
                r2.post = r3.post;
                r2.from_scheduled = r3.from_scheduled;
                r2.legacy = r3.legacy;
                r2.edit_hide = r3.edit_hide;
                r2.pinned = r3.pinned;
                r2.fwd_from = r3.fwd_from;
                r2.via_bot_id = r3.via_bot_id;
                r2.via_business_bot_id = r3.via_business_bot_id;
                r2.reply_to = r3.reply_to;
                r2.post_author = r3.post_author;
                r2.grouped_id = r3.grouped_id;
                r2.reactions = r3.reactions;
                r2.restriction_reason = r3.restriction_reason;
                r2.ttl_period = r3.ttl_period;
                r2.quick_reply_shortcut_id = r3.quick_reply_shortcut_id;
                r2.effect = r3.effect;
                r2.noforwards = r3.noforwards;
                r2.invert_media = r3.invert_media;
                r2.offline = r3.offline;
                r2.factcheck = r3.factcheck;
                r2.send_state = r3.send_state;
                r2.fwd_msg_id = r3.fwd_msg_id;
                r2.params = r3.params;
                r2.random_id = r3.random_id;
                r2.local_id = r3.local_id;
                r2.dialog_id = r3.dialog_id;
                r2.ttl = r3.ttl;
                r2.destroyTime = r3.destroyTime;
                r2.destroyTimeMillis = r3.destroyTimeMillis;
                r2.layer = r3.layer;
                r2.seq_in = r3.seq_in;
                r2.seq_out = r3.seq_out;
                r2.with_my_score = r3.with_my_score;
                r2.replyMessage = r3.replyMessage;
                r2.reqId = r3.reqId;
                r2.realId = r3.realId;
                r2.stickerVerified = r3.stickerVerified;
                r2.isThreadMessage = r3.isThreadMessage;
                r2.voiceTranscription = r3.voiceTranscription;
                r2.voiceTranscriptionOpen = r3.voiceTranscriptionOpen;
                r2.voiceTranscriptionRated = r3.voiceTranscriptionRated;
                r2.voiceTranscriptionFinal = r3.voiceTranscriptionFinal;
                r2.voiceTranscriptionForce = r3.voiceTranscriptionForce;
                r2.voiceTranscriptionId = r3.voiceTranscriptionId;
                r2.premiumEffectWasPlayed = r3.premiumEffectWasPlayed;
                r2.originalLanguage = r3.originalLanguage;
                r2.translatedToLanguage = r3.translatedToLanguage;
                r2.translatedText = r3.translatedText;
                r2.replyStory = r3.replyStory;
                r2.quick_reply_shortcut = r3.quick_reply_shortcut;
                return r2;
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public boolean canPerformActions() {
                return true;
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didPressInstantButton(ChatMessageCell r2, int r3) {
                if (r3 != 80) goto L6;
                PollVotesAlert.showForPoll(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$1.this$0), r2.getMessageObject());
                return;
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didPressPollMedia(ChatMessageCell r24, ImageReceiver r25, TLRPC.PollAnswer r26, TLRPC.MessageMedia r27, float r28, float r29, int r30) {
                MessageObject r7 = r24.getMessageObject();
                TLRPC.MessageMedia r2 = MessageObject.getMedia(r7);
                if (r27 == null) goto L88;
                if (r7 != null) goto L6;
                return;
            L6:
                if ((r2 instanceof TLRPC.TL_messageMediaPoll) == false) goto L91;
                TLRPC.TL_messageMediaPoll r8 = (TLRPC.TL_messageMediaPoll) r2;
                int r10 = 0;
                if (r27.geo == null) goto L24;
                if (AndroidUtilities.isMapsInstalled(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$1.this$0)) == false) goto L92;
                C00601 r22 = new C00601(this, 3);
                r22.setResourceProvider(this.val$resourcesProvider);
                TLRPC.TL_message r3 = new TLRPC.TL_message();
                r3.local_id = -1;
                r3.peer_id = MessagesController.getInstance(this.val$currentAccount).getPeer(SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$1.this$0));
                TLRPC.TL_messageMediaGeo r4 = new TLRPC.TL_messageMediaGeo();
                r4.geo = r27.geo;
                String r5 = r27.address;
                if (r5 != null) goto L21;
                if (r26 == null) goto L20;
                TLRPC.TL_textWithEntities r0 = r26.text;
                if (r0 == null) goto L20;
                r5 = r0.text;
            L20:
                r5 = _UrlKt.FRAGMENT_ENCODE_SET;
            L21:
                r4.address = r5;
                r3.media = r4;
                r22.setSharingAllowed(false);
                r22.setMessageObject(new MessageObject(UserConfig.selectedAccount, r3, false, false));
                SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$1.this$0).presentFragment(r22);
                return;
            L92:
                return;
            L24:
                if (MessageObject.isAnyKindOfStickerOrEmoji(r27.document) == false) goto L30;
                ContentPreviewViewer.getInstance().setParentActivity(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$1.this$0).getParentActivity());
                ContentPreviewViewer.getInstance().setDelegate(new AnonymousClass2(this, r8, r26, r24));
                ContentPreviewViewer r11 = ContentPreviewViewer.getInstance();
                TLRPC.Document r12 = r27.document;
                if (MessageObject.isAnimatedEmoji(r12) == false) goto L28;
                r10 = 2;
            L28:
                r11.open(r12, null, _UrlKt.FRAGMENT_ENCODE_SET, null, null, r10, false, r24.getMessageObject(), this.val$resourcesProvider, 200);
                return;
            L30:
                TLRPC.Message r112 = r7.messageOwner;
                ArrayList<Integer> r122 = new ArrayList();
                ArrayList<MessageObject> r14 = new ArrayList();
                TLRPC.MessageMedia r02 = r8.attached_media;
                if (r02 != null) goto L33;
            L42:
                int r13 = -1;
            L44:
                if (r7.expandedExplanation == false) goto L58;
                TLRPC.PollResults r03 = r8.results;
                if (r03 == null) goto L58;
                TLRPC.MessageMedia r04 = r03.solution_media;
                if (r04 == null) goto L58;
                if (r04.geo != null) goto L58;
                TLRPC.Document r23 = r04.document;
                if (r23 != null) goto L54;
            L55:
                if (r04 != r27) goto L57;
                r13 = r14.size();
            L57:
                TLRPC.TL_message r210 = copy(r112);
                r210.media = r04;
                TLRPC.PollResults r05 = r8.results;
                r210.message = r05.solution;
                r210.entities = r05.solution_entities;
                r14.add(new AnonymousClass4(this, this.val$currentAccount, r210, false, true));
                r122.add(-3);
                goto L58
            L54:
                if (MessageObject.isVideoDocument(r23) == true) goto L55;
            L58:
                TlUtils.calculateAnswerShuffleHash(r8.poll, UserConfig.getInstance(this.val$currentAccount).getClientUserId());
                TLRPC.Poll r06 = r8.poll;
                ArrayList<TLRPC.PollAnswer> r211 = r06.shuffled_answers;
                if (r211 != null) goto L62;
                r211 = r06.answers;
            L62:
                int r15 = r13;
            L64:
                if (r10 >= r211.size()) goto L79;
                TLRPC.PollAnswer r07 = r211.get(r10);
                TLRPC.MessageMedia r32 = r07.media;
                if (r32 == null) goto L78;
                if (r32.geo != null) goto L78;
                TLRPC.Document r42 = r32.document;
                if (r42 == null) goto L75;
                if (MessageObject.isVideoDocument(r42) == false) goto L78;
            L75:
                if (r07.unshuffled_index != r30) goto L77;
                r15 = r14.size();
            L77:
                TLRPC.TL_message r43 = copy(r112);
                r43.media = r32;
                TLRPC.TL_textWithEntities r33 = r07.text;
                r43.message = r33.text;
                r43.entities = r33.entities;
                r14.add(new AnonymousClass5(this, this.val$currentAccount, r43, false, true));
                r122.add(Integer.valueOf(r07.unshuffled_index));
            L78:
                r10 = r10 + 1;
                goto L64
            L79:
                if (r15 > (-1)) goto L81;
                return;
            L81:
                if (r14.isEmpty() == true) goto L93;
                r7.pollMediaMapping = r122;
                PhotoViewer.getInstance().setParentActivity(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$1.this$0), this.val$resourcesProvider);
                PhotoViewer.getInstance().openPhoto(r14, r15, SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$1.this$0), 0, 0, new AnonymousClass6(this));
                return;
            L93:
                return;
            L33:
                if (r02.geo != null) goto L42;
                TLRPC.Document r212 = r02.document;
                if (r212 != null) goto L37;
            L38:
                if (r02 != r27) goto L40;
                r13 = r14.size();
            L41:
                TLRPC.TL_message r34 = copy(r112);
                r34.media = r02;
                r14.add(new AnonymousClass3(this, this.val$currentAccount, r34, false, true));
                r122.add(-2);
                goto L44
            L40:
                r13 = -1;
                goto L41
            L37:
                if (MessageObject.isVideoDocument(r212) == false) goto L42;
            L91:
                return;
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public void didPressVoteButtons(ChatMessageCell r1, ArrayList<TLRPC.PollAnswer> r2, int r3, int r4, int r5) {
                SendMessagesHelper.getInstance(this.val$currentAccount).sendVote(r1.getMessageObject(), r2, null);
            }
        }

        /* renamed from: org.telegram.ui.Components.SharedMediaLayout$PollAdapter$2, reason: invalid class name */
        public class AnonymousClass2 extends ChatMessageCell {
            final /* synthetic */ PollAdapter this$1;

            public AnonymousClass2(PollAdapter r1, Context r2, int r3, boolean r4, ChatMessageSharedResources r5, Theme.ResourcesProvider r6) {
                this.this$1 = r1;
                super(r2, r3, r4, r5, r6);
            }

            @Override // android.view.View
            public boolean isPressed() {
                return false;
            }
        }

        public PollAdapter(SharedMediaLayout r3, Context r4, int r5, Theme.ResourcesProvider r6) {
            this.this$0 = r3;
            this.pollsToCheck = new ArrayList(10);
            this.groupedByDay = new ArrayList();
            this.mContext = r4;
            this.currentAccount = r5;
            this.resourcesProvider = r6;
            this.messageDelegate = new AnonymousClass1(this, r3, r5, r6);
            regroup();
        }

        private MessageObject action(int r7) {
            TLRPC.TL_message r0 = new TLRPC.TL_message();
            long r1 = r7;
            r0.message = LocaleController.formatDateChat(r1);
            r0.id = 0;
            Calendar r3 = Calendar.getInstance();
            r3.setTimeInMillis(r1 * 1000);
            r3.set(11, 0);
            r3.set(12, 0);
            r3.set(13, 0);
            r3.set(14, 0);
            r0.date = (int) (r3.getTimeInMillis() / 1000);
            MessageObject r12 = new MessageObject(this.currentAccount, r0, false, false);
            r12.type = 10;
            r12.contentType = 1;
            r12.isDateObject = true;
            return r12;
        }

        private void regroup() {
            this.groupedByDay.clear();
            ArrayList<MessageObject> r0 = SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[8].getMessages();
            int r1 = 0;
            int r2 = 0;
        L4:
            if (r1 >= r0.size()) goto L9;
            MessageObject r3 = r0.get(r1);
            if (r3.dateKeyInt == r2) goto L8;
            this.groupedByDay.add(action(r3.messageOwner.date));
            r2 = r3.dateKeyInt;
        L8:
            this.groupedByDay.add(r3);
            r1 = r1 + 1;
            goto L4
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.groupedByDay.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 >= 0) goto L4;
            return 0;
        L4:
            if (r2 < this.groupedByDay.size()) goto L7;
            return 0;
        L7:
            return this.groupedByDay.get(r2).contentType;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            regroup();
            super.notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r7, int r8) {
            if (r8 >= 0) goto L4;
            return;
        L4:
            if (r8 >= this.groupedByDay.size()) goto L13;
            MessageObject r1 = this.groupedByDay.get(r8);
            int r6 = r7.getItemViewType();
            View r72 = r7.itemView;
            if (r6 != 0) goto L10;
            ((ChatMessageCell) r72).setMessageObject(r1, null, false, false, false);
            return;
        L10:
            ((ChatActionCell) r72).setMessageObject(r1);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r8, int r9) {
            if (r9 != 0) goto L6;
            AnonymousClass2 r0 = new AnonymousClass2(this, this.mContext, this.currentAccount, false, null, this.resourcesProvider);
            r0.setDelegate(this.messageDelegate);
            return new RecyclerListView.Holder(r0);
        L6:
            return new RecyclerListView.Holder(new ChatActionCell(this.mContext, false, this.resourcesProvider));
        }

        public void onScrolled(RecyclerListView r4) {
            this.pollsToCheck.clear();
            int r0 = 0;
        L4:
            if (r0 >= r4.getChildCount()) goto L9;
            View r1 = r4.getChildAt(r0);
            if ((r1 instanceof ChatMessageCell) == false) goto L8;
            this.pollsToCheck.add(((ChatMessageCell) r1).getMessageObject());
        L8:
            r0 = r0 + 1;
            goto L4
        L9:
            MessagesController.getInstance(this.currentAccount).addToPollsQueue(SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0), this.pollsToCheck);
        }

        public void update(RecyclerListView r4, long r5, TLRPC.TL_poll r7, TLRPC.PollResults r8) {
            int r42 = 0;
        L4:
            if (r42 >= this.groupedByDay.size()) goto L16;
            MessageObject r0 = this.groupedByDay.get(r42);
            if (r0 == null) goto L15;
            if (r0.getPollId() != r5) goto L15;
            TLRPC.MessageMedia r02 = r0.messageOwner.media;
            if ((r02 instanceof TLRPC.TL_messageMediaPoll) == false) goto L15;
            TLRPC.TL_messageMediaPoll r03 = (TLRPC.TL_messageMediaPoll) r02;
            if (r7 == null) goto L14;
            r03.poll = r7;
        L14:
            MessageObject.updatePollResults(r03, r8);
            notifyItemChanged(r42);
        L15:
            r42 = r42 + 1;
            goto L4
        }
    }

    public class SavedDialogsAdapter extends RecyclerListView.SelectionAdapter {
        public RecyclerListView attachedToRecyclerView;
        private final SavedMessagesController controller;
        private final ArrayList<SavedMessagesController.SavedDialog> dialogs;
        public final ItemTouchHelper itemTouchHelper;
        private final Context mContext;
        private Runnable notifyOrderUpdate;
        private final ArrayList<SavedMessagesController.SavedDialog> oldDialogs;
        private boolean orderChanged;
        public final HashSet<Long> selectedDialogs;
        final /* synthetic */ SharedMediaLayout this$0;
        public final RecyclerView.RecycledViewPool viewPool;

        /* renamed from: org.telegram.ui.Components.SharedMediaLayout$SavedDialogsAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends ItemTouchHelper.Callback {
            final /* synthetic */ SavedDialogsAdapter this$1;

            public AnonymousClass1(SavedDialogsAdapter r1) {
                this.this$1 = r1;
            }

            private SavedMessagesController.SavedDialog getDialog(RecyclerView.ViewHolder r3) {
                if (r3 != null) goto L5;
                return null;
            L5:
                int r32 = r3.getAdapterPosition();
                if (r32 >= 0) goto L8;
            L12:
                return null;
            L8:
                if (r32 >= SavedDialogsAdapter.m12980$$Nest$fgetdialogs(this.this$1).size()) goto L12;
                return (SavedMessagesController.SavedDialog) SavedDialogsAdapter.m12980$$Nest$fgetdialogs(this.this$1).get(r32);
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void clearView(RecyclerView r1, RecyclerView.ViewHolder r2) {
                super.clearView(r1, r2);
                r2.itemView.setPressed(false);
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public int getMovementFlags(RecyclerView r3, RecyclerView.ViewHolder r4) {
                if (this.this$1.this$0.isActionModeShowed == false) goto L16;
                if (r3.getAdapter() == SharedMediaLayout.m12838$$Nest$fgetsavedMessagesSearchAdapter(this.this$1.this$0)) goto L16;
                SavedMessagesController.SavedDialog r2 = getDialog(r4);
                if (r2 == null) goto L14;
                if (r2.pinned == false) goto L14;
                return ItemTouchHelper.Callback.makeMovementFlags(3, 0);
            L14:
                return ItemTouchHelper.Callback.makeMovementFlags(0, 0);
            L16:
                return ItemTouchHelper.Callback.makeMovementFlags(0, 0);
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public boolean isLongPressDragEnabled() {
                return true;
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public boolean onMove(RecyclerView r4, RecyclerView.ViewHolder r5, RecyclerView.ViewHolder r6) {
                if (this.this$1.this$0.isActionModeShowed == true) goto L5;
            L16:
                return false;
            L5:
                if (r4.getAdapter() == SharedMediaLayout.m12838$$Nest$fgetsavedMessagesSearchAdapter(this.this$1.this$0)) goto L16;
                SavedMessagesController.SavedDialog r42 = getDialog(r5);
                SavedMessagesController.SavedDialog r0 = getDialog(r6);
                if (r42 == null) goto L16;
                if (r0 == null) goto L16;
                if (r42.pinned == false) goto L16;
                if (r0.pinned == false) goto L16;
                int r52 = r5.getAdapterPosition();
                int r62 = r6.getAdapterPosition();
                SavedDialogsAdapter.m12980$$Nest$fgetdialogs(this.this$1).remove(r52);
                SavedDialogsAdapter.m12980$$Nest$fgetdialogs(this.this$1).add(r62, r42);
                this.this$1.notifyItemMoved(r52, r62);
                SavedDialogsAdapter.m12982$$Nest$fputorderChanged(this.this$1, true);
                return true;
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void onSelectedChanged(RecyclerView.ViewHolder r4, int r5) {
                if (r4 == null) goto L6;
                RecyclerListView r0 = this.this$1.attachedToRecyclerView;
                if (r0 == null) goto L6;
                r0.hideSelector(false);
            L6:
                if (r5 != 0) goto L8;
                AndroidUtilities.cancelRunOnUIThread(SavedDialogsAdapter.m12981$$Nest$fgetnotifyOrderUpdate(this.this$1));
                AndroidUtilities.runOnUIThread(SavedDialogsAdapter.m12981$$Nest$fgetnotifyOrderUpdate(this.this$1), 300);
            L8:
                super.onSelectedChanged(r4, r5);
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void onSwiped(RecyclerView.ViewHolder r1, int r2) {
            }
        }

        /* renamed from: org.telegram.ui.Components.SharedMediaLayout$SavedDialogsAdapter$2, reason: invalid class name */
        public class AnonymousClass2 extends DialogCell {
            final /* synthetic */ SavedDialogsAdapter this$1;

            public AnonymousClass2(SavedDialogsAdapter r1, DialogsActivity r2, Context r3, boolean r4, boolean r5) {
                this.this$1 = r1;
                super(r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Cells.DialogCell
            public boolean getIsPinned() {
                RecyclerListView r0 = this.this$1.attachedToRecyclerView;
                if (r0 == null) goto L12;
                RecyclerView.Adapter r02 = r0.getAdapter();
                SavedDialogsAdapter r1 = this.this$1;
                if (r02 != r1) goto L14;
                int r03 = r1.attachedToRecyclerView.getChildAdapterPosition(this);
                if (r03 >= 0) goto L9;
                return false;
            L9:
                if (r03 < SavedDialogsAdapter.m12980$$Nest$fgetdialogs(this.this$1).size()) goto L11;
                return false;
            L11:
                return ((SavedMessagesController.SavedDialog) SavedDialogsAdapter.m12980$$Nest$fgetdialogs(this.this$1).get(r03)).pinned;
            L14:
                return false;
            L12:
                return false;
            }

            @Override // org.telegram.ui.Cells.DialogCell
            public boolean isForumCell() {
                return false;
            }
        }

        public static /* synthetic */ void $r8$lambda$Z5DztCyEPTCdVcF0ku9DA_lsu10(SavedDialogsAdapter r0) {
            r0.lambda$new$0();
        }

        /* renamed from: -$$Nest$fgetdialogs, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m12980$$Nest$fgetdialogs(SavedDialogsAdapter r0) {
            return r0.dialogs;
        }

        /* renamed from: -$$Nest$fgetnotifyOrderUpdate, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Runnable m12981$$Nest$fgetnotifyOrderUpdate(SavedDialogsAdapter r0) {
            return r0.notifyOrderUpdate;
        }

        /* renamed from: -$$Nest$fputorderChanged, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12982$$Nest$fputorderChanged(SavedDialogsAdapter r0, boolean r1) {
            r0.orderChanged = r1;
        }

        public SavedDialogsAdapter(SharedMediaLayout r3, Context r4) {
            this.this$0 = r3;
            this.oldDialogs = new ArrayList();
            this.dialogs = new ArrayList();
            this.notifyOrderUpdate = new SharedMediaLayout$SavedDialogsAdapter$$ExternalSyntheticLambda0(this);
            this.viewPool = new RecyclerView.RecycledViewPool();
            this.itemTouchHelper = new ItemTouchHelper(new AnonymousClass1(this));
            this.selectedDialogs = new HashSet();
            this.mContext = r4;
            SavedMessagesController r42 = SharedMediaLayout.m12834$$Nest$fgetprofileActivity(r3).getMessagesController().getSavedMessagesController();
            this.controller = r42;
            if (r3.includeSavedDialogs() == false) goto L5;
            r42.loadDialogs(false);
        L5:
            setHasStableIds(true);
            update(false);
        }

        private /* synthetic */ void lambda$new$0() {
            if (this.orderChanged == true) goto L5;
            return;
        L5:
            int r0 = 0;
            this.orderChanged = false;
            ArrayList<Long> r1 = new ArrayList();
        L7:
            if (r0 >= this.dialogs.size()) goto L12;
            if (this.dialogs.get(r0).pinned == false) goto L11;
            r1.add(Long.valueOf(this.dialogs.get(r0).dialogId));
        L11:
            r0 = r0 + 1;
            goto L7
        L12:
            SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getMessagesController().getSavedMessagesController().updatePinnedOrder(r1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.dialogs.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int r2) {
            if (r2 < 0) goto L9;
            if (r2 >= this.dialogs.size()) goto L9;
            return this.dialogs.get(r2).dialogId;
        L9:
            return r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return 13;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r9, int r10) {
            View r92 = r9.itemView;
            if ((r92 instanceof DialogCell) == true) goto L5;
            return;
        L5:
            DialogCell r1 = (DialogCell) r92;
            SavedMessagesController.SavedDialog r93 = this.dialogs.get(r10);
            r1.setDialog(r93.dialogId, r93.message, r93.getDate(), false, false);
            boolean r0 = true;
            r1.isSavedDialogCell = true;
            r1.setChecked(this.selectedDialogs.contains(Long.valueOf(r93.dialogId)), false);
            if ((r10 + 1) < getItemCount()) goto L9;
            r0 = false;
        L9:
            r1.useSeparator = r0;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r7, int r8) {
            AnonymousClass2 r0 = new AnonymousClass2(this, null, this.mContext, false, true);
            r0.setDialogCellDelegate(this.this$0);
            r0.isSavedDialog = true;
            r0.setBackgroundColor(SharedMediaLayout.m12897$$Nest$mgetThemedColor(this.this$0, Theme.key_windowBackgroundWhite));
            return new RecyclerListView.Holder(r0);
        }

        public void select(View r13) {
            if ((r13 instanceof DialogCell) == false) goto L74;
            DialogCell r132 = (DialogCell) r13;
            long r0 = r132.getDialogId();
            int r2 = 0;
            int r3 = 0;
        L7:
            if (r3 >= this.dialogs.size()) goto L12;
            if (this.dialogs.get(r3).dialogId == r0) goto L10;
            r3 = r3 + 1;
            goto L7
        L10:
            SavedMessagesController.SavedDialog r02 = this.dialogs.get(r3);
        L13:
            if (r02 != null) goto L15;
            return;
        L15:
            boolean r1 = this.selectedDialogs.contains(Long.valueOf(r02.dialogId));
            HashSet<Long> r32 = this.selectedDialogs;
            if (r1 == false) goto L22;
            r32.remove(Long.valueOf(r02.dialogId));
            if (this.selectedDialogs.size() > 0) goto L32;
            SharedMediaLayout r12 = this.this$0;
            if (r12.isActionModeShowed == false) goto L32;
            r12.showActionMode(false);
        L32:
            SharedMediaLayout.m12845$$Nest$fgetselectedMessagesCountTextView(this.this$0).setNumber(this.selectedDialogs.size(), true);
            if (this.selectedDialogs.size() <= 0) goto L35;
            boolean r14 = true;
        L36:
            Iterator<Long> r33 = this.selectedDialogs.iterator();
        L38:
            if (r33.hasNext() == false) goto L50;
            long r6 = r33.next().longValue();
            int r8 = 0;
        L41:
            if (r8 >= this.dialogs.size()) goto L48;
            SavedMessagesController.SavedDialog r9 = this.dialogs.get(r8);
            if (r9.dialogId == r6) goto L45;
            r8 = r8 + 1;
            goto L41
        L45:
            if (r9.pinned == true) goto L48;
            r14 = false;
        L48:
            if (r14 == true) goto L38;
        L50:
            if (SharedMediaLayout.m12832$$Nest$fgetpinItem(this.this$0) == null) goto L57;
            ActionBarMenuItem r34 = SharedMediaLayout.m12832$$Nest$fgetpinItem(this.this$0);
            if (r14 == false) goto L54;
            int r62 = 8;
        L55:
            r34.setVisibility(r62);
            goto L57
        L54:
            r62 = 0;
        L57:
            if (SharedMediaLayout.m12855$$Nest$fgetunpinItem(this.this$0) == null) goto L63;
            ActionBarMenuItem r35 = SharedMediaLayout.m12855$$Nest$fgetunpinItem(this.this$0);
            if (r14 == true) goto L62;
            r2 = 8;
        L62:
            r35.setVisibility(r2);
        L63:
            r132.setChecked(this.selectedDialogs.contains(Long.valueOf(r02.dialogId)), true);
            return;
        L35:
            r14 = false;
            goto L36
        L22:
            r32.add(Long.valueOf(r02.dialogId));
            if (this.selectedDialogs.size() <= 0) goto L32;
            SharedMediaLayout r15 = this.this$0;
            if (r15.isActionModeShowed == true) goto L32;
            r15.showActionMode(true);
            if (SharedMediaLayout.m12819$$Nest$fgetgotoItem(this.this$0) == null) goto L30;
            SharedMediaLayout.m12819$$Nest$fgetgotoItem(this.this$0).setVisibility(8);
        L30:
            if (SharedMediaLayout.m12815$$Nest$fgetforwardItem(this.this$0) == null) goto L32;
            SharedMediaLayout.m12815$$Nest$fgetforwardItem(this.this$0).setVisibility(8);
            goto L32
        L12:
            r02 = null;
            goto L13
        }

        public void unselectAll() {
            this.selectedDialogs.clear();
        }

        public void update(boolean r3) {
            this.oldDialogs.clear();
            this.oldDialogs.addAll(this.dialogs);
            this.dialogs.clear();
            this.dialogs.addAll(this.controller.allDialogs);
            if (r3 == false) goto L6;
            notifyDataSetChanged();
            return;
        }
    }

    public class SavedMessagesSearchAdapter extends RecyclerListView.SelectionAdapter {
        public final ArrayList<MessageObject> cachedMessages;
        private int count;
        private final int currentAccount;
        public final ArrayList<SavedMessagesController.SavedDialog> dialogs;
        private boolean endReached;
        private String lastQuery;
        private ReactionsLayoutInBubble.VisibleReaction lastReaction;
        int lastSearchId;
        public final ArrayList<MessageObject> loadedMessages;
        private boolean loading;
        private final Context mContext;
        public final ArrayList<MessageObject> messages;
        private int oldItemCounts;
        private int reqId;
        private Runnable searchRunnable;
        final /* synthetic */ SharedMediaLayout this$0;

        /* renamed from: org.telegram.ui.Components.SharedMediaLayout$SavedMessagesSearchAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends DialogCell {
            final /* synthetic */ SavedMessagesSearchAdapter this$1;

            public AnonymousClass1(SavedMessagesSearchAdapter r1, DialogsActivity r2, Context r3, boolean r4, boolean r5) {
                this.this$1 = r1;
                super(r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Cells.DialogCell
            public boolean isForumCell() {
                return false;
            }
        }

        /* renamed from: $r8$lambda$20jNDy4m7EmvjeSy6-Xrse20V8s, reason: not valid java name */
        public static /* synthetic */ void m12983$r8$lambda$20jNDy4m7EmvjeSy6Xrse20V8s(SavedMessagesSearchAdapter r0, int r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$sendRequest$1(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$40zWPhOPfupFo1inBbiCN3AXv4Y(SavedMessagesSearchAdapter r0) {
            r0.sendRequest();
        }

        public static /* synthetic */ void $r8$lambda$CYmyU43KS4DISbXMF70I3UoswRw(SavedMessagesSearchAdapter r0, int r1, TLRPC.TL_messages_search r2) {
            r0.lambda$sendRequest$2(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$Pk1qVoHd6vkqEP_r7hXCaiU4wKs(SavedMessagesSearchAdapter r0, Runnable r1, ArrayList r2, ArrayList r3, ArrayList r4, ArrayList r5) {
            r0.lambda$sendRequest$3(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$VMCl8G_KqqIcGjmP7pf4rdiag0U(SavedMessagesSearchAdapter r0, TLObject r1, int r2) {
            r0.lambda$sendRequest$0(r1, r2);
        }

        /* renamed from: -$$Nest$fgetcount, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m12984$$Nest$fgetcount(SavedMessagesSearchAdapter r0) {
            return r0.count;
        }

        /* renamed from: -$$Nest$fgetendReached, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m12985$$Nest$fgetendReached(SavedMessagesSearchAdapter r0) {
            return r0.endReached;
        }

        /* renamed from: -$$Nest$fgetlastQuery, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m12986$$Nest$fgetlastQuery(SavedMessagesSearchAdapter r0) {
            return r0.lastQuery;
        }

        /* renamed from: -$$Nest$fgetlastReaction, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ReactionsLayoutInBubble.VisibleReaction m12987$$Nest$fgetlastReaction(SavedMessagesSearchAdapter r0) {
            return r0.lastReaction;
        }

        public SavedMessagesSearchAdapter(SharedMediaLayout r2, Context r3) {
            this.this$0 = r2;
            this.dialogs = new ArrayList();
            this.messages = new ArrayList();
            this.loadedMessages = new ArrayList();
            this.cachedMessages = new ArrayList();
            this.endReached = false;
            this.oldItemCounts = 0;
            this.count = 0;
            this.reqId = -1;
            this.searchRunnable = new SharedMediaLayout$SavedMessagesSearchAdapter$$ExternalSyntheticLambda0(this);
            this.mContext = r3;
            this.currentAccount = SharedMediaLayout.m12834$$Nest$fgetprofileActivity(r2).getCurrentAccount();
            setHasStableIds(true);
        }

        private /* synthetic */ void lambda$sendRequest$0(TLObject r6, int r7) {
            if ((r6 instanceof TLRPC.messages_Messages) == true) goto L5;
            return;
        L5:
            if (r7 != this.lastSearchId) goto L30;
            TLRPC.messages_Messages r62 = (TLRPC.messages_Messages) r6;
            MessagesController.getInstance(this.currentAccount).putUsers(r62.users, false);
            MessagesController.getInstance(this.currentAccount).putChats(r62.chats, false);
            boolean r3 = true;
            MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(r62.users, r62.chats, true, true);
            int r72 = 0;
        L9:
            if (r72 >= r62.messages.size()) goto L14;
            MessageObject r2 = new MessageObject(this.currentAccount, r62.messages.get(r72), false, true);
            if (r2.hasValidGroupId() == false) goto L13;
            r2.isPrimaryGroupMessage = true;
        L13:
            r2.setQuery(this.lastQuery);
            this.loadedMessages.add(r2);
            r72 = r72 + 1;
            goto L9
        L14:
            this.count = r62.count;
            if ((r62 instanceof TLRPC.TL_messages_messagesSlice) == false) goto L22;
            if (this.loadedMessages.size() >= r62.count) goto L20;
            r3 = false;
        L20:
            this.endReached = r3;
        L24:
            updateMessages(false);
            this.loading = false;
            this.reqId = -1;
            return;
        L22:
            if ((r62 instanceof TLRPC.TL_messages_messages) == false) goto L24;
            this.endReached = true;
            goto L24
        }

        private /* synthetic */ void lambda$sendRequest$1(int r1, TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new SharedMediaLayout$SavedMessagesSearchAdapter$$ExternalSyntheticLambda4(this, r2, r1));
        }

        private /* synthetic */ void lambda$sendRequest$2(int r3, TLRPC.TL_messages_search r4) {
            if (r3 == this.lastSearchId) goto L5;
            return;
        L5:
            this.reqId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r4, new SharedMediaLayout$SavedMessagesSearchAdapter$$ExternalSyntheticLambda3(this, r3));
        }

        private /* synthetic */ void lambda$sendRequest$3(Runnable r3, ArrayList r4, ArrayList r5, ArrayList r6, ArrayList r7) {
            MessagesController.getInstance(this.currentAccount).putUsers(r5, true);
            MessagesController.getInstance(this.currentAccount).putChats(r6, true);
            AnimatedEmojiDrawable.getDocumentFetcher(this.currentAccount).processDocuments(r7);
            int r52 = 0;
        L4:
            if (r52 >= r4.size()) goto L11;
            MessageObject r62 = (MessageObject) r4.get(r52);
            if (r62.hasValidGroupId() == false) goto L10;
            if (r62.messageOwner.reactions == null) goto L10;
            r62.isPrimaryGroupMessage = true;
        L10:
            r62.setQuery(this.lastQuery);
            this.cachedMessages.add(r62);
            r52 = r52 + 1;
            goto L4
        L11:
            updateMessages(true);
            AndroidUtilities.runOnUIThread(r3, 540);
        }

        private void sendRequest() {
            if (TextUtils.isEmpty(this.lastQuery) == true) goto L5;
        L8:
            TLRPC.TL_messages_search r0 = new TLRPC.TL_messages_search();
            r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(UserConfig.getInstance(this.currentAccount).getClientUserId());
            r0.filter = new TLRPC.TL_inputMessagesFilterEmpty();
            r0.q = this.lastQuery;
            ReactionsLayoutInBubble.VisibleReaction r2 = this.lastReaction;
            if (r2 == null) goto L12;
            r0.flags |= 8;
            r0.saved_reaction.add(r2.toTLReaction());
        L12:
            if (this.loadedMessages.size() <= 0) goto L14;
            r0.offset_id = this.loadedMessages.get(r2.size() - 1).getId();
        L14:
            r0.limit = 10;
            this.endReached = false;
            int r1 = this.lastSearchId + 1;
            this.lastSearchId = r1;
            SharedMediaLayout$SavedMessagesSearchAdapter$$ExternalSyntheticLambda1 r22 = new SharedMediaLayout$SavedMessagesSearchAdapter$$ExternalSyntheticLambda1(this, r1, r0);
            if (this.lastReaction == null) goto L18;
            MessagesStorage.getInstance(this.currentAccount).searchSavedByTag(this.lastReaction.toTLReaction(), 0, this.lastQuery, 100, this.cachedMessages.size(), new SharedMediaLayout$SavedMessagesSearchAdapter$$ExternalSyntheticLambda2(this, r22), false);
            return;
        L18:
            r22.run();
            return;
        L5:
            if (this.lastReaction != null) goto L8;
            this.loading = false;
        }

        private void updateMessages(boolean r6) {
            this.messages.clear();
            HashSet r0 = new HashSet();
            int r2 = 0;
        L4:
            if (r2 >= this.loadedMessages.size()) goto L11;
            MessageObject r3 = this.loadedMessages.get(r2);
            if (r3 == null) goto L10;
            if (r0.contains(Integer.valueOf(r3.getId())) == true) goto L10;
            r0.add(Integer.valueOf(r3.getId()));
            this.messages.add(r3);
        L10:
            r2 = r2 + 1;
            goto L4
        L11:
            int r22 = 0;
        L13:
            if (r22 >= this.cachedMessages.size()) goto L20;
            MessageObject r32 = this.cachedMessages.get(r22);
            if (r32 == null) goto L19;
            if (r0.contains(Integer.valueOf(r32.getId())) == true) goto L19;
            r0.add(Integer.valueOf(r32.getId()));
            this.messages.add(r32);
        L19:
            r22 = r22 + 1;
            goto L13
        L20:
            if (r6 == true) goto L22;
        L23:
            int r62 = 0;
        L25:
            if (r62 >= SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0).length) goto L40;
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r62].selectedType != 11) goto L39;
            if (this.messages.isEmpty() == false) goto L39;
            if (this.dialogs.isEmpty() == false) goto L39;
            SpoilersTextView r02 = MediaPage.m12957$$Nest$fgetemptyView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r62]).title;
            if (this.lastReaction != null) goto L35;
        L37:
            CharSequence r23 = LocaleController.formatString(R.string.NoResultFoundFor, new Object[]{this.lastQuery});
        L38:
            r02.setText(r23);
            MediaPage.m12957$$Nest$fgetemptyView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r62]).button.setVisibility(8);
            MediaPage.m12957$$Nest$fgetemptyView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r62]).showProgress(false, true);
            goto L39
        L35:
            if (TextUtils.isEmpty(this.lastQuery) == false) goto L37;
            r23 = AndroidUtilities.replaceCharSequence("%s", LocaleController.getString(R.string.NoResultFoundForTag), this.lastReaction.toCharSequence(MediaPage.m12957$$Nest$fgetemptyView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r62]).title.getPaint().getFontMetricsInt()));
        L39:
            r62 = r62 + 1;
        L40:
            this.oldItemCounts = this.count;
            notifyDataSetChanged();
            return;
        L22:
            if (this.cachedMessages.isEmpty() == true) goto L40;
            goto L23
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.dialogs.size() + this.messages.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int r4) {
            if (r4 < 0) goto L4;
            int r0 = this.dialogs.size();
            ArrayList<SavedMessagesController.SavedDialog> r1 = this.dialogs;
            if (r4 >= r0) goto L10;
            int r3 = Objects.hash(new Object[]{1, Long.valueOf(r1.get(r4).dialogId)});
        L9:
            return r3;
        L10:
            int r42 = r4 - r1.size();
            if (r42 >= this.messages.size()) goto L14;
            r3 = Objects.hash(new Object[]{2, Long.valueOf(this.messages.get(r42).getSavedDialogId()), Integer.valueOf(this.messages.get(r42).getId())});
            goto L9
        L14:
            return r42;
        L4:
            return r4;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return 23;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return true;
        }

        public void loadMore() {
            if (this.endReached == false) goto L5;
            return;
        L5:
            if (this.loading == true) goto L10;
            this.loading = true;
            sendRequest();
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r9, int r10) {
            if (r10 < 0) goto L20;
            View r92 = r9.itemView;
            if ((r92 instanceof DialogCell) == false) goto L21;
            DialogCell r1 = (DialogCell) r92;
            if ((r10 + 1) >= getItemCount()) goto L10;
            boolean r93 = true;
        L11:
            r1.useSeparator = r93;
            int r94 = this.dialogs.size();
            ArrayList<SavedMessagesController.SavedDialog> r0 = this.dialogs;
            if (r10 >= r94) goto L15;
            SavedMessagesController.SavedDialog r8 = r0.get(r10);
            r1.setDialog(r8.dialogId, r8.message, r8.getDate(), false, false);
            return;
        L15:
            int r102 = r10 - r0.size();
            if (r102 >= this.messages.size()) goto L19;
            MessageObject r4 = this.messages.get(r102);
            r1.setDialog(r4.getSavedDialogId(), r4, r4.messageOwner.date, false, false);
            return;
        L19:
            return;
        L10:
            r93 = false;
            goto L11
        L21:
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r7, int r8) {
            AnonymousClass1 r0 = new AnonymousClass1(this, null, this.mContext, false, true);
            r0.setDialogCellDelegate(this.this$0);
            r0.isSavedDialog = true;
            r0.setBackgroundColor(SharedMediaLayout.m12897$$Nest$mgetThemedColor(this.this$0, Theme.key_windowBackgroundWhite));
            return new RecyclerListView.Holder(r0);
        }

        public void search(String r4, ReactionsLayoutInBubble.VisibleReaction r5) {
            if (TextUtils.equals(r4, this.lastQuery) == false) goto L11;
            ReactionsLayoutInBubble.VisibleReaction r0 = this.lastReaction;
            if (r0 != null) goto L7;
            if (r5 != null) goto L7;
            return;
        L7:
            if (r0 == null) goto L11;
            if (r0.equals(r5) == false) goto L11;
            return;
        L11:
            this.lastQuery = r4;
            this.lastReaction = r5;
            if (this.reqId < 0) goto L14;
            ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.reqId, true);
            this.reqId = -1;
        L14:
            this.cachedMessages.clear();
            this.loadedMessages.clear();
            this.messages.clear();
            int r52 = 0;
            this.count = 0;
            this.endReached = false;
            this.loading = true;
            this.dialogs.clear();
            if (this.lastReaction != null) goto L18;
            this.dialogs.addAll(MessagesController.getInstance(this.currentAccount).getSavedMessagesController().searchDialogs(r4));
        L18:
            if (r52 >= SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0).length) goto L24;
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r52].selectedType != 11) goto L22;
            MediaPage.m12957$$Nest$fgetemptyView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[r52]).showProgress(true, true);
        L22:
            r52 = r52 + 1;
            goto L18
        L24:
            if (this.lastReaction != null) goto L26;
            notifyDataSetChanged();
        L26:
            AndroidUtilities.cancelRunOnUIThread(this.searchRunnable);
            Runnable r42 = this.searchRunnable;
            if (this.lastReaction == null) goto L29;
            long r02 = 60;
        L30:
            AndroidUtilities.runOnUIThread(r42, r02);
            return;
        L29:
            r02 = 600;
            goto L30
        }
    }

    public class ScrollSlidingTextTabStripInner extends ScrollSlidingTextTabStrip {
        public int backgroundColor;
        protected Paint backgroundPaint;
        private Rect blurBounds;
        final /* synthetic */ SharedMediaLayout this$0;

        public ScrollSlidingTextTabStripInner(SharedMediaLayout r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
            this.backgroundColor = 0;
            this.blurBounds = new Rect();
        }

        @Override // org.telegram.ui.Components.ScrollSlidingTextTabStrip, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r6) {
            if (this.backgroundColor != 0) goto L5;
        L12:
            super.dispatchDraw(r6);
            return;
        L5:
            if (this.backgroundPaint != null) goto L7;
            this.backgroundPaint = new Paint();
        L7:
            this.backgroundPaint.setColor(this.backgroundColor);
            this.blurBounds.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
            r6.save();
            r6.translate(getScrollX(), 0.0f);
            r6.clipPath(this.clipPath);
            if (SharedConfig.chatBlurEnabled() == false) goto L10;
            this.this$0.drawBackgroundWithBlur(r6, getY(), this.blurBounds, this.backgroundPaint);
        L11:
            r6.translate(-getScrollX(), 0.0f);
            r6.restore();
            goto L12
        L10:
            r6.drawPaint(this.backgroundPaint);
            goto L11
        }

        public void drawBackground(Canvas r1) {
        }

        @Override // org.telegram.ui.Components.ScrollSlidingTextTabStrip, org.telegram.ui.ActionBar.Theme.Colorable
        public /* bridge */ /* synthetic */ int[] getColorKeys() {
            return super.getColorKeys();
        }

        @Override // android.view.View
        public void setBackgroundColor(int r1) {
            this.backgroundColor = r1;
            invalidate();
        }
    }

    public class SharedDocumentsAdapter extends RecyclerListView.FastScrollAdapter {
        private int currentType;
        private boolean inFastScrollMode;
        private Context mContext;
        final /* synthetic */ SharedMediaLayout this$0;

        /* renamed from: org.telegram.ui.Components.SharedMediaLayout$SharedDocumentsAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends SharedAudioCell {
            final /* synthetic */ SharedDocumentsAdapter this$1;

            public AnonymousClass1(SharedDocumentsAdapter r1, Context r2, int r3, Theme.ResourcesProvider r4) {
                this.this$1 = r1;
                super(r2, r3, r4);
            }

            @Override // org.telegram.ui.Cells.SharedAudioCell
            public boolean needPlayMessage(MessageObject r5) {
                if (r5.isVoice() == false) goto L5;
            L12:
                boolean r52 = MediaController.getInstance().playMessage(r5);
                MediaController r0 = MediaController.getInstance();
                if (r52 == false) goto L15;
                ArrayList<MessageObject> r4 = SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$1.this$0)[SharedDocumentsAdapter.m12988$$Nest$fgetcurrentType(this.this$1)].messages;
            L16:
                r0.setVoiceMessagesPlaylist(r4, false);
                return r52;
            L15:
                r4 = null;
                goto L16
            L5:
                if (r5.isRoundVideo() == true) goto L12;
                if (r5.isMusic() == true) goto L10;
                return false;
            L10:
                return MediaController.getInstance().setPlaylist(SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$1.this$0)[SharedDocumentsAdapter.m12988$$Nest$fgetcurrentType(this.this$1)].messages, r5, SharedMediaLayout.m12826$$Nest$fgetmergeDialogId(this.this$1.this$0));
            }
        }

        /* renamed from: -$$Nest$fgetcurrentType, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m12988$$Nest$fgetcurrentType(SharedDocumentsAdapter r0) {
            return r0.currentType;
        }

        public SharedDocumentsAdapter(SharedMediaLayout r1, Context r2, int r3) {
            this.this$0 = r1;
            this.mContext = r2;
            this.currentType = r3;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            boolean r0 = SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType].loadingAfterFastScroll;
            SharedMediaLayout r1 = this.this$0;
            if (r0 == false) goto L7;
            return SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(r1)[this.currentType].getTotalCount();
        L7:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(r1)[this.currentType].messages.size() != 0) goto L12;
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType].loading == true) goto L12;
            return 1;
        L12:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType].messages.size() == 0) goto L14;
        L20:
            int r02 = SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType].getTotalCount();
            SharedMediaLayout r3 = this.this$0;
            if (r02 != 0) goto L35;
            int r03 = SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(r3)[this.currentType].getStartOffset() + SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType].getMessages().size();
            if (r03 != 0) goto L25;
            return r03;
        L25:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType].endReached[0] == false) goto L29;
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType].endReached[1] == false) goto L29;
            return r03;
        L29:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType].getEndLoadingStubs() == 0) goto L33;
            return r03 + SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType].getEndLoadingStubs();
        L33:
            return r03 + 1;
        L35:
            return Math.max(SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(r3)[this.currentType].getTotalCount(), SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType].getStartOffset() + SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType].getMessages().size());
        L14:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType].endReached[0] == false) goto L18;
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType].endReached[1] == true) goto L20;
        L18:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType].startReached == false) goto L20;
            return 0;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r4) {
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType].sections.size() != 0) goto L9;
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType].loading == true) goto L9;
            return 9;
        L9:
            if (r4 >= SharedMediaData.m12992$$Nest$fgetstartOffset(SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType])) goto L11;
            return 8;
        L11:
            if (r4 >= (SharedMediaData.m12992$$Nest$fgetstartOffset(SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType]) + SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType].messages.size())) goto L24;
            int r3 = this.currentType;
            if (r3 != 2) goto L15;
            return 10;
        L15:
            if (r3 == 4) goto L23;
            return 7;
        L23:
            return 10;
        L24:
            return 8;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public String getLetter(int r3) {
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType].fastScrollPeriods != null) goto L5;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L5:
            ArrayList<Period> r2 = SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType].fastScrollPeriods;
            if (r2.isEmpty() == true) goto L17;
            int r0 = 0;
        L9:
            if (r0 >= r2.size()) goto L16;
            if (r3 <= r2.get(r0).startOffset) goto L13;
            r0 = r0 + 1;
            goto L9
        L13:
            return r2.get(r0).formatedDate;
        L16:
            return r2.get(r2.size() - 1).formatedDate;
        L17:
            return _UrlKt.FRAGMENT_ENCODE_SET;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public void getPositionForScrollProgress(RecyclerListView r4, float r5, int[] r6) {
            int r1 = r4.getChildAt(0).getMeasuredHeight();
            float r52 = r5 * ((getTotalItemsCount() * r1) - (r4.getMeasuredHeight() - r4.getPaddingTop()));
            r6[0] = (int) (r52 / r1);
            r6[1] = ((int) r52) % r1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public int getTotalItemsCount() {
            return SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType].getTotalCount();
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r10, int r11) {
            ArrayList<MessageObject> r0 = SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType].messages;
            int r1 = r10.getItemViewType();
            boolean r3 = false;
            if (r1 != 7) goto L5;
            View r102 = r10.itemView;
            if ((r102 instanceof SharedDocumentCell) == true) goto L30;
            return;
        L30:
            SharedDocumentCell r103 = (SharedDocumentCell) r102;
            MessageObject r12 = r0.get(r11 - SharedMediaData.m12992$$Nest$fgetstartOffset(SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType]));
            if (r11 == (r0.size() - 1)) goto L33;
            boolean r112 = true;
        L34:
            r103.setDocument(r12, r112);
            SharedMediaLayout r113 = this.this$0;
            if (r113.isActionModeShowed == false) goto L45;
            SparseArray[] r114 = SharedMediaLayout.m12844$$Nest$fgetselectedFiles(r113);
            if (r12.getDialogId() != SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0)) goto L39;
            char r02 = 0;
        L41:
            if (r114[r02].indexOfKey(r12.getId()) < 0) goto L43;
            r3 = true;
        L43:
            r103.setChecked(r3, !SharedMediaLayout.m12839$$Nest$fgetscrolling(this.this$0));
            return;
        L39:
            r02 = 1;
            goto L41
        L45:
            r103.setChecked(false, !SharedMediaLayout.m12839$$Nest$fgetscrolling(r113));
            return;
        L33:
            r112 = false;
            goto L34
        L5:
            if (r1 != 10) goto L47;
            View r104 = r10.itemView;
            if ((r104 instanceof SharedAudioCell) == false) goto L48;
            SharedAudioCell r105 = (SharedAudioCell) r104;
            MessageObject r13 = r0.get(r11 - SharedMediaData.m12992$$Nest$fgetstartOffset(SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[this.currentType]));
            if (r11 == (r0.size() - 1)) goto L13;
            boolean r115 = true;
        L14:
            r105.setMessageObject(r13, r115);
            SharedMediaLayout r116 = this.this$0;
            if (r116.isActionModeShowed == false) goto L25;
            SparseArray[] r117 = SharedMediaLayout.m12844$$Nest$fgetselectedFiles(r116);
            if (r13.getDialogId() != SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0)) goto L19;
            char r03 = 0;
        L21:
            if (r117[r03].indexOfKey(r13.getId()) < 0) goto L23;
            r3 = true;
        L23:
            r105.setChecked(r3, !SharedMediaLayout.m12839$$Nest$fgetscrolling(this.this$0));
            return;
        L19:
            r03 = 1;
            goto L21
        L25:
            r105.setChecked(false, !SharedMediaLayout.m12839$$Nest$fgetscrolling(r116));
            return;
        L13:
            r115 = false;
            goto L14
        L48:
            return;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r5, int r6) {
            if (r6 != 7) goto L5;
            SharedDocumentCell r52 = new SharedDocumentCell(this.mContext, 0, SharedMediaLayout.m12835$$Nest$fgetresourcesProvider(this.this$0));
            r52.setGlobalGradientView(this.this$0.globalGradientView);
            View r53 = r52;
        L27:
            r53.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r53);
        L5:
            if (r6 != 8) goto L7;
            FlickerLoadingView r54 = new FlickerLoadingView(this.mContext, SharedMediaLayout.m12835$$Nest$fgetresourcesProvider(this.this$0));
            if (this.currentType != 2) goto L24;
            r54.setViewType(4);
        L25:
            r54.showDate(false);
            r54.setIsSingleCell(true);
            r54.setGlobalGradientView(this.this$0.globalGradientView);
            r53 = r54;
            goto L27
        L24:
            r54.setViewType(3);
            goto L25
        L7:
            if (r6 != 9) goto L9;
            View r4 = SharedMediaLayout.createEmptyStubView(this.mContext, this.currentType, SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0), SharedMediaLayout.m12835$$Nest$fgetresourcesProvider(this.this$0));
            r4.setLayoutParams(new RecyclerView.LayoutParams(-1, -1));
            return new RecyclerListView.Holder(r4);
        L9:
            if (this.currentType == 4) goto L11;
        L15:
            View r55 = new AnonymousClass1(this, this.mContext, 0, SharedMediaLayout.m12835$$Nest$fgetresourcesProvider(this.this$0));
        L16:
            SharedAudioCell r62 = (SharedAudioCell) r55;
            r62.setGlobalGradientView(this.this$0.globalGradientView);
            r53 = r55;
            if (this.currentType != 4) goto L27;
            SharedMediaLayout.m12799$$Nest$fgetaudioCache(this.this$0).add(r62);
            r53 = r55;
            goto L27
        L11:
            if (SharedMediaLayout.m12800$$Nest$fgetaudioCellCache(this.this$0).isEmpty() == true) goto L15;
            View r56 = (View) SharedMediaLayout.m12800$$Nest$fgetaudioCellCache(this.this$0).get(0);
            SharedMediaLayout.m12800$$Nest$fgetaudioCellCache(this.this$0).remove(0);
            ViewGroup r63 = (ViewGroup) r56.getParent();
            r55 = r56;
            if (r63 == null) goto L16;
            r63.removeView(r56);
            r55 = r56;
            goto L16
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public void onFinishFastScroll(RecyclerListView r4) {
            if (this.inFastScrollMode == false) goto L18;
            int r0 = 0;
            this.inFastScrollMode = false;
            if (r4 == null) goto L19;
            int r1 = 0;
        L8:
            if (r0 >= r4.getChildCount()) goto L13;
            r1 = SharedMediaLayout.m12921$$Nest$smgetMessageId(r4.getChildAt(r0));
            if (r1 != 0) goto L13;
            r0 = r0 + 1;
        L13:
            if (r1 != 0) goto L20;
            SharedMediaLayout.m12891$$Nest$mfindPeriodAndJumpToDate(this.this$0, this.currentType, r4, true);
            return;
        L20:
            return;
        L19:
            return;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public void onStartFastScroll() {
            this.inFastScrollMode = true;
            MediaPage r2 = SharedMediaLayout.m12893$$Nest$mgetMediaPage(this.this$0, this.currentType);
            if (r2 == null) goto L6;
            SharedMediaLayout.m12923$$Nest$smshowFastScrollHint(r2, null, false);
            return;
        }
    }

    public class SharedLinksAdapter extends RecyclerListView.SectionsAdapter {
        private Context mContext;
        final /* synthetic */ SharedMediaLayout this$0;

        public SharedLinksAdapter(SharedMediaLayout r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public int getCountForSection(int r4) {
            int r2 = 1;
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[3].sections.size() != 0) goto L8;
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[3].loading == true) goto L8;
            return 1;
        L8:
            if (r4 >= SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[3].sections.size()) goto L15;
            int r3 = SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[3].sectionArrays.get(SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[3].sections.get(r4)).size();
            if (r4 != 0) goto L14;
            r2 = 0;
        L14:
            return r3 + r2;
        L15:
            return 1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public Object getItem(int r1, int r2) {
            return null;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public int getItemViewType(int r3, int r4) {
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[3].sections.size() != 0) goto L9;
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[3].loading == true) goto L9;
            return 5;
        L9:
            if (r3 >= SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[3].sections.size()) goto L15;
            if (r3 == 0) goto L13;
            if (r4 != 0) goto L17;
            return 3;
        L17:
            return 4;
        L13:
            return 4;
        L15:
            return 6;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public String getLetter(int r1) {
            return null;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public void getPositionForScrollProgress(RecyclerListView r1, float r2, int[] r3) {
            r3[0] = 0;
            r3[1] = 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public int getSectionCount() {
            int r2 = 1;
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[3].sections.size() == 0) goto L5;
        L7:
            int r0 = SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[3].sections.size();
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[3].sections.isEmpty() == false) goto L10;
        L13:
            r2 = 0;
        L15:
            return r0 + r2;
        L10:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[3].endReached[0] == false) goto L15;
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[3].endReached[1] == false) goto L15;
        L5:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[3].loading == true) goto L7;
            return 1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public View getSectionHeaderView(int r4, View r5) {
            if (r5 != null) goto L4;
            r5 = new GraySectionCell(this.mContext, 28, SharedMediaLayout.m12835$$Nest$fgetresourcesProvider(this.this$0));
        L4:
            if (r4 != 0) goto L8;
            r5.setAlpha(0.0f);
            return r5;
        L8:
            if (r4 >= SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[3].sections.size()) goto L10;
            r5.setAlpha(1.0f);
            MessageObject r3 = SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[3].sectionArrays.get(SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[3].sections.get(r4)).get(0);
            ((GraySectionCell) r5).setText(LocaleController.formatSectionDate(r3.messageOwner.date));
        L10:
            return r5;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r3, int r4, int r5) {
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[3].sections.size() == 0) goto L5;
        L7:
            if (r4 == 0) goto L11;
            if (r5 != 0) goto L13;
            return false;
        L13:
            return true;
        L11:
            return true;
        L5:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[3].loading == true) goto L7;
            return false;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public void onBindViewHolder(int r8, int r9, RecyclerView.ViewHolder r10) {
            if (r10.getItemViewType() != 6) goto L5;
            return;
        L5:
            if (r10.getItemViewType() == 5) goto L46;
            ArrayList<MessageObject> r0 = SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[3].sectionArrays.get(SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[3].sections.get(r8));
            int r2 = r10.getItemViewType();
            boolean r3 = false;
            if (r2 != 3) goto L9;
            MessageObject r7 = r0.get(0);
            View r82 = r10.itemView;
            if ((r82 instanceof GraySectionCell) == false) goto L49;
            ((GraySectionCell) r82).setText(LocaleController.formatSectionDate(r7.messageOwner.date));
            return;
        L49:
            return;
        L9:
            if (r2 != 4) goto L50;
            if (r8 == 0) goto L14;
            r9 = r9 - 1;
        L14:
            if ((r10.itemView instanceof SharedLinkCell) == false) goto L47;
            if (r9 >= 0) goto L17;
            return;
        L17:
            if (r9 >= r0.size()) goto L51;
            SharedLinkCell r102 = (SharedLinkCell) r10.itemView;
            MessageObject r22 = r0.get(r9);
            if (r9 == (r0.size() - 1)) goto L22;
        L27:
            boolean r83 = true;
        L28:
            r102.setLink(r22, r83);
            SharedMediaLayout r84 = this.this$0;
            if (r84.isActionModeShowed == false) goto L39;
            SparseArray[] r85 = SharedMediaLayout.m12844$$Nest$fgetselectedFiles(r84);
            if (r22.getDialogId() != SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0)) goto L33;
            char r92 = 0;
        L35:
            if (r85[r92].indexOfKey(r22.getId()) < 0) goto L37;
            r3 = true;
        L37:
            r102.setChecked(r3, !SharedMediaLayout.m12839$$Nest$fgetscrolling(this.this$0));
            return;
        L33:
            r92 = 1;
            goto L35
        L39:
            r102.setChecked(false, !SharedMediaLayout.m12839$$Nest$fgetscrolling(r84));
            return;
        L22:
            if (r8 == (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[3].sections.size() - 1)) goto L24;
        L26:
            r83 = false;
            goto L28
        L24:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[3].loading == false) goto L26;
        L51:
            return;
        L47:
            return;
        L50:
            return;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r4, int r5) {
            if (r5 != 3) goto L5;
            GraySectionCell r52 = new GraySectionCell(this.mContext, 28, SharedMediaLayout.m12835$$Nest$fgetresourcesProvider(this.this$0));
        L13:
            r52.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r52);
        L5:
            if (r5 != 4) goto L7;
            SharedLinkCell r53 = new SharedLinkCell(this.mContext, 0, SharedMediaLayout.m12835$$Nest$fgetresourcesProvider(this.this$0));
            r53.setDelegate(this.this$0.sharedLinkCellDelegate);
            r52 = r53;
            goto L13
        L7:
            if (r5 == 5) goto L9;
            FlickerLoadingView r54 = new FlickerLoadingView(this.mContext, SharedMediaLayout.m12835$$Nest$fgetresourcesProvider(this.this$0));
            r54.setIsSingleCell(true);
            r54.showDate(false);
            r54.setViewType(5);
            r52 = r54;
            goto L13
        L9:
            View r3 = SharedMediaLayout.createEmptyStubView(this.mContext, 3, SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0), SharedMediaLayout.m12835$$Nest$fgetresourcesProvider(this.this$0));
            r3.setLayoutParams(new RecyclerView.LayoutParams(-1, -1));
            return new RecyclerListView.Holder(r3);
        }
    }

    public static class SharedMediaData {
        private int endLoadingStubs;
        public boolean[] endReached;
        public boolean fastScrollDataLoaded;
        public ArrayList<Period> fastScrollPeriods;
        public int filterType;
        public int frozenEndLoadingStubs;
        public ArrayList<MessageObject> frozenMessages;
        public int frozenStartOffset;
        private boolean hasPhotos;
        private boolean hasVideos;
        public boolean isFrozen;
        public boolean loading;
        public boolean loadingAfterFastScroll;
        public int[] max_id;
        public ArrayList<MessageObject> messages;
        public SparseArray<MessageObject>[] messagesDict;
        public int min_id;
        RecyclerView.RecycledViewPool recycledViewPool;
        public int requestIndex;
        public HashMap<String, ArrayList<MessageObject>> sectionArrays;
        public ArrayList<String> sections;
        private int startOffset;
        public boolean startReached;
        public int[] totalCount;

        /* renamed from: -$$Nest$fgetendLoadingStubs, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m12989$$Nest$fgetendLoadingStubs(SharedMediaData r0) {
            return r0.endLoadingStubs;
        }

        /* renamed from: -$$Nest$fgethasPhotos, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m12990$$Nest$fgethasPhotos(SharedMediaData r0) {
            return r0.hasPhotos;
        }

        /* renamed from: -$$Nest$fgethasVideos, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m12991$$Nest$fgethasVideos(SharedMediaData r0) {
            return r0.hasVideos;
        }

        /* renamed from: -$$Nest$fgetstartOffset, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m12992$$Nest$fgetstartOffset(SharedMediaData r0) {
            return r0.startOffset;
        }

        /* renamed from: -$$Nest$fputendLoadingStubs, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12993$$Nest$fputendLoadingStubs(SharedMediaData r0, int r1) {
            r0.endLoadingStubs = r1;
        }

        /* renamed from: -$$Nest$fputstartOffset, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12994$$Nest$fputstartOffset(SharedMediaData r0, int r1) {
            r0.startOffset = r1;
        }

        public SharedMediaData() {
            this.messages = new ArrayList();
            this.messagesDict = new SparseArray[]{new SparseArray(), new SparseArray()};
            this.sections = new ArrayList();
            this.sectionArrays = new HashMap();
            this.fastScrollPeriods = new ArrayList();
            this.totalCount = new int[]{0, 0};
            this.endReached = new boolean[]{false, true};
            this.max_id = new int[]{0, 0};
            this.startReached = true;
            this.filterType = 0;
            this.frozenMessages = new ArrayList();
            this.recycledViewPool = new RecyclerView.RecycledViewPool();
        }

        private void updateIds(MessageObject r3, int r4, boolean r5) {
            if (r5 == false) goto L4;
            this.max_id[r4] = Math.max(r3.getId(), this.max_id[r4]);
            this.min_id = Math.min(r3.getId(), this.min_id);
            return;
        L4:
            if (r3.getId() <= 0) goto L9;
            this.max_id[r4] = Math.min(r3.getId(), this.max_id[r4]);
            this.min_id = Math.max(r3.getId(), this.min_id);
            return;
        }

        public boolean addMessage(MessageObject r5, int r6, boolean r7, boolean r8) {
            if (this.messagesDict[r6].indexOfKey(r5.getId()) < 0) goto L6;
            return false;
        L6:
            if (LocaleUtils.isCustomEmojiOnlyLinkMessage(r5.messageOwner) == false) goto L9;
            updateIds(r5, r6, r8);
            return false;
        L9:
            ArrayList<MessageObject> r0 = this.sectionArrays.get(r5.monthKey);
            if (r0 != null) goto L15;
            r0 = new ArrayList();
            this.sectionArrays.put(r5.monthKey, r0);
            ArrayList<String> r2 = this.sections;
            if (r7 == false) goto L14;
            r2.add(0, r5.monthKey);
            goto L15
        L14:
            r2.add(r5.monthKey);
        L15:
            if (r7 == false) goto L17;
            r0.add(0, r5);
            this.messages.add(0, r5);
        L18:
            this.messagesDict[r6].put(r5.getId(), r5);
            updateIds(r5, r6, r8);
            if (this.hasVideos == true) goto L24;
            if (r5.isVideo() == false) goto L24;
            this.hasVideos = true;
        L24:
            if (this.hasPhotos == false) goto L26;
        L28:
            return true;
        L26:
            if (r5.isPhoto() == false) goto L28;
            this.hasPhotos = true;
            goto L28
        L17:
            r0.add(r5);
            this.messages.add(r5);
            goto L18
        }

        public MessageObject deleteMessage(int r4, int r5) {
            MessageObject r42 = this.messagesDict[r5].get(r4);
            if (r42 != null) goto L5;
            return null;
        L5:
            ArrayList<MessageObject> r1 = this.sectionArrays.get(r42.monthKey);
            if (r1 != null) goto L8;
            return null;
        L8:
            r1.remove(r42);
            this.messages.remove(r42);
            this.messagesDict[r5].remove(r42.getId());
            if (r1.isEmpty() == false) goto L11;
            this.sectionArrays.remove(r42.monthKey);
            this.sections.remove(r42.monthKey);
        L11:
            int[] r3 = this.totalCount;
            int r0 = r3[r5] - 1;
            r3[r5] = r0;
            if (r0 >= 0) goto L14;
            r3[r5] = 0;
        L14:
            return r42;
        }

        public int getEndLoadingStubs() {
            if (this.isFrozen == false) goto L7;
            return this.frozenEndLoadingStubs;
        L7:
            return this.endLoadingStubs;
        }

        public ArrayList<MessageObject> getMessages() {
            if (this.isFrozen == false) goto L7;
            return this.frozenMessages;
        L7:
            return this.messages;
        }

        public int getStartOffset() {
            if (this.isFrozen == false) goto L7;
            return this.frozenStartOffset;
        L7:
            return this.startOffset;
        }

        public int getTotalCount() {
            int[] r2 = this.totalCount;
            return r2[0] + r2[1];
        }

        public void replaceMid(int r3, int r4, int r5) {
            MessageObject r0 = this.messagesDict[r3].get(r4);
            if (r0 == null) goto L6;
            this.messagesDict[r3].remove(r4);
            this.messagesDict[r3].put(r5, r0);
            r0.messageOwner.id = r5;
            int[] r2 = this.max_id;
            r2[r3] = Math.min(r5, r2[r3]);
            return;
        }

        public void setEndReached(int r1, boolean r2) {
            this.endReached[r1] = r2;
        }

        public void setListFrozen(boolean r2) {
            if (this.isFrozen == r2) goto L10;
            this.isFrozen = r2;
            if (r2 == false) goto L9;
            this.frozenStartOffset = this.startOffset;
            this.frozenEndLoadingStubs = this.endLoadingStubs;
            this.frozenMessages.clear();
            this.frozenMessages.addAll(this.messages);
            return;
        L9:
            return;
        }

        public void setMaxId(int r1, int r2) {
            this.max_id[r1] = r2;
        }

        public void setTotalCount(int r1, int r2) {
            this.totalCount[r1] = r2;
        }
    }

    public static class SharedMediaListView extends InternalListView {
        private final ArrayList<SharedPhotoVideoCell2> animationSupportingSortedCells;
        private int animationSupportingSortedCellsOffset;
        protected StaticLayout archivedHintLayout;
        protected float archivedHintLayoutLeft;
        protected float archivedHintLayoutWidth;
        protected TextPaint archivedHintPaint;
        final ArrayList<SharedPhotoVideoCell2> drawingViews;
        final ArrayList<SharedPhotoVideoCell2> drawingViews2;
        final ArrayList<SharedPhotoVideoCell2> drawingViews3;
        final HashSet<SharedPhotoVideoCell2> excludeDrawViews;
        UserListPoller poller;

        public SharedMediaListView(Context r1) {
            super(r1);
            this.excludeDrawViews = new HashSet();
            this.drawingViews = new ArrayList();
            this.drawingViews2 = new ArrayList();
            this.drawingViews3 = new ArrayList();
            this.animationSupportingSortedCells = new ArrayList();
        }

        public void checkHighlightCell(SharedPhotoVideoCell2 r1) {
        }

        @Override // org.telegram.ui.Components.BlurredRecyclerView, org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r30) {
            float r8 = 1.0f;
            Float r2 = Float.valueOf(1.0f);
            RecyclerListView.FastScrollAdapter r3 = getMovingAdapter();
            RecyclerListView.FastScrollAdapter r4 = getSupportingAdapter();
            if (isThisListView() == true) goto L5;
        L170:
            int r10 = 0;
        L172:
            if (r10 >= getChildCount()) goto L189;
            View r32 = getChildAt(r10);
            int r42 = SharedMediaLayout.m12921$$Nest$smgetMessageId(r32);
            if (r42 == 0) goto L181;
            if (getMessageAlphaEnter() == null) goto L181;
            if (getMessageAlphaEnter().get(r42, null) == null) goto L181;
            float r12 = getMessageAlphaEnter().get(r42, r2).floatValue();
        L183:
            if ((r32 instanceof SharedDocumentCell) == false) goto L186;
            ((SharedDocumentCell) r32).setEnterAnimationAlpha(r12);
        L188:
            r10 = r10 + 1;
            goto L172
        L186:
            if ((r32 instanceof SharedAudioCell) == false) goto L188;
            ((SharedAudioCell) r32).setEnterAnimationAlpha(r12);
        L181:
            r12 = 1.0f;
            goto L183
        L189:
            super.dispatchDraw(r30);
            return;
        L5:
            if (getAdapter() != r3) goto L170;
            float r5 = getMeasuredHeight();
            if (isChangeColumnsAnimation() == false) goto L88;
            int r13 = -1;
            int r14 = -1;
            int r7 = 0;
        L10:
            if (r7 >= getChildCount()) goto L21;
            int r15 = getChildAdapterPosition(getChildAt(r7));
            if (r15 < 0) goto L16;
            if (r15 > r14) goto L15;
            if (r14 != (-1)) goto L16;
        L15:
            r14 = r15;
        L16:
            if (r15 < 0) goto L20;
            if (r15 < r13) goto L19;
            if (r13 != (-1)) goto L20;
        L19:
            r13 = r15;
        L20:
            r7 = r7 + 1;
            goto L10
        L21:
            int r122 = -1;
            int r152 = -1;
            int r72 = 0;
            boolean r16 = true;
        L23:
            if (r72 >= getSupportingListView().getChildCount()) goto L34;
            float r17 = r8;
            int r82 = getSupportingListView().getChildAdapterPosition(getSupportingListView().getChildAt(r72));
            if (r82 < 0) goto L29;
            if (r82 > r122) goto L28;
            if (r122 != (-1)) goto L29;
        L28:
            r122 = r82;
        L29:
            if (r82 < 0) goto L33;
            if (r82 < r152) goto L32;
            if (r152 != (-1)) goto L33;
        L32:
            r152 = r82;
        L33:
            r72 = r72 + 1;
            r8 = r17;
            goto L23
        L34:
            float r172 = r8;
            if (r13 < 0) goto L58;
            if (r152 < 0) goto L58;
            if (getPinchCenterPosition() < 0) goto L58;
            int r6 = (int) Math.ceil(r3.getItemCount() / getColumnsCount());
            int r73 = (int) Math.ceil(r3.getItemCount() / getAnimateToColumnsCount());
            int r83 = ((getPinchCenterPosition() / getAnimateToColumnsCount()) - (r152 / getAnimateToColumnsCount())) - ((getPinchCenterPosition() / getColumnsCount()) - (r13 / getColumnsCount()));
            if (((r13 / getColumnsCount()) - r83) >= 0) goto L44;
            if (getAnimateToColumnsCount() >= getColumnsCount()) goto L44;
        L47:
            r83 = 0;
        L49:
            if (((r122 / getColumnsCount()) + r83) < r6) goto L53;
            if (getAnimateToColumnsCount() <= getColumnsCount()) goto L53;
        L56:
            r83 = 0;
        L57:
            int r62 = (int) ((getAnimateToColumnsCount() - getColumnsCount()) * ((getPinchCenterPosition() % getColumnsCount()) / (getColumnsCount() - 1)));
        L59:
            this.animationSupportingSortedCells.clear();
            this.excludeDrawViews.clear();
            this.drawingViews.clear();
            this.drawingViews2.clear();
            this.drawingViews3.clear();
            this.animationSupportingSortedCellsOffset = 0;
            int r74 = 0;
        L61:
            if (r74 >= getSupportingListView().getChildCount()) goto L74;
            View r9 = getSupportingListView().getChildAt(r74);
            if (r9.getTop() > getMeasuredHeight()) goto L73;
            if (r9.getBottom() < 0) goto L73;
            if ((r9 instanceof SharedPhotoVideoCell2) == false) goto L71;
            this.animationSupportingSortedCells.add((SharedPhotoVideoCell2) r9);
            goto L73
        L71:
            if ((r9 instanceof TextView) == false) goto L73;
            this.animationSupportingSortedCellsOffset++;
        L73:
            r74 = r74 + 1;
            goto L61
        L74:
            this.drawingViews.addAll(this.animationSupportingSortedCells);
            RecyclerListView.FastScroll r75 = getFastScroll();
            if (r75 != null) goto L77;
        L87:
            int r92 = r83;
            int r84 = r62;
        L89:
            float r11 = r5;
            int r33 = 0;
        L91:
            if (r33 >= getChildCount()) goto L130;
            View r43 = getChildAt(r33);
            if (r43.getTop() > getMeasuredHeight()) goto L127;
            if (r43.getBottom() < 0) goto L127;
            if ((r43 instanceof SharedPhotoVideoCell2) == false) goto L129;
            SharedPhotoVideoCell2 r44 = (SharedPhotoVideoCell2) getChildAt(r33);
            checkHighlightCell(r44);
            MessageObject r52 = r44.getMessageObject();
            if (r52 != null) goto L102;
        L106:
            float r53 = r172;
        L107:
            r44.setImageAlpha(r53, !isChangeColumnsAnimation());
            if (isChangeColumnsAnimation() == false) goto L120;
            int r54 = (((GridLayoutManager.LayoutParams) r44.getLayoutParams()).getViewAdapterPosition() % getColumnsCount()) + r84;
            int r63 = (((((((GridLayoutManager.LayoutParams) r44.getLayoutParams()).getViewAdapterPosition() - r13) / getColumnsCount()) + r92) * getAnimateToColumnsCount()) + r54) + this.animationSupportingSortedCellsOffset;
            if (r54 < 0) goto L120;
            if (r54 >= getAnimateToColumnsCount()) goto L120;
            if (r63 < 0) goto L120;
            if (r63 >= this.animationSupportingSortedCells.size()) goto L120;
            float r55 = AndroidUtilities.lerp(r172, (this.animationSupportingSortedCells.get(r63).getMeasuredWidth() - AndroidUtilities.dpf2(2.0f)) / (r44.getMeasuredWidth() - AndroidUtilities.dpf2(2.0f)), getChangeColumnsProgress());
            float r76 = r44.getLeft();
            float r123 = r44.getTop();
            float r142 = this.animationSupportingSortedCells.get(r63).getLeft();
            float r102 = this.animationSupportingSortedCells.get(r63).getTop();
            r44.setPivotX(0.0f);
            r44.setPivotY(0.0f);
            r44.setImageScale(r55, !isChangeColumnsAnimation());
            r44.setTranslationX((r142 - r76) * getChangeColumnsProgress());
            r44.setTranslationY((r102 - r123) * getChangeColumnsProgress());
            r44.setCrossfadeView(this.animationSupportingSortedCells.get(r63), getChangeColumnsProgress(), getAnimateToColumnsCount());
            this.excludeDrawViews.add(this.animationSupportingSortedCells.get(r63));
            this.drawingViews3.add(r44);
            r30.save();
            r30.translate(r44.getX(), r44.getY());
            r44.draw(r30);
            r30.restore();
            if (r44.getY() >= r11) goto L119;
            r11 = r44.getY();
        L119:
            boolean r56 = r16;
        L121:
            if (r56 == true) goto L129;
            if (isChangeColumnsAnimation() == false) goto L125;
            this.drawingViews2.add(r44);
        L125:
            r44.setCrossfadeView(null, 0.0f, 0);
            r44.setTranslationX(0.0f);
            r44.setTranslationY(0.0f);
            r44.setImageScale(1.0f, !isChangeColumnsAnimation());
        L120:
            r56 = false;
            goto L121
        L102:
            if (getMessageAlphaEnter() == null) goto L106;
            if (getMessageAlphaEnter().get(r52.getId(), null) == null) goto L106;
            r53 = getMessageAlphaEnter().get(r52.getId(), r2).floatValue();
        L129:
            r33 = r33 + 1;
            r172 = 1.0f;
        L127:
            if ((r43 instanceof SharedPhotoVideoCell2) == false) goto L129;
            SharedPhotoVideoCell2 r45 = (SharedPhotoVideoCell2) getChildAt(r33);
            r45.setCrossfadeView(null, 0.0f, 0);
            r45.setTranslationX(0.0f);
            r45.setTranslationY(0.0f);
            r45.setImageScale(1.0f, !isChangeColumnsAnimation());
            goto L129
        L130:
            float r103 = 1.25f;
            float r143 = 255.0f;
            if (isChangeColumnsAnimation() == true) goto L133;
        L149:
            float r18 = r103;
            super.dispatchDraw(r30);
            if (isChangeColumnsAnimation() == false) goto L222;
            float r104 = ((getColumnsCount() / getAnimateToColumnsCount()) * getChangeColumnsProgress()) + (1.0f - getChangeColumnsProgress());
            float r153 = ((((getMeasuredWidth() / getAnimateToColumnsCount()) - AndroidUtilities.dpf2(2.0f)) / ((getMeasuredWidth() / getColumnsCount()) - AndroidUtilities.dpf2(2.0f))) * getChangeColumnsProgress()) + (1.0f - getChangeColumnsProgress());
            float r22 = (float) (((Math.ceil(getMeasuredWidth() / getColumnsCount()) - AndroidUtilities.dpf2(2.0f)) * r153) + AndroidUtilities.dpf2(2.0f));
            if (isStories() == false) goto L154;
            r22 = r22 * r18;
        L154:
            float r124 = r22;
            float r182 = getMeasuredWidth() / getColumnsCount();
            float r19 = getMeasuredWidth() / getAnimateToColumnsCount();
            int r23 = 0;
        L156:
            if (r23 >= this.drawingViews2.size()) goto L162;
            SharedPhotoVideoCell2 r34 = this.drawingViews2.get(r23);
            int r46 = ((GridLayoutManager.LayoutParams) r34.getLayoutParams()).getViewAdapterPosition() % getColumnsCount();
            int r57 = ((((GridLayoutManager.LayoutParams) r34.getLayoutParams()).getViewAdapterPosition() - r13) / getColumnsCount()) + r92;
            int r64 = r46 + r84;
            r30.save();
            r34.setImageScale(r153, !isChangeColumnsAnimation());
            r30.translate(((r46 * r182) * (1.0f - getChangeColumnsProgress())) + ((r64 * r19) * getChangeColumnsProgress()), (r57 * r124) + r11);
            if (r64 >= getAnimateToColumnsCount()) goto L160;
            int r21 = r23;
            float r222 = r143;
            r30.saveLayerAlpha(0.0f, 0.0f, r34.getMeasuredWidth() * r104, r34.getMeasuredHeight() * r104, (int) ((1.0f - getChangeColumnsProgress()) * r143), 31);
            r34.draw(r30);
            r30.restore();
        L161:
            r30.restore();
            r23 = r21 + 1;
            r143 = r222;
            goto L156
        L160:
            r21 = r23;
            r222 = r143;
            r34.draw(r30);
            goto L161
        L162:
            float r223 = r143;
            if (this.drawingViews3.isEmpty() == true) goto L223;
            r30.saveLayerAlpha(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight(), (int) (getChangeColumnsProgress() * r223), 31);
            int r105 = 0;
        L166:
            if (r105 >= this.drawingViews3.size()) goto L168;
            this.drawingViews3.get(r105).drawCrossafadeImage(r30);
            r105 = r105 + 1;
            goto L166
        L168:
            r30.restore();
            return;
        L223:
            return;
        L222:
            return;
        L133:
            if (this.drawingViews.isEmpty() == true) goto L149;
            float r212 = ((getAnimateToColumnsCount() / getColumnsCount()) * (1.0f - getChangeColumnsProgress())) + getChangeColumnsProgress();
            float r24 = ((((getMeasuredWidth() / getColumnsCount()) - AndroidUtilities.dpf2(2.0f)) / ((getMeasuredWidth() / getAnimateToColumnsCount()) - AndroidUtilities.dpf2(2.0f))) * (1.0f - getChangeColumnsProgress())) + getChangeColumnsProgress();
            float r224 = getMeasuredWidth() / getColumnsCount();
            float r232 = getMeasuredWidth() / getAnimateToColumnsCount();
            float r35 = (float) (((Math.ceil(getMeasuredWidth() / getAnimateToColumnsCount()) - AndroidUtilities.dpf2(2.0f)) * r24) + AndroidUtilities.dpf2(2.0f));
            if (isStories() == false) goto L137;
            r35 = r35 * 1.25f;
        L137:
            float r242 = r35;
            int r36 = 0;
        L139:
            if (r36 >= this.drawingViews.size()) goto L149;
            SharedPhotoVideoCell2 r47 = this.drawingViews.get(r36);
            if (this.excludeDrawViews.contains(r47) == false) goto L143;
            float r26 = r24;
            int r27 = r36;
            float r183 = r103;
        L148:
            r36 = r27 + 1;
            r103 = r183;
            r24 = r26;
            goto L139
        L143:
            r47.setCrossfadeView(null, 0.0f, 0);
            int r65 = ((GridLayoutManager.LayoutParams) r47.getLayoutParams()).getViewAdapterPosition() % getAnimateToColumnsCount();
            int r58 = r65 - r84;
            int r77 = ((((GridLayoutManager.LayoutParams) r47.getLayoutParams()).getViewAdapterPosition() - r152) / getAnimateToColumnsCount()) - r92;
            r30.save();
            r183 = r103;
            r30.translate(((r58 * r224) * (1.0f - getChangeColumnsProgress())) + ((r65 * r232) * getChangeColumnsProgress()), (r77 * r242) + r11);
            r47.setImageScale(r24, !isChangeColumnsAnimation());
            if (r58 >= getColumnsCount()) goto L146;
            r26 = r24;
            r27 = r36;
            r30.saveLayerAlpha(0.0f, 0.0f, r47.getMeasuredWidth() * r212, r47.getMeasuredHeight() * r212, (int) (getChangeColumnsProgress() * 255.0f), 31);
            r47.draw(r30);
            r30.restore();
        L147:
            r30.restore();
            goto L148
        L146:
            r26 = r24;
            r27 = r36;
            r47.draw(r30);
            goto L147
        L77:
            if (r75.getTag() == null) goto L87;
            float r93 = r3.getScrollProgress(this);
            float r112 = r4.getScrollProgress(getSupportingListView());
            if (r3.fastScrollIsVisible(this) == false) goto L81;
            float r37 = r172;
        L83:
            if (r4.fastScrollIsVisible(getSupportingListView()) == false) goto L85;
            float r48 = r172;
        L86:
            r75.setProgress((r93 * (r172 - getChangeColumnsProgress())) + (r112 * getChangeColumnsProgress()));
            r75.setVisibilityAlpha((r37 * (r172 - getChangeColumnsProgress())) + (r48 * getChangeColumnsProgress()));
            goto L87
        L85:
            r48 = 0.0f;
            goto L86
        L81:
            r37 = 0.0f;
        L53:
            if (((r14 / getAnimateToColumnsCount()) - r83) < r73) goto L57;
            if (getAnimateToColumnsCount() >= getColumnsCount()) goto L57;
        L44:
            if (((r152 / getAnimateToColumnsCount()) + r83) >= 0) goto L49;
            if (getAnimateToColumnsCount() <= getColumnsCount()) goto L49;
        L58:
            r62 = 0;
            r83 = 0;
            goto L59
        L88:
            r172 = 1.0f;
            r16 = true;
            r84 = 0;
            r92 = 0;
            r13 = 0;
            r152 = 0;
            goto L89
        }

        @Override // org.telegram.ui.Components.BlurredRecyclerView, org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean drawChild(Canvas r3, View r4, long r5) {
            RecyclerListView.FastScrollAdapter r0 = getMovingAdapter();
            if (isThisListView() == false) goto L13;
            if (getAdapter() != r0) goto L13;
            if (isChangeColumnsAnimation() == false) goto L13;
            if ((r4 instanceof SharedPhotoVideoCell2) == false) goto L13;
            return true;
        L13:
            return super.drawChild(r3, r4, r5);
        }

        public int getAnimateToColumnsCount() {
            return 3;
        }

        public float getChangeColumnsProgress() {
            return 0.0f;
        }

        public int getColumnsCount() {
            return 3;
        }

        public SparseArray<Float> getMessageAlphaEnter() {
            return null;
        }

        public RecyclerListView.FastScrollAdapter getMovingAdapter() {
            return null;
        }

        public int getPinchCenterPosition() {
            return 0;
        }

        public RecyclerListView.FastScrollAdapter getSupportingAdapter() {
            return null;
        }

        public InternalListView getSupportingListView() {
            return null;
        }

        public abstract boolean isChangeColumnsAnimation();

        public abstract boolean isStories();

        public boolean isThisListView() {
            return true;
        }
    }

    /* loaded from: classes3.dex */
    public static class SharedMediaPreloader implements NotificationCenter.NotificationCenterDelegate {
        private boolean checkedHasSavedMessages;
        private ArrayList<SharedMediaPreloaderDelegate> delegates;
        private long dialogId;
        public boolean hasPreviews;
        public boolean hasSavedMessages;
        private int[] lastLoadMediaCount;
        private int[] lastLoadMergeMediaCount;
        private int[] lastMediaCount;
        private int[] mediaCount;
        private int[] mediaMergeCount;
        private boolean mediaWasLoaded;
        private long mergeDialogId;
        private final NotificationCenter.ObserversGroup observersGroup;
        private BaseFragment parentFragment;
        private SharedMediaData[] sharedMediaData;
        private long topicId;

        /* renamed from: org.telegram.ui.Components.SharedMediaLayout$SharedMediaPreloader$1, reason: invalid class name */
        /* loaded from: classes7.dex */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ SharedMediaPreloader this$0;
            final /* synthetic */ int val$account;
            final /* synthetic */ ArrayList val$allMessages;
            final /* synthetic */ String val$fileName;

            public AnonymousClass1(SharedMediaPreloader r1, ArrayList r2, String r3, int r4) {
                this.this$0 = r1;
                this.val$allMessages = r2;
                this.val$fileName = r3;
                this.val$account = r4;
            }

            @Override // java.lang.Runnable
            public void run() {
                int r0 = 0;
            L4:
                if (r0 >= this.val$allMessages.size()) goto L10;
                if (this.val$fileName.equals(((MessageObject) this.val$allMessages.get(r0)).getFileName()) == true) goto L8;
                this.val$allMessages.remove(r0);
                r0 = r0 - 1;
            L8:
                r0 = r0 + 1;
                goto L4
            L10:
                if (this.val$allMessages.size() <= 0) goto L16;
                FileLoader.getInstance(this.val$account).checkMediaExistance(this.val$allMessages);
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$ca2kxmK_vLdAuuNueHhG_TccQT8(SharedMediaPreloader r0, Boolean r1) {
            r0.lambda$new$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$v_dfmFpkBTIm0Mk4WbLSL9J29sA(SharedMediaPreloader r0, Boolean r1) {
            r0.lambda$new$0(r1);
        }

        /* renamed from: -$$Nest$fgettopicId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m12995$$Nest$fgettopicId(SharedMediaPreloader r2) {
            return r2.topicId;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public SharedMediaPreloader(BaseFragment r5) {
            this.mediaCount = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            this.mediaMergeCount = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            this.lastMediaCount = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            this.lastLoadMediaCount = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            this.lastLoadMergeMediaCount = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            this.delegates = new ArrayList();
            this.parentFragment = r5;
            if ((r5 instanceof ChatActivityInterface) == false) goto L8;
            ChatActivityInterface r0 = (ChatActivityInterface) r5;
            this.dialogId = r0.getDialogId();
            this.mergeDialogId = r0.getMergeDialogId();
            this.topicId = r0.getTopicId();
            if (this.dialogId == r5.getUserConfig().getClientUserId()) goto L25;
            r5.getMessagesController().getSavedMessagesController().hasSavedMessages(this.dialogId, new SharedMediaLayout$SharedMediaPreloader$$ExternalSyntheticLambda0(this));
        L25:
            if (this.mergeDialogId == 0) goto L27;
        L33:
            this.sharedMediaData = new SharedMediaData[9];
            int r02 = 0;
        L34:
            SharedMediaData[] r1 = this.sharedMediaData;
            if (r02 >= r1.length) goto L41;
            r1[r02] = new SharedMediaData();
            SharedMediaData r12 = this.sharedMediaData[r02];
            if (DialogObject.isEncryptedDialog(this.dialogId) == false) goto L39;
            int r2 = Integer.MIN_VALUE;
        L40:
            r12.setMaxId(0, r2);
            this.sharedMediaData[r02].setMaxId(1, Integer.MAX_VALUE);
            r02 = r02 + 1;
            goto L34
        L39:
            r2 = Integer.MAX_VALUE;
            goto L40
        L41:
            loadMediaCounts();
            BaseFragment r52 = this.parentFragment;
            if (r52 != null) goto L45;
            this.observersGroup = null;
            return;
        L45:
            this.observersGroup = r52.getNotificationCenter().createObserversGroup(this).add(NotificationCenter.mediaCountsDidLoad).add(NotificationCenter.mediaCountDidLoad).add(NotificationCenter.didReceiveNewMessages).add(NotificationCenter.messageReceivedByServer).add(NotificationCenter.mediaDidLoad).add(NotificationCenter.messagesDeleted).add(NotificationCenter.replaceMessagesObjects).add(NotificationCenter.chatInfoDidLoad).add(NotificationCenter.fileLoaded).add(NotificationCenter.storiesListUpdated).add(NotificationCenter.savedMessagesDialogsUpdate);
            return;
        L27:
            if (DialogObject.isChatDialog(this.dialogId) == false) goto L33;
            TLRPC.ChatFull r53 = r5.getMessagesController().getChatFull(-this.dialogId);
            if (r53 == null) goto L33;
            long r03 = r53.migrated_from_chat_id;
            if (r03 == 0) goto L33;
            this.mergeDialogId = -r03;
            goto L33
        L8:
            if ((r5 instanceof ProfileActivity) == false) goto L19;
            ProfileActivity r04 = (ProfileActivity) r5;
            if (r04.saved == false) goto L12;
            this.dialogId = r04.getUserConfig().getClientUserId();
            this.topicId = r04.getDialogId();
            goto L25
        L12:
            this.dialogId = r04.getDialogId();
            this.topicId = r04.getTopicId();
            TLRPC.ChatFull r05 = r04.getChatInfo();
            if (r05 == null) goto L16;
            setChatInfo(r05);
        L16:
            if (this.dialogId == r5.getUserConfig().getClientUserId()) goto L25;
            r5.getMessagesController().getSavedMessagesController().hasSavedMessages(this.dialogId, new SharedMediaLayout$SharedMediaPreloader$$ExternalSyntheticLambda1(this));
            goto L25
        L19:
            if ((r5 instanceof MediaActivity) == false) goto L22;
            this.dialogId = ((MediaActivity) r5).getDialogId();
            goto L25
        L22:
            if ((r5 instanceof DialogsActivity) == false) goto L25;
            this.dialogId = r5.getUserConfig().getClientUserId();
            goto L25
        }

        private /* synthetic */ void lambda$new$0(Boolean r3) {
            boolean r32 = r3.booleanValue();
            this.hasSavedMessages = r32;
            this.checkedHasSavedMessages = true;
            if (r32 == false) goto L7;
            int r33 = this.delegates.size();
            int r0 = 0;
        L5:
            if (r0 >= r33) goto L9;
            this.delegates.get(r0).mediaCountUpdated();
            r0 = r0 + 1;
            goto L5
        L9:
            return;
        }

        private /* synthetic */ void lambda$new$1(Boolean r3) {
            boolean r32 = r3.booleanValue();
            this.hasSavedMessages = r32;
            this.checkedHasSavedMessages = true;
            if (r32 == false) goto L7;
            int r33 = this.delegates.size();
            int r0 = 0;
        L5:
            if (r0 >= r33) goto L9;
            this.delegates.get(r0).mediaCountUpdated();
            r0 = r0 + 1;
            goto L5
        L9:
            return;
        }

        private void loadMediaCounts() {
            BaseFragment r0 = this.parentFragment;
            if (r0 == null) goto L10;
            r0.getMediaDataController().getMediaCounts(this.dialogId, this.topicId, this.parentFragment.getClassGuid());
            if (this.mergeDialogId == 0) goto L9;
            this.parentFragment.getMediaDataController().getMediaCounts(this.mergeDialogId, this.topicId, this.parentFragment.getClassGuid());
            return;
        L9:
            return;
        }

        private void setChatInfo(TLRPC.ChatFull r10) {
            BaseFragment r0 = this.parentFragment;
            if (r0 == null) goto L15;
            if (r10 == null) goto L12;
            long r1 = r10.migrated_from_chat_id;
            if (r1 != 0) goto L9;
            return;
        L9:
            if (this.mergeDialogId != 0) goto L14;
            this.mergeDialogId = -r1;
            r0.getMediaDataController().getMediaCounts(this.mergeDialogId, this.topicId, this.parentFragment.getClassGuid());
            return;
        L14:
            return;
        L12:
            return;
        }

        public void addDelegate(SharedMediaPreloaderDelegate r1) {
            this.delegates.add(r1);
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        public void didReceivedNotification(int r25, int r26, Object... r27) {
            int r5 = -1;
            int r7 = 0;
            boolean r8 = true;
            int r82 = 1;
            if (r25 != NotificationCenter.mediaCountsDidLoad) goto L65;
            long r10 = ((Long) r27[0]).longValue();
            long r16 = ((Long) r27[1]).longValue();
            if (this.topicId != r16) goto L331;
            long r1 = this.dialogId;
            if (r10 != r1) goto L9;
        L10:
            int[] r9 = (int[]) r27[2];
            if (r10 != r1) goto L13;
            this.mediaCount = r9;
        L14:
            int r12 = 0;
        L16:
            if (r12 >= r9.length) goto L61;
            int r2 = this.mediaCount[r12];
            if (r2 < 0) goto L22;
            int r122 = this.mediaMergeCount[r12];
            if (r122 < 0) goto L22;
            this.lastMediaCount[r12] = r2 + r122;
        L26:
            int r123 = 20;
            if (r10 == this.dialogId) goto L29;
        L43:
            int[] r22 = r9;
            if (r10 != this.mergeDialogId) goto L60;
            if (this.lastMediaCount[r12] == 0) goto L60;
            if (this.lastLoadMergeMediaCount[r12] == this.mediaMergeCount[r12]) goto L60;
            if (r12 != 0) goto L55;
            int r92 = this.sharedMediaData[0].filterType;
            if (r92 != 1) goto L53;
            int r15 = 6;
        L56:
            MediaDataController r93 = this.parentFragment.getMediaDataController();
            if (this.lastLoadMergeMediaCount[r12] != (-1)) goto L59;
            r123 = 30;
        L59:
            r93.loadMedia(r10, r123, 0, 0, r15, r16, 1, this.parentFragment.getClassGuid(), this.sharedMediaData[r12].requestIndex, null, null);
            this.lastLoadMergeMediaCount[r12] = this.mediaMergeCount[r12];
            goto L60
        L53:
            if (r92 != 2) goto L55;
            r15 = 7;
        L55:
            r15 = r12;
        L60:
            r12 = r12 + 1;
            r9 = r22;
            goto L16
        L29:
            if (this.lastMediaCount[r12] == 0) goto L43;
            if (this.lastLoadMediaCount[r12] == this.mediaCount[r12]) goto L43;
            if (r12 != 0) goto L38;
            int r23 = this.sharedMediaData[0].filterType;
            if (r23 != 1) goto L36;
            int r152 = 6;
        L39:
            MediaDataController r24 = this.parentFragment.getMediaDataController();
            if (this.lastLoadMediaCount[r12] != (-1)) goto L42;
            r123 = 30;
        L42:
            r22 = r9;
            r24.loadMedia(r10, r123, 0, 0, r152, r16, 1, this.parentFragment.getClassGuid(), this.sharedMediaData[r12].requestIndex, null, null);
            this.lastLoadMediaCount[r12] = this.mediaCount[r12];
            goto L60
        L36:
            if (r23 != 2) goto L38;
            r152 = 7;
        L38:
            r152 = r12;
        L22:
            int[] r124 = this.lastMediaCount;
            if (r2 < 0) goto L25;
            r124[r12] = r2;
            goto L26
        L25:
            r124[r12] = Math.max(this.mediaMergeCount[r12], 0);
            goto L26
        L61:
            this.mediaWasLoaded = true;
            int r13 = this.delegates.size();
        L62:
            if (r7 >= r13) goto L393;
            this.delegates.get(r7).mediaCountUpdated();
            r7 = r7 + 1;
            goto L62
        L393:
            return;
        L13:
            this.mediaMergeCount = r9;
            goto L14
        L9:
            if (r10 == this.mergeDialogId) goto L10;
            return;
        L331:
            return;
        L65:
            if (r25 != NotificationCenter.mediaCountDidLoad) goto L89;
            long r14 = ((Long) r27[0]).longValue();
            long r3 = ((Long) r27[1]).longValue();
            if (r14 == this.dialogId) goto L71;
            if (r14 == this.mergeDialogId) goto L71;
            return;
        L71:
            if (this.topicId != r3) goto L376;
            int r32 = ((Integer) r27[4]).intValue();
            int r4 = ((Integer) r27[2]).intValue();
            if (r14 != this.dialogId) goto L75;
            this.mediaCount[r32] = r4;
        L76:
            int r17 = this.mediaCount[r32];
            if (r17 < 0) goto L81;
            int r28 = this.mediaMergeCount[r32];
            if (r28 < 0) goto L81;
            this.lastMediaCount[r32] = r17 + r28;
        L85:
            int r18 = this.delegates.size();
        L86:
            if (r7 >= r18) goto L394;
            this.delegates.get(r7).mediaCountUpdated();
            r7 = r7 + 1;
            goto L86
        L394:
            return;
        L81:
            int[] r29 = this.lastMediaCount;
            if (r17 < 0) goto L84;
            r29[r32] = r17;
            goto L85
        L84:
            r29[r32] = Math.max(this.mediaMergeCount[r32], 0);
            goto L85
        L75:
            this.mediaMergeCount[r32] = r4;
            goto L76
        L376:
            return;
        L89:
            if (r25 != NotificationCenter.didReceiveNewMessages) goto L151;
            if (((Boolean) r27[2]).booleanValue() == true) goto L386;
            long r19 = ((Long) r27[0]).longValue();
            long r33 = this.dialogId;
            if (r19 == r33) goto L98;
            if (r19 == this.mergeDialogId) goto L98;
            return;
        L98:
            if (r19 != r33) goto L100;
            int r34 = 0;
        L101:
            boolean r110 = DialogObject.isEncryptedDialog(r19);
            ArrayList r210 = (ArrayList) r27[1];
            BaseFragment r42 = this.parentFragment;
            if (r42 == null) goto L104;
            int r43 = r42.getCurrentAccount();
        L105:
            int r94 = 0;
        L107:
            if (r94 >= r210.size()) goto L148;
            MessageObject r125 = (MessageObject) r210.get(r94);
            if (r125.isEphemeral() == true) goto L147;
            long r132 = this.topicId;
            if (r132 == 0) goto L117;
            if (r132 != MessageObject.getTopicId(r43, r125.messageOwner, true)) goto L147;
        L117:
            if (MessageObject.getMedia(r125.messageOwner) == null) goto L147;
            if (r125.needDrawBluredPreview() == true) goto L147;
            int r133 = MediaDataController.getMediaType(r125.messageOwner);
            if (r133 == (-1)) goto L147;
            if (r133 == 0) goto L126;
        L130:
            if (r133 == 0) goto L132;
        L136:
            SharedMediaData r142 = this.sharedMediaData[r133];
            if (r142.startReached == false) goto L140;
            r142.addMessage(r125, r34, true, r110);
        L140:
            if (this.topicId != 0) goto L142;
            int[] r126 = this.sharedMediaData[r133].totalCount;
            r126[r34] = r126[r34] + 1;
        L142:
            if (r34 != 0) goto L147;
            int r127 = 0;
        L145:
            if (r127 >= this.sharedMediaData[r133].fastScrollPeriods.size()) goto L147;
            this.sharedMediaData[r133].fastScrollPeriods.get(r127).startOffset++;
            r127 = r127 + 1;
            goto L145
        L132:
            if (this.sharedMediaData[0].filterType != 1) goto L136;
            if (r125.isVideo() == false) goto L136;
        L126:
            if (this.sharedMediaData[0].filterType != 2) goto L130;
            if (r125.isVideo() == true) goto L130;
        L147:
            r94 = r94 + 1;
            goto L107
        L148:
            loadMediaCounts();
            return;
        L104:
            r43 = -1;
            goto L105
        L100:
            r34 = 1;
            goto L101
        L386:
            return;
        L151:
            if (r25 != NotificationCenter.messageReceivedByServer) goto L166;
            if (((Boolean) r27[6]).booleanValue() == true) goto L387;
            Integer r111 = (Integer) r27[0];
            Integer r211 = (Integer) r27[1];
            Long r35 = (Long) r27[3];
            if (r35.longValue() == this.dialogId) goto L160;
            if (r35.longValue() == this.mergeDialogId) goto L160;
            return;
        L160:
            if (r35.longValue() != this.dialogId) goto L162;
            r82 = 0;
        L162:
            SharedMediaData[] r36 = this.sharedMediaData;
            if (r7 >= r36.length) goto L395;
            r36[r7].replaceMid(r82, r111.intValue(), r211.intValue());
            r7 = r7 + 1;
            goto L162
        L395:
            return;
        L387:
            return;
        L166:
            if (r25 != NotificationCenter.mediaDidLoad) goto L190;
            long r112 = ((Long) r27[0]).longValue();
            if (((Integer) r27[3]).intValue() != this.parentFragment.getClassGuid()) goto L379;
            int r52 = ((Integer) r27[4]).intValue();
            ArrayList r102 = (ArrayList) r27[2];
            boolean r11 = DialogObject.isEncryptedDialog(r112);
            if (r112 != this.dialogId) goto L172;
            int r113 = 0;
        L173:
            if (r52 == 0) goto L177;
            if (r52 == 6) goto L177;
            if (r52 == 7) goto L177;
        L180:
            if (r52 == 0) goto L185;
            if (r52 == 1) goto L185;
            if (r52 == 2) goto L185;
            if (r52 == 4) goto L185;
            this.sharedMediaData[r52].setTotalCount(r113, ((Integer) r27[1]).intValue());
        L185:
            this.sharedMediaData[r52].setEndReached(r113, ((Boolean) r27[5]).booleanValue());
            int r212 = 0;
        L187:
            if (r212 >= r102.size()) goto L396;
            this.sharedMediaData[r52].addMessage((MessageObject) r102.get(r212), r113, false, r11);
            r212 = r212 + 1;
            goto L187
        L396:
            return;
        L177:
            if (r52 != this.sharedMediaData[0].filterType) goto L388;
            r52 = 0;
            goto L180
        L388:
            return;
        L172:
            r113 = 1;
            goto L173
        L379:
            return;
        L190:
            if (r25 != NotificationCenter.messagesDeleted) goto L255;
            if (((Boolean) r27[2]).booleanValue() == true) goto L389;
            long r114 = ((Long) r27[1]).longValue();
            if (DialogObject.isChatDialog(this.dialogId) == false) goto L197;
            TLRPC.Chat r37 = this.parentFragment.getMessagesController().getChat(Long.valueOf(-this.dialogId));
        L199:
            if (ChatObject.isChannel(r37) == false) goto L208;
            if (r114 != 0) goto L205;
            if (this.mergeDialogId == 0) goto L205;
        L210:
            ArrayList r115 = (ArrayList) r27[0];
            BaseFragment r213 = this.parentFragment;
            if (r213 == null) goto L213;
            r5 = r213.getCurrentAccount();
        L213:
            int r214 = r115.size();
            int r38 = 0;
            boolean r44 = false;
        L214:
            if (r38 >= r214) goto L235;
            int r6 = 0;
            boolean r45 = r44;
        L216:
            SharedMediaData[] r95 = this.sharedMediaData;
            if (r6 >= r95.length) goto L234;
            MessageObject r96 = r95[r6].deleteMessage(((Integer) r115.get(r38)).intValue(), 0);
            if (r96 == null) goto L233;
            if (r96.getDialogId() == this.dialogId) goto L223;
        L229:
            int[] r46 = this.mediaMergeCount;
            int r97 = r46[r6];
            if (r97 <= 0) goto L232;
            r46[r6] = r97 - 1;
        L232:
            r45 = true;
            goto L233
        L223:
            if (this.topicId != 0) goto L225;
        L226:
            int[] r47 = this.mediaCount;
            int r98 = r47[r6];
            if (r98 <= 0) goto L232;
            r47[r6] = r98 - 1;
            goto L232
        L225:
            if (MessageObject.getTopicId(r5, r96.messageOwner, true) != this.topicId) goto L229;
        L233:
            r6 = r6 + 1;
            r45 = r45;
            goto L216
        L234:
            r38 = r38 + 1;
            r44 = r45;
            goto L214
        L235:
            if (r44 == false) goto L252;
            int r116 = 0;
        L237:
            int[] r215 = this.mediaCount;
            if (r116 >= r215.length) goto L249;
            int r216 = r215[r116];
            if (r216 < 0) goto L244;
            int r39 = this.mediaMergeCount[r116];
            if (r39 < 0) goto L244;
            this.lastMediaCount[r116] = r216 + r39;
        L248:
            r116 = r116 + 1;
        L244:
            int[] r310 = this.lastMediaCount;
            if (r216 < 0) goto L247;
            r310[r116] = r216;
            goto L248
        L247:
            r310[r116] = Math.max(this.mediaMergeCount[r116], 0);
            goto L248
        L249:
            int r117 = this.delegates.size();
        L250:
            if (r7 >= r117) goto L252;
            this.delegates.get(r7).mediaCountUpdated();
            r7 = r7 + 1;
        L252:
            loadMediaCounts();
            return;
        L205:
            if (r114 == r37.id) goto L210;
            return;
        L208:
            if (r114 == 0) goto L210;
            return;
        L197:
            r37 = null;
            goto L199
        L389:
            return;
        L255:
            if (r25 != NotificationCenter.replaceMessagesObjects) goto L299;
            long r118 = ((Long) r27[0]).longValue();
            long r311 = this.dialogId;
            if (r118 == r311) goto L262;
            if (r118 == this.mergeDialogId) goto L262;
            return;
        L262:
            if (r118 != r311) goto L264;
            int r119 = 0;
        L265:
            ArrayList r217 = (ArrayList) r27[1];
            BaseFragment r312 = this.parentFragment;
            if (r312 == null) goto L268;
            int r313 = r312.getCurrentAccount();
        L269:
            int r48 = r217.size();
            int r62 = 0;
        L270:
            if (r62 >= r48) goto L397;
            MessageObject r99 = (MessageObject) r217.get(r62);
            int r128 = r99.getId();
            long r134 = MessageObject.getTopicId(r313, r99.messageOwner, r8);
            int r153 = MediaDataController.getMediaType(r99.messageOwner);
            int r172 = r7;
            long r72 = this.topicId;
            if (r72 != 0) goto L274;
        L276:
            int r73 = r172;
        L277:
            SharedMediaData[] r83 = this.sharedMediaData;
            if (r73 >= r83.length) goto L297;
            MessageObject r84 = r83[r73].messagesDict[r119].get(r128);
            if (r84 != null) goto L281;
            r73 = r73 + 1;
            goto L277
        L281:
            int r135 = MediaDataController.getMediaType(r99.messageOwner);
            if (r153 == (-1)) goto L288;
            if (r135 != r153) goto L288;
            int r85 = this.sharedMediaData[r73].messages.indexOf(r84);
            if (r85 < 0) goto L297;
            this.sharedMediaData[r73].messagesDict[r119].put(r128, r99);
            this.sharedMediaData[r73].messages.set(r85, r99);
        L288:
            this.sharedMediaData[r73].deleteMessage(r128, r119);
            if (r119 != 0) goto L293;
            int[] r86 = this.mediaCount;
            int r910 = r86[r73];
            if (r910 <= 0) goto L297;
            r86[r73] = r910 - 1;
            goto L297
        L293:
            int[] r87 = this.mediaMergeCount;
            int r911 = r87[r73];
            if (r911 <= 0) goto L297;
            r87[r73] = r911 - 1;
        L297:
            r62 = r62 + 1;
            r7 = r172;
            r8 = true;
            goto L270
        L274:
            if (r134 == r72) goto L276;
        L397:
            return;
        L268:
            r313 = -1;
            goto L269
        L264:
            r119 = 1;
            goto L265
        L299:
            if (r25 != NotificationCenter.chatInfoDidLoad) goto L307;
            TLRPC.ChatFull r120 = (TLRPC.ChatFull) r27[0];
            long r218 = this.dialogId;
            if (r218 < 0) goto L303;
            return;
        L303:
            if (r120.id != (-r218)) goto L381;
            setChatInfo(r120);
            return;
        L381:
            return;
        L307:
            if (r25 != NotificationCenter.fileLoaded) goto L317;
            ArrayList r121 = new ArrayList();
            int r219 = 0;
        L309:
            SharedMediaData[] r314 = this.sharedMediaData;
            if (r219 >= r314.length) goto L312;
            r121.addAll(r314[r219].messages);
            r219 = r219 + 1;
            goto L309
        L312:
            String r220 = (String) r27[0];
            if (r220 == null) goto L382;
            Utilities.globalQueue.postRunnable(new AnonymousClass1(this, r121, r220, r26));
            return;
        L382:
            return;
        L317:
            if (r25 != NotificationCenter.savedMessagesDialogsUpdate) goto L383;
            BaseFragment r129 = this.parentFragment;
            if (r129 != null) goto L321;
        L323:
            boolean r88 = false;
        L325:
            if (this.checkedHasSavedMessages == true) goto L327;
            return;
        L327:
            if (this.hasSavedMessages == r88) goto L385;
            this.hasSavedMessages = r88;
            int r130 = this.delegates.size();
            int r74 = 0;
        L329:
            if (r74 >= r130) goto L398;
            this.delegates.get(r74).mediaCountUpdated();
            r74 = r74 + 1;
            goto L329
        L398:
            return;
        L385:
            return;
        L321:
            if (r129.getMessagesController().getSavedMessagesController().containsDialog(this.dialogId) == false) goto L323;
            r88 = true;
            goto L325
        }

        public int[] getLastMediaCount() {
            return this.lastMediaCount;
        }

        public SharedMediaData[] getSharedMediaData() {
            return this.sharedMediaData;
        }

        public long getTopicId() {
            return this.topicId;
        }

        public boolean hasSharedMedia() {
            int[] r0 = getLastMediaCount();
            if (r0 != null) goto L5;
            return false;
        L5:
            int r2 = 0;
        L7:
            if (r2 >= r0.length) goto L13;
            if (r0[r2] > 0) goto L10;
            r2 = r2 + 1;
            goto L7
        L10:
            return true;
        L13:
            if (this.hasSavedMessages == false) goto L15;
            return true;
        L15:
            BaseFragment r02 = this.parentFragment;
            if (r02 != null) goto L18;
        L24:
            return false;
        L18:
            if (this.dialogId != r02.getUserConfig().getClientUserId()) goto L24;
            if (this.topicId != 0) goto L24;
            if (this.parentFragment.getMessagesController().getSavedMessagesController().hasDialogs() == false) goto L24;
            return true;
        }

        public boolean isMediaWasLoaded() {
            return this.mediaWasLoaded;
        }

        public void onDestroy(BaseFragment r2) {
            onDestroy(r2, false);
        }

        public void onDestroy(BaseFragment r2, boolean r3) {
            if (r2 == this.parentFragment) goto L6;
            if (r3 == true) goto L6;
            return;
        L6:
            this.delegates.clear();
            NotificationCenter.ObserversGroup r1 = this.observersGroup;
            if (r1 == null) goto L10;
            r1.removeAllObservers();
            return;
        }

        public void removeDelegate(SharedMediaPreloaderDelegate r1) {
            this.delegates.remove(r1);
        }
    }

    public interface SharedMediaPreloaderDelegate {
        void mediaCountUpdated();
    }

    public class SharedPhotoVideoAdapter extends RecyclerListView.FastScrollAdapter {
        protected boolean inFastScrollMode;
        protected Context mContext;
        SharedPhotoVideoCell2.SharedResources sharedResources;
        final /* synthetic */ SharedMediaLayout this$0;

        public SharedPhotoVideoAdapter(SharedMediaLayout r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public boolean fastScrollIsVisible(RecyclerListView r5) {
            if (this.this$0.isSearchingStories() == false) goto L6;
            return false;
        L6:
            if (this != SharedMediaLayout.m12829$$Nest$fgetphotoVideoAdapter(this.this$0)) goto L8;
        L11:
            int r0 = SharedMediaLayout.m12824$$Nest$fgetmediaColumnsCount(this.this$0)[0];
        L12:
            int r4 = (int) Math.ceil(getTotalItemsCount() / r0);
            if (r5.getChildCount() != 0) goto L16;
            return false;
        L16:
            if ((r4 * r5.getChildAt(0).getMeasuredHeight()) <= r5.getMeasuredHeight()) goto L19;
            return true;
        L19:
            return false;
        L8:
            if (SharedMediaLayout.m12916$$Nest$mstoryAlbums_getTabTypeByStoriesAdapter(this.this$0, this) != (-1)) goto L11;
            r0 = SharedMediaLayout.m12792$$Nest$fgetanimateToColumnsCount(this.this$0);
            goto L12
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            boolean r0 = DialogObject.isEncryptedDialog(SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0));
            SharedMediaLayout r1 = this.this$0;
            if (r0 == true) goto L5;
            boolean r02 = SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(r1)[0].loadingAfterFastScroll;
            SharedMediaLayout r12 = this.this$0;
            if (r02 == false) goto L29;
            return SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(r12)[0].getTotalCount();
        L29:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(r12)[0].messages.size() != 0) goto L34;
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].loading == true) goto L34;
            return 1;
        L34:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].messages.size() == 0) goto L36;
        L42:
            int r03 = SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].getTotalCount();
            SharedMediaLayout r13 = this.this$0;
            if (r03 != 0) goto L57;
            int r04 = SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(r13)[0].getStartOffset() + SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].getMessages().size();
            if (r04 != 0) goto L47;
            return r04;
        L47:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].endReached[0] == false) goto L51;
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].endReached[1] == false) goto L51;
            return r04;
        L51:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].getEndLoadingStubs() == 0) goto L55;
            return r04 + SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].getEndLoadingStubs();
        L55:
            return r04 + 1;
        L57:
            return Math.max(SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(r13)[0].getTotalCount(), SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].getStartOffset() + SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].getMessages().size());
        L36:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].endReached[0] == false) goto L40;
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].endReached[1] == true) goto L42;
        L40:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].startReached == false) goto L42;
            return 0;
        L5:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(r1)[0].messages.size() != 0) goto L10;
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].loading == true) goto L10;
            return 1;
        L10:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].messages.size() == 0) goto L12;
        L16:
            int r05 = SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].getStartOffset() + SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].getMessages().size();
            if (r05 != 0) goto L19;
            return r05;
        L19:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].endReached[0] == false) goto L23;
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].endReached[1] == false) goto L23;
            return r05;
        L23:
            return r05 + 1;
        L12:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].endReached[0] == true) goto L14;
        L15:
            return 0;
        L14:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].endReached[1] == true) goto L16;
            goto L15
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (this.inFastScrollMode == false) goto L5;
        L12:
            SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].getStartOffset();
            SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].getMessages().size();
            SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].getStartOffset();
            return 0;
        L5:
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].getMessages().size() != 0) goto L12;
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].loading == true) goto L12;
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].startReached == false) goto L12;
            return 2;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public String getLetter(int r4) {
            int r1 = 0;
            if (SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].fastScrollPeriods != null) goto L5;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L5:
            ArrayList<Period> r3 = SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].fastScrollPeriods;
            if (r3.isEmpty() == false) goto L8;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L8:
            if (r1 >= r3.size()) goto L15;
            if (r4 <= r3.get(r1).startOffset) goto L12;
            r1 = r1 + 1;
            goto L8
        L12:
            return r3.get(r1).formatedDate;
        L15:
            return r3.get(r3.size() - 1).formatedDate;
        }

        public int getPositionForIndex(int r2) {
            return SharedMediaData.m12992$$Nest$fgetstartOffset(SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0]) + r2;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public void getPositionForScrollProgress(RecyclerListView r9, float r10, int[] r11) {
            int r1 = r9.getChildAt(0).getMeasuredHeight();
            if (SharedMediaLayout.m12917$$Nest$mstoryAlbums_getTabTypeByStoriesSupportingAdapter(this.this$0, this) == (-1)) goto L5;
        L11:
            int r2 = SharedMediaLayout.m12792$$Nest$fgetanimateToColumnsCount(this.this$0);
        L12:
            int r8 = (int) (Math.ceil(getTotalItemsCount() / r2) * r1);
            int r4 = r9.getMeasuredHeight() - r9.getPaddingTop();
            if (r1 != 0) goto L16;
            r11[1] = 0;
            r11[0] = 0;
            return;
        L16:
            float r102 = r10 * (r8 - r4);
            r11[0] = ((int) (r102 / r1)) * r2;
            r11[1] = ((int) r102) % r1;
            return;
        L5:
            if (this == SharedMediaLayout.m12795$$Nest$fgetanimationSupportingPhotoVideoAdapter(this.this$0)) goto L11;
            int r22 = SharedMediaLayout.m12916$$Nest$mstoryAlbums_getTabTypeByStoriesAdapter(this.this$0, this);
            SharedMediaLayout r5 = this.this$0;
            if (r22 == (-1)) goto L10;
            r2 = SharedMediaLayout.m12824$$Nest$fgetmediaColumnsCount(r5)[1];
            goto L12
        L10:
            r2 = SharedMediaLayout.m12824$$Nest$fgetmediaColumnsCount(r5)[0];
            goto L12
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public float getScrollProgress(RecyclerListView r6) {
            if (this != SharedMediaLayout.m12795$$Nest$fgetanimationSupportingPhotoVideoAdapter(this.this$0)) goto L5;
        L11:
            int r0 = SharedMediaLayout.m12792$$Nest$fgetanimateToColumnsCount(this.this$0);
        L12:
            int r5 = (int) Math.ceil(getTotalItemsCount() / r0);
            if (r6.getChildCount() != 0) goto L15;
            return 0.0f;
        L15:
            int r2 = r6.getChildAt(0).getMeasuredHeight();
            View r1 = r6.getChildAt(0);
            if (r6.getChildAdapterPosition(r1) >= 0) goto L19;
            return 0.0f;
        L19:
            return (((r4 / r0) * r2) - (r1.getTop() - r6.getPaddingTop())) / ((r5 * r2) - (r6.getMeasuredHeight() - r6.getPaddingTop()));
        L5:
            if (SharedMediaLayout.m12917$$Nest$mstoryAlbums_getTabTypeByStoriesSupportingAdapter(this.this$0, this) != (-1)) goto L11;
            int r02 = SharedMediaLayout.m12916$$Nest$mstoryAlbums_getTabTypeByStoriesAdapter(this.this$0, this);
            SharedMediaLayout r3 = this.this$0;
            if (r02 == (-1)) goto L10;
            r0 = SharedMediaLayout.m12824$$Nest$fgetmediaColumnsCount(r3)[1];
            goto L12
        L10:
            r0 = SharedMediaLayout.m12824$$Nest$fgetmediaColumnsCount(r3)[0];
            goto L12
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public int getTotalItemsCount() {
            return SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].getTotalCount();
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r11, int r12) {
            if (r11.getItemViewType() != 0) goto L36;
            boolean r1 = false;
            ArrayList<MessageObject> r0 = SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].getMessages();
            int r122 = r12 - SharedMediaLayout.m12846$$Nest$fgetsharedMediaData(this.this$0)[0].getStartOffset();
            View r112 = r11.itemView;
            if ((r112 instanceof SharedPhotoVideoCell2) == false) goto L37;
            SharedPhotoVideoCell2 r113 = (SharedPhotoVideoCell2) r112;
            int r2 = r113.getMessageId();
            SharedPhotoVideoAdapter r3 = SharedMediaLayout.m12829$$Nest$fgetphotoVideoAdapter(this.this$0);
            SharedMediaLayout r4 = this.this$0;
            if (this != r3) goto L10;
            int r32 = SharedMediaLayout.m12824$$Nest$fgetmediaColumnsCount(r4)[0];
        L14:
            if (r122 >= 0) goto L16;
        L34:
            r113.setMessageObject(null, r32);
            r113.setChecked(false, false);
            return;
        L16:
            if (r122 >= r0.size()) goto L34;
            MessageObject r123 = r0.get(r122);
            if (r123.getId() != r2) goto L20;
            boolean r02 = true;
        L21:
            SharedMediaLayout r22 = this.this$0;
            if (r22.isActionModeShowed == false) goto L31;
            SparseArray[] r23 = SharedMediaLayout.m12844$$Nest$fgetselectedFiles(r22);
            if (r123.getDialogId() != SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0)) goto L26;
            char r10 = 0;
        L28:
            if (r23[r10].indexOfKey(r123.getId()) < 0) goto L30;
            r1 = true;
        L30:
            r113.setChecked(r1, r02);
        L32:
            r113.setMessageObject(r123, r32);
            return;
        L26:
            r10 = 1;
            goto L28
        L31:
            r113.setChecked(false, r02);
            goto L32
        L20:
            r02 = false;
            goto L21
        L10:
            int r33 = SharedMediaLayout.m12916$$Nest$mstoryAlbums_getTabTypeByStoriesAdapter(r4, this);
            SharedMediaLayout r42 = this.this$0;
            if (r33 == (-1)) goto L13;
            r32 = SharedMediaLayout.m12824$$Nest$fgetmediaColumnsCount(r42)[1];
            goto L14
        L13:
            r32 = SharedMediaLayout.m12792$$Nest$fgetanimateToColumnsCount(r42);
            goto L14
        L37:
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r6, int r7) {
            if (r7 == 0) goto L8;
            if (r7 == 19) goto L8;
            View r5 = SharedMediaLayout.createEmptyStubView(this.mContext, 0, SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0), SharedMediaLayout.m12835$$Nest$fgetresourcesProvider(this.this$0));
            r5.setLayoutParams(new RecyclerView.LayoutParams(-1, -1));
            return new RecyclerListView.Holder(r5);
        L8:
            if (this.sharedResources != null) goto L10;
            this.sharedResources = new SharedPhotoVideoCell2.SharedResources(r6.getContext(), SharedMediaLayout.m12835$$Nest$fgetresourcesProvider(this.this$0));
        L10:
            SharedPhotoVideoCell2 r62 = new SharedPhotoVideoCell2(this.mContext, this.sharedResources, SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getCurrentAccount());
            if (r7 != 19) goto L13;
            r62.setCheck2();
        L13:
            r62.setGradientView(this.this$0.globalGradientView);
            if (SharedMediaLayout.m12916$$Nest$mstoryAlbums_getTabTypeByStoriesAdapter(this.this$0, this) == (-1)) goto L16;
            r62.isStory = true;
        L16:
            r62.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r62);
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public void onFastScrollSingleTap() {
            this.this$0.showMediaCalendar(0, true);
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public void onFinishFastScroll(RecyclerListView r6) {
            if (this.inFastScrollMode == false) goto L20;
            this.inFastScrollMode = false;
            if (r6 == null) goto L21;
            int r1 = 0;
            int r2 = 0;
        L8:
            if (r1 >= r6.getChildCount()) goto L15;
            View r3 = r6.getChildAt(r1);
            if ((r3 instanceof SharedPhotoVideoCell2) == false) goto L12;
            r2 = ((SharedPhotoVideoCell2) r3).getMessageId();
        L12:
            if (r2 != 0) goto L15;
            r1 = r1 + 1;
        L15:
            if (r2 != 0) goto L22;
            SharedMediaLayout.m12891$$Nest$mfindPeriodAndJumpToDate(this.this$0, 0, r6, true);
            return;
        L22:
            return;
        L21:
            return;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public void onStartFastScroll() {
            this.inFastScrollMode = true;
            MediaPage r2 = SharedMediaLayout.m12893$$Nest$mgetMediaPage(this.this$0, 0);
            if (r2 == null) goto L6;
            SharedMediaLayout.m12923$$Nest$smshowFastScrollHint(r2, null, false);
            return;
        }
    }

    public class StoriesAdapter extends SharedPhotoVideoAdapter {
        private final int albumId;
        public boolean applyingReorder;
        private int id;
        private boolean inAlbumStoriesReorder;
        private final boolean isArchive;
        public ArrayList<Integer> lastPinnedIds;
        private ViewsForPeerStoriesRequester poller;
        public StoriesController.StoriesList storiesList;
        private StoriesAdapter supportingAdapter;
        final /* synthetic */ SharedMediaLayout this$0;
        private final ArrayList<StoriesController.UploadingStory> uploadingStories;

        /* renamed from: org.telegram.ui.Components.SharedMediaLayout$StoriesAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends ViewsForPeerStoriesRequester {
            final /* synthetic */ StoriesAdapter this$1;
            final /* synthetic */ SharedMediaLayout val$this$0;

            public AnonymousClass1(StoriesAdapter r1, StoriesController r2, long r3, int r5, SharedMediaLayout r6) {
                this.this$1 = r1;
                this.val$this$0 = r6;
                super(r2, r3, r5);
            }

            @Override // org.telegram.ui.Stories.ViewsForPeerStoriesRequester
            public void getStoryIds(ArrayList<Integer> r5) {
                int r0 = 0;
                int r1 = 0;
            L4:
                if (r1 >= SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$1.this$0).length) goto L11;
                if (MediaPage.m12961$$Nest$fgetlistView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$1.this$0)[r1]) == null) goto L10;
                RecyclerView.Adapter r2 = MediaPage.m12961$$Nest$fgetlistView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$1.this$0)[r1]).getAdapter();
                StoriesAdapter r3 = this.this$1;
                if (r2 != r3) goto L10;
                InternalListView r4 = MediaPage.m12961$$Nest$fgetlistView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(r3.this$0)[r1]);
            L12:
                if (r4 != null) goto L14;
                return;
            L14:
                if (r0 >= r4.getChildCount()) goto L33;
                View r12 = r4.getChildAt(r0);
                if ((r12 instanceof SharedPhotoVideoCell2) == false) goto L22;
                MessageObject r13 = ((SharedPhotoVideoCell2) r12).getMessageObject();
                if (r13 == null) goto L22;
                if (r13.isStory() == false) goto L22;
                r5.add(Integer.valueOf(r13.storyItem.id));
            L22:
                r0 = r0 + 1;
                goto L14
            L33:
                return;
            L10:
                r1 = r1 + 1;
                goto L4
            L11:
                r4 = null;
                goto L12
            }

            @Override // org.telegram.ui.Stories.ViewsForPeerStoriesRequester
            public boolean updateStories(ArrayList<Integer> r1, TL_stories.TL_stories_storyViews r2) {
                this.this$1.storiesList.updateStoryViews(r1, r2.views);
                return true;
            }
        }

        /* renamed from: org.telegram.ui.Components.SharedMediaLayout$StoriesAdapter$2, reason: invalid class name */
        public class AnonymousClass2 extends MessageObject {
            final /* synthetic */ StoriesAdapter this$1;

            public AnonymousClass2(StoriesAdapter r1, int r2, TL_stories.StoryItem r3) {
                this.this$1 = r1;
                super(r2, r3);
            }

            @Override // org.telegram.messenger.MessageObject
            public float getProgress() {
                return this.uploadingStory.progress;
            }
        }

        /* renamed from: -$$Nest$fgetpoller, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ViewsForPeerStoriesRequester m12996$$Nest$fgetpoller(StoriesAdapter r0) {
            return r0.poller;
        }

        public StoriesAdapter(SharedMediaLayout r8, Context r9, int r10, boolean r11) {
            this.this$0 = r8;
            super(r8, r9);
            this.uploadingStories = new ArrayList();
            this.lastPinnedIds = new ArrayList();
            this.isArchive = r11;
            this.albumId = r10;
            int r92 = SharedMediaLayout.m12834$$Nest$fgetprofileActivity(r8).getCurrentAccount();
            String r1 = null;
            if (TextUtils.isEmpty(r8.getStoriesHashtag()) == true) goto L13;
            if (r8.searchStoriesList == null) goto L7;
        L11:
            this.storiesList = r8.searchStoriesList;
        L42:
            StoriesController.StoriesList r93 = this.storiesList;
            if (r93 == null) goto L45;
            this.id = r93.link();
            StoriesAdapter r12 = this;
            r12.poller = new AnonymousClass1(r12, SharedMediaLayout.m12834$$Nest$fgetprofileActivity(r8).getMessagesController().getStoriesController(), SharedMediaLayout.m12811$$Nest$fgetdialog_id(r8), this.storiesList.currentAccount, r8);
        L46:
            r12.checkColumns();
            return;
        L45:
            r12 = this;
            goto L46
        L7:
            if (TextUtils.isEmpty(r8.getStoriesHashtagUsername()) == true) goto L10;
            r1 = r8.getStoriesHashtagUsername();
        L10:
            r8.searchStoriesList = new StoriesController.SearchStoriesList(r92, r1, r8.getStoriesHashtag());
            goto L11
        L13:
            if (r8.getStoriesArea() != null) goto L15;
            if (r11 == true) goto L20;
        L21:
            if (r11 == false) goto L23;
        L25:
            int r13 = 1;
            if (SharedMediaLayout.m12811$$Nest$fgetdialog_id(r8) <= 0) goto L32;
            TLRPC.User r94 = MessagesController.getInstance(r92).getUser(Long.valueOf(SharedMediaLayout.m12811$$Nest$fgetdialog_id(r8)));
            if (r94 == null) goto L32;
            if (r94.bot == false) goto L32;
            boolean r95 = true;
        L33:
            if (r10 <= 0) goto L35;
            this.storiesList = SharedMediaLayout.m12834$$Nest$fgetprofileActivity(r8).getMessagesController().getStoriesController().getStoriesList(SharedMediaLayout.m12811$$Nest$fgetdialog_id(r8), 0, r10);
            goto L42
        L35:
            StoriesController r102 = SharedMediaLayout.m12834$$Nest$fgetprofileActivity(r8).getMessagesController().getStoriesController();
            long r3 = SharedMediaLayout.m12811$$Nest$fgetdialog_id(r8);
            if (r95 == false) goto L38;
            r13 = 4;
        L41:
            this.storiesList = r102.getStoriesList(r3, r13);
            goto L42
        L38:
            if (r11 == true) goto L41;
            r13 = 0;
        L32:
            r95 = false;
            goto L33
        L23:
            if (r8.isArchivedOnlyStoriesView() == false) goto L25;
        L24:
            this.storiesList = null;
            goto L42
        L20:
            if (r8.isStoriesView() == false) goto L24;
        L15:
            if (r8.searchStoriesList != null) goto L17;
            r8.searchStoriesList = new StoriesController.SearchStoriesList(r92, r8.getStoriesArea());
        L17:
            this.storiesList = r8.searchStoriesList;
            goto L42
        }

        public StoriesAdapter(SharedMediaLayout r2, Context r3, boolean r4) {
            this(r2, r3, 0, r4);
        }

        private void checkColumns() {
            if (this.storiesList != null) goto L6;
            return;
        L6:
            if (this.isArchive == false) goto L8;
            return;
        L8:
            if (SharedMediaLayout.m12850$$Nest$fgetstoriesColumnsCountSet(this.this$0) == false) goto L14;
            if (SharedMediaLayout.m12791$$Nest$fgetallowStoriesSingleColumn(this.this$0) == true) goto L12;
            return;
        L12:
            if (this.storiesList.getCount() > 1) goto L14;
            return;
        L14:
            if (this.storiesList.getCount() > 0) goto L16;
            return;
        L16:
            if (this.this$0.isStoriesView() == true) goto L53;
            int r0 = this.storiesList.getCount();
            SharedMediaLayout r2 = this.this$0;
            boolean r3 = false;
            if (r0 >= 5) goto L35;
            SharedMediaLayout.m12824$$Nest$fgetmediaColumnsCount(r2)[1] = this.storiesList.getCount();
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0) != null) goto L22;
        L30:
            SharedMediaLayout r02 = this.this$0;
            if (SharedMediaLayout.m12824$$Nest$fgetmediaColumnsCount(r02)[1] != 1) goto L33;
            r3 = true;
        L33:
            SharedMediaLayout.m12859$$Nest$fputallowStoriesSingleColumn(r02, r3);
        L47:
            SharedMediaLayout.m12878$$Nest$fputstoriesColumnsCountSet(this.this$0, true);
            return;
        L22:
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0] == null) goto L30;
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[1] == null) goto L30;
            if (MediaPage.m12961$$Nest$fgetlistView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0]) == null) goto L30;
            if (MediaPage.m12961$$Nest$fgetlistView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[1]) == null) goto L30;
            SharedMediaLayout.m12918$$Nest$mswitchToCurrentSelectedMode(this.this$0, false);
            goto L30
        L35:
            if (SharedMediaLayout.m12791$$Nest$fgetallowStoriesSingleColumn(r2) == false) goto L47;
            SharedMediaLayout.m12859$$Nest$fputallowStoriesSingleColumn(this.this$0, false);
            SharedMediaLayout.m12824$$Nest$fgetmediaColumnsCount(this.this$0)[1] = Math.max(2, SharedConfig.storiesColumnsCount);
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0) == null) goto L47;
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0] == null) goto L47;
            if (SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[1] == null) goto L47;
            if (MediaPage.m12961$$Nest$fgetlistView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[0]) == null) goto L47;
            if (MediaPage.m12961$$Nest$fgetlistView(SharedMediaLayout.m12825$$Nest$fgetmediaPages(this.this$0)[1]) == null) goto L47;
            SharedMediaLayout.m12918$$Nest$mswitchToCurrentSelectedMode(this.this$0, false);
            goto L47
        }

        public boolean canReorder(int r7) {
            if (this.isArchive == false) goto L5;
            return false;
        L5:
            StoriesController.StoriesList r0 = this.storiesList;
            if (r0 != null) goto L9;
            return false;
        L9:
            if ((r0 instanceof StoriesController.BotPreviewsList) == false) goto L20;
            TLRPC.User r6 = MessagesController.getInstance(SharedMediaLayout.m12834$$Nest$fgetprofileActivity(this.this$0).getCurrentAccount()).getUser(Long.valueOf(SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0)));
            if (r6 != null) goto L13;
        L19:
            return false;
        L13:
            if (r6.bot == false) goto L19;
            if (r6.bot_has_main_app == false) goto L19;
            if (r6.bot_can_edit == false) goto L19;
            return true;
        L20:
            if (r7 >= 0) goto L22;
        L29:
            return false;
        L22:
            if (r7 >= r0.messageObjects.size()) goto L29;
            MessageObject r72 = this.storiesList.messageObjects.get(r7);
            StoriesController.StoriesList r62 = this.storiesList;
            if (r62.albumId <= 0) goto L28;
            return true;
        L28:
            return r62.isPinned(r72.getId());
        }

        public int columnsCount() {
            SharedPhotoVideoAdapter r0 = SharedMediaLayout.m12829$$Nest$fgetphotoVideoAdapter(this.this$0);
            SharedMediaLayout r1 = this.this$0;
            if (this == r0) goto L5;
            int r02 = SharedMediaLayout.m12916$$Nest$mstoryAlbums_getTabTypeByStoriesAdapter(r1, this);
            SharedMediaLayout r2 = this.this$0;
            if (r02 == (-1)) goto L11;
            return SharedMediaLayout.m12824$$Nest$fgetmediaColumnsCount(r2)[1];
        L11:
            return SharedMediaLayout.m12792$$Nest$fgetanimateToColumnsCount(r2);
        L5:
            return SharedMediaLayout.m12824$$Nest$fgetmediaColumnsCount(r1)[0];
        }

        public void destroy() {
            StoriesController.StoriesList r0 = this.storiesList;
            if (r0 == null) goto L6;
            r0.unlink(this.id);
            return;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.SharedPhotoVideoAdapter, org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public /* bridge */ /* synthetic */ boolean fastScrollIsVisible(RecyclerListView r1) {
            return super.fastScrollIsVisible(r1);
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.SharedPhotoVideoAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            int r1 = 0;
            if (this.storiesList != null) goto L5;
            return 0;
        L5:
            int r0 = this.uploadingStories.size();
            if (this.storiesList.isOnlyCache() == true) goto L8;
        L10:
            r1 = this.storiesList.getCount();
        L12:
            return r0 + r1;
        L8:
            if (this.this$0.hasInternet() == false) goto L10;
            goto L10
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.SharedPhotoVideoAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return 19;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.SharedPhotoVideoAdapter, org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public String getLetter(int r3) {
            if (this.storiesList != null) goto L5;
            return null;
        L5:
            int r32 = r3 - getTopOffset();
            if (r32 >= 0) goto L8;
        L17:
            return null;
        L8:
            if (r32 >= this.storiesList.messageObjects.size()) goto L17;
            MessageObject r2 = this.storiesList.messageObjects.get(r32);
            if (r2 == null) goto L17;
            if (r2.storyItem == null) goto L17;
            return LocaleController.formatYearMont(r2.date, true);
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.SharedPhotoVideoAdapter
        public int getPositionForIndex(int r2) {
            if (this.isArchive == true) goto L5;
            return r2;
        L5:
            return getTopOffset() + r2;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.SharedPhotoVideoAdapter, org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public /* bridge */ /* synthetic */ void getPositionForScrollProgress(RecyclerListView r1, float r2, int[] r3) {
            super.getPositionForScrollProgress(r1, r2, r3);
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.SharedPhotoVideoAdapter, org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public /* bridge */ /* synthetic */ float getScrollProgress(RecyclerListView r1) {
            return super.getScrollProgress(r1);
        }

        public int getTopOffset() {
            return 0;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.SharedPhotoVideoAdapter, org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public int getTotalItemsCount() {
            return getItemCount();
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.SharedPhotoVideoAdapter, org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return false;
        }

        public void load(boolean r4) {
            if (this.storiesList != null) goto L5;
            return;
        L5:
            int r0 = columnsCount();
            int r02 = Math.min(100, (Math.max(1, r0 / 2) * r0) * r0);
            this.storiesList.load(r4, r02);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            if (this.storiesList != null) goto L5;
        L9:
            super.notifyDataSetChanged();
            StoriesAdapter r0 = this.supportingAdapter;
            if (r0 == null) goto L12;
            r0.notifyDataSetChanged();
        L12:
            checkColumns();
            return;
        L5:
            if (this.this$0.isBot() == false) goto L9;
            this.uploadingStories.clear();
            ArrayList<StoriesController.UploadingStory> r02 = MessagesController.getInstance(this.storiesList.currentAccount).getStoriesController().getUploadingStories(SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0));
            if (r02 == null) goto L9;
            this.uploadingStories.addAll(r02);
            goto L9
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.SharedPhotoVideoAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r8, int r9) {
            if (this.storiesList != null) goto L6;
            return;
        L6:
            if (r8.getItemViewType() != 19) goto L54;
            View r82 = r8.itemView;
            if ((r82 instanceof SharedPhotoVideoCell2) == false) goto L56;
            SharedPhotoVideoCell2 r83 = (SharedPhotoVideoCell2) r82;
            r83.isStory = true;
            if (r9 >= 0) goto L13;
        L19:
            int r92 = r9 - this.uploadingStories.size();
            if (r92 >= 0) goto L22;
        L52:
            r83.isStoryPinned = false;
            r83.setMessageObject(null, columnsCount());
            r83.isStory = true;
            return;
        L22:
            if (r92 >= this.storiesList.messageObjects.size()) goto L52;
            MessageObject r93 = this.storiesList.messageObjects.get(r92);
            if (r93 != null) goto L27;
        L29:
            boolean r2 = false;
        L30:
            r83.isStoryPinned = r2;
            if (this.this$0.isBot() == false) goto L33;
        L36:
            boolean r22 = true;
        L37:
            r83.setReorder(r22);
            r83.isSearchingHashtag = this.this$0.isSearchingStories();
            r83.setMessageObject(r93, columnsCount());
            SharedMediaLayout r23 = this.this$0;
            if (r23.isActionModeShowed == false) goto L49;
            if (r93 == null) goto L49;
            SparseArray[] r24 = SharedMediaLayout.m12844$$Nest$fgetselectedFiles(r23);
            if (r93.getDialogId() != SharedMediaLayout.m12811$$Nest$fgetdialog_id(this.this$0)) goto L43;
            char r3 = 0;
        L45:
            if (r24[r3].indexOfKey(r93.getId()) < 0) goto L47;
            boolean r94 = true;
        L48:
            r83.setChecked(r94, true);
        L50:
            r83.setReordering(this.inAlbumStoriesReorder, false);
            return;
        L47:
            r94 = false;
            goto L48
        L43:
            r3 = 1;
        L49:
            r83.setChecked(false, false);
            goto L50
        L33:
            if (r83.isStoryPinned == true) goto L36;
            r22 = false;
            goto L37
        L27:
            if (this.storiesList.isPinned(r93.getId()) == false) goto L29;
            r2 = true;
            goto L30
        L13:
            if (r9 >= this.uploadingStories.size()) goto L19;
            StoriesController.UploadingStory r95 = this.uploadingStories.get(r9);
            r83.isStoryPinned = false;
            if (r95.sharedMessageObject != null) goto L17;
            TL_stories.TL_storyItem r25 = new TL_stories.TL_storyItem();
            int r32 = Long.hashCode(r95.random_id);
            r25.messageId = r32;
            r25.id = r32;
            r25.attachPath = r95.firstFramePath;
            AnonymousClass2 r33 = new AnonymousClass2(this, this.storiesList.currentAccount, r25);
            r95.sharedMessageObject = r33;
            r33.uploadingStory = r95;
        L17:
            r83.setMessageObject(r95.sharedMessageObject, columnsCount());
            r83.isStory = true;
            r83.setReorder(false);
            r83.setChecked(false, false);
            return;
        L56:
            return;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.SharedPhotoVideoAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
            RecyclerView.ViewHolder r0 = super.onCreateViewHolder(r1, r2);
            View r12 = r0.itemView;
            if ((r12 instanceof SharedPhotoVideoCell2) == false) goto L5;
            ((SharedPhotoVideoCell2) r12).isStory = true;
        L5:
            return r0;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.SharedPhotoVideoAdapter, org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public void onFastScrollSingleTap() {
            SharedMediaLayout r0 = this.this$0;
            if (this.isArchive == false) goto L5;
            int r2 = 9;
        L6:
            r0.showMediaCalendar(r2, true);
            return;
        L5:
            r2 = 8;
            goto L6
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.SharedPhotoVideoAdapter, org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public /* bridge */ /* synthetic */ void onFinishFastScroll(RecyclerListView r1) {
            super.onFinishFastScroll(r1);
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.SharedPhotoVideoAdapter, org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public /* bridge */ /* synthetic */ void onStartFastScroll() {
            super.onStartFastScroll();
        }

        public void reorderDone() {
            if (this.isArchive == true) goto L40;
            StoriesController.StoriesList r0 = this.storiesList;
            if (r0 != null) goto L9;
            return;
        L9:
            if (this.applyingReorder == true) goto L12;
            return;
        L12:
            if ((r0 instanceof StoriesController.BotPreviewsList) == false) goto L14;
        L17:
            ArrayList<Integer> r02 = new ArrayList();
            int r1 = 0;
        L19:
            if (r1 >= this.storiesList.messageObjects.size()) goto L22;
            r02.add(Integer.valueOf(this.storiesList.messageObjects.get(r1).getId()));
            r1 = r1 + 1;
        L22:
            if (this.lastPinnedIds.size() == r02.size()) goto L24;
            boolean r12 = true;
        L25:
            if (r12 == true) goto L33;
            int r3 = 0;
        L28:
            if (r3 >= this.lastPinnedIds.size()) goto L33;
            if (this.lastPinnedIds.get(r3) != r02.get(r3)) goto L31;
            r3 = r3 + 1;
            goto L28
        L31:
            r12 = true;
        L33:
            if (r12 == false) goto L35;
            this.storiesList.updatePinnedOrder(r02, true);
        L35:
            this.applyingReorder = false;
            return;
        L24:
            r12 = false;
            goto L25
        L14:
            if (this.albumId > 0) goto L17;
            r02 = r0.pinnedIds;
            goto L22
        }

        public void setInAlbumStoriesReorder(boolean r2) {
            if (this.inAlbumStoriesReorder == r2) goto L6;
            this.inAlbumStoriesReorder = r2;
            return;
        }

        public boolean swapElements(int r6, int r7) {
            if (this.isArchive == false) goto L5;
            return false;
        L5:
            StoriesController.StoriesList r0 = this.storiesList;
            if (r0 != null) goto L8;
            return false;
        L8:
            if (r6 >= 0) goto L10;
        L31:
            return false;
        L10:
            if (r6 >= r0.messageObjects.size()) goto L31;
            if (r7 < 0) goto L31;
            if (r7 >= this.storiesList.messageObjects.size()) goto L31;
            if ((this.storiesList instanceof StoriesController.BotPreviewsList) == false) goto L19;
        L22:
            ArrayList<Integer> r02 = new ArrayList();
            int r2 = 0;
        L24:
            if (r2 >= this.storiesList.messageObjects.size()) goto L27;
            r02.add(Integer.valueOf(this.storiesList.messageObjects.get(r2).getId()));
            r2 = r2 + 1;
        L27:
            if (this.applyingReorder == true) goto L29;
            this.lastPinnedIds.clear();
            this.lastPinnedIds.addAll(r02);
            this.applyingReorder = true;
        L29:
            MessageObject r22 = this.storiesList.messageObjects.get(r6);
            MessageObject r4 = this.storiesList.messageObjects.get(r7);
            r02.remove(Integer.valueOf(r22.getId()));
            r02.add(Utilities.clamp(r7, r02.size(), 0), Integer.valueOf(r22.getId()));
            this.storiesList.updatePinnedOrder(r02, false);
            notifyItemMoved(r6, r7);
            return true;
        L19:
            if (this.albumId > 0) goto L22;
            r02 = new ArrayList(this.storiesList.pinnedIds);
            goto L27
        }
    }

    public class StoryAlbumData {
        public final StoriesAdapter adapter;
        public final StoriesAdapter adapterSupport;
        public final int albumId;
        public final int tabType;
        final /* synthetic */ SharedMediaLayout this$0;

        /* renamed from: org.telegram.ui.Components.SharedMediaLayout$StoryAlbumData$1, reason: invalid class name */
        public class AnonymousClass1 extends StoriesAdapter {
            final /* synthetic */ StoryAlbumData this$1;
            final /* synthetic */ SharedMediaLayout val$this$0;

            public AnonymousClass1(StoryAlbumData r1, Context r2, int r3, boolean r4, SharedMediaLayout r5) {
                this.this$1 = r1;
                this.val$this$0 = r5;
                super(r1.this$0, r2, r3, r4);
            }

            @Override // org.telegram.ui.Components.SharedMediaLayout.StoriesAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
            public void notifyDataSetChanged() {
                super.notifyDataSetChanged();
                StoryAlbumData r0 = this.this$1;
                MediaPage r02 = SharedMediaLayout.m12893$$Nest$mgetMediaPage(r0.this$0, r0.tabType);
                if (r02 != null) goto L5;
            L7:
                if (r02 == null) goto L20;
                StickerEmptyView r03 = MediaPage.m12957$$Nest$fgetemptyView(r02);
                StoriesController.StoriesList r1 = this.storiesList;
                if (r1 != null) goto L11;
            L17:
                boolean r2 = false;
            L18:
                r03.showProgress(r2);
                return;
            L11:
                if (r1.isLoading() == false) goto L13;
            L16:
                r2 = true;
                goto L18
            L13:
                if (this.this$1.this$0.hasInternet() == false) goto L17;
                if (this.storiesList.getCount() <= 0) goto L17;
            L20:
                return;
            L5:
                if (MediaPage.m12956$$Nest$fgetanimationSupportingListView(r02).getVisibility() != 0) goto L7;
                this.this$1.adapterSupport.notifyDataSetChanged();
                goto L7
            }
        }

        private StoryAlbumData(SharedMediaLayout r8, Context r9, int r10) {
            this.this$0 = r8;
            this.albumId = r10;
            int r0 = SharedMediaLayout.m12851$$Nest$fgettabIndexCounter(r8);
            SharedMediaLayout.m12879$$Nest$fputtabIndexCounter(r8, r0 + 1);
            this.tabType = SharedMediaLayout.m12922$$Nest$smgetStoryAlbumType(r0);
            this.adapter = new AnonymousClass1(this, r9, r10, false, r8);
            this.adapterSupport = new StoriesAdapter(r8, r9, r10, false);
        }

        public /* synthetic */ StoryAlbumData(SharedMediaLayout r1, Context r2, int r3, SharedMediaLayoutIA r4) {
            this(r1, r2, r3);
        }
    }

    /* renamed from: $r8$lambda$08lqdBHavWFpvZKphAvh-YnnSYA, reason: not valid java name */
    public static /* synthetic */ void m12761$r8$lambda$08lqdBHavWFpvZKphAvhYnnSYA(ItemOptions r0, ItemOptions r1) {
        r0.openSwipeback(r1);
    }

    public static /* synthetic */ void $r8$lambda$5HkhrPw45zzh_7twudE3PX0h4hc(SharedMediaLayout r0, View r1) {
        r0.lambda$new$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$5mBTAfJAW148Kq_2E23902LEhmY(SharedMediaLayout r0, String r1, BaseFragment r2) {
        r0.lambda$addStoryAlbumShareItemOptions$81(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$66s_DLfZmhcExTR7Kp_86Knq7wg(SharedMediaLayout r0, View[] r1) {
        r0.lambda$addZoomInZoomOutItemOptions$82(r1);
    }

    public static /* synthetic */ void $r8$lambda$6VSDxoMM5_LA0At4Rt3lTyIAnUY(SharedMediaLayout r0, TLRPC.TL_error r1, int r2, int r3, TLObject r4) {
        r0.lambda$loadFastScrollData$39(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$6Y6UeZAgA2EVtY3MgeoYpvIdoAI(SharedMediaLayout r0, BaseFragment r1, long r2, int r4, ItemOptions r5) {
        r0.lambda$buildItemOptionsForStoryAlbumActionBar$78(r1, r2, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$6yCYe81Y2RSDBlffAnNxppa3O0k(SharedMediaLayout r0, boolean r1) {
        r0.lambda$updateSearchItemIconAnimated$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$7iolReiangA1SCGV_PNyY13kH4k(SharedMediaLayout r0, Theme.ResourcesProvider r1, MessageObject r2, int r3, AlertDialog r4, int r5) {
        r0.lambda$new$28(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$7nLI5dvvBy-yV42nwwgDzzMJUSA, reason: not valid java name */
    public static /* synthetic */ void m12762$r8$lambda$7nLI5dvvByyV42nwwgDzzMJUSA(SharedMediaLayout r0, MediaPage r1, Context r2, long r3, Theme.ResourcesProvider r5, View r6, int r7, float r8, float r9) {
        r0.lambda$new$30(r1, r2, r3, r5, r6, r7, r8, r9);
    }

    /* renamed from: $r8$lambda$7twRAvLm3Bb3sEs60Cmcc8-JMNs, reason: not valid java name */
    public static /* synthetic */ void m12763$r8$lambda$7twRAvLm3Bb3sEs60Cmcc8JMNs(SharedMediaLayout r0, ArrayList r1, AlertDialog r2, int r3) {
        r0.lambda$onActionBarItemClick$45(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$8cBEGJZoMAHksHfdLerJ0fzt1No(SharedMediaLayout r0, long r1, int r3) {
        r0.lambda$openDeleteStoriesAlbumAlert$76(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$ADiuXWtCYQDVQ_ErlZ96bMWs1Vk(SharedMediaLayout r0, int r1) {
        r0.lambda$onItemLongClick$60(r1);
    }

    public static /* synthetic */ void $r8$lambda$BkrhzrL_huCO1Fx4Y7WkDm6SBC0(SharedMediaLayout r0, AlertDialog r1, MessagesController r2, Context r3, SharedPhotoVideoCell2 r4, float r5, float r6, int r7, TL_account.contentSettings r8) {
        r0.lambda$new$17(r1, r2, r3, r4, r5, r6, r7, r8);
    }

    /* renamed from: $r8$lambda$E33xJA6ymn-n1evTQ-YTvtEjniw, reason: not valid java name */
    public static /* synthetic */ void m12764$r8$lambda$E33xJA6ymnn1evTQYTvtEjniw(SharedMediaLayout r0, View r1) {
        r0.lambda$new$10(r1);
    }

    public static /* synthetic */ void $r8$lambda$FfaTXjPPclzOTbr0G8WgsJIIGbQ(SharedMediaLayout r0, View[] r1) {
        r0.lambda$addZoomInZoomOutItemOptions$83(r1);
    }

    public static /* synthetic */ void $r8$lambda$FgdacGBLGmdJkd1VpFzlIum1H_Q(SharedMediaLayout r0, TL_stories.StoryItem r1, String r2) {
        r0.lambda$onItemLongClick$55(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$FsMVQku6dMoMKaYz_4SMV99sos4(SharedMediaLayout r0, int r1) {
        r0.lambda$getThemeDescriptions$73(r1);
    }

    /* renamed from: $r8$lambda$G6Awdk7c2tTwI-Z4XdiXDN3z9nI, reason: not valid java name */
    public static /* synthetic */ void m12765$r8$lambda$G6Awdk7c2tTwIZ4XdiXDN3z9nI(boolean[] r1, DialogInterface r2) {
        r1[0] = true;
    }

    /* renamed from: $r8$lambda$G8znxryR3au8SgcKAAz5h7-sRf4, reason: not valid java name */
    public static /* synthetic */ void m12766$r8$lambda$G8znxryR3au8SgcKAAz5h7sRf4(SharedMediaLayout r0, AlertDialog[] r1, int r2, TLRPC.TL_messages_editMessage r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$new$25(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$GRR2iS5LPXdSS-wwGBP6AZSVYBU, reason: not valid java name */
    public static /* synthetic */ void m12767$r8$lambda$GRR2iS5LPXdSSwwGBP6AZSVYBU(MediaPage r0, ValueAnimator r1) {
        MediaPage.m12961$$Nest$fgetlistView(r0).invalidate();
    }

    /* renamed from: $r8$lambda$I6K3iD-HjL9q6VNMgcY_ndDuTxI, reason: not valid java name */
    public static /* synthetic */ void m12768$r8$lambda$I6K3iDHjL9q6VNMgcY_ndDuTxI(SharedMediaLayout r0, StoriesController.StoryAlbum r1) {
        r0.lambda$onStoryAlbumCreate$84(r1);
    }

    public static /* synthetic */ void $r8$lambda$ISP_0waSH7fZavyjWT2pbY6nVLg(AlertDialog[] r3, int r4, int r5) {
        AlertDialog r1 = r3[0];
        if (r1 != null) goto L5;
        return;
    L5:
        r1.setOnCancelListener(new SharedMediaLayout$$ExternalSyntheticLambda74(r4, r5));
        r3[0].show();
    }

    public static /* synthetic */ void $r8$lambda$JaHvFekyc84ovlPAcZfdw4TsMIg(SharedMediaLayout r0, StoriesController.BotPreviewsList r1, ArrayList r2, AlertDialog r3, int r4) {
        r0.lambda$onActionBarItemClick$43(r1, r2, r3, r4);
    }

    public static /* synthetic */ boolean $r8$lambda$Jsai2k6drcjqLlrrHMD68mn_r2I(SharedMediaLayout r0, View r1) {
        return r0.lambda$new$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$K2gyKha3EfFfed222wYv1iVRKuU(SharedMediaLayout r0, TL_stories.StoryItem r1) {
        r0.lambda$onItemLongClick$65(r1);
    }

    public static /* synthetic */ void $r8$lambda$K41AfOrVDuRacDQueGiAUJ5C8pw(BaseFragment r2) {
        r2.presentFragment(new ThemeActivity(0).highlightSensitiveRow());
    }

    /* renamed from: $r8$lambda$KC3b71yagf2DO2h1aV-2iFmODjQ, reason: not valid java name */
    public static /* synthetic */ void m12769$r8$lambda$KC3b71yagf2DO2h1aV2iFmODjQ(SharedMediaLayout r0, View r1) {
        r0.lambda$new$11(r1);
    }

    public static /* synthetic */ Boolean $r8$lambda$M6zaUu1gbTWVgZ75iN__66TMJCU(ArrayList r2, Integer r3) {
        int r0 = 0;
    L4:
        if (r0 >= r2.size()) goto L11;
        if (((Pair) r2.get(r0)).first == r3) goto L8;
        r0 = r0 + 1;
        goto L4
    L8:
        return Boolean.TRUE;
    L11:
        return Boolean.FALSE;
    }

    /* renamed from: $r8$lambda$MiPncLsaYY7WU8J9w4Aqt-ENG1M, reason: not valid java name */
    public static /* synthetic */ boolean m12770$r8$lambda$MiPncLsaYY7WU8J9w4AqtENG1M(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$NIZoMOy_Gu9dSlLBy3uJI0_IqfU(SharedMediaLayout r0, int r1, TLRPC.TL_error r2, TLRPC.TL_messages_editMessage r3) {
        r0.lambda$new$24(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$Rgb8IGkkf4LX2k1K4ii_UJJp6wM(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$RqjPa34H_6i_wMTcaEbhBPqgc3g(SharedMediaLayout r0, View r1) {
        r0.lambda$new$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$SdKpKEKnntOutYaH9R_ZPaN00Ns(AlertDialog[] r3, int r4, int r5) {
        AlertDialog r1 = r3[0];
        if (r1 != null) goto L5;
        return;
    L5:
        r1.setOnCancelListener(new SharedMediaLayout$$ExternalSyntheticLambda80(r4, r5));
        r3[0].show();
    }

    public static /* synthetic */ void $r8$lambda$U4cvLdM0fkrJnt5idrzWasduOII(SharedMediaLayout r0, ValueAnimator r1) {
        r0.lambda$stopScroll$50(r1);
    }

    public static /* synthetic */ void $r8$lambda$U_EFxz4MB2wUE7hiT2rs6Wq_Klo(SharedMediaLayout r0, TL_stories.StoryItem r1, StoriesController.StoryAlbum r2) {
        r0.lambda$onItemLongClick$54(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$WFAtXEvGbpacqNKJczv14aHVxMo(SharedMediaLayout r0, int r1, TL_stories.StoryItem r2, String r3) {
        r0.lambda$onItemLongClick$62(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$WTYNwS7YlV2XVSm3OB5u23Azu4A(SharedMediaLayout r0, long r1, int r3, String r4) {
        r0.lambda$openRenameStoriesAlbumAlert$75(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$XJO5xL3JR2uct0nb3MFcuPQHxv4(SharedMediaLayout r0, boolean r1, boolean r2) {
        r0.lambda$new$34(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$XwjspRaojwZ1j_a50Ifuf4pXHOI(StoriesController.StoriesList r1, boolean r2) {
        if (r2 == false) goto L5;
        r1.load(false, 30);
        return;
    }

    public static /* synthetic */ void $r8$lambda$YCv9ElbqOa5fZht3RC3OBavBrDI(SharedMediaLayout r0, TL_stories.StoryItem r1) {
        r0.lambda$onItemLongClick$68(r1);
    }

    public static /* synthetic */ void $r8$lambda$YXUgGlLq4yLnwBei54OjyjdlxNI(SharedMediaLayout r0, TLRPC.TL_error r1, int r2, int r3, TLObject r4) {
        r0.lambda$loadFastScrollData$40(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$_keaWgTVsD_fy5xexDkEduLM6LY(SharedMediaLayout r0, int r1, int r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$loadFastScrollData$41(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$bQ2nFkzYBApLz7LGjO71zYksn2c(SharedMediaLayout r0, long r1, int r3, ArrayList r4) {
        r0.lambda$openAddStoriesToAlbumSheet$77(r1, r3, r4);
    }

    /* renamed from: $r8$lambda$b_3M-Dvb_Xpai3iIKiaOSUVkEIU, reason: not valid java name */
    public static /* synthetic */ void m12771$r8$lambda$b_3MDvb_Xpai3iIKiaOSUVkEIU(SharedMediaLayout r0, Canvas r1, RectF r2) {
        r0.lambda$new$35(r1, r2);
    }

    /* renamed from: $r8$lambda$bdnerGRKQy-MbLh5f50BsjxSlU8, reason: not valid java name */
    public static /* synthetic */ void m12772$r8$lambda$bdnerGRKQyMbLh5f50BsjxSlU8(SharedMediaLayout r0, long r1, MessageObject r3) {
        r0.lambda$new$18(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$cUylFeDtfSXoeCcKAiZrSllYhqE(SharedMediaLayout r0, TL_stories.StoryItem r1, AlertDialog r2, int r3) {
        r0.lambda$onItemLongClick$66(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$du6we1gj5Fm2h_Ktz6LRWCWc6GI(SharedMediaLayout r0, RecyclerView.Adapter r1) {
        r0.lambda$postNotifyDataSetChangedSafely$0(r1);
    }

    /* renamed from: $r8$lambda$dvA1Sq5d-b5VkJYDyDkockpjc2o, reason: not valid java name */
    public static /* synthetic */ void m12773$r8$lambda$dvA1Sq5db5VkJYDyDkockpjc2o(SharedMediaLayout r0) {
        r0.lambda$new$33();
    }

    public static /* synthetic */ void $r8$lambda$eBnPQ__zxwyUKXtky2SPmcbv5OM(SharedPhotoVideoCell2 r0, float r1, float r2, boolean[] r3, boolean r4, TL_account.contentSettings r5, Context r6, int r7, MessagesController r8, AlertDialog r9, int r10) {
        SharedMediaLayout$$ExternalSyntheticLambda63 r92 = new SharedMediaLayout$$ExternalSyntheticLambda63(r0, r1, r2);
        if (r3[0] == false) goto L17;
        if (r4 == true) goto L11;
        if (r5 != null) goto L7;
    L9:
        r92.run(Boolean.TRUE);
        return;
    L7:
        if (r5.sensitive_can_change == false) goto L9;
    L11:
        BaseFragment r02 = LaunchActivity.getSafeLastFragment();
        SharedMediaLayout$$ExternalSyntheticLambda64 r12 = new SharedMediaLayout$$ExternalSyntheticLambda64(r8, r92);
        if (r02 != null) goto L14;
        Theme.ResourcesProvider r03 = null;
    L15:
        ThemeActivity.verifyAge(r6, r7, r12, r03);
        return;
    L14:
        r03 = r02.getResourceProvider();
        goto L15
    L17:
        r92.run(Boolean.FALSE);
    }

    public static /* synthetic */ void $r8$lambda$fxSn8p6GLBqfyeTT_SGKKnwUk8U(MessagesController r4, Utilities.Callback r5, Boolean r6) {
        BaseFragment r0 = LaunchActivity.getSafeLastFragment();
        if (r6.booleanValue() == false) goto L9;
        r4.setContentSettings(true);
        if (r0 == null) goto L7;
        BulletinFactory.of(r0).createSimpleBulletinDetail(R.raw.chats_infotip, AndroidUtilities.replaceArrows(AndroidUtilities.premiumText(LocaleController.getString(R.string.SensitiveContentSettingsToast), new SharedMediaLayout$$ExternalSyntheticLambda83(r0)), true)).show(true);
    L7:
        r5.run(Boolean.TRUE);
        return;
    L9:
        if (r0 == null) goto L12;
        BulletinFactory.of(r0).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.AgeVerificationFailedTitle), LocaleController.getString(R.string.AgeVerificationFailedText)).show();
        return;
    }

    public static /* synthetic */ void $r8$lambda$g4skAdkpzsM0kN7UXNhE1DJqFhY(SharedMediaLayout r0, int r1, TL_stories.StoryItem r2) {
        r0.lambda$onItemLongClick$61(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$g9i3q6gq0S8sIBkZ30Cvg9gQFn0(AlertDialog[] r2) {
        r2[0].dismiss();     // Catch: Throwable -> L6
    L4:
        r2[0] = null;
    }

    /* renamed from: $r8$lambda$gYwaWjk91EC-wofpzQbb3EZBFQk, reason: not valid java name */
    public static /* synthetic */ void m12774$r8$lambda$gYwaWjk91ECwofpzQbb3EZBFQk(MediaPage r1, int r2) {
        MediaPage.m12961$$Nest$fgetlistView(r1).scrollBy(0, r2);
    }

    public static /* synthetic */ void $r8$lambda$hG7DrtzxXKb7oTSgnKHsnDiVsy4(SharedMediaLayout r0, boolean r1, int r2, View r3) {
        r0.lambda$switchToCurrentSelectedMode$52(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$hiLBoCch6VVTmnZjNQXd-S4IZqc, reason: not valid java name */
    public static /* synthetic */ void m12775$r8$lambda$hiLBoCch6VVTmnZjNQXdS4IZqc(SharedMediaLayout r0, TL_stories.StoryItem r1, ItemOptions r2) {
        r0.lambda$onItemLongClick$56(r1, r2);
    }

    /* renamed from: $r8$lambda$igI-SI9oLbay3D1ekcLWX0o-1Lg, reason: not valid java name */
    public static /* synthetic */ void m12776$r8$lambda$igISI9oLbay3D1ekcLWX0o1Lg(SharedMediaLayout r0, int r1, RecyclerListView r2) {
        r0.lambda$checkLoadMoreScroll$37(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ihkV61H3eA69v1IyfThBB1TWcBQ(SharedMediaLayout r0, MessageObject r1, AlertDialog r2, boolean[] r3) {
        r0.lambda$parseMarkdownAsync$71(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$jxpdSI0XcvEexIJzVpeHgPhKHs8(boolean[] r3, View r4) {
        boolean r1 = !r3[0];
        r3[0] = r1;
        ((CheckBoxCell) r4).setChecked(r1, true);
    }

    public static /* synthetic */ void $r8$lambda$k0FMLofq8OxpRBHdWE8GhXkjgZI(SharedMediaLayout r0) {
        r0.lambda$onActionBarItemClick$48();
    }

    /* renamed from: $r8$lambda$mRxlglG_-OU-36UoNr235xpbRC0, reason: not valid java name */
    public static /* synthetic */ void m12777$r8$lambda$mRxlglG_OU36UoNr235xpbRC0(SharedMediaLayout r0, HashSet r1, TL_stories.StoryItem r2, ItemOptions r3, StoriesController.StoryAlbum r4) {
        r0.lambda$onItemLongClick$57(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$mYfxDHSztdBazM2Fhd2awaLfDjU(SharedMediaLayout r0, int r1, ItemOptions r2) {
        r0.lambda$buildItemOptionsForStoryAlbumActionBar$79(r1, r2);
    }

    /* renamed from: $r8$lambda$mquVE28SdXNmO53-VezhPsJeajA, reason: not valid java name */
    public static /* synthetic */ void m12778$r8$lambda$mquVE28SdXNmO53VezhPsJeajA(SharedMediaLayout r0, Theme.ResourcesProvider r1, MessageObject r2, int r3) {
        r0.lambda$new$29(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$p4IGz-dzuPYp8OUhOWzbSFOAyHk, reason: not valid java name */
    public static /* synthetic */ void m12779$r8$lambda$p4IGzdzuPYp8OUhOWzbSFOAyHk(SharedMediaLayout r0, TL_stories.StoryItem r1) {
        r0.lambda$onItemLongClick$64(r1);
    }

    public static /* synthetic */ float $r8$lambda$pgrLitFkYqSDqA1BbWpDVroXHNI(float r2) {
        float r22 = r2 - 1.0f;
        return ((((r22 * r22) * r22) * r22) * r22) + 1.0f;
    }

    /* renamed from: $r8$lambda$qPcb3sQ3Vepl4EhC-_kIcCd1UFI, reason: not valid java name */
    public static /* synthetic */ void m12780$r8$lambda$qPcb3sQ3Vepl4EhC_kIcCd1UFI(SharedMediaLayout r0) {
        r0.lambda$new$2();
    }

    /* renamed from: $r8$lambda$qanxF_mbqs6cmk-cGzcLl9OAEIA, reason: not valid java name */
    public static /* synthetic */ void m12781$r8$lambda$qanxF_mbqs6cmkcGzcLl9OAEIA(int r0, int r1, DialogInterface r2) {
        ConnectionsManager.getInstance(r0).cancelRequest(r1, true);
    }

    public static /* synthetic */ void $r8$lambda$rQGXHA1CXVwhFklfnswF6p9_7wo(SharedMediaLayout r0, View r1) {
        r0.lambda$new$5(r1);
    }

    /* renamed from: $r8$lambda$sJzfhQ5ODxcd-AChY7hOqXmwRl4, reason: not valid java name */
    public static /* synthetic */ void m12782$r8$lambda$sJzfhQ5ODxcdAChY7hOqXmwRl4(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    /* renamed from: $r8$lambda$sMfS9I-ph2ED20QlnGDMrqXhc00, reason: not valid java name */
    public static /* synthetic */ void m12783$r8$lambda$sMfS9Iph2ED20QlnGDMrqXhc00(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    public static /* synthetic */ int $r8$lambda$siAEEsCa6I5m_AtJh24R7TlZoAo(Period r0, Period r1) {
        return r1.date - r0.date;
    }

    public static /* synthetic */ void $r8$lambda$szVKEpDr71oIn31hsl82g1pKfGE(SharedMediaLayout r0, Theme.ResourcesProvider r1, int r2, MessageObject r3) {
        r0.lambda$new$22(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$tXhIsUtKiBpGWDVNxJFMFXlhFR8(SharedMediaLayout r0, TL_stories.StoryItem r1) {
        r0.lambda$onItemLongClick$63(r1);
    }

    public static /* synthetic */ void $r8$lambda$tdnmhYZohtLV7z8mq_YhrQs2EKQ(SharedPhotoVideoCell2 r0, float r1, float r2, Boolean r3) {
        r0.startRevealMedia(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$tlmpdWnLsvkkm6tCe5g7ElrDZzk(AlertDialog[] r2) {
        r2[0].dismiss();     // Catch: Throwable -> L6
    L4:
        r2[0] = null;
    }

    /* renamed from: $r8$lambda$uBs-Os_1UgJT2hEBoWDMQHcuVrk, reason: not valid java name */
    public static /* synthetic */ void m12784$r8$lambda$uBsOs_1UgJT2hEBoWDMQHcuVrk(MediaPage r2, SharedMediaFastScrollTooltip r3) {
        r2.fastScrollHintView = null;
        r2.fastScrollHideHintRunnable = null;
        r3.animate().alpha(0.0f).scaleX(0.5f).scaleY(0.5f).setDuration(220).setListener(new AnonymousClass42(r3)).start();
    }

    public static /* synthetic */ void $r8$lambda$volfZJ3deGmeWdSdsZ8PjeMEYWY(SharedMediaLayout r0, ArrayList r1, AlertDialog r2, int r3) {
        r0.lambda$onActionBarItemClick$47(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$vtvgUAsOiZZwyHB5k2ZlX8UsMUU(SharedMediaLayout r0, View r1) {
        r0.lambda$switchToCurrentSelectedMode$53(r1);
    }

    public static /* synthetic */ void $r8$lambda$w4r3jUuGltgfDguzcjgoHBRUxfg(SharedMediaLayout r0, BaseFragment r1, long r2, int r4, ItemOptions r5) {
        r0.lambda$buildItemOptionsForStoryAlbumActionBar$80(r1, r2, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$wFQRLTV_l4FguEWb5YDU2LoXqV0(SharedMediaLayout r0, View r1) {
        r0.lambda$new$6(r1);
    }

    /* renamed from: $r8$lambda$x-Oa5NBovLMEJVfKYMeWN-lBBxk, reason: not valid java name */
    public static /* synthetic */ void m12785$r8$lambda$xOa5NBovLMEJVfKYMeWNlBBxk(SharedMediaLayout r0, View r1) {
        r0.lambda$new$4(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$xDGrGDua23zRWd8HY6FlQXfh2qs(SharedMediaLayout r0, boolean r1, DialogsActivity r2, ArrayList r3, CharSequence r4, boolean r5, boolean r6, int r7, int r8, TopicsFragment r9) {
        return r0.lambda$onActionBarItemClick$49(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public static /* synthetic */ void $r8$lambda$zB4zMJLo0_6HaPFVBEXKLt8vJZk(int r0, int r1, DialogInterface r2) {
        ConnectionsManager.getInstance(r0).cancelRequest(r1, true);
    }

    /* renamed from: $r8$lambda$zWKbh7cssTyx53EBjEk-oBssnXw, reason: not valid java name */
    public static /* synthetic */ void m12786$r8$lambda$zWKbh7cssTyx53EBjEkoBssnXw(SharedMediaLayout r0, AlertDialog r1, boolean[] r2, boolean r3, MessageObject r4, TLRPC.WebPage r5) {
        r0.lambda$parseMarkdownAsync$70(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$zZxCvsoEC_QoWxKYIt_lQOrm3yQ(SharedMediaLayout r0, MessageObject r1, View r2, int r3) {
        r0.lambda$onItemLongClick$59(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetactionBar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBar m12787$$Nest$fgetactionBar(SharedMediaLayout r0) {
        return r0.actionBar;
    }

    /* renamed from: -$$Nest$fgetactionModeAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m12788$$Nest$fgetactionModeAnimation(SharedMediaLayout r0) {
        return r0.actionModeAnimation;
    }

    /* renamed from: -$$Nest$fgetactionModeLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m12789$$Nest$fgetactionModeLayout(SharedMediaLayout r0) {
        return r0.actionModeLayout;
    }

    /* renamed from: -$$Nest$fgetactionModeViews, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m12790$$Nest$fgetactionModeViews(SharedMediaLayout r0) {
        return r0.actionModeViews;
    }

    /* renamed from: -$$Nest$fgetallowStoriesSingleColumn, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12791$$Nest$fgetallowStoriesSingleColumn(SharedMediaLayout r0) {
        return r0.allowStoriesSingleColumn;
    }

    /* renamed from: -$$Nest$fgetanimateToColumnsCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12792$$Nest$fgetanimateToColumnsCount(SharedMediaLayout r0) {
        return r0.animateToColumnsCount;
    }

    /* renamed from: -$$Nest$fgetanimatingForward, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12793$$Nest$fgetanimatingForward(SharedMediaLayout r0) {
        return r0.animatingForward;
    }

    /* renamed from: -$$Nest$fgetanimationSupportingArchivedStoriesAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoriesAdapter m12794$$Nest$fgetanimationSupportingArchivedStoriesAdapter(SharedMediaLayout r0) {
        return r0.animationSupportingArchivedStoriesAdapter;
    }

    /* renamed from: -$$Nest$fgetanimationSupportingPhotoVideoAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SharedPhotoVideoAdapter m12795$$Nest$fgetanimationSupportingPhotoVideoAdapter(SharedMediaLayout r0) {
        return r0.animationSupportingPhotoVideoAdapter;
    }

    /* renamed from: -$$Nest$fgetanimationSupportingStoriesAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoriesAdapter m12796$$Nest$fgetanimationSupportingStoriesAdapter(SharedMediaLayout r0) {
        return r0.animationSupportingStoriesAdapter;
    }

    /* renamed from: -$$Nest$fgetarchivedStoriesAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoriesAdapter m12797$$Nest$fgetarchivedStoriesAdapter(SharedMediaLayout r0) {
        return r0.archivedStoriesAdapter;
    }

    /* renamed from: -$$Nest$fgetaudioAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SharedDocumentsAdapter m12798$$Nest$fgetaudioAdapter(SharedMediaLayout r0) {
        return r0.audioAdapter;
    }

    /* renamed from: -$$Nest$fgetaudioCache, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m12799$$Nest$fgetaudioCache(SharedMediaLayout r0) {
        return r0.audioCache;
    }

    /* renamed from: -$$Nest$fgetaudioCellCache, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m12800$$Nest$fgetaudioCellCache(SharedMediaLayout r0) {
        return r0.audioCellCache;
    }

    /* renamed from: -$$Nest$fgetaudioSearchAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MediaSearchAdapter m12801$$Nest$fgetaudioSearchAdapter(SharedMediaLayout r0) {
        return r0.audioSearchAdapter;
    }

    /* renamed from: -$$Nest$fgetbackAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12802$$Nest$fgetbackAnimation(SharedMediaLayout r0) {
        return r0.backAnimation;
    }

    /* renamed from: -$$Nest$fgetbackgroundPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m12803$$Nest$fgetbackgroundPaint(SharedMediaLayout r0) {
        return r0.backgroundPaint;
    }

    /* renamed from: -$$Nest$fgetbotPreviewsContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BotPreviewsEditContainer m12804$$Nest$fgetbotPreviewsContainer(SharedMediaLayout r0) {
        return r0.botPreviewsContainer;
    }

    /* renamed from: -$$Nest$fgetcantDeleteMessagesCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12805$$Nest$fgetcantDeleteMessagesCount(SharedMediaLayout r0) {
        return r0.cantDeleteMessagesCount;
    }

    /* renamed from: -$$Nest$fgetchangeColumnsTab, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12806$$Nest$fgetchangeColumnsTab(SharedMediaLayout r0) {
        return r0.changeColumnsTab;
    }

    /* renamed from: -$$Nest$fgetchangeTypeAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12807$$Nest$fgetchangeTypeAnimation(SharedMediaLayout r0) {
        return r0.changeTypeAnimation;
    }

    /* renamed from: -$$Nest$fgetchannelRecommendationsAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChannelRecommendationsAdapter m12808$$Nest$fgetchannelRecommendationsAdapter(SharedMediaLayout r0) {
        return r0.channelRecommendationsAdapter;
    }

    /* renamed from: -$$Nest$fgetchatUsersAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatUsersAdapter m12809$$Nest$fgetchatUsersAdapter(SharedMediaLayout r0) {
        return r0.chatUsersAdapter;
    }

    /* renamed from: -$$Nest$fgetdeleteItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m12810$$Nest$fgetdeleteItem(SharedMediaLayout r0) {
        return r0.deleteItem;
    }

    /* renamed from: -$$Nest$fgetdialog_id, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m12811$$Nest$fgetdialog_id(SharedMediaLayout r2) {
        return r2.dialog_id;
    }

    /* renamed from: -$$Nest$fgetdocumentsAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SharedDocumentsAdapter m12812$$Nest$fgetdocumentsAdapter(SharedMediaLayout r0) {
        return r0.documentsAdapter;
    }

    /* renamed from: -$$Nest$fgetdocumentsSearchAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MediaSearchAdapter m12813$$Nest$fgetdocumentsSearchAdapter(SharedMediaLayout r0) {
        return r0.documentsSearchAdapter;
    }

    /* renamed from: -$$Nest$fgetfirstTab, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12814$$Nest$fgetfirstTab(SharedMediaLayout r0) {
        return r0.firstTab;
    }

    /* renamed from: -$$Nest$fgetforwardItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m12815$$Nest$fgetforwardItem(SharedMediaLayout r0) {
        return r0.forwardItem;
    }

    /* renamed from: -$$Nest$fgetfragmentContextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FragmentContextView m12816$$Nest$fgetfragmentContextView(SharedMediaLayout r0) {
        return r0.fragmentContextView;
    }

    /* renamed from: -$$Nest$fgetfragmentContextViewWrapper, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m12817$$Nest$fgetfragmentContextViewWrapper(SharedMediaLayout r0) {
        return r0.fragmentContextViewWrapper;
    }

    /* renamed from: -$$Nest$fgetgifAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GifAdapter m12818$$Nest$fgetgifAdapter(SharedMediaLayout r0) {
        return r0.gifAdapter;
    }

    /* renamed from: -$$Nest$fgetgotoItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m12819$$Nest$fgetgotoItem(SharedMediaLayout r0) {
        return r0.gotoItem;
    }

    /* renamed from: -$$Nest$fgetgroupUsersSearchAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GroupUsersSearchAdapter m12820$$Nest$fgetgroupUsersSearchAdapter(SharedMediaLayout r0) {
        return r0.groupUsersSearchAdapter;
    }

    /* renamed from: -$$Nest$fgetignoreSearchCollapse, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12821$$Nest$fgetignoreSearchCollapse(SharedMediaLayout r0) {
        return r0.ignoreSearchCollapse;
    }

    /* renamed from: -$$Nest$fgetinfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.ChatFull m12822$$Nest$fgetinfo(SharedMediaLayout r0) {
        return r0.info;
    }

    /* renamed from: -$$Nest$fgetlinksSearchAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MediaSearchAdapter m12823$$Nest$fgetlinksSearchAdapter(SharedMediaLayout r0) {
        return r0.linksSearchAdapter;
    }

    /* renamed from: -$$Nest$fgetmediaColumnsCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m12824$$Nest$fgetmediaColumnsCount(SharedMediaLayout r0) {
        return r0.mediaColumnsCount;
    }

    /* renamed from: -$$Nest$fgetmediaPages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MediaPage[] m12825$$Nest$fgetmediaPages(SharedMediaLayout r0) {
        return r0.mediaPages;
    }

    /* renamed from: -$$Nest$fgetmergeDialogId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m12826$$Nest$fgetmergeDialogId(SharedMediaLayout r2) {
        return r2.mergeDialogId;
    }

    /* renamed from: -$$Nest$fgetoptionsAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12827$$Nest$fgetoptionsAlpha(SharedMediaLayout r0) {
        return r0.optionsAlpha;
    }

    /* renamed from: -$$Nest$fgetoptionsSearchImageView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RLottieImageView m12828$$Nest$fgetoptionsSearchImageView(SharedMediaLayout r0) {
        return r0.optionsSearchImageView;
    }

    /* renamed from: -$$Nest$fgetphotoVideoAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SharedPhotoVideoAdapter m12829$$Nest$fgetphotoVideoAdapter(SharedMediaLayout r0) {
        return r0.photoVideoAdapter;
    }

    /* renamed from: -$$Nest$fgetphotoVideoChangeColumnsAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12830$$Nest$fgetphotoVideoChangeColumnsAnimation(SharedMediaLayout r0) {
        return r0.photoVideoChangeColumnsAnimation;
    }

    /* renamed from: -$$Nest$fgetphotoVideoChangeColumnsProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12831$$Nest$fgetphotoVideoChangeColumnsProgress(SharedMediaLayout r0) {
        return r0.photoVideoChangeColumnsProgress;
    }

    /* renamed from: -$$Nest$fgetpinItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m12832$$Nest$fgetpinItem(SharedMediaLayout r0) {
        return r0.pinItem;
    }

    /* renamed from: -$$Nest$fgetpollAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PollAdapter m12833$$Nest$fgetpollAdapter(SharedMediaLayout r0) {
        return r0.pollAdapter;
    }

    /* renamed from: -$$Nest$fgetprofileActivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BaseFragment m12834$$Nest$fgetprofileActivity(SharedMediaLayout r0) {
        return r0.profileActivity;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m12835$$Nest$fgetresourcesProvider(SharedMediaLayout r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgetsavedDialogsAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SavedDialogsAdapter m12836$$Nest$fgetsavedDialogsAdapter(SharedMediaLayout r0) {
        return r0.savedDialogsAdapter;
    }

    /* renamed from: -$$Nest$fgetsavedMessagesContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivityContainer m12837$$Nest$fgetsavedMessagesContainer(SharedMediaLayout r0) {
        return r0.savedMessagesContainer;
    }

    /* renamed from: -$$Nest$fgetsavedMessagesSearchAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SavedMessagesSearchAdapter m12838$$Nest$fgetsavedMessagesSearchAdapter(SharedMediaLayout r0) {
        return r0.savedMessagesSearchAdapter;
    }

    /* renamed from: -$$Nest$fgetscrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12839$$Nest$fgetscrolling(SharedMediaLayout r0) {
        return r0.scrolling;
    }

    /* renamed from: -$$Nest$fgetsearchItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m12840$$Nest$fgetsearchItem(SharedMediaLayout r0) {
        return r0.searchItem;
    }

    /* renamed from: -$$Nest$fgetsearchItemState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12841$$Nest$fgetsearchItemState(SharedMediaLayout r0) {
        return r0.searchItemState;
    }

    /* renamed from: -$$Nest$fgetsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12842$$Nest$fgetsearching(SharedMediaLayout r0) {
        return r0.searching;
    }

    /* renamed from: -$$Nest$fgetsearchingReaction, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ReactionsLayoutInBubble.VisibleReaction m12843$$Nest$fgetsearchingReaction(SharedMediaLayout r0) {
        return r0.searchingReaction;
    }

    /* renamed from: -$$Nest$fgetselectedFiles, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SparseArray[] m12844$$Nest$fgetselectedFiles(SharedMediaLayout r0) {
        return r0.selectedFiles;
    }

    /* renamed from: -$$Nest$fgetselectedMessagesCountTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ NumberTextView m12845$$Nest$fgetselectedMessagesCountTextView(SharedMediaLayout r0) {
        return r0.selectedMessagesCountTextView;
    }

    /* renamed from: -$$Nest$fgetsharedMediaData, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SharedMediaData[] m12846$$Nest$fgetsharedMediaData(SharedMediaLayout r0) {
        return r0.sharedMediaData;
    }

    /* renamed from: -$$Nest$fgetshiftDp, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12847$$Nest$fgetshiftDp(SharedMediaLayout r0) {
        return r0.shiftDp;
    }

    /* renamed from: -$$Nest$fgetstartedTrackingX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12848$$Nest$fgetstartedTrackingX(SharedMediaLayout r0) {
        return r0.startedTrackingX;
    }

    /* renamed from: -$$Nest$fgetstoriesAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoriesAdapter m12849$$Nest$fgetstoriesAdapter(SharedMediaLayout r0) {
        return r0.storiesAdapter;
    }

    /* renamed from: -$$Nest$fgetstoriesColumnsCountSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12850$$Nest$fgetstoriesColumnsCountSet(SharedMediaLayout r0) {
        return r0.storiesColumnsCountSet;
    }

    /* renamed from: -$$Nest$fgettabIndexCounter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12851$$Nest$fgettabIndexCounter(SharedMediaLayout r0) {
        return r0.tabIndexCounter;
    }

    /* renamed from: -$$Nest$fgettabsAnimationInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12852$$Nest$fgettabsAnimationInProgress(SharedMediaLayout r0) {
        return r0.tabsAnimationInProgress;
    }

    /* renamed from: -$$Nest$fgettopPanelLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DialogsActivityTopPanelLayout m12853$$Nest$fgettopPanelLayout(SharedMediaLayout r0) {
        return r0.topPanelLayout;
    }

    /* renamed from: -$$Nest$fgettopicId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m12854$$Nest$fgettopicId(SharedMediaLayout r2) {
        return r2.topicId;
    }

    /* renamed from: -$$Nest$fgetunpinItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m12855$$Nest$fgetunpinItem(SharedMediaLayout r0) {
        return r0.unpinItem;
    }

    /* renamed from: -$$Nest$fgetuserInfo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.UserFull m12856$$Nest$fgetuserInfo(SharedMediaLayout r0) {
        return r0.userInfo;
    }

    /* renamed from: -$$Nest$fgetvoiceAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SharedDocumentsAdapter m12857$$Nest$fgetvoiceAdapter(SharedMediaLayout r0) {
        return r0.voiceAdapter;
    }

    /* renamed from: -$$Nest$fputactionModeAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12858$$Nest$fputactionModeAnimation(SharedMediaLayout r0, AnimatorSet r1) {
        r0.actionModeAnimation = r1;
    }

    /* renamed from: -$$Nest$fputallowStoriesSingleColumn, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12859$$Nest$fputallowStoriesSingleColumn(SharedMediaLayout r0, boolean r1) {
        r0.allowStoriesSingleColumn = r1;
    }

    /* renamed from: -$$Nest$fputanimatingForward, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12860$$Nest$fputanimatingForward(SharedMediaLayout r0, boolean r1) {
        r0.animatingForward = r1;
    }

    /* renamed from: -$$Nest$fputcantDeleteMessagesCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12861$$Nest$fputcantDeleteMessagesCount(SharedMediaLayout r0, int r1) {
        r0.cantDeleteMessagesCount = r1;
    }

    /* renamed from: -$$Nest$fputchangeColumnsTab, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12862$$Nest$fputchangeColumnsTab(SharedMediaLayout r0, int r1) {
        r0.changeColumnsTab = r1;
    }

    /* renamed from: -$$Nest$fputchangeTypeAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12863$$Nest$fputchangeTypeAnimation(SharedMediaLayout r0, boolean r1) {
        r0.changeTypeAnimation = r1;
    }

    /* renamed from: -$$Nest$fputfloatingDateAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12864$$Nest$fputfloatingDateAnimation(SharedMediaLayout r0, AnimatorSet r1) {
        r0.floatingDateAnimation = r1;
    }

    /* renamed from: -$$Nest$fputignoreSearchCollapse, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12865$$Nest$fputignoreSearchCollapse(SharedMediaLayout r0, boolean r1) {
        r0.ignoreSearchCollapse = r1;
    }

    /* renamed from: -$$Nest$fputmaybeStartTracking, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12866$$Nest$fputmaybeStartTracking(SharedMediaLayout r0, boolean r1) {
        r0.maybeStartTracking = r1;
    }

    /* renamed from: -$$Nest$fputoptionsAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12867$$Nest$fputoptionsAlpha(SharedMediaLayout r0, float r1) {
        r0.optionsAlpha = r1;
    }

    /* renamed from: -$$Nest$fputphotoVideoChangeColumnsAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12868$$Nest$fputphotoVideoChangeColumnsAnimation(SharedMediaLayout r0, boolean r1) {
        r0.photoVideoChangeColumnsAnimation = r1;
    }

    /* renamed from: -$$Nest$fputphotoVideoChangeColumnsProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12869$$Nest$fputphotoVideoChangeColumnsProgress(SharedMediaLayout r0, float r1) {
        r0.photoVideoChangeColumnsProgress = r1;
    }

    /* renamed from: -$$Nest$fputscrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12870$$Nest$fputscrolling(SharedMediaLayout r0, boolean r1) {
        r0.scrolling = r1;
    }

    /* renamed from: -$$Nest$fputsearchAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12871$$Nest$fputsearchAlpha(SharedMediaLayout r0, float r1) {
        r0.searchAlpha = r1;
    }

    /* renamed from: -$$Nest$fputsearchItemState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12872$$Nest$fputsearchItemState(SharedMediaLayout r0, int r1) {
        r0.searchItemState = r1;
    }

    /* renamed from: -$$Nest$fputsearchWas, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12873$$Nest$fputsearchWas(SharedMediaLayout r0, boolean r1) {
        r0.searchWas = r1;
    }

    /* renamed from: -$$Nest$fputsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12874$$Nest$fputsearching(SharedMediaLayout r0, boolean r1) {
        r0.searching = r1;
    }

    /* renamed from: -$$Nest$fputsearchingReaction, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12875$$Nest$fputsearchingReaction(SharedMediaLayout r0, ReactionsLayoutInBubble.VisibleReaction r1) {
        r0.searchingReaction = r1;
    }

    /* renamed from: -$$Nest$fputshiftDp, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12876$$Nest$fputshiftDp(SharedMediaLayout r0, float r1) {
        r0.shiftDp = r1;
    }

    /* renamed from: -$$Nest$fputstartedTracking, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12877$$Nest$fputstartedTracking(SharedMediaLayout r0, boolean r1) {
        r0.startedTracking = r1;
    }

    /* renamed from: -$$Nest$fputstoriesColumnsCountSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12878$$Nest$fputstoriesColumnsCountSet(SharedMediaLayout r0, boolean r1) {
        r0.storiesColumnsCountSet = r1;
    }

    /* renamed from: -$$Nest$fputtabIndexCounter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12879$$Nest$fputtabIndexCounter(SharedMediaLayout r0, int r1) {
        r0.tabIndexCounter = r1;
    }

    /* renamed from: -$$Nest$fputtabsAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12880$$Nest$fputtabsAnimation(SharedMediaLayout r0, AnimatorSet r1) {
        r0.tabsAnimation = r1;
    }

    /* renamed from: -$$Nest$fputtabsAnimationInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12881$$Nest$fputtabsAnimationInProgress(SharedMediaLayout r0, boolean r1) {
        r0.tabsAnimationInProgress = r1;
    }

    /* renamed from: -$$Nest$maddStoryAlbumShareItemOptions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12882$$Nest$maddStoryAlbumShareItemOptions(SharedMediaLayout r0, ItemOptions r1, BaseFragment r2, long r3, int r5) {
        r0.addStoryAlbumShareItemOptions(r1, r2, r3, r5);
    }

    /* renamed from: -$$Nest$maddZoomInZoomOutItemOptions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12883$$Nest$maddZoomInZoomOutItemOptions(SharedMediaLayout r0, ItemOptions r1) {
        r0.addZoomInZoomOutItemOptions(r1);
    }

    /* renamed from: -$$Nest$manimateItemsEnter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12884$$Nest$manimateItemsEnter(SharedMediaLayout r0, RecyclerListView r1, int r2, SparseBooleanArray r3) {
        r0.animateItemsEnter(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mbuildItemOptionsForStoryAlbumActionBar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ItemOptions m12885$$Nest$mbuildItemOptionsForStoryAlbumActionBar(SharedMediaLayout r0, BaseFragment r1, View r2, long r3, int r5) {
        return r0.buildItemOptionsForStoryAlbumActionBar(r1, r2, r3, r5);
    }

    /* renamed from: -$$Nest$mchangeMediaFilterType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12886$$Nest$mchangeMediaFilterType(SharedMediaLayout r0) {
        r0.changeMediaFilterType();
    }

    /* renamed from: -$$Nest$mcheckCurrentTabValid, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12887$$Nest$mcheckCurrentTabValid(SharedMediaLayout r0) {
        r0.checkCurrentTabValid();
    }

    /* renamed from: -$$Nest$mcheckLoadMoreScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12888$$Nest$mcheckLoadMoreScroll(SharedMediaLayout r0, MediaPage r1, RecyclerListView r2, LinearLayoutManager r3) {
        r0.checkLoadMoreScroll(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mcheckStoriesTabsPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12889$$Nest$mcheckStoriesTabsPosition(SharedMediaLayout r0) {
        r0.checkStoriesTabsPosition();
    }

    /* renamed from: -$$Nest$mcheckUi_topPanelLayoutY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12890$$Nest$mcheckUi_topPanelLayoutY(SharedMediaLayout r0) {
        r0.checkUi_topPanelLayoutY();
    }

    /* renamed from: -$$Nest$mfindPeriodAndJumpToDate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12891$$Nest$mfindPeriodAndJumpToDate(SharedMediaLayout r0, int r1, RecyclerListView r2, boolean r3) {
        r0.findPeriodAndJumpToDate(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mfixLayoutInternal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12892$$Nest$mfixLayoutInternal(SharedMediaLayout r0, int r1) {
        r0.fixLayoutInternal(r1);
    }

    /* renamed from: -$$Nest$mgetMediaPage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MediaPage m12893$$Nest$mgetMediaPage(SharedMediaLayout r0, int r1) {
        return r0.getMediaPage(r1);
    }

    /* renamed from: -$$Nest$mgetPagePaddingBottom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12894$$Nest$mgetPagePaddingBottom(SharedMediaLayout r0, boolean r1) {
        return r0.getPagePaddingBottom(r1);
    }

    /* renamed from: -$$Nest$mgetPagePaddingTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12895$$Nest$mgetPagePaddingTop(SharedMediaLayout r0, int r1) {
        return r0.getPagePaddingTop(r1);
    }

    /* renamed from: -$$Nest$mgetStoriesController, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoriesController m12896$$Nest$mgetStoriesController(SharedMediaLayout r0) {
        return r0.getStoriesController();
    }

    /* renamed from: -$$Nest$mgetThemedColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12897$$Nest$mgetThemedColor(SharedMediaLayout r0, int r1) {
        return r0.getThemedColor(r1);
    }

    /* renamed from: -$$Nest$mhideFloatingDateView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12898$$Nest$mhideFloatingDateView(SharedMediaLayout r0, boolean r1) {
        r0.hideFloatingDateView(r1);
    }

    /* renamed from: -$$Nest$mjumpToDate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12899$$Nest$mjumpToDate(SharedMediaLayout r0, int r1, int r2, int r3, boolean r4) {
        r0.jumpToDate(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$mnotifyDataSetChangedSafely, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12900$$Nest$mnotifyDataSetChangedSafely(SharedMediaLayout r0, RecyclerView.Adapter r1) {
        r0.notifyDataSetChangedSafely(r1);
    }

    /* renamed from: -$$Nest$monItemLongClick, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12901$$Nest$monItemLongClick(SharedMediaLayout r0, MessageObject r1, View r2, int r3) {
        return r0.onItemLongClick(r1, r2, r3);
    }

    /* renamed from: -$$Nest$monStoryAlbumCreate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12902$$Nest$monStoryAlbumCreate(SharedMediaLayout r0, StoriesController.StoryAlbum r1) {
        r0.onStoryAlbumCreate(r1);
    }

    /* renamed from: -$$Nest$mopenStoryTabIdPage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12903$$Nest$mopenStoryTabIdPage(SharedMediaLayout r0, int r1, boolean r2) {
        r0.openStoryTabIdPage(r1, r2);
    }

    /* renamed from: -$$Nest$mopenUrl, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12904$$Nest$mopenUrl(SharedMediaLayout r0, String r1) {
        r0.openUrl(r1);
    }

    /* renamed from: -$$Nest$mopenWebView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12905$$Nest$mopenWebView(SharedMediaLayout r0, TLRPC.WebPage r1, MessageObject r2) {
        r0.openWebView(r1, r2);
    }

    /* renamed from: -$$Nest$mpostNotifyDataSetChangedSafely, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12906$$Nest$mpostNotifyDataSetChangedSafely(SharedMediaLayout r0, RecyclerView.Adapter r1) {
        return r0.postNotifyDataSetChangedSafely(r1);
    }

    /* renamed from: -$$Nest$msaveScrollPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12907$$Nest$msaveScrollPosition(SharedMediaLayout r0) {
        r0.saveScrollPosition();
    }

    /* renamed from: -$$Nest$mscrollToTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12908$$Nest$mscrollToTop(SharedMediaLayout r0) {
        r0.scrollToTop();
    }

    /* renamed from: -$$Nest$mselectTabWithId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12909$$Nest$mselectTabWithId(SharedMediaLayout r0, int r1, float r2) {
        r0.selectTabWithId(r1, r2);
    }

    /* renamed from: -$$Nest$mshowFloatingDateView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12910$$Nest$mshowFloatingDateView(SharedMediaLayout r0) {
        r0.showFloatingDateView();
    }

    /* renamed from: -$$Nest$mstartStopVisibleGifs, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12911$$Nest$mstartStopVisibleGifs(SharedMediaLayout r0) {
        r0.startStopVisibleGifs();
    }

    /* renamed from: -$$Nest$mstoryAlbums_getByAlbumId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoryAlbumData m12912$$Nest$mstoryAlbums_getByAlbumId(SharedMediaLayout r0, int r1) {
        return r0.storyAlbums_getByAlbumId(r1);
    }

    /* renamed from: -$$Nest$mstoryAlbums_getByTabType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoryAlbumData m12913$$Nest$mstoryAlbums_getByTabType(SharedMediaLayout r0, int r1) {
        return r0.storyAlbums_getByTabType(r1);
    }

    /* renamed from: -$$Nest$mstoryAlbums_getStoriesAdapterByTabType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoriesAdapter m12914$$Nest$mstoryAlbums_getStoriesAdapterByTabType(SharedMediaLayout r0, int r1) {
        return r0.storyAlbums_getStoriesAdapterByTabType(r1);
    }

    /* renamed from: -$$Nest$mstoryAlbums_getStoriesSupportingAdapterByTabType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoriesAdapter m12915$$Nest$mstoryAlbums_getStoriesSupportingAdapterByTabType(SharedMediaLayout r0, int r1) {
        return r0.storyAlbums_getStoriesSupportingAdapterByTabType(r1);
    }

    /* renamed from: -$$Nest$mstoryAlbums_getTabTypeByStoriesAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12916$$Nest$mstoryAlbums_getTabTypeByStoriesAdapter(SharedMediaLayout r0, RecyclerView.Adapter r1) {
        return r0.storyAlbums_getTabTypeByStoriesAdapter(r1);
    }

    /* renamed from: -$$Nest$mstoryAlbums_getTabTypeByStoriesSupportingAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12917$$Nest$mstoryAlbums_getTabTypeByStoriesSupportingAdapter(SharedMediaLayout r0, RecyclerView.Adapter r1) {
        return r0.storyAlbums_getTabTypeByStoriesSupportingAdapter(r1);
    }

    /* renamed from: -$$Nest$mswitchToCurrentSelectedMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12918$$Nest$mswitchToCurrentSelectedMode(SharedMediaLayout r0, boolean r1) {
        r0.switchToCurrentSelectedMode(r1);
    }

    /* renamed from: -$$Nest$mupdateOptionsSearch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12919$$Nest$mupdateOptionsSearch(SharedMediaLayout r0) {
        r0.updateOptionsSearch();
    }

    /* renamed from: -$$Nest$mupdateOptionsSearch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12920$$Nest$mupdateOptionsSearch(SharedMediaLayout r0, boolean r1) {
        r0.updateOptionsSearch(r1);
    }

    /* renamed from: -$$Nest$smgetMessageId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12921$$Nest$smgetMessageId(View r0) {
        return getMessageId(r0);
    }

    /* renamed from: -$$Nest$smgetStoryAlbumType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12922$$Nest$smgetStoryAlbumType(int r0) {
        return getStoryAlbumType(r0);
    }

    /* renamed from: -$$Nest$smshowFastScrollHint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12923$$Nest$smshowFastScrollHint(MediaPage r0, SharedMediaData[] r1, boolean r2) {
        showFastScrollHint(r0, r1, r2);
    }

    static {
        supportedFastScrollTypes = new int[]{0, 1, 2, 4};
        interpolator = new SharedMediaLayout$$ExternalSyntheticLambda6();
    }

    public SharedMediaLayout(Context r17, long r18, SharedMediaPreloader r20, int r21, ArrayList<Integer> r22, TLRPC.ChatFull r23, TLRPC.UserFull r24, int r25, int r26, BaseFragment r27, Delegate r28, int r29, Theme.ResourcesProvider r30) {
        this(r17, r18, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, null);
    }

    public SharedMediaLayout(Context r39, long r40, SharedMediaPreloader r42, int r43, ArrayList<Integer> r44, TLRPC.ChatFull r45, TLRPC.UserFull r46, int r47, int r48, BaseFragment r49, Delegate r50, int r51, Theme.ResourcesProvider r52, BlurredBackgroundDrawableViewFactory r53) {
        TLRPC.ChatFull r6 = r45;
        super(r39);
        this.rect = new Rect();
        this.mediaPages = new MediaPage[2];
        this.cellCache = new ArrayList(10);
        this.cache = new ArrayList(10);
        this.audioCellCache = new ArrayList(10);
        this.audioCache = new ArrayList(10);
        this.hideFloatingDateRunnable = new SharedMediaLayout$$ExternalSyntheticLambda7(this);
        this.actionModeViews = new ArrayList();
        this.backgroundPaint = new Paint();
        this.selectedFiles = new SparseArray[]{new SparseArray(), new SparseArray()};
        this.allowStoriesSingleColumn = false;
        this.storiesColumnsCountSet = false;
        this.mediaColumnsCount = new int[]{3, 3};
        this.provider = new AnonymousClass1(this);
        this.shiftDp = -5.0f;
        this.sharedMediaData = new SharedMediaData[9];
        this.notificationsLocker = new AnimationNotificationsLocker();
        this.messageAlphaEnter = new SparseArray();
        this.firstTab = -1;
        this.lastMainTabId = -2;
        this.sharedLinkCellDelegate = new AnonymousClass49(this);
        this.subTabsVisibilityFactor = 0.0f;
        this.storyAlbumsById = new HashMap();
        this.storyAlbumsByTabType = new HashMap();
        BlurredBackgroundSourceColor r5 = new BlurredBackgroundSourceColor();
        this.iBlur3SourceColor = r5;
        r5.setColor(getThemedColor(Theme.key_windowBackgroundWhite));
        if (r53 != null) goto L6;
        BlurredBackgroundDrawableViewFactory r14 = new BlurredBackgroundDrawableViewFactory(r5);
    L7:
        this.viewType = r51;
        this.resourcesProvider = r52;
        FlickerLoadingView r54 = new FlickerLoadingView(r39);
        this.globalGradientView = r54;
        r54.setIsSingleCell(true);
        TLRPC.User r55 = r49.getMessagesController().getUser(Long.valueOf(r40));
        this.sharedMediaPreloader = r42;
        this.delegate = r50;
        int[] r10 = r42.getLastMediaCount();
        long r12 = SharedMediaPreloader.m12995$$Nest$fgettopicId(this.sharedMediaPreloader);
        this.topicId = r12;
        int r19 = r10[0];
        int r20 = r10[1];
        int r21 = r10[2];
        int r22 = r10[3];
        int r23 = r10[4];
        int r24 = r10[5];
        if (r12 != 0) goto L10;
        int r25 = r43;
    L11:
        this.hasMedia = new int[]{r19, r20, r21, r22, r23, r24, r25, r10[7], r10[8]};
        if (r46 == null) goto L14;
        TLRPC.ProfileTab r122 = r46.main_tab;
    L18:
        if (r47 == 14) goto L118;
        if (r47 == 10) goto L118;
        if (r47 == 11) goto L118;
        if (r47 == 6) goto L118;
        if (r55 != null) goto L26;
    L32:
        if (r46 == null) goto L39;
        TL_bots.BotInfo r56 = r46.bot_info;
        if (r56 == null) goto L39;
        if (r56.has_preview_medias == false) goto L39;
        this.initialTab = 8;
    L119:
        onTabProgress(r47);
        this.info = r6;
        this.userInfo = r46;
        if (r6 == null) goto L122;
        this.mergeDialogId = -r6.migrated_from_chat_id;
    L122:
        this.dialog_id = r40;
        int r0 = 0;
    L123:
        SharedMediaData[] r57 = this.sharedMediaData;
        if (r0 >= r57.length) goto L137;
        r57[r0] = new SharedMediaData();
        int[] r58 = this.sharedMediaData[r0].max_id;
        BlurredBackgroundDrawableViewFactory r123 = r14;
        if (DialogObject.isEncryptedDialog(this.dialog_id) == false) goto L128;
        int r13 = Integer.MIN_VALUE;
    L129:
        r58[0] = r13;
        this.sharedMediaData[r0].max_id[1] = Integer.MAX_VALUE;
        fillMediaData(r0);
        if (this.mergeDialogId == 0) goto L136;
        if (this.info == null) goto L136;
        if (this.sharedMediaData[r0].messagesDict[1].size() != 0) goto L136;
        SharedMediaData r59 = this.sharedMediaData[r0];
        r59.max_id[1] = this.info.migrated_from_max_id;
        r59.endReached[1] = false;
    L136:
        r0 = r0 + 1;
        r14 = r123;
        goto L123
    L128:
        r13 = Integer.MAX_VALUE;
        goto L129
    L137:
        BlurredBackgroundDrawableViewFactory r124 = r14;
        this.profileActivity = r49;
        this.actionBar = r49.getActionBar();
        int[] r02 = this.mediaColumnsCount;
        if (overrideColumnsCount() > 0) goto L140;
        int r510 = SharedConfig.mediaColumnsCount;
    L141:
        r02[0] = r510;
        int[] r03 = this.mediaColumnsCount;
        if (overrideColumnsCount() > 0) goto L144;
        int r511 = SharedConfig.storiesColumnsCount;
    L145:
        r03[1] = r511;
        this.observersGroup = this.profileActivity.getNotificationCenter().createObserversGroup(this).add(NotificationCenter.mediaDidLoad).add(NotificationCenter.messagesDeleted).add(NotificationCenter.didReceiveNewMessages).add(NotificationCenter.messageReceivedByServer).add(NotificationCenter.messagePlayingDidReset).add(NotificationCenter.messagePlayingPlayStateChanged).add(NotificationCenter.messagePlayingDidStart).add(NotificationCenter.storiesListUpdated).add(NotificationCenter.storiesUpdated).add(NotificationCenter.channelRecommendationsLoaded).add(NotificationCenter.savedMessagesDialogsUpdate).add(NotificationCenter.dialogsNeedReload).add(NotificationCenter.starUserGiftsLoaded).add(NotificationCenter.updatedChatRanks).add(NotificationCenter.didUpdatePollResults);
        int r04 = 0;
    L146:
        if (r04 >= 10) goto L151;
        if (r47 != 4) goto L150;
        AnonymousClass2 r512 = new AnonymousClass2(this, r39);
        r512.initStreamingIcons();
        this.audioCellCache.add(r512);
    L150:
        r04 = r04 + 1;
        goto L146
    L151:
        this.maximumVelocity = ViewConfiguration.get(r39).getScaledMaximumFlingVelocity();
        this.searching = false;
        this.searchingReaction = null;
        SearchTagsList r513 = this.searchTagsList;
        if (r513 == null) goto L154;
        r513.show(false);
    L154:
        this.searchWas = false;
        Drawable r05 = r39.getResources().getDrawable(R.drawable.photos_header_shadow);
        this.pinnedHeaderShadowDrawable = r05;
        r05.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_windowBackgroundGrayShadow), PorterDuff.Mode.MULTIPLY));
        ScrollSlidingTextTabStripInner r06 = this.scrollSlidingTextTabStrip;
        if (r06 == null) goto L157;
        int r7 = r06.getCurrentTabId();
    L158:
        this.scrollSlidingTextTabStrip = createScrollingTextTabStrip(r39);
        int r07 = 1;
    L159:
        if (r07 < 0) goto L161;
        this.selectedFiles[r07].clear();
        r07 = r07 - 1;
        goto L159
    L161:
        this.cantDeleteMessagesCount = 0;
        this.actionModeViews.clear();
        SavedDialogsAdapter r08 = this.savedDialogsAdapter;
        if (r08 == null) goto L165;
        r08.unselectAll();
    L165:
        if (addActionButtons() == false) goto L186;
        ActionBarMenu r09 = this.actionBar.createMenu();
        r09.addOnLayoutChangeListener(new AnonymousClass3(this));
        if (this.dialog_id == this.profileActivity.getUserConfig().getClientUserId()) goto L169;
    L173:
        ActionBarMenuItem r010 = r09.addItem(0, 0).setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass4(this));
        this.searchItem = r010;
        r010.setTranslationY(AndroidUtilities.dp(10.0f));
        ActionBarMenuItem r011 = this.searchItem;
        SearchTagsList r514 = this.searchTagsList;
        if (r514 != null) goto L176;
    L180:
        int r515 = R.string.Search;
    L181:
        r011.setSearchFieldHint(LocaleController.getString(r515));
        this.searchItem.setContentDescription(LocaleController.getString("Search", R.string.Search));
        ActionBarMenuItem r012 = this.searchItem;
        if (isStoriesView() == false) goto L184;
        int r516 = 8;
    L185:
        r012.setVisibility(r516);
        goto L186
    L184:
        r516 = 4;
        goto L185
    L176:
        if (r514.hasFilters() == false) goto L180;
        if (getSelectedTab() != 11) goto L180;
        r515 = R.string.SavedTagSearchHint;
        goto L181
    L169:
        if ((this.profileActivity instanceof MediaActivity) == false) goto L173;
        if (canShowSearchItem() == false) goto L173;
        this.searchItemIcon = r09.addItem(11, R.drawable.outline_header_search);
    L186:
        ImageView r013 = new ImageView(r39);
        this.photoVideoOptionsItem = r013;
        r013.setContentDescription(LocaleController.getString("AccDescrMoreOptions", R.string.AccDescrMoreOptions));
        this.photoVideoOptionsItem.setTranslationY(AndroidUtilities.dp(10.0f));
        this.photoVideoOptionsItem.setVisibility(4);
        if (isArchivedOnlyStoriesView() == true) goto L192;
        if (isSearchingStories() == true) goto L192;
        this.actionBar.addView(this.photoVideoOptionsItem, LayoutHelper.createFrame(48, 56, 85));
        RLottieImageView r014 = new RLottieImageView(r39);
        this.optionsSearchImageView = r014;
        r014.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        this.optionsSearchImageView.setAnimation(R.raw.options_to_search, 24, 24);
        this.optionsSearchImageView.getAnimatedDrawable().multiplySpeed(2.0f);
        this.optionsSearchImageView.getAnimatedDrawable().setPlayInDirectionOfCustomEndFrame(true);
        this.optionsSearchImageView.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_actionBarActionModeDefaultIcon), PorterDuff.Mode.SRC_IN));
        this.optionsSearchImageView.setVisibility(8);
        this.actionBar.addView(this.optionsSearchImageView, LayoutHelper.createFrame(48, 56, 85));
    L192:
        this.photoVideoOptionsItem.setOnClickListener(new AnonymousClass5(this, r40, r52, r39));
        ActionBarMenuItem r015 = this.searchItem;
        if (r015 == null) goto L195;
        EditTextBoldCursor r016 = r015.getSearchField();
        int r2 = Theme.key_windowBackgroundWhiteBlackText;
        r016.setTextColor(getThemedColor(r2));
        r016.setHintTextColor(getThemedColor(Theme.key_player_time));
        r016.setCursorColor(getThemedColor(r2));
    L195:
        this.searchItemState = 0;
        BaseFragment r017 = this.profileActivity;
        if (r017 != null) goto L198;
    L200:
        SizeNotifierFrameLayout r15 = null;
    L201:
        BlurredLinearLayout r018 = new BlurredLinearLayout(r39, r15);
        this.actionModeLayout = r018;
        r018.setBackgroundColor(getThemedColor(Theme.key_windowBackgroundGray));
        this.actionModeLayout.setAlpha(0.0f);
        this.actionModeLayout.setClickable(true);
        this.actionModeLayout.setVisibility(4);
        ImageView r019 = new ImageView(r39);
        this.closeButton = r019;
        r019.setScaleType(ImageView.ScaleType.CENTER);
        ImageView r020 = this.closeButton;
        BackDrawable r3 = new BackDrawable(true);
        this.backDrawable = r3;
        r020.setImageDrawable(r3);
        BackDrawable r021 = this.backDrawable;
        int r32 = Theme.key_actionBarActionModeDefaultIcon;
        r021.setColor(getThemedColor(r32));
        ImageView r022 = this.closeButton;
        int r4 = Theme.key_actionBarActionModeDefaultSelector;
        r022.setBackground(Theme.createSelectorDrawable(getThemedColor(r4), 1));
        this.closeButton.setContentDescription(LocaleController.getString("Close", R.string.Close));
        this.actionModeLayout.addView(this.closeButton, new LinearLayout.LayoutParams(AndroidUtilities.dp(54.0f), -1));
        this.actionModeViews.add(this.closeButton);
        this.closeButton.setOnClickListener(new SharedMediaLayout$$ExternalSyntheticLambda12(this));
        NumberTextView r023 = new NumberTextView(r39);
        this.selectedMessagesCountTextView = r023;
        r023.setTextSize(18);
        this.selectedMessagesCountTextView.setTypeface(AndroidUtilities.bold());
        this.selectedMessagesCountTextView.setTextColor(getThemedColor(r32));
        this.actionModeLayout.addView(this.selectedMessagesCountTextView, LayoutHelper.createLinear(0, -1, 1.0f, 18, 0, 0, 0));
        this.actionModeViews.add(this.selectedMessagesCountTextView);
        SizeNotifierFrameLayout r102 = r15;
        if (DialogObject.isEncryptedDialog(this.dialog_id) == false) goto L204;
        int r142 = r32;
        int r132 = r4;
    L209:
        ActionBarMenuItem r024 = new ActionBarMenuItem(r39, null, getThemedColor(r132), getThemedColor(r142), false);
        this.deleteItem = r024;
        r024.setIcon(R.drawable.msg_delete);
        this.deleteItem.setContentDescription(LocaleController.getString("Delete", R.string.Delete));
        this.deleteItem.setDuplicateParentStateEnabled(false);
        this.actionModeLayout.addView(this.deleteItem, new LinearLayout.LayoutParams(AndroidUtilities.dp(54.0f), -1));
        this.actionModeViews.add(this.deleteItem);
        this.deleteItem.setOnClickListener(new SharedMediaLayout$$ExternalSyntheticLambda18(this));
        this.photoVideoAdapter = new AnonymousClass6(this, r39);
        this.animationSupportingPhotoVideoAdapter = new SharedPhotoVideoAdapter(this, r39);
        this.documentsAdapter = new SharedDocumentsAdapter(this, r39, 1);
        this.voiceAdapter = new SharedDocumentsAdapter(this, r39, 2);
        this.audioAdapter = new SharedDocumentsAdapter(this, r39, 4);
        this.pollAdapter = new PollAdapter(this, r39, this.profileActivity.getCurrentAccount(), this.profileActivity.getResourceProvider());
        this.gifAdapter = new GifAdapter(this, r39);
        this.documentsSearchAdapter = new MediaSearchAdapter(this, r39, 1);
        this.audioSearchAdapter = new MediaSearchAdapter(this, r39, 4);
        this.linksSearchAdapter = new MediaSearchAdapter(this, r39, 3);
        this.groupUsersSearchAdapter = new GroupUsersSearchAdapter(this, r39);
        this.commonGroupsAdapter = new CommonGroupsAdapter(this, r39);
        this.channelRecommendationsAdapter = new ChannelRecommendationsAdapter(this, r39);
        this.savedDialogsAdapter = new SavedDialogsAdapter(this, r39);
        this.savedMessagesSearchAdapter = new SavedMessagesSearchAdapter(this, r39);
        if (isStoriesView() == false) goto L212;
    L216:
        ChatUsersAdapter r025 = new ChatUsersAdapter(this, r39);
        this.chatUsersAdapter = r025;
        if (this.topicId != 0) goto L223;
        ChatUsersAdapter.m12947$$Nest$fputsortedUsers(r025, r44);
        ChatUsersAdapter r026 = this.chatUsersAdapter;
        if (r7 == 7) goto L222;
        r6 = null;
    L222:
        ChatUsersAdapter.m12946$$Nest$fputchatInfo(r026, r6);
    L223:
        this.storiesAdapter = new AnonymousClass9(this, r39, false);
        this.storiesReorder = new ItemTouchHelper(new AnonymousClass10(this));
        this.animationSupportingStoriesAdapter = new StoriesAdapter(this, r39, false);
        this.archivedStoriesAdapter = new AnonymousClass11(this, r39, true);
        this.animationSupportingArchivedStoriesAdapter = new StoriesAdapter(this, r39, true);
        this.linksAdapter = new SharedLinksAdapter(this, r39);
        boolean r027 = isBot();
        BaseFragment r26 = this.profileActivity;
        float r133 = 48.0f;
        if (r027 == false) goto L228;
        SharedMediaLayout r1 = this;
        Context r33 = r39;
        r1.botPreviewsContainer = new AnonymousClass12(this, r39, r26, this.dialog_id);
    L226:
        Theme.ResourcesProvider r410 = r52;
    L247:
        r1.setWillNotDraw(false);
        int r028 = 0;
        int r27 = -1;
        int r72 = 0;
    L248:
        MediaPage[] r517 = r1.mediaPages;
        if (r72 >= r517.length) goto L275;
        if (r72 != 0) goto L261;
        MediaPage r518 = r517[r72];
        if (r518 == null) goto L261;
        if (MediaPage.m12960$$Nest$fgetlayoutManager(r518) == null) goto L261;
        r27 = MediaPage.m12960$$Nest$fgetlayoutManager(r1.mediaPages[r72]).findFirstVisibleItemPosition();
        if (r27 == (MediaPage.m12960$$Nest$fgetlayoutManager(r1.mediaPages[r72]).getItemCount() - 1)) goto L262;
        RecyclerListView.Holder r519 = (RecyclerListView.Holder) MediaPage.m12961$$Nest$fgetlistView(r1.mediaPages[r72]).findViewHolderForAdapterPosition(r27);
        if (r519 == null) goto L260;
        r028 = r519.itemView.getTop();
        goto L261
    L260:
        r27 = -1;
        goto L261
    L262:
        int r103 = r028;
        int r11 = -1;
    L263:
        AnonymousClass16 r28 = new AnonymousClass16(r1, r33);
        r1.addView(r28, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, r1.mediaPageTopMargin(), 0.0f, 0.0f));
        if (r72 != 1) goto L266;
        r28.setTranslationX(AndroidUtilities.displaySize.x);
    L266:
        r1.mediaPages[r72] = r28;
        AnonymousClass17 r143 = new AnonymousClass17(r1, r33, 100, r28);
        MediaPage.m12972$$Nest$fputlayoutManager(r28, r143);
        r143.setSpanSizeLookup(new AnonymousClass18(r1, r28));
        MediaPage.m12971$$Nest$fputitemAnimator(r1.mediaPages[r72], new DefaultItemAnimator());
        MediaPage.m12959$$Nest$fgetitemAnimator(r1.mediaPages[r72]).setDurations(280);
        MediaPage.m12959$$Nest$fgetitemAnimator(r1.mediaPages[r72]).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        MediaPage.m12959$$Nest$fgetitemAnimator(r1.mediaPages[r72]).setSupportsChangeAnimations(false);
        MediaPage.m12973$$Nest$fputlistView(r1.mediaPages[r72], new AnonymousClass19(r1, r33, r28, r143));
        MediaPage.m12961$$Nest$fgetlistView(r1.mediaPages[r72]).setFastScrollEnabled(1);
        MediaPage.m12961$$Nest$fgetlistView(r1.mediaPages[r72]).setScrollingTouchSlop(1);
        MediaPage.m12961$$Nest$fgetlistView(r1.mediaPages[r72]).setPinnedSectionOffsetY(-AndroidUtilities.dp(2.0f));
        MediaPage.m12961$$Nest$fgetlistView(r1.mediaPages[r72]).setPadding(0, AndroidUtilities.dp(54.0f), 0, 0);
        MediaPage.m12961$$Nest$fgetlistView(r1.mediaPages[r72]).setItemAnimator(null);
        MediaPage.m12961$$Nest$fgetlistView(r1.mediaPages[r72]).setClipToPadding(false);
        MediaPage.m12961$$Nest$fgetlistView(r1.mediaPages[r72]).setSectionsType(2);
        MediaPage.m12961$$Nest$fgetlistView(r1.mediaPages[r72]).setLayoutManager(r143);
        MediaPage r029 = r1.mediaPages[r72];
        float r422 = r133;
        r029.addView(MediaPage.m12961$$Nest$fgetlistView(r029), LayoutHelper.createFrame(-1, -1.0f));
        MediaPage.m12968$$Nest$fputanimationSupportingListView(r1.mediaPages[r72], new InternalListView(r33));
        InternalListView r030 = MediaPage.m12956$$Nest$fgetanimationSupportingListView(r1.mediaPages[r72]);
        MediaPage r520 = r1.mediaPages[r72];
        AnonymousClass20 r134 = new AnonymousClass20(r1, r33, 3);
        MediaPage.m12967$$Nest$fputanimationSupportingLayoutManager(r520, r134);
        r030.setLayoutManager(r134);
        MediaPage r031 = r1.mediaPages[r72];
        r031.addView(MediaPage.m12956$$Nest$fgetanimationSupportingListView(r031), LayoutHelper.createFrame(-1, -1.0f));
        MediaPage.m12956$$Nest$fgetanimationSupportingListView(r1.mediaPages[r72]).setVisibility(8);
        MediaPage.m12956$$Nest$fgetanimationSupportingListView(r1.mediaPages[r72]).addItemDecoration(new AnonymousClass21(r1, r28));
        MediaPage.m12961$$Nest$fgetlistView(r1.mediaPages[r72]).addItemDecoration(new AnonymousClass22(r1, r28));
        MediaPage.m12961$$Nest$fgetlistView(r1.mediaPages[r72]).setOnItemClickListener(new SharedMediaLayout$$ExternalSyntheticLambda20(r1, r28, r33, r40, r410));
        MediaPage.m12961$$Nest$fgetlistView(r1.mediaPages[r72]).setOnScrollListener(new AnonymousClass24(r1, r28, r143));
        MediaPage.m12961$$Nest$fgetlistView(r1.mediaPages[r72]).setOnItemLongClickListener(new AnonymousClass25(r1, r28));
        if (r72 == 0) goto L269;
    L271:
        MediaPage.m12966$$Nest$fputanimatingImageView(r1.mediaPages[r72], new AnonymousClass26(r1, r33, MediaPage.m12961$$Nest$fgetlistView(r1.mediaPages[r72])));
        MediaPage.m12954$$Nest$fgetanimatingImageView(r1.mediaPages[r72]).setVisibility(8);
        MediaPage.m12961$$Nest$fgetlistView(r1.mediaPages[r72]).addOverlayView(MediaPage.m12954$$Nest$fgetanimatingImageView(r1.mediaPages[r72]), LayoutHelper.createFrame(-1, -1.0f));
        MediaPage.m12974$$Nest$fputprogressView(r1.mediaPages[r72], new AnonymousClass27(r1, r33, r28));
        MediaPage.m12962$$Nest$fgetprogressView(r1.mediaPages[r72]).showDate(false);
        MediaPage.m12962$$Nest$fgetprogressView(r1.mediaPages[r72]).setClipToOutline(true);
        MediaPage.m12962$$Nest$fgetprogressView(r1.mediaPages[r72]).setOutlineProvider(new AnonymousClass28(r1));
        if (r72 == 0) goto L274;
        r1.mediaPages[r72].setVisibility(8);
    L274:
        MediaPage r032 = r1.mediaPages[r72];
        MediaPage.m12969$$Nest$fputemptyView(r032, new AnonymousClass29(r1, r33, MediaPage.m12962$$Nest$fgetprogressView(r032), 1));
        MediaPage.m12957$$Nest$fgetemptyView(r1.mediaPages[r72]).setVisibility(8, false);
        MediaPage.m12957$$Nest$fgetemptyView(r1.mediaPages[r72]).setAnimateLayoutChange(true);
        MediaPage r033 = r1.mediaPages[r72];
        r033.addView(MediaPage.m12957$$Nest$fgetemptyView(r033), LayoutHelper.createFrame(-1, -1.0f));
        MediaPage.m12957$$Nest$fgetemptyView(r1.mediaPages[r72]).setOnTouchListener(new SharedMediaLayout$$ExternalSyntheticLambda8());
        MediaPage.m12957$$Nest$fgetemptyView(r1.mediaPages[r72]).showProgress(true, false);
        MediaPage.m12957$$Nest$fgetemptyView(r1.mediaPages[r72]).title.setText(LocaleController.getString("NoResult", R.string.NoResult));
        MediaPage.m12957$$Nest$fgetemptyView(r1.mediaPages[r72]).button.setVisibility(8);
        MediaPage.m12957$$Nest$fgetemptyView(r1.mediaPages[r72]).subtitle.setText(LocaleController.getString(R.string.SearchEmptyViewFilteredSubtitle2));
        MediaPage.m12957$$Nest$fgetemptyView(r1.mediaPages[r72]).button.setVisibility(8);
        MediaPage.m12957$$Nest$fgetemptyView(r1.mediaPages[r72]).addView(MediaPage.m12962$$Nest$fgetprogressView(r1.mediaPages[r72]), LayoutHelper.createFrame(-1, -1.0f, 119, 12.0f, 60.0f, 12.0f, 12.0f));
        MediaPage.m12961$$Nest$fgetlistView(r1.mediaPages[r72]).setEmptyView(MediaPage.m12957$$Nest$fgetemptyView(r1.mediaPages[r72]));
        MediaPage.m12961$$Nest$fgetlistView(r1.mediaPages[r72]).setAnimateEmptyView(true, 0);
        MediaPage r034 = r1.mediaPages[r72];
        MediaPage.m12975$$Nest$fputscrollHelper(r034, new RecyclerAnimationScrollHelper(MediaPage.m12961$$Nest$fgetlistView(r034), MediaPage.m12960$$Nest$fgetlayoutManager(r1.mediaPages[r72])));
        r72 = r72 + 1;
        r133 = r422;
        r410 = r52;
        r028 = r103;
        r27 = r11;
        goto L248
    L269:
        if (r11 == (-1)) goto L271;
        r143.scrollToPositionWithOffset(r11, r103);
    L261:
        r103 = r028;
        r11 = r27;
        goto L263
    L275:
        float r423 = r133;
        ProfileStoriesCollectionTabs r035 = r1.storiesContainer;
        if (r035 == null) goto L278;
        r1.addView(r035, LayoutHelper.createFrame(-1, 42.0f, 48, 0.0f, 48.0f, 0.0f, 0.0f));
    L278:
        ChatActionCell r036 = new ChatActionCell(r33);
        r1.floatingDateView = r036;
        r036.setCustomDate((int) (System.currentTimeMillis() / 1000), false, false);
        r1.floatingDateView.setAlpha(0.0f);
        r1.floatingDateView.setOverrideColor(Theme.key_chat_mediaTimeBackground, Theme.key_chat_mediaTimeText);
        r1.floatingDateView.setTranslationY(-AndroidUtilities.dp(r423));
        r1.addView(r1.floatingDateView, LayoutHelper.createFrame(-2, -2.0f, 49, 0.0f, 52.0f, 0.0f, 0.0f));
        if (r1.customTabs() == true) goto L286;
        DialogsActivityTopPanelLayout r037 = new DialogsActivityTopPanelLayout(r33);
        r1.topPanelLayout = r037;
        r037.setPadding(AndroidUtilities.dp(11.0f), AndroidUtilities.dp(21.0f), AndroidUtilities.dp(11.0f), AndroidUtilities.dp(21.0f));
        BlurredBackgroundDrawable r038 = r124.create(r1.topPanelLayout, BlurredBackgroundProviderImpl.topPanel(r52));
        r038.setRadius(AndroidUtilities.dp(24.0f));
        r038.setPadding(AndroidUtilities.dp(7.0f));
        r1.topPanelLayout.setBlurredBackground(r038);
        FrameLayout r039 = new FrameLayout(r33);
        r1.fragmentContextViewWrapper = r039;
        r1.topPanelLayout.addView(r039);
        r1.topPanelLayout.setViewVisible(r1.fragmentContextViewWrapper, true, false);
        r1.topPanelLayout.setOnAnimatedHeightChangedListener(new SharedMediaLayout$$ExternalSyntheticLambda9(r1));
        AnonymousClass30 r040 = new AnonymousClass30(r1, r33, r49, this, false, r52);
        r1.fragmentContextView = r040;
        r1.fragmentContextViewWrapper.addView(r040);
        r1.topPanelLayout.setCallFragmentContextView(r1.fragmentContextView);
        r1.addView(r1.topPanelLayout, LayoutHelper.createFrame(-1, -2.0f, 48, 0.0f, 34.0f, 0.0f, 0.0f));
        r1.fragmentContextView.setDelegate(new SharedMediaLayout$$ExternalSyntheticLambda10(r1));
        BlurredBackgroundDrawable r041 = r124.create(r1.scrollSlidingTextTabStrip, BlurredBackgroundProviderImpl.topPanel(r52));
        r041.setRadius(AndroidUtilities.dp(18.0f));
        r041.setPadding(AndroidUtilities.dp(6.666f));
        r1.scrollSlidingTextTabStrip.setPadding(0, AndroidUtilities.dp(7.0f), 0, AndroidUtilities.dp(7.0f));
        r1.scrollSlidingTextTabStrip.setClipToPadding(false);
        r1.scrollSlidingTextTabStrip.setBackground(null);
        r1.scrollSlidingTextTabStrip.setBlurredBackground(r041);
        r1.scrollSlidingTextTabStrip.setOpen(false);
        r1.addView(r1.scrollSlidingTextTabStrip, LayoutHelper.createFrame(-2, 50.0f, 49, -2.0f, 0.0f, -2.0f, 0.0f));
        Context r29 = r1.getContext();
        BaseFragment r34 = r1.profileActivity;
        int r411 = r34.getCurrentAccount();
        if (r1.includeSavedDialogs() == false) goto L284;
        long r442 = 0;
    L283:
        SharedMediaLayout r402 = r1;
        AnonymousClass31 r042 = new AnonymousClass31(r402, r29, r34, r411, r442, r52);
        r1 = r402;
        r1.searchTagsList = r042;
        r042.setBlurredFactory(r124, BlurredBackgroundProviderImpl.topPanel(r52));
        r1.searchTagsList.setShown(0.0f);
        r1.addView(r1.searchTagsList, LayoutHelper.createFrame(-1, 38.0f, 51, 0.0f, 4.0f, 0.0f, 0.0f));
        r1.addView(r1.actionModeLayout, LayoutHelper.createFrame(-1, 48, 51));
        goto L286
    L284:
        r442 = r1.dialog_id;
    L286:
        r1.updateTabs(false);
        r1.switchToCurrentSelectedMode(false);
        if (r1.hasMedia[0] < 0) goto L289;
        r1.loadFastScrollData(false);
    L289:
        ProfileStoriesCollectionTabs r043 = r1.storiesContainer;
        if (r043 == null) goto L293;
        if (r48 <= 0) goto L293;
        r043.setInitialTabId(r48);
    L293:
        r1.iBlur3Capture = new SharedMediaLayout$$ExternalSyntheticLambda11(r1);
        return;
    L228:
        r33 = r39;
        r1 = this;
        if ((r26 instanceof ProfileActivity) == false) goto L226;
        TextView r044 = new TextView(r33);
        r1.saveItem = r044;
        r044.setText(LocaleController.getString(R.string.Save).toUpperCase());
        r1.saveItem.setTypeface(AndroidUtilities.bold());
        TextView r045 = r1.saveItem;
        int r210 = Theme.key_featuredStickers_addButton;
        r045.setTextColor(r1.getThemedColor(r210));
        r1.saveItem.setTextSize(1, 15.0f);
        r1.saveItem.setGravity(17);
        r1.saveItem.setBackground(Theme.createSelectorDrawable(Theme.multAlpha(r1.getThemedColor(r210), 0.15f), 3));
        r1.saveItem.setPadding(AndroidUtilities.dp(19.0f), 0, AndroidUtilities.dp(19.0f), 0);
        r1.actionBar.addView(r1.saveItem, LayoutHelper.createFrame(-2, 56, 85));
        r1.saveItem.setOnClickListener(new SharedMediaLayout$$ExternalSyntheticLambda19(r1));
        r1.saveItem.setVisibility(8);
        r1.saveItem.setAlpha(0.0f);
        r1.saveItem.setScaleX(0.4f);
        r1.saveItem.setScaleY(0.4f);
        BaseFragment r211 = r1.profileActivity;
        AnonymousClass13 r046 = new AnonymousClass13(r1, r211, r33, r211.getCurrentAccount(), ((ProfileActivity) r1.profileActivity).getDialogId(), r52);
        r410 = r52;
        r1.giftsContainer = r046;
        int r212 = AndroidUtilities.dp(48.0f);
        DialogsActivityTopPanelLayout r521 = r1.topPanelLayout;
        if (r521 == null) goto L233;
        int r522 = (int) r521.getAnimatedHeightWithPadding(0.0f);
    L234:
        r046.setPaddingTop(r212 + r522);
        if (r1.dialog_id != UserConfig.getInstance(r1.profileActivity.getCurrentAccount()).getClientUserId()) goto L237;
        boolean r047 = true;
    L238:
        StoriesController.StoriesCollections r213 = r1.getStoriesController().getStoryAlbumsList(r1.dialog_id, false);
        if (r213 != null) goto L241;
    L243:
        boolean r214 = false;
    L244:
        if (r047 == true) goto L246;
        if (r214 == false) goto L247;
    L246:
        r1.storiesContainer = new AnonymousClass15(r1, r33, r102, r1.getStoriesController().getStoryAlbumsList(r1.dialog_id), new AnonymousClass14(r1, r33, r49, r410));
        goto L247
    L241:
        if (r213.collections.isEmpty() == true) goto L243;
        r214 = true;
        goto L244
    L237:
        r047 = false;
        goto L238
    L233:
        r522 = 0;
        goto L234
    L212:
        if (includeSavedDialogs() == true) goto L216;
        if (this.topicId != 0) goto L216;
        Bundle r048 = new Bundle();
        r048.putLong("user_id", this.profileActivity.getUserConfig().getClientUserId());
        r048.putInt("chatMode", 3);
        AnonymousClass7 r215 = new AnonymousClass7(this, r39, this.profileActivity.getParentLayout(), r048);
        this.savedMessagesContainer = r215;
        r215.chatActivity.setSavedDialog(this.dialog_id);
        ChatActivityContainer r049 = this.savedMessagesContainer;
        r049.chatActivity.reversed = true;
        r049.setClipToOutline(true);
        this.savedMessagesContainer.setOutlineProvider(new AnonymousClass8(this));
        goto L216
    L204:
        if (isStoriesView() == true) goto L206;
        r132 = r4;
        r142 = r32;
        ActionBarMenuItem r050 = new ActionBarMenuItem(r39, null, getThemedColor(r4), getThemedColor(r32), false);
        this.gotoItem = r050;
        r050.setIcon(R.drawable.msg_message);
        this.gotoItem.setContentDescription(LocaleController.getString(R.string.AccDescrGoToMessage));
        this.gotoItem.setDuplicateParentStateEnabled(false);
        this.actionModeLayout.addView(this.gotoItem, new LinearLayout.LayoutParams(AndroidUtilities.dp(54.0f), -1));
        this.actionModeViews.add(this.gotoItem);
        this.gotoItem.setOnClickListener(new SharedMediaLayout$$ExternalSyntheticLambda13(this));
        ActionBarMenuItem r051 = new ActionBarMenuItem(r39, null, getThemedColor(r132), getThemedColor(r142), false);
        this.forwardItem = r051;
        r051.setIcon(R.drawable.msg_forward);
        this.forwardItem.setContentDescription(LocaleController.getString(R.string.Forward));
        this.forwardItem.setDuplicateParentStateEnabled(false);
        this.actionModeLayout.addView(this.forwardItem, new LinearLayout.LayoutParams(AndroidUtilities.dp(54.0f), -1));
        this.actionModeViews.add(this.forwardItem);
        this.forwardItem.setOnClickListener(new SharedMediaLayout$$ExternalSyntheticLambda14(this));
        this.forwardItem.setOnLongClickListener(new SharedMediaLayout$$ExternalSyntheticLambda15(this));
    L207:
        ActionBarMenuItem r052 = new ActionBarMenuItem(r39, null, getThemedColor(r132), getThemedColor(r142), false);
        this.pinItem = r052;
        r052.setIcon(R.drawable.msg_pin);
        this.pinItem.setContentDescription(LocaleController.getString(R.string.PinMessage));
        this.pinItem.setDuplicateParentStateEnabled(false);
        this.pinItem.setVisibility(8);
        this.actionModeLayout.addView(this.pinItem, new LinearLayout.LayoutParams(AndroidUtilities.dp(54.0f), -1));
        this.actionModeViews.add(this.pinItem);
        this.pinItem.setOnClickListener(new SharedMediaLayout$$ExternalSyntheticLambda16(this));
        ActionBarMenuItem r053 = new ActionBarMenuItem(r39, null, getThemedColor(r132), getThemedColor(r142), false);
        this.unpinItem = r053;
        r053.setIcon(R.drawable.msg_unpin);
        this.unpinItem.setContentDescription(LocaleController.getString(R.string.UnpinMessage));
        this.unpinItem.setDuplicateParentStateEnabled(false);
        this.unpinItem.setVisibility(8);
        this.actionModeLayout.addView(this.unpinItem, new LinearLayout.LayoutParams(AndroidUtilities.dp(54.0f), -1));
        this.actionModeViews.add(this.unpinItem);
        this.unpinItem.setOnClickListener(new SharedMediaLayout$$ExternalSyntheticLambda17(this));
        updateForwardItem();
        goto L209
    L206:
        r142 = r32;
        r132 = r4;
        goto L207
    L198:
        if ((r017.getFragmentView() instanceof SizeNotifierFrameLayout) == false) goto L200;
        r15 = (SizeNotifierFrameLayout) this.profileActivity.getFragmentView();
        goto L201
    L157:
        r7 = r47;
        goto L158
    L144:
        r511 = overrideColumnsCount();
        goto L145
    L140:
        r510 = overrideColumnsCount();
    L39:
        if ((r122 instanceof TLRPC.TL_profileTabPosts) == false) goto L50;
        if (r46 != null) goto L42;
    L43:
        if (r6 == null) goto L47;
        if (r6.stories_pinned_available == false) goto L47;
    L48:
        this.initialTab = 8;
    L47:
        if (isStoriesView() == false) goto L50;
    L42:
        if (r46.stories_pinned_available == true) goto L48;
    L50:
        if ((r122 instanceof TLRPC.TL_profileTabGifts) == false) goto L59;
        if (r46 != null) goto L53;
    L54:
        if (r6 == null) goto L59;
        if (r6.stargifts_count <= 0) goto L59;
    L57:
        this.initialTab = 14;
        goto L119
    L53:
        if (r46.stargifts_count > 0) goto L57;
    L59:
        if ((r122 instanceof TLRPC.TL_profileTabFiles) == false) goto L65;
        int r523 = this.hasMedia[1];
        if (r523 == (-1)) goto L63;
        if (r523 <= 0) goto L65;
    L63:
        this.initialTab = 1;
    L65:
        if ((r122 instanceof TLRPC.TL_profileTabGifs) == false) goto L71;
        int r524 = this.hasMedia[5];
        if (r524 == (-1)) goto L69;
        if (r524 <= 0) goto L71;
    L69:
        this.initialTab = 5;
    L71:
        if ((r122 instanceof TLRPC.TL_profileTabLinks) == false) goto L77;
        int r525 = this.hasMedia[3];
        if (r525 == (-1)) goto L75;
        if (r525 <= 0) goto L77;
    L75:
        this.initialTab = 3;
    L77:
        if ((r122 instanceof TLRPC.TL_profileTabMusic) == false) goto L83;
        int r526 = this.hasMedia[4];
        if (r526 == (-1)) goto L81;
        if (r526 <= 0) goto L83;
    L81:
        this.initialTab = 4;
    L83:
        if ((r122 instanceof TLRPC.TL_profileTabVoice) == false) goto L88;
        int r527 = this.hasMedia[2];
        if (r527 == (-1)) goto L87;
        if (r527 <= 0) goto L88;
    L87:
        this.initialTab = 2;
    L88:
        if (r46 != null) goto L90;
    L91:
        if (r6 == null) goto L95;
        if (r6.stories_pinned_available == false) goto L95;
    L96:
        this.initialTab = getInitialTab();
    L95:
        if (isStoriesView() == true) goto L96;
        if (r46 != null) goto L99;
    L100:
        if (r6 == null) goto L105;
        if (r6.stargifts_count <= 0) goto L105;
    L103:
        this.initialTab = 14;
    L105:
        if (r47 != (-1)) goto L107;
    L109:
        int r528 = 0;
    L110:
        int[] r104 = this.hasMedia;
        if (r528 >= r104.length) goto L119;
        int r105 = r104[r528];
        if (r105 == (-1)) goto L117;
        if (r105 > 0) goto L117;
        r528 = r528 + 1;
    L117:
        this.initialTab = r528;
        goto L119
    L107:
        if (this.topicId != 0) goto L109;
        this.initialTab = r47;
        goto L119
    L99:
        if (r46.stargifts_count > 0) goto L103;
    L90:
        if (r46.stories_pinned_available == true) goto L96;
    L26:
        if (r55.bot == false) goto L32;
        if (r55.bot_has_main_app == false) goto L32;
        if (r55.bot_can_edit == false) goto L32;
        this.initialTab = 13;
    L118:
        this.initialTab = r47;
        goto L119
    L14:
        if (r6 == null) goto L16;
        r122 = r6.main_tab;
        goto L18
    L16:
        r122 = null;
        goto L18
    L10:
        r25 = 0;
        goto L11
    L6:
        r14 = r53;
        goto L7
    }

    private CharSequence addPostText() {
        if (this.addPostButton != null) goto L9;
        this.addPostButton = new SpannableStringBuilder();
        boolean r0 = isBot();
        SpannableStringBuilder r1 = this.addPostButton;
        if (r0 == false) goto L7;
        r1.append(LocaleController.getString(R.string.ProfileBotPreviewEmptyButton));
        goto L9
    L7:
        r1.append("c");
        this.addPostButton.setSpan(new ColoredImageSpan(R.drawable.filled_premium_camera), 0, 1, 33);
        this.addPostButton.append("  ").append(LocaleController.getString(R.string.StoriesAddPost));
    L9:
        return this.addPostButton;
    }

    private void addStoryAlbumShareItemOptions(ItemOptions r3, BaseFragment r4, long r5, int r7) {
        if (r5 <= 0) goto L5;
        String r52 = UserObject.getPublicUsername(MessagesController.getInstance(r4.getCurrentAccount()).getUser(Long.valueOf(r5)));
    L6:
        if (r52 != null) goto L8;
        return;
    L8:
        r3.add(R.drawable.media_share, LocaleController.getString(R.string.StoriesAlbumMenuShareLink), new SharedMediaLayout$$ExternalSyntheticLambda62(this, "https://" + MessagesController.getInstance(r4.getCurrentAccount()).linkPrefix + "/" + r52 + "/a/" + r7, r4));
        return;
    L5:
        r52 = ChatObject.getPublicUsername(MessagesController.getInstance(r4.getCurrentAccount()).getChat(Long.valueOf(-r5)));
        goto L6
    }

    private void addZoomInZoomOutItemOptions(ItemOptions r6) {
        int r0 = r6.getItemsCount();
        r6.add(R.drawable.msg_zoomin, LocaleController.getString(R.string.MediaZoomIn), new SharedMediaLayout$$ExternalSyntheticLambda53(this, r1));
        r6.add(R.drawable.msg_zoomout, LocaleController.getString(R.string.MediaZoomOut), new SharedMediaLayout$$ExternalSyntheticLambda54(this, r1));
        View[] r1 = {r6.getItemAt(r0), r6.getItemAt(r0 + 1)};
        if (canZoomIn() == true) goto L6;
        r1[0].setEnabled(false);
        r1[0].setAlpha(0.5f);
    L6:
        if (canZoomOut() == true) goto L9;
        r1[1].setEnabled(false);
        r1[1].setAlpha(0.5f);
        return;
    }

    private void animateItemsEnter(RecyclerListView r10, int r11, SparseBooleanArray r12) {
        int r0 = r10.getChildCount();
        int r2 = 0;
        View r7 = null;
    L3:
        if (r2 >= r0) goto L8;
        View r1 = r10.getChildAt(r2);
        if ((r1 instanceof FlickerLoadingView) == false) goto L7;
        r7 = r1;
    L7:
        r2 = r2 + 1;
        goto L3
    L8:
        if (r7 == null) goto L10;
        r10.removeView(r7);
    L10:
        getViewTreeObserver().addOnPreDrawListener(new AnonymousClass45(this, r10, r12, r7, r11));
    }

    private void animateToMediaColumnsCount(int r9) {
        MediaPage r0 = getMediaPage(this.changeColumnsTab);
        this.pinchCenterPosition = -1;
        if (r0 == null) goto L26;
        MediaPage.m12961$$Nest$fgetlistView(r0).stopScroll();
        this.animateToColumnsCount = r9;
        MediaPage.m12956$$Nest$fgetanimationSupportingListView(r0).setVisibility(0);
        if (isAnyStoryPageType(this.changeColumnsTab) == false) goto L7;
        MediaPage.m12956$$Nest$fgetanimationSupportingListView(r0).setAdapter(storyAlbums_getStoriesSupportingAdapterByTabType(this.changeColumnsTab));
    L8:
        InternalListView r1 = MediaPage.m12956$$Nest$fgetanimationSupportingListView(r0);
        int r3 = MediaPage.m12956$$Nest$fgetanimationSupportingListView(r0).getPaddingLeft();
        InternalListView r4 = MediaPage.m12956$$Nest$fgetanimationSupportingListView(r0);
        int r5 = getPagePaddingTop(r0.selectedType);
        r4.hintPaddingTop = r5;
        int r42 = MediaPage.m12956$$Nest$fgetanimationSupportingListView(r0).getPaddingRight();
        InternalListView r6 = MediaPage.m12956$$Nest$fgetanimationSupportingListView(r0);
        int r7 = getPagePaddingBottom(isStoriesView());
        r6.hintPaddingBottom = r7;
        r1.setPadding(r3, r5, r42, r7);
        MediaPage.m12955$$Nest$fgetanimationSupportingLayoutManager(r0).setSpanCount(r9);
        MediaPage.m12956$$Nest$fgetanimationSupportingListView(r0).invalidateItemDecorations();
        int r12 = 0;
    L9:
        MediaPage[] r32 = this.mediaPages;
        if (r12 >= r32.length) goto L17;
        MediaPage r33 = r32[r12];
        if (r33 == null) goto L16;
        if (isTabZoomable(r33.selectedType) == false) goto L16;
        AndroidUtilities.updateVisibleRows(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r12]));
    L16:
        r12 = r12 + 1;
        goto L9
    L17:
        this.photoVideoChangeColumnsAnimation = true;
        if (this.changeColumnsTab != 0) goto L20;
        this.sharedMediaData[0].setListFrozen(true);
    L20:
        this.photoVideoChangeColumnsProgress = 0.0f;
        saveScrollPosition();
        ValueAnimator r13 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.notificationsLocker.lock();
        r13.addUpdateListener(new AnonymousClass37(this, r0));
        r13.addListener(new AnonymousClass38(this, isAnyStoryPageType(r0.selectedType) ? 1 : 0, r9));
        r13.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r13.setStartDelay(100);
        r13.setDuration(350);
        r13.start();
        return;
    L7:
        MediaPage.m12956$$Nest$fgetanimationSupportingListView(r0).setAdapter(this.animationSupportingPhotoVideoAdapter);
        goto L8
    }

    private ItemOptions buildItemOptionsForStoryAlbumActionBar(BaseFragment r9, View r10, long r11, int r13) {
        ItemOptions r6 = ItemOptions.makeOptions(r9, r10);
        r6.add(R.drawable.menu_add_stories, LocaleController.getString(R.string.StoriesAlbumMenuAddStories), new SharedMediaLayout$$ExternalSyntheticLambda59(this, r9, r11, r13, r6));
        addStoryAlbumShareItemOptions(r6, r9, r11, r13);
        r6.add(R.drawable.tabs_reorder, LocaleController.getString(R.string.StoriesAlbumMenuReorder), new SharedMediaLayout$$ExternalSyntheticLambda60(this, r13, r6));
        r6.add(R.drawable.msg_delete, LocaleController.getString(R.string.StoriesAlbumMenuDeleteAlbum), true, new SharedMediaLayout$$ExternalSyntheticLambda61(this, r9, r11, r13, r6));
        r6.addGap();
        addZoomInZoomOutItemOptions(r6);
        r6.setDismissWithButtons(false);
        return r6;
    }

    private void changeMediaFilterType() {
        MediaPage r1 = getMediaPage(0);
        if (r1 != null) goto L5;
    L14:
        int[] r12 = this.sharedMediaPreloader.getLastMediaCount();
        ArrayList<MessageObject> r3 = this.sharedMediaPreloader.getSharedMediaData()[0].messages;
        this.sharedMediaData[0].setTotalCount(1, 0);
        SharedMediaData r4 = this.sharedMediaData[0];
        int r5 = r4.filterType;
        if (r5 != 0) goto L17;
        r4.setTotalCount(0, r12[0]);
    L20:
        this.sharedMediaData[0].fastScrollDataLoaded = false;
        if (DialogObject.isEncryptedDialog(this.dialog_id) == false) goto L23;
        int r13 = Integer.MIN_VALUE;
    L24:
        jumpToDate(0, r13, 0, true);
        loadFastScrollData(false);
        this.delegate.updateSelectedMediaTabText();
        boolean r14 = DialogObject.isEncryptedDialog(this.dialog_id);
        int r42 = 0;
    L26:
        if (r42 >= r3.size()) goto L38;
        MessageObject r52 = r3.get(r42);
        SharedMediaData r6 = this.sharedMediaData[0];
        int r7 = r6.filterType;
        if (r7 != 0) goto L30;
        r6.addMessage(r52, 0, false, r14);
    L37:
        r42 = r42 + 1;
        goto L26
    L30:
        if (r7 != 1) goto L35;
        if (r52.isPhoto() == false) goto L37;
        this.sharedMediaData[0].addMessage(r52, 0, false, r14);
        goto L37
    L35:
        if (r52.isPhoto() == true) goto L37;
        this.sharedMediaData[0].addMessage(r52, 0, false, r14);
        goto L37
    L38:
        return;
    L23:
        r13 = Integer.MAX_VALUE;
        goto L24
    L17:
        if (r5 != 1) goto L19;
        r4.setTotalCount(0, r12[6]);
        goto L20
    L19:
        r4.setTotalCount(0, r12[7]);
        goto L20
    L5:
        if (r1.getMeasuredHeight() <= 0) goto L14;
        if (r1.getMeasuredWidth() <= 0) goto L14;
        Bitmap r32 = Bitmap.createBitmap(r1.getMeasuredWidth(), r1.getMeasuredHeight(), Bitmap.Config.ARGB_8888);     // Catch: Exception -> L10
    L12:
        if (r32 == null) goto L14;
        this.changeTypeAnimation = true;
        MediaPage.m12961$$Nest$fgetlistView(r1).draw(new Canvas(r32));
        View r43 = new View(r1.getContext());
        r43.setBackground(new BitmapDrawable(r32));
        r1.addView(r43);
        r43.animate().alpha(0.0f).setDuration(200).setListener(new AnonymousClass32(this, r43, r1, r32)).start();
        MediaPage.m12961$$Nest$fgetlistView(r1).setAlpha(0.0f);
        MediaPage.m12961$$Nest$fgetlistView(r1).animate().alpha(1.0f).setUpdateListener(new SharedMediaLayout$$ExternalSyntheticLambda75(r1)).setDuration(200).start();
    L10:
        e = move-exception;
        FileLog.e(e);
        r32 = null;
        goto L12
    }

    private void checkCurrentTabValid() {
        int r0 = this.scrollSlidingTextTabStrip.getCurrentTabId();
        if (this.scrollSlidingTextTabStrip.hasTab(r0) == true) goto L6;
        int r02 = this.scrollSlidingTextTabStrip.getFirstTabId();
        this.scrollSlidingTextTabStrip.setInitialTabId(r02);
        this.mediaPages[0].selectedType = r02;
        switchToCurrentSelectedMode(false);
        return;
    }

    private void checkLoadMoreScroll(MediaPage r34, RecyclerListView r35, LinearLayoutManager r36) {
        if (this.photoVideoChangeColumnsAnimation == false) goto L5;
        return;
    L5:
        if (this.jumpToRunnable != null) goto L213;
        long r3 = System.currentTimeMillis();
        if (r35.getFastScroll() != null) goto L10;
    L14:
        r34.lastCheckScrollTime = r3;
        if (this.searching == false) goto L21;
        if (this.searchWas == false) goto L21;
        if (r34.selectedType == 11) goto L21;
        return;
    L21:
        if (r34.selectedType == 7) goto L215;
        int r32 = r36.findFirstVisibleItemPosition();
        if (r32 != (-1)) goto L26;
        int r9 = 0;
    L28:
        if (r35.getAdapter() != null) goto L30;
        int r10 = 0;
    L31:
        int r11 = r34.selectedType;
        if (r11 == 0) goto L36;
        if (r11 == 1) goto L36;
        if (r11 == 2) goto L36;
        if (r11 == 4) goto L36;
    L62:
        int r6 = r34.selectedType;
        if (r6 == 7) goto L216;
        boolean r62 = isAnyStoryPageType(r6);
        int r112 = r34.selectedType;
        if (r62 == false) goto L76;
        StoriesAdapter r1 = storyAlbums_getStoriesAdapterByTabType(r112);
        if (r1 == null) goto L192;
        StoriesController.StoriesList r2 = r1.storiesList;
        if (r2 != null) goto L72;
        return;
    L72:
        if ((r32 + r9) <= (r2.getLoadedCount() - this.mediaColumnsCount[1])) goto L194;
        r1.load(false);
        return;
    L194:
        return;
    L192:
        return;
    L76:
        if (r112 != 6) goto L88;
        if (r9 > 0) goto L79;
        return;
    L79:
        if (CommonGroupsAdapter.m12949$$Nest$fgetendReached(this.commonGroupsAdapter) == false) goto L81;
        return;
    L81:
        if (CommonGroupsAdapter.m12950$$Nest$fgetloading(this.commonGroupsAdapter) == false) goto L83;
        return;
    L83:
        if (CommonGroupsAdapter.m12948$$Nest$fgetchats(this.commonGroupsAdapter).isEmpty() == false) goto L85;
        return;
    L85:
        if ((r32 + r9) < (r10 - 5)) goto L199;
        CommonGroupsAdapter r12 = this.commonGroupsAdapter;
        CommonGroupsAdapter.m12951$$Nest$mgetChats(r12, ((TLRPC.Chat) CommonGroupsAdapter.m12948$$Nest$fgetchats(r12).get(CommonGroupsAdapter.m12948$$Nest$fgetchats(this.commonGroupsAdapter).size() - 1)).id, 100);
        return;
    L199:
        return;
    L88:
        if (r112 != 11) goto L103;
        int r22 = 0;
        int r63 = -1;
    L91:
        if (r22 >= MediaPage.m12961$$Nest$fgetlistView(r34).getChildCount()) goto L93;
        r63 = Math.max(MediaPage.m12961$$Nest$fgetlistView(r34).getChildAdapterPosition(MediaPage.m12961$$Nest$fgetlistView(r34).getChildAt(r22)), r63);
        r22 = r22 + 1;
        goto L91
    L93:
        RecyclerView.Adapter r13 = MediaPage.m12961$$Nest$fgetlistView(r34).getAdapter();
        SavedMessagesSearchAdapter r23 = this.savedMessagesSearchAdapter;
        if (r13 != r23) goto L100;
        if ((r63 + 1) < (r23.dialogs.size() + this.savedMessagesSearchAdapter.loadedMessages.size())) goto L200;
        this.savedMessagesSearchAdapter.loadMore();
        return;
    L200:
        return;
    L100:
        if ((r63 + 1) < this.profileActivity.getMessagesController().getSavedMessagesController().getLoadedCount()) goto L201;
        this.profileActivity.getMessagesController().getSavedMessagesController().loadDialogs(false);
        return;
    L201:
        return;
    L103:
        int r4 = 10;
        if (r112 != 10) goto L106;
        return;
    L106:
        if (r112 != 12) goto L108;
        return;
    L108:
        if (r112 != 13) goto L110;
        return;
    L110:
        if (r112 != 14) goto L112;
        return;
    L112:
        if (r112 != 0) goto L114;
        r4 = 3;
    L118:
        if (r112 != 15) goto L120;
        int r18 = 8;
    L122:
        if ((r9 + r32) <= (r10 - r4)) goto L124;
    L125:
        SharedMediaData[] r92 = this.sharedMediaData;
        SharedMediaData r102 = r92[r18];
        if (r102.loading == true) goto L154;
        if (r112 != 0) goto L134;
        int r5 = r92[0].filterType;
        if (r5 != 1) goto L131;
        int r25 = 6;
    L146:
        boolean[] r52 = r102.endReached;
        if (r52[0] == true) goto L150;
        r102.loading = true;
        this.profileActivity.getMediaDataController().loadMedia(this.dialog_id, 50, this.sharedMediaData[r18].max_id[0], 0, r25, this.topicId, 1, this.profileActivity.getClassGuid(), this.sharedMediaData[r18].requestIndex, null, null);
        goto L154
    L150:
        if (this.mergeDialogId == 0) goto L154;
        if (r52[1] == true) goto L154;
        r102.loading = true;
        this.profileActivity.getMediaDataController().loadMedia(this.mergeDialogId, 50, this.sharedMediaData[r18].max_id[1], 0, r25, this.topicId, 1, this.profileActivity.getClassGuid(), this.sharedMediaData[r18].requestIndex, null, null);
        goto L154
    L131:
        if (r5 != 2) goto L133;
        r25 = 7;
        goto L146
    L133:
        r25 = 0;
        goto L146
    L134:
        if (r112 != 1) goto L136;
        r25 = 1;
        goto L146
    L136:
        if (r112 != 2) goto L139;
        r25 = 2;
        goto L146
    L139:
        if (r112 != 4) goto L141;
        r25 = 4;
        goto L146
    L141:
        if (r112 != 5) goto L143;
        r25 = 5;
        goto L146
    L143:
        if (r112 != 15) goto L145;
        r25 = 8;
        goto L146
    L145:
        r25 = 3;
    L154:
        int r53 = SharedMediaData.m12992$$Nest$fgetstartOffset(this.sharedMediaData[r18]);
        if (r18 != 0) goto L158;
        r53 = this.photoVideoAdapter.getPositionForIndex(0);
    L158:
        if ((r32 - r53) >= (r4 + 1)) goto L167;
        SharedMediaData r42 = this.sharedMediaData[r18];
        if (r42.loading == true) goto L167;
        if (r42.startReached == true) goto L167;
        if (r42.loadingAfterFastScroll == true) goto L167;
        loadFromStart(r34.selectedType);
    L167:
        if (MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[0]) != r35) goto L206;
        int r14 = this.mediaPages[0].selectedType;
        if (r14 == 0) goto L172;
        if (r14 == 5) goto L172;
        return;
    L172:
        if (r32 == (-1)) goto L208;
        RecyclerView.ViewHolder r15 = r35.findViewHolderForAdapterPosition(r32);
        if (r15 != null) goto L176;
        return;
    L176:
        if (r15.getItemViewType() != 0) goto L178;
    L179:
        View r16 = r15.itemView;
        if ((r16 instanceof SharedPhotoVideoCell) == false) goto L186;
        MessageObject r17 = ((SharedPhotoVideoCell) r16).getMessageObject(0);
        if (r17 == null) goto L211;
        this.floatingDateView.setCustomDate(r17.messageOwner.date, false, true);
        return;
    L211:
        return;
    L186:
        if ((r16 instanceof ContextLinkCell) == false) goto L212;
        this.floatingDateView.setCustomDate(((ContextLinkCell) r16).getDate(), false, true);
        return;
    L212:
        return;
    L178:
        if (r15.getItemViewType() == 12) goto L179;
        return;
    L208:
        return;
    L206:
        return;
    L124:
        if (this.sharedMediaData[r18].loadingAfterFastScroll == false) goto L154;
    L120:
        r18 = r112;
        goto L122
    L114:
        if (r112 == 5) goto L118;
        r4 = 6;
        goto L118
    L216:
        return;
    L36:
        r10 = this.sharedMediaData[r11].getStartOffset() + this.sharedMediaData[r11].messages.size();
        SharedMediaData r152 = this.sharedMediaData[r11];
        if (r152.fastScrollDataLoaded == false) goto L62;
        if (r152.fastScrollPeriods.size() <= 2) goto L62;
        if (r34.selectedType != 0) goto L62;
        if (this.sharedMediaData[r11].messages.size() == 0) goto L62;
        if (r11 != 0) goto L46;
        int r64 = this.mediaColumnsCount[0];
    L47:
        float r65 = r64;
        int r66 = (int) (((r35.getMeasuredHeight() / (r35.getMeasuredWidth() / r65)) * r65) * 1.5f);
        if (r66 >= 100) goto L51;
        r66 = 100;
    L51:
        if (r66 >= this.sharedMediaData[r11].fastScrollPeriods.get(1).startOffset) goto L53;
        r66 = this.sharedMediaData[r11].fastScrollPeriods.get(1).startOffset;
    L53:
        if (r32 > r10) goto L55;
    L56:
        int r132 = r32 + r9;
        if (r132 >= SharedMediaData.m12992$$Nest$fgetstartOffset(this.sharedMediaData[r11])) goto L62;
        if ((SharedMediaData.m12992$$Nest$fgetstartOffset(this.sharedMediaData[0]) - r132) <= r66) goto L62;
    L60:
        SharedMediaLayout$$ExternalSyntheticLambda29 r19 = new SharedMediaLayout$$ExternalSyntheticLambda29(this, r11, r35);
        this.jumpToRunnable = r19;
        AndroidUtilities.runOnUIThread(r19);
        return;
    L55:
        if ((r32 - r10) > r66) goto L60;
    L46:
        r64 = 1;
        goto L47
    L30:
        r10 = r35.getAdapter().getItemCount();
        goto L31
    L26:
        r9 = Math.abs(r36.findLastVisibleItemPosition() - r32) + 1;
        goto L28
    L215:
        return;
    L10:
        if (r35.getFastScroll().isPressed() == false) goto L14;
        if ((r3 - r34.lastCheckScrollTime) >= 300) goto L14;
        return;
    }

    private boolean checkPointerIds(MotionEvent r5) {
        if (r5.getPointerCount() >= 2) goto L6;
        return false;
    L6:
        if (this.pointerId1 != r5.getPointerId(0)) goto L11;
        if (this.pointerId2 != r5.getPointerId(1)) goto L11;
        return true;
    L11:
        if (this.pointerId1 == r5.getPointerId(1)) goto L13;
    L15:
        return false;
    L13:
        if (this.pointerId2 != r5.getPointerId(0)) goto L15;
        return true;
    }

    private void checkStoriesTabsPosition() {
        MediaPage[] r0 = this.mediaPages;
        MediaPage r2 = r0[0];
        if (r2 != null) goto L5;
        return;
    L5:
        if (r0[1] == null) goto L66;
        float r4 = 0.0f;
        if (this.storiesContainer != null) goto L10;
    L57:
        checkUi_topPanelLayoutY();
        if (this.subTabsVisibilityFactor == r4) goto L65;
        this.subTabsVisibilityFactor = r4;
        invalidateBlur();
        invalidate();
        return;
    L65:
        return;
    L10:
        if (isAnyStoryPageType(r2.selectedType) == true) goto L12;
    L14:
        char r02 = 0;
    L16:
        if (this.mediaPages[1].getVisibility() == 0) goto L18;
        char r22 = r02;
    L25:
        if (r02 != r22) goto L34;
        if (r02 == 0) goto L28;
        float r23 = 1.0f;
    L29:
        ProfileStoriesCollectionTabs r3 = this.storiesContainer;
        if (r02 == 0) goto L32;
        float r03 = 0.0f;
    L33:
        r3.setTranslationX(r03);
    L35:
        int r04 = 0;
        float r32 = 0.0f;
    L36:
        MediaPage[] r6 = this.mediaPages;
        if (r04 >= r6.length) goto L56;
        if (r6[r04].getVisibility() != 0) goto L55;
        InternalListView r62 = MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r04]);
        if (r62.getChildCount() != 0) goto L44;
        View r7 = null;
    L45:
        if (r7 != null) goto L47;
        int r9 = -1;
    L48:
        if (r9 != 0) goto L51;
        float r72 = r7.getY() - r62.getPaddingTop();
    L54:
        r32 = r32 + (r72 * Utilities.clamp01(1.0f - (this.mediaPages[r04].getTranslationX() / this.mediaPages[r04].getMeasuredWidth())));
        goto L55
    L51:
        if (r62.getChildCount() != 0) goto L53;
        r72 = 0.0f;
        goto L54
    L53:
        r72 = -AndroidUtilities.dp(48.0f);
        goto L54
    L47:
        r9 = r62.getChildAdapterPosition(r7);
        goto L48
    L44:
        r7 = r62.getChildAt(0);
    L55:
        r04 = r04 + 1;
        goto L36
    L56:
        float r05 = Utilities.clamp01(1.0f - ((-r32) / AndroidUtilities.dpf2(48.0f)));
        float r1 = AndroidUtilities.lerp(0.9f, 1.0f, r05);
        this.storiesContainer.setAlpha(r05);
        this.storiesContainer.setScaleX(r1);
        this.storiesContainer.setScaleY(r1);
        this.storiesContainer.setTranslationY(this.topPadding + r32);
        r4 = r23;
        goto L57
    L32:
        r03 = this.mediaPages[0].getMeasuredWidth();
        goto L33
    L28:
        r23 = 0.0f;
        goto L29
    L34:
        this.storiesContainer.setTranslationX(this.mediaPages[r02 ^ 1].getTranslationX());
        r23 = 1.0f - (Math.abs(this.storiesContainer.getTranslationX()) / this.storiesContainer.getMeasuredWidth());
        goto L35
    L18:
        if (isAnyStoryPageType(this.mediaPages[1].selectedType) == true) goto L20;
    L22:
        r22 = 0;
        goto L25
    L20:
        if (this.mediaPages[1].selectedType == 9) goto L22;
        r22 = 1;
        goto L25
    L12:
        if (this.mediaPages[0].selectedType == 9) goto L14;
        r02 = 1;
        goto L16
    }

    private void checkUi_topPanelLayoutY() {
        if (this.topPanelLayout == null) goto L9;
        ProfileStoriesCollectionTabs r0 = this.storiesContainer;
        float r1 = 0.0f;
        if (r0 == null) goto L7;
        r1 = 0.0f + ((AndroidUtilities.dp(38.0f) * this.storiesContainer.getVisibilityFactor()) * (1.0f - Math.abs(r0.getTranslationX() / this.storiesContainer.getMeasuredWidth())));
    L7:
        this.topPanelLayout.setTranslationY(this.topPadding + r1);
        return;
    }

    public static View createEmptyStubView(Context r1, int r2, long r3, Theme.ResourcesProvider r5) {
        EmptyStubView r0 = new EmptyStubView(r1, r5);
        if (r2 != 0) goto L11;
        boolean r12 = DialogObject.isEncryptedDialog(r3);
        TextView r22 = r0.emptyTextView;
        if (r12 == false) goto L8;
        r22.setText(LocaleController.getString(R.string.NoMediaSecret));
        return r0;
    L8:
        r22.setText(LocaleController.getString(R.string.NoMedia));
        return r0;
    L11:
        if (r2 != 1) goto L19;
        boolean r13 = DialogObject.isEncryptedDialog(r3);
        TextView r23 = r0.emptyTextView;
        if (r13 == false) goto L16;
        r23.setText(LocaleController.getString(R.string.NoSharedFilesSecret));
        return r0;
    L16:
        r23.setText(LocaleController.getString(R.string.NoSharedFiles));
        return r0;
    L19:
        if (r2 != 2) goto L27;
        boolean r14 = DialogObject.isEncryptedDialog(r3);
        TextView r24 = r0.emptyTextView;
        if (r14 == false) goto L24;
        r24.setText(LocaleController.getString(R.string.NoSharedVoiceSecret));
        return r0;
    L24:
        r24.setText(LocaleController.getString(R.string.NoSharedVoice));
        return r0;
    L27:
        if (r2 != 3) goto L35;
        boolean r15 = DialogObject.isEncryptedDialog(r3);
        TextView r25 = r0.emptyTextView;
        if (r15 == false) goto L32;
        r25.setText(LocaleController.getString(R.string.NoSharedLinksSecret));
        return r0;
    L32:
        r25.setText(LocaleController.getString(R.string.NoSharedLinks));
        return r0;
    L35:
        if (r2 != 4) goto L43;
        boolean r16 = DialogObject.isEncryptedDialog(r3);
        TextView r26 = r0.emptyTextView;
        if (r16 == false) goto L40;
        r26.setText(LocaleController.getString(R.string.NoSharedAudioSecret));
        return r0;
    L40:
        r26.setText(LocaleController.getString(R.string.NoSharedAudio));
        return r0;
    L43:
        if (r2 != 5) goto L51;
        boolean r17 = DialogObject.isEncryptedDialog(r3);
        TextView r27 = r0.emptyTextView;
        if (r17 == false) goto L48;
        r27.setText(LocaleController.getString(R.string.NoSharedGifSecret));
        return r0;
    L48:
        r27.setText(LocaleController.getString(R.string.NoGIFs));
        return r0;
    L51:
        if (r2 != 6) goto L55;
        r0.emptyImageView.setImageDrawable(null);
        r0.emptyTextView.setText(LocaleController.getString(R.string.NoGroupsInCommon));
        return r0;
    L55:
        if (r2 != 7) goto L57;
        r0.emptyImageView.setImageDrawable(null);
        r0.emptyTextView.setText(_UrlKt.FRAGMENT_ENCODE_SET);
    L57:
        return r0;
    }

    private ScrollSlidingTextTabStripInner createScrollingTextTabStrip(Context r5) {
        AnonymousClass39 r0 = new AnonymousClass39(this, r5, this.resourcesProvider);
        int r52 = this.initialTab;
        if (r52 == (-1)) goto L5;
        r0.setInitialTabId(r52);
        this.initialTab = -1;
    L5:
        r0.animationDuration = 320;
        r0.setColors(Theme.key_profile_tabSelectedLine, Theme.key_profile_tabSelectedText, Theme.key_profile_tabText, Theme.key_profile_tabSelector);
        r0.setUseMinimalWidth(true);
        r0.setDelegate(new AnonymousClass40(this));
        return r0;
    }

    private boolean fillMediaData(int r9) {
        SharedMediaData[] r0 = this.sharedMediaPreloader.getSharedMediaData();
        int r1 = 0;
        if (r0 != null) goto L5;
        return false;
    L5:
        SharedMediaData[] r2 = this.sharedMediaData;
        if (r9 != 0) goto L10;
        SharedMediaData r22 = r2[r9];
        if (r22.fastScrollDataLoaded == true) goto L11;
        int[] r23 = r22.totalCount;
        int[] r4 = r0[r9].totalCount;
        r23[0] = r4[0];
        r23[1] = r4[1];
    L11:
        this.sharedMediaData[r9].messages.addAll(r0[r9].messages);
        this.sharedMediaData[r9].sections.addAll(r0[r9].sections);
        Iterator<Map.Entry<String, ArrayList<MessageObject>>> r24 = r0[r9].sectionArrays.entrySet().iterator();
    L13:
        if (r24.hasNext() == false) goto L15;
        Map.Entry<String, ArrayList<MessageObject>> r42 = r24.next();
        this.sharedMediaData[r9].sectionArrays.put(r42.getKey(), new ArrayList(r42.getValue()));
    L15:
        SharedMediaData[] r25 = this.sharedMediaData;
        if (r1 >= 2) goto L18;
        r25[r9].messagesDict[r1] = r0[r9].messagesDict[r1].clone();
        SharedMediaData r26 = this.sharedMediaData[r9];
        int[] r43 = r26.max_id;
        SharedMediaData r5 = r0[r9];
        r43[r1] = r5.max_id[r1];
        r26.endReached[r1] = r5.endReached[r1];
        r1 = r1 + 1;
        goto L15
    L18:
        r25[r9].fastScrollPeriods.addAll(r0[r9].fastScrollPeriods);
        return !r0[r9].messages.isEmpty();
    L10:
        int[] r27 = r2[r9].totalCount;
        int[] r44 = r0[r9].totalCount;
        r27[0] = r44[0];
        r27[1] = r44[1];
        goto L11
    }

    private void findPeriodAndJumpToDate(int r5, RecyclerListView r6, boolean r7) {
        ArrayList<Period> r0 = this.sharedMediaData[r5].fastScrollPeriods;
        int r62 = ((LinearLayoutManager) r6.getLayoutManager()).findFirstVisibleItemPosition();
        if (r62 < 0) goto L20;
        Period r1 = null;
        if (r0 == null) goto L15;
        int r2 = 0;
    L8:
        if (r2 >= r0.size()) goto L13;
        if (r62 <= r0.get(r2).startOffset) goto L11;
        r2 = r2 + 1;
        goto L8
    L11:
        r1 = r0.get(r2);
    L13:
        if (r1 != null) goto L15;
        r1 = r0.get(r0.size() - 1);
    L15:
        if (r1 == null) goto L21;
        jumpToDate(r5, r1.maxId, r1.startOffset + 1, r7);
        return;
    L21:
        return;
    }

    private void finishPinchToMediaColumnsCount() {
        if (this.photoVideoChangeColumnsAnimation == false) goto L82;
        int r0 = 0;
        int r1 = 0;
    L5:
        MediaPage[] r2 = this.mediaPages;
        if (r1 >= r2.length) goto L11;
        MediaPage r22 = r2[r1];
        if (r22.selectedType == this.changeColumnsTab) goto L12;
        r1 = r1 + 1;
    L12:
        if (r22 == null) goto L83;
        boolean r12 = isAnyStoryPageType(r22.selectedType);
        float r3 = this.photoVideoChangeColumnsProgress;
        float r4 = 1.0f;
        if (r3 != 1.0f) goto L55;
        this.photoVideoChangeColumnsAnimation = false;
        int[] r32 = this.mediaColumnsCount;
        int r42 = this.animateToColumnsCount;
        r32[r12 ? 1 : 0] = r42;
        if (r12 == true) goto L19;
        SharedConfig.setMediaColumnsCount(r42);
    L21:
        int r23 = 0;
    L22:
        MediaPage[] r33 = this.mediaPages;
        if (r23 >= r33.length) goto L42;
        MediaPage r34 = r33[r23];
        if (r34 == null) goto L40;
        if (MediaPage.m12961$$Nest$fgetlistView(r34) == null) goto L40;
        if (isTabZoomable(this.mediaPages[r23].selectedType) == false) goto L40;
        RecyclerView.Adapter r35 = MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r23]).getAdapter();
        if (r35 == null) goto L40;
        int r43 = r35.getItemCount();
        if (r23 != 0) goto L36;
        this.sharedMediaData[0].setListFrozen(false);
    L36:
        MediaPage.m12956$$Nest$fgetanimationSupportingListView(this.mediaPages[r23]).setVisibility(8);
        MediaPage.m12960$$Nest$fgetlayoutManager(this.mediaPages[r23]).setSpanCount(this.mediaColumnsCount[r12 ? 1 : 0]);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r23]).invalidateItemDecorations();
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r23]).invalidate();
        if (r35.getItemCount() != r43) goto L39;
        AndroidUtilities.updateVisibleRows(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r23]));
        goto L40
    L39:
        r35.notifyDataSetChanged();
    L40:
        r23 = r23 + 1;
        goto L22
    L42:
        if (this.pinchCenterPosition < 0) goto L52;
    L43:
        MediaPage[] r13 = this.mediaPages;
        if (r0 >= r13.length) goto L84;
        MediaPage r14 = r13[r0];
        if (r14.selectedType != this.changeColumnsTab) goto L51;
        View r15 = MediaPage.m12955$$Nest$fgetanimationSupportingLayoutManager(r14).findViewByPosition(this.pinchCenterPosition);
        if (r15 == null) goto L50;
        this.pinchCenterOffset = r15.getTop();
    L50:
        MediaPage.m12960$$Nest$fgetlayoutManager(this.mediaPages[r0]).scrollToPositionWithOffset(this.pinchCenterPosition, (-MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r0]).getPaddingTop()) + this.pinchCenterOffset);
    L51:
        r0 = r0 + 1;
        goto L43
    L84:
        return;
    L52:
        saveScrollPosition();
        return;
    L19:
        if (getStoriesCount(r22.selectedType) < 5) goto L21;
        SharedConfig.setStoriesColumnsCount(this.animateToColumnsCount);
        goto L21
    L55:
        if (r3 != 0.0f) goto L62;
        this.photoVideoChangeColumnsAnimation = false;
        if (this.changeColumnsTab != 0) goto L59;
        this.sharedMediaData[0].setListFrozen(false);
    L59:
        MediaPage.m12956$$Nest$fgetanimationSupportingListView(r22).setVisibility(8);
        MediaPage.m12961$$Nest$fgetlistView(r22).invalidate();
        return;
    L62:
        if (r3 <= 0.2f) goto L64;
        boolean r6 = true;
    L65:
        if (r6 == true) goto L68;
        r4 = 0.0f;
    L68:
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{r3, r4});
        r02.addUpdateListener(new AnonymousClass35(this, r22));
        r02.addListener(new AnonymousClass36(this, r6, r12 ? 1 : 0, r22));
        r02.setInterpolator(CubicBezierInterpolator.DEFAULT);
        r02.setDuration(200);
        r02.start();
        return;
    L64:
        r6 = false;
        goto L65
    L83:
        return;
    L11:
        r22 = null;
        goto L12
    }

    private void fixLayoutInternal(int r3) {
        ((WindowManager) ApplicationLoader.applicationContext.getSystemService("window")).getDefaultDisplay().getRotation();
        if (r3 == 0) goto L5;
    L10:
        if (r3 != 0) goto L13;
        this.photoVideoAdapter.notifyDataSetChanged();
        return;
    L13:
        return;
    L5:
        if (AndroidUtilities.isTablet() == false) goto L7;
    L9:
        this.selectedMessagesCountTextView.setTextSize(20);
        goto L10
    L7:
        if (ApplicationLoader.applicationContext.getResources().getConfiguration().orientation != 2) goto L9;
        this.selectedMessagesCountTextView.setTextSize(18);
        goto L10
    }

    private MediaPage getMediaPage(int r4) {
        int r0 = 0;
    L3:
        MediaPage[] r1 = this.mediaPages;
        if (r0 >= r1.length) goto L11;
        MediaPage r12 = r1[r0];
        if (r12 == null) goto L10;
        if (r12.selectedType != r4) goto L10;
        return r12;
    L10:
        r0 = r0 + 1;
        goto L3
    L11:
        return null;
    }

    private static int getMessageId(View r1) {
        if ((r1 instanceof SharedPhotoVideoCell2) == false) goto L7;
        return ((SharedPhotoVideoCell2) r1).getMessageId();
    L7:
        if ((r1 instanceof SharedDocumentCell) == false) goto L11;
        return ((SharedDocumentCell) r1).getMessage().getId();
    L11:
        if ((r1 instanceof SharedAudioCell) == true) goto L13;
        return 0;
    L13:
        return ((SharedAudioCell) r1).getMessage().getId();
    }

    private int getNextPageId(boolean r5) {
        int r0 = getClosestTab();
        int r1 = this.scrollSlidingTextTabStrip.getNextPageId(r5);
        if (this.storiesContainer != null) goto L5;
    L20:
        return r1;
    L5:
        if (isStoryAlbumPageType(r0) == true) goto L14;
        if (r0 == 8) goto L14;
        if (isStoryAlbumPageType(r1) == true) goto L13;
        if (r1 == 8) goto L13;
        int r52 = -1;
    L15:
        if (r52 != 0) goto L17;
        return 8;
    L17:
        if (r52 <= 0) goto L20;
        return storyAlbums_getByAlbumId(r52).tabType;
    L13:
        r52 = this.storiesContainer.getCurrentAlbumId();
    L14:
        r52 = this.storiesContainer.getNextAlbumId(r5);
        goto L15
    }

    private int getPagePaddingBottom(boolean r1) {
        int r0 = this.pagesPaddingBottom;
        if (r1 == false) goto L5;
        int r12 = AndroidUtilities.dp(52.0f);
    L7:
        return r0 + r12;
    L5:
        r12 = 0;
        goto L7
    }

    private int getPagePaddingTop(int r3) {
        int r0 = AndroidUtilities.dp(54.0f) + this.topLayoutPadding;
        if (this.storiesContainer != null) goto L5;
    L9:
        float r2 = 0.0f;
    L10:
        int r02 = r0 + ((int) r2);
        if (r3 != 9) goto L13;
        int r22 = AndroidUtilities.dp(64.0f);
    L15:
        return r02 + r22;
    L13:
        r22 = 0;
        goto L15
    L5:
        if (isStoryAlbumPageType(r3) == false) goto L7;
    L8:
        r2 = this.storiesContainer.getVisibilityFactor() * AndroidUtilities.dp(40.0f);
        goto L10
    L7:
        if (r3 != 8) goto L9;
        goto L8
    }

    private StoriesController getStoriesController() {
        return MessagesController.getInstance(this.profileActivity.getCurrentAccount()).getStoriesController();
    }

    private static int getStoryAlbumType(int r1) {
        return (r1 & 65535) | 65536;
    }

    public static TLRPC.ProfileTab getTab(int r3, boolean r4) {
        if (r3 == 8) goto L7;
        if (r3 == 14) goto L7;
        if (r4 == true) goto L7;
        return null;
    L7:
        if (r3 == 0) goto L36;
        if (r3 == 1) goto L34;
        if (r3 == 2) goto L32;
        if (r3 == 3) goto L30;
        if (r3 == 4) goto L28;
        if (r3 == 5) goto L26;
        if (r3 == 8) goto L24;
        if (r3 == 14) goto L22;
        return null;
    L22:
        return new TLRPC.TL_profileTabGifts();
    L24:
        return new TLRPC.TL_profileTabPosts();
    L26:
        return new TLRPC.TL_profileTabGifs();
    L28:
        return new TLRPC.TL_profileTabMusic();
    L30:
        return new TLRPC.TL_profileTabLinks();
    L32:
        return new TLRPC.TL_profileTabVoice();
    L34:
        return new TLRPC.TL_profileTabFiles();
    L36:
        return new TLRPC.TL_profileTabMedia();
    }

    public static int getTabId(TLRPC.ProfileTab r1) {
        if ((r1 instanceof TLRPC.TL_profileTabPosts) == false) goto L7;
        return 8;
    L7:
        if ((r1 instanceof TLRPC.TL_profileTabMedia) == false) goto L11;
        return 0;
    L11:
        if ((r1 instanceof TLRPC.TL_profileTabGifts) == false) goto L15;
        return 14;
    L15:
        if ((r1 instanceof TLRPC.TL_profileTabMusic) == false) goto L19;
        return 4;
    L19:
        if ((r1 instanceof TLRPC.TL_profileTabVoice) == false) goto L23;
        return 2;
    L23:
        if ((r1 instanceof TLRPC.TL_profileTabLinks) == false) goto L27;
        return 3;
    L27:
        if ((r1 instanceof TLRPC.TL_profileTabFiles) == false) goto L31;
        return 1;
    L31:
        if ((r1 instanceof TLRPC.TL_profileTabGifs) == false) goto L34;
        return 5;
    L34:
        return -1;
    }

    public static String getTabName(int r1) {
        if (r1 == 0) goto L34;
        if (r1 == 1) goto L32;
        if (r1 == 2) goto L30;
        if (r1 == 3) goto L28;
        if (r1 == 4) goto L26;
        if (r1 == 5) goto L24;
        if (r1 == 8) goto L22;
        if (r1 == 14) goto L20;
        return null;
    L20:
        return LocaleController.getString(R.string.ProfileGifts);
    L22:
        return LocaleController.getString(R.string.ProfileStories);
    L24:
        return LocaleController.getString(R.string.SharedGIFsTab2);
    L26:
        return LocaleController.getString(R.string.SharedMusicTab2);
    L28:
        return LocaleController.getString(R.string.SharedLinksTab2);
    L30:
        return LocaleController.getString(R.string.SharedVoiceTab2);
    L32:
        return LocaleController.getString(R.string.SharedFilesTab2);
    L34:
        return LocaleController.getString(R.string.SharedMediaTabFull2);
    }

    private int getThemedColor(int r1) {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L7;
        return r0.getColor(r1);
    L7:
        return Theme.getColor(r1);
    }

    private boolean hasNoforwardsMessage() {
        boolean r3 = false;
        int r2 = 1;
    L3:
        if (r2 < 0) goto L22;
        ArrayList r4 = new ArrayList();
        int r5 = 0;
    L6:
        if (r5 >= this.selectedFiles[r2].size()) goto L8;
        r4.add(Integer.valueOf(this.selectedFiles[r2].keyAt(r5)));
        r5 = r5 + 1;
        goto L6
    L8:
        int r52 = r4.size();
        int r6 = 0;
    L9:
        if (r6 >= r52) goto L19;
        Object r7 = r4.get(r6);
        r6 = r6 + 1;
        Integer r72 = (Integer) r7;
        if (r72.intValue() <= 0) goto L9;
        MessageObject r73 = this.selectedFiles[r2].get(r72.intValue());
        if (r73 == null) goto L9;
        TLRPC.Message r74 = r73.messageOwner;
        if (r74 == null) goto L9;
        if (r74.noforwards == false) goto L9;
        r3 = true;
    L19:
        if (r3 == true) goto L20;
        r2 = r2 - 1;
        goto L3
    L20:
        return r3;
    L22:
        return r3;
    }

    private void hideFloatingDateView(boolean r7) {
        AndroidUtilities.cancelRunOnUIThread(this.hideFloatingDateRunnable);
        if (this.floatingDateView.getTag() != null) goto L5;
        return;
    L5:
        this.floatingDateView.setTag(null);
        AnimatorSet r0 = this.floatingDateAnimation;
        if (r0 == null) goto L9;
        r0.cancel();
        this.floatingDateAnimation = null;
    L9:
        if (r7 == false) goto L12;
        AnimatorSet r72 = new AnimatorSet();
        this.floatingDateAnimation = r72;
        r72.setDuration(180);
        this.floatingDateAnimation.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.floatingDateView, View.ALPHA, new float[]{0.0f}), ObjectAnimator.ofFloat(this.floatingDateView, View.TRANSLATION_Y, new float[]{(-AndroidUtilities.dp(48.0f)) + this.additionalFloatingTranslation})});
        this.floatingDateAnimation.setInterpolator(CubicBezierInterpolator.EASE_OUT);
        this.floatingDateAnimation.addListener(new AnonymousClass41(this));
        this.floatingDateAnimation.start();
        return;
    L12:
        this.floatingDateView.setAlpha(0.0f);
    }

    public static boolean isAnyStoryPageType(int r1) {
        if (r1 != 8) goto L5;
        return true;
    L5:
        if (r1 != 9) goto L7;
        return true;
    L7:
        if (isStoryAlbumPageType(r1) == true) goto L14;
        return false;
    L14:
        return true;
    }

    public static boolean isStoryAlbumPageType(int r1) {
        if ((r1 & Opcodes.V_PREVIEW) != 65536) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isTab(int r1, int r2, boolean r3) {
        if (r3 == true) goto L5;
    L9:
        if (r1 != r2) goto L11;
        return true;
    L11:
        return false;
    L5:
        if (r2 != 8) goto L9;
        if (isStoryAlbumPageType(r1) == false) goto L9;
        return true;
    }

    private void jumpToDate(int r5, int r6, int r7, boolean r8) {
        this.sharedMediaData[r5].messages.clear();
        this.sharedMediaData[r5].messagesDict[0].clear();
        this.sharedMediaData[r5].messagesDict[1].clear();
        this.sharedMediaData[r5].setMaxId(0, r6);
        this.sharedMediaData[r5].setEndReached(0, false);
        SharedMediaData r0 = this.sharedMediaData[r5];
        r0.startReached = false;
        SharedMediaData.m12994$$Nest$fputstartOffset(r0, r7);
        SharedMediaData r02 = this.sharedMediaData[r5];
        SharedMediaData.m12993$$Nest$fputendLoadingStubs(r02, (r02.getTotalCount() - r7) - 1);
        if (SharedMediaData.m12989$$Nest$fgetendLoadingStubs(this.sharedMediaData[r5]) >= 0) goto L5;
        SharedMediaData.m12993$$Nest$fputendLoadingStubs(this.sharedMediaData[r5], 0);
    L5:
        SharedMediaData r72 = this.sharedMediaData[r5];
        r72.min_id = r6;
        r72.loadingAfterFastScroll = true;
        r72.loading = false;
        r72.requestIndex++;
        MediaPage r62 = getMediaPage(r5);
        if (r62 != null) goto L8;
    L10:
        if (r8 == false) goto L18;
        int r63 = 0;
    L12:
        MediaPage[] r73 = this.mediaPages;
        if (r63 >= r73.length) goto L22;
        MediaPage r74 = r73[r63];
        if (r74.selectedType != r5) goto L17;
        MediaPage.m12960$$Nest$fgetlayoutManager(r74).scrollToPositionWithOffset(Math.min(this.sharedMediaData[r5].getTotalCount() - 1, SharedMediaData.m12992$$Nest$fgetstartOffset(this.sharedMediaData[r5])), 0);
    L17:
        r63 = r63 + 1;
        goto L12
    L22:
        return;
    L18:
        return;
    L8:
        if (MediaPage.m12961$$Nest$fgetlistView(r62).getAdapter() == null) goto L10;
        MediaPage.m12961$$Nest$fgetlistView(r62).getAdapter().notifyDataSetChanged();
        goto L10
    }

    private /* synthetic */ void lambda$addStoryAlbumShareItemOptions$81(String r11, BaseFragment r12) {
        AnonymousClass50 r0 = new AnonymousClass50(this, getContext(), null, r11, false, r11, false, this.resourcesProvider, r12);
        if (r12 == null) goto L6;
        r12.showDialog(r0);
        return;
    L6:
        r0.show();
    }

    private /* synthetic */ void lambda$addZoomInZoomOutItemOptions$82(View[] r3) {
        zoomIn(r3[0], r3[1]);
    }

    private /* synthetic */ void lambda$addZoomInZoomOutItemOptions$83(View[] r3) {
        zoomOut(r3[0], r3[1]);
    }

    private /* synthetic */ void lambda$buildItemOptionsForStoryAlbumActionBar$78(BaseFragment r1, long r2, int r4, ItemOptions r5) {
        openAddStoriesToAlbumSheet(r1, r2, r4);
        r5.dismiss();
    }

    private /* synthetic */ void lambda$buildItemOptionsForStoryAlbumActionBar$79(int r1, ItemOptions r2) {
        startAlbumsReorder(r1);
        r2.dismiss();
    }

    private /* synthetic */ void lambda$buildItemOptionsForStoryAlbumActionBar$80(BaseFragment r1, long r2, int r4, ItemOptions r5) {
        openDeleteStoriesAlbumAlert(r1, r2, r4);
        r5.dismiss();
    }

    private /* synthetic */ void lambda$checkLoadMoreScroll$37(int r2, RecyclerListView r3) {
        findPeriodAndJumpToDate(r2, r3, false);
        this.jumpToRunnable = null;
    }

    private /* synthetic */ void lambda$getThemeDescriptions$73(int r6) {
        if (MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r6]) == null) goto L16;
        int r0 = MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r6]).getChildCount();
        int r2 = 0;
    L5:
        if (r2 >= r0) goto L22;
        View r3 = MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r6]).getChildAt(r2);
        if ((r3 instanceof SharedPhotoVideoCell) == false) goto L10;
        ((SharedPhotoVideoCell) r3).updateCheckboxColor();
    L15:
        r2 = r2 + 1;
        goto L5
    L10:
        if ((r3 instanceof ProfileSearchCell) == false) goto L13;
        ((ProfileSearchCell) r3).update(0);
        goto L15
    L13:
        if ((r3 instanceof UserCell) == false) goto L15;
        ((UserCell) r3).update(0);
        goto L15
    L22:
        return;
    }

    private /* synthetic */ void lambda$loadFastScrollData$39(TLRPC.TL_error r4, int r5, int r6, TLObject r7) {
        if (r4 != null) goto L29;
        SharedMediaData r42 = this.sharedMediaData[r6];
        if (r5 == r42.requestIndex) goto L7;
        return;
    L7:
        TLRPC.TL_messages_searchResultsPositions r72 = (TLRPC.TL_messages_searchResultsPositions) r7;
        r42.fastScrollPeriods.clear();
        int r43 = r72.positions.size();
        int r52 = 0;
        int r0 = 0;
    L8:
        if (r0 >= r43) goto L13;
        TLRPC.TL_searchResultPosition r1 = r72.positions.get(r0);
        if (r1.date == 0) goto L12;
        this.sharedMediaData[r6].fastScrollPeriods.add(new Period(r1));
    L12:
        r0 = r0 + 1;
        goto L8
    L13:
        Collections.sort(this.sharedMediaData[r6].fastScrollPeriods, new SharedMediaLayout$$ExternalSyntheticLambda66());
        this.sharedMediaData[r6].setTotalCount(0, r72.count);
        SharedMediaData r44 = this.sharedMediaData[r6];
        r44.fastScrollDataLoaded = true;
        if (r44.fastScrollPeriods.isEmpty() == true) goto L21;
    L15:
        MediaPage[] r45 = this.mediaPages;
        if (r52 >= r45.length) goto L21;
        MediaPage r46 = r45[r52];
        if (r46.selectedType != r6) goto L20;
        r46.fastScrollEnabled = true;
        updateFastScrollVisibility(r46, true);
    L20:
        r52 = r52 + 1;
    L21:
        this.photoVideoAdapter.notifyDataSetChanged();
        return;
    }

    private /* synthetic */ void lambda$loadFastScrollData$40(TLRPC.TL_error r8, int r9, int r10, TLObject r11) {
        NotificationCenter.getInstance(this.profileActivity.getCurrentAccount()).doOnIdle(new SharedMediaLayout$$ExternalSyntheticLambda57(this, r8, r9, r10, r11));
    }

    private /* synthetic */ void lambda$loadFastScrollData$41(int r7, int r8, TLObject r9, TLRPC.TL_error r10) {
        AndroidUtilities.runOnUIThread(new SharedMediaLayout$$ExternalSyntheticLambda28(this, r10, r7, r8, r9));
    }

    private /* synthetic */ void lambda$new$10(View r2) {
        onActionBarItemClick(r2, 101);
    }

    private /* synthetic */ void lambda$new$11(View r2) {
        if (this.saveItem.getAlpha() < 0.1f) goto L18;
        ProfileGiftsContainer r22 = this.giftsContainer;
        if (r22 != null) goto L8;
    L10:
        ProfileStoriesCollectionTabs r23 = this.storiesContainer;
        if (r23 != null) goto L13;
        return;
    L13:
        if (r23.isReordering() == false) goto L17;
        saveAndStopAlbumsReorder();
        return;
    L17:
        return;
    L8:
        if (r22.isReordering() == false) goto L10;
        this.giftsContainer.resetReordering();
        goto L10
    }

    private /* synthetic */ void lambda$new$17(AlertDialog r21, MessagesController r22, Context r23, SharedPhotoVideoCell2 r24, float r25, float r26, int r27, TL_account.contentSettings r28) {
        r21.dismissUnless(200);
        if (r22.config.needAgeVideoVerification.get() == true) goto L5;
    L7:
        boolean r6 = false;
    L8:
        if (r28 != null) goto L10;
    L11:
        if (r6 == false) goto L13;
        boolean r1 = true;
    L14:
        boolean[] r5 = new boolean[1];
        FrameLayout r4 = new FrameLayout(r23);
        if (r6 == false) goto L17;
        r5[0] = true;
    L33:
        BaseFragment r3 = this.profileActivity;
        if (r3 != null) goto L36;
        Theme.ResourcesProvider r32 = null;
    L37:
        AlertDialog.Builder r2 = new AlertDialog.Builder(r23, r32).setTitle(LocaleController.getString(R.string.MessageShowSensitiveContentMediaTitle));
        if (r1 == false) goto L40;
        int r33 = R.string.MessageShowSensitiveContentMediaTextClosed;
    L41:
        AlertDialog.Builder r29 = r2.setMessage(LocaleController.getString(r33)).setView(r4).setCustomViewOffset(9);
        if (r1 == false) goto L44;
        int r34 = R.string.MessageShowSensitiveContentMediaTextClosedButton;
    L45:
        AlertDialog.Builder r11 = r29.setNegativeButton(LocaleController.getString(r34), null);
        if (r1 == true) goto L48;
        r11.setPositiveButton(LocaleController.getString(R.string.MessageShowSensitiveContentButton), new SharedMediaLayout$$ExternalSyntheticLambda39(r24, r25, r26, r5, r6, r28, r23, r27, r22));
    L48:
        BaseFragment r12 = this.profileActivity;
        if (r12 != null) goto L51;
    L54:
        r11.show();
        return;
    L51:
        if (r12.getContext() == null) goto L54;
        this.profileActivity.showDialog(r11.create());
        return;
    L44:
        r34 = R.string.Cancel;
        goto L45
    L40:
        r33 = R.string.MessageShowSensitiveContentMediaText;
        goto L41
    L36:
        r32 = r3.getResourceProvider();
        goto L37
    L17:
        if (r28 == null) goto L33;
        if (r28.sensitive_can_change == false) goto L33;
        BaseFragment r122 = this.profileActivity;
        if (r122 != null) goto L23;
        Theme.ResourcesProvider r123 = null;
    L24:
        CheckBoxCell r112 = new CheckBoxCell(r23, 1, r123);
        r112.setBackground(Theme.getSelectorDrawable(false));
        r112.setText(LocaleController.getString(R.string.MessageShowSensitiveContentAlways), _UrlKt.FRAGMENT_ENCODE_SET, r5[0], false);
        if (LocaleController.isRTL == false) goto L27;
        int r210 = AndroidUtilities.dp(16.0f);
    L29:
        if (LocaleController.isRTL == false) goto L31;
        int r124 = AndroidUtilities.dp(8.0f);
    L32:
        r112.setPadding(r210, 0, r124, 0);
        r4.addView(r112, LayoutHelper.createFrame(-1, 48.0f, 51, 0.0f, 0.0f, 0.0f, 0.0f));
        r112.setOnClickListener(new SharedMediaLayout$$ExternalSyntheticLambda38(r5));
        goto L33
    L31:
        r124 = AndroidUtilities.dp(16.0f);
        goto L32
    L27:
        r210 = AndroidUtilities.dp(8.0f);
        goto L29
    L23:
        r123 = r122.getResourceProvider();
    L13:
        r1 = false;
        goto L14
    L10:
        if (r28.sensitive_can_change == true) goto L13;
    L5:
        if (TextUtils.isEmpty(r22.verifyAgeBotUsername) == true) goto L7;
        r6 = true;
        goto L8
    }

    private /* synthetic */ void lambda$new$18(long r1, MessageObject r3) {
        this.profileActivity.presentFragment(ChatActivity.of(r1, r3.getId()));
    }

    private /* synthetic */ void lambda$new$2() {
        hideFloatingDateView(true);
    }

    private /* synthetic */ void lambda$new$22(Theme.ResourcesProvider r3, int r4, MessageObject r5) {
        AlertDialog[] r2 = {new AlertDialog(getContext(), 3, r3)};
        int r32 = SendMessagesHelper.getInstance(r4).sendVote(r5, null, new SharedMediaLayout$$ExternalSyntheticLambda51(r2));
        if (r32 == 0) goto L6;
        AndroidUtilities.runOnUIThread(new SharedMediaLayout$$ExternalSyntheticLambda52(r2, r4, r32), 500);
        return;
    }

    private /* synthetic */ void lambda$new$24(int r2, TLRPC.TL_error r3, TLRPC.TL_messages_editMessage r4) {
        AlertsCreator.processError(r2, r3, this.profileActivity, r4, new Object[0]);
    }

    private /* synthetic */ void lambda$new$25(AlertDialog[] r2, int r3, TLRPC.TL_messages_editMessage r4, TLObject r5, TLRPC.TL_error r6) {
        AndroidUtilities.runOnUIThread(new SharedMediaLayout$$ExternalSyntheticLambda78(r2));
        if (r6 != null) goto L6;
        MessagesController.getInstance(r3).processUpdates((TLRPC.Updates) r5, false);
        return;
    L6:
        AndroidUtilities.runOnUIThread(new SharedMediaLayout$$ExternalSyntheticLambda79(this, r3, r6, r4));
    }

    private /* synthetic */ void lambda$new$28(Theme.ResourcesProvider r5, MessageObject r6, int r7, AlertDialog r8, int r9) {
        AlertDialog[] r52 = {new AlertDialog(getContext(), 3, r5)};
        TLRPC.TL_messages_editMessage r82 = new TLRPC.TL_messages_editMessage();
        TLRPC.TL_messageMediaPoll r92 = (TLRPC.TL_messageMediaPoll) r6.messageOwner.media;
        TLRPC.TL_inputMediaPoll r0 = new TLRPC.TL_inputMediaPoll();
        TLRPC.TL_poll r1 = new TLRPC.TL_poll();
        r0.poll = r1;
        TLRPC.Poll r93 = r92.poll;
        r1.id = r93.id;
        r1.question = r93.question;
        r1.answers = r93.answers;
        r1.closed = true;
        r82.media = r0;
        r82.peer = MessagesController.getInstance(r7).getInputPeer(this.dialog_id);
        r82.id = r6.getId();
        r82.flags |= 16384;
        AndroidUtilities.runOnUIThread(new SharedMediaLayout$$ExternalSyntheticLambda73(r52, r7, ConnectionsManager.getInstance(r7).sendRequest(r82, new SharedMediaLayout$$ExternalSyntheticLambda72(this, r52, r7, r82))), 500);
    }

    private /* synthetic */ void lambda$new$29(Theme.ResourcesProvider r4, MessageObject r5, int r6) {
        AlertDialog.Builder r0 = new AlertDialog.Builder(getContext(), r4);
        r0.setDimEnabled(false);
        if (r5.isQuiz() == false) goto L5;
        r0.setTitle(LocaleController.getString(R.string.StopQuizAlertTitle));
        r0.setMessage(LocaleController.getString(R.string.StopQuizAlertText));
    L6:
        r0.setPositiveButton(LocaleController.getString(R.string.Stop), new SharedMediaLayout$$ExternalSyntheticLambda55(this, r4, r5, r6));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r0.show();
        return;
    L5:
        r0.setTitle(LocaleController.getString(R.string.StopPollAlertTitle));
        r0.setMessage(LocaleController.getString(R.string.StopPollAlertText));
        goto L6
    }

    private /* synthetic */ void lambda$new$30(MediaPage r11, Context r12, long r13, Theme.ResourcesProvider r15, View r16, int r17, float r18, float r19) {
        int r3 = r11.selectedType;
        if (r3 != 7) goto L33;
        if ((r16 instanceof UserCell) == true) goto L7;
        RecyclerView.Adapter r112 = MediaPage.m12961$$Nest$fgetlistView(r11).getAdapter();
        GroupUsersSearchAdapter r122 = this.groupUsersSearchAdapter;
        if (r112 != r122) goto L183;
        TLObject r113 = r122.getItem(r17);
        if ((r113 instanceof TLRPC.ChannelParticipant) == false) goto L23;
        long r114 = MessageObject.getPeerId(((TLRPC.ChannelParticipant) r113).peer);
    L26:
        if (r114 != 0) goto L28;
        return;
    L28:
        if (r114 == this.profileActivity.getUserConfig().getClientUserId()) goto L198;
        Bundle r0 = new Bundle();
        r0.putLong("user_id", r114);
        this.profileActivity.presentFragment(new ProfileActivity(r0));
        return;
    L198:
        return;
    L23:
        if ((r113 instanceof TLRPC.ChatParticipant) == false) goto L184;
        r114 = ((TLRPC.ChatParticipant) r113).user_id;
        goto L26
    L184:
        return;
    L183:
        return;
    L7:
        if (ChatUsersAdapter.m12945$$Nest$fgetsortedUsers(this.chatUsersAdapter).isEmpty() == true) goto L9;
        int r115 = ((Integer) ChatUsersAdapter.m12945$$Nest$fgetsortedUsers(this.chatUsersAdapter).get(r17)).intValue();
    L10:
        TLRPC.ChatParticipant r123 = ChatUsersAdapter.m12944$$Nest$fgetchatInfo(this.chatUsersAdapter).participants.participants.get(r115);
        if (r115 >= 0) goto L13;
        return;
    L13:
        if (r115 >= ChatUsersAdapter.m12944$$Nest$fgetchatInfo(this.chatUsersAdapter).participants.participants.size()) goto L197;
        onMemberClick(r123, false, r16);
        return;
    L197:
        return;
    L9:
        r115 = r17;
        goto L10
    L33:
        if (r3 != 6) goto L46;
        if ((r16 instanceof ProfileSearchCell) == false) goto L46;
        TLRPC.Chat r116 = ((ProfileSearchCell) r16).getChat();
        Bundle r124 = new Bundle();
        r124.putLong("chat_id", r116.id);
        if (this.profileActivity.getMessagesController().checkCanOpenChat(r124, this.profileActivity) == false) goto L199;
        boolean r117 = r116.forum;
        BaseFragment r10 = this.profileActivity;
        if (r117 == false) goto L43;
        r10.presentFragment(TopicsFragment.getTopicsOrChat(r10, r124));
        return;
    L43:
        r10.presentFragment(new ChatActivity(r124));
        return;
    L199:
        return;
    L46:
        if (r3 == 1) goto L48;
    L51:
        int r1 = 3;
        if (r3 != 3) goto L58;
        if ((r16 instanceof SharedLinkCell) == false) goto L58;
        onItemClick(r17, r16, ((SharedLinkCell) r16).getMessage(), 0, r11.selectedType);
        return;
    L58:
        if (r3 == 2) goto L62;
        if (r3 == 4) goto L62;
    L66:
        if (r3 != 5) goto L72;
        if ((r16 instanceof ContextLinkCell) == false) goto L72;
        onItemClick(r17, r16, (MessageObject) ((ContextLinkCell) r16).getParentObject(), 0, r11.selectedType);
        return;
    L72:
        if (r3 != 0) goto L94;
        if ((r16 instanceof SharedPhotoVideoCell2) == false) goto L94;
        SharedPhotoVideoCell2 r5 = (SharedPhotoVideoCell2) r16;
        MessageObject r32 = r5.getMessageObject();
        if (r32 == null) goto L85;
        if (r32.isSensitive() == false) goto L85;
        BaseFragment r118 = this.profileActivity;
        if (r118 == null) goto L200;
        int r8 = r118.getCurrentAccount();
        MessagesController r33 = MessagesController.getInstance(r8);
        AlertDialog r2 = new AlertDialog(r12, 3);
        r2.showDelayed(200);
        r33.getContentSettings(new SharedMediaLayout$$ExternalSyntheticLambda24(this, r2, r33, r12, r5, r18, r19, r8));
        return;
    L200:
        return;
    L85:
        if (this.isActionModeShowed == false) goto L87;
    L90:
        if (r32 == null) goto L186;
        onItemClick(r17, r16, r32, 0, r11.selectedType);
        return;
    L186:
        return;
    L87:
        if (r5.canRevealSpoiler() == false) goto L90;
        r5.startRevealMedia(r18, r19);
        return;
    L94:
        if (isAnyStoryPageType(r3) == true) goto L96;
    L101:
        int r82 = r11.selectedType;
        if (r82 != 10) goto L119;
        if ((r16 instanceof ProfileSearchCell) == false) goto L106;
    L107:
        if (r17 >= 0) goto L109;
        return;
    L109:
        if (r17 >= ChannelRecommendationsAdapter.m12942$$Nest$fgetchats(this.channelRecommendationsAdapter).size()) goto L190;
        Bundle r119 = new Bundle();
        TLObject r125 = (TLObject) ChannelRecommendationsAdapter.m12942$$Nest$fgetchats(this.channelRecommendationsAdapter).get(r17);
        if ((r125 instanceof TLRPC.Chat) == false) goto L114;
        r119.putLong("chat_id", ((TLRPC.Chat) r125).id);
    L116:
        this.profileActivity.presentFragment(new ChatActivity(r119));
        return;
    L114:
        if ((r125 instanceof TLRPC.User) == false) goto L191;
        r119.putLong("user_id", ((TLRPC.User) r125).id);
        goto L116
    L191:
        return;
    L190:
        return;
    L106:
        if (r19 < AndroidUtilities.dp(60.0f)) goto L107;
        return;
    L119:
        if (r82 != 11) goto L146;
        RecyclerView.Adapter r1110 = MediaPage.m12961$$Nest$fgetlistView(r11).getAdapter();
        SavedMessagesSearchAdapter r126 = this.savedMessagesSearchAdapter;
        if (r1110 != r126) goto L133;
        if (r17 < 0) goto L201;
        int r1111 = r126.dialogs.size();
        SavedMessagesSearchAdapter r127 = this.savedMessagesSearchAdapter;
        if (r17 >= r1111) goto L128;
        SavedMessagesController.SavedDialog r1112 = r127.dialogs.get(r17);
        Bundle r128 = new Bundle();
        r128.putLong("user_id", this.profileActivity.getUserConfig().getClientUserId());
        r128.putInt("chatMode", 3);
        ChatActivity r02 = new ChatActivity(r128);
        r02.setSavedDialog(r1112.dialogId);
        this.profileActivity.presentFragment(r02);
        return;
    L128:
        int r1113 = r17 - r127.dialogs.size();
        if (r1113 >= this.savedMessagesSearchAdapter.messages.size()) goto L192;
        MessageObject r129 = this.savedMessagesSearchAdapter.messages.get(r1113);
        Bundle r03 = new Bundle();
        r03.putLong("user_id", this.profileActivity.getUserConfig().getClientUserId());
        r03.putInt("message_id", r129.getId());
        AnonymousClass23 r14 = new AnonymousClass23(this, r03, r1113);
        r14.setHighlightMessageId(r129.getId());
        this.profileActivity.presentFragment(r14);
        return;
    L192:
        return;
    L201:
        return;
    L133:
        if (this.isActionModeShowed == true) goto L135;
        Bundle r1114 = new Bundle();
        if (r17 >= 0) goto L141;
        return;
    L141:
        if (r17 >= SavedDialogsAdapter.m12980$$Nest$fgetdialogs(this.savedDialogsAdapter).size()) goto L202;
        SavedMessagesController.SavedDialog r1210 = (SavedMessagesController.SavedDialog) SavedDialogsAdapter.m12980$$Nest$fgetdialogs(this.savedDialogsAdapter).get(r17);
        r1114.putLong("user_id", this.profileActivity.getUserConfig().getClientUserId());
        r1114.putInt("chatMode", 3);
        ChatActivity r04 = new ChatActivity(r1114);
        r04.setSavedDialog(r1210.dialogId);
        this.profileActivity.presentFragment(r04);
        return;
    L202:
        return;
    L135:
        if (this.savedDialogsAdapter.itemTouchHelper.isIdle() == false) goto L193;
        this.savedDialogsAdapter.select(r16);
        return;
    L193:
        return;
    L146:
        if (r82 == 15) goto L148;
        return;
    L148:
        if ((r16 instanceof ChatMessageCell) == false) goto L196;
        ChatMessageCell r1211 = (ChatMessageCell) r16;
        MessageObject r34 = r1211.getMessageObject();
        MediaPage.m12961$$Nest$fgetlistView(r11).stopScroll();
        int r52 = this.profileActivity.getCurrentAccount();
        TLRPC.Chat r6 = this.profileActivity.getMessagesController().getChat(Long.valueOf(r13));
        ItemOptions r1115 = ItemOptions.makeOptions(r11, r15, r1211);
        r1115.offsetByContainer();
        if (r34.isOutOwner() == false) goto L152;
        r1 = 5;
    L152:
        r1115.setGravity(r1);
        r1115.add(R.drawable.msg_message, LocaleController.getString(R.string.AccDescrGoToMessage), new SharedMediaLayout$$ExternalSyntheticLambda25(this, r13, r34));
        if (r34.isPollClosed() == false) goto L155;
    L180:
        r1115.show();
        return;
    L155:
        if (r34.canUnvote() == false) goto L158;
        r1115.add(R.drawable.msg_unvote, LocaleController.getString(R.string.Unvote), new SharedMediaLayout$$ExternalSyntheticLambda26(this, r15, r52, r34));
    L158:
        if (r34.isForwarded() == true) goto L180;
        if (r34.isOut() == false) goto L166;
        if (ChatObject.isChannel(r6) == true) goto L164;
    L175:
        int r1212 = R.drawable.msg_pollstop;
        if (r34.isQuiz() == false) goto L178;
        int r110 = R.string.StopQuiz;
    L179:
        r1115.add(r1212, LocaleController.getString(r110), new SharedMediaLayout$$ExternalSyntheticLambda27(this, r15, r34, r52));
        goto L180
    L178:
        r110 = R.string.StopPoll;
        goto L179
    L164:
        if (r6.megagroup == true) goto L175;
    L166:
        if (ChatObject.isChannel(r6) == false) goto L180;
        if (r6.megagroup == true) goto L180;
        if (r6.creator == true) goto L175;
        TLRPC.TL_chatAdminRights r1213 = r6.admin_rights;
        if (r1213 == null) goto L180;
        if (r1213.edit_messages == false) goto L180;
    L196:
        return;
    L96:
        if ((r16 instanceof SharedPhotoVideoCell2) == false) goto L101;
        MessageObject r35 = ((SharedPhotoVideoCell2) r16).getMessageObject();
        if (r35 == null) goto L187;
        onItemClick(r17, r16, r35, 0, r11.selectedType);
        return;
    L187:
        return;
    L62:
        if ((r16 instanceof SharedAudioCell) == false) goto L66;
        onItemClick(r17, r16, ((SharedAudioCell) r16).getMessage(), 0, r11.selectedType);
        return;
    L48:
        if ((r16 instanceof SharedDocumentCell) == false) goto L51;
        onItemClick(r17, r16, ((SharedDocumentCell) r16).getMessage(), 0, r11.selectedType);
    }

    private /* synthetic */ void lambda$new$33() {
        this.topLayoutPadding = (int) this.topPanelLayout.getAnimatedHeightWithPadding(AndroidUtilities.dp(14.0f));
        ProfileGiftsContainer r0 = this.giftsContainer;
        if (r0 == null) goto L5;
        r0.setPaddingTop(AndroidUtilities.dp(48.0f) + ((int) this.topPanelLayout.getAnimatedHeightWithPadding(AndroidUtilities.dp(7.0f))));
    L5:
        MediaPage[] r02 = this.mediaPages;
        if (r02 == null) goto L13;
        int r1 = r02.length;
        int r2 = 0;
    L8:
        if (r2 >= r1) goto L17;
        MediaPage r3 = r02[r2];
        if (r3 == null) goto L12;
        int r4 = MediaPage.m12961$$Nest$fgetlistView(r3).getPaddingTop();
        InternalListView r5 = MediaPage.m12961$$Nest$fgetlistView(r3);
        int r6 = MediaPage.m12961$$Nest$fgetlistView(r3).getPaddingLeft();
        int r7 = getPagePaddingTop(r3.selectedType);
        int r8 = MediaPage.m12961$$Nest$fgetlistView(r3).getPaddingRight();
        InternalListView r9 = MediaPage.m12961$$Nest$fgetlistView(r3);
        int r10 = getPagePaddingBottom(isStoriesView());
        r9.hintPaddingBottom = r10;
        r5.setPadding(r6, r7, r8, r10);
        AndroidUtilities.doOnLayout(MediaPage.m12961$$Nest$fgetlistView(r3), new SharedMediaLayout$$ExternalSyntheticLambda23(r3, r4 - MediaPage.m12961$$Nest$fgetlistView(r3).getPaddingTop()));
    L12:
        r2 = r2 + 1;
        goto L8
    L17:
        return;
    }

    private /* synthetic */ void lambda$new$34(boolean r1, boolean r2) {
        if (r1 == true) goto L4;
        requestLayout();
    L4:
        setVisibleHeight(this.lastVisibleHeight);
    }

    private /* synthetic */ void lambda$new$35(Canvas r6, RectF r7) {
        MediaPage[] r0 = this.mediaPages;
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L8;
        MediaPage r3 = r0[r2];
        if (MediaPage.m12958$$Nest$fgetiBlur3Capture(r3) == null) goto L7;
        MediaPage.m12958$$Nest$fgetiBlur3Capture(r3).capture(r6, r7);
    L7:
        r2 = r2 + 1;
        goto L3
    L8:
        ProfileGiftsContainer r5 = this.giftsContainer;
        if (r5 == null) goto L17;
        IBlur3Capture r52 = r5.iBlur3Capture;
        if (r52 == null) goto L18;
        r52.capture(r6, r7);
        return;
    L18:
        return;
    }

    private /* synthetic */ void lambda$new$4(View r1) {
        closeActionMode();
    }

    private /* synthetic */ void lambda$new$5(View r2) {
        onActionBarItemClick(r2, 102);
    }

    private /* synthetic */ void lambda$new$6(View r2) {
        onActionBarItemClick(r2, 100);
    }

    private /* synthetic */ boolean lambda$new$7(View r2) {
        onActionBarItemClick(r2, 105);
        return true;
    }

    private /* synthetic */ void lambda$new$8(View r2) {
        onActionBarItemClick(r2, 103);
    }

    private /* synthetic */ void lambda$new$9(View r2) {
        onActionBarItemClick(r2, 104);
    }

    private /* synthetic */ void lambda$onActionBarItemClick$43(StoriesController.BotPreviewsList r3, ArrayList r4, AlertDialog r5, int r6) {
        r3.delete(r4);
        BulletinFactory.of(this.profileActivity).createSimpleBulletin(R.raw.ic_delete, LocaleController.formatPluralString("BotPreviewsDeleted", r4.size(), new Object[0])).show();
        closeActionMode(false);
    }

    private /* synthetic */ void lambda$onActionBarItemClick$45(ArrayList r4, AlertDialog r5, int r6) {
        this.profileActivity.getMessagesController().getStoriesController().deleteStories(this.dialog_id, r4);
        BulletinFactory.of(this.profileActivity).createSimpleBulletin(R.raw.ic_delete, LocaleController.formatPluralString("StoriesDeleted", r4.size(), new Object[0])).show();
        closeActionMode(false);
    }

    private /* synthetic */ void lambda$onActionBarItemClick$47(ArrayList r3, AlertDialog r4, int r5) {
        int r42 = 0;
    L4:
        if (r42 >= r3.size()) goto L6;
        long r0 = ((Long) r3.get(r42)).longValue();
        this.profileActivity.getMessagesController().deleteSavedDialog(r0);
        r42 = r42 + 1;
        goto L4
    L6:
        closeActionMode();
    }

    private /* synthetic */ void lambda$onActionBarItemClick$48() {
        showActionMode(false);
        this.actionBar.closeSearchField();
        this.cantDeleteMessagesCount = 0;
    }

    private /* synthetic */ boolean lambda$onActionBarItemClick$49(boolean r32, DialogsActivity r33, ArrayList r34, CharSequence r35, boolean r36, boolean r37, int r38, int r39, TopicsFragment r40) {
        boolean r5 = r32;
        ArrayList<MessageObject> r2 = new ArrayList();
        int r1 = 1;
    L3:
        int r14 = 0;
        if (r1 < 0) goto L15;
        ArrayList r3 = new ArrayList();
        int r4 = 0;
    L7:
        if (r4 >= this.selectedFiles[r1].size()) goto L9;
        r3.add(Integer.valueOf(this.selectedFiles[r1].keyAt(r4)));
        r4 = r4 + 1;
        goto L7
    L9:
        Collections.sort(r3);
        int r42 = r3.size();
    L10:
        if (r14 >= r42) goto L14;
        Object r6 = r3.get(r14);
        r14 = r14 + 1;
        Integer r62 = (Integer) r6;
        if (r62.intValue() <= 0) goto L10;
        r2.add(this.selectedFiles[r1].get(r62.intValue()));
        goto L10
    L14:
        this.selectedFiles[r1].clear();
        r1 = r1 - 1;
        goto L3
    L15:
        this.cantDeleteMessagesCount = 0;
        showActionMode(false);
        SavedDialogsAdapter r12 = this.savedDialogsAdapter;
        if (r12 == null) goto L19;
        r12.unselectAll();
    L19:
        if (r34.size() <= 1) goto L21;
    L35:
        updateRowsSelection(true);
        int r15 = 0;
    L37:
        if (r15 >= r34.size()) goto L43;
        long r310 = ((MessagesStorage.TopicKey) r34.get(r15)).dialogId;
        if (r35 == null) goto L41;
        long r17 = r310;
        this.profileActivity.getSendMessagesHelper().sendMessage(SendMessagesHelper.SendMessageParams.of(r35.toString(), r17, null, null, null, true, null, null, null, true, 0, 0, null, false));
    L42:
        this.profileActivity.getSendMessagesHelper().sendMessage(r2, r17, r5, false, true, 0, 0);
        r15 = r15 + 1;
        r5 = r32;
        goto L37
    L41:
        r17 = r310;
        goto L42
    L43:
        r33.finishFragment();
        BaseFragment r0 = this.profileActivity;
        if ((r0 instanceof ProfileActivity) == false) goto L46;
        UndoView r02 = ((ProfileActivity) r0).getUndoView();
    L47:
        if (r02 != null) goto L49;
    L52:
        return true;
    L49:
        if (r34.size() != 1) goto L51;
        r02.showWithAction(((MessagesStorage.TopicKey) r34.get(0)).dialogId, 53, Integer.valueOf(r2.size()));
        goto L52
    L51:
        r02.showWithAction(0, 53, Integer.valueOf(r2.size()), Integer.valueOf(r34.size()), null, null);
        goto L52
    L46:
        r02 = null;
        goto L47
    L21:
        if (((MessagesStorage.TopicKey) r34.get(0)).dialogId == this.profileActivity.getUserConfig().getClientUserId()) goto L35;
        if (r35 != null) goto L35;
        long r311 = ((MessagesStorage.TopicKey) r34.get(0)).dialogId;
        Bundle r13 = new Bundle();
        r13.putBoolean("scrollToTopOnResume", true);
        if (DialogObject.isEncryptedDialog(r311) == false) goto L28;
        r13.putInt("enc_id", DialogObject.getEncryptedChatId(r311));
    L34:
        this.profileActivity.getNotificationCenter().postNotificationName(NotificationCenter.closeChats, new Object[0]);
        r13.putBoolean("forward_noquote", r5);
        ChatActivity r03 = new ChatActivity(r13);
        ForumUtilities.applyTopic(r03, (MessagesStorage.TopicKey) r34.get(0));
        r33.presentFragment(r03, true);
        r03.setForwardParams(r5);
        r03.showFieldPanelForForward(true, r2);
        goto L52
    L28:
        if (DialogObject.isUserDialog(r311) == false) goto L30;
        r13.putLong("user_id", r311);
    L32:
        if (this.profileActivity.getMessagesController().checkCanOpenChat(r13, r33) == true) goto L34;
        return true;
    L30:
        r13.putLong("chat_id", -r311);
        goto L32
    }

    private /* synthetic */ void lambda$onItemLongClick$54(TL_stories.StoryItem r5, StoriesController.StoryAlbum r6) {
        getStoriesController().addStoryToAlbum(this.dialog_id, r6.album_id, r5);
        onStoryAlbumCreate(r6);
    }

    private /* synthetic */ void lambda$onItemLongClick$55(TL_stories.StoryItem r5, String r6) {
        getStoriesController().createAlbum(this.dialog_id, r6, new SharedMediaLayout$$ExternalSyntheticLambda81(this, r5));
    }

    private /* synthetic */ void lambda$onItemLongClick$56(TL_stories.StoryItem r4, ItemOptions r5) {
        AlertsCreator.createStoriesAlbumEnterNameForCreate(getContext(), null, this.resourcesProvider, new SharedMediaLayout$$ExternalSyntheticLambda69(this, r4));
        r5.dismiss();
    }

    private /* synthetic */ void lambda$onItemLongClick$57(HashSet r4, TL_stories.StoryItem r5, ItemOptions r6, StoriesController.StoryAlbum r7) {
        if (r4.contains(Integer.valueOf(r7.album_id)) == false) goto L5;
        getStoriesController().addStoryToAlbum(this.dialog_id, r7.album_id, r5);
        String r42 = LocaleController.formatString(R.string.StoryAddedToAlbumX, new Object[]{r7.title});
    L6:
        BulletinFactory.of(this.profileActivity).createSimpleBulletin(R.raw.contact_check, AndroidUtilities.replaceTags(r42)).show();
        r6.dismiss();
        return;
    L5:
        getStoriesController().removeStoryFromAlbum(this.dialog_id, r7.album_id, r5);
        r42 = LocaleController.formatString(R.string.StoryRemovedFromAlbumX, new Object[]{r7.title});
        goto L6
    }

    private /* synthetic */ void lambda$onItemLongClick$59(MessageObject r2, View r3, int r4) {
        onItemLongClick(r2, r3, r4, false);
    }

    private /* synthetic */ void lambda$onItemLongClick$60(int r1) {
        startAlbumsReorder(r1);
    }

    private /* synthetic */ void lambda$onItemLongClick$61(int r4, TL_stories.StoryItem r5) {
        getStoriesController().addStoryToAlbum(this.dialog_id, r4, r5);
    }

    private /* synthetic */ void lambda$onItemLongClick$62(int r5, TL_stories.StoryItem r6, String r7) {
        SharedMediaLayout$$ExternalSyntheticLambda65 r0 = new SharedMediaLayout$$ExternalSyntheticLambda65(this, r5, r6);
        getStoriesController().removeStoryFromAlbum(this.dialog_id, r5, r6);
        BulletinFactory.of(this.profileActivity).createSimpleBulletin(R.raw.chats_archived, AndroidUtilities.replaceTags(LocaleController.formatPluralString("StoryRemovedFromAlbumTitle", 1, new Object[]{r7})), LocaleController.getString(R.string.UndoNoCaps), r0).show();
    }

    private /* synthetic */ void lambda$onItemLongClick$63(TL_stories.StoryItem r2) {
        pinOnUnpinStories(new ArrayList(Collections.singletonList(Integer.valueOf(r2.id))), false);
    }

    private /* synthetic */ void lambda$onItemLongClick$64(TL_stories.StoryItem r2) {
        pinOnUnpinStories(new ArrayList(Collections.singletonList(Integer.valueOf(r2.id))), true);
    }

    private /* synthetic */ void lambda$onItemLongClick$65(TL_stories.StoryItem r7) {
        getStoriesController().updateStoriesPinned(this.dialog_id, new ArrayList(Collections.singletonList(r7)), false, null);
        BulletinFactory.of(this.profileActivity).createSimpleBulletin(R.raw.chats_archived, LocaleController.formatPluralString("StoryArchived", 1, new Object[0]), 5000).show();
    }

    private /* synthetic */ void lambda$onItemLongClick$66(TL_stories.StoryItem r4, AlertDialog r5, int r6) {
        ArrayList<TL_stories.StoryItem> r52 = new ArrayList(1);
        r52.add(r4);
        this.profileActivity.getMessagesController().getStoriesController().deleteStories(this.dialog_id, r52);
        BulletinFactory.of(this.profileActivity).createSimpleBulletin(R.raw.ic_delete, LocaleController.formatPluralString("StoriesDeleted", 1, new Object[0])).show();
        closeActionMode(false);
    }

    private /* synthetic */ void lambda$onItemLongClick$68(TL_stories.StoryItem r5) {
        AlertDialog.Builder r0 = new AlertDialog.Builder(getContext(), this.resourcesProvider);
        r0.setTitle(LocaleController.getString(R.string.DeleteStoryTitle));
        r0.setMessage(LocaleController.formatPluralString("DeleteStoriesSubtitle", 1, new Object[0]));
        r0.setPositiveButton(LocaleController.getString(R.string.Delete), new SharedMediaLayout$$ExternalSyntheticLambda70(this, r5));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), new SharedMediaLayout$$ExternalSyntheticLambda71());
        AlertDialog r4 = r0.create();
        r4.show();
        r4.redPositive();
    }

    private /* synthetic */ void lambda$onStoryAlbumCreate$84(StoriesController.StoryAlbum r1) {
        ProfileStoriesCollectionTabs r0 = this.storiesContainer;
        if (r0 == null) goto L6;
        r0.scrollToAlbumId(r1.album_id);
        return;
    }

    private /* synthetic */ void lambda$openAddStoriesToAlbumSheet$77(long r1, int r3, ArrayList r4) {
        getStoriesController().addStoriesToAlbum(r1, r3, r4);
    }

    private /* synthetic */ void lambda$openDeleteStoriesAlbumAlert$76(long r1, int r3) {
        getStoriesController().removeAlbum(r1, r3);
    }

    private /* synthetic */ void lambda$openRenameStoriesAlbumAlert$75(long r1, int r3, String r4) {
        getStoriesController().renameAlbum(r1, r3, r4);
    }

    private /* synthetic */ void lambda$parseMarkdownAsync$70(AlertDialog r1, boolean[] r2, boolean r3, MessageObject r4, TLRPC.WebPage r5) {
        r1.dismiss();     // Catch: Throwable -> L13
    L4:
        if (r2[0] == true) goto L17;
        if (r3 == false) goto L11;
        TLRPC.MessageMedia r22 = r4.messageOwner.media;
        if (r22 == null) goto L10;
        r22.webpage = r5;
    L10:
        this.profileActivity.createArticleViewer(false).open(r4);
        return;
    L11:
        AndroidUtilities.openDocument(r4, this.profileActivity.getParentActivity(), this.profileActivity);
        return;
    }

    private /* synthetic */ void lambda$parseMarkdownAsync$71(MessageObject r10, AlertDialog r11, boolean[] r12) {
        Throwable r1 = null;
        TLRPC.WebPage r8 = MarkdownParser.fromMarkdown(r10);     // Catch: Throwable -> L5
    L7:
        if (r1 != null) goto L11;
        if (r8 == null) goto L11;
        boolean r0 = true;
    L12:
        AndroidUtilities.runOnUIThread(new SharedMediaLayout$$ExternalSyntheticLambda77(this, r11, r12, r0, r10, r8));
        return;
    L11:
        r0 = false;
    L5:
        th = move-exception;
        FileLog.e(th);
        r8 = null;
        r1 = th;
        goto L7
    }

    private /* synthetic */ void lambda$postNotifyDataSetChangedSafely$0(RecyclerView.Adapter r1) {
        notifyDataSetChangedSafely(r1);
    }

    private /* synthetic */ void lambda$stopScroll$50(ValueAnimator r1) {
        onTabProgress(getTabProgress());
    }

    private /* synthetic */ void lambda$switchToCurrentSelectedMode$52(boolean r3, int r4, View r5) {
        BaseFragment r52 = this.profileActivity;
        if (r3 == false) goto L6;
        openAddStoriesToAlbumSheet(r52, this.dialog_id, r4);
        return;
    L6:
        r52.getMessagesController().getMainSettings().edit().putBoolean("story_keep", true).apply();
        StoryRecorder.getInstance(this.profileActivity.getParentActivity(), this.profileActivity.getCurrentAccount()).open(null);
    }

    private /* synthetic */ void lambda$switchToCurrentSelectedMode$53(View r3) {
        this.profileActivity.getMessagesController().getMainSettings().edit().putBoolean("story_keep", true).apply();
        StoryRecorder.getInstance(this.profileActivity.getParentActivity(), this.profileActivity.getCurrentAccount()).open(null);
    }

    private /* synthetic */ void lambda$updateSearchItemIconAnimated$1(boolean r1) {
        if (r1 == true) goto L5;
        this.searchItemIcon.setVisibility(8);
        return;
    }

    private void loadFastScrollData(boolean r11) {
        if (this.topicId == 0) goto L5;
        return;
    L5:
        if (isSearchingStories() == true) goto L42;
        int r1 = 0;
    L8:
        int[] r4 = supportedFastScrollTypes;
        if (r1 >= r4.length) goto L43;
        int r42 = r4[r1];
        if (this.sharedMediaData[r42].fastScrollDataLoaded == false) goto L14;
        if (r11 == true) goto L14;
        return;
    L14:
        if (DialogObject.isEncryptedDialog(this.dialog_id) == true) goto L45;
        this.sharedMediaData[r42].fastScrollDataLoaded = false;
        TLRPC.TL_messages_getSearchResultsPositions r5 = new TLRPC.TL_messages_getSearchResultsPositions();
        if (r42 != 0) goto L24;
        int r8 = this.sharedMediaData[r42].filterType;
        if (r8 != 1) goto L21;
        r5.filter = new TLRPC.TL_inputMessagesFilterPhotos();
    L29:
        r5.limit = 100;
        r5.peer = this.profileActivity.getMessagesController().getInputPeer(this.dialog_id);
        if (this.topicId == 0) goto L34;
        if (this.profileActivity.getUserConfig().getClientUserId() != this.dialog_id) goto L34;
        r5.flags |= 4;
        r5.saved_peer_id = this.profileActivity.getMessagesController().getInputPeer(this.topicId);
    L34:
        ConnectionsManager.getInstance(this.profileActivity.getCurrentAccount()).bindRequestToGuid(ConnectionsManager.getInstance(this.profileActivity.getCurrentAccount()).sendRequest(r5, new SharedMediaLayout$$ExternalSyntheticLambda0(this, this.sharedMediaData[r42].requestIndex, r42)), this.profileActivity.getClassGuid());
        r1 = r1 + 1;
        goto L8
    L21:
        if (r8 != 2) goto L23;
        r5.filter = new TLRPC.TL_inputMessagesFilterVideo();
        goto L29
    L23:
        r5.filter = new TLRPC.TL_inputMessagesFilterPhotoVideo();
        goto L29
    L24:
        if (r42 != 1) goto L26;
        r5.filter = new TLRPC.TL_inputMessagesFilterDocument();
        goto L29
    L26:
        if (r42 != 2) goto L28;
        r5.filter = new TLRPC.TL_inputMessagesFilterRoundVoice();
        goto L29
    L28:
        r5.filter = new TLRPC.TL_inputMessagesFilterMusic();
        goto L29
    L45:
        return;
    L43:
        return;
    }

    private void loadFromStart(int r19) {
        int r2 = 2;
        if (r19 != 0) goto L11;
        int r4 = this.sharedMediaData[0].filterType;
        if (r4 != 1) goto L8;
        r2 = 6;
    L7:
        int r10 = r2;
    L22:
        this.sharedMediaData[r19].loading = true;
        this.profileActivity.getMediaDataController().loadMedia(this.dialog_id, 50, 0, this.sharedMediaData[r19].min_id, r10, this.topicId, 1, this.profileActivity.getClassGuid(), this.sharedMediaData[r19].requestIndex, null, null);
        return;
    L8:
        if (r4 != 2) goto L10;
        r2 = 7;
        goto L7
    L10:
        r10 = 0;
        goto L22
    L11:
        if (r19 != 1) goto L13;
        r10 = 1;
        goto L22
    L13:
        if (r19 == 2) goto L7;
        r2 = 4;
        if (r19 == 4) goto L7;
        r2 = 5;
        if (r19 == 5) goto L7;
        r2 = 3;
        goto L7
    }

    private void notifyDataSetChangedSafely(RecyclerView.Adapter<?> r1) {
        if (r1 != null) goto L5;
        return;
    L5:
        if (postNotifyDataSetChangedSafely(r1) == false) goto L7;
        return;
    L7:
        r1.notifyDataSetChanged();
    }

    private void onItemClick(int r14, View r15, MessageObject r16, int r17, int r18) {
        if (r16 != null) goto L5;
        return;
    L5:
        if (this.photoVideoChangeColumnsAnimation == true) goto L210;
        ProfileStoriesCollectionTabs r4 = this.storiesContainer;
        if (r4 != null) goto L10;
    L12:
        String r5 = null;
        StoriesController.StoriesList r52 = null;
        r5 = null;
        int r7 = 0;
        boolean r72 = false;
        boolean r73 = false;
        boolean r74 = false;
        boolean r75 = false;
        boolean r76 = false;
        boolean r77 = false;
        r7 = 0;
        if (this.isActionModeShowed == false) goto L103;
        int r142 = 8;
        if (r18 != 8) goto L20;
        if (canEditStories() == true) goto L20;
        return;
    L20:
        if (r16.getDialogId() != this.dialog_id) goto L22;
        char r3 = 0;
    L23:
        int r42 = this.selectedFiles[r3].indexOfKey(r16.getId());
        SparseArray<MessageObject>[] r8 = this.selectedFiles;
        if (r42 < 0) goto L29;
        r8[r3].remove(r16.getId());
        if (r16.canDeleteMessage(false, null) == true) goto L34;
        this.cantDeleteMessagesCount--;
    L34:
        onActionModeSelectedUpdate(this.selectedFiles[0]);
        if (this.selectedFiles[0].size() == 0) goto L37;
    L39:
        this.selectedMessagesCountTextView.setNumber(this.selectedFiles[0].size() + this.selectedFiles[1].size(), true);
        ActionBarMenuItem r43 = this.deleteItem;
        if (this.cantDeleteMessagesCount != 0) goto L42;
        int r53 = 0;
    L43:
        r43.setVisibility(r53);
        ActionBarMenuItem r44 = this.gotoItem;
        if (r44 != null) goto L46;
    L56:
        ActionBarMenuItem r45 = this.forwardItem;
        if (r45 != null) goto L59;
    L66:
        updateStoriesPinButton();
    L67:
        this.scrolling = false;
        if ((r15 instanceof SharedDocumentCell) == false) goto L74;
        SharedDocumentCell r143 = (SharedDocumentCell) r15;
        if (this.selectedFiles[r3].indexOfKey(r16.getId()) < 0) goto L72;
        r72 = true;
    L72:
        r143.setChecked(r72, true);
    L205:
        updateForwardItem();
        return;
    L74:
        if ((r15 instanceof SharedPhotoVideoCell) == false) goto L80;
        SharedPhotoVideoCell r144 = (SharedPhotoVideoCell) r15;
        if (this.selectedFiles[r3].indexOfKey(r16.getId()) < 0) goto L78;
        r73 = true;
    L78:
        r144.setChecked(r17, r73, true);
        goto L205
    L80:
        if ((r15 instanceof SharedLinkCell) == false) goto L86;
        SharedLinkCell r145 = (SharedLinkCell) r15;
        if (this.selectedFiles[r3].indexOfKey(r16.getId()) < 0) goto L84;
        r74 = true;
    L84:
        r145.setChecked(r74, true);
        goto L205
    L86:
        if ((r15 instanceof SharedAudioCell) == false) goto L92;
        SharedAudioCell r146 = (SharedAudioCell) r15;
        if (this.selectedFiles[r3].indexOfKey(r16.getId()) < 0) goto L90;
        r75 = true;
    L90:
        r146.setChecked(r75, true);
        goto L205
    L92:
        if ((r15 instanceof ContextLinkCell) == false) goto L98;
        ContextLinkCell r147 = (ContextLinkCell) r15;
        if (this.selectedFiles[r3].indexOfKey(r16.getId()) < 0) goto L96;
        r76 = true;
    L96:
        r147.setChecked(r76, true);
        goto L205
    L98:
        if ((r15 instanceof SharedPhotoVideoCell2) == false) goto L205;
        SharedPhotoVideoCell2 r148 = (SharedPhotoVideoCell2) r15;
        if (this.selectedFiles[r3].indexOfKey(r16.getId()) < 0) goto L102;
        r77 = true;
    L102:
        r148.setChecked(r77, true);
        goto L205
    L59:
        if (getClosestTab() != 8) goto L61;
    L65:
        r45.setVisibility(r142);
        goto L66
    L61:
        if (getClosestTab() == 13) goto L65;
        if (getClosestTab() == 14) goto L65;
        r142 = 0;
        goto L65
    L46:
        if (getClosestTab() != 8) goto L48;
    L54:
        int r9 = 8;
    L55:
        r44.setVisibility(r9);
        goto L56
    L48:
        if (getClosestTab() == 13) goto L54;
        if (getClosestTab() == 14) goto L54;
        if (this.selectedFiles[0].size() != 1) goto L54;
        r9 = 0;
        goto L55
    L42:
        r53 = 8;
        goto L43
    L37:
        if (this.selectedFiles[1].size() != 0) goto L39;
        showActionMode(false);
        goto L67
    L29:
        if ((r8[0].size() + this.selectedFiles[1].size()) >= 100) goto L213;
        this.selectedFiles[r3].put(r16.getId(), r16);
        if (r16.canDeleteMessage(false, null) == true) goto L34;
        this.cantDeleteMessagesCount++;
        goto L34
    L213:
        return;
    L22:
        r3 = 1;
        goto L23
    L103:
        if (r18 != 0) goto L110;
        int r149 = r14 - SharedMediaData.m12992$$Nest$fgetstartOffset(this.sharedMediaData[r18]);
        if (r149 < 0) goto L205;
        if (r149 >= this.sharedMediaData[r18].messages.size()) goto L205;
        PhotoViewer.getInstance().setParentActivity(this.profileActivity);
        PhotoViewer.getInstance().openPhoto(this.sharedMediaData[r18].messages, r149, this.dialog_id, this.mergeDialogId, this.topicId, this.provider);
        goto L205
    L110:
        if (r18 == 2) goto L203;
        if (r18 == 4) goto L203;
        if (r18 != 5) goto L120;
        PhotoViewer.getInstance().setParentActivity(this.profileActivity);
        int r2 = this.sharedMediaData[r18].messages.indexOf(r16);
        if (r2 >= 0) goto L119;
        ArrayList<MessageObject> r46 = new ArrayList();
        r46.add(r16);
        PhotoViewer.getInstance().openPhoto(r46, 0, 0, 0, 0, this.provider);
        goto L205
    L119:
        PhotoViewer.getInstance().openPhoto(this.sharedMediaData[r18].messages, r2, this.dialog_id, this.mergeDialogId, this.topicId, this.provider);
        goto L205
    L120:
        if (r18 != 1) goto L155;
        if ((r15 instanceof SharedDocumentCell) == false) goto L205;
        SharedDocumentCell r1410 = (SharedDocumentCell) r15;
        TLRPC.Document r0 = r16.getDocument();
        if (r1410.isLoaded() == false) goto L151;
        if (PreferencesUtils.getInstance().isBackup(r16) == false) goto L130;
        new BackupBottomSheet(this.profileActivity, r16).showIfPossible();
        return;
    L130:
        if (PluginsController.isPlugin(r16) == false) goto L133;
        PluginsController.getInstance().showInstallDialog(this.profileActivity, r16);
        return;
    L133:
        IconManager r1411 = IconManager.INSTANCE;
        if (r1411.isIconPack(r16) == false) goto L138;
        r1411.handleIconPack(this.profileActivity, r16);
        return;
    L138:
        if (r16.canPreviewDocument() == false) goto L146;
        PhotoViewer.getInstance().setParentActivity(this.profileActivity);
        int r22 = this.sharedMediaData[r18].messages.indexOf(r16);
        if (r22 >= 0) goto L143;
        ArrayList<MessageObject> r47 = new ArrayList();
        r47.add(r16);
        PhotoViewer.getInstance().openPhoto(r47, 0, 0, 0, 0, this.provider);
        return;
    L143:
        PhotoViewer.getInstance().openPhoto(this.sharedMediaData[r18].messages, r22, this.dialog_id, this.mergeDialogId, this.topicId, this.provider);
        return;
    L146:
        if (MarkdownParser.isMarkdown(r16) == false) goto L149;
        parseMarkdownAsync(r16);
        return;
    L149:
        AndroidUtilities.openDocument(r16, this.profileActivity.getParentActivity(), this.profileActivity);
        goto L205
    L151:
        if (r1410.isLoading() == true) goto L153;
        MessageObject r1 = r1410.getMessage();
        r1.putInDownloadsStore = true;
        this.profileActivity.getFileLoader().loadFile(r0, r1, 0, 0);
        r1410.updateFileExistIcon(true);
        goto L205
    L153:
        this.profileActivity.getFileLoader().cancelLoadFile(r0);
        r1410.updateFileExistIcon(true);
        goto L205
    L155:
        if (r18 != 3) goto L190;
    L159:
        e = move-exception;
        FileLog.e(e);
        goto L205
    L157:
        if (MessageObject.getMedia(r16.messageOwner) == null) goto L161;
        TLRPC.WebPage r1412 = MessageObject.getMedia(r16.messageOwner).webpage;     // Catch: Exception -> L159
    L162:
        if (r1412 != null) goto L164;
    L183:
        if (r5 != null) goto L185;
        r5 = ((SharedLinkCell) r15).getLink(0);     // Catch: Exception -> L159
    L185:
        if (r5 == null) goto L205;
        openUrl(r5);     // Catch: Exception -> L159
        goto L205
    L164:
        if ((r1412 instanceof TLRPC.TL_webPageEmpty) == true) goto L183;
        if (r1412.cached_page == null) goto L176;
        LaunchActivity r1413 = LaunchActivity.instance;     // Catch: Exception -> L159
        if (r1413 != null) goto L170;
    L174:
        this.profileActivity.createArticleViewer(false).open(r16);     // Catch: Exception -> L159
        return;
    L170:
        if (r1413.getBottomSheetTabs() == null) goto L174;
        if (LaunchActivity.instance.getBottomSheetTabs().tryReopenTab(r16) == null) goto L174;
        return;
    L176:
        String r23 = r1412.embed_url;     // Catch: Exception -> L159
        if (r23 != null) goto L179;
    L182:
        r5 = r1412.url;     // Catch: Exception -> L159
        goto L183
    L179:
        if (r23.length() == 0) goto L182;
        openWebView(r1412, r16);     // Catch: Exception -> L159
        return;
    L161:
        r1412 = null;
        goto L162
    L190:
        if (isAnyStoryPageType(r18) == false) goto L205;
        StoriesAdapter r1414 = storyAlbums_getStoriesAdapterByTabType(r18);
        if (r1414 == null) goto L194;
        r52 = r1414.storiesList;
    L194:
        if (r52 == null) goto L214;
        StoryViewer r1415 = this.profileActivity.getOrCreateStoryViewer();
        Context r02 = getContext();
        int r12 = r16.getId();
        StoriesListPlaceProvider r24 = StoriesListPlaceProvider.of(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r17])).with(new SharedMediaLayout$$ExternalSyntheticLambda56(r52));
        BaseFragment r32 = this.profileActivity;
        if ((r32 instanceof ProfileActivity) == true) goto L199;
    L201:
        r1415.open(r02, r12, r52, r24.addBottomClip(r7));
        goto L205
    L199:
        if (((ProfileActivity) r32).myProfile == false) goto L201;
        r7 = AndroidUtilities.dp(68.0f);
        goto L201
    L214:
        return;
    L203:
        if ((r15 instanceof SharedAudioCell) == false) goto L205;
        ((SharedAudioCell) r15).didPressedButton();
        goto L205
    L10:
        if (r4.isReordering() == false) goto L12;
        return;
    }

    private boolean onItemLongClick(MessageObject r2, View r3, int r4) {
        return onItemLongClick(r2, r3, r4, true);
    }

    private boolean onItemLongClick(MessageObject r11, View r12, int r13, boolean r14) {
        if (this.isActionModeShowed == false) goto L5;
    L105:
        return false;
    L5:
        if (this.profileActivity.getParentActivity() == null) goto L105;
        if (r11 == null) goto L105;
        ProfileStoriesCollectionTabs r0 = this.storiesContainer;
        if (r0 != null) goto L11;
    L13:
        AndroidUtilities.hideKeyboard(this.profileActivity.getParentActivity().getCurrentFocus());
        int r02 = 8;
        if (r14 == true) goto L16;
    L46:
        SparseArray<MessageObject>[] r142 = this.selectedFiles;
        if (r11.getDialogId() != this.dialog_id) goto L49;
        char r3 = 0;
    L50:
        r142[r3].put(r11.getId(), r11);
        if (r11.canDeleteMessage(false, null) == true) goto L53;
        this.cantDeleteMessagesCount++;
    L53:
        ActionBarMenuItem r112 = this.deleteItem;
        if (this.cantDeleteMessagesCount != 0) goto L56;
        int r143 = 0;
    L57:
        r112.setVisibility(r143);
        ActionBarMenuItem r113 = this.gotoItem;
        if (r113 != null) goto L60;
    L68:
        ActionBarMenuItem r114 = this.forwardItem;
        if (r114 != null) goto L71;
    L78:
        this.selectedMessagesCountTextView.setNumber(1, false);
        AnimatorSet r115 = new AnimatorSet();
        ArrayList r144 = new ArrayList();
        int r03 = 0;
    L80:
        if (r03 >= this.actionModeViews.size()) goto L82;
        View r32 = this.actionModeViews.get(r03);
        AndroidUtilities.clearDrawableAnimation(r32);
        r144.add(ObjectAnimator.ofFloat(r32, View.SCALE_Y, new float[]{0.1f, 1.0f}));
        r03 = r03 + 1;
        goto L80
    L82:
        r115.playTogether(r144);
        r115.setDuration(250);
        r115.start();
        this.scrolling = false;
        if ((r12 instanceof SharedDocumentCell) == false) goto L86;
        ((SharedDocumentCell) r12).setChecked(true, true);
    L101:
        if (this.isActionModeShowed == true) goto L103;
        showActionMode(true);
    L103:
        onActionModeSelectedUpdate(this.selectedFiles[0]);
        updateForwardItem();
        return true;
    L86:
        if ((r12 instanceof SharedPhotoVideoCell) == false) goto L89;
        ((SharedPhotoVideoCell) r12).setChecked(r13, true, true);
        goto L101
    L89:
        if ((r12 instanceof SharedLinkCell) == false) goto L92;
        ((SharedLinkCell) r12).setChecked(true, true);
        goto L101
    L92:
        if ((r12 instanceof SharedAudioCell) == false) goto L95;
        ((SharedAudioCell) r12).setChecked(true, true);
        goto L101
    L95:
        if ((r12 instanceof ContextLinkCell) == false) goto L98;
        ((ContextLinkCell) r12).setChecked(true, true);
        goto L101
    L98:
        if ((r12 instanceof SharedPhotoVideoCell2) == false) goto L101;
        ((SharedPhotoVideoCell2) r12).setChecked(true, true);
        goto L101
    L71:
        if (getClosestTab() != 8) goto L73;
    L77:
        r114.setVisibility(r02);
        goto L78
    L73:
        if (getClosestTab() == 13) goto L77;
        if (getClosestTab() == 14) goto L77;
        r02 = 0;
        goto L77
    L60:
        if (getClosestTab() != 8) goto L62;
    L66:
        int r4 = 8;
    L67:
        r113.setVisibility(r4);
        goto L68
    L62:
        if (getClosestTab() == 13) goto L66;
        if (getClosestTab() == 14) goto L66;
        r4 = 0;
        goto L67
    L56:
        r143 = 8;
        goto L57
    L49:
        r3 = 1;
        goto L50
    L16:
        if (isStoryAlbumPageType(getClosestTab()) == true) goto L20;
        if (getClosestTab() != 8) goto L46;
    L20:
        if (isActionModeShown() == true) goto L46;
        if ((r12 instanceof SharedPhotoVideoCell2) == false) goto L24;
        ((SharedPhotoVideoCell2) r12).initFullSizeReceiver();
    L24:
        TL_stories.StoryItem r145 = r11.storyItem;
        if (r145 != null) goto L27;
        return false;
    L27:
        HashSet r5 = new HashSet();
        ArrayList<Integer> r1 = r145.albums;
        if (r1 == null) goto L30;
        r5.addAll(r1);
    L30:
        boolean r15 = isStoryAlbumPageType(getClosestTab());
        ItemOptions r9 = ItemOptions.makeOptions(this.profileActivity, r12, true);
        ItemOptions r33 = r9.makeSwipeback();
        r33.add(R.drawable.ic_ab_back, LocaleController.getString(R.string.Back), new RatePill$$ExternalSyntheticLambda1(r9));
        r33.addGap();
        ItemOptions.addAlbumsItemOptions(r33, getStoriesController().getStoryAlbumsList(this.dialog_id), r5, true, new SharedMediaLayout$$ExternalSyntheticLambda42(this, r145, r9), new SharedMediaLayout$$ExternalSyntheticLambda43(this, r5, r145, r9));
        r9.add(R.drawable.menu_album_add, LocaleController.getString(R.string.StoriesAlbumAddToAlbum), new SharedMediaLayout$$ExternalSyntheticLambda44(r9, r33));
        r9.addGap();
        r9.add(R.drawable.msg_select, LocaleController.getString(R.string.StoriesAlbumMenuSelect), new SharedMediaLayout$$ExternalSyntheticLambda45(this, r11, r12, r13));
        if (r15 == false) goto L34;
        int r116 = storyAlbums_getAlbumIdByTabType(getClosestTab());
        String r122 = getStoriesController().getAlbumName(this.dialog_id, r116);
        r9.add(R.drawable.tabs_reorder, LocaleController.getString(R.string.StoriesAlbumMenuReorder), new SharedMediaLayout$$ExternalSyntheticLambda46(this, r116));
        r9.add(R.drawable.msg_removefolder, LocaleController.getString(R.string.StoriesAlbumMenuRemoveFromAlbum), new SharedMediaLayout$$ExternalSyntheticLambda47(this, r116, r145, r122));
    L44:
        r9.add(R.drawable.msg_delete, LocaleController.getString(R.string.Delete), true, new SharedMediaLayout$$ExternalSyntheticLambda41(this, r145));
        r9.setGravity(3);
        r9.setBlur(true);
        r9.allowMoveScrim();
        r9.allowMoveScrimGravity(3);
        Point r117 = AndroidUtilities.displaySize;
        Point r123 = AndroidUtilities.displaySize;
        int r118 = Math.min((int) (Math.min(r117.x, r117.y) * 0.6777f), (int) (((Math.max(r123.x, r123.y) * 0.4333f) * 3.0f) / 4.0f));
        r9.animateToSize(r118, (r118 * 4) / 3);
        r9.setDrawScrim(true);
        r9.hideScrimUnder();
        r9.forceBottom(true);
        r9.show();
        return true;
    L34:
        if (getClosestTab() != 8) goto L43;
        StoriesAdapter r119 = this.storiesAdapter;
        if (r119 == null) goto L43;
        StoriesController.StoriesList r1110 = r119.storiesList;
        if (r1110 == null) goto L43;
        if (r1110.isPinned(r145.id) == false) goto L42;
        r9.add(R.drawable.chats_unpin, LocaleController.getString(R.string.StoriesAlbumMenuUnpin), new SharedMediaLayout$$ExternalSyntheticLambda48(this, r145));
        goto L43
    L42:
        r9.add(R.drawable.chats_pin, LocaleController.getString(R.string.StoriesAlbumMenuPin), new SharedMediaLayout$$ExternalSyntheticLambda49(this, r145));
    L43:
        r9.add(R.drawable.msg_archive, LocaleController.getString(R.string.StoriesAlbumMenuArchive), new SharedMediaLayout$$ExternalSyntheticLambda50(this, r145));
        goto L44
    L11:
        if (r0.isReordering() == false) goto L13;
        return false;
    }

    private void onStoryAlbumCreate(StoriesController.StoryAlbum r2) {
        AndroidUtilities.runOnUIThread(new SharedMediaLayout$$ExternalSyntheticLambda84(this, r2), 100);
    }

    private void openStoryTabIdPage(int r4, boolean r5) {
        MediaPage[] r0 = this.mediaPages;
        if (r0[0].selectedType != r4) goto L5;
        return;
    L5:
        MediaPage r02 = r0[1];
        r02.selectedType = r4;
        r02.setVisibility(0);
        hideFloatingDateView(true);
        switchToCurrentSelectedMode(true);
        this.animatingForward = r5;
        onSelectedTabChanged();
        animateSearchToOptions(!isSearchItemVisible(r4), true);
        updateOptionsSearch(true);
    }

    private void openUrl(String r2) {
        boolean r0 = AndroidUtilities.shouldShowUrlInAlert(r2);
        BaseFragment r1 = this.profileActivity;
        if (r0 == false) goto L6;
        AlertsCreator.showOpenUrlAlert(r1, r2, true, true);
        return;
    L6:
        Browser.openUrl(r1.getParentActivity(), r2);
    }

    private void openWebView(TLRPC.WebPage r11, MessageObject r12) {
        EmbedBottomSheet.show(this.profileActivity, r12, this.provider, r11.site_name, r11.description, r11.url, r11.embed_url, r11.embed_width, r11.embed_height, false);
    }

    private void parseMarkdownAsync(MessageObject r6) {
        BaseFragment r0 = this.profileActivity;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getParentActivity() == null) goto L10;
        AlertDialog r02 = new AlertDialog(this.profileActivity.getParentActivity(), 3, this.profileActivity.getResourceProvider());
        r02.setCanceledOnTouchOutside(false);
        boolean[] r2 = {false};
        r02.setOnCancelListener(new SharedMediaLayout$$ExternalSyntheticLambda67(r2));
        r02.showDelayed(150);
        new Thread(new SharedMediaLayout$$ExternalSyntheticLambda68(this, r6, r02, r2)).start();
        return;
    }

    private void pinOnUnpinStories(ArrayList<Integer> r5, boolean r6) {
        StoriesAdapter r0 = this.storiesAdapter;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.storiesList != null) goto L8;
        return;
    L8:
        if (r6 == false) goto L14;
        if (r5.size() <= this.profileActivity.getMessagesController().storiesPinnedToTopCountMax) goto L14;
        BulletinFactory.of(this.profileActivity).createSimpleBulletin(R.raw.chats_infotip, AndroidUtilities.replaceTags(LocaleController.formatPluralString("StoriesPinLimit", this.profileActivity.getMessagesController().storiesPinnedToTopCountMax, new Object[0]))).show();
        return;
    L14:
        if (this.storiesAdapter.storiesList.updatePinned(r5, r6) == false) goto L17;
        BulletinFactory.of(this.profileActivity).createSimpleBulletin(R.raw.chats_infotip, AndroidUtilities.replaceTags(LocaleController.formatPluralString("StoriesPinLimit", this.profileActivity.getMessagesController().storiesPinnedToTopCountMax, new Object[0]))).show();
        return;
    L17:
        BaseFragment r4 = this.profileActivity;
        if (r6 == false) goto L21;
        BulletinFactory.of(r4).createSimpleBulletin(R.raw.ic_pin, AndroidUtilities.replaceTags(LocaleController.formatPluralString("StoriesPinned", r5.size(), new Object[0])), LocaleController.formatPluralString("StoriesPinnedText", r5.size(), new Object[0])).show();
        return;
    L21:
        BulletinFactory.of(r4).createSimpleBulletin(R.raw.ic_unpin, AndroidUtilities.replaceTags(LocaleController.formatPluralString("StoriesUnpinned", r5.size(), new Object[0]))).show();
    }

    private boolean postNotifyDataSetChangedSafely(RecyclerView.Adapter<?> r8) {
        if (r8 != null) goto L5;
        return false;
    L5:
        MediaPage[] r1 = this.mediaPages;
        int r2 = r1.length;
        InternalListView r3 = null;
        int r4 = 0;
    L6:
        if (r4 >= r2) goto L25;
        MediaPage r5 = r1[r4];
        if (r5 == null) goto L24;
        if (MediaPage.m12961$$Nest$fgetlistView(r5) == null) goto L18;
        if (MediaPage.m12961$$Nest$fgetlistView(r5).getAdapter() != r8) goto L18;
        if (MediaPage.m12961$$Nest$fgetlistView(r5).isComputingLayout() == false) goto L18;
        r3 = MediaPage.m12961$$Nest$fgetlistView(r5);
    L18:
        if (MediaPage.m12956$$Nest$fgetanimationSupportingListView(r5) == null) goto L24;
        if (MediaPage.m12956$$Nest$fgetanimationSupportingListView(r5).getAdapter() != r8) goto L24;
        if (MediaPage.m12956$$Nest$fgetanimationSupportingListView(r5).isComputingLayout() == false) goto L24;
        r3 = MediaPage.m12956$$Nest$fgetanimationSupportingListView(r5);
    L24:
        r4 = r4 + 1;
        goto L6
    L25:
        if (r3 != null) goto L27;
        return false;
    L27:
        r3.post(new SharedMediaLayout$$ExternalSyntheticLambda37(this, r8));
        return true;
    }

    private boolean prepareForMoving(MotionEvent r7, boolean r8) {
        int r0 = getNextPageId(r8);
        if (r0 >= 0) goto L6;
        return false;
    L6:
        if (this.searchItem != null) goto L8;
    L14:
        this.searchAlpha = getSearchAlpha(0.0f);
        updateSearchItemIcon(0.0f);
    L16:
        if (this.searching == false) goto L21;
        if (getSelectedTab() != 11) goto L21;
        return false;
    L21:
        if (canEditStories() == true) goto L23;
    L29:
        MediaPage r2 = this.mediaPages[0];
        if (r2 != null) goto L32;
    L38:
        MediaPage r22 = this.mediaPages[0];
        if (r22 == null) goto L48;
        if (r22.selectedType != 14) goto L48;
        ProfileGiftsContainer r23 = this.giftsContainer;
        if (r23 == null) goto L48;
        if (r23.canScroll(r8) == true) goto L48;
        return false;
    L48:
        if (this.isActionModeShowed == false) goto L54;
        MediaPage r24 = this.mediaPages[0];
        if (r24 == null) goto L54;
        if (r24.selectedType != 13) goto L54;
        return false;
    L54:
        ProfileGiftsContainer r25 = this.giftsContainer;
        if (r25 != null) goto L57;
    L59:
        ProfileStoriesCollectionTabs r26 = this.storiesContainer;
        if (r26 != null) goto L62;
    L64:
        updateOptionsSearch();
        getParent().requestDisallowInterceptTouchEvent(true);
        hideFloatingDateView(true);
        this.maybeStartTracking = false;
        this.startedTracking = true;
        onTabScroll(true);
        this.startedTrackingX = (int) r7.getX();
        this.actionBar.setEnabled(false);
        this.scrollSlidingTextTabStrip.setEnabled(false);
        MediaPage r72 = this.mediaPages[1];
        r72.selectedType = r0;
        r72.setVisibility(0);
        this.animatingForward = r8;
        switchToCurrentSelectedMode(true);
        MediaPage[] r73 = this.mediaPages;
        if (r8 == false) goto L67;
        r73[1].setTranslationX(r73[0].getMeasuredWidth());
    L68:
        onTabProgress(getTabProgress());
        return true;
    L67:
        r73[1].setTranslationX(-r73[0].getMeasuredWidth());
        goto L68
    L62:
        if (r26.isReordering() == false) goto L64;
        return false;
    L57:
        if (r25.isReordering() == false) goto L59;
        return false;
    L32:
        if (r2.selectedType != 13) goto L38;
        BotPreviewsEditContainer r27 = this.botPreviewsContainer;
        if (r27 == null) goto L38;
        if (r27.canScroll(r8) == true) goto L38;
        return false;
    L23:
        if (this.isActionModeShowed == false) goto L29;
        if (getClosestTab() != 8) goto L27;
    L28:
        return false;
    L27:
        if (isStoryAlbumPageType(getClosestTab()) == false) goto L29;
    L8:
        if (canShowSearchItem() == true) goto L14;
        ActionBarMenuItem r28 = this.searchItem;
        if (isStoriesView() == false) goto L12;
        int r5 = 8;
    L13:
        r28.setVisibility(r5);
        this.searchAlpha = 0.0f;
        goto L16
    L12:
        r5 = 4;
        goto L13
    }

    private void recycleAdapter(RecyclerView.Adapter r2) {
        if ((r2 instanceof SharedPhotoVideoAdapter) == false) goto L7;
        this.cellCache.addAll(this.cache);
        this.cache.clear();
        return;
    L7:
        if (r2 != this.audioAdapter) goto L10;
        this.audioCellCache.addAll(this.audioCache);
        this.audioCache.clear();
        return;
    L10:
        PollAdapter r1 = this.pollAdapter;
        if (r2 != r1) goto L14;
        r1.listView = null;
        return;
    }

    private void saveScrollPosition() {
        int r1 = 0;
    L3:
        MediaPage[] r2 = this.mediaPages;
        if (r1 >= r2.length) goto L53;
        InternalListView r22 = MediaPage.m12961$$Nest$fgetlistView(r2[r1]);
        if (r22 == null) goto L52;
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
    L9:
        if (r3 >= r22.getChildCount()) goto L22;
        View r6 = r22.getChildAt(r3);
        if ((r6 instanceof SharedPhotoVideoCell2) == false) goto L14;
        SharedPhotoVideoCell2 r42 = (SharedPhotoVideoCell2) r6;
        int r52 = r42.getMessageId();
        r5 = r42.getTop();
        r4 = r52;
    L14:
        if ((r6 instanceof SharedDocumentCell) == false) goto L17;
        SharedDocumentCell r43 = (SharedDocumentCell) r6;
        int r53 = r43.getMessage().getId();
        r5 = r43.getTop();
        r4 = r53;
    L17:
        if ((r6 instanceof SharedAudioCell) == false) goto L19;
        SharedAudioCell r62 = (SharedAudioCell) r6;
        r4 = r62.getMessage().getId();
        r5 = r62.getTop();
    L19:
        if (r4 != 0) goto L22;
        r3 = r3 + 1;
    L22:
        if (r4 == 0) goto L52;
        int r32 = this.mediaPages[r1].selectedType;
        int r7 = -1;
        if (isAnyStoryPageType(r32) == false) goto L37;
        StoriesAdapter r33 = storyAlbums_getStoriesAdapterByTabType(r32);
        if (r33 != null) goto L28;
    L36:
        int r34 = r7;
    L48:
        if (r7 < 0) goto L52;
        ((LinearLayoutManager) r22.getLayoutManager()).scrollToPositionWithOffset(r34, (-MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r1]).getPaddingTop()) + r5);
        if (this.photoVideoChangeColumnsAnimation == false) goto L52;
        MediaPage.m12955$$Nest$fgetanimationSupportingLayoutManager(this.mediaPages[r1]).scrollToPositionWithOffset(r34, (-MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r1]).getPaddingTop()) + r5);
        goto L52
    L28:
        if (r33.storiesList == null) goto L36;
        int r63 = 0;
    L31:
        if (r63 >= r33.storiesList.messageObjects.size()) goto L36;
        if (r4 == r33.storiesList.messageObjects.get(r63).getId()) goto L34;
        r63 = r63 + 1;
        goto L31
    L34:
        r7 = r63;
        goto L36
    L37:
        if (r32 < 0) goto L52;
        if (r32 >= this.sharedMediaData.length) goto L52;
        int r64 = 0;
    L42:
        if (r64 >= this.sharedMediaData[r32].messages.size()) goto L47;
        if (r4 == this.sharedMediaData[r32].messages.get(r64).getId()) goto L45;
        r64 = r64 + 1;
        goto L42
    L45:
        r7 = r64;
    L47:
        r34 = SharedMediaData.m12992$$Nest$fgetstartOffset(this.sharedMediaData[r32]) + r7;
    L52:
        r1 = r1 + 1;
        goto L3
    }

    private void scrollToTop() {
        int r0 = this.mediaPages[0].selectedType;
        if (r0 == 0) goto L17;
        if (r0 != 1) goto L6;
    L16:
        int r02 = AndroidUtilities.dp(56.0f);
    L18:
        MediaPage r3 = this.mediaPages[0];
        if (r3.selectedType != 0) goto L22;
        int r32 = MediaPage.m12960$$Nest$fgetlayoutManager(r3).findFirstVisibleItemPosition() / this.mediaColumnsCount[0];
    L23:
        float r03 = r32 * r02;
        float r33 = MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[0]).getMeasuredHeight() * 1.2f;
        MediaPage[] r34 = this.mediaPages;
        if (r03 < r33) goto L27;
        MediaPage.m12963$$Nest$fgetscrollHelper(r34[0]).setScrollDirection(1);
        MediaPage.m12963$$Nest$fgetscrollHelper(this.mediaPages[0]).scrollToPosition(0, 0, false, true);
        return;
    L27:
        MediaPage.m12961$$Nest$fgetlistView(r34[0]).smoothScrollToPosition(0);
        return;
    L22:
        r32 = MediaPage.m12960$$Nest$fgetlayoutManager(r3).findFirstVisibleItemPosition();
        goto L23
    L6:
        if (r0 == 2) goto L16;
        if (r0 != 3) goto L10;
        r02 = AndroidUtilities.dp(100.0f);
        goto L18
    L10:
        if (r0 == 4) goto L16;
        if (r0 == 5) goto L14;
        r02 = AndroidUtilities.dp(58.0f);
        goto L18
    L14:
        r02 = AndroidUtilities.dp(60.0f);
        goto L18
    L17:
        r02 = SharedPhotoVideoCell.getItemSize(1);
        goto L18
    }

    private void selectPinchPosition(int r8, int r9) {
        this.pinchCenterPosition = -1;
        int r92 = r9 + MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[0]).blurTopPadding;
        if (getY() != 0.0f) goto L5;
    L7:
        int r1 = 0;
    L9:
        if (r1 >= MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[0]).getChildCount()) goto L15;
        View r5 = MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[0]).getChildAt(r1);
        r5.getHitRect(this.rect);
        if (this.rect.contains(r8, r92) == false) goto L13;
        this.pinchCenterPosition = MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[0]).getChildLayoutPosition(r5);
        this.pinchCenterOffset = r5.getTop();
    L13:
        r1 = r1 + 1;
        goto L9
    L15:
        if (this.delegate.canSearchMembers() == true) goto L17;
        return;
    L17:
        if (this.pinchCenterPosition != (-1)) goto L24;
        this.pinchCenterPosition = (int) (MediaPage.m12960$$Nest$fgetlayoutManager(this.mediaPages[0]).findFirstVisibleItemPosition() + ((this.mediaColumnsCount[isAnyStoryPageType(this.mediaPages[0].selectedType) ? 1 : 0] - 1) * Math.min(1.0f, Math.max(r8 / MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[0]).getMeasuredWidth(), 0.0f))));
        this.pinchCenterOffset = 0;
        return;
    L24:
        return;
    L5:
        if (this.viewType != 1) goto L7;
        r92 = 0;
        goto L7
    }

    private void selectTabWithId(int r4, float r5) {
        if (this.scrollSlidingTextTabStrip == null) goto L10;
        if (isStoryAlbumPageType(r4) == false) goto L7;
        int r0 = 8;
    L8:
        this.scrollSlidingTextTabStrip.selectTabWithId(r0, r5);
        goto L10
    L7:
        r0 = r4;
    L10:
        if (this.storiesContainer != null) goto L12;
        return;
    L12:
        if (isStoryAlbumPageType(r4) == false) goto L15;
        this.storiesContainer.selectTabWithId(storyAlbums_getAlbumIdByTabType(r4), r5);
        return;
    L15:
        if (r4 != 8) goto L19;
        this.storiesContainer.selectTabWithId(0, r5);
        return;
    }

    private static void showFastScrollHint(MediaPage r2, SharedMediaData[] r3, boolean r4) {
        if (r4 == false) goto L21;
        if (SharedConfig.fastScrollHintCount > 0) goto L6;
        return;
    L6:
        if (r2.fastScrollHintView == null) goto L8;
        return;
    L8:
        if (r2.fastScrollHinWasShown == false) goto L10;
        return;
    L10:
        if (MediaPage.m12961$$Nest$fgetlistView(r2).getFastScroll() != null) goto L12;
        return;
    L12:
        if (MediaPage.m12961$$Nest$fgetlistView(r2).getFastScroll().isVisible == true) goto L14;
        return;
    L14:
        if (MediaPage.m12961$$Nest$fgetlistView(r2).getFastScroll().getVisibility() == 0) goto L16;
        return;
    L16:
        if (r3[0].getTotalCount() < 50) goto L34;
        SharedConfig.setFastScrollHintCount(SharedConfig.fastScrollHintCount - 1);
        r2.fastScrollHinWasShown = true;
        SharedMediaFastScrollTooltip r32 = new SharedMediaFastScrollTooltip(r2.getContext());
        r2.fastScrollHintView = r32;
        r2.addView(r32, LayoutHelper.createFrame(-2, -2.0f));
        r2.fastScrollHintView.setAlpha(0.0f);
        r2.fastScrollHintView.setScaleX(0.8f);
        r2.fastScrollHintView.setScaleY(0.8f);
        r2.fastScrollHintView.animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(150).start();
        r2.invalidate();
        SharedMediaLayout$$ExternalSyntheticLambda40 r42 = new SharedMediaLayout$$ExternalSyntheticLambda40(r2, r32);
        r2.fastScrollHideHintRunnable = r42;
        AndroidUtilities.runOnUIThread(r42, 4000);
        return;
    L34:
        return;
    L21:
        if (r2.fastScrollHintView == null) goto L33;
        Runnable r33 = r2.fastScrollHideHintRunnable;
        if (r33 == null) goto L35;
        AndroidUtilities.cancelRunOnUIThread(r33);
        r2.fastScrollHideHintRunnable.run();
        r2.fastScrollHideHintRunnable = null;
        r2.fastScrollHintView = null;
        return;
    L35:
        return;
    }

    private void showFloatingDateView() {
    }

    private void startPinchToMediaColumnsCount(boolean r8) {
        if (this.photoVideoChangeColumnsAnimation == true) goto L49;
        int r0 = 0;
        int r1 = 0;
    L6:
        MediaPage[] r2 = this.mediaPages;
        if (r1 >= r2.length) goto L15;
        int r22 = r2[r1].selectedType;
        if (r22 == 0) goto L14;
        if (isAnyStoryPageType(r22) == true) goto L14;
        r1 = r1 + 1;
    L14:
        MediaPage r12 = this.mediaPages[r1];
    L16:
        if (r12 == null) goto L47;
        int r23 = r12.selectedType;
        this.changeColumnsTab = r23;
        boolean r24 = isAnyStoryPageType(r23);
        int r82 = getNextMediaColumnsCount(r24 ? 1 : 0, this.mediaColumnsCount[r24 ? 1 : 0], r8);
        this.animateToColumnsCount = r82;
        if (r82 != this.mediaColumnsCount[r24 ? 1 : 0]) goto L20;
        return;
    L20:
        if (this.allowStoriesSingleColumn == true) goto L22;
    L24:
        MediaPage.m12956$$Nest$fgetanimationSupportingListView(r12).setVisibility(0);
        if (isAnyStoryPageType(this.changeColumnsTab) == false) goto L27;
        MediaPage.m12956$$Nest$fgetanimationSupportingListView(r12).setAdapter(storyAlbums_getStoriesSupportingAdapterByTabType(this.changeColumnsTab));
    L28:
        MediaPage.m12956$$Nest$fgetanimationSupportingListView(r12).setPadding(MediaPage.m12956$$Nest$fgetanimationSupportingListView(r12).getPaddingLeft(), getPagePaddingTop(this.changeColumnsTab), MediaPage.m12956$$Nest$fgetanimationSupportingListView(r12).getPaddingRight(), getPagePaddingBottom(isStoriesView()));
        MediaPage.m12955$$Nest$fgetanimationSupportingLayoutManager(r12).setSpanCount(r82);
        MediaPage.m12956$$Nest$fgetanimationSupportingListView(r12).invalidateItemDecorations();
        MediaPage.m12955$$Nest$fgetanimationSupportingLayoutManager(r12).setSpanSizeLookup(new AnonymousClass34(this, r12));
        AndroidUtilities.updateVisibleRows(MediaPage.m12961$$Nest$fgetlistView(r12));
        this.photoVideoChangeColumnsAnimation = true;
        if (this.changeColumnsTab != 0) goto L31;
        this.sharedMediaData[0].setListFrozen(true);
    L31:
        this.photoVideoChangeColumnsProgress = 0.0f;
        if (this.pinchCenterPosition < 0) goto L39;
    L33:
        MediaPage[] r83 = this.mediaPages;
        if (r0 >= r83.length) goto L51;
        MediaPage r84 = r83[r0];
        if (r84.selectedType != this.changeColumnsTab) goto L38;
        MediaPage.m12955$$Nest$fgetanimationSupportingLayoutManager(r84).scrollToPositionWithOffset(this.pinchCenterPosition, this.pinchCenterOffset - MediaPage.m12956$$Nest$fgetanimationSupportingListView(this.mediaPages[r0]).getPaddingTop());
    L38:
        r0 = r0 + 1;
        goto L33
    L51:
        return;
    L39:
        saveScrollPosition();
        return;
    L27:
        MediaPage.m12956$$Nest$fgetanimationSupportingListView(r12).setAdapter(this.animationSupportingPhotoVideoAdapter);
        goto L28
    L22:
        if (isAnyStoryPageType(this.changeColumnsTab) == false) goto L24;
        return;
    L47:
        return;
    L15:
        r12 = null;
        goto L16
    }

    private void startStopVisibleGifs() {
        int r1 = 0;
    L3:
        MediaPage[] r2 = this.mediaPages;
        if (r1 >= r2.length) goto L15;
        int r22 = MediaPage.m12961$$Nest$fgetlistView(r2[r1]).getChildCount();
        int r3 = 0;
    L6:
        if (r3 >= r22) goto L14;
        View r4 = MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r1]).getChildAt(r3);
        if ((r4 instanceof ContextLinkCell) == false) goto L13;
        ImageReceiver r42 = ((ContextLinkCell) r4).getPhotoImage();
        if (r1 != 0) goto L12;
        r42.setAllowStartAnimation(true);
        r42.startAnimation();
        goto L13
    L12:
        r42.setAllowStartAnimation(false);
        r42.stopAnimation();
    L13:
        r3 = r3 + 1;
        goto L6
    L14:
        r1 = r1 + 1;
        goto L3
    }

    private void stopScroll(MotionEvent r14) {
        VelocityTracker r0 = this.velocityTracker;
        if (r0 == null) goto L52;
        r0.computeCurrentVelocity(1000, this.maximumVelocity);
        if (r14 != null) goto L8;
    L20:
        float r4 = 0.0f;
        float r5 = 0.0f;
    L22:
        if (this.startedTracking == false) goto L46;
        float r142 = this.mediaPages[0].getX();
        this.tabsAnimation = new AnimatorSet();
        if (Math.abs(r142) < (this.mediaPages[0].getMeasuredWidth() / 3.0f)) goto L26;
    L30:
        boolean r52 = false;
    L31:
        this.backAnimation = r52;
        ValueAnimator r6 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r6.addUpdateListener(new SharedMediaLayout$$ExternalSyntheticLambda1(this));
        boolean r7 = this.backAnimation;
        Property r8 = View.TRANSLATION_X;
        if (r7 == false) goto L37;
        float r143 = Math.abs(r142);
        boolean r72 = this.animatingForward;
        AnimatorSet r9 = this.tabsAnimation;
        if (r72 == false) goto L36;
        r9.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.mediaPages[0], r8, new float[]{0.0f}), ObjectAnimator.ofFloat(this.mediaPages[1], r8, new float[]{r10.getMeasuredWidth()}), r6});
    L41:
        this.tabsAnimation.setInterpolator(interpolator);
        int r02 = getMeasuredWidth();
        float r53 = r02 / 2;
        float r54 = r53 + (AndroidUtilities.distanceInfluenceForSnapDuration(Math.min(1.0f, (r143 * 1.0f) / r02)) * r53);
        float r03 = Math.abs(r4);
        if (r03 <= 0.0f) goto L44;
        int r144 = Math.round(Math.abs(r54 / r03) * 1000.0f) * 4;
    L45:
        this.tabsAnimation.setDuration(Math.max(150, Math.min(r144, MediaError.DetailedErrorCode.TEXT_UNKNOWN)));
        this.tabsAnimation.addListener(new AnonymousClass43(this));
        this.tabsAnimation.start();
        this.tabsAnimationInProgress = true;
        this.startedTracking = false;
        onSelectedTabChanged();
    L47:
        VelocityTracker r145 = this.velocityTracker;
        if (r145 == null) goto L51;
        r145.recycle();
        this.velocityTracker = null;
        return;
    L51:
        return;
    L44:
        r144 = (int) (((r143 / getMeasuredWidth()) + 1.0f) * 100.0f);
        goto L45
    L36:
        r9.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.mediaPages[0], r8, new float[]{0.0f}), ObjectAnimator.ofFloat(this.mediaPages[1], r8, new float[]{-r10.getMeasuredWidth()}), r6});
        goto L41
    L37:
        r143 = this.mediaPages[0].getMeasuredWidth() - Math.abs(r142);
        boolean r73 = this.animatingForward;
        AnimatorSet r92 = this.tabsAnimation;
        if (r73 == false) goto L40;
        r92.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.mediaPages[0], r8, new float[]{-r7.getMeasuredWidth()}), ObjectAnimator.ofFloat(this.mediaPages[1], r8, new float[]{0.0f}), r6});
        goto L41
    L40:
        r92.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.mediaPages[0], r8, new float[]{r7.getMeasuredWidth()}), ObjectAnimator.ofFloat(this.mediaPages[1], r8, new float[]{0.0f}), r6});
        goto L41
    L26:
        if (Math.abs(r4) >= AppUtils.getSwipeVelocity()) goto L28;
    L29:
        r52 = true;
        goto L31
    L28:
        if (Math.abs(r4) >= Math.abs(r5)) goto L30;
    L46:
        this.maybeStartTracking = false;
        this.actionBar.setEnabled(true);
        this.scrollSlidingTextTabStrip.setEnabled(true);
        goto L47
    L8:
        if (r14.getAction() == 3) goto L20;
        r4 = this.velocityTracker.getXVelocity();
        r5 = this.velocityTracker.getYVelocity();
        if (this.startedTracking == true) goto L22;
        if (Math.abs(r4) < AppUtils.getSwipeVelocity()) goto L22;
        if (Math.abs(r4) <= Math.abs(r5)) goto L22;
        if (r4 >= 0.0f) goto L18;
        boolean r62 = true;
    L19:
        prepareForMoving(r14, r62);
        goto L22
    L18:
        r62 = false;
        goto L19
    }

    private StoryAlbumData storyAlbums_getByAlbumId(int r4) {
        StoryAlbumData r0 = this.storyAlbumsById.get(Integer.valueOf(r4));
        if (r0 != null) goto L6;
        StoryAlbumData r02 = new StoryAlbumData(this, getContext(), r4, null);
        this.storyAlbumsById.put(Integer.valueOf(r4), r02);
        this.storyAlbumsByTabType.put(Integer.valueOf(r02.tabType), Integer.valueOf(r02.albumId));
        return r02;
    L6:
        return r0;
    }

    private StoryAlbumData storyAlbums_getByTabType(int r2) {
        Integer r22 = this.storyAlbumsByTabType.get(Integer.valueOf(r2));
        if (r22 != null) goto L7;
        return null;
    L7:
        return this.storyAlbumsById.get(r22);
    }

    private StoriesAdapter storyAlbums_getStoriesAdapterByTabType(int r3) {
        if (r3 != 8) goto L7;
        return this.storiesAdapter;
    L7:
        if (r3 != 9) goto L11;
        return this.archivedStoriesAdapter;
    L11:
        if (isStoryAlbumPageType(r3) == false) goto L16;
        StoryAlbumData r2 = storyAlbums_getByTabType(r3);
        if (r2 == null) goto L16;
        return r2.adapter;
    L16:
        return null;
    }

    private StoriesAdapter storyAlbums_getStoriesSupportingAdapterByTabType(int r3) {
        if (r3 != 8) goto L7;
        return this.animationSupportingStoriesAdapter;
    L7:
        if (r3 != 9) goto L11;
        return this.animationSupportingArchivedStoriesAdapter;
    L11:
        if (isStoryAlbumPageType(r3) == false) goto L16;
        StoryAlbumData r2 = storyAlbums_getByTabType(r3);
        if (r2 == null) goto L16;
        return r2.adapterSupport;
    L16:
        return null;
    }

    private int storyAlbums_getTabTypeByStoriesAdapter(RecyclerView.Adapter<?> r3) {
        if (r3 != this.storiesAdapter) goto L7;
        return 8;
    L7:
        if (r3 != this.archivedStoriesAdapter) goto L10;
        return 9;
    L10:
        Iterator<StoryAlbumData> r2 = this.storyAlbumsById.values().iterator();
    L12:
        if (r2.hasNext() == false) goto L17;
        StoryAlbumData r0 = r2.next();
        if (r0.adapter != r3) goto L12;
        return r0.tabType;
    L17:
        return -1;
    }

    private int storyAlbums_getTabTypeByStoriesList(StoriesController.StoriesList r3) {
        StoriesAdapter r0 = this.storiesAdapter;
        if (r0 != null) goto L5;
    L8:
        StoriesAdapter r02 = this.archivedStoriesAdapter;
        if (r02 != null) goto L11;
    L14:
        Iterator<StoryAlbumData> r2 = this.storyAlbumsById.values().iterator();
    L16:
        if (r2.hasNext() == false) goto L21;
        StoryAlbumData r03 = r2.next();
        if (r03.adapter.storiesList != r3) goto L16;
        return r03.tabType;
    L21:
        return -1;
    L11:
        if (r3 != r02.storiesList) goto L14;
        return 9;
    L5:
        if (r3 != r0.storiesList) goto L8;
        return 8;
    }

    private int storyAlbums_getTabTypeByStoriesSupportingAdapter(RecyclerView.Adapter<?> r3) {
        if (r3 != this.animationSupportingStoriesAdapter) goto L7;
        return 8;
    L7:
        if (r3 != this.animationSupportingArchivedStoriesAdapter) goto L10;
        return 9;
    L10:
        Iterator<StoryAlbumData> r2 = this.storyAlbumsById.values().iterator();
    L12:
        if (r2.hasNext() == false) goto L17;
        StoryAlbumData r0 = r2.next();
        if (r0.adapterSupport != r3) goto L12;
        return r0.tabType;
    L17:
        return -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r16v0 */
    /* JADX WARN: Type inference failed for: r16v1 */
    /* JADX WARN: Type inference failed for: r16v4 */
    /* JADX WARN: Type inference failed for: r34v1 */
    /* JADX WARN: Type inference failed for: r34v10 */
    /* JADX WARN: Type inference failed for: r34v100 */
    /* JADX WARN: Type inference failed for: r34v101 */
    /* JADX WARN: Type inference failed for: r34v102 */
    /* JADX WARN: Type inference failed for: r34v103 */
    /* JADX WARN: Type inference failed for: r34v104 */
    /* JADX WARN: Type inference failed for: r34v105 */
    /* JADX WARN: Type inference failed for: r34v106 */
    /* JADX WARN: Type inference failed for: r34v107 */
    /* JADX WARN: Type inference failed for: r34v108 */
    /* JADX WARN: Type inference failed for: r34v109 */
    /* JADX WARN: Type inference failed for: r34v11 */
    /* JADX WARN: Type inference failed for: r34v110 */
    /* JADX WARN: Type inference failed for: r34v111 */
    /* JADX WARN: Type inference failed for: r34v12 */
    /* JADX WARN: Type inference failed for: r34v13 */
    /* JADX WARN: Type inference failed for: r34v14 */
    /* JADX WARN: Type inference failed for: r34v15 */
    /* JADX WARN: Type inference failed for: r34v16 */
    /* JADX WARN: Type inference failed for: r34v17 */
    /* JADX WARN: Type inference failed for: r34v18 */
    /* JADX WARN: Type inference failed for: r34v19 */
    /* JADX WARN: Type inference failed for: r34v2 */
    /* JADX WARN: Type inference failed for: r34v20 */
    /* JADX WARN: Type inference failed for: r34v21 */
    /* JADX WARN: Type inference failed for: r34v22 */
    /* JADX WARN: Type inference failed for: r34v23 */
    /* JADX WARN: Type inference failed for: r34v24 */
    /* JADX WARN: Type inference failed for: r34v25 */
    /* JADX WARN: Type inference failed for: r34v26 */
    /* JADX WARN: Type inference failed for: r34v27 */
    /* JADX WARN: Type inference failed for: r34v28 */
    /* JADX WARN: Type inference failed for: r34v29 */
    /* JADX WARN: Type inference failed for: r34v3 */
    /* JADX WARN: Type inference failed for: r34v30 */
    /* JADX WARN: Type inference failed for: r34v31 */
    /* JADX WARN: Type inference failed for: r34v32 */
    /* JADX WARN: Type inference failed for: r34v33 */
    /* JADX WARN: Type inference failed for: r34v34 */
    /* JADX WARN: Type inference failed for: r34v35 */
    /* JADX WARN: Type inference failed for: r34v36 */
    /* JADX WARN: Type inference failed for: r34v37 */
    /* JADX WARN: Type inference failed for: r34v38 */
    /* JADX WARN: Type inference failed for: r34v39 */
    /* JADX WARN: Type inference failed for: r34v4 */
    /* JADX WARN: Type inference failed for: r34v40 */
    /* JADX WARN: Type inference failed for: r34v41 */
    /* JADX WARN: Type inference failed for: r34v42 */
    /* JADX WARN: Type inference failed for: r34v43 */
    /* JADX WARN: Type inference failed for: r34v44 */
    /* JADX WARN: Type inference failed for: r34v45 */
    /* JADX WARN: Type inference failed for: r34v46 */
    /* JADX WARN: Type inference failed for: r34v47 */
    /* JADX WARN: Type inference failed for: r34v48 */
    /* JADX WARN: Type inference failed for: r34v49 */
    /* JADX WARN: Type inference failed for: r34v5 */
    /* JADX WARN: Type inference failed for: r34v50 */
    /* JADX WARN: Type inference failed for: r34v51 */
    /* JADX WARN: Type inference failed for: r34v52 */
    /* JADX WARN: Type inference failed for: r34v53 */
    /* JADX WARN: Type inference failed for: r34v54 */
    /* JADX WARN: Type inference failed for: r34v55 */
    /* JADX WARN: Type inference failed for: r34v56 */
    /* JADX WARN: Type inference failed for: r34v57 */
    /* JADX WARN: Type inference failed for: r34v58 */
    /* JADX WARN: Type inference failed for: r34v59 */
    /* JADX WARN: Type inference failed for: r34v6 */
    /* JADX WARN: Type inference failed for: r34v60 */
    /* JADX WARN: Type inference failed for: r34v61 */
    /* JADX WARN: Type inference failed for: r34v62 */
    /* JADX WARN: Type inference failed for: r34v63 */
    /* JADX WARN: Type inference failed for: r34v64 */
    /* JADX WARN: Type inference failed for: r34v65 */
    /* JADX WARN: Type inference failed for: r34v66 */
    /* JADX WARN: Type inference failed for: r34v67 */
    /* JADX WARN: Type inference failed for: r34v68 */
    /* JADX WARN: Type inference failed for: r34v69 */
    /* JADX WARN: Type inference failed for: r34v7 */
    /* JADX WARN: Type inference failed for: r34v70 */
    /* JADX WARN: Type inference failed for: r34v71 */
    /* JADX WARN: Type inference failed for: r34v72 */
    /* JADX WARN: Type inference failed for: r34v73 */
    /* JADX WARN: Type inference failed for: r34v74 */
    /* JADX WARN: Type inference failed for: r34v75 */
    /* JADX WARN: Type inference failed for: r34v76 */
    /* JADX WARN: Type inference failed for: r34v77 */
    /* JADX WARN: Type inference failed for: r34v78 */
    /* JADX WARN: Type inference failed for: r34v79 */
    /* JADX WARN: Type inference failed for: r34v8 */
    /* JADX WARN: Type inference failed for: r34v80 */
    /* JADX WARN: Type inference failed for: r34v81 */
    /* JADX WARN: Type inference failed for: r34v82 */
    /* JADX WARN: Type inference failed for: r34v83 */
    /* JADX WARN: Type inference failed for: r34v84 */
    /* JADX WARN: Type inference failed for: r34v85 */
    /* JADX WARN: Type inference failed for: r34v86 */
    /* JADX WARN: Type inference failed for: r34v87 */
    /* JADX WARN: Type inference failed for: r34v88 */
    /* JADX WARN: Type inference failed for: r34v89 */
    /* JADX WARN: Type inference failed for: r34v9 */
    /* JADX WARN: Type inference failed for: r34v90 */
    /* JADX WARN: Type inference failed for: r34v91 */
    /* JADX WARN: Type inference failed for: r34v92 */
    /* JADX WARN: Type inference failed for: r34v93 */
    /* JADX WARN: Type inference failed for: r34v94 */
    /* JADX WARN: Type inference failed for: r34v95 */
    /* JADX WARN: Type inference failed for: r34v96 */
    /* JADX WARN: Type inference failed for: r34v97 */
    /* JADX WARN: Type inference failed for: r34v98 */
    /* JADX WARN: Type inference failed for: r34v99 */
    /* JADX WARN: Type inference failed for: r3v40, types: [androidx.recyclerview.widget.ItemTouchHelper] */
    /* JADX WARN: Type inference failed for: r8v26 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5 */
    private void switchToCurrentSelectedMode(boolean r34) {
        ProfileGiftsContainer r2 = this.giftsContainer;
        if (r2 == null) goto L5;
        r2.resetReordering();
    L5:
        int r3 = 0;
    L6:
        MediaPage[] r4 = this.mediaPages;
        if (r3 >= r4.length) goto L9;
        MediaPage.m12961$$Nest$fgetlistView(r4[r3]).stopScroll();
        r3 = r3 + 1;
        goto L6
    L9:
        FrameLayout.LayoutParams r32 = (FrameLayout.LayoutParams) r4[r34 ? 1 : 0].getLayoutParams();
        r32.topMargin = AndroidUtilities.dp(mediaPageTopMargin());
        RecyclerView.Adapter r42 = MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).getAdapter();
        if (r42 != this.storiesAdapter) goto L12;
        this.storiesReorder.attachToRecyclerView(null);
    L12:
        boolean r5 = this.searching;
        String r9 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r5 == false) goto L104;
        if (this.searchWas == false) goto L104;
        if (MediaPage.m12964$$Nest$fgetsearchViewPool(this.mediaPages[r34 ? 1 : 0]) != null) goto L19;
        MediaPage.m12976$$Nest$fputsearchViewPool(this.mediaPages[r34 ? 1 : 0], new RecyclerView.RecycledViewPool());
    L19:
        RecyclerView.RecycledViewPool r33 = MediaPage.m12964$$Nest$fgetsearchViewPool(this.mediaPages[r34 ? 1 : 0]);
        MediaPage[] r52 = this.mediaPages;
        if (r34 == false) goto L75;
        int r53 = r52[r34 ? 1 : 0].selectedType;
        if (r53 == 0) goto L69;
        if (r53 == 2) goto L69;
        if (r53 == 5) goto L69;
        if (r53 == 6) goto L69;
        if (r53 == 7) goto L28;
    L30:
        ActionBarMenuItem r54 = this.searchItem;
        if (r54 == null) goto L33;
        r9 = r54.getSearchField().getText().toString();
    L33:
        int r55 = this.mediaPages[r34 ? 1 : 0].selectedType;
        if (r55 != 1) goto L40;
        MediaSearchAdapter r56 = this.documentsSearchAdapter;
        if (r56 == null) goto L102;
        r56.search(r9, false);
        if (r42 == this.documentsSearchAdapter) goto L102;
        recycleAdapter(r42);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setAdapter(this.documentsSearchAdapter);
    L102:
        boolean r57 = false;
        boolean r7 = false;
        int r10 = 4;
        int r22 = 100;
        int r12 = 8;
    L471:
        MediaPage r43 = this.mediaPages[r34 ? 1 : 0];
        r43.fastScrollEnabled = r57;
        updateFastScrollVisibility(r43, r7);
        MediaPage.m12960$$Nest$fgetlayoutManager(this.mediaPages[r34 ? 1 : 0]).setSpanCount(r22);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).invalidateItemDecorations();
        if (r33 == null) goto L475;
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setRecycledViewPool(r33);
        MediaPage.m12956$$Nest$fgetanimationSupportingListView(this.mediaPages[r34 ? 1 : 0]).setRecycledViewPool(r33);
    L475:
        if (this.searchItemState == 2) goto L477;
        return;
    L477:
        if (this.actionBar.isSearchFieldVisible() == false) goto L489;
        this.ignoreSearchCollapse = true;
        this.actionBar.closeSearchField();
        this.searchItemState = 0;
        this.searchAlpha = 0.0f;
        ActionBarMenuItem r1 = this.searchItem;
        if (r1 != null) goto L481;
    L485:
        updateOptionsSearch();
        return;
    L481:
        if (isStoriesView() == true) goto L484;
        r12 = r10;
    L484:
        r1.setVisibility(r12);
        goto L485
    L489:
        return;
    L40:
        if (r55 != 3) goto L46;
        MediaSearchAdapter r58 = this.linksSearchAdapter;
        if (r58 == null) goto L102;
        r58.search(r9, false);
        if (r42 == this.linksSearchAdapter) goto L102;
        recycleAdapter(r42);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setAdapter(this.linksSearchAdapter);
        goto L102
    L46:
        if (r55 != 4) goto L52;
        MediaSearchAdapter r59 = this.audioSearchAdapter;
        if (r59 == null) goto L102;
        r59.search(r9, false);
        if (r42 == this.audioSearchAdapter) goto L102;
        recycleAdapter(r42);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setAdapter(this.audioSearchAdapter);
        goto L102
    L52:
        if (r55 != 7) goto L58;
        GroupUsersSearchAdapter r510 = this.groupUsersSearchAdapter;
        if (r510 == null) goto L102;
        r510.search(r9, false);
        if (r42 == this.groupUsersSearchAdapter) goto L102;
        recycleAdapter(r42);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setAdapter(this.groupUsersSearchAdapter);
        goto L102
    L58:
        if (r55 != 11) goto L64;
        SavedMessagesSearchAdapter r511 = this.savedMessagesSearchAdapter;
        if (r511 == null) goto L102;
        r511.search(r9, this.searchingReaction);
        if (r42 == this.savedMessagesSearchAdapter) goto L102;
        recycleAdapter(r42);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setAdapter(this.savedMessagesSearchAdapter);
        goto L102
    L64:
        if (r55 != 15) goto L102;
        PollAdapter r512 = this.pollAdapter;
        if (r512 == null) goto L102;
        if (r42 == r512) goto L102;
        recycleAdapter(r42);
        this.pollAdapter.listView = MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setAdapter(this.pollAdapter);
        goto L102
    L28:
        if (this.delegate.canSearchMembers() == true) goto L30;
    L69:
        this.searching = false;
        SearchTagsList r13 = this.searchTagsList;
        if (r13 == null) goto L72;
        r13.show(false);
    L72:
        this.searchWas = false;
        switchToCurrentSelectedMode(true);
        return;
    L75:
        if (MediaPage.m12961$$Nest$fgetlistView(r52[r34 ? 1 : 0]) == null) goto L102;
        int r513 = this.mediaPages[r34 ? 1 : 0].selectedType;
        if (r513 == 1) goto L79;
        if (r513 == 3) goto L84;
        if (r513 == 4) goto L89;
        if (r513 == 7) goto L94;
        if (r513 != 11) goto L102;
        if (r42 == this.savedMessagesSearchAdapter) goto L101;
        recycleAdapter(r42);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setAdapter(this.savedMessagesSearchAdapter);
    L101:
        this.savedMessagesSearchAdapter.notifyDataSetChanged();
        goto L102
    L94:
        if (r42 == this.groupUsersSearchAdapter) goto L96;
        recycleAdapter(r42);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setAdapter(this.groupUsersSearchAdapter);
    L96:
        this.groupUsersSearchAdapter.notifyDataSetChanged();
        goto L102
    L89:
        if (r42 == this.audioSearchAdapter) goto L91;
        recycleAdapter(r42);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setAdapter(this.audioSearchAdapter);
    L91:
        this.audioSearchAdapter.notifyDataSetChanged();
        goto L102
    L84:
        if (r42 == this.linksSearchAdapter) goto L86;
        recycleAdapter(r42);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setAdapter(this.linksSearchAdapter);
    L86:
        this.linksSearchAdapter.notifyDataSetChanged();
        goto L102
    L79:
        if (r42 == this.documentsSearchAdapter) goto L81;
        recycleAdapter(r42);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setAdapter(this.documentsSearchAdapter);
    L81:
        this.documentsSearchAdapter.notifyDataSetChanged();
    L104:
        if (MediaPage.m12965$$Nest$fgetviewPool(this.mediaPages[r34 ? 1 : 0]) != null) goto L106;
        boolean r16 = 0;
        MediaPage.m12977$$Nest$fputviewPool(this.mediaPages[r34 ? 1 : 0], new RecyclerView.RecycledViewPool());
    L107:
        RecyclerView.RecycledViewPool r23 = MediaPage.m12965$$Nest$fgetviewPool(this.mediaPages[r34 ? 1 : 0]);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setPinnedHeaderShadowDrawable(null);
        int r514 = this.mediaPages[r34 ? 1 : 0].selectedType;
        if (r514 != 8) goto L110;
    L111:
        r32.topMargin = AndroidUtilities.dp(mediaPageTopMargin());
    L112:
        InternalListView r515 = MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]);
        int r72 = MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).getPaddingLeft();
        InternalListView r14 = MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]);
        int r132 = getPagePaddingTop(this.mediaPages[r34 ? 1 : 0].selectedType);
        r14.hintPaddingTop = r132;
        int r142 = MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).getPaddingRight();
        InternalListView r11 = MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]);
        int r102 = getPagePaddingBottom(isStoriesView());
        r11.hintPaddingBottom = r102;
        r515.setPadding(r72, r132, r142, r102);
        int r516 = this.mediaPages[r34 ? 1 : 0].selectedType;
        if (r516 == 0) goto L115;
        if (r516 != 1) goto L139;
        SharedMediaData r35 = this.sharedMediaData[1];
        if (r35.fastScrollDataLoaded == true) goto L131;
    L133:
        boolean r36 = r16;
    L135:
        if (r42 == this.documentsAdapter) goto L137;
        recycleAdapter(r42);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setAdapter(this.documentsAdapter);
    L137:
        r57 = r36;
        boolean r44 = true;
    L138:
        int r37 = 100;
    L228:
        int r8 = this.mediaPages[r34 ? 1 : 0].selectedType;
        if (r8 != 0) goto L231;
    L234:
        ?? r82 = true;
    L235:
        FlickerLoadingView r73 = MediaPage.m12962$$Nest$fgetprogressView(this.mediaPages[r34 ? 1 : 0]);
        if (r82 == false) goto L238;
        float r222 = 0.0f;
    L239:
        if (r82 == false) goto L241;
        int r19 = 8;
    L242:
        float r112 = r19 + 48;
        if (r82 == false) goto L245;
        float r24 = 0.0f;
    L246:
        if (r82 == false) goto L248;
        float r25 = 0.0f;
    L249:
        r73.setLayoutParams(LayoutHelper.createFrame(-1, -1.0f, 119, r222, r112, r24, r25));
        MediaPage[] r74 = this.mediaPages;
        if (r44 == false) goto L252;
        MediaPage.m12961$$Nest$fgetlistView(r74[r34 ? 1 : 0]).setSections(r16);
    L253:
        MediaPage r45 = this.mediaPages[r34 ? 1 : 0];
        if (r45.selectedType != 15) goto L256;
        r45.setBackground(PreviewView.getBackgroundDrawable(r45.getBackground(), this.profileActivity.getCurrentAccount(), this.dialog_id, Theme.isCurrentThemeDark()));
        this.mediaPages[r34 ? 1 : 0].setOutlineProvider(new AnonymousClass48(this));
        this.mediaPages[r34 ? 1 : 0].setClipToOutline(true);
    L257:
        MediaPage r46 = this.mediaPages[r34 ? 1 : 0];
        if (r46.selectedType != 11) goto L260;
        MediaPage.m12961$$Nest$fgetlistView(r46).setItemAnimator(MediaPage.m12959$$Nest$fgetitemAnimator(this.mediaPages[r34 ? 1 : 0]));
    L265:
        ChatActivityContainer r47 = this.savedMessagesContainer;
        if (r47 != null) goto L268;
    L272:
        BotPreviewsEditContainer r48 = this.botPreviewsContainer;
        if (r48 != null) goto L275;
    L279:
        ProfileGiftsContainer r49 = this.giftsContainer;
        if (r49 != null) goto L282;
    L286:
        int r410 = this.mediaPages[r34 ? 1 : 0].selectedType;
        if (r410 != 0) goto L289;
    L303:
        r10 = 4;
        if (r34 == false) goto L343;
        this.searchItemState = 2;
    L353:
        int r411 = this.mediaPages[r34 ? 1 : 0].selectedType;
        if (r411 != 6) goto L363;
        if (CommonGroupsAdapter.m12950$$Nest$fgetloading(this.commonGroupsAdapter) == false) goto L358;
    L419:
        int r412 = this.mediaPages[r34 ? 1 : 0].selectedType;
        if (r412 != 8) goto L422;
    L435:
        StickerEmptyView r413 = MediaPage.m12957$$Nest$fgetemptyView(this.mediaPages[r34 ? 1 : 0]);
        boolean r75 = isStoryAlbumPageType(this.mediaPages[r34 ? 1 : 0].selectedType);
        int r83 = storyAlbums_getAlbumIdByTabType(this.mediaPages[r34 ? 1 : 0].selectedType);
        BackupImageView r113 = r413.stickerView;
        if (r75 == false) goto L438;
    L442:
        int r122 = 8;
    L443:
        r113.setVisibility(r122);
        if (r75 == false) goto L447;
        r413.button.setVisibility(0);
        r413.button.setText(LocaleController.getString(R.string.StoriesAlbumAddToAlbum), false);
        r12 = 8;
    L454:
        SpoilersTextView r114 = r413.title;
        if (r75 == false) goto L458;
        r114.setText(LocaleController.getString(R.string.StoriesAlbumOrganizeTitle));
        r413.subtitle.setText(LocaleController.getString(R.string.StoriesAlbumOrganizeDescription));
    L469:
        r413.button.setOnClickListener(new SharedMediaLayout$$ExternalSyntheticLambda2(this, r75, r83));
    L470:
        r7 = false;
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setVisibility(0);
        int r322 = r37;
        r33 = r23;
        r22 = r322;
        goto L471
    L458:
        if (isSearchingStories() == false) goto L460;
        int r133 = R.string.NoHashtagStoriesTitle;
    L465:
        r114.setText(LocaleController.getString(r133));
        LinkSpanDrawable.LinksTextView r115 = r413.subtitle;
        if (isStoriesView() == false) goto L468;
        r9 = LocaleController.getString(R.string.NoStoriesSubtitle2);
    L468:
        r115.setText(r9);
        goto L469
    L460:
        if (isStoriesView() == false) goto L463;
        r133 = R.string.NoPublicStoriesTitle2;
        goto L465
    L463:
        r133 = R.string.NoStoriesTitle;
        goto L465
    L447:
        if (isSelf() == false) goto L449;
        r12 = 8;
        r413.button.setVisibility(8);
        goto L454
    L449:
        r12 = 8;
        r413.setStickerType(11);
        ButtonWithCounterView r116 = r413.button;
        if (isSearchingStories() == true) goto L452;
        int r134 = 0;
    L453:
        r116.setVisibility(r134);
        r413.button.setText(addPostText(), false);
        goto L454
    L452:
        r134 = 8;
        goto L453
    L438:
        if (isSelf() == true) goto L442;
        if (isBot() == true) goto L442;
        r122 = 0;
        goto L443
    L422:
        if (isStoryAlbumPageType(r412) == true) goto L435;
        MediaPage r414 = this.mediaPages[r34 ? 1 : 0];
        if (r414.selectedType != 9) goto L434;
        boolean r415 = isSelf();
        MediaPage[] r76 = this.mediaPages;
        if (r415 == false) goto L429;
        MediaPage.m12957$$Nest$fgetemptyView(r76[r34 ? 1 : 0]).stickerView.setVisibility(8);
        MediaPage.m12957$$Nest$fgetemptyView(this.mediaPages[r34 ? 1 : 0]).button.setVisibility(8);
    L430:
        MediaPage.m12957$$Nest$fgetemptyView(this.mediaPages[r34 ? 1 : 0]).title.setText(LocaleController.getString(R.string.NoArchivedStoriesTitle));
        LinkSpanDrawable.LinksTextView r416 = MediaPage.m12957$$Nest$fgetemptyView(this.mediaPages[r34 ? 1 : 0]).subtitle;
        if (isStoriesView() == false) goto L433;
        r9 = LocaleController.getString(R.string.NoArchivedStoriesSubtitle);
    L433:
        r416.setText(r9);
        MediaPage.m12957$$Nest$fgetemptyView(this.mediaPages[r34 ? 1 : 0]).button.setOnClickListener(new SharedMediaLayout$$ExternalSyntheticLambda3(this));
        r12 = 8;
        goto L470
    L429:
        MediaPage.m12957$$Nest$fgetemptyView(r76[r34 ? 1 : 0]).stickerView.setVisibility(0);
        MediaPage.m12957$$Nest$fgetemptyView(this.mediaPages[r34 ? 1 : 0]).setStickerType(11);
        MediaPage.m12957$$Nest$fgetemptyView(this.mediaPages[r34 ? 1 : 0]).button.setVisibility(0);
        MediaPage.m12957$$Nest$fgetemptyView(this.mediaPages[r34 ? 1 : 0]).button.setText(addPostText(), false);
        goto L430
    L434:
        MediaPage.m12957$$Nest$fgetemptyView(r414).stickerView.setVisibility(0);
        MediaPage.m12957$$Nest$fgetemptyView(this.mediaPages[r34 ? 1 : 0]).setStickerType(1);
        MediaPage.m12957$$Nest$fgetemptyView(this.mediaPages[r34 ? 1 : 0]).title.setText(LocaleController.getString(R.string.NoResult));
        MediaPage.m12957$$Nest$fgetemptyView(this.mediaPages[r34 ? 1 : 0]).subtitle.setText(LocaleController.getString(R.string.SearchEmptyViewFilteredSubtitle2));
        MediaPage.m12957$$Nest$fgetemptyView(this.mediaPages[r34 ? 1 : 0]).button.setVisibility(8);
        r12 = 8;
        goto L470
    L358:
        if (CommonGroupsAdapter.m12949$$Nest$fgetendReached(this.commonGroupsAdapter) == true) goto L419;
        if (CommonGroupsAdapter.m12948$$Nest$fgetchats(this.commonGroupsAdapter).isEmpty() == false) goto L419;
        CommonGroupsAdapter.m12951$$Nest$mgetChats(this.commonGroupsAdapter, 0, 100);
        goto L419
    L363:
        if (r411 == 7) goto L419;
        boolean r417 = isAnyStoryPageType(r411);
        MediaPage[] r117 = this.mediaPages;
        if (r417 == false) goto L388;
        StoriesAdapter r418 = storyAlbums_getStoriesAdapterByTabType(r117[r34 ? 1 : 0].selectedType);
        if (r418 == null) goto L419;
        StoriesController.StoriesList r517 = r418.storiesList;
        r418.load(false);
        StickerEmptyView r419 = MediaPage.m12957$$Nest$fgetemptyView(this.mediaPages[r34 ? 1 : 0]);
        if (r517 != null) goto L372;
    L378:
        boolean r77 = false;
    L379:
        r419.showProgress(r77, r34);
        if (r517 != null) goto L382;
    L386:
        boolean r420 = false;
    L387:
        r57 = r420;
        goto L419
    L382:
        if (r517.getCount() <= 0) goto L386;
        if (isSearchingStories() == true) goto L386;
        r420 = true;
        goto L387
    L372:
        if (r517.isLoading() == false) goto L374;
    L377:
        r77 = true;
        goto L379
    L374:
        if (hasInternet() == false) goto L378;
        if (r517.getCount() <= 0) goto L378;
    L388:
        int r421 = r117[r34 ? 1 : 0].selectedType;
        if (r421 == 10) goto L419;
        if (r421 == 11) goto L419;
        if (r421 == 12) goto L419;
        if (r421 == 13) goto L419;
        if (r421 == 14) goto L419;
        if (r421 != 15) goto L404;
        r421 = 8;
    L404:
        SharedMediaData r118 = this.sharedMediaData[r421];
        if (r118.loading == true) goto L419;
        if (r118.endReached[0] == true) goto L419;
        if (r118.messages.isEmpty() == false) goto L419;
        this.sharedMediaData[r421].loading = true;
        this.documentsAdapter.notifyDataSetChanged();
        if (r421 != 0) goto L417;
        int r119 = this.sharedMediaData[0].filterType;
        if (r119 != 1) goto L415;
        int r242 = 6;
    L418:
        this.profileActivity.getMediaDataController().loadMedia(this.dialog_id, 50, 0, 0, r242, this.topicId, 1, this.profileActivity.getClassGuid(), this.sharedMediaData[r242].requestIndex, null, null);
        goto L419
    L415:
        if (r119 != 2) goto L417;
        r242 = 7;
    L417:
        r242 = r421;
        goto L418
    L343:
        this.searchItemState = 0;
        ActionBarMenuItem r422 = this.searchItem;
        if (r422 == null) goto L353;
        if (isStoriesView() == false) goto L348;
    L351:
        int r78 = 8;
    L352:
        r422.setVisibility(r78);
        goto L353
    L348:
        if (this.searching == true) goto L351;
        r78 = 4;
        goto L352
    L289:
        if (r410 == 11) goto L303;
        if (isAnyStoryPageType(r410) == true) goto L303;
        int r423 = this.mediaPages[r34 ? 1 : 0].selectedType;
        if (r423 == 2) goto L303;
        if (r423 == 5) goto L303;
        if (r423 == 6) goto L303;
        if (r423 == 7) goto L301;
    L304:
        int r424 = this.mediaPages[r34 ? 1 : 0].selectedType;
        if (r424 == 10) goto L303;
        if (r424 == 13) goto L303;
        if (r424 == 14) goto L303;
        ActionBarMenuItem r425 = this.searchItem;
        if (r34 == false) goto L329;
        if (r425 != null) goto L314;
    L327:
        this.searchItemState = 0;
        this.searchAlpha = 1.0f;
    L326:
        r10 = 4;
    L340:
        updateOptionsSearch();
        goto L353
    L314:
        if (r425.getVisibility() != 4) goto L327;
        if (this.actionBar.isSearchFieldVisible() == true) goto L327;
        if (canShowSearchItem() == false) goto L320;
        this.searchItemState = 1;
        this.searchItem.setVisibility(0);
    L325:
        float r426 = r34 ? 1.0f : 0.0f;
        this.searchAlpha = getSearchAlpha(r426);
        updateSearchItemIcon(1.0f - r426);
        goto L326
    L320:
        ActionBarMenuItem r427 = this.searchItem;
        if (isStoriesView() == false) goto L323;
        int r79 = 8;
    L324:
        r427.setVisibility(r79);
        goto L325
    L323:
        r79 = 4;
        goto L324
    L329:
        if (r425 == null) goto L326;
        r10 = 4;
        if (r425.getVisibility() != 4) goto L340;
        if (canShowSearchItem() == false) goto L335;
        this.searchItemState = 0;
        this.searchAlpha = 1.0f;
        this.searchItem.setVisibility(0);
        goto L340
    L335:
        ActionBarMenuItem r428 = this.searchItem;
        if (isStoriesView() == false) goto L338;
        int r710 = 8;
    L339:
        r428.setVisibility(r710);
        this.searchAlpha = 0.0f;
        goto L340
    L338:
        r710 = 4;
        goto L339
    L301:
        if (this.delegate.canSearchMembers() == false) goto L303;
    L282:
        if (this.mediaPages[r34 ? 1 : 0].selectedType == 14) goto L286;
        ViewParent r429 = r49.getParent();
        MediaPage r711 = this.mediaPages[r34 ? 1 : 0];
        if (r429 != r711) goto L286;
        r711.removeView(this.giftsContainer);
        goto L286
    L275:
        if (this.mediaPages[r34 ? 1 : 0].selectedType == 13) goto L279;
        ViewParent r430 = r48.getParent();
        MediaPage r712 = this.mediaPages[r34 ? 1 : 0];
        if (r430 != r712) goto L279;
        r712.removeView(this.botPreviewsContainer);
        goto L279
    L268:
        if (this.mediaPages[r34 ? 1 : 0].selectedType == 12) goto L272;
        if (r47.getParent() != this.mediaPages[r34 ? 1 : 0]) goto L272;
        this.savedMessagesContainer.chatActivity.onRemoveFromParent();
        this.mediaPages[r34 ? 1 : 0].removeView(this.savedMessagesContainer);
        goto L272
    L260:
        MediaPage.m12961$$Nest$fgetlistView(r46).setItemAnimator(null);
        if (this.savedDialogsAdapter == null) goto L265;
        InternalListView r431 = MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]);
        SavedDialogsAdapter r84 = this.savedDialogsAdapter;
        if (r431 != r84.attachedToRecyclerView) goto L265;
        ItemTouchHelper r432 = r84.itemTouchHelper;
        r84.attachedToRecyclerView = null;
        r432.attachToRecyclerView(null);
        goto L265
    L256:
        r45.setClipToOutline(false);
        this.mediaPages[r34 ? 1 : 0].setBackground(null);
        goto L257
    L252:
        MediaPage.m12961$$Nest$fgetlistView(r74[r34 ? 1 : 0]).disableSections();
        goto L253
    L248:
        r25 = 12.0f;
        goto L249
    L245:
        r24 = 12.0f;
        goto L246
    L241:
        r19 = 12;
        goto L242
    L238:
        r222 = 12.0f;
        goto L239
    L231:
        if (isAnyStoryPageType(r8) == true) goto L234;
        r82 = r16;
        goto L235
    L131:
        if (r35.fastScrollPeriods.isEmpty() == true) goto L133;
        r36 = true;
        goto L135
    L139:
        if (r516 != 2) goto L149;
        SharedMediaData r38 = this.sharedMediaData[2];
        if (r38.fastScrollDataLoaded == true) goto L143;
    L145:
        r36 = r16;
    L147:
        if (r42 == this.voiceAdapter) goto L137;
        recycleAdapter(r42);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setAdapter(this.voiceAdapter);
        goto L137
    L143:
        if (r38.fastScrollPeriods.isEmpty() == true) goto L145;
        r36 = true;
        goto L147
    L149:
        if (r516 == 3) goto L151;
        if (r516 != 4) goto L165;
        SharedMediaData r39 = this.sharedMediaData[4];
        if (r39.fastScrollDataLoaded == true) goto L158;
    L160:
        r36 = r16;
    L162:
        if (r42 == this.audioAdapter) goto L137;
        recycleAdapter(r42);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setAdapter(this.audioAdapter);
        goto L137
    L158:
        if (r39.fastScrollPeriods.isEmpty() == true) goto L160;
        r36 = true;
        goto L162
    L165:
        if (r516 != 5) goto L170;
        if (r42 == this.gifAdapter) goto L174;
        recycleAdapter(r42);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setAdapter(this.gifAdapter);
    L174:
        r44 = r16;
        r57 = r44;
        goto L138
    L170:
        if (r516 != 15) goto L176;
        if (r42 == this.pollAdapter) goto L174;
        recycleAdapter(r42);
        this.pollAdapter.listView = MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setAdapter(this.pollAdapter);
        goto L174
    L176:
        if (r516 != 6) goto L181;
        if (r42 != this.commonGroupsAdapter) goto L179;
    L153:
        r44 = true;
        r57 = r16;
        goto L138
    L179:
        recycleAdapter(r42);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setAdapter(this.commonGroupsAdapter);
        goto L153
    L181:
        if (r516 == 7) goto L183;
        boolean r310 = isAnyStoryPageType(r516);
        MediaPage[] r518 = this.mediaPages;
        if (r310 == false) goto L194;
        StoriesAdapter r311 = storyAlbums_getStoriesAdapterByTabType(r518[r34 ? 1 : 0].selectedType);
        if (r42 == r311) goto L190;
        recycleAdapter(r42);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setAdapter(r311);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).updateEmptyViewAnimated();
    L190:
        MediaPage r312 = this.mediaPages[r34 ? 1 : 0];
        if (r312.selectedType == 9) goto L193;
        this.storiesReorder.attachToRecyclerView(MediaPage.m12961$$Nest$fgetlistView(r312));
    L193:
        r37 = this.mediaColumnsCount[1];
        r44 = r16;
        r57 = r44;
        goto L228
    L194:
        int r313 = r518[r34 ? 1 : 0].selectedType;
        if (r313 != 10) goto L200;
        if (r42 == this.channelRecommendationsAdapter) goto L153;
        recycleAdapter(r42);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setAdapter(this.channelRecommendationsAdapter);
        goto L153
    L200:
        if (r313 == 11) goto L202;
        if (r313 != 12) goto L211;
        if (r42 == null) goto L209;
        recycleAdapter(r42);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setAdapter(null);
    L209:
        if (this.savedMessagesContainer.getParent() == this.mediaPages[r34 ? 1 : 0]) goto L174;
        AndroidUtilities.removeFromParent(this.savedMessagesContainer);
        this.mediaPages[r34 ? 1 : 0].addView(this.savedMessagesContainer, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, 56.0f, 0.0f, 0.0f));
        goto L174
    L211:
        if (r313 != 13) goto L220;
        if (r42 == null) goto L214;
        recycleAdapter(r42);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setAdapter(null);
    L214:
        BotPreviewsEditContainer r314 = this.botPreviewsContainer;
        if (r314 == null) goto L174;
        if (r314.getParent() == this.mediaPages[r34 ? 1 : 0]) goto L174;
        AndroidUtilities.removeFromParent(this.botPreviewsContainer);
        this.mediaPages[r34 ? 1 : 0].addView(this.botPreviewsContainer);
        goto L174
    L220:
        if (r313 != 14) goto L174;
        if (r42 == null) goto L223;
        recycleAdapter(r42);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setAdapter(null);
    L223:
        ProfileGiftsContainer r315 = this.giftsContainer;
        if (r315 == null) goto L174;
        if (r315.getParent() == this.mediaPages[r34 ? 1 : 0]) goto L174;
        AndroidUtilities.removeFromParent(this.giftsContainer);
        this.mediaPages[r34 ? 1 : 0].addView(this.giftsContainer);
        MediaPage.m12957$$Nest$fgetemptyView(this.mediaPages[r34 ? 1 : 0]).setVisibility(4);
        goto L174
    L202:
        if (r42 == this.savedDialogsAdapter) goto L204;
        recycleAdapter(r42);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setAdapter(this.savedDialogsAdapter);
        SavedDialogsAdapter r26 = this.savedDialogsAdapter;
        ?? r316 = r26.itemTouchHelper;
        InternalListView r433 = MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]);
        r26.attachedToRecyclerView = r433;
        r316.attachToRecyclerView(r433);
    L204:
        r23 = this.savedDialogsAdapter.viewPool;
        goto L153
    L183:
        if (r42 == this.chatUsersAdapter) goto L153;
        recycleAdapter(r42);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setAdapter(this.chatUsersAdapter);
        goto L153
    L151:
        if (r42 == this.linksAdapter) goto L153;
        recycleAdapter(r42);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setAdapter(this.linksAdapter);
        goto L153
    L115:
        if (r42 == this.photoVideoAdapter) goto L117;
        recycleAdapter(r42);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setAdapter(this.photoVideoAdapter);
    L117:
        int r27 = -AndroidUtilities.dp(1.0f);
        r32.rightMargin = r27;
        r32.leftMargin = r27;
        SharedMediaData r28 = this.sharedMediaData[r16];
        if (r28.fastScrollDataLoaded == true) goto L120;
    L122:
        boolean r29 = r16;
    L123:
        r37 = this.mediaColumnsCount[r16];
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r34 ? 1 : 0]).setPinnedHeaderShadowDrawable(this.pinnedHeaderShadowDrawable);
        SharedMediaData r434 = this.sharedMediaData[r16];
        if (r434.recycledViewPool != null) goto L126;
        r434.recycledViewPool = new RecyclerView.RecycledViewPool();
    L126:
        r57 = r29;
        r23 = this.sharedMediaData[r16].recycledViewPool;
        r44 = r16;
        goto L228
    L120:
        if (r28.fastScrollPeriods.isEmpty() == true) goto L122;
        r29 = true;
        goto L123
    L110:
        if (isStoryAlbumPageType(r514) == false) goto L112;
    L106:
        r16 = 0;
        goto L107
    }

    private void updateForwardItem() {
        if (this.forwardItem != null) goto L6;
        return;
    L6:
        if (this.profileActivity.getMessagesController().isPeerNoForwards(this.dialog_id) == false) goto L8;
    L11:
        boolean r0 = true;
    L12:
        ActionBarMenuItem r1 = this.forwardItem;
        if (r0 == false) goto L15;
        float r2 = 0.5f;
    L16:
        r1.setAlpha(r2);
        if (r0 == true) goto L19;
    L22:
        if (r0 == false) goto L24;
        return;
    L24:
        if (this.forwardItem.getBackground() != null) goto L28;
        this.forwardItem.setBackground(Theme.createSelectorDrawable(getThemedColor(Theme.key_actionBarActionModeDefaultSelector), 5));
        return;
    L28:
        return;
    L19:
        if (this.forwardItem.getBackground() == null) goto L22;
        this.forwardItem.setBackground(null);
        return;
    L15:
        r2 = 1.0f;
        goto L16
    L8:
        if (hasNoforwardsMessage() == true) goto L11;
        r0 = false;
        goto L12
    }

    private void updateOptionsSearch() {
        updateOptionsSearch(false);
    }

    private void updateOptionsSearch(boolean r6) {
        RLottieImageView r0 = this.optionsSearchImageView;
        if (r0 != null) goto L5;
        return;
    L5:
        float r2 = 0.0f;
        if (this.searching == true) goto L17;
        ProfileGiftsContainer r1 = this.giftsContainer;
        if (r1 != null) goto L10;
    L11:
        ProfileStoriesCollectionTabs r12 = this.storiesContainer;
        if (r12 != null) goto L14;
    L16:
        r2 = Utilities.clamp(this.searchAlpha + this.optionsAlpha, 1.0f, 0.0f);
        goto L17
    L14:
        if (r12.isReordering() == false) goto L16;
    L10:
        if (r1.isReordering() == false) goto L11;
    L17:
        r0.setAlpha(r2);
        boolean r02 = false;
        if (r6 == false) goto L25;
        if (getPhotoVideoOptionsAlpha(1.0f) <= 0.5f) goto L22;
        r02 = true;
    L22:
        animateSearchToOptions(r02, true);
        return;
    L25:
        if (this.searchItemState != 2) goto L32;
        if (this.optionsAlpha <= 0.1f) goto L29;
        r02 = true;
    L29:
        animateSearchToOptions(r02, true);
        return;
    L32:
        if (this.searchAlpha >= 0.1f) goto L34;
        r02 = true;
    L34:
        animateSearchToOptions(r02, true);
    }

    private void updateRowsSelection(boolean r7) {
        int r1 = 0;
    L3:
        MediaPage[] r2 = this.mediaPages;
        if (r1 >= r2.length) goto L27;
        int r22 = MediaPage.m12961$$Nest$fgetlistView(r2[r1]).getChildCount();
        int r3 = 0;
    L6:
        if (r3 >= r22) goto L26;
        View r4 = MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r1]).getChildAt(r3);
        if ((r4 instanceof SharedDocumentCell) == false) goto L11;
        ((SharedDocumentCell) r4).setChecked(false, r7);
    L25:
        r3 = r3 + 1;
        goto L6
    L11:
        if ((r4 instanceof SharedPhotoVideoCell2) == false) goto L14;
        ((SharedPhotoVideoCell2) r4).setChecked(false, r7);
        goto L25
    L14:
        if ((r4 instanceof SharedLinkCell) == false) goto L17;
        ((SharedLinkCell) r4).setChecked(false, r7);
        goto L25
    L17:
        if ((r4 instanceof SharedAudioCell) == false) goto L20;
        ((SharedAudioCell) r4).setChecked(false, r7);
        goto L25
    L20:
        if ((r4 instanceof ContextLinkCell) == false) goto L23;
        ((ContextLinkCell) r4).setChecked(false, r7);
        goto L25
    L23:
        if ((r4 instanceof DialogCell) == false) goto L25;
        ((DialogCell) r4).setChecked(false, r7);
        goto L25
    L26:
        r1 = r1 + 1;
        goto L3
    }

    private void updateStoriesPinButton() {
        int r1 = 8;
        if (isBot() == false) goto L12;
        ActionBarMenuItem r0 = this.pinItem;
        if (r0 == null) goto L7;
        r0.setVisibility(8);
    L7:
        ActionBarMenuItem r5 = this.unpinItem;
        if (r5 == null) goto L60;
        r5.setVisibility(8);
        return;
    L60:
        return;
    L12:
        if (getClosestTab() != 9) goto L21;
        ActionBarMenuItem r02 = this.pinItem;
        if (r02 == null) goto L16;
        r02.setVisibility(8);
    L16:
        ActionBarMenuItem r52 = this.unpinItem;
        if (r52 == null) goto L61;
        r52.setVisibility(8);
        return;
    L61:
        return;
    L21:
        if (getClosestTab() != 8) goto L47;
        int r2 = 0;
    L24:
        if (r2 >= this.selectedFiles[0].size()) goto L33;
        MessageObject r3 = this.selectedFiles[0].valueAt(r2);
        StoriesAdapter r4 = this.storiesAdapter;
        if (r4 == null) goto L32;
        StoriesController.StoriesList r42 = r4.storiesList;
        if (r42 == null) goto L32;
        if (r42.isPinned(r3.getId()) == true) goto L32;
        boolean r22 = true;
    L34:
        ActionBarMenuItem r32 = this.pinItem;
        if (r32 == null) goto L40;
        if (r22 == false) goto L38;
        int r43 = 0;
    L39:
        r32.setVisibility(r43);
        goto L40
    L38:
        r43 = 8;
    L40:
        ActionBarMenuItem r53 = this.unpinItem;
        if (r53 == null) goto L62;
        if (r22 == true) goto L44;
        r1 = 0;
    L44:
        r53.setVisibility(r1);
        return;
    L62:
        return;
    L32:
        r2 = r2 + 1;
        goto L24
    L33:
        r22 = false;
        goto L34
    L47:
        if (isStoryAlbumPageType(getClosestTab()) == false) goto L63;
        ActionBarMenuItem r03 = this.pinItem;
        if (r03 == null) goto L51;
        r03.setVisibility(8);
    L51:
        ActionBarMenuItem r54 = this.unpinItem;
        if (r54 == null) goto L64;
        r54.setVisibility(8);
        return;
    L64:
        return;
    }

    public boolean addActionButtons() {
        return true;
    }

    public void animateSearchToOptions(boolean r3, boolean r4) {
        RLottieImageView r0 = this.optionsSearchImageView;
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.animatingToOptions == r3) goto L23;
        this.animatingToOptions = r3;
        if (r3 == false) goto L10;
    L12:
        RLottieDrawable r32 = this.optionsSearchImageView.getAnimatedDrawable();
        if (this.animatingToOptions == false) goto L15;
        int r02 = 50;
    L16:
        r32.setCustomEndFrame(r02);
    L17:
        RLottieImageView r33 = this.optionsSearchImageView;
        if (r4 == false) goto L21;
        r33.getAnimatedDrawable().start();
        return;
    L21:
        r33.getAnimatedDrawable().setCurrentFrame(this.optionsSearchImageView.getAnimatedDrawable().getCustomEndFrame());
        return;
    L15:
        r02 = 100;
        goto L16
    L10:
        if (r0.getAnimatedDrawable().getCurrentFrame() >= 20) goto L12;
        this.optionsSearchImageView.getAnimatedDrawable().setCustomEndFrame(0);
        goto L17
    }

    @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
    public boolean canClickButtonInside() {
        return false;
    }

    public boolean canEditStories() {
        if (isBot() == false) goto L13;
        TLRPC.User r5 = MessagesController.getInstance(this.profileActivity.getCurrentAccount()).getUser(Long.valueOf(this.dialog_id));
        if (r5 != null) goto L7;
    L11:
        return false;
    L7:
        if (r5.bot == false) goto L11;
        if (r5.bot_can_edit == false) goto L11;
        return true;
    L13:
        if (isStoriesView() == true) goto L20;
        BaseFragment r0 = this.profileActivity;
        if (r0 != null) goto L17;
    L19:
        return false;
    L17:
        if (r0.getMessagesController().getStoriesController().canEditStories(this.dialog_id) == false) goto L19;
    L20:
        return true;
    }

    public boolean canShowSearchItem() {
        return true;
    }

    public boolean canZoomIn() {
        MediaPage[] r0 = this.mediaPages;
        if (r0 == null) goto L10;
        MediaPage r02 = r0[0];
        if (r02 == null) goto L10;
        boolean r03 = isAnyStoryPageType(r02.selectedType);
        int r2 = this.mediaColumnsCount[r03 ? 1 : 0];
        if (r2 == getNextMediaColumnsCount(r03 ? 1 : 0, r2, true)) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean canZoomOut() {
        MediaPage[] r0 = this.mediaPages;
        if (r0 == null) goto L15;
        MediaPage r02 = r0[0];
        if (r02 == null) goto L15;
        if (this.allowStoriesSingleColumn == true) goto L9;
    L11:
        boolean r03 = isAnyStoryPageType(this.mediaPages[0].selectedType);
        int r2 = this.mediaColumnsCount[r03 ? 1 : 0];
        if (r2 == getNextMediaColumnsCount(r03 ? 1 : 0, r2, false)) goto L15;
        return true;
    L9:
        if (isAnyStoryPageType(r02.selectedType) == false) goto L11;
    L15:
        return false;
    }

    public boolean checkPinchToZoom(MotionEvent r15) {
        int r0 = this.mediaPages[0].selectedType;
        if (r0 != 13) goto L8;
        BotPreviewsEditContainer r2 = this.botPreviewsContainer;
        if (r2 == null) goto L8;
        return r2.checkPinchToZoom(r15);
    L8:
        if (r0 == 0) goto L12;
        if (isAnyStoryPageType(r0) == true) goto L12;
    L13:
        return false;
    L12:
        if (getParent() == null) goto L13;
        if (this.photoVideoChangeColumnsAnimation == false) goto L20;
        if (this.isInPinchToZoomTouchMode == true) goto L20;
        return true;
    L20:
        if (r15.getActionMasked() == 0) goto L104;
        if (r15.getActionMasked() == 5) goto L104;
        if (r15.getActionMasked() != 2) goto L93;
        if (this.isInPinchToZoomTouchMode == false) goto L29;
    L30:
        int r7 = -1;
        int r8 = -1;
        int r3 = 0;
    L32:
        if (r3 >= r15.getPointerCount()) goto L40;
        if (this.pointerId1 != r15.getPointerId(r3)) goto L37;
        r7 = r3;
    L37:
        if (this.pointerId2 != r15.getPointerId(r3)) goto L39;
        r8 = r3;
    L39:
        r3 = r3 + 1;
        goto L32
    L40:
        if (r7 == (-1)) goto L90;
        if (r8 == (-1)) goto L90;
        float r02 = ((float) Math.hypot(r15.getX(r8) - r15.getX(r7), r15.getY(r8) - r15.getY(r7))) / this.pinchStartDistance;
        this.pinchScale = r02;
        if (this.isInPinchToZoomTouchMode == true) goto L55;
        if (r02 <= 1.01f) goto L48;
    L49:
        this.isInPinchToZoomTouchMode = true;
        if (r02 <= 1.0f) goto L52;
        boolean r03 = true;
    L53:
        this.pinchScaleUp = r03;
        startPinchToMediaColumnsCount(r03);
        goto L55
    L52:
        r03 = false;
        goto L53
    L48:
        if (r02 < 0.99f) goto L49;
    L55:
        if (this.isInPinchToZoomTouchMode == false) goto L116;
        boolean r04 = this.pinchScaleUp;
        if (r04 == true) goto L59;
    L60:
        if (r04 == false) goto L62;
    L64:
        float r32 = this.pinchScale;
        if (r04 == false) goto L67;
        float r05 = 1.0f - ((2.0f - r32) / 1.0f);
    L68:
        this.photoVideoChangeColumnsProgress = Math.max(0.0f, Math.min(1.0f, r05));
    L69:
        float r06 = this.photoVideoChangeColumnsProgress;
        if (r06 == 1.0f) goto L74;
        if (r06 == 0.0f) goto L74;
    L87:
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[0]).invalidate();
        MediaPage r152 = this.mediaPages[0];
        if (r152.fastScrollHintView == null) goto L116;
        r152.invalidate();
    L74:
        if (isAnyStoryPageType(this.changeColumnsTab) == false) goto L76;
        RecyclerView.Adapter r07 = storyAlbums_getStoriesAdapterByTabType(this.changeColumnsTab);
    L78:
        if (this.photoVideoChangeColumnsProgress != 1.0f) goto L83;
        int r4 = (int) (MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[0]).getMeasuredWidth() / this.animateToColumnsCount);
        float r6 = this.startedTrackingX / (MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[0]).getMeasuredWidth() - r4);
        int r33 = (((int) Math.ceil(this.pinchCenterPosition / this.animateToColumnsCount)) * this.animateToColumnsCount) + ((int) (r6 * (r4 - 1)));
        if (r33 < r07.getItemCount()) goto L82;
        r33 = r07.getItemCount() - 1;
    L82:
        this.pinchCenterPosition = r33;
    L83:
        finishPinchToMediaColumnsCount();
        if (this.photoVideoChangeColumnsProgress != 0.0f) goto L86;
        this.pinchScaleUp = !this.pinchScaleUp;
    L86:
        startPinchToMediaColumnsCount(this.pinchScaleUp);
        this.pinchStartDistance = (float) Math.hypot(r15.getX(1) - r15.getX(0), r15.getY(1) - r15.getY(0));
        goto L87
    L76:
        r07 = this.photoVideoAdapter;
        goto L78
    L67:
        r05 = (1.0f - r32) / 0.5f;
        goto L68
    L62:
        if (this.pinchScale <= 1.0f) goto L64;
    L63:
        this.photoVideoChangeColumnsProgress = 0.0f;
        goto L69
    L59:
        if (this.pinchScale < 1.0f) goto L63;
    L116:
        return this.isInPinchToZoomTouchMode;
    L90:
        this.maybePinchToZoomTouchMode = false;
        this.maybePinchToZoomTouchMode2 = false;
        this.isInPinchToZoomTouchMode = false;
        finishPinchToMediaColumnsCount();
        return false;
    L29:
        if (this.maybePinchToZoomTouchMode2 == true) goto L30;
    L93:
        if (r15.getActionMasked() == 1) goto L101;
        if (r15.getActionMasked() != 6) goto L99;
        if (checkPointerIds(r15) == true) goto L101;
    L99:
        if (r15.getActionMasked() != 3) goto L116;
    L101:
        if (this.isInPinchToZoomTouchMode == false) goto L116;
        this.maybePinchToZoomTouchMode2 = false;
        this.maybePinchToZoomTouchMode = false;
        this.isInPinchToZoomTouchMode = false;
        finishPinchToMediaColumnsCount();
    L104:
        if (this.maybePinchToZoomTouchMode == false) goto L111;
        if (this.isInPinchToZoomTouchMode == true) goto L111;
        if (r15.getPointerCount() != 2) goto L111;
        this.pinchStartDistance = (float) Math.hypot(r15.getX(1) - r15.getX(0), r15.getY(1) - r15.getY(0));
        this.pinchScale = 1.0f;
        this.pointerId1 = r15.getPointerId(0);
        this.pointerId2 = r15.getPointerId(1);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[0]).cancelClickRunnables(false);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[0]).cancelLongPress();
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[0]).dispatchTouchEvent(MotionEvent.obtain(0, 0, 3, 0.0f, 0.0f, 0));
        View r08 = (View) getParent();
        this.pinchCenterX = (int) (((((int) ((r15.getX(0) + r15.getX(1)) / 2.0f)) - r08.getX()) - getX()) - this.mediaPages[0].getX());
        int r09 = (int) (((((int) ((r15.getY(0) + r15.getY(1)) / 2.0f)) - r08.getY()) - getY()) - this.mediaPages[0].getY());
        this.pinchCenterY = r09;
        selectPinchPosition(this.pinchCenterX, r09);
        this.maybePinchToZoomTouchMode2 = true;
    L111:
        if (r15.getActionMasked() != 0) goto L116;
        if ((((r15.getY() - ((View) getParent()).getY()) - getY()) - this.mediaPages[0].getY()) <= 0.0f) goto L116;
        this.maybePinchToZoomTouchMode = true;
        goto L116
    }

    public boolean checkTabsAnimationInProgress() {
        if (this.tabsAnimationInProgress == false) goto L25;
        boolean r0 = this.backAnimation;
        MediaPage[] r2 = this.mediaPages;
        int r3 = -1;
        if (r0 == false) goto L13;
        if (Math.abs(r2[0].getTranslationX()) >= 1.0f) goto L23;
        this.mediaPages[0].setTranslationX(0.0f);
        MediaPage[] r02 = this.mediaPages;
        MediaPage r22 = r02[1];
        int r03 = r02[0].getMeasuredWidth();
        if (this.animatingForward == false) goto L11;
        r3 = 1;
    L11:
        r22.setTranslationX(r03 * r3);
    L19:
        AnimatorSet r04 = this.tabsAnimation;
        if (r04 == null) goto L22;
        r04.cancel();
        this.tabsAnimation = null;
    L22:
        this.tabsAnimationInProgress = false;
    L23:
        onTabProgress(getTabProgress());
        return this.tabsAnimationInProgress;
    L13:
        if (Math.abs(r2[1].getTranslationX()) >= 1.0f) goto L23;
        MediaPage r05 = this.mediaPages[0];
        int r23 = r05.getMeasuredWidth();
        if (this.animatingForward == true) goto L18;
        r3 = 1;
    L18:
        r05.setTranslationX(r23 * r3);
        this.mediaPages[1].setTranslationX(0.0f);
        goto L19
    L25:
        return false;
    }

    public boolean closeActionMode() {
        return closeActionMode(true);
    }

    public boolean closeActionMode(boolean r5) {
        if (this.isActionModeShowed == false) goto L14;
        int r2 = 1;
    L5:
        if (r2 < 0) goto L7;
        this.selectedFiles[r2].clear();
        r2 = r2 - 1;
        goto L5
    L7:
        this.cantDeleteMessagesCount = 0;
        onActionModeSelectedUpdate(this.selectedFiles[0]);
        BotPreviewsEditContainer r22 = this.botPreviewsContainer;
        if (r22 == null) goto L10;
        r22.unselectAll();
        this.botPreviewsContainer.updateSelection(true);
    L10:
        showActionMode(false);
        updateRowsSelection(r5);
        SavedDialogsAdapter r4 = this.savedDialogsAdapter;
        if (r4 == null) goto L13;
        r4.unselectAll();
    L13:
        return true;
    L14:
        return false;
    }

    public boolean customTabs() {
        return false;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r34, int r35, Object... r36) {
        int r13 = 0;
        int r132 = 0;
        int r133 = 0;
        int r134 = 0;
        boolean r135 = false;
        int r136 = 0;
        int r14 = 1;
        if (r34 != NotificationCenter.mediaDidLoad) goto L170;
        long r1 = ((Long) r36[0]).longValue();
        int r15 = ((Integer) r36[3]).intValue();
        int r7 = ((Integer) r36[7]).intValue();
        int r9 = ((Integer) r36[4]).intValue();
        boolean r10 = ((Boolean) r36[6]).booleanValue();
        if (r9 == 6) goto L7;
        if (r9 == 7) goto L7;
    L9:
        if (r15 != this.profileActivity.getClassGuid()) goto L134;
        if (r7 != this.sharedMediaData[r9].requestIndex) goto L134;
        ArrayList r342 = (ArrayList) r36[2];
        boolean r3 = DialogObject.isEncryptedDialog(this.dialog_id);
        if (r1 != this.dialog_id) goto L15;
        int r12 = 0;
    L16:
        if (r9 == 0) goto L21;
        if (r9 == 1) goto L21;
        if (r9 == 2) goto L21;
        if (r9 == 4) goto L21;
        this.sharedMediaData[r9].setTotalCount(r12, ((Integer) r36[1]).intValue());
    L21:
        if (r9 != 0) goto L24;
        RecyclerView.Adapter r2 = this.photoVideoAdapter;
    L23:
        int r25 = r9;
    L38:
        int r4 = this.sharedMediaData[r25].messages.size();
        if (r2 == null) goto L43;
        int r5 = r2.getItemCount();
        if ((r2 instanceof RecyclerListView.SectionsAdapter) == false) goto L44;
        ((RecyclerListView.SectionsAdapter) r2).notifySectionsChanged();
    L44:
        this.sharedMediaData[r25].loading = false;
        SparseBooleanArray r72 = new SparseBooleanArray();
        if (r10 == false) goto L57;
        int r8 = r342.size() - 1;
    L47:
        if (r8 < 0) goto L54;
        ArrayList r92 = r342;
        MessageObject r11 = (MessageObject) r92.get(r8);
        if (this.sharedMediaData[r25].addMessage(r11, r12, true, r3) == false) goto L53;
        r72.put(r11.getId(), true);
        SharedMediaData r112 = this.sharedMediaData[r25];
        SharedMediaData.m12994$$Nest$fputstartOffset(r112, SharedMediaData.m12992$$Nest$fgetstartOffset(r112) - 1);
        if (SharedMediaData.m12992$$Nest$fgetstartOffset(this.sharedMediaData[r25]) >= 0) goto L53;
        SharedMediaData.m12994$$Nest$fputstartOffset(this.sharedMediaData[r25], 0);
    L53:
        r8 = r8 - 1;
        r342 = r92;
        goto L47
    L54:
        this.sharedMediaData[r25].startReached = ((Boolean) r36[5]).booleanValue();
        SharedMediaData r32 = this.sharedMediaData[r25];
        if (r32.startReached == false) goto L77;
        SharedMediaData.m12994$$Nest$fputstartOffset(r32, 0);
    L77:
        if (r10 == true) goto L84;
        if (r12 != 0) goto L84;
        SharedMediaData r33 = this.sharedMediaData[r25];
        if (r33.endReached[r12] == false) goto L84;
        if (this.mergeDialogId == 0) goto L84;
        r33.loading = true;
        this.profileActivity.getMediaDataController().loadMedia(this.mergeDialogId, 50, this.sharedMediaData[r25].max_id[1], 0, r25, this.topicId, 1, this.profileActivity.getClassGuid(), this.sharedMediaData[r25].requestIndex, null, null);
    L84:
        int r93 = r25;
        if (r2 == null) goto L126;
        int r37 = 0;
        InternalListView r16 = null;
    L87:
        MediaPage[] r6 = this.mediaPages;
        if (r37 >= r6.length) goto L97;
        MediaPage r62 = r6[r37];
        if (r62 == null) goto L96;
        if (MediaPage.m12961$$Nest$fgetlistView(r62) == null) goto L96;
        if (MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r37]).getAdapter() != r2) goto L96;
        r16 = MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r37]);
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r37]).stopScroll();
    L96:
        r37 = r37 + 1;
        goto L87
    L97:
        int r38 = r2.getItemCount();
        SharedPhotoVideoAdapter r63 = this.photoVideoAdapter;
        if (r2 == r63) goto L100;
        r2.notifyDataSetChanged();     // Catch: Throwable -> L439
    L105:
        if (this.sharedMediaData[r93].messages.isEmpty() == true) goto L107;
    L110:
        if (r16 != null) goto L112;
    L115:
        if (r16 == null) goto L126;
        if (this.sharedMediaData[r93].loadingAfterFastScroll == true) goto L126;
        if (r4 != 0) goto L125;
        int r22 = 0;
    L120:
        if (r22 >= 2) goto L126;
        if (this.mediaPages[r22].selectedType != 0) goto L124;
        ((LinearLayoutManager) r16.getLayoutManager()).scrollToPositionWithOffset(this.photoVideoAdapter.getPositionForIndex(0), 0);
    L124:
        r22 = r22 + 1;
        goto L120
    L125:
        saveScrollPosition();
        goto L126
    L112:
        if (r2 == this.photoVideoAdapter) goto L114;
        if (r38 < r5) goto L115;
    L114:
        animateItemsEnter(r16, r5, r72);
        goto L115
    L107:
        if (this.sharedMediaData[r93].loading == true) goto L110;
        if (r16 == null) goto L115;
        animateItemsEnter(r16, r5, r72);
        goto L115
    L100:
        if (r63.getItemCount() != r5) goto L102;
        AndroidUtilities.updateVisibleRows(r16);
        goto L105
    L102:
        this.photoVideoAdapter.notifyDataSetChanged();
    L126:
        SharedMediaData r17 = this.sharedMediaData[r93];
        if (r17.loadingAfterFastScroll == true) goto L129;
    L132:
        this.scrolling = true;
        return;
    L129:
        if (r17.messages.size() != 0) goto L131;
        loadFromStart(r93);
        goto L132
    L131:
        this.sharedMediaData[r93].loadingAfterFastScroll = false;
        goto L132
    L57:
        int r82 = 0;
    L59:
        if (r82 >= r342.size()) goto L66;
        MessageObject r113 = (MessageObject) r342.get(r82);
        if (this.sharedMediaData[r25].addMessage(r113, r12, false, r3) == false) goto L65;
        r72.put(r113.getId(), true);
        SharedMediaData r114 = this.sharedMediaData[r25];
        SharedMediaData.m12993$$Nest$fputendLoadingStubs(r114, SharedMediaData.m12989$$Nest$fgetendLoadingStubs(r114) - 1);
        if (SharedMediaData.m12989$$Nest$fgetendLoadingStubs(this.sharedMediaData[r25]) >= 0) goto L65;
        SharedMediaData.m12993$$Nest$fputendLoadingStubs(this.sharedMediaData[r25], 0);
    L65:
        r82 = r82 + 1;
        goto L59
    L66:
        SharedMediaData r39 = this.sharedMediaData[r25];
        if (r39.loadingAfterFastScroll == true) goto L69;
    L71:
        this.sharedMediaData[r25].endReached[r12] = ((Boolean) r36[5]).booleanValue();
        SharedMediaData r310 = this.sharedMediaData[r25];
        if (r310.endReached[r12] == false) goto L77;
        int r311 = r310.messagesDict[r12].size();
        if (r12 != 0) goto L76;
        r311 = r311 + SharedMediaData.m12992$$Nest$fgetstartOffset(this.sharedMediaData[r25]);
    L76:
        this.sharedMediaData[r25].setTotalCount(r12, r311);
        goto L77
    L69:
        if (r39.messages.size() <= 0) goto L71;
        SharedMediaData r312 = this.sharedMediaData[r25];
        r312.min_id = r312.messages.get(0).getId();
        goto L71
    L43:
        r5 = 0;
        goto L44
    L24:
        if (r9 != 1) goto L26;
        r2 = this.documentsAdapter;
        goto L23
    L26:
        if (r9 != 2) goto L28;
        r2 = this.voiceAdapter;
        goto L23
    L28:
        if (r9 != 3) goto L30;
        r2 = this.linksAdapter;
        goto L23
    L30:
        if (r9 != 4) goto L32;
        r2 = this.audioAdapter;
        goto L23
    L32:
        if (r9 != 5) goto L35;
        r2 = this.gifAdapter;
        goto L23
    L35:
        if (r9 != 15) goto L37;
        r2 = this.pollAdapter;
        r9 = 8;
        goto L23
    L37:
        r25 = r9;
        r2 = null;
        goto L38
    L15:
        r12 = 1;
    L134:
        if (this.sharedMediaPreloader != null) goto L136;
        return;
    L136:
        if (this.sharedMediaData[r9].messages.isEmpty() == true) goto L138;
        return;
    L138:
        if (this.sharedMediaData[r9].loadingAfterFastScroll == false) goto L140;
        return;
    L140:
        if (fillMediaData(r9) == false) goto L508;
        if (r9 != 0) goto L143;
        RecyclerView.Adapter r73 = this.photoVideoAdapter;
    L157:
        if (r73 == null) goto L169;
    L158:
        MediaPage[] r18 = this.mediaPages;
        if (r132 >= r18.length) goto L168;
        MediaPage r19 = r18[r132];
        if (r19 == null) goto L167;
        if (MediaPage.m12961$$Nest$fgetlistView(r19) == null) goto L167;
        if (MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r132]).getAdapter() != r73) goto L167;
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r132]).stopScroll();
    L167:
        r132 = r132 + 1;
        goto L158
    L168:
        r73.notifyDataSetChanged();
    L169:
        this.scrolling = true;
        return;
    L143:
        if (r9 != 1) goto L145;
        r73 = this.documentsAdapter;
        goto L157
    L145:
        if (r9 != 2) goto L147;
        r73 = this.voiceAdapter;
        goto L157
    L147:
        if (r9 != 3) goto L149;
        r73 = this.linksAdapter;
        goto L157
    L149:
        if (r9 != 4) goto L151;
        r73 = this.audioAdapter;
        goto L157
    L151:
        if (r9 != 5) goto L154;
        r73 = this.gifAdapter;
        goto L157
    L154:
        if (r9 != 15) goto L156;
        r73 = this.pollAdapter;
        goto L157
    L156:
        r73 = null;
        goto L157
    L508:
        return;
    L7:
        r9 = 0;
        goto L9
    L170:
        int r313 = -1;
        if (r34 != NotificationCenter.messagesDeleted) goto L232;
        if (((Boolean) r36[2]).booleanValue() == false) goto L176;
        return;
    L176:
        if (DialogObject.isChatDialog(this.dialog_id) == false) goto L178;
        TLRPC.Chat r74 = this.profileActivity.getMessagesController().getChat(Long.valueOf(-this.dialog_id));
    L179:
        long r110 = ((Long) r36[1]).longValue();
        if (ChatObject.isChannel(r74) == false) goto L190;
        if (r110 != 0) goto L187;
        if (this.mergeDialogId == 0) goto L187;
        int r111 = 1;
    L192:
        ArrayList r23 = (ArrayList) r36[0];
        int r42 = r23.size();
        int r52 = 0;
        boolean r64 = false;
    L193:
        if (r52 >= r42) goto L202;
        int r75 = 0;
        boolean r65 = r64;
    L195:
        SharedMediaData[] r83 = this.sharedMediaData;
        if (r75 >= r83.length) goto L201;
        if (r83[r75].deleteMessage(((Integer) r23.get(r52)).intValue(), r111) == null) goto L200;
        r313 = r75;
        r65 = true;
    L200:
        r75 = r75 + 1;
        r65 = r65;
        goto L195
    L201:
        r52 = r52 + 1;
        r64 = r65;
        goto L193
    L202:
        if (r64 == false) goto L229;
        this.scrolling = true;
        SharedPhotoVideoAdapter r115 = this.photoVideoAdapter;
        if (r115 == null) goto L206;
        r115.notifyDataSetChanged();
    L206:
        SharedDocumentsAdapter r116 = this.documentsAdapter;
        if (r116 == null) goto L209;
        r116.notifyDataSetChanged();
    L209:
        SharedDocumentsAdapter r117 = this.voiceAdapter;
        if (r117 == null) goto L212;
        r117.notifyDataSetChanged();
    L212:
        SharedLinksAdapter r118 = this.linksAdapter;
        if (r118 == null) goto L215;
        r118.notifyDataSetChanged();
    L215:
        SharedDocumentsAdapter r119 = this.audioAdapter;
        if (r119 == null) goto L218;
        r119.notifyDataSetChanged();
    L218:
        PollAdapter r120 = this.pollAdapter;
        if (r120 == null) goto L221;
        r120.notifyDataSetChanged();
    L221:
        GifAdapter r121 = this.gifAdapter;
        if (r121 == null) goto L224;
        r121.notifyDataSetChanged();
    L224:
        if (r313 == 0) goto L228;
        if (r313 == 1) goto L228;
        if (r313 == 2) goto L228;
        if (r313 != 4) goto L229;
    L228:
        loadFastScrollData(true);
    L229:
        getMediaPage(r313);
        return;
    L187:
        if (r110 != r74.id) goto L510;
    L188:
        r111 = 0;
        goto L192
    L510:
        return;
    L190:
        if (r110 == 0) goto L188;
        return;
    L178:
        r74 = null;
        goto L179
    L232:
        if (r34 != NotificationCenter.didReceiveNewMessages) goto L289;
        if (((Boolean) r36[2]).booleanValue() == true) goto L522;
        long r122 = ((Long) r36[0]).longValue();
        long r43 = this.dialog_id;
        if (r122 != r43) goto L511;
        ArrayList r123 = (ArrayList) r36[1];
        boolean r24 = DialogObject.isEncryptedDialog(r43);
        int r44 = 0;
        boolean r53 = false;
    L240:
        if (r44 >= r123.size()) goto L259;
        MessageObject r94 = (MessageObject) r123.get(r44);
        if (MessageObject.getMedia(r94.messageOwner) == null) goto L258;
        if (r94.needDrawBluredPreview() == true) goto L258;
        int r102 = MediaDataController.getMediaType(r94.messageOwner);
        if (r102 == r313) goto L526;
        SharedMediaData r152 = this.sharedMediaData[r102];
        if (r152.startReached == false) goto L258;
        if (r94.getDialogId() != this.dialog_id) goto L254;
        int r66 = 0;
    L256:
        if (r152.addMessage(r94, r66, true, r24) == false) goto L258;
        this.hasMedia[r102] = 1;
        r53 = true;
        goto L258
    L254:
        r66 = 1;
        goto L256
    L526:
        return;
    L258:
        r44 = r44 + 1;
        r313 = -1;
        r53 = r53;
        goto L240
    L259:
        if (r53 == false) goto L512;
        this.scrolling = true;
    L261:
        MediaPage[] r124 = this.mediaPages;
        if (r133 >= r124.length) goto L286;
        int r125 = r124[r133].selectedType;
        if (r125 != 0) goto L268;
        RecyclerView.Adapter r126 = this.photoVideoAdapter;
    L283:
        if (r126 == null) goto L285;
        r126.getItemCount();
        this.photoVideoAdapter.notifyDataSetChanged();
        this.documentsAdapter.notifyDataSetChanged();
        this.voiceAdapter.notifyDataSetChanged();
        this.linksAdapter.notifyDataSetChanged();
        this.audioAdapter.notifyDataSetChanged();
        this.pollAdapter.notifyDataSetChanged();
        this.gifAdapter.notifyDataSetChanged();
    L285:
        r133 = r133 + 1;
        goto L261
    L268:
        if (r125 != 1) goto L270;
        r126 = this.documentsAdapter;
        goto L283
    L270:
        if (r125 != 2) goto L272;
        r126 = this.voiceAdapter;
        goto L283
    L272:
        if (r125 != 3) goto L274;
        r126 = this.linksAdapter;
        goto L283
    L274:
        if (r125 != 4) goto L277;
        r126 = this.audioAdapter;
        goto L283
    L277:
        if (r125 != 5) goto L280;
        r126 = this.gifAdapter;
        goto L283
    L280:
        if (r125 != 15) goto L282;
        r126 = this.pollAdapter;
        goto L283
    L282:
        r126 = null;
        goto L283
    L286:
        updateTabs(true);
        return;
    L512:
        return;
    L511:
        return;
    L522:
        return;
    L289:
        if (r34 != NotificationCenter.messageReceivedByServer) goto L304;
        if (((Boolean) r36[6]).booleanValue() == true) goto L523;
        Integer r127 = (Integer) r36[0];
        Integer r26 = (Integer) r36[1];
        Long r314 = (Long) r36[3];
        if (r314.longValue() == this.dialog_id) goto L298;
        if (r314.longValue() == this.mergeDialogId) goto L298;
        return;
    L298:
        if (r314.longValue() != this.dialog_id) goto L300;
        r14 = 0;
    L300:
        SharedMediaData[] r315 = this.sharedMediaData;
        if (r134 >= r315.length) goto L527;
        r315[r134].replaceMid(r14, r127.intValue(), r26.intValue());
        r134 = r134 + 1;
        goto L300
    L527:
        return;
    L523:
        return;
    L304:
        if (r34 == NotificationCenter.messagePlayingDidStart) goto L402;
        if (r34 == NotificationCenter.messagePlayingPlayStateChanged) goto L402;
        if (r34 == NotificationCenter.messagePlayingDidReset) goto L402;
        if (r34 != NotificationCenter.storiesListUpdated) goto L332;
        StoriesController.StoriesList r128 = (StoriesController.StoriesList) r36[0];
        MediaPage r27 = getMediaPage(storyAlbums_getTabTypeByStoriesList(r128));
        if (r27 == null) goto L323;
        boolean r316 = r27.fastScrollEnabled;
        if (r128.getCount() <= 0) goto L317;
        boolean r45 = true;
    L318:
        if (r316 == r45) goto L323;
        if (r128.getCount() <= 0) goto L322;
        r135 = true;
    L322:
        r27.fastScrollEnabled = r135;
        updateFastScrollVisibility(r27, true);
        goto L323
    L317:
        r45 = false;
    L323:
        if (r27 == null) goto L327;
        AndroidUtilities.notifyDataSetChanged(MediaPage.m12961$$Nest$fgetlistView(r27));
        if ((MediaPage.m12961$$Nest$fgetlistView(r27).getLayoutManager() instanceof LinearLayoutManager) == false) goto L327;
        checkLoadMoreScroll(r27, MediaPage.m12961$$Nest$fgetlistView(r27), (LinearLayoutManager) MediaPage.m12961$$Nest$fgetlistView(r27).getLayoutManager());
    L327:
        Delegate r0 = this.delegate;
        if (r0 == null) goto L514;
        r0.updateSelectedMediaTabText();
        return;
    L514:
        return;
    L332:
        if (r34 != NotificationCenter.storiesUpdated) goto L355;
        MediaPage[] r129 = this.mediaPages;
        int r28 = r129.length;
        int r317 = 0;
    L334:
        if (r317 >= r28) goto L528;
        MediaPage r46 = r129[r317];
        if (r46 == null) goto L353;
        if (MediaPage.m12961$$Nest$fgetlistView(r46) == null) goto L353;
        if (isAnyStoryPageType(r46.selectedType) == false) goto L353;
        if (isBot() == true) goto L344;
    L346:
        int r54 = 0;
    L348:
        if (r54 >= MediaPage.m12961$$Nest$fgetlistView(r46).getChildCount()) goto L353;
        View r67 = MediaPage.m12961$$Nest$fgetlistView(r46).getChildAt(r54);
        if ((r67 instanceof SharedPhotoVideoCell2) == false) goto L352;
        ((SharedPhotoVideoCell2) r67).updateViews();
    L352:
        r54 = r54 + 1;
        goto L348
    L344:
        if (MediaPage.m12961$$Nest$fgetlistView(r46).getAdapter() == null) goto L346;
        AndroidUtilities.notifyDataSetChanged(MediaPage.m12961$$Nest$fgetlistView(r46));
    L353:
        r317 = r317 + 1;
        goto L334
    L528:
        return;
    L355:
        if (r34 != NotificationCenter.channelRecommendationsLoaded) goto L361;
        if (((Long) r36[0]).longValue() != this.dialog_id) goto L515;
        this.channelRecommendationsAdapter.update(true);
        updateTabs(true);
        checkCurrentTabValid();
        return;
    L515:
        return;
    L361:
        if (r34 != NotificationCenter.savedMessagesDialogsUpdate) goto L369;
        long r130 = this.dialog_id;
        if (r130 != 0) goto L365;
    L366:
        this.savedDialogsAdapter.update(true);
        updateTabs(true);
        checkCurrentTabValid();
        onSelectedTabChanged();
        return;
    L365:
        if (r130 == this.profileActivity.getUserConfig().getClientUserId()) goto L366;
        return;
    L369:
        if (r34 != NotificationCenter.dialogsNeedReload) goto L373;
        this.savedDialogsAdapter.update(true);
        return;
    L373:
        if (r34 != NotificationCenter.starUserGiftsLoaded) goto L379;
        if (((Long) r36[0]).longValue() != this.dialog_id) goto L517;
        updateTabs(true);
        return;
    L517:
        return;
    L379:
        if (r34 != NotificationCenter.updatedChatRanks) goto L393;
        long r131 = ((Long) r36[0]).longValue();
        long r318 = ((Long) r36[1]).longValue();
        if (this.dialog_id != (-r131)) goto L524;
        String r137 = (String) r36[2];
        ChatUsersAdapter r29 = this.chatUsersAdapter;
        if (r29 == null) goto L518;
        r29.updateRank(r318, r137);
    L386:
        MediaPage[] r138 = this.mediaPages;
        if (r136 >= r138.length) goto L529;
        MediaPage r139 = r138[r136];
        if (r139.selectedType != 7) goto L391;
        AndroidUtilities.updateVisibleRows(MediaPage.m12961$$Nest$fgetlistView(r139));
    L391:
        r136 = r136 + 1;
        goto L386
    L529:
        return;
    L518:
        return;
    L524:
        return;
    L393:
        if (r34 != NotificationCenter.didUpdatePollResults) goto L519;
        long r47 = ((Long) r36[0]).longValue();
        TLRPC.TL_poll r68 = (TLRPC.TL_poll) r36[1];
        TLRPC.PollResults r76 = (TLRPC.PollResults) r36[2];
    L395:
        MediaPage[] r140 = this.mediaPages;
        if (r13 >= r140.length) goto L530;
        RecyclerView.Adapter r141 = MediaPage.m12961$$Nest$fgetlistView(r140[r13]).getAdapter();
        PollAdapter r210 = this.pollAdapter;
        if (r141 != r210) goto L400;
        r210.update(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r13]), r47, r68, r76);
    L400:
        r13 = r13 + 1;
        goto L395
    L530:
        return;
    L519:
        return;
    L402:
        if (r34 != NotificationCenter.messagePlayingDidReset) goto L404;
    L421:
        int r142 = 0;
    L422:
        MediaPage[] r211 = this.mediaPages;
        if (r142 >= r211.length) goto L532;
        MediaPage r212 = r211[r142];
        if (r212 == null) goto L437;
        if (MediaPage.m12961$$Nest$fgetlistView(r212) == null) goto L437;
        int r213 = MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r142]).getChildCount();
        int r319 = 0;
    L430:
        if (r319 >= r213) goto L437;
        View r48 = MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r142]).getChildAt(r319);
        if ((r48 instanceof SharedAudioCell) == false) goto L436;
        SharedAudioCell r49 = (SharedAudioCell) r48;
        if (r49.getMessage() == null) goto L436;
        r49.updateButtonState(false, true);
    L436:
        r319 = r319 + 1;
    L437:
        r142 = r142 + 1;
        goto L422
    L532:
        return;
    L404:
        if (r34 == NotificationCenter.messagePlayingPlayStateChanged) goto L421;
        if (((MessageObject) r36[0]).eventId != 0) goto L525;
        int r143 = 0;
    L410:
        MediaPage[] r214 = this.mediaPages;
        if (r143 >= r214.length) goto L531;
        int r215 = MediaPage.m12961$$Nest$fgetlistView(r214[r143]).getChildCount();
        int r320 = 0;
    L413:
        if (r320 >= r215) goto L420;
        View r410 = MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r143]).getChildAt(r320);
        if ((r410 instanceof SharedAudioCell) == false) goto L419;
        SharedAudioCell r411 = (SharedAudioCell) r410;
        if (r411.getMessage() == null) goto L419;
        r411.updateButtonState(false, true);
    L419:
        r320 = r320 + 1;
        goto L413
    L420:
        r143 = r143 + 1;
        goto L410
    L531:
        return;
    }

    public void disableScroll(boolean r2) {
        if (r2 == false) goto L4;
        stopScroll(null);
    L4:
        this.disableScrolling = r2;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r3) {
        if (this.scrollSlidingTextTabStrip == null) goto L5;
        r3.save();
        r3.translate(this.scrollSlidingTextTabStrip.getX(), this.scrollSlidingTextTabStrip.getY());
        this.scrollSlidingTextTabStrip.drawBackground(r3);
        r3.restore();
    L5:
        super.dispatchDraw(r3);
        FragmentContextView r0 = this.fragmentContextView;
        if (r0 != null) goto L8;
        return;
    L8:
        if (r0.isCallStyle() == true) goto L10;
        return;
    L10:
        if (this.topPanelLayout != null) goto L15;
        r3.save();
        r3.translate(this.fragmentContextView.getX(), this.fragmentContextView.getY());
        this.fragmentContextView.setDrawOverlay(true);
        this.fragmentContextView.draw(r3);
        this.fragmentContextView.setDrawOverlay(false);
        r3.restore();
        return;
    }

    public boolean dispatchFastScrollEvent(MotionEvent r5) {
        View r0 = (View) getParent();
        r5.offsetLocation(((-r0.getX()) - getX()) - MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[0]).getFastScroll().getX(), (((-r0.getY()) - getY()) - this.mediaPages[0].getY()) - MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[0]).getFastScroll().getY());
        return MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[0]).getFastScroll().dispatchTouchEvent(r5);
    }

    public void drawBackgroundWithBlur(Canvas r1, float r2, Rect r3, Paint r4) {
        r1.drawRect(r3, r4);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r5, View r6, long r7) {
        if (r6 != this.fragmentContextView) goto L16;
        if (this.topPanelLayout != null) goto L16;
        r5.save();
        float r0 = this.mediaPages[0].getTop();
        if (this.storiesContainer == null) goto L13;
        int r1 = this.mediaPages[0].selectedType;
        if (r1 != 8) goto L11;
    L12:
        r0 = r0 - this.storiesContainer.getVisualHeight();
        goto L13
    L11:
        if (isStoryAlbumPageType(r1) == true) goto L12;
    L13:
        r5.clipRect(0.0f, r0, r6.getMeasuredWidth(), (r6.getMeasuredHeight() + r0) + AndroidUtilities.dp(12.0f));
        boolean r4 = super.drawChild(r5, r6, r7);
        r5.restore();
        return r4;
    L16:
        return super.drawChild(r5, r6, r7);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void drawListForBlur(Canvas r9, ArrayList<SizeNotifierFrameLayout.IViewWithInvalidateCallback> r10) {
        int r1 = 0;
    L3:
        MediaPage[] r2 = this.mediaPages;
        if (r1 >= r2.length) goto L22;
        MediaPage r22 = r2[r1];
        if (r22 == null) goto L21;
        if (r22.getVisibility() != 0) goto L21;
        int r23 = 0;
    L11:
        if (r23 >= MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r1]).getChildCount()) goto L21;
        View r3 = MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r1]).getChildAt(r23);
        if (r3.getY() >= (MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r1]).blurTopPadding + AndroidUtilities.dp(100.0f))) goto L20;
        int r4 = r9.save();
        r9.translate(this.mediaPages[r1].getX() + r3.getX(), ((getY() + this.mediaPages[r1].getY()) + MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r1]).getY()) + r3.getY());
        r3.draw(r9);
        if (r10 != null) goto L17;
    L19:
        r9.restoreToCount(r4);
        goto L20
    L17:
        if ((r3 instanceof SizeNotifierFrameLayout.IViewWithInvalidateCallback) == false) goto L19;
        r10.add((SizeNotifierFrameLayout.IViewWithInvalidateCallback) r3);
    L20:
        r23 = r23 + 1;
    L21:
        r1 = r1 + 1;
        goto L3
    }

    @Override // android.view.View
    public void forceHasOverlappingRendering(boolean r1) {
        super.forceHasOverlappingRendering(r1);
    }

    public SparseArray<MessageObject> getActionModeSelected() {
        return this.selectedFiles[0];
    }

    public String getBotPreviewsSubtitle(boolean r6) {
        if (isBot() == false) goto L5;
        if (r6 == false) goto L11;
        BotPreviewsEditContainer r62 = this.botPreviewsContainer;
        if (r62 == null) goto L11;
        return r62.getBotPreviewsSubtitle();
    L11:
        StoriesAdapter r63 = this.storiesAdapter;
        if (r63 != null) goto L14;
    L29:
        int r1 = 0;
        int r2 = 0;
    L30:
        if (r1 != 0) goto L34;
        if (r2 != 0) goto L34;
        return LocaleController.getString(R.string.BotPreviewEmpty);
    L34:
        StringBuilder r5 = new StringBuilder();
        if (r1 <= 0) goto L37;
        r5.append(LocaleController.formatPluralString("Images", r1, new Object[0]));
    L37:
        if (r2 <= 0) goto L43;
        if (r5.length() <= 0) goto L41;
        r5.append(", ");
    L41:
        r5.append(LocaleController.formatPluralString("Videos", r2, new Object[0]));
    L43:
        return r5.toString();
    L14:
        if (r63.storiesList == null) goto L29;
        int r64 = 0;
        r1 = 0;
        r2 = 0;
    L17:
        if (r64 >= this.storiesAdapter.storiesList.messageObjects.size()) goto L30;
        MessageObject r3 = this.storiesAdapter.storiesList.messageObjects.get(r64);
        TL_stories.StoryItem r4 = r3.storyItem;
        if (r4 == null) goto L28;
        TLRPC.MessageMedia r42 = r4.media;
        if (r42 == null) goto L28;
        if (MessageObject.isVideoDocument(r42.document) == false) goto L26;
        r2 = r2 + 1;
        goto L28
    L26:
        if (r3.storyItem.media.photo == null) goto L28;
        r1 = r1 + 1;
    L28:
        r64 = r64 + 1;
        goto L17
    L5:
        return LocaleController.getString(R.string.BotPreviewEmpty);
    }

    public float getBottomButtonStoriesVisibility() {
        MediaPage[] r0 = this.mediaPages;
        float r1 = 1.0f;
        if (r0 == null) goto L51;
        MediaPage r3 = r0[0];
        if (r3 == null) goto L51;
        if (r0[1] == null) goto L51;
        if (MediaPage.m12957$$Nest$fgetemptyView(r3) == null) goto L51;
        if (MediaPage.m12957$$Nest$fgetemptyView(this.mediaPages[1]) == null) goto L51;
        MediaPage[] r02 = this.mediaPages;
        int r32 = r02[0].selectedType;
        int r03 = r02[1].selectedType;
        if (isStoryAlbumPageType(r32) == true) goto L18;
        if (r32 == 8) goto L18;
        boolean r5 = false;
    L20:
        if (isStoryAlbumPageType(r03) == true) goto L24;
        if (r03 == 8) goto L24;
        boolean r7 = false;
    L25:
        if (r5 == true) goto L28;
        if (r7 == true) goto L28;
        return 1.0f;
    L28:
        float r8 = 1.0f - MediaPage.m12957$$Nest$fgetemptyView(this.mediaPages[0]).getVisibilityFactor();
        float r4 = 1.0f - MediaPage.m12957$$Nest$fgetemptyView(this.mediaPages[1]).getVisibilityFactor();
        StoriesAdapter r9 = storyAlbums_getStoriesAdapterByTabType(this.mediaPages[0].selectedType);
        if (r32 == 8) goto L35;
        if (r9 == null) goto L36;
        StoriesController.StoriesList r33 = r9.storiesList;
        if (r33 == null) goto L36;
        if (r33.getCount() > 0) goto L35;
    L36:
        StoriesAdapter r34 = storyAlbums_getStoriesAdapterByTabType(r03);
        if (r03 == 8) goto L45;
        if (r34 == null) goto L44;
        StoriesController.StoriesList r04 = r34.storiesList;
        if (r04 == null) goto L44;
        if (r04.getCount() > 0) goto L45;
    L44:
        r1 = r4;
    L45:
        if (r5 == true) goto L47;
        r8 = r1;
    L47:
        if (r7 == true) goto L50;
        r1 = r8;
    L50:
        return AndroidUtilities.lerp(r8, r1, Math.abs(this.mediaPages[0].getTranslationX() / this.mediaPages[0].getMeasuredWidth()));
    L35:
        r8 = 1.0f;
    L24:
        r7 = true;
    L18:
        r5 = true;
    L51:
        return 1.0f;
    }

    public int getClosestTab() {
        MediaPage r0 = this.mediaPages[1];
        if (r0 == null) goto L17;
        if (r0.getVisibility() != 0) goto L17;
        if (this.tabsAnimationInProgress == false) goto L13;
        if (this.backAnimation == true) goto L13;
        return this.mediaPages[1].selectedType;
    L13:
        if (Math.abs(this.mediaPages[1].getTranslationX()) >= (this.mediaPages[1].getMeasuredWidth() / 2.0f)) goto L17;
        return this.mediaPages[1].selectedType;
    L17:
        return getSelectedTab();
    }

    public RecyclerListView getCurrentListView() {
        MediaPage r0 = this.mediaPages[0];
        int r1 = r0.selectedType;
        if (r1 != 13) goto L7;
        return this.botPreviewsContainer.getCurrentListView();
    L7:
        if (r1 != 14) goto L11;
        return this.giftsContainer.getCurrentListView();
    L11:
        if (r1 != 12) goto L17;
        ChatActivityContainer r3 = this.savedMessagesContainer;
        if (r3 == null) goto L17;
        return r3.chatActivity.getChatListView();
    L17:
        return MediaPage.m12961$$Nest$fgetlistView(r0);
    }

    public int getInitialTab() {
        return 0;
    }

    public int getNextMediaColumnsCount(int r4, int r5, boolean r6) {
        int r0 = 1;
        if (r6 == true) goto L5;
        int r1 = 1;
    L6:
        int r52 = r5 + r1;
        if (r52 <= 6) goto L12;
        if (r6 == true) goto L10;
        r52 = 9;
        goto L12
    L10:
        r52 = 6;
    L12:
        if (this.allowStoriesSingleColumn == false) goto L15;
        if (r4 != 1) goto L15;
    L17:
        return Utilities.clamp(r52, 9, r0);
    L15:
        r0 = 2;
        goto L17
    L5:
        r1 = -1;
        goto L6
    }

    public float getPhotoVideoOptionsAlpha(float r9) {
        float r1 = 0.0f;
        if (isArchivedOnlyStoriesView() == false) goto L5;
        return 0.0f;
    L5:
        MediaPage r0 = this.mediaPages[1];
        if (r0 == null) goto L24;
        int r02 = r0.selectedType;
        if (r02 == 0) goto L23;
        if (r02 == 8) goto L13;
        if (isStoryAlbumPageType(r02) == true) goto L13;
    L14:
        int r03 = this.mediaPages[1].selectedType;
        if (r03 == 9) goto L23;
        if (r03 == 11) goto L23;
        if (r03 == 13) goto L23;
        if (r03 != 14) goto L24;
        ProfileGiftsContainer r04 = this.giftsContainer;
        if (r04 == null) goto L24;
        if (r04.canFilter() == false) goto L24;
    L13:
        if (TextUtils.isEmpty(getStoriesHashtag()) == false) goto L14;
    L23:
        r1 = 0.0f + r9;
    L24:
        MediaPage r05 = this.mediaPages[0];
        if (r05 == null) goto L44;
        int r06 = r05.selectedType;
        if (r06 == 0) goto L43;
        if (r06 == 8) goto L32;
        if (isStoryAlbumPageType(r06) == true) goto L32;
    L33:
        int r07 = this.mediaPages[0].selectedType;
        if (r07 == 9) goto L43;
        if (r07 == 11) goto L43;
        if (r07 == 13) goto L43;
        if (r07 != 14) goto L45;
        ProfileGiftsContainer r8 = this.giftsContainer;
        if (r8 != null) goto L41;
        return r1;
    L41:
        if (r8.canFilter() == true) goto L43;
        return r1;
    L45:
        return r1;
    L32:
        if (TextUtils.isEmpty(getStoriesHashtag()) == false) goto L33;
    L43:
        return r1 + (1.0f - r9);
    L44:
        return r1;
    }

    public int getPhotosVideosTypeFilter() {
        return this.sharedMediaData[0].filterType;
    }

    public TextView getSaveItem() {
        return this.saveItem;
    }

    public float getSearchAlpha(float r5) {
        float r1 = 0.0f;
        if (isArchivedOnlyStoriesView() == false) goto L5;
        return 0.0f;
    L5:
        MediaPage r0 = this.mediaPages[1];
        if (r0 != null) goto L8;
    L12:
        MediaPage r02 = this.mediaPages[0];
        if (r02 != null) goto L15;
        return r1;
    L15:
        if (isSearchItemVisible(r02.selectedType) == true) goto L17;
        return r1;
    L17:
        if (this.mediaPages[0].selectedType != 11) goto L19;
        return r1;
    L19:
        return r1 + (1.0f - r5);
    L8:
        if (isSearchItemVisible(r0.selectedType) == false) goto L12;
        if (this.mediaPages[1].selectedType == 11) goto L12;
        r1 = 0.0f + r5;
        goto L12
    }

    public ActionBarMenuItem getSearchItem() {
        return this.searchItem;
    }

    public RLottieImageView getSearchOptionsItem() {
        return this.optionsSearchImageView;
    }

    public int getSelectedTab() {
        int r0 = this.scrollSlidingTextTabStrip.getCurrentTabId();
        ProfileStoriesCollectionTabs r1 = this.storiesContainer;
        if (r1 != null) goto L5;
    L12:
        return r0;
    L5:
        if (r0 != 8) goto L12;
        int r12 = r1.getCurrentAlbumId();
        if (r12 != 0) goto L9;
        return 8;
    L9:
        if (r12 <= 0) goto L12;
        return storyAlbums_getByAlbumId(r12).tabType;
    }

    public TL_stories.MediaArea getStoriesArea() {
        return null;
    }

    public int getStoriesCount(int r2) {
        if (isAnyStoryPageType(r2) == false) goto L10;
        StoriesAdapter r1 = storyAlbums_getStoriesAdapterByTabType(r2);
        if (r1 == null) goto L12;
        StoriesController.StoriesList r12 = r1.storiesList;
        if (r12 != null) goto L9;
        return 0;
    L9:
        return r12.getCount();
    L12:
        return 0;
    L10:
        return 0;
    }

    public String getStoriesHashtag() {
        return null;
    }

    public String getStoriesHashtagUsername() {
        return null;
    }

    @Deprecated
    public float getTabProgress() {
        float r0 = 0.0f;
        int r1 = 0;
    L3:
        MediaPage[] r2 = this.mediaPages;
        if (r1 >= r2.length) goto L9;
        MediaPage r22 = r2[r1];
        if (r22 == null) goto L8;
        r0 = r0 + (r22.selectedType * (1.0f - Math.abs(r22.getTranslationX() / getWidth())));
    L8:
        r1 = r1 + 1;
        goto L3
    L9:
        return r0;
    }

    public float getTabTranslationX(int r6, boolean r7) {
        float r0 = getWidth();
        int r1 = 0;
        int r2 = 0;
    L3:
        MediaPage[] r3 = this.mediaPages;
        if (r1 >= r3.length) goto L12;
        MediaPage r32 = r3[r1];
        if (r32 == null) goto L10;
        if (isTab(r32.selectedType, r6, r7) == false) goto L10;
        r2 = r2 + 1;
        r0 = this.mediaPages[r1].getTranslationX();
    L10:
        r1 = r1 + 1;
        goto L3
    L12:
        if (r2 != 2) goto L15;
        return 0.0f;
    L15:
        return r0;
    }

    public float getTabVisibility(int r5, boolean r6) {
        float r0 = 0.0f;
        int r1 = 0;
    L3:
        MediaPage[] r2 = this.mediaPages;
        if (r1 >= r2.length) goto L11;
        MediaPage r22 = r2[r1];
        if (r22 == null) goto L10;
        if (isTab(r22.selectedType, r5, r6) == false) goto L10;
        r0 = r0 + (1.0f - Math.abs(this.mediaPages[r1].getTranslationX() / getWidth()));
    L10:
        r1 = r1 + 1;
        goto L3
    L11:
        return r0;
    }

    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.selectedMessagesCountTextView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteGrayText2));
        RLottieImageView r4 = this.deleteItem.getIconView();
        int r5 = ThemeDescription.FLAG_IMAGECOLOR;
        int r13 = Theme.key_actionBarActionModeDefaultIcon;
        r1.add(new ThemeDescription(r4, r5, null, null, null, null, r13));
        ActionBarMenuItem r52 = this.deleteItem;
        int r6 = ThemeDescription.FLAG_BACKGROUNDFILTER;
        int r21 = Theme.key_actionBarActionModeDefaultSelector;
        r1.add(new ThemeDescription(r52, r6, null, null, null, null, r21));
        if (this.gotoItem == null) goto L6;
        r1.add(new ThemeDescription(this.gotoItem.getIconView(), ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, r13));
        r1.add(new ThemeDescription(this.gotoItem, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, r21));
    L6:
        if (this.forwardItem == null) goto L8;
        r1.add(new ThemeDescription(this.forwardItem.getIconView(), ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, r13));
        r1.add(new ThemeDescription(this.forwardItem, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, r21));
    L8:
        r1.add(new ThemeDescription(this.closeButton, ThemeDescription.FLAG_IMAGECOLOR, null, null, new Drawable[]{this.backDrawable}, null, r13));
        r1.add(new ThemeDescription(this.closeButton, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, r21));
        LinearLayout r62 = this.actionModeLayout;
        int r7 = ThemeDescription.FLAG_BACKGROUND;
        int r15 = Theme.key_windowBackgroundWhite;
        r1.add(new ThemeDescription(r62, r7, null, null, null, null, r15));
        r1.add(new ThemeDescription(this.scrollSlidingTextTabStrip, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r15));
        r1.add(new ThemeDescription(this.floatingDateView, 0, null, null, null, null, Theme.key_chat_mediaTimeBackground));
        r1.add(new ThemeDescription(this.floatingDateView, 0, null, null, null, null, Theme.key_chat_mediaTimeText));
        r1.add(new ThemeDescription(this.scrollSlidingTextTabStrip, 0, new Class[]{ScrollSlidingTextTabStrip.class}, new String[]{"selectorDrawable"}, null, null, null, Theme.key_profile_tabSelectedLine));
        r1.add(new ThemeDescription(this.scrollSlidingTextTabStrip.getTabsContainer(), ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{TextView.class}, null, null, null, Theme.key_profile_tabSelectedText));
        r1.add(new ThemeDescription(this.scrollSlidingTextTabStrip.getTabsContainer(), ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{TextView.class}, null, null, null, Theme.key_profile_tabText));
        r1.add(new ThemeDescription(this.scrollSlidingTextTabStrip.getTabsContainer(), ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, new Class[]{TextView.class}, null, null, null, Theme.key_profile_tabSelector));
        if (this.fragmentContextView == null) goto L11;
        r1.add(new ThemeDescription(this.fragmentContextView, ThemeDescription.FLAG_CHECKTAG | ThemeDescription.FLAG_BACKGROUND, new Class[]{FragmentContextView.class}, new String[]{"frameLayout"}, null, null, null, Theme.key_inappPlayerBackground));
        r1.add(new ThemeDescription(this.fragmentContextView, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{FragmentContextView.class}, new String[]{"playButton"}, null, null, null, Theme.key_inappPlayerPlayPause));
        r1.add(new ThemeDescription(this.fragmentContextView, ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{FragmentContextView.class}, new String[]{"titleTextView"}, null, null, null, Theme.key_inappPlayerTitle));
        r1.add(new ThemeDescription(this.fragmentContextView, ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_FASTSCROLL, new Class[]{FragmentContextView.class}, new String[]{"titleTextView"}, null, null, null, Theme.key_inappPlayerPerformer));
        r1.add(new ThemeDescription(this.fragmentContextView, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{FragmentContextView.class}, new String[]{"closeButton"}, null, null, null, Theme.key_inappPlayerClose));
        r1.add(new ThemeDescription(this.fragmentContextView, ThemeDescription.FLAG_BACKGROUND | ThemeDescription.FLAG_CHECKTAG, new Class[]{FragmentContextView.class}, new String[]{"frameLayout"}, null, null, null, Theme.key_returnToCallBackground));
        r1.add(new ThemeDescription(this.fragmentContextView, ThemeDescription.FLAG_CHECKTAG | ThemeDescription.FLAG_TEXTCOLOR, new Class[]{FragmentContextView.class}, new String[]{"titleTextView"}, null, null, null, Theme.key_returnToCallText));
    L11:
        int r2 = 0;
    L13:
        if (r2 >= this.mediaPages.length) goto L15;
        SharedMediaLayout$$ExternalSyntheticLambda5 r12 = new SharedMediaLayout$$ExternalSyntheticLambda5(this, r2);
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        FlickerLoadingView r152 = MediaPage.m12962$$Nest$fgetprogressView(this.mediaPages[r2]);
        int r212 = Theme.key_windowBackgroundWhite;
        r1.add(new ThemeDescription(r152, 0, null, null, null, null, r212));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(MediaPage.m12957$$Nest$fgetemptyView(this.mediaPages[r2]), ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_emptyListPlaceholder));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), ThemeDescription.FLAG_SECTIONS, new Class[]{GraySectionCell.class}, new String[]{"textView"}, null, null, null, Theme.key_graySectionText));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), ThemeDescription.FLAG_CELLBACKGROUNDCOLOR | ThemeDescription.FLAG_SECTIONS, new Class[]{GraySectionCell.class}, null, null, null, Theme.key_graySection));
        int r35 = Theme.key_progressCircle;
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), 0, new Class[]{LoadingCell.class}, new String[]{"progressBar"}, null, null, null, r35));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), ThemeDescription.FLAG_TEXTCOLOR, new Class[]{UserCell.class}, new String[]{"adminTextView"}, null, null, null, Theme.key_profile_creatorIcon));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), 0, new Class[]{UserCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayIcon));
        int r44 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), 0, new Class[]{UserCell.class}, new String[]{"nameTextView"}, null, null, null, r44));
        int r29 = Theme.key_windowBackgroundWhiteGrayText;
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), 0, new Class[]{UserCell.class}, new String[]{"statusColor"}, null, null, r12, r29));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), 0, new Class[]{UserCell.class}, new String[]{"statusOnlineColor"}, null, null, r12, Theme.key_windowBackgroundWhiteBlueText));
        Drawable[] r30 = Theme.avatarDrawables;
        int r32 = Theme.key_avatar_text;
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), 0, new Class[]{UserCell.class}, null, r30, null, r32));
        com.exteragram.messenger.utils.ui.TextPaint[] r72 = Theme.dialogs_namePaint;
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), 0, new Class[]{ProfileSearchCell.class}, null, new Paint[]{r72[0], r72[1], Theme.dialogs_searchNamePaint}, null, null, Theme.key_chats_name));
        com.exteragram.messenger.utils.ui.TextPaint[] r73 = Theme.dialogs_nameEncryptedPaint;
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), 0, new Class[]{ProfileSearchCell.class}, null, new Paint[]{r73[0], r73[1], Theme.dialogs_searchNameEncryptedPaint}, null, null, Theme.key_chats_secretName));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), 0, new Class[]{ProfileSearchCell.class}, null, Theme.avatarDrawables, null, r32));
        r1.add(new ThemeDescription(null, 0, null, null, null, r12, Theme.key_avatar_backgroundRed));
        r1.add(new ThemeDescription(null, 0, null, null, null, r12, Theme.key_avatar_backgroundOrange));
        r1.add(new ThemeDescription(null, 0, null, null, null, r12, Theme.key_avatar_backgroundViolet));
        r1.add(new ThemeDescription(null, 0, null, null, null, r12, Theme.key_avatar_backgroundGreen));
        r1.add(new ThemeDescription(null, 0, null, null, null, r12, Theme.key_avatar_backgroundCyan));
        r1.add(new ThemeDescription(null, 0, null, null, null, r12, Theme.key_avatar_backgroundBlue));
        r1.add(new ThemeDescription(null, 0, null, null, null, r12, Theme.key_avatar_backgroundPink));
        int r33 = Theme.key_windowBackgroundWhiteGrayText2;
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), ThemeDescription.FLAG_TEXTCOLOR, new Class[]{EmptyStubView.class}, new String[]{"emptyTextView"}, null, null, null, r33));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), ThemeDescription.FLAG_TEXTCOLOR, new Class[]{SharedDocumentCell.class}, new String[]{"nameTextView"}, null, null, null, r44));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), ThemeDescription.FLAG_TEXTCOLOR, new Class[]{SharedDocumentCell.class}, new String[]{"dateTextView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText3));
        int r332 = Theme.key_sharedMedia_startStopLoadIcon;
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), ThemeDescription.FLAG_PROGRESSBAR, new Class[]{SharedDocumentCell.class}, new String[]{"progressView"}, null, null, null, r332));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), ThemeDescription.FLAG_IMAGECOLOR, new Class[]{SharedDocumentCell.class}, new String[]{"statusImageView"}, null, null, null, r332));
        int r53 = Theme.key_checkbox;
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), ThemeDescription.FLAG_CHECKBOX, new Class[]{SharedDocumentCell.class}, new String[]{"checkBox"}, null, null, null, r53));
        int r132 = Theme.key_checkboxCheck;
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), ThemeDescription.FLAG_CHECKBOXCHECK, new Class[]{SharedDocumentCell.class}, new String[]{"checkBox"}, null, null, null, r132));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), ThemeDescription.FLAG_IMAGECOLOR, new Class[]{SharedDocumentCell.class}, new String[]{"thumbImageView"}, null, null, null, Theme.key_files_folderIcon));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), ThemeDescription.FLAG_TEXTCOLOR, new Class[]{SharedDocumentCell.class}, new String[]{"extTextView"}, null, null, null, Theme.key_files_iconText));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), 0, new Class[]{LoadingCell.class}, new String[]{"progressBar"}, null, null, null, r35));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), ThemeDescription.FLAG_CHECKBOX, new Class[]{SharedAudioCell.class}, new String[]{"checkBox"}, null, null, null, r53));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), ThemeDescription.FLAG_CHECKBOXCHECK, new Class[]{SharedAudioCell.class}, new String[]{"checkBox"}, null, null, null, r132));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), ThemeDescription.FLAG_TEXTCOLOR, new Class[]{SharedAudioCell.class}, Theme.chat_contextResult_titleTextPaint, null, null, r44));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), ThemeDescription.FLAG_TEXTCOLOR, new Class[]{SharedAudioCell.class}, Theme.chat_contextResult_descriptionTextPaint, null, null, r33));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), ThemeDescription.FLAG_CHECKBOX, new Class[]{SharedLinkCell.class}, new String[]{"checkBox"}, null, null, null, r53));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), ThemeDescription.FLAG_CHECKBOXCHECK, new Class[]{SharedLinkCell.class}, new String[]{"checkBox"}, null, null, null, r132));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), 0, new Class[]{SharedLinkCell.class}, new String[]{"titleTextPaint"}, null, null, null, r44));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), 0, new Class[]{SharedLinkCell.class}, null, null, null, Theme.key_windowBackgroundWhiteLinkText));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), 0, new Class[]{SharedLinkCell.class}, Theme.linkSelectionPaint, null, null, Theme.key_windowBackgroundWhiteLinkSelection));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), 0, new Class[]{SharedLinkCell.class}, new String[]{"letterDrawable"}, null, null, null, Theme.key_sharedMedia_linkPlaceholderText));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{SharedLinkCell.class}, new String[]{"letterDrawable"}, null, null, null, Theme.key_sharedMedia_linkPlaceholder));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), ThemeDescription.FLAG_CELLBACKGROUNDCOLOR | ThemeDescription.FLAG_SECTIONS, new Class[]{SharedMediaSectionCell.class}, null, null, null, r212));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), ThemeDescription.FLAG_SECTIONS, new Class[]{SharedMediaSectionCell.class}, new String[]{"textView"}, null, null, null, r44));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), 0, new Class[]{SharedMediaSectionCell.class}, new String[]{"textView"}, null, null, null, r44));
        int r333 = Theme.key_sharedMedia_photoPlaceholder;
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), 0, new Class[]{SharedPhotoVideoCell.class}, new String[]{"backgroundPaint"}, null, null, null, r333));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), ThemeDescription.FLAG_CHECKBOX, new Class[]{SharedPhotoVideoCell.class}, null, null, r12, r53));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), ThemeDescription.FLAG_CHECKBOXCHECK, new Class[]{SharedPhotoVideoCell.class}, null, null, r12, r132));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), 0, new Class[]{ContextLinkCell.class}, new String[]{"backgroundPaint"}, null, null, null, r333));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), ThemeDescription.FLAG_CHECKBOX, new Class[]{ContextLinkCell.class}, null, null, r12, r53));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), ThemeDescription.FLAG_CHECKBOXCHECK, new Class[]{ContextLinkCell.class}, null, null, r12, r132));
        r1.add(new ThemeDescription(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r2]), 0, null, null, new Drawable[]{this.pinnedHeaderShadowDrawable}, null, Theme.key_windowBackgroundGrayShadow));
        r1.add(new ThemeDescription(MediaPage.m12957$$Nest$fgetemptyView(this.mediaPages[r2]).title, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r44));
        r1.add(new ThemeDescription(MediaPage.m12957$$Nest$fgetemptyView(this.mediaPages[r2]).subtitle, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r29));
        r2 = r2 + 1;
        goto L13
    L15:
        return r1;
    }

    public boolean hasInternet() {
        if (this.profileActivity.getConnectionsManager().getConnectionState() != 3) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean includeSavedDialogs() {
        return false;
    }

    public boolean includeStories() {
        return true;
    }

    public void initBlurCapture(ViewGroup r9) {
        MediaPage[] r0 = this.mediaPages;
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        MediaPage r3 = r0[r2];
        InternalListView r5 = MediaPage.m12961$$Nest$fgetlistView(r3);
        InternalListView r6 = MediaPage.m12961$$Nest$fgetlistView(r3);
        Objects.requireNonNull(r6);
        MediaPage.m12970$$Nest$fputiBlur3Capture(r3, new ViewGroupPartRenderer(r5, r9, new SharedMediaLayout$$ExternalSyntheticLambda4(r6)));
        r2 = r2 + 1;
        goto L3
    L5:
        ProfileGiftsContainer r8 = this.giftsContainer;
        if (r8 == null) goto L10;
        r8.initBlurCapture(r9);
        return;
    }

    public void invalidateBlur() {
    }

    public boolean isActionModeShown() {
        return this.isActionModeShowed;
    }

    public boolean isArchivedOnlyStoriesView() {
        return false;
    }

    public boolean isBot() {
        if (this.dialog_id <= 0) goto L10;
        TLRPC.User r4 = MessagesController.getInstance(this.profileActivity.getCurrentAccount()).getUser(Long.valueOf(this.dialog_id));
        if (r4 == null) goto L10;
        if (r4.bot == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isCalendarItemVisible() {
        int r0 = this.mediaPages[0].selectedType;
        if (r0 != 0) goto L5;
        return true;
    L5:
        if (isAnyStoryPageType(r0) == false) goto L7;
        return true;
    L7:
        if (this.mediaPages[0].selectedType == 11) goto L13;
        return false;
    L13:
        return true;
    }

    public boolean isCurrentTabFirst() {
        if (getSelectedTab() != this.scrollSlidingTextTabStrip.getFirstTabId()) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isInFastScroll() {
        MediaPage r0 = this.mediaPages[0];
        if (r0 != null) goto L5;
    L10:
        return false;
    L5:
        if (MediaPage.m12961$$Nest$fgetlistView(r0).getFastScroll() == null) goto L10;
        if (MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[0]).getFastScroll().isPressed() == false) goto L10;
        return true;
    }

    public boolean isOptionsItemVisible() {
        int r0 = this.mediaPages[0].selectedType;
        if (r0 != 0) goto L5;
        return true;
    L5:
        if (isAnyStoryPageType(r0) == false) goto L7;
        return true;
    L7:
        if (r0 != 11) goto L9;
        return true;
    L9:
        if (r0 != 13) goto L11;
        return true;
    L11:
        if (r0 != 14) goto L17;
        ProfileGiftsContainer r3 = this.giftsContainer;
        if (r3 == null) goto L17;
        if (r3.canFilter() == false) goto L17;
        return true;
    L17:
        return false;
    }

    public boolean isPinnedToTop() {
        return this.isPinnedToTop;
    }

    public boolean isSearchItemVisible() {
        return isSearchItemVisible(this.mediaPages[0].selectedType);
    }

    public boolean isSearchItemVisible(int r2) {
        if (r2 != 7) goto L7;
        return this.delegate.canSearchMembers();
    L7:
        if (isSearchingStories() == false) goto L9;
        return false;
    L9:
        if (r2 != 0) goto L11;
    L28:
        return false;
    L11:
        if (isAnyStoryPageType(r2) == true) goto L28;
        if (r2 == 2) goto L28;
        if (r2 == 5) goto L28;
        if (r2 == 6) goto L28;
        if (r2 == 11) goto L28;
        if (r2 == 10) goto L28;
        if (r2 == 13) goto L28;
        if (r2 == 14) goto L28;
        return true;
    }

    public boolean isSearchingStories() {
        if (TextUtils.isEmpty(getStoriesHashtag()) == true) goto L5;
        return true;
    L5:
        if (getStoriesArea() != null) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean isSelf() {
        return false;
    }

    public boolean isStoriesView() {
        return false;
    }

    public boolean isSwipeBackEnabled() {
        if (canEditStories() == true) goto L5;
    L13:
        ProfileGiftsContainer r0 = this.giftsContainer;
        if (r0 != null) goto L16;
    L18:
        ProfileStoriesCollectionTabs r02 = this.storiesContainer;
        if (r02 == null) goto L24;
        if (r02.isReordering() == false) goto L24;
        return false;
    L24:
        if (this.photoVideoChangeColumnsAnimation == false) goto L26;
    L29:
        return false;
    L26:
        if (this.tabsAnimationInProgress == true) goto L29;
        return true;
    L16:
        if (r0.isReordering() == false) goto L18;
        return false;
    L5:
        if (getClosestTab() == 8) goto L11;
        if (getClosestTab() == 13) goto L11;
        if (isStoryAlbumPageType(getClosestTab()) == false) goto L13;
    L11:
        if (isActionModeShown() == false) goto L13;
        return false;
    }

    public boolean isTabZoomable(int r1) {
        if (r1 != 0) goto L4;
        return true;
    L4:
        if (isAnyStoryPageType(r1) == true) goto L10;
        return false;
    L10:
        return true;
    }

    public int mediaPageTopMargin() {
        return 0;
    }

    public void onActionBarItemClick(View r20, int r21) {
        boolean r7 = true;
        char r72 = 1;
        boolean r8 = false;
        if (r21 != 101) goto L105;
        if (isAnyStoryPageType(getSelectedTab()) == true) goto L65;
        if (getSelectedTab() == 13) goto L65;
        if (getSelectedTab() != 11) goto L56;
        SavedMessagesController r1 = this.profileActivity.getMessagesController().getSavedMessagesController();
        ArrayList r3 = new ArrayList();
        int r6 = 0;
    L13:
        if (r6 >= r1.allDialogs.size()) goto L18;
        long r9 = r1.allDialogs.get(r6).dialogId;
        if (this.savedDialogsAdapter.selectedDialogs.contains(Long.valueOf(r9)) == false) goto L17;
        r3.add(Long.valueOf(r9));
    L17:
        r6 = r6 + 1;
        goto L13
    L18:
        boolean r12 = r3.isEmpty();
        String r62 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r12 == true) goto L36;
        Long r13 = (Long) r3.get(0);
        long r92 = r13.longValue();
        if (r92 != this.profileActivity.getUserConfig().getClientUserId()) goto L23;
        boolean r11 = true;
    L24:
        boolean r112 = r11;
        if (r92 >= 0) goto L29;
        TLRPC.Chat r14 = this.profileActivity.getMessagesController().getChat(Long.valueOf(-r92));
        r112 = r11;
        if (r14 == null) goto L37;
        r62 = r14.title;
        r112 = r11;
    L37:
        AlertDialog.Builder r15 = new AlertDialog.Builder(getContext(), this.resourcesProvider);
        if (r3.size() != 1) goto L43;
        if (r112 == false) goto L41;
        int r4 = R.string.ClearHistoryMyNotesTitle;
    L42:
        String r42 = LocaleController.formatString(r4, new Object[]{r62});
    L44:
        AlertDialog.Builder r16 = r15.setTitle(r42);
        if (r3.size() != 1) goto L50;
        if (r112 == false) goto L48;
        int r43 = R.string.ClearHistoryMyNotesMessage;
    L49:
        String r44 = LocaleController.formatString(r43, new Object[]{r62});
    L51:
        AlertDialog r17 = r16.setMessage(r44).setPositiveButton(LocaleController.getString(R.string.Remove), new SharedMediaLayout$$ExternalSyntheticLambda34(this, r3)).setNegativeButton(LocaleController.getString(R.string.Cancel), null).create();
        this.profileActivity.showDialog(r17);
        TextView r0 = (TextView) r17.getButton(-1);
        if (r0 == null) goto L222;
        r0.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L222:
        return;
    L48:
        r43 = R.string.ClearHistoryMessageSingle;
        goto L49
    L50:
        r44 = LocaleController.formatPluralString("ClearHistoryMessageMultiple", r3.size(), new Object[0]);
        goto L51
    L41:
        r4 = R.string.ClearHistoryTitleSingle2;
        goto L42
    L43:
        r42 = LocaleController.formatPluralString("ClearHistoryTitleMultiple", r3.size(), new Object[0]);
        goto L44
    L29:
        if (r92 < 0) goto L37;
        TLRPC.User r18 = this.profileActivity.getMessagesController().getUser(r13);
        r112 = r11;
        if (r18 == null) goto L37;
        if (UserObject.isAnonymous(r18) == false) goto L35;
        r62 = LocaleController.getString(R.string.AnonymousForward);
        r112 = r11;
        goto L37
    L35:
        r62 = UserObject.getUserName(r18);
        r112 = r11;
        goto L37
    L23:
        r11 = false;
        goto L24
    L36:
        r112 = false;
        goto L37
    L56:
        if (DialogObject.isEncryptedDialog(this.dialog_id) == false) goto L58;
        TLRPC.EncryptedChat r63 = this.profileActivity.getMessagesController().getEncryptedChat(Integer.valueOf(DialogObject.getEncryptedChatId(this.dialog_id)));
        TLRPC.User r45 = null;
        TLRPC.Chat r5 = null;
    L62:
        AlertsCreator.createDeleteMessagesAlert(this.profileActivity, r45, r5, r63, null, this.mergeDialogId, null, this.selectedFiles, null, 0, 0, null, new SharedMediaLayout$$ExternalSyntheticLambda35(this), null, this.resourcesProvider);
        return;
    L58:
        boolean r19 = DialogObject.isUserDialog(this.dialog_id);
        BaseFragment r32 = this.profileActivity;
        if (r19 == false) goto L61;
        r45 = r32.getMessagesController().getUser(Long.valueOf(this.dialog_id));
        r5 = null;
        r63 = null;
        goto L62
    L61:
        r5 = r32.getMessagesController().getChat(Long.valueOf(-this.dialog_id));
        r45 = null;
        r63 = null;
    L65:
        if (this.selectedFiles[0] != null) goto L67;
        return;
    L67:
        if (isBot() == false) goto L88;
        BotPreviewsEditContainer r110 = this.botPreviewsContainer;
        if (r110 == null) goto L88;
        if (r110.getCurrentList() == null) goto L88;
        StoriesController.BotPreviewsList r111 = this.botPreviewsContainer.getCurrentList();
        ArrayList r2 = new ArrayList();
        int r33 = 0;
    L74:
        if (r33 >= this.selectedFiles[0].size()) goto L80;
        TL_stories.StoryItem r46 = this.selectedFiles[0].valueAt(r33).storyItem;
        if (r46 == null) goto L78;
        r2.add(r46.media);
    L78:
        r33 = r33 + 1;
        goto L74
    L80:
        if (r2.isEmpty() == true) goto L224;
        AlertDialog.Builder r34 = new AlertDialog.Builder(getContext(), this.resourcesProvider);
        if (r2.size() <= 1) goto L85;
        int r47 = R.string.DeleteBotPreviewsTitle;
    L86:
        r34.setTitle(LocaleController.getString(r47));
        r34.setMessage(LocaleController.formatPluralString("DeleteBotPreviewsSubtitle", r2.size(), new Object[0]));
        r34.setPositiveButton(LocaleController.getString(R.string.Delete), new SharedMediaLayout$$ExternalSyntheticLambda30(this, r111, r2));
        r34.setNegativeButton(LocaleController.getString(R.string.Cancel), new SharedMediaLayout$$ExternalSyntheticLambda31());
        AlertDialog r02 = r34.create();
        r02.show();
        r02.redPositive();
        return;
    L85:
        r47 = R.string.DeleteBotPreviewTitle;
        goto L86
    L224:
        return;
    L88:
        ArrayList r113 = new ArrayList();
        int r22 = 0;
    L90:
        if (r22 >= this.selectedFiles[0].size()) goto L96;
        TL_stories.StoryItem r35 = this.selectedFiles[0].valueAt(r22).storyItem;
        if (r35 == null) goto L94;
        r113.add(r35);
    L94:
        r22 = r22 + 1;
        goto L90
    L96:
        if (r113.isEmpty() == true) goto L225;
        AlertDialog.Builder r23 = new AlertDialog.Builder(getContext(), this.resourcesProvider);
        if (r113.size() <= 1) goto L101;
        int r36 = R.string.DeleteStoriesTitle;
    L102:
        r23.setTitle(LocaleController.getString(r36));
        r23.setMessage(LocaleController.formatPluralString("DeleteStoriesSubtitle", r113.size(), new Object[0]));
        r23.setPositiveButton(LocaleController.getString(R.string.Delete), new SharedMediaLayout$$ExternalSyntheticLambda32(this, r113));
        r23.setNegativeButton(LocaleController.getString(R.string.Cancel), new SharedMediaLayout$$ExternalSyntheticLambda33());
        AlertDialog r03 = r23.create();
        r03.show();
        r03.redPositive();
        return;
    L101:
        r36 = R.string.DeleteStoryTitle;
        goto L102
    L225:
        return;
    L105:
        if (r21 == 100) goto L175;
        if (r21 == 105) goto L175;
        if (r21 != 102) goto L134;
        if ((this.selectedFiles[0].size() + this.selectedFiles[1].size()) != 1) goto L231;
        SparseArray<MessageObject>[] r114 = this.selectedFiles;
        if (r114[0].size() != 1) goto L116;
        r72 = 0;
    L116:
        MessageObject r115 = r114[r72].valueAt(0);
        Bundle r24 = new Bundle();
        long r64 = r115.getDialogId();
        if (DialogObject.isEncryptedDialog(r64) == false) goto L120;
        r24.putInt("enc_id", DialogObject.getEncryptedChatId(r64));
    L128:
        r24.putInt("message_id", r115.getId());
        r24.putBoolean("need_remove_previous_same_chat_activity", false);
        ChatActivity r37 = new ChatActivity(r24);
        r37.highlightMessageId = r115.getId();
        long r10 = this.topicId;
        if (r10 == 0) goto L131;
        ForumUtilities.applyTopic(r37, MessagesStorage.TopicKey.of(r64, r10));
        r24.putInt("message_id", r115.getId());
    L131:
        this.profileActivity.presentFragment(r37, false);
        return;
    L120:
        if (DialogObject.isUserDialog(r64) == false) goto L122;
        r24.putLong("user_id", r64);
        goto L128
    L122:
        TLRPC.Chat r38 = this.profileActivity.getMessagesController().getChat(Long.valueOf(-r64));
        if (r38 != null) goto L125;
    L127:
        r24.putLong("chat_id", -r64);
        goto L128
    L125:
        if (r38.migrated_to == null) goto L127;
        r24.putLong("migrated_to", r64);
        r64 = -r38.migrated_to.channel_id;
        goto L127
    L231:
        return;
    L134:
        if (r21 == 103) goto L138;
        if (r21 == 104) goto L138;
        return;
    L138:
        if (getClosestTab() != 8) goto L153;
        StoriesAdapter r39 = this.storiesAdapter;
        if (r39 != null) goto L142;
        return;
    L142:
        if (r39.storiesList == null) goto L232;
        ArrayList<Integer> r310 = new ArrayList();
        int r48 = 0;
    L146:
        if (r48 >= this.selectedFiles[0].size()) goto L148;
        r310.add(Integer.valueOf(this.selectedFiles[0].valueAt(r48).getId()));
        r48 = r48 + 1;
        goto L146
    L148:
        if (r21 == 103) goto L151;
        r7 = false;
    L151:
        pinOnUnpinStories(r310, r7);
        closeActionMode(false);
        return;
    L232:
        return;
    L153:
        SavedMessagesController r311 = this.profileActivity.getMessagesController().getSavedMessagesController();
        ArrayList<Long> r49 = new ArrayList();
        int r52 = 0;
    L155:
        if (r52 >= r311.allDialogs.size()) goto L160;
        long r93 = r311.allDialogs.get(r52).dialogId;
        if (this.savedDialogsAdapter.selectedDialogs.contains(Long.valueOf(r93)) == false) goto L159;
        r49.add(Long.valueOf(r93));
    L159:
        r52 = r52 + 1;
        goto L155
    L160:
        if (r21 != 103) goto L162;
        boolean r116 = true;
    L164:
        if (r311.updatePinned(r49, r116, true) == true) goto L166;
        this.profileActivity.showDialog(new LimitReachedBottomSheet(this.profileActivity, getContext(), 33, this.profileActivity.getCurrentAccount(), null));
    L173:
        closeActionMode(true);
        return;
    L166:
        int r117 = 0;
    L167:
        MediaPage[] r25 = this.mediaPages;
        if (r117 >= r25.length) goto L173;
        MediaPage r26 = r25[r117];
        if (r26.selectedType == 11) goto L171;
        r117 = r117 + 1;
        goto L167
    L171:
        MediaPage.m12960$$Nest$fgetlayoutManager(r26).scrollToPositionWithOffset(0, 0);
        goto L173
    L162:
        r116 = false;
    L175:
        if (r21 != 105) goto L178;
        r8 = true;
    L178:
        if (this.userInfo == null) goto L186;
        if (this.profileActivity.getMessagesController().isUserNoForwards(this.userInfo) == false) goto L186;
        HintView r27 = this.fwdRestrictedHint;
        if (r27 == null) goto L228;
        r27.setText(LocaleController.getString(R.string.ForwardsRestrictedInfoUser));
        this.fwdRestrictedHint.showForView(r20, true);
        return;
    L228:
        return;
    L186:
        if (this.info == null) goto L200;
        TLRPC.Chat r28 = this.profileActivity.getMessagesController().getChat(Long.valueOf(this.info.id));
        if (this.profileActivity.getMessagesController().isChatNoForwards(r28) == false) goto L200;
        HintView r312 = this.fwdRestrictedHint;
        if (r312 != null) goto L192;
        return;
    L192:
        if (ChatObject.isChannel(r28) == true) goto L194;
    L196:
        String r29 = LocaleController.getString(R.string.ForwardsRestrictedInfoGroup);
    L197:
        r312.setText(r29);
        this.fwdRestrictedHint.showForView(r20, true);
        return;
    L194:
        if (r28.megagroup == true) goto L196;
        r29 = LocaleController.getString(R.string.ForwardsRestrictedInfoChannel);
    L200:
        if (hasNoforwardsMessage() == false) goto L205;
        HintView r210 = this.fwdRestrictedHint;
        if (r210 == null) goto L230;
        r210.setText(LocaleController.getString("ForwardsRestrictedInfoBot", R.string.ForwardsRestrictedInfoBot));
        this.fwdRestrictedHint.showForView(r20, true);
        return;
    L230:
        return;
    L205:
        Bundle r118 = new Bundle();
        r118.putBoolean("onlySelect", true);
        r118.putBoolean("canSelectTopics", true);
        r118.putInt("dialogsType", 3);
        r118.putBoolean("forward_noquote", r8);
        DialogsActivity r211 = new DialogsActivity(r118);
        r211.setDelegate(new SharedMediaLayout$$ExternalSyntheticLambda36(this, r8));
        this.profileActivity.presentFragment(r211);
    }

    public void onActionModeSelectedUpdate(SparseArray<MessageObject> r1) {
    }

    public void onBottomButtonVisibilityChange() {
    }

    @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
    public void onButtonClicked(DialogCell r1) {
    }

    @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
    public void onButtonLongPress(DialogCell r1) {
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration r3) {
        super.onConfigurationChanged(r3);
        int r32 = 0;
    L3:
        MediaPage[] r0 = this.mediaPages;
        if (r32 >= r0.length) goto L9;
        if (MediaPage.m12961$$Nest$fgetlistView(r0[r32]) == null) goto L8;
        MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r32]).getViewTreeObserver().addOnPreDrawListener(new AnonymousClass46(this, r32));
    L8:
        r32 = r32 + 1;
        goto L3
    }

    public void onDestroy() {
        this.observersGroup.removeAllObservers();
        StoriesAdapter r0 = this.storiesAdapter;
        if (r0 != null) goto L5;
    L7:
        StoriesAdapter r02 = this.archivedStoriesAdapter;
        if (r02 != null) goto L10;
    L12:
        Iterator<StoryAlbumData> r2 = this.storyAlbumsById.values().iterator();
    L14:
        if (r2.hasNext() == false) goto L18;
        StoriesAdapter r03 = r2.next().adapter;
        if (r03.storiesList == null) goto L14;
        r03.destroy();
        goto L14
    L18:
        return;
    L10:
        if (r02.storiesList == null) goto L12;
        r02.destroy();
        goto L12
    L5:
        if (r0.storiesList == null) goto L7;
        r0.destroy();
        goto L7
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r2) {
        if (checkTabsAnimationInProgress() == false) goto L5;
        return true;
    L5:
        if (this.scrollSlidingTextTabStrip.isAnimatingIndicator() == false) goto L7;
        return true;
    L7:
        if (onTouchEvent(r2) == true) goto L14;
        return false;
    L14:
        return true;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r11, int r12) {
        int r0 = View.MeasureSpec.getSize(r11);
        if (this.delegate.getListView() == null) goto L5;
        int r1 = this.delegate.getListView().getHeight();
    L6:
        if (r1 != 0) goto L8;
        r1 = View.MeasureSpec.getSize(r12);
    L8:
        setMeasuredDimension(r0, r1);
        int r02 = getChildCount();
        int r3 = 0;
    L9:
        if (r3 >= r02) goto L21;
        View r5 = this.getChildAt(r3);
        if (r5 != null) goto L13;
    L14:
        SharedMediaLayout r4 = this;
        int r6 = r11;
    L15:
        int r8 = r12;
    L20:
        r3 = r3 + 1;
        SharedMediaLayout this = r4;
        r11 = r6;
        r12 = r8;
        goto L9
    L13:
        if (r5.getVisibility() == 8) goto L14;
        if ((r5 instanceof MediaPage) == false) goto L19;
        r4 = this;
        r6 = r11;
        r4.measureChildWithMargins(r5, r6, 0, View.MeasureSpec.makeMeasureSpec(r1, TLObject.FLAG_30), 0);
        MediaPage r52 = (MediaPage) r5;
        MediaPage.m12961$$Nest$fgetlistView(r52).setPadding(0, MediaPage.m12961$$Nest$fgetlistView(r52).topPadding, 0, MediaPage.m12961$$Nest$fgetlistView(r52).bottomPadding);
        goto L15
    L19:
        r4 = this;
        r6 = r11;
        r8 = r12;
        r4.measureChildWithMargins(r5, r6, 0, r8, 0);
        goto L20
    L21:
        return;
    L5:
        r1 = 0;
        goto L6
    }

    public boolean onMemberClick(TLRPC.ChatParticipant r1, boolean r2, View r3) {
        return false;
    }

    public void onPageMediaProgress(float r8) {
        if (r8 == 1.0f) goto L5;
    L7:
        boolean r2 = this.animatingForward;
        MediaPage[] r3 = this.mediaPages;
        if (r2 == false) goto L10;
        r3[0].setTranslationX((-r8) * r2.getMeasuredWidth());
        this.mediaPages[1].setTranslationX(r2[0].getMeasuredWidth() - (this.mediaPages[0].getMeasuredWidth() * r8));
    L11:
        onTabProgress(getTabProgress());
        float r22 = getPhotoVideoOptionsAlpha(r8);
        this.optionsAlpha = r22;
        ImageView r32 = this.photoVideoOptionsItem;
        int r6 = 4;
        if (r22 != 0.0f) goto L14;
    L19:
        int r23 = 4;
    L20:
        r32.setVisibility(r23);
        if (this.searchItem != null) goto L23;
    L29:
        this.searchAlpha = getSearchAlpha(r8);
        updateSearchItemIconAnimated();
    L30:
        updateOptionsSearch();
        if (r8 != 1.0f) goto L42;
        MediaPage[] r82 = this.mediaPages;
        MediaPage r0 = r82[0];
        r82[0] = r82[1];
        r82[1] = r0;
        r0.setVisibility(8);
        ActionBarMenuItem r83 = this.searchItem;
        if (r83 != null) goto L35;
    L40:
        this.searchItemState = 0;
        startStopVisibleGifs();
        return;
    L35:
        if (this.searchItemState != 2) goto L40;
        if (isStoriesView() == false) goto L39;
        r6 = 8;
    L39:
        r83.setVisibility(r6);
        goto L40
    L42:
        return;
    L23:
        if (canShowSearchItem() == true) goto L29;
        ActionBarMenuItem r84 = this.searchItem;
        if (isStoriesView() == false) goto L27;
        int r24 = 8;
    L28:
        r84.setVisibility(r24);
        this.searchAlpha = 0.0f;
        goto L30
    L27:
        r24 = 4;
        goto L28
    L14:
        if (canShowSearchItem() == false) goto L19;
        if (isArchivedOnlyStoriesView() == true) goto L19;
        r23 = 0;
        goto L20
    L10:
        r3[0].setTranslationX(r2.getMeasuredWidth() * r8);
        this.mediaPages[1].setTranslationX((r2[0].getMeasuredWidth() * r8) - this.mediaPages[0].getMeasuredWidth());
        goto L11
    L5:
        if (this.mediaPages[1].getVisibility() == 0) goto L7;
    }

    public void onPause() {
        ChatActivityContainer r0 = this.savedMessagesContainer;
        if (r0 == null) goto L6;
        r0.onPause();
        return;
    }

    public void onResume() {
        this.scrolling = true;
        SharedPhotoVideoAdapter r0 = this.photoVideoAdapter;
        if (r0 == null) goto L5;
        r0.notifyDataSetChanged();
    L5:
        SharedDocumentsAdapter r02 = this.documentsAdapter;
        if (r02 == null) goto L8;
        r02.notifyDataSetChanged();
    L8:
        SharedLinksAdapter r03 = this.linksAdapter;
        if (r03 == null) goto L11;
        r03.notifyDataSetChanged();
    L11:
        int r04 = 0;
    L13:
        if (r04 >= this.mediaPages.length) goto L15;
        fixLayoutInternal(r04);
        r04 = r04 + 1;
        goto L13
    L15:
        ChatActivityContainer r2 = this.savedMessagesContainer;
        if (r2 == null) goto L20;
        r2.onResume();
        return;
    }

    public void onSearchStateChanged(boolean r1) {
    }

    public void onSelectedTabChanged() {
        if (isStoriesView() == false) goto L5;
    L8:
        boolean r0 = true;
    L10:
        if (StoriesAdapter.m12996$$Nest$fgetpoller(this.archivedStoriesAdapter) == null) goto L19;
        ViewsForPeerStoriesRequester r3 = StoriesAdapter.m12996$$Nest$fgetpoller(this.archivedStoriesAdapter);
        if (r0 == true) goto L14;
    L16:
        boolean r4 = false;
    L17:
        r3.start(r4);
        goto L19
    L14:
        if (getClosestTab() != 9) goto L16;
        r4 = true;
    L19:
        if (StoriesAdapter.m12996$$Nest$fgetpoller(this.storiesAdapter) == null) goto L27;
        ViewsForPeerStoriesRequester r32 = StoriesAdapter.m12996$$Nest$fgetpoller(this.storiesAdapter);
        if (r0 == true) goto L23;
    L25:
        boolean r42 = false;
    L26:
        r32.start(r42);
        goto L27
    L23:
        if (getClosestTab() != 8) goto L25;
        r42 = true;
    L27:
        Iterator<StoryAlbumData> r33 = this.storyAlbumsById.values().iterator();
    L29:
        if (r33.hasNext() == false) goto L39;
        StoryAlbumData r43 = r33.next();
        StoriesAdapter r5 = r43.adapter;
        if (r5.storiesList == null) goto L29;
        ViewsForPeerStoriesRequester r52 = StoriesAdapter.m12996$$Nest$fgetpoller(r5);
        if (r0 == true) goto L35;
    L37:
        boolean r44 = false;
    L38:
        r52.start(r44);
        goto L29
    L35:
        if (getClosestTab() != r43.tabType) goto L37;
        r44 = true;
        goto L38
    L39:
        ActionBarMenuItem r02 = this.searchItem;
        if (r02 == null) goto L50;
        SearchTagsList r1 = this.searchTagsList;
        if (r1 != null) goto L44;
    L48:
        int r12 = R.string.Search;
    L49:
        r02.setSearchFieldHint(LocaleController.getString(r12));
        goto L50
    L44:
        if (r1.hasFilters() == false) goto L48;
        if (getSelectedTab() != 11) goto L48;
        r12 = R.string.SavedTagSearchHint;
    L50:
        checkStoriesTabsPosition();
        return;
    L5:
        if (isArchivedOnlyStoriesView() == true) goto L8;
        r0 = false;
        goto L10
    }

    public void onTabProgress(float r1) {
        onBottomButtonVisibilityChange();
    }

    public void onTabScroll(boolean r1) {
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r9) {
        int r1 = 0;
        boolean r12 = false;
        if (this.disableScrolling == false) goto L6;
        return false;
    L6:
        if (this.profileActivity.getParentLayout() != null) goto L8;
    L95:
        return false;
    L8:
        if (this.profileActivity.getParentLayout().checkTransitionAnimation() == true) goto L95;
        if (checkTabsAnimationInProgress() == true) goto L95;
        if (this.isInPinchToZoomTouchMode == true) goto L95;
        if (r9 == null) goto L21;
        if (this.velocityTracker != null) goto L17;
        this.velocityTracker = VelocityTracker.obtain();
    L17:
        this.velocityTracker.addMovement(r9);
        HintView r0 = this.fwdRestrictedHint;
        if (r0 == null) goto L21;
        r0.hide();
    L21:
        if (r9 != null) goto L23;
    L31:
        if (r9 != null) goto L33;
    L83:
        if (r9 != null) goto L85;
    L92:
        stopScroll(r9);
    L94:
        return this.startedTracking;
    L85:
        if (r9.getPointerId(0) != this.startedTrackingPointerId) goto L94;
        if (r9.getAction() == 3) goto L92;
        if (r9.getAction() == 1) goto L92;
        if (r9.getAction() != 6) goto L94;
    L33:
        if (r9.getAction() != 2) goto L83;
        if (r9.getPointerId(0) != this.startedTrackingPointerId) goto L83;
        int r2 = (int) (r9.getX() - this.startedTrackingX);
        int r3 = Math.abs(((int) r9.getY()) - this.startedTrackingY);
        if (this.startedTracking == false) goto L55;
        boolean r4 = this.animatingForward;
        if (r4 == false) goto L41;
        if (r2 <= 0) goto L41;
    L43:
        if (r2 >= 0) goto L45;
        boolean r42 = true;
    L47:
        if (prepareForMoving(r9, r42) == true) goto L55;
        this.maybeStartTracking = true;
        this.startedTracking = false;
        onTabScroll(false);
        this.mediaPages[0].setTranslationX(0.0f);
        MediaPage[] r43 = this.mediaPages;
        MediaPage r6 = r43[1];
        if (this.animatingForward == false) goto L52;
        int r44 = r43[0].getMeasuredWidth();
    L53:
        r6.setTranslationX(r44);
        selectTabWithId(this.mediaPages[1].selectedType, 0.0f);
        onTabProgress(getTabProgress());
        goto L55
    L52:
        r44 = -r43[0].getMeasuredWidth();
        goto L53
    L45:
        r42 = false;
    L41:
        if (r4 == true) goto L55;
        if (r2 < 0) goto L43;
    L55:
        if (this.maybeStartTracking == false) goto L66;
        if (this.startedTracking == true) goto L66;
        if (Math.abs(r2) < AndroidUtilities.getPixelsInCM(0.3f, true)) goto L94;
        if (Math.abs(r2) <= r3) goto L94;
        if (r2 >= 0) goto L64;
        r12 = true;
    L64:
        prepareForMoving(r9, r12);
    L66:
        if (this.startedTracking == false) goto L94;
        this.mediaPages[0].setTranslationX(r2);
        boolean r92 = this.animatingForward;
        MediaPage[] r32 = this.mediaPages;
        if (r92 == false) goto L70;
        r32[1].setTranslationX(r32[0].getMeasuredWidth() + r2);
    L71:
        float r93 = Math.abs(r2) / this.mediaPages[0].getMeasuredWidth();
        if (canShowSearchItem() == true) goto L74;
        this.searchAlpha = 0.0f;
    L82:
        updateOptionsSearch();
        selectTabWithId(this.mediaPages[1].selectedType, r93);
        onTabProgress(getTabProgress());
        onSelectedTabChanged();
        goto L94
    L74:
        this.searchAlpha = getSearchAlpha(r93);
        updateSearchItemIcon(r93);
        float r22 = getPhotoVideoOptionsAlpha(r93);
        this.optionsAlpha = r22;
        ImageView r33 = this.photoVideoOptionsItem;
        if (r22 != 0.0f) goto L77;
    L80:
        r1 = 4;
    L81:
        r33.setVisibility(r1);
        goto L82
    L77:
        if (canShowSearchItem() == false) goto L80;
        if (isArchivedOnlyStoriesView() == false) goto L81;
    L70:
        r32[1].setTranslationX(r2 - r32[0].getMeasuredWidth());
        goto L71
    L23:
        if (r9.getAction() != 0) goto L31;
        if (this.startedTracking == true) goto L31;
        if (this.maybeStartTracking == true) goto L31;
        if (r9.getY() < AndroidUtilities.dp(90.0f)) goto L31;
        this.startedTrackingPointerId = r9.getPointerId(0);
        this.maybeStartTracking = true;
        this.startedTrackingX = (int) r9.getX();
        this.startedTrackingY = (int) r9.getY();
        this.velocityTracker.clear();
        goto L94
    }

    public void openAddStoriesToAlbumSheet(BaseFragment r7, long r8, int r10) {
        new SelectStoriesBottomSheet(r7, r8, this.mediaColumnsCount[1], new SharedMediaLayout$$ExternalSyntheticLambda22(this, r8, r10)).show();
    }

    public void openDeleteStoriesAlbumAlert(BaseFragment r9, long r10, int r12) {
        String r0 = getStoriesController().getAlbumName(r10, r12);
        AlertsCreator.showSimpleConfirmAlert(r9, LocaleController.getString(R.string.Delete), AndroidUtilities.replaceTags(LocaleController.formatString(R.string.StoriesAlbumMenuDeleteAlbumAsk, new Object[]{r0})), LocaleController.getString(R.string.Delete), true, new SharedMediaLayout$$ExternalSyntheticLambda76(this, r10, r12));
    }

    @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
    public void openHiddenStories() {
    }

    public void openRenameStoriesAlbumAlert(BaseFragment r5, long r6, int r8) {
        String r0 = getStoriesController().getAlbumName(r6, r8);
        AlertsCreator.createStoriesAlbumEnterNameForRename(r5.getContext(), r5, r0, r5.getResourceProvider(), new SharedMediaLayout$$ExternalSyntheticLambda82(this, r6, r8));
    }

    @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
    public void openStory(DialogCell r5, Runnable r6) {
        BaseFragment r0 = this.profileActivity;
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.getMessagesController().getStoriesController().hasStories(r5.getDialogId()) == false) goto L15;
        this.profileActivity.getOrCreateStoryViewer().doOnAnimationReady(r6);
        StoryViewer r62 = this.profileActivity.getOrCreateStoryViewer();
        Context r02 = this.profileActivity.getContext();
        long r1 = r5.getDialogId();
        StoriesListPlaceProvider r52 = StoriesListPlaceProvider.of((RecyclerListView) r5.getParent());
        BaseFragment r4 = this.profileActivity;
        if ((r4 instanceof ProfileActivity) == true) goto L10;
    L12:
        int r42 = 0;
    L13:
        r62.open(r02, r1, r52.addBottomClip(r42));
        return;
    L10:
        if (((ProfileActivity) r4).myProfile == false) goto L12;
        r42 = AndroidUtilities.dp(68.0f);
        goto L13
    }

    public int overrideColumnsCount() {
        return -1;
    }

    public int processColor(int r1) {
        return r1;
    }

    public void saveAndStopAlbumsReorder() {
        StoryAlbumData r0 = storyAlbums_getByAlbumId(storyAlbums_getAlbumIdByTabType(getClosestTab()));
        if (r0 == null) goto L23;
        MediaPage r1 = getMediaPage(r0.tabType);
        if (r1 == null) goto L24;
        this.storiesContainer.resetReordering();
        InternalListView r5 = MediaPage.m12961$$Nest$fgetlistView(r1);
        int r2 = 0;
    L10:
        if (r2 >= r5.getChildCount()) goto L15;
        View r3 = r5.getChildAt(r2);
        if ((r3 instanceof SharedPhotoVideoCell2) == false) goto L14;
        ((SharedPhotoVideoCell2) r3).setReordering(false, true);
    L14:
        r2 = r2 + 1;
        goto L10
    L15:
        StoriesAdapter r52 = r0.adapter;
        if (r52 == null) goto L22;
        r52.setInAlbumStoriesReorder(false);
        return;
    L22:
        return;
    L24:
        return;
    }

    public void scrollToPage(int r2) {
        if (this.disableScrolling == true) goto L9;
        ScrollSlidingTextTabStripInner r1 = this.scrollSlidingTextTabStrip;
        if (r1 == null) goto L10;
        r1.scrollTo(r2);
        return;
    L10:
        return;
    }

    public void sendTabsOrder() {
        if (this.profileActivity != null) goto L5;
        return;
    L5:
        if (this.scrollSlidingTextTabStrip == null) goto L18;
        ArrayList r0 = new ArrayList();
        ArrayList<Integer> r1 = this.scrollSlidingTextTabStrip.getTabIds();
        int r2 = r1.size();
        int r3 = 0;
    L8:
        if (r3 >= r2) goto L19;
        Integer r4 = r1.get(r3);
        r3 = r3 + 1;
        TLRPC.ProfileTab r42 = getTab(r4.intValue(), this.info instanceof TLRPC.TL_channelFull);
        if (r42 == null) goto L8;
        r0.add(r42);
        goto L8
    L19:
        return;
    }

    public void setChatInfo(TLRPC.ChatFull r10) {
        TLRPC.ChatFull r0 = this.info;
        if (r0 != null) goto L5;
    L7:
        boolean r02 = false;
    L8:
        this.info = r10;
        if (r10 == null) goto L21;
        long r3 = r10.migrated_from_chat_id;
        if (r3 == 0) goto L21;
        if (this.mergeDialogId != 0) goto L21;
        this.mergeDialogId = -r3;
        int r102 = 0;
    L15:
        SharedMediaData[] r32 = this.sharedMediaData;
        if (r102 >= r32.length) goto L21;
        if (r32[r102].messagesDict[1].size() != 0) goto L20;
        SharedMediaData r33 = this.sharedMediaData[r102];
        r33.max_id[1] = this.info.migrated_from_max_id;
        r33.endReached[1] = false;
    L20:
        r102 = r102 + 1;
    L21:
        TLRPC.ChatFull r103 = this.info;
        if (r103 != null) goto L24;
        return;
    L24:
        if (r02 == r103.stories_pinned_available) goto L38;
        ScrollSlidingTextTabStripInner r104 = this.scrollSlidingTextTabStrip;
        if (r104 != null) goto L28;
    L32:
        updateTabs(true);
        switchToCurrentSelectedMode(false);
        return;
    L28:
        if (isArchivedOnlyStoriesView() == false) goto L30;
        int r03 = 9;
    L31:
        r104.setInitialTabId(r03);
        goto L32
    L30:
        r03 = 8;
        goto L31
    L38:
        return;
    L5:
        if (r0.stories_pinned_available == false) goto L7;
        r02 = true;
        goto L8
    }

    public void setChatUsers(ArrayList<Integer> r8, TLRPC.ChatFull r9) {
        int r0 = 0;
        int r1 = 0;
    L3:
        MediaPage[] r2 = this.mediaPages;
        if (r1 >= r2.length) goto L16;
        MediaPage r22 = r2[r1];
        if (r22.selectedType != 7) goto L14;
        if (MediaPage.m12961$$Nest$fgetlistView(r22).getAdapter() == null) goto L14;
        if (MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r1]).getAdapter().getItemCount() == 0) goto L14;
        if (this.profileActivity.getMessagesController().getStoriesController().hasLoadingStories() == false) goto L14;
        return;
    L14:
        r1 = r1 + 1;
        goto L3
    L16:
        if (this.topicId != 0) goto L18;
        ChatUsersAdapter.m12946$$Nest$fputchatInfo(this.chatUsersAdapter, r9);
        ChatUsersAdapter.m12947$$Nest$fputsortedUsers(this.chatUsersAdapter, r8);
    L18:
        updateTabs(true);
    L19:
        MediaPage[] r82 = this.mediaPages;
        if (r0 >= r82.length) goto L38;
        MediaPage r83 = r82[r0];
        if (r83.selectedType != 7) goto L26;
        if (MediaPage.m12961$$Nest$fgetlistView(r83).getAdapter() == null) goto L26;
        AndroidUtilities.notifyDataSetChanged(MediaPage.m12961$$Nest$fgetlistView(this.mediaPages[r0]));
    L26:
        r0 = r0 + 1;
        goto L19
    }

    public void setCommonGroupsCount(int r5) {
        if (this.topicId != 0) goto L5;
        this.hasMedia[6] = r5;
    L5:
        updateTabs(true);
        checkCurrentTabValid();
    }

    public void setForwardRestrictedHint(HintView r1) {
        this.fwdRestrictedHint = r1;
    }

    public void setMergeDialogId(long r1) {
        this.mergeDialogId = r1;
    }

    public void setNewMediaCounts(int[] r5) {
        int r1 = 0;
    L4:
        if (r1 > 6) goto L9;
        if (this.hasMedia[r1] >= 0) goto L9;
        r1 = r1 + 1;
    L9:
        System.arraycopy(r5, 0, this.hasMedia, 0, 6);
        updateTabs(true);
        checkCurrentTabValid();
        if (this.hasMedia[0] < 0) goto L15;
        loadFastScrollData(false);
        return;
    }

    @Override // android.view.View
    public void setPadding(int r3, int r4, int r5, int r6) {
        this.topPadding = r4;
        int r32 = 0;
        int r52 = 0;
    L3:
        MediaPage[] r62 = this.mediaPages;
        if (r52 >= r62.length) goto L7;
        r62[r52].setTranslationY(this.topPadding - this.lastMeasuredTopPadding);
        r52 = r52 + 1;
        goto L3
    L7:
        if (this.topPanelLayout == null) goto L9;
        checkUi_topPanelLayoutY();
    L12:
        this.additionalFloatingTranslation = r4;
        ChatActionCell r42 = this.floatingDateView;
        if (r42.getTag() != null) goto L15;
        r32 = -AndroidUtilities.dp(48.0f);
    L15:
        r42.setTranslationY(r32 + this.additionalFloatingTranslation);
        return;
    L9:
        FragmentContextView r53 = this.fragmentContextView;
        if (r53 == null) goto L12;
        r53.setTranslationY(AndroidUtilities.dp(48.0f) + r4);
        goto L12
    }

    public void setPagesPaddingBottom(int r9) {
        if (this.pagesPaddingBottom == r9) goto L12;
        this.pagesPaddingBottom = r9;
        MediaPage[] r92 = this.mediaPages;
        if (r92 == null) goto L16;
        int r0 = r92.length;
        int r1 = 0;
    L7:
        if (r1 >= r0) goto L17;
        MediaPage r2 = r92[r1];
        if (r2 == null) goto L11;
        InternalListView r3 = MediaPage.m12961$$Nest$fgetlistView(r2);
        int r4 = MediaPage.m12961$$Nest$fgetlistView(r2).getPaddingLeft();
        int r5 = MediaPage.m12961$$Nest$fgetlistView(r2).topPadding;
        int r6 = MediaPage.m12961$$Nest$fgetlistView(r2).getPaddingRight();
        InternalListView r22 = MediaPage.m12961$$Nest$fgetlistView(r2);
        int r7 = getPagePaddingBottom(isStoriesView());
        r22.hintPaddingBottom = r7;
        r3.setPadding(r4, r5, r6, r7);
    L11:
        r1 = r1 + 1;
        goto L7
    L17:
        return;
    L16:
        return;
    }

    public void setPinnedToTop(boolean r3) {
        if (this.isPinnedToTop == r3) goto L8;
        this.isPinnedToTop = r3;
        int r32 = 0;
    L5:
        MediaPage[] r0 = this.mediaPages;
        if (r32 >= r0.length) goto L10;
        updateFastScrollVisibility(r0[r32], true);
        r32 = r32 + 1;
        goto L5
    L10:
        return;
    }

    public void setStoriesFilter(boolean r2, boolean r3) {
        StoriesAdapter r0 = this.storiesAdapter;
        if (r0 == null) goto L7;
        StoriesController.StoriesList r02 = r0.storiesList;
        if (r02 == null) goto L7;
        r02.updateFilters(r2, r3);
    L7:
        StoriesAdapter r1 = this.archivedStoriesAdapter;
        if (r1 == null) goto L13;
        StoriesController.StoriesList r12 = r1.storiesList;
        if (r12 == null) goto L14;
        r12.updateFilters(r2, r3);
        return;
    L14:
        return;
    }

    public void setUserInfo(TLRPC.UserFull r3) {
        TLRPC.UserFull r0 = this.userInfo;
        if (r0 != null) goto L5;
    L7:
        boolean r02 = false;
    L8:
        this.userInfo = r3;
        updateTabs(true);
        if (r3 != null) goto L11;
        return;
    L11:
        if (r02 == r3.stories_pinned_available) goto L15;
        scrollToPage(8);
        return;
    L15:
        return;
    L5:
        if (r0.stories_pinned_available == false) goto L7;
        r02 = true;
        goto L8
    }

    public void setVisibleHeight(int r5) {
        this.lastVisibleHeight = r5;
        int r0 = 0;
    L4:
        if (r0 >= this.mediaPages.length) goto L10;
        int r1 = getMeasuredHeight();
        if (this.mediaPages[r0].selectedType != 8) goto L8;
        float r2 = 280.0f;
    L9:
        float r12 = (-(r1 - Math.max(r5, AndroidUtilities.dp(r2)))) / 2.0f;
        MediaPage.m12957$$Nest$fgetemptyView(this.mediaPages[r0]).setTranslationY(r12);
        MediaPage.m12962$$Nest$fgetprogressView(this.mediaPages[r0]).setTranslationY(-r12);
        r0 = r0 + 1;
        goto L4
    L8:
        r2 = 120.0f;
        goto L9
    L10:
        BotPreviewsEditContainer r02 = this.botPreviewsContainer;
        if (r02 == null) goto L13;
        r02.setVisibleHeight(r5);
    L13:
        ProfileGiftsContainer r4 = this.giftsContainer;
        if (r4 == null) goto L20;
        r4.setVisibleHeight(r5);
        return;
    }

    public void showActionMode(boolean r7) {
        if (this.isActionModeShowed == r7) goto L20;
        this.isActionModeShowed = r7;
        AnimatorSet r0 = this.actionModeAnimation;
        if (r0 == null) goto L9;
        r0.cancel();
    L9:
        if (r7 == false) goto L11;
        this.actionModeLayout.setVisibility(0);
    L11:
        AnimatorSet r1 = new AnimatorSet();
        this.actionModeAnimation = r1;
        LinearLayout r2 = this.actionModeLayout;
        if (r7 == false) goto L14;
        float r3 = 1.0f;
    L15:
        r1.playTogether(new Animator[]{ObjectAnimator.ofFloat(r2, View.ALPHA, new float[]{r3})});
        this.actionModeAnimation.setDuration(180);
        this.actionModeAnimation.addListener(new AnonymousClass44(this, r7));
        this.actionModeAnimation.start();
        if (r7 == false) goto L19;
        updateStoriesPinButton();
        return;
    L19:
        return;
    L14:
        r3 = 0.0f;
        goto L15
    }

    @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
    public void showChatPreview(DialogCell r1) {
    }

    public void showMediaCalendar(int r8, boolean r9) {
        if (r9 == true) goto L5;
    L9:
        if (r9 == true) goto L11;
    L15:
        Bundle r1 = new Bundle();
        r1.putLong("dialog_id", this.dialog_id);
        r1.putLong("topic_id", this.topicId);
        if (r9 == false) goto L34;
        MediaPage r92 = getMediaPage(0);
        if (r92 == null) goto L34;
        ArrayList<Period> r3 = this.sharedMediaData[0].fastScrollPeriods;
        int r93 = MediaPage.m12960$$Nest$fgetlayoutManager(r92).findFirstVisibleItemPosition();
        if (r93 < 0) goto L34;
        Period r4 = null;
        if (r3 == null) goto L32;
        int r5 = 0;
    L25:
        if (r5 >= r3.size()) goto L30;
        if (r93 <= r3.get(r5).startOffset) goto L28;
        r5 = r5 + 1;
        goto L25
    L28:
        r4 = r3.get(r5);
    L30:
        if (r4 != null) goto L32;
        r4 = r3.get(r3.size() - 1);
    L32:
        if (r4 == null) goto L34;
        int r94 = r4.date;
    L36:
        if (r8 != 9) goto L39;
        r1.putInt("type", 3);
    L42:
        CalendarActivity r82 = new CalendarActivity(r1, this.sharedMediaData[0].filterType, r94);
        r82.setCallback(new AnonymousClass33(this));
        this.profileActivity.presentFragment(r82);
        return;
    L39:
        if (r8 != 8) goto L41;
        r1.putInt("type", 2);
        goto L42
    L41:
        r1.putInt("type", 1);
    L34:
        r94 = 0;
        goto L36
    L11:
        if (isAnyStoryPageType(r8) == false) goto L15;
        if (getStoriesCount(r8) > 0) goto L15;
        return;
    L5:
        if (getY() == 0.0f) goto L9;
        if (this.viewType != 1) goto L9;
    }

    public void startAlbumsReorder(int r6) {
        int r0 = storyAlbums_getAlbumIdByTabType(getClosestTab());
        ProfileStoriesCollectionTabs r1 = this.storiesContainer;
        if (r0 == r6) goto L7;
        if (r1 == null) goto L12;
        r1.scrollToAlbumId(r6);
        return;
    L12:
        return;
    L7:
        r1.setReorderingAlbums(true);
        StoryAlbumData r62 = storyAlbums_getByAlbumId(r6);
        if (r62 == null) goto L29;
        MediaPage r12 = getMediaPage(r62.tabType);
        if (r12 == null) goto L28;
        InternalListView r13 = MediaPage.m12961$$Nest$fgetlistView(r12);
        int r2 = 0;
    L15:
        if (r2 >= r13.getChildCount()) goto L20;
        View r3 = r13.getChildAt(r2);
        if ((r3 instanceof SharedPhotoVideoCell2) == false) goto L19;
        ((SharedPhotoVideoCell2) r3).setReordering(true, true);
    L19:
        r2 = r2 + 1;
        goto L15
    L20:
        StoriesAdapter r63 = r62.adapter;
        if (r63 == null) goto L23;
        r63.setInAlbumStoriesReorder(true);
    L23:
        updateOptionsSearch(true);
        return;
    L28:
        return;
    }

    public int storyAlbums_getAlbumIdByTabType(int r1) {
        StoryAlbumData r0 = storyAlbums_getByTabType(r1);
        if (r0 != null) goto L7;
        return -1;
    L7:
        return r0.albumId;
    }

    public void updateAdapters() {
        SharedPhotoVideoAdapter r0 = this.photoVideoAdapter;
        if (r0 == null) goto L5;
        r0.notifyDataSetChanged();
    L5:
        SharedDocumentsAdapter r02 = this.documentsAdapter;
        if (r02 == null) goto L8;
        r02.notifyDataSetChanged();
    L8:
        SharedDocumentsAdapter r03 = this.voiceAdapter;
        if (r03 == null) goto L11;
        r03.notifyDataSetChanged();
    L11:
        SharedLinksAdapter r04 = this.linksAdapter;
        if (r04 == null) goto L14;
        r04.notifyDataSetChanged();
    L14:
        SharedDocumentsAdapter r05 = this.audioAdapter;
        if (r05 == null) goto L17;
        r05.notifyDataSetChanged();
    L17:
        PollAdapter r06 = this.pollAdapter;
        if (r06 == null) goto L20;
        r06.notifyDataSetChanged();
    L20:
        GifAdapter r07 = this.gifAdapter;
        if (r07 == null) goto L23;
        r07.notifyDataSetChanged();
    L23:
        StoriesAdapter r08 = this.storiesAdapter;
        if (r08 == null) goto L26;
        r08.notifyDataSetChanged();
    L26:
        Iterator<StoryAlbumData> r1 = this.storyAlbumsById.values().iterator();
    L28:
        if (r1.hasNext() == false) goto L30;
        r1.next().adapter.notifyDataSetChanged();
        goto L28
    }

    public void updateFastScrollVisibility(MediaPage r12, boolean r13) {
        Integer r1 = 1;
        int r3 = 0;
        if (r12.fastScrollEnabled == true) goto L5;
    L7:
        boolean r11 = false;
    L8:
        RecyclerListView.FastScroll r2 = MediaPage.m12961$$Nest$fgetlistView(r12).getFastScroll();
        ObjectAnimator r4 = r12.fastScrollAnimator;
        if (r4 == null) goto L12;
        r4.removeAllListeners();
        r12.fastScrollAnimator.cancel();
    L12:
        if (r13 == true) goto L23;
        r2.animate().setListener(null).cancel();
        if (r11 == true) goto L17;
        r3 = 8;
    L17:
        r2.setVisibility(r3);
        if (r11 == true) goto L21;
        r1 = null;
    L21:
        r2.setTag(r1);
        r2.setAlpha(1.0f);
        r2.setScaleX(1.0f);
        r2.setScaleY(1.0f);
        return;
    L23:
        Property r8 = View.ALPHA;
        if (r11 == true) goto L26;
    L32:
        if (r11 == false) goto L34;
        return;
    L34:
        if (r2.getTag() == null) goto L38;
        ObjectAnimator r112 = ObjectAnimator.ofFloat(r2, r8, new float[]{r2.getAlpha(), 0.0f});
        r112.addListener(new HideViewAfterAnimation(r2));
        r12.fastScrollAnimator = r112;
        r112.setDuration(150).start();
        r2.animate().setListener(null).cancel();
        r2.setTag(null);
        return;
    L38:
        return;
    L26:
        if (r2.getTag() != null) goto L32;
        r2.animate().setListener(null).cancel();
        if (r2.getVisibility() == 0) goto L30;
        r2.setVisibility(0);
        r2.setAlpha(0.0f);
    L30:
        ObjectAnimator r113 = ObjectAnimator.ofFloat(r2, r8, new float[]{r2.getAlpha(), 1.0f});
        r12.fastScrollAnimator = r113;
        r113.setDuration(150).start();
        r2.setTag(r1);
        return;
    L5:
        if (this.isPinnedToTop == false) goto L7;
        r11 = true;
        goto L8
    }

    public void updateSearchItemIcon(float r6) {
        ActionBarMenuItem r0 = this.searchItemIcon;
        if (r0 != null) goto L5;
        return;
    L5:
        MediaPage[] r1 = this.mediaPages;
        MediaPage r2 = r1[1];
        float r4 = 0.0f;
        if (r2 != null) goto L8;
    L10:
        int r22 = 0;
        MediaPage r12 = r1[0];
        if (r12 != null) goto L13;
    L15:
        r0.setAlpha(r4);
        float r02 = (0.15f * r4) + 0.85f;
        this.searchItemIcon.setScaleX(r02);
        this.searchItemIcon.setScaleY(r02);
        ActionBarMenuItem r5 = this.searchItemIcon;
        if (r4 > 0.01f) goto L18;
        r22 = 8;
    L18:
        r5.setVisibility(r22);
        return;
    L13:
        if (r12.selectedType != 11) goto L15;
        r4 = r4 + (1.0f - r6);
        goto L15
    L8:
        if (r2.selectedType != 11) goto L10;
        r4 = 0.0f + r6;
        goto L10
    }

    public void updateSearchItemIconAnimated() {
        ActionBarMenuItem r0 = this.searchItemIcon;
        if (r0 != null) goto L5;
        return;
    L5:
        boolean r2 = true;
        MediaPage r1 = this.mediaPages[1];
        if (r1 != null) goto L8;
    L10:
        r2 = false;
    L11:
        if (r2 == false) goto L13;
        r0.setVisibility(0);
    L13:
        ViewPropertyAnimator r02 = this.searchItemIcon.animate();
        float r12 = 1.0f;
        if (r2 == false) goto L16;
        float r3 = 1.0f;
    L17:
        ViewPropertyAnimator r03 = r02.alpha(r3);
        if (r2 == false) goto L20;
        float r4 = 1.0f;
    L21:
        ViewPropertyAnimator r04 = r03.scaleX(r4);
        if (r2 == true) goto L25;
        r12 = 0.85f;
    L25:
        r04.scaleY(r12).withEndAction(new SharedMediaLayout$$ExternalSyntheticLambda58(this, r2)).setDuration(420).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
        return;
    L20:
        r4 = 0.85f;
        goto L21
    L16:
        r3 = 0.0f;
        goto L17
    L8:
        if (r1.selectedType != 11) goto L10;
        goto L10
    }

    public void updateStoriesList(StoriesController.StoriesList r2) {
        this.searchStoriesList = r2;
        StoriesAdapter r0 = this.storiesAdapter;
        r0.storiesList = r2;
        r0.notifyDataSetChanged();
        StoriesAdapter r1 = this.animationSupportingStoriesAdapter;
        r1.storiesList = r2;
        r1.notifyDataSetChanged();
    }

    public void updateTabs(boolean r38) {
        if (this.scrollSlidingTextTabStrip != null) goto L6;
        return;
    L6:
        if (this.delegate.isFragmentOpened() == true) goto L8;
        boolean r8 = false;
    L10:
        if (this.savedMessagesContainer == null) goto L16;
        SharedMediaPreloader r9 = this.sharedMediaPreloader;
        if (r9 == null) goto L16;
        if (r9.hasSavedMessages == false) goto L16;
        boolean r92 = true;
    L18:
        if (this.dialog_id <= 0) goto L21;
        BaseFragment r11 = this.profileActivity;
        if (r11 == null) goto L21;
        long r15 = 0;
        TLRPC.User r112 = r11.getMessagesController().getUser(Long.valueOf(this.dialog_id));
    L24:
        if (r112 != null) goto L26;
    L32:
        boolean r13 = false;
    L33:
        if (r112 != null) goto L35;
    L47:
        boolean r113 = false;
    L49:
        if (DialogObject.isUserDialog(this.dialog_id) == true) goto L53;
        if (DialogObject.isChatDialog(this.dialog_id) == true) goto L53;
    L67:
        boolean r4 = false;
    L69:
        if (this.giftsContainer == null) goto L80;
        TLRPC.UserFull r5 = this.userInfo;
        if (r5 != null) goto L73;
    L74:
        TLRPC.ChatFull r52 = this.info;
        if (r52 == null) goto L80;
        if (r52.stargifts_count <= 0) goto L80;
    L78:
        boolean r53 = true;
    L81:
        TLRPC.ChatFull r14 = this.info;
        if (r14 == null) goto L85;
        TLRPC.ProfileTab r142 = r14.main_tab;
    L84:
        TLRPC.ProfileTab r18 = r142;
    L89:
        int r143 = getTabId(r18);
        long r19 = r15;
        int r152 = this.lastMainTabId;
        if (r152 == (-2)) goto L93;
        if (r152 == r143) goto L93;
        int r153 = 1;
    L95:
        if (this.wasReordering == this.scrollSlidingTextTabStrip.isReordering()) goto L97;
        r153 = r153 + 1;
    L97:
        if (r4 == true) goto L101;
        if (r113 == true) goto L101;
        boolean r42 = false;
    L103:
        if (r42 == this.scrollSlidingTextTabStrip.hasTab(8)) goto L106;
        r153 = r153 + 1;
    L106:
        if (r13 == this.scrollSlidingTextTabStrip.hasTab(13)) goto L109;
        r153 = r153 + 1;
    L109:
        if (isSearchingStories() == this.scrollSlidingTextTabStrip.hasTab(8)) goto L112;
        r153 = r153 + 1;
    L112:
        if (r53 == this.scrollSlidingTextTabStrip.hasTab(14)) goto L115;
        r153 = r153 + 1;
    L114:
        int r24 = 13;
        boolean r23 = r113;
    L122:
        if (isStoriesView() == false) goto L124;
        int r34 = 15;
        int r33 = 7;
        int r12 = r143;
        boolean r43 = false;
        boolean r114 = false;
    L214:
        if (r153 <= 0) goto L346;
        if (r8 == false) goto L217;
        TransitionSet r82 = new TransitionSet();
        r82.setOrdering(0);
        r82.addTransition(new AnonymousClass47(this));
        int r10 = 0;
        Integer r35 = 0;
        r82.setDuration(200);
        TransitionManager.beginDelayedTransition(this.scrollSlidingTextTabStrip.getTabsContainer(), r82);
        this.scrollSlidingTextTabStrip.recordIndicatorParams();
    L218:
        SparseArray<View> r6 = this.scrollSlidingTextTabStrip.removeTabs();
        if (r153 <= 3) goto L221;
        r6 = null;
    L221:
        ArrayList r7 = new ArrayList();
        if (isSearchingStories() == false) goto L224;
        int r36 = r10;
        r7.add(new Pair(8, LocaleController.getString(R.string.ProfileStories)));
        this.scrollSlidingTextTabStrip.animationDuration = 420;
    L225:
        if (r23 == false) goto L227;
        r7.add(new Pair(8, LocaleController.getString(R.string.ProfileBotPreviewTab)));
        int r102 = 3;
    L251:
        if (r53 == false) goto L253;
        String r3 = LocaleController.getString(R.string.ProfileGifts);
        CharSequence r54 = this.giftsContainer.getLastEmojis(null);
        CharSequence[] r83 = new CharSequence[2];
        r83[r36] = r3;
        r83[1] = r54;
        r7.add(new Pair(14, TextUtils.concat(r83)));
        this.giftsLastHash = this.giftsContainer.getLastEmojisHash();
    L253:
        if (r13 == false) goto L256;
        r7.add(new Pair(Integer.valueOf(r24), LocaleController.getString(R.string.ProfileBotPreviewTab)));
    L256:
        if (isStoriesView() == true) goto L315;
        if (r114 == false) goto L260;
        r7.add(new Pair(11, LocaleController.getString(R.string.SavedDialogsTab)));
    L260:
        if (ChatUsersAdapter.m12944$$Nest$fgetchatInfo(this.chatUsersAdapter) == null) goto L262;
        r7.add(new Pair(Integer.valueOf(r33), LocaleController.getString(R.string.GroupMembers)));
    L262:
        int[] r1 = this.hasMedia;
        if (r1[r36] > 0) goto L265;
    L280:
        if (r92 == false) goto L283;
        r7.add(new Pair(12, LocaleController.getString(R.string.SavedMessagesTab2)));
        MessagesController.getGlobalMainSettings().edit().putInt("savedhint", r102).apply();
    L283:
        if (this.hasMedia[1] <= 0) goto L285;
        r7.add(new Pair(1, LocaleController.getString(R.string.SharedFilesTab2)));
    L285:
        boolean r16 = DialogObject.isEncryptedDialog(this.dialog_id);
        int[] r2 = this.hasMedia;
        if (r16 == true) goto L297;
        if (r2[3] <= 0) goto L291;
        r7.add(new Pair(3, LocaleController.getString(R.string.SharedLinksTab2)));
    L291:
        if (this.hasMedia[4] <= 0) goto L294;
        r7.add(new Pair(4, LocaleController.getString(R.string.SharedMusicTab2)));
    L294:
        if (this.hasMedia[8] <= 0) goto L300;
        r7.add(new Pair(Integer.valueOf(r34), LocaleController.getString(R.string.SharedPollTab)));
    L300:
        if (this.hasMedia[2] <= 0) goto L303;
        r7.add(new Pair(2, LocaleController.getString(R.string.SharedVoiceTab2)));
    L303:
        if (this.hasMedia[5] <= 0) goto L306;
        r7.add(new Pair(5, LocaleController.getString(R.string.SharedGIFsTab2)));
    L306:
        if (this.hasMedia[6] <= 0) goto L308;
        r7.add(new Pair(6, LocaleController.getString(R.string.SharedGroupsTab2)));
    L308:
        if (r43 == false) goto L315;
        if (this.dialog_id <= r19) goto L312;
        int r32 = R.string.SimilarBotsTab;
    L313:
        r7.add(new Pair(10, LocaleController.getString(r32)));
        goto L315
    L312:
        r32 = R.string.SimilarChannelsTab;
        goto L313
    L297:
        if (r2[4] <= 0) goto L300;
        r7.add(new Pair(4, LocaleController.getString(R.string.SharedMusicTab2)));
        goto L300
    L265:
        if (r1[1] == 0) goto L267;
    L279:
        r7.add(new Pair(r35, LocaleController.getString(R.string.SharedMediaTab2)));
        goto L280
    L267:
        if (r1[2] != 0) goto L279;
        if (r1[r102] != 0) goto L279;
        if (r1[4] != 0) goto L279;
        if (r1[5] != 0) goto L279;
        if (r1[6] != 0) goto L279;
        if (ChatUsersAdapter.m12944$$Nest$fgetchatInfo(this.chatUsersAdapter) != null) goto L279;
        r7.add(new Pair(r35, LocaleController.getString(R.string.SharedMediaTabFull2)));
    L315:
        if (this.scrollSlidingTextTabStrip.isReordering() == false) goto L324;
        SharedMediaLayout$$ExternalSyntheticLambda21 r17 = new SharedMediaLayout$$ExternalSyntheticLambda21(r7);
        boolean r22 = this.info instanceof TLRPC.TL_channelFull;
        int r37 = r34;
        int r103 = r36;
    L317:
        if (r103 >= r37) goto L324;
        if (getTab(r103, r22) == null) goto L323;
        if (((Boolean) r17.run(Integer.valueOf(r103))).booleanValue() == true) goto L323;
        r7.add(new Pair(Integer.valueOf(r103), getTabName(r103)));
    L323:
        r103 = r103 + 1;
    L324:
        if (r18 == null) goto L335;
        int r110 = getTabId(r18);
        int r104 = r36;
    L327:
        if (r104 >= r7.size()) goto L332;
        if (((Integer) ((Pair) r7.get(r104)).first).intValue() == r110) goto L333;
        r104 = r104 + 1;
    L333:
        if (r104 < 0) goto L335;
        Pair r111 = (Pair) r7.remove(r104);
        int r105 = r36;
        r7.add(r105, r111);
    L337:
        if (r7.isEmpty() == true) goto L339;
        this.firstTab = ((Integer) ((Pair) r7.get(r105)).first).intValue();
    L339:
        int r115 = r7.size();
        int r25 = 0;
    L340:
        if (r25 >= r115) goto L344;
        Object r39 = r7.get(r25);
        r25 = r25 + 1;
        Pair r310 = (Pair) r39;
        if (this.scrollSlidingTextTabStrip.hasTab(((Integer) r310.first).intValue()) == true) goto L340;
        this.scrollSlidingTextTabStrip.addTextTab(((Integer) r310.first).intValue(), (CharSequence) r310.second, r6);
        goto L340
    L344:
        this.lastMainTabId = r12;
        this.wasReordering = this.scrollSlidingTextTabStrip.isReordering();
        this.scrollSlidingTextTabStrip.finishAddingTabs();
        onSelectedTabChanged();
        checkStoriesTabsPosition();
    L348:
        int r116 = getSelectedTab();
        if (r116 < 0) goto L363;
        this.mediaPages[0].selectedType = r116;
        return;
    L363:
        return;
    L332:
        r104 = -1;
    L335:
        r105 = r36;
        goto L337
    L227:
        r102 = 3;
        if (DialogObject.isUserDialog(this.dialog_id) == true) goto L232;
        if (DialogObject.isChatDialog(this.dialog_id) == false) goto L251;
    L232:
        if (DialogObject.isEncryptedDialog(this.dialog_id) == true) goto L251;
        TLRPC.UserFull r26 = this.userInfo;
        if (r26 != null) goto L236;
    L237:
        TLRPC.ChatFull r27 = this.info;
        if (r27 == null) goto L242;
        if (r27.stories_pinned_available == false) goto L242;
    L244:
        if (includeStories() == false) goto L251;
        if (isArchivedOnlyStoriesView() == false) goto L248;
        r7.add(new Pair(9, LocaleController.getString(R.string.ProfileArchivedStories)));
        this.scrollSlidingTextTabStrip.animationDuration = 420;
        goto L251
    L248:
        r7.add(new Pair(8, LocaleController.getString(R.string.ProfileStories)));
        if (isStoriesView() == false) goto L251;
        r7.add(new Pair(9, LocaleController.getString(R.string.ProfileArchivedStories)));
    L242:
        if (isStoriesView() == false) goto L251;
    L236:
        if (r26.stories_pinned_available == true) goto L244;
    L224:
        r36 = r10;
        goto L225
    L217:
        r10 = 0;
        r35 = 0;
        goto L218
    L346:
        if (this.lastMainTabId != (-2)) goto L348;
        this.lastMainTabId = r12;
        goto L348
    L124:
        if (ChatUsersAdapter.m12944$$Nest$fgetchatInfo(this.chatUsersAdapter) != null) goto L127;
        boolean r44 = true;
    L129:
        if (r44 != this.scrollSlidingTextTabStrip.hasTab(7)) goto L132;
        r153 = r153 + 1;
    L132:
        if (this.hasMedia[0] > 0) goto L134;
        boolean r45 = true;
    L136:
        if (r45 != this.scrollSlidingTextTabStrip.hasTab(0)) goto L139;
        r153 = r153 + 1;
    L139:
        if (this.hasMedia[1] > 0) goto L141;
        boolean r46 = true;
    L142:
        r33 = 7;
        if (r46 != this.scrollSlidingTextTabStrip.hasTab(1)) goto L145;
        r153 = r153 + 1;
    L145:
        boolean r47 = DialogObject.isEncryptedDialog(this.dialog_id);
        int[] r117 = this.hasMedia;
        if (r47 == false) goto L148;
        r12 = r143;
        if (r117[4] > 0) goto L171;
        boolean r48 = true;
    L173:
        if (r48 != this.scrollSlidingTextTabStrip.hasTab(4)) goto L176;
    L167:
        r153 = r153 + 1;
    L176:
        if (this.hasMedia[2] > 0) goto L178;
        boolean r49 = true;
    L180:
        if (r49 != this.scrollSlidingTextTabStrip.hasTab(2)) goto L183;
        r153 = r153 + 1;
    L183:
        if (this.hasMedia[5] > 0) goto L185;
        boolean r410 = true;
    L187:
        if (r410 != this.scrollSlidingTextTabStrip.hasTab(5)) goto L190;
        r153 = r153 + 1;
    L190:
        if (this.hasMedia[6] > 0) goto L192;
        boolean r411 = true;
    L194:
        if (r411 != this.scrollSlidingTextTabStrip.hasTab(6)) goto L196;
        r153 = r153 + 1;
    L196:
        r43 = !ChannelRecommendationsAdapter.m12942$$Nest$fgetchats(this.channelRecommendationsAdapter).isEmpty();
        if (r43 == this.scrollSlidingTextTabStrip.hasTab(10)) goto L200;
        r153 = r153 + 1;
    L200:
        if (includeSavedDialogs() == true) goto L202;
    L206:
        r114 = false;
    L207:
        r34 = 15;
        if (r114 == this.scrollSlidingTextTabStrip.hasTab(11)) goto L211;
        r153 = r153 + 1;
    L211:
        if (r92 == this.scrollSlidingTextTabStrip.hasTab(12)) goto L214;
        r153 = r153 + 1;
        goto L214
    L202:
        if (this.profileActivity.getMessagesController().getSavedMessagesController().unsupported == true) goto L206;
        if (this.profileActivity.getMessagesController().getSavedMessagesController().hasDialogs() == false) goto L206;
        r114 = true;
        goto L207
    L192:
        r411 = false;
        goto L194
    L185:
        r410 = false;
        goto L187
    L178:
        r49 = false;
        goto L180
    L171:
        r48 = false;
        goto L173
    L148:
        if (r117[3] > 0) goto L150;
        boolean r412 = true;
    L152:
        if (r412 != this.scrollSlidingTextTabStrip.hasTab(3)) goto L155;
        r153 = r153 + 1;
    L155:
        if (this.hasMedia[4] > 0) goto L157;
        boolean r413 = true;
    L158:
        r12 = r143;
        if (r413 != this.scrollSlidingTextTabStrip.hasTab(4)) goto L162;
        r153 = r153 + 1;
    L162:
        if (this.hasMedia[8] > 0) goto L164;
        boolean r414 = true;
    L166:
        if (r414 != this.scrollSlidingTextTabStrip.hasTab(15)) goto L176;
    L164:
        r414 = false;
        goto L166
    L157:
        r413 = false;
        goto L158
    L150:
        r412 = false;
        goto L152
    L141:
        r46 = false;
        goto L142
    L134:
        r45 = false;
        goto L136
    L127:
        r44 = false;
        goto L129
    L115:
        ProfileGiftsContainer r415 = this.giftsContainer;
        if (r415 == null) goto L114;
        if (r53 == false) goto L114;
        r24 = 13;
        r23 = r113;
        if (this.giftsLastHash == r415.getLastEmojisHash()) goto L122;
        r153 = r153 + 1;
    L101:
        r42 = true;
    L93:
        r153 = 0;
        goto L95
    L85:
        TLRPC.UserFull r144 = this.userInfo;
        if (r144 == null) goto L88;
        r142 = r144.main_tab;
        goto L84
    L88:
        r18 = null;
        goto L89
    L73:
        if (r5.stargifts_count > 0) goto L78;
    L80:
        r53 = false;
    L53:
        if (DialogObject.isEncryptedDialog(this.dialog_id) == true) goto L67;
        TLRPC.UserFull r416 = this.userInfo;
        if (r416 != null) goto L57;
    L58:
        TLRPC.ChatFull r417 = this.info;
        if (r417 == null) goto L63;
        if (r417.stories_pinned_available == false) goto L63;
    L65:
        if (includeStories() == false) goto L67;
        r4 = true;
    L63:
        if (isStoriesView() == false) goto L67;
    L57:
        if (r416.stories_pinned_available == true) goto L65;
    L35:
        if (r112.bot == false) goto L47;
        if (r112.bot_can_edit == true) goto L47;
        TLRPC.UserFull r118 = this.userInfo;
        if (r118 == null) goto L47;
        TL_bots.BotInfo r119 = r118.bot_info;
        if (r119 == null) goto L47;
        if (r119.has_preview_medias == false) goto L47;
        if (r13 == true) goto L47;
        r113 = true;
        goto L49
    L26:
        if (r112.bot == false) goto L32;
        if (r112.bot_has_main_app == false) goto L32;
        if (r112.bot_can_edit == false) goto L32;
        r13 = true;
    L21:
        r15 = 0;
        r112 = null;
    L16:
        r92 = false;
        goto L18
    L8:
        r8 = r38;
        goto L10
    }

    public Boolean zoomIn() {
        return zoomIn(null, null);
    }

    public Boolean zoomIn(View r6, View r7) {
        if (this.photoVideoChangeColumnsAnimation == true) goto L31;
        boolean r1 = false;
        MediaPage r0 = this.mediaPages[0];
        if (r0 == null) goto L33;
        int r02 = r0.selectedType;
        this.changeColumnsTab = r02;
        boolean r03 = isAnyStoryPageType(r02);
        int r2 = getNextMediaColumnsCount(r03 ? 1 : 0, this.mediaColumnsCount[r03 ? 1 : 0], true);
        if (r6 == null) goto L13;
        if (r2 != getNextMediaColumnsCount(r03 ? 1 : 0, r2, true)) goto L13;
        r6.setEnabled(false);
        r6.animate().alpha(0.5f).start();
    L13:
        if (this.mediaColumnsCount[r03 ? 1 : 0] == r2) goto L27;
        if (r7 != null) goto L16;
    L18:
        if (r03 == true) goto L21;
        SharedConfig.setMediaColumnsCount(r2);
    L25:
        animateToMediaColumnsCount(r2);
        goto L27
    L21:
        if (getStoriesCount(this.mediaPages[0].selectedType) < 5) goto L23;
    L24:
        SharedConfig.setStoriesColumnsCount(r2);
        goto L25
    L23:
        if (isStoryAlbumPageType(this.mediaPages[0].selectedType) == false) goto L25;
    L16:
        if (r7.isEnabled() == true) goto L18;
        r7.setEnabled(true);
        r7.animate().alpha(1.0f).start();
    L27:
        if (r2 == getNextMediaColumnsCount(r03 ? 1 : 0, r2, true)) goto L30;
        r1 = true;
    L30:
        return Boolean.valueOf(r1);
    L33:
        return null;
    L31:
        return null;
    }

    public Boolean zoomOut() {
        return zoomOut(null, null);
    }

    public Boolean zoomOut(View r5, View r6) {
        if (this.photoVideoChangeColumnsAnimation == true) goto L35;
        boolean r1 = false;
        MediaPage r0 = this.mediaPages[0];
        if (r0 != null) goto L7;
        return null;
    L7:
        if (this.allowStoriesSingleColumn == true) goto L9;
    L11:
        int r02 = this.mediaPages[0].selectedType;
        this.changeColumnsTab = r02;
        boolean r03 = isAnyStoryPageType(r02);
        int r2 = getNextMediaColumnsCount(r03 ? 1 : 0, this.mediaColumnsCount[r03 ? 1 : 0], false);
        if (r6 == null) goto L17;
        if (r2 != getNextMediaColumnsCount(r03 ? 1 : 0, r2, false)) goto L17;
        r6.setEnabled(false);
        r6.animate().alpha(0.5f).start();
    L17:
        if (this.mediaColumnsCount[r03 ? 1 : 0] == r2) goto L31;
        if (r5 != null) goto L20;
    L22:
        if (r03 == true) goto L25;
        SharedConfig.setMediaColumnsCount(r2);
    L29:
        animateToMediaColumnsCount(r2);
        goto L31
    L25:
        if (getStoriesCount(this.mediaPages[0].selectedType) < 5) goto L27;
    L28:
        SharedConfig.setStoriesColumnsCount(r2);
        goto L29
    L27:
        if (isStoryAlbumPageType(this.mediaPages[0].selectedType) == false) goto L29;
    L20:
        if (r5.isEnabled() == true) goto L22;
        r5.setEnabled(true);
        r5.animate().alpha(1.0f).start();
    L31:
        if (r2 == getNextMediaColumnsCount(r03 ? 1 : 0, r2, false)) goto L34;
        r1 = true;
    L34:
        return Boolean.valueOf(r1);
    L9:
        if (isAnyStoryPageType(r0.selectedType) == false) goto L11;
        return null;
    L35:
        return null;
    }
}
