package org.telegram.ui.Components;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public abstract class BottomSheetLayouted extends BottomSheetWithRecyclerListView {
    public ButtonWithCounterView button;
    public FrameLayout buttonContainer;
    public final LinearLayout layout;

    public class Adapter extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ BottomSheetLayouted this$0;

        public Adapter(BottomSheetLayouted r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return r1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r1, int r2) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
            return new RecyclerListView.Holder(this.this$0.layout);
        }
    }

    public static class SpaceView extends View {
        private int height;

        public SpaceView(Context r1) {
            super(r1);
            this.height = 0;
        }

        @Override // android.view.View
        public boolean dispatchTouchEvent(MotionEvent r1) {
            return false;
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(this.height, TLObject.FLAG_30));
        }

        public void setHeight(int r1, int r2) {
            if (this.height == r1) goto L6;
            this.height = r1;
            requestLayout();
            return;
        }
    }

    public BottomSheetLayouted(Context r8, Theme.ResourcesProvider r9) {
        super(r8, null, false, false, false, r9);
        LinearLayout r7 = new LinearLayout(r8);
        this.layout = r7;
        r7.setOrientation(1);
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public RecyclerListView.SelectionAdapter createAdapter(RecyclerListView r1) {
        return new Adapter(this);
    }

    public void createButton() {
        float r0 = this.backgroundPaddingLeft / AndroidUtilities.density;
        FrameLayout r1 = new FrameLayout(getContext());
        this.buttonContainer = r1;
        r1.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground, this.resourcesProvider));
        View r12 = new View(getContext());
        r12.setBackgroundColor(Theme.getColor(Theme.key_divider, this.resourcesProvider));
        this.buttonContainer.addView(r12, LayoutHelper.createFrame(-1.0f, 1.0f / AndroidUtilities.density, 55));
        ButtonWithCounterView r13 = new ButtonWithCounterView(getContext(), this.resourcesProvider);
        this.button = r13;
        float r7 = r0 + 16.0f;
        this.buttonContainer.addView(r13, LayoutHelper.createFrame(-1, 48.0f, 119, r7, 16.0f, r7, 16.0f));
        this.containerView.addView(this.buttonContainer, LayoutHelper.createFrame(-1, -2, 87));
        RecyclerListView r02 = this.recyclerListView;
        r02.setPadding(r02.getPaddingLeft(), this.recyclerListView.getPaddingTop(), this.recyclerListView.getPaddingRight(), this.recyclerListView.getPaddingBottom() + AndroidUtilities.dp(80.0f));
    }

    @Override // org.telegram.ui.Components.BottomSheetWithRecyclerListView
    public CharSequence getTitle() {
        return null;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void setTitle(CharSequence r1) {
        this.actionBar.setTitle(r1);
    }
}
