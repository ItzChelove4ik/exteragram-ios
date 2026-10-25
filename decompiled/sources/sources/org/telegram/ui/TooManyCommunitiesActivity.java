package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.EmptyCell;
import org.telegram.ui.Cells.GroupCreateUserCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TooManyCommunitiesHintCell;
import org.telegram.ui.Components.CombinedDrawable;
import org.telegram.ui.Components.EmptyTextProgressView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes6.dex */
public class TooManyCommunitiesActivity extends BaseFragment {
    private Adapter adapter;
    private int buttonAnimation;
    private int buttonHeight;
    private FrameLayout buttonLayout;
    private TextView buttonTextView;
    private EmptyTextProgressView emptyView;
    private ValueAnimator enterAnimator;
    private float enterProgress;
    private TooManyCommunitiesHintCell hintCell;
    private ArrayList<TLRPC.Chat> inactiveChats;
    private ArrayList<String> inactiveChatsSignatures;
    private RecyclerListView listView;
    RecyclerListView.OnItemClickListener onItemClickListener;
    RecyclerListView.OnItemLongClickListener onItemLongClickListener;
    protected RadialProgressView progressBar;
    private SearchAdapter searchAdapter;
    private RecyclerListView searchListView;
    private FrameLayout searchViewContainer;
    private Set<Long> selectedIds;
    Runnable showProgressRunnable;
    int type;

    /* renamed from: org.telegram.ui.TooManyCommunitiesActivity$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ TooManyCommunitiesActivity this$0;

        public AnonymousClass1(TooManyCommunitiesActivity r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.this$0.progressBar.setVisibility(0);
            this.this$0.progressBar.setAlpha(0.0f);
            this.this$0.progressBar.animate().alpha(1.0f).start();
        }
    }

    /* renamed from: org.telegram.ui.TooManyCommunitiesActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ TooManyCommunitiesActivity this$0;

        public AnonymousClass2(TooManyCommunitiesActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.TooManyCommunitiesActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        boolean expanded;
        final /* synthetic */ TooManyCommunitiesActivity this$0;

        /* renamed from: org.telegram.ui.TooManyCommunitiesActivity$3$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass3 this$1;

            public AnonymousClass1(AnonymousClass3 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                TooManyCommunitiesActivity.m20907$$Nest$fgetsearchViewContainer(this.this$1.this$0).setVisibility(8);
            }
        }

        /* renamed from: org.telegram.ui.TooManyCommunitiesActivity$3$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass3 this$1;

            public AnonymousClass2(AnonymousClass3 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                TooManyCommunitiesActivity.m20905$$Nest$fgetlistView(this.this$1.this$0).setVisibility(8);
            }
        }

        public AnonymousClass3(TooManyCommunitiesActivity r1) {
            this.this$0 = r1;
            this.expanded = false;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchCollapse() {
            super.onSearchCollapse();
            if (TooManyCommunitiesActivity.m20905$$Nest$fgetlistView(this.this$0).getVisibility() == 0) goto L5;
            TooManyCommunitiesActivity.m20905$$Nest$fgetlistView(this.this$0).setVisibility(0);
            TooManyCommunitiesActivity.m20905$$Nest$fgetlistView(this.this$0).setAlpha(0.0f);
        L5:
            TooManyCommunitiesActivity.m20900$$Nest$fgetemptyView(this.this$0).setVisibility(8);
            TooManyCommunitiesActivity.m20898$$Nest$fgetadapter(this.this$0).notifyDataSetChanged();
            TooManyCommunitiesActivity.m20905$$Nest$fgetlistView(this.this$0).animate().alpha(1.0f).setDuration(150).setListener(null).start();
            TooManyCommunitiesActivity.m20907$$Nest$fgetsearchViewContainer(this.this$0).animate().alpha(0.0f).setDuration(150).setListener(new AnonymousClass1(this)).start();
            this.expanded = false;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r4) {
            String r42 = r4.getText().toString();
            TooManyCommunitiesActivity.m20906$$Nest$fgetsearchAdapter(this.this$0).search(r42);
            if (this.expanded == true) goto L12;
            if (TextUtils.isEmpty(r42) == true) goto L12;
            if (TooManyCommunitiesActivity.m20907$$Nest$fgetsearchViewContainer(this.this$0).getVisibility() == 0) goto L9;
            TooManyCommunitiesActivity.m20907$$Nest$fgetsearchViewContainer(this.this$0).setVisibility(0);
            TooManyCommunitiesActivity.m20907$$Nest$fgetsearchViewContainer(this.this$0).setAlpha(0.0f);
        L9:
            TooManyCommunitiesActivity.m20905$$Nest$fgetlistView(this.this$0).animate().alpha(0.0f).setDuration(150).setListener(new AnonymousClass2(this)).start();
            TooManyCommunitiesActivity.m20906$$Nest$fgetsearchAdapter(this.this$0).searchResultsSignatures.clear();
            TooManyCommunitiesActivity.m20906$$Nest$fgetsearchAdapter(this.this$0).searchResults.clear();
            TooManyCommunitiesActivity.m20906$$Nest$fgetsearchAdapter(this.this$0).notifyDataSetChanged();
            TooManyCommunitiesActivity.m20907$$Nest$fgetsearchViewContainer(this.this$0).animate().setListener(null).alpha(1.0f).setDuration(150).start();
            this.expanded = true;
            return;
        L12:
            if (this.expanded == true) goto L14;
            return;
        L14:
            if (TextUtils.isEmpty(r42) == false) goto L18;
            onSearchCollapse();
            return;
        }
    }

    /* renamed from: org.telegram.ui.TooManyCommunitiesActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends RecyclerView.OnScrollListener {
        final /* synthetic */ TooManyCommunitiesActivity this$0;

        public AnonymousClass4(TooManyCommunitiesActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 1) goto L6;
            AndroidUtilities.hideKeyboard(this.this$0.getParentActivity().getCurrentFocus());
            return;
        }
    }

    /* renamed from: org.telegram.ui.TooManyCommunitiesActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends FrameLayout {
        final /* synthetic */ TooManyCommunitiesActivity this$0;

        public AnonymousClass5(TooManyCommunitiesActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r7) {
            super.onDraw(r7);
            r7.drawRect(0.0f, 0.0f, getMeasuredWidth(), 1.0f, Theme.dividerPaint);
        }
    }

    /* renamed from: org.telegram.ui.TooManyCommunitiesActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ TooManyCommunitiesActivity this$0;

        public AnonymousClass6(TooManyCommunitiesActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            TooManyCommunitiesActivity.m20909$$Nest$fputbuttonAnimation(this.this$0, 0);
            TooManyCommunitiesActivity.m20899$$Nest$fgetbuttonLayout(this.this$0).setVisibility(8);
        }
    }

    /* renamed from: org.telegram.ui.TooManyCommunitiesActivity$7, reason: invalid class name */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ TooManyCommunitiesActivity this$0;

        public AnonymousClass7(TooManyCommunitiesActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            TooManyCommunitiesActivity.m20909$$Nest$fputbuttonAnimation(this.this$0, 0);
        }
    }

    /* renamed from: org.telegram.ui.TooManyCommunitiesActivity$8, reason: invalid class name */
    public class AnonymousClass8 extends AnimatorListenerAdapter {
        final /* synthetic */ TooManyCommunitiesActivity this$0;

        public AnonymousClass8(TooManyCommunitiesActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            this.this$0.progressBar.setVisibility(8);
        }
    }

    public class Adapter extends RecyclerListView.SelectionAdapter {
        int endPaddingPosition;
        int headerPosition;
        int hintPosition;
        int inactiveChatsEndRow;
        int inactiveChatsStartRow;
        int rowCount;
        int shadowPosition;
        final /* synthetic */ TooManyCommunitiesActivity this$0;

        public Adapter(TooManyCommunitiesActivity r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.rowCount;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != this.hintPosition) goto L7;
            return 1;
        L7:
            if (r2 != this.shadowPosition) goto L11;
            return 2;
        L11:
            if (r2 != this.headerPosition) goto L15;
            return 3;
        L15:
            if (r2 != this.endPaddingPosition) goto L18;
            return 5;
        L18:
            return 4;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r3) {
            if (r3.getAdapterPosition() >= this.inactiveChatsStartRow) goto L5;
            return false;
        L5:
            if (r3.getAdapterPosition() >= this.inactiveChatsEndRow) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            updateRows();
            super.notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r7, int r8) {
            int r0 = this.headerPosition;
            if (r8 < r0) goto L6;
            if (r0 <= 0) goto L6;
            r7.itemView.setAlpha(TooManyCommunitiesActivity.m20901$$Nest$fgetenterProgress(this.this$0));
        L8:
            if (getItemViewType(r8) != 4) goto L15;
            GroupCreateUserCell r72 = (GroupCreateUserCell) r7.itemView;
            TLRPC.Chat r02 = (TLRPC.Chat) TooManyCommunitiesActivity.m20903$$Nest$fgetinactiveChats(this.this$0).get(r8 - this.inactiveChatsStartRow);
            String r1 = (String) TooManyCommunitiesActivity.m20904$$Nest$fgetinactiveChatsSignatures(this.this$0).get(r8 - this.inactiveChatsStartRow);
            String r2 = r02.title;
            boolean r4 = true;
            if (r8 != (this.inactiveChatsEndRow - 1)) goto L13;
            r4 = false;
        L13:
            r72.setObject(r02, r2, r1, r4);
            r72.setChecked(TooManyCommunitiesActivity.m20908$$Nest$fgetselectedIds(this.this$0).contains(Long.valueOf(r02.id)), false);
            return;
        L15:
            return;
        L6:
            r7.itemView.setAlpha(1.0f);
            goto L8
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r7, int r8) {
            if (r8 != 1) goto L5;
            TooManyCommunitiesActivity.m20910$$Nest$fputhintCell(this.this$0, new TooManyCommunitiesHintCell(r7.getContext()));
            View r72 = TooManyCommunitiesActivity.m20902$$Nest$fgethintCell(this.this$0);
            int r82 = this.this$0.type;
            if (r82 != 0) goto L17;
            String r83 = LocaleController.getString(R.string.TooManyCommunitiesHintJoin);
        L20:
            TooManyCommunitiesActivity.m20902$$Nest$fgethintCell(this.this$0).setMessageText(r83);
            RecyclerView.LayoutParams r84 = new RecyclerView.LayoutParams(-1, -2);
            ((ViewGroup.MarginLayoutParams) r84).bottomMargin = AndroidUtilities.dp(16.0f);
            ((ViewGroup.MarginLayoutParams) r84).topMargin = AndroidUtilities.dp(23.0f);
            TooManyCommunitiesActivity.m20902$$Nest$fgethintCell(this.this$0).setLayoutParams(r84);
            View r6 = r72;
        L22:
            return new RecyclerListView.Holder(r6);
        L17:
            if (r82 != 1) goto L19;
            r83 = LocaleController.getString(R.string.TooManyCommunitiesHintEdit);
            goto L20
        L19:
            r83 = LocaleController.getString(R.string.TooManyCommunitiesHintCreate);
            goto L20
        L5:
            if (r8 != 2) goto L7;
            r6 = new ShadowSectionCell(r7.getContext());
            Drawable r73 = Theme.getThemedDrawableByKey(r7.getContext(), R.drawable.greydivider, Theme.key_windowBackgroundGrayShadow);
            CombinedDrawable r85 = new CombinedDrawable(new ColorDrawable(Theme.getColor(Theme.key_windowBackgroundGray)), r73);
            r85.setFullsize(true);
            r6.setBackground(r85);
            goto L22
        L7:
            if (r8 != 3) goto L9;
            HeaderCell r0 = new HeaderCell(r7.getContext(), Theme.key_windowBackgroundWhiteBlueHeader, 21, 8, false);
            r0.setHeight(54);
            r0.setText(LocaleController.getString(R.string.InactiveChats));
            r6 = r0;
            goto L22
        L9:
            if (r8 == 5) goto L11;
            r6 = new GroupCreateUserCell(r7.getContext(), 1, 0, false);
            goto L22
        L11:
            r6 = new EmptyCell(r7.getContext(), AndroidUtilities.dp(12.0f));
            goto L22
        }

        public void updateRows() {
            this.headerPosition = -1;
            this.inactiveChatsStartRow = -1;
            this.inactiveChatsEndRow = -1;
            this.endPaddingPosition = -1;
            this.hintPosition = 0;
            this.rowCount = 1 + 1;
            this.shadowPosition = 1;
            if (TooManyCommunitiesActivity.m20903$$Nest$fgetinactiveChats(this.this$0).isEmpty() == true) goto L6;
            int r1 = this.rowCount;
            int r2 = r1 + 1;
            this.headerPosition = r1;
            int r12 = r1 + 2;
            this.rowCount = r12;
            this.inactiveChatsStartRow = r2;
            int r13 = r12 + (TooManyCommunitiesActivity.m20903$$Nest$fgetinactiveChats(this.this$0).size() - 1);
            this.inactiveChatsEndRow = r13;
            this.rowCount = r13 + 1;
            this.endPaddingPosition = r13;
            return;
        }
    }

    public class SearchAdapter extends RecyclerListView.SelectionAdapter {
        private int lastSearchId;
        ArrayList<TLRPC.Chat> searchResults;
        ArrayList<String> searchResultsSignatures;
        private Runnable searchRunnable;
        final /* synthetic */ TooManyCommunitiesActivity this$0;

        /* renamed from: $r8$lambda$Z2j-sUrI1NWNLs2F0n7H1cMZjZ8, reason: not valid java name */
        public static /* synthetic */ void m20911$r8$lambda$Z2jsUrI1NWNLs2F0n7H1cMZjZ8(SearchAdapter r0, int r1, ArrayList r2, ArrayList r3) {
            r0.lambda$updateSearchResults$2(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$k6vrz7SAPS4P_eQZLOj6JykHftU(SearchAdapter r0, String r1, int r2) {
            r0.lambda$search$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$teaPmRNhHVXUNQUXngW0CEbHM4A(SearchAdapter r0, String r1, int r2) {
            r0.lambda$processSearch$1(r1, r2);
        }

        public SearchAdapter(TooManyCommunitiesActivity r1) {
            this.this$0 = r1;
            this.searchResults = new ArrayList();
            this.searchResultsSignatures = new ArrayList();
        }

        private /* synthetic */ void lambda$processSearch$1(String r17, int r18) {
            String r2 = r17.trim().toLowerCase();
            String r4 = null;
            if (r2.length() != 0) goto L6;
            updateSearchResults(null, null, r18);
            return;
        L6:
            String r3 = LocaleController.getInstance().getTranslitString(r2);
            if (r2.equals(r3) == false) goto L9;
        L12:
            int r32 = 0;
            if (r4 == null) goto L15;
            int r6 = 1;
        L16:
            int r62 = r6 + 1;
            String[] r7 = new String[r62];
            r7[0] = r2;
            if (r4 == null) goto L19;
            r7[1] = r4;
        L19:
            ArrayList<TLRPC.Chat> r22 = new ArrayList();
            ArrayList<String> r42 = new ArrayList();
            int r8 = 0;
        L21:
            if (r8 >= TooManyCommunitiesActivity.m20903$$Nest$fgetinactiveChats(this.this$0).size()) goto L43;
            TLRPC.Chat r9 = (TLRPC.Chat) TooManyCommunitiesActivity.m20903$$Nest$fgetinactiveChats(this.this$0).get(r8);
            int r10 = r32;
            int r11 = r10;
        L24:
            if (r10 >= 2) goto L42;
            if (r10 != 0) goto L27;
            String r12 = r9.title;
        L28:
            if (r12 == null) goto L41;
            String r122 = r12.toLowerCase();
            int r13 = r32;
        L31:
            if (r13 >= r62) goto L39;
            String r14 = r7[r13];
            if (r122.startsWith(r14) == true) goto L38;
            if (r122.contains(" " + r14) == true) goto L38;
            r13 = r13 + 1;
        L38:
            r11 = 1;
        L39:
            if (r11 == 0) goto L41;
            r22.add(r9);
            r42.add((String) TooManyCommunitiesActivity.m20904$$Nest$fgetinactiveChatsSignatures(this.this$0).get(r8));
        L41:
            r10 = r10 + 1;
            r32 = 0;
            goto L24
        L27:
            r12 = ChatObject.getPublicUsername(r9);
        L42:
            r8 = r8 + 1;
            r32 = 0;
            goto L21
        L43:
            updateSearchResults(r22, r42, r18);
            return;
        L15:
            r6 = 0;
            goto L16
        L9:
            if (r3.length() == 0) goto L12;
            r4 = r3;
            goto L12
        }

        private /* synthetic */ void lambda$search$0(String r1, int r2) {
            processSearch(r1, r2);
        }

        private /* synthetic */ void lambda$updateSearchResults$2(int r2, ArrayList r3, ArrayList r4) {
            if (r2 == this.lastSearchId) goto L5;
            return;
        L5:
            this.searchResults.clear();
            this.searchResultsSignatures.clear();
            if (r3 == null) goto L8;
            this.searchResults.addAll(r3);
            this.searchResultsSignatures.addAll(r4);
        L8:
            notifyDataSetChanged();
            boolean r22 = this.searchResults.isEmpty();
            TooManyCommunitiesActivity r1 = this.this$0;
            if (r22 == false) goto L12;
            TooManyCommunitiesActivity.m20900$$Nest$fgetemptyView(r1).setVisibility(0);
            return;
        L12:
            TooManyCommunitiesActivity.m20900$$Nest$fgetemptyView(r1).setVisibility(8);
        }

        private void updateSearchResults(ArrayList<TLRPC.Chat> r2, ArrayList<String> r3, int r4) {
            AndroidUtilities.runOnUIThread(new TooManyCommunitiesActivity$SearchAdapter$$ExternalSyntheticLambda2(this, r4, r2, r3));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.searchResults.size();
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r7, int r8) {
            TLRPC.Chat r0 = this.searchResults.get(r8);
            String r1 = this.searchResultsSignatures.get(r8);
            GroupCreateUserCell r72 = (GroupCreateUserCell) r7.itemView;
            String r2 = r0.title;
            boolean r4 = true;
            if (r8 != (this.searchResults.size() - 1)) goto L6;
            r4 = false;
        L6:
            r72.setObject(r0, r2, r1, r4);
            r72.setChecked(TooManyCommunitiesActivity.m20908$$Nest$fgetselectedIds(this.this$0).contains(Long.valueOf(r0.id)), false);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            return new RecyclerListView.Holder(new GroupCreateUserCell(r3.getContext(), 1, 0, false));
        }

        public void processSearch(String r3, int r4) {
            Utilities.searchQueue.postRunnable(new TooManyCommunitiesActivity$SearchAdapter$$ExternalSyntheticLambda1(this, r3, r4));
        }

        public void search(String r4) {
            if (this.searchRunnable == null) goto L6;
            Utilities.searchQueue.cancelRunnable(this.searchRunnable);
            this.searchRunnable = null;
        L6:
            if (TextUtils.isEmpty(r4) == false) goto L9;
            this.searchResults.clear();
            this.searchResultsSignatures.clear();
            notifyDataSetChanged();
            TooManyCommunitiesActivity.m20900$$Nest$fgetemptyView(this.this$0).setVisibility(8);
            return;
        L9:
            int r0 = this.lastSearchId + 1;
            this.lastSearchId = r0;
            DispatchQueue r1 = Utilities.searchQueue;
            TooManyCommunitiesActivity$SearchAdapter$$ExternalSyntheticLambda0 r2 = new TooManyCommunitiesActivity$SearchAdapter$$ExternalSyntheticLambda0(this, r4, r0);
            this.searchRunnable = r2;
            r1.postRunnable(r2, 300);
        }
    }

    /* renamed from: $r8$lambda$Z0gyaZ98Ug0ra5B_YaWd0xu-9Ac, reason: not valid java name */
    public static /* synthetic */ void m20896$r8$lambda$Z0gyaZ98Ug0ra5B_YaWd0xu9Ac(TooManyCommunitiesActivity r0, View r1) {
        r0.lambda$createView$2(r1);
    }

    /* renamed from: $r8$lambda$_88xixumU8-Ez0fLf9vtIbuGpuI, reason: not valid java name */
    public static /* synthetic */ void m20897$r8$lambda$_88xixumU8Ez0fLf9vtIbuGpuI(TooManyCommunitiesActivity r0) {
        r0.lambda$getThemeDescriptions$6();
    }

    public static /* synthetic */ void $r8$lambda$ayhFeTqw4wimdyprR5fSOk7lR48(TooManyCommunitiesActivity r0, ArrayList r1, TLRPC.TL_messages_inactiveChats r2) {
        r0.lambda$loadInactiveChannels$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$nxJy4OoKNEFrwwi76cXjL52n0G0(TooManyCommunitiesActivity r0, ValueAnimator r1) {
        r0.lambda$loadInactiveChannels$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$qb6S10YHcxmNMDriCrmQxxx3a7I(TooManyCommunitiesActivity r0, View r1, int r2) {
        r0.lambda$new$0(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$rEFAYWbuxzk_0Qw7_kgmxM0sCY0(TooManyCommunitiesActivity r0, View r1, int r2) {
        return r0.lambda$new$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$t2_A5GuRSAkvpdhh0zriLq9OSyc(TooManyCommunitiesActivity r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$loadInactiveChannels$5(r1, r2);
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Adapter m20898$$Nest$fgetadapter(TooManyCommunitiesActivity r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetbuttonLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m20899$$Nest$fgetbuttonLayout(TooManyCommunitiesActivity r0) {
        return r0.buttonLayout;
    }

    /* renamed from: -$$Nest$fgetemptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmptyTextProgressView m20900$$Nest$fgetemptyView(TooManyCommunitiesActivity r0) {
        return r0.emptyView;
    }

    /* renamed from: -$$Nest$fgetenterProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m20901$$Nest$fgetenterProgress(TooManyCommunitiesActivity r0) {
        return r0.enterProgress;
    }

    /* renamed from: -$$Nest$fgethintCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TooManyCommunitiesHintCell m20902$$Nest$fgethintCell(TooManyCommunitiesActivity r0) {
        return r0.hintCell;
    }

    /* renamed from: -$$Nest$fgetinactiveChats, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m20903$$Nest$fgetinactiveChats(TooManyCommunitiesActivity r0) {
        return r0.inactiveChats;
    }

    /* renamed from: -$$Nest$fgetinactiveChatsSignatures, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m20904$$Nest$fgetinactiveChatsSignatures(TooManyCommunitiesActivity r0) {
        return r0.inactiveChatsSignatures;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m20905$$Nest$fgetlistView(TooManyCommunitiesActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetsearchAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SearchAdapter m20906$$Nest$fgetsearchAdapter(TooManyCommunitiesActivity r0) {
        return r0.searchAdapter;
    }

    /* renamed from: -$$Nest$fgetsearchViewContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m20907$$Nest$fgetsearchViewContainer(TooManyCommunitiesActivity r0) {
        return r0.searchViewContainer;
    }

    /* renamed from: -$$Nest$fgetselectedIds, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Set m20908$$Nest$fgetselectedIds(TooManyCommunitiesActivity r0) {
        return r0.selectedIds;
    }

    /* renamed from: -$$Nest$fputbuttonAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20909$$Nest$fputbuttonAnimation(TooManyCommunitiesActivity r0, int r1) {
        r0.buttonAnimation = r1;
    }

    /* renamed from: -$$Nest$fputhintCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20910$$Nest$fputhintCell(TooManyCommunitiesActivity r0, TooManyCommunitiesHintCell r1) {
        r0.hintCell = r1;
    }

    public TooManyCommunitiesActivity(int r3) {
        this.inactiveChats = new ArrayList();
        this.inactiveChatsSignatures = new ArrayList();
        this.selectedIds = new HashSet();
        this.buttonHeight = AndroidUtilities.dp(64.0f);
        this.showProgressRunnable = new AnonymousClass1(this);
        this.onItemClickListener = new TooManyCommunitiesActivity$$ExternalSyntheticLambda2(this);
        this.onItemLongClickListener = new TooManyCommunitiesActivity$$ExternalSyntheticLambda3(this);
        Bundle r0 = new Bundle();
        r0.putInt("type", r3);
        this.arguments = r0;
    }

    private /* synthetic */ void lambda$createView$2(View r8) {
        if (this.selectedIds.isEmpty() == false) goto L5;
        return;
    L5:
        TLRPC.User r82 = getMessagesController().getUser(Long.valueOf(getUserConfig().getClientUserId()));
        ArrayList r0 = new ArrayList();
        int r2 = 0;
    L7:
        if (r2 >= this.inactiveChats.size()) goto L12;
        if (this.selectedIds.contains(Long.valueOf(this.inactiveChats.get(r2).id)) == false) goto L11;
        r0.add(this.inactiveChats.get(r2));
    L11:
        r2 = r2 + 1;
        goto L7
    L12:
        int r22 = 0;
    L14:
        if (r22 >= r0.size()) goto L16;
        TLRPC.Chat r3 = (TLRPC.Chat) r0.get(r22);
        getMessagesController().putChat(r3, false);
        getMessagesController().deleteParticipantFromChat(r3.id, r82);
        r22 = r22 + 1;
        goto L14
    L16:
        finishFragment();
    }

    private /* synthetic */ void lambda$getThemeDescriptions$6() {
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L10;
        int r02 = r0.getChildCount();
        int r2 = 0;
    L5:
        if (r2 >= r02) goto L10;
        View r3 = this.listView.getChildAt(r2);
        if ((r3 instanceof GroupCreateUserCell) == false) goto L9;
        ((GroupCreateUserCell) r3).update(0);
    L9:
        r2 = r2 + 1;
    L10:
        RecyclerListView r03 = this.searchListView;
        if (r03 == null) goto L18;
        int r04 = r03.getChildCount();
        int r22 = 0;
    L13:
        if (r22 >= r04) goto L18;
        View r32 = this.searchListView.getChildAt(r22);
        if ((r32 instanceof GroupCreateUserCell) == false) goto L17;
        ((GroupCreateUserCell) r32).update(0);
    L17:
        r22 = r22 + 1;
    L18:
        this.buttonTextView.setBackground(Theme.AdaptiveRipple.filledRectByKey(Theme.key_featuredStickers_addButton, new float[]{4.0f}));
        this.progressBar.setProgressColor(Theme.getColor(Theme.key_progressCircle));
    }

    private /* synthetic */ void lambda$loadInactiveChannels$3(ValueAnimator r4) {
        this.enterProgress = ((Float) r4.getAnimatedValue()).floatValue();
        int r42 = this.listView.getChildCount();
        int r0 = 0;
    L3:
        if (r0 >= r42) goto L10;
        RecyclerListView r1 = this.listView;
        int r12 = r1.getChildAdapterPosition(r1.getChildAt(r0));
        int r2 = this.adapter.headerPosition;
        if (r12 < r2) goto L8;
        if (r2 <= 0) goto L8;
        this.listView.getChildAt(r0).setAlpha(this.enterProgress);
    L9:
        r0 = r0 + 1;
    L8:
        this.listView.getChildAt(r0).setAlpha(1.0f);
        goto L9
    }

    private /* synthetic */ void lambda$loadInactiveChannels$4(ArrayList r3, TLRPC.TL_messages_inactiveChats r4) {
        this.inactiveChatsSignatures.clear();
        this.inactiveChats.clear();
        this.inactiveChatsSignatures.addAll(r3);
        this.inactiveChats.addAll(r4.chats);
        this.adapter.notifyDataSetChanged();
        if (this.listView.getMeasuredHeight() <= 0) goto L5;
        ValueAnimator r32 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.enterAnimator = r32;
        r32.addUpdateListener(new TooManyCommunitiesActivity$$ExternalSyntheticLambda6(this));
        this.enterAnimator.setDuration(100);
        this.enterAnimator.start();
    L6:
        AndroidUtilities.cancelRunOnUIThread(this.showProgressRunnable);
        if (this.progressBar.getVisibility() != 0) goto L10;
        this.progressBar.animate().alpha(0.0f).setListener(new AnonymousClass8(this)).start();
        return;
    L10:
        return;
    L5:
        this.enterProgress = 1.0f;
        goto L6
    }

    private /* synthetic */ void lambda$loadInactiveChannels$5(TLObject r8, TLRPC.TL_error r9) {
        if (r9 != null) goto L27;
        TLRPC.TL_messages_inactiveChats r82 = (TLRPC.TL_messages_inactiveChats) r8;
        ArrayList r92 = new ArrayList();
        int r1 = 0;
    L5:
        if (r1 >= r82.chats.size()) goto L21;
        TLRPC.Chat r2 = r82.chats.get(r1);
        int r3 = (getConnectionsManager().getCurrentTime() - r82.dates.get(r1).intValue()) / 86400;
        if (r3 >= 30) goto L10;
        String r32 = LocaleController.formatPluralString("Days", r3, new Object[0]);
    L14:
        if (ChatObject.isMegagroup(r2) == false) goto L17;
        r92.add(LocaleController.formatString("InactiveChatSignature", R.string.InactiveChatSignature, new Object[]{LocaleController.formatPluralString("Members", r2.participants_count, new Object[0]), r32}));
    L20:
        r1 = r1 + 1;
        goto L5
    L17:
        if (ChatObject.isChannel(r2) == false) goto L19;
        r92.add(LocaleController.formatString("InactiveChannelSignature", R.string.InactiveChannelSignature, new Object[]{r32}));
        goto L20
    L19:
        r92.add(LocaleController.formatString("InactiveChatSignature", R.string.InactiveChatSignature, new Object[]{LocaleController.formatPluralString("Members", r2.participants_count, new Object[0]), r32}));
        goto L20
    L10:
        if (r3 >= 365) goto L12;
        r32 = LocaleController.formatPluralString("Months", r3 / 30, new Object[0]);
        goto L14
    L12:
        r32 = LocaleController.formatPluralString("Years", r3 / 365, new Object[0]);
        goto L14
    L21:
        AndroidUtilities.runOnUIThread(new TooManyCommunitiesActivity$$ExternalSyntheticLambda5(this, r92, r82));
        return;
    }

    private /* synthetic */ void lambda$new$0(View r6, int r7) {
        if ((r6 instanceof GroupCreateUserCell) == false) goto L18;
        GroupCreateUserCell r72 = (GroupCreateUserCell) r6;
        TLRPC.Chat r0 = (TLRPC.Chat) r72.getObject();
        boolean r1 = this.selectedIds.contains(Long.valueOf(r0.id));
        Set<Long> r2 = this.selectedIds;
        if (r1 == false) goto L7;
        r2.remove(Long.valueOf(r0.id));
        r72.setChecked(false, true);
    L8:
        onSelectedCountChange();
        if (this.selectedIds.isEmpty() == false) goto L11;
        return;
    L11:
        if (this.searchViewContainer.getVisibility() != 0) goto L13;
        RecyclerListView r73 = this.searchListView;
    L14:
        int r02 = r73.getHeight() - r6.getBottom();
        int r5 = this.buttonHeight;
        if (r02 >= r5) goto L20;
        r73.smoothScrollBy(0, r5 - r02);
        return;
    L20:
        return;
    L13:
        r73 = this.listView;
        goto L14
    L7:
        r2.add(Long.valueOf(r0.id));
        r72.setChecked(true, true);
        goto L8
    }

    private /* synthetic */ boolean lambda$new$1(View r1, int r2) {
        this.onItemClickListener.onItemClick(r1, r2);
        return true;
    }

    private void loadInactiveChannels() {
        this.adapter.notifyDataSetChanged();
        this.enterProgress = 0.0f;
        AndroidUtilities.runOnUIThread(this.showProgressRunnable, 500);
        TLRPC.TL_channels_getInactiveChannels r0 = new TLRPC.TL_channels_getInactiveChannels();
        getConnectionsManager().sendRequest(r0, new TooManyCommunitiesActivity$$ExternalSyntheticLambda4(this));
    }

    private void onSelectedCountChange() {
        if (this.selectedIds.isEmpty() == false) goto L28;
        if (this.buttonAnimation == (-1)) goto L28;
        if (this.buttonLayout.getVisibility() != 0) goto L28;
        this.buttonAnimation = -1;
        this.buttonLayout.animate().setListener(null).cancel();
        this.buttonLayout.animate().translationY(this.buttonHeight).setDuration(200).setListener(new AnonymousClass6(this)).start();
        if (this.searchViewContainer.getVisibility() != 0) goto L11;
        RecyclerListView r0 = this.searchListView;
    L12:
        r0.hideSelector(false);
        int r8 = ((LinearLayoutManager) r0.getLayoutManager()).findLastVisibleItemPosition();
        if (r8 != (r0.getAdapter().getItemCount() - 1)) goto L15;
    L18:
        RecyclerView.ViewHolder r9 = r0.findViewHolderForAdapterPosition(r8);
        if (r9 == null) goto L26;
        int r92 = r9.itemView.getBottom();
        if (r8 != (this.adapter.getItemCount() - 2)) goto L24;
        r92 = r92 + AndroidUtilities.dp(12.0f);
    L24:
        if ((r0.getMeasuredHeight() - r92) > this.buttonHeight) goto L26;
        r0.setTranslationY(-(r0.getMeasuredHeight() - r92));
        r0.animate().translationY(0.0f).setDuration(200).start();
    L26:
        this.listView.setPadding(0, 0, 0, 0);
        this.searchListView.setPadding(0, 0, 0, 0);
        goto L28
    L15:
        if (r8 != (r0.getAdapter().getItemCount() - 2)) goto L26;
        if (r0 != this.listView) goto L26;
    L11:
        r0 = this.listView;
    L28:
        if (this.selectedIds.isEmpty() == true) goto L35;
        if (this.buttonLayout.getVisibility() != 8) goto L35;
        if (this.buttonAnimation == 1) goto L35;
        this.buttonAnimation = 1;
        this.buttonLayout.setVisibility(0);
        this.buttonLayout.setTranslationY(this.buttonHeight);
        this.buttonLayout.animate().setListener(null).cancel();
        this.buttonLayout.animate().translationY(0.0f).setDuration(200).setListener(new AnonymousClass7(this)).start();
        this.listView.setPadding(0, 0, 0, this.buttonHeight - AndroidUtilities.dp(12.0f));
        this.searchListView.setPadding(0, 0, 0, this.buttonHeight);
    L35:
        if (this.selectedIds.isEmpty() == true) goto L38;
        this.buttonTextView.setText(LocaleController.formatString("LeaveChats", R.string.LeaveChats, new Object[]{LocaleController.formatPluralString("Chats", this.selectedIds.size(), new Object[0])}));
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r13) {
        this.type = this.arguments.getInt("type", 0);
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.LimitReached));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass2(this));
        ActionBarMenuItem r0 = this.actionBar.createMenu().addItem(0, R.drawable.outline_header_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass3(this));
        r0.setContentDescription(LocaleController.getString(R.string.Search));
        r0.setSearchFieldHint(LocaleController.getString(R.string.Search));
        FrameLayout r02 = new FrameLayout(r13);
        this.fragmentView = r02;
        RecyclerListView r3 = new RecyclerListView(r13);
        this.listView = r3;
        r3.setLayoutManager(new LinearLayoutManager(r13));
        RecyclerListView r32 = this.listView;
        Adapter r4 = new Adapter(this);
        this.adapter = r4;
        r32.setAdapter(r4);
        this.listView.setClipToPadding(false);
        this.listView.setOnItemClickListener(this.onItemClickListener);
        this.listView.setOnItemLongClickListener(this.onItemLongClickListener);
        RecyclerListView r33 = new RecyclerListView(r13);
        this.searchListView = r33;
        r33.setLayoutManager(new LinearLayoutManager(r13));
        RecyclerListView r34 = this.searchListView;
        SearchAdapter r42 = new SearchAdapter(this);
        this.searchAdapter = r42;
        r34.setAdapter(r42);
        this.searchListView.setOnItemClickListener(this.onItemClickListener);
        this.searchListView.setOnItemLongClickListener(this.onItemLongClickListener);
        this.searchListView.setOnScrollListener(new AnonymousClass4(this));
        EmptyTextProgressView r35 = new EmptyTextProgressView(r13);
        this.emptyView = r35;
        r35.setShowAtCenter(true);
        this.emptyView.setText(LocaleController.getString(R.string.NoResult));
        this.emptyView.showTextView();
        RadialProgressView r36 = new RadialProgressView(r13);
        this.progressBar = r36;
        r02.addView(r36, LayoutHelper.createFrame(-2, -2.0f));
        this.adapter.updateRows();
        this.progressBar.setVisibility(8);
        r02.addView(this.listView);
        FrameLayout r37 = new FrameLayout(r13);
        this.searchViewContainer = r37;
        r37.addView(this.searchListView);
        this.searchViewContainer.addView(this.emptyView);
        this.searchViewContainer.setVisibility(8);
        r02.addView(this.searchViewContainer);
        loadInactiveChannels();
        View r38 = this.fragmentView;
        int r5 = Theme.key_windowBackgroundWhite;
        r38.setBackgroundColor(Theme.getColor(r5));
        AnonymousClass5 r39 = new AnonymousClass5(this, r13);
        this.buttonLayout = r39;
        r39.setWillNotDraw(false);
        TextView r310 = new TextView(r13);
        this.buttonTextView = r310;
        r310.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
        this.buttonTextView.setGravity(17);
        this.buttonTextView.setTextSize(1, 14.0f);
        this.buttonTextView.setTypeface(AndroidUtilities.bold());
        this.buttonTextView.setBackground(Theme.AdaptiveRipple.filledRectByKey(Theme.key_featuredStickers_addButton, new float[]{4.0f}));
        r02.addView(this.buttonLayout, LayoutHelper.createFrame(-1, 64, 80));
        this.buttonLayout.setBackgroundColor(Theme.getColor(r5));
        this.buttonLayout.addView(this.buttonTextView, LayoutHelper.createFrame(-1, -1.0f, 0, 16.0f, 12.0f, 16.0f, 12.0f));
        this.buttonLayout.setVisibility(8);
        this.buttonTextView.setOnClickListener(new TooManyCommunitiesActivity$$ExternalSyntheticLambda0(this));
        return this.fragmentView;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        TooManyCommunitiesActivity$$ExternalSyntheticLambda1 r8 = new TooManyCommunitiesActivity$$ExternalSyntheticLambda1(this);
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_actionBarDefault));
        ActionBar r11 = this.actionBar;
        int r12 = ThemeDescription.FLAG_AB_ITEMSCOLOR;
        int r17 = Theme.key_actionBarDefaultIcon;
        r1.add(new ThemeDescription(r11, r12, null, null, null, null, r17));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, r17));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SEARCH, null, null, null, null, Theme.key_actionBarDefaultSearch));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SEARCHPLACEHOLDER, null, null, null, null, Theme.key_actionBarDefaultSearchPlaceholder));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        int r172 = Theme.key_chats_nameMessage_threeLines;
        r1.add(new ThemeDescription(this.hintCell, 0, new Class[]{TooManyCommunitiesHintCell.class}, new String[]{"imageView"}, null, null, null, r172));
        r1.add(new ThemeDescription(this.hintCell, 0, new Class[]{TooManyCommunitiesHintCell.class}, new String[]{"headerTextView"}, null, null, null, r172));
        r1.add(new ThemeDescription(this.hintCell, 0, new Class[]{TooManyCommunitiesHintCell.class}, new String[]{"messageTextView"}, null, null, null, Theme.key_chats_message));
        View r112 = this.fragmentView;
        int r122 = ThemeDescription.FLAG_BACKGROUND;
        int r173 = Theme.key_windowBackgroundWhite;
        r1.add(new ThemeDescription(r112, r122, null, null, null, null, r173));
        r1.add(new ThemeDescription(this.buttonLayout, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r173));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{ShadowSectionCell.class}, null, null, null, Theme.key_windowBackgroundGrayShadow));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_BACKGROUNDFILTER | ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{ShadowSectionCell.class}, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{HeaderCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteBlueHeader));
        int r174 = Theme.key_groupcreate_sectionText;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{GroupCreateUserCell.class}, new String[]{"textView"}, null, null, null, r174));
        int r32 = Theme.key_checkbox;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{GroupCreateUserCell.class}, new String[]{"checkBox"}, null, null, null, r32));
        int r41 = Theme.key_checkboxDisabled;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{GroupCreateUserCell.class}, new String[]{"checkBox"}, null, null, null, r41));
        int r50 = Theme.key_checkboxCheck;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{GroupCreateUserCell.class}, new String[]{"checkBox"}, null, null, null, r50));
        int r27 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{GroupCreateUserCell.class}, new String[]{"nameTextView"}, null, null, null, r27));
        int r68 = Theme.key_windowBackgroundWhiteGrayText;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{GroupCreateUserCell.class}, new String[]{"statusTextView"}, null, null, null, r68));
        Drawable[] r14 = Theme.avatarDrawables;
        int r16 = Theme.key_avatar_text;
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{GroupCreateUserCell.class}, null, r14, null, r16));
        r1.add(new ThemeDescription(this.searchListView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{GroupCreateUserCell.class}, new String[]{"textView"}, null, null, null, r174));
        r1.add(new ThemeDescription(this.searchListView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{GroupCreateUserCell.class}, new String[]{"checkBox"}, null, null, null, r32));
        r1.add(new ThemeDescription(this.searchListView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{GroupCreateUserCell.class}, new String[]{"checkBox"}, null, null, null, r41));
        r1.add(new ThemeDescription(this.searchListView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{GroupCreateUserCell.class}, new String[]{"checkBox"}, null, null, null, r50));
        r1.add(new ThemeDescription(this.searchListView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{GroupCreateUserCell.class}, new String[]{"nameTextView"}, null, null, null, r27));
        r1.add(new ThemeDescription(this.searchListView, ThemeDescription.FLAG_TEXTCOLOR | ThemeDescription.FLAG_CHECKTAG, new Class[]{GroupCreateUserCell.class}, new String[]{"statusTextView"}, null, null, null, r68));
        r1.add(new ThemeDescription(this.searchListView, 0, new Class[]{GroupCreateUserCell.class}, null, Theme.avatarDrawables, null, r16));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundRed));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundOrange));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundViolet));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundGreen));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundCyan));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundBlue));
        r1.add(new ThemeDescription(null, 0, null, null, null, r8, Theme.key_avatar_backgroundPink));
        r1.add(new ThemeDescription(this.emptyView, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_emptyListPlaceholder));
        r1.add(new ThemeDescription(this.buttonTextView, 0, null, null, null, r8, Theme.key_featuredStickers_addButton));
        TextView r3 = this.buttonTextView;
        int r9 = Theme.key_featuredStickers_addButtonPressed;
        r1.add(new ThemeDescription(r3, 0, null, null, null, r8, r9));
        r1.add(new ThemeDescription(this.progressBar, 0, null, null, null, r8, r9));
        r1.add(new ThemeDescription(this.hintCell, 0, new Class[]{TooManyCommunitiesHintCell.class}, new String[]{"imageLayout"}, null, null, null, Theme.key_text_RedRegular));
        return r1;
    }
}
