package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public class ChatAttachRestrictedLayout extends ChatAttachAlert.AttachAlertLayout {
    private final RecyclerView.Adapter adapter;
    private int gridExtraSpace;
    public final int id;
    private final RecyclerListView listView;
    private final EmptyTextProgressView progressView;

    /* renamed from: org.telegram.ui.Components.ChatAttachRestrictedLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends RecyclerView.Adapter {
        final /* synthetic */ ChatAttachRestrictedLayout this$0;

        /* renamed from: org.telegram.ui.Components.ChatAttachRestrictedLayout$1$1, reason: invalid class name and collision with other inner class name */
        public class C00481 extends View {
            final /* synthetic */ AnonymousClass1 this$1;

            public C00481(AnonymousClass1 r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(ChatAttachRestrictedLayout.m9937$$Nest$fgetgridExtraSpace(this.this$1.this$0), TLObject.FLAG_30));
            }
        }

        public AnonymousClass1(ChatAttachRestrictedLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r1, int r2) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
            return new RecyclerListView.Holder(new C00481(this, this.this$0.getContext()));
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachRestrictedLayout$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ChatAttachRestrictedLayout this$0;

        public AnonymousClass2(ChatAttachRestrictedLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            ChatAttachRestrictedLayout r0 = this.this$0;
            r0.parentAlert.updateLayout(r0, true, r3);
        }
    }

    /* renamed from: -$$Nest$fgetgridExtraSpace, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9937$$Nest$fgetgridExtraSpace(ChatAttachRestrictedLayout r0) {
        return r0.gridExtraSpace;
    }

    public ChatAttachRestrictedLayout(int r4, ChatAttachAlert r5, Context r6, Theme.ResourcesProvider r7) {
        super(r5, r6, r7);
        this.id = r4;
        EmptyTextProgressView r52 = new EmptyTextProgressView(r6, null, r7);
        this.progressView = r52;
        r52.setText(LocaleController.getString(R.string.NoPhotos));
        r52.setOnTouchListener(null);
        r52.setTextSize(16);
        addView(r52, LayoutHelper.createFrame(-1, -2.0f));
        r52.setLottie(R.raw.media_forbidden, 150, 150);
        TLRPC.Chat r0 = this.parentAlert.getChat();
        if (r4 != 1) goto L6;
        r52.setText(ChatObject.getRestrictedErrorText(r0, 7));
    L12:
        r52.showTextView();
        RecyclerListView r42 = new RecyclerListView(r6, r7);
        this.listView = r42;
        r42.setSectionsType(2);
        r42.setVerticalScrollBarEnabled(false);
        r42.setLayoutManager(new LinearLayoutManager(r6));
        r42.setClipToPadding(false);
        AnonymousClass1 r62 = new AnonymousClass1(this);
        this.adapter = r62;
        r42.setAdapter(r62);
        r42.setPadding(0, 0, 0, AndroidUtilities.dp(48.0f));
        r42.setOnScrollListener(new AnonymousClass2(this));
        addView(r42, LayoutHelper.createFrame(-1, -1.0f));
        return;
    L6:
        if (r4 != 3) goto L9;
        r52.setText(ChatObject.getRestrictedErrorText(r0, 18));
        goto L12
    L9:
        if (r4 != 4) goto L11;
        r52.setText(ChatObject.getRestrictedErrorText(r0, 19));
        goto L12
    L11:
        r52.setText(ChatObject.getRestrictedErrorText(r0, 22));
        goto L12
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getCurrentItemTop() {
        if (this.listView.getChildCount() > 0) goto L6;
        return Integer.MAX_VALUE;
    L6:
        int r1 = 0;
        View r0 = this.listView.getChildAt(0);
        RecyclerListView.Holder r2 = (RecyclerListView.Holder) this.listView.findContainingViewHolder(r0);
        int r02 = r0.getTop() - AndroidUtilities.dp(8.0f);
        if (r02 <= 0) goto L12;
        if (r2 == null) goto L12;
        if (r2.getAdapterPosition() != 0) goto L12;
        r1 = r02;
    L12:
        if (r02 < 0) goto L17;
        if (r2 == null) goto L17;
        if (r2.getAdapterPosition() != 0) goto L17;
    L18:
        this.progressView.setTranslationY(((((getMeasuredHeight() - r02) - AndroidUtilities.dp(50.0f)) - this.progressView.getMeasuredHeight()) / 2) + r02);
        return r02 + AndroidUtilities.dp(12.0f);
    L17:
        r02 = r1;
        goto L18
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getFirstOffset() {
        return getListTopPadding() + AndroidUtilities.dp(4.0f);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getListTopPadding() {
        return this.listView.getPaddingTop();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onPreMeasure(int r3, int r4) {
        super.onPreMeasure(r3, r4);
        int r0 = 0;
        int r32 = Math.max(0, r4 - ActionBar.getCurrentActionBarHeight());
        if (this.gridExtraSpace == r32) goto L6;
        this.gridExtraSpace = r32;
        this.adapter.notifyDataSetChanged();
    L6:
        if (AndroidUtilities.isTablet() == true) goto L10;
        Point r33 = AndroidUtilities.displaySize;
        if (r33.x <= r33.y) goto L10;
        int r34 = (int) (r4 / 3.5f);
    L11:
        int r35 = r34 - AndroidUtilities.dp(52.0f);
        if (r35 < 0) goto L16;
        r0 = r35;
    L16:
        if (this.listView.getPaddingTop() == r0) goto L19;
        this.listView.setPadding(AndroidUtilities.dp(6.0f), r0, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(48.0f));
        return;
    L19:
        return;
    L10:
        r34 = (r4 / 5) * 2;
        goto L11
    }

    @Override // android.view.View
    public void setTranslationY(float r1) {
        super.setTranslationY(r1);
        this.parentAlert.getSheetContainer().invalidate();
    }
}
