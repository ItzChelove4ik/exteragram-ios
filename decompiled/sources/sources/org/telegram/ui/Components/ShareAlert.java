package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Property;
import android.util.SparseIntArray;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.camera.core.ImageCapture$$ExternalSyntheticBackport0;
import androidx.collection.LongSparseArray;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.backup.PreferencesUtils;
import com.exteragram.messenger.components.TranslateBeforeSendWrapper;
import com.exteragram.messenger.translator.TranslatorUtils;
import com.exteragram.messenger.utils.text.LocaleUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.AdjustPanLayoutHelper;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Adapters.DialogsSearchAdapter;
import org.telegram.ui.Adapters.SearchAdapterHelper;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.HintDialogCell;
import org.telegram.ui.Cells.ProfileSearchCell;
import org.telegram.ui.Cells.ShareDialogCell;
import org.telegram.ui.Cells.ShareTopicCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.ChatActivityEnterView;
import org.telegram.ui.Components.Forum.ForumUtilities;
import org.telegram.ui.Components.MessagePreviewView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.BlurredBackgroundWithFadeDrawable;
import org.telegram.ui.Components.blur3.DownscaleScrollableNoiseSuppressor;
import org.telegram.ui.Components.blur3.RenderNodeWithHash;
import org.telegram.ui.Components.blur3.ViewGroupPartRenderer;
import org.telegram.ui.Components.blur3.capture.IBlur3Capture;
import org.telegram.ui.Components.blur3.capture.IBlur3Hash;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceRenderNode;
import org.telegram.ui.Components.chat.ViewPositionWatcher;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.MessageStatisticActivity;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.PremiumPreviewFragment;
import org.telegram.ui.Stories.DarkThemeResourceProvider;

/* loaded from: classes7.dex */
public class ShareAlert extends BottomSheet implements NotificationCenter.NotificationCenterDelegate {
    private AnimatorSet animatorSet;
    private View bottomFadeView;
    private FrameLayout bulletinContainer;
    public FrameLayout bulletinContainer2;
    private BlurredBackgroundDrawable captionContainerBg;
    private float captionEditTextTopOffset;
    private float chatActivityEnterViewAnimateFromTop;
    private EditTextEmoji commentTextView;
    private int containerViewTop;
    private boolean copyLinkOnEnd;
    private float currentPanTranslationY;
    private boolean darkTheme;
    private ShareAlertDelegate delegate;
    private BlurredBackgroundDrawable emojiViewChildBg;
    private TLRPC.TL_exportedMessageLink exportedMessageLink;
    private BlurredBackgroundWithFadeDrawable fadeDrawable;
    public boolean forceDarkThemeForHint;
    private FrameLayout frameLayout;
    private FrameLayout frameLayout2;
    private boolean fullyShown;
    private RecyclerListView gridView;
    private int hasPoll;
    private boolean hideCaption;
    private boolean hideSendersName;
    private IBlur3Capture iBlur3Capture;
    private final BlurredBackgroundDrawableViewFactory iBlur3FactoryFade;
    private final BlurredBackgroundDrawableViewFactory iBlur3FactoryFrostedLiquidGlass;
    private final BlurredBackgroundDrawableViewFactory iBlur3FactoryLiquidGlass;
    private final RectF iBlur3PositionMainTabs;
    private final ArrayList<RectF> iBlur3Positions;
    private final BlurredBackgroundSourceColor iBlur3SourceColor;
    private final BlurredBackgroundSourceRenderNode iBlur3SourceGlass;
    private final BlurredBackgroundSourceRenderNode iBlur3SourceGlassFrosted;
    private boolean includeStory;
    public boolean includeStoryFromMessage;
    private boolean isChannel;
    private int keyboardSize2;
    private float keyboardT;
    int lastOffset;
    private GridLayoutManager layoutManager;
    private LinearLayout linkContainer;
    private TextView linkCopyButton;
    private SimpleTextView linkTextView;
    private String[] linkToCopy;
    private ShareDialogsAdapter listAdapter;
    private boolean loadingLink;
    private Paint paint;
    private boolean panTranslationMoveLayout;
    private Activity parentActivity;
    private ChatActivity parentFragment;
    private FrameLayout pickerBottom;
    private FrameLayout pickerBottomLayout;
    private int previousScrollOffsetY;
    private ArrayList<DialogsSearchAdapter.RecentSearchObject> recentSearchObjects;
    private LongSparseArray<DialogsSearchAdapter.RecentSearchObject> recentSearchObjectsById;
    private RectF rect;
    RecyclerItemsEnterAnimator recyclerItemsEnterAnimator;
    private int scrollOffsetY;
    private final DownscaleScrollableNoiseSuppressor scrollableViewNoiseSuppressor;
    private ShareSearchAdapter searchAdapter;
    private StickerEmptyView searchEmptyView;
    private RecyclerListView searchGridView;
    private boolean searchIsVisible;
    private FillLastGridLayoutManager searchLayoutManager;
    FragmentSearchField searchView;
    private boolean searchWasVisibleBeforeTopics;
    protected Map<TLRPC.Dialog, TLRPC.TL_forumTopic> selectedDialogTopics;
    protected LongSparseArray<TLRPC.Dialog> selectedDialogs;
    private TLRPC.Dialog selectedTopicDialog;
    private ActionBarPopupWindow sendPopupWindow;
    private String sendingFile;
    protected ArrayList<MessageObject> sendingMessageObjects;
    private String[] sendingText;
    private View[] shadow;
    private AnimatorSet[] shadowAnimation;
    private Drawable shadowDrawable;
    private ShareTopicsAdapter shareTopicsAdapter;
    private LinearLayout sharesCountLayout;
    private int shiftDp;
    private SizeNotifierFrameLayout sizeNotifierFrameLayout;
    TL_stories.StoryItem storyItem;
    private SwitchView switchView;
    private Insets systemInsets;
    private TextPaint textPaint;
    public int timestamp;
    public CheckBox2 timestampCheckbox;
    public FrameLayout timestampFrameLayout;
    public LinearLayout timestampLayout;
    public TextView timestampTextView;
    private ValueAnimator topBackgroundAnimator;
    private int topBeforeSwitch;
    private SpringAnimation topicsAnimation;
    ActionBar topicsBackActionBar;
    private RecyclerListView topicsGridView;
    private GridLayoutManager topicsLayoutManager;
    private boolean updateSearchAdapter;
    private ChatActivityEnterView.SendButton writeButton;
    private FrameLayout writeButtonContainer;

    /* renamed from: org.telegram.ui.Components.ShareAlert$1, reason: invalid class name */
    public class AnonymousClass1 implements RenderNodeWithHash.Renderer {
        final /* synthetic */ ShareAlert this$0;

        public AnonymousClass1(ShareAlert r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.blur3.RenderNodeWithHash.Renderer
        public void renderNodeCalculateHash(IBlur3Hash r3) {
            r3.add(ShareAlert.access$300(this.this$0, Theme.key_windowBackgroundWhite));
            r3.add(SharedConfig.chatBlurEnabled());
        }

        @Override // org.telegram.ui.Components.blur3.RenderNodeWithHash.Renderer
        public void renderNodeUpdateDisplayList(Canvas r3) {
            r3.drawColor(ShareAlert.access$400(this.this$0, Theme.key_windowBackgroundWhite));
            if (SharedConfig.chatBlurEnabled() == false) goto L6;
            ShareAlert.m12681$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).draw(r3, -2);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$10, reason: invalid class name */
    public class AnonymousClass10 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ShareAlert this$0;

        public AnonymousClass10(ShareAlert r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            if (r3 == 0) goto L5;
            ShareAlert.m12725$$Nest$mupdateLayout(this.this$0);
            ShareAlert r0 = this.this$0;
            ShareAlert.m12710$$Nest$fputpreviousScrollOffsetY(r0, ShareAlert.m12680$$Nest$fgetscrollOffsetY(r0));
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$11, reason: invalid class name */
    public class AnonymousClass11 extends RecyclerView.ItemDecoration {
        final /* synthetic */ ShareAlert this$0;

        public AnonymousClass11(ShareAlert r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r1, View r2, RecyclerView r3, RecyclerView.State r4) {
            RecyclerListView.Holder r0 = (RecyclerListView.Holder) r3.getChildViewHolder(r2);
            if (r0 == null) goto L14;
            int r02 = r0.getAdapterPosition() % 4;
            int r32 = 0;
            if (r02 != 0) goto L7;
            int r42 = 0;
        L8:
            r1.left = r42;
            if (r02 == 3) goto L12;
            r32 = AndroidUtilities.dp(4.0f);
        L12:
            r1.right = r32;
            return;
        L7:
            r42 = AndroidUtilities.dp(4.0f);
            goto L8
        L14:
            r1.left = AndroidUtilities.dp(4.0f);
            r1.right = AndroidUtilities.dp(4.0f);
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$12, reason: invalid class name */
    public class AnonymousClass12 extends RecyclerListView {
        final /* synthetic */ ShareAlert this$0;

        public AnonymousClass12(ShareAlert r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public boolean allowSelectChildAtPosition(float r2, float r3) {
            if (ShareAlert.m12661$$Nest$fgetdarkTheme(this.this$0) == true) goto L5;
        L7:
            float r1 = 58.0f;
        L9:
            if (r3 < AndroidUtilities.dp(r1)) goto L11;
            return true;
        L11:
            return false;
        L5:
            if (ShareAlert.m12673$$Nest$fgetlinkToCopy(this.this$0)[1] == null) goto L7;
            r1 = 111.0f;
            goto L9
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
        public void draw(Canvas r6) {
            if (ShareAlert.m12697$$Nest$fgettopicsGridView(this.this$0).getVisibility() == 8) goto L11;
            r6.save();
            int r0 = ShareAlert.m12680$$Nest$fgetscrollOffsetY(this.this$0);
            if (ShareAlert.m12661$$Nest$fgetdarkTheme(this.this$0) == true) goto L7;
        L9:
            float r2 = 58.0f;
        L10:
            r6.clipRect(0, r0 + AndroidUtilities.dp(r2), getWidth(), getHeight());
            goto L11
        L7:
            if (ShareAlert.m12673$$Nest$fgetlinkToCopy(this.this$0)[1] == null) goto L9;
            r2 = 111.0f;
        L11:
            super.draw(r6);
            if (ShareAlert.m12697$$Nest$fgettopicsGridView(this.this$0).getVisibility() == 8) goto L15;
            r6.restore();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$13, reason: invalid class name */
    public class AnonymousClass13 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ ShareAlert this$0;

        public AnonymousClass13(ShareAlert r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r1) {
            if (r1 == 0) goto L4;
            return 1;
        L4:
            return ShareAlert.m12672$$Nest$fgetlayoutManager(this.this$0).getSpanCount();
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$14, reason: invalid class name */
    public class AnonymousClass14 extends RecyclerView.ItemDecoration {
        final /* synthetic */ ShareAlert this$0;

        public AnonymousClass14(ShareAlert r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r1, View r2, RecyclerView r3, RecyclerView.State r4) {
            RecyclerListView.Holder r0 = (RecyclerListView.Holder) r3.getChildViewHolder(r2);
            if (r0 == null) goto L14;
            int r02 = r0.getAdapterPosition() % 4;
            int r32 = 0;
            if (r02 != 0) goto L7;
            int r42 = 0;
        L8:
            r1.left = r42;
            if (r02 == 3) goto L12;
            r32 = AndroidUtilities.dp(4.0f);
        L12:
            r1.right = r32;
            return;
        L7:
            r42 = AndroidUtilities.dp(4.0f);
            goto L8
        L14:
            r1.left = AndroidUtilities.dp(4.0f);
            r1.right = AndroidUtilities.dp(4.0f);
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$15, reason: invalid class name */
    public class AnonymousClass15 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ShareAlert this$0;

        public AnonymousClass15(ShareAlert r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r2, int r3, int r4) {
            if (r4 == 0) goto L5;
            ShareAlert.m12725$$Nest$mupdateLayout(this.this$0);
            ShareAlert r22 = this.this$0;
            ShareAlert.m12710$$Nest$fputpreviousScrollOffsetY(r22, ShareAlert.m12680$$Nest$fgetscrollOffsetY(r22));
        L5:
            if (Bulletin.getVisibleBulletin() == null) goto L14;
            if (Bulletin.getVisibleBulletin().getLayout() == null) goto L14;
            if ((Bulletin.getVisibleBulletin().getLayout().getParent() instanceof View) == false) goto L14;
            if (((View) Bulletin.getVisibleBulletin().getLayout().getParent()).getParent() != this.this$0.bulletinContainer2) goto L14;
            Bulletin.hideVisible();
        L14:
            if (Build.VERSION.SDK_INT >= 31) goto L16;
            return;
        L16:
            if (ShareAlert.m12681$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0) == null) goto L20;
            ShareAlert.m12681$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).onScrolled(r3, r4);
            ShareAlert.m12718$$Nest$mblur3_InvalidateBlur(this.this$0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$16, reason: invalid class name */
    public class AnonymousClass16 extends RecyclerListView {
        final /* synthetic */ ShareAlert this$0;

        public AnonymousClass16(ShareAlert r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public boolean allowSelectChildAtPosition(float r2, float r3) {
            if (ShareAlert.m12661$$Nest$fgetdarkTheme(this.this$0) == true) goto L5;
        L7:
            float r1 = 58.0f;
        L9:
            if (r3 < AndroidUtilities.dp(r1)) goto L11;
            return true;
        L11:
            return false;
        L5:
            if (ShareAlert.m12673$$Nest$fgetlinkToCopy(this.this$0)[1] == null) goto L7;
            r1 = 111.0f;
            goto L9
        }

        @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
        public void draw(Canvas r6) {
            if (ShareAlert.m12697$$Nest$fgettopicsGridView(this.this$0).getVisibility() == 8) goto L11;
            r6.save();
            int r0 = ShareAlert.m12680$$Nest$fgetscrollOffsetY(this.this$0);
            if (ShareAlert.m12661$$Nest$fgetdarkTheme(this.this$0) == true) goto L7;
        L9:
            float r2 = 58.0f;
        L10:
            r6.clipRect(0, r0 + AndroidUtilities.dp(r2), getWidth(), getHeight());
            goto L11
        L7:
            if (ShareAlert.m12673$$Nest$fgetlinkToCopy(this.this$0)[1] == null) goto L9;
            r2 = 111.0f;
        L11:
            super.draw(r6);
            if (ShareAlert.m12697$$Nest$fgettopicsGridView(this.this$0).getVisibility() == 8) goto L15;
            r6.restore();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$17, reason: invalid class name */
    public class AnonymousClass17 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ ShareAlert this$0;

        public AnonymousClass17(ShareAlert r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r2) {
            return ShareAlert.m12682$$Nest$fgetsearchAdapter(this.this$0).getSpanSize(4, r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$18, reason: invalid class name */
    public class AnonymousClass18 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ShareAlert this$0;

        public AnonymousClass18(ShareAlert r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            if (r3 == 0) goto L5;
            ShareAlert.m12725$$Nest$mupdateLayout(this.this$0);
            ShareAlert r0 = this.this$0;
            ShareAlert.m12710$$Nest$fputpreviousScrollOffsetY(r0, ShareAlert.m12680$$Nest$fgetscrollOffsetY(r0));
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$19, reason: invalid class name */
    public class AnonymousClass19 extends RecyclerView.ItemDecoration {
        final /* synthetic */ ShareAlert this$0;

        public AnonymousClass19(ShareAlert r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r2, View r3, RecyclerView r4, RecyclerView.State r5) {
            RecyclerListView.Holder r1 = (RecyclerListView.Holder) r4.getChildViewHolder(r3);
            if (r1 == null) goto L18;
            int r0 = 0;
            if (r1.getItemViewType() == 5) goto L8;
            r2.right = 0;
            r2.left = 0;
            return;
        L8:
            int r12 = r1.getAdapterPosition() % 4;
            if (r12 != 0) goto L11;
            int r42 = 0;
        L12:
            r2.left = r42;
            if (r12 == 3) goto L16;
            r0 = AndroidUtilities.dp(4.0f);
        L16:
            r2.right = r0;
            return;
        L11:
            r42 = AndroidUtilities.dp(4.0f);
            goto L12
        L18:
            r2.left = AndroidUtilities.dp(4.0f);
            r2.right = AndroidUtilities.dp(4.0f);
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$2, reason: invalid class name */
    public class AnonymousClass2 implements RenderNodeWithHash.Renderer {
        final /* synthetic */ ShareAlert this$0;

        public AnonymousClass2(ShareAlert r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.blur3.RenderNodeWithHash.Renderer
        public void renderNodeCalculateHash(IBlur3Hash r3) {
            r3.add(ShareAlert.access$500(this.this$0, Theme.key_windowBackgroundWhite));
            r3.add(SharedConfig.chatBlurEnabled());
        }

        @Override // org.telegram.ui.Components.blur3.RenderNodeWithHash.Renderer
        public void renderNodeUpdateDisplayList(Canvas r3) {
            r3.drawColor(ShareAlert.access$600(this.this$0, Theme.key_windowBackgroundWhite));
            if (SharedConfig.chatBlurEnabled() == false) goto L6;
            ShareAlert.m12681$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).draw(r3, -3);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$20, reason: invalid class name */
    public class AnonymousClass20 extends View {
        final /* synthetic */ ShareAlert this$0;

        public AnonymousClass20(ShareAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void draw(Canvas r6) {
            super.draw(r6);
            ShareAlert.m12663$$Nest$fgetfadeDrawable(this.this$0).setBounds(0, (getMeasuredHeight() - ShareAlert.m12694$$Nest$fgetsystemInsets(this.this$0).bottom) - AndroidUtilities.dp(72.0f), getMeasuredWidth(), getMeasuredHeight());
            ShareAlert.m12663$$Nest$fgetfadeDrawable(this.this$0).draw(r6);
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$21, reason: invalid class name */
    public class AnonymousClass21 extends FrameLayout {
        final /* synthetic */ ShareAlert this$0;

        public static /* synthetic */ void $r8$lambda$JReoIFhc7PWfUIT73EcGoKJ1AAQ(AnonymousClass21 r0, ValueAnimator r1) {
            r0.lambda$onDraw$0(r1);
        }

        public AnonymousClass21(ShareAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        private /* synthetic */ void lambda$onDraw$0(ValueAnimator r2) {
            ShareAlert.m12702$$Nest$fputcaptionEditTextTopOffset(this.this$0, ((Float) r2.getAnimatedValue()).floatValue());
            ShareAlert.m12665$$Nest$fgetframeLayout2(this.this$0).invalidate();
            invalidate();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r6) {
            ShareAlert.m12656$$Nest$fgetcaptionContainerBg(this.this$0).setBounds(0, (int) ShareAlert.m12657$$Nest$fgetcaptionEditTextTopOffset(this.this$0), getMeasuredWidth(), getMeasuredHeight());
            ShareAlert.m12656$$Nest$fgetcaptionContainerBg(this.this$0).draw(r6);
            r6.save();
            r6.clipRect(0.0f, ShareAlert.m12657$$Nest$fgetcaptionEditTextTopOffset(this.this$0), getMeasuredWidth(), getMeasuredHeight());
            super.dispatchDraw(r6);
            r6.restore();
        }

        @Override // android.view.View
        public void onDraw(Canvas r6) {
            if (ShareAlert.m12658$$Nest$fgetchatActivityEnterViewAnimateFromTop(this.this$0) != 0.0f) goto L5;
        L10:
            ShareAlert.m12689$$Nest$fgetshadow(this.this$0)[1].setTranslationY((((-(ShareAlert.m12665$$Nest$fgetframeLayout2(this.this$0).getMeasuredHeight() - AndroidUtilities.dp(48.0f))) + ShareAlert.m12657$$Nest$fgetcaptionEditTextTopOffset(this.this$0)) + ShareAlert.m12660$$Nest$fgetcurrentPanTranslationY(this.this$0)) + ((ShareAlert.m12665$$Nest$fgetframeLayout2(this.this$0).getMeasuredHeight() - AndroidUtilities.dp(48.0f)) * (1.0f - getAlpha())));
            return;
        L5:
            if (ShareAlert.m12658$$Nest$fgetchatActivityEnterViewAnimateFromTop(this.this$0) == (ShareAlert.m12665$$Nest$fgetframeLayout2(this.this$0).getTop() + ShareAlert.m12658$$Nest$fgetchatActivityEnterViewAnimateFromTop(this.this$0))) goto L10;
            if (ShareAlert.m12695$$Nest$fgettopBackgroundAnimator(this.this$0) == null) goto L9;
            ShareAlert.m12695$$Nest$fgettopBackgroundAnimator(this.this$0).cancel();
        L9:
            ShareAlert r62 = this.this$0;
            ShareAlert.m12702$$Nest$fputcaptionEditTextTopOffset(r62, ShareAlert.m12658$$Nest$fgetchatActivityEnterViewAnimateFromTop(r62) - (ShareAlert.m12665$$Nest$fgetframeLayout2(this.this$0).getTop() + ShareAlert.m12657$$Nest$fgetcaptionEditTextTopOffset(this.this$0)));
            ShareAlert r63 = this.this$0;
            ShareAlert.m12715$$Nest$fputtopBackgroundAnimator(r63, ValueAnimator.ofFloat(new float[]{ShareAlert.m12657$$Nest$fgetcaptionEditTextTopOffset(r63), 0.0f}));
            ShareAlert.m12695$$Nest$fgettopBackgroundAnimator(this.this$0).addUpdateListener(new ShareAlert$21$$ExternalSyntheticLambda0(this));
            ShareAlert.m12695$$Nest$fgettopBackgroundAnimator(this.this$0).setInterpolator(CubicBezierInterpolator.DEFAULT);
            ShareAlert.m12695$$Nest$fgettopBackgroundAnimator(this.this$0).setDuration(200);
            ShareAlert.m12695$$Nest$fgettopBackgroundAnimator(this.this$0).start();
            ShareAlert.m12703$$Nest$fputchatActivityEnterViewAnimateFromTop(this.this$0, 0.0f);
            goto L10
        }

        @Override // android.view.View
        public void setAlpha(float r1) {
            super.setAlpha(r1);
            invalidate();
        }

        @Override // android.view.View
        public void setVisibility(int r1) {
            super.setVisibility(r1);
            if (r1 == 0) goto L6;
            ShareAlert.m12689$$Nest$fgetshadow(this.this$0)[1].setTranslationY(0.0f);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$22, reason: invalid class name */
    public class AnonymousClass22 extends EditTextEmoji {
        private ValueAnimator messageEditTextAnimator;
        private int messageEditTextPredrawHeigth;
        private int messageEditTextPredrawScrollY;
        private boolean shouldAnimateEditTextWithBounds;
        final /* synthetic */ ShareAlert this$0;

        /* renamed from: $r8$lambda$Dyq-ULSSV_9pjh-naefevXZuyh0, reason: not valid java name */
        public static /* synthetic */ void m12727$r8$lambda$DyqULSSV_9pjhnaefevXZuyh0(EditTextCaption r0, ValueAnimator r1) {
            r0.setOffsetY(((Float) r1.getAnimatedValue()).floatValue());
        }

        public AnonymousClass22(ShareAlert r1, Context r2, SizeNotifierFrameLayout r3, BaseFragment r4, int r5, boolean r6, Theme.ResourcesProvider r7) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7);
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public void bottomPanelTranslationY(float r1) {
            super.bottomPanelTranslationY(r1);
            this.this$0.updateBottomOverlay();
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public void createEmojiView() {
            super.createEmojiView();
            EmojiView r0 = getEmojiView();
            if (r0 == null) goto L5;
            r0.shouldLightenBackground = false;
            r0.fixBottomTabContainerTranslation = false;
            r0.setShouldDrawBackground(false);
            r0.setBottomInset(ShareAlert.m12694$$Nest$fgetsystemInsets(this.this$0).bottom);
        L5:
            FrameLayout r02 = this.this$0.timestampFrameLayout;
            if (r02 == null) goto L9;
            r02.bringToFront();
        L9:
            if (ShareAlert.m12665$$Nest$fgetframeLayout2(this.this$0) == null) goto L12;
            ShareAlert.m12665$$Nest$fgetframeLayout2(this.this$0).bringToFront();
        L12:
            if (ShareAlert.m12700$$Nest$fgetwriteButtonContainer(this.this$0) == null) goto L15;
            ShareAlert.m12700$$Nest$fgetwriteButtonContainer(this.this$0).bringToFront();
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r7) {
            if (this.shouldAnimateEditTextWithBounds == false) goto L8;
            EditTextCaption r0 = ShareAlert.m12659$$Nest$fgetcommentTextView(this.this$0).getEditText();
            r0.setOffsetY(r0.getOffsetY() - ((this.messageEditTextPredrawHeigth - r0.getMeasuredHeight()) + (this.messageEditTextPredrawScrollY - r0.getScrollY())));
            ValueAnimator r1 = ValueAnimator.ofFloat(new float[]{r0.getOffsetY(), 0.0f});
            r1.addUpdateListener(new ShareAlert$22$$ExternalSyntheticLambda0(r0));
            ValueAnimator r02 = this.messageEditTextAnimator;
            if (r02 == null) goto L7;
            r02.cancel();
        L7:
            this.messageEditTextAnimator = r1;
            r1.setDuration(200);
            r1.setInterpolator(CubicBezierInterpolator.DEFAULT);
            r1.start();
            this.shouldAnimateEditTextWithBounds = false;
        L8:
            super.dispatchDraw(r7);
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public void hidePopup(boolean r1) {
            super.hidePopup(r1);
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public void onLineCountChanged(int r2, int r3) {
            if (TextUtils.isEmpty(getEditText().getText()) == true) goto L5;
            this.shouldAnimateEditTextWithBounds = true;
            this.messageEditTextPredrawHeigth = getEditText().getMeasuredHeight();
            this.messageEditTextPredrawScrollY = getEditText().getScrollY();
            invalidate();
        L6:
            ShareAlert.m12703$$Nest$fputchatActivityEnterViewAnimateFromTop(this.this$0, ShareAlert.m12665$$Nest$fgetframeLayout2(r2).getTop() + ShareAlert.m12657$$Nest$fgetcaptionEditTextTopOffset(this.this$0));
            ShareAlert.m12665$$Nest$fgetframeLayout2(this.this$0).invalidate();
            return;
        L5:
            getEditText().animate().cancel();
            getEditText().setOffsetY(0.0f);
            this.shouldAnimateEditTextWithBounds = false;
            goto L6
        }

        @Override // org.telegram.ui.Components.EditTextEmoji
        public void showPopup(int r1) {
            super.showPopup(r1);
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$23, reason: invalid class name */
    public class AnonymousClass23 implements TextWatcher {
        final /* synthetic */ ShareAlert this$0;

        /* renamed from: $r8$lambda$-n8N0UpVNkhvh2KEIF0sZ-J9ZCQ, reason: not valid java name */
        public static /* synthetic */ void m12728$r8$lambda$n8N0UpVNkhvh2KEIF0sZJ9ZCQ(AnonymousClass23 r0) {
            r0.lambda$afterTextChanged$0();
        }

        public AnonymousClass23(ShareAlert r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$afterTextChanged$0() {
            this.this$0.updateSelectedCount(1);
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r1) {
            AndroidUtilities.runOnUIThread(new ShareAlert$23$$ExternalSyntheticLambda0(this));
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$24, reason: invalid class name */
    public class AnonymousClass24 extends FrameLayout {
        final /* synthetic */ ShareAlert this$0;

        public AnonymousClass24(ShareAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
            super.onInitializeAccessibilityNodeInfo(r3);
            r3.setText(LocaleController.formatPluralString("AccDescrShareInChats", this.this$0.selectedDialogs.size(), new Object[0]));
            r3.setClassName(Button.class.getName());
            r3.setLongClickable(true);
            r3.setClickable(true);
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$25, reason: invalid class name */
    public class AnonymousClass25 extends ChatActivityEnterView.SendButton {
        final /* synthetic */ ShareAlert this$0;

        public AnonymousClass25(ShareAlert r1, Context r2, int r3, Theme.ResourcesProvider r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.SendButton
        public int getFillColor() {
            return ShareAlert.access$3300(this.this$0, Theme.key_dialogFloatingButton);
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.SendButton
        public boolean isInScheduleMode() {
            return false;
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.SendButton
        public boolean isInactive() {
            return false;
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.SendButton
        public boolean isOpen() {
            return true;
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.SendButton
        public boolean shouldDrawBackground() {
            return true;
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$26, reason: invalid class name */
    public class AnonymousClass26 implements DialogsSearchAdapter.OnRecentSearchLoaded {
        final /* synthetic */ ShareAlert this$0;

        public AnonymousClass26(ShareAlert r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Adapters.DialogsSearchAdapter.OnRecentSearchLoaded
        public void setRecentSearch(ArrayList<DialogsSearchAdapter.RecentSearchObject> r6, LongSparseArray<DialogsSearchAdapter.RecentSearchObject> r7) {
            int r0 = 0;
            if (r6 == null) goto L13;
            int r2 = 0;
        L6:
            if (r2 >= r6.size()) goto L13;
            TLObject r3 = r6.get(r2).object;
            if ((r3 instanceof TLRPC.Chat) == false) goto L12;
            if (ChatObject.canWriteToChat((TLRPC.Chat) r3) == true) goto L12;
            r6.remove(r2);
            r2 = r2 - 1;
        L12:
            r2 = r2 + 1;
        L13:
            ShareAlert.m12711$$Nest$fputrecentSearchObjects(this.this$0, r6);
            ShareAlert.m12712$$Nest$fputrecentSearchObjectsById(this.this$0, r7);
        L14:
            int r62 = ShareAlert.m12679$$Nest$fgetrecentSearchObjects(this.this$0).size();
            ShareAlert r72 = this.this$0;
            if (r0 >= r62) goto L26;
            DialogsSearchAdapter.RecentSearchObject r63 = (DialogsSearchAdapter.RecentSearchObject) ShareAlert.m12679$$Nest$fgetrecentSearchObjects(r72).get(r0);
            TLObject r73 = r63.object;
            if ((r73 instanceof TLRPC.User) == false) goto L20;
            MessagesController.getInstance(ShareAlert.access$3400(this.this$0)).putUser((TLRPC.User) r63.object, true);
        L25:
            r0 = r0 + 1;
            goto L14
        L20:
            if ((r73 instanceof TLRPC.Chat) == false) goto L23;
            MessagesController.getInstance(ShareAlert.access$3500(this.this$0)).putChat((TLRPC.Chat) r63.object, true);
            goto L25
        L23:
            if ((r73 instanceof TLRPC.EncryptedChat) == false) goto L25;
            MessagesController.getInstance(ShareAlert.access$3600(this.this$0)).putEncryptedChat((TLRPC.EncryptedChat) r63.object, true);
            goto L25
        L26:
            ShareAlert.m12682$$Nest$fgetsearchAdapter(r72).notifyDataSetChanged();
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$27, reason: invalid class name */
    public class AnonymousClass27 implements NotificationCenter.NotificationCenterDelegate {
        final /* synthetic */ ShareAlert this$0;
        final /* synthetic */ View val$cell;
        final /* synthetic */ TLRPC.Dialog val$dialog;
        final /* synthetic */ AtomicReference val$timeoutRef;

        public static /* synthetic */ void $r8$lambda$HUtBdi48DU82zdDCA2BiAdUBiuU(AnonymousClass27 r0, View r1, int[] r2, DynamicAnimation r3, float r4, float r5) {
            r0.lambda$didReceivedNotification$0(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$jupa5cVK62tviqP3KveiLt1fHHE(AnonymousClass27 r0, DynamicAnimation r1, boolean r2, float r3, float r4) {
            r0.lambda$didReceivedNotification$1(r1, r2, r3, r4);
        }

        public AnonymousClass27(ShareAlert r1, TLRPC.Dialog r2, AtomicReference r3, View r4) {
            this.this$0 = r1;
            this.val$dialog = r2;
            this.val$timeoutRef = r3;
            this.val$cell = r4;
        }

        private /* synthetic */ void lambda$didReceivedNotification$0(View r1, int[] r2, DynamicAnimation r3, float r4, float r5) {
            ShareAlert r0 = this.this$0;
            ShareAlert.m12722$$Nest$minvalidateTopicsAnimation(r0, r1, r2, r4 / 1000.0f);
        }

        private /* synthetic */ void lambda$didReceivedNotification$1(DynamicAnimation r1, boolean r2, float r3, float r4) {
            ShareAlert.m12667$$Nest$fgetgridView(this.this$0).setVisibility(8);
            ShareAlert.m12684$$Nest$fgetsearchGridView(this.this$0).setVisibility(8);
            this.this$0.searchView.setVisibility(8);
            ShareAlert.m12717$$Nest$fputtopicsAnimation(this.this$0, null);
        }

        @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
        @SuppressLint({"NotifyDataSetChanged"})
        public void didReceivedNotification(int r4, int r5, Object... r6) {
            if (((Long) r6[0]).longValue() == (-this.val$dialog.id)) goto L5;
            return;
        L5:
            if (ShareTopicsAdapter.m12749$$Nest$fgettopics(ShareAlert.m12692$$Nest$fgetshareTopicsAdapter(this.this$0)) != null) goto L9;
            if (MessagesController.getInstance(ShareAlert.access$3700(this.this$0)).getTopicsController().getTopics(-this.val$dialog.id) == null) goto L9;
        L10:
            boolean r52 = true;
        L12:
            ShareTopicsAdapter.m12752$$Nest$fputtopics(ShareAlert.m12692$$Nest$fgetshareTopicsAdapter(this.this$0), MessagesController.getInstance(ShareAlert.access$3800(this.this$0)).getTopicsController().getTopics(-this.val$dialog.id));
            ShareTopicsAdapter.m12750$$Nest$fputisBotForum(ShareAlert.m12692$$Nest$fgetshareTopicsAdapter(this.this$0), UserObject.isBotForum(ShareAlert.access$3900(this.this$0), this.val$dialog.id));
            ShareTopicsAdapter.m12751$$Nest$fputisBotForumWithManageTopics(ShareAlert.m12692$$Nest$fgetshareTopicsAdapter(this.this$0), UserObject.isBotForumWithEditableTopics(ShareAlert.access$4000(this.this$0), this.val$dialog.id));
            if (r52 == false) goto L16;
            ShareAlert.m12692$$Nest$fgetshareTopicsAdapter(this.this$0).notifyDataSetChanged();
        L16:
            if (ShareTopicsAdapter.m12749$$Nest$fgettopics(ShareAlert.m12692$$Nest$fgetshareTopicsAdapter(this.this$0)) == null) goto L18;
            NotificationCenter.getInstance(ShareAlert.access$4100(this.this$0)).removeObserver(this, NotificationCenter.topicsDidLoaded);
        L18:
            if (r52 == false) goto L40;
            ShareAlert.m12697$$Nest$fgettopicsGridView(this.this$0).setVisibility(0);
            ShareAlert.m12697$$Nest$fgettopicsGridView(this.this$0).setAlpha(0.0f);
            this.this$0.topicsBackActionBar.setVisibility(0);
            this.this$0.topicsBackActionBar.setAlpha(0.0f);
            boolean r42 = UserObject.isBotForum(ShareAlert.access$4200(this.this$0), this.val$dialog.id);
            ShareAlert r53 = this.this$0;
            if (r42 == false) goto L22;
            r53.topicsBackActionBar.setTitle(DialogObject.getShortName(MessagesController.getInstance(ShareAlert.access$4300(r53)).getUser(Long.valueOf(this.val$dialog.id))));
            this.this$0.topicsBackActionBar.setSubtitle(LocaleController.getString(R.string.SelectChat));
        L26:
            ShareAlert r43 = this.this$0;
            ShareAlert.m12714$$Nest$fputsearchWasVisibleBeforeTopics(r43, ShareAlert.m12685$$Nest$fgetsearchIsVisible(r43));
            if (ShareAlert.m12696$$Nest$fgettopicsAnimation(this.this$0) == null) goto L29;
            ShareAlert.m12696$$Nest$fgettopicsAnimation(this.this$0).cancel();
        L29:
            int[] r44 = new int[2];
            ShareAlert r54 = this.this$0;
            SpringAnimation r0 = new SpringAnimation(new FloatValueHolder(0.0f));
            SpringForce r62 = new SpringForce(1000.0f);
            if (ShareAlert.m12675$$Nest$fgetparentFragment(this.this$0) != null) goto L32;
        L34:
            float r1 = 800.0f;
        L35:
            ShareAlert.m12717$$Nest$fputtopicsAnimation(r54, r0.setSpring(r62.setStiffness(r1).setDampingRatio(1.0f)));
            ShareAlert.m12696$$Nest$fgettopicsAnimation(this.this$0).addUpdateListener(new ShareAlert$27$$ExternalSyntheticLambda0(this, this.val$cell, r44));
            ShareAlert.m12696$$Nest$fgettopicsAnimation(this.this$0).addEndListener(new ShareAlert$27$$ExternalSyntheticLambda1(this));
            ShareAlert.m12696$$Nest$fgettopicsAnimation(this.this$0).start();
            if (this.val$timeoutRef.get() == null) goto L41;
            AndroidUtilities.cancelRunOnUIThread((Runnable) this.val$timeoutRef.get());
            this.val$timeoutRef.set(null);
            return;
        L41:
            return;
        L32:
            if (ShareAlert.m12675$$Nest$fgetparentFragment(this.this$0).shareAlertDebugTopicsSlowMotion == false) goto L34;
            r1 = 10.0f;
            goto L35
        L22:
            boolean r45 = ChatObject.isMonoForum(ShareAlert.access$4400(r53), this.val$dialog.id);
            ShareAlert r55 = this.this$0;
            if (r45 == false) goto L25;
            r55.topicsBackActionBar.setTitle(ForumUtilities.getMonoForumTitle(ShareAlert.access$4500(r55), MessagesController.getInstance(ShareAlert.access$4600(this.this$0)).getChat(Long.valueOf(-this.val$dialog.id))));
            this.this$0.topicsBackActionBar.setSubtitle(LocaleController.getString(R.string.SelectChat));
            goto L26
        L25:
            r55.topicsBackActionBar.setTitle(MessagesController.getInstance(ShareAlert.access$4700(r55)).getChat(Long.valueOf(-this.val$dialog.id)).title);
            this.this$0.topicsBackActionBar.setSubtitle(LocaleController.getString(R.string.SelectTopic));
            goto L26
        L40:
            return;
        L9:
            if (this.val$timeoutRef.get() == null) goto L10;
            r52 = false;
            goto L12
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$28, reason: invalid class name */
    public class AnonymousClass28 extends ActionBarPopupWindow.ActionBarPopupWindowLayout {
        final Path path;
        final /* synthetic */ ShareAlert this$0;

        public AnonymousClass28(ShareAlert r1, Context r2, int r3, Theme.ResourcesProvider r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
            this.path = new Path();
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r6, View r7, long r8) {
            r6.save();
            this.path.rewind();
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(r7.getLeft(), r7.getTop(), r7.getRight(), r7.getBottom());
            this.path.addRoundRect(r0, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), Path.Direction.CW);
            r6.clipPath(this.path);
            boolean r5 = super.drawChild(r6, r7, r8);
            r6.restore();
            return r5;
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$29, reason: invalid class name */
    public class AnonymousClass29 implements View.OnTouchListener {
        private Rect popupRect;
        final /* synthetic */ ShareAlert this$0;

        public AnonymousClass29(ShareAlert r1) {
            this.this$0 = r1;
            this.popupRect = new Rect();
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View r2, MotionEvent r3) {
            if (r3.getActionMasked() == 0) goto L5;
            return false;
        L5:
            if (ShareAlert.m12688$$Nest$fgetsendPopupWindow(this.this$0) != null) goto L7;
            return false;
        L7:
            if (ShareAlert.m12688$$Nest$fgetsendPopupWindow(this.this$0).isShowing() == false) goto L15;
            r2.getHitRect(this.popupRect);
            if (this.popupRect.contains((int) r3.getX(), (int) r3.getY()) == true) goto L16;
            ShareAlert.m12688$$Nest$fgetsendPopupWindow(this.this$0).dismiss();
            return false;
        L16:
            return false;
        L15:
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$3, reason: invalid class name */
    public class AnonymousClass3 extends DarkThemeResourceProvider {
        final /* synthetic */ ShareAlert this$0;

        public AnonymousClass3(ShareAlert r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Stories.DarkThemeResourceProvider
        public void appendColors() {
            SparseIntArray r0 = this.sparseIntArray;
            int r1 = Theme.key_windowBackgroundGray;
            int r2 = Theme.key_dialogBackground;
            r0.put(r1, getColor(r2));
            this.sparseIntArray.put(Theme.key_divider, -15264235);
            this.sparseIntArray.put(Theme.key_chat_messagePanelIcons, Theme.multAlpha(-1, 0.45f));
            this.sparseIntArray.put(Theme.key_dialogBackgroundGray, 352321535);
            this.sparseIntArray.put(Theme.key_chat_emojiPanelBackground, getColor(r2));
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$30, reason: invalid class name */
    public class AnonymousClass30 implements View.OnTouchListener {
        private Rect popupRect;
        final /* synthetic */ ShareAlert this$0;

        public AnonymousClass30(ShareAlert r1) {
            this.this$0 = r1;
            this.popupRect = new Rect();
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View r2, MotionEvent r3) {
            if (r3.getActionMasked() == 0) goto L5;
            return false;
        L5:
            if (ShareAlert.m12688$$Nest$fgetsendPopupWindow(this.this$0) != null) goto L7;
            return false;
        L7:
            if (ShareAlert.m12688$$Nest$fgetsendPopupWindow(this.this$0).isShowing() == false) goto L15;
            r2.getHitRect(this.popupRect);
            if (this.popupRect.contains((int) r3.getX(), (int) r3.getY()) == true) goto L16;
            ShareAlert.m12688$$Nest$fgetsendPopupWindow(this.this$0).dismiss();
            return false;
        L16:
            return false;
        L15:
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$31, reason: invalid class name */
    public class AnonymousClass31 extends TranslateBeforeSendWrapper {
        final /* synthetic */ ShareAlert this$0;

        /* renamed from: org.telegram.ui.Components.ShareAlert$31$1, reason: invalid class name */
        public class AnonymousClass1 implements TranslatorUtils.TranslateCallback {
            final /* synthetic */ AnonymousClass31 this$1;
            final /* synthetic */ AlertDialog val$progressDialog;

            public AnonymousClass1(AnonymousClass31 r1, AlertDialog r2) {
                this.this$1 = r1;
                this.val$progressDialog = r2;
            }

            @Override // com.exteragram.messenger.translator.TranslatorUtils.TranslateCallback
            public void onFailed() {
                this.val$progressDialog.dismiss();     // Catch: Exception -> L5
            L3:
                BulletinFactory.of(ShareAlert.m12655$$Nest$fgetbulletinContainer(this.this$1.this$0), AnonymousClass31.access$4900(this.this$1)).createErrorBulletin(LocaleController.getString(R.string.TranslationFailedAlert2)).show();
            }

            @Override // com.exteragram.messenger.translator.TranslatorUtils.TranslateCallback
            public void onSuccess(TLRPC.TL_textWithEntities r8) {
                this.val$progressDialog.dismiss();     // Catch: Exception -> L9
            L3:
                SpannableStringBuilder r1 = SpannableStringBuilder.valueOf(r8.text);
                MessageObject.addEntitiesToText(r1, r8.entities, true, true, false, true);
                ShareAlert.m12659$$Nest$fgetcommentTextView(this.this$1.this$0).setText(r1);
                ShareAlert.m12659$$Nest$fgetcommentTextView(this.this$1.this$0).setSelection(r1.length());
                if (ShareAlert.m12688$$Nest$fgetsendPopupWindow(this.this$1.this$0) != null) goto L6;
                return;
            L6:
                if (ShareAlert.m12688$$Nest$fgetsendPopupWindow(this.this$1.this$0).isShowing() == false) goto L13;
                ShareAlert.m12688$$Nest$fgetsendPopupWindow(this.this$1.this$0).dismiss();
                return;
            }
        }

        public AnonymousClass31(ShareAlert r1, Context r2, boolean r3, boolean r4, Theme.ResourcesProvider r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        public static /* synthetic */ Theme.ResourcesProvider access$4900(AnonymousClass31 r0) {
            return r0.resourcesProvider;
        }

        @Override // com.exteragram.messenger.components.TranslateBeforeSendWrapper
        public void onClick() {
            if (ShareAlert.m12688$$Nest$fgetsendPopupWindow(this.this$0) != null) goto L5;
        L7:
            AlertDialog r0 = new AlertDialog(getContext(), 3, this.resourcesProvider);
            r0.showDelayed(150);
            CharSequence[] r3 = {ShareAlert.m12659$$Nest$fgetcommentTextView(this.this$0).getText()};
            TranslatorUtils.translate(r3[0], TranslatorUtils.getResolvedSendTargetLanguageCode(), MediaDataController.getInstance(ShareAlert.access$4800(this.this$0)).getEntities(r3, true), new AnonymousClass1(this, r0));
            return;
        L5:
            if (ShareAlert.m12688$$Nest$fgetsendPopupWindow(this.this$0).isShowing() == false) goto L7;
            ShareAlert.m12688$$Nest$fgetsendPopupWindow(this.this$0).dismiss();
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$32, reason: invalid class name */
    public class AnonymousClass32 extends AnimatorListenerAdapter {
        final /* synthetic */ ShareAlert this$0;
        final /* synthetic */ int val$num;
        final /* synthetic */ boolean val$show;

        public AnonymousClass32(ShareAlert r1, int r2, boolean r3) {
            this.this$0 = r1;
            this.val$num = r2;
            this.val$show = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r3) {
            if (ShareAlert.m12690$$Nest$fgetshadowAnimation(this.this$0)[this.val$num] != null) goto L5;
            return;
        L5:
            if (ShareAlert.m12690$$Nest$fgetshadowAnimation(this.this$0)[this.val$num].equals(r3) == false) goto L9;
            ShareAlert.m12690$$Nest$fgetshadowAnimation(this.this$0)[this.val$num] = null;
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            if (ShareAlert.m12690$$Nest$fgetshadowAnimation(this.this$0)[this.val$num] != null) goto L5;
            return;
        L5:
            if (ShareAlert.m12690$$Nest$fgetshadowAnimation(this.this$0)[this.val$num].equals(r3) == true) goto L7;
            return;
        L7:
            if (this.val$show == true) goto L9;
            ShareAlert.m12689$$Nest$fgetshadow(this.this$0)[this.val$num].setVisibility(4);
        L9:
            ShareAlert.m12690$$Nest$fgetshadowAnimation(this.this$0)[this.val$num] = null;
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$33, reason: invalid class name */
    public class AnonymousClass33 extends AnimatorListenerAdapter {
        final /* synthetic */ ShareAlert this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass33(ShareAlert r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            if (r2.equals(ShareAlert.m12653$$Nest$fgetanimatorSet(this.this$0)) == false) goto L6;
            ShareAlert.m12701$$Nest$fputanimatorSet(this.this$0, null);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            if (r3.equals(ShareAlert.m12653$$Nest$fgetanimatorSet(this.this$0)) == false) goto L17;
            boolean r32 = this.val$show;
            ShareAlert r0 = this.this$0;
            if (r32 == true) goto L13;
            ShareAlert.m12665$$Nest$fgetframeLayout2(r0).setVisibility(4);
            ShareAlert r33 = this.this$0;
            if (r33.timestampFrameLayout != null) goto L9;
        L11:
            ShareAlert.m12700$$Nest$fgetwriteButtonContainer(this.this$0).setVisibility(4);
        L15:
            ShareAlert.m12701$$Nest$fputanimatorSet(this.this$0, null);
            return;
        L9:
            if (ShareAlert.m12676$$Nest$fgetpickerBottom(r33) != null) goto L11;
            this.this$0.timestampFrameLayout.setVisibility(4);
            goto L11
        L13:
            if (ShareAlert.m12676$$Nest$fgetpickerBottom(r0) == null) goto L15;
            ShareAlert.m12676$$Nest$fgetpickerBottom(this.this$0).setVisibility(4);
            goto L15
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$4, reason: invalid class name */
    public class AnonymousClass4 extends BottomSheet.BottomSheetDelegate {
        final /* synthetic */ ShareAlert this$0;

        public AnonymousClass4(ShareAlert r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.BottomSheet.BottomSheetDelegate, org.telegram.ui.ActionBar.BottomSheet.BottomSheetDelegateInterface
        public void onOpenAnimationEnd() {
            ShareAlert.m12706$$Nest$fputfullyShown(this.this$0, true);
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$5, reason: invalid class name */
    public class AnonymousClass5 extends SizeNotifierFrameLayout {
        private int fromOffsetTop;
        private int fromScrollY;
        private boolean fullHeight;
        private boolean ignoreLayout;
        private boolean lightStatusBar;
        private final AnimatedFloat pinnedToTop;
        private int previousTopOffset;
        private RectF rect1;
        final /* synthetic */ ShareAlert this$0;
        private int toOffsetTop;
        private int toScrollY;
        private int topOffset;

        /* renamed from: org.telegram.ui.Components.ShareAlert$5$1, reason: invalid class name */
        public class AnonymousClass1 extends AdjustPanLayoutHelper {
            final /* synthetic */ AnonymousClass5 this$1;

            public AnonymousClass1(AnonymousClass5 r1, View r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.ActionBar.AdjustPanLayoutHelper
            public boolean heightAnimationEnabled() {
                if (this.this$1.this$0.isDismissed() == false) goto L5;
                return false;
            L5:
                if (ShareAlert.m12666$$Nest$fgetfullyShown(this.this$1.this$0) == true) goto L8;
                return false;
            L8:
                return !ShareAlert.m12659$$Nest$fgetcommentTextView(this.this$1.this$0).isPopupVisible();
            }

            @Override // org.telegram.ui.ActionBar.AdjustPanLayoutHelper
            public void onPanTranslationUpdate(float r5, float r6, boolean r7) {
                ShareAlert.m12708$$Nest$fputkeyboardT(this.this$1.this$0, r6);
                super.onPanTranslationUpdate(r5, r6, r7);
                int r0 = 0;
            L3:
                int r1 = ShareAlert.access$700(this.this$1.this$0).getChildCount();
                AnonymousClass5 r2 = this.this$1;
                if (r0 >= r1) goto L21;
                View r12 = ShareAlert.access$800(r2.this$0).getChildAt(r0);
                if (r12 == ShareAlert.m12676$$Nest$fgetpickerBottom(this.this$1.this$0)) goto L20;
                if (r12 == ShareAlert.m12655$$Nest$fgetbulletinContainer(this.this$1.this$0)) goto L20;
                if (r12 == ShareAlert.m12689$$Nest$fgetshadow(this.this$1.this$0)[1]) goto L20;
                if (r12 == ShareAlert.m12693$$Nest$fgetsharesCountLayout(this.this$1.this$0)) goto L20;
                if (r12 == ShareAlert.m12665$$Nest$fgetframeLayout2(this.this$1.this$0)) goto L20;
                ShareAlert r22 = this.this$1.this$0;
                if (r12 == r22.timestampFrameLayout) goto L20;
                if (r12 == ShareAlert.m12700$$Nest$fgetwriteButtonContainer(r22)) goto L20;
                r12.setTranslationY(r5);
            L20:
                r0 = r0 + 1;
                goto L3
            L21:
                ShareAlert.m12705$$Nest$fputcurrentPanTranslationY(r2.this$0, r5);
                if (AnonymousClass5.m12730$$Nest$fgetfromScrollY(this.this$1) == (-1)) goto L31;
                if (r7 == true) goto L26;
                r6 = 1.0f - r6;
            L26:
                float r13 = 1.0f - r6;
                ShareAlert.m12713$$Nest$fputscrollOffsetY(this.this$1.this$0, (int) ((AnonymousClass5.m12730$$Nest$fgetfromScrollY(r5) * r13) + (AnonymousClass5.m12733$$Nest$fgettoScrollY(this.this$1) * r6)));
                float r52 = ShareAlert.m12660$$Nest$fgetcurrentPanTranslationY(this.this$1.this$0) + ((AnonymousClass5.m12730$$Nest$fgetfromScrollY(this.this$1) - AnonymousClass5.m12733$$Nest$fgettoScrollY(this.this$1)) * r13);
                ShareAlert.m12667$$Nest$fgetgridView(this.this$1.this$0).setTranslationY(r52);
                AnonymousClass5 r62 = this.this$1;
                if (r7 == false) goto L29;
                ShareAlert.m12684$$Nest$fgetsearchGridView(r62.this$0).setTranslationY(r52);
            L40:
                ShareAlert.m12667$$Nest$fgetgridView(this.this$1.this$0).setTopGlowOffset((int) (ShareAlert.m12680$$Nest$fgetscrollOffsetY(this.this$1.this$0) + ShareAlert.m12660$$Nest$fgetcurrentPanTranslationY(this.this$1.this$0)));
                ShareAlert.m12664$$Nest$fgetframeLayout(this.this$1.this$0).setTranslationY(ShareAlert.m12680$$Nest$fgetscrollOffsetY(this.this$1.this$0) + ShareAlert.m12660$$Nest$fgetcurrentPanTranslationY(this.this$1.this$0));
                ShareAlert.m12683$$Nest$fgetsearchEmptyView(this.this$1.this$0).setTranslationY(ShareAlert.m12680$$Nest$fgetscrollOffsetY(this.this$1.this$0) + ShareAlert.m12660$$Nest$fgetcurrentPanTranslationY(this.this$1.this$0));
                ShareAlert.m12665$$Nest$fgetframeLayout2(this.this$1.this$0).invalidate();
                ShareAlert r53 = this.this$1.this$0;
                r53.setCurrentPanTranslationY(ShareAlert.m12660$$Nest$fgetcurrentPanTranslationY(r53));
                this.this$1.this$0.updateBottomOverlay();
                this.this$1.invalidate();
                return;
            L29:
                ShareAlert.m12684$$Nest$fgetsearchGridView(r62.this$0).setTranslationY(r52 + ShareAlert.m12667$$Nest$fgetgridView(this.this$1.this$0).getPaddingTop());
                goto L40
            L31:
                if (AnonymousClass5.m12729$$Nest$fgetfromOffsetTop(this.this$1) == (-1)) goto L40;
                float r14 = 1.0f - r6;
                ShareAlert.m12713$$Nest$fputscrollOffsetY(this.this$1.this$0, (int) ((AnonymousClass5.m12729$$Nest$fgetfromOffsetTop(r5) * r14) + (AnonymousClass5.m12732$$Nest$fgettoOffsetTop(this.this$1) * r6)));
                if (r7 == true) goto L36;
                r14 = r6;
            L36:
                AnonymousClass5 r54 = this.this$1;
                if (r7 == false) goto L39;
                ShareAlert.m12667$$Nest$fgetgridView(r54.this$0).setTranslationY(ShareAlert.m12660$$Nest$fgetcurrentPanTranslationY(this.this$1.this$0) - ((AnonymousClass5.m12729$$Nest$fgetfromOffsetTop(this.this$1) - AnonymousClass5.m12732$$Nest$fgettoOffsetTop(this.this$1)) * r6));
                goto L40
            L39:
                ShareAlert.m12667$$Nest$fgetgridView(r54.this$0).setTranslationY(ShareAlert.m12660$$Nest$fgetcurrentPanTranslationY(this.this$1.this$0) + ((AnonymousClass5.m12732$$Nest$fgettoOffsetTop(this.this$1) - AnonymousClass5.m12729$$Nest$fgetfromOffsetTop(this.this$1)) * r14));
                goto L40
            }

            @Override // org.telegram.ui.ActionBar.AdjustPanLayoutHelper
            public void onTransitionEnd() {
                super.onTransitionEnd();
                ShareAlert r0 = this.this$1.this$0;
                if (ShareAlert.m12659$$Nest$fgetcommentTextView(r0) == null) goto L7;
                if (ShareAlert.m12659$$Nest$fgetcommentTextView(this.this$1.this$0).isPopupVisible() == false) goto L7;
            L8:
                float r1 = 1.0f;
            L10:
                ShareAlert.m12708$$Nest$fputkeyboardT(r0, r1);
                ShareAlert.m12709$$Nest$fputpanTranslationMoveLayout(this.this$1.this$0, false);
                ShareAlert r02 = this.this$1.this$0;
                ShareAlert.m12710$$Nest$fputpreviousScrollOffsetY(r02, ShareAlert.m12680$$Nest$fgetscrollOffsetY(r02));
                ShareAlert.m12667$$Nest$fgetgridView(this.this$1.this$0).setTopGlowOffset(ShareAlert.m12680$$Nest$fgetscrollOffsetY(this.this$1.this$0));
                ShareAlert.m12664$$Nest$fgetframeLayout(this.this$1.this$0).setTranslationY(ShareAlert.m12680$$Nest$fgetscrollOffsetY(this.this$1.this$0));
                ShareAlert.m12683$$Nest$fgetsearchEmptyView(this.this$1.this$0).setTranslationY(ShareAlert.m12680$$Nest$fgetscrollOffsetY(this.this$1.this$0));
                ShareAlert.m12667$$Nest$fgetgridView(this.this$1.this$0).setTranslationY(0.0f);
                ShareAlert.m12684$$Nest$fgetsearchGridView(this.this$1.this$0).setTranslationY(0.0f);
                this.this$1.this$0.updateBottomOverlay();
                return;
            L7:
                if (ShareAlert.m12671$$Nest$fgetkeyboardSize2(this.this$1.this$0) > AndroidUtilities.dp(20.0f)) goto L8;
                r1 = 0.0f;
                goto L10
            }

            @Override // org.telegram.ui.ActionBar.AdjustPanLayoutHelper
            public void onTransitionStart(boolean r5, int r6) {
                super.onTransitionStart(r5, r6);
                int r62 = ShareAlert.m12678$$Nest$fgetpreviousScrollOffsetY(this.this$1.this$0);
                int r0 = ShareAlert.m12680$$Nest$fgetscrollOffsetY(this.this$1.this$0);
                AnonymousClass5 r1 = this.this$1;
                if (r62 == r0) goto L5;
                AnonymousClass5.m12736$$Nest$fputfromScrollY(r1, ShareAlert.m12678$$Nest$fgetpreviousScrollOffsetY(r1.this$0));
                AnonymousClass5 r63 = this.this$1;
                AnonymousClass5.m12738$$Nest$fputtoScrollY(r63, ShareAlert.m12680$$Nest$fgetscrollOffsetY(r63.this$0));
                ShareAlert.m12709$$Nest$fputpanTranslationMoveLayout(this.this$1.this$0, true);
                AnonymousClass5 r64 = this.this$1;
                ShareAlert.m12713$$Nest$fputscrollOffsetY(r64.this$0, AnonymousClass5.m12730$$Nest$fgetfromScrollY(r64));
            L6:
                int r65 = AnonymousClass5.m12734$$Nest$fgettopOffset(this.this$1);
                int r02 = AnonymousClass5.m12731$$Nest$fgetpreviousTopOffset(this.this$1);
                AnonymousClass5 r12 = this.this$1;
                if (r65 == r02) goto L17;
                AnonymousClass5.m12735$$Nest$fputfromOffsetTop(r12, 0);
                AnonymousClass5.m12737$$Nest$fputtoOffsetTop(this.this$1, 0);
                ShareAlert.m12709$$Nest$fputpanTranslationMoveLayout(this.this$1.this$0, true);
                AnonymousClass5 r66 = this.this$1;
                if (r5 == true) goto L11;
                AnonymousClass5.m12737$$Nest$fputtoOffsetTop(r66, AnonymousClass5.m12732$$Nest$fgettoOffsetTop(r66) - (AnonymousClass5.m12734$$Nest$fgettopOffset(this.this$1) - AnonymousClass5.m12731$$Nest$fgetpreviousTopOffset(this.this$1)));
            L12:
                AnonymousClass5 r67 = this.this$1;
                ShareAlert r03 = r67.this$0;
                if (r5 == false) goto L15;
                int r52 = AnonymousClass5.m12730$$Nest$fgetfromScrollY(r67);
            L16:
                ShareAlert.m12713$$Nest$fputscrollOffsetY(r03, r52);
            L18:
                ShareAlert.m12667$$Nest$fgetgridView(this.this$1.this$0).setTopGlowOffset((int) (ShareAlert.m12660$$Nest$fgetcurrentPanTranslationY(this.this$1.this$0) + ShareAlert.m12680$$Nest$fgetscrollOffsetY(this.this$1.this$0)));
                ShareAlert.m12664$$Nest$fgetframeLayout(this.this$1.this$0).setTranslationY(ShareAlert.m12660$$Nest$fgetcurrentPanTranslationY(this.this$1.this$0) + ShareAlert.m12680$$Nest$fgetscrollOffsetY(this.this$1.this$0));
                ShareAlert.m12683$$Nest$fgetsearchEmptyView(this.this$1.this$0).setTranslationY(ShareAlert.m12660$$Nest$fgetcurrentPanTranslationY(this.this$1.this$0) + ShareAlert.m12680$$Nest$fgetscrollOffsetY(this.this$1.this$0));
                this.this$1.invalidate();
                return;
            L15:
                r52 = AnonymousClass5.m12733$$Nest$fgettoScrollY(r67);
                goto L16
            L11:
                AnonymousClass5.m12737$$Nest$fputtoOffsetTop(r66, AnonymousClass5.m12732$$Nest$fgettoOffsetTop(r66) + (AnonymousClass5.m12734$$Nest$fgettopOffset(this.this$1) - AnonymousClass5.m12731$$Nest$fgetpreviousTopOffset(this.this$1)));
                goto L12
            L17:
                AnonymousClass5.m12735$$Nest$fputfromOffsetTop(r12, -1);
                goto L18
            L5:
                AnonymousClass5.m12736$$Nest$fputfromScrollY(r1, -1);
                goto L6
            }
        }

        /* renamed from: -$$Nest$fgetfromOffsetTop, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m12729$$Nest$fgetfromOffsetTop(AnonymousClass5 r0) {
            return r0.fromOffsetTop;
        }

        /* renamed from: -$$Nest$fgetfromScrollY, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m12730$$Nest$fgetfromScrollY(AnonymousClass5 r0) {
            return r0.fromScrollY;
        }

        /* renamed from: -$$Nest$fgetpreviousTopOffset, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m12731$$Nest$fgetpreviousTopOffset(AnonymousClass5 r0) {
            return r0.previousTopOffset;
        }

        /* renamed from: -$$Nest$fgettoOffsetTop, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m12732$$Nest$fgettoOffsetTop(AnonymousClass5 r0) {
            return r0.toOffsetTop;
        }

        /* renamed from: -$$Nest$fgettoScrollY, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m12733$$Nest$fgettoScrollY(AnonymousClass5 r0) {
            return r0.toScrollY;
        }

        /* renamed from: -$$Nest$fgettopOffset, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m12734$$Nest$fgettopOffset(AnonymousClass5 r0) {
            return r0.topOffset;
        }

        /* renamed from: -$$Nest$fputfromOffsetTop, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12735$$Nest$fputfromOffsetTop(AnonymousClass5 r0, int r1) {
            r0.fromOffsetTop = r1;
        }

        /* renamed from: -$$Nest$fputfromScrollY, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12736$$Nest$fputfromScrollY(AnonymousClass5 r0, int r1) {
            r0.fromScrollY = r1;
        }

        /* renamed from: -$$Nest$fputtoOffsetTop, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12737$$Nest$fputtoOffsetTop(AnonymousClass5 r0, int r1) {
            r0.toOffsetTop = r1;
        }

        /* renamed from: -$$Nest$fputtoScrollY, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12738$$Nest$fputtoScrollY(AnonymousClass5 r0, int r1) {
            r0.toScrollY = r1;
        }

        public AnonymousClass5(ShareAlert r8, Context r9) {
            this.this$0 = r8;
            super(r9);
            boolean r92 = false;
            this.ignoreLayout = false;
            this.rect1 = new RectF();
            this.adjustPanLayoutHelper = new AnonymousClass1(this, this);
            if (AndroidUtilities.computePerceivedBrightness(ShareAlert.access$2000(r8, Theme.key_dialogBackground)) <= 0.721f) goto L5;
            r92 = true;
        L5:
            this.lightStatusBar = r92;
            this.pinnedToTop = new AnimatedFloat(this, 0, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
        }

        private void onMeasureInternal(int r14, int r15) {
            int r0 = View.MeasureSpec.getSize(r14);
            int r1 = View.MeasureSpec.getSize(r15);
            int r02 = r0 - (ShareAlert.access$1600(this.this$0) * 2);
            ShareAlert.m12707$$Nest$fputkeyboardSize2(this.this$0, measureKeyboardHeight());
            int r5 = 0;
            if (ShareAlert.m12659$$Nest$fgetcommentTextView(this.this$0).isWaitingForKeyboardOpen() == false) goto L5;
        L11:
            this.ignoreLayout = true;
            if (ShareAlert.m12671$$Nest$fgetkeyboardSize2(this.this$0) > AndroidUtilities.dp(20.0f)) goto L29;
            if (AndroidUtilities.isInMultiwindow == true) goto L21;
            if (ShareAlert.access$1700(this.this$0) == false) goto L18;
            int r152 = 0;
        L19:
            r1 = r1 - r152;
            r15 = View.MeasureSpec.makeMeasureSpec(r1, TLObject.FLAG_30);
            goto L21
        L18:
            r152 = ShareAlert.m12659$$Nest$fgetcommentTextView(this.this$0).getEmojiPadding();
        L21:
            if (ShareAlert.m12659$$Nest$fgetcommentTextView(this.this$0).isPopupShowing() == false) goto L23;
            int r2 = 8;
        L25:
            if (ShareAlert.m12677$$Nest$fgetpickerBottomLayout(this.this$0) == null) goto L27;
            ShareAlert.m12677$$Nest$fgetpickerBottomLayout(this.this$0).setVisibility(r2);
        L27:
            int r11 = r15;
            this.ignoreLayout = false;
            int r153 = getChildCount();
        L35:
            if (r5 >= r153) goto L57;
            View r8 = this.getChildAt(r5);
            if (r8 != null) goto L39;
        L40:
            AnonymousClass5 r7 = this;
            int r9 = r14;
        L56:
            r5 = r5 + 1;
            AnonymousClass5 this = r7;
            r14 = r9;
            goto L35
        L39:
            if (r8.getVisibility() == 8) goto L40;
            if (ShareAlert.m12659$$Nest$fgetcommentTextView(this.this$0) != null) goto L44;
        L55:
            r7 = this;
            r9 = r14;
            r7.measureChildWithMargins(r8, r9, 0, r11, 0);
            goto L56
        L44:
            if (ShareAlert.m12659$$Nest$fgetcommentTextView(this.this$0).isPopupView(r8) == false) goto L55;
            if (AndroidUtilities.isInMultiwindow == true) goto L52;
            if (AndroidUtilities.isTablet() == true) goto L52;
            r8.measure(View.MeasureSpec.makeMeasureSpec(r02, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r8.getLayoutParams().height, TLObject.FLAG_30));
        L52:
            if (AndroidUtilities.isTablet() == false) goto L54;
            r8.measure(View.MeasureSpec.makeMeasureSpec(r02, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(Math.min(AndroidUtilities.dp(200.0f), (r1 - ShareAlert.m12694$$Nest$fgetsystemInsets(this.this$0).top) + this.getPaddingTop()), TLObject.FLAG_30));
            goto L40
        L54:
            r8.measure(View.MeasureSpec.makeMeasureSpec(r02, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((r1 - ShareAlert.m12694$$Nest$fgetsystemInsets(this.this$0).top) + this.getPaddingTop(), TLObject.FLAG_30));
            goto L40
        L57:
            this.this$0.updateBottomOverlay();
            return;
        L23:
            r2 = 0;
            goto L25
        L29:
            if (ShareAlert.m12659$$Nest$fgetcommentTextView(this.this$0).isPopupVisible() == true) goto L32;
            ShareAlert.m12659$$Nest$fgetcommentTextView(this.this$0).hideEmojiView();
        L32:
            if (ShareAlert.m12677$$Nest$fgetpickerBottomLayout(this.this$0) == null) goto L27;
            ShareAlert.m12677$$Nest$fgetpickerBottomLayout(this.this$0).setVisibility(8);
            goto L27
        L5:
            if (ShareAlert.m12671$$Nest$fgetkeyboardSize2(this.this$0) > AndroidUtilities.dp(20.0f)) goto L11;
            if (ShareAlert.m12659$$Nest$fgetcommentTextView(this.this$0).isPopupShowing() == true) goto L11;
            if (ShareAlert.m12659$$Nest$fgetcommentTextView(this.this$0).isAnimatePopupClosing() == true) goto L11;
            this.ignoreLayout = true;
            ShareAlert.m12659$$Nest$fgetcommentTextView(this.this$0).hideEmojiView();
            this.ignoreLayout = false;
            goto L11
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r5) {
            if (Build.VERSION.SDK_INT >= 31) goto L5;
        L12:
            r5.save();
            r5.clipRect(0.0f, getPaddingTop() + ShareAlert.m12660$$Nest$fgetcurrentPanTranslationY(this.this$0), getMeasuredWidth(), (getMeasuredHeight() + ShareAlert.m12660$$Nest$fgetcurrentPanTranslationY(this.this$0)) + AndroidUtilities.dp(50.0f));
            super.dispatchDraw(r5);
            r5.restore();
            return;
        L5:
            if (ShareAlert.m12681$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0) == null) goto L12;
            ShareAlert.m12718$$Nest$mblur3_InvalidateBlur(this.this$0);
            if (ShareAlert.m12669$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0) == null) goto L10;
            ShareAlert.m12669$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).setSize(ShareAlert.access$2900(this.this$0).getMeasuredWidth(), ShareAlert.access$3000(this.this$0).getMeasuredHeight());
            ShareAlert.m12669$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).updateDisplayListIfNeeded();
        L10:
            if (ShareAlert.m12668$$Nest$fgetiBlur3SourceGlass(this.this$0) == null) goto L12;
            ShareAlert.m12668$$Nest$fgetiBlur3SourceGlass(this.this$0).setSize(ShareAlert.access$3100(this.this$0).getMeasuredWidth(), ShareAlert.access$3200(this.this$0).getMeasuredHeight());
            ShareAlert.m12668$$Nest$fgetiBlur3SourceGlass(this.this$0).updateDisplayListIfNeeded();
            goto L12
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r6, View r7, long r8) {
            if ((r7 instanceof EmojiView) == false) goto L9;
            if (ShareAlert.m12662$$Nest$fgetemojiViewChildBg(this.this$0) == null) goto L9;
            r6.save();
            ShareAlert.m12662$$Nest$fgetemojiViewChildBg(this.this$0).setBounds(0, r7.getTop(), getMeasuredWidth(), getMeasuredHeight());
            r6.clipPath(ShareAlert.m12662$$Nest$fgetemojiViewChildBg(this.this$0).getPath());
            ShareAlert.m12662$$Nest$fgetemojiViewChildBg(this.this$0).draw(r6);
            boolean r5 = super.drawChild(r6, r7, r8);
            r6.restore();
            return r5;
        L9:
            return super.drawChild(r6, r7, r8);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public void drawList(Canvas r2, boolean r3, ArrayList<SizeNotifierFrameLayout.IViewWithInvalidateCallback> r4) {
            if (ShareAlert.m12667$$Nest$fgetgridView(this.this$0).getVisibility() != 0) goto L8;
            if (ShareAlert.m12667$$Nest$fgetgridView(this.this$0).getAlpha() < 0.0f) goto L8;
            r2.save();
            r2.translate(ShareAlert.m12667$$Nest$fgetgridView(this.this$0).getX(), ShareAlert.m12667$$Nest$fgetgridView(this.this$0).getY());
            ShareAlert.m12667$$Nest$fgetgridView(this.this$0).draw(r2);
            r2.restore();
        L8:
            if (ShareAlert.m12697$$Nest$fgettopicsGridView(this.this$0).getVisibility() != 0) goto L13;
            if (ShareAlert.m12697$$Nest$fgettopicsGridView(this.this$0).getAlpha() < 0.0f) goto L13;
            r2.save();
            r2.translate(ShareAlert.m12697$$Nest$fgettopicsGridView(this.this$0).getX(), ShareAlert.m12697$$Nest$fgettopicsGridView(this.this$0).getY());
            ShareAlert.m12697$$Nest$fgettopicsGridView(this.this$0).draw(r2);
            r2.restore();
        L13:
            if (ShareAlert.m12684$$Nest$fgetsearchGridView(this.this$0).getVisibility() == 0) goto L15;
            return;
        L15:
            if (ShareAlert.m12684$$Nest$fgetsearchGridView(this.this$0).getAlpha() < 0.0f) goto L19;
            r2.save();
            r2.translate(ShareAlert.m12684$$Nest$fgetsearchGridView(this.this$0).getX(), ShareAlert.m12684$$Nest$fgetsearchGridView(this.this$0).getY());
            ShareAlert.m12684$$Nest$fgetsearchGridView(this.this$0).draw(r2);
            r2.restore();
            return;
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public Theme.ResourcesProvider getResourceProvider() {
            return ShareAlert.access$900(this.this$0);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.adjustPanLayoutHelper.setResizableView(this);
            this.adjustPanLayoutHelper.onAttach();
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            ShareAlert.m12720$$Nest$mdismissSendPopupWindow(this.this$0);
            this.adjustPanLayoutHelper.onDetach();
        }

        @Override // android.view.View
        public void onDraw(Canvas r12) {
            r12.save();
            r12.translate(0.0f, ShareAlert.m12660$$Nest$fgetcurrentPanTranslationY(this.this$0));
            int r0 = ((ShareAlert.m12680$$Nest$fgetscrollOffsetY(this.this$0) - ShareAlert.access$2100(this.this$0)) + AndroidUtilities.dp(6.0f)) + this.topOffset;
            ShareAlert r2 = this.this$0;
            int r3 = ((ShareAlert.m12680$$Nest$fgetscrollOffsetY(r2) - ShareAlert.access$2200(this.this$0)) - AndroidUtilities.dp(13.0f)) + this.topOffset;
            ShareAlert.m12704$$Nest$fputcontainerViewTop(r2, r3);
            int r22 = (getMeasuredHeight() + AndroidUtilities.dp(60.0f)) + ShareAlert.access$2300(this.this$0);
            boolean r5 = true;
            if (ShareAlert.access$2400(this.this$0) == true) goto L11;
            r0 = r0 + ShareAlert.m12694$$Nest$fgetsystemInsets(this.this$0).top;
            if (this.fullHeight == true) goto L7;
        L9:
            boolean r4 = false;
        L10:
            int r32 = r3 + ShareAlert.m12694$$Nest$fgetsystemInsets(this.this$0).top;
            int r7 = -ShareAlert.access$2600(this.this$0);
            float r42 = this.pinnedToTop.set(r4);
            r3 = AndroidUtilities.lerp(r32, r7, r42);
        L12:
            ShareAlert.m12691$$Nest$fgetshadowDrawable(this.this$0).setBounds(0, r3, getMeasuredWidth(), r22);
            ShareAlert.m12691$$Nest$fgetshadowDrawable(this.this$0).draw(r12);
            ShareAlert r23 = this.this$0;
            if (r23.bulletinContainer2 == null) goto L26;
            if (r3 <= ShareAlert.m12694$$Nest$fgetsystemInsets(r23).top) goto L17;
        L24:
            this.this$0.bulletinContainer2.setTranslationY(Math.max(0, ((r3 + ShareAlert.access$2700(r2)) - this.this$0.bulletinContainer2.getTop()) - this.this$0.bulletinContainer2.getMeasuredHeight()));
            goto L26
        L17:
            if (this.this$0.bulletinContainer2.getChildCount() <= 0) goto L24;
            this.this$0.bulletinContainer2.setTranslationY(0.0f);
            Bulletin r24 = Bulletin.getVisibleBulletin();
            if (r24 == null) goto L26;
            if (r24.getLayout() == null) goto L23;
            r24.getLayout().setTop(true);
        L23:
            r24.hide();
        L26:
            if (r42 >= 1.0f) goto L28;
            int r33 = AndroidUtilities.dp(36.0f);
            this.rect1.set((getMeasuredWidth() - r33) / 2, r0, (getMeasuredWidth() + r33) / 2, r0 + AndroidUtilities.dp(4.0f));
            Theme.dialogs_onlineCirclePaint.setColor(ShareAlert.access$2800(this.this$0, Theme.key_sheet_scrollUp));
            Theme.dialogs_onlineCirclePaint.setAlpha((int) (r0.getAlpha() * (1.0f - r42)));
            r12.drawRoundRect(this.rect1, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), Theme.dialogs_onlineCirclePaint);
        L28:
            int r02 = getSystemUiVisibility();
            if (this.lightStatusBar == true) goto L31;
        L33:
            boolean r1 = false;
        L35:
            if ((r02 & 8192) > 0) goto L38;
            r5 = false;
        L38:
            if (r1 == r5) goto L43;
            if (r1 == false) goto L41;
            int r03 = r02 | 8192;
        L42:
            setSystemUiVisibility(r03);
            goto L43
        L41:
            r03 = r02 & (-8193);
        L43:
            r12.restore();
            this.previousTopOffset = this.topOffset;
            return;
        L31:
            if (0.0f <= (ShareAlert.m12694$$Nest$fgetsystemInsets(this.this$0).top * 0.5f)) goto L33;
            r1 = true;
            goto L35
        L7:
            if ((ShareAlert.access$2500(this.this$0) + r3) >= ShareAlert.m12694$$Nest$fgetsystemInsets(this.this$0).top) goto L9;
            r4 = true;
            goto L10
        L11:
            r42 = 0.0f;
            goto L12
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r5) {
            if (this.fullHeight == true) goto L11;
            if (r5.getAction() != 0) goto L19;
            if (r5.getY() >= (this.topOffset - AndroidUtilities.dp(30.0f))) goto L19;
            this.this$0.dismiss();
            return true;
        L19:
            return super.onInterceptTouchEvent(r5);
        L11:
            if (r5.getAction() != 0) goto L19;
            if (ShareAlert.m12680$$Nest$fgetscrollOffsetY(this.this$0) == 0) goto L19;
            if (r5.getY() >= (ShareAlert.m12680$$Nest$fgetscrollOffsetY(this.this$0) - AndroidUtilities.dp(30.0f))) goto L19;
            this.this$0.dismiss();
            return true;
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r11, int r12, int r13, int r14, int r15) {
            int r112 = getChildCount();
            int r0 = measureKeyboardHeight();
            int r2 = 0;
            if (ShareAlert.access$1800(this.this$0) == false) goto L6;
        L4:
            int r1 = 0;
        L10:
            setBottomClip(r1);
        L11:
            if (r2 >= r112) goto L51;
            View r3 = getChildAt(r2);
            if (r3.getVisibility() == 8) goto L50;
            FrameLayout.LayoutParams r4 = (FrameLayout.LayoutParams) r3.getLayoutParams();
            int r5 = r3.getMeasuredWidth();
            int r6 = r3.getMeasuredHeight();
            int r7 = r4.gravity;
            if (r7 != (-1)) goto L18;
            r7 = 51;
        L18:
            int r8 = r7 & 112;
            int r72 = r7 & 7;
            if (r72 != 1) goto L21;
            int r73 = (((r14 - r12) - r5) / 2) + r4.leftMargin;
            int r9 = r4.rightMargin;
        L24:
            int r74 = r73 - r9;
        L27:
            if (r8 != 16) goto L29;
            int r82 = ((((r15 - r1) - (this.topOffset + r13)) - r6) / 2) + r4.topMargin;
            int r42 = r4.bottomMargin;
        L34:
            int r43 = r82 - r42;
        L38:
            if (ShareAlert.m12659$$Nest$fgetcommentTextView(this.this$0) == null) goto L47;
            if (ShareAlert.m12659$$Nest$fgetcommentTextView(this.this$0).isPopupView(r3) == false) goto L47;
            if (AndroidUtilities.isTablet() == false) goto L45;
            int r44 = getMeasuredHeight();
            int r83 = r3.getMeasuredHeight();
        L44:
            r43 = r44 - r83;
            goto L47
        L45:
            r44 = getMeasuredHeight() + r0;
            r83 = r3.getMeasuredHeight();
        L47:
            if (r3 != ShareAlert.m12654$$Nest$fgetbottomFadeView(this.this$0)) goto L49;
            r43 = r43 + ShareAlert.m12694$$Nest$fgetsystemInsets(this.this$0).bottom;
        L49:
            r3.layout(r74, r43, r5 + r74, r6 + r43);
            goto L50
        L29:
            if (r8 != 48) goto L31;
            r43 = (r4.topMargin + getPaddingTop()) + this.topOffset;
            goto L38
        L31:
            if (r8 == 80) goto L33;
            r43 = r4.topMargin;
            goto L38
        L33:
            r82 = ((r15 - r1) - r13) - r6;
            r42 = r4.bottomMargin;
            goto L34
        L21:
            if (r72 == 5) goto L23;
            r74 = r4.leftMargin + getPaddingLeft();
            goto L27
        L23:
            r73 = (((r14 - r12) - r5) - r4.rightMargin) - getPaddingRight();
            r9 = ShareAlert.access$1900(this.this$0);
        L50:
            r2 = r2 + 1;
            goto L11
        L51:
            notifyHeightChanged();
            this.this$0.updateBottomOverlay();
            ShareAlert.m12725$$Nest$mupdateLayout(this.this$0);
            return;
        L6:
            if (r0 > AndroidUtilities.dp(20.0f)) goto L4;
            if (AndroidUtilities.isInMultiwindow == true) goto L4;
            r1 = Math.max(ShareAlert.m12694$$Nest$fgetsystemInsets(this.this$0).bottom, ShareAlert.m12659$$Nest$fgetcommentTextView(this.this$0).getEmojiPadding());
            goto L10
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r11, int r12) {
            if (getLayoutParams().height <= 0) goto L5;
            int r122 = getLayoutParams().height;
        L6:
            GridLayoutManager r0 = ShareAlert.m12672$$Nest$fgetlayoutManager(this.this$0);
            if (getLayoutParams().height > 0) goto L9;
            boolean r1 = true;
        L10:
            r0.setNeedFixGap(r1);
            FillLastGridLayoutManager r02 = ShareAlert.m12686$$Nest$fgetsearchLayoutManager(this.this$0);
            if (getLayoutParams().height > 0) goto L13;
            boolean r13 = true;
        L14:
            r02.setNeedFixGap(r13);
            if (ShareAlert.access$1000(this.this$0) == true) goto L17;
            this.ignoreLayout = true;
            setPadding(ShareAlert.access$1100(this.this$0), ShareAlert.m12694$$Nest$fgetsystemInsets(this.this$0).top, ShareAlert.access$1200(this.this$0), 0);
            this.ignoreLayout = false;
        L17:
            int r03 = r122 - getPaddingTop();
            int r14 = Math.max(ShareAlert.m12682$$Nest$fgetsearchAdapter(this.this$0).getItemCount(), ShareAlert.m12674$$Nest$fgetlistAdapter(this.this$0).getItemCount() - 1);
            int r5 = ((AndroidUtilities.dp(103.0f) + AndroidUtilities.dp(48.0f)) + (Math.max(2, (int) Math.ceil(r14 / 4.0f)) * AndroidUtilities.dp(103.0f))) + ShareAlert.access$1300(this.this$0);
            if (ShareAlert.m12697$$Nest$fgettopicsGridView(this.this$0).getVisibility() == 8) goto L22;
            int r15 = ((AndroidUtilities.dp(103.0f) + AndroidUtilities.dp(48.0f)) + (Math.max(2, (int) Math.ceil((ShareAlert.m12692$$Nest$fgetshareTopicsAdapter(this.this$0).getItemCount() - 1) / 4.0f)) * AndroidUtilities.dp(103.0f))) + ShareAlert.access$1400(this.this$0);
            if (r15 <= r5) goto L22;
            r5 = AndroidUtilities.lerp(r5, r15, ShareAlert.m12697$$Nest$fgettopicsGridView(this.this$0).getAlpha());
        L22:
            if (r5 >= r03) goto L24;
            int r04 = 0;
        L25:
            int r4 = 48;
            if (this.this$0.timestampFrameLayout == null) goto L28;
            int r16 = 48;
        L29:
            int r17 = AndroidUtilities.dp(r16 + 100) + ShareAlert.m12694$$Nest$fgetsystemInsets(this.this$0).bottom;
            if (ShareAlert.m12667$$Nest$fgetgridView(this.this$0).getPaddingTop() == r04) goto L32;
        L33:
            this.ignoreLayout = true;
            ShareAlert.m12667$$Nest$fgetgridView(this.this$0).setPadding(0, r04, 0, r17);
            ShareAlert.m12697$$Nest$fgettopicsGridView(this.this$0).setPadding(0, r04, 0, r17);
            this.ignoreLayout = false;
        L35:
            if (ShareAlert.access$1500(this.this$0) == true) goto L37;
        L45:
            if (r5 < r122) goto L47;
            boolean r05 = true;
        L48:
            this.fullHeight = r05;
            if (r05 == false) goto L51;
            int r06 = 0;
        L52:
            this.topOffset = r06;
            this.ignoreLayout = true;
            ShareAlert.m12719$$Nest$mcheckCurrentList(this.this$0, false);
            this.ignoreLayout = false;
            setMeasuredDimension(View.MeasureSpec.getSize(r11), r122);
            onMeasureInternal(r11, View.MeasureSpec.makeMeasureSpec(r122, TLObject.FLAG_30));
            return;
        L51:
            r06 = r122 - r5;
            goto L52
        L47:
            r05 = false;
            goto L48
        L37:
            if (getLayoutParams().height > 0) goto L45;
            if (ShareAlert.m12684$$Nest$fgetsearchGridView(this.this$0).getPaddingTop() == r04) goto L45;
            this.ignoreLayout = true;
            RecyclerListView r07 = ShareAlert.m12684$$Nest$fgetsearchGridView(this.this$0);
            if (this.this$0.timestampFrameLayout != null) goto L44;
            r4 = 0;
        L44:
            r07.setPadding(0, 0, 0, AndroidUtilities.dp(r4 + 60) + ShareAlert.m12694$$Nest$fgetsystemInsets(this.this$0).bottom);
            this.ignoreLayout = false;
            goto L45
        L32:
            if (ShareAlert.m12667$$Nest$fgetgridView(this.this$0).getPaddingBottom() == r17) goto L35;
        L28:
            r16 = 0;
            goto L29
        L24:
            r04 = r03 - ((r03 / 5) * 3);
            goto L25
        L13:
            r13 = false;
            goto L14
        L9:
            r1 = false;
            goto L10
        L5:
            r122 = View.MeasureSpec.getSize(r12);
            goto L6
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (this.this$0.isDismissed() == false) goto L5;
            return false;
        L5:
            if (super.onTouchEvent(r2) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (this.ignoreLayout == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$6, reason: invalid class name */
    public class AnonymousClass6 extends SwitchView {
        final /* synthetic */ ShareAlert this$0;

        public AnonymousClass6(ShareAlert r1, Context r2) {
            this.this$0 = r1;
            super(r1, r2);
        }

        @Override // org.telegram.ui.Components.ShareAlert.SwitchView
        public void onTabSwitch(int r1) {
            ShareAlert.m12726$$Nest$mupdateLinkTextView(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$7, reason: invalid class name */
    public class AnonymousClass7 implements TextWatcher {
        final /* synthetic */ ShareAlert this$0;

        public AnonymousClass7(ShareAlert r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r5) {
            if (TextUtils.isEmpty(this.this$0.searchView.editText.getText()) == true) goto L6;
            ShareAlert.m12719$$Nest$mcheckCurrentList(this.this$0, false);
        L6:
            if (ShareAlert.m12699$$Nest$fgetupdateSearchAdapter(this.this$0) == false) goto L23;
            String r52 = this.this$0.searchView.editText.getText().toString();
            int r1 = r52.length();
            ShareAlert r2 = this.this$0;
            if (r1 == 0) goto L14;
            if (ShareAlert.m12683$$Nest$fgetsearchEmptyView(r2) == null) goto L19;
            ShareAlert.m12683$$Nest$fgetsearchEmptyView(this.this$0).title.setText(LocaleController.getString(R.string.NoResult));
        L19:
            if (ShareAlert.m12682$$Nest$fgetsearchAdapter(this.this$0) == null) goto L22;
            ShareAlert.m12682$$Nest$fgetsearchAdapter(this.this$0).searchDialogs(r52);
            return;
        L22:
            return;
        L14:
            if (ShareAlert.m12667$$Nest$fgetgridView(r2).getAdapter() == ShareAlert.m12674$$Nest$fgetlistAdapter(this.this$0)) goto L19;
            int r12 = ShareAlert.m12721$$Nest$mgetCurrentTop(this.this$0);
            ShareAlert.m12683$$Nest$fgetsearchEmptyView(this.this$0).title.setText(LocaleController.getString(R.string.NoResult));
            ShareAlert.m12683$$Nest$fgetsearchEmptyView(this.this$0).showProgress(false, true);
            ShareAlert.m12719$$Nest$mcheckCurrentList(this.this$0, false);
            ShareAlert.m12674$$Nest$fgetlistAdapter(this.this$0).notifyDataSetChanged();
            if (r12 <= 0) goto L19;
            ShareAlert.m12672$$Nest$fgetlayoutManager(this.this$0).scrollToPositionWithOffset(0, -r12);
            goto L19
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$8, reason: invalid class name */
    public class AnonymousClass8 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ShareAlert this$0;

        public AnonymousClass8(ShareAlert r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r1) {
            this.this$0.onBackPressed();
        }
    }

    /* renamed from: org.telegram.ui.Components.ShareAlert$9, reason: invalid class name */
    public class AnonymousClass9 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ ShareAlert this$0;

        public AnonymousClass9(ShareAlert r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r1) {
            if (r1 == 0) goto L4;
            return 1;
        L4:
            return ShareAlert.m12698$$Nest$fgettopicsLayoutManager(this.this$0).getSpanCount();
        }
    }

    public static class DialogSearchResult {
        public int date;
        public TLRPC.Dialog dialog;
        public CharSequence name;
        public TLObject object;

        public DialogSearchResult() {
            this.dialog = new TLRPC.TL_dialog();
        }
    }

    public interface ShareAlertDelegate {
        boolean didCopy();

        default void didShare() {
        }
    }

    public class ShareDialogsAdapter extends RecyclerListView.SelectionAdapter {
        private Context context;
        private ArrayList<TLRPC.Dialog> dialogs;
        private LongSparseArray<TLRPC.Dialog> dialogsMap;
        final /* synthetic */ ShareAlert this$0;

        /* renamed from: org.telegram.ui.Components.ShareAlert$ShareDialogsAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends ShareDialogCell {
            final /* synthetic */ ShareDialogsAdapter this$1;

            public AnonymousClass1(ShareDialogsAdapter r1, Context r2, int r3, Theme.ResourcesProvider r4) {
                this.this$1 = r1;
                super(r2, r3, r4);
            }

            @Override // org.telegram.ui.Cells.ShareDialogCell
            public String repostToCustomName() {
                if (this.this$1.this$0.includeStoryFromMessage == false) goto L7;
                return LocaleController.getString(R.string.RepostToStory);
            L7:
                return super.repostToCustomName();
            }
        }

        public class MyStoryDialog extends TLRPC.Dialog {
            final /* synthetic */ ShareDialogsAdapter this$1;

            private MyStoryDialog(ShareDialogsAdapter r3) {
                this.this$1 = r3;
                this.id = LongCompanionObject.MAX_VALUE;
            }

            public /* synthetic */ MyStoryDialog(ShareDialogsAdapter r1, ShareAlertIA r2) {
                this(r1);
            }
        }

        /* renamed from: -$$Nest$fgetdialogs, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m12739$$Nest$fgetdialogs(ShareDialogsAdapter r0) {
            return r0.dialogs;
        }

        /* renamed from: -$$Nest$fgetdialogsMap, reason: not valid java name */
        public static /* bridge */ /* synthetic */ LongSparseArray m12740$$Nest$fgetdialogsMap(ShareDialogsAdapter r0) {
            return r0.dialogsMap;
        }

        public ShareDialogsAdapter(ShareAlert r1, Context r2) {
            this.this$0 = r1;
            this.dialogs = new ArrayList();
            this.dialogsMap = new LongSparseArray();
            this.context = r2;
            fetchDialogs();
        }

        public void fetchDialogs() {
            this.dialogs.clear();
            this.dialogsMap.clear();
            long r0 = UserConfig.getInstance(ShareAlert.access$5000(this.this$0)).clientUserId;
            if (ShareAlert.m12670$$Nest$fgetincludeStory(this.this$0) == false) goto L6;
            MyStoryDialog r2 = new MyStoryDialog(this, null);
            this.dialogs.add(r2);
            this.dialogsMap.put(r2.id, r2);
        L6:
            if (MessagesController.getInstance(ShareAlert.access$5100(this.this$0)).dialogsForward.isEmpty() == true) goto L8;
            TLRPC.Dialog r22 = MessagesController.getInstance(ShareAlert.access$5200(this.this$0)).dialogsForward.get(0);
            this.dialogs.add(r22);
            this.dialogsMap.put(r22.id, r22);
        L8:
            ArrayList r23 = new ArrayList();
            ArrayList<TLRPC.Dialog> r4 = MessagesController.getInstance(ShareAlert.access$5300(this.this$0)).getAllDialogs();
            int r5 = 0;
        L10:
            if (r5 >= r4.size()) goto L50;
            TLRPC.Dialog r6 = r4.get(r5);
            if ((r6 instanceof TLRPC.TL_dialog) == false) goto L49;
            long r8 = r6.id;
            if (r8 == r0) goto L49;
            if (DialogObject.isEncryptedDialog(r8) == true) goto L49;
            if (DialogObject.isUserDialog(r6.id) == true) goto L22;
            TLRPC.Chat r82 = MessagesController.getInstance(ShareAlert.access$5400(this.this$0)).getChat(Long.valueOf(-r6.id));
            if (r82 == null) goto L49;
            if (ChatObject.isNotInChat(r82) == true) goto L49;
            if (r82.gigagroup == false) goto L35;
            if (ChatObject.hasAdminRights(r82) == false) goto L49;
        L35:
            if (ChatObject.isChannel(r82) == false) goto L45;
            if (r82.creator == true) goto L45;
            TLRPC.TL_chatAdminRights r9 = r82.admin_rights;
            if (r9 == null) goto L43;
            if (r9.post_messages == true) goto L45;
        L43:
            if (r82.megagroup == false) goto L49;
        L45:
            if (r6.folder_id != 1) goto L47;
            r23.add(r6);
        L48:
            this.dialogsMap.put(r6.id, r6);
            goto L49
        L47:
            this.dialogs.add(r6);
            goto L48
        L22:
            if (r6.folder_id != 1) goto L24;
            r23.add(r6);
        L25:
            this.dialogsMap.put(r6.id, r6);
            goto L49
        L24:
            this.dialogs.add(r6);
        L49:
            r5 = r5 + 1;
            goto L10
        L50:
            this.dialogs.addAll(r23);
            if (ShareAlert.m12675$$Nest$fgetparentFragment(this.this$0) == null) goto L63;
            int r02 = ShareAlert.m12675$$Nest$fgetparentFragment(this.this$0).shareAlertDebugMode;
            if (r02 != 1) goto L55;
            ArrayList<TLRPC.Dialog> r1 = this.dialogs;
            ArrayList r03 = new ArrayList(r1.subList(0, Math.min(4, r1.size())));
            this.dialogs.clear();
            this.dialogs.addAll(r03);
            goto L63
        L55:
            if (r02 != 2) goto L63;
        L58:
            if (this.dialogs.isEmpty() == true) goto L63;
            if (this.dialogs.size() >= 80) goto L63;
            ArrayList<TLRPC.Dialog> r04 = this.dialogs;
            r04.add(r04.get(r04.size() - 1));
        L63:
            notifyDataSetChanged();
        }

        public TLRPC.Dialog getItem(int r2) {
            int r22 = r2 - 1;
            if (r22 >= 0) goto L5;
            return null;
        L5:
            if (r22 < this.dialogs.size()) goto L8;
            return null;
        L8:
            return this.dialogs.get(r22);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            int r0 = this.dialogs.size();
            if (r0 != 0) goto L5;
            return r0;
        L5:
            return r0 + 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            if (r1 != 0) goto L5;
            return 1;
        L5:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() != 1) goto L6;
            return false;
        L6:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r5, int r6) {
            if (r5.getItemViewType() != 0) goto L12;
            ShareDialogCell r52 = (ShareDialogCell) r5.itemView;
            TLRPC.Dialog r62 = getItem(r6);
            if (r62 == null) goto L13;
            TLRPC.TL_forumTopic r0 = this.this$0.selectedDialogTopics.get(r62);
            boolean r1 = MessagesController.getInstance(ShareAlert.access$5600(this.this$0)).isMonoForum(r62.id);
            boolean r2 = false;
            r52.setTopic(r0, r1, false);
            long r02 = r62.id;
            if (this.this$0.selectedDialogs.indexOfKey(r02) < 0) goto L10;
            r2 = true;
        L10:
            r52.setDialog(r02, r2, null);
            return;
        L13:
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r4, int r5) {
            if (r5 == 0) goto L11;
            View r52 = new View(this.context);
            if (ShareAlert.m12661$$Nest$fgetdarkTheme(this.this$0) == true) goto L7;
        L9:
            float r3 = 56.0f;
        L10:
            r52.setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(r3)));
        L13:
            return new RecyclerListView.Holder(r52);
        L7:
            if (ShareAlert.m12673$$Nest$fgetlinkToCopy(this.this$0)[1] == null) goto L9;
            r3 = 109.0f;
            goto L10
        L11:
            r52 = new AnonymousClass1(this, this.context, 0, ShareAlert.access$5500(this.this$0));
            r52.setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(100.0f)));
            goto L13
        }
    }

    public class ShareSearchAdapter extends RecyclerListView.SelectionAdapter {
        DialogsSearchAdapter.CategoryAdapterRecycler categoryAdapter;
        RecyclerView categoryListView;
        private Context context;
        int firstEmptyViewCell;
        int hintsCell;
        boolean internalDialogsIsSearching;
        int itemsCount;
        int lastFilledItem;
        private int lastGlobalSearchId;
        int lastItemCont;
        private int lastLocalSearchId;
        private int lastSearchId;
        private String lastSearchText;
        int recentDialogsStartRow;
        int resentTitleCell;
        private SearchAdapterHelper searchAdapterHelper;
        private ArrayList<Object> searchResult;
        int searchResultsStartRow;
        private Runnable searchRunnable;
        private Runnable searchRunnable2;
        final /* synthetic */ ShareAlert this$0;

        /* renamed from: org.telegram.ui.Components.ShareAlert$ShareSearchAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends SearchAdapterHelper {
            final /* synthetic */ ShareSearchAdapter this$1;
            final /* synthetic */ ShareAlert val$this$0;

            public AnonymousClass1(ShareSearchAdapter r1, boolean r2, ShareAlert r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // org.telegram.ui.Adapters.SearchAdapterHelper
            public boolean filter(TLObject r1) {
                if ((r1 instanceof TLRPC.Chat) == true) goto L5;
                return true;
            L5:
                if (ChatObject.canWriteToChat((TLRPC.Chat) r1) == true) goto L11;
                return false;
            L11:
                return true;
            }
        }

        /* renamed from: org.telegram.ui.Components.ShareAlert$ShareSearchAdapter$2, reason: invalid class name */
        public class AnonymousClass2 implements SearchAdapterHelper.SearchAdapterHelperDelegate {
            final /* synthetic */ ShareSearchAdapter this$1;
            final /* synthetic */ ShareAlert val$this$0;

            public AnonymousClass2(ShareSearchAdapter r1, ShareAlert r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // org.telegram.ui.Adapters.SearchAdapterHelper.SearchAdapterHelperDelegate
            public boolean canApplySearchResults(int r1) {
                if (r1 != ShareSearchAdapter.m12744$$Nest$fgetlastSearchId(this.this$1)) goto L6;
                return true;
            L6:
                return false;
            }

            @Override // org.telegram.ui.Adapters.SearchAdapterHelper.SearchAdapterHelperDelegate
            public void onDataSetChanged(int r4) {
                ShareSearchAdapter.m12747$$Nest$fputlastGlobalSearchId(this.this$1, r4);
                if (ShareSearchAdapter.m12743$$Nest$fgetlastLocalSearchId(this.this$1) == r4) goto L5;
                ShareSearchAdapter.m12746$$Nest$fgetsearchResult(this.this$1).clear();
            L5:
                ShareSearchAdapter r42 = this.this$1;
                int r0 = r42.lastItemCont;
                if (r42.getItemCount() == 0) goto L8;
            L12:
                this.this$1.this$0.recyclerItemsEnterAnimator.showItemsAnimated(r0);
            L13:
                this.this$1.notifyDataSetChanged();
                ShareAlert.m12719$$Nest$mcheckCurrentList(this.this$1.this$0, true);
                return;
            L8:
                if (ShareSearchAdapter.m12745$$Nest$fgetsearchAdapterHelper(this.this$1).isSearchInProgress() == true) goto L12;
                ShareSearchAdapter r43 = this.this$1;
                if (r43.internalDialogsIsSearching == true) goto L12;
                ShareAlert.m12683$$Nest$fgetsearchEmptyView(r43.this$0).showProgress(false, true);
                goto L13
            }
        }

        /* renamed from: org.telegram.ui.Components.ShareAlert$ShareSearchAdapter$3, reason: invalid class name */
        public class AnonymousClass3 extends RecyclerListView {
            final /* synthetic */ ShareSearchAdapter this$1;

            public AnonymousClass3(ShareSearchAdapter r1, Context r2, Theme.ResourcesProvider r3) {
                this.this$1 = r1;
                super(r2, r3);
            }

            @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
            public boolean onInterceptTouchEvent(MotionEvent r4) {
                if (getParent() == null) goto L14;
                if (getParent().getParent() == null) goto L14;
                ViewParent r0 = getParent().getParent();
                boolean r2 = true;
                if (canScrollHorizontally(-1) == false) goto L9;
            L12:
                r0.requestDisallowInterceptTouchEvent(r2);
                goto L14
            L9:
                if (canScrollHorizontally(1) == true) goto L12;
                r2 = false;
            L14:
                return super.onInterceptTouchEvent(r4);
            }
        }

        /* renamed from: org.telegram.ui.Components.ShareAlert$ShareSearchAdapter$4, reason: invalid class name */
        public class AnonymousClass4 extends LinearLayoutManager {
            final /* synthetic */ ShareSearchAdapter this$1;

            public AnonymousClass4(ShareSearchAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean supportsPredictiveItemAnimations() {
                return false;
            }
        }

        /* renamed from: org.telegram.ui.Components.ShareAlert$ShareSearchAdapter$5, reason: invalid class name */
        public class AnonymousClass5 extends DialogsSearchAdapter.CategoryAdapterRecycler {
            final /* synthetic */ ShareSearchAdapter this$1;

            public AnonymousClass5(ShareSearchAdapter r1, Context r2, int r3, boolean r4, boolean r5, Theme.ResourcesProvider r6) {
                this.this$1 = r1;
                super(r2, r3, r4, r5, r6);
            }

            @Override // org.telegram.ui.Adapters.DialogsSearchAdapter.CategoryAdapterRecycler, androidx.recyclerview.widget.RecyclerView.Adapter
            public void onBindViewHolder(RecyclerView.ViewHolder r9, int r10) {
                HintDialogCell r92 = (HintDialogCell) r9.itemView;
                if (ShareAlert.m12661$$Nest$fgetdarkTheme(this.this$1.this$0) == false) goto L5;
            L6:
                r92.setColors(Theme.key_voipgroup_nameText, Theme.key_voipgroup_inviteMembersBackground);
            L7:
                TLRPC.TL_topPeer r102 = MediaDataController.getInstance(ShareAlert.access$6400(this.this$1.this$0)).hints.get(r10);
                TLRPC.Peer r0 = r102.peer;
                long r1 = r0.user_id;
                TLRPC.User r6 = null;
                if (r1 == 0) goto L10;
                r6 = MessagesController.getInstance(ShareAlert.access$6500(this.this$1.this$0)).getUser(Long.valueOf(r102.peer.user_id));
                TLRPC.Chat r103 = null;
            L17:
                boolean r3 = false;
                if (r1 != r92.getDialogId()) goto L20;
                boolean r02 = true;
            L21:
                r92.setTag(Long.valueOf(r1));
                if (r6 == null) goto L24;
                String r104 = UserObject.getFirstName(r6);
            L27:
                r92.setDialog(r1, true, r104);
                if (this.this$1.this$0.selectedDialogs.indexOfKey(r1) < 0) goto L30;
                r3 = true;
            L30:
                r92.setChecked(r3, r02);
                return;
            L24:
                if (r103 == null) goto L26;
                r104 = r103.title;
                goto L27
            L26:
                r104 = _UrlKt.FRAGMENT_ENCODE_SET;
                goto L27
            L20:
                r02 = false;
                goto L21
            L10:
                long r12 = r0.channel_id;
                if (r12 == 0) goto L13;
                r1 = -r12;
                r103 = MessagesController.getInstance(ShareAlert.access$6600(this.this$1.this$0)).getChat(Long.valueOf(r102.peer.channel_id));
                goto L17
            L13:
                long r03 = r0.chat_id;
                if (r03 == 0) goto L16;
                r1 = -r03;
                r103 = MessagesController.getInstance(ShareAlert.access$6700(this.this$1.this$0)).getChat(Long.valueOf(r102.peer.chat_id));
                goto L17
            L16:
                r1 = 0;
                r103 = null;
                goto L17
            L5:
                if (this.this$1.this$0.forceDarkThemeForHint == false) goto L7;
                goto L6
            }
        }

        /* renamed from: org.telegram.ui.Components.ShareAlert$ShareSearchAdapter$6, reason: invalid class name */
        public class AnonymousClass6 extends View {
            final /* synthetic */ ShareSearchAdapter this$1;

            public AnonymousClass6(ShareSearchAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(ShareAlert.m12686$$Nest$fgetsearchLayoutManager(this.this$1.this$0).lastItemHeight, TLObject.FLAG_30));
            }
        }

        public static /* synthetic */ void $r8$lambda$1bGLfrwAAt1v4YfooAiAfnQU9Xg(ShareSearchAdapter r0, int r1, ArrayList r2) {
            r0.lambda$updateSearchResults$2(r1, r2);
        }

        /* renamed from: $r8$lambda$FRIrbXa-K-Sfp5PmfTnaAZTYg6s, reason: not valid java name */
        public static /* synthetic */ void m12741$r8$lambda$FRIrbXaKSfp5PmfTnaAZTYg6s(ShareSearchAdapter r0, int r1, String r2) {
            r0.lambda$searchDialogs$3(r1, r2);
        }

        /* renamed from: $r8$lambda$L96UGBN3h-V2z4gDzPdl7rMx30s, reason: not valid java name */
        public static /* synthetic */ void m12742$r8$lambda$L96UGBN3hV2z4gDzPdl7rMx30s(ShareSearchAdapter r0, String r1, int r2) {
            r0.lambda$searchDialogsInternal$1(r1, r2);
        }

        public static /* synthetic */ int $r8$lambda$N2leCj1fsSDLiXy7aIlYyot2xnY(Object r0, Object r1) {
            int r02 = ((DialogSearchResult) r0).date;
            int r12 = ((DialogSearchResult) r1).date;
            if (r02 >= r12) goto L6;
            return 1;
        L6:
            if (r02 <= r12) goto L9;
            return -1;
        L9:
            return 0;
        }

        public static /* synthetic */ void $r8$lambda$YpQNtdwmkUE2_3YLdF7aSpIRhCA(ShareSearchAdapter r0, View r1, int r2) {
            r0.lambda$onCreateViewHolder$5(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$Ypqdu1vkjoaE2VBVrCjU_hBse8E(ShareSearchAdapter r0, String r1, int r2) {
            r0.lambda$searchDialogs$4(r1, r2);
        }

        /* renamed from: -$$Nest$fgetlastLocalSearchId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m12743$$Nest$fgetlastLocalSearchId(ShareSearchAdapter r0) {
            return r0.lastLocalSearchId;
        }

        /* renamed from: -$$Nest$fgetlastSearchId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m12744$$Nest$fgetlastSearchId(ShareSearchAdapter r0) {
            return r0.lastSearchId;
        }

        /* renamed from: -$$Nest$fgetsearchAdapterHelper, reason: not valid java name */
        public static /* bridge */ /* synthetic */ SearchAdapterHelper m12745$$Nest$fgetsearchAdapterHelper(ShareSearchAdapter r0) {
            return r0.searchAdapterHelper;
        }

        /* renamed from: -$$Nest$fgetsearchResult, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m12746$$Nest$fgetsearchResult(ShareSearchAdapter r0) {
            return r0.searchResult;
        }

        /* renamed from: -$$Nest$fputlastGlobalSearchId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12747$$Nest$fputlastGlobalSearchId(ShareSearchAdapter r0, int r1) {
            r0.lastGlobalSearchId = r1;
        }

        public ShareSearchAdapter(ShareAlert r2, Context r3) {
            this.this$0 = r2;
            this.searchResult = new ArrayList();
            this.hintsCell = -1;
            this.resentTitleCell = -1;
            this.firstEmptyViewCell = -1;
            this.recentDialogsStartRow = -1;
            this.searchResultsStartRow = -1;
            this.lastFilledItem = -1;
            this.internalDialogsIsSearching = false;
            this.context = r3;
            AnonymousClass1 r32 = new AnonymousClass1(this, false, r2);
            this.searchAdapterHelper = r32;
            r32.setDelegate(new AnonymousClass2(this, r2));
        }

        private /* synthetic */ void lambda$onCreateViewHolder$5(View r7, int r8) {
            HintDialogCell r72 = (HintDialogCell) r7;
            TLRPC.TL_topPeer r82 = MediaDataController.getInstance(ShareAlert.access$7300(this.this$0)).hints.get(r8);
            TLRPC.TL_dialog r0 = new TLRPC.TL_dialog();
            TLRPC.Peer r83 = r82.peer;
            long r1 = r83.user_id;
            if (r1 != 0) goto L13;
            long r12 = r83.channel_id;
            if (r12 == 0) goto L8;
        L7:
            r1 = -r12;
            goto L13
        L8:
            r12 = r83.chat_id;
            if (r12 != 0) goto L7;
            r1 = 0;
        L13:
            if (r72.isBlocked() == false) goto L16;
            ShareAlert.m12724$$Nest$mshowPremiumBlockedToast(this.this$0, r72, r1);
            return;
        L16:
            r0.id = r1;
            ShareAlert.m12723$$Nest$mselectDialog(this.this$0, null, r0);
            if (this.this$0.selectedDialogs.indexOfKey(r1) < 0) goto L19;
            boolean r6 = true;
        L20:
            r72.setChecked(r6, true);
            return;
        L19:
            r6 = false;
            goto L20
        }

        private /* synthetic */ void lambda$searchDialogs$3(int r14, String r15) {
            this.searchRunnable2 = null;
            if (r14 == this.lastSearchId) goto L5;
            return;
        L5:
            this.searchAdapterHelper.queryServerSearch(r15, true, true, true, true, false, 0, false, 0, r14);
        }

        private /* synthetic */ void lambda$searchDialogs$4(String r2, int r3) {
            this.searchRunnable = null;
            searchDialogsInternal(r2, r3);
            ShareAlert$ShareSearchAdapter$$ExternalSyntheticLambda2 r0 = new ShareAlert$ShareSearchAdapter$$ExternalSyntheticLambda2(this, r3, r2);
            this.searchRunnable2 = r0;
            AndroidUtilities.runOnUIThread(r0);
        }

        private /* synthetic */ void lambda$searchDialogsInternal$1(String r21, int r22) {
            String r1 = r21.trim().toLowerCase();     // Catch: Exception -> L176
            int r3 = -1;
            if (r1.length() != 0) goto L7;
            this.lastSearchId = -1;     // Catch: Exception -> L176
            updateSearchResults(new ArrayList(), this.lastSearchId);     // Catch: Exception -> L176
            return;
        L7:
            String r2 = LocaleController.getInstance().getTranslitString(r1);     // Catch: Exception -> L176
            if (r1.equals(r2) == false) goto L10;
        L11:
            r2 = null;
        L12:
            int r6 = 0;
            if (r2 == null) goto L15;
            int r7 = 1;
        L16:
            int r72 = r7 + 1;     // Catch: Exception -> L176
            String[] r8 = new String[r72];     // Catch: Exception -> L176
            r8[0] = r1;     // Catch: Exception -> L176
            if (r2 == null) goto L19;
            r8[1] = r2;     // Catch: Exception -> L176
        L19:
            ArrayList r12 = new ArrayList();     // Catch: Exception -> L176
            ArrayList r23 = new ArrayList();     // Catch: Exception -> L176
            LongSparseArray r9 = new LongSparseArray();     // Catch: Exception -> L176
            SQLiteCursor r10 = MessagesStorage.getInstance(ShareAlert.access$7600(this.this$0)).getDatabase().queryFinalized("SELECT did, date FROM dialogs ORDER BY date DESC LIMIT 400", new Object[0]);     // Catch: Exception -> L176
        L21:
            if (r10.next() == false) goto L32;
            long r11 = r10.longValue(0);     // Catch: Exception -> L176
            DialogSearchResult r13 = new DialogSearchResult();     // Catch: Exception -> L176
            r13.date = r10.intValue(1);     // Catch: Exception -> L176
            r9.put(r11, r13);     // Catch: Exception -> L176
            if (DialogObject.isUserDialog(r11) == true) goto L25;
            if (DialogObject.isChatDialog(r11) == false) goto L21;
            long r112 = -r11;
            if (r23.contains(Long.valueOf(r112)) == true) goto L21;
            r23.add(Long.valueOf(r112));     // Catch: Exception -> L176
            goto L21
        L25:
            if (r12.contains(Long.valueOf(r11)) == true) goto L21;
            r12.add(Long.valueOf(r11));     // Catch: Exception -> L176
            goto L21
        L32:
            r10.dispose();     // Catch: Exception -> L176
            int r14 = 2;
            String r15 = " ";
            if (r12.isEmpty() == true) goto L81;
            SQLiteCursor r16 = MessagesStorage.getInstance(ShareAlert.access$7700(this.this$0)).getDatabase().queryFinalized(String.format(Locale.US, "SELECT data, status, name FROM users WHERE uid IN(%s)", new Object[]{TextUtils.join(",", r12)}), new Object[0]);     // Catch: Exception -> L176
            int r4 = 0;
        L37:
            if (r16.next() == false) goto L80;
            String r5 = r16.stringValue(r14);     // Catch: Exception -> L176
            String r102 = LocaleController.getInstance().getTranslitString(r5);     // Catch: Exception -> L176
            if (r5.equals(r102) == false) goto L41;
            r102 = null;
        L41:
            int r142 = r5.lastIndexOf(";;;");     // Catch: Exception -> L176
            if (r142 == r3) goto L44;
            String r143 = r5.substring(r142 + 3);     // Catch: Exception -> L176
        L45:
            int r32 = r6;
            int r17 = r32;
        L46:
            if (r32 >= r72) goto L77;
            String r62 = r8[r32];     // Catch: Exception -> L176
            if (r5.startsWith(r62) == true) goto L62;
            int r18 = r32;
            if (r5.contains(" " + r62) == true) goto L63;
            if (r102 != null) goto L53;
        L57:
            if (r143 != null) goto L59;
        L61:
            int r33 = r17;
        L64:
            if (r33 != 0) goto L65;
            r17 = r33;
            r32 = r18 + 1;     // Catch: Exception -> L176
            r4 = r4;
            goto L46
        L65:
            int r172 = r4;
            NativeByteBuffer r52 = r16.byteBufferValue(0);     // Catch: Exception -> L176
            if (r52 == null) goto L75;
            TLRPC.User r103 = TLRPC.User.TLdeserialize(r52, r52.readInt32(false), false);     // Catch: Exception -> L176
            r52.reuse();     // Catch: Exception -> L176
            DialogSearchResult r42 = (DialogSearchResult) r9.get(r103.id);     // Catch: Exception -> L176
            TLRPC.UserStatus r53 = r103.status;     // Catch: Exception -> L176
            if (r53 == null) goto L70;
            String[] r19 = r8;
            int r144 = 1;
            r53.expires = r16.intValue(1);     // Catch: Exception -> L176
        L71:
            if (r33 != r144) goto L73;
            r42.name = AndroidUtilities.generateSearchName(r103.first_name, r103.last_name, r62);     // Catch: Exception -> L176
        L74:
            r42.object = r103;     // Catch: Exception -> L176
            r42.dialog.id = r103.id;     // Catch: Exception -> L176
            r4 = r172 + 1;     // Catch: Exception -> L176
        L79:
            r8 = r19;
            r3 = -1;
            r6 = 0;
            r14 = 2;
            goto L37
        L73:
            r42.name = AndroidUtilities.generateSearchName("@" + UserObject.getPublicUsername(r103), null, "@" + r62);     // Catch: Exception -> L176
            goto L74
        L70:
            r19 = r8;
            r144 = 1;
        L75:
            r19 = r8;
            r4 = r172;
            goto L79
        L59:
            if (r143.startsWith(r62) == false) goto L61;
            r33 = 2;
            goto L64
        L53:
            if (r102.startsWith(r62) == true) goto L63;
            if (r102.contains(" " + r62) == false) goto L57;
        L63:
            r33 = 1;
            goto L64
        L62:
            r18 = r32;
            goto L63
        L77:
            r172 = r4;
            goto L75
        L44:
            r143 = null;
            goto L45
        L80:
            String[] r192 = r8;
            r16.dispose();     // Catch: Exception -> L176
            int r43 = r4;
        L83:
            if (r23.isEmpty() == true) goto L121;
            SQLiteCursor r110 = MessagesStorage.getInstance(ShareAlert.access$7800(this.this$0)).getDatabase().queryFinalized(String.format(Locale.US, "SELECT data, name FROM chats WHERE uid IN(%s)", new Object[]{TextUtils.join(",", r23)}), new Object[0]);     // Catch: Exception -> L176
        L86:
            if (r110.next() == false) goto L120;
            String r24 = r110.stringValue(1);     // Catch: Exception -> L176
            String r34 = LocaleController.getInstance().getTranslitString(r24);     // Catch: Exception -> L176
            if (r24.equals(r34) == false) goto L90;
            r34 = null;
        L90:
            int r54 = 0;
        L91:
            if (r54 >= r72) goto L86;
            String r63 = r192[r54];     // Catch: Exception -> L176
            if (r24.startsWith(r63) == true) goto L103;
            if (r24.contains(" " + r63) == true) goto L103;
            if (r34 == null) goto L102;
            if (r34.startsWith(r63) == true) goto L103;
            if (r34.contains(" " + r63) == true) goto L103;
        L102:
            r54 = r54 + 1;     // Catch: Exception -> L176
        L103:
            NativeByteBuffer r25 = r110.byteBufferValue(0);     // Catch: Exception -> L176
            if (r25 == null) goto L86;
            TLRPC.Chat r55 = TLRPC.Chat.TLdeserialize(r25, r25.readInt32(false), false);     // Catch: Exception -> L176
            r25.reuse();     // Catch: Exception -> L176
            if (r55 == null) goto L86;
            if (ChatObject.isNotInChat(r55) == true) goto L86;
            if (ChatObject.isChannel(r55) == false) goto L119;
            if (r55.creator == true) goto L119;
            TLRPC.TL_chatAdminRights r26 = r55.admin_rights;     // Catch: Exception -> L176
            if (r26 == null) goto L118;
            if (r26.post_messages == true) goto L119;
        L118:
            if (r55.megagroup == false) goto L86;
        L119:
            DialogSearchResult r27 = (DialogSearchResult) r9.get(-r55.id);     // Catch: Exception -> L176
            r27.name = AndroidUtilities.generateSearchName(r55.title, null, r63);     // Catch: Exception -> L176
            r27.object = r55;     // Catch: Exception -> L176
            r27.dialog.id = -r55.id;     // Catch: Exception -> L176
            r43 = r43 + 1;     // Catch: Exception -> L176
            goto L86
        L120:
            r110.dispose();     // Catch: Exception -> L176
        L121:
            ArrayList<Object> r111 = new ArrayList(r43);     // Catch: Exception -> L176
            int r28 = 0;
        L123:
            if (r28 >= r9.size()) goto L130;
            DialogSearchResult r35 = (DialogSearchResult) r9.valueAt(r28);     // Catch: Exception -> L176
            if (r35.object == null) goto L129;
            if (r35.name == null) goto L129;
            r111.add(r35);     // Catch: Exception -> L176
        L129:
            r28 = r28 + 1;     // Catch: Exception -> L176
            goto L123
        L130:
            SQLiteCursor r29 = MessagesStorage.getInstance(ShareAlert.access$7900(this.this$0)).getDatabase().queryFinalized("SELECT u.data, u.status, u.name, u.uid FROM users as u INNER JOIN contacts as c ON u.uid = c.uid", new Object[0]);     // Catch: Exception -> L176
        L132:
            if (r29.next() == false) goto L174;
            if (r9.indexOfKey(r29.longValue(3)) >= 0) goto L132;
            char r145 = 2;
            String r36 = r29.stringValue(2);     // Catch: Exception -> L176
            String r64 = LocaleController.getInstance().getTranslitString(r36);     // Catch: Exception -> L176
            if (r36.equals(r64) == false) goto L139;
            r64 = null;
        L139:
            int r44 = r36.lastIndexOf(";;;");     // Catch: Exception -> L176
            if (r44 == (-1)) goto L142;
            String r45 = r36.substring(r44 + 3);     // Catch: Exception -> L176
        L143:
            int r82 = 0;
            char r104 = 0;
        L144:
            if (r82 >= r72) goto L171;
            String r122 = r192[r82];     // Catch: Exception -> L176
            if (r36.startsWith(r122) == false) goto L148;
        L159:
            r104 = 1;
        L160:
            if (r104 != 0) goto L161;
            r82 = r82 + 1;     // Catch: Exception -> L176
            r145 = 2;
            goto L144
        L161:
            NativeByteBuffer r37 = r29.byteBufferValue(0);     // Catch: Exception -> L176
            if (r37 == null) goto L171;
            TLRPC.User r46 = TLRPC.User.TLdeserialize(r37, r37.readInt32(false), false);     // Catch: Exception -> L176
            r37.reuse();     // Catch: Exception -> L176
            DialogSearchResult r38 = new DialogSearchResult();     // Catch: Exception -> L176
            TLRPC.UserStatus r65 = r46.status;     // Catch: Exception -> L176
            if (r65 == null) goto L166;
            r65.expires = r29.intValue(1);     // Catch: Exception -> L176
        L166:
            String r162 = r15;
            r38.dialog.id = r46.id;     // Catch: Exception -> L176
            r38.object = r46;     // Catch: Exception -> L176
            if (r104 != 1) goto L169;
            r38.name = AndroidUtilities.generateSearchName(r46.first_name, r46.last_name, r122);     // Catch: Exception -> L176
        L170:
            r111.add(r38);     // Catch: Exception -> L176
        L173:
            r15 = r162;
            goto L132
        L169:
            r38.name = AndroidUtilities.generateSearchName("@" + UserObject.getPublicUsername(r46), null, "@" + r122);     // Catch: Exception -> L176
            goto L170
        L148:
            if (r36.contains(r15 + r122) == true) goto L159;
            if (r64 != null) goto L151;
        L155:
            if (r45 == null) goto L160;
            if (r45.startsWith(r122) == false) goto L160;
            r104 = r145;
            goto L160
        L151:
            if (r64.startsWith(r122) == true) goto L159;
            if (r64.contains(r15 + r122) == false) goto L155;
        L171:
            r162 = r15;
            goto L173
        L142:
            r45 = null;
            goto L143
        L174:
            r29.dispose();     // Catch: Exception -> L176
            Collections.sort(r111, new ShareAlert$ShareSearchAdapter$$ExternalSyntheticLambda4());     // Catch: Exception -> L176
            updateSearchResults(r111, r22);     // Catch: Exception -> L176
            return;
        L81:
            r192 = r8;
            r43 = 0;
            goto L83
        L15:
            r7 = 0;
            goto L16
        L10:
            if (r2.length() != 0) goto L12;
        L176:
            e = move-exception;
            FileLog.e(e);
        }

        private /* synthetic */ void lambda$updateSearchResults$2(int r5, ArrayList r6) {
            if (r5 == this.lastSearchId) goto L5;
            return;
        L5:
            getItemCount();
            this.internalDialogsIsSearching = false;
            this.lastLocalSearchId = r5;
            if (this.lastGlobalSearchId == r5) goto L9;
            this.searchAdapterHelper.clear();
        L9:
            if (ShareAlert.m12667$$Nest$fgetgridView(this.this$0).getAdapter() == ShareAlert.m12682$$Nest$fgetsearchAdapter(this.this$0)) goto L11;
            ShareAlert r52 = this.this$0;
            ShareAlert.m12716$$Nest$fputtopBeforeSwitch(r52, ShareAlert.m12721$$Nest$mgetCurrentTop(r52));
            ShareAlert.m12682$$Nest$fgetsearchAdapter(this.this$0).notifyDataSetChanged();
        L11:
            int r53 = 0;
        L13:
            if (r53 >= r6.size()) goto L22;
            TLObject r1 = ((DialogSearchResult) r6.get(r53)).object;
            if ((r1 instanceof TLRPC.User) == false) goto L18;
            MessagesController.getInstance(ShareAlert.access$7400(this.this$0)).putUser((TLRPC.User) r1, true);
        L20:
            r53 = r53 + 1;
            goto L13
        L18:
            if ((r1 instanceof TLRPC.Chat) == false) goto L20;
            MessagesController.getInstance(ShareAlert.access$7500(this.this$0)).putChat((TLRPC.Chat) r1, true);
            goto L20
        L22:
            if (this.searchResult.isEmpty() == false) goto L24;
        L26:
            boolean r54 = false;
        L28:
            if (this.searchResult.isEmpty() == false) goto L30;
            r6.isEmpty();
        L30:
            if (r54 == false) goto L32;
            ShareAlert r55 = this.this$0;
            ShareAlert.m12716$$Nest$fputtopBeforeSwitch(r55, ShareAlert.m12721$$Nest$mgetCurrentTop(r55));
        L32:
            this.searchResult = r6;
            this.searchAdapterHelper.mergeResults(r6, null);
            int r56 = this.lastItemCont;
            if (getItemCount() == 0) goto L35;
        L39:
            this.this$0.recyclerItemsEnterAnimator.showItemsAnimated(r56);
        L40:
            notifyDataSetChanged();
            ShareAlert.m12719$$Nest$mcheckCurrentList(this.this$0, true);
            return;
        L35:
            if (this.searchAdapterHelper.isSearchInProgress() == true) goto L39;
            if (this.internalDialogsIsSearching == true) goto L39;
            ShareAlert.m12683$$Nest$fgetsearchEmptyView(this.this$0).showProgress(false, true);
            goto L40
        L24:
            if (r6.isEmpty() == false) goto L26;
            r54 = true;
            goto L28
        }

        private void searchDialogsInternal(String r3, int r4) {
            MessagesStorage.getInstance(ShareAlert.access$5800(this.this$0)).getStorageQueue().postRunnable(new ShareAlert$ShareSearchAdapter$$ExternalSyntheticLambda3(this, r3, r4));
        }

        private void updateSearchResults(ArrayList<Object> r2, int r3) {
            AndroidUtilities.runOnUIThread(new ShareAlert$ShareSearchAdapter$$ExternalSyntheticLambda5(this, r3, r2));
        }

        public TLRPC.Dialog getItem(int r4) {
            int r0 = this.recentDialogsStartRow;
            if (r4 < r0) goto L19;
            if (r0 < 0) goto L19;
            int r42 = r4 - r0;
            if (r42 >= 0) goto L8;
        L18:
            return null;
        L8:
            if (r42 >= ShareAlert.m12679$$Nest$fgetrecentSearchObjects(this.this$0).size()) goto L18;
            TLObject r3 = ((DialogsSearchAdapter.RecentSearchObject) ShareAlert.m12679$$Nest$fgetrecentSearchObjects(this.this$0).get(r42)).object;
            TLRPC.TL_dialog r43 = new TLRPC.TL_dialog();
            if ((r3 instanceof TLRPC.User) == false) goto L15;
            r43.id = ((TLRPC.User) r3).id;
            return r43;
        L15:
            if ((r3 instanceof TLRPC.Chat) == false) goto L18;
            r43.id = -((TLRPC.Chat) r3).id;
            return r43;
        L19:
            int r44 = r4 - 1;
            if (r44 >= 0) goto L22;
            return null;
        L22:
            int r02 = this.searchResult.size();
            ArrayList<Object> r2 = this.searchResult;
            if (r44 < r02) goto L25;
            int r45 = r44 - r2.size();
            ArrayList<TLObject> r32 = this.searchAdapterHelper.getLocalServerSearch();
            if (r45 >= r32.size()) goto L36;
            TLObject r33 = r32.get(r45);
            TLRPC.TL_dialog r46 = new TLRPC.TL_dialog();
            if ((r33 instanceof TLRPC.User) == false) goto L33;
            r46.id = ((TLRPC.User) r33).id;
            return r46;
        L33:
            if ((r33 instanceof TLRPC.Chat) == false) goto L36;
            r46.id = -((TLRPC.Chat) r33).id;
            return r46;
        L36:
            return null;
        L25:
            return ((DialogSearchResult) r2.get(r44)).dialog;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            this.itemsCount = 0;
            this.hintsCell = -1;
            this.resentTitleCell = -1;
            this.recentDialogsStartRow = -1;
            this.searchResultsStartRow = -1;
            this.lastFilledItem = -1;
            boolean r2 = TextUtils.isEmpty(this.lastSearchText);
            int r3 = this.itemsCount;
            if (r2 == false) goto L9;
            int r0 = r3 + 1;
            this.firstEmptyViewCell = r3;
            this.itemsCount = r3 + 2;
            this.hintsCell = r0;
            if (ShareAlert.m12679$$Nest$fgetrecentSearchObjects(this.this$0).size() <= 0) goto L7;
            int r02 = this.itemsCount;
            int r1 = r02 + 1;
            this.itemsCount = r1;
            this.resentTitleCell = r02;
            this.recentDialogsStartRow = r1;
            this.itemsCount = r1 + ShareAlert.m12679$$Nest$fgetrecentSearchObjects(this.this$0).size();
        L7:
            int r03 = this.itemsCount;
            int r12 = r03 + 1;
            this.itemsCount = r12;
            this.lastFilledItem = r03;
            this.lastItemCont = r12;
            return r12;
        L9:
            int r22 = r3 + 1;
            this.itemsCount = r22;
            this.firstEmptyViewCell = r3;
            this.searchResultsStartRow = r22;
            int r23 = r22 + (this.searchResult.size() + this.searchAdapterHelper.getLocalServerSearch().size());
            this.itemsCount = r23;
            if (r23 != 1) goto L13;
            this.firstEmptyViewCell = -1;
            this.itemsCount = 0;
            this.lastItemCont = 0;
            return 0;
        L13:
            int r04 = r23 + 1;
            this.itemsCount = r04;
            this.lastFilledItem = r23;
            this.lastItemCont = r04;
            return r04;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != this.lastFilledItem) goto L7;
            return 4;
        L7:
            if (r2 != this.firstEmptyViewCell) goto L11;
            return 1;
        L11:
            if (r2 != this.hintsCell) goto L15;
            return 2;
        L15:
            if (r2 != this.resentTitleCell) goto L19;
            return 3;
        L19:
            if (TextUtils.isEmpty(this.lastSearchText) == false) goto L22;
            return 0;
        L22:
            return 5;
        }

        public int getSpanSize(int r2, int r3) {
            if (r3 != this.hintsCell) goto L5;
        L16:
            return r2;
        L5:
            if (r3 == this.resentTitleCell) goto L16;
            if (r3 == this.firstEmptyViewCell) goto L16;
            if (r3 == this.lastFilledItem) goto L16;
            if (getItemViewType(r3) == 0) goto L16;
            return 1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r2) {
            if (r2.getItemViewType() != 1) goto L5;
            return false;
        L5:
            if (r2.getItemViewType() == 4) goto L10;
            return true;
        L10:
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v23, types: [java.lang.CharSequence] */
        /* JADX WARN: Type inference failed for: r7v1, types: [android.text.SpannableStringBuilder] */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r26, int r27) {
            if (r26.getItemViewType() != 0) goto L5;
        L11:
            TLObject r6 = null;
            String r62 = null;
            r62 = null;
            boolean r8 = false;
            TLRPC.TL_encryptedChat r12 = null;
            if (TextUtils.isEmpty(this.lastSearchText) == false) goto L50;
            int r3 = this.recentDialogsStartRow;
            long r10 = 0;
            if (r3 < 0) goto L35;
            if (r27 < r3) goto L35;
            TLObject r32 = ((DialogsSearchAdapter.RecentSearchObject) ShareAlert.m12679$$Nest$fgetrecentSearchObjects(this.this$0).get(r27 - r3)).object;
            if ((r32 instanceof TLRPC.User) == false) goto L20;
            TLRPC.User r63 = (TLRPC.User) r32;
            r10 = r63.id;
            r62 = ContactsController.formatName(r63.first_name, r63.last_name);
        L27:
            String r13 = this.searchAdapterHelper.getLastFoundUsername();
            if (TextUtils.isEmpty(r13) == true) goto L34;
            if (r62 == null) goto L34;
            int r14 = AndroidUtilities.indexOfIgnoreCase(r62.toString(), r13);
            if (r14 == (-1)) goto L34;
            SpannableStringBuilder r7 = new SpannableStringBuilder(r62);
            r7.setSpan(new ForegroundColorSpanThemable(Theme.key_windowBackgroundWhiteBlueText4, ShareAlert.access$7100(this.this$0)), r14, r13.length() + r14, 33);
            TLObject r19 = r32;
            CharSequence r21 = r7;
        L33:
            TLRPC.TL_encryptedChat r20 = r12;
            View r33 = r26.itemView;
            if ((r33 instanceof ProfileSearchCell) == false) goto L43;
            ((ProfileSearchCell) r33).setData(r19, r20, r21, null, false, false);
            ProfileSearchCell r1 = (ProfileSearchCell) r26.itemView;
            if (r27 >= (getItemCount() - 2)) goto L41;
            r8 = true;
        L41:
            r1.useSeparator = r8;
            return;
        L43:
            CharSequence r64 = r21;
            if ((r33 instanceof ShareDialogCell) == false) goto L81;
            ShareDialogCell r34 = (ShareDialogCell) r33;
            if (this.this$0.selectedDialogs.indexOfKey(r10) < 0) goto L48;
            r8 = true;
        L48:
            r34.setDialog(r10, r8, r64);
            return;
        L81:
            return;
        L34:
            r19 = r32;
            r21 = r62;
            goto L33
        L20:
            if ((r32 instanceof TLRPC.Chat) == false) goto L23;
            TLRPC.Chat r65 = (TLRPC.Chat) r32;
            r10 = -r65.id;
            r62 = r65.title;
            goto L27
        L23:
            if ((r32 instanceof TLRPC.TL_encryptedChat) == false) goto L27;
            r12 = (TLRPC.TL_encryptedChat) r32;
            TLRPC.User r132 = MessagesController.getInstance(ShareAlert.access$7000(this.this$0)).getUser(Long.valueOf(r12.user_id));
            if (r132 == null) goto L27;
            r10 = r132.id;
            r62 = ContactsController.formatName(r132.first_name, r132.last_name);
        L35:
            r19 = null;
            r21 = null;
            goto L33
        L50:
            int r2 = r27 - 1;
            int r35 = this.searchResult.size();
            ArrayList<Object> r4 = this.searchResult;
            if (r2 >= r35) goto L54;
            DialogSearchResult r36 = (DialogSearchResult) r4.get(r2);
            long r42 = r36.dialog.id;
            String r133 = r36.name;
        L53:
            TLObject r11 = r6;
            View r37 = r26.itemView;
            if ((r37 instanceof ProfileSearchCell) == false) goto L74;
            ((ProfileSearchCell) r37).setData(r11, null, r133, null, false, false);
            ProfileSearchCell r15 = (ProfileSearchCell) r26.itemView;
            if (r2 >= (getItemCount() - 2)) goto L71;
            r8 = true;
        L71:
            r15.useSeparator = r8;
            return;
        L74:
            if ((r37 instanceof ShareDialogCell) == false) goto L82;
            ShareDialogCell r38 = (ShareDialogCell) r37;
            if (this.this$0.selectedDialogs.indexOfKey(r42) < 0) goto L78;
            r8 = true;
        L78:
            r38.setDialog(r42, r8, r133);
            return;
        L82:
            return;
        L54:
            r2 = r2 - r4.size();
            r6 = this.searchAdapterHelper.getLocalServerSearch().get(r2);
            if ((r6 instanceof TLRPC.User) == false) goto L57;
            TLRPC.User r39 = (TLRPC.User) r6;
            long r102 = r39.id;
            String r310 = ContactsController.formatName(r39.first_name, r39.last_name);
        L58:
            String r43 = this.searchAdapterHelper.getLastFoundUsername();
            if (TextUtils.isEmpty(r43) == true) goto L65;
            if (r310 == null) goto L65;
            int r134 = AndroidUtilities.indexOfIgnoreCase(r310.toString(), r43);
            if (r134 == (-1)) goto L65;
            ?? r72 = new SpannableStringBuilder(r310);
            r72.setSpan(new ForegroundColorSpanThemable(Theme.key_windowBackgroundWhiteBlueText4, ShareAlert.access$7200(this.this$0)), r134, r43.length() + r134, 33);
            r133 = r72;
        L64:
            r42 = r102;
        L65:
            r133 = r310;
            goto L64
        L57:
            TLRPC.Chat r311 = (TLRPC.Chat) r6;
            r102 = -r311.id;
            r310 = r311.title;
            goto L58
        L5:
            if (r26.getItemViewType() == 5) goto L11;
            if (r26.getItemViewType() != 2) goto L80;
            ((RecyclerListView) r26.itemView).getAdapter().notifyDataSetChanged();
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r10, int r11) {
            if (r11 != 0) goto L5;
            View r112 = new ProfileSearchCell(this.context, ShareAlert.access$6000(this.this$0)).useCustomPaints().showPremiumBlock(true);
        L25:
            return new RecyclerListView.Holder(r112);
        L5:
            if (r11 != 2) goto L7;
            AnonymousClass3 r113 = new AnonymousClass3(this, this.context, ShareAlert.access$6100(this.this$0));
            this.categoryListView = r113;
            r113.setItemAnimator(null);
            r113.setLayoutAnimation(null);
            AnonymousClass4 r102 = new AnonymousClass4(this, this.context);
            r102.setOrientation(0);
            r113.setLayoutManager(r102);
            AnonymousClass5 r2 = new AnonymousClass5(this, this.context, ShareAlert.access$6200(this.this$0), true, true, ShareAlert.access$6300(this.this$0));
            this.categoryAdapter = r2;
            r113.setAdapter(r2);
            r113.setOnItemClickListener(new ShareAlert$ShareSearchAdapter$$ExternalSyntheticLambda0(this));
            r112 = r113;
            goto L25
        L7:
            if (r11 != 3) goto L9;
            GraySectionCell r114 = new GraySectionCell(this.context, ShareAlert.access$6800(this.this$0));
            r114.setTextColor(Theme.key_graySectionText);
            r114.setBackgroundColor(ShareAlert.access$6900(this.this$0, Theme.key_graySection));
            r114.setText(LocaleController.getString(R.string.Recent));
            r112 = r114;
            goto L25
        L9:
            if (r11 != 4) goto L11;
            r112 = new AnonymousClass6(this, this.context);
            goto L25
        L11:
            if (r11 == 5) goto L19;
            View r115 = new View(this.context);
            if (ShareAlert.m12661$$Nest$fgetdarkTheme(this.this$0) == true) goto L15;
        L17:
            float r9 = 56.0f;
        L18:
            r115.setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(r9)));
            r112 = r115;
            goto L25
        L15:
            if (ShareAlert.m12673$$Nest$fgetlinkToCopy(this.this$0)[1] == null) goto L17;
            r9 = 109.0f;
            goto L18
        L19:
            View r116 = new ShareDialogCell(this.context, 0, ShareAlert.access$5900(this.this$0));
            r116.setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(100.0f)));
            r112 = r116;
            goto L25
        }

        public void searchDialogs(String r15) {
            if (r15 != null) goto L4;
        L6:
            this.lastSearchText = r15;
            if (this.searchRunnable == null) goto L9;
            Utilities.searchQueue.cancelRunnable(this.searchRunnable);
            this.searchRunnable = null;
        L9:
            Runnable r0 = this.searchRunnable2;
            if (r0 == null) goto L12;
            AndroidUtilities.cancelRunOnUIThread(r0);
            this.searchRunnable2 = null;
        L12:
            this.searchResult.clear();
            this.searchAdapterHelper.mergeResults(null);
            this.searchAdapterHelper.queryServerSearch(null, true, true, true, true, false, 0, false, 0, 0);
            notifyDataSetChanged();
            ShareAlert.m12719$$Nest$mcheckCurrentList(this.this$0, true);
            if (TextUtils.isEmpty(r15) == false) goto L15;
            ShareAlert r152 = this.this$0;
            ShareAlert.m12716$$Nest$fputtopBeforeSwitch(r152, ShareAlert.m12721$$Nest$mgetCurrentTop(r152));
            this.lastSearchId = -1;
            this.internalDialogsIsSearching = false;
        L16:
            ShareAlert.m12719$$Nest$mcheckCurrentList(this.this$0, false);
            return;
        L15:
            this.internalDialogsIsSearching = true;
            int r02 = this.lastSearchId + 1;
            this.lastSearchId = r02;
            ShareAlert.m12683$$Nest$fgetsearchEmptyView(this.this$0).showProgress(true, true);
            DispatchQueue r1 = Utilities.searchQueue;
            ShareAlert$ShareSearchAdapter$$ExternalSyntheticLambda1 r3 = new ShareAlert$ShareSearchAdapter$$ExternalSyntheticLambda1(this, r15, r02);
            this.searchRunnable = r3;
            r1.postRunnable(r3, 300);
            goto L16
        L4:
            if (r15.equals(this.lastSearchText) == false) goto L6;
        }
    }

    public class ShareTopicsAdapter extends RecyclerListView.SelectionAdapter {
        private Context context;
        private boolean isBotForum;
        private boolean isBotForumWithManageTopics;
        final /* synthetic */ ShareAlert this$0;
        private List<TLRPC.TL_forumTopic> topics;

        /* renamed from: -$$Nest$fgetisBotForum, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m12748$$Nest$fgetisBotForum(ShareTopicsAdapter r0) {
            return r0.isBotForum;
        }

        /* renamed from: -$$Nest$fgettopics, reason: not valid java name */
        public static /* bridge */ /* synthetic */ List m12749$$Nest$fgettopics(ShareTopicsAdapter r0) {
            return r0.topics;
        }

        /* renamed from: -$$Nest$fputisBotForum, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12750$$Nest$fputisBotForum(ShareTopicsAdapter r0, boolean r1) {
            r0.isBotForum = r1;
        }

        /* renamed from: -$$Nest$fputisBotForumWithManageTopics, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12751$$Nest$fputisBotForumWithManageTopics(ShareTopicsAdapter r0, boolean r1) {
            r0.isBotForumWithManageTopics = r1;
        }

        /* renamed from: -$$Nest$fputtopics, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12752$$Nest$fputtopics(ShareTopicsAdapter r0, List r1) {
            r0.topics = r1;
        }

        public ShareTopicsAdapter(ShareAlert r1, Context r2) {
            this.this$0 = r1;
            this.context = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            List<TLRPC.TL_forumTopic> r0 = this.topics;
            if (r0 == null) goto L5;
            int r02 = r0.size() + 1;
        L7:
            return r02 + (this.isBotForum ? 1 : 0);
        L5:
            r02 = 0;
            goto L7
        }

        public TLRPC.TL_forumTopic getItemTopic(int r3) {
            int r0 = r3 - 1;
            if (this.isBotForum == false) goto L5;
            r0 = r3 - 2;
        L5:
            List<TLRPC.TL_forumTopic> r32 = this.topics;
            if (r32 == null) goto L13;
            if (r0 >= 0) goto L9;
            return null;
        L9:
            if (r0 < r32.size()) goto L12;
            return null;
        L12:
            return this.topics.get(r0);
        L13:
            return null;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            if (r1 != 0) goto L5;
            return 1;
        L5:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() == 1) goto L5;
            return true;
        L5:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r5, int r6) {
            if (r5.getItemViewType() != 0) goto L20;
            ShareTopicCell r52 = (ShareTopicCell) r5.itemView;
            boolean r0 = true;
            if (r6 != 1) goto L11;
            if (this.isBotForum == false) goto L11;
            r52.setAsNewBotForumTopic(this.isBotForumWithManageTopics);
            return;
        L11:
            if (this.topics == null) goto L21;
            TLRPC.TL_forumTopic r62 = getItemTopic(r6);
            TLRPC.Dialog r1 = ShareAlert.m12687$$Nest$fgetselectedTopicDialog(this.this$0);
            if (r62 != null) goto L15;
        L17:
            r0 = false;
        L18:
            r52.setTopic(r1, r62, r0, null);
            return;
        L15:
            if (this.this$0.selectedDialogs.indexOfKey(r62.id) < 0) goto L17;
        L21:
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            if (r3 != 0) goto L5;
        L7:
            View r32 = new ShareTopicCell(this.context, ShareAlert.access$5700(this.this$0));
            r32.setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(100.0f)));
        L9:
            return new RecyclerListView.Holder(r32);
        L5:
            if (r3 == 2) goto L7;
            r32 = new View(this.context);
            r32.setLayoutParams(new RecyclerView.LayoutParams(-1, ActionBar.getCurrentActionBarHeight()));
            goto L9
        }
    }

    public class SwitchView extends FrameLayout {
        private AnimatorSet animator;
        private int currentTab;
        private int lastColor;
        private SimpleTextView leftTab;
        private LinearGradient linearGradient;
        private Paint paint;
        private RectF rect;
        private SimpleTextView rightTab;
        private View searchBackground;
        private View slidingView;
        final /* synthetic */ ShareAlert this$0;

        /* renamed from: org.telegram.ui.Components.ShareAlert$SwitchView$1, reason: invalid class name */
        public class AnonymousClass1 extends View {
            final /* synthetic */ SwitchView this$1;
            final /* synthetic */ ShareAlert val$this$0;

            public AnonymousClass1(SwitchView r1, Context r2, ShareAlert r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // android.view.View
            public void onDraw(Canvas r12) {
                super.onDraw(r12);
                int r0 = AndroidUtilities.getOffsetColor(-9057429, -10513163, getTranslationX() / getMeasuredWidth(), 1.0f);
                int r1 = AndroidUtilities.getOffsetColor(-11554882, -4629871, getTranslationX() / getMeasuredWidth(), 1.0f);
                if (r0 == SwitchView.m12754$$Nest$fgetlastColor(this.this$1)) goto L5;
                SwitchView.m12759$$Nest$fputlinearGradient(this.this$1, new LinearGradient(0.0f, 0.0f, getMeasuredWidth(), 0.0f, new int[]{r0, r1}, null, Shader.TileMode.CLAMP));
                SwitchView.m12756$$Nest$fgetpaint(this.this$1).setShader(SwitchView.m12755$$Nest$fgetlinearGradient(this.this$1));
            L5:
                SwitchView.m12757$$Nest$fgetrect(this.this$1).set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
                r12.drawRoundRect(SwitchView.m12757$$Nest$fgetrect(this.this$1), AndroidUtilities.dp(18.0f), AndroidUtilities.dp(18.0f), SwitchView.m12756$$Nest$fgetpaint(this.this$1));
            }

            @Override // android.view.View
            public void setTranslationX(float r1) {
                super.setTranslationX(r1);
                invalidate();
            }
        }

        /* renamed from: org.telegram.ui.Components.ShareAlert$SwitchView$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ SwitchView this$1;

            public AnonymousClass2(SwitchView r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                SwitchView.m12758$$Nest$fputanimator(this.this$1, null);
            }
        }

        public static /* synthetic */ void $r8$lambda$pMuksQsNPKMUrb06zYIJMhQyYo4(SwitchView r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$vxNvKCrXansDEkwJKYIzH6j2TEk(SwitchView r0, View r1) {
            r0.lambda$new$1(r1);
        }

        /* renamed from: -$$Nest$fgetcurrentTab, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m12753$$Nest$fgetcurrentTab(SwitchView r0) {
            return r0.currentTab;
        }

        /* renamed from: -$$Nest$fgetlastColor, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m12754$$Nest$fgetlastColor(SwitchView r0) {
            return r0.lastColor;
        }

        /* renamed from: -$$Nest$fgetlinearGradient, reason: not valid java name */
        public static /* bridge */ /* synthetic */ LinearGradient m12755$$Nest$fgetlinearGradient(SwitchView r0) {
            return r0.linearGradient;
        }

        /* renamed from: -$$Nest$fgetpaint, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Paint m12756$$Nest$fgetpaint(SwitchView r0) {
            return r0.paint;
        }

        /* renamed from: -$$Nest$fgetrect, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RectF m12757$$Nest$fgetrect(SwitchView r0) {
            return r0.rect;
        }

        /* renamed from: -$$Nest$fputanimator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12758$$Nest$fputanimator(SwitchView r0, AnimatorSet r1) {
            r0.animator = r1;
        }

        /* renamed from: -$$Nest$fputlinearGradient, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m12759$$Nest$fputlinearGradient(SwitchView r0, LinearGradient r1) {
            r0.linearGradient = r1;
        }

        public SwitchView(ShareAlert r12, Context r13) {
            this.this$0 = r12;
            super(r13);
            this.paint = new Paint(1);
            this.rect = new RectF();
            View r0 = new View(r13);
            this.searchBackground = r0;
            r0.setBackgroundDrawable(Theme.createRoundRectDrawable(AndroidUtilities.dp(18.0f), ShareAlert.access$000(r12, Theme.key_dialogSearchBackground)));
            addView(this.searchBackground, LayoutHelper.createFrame(-1, 36.0f, 51, 14.0f, 0.0f, 14.0f, 0.0f));
            AnonymousClass1 r02 = new AnonymousClass1(this, r13, r12);
            this.slidingView = r02;
            addView(r02, LayoutHelper.createFrame(-1, 36.0f, 51, 14.0f, 0.0f, 14.0f, 0.0f));
            SimpleTextView r03 = new SimpleTextView(r13);
            this.leftTab = r03;
            int r1 = Theme.key_voipgroup_nameText;
            r03.setTextColor(ShareAlert.access$100(r12, r1));
            this.leftTab.setTextSize(13);
            this.leftTab.setLeftDrawable(R.drawable.msg_tabs_mic1);
            this.leftTab.setText(LocaleController.getString(R.string.VoipGroupInviteCanSpeak));
            this.leftTab.setGravity(17);
            addView(this.leftTab, LayoutHelper.createFrame(-1, -1.0f, 51, 14.0f, 0.0f, 0.0f, 0.0f));
            this.leftTab.setOnClickListener(new ShareAlert$SwitchView$$ExternalSyntheticLambda0(this));
            SimpleTextView r04 = new SimpleTextView(r13);
            this.rightTab = r04;
            r04.setTextColor(ShareAlert.access$200(r12, r1));
            this.rightTab.setTextSize(13);
            this.rightTab.setLeftDrawable(R.drawable.msg_tabs_mic2);
            this.rightTab.setText(LocaleController.getString(R.string.VoipGroupInviteListenOnly));
            this.rightTab.setGravity(17);
            addView(this.rightTab, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 0.0f, 14.0f, 0.0f));
            this.rightTab.setOnClickListener(new ShareAlert$SwitchView$$ExternalSyntheticLambda1(this));
        }

        private /* synthetic */ void lambda$new$0(View r1) {
            switchToTab(0);
        }

        private /* synthetic */ void lambda$new$1(View r1) {
            switchToTab(1);
        }

        private void switchToTab(int r6) {
            if (this.currentTab != r6) goto L5;
            return;
        L5:
            this.currentTab = r6;
            AnimatorSet r62 = this.animator;
            if (r62 == null) goto L8;
            r62.cancel();
        L8:
            AnimatorSet r63 = new AnimatorSet();
            this.animator = r63;
            View r0 = this.slidingView;
            if (this.currentTab != 0) goto L11;
            float r1 = 0.0f;
        L12:
            r63.playTogether(new Animator[]{ObjectAnimator.ofFloat(r0, View.TRANSLATION_X, new float[]{r1})});
            this.animator.setDuration(180);
            this.animator.setInterpolator(CubicBezierInterpolator.EASE_OUT);
            this.animator.addListener(new AnonymousClass2(this));
            this.animator.start();
            onTabSwitch(this.currentTab);
            return;
        L11:
            r1 = r0.getMeasuredWidth();
            goto L12
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            int r0 = (View.MeasureSpec.getSize(r4) - AndroidUtilities.dp(28.0f)) / 2;
            ((FrameLayout.LayoutParams) this.leftTab.getLayoutParams()).width = r0;
            FrameLayout.LayoutParams r1 = (FrameLayout.LayoutParams) this.rightTab.getLayoutParams();
            r1.width = r0;
            r1.leftMargin = AndroidUtilities.dp(14.0f) + r0;
            FrameLayout.LayoutParams r12 = (FrameLayout.LayoutParams) this.slidingView.getLayoutParams();
            r12.width = r0;
            AnimatorSet r02 = this.animator;
            if (r02 == null) goto L5;
            r02.cancel();
        L5:
            View r03 = this.slidingView;
            if (this.currentTab != 0) goto L8;
            float r13 = 0.0f;
        L9:
            r03.setTranslationX(r13);
            super.onMeasure(r4, r5);
            return;
        L8:
            r13 = r12.width;
            goto L9
        }

        public abstract void onTabSwitch(int r1);
    }

    /* renamed from: $r8$lambda$-R3osssVusRZcx1n3GlP10yQA-g, reason: not valid java name */
    public static /* synthetic */ void m12640$r8$lambda$R3osssVusRZcx1n3GlP10yQAg(ShareAlert r0) {
        r0.lambda$showPremiumBlockedToast$20();
    }

    /* renamed from: $r8$lambda$0FKl3ou399TBylU7OM5qnRj_P-0, reason: not valid java name */
    public static /* synthetic */ void m12641$r8$lambda$0FKl3ou399TBylU7OM5qnRj_P0(ShareAlert r0, View r1, int r2) {
        r0.lambda$new$9(r1, r2);
    }

    public static /* synthetic */ Integer $r8$lambda$1PcynxTGhKcpqzn9H9A8Iu69dXA(Integer r0) {
        return 0;
    }

    public static /* synthetic */ void $r8$lambda$2WnK0lM2aLfPKhvcqiElLqi_Frc(ShareAlert r0, View r1) {
        r0.lambda$onSendLongClick$28(r1);
    }

    /* renamed from: $r8$lambda$3Lsl75IaP-TS0DQeadjI3v6vohw, reason: not valid java name */
    public static /* synthetic */ void m12642$r8$lambda$3Lsl75IaPTS0DQeadjI3v6vohw(ShareAlert r0, View r1) {
        r0.lambda$onSendLongClick$31(r1);
    }

    /* renamed from: $r8$lambda$8sKW0oonFKzVckKz6pjYENNob-4, reason: not valid java name */
    public static /* synthetic */ void m12643$r8$lambda$8sKW0oonFKzVckKz6pjYENNob4(ShareAlert r0, MessageObject r1, View r2) {
        r0.lambda$new$12(r1, r2);
    }

    public static /* synthetic */ WindowInsetsCompat $r8$lambda$BaN7ROL1b8ooasEWJ4SDjxqtQqI(ShareAlert r0, View r1, WindowInsetsCompat r2) {
        return r0.onApplyWindowInsets(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$BoHI5SNXimi7_VUaV4p8JhzEllY(ShareAlert r0, TextView r1, int r2, KeyEvent r3) {
        return r0.lambda$new$2(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$CGfx0AGdjku7wFU3rF4-5QSno4c, reason: not valid java name */
    public static /* synthetic */ Bulletin m12644$r8$lambda$CGfx0AGdjku7wFU3rF45QSno4c(boolean r0, BulletinFactory r1) {
        return r1.createCopyLinkBulletin(r0);
    }

    public static /* synthetic */ void $r8$lambda$DbEH8jHjKr1B3KG1CT9NEs7Mbtk(ShareAlert r0, View r1) {
        r0.lambda$new$16(r1);
    }

    /* renamed from: $r8$lambda$EcvaulHqF1Ot_0Fdb-bxd5cRbIs, reason: not valid java name */
    public static /* synthetic */ void m12645$r8$lambda$EcvaulHqF1Ot_0Fdbbxd5cRbIs(ShareAlert r0, CharSequence[] r1, ArrayList r2, boolean r3, int r4, int r5, int r6, HashMap r7) {
        r0.lambda$sendInternal$33(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$GOMIoYAFqLREbRXjM_og1HpvK7o(ShareAlert r0, View r1) {
        r0.lambda$new$10(r1);
    }

    /* renamed from: $r8$lambda$Ggozi3Une-GGL--FydaiafWRpgY, reason: not valid java name */
    public static /* synthetic */ void m12646$r8$lambda$Ggozi3UneGGLFydaiafWRpgY(ShareAlert r0, DynamicAnimation r1, boolean r2, float r3, float r4) {
        r0.lambda$collapseTopics$23(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$O9AnHxh4UHGQdf85w4AGxxpYDao(ShareAlert r0, MessagePreviewView.ToggleButton r1, MessagePreviewView.ToggleButton r2, View r3) {
        r0.lambda$onSendLongClick$26(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$TE1qSV15g8GKfb8CtDD6Saq4hEs(ShareAlert r0, TLObject r1, Context r2) {
        r0.lambda$new$0(r1, r2);
    }

    /* renamed from: $r8$lambda$WlEDRalune-1YsBIgtpe7uUY1f4, reason: not valid java name */
    public static /* synthetic */ void m12647$r8$lambda$WlEDRalune1YsBIgtpe7uUY1f4(ShareAlert r0, View r1, int r2) {
        r0.lambda$new$5(r1, r2);
    }

    /* renamed from: $r8$lambda$ZisXH2MphOIUgyFxLnQF-3tcrzY, reason: not valid java name */
    public static /* synthetic */ void m12648$r8$lambda$ZisXH2MphOIUgyFxLnQF3tcrzY(ShareAlert r0, View r1, int[] r2, DynamicAnimation r3, float r4, float r5) {
        r0.lambda$collapseTopics$22(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$ZzSwuF1xQPQ9SIOZ0p4w_GvqLdc(ShareAlert r0, View r1) {
        r0.lambda$onSendLongClick$30(r1);
    }

    public static /* synthetic */ void $r8$lambda$a9AUzfJ1iGi9AjesNwq3XuSe65Q(ShareAlert r0, View r1) {
        r0.lambda$new$11(r1);
    }

    /* renamed from: $r8$lambda$afmCE9Pb9L9r-x1QIMJCy2kiVaE, reason: not valid java name */
    public static /* synthetic */ void m12649$r8$lambda$afmCE9Pb9L9rx1QIMJCy2kiVaE(ShareAlert r0, Context r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$new$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$cYrHFUawrwPZAiMrLTCHATfBRWk(ShareAlert r0, AtomicReference r1, NotificationCenter.NotificationCenterDelegate r2, TLRPC.Dialog r3) {
        r0.lambda$selectDialog$21(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$cj53NDg0skw8HE_3YgyxukRofT4(ShareAlert r0, ActionBarPopupWindow r1) {
        r0.lambda$onSendLongClick$32(r1);
    }

    public static /* synthetic */ void $r8$lambda$dgRvlUHFv1P8WlXWlLcWEJrLJNg(ShareAlert r0, View r1) {
        r0.lambda$new$18(r1);
    }

    public static /* synthetic */ Integer $r8$lambda$ecX7uqh2NAahRSKkgn7yzUDSRcY(Integer r0) {
        return 0;
    }

    public static /* synthetic */ boolean $r8$lambda$fa9TxitH_bTtIw4ogWXMyiVk3as(View r0, MotionEvent r1) {
        return true;
    }

    /* renamed from: $r8$lambda$in-tXLdsuvjpDYLcPbeUivaVau8, reason: not valid java name */
    public static /* synthetic */ void m12650$r8$lambda$intXLdsuvjpDYLcPbeUivaVau8(ShareAlert r0, View r1, int r2) {
        r0.lambda$new$7(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$kNeyjrWvZmR0p8PZco1tiL2u_Zo(ShareAlert r0, MessagePreviewView.ToggleButton r1, MessagePreviewView.ToggleButton r2, View r3) {
        r0.lambda$onSendLongClick$25(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$kRHPZFpOvYVFQ8Z_KS8LW6nHJEU(ShareAlert r0, KeyEvent r1) {
        r0.lambda$onSendLongClick$24(r1);
    }

    /* renamed from: $r8$lambda$pkcVO4IZuQED7bWMpZ0D-eYQDRQ, reason: not valid java name */
    public static /* synthetic */ void m12651$r8$lambda$pkcVO4IZuQED7bWMpZ0DeYQDRQ(ShareAlert r0, KeyEvent r1) {
        r0.lambda$onSendLongClick$27(r1);
    }

    public static /* synthetic */ Integer $r8$lambda$qkwDv7PSB2Nua20LSuVOwcrHWOU(Integer r0) {
        return 0;
    }

    public static /* synthetic */ void $r8$lambda$rX7E4czmcWesRO7wQ1tMfoGge3w(ShareAlert r0, boolean r1, int r2, int r3) {
        r0.lambda$onSendLongClick$29(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$sex-qhelEX7tZWAaX_rMBCQol_w, reason: not valid java name */
    public static /* synthetic */ void m12652$r8$lambda$sexqhelEX7tZWAaX_rMBCQol_w(ShareAlert r0) {
        r0.lambda$new$3();
    }

    public static /* synthetic */ boolean $r8$lambda$svpZTujGYxbwjioQLPdPQE8GdIA(ShareAlert r0, View r1) {
        return r0.lambda$new$17(r1);
    }

    public static /* synthetic */ void $r8$lambda$zIySNlfjdaZnyQ7LG4nDjEIRPKc() {
        BaseFragment r0 = LaunchActivity.getLastFragment();
        if (r0 == null) goto L6;
        BaseFragment.BottomSheetParams r1 = new BaseFragment.BottomSheetParams();
        r1.transitionFromLeft = true;
        r1.allowNestedScroll = false;
        r0.showAsSheet(new PremiumPreviewFragment("noncontacts"), r1);
        return;
    }

    /* renamed from: -$$Nest$fgetanimatorSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m12653$$Nest$fgetanimatorSet(ShareAlert r0) {
        return r0.animatorSet;
    }

    /* renamed from: -$$Nest$fgetbottomFadeView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m12654$$Nest$fgetbottomFadeView(ShareAlert r0) {
        return r0.bottomFadeView;
    }

    /* renamed from: -$$Nest$fgetbulletinContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m12655$$Nest$fgetbulletinContainer(ShareAlert r0) {
        return r0.bulletinContainer;
    }

    /* renamed from: -$$Nest$fgetcaptionContainerBg, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundDrawable m12656$$Nest$fgetcaptionContainerBg(ShareAlert r0) {
        return r0.captionContainerBg;
    }

    /* renamed from: -$$Nest$fgetcaptionEditTextTopOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12657$$Nest$fgetcaptionEditTextTopOffset(ShareAlert r0) {
        return r0.captionEditTextTopOffset;
    }

    /* renamed from: -$$Nest$fgetchatActivityEnterViewAnimateFromTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12658$$Nest$fgetchatActivityEnterViewAnimateFromTop(ShareAlert r0) {
        return r0.chatActivityEnterViewAnimateFromTop;
    }

    /* renamed from: -$$Nest$fgetcommentTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextEmoji m12659$$Nest$fgetcommentTextView(ShareAlert r0) {
        return r0.commentTextView;
    }

    /* renamed from: -$$Nest$fgetcurrentPanTranslationY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m12660$$Nest$fgetcurrentPanTranslationY(ShareAlert r0) {
        return r0.currentPanTranslationY;
    }

    /* renamed from: -$$Nest$fgetdarkTheme, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12661$$Nest$fgetdarkTheme(ShareAlert r0) {
        return r0.darkTheme;
    }

    /* renamed from: -$$Nest$fgetemojiViewChildBg, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundDrawable m12662$$Nest$fgetemojiViewChildBg(ShareAlert r0) {
        return r0.emojiViewChildBg;
    }

    /* renamed from: -$$Nest$fgetfadeDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundWithFadeDrawable m12663$$Nest$fgetfadeDrawable(ShareAlert r0) {
        return r0.fadeDrawable;
    }

    /* renamed from: -$$Nest$fgetframeLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m12664$$Nest$fgetframeLayout(ShareAlert r0) {
        return r0.frameLayout;
    }

    /* renamed from: -$$Nest$fgetframeLayout2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m12665$$Nest$fgetframeLayout2(ShareAlert r0) {
        return r0.frameLayout2;
    }

    /* renamed from: -$$Nest$fgetfullyShown, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12666$$Nest$fgetfullyShown(ShareAlert r0) {
        return r0.fullyShown;
    }

    /* renamed from: -$$Nest$fgetgridView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m12667$$Nest$fgetgridView(ShareAlert r0) {
        return r0.gridView;
    }

    /* renamed from: -$$Nest$fgetiBlur3SourceGlass, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundSourceRenderNode m12668$$Nest$fgetiBlur3SourceGlass(ShareAlert r0) {
        return r0.iBlur3SourceGlass;
    }

    /* renamed from: -$$Nest$fgetiBlur3SourceGlassFrosted, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundSourceRenderNode m12669$$Nest$fgetiBlur3SourceGlassFrosted(ShareAlert r0) {
        return r0.iBlur3SourceGlassFrosted;
    }

    /* renamed from: -$$Nest$fgetincludeStory, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12670$$Nest$fgetincludeStory(ShareAlert r0) {
        return r0.includeStory;
    }

    /* renamed from: -$$Nest$fgetkeyboardSize2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12671$$Nest$fgetkeyboardSize2(ShareAlert r0) {
        return r0.keyboardSize2;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GridLayoutManager m12672$$Nest$fgetlayoutManager(ShareAlert r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetlinkToCopy, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String[] m12673$$Nest$fgetlinkToCopy(ShareAlert r0) {
        return r0.linkToCopy;
    }

    /* renamed from: -$$Nest$fgetlistAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ShareDialogsAdapter m12674$$Nest$fgetlistAdapter(ShareAlert r0) {
        return r0.listAdapter;
    }

    /* renamed from: -$$Nest$fgetparentFragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivity m12675$$Nest$fgetparentFragment(ShareAlert r0) {
        return r0.parentFragment;
    }

    /* renamed from: -$$Nest$fgetpickerBottom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m12676$$Nest$fgetpickerBottom(ShareAlert r0) {
        return r0.pickerBottom;
    }

    /* renamed from: -$$Nest$fgetpickerBottomLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m12677$$Nest$fgetpickerBottomLayout(ShareAlert r0) {
        return r0.pickerBottomLayout;
    }

    /* renamed from: -$$Nest$fgetpreviousScrollOffsetY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12678$$Nest$fgetpreviousScrollOffsetY(ShareAlert r0) {
        return r0.previousScrollOffsetY;
    }

    /* renamed from: -$$Nest$fgetrecentSearchObjects, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m12679$$Nest$fgetrecentSearchObjects(ShareAlert r0) {
        return r0.recentSearchObjects;
    }

    /* renamed from: -$$Nest$fgetscrollOffsetY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12680$$Nest$fgetscrollOffsetY(ShareAlert r0) {
        return r0.scrollOffsetY;
    }

    /* renamed from: -$$Nest$fgetscrollableViewNoiseSuppressor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DownscaleScrollableNoiseSuppressor m12681$$Nest$fgetscrollableViewNoiseSuppressor(ShareAlert r0) {
        return r0.scrollableViewNoiseSuppressor;
    }

    /* renamed from: -$$Nest$fgetsearchAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ShareSearchAdapter m12682$$Nest$fgetsearchAdapter(ShareAlert r0) {
        return r0.searchAdapter;
    }

    /* renamed from: -$$Nest$fgetsearchEmptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StickerEmptyView m12683$$Nest$fgetsearchEmptyView(ShareAlert r0) {
        return r0.searchEmptyView;
    }

    /* renamed from: -$$Nest$fgetsearchGridView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m12684$$Nest$fgetsearchGridView(ShareAlert r0) {
        return r0.searchGridView;
    }

    /* renamed from: -$$Nest$fgetsearchIsVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12685$$Nest$fgetsearchIsVisible(ShareAlert r0) {
        return r0.searchIsVisible;
    }

    /* renamed from: -$$Nest$fgetsearchLayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FillLastGridLayoutManager m12686$$Nest$fgetsearchLayoutManager(ShareAlert r0) {
        return r0.searchLayoutManager;
    }

    /* renamed from: -$$Nest$fgetselectedTopicDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.Dialog m12687$$Nest$fgetselectedTopicDialog(ShareAlert r0) {
        return r0.selectedTopicDialog;
    }

    /* renamed from: -$$Nest$fgetsendPopupWindow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarPopupWindow m12688$$Nest$fgetsendPopupWindow(ShareAlert r0) {
        return r0.sendPopupWindow;
    }

    /* renamed from: -$$Nest$fgetshadow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View[] m12689$$Nest$fgetshadow(ShareAlert r0) {
        return r0.shadow;
    }

    /* renamed from: -$$Nest$fgetshadowAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet[] m12690$$Nest$fgetshadowAnimation(ShareAlert r0) {
        return r0.shadowAnimation;
    }

    /* renamed from: -$$Nest$fgetshadowDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m12691$$Nest$fgetshadowDrawable(ShareAlert r0) {
        return r0.shadowDrawable;
    }

    /* renamed from: -$$Nest$fgetshareTopicsAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ShareTopicsAdapter m12692$$Nest$fgetshareTopicsAdapter(ShareAlert r0) {
        return r0.shareTopicsAdapter;
    }

    /* renamed from: -$$Nest$fgetsharesCountLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m12693$$Nest$fgetsharesCountLayout(ShareAlert r0) {
        return r0.sharesCountLayout;
    }

    /* renamed from: -$$Nest$fgetsystemInsets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Insets m12694$$Nest$fgetsystemInsets(ShareAlert r0) {
        return r0.systemInsets;
    }

    /* renamed from: -$$Nest$fgettopBackgroundAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m12695$$Nest$fgettopBackgroundAnimator(ShareAlert r0) {
        return r0.topBackgroundAnimator;
    }

    /* renamed from: -$$Nest$fgettopicsAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SpringAnimation m12696$$Nest$fgettopicsAnimation(ShareAlert r0) {
        return r0.topicsAnimation;
    }

    /* renamed from: -$$Nest$fgettopicsGridView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m12697$$Nest$fgettopicsGridView(ShareAlert r0) {
        return r0.topicsGridView;
    }

    /* renamed from: -$$Nest$fgettopicsLayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GridLayoutManager m12698$$Nest$fgettopicsLayoutManager(ShareAlert r0) {
        return r0.topicsLayoutManager;
    }

    /* renamed from: -$$Nest$fgetupdateSearchAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12699$$Nest$fgetupdateSearchAdapter(ShareAlert r0) {
        return r0.updateSearchAdapter;
    }

    /* renamed from: -$$Nest$fgetwriteButtonContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m12700$$Nest$fgetwriteButtonContainer(ShareAlert r0) {
        return r0.writeButtonContainer;
    }

    /* renamed from: -$$Nest$fputanimatorSet, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12701$$Nest$fputanimatorSet(ShareAlert r0, AnimatorSet r1) {
        r0.animatorSet = r1;
    }

    /* renamed from: -$$Nest$fputcaptionEditTextTopOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12702$$Nest$fputcaptionEditTextTopOffset(ShareAlert r0, float r1) {
        r0.captionEditTextTopOffset = r1;
    }

    /* renamed from: -$$Nest$fputchatActivityEnterViewAnimateFromTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12703$$Nest$fputchatActivityEnterViewAnimateFromTop(ShareAlert r0, float r1) {
        r0.chatActivityEnterViewAnimateFromTop = r1;
    }

    /* renamed from: -$$Nest$fputcontainerViewTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12704$$Nest$fputcontainerViewTop(ShareAlert r0, int r1) {
        r0.containerViewTop = r1;
    }

    /* renamed from: -$$Nest$fputcurrentPanTranslationY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12705$$Nest$fputcurrentPanTranslationY(ShareAlert r0, float r1) {
        r0.currentPanTranslationY = r1;
    }

    /* renamed from: -$$Nest$fputfullyShown, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12706$$Nest$fputfullyShown(ShareAlert r0, boolean r1) {
        r0.fullyShown = r1;
    }

    /* renamed from: -$$Nest$fputkeyboardSize2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12707$$Nest$fputkeyboardSize2(ShareAlert r0, int r1) {
        r0.keyboardSize2 = r1;
    }

    /* renamed from: -$$Nest$fputkeyboardT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12708$$Nest$fputkeyboardT(ShareAlert r0, float r1) {
        r0.keyboardT = r1;
    }

    /* renamed from: -$$Nest$fputpanTranslationMoveLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12709$$Nest$fputpanTranslationMoveLayout(ShareAlert r0, boolean r1) {
        r0.panTranslationMoveLayout = r1;
    }

    /* renamed from: -$$Nest$fputpreviousScrollOffsetY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12710$$Nest$fputpreviousScrollOffsetY(ShareAlert r0, int r1) {
        r0.previousScrollOffsetY = r1;
    }

    /* renamed from: -$$Nest$fputrecentSearchObjects, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12711$$Nest$fputrecentSearchObjects(ShareAlert r0, ArrayList r1) {
        r0.recentSearchObjects = r1;
    }

    /* renamed from: -$$Nest$fputrecentSearchObjectsById, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12712$$Nest$fputrecentSearchObjectsById(ShareAlert r0, LongSparseArray r1) {
        r0.recentSearchObjectsById = r1;
    }

    /* renamed from: -$$Nest$fputscrollOffsetY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12713$$Nest$fputscrollOffsetY(ShareAlert r0, int r1) {
        r0.scrollOffsetY = r1;
    }

    /* renamed from: -$$Nest$fputsearchWasVisibleBeforeTopics, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12714$$Nest$fputsearchWasVisibleBeforeTopics(ShareAlert r0, boolean r1) {
        r0.searchWasVisibleBeforeTopics = r1;
    }

    /* renamed from: -$$Nest$fputtopBackgroundAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12715$$Nest$fputtopBackgroundAnimator(ShareAlert r0, ValueAnimator r1) {
        r0.topBackgroundAnimator = r1;
    }

    /* renamed from: -$$Nest$fputtopBeforeSwitch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12716$$Nest$fputtopBeforeSwitch(ShareAlert r0, int r1) {
        r0.topBeforeSwitch = r1;
    }

    /* renamed from: -$$Nest$fputtopicsAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12717$$Nest$fputtopicsAnimation(ShareAlert r0, SpringAnimation r1) {
        r0.topicsAnimation = r1;
    }

    /* renamed from: -$$Nest$mblur3_InvalidateBlur, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12718$$Nest$mblur3_InvalidateBlur(ShareAlert r0) {
        r0.blur3_InvalidateBlur();
    }

    /* renamed from: -$$Nest$mcheckCurrentList, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12719$$Nest$mcheckCurrentList(ShareAlert r0, boolean r1) {
        r0.checkCurrentList(r1);
    }

    /* renamed from: -$$Nest$mdismissSendPopupWindow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12720$$Nest$mdismissSendPopupWindow(ShareAlert r0) {
        r0.dismissSendPopupWindow();
    }

    /* renamed from: -$$Nest$mgetCurrentTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12721$$Nest$mgetCurrentTop(ShareAlert r0) {
        return r0.getCurrentTop();
    }

    /* renamed from: -$$Nest$minvalidateTopicsAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12722$$Nest$minvalidateTopicsAnimation(ShareAlert r0, View r1, int[] r2, float r3) {
        r0.invalidateTopicsAnimation(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mselectDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12723$$Nest$mselectDialog(ShareAlert r0, View r1, TLRPC.Dialog r2) {
        r0.selectDialog(r1, r2);
    }

    /* renamed from: -$$Nest$mshowPremiumBlockedToast, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12724$$Nest$mshowPremiumBlockedToast(ShareAlert r0, View r1, long r2) {
        r0.showPremiumBlockedToast(r1, r2);
    }

    /* renamed from: -$$Nest$mupdateLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12725$$Nest$mupdateLayout(ShareAlert r0) {
        r0.updateLayout();
    }

    /* renamed from: -$$Nest$mupdateLinkTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12726$$Nest$mupdateLinkTextView(ShareAlert r0) {
        r0.updateLinkTextView();
    }

    public ShareAlert(Context r9, ArrayList<MessageObject> r10, String r11, boolean r12, String r13, boolean r14) {
        this(r9, r10, r11, r12, r13, r14, null);
    }

    public ShareAlert(Context r16, ArrayList<MessageObject> r17, String r18, boolean r19, String r20, boolean r21, Theme.ResourcesProvider r22) {
        this(r16, null, r17, null, r18, null, r19, r20, null, r21, false, false, null, r22);
    }

    public ShareAlert(Context r34, ChatActivity r35, ArrayList<MessageObject> r36, String r37, String r38, String r39, boolean r40, String r41, String r42, boolean r43, boolean r44, boolean r45, Integer r46, Theme.ResourcesProvider r47) {
        super(r34, true, r47);
        this.sendingText = new String[2];
        this.shadow = new View[2];
        this.shadowAnimation = new AnimatorSet[2];
        this.selectedDialogs = new LongSparseArray();
        this.selectedDialogTopics = new HashMap();
        this.containerViewTop = -1;
        this.fullyShown = false;
        this.rect = new RectF();
        this.paint = new Paint(1);
        this.textPaint = new TextPaint(1);
        this.linkToCopy = new String[2];
        this.recentSearchObjects = new ArrayList();
        this.recentSearchObjectsById = new LongSparseArray();
        this.systemInsets = Insets.NONE;
        this.shiftDp = 4;
        this.hideSendersName = false;
        this.hideCaption = false;
        this.lastOffset = Integer.MAX_VALUE;
        ArrayList<RectF> r12 = new ArrayList();
        this.iBlur3Positions = r12;
        RectF r13 = new RectF();
        this.iBlur3PositionMainTabs = r13;
        r12.add(r13);
        AndroidUtilities.enableEdgeToEdge(getWindow());
        BlurredBackgroundSourceColor r122 = new BlurredBackgroundSourceColor();
        this.iBlur3SourceColor = r122;
        int r132 = Theme.key_windowBackgroundWhite;
        r122.setColor(getThemedColor(r132));
        if (Build.VERSION.SDK_INT < 31) goto L5;
        this.scrollableViewNoiseSuppressor = new DownscaleScrollableNoiseSuppressor();
        BlurredBackgroundSourceRenderNode r14 = new BlurredBackgroundSourceRenderNode(null);
        this.iBlur3SourceGlass = r14;
        r14.setupRenderer(new AnonymousClass1(this));
        BlurredBackgroundSourceRenderNode r15 = new BlurredBackgroundSourceRenderNode(null);
        this.iBlur3SourceGlassFrosted = r15;
        r15.setupRenderer(new AnonymousClass2(this));
        BlurredBackgroundDrawableViewFactory r9 = new BlurredBackgroundDrawableViewFactory(r14);
        this.iBlur3FactoryLiquidGlass = r9;
        r9.setLiquidGlassEffectAllowed(LiteMode.isEnabled(262144));
        BlurredBackgroundDrawableViewFactory r92 = new BlurredBackgroundDrawableViewFactory(r15);
        this.iBlur3FactoryFrostedLiquidGlass = r92;
        r92.setLiquidGlassEffectAllowed(LiteMode.isEnabled(262144));
    L6:
        this.iBlur3FactoryFade = new BlurredBackgroundDrawableViewFactory(r122);
        this.resourcesProvider = r47;
        this.includeStory = r45;
        this.parentActivity = AndroidUtilities.findActivity(r34);
        this.darkTheme = r44;
        if (r44 == false) goto L9;
        this.resourcesProvider = new AnonymousClass3(this);
    L9:
        r122.setColor(getThemedColor(r132));
        this.parentFragment = r35;
        if (r35 == null) goto L12;
        this.hideSendersName = r35.isForwardNoQuote();
    L12:
        this.shadowDrawable = r34.getResources().getDrawable(R.drawable.sheet_shadow_round).mutate();
        int r2 = Theme.key_dialogBackground;
        this.behindKeyboardColorKey = r2;
        this.shadowDrawable.setColorFilter(new PorterDuffColorFilter(getThemedColor(r2), PorterDuff.Mode.MULTIPLY));
        this.occupyNavigationBarWithoutKeyboard = true;
        this.isFullscreen = r43;
        String[] r22 = this.linkToCopy;
        r22[0] = r41;
        r22[1] = r42;
        if (r41 != null) goto L25;
        if (r42 != null) goto L25;
        if (r36 == null) goto L25;
        if (r36.isEmpty() == true) goto L25;
        MessageObject r23 = r36.get(0);
        String r5 = DialogObject.getPublicUsername(MessagesController.getInstance(this.currentAccount).getUserOrChat(r23.getDialogId()));
        if (TextUtils.isEmpty(r5) == true) goto L25;
        this.linkToCopy[0] = "https://" + MessagesController.getInstance(this.currentAccount).linkPrefix + "/" + r5 + "/" + r23.getId();
        if (r36.size() != 1) goto L25;
        if (r23.hasValidGroupId() == false) goto L25;
        StringBuilder r24 = new StringBuilder();
        String[] r52 = this.linkToCopy;
        r24.append(r52[0]);
        r24.append("?single");
        r52[0] = r24.toString();
    L25:
        this.sendingMessageObjects = r36;
        this.searchAdapter = new ShareSearchAdapter(this, r34);
        this.isChannel = r40;
        String[] r25 = this.sendingText;
        r25[0] = r38;
        r25[1] = r39;
        this.sendingFile = r37;
        this.useSmoothKeyboard = true;
        super.setDelegate(new AnonymousClass4(this));
        ArrayList<MessageObject> r26 = this.sendingMessageObjects;
        if (r26 == null) goto L42;
        int r27 = r26.size();
        int r53 = 0;
    L28:
        if (r53 >= r27) goto L42;
        MessageObject r6 = this.sendingMessageObjects.get(r53);
        if (r6.isTodo() == false) goto L33;
        this.hasPoll = 3;
    L41:
        r53 = r53 + 1;
        goto L28
    L33:
        if (r6.isPoll() == false) goto L41;
        if (r6.isPublicPoll() == false) goto L37;
        int r62 = 2;
    L38:
        this.hasPoll = r62;
        if (r62 != 2) goto L41;
    L37:
        r62 = 1;
    L42:
        if (r40 == false) goto L47;
        if (r36 == null) goto L47;
        if (r36.isEmpty() == true) goto L47;
        this.loadingLink = true;
        TLRPC.TL_channels_exportMessageLink r28 = new TLRPC.TL_channels_exportMessageLink();
        r28.id = r36.get(0).getId();
        r28.channel = MessagesController.getInstance(this.currentAccount).getInputChannel(r36.get(0).messageOwner.peer_id.channel_id);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r28, new ShareAlert$$ExternalSyntheticLambda2(this, r34));
    L47:
        AnonymousClass5 r29 = new AnonymousClass5(this, r34);
        this.sizeNotifierFrameLayout = r29;
        this.containerView = r29;
        this.iBlur3FactoryLiquidGlass.setSourceRootView(new ViewPositionWatcher(this.containerView), this.containerView);
        this.iBlur3FactoryFrostedLiquidGlass.setSourceRootView(new ViewPositionWatcher(this.containerView), this.containerView);
        this.iBlur3FactoryFade.setSourceRootView(new ViewPositionWatcher(this.containerView), this.containerView);
        this.containerView.setWillNotDraw(false);
        this.containerView.setClipChildren(false);
        ViewGroup r210 = this.containerView;
        int r4 = this.backgroundPaddingLeft;
        r210.setPadding(r4, 0, r4, this.systemInsets.bottom);
        FrameLayout r211 = new FrameLayout(r34);
        this.frameLayout = r211;
        int r48 = Theme.key_dialogBackground;
        r211.setBackgroundColor(getThemedColor(r48));
        if (this.darkTheme == true) goto L50;
    L52:
        FragmentSearchField r212 = new FragmentSearchField(r34, this.resourcesProvider);
        this.searchView = r212;
        r212.editText.addTextChangedListener(new AnonymousClass7(this));
        this.searchView.editText.setHint(LocaleController.getString(R.string.ShareSendTo));
        this.searchView.editText.setOnEditorActionListener(new ShareAlert$$ExternalSyntheticLambda11(this));
        this.searchView.setCloseButtonOnClickListener(new ShareAlert$$ExternalSyntheticLambda12(this));
        this.frameLayout.addView(this.searchView, LayoutHelper.createFrame(-1, 40.0f, 83, 11.0f, 7.0f, 11.0f, 11.0f));
        ActionBar r213 = new ActionBar(r34);
        this.topicsBackActionBar = r213;
        r213.setOccupyStatusBar(false);
        this.topicsBackActionBar.setBackButtonImage(R.drawable.ic_ab_back);
        ActionBar r214 = this.topicsBackActionBar;
        int r54 = Theme.key_dialogTextBlack;
        r214.setTitleColor(getThemedColor(r54));
        this.topicsBackActionBar.setSubtitleColor(getThemedColor(Theme.key_dialogTextGray2));
        this.topicsBackActionBar.setItemsColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2), false);
        this.topicsBackActionBar.setItemsBackgroundColor(Theme.getColor(Theme.key_actionBarWhiteSelector), false);
        this.topicsBackActionBar.setActionBarMenuOnItemClick(new AnonymousClass8(this));
        this.topicsBackActionBar.setVisibility(8);
        this.frameLayout.addView(this.topicsBackActionBar, LayoutHelper.createFrame(-1, 58, 83));
        RecyclerListView r215 = new RecyclerListView(r34, this.resourcesProvider);
        this.topicsGridView = r215;
        GridLayoutManager r93 = new GridLayoutManager(r34, 4);
        this.topicsLayoutManager = r93;
        r215.setLayoutManager(r93);
        this.topicsLayoutManager.setSpanSizeLookup(new AnonymousClass9(this));
        this.topicsGridView.setOnScrollListener(new AnonymousClass10(this));
        RecyclerListView r216 = this.topicsGridView;
        ShareTopicsAdapter r94 = new ShareTopicsAdapter(this, r34);
        this.shareTopicsAdapter = r94;
        r216.setAdapter(r94);
        RecyclerListView r217 = this.topicsGridView;
        int r95 = Theme.key_dialogScrollGlow;
        r217.setGlowColor(getThemedColor(r95));
        this.topicsGridView.setVerticalScrollBarEnabled(false);
        this.topicsGridView.setHorizontalScrollBarEnabled(false);
        this.topicsGridView.setOverScrollMode(2);
        this.topicsGridView.setSelectorDrawableColor(0);
        this.topicsGridView.setItemSelectorColorProvider(new ShareAlert$$ExternalSyntheticLambda13());
        this.topicsGridView.setPadding(0, 0, 0, AndroidUtilities.dp(48.0f));
        this.topicsGridView.setClipToPadding(false);
        this.topicsGridView.addItemDecoration(new AnonymousClass11(this));
        this.topicsGridView.setOnItemClickListener(new ShareAlert$$ExternalSyntheticLambda14(this));
        this.topicsGridView.setVisibility(8);
        this.containerView.addView(this.topicsGridView, LayoutHelper.createFrame(-1, -1, 51));
        AnonymousClass12 r218 = new AnonymousClass12(this, r34, this.resourcesProvider);
        this.gridView = r218;
        r218.setSelectorDrawableColor(0);
        this.gridView.setItemSelectorColorProvider(new ShareAlert$$ExternalSyntheticLambda15());
        this.gridView.setPadding(0, 0, 0, AndroidUtilities.dp(48.0f));
        this.gridView.setClipToPadding(false);
        RecyclerListView r219 = this.gridView;
        GridLayoutManager r123 = new GridLayoutManager(getContext(), 4);
        this.layoutManager = r123;
        r219.setLayoutManager(r123);
        RecyclerListView r124 = this.gridView;
        ViewGroup r142 = this.containerView;
        Objects.requireNonNull(r124);
        this.iBlur3Capture = new ViewGroupPartRenderer(r124, r142, new ShareAlert$$ExternalSyntheticLambda16(r124));
        this.layoutManager.setSpanSizeLookup(new AnonymousClass13(this));
        this.gridView.setHorizontalScrollBarEnabled(false);
        this.gridView.setVerticalScrollBarEnabled(false);
        this.gridView.setOverScrollMode(2);
        this.gridView.addItemDecoration(new AnonymousClass14(this));
        this.containerView.addView(this.gridView, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 0.0f, 0.0f, 0.0f));
        RecyclerListView r220 = this.gridView;
        ShareDialogsAdapter r8 = new ShareDialogsAdapter(this, r34);
        this.listAdapter = r8;
        r220.setAdapter(r8);
        this.gridView.setGlowColor(getThemedColor(r95));
        this.gridView.setOnItemClickListener(new ShareAlert$$ExternalSyntheticLambda17(this));
        this.gridView.setOnScrollListener(new AnonymousClass15(this));
        AnonymousClass16 r221 = new AnonymousClass16(this, r34, this.resourcesProvider);
        this.searchGridView = r221;
        r221.setItemSelectorColorProvider(new ShareAlert$$ExternalSyntheticLambda18());
        this.searchGridView.setSelectorDrawableColor(0);
        this.searchGridView.setPadding(0, 0, 0, AndroidUtilities.dp(48.0f));
        this.searchGridView.setClipToPadding(false);
        RecyclerListView r222 = this.searchGridView;
        FillLastGridLayoutManager r82 = new FillLastGridLayoutManager(getContext(), 4, 0, this.searchGridView);
        this.searchLayoutManager = r82;
        r222.setLayoutManager(r82);
        this.searchLayoutManager.setSpanSizeLookup(new AnonymousClass17(this));
        this.searchGridView.setOnItemClickListener(new ShareAlert$$ExternalSyntheticLambda19(this));
        this.searchGridView.setHasFixedSize(true);
        this.searchGridView.setItemAnimator(null);
        this.searchGridView.setHorizontalScrollBarEnabled(false);
        this.searchGridView.setVerticalScrollBarEnabled(false);
        this.searchGridView.setOnScrollListener(new AnonymousClass18(this));
        this.searchGridView.addItemDecoration(new AnonymousClass19(this));
        this.searchGridView.setAdapter(this.searchAdapter);
        this.searchGridView.setGlowColor(getThemedColor(r95));
        this.recyclerItemsEnterAnimator = new RecyclerItemsEnterAnimator(this.searchGridView, true);
        FlickerLoadingView r223 = new FlickerLoadingView(r34, this.resourcesProvider);
        r223.setViewType(12);
        StickerEmptyView r83 = new StickerEmptyView(r34, r223, 1, this.resourcesProvider);
        this.searchEmptyView = r83;
        r83.addView(r223, 0);
        this.searchEmptyView.setAnimateLayoutChange(true);
        this.searchEmptyView.showProgress(false, false);
        if (this.darkTheme == false) goto L55;
        this.searchEmptyView.title.setTextColor(getThemedColor(Theme.key_voipgroup_nameText));
    L55:
        this.searchEmptyView.title.setText(LocaleController.getString(R.string.NoResult));
        this.searchGridView.setEmptyView(this.searchEmptyView);
        this.searchGridView.setHideIfEmpty(false);
        this.searchGridView.setAnimateEmptyView(true, 0);
        this.containerView.addView(this.searchEmptyView, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 52.0f, 0.0f, 0.0f));
        this.containerView.addView(this.searchGridView, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 0.0f, 0.0f, 0.0f));
        AnonymousClass20 r224 = new AnonymousClass20(this, r34);
        this.bottomFadeView = r224;
        this.containerView.addView(r224, LayoutHelper.createFrame(-1, 300, 80));
        FrameLayout.LayoutParams r225 = new FrameLayout.LayoutParams(-1, AndroidUtilities.getShadowHeight(), 51);
        if (this.darkTheme == true) goto L58;
    L60:
        float r84 = 58.0f;
    L61:
        r225.topMargin = AndroidUtilities.dp(r84);
        this.shadow[0] = new View(r34);
        View r85 = this.shadow[0];
        int r96 = Theme.key_dialogShadowLine;
        r85.setBackgroundColor(getThemedColor(r96));
        this.shadow[0].setAlpha(0.0f);
        this.shadow[0].setTag(1);
        this.containerView.addView(this.shadow[0], r225);
        ViewGroup r226 = this.containerView;
        FrameLayout r86 = this.frameLayout;
        if (this.darkTheme == true) goto L64;
    L67:
        int r143 = 58;
    L68:
        r226.addView(r86, LayoutHelper.createFrame(-1, r143, 51));
        FrameLayout.LayoutParams r227 = new FrameLayout.LayoutParams(-1, AndroidUtilities.getShadowHeight(), 83);
        r227.bottomMargin = AndroidUtilities.dp(48.0f);
        this.shadow[1] = new View(r34);
        this.shadow[1].setBackgroundColor(getThemedColor(r96));
        this.shadow[1].setLayoutParams(r227);
        if (this.isChannel == false) goto L74;
        if (r36 == null) goto L74;
        if (r36.size() != 1) goto L74;
    L75:
        this.pickerBottom = new FrameLayout(r34);
        FrameLayout r228 = new FrameLayout(r34);
        this.pickerBottomLayout = r228;
        this.pickerBottom.addView(r228, LayoutHelper.createFrame(-1, -1.0f, 119, -2.0f, 0.0f, -2.0f, 0.0f));
        LinearLayout r229 = new LinearLayout(r34);
        r229.setOrientation(0);
        this.pickerBottomLayout.addView(r229, LayoutHelper.createFrame(-1, -1, 119));
        LinearLayout r3 = new LinearLayout(r34);
        this.linkContainer = r3;
        r3.setOrientation(0);
        ScaleStateListAnimator.apply(this.linkContainer, 0.015f, 1.2f);
        this.linkContainer.setOnClickListener(new ShareAlert$$ExternalSyntheticLambda3(this));
        SimpleTextView r32 = new SimpleTextView(r34);
        this.linkTextView = r32;
        r32.setTextSize(15);
        this.linkTextView.setTextColor(getThemedColor(Theme.key_share_linkText));
        this.linkTextView.setEllipsizeByGradient(true);
        updateLinkTextView();
        this.linkContainer.addView(this.linkTextView, LayoutHelper.createLinear(-1, 20, 1.0f, 23, 16, 0, 16, 0));
        TextView r33 = new TextView(r34);
        this.linkCopyButton = r33;
        r33.setTextSize(1, 14.0f);
        this.linkCopyButton.setTextColor(getThemedColor(Theme.key_windowBackgroundWhiteBlackText));
        this.linkCopyButton.setText(LocaleController.getString(R.string.Copy).toUpperCase());
        this.linkCopyButton.setPadding(AndroidUtilities.dp(9.0f), 0, AndroidUtilities.dp(9.0f), 0);
        this.linkCopyButton.setTypeface(AndroidUtilities.bold());
        this.linkCopyButton.setGravity(17);
        TextView r310 = this.linkCopyButton;
        int r63 = Theme.key_listSelector;
        r310.setBackground(Theme.createRadSelectorDrawable(getThemedColor(r63), 4, 4));
        this.linkContainer.addView(this.linkCopyButton, LayoutHelper.createLinear(-2, 28, 0.0f, 21, 0, 0, 7, 0));
        this.linkCopyButton.setOnClickListener(new ShareAlert$$ExternalSyntheticLambda4(this));
        ScaleStateListAnimator.apply(this.linkCopyButton);
        this.containerView.addView(this.pickerBottom, LayoutHelper.createFrame(-1, 58, 83));
        ArrayList<MessageObject> r311 = this.sendingMessageObjects;
        if (r311 != null) goto L78;
    L88:
        int r31 = 11;
        LinearLayout r7 = null;
    L89:
        r229.addView(this.linkContainer, LayoutHelper.createLinear(-1, 42, 1.0f, 23, 11, 0, r31, 0));
        if (r7 == null) goto L93;
        r229.addView(r7, LayoutHelper.createLinear(-2, -2, 0.0f, 16, 0, 5, 8, 0));
    L93:
        FrameLayout r230 = new FrameLayout(r34);
        this.bulletinContainer = r230;
        ViewGroup r312 = this.containerView;
        if (this.pickerBottomLayout == null) goto L96;
        float r313 = 48.0f;
    L97:
        r312.addView(r230, LayoutHelper.createFrame(-1, 100.0f, 87, 0.0f, 0.0f, 0.0f, r313));
        FrameLayout r231 = new FrameLayout(r34);
        this.bulletinContainer2 = r231;
        this.containerView.addView(r231, LayoutHelper.createFrame(-1, -2.0f, 55, 0.0f, 0.0f, 0.0f, 0.0f));
        AnonymousClass21 r232 = new AnonymousClass21(this, r34);
        this.frameLayout2 = r232;
        r232.setWillNotDraw(false);
        this.frameLayout2.setAlpha(0.0f);
        this.frameLayout2.setVisibility(4);
        this.containerView.addView(this.frameLayout2, LayoutHelper.createFrame(-1, -2.0f, 83, -2.0f, 0.0f, -2.0f, 0.0f));
        this.frameLayout2.setOnTouchListener(new ShareAlert$$ExternalSyntheticLambda6());
        BottomSheet.ContainerView r233 = this.container;
        if (AndroidUtilities.computePerceivedBrightness(getThemedColor(r48)) <= 0.721f) goto L100;
        boolean r314 = true;
    L101:
        AndroidUtilities.setLightNavigationBar(r233, r314);
        SizeNotifierFrameLayout r315 = this.sizeNotifierFrameLayout;
        if (this.sendingFile != null) goto L104;
        boolean r49 = true;
    L105:
        AnonymousClass22 r234 = new AnonymousClass22(this, r34, r315, null, 1, r49, this.resourcesProvider);
        this.commentTextView = r234;
        r234.includeNavigationBar = true;
        if (this.darkTheme == false) goto L108;
        EditTextCaption r235 = r234.getEditText();
        int r316 = Theme.key_voipgroup_nameText;
        r235.setTextColor(getThemedColor(r316));
        this.commentTextView.getEditText().setCursorColor(getThemedColor(r316));
    L108:
        EditTextEmoji r236 = this.commentTextView;
        if (this.sendingFile == null) goto L112;
        int r317 = R.string.WriteFileName;
    L113:
        r236.setHint(LocaleController.getString(r317));
        this.commentTextView.onResume();
        this.commentTextView.allowEmojisForNonPremium(LocaleUtils.canUseLocalPremiumEmojis(this.currentAccount));
        this.commentTextView.setPadding(0, 0, AndroidUtilities.dp(84.0f), 0);
        this.frameLayout2.addView(this.commentTextView, LayoutHelper.createFrame(-1, -2, 51));
        this.frameLayout2.setClipChildren(false);
        this.frameLayout2.setClipToPadding(false);
        this.commentTextView.setClipChildren(false);
        this.commentTextView.getEditText().addTextChangedListener(new AnonymousClass23(this));
        AnonymousClass24 r237 = new AnonymousClass24(this, r34);
        this.writeButtonContainer = r237;
        r237.setFocusable(true);
        this.writeButtonContainer.setFocusableInTouchMode(true);
        this.writeButtonContainer.setVisibility(4);
        this.writeButtonContainer.setScaleX(0.2f);
        this.writeButtonContainer.setScaleY(0.2f);
        this.writeButtonContainer.setAlpha(0.0f);
        this.containerView.addView(this.writeButtonContainer, LayoutHelper.createFrame(110, 50, 85));
        AnonymousClass25 r238 = new AnonymousClass25(this, r34, R.drawable.send_extera_24, this.resourcesProvider);
        this.writeButton = r238;
        r238.setCircleSize(AndroidUtilities.dp(52.0f), AndroidUtilities.dp(38.0f));
        this.writeButton.setCirclePadding(AndroidUtilities.dp(1.0f), AndroidUtilities.dp(6.0f));
        ChatActivityEnterView.SendButton r239 = this.writeButton;
        r239.newCounterPos = true;
        this.writeButtonContainer.addView(r239, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, 0.0f, 0.0f, 2.0f));
        this.writeButton.setOnClickListener(new ShareAlert$$ExternalSyntheticLambda7(this));
        this.writeButton.setOnLongClickListener(new ShareAlert$$ExternalSyntheticLambda8(this));
        this.textPaint.setTextSize(AndroidUtilities.dp(12.0f));
        this.textPaint.setTypeface(AndroidUtilities.bold());
        if (r46 == null) goto L125;
        this.timestamp = r46.intValue();
        this.timestampFrameLayout = new FrameLayout(r34);
        LinearLayout r240 = new LinearLayout(r34);
        this.timestampLayout = r240;
        r240.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(4.0f));
        this.timestampLayout.setClipToPadding(false);
        this.timestampLayout.setOrientation(0);
        this.timestampLayout.setBackground(Theme.createRadSelectorDrawable(getThemedColor(Theme.key_listSelector), 6, 6));
        CheckBox2 r241 = new CheckBox2(r34, 24, this.resourcesProvider);
        this.timestampCheckbox = r241;
        r241.setColor(Theme.key_radioBackgroundChecked, Theme.key_checkboxDisabled, Theme.key_checkboxCheck);
        this.timestampCheckbox.setDrawUnchecked(true);
        this.timestampCheckbox.setChecked(false, false);
        this.timestampCheckbox.setDrawBackgroundAsArc(10);
        this.timestampLayout.addView(this.timestampCheckbox, LayoutHelper.createLinear(26, 26, 16, 0, 0, 0, 0));
        TextView r242 = new TextView(r34);
        this.timestampTextView = r242;
        r242.setTextColor(getThemedColor(r54));
        this.timestampTextView.setTextSize(1, 14.0f);
        this.timestampTextView.setText(LocaleController.formatString(R.string.VideoShareAddTimestamp, new Object[]{AndroidUtilities.formatShortDuration(r46.intValue())}));
        this.timestampLayout.addView(this.timestampTextView, LayoutHelper.createLinear(-2, -2, 16, 9, 0, 0, 0));
        ScaleStateListAnimator.apply(this.timestampLayout, 0.025f, 1.5f);
        this.timestampLayout.setOnClickListener(new ShareAlert$$ExternalSyntheticLambda9(this));
        this.timestampFrameLayout.addView(this.timestampLayout, LayoutHelper.createFrame(-2, -2, 17));
        if (this.pickerBottom != null) goto L122;
        View r243 = new View(r34);
        r243.setBackgroundColor(getThemedColor(Theme.key_divider));
        FrameLayout r1 = this.timestampFrameLayout;
        if (this.darkTheme == false) goto L120;
        float r410 = 0.66f;
    L121:
        r1.addView(r243, LayoutHelper.createFrame(-1.0f, r410, 55));
        goto L122
    L120:
        r410 = 1.0f / AndroidUtilities.density;
    L122:
        this.containerView.addView(this.timestampFrameLayout, LayoutHelper.createFrame(-1, 58, 83));
        if (this.pickerBottom != null) goto L125;
        this.timestampFrameLayout.setAlpha(0.0f);
        this.timestampFrameLayout.setVisibility(4);
    L125:
        this.fadeDrawable = new BlurredBackgroundWithFadeDrawable(this.iBlur3FactoryFade.create(this.bottomFadeView, null));
        if (SharedConfig.chatBlurEnabled() == false) goto L128;
        LiteMode.isEnabled(262144);
    L128:
        this.fadeDrawable.setFadeHeight(AndroidUtilities.dp(72.0f), true);
        BlurredBackgroundDrawable r16 = this.iBlur3FactoryFrostedLiquidGlass.create(this.sizeNotifierFrameLayout, BlurredBackgroundProviderImpl.inputFieldShareAlert(this.resourcesProvider));
        this.emojiViewChildBg = r16;
        r16.enableInAppKeyboardOptimization();
        this.emojiViewChildBg.setRadius(AndroidUtilities.dp(29.0f), AndroidUtilities.dp(29.0f), 0.0f, 0.0f);
        this.emojiViewChildBg.setThickness(AndroidUtilities.dp(32.0f));
        this.emojiViewChildBg.setIntensity(0.4f);
        BlurredBackgroundDrawable r17 = this.iBlur3FactoryLiquidGlass.create(this.frameLayout2, BlurredBackgroundProviderImpl.inputFieldShareAlert(this.resourcesProvider));
        this.captionContainerBg = r17;
        r17.setRadius(AndroidUtilities.dp(22.0f));
        this.captionContainerBg.setPadding(AndroidUtilities.dp(9.0f));
        this.frameLayout2.setPadding(AndroidUtilities.dp(9.0f), AndroidUtilities.dp(7.0f), AndroidUtilities.dp(9.0f), AndroidUtilities.dp(7.0f));
        FrameLayout r18 = this.pickerBottomLayout;
        if (r18 == null) goto L131;
        BlurredBackgroundDrawable r19 = this.iBlur3FactoryLiquidGlass.create(r18, BlurredBackgroundProviderImpl.inputFieldShareAlert(this.resourcesProvider));
        r19.setPadding(AndroidUtilities.dp(9.0f));
        r19.setRadius(AndroidUtilities.dp(22.0f));
        this.pickerBottomLayout.setBackground(r19);
        this.pickerBottomLayout.setPadding(AndroidUtilities.dp(9.0f), AndroidUtilities.dp(9.0f), AndroidUtilities.dp(9.0f), AndroidUtilities.dp(9.0f));
    L131:
        FrameLayout r110 = this.timestampFrameLayout;
        if (r110 == null) goto L134;
        BlurredBackgroundDrawable r111 = this.iBlur3FactoryLiquidGlass.create(r110, BlurredBackgroundProviderImpl.inputFieldShareAlert(this.resourcesProvider));
        r111.setPadding(AndroidUtilities.dp(9.0f));
        r111.setRadius(AndroidUtilities.dp(22.0f));
        this.timestampFrameLayout.setBackground(r111);
        this.timestampFrameLayout.setPadding(AndroidUtilities.dp(9.0f), AndroidUtilities.dp(9.0f), AndroidUtilities.dp(9.0f), AndroidUtilities.dp(9.0f));
    L134:
        updateSelectedCount(0);
        DialogsActivity.loadDialogs(AccountInstance.getInstance(this.currentAccount));
        if (ShareDialogsAdapter.m12739$$Nest$fgetdialogs(this.listAdapter).isEmpty() == false) goto L137;
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.dialogsNeedReload);
    L137:
        DialogsSearchAdapter.loadRecentSearch(this.currentAccount, 0, new AnonymousClass26(this));
        MediaDataController.getInstance(this.currentAccount).loadHints(true);
        AndroidUtilities.updateViewVisibilityAnimated(this.gridView, true, 1.0f, false);
        AndroidUtilities.updateViewVisibilityAnimated(this.searchGridView, false, 1.0f, false);
        ViewCompat.setOnApplyWindowInsetsListener(getContainer(), new ShareAlert$$ExternalSyntheticLambda10(this));
        return;
    L112:
        r317 = R.string.ShareComment;
        goto L113
    L104:
        r49 = false;
        goto L105
    L100:
        r314 = false;
        goto L101
    L96:
        r313 = 0.0f;
        goto L97
    L78:
        if (r311.size() <= 0) goto L88;
        if (this.sendingMessageObjects.get(0).messageOwner == null) goto L88;
        if (this.sendingMessageObjects.get(0).messageOwner.forwards <= 0) goto L88;
        MessageObject r318 = this.sendingMessageObjects.get(0);
        if (ChatObject.hasAdminRights(MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r318.getDialogId()))) == false) goto L88;
        if (r318.isForwarded() == true) goto L88;
        r7 = new LinearLayout(r34);
        r7.setOrientation(0);
        r7.setOnClickListener(new ShareAlert$$ExternalSyntheticLambda5(this, r318));
        r7.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(4.0f));
        r7.setBackground(Theme.createRadSelectorDrawable(getThemedColor(r63), 6, 6));
        ScaleStateListAnimator.apply(r7);
        ImageView r64 = new ImageView(r34);
        r64.setImageResource(R.drawable.mini_stats_shares);
        r64.setScaleType(ImageView.ScaleType.CENTER);
        int r97 = Theme.key_share_icon;
        r64.setColorFilter(new PorterDuffColorFilter(getThemedColor(r97), PorterDuff.Mode.SRC_IN));
        r7.addView(r64, LayoutHelper.createLinear(-2, -2, 0.0f, 16));
        TextView r65 = new TextView(r34);
        r65.setTextColor(getThemedColor(r97));
        r65.setTextSize(1, 15.0f);
        r65.setGravity(17);
        r65.setText(LocaleController.formatNumber(r318.messageOwner.forwards, ','));
        r7.addView(r65, LayoutHelper.createLinear(-2, -2, 0.0f, 16, 4, -1, 0, 0));
        r31 = 8;
    L74:
        if (this.linkToCopy[0] != null) goto L75;
        this.shadow[1].setAlpha(0.0f);
        goto L93
    L64:
        if (this.linkToCopy[1] == null) goto L67;
        r143 = 111;
        goto L68
    L58:
        if (this.linkToCopy[1] == null) goto L60;
        r84 = 111.0f;
        goto L61
    L50:
        if (this.linkToCopy[1] == null) goto L52;
        AnonymousClass6 r244 = new AnonymousClass6(this, r34);
        this.switchView = r244;
        this.frameLayout.addView(r244, LayoutHelper.createFrame(-1, 36.0f, 51, 0.0f, 11.0f, 0.0f, 0.0f));
        goto L52
    L5:
        this.scrollableViewNoiseSuppressor = null;
        this.iBlur3SourceGlassFrosted = null;
        this.iBlur3SourceGlass = null;
        this.iBlur3FactoryLiquidGlass = new BlurredBackgroundDrawableViewFactory(r122);
        this.iBlur3FactoryFrostedLiquidGlass = new BlurredBackgroundDrawableViewFactory(r122);
        goto L6
    }

    public ShareAlert(Context r16, ChatActivity r17, ArrayList<MessageObject> r18, String r19, String r20, boolean r21, String r22, String r23, boolean r24, boolean r25) {
        this(r16, r17, r18, null, r19, r20, r21, r22, r23, r24, r25, false, null, null);
    }

    public static /* synthetic */ int access$000(ShareAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$100(ShareAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ boolean access$1000(ShareAlert r0) {
        return r0.isFullscreen;
    }

    public static /* synthetic */ int access$1100(ShareAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1200(ShareAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$1300(ShareAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$1400(ShareAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ boolean access$1500(ShareAlert r0) {
        return r0.keyboardVisible;
    }

    public static /* synthetic */ int access$1600(ShareAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ boolean access$1700(ShareAlert r0) {
        return r0.keyboardVisible;
    }

    public static /* synthetic */ boolean access$1800(ShareAlert r0) {
        return r0.keyboardVisible;
    }

    public static /* synthetic */ int access$1900(ShareAlert r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$200(ShareAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$2000(ShareAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$2100(ShareAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$2200(ShareAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$2300(ShareAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ boolean access$2400(ShareAlert r0) {
        return r0.isFullscreen;
    }

    public static /* synthetic */ int access$2500(ShareAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$2600(ShareAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$2700(ShareAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ int access$2800(ShareAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ ViewGroup access$2900(ShareAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$300(ShareAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ ViewGroup access$3000(ShareAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$3100(ShareAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$3200(ShareAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$3300(ShareAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$3400(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3500(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3600(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3700(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3800(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$3900(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$400(ShareAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$4000(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4100(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4200(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4300(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4400(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4500(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4600(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4700(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$4800(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$500(ShareAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$5000(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$5100(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$5200(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$5300(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$5400(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$5500(ShareAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$5600(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$5700(ShareAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$5800(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$5900(ShareAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$600(ShareAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$6000(ShareAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$6100(ShareAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$6200(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$6300(ShareAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$6400(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$6500(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$6600(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$6700(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$6800(ShareAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$6900(ShareAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ ViewGroup access$700(ShareAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$7000(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$7100(ShareAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$7200(ShareAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$7300(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$7400(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$7500(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$7600(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$7700(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$7800(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$7900(ShareAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ ViewGroup access$800(ShareAlert r0) {
        return r0.containerView;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$900(ShareAlert r0) {
        return r0.resourcesProvider;
    }

    private void applyDarkTheme(ActionBarMenuSubItem r3) {
        if (this.darkTheme == false) goto L10;
        int r0 = getThemedColor(Theme.key_windowBackgroundWhiteHintText);
        r3.setColors(getThemedColor(Theme.key_voipgroup_nameText), r0);
        r3.setSubtextColor(r0);
        if (r3.getRightIcon() == null) goto L9;
        r3.getRightIcon().setColorFilter(new PorterDuffColorFilter(r0, PorterDuff.Mode.MULTIPLY));
        return;
    L9:
        return;
    }

    private void applyDarkTheme(MessagePreviewView.ToggleButton r3) {
        if (this.darkTheme == true) goto L5;
        return;
    L5:
        r3.setColors(getThemedColor(Theme.key_voipgroup_nameText), getThemedColor(Theme.key_windowBackgroundWhiteHintText));
        r3.setSelectorColor(getThemedColor(Theme.key_voipgroup_listSelector));
    }

    private void blur3_InvalidateBlur() {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        return;
    L5:
        if (this.scrollableViewNoiseSuppressor == null) goto L14;
        this.iBlur3PositionMainTabs.set(0.0f, 0.0f, this.containerView.getMeasuredWidth(), this.containerView.getMeasuredHeight());
        RectF r0 = this.iBlur3PositionMainTabs;
        if (LiteMode.isEnabled(262144) == false) goto L10;
        float r1 = 0.0f;
    L11:
        r0.inset(0.0f, r1);
        this.scrollableViewNoiseSuppressor.setupRenderNodes(this.iBlur3Positions, 1);
        this.scrollableViewNoiseSuppressor.invalidateResultRenderNodes(this.iBlur3Capture, this.containerView.getMeasuredWidth(), this.containerView.getMeasuredHeight());
        return;
    L10:
        r1 = -AndroidUtilities.dp(48.0f);
        goto L11
    }

    private void checkCurrentList(boolean r5) {
        boolean r2 = true;
        if (TextUtils.isEmpty(this.searchView.editText.getText()) == true) goto L5;
    L15:
        this.updateSearchAdapter = true;
        if (this.selectedTopicDialog != null) goto L19;
        AndroidUtilities.updateViewVisibilityAnimated(this.gridView, false, 0.98f, true);
        AndroidUtilities.updateViewVisibilityAnimated(this.searchGridView, true);
    L19:
        if (this.searchIsVisible != r2) goto L23;
        if (r5 == true) goto L23;
        return;
    L23:
        this.searchIsVisible = r2;
        this.searchAdapter.notifyDataSetChanged();
        this.listAdapter.notifyDataSetChanged();
        boolean r52 = this.searchIsVisible;
        int r0 = this.lastOffset;
        if (r52 == false) goto L31;
        RecyclerListView r53 = this.searchGridView;
        if (r0 != Integer.MAX_VALUE) goto L28;
        ((LinearLayoutManager) r53.getLayoutManager()).scrollToPositionWithOffset(0, -this.searchGridView.getPaddingTop());
    L29:
        this.searchAdapter.searchDialogs(this.searchView.editText.getText().toString());
        return;
    L28:
        ((LinearLayoutManager) r53.getLayoutManager()).scrollToPositionWithOffset(0, this.lastOffset - this.searchGridView.getPaddingTop());
        goto L29
    L31:
        GridLayoutManager r4 = this.layoutManager;
        if (r0 != Integer.MAX_VALUE) goto L35;
        r4.scrollToPositionWithOffset(0, 0);
        return;
    L35:
        r4.scrollToPositionWithOffset(0, 0);
        return;
    L5:
        if (this.keyboardVisible == false) goto L9;
        if (this.searchView.editText.hasFocus() == true) goto L15;
    L9:
        if (this.searchWasVisibleBeforeTopics == true) goto L15;
        if (this.selectedTopicDialog != null) goto L14;
        AndroidUtilities.updateViewVisibilityAnimated(this.gridView, true, 0.98f, true);
        AndroidUtilities.updateViewVisibilityAnimated(this.searchGridView, false);
    L14:
        r2 = false;
        goto L19
    }

    @SuppressLint({"NotifyDataSetChanged"})
    private void collapseTopics() {
        TLRPC.Dialog r0 = this.selectedTopicDialog;
        if (r0 == null) goto L36;
        View r1 = null;
        this.selectedTopicDialog = null;
        int r3 = 0;
    L7:
        if (r3 >= getMainGridView().getChildCount()) goto L14;
        View r4 = getMainGridView().getChildAt(r3);
        if ((r4 instanceof ShareDialogCell) == false) goto L13;
        if (((ShareDialogCell) r4).getCurrentDialog() != r0.id) goto L13;
        r1 = r4;
    L13:
        r3 = r3 + 1;
        goto L7
    L14:
        if (r1 != null) goto L16;
        return;
    L16:
        SpringAnimation r02 = this.topicsAnimation;
        if (r02 == null) goto L19;
        r02.cancel();
    L19:
        getMainGridView().setVisibility(0);
        this.searchView.setVisibility(0);
        if (this.searchIsVisible == false) goto L22;
    L23:
        this.sizeNotifierFrameLayout.adjustPanLayoutHelper.ignoreOnce();
        this.searchView.editText.requestFocus();
        AndroidUtilities.showKeyboard(this.searchView.editText);
    L24:
        int[] r03 = new int[2];
        SpringAnimation r2 = new SpringAnimation(new FloatValueHolder(1000.0f));
        SpringForce r32 = new SpringForce(0.0f);
        ChatActivity r42 = this.parentFragment;
        if (r42 != null) goto L27;
    L29:
        float r43 = 800.0f;
    L30:
        SpringAnimation r22 = r2.setSpring(r32.setStiffness(r43).setDampingRatio(1.0f));
        this.topicsAnimation = r22;
        r22.addUpdateListener(new ShareAlert$$ExternalSyntheticLambda0(this, r1, r03));
        this.topicsAnimation.addEndListener(new ShareAlert$$ExternalSyntheticLambda1(this));
        this.topicsAnimation.start();
        return;
    L27:
        if (r42.shareAlertDebugTopicsSlowMotion == false) goto L29;
        r43 = 10.0f;
        goto L30
    L22:
        if (this.searchWasVisibleBeforeTopics == false) goto L24;
    }

    private void copyLink(Context r4) {
        boolean r0 = false;
        if (this.exportedMessageLink == null) goto L5;
    L27:
        String r42 = getLink();     // Catch: Exception -> L24
        ClipboardManager r1 = (ClipboardManager) ApplicationLoader.applicationContext.getSystemService("clipboard");     // Catch: Exception -> L24
        if (r42 != null) goto L11;
        r42 = this.exportedMessageLink.link;     // Catch: Exception -> L24
    L11:
        r1.setPrimaryClip(ClipData.newPlainText("label", r42));     // Catch: Exception -> L24
        ShareAlertDelegate r43 = this.delegate;     // Catch: Exception -> L24
        if (r43 == null) goto L16;
        if (r43.didCopy() == false) goto L16;
        return;
    L16:
        if ((this.parentActivity instanceof LaunchActivity) == false) goto L30;
        TLRPC.TL_exportedMessageLink r44 = this.exportedMessageLink;     // Catch: Exception -> L24
        if (r44 != null) goto L20;
    L22:
        ((LaunchActivity) this.parentActivity).showBulletin(new ShareAlert$$ExternalSyntheticLambda20(r0));     // Catch: Exception -> L24
        return;
    L20:
        if (r44.link.contains("/c/") == false) goto L22;
        r0 = true;
        goto L22
    L30:
        return;
    L24:
        e = move-exception;
        FileLog.e(e);
        return;
    L5:
        if (this.linkToCopy[0] != null) goto L27;
    }

    public static ShareAlert createShareAlert(Context r12, MessageObject r13, String r14, boolean r15, String r16, boolean r17) {
        if (r13 == null) goto L5;
        ArrayList r0 = new ArrayList();
        r0.add(r13);
    L7:
        return new ShareAlert(r12, null, r0, r14, null, r15, r16, null, r17, false);
    L5:
        r0 = null;
        goto L7
    }

    private void dismissSendPopupWindow() {
        ActionBarPopupWindow r0 = this.sendPopupWindow;
        if (r0 != null) goto L5;
        return;
    L5:
        this.sendPopupWindow = null;
        r0.setOnDismissListener(null);
        r0.dismiss(false);     // Catch: Exception -> L8
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
    }

    private int getCurrentTop() {
        if (this.gridView.getChildCount() == 0) goto L13;
        int r1 = 0;
        View r0 = this.gridView.getChildAt(0);
        RecyclerListView.Holder r2 = (RecyclerListView.Holder) this.gridView.findContainingViewHolder(r0);
        if (r2 == null) goto L15;
        int r3 = this.gridView.getPaddingTop();
        if (r2.getLayoutPosition() != 0) goto L12;
        if (r0.getTop() < 0) goto L12;
        r1 = r0.getTop();
    L12:
        return r3 - r1;
    L15:
        return -1000;
    L13:
        return -1000;
    }

    private String getLink() {
        SwitchView r0 = this.switchView;
        if (r0 == null) goto L5;
        String r02 = this.linkToCopy[SwitchView.m12753$$Nest$fgetcurrentTab(r0)];
    L11:
        CheckBox2 r1 = this.timestampCheckbox;
        if (r1 != null) goto L14;
    L19:
        if (r02 != null) goto L22;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L22:
        return r02;
    L14:
        if (r1.isChecked() == false) goto L19;
        r02 = Uri.parse(r02).buildUpon().appendQueryParameter("t", AndroidUtilities.formatTimestamp(this.timestamp)).build().toString();     // Catch: Exception -> L17
    L17:
        e = move-exception;
        FileLog.e(e);
        goto L19
    L5:
        TLRPC.TL_exportedMessageLink r03 = this.exportedMessageLink;
        if (r03 == null) goto L8;
        r02 = r03.link;
    L9:
        if (r02 != null) goto L11;
        r02 = this.linkToCopy[0];
        goto L11
    L8:
        r02 = null;
        goto L9
    }

    private RecyclerListView getMainGridView() {
        if (this.searchIsVisible == true) goto L10;
        if (this.searchWasVisibleBeforeTopics == true) goto L10;
        return this.gridView;
    L10:
        return this.searchGridView;
    }

    private void invalidateTopicsAnimation(View r13, int[] r14, float r15) {
        this.topicsGridView.setPivotX(r13.getX() + (r13.getWidth() / 2.0f));
        this.topicsGridView.setPivotY(r13.getY() + (r13.getHeight() / 2.0f));
        float r1 = 0.25f * r15;
        float r2 = 0.75f + r1;
        this.topicsGridView.setScaleX(r2);
        this.topicsGridView.setScaleY(r2);
        this.topicsGridView.setAlpha(r15);
        RecyclerListView r0 = getMainGridView();
        r0.setPivotX(r13.getX() + (r13.getWidth() / 2.0f));
        r0.setPivotY(r13.getY() + (r13.getHeight() / 2.0f));
        float r12 = r1 + 1.0f;
        r0.setScaleX(r12);
        r0.setScaleY(r12);
        float r16 = 1.0f - r15;
        r0.setAlpha(r16);
        this.searchView.setPivotX(r4.getWidth() / 2.0f);
        this.searchView.setPivotY(0.0f);
        float r4 = (0.1f * r16) + 0.9f;
        this.searchView.setScaleX(r4);
        this.searchView.setScaleY(r4);
        this.searchView.setAlpha(r16);
        this.topicsBackActionBar.getBackButton().setTranslationX((-AndroidUtilities.dp(16.0f)) * r16);
        this.topicsBackActionBar.getTitleTextView().setTranslationY(AndroidUtilities.dp(16.0f) * r16);
        this.topicsBackActionBar.getSubtitleTextView().setTranslationY(AndroidUtilities.dp(16.0f) * r16);
        this.topicsBackActionBar.setAlpha(r15);
        this.topicsGridView.getLocationInWindow(r14);
        float r142 = CubicBezierInterpolator.EASE_OUT.getInterpolation(r15);
        int r3 = 0;
        int r42 = 0;
    L4:
        if (r42 >= r0.getChildCount()) goto L13;
        View r5 = r0.getChildAt(r42);
        if ((r5 instanceof ShareDialogCell) == false) goto L11;
        r5.setTranslationX(((r5.getX() - r13.getX()) * 0.5f) * r142);
        r5.setTranslationY(((r5.getY() - r13.getY()) * 0.5f) * r142);
        if (r5 == r13) goto L10;
        r5.setAlpha(1.0f - (Math.min(r15, 0.5f) / 0.5f));
        goto L11
    L10:
        r5.setAlpha(r16);
    L11:
        r42 = r42 + 1;
    L13:
        if (r3 >= this.topicsGridView.getChildCount()) goto L18;
        View r152 = this.topicsGridView.getChildAt(r3);
        if ((r152 instanceof ShareTopicCell) == false) goto L17;
        double r6 = 1.0f - r142;
        r152.setTranslationX((float) ((-(r152.getX() - r13.getX())) * Math.pow(r6, 2.0d)));
        r152.setTranslationY((float) ((-((r152.getY() + this.topicsGridView.getTranslationY()) - r13.getY())) * Math.pow(r6, 2.0d)));
    L17:
        r3 = r3 + 1;
        goto L13
    L18:
        this.containerView.requestLayout();
        r0.invalidate();
    }

    private boolean isBotForumWithNotEmptyTopics(TLRPC.User r5) {
        if (UserObject.isBotForum(r5) == true) goto L5;
        return false;
    L5:
        ArrayList<TLRPC.TL_forumTopic> r0 = MessagesController.getInstance(this.currentAccount).getTopicsController().getTopics(-r5.id);
        if (r0 == null) goto L10;
        if (r0.isEmpty() == true) goto L10;
        return true;
    L10:
        if (MessagesController.getInstance(this.currentAccount).getTopicsController().endIsReached(-r5.id) == false) goto L14;
        return false;
    L14:
        return true;
    }

    private /* synthetic */ void lambda$collapseTopics$22(View r1, int[] r2, DynamicAnimation r3, float r4, float r5) {
        invalidateTopicsAnimation(r1, r2, r4 / 1000.0f);
    }

    private /* synthetic */ void lambda$collapseTopics$23(DynamicAnimation r1, boolean r2, float r3, float r4) {
        this.topicsGridView.setVisibility(8);
        this.topicsBackActionBar.setVisibility(8);
        ShareTopicsAdapter.m12752$$Nest$fputtopics(this.shareTopicsAdapter, null);
        this.shareTopicsAdapter.notifyDataSetChanged();
        this.topicsAnimation = null;
        this.searchWasVisibleBeforeTopics = false;
    }

    private /* synthetic */ void lambda$new$0(TLObject r1, Context r2) {
        if (r1 == null) goto L6;
        this.exportedMessageLink = (TLRPC.TL_exportedMessageLink) r1;
        updateLinkTextView();
        if (this.copyLinkOnEnd == false) goto L6;
        copyLink(r2);
    L6:
        this.loadingLink = false;
    }

    private /* synthetic */ void lambda$new$1(Context r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new ShareAlert$$ExternalSyntheticLambda31(this, r2, r1));
    }

    private /* synthetic */ void lambda$new$10(View r3) {
        if (this.selectedDialogs.size() == 0) goto L5;
        return;
    L5:
        if (this.isChannel == false) goto L7;
    L8:
        dismiss();
        PhotoViewer.getInstance().closePhoto(true, false);
        if (this.linkToCopy[0] == null) goto L11;
    L14:
        copyLink(getContext());
        return;
    L11:
        if (this.loadingLink == false) goto L14;
        this.copyLinkOnEnd = true;
        Toast.makeText(getContext(), LocaleController.getString(R.string.Loading), 0).show();
        return;
    L7:
        if (this.linkToCopy[0] != null) goto L8;
    }

    private /* synthetic */ void lambda$new$11(View r2) {
        if (this.selectedDialogs.size() == 0) goto L5;
        return;
    L5:
        if (this.isChannel == false) goto L7;
    L8:
        dismiss();
        if (this.linkToCopy[0] == null) goto L11;
    L14:
        copyLink(getContext());
        return;
    L11:
        if (this.loadingLink == false) goto L14;
        this.copyLinkOnEnd = true;
        Toast.makeText(getContext(), LocaleController.getString(R.string.Loading), 0).show();
        return;
    L7:
        if (this.linkToCopy[0] != null) goto L8;
    }

    private /* synthetic */ void lambda$new$12(MessageObject r1, View r2) {
        BaseFragment r22 = this.parentFragment;
        if (r22 != null) goto L5;
        r22 = LaunchActivity.getSafeLastFragment();
    L5:
        if (r22 != null) goto L7;
        return;
    L7:
        dismiss();
        r22.presentFragment(new MessageStatisticActivity(r1));
    }

    private /* synthetic */ void lambda$new$16(View r1) {
        sendInternal(true);
    }

    private /* synthetic */ boolean lambda$new$17(View r1) {
        return onSendLongClick(this.writeButton);
    }

    private /* synthetic */ void lambda$new$18(View r3) {
        this.timestampCheckbox.setChecked(!r3.isChecked(), true);
        updateLinkTextView();
    }

    private /* synthetic */ boolean lambda$new$2(TextView r1, int r2, KeyEvent r3) {
        if (r3 != null) goto L4;
        return false;
    L4:
        if (r3.getAction() != 1) goto L8;
        if (r3.getKeyCode() != 84) goto L8;
    L11:
        AndroidUtilities.hideKeyboard(this.searchView.editText);
        return false;
    L8:
        if (r3.getAction() == 0) goto L10;
        return false;
    L10:
        if (r3.getKeyCode() == 66) goto L11;
        return false;
    }

    private /* synthetic */ void lambda$new$3() {
        this.updateSearchAdapter = true;
        this.searchView.editText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        AndroidUtilities.showKeyboard(this.searchView.editText);
    }

    private /* synthetic */ void lambda$new$5(View r1, int r2) {
        if (ShareTopicsAdapter.m12748$$Nest$fgetisBotForum(this.shareTopicsAdapter) == true) goto L5;
    L8:
        TLRPC.TL_forumTopic r12 = this.shareTopicsAdapter.getItemTopic(r2);
        if (r12 == null) goto L12;
        onTopicCellClick(r12);
        return;
    L12:
        return;
    L5:
        if (r2 != 1) goto L8;
        onTopicCreateCellClick();
    }

    private /* synthetic */ void lambda$new$7(View r2, int r3) {
        if (r3 < 0) goto L9;
        TLRPC.Dialog r32 = this.listAdapter.getItem(r3);
        if (r32 != null) goto L7;
        return;
    L7:
        selectDialog(r2, r32);
        return;
    }

    private /* synthetic */ void lambda$new$9(View r2, int r3) {
        if (r3 < 0) goto L9;
        TLRPC.Dialog r32 = this.searchAdapter.getItem(r3);
        if (r32 != null) goto L7;
        return;
    L7:
        selectDialog(r2, r32);
        return;
    }

    private /* synthetic */ void lambda$onSendLongClick$24(KeyEvent r3) {
        if (r3.getKeyCode() == 4) goto L5;
        return;
    L5:
        if (r3.getRepeatCount() != 0) goto L13;
        ActionBarPopupWindow r32 = this.sendPopupWindow;
        if (r32 != null) goto L9;
        return;
    L9:
        if (r32.isShowing() == false) goto L15;
        this.sendPopupWindow.dismiss();
        return;
    L15:
        return;
    }

    private /* synthetic */ void lambda$onSendLongClick$25(MessagePreviewView.ToggleButton r2, MessagePreviewView.ToggleButton r3, View r4) {
        boolean r42 = this.hideSendersName;
        this.hideSendersName = !r42;
        if (r42 == false) goto L7;
        this.hideCaption = false;
        if (r2 == null) goto L7;
        r2.setState(false, true);
    L7:
        r3.setState(this.hideSendersName, true);
    }

    private /* synthetic */ void lambda$onSendLongClick$26(MessagePreviewView.ToggleButton r3, MessagePreviewView.ToggleButton r4, View r5) {
        boolean r52 = this.hideCaption;
        boolean r0 = !r52;
        this.hideCaption = r0;
        if (r52 == false) goto L5;
        this.hideSendersName = false;
    L8:
        r3.setState(r0, true);
        r4.setState(this.hideSendersName, true);
        return;
    L5:
        if (this.hideSendersName == true) goto L8;
        this.hideSendersName = true;
        goto L8
    }

    private /* synthetic */ void lambda$onSendLongClick$27(KeyEvent r3) {
        if (r3.getKeyCode() == 4) goto L5;
        return;
    L5:
        if (r3.getRepeatCount() != 0) goto L13;
        ActionBarPopupWindow r32 = this.sendPopupWindow;
        if (r32 != null) goto L9;
        return;
    L9:
        if (r32.isShowing() == false) goto L15;
        this.sendPopupWindow.dismiss();
        return;
    L15:
        return;
    }

    private /* synthetic */ void lambda$onSendLongClick$28(View r1) {
        ActionBarPopupWindow r12 = this.sendPopupWindow;
        if (r12 != null) goto L5;
    L7:
        sendInternal(false);
        return;
    L5:
        if (r12.isShowing() == false) goto L7;
        this.sendPopupWindow.dismiss();
        goto L7
    }

    private /* synthetic */ void lambda$onSendLongClick$29(boolean r1, int r2, int r3) {
        sendInternal(r1, r2, r3);
    }

    private /* synthetic */ void lambda$onSendLongClick$30(View r4) {
        dismissSendPopupWindow();
        AlertsCreator.createScheduleDatePickerDialog(getContext(), -1, new ShareAlert$$ExternalSyntheticLambda33(this), this.resourcesProvider);
    }

    private /* synthetic */ void lambda$onSendLongClick$31(View r1) {
        ActionBarPopupWindow r12 = this.sendPopupWindow;
        if (r12 != null) goto L5;
    L7:
        sendInternal(true);
        return;
    L5:
        if (r12.isShowing() == false) goto L7;
        this.sendPopupWindow.dismiss();
        goto L7
    }

    private /* synthetic */ void lambda$onSendLongClick$32(ActionBarPopupWindow r2) {
        if (this.sendPopupWindow != r2) goto L6;
        this.sendPopupWindow = null;
        return;
    }

    private /* synthetic */ void lambda$selectDialog$21(AtomicReference r3, NotificationCenter.NotificationCenterDelegate r4, TLRPC.Dialog r5) {
        r3.set(null);
        r4.didReceivedNotification(NotificationCenter.topicsDidLoaded, this.currentAccount, new Object[]{Long.valueOf(-r5.id)});
    }

    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v22, types: [boolean] */
    /* JADX WARN: Type inference failed for: r8v28 */
    private /* synthetic */ void lambda$sendInternal$33(CharSequence[] r59, ArrayList r60, boolean r61, int r62, int r63, int r64, HashMap r65) {
        long r2 = 0;
        TLRPC.TL_forumTopic r6 = null;
        int r7 = 1;
        ?? r8 = 0;
        if (this.sendingMessageObjects == null) goto L64;
        ArrayList r5 = new ArrayList();
        int r9 = 0;
        boolean r10 = false;
    L6:
        if (r9 >= this.selectedDialogs.size()) goto L53;
        long r13 = this.selectedDialogs.keyAt(r9);
        boolean r11 = MessagesController.getInstance(this.currentAccount).isMonoForum(r13);
        if (r65 != null) goto L10;
        Long r27 = 0L;
    L11:
        if (r27 != null) goto L13;
    L15:
        TLRPC.TL_forumTopic r12 = this.selectedDialogTopics.get(this.selectedDialogs.get(r13));
        if (r12 == null) goto L19;
        if (r11 == false) goto L19;
        long r28 = r2;
        r2 = DialogObject.getPeerDialogId(r12.from_id);
    L20:
        if (r12 == null) goto L23;
        if (r11 == true) goto L23;
        MessageObject r15 = new MessageObject(this.currentAccount, r12.topicStartMessage, r8, r8);
    L24:
        if (r15 == null) goto L27;
        r15.isTopicMainMessage = true;
    L27:
        if (this.frameLayout2.getTag() != null) goto L29;
    L39:
        int r30 = r9;
    L40:
        SendMessagesHelper r122 = SendMessagesHelper.getInstance(this.currentAccount);
        MessageObject r21 = r15;
        ArrayList<MessageObject> r132 = this.sendingMessageObjects;
        boolean r82 = this.hideSendersName;
        boolean r92 = this.hideCaption;
        if (r27 != null) goto L43;
        long r23 = r28;
    L44:
        int r22 = r122.sendMessage(r132, r13, r82, r92, r61, r62, r63, r21, r64, r23, r2, null);
        if (r22 == 0) goto L48;
        r5.add(Long.valueOf(r13));
    L48:
        if (this.selectedDialogs.size() != 1) goto L52;
        AlertsCreator.showSendMediaAlert(r22, this.parentFragment, null);
        if (r22 != 0) goto L53;
    L52:
        r9 = r30 + 1;
        r2 = r28;
        r8 = 0;
        goto L6
    L43:
        r23 = r27.longValue();
        goto L44
    L29:
        if (this.commentTextView.length() <= 0) goto L39;
        CharSequence r112 = r59[r8];
        if (r112 != null) goto L33;
        String r123 = null;
    L34:
        SendMessagesHelper.SendMessageParams r113 = SendMessagesHelper.SendMessageParams.of(r123, r13, r15, r15, null, true, r60, null, null, r61, r62, r63, null, false);
        if (r27 != null) goto L37;
        r30 = r9;
        long r83 = r28;
    L38:
        r113.payStars = r83;
        r113.monoForumPeer = r2;
        SendMessagesHelper.getInstance(this.currentAccount).sendMessage(r113);
        goto L40
    L37:
        r30 = r9;
        r83 = r27.longValue();
        goto L38
    L33:
        r123 = r112.toString();
    L23:
        r15 = null;
    L19:
        r28 = r2;
        goto L20
    L13:
        if (r27.longValue() <= r2) goto L15;
        r10 = true;
        goto L15
    L10:
        r27 = (Long) r65.get(Long.valueOf(r13));
    L53:
        int r1 = r5.size();
        int r24 = 0;
    L54:
        if (r24 >= r1) goto L59;
        Object r3 = r5.get(r24);
        r24 = r24 + 1;
        long r32 = ((Long) r3).longValue();
        TLRPC.Dialog r84 = this.selectedDialogs.get(r32);
        this.selectedDialogs.remove(r32);
        if (r84 == null) goto L54;
        this.selectedDialogTopics.remove(r84);
        goto L54
    L59:
        if (this.selectedDialogs.isEmpty() == true) goto L175;
        LongSparseArray<TLRPC.Dialog> r14 = this.selectedDialogs;
        int r25 = this.sendingMessageObjects.size();
        if (this.selectedDialogs.size() != 1) goto L63;
        r6 = this.selectedDialogTopics.get(this.selectedDialogs.valueAt(0));
    L63:
        onSend(r14, r25, r6, !r10);
    L175:
        ShareAlertDelegate r16 = this.delegate;
        if (r16 == null) goto L178;
        r16.didShare();
    L178:
        dismiss();
        return;
    L64:
        SwitchView r26 = this.switchView;
        if (r26 == null) goto L67;
        int r29 = SwitchView.m12753$$Nest$fgetcurrentTab(r26);
    L69:
        if (this.storyItem == null) goto L116;
        int r33 = 0;
        int r52 = 0;
    L72:
        if (r33 >= this.selectedDialogs.size()) goto L114;
        long r133 = this.selectedDialogs.keyAt(r33);
        boolean r85 = MessagesController.getInstance(this.currentAccount).isMonoForum(r133);
        if (r65 != null) goto L76;
        Long r93 = 0L;
    L77:
        if (r93 != null) goto L79;
    L81:
        TLRPC.TL_forumTopic r102 = this.selectedDialogTopics.get(this.selectedDialogs.get(r133));
        if (r102 == null) goto L85;
        if (r85 == false) goto L85;
        long r114 = DialogObject.getPeerDialogId(r102.from_id);
    L86:
        if (r102 == null) goto L89;
        if (r85 == true) goto L89;
        MessageObject r152 = new MessageObject(this.currentAccount, r102.topicStartMessage, false, false);
    L90:
        TL_stories.StoryItem r66 = this.storyItem;
        FrameLayout r86 = this.frameLayout2;
        if (r66 == null) goto L93;
        long r103 = r114;
        if (r86.getTag() != null) goto L105;
    L109:
        SendMessagesHelper.SendMessageParams r67 = SendMessagesHelper.SendMessageParams.of(null, r133, r152, r152, null, true, null, null, null, r61, r62, r63, null, false);
        r67.sendingStory = this.storyItem;
    L110:
        if (r93 != null) goto L112;
        long r87 = 0;
    L113:
        r67.payStars = r87;
        r67.monoForumPeer = r103;
        SendMessagesHelper.getInstance(this.currentAccount).sendMessage(r67);
        r33 = r33 + 1;
        goto L72
    L112:
        r87 = r93.longValue();
        goto L113
    L105:
        if (this.commentTextView.length() <= 0) goto L109;
        if (r59[0] == null) goto L109;
        MessageObject r162 = r152;
        r152 = r162;
        SendMessagesHelper.getInstance(this.currentAccount).sendMessage(SendMessagesHelper.SendMessageParams.of(r59[0].toString(), r133, null, r162, null, true, null, null, null, r61, r62, r63, null, false));
        goto L109
    L93:
        if (r86.getTag() != null) goto L95;
    L101:
        r103 = r114;
        r67 = SendMessagesHelper.SendMessageParams.of(this.sendingText[r29], r133, r152, r152, null, true, null, null, null, r61, r62, r63, null, false);
        goto L110
    L95:
        if (this.commentTextView.length() <= 0) goto L101;
        CharSequence r68 = r59[0];
        if (r68 != null) goto L99;
        String r302 = null;
    L100:
        r67 = SendMessagesHelper.SendMessageParams.of(r302, r133, r152, r152, null, true, r60, null, null, r61, r62, r63, null, false);
        r103 = r114;
        goto L110
    L99:
        r302 = r68.toString();
    L89:
        r152 = null;
    L85:
        r114 = 0;
        goto L86
    L79:
        if (r93.longValue() <= 0) goto L81;
        r52 = 1;
        goto L81
    L76:
        r93 = (Long) r65.get(Long.valueOf(r133));
    L114:
        int r94 = r7;
    L174:
        LongSparseArray<TLRPC.Dialog> r17 = this.selectedDialogs;
        onSend(r17, r94, this.selectedDialogTopics.get(r17.valueAt(0)), r52 ^ 1);
        goto L175
    L116:
        if (this.sendingText[r29] == null) goto L156;
        int r34 = 0;
        r52 = 0;
    L119:
        if (r34 >= this.selectedDialogs.size()) goto L114;
        long r134 = this.selectedDialogs.keyAt(r34);
        boolean r69 = MessagesController.getInstance(this.currentAccount).isMonoForum(r134);
        if (r65 != null) goto L123;
        Long r88 = 0L;
    L124:
        if (r88 != null) goto L126;
    L128:
        TLRPC.TL_forumTopic r95 = this.selectedDialogTopics.get(this.selectedDialogs.get(r134));
        if (r95 == null) goto L132;
        if (r69 == false) goto L132;
        long r104 = DialogObject.getPeerDialogId(r95.from_id);
    L133:
        if (r95 == null) goto L136;
        if (r69 == true) goto L136;
        char r153 = 0;
        MessageObject r332 = new MessageObject(this.currentAccount, r95.topicStartMessage, false, false);
    L138:
        if (this.frameLayout2.getTag() != null) goto L140;
    L150:
        int r96 = r7;
        Long r642 = r88;
        MessageObject r154 = r332;
    L151:
        SendMessagesHelper.SendMessageParams r610 = SendMessagesHelper.SendMessageParams.of(this.sendingText[r29], r134, r154, r154, null, true, null, null, null, r61, r62, r63, null, false);
        if (r642 != null) goto L154;
        long r72 = 0;
    L155:
        r610.payStars = r72;
        r610.monoForumPeer = r104;
        SendMessagesHelper.getInstance(this.currentAccount).sendMessage(r610);
        r34 = r34 + 1;
        r7 = r96;
        goto L119
    L154:
        r72 = r642.longValue();
        goto L155
    L140:
        if (this.commentTextView.length() <= 0) goto L150;
        CharSequence r611 = r59[r153];
        if (r611 != null) goto L144;
        String r303 = null;
    L145:
        SendMessagesHelper.SendMessageParams r612 = SendMessagesHelper.SendMessageParams.of(r303, r134, r332, r332, null, true, r60, null, null, r61, r62, r63, null, false);
        r154 = r332;
        if (r88 != null) goto L148;
        r96 = r7;
        r642 = r88;
        long r73 = 0;
    L149:
        r612.payStars = r73;
        r612.monoForumPeer = r104;
        SendMessagesHelper.getInstance(this.currentAccount).sendMessage(r612);
        goto L151
    L148:
        r96 = r7;
        r642 = r88;
        r73 = r88.longValue();
        goto L149
    L144:
        r303 = r611.toString();
    L136:
        r153 = 0;
        r332 = null;
    L132:
        r104 = 0;
        goto L133
    L126:
        if (r88.longValue() <= 0) goto L128;
        r52 = r7;
        goto L128
    L123:
        r88 = (Long) r65.get(Long.valueOf(r134));
        goto L124
    L156:
        r94 = 1;
        if (this.sendingFile == null) goto L173;
        int r53 = 0;
    L160:
        if (r53 >= this.selectedDialogs.size()) goto L173;
        long r18 = this.selectedDialogs.keyAt(r53);
        TLRPC.TL_forumTopic r35 = this.selectedDialogTopics.get(this.selectedDialogs.get(r18));
        if (r35 == null) goto L164;
        char r155 = 0;
        MessageObject r40 = new MessageObject(this.currentAccount, r35.topicStartMessage, false, false);
    L166:
        if (this.frameLayout2.getTag() == null) goto L172;
        if (this.commentTextView.length() <= 0) goto L172;
        if (r59[r155] == null) goto L172;
        File r36 = new File(this.sendingFile);
        String r4 = r36.getParent() + "/" + PreferencesUtils.generateBackupName(r59[r155].toString());
        this.sendingFile = r4;
        r36.renameTo(new File(r4));
    L172:
        ArrayList r37 = new ArrayList(ImageCapture$$ExternalSyntheticBackport0.m(this.sendingFile));
        SendMessagesHelper.prepareSendingDocuments(AccountInstance.getInstance(this.currentAccount), r37, r37, null, null, null, null, r18, null, r40, null, null, null, r61, r62, r63, null, null, 0, false, 0, 0, null);
        r53 = r53 + 1;
        goto L160
    L164:
        r155 = 0;
        r40 = null;
    L173:
        r52 = 0;
        goto L174
    L67:
        r29 = 0;
        goto L69
    }

    private /* synthetic */ void lambda$showPremiumBlockedToast$20() {
        ShareAlert$$ExternalSyntheticLambda34 r0 = new ShareAlert$$ExternalSyntheticLambda34();
        if (isKeyboardVisible() == false) goto L9;
        FragmentSearchField r3 = this.searchView;
        if (r3 == null) goto L7;
        AndroidUtilities.hideKeyboard(r3.editText);
    L7:
        AndroidUtilities.runOnUIThread(r0, 300);
        return;
    L9:
        r0.run();
    }

    private WindowInsetsCompat onApplyWindowInsets(View r1, WindowInsetsCompat r2) {
        processLegacyContainerInsets(r2.toWindowInsets());
        Insets r12 = r2.getInsets(WindowInsetsCompat.Type.systemBars());
        if (this.systemInsets.equals(r12) == true) goto L6;
        this.systemInsets = r12;
        this.container.requestLayout();
    L6:
        return WindowInsetsCompat.CONSUMED;
    }

    private boolean onSendLongClick(View r21) {
        if (this.parentActivity != null) goto L5;
        return false;
    L5:
        dismissSendPopupWindow();
        LinearLayout r8 = new LinearLayout(getContext());
        r8.setOrientation(1);
        if (this.sendingMessageObjects == null) goto L65;
        AnonymousClass28 r0 = new AnonymousClass28(this, this.parentActivity, R.drawable.popup_fixed_alert3, this.resourcesProvider);
        if (this.darkTheme == false) goto L10;
        r0.setBackgroundColor(getThemedColor(Theme.key_voipgroup_inviteMembersBackground));
    L10:
        r0.setAnimationEnabled(false);
        r0.setOnTouchListener(new AnonymousClass29(this));
        r0.setDispatchKeyEventListener(new ShareAlert$$ExternalSyntheticLambda22(this));
        r0.setShownFromBottom(false);
        ArrayList r2 = new ArrayList();
        int r3 = 0;
        boolean r4 = false;
    L12:
        if (r3 >= this.sendingMessageObjects.size()) goto L24;
        MessageObject r5 = this.sendingMessageObjects.get(r3);
        if (TextUtils.isEmpty(r5.caption) == true) goto L16;
        r4 = true;
    L16:
        TLRPC.MessageFwdHeader r52 = r5.messageOwner.fwd_from;
        if (r52 == null) goto L23;
        if (r52.from_id != null) goto L23;
        if (r2.contains(r52.from_name) == true) goto L23;
        r2.add(r52.from_name);
    L23:
        r3 = r3 + 1;
        goto L12
    L24:
        ArrayList r32 = new ArrayList();
        int r53 = 0;
    L26:
        if (r53 >= this.sendingMessageObjects.size()) goto L44;
        MessageObject r12 = this.sendingMessageObjects.get(r53);
        if (r12.isFromUser() == false) goto L30;
        long r122 = r12.messageOwner.from_id.user_id;
    L40:
        if (r32.contains(Long.valueOf(r122)) == true) goto L42;
        r32.add(Long.valueOf(r122));
    L42:
        r53 = r53 + 1;
        goto L26
    L30:
        TLRPC.Chat r13 = MessagesController.getInstance(r12.currentAccount).getChat(Long.valueOf(r12.messageOwner.peer_id.channel_id));
        if (ChatObject.isChannel(r13) == true) goto L33;
    L38:
        long r123 = r12.messageOwner.peer_id.channel_id;
    L37:
        r122 = -r123;
        goto L40
    L33:
        if (r13.megagroup == false) goto L38;
        if (r12.isForwardedChannelPost() == false) goto L38;
        r123 = r12.messageOwner.fwd_from.from_id.channel_id;
        goto L37
    L44:
        if ((r32.size() + r2.size()) <= 1) goto L46;
        boolean r22 = true;
    L47:
        Activity r132 = this.parentActivity;
        int r14 = R.raw.name_hide;
        if (r22 == false) goto L51;
        int r33 = R.string.ShowSenderNames;
    L50:
        String r34 = LocaleController.getString(r33);
        int r16 = R.raw.name_show;
        if (r22 == false) goto L56;
        int r23 = R.string.HideSenderNames;
    L57:
        MessagePreviewView.ToggleButton r124 = new MessagePreviewView.ToggleButton(r132, r14, r34, r16, LocaleController.getString(r23), this.resourcesProvider);
        applyDarkTheme(r124);
        r0.addView(r124, LayoutHelper.createLinear(-1, 48));
        if (r4 == false) goto L60;
        MessagePreviewView.ToggleButton r133 = new MessagePreviewView.ToggleButton(this.parentActivity, R.raw.caption_hide, LocaleController.getString(R.string.ShowCaption), R.raw.caption_show, LocaleController.getString(R.string.HideCaption), this.resourcesProvider);
        applyDarkTheme(r133);
        r133.setState(this.hideCaption, false);
        r0.addView(r133, LayoutHelper.createLinear(-1, 48));
    L61:
        r124.setState(this.hideSendersName, false);
        r124.setOnClickListener(new ShareAlert$$ExternalSyntheticLambda23(this, r133, r124));
        if (r133 == null) goto L64;
        r133.setOnClickListener(new ShareAlert$$ExternalSyntheticLambda24(this, r133, r124));
    L64:
        r8.addView(r0, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, -8.0f));
        goto L65
    L60:
        r133 = null;
        goto L61
    L56:
        r23 = R.string.HideSendersName;
        goto L57
    L51:
        r33 = R.string.ShowSendersName;
        goto L50
    L46:
        r22 = false;
    L65:
        ActionBarPopupWindow.ActionBarPopupWindowLayout r125 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(this.parentActivity, this.resourcesProvider);
        if (this.darkTheme == false) goto L68;
        r125.setBackgroundColor(getThemedColor(Theme.key_voipgroup_inviteMembersBackground));
    L68:
        r125.setAnimationEnabled(false);
        r125.setOnTouchListener(new AnonymousClass30(this));
        r125.setDispatchKeyEventListener(new ShareAlert$$ExternalSyntheticLambda25(this));
        r125.setShownFromBottom(false);
        if (this.commentTextView.getText() != null) goto L71;
    L73:
        ActionBarMenuSubItem r02 = new ActionBarMenuSubItem(getContext(), true, true, this.resourcesProvider);
        applyDarkTheme(r02);
        r02.setTextAndIcon(LocaleController.getString(R.string.SendWithoutSound), R.drawable.input_notify_off);
        r02.setMinimumWidth(AndroidUtilities.dp(196.0f));
        r125.addView(r02, LayoutHelper.createLinear(-1, 48));
        r02.setOnClickListener(new ShareAlert$$ExternalSyntheticLambda26(this));
        ActionBarMenuSubItem r03 = new ActionBarMenuSubItem(getContext(), true, true, this.resourcesProvider);
        applyDarkTheme(r03);
        r03.setTextAndIcon(LocaleController.getString(R.string.ScheduleMessage), R.drawable.msg_calendar2);
        r125.addView(r03, LayoutHelper.createLinear(-1, 48));
        r03.setOnClickListener(new ShareAlert$$ExternalSyntheticLambda27(this));
        ActionBarMenuSubItem r04 = new ActionBarMenuSubItem(getContext(), true, true, this.resourcesProvider);
        applyDarkTheme(r04);
        r04.setTextAndIcon(LocaleController.getString(R.string.ForwardSendMessages), R.drawable.msg_send);
        r125.addView(r04, LayoutHelper.createLinear(-1, 48));
        r04.setOnClickListener(new ShareAlert$$ExternalSyntheticLambda28(this));
        if (this.darkTheme == false) goto L76;
        int r05 = Theme.key_voipgroup_listSelector;
    L77:
        r125.setupRadialSelectors(getThemedColor(r05));
        r8.addView(r125, LayoutHelper.createLinear(-1, -2));
        ActionBarPopupWindow r24 = new ActionBarPopupWindow(r8, -2, -2);
        this.sendPopupWindow = r24;
        r24.setAnimationEnabled(false);
        this.sendPopupWindow.setAnimationStyle(R.style.PopupContextAnimation2);
        this.sendPopupWindow.setOutsideTouchable(true);
        this.sendPopupWindow.setClippingEnabled(true);
        this.sendPopupWindow.setInputMethodMode(2);
        this.sendPopupWindow.setSoftInputMode(0);
        this.sendPopupWindow.getContentView().setFocusableInTouchMode(true);
        this.sendPopupWindow.setOnDismissListener(new ShareAlert$$ExternalSyntheticLambda29(this, r24));
        SharedConfig.removeScheduledOrNoSoundHint();
        r8.measure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(1000.0f), Integer.MIN_VALUE));
        this.sendPopupWindow.setFocusable(true);
        int[] r06 = new int[2];
        r21.getLocationInWindow(r06);
        if (this.keyboardVisible == false) goto L84;
        ChatActivity r25 = this.parentFragment;
        if (r25 == null) goto L84;
        if (r25.contentView.getMeasuredHeight() <= AndroidUtilities.dp(58.0f)) goto L84;
        int r26 = r06[1] + r21.getMeasuredHeight();
    L85:
        this.sendPopupWindow.showAtLocation(r21, 51, ((r06[0] + r21.getMeasuredWidth()) - r8.getMeasuredWidth()) + AndroidUtilities.dp(8.0f), r26);
        this.sendPopupWindow.dimBehind();
        r21.performHapticFeedback(3, 2);     // Catch: Exception -> L88
    L87:
        return true;
    L84:
        r26 = (r06[1] - r8.getMeasuredHeight()) - AndroidUtilities.dp(2.0f);
        goto L85
    L76:
        r05 = Theme.key_dialogButtonSelector;
        goto L77
    L71:
        if (this.commentTextView.getText().toString().trim().length() == 0) goto L73;
        AnonymousClass31 r07 = new AnonymousClass31(this, getContext(), true, true, this.resourcesProvider);
        applyDarkTheme(r07);
        r125.addView(r07, LayoutHelper.createLinear(-1, 48));
        goto L73
    }

    private void onTopicCellClick(TLRPC.TL_forumTopic r10) {
        if (r10 == null) goto L29;
        TLRPC.Dialog r0 = this.selectedTopicDialog;
        if (r0 == null) goto L30;
        long r02 = r0.id;
        boolean r2 = MessagesController.getInstance(this.currentAccount).isMonoForum(r02);
        TLRPC.Dialog r3 = this.selectedTopicDialog;
        this.selectedDialogs.put(r02, r3);
        this.selectedDialogTopics.put(r3, r10);
        updateSelectedCount(2);
        int r1 = 0;
        if (this.searchIsVisible == true) goto L11;
        if (this.searchWasVisibleBeforeTopics == true) goto L11;
    L15:
        if (r1 >= getMainGridView().getChildCount()) goto L22;
        View r03 = getMainGridView().getChildAt(r1);
        if ((r03 instanceof ShareDialogCell) == false) goto L21;
        ShareDialogCell r04 = (ShareDialogCell) r03;
        if (r04.getCurrentDialog() != this.selectedTopicDialog.id) goto L21;
        r04.setTopic(r10, r2, true);
        r04.setChecked(true, true);
    L21:
        r1 = r1 + 1;
        goto L15
    L22:
        collapseTopics();
        return;
    L11:
        if (((TLRPC.Dialog) ShareDialogsAdapter.m12740$$Nest$fgetdialogsMap(this.listAdapter).get(r3.id)) != null) goto L13;
        ShareDialogsAdapter.m12740$$Nest$fgetdialogsMap(this.listAdapter).put(r3.id, r3);
        ShareDialogsAdapter.m12739$$Nest$fgetdialogs(this.listAdapter).add(!ShareDialogsAdapter.m12739$$Nest$fgetdialogs(this.listAdapter).isEmpty() ? 1 : 0, r3);
    L13:
        this.listAdapter.notifyDataSetChanged();
        this.updateSearchAdapter = false;
        this.searchView.editText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        checkCurrentList(false);
        goto L15
    L30:
        return;
    }

    private void onTopicCreateCellClick() {
        TLRPC.Dialog r0 = this.selectedTopicDialog;
        if (r0 != null) goto L5;
        return;
    L5:
        this.selectedDialogs.put(r0.id, r0);
        this.selectedDialogTopics.remove(r0);
        updateSelectedCount(2);
        if (this.searchIsVisible == true) goto L10;
        if (this.searchWasVisibleBeforeTopics == true) goto L10;
    L13:
        int r02 = 0;
    L15:
        if (r02 >= getMainGridView().getChildCount()) goto L22;
        View r1 = getMainGridView().getChildAt(r02);
        if ((r1 instanceof ShareDialogCell) == false) goto L21;
        ShareDialogCell r12 = (ShareDialogCell) r1;
        if (r12.getCurrentDialog() != this.selectedTopicDialog.id) goto L21;
        r12.setTopic(null, false, true);
        r12.setChecked(true, true);
    L21:
        r02 = r02 + 1;
        goto L15
    L22:
        collapseTopics();
        return;
    L10:
        if (((TLRPC.Dialog) ShareDialogsAdapter.m12740$$Nest$fgetdialogsMap(this.listAdapter).get(r0.id)) != null) goto L12;
        ShareDialogsAdapter.m12740$$Nest$fgetdialogsMap(this.listAdapter).put(r0.id, r0);
        ShareDialogsAdapter.m12739$$Nest$fgetdialogs(this.listAdapter).add(!ShareDialogsAdapter.m12739$$Nest$fgetdialogs(this.listAdapter).isEmpty() ? 1 : 0, r0);
    L12:
        this.listAdapter.notifyDataSetChanged();
        this.updateSearchAdapter = false;
        this.searchView.editText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        checkCurrentList(false);
        goto L13
    }

    private void runShadowAnimation(int r8, boolean r9) {
        if (r9 == true) goto L4;
    L5:
        if (r9 == false) goto L7;
        return;
    L7:
        if (this.shadow[r8].getTag() != null) goto L25;
    L8:
        View r0 = this.shadow[r8];
        if (r9 == false) goto L11;
        Integer r2 = null;
    L12:
        r0.setTag(r2);
        if (r9 == false) goto L15;
        this.shadow[r8].setVisibility(0);
    L15:
        AnimatorSet r22 = this.shadowAnimation[r8];
        if (r22 == null) goto L18;
        r22.cancel();
    L18:
        this.shadowAnimation[r8] = new AnimatorSet();
        AnimatorSet r23 = this.shadowAnimation[r8];
        View r3 = this.shadow[r8];
        Property r4 = View.ALPHA;
        if (r9 == false) goto L21;
        float r5 = 1.0f;
    L22:
        r23.playTogether(new Animator[]{ObjectAnimator.ofFloat(r3, r4, new float[]{r5})});
        this.shadowAnimation[r8].setDuration(150);
        this.shadowAnimation[r8].addListener(new AnonymousClass32(this, r8, r9));
        this.shadowAnimation[r8].start();
        return;
    L21:
        r5 = 0.0f;
        goto L22
    L11:
        r2 = 1;
        goto L12
    L25:
        return;
    L4:
        if (this.shadow[r8].getTag() != null) goto L8;
        goto L5
    }

    private void selectDialog(View r8, TLRPC.Dialog r9) {
        if ((r9 instanceof ShareDialogsAdapter.MyStoryDialog) == false) goto L6;
        onShareStory(r8);
        return;
    L6:
        if (r9 == null) goto L18;
        if ((r8 instanceof ShareDialogCell) == false) goto L12;
        if (((ShareDialogCell) r8).isBlocked() == false) goto L12;
    L15:
        showPremiumBlockedToast(r8, r9.id);
        return;
    L12:
        if ((r8 instanceof ProfileSearchCell) == false) goto L18;
        if (((ProfileSearchCell) r8).isBlocked() == true) goto L15;
    L18:
        if (this.topicsGridView.getVisibility() == 8) goto L20;
        return;
    L20:
        if (this.parentActivity != null) goto L23;
        return;
    L23:
        if (DialogObject.isChatDialog(r9.id) == false) goto L51;
        TLRPC.Chat r0 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r9.id));
        if (ChatObject.isChannel(r0) == false) goto L63;
        if (r0.megagroup == true) goto L63;
        if (ChatObject.isCanWriteToChannel(-r9.id, this.currentAccount) == false) goto L33;
        int r4 = this.hasPoll;
        if (r4 == 2) goto L33;
        if (r4 != 3) goto L63;
    L33:
        AlertDialog.Builder r82 = new AlertDialog.Builder(this.parentActivity);
        r82.setTitle(LocaleController.getString(R.string.SendMessageTitle));
        int r92 = this.hasPoll;
        if (r92 == 3) goto L36;
        if (r92 == 2) goto L41;
        r82.setMessage(LocaleController.getString(R.string.ChannelCantSendMessage));
    L48:
        r82.setNegativeButton(LocaleController.getString(R.string.OK), null);
        r82.show();
        return;
    L41:
        if (this.isChannel == false) goto L44;
        r82.setMessage(LocaleController.getString(R.string.PublicPollCantForward));
        goto L48
    L44:
        if (ChatObject.isActionBannedByDefault(r0, 10) == false) goto L46;
        r82.setMessage(LocaleController.getString(R.string.ErrorSendRestrictedPollsAll));
        goto L48
    L46:
        r82.setMessage(LocaleController.getString(R.string.ErrorSendRestrictedPolls));
        goto L48
    L36:
        if (ChatObject.isActionBannedByDefault(r0, 10) == false) goto L38;
        r82.setMessage(LocaleController.getString(R.string.ErrorSendRestrictedTodoAll));
        goto L48
    L38:
        r82.setMessage(LocaleController.getString(R.string.ErrorSendRestrictedTodo));
    L63:
        if (this.selectedDialogs.indexOfKey(r9.id) < 0) goto L71;
        this.selectedDialogs.remove(r9.id);
        this.selectedDialogTopics.remove(r9);
        if ((r8 instanceof ProfileSearchCell) == false) goto L68;
        ((ProfileSearchCell) r8).setChecked(false, true);
    L70:
        updateSelectedCount(1);
    L97:
        ShareSearchAdapter r7 = this.searchAdapter;
        if (r7 == null) goto L110;
        DialogsSearchAdapter.CategoryAdapterRecycler r72 = r7.categoryAdapter;
        if (r72 == null) goto L111;
        r72.notifyItemRangeChanged(0, r72.getItemCount());
        return;
    L111:
        return;
    L110:
        return;
    L68:
        if ((r8 instanceof ShareDialogCell) == false) goto L70;
        ((ShareDialogCell) r8).setChecked(false, true);
        goto L70
    L71:
        TLRPC.User r02 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r9.id));
        TLRPC.Chat r42 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r9.id));
        if (isBotForumWithNotEmptyTopics(r02) == false) goto L74;
    L103:
        this.selectedTopicDialog = r9;
        this.topicsLayoutManager.scrollToPositionWithOffset(0, this.scrollOffsetY - this.topicsGridView.getPaddingTop());
        AtomicReference r03 = new AtomicReference();
        AnonymousClass27 r1 = new AnonymousClass27(this, r9, r03, r8);
        r03.set(new ShareAlert$$ExternalSyntheticLambda21(this, r03, r1, r9));
        NotificationCenter r83 = NotificationCenter.getInstance(this.currentAccount);
        int r2 = NotificationCenter.topicsDidLoaded;
        r83.addObserver(r1, r2);
        ArrayList<TLRPC.TL_forumTopic> r84 = MessagesController.getInstance(this.currentAccount).getTopicsController().getTopics(-r9.id);
        int r73 = this.currentAccount;
        if (r84 == null) goto L107;
        r1.didReceivedNotification(r2, r73, new Object[]{Long.valueOf(-r9.id)});
        return;
    L107:
        MessagesController.getInstance(r73).getTopicsController().loadTopics(-r9.id);
        AndroidUtilities.runOnUIThread((Runnable) r03.get(), 300);
        return;
    L74:
        if (DialogObject.isChatDialog(r9.id) == true) goto L76;
    L82:
        this.selectedDialogs.put(r9.id, r9);
        if ((r8 instanceof ProfileSearchCell) == false) goto L86;
        ((ProfileSearchCell) r8).setChecked(true, true);
    L88:
        updateSelectedCount(2);
        long r3 = UserConfig.getInstance(this.currentAccount).clientUserId;
        if (this.searchIsVisible == false) goto L97;
        TLRPC.Dialog r85 = (TLRPC.Dialog) ShareDialogsAdapter.m12740$$Nest$fgetdialogsMap(this.listAdapter).get(r9.id);
        if (r85 != null) goto L94;
        ShareDialogsAdapter.m12740$$Nest$fgetdialogsMap(this.listAdapter).put(r9.id, r9);
        ShareDialogsAdapter.m12739$$Nest$fgetdialogs(this.listAdapter).add(!ShareDialogsAdapter.m12739$$Nest$fgetdialogs(this.listAdapter).isEmpty() ? 1 : 0, r9);
    L96:
        this.listAdapter.notifyDataSetChanged();
        this.updateSearchAdapter = false;
        this.searchView.editText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        checkCurrentList(false);
        AndroidUtilities.hideKeyboard(this.searchView.editText);
        goto L97
    L94:
        if (r85.id == r3) goto L96;
        ShareDialogsAdapter.m12739$$Nest$fgetdialogs(this.listAdapter).remove(r85);
        ShareDialogsAdapter.m12739$$Nest$fgetdialogs(this.listAdapter).add(!ShareDialogsAdapter.m12739$$Nest$fgetdialogs(this.listAdapter).isEmpty() ? 1 : 0, r85);
        goto L96
    L86:
        if ((r8 instanceof ShareDialogCell) == false) goto L88;
        ((ShareDialogCell) r8).setChecked(true, true);
        goto L88
    L76:
        if (ChatObject.isForum(r42) == true) goto L103;
        if (ChatObject.isMonoForum(r42) == false) goto L82;
        if (ChatObject.canManageMonoForum(this.currentAccount, r42) == false) goto L82;
    L51:
        if (DialogObject.isEncryptedDialog(r9.id) == false) goto L63;
        if (this.hasPoll == 0) goto L63;
        AlertDialog.Builder r86 = new AlertDialog.Builder(this.parentActivity);
        r86.setTitle(LocaleController.getString(R.string.SendMessageTitle));
        int r74 = this.hasPoll;
        if (r74 != 3) goto L57;
        r86.setMessage(LocaleController.getString(R.string.TodoCantForwardSecretChat));
    L60:
        r86.setNegativeButton(LocaleController.getString(R.string.OK), null);
        r86.show();
        return;
    L57:
        if (r74 == 0) goto L59;
        r86.setMessage(LocaleController.getString(R.string.PollCantForwardSecretChat));
        goto L60
    L59:
        r86.setMessage(LocaleController.getString(R.string.InvoiceCantForwardSecretChat));
        goto L60
    }

    private boolean showCommentTextView(boolean r12) {
        if (this.frameLayout2.getTag() == null) goto L5;
        boolean r0 = true;
    L6:
        if (r12 != r0) goto L8;
        return false;
    L8:
        AnimatorSet r02 = this.animatorSet;
        if (r02 == null) goto L11;
        r02.cancel();
    L11:
        FrameLayout r03 = this.frameLayout2;
        if (r12 == false) goto L14;
        Integer r3 = 1;
    L15:
        r03.setTag(r3);
        if (this.commentTextView.getEditText().isFocused() == false) goto L18;
        AndroidUtilities.hideKeyboard(this.commentTextView.getEditText());
    L18:
        this.commentTextView.hidePopup(true);
        if (r12 == false) goto L26;
        this.frameLayout2.setVisibility(0);
        FrameLayout r04 = this.timestampFrameLayout;
        if (r04 != null) goto L23;
    L25:
        this.writeButtonContainer.setVisibility(0);
    L29:
        FrameLayout r05 = this.pickerBottom;
        int r32 = 4;
        if (r05 == null) goto L35;
        if (r12 == false) goto L33;
        int r4 = 4;
    L34:
        ViewCompat.setImportantForAccessibility(r05, r4);
        goto L35
    L33:
        r4 = 1;
    L35:
        LinearLayout r06 = this.sharesCountLayout;
        if (r06 == null) goto L41;
        if (r12 == true) goto L40;
        r32 = 1;
    L40:
        ViewCompat.setImportantForAccessibility(r06, r32);
    L41:
        this.animatorSet = new AnimatorSet();
        ArrayList r07 = new ArrayList();
        FrameLayout r33 = this.frameLayout2;
        Property r42 = View.ALPHA;
        float r6 = 1.0f;
        if (r12 == false) goto L44;
        float r7 = 1.0f;
    L45:
        r07.add(ObjectAnimator.ofFloat(r33, r42, new float[]{r7}));
        FrameLayout r34 = this.timestampFrameLayout;
        if (r34 != null) goto L48;
    L53:
        FrameLayout r35 = this.writeButtonContainer;
        Property r72 = View.SCALE_X;
        float r8 = 0.2f;
        if (r12 == false) goto L56;
        float r9 = 1.0f;
    L57:
        r07.add(ObjectAnimator.ofFloat(r35, r72, new float[]{r9}));
        FrameLayout r36 = this.writeButtonContainer;
        Property r73 = View.SCALE_Y;
        if (r12 == false) goto L60;
        r8 = 1.0f;
    L60:
        r07.add(ObjectAnimator.ofFloat(r36, r73, new float[]{r8}));
        FrameLayout r37 = this.writeButtonContainer;
        if (r12 == false) goto L63;
        float r74 = 1.0f;
    L64:
        r07.add(ObjectAnimator.ofFloat(r37, r42, new float[]{r74}));
        FrameLayout r38 = this.pickerBottom;
        if (r38 != null) goto L67;
    L68:
        View r39 = this.shadow[1];
        if (r12 == true) goto L72;
        r6 = 0.0f;
    L72:
        r07.add(ObjectAnimator.ofFloat(r39, r42, new float[]{r6}));
    L73:
        FrameLayout r310 = this.pickerBottomLayout;
        if (r310 == null) goto L76;
        r07.add(ObjectAnimator.ofFloat(r310, View.TRANSLATION_Y, new float[]{0.0f}));
    L76:
        this.animatorSet.playTogether(r07);
        this.animatorSet.setInterpolator(new DecelerateInterpolator());
        this.animatorSet.setDuration(180);
        this.animatorSet.addListener(new AnonymousClass33(this, r12));
        this.animatorSet.start();
        return true;
    L67:
        if (r38.getVisibility() == 0) goto L73;
    L63:
        r74 = 0.0f;
        goto L64
    L56:
        r9 = 0.2f;
        goto L57
    L48:
        if (this.pickerBottom != null) goto L53;
        if (r12 == false) goto L51;
        float r75 = 1.0f;
    L52:
        r07.add(ObjectAnimator.ofFloat(r34, r42, new float[]{r75}));
        goto L53
    L51:
        r75 = 0.0f;
        goto L52
    L44:
        r7 = 0.0f;
        goto L45
    L23:
        if (this.pickerBottom != null) goto L25;
        r04.setVisibility(0);
        goto L25
    L26:
        FrameLayout r08 = this.pickerBottom;
        if (r08 == null) goto L29;
        r08.setVisibility(0);
        goto L29
    L14:
        r3 = null;
        goto L15
    L5:
        r0 = false;
        goto L6
    }

    private void showPremiumBlockedToast(View r3, long r4) {
        int r0 = -this.shiftDp;
        this.shiftDp = r0;
        AndroidUtilities.shakeViewSpring(r3, r0);
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        if (r4 < 0) goto L5;
        String r32 = UserObject.getUserName(MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r4)));
    L6:
        boolean r42 = MessagesController.getInstance(this.currentAccount).premiumFeaturesBlocked();
        FrameLayout r5 = this.bulletinContainer;
        if (r42 == false) goto L9;
        Bulletin r2 = BulletinFactory.of(r5, this.resourcesProvider).createSimpleBulletin(R.raw.star_premium_2, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.UserBlockedNonPremium, new Object[]{r32})));
    L10:
        r2.show();
        return;
    L9:
        r2 = BulletinFactory.of(r5, this.resourcesProvider).createSimpleBulletin(R.raw.star_premium_2, AndroidUtilities.replaceTags(LocaleController.formatString(R.string.UserBlockedNonPremium, new Object[]{r32})), LocaleController.getString(R.string.UserBlockedNonPremiumButton), new ShareAlert$$ExternalSyntheticLambda32(this));
        goto L10
    L5:
        r32 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }

    @SuppressLint({"NewApi"})
    private void updateLayout() {
        if (this.panTranslationMoveLayout == false) goto L6;
        return;
    L6:
        if (this.searchIsVisible == false) goto L8;
        RecyclerListView r0 = this.searchGridView;
    L10:
        if (r0.getChildCount() <= 0) goto L71;
        View r2 = r0.getChildAt(0);
        int r3 = 0;
    L14:
        if (r3 >= r0.getChildCount()) goto L19;
        if (r0.getChildAt(r3).getTop() >= r2.getTop()) goto L18;
        r2 = r0.getChildAt(r3);
    L18:
        r3 = r3 + 1;
        goto L14
    L19:
        RecyclerListView.Holder r02 = (RecyclerListView.Holder) r0.findContainingViewHolder(r2);
        int r32 = r2.getTop() - AndroidUtilities.dp(8.0f);
        if (r32 <= 0) goto L25;
        if (r02 == null) goto L25;
        if (r02.getAdapterPosition() != 0) goto L25;
        int r5 = r32;
    L27:
        if (r32 < 0) goto L32;
        if (r02 == null) goto L32;
        if (r02.getAdapterPosition() != 0) goto L32;
        this.lastOffset = r2.getTop();
        runShadowAnimation(0, false);
    L34:
        if (this.topicsGridView.getVisibility() != 0) goto L59;
        RecyclerListView r03 = this.topicsGridView;
        if (r03.getChildCount() <= 0) goto L72;
        View r22 = r03.getChildAt(0);
        int r52 = 0;
    L40:
        if (r52 >= r03.getChildCount()) goto L45;
        if (r03.getChildAt(r52).getTop() >= r22.getTop()) goto L44;
        r22 = r03.getChildAt(r52);
    L44:
        r52 = r52 + 1;
        goto L40
    L45:
        RecyclerListView.Holder r04 = (RecyclerListView.Holder) r03.findContainingViewHolder(r22);
        int r53 = r22.getTop() - AndroidUtilities.dp(8.0f);
        if (r53 <= 0) goto L51;
        if (r04 == null) goto L51;
        if (r04.getAdapterPosition() != 0) goto L51;
        int r4 = r53;
    L52:
        if (r53 < 0) goto L57;
        if (r04 == null) goto L57;
        if (r04.getAdapterPosition() != 0) goto L57;
        this.lastOffset = r22.getTop();
        runShadowAnimation(0, false);
    L58:
        r32 = AndroidUtilities.lerp(r32, r53, this.topicsGridView.getAlpha());
    L57:
        this.lastOffset = Integer.MAX_VALUE;
        runShadowAnimation(0, true);
        r53 = r4;
    L51:
        r4 = 0;
        goto L52
    L72:
        return;
    L59:
        int r05 = this.scrollOffsetY;
        if (r05 == r32) goto L69;
        this.previousScrollOffsetY = r05;
        RecyclerListView r06 = this.gridView;
        float r1 = r32;
        int r23 = (int) (this.currentPanTranslationY + r1);
        this.scrollOffsetY = r23;
        r06.setTopGlowOffset(r23);
        RecyclerListView r07 = this.searchGridView;
        int r24 = (int) (this.currentPanTranslationY + r1);
        this.scrollOffsetY = r24;
        r07.setTopGlowOffset(r24);
        RecyclerListView r08 = this.topicsGridView;
        int r12 = (int) (r1 + this.currentPanTranslationY);
        this.scrollOffsetY = r12;
        r08.setTopGlowOffset(r12);
        this.frameLayout.setTranslationY(this.scrollOffsetY + this.currentPanTranslationY);
        this.searchEmptyView.setTranslationY(this.scrollOffsetY + this.currentPanTranslationY);
        this.containerView.invalidate();
        return;
    L69:
        return;
    L32:
        this.lastOffset = Integer.MAX_VALUE;
        runShadowAnimation(0, true);
        r32 = r5;
    L25:
        r5 = 0;
        goto L27
    L71:
        return;
    L8:
        r0 = this.gridView;
        goto L10
    }

    private void updateLinkTextView() {
        if (this.linkTextView == null) goto L14;
        String r0 = getLink();
        if (r0 != null) goto L7;
    L12:
        this.linkTextView.setText(r0);
        return;
    L7:
        if (r0.startsWith("https://") == false) goto L10;
        r0 = r0.substring(8);
        goto L12
    L10:
        if (r0.startsWith("http://") == false) goto L12;
        r0 = r0.substring(7);
        goto L12
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        return false;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        int r22 = NotificationCenter.dialogsNeedReload;
        if (r1 != r22) goto L9;
        ShareDialogsAdapter r12 = this.listAdapter;
        if (r12 == null) goto L7;
        r12.fetchDialogs();
    L7:
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, r22);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        dismissSendPopupWindow();
        EditTextEmoji r0 = this.commentTextView;
        if (r0 == null) goto L5;
        AndroidUtilities.hideKeyboard(r0.getEditText());
    L5:
        this.fullyShown = false;
        super.dismiss();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.dialogsNeedReload);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void dismissInternal() {
        super.dismissInternal();
        EditTextEmoji r0 = this.commentTextView;
        if (r0 == null) goto L6;
        r0.onDestroy();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public int getContainerViewHeight() {
        return this.containerView.getMeasuredHeight() - this.containerViewTop;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void onBackPressed() {
        if (this.selectedTopicDialog == null) goto L6;
        collapseTopics();
        return;
    L6:
        EditTextEmoji r0 = this.commentTextView;
        if (r0 != null) goto L9;
    L12:
        super.onBackPressed();
        return;
    L9:
        if (r0.isPopupShowing() == false) goto L12;
        this.commentTextView.hidePopup(true);
    }

    public void onSend(LongSparseArray<TLRPC.Dialog> r1, int r2, TLRPC.TL_forumTopic r3, boolean r4) {
    }

    public void onShareStory(View r1) {
    }

    public void sendInternal(boolean r2) {
        sendInternal(r2, 0, 0);
    }

    public void sendInternal(boolean r15, int r16, int r17) {
        int r0 = 0;
        int r1 = 0;
    L3:
        boolean r3 = true;
        if (r1 >= this.selectedDialogs.size()) goto L15;
        long r4 = this.selectedDialogs.keyAt(r1);
        Context r2 = getContext();
        int r6 = this.currentAccount;
        if (this.frameLayout2.getTag() != null) goto L8;
    L10:
        r3 = false;
    L12:
        if (AlertsCreator.checkSlowMode(r2, r6, r4, r3) == true) goto L13;
        r1 = r1 + 1;
        goto L3
    L13:
        return;
    L8:
        if (this.commentTextView.length() <= 0) goto L10;
    L15:
        CharSequence[] r62 = {this.commentTextView.getText()};
        ArrayList<TLRPC.MessageEntity> r7 = MediaDataController.getInstance(this.currentAccount).getEntities(r62, true);
        CheckBox2 r12 = this.timestampCheckbox;
        if (r12 != null) goto L18;
    L21:
        int r13 = -1;
    L20:
        int r11 = r13;
        ArrayList r14 = new ArrayList();
        if (this.sendingMessageObjects == null) goto L46;
        int r22 = 0;
    L26:
        if (r0 >= this.selectedDialogs.size()) goto L45;
        long r8 = this.selectedDialogs.keyAt(r0);
        long r122 = MessagesController.getInstance(this.currentAccount).getSendPaidMessagesStars(r8);
        if (r122 > 0) goto L31;
        r122 = DialogObject.getMessagesStarsPrice(MessagesController.getInstance(this.currentAccount).isUserContactBlocked(r8));
    L31:
        if (this.frameLayout2.getTag() == null) goto L38;
        if (this.commentTextView.length() <= 0) goto L38;
        if (r122 <= 0) goto L38;
        r22 = r22 + 1;
    L38:
        if (r122 <= 0) goto L40;
        r22 = r22 + 1;
    L40:
        if (r122 <= 0) goto L44;
        if (r14.contains(Long.valueOf(r8)) == true) goto L44;
        r14.add(Long.valueOf(r8));
    L44:
        r0 = r0 + 1;
    L45:
        r0 = r22;
    L101:
        AlertsCreator.ensurePaidMessagesMultiConfirmation(this.currentAccount, r14, r0, new ShareAlert$$ExternalSyntheticLambda30(this, r62, r7, r15, r16, r17, r11));
        return;
    L46:
        SwitchView r23 = this.switchView;
        if (r23 == null) goto L49;
        int r24 = SwitchView.m12753$$Nest$fgetcurrentTab(r23);
    L51:
        if (this.storyItem == null) goto L79;
        int r25 = 0;
        int r5 = 0;
    L54:
        if (r25 >= this.selectedDialogs.size()) goto L77;
        long r82 = this.selectedDialogs.keyAt(r25);
        long r123 = MessagesController.getInstance(this.currentAccount).getSendPaidMessagesStars(r82);
        if (r123 > 0) goto L59;
        r123 = DialogObject.getMessagesStarsPrice(MessagesController.getInstance(this.currentAccount).isUserContactBlocked(r82));
    L59:
        if (this.storyItem == null) goto L70;
        if (this.frameLayout2.getTag() == null) goto L70;
        if (this.commentTextView.length() <= 0) goto L70;
        if (r62[0] == null) goto L70;
        if (r123 <= 0) goto L70;
        r5 = r5 + 1;
    L70:
        if (r123 <= 0) goto L72;
        r5 = r5 + 1;
    L72:
        if (r123 <= 0) goto L76;
        if (r14.contains(Long.valueOf(r82)) == true) goto L76;
        r14.add(Long.valueOf(r82));
    L76:
        r25 = r25 + 1;
        goto L54
    L77:
        r0 = r5;
        goto L101
    L79:
        if (this.sendingText[r24] == null) goto L101;
        r22 = 0;
    L82:
        if (r0 >= this.selectedDialogs.size()) goto L45;
        long r83 = this.selectedDialogs.keyAt(r0);
        long r124 = MessagesController.getInstance(this.currentAccount).getSendPaidMessagesStars(r83);
        if (r124 > 0) goto L87;
        r124 = DialogObject.getMessagesStarsPrice(MessagesController.getInstance(this.currentAccount).isUserContactBlocked(r83));
    L87:
        if (this.frameLayout2.getTag() == null) goto L94;
        if (this.commentTextView.length() <= 0) goto L94;
        if (r124 <= 0) goto L94;
        r22 = r22 + 1;
    L94:
        if (r124 <= 0) goto L96;
        r22 = r22 + 1;
    L96:
        if (r124 <= 0) goto L100;
        if (r14.contains(Long.valueOf(r83)) == true) goto L100;
        r14.add(Long.valueOf(r83));
    L100:
        r0 = r0 + 1;
        goto L82
    L49:
        r24 = 0;
        goto L51
    L18:
        if (r12.isChecked() == false) goto L21;
        r13 = this.timestamp;
        goto L20
    }

    public void setDelegate(ShareAlertDelegate r1) {
        this.delegate = r1;
    }

    public void setStoryToShare(TL_stories.StoryItem r1) {
        this.storyItem = r1;
    }

    public void updateBottomOverlay() {
        if (this.frameLayout2 != null) goto L5;
        return;
    L5:
        EditTextEmoji r0 = this.commentTextView;
        float r1 = 0.0f;
        if (r0 != null) goto L8;
    L10:
        SizeNotifierFrameLayout r02 = this.sizeNotifierFrameLayout;
        if (r02 == null) goto L21;
        AdjustPanLayoutHelper r03 = r02.adjustPanLayoutHelper;
        if (r03 == null) goto L21;
        if (r03.animationInProgress() == true) goto L21;
        if (this.keyboardSize2 <= AndroidUtilities.dp(20.0f)) goto L19;
        float r04 = 1.0f;
    L20:
        this.keyboardT = r04;
        goto L21
    L19:
        r04 = 0.0f;
    L21:
        FrameLayout r05 = this.timestampFrameLayout;
        if (r05 == null) goto L24;
        r05.setTranslationY(-0.0f);
        r1 = 0.0f + AndroidUtilities.dp(48.0f);
    L24:
        FrameLayout r06 = this.pickerBottom;
        if (r06 == null) goto L29;
        float r2 = -r1;
        r06.setTranslationY(r2);
        LinearLayout r07 = this.sharesCountLayout;
        if (r07 == null) goto L29;
        r07.setTranslationY(r2);
    L29:
        float r12 = -r1;
        this.frameLayout2.setTranslationY(r12);
        this.writeButtonContainer.setTranslationY(r12);
        return;
    L8:
        if (r0.isPopupVisible() == false) goto L10;
        this.keyboardT = this.commentTextView.getEmojiPaddingShown();
        goto L21
    }

    public void updateSelectedCount(int r14) {
        if (this.selectedDialogs.size() != 0) goto L6;
        showCommentTextView(false);
        return;
    L6:
        ArrayList<MessageObject> r0 = this.sendingMessageObjects;
        if (r0 != null) goto L9;
        int r02 = 1;
    L11:
        if (this.frameLayout2.getTag() != null) goto L13;
    L15:
        int r5 = 0;
        long r6 = 0;
    L17:
        if (r5 >= this.selectedDialogs.size()) goto L22;
        long r8 = this.selectedDialogs.valueAt(r5).id;
        long r10 = MessagesController.getInstance(this.currentAccount).getSendPaidMessagesStars(r8);
        if (r10 > 0) goto L21;
        r10 = DialogObject.getMessagesStarsPrice(MessagesController.getInstance(this.currentAccount).isUserContactBlocked(r8));
    L21:
        r6 = r6 + r10;
        r5 = r5 + 1;
        goto L17
    L22:
        ChatActivityEnterView.SendButton r3 = this.writeButton;
        int r4 = Math.max(1, this.selectedDialogs.size());
        if (r14 == 0) goto L25;
        boolean r52 = true;
    L26:
        r3.setCount(r4, r52);
        ChatActivityEnterView.SendButton r32 = this.writeButton;
        if (r14 == 0) goto L29;
        boolean r142 = true;
    L30:
        r32.setStarsPrice(r6, r02, r142);
        showCommentTextView(true);
        this.commentTextView.setPadding(0, 0, Math.max(AndroidUtilities.dp(84.0f), this.writeButton.width()), 0);
        return;
    L29:
        r142 = false;
        goto L30
    L25:
        r52 = false;
        goto L26
    L13:
        if (this.commentTextView.length() <= 0) goto L15;
        r02 = r02 + 1;
        goto L15
    L9:
        r02 = r0.size();
        goto L11
    }
}
