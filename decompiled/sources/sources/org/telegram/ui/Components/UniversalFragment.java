package org.telegram.ui.Components;

import android.content.Context;
import android.view.View;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public abstract class UniversalFragment extends BaseFragment {
    public UniversalRecyclerView listView;
    private int savedScrollOffset;
    private int savedScrollPosition;

    /* renamed from: org.telegram.ui.Components.UniversalFragment$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ UniversalFragment this$0;

        public AnonymousClass1(UniversalFragment r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.UniversalFragment$2, reason: invalid class name */
    public class AnonymousClass2 extends SizeNotifierFrameLayout {
        final /* synthetic */ UniversalFragment this$0;

        public AnonymousClass2(UniversalFragment r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30));
        }
    }

    /* renamed from: org.telegram.ui.Components.UniversalFragment$3, reason: invalid class name */
    public class AnonymousClass3 extends UniversalRecyclerView {
        final /* synthetic */ UniversalFragment this$0;

        public AnonymousClass3(UniversalFragment r1, BaseFragment r2, Utilities.Callback2 r3, Utilities.Callback5 r4, Utilities.Callback5Return r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            UniversalFragment.m13479$$Nest$fputsavedScrollPosition(this.this$0, -1);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
        }
    }

    /* renamed from: -$$Nest$fputsavedScrollPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13479$$Nest$fputsavedScrollPosition(UniversalFragment r0, int r1) {
        r0.savedScrollPosition = r1;
    }

    public UniversalFragment() {
        this.savedScrollPosition = -1;
    }

    public void applyScrolledPosition() {
        int r0 = this.savedScrollPosition;
        if (r0 < 0) goto L6;
        UniversalRecyclerView r1 = this.listView;
        r1.layoutManager.scrollToPositionWithOffset(r0, this.savedScrollOffset - r1.getPaddingTop());
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r10) {
        this.actionBar.setBackButtonDrawable(new BackDrawable(false));
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(getTitle());
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        AnonymousClass2 r0 = new AnonymousClass2(this, r10);
        r0.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        AnonymousClass3 r3 = new AnonymousClass3(this, this, new UniversalFragment$$ExternalSyntheticLambda0(this), new UniversalFragment$$ExternalSyntheticLambda1(this), new UniversalFragment$$ExternalSyntheticLambda2(this));
        this.listView = r3;
        r3.setSections();
        this.listView.adapter.setApplyBackground(false);
        r0.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.fragmentView = r0;
        return r0;
    }

    public abstract void fillItems(ArrayList<UItem> r1, UniversalAdapter r2);

    public abstract CharSequence getTitle();

    public abstract void onClick(UItem r1, View r2, int r3, float r4, float r5);

    public abstract boolean onLongClick(UItem r1, View r2, int r3, float r4, float r5);

    public void saveScrollPosition() {
        UniversalRecyclerView r0 = this.listView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getChildCount() <= 0) goto L28;
        View r1 = null;
        int r2 = Integer.MAX_VALUE;
        int r3 = 0;
        int r4 = -1;
    L8:
        if (r3 >= this.listView.getChildCount()) goto L15;
        UniversalRecyclerView r5 = this.listView;
        int r52 = r5.getChildAdapterPosition(r5.getChildAt(r3));
        View r6 = this.listView.getChildAt(r3);
        if (r52 == (-1)) goto L14;
        if (r6.getTop() >= r2) goto L14;
        r2 = r6.getTop();
        r4 = r52;
        r1 = r6;
    L14:
        r3 = r3 + 1;
        goto L8
    L15:
        if (r1 == null) goto L29;
        this.savedScrollPosition = r4;
        int r02 = r1.getTop();
        this.savedScrollOffset = r02;
        if (this.savedScrollPosition == 0) goto L19;
    L21:
        this.listView.layoutManager.scrollToPositionWithOffset(r4, r1.getTop() - this.listView.getPaddingTop());
        return;
    L19:
        if (r02 <= AndroidUtilities.dp(88.0f)) goto L21;
        this.savedScrollOffset = AndroidUtilities.dp(88.0f);
        goto L21
    L29:
        return;
    }
}
