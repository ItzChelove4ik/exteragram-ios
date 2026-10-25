package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Property;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import me.vkryl.android.animator.FactorAnimator;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.GroupCreateSectionCell;
import org.telegram.ui.Cells.InviteUserCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.FragmentFloatingButton;
import org.telegram.ui.Components.GroupCreateSpan;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ShareAlert$$ExternalSyntheticLambda16;
import org.telegram.ui.Components.StickerEmptyView;
import org.telegram.ui.Components.blur3.DownscaleScrollableNoiseSuppressor;
import org.telegram.ui.Components.blur3.ViewGroupPartRenderer;
import org.telegram.ui.Components.blur3.capture.IBlur3Capture;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceRenderNode;
import org.telegram.ui.Components.inset.WindowAnimatedInsetsProvider;

/* loaded from: classes6.dex */
public class InviteContactsActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate, View.OnClickListener, FactorAnimator.Target, WindowAnimatedInsetsProvider.Listener {
    private final int ADDITIONAL_LIST_HEIGHT_DP;
    private InviteAdapter adapter;
    private final ArrayList<GroupCreateSpan> allSpans;
    private final FactorAnimator animatorSelectorContainerHeight;
    private GroupCreateSpan currentDeletingSpan;
    private StickerEmptyView emptyView;
    private int fieldY;
    private FragmentFloatingButton floatingButton;
    private IBlur3Capture iBlur3Capture;
    private boolean iBlur3Invalidated;
    private final RectF iBlur3PositionActionBar;
    private final ArrayList<RectF> iBlur3Positions;
    private final BlurredBackgroundSourceRenderNode iBlur3SourceGlassFrosted;
    private boolean ignoreScrollEvent;
    private int imeInsetAnimatedHeight;
    private LinearLayoutManager layoutManager;
    private RecyclerListView listView;
    private int maxSize;
    private int navigationBarHeight;
    private ArrayList<ContactsController.Contact> phoneBookContacts;
    private ScrollView scrollView;
    private final DownscaleScrollableNoiseSuppressor scrollableViewNoiseSuppressor;
    private SearchField searchField;
    private boolean searchWas;
    private boolean searching;
    private final HashMap<String, GroupCreateSpan> selectedContacts;
    private SpansContainer spansContainer;

    /* renamed from: org.telegram.ui.InviteContactsActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ InviteContactsActivity this$0;

        public AnonymousClass1(InviteContactsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.InviteContactsActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends FrameLayout {
        final /* synthetic */ InviteContactsActivity this$0;

        public AnonymousClass2(InviteContactsActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r4) {
            if (Build.VERSION.SDK_INT >= 31) goto L5;
        L16:
            super.dispatchDraw(r4);
            return;
        L5:
            if (InviteContactsActivity.m15539$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0) == null) goto L16;
            InviteContactsActivity.m15549$$Nest$mblur3_InvalidateBlur(this.this$0);
            int r0 = getMeasuredWidth();
            int r1 = getMeasuredHeight();
            if (InviteContactsActivity.m15532$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0) != null) goto L9;
        L15:
            InviteContactsActivity.m15544$$Nest$fputiBlur3Invalidated(this.this$0, false);
            goto L16
        L9:
            if (InviteContactsActivity.m15532$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).inRecording() == true) goto L15;
            if (InviteContactsActivity.m15532$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).needUpdateDisplayList(r0, r1) == false) goto L13;
        L14:
            RecordingCanvas r02 = InviteContactsActivity.m15532$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).beginRecording(r0, r1);
            InviteContactsActivity.m15539$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).draw(r02, -3);
            InviteContactsActivity.m15532$$Nest$fgetiBlur3SourceGlassFrosted(this.this$0).endRecording();
            goto L15
        L13:
            if (InviteContactsActivity.m15531$$Nest$fgetiBlur3Invalidated(this.this$0) == false) goto L15;
            goto L14
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            InviteContactsActivity.m15551$$Nest$mcheckUi_floatingButton(this.this$0);
            InviteContactsActivity.m15553$$Nest$mcheckUi_searchFieldY(this.this$0);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r8, int r9) {
            int r0 = View.MeasureSpec.getSize(r8);
            int r1 = View.MeasureSpec.getSize(r9);
            if (AndroidUtilities.isTablet() == true) goto L7;
            if (r1 > r0) goto L7;
            InviteContactsActivity.m15546$$Nest$fputmaxSize(this.this$0, AndroidUtilities.dp(56.0f));
        L8:
            measureChildWithMargins(InviteContactsActivity.access$000(this.this$0), r8, 0, r9, 0);
            ViewGroup.MarginLayoutParams r7 = (ViewGroup.MarginLayoutParams) InviteContactsActivity.m15529$$Nest$fgetemptyView(this.this$0).getLayoutParams();
            r7.topMargin = InviteContactsActivity.access$100(this.this$0).getMeasuredHeight() + AndroidUtilities.dp(48.0f);
            ViewGroup.LayoutParams r72 = InviteContactsActivity.m15540$$Nest$fgetsearchField(this.this$0).getLayoutParams();
            r72.height = InviteContactsActivity.m15536$$Nest$fgetmaxSize(this.this$0) + AndroidUtilities.dp(18.0f);
            InviteContactsActivity.m15552$$Nest$mcheckUi_listViewPadding(this.this$0);
            super.onMeasure(r8, r9);
            return;
        L7:
            InviteContactsActivity.m15546$$Nest$fputmaxSize(this.this$0, AndroidUtilities.dp(144.0f));
            goto L8
        }
    }

    /* renamed from: org.telegram.ui.InviteContactsActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends ScrollView {
        final /* synthetic */ InviteContactsActivity this$0;

        public AnonymousClass3(InviteContactsActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r4) {
            int r0 = r4.getAction();
            float r1 = InviteContactsActivity.m15528$$Nest$fgetanimatorSelectorContainerHeight(this.this$0).getFactor();
            float r2 = r4.getY();
            if (r0 != 0) goto L9;
            if (r2 <= r1) goto L9;
            return false;
        L9:
            return super.dispatchTouchEvent(r4);
        }

        @Override // android.widget.ScrollView, android.view.ViewGroup, android.view.ViewParent
        public boolean requestChildRectangleOnScreen(View r4, Rect r5, boolean r6) {
            if (InviteContactsActivity.m15533$$Nest$fgetignoreScrollEvent(this.this$0) == false) goto L6;
            InviteContactsActivity.m15545$$Nest$fputignoreScrollEvent(this.this$0, false);
            return false;
        L6:
            r5.offset(r4.getLeft() - r4.getScrollX(), r4.getTop() - r4.getScrollY());
            r5.top += InviteContactsActivity.m15530$$Nest$fgetfieldY(this.this$0) + AndroidUtilities.dp(20.0f);
            r5.bottom += InviteContactsActivity.m15530$$Nest$fgetfieldY(this.this$0) + AndroidUtilities.dp(50.0f);
            return super.requestChildRectangleOnScreen(r4, r5, r6);
        }
    }

    /* renamed from: org.telegram.ui.InviteContactsActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends RecyclerView.OnScrollListener {
        final /* synthetic */ InviteContactsActivity this$0;

        public AnonymousClass4(InviteContactsActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 1) goto L6;
            SearchField.m15562$$Nest$fgeteditText(InviteContactsActivity.m15540$$Nest$fgetsearchField(this.this$0)).hideActionMode();
            AndroidUtilities.hideKeyboard(SearchField.m15562$$Nest$fgeteditText(InviteContactsActivity.m15540$$Nest$fgetsearchField(this.this$0)));
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r2, int r3, int r4) {
            InviteContactsActivity.m15534$$Nest$fgetlayoutManager(this.this$0).findFirstVisibleItemPosition();
            View r22 = InviteContactsActivity.m15535$$Nest$fgetlistView(this.this$0).getChildAt(0);
            if (r22 == null) goto L6;
            r22.getTop();
        L6:
            if (Build.VERSION.SDK_INT >= 31) goto L8;
            return;
        L8:
            if (InviteContactsActivity.m15539$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0) == null) goto L12;
            InviteContactsActivity.m15539$$Nest$fgetscrollableViewNoiseSuppressor(this.this$0).onScrolled(r3, r4);
            InviteContactsActivity.m15549$$Nest$mblur3_InvalidateBlur(this.this$0);
            return;
        }
    }

    public class InviteAdapter extends RecyclerListView.SelectionAdapter {
        private final Context context;
        private ArrayList<ContactsController.Contact> searchResult;
        private ArrayList<CharSequence> searchResultNames;
        private Timer searchTimer;
        private boolean searching;
        final /* synthetic */ InviteContactsActivity this$0;

        /* renamed from: org.telegram.ui.InviteContactsActivity$InviteAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends TimerTask {
            final /* synthetic */ InviteAdapter this$1;
            final /* synthetic */ String val$query;

            /* renamed from: $r8$lambda$r-FhMeV3PJVA8Xyutn0FLBhQpWc, reason: not valid java name */
            public static /* synthetic */ void m15560$r8$lambda$rFhMeV3PJVA8Xyutn0FLBhQpWc(AnonymousClass1 r0, String r1) {
                r0.lambda$run$1(r1);
            }

            public static /* synthetic */ void $r8$lambda$w4tCYuJ8z9h9yOH4wVoEfctU6r0(AnonymousClass1 r0, String r1) {
                r0.lambda$run$0(r1);
            }

            public AnonymousClass1(InviteAdapter r1, String r2) {
                this.this$1 = r1;
                this.val$query = r2;
            }

            private /* synthetic */ void lambda$run$0(String r17) {
                String r1 = r17.trim().toLowerCase();
                if (r1.isEmpty() == false) goto L6;
                InviteAdapter.m15559$$Nest$mupdateSearchResults(this.this$1, new ArrayList(), new ArrayList());
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
                ArrayList r12 = new ArrayList();
                ArrayList r22 = new ArrayList();
                int r8 = 0;
            L19:
                int r9 = InviteContactsActivity.m15537$$Nest$fgetphoneBookContacts(this.this$1.this$0).size();
                InviteAdapter r10 = this.this$1;
                if (r8 >= r9) goto L40;
                ContactsController.Contact r92 = (ContactsController.Contact) InviteContactsActivity.m15537$$Nest$fgetphoneBookContacts(r10.this$0).get(r8);
                String r102 = ContactsController.formatName(r92.first_name, r92.last_name).toLowerCase();
                String r11 = LocaleController.getInstance().getTranslitString(r102);
                if (r102.equals(r11) == false) goto L24;
                r11 = null;
            L24:
                int r122 = r3;
                int r13 = r122;
            L25:
                if (r122 >= r62) goto L39;
                String r14 = r7[r122];
                if (r102.startsWith(r14) == false) goto L29;
            L35:
                r13 = 1;
            L36:
                if (r13 != 0) goto L37;
                r122 = r122 + 1;
                goto L25
            L37:
                r22.add(AndroidUtilities.generateSearchName(r92.first_name, r92.last_name, r14));
                r12.add(r92);
                goto L39
            L29:
                if (r102.contains(" " + r14) == true) goto L35;
                if (r11 == null) goto L36;
                if (r11.startsWith(r14) == true) goto L35;
                if (r11.contains(" " + r14) == false) goto L36;
            L39:
                r8 = r8 + 1;
                r3 = 0;
                goto L19
            L40:
                InviteAdapter.m15559$$Nest$mupdateSearchResults(r10, r12, r22);
                return;
            L14:
                r6 = 0;
                goto L15
            L9:
                if (r2.isEmpty() == false) goto L11;
                goto L10
            }

            private /* synthetic */ void lambda$run$1(String r3) {
                Utilities.searchQueue.postRunnable(new InviteContactsActivity$InviteAdapter$1$$ExternalSyntheticLambda1(this, r3));
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                InviteAdapter.m15557$$Nest$fgetsearchTimer(this.this$1).cancel();     // Catch: Exception -> L4
                InviteAdapter.m15558$$Nest$fputsearchTimer(this.this$1, null);     // Catch: Exception -> L4
            L6:
                AndroidUtilities.runOnUIThread(new InviteContactsActivity$InviteAdapter$1$$ExternalSyntheticLambda0(this, this.val$query));
                return;
            L4:
                e = move-exception;
                FileLog.e(e);
                goto L6
            }
        }

        public static /* synthetic */ void $r8$lambda$cAYAh0H6vpCOpM82o7xw5OA6Dms(InviteAdapter r0, ArrayList r1, ArrayList r2) {
            r0.lambda$updateSearchResults$0(r1, r2);
        }

        /* renamed from: -$$Nest$fgetsearchTimer, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Timer m15557$$Nest$fgetsearchTimer(InviteAdapter r0) {
            return r0.searchTimer;
        }

        /* renamed from: -$$Nest$fputsearchTimer, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15558$$Nest$fputsearchTimer(InviteAdapter r0, Timer r1) {
            r0.searchTimer = r1;
        }

        /* renamed from: -$$Nest$mupdateSearchResults, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15559$$Nest$mupdateSearchResults(InviteAdapter r0, ArrayList r1, ArrayList r2) {
            r0.updateSearchResults(r1, r2);
        }

        public InviteAdapter(InviteContactsActivity r1, Context r2) {
            this.this$0 = r1;
            this.searchResult = new ArrayList();
            this.searchResultNames = new ArrayList();
            this.context = r2;
        }

        private /* synthetic */ void lambda$updateSearchResults$0(ArrayList r2, ArrayList r3) {
            if (this.searching == true) goto L5;
            return;
        L5:
            this.searchResult = r2;
            this.searchResultNames = r3;
            notifyDataSetChanged();
            InviteContactsActivity.m15529$$Nest$fgetemptyView(this.this$0).showProgress(false);
        }

        private void updateSearchResults(ArrayList<ContactsController.Contact> r2, ArrayList<CharSequence> r3) {
            AndroidUtilities.runOnUIThread(new InviteContactsActivity$InviteAdapter$$ExternalSyntheticLambda0(this, r2, r3));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (this.searching == false) goto L7;
            return this.searchResult.size();
        L7:
            return InviteContactsActivity.m15537$$Nest$fgetphoneBookContacts(this.this$0).size() + 2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            if (this.searching == false) goto L5;
            return 0;
        L5:
            if (r1 != 0) goto L7;
            return 1;
        L7:
            if (r1 != 1) goto L12;
            return 2;
        L12:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() == 2) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            InviteContactsActivity.m15550$$Nest$mcheckUi_emptyViewVisible(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r3, int r4) {
            if (r3.getItemViewType() != 0) goto L10;
            InviteUserCell r32 = (InviteUserCell) r3.itemView;
            if (this.searching == false) goto L7;
            ContactsController.Contact r0 = this.searchResult.get(r4);
            CharSequence r42 = this.searchResultNames.get(r4);
        L8:
            r32.setUser(r0, r42);
            r32.setChecked(InviteContactsActivity.m15541$$Nest$fgetselectedContacts(this.this$0).containsKey(r0.key), false);
            return;
        L7:
            r0 = (ContactsController.Contact) InviteContactsActivity.m15537$$Nest$fgetphoneBookContacts(this.this$0).get(r4 - 2);
            r42 = null;
            goto L8
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            if (r4 != 1) goto L6;
            TextCell r32 = new TextCell(this.context);
            int r2 = Theme.key_windowBackgroundWhiteBlackText;
            r32.setColors(r2, r2);
            r32.setTextAndValueAndIcon(LocaleController.getString(R.string.ShareTelegram2), _UrlKt.FRAGMENT_ENCODE_SET, R.drawable.msg_shareout, false);
            View r33 = r32;
        L10:
            return new RecyclerListView.Holder(r33);
        L6:
            if (r4 != 2) goto L8;
            r33 = new ShadowSectionCell(this.context, 12);
            goto L10
        L8:
            r33 = new InviteUserCell(this.context, true);
            goto L10
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewRecycled(RecyclerView.ViewHolder r1) {
            View r0 = r1.itemView;
            if ((r0 instanceof InviteUserCell) == false) goto L6;
            ((InviteUserCell) r0).recycle();
            return;
        }

        public void searchDialogs(String r7) {
            Timer r0 = this.searchTimer;     // Catch: Exception -> L6
            if (r0 == null) goto L8;
            r0.cancel();     // Catch: Exception -> L6
        L8:
            if (r7 != null) goto L10;
            this.searchResult.clear();
            this.searchResultNames.clear();
            notifyDataSetChanged();
            return;
        L10:
            Timer r02 = new Timer();
            this.searchTimer = r02;
            r02.schedule(new AnonymousClass1(this, r7), 200, 300);
            return;
        L6:
            e = move-exception;
            FileLog.e(e);
            goto L8
        }

        public void setSearching(boolean r2) {
            if (this.searching != r2) goto L5;
            return;
        L5:
            this.searching = r2;
            notifyDataSetChanged();
        }
    }

    public class SearchField extends FrameLayout implements Theme.Colorable {
        private final EditTextBoldCursor editText;
        private GradientDrawable gradient;
        private final ImageView iconView;
        private final Paint paint;
        private final Path path;
        final /* synthetic */ InviteContactsActivity this$0;

        /* renamed from: org.telegram.ui.InviteContactsActivity$SearchField$1, reason: invalid class name */
        public class AnonymousClass1 extends EditTextBoldCursor {
            final /* synthetic */ SearchField this$1;
            final /* synthetic */ InviteContactsActivity val$this$0;

            public AnonymousClass1(SearchField r1, Context r2, InviteContactsActivity r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // android.widget.TextView, android.view.View, android.view.KeyEvent.Callback
            public boolean onKeyDown(int r2, KeyEvent r3) {
                if (r2 != 67) goto L11;
                if (SearchField.m15562$$Nest$fgeteditText(this.this$1).length() != 0) goto L11;
                if (InviteContactsActivity.m15527$$Nest$fgetallSpans(this.this$1.this$0).isEmpty() == true) goto L11;
                GroupCreateSpan r22 = (GroupCreateSpan) InviteContactsActivity.m15527$$Nest$fgetallSpans(this.this$1.this$0).get(InviteContactsActivity.m15527$$Nest$fgetallSpans(this.this$1.this$0).size() - 1);
                InviteContactsActivity.m15542$$Nest$fgetspansContainer(this.this$1.this$0).removeSpan(r22);
                InviteContactsActivity.m15556$$Nest$mupdateHint(this.this$1.this$0);
                InviteContactsActivity.m15554$$Nest$mcheckVisibleRows(this.this$1.this$0);
                return true;
            L11:
                return super.onKeyDown(r2, r3);
            }
        }

        /* renamed from: org.telegram.ui.InviteContactsActivity$SearchField$2, reason: invalid class name */
        public class AnonymousClass2 implements TextWatcher {
            final /* synthetic */ SearchField this$1;
            final /* synthetic */ InviteContactsActivity val$this$0;

            public AnonymousClass2(SearchField r1, InviteContactsActivity r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable r3) {
                int r32 = SearchField.m15562$$Nest$fgeteditText(InviteContactsActivity.m15540$$Nest$fgetsearchField(this.this$1.this$0)).length();
                SearchField r0 = this.this$1;
                if (r32 == 0) goto L6;
                InviteContactsActivity.m15548$$Nest$fputsearching(r0.this$0, true);
                InviteContactsActivity.m15547$$Nest$fputsearchWas(this.this$1.this$0, true);
                InviteContactsActivity.m15526$$Nest$fgetadapter(this.this$1.this$0).setSearching(true);
                InviteContactsActivity.m15526$$Nest$fgetadapter(this.this$1.this$0).searchDialogs(SearchField.m15562$$Nest$fgeteditText(InviteContactsActivity.m15540$$Nest$fgetsearchField(this.this$1.this$0)).toString());
                InviteContactsActivity.m15535$$Nest$fgetlistView(this.this$1.this$0).setFastScrollVisible(false);
                InviteContactsActivity.m15535$$Nest$fgetlistView(this.this$1.this$0).setVerticalScrollBarEnabled(true);
                InviteContactsActivity.m15529$$Nest$fgetemptyView(this.this$1.this$0).showProgress(true);
                InviteContactsActivity.m15529$$Nest$fgetemptyView(this.this$1.this$0).setStickerType(1);
                InviteContactsActivity.m15529$$Nest$fgetemptyView(this.this$1.this$0).title.setText(LocaleController.getString(R.string.NoResult));
                InviteContactsActivity.m15529$$Nest$fgetemptyView(this.this$1.this$0).subtitle.setText(LocaleController.getString(R.string.SearchEmptyViewFilteredSubtitle2));
                return;
            L6:
                InviteContactsActivity.m15555$$Nest$mcloseSearch(r0.this$0);
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            }
        }

        /* renamed from: $r8$lambda$q65gh0s7XVsGlSMTugN-obloBSA, reason: not valid java name */
        public static /* synthetic */ void m15561$r8$lambda$q65gh0s7XVsGlSMTugNobloBSA(SearchField r0, float r1) {
            r0.lambda$setSpansBounds$0(r1);
        }

        /* renamed from: -$$Nest$fgeteditText, reason: not valid java name */
        public static /* bridge */ /* synthetic */ EditTextBoldCursor m15562$$Nest$fgeteditText(SearchField r0) {
            return r0.editText;
        }

        public SearchField(InviteContactsActivity r19, Context r20, ScrollView r21) {
            this.this$0 = r19;
            super(r20);
            this.paint = new Paint(1);
            this.path = new Path();
            setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(3.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(3.0f));
            setClipChildren(false);
            setClipToPadding(false);
            ImageView r6 = new ImageView(r20);
            this.iconView = r6;
            r6.setImageResource(R.drawable.outline_search_1_24);
            int r8 = Theme.key_windowBackgroundWhiteBlackText;
            r6.setColorFilter(new PorterDuffColorFilter(Theme.multAlpha(r19.getThemedColor(r8), 0.6f), PorterDuff.Mode.SRC_IN));
            addView(r6, LayoutHelper.createFrame(24, 24.0f, 51, 11.0f, 8.0f, 11.0f, 8.0f));
            r21.setClipChildren(true);
            addView(r21, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, 0.0f, 0.0f, 40.0f));
            AnonymousClass1 r3 = new AnonymousClass1(this, r20, r19);
            this.editText = r3;
            r3.setHint(LocaleController.getString(R.string.Search));
            r3.setTextSize(1, 15.0f);
            r3.setCursorWidth(1.5f);
            r3.setInputType(r3.getInputType() | 176);
            r3.setSingleLine(true);
            r3.setBackground(null);
            r3.setVerticalScrollBarEnabled(false);
            r3.setHorizontalScrollBarEnabled(false);
            r3.setClipToPadding(true);
            r3.setPadding(AndroidUtilities.dp(46.0f), 0, AndroidUtilities.dp(46.0f), 0);
            r3.setEllipsizeByGradient(true);
            r3.setImeOptions(268435462);
            if (LocaleController.isRTL == false) goto L5;
            int r2 = 5;
        L6:
            r3.setGravity(r2 | 16);
            r3.addTextChangedListener(new AnonymousClass2(this, r19));
            if (Build.VERSION.SDK_INT < 35) goto L9;
            r3.setLocalePreferredLineHeightForMinimumUsed(false);
        L9:
            r3.setTextColor(r19.getThemedColor(r8));
            r3.setHintTextColor(r19.getThemedColor(Theme.key_windowBackgroundWhiteHintText));
            addView(r3, LayoutHelper.createFrame(-1, 40.0f, 55, 0.0f, 0.0f, 0.0f, 0.0f));
            updateColors();
            return;
        L5:
            r2 = 3;
            goto L6
        }

        private /* synthetic */ void lambda$setSpansBounds$0(float r2) {
            InviteContactsActivity.m15538$$Nest$fgetscrollView(this.this$0).smoothScrollTo(0, (int) r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r8) {
            this.paint.setShadowLayer(AndroidUtilities.dpf2(2.0f), 0.0f, AndroidUtilities.dpf2(0.33f), 285212672);
            this.paint.setColor(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite));
            RectF r0 = AndroidUtilities.rectTmp;
            r0.set(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(3.0f), getWidth() - AndroidUtilities.dp(12.0f), (AndroidUtilities.dp(3.0f) + InviteContactsActivity.m15528$$Nest$fgetanimatorSelectorContainerHeight(this.this$0).getFactor()) + AndroidUtilities.dp(3.0f));
            this.path.rewind();
            this.path.addRoundRect(r0, AndroidUtilities.dp(20.0f), AndroidUtilities.dp(20.0f), Path.Direction.CW);
            GradientDrawable r02 = this.gradient;
            if (r02 == null) goto L5;
            r02.setBounds(0, 0, getWidth(), Math.min(getHeight(), ((int) InviteContactsActivity.m15528$$Nest$fgetanimatorSelectorContainerHeight(this.this$0).getFactor()) + AndroidUtilities.dp(24.0f)));
            this.gradient.draw(r8);
        L5:
            r8.save();
            r8.drawPath(this.path, this.paint);
            r8.clipPath(this.path);
            super.dispatchDraw(r8);
            r8.restore();
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r6, View r7, long r8) {
            if (r7 != InviteContactsActivity.m15538$$Nest$fgetscrollView(this.this$0)) goto L7;
            r6.save();
            r6.clipRect(r7.getX(), r7.getY(), r7.getX() + r7.getWidth(), r7.getY() + r7.getHeight());
            boolean r5 = super.drawChild(r6, r7, r8);
            r6.restore();
            return r5;
        L7:
            return super.drawChild(r6, r7, r8);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(144.0f), TLObject.FLAG_30));
        }

        public void setSpansBounds(int r8, float r9, float r10, boolean r11) {
            if (r8 > 0) goto L4;
            boolean r0 = true;
        L5:
            ViewPropertyAnimator r1 = this.iconView.animate();
            float r2 = 0.0f;
            float r3 = 1.0f;
            if (r0 == false) goto L8;
            float r4 = 1.0f;
        L9:
            ViewPropertyAnimator r12 = r1.alpha(r4);
            if (r0 == false) goto L12;
            float r5 = 1.0f;
        L13:
            ViewPropertyAnimator r13 = r12.scaleX(r5);
            if (r0 == true) goto L17;
            r3 = 0.5f;
        L17:
            ViewPropertyAnimator r02 = r13.scaleY(r3);
            CubicBezierInterpolator r14 = CubicBezierInterpolator.EASE_OUT_QUINT;
            r02.setInterpolator(r14).setDuration(320).start();
            ViewPropertyAnimator r03 = this.editText.animate();
            if (r11 == false) goto L20;
            float r52 = ((getHeight() - getPaddingTop()) - getPaddingBottom()) - AndroidUtilities.dp(44.0f);
        L21:
            ViewPropertyAnimator r04 = r03.translationY(r52);
            if (r11 == false) goto L24;
            r2 = AndroidUtilities.dp(-36.0f);
        L27:
            r04.translationX(r2).setInterpolator(r14).setDuration(320).start();
            InviteContactsActivity.m15538$$Nest$fgetscrollView(this.this$0).post(new InviteContactsActivity$SearchField$$ExternalSyntheticLambda0(this, r9));
            return;
        L24:
            if (r8 <= 0) goto L27;
            r2 = Math.max(-AndroidUtilities.dp(36.0f), r10 - AndroidUtilities.dp(46.0f));
            goto L27
        L20:
            r52 = r9;
            goto L21
        L12:
            r5 = 0.5f;
            goto L13
        L8:
            r4 = 0.0f;
            goto L9
        L4:
            r0 = false;
            goto L5
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public void updateColors() {
            int r0 = this.this$0.getThemedColor(Theme.key_windowBackgroundGray);
            this.gradient = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{Theme.multAlpha(r0, 1.0f), Theme.multAlpha(r0, 0.0f)});
        }
    }

    public class SpansContainer extends ViewGroup {
        private View addingSpan;
        private boolean animationStarted;
        private final ArrayList<Animator> animators;
        private int containerHeight;
        private AnimatorSet currentAnimation;
        private View removingSpan;
        final /* synthetic */ InviteContactsActivity this$0;

        /* renamed from: org.telegram.ui.InviteContactsActivity$SpansContainer$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ SpansContainer this$1;

            public AnonymousClass1(SpansContainer r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                SpansContainer.m15563$$Nest$fputaddingSpan(this.this$1, null);
                SpansContainer.m15565$$Nest$fputcurrentAnimation(this.this$1, null);
                SpansContainer.m15564$$Nest$fputanimationStarted(this.this$1, false);
            }
        }

        /* renamed from: org.telegram.ui.InviteContactsActivity$SpansContainer$2, reason: invalid class name */
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
                SpansContainer.m15566$$Nest$fputremovingSpan(this.this$1, null);
                SpansContainer.m15565$$Nest$fputcurrentAnimation(this.this$1, null);
                SpansContainer.m15564$$Nest$fputanimationStarted(this.this$1, false);
            }
        }

        /* renamed from: -$$Nest$fputaddingSpan, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15563$$Nest$fputaddingSpan(SpansContainer r0, View r1) {
            r0.addingSpan = r1;
        }

        /* renamed from: -$$Nest$fputanimationStarted, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15564$$Nest$fputanimationStarted(SpansContainer r0, boolean r1) {
            r0.animationStarted = r1;
        }

        /* renamed from: -$$Nest$fputcurrentAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15565$$Nest$fputcurrentAnimation(SpansContainer r0, AnimatorSet r1) {
            r0.currentAnimation = r1;
        }

        /* renamed from: -$$Nest$fputremovingSpan, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m15566$$Nest$fputremovingSpan(SpansContainer r0, View r1) {
            r0.removingSpan = r1;
        }

        public SpansContainer(InviteContactsActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.animators = new ArrayList();
        }

        public void addSpan(GroupCreateSpan r6) {
            InviteContactsActivity.m15527$$Nest$fgetallSpans(this.this$0).add(r6);
            InviteContactsActivity.m15541$$Nest$fgetselectedContacts(this.this$0).put(r6.getKey(), r6);
            AnimatorSet r0 = this.currentAnimation;
            if (r0 == null) goto L5;
            r0.setupEndValues();
            this.currentAnimation.cancel();
        L5:
            this.animationStarted = false;
            AnimatorSet r02 = new AnimatorSet();
            this.currentAnimation = r02;
            r02.addListener(new AnonymousClass1(this));
            this.currentAnimation.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.currentAnimation.setDuration(320);
            this.addingSpan = r6;
            this.animators.clear();
            ArrayList<Animator> r03 = this.animators;
            Property r4 = View.SCALE_X;
            r03.add(ObjectAnimator.ofFloat(this.addingSpan, r4, new float[]{0.75f, 1.0f}));
            ArrayList<Animator> r04 = this.animators;
            Property r42 = View.SCALE_Y;
            r04.add(ObjectAnimator.ofFloat(this.addingSpan, r42, new float[]{0.75f, 1.0f}));
            ArrayList<Animator> r05 = this.animators;
            Property r3 = View.ALPHA;
            r05.add(ObjectAnimator.ofFloat(this.addingSpan, r3, new float[]{0.0f, 1.0f}));
            addView(r6);
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
            float r4 = 6.0f;
            int r5 = AndroidUtilities.dp(6.0f);
            int r6 = AndroidUtilities.dp(6.0f);
            int r8 = 0;
            int r9 = 0;
            int r10 = 0;
            int r11 = 0;
        L3:
            int r13 = 1;
            if (r8 >= r1) goto L35;
            View r15 = getChildAt(r8);
            float r172 = r4;
            if ((r15 instanceof GroupCreateSpan) == false) goto L34;
            r15.measure(View.MeasureSpec.makeMeasureSpec(r2, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(28.0f), TLObject.FLAG_30));
            if (r15 == this.removingSpan) goto L14;
            if ((r15.getMeasuredWidth() + r10) <= r3) goto L14;
            r5 = r5 + AndroidUtilities.dp(34.0f);
            r10 = 0;
        L14:
            if ((r15.getMeasuredWidth() + r11) <= r3) goto L16;
            r6 = r6 + AndroidUtilities.dp(34.0f);
            r11 = 0;
        L16:
            int r12 = AndroidUtilities.dp(5.0f) + r10;
            if (this.animationStarted == true) goto L31;
            View r14 = this.removingSpan;
            if (r15 != r14) goto L21;
            r15.setTranslationX(AndroidUtilities.dp(5.0f) + r11);
            r15.setTranslationY(r6);
            goto L31
        L21:
            if (r14 == null) goto L29;
            float r122 = r12;
            if (r15.getTranslationX() == r122) goto L25;
            this.animators.add(ObjectAnimator.ofFloat(r15, View.TRANSLATION_X, new float[]{r122}));
        L25:
            float r123 = r5;
            if (r15.getTranslationY() == r123) goto L28;
            this.animators.add(ObjectAnimator.ofFloat(r15, View.TRANSLATION_Y, new float[]{r123}));
        L28:
            r9 = Math.max(r9, r5);
            goto L31
        L29:
            r15.setTranslationX(r12);
            r15.setTranslationY(r5);
            r9 = Math.max(r9, r5);
        L31:
            if (r15 == this.removingSpan) goto L33;
            r10 = r10 + (r15.getMeasuredWidth() + AndroidUtilities.dp(9.0f));
        L33:
            r11 = r11 + (r15.getMeasuredWidth() + AndroidUtilities.dp(9.0f));
        L34:
            r8 = r8 + 1;
            r4 = r172;
            goto L3
        L35:
            float r173 = r4;
            if (AndroidUtilities.isTablet() == false) goto L38;
            int r42 = AndroidUtilities.dp(372.0f) / 3;
        L39:
            if (r9 <= 0) goto L41;
            int r82 = AndroidUtilities.dp(34.0f) + r9;
        L43:
            if (r82 <= (InviteContactsActivity.m15536$$Nest$fgetmaxSize(this.this$0) - AndroidUtilities.dp(12.0f))) goto L45;
            boolean r83 = true;
        L47:
            if ((r3 - r10) >= r42) goto L50;
            if (r83 == true) goto L50;
            r5 = r5 + AndroidUtilities.dp(34.0f);
            r9 = Math.max(r9, r5);
            r10 = 0;
        L50:
            if (r9 <= 0) goto L52;
            int r32 = AndroidUtilities.dp(34.0f) + r9;
        L54:
            if (r32 <= (InviteContactsActivity.m15536$$Nest$fgetmaxSize(this.this$0) - AndroidUtilities.dp(12.0f))) goto L56;
            boolean r33 = true;
        L58:
            if (this.animationStarted == true) goto L63;
            int r62 = r6 + AndroidUtilities.dp(28.0f);
            InviteContactsActivity.m15543$$Nest$fputfieldY(this.this$0, r5);
            if (this.currentAnimation == null) goto L62;
            this.containerHeight = r5 + AndroidUtilities.dp(28.0f);
            this.currentAnimation.playTogether(this.animators);
            this.currentAnimation.start();
            this.animationStarted = true;
            goto L63
        L62:
            this.containerHeight = r62;
        L63:
            if (r33 == false) goto L66;
            int r43 = InviteContactsActivity.m15536$$Nest$fgetmaxSize(this.this$0) - AndroidUtilities.dp(12.0f);
        L71:
            InviteContactsActivity.m15528$$Nest$fgetanimatorSelectorContainerHeight(this.this$0).animateTo(r43);
            if (InviteContactsActivity.m15540$$Nest$fgetsearchField(this.this$0) == null) goto L78;
            SearchField r44 = InviteContactsActivity.m15540$$Nest$fgetsearchField(this.this$0);
            if (this.removingSpan != null) goto L77;
            r13 = 0;
        L77:
            r44.setSpansBounds(Math.max(0, r1 - r13), Math.max(0, r9 - AndroidUtilities.dp(r173)), r10, r33);
        L78:
            setMeasuredDimension(r2, this.containerHeight);
            return;
        L66:
            int r45 = AndroidUtilities.dp(37.0f);
            if (r9 <= 0) goto L69;
            int r52 = AndroidUtilities.dp(31.0f) + r9;
        L70:
            r43 = Math.max(r45, Math.min(r52, InviteContactsActivity.m15536$$Nest$fgetmaxSize(this.this$0) - AndroidUtilities.dp(12.0f)));
            goto L71
        L69:
            r52 = 0;
            goto L70
        L56:
            r33 = false;
            goto L58
        L52:
            r32 = 0;
            goto L54
        L45:
            r83 = false;
            goto L47
        L41:
            r82 = 0;
            goto L43
        L38:
            Point r46 = AndroidUtilities.displaySize;
            r42 = (Math.min(r46.x, r46.y) - AndroidUtilities.dp(158.0f)) / 3;
            goto L39
        }

        public void removeSpan(GroupCreateSpan r5) {
            InviteContactsActivity.m15545$$Nest$fputignoreScrollEvent(this.this$0, true);
            InviteContactsActivity.m15541$$Nest$fgetselectedContacts(this.this$0).remove(r5.getKey());
            InviteContactsActivity.m15527$$Nest$fgetallSpans(this.this$0).remove(r5);
            r5.setOnClickListener(null);
            AnimatorSet r0 = this.currentAnimation;
            if (r0 != null) goto L5;
        L7:
            this.animationStarted = false;
            AnimatorSet r02 = new AnimatorSet();
            this.currentAnimation = r02;
            r02.addListener(new AnonymousClass2(this, r5));
            this.currentAnimation.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.currentAnimation.setDuration(320);
            this.removingSpan = r5;
            this.animators.clear();
            ArrayList<Animator> r52 = this.animators;
            Property r3 = View.SCALE_X;
            r52.add(ObjectAnimator.ofFloat(this.removingSpan, r3, new float[]{1.0f, 0.75f}));
            ArrayList<Animator> r53 = this.animators;
            Property r32 = View.SCALE_Y;
            r53.add(ObjectAnimator.ofFloat(this.removingSpan, r32, new float[]{1.0f, 0.75f}));
            ArrayList<Animator> r54 = this.animators;
            Property r2 = View.ALPHA;
            r54.add(ObjectAnimator.ofFloat(this.removingSpan, r2, new float[]{1.0f, 0.0f}));
            requestLayout();
            return;
        L5:
            if (r0.isRunning() == false) goto L7;
            this.currentAnimation.setupEndValues();
            this.currentAnimation.cancel();
            goto L7
        }
    }

    public static /* synthetic */ int $r8$lambda$Hq3CwfD8ExYcuUXnPHqgZKkzKSo(ContactsController.Contact r0, ContactsController.Contact r1) {
        int r02 = r0.imported;
        int r12 = r1.imported;
        if (r02 <= r12) goto L6;
        return -1;
    L6:
        if (r02 >= r12) goto L9;
        return 1;
    L9:
        return 0;
    }

    public static /* synthetic */ void $r8$lambda$M9xGJcb2HGMrYVHhyr3jiOiFrhc(InviteContactsActivity r0, View r1) {
        r0.lambda$createView$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$WoacsEx85PnwLurpjiQPC3jU5fk(InviteContactsActivity r0) {
        r0.lambda$createView$2();
    }

    public static /* synthetic */ void $r8$lambda$WscBSuUABR8z51jSRjy1GtkCby8(InviteContactsActivity r0) {
        r0.lambda$createView$3();
    }

    /* renamed from: $r8$lambda$_9UwJxmCM-UM19FpPmsd138eJ7o, reason: not valid java name */
    public static /* synthetic */ void m15525$r8$lambda$_9UwJxmCMUM19FpPmsd138eJ7o(InviteContactsActivity r0, View r1, int r2) {
        r0.lambda$createView$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$lkXqfBiULEqhNANXJNp90e5shTQ(InviteContactsActivity r0) {
        r0.lambda$getThemeDescriptions$5();
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ InviteAdapter m15526$$Nest$fgetadapter(InviteContactsActivity r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetallSpans, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m15527$$Nest$fgetallSpans(InviteContactsActivity r0) {
        return r0.allSpans;
    }

    /* renamed from: -$$Nest$fgetanimatorSelectorContainerHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FactorAnimator m15528$$Nest$fgetanimatorSelectorContainerHeight(InviteContactsActivity r0) {
        return r0.animatorSelectorContainerHeight;
    }

    /* renamed from: -$$Nest$fgetemptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StickerEmptyView m15529$$Nest$fgetemptyView(InviteContactsActivity r0) {
        return r0.emptyView;
    }

    /* renamed from: -$$Nest$fgetfieldY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15530$$Nest$fgetfieldY(InviteContactsActivity r0) {
        return r0.fieldY;
    }

    /* renamed from: -$$Nest$fgetiBlur3Invalidated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15531$$Nest$fgetiBlur3Invalidated(InviteContactsActivity r0) {
        return r0.iBlur3Invalidated;
    }

    /* renamed from: -$$Nest$fgetiBlur3SourceGlassFrosted, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundSourceRenderNode m15532$$Nest$fgetiBlur3SourceGlassFrosted(InviteContactsActivity r0) {
        return r0.iBlur3SourceGlassFrosted;
    }

    /* renamed from: -$$Nest$fgetignoreScrollEvent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m15533$$Nest$fgetignoreScrollEvent(InviteContactsActivity r0) {
        return r0.ignoreScrollEvent;
    }

    /* renamed from: -$$Nest$fgetlayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayoutManager m15534$$Nest$fgetlayoutManager(InviteContactsActivity r0) {
        return r0.layoutManager;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m15535$$Nest$fgetlistView(InviteContactsActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetmaxSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m15536$$Nest$fgetmaxSize(InviteContactsActivity r0) {
        return r0.maxSize;
    }

    /* renamed from: -$$Nest$fgetphoneBookContacts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m15537$$Nest$fgetphoneBookContacts(InviteContactsActivity r0) {
        return r0.phoneBookContacts;
    }

    /* renamed from: -$$Nest$fgetscrollView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ScrollView m15538$$Nest$fgetscrollView(InviteContactsActivity r0) {
        return r0.scrollView;
    }

    /* renamed from: -$$Nest$fgetscrollableViewNoiseSuppressor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DownscaleScrollableNoiseSuppressor m15539$$Nest$fgetscrollableViewNoiseSuppressor(InviteContactsActivity r0) {
        return r0.scrollableViewNoiseSuppressor;
    }

    /* renamed from: -$$Nest$fgetsearchField, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SearchField m15540$$Nest$fgetsearchField(InviteContactsActivity r0) {
        return r0.searchField;
    }

    /* renamed from: -$$Nest$fgetselectedContacts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m15541$$Nest$fgetselectedContacts(InviteContactsActivity r0) {
        return r0.selectedContacts;
    }

    /* renamed from: -$$Nest$fgetspansContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SpansContainer m15542$$Nest$fgetspansContainer(InviteContactsActivity r0) {
        return r0.spansContainer;
    }

    /* renamed from: -$$Nest$fputfieldY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15543$$Nest$fputfieldY(InviteContactsActivity r0, int r1) {
        r0.fieldY = r1;
    }

    /* renamed from: -$$Nest$fputiBlur3Invalidated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15544$$Nest$fputiBlur3Invalidated(InviteContactsActivity r0, boolean r1) {
        r0.iBlur3Invalidated = r1;
    }

    /* renamed from: -$$Nest$fputignoreScrollEvent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15545$$Nest$fputignoreScrollEvent(InviteContactsActivity r0, boolean r1) {
        r0.ignoreScrollEvent = r1;
    }

    /* renamed from: -$$Nest$fputmaxSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15546$$Nest$fputmaxSize(InviteContactsActivity r0, int r1) {
        r0.maxSize = r1;
    }

    /* renamed from: -$$Nest$fputsearchWas, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15547$$Nest$fputsearchWas(InviteContactsActivity r0, boolean r1) {
        r0.searchWas = r1;
    }

    /* renamed from: -$$Nest$fputsearching, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15548$$Nest$fputsearching(InviteContactsActivity r0, boolean r1) {
        r0.searching = r1;
    }

    /* renamed from: -$$Nest$mblur3_InvalidateBlur, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15549$$Nest$mblur3_InvalidateBlur(InviteContactsActivity r0) {
        r0.blur3_InvalidateBlur();
    }

    /* renamed from: -$$Nest$mcheckUi_emptyViewVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15550$$Nest$mcheckUi_emptyViewVisible(InviteContactsActivity r0) {
        r0.checkUi_emptyViewVisible();
    }

    /* renamed from: -$$Nest$mcheckUi_floatingButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15551$$Nest$mcheckUi_floatingButton(InviteContactsActivity r0) {
        r0.checkUi_floatingButton();
    }

    /* renamed from: -$$Nest$mcheckUi_listViewPadding, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15552$$Nest$mcheckUi_listViewPadding(InviteContactsActivity r0) {
        r0.checkUi_listViewPadding();
    }

    /* renamed from: -$$Nest$mcheckUi_searchFieldY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15553$$Nest$mcheckUi_searchFieldY(InviteContactsActivity r0) {
        r0.checkUi_searchFieldY();
    }

    /* renamed from: -$$Nest$mcheckVisibleRows, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15554$$Nest$mcheckVisibleRows(InviteContactsActivity r0) {
        r0.checkVisibleRows();
    }

    /* renamed from: -$$Nest$mcloseSearch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15555$$Nest$mcloseSearch(InviteContactsActivity r0) {
        r0.closeSearch();
    }

    /* renamed from: -$$Nest$mupdateHint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m15556$$Nest$mupdateHint(InviteContactsActivity r0) {
        r0.updateHint();
    }

    public InviteContactsActivity() {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 31) goto L5;
        int r2 = 48;
    L6:
        this.ADDITIONAL_LIST_HEIGHT_DP = r2;
        this.animatorSelectorContainerHeight = new FactorAnimator(3, this, CubicBezierInterpolator.EASE_OUT_QUINT, 350, AndroidUtilities.dp(37.0f));
        this.selectedContacts = new HashMap();
        this.allSpans = new ArrayList();
        ArrayList<RectF> r10 = new ArrayList();
        this.iBlur3Positions = r10;
        RectF r22 = new RectF();
        this.iBlur3PositionActionBar = r22;
        r10.add(r22);
        if (r0 < 31) goto L10;
        this.scrollableViewNoiseSuppressor = new DownscaleScrollableNoiseSuppressor();
        this.iBlur3SourceGlassFrosted = new BlurredBackgroundSourceRenderNode(null);
        return;
    L10:
        this.scrollableViewNoiseSuppressor = null;
        this.iBlur3SourceGlassFrosted = null;
        return;
    L5:
        r2 = 0;
        goto L6
    }

    public static /* synthetic */ ActionBar access$000(InviteContactsActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$100(InviteContactsActivity r0) {
        return r0.actionBar;
    }

    private void blur3_InvalidateBlur() {
        if (Build.VERSION.SDK_INT >= 31) goto L5;
        return;
    L5:
        if (this.scrollableViewNoiseSuppressor == null) goto L10;
        int r1 = AndroidUtilities.dp(48.0f);
        int r0 = AndroidUtilities.dp(48.0f) + this.maxSize;
        this.iBlur3PositionActionBar.set(0.0f, -r1, this.fragmentView.getMeasuredWidth(), (this.actionBar.getMeasuredHeight() + r1) + r0);
        this.scrollableViewNoiseSuppressor.setupRenderNodes(this.iBlur3Positions, 1);
        this.scrollableViewNoiseSuppressor.invalidateResultRenderNodes(this.iBlur3Capture, this.fragmentView.getMeasuredWidth(), this.fragmentView.getMeasuredHeight());
        return;
    }

    private void checkUi_emptyViewVisible() {
        InviteAdapter r0 = this.adapter;
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.searching == true) goto L13;
        int r02 = r0.getItemCount();
        StickerEmptyView r2 = this.emptyView;
        if (r02 != 2) goto L10;
        int r03 = 0;
    L11:
        r2.setVisibility(r03);
        return;
    L10:
        r03 = 4;
        goto L11
    }

    private void checkUi_floatingButton() {
        FragmentFloatingButton r0 = this.floatingButton;
        if (r0 == null) goto L6;
        r0.setTranslationY(-Math.max(this.navigationBarHeight, this.imeInsetAnimatedHeight));
        return;
    }

    private void checkUi_listViewPadding() {
        this.listView.setPadding(0, ((AndroidUtilities.dp(this.ADDITIONAL_LIST_HEIGHT_DP) + this.actionBar.getMeasuredHeight()) + AndroidUtilities.dp(4.0f)) + ((int) this.animatorSelectorContainerHeight.getFactor()), 0, this.navigationBarHeight);
        this.emptyView.setPadding(0, 0, 0, this.navigationBarHeight);
    }

    private void checkUi_searchFieldY() {
        this.searchField.setTranslationY(this.actionBar.getMeasuredHeight());
    }

    private void checkVisibleRows() {
        int r0 = this.listView.getChildCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L10;
        View r2 = this.listView.getChildAt(r1);
        if ((r2 instanceof InviteUserCell) == false) goto L9;
        InviteUserCell r22 = (InviteUserCell) r2;
        ContactsController.Contact r3 = r22.getContact();
        if (r3 == null) goto L9;
        r22.setChecked(this.selectedContacts.containsKey(r3.key), true);
    L9:
        r1 = r1 + 1;
        goto L3
    }

    private void closeSearch() {
        this.searching = false;
        this.searchWas = false;
        this.adapter.setSearching(false);
        this.adapter.searchDialogs(null);
        this.listView.setFastScrollVisible(true);
        this.listView.setVerticalScrollBarEnabled(false);
        this.emptyView.showProgress(false);
        this.emptyView.setStickerType(0);
        this.emptyView.title.setText(LocaleController.getString(R.string.NoContacts));
        this.emptyView.subtitle.setText(_UrlKt.FRAGMENT_ENCODE_SET);
    }

    private void fetchContacts() {
        ArrayList<ContactsController.Contact> r0 = new ArrayList(ContactsController.getInstance(this.currentAccount).phoneBookContacts);
        this.phoneBookContacts = r0;
        Collections.sort(r0, new InviteContactsActivity$$ExternalSyntheticLambda0());
        StickerEmptyView r02 = this.emptyView;
        if (r02 == null) goto L5;
        r02.showProgress(false);
    L5:
        InviteAdapter r2 = this.adapter;
        if (r2 == null) goto L9;
        r2.notifyDataSetChanged();
        return;
    }

    private /* synthetic */ void lambda$createView$0(View r8, int r9) {
        boolean r0 = false;
        if (r9 != 0) goto L12;
        if (this.searching == true) goto L12;
        Intent r82 = new Intent("android.intent.action.SEND");     // Catch: Exception -> L8
        r82.setType("text/plain");     // Catch: Exception -> L8
        String r92 = ContactsController.getInstance(this.currentAccount).getInviteText(0);     // Catch: Exception -> L8
        r82.putExtra("android.intent.extra.TEXT", r92);     // Catch: Exception -> L8
        getParentActivity().startActivityForResult(Intent.createChooser(r82, r92), MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);     // Catch: Exception -> L8
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
        return;
    L12:
        if ((r8 instanceof InviteUserCell) == false) goto L34;
        InviteUserCell r83 = (InviteUserCell) r8;
        ContactsController.Contact r4 = r83.getContact();
        if (r4 == null) goto L35;
        GroupCreateSpan r93 = this.selectedContacts.get(r4.key);
        if (r93 == null) goto L20;
        this.spansContainer.removeSpan(r93);
    L21:
        updateHint();
        if (this.searching == false) goto L24;
        return;
    L24:
        if (this.searchWas == false) goto L27;
        return;
    L27:
        if (r93 != null) goto L29;
        r0 = true;
    L29:
        r83.setChecked(r0, true);
        return;
    L20:
        GroupCreateSpan r1 = new GroupCreateSpan(getContext(), null, r4, true, this.resourceProvider);
        this.spansContainer.addSpan(r1);
        r1.setOnClickListener(this);
        goto L21
    L35:
        return;
    }

    private /* synthetic */ void lambda$createView$1(View r7) {
        StringBuilder r72 = new StringBuilder();     // Catch: Exception -> L8
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= this.allSpans.size()) goto L16;
        ContactsController.Contact r3 = this.allSpans.get(r1).getContact();     // Catch: Exception -> L8
        if (r72.length() == 0) goto L10;
        r72.append(';');     // Catch: Exception -> L8
    L10:
        r72.append(r3.phones.get(0));     // Catch: Exception -> L8
        if (r1 != 0) goto L15;
        if (this.allSpans.size() != 1) goto L15;
        r2 = r3.imported;     // Catch: Exception -> L8
    L15:
        r1 = r1 + 1;     // Catch: Exception -> L8
        goto L4
    L16:
        Intent r0 = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + r72.toString()));     // Catch: Exception -> L8
        r0.putExtra("sms_body", ContactsController.getInstance(this.currentAccount).getInviteText(r2));     // Catch: Exception -> L8
        getParentActivity().startActivityForResult(r0, MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);     // Catch: Exception -> L8
    L19:
        finishFragment();
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
        goto L19
    }

    private /* synthetic */ void lambda$createView$2() {
        blur3_InvalidateBlur();
    }

    private /* synthetic */ void lambda$createView$3() {
        this.listView.postOnAnimation(new InviteContactsActivity$$ExternalSyntheticLambda5(this));
    }

    private /* synthetic */ void lambda$getThemeDescriptions$5() {
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L10;
        int r02 = r0.getChildCount();
        int r2 = 0;
    L5:
        if (r2 >= r02) goto L14;
        View r3 = this.listView.getChildAt(r2);
        if ((r3 instanceof InviteUserCell) == false) goto L9;
        ((InviteUserCell) r3).update(0);
    L9:
        r2 = r2 + 1;
        goto L5
    L14:
        return;
    }

    private void updateHint() {
        this.floatingButton.setButtonVisible(!this.allSpans.isEmpty(), true);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ActionBar createActionBar(Context r1) {
        ActionBar r0 = super.createActionBar(r1);
        r0.setAddToContainer(false);
        return r0;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r13) {
        this.searching = false;
        this.searchWas = false;
        this.allSpans.clear();
        this.selectedContacts.clear();
        this.currentDeletingSpan = null;
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        int r2 = 1;
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.InviteFriends));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        AnonymousClass2 r1 = new AnonymousClass2(this, r13);
        this.fragmentView = r1;
        AnonymousClass3 r3 = new AnonymousClass3(this, r13);
        this.scrollView = r3;
        r3.setVerticalScrollBarEnabled(false);
        SpansContainer r32 = new SpansContainer(this, r13);
        this.spansContainer = r32;
        this.scrollView.addView(r32, LayoutHelper.createFrame(-1, 108.0f));
        this.searchField = new SearchField(this, r13, this.scrollView);
        FlickerLoadingView r33 = new FlickerLoadingView(r13);
        r33.setViewType(6);
        r33.showDate(false);
        StickerEmptyView r4 = new StickerEmptyView(r13, r33, 0);
        this.emptyView = r4;
        r4.addView(r33, 0);
        this.emptyView.setAnimateLayoutChange(true);
        this.emptyView.title.setText(LocaleController.getString(R.string.NoContacts));
        this.emptyView.subtitle.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        this.emptyView.showProgress(ContactsController.getInstance(this.currentAccount).isLoadingContacts());
        int r34 = Theme.key_windowBackgroundGray;
        r1.setBackgroundColor(getThemedColor(r34));
        r1.addView(this.emptyView);
        this.layoutManager = new LinearLayoutManager(r13, 1, false);
        this.adapter = new InviteAdapter(this, r13);
        RecyclerListView r42 = new RecyclerListView(r13);
        this.listView = r42;
        r42.setSections(true);
        this.listView.setEmptyView(this.emptyView);
        this.listView.setAdapter(this.adapter);
        this.listView.setLayoutManager(this.layoutManager);
        this.listView.setVerticalScrollBarEnabled(true);
        RecyclerListView r43 = this.listView;
        if (LocaleController.isRTL == true) goto L6;
        r2 = 2;
    L6:
        r43.setVerticalScrollbarPosition(r2);
        this.listView.setClipToPadding(false);
        r1.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, -this.ADDITIONAL_LIST_HEIGHT_DP, 0.0f, 0.0f));
        this.listView.setOnItemClickListener(new InviteContactsActivity$$ExternalSyntheticLambda2(this));
        this.listView.setOnScrollListener(new AnonymousClass4(this));
        BackDrawable r22 = new BackDrawable(false);
        r22.setArrowRotation(180);
        FragmentFloatingButton r44 = new FragmentFloatingButton(r13, this.resourceProvider);
        this.floatingButton = r44;
        r44.imageView.setImageDrawable(r22);
        this.floatingButton.setButtonVisible(false, false);
        this.floatingButton.setContentDescription(LocaleController.getString(R.string.Next));
        this.floatingButton.setOnClickListener(new InviteContactsActivity$$ExternalSyntheticLambda3(this));
        this.actionBar.setBackgroundColor(getThemedColor(r34));
        RecyclerListView r0 = this.listView;
        Objects.requireNonNull(r0);
        this.iBlur3Capture = new ViewGroupPartRenderer(r0, r1, new ShareAlert$$ExternalSyntheticLambda16(r0));
        this.listView.addEdgeEffectListener(new InviteContactsActivity$$ExternalSyntheticLambda4(this));
        checkUi_emptyViewVisible();
        r1.addView(this.floatingButton, FragmentFloatingButton.createDefaultLayoutParams());
        r1.addView(this.actionBar);
        r1.addView(this.searchField, LayoutHelper.createFrame(-1, -2.0f, 48, 0.0f, 0.0f, 0.0f, 0.0f));
        LaunchActivity r132 = LaunchActivity.instance;
        if (r132 == null) goto L10;
        r132.getRootAnimatedInsetsListener().subscribeToWindowInsetsAnimation(this);
    L10:
        return this.fragmentView;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.contactsImported) goto L7;
        fetchContacts();
        return;
    L7:
        if (r1 != NotificationCenter.contactsDidLoad) goto L12;
        StickerEmptyView r0 = this.emptyView;
        if (r0 == null) goto L13;
        r0.showProgress(false);
        return;
    L13:
        return;
    }

    @Override // org.telegram.ui.Components.inset.WindowAnimatedInsetsProvider.Listener
    public View getAnimatedInsetsTargetView() {
        return this.fragmentView;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        InviteContactsActivity$$ExternalSyntheticLambda1 r8 = new InviteContactsActivity$$ExternalSyntheticLambda1(this);
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_FASTSCROLL, null, null, null, null, Theme.key_fastScrollActive));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_FASTSCROLL, null, null, null, null, Theme.key_fastScrollInactive));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_FASTSCROLL, null, null, null, null, Theme.key_fastScrollText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{GroupCreateSectionCell.class}, null, null, null, Theme.key_graySection));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{GroupCreateSectionCell.class}, new String[]{"drawable"}, null, null, null, Theme.key_groupcreate_sectionShadow));
        int r21 = Theme.key_groupcreate_sectionText;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{GroupCreateSectionCell.class}, new String[]{"textView"}, null, null, null, r21));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{InviteUserCell.class}, new String[]{"textView"}, null, null, null, r21));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{InviteUserCell.class}, new String[]{"nameTextView"}, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{InviteUserCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_checkbox));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{InviteUserCell.class}, new String[]{"checkBox"}, null, null, null, Theme.key_checkboxCheck));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{InviteUserCell.class}, new String[]{"statusTextView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueText));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{InviteUserCell.class}, new String[]{"statusTextView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{InviteUserCell.class}, null, Theme.avatarDrawables, null, Theme.key_avatar_text));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundRed));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundOrange));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundViolet));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundGreen));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundCyan));
        int r16 = Theme.key_avatar_backgroundBlue;
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, r16));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundPink));
        r1.add(new ThemeDescription(this.spansContainer, 0, new Class[]{GroupCreateSpan.class}, null, null, null, Theme.key_groupcreate_spanBackground));
        r1.add(new ThemeDescription(this.spansContainer, 0, new Class[]{GroupCreateSpan.class}, null, null, null, Theme.key_groupcreate_spanText));
        r1.add(new ThemeDescription(this.spansContainer, 0, new Class[]{GroupCreateSpan.class}, null, null, null, Theme.key_groupcreate_spanDelete));
        r1.add(new ThemeDescription(this.spansContainer, 0, new Class[]{GroupCreateSpan.class}, null, null, null, r16));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.Components.inset.WindowAnimatedInsetsProvider.Listener
    public void onAnimatedInsetsChanged(View r1, WindowInsetsCompat r2) {
        this.imeInsetAnimatedHeight = r2.getInsets(WindowInsetsCompat.Type.ime()).bottom;
        checkUi_floatingButton();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View r2) {
        GroupCreateSpan r22 = (GroupCreateSpan) r2;
        if (r22.isDeleting() == false) goto L6;
        this.currentDeletingSpan = null;
        this.spansContainer.removeSpan(r22);
        updateHint();
        checkVisibleRows();
        return;
    L6:
        GroupCreateSpan r0 = this.currentDeletingSpan;
        if (r0 == null) goto L9;
        r0.cancelDeleteAnimation();
    L9:
        this.currentDeletingSpan = r22;
        r22.startDeleteAnimation();
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        if (r1 != 3) goto L8;
        int r12 = this.listView.getPaddingTop();
        this.searchField.invalidate();
        checkUi_listViewPadding();
        checkUi_searchFieldY();
        int r22 = this.listView.getPaddingTop();
        if (r22 == r12) goto L9;
        this.listView.scrollBy(0, r12 - r22);
        return;
    L9:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.contactsImported);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.contactsDidLoad);
        fetchContacts();
        if (UserConfig.getInstance(this.currentAccount).contactsReimported == true) goto L6;
        ContactsController.getInstance(this.currentAccount).forceImportContacts();
        UserConfig.getInstance(this.currentAccount).contactsReimported = true;
        UserConfig.getInstance(this.currentAccount).saveConfig(false);
    L6:
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.contactsImported);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.contactsDidLoad);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.navigationBarHeight = r4;
        checkUi_listViewPadding();
        checkUi_floatingButton();
    }
}
