package org.telegram.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public abstract class ChatActivityContainer extends FrameLayout {
    public final ChatActivity chatActivity;
    private View fragmentView;
    private boolean isActive;
    private final INavigationLayout parentLayout;
    private int topPadding;

    /* renamed from: org.telegram.ui.ChatActivityContainer$1, reason: invalid class name */
    public class AnonymousClass1 extends ChatActivity {
        final /* synthetic */ ChatActivityContainer this$0;

        public AnonymousClass1(ChatActivityContainer r1, Bundle r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.ChatActivity
        public void onSearchLoadingUpdate(boolean r1) {
            this.this$0.onSearchLoadingUpdate(r1);
        }

        @Override // org.telegram.ui.ChatActivity, org.telegram.ui.ActionBar.BaseFragment
        public void setNavigationBarColor(int r1) {
        }
    }

    public ChatActivityContainer(Context r1, INavigationLayout r2, Bundle r3) {
        super(r1);
        this.isActive = true;
        this.parentLayout = r2;
        AnonymousClass1 r22 = new AnonymousClass1(this, r3);
        this.chatActivity = r22;
        r22.isInsideContainer = true;
    }

    public void initChatActivity() {
        if (this.chatActivity.onFragmentCreate() == false) goto L21;
        ChatActivity r0 = this.chatActivity;
        this.fragmentView = r0.fragmentView;
        r0.setParentLayout(this.parentLayout);
        View r02 = this.fragmentView;
        if (r02 != null) goto L8;
        this.fragmentView = this.chatActivity.createView(getContext());
    L12:
        if (this.chatActivity.getChatListView() != null) goto L14;
    L16:
        this.chatActivity.openedInstantly();
        addView(this.fragmentView, LayoutHelper.createFrame(-1, -1.0f));
        if (this.isActive == false) goto L20;
        this.chatActivity.onResume();
        return;
    L20:
        return;
    L14:
        if (this.topPadding == 0) goto L16;
        this.chatActivity.getChatListView().setPadding(0, this.topPadding, 0, 0);
        goto L16
    L8:
        ViewGroup r03 = (ViewGroup) r02.getParent();
        if (r03 == null) goto L12;
        this.chatActivity.onRemoveFromParent();
        r03.removeView(this.fragmentView);
        goto L12
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        initChatActivity();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void onPause() {
        if (this.isActive == false) goto L10;
        this.isActive = false;
        if (this.fragmentView == null) goto L9;
        this.chatActivity.onPause();
        return;
    L9:
        return;
    }

    public void onResume() {
        if (this.isActive == true) goto L10;
        this.isActive = true;
        if (this.fragmentView == null) goto L9;
        this.chatActivity.onResume();
        return;
    L9:
        return;
    }

    public void onSearchLoadingUpdate(boolean r1) {
    }

    public void setTopPadding(int r1) {
        this.topPadding = r1;
    }
}
