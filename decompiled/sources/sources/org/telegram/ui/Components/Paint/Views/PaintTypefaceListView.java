package org.telegram.ui.Components.Paint.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.util.Consumer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.NotificationCenter;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.Components.Paint.PaintTypeface;
import org.telegram.ui.Components.Paint.Views.PaintTextOptionsView;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public class PaintTypefaceListView extends RecyclerListView implements NotificationCenter.NotificationCenterDelegate {
    private Path mask;
    private Consumer<Path> maskProvider;

    /* renamed from: org.telegram.ui.Components.Paint.Views.PaintTypefaceListView$1, reason: invalid class name */
    public class AnonymousClass1 extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ PaintTypefaceListView this$0;

        public AnonymousClass1(PaintTypefaceListView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return PaintTypeface.get().size();
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r1, int r2) {
            ((PaintTextOptionsView.TypefaceCell) r1.itemView).bind(PaintTypeface.get().get(r2));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            PaintTextOptionsView.TypefaceCell r1 = new PaintTextOptionsView.TypefaceCell(r2.getContext());
            r1.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r1);
        }
    }

    public PaintTypefaceListView(Context r3) {
        super(r3);
        this.mask = new Path();
        setWillNotDraw(false);
        setLayoutManager(new LinearLayoutManager(r3));
        setAdapter(new AnonymousClass1(this));
        setPadding(0, AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(8.0f));
        setClipToPadding(false);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.customTypefacesLoaded) goto L6;
        getAdapter().notifyDataSetChanged();
        return;
    }

    @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent r1) {
        return super.dispatchTouchEvent(r1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void draw(Canvas r3) {
        Consumer<Path> r0 = this.maskProvider;
        if (r0 == null) goto L5;
        r0.accept(this.mask);
        r3.save();
        r3.clipPath(this.mask);
    L5:
        super.draw(r3);
        if (this.maskProvider == null) goto L9;
        r3.restore();
        return;
    }

    @Override // org.telegram.ui.Components.RecyclerListView
    public Integer getSelectorColor(int r1) {
        return 285212671;
    }

    @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.customTypefacesLoaded);
    }

    @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.customTypefacesLoaded);
    }

    @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec((Math.min(PaintTypeface.get().size(), 6) * AndroidUtilities.dp(48.0f)) + AndroidUtilities.dp(16.0f), TLObject.FLAG_30));
    }

    @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent r1) {
        return super.onTouchEvent(r1);
    }

    public void setMaskProvider(Consumer<Path> r1) {
        this.maskProvider = r1;
        invalidate();
    }
}
