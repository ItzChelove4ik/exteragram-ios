package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Property;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.StringTokenizer;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.ringtone.RingtoneDataStore;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Adapters.FiltersView;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.SharedDocumentCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.FilteredSearchView;
import org.telegram.ui.PhotoPickerActivity;

/* loaded from: classes7.dex */
public class ChatAttachAlertDocumentLayout extends ChatAttachAlert.AttachAlertLayout {
    private float additionalTranslationY;
    private boolean allowMusic;
    private LinearLayoutManager backgroundLayoutManager;
    private ListAdapter backgroundListAdapter;
    private RecyclerListView backgroundListView;
    private boolean canSelectOnlyImageFiles;
    private int currentAnimationType;
    private File currentDir;
    private DocumentSelectActivityDelegate delegate;
    private StickerEmptyView emptyView;
    private FiltersView filtersView;
    private AnimatorSet filtersViewAnimator;
    private boolean hasFiles;
    public boolean isSoundPicker;
    private LinearLayoutManager layoutManager;
    private ListAdapter listAdapter;
    ValueAnimator listAnimation;
    private RecyclerListView listView;
    private FlickerLoadingView loadingView;
    private int maxSelectedFiles;
    private BroadcastReceiver receiver;
    private boolean receiverRegistered;
    private boolean scrolling;
    private SearchAdapter searchAdapter;
    private ActionBarMenuItem searchItem;
    private boolean searching;
    private HashMap<String, ListItem> selectedFiles;
    public ArrayList<String> selectedFilesOrder;
    private HashMap<FilteredSearchView.MessageHashId, MessageObject> selectedMessages;
    private boolean sendPressed;
    private boolean sortByName;
    private ActionBarMenuItem sortItem;

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertDocumentLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends BroadcastReceiver {
        final /* synthetic */ ChatAttachAlertDocumentLayout this$0;

        public static /* synthetic */ void $r8$lambda$2nTyL6aGYldVJkRGvUfSiUURDL4(AnonymousClass1 r0) {
            r0.lambda$onReceive$0();
        }

        public AnonymousClass1(ChatAttachAlertDocumentLayout r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onReceive$0() {
            File r0 = ChatAttachAlertDocumentLayout.m9584$$Nest$fgetcurrentDir(this.this$0);     // Catch: Exception -> L9
            ChatAttachAlertDocumentLayout r1 = this.this$0;
            if (r0 != null) goto L6;
            ChatAttachAlertDocumentLayout.m9606$$Nest$mlistRoots(r1);     // Catch: Exception -> L9
        L7:
            ChatAttachAlertDocumentLayout.m9610$$Nest$mupdateSearchButton(this.this$0);     // Catch: Exception -> L9
            return;
        L6:
            ChatAttachAlertDocumentLayout.m9605$$Nest$mlistFiles(r1, ChatAttachAlertDocumentLayout.m9584$$Nest$fgetcurrentDir(r1));     // Catch: Exception -> L9
        L9:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context r3, Intent r4) {
            ChatAttachAlertDocumentLayout$1$$ExternalSyntheticLambda0 r32 = new ChatAttachAlertDocumentLayout$1$$ExternalSyntheticLambda0(this);
            if ("android.intent.action.MEDIA_UNMOUNTED".equals(r4.getAction()) == false) goto L6;
            ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$0).postDelayed(r32, 1000);
            return;
        L6:
            r32.run();
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertDocumentLayout$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        final /* synthetic */ ChatAttachAlertDocumentLayout this$0;

        public AnonymousClass2(ChatAttachAlertDocumentLayout r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchCollapse() {
            ChatAttachAlertDocumentLayout.m9604$$Nest$fputsearching(this.this$0, false);
            ChatAttachAlertDocumentLayout.m9600$$Nest$fgetsortItem(this.this$0).setVisibility(0);
            if (ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$0).getAdapter() == ChatAttachAlertDocumentLayout.m9590$$Nest$fgetlistAdapter(this.this$0)) goto L5;
            ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$0).setAdapter(ChatAttachAlertDocumentLayout.m9590$$Nest$fgetlistAdapter(this.this$0));
        L5:
            ChatAttachAlertDocumentLayout.m9590$$Nest$fgetlistAdapter(this.this$0).notifyDataSetChanged();
            ChatAttachAlertDocumentLayout.m9594$$Nest$fgetsearchAdapter(this.this$0).search(null, true);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
            ChatAttachAlertDocumentLayout.m9604$$Nest$fputsearching(this.this$0, true);
            ChatAttachAlertDocumentLayout.m9600$$Nest$fgetsortItem(this.this$0).setVisibility(8);
            ChatAttachAlertDocumentLayout r3 = this.this$0;
            r3.parentAlert.makeFocusable(ChatAttachAlertDocumentLayout.m9595$$Nest$fgetsearchItem(r3).getSearchField(), true);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchFilterCleared(FiltersView.MediaFilterData r3) {
            ChatAttachAlertDocumentLayout.m9594$$Nest$fgetsearchAdapter(this.this$0).removeSearchFilter(r3);
            ChatAttachAlertDocumentLayout.m9594$$Nest$fgetsearchAdapter(this.this$0).search(ChatAttachAlertDocumentLayout.m9595$$Nest$fgetsearchItem(this.this$0).getSearchField().getText().toString(), false);
            SearchAdapter.m9622$$Nest$mupdateFiltersView(ChatAttachAlertDocumentLayout.m9594$$Nest$fgetsearchAdapter(this.this$0), true, null, null, true);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r2) {
            ChatAttachAlertDocumentLayout.m9594$$Nest$fgetsearchAdapter(this.this$0).search(r2.getText().toString(), false);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertDocumentLayout$3, reason: invalid class name */
    public class AnonymousClass3 extends StickerEmptyView {
        final /* synthetic */ ChatAttachAlertDocumentLayout this$0;

        public AnonymousClass3(ChatAttachAlertDocumentLayout r1, Context r2, View r3, int r4, Theme.ResourcesProvider r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // android.view.View
        public float getTranslationY() {
            return super.getTranslationY() - ChatAttachAlertDocumentLayout.m9580$$Nest$fgetadditionalTranslationY(this.this$0);
        }

        @Override // android.view.View
        public void setTranslationY(float r2) {
            super.setTranslationY(r2 + ChatAttachAlertDocumentLayout.m9580$$Nest$fgetadditionalTranslationY(this.this$0));
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertDocumentLayout$4, reason: invalid class name */
    public class AnonymousClass4 extends RecyclerListView {
        Paint paint;
        final /* synthetic */ ChatAttachAlertDocumentLayout this$0;

        public AnonymousClass4(ChatAttachAlertDocumentLayout r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
            this.paint = new Paint();
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r4) {
            if (ChatAttachAlertDocumentLayout.m9583$$Nest$fgetcurrentAnimationType(this.this$0) == 2) goto L5;
        L14:
            super.dispatchDraw(r4);
            return;
        L5:
            if (getChildCount() <= 0) goto L14;
            float r0 = 2.1474836E9f;
            int r1 = 0;
        L8:
            if (r1 >= getChildCount()) goto L13;
            if (getChildAt(r1).getY() >= r0) goto L12;
            r0 = getChildAt(r1).getY();
        L12:
            r1 = r1 + 1;
            goto L8
        L13:
            this.paint.setColor(Theme.getColor(Theme.key_dialogBackground));
            goto L14
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (ChatAttachAlertDocumentLayout.m9583$$Nest$fgetcurrentAnimationType(this.this$0) == 0) goto L7;
            return false;
        L7:
            return super.onTouchEvent(r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertDocumentLayout$5, reason: invalid class name */
    public class AnonymousClass5 extends RecyclerListView {
        Paint paint;
        final /* synthetic */ ChatAttachAlertDocumentLayout this$0;

        public AnonymousClass5(ChatAttachAlertDocumentLayout r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
            this.paint = new Paint();
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r4) {
            if (ChatAttachAlertDocumentLayout.m9583$$Nest$fgetcurrentAnimationType(this.this$0) == 1) goto L5;
        L14:
            super.dispatchDraw(r4);
            return;
        L5:
            if (getChildCount() <= 0) goto L14;
            float r0 = 2.1474836E9f;
            int r1 = 0;
        L8:
            if (r1 >= getChildCount()) goto L13;
            if (getChildAt(r1).getY() >= r0) goto L12;
            r0 = getChildAt(r1).getY();
        L12:
            r1 = r1 + 1;
            goto L8
        L13:
            this.paint.setColor(Theme.getColor(Theme.key_dialogBackground));
            goto L14
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertDocumentLayout$6, reason: invalid class name */
    public class AnonymousClass6 extends FillLastLinearLayoutManager {
        final /* synthetic */ ChatAttachAlertDocumentLayout this$0;

        /* renamed from: org.telegram.ui.Components.ChatAttachAlertDocumentLayout$6$1, reason: invalid class name */
        public class AnonymousClass1 extends LinearSmoothScroller {
            final /* synthetic */ AnonymousClass6 this$1;

            public AnonymousClass1(AnonymousClass6 r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            public int calculateDyToMakeVisible(View r1, int r2) {
                return super.calculateDyToMakeVisible(r1, r2) - ((ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$1.this$0).getPaddingTop() - AndroidUtilities.statusBarHeight) - AndroidUtilities.dp(56.0f));
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            public int calculateTimeForDeceleration(int r1) {
                return super.calculateTimeForDeceleration(r1) * 2;
            }
        }

        public AnonymousClass6(ChatAttachAlertDocumentLayout r1, Context r2, int r3, boolean r4, int r5, RecyclerView r6) {
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

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertDocumentLayout$7, reason: invalid class name */
    public class AnonymousClass7 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ChatAttachAlertDocumentLayout this$0;

        public AnonymousClass7(ChatAttachAlertDocumentLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r4, int r5) {
            boolean r42 = false;
            if (r5 != 0) goto L12;
            int r0 = AndroidUtilities.dp(13.0f);
            int r1 = this.this$0.parentAlert.getBackgroundPaddingTop();
            if ((((this.this$0.parentAlert.scrollOffsetY[0] - r1) - r0) + r1) >= ActionBar.getCurrentActionBarHeight()) goto L12;
            RecyclerListView.Holder r02 = (RecyclerListView.Holder) ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$0).findViewHolderForAdapterPosition(0);
            if (r02 == null) goto L12;
            int r03 = (r02.itemView.getTop() - AndroidUtilities.statusBarHeight) - AndroidUtilities.dp(56.0f);
            if (r03 <= 0) goto L12;
            ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$0).smoothScrollBy(0, r03);
        L12:
            if (r5 == 1) goto L14;
        L18:
            ChatAttachAlertDocumentLayout r3 = this.this$0;
            if (r5 == 0) goto L21;
            r42 = true;
        L21:
            ChatAttachAlertDocumentLayout.m9603$$Nest$fputscrolling(r3, r42);
            return;
        L14:
            if (ChatAttachAlertDocumentLayout.m9596$$Nest$fgetsearching(this.this$0) == false) goto L18;
            if (ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$0).getAdapter() != ChatAttachAlertDocumentLayout.m9594$$Nest$fgetsearchAdapter(this.this$0)) goto L18;
            AndroidUtilities.hideKeyboard(this.this$0.parentAlert.getCurrentFocus());
            goto L18
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r3, int r4, int r5) {
            ChatAttachAlertDocumentLayout r42 = this.this$0;
            r42.parentAlert.updateLayout(r42, true, r5);
            ChatAttachAlertDocumentLayout.m9609$$Nest$mupdateEmptyViewPosition(this.this$0);
            if (ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$0).getAdapter() != ChatAttachAlertDocumentLayout.m9594$$Nest$fgetsearchAdapter(this.this$0)) goto L10;
            int r43 = ChatAttachAlertDocumentLayout.m9589$$Nest$fgetlayoutManager(this.this$0).findFirstVisibleItemPosition();
            int r52 = ChatAttachAlertDocumentLayout.m9589$$Nest$fgetlayoutManager(this.this$0).findLastVisibleItemPosition();
            int r44 = Math.abs(r52 - r43) + 1;
            int r32 = r3.getAdapter().getItemCount();
            if (r44 > 0) goto L7;
            return;
        L7:
            if (r52 < (r32 - 10)) goto L12;
            ChatAttachAlertDocumentLayout.m9594$$Nest$fgetsearchAdapter(this.this$0).loadMore();
            return;
        L12:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertDocumentLayout$8, reason: invalid class name */
    public class AnonymousClass8 implements PhotoPickerActivity.PhotoPickerActivityDelegate {
        final /* synthetic */ ChatAttachAlertDocumentLayout this$0;
        final /* synthetic */ HashMap val$selectedPhotos;
        final /* synthetic */ ArrayList val$selectedPhotosOrder;

        public AnonymousClass8(ChatAttachAlertDocumentLayout r1, HashMap r2, ArrayList r3) {
            this.this$0 = r1;
            this.val$selectedPhotos = r2;
            this.val$selectedPhotosOrder = r3;
        }

        @Override // org.telegram.ui.PhotoPickerActivity.PhotoPickerActivityDelegate
        public void actionButtonPressed(boolean r1, boolean r2, int r3, int r4) {
            if (r1 == true) goto L5;
            ChatAttachAlertDocumentLayout.m9607$$Nest$msendSelectedPhotos(this.this$0, this.val$selectedPhotos, this.val$selectedPhotosOrder, r2, r3);
            return;
        }

        @Override // org.telegram.ui.PhotoPickerActivity.PhotoPickerActivityDelegate
        public void onCaptionChanged(CharSequence r1) {
        }

        @Override // org.telegram.ui.PhotoPickerActivity.PhotoPickerActivityDelegate
        public void onOpenInPressed() {
            ChatAttachAlertDocumentLayout.m9585$$Nest$fgetdelegate(this.this$0).startDocumentSelectActivity();
        }

        @Override // org.telegram.ui.PhotoPickerActivity.PhotoPickerActivityDelegate
        public void selectedPhotosChanged() {
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertDocumentLayout$9, reason: invalid class name */
    public class AnonymousClass9 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatAttachAlertDocumentLayout this$0;

        public AnonymousClass9(ChatAttachAlertDocumentLayout r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            super.onAnimationEnd(r2);
            ChatAttachAlertDocumentLayout.m9581$$Nest$fgetbackgroundListView(this.this$0).setVisibility(8);
            ChatAttachAlertDocumentLayout.m9601$$Nest$fputcurrentAnimationType(this.this$0, 0);
            ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$0).setAlpha(1.0f);
            ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$0).setScaleX(1.0f);
            ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$0).setScaleY(1.0f);
            ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$0).setTranslationX(0.0f);
            ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$0).invalidate();
        }
    }

    /* loaded from: classes3.dex */
    public interface DocumentSelectActivityDelegate {
        void didSelectFiles(ArrayList<String> r1, String r2, ArrayList<TLRPC.MessageEntity> r3, ArrayList<MessageObject> r4, boolean r5, int r6, int r7, long r8, boolean r10, long r11);

        default void didSelectPhotos(ArrayList<SendMessagesHelper.SendingMediaInfo> r1, boolean r2, int r3, int r4, long r5) {
        }

        void startDocumentSelectActivity();

        default void startMusicSelectActivity() {
        }
    }

    public static class HistoryEntry {
        File dir;
        int scrollItem;
        int scrollOffset;
        String title;

        private HistoryEntry() {
        }

        public /* synthetic */ HistoryEntry(ChatAttachAlertDocumentLayoutIA r1) {
            this();
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private ArrayList<HistoryEntry> history;
        private ArrayList<ListItem> items;
        private Context mContext;
        private ArrayList<ListItem> recentItems;
        final /* synthetic */ ChatAttachAlertDocumentLayout this$0;

        /* renamed from: -$$Nest$fgethistory, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m9611$$Nest$fgethistory(ListAdapter r0) {
            return r0.history;
        }

        /* renamed from: -$$Nest$fgetitems, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m9612$$Nest$fgetitems(ListAdapter r0) {
            return r0.items;
        }

        /* renamed from: -$$Nest$fgetrecentItems, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m9613$$Nest$fgetrecentItems(ListAdapter r0) {
            return r0.recentItems;
        }

        public ListAdapter(ChatAttachAlertDocumentLayout r1, Context r2) {
            this.this$0 = r1;
            this.items = new ArrayList();
            this.history = new ArrayList();
            this.recentItems = new ArrayList();
            this.mContext = r2;
        }

        public ListItem getItem(int r3) {
            int r0 = this.items.size();
            if (r3 >= r0) goto L7;
            return this.items.get(r3);
        L7:
            if (this.history.isEmpty() == true) goto L9;
            return null;
        L9:
            if (this.recentItems.isEmpty() == true) goto L19;
            if (r3 != r0) goto L12;
            return null;
        L12:
            if (r3 == (r0 + 1)) goto L21;
            int r32 = r3 - (this.items.size() + 2);
            if (r32 < this.recentItems.size()) goto L16;
            return null;
        L16:
            return this.recentItems.get(r32);
        L21:
            return null;
        L19:
            return null;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            int r0 = this.items.size();
            if (this.history.isEmpty() == false) goto L8;
            if (this.recentItems.isEmpty() == true) goto L8;
            r0 = r0 + (this.recentItems.size() + 2);
        L8:
            return r0 + 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (r3 != (getItemCount() - 1)) goto L6;
            return 3;
        L6:
            int r2 = this.items.size();
            if (r3 != r2) goto L11;
            return 2;
        L11:
            if (r3 != (r2 + 1)) goto L14;
            return 0;
        L14:
            return 1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() != 1) goto L5;
            return true;
        L5:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            ChatAttachAlertDocumentLayout.m9608$$Nest$mupdateEmptyView(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r10, int r11) {
            int r0 = r10.getItemViewType();
            if (r0 != 0) goto L5;
            HeaderCell r102 = (HeaderCell) r10.itemView;
            if (ChatAttachAlertDocumentLayout.m9599$$Nest$fgetsortByName(this.this$0) == false) goto L25;
            r102.setText(LocaleController.getString(R.string.RecentFilesAZ));
            return;
        L25:
            r102.setText(LocaleController.getString(R.string.RecentFiles));
            return;
        L5:
            if (r0 == 1) goto L7;
            return;
        L7:
            ListItem r02 = getItem(r11);
            SharedDocumentCell r2 = (SharedDocumentCell) r10.itemView;
            int r7 = r02.icon;
            if (r7 == 0) goto L14;
            String r3 = r02.title;
            String r4 = r02.subtitle;
            if (r11 == (this.items.size() - 1)) goto L12;
            boolean r8 = true;
        L13:
            r2.setTextAndValueAndTypeAndThumb(r3, r4, null, null, r7, r8);
        L15:
            File r112 = r02.file;
            ChatAttachAlertDocumentLayout r32 = this.this$0;
            if (r112 == null) goto L19;
            r2.setChecked(ChatAttachAlertDocumentLayout.m9597$$Nest$fgetselectedFiles(r32).containsKey(r02.file.toString()), !ChatAttachAlertDocumentLayout.m9593$$Nest$fgetscrolling(this.this$0));
            return;
        L19:
            r2.setChecked(false, !ChatAttachAlertDocumentLayout.m9593$$Nest$fgetscrolling(r32));
            return;
        L12:
            r8 = false;
            goto L13
        L14:
            r2.setTextAndValueAndTypeAndThumb(r02.title, r02.subtitle, r02.ext.toUpperCase().substring(0, Math.min(r02.ext.length(), 4)), r02.thumb, 0, false);
            goto L15
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            if (r3 != 0) goto L4;
            View r22 = new HeaderCell(this.mContext, this.this$0.resourcesProvider);
        L12:
            return new RecyclerListView.Holder(r22);
        L4:
            if (r3 != 1) goto L6;
            r22 = new SharedDocumentCell(this.mContext, 1, this.this$0.resourcesProvider);
            goto L12
        L6:
            if (r3 == 2) goto L8;
            r22 = new View(this.mContext);
            r22.setTag(-33024);
            goto L12
        L8:
            r22 = new ShadowSectionCell(this.mContext);
            goto L12
        }
    }

    public static class ListItem {
        public String ext;
        public File file;
        public int icon;
        public String subtitle;
        public String thumb;
        public String title;

        private ListItem() {
            this.subtitle = _UrlKt.FRAGMENT_ENCODE_SET;
            this.ext = _UrlKt.FRAGMENT_ENCODE_SET;
        }

        public /* synthetic */ ListItem(ChatAttachAlertDocumentLayoutIA r1) {
            this();
        }
    }

    public class SearchAdapter extends RecyclerListView.SectionsAdapter {
        private Runnable clearCurrentResultsRunnable;
        private String currentDataQuery;
        private long currentSearchDialogId;
        private FiltersView.MediaFilterData currentSearchFilter;
        private ArrayList<FiltersView.MediaFilterData> currentSearchFilters;
        private long currentSearchMaxDate;
        private long currentSearchMinDate;
        private boolean endReached;
        private boolean firstLoading;
        private boolean isLoading;
        private String lastMessagesSearchString;
        private String lastSearchFilterQueryString;
        private Runnable localSearchRunnable;
        private ArrayList<Object> localTipChats;
        private ArrayList<FiltersView.DateData> localTipDates;
        private Context mContext;
        private final FilteredSearchView.MessageHashId messageHashIdTmp;
        public ArrayList<MessageObject> messages;
        public SparseArray<MessageObject> messagesById;
        private int nextSearchRate;
        private AnimationNotificationsLocker notificationsLocker;
        private int requestIndex;
        private ArrayList<ListItem> searchResult;
        private Runnable searchRunnable;
        public HashMap<String, ArrayList<MessageObject>> sectionArrays;
        public ArrayList<String> sections;
        final /* synthetic */ ChatAttachAlertDocumentLayout this$0;

        /* renamed from: org.telegram.ui.Components.ChatAttachAlertDocumentLayout$SearchAdapter$1, reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            final /* synthetic */ SearchAdapter this$1;

            public AnonymousClass1(SearchAdapter r1) {
                this.this$1 = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (SearchAdapter.m9617$$Nest$fgetisLoading(this.this$1) == false) goto L6;
                this.this$1.messages.clear();
                this.this$1.sections.clear();
                this.this$1.sectionArrays.clear();
                this.this$1.notifyDataSetChanged();
                return;
            }
        }

        /* renamed from: org.telegram.ui.Components.ChatAttachAlertDocumentLayout$SearchAdapter$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ SearchAdapter this$1;

            public AnonymousClass2(SearchAdapter r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                if (ChatAttachAlertDocumentLayout.m9587$$Nest$fgetfiltersView(this.this$1.this$0).getTag() != null) goto L5;
                ChatAttachAlertDocumentLayout.m9587$$Nest$fgetfiltersView(this.this$1.this$0).setVisibility(4);
            L5:
                ChatAttachAlertDocumentLayout.m9602$$Nest$fputfiltersViewAnimator(this.this$1.this$0, null);
            }
        }

        /* renamed from: org.telegram.ui.Components.ChatAttachAlertDocumentLayout$SearchAdapter$3, reason: invalid class name */
        public class AnonymousClass3 implements ViewTreeObserver.OnPreDrawListener {
            final /* synthetic */ SearchAdapter this$1;
            final /* synthetic */ View val$finalProgressView;
            final /* synthetic */ int val$finalProgressViewPosition;

            /* renamed from: org.telegram.ui.Components.ChatAttachAlertDocumentLayout$SearchAdapter$3$1, reason: invalid class name */
            public class AnonymousClass1 extends AnimatorListenerAdapter {
                final /* synthetic */ AnonymousClass3 this$2;

                public AnonymousClass1(AnonymousClass3 r1) {
                    this.this$2 = r1;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator r1) {
                    SearchAdapter.m9619$$Nest$fgetnotificationsLocker(this.this$2.this$1).unlock();
                }
            }

            /* renamed from: org.telegram.ui.Components.ChatAttachAlertDocumentLayout$SearchAdapter$3$2, reason: invalid class name */
            public class AnonymousClass2 extends AnimatorListenerAdapter {
                final /* synthetic */ AnonymousClass3 this$2;
                final /* synthetic */ RecyclerView.LayoutManager val$layoutManager;

                public AnonymousClass2(AnonymousClass3 r1, RecyclerView.LayoutManager r2) {
                    this.this$2 = r1;
                    this.val$layoutManager = r2;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator r2) {
                    this.this$2.val$finalProgressView.setAlpha(1.0f);
                    this.val$layoutManager.stopIgnoringView(this.this$2.val$finalProgressView);
                    ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$2.this$1.this$0).removeView(this.this$2.val$finalProgressView);
                }
            }

            public AnonymousClass3(SearchAdapter r1, View r2, int r3) {
                this.this$1 = r1;
                this.val$finalProgressView = r2;
                this.val$finalProgressViewPosition = r3;
            }

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                this.this$1.this$0.getViewTreeObserver().removeOnPreDrawListener(this);
                int r0 = ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$1.this$0).getChildCount();
                AnimatorSet r1 = new AnimatorSet();
                int r3 = 0;
            L4:
                if (r3 >= r0) goto L12;
                View r7 = ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$1.this$0).getChildAt(r3);
                if (this.val$finalProgressView != null) goto L8;
            L10:
                r7.setAlpha(0.0f);
                ObjectAnimator r4 = ObjectAnimator.ofFloat(r7, View.ALPHA, new float[]{0.0f, 1.0f});
                r4.setStartDelay((int) ((Math.min(ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$1.this$0).getMeasuredHeight(), Math.max(0, r7.getTop())) / ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$1.this$0).getMeasuredHeight()) * 100.0f));
                r4.setDuration(200);
                r1.playTogether(new Animator[]{r4});
            L11:
                r3 = r3 + 1;
                goto L4
            L8:
                if (ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$1.this$0).getChildAdapterPosition(r7) >= this.val$finalProgressViewPosition) goto L10;
            L12:
                r1.addListener(new AnonymousClass1(this));
                SearchAdapter.m9619$$Nest$fgetnotificationsLocker(this.this$1).lock();
                r1.start();
                View r02 = this.val$finalProgressView;
                if (r02 != null) goto L15;
            L19:
                return true;
            L15:
                if (r02.getParent() != null) goto L19;
                ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$1.this$0).addView(this.val$finalProgressView);
                RecyclerView.LayoutManager r03 = ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$1.this$0).getLayoutManager();
                if (r03 == null) goto L19;
                r03.ignoreView(this.val$finalProgressView);
                View r12 = this.val$finalProgressView;
                ObjectAnimator r13 = ObjectAnimator.ofFloat(r12, View.ALPHA, new float[]{r12.getAlpha(), 0.0f});
                r13.addListener(new AnonymousClass2(this, r03));
                r13.start();
                goto L19
            }
        }

        /* renamed from: org.telegram.ui.Components.ChatAttachAlertDocumentLayout$SearchAdapter$4, reason: invalid class name */
        public class AnonymousClass4 implements ViewTreeObserver.OnPreDrawListener {
            final /* synthetic */ SearchAdapter this$1;
            final /* synthetic */ boolean val$animated;
            final /* synthetic */ MessageObject val$messageObject;
            final /* synthetic */ SharedDocumentCell val$sharedDocumentCell;

            public AnonymousClass4(SearchAdapter r1, SharedDocumentCell r2, MessageObject r3, boolean r4) {
                this.this$1 = r1;
                this.val$sharedDocumentCell = r2;
                this.val$messageObject = r3;
                this.val$animated = r4;
            }

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                this.val$sharedDocumentCell.getViewTreeObserver().removeOnPreDrawListener(this);
                if (this.this$1.this$0.parentAlert.actionBar.isActionModeShowed() == false) goto L5;
                SearchAdapter.m9618$$Nest$fgetmessageHashIdTmp(this.this$1).set(this.val$messageObject.getId(), this.val$messageObject.getDialogId());
                this.val$sharedDocumentCell.setChecked(ChatAttachAlertDocumentLayout.m9598$$Nest$fgetselectedMessages(this.this$1.this$0).containsKey(SearchAdapter.m9618$$Nest$fgetmessageHashIdTmp(this.this$1)), this.val$animated);
                return true;
            L5:
                this.val$sharedDocumentCell.setChecked(false, this.val$animated);
                return true;
            }
        }

        public static /* synthetic */ void $r8$lambda$3063dzi9bMD_UISt2_IsffygYwE(SearchAdapter r0, long r1, String r3, AccountInstance r4, long r5, long r7, boolean r9, String r10, int r11) {
            r0.lambda$searchGlobal$4(r1, r3, r4, r5, r7, r9, r10, r11);
        }

        public static /* synthetic */ void $r8$lambda$4PXWLWw74bAiZGlojxMgBZplQfo(SearchAdapter r0, AccountInstance r1, String r2, int r3, boolean r4, long r5, long r7, ArrayList r9, ArrayList r10, TLObject r11, TLRPC.TL_error r12) {
            r0.lambda$searchGlobal$3(r1, r2, r3, r4, r5, r7, r9, r10, r11, r12);
        }

        /* renamed from: $r8$lambda$7r8urnWo32cUu-6M3ybulMVmot0, reason: not valid java name */
        public static /* synthetic */ void m9614$r8$lambda$7r8urnWo32cUu6M3ybulMVmot0(SearchAdapter r0, String r1, boolean r2, ArrayList r3) {
            r0.lambda$search$0(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$RJGWkF24QKT8CGx6vxK6pgUTuaA(SearchAdapter r0, ArrayList r1) {
            r0.lambda$updateSearchResults$5(r1);
        }

        public static /* synthetic */ void $r8$lambda$TE0O6Pa_kQjBrXFUgVcwfvNfT1w(SearchAdapter r0, int r1, TLRPC.TL_error r2, TLObject r3, AccountInstance r4, boolean r5, String r6, ArrayList r7, long r8, long r10, ArrayList r12, ArrayList r13) {
            r0.lambda$searchGlobal$2(r1, r2, r3, r4, r5, r6, r7, r8, r10, r12, r13);
        }

        /* renamed from: $r8$lambda$rcp64mL-mncofSO5a-mWQvu0JFc, reason: not valid java name */
        public static /* synthetic */ void m9615$r8$lambda$rcp64mLmncofSO5amWQvu0JFc(SearchAdapter r0, String r1) {
            r0.lambda$search$1(r1);
        }

        /* renamed from: -$$Nest$fgetcurrentSearchFilters, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m9616$$Nest$fgetcurrentSearchFilters(SearchAdapter r0) {
            return r0.currentSearchFilters;
        }

        /* renamed from: -$$Nest$fgetisLoading, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m9617$$Nest$fgetisLoading(SearchAdapter r0) {
            return r0.isLoading;
        }

        /* renamed from: -$$Nest$fgetmessageHashIdTmp, reason: not valid java name */
        public static /* bridge */ /* synthetic */ FilteredSearchView.MessageHashId m9618$$Nest$fgetmessageHashIdTmp(SearchAdapter r0) {
            return r0.messageHashIdTmp;
        }

        /* renamed from: -$$Nest$fgetnotificationsLocker, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimationNotificationsLocker m9619$$Nest$fgetnotificationsLocker(SearchAdapter r0) {
            return r0.notificationsLocker;
        }

        /* renamed from: -$$Nest$fgetsearchResult, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m9620$$Nest$fgetsearchResult(SearchAdapter r0) {
            return r0.searchResult;
        }

        /* renamed from: -$$Nest$maddSearchFilter, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m9621$$Nest$maddSearchFilter(SearchAdapter r0, FiltersView.MediaFilterData r1) {
            r0.addSearchFilter(r1);
        }

        /* renamed from: -$$Nest$mupdateFiltersView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m9622$$Nest$mupdateFiltersView(SearchAdapter r0, boolean r1, ArrayList r2, ArrayList r3, boolean r4) {
            r0.updateFiltersView(r1, r2, r3, r4);
        }

        public SearchAdapter(ChatAttachAlertDocumentLayout r4, Context r5) {
            this.this$0 = r4;
            this.searchResult = new ArrayList();
            this.messageHashIdTmp = new FilteredSearchView.MessageHashId(0, 0);
            this.localTipChats = new ArrayList();
            this.localTipDates = new ArrayList();
            this.messages = new ArrayList();
            this.messagesById = new SparseArray();
            this.sections = new ArrayList();
            this.sectionArrays = new HashMap();
            this.currentSearchFilters = new ArrayList();
            this.firstLoading = true;
            this.notificationsLocker = new AnimationNotificationsLocker();
            this.clearCurrentResultsRunnable = new AnonymousClass1(this);
            this.mContext = r5;
        }

        private void addSearchFilter(FiltersView.MediaFilterData r3) {
            if (this.currentSearchFilters.isEmpty() == true) goto L11;
            int r0 = 0;
        L6:
            if (r0 >= this.currentSearchFilters.size()) goto L11;
            if (r3.isSameType(this.currentSearchFilters.get(r0)) == true) goto L9;
            r0 = r0 + 1;
            goto L6
        L9:
            return;
        L11:
            this.currentSearchFilters.add(r3);
            this.this$0.parentAlert.actionBar.setSearchFilter(r3);
            this.this$0.parentAlert.actionBar.setSearchFieldText(_UrlKt.FRAGMENT_ENCODE_SET);
            updateFiltersView(true, null, null, true);
        }

        private /* synthetic */ void lambda$search$0(String r9, boolean r10, ArrayList r11) {
            String r0 = r9.trim().toLowerCase();
            if (r0.length() != 0) goto L6;
            updateSearchResults(new ArrayList(), r9);
            return;
        L6:
            String r1 = LocaleController.getInstance().getTranslitString(r0);
            if (r0.equals(r1) == false) goto L9;
        L10:
            r1 = null;
        L12:
            if (r1 == null) goto L14;
            int r4 = 1;
        L15:
            int r42 = r4 + 1;
            String[] r5 = new String[r42];
            r5[0] = r0;
            if (r1 == null) goto L18;
            r5[1] = r1;
        L18:
            ArrayList<ListItem> r02 = new ArrayList();
            if (r10 == true) goto L38;
            int r102 = 0;
        L22:
            if (r102 >= r11.size()) goto L38;
            ListItem r12 = (ListItem) r11.get(r102);
            File r3 = r12.file;
            if (r3 == null) goto L37;
            if (r3.isDirectory() == true) goto L37;
            int r32 = 0;
        L29:
            if (r32 >= r42) goto L37;
            String r6 = r5[r32];
            String r7 = r12.title;
            if (r7 == null) goto L33;
            boolean r62 = r7.toLowerCase().contains(r6);
        L34:
            if (r62 == true) goto L35;
            r32 = r32 + 1;
            goto L29
        L35:
            r02.add(r12);
            goto L37
        L33:
            r62 = false;
        L37:
            r102 = r102 + 1;
        L38:
            updateSearchResults(r02, r9);
            return;
        L14:
            r4 = 0;
            goto L15
        L9:
            if (r1.length() != 0) goto L12;
            goto L10
        }

        private /* synthetic */ void lambda$search$1(String r5) {
            ArrayList r0 = new ArrayList(ListAdapter.m9612$$Nest$fgetitems(ChatAttachAlertDocumentLayout.m9590$$Nest$fgetlistAdapter(this.this$0)));
            if (ListAdapter.m9611$$Nest$fgethistory(ChatAttachAlertDocumentLayout.m9590$$Nest$fgetlistAdapter(this.this$0)).isEmpty() == false) goto L5;
            r0.addAll(0, ListAdapter.m9613$$Nest$fgetrecentItems(ChatAttachAlertDocumentLayout.m9590$$Nest$fgetlistAdapter(this.this$0)));
        L5:
            Utilities.searchQueue.postRunnable(new ChatAttachAlertDocumentLayout$SearchAdapter$$ExternalSyntheticLambda1(this, r5, !this.currentSearchFilters.isEmpty(), r0));
        }

        private /* synthetic */ void lambda$searchGlobal$2(int r11, TLRPC.TL_error r12, TLObject r13, AccountInstance r14, boolean r15, String r16, ArrayList r17, long r18, long r20, ArrayList r22, ArrayList r23) {
            if (r11 == this.requestIndex) goto L5;
            return;
        L5:
            int r112 = 0;
            this.isLoading = false;
            ChatAttachAlertDocumentLayout r2 = this.this$0;
            if (r12 == null) goto L9;
            ChatAttachAlertDocumentLayout.m9586$$Nest$fgetemptyView(r2).title.setText(LocaleController.getString(R.string.SearchEmptyViewTitle2));
            ChatAttachAlertDocumentLayout.m9586$$Nest$fgetemptyView(this.this$0).subtitle.setVisibility(0);
            ChatAttachAlertDocumentLayout.m9586$$Nest$fgetemptyView(this.this$0).subtitle.setText(LocaleController.getString(R.string.SearchEmptyViewFilteredSubtitle2));
            ChatAttachAlertDocumentLayout.m9586$$Nest$fgetemptyView(this.this$0).showProgress(false, true);
            return;
        L9:
            ChatAttachAlertDocumentLayout.m9586$$Nest$fgetemptyView(r2).showProgress(false);
            TLRPC.messages_Messages r122 = (TLRPC.messages_Messages) r13;
            this.nextSearchRate = r122.next_rate;
            r14.getMessagesStorage().putUsersAndChats(r122.users, r122.chats, true, true);
            r14.getMessagesController().putUsers(r122.users, false);
            r14.getMessagesController().putChats(r122.chats, false);
            if (r15 == true) goto L12;
            this.messages.clear();
            this.messagesById.clear();
            this.sections.clear();
            this.sectionArrays.clear();
        L12:
            int r123 = r122.count;
            this.currentDataQuery = r16;
            int r24 = r17.size();
            int r4 = 0;
        L13:
            if (r4 >= r24) goto L19;
            MessageObject r6 = (MessageObject) r17.get(r4);
            ArrayList<MessageObject> r7 = this.sectionArrays.get(r6.monthKey);
            if (r7 != null) goto L17;
            r7 = new ArrayList();
            this.sectionArrays.put(r6.monthKey, r7);
            this.sections.add(r6.monthKey);
        L17:
            r7.add(r6);
            this.messages.add(r6);
            this.messagesById.put(r6.getId(), r6);
            r4 = r4 + 1;
            goto L13
        L19:
            if (this.messages.size() <= r123) goto L22;
            r123 = this.messages.size();
        L22:
            if (this.messages.size() < r123) goto L24;
            boolean r124 = true;
        L25:
            this.endReached = r124;
            if (this.messages.isEmpty() == true) goto L28;
        L35:
            if (r15 == true) goto L56;
            this.localTipChats.clear();
            if (r22 == null) goto L40;
            this.localTipChats.addAll(r22);
        L40:
            if (r16.length() >= 3) goto L42;
        L55:
            this.localTipDates.clear();
            this.localTipDates.addAll(r23);
            updateFiltersView(TextUtils.isEmpty(this.currentDataQuery), this.localTipChats, this.localTipDates, true);
            goto L56
        L42:
            if (LocaleController.getString(R.string.SavedMessages).toLowerCase().startsWith(r16) == false) goto L44;
        L45:
            int r125 = 0;
        L46:
            int r0 = this.localTipChats.size();
            ArrayList<Object> r1 = this.localTipChats;
            if (r125 >= r0) goto L54;
            if ((r1.get(r125) instanceof TLRPC.User) == false) goto L53;
            if (UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser().id == ((TLRPC.User) this.localTipChats.get(r125)).id) goto L55;
        L53:
            r125 = r125 + 1;
            goto L46
        L54:
            r1.add(0, UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser());
            goto L55
        L44:
            if ("saved messages".startsWith(r16) == false) goto L55;
        L56:
            this.firstLoading = false;
            View r126 = null;
            int r02 = -1;
        L57:
            if (r112 >= r24) goto L62;
            View r19 = ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$0).getChildAt(r112);
            if ((r19 instanceof FlickerLoadingView) == false) goto L61;
            r02 = ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$0).getChildAdapterPosition(r19);
            r126 = r19;
        L61:
            r112 = r112 + 1;
            goto L57
        L62:
            if (r126 == null) goto L65;
            ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$0).removeView(r126);
        L65:
            if (ChatAttachAlertDocumentLayout.m9592$$Nest$fgetloadingView(this.this$0).getVisibility() == 0) goto L67;
        L68:
            if (r126 == null) goto L70;
        L69:
            this.this$0.getViewTreeObserver().addOnPreDrawListener(new AnonymousClass3(this, r126, r02));
        L70:
            notifyDataSetChanged();
            return;
        L67:
            if (ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$0).getChildCount() <= 1) goto L69;
        L28:
            if (TextUtils.isEmpty(this.currentDataQuery) == true) goto L30;
        L34:
            ChatAttachAlertDocumentLayout.m9586$$Nest$fgetemptyView(this.this$0).title.setText(LocaleController.getString(R.string.SearchEmptyViewTitle2));
            ChatAttachAlertDocumentLayout.m9586$$Nest$fgetemptyView(this.this$0).subtitle.setVisibility(0);
            ChatAttachAlertDocumentLayout.m9586$$Nest$fgetemptyView(this.this$0).subtitle.setText(LocaleController.getString(R.string.SearchEmptyViewFilteredSubtitle2));
            goto L35
        L30:
            if (r18 != 0) goto L34;
            if (r20 != 0) goto L34;
            ChatAttachAlertDocumentLayout.m9586$$Nest$fgetemptyView(this.this$0).title.setText(LocaleController.getString(R.string.SearchEmptyViewTitle));
            ChatAttachAlertDocumentLayout.m9586$$Nest$fgetemptyView(this.this$0).subtitle.setVisibility(0);
            ChatAttachAlertDocumentLayout.m9586$$Nest$fgetemptyView(this.this$0).subtitle.setText(LocaleController.getString(R.string.SearchEmptyViewFilteredSubtitleFiles));
            goto L35
        L24:
            r124 = false;
            goto L25
        }

        private /* synthetic */ void lambda$searchGlobal$3(AccountInstance r16, String r17, int r18, boolean r19, long r20, long r22, ArrayList r24, ArrayList r25, TLObject r26, TLRPC.TL_error r27) {
            ArrayList r8 = new ArrayList();
            if (r27 != null) goto L7;
            TLRPC.messages_Messages r0 = (TLRPC.messages_Messages) r26;
            int r1 = r0.messages.size();
            int r3 = 0;
        L5:
            if (r3 >= r1) goto L7;
            MessageObject r4 = new MessageObject(r16.getCurrentAccount(), r0.messages.get(r3), false, true);
            r4.setQuery(r17);
            r8.add(r4);
            r3 = r3 + 1;
        L7:
            AndroidUtilities.runOnUIThread(new ChatAttachAlertDocumentLayout$SearchAdapter$$ExternalSyntheticLambda5(this, r18, r27, r26, r16, r19, r17, r8, r20, r22, r24, r25));
        }

        /* JADX WARN: Multi-variable type inference failed */
        private /* synthetic */ void lambda$searchGlobal$4(long r19, String r21, AccountInstance r22, long r23, long r25, boolean r27, String r28, int r29) {
            ArrayList<Object> r8 = null;
            if (r19 == 0) goto L19;
            TLRPC.TL_messages_search r0 = new TLRPC.TL_messages_search();
            r0.q = r21;
            r0.limit = 20;
            r0.filter = this.currentSearchFilter.filter;
            r0.peer = r22.getMessagesController().getInputPeer(r19);
            if (r23 <= 0) goto L8;
            r0.min_date = (int) (r23 / 1000);
        L8:
            if (r25 <= 0) goto L10;
            r0.max_date = (int) (r25 / 1000);
        L10:
            if (r27 == true) goto L12;
        L16:
            r0.offset_id = 0;
            TLRPC.TL_messages_searchGlobal r02 = r0;
        L17:
            TLRPC.TL_messages_searchGlobal r12 = r02;
            this.lastMessagesSearchString = r21;
            this.lastSearchFilterQueryString = r28;
            ArrayList r11 = new ArrayList();
            FiltersView.fillTipDates(this.lastMessagesSearchString, r11);
            r22.getConnectionsManager().sendRequest(r12, new ChatAttachAlertDocumentLayout$SearchAdapter$$ExternalSyntheticLambda3(this, r22, r21, r29, r27, r19, r23, r8, r11));
            return;
        L12:
            if (r21.equals(this.lastMessagesSearchString) == false) goto L16;
            if (this.messages.isEmpty() == true) goto L16;
            r0.offset_id = this.messages.get(r2.size() - 1).getId();
            r02 = r0;
            goto L17
        L19:
            if (TextUtils.isEmpty(r21) == true) goto L21;
            ArrayList<Object> r112 = new ArrayList();
            r22.getMessagesStorage().localSearch(0, r21, r112, new ArrayList(), new ArrayList(), null, -1);
            r8 = r112;
        L21:
            TLRPC.TL_messages_searchGlobal r03 = new TLRPC.TL_messages_searchGlobal();
            r03.limit = 20;
            r03.q = r21;
            r03.filter = this.currentSearchFilter.filter;
            if (r23 <= 0) goto L25;
            r03.min_date = (int) (r23 / 1000);
        L25:
            if (r25 <= 0) goto L27;
            r03.max_date = (int) (r25 / 1000);
        L27:
            if (r27 == true) goto L29;
        L40:
            r03.offset_rate = 0;
            r03.offset_id = 0;
            r03.offset_peer = new TLRPC.TL_inputPeerEmpty();
            r02 = r03;
            goto L17
        L29:
            if (r21.equals(this.lastMessagesSearchString) == false) goto L40;
            if (this.messages.isEmpty() == true) goto L40;
            MessageObject r4 = this.messages.get(r4.size() - 1);
            r03.offset_id = r4.getId();
            r03.offset_rate = this.nextSearchRate;
            TLRPC.Peer r42 = r4.messageOwner.peer_id;
            long r113 = r42.channel_id;
            if (r113 == 0) goto L35;
        L34:
            long r2 = -r113;
        L39:
            r03.offset_peer = r22.getMessagesController().getInputPeer(r2);
            r02 = r03;
            goto L17
        L35:
            r113 = r42.chat_id;
            if (r113 != 0) goto L34;
            r2 = r42.user_id;
            goto L39
        }

        private /* synthetic */ void lambda$updateSearchResults$5(ArrayList r3) {
            if (ChatAttachAlertDocumentLayout.m9596$$Nest$fgetsearching(this.this$0) == true) goto L5;
        L7:
            this.searchResult = r3;
            notifyDataSetChanged();
            return;
        L5:
            if (ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$0).getAdapter() == ChatAttachAlertDocumentLayout.m9594$$Nest$fgetsearchAdapter(this.this$0)) goto L7;
            ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$0).setAdapter(ChatAttachAlertDocumentLayout.m9594$$Nest$fgetsearchAdapter(this.this$0));
            goto L7
        }

        private void searchGlobal(long r17, long r19, long r21, FiltersView.MediaFilterData r23, String r24, boolean r25) {
            String r11 = String.format(Locale.ENGLISH, "%d%d%d%d%s", new Object[]{Long.valueOf(r17), Long.valueOf(r19), Long.valueOf(r21), Integer.valueOf(r23.filterType), r24});
            String r4 = this.lastSearchFilterQueryString;
            if (r4 != null) goto L5;
        L7:
            boolean r42 = false;
        L8:
            if (r42 == true) goto L11;
            if (r25 == false) goto L11;
            boolean r12 = true;
        L13:
            if (r17 == this.currentSearchDialogId) goto L15;
        L17:
            this.currentSearchFilter = r23;
            this.currentSearchDialogId = r17;
            this.currentSearchMinDate = r19;
            this.currentSearchMaxDate = r21;
            Runnable r0 = this.searchRunnable;
            if (r0 == null) goto L20;
            AndroidUtilities.cancelRunOnUIThread(r0);
        L20:
            AndroidUtilities.cancelRunOnUIThread(this.clearCurrentResultsRunnable);
            if (r42 == false) goto L24;
            if (r25 == false) goto L24;
            return;
        L24:
            if (r12 == false) goto L29;
            this.messages.clear();
            this.sections.clear();
            this.sectionArrays.clear();
            this.isLoading = true;
            ChatAttachAlertDocumentLayout.m9586$$Nest$fgetemptyView(this.this$0).setVisibility(0);
            notifyDataSetChanged();
            this.requestIndex++;
            this.firstLoading = true;
            if (ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$0).getPinnedHeader() == null) goto L28;
            ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$0).getPinnedHeader().setAlpha(0.0f);
        L28:
            this.localTipChats.clear();
            this.localTipDates.clear();
        L29:
            this.isLoading = true;
            notifyDataSetChanged();
            if (r42 == true) goto L33;
            this.clearCurrentResultsRunnable.run();
            ChatAttachAlertDocumentLayout.m9586$$Nest$fgetemptyView(this.this$0).showProgress(true, !r25);
        L33:
            if (TextUtils.isEmpty(r24) == false) goto L36;
            this.localTipDates.clear();
            this.localTipChats.clear();
            updateFiltersView(false, null, null, true);
            return;
        L36:
            int r122 = this.requestIndex + 1;
            this.requestIndex = r122;
            boolean r10 = r42;
            ChatAttachAlertDocumentLayout$SearchAdapter$$ExternalSyntheticLambda2 r02 = new ChatAttachAlertDocumentLayout$SearchAdapter$$ExternalSyntheticLambda2(this, r17, r24, AccountInstance.getInstance(UserConfig.selectedAccount), r19, r21, r10, r11, r122);
            this.searchRunnable = r02;
            if (r10 == true) goto L39;
        L41:
            long r2 = 350;
        L42:
            AndroidUtilities.runOnUIThread(r02, r2);
            ChatAttachAlertDocumentLayout.m9592$$Nest$fgetloadingView(this.this$0).setViewType(3);
            return;
        L39:
            if (this.messages.isEmpty() == true) goto L41;
            r2 = 0;
            goto L42
        L15:
            if (this.currentSearchMinDate != r19) goto L17;
            int r14 = (this.currentSearchMaxDate > r21 ? 1 : (this.currentSearchMaxDate == r21 ? 0 : -1));
        L11:
            r12 = false;
            goto L13
        L5:
            if (r4.equals(r11) == false) goto L7;
            r42 = true;
            goto L8
        }

        private void updateFiltersView(boolean r10, ArrayList<Object> r11, ArrayList<FiltersView.DateData> r12, boolean r13) {
            int r0 = 0;
            int r1 = 0;
            boolean r2 = false;
            boolean r3 = false;
            boolean r4 = false;
        L4:
            if (r1 >= this.currentSearchFilters.size()) goto L15;
            if (this.currentSearchFilters.get(r1).isMedia() == false) goto L9;
            r2 = true;
        L14:
            r1 = r1 + 1;
            goto L4
        L9:
            if (this.currentSearchFilters.get(r1).filterType != 4) goto L12;
            r3 = true;
            goto L14
        L12:
            if (this.currentSearchFilters.get(r1).filterType != 6) goto L14;
            r4 = true;
            goto L14
        L15:
            if (r11 != null) goto L17;
        L18:
            if (r12 != null) goto L20;
        L22:
            boolean r14 = false;
        L23:
            Integer r5 = null;
            if (r2 == true) goto L28;
            if (r14 == true) goto L28;
            if (r10 == false) goto L28;
        L44:
            boolean r102 = false;
        L45:
            if (r102 == true) goto L47;
            ChatAttachAlertDocumentLayout.m9587$$Nest$fgetfiltersView(this.this$0).setUsersAndDates(null, null, false);
        L47:
            ChatAttachAlertDocumentLayout.m9587$$Nest$fgetfiltersView(this.this$0).setEnabled(r102);
            if (r102 == true) goto L50;
        L51:
            if (r102 == false) goto L53;
        L55:
            FiltersView r112 = ChatAttachAlertDocumentLayout.m9587$$Nest$fgetfiltersView(this.this$0);
            if (r102 == false) goto L58;
            r5 = 1;
        L58:
            r112.setTag(r5);
            if (ChatAttachAlertDocumentLayout.m9588$$Nest$fgetfiltersViewAnimator(this.this$0) == null) goto L61;
            ChatAttachAlertDocumentLayout.m9588$$Nest$fgetfiltersViewAnimator(this.this$0).cancel();
        L61:
            float r113 = 0.0f;
            if (r13 == false) goto L82;
            if (r102 == false) goto L65;
            ChatAttachAlertDocumentLayout.m9587$$Nest$fgetfiltersView(this.this$0).setVisibility(0);
        L65:
            ChatAttachAlertDocumentLayout.m9602$$Nest$fputfiltersViewAnimator(this.this$0, new AnimatorSet());
            AnimatorSet r132 = ChatAttachAlertDocumentLayout.m9588$$Nest$fgetfiltersViewAnimator(this.this$0);
            RecyclerListView r15 = ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$0);
            Property r22 = View.TRANSLATION_Y;
            if (r102 == false) goto L68;
            float r32 = AndroidUtilities.dp(44.0f);
        L69:
            ObjectAnimator r16 = ObjectAnimator.ofFloat(r15, r22, new float[]{r32});
            FiltersView r33 = ChatAttachAlertDocumentLayout.m9587$$Nest$fgetfiltersView(this.this$0);
            if (r102 == false) goto L72;
            float r42 = 0.0f;
        L73:
            ObjectAnimator r34 = ObjectAnimator.ofFloat(r33, r22, new float[]{r42});
            FlickerLoadingView r43 = ChatAttachAlertDocumentLayout.m9592$$Nest$fgetloadingView(this.this$0);
            if (r102 == false) goto L76;
            float r52 = AndroidUtilities.dp(44.0f);
        L77:
            ObjectAnimator r44 = ObjectAnimator.ofFloat(r43, r22, new float[]{r52});
            StickerEmptyView r53 = ChatAttachAlertDocumentLayout.m9586$$Nest$fgetemptyView(this.this$0);
            if (r102 == false) goto L80;
            r113 = AndroidUtilities.dp(44.0f);
        L80:
            r132.playTogether(new Animator[]{r16, r34, r44, ObjectAnimator.ofFloat(r53, r22, new float[]{r113})});
            ChatAttachAlertDocumentLayout.m9588$$Nest$fgetfiltersViewAnimator(this.this$0).addListener(new AnonymousClass2(this));
            ChatAttachAlertDocumentLayout.m9588$$Nest$fgetfiltersViewAnimator(this.this$0).setInterpolator(CubicBezierInterpolator.EASE_OUT);
            ChatAttachAlertDocumentLayout.m9588$$Nest$fgetfiltersViewAnimator(this.this$0).setDuration(180);
            ChatAttachAlertDocumentLayout.m9588$$Nest$fgetfiltersViewAnimator(this.this$0).start();
            return;
        L76:
            r52 = 0.0f;
            goto L77
        L72:
            r42 = -AndroidUtilities.dp(44.0f);
            goto L73
        L68:
            r32 = 0.0f;
            goto L69
        L82:
            ChatAttachAlertDocumentLayout.m9587$$Nest$fgetfiltersView(this.this$0).getAdapter().notifyDataSetChanged();
            RecyclerListView r133 = ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$0);
            if (r102 == false) goto L85;
            float r17 = AndroidUtilities.dp(44.0f);
        L86:
            r133.setTranslationY(r17);
            FiltersView r134 = ChatAttachAlertDocumentLayout.m9587$$Nest$fgetfiltersView(this.this$0);
            if (r102 == false) goto L89;
            float r18 = 0.0f;
        L90:
            r134.setTranslationY(r18);
            FlickerLoadingView r135 = ChatAttachAlertDocumentLayout.m9592$$Nest$fgetloadingView(this.this$0);
            if (r102 == false) goto L93;
            float r19 = AndroidUtilities.dp(44.0f);
        L94:
            r135.setTranslationY(r19);
            StickerEmptyView r136 = ChatAttachAlertDocumentLayout.m9586$$Nest$fgetemptyView(this.this$0);
            if (r102 == false) goto L97;
            r113 = AndroidUtilities.dp(44.0f);
        L97:
            r136.setTranslationY(r113);
            FiltersView r9 = ChatAttachAlertDocumentLayout.m9587$$Nest$fgetfiltersView(this.this$0);
            if (r102 == true) goto L101;
            r0 = 4;
        L101:
            r9.setVisibility(r0);
            return;
        L93:
            r19 = 0.0f;
            goto L94
        L89:
            r18 = -AndroidUtilities.dp(44.0f);
            goto L90
        L85:
            r17 = 0.0f;
            goto L86
        L53:
            if (ChatAttachAlertDocumentLayout.m9587$$Nest$fgetfiltersView(this.this$0).getTag() != null) goto L55;
            return;
        L50:
            if (ChatAttachAlertDocumentLayout.m9587$$Nest$fgetfiltersView(this.this$0).getTag() == null) goto L51;
            return;
        L28:
            if (r14 == false) goto L44;
            if (r11 != null) goto L31;
        L34:
            r11 = null;
        L35:
            if (r12 != null) goto L37;
        L40:
            r12 = null;
        L41:
            if (r11 != null) goto L43;
            if (r12 == null) goto L44;
        L43:
            ChatAttachAlertDocumentLayout.m9587$$Nest$fgetfiltersView(this.this$0).setUsersAndDates(r11, r12, false);
            r102 = true;
            goto L45
        L37:
            if (r12.isEmpty() == true) goto L40;
            if (r4 == true) goto L40;
        L31:
            if (r11.isEmpty() == true) goto L34;
            if (r3 == true) goto L34;
        L20:
            if (r12.isEmpty() == true) goto L22;
        L21:
            r14 = true;
            goto L23
        L17:
            if (r11.isEmpty() == false) goto L21;
            goto L18
        }

        private void updateSearchResults(ArrayList<ListItem> r1, String r2) {
            AndroidUtilities.runOnUIThread(new ChatAttachAlertDocumentLayout$SearchAdapter$$ExternalSyntheticLambda4(this, r1));
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public int getCountForSection(int r4) {
            if (r4 == 0) goto L4;
            int r42 = r4 - 1;
            int r1 = 1;
            if (r42 >= this.sections.size()) goto L17;
            ArrayList<MessageObject> r0 = this.sectionArrays.get(this.sections.get(r42));
            if (r0 == null) goto L16;
            int r02 = r0.size();
            if (r42 != 0) goto L15;
            if (this.searchResult.isEmpty() == false) goto L15;
            r1 = 0;
        L15:
            return r02 + r1;
        L16:
            return 0;
        L17:
            return 1;
        L4:
            return this.searchResult.size();
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public Object getItem(int r3, int r4) {
            if (r3 == 0) goto L4;
            int r32 = r3 - 1;
            if (r32 >= this.sections.size()) goto L24;
            ArrayList<MessageObject> r0 = this.sectionArrays.get(this.sections.get(r32));
            if (r0 == null) goto L25;
            if (r32 == 0) goto L13;
        L15:
            int r2 = 1;
        L16:
            int r42 = r4 - r2;
            if (r42 >= 0) goto L19;
            return null;
        L19:
            if (r42 < r0.size()) goto L21;
            return null;
        L21:
            return r0.get(r42);
        L13:
            if (this.searchResult.isEmpty() == false) goto L15;
            r2 = 0;
            goto L16
        L25:
            return null;
        L24:
            return null;
        L4:
            if (r4 < this.searchResult.size()) goto L6;
            return null;
        L6:
            return this.searchResult.get(r4);
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public int getItemViewType(int r3, int r4) {
            if (r3 != 0) goto L6;
            return 1;
        L6:
            if (r3 != (getSectionCount() - 1)) goto L9;
            return 3;
        L9:
            int r32 = r3 - 1;
            if (r32 >= this.sections.size()) goto L19;
            if (r32 == 0) goto L13;
        L14:
            if (r4 != 0) goto L21;
            return 0;
        L21:
            return 4;
        L13:
            if (this.searchResult.isEmpty() == false) goto L14;
            return 4;
        L19:
            return 2;
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
            if (this.sections.isEmpty() == false) goto L5;
            return 2;
        L5:
            return (this.sections.size() + (!this.endReached ? 1 : 0)) + 2;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public View getSectionHeaderView(int r4, View r5) {
            GraySectionCell r0 = (GraySectionCell) r5;
            if (r0 != null) goto L5;
            r0 = new GraySectionCell(this.mContext, this.this$0.resourcesProvider);
            r0.setBackgroundColor(this.this$0.getThemedColor(Theme.key_graySection) & (-218103809));
        L5:
            if (r4 != 0) goto L7;
        L23:
            r0.setAlpha(0.0f);
            return r0;
        L7:
            if (r4 == 1) goto L9;
        L11:
            int r42 = r4 - 1;
            if (r42 >= this.sections.size()) goto L22;
            r0.setAlpha(1.0f);
            ArrayList<MessageObject> r1 = this.sectionArrays.get(this.sections.get(r42));
            if (r1 == null) goto L22;
            MessageObject r12 = r1.get(0);
            if (r42 == 0) goto L18;
        L20:
            String r3 = LocaleController.formatSectionDate(r12.messageOwner.date);
        L21:
            r0.setText(r3);
            goto L22
        L18:
            if (this.searchResult.isEmpty() == true) goto L20;
            r3 = LocaleController.getString(R.string.GlobalSearch);
        L22:
            return r5;
        L9:
            if (this.searchResult.isEmpty() == false) goto L11;
            goto L11
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1, int r2, int r3) {
            int r0 = r1.getItemViewType();
            if (r0 != 1) goto L5;
        L9:
            return true;
        L5:
            if (r0 == 4) goto L9;
            return false;
        }

        public void loadMore() {
            if (ChatAttachAlertDocumentLayout.m9594$$Nest$fgetsearchAdapter(this.this$0).isLoading == false) goto L5;
            return;
        L5:
            if (ChatAttachAlertDocumentLayout.m9594$$Nest$fgetsearchAdapter(this.this$0).endReached == true) goto L12;
            FiltersView.MediaFilterData r8 = this.currentSearchFilter;
            if (r8 == null) goto L13;
            searchGlobal(this.currentSearchDialogId, this.currentSearchMinDate, this.currentSearchMaxDate, r8, this.lastMessagesSearchString, false);
            return;
        L13:
            return;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            ChatAttachAlertDocumentLayout.m9608$$Nest$mupdateEmptyView(this.this$0);
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public void onBindViewHolder(int r12, int r13, RecyclerView.ViewHolder r14) {
            int r0 = r14.getItemViewType();
            if (r0 != 2) goto L5;
            return;
        L5:
            if (r0 == 3) goto L60;
            boolean r1 = false;
            if (r0 != 0) goto L10;
            int r122 = r12 - 1;
            ArrayList<MessageObject> r132 = this.sectionArrays.get(this.sections.get(r122));
            if (r132 == null) goto L63;
            MessageObject r133 = r132.get(0);
            if (r122 == 0) goto L54;
        L56:
            String r11 = LocaleController.formatSectionDate(r133.messageOwner.date);
        L57:
            ((GraySectionCell) r14.itemView).setText(r11);
            return;
        L54:
            if (this.searchResult.isEmpty() == true) goto L56;
            r11 = LocaleController.getString(R.string.GlobalSearch);
            goto L57
        L63:
            return;
        L10:
            if (r0 == 1) goto L13;
            if (r0 == 4) goto L13;
            return;
        L13:
            SharedDocumentCell r02 = (SharedDocumentCell) r14.itemView;
            if (r12 != 0) goto L25;
            ListItem r123 = (ListItem) getItem(r13);
            SharedDocumentCell r4 = (SharedDocumentCell) r14.itemView;
            int r9 = r123.icon;
            if (r9 == 0) goto L18;
            r4.setTextAndValueAndTypeAndThumb(r123.title, r123.subtitle, null, null, r9, false);
        L19:
            File r134 = r123.file;
            ChatAttachAlertDocumentLayout r142 = this.this$0;
            if (r134 == null) goto L23;
            r4.setChecked(ChatAttachAlertDocumentLayout.m9597$$Nest$fgetselectedFiles(r142).containsKey(r123.file.toString()), !ChatAttachAlertDocumentLayout.m9593$$Nest$fgetscrolling(this.this$0));
            return;
        L23:
            r4.setChecked(false, !ChatAttachAlertDocumentLayout.m9593$$Nest$fgetscrolling(r142));
            return;
        L18:
            r4.setTextAndValueAndTypeAndThumb(r123.title, r123.subtitle, r123.ext.toUpperCase().substring(0, Math.min(r123.ext.length(), 4)), r123.thumb, 0, false);
            goto L19
        L25:
            int r124 = r12 - 1;
            if (r124 == 0) goto L28;
        L29:
            r13 = r13 - 1;
        L30:
            ArrayList<MessageObject> r143 = this.sectionArrays.get(this.sections.get(r124));
            if (r143 == null) goto L62;
            MessageObject r2 = r143.get(r13);
            if (r02.getMessage() != null) goto L36;
        L38:
            boolean r42 = false;
        L40:
            if (r13 == (r143.size() - 1)) goto L42;
        L45:
            r1 = true;
        L46:
            r02.setDocument(r2, r1);
            r02.getViewTreeObserver().addOnPreDrawListener(new AnonymousClass4(this, r02, r2, r42));
            return;
        L42:
            if (r124 != (this.sections.size() - 1)) goto L46;
            if (this.isLoading == false) goto L46;
        L36:
            if (r02.getMessage().getId() != r2.getId()) goto L38;
            r42 = true;
            goto L40
        L62:
            return;
        L28:
            if (this.searchResult.isEmpty() == true) goto L30;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r4, int r5) {
            if (r5 == 0) goto L14;
            int r42 = 2;
            if (r5 == 1) goto L10;
            if (r5 != 2) goto L7;
            FlickerLoadingView r43 = new FlickerLoadingView(this.mContext, this.this$0.resourcesProvider);
            r43.setViewType(3);
            r43.setIsSingleCell(true);
            View r44 = r43;
        L15:
            r44.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r44);
        L7:
            if (r5 == 4) goto L10;
            View r45 = new View(this.mContext);
            r45.setTag(-33024);
            r44 = r45;
        L10:
            Context r2 = this.mContext;
            if (r5 != 1) goto L13;
            r42 = 1;
        L13:
            SharedDocumentCell r1 = new SharedDocumentCell(r2, r42, this.this$0.resourcesProvider);
            r1.setDrawDownloadIcon(false);
            r44 = r1;
            goto L15
        L14:
            r44 = new GraySectionCell(this.mContext, this.this$0.resourcesProvider);
            goto L15
        }

        public void removeSearchFilter(FiltersView.MediaFilterData r1) {
            this.currentSearchFilters.remove(r1);
        }

        public void search(String r14, boolean r15) {
            Runnable r0 = this.localSearchRunnable;
            if (r0 == null) goto L6;
            AndroidUtilities.cancelRunOnUIThread(r0);
            this.localSearchRunnable = null;
        L6:
            if (TextUtils.isEmpty(r14) == true) goto L8;
            ChatAttachAlertDocumentLayout$SearchAdapter$$ExternalSyntheticLambda0 r02 = new ChatAttachAlertDocumentLayout$SearchAdapter$$ExternalSyntheticLambda0(this, r14);
            this.localSearchRunnable = r02;
            AndroidUtilities.runOnUIThread(r02, 300);
        L16:
            if (ChatAttachAlertDocumentLayout.m9582$$Nest$fgetcanSelectOnlyImageFiles(this.this$0) == false) goto L18;
            return;
        L18:
            if (ListAdapter.m9611$$Nest$fgethistory(ChatAttachAlertDocumentLayout.m9590$$Nest$fgetlistAdapter(this.this$0)).isEmpty() == false) goto L43;
            int r2 = 0;
            long r4 = 0;
            long r6 = 0;
            long r8 = 0;
        L21:
            if (r2 >= this.currentSearchFilters.size()) goto L35;
            FiltersView.MediaFilterData r03 = this.currentSearchFilters.get(r2);
            int r1 = r03.filterType;
            if (r1 != 4) goto L32;
            TLObject r04 = r03.chat;
            if ((r04 instanceof TLRPC.User) == false) goto L29;
            long r05 = ((TLRPC.User) r04).id;
        L27:
            r4 = r05;
        L34:
            r2 = r2 + 1;
            goto L21
        L29:
            if ((r04 instanceof TLRPC.Chat) == false) goto L34;
            r05 = -((TLRPC.Chat) r04).id;
            goto L27
        L32:
            if (r1 != 6) goto L34;
            FiltersView.DateData r06 = r03.dateData;
            r6 = r06.minDate;
            r8 = r06.maxDate;
            goto L34
        L35:
            searchGlobal(r4, r6, r8, FiltersView.filters[2], r14, r15);
            return;
        L43:
            return;
        L8:
            if (this.searchResult.isEmpty() == true) goto L11;
            this.searchResult.clear();
        L11:
            if (ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$0).getAdapter() == ChatAttachAlertDocumentLayout.m9590$$Nest$fgetlistAdapter(this.this$0)) goto L13;
            ChatAttachAlertDocumentLayout.m9591$$Nest$fgetlistView(this.this$0).setAdapter(ChatAttachAlertDocumentLayout.m9590$$Nest$fgetlistAdapter(this.this$0));
        L13:
            notifyDataSetChanged();
            goto L16
        }
    }

    public static /* synthetic */ boolean $r8$lambda$5JuEfiuSvV_VVHfc5tcygldmsuM(ChatAttachAlertDocumentLayout r0, View r1, int r2) {
        return r0.lambda$new$2(r1, r2);
    }

    public static /* synthetic */ int $r8$lambda$C6T4dVLb6vSoYf98TMimEhGvEbU(ChatAttachAlertDocumentLayout r0, ListItem r1, ListItem r2) {
        return r0.lambda$sortFileItems$8(r1, r2);
    }

    /* renamed from: $r8$lambda$J6_v-B34CW3v7HZ5M2hUKo2fan4, reason: not valid java name */
    public static /* synthetic */ void m9577$r8$lambda$J6_vB34CW3v7HZ5M2hUKo2fan4(ChatAttachAlertDocumentLayout r0, ArrayList r1, String r2, ArrayList r3, ArrayList r4, boolean r5, int r6, long r7, boolean r9, Long r10) {
        r0.lambda$sendSelectedItems$5(r1, r2, r3, r4, r5, r6, r7, r9, r10);
    }

    /* renamed from: $r8$lambda$SRLW8HZ-Mc7N5NDtbESRC7PL1uw, reason: not valid java name */
    public static /* synthetic */ void m9578$r8$lambda$SRLW8HZMc7N5NDtbESRC7PL1uw(ChatAttachAlertDocumentLayout r0, View r1, int r2) {
        r0.lambda$new$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$h7ZVj64TX_WHJ0kgPZp_4_k6WDI(ChatAttachAlertDocumentLayout r0, ArrayList r1, boolean r2, int r3, Long r4) {
        r0.lambda$sendSelectedPhotos$6(r1, r2, r3, r4);
    }

    public static /* synthetic */ int $r8$lambda$mmekL1GX7ZvJE6Y6MYdIZg3MlXs(ChatAttachAlertDocumentLayout r0, ListItem r1, ListItem r2) {
        return r0.lambda$sortRecentItems$7(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$qP60ZdA4oSS_tOn_64X7emq6heE(View r0, MotionEvent r1) {
        return true;
    }

    /* renamed from: $r8$lambda$qZppm7wYMORpX7cvhERmI3j-nlE, reason: not valid java name */
    public static /* synthetic */ void m9579$r8$lambda$qZppm7wYMORpX7cvhERmI3jnlE(ChatAttachAlertDocumentLayout r0, View r1, int r2) {
        r0.lambda$new$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$vJ_Mpm1J3f6H2ZC9KrlyBhYaWTA(ChatAttachAlertDocumentLayout r0, int r1, float r2, ValueAnimator r3) {
        r0.lambda$runAnimation$4(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetadditionalTranslationY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m9580$$Nest$fgetadditionalTranslationY(ChatAttachAlertDocumentLayout r0) {
        return r0.additionalTranslationY;
    }

    /* renamed from: -$$Nest$fgetbackgroundListView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m9581$$Nest$fgetbackgroundListView(ChatAttachAlertDocumentLayout r0) {
        return r0.backgroundListView;
    }

    /* renamed from: -$$Nest$fgetcanSelectOnlyImageFiles, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9582$$Nest$fgetcanSelectOnlyImageFiles(ChatAttachAlertDocumentLayout r0) {
        return r0.canSelectOnlyImageFiles;
    }

    /* renamed from: -$$Nest$fgetcurrentAnimationType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9583$$Nest$fgetcurrentAnimationType(ChatAttachAlertDocumentLayout r0) {
        return r0.currentAnimationType;
    }

    /* renamed from: -$$Nest$fgetcurrentDir, reason: not valid java name */
    public static /* bridge */ /* synthetic */ File m9584$$Nest$fgetcurrentDir(ChatAttachAlertDocumentLayout r0) {
        return r0.currentDir;
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DocumentSelectActivityDelegate m9585$$Nest$fgetdelegate(ChatAttachAlertDocumentLayout r0) {
        return r0.delegate;
    }

    /* renamed from: -$$Nest$fgetemptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StickerEmptyView m9586$$Nest$fgetemptyView(ChatAttachAlertDocumentLayout r0) {
        return r0.emptyView;
    }

    /* renamed from: -$$Nest$fgetfiltersView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FiltersView m9587$$Nest$fgetfiltersView(ChatAttachAlertDocumentLayout r0) {
        return r0.filtersView;
    }

    /* renamed from: -$$Nest$fgetfiltersViewAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m9588$$Nest$fgetfiltersViewAnimator(ChatAttachAlertDocumentLayout r0) {
        return r0.filtersViewAnimator;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayoutManager m9589$$Nest$fgetlayoutManager(ChatAttachAlertDocumentLayout r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetlistAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ListAdapter m9590$$Nest$fgetlistAdapter(ChatAttachAlertDocumentLayout r0) {
        return r0.listAdapter;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m9591$$Nest$fgetlistView(ChatAttachAlertDocumentLayout r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetloadingView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FlickerLoadingView m9592$$Nest$fgetloadingView(ChatAttachAlertDocumentLayout r0) {
        return r0.loadingView;
    }

    /* renamed from: -$$Nest$fgetscrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9593$$Nest$fgetscrolling(ChatAttachAlertDocumentLayout r0) {
        return r0.scrolling;
    }

    /* renamed from: -$$Nest$fgetsearchAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SearchAdapter m9594$$Nest$fgetsearchAdapter(ChatAttachAlertDocumentLayout r0) {
        return r0.searchAdapter;
    }

    /* renamed from: -$$Nest$fgetsearchItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m9595$$Nest$fgetsearchItem(ChatAttachAlertDocumentLayout r0) {
        return r0.searchItem;
    }

    /* renamed from: -$$Nest$fgetsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9596$$Nest$fgetsearching(ChatAttachAlertDocumentLayout r0) {
        return r0.searching;
    }

    /* renamed from: -$$Nest$fgetselectedFiles, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m9597$$Nest$fgetselectedFiles(ChatAttachAlertDocumentLayout r0) {
        return r0.selectedFiles;
    }

    /* renamed from: -$$Nest$fgetselectedMessages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m9598$$Nest$fgetselectedMessages(ChatAttachAlertDocumentLayout r0) {
        return r0.selectedMessages;
    }

    /* renamed from: -$$Nest$fgetsortByName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9599$$Nest$fgetsortByName(ChatAttachAlertDocumentLayout r0) {
        return r0.sortByName;
    }

    /* renamed from: -$$Nest$fgetsortItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m9600$$Nest$fgetsortItem(ChatAttachAlertDocumentLayout r0) {
        return r0.sortItem;
    }

    /* renamed from: -$$Nest$fputcurrentAnimationType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9601$$Nest$fputcurrentAnimationType(ChatAttachAlertDocumentLayout r0, int r1) {
        r0.currentAnimationType = r1;
    }

    /* renamed from: -$$Nest$fputfiltersViewAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9602$$Nest$fputfiltersViewAnimator(ChatAttachAlertDocumentLayout r0, AnimatorSet r1) {
        r0.filtersViewAnimator = r1;
    }

    /* renamed from: -$$Nest$fputscrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9603$$Nest$fputscrolling(ChatAttachAlertDocumentLayout r0, boolean r1) {
        r0.scrolling = r1;
    }

    /* renamed from: -$$Nest$fputsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9604$$Nest$fputsearching(ChatAttachAlertDocumentLayout r0, boolean r1) {
        r0.searching = r1;
    }

    /* renamed from: -$$Nest$mlistFiles, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m9605$$Nest$mlistFiles(ChatAttachAlertDocumentLayout r0, File r1) {
        return r0.listFiles(r1);
    }

    /* renamed from: -$$Nest$mlistRoots, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9606$$Nest$mlistRoots(ChatAttachAlertDocumentLayout r0) {
        r0.listRoots();
    }

    /* renamed from: -$$Nest$msendSelectedPhotos, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9607$$Nest$msendSelectedPhotos(ChatAttachAlertDocumentLayout r0, HashMap r1, ArrayList r2, boolean r3, int r4) {
        r0.sendSelectedPhotos(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$mupdateEmptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9608$$Nest$mupdateEmptyView(ChatAttachAlertDocumentLayout r0) {
        r0.updateEmptyView();
    }

    /* renamed from: -$$Nest$mupdateEmptyViewPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9609$$Nest$mupdateEmptyViewPosition(ChatAttachAlertDocumentLayout r0) {
        r0.updateEmptyViewPosition();
    }

    /* renamed from: -$$Nest$mupdateSearchButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m9610$$Nest$mupdateSearchButton(ChatAttachAlertDocumentLayout r0) {
        r0.updateSearchButton();
    }

    public ChatAttachAlertDocumentLayout(ChatAttachAlert r18, Context r19, int r20, Theme.ResourcesProvider r21) {
        super(r18, r19, r21);
        this.receiverRegistered = false;
        this.selectedFiles = new HashMap();
        this.selectedFilesOrder = new ArrayList();
        this.selectedMessages = new HashMap();
        this.maxSelectedFiles = -1;
        this.receiver = new AnonymousClass1(this);
        this.listAdapter = new ListAdapter(this, r19);
        if (r20 != 1) goto L5;
        boolean r3 = true;
    L6:
        this.allowMusic = r3;
        if (r20 != 2) goto L9;
        boolean r0 = true;
    L10:
        this.isSoundPicker = r0;
        this.sortByName = SharedConfig.sortFilesByName;
        loadRecentFiles();
        this.searching = false;
        if (this.receiverRegistered == true) goto L16;
        this.receiverRegistered = true;
        IntentFilter r02 = new IntentFilter();
        r02.addAction("android.intent.action.MEDIA_BAD_REMOVAL");
        r02.addAction("android.intent.action.MEDIA_CHECKING");
        r02.addAction("android.intent.action.MEDIA_EJECT");
        r02.addAction("android.intent.action.MEDIA_MOUNTED");
        r02.addAction("android.intent.action.MEDIA_NOFS");
        r02.addAction("android.intent.action.MEDIA_REMOVED");
        r02.addAction("android.intent.action.MEDIA_SHARED");
        r02.addAction("android.intent.action.MEDIA_UNMOUNTABLE");
        r02.addAction("android.intent.action.MEDIA_UNMOUNTED");
        r02.addDataScheme("file");
        if (Build.VERSION.SDK_INT < 33) goto L15;
        ApplicationLoader.applicationContext.registerReceiver(this.receiver, r02, 4);
        goto L16
    L15:
        ApplicationLoader.applicationContext.registerReceiver(this.receiver, r02);
    L16:
        ActionBarMenu r03 = this.parentAlert.actionBar.createMenu();
        ActionBarMenuItem r32 = r03.addItem(0, R.drawable.outline_header_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass2(this));
        this.searchItem = r32;
        r32.setSearchFieldHint(LocaleController.getString(R.string.Search));
        this.searchItem.setContentDescription(LocaleController.getString(R.string.Search));
        EditTextBoldCursor r33 = this.searchItem.getSearchField();
        int r4 = Theme.key_dialogTextBlack;
        r33.setTextColor(getThemedColor(r4));
        r33.setCursorColor(getThemedColor(r4));
        r33.setHintTextColor(getThemedColor(Theme.key_chat_messagePanelHint));
        if (this.sortByName == false) goto L19;
        int r34 = R.drawable.msg_contacts_time;
    L20:
        ActionBarMenuItem r04 = r03.addItem(6, r34);
        this.sortItem = r04;
        r04.setContentDescription(LocaleController.getString(R.string.AccDescrContactSorting));
        FlickerLoadingView r05 = new FlickerLoadingView(r19, r21);
        this.loadingView = r05;
        addView(r05);
        AnonymousClass3 r06 = new AnonymousClass3(this, r19, this.loadingView, 1, r21);
        this.emptyView = r06;
        addView(r06, LayoutHelper.createFrame(-1, -1.0f));
        this.emptyView.setVisibility(8);
        this.emptyView.setOnTouchListener(new ChatAttachAlertDocumentLayout$$ExternalSyntheticLambda1());
        AnonymousClass4 r07 = new AnonymousClass4(this, r19, r21);
        this.backgroundListView = r07;
        r07.setSectionsType(2);
        this.backgroundListView.setVerticalScrollBarEnabled(false);
        RecyclerListView r15 = this.backgroundListView;
        FillLastLinearLayoutManager r08 = new FillLastLinearLayoutManager(r19, 1, false, AndroidUtilities.dp(56.0f), this.backgroundListView);
        this.backgroundLayoutManager = r08;
        r15.setLayoutManager(r08);
        this.backgroundListView.setClipToPadding(false);
        RecyclerListView r09 = this.backgroundListView;
        ListAdapter r1 = new ListAdapter(this, r19);
        this.backgroundListAdapter = r1;
        r09.setAdapter(r1);
        addView(this.backgroundListView, LayoutHelper.createFrame(-1, -1.0f));
        this.backgroundListView.setVisibility(8);
        AnonymousClass5 r010 = new AnonymousClass5(this, r19, r21);
        this.listView = r010;
        r010.setSections();
        RecyclerListView r011 = this.listView;
        this.iBlur3Capture = r011;
        this.iBlur3CaptureView = r011;
        this.occupyStatusBar = true;
        this.occupyNavigationBar = true;
        r011.setSectionsType(2);
        this.listView.setVerticalScrollBarEnabled(false);
        RecyclerListView r9 = this.listView;
        AnonymousClass6 r012 = new AnonymousClass6(this, r19, 1, false, AndroidUtilities.dp(56.0f) + AndroidUtilities.statusBarHeight, this.listView);
        this.layoutManager = r012;
        r9.setLayoutManager(r012);
        this.listView.setClipToPadding(false);
        this.listView.setAdapter(this.listAdapter);
        addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.searchAdapter = new SearchAdapter(this, r19);
        this.listView.setOnScrollListener(new AnonymousClass7(this));
        this.listView.setOnItemClickListener(new ChatAttachAlertDocumentLayout$$ExternalSyntheticLambda2(this));
        this.listView.setOnItemLongClickListener(new ChatAttachAlertDocumentLayout$$ExternalSyntheticLambda3(this));
        FiltersView r013 = new FiltersView(r19, r21);
        this.filtersView = r013;
        r013.setOnItemClickListener(new ChatAttachAlertDocumentLayout$$ExternalSyntheticLambda4(this));
        this.filtersView.setBackgroundColor(getThemedColor(Theme.key_dialogBackground));
        addView(this.filtersView, LayoutHelper.createFrame(-1, 44, 48));
        this.filtersView.setTranslationY(-AndroidUtilities.dp(44.0f));
        this.filtersView.setVisibility(4);
        listRoots();
        updateSearchButton();
        updateEmptyView();
        return;
    L19:
        r34 = R.drawable.msg_contacts_name;
        goto L20
    L9:
        r0 = false;
        goto L10
    L5:
        r3 = false;
        goto L6
    }

    private boolean canClosePicker() {
        if (ListAdapter.m9611$$Nest$fgethistory(this.listAdapter).size() <= 0) goto L10;
        prepareAnimation();
        HistoryEntry r0 = (HistoryEntry) ListAdapter.m9611$$Nest$fgethistory(this.listAdapter).remove(ListAdapter.m9611$$Nest$fgethistory(this.listAdapter).size() - 1);
        this.parentAlert.actionBar.setTitle(r0.title);
        int r1 = getTopForScroll();
        File r02 = r0.dir;
        if (r02 == null) goto L7;
        listFiles(r02);
    L8:
        updateSearchButton();
        this.layoutManager.scrollToPositionWithOffset(0, r1);
        runAnimation(2);
        return false;
    L7:
        listRoots();
        goto L8
    L10:
        return true;
    }

    private void checkDirectory(File r9) {
        File[] r92 = r9.listFiles();
        File r0 = FileLoader.checkDirectory(6);
        if (r92 == null) goto L30;
        int r1 = 0;
    L6:
        if (r1 >= r92.length) goto L35;
        File r2 = r92[r1];
        if (r2.isDirectory() == false) goto L13;
        if (r2.getName().equals("exteraGram") == false) goto L13;
        checkDirectory(r2);
    L29:
        r1 = r1 + 1;
    L13:
        if (r2.equals(r0) == true) goto L29;
        ListItem r3 = new ListItem(null);
        r3.title = r2.getName();
        r3.file = r2;
        String r4 = r2.getName();
        String[] r5 = r4.split("\\.");
        if (r5.length <= 1) goto L18;
        String r52 = r5[r5.length - 1];
    L19:
        r3.ext = r52;
        r3.subtitle = AndroidUtilities.formatFileSize(r2.length());
        String r42 = r4.toLowerCase();
        if (r42.endsWith(".jpg") == false) goto L22;
    L27:
        r3.thumb = r2.getAbsolutePath();
    L28:
        ListAdapter.m9613$$Nest$fgetrecentItems(this.listAdapter).add(r3);
        goto L29
    L22:
        if (r42.endsWith(".png") == true) goto L27;
        if (r42.endsWith(".gif") == true) goto L27;
        if (r42.endsWith(".jpeg") == false) goto L28;
    L18:
        r52 = "?";
        goto L19
    L35:
        return;
    }

    private int getTopForScroll() {
        View r0 = this.listView.getChildAt(0);
        RecyclerView.ViewHolder r1 = this.listView.findContainingViewHolder(r0);
        int r2 = -this.listView.getPaddingTop();
        if (r1 != null) goto L5;
        return r2;
    L5:
        if (r1.getAdapterPosition() == 0) goto L7;
        return r2;
    L7:
        return r2 + r0.getTop();
    }

    private /* synthetic */ void lambda$new$1(View r14, int r15) {
        RecyclerView.Adapter r0 = this.listView.getAdapter();
        ListAdapter r1 = this.listAdapter;
        if (r0 != r1) goto L5;
        Object r152 = r1.getItem(r15);
    L7:
        if ((r152 instanceof ListItem) == false) goto L58;
        ListItem r153 = (ListItem) r152;
        File r02 = r153.file;
        if (Build.VERSION.SDK_INT < 30) goto L11;
        boolean r12 = Environment.isExternalStorageManager();
    L13:
        if (BuildVars.NO_SCOPED_STORAGE == true) goto L21;
        int r2 = r153.icon;
        if (r2 != R.drawable.files_storage) goto L17;
    L18:
        if (r12 == true) goto L21;
        this.delegate.startDocumentSelectActivity();
        return;
    L17:
        if (r2 == R.drawable.files_internal) goto L18;
    L21:
        ChatActivity r13 = null;
        if (r02 != null) goto L47;
        int r142 = r153.icon;
        if (r142 != R.drawable.files_gallery) goto L35;
        HashMap r7 = new HashMap();
        ArrayList r8 = new ArrayList();
        BaseFragment r143 = this.parentAlert.baseFragment;
        if ((r143 instanceof ChatActivity) == false) goto L28;
        r13 = (ChatActivity) r143;
    L28:
        ChatActivity r11 = r13;
        MediaController.AlbumEntry r6 = MediaController.allMediaAlbumEntry;
        if (r11 == null) goto L31;
        boolean r10 = true;
    L32:
        PhotoPickerActivity r4 = new PhotoPickerActivity(0, r6, r7, r8, 0, r10, r11, false);
        r4.setDocumentsPicker(true);
        r4.setDelegate(new AnonymousClass8(this, r7, r8));
        r4.setMaxSelectedPhotos(this.maxSelectedFiles, false);
        this.parentAlert.presentFragment(r4);
        this.parentAlert.dismiss(true);
        return;
    L31:
        r10 = false;
        goto L32
    L35:
        if (r142 != R.drawable.files_music) goto L40;
        DocumentSelectActivityDelegate r132 = this.delegate;
        if (r132 == null) goto L60;
        r132.startMusicSelectActivity();
        return;
    L60:
        return;
    L40:
        int r144 = getTopForScroll();
        prepareAnimation();
        HistoryEntry r154 = (HistoryEntry) ListAdapter.m9611$$Nest$fgethistory(this.listAdapter).remove(ListAdapter.m9611$$Nest$fgethistory(this.listAdapter).size() - 1);
        this.parentAlert.actionBar.setTitle(r154.title);
        File r155 = r154.dir;
        if (r155 == null) goto L43;
        listFiles(r155);
    L44:
        updateSearchButton();
        this.layoutManager.scrollToPositionWithOffset(0, r144);
        runAnimation(2);
        return;
    L43:
        listRoots();
        goto L44
    L47:
        if (r02.isDirectory() == false) goto L56;
        HistoryEntry r145 = new HistoryEntry(null);
        View r16 = this.listView.getChildAt(0);
        RecyclerView.ViewHolder r3 = this.listView.findContainingViewHolder(r16);
        if (r3 == null) goto L61;
        r145.scrollItem = r3.getAdapterPosition();
        r145.scrollOffset = r16.getTop();
        r145.dir = this.currentDir;
        r145.title = this.parentAlert.actionBar.getTitle();
        prepareAnimation();
        ListAdapter.m9611$$Nest$fgethistory(this.listAdapter).add(r145);
        if (listFiles(r02) == true) goto L54;
        ListAdapter.m9611$$Nest$fgethistory(this.listAdapter).remove(r145);
        return;
    L54:
        runAnimation(1);
        this.parentAlert.actionBar.setTitle(r153.title);
        return;
    L61:
        return;
    L56:
        onItemClick(r14, r153);
        return;
    L11:
        r12 = false;
        goto L13
    L58:
        onItemClick(r14, r152);
        return;
    L5:
        r152 = this.searchAdapter.getItem(r15);
        goto L7
    }

    private /* synthetic */ boolean lambda$new$2(View r3, int r4) {
        RecyclerView.Adapter r0 = this.listView.getAdapter();
        ListAdapter r1 = this.listAdapter;
        if (r0 != r1) goto L5;
        Object r42 = r1.getItem(r4);
    L7:
        return onItemClick(r3, r42);
    L5:
        r42 = this.searchAdapter.getItem(r4);
        goto L7
    }

    private /* synthetic */ void lambda$new$3(View r2, int r3) {
        this.filtersView.cancelClickRunnables(true);
        SearchAdapter.m9621$$Nest$maddSearchFilter(this.searchAdapter, this.filtersView.getFilterAt(r3));
    }

    private /* synthetic */ void lambda$runAnimation$4(int r5, float r6, ValueAnimator r7) {
        float r72 = ((Float) r7.getAnimatedValue()).floatValue();
        if (r5 != 1) goto L6;
        this.listView.setTranslationX(r6 * r72);
        this.listView.setAlpha(1.0f - r72);
        this.listView.invalidate();
        this.backgroundListView.setAlpha(r72);
        float r73 = (r72 * 0.05f) + 0.95f;
        this.backgroundListView.setScaleX(r73);
        this.backgroundListView.setScaleY(r73);
        return;
    L6:
        this.backgroundListView.setTranslationX(r6 * r72);
        this.backgroundListView.setAlpha(Math.max(0.0f, 1.0f - r72));
        this.backgroundListView.invalidate();
        this.listView.setAlpha(r72);
        float r74 = (r72 * 0.05f) + 0.95f;
        this.listView.setScaleX(r74);
        this.listView.setScaleY(r74);
        this.backgroundListView.invalidate();
    }

    private /* synthetic */ void lambda$sendSelectedItems$5(ArrayList r15, String r16, ArrayList r17, ArrayList r18, boolean r19, int r20, long r21, boolean r23, Long r24) {
        this.sendPressed = true;
        this.delegate.didSelectFiles(r15, r16, r17, r18, r19, r20, 0, r21, r23, r24.longValue());
        this.parentAlert.dismiss(true);
    }

    private /* synthetic */ void lambda$sendSelectedPhotos$6(ArrayList r8, boolean r9, int r10, Long r11) {
        this.delegate.didSelectPhotos(r8, r9, r10, 0, r11.longValue());
    }

    private /* synthetic */ int lambda$sortFileItems$8(ListItem r7, ListItem r8) {
        File r0 = r7.file;
        if (r0 != null) goto L6;
        return -1;
    L6:
        if (r8.file != null) goto L8;
        return 1;
    L8:
        boolean r02 = r0.isDirectory();
        if (r02 == r8.file.isDirectory()) goto L13;
        if (r02 == false) goto L12;
        return -1;
    L12:
        return 1;
    L13:
        if (r02 == true) goto L25;
        if (this.sortByName == true) goto L25;
        long r6 = r7.file.lastModified();
        long r4 = r8.file.lastModified();
        if (r6 != r4) goto L21;
        return 0;
    L21:
        if (r6 <= r4) goto L23;
        return -1;
    L23:
        return 1;
    L25:
        return r7.file.getName().compareToIgnoreCase(r8.file.getName());
    }

    private /* synthetic */ int lambda$sortRecentItems$7(ListItem r3, ListItem r4) {
        if (this.sortByName == true) goto L5;
        long r2 = r3.file.lastModified();
        long r0 = r4.file.lastModified();
        if (r2 != r0) goto L10;
        return 0;
    L10:
        if (r2 <= r0) goto L13;
        return -1;
    L13:
        return 1;
    L5:
        return r3.file.getName().compareToIgnoreCase(r4.file.getName());
    }

    private boolean listFiles(File r10) {
        this.hasFiles = false;
        if (r10.canRead() == false) goto L5;
        File[] r1 = r10.listFiles();     // Catch: Exception -> L58
        if (r1 != null) goto L22;
        showErrorBox(LocaleController.getString(R.string.UnknownError));
        return false;
    L22:
        this.currentDir = r10;
        ListAdapter.m9612$$Nest$fgetitems(this.listAdapter).clear();
        File r102 = FileLoader.checkDirectory(6);
        int r3 = 0;
    L24:
        if (r3 >= r1.length) goto L49;
        File r4 = r1[r3];
        if (r4.getName().indexOf(46) == 0) goto L48;
        if (r4.equals(r102) == true) goto L48;
        ListItem r6 = new ListItem(null);
        r6.title = r4.getName();
        r6.file = r4;
        if (r4.isDirectory() == false) goto L34;
        r6.icon = R.drawable.files_folder;
        r6.subtitle = LocaleController.getString(R.string.Folder);
    L47:
        ListAdapter.m9612$$Nest$fgetitems(this.listAdapter).add(r6);
        goto L48
    L34:
        this.hasFiles = true;
        String r5 = r4.getName();
        String[] r7 = r5.split("\\.");
        if (r7.length <= 1) goto L37;
        String r72 = r7[r7.length - 1];
    L38:
        r6.ext = r72;
        r6.subtitle = AndroidUtilities.formatFileSize(r4.length());
        String r52 = r5.toLowerCase();
        if (r52.endsWith(".jpg") == false) goto L41;
    L46:
        r6.thumb = r4.getAbsolutePath();
        goto L47
    L41:
        if (r52.endsWith(".png") == true) goto L46;
        if (r52.endsWith(".gif") == true) goto L46;
        if (r52.endsWith(".jpeg") == false) goto L47;
    L37:
        r72 = "?";
    L48:
        r3 = r3 + 1;
        goto L24
    L49:
        ListItem r103 = new ListItem(null);
        r103.title = "..";
        if (ListAdapter.m9611$$Nest$fgethistory(this.listAdapter).size() <= 0) goto L55;
        File r12 = ((HistoryEntry) ListAdapter.m9611$$Nest$fgethistory(this.listAdapter).get(ListAdapter.m9611$$Nest$fgethistory(this.listAdapter).size() - 1)).dir;
        if (r12 != null) goto L54;
        r103.subtitle = LocaleController.getString(R.string.Folder);
    L56:
        r103.icon = R.drawable.files_folder;
        r103.file = null;
        ListAdapter.m9612$$Nest$fgetitems(this.listAdapter).add(0, r103);
        sortFileItems();
        updateSearchButton();
        AndroidUtilities.clearDrawableAnimation(this.listView);
        this.scrolling = true;
        int r104 = getTopForScroll();
        this.listAdapter.notifyDataSetChanged();
        this.layoutManager.scrollToPositionWithOffset(0, r104);
        return true;
    L54:
        r103.subtitle = r12.toString();
        goto L56
    L55:
        r103.subtitle = LocaleController.getString(R.string.Folder);
    L58:
        e = move-exception;
        showErrorBox(e.getLocalizedMessage());
        return false;
    L5:
        if (r10.getAbsolutePath().startsWith(Environment.getExternalStorageDirectory().toString()) == true) goto L11;
        if (r10.getAbsolutePath().startsWith("/sdcard") == true) goto L11;
        if (r10.getAbsolutePath().startsWith("/mnt/sdcard") == true) goto L11;
    L16:
        showErrorBox(LocaleController.getString(R.string.AccessError));
        return false;
    L11:
        if (Environment.getExternalStorageState().equals("mounted") == true) goto L16;
        if (Environment.getExternalStorageState().equals("mounted_ro") == true) goto L16;
        this.currentDir = r10;
        ListAdapter.m9612$$Nest$fgetitems(this.listAdapter).clear();
        Environment.getExternalStorageState();
        AndroidUtilities.clearDrawableAnimation(this.listView);
        this.scrolling = true;
        this.listAdapter.notifyDataSetChanged();
        return true;
    }

    @SuppressLint({"NewApi"})
    private void listRoots() {
        BufferedReader r0 = null;
        this.currentDir = null;
        this.hasFiles = false;
        ListAdapter.m9612$$Nest$fgetitems(this.listAdapter).clear();
        HashSet r1 = new HashSet();
        if (Build.VERSION.SDK_INT < 30) goto L5;
        Environment.isExternalStorageManager();
    L5:
        String r2 = Environment.getExternalStorageDirectory().getPath();
        String r3 = Environment.getExternalStorageState();
        if (r3.equals("mounted") == false) goto L8;
    L9:
        ListItem r32 = new ListItem(null);
        if (Environment.isExternalStorageRemovable() == false) goto L12;
        r32.title = LocaleController.getString(R.string.SdCard);
        r32.icon = R.drawable.files_internal;
        r32.subtitle = LocaleController.getString(R.string.ExternalFolderInfo);
    L13:
        r32.file = Environment.getExternalStorageDirectory();
        ListAdapter.m9612$$Nest$fgetitems(this.listAdapter).add(r32);
        r1.add(r2);
    L110:
        BufferedReader r22 = new BufferedReader(new FileReader("/proc/mounts"));     // Catch: Throwable -> L66 Exception -> L68
    L102:
        String r33 = r22.readLine();     // Catch: Throwable -> L22 Exception -> L24
        if (r33 != null) goto L18;
    L103:
        r22.close();     // Catch: Exception -> L64
    L108:
        File r12 = new File(ApplicationLoader.applicationContext.getExternalFilesDir(null), "exteraGram");     // Catch: Exception -> L77
        if (r12.exists() == false) goto L80;
        ListItem r23 = new ListItem(null);     // Catch: Exception -> L77
        r23.title = LocaleController.getString("AppName");     // Catch: Exception -> L77
        r23.subtitle = LocaleController.getString(R.string.AppFolderInfo);     // Catch: Exception -> L77
        r23.icon = R.drawable.files_folder;     // Catch: Exception -> L77
        r23.file = r12;     // Catch: Exception -> L77
        ListAdapter.m9612$$Nest$fgetitems(this.listAdapter).add(r23);     // Catch: Exception -> L77
    L80:
        if (this.isSoundPicker == true) goto L87;
        ChatAttachAlert r13 = this.parentAlert;
        if (r13 != null) goto L84;
    L85:
        ListItem r14 = new ListItem(null);
        r14.title = LocaleController.getString(R.string.Gallery);
        r14.subtitle = LocaleController.getString(R.string.GalleryInfo);
        r14.icon = R.drawable.files_gallery;
        r14.file = null;
        ListAdapter.m9612$$Nest$fgetitems(this.listAdapter).add(r14);
        goto L87
    L84:
        if (r13.isPollAttach == false) goto L85;
    L87:
        if (this.allowMusic == false) goto L90;
        ListItem r15 = new ListItem(null);
        r15.title = LocaleController.getString(R.string.AttachMusic);
        r15.subtitle = LocaleController.getString(R.string.MusicInfo);
        r15.icon = R.drawable.files_music;
        r15.file = null;
        ListAdapter.m9612$$Nest$fgetitems(this.listAdapter).add(r15);
    L90:
        if (ListAdapter.m9613$$Nest$fgetrecentItems(this.listAdapter).isEmpty() == true) goto L92;
        this.hasFiles = true;
    L92:
        AndroidUtilities.clearDrawableAnimation(this.listView);
        this.scrolling = true;
        this.listAdapter.notifyDataSetChanged();
        return;
    L77:
        e = move-exception;
        FileLog.e(e);
    L64:
        e = move-exception;
        FileLog.e(e);
        goto L108
    L18:
        if (r33.contains("vfat") == true) goto L27;
        if (r33.contains("/mnt") == false) goto L102;
    L27:
        if (BuildVars.LOGS_ENABLED == false) goto L29;
        FileLog.d(r33);     // Catch: Throwable -> L22 Exception -> L24
    L29:
        StringTokenizer r4 = new StringTokenizer(r33, " ");     // Catch: Throwable -> L22 Exception -> L24
        r4.nextToken();     // Catch: Throwable -> L22 Exception -> L24
        String r42 = r4.nextToken();     // Catch: Throwable -> L22 Exception -> L24
        if (r1.contains(r42) == true) goto L102;
        if (r33.contains("/dev/block/vold") == false) goto L102;
        if (r33.contains("/mnt/secure") == true) goto L102;
        if (r33.contains("/mnt/asec") == true) goto L102;
        if (r33.contains("/mnt/obb") == true) goto L102;
        if (r33.contains("/dev/mapper") == true) goto L102;
        if (r33.contains("tmpfs") == true) goto L102;
        if (new File(r42).isDirectory() == true) goto L51;
        int r34 = r42.lastIndexOf(47);     // Catch: Throwable -> L22 Exception -> L24
        if (r34 == (-1)) goto L51;
        String r35 = "/storage/" + r42.substring(r34 + 1);     // Catch: Throwable -> L22 Exception -> L24
        if (new File(r35).isDirectory() == false) goto L51;
        r42 = r35;
    L51:
        r1.add(r42);     // Catch: Throwable -> L22 Exception -> L24
        ListItem r36 = new ListItem(null);     // Catch: Throwable -> L22 Exception -> L55
        if (r42.toLowerCase().contains("sd") == false) goto L57;
        r36.title = LocaleController.getString(R.string.SdCard);     // Catch: Throwable -> L22 Exception -> L55
    L58:
        r36.subtitle = LocaleController.getString(R.string.ExternalFolderInfo);     // Catch: Throwable -> L22 Exception -> L55
        r36.icon = R.drawable.files_internal;     // Catch: Throwable -> L22 Exception -> L55
        r36.file = new File(r42);     // Catch: Throwable -> L22 Exception -> L55
        ListAdapter.m9612$$Nest$fgetitems(this.listAdapter).add(r36);     // Catch: Throwable -> L22 Exception -> L55
        goto L102
    L57:
        r36.title = LocaleController.getString(R.string.ExternalStorage);     // Catch: Throwable -> L22 Exception -> L55
    L55:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L22 Exception -> L24
    L22:
        th = th;
        r0 = r22;
    L94:
        if (r0 != null) goto L100;
    L99:
        throw th;
    L100:
        r0.close();     // Catch: Exception -> L97
    L97:
        e = move-exception;
        FileLog.e(e);
    L24:
        e = e;
    L70:
        FileLog.e(e);     // Catch: Throwable -> L22
        if (r22 == null) goto L108;
    L68:
        e = e;
        r22 = null;
    L66:
        th = th;
        goto L94
    L12:
        r32.title = LocaleController.getString(R.string.InternalStorage);
        r32.icon = R.drawable.files_storage;
        r32.subtitle = LocaleController.getString(R.string.InternalFolderInfo);
        goto L13
    L8:
        if (r3.equals("mounted_ro") == false) goto L110;
        goto L9
    }

    private boolean onItemClick(View r19, Object r20) {
        boolean r5 = false;
        if ((r20 instanceof ListItem) == false) goto L48;
        ListItem r2 = (ListItem) r20;
        File r3 = r2.file;
        if (r3 != null) goto L7;
    L46:
        return false;
    L7:
        if (r3.isDirectory() == true) goto L46;
        String r32 = r2.file.getAbsolutePath();
        if (this.selectedFiles.containsKey(r32) == false) goto L13;
        this.selectedFiles.remove(r32);
        this.selectedFilesOrder.remove(r32);
        boolean r22 = false;
    L45:
        this.scrolling = false;
    L58:
        if (this.parentAlert.isPollAttach == false) goto L75;
        if (this.selectedFiles.isEmpty() == false) goto L64;
        if (this.selectedMessages.isEmpty() == false) goto L64;
    L73:
        return true;
    L64:
        if (this.delegate == null) goto L73;
        if (this.sendPressed == true) goto L73;
        ArrayList<MessageObject> r9 = new ArrayList();
        Iterator<FilteredSearchView.MessageHashId> r1 = this.selectedMessages.keySet().iterator();
    L70:
        if (r1.hasNext() == false) goto L72;
        r9.add(this.selectedMessages.get(r1.next()));
        goto L70
    L72:
        this.delegate.didSelectFiles(new ArrayList(this.selectedFilesOrder), null, null, r9, false, 0, 0, 0, false, 0);
        goto L73
    L75:
        if ((r19 instanceof SharedDocumentCell) == false) goto L77;
        ((SharedDocumentCell) r19).setChecked(r22, true);
    L77:
        ChatAttachAlert r0 = this.parentAlert;
        if (r22 == false) goto L80;
        int r12 = 1;
    L81:
        r0.updateCountButton(r12);
        return true;
    L80:
        r12 = 2;
        goto L81
    L13:
        if (r2.file.canRead() == true) goto L17;
        showErrorBox(LocaleController.getString(R.string.AccessError));
        return false;
    L17:
        if (this.canSelectOnlyImageFiles == false) goto L23;
        if (r2.thumb != null) goto L23;
        showErrorBox(LocaleController.formatString("PassportUploadNotImage", R.string.PassportUploadNotImage, new Object[0]));
        return false;
    L23:
        if (r2.file.length() <= FileLoader.DEFAULT_MAX_FILE_SIZE) goto L27;
        if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == true) goto L27;
    L28:
        ChatAttachAlert r02 = this.parentAlert;
        LimitReachedBottomSheet r7 = new LimitReachedBottomSheet(r02.baseFragment, r02.getContainer().getContext(), 6, UserConfig.selectedAccount, null);
        r7.setVeryLargeFile(true);
        r7.show();
        return false;
    L27:
        if (r2.file.length() > FileLoader.DEFAULT_MAX_FILE_SIZE_PREMIUM) goto L28;
        if (this.maxSelectedFiles < 0) goto L37;
        int r6 = this.selectedFiles.size();
        int r72 = this.maxSelectedFiles;
        if (r6 < r72) goto L37;
        showErrorBox(LocaleController.formatString("PassportUploadMaxReached", R.string.PassportUploadMaxReached, new Object[]{LocaleController.formatPluralString("Files", r72, new Object[0])}));
        return false;
    L37:
        if (this.isSoundPicker == false) goto L42;
        if (isRingtone(r2.file) == true) goto L42;
        return false;
    L42:
        if (r2.file.length() != 0) goto L44;
        return false;
    L44:
        boolean r62 = this.parentAlert.storyMediaPicker;
        this.selectedFiles.put(r32, r2);
        this.selectedFilesOrder.add(r32);
        r22 = true;
        goto L45
    L48:
        if ((r20 instanceof MessageObject) == false) goto L83;
        MessageObject r23 = (MessageObject) r20;
        FilteredSearchView.MessageHashId r33 = new FilteredSearchView.MessageHashId(r23.getId(), r23.getDialogId());
        boolean r63 = this.selectedMessages.containsKey(r33);
        HashMap<FilteredSearchView.MessageHashId, MessageObject> r73 = this.selectedMessages;
        if (r63 == false) goto L53;
        r73.remove(r33);
    L56:
        r22 = r5;
        goto L58
    L53:
        if (r73.size() < 100) goto L55;
        return false;
    L55:
        this.selectedMessages.put(r33, r23);
        r5 = true;
        goto L56
    L83:
        return false;
    }

    private void prepareAnimation() {
        ListAdapter.m9611$$Nest$fgethistory(this.backgroundListAdapter).clear();
        ListAdapter.m9611$$Nest$fgethistory(this.backgroundListAdapter).addAll(ListAdapter.m9611$$Nest$fgethistory(this.listAdapter));
        ListAdapter.m9612$$Nest$fgetitems(this.backgroundListAdapter).clear();
        ListAdapter.m9612$$Nest$fgetitems(this.backgroundListAdapter).addAll(ListAdapter.m9612$$Nest$fgetitems(this.listAdapter));
        ListAdapter.m9613$$Nest$fgetrecentItems(this.backgroundListAdapter).clear();
        ListAdapter.m9613$$Nest$fgetrecentItems(this.backgroundListAdapter).addAll(ListAdapter.m9613$$Nest$fgetrecentItems(this.listAdapter));
        this.backgroundListAdapter.notifyDataSetChanged();
        this.backgroundListView.setVisibility(0);
        this.backgroundListView.setPadding(this.listView.getPaddingLeft(), this.listView.getPaddingTop(), this.listView.getPaddingRight(), this.listView.getPaddingBottom());
        int r0 = this.layoutManager.findFirstVisibleItemPosition();
        if (r0 < 0) goto L8;
        View r1 = this.layoutManager.findViewByPosition(r0);
        if (r1 == null) goto L9;
        this.backgroundLayoutManager.scrollToPositionWithOffset(r0, r1.getTop() - this.backgroundListView.getPaddingTop());
        return;
    L9:
        return;
    }

    private void runAnimation(int r10) {
        ValueAnimator r0 = this.listAnimation;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        this.currentAnimationType = r10;
        int r1 = 0;
    L7:
        if (r1 >= getChildCount()) goto L12;
        if (getChildAt(r1) == this.listView) goto L14;
        r1 = r1 + 1;
    L14:
        if (r10 != 1) goto L16;
        float r3 = AndroidUtilities.dp(150.0f);
        this.backgroundListView.setAlpha(1.0f);
        this.backgroundListView.setScaleX(1.0f);
        this.backgroundListView.setScaleY(1.0f);
        this.backgroundListView.setTranslationX(0.0f);
        removeView(this.backgroundListView);
        addView(this.backgroundListView, r1);
        this.backgroundListView.setVisibility(0);
        this.listView.setTranslationX(r3);
        this.listView.setAlpha(0.0f);
        this.listAnimation = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
    L17:
        this.listAnimation.addUpdateListener(new ChatAttachAlertDocumentLayout$$ExternalSyntheticLambda7(this, r10, r3));
        this.listAnimation.addListener(new AnonymousClass9(this));
        ValueAnimator r02 = this.listAnimation;
        if (r10 != 1) goto L20;
        r02.setDuration(220);
    L21:
        this.listAnimation.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.listAnimation.start();
        return;
    L20:
        r02.setDuration(200);
        goto L21
    L16:
        r3 = AndroidUtilities.dp(150.0f);
        this.listView.setAlpha(0.0f);
        this.listView.setScaleX(0.95f);
        this.listView.setScaleY(0.95f);
        this.backgroundListView.setScaleX(1.0f);
        this.backgroundListView.setScaleY(1.0f);
        this.backgroundListView.setTranslationX(0.0f);
        this.backgroundListView.setAlpha(1.0f);
        removeView(this.backgroundListView);
        addView(this.backgroundListView, r1 + 1);
        this.backgroundListView.setVisibility(0);
        this.listAnimation = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        goto L17
    L12:
        r1 = 0;
        goto L14
    }

    private void sendSelectedPhotos(HashMap<Object, Object> r6, ArrayList<Object> r7, boolean r8, int r9) {
        if (r6.isEmpty() == false) goto L5;
        return;
    L5:
        if (this.delegate != null) goto L7;
        return;
    L7:
        if (this.sendPressed == true) goto L31;
        this.sendPressed = true;
        ArrayList r0 = new ArrayList();
        int r1 = 0;
    L11:
        if (r1 >= r7.size()) goto L24;
        Object r2 = r6.get(r7.get(r1));
        SendMessagesHelper.SendingMediaInfo r3 = new SendMessagesHelper.SendingMediaInfo();
        r0.add(r3);
        if ((r2 instanceof MediaController.PhotoEntry) == false) goto L23;
        MediaController.PhotoEntry r22 = (MediaController.PhotoEntry) r2;
        String r4 = r22.imagePath;
        if (r4 == null) goto L17;
        r3.path = r4;
    L18:
        r3.thumbPath = r22.thumbPath;
        r3.coverPath = r22.coverPath;
        r3.videoEditedInfo = r22.editedInfo;
        r3.isVideo = r22.isVideo;
        CharSequence r42 = r22.caption;
        if (r42 == null) goto L21;
        String r43 = r42.toString();
    L22:
        r3.caption = r43;
        r3.entities = r22.entities;
        r3.masks = r22.stickers;
        r3.ttl = r22.ttl;
        goto L23
    L21:
        r43 = null;
        goto L22
    L17:
        r3.path = r22.path;
    L23:
        r1 = r1 + 1;
        goto L11
    L24:
        ChatAttachAlert r62 = this.parentAlert;
        AlertsCreator.ensurePaidMessageConfirmation(r62.currentAccount, r62.getDialogId(), r0.size() + this.parentAlert.getAdditionalMessagesCount(), new ChatAttachAlertDocumentLayout$$ExternalSyntheticLambda8(this, r0, r8, r9));
        return;
    }

    private void showErrorBox(String r3) {
        new AlertDialog.Builder(getContext(), this.resourcesProvider).setTitle(LocaleController.getString(R.string.AppName)).setMessage(r3).setPositiveButton(LocaleController.getString(R.string.OK), null).show();
    }

    private void sortFileItems() {
        if (this.currentDir != null) goto L5;
        return;
    L5:
        Collections.sort(ListAdapter.m9612$$Nest$fgetitems(this.listAdapter), new ChatAttachAlertDocumentLayout$$ExternalSyntheticLambda5(this));
    }

    private void sortRecentItems() {
        Collections.sort(ListAdapter.m9613$$Nest$fgetrecentItems(this.listAdapter), new ChatAttachAlertDocumentLayout$$ExternalSyntheticLambda6(this));
    }

    private void updateEmptyView() {
        RecyclerView.Adapter r0 = this.listView.getAdapter();
        SearchAdapter r1 = this.searchAdapter;
        int r2 = 0;
        boolean r3 = true;
        if (r0 != r1) goto L11;
        if (SearchAdapter.m9620$$Nest$fgetsearchResult(r1).isEmpty() == true) goto L7;
    L9:
        r3 = false;
    L12:
        StickerEmptyView r02 = this.emptyView;
        if (r3 == true) goto L16;
        r2 = 8;
    L16:
        r02.setVisibility(r2);
        updateEmptyViewPosition();
        return;
    L7:
        if (this.searchAdapter.sections.isEmpty() == false) goto L9;
    L11:
        if (this.listAdapter.getItemCount() != 1) goto L9;
        goto L9
    }

    private void updateEmptyViewPosition() {
        if (this.emptyView.getVisibility() == 0) goto L6;
        return;
    L6:
        if (this.listView.getChildAt(0) != null) goto L8;
        return;
    L8:
        float r1 = this.emptyView.getTranslationY();
        this.additionalTranslationY = ((this.emptyView.getMeasuredHeight() - getMeasuredHeight()) + r0.getTop()) / 2;
        this.emptyView.setTranslationY(r1);
    }

    private void updateSearchButton() {
        ActionBarMenuItem r0 = this.searchItem;
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.isSearchFieldVisible() == true) goto L16;
        ActionBarMenuItem r02 = this.searchItem;
        if (this.hasFiles == false) goto L10;
    L13:
        int r2 = 0;
    L14:
        r02.setVisibility(r2);
        return;
    L10:
        if (ListAdapter.m9611$$Nest$fgethistory(this.listAdapter).isEmpty() == true) goto L13;
        r2 = 8;
        goto L14
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getButtonsHideOffset() {
        return AndroidUtilities.dp(62.0f);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getCurrentItemTop() {
        if (this.listView.getChildCount() > 0) goto L6;
        return Integer.MAX_VALUE;
    L6:
        int r1 = 0;
        View r0 = this.listView.getChildAt(0);
        RecyclerListView.Holder r3 = (RecyclerListView.Holder) this.listView.findContainingViewHolder(r0);
        int r02 = ((((int) r0.getY()) - AndroidUtilities.statusBarHeight) - AndroidUtilities.dp(4.0f)) - AndroidUtilities.dp(8.0f);
        if (r02 <= 0) goto L12;
        if (r3 == null) goto L12;
        if (r3.getAdapterPosition() != 0) goto L12;
        r1 = r02;
    L12:
        if (r02 < 0) goto L17;
        if (r3 == null) goto L17;
        if (r3.getAdapterPosition() != 0) goto L17;
    L19:
        return r02 + AndroidUtilities.dp(13.0f);
    L17:
        r02 = r1;
        goto L19
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getFirstOffset() {
        return getListTopPadding() + AndroidUtilities.dp(5.0f);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getListTopPadding() {
        return this.listView.getPaddingTop();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getSelectedItemsCount() {
        return this.selectedFiles.size() + this.selectedMessages.size();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.searchItem.getSearchField(), ThemeDescription.FLAG_CURSORCOLOR, null, null, null, null, Theme.key_dialogTextBlack));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_dialogScrollGlow));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ShadowSectionCell.class}, null, null, null, Theme.key_windowBackgroundGrayShadow));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{ShadowSectionCell.class}, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{SharedDocumentCell.class}, new String[]{"nameTextView"}, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{SharedDocumentCell.class}, new String[]{"dateTextView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText3));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKBOX, new Class[]{SharedDocumentCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_checkbox));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKBOXCHECK, new Class[]{SharedDocumentCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_checkboxCheck));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{SharedDocumentCell.class}, new String[]{"thumbImageView"}, null, null, null, Theme.key_files_folderIcon));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_IMAGECOLOR | ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{SharedDocumentCell.class}, new String[]{"thumbImageView"}, null, null, null, Theme.key_files_folderIconBackground));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{SharedDocumentCell.class}, new String[]{"extTextView"}, null, null, null, Theme.key_files_iconText));
        return r1;
    }

    public boolean isRingtone(File r9) {
        String r0 = FileLoader.getFileExtension(r9);
        if (r0 == null) goto L5;
        String r02 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(r0);
    L7:
        if (r9.length() == 0) goto L25;
        if (r02 == null) goto L25;
        if (RingtoneDataStore.ringtoneSupportedMimeType.contains(r02) == false) goto L25;
        if (r9.length() <= MessagesController.getInstance(UserConfig.selectedAccount).ringtoneSizeMax) goto L27;
        BulletinFactory.of(this.parentAlert.getContainer(), null).createErrorBulletinSubtitle(LocaleController.formatString("TooLargeError", R.string.TooLargeError, new Object[0]), LocaleController.formatString("ErrorRingtoneSizeTooBig", R.string.ErrorRingtoneSizeTooBig, new Object[]{Integer.valueOf(MessagesController.getInstance(UserConfig.selectedAccount).ringtoneSizeMax / 1024)}), null).show();
        return false;
    L27:
        MediaMetadataRetriever r03 = new MediaMetadataRetriever();     // Catch: Exception -> L18
        r03.setDataSource(ApplicationLoader.applicationContext, Uri.fromFile(r9));     // Catch: Exception -> L18
        int r92 = Integer.parseInt(r03.extractMetadata(9));     // Catch: Exception -> L18
    L20:
        if (r92 <= (MessagesController.getInstance(UserConfig.selectedAccount).ringtoneDurationMax * 1000)) goto L23;
        BulletinFactory.of(this.parentAlert.getContainer(), null).createErrorBulletinSubtitle(LocaleController.formatString("TooLongError", R.string.TooLongError, new Object[0]), LocaleController.formatString("ErrorRingtoneDurationTooLong", R.string.ErrorRingtoneDurationTooLong, new Object[]{Integer.valueOf(MessagesController.getInstance(UserConfig.selectedAccount).ringtoneDurationMax)}), null).show();
        return false;
    L23:
        return true;
    L18:
        r92 = Integer.MAX_VALUE;
    L25:
        BulletinFactory.of(this.parentAlert.getContainer(), null).createErrorBulletinSubtitle(LocaleController.formatString("InvalidFormatError", R.string.InvalidFormatError, new Object[0]), LocaleController.getString(R.string.ErrorRingtoneInvalidFormat), null).show();
        return false;
    L5:
        r02 = null;
        goto L7
    }

    public void loadRecentFiles() {
    L49:
        e = move-exception;
        FileLog.e(e);
        return;
    L3:
        if (this.isSoundPicker == true) goto L53;
        checkDirectory(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));     // Catch: Exception -> L49
        sortRecentItems();     // Catch: Exception -> L49
        return;
    L53:
        Cursor r1 = ApplicationLoader.applicationContext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data", "duration", "_size", "mime_type"}, "is_music != 0", null, "date_added DESC");     // Catch: Exception -> L44
    L56:
        if (r1.moveToNext() == false) goto L36;
        File r0 = new File(r1.getString(1));     // Catch: Throwable -> L19
        long r3 = r1.getLong(2);     // Catch: Throwable -> L19
        long r5 = r1.getLong(3);     // Catch: Throwable -> L19
        String r7 = r1.getString(4);     // Catch: Throwable -> L19
        if (r3 > (MessagesController.getInstance(UserConfig.selectedAccount).ringtoneDurationMax * 1000)) goto L56;
        if (r5 > MessagesController.getInstance(UserConfig.selectedAccount).ringtoneSizeMax) goto L56;
        if (TextUtils.isEmpty(r7) == true) goto L21;
        if ("audio/mpeg".equals(r7) == true) goto L21;
        if ("audio/mpeg4".equals(r7) == true) goto L56;
    L21:
        ListItem r32 = new ListItem(null);     // Catch: Throwable -> L19
        r32.title = r0.getName();     // Catch: Throwable -> L19
        r32.file = r0;     // Catch: Throwable -> L19
        String r4 = r0.getName();     // Catch: Throwable -> L19
        String[] r52 = r4.split("\\.");     // Catch: Throwable -> L19
        if (r52.length <= 1) goto L24;
        String r2 = r52[r52.length - 1];     // Catch: Throwable -> L19
    L25:
        r32.ext = r2;     // Catch: Throwable -> L19
        r32.subtitle = AndroidUtilities.formatFileSize(r0.length());     // Catch: Throwable -> L19
        String r22 = r4.toLowerCase();     // Catch: Throwable -> L19
        if (r22.endsWith(".jpg") == false) goto L28;
    L33:
        r32.thumb = r0.getAbsolutePath();     // Catch: Throwable -> L19
    L34:
        ListAdapter.m9613$$Nest$fgetrecentItems(this.listAdapter).add(r32);     // Catch: Throwable -> L19
        goto L56
    L28:
        if (r22.endsWith(".png") == true) goto L33;
        if (r22.endsWith(".gif") == true) goto L33;
        if (r22.endsWith(".jpeg") == false) goto L34;
    L24:
        r2 = "?";
        goto L25
    L36:
        r1.close();     // Catch: Exception -> L44
        return;
    L19:
        th = move-exception;
        if (r1 == null) goto L70;
        r1.close();     // Catch: Throwable -> L41
    L71:
        throw th;     // Catch: Exception -> L44
    L41:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Exception -> L44
        throw th;     // Catch: Exception -> L44
    L70:
        throw th;     // Catch: Exception -> L44
    L44:
        e = move-exception;
        FileLog.e(e);     // Catch: Exception -> L49
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int needsActionBar() {
        return 1;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean onBackPressed() {
        if (canClosePicker() == true) goto L7;
        return true;
    L7:
        return super.onBackPressed();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onDestroy() {
    L6:
        e = move-exception;
        FileLog.e(e);
    L8:
        this.parentAlert.actionBar.closeSearchField();
        ActionBarMenu r0 = this.parentAlert.actionBar.createMenu();
        r0.removeView(this.sortItem);
        r0.removeView(this.searchItem);
        return;
    L3:
        if (this.receiverRegistered == false) goto L8;
        ApplicationLoader.applicationContext.unregisterReceiver(this.receiver);     // Catch: Exception -> L6
        this.receiverRegistered = false;     // Catch: Exception -> L6
        goto L8
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onHide() {
        this.sortItem.setVisibility(8);
        this.searchItem.setVisibility(8);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        updateEmptyViewPosition();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onMenuItemClick(int r2) {
        if (r2 != 6) goto L10;
        SharedConfig.toggleSortFilesByName();
        this.sortByName = SharedConfig.sortFilesByName;
        sortRecentItems();
        sortFileItems();
        this.listAdapter.notifyDataSetChanged();
        ActionBarMenuItem r22 = this.sortItem;
        if (this.sortByName == false) goto L7;
        int r1 = R.drawable.msg_contacts_time;
    L8:
        r22.setIcon(r1);
        return;
    L7:
        r1 = R.drawable.msg_contacts_name;
        goto L8
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onPreMeasure(int r3, int r4) {
        if (this.parentAlert.actionBar.isSearchFieldVisible() == false) goto L5;
    L17:
        int r32 = AndroidUtilities.dp(56.0f);
        this.parentAlert.setAllowNestedScroll(false);
    L18:
        this.listView.setPaddingWithoutRequestLayout(0, r32 + AndroidUtilities.statusBarHeight, 0, this.listPaddingBottom);
        ((FrameLayout.LayoutParams) this.filtersView.getLayoutParams()).topMargin = ActionBar.getCurrentActionBarHeight();
        return;
    L5:
        if (this.parentAlert.sizeNotifierFrameLayout.measureKeyboardHeight() > AndroidUtilities.dp(20.0f)) goto L17;
        if (AndroidUtilities.isTablet() == true) goto L12;
        Point r33 = AndroidUtilities.displaySize;
        if (r33.x <= r33.y) goto L12;
        int r34 = (int) (r4 / 3.5f);
    L13:
        r32 = r34 - AndroidUtilities.dp(1.0f);
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
        super.onResume();
        ListAdapter r0 = this.listAdapter;
        if (r0 == null) goto L5;
        r0.notifyDataSetChanged();
    L5:
        SearchAdapter r1 = this.searchAdapter;
        if (r1 == null) goto L9;
        r1.notifyDataSetChanged();
        return;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onShow(ChatAttachAlert.AttachAlertLayout r2) {
        this.selectedFiles.clear();
        this.selectedMessages.clear();
        SearchAdapter.m9616$$Nest$fgetcurrentSearchFilters(this.searchAdapter).clear();
        this.selectedFilesOrder.clear();
        ListAdapter.m9611$$Nest$fgethistory(this.listAdapter).clear();
        listRoots();
        updateSearchButton();
        updateEmptyView();
        this.parentAlert.actionBar.setTitle(LocaleController.getString(R.string.SelectFile));
        this.sortItem.setVisibility(0);
        this.layoutManager.scrollToPositionWithOffset(0, 0);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void scrollToTop() {
        this.listView.smoothScrollToPosition(0);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public boolean sendSelectedItems(boolean r16, int r17, int r18, long r19, boolean r21) {
        if (this.selectedFiles.size() != 0) goto L7;
        if (this.selectedMessages.size() != 0) goto L7;
    L17:
        return false;
    L7:
        if (this.delegate == null) goto L17;
        if (this.sendPressed == true) goto L17;
        ArrayList r7 = new ArrayList();
        Iterator<FilteredSearchView.MessageHashId> r0 = this.selectedMessages.keySet().iterator();
    L13:
        if (r0.hasNext() == false) goto L15;
        r7.add(this.selectedMessages.get(r0.next()));
        goto L13
    L15:
        ArrayList r4 = new ArrayList(this.selectedFilesOrder);
        CharSequence[] r3 = {this.parentAlert.getCommentView().getText()};
        ArrayList<TLRPC.MessageEntity> r6 = MediaDataController.getInstance(this.parentAlert.currentAccount).getEntities(r3, true);
        String r5 = r3[0].toString();
        ChatAttachAlert r02 = this.parentAlert;
        return AlertsCreator.ensurePaidMessageConfirmation(r02.currentAccount, r02.getDialogId(), ((!TextUtils.isEmpty(r5) ? 1 : 0) + r4.size()) + this.parentAlert.getAdditionalMessagesCount(), new ChatAttachAlertDocumentLayout$$ExternalSyntheticLambda0(this, r4, r5, r6, r7, r16, r17, r19, r21));
    }

    public void setCanSelectOnlyImageFiles(boolean r1) {
        this.canSelectOnlyImageFiles = r1;
    }

    public void setDelegate(DocumentSelectActivityDelegate r1) {
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
}
