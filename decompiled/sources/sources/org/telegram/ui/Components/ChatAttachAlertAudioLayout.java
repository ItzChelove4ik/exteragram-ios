package org.telegram.ui.Components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.cast.HlsSegmentFormat;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AiTonesController$$ExternalSyntheticLambda0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.audioinfo.AudioInfo;
import org.telegram.messenger.utils.TextWatcherImpl;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.SharedAudioCell;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class ChatAttachAlertAudioLayout extends ChatAttachAlert.AttachAlertLayout implements NotificationCenter.NotificationCenterDelegate, FactorAnimator.Target {
    private int LOAD_MORE_SEARCH_CHATS;
    private int LOAD_MORE_SEARCH_GLOBAL;
    private int LOAD_MORE_SEARCH_PROFILE;
    private final BoolAnimator animatorFadeVisible;
    private ArrayList<MediaController.AudioEntry> audioEntries;
    private float currentPanTranslationProgress;
    private AudioSelectDelegate delegate;
    private final View fadeView;
    private boolean failedToResolveGlobalAudioBot;
    private ArrayList<MediaController.AudioEntry> foundGlobal;
    private ArrayList<MediaController.AudioEntry> foundInChats;
    private final FrameLayout frameLayout;
    private TLRPC.User globalAudioBot;
    private int globalAudioMessageId;
    private String globalAudioOffset;
    private String lastSearchChatsQuery;
    private String lastSearchGlobalQuery;
    private UniversalRecyclerView listView;
    private boolean loadingAudio;
    private boolean loadingSearchChats;
    private boolean loadingSearchGlobal;
    private int maxSelectedFiles;
    private MessageObject playingAudio;
    private int preMeasuredAvailableHeight;
    private ArrayList<MediaController.AudioEntry> profileEntries;
    private String query;
    private boolean resolvingGlobalAudioBot;
    private MessagesController.SavedMusicList savedMusicList;
    private boolean searchChatsHasMore;
    private int searchChatsNextRate;
    private int searchChatsRequestId;
    private final Runnable searchChatsRunnable;
    private final FragmentSearchField searchField;
    private boolean searchGlobalHasMore;
    private int searchGlobalRequestId;
    private final Runnable searchGlobalRunnable;
    private final HashSet<MediaController.AudioEntry> selectedAudios;
    private boolean sendPressed;
    private DialogsActivityTopPanelLayout topPanelLayout;
    private final Runnable updateWithSavingScrollRunnable;

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertAudioLayout$1, reason: invalid class name */
    public class AnonymousClass1 implements TextWatcherImpl {
        final /* synthetic */ ChatAttachAlertAudioLayout this$0;

        public AnonymousClass1(ChatAttachAlertAudioLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r7) {
            TextUtils.isEmpty(ChatAttachAlertAudioLayout.m9551$$Nest$fgetquery(this.this$0));
            ChatAttachAlertAudioLayout.m9557$$Nest$fputquery(this.this$0, r7.toString().trim());
            AndroidUtilities.cancelRunOnUIThread(ChatAttachAlertAudioLayout.m9552$$Nest$fgetsearchChatsRunnable(this.this$0));
            boolean r2 = true;
            if (TextUtils.isEmpty(ChatAttachAlertAudioLayout.m9551$$Nest$fgetquery(this.this$0)) == true) goto L14;
            ChatAttachAlertAudioLayout r72 = this.this$0;
            if (ChatAttachAlertAudioLayout.m9551$$Nest$fgetquery(r72) != null) goto L7;
        L9:
            boolean r4 = false;
        L10:
            ChatAttachAlertAudioLayout.m9555$$Nest$fputloadingSearchChats(r72, r4);
            if (TextUtils.equals(ChatAttachAlertAudioLayout.m9549$$Nest$fgetlastSearchChatsQuery(this.this$0), ChatAttachAlertAudioLayout.m9551$$Nest$fgetquery(this.this$0)) == true) goto L13;
            ChatAttachAlertAudioLayout.m9548$$Nest$fgetfoundInChats(this.this$0).clear();
            ChatAttachAlertAudioLayout.m9559$$Nest$fputsearchChatsNextRate(this.this$0, 0);
            ChatAttachAlertAudioLayout.m9558$$Nest$fputsearchChatsHasMore(this.this$0, false);
        L13:
            AndroidUtilities.runOnUIThread(ChatAttachAlertAudioLayout.m9552$$Nest$fgetsearchChatsRunnable(this.this$0), 1500);
            goto L14
        L7:
            if (ChatAttachAlertAudioLayout.m9551$$Nest$fgetquery(this.this$0).length() < 0) goto L9;
            r4 = true;
        L14:
            AndroidUtilities.cancelRunOnUIThread(ChatAttachAlertAudioLayout.m9553$$Nest$fgetsearchGlobalRunnable(this.this$0));
            if (TextUtils.isEmpty(ChatAttachAlertAudioLayout.m9551$$Nest$fgetquery(this.this$0)) == true) goto L28;
            ChatAttachAlertAudioLayout r73 = this.this$0;
            if (ChatAttachAlertAudioLayout.m9551$$Nest$fgetquery(r73) != null) goto L19;
        L23:
            r2 = false;
        L24:
            ChatAttachAlertAudioLayout.m9556$$Nest$fputloadingSearchGlobal(r73, r2);
            if (TextUtils.equals(ChatAttachAlertAudioLayout.m9550$$Nest$fgetlastSearchGlobalQuery(this.this$0), ChatAttachAlertAudioLayout.m9551$$Nest$fgetquery(this.this$0)) == true) goto L27;
            ChatAttachAlertAudioLayout.m9547$$Nest$fgetfoundGlobal(this.this$0).clear();
            ChatAttachAlertAudioLayout.m9560$$Nest$fputsearchGlobalHasMore(this.this$0, false);
        L27:
            AndroidUtilities.runOnUIThread(ChatAttachAlertAudioLayout.m9553$$Nest$fgetsearchGlobalRunnable(this.this$0), 1500);
            goto L28
        L19:
            if (ChatAttachAlertAudioLayout.m9551$$Nest$fgetquery(this.this$0).length() < 3) goto L23;
            if (TextUtils.isEmpty(MessagesController.getInstance(this.this$0.parentAlert.currentAccount).config.musicSearchUsername.get()) == true) goto L23;
        L28:
            ChatAttachAlertAudioLayout.m9561$$Nest$mupdateWithSavingScroll(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertAudioLayout$2, reason: invalid class name */
    public class AnonymousClass2 extends FragmentContextView {
        final /* synthetic */ ChatAttachAlertAudioLayout this$0;
        final /* synthetic */ FrameLayout val$fragmentContextViewWrapper;

        public AnonymousClass2(ChatAttachAlertAudioLayout r1, Context r2, BaseFragment r3, View r4, boolean r5, Theme.ResourcesProvider r6, FrameLayout r7) {
            this.this$0 = r1;
            this.val$fragmentContextViewWrapper = r7;
            super(r2, r3, r4, r5, r6);
        }

        @Override // org.telegram.ui.Components.FragmentContextView, android.view.View
        public void setVisibility(int r2) {
            DialogsActivityTopPanelLayout r0 = ChatAttachAlertAudioLayout.m9554$$Nest$fgettopPanelLayout(this.this$0);
            FrameLayout r1 = this.val$fragmentContextViewWrapper;
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

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertAudioLayout$3, reason: invalid class name */
    public class AnonymousClass3 extends UniversalRecyclerView {
        final /* synthetic */ ChatAttachAlertAudioLayout this$0;

        public AnonymousClass3(ChatAttachAlertAudioLayout r1, Context r2, int r3, int r4, Utilities.Callback2 r5, Utilities.Callback5 r6, Utilities.Callback5Return r7, Theme.ResourcesProvider r8) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8);
        }

        @Override // org.telegram.ui.Components.RecyclerListView
        public boolean allowSelectChildAtPosition(float r3, float r4) {
            int r32 = this.this$0.parentAlert.scrollOffsetY[0] + AndroidUtilities.dp(30.0f);
            if (this.this$0.parentAlert.inBubbleMode == true) goto L5;
            int r2 = AndroidUtilities.statusBarHeight;
        L7:
            if (r4 < (r32 + r2)) goto L10;
            return true;
        L10:
            return false;
        L5:
            r2 = 0;
            goto L7
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            ChatAttachAlertAudioLayout r0 = this.this$0;
            r0.parentAlert.updateLayout(r0, true, 0);
        }

        @Override // org.telegram.ui.Components.UniversalRecyclerView
        public void onLayoutUpdate() {
            ChatAttachAlertAudioLayout r3 = this.this$0;
            r3.parentAlert.updateLayout(r3, true, 0);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertAudioLayout$4, reason: invalid class name */
    public class AnonymousClass4 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ChatAttachAlertAudioLayout this$0;

        public AnonymousClass4(ChatAttachAlertAudioLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            ChatAttachAlertAudioLayout r0 = this.this$0;
            r0.parentAlert.updateLayout(r0, true, r3);
        }
    }

    public interface AudioSelectDelegate {
        void didSelectAudio(ArrayList<MessageObject> r1, CharSequence r2, boolean r3, int r4, int r5, long r6, boolean r8, long r9);
    }

    public static final class EmptyView extends FrameLayout implements Theme.Colorable {
        public BackupImageView imageView;
        public LinearLayout layout;
        private final Theme.ResourcesProvider resourcesProvider;
        public TextView subtitleView;
        public TextView titleView;

        public static final class Factory extends UItem.UItemFactory<EmptyView> {
            static {
                UItem.UItemFactory.setup(new Factory());
            }

            public Factory() {
            }

            public static UItem as(CharSequence r1, CharSequence r2) {
                UItem r0 = UItem.ofFactory(Factory.class);
                r0.text = r1;
                r0.subtext = r2;
                return r0;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
                ((EmptyView) r1).set(r2.text, r2.subtext);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return createView(r1, r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public EmptyView createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
                return new EmptyView(r1, r5);
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean isClickable() {
                return false;
            }

            @Override // org.telegram.ui.Components.UItem.UItemFactory
            public boolean isShadow() {
                return true;
            }
        }

        public EmptyView(Context r12, Theme.ResourcesProvider r13) {
            super(r12);
            this.resourcesProvider = r13;
            setPadding(0, AndroidUtilities.dp(42.0f), 0, AndroidUtilities.dp(42.0f));
            setTag(-33024);
            LinearLayout r132 = new LinearLayout(r12);
            this.layout = r132;
            r132.setOrientation(1);
            addView(this.layout, LayoutHelper.createLinear(-1, -2, 17));
            BackupImageView r133 = new BackupImageView(r12);
            this.imageView = r133;
            r133.setImageDrawable(new RLottieDrawable(R.raw.utyan_empty, AndroidUtilities.dp(120.0f), AndroidUtilities.dp(120.0f)));
            this.layout.addView(this.imageView, LayoutHelper.createLinear(120, 120, 17, 0, 0, 0, 0));
            TextView r134 = new TextView(r12);
            this.titleView = r134;
            r134.setTextSize(1, 20.0f);
            this.titleView.setTypeface(AndroidUtilities.bold());
            this.titleView.setGravity(17);
            this.layout.addView(this.titleView, LayoutHelper.createLinear(-1, -2, 17, 32, 12, 32, 8));
            TextView r135 = new TextView(r12);
            this.subtitleView = r135;
            r135.setTextSize(1, 14.0f);
            this.subtitleView.setGravity(17);
            this.layout.addView(this.subtitleView, LayoutHelper.createLinear(-1, -2, 17, 32, 0, 32, 0));
            updateColors();
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public /* bridge */ /* synthetic */ int[] getColorKeys() {
            return super.getColorKeys();
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }

        public void set(CharSequence r2, CharSequence r3) {
            this.titleView.setText(r2);
            this.subtitleView.setText(r3);
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public void updateColors() {
            this.titleView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
            this.subtitleView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, this.resourcesProvider));
        }
    }

    public static /* synthetic */ void $r8$lambda$07kd5YMNVnV269KaUOdeTu1eNb0(ChatAttachAlertAudioLayout r0, MessagesController r1, int r2, TLRPC.messages_Messages r3, TLRPC.TL_error r4) {
        r0.lambda$searchChats$5(r1, r2, r3, r4);
    }

    public static /* synthetic */ boolean $r8$lambda$3ut1ml4_4pIMZOrAENRRMXXD6vc(ChatAttachAlertAudioLayout r0, MessageObject r1) {
        return r0.needPlayMessage(r1);
    }

    public static /* synthetic */ void $r8$lambda$BNstykwanCyTOq3xfwdl0F_90Ag(ChatAttachAlertAudioLayout r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$KBSEvv7Wy2oDULjkon8hQKm_OLk(ChatAttachAlertAudioLayout r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onItemClick(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$UVlDdqHH3n_cijYfzqLfTrpCAXE(ChatAttachAlertAudioLayout r0, MessagesController r1, Long r2) {
        r0.lambda$searchGlobal$6(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$VHwh4jigJmQ9R6GEREQq3qsEvlc(ChatAttachAlertAudioLayout r0, UItem r1, View r2, int r3, float r4, float r5) {
        return r0.onItemLongClick(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$_vqpvmU_qHEFD07UoWae1pOD6us(ChatAttachAlertAudioLayout r0, ChatAttachAlert r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$gX5P-qWSBfmDsmj2Ne2TVHFPsSI, reason: not valid java name */
    public static /* synthetic */ void m9544$r8$lambda$gX5PqWSBfmDsmj2Ne2TVHFPsSI(ChatAttachAlertAudioLayout r0, ArrayList r1, boolean r2, int r3, int r4, long r5, boolean r7, Long r8) {
        r0.lambda$sendSelectedItems$2(r1, r2, r3, r4, r5, r7, r8);
    }

    public static /* synthetic */ void $r8$lambda$jmYh02CihzT8FymZ6I4bmVt3ggk(ChatAttachAlertAudioLayout r0) {
        r0.lambda$new$1();
    }

    public static /* synthetic */ void $r8$lambda$ls29GwDa25w_ng2xKDudavXBdak(ChatAttachAlertAudioLayout r0) {
        r0.searchGlobal();
    }

    /* renamed from: $r8$lambda$q3OUdZ_EISEiOOlRSk2vq-fTEPc, reason: not valid java name */
    public static /* synthetic */ void m9545$r8$lambda$q3OUdZ_EISEiOOlRSk2vqfTEPc(ChatAttachAlertAudioLayout r0) {
        r0.searchChats();
    }

    public static /* synthetic */ void $r8$lambda$snpivLdi_zkvzeKeBluSUuwkg9M(ChatAttachAlertAudioLayout r0, MessagesController r1, int r2, TLRPC.messages_BotResults r3, TLRPC.TL_error r4) {
        r0.lambda$searchGlobal$7(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$x8qXmOVU7vaU2QygVZBuz01ymdQ(ChatAttachAlertAudioLayout r0, ArrayList r1) {
        r0.lambda$loadAudio$3(r1);
    }

    /* renamed from: $r8$lambda$z-chNqwORDOmlqk3QXY9cCO7PJI, reason: not valid java name */
    public static /* synthetic */ void m9546$r8$lambda$zchNqwORDOmlqk3QXY9cCO7PJI(ChatAttachAlertAudioLayout r0) {
        r0.lambda$loadAudio$4();
    }

    /* renamed from: -$$Nest$fgetfoundGlobal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m9547$$Nest$fgetfoundGlobal(ChatAttachAlertAudioLayout r0) {
        return r0.foundGlobal;
    }

    /* renamed from: -$$Nest$fgetfoundInChats, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m9548$$Nest$fgetfoundInChats(ChatAttachAlertAudioLayout r0) {
        return r0.foundInChats;
    }

    /* renamed from: -$$Nest$fgetlastSearchChatsQuery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m9549$$Nest$fgetlastSearchChatsQuery(ChatAttachAlertAudioLayout r0) {
        return r0.lastSearchChatsQuery;
    }

    /* renamed from: -$$Nest$fgetlastSearchGlobalQuery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m9550$$Nest$fgetlastSearchGlobalQuery(ChatAttachAlertAudioLayout r0) {
        return r0.lastSearchGlobalQuery;
    }

    /* renamed from: -$$Nest$fgetquery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m9551$$Nest$fgetquery(ChatAttachAlertAudioLayout r0) {
        return r0.query;
    }

    /* renamed from: -$$Nest$fgetsearchChatsRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m9552$$Nest$fgetsearchChatsRunnable(ChatAttachAlertAudioLayout r0) {
        return r0.searchChatsRunnable;
    }

    /* renamed from: -$$Nest$fgetsearchGlobalRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m9553$$Nest$fgetsearchGlobalRunnable(ChatAttachAlertAudioLayout r0) {
        return r0.searchGlobalRunnable;
    }

    /* renamed from: -$$Nest$fgettopPanelLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DialogsActivityTopPanelLayout m9554$$Nest$fgettopPanelLayout(ChatAttachAlertAudioLayout r0) {
        return r0.topPanelLayout;
    }

    /* renamed from: -$$Nest$fputloadingSearchChats, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9555$$Nest$fputloadingSearchChats(ChatAttachAlertAudioLayout r0, boolean r1) {
        r0.loadingSearchChats = r1;
    }

    /* renamed from: -$$Nest$fputloadingSearchGlobal, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9556$$Nest$fputloadingSearchGlobal(ChatAttachAlertAudioLayout r0, boolean r1) {
        r0.loadingSearchGlobal = r1;
    }

    /* renamed from: -$$Nest$fputquery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9557$$Nest$fputquery(ChatAttachAlertAudioLayout r0, String r1) {
        r0.query = r1;
    }

    /* renamed from: -$$Nest$fputsearchChatsHasMore, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9558$$Nest$fputsearchChatsHasMore(ChatAttachAlertAudioLayout r0, boolean r1) {
        r0.searchChatsHasMore = r1;
    }

    /* renamed from: -$$Nest$fputsearchChatsNextRate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9559$$Nest$fputsearchChatsNextRate(ChatAttachAlertAudioLayout r0, int r1) {
        r0.searchChatsNextRate = r1;
    }

    /* renamed from: -$$Nest$fputsearchGlobalHasMore, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9560$$Nest$fputsearchGlobalHasMore(ChatAttachAlertAudioLayout r0, boolean r1) {
        r0.searchGlobalHasMore = r1;
    }

    /* renamed from: -$$Nest$mupdateWithSavingScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9561$$Nest$mupdateWithSavingScroll(ChatAttachAlertAudioLayout r0) {
        r0.updateWithSavingScroll();
    }

    public ChatAttachAlertAudioLayout(ChatAttachAlert r21, Context r22, Theme.ResourcesProvider r23) {
        super(r21, r22, r23);
        this.animatorFadeVisible = new BoolAnimator(0, this, CubicBezierInterpolator.EASE_OUT_QUINT, 380);
        this.maxSelectedFiles = -1;
        this.audioEntries = new ArrayList();
        this.selectedAudios = new HashSet();
        this.profileEntries = new ArrayList();
        this.foundInChats = new ArrayList();
        this.foundGlobal = new ArrayList();
        this.LOAD_MORE_SEARCH_CHATS = 1;
        this.LOAD_MORE_SEARCH_GLOBAL = 2;
        this.LOAD_MORE_SEARCH_PROFILE = 3;
        this.updateWithSavingScrollRunnable = new ChatAttachAlertAudioLayout$$ExternalSyntheticLambda0(this);
        this.searchChatsRequestId = -1;
        this.searchChatsRunnable = new ChatAttachAlertAudioLayout$$ExternalSyntheticLambda1(this);
        this.searchGlobalRequestId = -1;
        this.searchGlobalRunnable = new ChatAttachAlertAudioLayout$$ExternalSyntheticLambda2(this);
        this.globalAudioMessageId = -1000000000;
        NotificationCenter.getInstance(this.parentAlert.currentAccount).addObserver(this, NotificationCenter.messagePlayingDidReset);
        NotificationCenter.getInstance(this.parentAlert.currentAccount).addObserver(this, NotificationCenter.messagePlayingDidStart);
        NotificationCenter.getInstance(this.parentAlert.currentAccount).addObserver(this, NotificationCenter.messagePlayingPlayStateChanged);
        NotificationCenter.getInstance(this.parentAlert.currentAccount).addObserver(this, NotificationCenter.musicListLoaded);
        loadAudio();
        ChatAttachAlert.SearchFadeView r0 = new ChatAttachAlert.SearchFadeView(r22, Theme.key_windowBackgroundWhite, r23);
        this.fadeView = r0;
        r0.setVisibility(4);
        FrameLayout r4 = new FrameLayout(r22);
        this.frameLayout = r4;
        ChatAttachAlert.AttachSearchField r2 = new ChatAttachAlert.AttachSearchField(r22, this.parentAlert, r23);
        this.searchField = r2;
        r2.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f));
        r2.editText.addTextChangedListener(new AnonymousClass1(this));
        r2.editText.setHint(LocaleController.getString(R.string.SearchMusic));
        r4.addView(r0, LayoutHelper.createFrameMatchParent());
        FrameLayout.LayoutParams r02 = LayoutHelper.createFrame(-1, 48.0f, 51, 7.0f, 8.0f, 7.0f, 4.0f);
        ((ViewGroup.MarginLayoutParams) r02).topMargin += AndroidUtilities.statusBarHeight;
        r4.addView(r2, r02);
        DialogsActivityTopPanelLayout r03 = new DialogsActivityTopPanelLayout(r22);
        this.topPanelLayout = r03;
        r03.setPadding(AndroidUtilities.dp(11.0f), AndroidUtilities.dp(21.0f), AndroidUtilities.dp(11.0f), AndroidUtilities.dp(21.0f));
        this.topPanelLayout.setOnAnimatedHeightChangedListener(new ChatAttachAlertAudioLayout$$ExternalSyntheticLambda3(this, r21));
        if (r21 != null) goto L5;
    L7:
        FrameLayout r12 = r4;
    L8:
        FrameLayout.LayoutParams r04 = LayoutHelper.createFrame(-1, -2.0f, 51, 0.0f, 8.0f, 0.0f, 4.0f);
        ((ViewGroup.MarginLayoutParams) r04).topMargin += AndroidUtilities.statusBarHeight + AndroidUtilities.dp(27.0f);
        r12.addView(this.topPanelLayout, r04);
        AnonymousClass3 r05 = new AnonymousClass3(this, r22, r21.currentAccount, 0, new ChatAttachAlertAudioLayout$$ExternalSyntheticLambda4(this), new ChatAttachAlertAudioLayout$$ExternalSyntheticLambda5(this), new ChatAttachAlertAudioLayout$$ExternalSyntheticLambda6(this), r23);
        this.listView = r05;
        r05.adapter.setApplyBackground(false);
        this.listView.setSections();
        UniversalRecyclerView r06 = this.listView;
        this.iBlur3Capture = r06;
        this.iBlur3CaptureView = r06;
        this.occupyStatusBar = true;
        this.occupyNavigationBar = true;
        r06.setClipToPadding(false);
        this.listView.setHorizontalScrollBarEnabled(false);
        this.listView.setVerticalScrollBarEnabled(false);
        addView(this.listView, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 0.0f, 0.0f, 0.0f));
        this.listView.setGlowColor(getThemedColor(Theme.key_dialogScrollGlow));
        this.listView.setOnScrollListener(new AnonymousClass4(this));
        addView(r12, LayoutHelper.createFrame(-1, 200, 51));
        this.listView.adapter.update(false);
        checkUi_listViewPadding();
        int r24 = this.parentAlert.currentAccount;
        this.savedMusicList = new MessagesController.SavedMusicList(r24, UserConfig.getInstance(r24).getClientUserId());
        return;
    L5:
        if (r21.baseFragment == null) goto L7;
        FrameLayout r7 = new FrameLayout(r22);
        this.topPanelLayout.addView(r7);
        this.topPanelLayout.setViewVisible(r7, true, false);
        AnonymousClass2 r07 = new AnonymousClass2(this, r22, r21.baseFragment, r4, false, r23, r7);
        r12 = r4;
        r7.addView(r07);
        this.topPanelLayout.setCallFragmentContextView(r07);
        goto L8
    }

    private void checkUi_listViewPadding() {
        if (this.parentAlert.sizeNotifierFrameLayout.measureKeyboardHeight() <= AndroidUtilities.dp(20.0f)) goto L6;
        int r0 = AndroidUtilities.dp(8.0f);
        this.parentAlert.setAllowNestedScroll(false);
    L12:
        this.listView.setPadding(0, (int) (((r0 + AndroidUtilities.statusBarHeight) + AndroidUtilities.dp(56.0f)) + this.topPanelLayout.getAnimatedHeightWithPadding(0.0f)), 0, this.listPaddingBottom);
        return;
    L6:
        if (AndroidUtilities.isTablet() == true) goto L10;
        Point r02 = AndroidUtilities.displaySize;
        if (r02.x <= r02.y) goto L10;
        r0 = (int) (this.preMeasuredAvailableHeight / 3.5f);
    L11:
        this.parentAlert.setAllowNestedScroll(true);
    L10:
        r0 = (this.preMeasuredAvailableHeight / 5) * 2;
        goto L11
    }

    private void convertProfileMusicToEntries() {
        MessagesController.SavedMusicList r0 = this.savedMusicList;
        if (r0 == null) goto L18;
        int r02 = r0.list.size();
        if (r02 >= this.profileEntries.size()) goto L8;
        ArrayList<MediaController.AudioEntry> r1 = this.profileEntries;
        r1.subList(r02, r1.size()).clear();
    L8:
        int r12 = 0;
    L9:
        if (r12 >= r02) goto L22;
        int r2 = this.profileEntries.size();
        ArrayList<MediaController.AudioEntry> r3 = this.profileEntries;
        if (r12 < r2) goto L13;
        MediaController.AudioEntry r22 = new MediaController.AudioEntry();
        r3.add(r22);
    L15:
        if (r22.messageObject == this.savedMusicList.list.get(r12)) goto L17;
        MessageObject r32 = this.savedMusicList.list.get(r12);
        int r4 = this.globalAudioMessageId;
        this.globalAudioMessageId = r4 - 1;
        r22.id = r4;
        r22.messageObject = r32;
    L17:
        r12 = r12 + 1;
        goto L9
    L13:
        r22 = r3.get(r12);
        goto L15
    L22:
        return;
    }

    private void fillItems(ArrayList<UItem> r19, UniversalAdapter r20) {
        r19.add(UItem.asSpace(-100, AndroidUtilities.dp(1.0f)));
        int r2 = r19.size();
        if (TextUtils.isEmpty(this.query) == false) goto L65;
        r20.whiteSectionStart();
        int r3 = 0;
    L6:
        if (r3 >= this.audioEntries.size()) goto L9;
        MediaController.AudioEntry r13 = this.audioEntries.get(r3);
        r13.messageObject.setQuery(null);
        r19.add(SharedAudioCell.Factory.as(r13, new ChatAttachAlertAudioLayout$$ExternalSyntheticLambda10(this)).setChecked(this.selectedAudios.contains(r13)).setId(-1));
        r3 = r3 + 1;
        goto L6
    L9:
        if (this.loadingAudio == false) goto L11;
        r19.add(UItem.asFlicker(11, 4));
        r19.add(UItem.asFlicker(12, 4));
        r19.add(UItem.asFlicker(13, 4));
    L11:
        r20.whiteSectionEnd();
        convertProfileMusicToEntries();
        if (this.savedMusicList == null) goto L33;
        ArrayList<MediaController.AudioEntry> r32 = this.profileEntries;
        if (r32 == null) goto L33;
        if (r32.isEmpty() == true) goto L33;
        if (r19.size() <= r2) goto L20;
        r19.add(UItem.asShadow(-98, null));
    L20:
        r20.whiteSectionStart();
        r19.add(UItem.asHeader(45, LocaleController.getString(R.string.AudioSearchProfile)));
        int r33 = 0;
    L22:
        if (r33 >= this.profileEntries.size()) goto L25;
        MediaController.AudioEntry r132 = this.profileEntries.get(r33);
        r19.add(SharedAudioCell.Factory.as(r132, new ChatAttachAlertAudioLayout$$ExternalSyntheticLambda10(this)).setChecked(this.selectedAudios.contains(r132)));
        r33 = r33 + 1;
        goto L22
    L25:
        if (this.savedMusicList.loading == false) goto L27;
        r19.add(UItem.asFlicker(41, 4));
        r19.add(UItem.asFlicker(42, 4));
        r19.add(UItem.asFlicker(43, 4));
    L27:
        MessagesController.SavedMusicList r34 = this.savedMusicList;
        if (r34.loading == false) goto L30;
    L32:
        r20.whiteSectionEnd();
        goto L33
    L30:
        if (r34.endReached == true) goto L32;
        r19.add(UItem.asButton(this.LOAD_MORE_SEARCH_PROFILE, R.drawable.arrow_more, LocaleController.getString(R.string.ShowMore)).accent());
    L33:
        ArrayList<MediaController.AudioEntry> r35 = this.foundInChats;
        if (r35 == null) goto L168;
        if (r35.isEmpty() == false) goto L42;
        if (this.searchChatsRequestId >= 0) goto L42;
        if (this.loadingSearchChats == false) goto L168;
    L42:
        if (r19.size() <= r2) goto L44;
        r19.add(UItem.asShadow(-98, null));
    L44:
        r20.whiteSectionStart();
        if (this.searchChatsRequestId >= 0) goto L49;
        if (this.loadingSearchChats == true) goto L49;
    L51:
        int r7 = 20;
    L52:
        r19.add(UItem.asHeader(r7, LocaleController.getString(R.string.AudioSearchChats)));
        int r11 = 0;
    L54:
        if (r11 >= this.foundInChats.size()) goto L57;
        MediaController.AudioEntry r36 = this.foundInChats.get(r11);
        r36.messageObject.setQuery(this.query);
        r19.add(SharedAudioCell.Factory.as(r36, new ChatAttachAlertAudioLayout$$ExternalSyntheticLambda10(this)).setChecked(this.selectedAudios.contains(r36)));
        r11 = r11 + 1;
        goto L54
    L57:
        if (this.searchChatsRequestId < 0) goto L59;
    L60:
        r19.add(UItem.asFlicker(21, 4));
        r19.add(UItem.asFlicker(22, 4));
        r19.add(UItem.asFlicker(23, 4));
    L62:
        if (this.searchChatsHasMore == false) goto L64;
        r19.add(UItem.asButton(this.LOAD_MORE_SEARCH_CHATS, R.drawable.arrow_more, LocaleController.getString(R.string.ShowMore)).accent());
    L64:
        r20.whiteSectionEnd();
        goto L168
    L59:
        if (this.loadingSearchChats == false) goto L62;
    L49:
        if (this.foundInChats.isEmpty() == false) goto L51;
        r7 = 25;
    L168:
        if (r19.size() <= r2) goto L170;
    L179:
        r19.add(UItem.asShadow(-99, null));
        return;
    L170:
        if (this.loadingAudio == true) goto L179;
        if (isSearching() == false) goto L178;
        String r22 = LocaleController.getString(R.string.NoAudioFound);
        if (this.query.length() < 3) goto L176;
        int r37 = R.string.NoAudioFoundInfo2;
    L177:
        r19.add(EmptyView.Factory.as(r22, AndroidUtilities.replaceTags(LocaleController.formatString(r37, new Object[]{this.query}))));
        goto L179
    L176:
        r37 = R.string.NoAudioFoundInfo;
        goto L177
    L178:
        r19.add(EmptyView.Factory.as(LocaleController.getString(R.string.NoAudioFiles), LocaleController.getString(R.string.NoAudioFilesInfo)));
        goto L179
    L65:
        String r38 = this.query.toLowerCase();
        String r133 = AndroidUtilities.translitSafe(r38);
        int r14 = 0;
        boolean r15 = false;
    L67:
        if (r14 >= this.audioEntries.size()) goto L104;
        MediaController.AudioEntry r72 = this.audioEntries.get(r14);
        String r8 = r72.author;
        if (r8 == null) goto L79;
        String r82 = r8.toLowerCase();
        String r4 = AndroidUtilities.translitSafe(r82);
        if (r82.startsWith(r38) == false) goto L73;
    L78:
        boolean r42 = true;
    L80:
        String r5 = r72.title;
        if (r5 == null) goto L95;
        String r52 = r5.toLowerCase();
        String r83 = AndroidUtilities.translitSafe(r52);
        if (r42 == false) goto L85;
    L94:
        r42 = true;
        goto L95
    L85:
        if (r52.startsWith(r38) == true) goto L94;
        if (r52.contains(" " + r38) == true) goto L94;
        if (r83.startsWith(r133) == true) goto L94;
        if (r83.contains(" " + r133) == true) goto L94;
        r42 = false;
    L95:
        if (r42 == false) goto L103;
        if (r15 == false) goto L99;
    L102:
        r72.messageObject.setQuery(this.query);
        r19.add(SharedAudioCell.Factory.as(r72, new ChatAttachAlertAudioLayout$$ExternalSyntheticLambda10(this)).setChecked(this.selectedAudios.contains(r72)).setId(10));
        goto L103
    L99:
        if (r19.size() <= r2) goto L101;
        r19.add(UItem.asShadow(-97, null));
    L101:
        r20.whiteSectionStart();
        r19.add(UItem.asHeader(10, LocaleController.getString(R.string.AudioSearchLocal)));
        r15 = true;
    L103:
        r14 = r14 + 1;
        goto L67
    L73:
        if (r82.contains(" " + r38) == true) goto L78;
        if (r4.startsWith(r133) == true) goto L78;
        if (r4.contains(" " + r133) == true) goto L78;
    L79:
        r42 = false;
        goto L80
    L104:
        r20.whiteSectionEnd();
        ArrayList<MediaController.AudioEntry> r39 = this.foundInChats;
        if (r39 != null) goto L107;
    L136:
        ArrayList<MediaController.AudioEntry> r310 = this.foundGlobal;
        if (r310 == null) goto L168;
        if (r310.isEmpty() == false) goto L145;
        if (this.searchGlobalRequestId >= 0) goto L145;
        if (this.loadingSearchGlobal == false) goto L168;
    L145:
        if (r19.size() <= r2) goto L147;
        r19.add(UItem.asShadow(-96, null));
    L147:
        r20.whiteSectionStart();
        if (this.searchGlobalRequestId >= 0) goto L152;
        if (this.loadingSearchGlobal == true) goto L152;
    L154:
        int r311 = 30;
    L155:
        r19.add(UItem.asHeader(r311, LocaleController.getString(R.string.AudioSearchGlobal)));
        int r312 = this.foundGlobal.size();
        int r112 = 0;
    L156:
        if (r112 >= r312) goto L159;
        MediaController.AudioEntry r43 = this.foundGlobal.get(r112);
        r43.messageObject.setQuery(this.query);
        r19.add(SharedAudioCell.Factory.as(r43, new ChatAttachAlertAudioLayout$$ExternalSyntheticLambda10(this)).setChecked(this.selectedAudios.contains(r43)));
        r112 = r112 + 1;
        goto L156
    L159:
        if (this.searchGlobalRequestId < 0) goto L161;
    L162:
        r19.add(UItem.asFlicker(31, 4));
        r19.add(UItem.asFlicker(32, 4));
        r19.add(UItem.asFlicker(33, 4));
    L164:
        if (this.searchGlobalHasMore == false) goto L166;
        r19.add(UItem.asButton(this.LOAD_MORE_SEARCH_GLOBAL, R.drawable.arrow_more, LocaleController.getString(R.string.ShowMore)).accent());
    L166:
        r20.whiteSectionEnd();
        goto L168
    L161:
        if (this.loadingSearchGlobal == false) goto L164;
    L152:
        if (this.foundGlobal.isEmpty() == false) goto L154;
        r311 = 35;
        goto L155
    L107:
        if (r39.isEmpty() == false) goto L113;
        if (this.searchChatsRequestId >= 0) goto L113;
        if (this.loadingSearchChats == false) goto L136;
    L113:
        if (r19.size() <= r2) goto L115;
        r19.add(UItem.asShadow(-98, null));
    L115:
        r20.whiteSectionStart();
        if (this.searchChatsRequestId >= 0) goto L120;
        if (this.loadingSearchChats == true) goto L120;
    L122:
        int r73 = 20;
    L123:
        r19.add(UItem.asHeader(r73, LocaleController.getString(R.string.AudioSearchChats)));
        int r313 = 0;
    L125:
        if (r313 >= this.foundInChats.size()) goto L128;
        MediaController.AudioEntry r44 = this.foundInChats.get(r313);
        r44.messageObject.setQuery(this.query);
        r19.add(SharedAudioCell.Factory.as(r44, new ChatAttachAlertAudioLayout$$ExternalSyntheticLambda10(this)).setChecked(this.selectedAudios.contains(r44)));
        r313 = r313 + 1;
        goto L125
    L128:
        if (this.searchChatsRequestId < 0) goto L130;
    L131:
        r19.add(UItem.asFlicker(21, 4));
        r19.add(UItem.asFlicker(22, 4));
        r19.add(UItem.asFlicker(23, 4));
    L133:
        if (this.searchChatsHasMore == false) goto L135;
        r19.add(UItem.asButton(this.LOAD_MORE_SEARCH_CHATS, R.drawable.arrow_more, LocaleController.getString(R.string.ShowMore)).accent());
    L135:
        r20.whiteSectionEnd();
        goto L136
    L130:
        if (this.loadingSearchChats == false) goto L133;
    L120:
        if (this.foundInChats.isEmpty() == false) goto L122;
        r73 = 25;
        goto L123
    }

    private boolean isSearching() {
        return !TextUtils.isEmpty(this.query);
    }

    private /* synthetic */ void lambda$loadAudio$3(ArrayList r2) {
        this.loadingAudio = false;
        this.audioEntries = r2;
        updateWithSavingScroll();
    }

    private /* synthetic */ void lambda$loadAudio$4() {
        String[] r8 = {"_id", "artist", "title", "_data", "duration", "album"};
        ArrayList r1 = new ArrayList();
        Cursor r2 = ApplicationLoader.applicationContext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, r8, "is_music != 0", null, "title");     // Catch: Exception -> L28
        int r0 = -2000000000;
    L42:
        if (r2.moveToNext() == false) goto L26;
        MediaController.AudioEntry r3 = new MediaController.AudioEntry();     // Catch: Throwable -> L21
        r3.id = r2.getInt(0);     // Catch: Throwable -> L21
        r3.author = r2.getString(1);     // Catch: Throwable -> L21
        r3.title = r2.getString(2);     // Catch: Throwable -> L21
        r3.path = r2.getString(3);     // Catch: Throwable -> L21
        r3.duration = (int) (r2.getLong(4) / 1000);     // Catch: Throwable -> L21
        r3.genre = r2.getString(5);     // Catch: Throwable -> L21
        File r7 = new File(r3.path);     // Catch: Throwable -> L21
        TLRPC.TL_message r82 = new TLRPC.TL_message();     // Catch: Throwable -> L21
        r82.out = true;     // Catch: Throwable -> L21
        r82.id = r0;     // Catch: Throwable -> L21
        r82.peer_id = new TLRPC.TL_peerUser();     // Catch: Throwable -> L21
        TLRPC.TL_peerUser r11 = new TLRPC.TL_peerUser();     // Catch: Throwable -> L21
        r82.from_id = r11;     // Catch: Throwable -> L21
        TLRPC.Peer r12 = r82.peer_id;     // Catch: Throwable -> L21
        long r13 = UserConfig.getInstance(this.parentAlert.currentAccount).getClientUserId();     // Catch: Throwable -> L21
        r11.user_id = r13;     // Catch: Throwable -> L21
        r12.user_id = r13;     // Catch: Throwable -> L21
        r82.date = (int) (System.currentTimeMillis() / 1000);     // Catch: Throwable -> L21
        r82.message = _UrlKt.FRAGMENT_ENCODE_SET;     // Catch: Throwable -> L21
        r82.attachPath = r3.path;     // Catch: Throwable -> L21
        TLRPC.TL_messageMediaDocument r9 = new TLRPC.TL_messageMediaDocument();     // Catch: Throwable -> L21
        r82.media = r9;     // Catch: Throwable -> L21
        r9.flags |= 3;
        r9.document = new TLRPC.TL_document();     // Catch: Throwable -> L21
        r82.flags |= 768;
        String r92 = FileLoader.getFileExtension(r7);     // Catch: Throwable -> L21
        TLRPC.Document r10 = r82.media.document;     // Catch: Throwable -> L21
        r10.id = 0;     // Catch: Throwable -> L21
        r10.access_hash = 0;     // Catch: Throwable -> L21
        r10.file_reference = new byte[0];     // Catch: Throwable -> L21
        r10.date = r82.date;     // Catch: Throwable -> L21
        StringBuilder r112 = new StringBuilder();     // Catch: Throwable -> L21
        r112.append("audio/");     // Catch: Throwable -> L21
        if (r92.length() > 0) goto L11;
        r92 = HlsSegmentFormat.MP3;
    L11:
        r112.append(r92);     // Catch: Throwable -> L21
        r10.mime_type = r112.toString();     // Catch: Throwable -> L21
        r82.media.document.size = (int) r7.length();     // Catch: Throwable -> L21
        r82.media.document.dc_id = 0;     // Catch: Throwable -> L21
        TLRPC.TL_documentAttributeAudio r93 = new TLRPC.TL_documentAttributeAudio();     // Catch: Throwable -> L21
        r93.duration = r3.duration;     // Catch: Throwable -> L21
        r93.title = r3.title;     // Catch: Throwable -> L21
        r93.performer = r3.author;     // Catch: Throwable -> L21
        r93.flags = 3 | r93.flags;     // Catch: Throwable -> L21
        r82.media.document.attributes.add(r93);     // Catch: Throwable -> L21
        TLRPC.TL_documentAttributeFilename r6 = new TLRPC.TL_documentAttributeFilename();     // Catch: Throwable -> L21
        r6.file_name = r7.getName();     // Catch: Throwable -> L21
        r82.media.document.attributes.add(r6);     // Catch: Throwable -> L21
        r3.messageObject = new MessageObject(this.parentAlert.currentAccount, r82, false, true);     // Catch: Throwable -> L21
        AudioInfo r4 = AudioInfo.getAudioInfo(r7);     // Catch: Throwable -> L21
        if (r4 != null) goto L14;
    L24:
        r1.add(r3);     // Catch: Throwable -> L21
        r0 = r0 - 1;
        goto L42
    L14:
        if (r4.getCover() == null) goto L24;
        int r62 = AndroidUtilities.dp(44.0f);     // Catch: Throwable -> L21
        Bitmap r42 = r4.getCover();     // Catch: Throwable -> L21
        if (r42.getWidth() <= r62) goto L18;
    L23:
        float r63 = r62;
        float r64 = Math.min(r63 / r42.getWidth(), r63 / r42.getHeight());     // Catch: Throwable -> L21
        r3.messageObject.audioCover = Bitmap.createScaledBitmap(r42, (int) (r42.getWidth() * r64), (int) (r42.getHeight() * r64), true);     // Catch: Throwable -> L21
        goto L24
    L18:
        if (r42.getHeight() > r62) goto L23;
        r3.messageObject.audioCover = r42;     // Catch: Throwable -> L21
        goto L24
    L26:
        r2.close();     // Catch: Exception -> L28
    L37:
        AndroidUtilities.runOnUIThread(new ChatAttachAlertAudioLayout$$ExternalSyntheticLambda13(this, r1));
        return;
    L21:
        th = move-exception;
        if (r2 == null) goto L45;
        r2.close();     // Catch: Throwable -> L33
    L46:
        throw th;     // Catch: Exception -> L28
    L33:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L28
        throw th;     // Catch: Exception -> L28
    L45:
        throw th;     // Catch: Exception -> L28
    L28:
        e = move-exception;
        FileLog.e(e);
        goto L37
    }

    private /* synthetic */ void lambda$new$0(ChatAttachAlert r3) {
        r3.blur3_InvalidateBlur();
        checkUi_listViewPadding();
        this.parentAlert.updateLayout(this, true, 0);
    }

    private /* synthetic */ void lambda$new$1() {
        int r1 = -1;
        boolean r0 = this.listView.canScrollVertically(-1);
        int r3 = -1;
        int r4 = 0;
    L4:
        if (r4 >= this.listView.getChildCount()) goto L9;
        View r12 = this.listView.getChildAt(r4);
        int r32 = this.listView.getChildAdapterPosition(r12);
        int r13 = r12.getTop();
        if (r32 >= 0) goto L7;
        r4 = r4 + 1;
        r3 = r13;
        r1 = r32;
        goto L4
    L7:
        r3 = r13;
        r1 = r32;
    L9:
        this.listView.adapter.update(true);
        if (r0 == true) goto L13;
        this.listView.layoutManager.scrollToPositionWithOffset(0, 0);
        return;
    L13:
        if (r1 < 0) goto L18;
        UniversalRecyclerView r7 = this.listView;
        r7.layoutManager.scrollToPositionWithOffset(r1, r3 - r7.getPaddingTop());
        return;
    }

    private /* synthetic */ void lambda$searchChats$5(MessagesController r7, int r8, TLRPC.messages_Messages r9, TLRPC.TL_error r10) {
        this.searchChatsRequestId = -1;
        boolean r102 = false;
        this.loadingSearchChats = false;
        if (r9 == null) goto L39;
        r7.putUsers(r9.users, false);
        r7.putChats(r9.chats, false);
        ArrayList<TLRPC.Message> r72 = r9.messages;
        int r0 = r72.size();
        int r1 = 0;
    L6:
        if (r1 >= r0) goto L21;
        TLRPC.Message r3 = r72.get(r1);
        r1 = r1 + 1;
        MediaController.AudioEntry r4 = new MediaController.AudioEntry();
        MessageObject r5 = new MessageObject(r8, r3, false, true);
        r4.messageObject = r5;
        TLRPC.Document r2 = r5.getDocument();
        if (r2 == null) goto L6;
        int r32 = 0;
    L12:
        if (r32 >= r2.attributes.size()) goto L17;
        if ((r2.attributes.get(r32) instanceof TLRPC.TL_documentAttributeAudio) == true) goto L15;
        r32 = r32 + 1;
        goto L12
    L15:
        TLRPC.TL_documentAttributeAudio r22 = (TLRPC.TL_documentAttributeAudio) r2.attributes.get(r32);
    L18:
        if (r22 == null) goto L6;
        r4.author = r22.performer;
        r4.title = r22.title;
        r4.duration = (int) r22.duration;
        this.foundInChats.add(r4);
        goto L6
    L17:
        r22 = null;
        goto L18
    L21:
        int r73 = r9.next_rate;
        this.searchChatsNextRate = r73;
        if (r73 == 0) goto L24;
    L27:
        r102 = true;
    L28:
        this.searchChatsHasMore = r102;
        updateWithSavingScroll();
        return;
    L24:
        if (r9.count <= 0) goto L28;
        if (this.foundInChats.size() >= r9.count) goto L28;
    }

    private /* synthetic */ void lambda$searchGlobal$6(MessagesController r2, Long r3) {
        boolean r0 = false;
        this.resolvingGlobalAudioBot = false;
        if (r3 != null) goto L5;
        TLRPC.User r22 = null;
    L6:
        this.globalAudioBot = r22;
        if (r22 != null) goto L9;
        r0 = true;
    L9:
        this.failedToResolveGlobalAudioBot = r0;
        if (r22 == null) goto L13;
        searchGlobal();
        return;
    L13:
        return;
    L5:
        r22 = r2.getUser(r3);
        goto L6
    }

    private /* synthetic */ void lambda$searchGlobal$7(MessagesController r10, int r11, TLRPC.messages_BotResults r12, TLRPC.TL_error r13) {
        this.searchGlobalRequestId = -1;
        this.loadingSearchGlobal = false;
        if (r12 == null) goto L40;
        r10.putUsers(r12.users, false);
        ArrayList<TLRPC.BotInlineResult> r102 = r12.results;
        int r0 = r102.size();
        int r1 = 0;
    L6:
        if (r1 >= r0) goto L25;
        TLRPC.BotInlineResult r3 = r102.get(r1);
        r1 = r1 + 1;
        TLRPC.BotInlineResult r32 = r3;
        if ((r32 instanceof TLRPC.TL_botInlineMediaResult) == false) goto L6;
        TLRPC.TL_botInlineMediaResult r33 = (TLRPC.TL_botInlineMediaResult) r32;
        if (r33.document == null) goto L6;
        TLRPC.TL_message r4 = new TLRPC.TL_message();
        r4.out = true;
        int r5 = this.globalAudioMessageId;
        this.globalAudioMessageId = r5 - 1;
        r4.id = r5;
        r4.peer_id = new TLRPC.TL_peerUser();
        TLRPC.TL_peerUser r52 = new TLRPC.TL_peerUser();
        r4.from_id = r52;
        TLRPC.Peer r6 = r4.peer_id;
        long r7 = UserConfig.getInstance(r11).getClientUserId();
        r52.user_id = r7;
        r6.user_id = r7;
        r4.date = (int) (System.currentTimeMillis() / 1000);
        r4.message = _UrlKt.FRAGMENT_ENCODE_SET;
        TLRPC.TL_messageMediaDocument r53 = new TLRPC.TL_messageMediaDocument();
        r4.media = r53;
        r53.flags |= 3;
        r53.document = r33.document;
        r4.flags |= 768;
        MediaController.AudioEntry r34 = new MediaController.AudioEntry();
        MessageObject r54 = new MessageObject(r11, r4, false, true);
        r34.messageObject = r54;
        TLRPC.Document r2 = r54.getDocument();
        if (r2 == null) goto L6;
        int r42 = 0;
    L16:
        if (r42 >= r2.attributes.size()) goto L21;
        if ((r2.attributes.get(r42) instanceof TLRPC.TL_documentAttributeAudio) == true) goto L19;
        r42 = r42 + 1;
        goto L16
    L19:
        TLRPC.TL_documentAttributeAudio r22 = (TLRPC.TL_documentAttributeAudio) r2.attributes.get(r42);
    L22:
        if (r22 == null) goto L6;
        r34.author = r22.performer;
        r34.title = r22.title;
        r34.duration = (int) r22.duration;
        this.foundGlobal.add(r34);
        goto L6
    L21:
        r22 = null;
        goto L22
    L25:
        this.globalAudioOffset = r12.next_offset;
        this.searchGlobalHasMore = !TextUtils.isEmpty(r10);
        updateWithSavingScroll();
        return;
    }

    private /* synthetic */ void lambda$sendSelectedItems$2(ArrayList r12, boolean r13, int r14, int r15, long r16, boolean r18, Long r19) {
        this.delegate.didSelectAudio(r12, this.parentAlert.getCommentView().getText(), r13, r14, r15, r16, r18, r19.longValue());
        this.parentAlert.dismiss(true);
    }

    private void loadAudio() {
        this.loadingAudio = true;
        Utilities.globalQueue.postRunnable(new ChatAttachAlertAudioLayout$$ExternalSyntheticLambda9(this));
    }

    private boolean needPlayMessage(MessageObject r4) {
        this.playingAudio = r4;
        ArrayList<MessageObject> r3 = new ArrayList();
        r3.add(r4);
        return MediaController.getInstance().setPlaylist(r3, r4, 0);
    }

    private void onItemClick(UItem r19, View r20, int r21, float r22, float r23) {
        if (r19 != null) goto L5;
    L8:
        if (r19 != null) goto L10;
    L13:
        if (r19 == null) goto L19;
        if (r19.id != this.LOAD_MORE_SEARCH_GLOBAL) goto L19;
        searchGlobal();
        return;
    L19:
        if ((r20 instanceof SharedAudioCell) == true) goto L21;
        return;
    L21:
        SharedAudioCell r2 = (SharedAudioCell) r20;
        MediaController.AudioEntry r3 = (MediaController.AudioEntry) r2.getTag();
        ChatAttachAlert r4 = this.parentAlert;
        int r6 = 1;
        if (r4.isStoryAudioPicker == false) goto L24;
    L37:
        this.sendPressed = true;
        ArrayList<MessageObject> r8 = new ArrayList();
        r8.add(r3.messageObject);
        this.delegate.didSelectAudio(r8, this.parentAlert.getCommentView().getText(), false, 0, 0, 0, false, 0);
    L36:
        boolean r5 = true;
    L38:
        ChatAttachAlert r0 = this.parentAlert;
        if (r5 == true) goto L42;
        r6 = 2;
    L42:
        r0.updateCountButton(r6);
        return;
    L24:
        if (r4.isPollAttach == true) goto L37;
        r5 = false;
        if (this.selectedAudios.contains(r3) == false) goto L30;
        this.selectedAudios.remove(r3);
        r19.checked = false;
        r2.setChecked(false, true);
        goto L38
    L30:
        if (this.maxSelectedFiles < 0) goto L35;
        int r42 = this.selectedAudios.size();
        int r7 = this.maxSelectedFiles;
        if (r42 < r7) goto L35;
        showErrorBox(LocaleController.formatString(R.string.PassportUploadMaxReached, new Object[]{LocaleController.formatPluralString("Files", r7, new Object[0])}));
        return;
    L35:
        r19.checked = true;
        this.selectedAudios.add(r3);
        r2.setChecked(true, true);
        goto L36
    L10:
        if (r19.id != this.LOAD_MORE_SEARCH_CHATS) goto L13;
        searchChats();
        return;
    L5:
        if (r19.id != this.LOAD_MORE_SEARCH_PROFILE) goto L8;
        this.savedMusicList.load();
    }

    private boolean onItemLongClick(UItem r1, View r2, int r3, float r4, float r5) {
        onItemClick(r1, r2, r3, r4, r5);
        return true;
    }

    private void searchChats() {
        AndroidUtilities.cancelRunOnUIThread(this.searchChatsRunnable);
        String r0 = this.query;
        int r1 = 3;
        if (r0 == null) goto L13;
        if (r0.length() <= 0) goto L13;
        if (this.query.length() >= 3) goto L13;
        if (this.loadingSearchChats == false) goto L39;
        this.loadingSearchChats = false;
        updateWithSavingScroll();
        return;
    L39:
        return;
    L13:
        if (TextUtils.equals(this.lastSearchChatsQuery, this.query) == true) goto L16;
        this.foundInChats.clear();
        this.searchChatsNextRate = 0;
        this.searchChatsHasMore = false;
    L16:
        if (this.foundInChats.isEmpty() == false) goto L18;
    L23:
        int r02 = this.parentAlert.currentAccount;
        MessagesController r3 = MessagesController.getInstance(r02);
        ConnectionsManager r4 = ConnectionsManager.getInstance(r02);
        int r5 = this.searchChatsRequestId;
        if (r5 < 0) goto L26;
        r4.cancelRequest(r5, true);
        this.searchChatsRequestId = -1;
    L26:
        TLRPC.TL_messages_searchGlobal r52 = new TLRPC.TL_messages_searchGlobal();
        r52.filter = new TLRPC.TL_inputMessagesFilterMusic();
        String r7 = this.query;
        this.lastSearchChatsQuery = r7;
        if (r7 != null) goto L29;
        r7 = _UrlKt.FRAGMENT_ENCODE_SET;
    L29:
        r52.q = r7;
        if (this.foundInChats.isEmpty() == true) goto L33;
        r1 = 15;
    L33:
        r52.limit = r1;
        if (this.foundInChats.size() <= 0) goto L36;
        ArrayList<MediaController.AudioEntry> r12 = this.foundInChats;
        MessageObject r13 = r12.get(r12.size() - 1).messageObject;
        r52.offset_id = r13.getId();
        r52.offset_rate = this.searchChatsNextRate;
        r52.offset_peer = r3.getInputPeer(MessageObject.getPeerId(r13.messageOwner.peer_id));
    L37:
        this.searchChatsRequestId = r4.sendRequestTyped(r52, new AiTonesController$$ExternalSyntheticLambda0(), new ChatAttachAlertAudioLayout$$ExternalSyntheticLambda7(this, r3, r02));
        updateWithSavingScroll();
        return;
    L36:
        r52.offset_rate = 0;
        r52.offset_id = 0;
        r52.offset_peer = new TLRPC.TL_inputPeerEmpty();
        goto L37
    L18:
        if (this.searchChatsHasMore == true) goto L23;
        if (this.loadingSearchChats == false) goto L40;
        this.loadingSearchChats = false;
        updateWithSavingScroll();
        return;
    }

    private void searchGlobal() {
        AndroidUtilities.cancelRunOnUIThread(this.searchGlobalRunnable);
        if (TextUtils.isEmpty(this.query) == true) goto L40;
        if (this.query.length() < 3) goto L40;
        if (TextUtils.equals(this.lastSearchGlobalQuery, this.query) == true) goto L10;
        this.foundGlobal.clear();
        this.searchGlobalHasMore = false;
    L10:
        int r0 = this.parentAlert.currentAccount;
        MessagesController r1 = MessagesController.getInstance(r0);
        ConnectionsManager r2 = ConnectionsManager.getInstance(r0);
        int r3 = this.searchGlobalRequestId;
        if (r3 < 0) goto L13;
        r2.cancelRequest(r3, true);
        this.searchGlobalRequestId = -1;
    L13:
        String r32 = r1.config.musicSearchUsername.get();
        if (TextUtils.isEmpty(r32) == false) goto L17;
        return;
    L17:
        if (this.globalAudioBot != null) goto L20;
        this.globalAudioBot = r1.getUser(r32);
    L20:
        if (this.globalAudioBot == null) goto L22;
        TLRPC.User r33 = UserConfig.getInstance(r0).getCurrentUser();
        TLRPC.TL_messages_getInlineBotResults r4 = new TLRPC.TL_messages_getInlineBotResults();
        r4.bot = r1.getInputUser(this.globalAudioBot);
        r4.peer = MessagesController.getInputPeer(r33);
        boolean r34 = this.foundGlobal.isEmpty();
        String r5 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r34 == true) goto L32;
        String r35 = this.globalAudioOffset;
        if (r35 == null) goto L32;
    L33:
        r4.offset = r35;
        String r36 = this.query;
        if (r36 == null) goto L37;
        r5 = r36;
    L37:
        this.lastSearchGlobalQuery = r5;
        r4.query = r5;
        this.searchGlobalRequestId = r2.sendRequestTyped(r4, new AiTonesController$$ExternalSyntheticLambda0(), new ChatAttachAlertAudioLayout$$ExternalSyntheticLambda12(this, r1, r0));
        updateWithSavingScroll();
        return;
    L32:
        r35 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L33
    L22:
        if (this.resolvingGlobalAudioBot == false) goto L24;
        return;
    L24:
        if (this.failedToResolveGlobalAudioBot == true) goto L46;
        this.resolvingGlobalAudioBot = true;
        r1.getUserNameResolver().resolve(r32, new ChatAttachAlertAudioLayout$$ExternalSyntheticLambda11(this, r1));
        return;
    L46:
        return;
    L40:
        if (this.loadingSearchGlobal == false) goto L44;
        this.loadingSearchGlobal = false;
        updateWithSavingScroll();
        return;
    }

    private void showErrorBox(String r3) {
        new AlertDialog.Builder(getContext(), this.resourcesProvider).setTitle(LocaleController.getString(R.string.AppName)).setMessage(r3).setPositiveButton(LocaleController.getString(R.string.OK), null).show();
    }

    private void updateWithSavingScroll() {
        AndroidUtilities.cancelRunOnUIThread(this.updateWithSavingScrollRunnable);
        AndroidUtilities.runOnUIThread(this.updateWithSavingScrollRunnable);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r5, int r6, Object... r7) {
        int r62 = NotificationCenter.messagePlayingDidReset;
        if (r5 != r62) goto L5;
    L17:
        if (r5 != r62) goto L19;
    L34:
        int r52 = this.listView.getChildCount();
        int r63 = 0;
    L35:
        if (r63 >= r52) goto L56;
        View r72 = this.listView.getChildAt(r63);
        if ((r72 instanceof SharedAudioCell) == false) goto L41;
        SharedAudioCell r73 = (SharedAudioCell) r72;
        if (r73.getMessage() == null) goto L41;
        r73.updateButtonState(false, true);
    L41:
        r63 = r63 + 1;
        goto L35
    L56:
        return;
    L19:
        if (r5 == NotificationCenter.messagePlayingPlayStateChanged) goto L34;
        if (r5 == NotificationCenter.messagePlayingDidStart) goto L24;
        return;
    L24:
        if (((MessageObject) r7[0]).eventId != 0) goto L54;
        int r53 = this.listView.getChildCount();
        int r64 = 0;
    L27:
        if (r64 >= r53) goto L55;
        View r74 = this.listView.getChildAt(r64);
        if ((r74 instanceof SharedAudioCell) == false) goto L33;
        SharedAudioCell r75 = (SharedAudioCell) r74;
        if (r75.getMessage() == null) goto L33;
        r75.updateButtonState(false, true);
    L33:
        r64 = r64 + 1;
        goto L27
    L55:
        return;
    L54:
        return;
    L5:
        if (r5 == NotificationCenter.messagePlayingDidStart) goto L17;
        if (r5 == NotificationCenter.messagePlayingPlayStateChanged) goto L17;
        if (r5 == NotificationCenter.musicListLoaded) goto L12;
        return;
    L12:
        if (r7[0] != this.savedMusicList) goto L51;
        UniversalRecyclerView r4 = this.listView;
        if (r4 == null) goto L52;
        r4.adapter.update(true);
        return;
    L52:
        return;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getCurrentItemTop() {
        if (this.listView.getChildCount() > 0) goto L5;
        return Integer.MAX_VALUE;
    L5:
        int r2 = 0;
        boolean r4 = false;
        int r3 = Integer.MAX_VALUE;
    L7:
        if (r2 >= this.listView.getChildCount()) goto L16;
        View r5 = this.listView.getChildAt(r2);
        int r7 = this.listView.getChildAdapterPosition(r5);
        if (r7 != 0) goto L11;
        r4 = true;
    L11:
        if (r7 < 0) goto L15;
        if (r5.getTop() >= r3) goto L15;
        r3 = r5.getTop();
    L15:
        r2 = r2 + 1;
        goto L7
    L16:
        if (r3 != Integer.MAX_VALUE) goto L18;
        return Integer.MAX_VALUE;
    L18:
        int r32 = (((r3 - AndroidUtilities.dp(56.0f)) - ((int) this.topPanelLayout.getAnimatedHeightWithPadding(0.0f))) - AndroidUtilities.statusBarHeight) - AndroidUtilities.dp(8.0f);
        if (r32 <= 0) goto L22;
        if (r4 == false) goto L22;
        int r1 = r32;
    L23:
        if (r32 < 0) goto L26;
        if (r4 == false) goto L26;
        this.animatorFadeVisible.setValue(false, true);
    L27:
        this.frameLayout.setTranslationY(r32);
        return r32 + AndroidUtilities.dp(12.0f);
    L26:
        this.animatorFadeVisible.setValue(true, true);
        r32 = r1;
    L22:
        r1 = 0;
        goto L23
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getFirstOffset() {
        return getListTopPadding() + AndroidUtilities.dp(4.0f);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getListTopPadding() {
        return (this.listView.getPaddingTop() - AndroidUtilities.dp(56.0f)) - ((int) this.topPanelLayout.getAnimatedHeightWithPadding(0.0f));
    }

    public ArrayList<MessageObject> getSelected() {
        ArrayList<MessageObject> r0 = new ArrayList();
        Iterator<MediaController.AudioEntry> r2 = this.selectedAudios.iterator();
    L4:
        if (r2.hasNext() == false) goto L6;
        r0.add(r2.next().messageObject);
        goto L4
    L6:
        return r0;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getSelectedItemsCount() {
        return this.selectedAudios.size();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r0 = new ArrayList();
        r0.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_dialogScrollGlow));
        r0.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r0.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r0.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKBOX, new Class[]{SharedAudioCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_checkbox));
        r0.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKBOXCHECK, new Class[]{SharedAudioCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_checkboxCheck));
        r0.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{SharedAudioCell.class}, Theme.chat_contextResult_titleTextPaint, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r0.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{SharedAudioCell.class}, Theme.chat_contextResult_descriptionTextPaint, null, null, Theme.key_windowBackgroundWhiteGrayText2));
        return r0;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onContainerTranslationUpdated(float r1) {
        this.currentPanTranslationProgress = r1;
        super.onContainerTranslationUpdated(r1);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onDestroy() {
        onHide();
        NotificationCenter.getInstance(this.parentAlert.currentAccount).removeObserver(this, NotificationCenter.messagePlayingDidReset);
        NotificationCenter.getInstance(this.parentAlert.currentAccount).removeObserver(this, NotificationCenter.messagePlayingDidStart);
        NotificationCenter.getInstance(this.parentAlert.currentAccount).removeObserver(this, NotificationCenter.messagePlayingPlayStateChanged);
        NotificationCenter.getInstance(this.parentAlert.currentAccount).removeObserver(this, NotificationCenter.musicListLoaded);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean onDismiss() {
        if (this.playingAudio == null) goto L8;
        if (MediaController.getInstance().isPlayingMessage(this.playingAudio) == false) goto L8;
        MediaController.getInstance().cleanupPlayer(true, true);
    L8:
        return super.onDismiss();
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

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onHidden() {
        this.selectedAudios.clear();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onHide() {
        if (this.playingAudio != null) goto L5;
    L7:
        this.playingAudio = null;
        return;
    L5:
        if (MediaController.getInstance().isPlayingMessage(this.playingAudio) == false) goto L7;
        MediaController.getInstance().cleanupPlayer(true, true);
        goto L7
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onPreMeasure(int r1, int r2) {
        this.preMeasuredAvailableHeight = r2;
        checkUi_listViewPadding();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onShow(ChatAttachAlert.AttachAlertLayout r2) {
        searchChats();
        this.savedMusicList.load();
        this.listView.layoutManager.scrollToPositionWithOffset(0, 0);
        this.listView.adapter.update(false);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void scrollToTop() {
        this.listView.smoothScrollToPosition(0);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean sendSelectedItems(boolean r14, int r15, int r16, long r17, boolean r19) {
        if (this.selectedAudios.size() != 0) goto L5;
        return false;
    L5:
        if (this.delegate != null) goto L7;
        return false;
    L7:
        if (this.sendPressed == true) goto L19;
        this.sendPressed = true;
        ArrayList r3 = new ArrayList();
        Iterator<MediaController.AudioEntry> r0 = this.selectedAudios.iterator();
    L11:
        if (r0.hasNext() == false) goto L13;
        r3.add(r0.next().messageObject);
        goto L11
    L13:
        ChatAttachAlert r02 = this.parentAlert;
        return AlertsCreator.ensurePaidMessageConfirmation(r02.currentAccount, r02.getDialogId(), r3.size() + this.parentAlert.getAdditionalMessagesCount(), new ChatAttachAlertAudioLayout$$ExternalSyntheticLambda8(this, r3, r14, r15, r16, r17, r19));
    L19:
        return false;
    }

    public void setDelegate(AudioSelectDelegate r1) {
        this.delegate = r1;
    }

    public void setMaxSelectedFiles(int r1) {
        this.maxSelectedFiles = r1;
    }

    @Override // android.view.View
    public void setTranslationY(float r1) {
        super.setTranslationY(r1);
        this.parentAlert.getSheetContainer().invalidate();
    }

    public void setupBlurredSearchField(BlurredBackgroundDrawableViewFactory r3) {
        FragmentSearchField r0 = this.searchField;
        if (r0 == null) goto L5;
        r0.setupBlurredBackground(r3.create(r0, BlurredBackgroundProviderImpl.topPanel(this.resourcesProvider)));
    L5:
        View r02 = this.topPanelLayout;
        if (r02 == null) goto L9;
        BlurredBackgroundDrawable r32 = r3.create(r02, BlurredBackgroundProviderImpl.topPanel(this.resourcesProvider));
        r32.setRadius(AndroidUtilities.dp(24.0f));
        r32.setPadding(AndroidUtilities.dp(7.0f));
        this.topPanelLayout.setBlurredBackground(r32);
        return;
    }
}
