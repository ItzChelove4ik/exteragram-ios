package org.telegram.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TopicExceptionCell;
import org.telegram.ui.Components.ListView.AdapterWithDiffUtils;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.NotificationsSettingsActivity;
import org.telegram.ui.ProfileNotificationsActivity;

/* loaded from: classes6.dex */
public class TopicsNotifySettingsFragments extends BaseFragment {
    private final int VIEW_TYPE_ADD_EXCEPTION;
    private final int VIEW_TYPE_DELETE_ALL;
    private final int VIEW_TYPE_DIVIDER;
    private final int VIEW_TYPE_TOPIC;
    Adapter adapter;
    long dialogId;
    HashSet<Integer> exceptionsTopics;
    ArrayList<Item> items;
    RecyclerListView recyclerListView;

    /* renamed from: org.telegram.ui.TopicsNotifySettingsFragments$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ TopicsNotifySettingsFragments this$0;

        public AnonymousClass1(TopicsNotifySettingsFragments r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.TopicsNotifySettingsFragments$2, reason: invalid class name */
    public class AnonymousClass2 implements RecyclerListView.OnItemClickListener {
        final /* synthetic */ TopicsNotifySettingsFragments this$0;

        /* renamed from: org.telegram.ui.TopicsNotifySettingsFragments$2$1, reason: invalid class name */
        public class AnonymousClass1 implements ProfileNotificationsActivity.ProfileNotificationsActivityDelegate {
            final /* synthetic */ AnonymousClass2 this$1;
            final /* synthetic */ TLRPC.TL_forumTopic val$topic;

            public static /* synthetic */ void $r8$lambda$TG35sGy66fZKcKN_HhfX528PyI0(AnonymousClass1 r0, TLRPC.TL_forumTopic r1) {
                r0.lambda$didRemoveException$0(r1);
            }

            public AnonymousClass1(AnonymousClass2 r1, TLRPC.TL_forumTopic r2) {
                this.this$1 = r1;
                this.val$topic = r2;
            }

            private /* synthetic */ void lambda$didRemoveException$0(TLRPC.TL_forumTopic r2) {
                this.this$1.this$0.exceptionsTopics.remove(Integer.valueOf(r2.id));
                TopicsNotifySettingsFragments.m21010$$Nest$mupdateRows(this.this$1.this$0);
            }

            @Override // org.telegram.ui.ProfileNotificationsActivity.ProfileNotificationsActivityDelegate
            public void didCreateNewException(NotificationsSettingsActivity.NotificationException r1) {
            }

            @Override // org.telegram.ui.ProfileNotificationsActivity.ProfileNotificationsActivityDelegate
            public void didRemoveException(long r1) {
                TopicsNotifySettingsFragments.m21009$$Nest$mremoveException(this.this$1.this$0, this.val$topic.id);
                AndroidUtilities.runOnUIThread(new TopicsNotifySettingsFragments$2$1$$ExternalSyntheticLambda0(this, this.val$topic), 300);
            }
        }

        public static /* synthetic */ void $r8$lambda$K7UE47qjd34qmlvn4yniUkjoXKk(AnonymousClass2 r0, AlertDialog r1, int r2) {
            r0.lambda$onItemClick$2(r1, r2);
        }

        /* renamed from: $r8$lambda$YRzvGIyjDhwky2f6aPOst0R83-8, reason: not valid java name */
        public static /* synthetic */ void m21011$r8$lambda$YRzvGIyjDhwky2f6aPOst0R838(AnonymousClass2 r0, TLRPC.TL_forumTopic r1, NotificationsSettingsActivity.NotificationException r2) {
            r0.lambda$onItemClick$0(r1, r2);
        }

        /* renamed from: $r8$lambda$bc4Wn8UW08VjLb6_P7oMR-YWOLw, reason: not valid java name */
        public static /* synthetic */ void m21012$r8$lambda$bc4Wn8UW08VjLb6_P7oMRYWOLw(AnonymousClass2 r0, TLRPC.TL_forumTopic r1) {
            r0.lambda$onItemClick$1(r1);
        }

        public AnonymousClass2(TopicsNotifySettingsFragments r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onItemClick$0(TLRPC.TL_forumTopic r1, NotificationsSettingsActivity.NotificationException r2) {
            this.this$0.exceptionsTopics.add(Integer.valueOf(r1.id));
            TopicsNotifySettingsFragments.m21010$$Nest$mupdateRows(this.this$0);
        }

        private /* synthetic */ void lambda$onItemClick$1(TLRPC.TL_forumTopic r5) {
            Bundle r0 = new Bundle();
            r0.putLong("dialog_id", this.this$0.dialogId);
            r0.putLong("topic_id", r5.id);
            r0.putBoolean("exception", true);
            ProfileNotificationsActivity r1 = new ProfileNotificationsActivity(r0);
            r1.setDelegate(new TopicsNotifySettingsFragments$2$$ExternalSyntheticLambda2(this, r5));
            this.this$0.presentFragment(r1);
        }

        private /* synthetic */ void lambda$onItemClick$2(AlertDialog r2, int r3) {
            Iterator<Integer> r22 = this.this$0.exceptionsTopics.iterator();
        L4:
            if (r22.hasNext() == false) goto L6;
            int r32 = r22.next().intValue();
            TopicsNotifySettingsFragments.m21009$$Nest$mremoveException(this.this$0, r32);
            goto L4
        L6:
            this.this$0.exceptionsTopics.clear();
            TopicsNotifySettingsFragments.m21010$$Nest$mupdateRows(this.this$0);
        }

        @Override // org.telegram.ui.Components.RecyclerListView.OnItemClickListener
        public void onItemClick(View r5, int r6) {
            if (this.this$0.items.get(r6).viewType != 1) goto L6;
            Bundle r52 = new Bundle();
            r52.putLong("chat_id", -this.this$0.dialogId);
            r52.putBoolean("for_select", true);
            TopicsFragment r0 = new TopicsFragment(r52);
            r0.setExcludeTopics(this.this$0.exceptionsTopics);
            r0.setOnTopicSelectedListener(new TopicsNotifySettingsFragments$2$$ExternalSyntheticLambda0(this));
            this.this$0.presentFragment(r0);
        L6:
            if (this.this$0.items.get(r6).viewType != 2) goto L9;
            TLRPC.TL_forumTopic r53 = this.this$0.items.get(r6).topic;
            Bundle r02 = new Bundle();
            r02.putLong("dialog_id", this.this$0.dialogId);
            r02.putLong("topic_id", r53.id);
            r02.putBoolean("exception", false);
            ProfileNotificationsActivity r1 = new ProfileNotificationsActivity(r02);
            r1.setDelegate(new AnonymousClass1(this, r53));
            this.this$0.presentFragment(r1);
        L9:
            if (this.this$0.items.get(r6).viewType != 4) goto L14;
            AlertDialog.Builder r54 = new AlertDialog.Builder(this.this$0.getParentActivity());
            r54.setTitle(LocaleController.getString(R.string.NotificationsDeleteAllExceptionTitle));
            r54.setMessage(LocaleController.getString(R.string.NotificationsDeleteAllExceptionAlert));
            r54.setPositiveButton(LocaleController.getString(R.string.Delete), new TopicsNotifySettingsFragments$2$$ExternalSyntheticLambda1(this));
            r54.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
            AlertDialog r55 = r54.create();
            this.this$0.showDialog(r55);
            TextView r4 = (TextView) r55.getButton(-1);
            if (r4 == null) goto L15;
            r4.setTextColor(Theme.getColor(Theme.key_text_RedBold));
            return;
        L15:
            return;
        }
    }

    public class Adapter extends AdapterWithDiffUtils {
        final /* synthetic */ TopicsNotifySettingsFragments this$0;

        private Adapter(TopicsNotifySettingsFragments r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Adapter(TopicsNotifySettingsFragments r1, TopicsNotifySettingsFragmentsIA r2) {
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
        public void onBindViewHolder(RecyclerView.ViewHolder r5, int r6) {
            if (this.this$0.items.get(r6).viewType != 2) goto L12;
            TopicExceptionCell r52 = (TopicExceptionCell) r5.itemView;
            TopicsNotifySettingsFragments r0 = this.this$0;
            r52.setTopic(r0.dialogId, r0.items.get(r6).topic);
            boolean r2 = true;
            if (r6 != (this.this$0.items.size() - 1)) goto L7;
        L10:
            r52.drawDivider = r2;
            return;
        L7:
            if (this.this$0.items.get(r6 + 1).viewType == 2) goto L10;
            r2 = false;
            goto L10
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            if (r4 != 1) goto L5;
            TextCell r42 = new TextCell(r3.getContext());
            r42.setTextAndIcon(LocaleController.getString(R.string.NotificationsAddAnException), R.drawable.msg_contact_add, true);
            r42.setColors(Theme.key_windowBackgroundWhiteBlueIcon, Theme.key_windowBackgroundWhiteBlueButton);
            r42.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            View r43 = r42;
        L12:
            View r32 = r43;
        L16:
            r32.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r32);
        L5:
            if (r4 != 2) goto L7;
            View r44 = new TopicExceptionCell(r3.getContext());
            r44.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            r43 = r44;
            goto L12
        L7:
            if (r4 != 3) goto L9;
            r43 = new ShadowSectionCell(r3.getContext());
            goto L12
        L9:
            if (r4 == 4) goto L11;
            r32 = null;
            goto L16
        L11:
            TextCell r45 = new TextCell(r3.getContext());
            r45.setText(LocaleController.getString(R.string.NotificationsDeleteAllException), false);
            r45.setColors(-1, Theme.key_text_RedRegular);
            r45.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            r43 = r45;
            goto L12
        }
    }

    public class Item extends AdapterWithDiffUtils.Item {
        final /* synthetic */ TopicsNotifySettingsFragments this$0;
        final TLRPC.TL_forumTopic topic;

        private Item(TopicsNotifySettingsFragments r1, int r2, TLRPC.TL_forumTopic r3) {
            this.this$0 = r1;
            super(r2, false);
            this.topic = r3;
        }

        public /* synthetic */ Item(TopicsNotifySettingsFragments r1, int r2, TLRPC.TL_forumTopic r3, TopicsNotifySettingsFragmentsIA r4) {
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
            TLRPC.TL_forumTopic r4 = this.topic;
            if (r4 == null) goto L21;
            TLRPC.TL_forumTopic r53 = r52.topic;
            if (r53 == null) goto L21;
            if (r4.id != r53.id) goto L20;
            return true;
        L20:
            return false;
        L21:
            return true;
        }
    }

    /* renamed from: $r8$lambda$c3dNfmvd9-QA3F1Dmf28JyLaDVg, reason: not valid java name */
    public static /* synthetic */ void m21008$r8$lambda$c3dNfmvd9QA3F1Dmf28JyLaDVg(TLObject r0, TLRPC.TL_error r1) {
    }

    /* renamed from: -$$Nest$mremoveException, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21009$$Nest$mremoveException(TopicsNotifySettingsFragments r0, int r1) {
        r0.removeException(r1);
    }

    /* renamed from: -$$Nest$mupdateRows, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21010$$Nest$mupdateRows(TopicsNotifySettingsFragments r0) {
        r0.updateRows();
    }

    public TopicsNotifySettingsFragments(Bundle r1) {
        super(r1);
        this.VIEW_TYPE_ADD_EXCEPTION = 1;
        this.VIEW_TYPE_TOPIC = 2;
        this.VIEW_TYPE_DIVIDER = 3;
        this.VIEW_TYPE_DELETE_ALL = 4;
        this.items = new ArrayList();
        this.exceptionsTopics = new HashSet();
    }

    private void removeException(int r6) {
        getNotificationsController().getNotificationsSettingsFacade().clearPreference(this.dialogId, r6);
        TL_account.updateNotifySettings r0 = new TL_account.updateNotifySettings();
        r0.settings = new TLRPC.TL_inputPeerNotifySettings();
        TLRPC.TL_inputNotifyForumTopic r1 = new TLRPC.TL_inputNotifyForumTopic();
        r1.peer = getMessagesController().getInputPeer(this.dialogId);
        r1.top_msg_id = r6;
        r0.peer = r1;
        getConnectionsManager().sendRequest(r0, new TopicsNotifySettingsFragments$$ExternalSyntheticLambda0());
    }

    private void updateRows() {
        int r1 = 0;
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
        ArrayList<TLRPC.TL_forumTopic> r4 = getMessagesController().getTopicsController().getTopics(-this.dialogId);
        if (r4 == null) goto L23;
        int r5 = 0;
    L16:
        if (r1 >= r4.size()) goto L21;
        if (this.exceptionsTopics.contains(Integer.valueOf(r4.get(r1).id)) == false) goto L20;
        this.items.add(new Item(this, 2, r4.get(r1), null));
        r5 = 1;
    L20:
        r1 = r1 + 1;
        goto L16
    L21:
        r1 = r5;
    L23:
        if (r1 == 0) goto L25;
        this.items.add(new Item(this, 3, null, null));
        this.items.add(new Item(this, 4, null, null));
    L25:
        this.items.add(new Item(this, 3, null, null));
        Adapter r12 = this.adapter;
        if (r12 == null) goto L32;
        r12.setItems(r02, this.items);
        return;
    L32:
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
        this.recyclerListView.setOnItemClickListener(new AnonymousClass2(this));
        r0.addView(this.recyclerListView);
        r0.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        return this.fragmentView;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        this.dialogId = this.arguments.getLong("dialog_id");
        updateRows();
        return super.onFragmentCreate();
    }

    public void setExceptions(HashSet<Integer> r1) {
        this.exceptionsTopics = r1;
    }
}
