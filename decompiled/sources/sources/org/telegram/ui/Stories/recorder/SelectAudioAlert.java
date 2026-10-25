package org.telegram.ui.Stories.recorder;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Build;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.cast.HlsSegmentFormat;
import java.io.File;
import java.util.ArrayList;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AiTonesController$$ExternalSyntheticLambda0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.RectFMergeBounding;
import org.telegram.messenger.utils.TextWatcherImpl;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.SharedAudioCell;
import org.telegram.ui.Components.BottomSheetWithRecyclerListView;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.ChatAttachAlertAudioLayout;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.FragmentSearchField;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.DownscaleScrollableNoiseSuppressor;
import org.telegram.ui.Components.blur3.RenderNodeWithHash;
import org.telegram.ui.Components.blur3.capture.IBlur3Capture;
import org.telegram.ui.Components.blur3.capture.IBlur3Hash;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceRenderNode;
import org.telegram.ui.Components.blur3.utils.Blur3Utils;
import org.telegram.ui.recyclerview.LinearSmoothScrollerCustom;

/* loaded from: classes7.dex */
public class SelectAudioAlert extends BottomSheetWithRecyclerListView implements NotificationCenter.NotificationCenterDelegate, DownloadController.FileDownloadProgressListener, FactorAnimator.Target {
    private UniversalAdapter adapter;
    private final BoolAnimator animatorFadeVisible;
    private MessageObject downloadingMessageObject;
    private final View fadeView;
    private boolean failedToResolveGlobalAudioBot;
    private final FrameLayout frameLayout;
    private final ArrayList<MessageObject> globalAudio;
    private TLRPC.User globalAudioBot;
    private boolean globalAudioHasMore;
    private int globalAudioId;
    private String globalAudioOffset;
    private final IBlur3Capture iBlur3Capture;
    private final BlurredBackgroundDrawableViewFactory iBlur3FactoryFade;
    private final BlurredBackgroundDrawableViewFactory iBlur3FactoryFrostedLiquidGlass;
    private final BlurredBackgroundDrawableViewFactory iBlur3FactoryLiquidGlass;
    private final RectF iBlur3PositionActionBar;
    private final ArrayList<RectF> iBlur3Positions;
    private final ArrayList<RectF> iBlur3PositionsMerged;
    private final BlurredBackgroundSourceColor iBlur3SourceColor;
    private final BlurredBackgroundSourceRenderNode iBlur3SourceGlass;
    private final BlurredBackgroundSourceRenderNode iBlur3SourceGlassFrosted;
    private boolean ignoreScroll;
    private String lastLoadingGlobalAudioQuery;
    private String lastLoadingSharedAudioQuery;
    private Runnable loadGlobalAudioRunnable;
    private Runnable loadSharedAudioRunnable;
    private boolean loadingGlobalAudio;
    private int loadingGlobalAudioRequestId;
    private boolean loadingLocalAudio;
    private boolean loadingSharedAudio;
    private int loadingSharedAudioRequestId;
    private boolean local;
    private final ArrayList<MessageObject> localAudio;
    private int nextSearchRate;
    private final Utilities.Callback<MessageObject> onAudioSelected;
    private final SelectAudioAlert parentAlert;
    private MessageObject playingAudio;
    private String query;
    private boolean resolvingGlobalAudioBot;
    private final MessagesController.SavedMusicList savedMusicList;
    private final DownscaleScrollableNoiseSuppressor scrollableViewNoiseSuppressor;
    private final FragmentSearchField searchField;
    private final ArrayList<MessageObject> sharedAudio;
    private boolean sharedAudioHasMore;
    private final int tag;
    private boolean willLoadGlobalAudio;
    private boolean willLoadSharedAudio;
    private boolean withoutSavedMusic;

    /* renamed from: org.telegram.ui.Stories.recorder.SelectAudioAlert$1, reason: invalid class name */
    public class AnonymousClass1 implements RenderNodeWithHash.Renderer {
        final /* synthetic */ SelectAudioAlert this$0;

        public AnonymousClass1(SelectAudioAlert r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.blur3.RenderNodeWithHash.Renderer
        public void renderNodeCalculateHash(IBlur3Hash r3) {
            r3.add(SelectAudioAlert.access$000(this.this$0, Theme.key_windowBackgroundWhite));
            r3.add(SharedConfig.chatBlurEnabled());
        }

        @Override // org.telegram.ui.Components.blur3.RenderNodeWithHash.Renderer
        public void renderNodeUpdateDisplayList(Canvas r3) {
            r3.drawColor(SelectAudioAlert.access$100(this.this$0, Theme.key_windowBackgroundWhite));
            if (SharedConfig.chatBlurEnabled() == false) goto L6;
            SelectAudioAlert.m20210$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).draw(r3, -2);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.SelectAudioAlert$2, reason: invalid class name */
    public class AnonymousClass2 implements RenderNodeWithHash.Renderer {
        final /* synthetic */ SelectAudioAlert this$0;

        public AnonymousClass2(SelectAudioAlert r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.blur3.RenderNodeWithHash.Renderer
        public void renderNodeCalculateHash(IBlur3Hash r3) {
            r3.add(SelectAudioAlert.access$200(this.this$0, Theme.key_windowBackgroundWhite));
            r3.add(SharedConfig.chatBlurEnabled());
        }

        @Override // org.telegram.ui.Components.blur3.RenderNodeWithHash.Renderer
        public void renderNodeUpdateDisplayList(Canvas r3) {
            r3.drawColor(SelectAudioAlert.access$300(this.this$0, Theme.key_windowBackgroundWhite));
            if (SharedConfig.chatBlurEnabled() == false) goto L6;
            SelectAudioAlert.m20210$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).draw(r3, -3);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.SelectAudioAlert$3, reason: invalid class name */
    public class AnonymousClass3 implements View.OnFocusChangeListener {
        final /* synthetic */ SelectAudioAlert this$0;

        public AnonymousClass3(SelectAudioAlert r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View r1, boolean r2) {
            if (r2 == false) goto L5;
            SelectAudioAlert.m20211$$Nest$fputignoreScroll(this.this$0, true);
            SelectAudioAlert.m20219$$Nest$mscrollToSearchTop(this.this$0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.SelectAudioAlert$4, reason: invalid class name */
    public class AnonymousClass4 implements TextWatcherImpl {
        final /* synthetic */ SelectAudioAlert this$0;

        public AnonymousClass4(SelectAudioAlert r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r5) {
            SelectAudioAlert.m20209$$Nest$fgetquery(this.this$0);
            SelectAudioAlert.m20212$$Nest$fputquery(this.this$0, r5.toString());
            if (SelectAudioAlert.m20208$$Nest$fgetlocal(this.this$0) == true) goto L32;
            String r52 = SelectAudioAlert.m20207$$Nest$fgetlastLoadingSharedAudioQuery(this.this$0);
            String r1 = SelectAudioAlert.m20209$$Nest$fgetquery(this.this$0);
            String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r1 != null) goto L7;
            String r12 = _UrlKt.FRAGMENT_ENCODE_SET;
        L8:
            boolean r53 = TextUtils.equals(r52, r12);
            boolean r13 = false;
            if (r53 == true) goto L17;
            SelectAudioAlert.m20216$$Nest$mcancelLoadingSharedAudio(this.this$0);
            SelectAudioAlert r54 = this.this$0;
            if (SelectAudioAlert.m20209$$Nest$fgetquery(r54) != null) goto L13;
        L15:
            boolean r3 = false;
        L16:
            SelectAudioAlert.m20214$$Nest$fputwillLoadSharedAudio(r54, r3);
            goto L17
        L13:
            if (SelectAudioAlert.m20209$$Nest$fgetquery(this.this$0).length() <= 0) goto L15;
            r3 = true;
        L17:
            String r55 = SelectAudioAlert.m20206$$Nest$fgetlastLoadingGlobalAudioQuery(this.this$0);
            if (SelectAudioAlert.m20209$$Nest$fgetquery(this.this$0) == null) goto L22;
            r2 = SelectAudioAlert.m20209$$Nest$fgetquery(this.this$0);
        L22:
            if (TextUtils.equals(r55, r2) == true) goto L31;
            SelectAudioAlert.m20215$$Nest$mcancelLoadingGlobalAudio(this.this$0);
            SelectAudioAlert r56 = this.this$0;
            if (SelectAudioAlert.m20209$$Nest$fgetquery(r56) != null) goto L26;
        L30:
            SelectAudioAlert.m20213$$Nest$fputwillLoadGlobalAudio(r56, r13);
            goto L31
        L26:
            if (SelectAudioAlert.m20209$$Nest$fgetquery(this.this$0).length() <= 3) goto L30;
            if (TextUtils.isEmpty(MessagesController.getInstance(SelectAudioAlert.access$400(this.this$0)).config.musicSearchUsername.get()) == true) goto L30;
            r13 = true;
        L31:
            SelectAudioAlert.m20218$$Nest$mloadSharedAudioDelayed(this.this$0);
            SelectAudioAlert.m20217$$Nest$mloadGlobalAudioDelayed(this.this$0);
            goto L32
        L7:
            r12 = SelectAudioAlert.m20209$$Nest$fgetquery(this.this$0);
        L32:
            SelectAudioAlert.m20204$$Nest$fgetadapter(this.this$0).update(true);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.SelectAudioAlert$5, reason: invalid class name */
    public class AnonymousClass5 extends RecyclerView.OnScrollListener {
        final /* synthetic */ SelectAudioAlert this$0;

        public AnonymousClass5(SelectAudioAlert r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            super.onScrollStateChanged(r1, r2);
            if (r2 == 0) goto L5;
            return;
        L5:
            if (SelectAudioAlert.m20205$$Nest$fgetignoreScroll(this.this$0) == false) goto L9;
            SelectAudioAlert.m20211$$Nest$fputignoreScroll(this.this$0, false);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            SelectAudioAlert.m20220$$Nest$mupdateSearchY(this.this$0);
            this.this$0.blur3_InvalidateBlur();
            if (SelectAudioAlert.access$500(this.this$0).scrollingByUser == true) goto L5;
            return;
        L5:
            if (SelectAudioAlert.m20205$$Nest$fgetignoreScroll(this.this$0) == true) goto L9;
            AndroidUtilities.hideKeyboard(SelectAudioAlert.access$600(this.this$0));
            return;
        }
    }

    /* renamed from: $r8$lambda$68HpPsligHbbf9Q6ji18hDK-nFQ, reason: not valid java name */
    public static /* synthetic */ void m20201$r8$lambda$68HpPsligHbbf9Q6ji18hDKnFQ(SelectAudioAlert r0) {
        r0.loadSharedAudio();
    }

    public static /* synthetic */ void $r8$lambda$8rOcm7yWK0DRAz2_jR8UeUmPdZ4(SelectAudioAlert r0, Long r1) {
        r0.lambda$loadGlobalAudio$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$A1BB63idCCT5unHA5A_SFxynkPg(SelectAudioAlert r0) {
        r0.lambda$loadLocalAudio$7();
    }

    public static /* synthetic */ boolean $r8$lambda$ATnQ0G5Um1fAArJwxHI5YLVVfvo(SelectAudioAlert r0, MessageObject r1) {
        return r0.needPlayMessage(r1);
    }

    public static /* synthetic */ void $r8$lambda$KoFM21HWHN6by2wCdO_mjMaTwcc(SelectAudioAlert r0, Utilities.Callback r1, Theme.ResourcesProvider r2, View r3, int r4) {
        r0.lambda$new$1(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$Qo65yDJ9jlnDuZFP3JkLCtfwgeE(SelectAudioAlert r0, TLRPC.messages_BotResults r1, TLRPC.TL_error r2) {
        r0.lambda$loadGlobalAudio$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$VMZNbzwbERFl6XmGv2yGDYrPDqI(SelectAudioAlert r0) {
        r0.loadGlobalAudio();
    }

    /* renamed from: $r8$lambda$YqeyMgGWL7SzGX6-FMopWveEmqA, reason: not valid java name */
    public static /* synthetic */ void m20202$r8$lambda$YqeyMgGWL7SzGX6FMopWveEmqA(SelectAudioAlert r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadSharedAudio$3(r1, r2);
    }

    /* renamed from: $r8$lambda$_VHDF_e3-2D54sd68PNWqVlG_qo, reason: not valid java name */
    public static /* synthetic */ void m20203$r8$lambda$_VHDF_e32D54sd68PNWqVlG_qo(SelectAudioAlert r0, TLObject r1) {
        r0.lambda$loadSharedAudio$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$fxM54q3IMs81QZA2zHXuQOxs1rc(SelectAudioAlert r0, ArrayList r1) {
        r0.lambda$loadLocalAudio$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$xkRvggRmC1p5KcRjgWzINtT3ld8(SelectAudioAlert r0, Canvas r1, RectF r2) {
        r0.lambda$new$0(r1, r2);
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UniversalAdapter m20204$$Nest$fgetadapter(SelectAudioAlert r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetignoreScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20205$$Nest$fgetignoreScroll(SelectAudioAlert r0) {
        return r0.ignoreScroll;
    }

    /* renamed from: -$$Nest$fgetlastLoadingGlobalAudioQuery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m20206$$Nest$fgetlastLoadingGlobalAudioQuery(SelectAudioAlert r0) {
        return r0.lastLoadingGlobalAudioQuery;
    }

    /* renamed from: -$$Nest$fgetlastLoadingSharedAudioQuery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m20207$$Nest$fgetlastLoadingSharedAudioQuery(SelectAudioAlert r0) {
        return r0.lastLoadingSharedAudioQuery;
    }

    /* renamed from: -$$Nest$fgetlocal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20208$$Nest$fgetlocal(SelectAudioAlert r0) {
        return r0.local;
    }

    /* renamed from: -$$Nest$fgetquery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m20209$$Nest$fgetquery(SelectAudioAlert r0) {
        return r0.query;
    }

    /* renamed from: -$$Nest$fgetscrollableViewNoiseSuppressor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DownscaleScrollableNoiseSuppressor m20210$$Nest$fgetscrollableViewNoiseSuppressor(SelectAudioAlert r0) {
        return r0.scrollableViewNoiseSuppressor;
    }

    /* renamed from: -$$Nest$fputignoreScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20211$$Nest$fputignoreScroll(SelectAudioAlert r0, boolean r1) {
        r0.ignoreScroll = r1;
    }

    /* renamed from: -$$Nest$fputquery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20212$$Nest$fputquery(SelectAudioAlert r0, String r1) {
        r0.query = r1;
    }

    /* renamed from: -$$Nest$fputwillLoadGlobalAudio, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20213$$Nest$fputwillLoadGlobalAudio(SelectAudioAlert r0, boolean r1) {
        r0.willLoadGlobalAudio = r1;
    }

    /* renamed from: -$$Nest$fputwillLoadSharedAudio, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20214$$Nest$fputwillLoadSharedAudio(SelectAudioAlert r0, boolean r1) {
        r0.willLoadSharedAudio = r1;
    }

    /* renamed from: -$$Nest$mcancelLoadingGlobalAudio, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20215$$Nest$mcancelLoadingGlobalAudio(SelectAudioAlert r0) {
        r0.cancelLoadingGlobalAudio();
    }

    /* renamed from: -$$Nest$mcancelLoadingSharedAudio, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20216$$Nest$mcancelLoadingSharedAudio(SelectAudioAlert r0) {
        r0.cancelLoadingSharedAudio();
    }

    /* renamed from: -$$Nest$mloadGlobalAudioDelayed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20217$$Nest$mloadGlobalAudioDelayed(SelectAudioAlert r0) {
        r0.loadGlobalAudioDelayed();
    }

    /* renamed from: -$$Nest$mloadSharedAudioDelayed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20218$$Nest$mloadSharedAudioDelayed(SelectAudioAlert r0) {
        r0.loadSharedAudioDelayed();
    }

    /* renamed from: -$$Nest$mscrollToSearchTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20219$$Nest$mscrollToSearchTop(SelectAudioAlert r0) {
        r0.scrollToSearchTop();
    }

    /* renamed from: -$$Nest$mupdateSearchY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20220$$Nest$mupdateSearchY(SelectAudioAlert r0) {
        r0.updateSearchY();
    }

    public SelectAudioAlert(Context r7, Utilities.Callback<MessageObject> r8, Theme.ResourcesProvider r9) {
        this(r7, false, null, r8, r9);
    }

    public SelectAudioAlert(Context r21, boolean r22, SelectAudioAlert r23, Utilities.Callback<MessageObject> r24, Theme.ResourcesProvider r25) {
        super(r21, null, true, false, false, BottomSheetWithRecyclerListView.ActionBarType.SLIDING, r25);
        CubicBezierInterpolator r3 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.animatorFadeVisible = new BoolAnimator(0, this, r3, 380);
        this.localAudio = new ArrayList();
        this.sharedAudio = new ArrayList();
        this.globalAudio = new ArrayList();
        this.loadingSharedAudioRequestId = -1;
        this.loadSharedAudioRunnable = new SelectAudioAlert$$ExternalSyntheticLambda1(this);
        this.loadingGlobalAudioRequestId = -1;
        this.globalAudioId = -2000000000;
        this.loadGlobalAudioRunnable = new SelectAudioAlert$$ExternalSyntheticLambda2(this);
        ArrayList<RectF> r2 = new ArrayList();
        this.iBlur3Positions = r2;
        RectF r4 = new RectF();
        this.iBlur3PositionActionBar = r4;
        r2.add(r4);
        this.iBlur3PositionsMerged = new ArrayList();
        this.topPadding = 0.35f;
        int r26 = Theme.key_windowBackgroundGray;
        fixNavigationBar(getThemedColor(r26));
        setSlidingActionBar();
        this.headerPaddingTop = AndroidUtilities.dp(4.0f);
        this.headerPaddingBottom = AndroidUtilities.dp(-20.0f);
        this.local = r22;
        this.tag = DownloadController.getInstance(this.currentAccount).generateObserverTag();
        this.parentAlert = r23;
        this.onAudioSelected = r24;
        BlurredBackgroundSourceColor r5 = new BlurredBackgroundSourceColor();
        this.iBlur3SourceColor = r5;
        r5.setColor(getThemedColor(Theme.key_windowBackgroundWhite));
        if (Build.VERSION.SDK_INT < 31) goto L5;
        this.scrollableViewNoiseSuppressor = new DownscaleScrollableNoiseSuppressor();
        BlurredBackgroundSourceRenderNode r10 = new BlurredBackgroundSourceRenderNode(null);
        this.iBlur3SourceGlass = r10;
        r10.setupRenderer(new AnonymousClass1(this));
        BlurredBackgroundSourceRenderNode r11 = new BlurredBackgroundSourceRenderNode(null);
        this.iBlur3SourceGlassFrosted = r11;
        r11.setupRenderer(new AnonymousClass2(this));
        BlurredBackgroundDrawableViewFactory r13 = new BlurredBackgroundDrawableViewFactory(r10);
        this.iBlur3FactoryLiquidGlass = r13;
        r13.setLiquidGlassEffectAllowed(LiteMode.isEnabled(262144));
        BlurredBackgroundDrawableViewFactory r132 = new BlurredBackgroundDrawableViewFactory(r11);
        this.iBlur3FactoryFrostedLiquidGlass = r132;
        r132.setLiquidGlassEffectAllowed(LiteMode.isEnabled(262144));
    L6:
        this.iBlur3FactoryFade = new BlurredBackgroundDrawableViewFactory(r5);
        this.iBlur3Capture = new SelectAudioAlert$$ExternalSyntheticLambda3(this);
        ChatAttachAlert.SearchFadeView r52 = new ChatAttachAlert.SearchFadeView(r21, r26, r25);
        this.fadeView = r52;
        r52.setVisibility(4);
        FrameLayout r102 = new FrameLayout(r21);
        this.frameLayout = r102;
        FragmentSearchField r112 = new FragmentSearchField(r21, r25);
        this.searchField = r112;
        r112.editText.setOnFocusChangeListener(new AnonymousClass3(this));
        r112.setSectionBackground();
        r112.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f));
        r112.editText.addTextChangedListener(new AnonymousClass4(this));
        r112.editText.setHint(LocaleController.getString(R.string.Search));
        r102.addView(r52, LayoutHelper.createFrameMatchParent());
        r102.addView(r112, LayoutHelper.createFrame(-1, 48.0f, 51, 0.0f, 8.0f, 0.0f, 4.0f));
        r112.setupBlurredBackground(this.iBlur3FactoryLiquidGlass.create(r112, BlurredBackgroundProviderImpl.topPanel(r25)));
        r102.setPadding(this.backgroundPaddingLeft + AndroidUtilities.dp(8.0f), 0, this.backgroundPaddingLeft + AndroidUtilities.dp(8.0f), 0);
        this.containerView.addView(r102, LayoutHelper.createFrame(-1, -2, 55));
        setBackgroundColor(getThemedColor(r26));
        RecyclerListView r1 = this.recyclerListView;
        int r27 = this.backgroundPaddingLeft;
        r1.setPadding(r27, 0, r27, 0);
        this.recyclerListView.setSections();
        DefaultItemAnimator r12 = new DefaultItemAnimator();
        r12.setSupportsChangeAnimations(false);
        r12.setDelayAnimations(false);
        r12.setInterpolator(r3);
        r12.setDurations(350);
        this.recyclerListView.setItemAnimator(r12);
        if (r22 == true) goto L9;
        int r28 = this.currentAccount;
        MessagesController.SavedMusicList r14 = new MessagesController.SavedMusicList(r28, UserConfig.getInstance(r28).getClientUserId());
        this.savedMusicList = r14;
        r14.load();
        loadSharedAudio();
        loadGlobalAudio();
    L10:
        this.recyclerListView.setOnScrollListener(new AnonymousClass5(this));
        this.recyclerListView.setOnItemClickListener(new SelectAudioAlert$$ExternalSyntheticLambda4(this, r24, r25));
        return;
    L9:
        this.savedMusicList = null;
        loadLocalAudio();
        goto L10
    L5:
        this.scrollableViewNoiseSuppressor = null;
        this.iBlur3SourceGlassFrosted = null;
        this.iBlur3SourceGlass = null;
        this.iBlur3FactoryLiquidGlass = new BlurredBackgroundDrawableViewFactory(r5);
        this.iBlur3FactoryFrostedLiquidGlass = new BlurredBackgroundDrawableViewFactory(r5);
        goto L6
    }

    public static /* synthetic */ int access$000(SelectAudioAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$100(SelectAudioAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$200(SelectAudioAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$300(SelectAudioAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$400(SelectAudioAlert r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ RecyclerListView access$500(SelectAudioAlert r0) {
        return r0.recyclerListView;
    }

    public static /* synthetic */ ViewGroup access$600(SelectAudioAlert r0) {
        return r0.containerView;
    }

    private int addSection(boolean r12, ArrayList<UItem> r13, String r14, ArrayList<MessageObject> r15, boolean r16, boolean r17, int r18) {
        int r1 = 0;
        if (r15 != null) goto L5;
    L49:
        return 0;
    L5:
        if (r15.isEmpty() == false) goto L8;
        if (r16 == false) goto L49;
    L8:
        ArrayList r2 = new ArrayList();
        String r3 = this.query;
        if (r3 != null) goto L11;
        String r32 = null;
    L12:
        String r5 = AndroidUtilities.translitSafe(r32);
        int r6 = r15.size();
        int r7 = 0;
    L13:
        if (r7 >= r6) goto L29;
        MessageObject r8 = r15.get(r7);
        r7 = r7 + 1;
        MessageObject r82 = r8;
        if (r12 == false) goto L16;
        if (TextUtils.isEmpty(r32) == true) goto L27;
        if (r15 == this.sharedAudio) goto L27;
        String r9 = r82.getMusicTitle();
        String r10 = r82.getMusicAuthor();
        if (matches(r32, r5, r9) == true) goto L26;
        if (matches(r32, r5, r10) == false) goto L13;
    L26:
        r82.setQuery(this.query);
        r2.add(r82);
    L27:
        r82.setQuery(null);
        r2.add(r82);
        goto L13
    L16:
        r82.setQuery(this.query);
        r2.add(r82);
        goto L13
    L29:
        if (r2.isEmpty() == false) goto L33;
        if (r16 == true) goto L33;
        return 0;
    L33:
        if (r13.isEmpty() == false) goto L35;
    L37:
        int r122 = 0;
    L38:
        this.adapter.whiteSectionStart();
        r13.add(UItem.asHeader(r14));
        int r0 = r2.size();
    L40:
        if (r1 >= r0) goto L42;
        Object r4 = r2.get(r1);
        r1 = r1 + 1;
        r13.add(SharedAudioCell.Factory.as((MessageObject) r4, new SelectAudioAlert$$ExternalSyntheticLambda7(this)));
        r122 = r122 + AndroidUtilities.dp(56.0f);
        goto L40
    L42:
        if (r16 == false) goto L44;
        r13.add(UItem.asFlicker(4));
        r13.add(UItem.asFlicker(4));
        r13.add(UItem.asFlicker(4));
        r122 = r122 + (AndroidUtilities.dp(56.0f) * 3);
    L44:
        if (r17 == false) goto L47;
        if (r16 == true) goto L47;
        r13.add(UItem.asButton(r18, R.drawable.arrow_more, LocaleController.getString(R.string.ShowMore)).accent());
        r122 = r122 + AndroidUtilities.dp(50.0f);
    L47:
        this.adapter.whiteSectionEnd();
        return r122;
    L35:
        if (r13.size() <= 1) goto L37;
        r13.add(UItem.asShadow(null));
        r122 = AndroidUtilities.dp(12.0f);
        goto L38
    L11:
        r32 = r3.toLowerCase();
        goto L12
    }

    private void cancelLoadingGlobalAudio() {
        if (this.loadingGlobalAudioRequestId < 0) goto L5;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.loadingGlobalAudioRequestId, true);
    L5:
        this.loadingGlobalAudioRequestId = -1;
        this.globalAudioOffset = _UrlKt.FRAGMENT_ENCODE_SET;
        this.globalAudioHasMore = false;
        this.globalAudio.clear();
        this.loadingGlobalAudio = false;
        this.willLoadGlobalAudio = false;
    }

    private void cancelLoadingSharedAudio() {
        if (this.loadingSharedAudioRequestId < 0) goto L5;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.loadingSharedAudioRequestId, true);
    L5:
        this.loadingSharedAudioRequestId = -1;
        this.nextSearchRate = 0;
        this.sharedAudio.clear();
        this.loadingSharedAudio = false;
        this.willLoadSharedAudio = false;
    }

    private void done(MessageObject r2) {
        this.onAudioSelected.run(r2);
        SelectAudioAlert r22 = this.parentAlert;
        if (r22 == null) goto L5;
        r22.dismiss();
    L5:
        dismiss();
    }

    private /* synthetic */ void lambda$loadGlobalAudio$4(Long r3) {
        boolean r0 = false;
        this.resolvingGlobalAudioBot = false;
        if (r3 != null) goto L5;
        TLRPC.User r32 = null;
    L6:
        this.globalAudioBot = r32;
        if (r32 != null) goto L9;
        r0 = true;
    L9:
        this.failedToResolveGlobalAudioBot = r0;
        if (r32 == null) goto L13;
        loadGlobalAudio();
        return;
    L13:
        return;
    L5:
        r32 = MessagesController.getInstance(this.currentAccount).getUser(r3);
        goto L6
    }

    private /* synthetic */ void lambda$loadGlobalAudio$5(TLRPC.messages_BotResults r11, TLRPC.TL_error r12) {
        boolean r122 = false;
        this.loadingGlobalAudio = false;
        this.willLoadGlobalAudio = false;
        if (r11 == null) goto L18;
        MessagesController.getInstance(this.currentAccount).putUsers(r11.users, false);
        ArrayList<TLRPC.BotInlineResult> r1 = r11.results;
        int r2 = r1.size();
        int r3 = 0;
    L5:
        if (r3 >= r2) goto L11;
        TLRPC.BotInlineResult r4 = r1.get(r3);
        r3 = r3 + 1;
        TLRPC.BotInlineResult r42 = r4;
        if ((r42 instanceof TLRPC.TL_botInlineMediaResult) == false) goto L5;
        TLRPC.TL_botInlineMediaResult r43 = (TLRPC.TL_botInlineMediaResult) r42;
        if (r43.document == null) goto L5;
        TLRPC.TL_message r5 = new TLRPC.TL_message();
        r5.out = true;
        int r6 = this.globalAudioId;
        this.globalAudioId = r6 - 1;
        r5.id = r6;
        r5.peer_id = new TLRPC.TL_peerUser();
        TLRPC.TL_peerUser r62 = new TLRPC.TL_peerUser();
        r5.from_id = r62;
        TLRPC.Peer r7 = r5.peer_id;
        long r8 = UserConfig.getInstance(this.currentAccount).getClientUserId();
        r62.user_id = r8;
        r7.user_id = r8;
        r5.date = (int) (System.currentTimeMillis() / 1000);
        r5.message = _UrlKt.FRAGMENT_ENCODE_SET;
        TLRPC.TL_messageMediaDocument r63 = new TLRPC.TL_messageMediaDocument();
        r5.media = r63;
        r63.flags |= 3;
        r63.document = r43.document;
        r5.flags |= 768;
        this.globalAudio.add(new MessageObject(this.currentAccount, r5, false, true));
        goto L5
    L11:
        this.globalAudioOffset = r11.next_offset;
        if (this.globalAudio.isEmpty() == false) goto L14;
    L16:
        this.globalAudioHasMore = r122;
        this.adapter.update(true);
        return;
    L14:
        if (TextUtils.isEmpty(this.globalAudioOffset) == true) goto L16;
        r122 = true;
        goto L16
    L18:
        this.adapter.update(true);
    }

    private /* synthetic */ void lambda$loadLocalAudio$6(ArrayList r2) {
        this.loadingLocalAudio = false;
        this.localAudio.addAll(r2);
        this.adapter.update(true);
    }

    private /* synthetic */ void lambda$loadLocalAudio$7() {
        String[] r8 = {"_id", "artist", "title", "_data", "duration", "album"};
        ArrayList r1 = new ArrayList();
        Cursor r2 = ApplicationLoader.applicationContext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, r8, "is_music != 0", null, "title");     // Catch: Exception -> L17
        int r0 = -2000000000;
    L30:
        if (r2.moveToNext() == false) goto L15;
        MediaController.AudioEntry r3 = new MediaController.AudioEntry();     // Catch: Throwable -> L13
        r3.id = r2.getInt(0);     // Catch: Throwable -> L13
        r3.author = r2.getString(1);     // Catch: Throwable -> L13
        r3.title = r2.getString(2);     // Catch: Throwable -> L13
        r3.path = r2.getString(3);     // Catch: Throwable -> L13
        r3.duration = (int) (r2.getLong(4) / 1000);     // Catch: Throwable -> L13
        r3.genre = r2.getString(5);     // Catch: Throwable -> L13
        File r7 = new File(r3.path);     // Catch: Throwable -> L13
        TLRPC.TL_message r82 = new TLRPC.TL_message();     // Catch: Throwable -> L13
        r82.out = true;     // Catch: Throwable -> L13
        r82.id = r0;     // Catch: Throwable -> L13
        r82.peer_id = new TLRPC.TL_peerUser();     // Catch: Throwable -> L13
        TLRPC.TL_peerUser r11 = new TLRPC.TL_peerUser();     // Catch: Throwable -> L13
        r82.from_id = r11;     // Catch: Throwable -> L13
        TLRPC.Peer r12 = r82.peer_id;     // Catch: Throwable -> L13
        long r13 = UserConfig.getInstance(this.currentAccount).getClientUserId();     // Catch: Throwable -> L13
        r11.user_id = r13;     // Catch: Throwable -> L13
        r12.user_id = r13;     // Catch: Throwable -> L13
        r82.date = (int) (System.currentTimeMillis() / 1000);     // Catch: Throwable -> L13
        r82.message = _UrlKt.FRAGMENT_ENCODE_SET;     // Catch: Throwable -> L13
        r82.attachPath = r3.path;     // Catch: Throwable -> L13
        TLRPC.TL_messageMediaDocument r9 = new TLRPC.TL_messageMediaDocument();     // Catch: Throwable -> L13
        r82.media = r9;     // Catch: Throwable -> L13
        r9.flags |= 3;
        r9.document = new TLRPC.TL_document();     // Catch: Throwable -> L13
        r82.flags |= 768;
        String r92 = FileLoader.getFileExtension(r7);     // Catch: Throwable -> L13
        TLRPC.Document r10 = r82.media.document;     // Catch: Throwable -> L13
        r10.id = 0;     // Catch: Throwable -> L13
        r10.access_hash = 0;     // Catch: Throwable -> L13
        r10.file_reference = new byte[0];     // Catch: Throwable -> L13
        r10.date = r82.date;     // Catch: Throwable -> L13
        StringBuilder r112 = new StringBuilder();     // Catch: Throwable -> L13
        r112.append("audio/");     // Catch: Throwable -> L13
        if (r92.length() > 0) goto L11;
        r92 = HlsSegmentFormat.MP3;
    L11:
        r112.append(r92);     // Catch: Throwable -> L13
        r10.mime_type = r112.toString();     // Catch: Throwable -> L13
        r82.media.document.size = (int) r7.length();     // Catch: Throwable -> L13
        r82.media.document.dc_id = 0;     // Catch: Throwable -> L13
        TLRPC.TL_documentAttributeAudio r93 = new TLRPC.TL_documentAttributeAudio();     // Catch: Throwable -> L13
        r93.duration = r3.duration;     // Catch: Throwable -> L13
        r93.title = r3.title;     // Catch: Throwable -> L13
        r93.performer = r3.author;     // Catch: Throwable -> L13
        r93.flags = 3 | r93.flags;     // Catch: Throwable -> L13
        r82.media.document.attributes.add(r93);     // Catch: Throwable -> L13
        TLRPC.TL_documentAttributeFilename r6 = new TLRPC.TL_documentAttributeFilename();     // Catch: Throwable -> L13
        r6.file_name = r7.getName();     // Catch: Throwable -> L13
        r82.media.document.attributes.add(r6);     // Catch: Throwable -> L13
        MessageObject r62 = new MessageObject(this.currentAccount, r82, false, true);     // Catch: Throwable -> L13
        r3.messageObject = r62;     // Catch: Throwable -> L13
        r1.add(r62);     // Catch: Throwable -> L13
        r0 = r0 - 1;
        goto L30
    L15:
        r2.close();     // Catch: Exception -> L17
    L26:
        AndroidUtilities.runOnUIThread(new SelectAudioAlert$$ExternalSyntheticLambda10(this, r1));
        return;
    L13:
        th = move-exception;
        if (r2 == null) goto L34;
        r2.close();     // Catch: Throwable -> L22
    L35:
        throw th;     // Catch: Exception -> L17
    L22:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L17
        throw th;     // Catch: Exception -> L17
    L34:
        throw th;     // Catch: Exception -> L17
    L17:
        e = move-exception;
        FileLog.e(e);
        goto L26
    }

    private /* synthetic */ void lambda$loadSharedAudio$2(TLObject r9) {
        boolean r0 = false;
        this.willLoadSharedAudio = false;
        this.loadingSharedAudio = false;
        if ((r9 instanceof TLRPC.messages_Messages) == false) goto L13;
        TLRPC.messages_Messages r92 = (TLRPC.messages_Messages) r9;
        MessagesController.getInstance(this.currentAccount).putUsers(r92.users, false);
        MessagesController.getInstance(this.currentAccount).putChats(r92.chats, false);
        ArrayList<TLRPC.Message> r1 = r92.messages;
        int r3 = r1.size();
        int r4 = 0;
    L5:
        if (r4 >= r3) goto L8;
        TLRPC.Message r5 = r1.get(r4);
        r4 = r4 + 1;
        this.sharedAudio.add(new MessageObject(this.currentAccount, r5, false, true));
        goto L5
    L8:
        if ((r92 instanceof TLRPC.TL_messages_messagesSlice) == true) goto L10;
    L12:
        this.sharedAudioHasMore = r0;
        this.nextSearchRate = r92.next_rate;
    L14:
        this.adapter.update(true);
        return;
    L10:
        if (this.sharedAudio.size() >= r92.count) goto L12;
        r0 = true;
        goto L12
    L13:
        this.sharedAudioHasMore = false;
        this.nextSearchRate = 0;
        goto L14
    }

    private /* synthetic */ void lambda$loadSharedAudio$3(TLObject r1, TLRPC.TL_error r2) {
        AndroidUtilities.runOnUIThread(new SelectAudioAlert$$ExternalSyntheticLambda11(this, r1));
    }

    private /* synthetic */ void lambda$new$0(Canvas r7, RectF r8) {
        RecyclerListView r0 = this.recyclerListView;
        Blur3Utils.captureRelativeParent(r0, r7, r8, r0, getContainerView(), 255);
    }

    private /* synthetic */ void lambda$new$1(Utilities.Callback r9, Theme.ResourcesProvider r10, View r11, int r12) {
        if ((r11 instanceof SharedAudioCell) == false) goto L21;
        MessageObject r92 = ((SharedAudioCell) r11).getMessage();
        if (r92 == null) goto L45;
        DownloadController.getInstance(this.currentAccount).removeLoadingFileObserver(this);
        if (this.downloadingMessageObject == null) goto L11;
        FileLoader.getInstance(this.currentAccount).cancelLoadFile(this.downloadingMessageObject.getDocument());
        this.downloadingMessageObject = null;
    L11:
        if (r92.attachPathExists == false) goto L13;
    L19:
        done(r92);
        return;
    L13:
        if (r92.mediaExists == true) goto L19;
        String r102 = r92.getFileName();
        if (TextUtils.isEmpty(r102) == true) goto L46;
        this.downloadingMessageObject = r92;
        DownloadController.getInstance(this.currentAccount).addLoadingFileObserver(r102, r92, this);
        FileLoader.getInstance(this.currentAccount).loadFile(r92.getDocument(), r92, 1, 0);
        return;
    L46:
        return;
    L45:
        return;
    L21:
        UItem r112 = this.adapter.getItem(r12 - 1);
        if (r112 == null) goto L28;
        if (r112.id != 1) goto L28;
        new SelectAudioAlert(getContext(), true, this, r9, r10).show();
        return;
    L28:
        if (r112 != null) goto L30;
    L33:
        if (r112 != null) goto L35;
    L38:
        if (r112 != null) goto L40;
        return;
    L40:
        if (r112.id != 4) goto L44;
        loadGlobalAudio();
        return;
    L44:
        return;
    L35:
        if (r112.id != 3) goto L38;
        loadSharedAudio();
        return;
    L30:
        if (r112.id != 2) goto L33;
        this.savedMusicList.load();
    }

    private void loadGlobalAudio() {
        String r0 = MessagesController.getInstance(this.currentAccount).config.musicSearchUsername.get();
        if (TextUtils.isEmpty(r0) == true) goto L49;
        String r1 = this.lastLoadingGlobalAudioQuery;
        String r2 = this.query;
        String r3 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r2 != null) goto L9;
        r2 = _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        if (TextUtils.equals(r1, r2) == true) goto L12;
        cancelLoadingGlobalAudio();
    L12:
        if (this.loadingGlobalAudio == false) goto L15;
        return;
    L15:
        if (TextUtils.isEmpty(this.query) == false) goto L17;
        return;
    L17:
        if (this.query.length() >= 3) goto L20;
        return;
    L20:
        if (this.globalAudio.isEmpty() == true) goto L25;
        if (this.globalAudioHasMore == true) goto L25;
        return;
    L25:
        if (this.globalAudioBot != null) goto L28;
        this.globalAudioBot = MessagesController.getInstance(this.currentAccount).getUser(r0);
    L28:
        if (this.globalAudioBot == null) goto L30;
        this.loadingGlobalAudio = true;
        TLRPC.User r02 = UserConfig.getInstance(this.currentAccount).getCurrentUser();
        TLRPC.TL_messages_getInlineBotResults r12 = new TLRPC.TL_messages_getInlineBotResults();
        r12.bot = MessagesController.getInstance(this.currentAccount).getInputUser(this.globalAudioBot);
        r12.peer = MessagesController.getInputPeer(r02);
        if (this.globalAudio.isEmpty() == true) goto L40;
        String r03 = this.globalAudioOffset;
        if (r03 == null) goto L40;
    L41:
        r12.offset = r03;
        String r04 = this.query;
        if (r04 == null) goto L45;
        r3 = r04;
    L45:
        this.lastLoadingGlobalAudioQuery = r3;
        r12.query = r3;
        this.loadingGlobalAudioRequestId = ConnectionsManager.getInstance(this.currentAccount).sendRequestTyped(r12, new AiTonesController$$ExternalSyntheticLambda0(), new SelectAudioAlert$$ExternalSyntheticLambda6(this));
        this.adapter.update(true);
        return;
    L40:
        r03 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L41
    L30:
        if (this.resolvingGlobalAudioBot == false) goto L32;
        return;
    L32:
        if (this.failedToResolveGlobalAudioBot == true) goto L53;
        this.resolvingGlobalAudioBot = true;
        MessagesController.getInstance(this.currentAccount).getUserNameResolver().resolve(r0, new SelectAudioAlert$$ExternalSyntheticLambda5(this));
        return;
    L53:
        return;
    }

    private void loadGlobalAudioDelayed() {
        AndroidUtilities.cancelRunOnUIThread(this.loadGlobalAudioRunnable);
        AndroidUtilities.runOnUIThread(this.loadGlobalAudioRunnable, 400);
    }

    private void loadLocalAudio() {
        if (this.local == true) goto L6;
        return;
    L6:
        if (this.loadingLocalAudio == false) goto L8;
        return;
    L8:
        this.loadingLocalAudio = true;
        Utilities.globalQueue.postRunnable(new SelectAudioAlert$$ExternalSyntheticLambda8(this));
    }

    private void loadSharedAudio() {
        if (this.local == true) goto L31;
        String r0 = this.lastLoadingSharedAudioQuery;
        String r1 = this.query;
        String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r1 != null) goto L9;
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L9:
        if (TextUtils.equals(r0, r1) == true) goto L12;
        cancelLoadingSharedAudio();
    L12:
        if (this.loadingSharedAudio == false) goto L15;
        return;
    L15:
        if (this.sharedAudio.isEmpty() == false) goto L17;
    L19:
        this.loadingSharedAudio = true;
        TLRPC.TL_messages_searchGlobal r12 = new TLRPC.TL_messages_searchGlobal();
        r12.filter = new TLRPC.TL_inputMessagesFilterMusic();
        String r3 = this.query;
        if (r3 == null) goto L23;
        r2 = r3;
    L23:
        this.lastLoadingSharedAudioQuery = r2;
        r12.q = r2;
        r12.limit = 20;
        ArrayList<MessageObject> r22 = this.sharedAudio;
        if (r22 != null) goto L26;
    L28:
        r12.offset_rate = 0;
        r12.offset_id = 0;
        r12.offset_peer = new TLRPC.TL_inputPeerEmpty();
    L29:
        this.loadingSharedAudioRequestId = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r12, new SelectAudioAlert$$ExternalSyntheticLambda9(this));
        this.adapter.update(true);
        return;
    L26:
        if (r22.size() <= 0) goto L28;
        ArrayList<MessageObject> r23 = this.sharedAudio;
        MessageObject r24 = r23.get(r23.size() - 1);
        r12.offset_id = r24.getId();
        r12.offset_rate = this.nextSearchRate;
        long r25 = MessageObject.getPeerId(r24.messageOwner.peer_id);
        r12.offset_peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r25);
        goto L29
    L17:
        if (this.sharedAudioHasMore == true) goto L19;
        return;
    }

    private void loadSharedAudioDelayed() {
        AndroidUtilities.cancelRunOnUIThread(this.loadSharedAudioRunnable);
        AndroidUtilities.runOnUIThread(this.loadSharedAudioRunnable, 400);
    }

    private boolean matches(String r4, String r5, String r6) {
        if (r6 != null) goto L5;
        return false;
    L5:
        String r62 = r6.toLowerCase();
        if (r62.startsWith(r4) == false) goto L8;
    L16:
        return true;
    L8:
        if (r62.contains(" " + r4) == true) goto L16;
        String r42 = AndroidUtilities.translitSafe(r62);
        if (r42.startsWith(r5) == true) goto L16;
        if (r42.contains(" " + r5) == true) goto L16;
        return false;
    }

    private boolean needPlayMessage(MessageObject r4) {
        this.playingAudio = r4;
        ArrayList<MessageObject> r3 = new ArrayList();
        r3.add(r4);
        return MediaController.getInstance().setPlaylist(r3, r4, 0);
    }

    private void scrollToSearchTop() {
        LinearLayoutManager r0 = (LinearLayoutManager) this.recyclerListView.getLayoutManager();
        LinearSmoothScrollerCustom r1 = new LinearSmoothScrollerCustom(getContext(), 2);
        r1.setTargetPosition(1);
        r1.setOffset((AndroidUtilities.statusBarHeight + ActionBar.getCurrentActionBarHeight()) - AndroidUtilities.dp(1.0f));
        r0.startSmoothScroll(r1);
    }

    private void updateSearchY() {
        float r0 = AndroidUtilities.displaySize.y;
        boolean r1 = false;
        int r2 = 0;
    L4:
        if (r2 >= this.recyclerListView.getChildCount()) goto L11;
        View r3 = this.recyclerListView.getChildAt(r2);
        if (this.recyclerListView.getChildAdapterPosition(r3) < 1) goto L10;
        if (r3.getY() >= r0) goto L10;
        r0 = r3.getY();
    L10:
        r2 = r2 + 1;
        goto L4
    L11:
        this.frameLayout.setTranslationY(Math.max(AndroidUtilities.statusBarHeight + ActionBar.getCurrentActionBarHeight(), r0));
        BoolAnimator r6 = this.animatorFadeVisible;
        if (r0 > (AndroidUtilities.statusBarHeight + ActionBar.getCurrentActionBarHeight())) goto L14;
        r1 = true;
    L14:
        r6.setValue(r1, true);
    }

    public void blur3_InvalidateBlur() {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        return;
    L5:
        if (this.scrollableViewNoiseSuppressor == null) goto L10;
        this.iBlur3PositionActionBar.set(0.0f, ActionBar.getCurrentActionBarHeight() + AndroidUtilities.statusBarHeight, this.containerView.getMeasuredWidth(), (ActionBar.getCurrentActionBarHeight() + AndroidUtilities.statusBarHeight) + AndroidUtilities.dp(64.0f));
        this.scrollableViewNoiseSuppressor.setupRenderNodes(this.iBlur3PositionsMerged, RectFMergeBounding.mergeOverlapping(this.iBlur3Positions, 1, this.iBlur3PositionsMerged));
        this.scrollableViewNoiseSuppressor.invalidateResultRenderNodes(this.iBlur3Capture, this.containerView.getMeasuredWidth(), this.containerView.getMeasuredHeight());
        return;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r8) {
        UniversalAdapter r0 = new UniversalAdapter(r8, getContext(), this.currentAccount, 0, new SelectAudioAlert$$ExternalSyntheticLambda0(this), this.resourcesProvider);
        this.adapter = r0;
        r0.setApplyBackground(false);
        return this.adapter;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.musicListLoaded) goto L6;
        this.adapter.update(true);
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        super.dismiss();
        if (this.playingAudio != null) goto L5;
    L7:
        this.playingAudio = null;
        return;
    L5:
        if (MediaController.getInstance().isPlayingMessage(this.playingAudio) == false) goto L7;
        MediaController.getInstance().cleanupPlayer(true, true);
        goto L7
    }

    public void fillItems(ArrayList<UItem> r12, UniversalAdapter r13) {
        int r0 = 1;
        r13.itemsOffset = 1;
        int r2 = AndroidUtilities.dp(64.0f);
        r12.add(UItem.asSpace(AndroidUtilities.dp(64.0f)));
        if (this.local == false) goto L5;
    L8:
        SelectAudioAlert r3 = this;
        ArrayList<UItem> r5 = r12;
        r2 = r2 + r3.addSection(true, r5, LocaleController.getString(R.string.AudioSearchLocal), this.localAudio, false, false, -1);
    L10:
        if (r3.local == false) goto L12;
    L36:
        int r11 = r5.size();
        if (r3.local == false) goto L39;
    L43:
        if (r11 <= r0) goto L45;
    L52:
        r5.add(UItem.asShadow(null));
        r5.add(UItem.asSpace(Math.max(0, (((AndroidUtilities.displaySize.y - (r2 + AndroidUtilities.dp(12.0f))) - AndroidUtilities.statusBarHeight) - ActionBar.getCurrentActionBarHeight()) + AndroidUtilities.dp(24.0f))));
        return;
    L45:
        if (TextUtils.isEmpty(r3.query) == false) goto L47;
        r5.add(ChatAttachAlertAudioLayout.EmptyView.Factory.as(LocaleController.getString(R.string.NoAudioFound), LocaleController.getString(R.string.NoAudioFilesInfo)));
        goto L52
    L47:
        String r112 = LocaleController.getString(R.string.NoAudioFound);
        if (r3.query.length() < 3) goto L50;
        int r132 = R.string.NoAudioFoundInfo2;
    L51:
        r5.add(ChatAttachAlertAudioLayout.EmptyView.Factory.as(r112, AndroidUtilities.replaceTags(LocaleController.formatString(r132, new Object[]{r3.query}))));
        goto L52
    L50:
        r132 = R.string.NoAudioFoundInfo;
        goto L51
    L39:
        if (TextUtils.isEmpty(r3.query) == false) goto L43;
        if (r3.withoutSavedMusic == true) goto L43;
        r0 = 2;
        goto L43
    L12:
        if (TextUtils.isEmpty(r3.query) == false) goto L17;
        if (r3.withoutSavedMusic == true) goto L17;
        r13.whiteSectionStart();
        r5.add(UItem.asButton(1, R.drawable.msg2_folder, LocaleController.getString(R.string.StoryMusicSelectFromFiles)).accent());
        r13.whiteSectionEnd();
        r2 = r2 + AndroidUtilities.dp(50.0f);
    L17:
        if (r3.withoutSavedMusic == false) goto L19;
    L21:
        String r6 = LocaleController.getString(R.string.AudioSearchChats);
        ArrayList<MessageObject> r7 = r3.sharedAudio;
        if (r3.willLoadSharedAudio == false) goto L24;
    L27:
        boolean r8 = true;
    L28:
        int r22 = r2 + r3.addSection(false, r5, r6, r7, r8, r3.sharedAudioHasMore, 3);
        String r62 = LocaleController.getString(R.string.AudioSearchGlobal);
        ArrayList<MessageObject> r72 = r3.globalAudio;
        if (r3.willLoadGlobalAudio == false) goto L31;
    L34:
        boolean r82 = true;
    L35:
        r2 = r22 + r3.addSection(false, r5, r62, r72, r82, r3.globalAudioHasMore, 4);
        goto L36
    L31:
        if (r3.loadingGlobalAudio == true) goto L34;
        r82 = false;
        goto L35
    L24:
        if (r3.loadingSharedAudio == true) goto L27;
        r8 = false;
        goto L28
    L19:
        if (r3.savedMusicList == null) goto L21;
        String r63 = LocaleController.getString(R.string.AudioSearchProfile);
        MessagesController.SavedMusicList r113 = r3.savedMusicList;
        r2 = r2 + r3.addSection(true, r5, r63, r113.list, r113.loading, !r113.endReached, 2);
        goto L21
    L5:
        if (this.withoutSavedMusic == true) goto L8;
        r3 = this;
        r5 = r12;
        goto L10
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public int getObserverTag() {
        return this.tag;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return LocaleController.getString(R.string.StoryMusicTitle2);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.musicListLoaded);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.musicListLoaded);
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        if (r1 != 0) goto L9;
        this.fadeView.setAlpha(r2);
        View r0 = this.fadeView;
        if (r2 <= 0.0f) goto L6;
        int r12 = 0;
    L7:
        r0.setVisibility(r12);
        return;
    L6:
        r12 = 4;
        goto L7
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onFailedDownload(String r1, boolean r2) {
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressDownload(String r1, long r2, long r4) {
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onProgressUpload(String r1, long r2, long r4, boolean r6) {
    }

    @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
    public void onSuccessDownload(String r2) {
        MessageObject r0 = this.downloadingMessageObject;
        if (r0 != null) goto L5;
        return;
    L5:
        if (TextUtils.equals(r0.getFileName(), r2) == false) goto L9;
        done(this.downloadingMessageObject);
        return;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public void preDrawInternal(Canvas r4, View r5) {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
    L12:
        updateSearchY();
        super.preDrawInternal(r4, r5);
        return;
    L5:
        if (this.scrollableViewNoiseSuppressor == null) goto L12;
        blur3_InvalidateBlur();
        BlurredBackgroundSourceRenderNode r0 = this.iBlur3SourceGlassFrosted;
        if (r0 == null) goto L9;
        r0.setSize(this.containerView.getMeasuredWidth(), this.containerView.getMeasuredHeight());
        this.iBlur3SourceGlassFrosted.updateDisplayListIfNeeded();
    L9:
        BlurredBackgroundSourceRenderNode r02 = this.iBlur3SourceGlass;
        if (r02 == null) goto L12;
        r02.setSize(this.containerView.getMeasuredWidth(), this.containerView.getMeasuredHeight());
        this.iBlur3SourceGlass.updateDisplayListIfNeeded();
        goto L12
    }

    public SelectAudioAlert withoutSavedMusic() {
        this.withoutSavedMusic = true;
        this.local = false;
        this.adapter.update(true);
        return this;
    }
}
