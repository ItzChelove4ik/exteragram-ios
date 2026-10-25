package org.telegram.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.CacheByChatsController;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.UserCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.ListView.AdapterWithDiffUtils;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes6.dex */
public class CacheChatsExceptionsFragment extends BaseFragment {
    private final int VIEW_TYPE_ADD_EXCEPTION;
    private final int VIEW_TYPE_CHAT;
    private final int VIEW_TYPE_DELETE_ALL;
    private final int VIEW_TYPE_DIVIDER;
    Adapter adapter;
    int currentType;
    ArrayList<CacheByChatsController.KeepMediaException> exceptionsDialogs;
    ArrayList<Item> items;
    RecyclerListView recyclerListView;

    /* renamed from: org.telegram.ui.CacheChatsExceptionsFragment$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ CacheChatsExceptionsFragment this$0;

        public AnonymousClass1(CacheChatsExceptionsFragment r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    public class Adapter extends AdapterWithDiffUtils {
        final /* synthetic */ CacheChatsExceptionsFragment this$0;

        private Adapter(CacheChatsExceptionsFragment r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Adapter(CacheChatsExceptionsFragment r1, CacheChatsExceptionsFragmentIA r2) {
            this(r1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.this$0.items.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return this.this$0.items.get(r1).viewType;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r3) {
            if (r3.getItemViewType() != 1) goto L5;
        L11:
            return true;
        L5:
            if (r3.getItemViewType() == 2) goto L11;
            if (r3.getItemViewType() == 4) goto L11;
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r9, int r10) {
            if (this.this$0.items.get(r10).viewType != 2) goto L23;
            UserCell r2 = (UserCell) r9.itemView;
            CacheByChatsController.KeepMediaException r92 = this.this$0.items.get(r10).exception;
            TLObject r3 = this.this$0.getMessagesController().getUserOrChat(r92.dialogId);
            if ((r3 instanceof TLRPC.User) == false) goto L12;
            TLRPC.User r0 = (TLRPC.User) r3;
            if (r0.self == false) goto L9;
            String r02 = LocaleController.getString(R.string.SavedMessages);
        L10:
            String r4 = r02;
            boolean r03 = true;
            r2.setSelfAsSavedMessages(true);
            String r5 = CacheByChatsController.getKeepMediaString(r92.keepMedia);
            if (r10 != (this.this$0.items.size() - 1)) goto L18;
        L21:
            r2.setData(r3, r4, r5, 0, r03);
            return;
        L18:
            if (this.this$0.items.get(r10 + 1).viewType == 2) goto L21;
            r03 = false;
            goto L21
        L9:
            r02 = ContactsController.formatName(r0.first_name, r0.last_name);
            goto L10
        L12:
            if ((r3 instanceof TLRPC.Chat) == false) goto L14;
            r02 = ((TLRPC.Chat) r3).title;
            goto L10
        L14:
            r02 = null;
            goto L10
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r7, int r8) {
            if (r8 != 1) goto L5;
            TextCell r82 = new TextCell(r7.getContext());
            r82.setTextAndIcon(LocaleController.getString(R.string.NotificationsAddAnException), R.drawable.msg_contact_add, true);
            r82.setColors(Theme.key_windowBackgroundWhiteBlueIcon, Theme.key_windowBackgroundWhiteBlueButton);
            r82.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            View r83 = r82;
        L12:
            View r72 = r83;
        L16:
            r72.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r72);
        L5:
            if (r8 != 2) goto L7;
            View r0 = new UserCell(r7.getContext(), 4, 0, false, false);
            r0.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            r72 = r0;
            goto L16
        L7:
            if (r8 != 3) goto L9;
            r83 = new ShadowSectionCell(r7.getContext());
            goto L12
        L9:
            if (r8 == 4) goto L11;
            r72 = null;
            goto L16
        L11:
            TextCell r84 = new TextCell(r7.getContext());
            r84.setText(LocaleController.getString(R.string.NotificationsDeleteAllException), false);
            r84.setColors(-1, Theme.key_text_RedRegular);
            r84.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            r83 = r84;
            goto L12
        }
    }

    public class Item extends AdapterWithDiffUtils.Item {
        final CacheByChatsController.KeepMediaException exception;
        final /* synthetic */ CacheChatsExceptionsFragment this$0;

        private Item(CacheChatsExceptionsFragment r1, int r2, CacheByChatsController.KeepMediaException r3) {
            this.this$0 = r1;
            super(r2, false);
            this.exception = r3;
        }

        public /* synthetic */ Item(CacheChatsExceptionsFragment r1, int r2, CacheByChatsController.KeepMediaException r3, CacheChatsExceptionsFragmentIA r4) {
            this(r1, r2, r3);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L22:
            return false;
        L8:
            if (getClass() != r5.getClass()) goto L22;
            Item r52 = (Item) r5;
            if (this.viewType == r52.viewType) goto L13;
            return false;
        L13:
            CacheByChatsController.KeepMediaException r4 = this.exception;
            if (r4 == null) goto L21;
            CacheByChatsController.KeepMediaException r53 = r52.exception;
            if (r53 == null) goto L21;
            if (r4.dialogId != r53.dialogId) goto L20;
            return true;
        L20:
            return false;
        L21:
            return true;
        }
    }

    public static /* synthetic */ void $r8$lambda$0GLqnzBjgfAu8Dhk9PLVe0Hksbw(CacheChatsExceptionsFragment r0, View r1, int r2, float r3, float r4) {
        r0.lambda$createView$3(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$Alqbz8j5U3swQDgQtRpo90GYas8(CacheChatsExceptionsFragment r0) {
        r0.lambda$createView$2();
    }

    public static /* synthetic */ void $r8$lambda$Lg9sMdhr8_NNICx4ECdSUuUFKU0(CacheChatsExceptionsFragment r0, CacheByChatsController.KeepMediaException r1, int r2, int r3) {
        r0.lambda$showPopupFor$4(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$SJQVpunu8WCs_GYF7qiuThFp0yM(CacheChatsExceptionsFragment r0, CacheByChatsController.KeepMediaException r1, int r2, int r3) {
        r0.lambda$createView$1(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$l9wcOCIj-mj05e5inbTH5lhc04c, reason: not valid java name */
    public static /* synthetic */ void m6410$r8$lambda$l9wcOCIjmj05e5inbTH5lhc04c(CacheChatsExceptionsFragment r0, CacheByChatsController.KeepMediaException r1) {
        r0.lambda$showPopupFor$5(r1);
    }

    /* renamed from: $r8$lambda$skueoPaSy9I5-ZeqocSySRQTnjU, reason: not valid java name */
    public static /* synthetic */ boolean m6411$r8$lambda$skueoPaSy9I5ZeqocSySRQTnjU(CacheChatsExceptionsFragment r0, DialogsActivity r1, DialogsActivity r2, ArrayList r3, CharSequence r4, boolean r5, boolean r6, int r7, int r8, TopicsFragment r9) {
        return r0.lambda$createView$0(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public CacheChatsExceptionsFragment(Bundle r1) {
        super(r1);
        this.VIEW_TYPE_ADD_EXCEPTION = 1;
        this.VIEW_TYPE_CHAT = 2;
        this.VIEW_TYPE_DIVIDER = 3;
        this.VIEW_TYPE_DELETE_ALL = 4;
        this.items = new ArrayList();
        this.exceptionsDialogs = new ArrayList();
    }

    private /* synthetic */ boolean lambda$createView$0(DialogsActivity r3, DialogsActivity r4, ArrayList r5, CharSequence r6, boolean r7, boolean r8, int r9, int r10, TopicsFragment r11) {
        r3.finishFragment();
        CacheByChatsController.KeepMediaException r32 = null;
        int r42 = 0;
        int r62 = 0;
    L3:
        boolean r82 = true;
        if (r62 >= r5.size()) goto L19;
        int r72 = 0;
    L7:
        if (r72 >= this.exceptionsDialogs.size()) goto L12;
        if (this.exceptionsDialogs.get(r72).dialogId == ((MessagesStorage.TopicKey) r5.get(r62)).dialogId) goto L10;
        r72 = r72 + 1;
        goto L7
    L10:
        r32 = this.exceptionsDialogs.get(r72);
    L13:
        if (r82 == true) goto L18;
        int r33 = CacheByChatsController.KEEP_MEDIA_FOREVER;
        if (getMessagesController().getCacheByChatsController().getKeepMedia(this.currentType) != CacheByChatsController.KEEP_MEDIA_FOREVER) goto L17;
        r33 = CacheByChatsController.KEEP_MEDIA_ONE_DAY;
    L17:
        ArrayList<CacheByChatsController.KeepMediaException> r73 = this.exceptionsDialogs;
        CacheByChatsController.KeepMediaException r83 = new CacheByChatsController.KeepMediaException(((MessagesStorage.TopicKey) r5.get(r62)).dialogId, r33);
        r73.add(r83);
        r32 = r83;
    L18:
        r62 = r62 + 1;
        goto L3
    L12:
        r82 = false;
        goto L13
    L19:
        getMessagesController().getCacheByChatsController().saveKeepMediaExceptions(this.currentType, this.exceptionsDialogs);
        updateRows();
        if (r32 == null) goto L31;
        int r52 = 0;
    L23:
        if (r52 >= this.items.size()) goto L30;
        if (this.items.get(r52).exception == null) goto L29;
        if (this.items.get(r52).exception.dialogId != r32.dialogId) goto L29;
        r42 = r52;
    L29:
        r52 = r52 + 1;
    L30:
        this.recyclerListView.scrollToPosition(r42);
        showPopupFor(r32);
    L31:
        return true;
    }

    private /* synthetic */ void lambda$createView$1(CacheByChatsController.KeepMediaException r1, int r2, int r3) {
        if (r3 != CacheByChatsController.KEEP_MEDIA_DELETE) goto L5;
        this.exceptionsDialogs.remove(r1);
        updateRows();
    L6:
        getMessagesController().getCacheByChatsController().saveKeepMediaExceptions(this.currentType, this.exceptionsDialogs);
        return;
    L5:
        r1.keepMedia = r3;
        AndroidUtilities.updateVisibleRows(this.recyclerListView);
        goto L6
    }

    private /* synthetic */ void lambda$createView$2() {
        this.exceptionsDialogs.clear();
        getMessagesController().getCacheByChatsController().saveKeepMediaExceptions(this.currentType, this.exceptionsDialogs);
        updateRows();
        finishFragment();
    }

    private /* synthetic */ void lambda$createView$3(View r9, int r10, float r11, float r12) {
        if (this.items.get(r10).viewType != 1) goto L12;
        Bundle r92 = new Bundle();
        r92.putBoolean("onlySelect", true);
        r92.putBoolean("checkCanWrite", false);
        int r102 = this.currentType;
        if (r102 != 1) goto L7;
        r92.putInt("dialogsType", 6);
    L10:
        r92.putBoolean("allowGlobalSearch", false);
        DialogsActivity r103 = new DialogsActivity(r92);
        r103.setDelegate(new CacheChatsExceptionsFragment$$ExternalSyntheticLambda2(this, r103));
        presentFragment(r103);
        return;
    L7:
        if (r102 != 2) goto L9;
        r92.putInt("dialogsType", 5);
        goto L10
    L9:
        r92.putInt("dialogsType", 4);
        goto L10
    L12:
        int r0 = this.items.get(r10).viewType;
        ArrayList<Item> r4 = this.items;
        if (r0 != 2) goto L17;
        CacheByChatsController.KeepMediaException r104 = r4.get(r10).exception;
        KeepMediaPopupView r02 = new KeepMediaPopupView(this, r9.getContext());
        r02.updateForDialog(false);
        r02.setParentWindow(AlertsCreator.createSimplePopup(this, r02, r9, r11, r12));
        r02.setCallback(new CacheChatsExceptionsFragment$$ExternalSyntheticLambda3(this, r104));
        return;
    L17:
        if (r4.get(r10).viewType != 4) goto L20;
        AlertDialog r8 = AlertsCreator.createSimpleAlert(getContext(), LocaleController.getString(R.string.NotificationsDeleteAllExceptionTitle), LocaleController.getString(R.string.NotificationsDeleteAllExceptionAlert), LocaleController.getString(R.string.Delete), new CacheChatsExceptionsFragment$$ExternalSyntheticLambda4(this), null).create();
        r8.show();
        r8.redPositive();
        return;
    }

    private /* synthetic */ void lambda$showPopupFor$4(CacheByChatsController.KeepMediaException r1, int r2, int r3) {
        r1.keepMedia = r3;
        getMessagesController().getCacheByChatsController().saveKeepMediaExceptions(this.currentType, this.exceptionsDialogs);
        AndroidUtilities.updateVisibleRows(this.recyclerListView);
    }

    private /* synthetic */ void lambda$showPopupFor$5(CacheByChatsController.KeepMediaException r7) {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r1 >= this.items.size()) goto L11;
        if (this.items.get(r1).exception == null) goto L10;
        if (this.items.get(r1).exception.dialogId != r7.dialogId) goto L10;
        r0 = r1;
    L10:
        r1 = r1 + 1;
    L11:
        RecyclerView.ViewHolder r02 = this.recyclerListView.findViewHolderForAdapterPosition(r0);
        if (r02 == null) goto L19;
        KeepMediaPopupView r12 = new KeepMediaPopupView(this, getContext());
        r12.updateForDialog(true);
        r12.setParentWindow(AlertsCreator.createSimplePopup(this, r12, r02.itemView, r2.getMeasuredWidth() / 2.0f, r02.itemView.getMeasuredHeight() / 2.0f));
        r12.setCallback(new CacheChatsExceptionsFragment$$ExternalSyntheticLambda5(this, r7));
        return;
    }

    private void updateRows() {
        boolean r1 = false;
        if (this.isPaused == false) goto L5;
    L7:
        boolean r0 = false;
    L9:
        if (r0 == false) goto L11;
        ArrayList<? extends AdapterWithDiffUtils.Item> r02 = new ArrayList();
        r02.addAll(this.items);
    L12:
        this.items.clear();
        this.items.add(new Item(this, 1, null, null));
        ArrayList<CacheByChatsController.KeepMediaException> r4 = this.exceptionsDialogs;
        int r5 = r4.size();
        int r6 = 0;
    L13:
        if (r6 >= r5) goto L16;
        CacheByChatsController.KeepMediaException r12 = r4.get(r6);
        r6 = r6 + 1;
        this.items.add(new Item(this, 2, r12, null));
        r1 = true;
        goto L13
    L16:
        if (r1 == false) goto L18;
        this.items.add(new Item(this, 3, null, null));
        this.items.add(new Item(this, 4, null, null));
    L18:
        this.items.add(new Item(this, 3, null, null));
        Adapter r13 = this.adapter;
        if (r13 == null) goto L26;
        if (r02 == null) goto L23;
        r13.setItems(r02, this.items);
        return;
    L23:
        r13.notifyDataSetChanged();
        return;
    L26:
        return;
    L11:
        r02 = null;
        goto L12
    L5:
        if (this.adapter == null) goto L7;
        r0 = true;
        goto L9
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r5) {
        FrameLayout r0 = new FrameLayout(r5);
        this.fragmentView = r0;
        this.actionBar.setBackButtonDrawable(new BackDrawable(false));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        this.actionBar.setTitle(LocaleController.getString(R.string.NotificationsExceptions));
        this.recyclerListView = new RecyclerListView(r5);
        DefaultItemAnimator r1 = new DefaultItemAnimator();
        r1.setDelayAnimations(false);
        r1.setSupportsChangeAnimations(false);
        this.recyclerListView.setItemAnimator(r1);
        this.recyclerListView.setLayoutManager(new LinearLayoutManager(r5));
        RecyclerListView r52 = this.recyclerListView;
        Adapter r12 = new Adapter(this, null);
        this.adapter = r12;
        r52.setAdapter(r12);
        this.recyclerListView.setOnItemClickListener(new CacheChatsExceptionsFragment$$ExternalSyntheticLambda0(this));
        r0.addView(this.recyclerListView);
        r0.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        updateRows();
        return this.fragmentView;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        this.currentType = getArguments().getInt("type");
        updateRows();
        return super.onFragmentCreate();
    }

    public void setExceptions(ArrayList<CacheByChatsController.KeepMediaException> r1) {
        this.exceptionsDialogs = r1;
        updateRows();
    }

    public void showPopupFor(CacheByChatsController.KeepMediaException r2) {
        AndroidUtilities.runOnUIThread(new CacheChatsExceptionsFragment$$ExternalSyntheticLambda1(this, r2), 150);
    }
}
