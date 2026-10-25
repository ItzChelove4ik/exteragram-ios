package org.telegram.ui.Components;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ArchivedStickerSetCell;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.StickersActivity;

/* loaded from: classes7.dex */
public class StickersArchiveAlert extends AlertDialog.Builder {
    private int currentType;
    private BaseFragment parentFragment;
    private ArrayList<TLRPC.StickerSetCovered> stickerSets;

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        Context context;
        final /* synthetic */ StickersArchiveAlert this$0;

        public ListAdapter(StickersArchiveAlert r1, Context r2) {
            this.this$0 = r1;
            this.context = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return StickersArchiveAlert.m13132$$Nest$fgetstickerSets(this.this$0).size();
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r3, int r4) {
            ArchivedStickerSetCell r32 = (ArchivedStickerSetCell) r3.itemView;
            TLRPC.StickerSetCovered r0 = (TLRPC.StickerSetCovered) StickersArchiveAlert.m13132$$Nest$fgetstickerSets(this.this$0).get(r4);
            boolean r1 = true;
            if (r4 != (StickersArchiveAlert.m13132$$Nest$fgetstickerSets(this.this$0).size() - 1)) goto L6;
            r1 = false;
        L6:
            r32.setStickersSet(r0, r1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            ArchivedStickerSetCell r22 = new ArchivedStickerSetCell(this.context, false);
            r22.setLayoutParams(new RecyclerView.LayoutParams(-1, AndroidUtilities.dp(82.0f)));
            return new RecyclerListView.Holder(r22);
        }
    }

    public static /* synthetic */ void $r8$lambda$5h0ItYDBC38SOEI3pwmaWHXrmbE(StickersArchiveAlert r0, AlertDialog r1, int r2) {
        r0.lambda$new$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$snTsqwG1WYuQ92Ky4abb27Y5hI4(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    /* renamed from: -$$Nest$fgetstickerSets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m13132$$Nest$fgetstickerSets(StickersArchiveAlert r0) {
        return r0.stickerSets;
    }

    public StickersArchiveAlert(Context r8, BaseFragment r9, ArrayList<TLRPC.StickerSetCovered> r10) {
        super(r8);
        TLRPC.StickerSetCovered r1 = r10.get(0);
        if (r1.set.masks == false) goto L5;
        this.currentType = 1;
        setTitle(LocaleController.getString(R.string.ArchivedMasksAlertTitle));
    L6:
        this.stickerSets = new ArrayList(r10);
        this.parentFragment = r9;
        LinearLayout r92 = new LinearLayout(r8);
        r92.setOrientation(1);
        setView(r92);
        TextView r102 = new TextView(r8);
        r102.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        r102.setGravity(LayoutHelper.getAbsoluteGravityStart());
        r102.setTextSize(1, 16.0f);
        r102.setPadding(AndroidUtilities.dp(23.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(23.0f), 0);
        if (r1.set.masks == false) goto L9;
        r102.setText(LocaleController.getString(R.string.ArchivedMasksAlertInfo));
    L10:
        r92.addView(r102, LayoutHelper.createLinear(-2, -2));
        RecyclerListView r103 = new RecyclerListView(r8);
        r103.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        r103.setAdapter(new ListAdapter(this, r8));
        r103.setVerticalScrollBarEnabled(false);
        r103.setPadding(AndroidUtilities.dp(10.0f), 0, AndroidUtilities.dp(10.0f), 0);
        r103.setGlowColor(-657673);
        r92.addView(r103, LayoutHelper.createLinear(-1, -2, 0.0f, 10.0f, 0.0f, 0.0f));
        setNegativeButton(LocaleController.getString(R.string.Close), new StickersArchiveAlert$$ExternalSyntheticLambda0());
        if (this.parentFragment == null) goto L14;
        setPositiveButton(LocaleController.getString(R.string.Settings), new StickersArchiveAlert$$ExternalSyntheticLambda1(this));
        return;
    L14:
        return;
    L9:
        r102.setText(LocaleController.getString(R.string.ArchivedStickersAlertInfo));
        goto L10
    L5:
        this.currentType = 0;
        setTitle(LocaleController.getString(R.string.ArchivedStickersAlertTitle));
        goto L6
    }

    private /* synthetic */ void lambda$new$1(AlertDialog r3, int r4) {
        this.parentFragment.presentFragment(new StickersActivity(this.currentType, null));
        r3.dismiss();
    }
}
