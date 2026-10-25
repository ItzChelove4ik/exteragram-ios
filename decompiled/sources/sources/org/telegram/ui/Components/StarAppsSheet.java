package org.telegram.ui.Components;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public class StarAppsSheet extends BottomSheetWithRecyclerListView {
    private DialogsBotsAdapter adapter;

    /* renamed from: org.telegram.ui.Components.StarAppsSheet$1, reason: invalid class name */
    public class AnonymousClass1 extends RecyclerView.OnScrollListener {
        final /* synthetic */ StarAppsSheet this$0;

        public AnonymousClass1(StarAppsSheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            StarAppsSheet.m13045$$Nest$fgetadapter(this.this$0).checkBottom();
        }
    }

    /* renamed from: $r8$lambda$zN8lH2owgjCNdn3-OssAQR2KxQ0, reason: not valid java name */
    public static /* synthetic */ void m13044$r8$lambda$zN8lH2owgjCNdn3OssAQR2KxQ0(StarAppsSheet r0, View r1, int r2) {
        r0.lambda$new$0(r1, r2);
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DialogsBotsAdapter m13045$$Nest$fgetadapter(StarAppsSheet r0) {
        return r0.adapter;
    }

    public StarAppsSheet(Context r8) {
        super(r8, null, true, false, false, null);
        fixNavigationBar();
        this.handleOffset = true;
        setShowHandle(true);
        setSlidingActionBar();
        RecyclerListView r7 = this.recyclerListView;
        int r82 = this.backgroundPaddingLeft;
        r7.setPadding(r82, 0, r82, 0);
        this.recyclerListView.addOnScrollListener(new AnonymousClass1(this));
        this.recyclerListView.setOnItemClickListener(new StarAppsSheet$$ExternalSyntheticLambda0(this));
    }

    private /* synthetic */ void lambda$new$0(View r7, int r8) {
        Object r72 = this.adapter.getObject(r8 - 1);
        if ((r72 instanceof TLRPC.User) == false) goto L6;
        MessagesController.getInstance(this.currentAccount).openApp(this.attachedFragment, (TLRPC.User) r72, null, 0, null);
        return;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r8) {
        DialogsBotsAdapter r0 = new DialogsBotsAdapter(r8, getContext(), this.currentAccount, 0, true, this.resourcesProvider);
        this.adapter = r0;
        r0.setApplyBackground(false);
        return this.adapter;
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return LocaleController.getString(R.string.SearchAppsExamples);
    }
}
