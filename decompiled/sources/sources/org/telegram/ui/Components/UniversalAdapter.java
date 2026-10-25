package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import androidx.mediarouter.media.PlatformMediaRouter1RouteProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.plugins.models.SettingItem;
import com.exteragram.messenger.plugins.models.TextSetting;
import java.util.ArrayList;
import me.vkryl.core.BitwiseUtils;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stats;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Business.BusinessLinksActivity;
import org.telegram.ui.Business.QuickRepliesActivity;
import org.telegram.ui.Business.QuickRepliesController;
import org.telegram.ui.Cells.CheckBoxCell;
import org.telegram.ui.Cells.CollapseTextCell;
import org.telegram.ui.Cells.DialogCell;
import org.telegram.ui.Cells.DialogRadioCell;
import org.telegram.ui.Cells.EditTextCell;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.NotificationsCheckCell;
import org.telegram.ui.Cells.ProfileSearchCell;
import org.telegram.ui.Cells.RadioButtonCell;
import org.telegram.ui.Cells.SlideIntChooseView;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextCheckCell2;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextRightIconCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Cells.UserCell;
import org.telegram.ui.ChannelMonetizationLayout;
import org.telegram.ui.Charts.BaseChartView;
import org.telegram.ui.Components.ListView.AdapterWithDiffUtils;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.StatisticActivity;
import org.telegram.ui.Stories.recorder.HintView2;
import org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet;

/* loaded from: classes3.dex */
public class UniversalAdapter extends AdapterWithDiffUtils {
    private boolean allowReorder;
    private boolean applyBackground;
    private BaseChartView.SharedUiComponents chartSharedUI;
    private final int classGuid;
    private final Context context;
    public final int currentAccount;
    private Section currentReorderSection;
    private Section currentWhiteSection;
    private final boolean dialog;
    protected Utilities.Callback2<ArrayList<UItem>, UniversalAdapter> fillItems;
    private final ArrayList<UItem> items;
    public int itemsOffset;
    protected final RecyclerListView listView;
    private final ArrayList<UItem> oldItems;
    private Utilities.Callback2<Integer, ArrayList<UItem>> onReordered;
    private boolean orderChanged;
    private int orderChangedId;
    private final ArrayList<Section> reorderSections;
    private final Theme.ResourcesProvider resourcesProvider;
    private final ArrayList<Section> whiteSections;

    /* renamed from: org.telegram.ui.Components.UniversalAdapter$1, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ UniversalAdapter this$0;

        public AnonymousClass1(UniversalAdapter r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), r3);
        }
    }

    /* renamed from: org.telegram.ui.Components.UniversalAdapter$2, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass2 extends FrameLayout {
        final /* synthetic */ UniversalAdapter this$0;

        public AnonymousClass2(UniversalAdapter r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            int r42 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r4), TLObject.FLAG_30);
            measureChildren(r42, r5);
            int r52 = 0;
            int r1 = 0;
        L4:
            if (r52 >= getChildCount()) goto L6;
            r1 = Math.max(r1, getChildAt(r52).getMeasuredHeight());
            r52 = r52 + 1;
            goto L4
        L6:
            super.onMeasure(r42, View.MeasureSpec.makeMeasureSpec(r1, TLObject.FLAG_30));
        }
    }

    /* loaded from: classes7.dex */
    public static class FullscreenCustomFrameLayout extends FrameLayout {
        private int minusHeight;
        private boolean minusPadding;

        public FullscreenCustomFrameLayout(Context r1) {
            super(r1);
            this.minusHeight = 0;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r6, int r7) {
            int r0 = this.minusHeight;
            if ((getParent() instanceof View) == false) goto L5;
            View r1 = (View) getParent();
        L7:
            if (this.minusPadding == false) goto L11;
            if (r1 == null) goto L11;
            r0 = (r0 + r1.getPaddingTop()) + r1.getPaddingBottom();
        L11:
            if (r1 == null) goto L17;
            if (r1.getMeasuredHeight() <= 0) goto L17;
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r6), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r1.getMeasuredHeight() - r0, TLObject.FLAG_30));
            return;
        L17:
            if (View.MeasureSpec.getMode(r7) == 0) goto L20;
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r6), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r7) - r0, TLObject.FLAG_30));
            return;
        L20:
            int r12 = View.MeasureSpec.getSize(r7);
            int r62 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r6), TLObject.FLAG_30);
            measureChildren(r62, r7);
            int r72 = 0;
            int r3 = 0;
        L22:
            if (r72 >= getChildCount()) goto L24;
            r3 = Math.max(r3, getChildAt(r72).getMeasuredHeight());
            r72 = r72 + 1;
            goto L22
        L24:
            if (r12 <= 0) goto L26;
            r3 = Math.min(r3, r12 - r0);
        L26:
            super.onMeasure(r62, View.MeasureSpec.makeMeasureSpec(r3, TLObject.FLAG_30));
            return;
        L5:
            r1 = null;
            goto L7
        }

        public void setMinusHeight(int r1) {
            this.minusHeight = r1;
        }

        public void setMinusPadding(boolean r1) {
            this.minusPadding = r1;
        }
    }

    /* loaded from: classes7.dex */
    public static class Section {
        public int end;
        public int start;

        private Section() {
        }

        public /* synthetic */ Section(UniversalAdapterIA r1) {
            this();
        }

        public boolean contains(int r2) {
            if (r2 >= this.start) goto L5;
            return false;
        L5:
            if (r2 > this.end) goto L10;
            return true;
        L10:
            return false;
        }
    }

    /* loaded from: classes7.dex */
    public static class SpaceView extends View {
        private int height;

        public SpaceView(Context r1) {
            super(r1);
            setTag(-33024);
        }

        @Override // android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(this.height, TLObject.FLAG_30));
        }

        public void setHeight(int r2) {
            if (this.height != r2) goto L5;
            return;
        L5:
            this.height = r2;
            requestLayout();
        }
    }

    public static /* synthetic */ void $r8$lambda$1wyK2ywxjPZOsr8dl6fghzdPDMc(UItem r0, TextCheckCell2 r1) {
        r0.clickCallback.onClick(r1);
    }

    public static /* synthetic */ void $r8$lambda$FyXULGrJCbzgPBhCX_xYYkBMPnM(UItem r0, TextCheckCell2 r1) {
        r0.switchClickCallback.onClick(r1);
    }

    /* renamed from: $r8$lambda$NIPY3CJYdYKGXX4T4kOV-DMH18c, reason: not valid java name */
    public static /* synthetic */ StatisticActivity.BaseChartCell m13478$r8$lambda$NIPY3CJYdYKGXX4T4kOVDMH18c(UniversalAdapter r0, UItem r1) {
        return r0.lambda$onBindViewHolder$3(r1);
    }

    public static /* synthetic */ void $r8$lambda$T0LSwD87LisoRJkto5JsMpKXFOQ(UniversalAdapter r0, boolean r1) {
        r0.lambda$update$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$oQs2QYu66NvT3BIPP1RyA3k9ryA(UItem r1, Integer r2) {
        r1.intValue = r2.intValue();
        Utilities.Callback<Integer> r12 = r1.intCallback;
        if (r12 == null) goto L6;
        r12.run(r2);
        return;
    }

    public static /* synthetic */ void $r8$lambda$zkYqYy_dtBSQIc15WzODxdmLimw(UItem r0, int r1) {
        Utilities.Callback<Integer> r02 = r0.intCallback;
        if (r02 == null) goto L6;
        r02.run(Integer.valueOf(r1));
        return;
    }

    public UniversalAdapter(RecyclerListView r9, Context r10, int r11, int r12, Utilities.Callback2<ArrayList<UItem>, UniversalAdapter> r13, Theme.ResourcesProvider r14) {
        this(r9, r10, r11, r12, false, r13, r14);
    }

    public UniversalAdapter(RecyclerListView r3, Context r4, int r5, int r6, boolean r7, Utilities.Callback2<ArrayList<UItem>, UniversalAdapter> r8, Theme.ResourcesProvider r9) {
        this.applyBackground = true;
        this.oldItems = new ArrayList();
        this.items = new ArrayList();
        this.itemsOffset = 0;
        this.whiteSections = new ArrayList();
        this.reorderSections = new ArrayList();
        this.listView = r3;
        this.context = r4;
        this.currentAccount = r5;
        this.classGuid = r6;
        this.dialog = r7;
        this.fillItems = r8;
        this.resourcesProvider = r9;
        update(false);
    }

    private void callReorder(int r6) {
        if (r6 >= 0) goto L4;
        return;
    L4:
        if (r6 >= this.reorderSections.size()) goto L9;
        Section r0 = this.reorderSections.get(r6);
        this.onReordered.run(Integer.valueOf(r6), new ArrayList(this.items.subList(r0.start, r0.end + 1)));
        this.orderChanged = false;
        return;
    }

    private View findViewByItemObject(Object r6) {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r1 >= getItemCount()) goto L11;
        UItem r2 = getItem(r1);
        if (r2 == null) goto L10;
        if (r2.object != r6) goto L10;
    L13:
        if (r1 != (-1)) goto L16;
        return null;
    L16:
        if (r0 >= this.listView.getChildCount()) goto L22;
        View r22 = this.listView.getChildAt(r0);
        int r4 = this.listView.getChildAdapterPosition(r22);
        if (r4 == (-1)) goto L21;
        if (r4 != r1) goto L21;
        return r22;
    L21:
        r0 = r0 + 1;
        goto L16
    L22:
        return null;
    L10:
        r1 = r1 + 1;
        goto L4
    L11:
        r1 = -1;
        goto L13
    }

    private boolean hasDivider(int r4) {
        UItem r0 = getItem(r4);
        UItem r3 = getItem(r4 + 1);
        if (r0 == null) goto L16;
        if (r3 == null) goto L16;
        if (r0.hideDivider == true) goto L16;
        if (ExteraConfig.getSectionsSeparatedHeaders() == false) goto L14;
        if (isHeader(r3.viewType) == false) goto L14;
        return false;
    L14:
        if (isShadow(r3.viewType) != isShadow(r0.viewType)) goto L16;
        return true;
    L16:
        return false;
    }

    public static boolean isHeader(int r2) {
        if (r2 != 0) goto L5;
    L12:
        return true;
    L5:
        if (r2 == 42) goto L12;
        if (r2 == 1) goto L12;
        if (r2 == 26) goto L12;
        return false;
    }

    public static boolean isShadow(int r3) {
        if (r3 < UItem.factoryViewTypeStartsWith) goto L11;
        UItem.UItemFactory<?> r32 = UItem.findFactory(r3);
        if (r32 != null) goto L7;
    L9:
        return false;
    L7:
        if (r32.isShadow() == false) goto L9;
        return true;
    L11:
        if (r3 != 7) goto L13;
    L28:
        return true;
    L13:
        if (r3 == 8) goto L28;
        if (r3 == 38) goto L28;
        if (r3 == 31) goto L28;
        if (r3 == (-4)) goto L28;
        if (r3 == 28) goto L28;
        if (r3 == 2) goto L28;
        if (r3 == (-2)) goto L28;
        return false;
    }

    private /* synthetic */ StatisticActivity.BaseChartCell lambda$onBindViewHolder$3(UItem r1) {
        View r0 = findViewByItemObject(r1.object);
        if ((r0 instanceof StatisticActivity.UniversalChartCell) == true) goto L5;
        return null;
    L5:
        return (StatisticActivity.UniversalChartCell) r0;
    }

    private /* synthetic */ void lambda$update$0(boolean r1) {
        updateInternal(r1);
    }

    private void updateColors(RecyclerView.ViewHolder r3, UItem r4) {
        KeyEvent.Callback r0 = r3.itemView;
        if ((r0 instanceof Theme.Colorable) == false) goto L6;
        ((Theme.Colorable) r0).updateColors();
    L6:
        if (shouldApplyBackground(r3.getItemViewType()) == false) goto L18;
        if (r4 == null) goto L13;
        if (r4.transparent == false) goto L13;
        r3.itemView.setBackground(null);
        return;
    L13:
        if (this.dialog == false) goto L15;
        int r42 = Theme.key_dialogBackground;
    L16:
        r3.itemView.setBackgroundColor(getThemedColor(r42));
        return;
    L15:
        r42 = Theme.key_windowBackgroundWhite;
        goto L16
    }

    private void updateInternal(boolean r3) {
        RecyclerListView r0 = this.listView;
        if (r0 != null) goto L5;
    L7:
        this.oldItems.clear();
        this.oldItems.addAll(this.items);
        this.items.clear();
        this.currentWhiteSection = null;
        this.whiteSections.clear();
        this.reorderSections.clear();
        Utilities.Callback2<ArrayList<UItem>, UniversalAdapter> r02 = this.fillItems;
        if (r02 == null) goto L15;
        r02.run(this.items, this);
        updateReorderSections();
        if (r3 == false) goto L13;
        setItems(this.oldItems, this.items);
        return;
    L13:
        notifyDataSetChanged();
        return;
    L15:
        return;
    L5:
        if (r0.isComputingLayout() == false) goto L7;
    }

    private void updateReorderSections() {
        RecyclerListView r0 = this.listView;
        if (r0 == null) goto L12;
        ArrayList<Long> r1 = r0.forcedSections;
        if (r1 != null) goto L8;
        r0.forcedSections = new ArrayList();
    L9:
        ArrayList<Section> r02 = this.whiteSections;
        int r12 = r02.size();
        int r2 = 0;
    L10:
        if (r2 >= r12) goto L14;
        Section r3 = r02.get(r2);
        r2 = r2 + 1;
        Section r32 = r3;
        this.listView.forcedSections.add(Long.valueOf(AndroidUtilities.pack(r32.start, r32.end)));
        goto L10
    L14:
        return;
    L8:
        r1.clear();
        goto L9
    }

    public void drawWhiteSections(Canvas r5, RecyclerListView r6) {
        int r0 = 0;
    L4:
        if (r0 >= this.whiteSections.size()) goto L14;
        Section r1 = this.whiteSections.get(r0);
        int r2 = r1.end;
        if (r2 < 0) goto L13;
        int r12 = r1.start;
        if (this.dialog == false) goto L11;
        int r3 = Theme.key_dialogBackground;
    L12:
        r6.drawSectionBackground(r5, r12, r2, getThemedColor(r3));
        goto L13
    L11:
        r3 = Theme.key_windowBackgroundWhite;
    L13:
        r0 = r0 + 1;
        goto L4
    }

    public UItem getItem(int r2) {
        if (r2 >= 0) goto L4;
        return null;
    L4:
        if (r2 < this.items.size()) goto L7;
        return null;
    L7:
        return this.items.get(r2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int r1) {
        UItem r0 = getItem(r1);
        if (r0 != null) goto L7;
        return 0;
    L7:
        return r0.viewType;
    }

    public int getReorderSectionId(int r3) {
        int r0 = 0;
    L4:
        if (r0 >= this.reorderSections.size()) goto L9;
        if (this.reorderSections.get(r0).contains(r3) == true) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        return r0;
    L9:
        return -1;
    }

    public int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
    public boolean isEnabled(RecyclerView.ViewHolder r2) {
        int r0 = r2.getItemViewType();
        UItem r1 = getItem(r2.getAdapterPosition());
        if (r0 < UItem.factoryViewTypeStartsWith) goto L10;
        UItem.UItemFactory<?> r22 = UItem.findFactory(r0);
        if (r22 != null) goto L7;
        return false;
    L7:
        if (r22.isClickable() == false) goto L63;
    L55:
        if (r1 != null) goto L57;
        return true;
    L57:
        if (r1.enabled == true) goto L66;
        return false;
    L66:
        return true;
    L63:
        return false;
    L10:
        if (r0 == 3) goto L55;
        if (r0 == 5) goto L55;
        if (r0 == 6) goto L55;
        if (r0 == 30) goto L55;
        if (r0 == 4) goto L55;
        if (r0 == 10) goto L55;
        if (r0 == 44) goto L55;
        if (r0 == 11) goto L55;
        if (r0 == 12) goto L55;
        if (r0 == 17) goto L55;
        if (r0 == 16) goto L55;
        if (r0 == 29) goto L55;
        if (r0 == 25) goto L55;
        if (r0 == 27) goto L55;
        if (r0 == 32) goto L55;
        if (r0 == 33) goto L55;
        if (r0 == 35) goto L55;
        if (r0 == 36) goto L55;
        if (r0 == 37) goto L55;
        if (r0 == 41) goto L55;
        if (r0 == 39) goto L55;
        if (r0 == 40) goto L55;
        if (r0 == 38) goto L55;
        return false;
    }

    public boolean isReorderItem(int r1) {
        if (getReorderSectionId(r1) < 0) goto L6;
        return true;
    L6:
        return false;
    }

    public void listenReorder(Utilities.Callback2<Integer, ArrayList<UItem>> r1) {
        this.onReordered = r1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder r24, int r25) {
        UItem r2 = getItem(r25);
        UItem r1 = getItem(r25 + 1);
        UItem r3 = getItem(r25 - 1);
        if (r2 == null) goto L404;
        int r5 = r24.getItemViewType();
        boolean r13 = hasDivider(r25);
        updateColors(r24, r2);
        Drawable r7 = null;
        UniversalRecyclerView r72 = null;
        MessageObject r73 = null;
        Utilities.Callback<TLRPC.User> r74 = null;
        String r75 = null;
        if (r5 < UItem.factoryViewTypeStartsWith) goto L13;
        UItem.UItemFactory<?> r0 = UItem.findFactory(r5);
        if (r0 == null) goto L399;
        View r12 = r24.itemView;
        RecyclerListView r32 = this.listView;
        if ((r32 instanceof UniversalRecyclerView) == false) goto L12;
        r72 = (UniversalRecyclerView) r32;
    L12:
        r0.bindView(r12, r2, r13, this, r72);
    L399:
        Utilities.Callback<View> r02 = r2.bind;
        if (r02 == null) goto L403;
        r02.run(r24.itemView);
        return;
    L403:
        return;
    L13:
        int r8 = 12;
        String r11 = _UrlKt.FRAGMENT_ENCODE_SET;
        boolean r122 = false;
        boolean r123 = false;
        boolean r124 = false;
        int r125 = 0;
        boolean r126 = false;
        r122 = false;
        boolean r15 = true;
        switch(r5) {
            case -4: goto L378;
            case -3: goto L361;
            case -2: goto L378;
            case -1: goto L378;
            case 0: goto L360;
            case 1: goto L360;
            case 2: goto L346;
            case 3: goto L282;
            case 4: goto L252;
            case 5: goto L241;
            case 6: goto L241;
            case 7: goto L192;
            case 8: goto L192;
            case 9: goto L252;
            case 10: goto L182;
            case 11: goto L179;
            case 12: goto L179;
            case 13: goto L174;
            case 14: goto L173;
            case 15: goto L172;
            case 16: goto L169;
            case 17: goto L166;
            case 18: goto L165;
            case 19: goto L165;
            case 20: goto L165;
            case 21: goto L165;
            case 22: goto L165;
            case 23: goto L165;
            case 24: goto L164;
            case 25: goto L163;
            case 26: goto L360;
            case 27: goto L151;
            case 28: goto L145;
            case 29: goto L141;
            case 30: goto L140;
            case 31: goto L136;
            case 32: goto L89;
            case 33: goto L82;
            case 34: goto L81;
            case 35: goto L63;
            case 36: goto L63;
            case 37: goto L55;
            case 38: goto L192;
            case 39: goto L31;
            case 40: goto L31;
            case 41: goto L63;
            case 42: goto L27;
            case 43: goto L20;
            case 44: goto L16;
            default: goto L399;
        };
    L16:
        RadioButtonCell r03 = (RadioButtonCell) r24.itemView;
        if (r03.itemId != r2.id) goto L19;
        r03.setChecked(r2.checked, true);
    L19:
        r03.setEnabled(r2.enabled);
        r03.setTextAndValue(r2.text, r2.textValue, r13, r2.checked);
        r03.itemId = r2.id;
        goto L399
    L20:
        TextSettingsCell r04 = (TextSettingsCell) r24.itemView;
        r04.getValueBackupImageView().setImageDrawable(null);
        r04.getValueBackupImageView().setAnimatedEmojiDrawable(null);
        CharSequence r14 = r2.text;
        if (r14 == null) goto L26;
        CharSequence r33 = r2.subtext;
        if (r33 == null) goto L25;
        r04.setTextAndValue(r14, r33, r13);
        goto L26
    L25:
        r04.setText(r14, r13);
    L26:
        r04.setIcon(r2.iconResId);
        goto L399
    L27:
        HeaderCell r05 = (HeaderCell) r24.itemView;
        CharSequence r16 = r2.animatedText;
        if (r05.id != r2.id) goto L30;
        r123 = true;
    L30:
        r05.setText(r16, r123);
        r05.id = r2.id;
        goto L399
    L31:
        TextCheckCell2 r06 = (TextCheckCell2) r24.itemView;
        r06.reset();
        CharSequence r17 = r2.text;
        boolean r34 = r2.checked;
        if (r06.id == r2.id) goto L35;
        r15 = false;
    L35:
        r06.setTextAndCheck(r17, r34, r13, r15);
        r06.getCheckBox().setDrawIconType(r2.intValue);
        Switch r18 = r06.getCheckBox();
        if (r2.intValue != 0) goto L38;
        int r35 = Theme.key_switchTrack;
    L39:
        int r4 = Theme.key_switchTrackChecked;
        int r76 = Theme.key_windowBackgroundWhite;
        r18.setColors(r35, r4, r76, r76);
        r06.id = r2.id;
        if (r2.locked == false) goto L42;
        int r19 = R.drawable.permission_locked;
    L43:
        r06.setIcon(r19);
        if (r5 != 40) goto L399;
        if (TextUtils.isEmpty(r2.animatedText) == false) goto L48;
        r06.hideCollapseArrow();
        goto L399
    L48:
        String r110 = r2.animatedText.toString();
        boolean r36 = r2.collapsed;
        if (r2.exteraExpandableSwitch == false) goto L51;
        Runnable r52 = new UniversalAdapter$$ExternalSyntheticLambda3(r2, r06);
    L52:
        r06.setCollapseArrow(r110, r36, r52);
        r06.getCollapseContainer().setVisibility(0);
        if (r2.exteraExpandableSwitch == false) goto L399;
        r06.getCheckBox().setColors(Theme.key_switchTrack, r4, r76, r76);
        r06.getCheckBox().setDrawIconType(0);
        goto L399
    L51:
        r52 = new UniversalAdapter$$ExternalSyntheticLambda4(r2, r06);
        goto L52
    L42:
        r19 = 0;
        goto L43
    L38:
        r35 = Theme.key_fill_RedNormal;
        goto L39
    L55:
        CheckBoxCell r07 = (CheckBoxCell) r24.itemView;
        r07.setPad(r2.pad);
        TLObject r111 = (TLObject) r2.object;
        if (r07.itemId != r2.id) goto L58;
        boolean r37 = true;
    L59:
        r07.setUserOrChat(r111, r37);
        boolean r112 = r2.checked;
        if (r07.itemId != r2.id) goto L62;
        r124 = true;
    L62:
        r07.setChecked(r112, r124);
        r07.itemId = r2.id;
        r07.setNeedDivider(r13);
        goto L399
    L58:
        r37 = false;
        goto L59
    L63:
        CheckBoxCell r08 = (CheckBoxCell) r24.itemView;
        r08.reset();
        r08.setPad(r2.pad);
        CharSequence r113 = r2.text;
        if (r08.itemId == r2.id) goto L66;
    L68:
        boolean r38 = false;
    L69:
        r08.setText(r113, _UrlKt.FRAGMENT_ENCODE_SET, r38);
        boolean r114 = r2.checked;
        if (r08.itemId == r2.id) goto L73;
        r15 = false;
    L73:
        r08.setChecked(r114, r15);
        r08.setNeedDivider(r13);
        r08.itemId = r2.id;
        if (r2.locked == false) goto L76;
        r125 = R.drawable.permission_locked;
    L76:
        r08.setIcon(r125);
        if (r5 != 36) goto L79;
    L80:
        r08.setCollapseButton(r2.collapsed, r2.animatedText, r2.clickCallback);
        goto L399
    L79:
        if (r5 != 41) goto L399;
    L66:
        if (r08.getAnimatedTextView().getText() == _UrlKt.FRAGMENT_ENCODE_SET) goto L68;
        r38 = true;
        goto L69
    L81:
        ((FlickerLoadingView) r24.itemView).setViewType(r2.intValue);
        goto L399
    L82:
        DialogCell r142 = (DialogCell) r24.itemView;
        Object r09 = r2.object;
        if ((r09 instanceof MessageObject) == false) goto L85;
        r73 = (MessageObject) r09;
    L85:
        r142.useSeparator = r13;
        if (r73 != null) goto L88;
        r142.setDialog(0, null, 0, false, false);
        goto L399
    L88:
        r142.setDialog(r73.getDialogId(), r73, r73.messageOwner.date, false, false);
        goto L399
    L89:
        ProfileSearchCell r115 = (ProfileSearchCell) r24.itemView;
        Object r39 = r2.object;
        if (r2.accent == false) goto L98;
        if ((r39 instanceof TLRPC.User) == false) goto L98;
        int r42 = ((TLRPC.User) r39).bot_active_users;
        if (r42 == 0) goto L98;
        if (r42 == 0) goto L108;
        CharSequence r43 = LocaleController.formatPluralStringSpaced("BotUsers", r42);
    L110:
        if ((r39 instanceof TLRPC.Chat) == false) goto L126;
        TLRPC.Chat r53 = (TLRPC.Chat) r39;
        if (r53.participants_count != 0) goto L114;
    L123:
        r11 = r53.title;
    L124:
        String r192 = r11;
        boolean r010 = r2.locked;
        Object r54 = r2.object2;
        if ((r54 instanceof Utilities.Callback) == false) goto L131;
        r74 = (Utilities.Callback) r54;
    L131:
        r115.allowBotOpenButton(r010, r74);
        r115.setRectangularAvatar(r2.red);
        CharSequence r011 = r2.subtext;
        if (r011 == null) goto L134;
        CharSequence r20 = r011;
    L135:
        r115.setData(r39, null, r192, r20, false, false);
        r115.setChecked(r2.checked, false);
        r115.useSeparator = r13;
        goto L399
    L134:
        r20 = r43;
        goto L135
    L114:
        if (ChatObject.isChannel(r53) == true) goto L116;
    L118:
        String r82 = LocaleController.formatPluralStringSpaced("Members", r53.participants_count);
    L120:
        if (TextUtils.isEmpty(r43) == true) goto L122;
        r43 = TextUtils.concat(new CharSequence[]{r43, ", ", r82});
        goto L123
    L122:
        r43 = r82;
        goto L123
    L116:
        if (r53.megagroup == true) goto L118;
        r82 = LocaleController.formatPluralStringSpaced("Subscribers", r53.participants_count);
        goto L120
    L126:
        if ((r39 instanceof TLRPC.User) == false) goto L124;
        r11 = UserObject.getUserName((TLRPC.User) r39);
    L108:
        r43 = _UrlKt.FRAGMENT_ENCODE_SET;
    L98:
        if (r2.withUsername == false) goto L108;
        if ((r39 instanceof TLRPC.User) == false) goto L103;
        String r44 = UserObject.getPublicUsername((TLRPC.User) r39);
    L106:
        if (r44 == null) goto L108;
        r43 = "@".concat(r44);
        goto L110
    L103:
        if ((r39 instanceof TLRPC.Chat) == false) goto L105;
        r44 = ChatObject.getPublicUsername((TLRPC.Chat) r39);
        goto L106
    L105:
        r44 = null;
        goto L106
    L136:
        GraySectionCell r012 = (GraySectionCell) r24.itemView;
        if (TextUtils.equals(r012.getText(), r2.text) == false) goto L139;
        r012.setRightText(r2.subtext, true, r2.clickCallback);
        goto L399
    L139:
        r012.setText(r2.text, r2.subtext, r2.clickCallback);
        goto L399
    L140:
        TextRightIconCell r013 = (TextRightIconCell) r24.itemView;
        r013.setTextAndIcon(r2.text, r2.iconResId);
        r013.setDivider(r13);
        r013.setBackgroundColor(getThemedColor(Theme.key_dialogBackground));
        goto L399
    L141:
        BusinessLinksActivity.BusinessLinkView r014 = (BusinessLinksActivity.BusinessLinkView) r24.itemView;
        Object r116 = r2.object;
        if ((r116 instanceof BusinessLinksActivity.BusinessLinkWrapper) == false) goto L399;
        r014.set((BusinessLinksActivity.BusinessLinkWrapper) r116, r13);
        goto L399
    L151:
        StoryPrivacyBottomSheet.UserCell r015 = (StoryPrivacyBottomSheet.UserCell) r24.itemView;
        long r310 = r015.dialogId;
        Object r117 = r2.object;
        if ((r117 instanceof TLRPC.User) == false) goto L155;
        long r77 = ((TLRPC.User) r117).id;
    L159:
        if (r310 != r77) goto L161;
        boolean r118 = true;
    L162:
        r015.setIsSendAs(false, true);
        r015.set(r2.object);
        r015.checkBox.setVisibility(8);
        r015.radioButton.setVisibility(0);
        r015.setChecked(r2.checked, r118);
        r015.setDivider(r13);
        goto L399
    L161:
        r118 = false;
        goto L162
    L155:
        if ((r117 instanceof TLRPC.Chat) == false) goto L157;
        r77 = -((TLRPC.Chat) r117).id;
        goto L159
    L157:
        r77 = 0;
        goto L159
    L163:
        ((ChannelMonetizationLayout.TransactionCell) r24.itemView).set((TL_stats.BroadcastRevenueTransaction) r2.object, r13);
        goto L399
    L164:
        ((ChannelMonetizationLayout.ProceedOverviewCell) r24.itemView).set((ChannelMonetizationLayout.ProceedOverview) r2.object);
        goto L399
    L165:
        ((StatisticActivity.UniversalChartCell) r24.itemView).set(r2.intValue, (StatisticActivity.ChartViewData) r2.object, new UniversalAdapter$$ExternalSyntheticLambda2(this, r2));
        goto L399
    L166:
        QuickRepliesActivity.LargeQuickReplyView r016 = (QuickRepliesActivity.LargeQuickReplyView) r24.itemView;
        r016.setChecked(r2.checked, false);
        Object r119 = r2.object;
        if ((r119 instanceof QuickRepliesController.QuickReply) == false) goto L399;
        r016.set((QuickRepliesController.QuickReply) r119, r13);
        goto L399
    L169:
        QuickRepliesActivity.QuickReplyView r017 = (QuickRepliesActivity.QuickReplyView) r24.itemView;
        r017.setChecked(r2.checked, false);
        r017.setReorder(this.allowReorder);
        Object r120 = r2.object;
        if ((r120 instanceof QuickRepliesController.QuickReply) == false) goto L399;
        r017.set((QuickRepliesController.QuickReply) r120, null, r13);
        goto L399
    L172:
        SlideIntChooseView r018 = (SlideIntChooseView) r24.itemView;
        r018.set(r2.intValue, (SlideIntChooseView.Options) r2.object, new UniversalAdapter$$ExternalSyntheticLambda1(r2));
        r018.setMinValueAllowed((int) r2.longValue);
        r018.setNeedDivider(r13);
        goto L399
    L173:
        SlideChooseView r019 = (SlideChooseView) r24.itemView;
        r019.setOptions(r2.intValue, r2.texts);
        r019.setNeedDivider(r13);
        r019.setMinAllowedIndex((int) r2.longValue);
        r019.setCallback(new UniversalAdapter$$ExternalSyntheticLambda0(r2));
        goto L399
    L174:
        UserCell r020 = (UserCell) r24.itemView;
        r020.setFromUItem(this.currentAccount, r2, r13);
        CharSequence r121 = r2.textValue;
        if (r121 == null) goto L178;
        r75 = r121.toString().toLowerCase();
    L178:
        r020.setQuery(r75);
        r020.setAddButtonVisible(!r2.checked);
        r020.setCloseIcon(r2.clickCallback);
        goto L399
    L179:
        UserCell r021 = (UserCell) r24.itemView;
        r021.setFromUItem(this.currentAccount, r2, r13);
        if (r5 != 12) goto L399;
        r021.setChecked(r2.checked, false);
        goto L399
    L182:
        DialogRadioCell r022 = (DialogRadioCell) r24.itemView;
        if (r022.itemId != r2.id) goto L185;
        r022.setChecked(r2.checked, true);
        r022.setEnabled(r2.enabled, true);
    L186:
        boolean r127 = TextUtils.isEmpty(r2.textValue);
        CharSequence r311 = r2.text;
        if (r127 == false) goto L189;
        r022.setText(r311, r2.checked, r13);
    L190:
        r022.itemId = r2.id;
        goto L399
    L189:
        r022.setTextAndValue(r311, r2.textValue, r2.checked, r13);
        goto L190
    L185:
        r022.setEnabled(r2.enabled, false);
        goto L186
    L241:
        NotificationsCheckCell r78 = (NotificationsCheckCell) r24.itemView;
        CharSequence r9 = r2.subtext;
        if (r9 == null) goto L244;
        r126 = true;
    L244:
        int r10 = r2.iconResId;
        CharSequence r83 = r2.text;
        if (r10 == 0) goto L247;
        r78.setTextAndValueAndIconAndCheck(r83, r9, r10, r2.checked, 0, r126, r13);
    L248:
        r78.setAnimationsEnabled(true);
        if (r5 != 6) goto L251;
        r78.getImageView().setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon), PorterDuff.Mode.MULTIPLY));
    L251:
        r78.setDrawLine(r2.drawLine);
        goto L399
    L247:
        r78.setTextAndValueAndCheck(r83, r9, r2.checked, 0, r126, r13);
        goto L248
    L252:
        TextCheckCell r023 = (TextCheckCell) r24.itemView;
        r023.reset();
        if (r023.itemId != r2.id) goto L255;
        r023.setChecked(r2.checked);
    L255:
        boolean r128 = TextUtils.isEmpty(r2.textValue);
        CharSequence r312 = r2.text;
        if (r128 == false) goto L262;
        boolean r129 = r2.checked;
        if (r5 != 4) goto L261;
        if (r13 == false) goto L261;
        r122 = true;
    L261:
        r023.setTextAndCheck(r312, r129, r122);
    L269:
        r023.setEnabled(r2.enabled, null);
        int r130 = r2.iconResId;
        if (r130 == 0) goto L272;
        r023.setIcon(r130);
    L272:
        int r131 = r2.checkBoxIconResId;
        if (r131 == 0) goto L275;
        r023.setCheckBoxIcon(r131);
    L275:
        r023.itemId = r2.id;
        if (r5 != 9) goto L399;
        View r024 = r24.itemView;
        if (r2.checked == false) goto L280;
        int r132 = Theme.key_windowBackgroundChecked;
    L281:
        r024.setBackgroundColor(Theme.getColor(r132));
        goto L399
    L280:
        r132 = Theme.key_windowBackgroundUnchecked;
        goto L281
    L262:
        String r182 = r2.textValue.toString();
        boolean r133 = r2.checked;
        boolean r84 = r2.multiline;
        if (r5 != 4) goto L267;
        if (r13 == false) goto L267;
        boolean r21 = true;
    L268:
        r023.setTextAndValueAndCheck(r312, r182, r133, r84, r21);
        r023 = r023;
    L267:
        r21 = false;
        goto L268
    L282:
        TextCell r025 = (TextCell) r24.itemView;
        r025.reset();
        Object r134 = r2.object;
        if ((r134 instanceof TLRPC.Document) == false) goto L286;
        r025.setTextAndSticker(r2.text, (TLRPC.Document) r134, r13);
    L321:
        if (r2.accent == false) goto L324;
        int r135 = Theme.key_windowBackgroundWhiteBlueText4;
        r025.setColors(r135, r135);
    L327:
        Integer r136 = r2.iconColor;
        if (r136 == null) goto L330;
        r025.setColorfulIcon(r136.intValue(), r2.iconColor.intValue(), r2.iconResId, false);
    L330:
        int r137 = r2.pad;
        if (r137 <= 0) goto L333;
        r025.setOffsetFromImage(r137);
    L333:
        int r138 = r2.intValue;
        if (r138 <= 0) goto L341;
        SettingItem r313 = r2.settingItem;
        if (r313 != null) goto L338;
    L339:
        r025.heightDp = r138;
        goto L341
    L338:
        if ((r313 instanceof TextSetting) == true) goto L339;
    L341:
        if (TextUtils.isEmpty(r2.subtext) == true) goto L345;
        r025.setSubtitle(r2.subtext);
        if (r025.getImageView() == null) goto L345;
        r025.getImageView().setTranslationY(AndroidUtilities.dp(2.0f));
    L345:
        r025.setPrioritizeTitleOverValue(r2.prioritizeTitleOverValue);
        r025.setEnabled(r2.enabled, true);
        goto L399
    L324:
        if (r2.red == false) goto L326;
        r025.setColors(Theme.key_text_RedBold, Theme.key_text_RedRegular);
        goto L327
    L326:
        r025.setColors(Theme.key_windowBackgroundWhiteGrayIcon, Theme.key_windowBackgroundWhiteBlackText);
        goto L327
    L286:
        if ((r134 instanceof String) == false) goto L288;
        r025.setTextAndSticker(r2.text, (String) r134, r13);
        goto L321
    L288:
        Object r139 = r2.object2;
        if ((r139 instanceof Drawable) == false) goto L304;
        Drawable r140 = ((Drawable) r139).mutate();
        Integer r314 = r2.iconColor;
        if (r314 == null) goto L293;
        int r315 = r314.intValue();
    L294:
        PorterDuff.Mode r85 = PorterDuff.Mode.MULTIPLY;
        r140.setColorFilter(new PorterDuffColorFilter(r315, r85));
        Object r55 = r2.object;
        if ((r55 instanceof Drawable) == false) goto L297;
        r7 = (Drawable) r55;
    L300:
        CharSequence r316 = r2.text;
        if (r7 == null) goto L303;
        r025.setTextAndIconAndValueDrawable(r316, r7, r140, r13);
        goto L321
    L303:
        r025.setTextAndValueDrawable(r316, r140, r13);
        goto L321
    L297:
        int r56 = r2.iconResId;
        if (r56 == 0) goto L300;
        r7 = ContextCompat.getDrawable(this.context, r56).mutate();
        r7.setColorFilter(new PorterDuffColorFilter(r315, r85));
        goto L300
    L293:
        r315 = Theme.getColor(Theme.key_windowBackgroundWhiteGrayIcon, this.resourcesProvider);
        goto L294
    L304:
        boolean r141 = TextUtils.isEmpty(r2.textValue);
        Object r317 = r2.object;
        if (r141 == false) goto L314;
        if ((r317 instanceof Drawable) == false) goto L309;
        r025.setTextAndIcon(r2.text, (Drawable) r317, r13);
        goto L321
    L309:
        int r143 = r2.iconResId;
        CharSequence r318 = r2.text;
        if (r143 != 0) goto L312;
        r025.setText(r318, r13);
        goto L321
    L312:
        r025.setTextAndIcon(r318, r143, r13);
        goto L321
    L314:
        if ((r317 instanceof Drawable) == false) goto L316;
        r025.setTextAndValueAndIcon(r2.text, r2.textValue, (Drawable) r317, r13);
        goto L321
    L316:
        int r144 = r2.iconResId;
        CharSequence r319 = r2.text;
        if (r144 != 0) goto L319;
        r025.setTextAndValue(r319, r2.textValue, r13);
        goto L321
    L319:
        r025.setTextAndValueAndIcon(r319, r2.textValue, r144, r13);
        goto L321
    L346:
        TopViewCell r026 = (TopViewCell) r24.itemView;
        int r145 = r2.iconResId;
        if (r145 != 0) goto L349;
        int r146 = r2.intValue;
        if (r146 == 0) goto L355;
        r026.setEmojiSize(r146);
    L355:
        r026.setEmoji(r2.subtext.toString(), r2.textValue.toString());
    L356:
        boolean r147 = TextUtils.isEmpty(r2.animatedText);
        CharSequence r320 = r2.text;
        if (r147 == false) goto L359;
        r026.setText(r320);
        goto L399
    L359:
        r026.setText(r320, r2.animatedText);
        goto L399
    L349:
        if (r2.accent == false) goto L351;
        r026.setEmojiStatic(r145);
        goto L356
    L351:
        r026.setEmoji(r145);
        goto L356
    L360:
        ((HeaderCell) r24.itemView).setText(r2.text);
        ((HeaderCell) r24.itemView).setEnabled(r2.enabled, true);
        goto L399
    L361:
        FullscreenCustomFrameLayout r027 = (FullscreenCustomFrameLayout) r24.itemView;
        r027.setMinusHeight(r2.intValue);
        r027.setMinusPadding(BitwiseUtils.hasFlag(r2.flags, 1));
        r24.itemView.setTag(R.id.parent_tag, Boolean.valueOf(r2.transparent));
        if (r2.transparent == false) goto L365;
        r027.setBackgroundColor(0);
    L369:
        int r148 = r027.getChildCount();
        if (r2.view != null) goto L372;
        r15 = false;
    L372:
        if (r148 == r15) goto L374;
    L375:
        r027.removeAllViews();
        View r149 = r2.view;
        if (r149 == null) goto L399;
        AndroidUtilities.removeFromParent(r149);
        r027.addView(r2.view, LayoutHelper.createFrame(-1, -1.0f));
        goto L399
    L374:
        if (r027.getChildAt(0) == r2.view) goto L399;
    L365:
        if (this.dialog == false) goto L367;
        int r150 = Theme.key_dialogBackground;
    L368:
        r027.setBackgroundColor(getThemedColor(r150));
        goto L369
    L367:
        r150 = Theme.key_windowBackgroundWhite;
        goto L368
    L378:
        FrameLayout r028 = (FrameLayout) r24.itemView;
        r028.setClipChildren(!r2.checked);
        r028.setClipToPadding(!r2.checked);
        int r151 = r028.getChildCount();
        if (r2.view != null) goto L381;
        r15 = false;
    L381:
        if (r151 == r15) goto L383;
    L384:
        r028.removeAllViews();
        View r152 = r2.view;
        if (r152 == null) goto L399;
        if ((r152 instanceof EditTextCell) == false) goto L389;
        ((EditTextCell) r152).setDivider(r13);
    L389:
        AndroidUtilities.removeFromParent(r2.view);
        if (r2.transparent == true) goto L392;
        r2.view.setBackground(null);
    L392:
        if (r5 != (-1)) goto L394;
    L397:
        FrameLayout.LayoutParams r153 = LayoutHelper.createFrame(-1, r2.intValue);
    L398:
        r028.addView(r2.view, r153);
        goto L399
    L394:
        if (r5 == (-4)) goto L397;
        r153 = LayoutHelper.createFrame(-2, -2.0f);
        goto L398
    L383:
        if (r028.getChildAt(0) == r2.view) goto L399;
    L145:
        if (r2.transparent == false) goto L147;
        r24.itemView.setBackgroundColor(0);
    L150:
        r24.itemView.setId(r2.id);
        ((SpaceView) r24.itemView).setHeight(r2.intValue);
        goto L399
    L147:
        int r029 = r2.iconResId;
        if (r029 == 0) goto L150;
        r24.itemView.setBackgroundColor(r029);
        goto L150
    L192:
        if (r5 == 7) goto L205;
        if (r5 == 8) goto L205;
        if (r5 != 38) goto L204;
        CollapseTextCell r57 = (CollapseTextCell) r24.itemView;
        r57.set(r2.animatedText, r2.collapsed);
        if (r2.accent == false) goto L201;
        r57.setColor(Theme.key_windowBackgroundWhiteBlueText4);
        TextInfoPrivacyCell r58 = r57;
    L216:
        if (r3 != null) goto L218;
    L220:
        boolean r321 = false;
    L221:
        if (r1 != null) goto L223;
    L225:
        boolean r154 = false;
    L227:
        if (this.listView.hasSections() == false) goto L229;
        r58.setBackground(null);
        goto L399
    L229:
        if (r321 == false) goto L232;
        if (r154 == false) goto L232;
        int r155 = R.drawable.greydivider;
    L237:
        Drawable r156 = Theme.getThemedDrawableByKey(this.context, r155, Theme.key_windowBackgroundGrayShadow, this.resourcesProvider);
        if (this.dialog == false) goto L240;
        r58.setBackground(new LayerDrawable(new Drawable[]{new ColorDrawable(getThemedColor(Theme.key_dialogBackgroundGray)), r156}));
        goto L399
    L240:
        r58.setBackground(r156);
    L232:
        if (r321 == false) goto L234;
        r155 = R.drawable.greydivider_bottom;
        goto L237
    L234:
        if (r154 == false) goto L236;
        r155 = R.drawable.greydivider_top;
        goto L237
    L236:
        r155 = R.drawable.field_carret_empty;
        goto L237
    L223:
        if (isShadow(r1.viewType) == true) goto L225;
        r154 = true;
        goto L227
    L218:
        if (isShadow(r3.viewType) == true) goto L220;
        r321 = true;
        goto L221
    L201:
        if (r2.red == false) goto L203;
        r57.setColor(Theme.key_text_RedRegular);
        r58 = r57;
        goto L216
    L203:
        r57.setColor(Theme.key_windowBackgroundWhiteBlackText);
        r58 = r57;
        goto L216
    L204:
        r58 = null;
    L205:
        TextInfoPrivacyCell r92 = (TextInfoPrivacyCell) r24.itemView;
        if (TextUtils.isEmpty(r2.text) == false) goto L210;
        if (r5 != 8) goto L209;
        r8 = Opcodes.REM_INT_LIT8;
    L209:
        r92.setFixedSize(r8);
        r92.setText(_UrlKt.FRAGMENT_ENCODE_SET);
    L212:
        if (r2.accent == false) goto L214;
        r92.setTextGravity(17);
        r92.getTextView().setWidth(Math.min(HintView2.cutInFancyHalf(r92.getText(), r92.getTextView().getPaint()), AndroidUtilities.displaySize.x - AndroidUtilities.dp(60.0f)));
        r92.getTextView().setPadding(0, AndroidUtilities.dp(17.0f), 0, AndroidUtilities.dp(17.0f));
    L215:
        r58 = r92;
        goto L216
    L214:
        r92.setTextGravity(PlatformMediaRouter1RouteProvider.ALL_ROUTE_TYPES);
        r92.getTextView().setMinWidth(0);
        r92.getTextView().setMaxWidth(AndroidUtilities.displaySize.x);
        r92.getTextView().setPadding(0, AndroidUtilities.dp(10.0f), 0, AndroidUtilities.dp(17.0f));
        goto L215
    L210:
        r92.setFixedSize(0);
        r92.setText(r2.text);
        goto L212
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r13, int r14) {
        boolean r132 = this.dialog;
        if (r132 == false) goto L5;
        int r0 = Theme.key_dialogBackground;
    L7:
        if (r14 < UItem.factoryViewTypeStartsWith) goto L12;
        UItem.UItemFactory<?> r2 = UItem.findFactory(r14);
        if (r2 == null) goto L11;
        View r133 = r2.createView(this.context, this.listView, this.currentAccount, this.classGuid, this.resourcesProvider);
    L93:
        if (shouldApplyBackground(r14) == false) goto L96;
        r133.setBackgroundColor(getThemedColor(r0));
    L96:
        return new RecyclerListView.Holder(r133);
    L11:
        r133 = new View(this.context);
        goto L93
    L12:
        int r1 = 6;
        boolean r22 = true;
        switch(r14) {
            case -4: goto L89;
            case -3: goto L88;
            case -2: goto L87;
            case -1: goto L89;
            case 0: goto L84;
            case 1: goto L83;
            case 2: goto L82;
            case 3: goto L81;
            case 4: goto L78;
            case 5: goto L73;
            case 6: goto L73;
            case 7: goto L14;
            case 8: goto L14;
            case 9: goto L78;
            case 10: goto L72;
            case 11: goto L67;
            case 12: goto L67;
            case 13: goto L66;
            case 14: goto L65;
            case 15: goto L64;
            case 16: goto L59;
            case 17: goto L58;
            case 18: goto L55;
            case 19: goto L55;
            case 20: goto L55;
            case 21: goto L55;
            case 22: goto L55;
            case 23: goto L55;
            case 24: goto L53;
            case 25: goto L52;
            case 26: goto L50;
            case 27: goto L49;
            case 28: goto L48;
            case 29: goto L47;
            case 30: goto L46;
            case 31: goto L40;
            case 32: goto L39;
            case 33: goto L38;
            case 34: goto L37;
            case 35: goto L22;
            case 36: goto L22;
            case 37: goto L22;
            case 38: goto L20;
            case 39: goto L19;
            case 40: goto L19;
            case 41: goto L22;
            case 42: goto L17;
            case 43: goto L16;
            case 44: goto L15;
            default: goto L14;
        };
    L14:
        r133 = new TextInfoPrivacyCell(this.context, this.resourcesProvider);
        goto L93
    L15:
        r133 = new RadioButtonCell(this.context);
        goto L93
    L16:
        r133 = new TextSettingsCell(this.context, this.resourcesProvider);
        goto L93
    L17:
        View r3 = new HeaderCell(this.context, Theme.key_windowBackgroundWhiteBlueHeader, 21, 15, 0, false, true, this.resourcesProvider);
    L18:
        r133 = r3;
        goto L93
    L19:
        r133 = new TextCheckCell2(this.context);
        goto L93
    L20:
        r133 = new CollapseTextCell(this.context, this.resourcesProvider);
        goto L93
    L37:
        FlickerLoadingView r134 = new FlickerLoadingView(this.context, this.resourcesProvider);
        r134.setIsSingleCell(true);
        r133 = r134;
        goto L93
    L38:
        r133 = new DialogCell(null, this.context, false, true);
        goto L93
    L39:
        r133 = new ProfileSearchCell(this.context);
        goto L93
    L40:
        RecyclerListView r135 = this.listView;
        if (r135 != null) goto L43;
    L45:
        r133 = new GraySectionCell(this.context, this.resourcesProvider);
        goto L93
    L43:
        if (r135.hasSections() == false) goto L45;
        GraySectionCell r136 = new GraySectionCell(this.context, 28, this.resourcesProvider);
        r136.setNoBackground(true);
        r133 = r136;
        goto L93
    L46:
        r133 = new TextRightIconCell(this.context, this.resourcesProvider);
        goto L93
    L47:
        r133 = new BusinessLinksActivity.BusinessLinkView(this.context, this.resourcesProvider);
        goto L93
    L48:
        r133 = new SpaceView(this.context);
        goto L93
    L49:
        StoryPrivacyBottomSheet.UserCell r137 = new StoryPrivacyBottomSheet.UserCell(this.context, this.resourcesProvider);
        r137.setIsSendAs(false, false);
        r133 = r137;
        goto L93
    L50:
        HeaderCell r4 = new HeaderCell(this.context, Theme.key_windowBackgroundWhiteBlackText, 23, 20, 0, false, this.resourcesProvider);
        r4.setTextSize(20.0f);
    L51:
        r133 = r4;
        goto L93
    L52:
        r133 = new ChannelMonetizationLayout.TransactionCell(this.context, this.resourcesProvider);
        goto L93
    L53:
        r133 = new ChannelMonetizationLayout.ProceedOverviewCell(this.context, this.resourcesProvider);
        goto L93
    L58:
        r133 = new QuickRepliesActivity.LargeQuickReplyView(this.context, this.resourcesProvider);
        goto L93
    L59:
        Context r12 = this.context;
        if (this.onReordered != null) goto L63;
        r22 = false;
    L63:
        r133 = new QuickRepliesActivity.QuickReplyView(r12, r22, this.resourcesProvider);
        goto L93
    L64:
        r133 = new SlideIntChooseView(this.context, this.resourcesProvider);
        goto L93
    L65:
        r133 = new SlideChooseView(this.context, this.resourcesProvider);
        goto L93
    L66:
        r3 = new UserCell(this.context, 6, 0, false, true);
        goto L18
    L67:
        Context r42 = this.context;
        if (r14 != 12) goto L70;
        int r5 = 3;
    L71:
        UserCell r138 = new UserCell(r42, 6, r5, false);
        r138.setSelfAsSavedMessages(true);
        r133 = r138;
        goto L93
    L70:
        r5 = 0;
        goto L71
    L72:
        r133 = new DialogRadioCell(this.context);
        goto L93
    L73:
        Context r32 = this.context;
        if (r14 != 6) goto L76;
        boolean r7 = true;
    L77:
        View r23 = new NotificationsCheckCell(r32, 21, 60, 71, r7, this.resourcesProvider);
    L36:
        r133 = r23;
        goto L93
    L76:
        r7 = false;
        goto L77
    L78:
        TextCheckCell r33 = new TextCheckCell(this.context, this.resourcesProvider);
        r3 = r33;
        if (r14 != 9) goto L18;
        r33.setDrawCheckRipple(true);
        r33.setColors(Theme.key_windowBackgroundCheckText, Theme.key_switchTrackBlue, Theme.key_switchTrackBlueChecked, Theme.key_switchTrackBlueThumb, Theme.key_switchTrackBlueThumbChecked);
        r33.setTypeface(AndroidUtilities.bold());
        r33.setHeight(56);
        r3 = r33;
        goto L18
    L81:
        r133 = new TextCell(this.context, this.resourcesProvider);
        goto L93
    L82:
        r133 = new TopViewCell(this.context, this.resourcesProvider);
        goto L93
    L83:
        r3 = new HeaderCell(this.context, Theme.key_windowBackgroundWhiteBlackText, 17, 15, false, this.resourcesProvider);
        goto L18
    L84:
        if (r132 == false) goto L86;
        r4 = new HeaderCell(this.context, Theme.key_windowBackgroundWhiteBlueHeader, 21, 15, 0, false, this.resourcesProvider);
        goto L51
    L86:
        r133 = new HeaderCell(this.context, this.resourcesProvider);
        goto L93
    L87:
        r133 = new AnonymousClass2(this, this.context);
        goto L93
    L88:
        r133 = new FullscreenCustomFrameLayout(this.context);
        goto L93
    L89:
        View r139 = new AnonymousClass1(this, this.context);
        r133 = r139;
        if (r14 != (-4)) goto L93;
        r139.setTag(-33024);
        r133 = r139;
        goto L93
    L22:
        if (r14 != 35) goto L26;
        r1 = 4;
    L24:
        int r43 = r1;
    L35:
        CheckBoxCell r24 = new CheckBoxCell(this.context, r43, 21, true, this.resourcesProvider);
        r24.getCheckBoxRound().setColor(Theme.key_switch2TrackChecked, Theme.key_radioBackground, Theme.key_checkboxCheck);
        r23 = r24;
        goto L36
    L26:
        if (r14 == 36) goto L24;
        if (r14 != 37) goto L32;
        r1 = 7;
        goto L24
    L32:
        if (r14 != 41) goto L34;
        r1 = 8;
        goto L24
    L34:
        r43 = 0;
        goto L35
    L55:
        if (this.chartSharedUI != null) goto L57;
        this.chartSharedUI = new BaseChartView.SharedUiComponents();
    L57:
        r133 = new StatisticActivity.UniversalChartCell(this.context, this.currentAccount, r14 - 18, this.chartSharedUI, this.classGuid);
        goto L93
    L5:
        r0 = Theme.key_windowBackgroundWhite;
        goto L7
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(RecyclerView.ViewHolder r2) {
        updateReorder(r2, this.allowReorder);
        updateColors(r2, getItem(r2.getAdapterPosition()));
    }

    public void reorderDone() {
        if (this.orderChanged == false) goto L6;
        callReorder(this.orderChangedId);
        return;
    }

    public void reorderSectionEnd() {
        Section r0 = this.currentReorderSection;
        if (r0 == null) goto L14;
        int r02 = r0.start;
        int r1 = Math.max(0, this.items.size() - 1);
        if (ExteraConfig.getSectionsSeparatedHeaders() == false) goto L10;
    L6:
        if (r02 > r1) goto L10;
        if (isHeader(getItemViewType(r02)) == false) goto L10;
        r02 = r02 + 1;
    L10:
        Section r3 = this.currentReorderSection;
        r3.start = r02;
        r3.end = r1;
        return;
    }

    public int reorderSectionStart() {
        Section r0 = new Section(null);
        this.currentReorderSection = r0;
        r0.start = this.items.size();
        Section r02 = this.currentReorderSection;
        r02.end = -1;
        this.reorderSections.add(r02);
        return this.reorderSections.size() - 1;
    }

    public void setApplyBackground(boolean r1) {
        this.applyBackground = r1;
    }

    public boolean shouldApplyBackground(int r3) {
        if (this.applyBackground == true) goto L6;
        return false;
    L6:
        if (r3 < UItem.factoryViewTypeStartsWith) goto L8;
        return true;
    L8:
        switch(r3) {
            case -3: goto L10;
            case -2: goto L9;
            case -1: goto L9;
            case 0: goto L10;
            case 1: goto L10;
            case 2: goto L9;
            case 3: goto L10;
            case 4: goto L10;
            case 5: goto L10;
            case 6: goto L10;
            case 7: goto L9;
            case 8: goto L9;
            case 9: goto L10;
            case 10: goto L10;
            case 11: goto L10;
            case 12: goto L10;
            case 13: goto L10;
            case 14: goto L10;
            case 15: goto L10;
            case 16: goto L10;
            case 17: goto L10;
            case 18: goto L10;
            case 19: goto L10;
            case 20: goto L10;
            case 21: goto L10;
            case 22: goto L10;
            case 23: goto L10;
            case 24: goto L10;
            case 25: goto L10;
            case 26: goto L9;
            case 27: goto L10;
            case 28: goto L10;
            case 29: goto L10;
            case 30: goto L10;
            case 31: goto L9;
            case 32: goto L10;
            case 33: goto L10;
            case 34: goto L10;
            case 35: goto L10;
            case 36: goto L10;
            case 37: goto L10;
            case 38: goto L9;
            case 39: goto L10;
            case 40: goto L10;
            case 41: goto L10;
            case 42: goto L10;
            case 43: goto L10;
            case 44: goto L10;
            default: goto L9;
        };
    L9:
        return false;
    L10:
        return true;
    }

    public void swapElements(int r7, int r8) {
        if (this.onReordered == null) goto L23;
        int r1 = getReorderSectionId(r7);
        int r2 = getReorderSectionId(r8);
        if (r1 < 0) goto L22;
        if (r1 != r2) goto L24;
        boolean r22 = hasDivider(r7);
        boolean r3 = hasDivider(r8);
        UItem r4 = this.items.remove(r7);
        this.items.add(r8, r4);
        notifyItemMoved(r7, r8);
        if (hasDivider(r8) == r22) goto L13;
        notifyItemChanged(r8, 3);
    L13:
        if (hasDivider(r7) == r3) goto L16;
        notifyItemChanged(r7, 3);
    L16:
        if (this.orderChanged == false) goto L20;
        int r72 = this.orderChangedId;
        if (r72 == r1) goto L20;
        callReorder(r72);
    L20:
        this.orderChanged = true;
        this.orderChangedId = r1;
        return;
    L24:
        return;
    L22:
        return;
    }

    public void update(boolean r3) {
        RecyclerListView r0 = this.listView;
        if (r0 != null) goto L5;
    L8:
        updateInternal(r3);
        return;
    L5:
        if (r0.isComputingLayout() == false) goto L8;
        this.listView.post(new UniversalAdapter$$ExternalSyntheticLambda5(this, r3));
    }

    public void updateReorder(RecyclerView.ViewHolder r3, boolean r4) {
        if (r3 == null) goto L16;
        int r0 = r3.getItemViewType();
        if (r0 < UItem.factoryViewTypeStartsWith) goto L11;
        UItem.UItemFactory<?> r42 = UItem.findFactory(r0);
        if (r42 == null) goto L12;
        r42.attachedView(this.listView, r3.itemView, getItem(r3.getAdapterPosition()));
        return;
    L12:
        return;
    L11:
        if (r0 != 16) goto L15;
        ((QuickRepliesActivity.QuickReplyView) r3.itemView).setReorder(r4);
        return;
    L15:
        return;
    }

    public void updateReorder(boolean r1) {
        this.allowReorder = r1;
    }

    public void updateWithoutNotify() {
        this.oldItems.clear();
        this.oldItems.addAll(this.items);
        this.items.clear();
        this.whiteSections.clear();
        this.reorderSections.clear();
        Utilities.Callback2<ArrayList<UItem>, UniversalAdapter> r0 = this.fillItems;
        if (r0 == null) goto L5;
        r0.run(this.items, this);
    L5:
        updateReorderSections();
    }

    public void whiteSectionEnd() {
        Section r0 = this.currentWhiteSection;
        if (r0 == null) goto L17;
        int r02 = r0.start;
        int r1 = Math.max(0, (this.itemsOffset + this.items.size()) - 1);
        if (ExteraConfig.getSectionsSeparatedHeaders() == false) goto L10;
    L6:
        if (r02 > r1) goto L10;
        if (isHeader(getItemViewType(r02)) == false) goto L10;
        r02 = r02 + 1;
    L10:
        Section r2 = this.currentWhiteSection;
        r2.start = r02;
        r2.end = r1;
        if (r02 < r1) goto L13;
        this.whiteSections.remove(r2);
    L13:
        this.currentWhiteSection = null;
        return;
    }

    public void whiteSectionStart() {
        Section r0 = new Section(null);
        this.currentWhiteSection = r0;
        r0.start = this.itemsOffset + this.items.size();
        Section r02 = this.currentWhiteSection;
        r02.end = -1;
        this.whiteSections.add(r02);
    }
}
