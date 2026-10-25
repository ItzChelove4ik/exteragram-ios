package org.telegram.ui;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Delegates.MemberRequestsDelegate;

/* loaded from: classes6.dex */
public class MemberRequestsActivity extends BaseFragment {
    private final MemberRequestsDelegate delegate;

    /* renamed from: org.telegram.ui.MemberRequestsActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends MemberRequestsDelegate {
        final /* synthetic */ MemberRequestsActivity this$0;

        public AnonymousClass1(MemberRequestsActivity r1, BaseFragment r2, FrameLayout r3, long r4, boolean r6) {
            this.this$0 = r1;
            super(r2, r3, r4, r6);
        }

        @Override // org.telegram.ui.Delegates.MemberRequestsDelegate
        public void onImportersChanged(String r1, boolean r2, boolean r3) {
            if (r3 == false) goto L5;
            MemberRequestsActivity.access$000(this.this$0).setSearchFieldText(_UrlKt.FRAGMENT_ENCODE_SET);
            return;
        L5:
            super.onImportersChanged(r1, r2, r3);
        }
    }

    /* renamed from: org.telegram.ui.MemberRequestsActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ MemberRequestsActivity this$0;

        public AnonymousClass2(MemberRequestsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.MemberRequestsActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        final /* synthetic */ MemberRequestsActivity this$0;

        public AnonymousClass3(MemberRequestsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchCollapse() {
            super.onSearchCollapse();
            MemberRequestsActivity.m16104$$Nest$fgetdelegate(this.this$0).setSearchExpanded(false);
            MemberRequestsActivity.m16104$$Nest$fgetdelegate(this.this$0).setQuery(null);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
            super.onSearchExpand();
            MemberRequestsActivity.m16104$$Nest$fgetdelegate(this.this$0).setSearchExpanded(true);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r1) {
            super.onTextChanged(r1);
            MemberRequestsActivity.m16104$$Nest$fgetdelegate(this.this$0).setQuery(r1.getText().toString());
        }
    }

    /* renamed from: -$$Nest$fgetdelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MemberRequestsDelegate m16104$$Nest$fgetdelegate(MemberRequestsActivity r0) {
        return r0.delegate;
    }

    public MemberRequestsActivity(long r8) {
        this.delegate = new AnonymousClass1(this, this, getLayoutContainer(), r8, true);
    }

    public static /* synthetic */ ActionBar access$000(MemberRequestsActivity r0) {
        return r0.actionBar;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r4) {
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass2(this));
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        ActionBar r42 = this.actionBar;
        if (this.delegate.isChannel == false) goto L6;
        int r1 = R.string.SubscribeRequests;
    L7:
        r42.setTitle(LocaleController.getString(r1));
        ActionBarMenuItem r43 = this.actionBar.createMenu().addItem(0, R.drawable.outline_header_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass3(this));
        r43.setSearchFieldHint(LocaleController.getString(R.string.Search));
        r43.setVisibility(8);
        FrameLayout r44 = this.delegate.getRootLayout();
        this.actionBar.setAdaptiveBackground(this.delegate.getRecyclerView(), false);
        this.delegate.loadMembers();
        this.fragmentView = r44;
        return r44;
    L6:
        r1 = R.string.MemberRequests;
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r1) {
        return this.delegate.onBackPressed(r1);
    }
}
