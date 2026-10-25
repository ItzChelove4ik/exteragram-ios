package org.telegram.ui.Components;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Delegates.MemberRequestsDelegate;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes7.dex */
public abstract class MemberRequestsBottomSheet extends UsersAlertBase {
    private final FlickerLoadingView currentLoadingView;
    private final MemberRequestsDelegate delegate;
    private boolean enterEventSent;
    private final StickerEmptyView membersEmptyView;
    private final StickerEmptyView membersSearchEmptyView;
    private final int touchSlop;
    private float yOffset;

    /* renamed from: org.telegram.ui.Components.MemberRequestsBottomSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends MemberRequestsDelegate {
        final /* synthetic */ MemberRequestsBottomSheet this$0;

        public AnonymousClass1(MemberRequestsBottomSheet r1, BaseFragment r2, FrameLayout r3, long r4, boolean r6) {
            this.this$0 = r1;
            super(r2, r3, r4, r6);
        }

        @Override // org.telegram.ui.Delegates.MemberRequestsDelegate
        public void onImportersChanged(String r2, boolean r3, boolean r4) {
            if (hasAllImporters() == false) goto L5;
            if (r4 == false) goto L11;
            this.this$0.searchView.searchEditText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
            return;
        L11:
            super.onImportersChanged(r2, r3, r4);
            return;
        L5:
            if (MemberRequestsBottomSheet.m11237$$Nest$fgetmembersEmptyView(this.this$0).getVisibility() == 4) goto L13;
            MemberRequestsBottomSheet.m11237$$Nest$fgetmembersEmptyView(this.this$0).setVisibility(4);
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$UlnoPE_LJB_OiVZJOu0Yxyw9osg(MemberRequestsBottomSheet r0, EditTextBoldCursor r1) {
        r0.lambda$onSearchViewTouched$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$ojMLstStjkMnaYMTpYum2SOV2xw(EditTextBoldCursor r0) {
        AndroidUtilities.showKeyboard(r0);
    }

    public static /* synthetic */ void $r8$lambda$rmzqwOSo9uMYgelfGPiRj8jsyRg(EditTextBoldCursor r0) {
        AndroidUtilities.showKeyboard(r0);
    }

    /* renamed from: -$$Nest$fgetmembersEmptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StickerEmptyView m11237$$Nest$fgetmembersEmptyView(MemberRequestsBottomSheet r0) {
        return r0.membersEmptyView;
    }

    public MemberRequestsBottomSheet(BaseFragment r12, long r13) {
        super(r12.getParentActivity(), false, r12.getCurrentAccount(), r12.getResourceProvider());
        this.touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        int r0 = Theme.key_windowBackgroundGray;
        setBackgroundColor(getThemedColor(r0));
        this.keyListViewBackground = r0;
        this.keyInviteMembersBackground = r0;
        setColorProgress(0.0f);
        fixNavigationBar(getThemedColor(r0));
        this.needSnapToTop = false;
        this.isEmptyViewVisible = false;
        AnonymousClass1 r4 = new AnonymousClass1(this, r12, this.container, r13, false);
        this.delegate = r4;
        r4.setShowLastItemDivider(false);
        setDimBehindAlpha(75);
        this.searchView.searchEditText.setHint(LocaleController.getString(R.string.SearchMemberRequests));
        MemberRequestsDelegate.Adapter r11 = r4.getAdapter();
        this.listViewAdapter = r11;
        this.searchListViewAdapter = r11;
        this.listView.setAdapter(r11);
        this.listView.setSections();
        r4.setRecyclerView(this.listView);
        int r112 = ((ViewGroup) this.listView.getParent()).indexOfChild(this.listView);
        FlickerLoadingView r122 = r4.getLoadingView();
        this.currentLoadingView = r122;
        this.containerView.addView(r122, r112, LayoutHelper.createFrame(-1, -1.0f));
        StickerEmptyView r123 = r4.getEmptyView();
        this.membersEmptyView = r123;
        this.containerView.addView(r123, r112, LayoutHelper.createFrame(-1, -1.0f));
        StickerEmptyView r124 = r4.getSearchEmptyView();
        this.membersSearchEmptyView = r124;
        this.containerView.addView(r124, r112, LayoutHelper.createFrame(-1, -1.0f));
        r4.loadMembers();
    }

    private /* synthetic */ void lambda$onSearchViewTouched$1(EditTextBoldCursor r2) {
        setFocusable(true);
        r2.requestFocus();
        AndroidUtilities.runOnUIThread(new MemberRequestsBottomSheet$$ExternalSyntheticLambda2(r2));
    }

    public boolean isNeedRestoreDialog() {
        return this.delegate.isNeedRestoreList;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void onBackPressed() {
        if (this.delegate.onBackPressed(true) == false) goto L6;
        super.onBackPressed();
        return;
    }

    @Override // org.telegram.ui.Components.UsersAlertBase
    public void onSearchViewTouched(MotionEvent r6, EditTextBoldCursor r7) {
        if (r6.getAction() != 0) goto L6;
        this.yOffset = this.scrollOffsetY;
        this.delegate.setAdapterItemsEnabled(false);
    L24:
        if (r6.getAction() != 1) goto L26;
    L29:
        this.delegate.setAdapterItemsEnabled(true);
        return;
    L26:
        if (r6.getAction() == 3) goto L29;
        return;
    L6:
        if (r6.getAction() != 1) goto L24;
        if (Math.abs(this.scrollOffsetY - this.yOffset) >= this.touchSlop) goto L24;
        if (this.enterEventSent == true) goto L24;
        Activity r0 = AndroidUtilities.findActivity(getContext());
        if ((r0 instanceof LaunchActivity) == false) goto L14;
        LaunchActivity r02 = (LaunchActivity) r0;
        BaseFragment r03 = r02.getActionBarLayout().getFragmentStack().get(r02.getActionBarLayout().getFragmentStack().size() - 1);
    L16:
        if ((r03 instanceof ChatActivity) == false) goto L22;
        boolean r04 = ((ChatActivity) r03).needEnterText();
        this.enterEventSent = true;
        MemberRequestsBottomSheet$$ExternalSyntheticLambda0 r2 = new MemberRequestsBottomSheet$$ExternalSyntheticLambda0(this, r7);
        if (r04 == false) goto L20;
        long r3 = 200;
    L21:
        AndroidUtilities.runOnUIThread(r2, r3);
        goto L24
    L20:
        r3 = 0;
        goto L21
    L22:
        this.enterEventSent = true;
        setFocusable(true);
        r7.requestFocus();
        AndroidUtilities.runOnUIThread(new MemberRequestsBottomSheet$$ExternalSyntheticLambda1(r7));
        goto L24
    L14:
        r03 = null;
        goto L16
    }

    @Override // org.telegram.ui.Components.UsersAlertBase
    public void search(String r1) {
        super.search(r1);
        this.delegate.setQuery(r1);
    }

    @Override // org.telegram.ui.Components.UsersAlertBase
    public void setTranslationY(int r3) {
        super.setTranslationY(r3);
        this.currentLoadingView.setTranslationY(this.frameLayout.getMeasuredHeight() + r3);
        float r32 = r3;
        this.membersEmptyView.setTranslationY(r32);
        this.membersSearchEmptyView.setTranslationY(r32);
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void show() {
        if (this.delegate.isNeedRestoreList == true) goto L5;
    L7:
        super.show();
        this.delegate.isNeedRestoreList = false;
        return;
    L5:
        if (this.scrollOffsetY != 0) goto L7;
        this.scrollOffsetY = AndroidUtilities.dp(8.0f);
        goto L7
    }

    @Override // org.telegram.ui.Components.UsersAlertBase
    public void updateLayout() {
        if (this.listView.getChildCount() <= 0) goto L5;
        super.updateLayout();
        return;
    L5:
        if (this.listView.getVisibility() != 0) goto L7;
        int r0 = this.listView.getPaddingTop() - AndroidUtilities.dp(8.0f);
    L9:
        if (this.scrollOffsetY == r0) goto L14;
        this.scrollOffsetY = r0;
        setTranslationY(r0);
        return;
    L14:
        return;
    L7:
        r0 = 0;
        goto L9
    }
}
