package org.telegram.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.Keep;
import androidx.collection.LongSparseArray;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.config.BottomNavigationBar;
import com.exteragram.messenger.utils.ui.MainTabsUiHelper;
import com.exteragram.messenger.utils.ui.TextPaint;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.Objects;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SecretChatHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.SearchTextWatcher;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Adapters.ContactsAdapter;
import org.telegram.ui.Adapters.SearchAdapter;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.LetterSectionCell;
import org.telegram.ui.Cells.ProfileSearchCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.UserCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ContactsEmptyView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.FragmentFloatingButton;
import org.telegram.ui.Components.FragmentSearchField;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.NumberTextView;
import org.telegram.ui.Components.RecyclerAnimationScrollHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ShareAlert$$ExternalSyntheticLambda16;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.StickerEmptyView;
import org.telegram.ui.Components.blur3.DownscaleScrollableNoiseSuppressor;
import org.telegram.ui.Components.blur3.ViewGroupPartRenderer;
import org.telegram.ui.Components.blur3.capture.IBlur3Capture;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceRenderNode;
import org.telegram.ui.Components.inset.WindowAnimatedInsetsProvider;
import org.telegram.ui.MainTabsActivity;

/* loaded from: classes6.dex */
public class ContactsActivity extends BaseFragment implements FactorAnimator.Target, NotificationCenter.NotificationCenterDelegate, MainTabsActivity.TabFragmentDelegate, WindowAnimatedInsetsProvider.Listener {
    private final int ADDITIONAL_LIST_HEIGHT_DP;
    private ImageView actionModeCloseView;
    private int additionFloatingButtonOffset;
    private int additionNavigationBarHeight;
    private float additionalFloatingTranslation;
    private boolean allowBots;
    private boolean allowSelf;
    private boolean allowUsernameSearch;
    private final BoolAnimator animatorSearchFieldVisible;
    private final BoolAnimator animatorSearchHasQuery;
    private boolean askAboutContacts;
    private BackDrawable backDrawable;
    private long channelId;
    private long chatId;
    private boolean checkPermission;
    private SizeNotifierFrameLayout contentView;
    private boolean createSecretChat;
    private boolean creatingChat;
    private ContactsActivityDelegate delegate;
    private boolean destroyAfterSelect;
    private boolean disableSections;
    private StickerEmptyView emptyView;
    private FragmentFloatingButton floatingButton;
    private boolean floatingButtonVisibleByScroll;
    public boolean hasMainTabs;
    private HeaderShadowView headerShadowView;
    private IBlur3Capture iBlur3Capture;
    private boolean iBlur3Invalidated;
    private final RectF iBlur3PositionActionBar;
    private final RectF iBlur3PositionMainTabs;
    private final ArrayList<RectF> iBlur3Positions;
    private final BlurredBackgroundSourceRenderNode iBlur3SourceGlass;
    private final BlurredBackgroundSourceRenderNode iBlur3SourceGlassFrosted;
    private LongSparseArray<TLRPC.User> ignoreUsers;
    private int imeInsetAnimatedHeight;
    private String initialSearchString;
    private boolean lastIsEmpty;
    private LinearLayoutManager layoutManager;
    private RecyclerListView listView;
    private ContactsAdapter listViewAdapter;
    private MainTabsActivityController mainTabsActivityController;
    private int navigationBarHeight;
    private boolean needFinishFragment;
    private boolean needForwardCount;
    private boolean needPhonebook;
    private boolean onlyUsers;
    private ActionBarMenuItem otherItem;
    private AlertDialog permissionDialog;
    private long permissionRequestTime;

    @Keep
    public int phonebookRow;
    private boolean resetDelegate;
    private boolean returnAsResult;
    boolean scheduled;
    private RecyclerAnimationScrollHelper scrollHelper;
    private final DownscaleScrollableNoiseSuppressor scrollableViewNoiseSuppressor;
    private FragmentSearchField searchField;
    private ActionBarMenuItem searchItem;
    private SearchAdapter searchListViewAdapter;
    private String searchQuery;
    private boolean searchWas;
    private boolean searching;
    private String selectAlertString;
    private final LongSparseArray<TLRPC.User> selectedContacts;
    private NumberTextView selectedContactsCountTextView;
    private boolean sortByName;
    Runnable sortContactsRunnable;
    private ActionBarMenuItem sortItem;

    /* renamed from: org.telegram.ui.ContactsActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ContactsActivity this$0;

        public static /* synthetic */ void $r8$lambda$IAPgflADWPEGzRdK6an0yvxnzRs(AnonymousClass1 r0) {
            r0.lambda$onItemClick$0();
        }

        public AnonymousClass1(ContactsActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onItemClick$0() {
            ContactsActivity.m14070$$Nest$fgetsearchField(this.this$0).editText.requestFocus();
            AndroidUtilities.showKeyboard(ContactsActivity.m14070$$Nest$fgetsearchField(this.this$0).editText);
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r4) {
            if (r4 != (-1)) goto L11;
            boolean r42 = ContactsActivity.access$000(this.this$0).isActionModeShowed();
            ContactsActivity r3 = this.this$0;
            if (r42 == false) goto L8;
            ContactsActivity.m14092$$Nest$mhideActionMode(r3);
            return;
        L8:
            r3.finishFragment();
            return;
        L11:
            if (r4 != 100) goto L14;
            ContactsActivity.m14093$$Nest$mperformSelectedContactsDelete(this.this$0);
            return;
        L14:
            int r1 = 1;
            if (r4 != 1) goto L26;
            SharedConfig.toggleSortContactsByName();
            ContactsActivity.m14082$$Nest$fputsortByName(this.this$0, SharedConfig.sortContactsByName);
            ContactsAdapter r43 = ContactsActivity.m14066$$Nest$fgetlistViewAdapter(this.this$0);
            if (ContactsActivity.m14074$$Nest$fgetsortByName(this.this$0) == true) goto L20;
            r1 = 2;
        L20:
            r43.setSortType(r1, false);
            ActionBarMenuItem r44 = ContactsActivity.m14075$$Nest$fgetsortItem(this.this$0);
            if (ContactsActivity.m14074$$Nest$fgetsortByName(this.this$0) == false) goto L23;
            int r32 = R.drawable.msg_contacts_time;
        L24:
            r44.setIcon(r32);
            return;
        L23:
            r32 = R.drawable.msg_contacts_name;
            goto L24
        L26:
            if (r4 != 0) goto L29;
            ContactsActivity.m14065$$Nest$fgetlistView(this.this$0).smoothScrollToPosition(0);
            AndroidUtilities.doOnPreDraw(ContactsActivity.m14070$$Nest$fgetsearchField(this.this$0).editText, new ContactsActivity$1$$ExternalSyntheticLambda0(this));
            return;
        }
    }

    /* renamed from: org.telegram.ui.ContactsActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        final /* synthetic */ ContactsActivity this$0;

        public AnonymousClass2(ContactsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchCollapse() {
            ContactsActivity.m14071$$Nest$fgetsearchListViewAdapter(this.this$0).searchDialogs(null);
            ContactsActivity.m14081$$Nest$fputsearching(this.this$0, false);
            ContactsActivity.m14080$$Nest$fputsearchWas(this.this$0, false);
            ContactsActivity.m14065$$Nest$fgetlistView(this.this$0).setAdapter(ContactsActivity.m14066$$Nest$fgetlistViewAdapter(this.this$0));
            ContactsActivity.m14065$$Nest$fgetlistView(this.this$0).setSectionsType(1);
            ContactsActivity.m14066$$Nest$fgetlistViewAdapter(this.this$0).notifyDataSetChanged();
            ContactsActivity.m14065$$Nest$fgetlistView(this.this$0).setFastScrollVisible(true);
            ContactsActivity.m14065$$Nest$fgetlistView(this.this$0).setVerticalScrollBarEnabled(false);
            RecyclerListView.FastScroll r0 = ContactsActivity.m14065$$Nest$fgetlistView(this.this$0).getFastScroll();
            r0.topOffset = AndroidUtilities.dp(90.0f);
            ContactsActivity.m14086$$Nest$mcheckUi_floatingButtonVisible(this.this$0);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
            ContactsActivity.m14081$$Nest$fputsearching(this.this$0, true);
            ContactsActivity.m14086$$Nest$mcheckUi_floatingButtonVisible(this.this$0);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r4) {
            if (ContactsActivity.m14071$$Nest$fgetsearchListViewAdapter(this.this$0) == null) goto L17;
            String r42 = r4.getText().toString();
            ContactsActivity.m14058$$Nest$fgetanimatorSearchHasQuery(this.this$0).setValue(!r42.isEmpty(), true);
            ContactsActivity.m14079$$Nest$fputsearchQuery(this.this$0, r42);
            boolean r0 = r42.isEmpty();
            ContactsActivity r1 = this.this$0;
            if (r0 == true) goto L13;
            ContactsActivity.m14080$$Nest$fputsearchWas(r1, true);
            if (ContactsActivity.m14065$$Nest$fgetlistView(this.this$0) == null) goto L10;
            ContactsActivity.m14065$$Nest$fgetlistView(this.this$0).setAdapter(ContactsActivity.m14071$$Nest$fgetsearchListViewAdapter(this.this$0));
            ContactsActivity.m14065$$Nest$fgetlistView(this.this$0).setSectionsType(0);
            ContactsActivity.m14071$$Nest$fgetsearchListViewAdapter(this.this$0).notifyDataSetChanged();
            ContactsActivity.m14065$$Nest$fgetlistView(this.this$0).setFastScrollVisible(false);
            ContactsActivity.m14065$$Nest$fgetlistView(this.this$0).setVerticalScrollBarEnabled(true);
        L10:
            ContactsActivity.m14059$$Nest$fgetemptyView(this.this$0).showProgress(true, true);
            ContactsActivity.m14071$$Nest$fgetsearchListViewAdapter(this.this$0).searchDialogs(r42);
            return;
        L13:
            if (ContactsActivity.m14065$$Nest$fgetlistView(r1) == null) goto L16;
            ContactsActivity.m14065$$Nest$fgetlistView(this.this$0).setAdapter(ContactsActivity.m14066$$Nest$fgetlistViewAdapter(this.this$0));
            ContactsActivity.m14065$$Nest$fgetlistView(this.this$0).setSectionsType(1);
            return;
        L16:
            return;
        }
    }

    /* renamed from: org.telegram.ui.ContactsActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends SearchAdapter {
        final /* synthetic */ ContactsActivity this$0;

        public AnonymousClass3(ContactsActivity r1, RecyclerListView r2, Context r3, LongSparseArray r4, LongSparseArray r5, boolean r6, boolean r7, boolean r8, boolean r9, boolean r10, boolean r11, int r12, Theme.ResourcesProvider r13) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
        }

        @Override // org.telegram.ui.Adapters.SearchAdapter
        public void onSearchProgressChanged() {
            if (searchInProgress() == false) goto L5;
            return;
        L5:
            if (getItemCount() != 0) goto L9;
            ContactsActivity.m14059$$Nest$fgetemptyView(this.this$0).showProgress(false, true);
            return;
        }
    }

    /* renamed from: org.telegram.ui.ContactsActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends ContactsAdapter {
        final /* synthetic */ ContactsActivity this$0;

        public AnonymousClass4(ContactsActivity r1, Context r2, BaseFragment r3, int r4, boolean r5, LongSparseArray r6, LongSparseArray r7, int r8) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8);
        }

        @Override // org.telegram.ui.Adapters.ContactsAdapter, org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public int getSectionCount() {
            int r0 = super.getSectionCount();
            ContactsActivity.m14086$$Nest$mcheckUi_floatingButtonVisible(this.this$0);
            ContactsActivity.m14091$$Nest$mcheckUi_sortItem(this.this$0);
            ContactsActivity.m14089$$Nest$mcheckUi_searchFieldHint(this.this$0);
            return r0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            if (ContactsActivity.m14065$$Nest$fgetlistView(this.this$0) != null) goto L5;
            return;
        L5:
            if (ContactsActivity.m14065$$Nest$fgetlistView(this.this$0).getAdapter() != this) goto L19;
            int r0 = super.getItemCount();
            boolean r1 = ContactsActivity.m14068$$Nest$fgetneedPhonebook(this.this$0);
            ContactsActivity r4 = this.this$0;
            boolean r2 = false;
            if (r1 == false) goto L13;
            RecyclerListView r42 = ContactsActivity.m14065$$Nest$fgetlistView(r4);
            if (r0 == 2) goto L11;
            r2 = true;
        L11:
            r42.setFastScrollVisible(r2);
            return;
        L13:
            RecyclerListView r43 = ContactsActivity.m14065$$Nest$fgetlistView(r4);
            if (r0 == 0) goto L16;
            r2 = true;
        L16:
            r43.setFastScrollVisible(r2);
            return;
        }
    }

    /* renamed from: org.telegram.ui.ContactsActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends SizeNotifierFrameLayout {
        final /* synthetic */ ContactsActivity this$0;

        public AnonymousClass5(ContactsActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r6) {
            if (Build.VERSION.SDK_INT >= 31) goto L5;
        L23:
            super.dispatchDraw(r6);
            return;
        L5:
            if (ContactsActivity.m14069$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0) == null) goto L23;
            ContactsActivity.m14083$$Nest$mblur3_InvalidateBlur(this.this$0);
            int r0 = getMeasuredWidth();
            int r1 = getMeasuredHeight();
            if (ContactsActivity.m14063$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0) == null) goto L15;
            if (ContactsActivity.m14063$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).inRecording() == true) goto L15;
            RecordingCanvas r2 = ContactsActivity.m14063$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).beginRecording(r0, r1);
            r2.drawColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
            if (SharedConfig.chatBlurEnabled() == false) goto L13;
            ContactsActivity.m14069$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).draw(r2, -3);
        L13:
            ContactsActivity.m14063$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).endRecording();
        L15:
            if (ContactsActivity.m14062$$Nest$fgetiBlur3SourceGlass(this.this$0) != null) goto L17;
        L22:
            ContactsActivity.m14078$$Nest$fputiBlur3Invalidated(this.this$0, false);
            goto L23
        L17:
            if (ContactsActivity.m14062$$Nest$fgetiBlur3SourceGlass(this.this$0).inRecording() == true) goto L22;
            RecordingCanvas r02 = ContactsActivity.m14062$$Nest$fgetiBlur3SourceGlass(this.this$0).beginRecording(r0, r1);
            r02.drawColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
            if (SharedConfig.chatBlurEnabled() == false) goto L21;
            ContactsActivity.m14069$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).draw(r02, -2);
        L21:
            ContactsActivity.m14062$$Nest$fgetiBlur3SourceGlass(this.this$0).endRecording();
            goto L22
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public void drawBlurRect(Canvas r7, float r8, Rect r9, Paint r10, boolean r11) {
            if (Build.VERSION.SDK_INT < 29) goto L11;
            if (SharedConfig.chatBlurEnabled() == false) goto L11;
            if (ContactsActivity.m14063$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0) == null) goto L11;
            r7.save();
            r7.translate(0.0f, -r8);
            ContactsActivity.m14063$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).draw(r7, r9.left, r9.top + r8, r9.right, r9.bottom + r8);
            r7.restore();
            int r6 = r10.getAlpha();
            r10.setAlpha(178);
            r7.drawRect(r9, r10);
            r10.setAlpha(r6);
            return;
        L11:
            r7.drawRect(r9, r10);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            ContactsActivity.m14084$$Nest$mcheckUi_emptyView(this.this$0);
            ContactsActivity.m14088$$Nest$mcheckUi_searchButton(this.this$0);
            ContactsActivity.m14091$$Nest$mcheckUi_sortItem(this.this$0);
            ContactsActivity.m14085$$Nest$mcheckUi_floatingButtonPosition(this.this$0);
            ContactsActivity.m14090$$Nest$mcheckUi_searchFieldY(this.this$0);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r8, int r9) {
            measureChildWithMargins(ContactsActivity.access$100(this.this$0), r8, 0, r9, 0);
            ViewGroup.MarginLayoutParams r7 = (ViewGroup.MarginLayoutParams) ContactsActivity.m14059$$Nest$fgetemptyView(this.this$0).getLayoutParams();
            r7.topMargin = ContactsActivity.access$200(this.this$0).getMeasuredHeight() + AndroidUtilities.dp(48.0f);
            ViewGroup.MarginLayoutParams r72 = (ViewGroup.MarginLayoutParams) ContactsActivity.m14061$$Nest$fgetheaderShadowView(this.this$0).getLayoutParams();
            r72.topMargin = ContactsActivity.access$300(this.this$0).getMeasuredHeight();
            ContactsActivity.m14087$$Nest$mcheckUi_listViewPadding(this.this$0);
            super.onMeasure(r8, r9);
        }
    }

    /* renamed from: org.telegram.ui.ContactsActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends RecyclerView.OnScrollListener {
        private boolean lastScrollToDown;
        private boolean scrollUpdated;
        private boolean scrollingManually;
        final /* synthetic */ ContactsActivity this$0;

        public AnonymousClass6(ContactsActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 == 1) goto L5;
            this.scrollingManually = false;
            return;
        L5:
            if (ContactsActivity.m14073$$Nest$fgetsearching(this.this$0) == false) goto L9;
            if (ContactsActivity.m14072$$Nest$fgetsearchWas(this.this$0) == false) goto L9;
        L10:
            AndroidUtilities.hideKeyboard(this.this$0.getParentActivity().getCurrentFocus());
        L11:
            this.scrollingManually = true;
            return;
        L9:
            if (ContactsActivity.m14070$$Nest$fgetsearchField(this.this$0).editText.isFocused() == false) goto L11;
            goto L10
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r7, int r8, int r9) {
            int r0 = ContactsActivity.m14064$$Nest$fgetlayoutManager(this.this$0).findFirstVisibleItemPosition();
            boolean r1 = false;
            View r2 = r7.getChildAt(0);
            if (r2 == null) goto L5;
            int r22 = r2.getTop();
        L7:
            if (ContactsActivity.m14060$$Nest$fgetfloatingButton(this.this$0) != null) goto L9;
        L28:
            if (r0 == 0) goto L30;
        L33:
            boolean r72 = true;
        L34:
            ContactsActivity.m14061$$Nest$fgetheaderShadowView(this.this$0).setShadowVisible(r72, true);
            if (r9 >= 0) goto L37;
            r1 = true;
        L37:
            this.lastScrollToDown = r1;
            if (Build.VERSION.SDK_INT >= 31) goto L40;
        L42:
            ContactsActivity.m14090$$Nest$mcheckUi_searchFieldY(this.this$0);
            return;
        L40:
            if (ContactsActivity.m14069$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0) == null) goto L42;
            ContactsActivity.m14069$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).onScrolled(r8, r9);
            ContactsActivity.m14083$$Nest$mblur3_InvalidateBlur(this.this$0);
            goto L42
        L30:
            if (r22 < ContactsActivity.m14065$$Nest$fgetlistView(this.this$0).getPaddingTop()) goto L33;
            r72 = false;
            goto L34
        L9:
            if (ContactsActivity.m14073$$Nest$fgetsearching(this.this$0) == true) goto L28;
            boolean r73 = r7.canScrollVertically(1);
            if (r9 <= 0) goto L13;
            boolean r3 = true;
        L14:
            if (r9 != 0) goto L16;
        L27:
            this.scrollUpdated = true;
            goto L28
        L16:
            if (this.scrollUpdated == false) goto L27;
            if (r3 == false) goto L19;
        L20:
            ContactsActivity r5 = this.this$0;
            if (r3 == false) goto L25;
            if (r73 == false) goto L25;
            boolean r74 = false;
        L26:
            ContactsActivity.m14077$$Nest$fputfloatingButtonVisibleByScroll(r5, r74);
            ContactsActivity.m14086$$Nest$mcheckUi_floatingButtonVisible(this.this$0);
        L25:
            r74 = true;
            goto L26
        L19:
            if (this.scrollingManually == false) goto L27;
        L13:
            r3 = false;
            goto L14
        L5:
            r22 = 0;
            goto L7
        }
    }

    /* renamed from: org.telegram.ui.ContactsActivity$7, reason: invalid class name */
    public class AnonymousClass7 implements Bulletin.Delegate {
        final /* synthetic */ ContactsActivity this$0;

        public AnonymousClass7(ContactsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getBottomOffset(int r1) {
            return ContactsActivity.m14067$$Nest$fgetnavigationBarHeight(this.this$0) + ContactsActivity.m14057$$Nest$fgetadditionFloatingButtonOffset(this.this$0);
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public void onBottomOffsetChange(float r3) {
            ContactsActivity.m14076$$Nest$fputadditionalFloatingTranslation(this.this$0, Math.max(0.0f, (r3 - ContactsActivity.m14067$$Nest$fgetnavigationBarHeight(r0)) - ContactsActivity.m14057$$Nest$fgetadditionFloatingButtonOffset(this.this$0)));
            ContactsActivity.m14085$$Nest$mcheckUi_floatingButtonPosition(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.ContactsActivity$8, reason: invalid class name */
    public class AnonymousClass8 implements TextWatcher {
        final /* synthetic */ ContactsActivity this$0;
        final /* synthetic */ EditText val$editTextFinal;

        public AnonymousClass8(ContactsActivity r1, EditText r2) {
            this.this$0 = r1;
            this.val$editTextFinal = r2;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r4) {
            String r42 = r4.toString();     // Catch: Exception -> L17
            if (r42.isEmpty() == true) goto L21;
            int r1 = Utilities.parseInt(r42);     // Catch: Exception -> L17
            if (r1 >= 0) goto L10;
            this.val$editTextFinal.setText(MVEL.VERSION_SUB);     // Catch: Exception -> L17
            EditText r3 = this.val$editTextFinal;     // Catch: Exception -> L17
            r3.setSelection(r3.length());     // Catch: Exception -> L17
            return;
        L10:
            if (r1 <= 300) goto L14;
            this.val$editTextFinal.setText("300");     // Catch: Exception -> L17
            EditText r32 = this.val$editTextFinal;     // Catch: Exception -> L17
            r32.setSelection(r32.length());     // Catch: Exception -> L17
            return;
        L14:
            if (r42.equals(_UrlKt.FRAGMENT_ENCODE_SET + r1) == true) goto L22;
            this.val$editTextFinal.setText(_UrlKt.FRAGMENT_ENCODE_SET + r1);     // Catch: Exception -> L17
            EditText r33 = this.val$editTextFinal;     // Catch: Exception -> L17
            r33.setSelection(r33.length());     // Catch: Exception -> L17
            return;
        L22:
            return;
        L21:
            return;
        L17:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.ContactsActivity$9, reason: invalid class name */
    public class AnonymousClass9 implements Runnable {
        final /* synthetic */ ContactsActivity this$0;

        public AnonymousClass9(ContactsActivity r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            ContactsActivity.m14066$$Nest$fgetlistViewAdapter(this.this$0).sortOnlineContacts();
            this.this$0.scheduled = false;
        }
    }

    public interface ContactsActivityDelegate {
        void didSelectContact(TLRPC.User r1, String r2, ContactsActivity r3);
    }

    /* renamed from: $r8$lambda$5tg-Skt40gG1lGkoQaEth-qZWZw, reason: not valid java name */
    public static /* synthetic */ void m14051$r8$lambda$5tgSkt40gG1lGkoQaEthqZWZw(ContactsActivity r0, ContactsActivity r1) {
        r0.lambda$showItemOptions$16(r1);
    }

    public static /* synthetic */ WindowInsetsCompat $r8$lambda$BF0NfsEeOKN7NtOPc9sH0L8i8RI(ContactsActivity r0, View r1, WindowInsetsCompat r2) {
        return r0.onApplyWindowInsets(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$JKMMNZVbhnScE5UBvbHjndBG4o0(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$K2BXzwbskWm3G5CJpLv2R7JGyhI(ContactsActivity r0) {
        r0.lambda$createView$4();
    }

    public static /* synthetic */ void $r8$lambda$KDhYSmylBGemapyKIwGE0Fkg3xM(ContactsActivity r0, int r1, View r2, int r3, float r4, float r5) {
        r0.lambda$createView$6(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$L8nP2YjOEjQZvHFeoyfcJhXnR-Q, reason: not valid java name */
    public static /* synthetic */ void m14052$r8$lambda$L8nP2YjOEjQZvHFeoyfcJhXnRQ(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$PWelELtJfhhoz0KTNKChWG3RTXE(ContactsActivity r0, ContactsActivity r1) {
        r0.lambda$showItemOptions$17(r1);
    }

    /* renamed from: $r8$lambda$QNdpKz_fOXdhyoz9WHw-8yWIIJo, reason: not valid java name */
    public static /* synthetic */ void m14053$r8$lambda$QNdpKz_fOXdhyoz9WHw8yWIIJo(ContactsActivity r0, String r1, AlertDialog r2, int r3) {
        r0.lambda$createView$5(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$SKM9hcjcYOv0C2T1dUFezTE1TnA(ContactsActivity r0, int r1) {
        r0.lambda$askForPermissons$14(r1);
    }

    public static /* synthetic */ void $r8$lambda$TU6iJXh5kpJIZwwqiWJ_WdZUkRc(ContactsActivity r0, View r1) {
        r0.lambda$createView$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$TXk57Rtq5qgMLLjMPYDRNhSVdkM(ContactsActivity r0, TLRPC.User r1, String r2, AlertDialog r3, int r4) {
        r0.lambda$didSelectResult$11(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$WpJEgVzgAgmXhx9eDfQFfj1dRkI(ContactsActivity r0, View r1) {
        r0.lambda$createView$8(r1);
    }

    /* renamed from: $r8$lambda$_mh1k7NOStFCyTJ4WijImlA-pI0, reason: not valid java name */
    public static /* synthetic */ void m14054$r8$lambda$_mh1k7NOStFCyTJ4WijImlApI0(ContactsActivity r0) {
        r0.blur3_InvalidateBlur();
    }

    public static /* synthetic */ void $r8$lambda$hfbGnUoBMY_WMbxVvvibYPPlmYM(ContactsActivity r0, View r1) {
        r0.lambda$createView$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$j6GalSEx282k5KZoowilW0kBrY4(ContactsActivity r0) {
        r0.lambda$getThemeDescriptions$15();
    }

    public static /* synthetic */ void $r8$lambda$jDLyVIJr9z0ErWIuZKUFNUYfIsQ(ContactsActivity r0, int r1) {
        r0.lambda$onBecomeFullyVisible$13(r1);
    }

    public static /* synthetic */ void $r8$lambda$mu63MqzroJbiOxbXfWinYfrvdC8(ContactsActivity r0, TLRPC.User r1, EditText r2, AlertDialog r3, int r4) {
        r0.lambda$didSelectResult$12(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$phR3lbdMVIHeu-codY8QAn8SO1g, reason: not valid java name */
    public static /* synthetic */ void m14055$r8$lambda$phR3lbdMVIHeucodY8QAn8SO1g(ContactsActivity r0, AlertDialog r1, int r2) {
        r0.lambda$performSelectedContactsDelete$9(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$qxl6pCycibn89My4yJlxl__aocc(ContactsActivity r0) {
        r0.lambda$createView$3();
    }

    /* renamed from: $r8$lambda$xQ5nBuNqyXlEL4HmIW890h2fv-Q, reason: not valid java name */
    public static /* synthetic */ boolean m14056$r8$lambda$xQ5nBuNqyXlEL4HmIW890h2fvQ(ContactsActivity r0, View r1, int r2) {
        return r0.lambda$createView$7(r1, r2);
    }

    /* renamed from: -$$Nest$fgetadditionFloatingButtonOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14057$$Nest$fgetadditionFloatingButtonOffset(ContactsActivity r0) {
        return r0.additionFloatingButtonOffset;
    }

    /* renamed from: -$$Nest$fgetanimatorSearchHasQuery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BoolAnimator m14058$$Nest$fgetanimatorSearchHasQuery(ContactsActivity r0) {
        return r0.animatorSearchHasQuery;
    }

    /* renamed from: -$$Nest$fgetemptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StickerEmptyView m14059$$Nest$fgetemptyView(ContactsActivity r0) {
        return r0.emptyView;
    }

    /* renamed from: -$$Nest$fgetfloatingButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FragmentFloatingButton m14060$$Nest$fgetfloatingButton(ContactsActivity r0) {
        return r0.floatingButton;
    }

    /* renamed from: -$$Nest$fgetheaderShadowView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HeaderShadowView m14061$$Nest$fgetheaderShadowView(ContactsActivity r0) {
        return r0.headerShadowView;
    }

    /* renamed from: -$$Nest$fgetiBlur3SourceGlass, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundSourceRenderNode m14062$$Nest$fgetiBlur3SourceGlass(ContactsActivity r0) {
        return r0.iBlur3SourceGlass;
    }

    /* renamed from: -$$Nest$fgetiBlur3SourceGlassFrosted, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundSourceRenderNode m14063$$Nest$fgetiBlur3SourceGlassFrosted(ContactsActivity r0) {
        return r0.iBlur3SourceGlassFrosted;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayoutManager m14064$$Nest$fgetlayoutManager(ContactsActivity r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m14065$$Nest$fgetlistView(ContactsActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetlistViewAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ContactsAdapter m14066$$Nest$fgetlistViewAdapter(ContactsActivity r0) {
        return r0.listViewAdapter;
    }

    /* renamed from: -$$Nest$fgetnavigationBarHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14067$$Nest$fgetnavigationBarHeight(ContactsActivity r0) {
        return r0.navigationBarHeight;
    }

    /* renamed from: -$$Nest$fgetneedPhonebook, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14068$$Nest$fgetneedPhonebook(ContactsActivity r0) {
        return r0.needPhonebook;
    }

    /* renamed from: -$$Nest$fgetscrollableViewNoiseSuppressor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DownscaleScrollableNoiseSuppressor m14069$$Nest$fgetscrollableViewNoiseSuppressor(ContactsActivity r0) {
        return r0.scrollableViewNoiseSuppressor;
    }

    /* renamed from: -$$Nest$fgetsearchField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FragmentSearchField m14070$$Nest$fgetsearchField(ContactsActivity r0) {
        return r0.searchField;
    }

    /* renamed from: -$$Nest$fgetsearchListViewAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SearchAdapter m14071$$Nest$fgetsearchListViewAdapter(ContactsActivity r0) {
        return r0.searchListViewAdapter;
    }

    /* renamed from: -$$Nest$fgetsearchWas, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14072$$Nest$fgetsearchWas(ContactsActivity r0) {
        return r0.searchWas;
    }

    /* renamed from: -$$Nest$fgetsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14073$$Nest$fgetsearching(ContactsActivity r0) {
        return r0.searching;
    }

    /* renamed from: -$$Nest$fgetsortByName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14074$$Nest$fgetsortByName(ContactsActivity r0) {
        return r0.sortByName;
    }

    /* renamed from: -$$Nest$fgetsortItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m14075$$Nest$fgetsortItem(ContactsActivity r0) {
        return r0.sortItem;
    }

    /* renamed from: -$$Nest$fputadditionalFloatingTranslation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14076$$Nest$fputadditionalFloatingTranslation(ContactsActivity r0, float r1) {
        r0.additionalFloatingTranslation = r1;
    }

    /* renamed from: -$$Nest$fputfloatingButtonVisibleByScroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14077$$Nest$fputfloatingButtonVisibleByScroll(ContactsActivity r0, boolean r1) {
        r0.floatingButtonVisibleByScroll = r1;
    }

    /* renamed from: -$$Nest$fputiBlur3Invalidated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14078$$Nest$fputiBlur3Invalidated(ContactsActivity r0, boolean r1) {
        r0.iBlur3Invalidated = r1;
    }

    /* renamed from: -$$Nest$fputsearchQuery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14079$$Nest$fputsearchQuery(ContactsActivity r0, String r1) {
        r0.searchQuery = r1;
    }

    /* renamed from: -$$Nest$fputsearchWas, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14080$$Nest$fputsearchWas(ContactsActivity r0, boolean r1) {
        r0.searchWas = r1;
    }

    /* renamed from: -$$Nest$fputsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14081$$Nest$fputsearching(ContactsActivity r0, boolean r1) {
        r0.searching = r1;
    }

    /* renamed from: -$$Nest$fputsortByName, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14082$$Nest$fputsortByName(ContactsActivity r0, boolean r1) {
        r0.sortByName = r1;
    }

    /* renamed from: -$$Nest$mblur3_InvalidateBlur, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14083$$Nest$mblur3_InvalidateBlur(ContactsActivity r0) {
        r0.blur3_InvalidateBlur();
    }

    /* renamed from: -$$Nest$mcheckUi_emptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14084$$Nest$mcheckUi_emptyView(ContactsActivity r0) {
        r0.checkUi_emptyView();
    }

    /* renamed from: -$$Nest$mcheckUi_floatingButtonPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14085$$Nest$mcheckUi_floatingButtonPosition(ContactsActivity r0) {
        r0.checkUi_floatingButtonPosition();
    }

    /* renamed from: -$$Nest$mcheckUi_floatingButtonVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14086$$Nest$mcheckUi_floatingButtonVisible(ContactsActivity r0) {
        r0.checkUi_floatingButtonVisible();
    }

    /* renamed from: -$$Nest$mcheckUi_listViewPadding, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14087$$Nest$mcheckUi_listViewPadding(ContactsActivity r0) {
        r0.checkUi_listViewPadding();
    }

    /* renamed from: -$$Nest$mcheckUi_searchButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14088$$Nest$mcheckUi_searchButton(ContactsActivity r0) {
        r0.checkUi_searchButton();
    }

    /* renamed from: -$$Nest$mcheckUi_searchFieldHint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14089$$Nest$mcheckUi_searchFieldHint(ContactsActivity r0) {
        r0.checkUi_searchFieldHint();
    }

    /* renamed from: -$$Nest$mcheckUi_searchFieldY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14090$$Nest$mcheckUi_searchFieldY(ContactsActivity r0) {
        r0.checkUi_searchFieldY();
    }

    /* renamed from: -$$Nest$mcheckUi_sortItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14091$$Nest$mcheckUi_sortItem(ContactsActivity r0) {
        r0.checkUi_sortItem();
    }

    /* renamed from: -$$Nest$mhideActionMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14092$$Nest$mhideActionMode(ContactsActivity r0) {
        r0.hideActionMode();
    }

    /* renamed from: -$$Nest$mperformSelectedContactsDelete, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14093$$Nest$mperformSelectedContactsDelete(ContactsActivity r0) {
        r0.performSelectedContactsDelete();
    }

    public ContactsActivity(Bundle r11) {
        super(r11);
        int r112 = Build.VERSION.SDK_INT;
        if (r112 < 31) goto L5;
        int r2 = 48;
    L6:
        this.ADDITIONAL_LIST_HEIGHT_DP = r2;
        CubicBezierInterpolator r6 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.animatorSearchFieldVisible = new BoolAnimator(0, this, r6, 350);
        this.animatorSearchHasQuery = new BoolAnimator(2, this, r6, 350);
        this.phonebookRow = 0;
        this.floatingButtonVisibleByScroll = true;
        this.allowSelf = true;
        this.allowBots = true;
        this.needForwardCount = true;
        this.needFinishFragment = true;
        this.resetDelegate = true;
        this.selectAlertString = null;
        this.allowUsernameSearch = true;
        this.askAboutContacts = true;
        this.selectedContacts = new LongSparseArray();
        this.checkPermission = true;
        this.sortContactsRunnable = new AnonymousClass9(this);
        ArrayList<RectF> r10 = new ArrayList();
        this.iBlur3Positions = r10;
        RectF r22 = new RectF();
        this.iBlur3PositionActionBar = r22;
        RectF r3 = new RectF();
        this.iBlur3PositionMainTabs = r3;
        r10.add(r22);
        r10.add(r3);
        if (r112 < 31) goto L10;
        this.scrollableViewNoiseSuppressor = new DownscaleScrollableNoiseSuppressor();
        this.iBlur3SourceGlassFrosted = new BlurredBackgroundSourceRenderNode(null);
        this.iBlur3SourceGlass = new BlurredBackgroundSourceRenderNode(null);
        return;
    L10:
        this.scrollableViewNoiseSuppressor = null;
        this.iBlur3SourceGlassFrosted = null;
        this.iBlur3SourceGlass = null;
        return;
    L5:
        r2 = 0;
        goto L6
    }

    public static /* synthetic */ ActionBar access$000(ContactsActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$100(ContactsActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$200(ContactsActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$300(ContactsActivity r0) {
        return r0.actionBar;
    }

    @TargetApi(23)
    private void askForPermissons(boolean r5) {
        Activity r0 = getParentActivity();
        if (r0 != null) goto L5;
        return;
    L5:
        if (UserConfig.getInstance(this.currentAccount).syncContacts == true) goto L7;
        return;
    L7:
        if (r0.checkSelfPermission("android.permission.READ_CONTACTS") == 0) goto L24;
        if (r5 == true) goto L11;
    L14:
        this.permissionRequestTime = SystemClock.elapsedRealtime();
        ArrayList r4 = new ArrayList();
        r4.add("android.permission.READ_CONTACTS");
        r4.add("android.permission.WRITE_CONTACTS");
        r4.add("android.permission.GET_ACCOUNTS");
        r0.requestPermissions((String[]) r4.toArray(new String[0]), 1);     // Catch: Exception -> L17
        return;
    L17:
        e = move-exception;
        FileLog.e(e);
        return;
    L11:
        if (this.askAboutContacts == false) goto L14;
        showDialog(AlertsCreator.createContactsPermissionDialog(r0, new ContactsActivity$$ExternalSyntheticLambda11(this)).create());
        return;
    }

    private void blur3_InvalidateBlur() {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        return;
    L5:
        if (this.scrollableViewNoiseSuppressor == null) goto L20;
        int r1 = AndroidUtilities.dp(48.0f);
        int r2 = AndroidUtilities.dp(48.0f);
        this.iBlur3PositionActionBar.set(0.0f, -r1, this.fragmentView.getMeasuredWidth(), (this.actionBar.getMeasuredHeight() + r1) + r2);
        MainTabsUiHelper.setBlurBounds(this.iBlur3PositionMainTabs, this.fragmentView, this.navigationBarHeight);
        RectF r12 = this.iBlur3PositionMainTabs;
        if (LiteMode.isEnabled(262144) == false) goto L10;
        float r0 = 0.0f;
    L11:
        r12.inset(0.0f, r0);
        DownscaleScrollableNoiseSuppressor r02 = this.scrollableViewNoiseSuppressor;
        ArrayList<RectF> r13 = this.iBlur3Positions;
        if (this.hasMainTabs == true) goto L14;
    L16:
        int r22 = 1;
    L17:
        r02.setupRenderNodes(r13, r22);
        this.scrollableViewNoiseSuppressor.invalidateResultRenderNodes(this.iBlur3Capture, this.fragmentView.getMeasuredWidth(), this.fragmentView.getMeasuredHeight());
        return;
    L14:
        if (BottomNavigationBar.visible() == false) goto L16;
        r22 = 2;
        goto L17
    L10:
        r0 = -AndroidUtilities.dp(48.0f);
        goto L11
    }

    private void checkUi_emptyView() {
        StickerEmptyView r0 = this.emptyView;
        if (r0 == null) goto L6;
        r0.setKeyboardHeight(Math.max(this.navigationBarHeight + this.additionNavigationBarHeight, this.imeInsetAnimatedHeight), false);
        return;
    }

    private void checkUi_floatingButtonPosition() {
        FragmentFloatingButton r0 = this.floatingButton;
        if (r0 == null) goto L6;
        r0.setTranslationY(((-this.navigationBarHeight) - this.additionFloatingButtonOffset) - this.additionalFloatingTranslation);
        return;
    }

    private void checkUi_floatingButtonVisible() {
        FragmentFloatingButton r0 = this.floatingButton;
        boolean r1 = false;
        if (r0 == null) goto L16;
        ContactsAdapter r3 = this.listViewAdapter;
        if (r3 == null) goto L16;
        if (this.floatingButtonVisibleByScroll == true) goto L9;
    L13:
        boolean r32 = false;
    L14:
        r0.setButtonVisible(r32, true);
        goto L16
    L9:
        if (this.searching == true) goto L13;
        if (r3.isEmpty() == true) goto L13;
        r32 = true;
    L16:
        if (this.mainTabsActivityController != null) goto L18;
        return;
    L18:
        if (BottomNavigationBar.visible() == true) goto L20;
    L26:
        this.mainTabsActivityController.setTabsVisible(r1);
        return;
    L20:
        if (BottomNavigationBar.floating() == false) goto L24;
        if (this.floatingButtonVisibleByScroll == false) goto L26;
    L24:
        if (this.searching == true) goto L26;
        r1 = true;
        goto L26
    }

    private void checkUi_listViewPadding() {
        this.listView.setPadding(0, AndroidUtilities.dp(this.ADDITIONAL_LIST_HEIGHT_DP + 44) + this.actionBar.getMeasuredHeight(), 0, ((AndroidUtilities.dp(this.ADDITIONAL_LIST_HEIGHT_DP) + this.navigationBarHeight) + this.additionNavigationBarHeight) + MainTabsUiHelper.getFloatingTabsPadding(this.hasMainTabs));
    }

    private void checkUi_searchButton() {
        float r0 = (1.0f - this.animatorSearchFieldVisible.getFloatValue()) * (1.0f - this.animatorSearchHasQuery.getFloatValue());
        FragmentFloatingButton.setAnimatedVisibility(this.searchItem, r0);
    }

    private void checkUi_searchFieldHint() {
        ContactsAdapter r0 = this.listViewAdapter;
        if (r0 != null) goto L5;
    L7:
        boolean r02 = false;
    L9:
        if (this.lastIsEmpty == r02) goto L11;
    L14:
        EditTextBoldCursor r1 = this.searchField.editText;
        if (r02 == false) goto L17;
        int r2 = R.string.SearchPeopleByUsername;
    L18:
        r1.setHint(LocaleController.getString(r2));
        EditTextBoldCursor r12 = this.searchField.editText;
        if (r02 == false) goto L21;
        int r22 = R.string.SearchPeopleByUsername;
    L22:
        r12.setContentDescription(LocaleController.getString(r22));
        this.lastIsEmpty = r02;
        return;
    L21:
        r22 = R.string.SearchContacts;
        goto L22
    L17:
        r2 = R.string.SearchContacts;
        goto L18
    L11:
        if (TextUtils.isEmpty(this.searchField.editText.getHint()) == true) goto L14;
        return;
    L5:
        if (r0.isEmpty() == false) goto L7;
        r02 = true;
        goto L9
    }

    private void checkUi_searchFieldY() {
        float r0 = this.listView.getY() + this.listView.getPaddingTop();
        boolean r1 = false;
        int r2 = 0;
    L4:
        if (r2 >= this.listView.getChildCount()) goto L15;
        View r3 = this.listView.getChildAt(r2);
        int r4 = this.listView.getChildAdapterPosition(r3);
        if (r4 == 0) goto L7;
        if (r4 > 0) goto L13;
        r2 = r2 + 1;
        goto L4
    L13:
        r0 = -AndroidUtilities.dp(52.0f);
        goto L15
    L7:
        RecyclerView.ItemDecoration r02 = this.listView.getItemDecorationAt(r2);
        Rect r22 = AndroidUtilities.rectTmp2;
        RecyclerListView r42 = this.listView;
        r02.getItemOffsets(r22, r3, r42, r42.mState);
        float r03 = this.listView.getY();
        float r32 = r3.getY();
        if (this.listViewAdapter.isEmptyWithMainTabs == false) goto L10;
        int r23 = 0;
    L11:
        r0 = r03 + (r32 - r23);
        goto L15
    L10:
        r23 = r22.top;
    L15:
        this.searchField.setTranslationY(AndroidUtilities.lerp(r0, this.listView.getY() + this.listView.getPaddingTop(), this.animatorSearchHasQuery.getFloatValue()) - AndroidUtilities.dp(48.0f));
        BoolAnimator r24 = this.animatorSearchFieldVisible;
        if (r0 <= ((this.listView.getY() + this.listView.getPaddingTop()) - AndroidUtilities.dp(12.0f))) goto L18;
        r1 = true;
    L18:
        r24.setValue(r1, true);
    }

    private void checkUi_sortItem() {
        float r1 = 1.0f;
        float r0 = 1.0f - this.animatorSearchHasQuery.getFloatValue();
        ContactsAdapter r2 = this.listViewAdapter;
        if (r2 != null) goto L5;
    L6:
        r1 = 0.0f;
    L7:
        ActionBarMenuItem r3 = this.sortItem;
        FragmentFloatingButton.setAnimatedVisibility(r3, r0 * r1);
        return;
    L5:
        if (r2.isEmpty() == false) goto L7;
        goto L6
    }

    private void didSelectResult(TLRPC.User r6, boolean r7, String r8) {
        if (r7 == true) goto L5;
    L42:
        ContactsActivityDelegate r72 = this.delegate;
        if (r72 == null) goto L48;
        r72.didSelectContact(r6, r8, this);
        if (this.resetDelegate == false) goto L48;
        this.delegate = null;
    L48:
        if (this.needFinishFragment == false) goto L54;
        finishFragment();
        return;
    L54:
        return;
    L5:
        if (this.selectAlertString == null) goto L42;
        if (getParentActivity() != null) goto L10;
        return;
    L10:
        if (r6.bot == true) goto L12;
    L26:
        AlertDialog.Builder r73 = new AlertDialog.Builder(getParentActivity());
        r73.setTitle(LocaleController.getString(R.string.AppName));
        String r82 = LocaleController.formatStringSimple(this.selectAlertString, new Object[]{UserObject.getUserName(r6)});
        if (r6.bot == false) goto L29;
    L31:
        EditTextBoldCursor r1 = null;
    L32:
        r73.setMessage(r82);
        r73.setPositiveButton(LocaleController.getString(R.string.OK), new ContactsActivity$$ExternalSyntheticLambda19(this, r6, r1));
        r73.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        showDialog(r73.create());
        if (r1 == null) goto L53;
        ViewGroup.MarginLayoutParams r5 = (ViewGroup.MarginLayoutParams) r1.getLayoutParams();
        if (r5 != null) goto L37;
    L40:
        r1.setSelection(r1.getText().length());
        return;
    L37:
        if ((r5 instanceof FrameLayout.LayoutParams) == false) goto L39;
        ((FrameLayout.LayoutParams) r5).gravity = 1;
    L39:
        int r62 = AndroidUtilities.dp(24.0f);
        r5.leftMargin = r62;
        r5.rightMargin = r62;
        r5.height = AndroidUtilities.dp(36.0f);
        r1.setLayoutParams(r5);
        goto L40
    L53:
        return;
    L29:
        if (this.needForwardCount == false) goto L31;
        r82 = String.format("%s\n\n%s", new Object[]{r82, LocaleController.getString(R.string.AddToTheGroupForwardCount)});
        r1 = new EditTextBoldCursor(getParentActivity());
        r1.setTextSize(1, 18.0f);
        r1.setText("50");
        r1.setTextColor(getThemedColor(Theme.key_dialogTextBlack));
        r1.setGravity(17);
        r1.setInputType(2);
        r1.setImeOptions(6);
        r1.setBackground(Theme.createEditTextDrawable(getParentActivity(), true));
        r1.addTextChangedListener(new AnonymousClass8(this, r1));
        r73.setView(r1);
        goto L32
    L12:
        if (r6.bot_nochats == false) goto L19;
        BulletinFactory.of(this).createErrorBulletin(LocaleController.getString(R.string.BotCantJoinGroups)).show();     // Catch: Exception -> L15
        return;
    L15:
        e = move-exception;
        FileLog.e(e);
        return;
    L19:
        if (this.channelId == 0) goto L26;
        TLRPC.Chat r74 = getMessagesController().getChat(Long.valueOf(this.channelId));
        AlertDialog.Builder r12 = new AlertDialog.Builder(getParentActivity());
        if (ChatObject.canAddAdmins(r74) == false) goto L23;
        r12.setTitle(LocaleController.getString(R.string.AddBotAdminAlert));
        r12.setMessage(LocaleController.getString(R.string.AddBotAsAdmin));
        r12.setPositiveButton(LocaleController.getString(R.string.AddAsAdmin), new ContactsActivity$$ExternalSyntheticLambda18(this, r6, r8));
        r12.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
    L24:
        showDialog(r12.create());
        return;
    L23:
        r12.setMessage(LocaleController.getString(R.string.CantAddBotAsAdmin));
        r12.setPositiveButton(LocaleController.getString(R.string.OK), null);
        goto L24
    }

    private void hideActionMode() {
        this.actionBar.hideActionMode();
        int r0 = this.listView.getChildCount();
        int r2 = 0;
    L4:
        if (r2 >= r0) goto L16;
        View r4 = this.listView.getChildAt(r2);
        if ((r4 instanceof UserCell) == false) goto L11;
        UserCell r42 = (UserCell) r4;
        if (this.selectedContacts.indexOfKey(r42.getDialogId()) < 0) goto L15;
        r42.setChecked(false, true);
    L15:
        r2 = r2 + 1;
        goto L4
    L11:
        if ((r4 instanceof ProfileSearchCell) == false) goto L15;
        ProfileSearchCell r43 = (ProfileSearchCell) r4;
        if (this.selectedContacts.indexOfKey(r43.getDialogId()) < 0) goto L15;
        r43.setChecked(false, true);
        goto L15
    L16:
        this.selectedContacts.clear();
        this.backDrawable.setRotation(0.0f, true);
    }

    private /* synthetic */ void lambda$askForPermissons$14(int r5) {
        MessagesController.getGlobalNotificationsSettings().edit().putBoolean("askAboutContacts2", false).commit();
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.contactsPermissionBadgeCheck, new Object[0]);
        if (r5 == 0) goto L5;
        boolean r0 = true;
    L6:
        this.askAboutContacts = r0;
        if (r5 != 0) goto L9;
        return;
    L9:
        askForPermissons(false);
        return;
    L5:
        r0 = false;
        goto L6
    }

    private /* synthetic */ void lambda$createView$0(View r1) {
        hideActionMode();
    }

    private /* synthetic */ void lambda$createView$2(View r1) {
        showItemOptions();
    }

    private /* synthetic */ void lambda$createView$3() {
        blur3_InvalidateBlur();
    }

    private /* synthetic */ void lambda$createView$4() {
        this.listView.postOnAnimation(new ContactsActivity$$ExternalSyntheticLambda14(this));
    }

    private /* synthetic */ void lambda$createView$5(String r3, AlertDialog r4, int r5) {
        Intent r42 = new Intent("android.intent.action.VIEW", Uri.fromParts("sms", r3, null));     // Catch: Exception -> L4
        r42.putExtra("sms_body", ContactsController.getInstance(this.currentAccount).getInviteText(1));     // Catch: Exception -> L4
        getParentActivity().startActivityForResult(r42, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ void lambda$createView$6(int r6, View r7, int r8, float r9, float r10) {
        RecyclerView.Adapter r92 = this.listView.getAdapter();
        SearchAdapter r102 = this.searchListViewAdapter;
        if (r92 == r102) goto L5;
        ContactsAdapter r93 = this.listViewAdapter;
        if (r93.includeSearch == false) goto L63;
        if (r8 == 0) goto L176;
        r8 = r8 - 1;
        goto L63
    L176:
        return;
    L63:
        int r94 = r93.getSectionForPosition(r8);
        int r103 = this.listViewAdapter.getPositionInSectionForPosition(r8);
        if (r103 < 0) goto L165;
        if (r94 >= 0) goto L68;
        return;
    L68:
        if ((r7 instanceof ViewGroup) == false) goto L76;
        if ((((ViewGroup) r7).getChildAt(0) instanceof ContactsEmptyView) == false) goto L76;
        FragmentFloatingButton r5 = this.floatingButton;
        if (r5 == null) goto L166;
        r5.performClick();
        return;
    L166:
        return;
    L76:
        if (this.selectedContacts.isEmpty() == true) goto L82;
        if ((r7 instanceof UserCell) == false) goto L82;
        showOrUpdateActionMode((UserCell) r7);
        return;
    L82:
        if (this.onlyUsers == false) goto L84;
        if (r6 != 0) goto L84;
    L129:
        Object r62 = this.listViewAdapter.getItem(this.listViewAdapter.getSectionForPosition(r8), this.listViewAdapter.getPositionInSectionForPosition(r8));
        if ((r62 instanceof TLRPC.User) == false) goto L149;
        TLRPC.User r63 = (TLRPC.User) r62;
        if (this.returnAsResult == false) goto L141;
        LongSparseArray<TLRPC.User> r72 = this.ignoreUsers;
        if (r72 != null) goto L136;
    L138:
        didSelectResult(r63, true, null);
        return;
    L136:
        if (r72.indexOfKey(r63.id) < 0) goto L138;
        return;
    L141:
        if (this.createSecretChat == false) goto L144;
        this.creatingChat = true;
        SecretChatHelper.getInstance(this.currentAccount).startSecretChat(getParentActivity(), r63);
        return;
    L144:
        Bundle r73 = new Bundle();
        r73.putLong("user_id", r63.id);
        if (getMessagesController().checkCanOpenChat(r73, this) == false) goto L170;
        presentFragment(new ChatActivity(r73), this.needFinishFragment);
        return;
    L170:
        return;
    L149:
        if ((r62 instanceof ContactsController.Contact) == false) goto L171;
        ContactsController.Contact r64 = (ContactsController.Contact) r62;
        if (r64.phones.isEmpty() == true) goto L153;
        String r65 = r64.phones.get(0);
    L154:
        if (r65 != null) goto L156;
        return;
    L156:
        if (getParentActivity() == null) goto L179;
        AlertDialog.Builder r74 = new AlertDialog.Builder(getParentActivity());
        r74.setMessage(LocaleController.getString(R.string.InviteUser));
        r74.setTitle(LocaleController.getString(R.string.AppName));
        r74.setPositiveButton(LocaleController.getString(R.string.OK), new ContactsActivity$$ExternalSyntheticLambda17(this, r65));
        r74.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        showDialog(r74.create());
        return;
    L179:
        return;
    L153:
        r65 = null;
        goto L154
    L171:
        return;
    L84:
        if (r94 != 0) goto L129;
        if (this.needPhonebook == false) goto L97;
        if (r103 == 0) goto L89;
        if (r103 != 1) goto L167;
        presentFragment(new CallLogActivity());
        return;
    L167:
        return;
    L89:
        if (MessagesController.getInstance(this.currentAccount).isFrozen() == false) goto L92;
        AccountFrozenAlert.show(this.currentAccount);
        return;
    L92:
        presentFragment(new InviteContactsActivity());
        return;
    L97:
        if (r6 == 0) goto L109;
        if (r103 == 0) goto L100;
        return;
    L100:
        if (MessagesController.getInstance(this.currentAccount).isFrozen() == false) goto L103;
        AccountFrozenAlert.show(this.currentAccount);
        return;
    L103:
        long r75 = this.chatId;
        if (r75 != 0) goto L107;
        r75 = this.channelId;
    L107:
        presentFragment(new GroupInviteActivity(r75));
        return;
    L109:
        if (r103 == 0) goto L111;
        if (r103 == 1) goto L118;
        return;
    L118:
        if (MessagesController.getInstance(this.currentAccount).isFrozen() == false) goto L121;
        AccountFrozenAlert.show(this.currentAccount);
        return;
    L121:
        SharedPreferences r66 = MessagesController.getGlobalMainSettings();
        if (BuildVars.DEBUG_VERSION == false) goto L124;
    L127:
        presentFragment(new ActionIntroActivity(0));
        r66.edit().putBoolean("channel_intro", true).apply();
        return;
    L124:
        if (r66.getBoolean("channel_intro", false) == false) goto L127;
        Bundle r67 = new Bundle();
        r67.putInt("step", 0);
        presentFragment(new ChannelCreateActivity(r67));
        return;
    L111:
        if (MessagesController.getInstance(this.currentAccount).isFrozen() == false) goto L114;
        AccountFrozenAlert.show(this.currentAccount);
        return;
    L114:
        presentFragment(new GroupCreateActivity(new Bundle()), false);
        return;
    L165:
        return;
    L5:
        if (r102.includeSearch == false) goto L9;
        if (r8 == 0) goto L173;
        r8 = r8 - 1;
        goto L9
    L173:
        return;
    L9:
        Object r68 = r102.getItem(r8);
        if (this.selectedContacts.isEmpty() == true) goto L20;
        if ((r7 instanceof ProfileSearchCell) == false) goto L20;
        ProfileSearchCell r76 = (ProfileSearchCell) r7;
        if (r76.getUser() != null) goto L16;
        return;
    L16:
        if (r76.getUser().contact == false) goto L161;
        showOrUpdateActionMode(r76);
        return;
    L161:
        return;
    L20:
        if ((r68 instanceof TLRPC.User) == false) goto L45;
        TLRPC.User r69 = (TLRPC.User) r68;
        if (this.searchListViewAdapter.isGlobalSearch(r8) == false) goto L25;
        ArrayList<TLRPC.User> r77 = new ArrayList();
        r77.add(r69);
        getMessagesController().putUsers(r77, false);
        MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(r77, null, false, true);
    L25:
        if (this.returnAsResult == false) goto L34;
        LongSparseArray<TLRPC.User> r78 = this.ignoreUsers;
        if (r78 != null) goto L29;
    L31:
        didSelectResult(r69, true, null);
        return;
    L29:
        if (r78.indexOfKey(r69.id) < 0) goto L31;
        return;
    L34:
        if (this.createSecretChat == true) goto L36;
        Bundle r79 = new Bundle();
        r79.putLong("user_id", r69.id);
        if (getMessagesController().checkCanOpenChat(r79, this) == false) goto L162;
        presentFragment(new ChatActivity(r79), this.needFinishFragment);
        return;
    L162:
        return;
    L36:
        if (r69.id == UserConfig.getInstance(this.currentAccount).getClientUserId()) goto L175;
        this.creatingChat = true;
        SecretChatHelper.getInstance(this.currentAccount).startSecretChat(getParentActivity(), r69);
        return;
    L175:
        return;
    L45:
        if ((r68 instanceof String) == false) goto L55;
        String r610 = (String) r68;
        if (r610.equals("section") == false) goto L49;
        return;
    L49:
        if (MessagesController.getInstance(this.currentAccount).isFrozen() == false) goto L52;
        AccountFrozenAlert.show(this.currentAccount);
        return;
    L52:
        NewContactBottomSheet r710 = new NewContactBottomSheet(this, getContext());
        r710.setInitialPhoneNumber(r610, true);
        r710.show();
        return;
    L55:
        if ((r68 instanceof ContactsController.Contact) == false) goto L164;
        ContactsController.Contact r611 = (ContactsController.Contact) r68;
        AlertsCreator.createContactInviteDialog(this, r611.first_name, r611.last_name, r611.phones.get(0));
        return;
    }

    private /* synthetic */ boolean lambda$createView$7(View r4, int r5) {
        RecyclerView.Adapter r0 = this.listView.getAdapter();
        ContactsAdapter r1 = this.listViewAdapter;
        if (r0 != r1) goto L10;
        int r02 = r1.getSectionForPosition(r5);
        int r52 = this.listViewAdapter.getPositionInSectionForPosition(r5);
        if (Bulletin.getVisibleBulletin() == null) goto L7;
        Bulletin.getVisibleBulletin().hide();
    L7:
        if (r52 < 0) goto L9;
        if (r02 >= 0) goto L10;
    L9:
        return false;
    L10:
        boolean r53 = this.returnAsResult;
        if (r53 == false) goto L13;
    L18:
        if (r53 == false) goto L20;
    L29:
        return false;
    L20:
        if (this.createSecretChat == true) goto L29;
        if ((r4 instanceof ProfileSearchCell) == false) goto L29;
        ProfileSearchCell r42 = (ProfileSearchCell) r4;
        if (r42.getUser() != null) goto L26;
    L28:
        return true;
    L26:
        if (r42.getUser().contact == false) goto L28;
        showOrUpdateActionMode(r42);
        goto L28
    L13:
        if (this.createSecretChat == true) goto L18;
        if ((r4 instanceof UserCell) == false) goto L18;
        showOrUpdateActionMode((UserCell) r4);
        return true;
    }

    private /* synthetic */ void lambda$createView$8(View r2) {
        if (MessagesController.getInstance(this.currentAccount).isFrozen() == false) goto L6;
        AccountFrozenAlert.show(this.currentAccount);
        return;
    L6:
        new NewContactBottomSheet(this, getContext()).show();
    }

    private /* synthetic */ void lambda$didSelectResult$11(TLRPC.User r1, String r2, AlertDialog r3, int r4) {
        ContactsActivityDelegate r32 = this.delegate;
        if (r32 == null) goto L6;
        r32.didSelectContact(r1, r2, this);
        this.delegate = null;
        return;
    }

    private /* synthetic */ void lambda$didSelectResult$12(TLRPC.User r1, EditText r2, AlertDialog r3, int r4) {
        if (r2 == null) goto L4;
        String r22 = r2.getText().toString();
    L5:
        didSelectResult(r1, false, r22);
        return;
    L4:
        r22 = MVEL.VERSION_SUB;
        goto L5
    }

    private /* synthetic */ void lambda$getThemeDescriptions$15() {
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L13;
        int r02 = r0.getChildCount();
        int r2 = 0;
    L5:
        if (r2 >= r02) goto L13;
        View r3 = this.listView.getChildAt(r2);
        if ((r3 instanceof UserCell) == false) goto L10;
        ((UserCell) r3).update(0);
    L12:
        r2 = r2 + 1;
        goto L5
    L10:
        if ((r3 instanceof ProfileSearchCell) == false) goto L12;
        ((ProfileSearchCell) r3).update(0);
    L13:
        ImageView r03 = this.actionModeCloseView;
        if (r03 == null) goto L16;
        r03.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_actionBarActionModeDefaultIcon), PorterDuff.Mode.MULTIPLY));
        this.actionModeCloseView.setBackground(Theme.createSelectorDrawable(getThemedColor(Theme.key_actionBarActionModeDefaultSelector)));
    L16:
        ActionBar r04 = this.actionBar;
        if (r04 == null) goto L19;
        r04.updateColors();
    L19:
        SizeNotifierFrameLayout r05 = this.contentView;
        if (r05 == null) goto L27;
        r05.setBackgroundColor(getThemedColor(Theme.key_windowBackgroundGray));
        return;
    }

    private /* synthetic */ void lambda$onBecomeFullyVisible$13(int r3) {
        if (r3 == 0) goto L5;
        boolean r1 = true;
    L6:
        this.askAboutContacts = r1;
        if (r3 != 0) goto L9;
        return;
    L9:
        askForPermissons(false);
        return;
    L5:
        r1 = false;
        goto L6
    }

    private /* synthetic */ void lambda$performSelectedContactsDelete$9(AlertDialog r4, int r5) {
        ArrayList<TLRPC.User> r42 = new ArrayList(this.selectedContacts.size());
        int r52 = 0;
    L4:
        if (r52 >= this.selectedContacts.size()) goto L6;
        long r0 = this.selectedContacts.keyAt(r52);
        r42.add(this.selectedContacts.get(r0));
        r52 = r52 + 1;
        goto L4
    L6:
        getContactsController().deleteContactsUndoable(getContext(), this, r42);
        hideActionMode();
    }

    private /* synthetic */ void lambda$showItemOptions$16(ContactsActivity r2) {
        getUserConfig().setShowContactsTab(r2, false);
    }

    private /* synthetic */ void lambda$showItemOptions$17(ContactsActivity r2) {
        getUserConfig().setShowContactsTab(r2, true);
    }

    private WindowInsetsCompat onApplyWindowInsets(View r1, WindowInsetsCompat r2) {
        this.navigationBarHeight = AndroidUtilities.getDefaultWindowInsets(r2, false).bottom;
        checkUi_listViewPadding();
        checkUi_floatingButtonPosition();
        checkUi_emptyView();
        return WindowInsetsCompat.CONSUMED;
    }

    private void performSelectedContactsDelete() {
        AlertDialog.Builder r0 = new AlertDialog.Builder(getContext(), getResourceProvider());
        if (this.selectedContacts.size() != 1) goto L5;
        r0.setTitle(LocaleController.getString(R.string.DeleteContactTitle));
        r0.setMessage(LocaleController.getString(R.string.DeleteContactSubtitle));
    L6:
        r0.setPositiveButton(LocaleController.getString(R.string.Delete), new ContactsActivity$$ExternalSyntheticLambda15(this));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), new ContactsActivity$$ExternalSyntheticLambda16());
        AlertDialog r4 = r0.create();
        r4.show();
        r4.redPositive();
        return;
    L5:
        r0.setTitle(LocaleController.formatPluralString("DeleteContactsTitle", this.selectedContacts.size(), new Object[0]));
        r0.setMessage(LocaleController.getString(R.string.DeleteContactsSubtitle));
        goto L6
    }

    private void scheduleSort() {
        if (this.scheduled == true) goto L6;
        this.scheduled = true;
        AndroidUtilities.cancelRunOnUIThread(this.sortContactsRunnable);
        AndroidUtilities.runOnUIThread(this.sortContactsRunnable, 5000);
        return;
    }

    private void showItemOptions() {
        if (this.hasMainTabs == false) goto L5;
        ContactsActivity r0 = null;
    L6:
        ItemOptions.makeOptions(this, this.otherItem).setDimAlpha(8).addIf(getUserConfig().showContactsTab, R.drawable.msg_archive_hide, LocaleController.getString(R.string.HideContactsTab), new ContactsActivity$$ExternalSyntheticLambda12(this, r0)).addIf(!getUserConfig().showContactsTab, R.drawable.menu_add_tab_24, LocaleController.getString(R.string.ShowContactsTab), new ContactsActivity$$ExternalSyntheticLambda13(this, r0)).translate(0.0f, -AndroidUtilities.dp(64.0f)).show();
        return;
    L5:
        r0 = this;
        goto L6
    }

    private void showOrUpdateActionMode(Object r3) {
        if ((r3 instanceof UserCell) == false) goto L6;
        boolean r32 = addOrRemoveSelectedContact((UserCell) r3);
    L8:
        boolean r1 = true;
        if (this.actionBar.isActionModeShowed() == true) goto L11;
        if (r32 == false) goto L16;
        AndroidUtilities.hideKeyboard(this.fragmentView.findFocus());
        this.actionBar.showActionMode();
        this.backDrawable.setRotation(1.0f, true);
    L16:
        r1 = false;
    L17:
        this.selectedContactsCountTextView.setNumber(this.selectedContacts.size(), r1);
        return;
    L11:
        if (this.selectedContacts.isEmpty() == false) goto L17;
        hideActionMode();
        return;
    L6:
        if ((r3 instanceof ProfileSearchCell) == false) goto L19;
        r32 = addOrRemoveSelectedContact((ProfileSearchCell) r3);
        goto L8
    }

    private void updateVisibleRows(int r5) {
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L10;
        int r02 = r0.getChildCount();
        int r1 = 0;
    L5:
        if (r1 >= r02) goto L14;
        View r2 = this.listView.getChildAt(r1);
        if ((r2 instanceof UserCell) == false) goto L9;
        ((UserCell) r2).update(r5);
    L9:
        r1 = r1 + 1;
        goto L5
    L14:
        return;
    }

    public boolean addOrRemoveSelectedContact(ProfileSearchCell r6) {
        long r0 = r6.getDialogId();
        if (this.selectedContacts.indexOfKey(r0) < 0) goto L6;
        this.selectedContacts.remove(r0);
        r6.setChecked(false, true);
    L9:
        return false;
    L6:
        if (r6.getUser() == null) goto L9;
        this.selectedContacts.put(r0, r6.getUser());
        r6.setChecked(true, true);
        return true;
    }

    public boolean addOrRemoveSelectedContact(UserCell r6) {
        long r0 = r6.getDialogId();
        if (this.selectedContacts.indexOfKey(r0) < 0) goto L6;
        this.selectedContacts.remove(r0);
        r6.setChecked(false, true);
    L9:
        return false;
    L6:
        if ((r6.getCurrentObject() instanceof TLRPC.User) == false) goto L9;
        this.selectedContacts.put(r0, (TLRPC.User) r6.getCurrentObject());
        r6.setChecked(true, true);
        return true;
    }

    @Override // org.telegram.ui.MainTabsActivity.TabFragmentDelegate
    public boolean canParentTabsSlide(MotionEvent r1, boolean r2) {
        RecyclerListView r12 = this.listView;
        if (r12 != null) goto L5;
        return true;
    L5:
        if (r12.getFastScroll() != null) goto L7;
        return true;
    L7:
        if (this.listView.getFastScroll().isPressed() == false) goto L13;
        return false;
    L13:
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ActionBar createActionBar(Context r3) {
        ActionBar r2 = super.createActionBar(r3);
        r2.setUseContainerForTitles();
        r2.getTitlesContainer().setTranslationX(AndroidUtilities.dp(4.0f));
        r2.setAddToContainer(false);
        r2.createAdditionalSubTitleOverlayContainer();
        r2.getAdditionalSubTitleOverlayContainer().setTranslationX(AndroidUtilities.dp(4.0f));
        r2.getAdditionalSubTitleOverlayContainer().setTranslationY(-AndroidUtilities.dp(2.0f));
        return r2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r25) {
        this.additionNavigationBarHeight = MainTabsUiHelper.getAdditionalNavigationBarHeight(this.hasMainTabs);
        this.additionFloatingButtonOffset = MainTabsUiHelper.getTabsFabOffset(this.hasMainTabs);
        this.searching = false;
        this.searchWas = false;
        this.actionBar.setAllowOverlayTitle(true);
        if (this.destroyAfterSelect == false) goto L12;
        boolean r0 = this.returnAsResult;
        ActionBar r3 = this.actionBar;
        if (r0 == false) goto L8;
        r3.setTitle(LocaleController.getString(R.string.SelectContact));
    L13:
        BackDrawable r02 = new BackDrawable(false);
        this.backDrawable = r02;
        if (this.hasMainTabs == true) goto L16;
        this.actionBar.setBackButtonDrawable(r02);
    L16:
        FragmentSearchField r03 = new FragmentSearchField(r25, this.resourceProvider);
        this.searchField = r03;
        r03.setSectionBackground();
        this.searchField.setPivotY(0.0f);
        ActionBarMenu r04 = this.actionBar.createActionMode(false, null);
        r04.setBackgroundColor(0);
        if (this.hasMainTabs == false) goto L19;
        ImageView r4 = new ImageView(r25);
        this.actionModeCloseView = r4;
        r4.setScaleType(ImageView.ScaleType.CENTER);
        this.actionModeCloseView.setImageDrawable(new BackDrawable(true));
        this.actionModeCloseView.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_actionBarActionModeDefaultIcon), PorterDuff.Mode.MULTIPLY));
        this.actionModeCloseView.setBackground(Theme.createSelectorDrawable(getThemedColor(Theme.key_actionBarActionModeDefaultSelector)));
        this.actionModeCloseView.setOnClickListener(new ContactsActivity$$ExternalSyntheticLambda2(this));
        r04.addView(this.actionModeCloseView, LayoutHelper.createLinear(54, 54, 16));
    L19:
        NumberTextView r42 = new NumberTextView(r04.getContext());
        this.selectedContactsCountTextView = r42;
        int r5 = 18;
        r42.setTextSize(18);
        this.selectedContactsCountTextView.setTypeface(AndroidUtilities.bold());
        this.selectedContactsCountTextView.setTextColor(getThemedColor(Theme.key_actionBarActionModeDefaultIcon));
        NumberTextView r43 = this.selectedContactsCountTextView;
        if (this.hasMainTabs == true) goto L23;
        r5 = 72;
    L23:
        r04.addView(r43, LayoutHelper.createLinear(0, -1, 1.0f, r5, 0, 0, 0));
        this.selectedContactsCountTextView.setOnTouchListener(new ContactsActivity$$ExternalSyntheticLambda3());
        r04.addItemWithWidth(100, R.drawable.msg_delete, AndroidUtilities.dp(54.0f), LocaleController.getString(R.string.Delete));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        ActionBarMenu r05 = this.actionBar.createMenu();
        ActionBarMenuItem r44 = r05.addItem(0, R.drawable.outline_header_search);
        this.searchItem = r44;
        r44.setContentDescription(LocaleController.getString(R.string.SearchContacts));
        EditTextBoldCursor r45 = this.searchField.editText;
        r45.addTextChangedListener(new SearchTextWatcher(r45, new AnonymousClass2(this)));
        if (this.createSecretChat == false) goto L26;
    L32:
        RecyclerListView r06 = new RecyclerListView(r25);
        this.listView = r06;
        AnonymousClass3 r07 = new AnonymousClass3(this, r06, r25, this.ignoreUsers, this.selectedContacts, this.allowUsernameSearch, false, false, this.allowBots, this.allowSelf, true, 0, this.resourceProvider);
        this.searchListViewAdapter = r07;
        r07.setUseUserCell(true);
        this.searchListViewAdapter.includeSearch = false;
        int r9 = 2;
        if (this.chatId == 0) goto L37;
        int r08 = ChatObject.canUserDoAdminAction(getMessagesController().getChat(Long.valueOf(this.chatId)), 3);
    L35:
        int r8 = r08;
    L45:
        boolean r46 = this.onlyUsers;
        AnonymousClass4 r09 = new AnonymousClass4(this, r25, this, r46 ? 1 : 0, this.needPhonebook, this.ignoreUsers, this.selectedContacts, r8);
        this.listViewAdapter = r09;
        if (this.sortItem != null) goto L48;
        r9 = 0;
    L51:
        r09.setSortType(r9, false);
        this.listViewAdapter.setDisableSections(this.disableSections);
        this.listViewAdapter.includeSearch = false;
        AnonymousClass5 r010 = new AnonymousClass5(this, r25);
        this.contentView = r010;
        this.fragmentView = r010;
        RecyclerListView r47 = this.listView;
        Objects.requireNonNull(r47);
        this.iBlur3Capture = new ViewGroupPartRenderer(r47, r010, new ShareAlert$$ExternalSyntheticLambda16(r47));
        this.listView.addEdgeEffectListener(new ContactsActivity$$ExternalSyntheticLambda5(this));
        this.listView.setSections(true);
        this.contentView.setBackgroundColor(getThemedColor(Theme.key_windowBackgroundGray));
        FlickerLoadingView r011 = new FlickerLoadingView(r25);
        r011.setViewType(29);
        r011.showDate(false);
        StickerEmptyView r32 = new StickerEmptyView(r25, r011, 1);
        this.emptyView = r32;
        r32.addView(r011, 0);
        this.emptyView.setAnimateLayoutChange(true);
        this.emptyView.showProgress(true, false);
        this.emptyView.title.setText(LocaleController.getString(R.string.NoResult));
        this.emptyView.subtitle.setText(LocaleController.getString(R.string.SearchEmptyViewFilteredSubtitle2));
        this.contentView.addView(this.emptyView, LayoutHelper.createFrame(-1, -1.0f, 119, 12.0f, 64.0f, 12.0f, 0.0f));
        DefaultItemAnimator r012 = new DefaultItemAnimator();
        r012.setDelayAnimations(false);
        r012.setDurations(150);
        r012.setSupportsChangeAnimations(false);
        this.listView.setItemAnimator(r012);
        this.listView.setSectionsType(1);
        this.listView.setVerticalScrollBarEnabled(false);
        this.listView.setFastScrollEnabled(0);
        RecyclerListView r013 = this.listView;
        LinearLayoutManager r33 = new LinearLayoutManager(r25, 1, false);
        this.layoutManager = r33;
        r013.setLayoutManager(r33);
        this.listView.setAdapter(this.listViewAdapter);
        this.listView.setClipToPadding(false);
        RecyclerAnimationScrollHelper r014 = new RecyclerAnimationScrollHelper(this.listView, this.layoutManager);
        this.scrollHelper = r014;
        r014.setScrollListener(new ContactsActivity$$ExternalSyntheticLambda6(this));
        SizeNotifierFrameLayout r015 = this.contentView;
        RecyclerListView r34 = this.listView;
        int r48 = this.ADDITIONAL_LIST_HEIGHT_DP;
        r015.addView(r34, LayoutHelper.createFrame(-1, -1.0f, 3, 0.0f, -r48, 0.0f, -r48));
        this.contentView.addView(this.searchField, LayoutHelper.createFrame(-1, 52.0f, 48, 6.0f, 0.0f, 6.0f, 0.0f));
        this.listView.setEmptyView(this.emptyView);
        this.listView.setAnimateEmptyView(true, 0);
        this.listView.setOnItemClickListener(new ContactsActivity$$ExternalSyntheticLambda7(this, r8));
        this.listView.setOnItemLongClickListener(new ContactsActivity$$ExternalSyntheticLambda8(this));
        this.listView.setOnScrollListener(new AnonymousClass6(this));
        if (this.createSecretChat == false) goto L54;
    L56:
        String r016 = this.initialSearchString;
        if (r016 == null) goto L59;
        this.actionBar.openSearchField(r016, false);
        this.initialSearchString = null;
    L59:
        this.contentView.addView(this.actionBar);
        HeaderShadowView r017 = new HeaderShadowView(r25, this.parentLayout);
        this.headerShadowView = r017;
        r017.setShadowVisible(false, false);
        this.contentView.addView(this.headerShadowView, LayoutHelper.createFrame(-1, 5, 48));
        this.actionBar.setAdaptiveBackground(this.listView);
        this.actionBar.setDrawBlurBackground(this.contentView);
        this.animatorSearchFieldVisible.setValue(true, false);
        checkUi_searchFieldHint();
        Bulletin.addDelegate(this, new AnonymousClass7(this));
        LaunchActivity r018 = LaunchActivity.instance;
        if (r018 == null) goto L62;
        r018.getRootAnimatedInsetsListener().subscribeToWindowInsetsAnimation(this);
    L62:
        ViewCompat.setOnApplyWindowInsetsListener(this.fragmentView, new ContactsActivity$$ExternalSyntheticLambda10(this));
        return this.fragmentView;
    L54:
        if (this.returnAsResult == true) goto L56;
        FragmentFloatingButton r019 = new FragmentFloatingButton(r25, this.resourceProvider);
        this.floatingButton = r019;
        this.contentView.addView(r019, FragmentFloatingButton.createDefaultLayoutParams());
        this.floatingButton.setOnClickListener(new ContactsActivity$$ExternalSyntheticLambda9(this));
        this.floatingButton.setAnimation(R.raw.write_contacts_fab_icon, 44);
        this.floatingButton.imageView.getAnimatedDrawable().setCurrentFrame(this.floatingButton.imageView.getAnimatedDrawable().getFramesCount() - 1);
        this.floatingButton.setContentDescription(LocaleController.getString(R.string.CreateNewContact));
        goto L56
    L48:
        if (this.sortByName == false) goto L51;
        r9 = 1;
        goto L51
    L37:
        if (this.channelId == 0) goto L44;
        TLRPC.Chat r020 = getMessagesController().getChat(Long.valueOf(this.channelId));
        if (ChatObject.canUserDoAdminAction(r020, 3) == true) goto L41;
    L43:
        r08 = 0;
        goto L35
    L41:
        if (ChatObject.isPublic(r020) == true) goto L43;
        r08 = 2;
        goto L35
    L44:
        r8 = 0;
        goto L45
    L26:
        if (this.returnAsResult == true) goto L32;
        if (this.sortByName == false) goto L30;
        int r49 = R.drawable.msg_contacts_time;
    L31:
        ActionBarMenuItem r410 = r05.addItem(1, r49);
        this.sortItem = r410;
        r410.setContentDescription(LocaleController.getString(R.string.AccDescrContactSorting));
        ActionBarMenuItem r021 = r05.addItem(-1, R.drawable.ic_ab_other);
        this.otherItem = r021;
        r021.setContentDescription(LocaleController.getString(R.string.AccDescrMoreOptions));
        this.otherItem.setOnClickListener(new ContactsActivity$$ExternalSyntheticLambda4(this));
        goto L32
    L30:
        r49 = R.drawable.msg_contacts_name;
        goto L31
    L8:
        if (this.createSecretChat == false) goto L10;
        int r022 = R.string.NewSecretChat;
    L11:
        r3.setTitle(LocaleController.getString(r022));
        goto L13
    L10:
        r022 = R.string.NewMessageTitle;
        goto L11
    L12:
        this.actionBar.setTitle(LocaleController.getString(R.string.Contacts));
        goto L13
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.contactsDidLoad) goto L17;
        ContactsAdapter r32 = this.listViewAdapter;
        if (r32 == null) goto L11;
        if (this.sortByName == true) goto L9;
        r32.setSortType(2, true);
    L9:
        this.listViewAdapter.notifyDataSetChanged();
    L11:
        if (this.searchListViewAdapter == null) goto L47;
        RecyclerView.Adapter r33 = this.listView.getAdapter();
        SearchAdapter r42 = this.searchListViewAdapter;
        if (r33 != r42) goto L48;
        r42.searchDialogs(this.searchQuery);
        return;
    L48:
        return;
    L47:
        return;
    L17:
        if (r3 != NotificationCenter.updateInterfaces) goto L34;
        int r34 = ((Integer) r5[0]).intValue();
        if ((MessagesController.UPDATE_MASK_AVATAR & r34) == 0) goto L21;
    L24:
        updateVisibleRows(r34);
    L26:
        if ((r34 & MessagesController.UPDATE_MASK_STATUS) != 0) goto L28;
        return;
    L28:
        if (this.sortByName == false) goto L30;
        return;
    L30:
        if (this.listViewAdapter == null) goto L51;
        scheduleSort();
        return;
    L51:
        return;
    L21:
        if ((MessagesController.UPDATE_MASK_NAME & r34) != 0) goto L24;
        if ((MessagesController.UPDATE_MASK_STATUS & r34) == 0) goto L26;
    L34:
        if (r3 != NotificationCenter.encryptedChatCreated) goto L42;
        if (this.createSecretChat == true) goto L38;
        return;
    L38:
        if (this.creatingChat == false) goto L53;
        TLRPC.EncryptedChat r35 = (TLRPC.EncryptedChat) r5[0];
        Bundle r43 = new Bundle();
        r43.putInt("enc_id", r35.id);
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.closeChats, new Object[0]);
        presentFragment(new ChatActivity(r43), false);
        return;
    L53:
        return;
    L42:
        if (r3 == NotificationCenter.closeChats) goto L44;
        return;
    L44:
        if (this.creatingChat == true) goto L55;
        removeSelfFromStack(true);
        return;
    }

    @Override // org.telegram.ui.Components.inset.WindowAnimatedInsetsProvider.Listener
    public View getAnimatedInsetsTargetView() {
        return this.fragmentView;
    }

    @Override // org.telegram.ui.MainTabsActivity.TabFragmentDelegate
    public BlurredBackgroundSourceRenderNode getGlassSource() {
        return this.iBlur3SourceGlass;
    }

    public RecyclerListView getListView() {
        return this.listView;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        ContactsActivity$$ExternalSyntheticLambda1 r8 = new ContactsActivity$$ExternalSyntheticLambda1(this);
        if (this.hasMainTabs == true) goto L5;
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundWhite));
    L5:
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SEARCH, null, null, null, null, Theme.key_actionBarDefaultSearch));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SEARCHPLACEHOLDER, null, null, null, null, Theme.key_actionBarDefaultSearchPlaceholder));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SECTIONS, new Class[]{LetterSectionCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText4));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_FASTSCROLL, null, null, null, null, Theme.key_fastScrollActive));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_FASTSCROLL, null, null, null, null, Theme.key_fastScrollInactive));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_FASTSCROLL, null, null, null, null, Theme.key_fastScrollText));
        int r32 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell.class}, new String[]{"nameTextView"}, null, null, null, r32));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell.class}, new String[]{"statusColor"}, null, null, r8, Theme.key_windowBackgroundWhiteGrayText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell.class}, new String[]{"statusOnlineColor"}, null, null, r8, Theme.key_windowBackgroundWhiteBlueText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{UserCell.class}, null, Theme.avatarDrawables, null, Theme.key_avatar_text));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundRed));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundOrange));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundViolet));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundGreen));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundCyan));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundBlue));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundPink));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{TextCell.class}, new String[]{"textView"}, null, null, null, r32));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CHECKTAG | ThemeDescription.FLAG_TEXTCOLOR, new Class[]{TextCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueText2));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextCell.class}, new String[]{"imageView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayIcon));
        if (this.floatingButton == null) goto L8;
        r1.add(new ThemeDescription(this.floatingButton.imageView, ThemeDescription.FLAG_IMAGECOLOR, null, null, null, null, Theme.key_chats_actionIcon));
        r1.add(new ThemeDescription(this.floatingButton.imageView, ThemeDescription.FLAG_BACKGROUNDFILTER, null, null, null, null, Theme.key_chats_actionBackground));
        r1.add(new ThemeDescription(this.floatingButton.imageView, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_DRAWABLESELECTEDSTATE, null, null, null, null, Theme.key_chats_actionPressedBackground));
    L8:
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{GraySectionCell.class}, new String[]{"textView"}, null, null, null, Theme.key_graySectionText));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{GraySectionCell.class}, null, null, null, Theme.key_graySection));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ProfileSearchCell.class}, null, new Drawable[]{Theme.dialogs_verifiedCheckDrawable}, null, Theme.key_chats_verifiedCheck));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ProfileSearchCell.class}, null, new Drawable[]{Theme.dialogs_verifiedDrawable}, null, Theme.key_chats_verifiedBackground));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ProfileSearchCell.class}, Theme.dialogs_offlinePaint, null, null, Theme.key_windowBackgroundWhiteGrayText3));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ProfileSearchCell.class}, Theme.dialogs_onlinePaint, null, null, Theme.key_windowBackgroundWhiteBlueText3));
        TextPaint[] r3 = Theme.dialogs_namePaint;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ProfileSearchCell.class}, null, new Paint[]{r3[0], r3[1], Theme.dialogs_searchNamePaint}, null, null, Theme.key_chats_name));
        TextPaint[] r0 = Theme.dialogs_nameEncryptedPaint;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{ProfileSearchCell.class}, null, new Paint[]{r0[0], r0[1], Theme.dialogs_searchNameEncryptedPaint}, null, null, Theme.key_chats_secretName));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.Components.inset.WindowAnimatedInsetsProvider.Listener
    public void onAnimatedInsetsChanged(View r1, WindowInsetsCompat r2) {
        this.imeInsetAnimatedHeight = r2.getInsets(WindowInsetsCompat.Type.ime()).bottom;
        checkUi_emptyView();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r3) {
        if (this.actionBar.isActionModeShowed() == false) goto L8;
        if (r3 == false) goto L6;
        hideActionMode();
    L6:
        return false;
    L8:
        if (this.animatorSearchHasQuery.getValue() == false) goto L13;
        if (r3 == false) goto L11;
        this.searchField.editText.getText().clear();
    L11:
        return false;
    L13:
        return super.onBackPressed(r3);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onBecomeFullyVisible() {
        super.onBecomeFullyVisible();
        if (this.checkPermission == false) goto L14;
        Activity r0 = getParentActivity();
        if (r0 == null) goto L15;
        this.checkPermission = false;
        if (r0.checkSelfPermission("android.permission.READ_CONTACTS") != 0) goto L9;
        return;
    L9:
        if (r0.shouldShowRequestPermissionRationale("android.permission.READ_CONTACTS") == false) goto L12;
        AlertDialog r02 = AlertsCreator.createContactsPermissionDialog(r0, new ContactsActivity$$ExternalSyntheticLambda0(this)).create();
        this.permissionDialog = r02;
        showDialog(r02);
        return;
    L12:
        askForPermissons(true);
        return;
    L15:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onDialogDismiss(Dialog r2) {
        super.onDialogDismiss(r2);
        AlertDialog r0 = this.permissionDialog;
        if (r0 == null) goto L11;
        if (r2 == r0) goto L6;
        return;
    L6:
        if (getParentActivity() != null) goto L8;
        return;
    L8:
        if (this.askAboutContacts == false) goto L14;
        askForPermissons(false);
        return;
    L14:
        return;
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChangeFinished(int r1, float r2, FactorAnimator r3) {
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        if (r1 != 0) goto L6;
        checkUi_searchButton();
        return;
    L6:
        if (r1 != 2) goto L9;
        checkUi_searchButton();
        checkUi_sortItem();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.contactsDidLoad);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.updateInterfaces);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.encryptedChatCreated);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.closeChats);
        this.checkPermission = UserConfig.getInstance(this.currentAccount).syncContacts;
        Bundle r0 = this.arguments;
        if (r0 == null) goto L5;
        this.onlyUsers = r0.getBoolean("onlyUsers", false);
        this.destroyAfterSelect = this.arguments.getBoolean("destroyAfterSelect", false);
        this.returnAsResult = this.arguments.getBoolean("returnAsResult", false);
        this.createSecretChat = this.arguments.getBoolean("createSecretChat", false);
        this.selectAlertString = this.arguments.getString("selectAlertString");
        this.allowUsernameSearch = this.arguments.getBoolean("allowUsernameSearch", true);
        this.needForwardCount = this.arguments.getBoolean("needForwardCount", true);
        this.allowBots = this.arguments.getBoolean("allowBots", true);
        this.allowSelf = this.arguments.getBoolean("allowSelf", true);
        this.channelId = this.arguments.getLong("channelId", 0);
        this.needFinishFragment = this.arguments.getBoolean("needFinishFragment", true);
        this.chatId = this.arguments.getLong("chat_id", 0);
        this.disableSections = this.arguments.getBoolean("disableSections", false);
        this.resetDelegate = this.arguments.getBoolean("resetDelegate", false);
        this.needPhonebook = this.arguments.getBoolean("needPhonebook", false);
        this.hasMainTabs = this.arguments.getBoolean("hasMainTabs", false);
    L7:
        if (this.createSecretChat == false) goto L9;
    L11:
        getContactsController().checkInviteText();
        getContactsController().reloadContactsStatusesMaybe(false);
        this.additionNavigationBarHeight = MainTabsUiHelper.getAdditionalNavigationBarHeight(this.hasMainTabs);
        this.additionFloatingButtonOffset = MainTabsUiHelper.getTabsFabOffset(this.hasMainTabs);
        return true;
    L9:
        if (this.returnAsResult == true) goto L11;
        this.sortByName = SharedConfig.sortContactsByName;
        goto L11
    L5:
        this.needPhonebook = true;
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.contactsDidLoad);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.updateInterfaces);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.encryptedChatCreated);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.closeChats);
        this.delegate = null;
        RecyclerListView r1 = this.listView;
        if (r1 == null) goto L5;
        r1.setAdapter(null);
        this.listView = null;
    L5:
        Bulletin.removeDelegate(this);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.navigationBarHeight = r4;
        checkUi_listViewPadding();
        checkUi_floatingButtonPosition();
        checkUi_emptyView();
    }

    @Override // org.telegram.ui.MainTabsActivity.TabFragmentDelegate
    public void onParentScrollToTop() {
        if (this.layoutManager.findFirstVisibleItemPosition() >= 15) goto L5;
        this.listView.smoothScrollToPosition(0);
    L6:
        this.animatorSearchFieldVisible.setValue(true, true);
        return;
    L5:
        this.scrollHelper.setScrollDirection(1);
        this.scrollHelper.scrollToPosition(0, 0, false, true);
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        ActionBar r0 = this.actionBar;
        if (r0 == null) goto L6;
        r0.closeSearchField();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onRequestPermissionsResultFragment(int r4, String[] r5, int[] r6) {
        if (r4 != 1) goto L24;
        int r0 = 0;
    L6:
        if (r0 >= r5.length) goto L32;
        if (r6.length <= r0) goto L23;
        if ("android.permission.READ_CONTACTS".equals(r5[r0]) == false) goto L23;
        if (r6[r0] != 0) goto L16;
        ContactsController.getInstance(this.currentAccount).forceImportContacts();
        return;
    L16:
        SharedPreferences.Editor r52 = MessagesController.getGlobalNotificationsSettings().edit();
        this.askAboutContacts = false;
        r52.putBoolean("askAboutContacts", false).putBoolean("askAboutContacts2", false).apply();
        if ((SystemClock.elapsedRealtime() - this.permissionRequestTime) >= 200) goto L31;
        Intent r42 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");     // Catch: Exception -> L20
        r42.setData(Uri.fromParts("package", ApplicationLoader.applicationContext.getPackageName(), null));     // Catch: Exception -> L20
        getParentActivity().startActivity(r42);     // Catch: Exception -> L20
        return;
    L20:
        e = move-exception;
        FileLog.e(e);
        return;
    L31:
        return;
    L23:
        r0 = r0 + 1;
        goto L6
    L32:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        this.floatingButtonVisibleByScroll = true;
        checkUi_floatingButtonVisible();
        ContactsAdapter r1 = this.listViewAdapter;
        if (r1 == null) goto L6;
        r1.notifyDataSetChanged();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationProgress(boolean r1, float r2) {
        super.onTransitionAnimationProgress(r1, r2);
        View r0 = this.fragmentView;
        if (r0 == null) goto L6;
        r0.invalidate();
        return;
    }

    public void setDelegate(ContactsActivityDelegate r1) {
        this.delegate = r1;
    }

    public void setInitialSearchString(String r1) {
        this.initialSearchString = r1;
    }

    public void setMainTabsActivityController(MainTabsActivityController r1) {
        this.mainTabsActivityController = r1;
    }

    @Override // org.telegram.ui.MainTabsActivity.TabFragmentDelegate
    public void updateMainTabsVisibility() {
        checkUi_floatingButtonVisible();
    }
}
