package org.telegram.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Cells.EditTextSettingsCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes6.dex */
public class QuickRepliesSettingsActivity extends BaseFragment {
    private int explanationRow;
    private ListAdapter listAdapter;
    private RecyclerListView listView;
    private int reply1Row;
    private int reply2Row;
    private int reply3Row;
    private int reply4Row;
    private int rowCount;
    private EditTextSettingsCell[] textCells;

    /* renamed from: org.telegram.ui.QuickRepliesSettingsActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ QuickRepliesSettingsActivity this$0;

        public AnonymousClass1(QuickRepliesSettingsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        final /* synthetic */ QuickRepliesSettingsActivity this$0;

        public ListAdapter(QuickRepliesSettingsActivity r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return QuickRepliesSettingsActivity.m18459$$Nest$fgetrowCount(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != QuickRepliesSettingsActivity.m18454$$Nest$fgetexplanationRow(this.this$0)) goto L7;
            return 0;
        L7:
            if (r2 == QuickRepliesSettingsActivity.m18455$$Nest$fgetreply1Row(this.this$0)) goto L18;
            if (r2 == QuickRepliesSettingsActivity.m18456$$Nest$fgetreply2Row(this.this$0)) goto L18;
            if (r2 == QuickRepliesSettingsActivity.m18457$$Nest$fgetreply3Row(this.this$0)) goto L18;
            if (r2 == QuickRepliesSettingsActivity.m18458$$Nest$fgetreply4Row(this.this$0)) goto L18;
            return 1;
        L18:
            return (r2 - QuickRepliesSettingsActivity.m18455$$Nest$fgetreply1Row(this.this$0)) + 9;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r2) {
            int r22 = r2.getAdapterPosition();
            if (r22 != QuickRepliesSettingsActivity.m18455$$Nest$fgetreply1Row(this.this$0)) goto L5;
            return true;
        L5:
            if (r22 != QuickRepliesSettingsActivity.m18456$$Nest$fgetreply2Row(this.this$0)) goto L7;
            return true;
        L7:
            if (r22 != QuickRepliesSettingsActivity.m18457$$Nest$fgetreply3Row(this.this$0)) goto L9;
            return true;
        L9:
            if (r22 == QuickRepliesSettingsActivity.m18458$$Nest$fgetreply4Row(this.this$0)) goto L17;
            return false;
        L17:
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r7, int r8) {
            int r0 = r7.getItemViewType();
            if (r0 == 0) goto L33;
            boolean r1 = true;
            if (r0 != 1) goto L7;
            TextSettingsCell r6 = (TextSettingsCell) r7.itemView;
            return;
        L7:
            if (r0 == 4) goto L29;
            switch(r0) {
                case 9: goto L10;
                case 10: goto L10;
                case 11: goto L10;
                case 12: goto L10;
                default: goto L9;
            };
        L9:
            return;
        L10:
            EditTextSettingsCell r72 = (EditTextSettingsCell) r7.itemView;
            if (r8 != QuickRepliesSettingsActivity.m18455$$Nest$fgetreply1Row(this.this$0)) goto L14;
            String r02 = LocaleController.getString(R.string.QuickReplyDefault1);
            String r2 = "quick_reply_msg1";
        L23:
            String r22 = this.this$0.getParentActivity().getSharedPreferences("mainconfig", 0).getString(r2, _UrlKt.FRAGMENT_ENCODE_SET);
            if (r8 != QuickRepliesSettingsActivity.m18458$$Nest$fgetreply4Row(this.this$0)) goto L27;
            r1 = false;
        L27:
            r72.setTextAndHint(r22, r02, r1);
            return;
        L14:
            if (r8 != QuickRepliesSettingsActivity.m18456$$Nest$fgetreply2Row(this.this$0)) goto L17;
            r02 = LocaleController.getString(R.string.QuickReplyDefault2);
            r2 = "quick_reply_msg2";
            goto L23
        L17:
            if (r8 != QuickRepliesSettingsActivity.m18457$$Nest$fgetreply3Row(this.this$0)) goto L20;
            r02 = LocaleController.getString(R.string.QuickReplyDefault3);
            r2 = "quick_reply_msg3";
            goto L23
        L20:
            if (r8 != QuickRepliesSettingsActivity.m18458$$Nest$fgetreply4Row(this.this$0)) goto L22;
            r02 = LocaleController.getString(R.string.QuickReplyDefault4);
            r2 = "quick_reply_msg4";
            goto L23
        L22:
            r02 = null;
            r2 = null;
            goto L23
        L29:
            ((TextCheckCell) r7.itemView).setTextAndCheck(LocaleController.getString(R.string.AllowCustomQuickReply), this.this$0.getParentActivity().getSharedPreferences("mainconfig", 0).getBoolean("quick_reply_allow_custom", true), false);
            return;
        L33:
            TextInfoPrivacyCell r73 = (TextInfoPrivacyCell) r7.itemView;
            r73.setBackgroundDrawable(Theme.getThemedDrawableByKey(this.mContext, R.drawable.greydivider_bottom, Theme.key_windowBackgroundGrayShadow));
            r73.setText(LocaleController.getString(R.string.VoipQuickRepliesExplain));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            if (r3 != 0) goto L4;
            View r22 = new TextInfoPrivacyCell(this.mContext);
        L10:
            r22.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r22);
        L4:
            if (r3 == 1) goto L8;
            switch(r3) {
                case 9: goto L7;
                case 10: goto L7;
                case 11: goto L7;
                case 12: goto L7;
                default: goto L6;
            };
        L6:
            r22 = new TextCheckCell(this.mContext);
            r22.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            goto L10
        L7:
            r22 = new EditTextSettingsCell(this.mContext);
            r22.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            QuickRepliesSettingsActivity.m18460$$Nest$fgettextCells(this.this$0)[r3 - 9] = r22;
            goto L10
        L8:
            r22 = new TextSettingsCell(this.mContext);
            r22.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
            goto L10
        }
    }

    /* renamed from: -$$Nest$fgetexplanationRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18454$$Nest$fgetexplanationRow(QuickRepliesSettingsActivity r0) {
        return r0.explanationRow;
    }

    /* renamed from: -$$Nest$fgetreply1Row, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18455$$Nest$fgetreply1Row(QuickRepliesSettingsActivity r0) {
        return r0.reply1Row;
    }

    /* renamed from: -$$Nest$fgetreply2Row, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18456$$Nest$fgetreply2Row(QuickRepliesSettingsActivity r0) {
        return r0.reply2Row;
    }

    /* renamed from: -$$Nest$fgetreply3Row, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18457$$Nest$fgetreply3Row(QuickRepliesSettingsActivity r0) {
        return r0.reply3Row;
    }

    /* renamed from: -$$Nest$fgetreply4Row, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18458$$Nest$fgetreply4Row(QuickRepliesSettingsActivity r0) {
        return r0.reply4Row;
    }

    /* renamed from: -$$Nest$fgetrowCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18459$$Nest$fgetrowCount(QuickRepliesSettingsActivity r0) {
        return r0.rowCount;
    }

    /* renamed from: -$$Nest$fgettextCells, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextSettingsCell[] m18460$$Nest$fgettextCells(QuickRepliesSettingsActivity r0) {
        return r0.textCells;
    }

    public QuickRepliesSettingsActivity() {
        this.textCells = new EditTextSettingsCell[4];
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r6) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setTitle(LocaleController.getString(R.string.VoipQuickReplies));
        if (AndroidUtilities.isTablet() == false) goto L5;
        this.actionBar.setOccupyStatusBar(false);
    L5:
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        this.listAdapter = new ListAdapter(this, r6);
        FrameLayout r0 = new FrameLayout(r6);
        this.fragmentView = r0;
        r0.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        FrameLayout r02 = (FrameLayout) this.fragmentView;
        RecyclerListView r3 = new RecyclerListView(r6);
        this.listView = r3;
        r3.setSections();
        this.listView.setVerticalScrollBarEnabled(false);
        this.listView.setLayoutManager(new LinearLayoutManager(r6, 1, false));
        r02.addView(this.listView, LayoutHelper.createFrame(-1, -1, 51));
        this.listView.setAdapter(this.listAdapter);
        this.actionBar.setAdaptiveBackground(this.listView);
        return this.fragmentView;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        ArrayList<ThemeDescription> r1 = new ArrayList();
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR, new Class[]{TextSettingsCell.class, TextCheckCell.class, EditTextSettingsCell.class}, null, null, null, Theme.key_windowBackgroundWhite));
        r1.add(new ThemeDescription(this.fragmentView, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundGray));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_LISTGLOWCOLOR, null, null, null, null, Theme.key_actionBarDefault));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_ITEMSCOLOR, null, null, null, null, Theme.key_actionBarDefaultIcon));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_TITLECOLOR, null, null, null, null, Theme.key_actionBarDefaultTitle));
        r1.add(new ThemeDescription(this.actionBar, ThemeDescription.FLAG_AB_SELECTORCOLOR, null, null, null, null, Theme.key_actionBarDefaultSelector));
        int r21 = Theme.key_windowBackgroundWhiteBlackText;
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{EditTextSettingsCell.class}, new String[]{"textView"}, null, null, null, r21));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_HINTTEXTCOLOR, new Class[]{EditTextSettingsCell.class}, new String[]{"textView"}, null, null, null, Theme.key_windowBackgroundWhiteHintText));
        r1.add(new ThemeDescription(this.listView, ThemeDescription.FLAG_SELECTOR, null, null, null, null, Theme.key_listSelector));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{View.class}, Theme.dividerPaint, null, null, Theme.key_divider));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"textView"}, null, null, null, r21));
        r1.add(new ThemeDescription(this.listView, 0, new Class[]{TextSettingsCell.class}, new String[]{"valueTextView"}, null, null, null, Theme.key_windowBackgroundWhiteValueText));
        return r1;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        super.onFragmentCreate();
        this.reply1Row = 0;
        int r1 = 1 + 1;
        this.reply2Row = 1;
        this.reply3Row = r1;
        int r3 = r1 + 2;
        this.reply4Row = r1 + 1;
        this.rowCount = r1 + 3;
        this.explanationRow = r3;
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        int r2 = 0;
        SharedPreferences.Editor r0 = getParentActivity().getSharedPreferences("mainconfig", 0).edit();
    L3:
        EditTextSettingsCell[] r1 = this.textCells;
        if (r2 >= r1.length) goto L12;
        EditTextSettingsCell r12 = r1[r2];
        if (r12 == null) goto L11;
        String r13 = r12.getTextView().getText().toString();
        if (TextUtils.isEmpty(r13) == true) goto L10;
        r0.putString("quick_reply_msg" + (r2 + 1), r13);
        goto L11
    L10:
        r0.remove("quick_reply_msg" + (r2 + 1));
    L11:
        r2 = r2 + 1;
        goto L3
    L12:
        r0.apply();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        ListAdapter r0 = this.listAdapter;
        if (r0 == null) goto L6;
        r0.notifyDataSetChanged();
        return;
    }
}
