package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Property;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import androidx.annotation.Keep;
import androidx.collection.LongSparseArray;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Adapters.SearchAdapterHelper;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.GroupCreateUserCell;
import org.telegram.ui.Components.AnimatedAvatarContainer;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.FragmentFloatingButton;
import org.telegram.ui.Components.GroupCreateSpan;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.StickerEmptyView;

/* loaded from: classes6.dex */
public class UsersSelectActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate, View.OnClickListener {
    private GroupCreateAdapter adapter;
    private ArrayList<GroupCreateSpan> allSpans;
    public boolean allowSelf;
    AnimatedAvatarContainer animatedAvatarContainer;
    private int containerHeight;
    private GroupCreateSpan currentDeletingSpan;
    private FilterUsersActivityDelegate delegate;
    public boolean doNotNewChats;
    private EditTextBoldCursor editText;
    private StickerEmptyView emptyView;
    private int fieldY;
    private int filterFlags;
    private FragmentFloatingButton floatingButton;
    private FrameLayout.LayoutParams floatingButtonLp;
    private boolean ignoreScrollEvent;
    private ArrayList<Long> initialIds;
    private boolean isInclude;
    private RecyclerListView listView;
    public boolean noChatTypes;
    private FlickerLoadingView progressView;
    private ScrollView scrollView;
    private boolean searchWas;
    private boolean searching;
    private LongSparseArray<GroupCreateSpan> selectedContacts;
    private int selectedCount;
    private SpansContainer spansContainer;
    private int ttlPeriod;
    private int type;

    /* renamed from: org.telegram.ui.UsersSelectActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ UsersSelectActivity this$0;

        public AnonymousClass1(UsersSelectActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L7;
            this.this$0.finishFragment();
            return;
        L7:
            if (r2 != 1) goto L10;
            UsersSelectActivity.m21131$$Nest$monDonePressed(this.this$0, true);
            return;
        }
    }

    /* renamed from: org.telegram.ui.UsersSelectActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends ViewGroup {
        final /* synthetic */ UsersSelectActivity this$0;

        public AnonymousClass2(UsersSelectActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r1, View r2, long r3) {
            boolean r32 = super.drawChild(r1, r2, r3);
            if (r2 != UsersSelectActivity.m21114$$Nest$fgetlistView(this.this$0)) goto L5;
        L8:
            UsersSelectActivity.access$000(this.this$0).drawHeaderShadow(r1, UsersSelectActivity.m21116$$Nest$fgetscrollView(this.this$0).getMeasuredHeight());
            return r32;
        L5:
            if (r2 == UsersSelectActivity.m21107$$Nest$fgetemptyView(this.this$0)) goto L8;
            return r32;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onLayout(boolean r6, int r7, int r8, int r9, int r10) {
            UsersSelectActivity.m21116$$Nest$fgetscrollView(this.this$0).layout(0, 0, UsersSelectActivity.m21116$$Nest$fgetscrollView(this.this$0).getMeasuredWidth(), UsersSelectActivity.m21116$$Nest$fgetscrollView(this.this$0).getMeasuredHeight());
            UsersSelectActivity.m21114$$Nest$fgetlistView(this.this$0).layout(0, UsersSelectActivity.m21116$$Nest$fgetscrollView(this.this$0).getMeasuredHeight(), UsersSelectActivity.m21114$$Nest$fgetlistView(this.this$0).getMeasuredWidth(), UsersSelectActivity.m21116$$Nest$fgetscrollView(this.this$0).getMeasuredHeight() + UsersSelectActivity.m21114$$Nest$fgetlistView(this.this$0).getMeasuredHeight());
            UsersSelectActivity.m21107$$Nest$fgetemptyView(this.this$0).layout(0, UsersSelectActivity.m21116$$Nest$fgetscrollView(this.this$0).getMeasuredHeight(), UsersSelectActivity.m21107$$Nest$fgetemptyView(this.this$0).getMeasuredWidth(), UsersSelectActivity.m21116$$Nest$fgetscrollView(this.this$0).getMeasuredHeight() + UsersSelectActivity.m21107$$Nest$fgetemptyView(this.this$0).getMeasuredHeight());
            UsersSelectActivity.m21115$$Nest$fgetprogressView(this.this$0).layout(0, UsersSelectActivity.m21116$$Nest$fgetscrollView(this.this$0).getMeasuredHeight(), UsersSelectActivity.m21107$$Nest$fgetemptyView(this.this$0).getMeasuredWidth(), UsersSelectActivity.m21116$$Nest$fgetscrollView(this.this$0).getMeasuredHeight() + UsersSelectActivity.m21115$$Nest$fgetprogressView(this.this$0).getMeasuredHeight());
            if (UsersSelectActivity.m21110$$Nest$fgetfloatingButton(this.this$0) == null) goto L10;
            boolean r62 = LocaleController.isRTL;
            UsersSelectActivity r0 = this.this$0;
            if (r62 == false) goto L7;
            int r63 = UsersSelectActivity.m21111$$Nest$fgetfloatingButtonLp(r0).leftMargin;
        L8:
            int r102 = ((r10 - r8) - UsersSelectActivity.m21111$$Nest$fgetfloatingButtonLp(this.this$0).bottomMargin) - UsersSelectActivity.m21110$$Nest$fgetfloatingButton(this.this$0).getMeasuredHeight();
            UsersSelectActivity.m21110$$Nest$fgetfloatingButton(this.this$0).layout(r63, r102, UsersSelectActivity.m21110$$Nest$fgetfloatingButton(this.this$0).getMeasuredWidth() + r63, UsersSelectActivity.m21110$$Nest$fgetfloatingButton(this.this$0).getMeasuredHeight() + r102);
            return;
        L7:
            r63 = ((r9 - r7) - UsersSelectActivity.m21111$$Nest$fgetfloatingButtonLp(r0).rightMargin) - UsersSelectActivity.m21110$$Nest$fgetfloatingButton(this.this$0).getMeasuredWidth();
            goto L8
        }

        @Override // android.view.View
        public void onMeasure(int r6, int r7) {
            int r62 = View.MeasureSpec.getSize(r6);
            int r72 = View.MeasureSpec.getSize(r7);
            setMeasuredDimension(r62, r72);
            if (AndroidUtilities.isTablet() == true) goto L7;
            if (r72 > r62) goto L7;
            int r0 = AndroidUtilities.dp(56.0f);
        L8:
            UsersSelectActivity.m21116$$Nest$fgetscrollView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r62, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r0, Integer.MIN_VALUE));
            UsersSelectActivity.m21114$$Nest$fgetlistView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r62, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r72 - UsersSelectActivity.m21116$$Nest$fgetscrollView(this.this$0).getMeasuredHeight(), TLObject.FLAG_30));
            UsersSelectActivity.m21107$$Nest$fgetemptyView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r62, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r72 - UsersSelectActivity.m21116$$Nest$fgetscrollView(this.this$0).getMeasuredHeight(), TLObject.FLAG_30));
            UsersSelectActivity.m21115$$Nest$fgetprogressView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r62, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r72 - UsersSelectActivity.m21116$$Nest$fgetscrollView(this.this$0).getMeasuredHeight(), TLObject.FLAG_30));
            if (UsersSelectActivity.m21110$$Nest$fgetfloatingButton(this.this$0) == null) goto L12;
            int r63 = UsersSelectActivity.m21111$$Nest$fgetfloatingButtonLp(this.this$0).width;
            UsersSelectActivity.m21110$$Nest$fgetfloatingButton(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r63, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r63, TLObject.FLAG_30));
            return;
        L12:
            return;
        L7:
            r0 = AndroidUtilities.dp(144.0f);
            goto L8
        }
    }

    /* renamed from: org.telegram.ui.UsersSelectActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends ScrollView {
        final /* synthetic */ UsersSelectActivity this$0;

        public AnonymousClass3(UsersSelectActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.ScrollView, android.view.ViewGroup, android.view.ViewParent
        public boolean requestChildRectangleOnScreen(View r4, Rect r5, boolean r6) {
            if (UsersSelectActivity.m21112$$Nest$fgetignoreScrollEvent(this.this$0) == false) goto L6;
            UsersSelectActivity.m21125$$Nest$fputignoreScrollEvent(this.this$0, false);
            return false;
        L6:
            r5.offset(r4.getLeft() - r4.getScrollX(), r4.getTop() - r4.getScrollY());
            r5.top += UsersSelectActivity.m21108$$Nest$fgetfieldY(this.this$0) + AndroidUtilities.dp(20.0f);
            r5.bottom += UsersSelectActivity.m21108$$Nest$fgetfieldY(this.this$0) + AndroidUtilities.dp(50.0f);
            return super.requestChildRectangleOnScreen(r4, r5, r6);
        }
    }

    /* renamed from: org.telegram.ui.UsersSelectActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends EditTextBoldCursor {
        final /* synthetic */ UsersSelectActivity this$0;

        public AnonymousClass4(UsersSelectActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public boolean onTouchEvent(MotionEvent r3) {
            if (UsersSelectActivity.m21105$$Nest$fgetcurrentDeletingSpan(this.this$0) == null) goto L6;
            UsersSelectActivity.m21105$$Nest$fgetcurrentDeletingSpan(this.this$0).cancelDeleteAnimation();
            UsersSelectActivity.m21122$$Nest$fputcurrentDeletingSpan(this.this$0, null);
        L6:
            if (r3.getAction() != 0) goto L11;
            if (AndroidUtilities.showKeyboard(this) == true) goto L11;
            clearFocus();
            requestFocus();
        L11:
            return super.onTouchEvent(r3);
        }
    }

    /* renamed from: org.telegram.ui.UsersSelectActivity$5, reason: invalid class name */
    public class AnonymousClass5 implements ActionMode.Callback {
        final /* synthetic */ UsersSelectActivity this$0;

        public AnonymousClass5(UsersSelectActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode r1, MenuItem r2) {
            return false;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode r1, Menu r2) {
            return false;
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode r1) {
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode r1, Menu r2) {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.UsersSelectActivity$6, reason: invalid class name */
    public class AnonymousClass6 implements View.OnKeyListener {
        final /* synthetic */ UsersSelectActivity this$0;
        private boolean wasEmpty;

        public AnonymousClass6(UsersSelectActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View r10, int r11, KeyEvent r12) {
            if (r11 != 67) goto L57;
            boolean r112 = true;
            if (r12.getAction() != 0) goto L12;
            if (UsersSelectActivity.m21106$$Nest$fgeteditText(this.this$0).length() == 0) goto L10;
            r112 = false;
        L10:
            this.wasEmpty = r112;
            goto L57
        L12:
            if (r12.getAction() != 1) goto L57;
            if (this.wasEmpty == false) goto L57;
            if (UsersSelectActivity.m21103$$Nest$fgetallSpans(this.this$0).isEmpty() == true) goto L57;
            GroupCreateSpan r102 = (GroupCreateSpan) UsersSelectActivity.m21103$$Nest$fgetallSpans(this.this$0).get(UsersSelectActivity.m21103$$Nest$fgetallSpans(this.this$0).size() - 1);
            UsersSelectActivity.m21119$$Nest$fgetspansContainer(this.this$0).removeSpan(r102);
            if (UsersSelectActivity.m21120$$Nest$fgettype(this.this$0) != 2) goto L32;
            if (r102.getUid() != (-9223372036854775800L)) goto L23;
            UsersSelectActivity r103 = this.this$0;
            UsersSelectActivity.m21124$$Nest$fputfilterFlags(r103, UsersSelectActivity.m21109$$Nest$fgetfilterFlags(r103) & (-2));
        L55:
            UsersSelectActivity.m21132$$Nest$mupdateHint(this.this$0);
            UsersSelectActivity.m21129$$Nest$mcheckVisibleRows(this.this$0);
            return true;
        L23:
            if (r102.getUid() != (-9223372036854775799L)) goto L26;
            UsersSelectActivity r104 = this.this$0;
            UsersSelectActivity.m21124$$Nest$fputfilterFlags(r104, UsersSelectActivity.m21109$$Nest$fgetfilterFlags(r104) & (-3));
            goto L55
        L26:
            if (r102.getUid() != Long.MIN_VALUE) goto L29;
            UsersSelectActivity r105 = this.this$0;
            UsersSelectActivity.m21124$$Nest$fputfilterFlags(r105, UsersSelectActivity.m21109$$Nest$fgetfilterFlags(r105) & (-5));
            goto L55
        L29:
            if (r102.getUid() != (-9223372036854775807L)) goto L55;
            UsersSelectActivity r106 = this.this$0;
            UsersSelectActivity.m21124$$Nest$fputfilterFlags(r106, UsersSelectActivity.m21109$$Nest$fgetfilterFlags(r106) & (-9));
            goto L55
        L32:
            if (r102.getUid() != Long.MIN_VALUE) goto L35;
            UsersSelectActivity r107 = this.this$0;
            UsersSelectActivity.m21124$$Nest$fputfilterFlags(r107, UsersSelectActivity.m21109$$Nest$fgetfilterFlags(r107) & (~MessagesController.DIALOG_FILTER_FLAG_CONTACTS));
            goto L55
        L35:
            if (r102.getUid() != (-9223372036854775807L)) goto L38;
            UsersSelectActivity r108 = this.this$0;
            UsersSelectActivity.m21124$$Nest$fputfilterFlags(r108, UsersSelectActivity.m21109$$Nest$fgetfilterFlags(r108) & (~MessagesController.DIALOG_FILTER_FLAG_NON_CONTACTS));
            goto L55
        L38:
            if (r102.getUid() != (-9223372036854775806L)) goto L41;
            UsersSelectActivity r109 = this.this$0;
            UsersSelectActivity.m21124$$Nest$fputfilterFlags(r109, UsersSelectActivity.m21109$$Nest$fgetfilterFlags(r109) & (~MessagesController.DIALOG_FILTER_FLAG_GROUPS));
            goto L55
        L41:
            if (r102.getUid() != (-9223372036854775805L)) goto L44;
            UsersSelectActivity r1010 = this.this$0;
            UsersSelectActivity.m21124$$Nest$fputfilterFlags(r1010, UsersSelectActivity.m21109$$Nest$fgetfilterFlags(r1010) & (~MessagesController.DIALOG_FILTER_FLAG_CHANNELS));
            goto L55
        L44:
            if (r102.getUid() != (-9223372036854775804L)) goto L47;
            UsersSelectActivity r1011 = this.this$0;
            UsersSelectActivity.m21124$$Nest$fputfilterFlags(r1011, UsersSelectActivity.m21109$$Nest$fgetfilterFlags(r1011) & (~MessagesController.DIALOG_FILTER_FLAG_BOTS));
            goto L55
        L47:
            if (r102.getUid() != (-9223372036854775803L)) goto L50;
            UsersSelectActivity r1012 = this.this$0;
            UsersSelectActivity.m21124$$Nest$fputfilterFlags(r1012, UsersSelectActivity.m21109$$Nest$fgetfilterFlags(r1012) & (~MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_MUTED));
            goto L55
        L50:
            if (r102.getUid() != (-9223372036854775802L)) goto L53;
            UsersSelectActivity r1013 = this.this$0;
            UsersSelectActivity.m21124$$Nest$fputfilterFlags(r1013, UsersSelectActivity.m21109$$Nest$fgetfilterFlags(r1013) & (~MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_READ));
            goto L55
        L53:
            if (r102.getUid() != (-9223372036854775801L)) goto L55;
            UsersSelectActivity r1014 = this.this$0;
            UsersSelectActivity.m21124$$Nest$fputfilterFlags(r1014, UsersSelectActivity.m21109$$Nest$fgetfilterFlags(r1014) & (~MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_ARCHIVED));
        L57:
            return false;
        }
    }

    /* renamed from: org.telegram.ui.UsersSelectActivity$7, reason: invalid class name */
    public class AnonymousClass7 implements TextWatcher {
        final /* synthetic */ UsersSelectActivity this$0;

        public AnonymousClass7(UsersSelectActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r3) {
            int r32 = UsersSelectActivity.m21106$$Nest$fgeteditText(this.this$0).length();
            UsersSelectActivity r0 = this.this$0;
            if (r32 != 0) goto L5;
            UsersSelectActivity.m21130$$Nest$mcloseSearch(r0);
            return;
        L5:
            if (GroupCreateAdapter.m21134$$Nest$fgetsearching(UsersSelectActivity.m21102$$Nest$fgetadapter(r0)) == true) goto L7;
            UsersSelectActivity.m21127$$Nest$fputsearching(this.this$0, true);
            UsersSelectActivity.m21126$$Nest$fputsearchWas(this.this$0, true);
            UsersSelectActivity.m21102$$Nest$fgetadapter(this.this$0).setSearching(true);
            UsersSelectActivity.m21114$$Nest$fgetlistView(this.this$0).setFastScrollVisible(false);
            UsersSelectActivity.m21114$$Nest$fgetlistView(this.this$0).setVerticalScrollBarEnabled(true);
            UsersSelectActivity.m21107$$Nest$fgetemptyView(this.this$0).title.setText(LocaleController.getString(R.string.NoResult));
        L7:
            UsersSelectActivity.m21107$$Nest$fgetemptyView(this.this$0).showProgress(true);
            UsersSelectActivity.m21102$$Nest$fgetadapter(this.this$0).searchDialogs(UsersSelectActivity.m21106$$Nest$fgeteditText(this.this$0).getText().toString());
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    /* renamed from: org.telegram.ui.UsersSelectActivity$8, reason: invalid class name */
    public class AnonymousClass8 extends StickerEmptyView {
        final /* synthetic */ UsersSelectActivity this$0;

        public AnonymousClass8(UsersSelectActivity r1, Context r2, View r3, int r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.StickerEmptyView, android.view.View
        public void setVisibility(int r1) {
            super.setVisibility(r1);
            if (r1 == 0) goto L6;
            showProgress(false, false);
            return;
        }
    }

    /* renamed from: org.telegram.ui.UsersSelectActivity$9, reason: invalid class name */
    public class AnonymousClass9 extends RecyclerView.OnScrollListener {
        final /* synthetic */ UsersSelectActivity this$0;

        public AnonymousClass9(UsersSelectActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 1) goto L6;
            AndroidUtilities.hideKeyboard(UsersSelectActivity.m21106$$Nest$fgeteditText(this.this$0));
            return;
        }
    }

    public interface FilterUsersActivityDelegate {
        void didSelectChats(ArrayList<Long> r1, int r2);
    }

    public class GroupCreateAdapter extends RecyclerListView.FastScrollAdapter {
        private ArrayList<TLObject> contacts;
        private Context context;
        private SearchAdapterHelper searchAdapterHelper;
        private ArrayList<Object> searchResult;
        private ArrayList<CharSequence> searchResultNames;
        private Runnable searchRunnable;
        private boolean searching;
        final /* synthetic */ UsersSelectActivity this$0;
        private final int usersStartRow;

        public static /* synthetic */ void $r8$lambda$2OVJEdHHT0EecvQ93XUueSXRXhA(GroupCreateAdapter r0, String r1, boolean r2, boolean r3) {
            r0.lambda$searchDialogs$1(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$VklIRlA0PYZmEca_FqwJ5KKogoM(GroupCreateAdapter r0, int r1) {
            r0.lambda$new$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$dOpSi0TvYOAiq19fXVRZJ5jkLLk(GroupCreateAdapter r0, String r1, boolean r2, boolean r3) {
            r0.lambda$searchDialogs$3(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$rLFADOAQX8vY9HXPiJ1Tvgg03x8(GroupCreateAdapter r0, String r1, boolean r2, boolean r3) {
            r0.lambda$searchDialogs$2(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$tpPUWIJV5lT-6KBZWFOMqN0kGqg, reason: not valid java name */
        public static /* synthetic */ void m21133$r8$lambda$tpPUWIJV5lT6KBZWFOMqN0kGqg(GroupCreateAdapter r0, ArrayList r1, ArrayList r2) {
            r0.lambda$updateSearchResults$4(r1, r2);
        }

        /* renamed from: -$$Nest$fgetsearching, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m21134$$Nest$fgetsearching(GroupCreateAdapter r0) {
            return r0.searching;
        }

        public GroupCreateAdapter(UsersSelectActivity r12, Context r13) {
            this.this$0 = r12;
            this.searchResult = new ArrayList();
            this.searchResultNames = new ArrayList();
            this.contacts = new ArrayList();
            this.context = r13;
            if (r12.noChatTypes == false) goto L6;
            this.usersStartRow = 0;
        L16:
            if (UsersSelectActivity.m21120$$Nest$fgettype(r12) == 2) goto L18;
            boolean r132 = true;
        L20:
            if (UsersSelectActivity.m21120$$Nest$fgettype(r12) == 2) goto L22;
            boolean r0 = true;
        L23:
            ArrayList<TLRPC.Dialog> r3 = r12.getMessagesController().getAllDialogs();
            int r4 = r3.size();
            int r5 = 0;
            boolean r6 = false;
        L24:
            if (r5 >= r4) goto L50;
            TLRPC.Dialog r7 = r3.get(r5);
            if (DialogObject.isEncryptedDialog(r7.id) == true) goto L49;
            if (DialogObject.isUserDialog(r7.id) == false) goto L44;
            TLRPC.User r72 = r12.getMessagesController().getUser(Long.valueOf(r7.id));
            if (r72 == null) goto L49;
            if (r12.allowSelf == true) goto L38;
            if (UserObject.isUserSelf(r72) == true) goto L49;
        L38:
            if (r72.bot == false) goto L41;
            if (r132 == false) goto L49;
        L41:
            this.contacts.add(r72);
            if (UserObject.isUserSelf(r72) == false) goto L49;
            r6 = true;
            goto L49
        L44:
            TLRPC.Chat r73 = r12.getMessagesController().getChat(Long.valueOf(-r7.id));
            if (r0 == false) goto L49;
            if (r73 == null) goto L49;
            this.contacts.add(r73);
        L49:
            r5 = r5 + 1;
            r6 = r6;
            goto L24
        L50:
            if (r6 == false) goto L52;
        L54:
            SearchAdapterHelper r122 = new SearchAdapterHelper(false);
            this.searchAdapterHelper = r122;
            r122.setAllowGlobalResults(false);
            this.searchAdapterHelper.setDelegate(new UsersSelectActivity$GroupCreateAdapter$$ExternalSyntheticLambda0(this));
            return;
        L52:
            if (r12.allowSelf == false) goto L54;
            this.contacts.add(0, r12.getMessagesController().getUser(Long.valueOf(r12.getUserConfig().clientUserId)));
            goto L54
        L22:
            r0 = false;
            goto L23
        L18:
            r132 = false;
            goto L20
        L6:
            if (UsersSelectActivity.m21120$$Nest$fgettype(r12) != 2) goto L9;
            this.usersStartRow = (!r12.doNotNewChats ? 1 : 0) + 5;
            goto L16
        L9:
            if (UsersSelectActivity.m21120$$Nest$fgettype(r12) == 0) goto L11;
            this.usersStartRow = 0;
            goto L16
        L11:
            if (UsersSelectActivity.m21113$$Nest$fgetisInclude(r12) == false) goto L13;
            this.usersStartRow = 7;
            goto L16
        L13:
            this.usersStartRow = 5;
            goto L16
        }

        private /* synthetic */ void lambda$new$0(int r2) {
            if (this.searchRunnable == null) goto L5;
        L7:
            notifyDataSetChanged();
            return;
        L5:
            if (this.searchAdapterHelper.isSearchInProgress() == true) goto L7;
            UsersSelectActivity.m21107$$Nest$fgetemptyView(this.this$0).showProgress(false);
            goto L7
        }

        private /* synthetic */ void lambda$searchDialogs$1(String r19, boolean r20, boolean r21) {
            String r1 = r19.trim().toLowerCase();
            if (r1.length() != 0) goto L6;
            updateSearchResults(new ArrayList(), new ArrayList());
            return;
        L6:
            String r2 = LocaleController.getInstance().getTranslitString(r1);
            String r4 = null;
            if (r1.equals(r2) == false) goto L9;
        L10:
            r2 = null;
        L11:
            int r3 = 0;
            char r5 = 1;
            if (r2 == null) goto L14;
            int r6 = 1;
        L15:
            int r62 = r6 + 1;
            String[] r7 = new String[r62];
            r7[0] = r1;
            if (r2 == null) goto L18;
            r7[1] = r2;
        L18:
            ArrayList<Object> r12 = new ArrayList();
            ArrayList<CharSequence> r22 = new ArrayList();
            int r8 = 0;
        L20:
            if (r8 >= this.contacts.size()) goto L71;
            TLObject r9 = this.contacts.get(r8);
            int r10 = 3;
            String[] r11 = new String[3];
            boolean r122 = r9 instanceof TLRPC.User;
            if (r122 == false) goto L36;
            TLRPC.User r14 = (TLRPC.User) r9;
            int r192 = r3;
            r11[r192] = ContactsController.formatName(r14.first_name, r14.last_name).toLowerCase();
            String r32 = UserObject.getPublicUsername(r14);
            if (UserObject.isReplyUser(r14) == false) goto L27;
            r11[2] = LocaleController.getString(R.string.RepliesTitle).toLowerCase();
        L39:
            String r142 = LocaleController.getInstance().getTranslitString(r11[r192]);
            r11[r5] = r142;
            if (r11[r192].equals(r142) == false) goto L42;
            r11[r5] = r4;
        L42:
            int r143 = r192;
            int r15 = r143;
        L43:
            if (r143 >= r62) goto L30;
            String r13 = r7[r143];
            int r42 = r192;
        L45:
            if (r42 >= r10) goto L55;
            String r102 = r11[r42];
            if (r102 != null) goto L49;
            int r17 = r42;
        L54:
            r42 = r17 + 1;
            r10 = 3;
            goto L45
        L49:
            if (r102.startsWith(r13) == true) goto L52;
            r17 = r42;
            if (r102.contains(" " + r13) == false) goto L54;
        L52:
            r15 = 1;
        L55:
            if (r15 != 0) goto L60;
            if (r32 == null) goto L60;
            if (r32.toLowerCase().startsWith(r13) == false) goto L60;
            r15 = 2;
        L60:
            if (r15 != 0) goto L61;
            r143 = r143 + 1;
            r5 = 1;
            r4 = null;
            r10 = 3;
            goto L43
        L61:
            char r43 = 1;
            if (r15 != 1) goto L67;
            if (r122 == false) goto L66;
            TLRPC.User r33 = (TLRPC.User) r9;
            r22.add(AndroidUtilities.generateSearchName(r33.first_name, r33.last_name, r13));
        L65:
            String r103 = null;
        L68:
            r12.add(r9);
        L70:
            r8 = r8 + 1;
            r3 = r192;
            r5 = r43;
            r4 = r103;
            goto L20
        L66:
            r22.add(AndroidUtilities.generateSearchName(((TLRPC.Chat) r9).title, null, r13));
            goto L65
        L67:
            r103 = null;
            r22.add(AndroidUtilities.generateSearchName("@" + r32, null, "@" + r13));
        L30:
            r103 = r4;
            r43 = r5;
            goto L70
        L27:
            if (UserObject.isUserSelf(r14) == false) goto L33;
            if (this.this$0.allowSelf == false) goto L30;
            r11[2] = LocaleController.getString(R.string.SavedMessages).toLowerCase();
            goto L39
        L33:
            if (r14.bot == false) goto L39;
            if (r20 == true) goto L39;
        L36:
            r192 = r3;
            TLRPC.Chat r34 = (TLRPC.Chat) r9;
            r11[r192] = r34.title.toLowerCase();
            r32 = r34.username;
            if (r21 == true) goto L39;
        L71:
            updateSearchResults(r12, r22);
            return;
        L14:
            r6 = 0;
            goto L15
        L9:
            if (r2.length() != 0) goto L11;
            goto L10
        }

        private /* synthetic */ void lambda$searchDialogs$2(String r13, boolean r14, boolean r15) {
            this.searchAdapterHelper.queryServerSearch(r13, true, r14, r14, this.this$0.allowSelf, false, 0, false, 0, 0);
            DispatchQueue r132 = Utilities.searchQueue;
            UsersSelectActivity$GroupCreateAdapter$$ExternalSyntheticLambda3 r142 = new UsersSelectActivity$GroupCreateAdapter$$ExternalSyntheticLambda3(this, r13, r15, r14);
            this.searchRunnable = r142;
            r132.postRunnable(r142);
        }

        private /* synthetic */ void lambda$searchDialogs$3(String r2, boolean r3, boolean r4) {
            AndroidUtilities.runOnUIThread(new UsersSelectActivity$GroupCreateAdapter$$ExternalSyntheticLambda2(this, r2, r3, r4));
        }

        private /* synthetic */ void lambda$updateSearchResults$4(ArrayList r2, ArrayList r3) {
            if (this.searching == true) goto L5;
            return;
        L5:
            this.searchRunnable = null;
            this.searchResult = r2;
            this.searchResultNames = r3;
            this.searchAdapterHelper.mergeResults(r2);
            if (this.searching == true) goto L8;
        L10:
            notifyDataSetChanged();
            return;
        L8:
            if (this.searchAdapterHelper.isSearchInProgress() == true) goto L10;
            UsersSelectActivity.m21107$$Nest$fgetemptyView(this.this$0).showProgress(false);
            goto L10
        }

        private void updateSearchResults(ArrayList<Object> r2, ArrayList<CharSequence> r3) {
            AndroidUtilities.runOnUIThread(new UsersSelectActivity$GroupCreateAdapter$$ExternalSyntheticLambda4(this, r2, r3));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (this.searching == true) goto L5;
            UsersSelectActivity r0 = this.this$0;
            int r2 = 0;
            if (r0.noChatTypes == true) goto L19;
            int r02 = UsersSelectActivity.m21120$$Nest$fgettype(r0);
            UsersSelectActivity r1 = this.this$0;
            if (r02 != 2) goto L13;
            r2 = (!r1.doNotNewChats ? 1 : 0) + 3;
            goto L19
        L13:
            if (UsersSelectActivity.m21120$$Nest$fgettype(r1) != 0) goto L19;
            if (UsersSelectActivity.m21113$$Nest$fgetisInclude(this.this$0) == false) goto L17;
            r2 = 7;
            goto L19
        L17:
            r2 = 5;
        L19:
            return r2 + this.contacts.size();
        L5:
            return this.searchResult.size() + (this.searchAdapterHelper.getLocalServerSearch().size() + this.searchAdapterHelper.getGlobalSearch().size());
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r5) {
            if (this.searching == false) goto L5;
            return 1;
        L5:
            UsersSelectActivity r0 = this.this$0;
            if (r0.noChatTypes == false) goto L10;
            if (r5 != 0) goto L26;
            return 2;
        L26:
            return 1;
        L10:
            if (UsersSelectActivity.m21120$$Nest$fgettype(r0) != 2) goto L16;
            if (r5 != 0) goto L13;
        L14:
            return 2;
        L13:
            if (r5 != ((!this.this$0.doNotNewChats ? 1 : 0) + 4)) goto L26;
        L16:
            if (UsersSelectActivity.m21120$$Nest$fgettype(this.this$0) != 0) goto L26;
            if (UsersSelectActivity.m21113$$Nest$fgetisInclude(this.this$0) == false) goto L23;
            if (r5 != 0) goto L21;
        L22:
            return 2;
        L21:
            if (r5 != 6) goto L26;
        L23:
            if (r5 == 0) goto L25;
            if (r5 != 4) goto L26;
        L25:
            return 2;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public String getLetter(int r1) {
            return null;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public void getPositionForScrollProgress(RecyclerListView r1, float r2, int[] r3) {
            r3[0] = (int) (getItemCount() * r2);
            r3[1] = 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() != 1) goto L5;
            return true;
        L5:
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r6v0 */
        /* JADX WARN: Type inference failed for: r6v1 */
        /* JADX WARN: Type inference failed for: r6v10 */
        /* JADX WARN: Type inference failed for: r6v11 */
        /* JADX WARN: Type inference failed for: r6v12 */
        /* JADX WARN: Type inference failed for: r6v13 */
        /* JADX WARN: Type inference failed for: r6v14 */
        /* JADX WARN: Type inference failed for: r6v2 */
        /* JADX WARN: Type inference failed for: r6v8, types: [android.text.SpannableStringBuilder] */
        /* JADX WARN: Type inference failed for: r6v9 */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r18, int r19) {
            int r3 = r18.getItemViewType();
            int r4 = 2;
            if (r3 == 1) goto L14;
            if (r3 != 2) goto L156;
            GraySectionCell r1 = (GraySectionCell) r18.itemView;
            if (r19 == 0) goto L9;
        L12:
            r1.setText(LocaleController.getString(R.string.FilterChats));
            return;
        L9:
            if (this.this$0.noChatTypes == true) goto L12;
            r1.setText(LocaleController.getString(R.string.FilterChatTypes));
            return;
        L156:
            return;
        L14:
            GroupCreateUserCell r12 = (GroupCreateUserCell) r18.itemView;
            ?? r6 = 0;
            r6 = 0;
            r6 = 0;
            r6 = 0;
            if (this.searching == false) goto L59;
            int r32 = this.searchResult.size();
            int r8 = this.searchAdapterHelper.getGlobalSearch().size();
            int r9 = this.searchAdapterHelper.getLocalServerSearch().size();
            if (r19 < 0) goto L20;
            if (r19 >= r32) goto L20;
            Object r82 = this.searchResult.get(r19);
        L30:
            if (r82 != null) goto L32;
        L98:
            CharSequence r2 = null;
        L100:
            if ((r82 instanceof TLRPC.User) == false) goto L103;
            long r11 = ((TLRPC.User) r82).id;
        L107:
            if (UsersSelectActivity.m21120$$Nest$fgettype(this.this$0) != 2) goto L110;
        L122:
            long r182 = 0;
            boolean r42 = true;
            boolean r33 = false;
            SpannableStringBuilder r62 = r6;
        L135:
            if (r42 == false) goto L137;
            r12.setAlpha(1.0f);
        L138:
            r12.setObject(r82, r2, r62);
            SimpleTextView r22 = r12.getStatusTextView();
            if (r33 == false) goto L141;
            int r34 = Theme.key_windowBackgroundWhiteBlueText;
        L142:
            r22.setTextColor(Theme.getColor(r34));
            if (r11 != r182) goto L145;
            return;
        L145:
            if (UsersSelectActivity.m21117$$Nest$fgetselectedContacts(this.this$0).indexOfKey(r11) < 0) goto L147;
            boolean r0 = true;
        L148:
            r12.setChecked(r0, false);
            r12.setCheckBoxEnabled(true);
            return;
        L147:
            r0 = false;
            goto L148
        L141:
            r34 = Theme.key_windowBackgroundWhiteGrayText;
            goto L142
        L137:
            r12.setAlpha(0.5f);
            goto L138
        L110:
            if (UsersSelectActivity.m21120$$Nest$fgettype(this.this$0) == 0) goto L112;
            r182 = 0;
            if (this.this$0.getMessagesController().dialogs_dict.get(r11) == null) goto L126;
            int r35 = this.this$0.getMessagesController().dialogs_dict.get(r11).ttl_period;
        L128:
            if (r35 <= 0) goto L130;
            SpannableStringBuilder r63 = new SpannableStringBuilder();
            r63.append("d");
            r63.setSpan(new ColoredImageSpan(R.drawable.msg_mini_fireon), 0, 1, 0);
            r63.append(LocaleController.formatString(R.string.AutoDeleteAfter, new Object[]{LocaleController.formatTTLString(r35)}).toLowerCase());
            boolean r36 = true;
            SpannableStringBuilder r64 = r63;
        L132:
            if ((r82 instanceof TLRPC.Chat) == false) goto L134;
            r42 = ChatObject.canUserDoAdminAction((TLRPC.Chat) r82, 13);
            r33 = r36;
            r62 = r64;
            goto L135
        L134:
            r42 = true;
            r33 = r36;
            r62 = r64;
            goto L135
        L130:
            SpannableStringBuilder r37 = new SpannableStringBuilder();
            r37.append("d");
            r37.setSpan(new ColoredImageSpan(R.drawable.msg_mini_fireoff), 0, 1, 0);
            r37.append(LocaleController.getString(R.string.AutoDeleteDisabled));
            r64 = r37;
            r36 = false;
            goto L132
        L126:
            r35 = 0;
            goto L128
        L112:
            if (this.searching == true) goto L122;
            Paint.FontMetricsInt r38 = r12.getStatusTextView().getPaint().getFontMetricsInt();
            r6 = new SpannableStringBuilder();
            ArrayList<MessagesController.DialogFilter> r43 = this.this$0.getMessagesController().dialogFilters;
            int r13 = r43.size();
            int r14 = 0;
        L114:
            if (r14 >= r13) goto L122;
            MessagesController.DialogFilter r15 = r43.get(r14);
            if (r15.includesDialog(this.this$0.getAccountInstance(), r11) == false) goto L121;
            if (r6.length() <= 0) goto L120;
            r6.append(", ");
        L120:
            r6.append(MessageObject.replaceAnimatedEmoji(Emoji.replaceEmoji(new SpannableStringBuilder(r15.name), r38, false), r15.entities, r38));
        L121:
            r14 = r14 + 1;
            goto L114
        L103:
            if ((r82 instanceof TLRPC.Chat) == false) goto L105;
            r11 = -((TLRPC.Chat) r82).id;
            goto L107
        L105:
            r11 = 0;
            goto L107
        L32:
            if ((r82 instanceof TLRPC.User) == false) goto L34;
            String r92 = ((TLRPC.User) r82).username;
        L36:
            if (r19 >= r32) goto L44;
            r2 = this.searchResultNames.get(r19);
            if (r2 == null) goto L100;
            if (TextUtils.isEmpty(r92) == true) goto L100;
            if (r2.toString().startsWith("@" + r92) == false) goto L100;
            r6 = r2;
            r2 = null;
            goto L100
        L44:
            if (r19 <= r32) goto L98;
            if (TextUtils.isEmpty(r92) == true) goto L98;
            String r23 = this.searchAdapterHelper.getLastFoundUsername();
            if (r23.startsWith("@") == false) goto L150;
            r23 = r23.substring(1);
        L150:
            SpannableStringBuilder r39 = new SpannableStringBuilder();     // Catch: Exception -> L58
            r39.append("@");     // Catch: Exception -> L58
            r39.append(r92);     // Catch: Exception -> L58
            int r10 = AndroidUtilities.indexOfIgnoreCase(r92, r23);     // Catch: Exception -> L58
            if (r10 == (-1)) goto L57;
            int r24 = r23.length();     // Catch: Exception -> L58
            if (r10 != 0) goto L55;
            r24 = r24 + 1;     // Catch: Exception -> L58
        L56:
            r39.setSpan(new ForegroundColorSpan(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText4)), r10, r24 + r10, 33);     // Catch: Exception -> L58
            goto L57
        L55:
            r10 = r10 + 1;     // Catch: Exception -> L58
        L57:
            r2 = null;
            r6 = r39;
        L58:
            r2 = null;
            r6 = r92;
            goto L100
        L34:
            r92 = ChatObject.getPublicUsername((TLRPC.Chat) r82);
        L20:
            if (r19 < r32) goto L25;
            if (r19 >= (r9 + r32)) goto L25;
            r82 = this.searchAdapterHelper.getLocalServerSearch().get(r19 - r32);
        L25:
            if (r19 > (r32 + r9)) goto L27;
        L29:
            r82 = null;
            goto L30
        L27:
            if (r19 >= ((r8 + r32) + r9)) goto L29;
            r82 = this.searchAdapterHelper.getGlobalSearch().get((r19 - r32) - r9);
            goto L30
        L59:
            int r310 = this.usersStartRow;
            if (r19 >= r310) goto L97;
            String r83 = "non_contacts";
            if (UsersSelectActivity.m21120$$Nest$fgettype(this.this$0) != 2) goto L75;
            if (r19 != 1) goto L65;
            String r25 = LocaleController.getString(R.string.FilterExistingChats);
            r83 = "existing_chats";
            r4 = 1;
        L91:
            r12.setObject(r83, r25, null);
            if ((UsersSelectActivity.m21109$$Nest$fgetfilterFlags(this.this$0) & r4) != r4) goto L94;
            boolean r02 = true;
        L95:
            r12.setChecked(r02, false);
            r12.setCheckBoxEnabled(true);
            return;
        L94:
            r02 = false;
            goto L95
        L65:
            if (r19 != 2) goto L70;
            if (this.this$0.doNotNewChats == true) goto L70;
            r25 = LocaleController.getString(R.string.FilterNewChats);
            r83 = "new_chats";
        L70:
            if (r19 != ((!this.this$0.doNotNewChats ? 1 : 0) + 2)) goto L73;
            r25 = LocaleController.getString(R.string.FilterContacts);
            r4 = 4;
        L72:
            r83 = "contacts";
            goto L91
        L73:
            r25 = LocaleController.getString(R.string.FilterNonContacts);
            r4 = 8;
            goto L91
        L75:
            if (UsersSelectActivity.m21113$$Nest$fgetisInclude(this.this$0) == false) goto L86;
            if (r19 != 1) goto L78;
            r25 = LocaleController.getString(R.string.FilterContacts);
            r4 = MessagesController.DIALOG_FILTER_FLAG_CONTACTS;
            goto L72
        L78:
            if (r19 != 2) goto L81;
            r25 = LocaleController.getString(R.string.FilterNonContacts);
            r4 = MessagesController.DIALOG_FILTER_FLAG_NON_CONTACTS;
            goto L91
        L81:
            if (r19 != 3) goto L83;
            r25 = LocaleController.getString(R.string.FilterGroups);
            r4 = MessagesController.DIALOG_FILTER_FLAG_GROUPS;
            r83 = "groups";
            goto L91
        L83:
            if (r19 != 4) goto L85;
            r25 = LocaleController.getString(R.string.FilterChannels);
            r4 = MessagesController.DIALOG_FILTER_FLAG_CHANNELS;
            r83 = "channels";
            goto L91
        L85:
            r25 = LocaleController.getString(R.string.FilterBots);
            r4 = MessagesController.DIALOG_FILTER_FLAG_BOTS;
            r83 = "bots";
            goto L91
        L86:
            if (r19 != 1) goto L88;
            r25 = LocaleController.getString(R.string.FilterMuted);
            r4 = MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_MUTED;
            r83 = "muted";
            goto L91
        L88:
            if (r19 != 2) goto L90;
            r25 = LocaleController.getString(R.string.FilterRead);
            r4 = MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_READ;
            r83 = "read";
            goto L91
        L90:
            r25 = LocaleController.getString(R.string.FilterArchived);
            r4 = MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_ARCHIVED;
            r83 = "archived";
            goto L91
        L97:
            r82 = this.contacts.get(r19 - r310);
            goto L98
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            if (r3 == 1) goto L5;
            View r22 = new GraySectionCell(this.context);
        L7:
            return new RecyclerListView.Holder(r22);
        L5:
            r22 = new GroupCreateUserCell(this.context, 1, 0, true);
            goto L7
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewRecycled(RecyclerView.ViewHolder r1) {
            View r0 = r1.itemView;
            if ((r0 instanceof GroupCreateUserCell) == false) goto L6;
            ((GroupCreateUserCell) r0).recycle();
            return;
        }

        public void searchDialogs(String r15) {
            if (this.searchRunnable == null) goto L5;
            Utilities.searchQueue.cancelRunnable(this.searchRunnable);
            this.searchRunnable = null;
        L5:
            boolean r2 = false;
            if (UsersSelectActivity.m21120$$Nest$fgettype(this.this$0) == 2) goto L8;
            boolean r0 = true;
        L10:
            if (UsersSelectActivity.m21120$$Nest$fgettype(this.this$0) == 2) goto L12;
            r2 = true;
        L12:
            if (r15 != null) goto L15;
            this.searchResult.clear();
            this.searchResultNames.clear();
            this.searchAdapterHelper.mergeResults(null);
            this.searchAdapterHelper.queryServerSearch(null, true, false, false, false, false, 0, false, 0, 0);
            notifyDataSetChanged();
            return;
        L15:
            DispatchQueue r1 = Utilities.searchQueue;
            UsersSelectActivity$GroupCreateAdapter$$ExternalSyntheticLambda1 r3 = new UsersSelectActivity$GroupCreateAdapter$$ExternalSyntheticLambda1(this, r15, r2, r0);
            this.searchRunnable = r3;
            r1.postRunnable(r3, 300);
            return;
        L8:
            r0 = false;
            goto L10
        }

        public void setSearching(boolean r2) {
            if (this.searching != r2) goto L5;
            return;
        L5:
            this.searching = r2;
            notifyDataSetChanged();
        }
    }

    public static class ItemDecoration extends RecyclerView.ItemDecoration {
        private boolean single;
        private int skipRows;

        private ItemDecoration() {
        }

        public /* synthetic */ ItemDecoration(UsersSelectActivityIA r1) {
            this();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r1, View r2, RecyclerView r3, RecyclerView.State r4) {
            super.getItemOffsets(r1, r2, r3, r4);
            r1.top = 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void onDraw(Canvas r13, RecyclerView r14, RecyclerView.State r15) {
            int r152 = r14.getWidth();
            int r0 = r14.getChildCount() - (!this.single ? 1 : 0);
            int r2 = 0;
        L3:
            if (r2 >= r0) goto L26;
            View r3 = r14.getChildAt(r2);
            if (r2 >= (r0 - 1)) goto L7;
            View r4 = r14.getChildAt(r2 + 1);
        L9:
            if (r14.getChildAdapterPosition(r3) >= this.skipRows) goto L11;
        L14:
            Canvas r6 = r13;
        L25:
            r2 = r2 + 1;
            r13 = r6;
            goto L3
        L11:
            if ((r3 instanceof GraySectionCell) == true) goto L14;
            if ((r4 instanceof GraySectionCell) == true) goto L14;
            int r32 = r3.getBottom();
            if (LocaleController.isRTL == false) goto L19;
            float r42 = 0.0f;
        L18:
            float r7 = r42;
            float r8 = r32;
            if (LocaleController.isRTL == false) goto L23;
            int r33 = AndroidUtilities.dp(72.0f);
        L24:
            r6 = r13;
            r6.drawLine(r7, r8, r152 - r33, r8, Theme.dividerPaint);
            goto L25
        L23:
            r33 = 0;
            goto L24
        L19:
            r42 = AndroidUtilities.dp(72.0f);
            goto L18
        L7:
            r4 = null;
            goto L9
        }
    }

    public class SpansContainer extends ViewGroup {
        private View addingSpan;
        private boolean animationStarted;
        private ArrayList<Animator> animators;
        private AnimatorSet currentAnimation;
        private View removingSpan;
        final /* synthetic */ UsersSelectActivity this$0;

        /* renamed from: org.telegram.ui.UsersSelectActivity$SpansContainer$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ SpansContainer this$1;

            public AnonymousClass1(SpansContainer r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                SpansContainer.m21135$$Nest$fputaddingSpan(this.this$1, null);
                SpansContainer.m21137$$Nest$fputcurrentAnimation(this.this$1, null);
                SpansContainer.m21136$$Nest$fputanimationStarted(this.this$1, false);
                UsersSelectActivity.m21106$$Nest$fgeteditText(this.this$1.this$0).setAllowDrawCursor(true);
            }
        }

        /* renamed from: org.telegram.ui.UsersSelectActivity$SpansContainer$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ SpansContainer this$1;
            final /* synthetic */ GroupCreateSpan val$span;

            public AnonymousClass2(SpansContainer r1, GroupCreateSpan r2) {
                this.this$1 = r1;
                this.val$span = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                this.this$1.removeView(this.val$span);
                SpansContainer.m21138$$Nest$fputremovingSpan(this.this$1, null);
                SpansContainer.m21137$$Nest$fputcurrentAnimation(this.this$1, null);
                SpansContainer.m21136$$Nest$fputanimationStarted(this.this$1, false);
                UsersSelectActivity.m21106$$Nest$fgeteditText(this.this$1.this$0).setAllowDrawCursor(true);
                if (UsersSelectActivity.m21103$$Nest$fgetallSpans(this.this$1.this$0).isEmpty() == false) goto L6;
                UsersSelectActivity.m21106$$Nest$fgeteditText(this.this$1.this$0).setHintVisible(true, true);
                return;
            }
        }

        /* renamed from: -$$Nest$fputaddingSpan, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m21135$$Nest$fputaddingSpan(SpansContainer r0, View r1) {
            r0.addingSpan = r1;
        }

        /* renamed from: -$$Nest$fputanimationStarted, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m21136$$Nest$fputanimationStarted(SpansContainer r0, boolean r1) {
            r0.animationStarted = r1;
        }

        /* renamed from: -$$Nest$fputcurrentAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m21137$$Nest$fputcurrentAnimation(SpansContainer r0, AnimatorSet r1) {
            r0.currentAnimation = r1;
        }

        /* renamed from: -$$Nest$fputremovingSpan, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m21138$$Nest$fputremovingSpan(SpansContainer r0, View r1) {
            r0.removingSpan = r1;
        }

        public SpansContainer(UsersSelectActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.animators = new ArrayList();
        }

        public void addSpan(GroupCreateSpan r5, boolean r6) {
            UsersSelectActivity.m21103$$Nest$fgetallSpans(this.this$0).add(r5);
            long r0 = r5.getUid();
            if (r0 <= (-9223372036854775801L)) goto L5;
            UsersSelectActivity r2 = this.this$0;
            UsersSelectActivity.m21128$$Nest$fputselectedCount(r2, UsersSelectActivity.m21118$$Nest$fgetselectedCount(r2) + 1);
        L5:
            UsersSelectActivity.m21117$$Nest$fgetselectedContacts(this.this$0).put(r0, r5);
            UsersSelectActivity.m21106$$Nest$fgeteditText(this.this$0).setHintVisible(false, TextUtils.isEmpty(UsersSelectActivity.m21106$$Nest$fgeteditText(this.this$0).getText()));
            AnimatorSet r02 = this.currentAnimation;
            if (r02 != null) goto L8;
        L10:
            this.animationStarted = false;
            if (r6 == false) goto L13;
            AnimatorSet r62 = new AnimatorSet();
            this.currentAnimation = r62;
            r62.addListener(new AnonymousClass1(this));
            this.currentAnimation.setDuration(150);
            this.addingSpan = r5;
            this.animators.clear();
            ArrayList<Animator> r63 = this.animators;
            Property r3 = View.SCALE_X;
            r63.add(ObjectAnimator.ofFloat(this.addingSpan, r3, new float[]{0.01f, 1.0f}));
            ArrayList<Animator> r64 = this.animators;
            Property r32 = View.SCALE_Y;
            r64.add(ObjectAnimator.ofFloat(this.addingSpan, r32, new float[]{0.01f, 1.0f}));
            ArrayList<Animator> r65 = this.animators;
            Property r22 = View.ALPHA;
            r65.add(ObjectAnimator.ofFloat(this.addingSpan, r22, new float[]{0.0f, 1.0f}));
        L13:
            addView(r5);
            return;
        L8:
            if (r02.isRunning() == false) goto L10;
            this.currentAnimation.setupEndValues();
            this.currentAnimation.cancel();
            goto L10
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onLayout(boolean r2, int r3, int r4, int r5, int r6) {
            int r22 = getChildCount();
            int r42 = 0;
        L3:
            if (r42 >= r22) goto L5;
            View r52 = getChildAt(r42);
            r52.layout(0, 0, r52.getMeasuredWidth(), r52.getMeasuredHeight());
            r42 = r42 + 1;
            goto L3
        }

        @Override // android.view.View
        public void onMeasure(int r17, int r18) {
            int r1 = getChildCount();
            int r2 = View.MeasureSpec.getSize(r17);
            int r3 = r2 - AndroidUtilities.dp(26.0f);
            int r5 = AndroidUtilities.dp(10.0f);
            int r4 = AndroidUtilities.dp(10.0f);
            int r7 = 0;
            int r8 = 0;
            int r9 = 0;
        L3:
            Property r11 = View.TRANSLATION_Y;
            Property r12 = View.TRANSLATION_X;
            if (r7 >= r1) goto L35;
            View r15 = getChildAt(r7);
            if ((r15 instanceof GroupCreateSpan) == false) goto L33;
            r15.measure(View.MeasureSpec.makeMeasureSpec(r2, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(32.0f), TLObject.FLAG_30));
            if (r15 == this.removingSpan) goto L14;
            if ((r15.getMeasuredWidth() + r8) <= r3) goto L14;
            r5 = r5 + (r15.getMeasuredHeight() + AndroidUtilities.dp(8.0f));
            r8 = 0;
        L14:
            if ((r15.getMeasuredWidth() + r9) <= r3) goto L16;
            r4 = r4 + (r15.getMeasuredHeight() + AndroidUtilities.dp(8.0f));
            r9 = 0;
        L16:
            int r13 = AndroidUtilities.dp(13.0f) + r8;
            if (this.animationStarted == true) goto L30;
            View r14 = this.removingSpan;
            if (r15 != r14) goto L21;
            r15.setTranslationX(AndroidUtilities.dp(13.0f) + r9);
            r15.setTranslationY(r4);
            goto L30
        L21:
            if (r14 == null) goto L28;
            float r132 = r13;
            if (r15.getTranslationX() == r132) goto L25;
            this.animators.add(ObjectAnimator.ofFloat(r15, r12, new float[]{r132}));
        L25:
            float r122 = r5;
            if (r15.getTranslationY() == r122) goto L30;
            this.animators.add(ObjectAnimator.ofFloat(r15, r11, new float[]{r122}));
            goto L30
        L28:
            r15.setTranslationX(r13);
            r15.setTranslationY(r5);
        L30:
            if (r15 == this.removingSpan) goto L32;
            r8 = r8 + (r15.getMeasuredWidth() + AndroidUtilities.dp(9.0f));
        L32:
            r9 = r9 + (r15.getMeasuredWidth() + AndroidUtilities.dp(9.0f));
        L33:
            r7 = r7 + 1;
            goto L3
        L35:
            if (AndroidUtilities.isTablet() == false) goto L37;
            int r16 = AndroidUtilities.dp(372.0f) / 3;
        L39:
            if ((r3 - r8) >= r16) goto L42;
            r5 = r5 + AndroidUtilities.dp(40.0f);
            r8 = 0;
        L42:
            if ((r3 - r9) >= r16) goto L44;
            r4 = r4 + AndroidUtilities.dp(40.0f);
        L44:
            UsersSelectActivity.m21106$$Nest$fgeteditText(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r3 - r8, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(32.0f), TLObject.FLAG_30));
            if (this.animationStarted == true) goto L60;
            int r42 = r4 + AndroidUtilities.dp(42.0f);
            int r82 = r8 + AndroidUtilities.dp(16.0f);
            UsersSelectActivity.m21123$$Nest$fputfieldY(this.this$0, r5);
            if (this.currentAnimation == null) goto L58;
            int r52 = r5 + AndroidUtilities.dp(42.0f);
            if (UsersSelectActivity.m21104$$Nest$fgetcontainerHeight(this.this$0) == r52) goto L51;
            this.animators.add(ObjectAnimator.ofInt(this.this$0, "containerHeight", new int[]{r52}));
        L51:
            float r32 = r82;
            if (UsersSelectActivity.m21106$$Nest$fgeteditText(this.this$0).getTranslationX() == r32) goto L55;
            this.animators.add(ObjectAnimator.ofFloat(UsersSelectActivity.m21106$$Nest$fgeteditText(this.this$0), r12, new float[]{r32}));
        L55:
            if (UsersSelectActivity.m21106$$Nest$fgeteditText(this.this$0).getTranslationY() == UsersSelectActivity.m21108$$Nest$fgetfieldY(this.this$0)) goto L57;
            this.animators.add(ObjectAnimator.ofFloat(UsersSelectActivity.m21106$$Nest$fgeteditText(this.this$0), r11, new float[]{UsersSelectActivity.m21108$$Nest$fgetfieldY(this.this$0)}));
        L57:
            UsersSelectActivity.m21106$$Nest$fgeteditText(this.this$0).setAllowDrawCursor(false);
            this.currentAnimation.playTogether(this.animators);
            this.currentAnimation.start();
            this.animationStarted = true;
        L66:
            setMeasuredDimension(r2, UsersSelectActivity.m21104$$Nest$fgetcontainerHeight(this.this$0));
            return;
        L58:
            UsersSelectActivity.m21121$$Nest$fputcontainerHeight(this.this$0, r42);
            UsersSelectActivity.m21106$$Nest$fgeteditText(this.this$0).setTranslationX(r82);
            UsersSelectActivity.m21106$$Nest$fgeteditText(this.this$0).setTranslationY(UsersSelectActivity.m21108$$Nest$fgetfieldY(this.this$0));
            goto L66
        L60:
            if (this.currentAnimation == null) goto L66;
            if (UsersSelectActivity.m21112$$Nest$fgetignoreScrollEvent(this.this$0) == true) goto L66;
            if (this.removingSpan != null) goto L66;
            UsersSelectActivity.m21106$$Nest$fgeteditText(this.this$0).bringPointIntoView(UsersSelectActivity.m21106$$Nest$fgeteditText(this.this$0).getSelectionStart());
            goto L66
        L37:
            Point r19 = AndroidUtilities.displaySize;
            r16 = (Math.min(r19.x, r19.y) - AndroidUtilities.dp(158.0f)) / 3;
            goto L39
        }

        public void removeSpan(GroupCreateSpan r7) {
            UsersSelectActivity.m21125$$Nest$fputignoreScrollEvent(this.this$0, true);
            long r2 = r7.getUid();
            if (r2 <= (-9223372036854775801L)) goto L5;
            UsersSelectActivity r0 = this.this$0;
            UsersSelectActivity.m21128$$Nest$fputselectedCount(r0, UsersSelectActivity.m21118$$Nest$fgetselectedCount(r0) - 1);
        L5:
            UsersSelectActivity.m21117$$Nest$fgetselectedContacts(this.this$0).remove(r2);
            UsersSelectActivity.m21103$$Nest$fgetallSpans(this.this$0).remove(r7);
            r7.setOnClickListener(null);
            AnimatorSet r02 = this.currentAnimation;
            if (r02 == null) goto L8;
            r02.setupEndValues();
            this.currentAnimation.cancel();
        L8:
            this.animationStarted = false;
            AnimatorSet r03 = new AnimatorSet();
            this.currentAnimation = r03;
            r03.addListener(new AnonymousClass2(this, r7));
            this.currentAnimation.setDuration(150);
            this.removingSpan = r7;
            this.animators.clear();
            ArrayList<Animator> r72 = this.animators;
            Property r3 = View.SCALE_X;
            r72.add(ObjectAnimator.ofFloat(this.removingSpan, r3, new float[]{1.0f, 0.01f}));
            ArrayList<Animator> r73 = this.animators;
            Property r32 = View.SCALE_Y;
            r73.add(ObjectAnimator.ofFloat(this.removingSpan, r32, new float[]{1.0f, 0.01f}));
            ArrayList<Animator> r74 = this.animators;
            Property r22 = View.ALPHA;
            r74.add(ObjectAnimator.ofFloat(this.removingSpan, r22, new float[]{1.0f, 0.0f}));
            requestLayout();
        }
    }

    /* renamed from: $r8$lambda$1_dJgUwVzBvitu-yFU_uZc0geuo, reason: not valid java name */
    public static /* synthetic */ void m21100$r8$lambda$1_dJgUwVzBvituyFU_uZc0geuo(UsersSelectActivity r0, View r1) {
        r0.lambda$createView$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$HrQoRplIURDY6PoarCWOTXmr_YI(UsersSelectActivity r0, Context r1, View r2, int r3) {
        r0.lambda$createView$1(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$S_XMa_S43HAscWpAXW30L-7C-aY, reason: not valid java name */
    public static /* synthetic */ void m21101$r8$lambda$S_XMa_S43HAscWpAXW30L7CaY(UsersSelectActivity r0) {
        r0.lambda$getThemeDescriptions$3();
    }

    public static /* synthetic */ void $r8$lambda$fWCtUcviuW3GBwkjngRPBrHfX5k(UsersSelectActivity r0, View r1) {
        r0.lambda$createView$2(r1);
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GroupCreateAdapter m21102$$Nest$fgetadapter(UsersSelectActivity r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetallSpans, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m21103$$Nest$fgetallSpans(UsersSelectActivity r0) {
        return r0.allSpans;
    }

    /* renamed from: -$$Nest$fgetcontainerHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21104$$Nest$fgetcontainerHeight(UsersSelectActivity r0) {
        return r0.containerHeight;
    }

    /* renamed from: -$$Nest$fgetcurrentDeletingSpan, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GroupCreateSpan m21105$$Nest$fgetcurrentDeletingSpan(UsersSelectActivity r0) {
        return r0.currentDeletingSpan;
    }

    /* renamed from: -$$Nest$fgeteditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextBoldCursor m21106$$Nest$fgeteditText(UsersSelectActivity r0) {
        return r0.editText;
    }

    /* renamed from: -$$Nest$fgetemptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StickerEmptyView m21107$$Nest$fgetemptyView(UsersSelectActivity r0) {
        return r0.emptyView;
    }

    /* renamed from: -$$Nest$fgetfieldY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21108$$Nest$fgetfieldY(UsersSelectActivity r0) {
        return r0.fieldY;
    }

    /* renamed from: -$$Nest$fgetfilterFlags, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21109$$Nest$fgetfilterFlags(UsersSelectActivity r0) {
        return r0.filterFlags;
    }

    /* renamed from: -$$Nest$fgetfloatingButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FragmentFloatingButton m21110$$Nest$fgetfloatingButton(UsersSelectActivity r0) {
        return r0.floatingButton;
    }

    /* renamed from: -$$Nest$fgetfloatingButtonLp, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout.LayoutParams m21111$$Nest$fgetfloatingButtonLp(UsersSelectActivity r0) {
        return r0.floatingButtonLp;
    }

    /* renamed from: -$$Nest$fgetignoreScrollEvent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21112$$Nest$fgetignoreScrollEvent(UsersSelectActivity r0) {
        return r0.ignoreScrollEvent;
    }

    /* renamed from: -$$Nest$fgetisInclude, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21113$$Nest$fgetisInclude(UsersSelectActivity r0) {
        return r0.isInclude;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m21114$$Nest$fgetlistView(UsersSelectActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetprogressView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FlickerLoadingView m21115$$Nest$fgetprogressView(UsersSelectActivity r0) {
        return r0.progressView;
    }

    /* renamed from: -$$Nest$fgetscrollView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ScrollView m21116$$Nest$fgetscrollView(UsersSelectActivity r0) {
        return r0.scrollView;
    }

    /* renamed from: -$$Nest$fgetselectedContacts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m21117$$Nest$fgetselectedContacts(UsersSelectActivity r0) {
        return r0.selectedContacts;
    }

    /* renamed from: -$$Nest$fgetselectedCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21118$$Nest$fgetselectedCount(UsersSelectActivity r0) {
        return r0.selectedCount;
    }

    /* renamed from: -$$Nest$fgetspansContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SpansContainer m21119$$Nest$fgetspansContainer(UsersSelectActivity r0) {
        return r0.spansContainer;
    }

    /* renamed from: -$$Nest$fgettype, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21120$$Nest$fgettype(UsersSelectActivity r0) {
        return r0.type;
    }

    /* renamed from: -$$Nest$fputcontainerHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21121$$Nest$fputcontainerHeight(UsersSelectActivity r0, int r1) {
        r0.containerHeight = r1;
    }

    /* renamed from: -$$Nest$fputcurrentDeletingSpan, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21122$$Nest$fputcurrentDeletingSpan(UsersSelectActivity r0, GroupCreateSpan r1) {
        r0.currentDeletingSpan = r1;
    }

    /* renamed from: -$$Nest$fputfieldY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21123$$Nest$fputfieldY(UsersSelectActivity r0, int r1) {
        r0.fieldY = r1;
    }

    /* renamed from: -$$Nest$fputfilterFlags, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21124$$Nest$fputfilterFlags(UsersSelectActivity r0, int r1) {
        r0.filterFlags = r1;
    }

    /* renamed from: -$$Nest$fputignoreScrollEvent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21125$$Nest$fputignoreScrollEvent(UsersSelectActivity r0, boolean r1) {
        r0.ignoreScrollEvent = r1;
    }

    /* renamed from: -$$Nest$fputsearchWas, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21126$$Nest$fputsearchWas(UsersSelectActivity r0, boolean r1) {
        r0.searchWas = r1;
    }

    /* renamed from: -$$Nest$fputsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21127$$Nest$fputsearching(UsersSelectActivity r0, boolean r1) {
        r0.searching = r1;
    }

    /* renamed from: -$$Nest$fputselectedCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21128$$Nest$fputselectedCount(UsersSelectActivity r0, int r1) {
        r0.selectedCount = r1;
    }

    /* renamed from: -$$Nest$mcheckVisibleRows, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21129$$Nest$mcheckVisibleRows(UsersSelectActivity r0) {
        r0.checkVisibleRows();
    }

    /* renamed from: -$$Nest$mcloseSearch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21130$$Nest$mcloseSearch(UsersSelectActivity r0) {
        r0.closeSearch();
    }

    /* renamed from: -$$Nest$monDonePressed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21131$$Nest$monDonePressed(UsersSelectActivity r0, boolean r1) {
        return r0.onDonePressed(r1);
    }

    /* renamed from: -$$Nest$mupdateHint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21132$$Nest$mupdateHint(UsersSelectActivity r0) {
        r0.updateHint();
    }

    public UsersSelectActivity(int r2) {
        this.selectedContacts = new LongSparseArray();
        this.allSpans = new ArrayList();
        this.type = r2;
        boolean r0 = true;
        if (r2 != 1) goto L6;
        r0 = false;
    L6:
        this.allowSelf = r0;
    }

    public UsersSelectActivity(boolean r2, ArrayList<Long> r3, int r4) {
        this.selectedContacts = new LongSparseArray();
        this.allSpans = new ArrayList();
        this.isInclude = r2;
        this.filterFlags = r4;
        this.initialIds = r3;
        this.type = 0;
        this.allowSelf = true;
    }

    public static /* synthetic */ INavigationLayout access$000(UsersSelectActivity r0) {
        return r0.parentLayout;
    }

    private void checkVisibleRows() {
        int r0 = this.listView.getChildCount();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L55;
        View r3 = this.listView.getChildAt(r2);
        if ((r3 instanceof GroupCreateUserCell) == false) goto L54;
        GroupCreateUserCell r32 = (GroupCreateUserCell) r3;
        Object r4 = r32.getObject();
        if ((r4 instanceof String) == false) goto L41;
        String r42 = (String) r4;
        long r8 = -9223372036854775800L;
        switch(r42.hashCode()) {
            case -1716307998: goto L38;
            case -1237460524: goto L36;
            case -1197490811: goto L33;
            case -567451565: goto L30;
            case -268161860: goto L27;
            case 3029900: goto L24;
            case 3496342: goto L21;
            case 104264043: goto L18;
            case 151051367: goto L15;
            case 1432626128: goto L12;
            default: goto L39;
        };
    L38:
        r42.equals("archived");
    L39:
        r8 = -9223372036854775801L;
    L48:
        if (r8 == 0) goto L54;
        if (this.selectedContacts.indexOfKey(r8) < 0) goto L52;
        boolean r43 = true;
    L53:
        r32.setChecked(r43, true);
        r32.setCheckBoxEnabled(true);
        goto L54
    L52:
        r43 = false;
        goto L53
    L12:
        if (r42.equals("channels") == false) goto L39;
        r8 = -9223372036854775805L;
        goto L48
    L15:
        if (r42.equals("existing_chats") == false) goto L39;
    L18:
        if (r42.equals("muted") == false) goto L39;
        r8 = -9223372036854775803L;
        goto L48
    L21:
        if (r42.equals("read") == false) goto L39;
        r8 = -9223372036854775802L;
        goto L48
    L24:
        if (r42.equals("bots") == false) goto L39;
        r8 = -9223372036854775804L;
        goto L48
    L27:
        if (r42.equals("new_chats") == false) goto L39;
    L30:
        if (r42.equals("contacts") == false) goto L39;
        r8 = Long.MIN_VALUE;
        goto L48
    L33:
        if (r42.equals("non_contacts") == false) goto L39;
        r8 = -9223372036854775807L;
        goto L48
    L36:
        if (r42.equals("groups") == false) goto L39;
        r8 = -9223372036854775806L;
        goto L48
    L41:
        if ((r4 instanceof TLRPC.User) == false) goto L44;
        r8 = ((TLRPC.User) r4).id;
        goto L48
    L44:
        if ((r4 instanceof TLRPC.Chat) == false) goto L46;
        r8 = -((TLRPC.Chat) r4).id;
        goto L48
    L46:
        r8 = 0;
    L54:
        r2 = r2 + 1;
        goto L3
    }

    private void closeSearch() {
        this.searching = false;
        this.searchWas = false;
        this.adapter.setSearching(false);
        this.adapter.searchDialogs(null);
        this.listView.setFastScrollVisible(true);
        this.listView.setVerticalScrollBarEnabled(false);
        this.emptyView.title.setText(LocaleController.getString(R.string.NoContacts));
    }

    private /* synthetic */ void lambda$createView$0(View r1) {
        this.editText.clearFocus();
        this.editText.requestFocus();
        AndroidUtilities.showKeyboard(this.editText);
    }

    private /* synthetic */ void lambda$createView$1(Context r11, View r12, int r13) {
        if ((r12 instanceof GroupCreateUserCell) == false) goto L83;
        GroupCreateUserCell r122 = (GroupCreateUserCell) r12;
        Object r0 = r122.getObject();
        boolean r1 = r0 instanceof String;
        if (r1 == false) goto L40;
        long r4 = -9223372036854775807L;
        int r8 = 4;
        if (this.type != 2) goto L19;
        if (r13 != 1) goto L10;
        r4 = -9223372036854775800L;
        r8 = 1;
    L35:
        boolean r132 = r122.isChecked();
        int r3 = this.filterFlags;
        if (r132 == false) goto L38;
        this.filterFlags = (~r8) & r3;
    L51:
        if (this.selectedContacts.indexOfKey(r4) < 0) goto L53;
        boolean r133 = true;
    L54:
        if (r133 == false) goto L56;
        this.spansContainer.removeSpan(this.selectedContacts.get(r4));
        UsersSelectActivity r5 = this;
    L72:
        r5.updateHint();
        if (r5.searching == false) goto L75;
    L78:
        AndroidUtilities.showKeyboard(r5.editText);
    L80:
        if (r5.editText.length() <= 0) goto L85;
        r5.editText.setText(null);
        return;
    L85:
        return;
    L75:
        if (r5.searchWas == true) goto L78;
        r122.setChecked(!r133, true);
        goto L80
    L56:
        if (r1 == true) goto L62;
        if (getUserConfig().isPremium() == true) goto L62;
        if (this.selectedCount < MessagesController.getInstance(this.currentAccount).dialogFiltersChatsLimitDefault) goto L62;
    L63:
        LimitReachedBottomSheet r42 = new LimitReachedBottomSheet(this, r11, 4, this.currentAccount, null);
        r42.setCurrentValue(this.selectedCount);
        showDialog(r42);
        return;
    L62:
        if (this.selectedCount >= MessagesController.getInstance(this.currentAccount).dialogFiltersChatsLimitPremium) goto L63;
        r5 = this;
        if ((r0 instanceof TLRPC.User) == false) goto L69;
        MessagesController.getInstance(r5.currentAccount).putUser((TLRPC.User) r0, !r5.searching);
    L71:
        GroupCreateSpan r10 = new GroupCreateSpan(r5.editText.getContext(), r0);
        r5.spansContainer.addSpan(r10, true);
        r10.setOnClickListener(r5);
        goto L72
    L69:
        if ((r0 instanceof TLRPC.Chat) == false) goto L71;
        MessagesController.getInstance(r5.currentAccount).putChat((TLRPC.Chat) r0, !r5.searching);
        goto L71
    L53:
        r133 = false;
        goto L54
    L38:
        this.filterFlags = r3 | r8;
        goto L51
    L10:
        if (r13 != 2) goto L15;
        if (this.doNotNewChats == true) goto L15;
        r4 = -9223372036854775799L;
        r8 = 2;
    L15:
        if (r13 != ((!this.doNotNewChats ? 1 : 0) + 2)) goto L17;
    L16:
        r4 = Long.MIN_VALUE;
        goto L35
    L17:
        r8 = 8;
        goto L35
    L19:
        if (this.isInclude == false) goto L30;
        if (r13 != 1) goto L22;
        r8 = MessagesController.DIALOG_FILTER_FLAG_CONTACTS;
        goto L16
    L22:
        if (r13 != 2) goto L25;
        r8 = MessagesController.DIALOG_FILTER_FLAG_NON_CONTACTS;
        goto L35
    L25:
        if (r13 != 3) goto L27;
        r8 = MessagesController.DIALOG_FILTER_FLAG_GROUPS;
        r4 = -9223372036854775806L;
        goto L35
    L27:
        if (r13 != 4) goto L29;
        r8 = MessagesController.DIALOG_FILTER_FLAG_CHANNELS;
        r4 = -9223372036854775805L;
        goto L35
    L29:
        r8 = MessagesController.DIALOG_FILTER_FLAG_BOTS;
        r4 = -9223372036854775804L;
        goto L35
    L30:
        if (r13 != 1) goto L32;
        r8 = MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_MUTED;
        r4 = -9223372036854775803L;
        goto L35
    L32:
        if (r13 != 2) goto L34;
        r8 = MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_READ;
        r4 = -9223372036854775802L;
        goto L35
    L34:
        r8 = MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_ARCHIVED;
        r4 = -9223372036854775801L;
        goto L35
    L40:
        if ((r0 instanceof TLRPC.User) == false) goto L43;
        r4 = ((TLRPC.User) r0).id;
        goto L51
    L43:
        if ((r0 instanceof TLRPC.Chat) == false) goto L84;
        TLRPC.Chat r134 = (TLRPC.Chat) r0;
        r4 = -r134.id;
        if (this.type != 1) goto L51;
        if (ChatObject.canUserDoAdminAction(r134, 13) == true) goto L51;
        BulletinFactory.of(this).createErrorBulletin(LocaleController.getString(R.string.NeedAdminRightForSetAutoDeleteTimer)).show();
        return;
    L84:
        return;
    }

    private /* synthetic */ void lambda$createView$2(View r1) {
        onDonePressed(true);
    }

    private /* synthetic */ void lambda$getThemeDescriptions$3() {
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L10;
        int r02 = r0.getChildCount();
        int r2 = 0;
    L5:
        if (r2 >= r02) goto L14;
        View r3 = this.listView.getChildAt(r2);
        if ((r3 instanceof GroupCreateUserCell) == false) goto L9;
        ((GroupCreateUserCell) r3).update(0);
    L9:
        r2 = r2 + 1;
        goto L5
    L14:
        return;
    }

    private boolean onDonePressed(boolean r6) {
        ArrayList<Long> r62 = new ArrayList();
        int r0 = 0;
    L4:
        if (r0 >= this.selectedContacts.size()) goto L10;
        if (this.selectedContacts.keyAt(r0) <= (-9223372036854775799L)) goto L9;
        r62.add(Long.valueOf(this.selectedContacts.keyAt(r0)));
    L9:
        r0 = r0 + 1;
        goto L4
    L10:
        FilterUsersActivityDelegate r02 = this.delegate;
        if (r02 == null) goto L13;
        r02.didSelectChats(r62, this.filterFlags);
    L13:
        finishFragment();
        return true;
    }

    private void updateHint() {
        int r0 = this.type;
        if (r0 != 0) goto L15;
        if (getUserConfig().isPremium() == false) goto L7;
        int r02 = getMessagesController().dialogFiltersChatsLimitPremium;
    L8:
        int r2 = this.selectedCount;
        ActionBar r3 = this.actionBar;
        if (r2 != 0) goto L12;
        r3.setSubtitle(LocaleController.formatString("MembersCountZero", R.string.MembersCountZero, new Object[]{LocaleController.formatPluralString("Chats", r02, new Object[0])}));
        return;
    L12:
        r3.setSubtitle(String.format(LocaleController.getPluralString("MembersCountSelected", r2), new Object[]{Integer.valueOf(this.selectedCount), Integer.valueOf(r02)}));
        return;
    L7:
        r02 = getMessagesController().dialogFiltersChatsLimitDefault;
        goto L8
    L15:
        if (r0 != 1) goto L30;
        this.actionBar.setTitle(_UrlKt.FRAGMENT_ENCODE_SET);
        this.actionBar.setSubtitle(_UrlKt.FRAGMENT_ENCODE_SET);
        int r03 = this.selectedCount;
        AnimatedAvatarContainer r32 = this.animatedAvatarContainer;
        if (r03 != 0) goto L24;
        r32.getTitle().setText(LocaleController.getString(R.string.SelectChats), true);
        int r04 = this.ttlPeriod;
        AnimatedAvatarContainer r4 = this.animatedAvatarContainer;
        if (r04 <= 0) goto L22;
        r4.getSubtitleTextView().setText(LocaleController.getString(R.string.SelectChatsForAutoDelete), true);
        return;
    L22:
        r4.getSubtitleTextView().setText(LocaleController.getString(R.string.SelectChatsForDisableAutoDelete), true);
        return;
    L24:
        AnimatedTextView r05 = r32.getTitle();
        int r22 = this.selectedCount;
        r05.setText(LocaleController.formatPluralString("Chats", r22, new Object[]{Integer.valueOf(r22)}));
        int r06 = this.ttlPeriod;
        AnimatedAvatarContainer r42 = this.animatedAvatarContainer;
        if (r06 <= 0) goto L28;
        r42.getSubtitleTextView().setText(LocaleController.getString(R.string.SelectChatsForAutoDelete2));
        return;
    L28:
        r42.getSubtitleTextView().setText(LocaleController.getString(R.string.SelectChatsForDisableAutoDelete2));
        return;
    }

    public UsersSelectActivity asPrivateChats() {
        this.type = 2;
        this.allowSelf = false;
        return this;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r18) {
        this.searching = false;
        this.searchWas = false;
        this.allSpans.clear();
        this.selectedContacts.clear();
        this.currentDeletingSpan = null;
        if (this.type != 1) goto L12;
        AnimatedAvatarContainer r4 = new AnimatedAvatarContainer(getContext());
        this.animatedAvatarContainer = r4;
        ActionBar r6 = this.actionBar;
        boolean r7 = LocaleController.isRTL;
        if (r7 == false) goto L7;
        float r13 = 0.0f;
    L8:
        if (r7 == false) goto L10;
        float r15 = 64.0f;
    L11:
        r6.addView(r4, LayoutHelper.createFrame(-1, -1.0f, 0, r13, 0.0f, r15, 0.0f));
        this.actionBar.setAllowOverlayTitle(false);
        goto L12
    L10:
        r15 = 0.0f;
        goto L11
    L7:
        r13 = 64.0f;
    L12:
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        int r42 = this.type;
        if (r42 == 0) goto L18;
        if (r42 == 2) goto L18;
        if (r42 != 1) goto L22;
        updateHint();
    L22:
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        AnonymousClass2 r43 = new AnonymousClass2(this, r18);
        this.fragmentView = r43;
        AnonymousClass2 r44 = r43;
        AnonymousClass3 r72 = new AnonymousClass3(this, r18);
        this.scrollView = r72;
        r72.setVerticalScrollBarEnabled(false);
        AndroidUtilities.setScrollViewEdgeEffectColor(this.scrollView, Theme.getColor(Theme.key_windowBackgroundWhite));
        r44.addView(this.scrollView);
        SpansContainer r73 = new SpansContainer(this, r18);
        this.spansContainer = r73;
        this.scrollView.addView(r73, LayoutHelper.createFrame(-1, -2.0f));
        this.spansContainer.setOnClickListener(new UsersSelectActivity$$ExternalSyntheticLambda0(this));
        AnonymousClass4 r74 = new AnonymousClass4(this, r18);
        this.editText = r74;
        r74.setTextSize(1, 16.0f);
        this.editText.setHintColor(Theme.getColor(Theme.key_groupcreate_hintText));
        this.editText.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.editText.setCursorColor(Theme.getColor(Theme.key_groupcreate_cursor));
        this.editText.setCursorWidth(1.5f);
        EditTextBoldCursor r75 = this.editText;
        r75.setInputType(r75.getInputType() | 176);
        this.editText.setSingleLine(true);
        this.editText.setBackgroundDrawable(null);
        this.editText.setVerticalScrollBarEnabled(false);
        this.editText.setHorizontalScrollBarEnabled(false);
        this.editText.setTextIsSelectable(false);
        this.editText.setPadding(0, 0, 0, 0);
        this.editText.setImeOptions(268435462);
        EditTextBoldCursor r76 = this.editText;
        int r9 = 5;
        if (LocaleController.isRTL == false) goto L25;
        int r8 = 5;
    L26:
        r76.setGravity(r8 | 16);
        this.spansContainer.addView(this.editText);
        this.editText.setHintText(LocaleController.getString(R.string.SearchForPeopleAndGroups));
        this.editText.setCustomSelectionActionModeCallback(new AnonymousClass5(this));
        this.editText.setOnKeyListener(new AnonymousClass6(this));
        this.editText.addTextChangedListener(new AnonymousClass7(this));
        FlickerLoadingView r77 = new FlickerLoadingView(r18);
        this.progressView = r77;
        r77.setViewType(10);
        this.progressView.showDate(false);
        this.progressView.setItemsCount(3);
        FlickerLoadingView r78 = this.progressView;
        int r82 = Theme.key_actionBarDefaultSubmenuBackground;
        int r11 = Theme.key_listSelector;
        r78.setColors(r82, r11, r11);
        r44.addView(this.progressView);
        AnonymousClass8 r79 = new AnonymousClass8(this, r18, this.progressView, 1);
        this.emptyView = r79;
        r79.showProgress(ContactsController.getInstance(this.currentAccount).isLoadingContacts());
        this.emptyView.title.setText(LocaleController.getString(R.string.NoContacts));
        r44.addView(this.emptyView);
        LinearLayoutManager r710 = new LinearLayoutManager(r18, 1, false);
        RecyclerListView r83 = new RecyclerListView(r18);
        this.listView = r83;
        r83.setFastScrollEnabled(0);
        this.listView.setEmptyView(this.emptyView);
        RecyclerListView r84 = this.listView;
        GroupCreateAdapter r112 = new GroupCreateAdapter(this, r18);
        this.adapter = r112;
        r84.setAdapter(r112);
        this.listView.setLayoutManager(r710);
        this.listView.setVerticalScrollBarEnabled(false);
        RecyclerListView r711 = this.listView;
        if (LocaleController.isRTL == false) goto L29;
        int r85 = 1;
    L30:
        r711.setVerticalScrollbarPosition(r85);
        this.listView.addItemDecoration(new ItemDecoration(null));
        r44.addView(this.listView);
        this.listView.setOnItemClickListener(new UsersSelectActivity$$ExternalSyntheticLambda1(this, r18));
        this.listView.setOnScrollListener(new AnonymousClass9(this));
        this.floatingButtonLp = FragmentFloatingButton.createDefaultLayoutParams();
        FragmentFloatingButton r3 = new FragmentFloatingButton(r18, this.resourceProvider);
        this.floatingButton = r3;
        r3.setImageResource(R.drawable.floating_check);
        r44.addView(this.floatingButton, this.floatingButtonLp);
        this.floatingButton.setOnClickListener(new UsersSelectActivity$$ExternalSyntheticLambda2(this));
        this.floatingButton.setContentDescription(LocaleController.getString(R.string.Next));
        if (this.isInclude == true) goto L34;
        r9 = 3;
    L34:
        int r1 = 1;
    L35:
        if (r1 > r9) goto L68;
        String r45 = "non_contacts";
        int r712 = 4;
        if (this.type != 2) goto L49;
        if (r1 != 1) goto L40;
        r45 = "existing_chats";
        r712 = 1;
    L65:
        if ((this.filterFlags & r712) == 0) goto L67;
        GroupCreateSpan r32 = new GroupCreateSpan(this.editText.getContext(), r45);
        this.spansContainer.addSpan(r32, false);
        r32.setOnClickListener(this);
    L67:
        r1 = r1 + 1;
        goto L35
    L40:
        if (r1 != 2) goto L45;
        if (this.doNotNewChats == true) goto L45;
        r45 = "new_chats";
        r712 = 2;
    L45:
        if (r1 != ((!this.doNotNewChats ? 1 : 0) + 2)) goto L47;
    L46:
        r45 = "contacts";
        goto L65
    L47:
        r712 = 8;
        goto L65
    L49:
        if (this.isInclude == false) goto L59;
        if (r1 != 1) goto L52;
        r712 = MessagesController.DIALOG_FILTER_FLAG_CONTACTS;
        goto L46
    L52:
        if (r1 != 2) goto L54;
        r712 = MessagesController.DIALOG_FILTER_FLAG_NON_CONTACTS;
        goto L65
    L54:
        if (r1 != 3) goto L56;
        r712 = MessagesController.DIALOG_FILTER_FLAG_GROUPS;
        r45 = "groups";
        goto L65
    L56:
        if (r1 != 4) goto L58;
        r712 = MessagesController.DIALOG_FILTER_FLAG_CHANNELS;
        r45 = "channels";
        goto L65
    L58:
        r712 = MessagesController.DIALOG_FILTER_FLAG_BOTS;
        r45 = "bots";
        goto L65
    L59:
        if (r1 != 1) goto L61;
        r712 = MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_MUTED;
        r45 = "muted";
        goto L65
    L61:
        if (r1 != 2) goto L63;
        r712 = MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_READ;
        r45 = "read";
        goto L65
    L63:
        r712 = MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_ARCHIVED;
        r45 = "archived";
        goto L65
    L68:
        ArrayList<Long> r12 = this.initialIds;
        if (r12 != null) goto L71;
    L82:
        updateHint();
        return this.fragmentView;
    L71:
        if (r12.isEmpty() == true) goto L82;
        int r14 = this.initialIds.size();
        int r33 = 0;
    L73:
        if (r33 >= r14) goto L82;
        Long r46 = this.initialIds.get(r33);
        if (r46.longValue() <= 0) goto L77;
        Object r47 = getMessagesController().getUser(r46);
    L78:
        if (r47 == null) goto L81;
        GroupCreateSpan r5 = new GroupCreateSpan(this.editText.getContext(), r47);
        this.spansContainer.addSpan(r5, false);
        r5.setOnClickListener(this);
    L81:
        r33 = r33 + 1;
        goto L73
    L77:
        r47 = getMessagesController().getChat(Long.valueOf(-r46.longValue()));
        goto L78
    L29:
        r85 = 2;
        goto L30
    L25:
        r8 = 3;
    L18:
        boolean r48 = this.isInclude;
        ActionBar r713 = this.actionBar;
        if (r48 == false) goto L21;
        r713.setTitle(LocaleController.getString(R.string.FilterAlwaysShow));
        goto L22
    L21:
        r713.setTitle(LocaleController.getString(R.string.FilterNeverShow));
        goto L22
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        int r0 = 0;
        if (r3 != NotificationCenter.contactsDidLoad) goto L12;
        StickerEmptyView r32 = this.emptyView;
        if (r32 == null) goto L7;
        r32.showProgress(false);
    L7:
        GroupCreateAdapter r2 = this.adapter;
        if (r2 == null) goto L34;
        r2.notifyDataSetChanged();
        return;
    L34:
        return;
    L12:
        if (r3 != NotificationCenter.updateInterfaces) goto L27;
        if (this.listView == null) goto L35;
        int r33 = ((Integer) r5[0]).intValue();
        int r42 = this.listView.getChildCount();
        if ((MessagesController.UPDATE_MASK_AVATAR & r33) != 0) goto L21;
        if ((MessagesController.UPDATE_MASK_NAME & r33) != 0) goto L21;
        if ((MessagesController.UPDATE_MASK_STATUS & r33) != 0) goto L21;
        return;
    L21:
        if (r0 >= r42) goto L38;
        View r52 = this.listView.getChildAt(r0);
        if ((r52 instanceof GroupCreateUserCell) == false) goto L25;
        ((GroupCreateUserCell) r52).update(r33);
    L25:
        r0 = r0 + 1;
        goto L21
    L38:
        return;
    L35:
        return;
    L27:
        if (r3 != NotificationCenter.chatDidCreated) goto L37;
        removeSelfFromStack();
        return;
    }

    @Keep
    public int getContainerHeight() {
        return this.containerHeight;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        UsersSelectActivity$$ExternalSyntheticLambda3 r8 = new UsersSelectActivity$$ExternalSyntheticLambda3(this);
        View r10 = this.fragmentView;
        int r11 = ThemeDescription.FLAG_BACKGROUND;
        int r16 = Theme.key_windowBackgroundWhite;
        r1.add(new ThemeDescription(r10, r11, null, null, null, null, r16));
        ActionBar r2 = this.actionBar;
        int r19 = ThemeDescription.FLAG_BACKGROUND;
        int r24 = Theme.key_actionBarDefault;
        r1.add(new ThemeDescription(r2, r19, null, null, null, null, r24));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, r24));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.scrollView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, r16));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_FASTSCROLL, null, null, null, null, Theme.key_fastScrollActive));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_FASTSCROLL, null, null, null, null, Theme.key_fastScrollInactive));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_FASTSCROLL, null, null, null, null, Theme.key_fastScrollText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.emptyView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_emptyListPlaceholder));
        r1.add(new ThemeDescription(this.emptyView, ThemeDescription.FLAG_PROGRESSBAR, null, null, null, null, Theme.key_progressCircle));
        r1.add(new ThemeDescription(this.editText, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r1.add(new ThemeDescription(this.editText, ThemeDescription.FLAG_HINTTEXTCOLOR, null, null, null, null, Theme.key_groupcreate_hintText));
        r1.add(new ThemeDescription(this.editText, ThemeDescription.FLAG_CURSORCOLOR, null, null, null, null, Theme.key_groupcreate_cursor));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{GraySectionCell.class}, new String[]{"textView"}, null, null, null, Theme.key_graySectionText));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{GraySectionCell.class}, null, null, null, Theme.key_graySection));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{GroupCreateUserCell.class}, new String[]{"textView"}, null, null, null, Theme.key_groupcreate_sectionText));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{GroupCreateUserCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_checkbox));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{GroupCreateUserCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_checkboxDisabled));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{GroupCreateUserCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_checkboxCheck));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{GroupCreateUserCell.class}, new String[]{"statusTextView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueText));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{GroupCreateUserCell.class}, new String[]{"statusTextView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{GroupCreateUserCell.class}, null, Theme.avatarDrawables, null, Theme.key_avatar_text));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundRed));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundOrange));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundViolet));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundGreen));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundCyan));
        int r162 = Theme.key_avatar_backgroundBlue;
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, r162));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundPink));
        r1.add(new ThemeDescription(this.spansContainer, 0, new Class[]{GroupCreateSpan.class}, null, null, null, Theme.key_groupcreate_spanBackground));
        r1.add(new ThemeDescription(this.spansContainer, 0, new Class[]{GroupCreateSpan.class}, null, null, null, Theme.key_groupcreate_spanText));
        r1.add(new ThemeDescription(this.spansContainer, 0, new Class[]{GroupCreateSpan.class}, null, null, null, Theme.key_groupcreate_spanDelete));
        r1.add(new ThemeDescription(this.spansContainer, 0, new Class[]{GroupCreateSpan.class}, null, null, null, r162));
        return r1;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View r9) {
        GroupCreateSpan r92 = (GroupCreateSpan) r9;
        if (r92.isDeleting() == false) goto L44;
        this.currentDeletingSpan = null;
        this.spansContainer.removeSpan(r92);
        if (this.type != 2) goto L19;
        if (r92.getUid() != (-9223372036854775800L)) goto L10;
        this.filterFlags &= -2;
    L42:
        updateHint();
        checkVisibleRows();
        return;
    L10:
        if (r92.getUid() != (-9223372036854775799L)) goto L13;
        this.filterFlags &= -3;
        goto L42
    L13:
        if (r92.getUid() != Long.MIN_VALUE) goto L16;
        this.filterFlags &= -5;
        goto L42
    L16:
        if (r92.getUid() != (-9223372036854775807L)) goto L42;
        this.filterFlags &= -9;
        goto L42
    L19:
        if (r92.getUid() != Long.MIN_VALUE) goto L22;
        this.filterFlags &= ~MessagesController.DIALOG_FILTER_FLAG_CONTACTS;
        goto L42
    L22:
        if (r92.getUid() != (-9223372036854775807L)) goto L25;
        this.filterFlags &= ~MessagesController.DIALOG_FILTER_FLAG_NON_CONTACTS;
        goto L42
    L25:
        if (r92.getUid() != (-9223372036854775806L)) goto L28;
        this.filterFlags &= ~MessagesController.DIALOG_FILTER_FLAG_GROUPS;
        goto L42
    L28:
        if (r92.getUid() != (-9223372036854775805L)) goto L31;
        this.filterFlags &= ~MessagesController.DIALOG_FILTER_FLAG_CHANNELS;
        goto L42
    L31:
        if (r92.getUid() != (-9223372036854775804L)) goto L34;
        this.filterFlags &= ~MessagesController.DIALOG_FILTER_FLAG_BOTS;
        goto L42
    L34:
        if (r92.getUid() != (-9223372036854775803L)) goto L37;
        this.filterFlags &= ~MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_MUTED;
        goto L42
    L37:
        if (r92.getUid() != (-9223372036854775802L)) goto L40;
        this.filterFlags &= ~MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_READ;
        goto L42
    L40:
        if (r92.getUid() != (-9223372036854775801L)) goto L42;
        this.filterFlags &= ~MessagesController.DIALOG_FILTER_FLAG_EXCLUDE_ARCHIVED;
        goto L42
    L44:
        GroupCreateSpan r0 = this.currentDeletingSpan;
        if (r0 == null) goto L47;
        r0.cancelDeleteAnimation();
    L47:
        this.currentDeletingSpan = r92;
        r92.startDeleteAnimation();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.contactsDidLoad);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.updateInterfaces);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.chatDidCreated);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.contactsDidLoad);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.updateInterfaces);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.chatDidCreated);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        EditTextBoldCursor r0 = this.editText;
        if (r0 == null) goto L5;
        r0.requestFocus();
    L5:
        AndroidUtilities.requestAdjustResize(getParentActivity(), this.classGuid);
    }

    @Keep
    public void setContainerHeight(int r1) {
        this.containerHeight = r1;
        SpansContainer r0 = this.spansContainer;
        if (r0 == null) goto L6;
        r0.requestLayout();
        return;
    }

    public void setDelegate(FilterUsersActivityDelegate r1) {
        this.delegate = r1;
    }

    public void setTtlPeriod(int r1) {
        this.ttlPeriod = r1;
    }
}
