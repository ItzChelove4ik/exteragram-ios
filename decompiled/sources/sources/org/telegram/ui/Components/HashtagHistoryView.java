package org.telegram.ui.Components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.dx.io.Opcodes;
import java.util.ArrayList;
import org.telegram.messenger.HashtagSearchController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class HashtagHistoryView extends FrameLayout {
    private final UniversalAdapter adapter;
    private final int currentAccount;
    private final ImageView emptyImage;
    private final TextView emptyText;
    public final FrameLayout emptyView;
    private ArrayList<String> history;
    private Utilities.Callback<String> onClickListener;
    private final UniversalRecyclerView recyclerView;
    private final Theme.ResourcesProvider resourcesProvider;

    public static /* synthetic */ void $r8$lambda$2PfvNAaJHgRSYK_QpoO6qbhyXGc(HashtagHistoryView r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onClick(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$I2n01wOzdpSwNTsMFIefw8nDbig(HashtagHistoryView r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$Y5Vg8et5BF485aFrrIYkeH6evZk(HashtagHistoryView r0, UItem r1, View r2, int r3, float r4, float r5) {
        return r0.onLongClick(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$tMeS0hTggqVIiuI5dZsblZTO6RM(HashtagHistoryView r0, String r1, AlertDialog r2, int r3) {
        r0.lambda$onLongClick$0(r1, r2, r3);
    }

    public HashtagHistoryView(Context r9, Theme.ResourcesProvider r10, int r11) {
        super(r9);
        this.currentAccount = r11;
        this.resourcesProvider = r10;
        UniversalRecyclerView r0 = new UniversalRecyclerView(r9, r11, 0, new HashtagHistoryView$$ExternalSyntheticLambda0(this), new HashtagHistoryView$$ExternalSyntheticLambda1(this), new HashtagHistoryView$$ExternalSyntheticLambda2(this), r10);
        this.recyclerView = r0;
        r0.setClipToPadding(false);
        UniversalAdapter r102 = (UniversalAdapter) r0.getAdapter();
        this.adapter = r102;
        r102.setApplyBackground(false);
        addView(r0, -1, -1);
        FrameLayout r92 = new FrameLayout(r9);
        this.emptyView = r92;
        ImageView r103 = new ImageView(r9);
        this.emptyImage = r103;
        int r2 = Theme.key_windowBackgroundWhiteGrayIcon;
        r103.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r2, r10), PorterDuff.Mode.MULTIPLY));
        r103.setScaleType(ImageView.ScaleType.CENTER);
        r103.setImageResource(R.drawable.large_hashtags);
        r92.addView(r103, LayoutHelper.createFrame(56, 56, 49));
        TextView r104 = new TextView(r9);
        this.emptyText = r104;
        r104.setTextColor(Theme.getColor(r2, r10));
        r104.setText(LocaleController.getString(R.string.HashtagSearchPlaceholder));
        r104.setGravity(17);
        r92.addView(r104, LayoutHelper.createFrame(-2, -2.0f, 81, 0.0f, 56.0f, 0.0f, 0.0f));
        addView(r92, LayoutHelper.createFrame(Opcodes.MUL_INT_LIT16, -2, 17));
        r0.setEmptyView(r92);
    }

    private void fillItems(ArrayList<UItem> r5, UniversalAdapter r6) {
        ArrayList<String> r62 = new ArrayList(0);
        this.history = r62;
        r62.addAll(HashtagSearchController.getInstance(this.currentAccount).history);
        if (this.history.isEmpty() == false) goto L5;
        return;
    L5:
        int r63 = 0;
    L7:
        if (r63 >= this.history.size()) goto L19;
        String r1 = this.history.get(r63);
        if (r1.startsWith("#") == true) goto L14;
        if (r1.startsWith("$") == true) goto L14;
    L18:
        r63 = r63 + 1;
    L14:
        if (r1.startsWith("$") == false) goto L16;
        int r2 = R.drawable.menu_cashtag;
    L17:
        r5.add(UItem.asButton(r63 + 1, r2, r1.substring(1)));
        goto L18
    L16:
        r2 = R.drawable.menu_hashtag;
        goto L17
    L19:
        r5.add(UItem.asButton(0, R.drawable.msg_clear_recent, LocaleController.getString(R.string.ClearHistory)));
    }

    private /* synthetic */ void lambda$onLongClick$0(String r1, AlertDialog r2, int r3) {
        HashtagSearchController.getInstance(this.currentAccount).removeHashtagFromHistory(r1);
        update();
    }

    private void onClick(UItem r1, View r2, int r3, float r4, float r5) {
        int r12 = r1.id;
        if (r12 != 0) goto L6;
        HashtagSearchController.getInstance(this.currentAccount).clearHistory();
        update();
        return;
    L6:
        Utilities.Callback<String> r22 = this.onClickListener;
        if (r22 == null) goto L10;
        r22.run(this.history.get(r12 - 1));
        return;
    }

    private boolean onLongClick(UItem r1, View r2, int r3, float r4, float r5) {
        int r12 = r1.id;
        if (r12 == 0) goto L6;
        String r13 = this.history.get(r12 - 1);
        AlertDialog.Builder r22 = new AlertDialog.Builder(getContext(), this.resourcesProvider);
        r22.setTitle(LocaleController.getString(R.string.ClearSearchSingleAlertTitle));
        r22.setMessage(LocaleController.formatString(R.string.ClearSearchSingleHashtagAlertText, new Object[]{r13}));
        r22.setPositiveButton(LocaleController.getString(R.string.ClearSearchRemove), new HashtagHistoryView$$ExternalSyntheticLambda3(this, r13));
        r22.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r22.create().show();
        return true;
    L6:
        return false;
    }

    public void setOnHashtagClickListener(Utilities.Callback<String> r1) {
        this.onClickListener = r1;
    }

    public void setOnScrollListener(RecyclerView.OnScrollListener r1) {
        this.recyclerView.addOnScrollListener(r1);
    }

    public void setTopBottomPadding(int r3, int r4) {
        this.recyclerView.setPadding(0, r3, 0, r4);
        this.emptyView.setTranslationY((r3 - r4) / 2.0f);
    }

    public void update() {
        this.adapter.update(true);
    }
}
