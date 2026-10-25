package org.telegram.ui.Adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.support.LongSparseIntArray;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_chatlists;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.DialogCell;
import org.telegram.ui.Cells.DialogMeUrlCell;
import org.telegram.ui.Cells.DialogsEmptyCell;
import org.telegram.ui.Cells.DialogsHintCell;
import org.telegram.ui.Cells.DialogsRequestedEmptyCell;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.ProfileSearchCell;
import org.telegram.ui.Cells.RequestPeerRequirementsCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.UserCell;
import org.telegram.ui.Components.ArchiveHelp;
import org.telegram.ui.Components.BlurredRecyclerView;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.ListView.AdapterWithDiffUtils;
import org.telegram.ui.Components.PullForegroundDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.Stories.StoriesController;
import org.telegram.ui.Stories.StoriesListPlaceProvider;

/* loaded from: classes3.dex */
public class DialogsAdapter extends RecyclerListView.SelectionAdapter implements DialogCell.DialogCellDelegate {
    private static final boolean ALLOW_UPDATE_IN_BACKGROUND = false;
    private boolean allowForwardAsStories;
    private Drawable arrowDrawable;
    private boolean collapsedView;
    public final long communityId;
    private int currentAccount;
    private int currentCount;
    private int dialogsCount;
    HashMap<String, Integer> dialogsHeaderStableIds;
    private boolean dialogsListFrozen;
    LongSparseIntArray dialogsStableIds;
    private int dialogsType;
    private boolean firstUpdate;
    private int folderId;
    private boolean forceShowEmptyCell;
    private boolean forceUpdatingContacts;
    private boolean hasChatlistHint;
    private boolean hasHints;
    boolean isCalculatingDiff;
    public boolean isEmpty;
    private boolean isOnlySelect;
    private boolean isReordering;
    private boolean isTransitionSupport;
    ArrayList<ItemInternal> itemInternals;
    public int lastDialogsEmptyType;
    private long lastSortTime;
    private Context mContext;
    ArrayList<ItemInternal> oldItems;
    private ArrayList<TLRPC.TL_contact> onlineContacts;
    private long openedDialogId;
    private DialogsActivity parentFragment;
    private DialogsPreloader preloader;
    private PullForegroundDrawable pullForegroundDrawable;
    RecyclerListView recyclerListView;
    private TLRPC.RequestPeerType requestPeerType;
    private ArrayList<Long> selectedDialogs;
    int stableIdPointer;
    boolean updateListPending;

    /* renamed from: org.telegram.ui.Adapters.DialogsAdapter$1, reason: invalid class name */
    public class AnonymousClass1 extends DiffUtil.Callback {
        final /* synthetic */ DialogsAdapter this$0;
        final /* synthetic */ ArrayList val$newItems;

        public AnonymousClass1(DialogsAdapter r1, ArrayList r2) {
            this.this$0 = r1;
            this.val$newItems = r2;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areContentsTheSame(int r2, int r3) {
            if (this.this$0.oldItems.get(r2).viewType != ((ItemInternal) this.val$newItems.get(r3)).viewType) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areItemsTheSame(int r2, int r3) {
            return this.this$0.oldItems.get(r2).compare((ItemInternal) this.val$newItems.get(r3));
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getNewListSize() {
            return this.val$newItems.size();
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getOldListSize() {
            return this.this$0.oldItems.size();
        }
    }

    /* renamed from: org.telegram.ui.Adapters.DialogsAdapter$2, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass2 extends FrameLayout {
        final /* synthetic */ DialogsAdapter this$0;

        public AnonymousClass2(DialogsAdapter r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(12.0f), TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.Adapters.DialogsAdapter$3, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass3 extends DialogsRequestedEmptyCell {
        final /* synthetic */ DialogsAdapter this$0;

        public AnonymousClass3(DialogsAdapter r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Cells.DialogsRequestedEmptyCell
        public void onButtonClick() {
            this.this$0.onCreateGroupForThisClick();
        }
    }

    /* renamed from: org.telegram.ui.Adapters.DialogsAdapter$4, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass4 extends TextInfoPrivacyCell {
        private long lastUpdateTime;
        private float moveProgress;
        private int movement;
        private int originalX;
        private int originalY;
        final /* synthetic */ DialogsAdapter this$0;

        public AnonymousClass4(DialogsAdapter r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Cells.TextInfoPrivacyCell
        public void afterTextDraw() {
            if (DialogsAdapter.m5859$$Nest$fgetarrowDrawable(this.this$0) == null) goto L6;
            Rect r0 = DialogsAdapter.m5859$$Nest$fgetarrowDrawable(this.this$0).getBounds();
            Drawable r1 = DialogsAdapter.m5859$$Nest$fgetarrowDrawable(this.this$0);
            int r2 = this.originalX;
            r1.setBounds(r2, this.originalY, r0.width() + r2, this.originalY + r0.height());
            return;
        }

        @Override // org.telegram.ui.Cells.TextInfoPrivacyCell
        public void onTextDraw() {
            if (DialogsAdapter.m5859$$Nest$fgetarrowDrawable(this.this$0) == null) goto L17;
            Rect r0 = DialogsAdapter.m5859$$Nest$fgetarrowDrawable(this.this$0).getBounds();
            int r1 = (int) (this.moveProgress * AndroidUtilities.dp(3.0f));
            this.originalX = r0.left;
            this.originalY = r0.top;
            DialogsAdapter.m5859$$Nest$fgetarrowDrawable(this.this$0).setBounds(this.originalX + r1, this.originalY + AndroidUtilities.dp(1.0f), (this.originalX + r1) + r0.width(), (this.originalY + AndroidUtilities.dp(1.0f)) + r0.height());
            long r02 = SystemClock.elapsedRealtime();
            long r2 = r02 - this.lastUpdateTime;
            if (r2 <= 17) goto L7;
            r2 = 17;
        L7:
            this.lastUpdateTime = r02;
            int r03 = this.movement;
            float r12 = this.moveProgress;
            if (r03 != 0) goto L12;
            float r13 = r12 + (r2 / 664.0f);
            this.moveProgress = r13;
            if (r13 < 1.0f) goto L15;
            this.movement = 1;
            this.moveProgress = 1.0f;
        L15:
            getTextView().invalidate();
            return;
        L12:
            float r14 = r12 - (r2 / 664.0f);
            this.moveProgress = r14;
            if (r14 > 0.0f) goto L15;
            this.movement = 0;
            this.moveProgress = 0.0f;
            goto L15
        }
    }

    /* renamed from: org.telegram.ui.Adapters.DialogsAdapter$5, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass5 extends View {
        final /* synthetic */ DialogsAdapter this$0;

        public AnonymousClass5(DialogsAdapter r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(81.0f), TLObject.FLAG_30));
        }
    }

    public static class DialogsPreloader {
        private final int MAX_NETWORK_REQUEST_COUNT;
        private final int MAX_REQUEST_COUNT;
        private final int NETWORK_REQUESTS_RESET_TIME;
        Runnable clearNetworkRequestCount;
        int currentRequestCount;
        HashSet<Long> dialogsReadyMap;
        HashSet<Long> loadingDialogs;
        int networkRequestCount;
        ArrayList<Long> preloadDialogsPool;
        HashSet<Long> preloadedErrorMap;
        boolean resumed;

        /* renamed from: org.telegram.ui.Adapters.DialogsAdapter$DialogsPreloader$1, reason: invalid class name */
        /* loaded from: classes6.dex */
        public class AnonymousClass1 implements MessagesController.MessagesLoadedCallback {
            final /* synthetic */ DialogsPreloader this$0;
            final /* synthetic */ long val$dialog_id;

            public static /* synthetic */ void $r8$lambda$T1K2Enj5gBeIg9IrLdt75j8JZgk(AnonymousClass1 r0, long r1) {
                r0.lambda$onError$1(r1);
            }

            public static /* synthetic */ void $r8$lambda$tAb66vKpGbZcsZry2gdxh69NjcQ(AnonymousClass1 r0, boolean r1, long r2) {
                r0.lambda$onMessagesLoaded$0(r1, r2);
            }

            public AnonymousClass1(DialogsPreloader r1, long r2) {
                this.this$0 = r1;
                this.val$dialog_id = r2;
            }

            private /* synthetic */ void lambda$onError$1(long r3) {
                if (this.this$0.loadingDialogs.remove(Long.valueOf(r3)) == false) goto L6;
                this.this$0.preloadedErrorMap.add(Long.valueOf(r3));
                r2.currentRequestCount--;
                DialogsPreloader.m5867$$Nest$mstart(this.this$0);
                return;
            }

            private /* synthetic */ void lambda$onMessagesLoaded$0(boolean r3, long r4) {
                if (r3 == true) goto L7;
                DialogsPreloader r32 = this.this$0;
                int r0 = r32.networkRequestCount + 1;
                r32.networkRequestCount = r0;
                if (r0 < 6) goto L7;
                AndroidUtilities.cancelRunOnUIThread(r32.clearNetworkRequestCount);
                AndroidUtilities.runOnUIThread(this.this$0.clearNetworkRequestCount, 60000);
            L7:
                if (this.this$0.loadingDialogs.remove(Long.valueOf(r4)) == false) goto L10;
                this.this$0.dialogsReadyMap.add(Long.valueOf(r4));
                this.this$0.updateList();
                r2.currentRequestCount--;
                DialogsPreloader.m5867$$Nest$mstart(this.this$0);
                return;
            }

            @Override // org.telegram.messenger.MessagesController.MessagesLoadedCallback
            public void onError() {
                AndroidUtilities.runOnUIThread(new DialogsAdapter$DialogsPreloader$1$$ExternalSyntheticLambda0(this, this.val$dialog_id));
            }

            @Override // org.telegram.messenger.MessagesController.MessagesLoadedCallback
            public void onMessagesLoaded(boolean r4) {
                AndroidUtilities.runOnUIThread(new DialogsAdapter$DialogsPreloader$1$$ExternalSyntheticLambda1(this, r4, this.val$dialog_id));
            }
        }

        public static /* synthetic */ void $r8$lambda$yJNgDXFZ51z8jDqR1RPe7SsuI40(DialogsPreloader r0) {
            r0.lambda$new$0();
        }

        /* renamed from: -$$Nest$mstart, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m5867$$Nest$mstart(DialogsPreloader r0) {
            r0.start();
        }

        public DialogsPreloader() {
            this.MAX_REQUEST_COUNT = 4;
            this.MAX_NETWORK_REQUEST_COUNT = 6;
            this.NETWORK_REQUESTS_RESET_TIME = 60000;
            this.dialogsReadyMap = new HashSet();
            this.preloadedErrorMap = new HashSet();
            this.loadingDialogs = new HashSet();
            this.preloadDialogsPool = new ArrayList();
            this.clearNetworkRequestCount = new DialogsAdapter$DialogsPreloader$$ExternalSyntheticLambda0(this);
        }

        private /* synthetic */ void lambda$new$0() {
            this.networkRequestCount = 0;
            start();
        }

        private boolean preloadIsAvilable() {
            return false;
        }

        private void start() {
            if (preloadIsAvilable() == true) goto L5;
            return;
        L5:
            if (this.resumed == true) goto L7;
            return;
        L7:
            if (this.preloadDialogsPool.isEmpty() == false) goto L9;
            return;
        L9:
            if (this.currentRequestCount < 4) goto L11;
            return;
        L11:
            if (this.networkRequestCount > 6) goto L19;
            Long r0 = this.preloadDialogsPool.remove(0);
            long r2 = r0.longValue();
            this.currentRequestCount++;
            this.loadingDialogs.add(r0);
            MessagesController.getInstance(UserConfig.selectedAccount).ensureMessagesLoaded(r2, 0, new AnonymousClass1(this, r2));
            return;
        }

        public void add(long r3) {
            if (isReady(r3) == false) goto L5;
            return;
        L5:
            if (this.preloadedErrorMap.contains(Long.valueOf(r3)) == false) goto L7;
            return;
        L7:
            if (this.loadingDialogs.contains(Long.valueOf(r3)) == false) goto L9;
            return;
        L9:
            if (this.preloadDialogsPool.contains(Long.valueOf(r3)) == true) goto L16;
            this.preloadDialogsPool.add(Long.valueOf(r3));
            start();
            return;
        }

        public void clear() {
            this.dialogsReadyMap.clear();
            this.preloadedErrorMap.clear();
            this.loadingDialogs.clear();
            this.preloadDialogsPool.clear();
            this.currentRequestCount = 0;
            this.networkRequestCount = 0;
            AndroidUtilities.cancelRunOnUIThread(this.clearNetworkRequestCount);
            updateList();
        }

        public boolean isReady(long r1) {
            return this.dialogsReadyMap.contains(Long.valueOf(r1));
        }

        public void pause() {
            this.resumed = false;
        }

        public void remove(long r1) {
            this.preloadDialogsPool.remove(Long.valueOf(r1));
        }

        public void resume() {
            this.resumed = true;
            start();
        }

        public void updateList() {
        }
    }

    public class ItemInternal extends AdapterWithDiffUtils.Item {
        private TLRPC.Chat chat;
        TL_chatlists.TL_chatlists_chatlistUpdates chatlistUpdates;
        TLRPC.TL_contact contact;
        TLRPC.Dialog dialog;
        private long dialogId;
        private int emptyType;
        private boolean isFolder;
        boolean isForumCell;
        private boolean pinned;
        TLRPC.RecentMeUrl recentMeUrl;
        private final int stableId;
        final /* synthetic */ DialogsAdapter this$0;
        private String title;
        private TLRPC.User user;

        /* renamed from: -$$Nest$fgetchat, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.Chat m5868$$Nest$fgetchat(ItemInternal r0) {
            return r0.chat;
        }

        /* renamed from: -$$Nest$fgetstableId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m5869$$Nest$fgetstableId(ItemInternal r0) {
            return r0.stableId;
        }

        /* renamed from: -$$Nest$fgettitle, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m5870$$Nest$fgettitle(ItemInternal r0) {
            return r0.title;
        }

        /* renamed from: -$$Nest$fgetuser, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.User m5871$$Nest$fgetuser(ItemInternal r0) {
            return r0.user;
        }

        public ItemInternal(DialogsAdapter r3, int r4) {
            this.this$0 = r3;
            super(r4, true);
            this.emptyType = r4;
            if (r4 != 10) goto L7;
            this.stableId = 1;
            return;
        L7:
            if (this.viewType != 19) goto L10;
            this.stableId = 5;
            return;
        L10:
            int r42 = r3.stableIdPointer;
            r3.stableIdPointer = r42 + 1;
            this.stableId = r42;
        }

        public ItemInternal(DialogsAdapter r2, int r3, int r4) {
            this.this$0 = r2;
            super(r3, true);
            this.emptyType = r4;
            int r32 = r2.stableIdPointer;
            r2.stableIdPointer = r32 + 1;
            this.stableId = r32;
        }

        public ItemInternal(DialogsAdapter r2, int r3, String r4) {
            this.this$0 = r2;
            super(r3, false);
            Integer r32 = r2.dialogsHeaderStableIds.get(r4);
            if (r32 == null) goto L5;
            this.stableId = r32.intValue();
        L6:
            this.title = r4;
            return;
        L5:
            int r33 = r2.stableIdPointer;
            r2.stableIdPointer = r33 + 1;
            this.stableId = r33;
            r2.dialogsHeaderStableIds.put(r4, Integer.valueOf(r33));
            goto L6
        }

        public ItemInternal(DialogsAdapter r5, int r6, TLRPC.Chat r7) {
            this.this$0 = r5;
            super(r6, false);
            this.chat = r7;
            long r0 = r7.id;
            this.dialogId = -r0;
            int r62 = r5.dialogsStableIds.get(-r0, -1);
            if (r62 < 0) goto L6;
            this.stableId = r62;
            return;
        L6:
            int r63 = r5.stableIdPointer;
            r5.stableIdPointer = r63 + 1;
            this.stableId = r63;
            r5.dialogsStableIds.put(-r7.id, r63);
        }

        public ItemInternal(DialogsAdapter r5, int r6, TLRPC.Dialog r7) {
            this.this$0 = r5;
            boolean r0 = true;
            super(r6, true);
            this.dialog = r7;
            if (r7 == null) goto L9;
            int r62 = r5.dialogsStableIds.get(r7.id, -1);
            if (r62 < 0) goto L7;
            this.stableId = r62;
        L12:
            if (r7 != null) goto L14;
            return;
        L14:
            if (DialogsAdapter.m5862$$Nest$fgetdialogsType(r5) != 7) goto L16;
        L19:
            MessagesController.DialogFilter[] r63 = MessagesController.getInstance(DialogsAdapter.m5861$$Nest$fgetcurrentAccount(r5)).selectedDialogFilter;
            if (DialogsAdapter.m5862$$Nest$fgetdialogsType(r5) != 8) goto L22;
            char r1 = 1;
        L23:
            MessagesController.DialogFilter r64 = r63[r1];
            if (r64 != null) goto L26;
        L28:
            r0 = false;
        L29:
            this.pinned = r0;
        L30:
            this.isFolder = r7.isFolder;
            this.isForumCell = MessagesController.getInstance(DialogsAdapter.m5861$$Nest$fgetcurrentAccount(r5)).isForum(r7.id);
            return;
        L26:
            if (r64.pinnedDialogs.indexOfKey(r7.id) < 0) goto L28;
        L22:
            r1 = 0;
            goto L23
        L16:
            if (DialogsAdapter.m5862$$Nest$fgetdialogsType(r5) == 8) goto L19;
            this.pinned = r7.pinned;
            goto L30
        L7:
            int r65 = r5.stableIdPointer;
            r5.stableIdPointer = r65 + 1;
            this.stableId = r65;
            r5.dialogsStableIds.put(r7.id, r65);
            goto L12
        L9:
            if (r6 != 19) goto L11;
            this.stableId = 5;
            goto L12
        L11:
            int r66 = r5.stableIdPointer;
            r5.stableIdPointer = r66 + 1;
            this.stableId = r66;
            goto L12
        }

        public ItemInternal(DialogsAdapter r2, int r3, TLRPC.RecentMeUrl r4) {
            this.this$0 = r2;
            super(r3, true);
            this.recentMeUrl = r4;
            int r32 = r2.stableIdPointer;
            r2.stableIdPointer = r32 + 1;
            this.stableId = r32;
        }

        public ItemInternal(DialogsAdapter r3, int r4, TLRPC.TL_contact r5) {
            this.this$0 = r3;
            super(r4, true);
            this.contact = r5;
            if (r5 == null) goto L10;
            int r42 = r3.dialogsStableIds.get(r5.user_id, -1);
            if (r42 <= 0) goto L8;
            this.stableId = r42;
            return;
        L8:
            int r43 = r3.stableIdPointer;
            r3.stableIdPointer = r43 + 1;
            this.stableId = r43;
            r3.dialogsStableIds.put(this.contact.user_id, r43);
            return;
        L10:
            int r44 = r3.stableIdPointer;
            r3.stableIdPointer = r44 + 1;
            this.stableId = r44;
        }

        public ItemInternal(DialogsAdapter r4, int r5, TLRPC.User r6) {
            this.this$0 = r4;
            super(r5, false);
            this.user = r6;
            long r0 = r6.id;
            this.dialogId = r0;
            int r52 = r4.dialogsStableIds.get(r0, -1);
            if (r52 < 0) goto L6;
            this.stableId = r52;
            return;
        L6:
            int r53 = r4.stableIdPointer;
            r4.stableIdPointer = r53 + 1;
            this.stableId = r53;
            r4.dialogsStableIds.put(r6.id, r53);
        }

        public ItemInternal(DialogsAdapter r3, TL_chatlists.TL_chatlists_chatlistUpdates r4) {
            this.this$0 = r3;
            super(17, true);
            this.chatlistUpdates = r4;
            int r42 = r3.stableIdPointer;
            r3.stableIdPointer = r42 + 1;
            this.stableId = r42;
        }

        public boolean compare(ItemInternal r9) {
            int r0 = this.viewType;
            if (r0 == r9.viewType) goto L6;
            return false;
        L6:
            if (r0 != 0) goto L22;
            TLRPC.Dialog r02 = this.dialog;
            if (r02 == null) goto L20;
            TLRPC.Dialog r3 = r9.dialog;
            if (r3 == null) goto L20;
            if (r02.id != r3.id) goto L20;
            if (this.isFolder != r9.isFolder) goto L20;
            if (this.isForumCell != r9.isForumCell) goto L20;
            if (this.pinned != r9.pinned) goto L20;
            return true;
        L20:
            return false;
        L22:
            if (r0 != 14) goto L34;
            TLRPC.Dialog r8 = this.dialog;
            if (r8 == null) goto L32;
            TLRPC.Dialog r92 = r9.dialog;
            if (r92 == null) goto L32;
            if (r8.id != r92.id) goto L32;
            if (r8.isFolder != r92.isFolder) goto L32;
            return true;
        L32:
            return false;
        L34:
            if (r0 != 4) goto L46;
            TLRPC.RecentMeUrl r82 = this.recentMeUrl;
            if (r82 != null) goto L38;
        L44:
            return false;
        L38:
            if (r9.recentMeUrl == null) goto L44;
            String r83 = r82.url;
            if (r83 == null) goto L44;
            if (r83.equals(r83) == false) goto L44;
            return true;
        L46:
            if (r0 != 6) goto L56;
            TLRPC.TL_contact r84 = this.contact;
            if (r84 == null) goto L54;
            TLRPC.TL_contact r93 = r9.contact;
            if (r93 == null) goto L54;
            if (r84.user_id != r93.user_id) goto L54;
            return true;
        L54:
            return false;
        L56:
            if (r0 != 5) goto L62;
            if (this.emptyType != r9.emptyType) goto L60;
            return true;
        L60:
            return false;
        L62:
            if (r0 != 10) goto L64;
            return false;
        L64:
            return true;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.dialog, this.chat, this.recentMeUrl, this.contact, this.title});
        }
    }

    public class LastEmptyView extends FrameLayout {
        public boolean moving;
        final /* synthetic */ DialogsAdapter this$0;

        public LastEmptyView(DialogsAdapter r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r13, int r14) {
            int r0 = this.this$0.itemInternals.size();
            int r2 = 0;
            if (DialogsAdapter.m5863$$Nest$fgetfolderId(this.this$0) == 0) goto L5;
        L9:
            boolean r1 = false;
        L10:
            View r4 = (View) getParent();
            if ((r4 instanceof BlurredRecyclerView) == false) goto L13;
            int r5 = ((BlurredRecyclerView) r4).blurTopPadding;
        L14:
            boolean r6 = DialogsAdapter.m5860$$Nest$fgetcollapsedView(this.this$0);
            int r7 = r4.getPaddingTop();
            int r8 = r4.getPaddingBottom();
            int r72 = r7 - r5;
            if (DialogsAdapter.m5863$$Nest$fgetfolderId(this.this$0) != 1) goto L27;
            if (r0 != 1) goto L27;
            if (this.this$0.itemInternals.get(0).viewType != 19) goto L27;
            int r142 = View.MeasureSpec.getSize(r14);
            if (r142 != 0) goto L22;
            r142 = r4.getMeasuredHeight();
        L22:
            if (r142 != 0) goto L25;
            r142 = (AndroidUtilities.displaySize.y - ActionBar.getCurrentActionBarHeight()) - AndroidUtilities.statusBarHeight;
        L25:
            if (DialogsAdapter.m5866$$Nest$fgetparentFragment(this.this$0).hasStories == false) goto L91;
            r142 = r142 + AndroidUtilities.dp(81.0f);
        L91:
            if (r142 < 0) goto L95;
            r2 = r142;
        L95:
            if (DialogsAdapter.m5864$$Nest$fgetisTransitionSupport(this.this$0) == false) goto L97;
            r2 = r2 + AndroidUtilities.dp(1000.0f);
        L97:
            super.onMeasure(r13, View.MeasureSpec.makeMeasureSpec(r2, TLObject.FLAG_30));
            return;
        L27:
            if (r0 == 0) goto L90;
            if (r72 != 0) goto L31;
            if (r1 == false) goto L90;
        L31:
            int r143 = View.MeasureSpec.getSize(r14);
            if (r143 != 0) goto L34;
            r143 = r4.getMeasuredHeight();
        L34:
            if (r143 != 0) goto L36;
            r143 = (AndroidUtilities.displaySize.y - ActionBar.getCurrentActionBarHeight()) - AndroidUtilities.statusBarHeight;
        L36:
            int r144 = r143 - r5;
            if (SharedConfig.useThreeLinesLayout == false) goto L39;
            float r42 = 76.0f;
        L40:
            int r43 = AndroidUtilities.dp(r42);
            int r52 = 0;
            int r9 = 0;
        L41:
            DialogsAdapter r10 = this.this$0;
            if (r52 >= r0) goto L58;
            int r102 = r10.itemInternals.get(r52).viewType;
            DialogsAdapter r11 = this.this$0;
            if (r102 != 0) goto L55;
            if (r11.itemInternals.get(r52).isForumCell == false) goto L53;
            if (r6 == true) goto L53;
            if (SharedConfig.useThreeLinesLayout == false) goto L51;
            float r103 = 86.0f;
        L52:
            r9 = r9 + AndroidUtilities.dp(r103);
        L57:
            r52 = r52 + 1;
            goto L41
        L51:
            r103 = 91.0f;
        L53:
            r9 = r9 + r43;
            goto L57
        L55:
            if (r11.itemInternals.get(r52).viewType != 1) goto L57;
        L58:
            int r92 = r9 + (r0 - 1);
            if (DialogsAdapter.m5865$$Nest$fgetonlineContacts(r10) == null) goto L61;
            r92 = r92 + (((DialogsAdapter.m5865$$Nest$fgetonlineContacts(this.this$0).size() * AndroidUtilities.dp(58.0f)) + (DialogsAdapter.m5865$$Nest$fgetonlineContacts(this.this$0).size() - 1)) + AndroidUtilities.dp(52.0f));
        L61:
            if (r1 == false) goto L63;
            int r44 = r43 + 1;
        L64:
            if (r92 >= r144) goto L77;
            r142 = ((r144 - r92) + r44) - r8;
            if (r72 == 0) goto L91;
            r142 = r142 - AndroidUtilities.statusBarHeight;
            if (r6 == false) goto L70;
        L75:
            if (r6 == false) goto L91;
        L76:
            r142 = r142 - r72;
            goto L91
        L70:
            if (DialogsAdapter.m5864$$Nest$fgetisTransitionSupport(this.this$0) == true) goto L75;
            r142 = r142 - ActionBar.getCurrentActionBarHeight();
            if ((getParent() instanceof DialogsActivity.DialogsRecyclerView) == false) goto L91;
            int r02 = ((DialogsActivity.DialogsRecyclerView) getParent()).additionalPadding;
        L74:
            r142 = r142 - r02;
            goto L91
        L77:
            int r93 = r92 - r144;
            if (r93 >= r44) goto L90;
            r142 = (r44 - r93) - r8;
            if (r72 == 0) goto L91;
            r142 = r142 - AndroidUtilities.statusBarHeight;
            if (r6 == false) goto L84;
        L88:
            if (r6 == false) goto L91;
        L84:
            if (DialogsAdapter.m5864$$Nest$fgetisTransitionSupport(this.this$0) == true) goto L88;
            r142 = r142 - ActionBar.getCurrentActionBarHeight();
            if ((getParent() instanceof DialogsActivity.DialogsRecyclerView) == false) goto L91;
            r02 = ((DialogsActivity.DialogsRecyclerView) getParent()).additionalPadding;
            goto L74
        L63:
            r44 = 0;
            goto L64
        L39:
            r42 = 70.0f;
        L90:
            r142 = 0;
            goto L91
        L13:
            r5 = 0;
            goto L14
        L5:
            if (DialogsAdapter.m5862$$Nest$fgetdialogsType(this.this$0) != 0) goto L9;
            if (MessagesController.getInstance(DialogsAdapter.m5861$$Nest$fgetcurrentAccount(this.this$0)).dialogs_dict.get(DialogObject.makeFolderDialogId(1)) == null) goto L9;
            r1 = true;
            goto L10
        }
    }

    /* renamed from: $r8$lambda$9S7fGEHjSK-snUP2r6wz80OCC2E, reason: not valid java name */
    public static /* synthetic */ void m5856$r8$lambda$9S7fGEHjSKsnUP2r6wz80OCC2E(DialogsAdapter r0, Runnable r1, ArrayList r2, DiffUtil.DiffResult r3) {
        r0.lambda$updateList$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$Ncu5D8c9rSY70kAilJcwM9XUHfE(DialogsAdapter r0, Float r1) {
        r0.lambda$onBindViewHolder$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$XG1DDXU3uTtHR5NxbWIuy5US9Rk(DialogsAdapter r0, View r1) {
        r0.lambda$onCreateViewHolder$3(r1);
    }

    public static /* synthetic */ int $r8$lambda$_IyqYv0ErkEI_Omrn2azgDWluho(MessagesController r2, int r3, TLRPC.TL_contact r4, TLRPC.TL_contact r5) {
        TLRPC.User r52 = r2.getUser(Long.valueOf(r5.user_id));
        TLRPC.User r22 = r2.getUser(Long.valueOf(r4.user_id));
        if (r52 != null) goto L5;
    L10:
        int r53 = 0;
    L11:
        if (r22 != null) goto L13;
    L18:
        int r32 = 0;
    L19:
        if (r53 <= 0) goto L25;
        if (r32 <= 0) goto L25;
        if (r53 > r32) goto L46;
        if (r53 < r32) goto L44;
    L40:
        return 0;
    L44:
        return -1;
    L46:
        return 1;
    L25:
        if (r53 >= 0) goto L31;
        if (r32 >= 0) goto L31;
        if (r53 > r32) goto L47;
        if (r53 >= r32) goto L40;
        return -1;
    L47:
        return 1;
    L31:
        if (r53 >= 0) goto L33;
        if (r32 <= 0) goto L33;
        return -1;
    L33:
        if (r53 != 0) goto L37;
        if (r32 == 0) goto L37;
        return -1;
    L37:
        if (r32 < 0) goto L41;
        if (r53 == 0) goto L40;
        return 1;
    L41:
        return 1;
    L13:
        if (r22.self == false) goto L15;
        r32 = r3 + 50000;
        goto L19
    L15:
        TLRPC.UserStatus r23 = r22.status;
        if (r23 == null) goto L18;
        r32 = r23.expires;
        goto L19
    L5:
        if (r52.self == false) goto L7;
        r53 = r3 + 50000;
        goto L11
    L7:
        TLRPC.UserStatus r54 = r52.status;
        if (r54 == null) goto L10;
        r53 = r54.expires;
        goto L11
    }

    /* renamed from: $r8$lambda$bOWDC2LVH-BW_ztqUwBt3I52P8Q, reason: not valid java name */
    public static /* synthetic */ void m5857$r8$lambda$bOWDC2LVHBW_ztqUwBt3I52P8Q(DialogsAdapter r0, DiffUtil.Callback r1, Runnable r2, ArrayList r3) {
        r0.lambda$updateList$2(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$iPI4SWtQZwjvuN0Au_-MQOoIObw, reason: not valid java name */
    public static /* synthetic */ void m5858$r8$lambda$iPI4SWtQZwjvuN0Au_MQOoIObw(DialogsAdapter r0) {
        r0.lambda$onBindViewHolder$4();
    }

    /* renamed from: -$$Nest$fgetarrowDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m5859$$Nest$fgetarrowDrawable(DialogsAdapter r0) {
        return r0.arrowDrawable;
    }

    /* renamed from: -$$Nest$fgetcollapsedView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5860$$Nest$fgetcollapsedView(DialogsAdapter r0) {
        return r0.collapsedView;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5861$$Nest$fgetcurrentAccount(DialogsAdapter r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetdialogsType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5862$$Nest$fgetdialogsType(DialogsAdapter r0) {
        return r0.dialogsType;
    }

    /* renamed from: -$$Nest$fgetfolderId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m5863$$Nest$fgetfolderId(DialogsAdapter r0) {
        return r0.folderId;
    }

    /* renamed from: -$$Nest$fgetisTransitionSupport, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m5864$$Nest$fgetisTransitionSupport(DialogsAdapter r0) {
        return r0.isTransitionSupport;
    }

    /* renamed from: -$$Nest$fgetonlineContacts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m5865$$Nest$fgetonlineContacts(DialogsAdapter r0) {
        return r0.onlineContacts;
    }

    /* renamed from: -$$Nest$fgetparentFragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DialogsActivity m5866$$Nest$fgetparentFragment(DialogsAdapter r0) {
        return r0.parentFragment;
    }

    static {
        ALLOW_UPDATE_IN_BACKGROUND = BuildVars.DEBUG_PRIVATE_VERSION;
    }

    public DialogsAdapter(DialogsActivity r3, Context r4, int r5, int r6, boolean r7, ArrayList<Long> r8, int r9, TLRPC.RequestPeerType r10) {
        boolean r0 = true;
        this.firstUpdate = true;
        this.itemInternals = new ArrayList();
        this.oldItems = new ArrayList();
        this.stableIdPointer = 10;
        this.dialogsStableIds = new LongSparseIntArray();
        this.dialogsHeaderStableIds = new HashMap();
        this.lastDialogsEmptyType = -1;
        this.mContext = r4;
        this.parentFragment = r3;
        this.dialogsType = r5;
        this.folderId = r6;
        this.isOnlySelect = r7;
        if (r6 != 0) goto L7;
        if (r5 != 0) goto L7;
        if (r7 == true) goto L7;
    L8:
        this.hasHints = r0;
        this.selectedDialogs = r8;
        this.currentAccount = r9;
        if (r3 == null) goto L11;
        long r32 = r3.getCommunityId();
    L12:
        this.communityId = r32;
        if (r6 != 0) goto L15;
        this.preloader = new DialogsPreloader();
    L15:
        this.requestPeerType = r10;
        return;
    L11:
        r32 = 0;
    L7:
        r0 = false;
        goto L8
    }

    private MessagesController.DialogFilter getCurrentFilter() {
        int r0 = this.dialogsType;
        if (r0 == 7) goto L10;
        if (r0 == 8) goto L10;
        return null;
    L10:
        return MessagesController.getInstance(this.currentAccount).selectedDialogFilter[this.dialogsType - 7];
    }

    private /* synthetic */ void lambda$onBindViewHolder$4() {
        this.parentFragment.setScrollDisabled(false);
    }

    private /* synthetic */ void lambda$onBindViewHolder$5(Float r1) {
        this.parentFragment.setContactsAlpha(r1.floatValue());
    }

    private /* synthetic */ void lambda$onCreateViewHolder$3(View r2) {
        MessagesController.getInstance(this.currentAccount).hintDialogs.clear();
        MessagesController.getGlobalMainSettings().edit().remove("installReferer").apply();
        notifyDataSetChanged();
    }

    private /* synthetic */ void lambda$updateList$1(Runnable r2, ArrayList r3, DiffUtil.DiffResult r4) {
        if (this.isCalculatingDiff == false) goto L13;
        this.isCalculatingDiff = false;
        if (r2 == null) goto L8;
        r2.run();
    L8:
        this.itemInternals = r3;
        r4.dispatchUpdatesTo(this);
        if (this.updateListPending == false) goto L12;
        this.updateListPending = false;
        updateList(r2);
        return;
    L12:
        return;
    }

    private /* synthetic */ void lambda$updateList$2(DiffUtil.Callback r2, Runnable r3, ArrayList r4) {
        AndroidUtilities.runOnUIThread(new DialogsAdapter$$ExternalSyntheticLambda7(this, r3, r4, DiffUtil.calculateDiff(r2)));
    }

    private void updateItemList() {
        long r3 = 0;
        if (this.communityId == 0) goto L6;
        updateItemListForCommunity();
        return;
    L6:
        this.itemInternals.clear();
        updateHasHints();
        MessagesController r1 = MessagesController.getInstance(this.currentAccount);
        long r5 = this.communityId;
        if (r5 == 0) goto L9;
        ArrayList<TLRPC.Dialog> r2 = r1.getDialogsByCommunity(r5);
    L12:
        int r52 = r2.size();
        this.dialogsCount = r52;
        int r6 = 0;
        this.isEmpty = false;
        if (r52 == 0) goto L15;
    L18:
        TLRPC.Dialog r7 = null;
        if (this.hasHints == false) goto L21;
    L48:
        MessagesController.DialogFilter r53 = getCurrentFilter();
        if (r53 != null) goto L51;
    L54:
        DialogsActivity r12 = this.parentFragment;
        if (r12 != null) goto L57;
    L53:
        long r16 = 0;
        if (r53 != null) goto L73;
    L74:
        DialogsActivity r32 = this.parentFragment;
        if (r32 != null) goto L77;
    L90:
        this.hasChatlistHint = false;
        int r33 = this.dialogsType;
        if (r33 == 7) goto L93;
        if (r33 == 8) goto L93;
    L102:
        if (this.requestPeerType == null) goto L105;
        this.itemInternals.add(new ItemInternal(this, 15));
    L105:
        if (this.collapsedView == false) goto L107;
    L222:
        int r13 = 0;
    L224:
        if (r13 >= r2.size()) goto L232;
        if (this.dialogsType == 2) goto L228;
    L230:
        this.itemInternals.add(new ItemInternal(this, 0, r2.get(r13)));
    L231:
        r13 = r13 + 1;
        goto L224
    L228:
        if ((r2.get(r13) instanceof DialogsActivity.DialogsHeader) == false) goto L230;
        this.itemInternals.add(new ItemInternal(this, 14, r2.get(r13)));
        goto L231
    L232:
        this.itemInternals.add(new ItemInternal(this, 10));
        return;
    L107:
        if (this.isTransitionSupport == true) goto L222;
        if (this.dialogsCount == 0) goto L112;
    L118:
        ArrayList<TLRPC.TL_contact> r34 = this.onlineContacts;
        if (r34 == null) goto L143;
        if (r34.isEmpty() == true) goto L143;
        int r35 = this.dialogsType;
        if (r35 == 7) goto L143;
        if (r35 == 8) goto L143;
        if (this.dialogsCount != 0) goto L132;
        this.isEmpty = true;
        TLRPC.RequestPeerType r36 = this.requestPeerType;
        ArrayList<ItemInternal> r122 = this.itemInternals;
        if (r36 == null) goto L130;
        r122.add(new ItemInternal(this, 16));
    L131:
        this.itemInternals.add(new ItemInternal(this, 8));
        this.itemInternals.add(new ItemInternal(this, 7));
    L137:
        int r37 = 0;
    L138:
        int r123 = this.onlineContacts.size();
        ArrayList<ItemInternal> r132 = this.itemInternals;
        if (r37 >= r123) goto L141;
        r132.add(new ItemInternal(this, 6, this.onlineContacts.get(r37)));
        r37 = r37 + 1;
        goto L138
    L141:
        r132.add(new ItemInternal(this, 10));
        boolean r38 = true;
    L158:
        TLRPC.RequestPeerType r54 = this.requestPeerType;
        if ((r54 instanceof TLRPC.TL_requestPeerTypeBroadcast) == true) goto L163;
        if ((r54 instanceof TLRPC.TL_requestPeerTypeChat) == true) goto L163;
    L166:
        if (this.allowForwardAsStories == true) goto L168;
    L170:
        if (r38 == true) goto L210;
        int r39 = 0;
    L173:
        if (r39 >= r2.size()) goto L182;
        if (this.dialogsType == 2) goto L177;
    L179:
        this.itemInternals.add(new ItemInternal(this, 0, r2.get(r39)));
    L180:
        r39 = r39 + 1;
        goto L173
    L177:
        if ((r2.get(r39) instanceof DialogsActivity.DialogsHeader) == false) goto L179;
        this.itemInternals.add(new ItemInternal(this, 14, r2.get(r39)));
        goto L180
    L182:
        if (this.communityId == r16) goto L184;
    L194:
        int r22 = this.dialogsCount;
        if (r22 != 0) goto L201;
        this.isEmpty = true;
        TLRPC.RequestPeerType r23 = this.requestPeerType;
        ArrayList<ItemInternal> r310 = this.itemInternals;
        if (r23 == null) goto L199;
        r310.add(new ItemInternal(this, 16));
        goto L210
    L199:
        r310.add(new ItemInternal(this, 5, dialogsEmptyType()));
        goto L210
    L201:
        if (this.folderId != 0) goto L208;
        if (r22 <= 10) goto L208;
        if (this.dialogsType != 0) goto L208;
        if (ExteraConfig.getHideFloatingButton() == true) goto L208;
        this.itemInternals.add(new ItemInternal(this, 11));
    L208:
        this.itemInternals.add(new ItemInternal(this, 10));
        goto L210
    L184:
        if (this.forceShowEmptyCell == true) goto L194;
        int r24 = this.dialogsType;
        if (r24 == 7) goto L194;
        if (r24 == 8) goto L194;
        if (MessagesController.getInstance(this.currentAccount).isDialogsEndReached(this.folderId) == true) goto L194;
        if (this.dialogsCount == 0) goto L193;
        this.itemInternals.add(new ItemInternal(this, 1));
    L193:
        this.itemInternals.add(new ItemInternal(this, 10));
    L210:
        if (r1.hiddenUndoChats.isEmpty() == false) goto L212;
        return;
    L212:
        if (r6 >= this.itemInternals.size()) goto L255;
        ItemInternal r25 = this.itemInternals.get(r6);
        if (r25.viewType != 0) goto L220;
        TLRPC.Dialog r26 = r25.dialog;
        if (r26 == null) goto L220;
        if (r1.isHiddenByUndo(r26.id) == false) goto L220;
        this.itemInternals.remove(r6);
        r6 = r6 - 1;
    L220:
        r6 = r6 + 1;
        goto L212
    L255:
        return;
    L168:
        if (this.dialogsType != 3) goto L170;
        this.itemInternals.add(new ItemInternal(this, 21));
    L163:
        if (this.dialogsCount <= 0) goto L166;
        this.itemInternals.add(new ItemInternal(this, 12));
        goto L166
    L130:
        r122.add(new ItemInternal(this, 5, dialogsEmptyType()));
        goto L131
    L132:
        int r311 = 0;
    L133:
        int r124 = r2.size();
        ArrayList<ItemInternal> r133 = this.itemInternals;
        if (r311 >= r124) goto L136;
        r133.add(new ItemInternal(this, 0, r2.get(r311)));
        r311 = r311 + 1;
        goto L133
    L136:
        r133.add(new ItemInternal(this, 8));
        this.itemInternals.add(new ItemInternal(this, 7));
    L143:
        if (this.hasHints == false) goto L149;
        int r312 = MessagesController.getInstance(this.currentAccount).hintDialogs.size();
        this.itemInternals.add(new ItemInternal(this, 2));
        int r125 = 0;
    L145:
        ArrayList<ItemInternal> r134 = this.itemInternals;
        if (r125 >= r312) goto L148;
        r134.add(new ItemInternal(this, 4, MessagesController.getInstance(this.currentAccount).hintDialogs.get(r125)));
        r125 = r125 + 1;
        goto L145
    L148:
        r134.add(new ItemInternal(this, 3));
    L157:
        r38 = false;
        goto L158
    L149:
        int r313 = this.dialogsType;
        if (r313 == 11) goto L156;
        if (r313 == 13) goto L156;
        if (r313 != 12) goto L157;
        this.itemInternals.add(new ItemInternal(this, 7));
    L156:
        this.itemInternals.add(new ItemInternal(this, 7));
        this.itemInternals.add(new ItemInternal(this, 12));
        goto L157
    L112:
        if (this.forceUpdatingContacts == false) goto L118;
        this.isEmpty = true;
        TLRPC.RequestPeerType r314 = this.requestPeerType;
        ArrayList<ItemInternal> r14 = this.itemInternals;
        if (r314 == null) goto L116;
        r14.add(new ItemInternal(this, 16));
    L117:
        this.itemInternals.add(new ItemInternal(this, 8));
        this.itemInternals.add(new ItemInternal(this, 7));
        this.itemInternals.add(new ItemInternal(this, 13));
        goto L157
    L116:
        r14.add(new ItemInternal(this, 5, dialogsEmptyType()));
    L93:
        if (r53 == null) goto L102;
        if (r53.isChatlist() == false) goto L102;
        r1.checkChatlistFolderUpdate(r53.id, false);
        TL_chatlists.TL_chatlists_chatlistUpdates r315 = r1.getChatlistFolderUpdates(r53.id);
        if (r315 == null) goto L102;
        if (r315.missing_peers.size() <= 0) goto L102;
        this.hasChatlistHint = true;
        this.itemInternals.add(new ItemInternal(this, r315));
        goto L102
    L77:
        if (this.dialogsType != 3) goto L90;
        if (r32.forwardOriginalChannel == 0) goto L90;
        this.itemInternals.add(new ItemInternal(this, 20));
        int r316 = 0;
    L82:
        if (r316 >= r2.size()) goto L87;
        if (r2.get(r316).id == this.parentFragment.forwardOriginalChannel) goto L85;
        r316 = r316 + 1;
        goto L82
    L85:
        r7 = r2.get(r316);
    L87:
        if (r7 != null) goto L89;
        r7 = new TLRPC.TL_dialog();
        r7.id = this.parentFragment.forwardOriginalChannel;
    L89:
        this.itemInternals.add(new ItemInternal(this, 0, r7));
        this.itemInternals.add(new ItemInternal(this, 20));
        goto L90
    L73:
        if (r53.isDefault() == false) goto L90;
    L57:
        if (r12.isReplyTo == false) goto L53;
        if (r12.replyMessageAuthor == 0) goto L53;
        this.itemInternals.add(new ItemInternal(this, 20));
        int r126 = 0;
    L62:
        if (r126 >= r2.size()) goto L67;
        r16 = r3;
        if (r2.get(r126).id == this.parentFragment.replyMessageAuthor) goto L65;
        r126 = r126 + 1;
        r3 = r16;
        goto L62
    L65:
        r7 = r2.get(r126);
    L68:
        if (r7 != null) goto L70;
        r7 = new TLRPC.TL_dialog();
        r7.id = this.parentFragment.replyMessageAuthor;
    L70:
        this.itemInternals.add(new ItemInternal(this, 0, r7));
        this.itemInternals.add(new ItemInternal(this, 20));
        goto L90
    L67:
        r16 = r3;
        goto L68
    L51:
        if (r53.isDefault() == false) goto L53;
    L21:
        if (this.dialogsType != 0) goto L48;
        int r55 = this.folderId;
        if (r55 != 0) goto L48;
        if (r1.isDialogsEndReached(r55) == false) goto L48;
        if (this.forceUpdatingContacts == true) goto L48;
        if (r1.getAllFoldersDialogsCount() <= 10) goto L31;
    L47:
        this.onlineContacts = null;
        goto L48
    L31:
        if (ContactsController.getInstance(this.currentAccount).doneLoadingContacts == false) goto L47;
        if (ContactsController.getInstance(this.currentAccount).contacts.isEmpty() == true) goto L47;
        this.onlineContacts = new ArrayList(ContactsController.getInstance(this.currentAccount).contacts);
        long r10 = UserConfig.getInstance(this.currentAccount).clientUserId;
        int r56 = this.onlineContacts.size();
        int r127 = 0;
    L35:
        ArrayList<TLRPC.TL_contact> r135 = this.onlineContacts;
        if (r127 >= r56) goto L44;
        long r136 = r135.get(r127).user_id;
        if (r136 != r10) goto L40;
    L41:
        this.onlineContacts.remove(r127);
        r127 = r127 - 1;
        r56 = r56 - 1;
    L42:
        r127 = r127 + 1;
        goto L35
    L40:
        if (r1.dialogs_dict.get(r136) == null) goto L42;
    L44:
        if (r135.isEmpty() == false) goto L46;
        this.onlineContacts = null;
        goto L48
    L46:
        sortOnlineContacts(false);
        goto L48
    L15:
        if (this.parentFragment.isArchive() == false) goto L18;
        this.itemInternals.add(new ItemInternal(this, 19));
        return;
    L9:
        r2 = this.parentFragment.getDialogsArray(this.currentAccount, this.dialogsType, this.folderId, this.dialogsListFrozen);
        if (r2 != null) goto L12;
        r2 = new ArrayList();
        goto L12
    }

    private void updateItemListForCommunity() {
        this.itemInternals.clear();
        updateHasHints();
        MessagesController.CommunityPeersDialog r0 = MessagesController.getInstance(this.currentAccount).buildCommunityPeers(this.communityId);
        this.dialogsCount = r0.getDialogsCount();
        this.isEmpty = false;
        if (this.dialogsType != 3) goto L5;
        int r2 = 2;
    L6:
        int r3 = 0;
    L7:
        if (r3 >= r2) goto L32;
        if (r3 != 0) goto L11;
        ArrayList<MessagesController.CommunityPeerDialog> r5 = r0.chatsYouAreIn;
        String r6 = LocaleController.getString(R.string.CommunitySectionChatsYouAreIn);
    L17:
        if (r5.isEmpty() == true) goto L31;
        this.itemInternals.add(new ItemInternal(this, 22, r6));
        int r62 = 0;
    L20:
        if (r62 >= r5.size()) goto L31;
        MessagesController.CommunityPeerDialog r7 = r5.get(r62);
        TLRPC.Dialog r8 = r7.dialog;
        if (r8 == null) goto L24;
        this.itemInternals.add(new ItemInternal(this, 0, r8));
    L30:
        r62 = r62 + 1;
        goto L20
    L24:
        TLRPC.Chat r82 = r7.chat;
        if (r82 == null) goto L27;
        this.itemInternals.add(new ItemInternal(this, 23, r82));
        goto L30
    L27:
        TLRPC.User r72 = r7.user;
        if (r72 == null) goto L30;
        this.itemInternals.add(new ItemInternal(this, 23, r72));
    L31:
        r3 = r3 + 1;
        goto L7
    L11:
        if (r3 != 1) goto L13;
        r5 = r0.chatsYouCanView;
        r6 = LocaleController.getString(R.string.CommunitySectionChatsYouCanView);
        goto L17
    L13:
        if (r3 != 2) goto L15;
        r5 = r0.chatsYouCanJoin;
        r6 = LocaleController.getString(R.string.CommunitySectionChatsYouCanRequestToJoin);
        goto L17
    L15:
        r5 = r0.chatsOther;
        r6 = LocaleController.getString(R.string.CommunitySectionHiddenChats);
        goto L17
    L32:
        return;
    L5:
        r2 = 4;
        goto L6
    }

    @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
    public boolean canClickButtonInside() {
        return this.selectedDialogs.isEmpty();
    }

    public int dialogsEmptyType() {
        int r0 = this.dialogsType;
        if (r0 == 7) goto L16;
        if (r0 == 8) goto L16;
        if (this.folderId != 1) goto L11;
        return 2;
    L11:
        if (this.onlineContacts == null) goto L13;
        return 1;
    L13:
        return 0;
    L16:
        if (MessagesController.getInstance(this.currentAccount).isDialogsEndReached(this.folderId) == false) goto L18;
        return 2;
    L18:
        return 3;
    }

    public void didDatabaseCleared() {
        DialogsPreloader r0 = this.preloader;
        if (r0 == null) goto L6;
        r0.clear();
        return;
    }

    public int findDialogPosition(long r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.itemInternals.size()) goto L11;
        if (this.itemInternals.get(r0).dialog == null) goto L10;
        if (this.itemInternals.get(r0).dialog.id != r4) goto L10;
        return r0;
    L10:
        r0 = r0 + 1;
        goto L4
    L11:
        return -1;
    }

    public int fixPosition(int r3) {
        if (this.hasChatlistHint == false) goto L6;
        r3 = r3 - 1;
    L6:
        if (this.hasHints == false) goto L9;
        r3 = r3 - (MessagesController.getInstance(this.currentAccount).hintDialogs.size() + 2);
    L9:
        if (this.allowForwardAsStories == true) goto L11;
    L13:
        int r2 = this.dialogsType;
        if (r2 == 11) goto L23;
        if (r2 == 13) goto L23;
        if (r2 == 12) goto L21;
        return r3;
    L21:
        return r3 - 1;
    L23:
        return r3 - 2;
    L11:
        if (this.dialogsType != 3) goto L13;
        r3 = r3 - 1;
        goto L13
    }

    public int fixScrollGap(RecyclerListView r1, int r2, int r3, boolean r4, boolean r5, boolean r6, boolean r7) {
        if (SharedConfig.useThreeLinesLayout == false) goto L5;
        float r0 = 76.0f;
    L6:
        int r02 = AndroidUtilities.dp(r0);
        int r52 = ((r1.getPaddingTop() + r3) - (r2 * r02)) - r2;
        if (r4 == false) goto L9;
        r52 = r52 + r02;
    L9:
        int r03 = r1.getPaddingTop();
        if (r52 > r03) goto L12;
        return r3;
    L12:
        return (r3 + r03) - r52;
    L5:
        r0 = 70.0f;
        goto L6
    }

    public ViewPager getArchiveHintCellPager() {
        return null;
    }

    public TL_chatlists.TL_chatlists_chatlistUpdates getChatlistUpdate() {
        ItemInternal r2 = this.itemInternals.get(0);
        if (r2 != null) goto L5;
        return null;
    L5:
        if (r2.viewType == 17) goto L7;
        return null;
    L7:
        return r2.chatlistUpdates;
    }

    public int getCurrentCount() {
        return this.currentCount;
    }

    public int getDialogsCount() {
        return this.dialogsCount;
    }

    public boolean getDialogsListIsFrozen() {
        return this.dialogsListFrozen;
    }

    public int getDialogsType() {
        return this.dialogsType;
    }

    public Object getItem(int r3) {
        if (r3 >= 0) goto L5;
    L29:
        return null;
    L5:
        if (r3 >= this.itemInternals.size()) goto L29;
        ItemInternal r32 = this.itemInternals.get(r3);
        if (ItemInternal.m5870$$Nest$fgettitle(r32) == null) goto L12;
        return ItemInternal.m5870$$Nest$fgettitle(r32);
    L12:
        if (ItemInternal.m5868$$Nest$fgetchat(r32) == null) goto L16;
        return ItemInternal.m5868$$Nest$fgetchat(r32);
    L16:
        if (ItemInternal.m5871$$Nest$fgetuser(r32) != null) goto L18;
        TLRPC.Dialog r1 = r32.dialog;
        if (r1 == null) goto L23;
        return r1;
    L23:
        if (r32.contact != null) goto L25;
        TLRPC.RecentMeUrl r2 = r32.recentMeUrl;
        if (r2 == null) goto L29;
        return r2;
    L25:
        return MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r32.contact.user_id));
    L18:
        return ItemInternal.m5871$$Nest$fgetuser(r32);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int r0 = this.itemInternals.size();
        this.currentCount = r0;
        return r0;
    }

    public int getItemHeight(int r2) {
        if (this.itemInternals.get(r2).viewType == 0) goto L5;
        return 0;
    L5:
        if (this.itemInternals.get(r2).isForumCell == false) goto L16;
        if (this.collapsedView == true) goto L16;
        if (SharedConfig.useThreeLinesLayout == false) goto L11;
        float r1 = 86.0f;
    L12:
        int r12 = AndroidUtilities.dp(r1);
    L14:
        return r12 + 1;
    L11:
        r1 = 91.0f;
    L16:
        if (SharedConfig.useThreeLinesLayout == false) goto L18;
        float r13 = 76.0f;
    L19:
        r12 = AndroidUtilities.dp(r13);
        goto L14
    L18:
        r13 = 70.0f;
        goto L19
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int r1) {
        return ItemInternal.m5869$$Nest$fgetstableId(this.itemInternals.get(r1));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int r1) {
        return this.itemInternals.get(r1).viewType;
    }

    public boolean isAllowForwardAsStories() {
        return this.allowForwardAsStories;
    }

    public boolean isDataSetChanged() {
        return true;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
    public boolean isEnabled(RecyclerView.ViewHolder r2) {
        int r1 = r2.getItemViewType();
        if (r1 != 1) goto L5;
        return false;
    L5:
        if (r1 != 5) goto L7;
        return false;
    L7:
        if (r1 != 3) goto L9;
        return false;
    L9:
        if (r1 != 8) goto L11;
        return false;
    L11:
        if (r1 != 7) goto L13;
        return false;
    L13:
        if (r1 != 10) goto L15;
        return false;
    L15:
        if (r1 != 11) goto L17;
        return false;
    L17:
        if (r1 != 13) goto L19;
        return false;
    L19:
        if (r1 != 15) goto L21;
        return false;
    L21:
        if (r1 != 16) goto L23;
        return false;
    L23:
        if (r1 != 18) goto L25;
        return false;
    L25:
        if (r1 != 19) goto L27;
        return false;
    L27:
        if (r1 == 20) goto L42;
        return true;
    L42:
        return false;
    }

    public void moveDialogs(RecyclerListView r9, int r10, int r11) {
        char r3 = 0;
        ArrayList<TLRPC.Dialog> r92 = this.parentFragment.getDialogsArray(this.currentAccount, this.dialogsType, this.folderId, false);
        int r102 = fixPosition(r10);
        int r112 = fixPosition(r11);
        TLRPC.Dialog r0 = r92.get(r102);
        TLRPC.Dialog r1 = r92.get(r112);
        int r2 = this.dialogsType;
        if (r2 == 7) goto L7;
        if (r2 == 8) goto L7;
        int r22 = r0.pinnedNum;
        r0.pinnedNum = r1.pinnedNum;
        r1.pinnedNum = r22;
    L11:
        Collections.swap(r92, r102, r112);
        updateList(null);
        return;
    L7:
        MessagesController.DialogFilter[] r23 = MessagesController.getInstance(this.currentAccount).selectedDialogFilter;
        if (this.dialogsType != 8) goto L10;
        r3 = 1;
    L10:
        MessagesController.DialogFilter r24 = r23[r3];
        int r32 = r24.pinnedDialogs.get(r0.id);
        int r4 = r24.pinnedDialogs.get(r1.id);
        r24.pinnedDialogs.put(r0.id, r4);
        r24.pinnedDialogs.put(r1.id, r32);
        goto L11
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void notifyDataSetChanged() {
        if (this.isCalculatingDiff == false) goto L5;
        this.itemInternals = new ArrayList();
    L5:
        this.isCalculatingDiff = false;
        updateItemList();
        super.notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void notifyItemMoved(int r1, int r2) {
        super.notifyItemMoved(r1, r2);
    }

    public void onArchiveSettingsClick() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder r22, int r23) {
        int r3 = r22.getItemViewType();
        boolean r9 = false;
        if (r3 != 0) goto L5;
        TLRPC.Dialog r32 = (TLRPC.Dialog) getItem(r23);
        Object r10 = getItem(r23 + 1);
        if ((r10 instanceof TLRPC.Dialog) == false) goto L118;
        TLRPC.Dialog r102 = (TLRPC.Dialog) r10;
    L119:
        int r11 = this.dialogsType;
        if (r11 != 2) goto L122;
    L181:
        ProfileSearchCell r14 = (ProfileSearchCell) r22.itemView;
        long r4 = r14.getDialogId();
        int r112 = 1;
        if (r32.id == 0) goto L190;
        TLRPC.Chat r7 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r32.id));
        if (r7 != null) goto L186;
    L191:
        if (r7 == null) goto L214;
        String r33 = r7.title;
        if (ChatObject.isChannel(r7) == true) goto L195;
    L204:
        int r8 = r7.participants_count;
        if (r8 == 0) goto L208;
        String r6 = LocaleController.formatPluralStringComma("Members", r8);
    L199:
        String r17 = r33;
        String r18 = r6;
        TLRPC.Chat r15 = r7;
    L224:
        if (r102 == null) goto L226;
        boolean r34 = true;
    L227:
        r14.useSeparator = r34;
        r14.setData(r15, null, r17, r18, false, false);
        boolean r35 = this.selectedDialogs.contains(Long.valueOf(r14.getDialogId()));
        if (r4 != r14.getDialogId()) goto L230;
        r9 = true;
    L230:
        r14.setChecked(r35, r9);
    L232:
        if (r23 < (this.dialogsCount + r112)) goto L235;
        r22.itemView.setAlpha(1.0f);
        return;
    L235:
        return;
    L226:
        r34 = false;
        goto L227
    L208:
        if (r7.has_geo == false) goto L211;
        r6 = LocaleController.getString(R.string.MegaLocation);
        goto L199
    L211:
        if (ChatObject.isPublic(r7) == true) goto L213;
        r6 = LocaleController.getString(R.string.MegaPrivate).toLowerCase();
        goto L199
    L213:
        r6 = LocaleController.getString(R.string.MegaPublic).toLowerCase();
        goto L199
    L195:
        if (r7.megagroup == true) goto L204;
        int r62 = r7.participants_count;
        if (r62 == 0) goto L201;
        r6 = LocaleController.formatPluralStringComma("Subscribers", r62);
        goto L199
    L201:
        if (ChatObject.isPublic(r7) == true) goto L203;
        r6 = LocaleController.getString(R.string.ChannelPrivate).toLowerCase();
        goto L199
    L203:
        r6 = LocaleController.getString(R.string.ChannelPublic).toLowerCase();
        goto L199
    L214:
        TLRPC.User r36 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r32.id));
        String r63 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r36 == 0) goto L223;
        String r72 = UserObject.getUserName(r36);
        if (UserObject.isReplyUser(r36) == false) goto L219;
    L221:
        r15 = r36;
        r18 = r63;
        r17 = r72;
        goto L224
    L219:
        if (r36.bot == false) goto L222;
        r63 = LocaleController.getString(R.string.Bot);
        goto L221
    L222:
        r63 = LocaleController.formatUserStatus(this.currentAccount, r36);
        goto L221
    L223:
        r18 = _UrlKt.FRAGMENT_ENCODE_SET;
        r15 = null;
        r17 = null;
        goto L224
    L186:
        if (r7.migrated_to == null) goto L191;
        TLRPC.Chat r82 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r7.migrated_to.channel_id));
        if (r82 == null) goto L191;
        r7 = r82;
        goto L191
    L190:
        r7 = null;
        goto L191
    L122:
        if (r11 == 15) goto L181;
        DialogCell r42 = (DialogCell) r22.itemView;
        if (this.communityId != 0) goto L127;
    L129:
        boolean r64 = false;
    L130:
        r42.isHiddenInCommunity = r64;
        r42.useSeparator = false;
        r42.fullSeparator = false;
        if (this.dialogsType == 0) goto L133;
    L139:
        r42.setChecked(this.selectedDialogs.contains(Long.valueOf(r32.id)), false);
        if (r23 != 1) goto L156;
        DialogsActivity r65 = this.parentFragment;
        if (r65 == null) goto L156;
        if (r65.isReplyTo == false) goto L156;
        if (r65.replyMessageAuthor == 0) goto L156;
        if (r32.top_message != 0) goto L156;
        MessagesController.DialogFilter r5 = getCurrentFilter();
        if (r5 != null) goto L152;
    L155:
        r42.setCustomMessage(DialogObject.getStatus(this.parentFragment.replyMessageAuthor));
    L173:
        r42.setDialog(r32, this.dialogsType, this.folderId);
        r42.checkHeight();
        boolean r52 = r42.collapsed;
        boolean r66 = this.collapsedView;
        if (r52 == r66) goto L176;
        r42.collapsed = r66;
        r42.requestLayout();
    L176:
        DialogsPreloader r43 = this.preloader;
        if (r43 != null) goto L179;
    L16:
        r112 = 1;
        goto L232
    L179:
        if (r23 >= 10) goto L16;
        r43.add(r32.id);
        goto L16
    L152:
        if (r5.isDefault() == true) goto L155;
        r42.setCustomMessage(null);
    L156:
        if (r23 != 1) goto L172;
        DialogsActivity r67 = this.parentFragment;
        if (r67 == null) goto L172;
        if (this.dialogsType != 3) goto L172;
        if (r67.forwardOriginalChannel == 0) goto L172;
        if (r32.top_message != 0) goto L172;
        MessagesController.DialogFilter r53 = getCurrentFilter();
        if (r53 != null) goto L168;
    L171:
        r42.setCustomMessage(DialogObject.getStatus(this.parentFragment.forwardOriginalChannel));
        goto L173
    L168:
        if (r53.isDefault() == true) goto L171;
        r42.setCustomMessage(null);
    L172:
        r42.setCustomMessage(null);
        goto L173
    L133:
        if (AndroidUtilities.isTablet() == false) goto L139;
        if (r32.id != this.openedDialogId) goto L137;
        boolean r68 = true;
    L138:
        r42.setDialogSelected(r68);
        goto L139
    L137:
        r68 = false;
        goto L138
    L127:
        if (ChatObject.isHiddenInCommunity(this.currentAccount, r32.id) == false) goto L129;
        r64 = true;
        goto L130
    L118:
        r102 = null;
        goto L119
    L5:
        if (r3 != 4) goto L7;
        ((DialogMeUrlCell) r22.itemView).setRecentMeUrl((TLRPC.RecentMeUrl) getItem(r23));
        goto L16
    L7:
        if (r3 != 5) goto L9;
        DialogsEmptyCell r37 = (DialogsEmptyCell) r22.itemView;
        int r44 = this.lastDialogsEmptyType;
        int r54 = dialogsEmptyType();
        this.lastDialogsEmptyType = r54;
        r37.setType(r54, this.isOnlySelect);
        int r55 = this.dialogsType;
        if (r55 == 7) goto L16;
        if (r55 == 8) goto L16;
        r37.setOnUtyanAnimationEndListener(new DialogsAdapter$$ExternalSyntheticLambda1(this));
        r37.setOnUtyanAnimationUpdateListener(new DialogsAdapter$$ExternalSyntheticLambda2(this));
        if (r37.isUtyanAnimationTriggered() == true) goto L99;
        if (this.dialogsCount != 0) goto L99;
        this.parentFragment.setContactsAlpha(0.0f);
        this.parentFragment.setScrollDisabled(true);
    L99:
        if (this.onlineContacts == null) goto L105;
        if (r44 != 0) goto L105;
        if (r37.isUtyanAnimationTriggered() == true) goto L16;
        r37.startUtyanCollapseAnimation(true);
    L105:
        if (this.forceUpdatingContacts == false) goto L110;
        if (this.dialogsCount != 0) goto L16;
        r37.startUtyanCollapseAnimation(false);
        goto L16
    L110:
        if (r37.isUtyanAnimationTriggered() == false) goto L16;
        if (this.lastDialogsEmptyType != 0) goto L16;
        r37.startUtyanExpandAnimation();
        goto L16
    L9:
        if (r3 != 6) goto L11;
        ((UserCell) r22.itemView).setData((TLRPC.User) getItem(r23), null, null, 0);
        goto L16
    L11:
        if (r3 == 7) goto L72;
        if (r3 == 11) goto L62;
        if (r3 == 12) goto L48;
        switch(r3) {
            case 14: goto L40;
            case 15: goto L39;
            case 16: goto L38;
            case 17: goto L35;
            default: goto L15;
        };
    L15:
        switch(r3) {
            case 20: goto L23;
            case 21: goto L22;
            case 22: goto L21;
            case 23: goto L17;
            default: goto L16;
        };
    L17:
        Object r38 = getItem(r23);
        DialogCell r103 = (DialogCell) r22.itemView;
        if ((r38 instanceof TLRPC.Chat) == false) goto L20;
        TLRPC.Chat r39 = (TLRPC.Chat) r38;
        r103.isHiddenInCommunity = ChatObject.isHiddenInCommunity(this.currentAccount, r39);
        r103.setCustomMessageWithoutRebuild(LocaleController.formatPluralString("Members", r39.participants_count, new Object[0]));
        r103.setDialog(-r39.id, null, 0, false, false);
        goto L16
    L20:
        TLRPC.User r310 = (TLRPC.User) r38;
        r103.isHiddenInCommunity = ChatObject.isHiddenInCommunity(this.currentAccount, r310);
        r103.setCustomMessageWithoutRebuild(LocaleController.getString(R.string.Bot));
        r103.setDialog(r310.id, null, 0, false, false);
        goto L16
    L21:
        ((HeaderCell) r22.itemView).setText((String) getItem(r23));
        goto L16
    L22:
        TLRPC.Dialog r311 = (TLRPC.Dialog) getItem(r23 + 1);
        DialogCell r312 = (DialogCell) r22.itemView;
        DialogCell.CustomDialog r45 = new DialogCell.CustomDialog();
        r45.name = LocaleController.getString(R.string.StoriesForwardTitle);
        r45.message = LocaleController.getString(R.string.StoriesForwardText);
        r312.useSeparator = false;
        r312.fullSeparator = false;
        r312.setDialog(r45);
        r312.checkHeight();
        goto L16
    L23:
        GraySectionCell r313 = (GraySectionCell) r22.itemView;
        DialogsActivity r46 = this.parentFragment;
        if (r46 == null) goto L31;
        if (r46.isReplyTo == false) goto L31;
        if (r23 != 0) goto L29;
        r313.setText(LocaleController.getString(R.string.ReplyDialogMessageAuthor));
        goto L16
    L29:
        r313.setText(LocaleController.getString(R.string.ReplyDialogYourChats));
    L31:
        if (this.dialogsType != 3) goto L16;
        if (r23 != 0) goto L34;
        r313.setText(LocaleController.getString(R.string.ForwardDialogYourChannel));
        goto L16
    L34:
        r313.setText(LocaleController.getString(R.string.ReplyDialogYourChats));
        goto L16
    L35:
        DialogsHintCell r314 = (DialogsHintCell) r22.itemView;
        TL_chatlists.TL_chatlists_chatlistUpdates r47 = this.itemInternals.get(r23).chatlistUpdates;
        if (r47 == null) goto L16;
        int r48 = r47.missing_peers.size();
        r314.setText(AndroidUtilities.replaceSingleTag(LocaleController.formatPluralString("FolderUpdatesTitle", r48, new Object[0]), Theme.key_windowBackgroundWhiteValueText, 0, null), LocaleController.formatPluralString("FolderUpdatesSubtitle", r48, new Object[0]));
        goto L16
    L38:
        ((DialogsRequestedEmptyCell) r22.itemView).set(this.requestPeerType);
        goto L16
    L39:
        ((RequestPeerRequirementsCell) r22.itemView).set(this.requestPeerType);
        goto L16
    L40:
        HeaderCell r315 = (HeaderCell) r22.itemView;
        r315.setTextSize(14.0f);
        r315.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
        r315.setBackgroundColor(Theme.getColor(Theme.key_graySection));
        int r56 = ((DialogsActivity.DialogsHeader) getItem(r23)).headerType;
        if (r56 == 0) goto L47;
        if (r56 == 1) goto L46;
        if (r56 != 2) goto L16;
        r315.setText(LocaleController.getString(R.string.FilterGroups));
        goto L16
    L46:
        r315.setText(LocaleController.getString(R.string.MyGroups));
        goto L16
    L47:
        r315.setText(LocaleController.getString(R.string.MyChannels));
        goto L16
    L48:
        View r316 = r22.itemView;
        if ((r316 instanceof TextCell) == false) goto L236;
        TextCell r317 = (TextCell) r316;
        int r49 = Theme.key_windowBackgroundWhiteBlueText4;
        r317.setColors(r49, r49);
        TLRPC.RequestPeerType r410 = this.requestPeerType;
        if (r410 != null) goto L54;
        String r411 = LocaleController.getString(R.string.CreateGroupForImport);
        int r57 = R.drawable.msg_groups_create;
        if (this.dialogsCount == 0) goto L60;
        r9 = true;
    L60:
        r317.setTextAndIcon(r411, r57, r9);
    L61:
        r317.setIsInDialogs();
        r317.setOffsetFromImage(75);
        goto L16
    L54:
        if ((r410 instanceof TLRPC.TL_requestPeerTypeBroadcast) == false) goto L56;
        r317.setTextAndIcon(LocaleController.getString(R.string.CreateChannelForThis), R.drawable.msg_channel_create, true);
        goto L61
    L56:
        r317.setTextAndIcon(LocaleController.getString(R.string.CreateGroupForThis), R.drawable.msg_groups_create, true);
        goto L61
    L236:
        return;
    L62:
        TextInfoPrivacyCell r318 = (TextInfoPrivacyCell) r22.itemView;
        r318.setText(LocaleController.getString(R.string.TapOnThePencilButton));
        if (this.arrowDrawable != null) goto L65;
        Drawable r412 = this.mContext.getResources().getDrawable(R.drawable.arrow_newchat);
        this.arrowDrawable = r412;
        r412.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText4), PorterDuff.Mode.MULTIPLY));
    L65:
        LinkSpanDrawable.LinksTextView r319 = r318.getTextView();
        r319.setCompoundDrawablePadding(AndroidUtilities.dp(4.0f));
        DialogsActivity r413 = this.parentFragment;
        if (r413 != null) goto L68;
    L70:
        Drawable r414 = this.arrowDrawable;
    L71:
        r319.setCompoundDrawablesWithIntrinsicBounds(null, null, r414, null);
        r319.getLayoutParams().width = -2;
        goto L16
    L68:
        if (r413.storiesEnabled == false) goto L70;
        r414 = null;
        goto L71
    L72:
        HeaderCell r320 = (HeaderCell) r22.itemView;
        int r415 = this.dialogsType;
        if (r415 == 11) goto L85;
        if (r415 == 12) goto L85;
        if (r415 == 13) goto L85;
        if (this.dialogsCount == 0) goto L81;
    L83:
        int r416 = R.string.YourContacts;
    L84:
        r320.setText(LocaleController.getString(r416));
        goto L16
    L81:
        if (this.forceUpdatingContacts == false) goto L83;
        r416 = R.string.ConnectingYourContacts;
    L85:
        if (r23 != 0) goto L87;
        r320.setText(LocaleController.getString(R.string.ImportHeader));
        goto L16
    L87:
        r320.setText(LocaleController.getString(R.string.ImportHeaderContacts));
        goto L16
    }

    @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
    public void onButtonClicked(DialogCell r1) {
    }

    @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
    public void onButtonLongPress(DialogCell r1) {
    }

    public void onCreateGroupForThisClick() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v10, types: [org.telegram.ui.Cells.UserCell] */
    /* JADX WARN: Type inference failed for: r2v11, types: [android.view.View, org.telegram.ui.Cells.HeaderCell] */
    /* JADX WARN: Type inference failed for: r2v12, types: [android.view.View, org.telegram.ui.Cells.ShadowSectionCell] */
    /* JADX WARN: Type inference failed for: r2v13, types: [org.telegram.ui.Adapters.DialogsAdapter$LastEmptyView] */
    /* JADX WARN: Type inference failed for: r2v14, types: [android.view.View, org.telegram.ui.Adapters.DialogsAdapter$4] */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16, types: [org.telegram.ui.Cells.RequestPeerRequirementsCell] */
    /* JADX WARN: Type inference failed for: r2v17, types: [org.telegram.ui.Adapters.DialogsAdapter$3] */
    /* JADX WARN: Type inference failed for: r2v18, types: [org.telegram.ui.Cells.DialogsHintCell] */
    /* JADX WARN: Type inference failed for: r2v19, types: [org.telegram.ui.Adapters.DialogsAdapter$5] */
    /* JADX WARN: Type inference failed for: r2v2, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r2v20, types: [android.view.ViewGroup, org.telegram.ui.Adapters.DialogsAdapter$LastEmptyView] */
    /* JADX WARN: Type inference failed for: r2v21, types: [org.telegram.ui.Cells.GraySectionCell] */
    /* JADX WARN: Type inference failed for: r2v22, types: [org.telegram.ui.Cells.HeaderCell] */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v24, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r2v25, types: [android.view.View, org.telegram.ui.Cells.TextCell] */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5, types: [org.telegram.ui.Components.FlickerLoadingView] */
    /* JADX WARN: Type inference failed for: r2v6, types: [android.view.ViewGroup, org.telegram.ui.Cells.HeaderCell] */
    /* JADX WARN: Type inference failed for: r2v7, types: [android.view.View, android.view.ViewGroup, org.telegram.ui.Adapters.DialogsAdapter$2] */
    /* JADX WARN: Type inference failed for: r2v8, types: [org.telegram.ui.Cells.DialogMeUrlCell] */
    /* JADX WARN: Type inference failed for: r2v9, types: [org.telegram.ui.Cells.DialogsEmptyCell] */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r18, int r19) {
        switch(r19) {
            case 0: goto L49;
            case 1: goto L40;
            case 2: goto L31;
            case 3: goto L30;
            case 4: goto L29;
            case 5: goto L28;
            case 6: goto L27;
            case 7: goto L22;
            case 8: goto L21;
            case 9: goto L4;
            case 10: goto L20;
            case 11: goto L19;
            case 12: goto L4;
            case 13: goto L40;
            case 14: goto L18;
            case 15: goto L17;
            case 16: goto L16;
            case 17: goto L15;
            case 18: goto L14;
            case 19: goto L13;
            case 20: goto L12;
            case 21: goto L49;
            case 22: goto L11;
            case 23: goto L7;
            default: goto L4;
        };
    L4:
        ?? r2 = new TextCell(this.mContext);
        if (this.dialogsType != 15) goto L68;
        r2.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
    L68:
        if (r19 != 5) goto L70;
    L73:
        int r1 = -1;
    L74:
        r2.setLayoutParams(new RecyclerView.LayoutParams(-1, r1));
        return new RecyclerListView.Holder(r2);
    L70:
        if (r19 == 19) goto L73;
        r1 = -2;
        goto L74
    L7:
        DialogCell r9 = new DialogCell(this.parentFragment, this.mContext, true, false, this.currentAccount, null);
        if (this.communityId == 0) goto L10;
        r9.insideCommunityList = true;
        r9.insideCommunityListNoDialog = true;
    L10:
        r2 = r9;
        goto L68
    L11:
        r2 = new HeaderCell(this.mContext);
        goto L68
    L12:
        r2 = new GraySectionCell(this.mContext);
        goto L68
    L13:
        r2 = new LastEmptyView(this, this.mContext);
        r2.addView(new ArchiveHelp(this.mContext, this.currentAccount, null, new DialogsAdapter$$ExternalSyntheticLambda5(this), null), LayoutHelper.createFrame(-1, -1.0f, 17, 0.0f, -40.0f, 0.0f, 0.0f));
        goto L68
    L14:
        r2 = new AnonymousClass5(this, this.mContext);
        goto L68
    L15:
        r2 = new DialogsHintCell(this.mContext);
        goto L68
    L16:
        r2 = new AnonymousClass3(this, this.mContext);
        goto L68
    L17:
        r2 = new RequestPeerRequirementsCell(this.mContext);
        goto L68
    L18:
        HeaderCell r8 = new HeaderCell(this.mContext, Theme.key_graySectionText, 16, 0, false);
        r8.setHeight(32);
        r8.setClickable(false);
        r2 = r8;
        goto L68
    L19:
        r2 = new AnonymousClass4(this, this.mContext);
        CombinedDrawable r3 = new CombinedDrawable(new ColorDrawable(Theme.getColor(Theme.key_windowBackgroundGray)), Theme.getThemedDrawableByKey(this.mContext, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
        r3.setFullsize(true);
        r2.setBackgroundDrawable(r3);
        goto L68
    L20:
        r2 = new LastEmptyView(this, this.mContext);
        goto L68
    L21:
        r2 = new ShadowSectionCell(this.mContext);
        CombinedDrawable r32 = new CombinedDrawable(new ColorDrawable(Theme.getColor(Theme.key_windowBackgroundGray)), Theme.getThemedDrawableByKey(this.mContext, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
        r32.setFullsize(true);
        r2.setBackgroundDrawable(r32);
        goto L68
    L22:
        r2 = new HeaderCell(this.mContext);
        DialogsActivity r0 = this.parentFragment;
        if (r0 != null) goto L25;
    L26:
        r2.setPadding(0, 0, 0, AndroidUtilities.dp(12.0f));
        goto L68
    L25:
        if (r0.isReplyTo == true) goto L68;
    L27:
        r2 = new UserCell(this.mContext, 8, 0, false);
        goto L68
    L28:
        r2 = new DialogsEmptyCell(this.mContext);
        goto L68
    L29:
        r2 = new DialogMeUrlCell(this.mContext);
        goto L68
    L30:
        r2 = new AnonymousClass2(this, this.mContext);
        r2.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        View r33 = new View(this.mContext);
        r33.setBackgroundDrawable(Theme.getThemedDrawableByKey(this.mContext, R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow));
        r2.addView(r33, LayoutHelper.createFrame(-1, -1.0f));
        goto L68
    L31:
        r2 = new HeaderCell(this.mContext);
        r2.setText(LocaleController.getString(R.string.RecentlyViewed));
        TextView r34 = new TextView(this.mContext);
        r34.setTextSize(1, 15.0f);
        r34.setTypeface(AndroidUtilities.bold());
        r34.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueHeader));
        r34.setText(LocaleController.getString(R.string.RecentlyViewedHide));
        int r7 = 3;
        if (LocaleController.isRTL == false) goto L34;
        int r6 = 3;
    L35:
        r34.setGravity(r6 | 16);
        if (LocaleController.isRTL == true) goto L39;
        r7 = 5;
    L39:
        r2.addView(r34, LayoutHelper.createFrame(-1, -1.0f, r7 | 48, 17.0f, 15.0f, 17.0f, 0.0f));
        r34.setOnClickListener(new DialogsAdapter$$ExternalSyntheticLambda4(this));
        goto L68
    L34:
        r6 = 5;
        goto L35
    L40:
        r2 = new FlickerLoadingView(this.mContext);
        r2.setIsSingleCell(true);
        if (r19 != 13) goto L43;
        int r62 = 18;
    L44:
        r2.setViewType(r62);
        if (r62 != 18) goto L47;
        r2.setIgnoreHeightCheck(true);
    L47:
        if (r19 != 13) goto L68;
        r2.setItemsCount((int) ((AndroidUtilities.displaySize.y * 0.5f) / AndroidUtilities.dp(64.0f)));
        goto L68
    L43:
        r62 = 7;
        goto L44
    L49:
        int r72 = this.dialogsType;
        if (r72 == 2) goto L63;
        if (r72 == 15) goto L63;
        DialogCell r10 = new DialogCell(this.parentFragment, this.mContext, true, false, this.currentAccount, null);
        if (showOpenBotButton() == false) goto L56;
        r10.allowBotOpenButton(true, new DialogsAdapter$$ExternalSyntheticLambda3(this));
    L56:
        r10.setArchivedPullAnimation(this.pullForegroundDrawable);
        r10.setPreloader(this.preloader);
        r10.setDialogCellDelegate(this);
        r10.setIsTransitionSupport(this.isTransitionSupport);
        if (r19 != 21) goto L60;
        r10.setIsShareToStoryCell();
    L60:
        if (this.communityId == 0) goto L62;
        r10.insideCommunityList = true;
    L62:
        r2 = r10;
    L65:
        if (this.dialogsType != 15) goto L68;
        r2.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
    L63:
        r2 = new ProfileSearchCell(this.mContext);
        goto L65
    }

    public void onOpenBot(TLRPC.User r1) {
    }

    public void onReorderStateChanged(boolean r1) {
        this.isReordering = r1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(RecyclerView.ViewHolder r5) {
        View r52 = r5.itemView;
        if ((r52 instanceof DialogCell) == false) goto L6;
        DialogCell r53 = (DialogCell) r52;
        r53.onReorderStateChanged(this.isReordering, false);
        r53.checkCurrentDialogIndex(this.dialogsListFrozen);
        r53.setChecked(this.selectedDialogs.contains(Long.valueOf(r53.getDialogId())), false);
        return;
    }

    @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
    public void openHiddenStories() {
        StoriesController r0 = MessagesController.getInstance(this.currentAccount).getStoriesController();
        if (r0.getHiddenList().isEmpty() == false) goto L5;
        return;
    L5:
        int r2 = 0;
        if (r0.getUnreadState(DialogObject.getPeerDialogId(r0.getHiddenList().get(0).peer)) == 0) goto L8;
        boolean r1 = true;
    L9:
        ArrayList<Long> r7 = new ArrayList();
    L11:
        if (r2 >= r0.getHiddenList().size()) goto L18;
        long r4 = DialogObject.getPeerDialogId(r0.getHiddenList().get(r2).peer);
        if (r1 == true) goto L15;
    L16:
        r7.add(Long.valueOf(r4));
    L17:
        r2 = r2 + 1;
        goto L11
    L15:
        if (r0.getUnreadState(r4) == 0) goto L17;
    L18:
        this.parentFragment.getOrCreateStoryViewer().open(this.mContext, null, r7, 0, null, null, StoriesListPlaceProvider.of(this.recyclerListView, true), false);
        return;
    L8:
        r1 = false;
        goto L9
    }

    @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
    public void openStory(DialogCell r4, Runnable r5) {
        MessagesController.getInstance(this.currentAccount);
        if (MessagesController.getInstance(this.currentAccount).getStoriesController().hasStories(r4.getDialogId()) == false) goto L6;
        this.parentFragment.getOrCreateStoryViewer().doOnAnimationReady(r5);
        this.parentFragment.getOrCreateStoryViewer().open(this.parentFragment.getContext(), r4.getDialogId(), StoriesListPlaceProvider.of((RecyclerListView) r4.getParent()));
        return;
    }

    public void pause() {
        DialogsPreloader r0 = this.preloader;
        if (r0 == null) goto L6;
        r0.pause();
        return;
    }

    public void resume() {
        DialogsPreloader r0 = this.preloader;
        if (r0 == null) goto L6;
        r0.resume();
        return;
    }

    public void setAllowForwardAsStories(boolean r1) {
        this.allowForwardAsStories = r1;
    }

    public void setArchivedPullDrawable(PullForegroundDrawable r1) {
        this.pullForegroundDrawable = r1;
    }

    public void setCollapsedView(boolean r3, RecyclerListView r4) {
        this.collapsedView = r3;
        int r2 = 0;
        int r0 = 0;
    L4:
        if (r0 >= r4.getChildCount()) goto L9;
        if ((r4.getChildAt(r0) instanceof DialogCell) == false) goto L8;
        ((DialogCell) r4.getChildAt(r0)).collapsed = r3;
    L8:
        r0 = r0 + 1;
        goto L4
    L9:
        int r02 = 0;
    L11:
        if (r02 >= r4.getCachedChildCount()) goto L16;
        if ((r4.getCachedChildAt(r02) instanceof DialogCell) == false) goto L15;
        ((DialogCell) r4.getCachedChildAt(r02)).collapsed = r3;
    L15:
        r02 = r02 + 1;
        goto L11
    L16:
        int r03 = 0;
    L18:
        if (r03 >= r4.getHiddenChildCount()) goto L24;
        if ((r4.getHiddenChildAt(r03) instanceof DialogCell) == false) goto L22;
        ((DialogCell) r4.getHiddenChildAt(r03)).collapsed = r3;
    L22:
        r03 = r03 + 1;
    L24:
        if (r2 >= r4.getAttachedScrapChildCount()) goto L29;
        if ((r4.getAttachedScrapChildAt(r2) instanceof DialogCell) == false) goto L28;
        ((DialogCell) r4.getAttachedScrapChildAt(r2)).collapsed = r3;
    L28:
        r2 = r2 + 1;
        goto L24
    }

    public void setDialogsListFrozen(boolean r1) {
        this.dialogsListFrozen = r1;
    }

    public void setDialogsType(int r1) {
        this.dialogsType = r1;
        notifyDataSetChanged();
    }

    public void setForceShowEmptyCell(boolean r1) {
        this.forceShowEmptyCell = r1;
    }

    public void setForceUpdatingContacts(boolean r1) {
        this.forceUpdatingContacts = r1;
    }

    public void setIsTransitionSupport() {
        this.isTransitionSupport = true;
    }

    public void setOpenedDialogId(long r1) {
        this.openedDialogId = r1;
    }

    public void setRecyclerListView(RecyclerListView r1) {
        this.recyclerListView = r1;
    }

    @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
    public void showChatPreview(DialogCell r1) {
        this.parentFragment.showChatPreview(r1);
    }

    public boolean showOpenBotButton() {
        return false;
    }

    public void sortOnlineContacts(boolean r5) {
        if (this.onlineContacts == null) goto L18;
        if (r5 == true) goto L6;
    L8:
        this.lastSortTime = SystemClock.elapsedRealtime();
        int r0 = ConnectionsManager.getInstance(this.currentAccount).getCurrentTime();     // Catch: Exception -> L13
        MessagesController r1 = MessagesController.getInstance(this.currentAccount);     // Catch: Exception -> L13
        Collections.sort(this.onlineContacts, new DialogsAdapter$$ExternalSyntheticLambda0(r1, r0));     // Catch: Exception -> L13
        if (r5 == false) goto L19;
        notifyDataSetChanged();     // Catch: Exception -> L13
        return;
    L19:
        return;
    L13:
        e = move-exception;
        FileLog.e(e);
        return;
    L6:
        if ((SystemClock.elapsedRealtime() - this.lastSortTime) >= 2000) goto L8;
        return;
    }

    public void updateHasHints() {
        if (this.folderId == 0) goto L5;
    L11:
        boolean r0 = false;
    L12:
        this.hasHints = r0;
        return;
    L5:
        if (this.dialogsType != 0) goto L11;
        if (this.isOnlySelect == true) goto L11;
        if (MessagesController.getInstance(this.currentAccount).hintDialogs.isEmpty() == true) goto L11;
        r0 = true;
        goto L12
    }

    public void updateList(Runnable r5) {
        if (this.isCalculatingDiff == false) goto L6;
        this.updateListPending = true;
        return;
    L6:
        this.isCalculatingDiff = true;
        ArrayList<ItemInternal> r0 = new ArrayList();
        this.oldItems = r0;
        r0.addAll(this.itemInternals);
        updateItemList();
        ArrayList<ItemInternal> r02 = new ArrayList(this.itemInternals);
        this.itemInternals = this.oldItems;
        AnonymousClass1 r1 = new AnonymousClass1(this, r02);
        if (this.itemInternals.size() >= 50) goto L9;
    L13:
        DiffUtil.DiffResult r12 = DiffUtil.calculateDiff(r1);
        this.isCalculatingDiff = false;
        if (r5 == null) goto L16;
        r5.run();
    L16:
        this.itemInternals = r02;
        r12.dispatchUpdatesTo(this);
        return;
    L9:
        if (ALLOW_UPDATE_IN_BACKGROUND == false) goto L13;
        Utilities.searchQueue.postRunnable(new DialogsAdapter$$ExternalSyntheticLambda6(this, r1, r5, r02));
    }
}
