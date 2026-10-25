package org.telegram.ui.Components.Premium.boosts.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ShadowSectionCell;
import org.telegram.ui.Components.ListView.AdapterWithDiffUtils;
import org.telegram.ui.Components.Premium.boosts.BoostRepository;
import org.telegram.ui.Components.Premium.boosts.cells.AddChannelCell;
import org.telegram.ui.Components.Premium.boosts.cells.BoostTypeCell;
import org.telegram.ui.Components.Premium.boosts.cells.BoostTypeSingleCell;
import org.telegram.ui.Components.Premium.boosts.cells.ChatCell;
import org.telegram.ui.Components.Premium.boosts.cells.DateEndCell;
import org.telegram.ui.Components.Premium.boosts.cells.DurationCell;
import org.telegram.ui.Components.Premium.boosts.cells.EnterPrizeCell;
import org.telegram.ui.Components.Premium.boosts.cells.HeaderCell;
import org.telegram.ui.Components.Premium.boosts.cells.ParticipantsTypeCell;
import org.telegram.ui.Components.Premium.boosts.cells.SliderCell;
import org.telegram.ui.Components.Premium.boosts.cells.StarGiveawayOptionCell;
import org.telegram.ui.Components.Premium.boosts.cells.SubtitleWithCounterCell;
import org.telegram.ui.Components.Premium.boosts.cells.SwitcherCell;
import org.telegram.ui.Components.Premium.boosts.cells.TextInfoCell;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SlideChooseView;
import org.telegram.ui.Stars.StarsIntroActivity;

/* loaded from: classes7.dex */
public class BoostAdapter extends AdapterWithDiffUtils {
    private EnterPrizeCell.AfterTextChangedListener afterTextChangedListener;
    private ChatCell.ChatDeleteListener chatDeleteListener;
    private HashMap<Long, Integer> chatsParticipantsCount;
    private TLRPC.Chat currentChat;
    private HeaderCell headerCell;
    private List<Item> items;
    private RecyclerListView recyclerListView;
    private final Theme.ResourcesProvider resourcesProvider;
    private SlideChooseView.Callback sliderCallback;

    public static class Item extends AdapterWithDiffUtils.Item {
        public boolean boolValue;
        public TLRPC.Chat chat;
        public float floatValue;
        public int intValue;
        public int intValue2;
        public int intValue3;
        public long longValue;
        public Object object;
        public TLRPC.InputPeer peer;
        public int subType;
        public CharSequence text;
        public Object user;
        public List<Integer> values;

        private Item(int r1, boolean r2) {
            super(r1, r2);
        }

        public static Item asAddChannel() {
            return new Item(8, false);
        }

        public static Item asBoost(int r2, int r3, Object r4, int r5) {
            if (r5 != r2) goto L5;
            boolean r52 = true;
        L6:
            Item r0 = new Item(2, r52);
            r0.subType = r2;
            r0.intValue = r3;
            r0.user = r4;
            return r0;
        L5:
            r52 = false;
            goto L6
        }

        public static Item asChat(TLRPC.Chat r3, boolean r4, int r5) {
            Item r0 = new Item(9, false);
            r0.chat = r3;
            r0.peer = null;
            r0.boolValue = r4;
            r0.intValue = r5;
            return r0;
        }

        public static Item asDateEnd(long r3) {
            Item r0 = new Item(10, false);
            r0.longValue = r3;
            return r0;
        }

        public static Item asDivider() {
            return new Item(4, false);
        }

        public static Item asDivider(CharSequence r3, boolean r4) {
            Item r0 = new Item(7, false);
            r0.text = r3;
            r0.boolValue = r4;
            return r0;
        }

        public static Item asDuration(Object r2, int r3, int r4, long r5, int r7, String r8, boolean r9) {
            if (r3 != r7) goto L5;
            boolean r72 = true;
        L6:
            Item r0 = new Item(12, r72);
            r0.intValue = r3;
            r0.intValue2 = r4;
            r0.longValue = r5;
            r0.boolValue = r9;
            r0.text = r8;
            r0.object = r2;
            return r0;
        L5:
            r72 = false;
            goto L6
        }

        public static Item asEnterPrize(int r3) {
            Item r0 = new Item(16, false);
            r0.intValue = r3;
            return r0;
        }

        public static Item asExpandOptions() {
            return new Item(18, false);
        }

        public static Item asHeader(boolean r2) {
            Item r0 = new Item(0, false);
            r0.boolValue = r2;
            return r0;
        }

        public static Item asOption(TL_stars.TL_starsGiveawayOption r2, int r3, long r4, boolean r6, boolean r7) {
            Item r0 = new Item(17, r6);
            r0.intValue = r3;
            r0.longValue = r4;
            r0.object = r2;
            r0.boolValue = r7;
            return r0;
        }

        public static Item asParticipants(int r2, int r3, boolean r4, List<TLObject> r5) {
            if (r3 != r2) goto L5;
            boolean r32 = true;
        L6:
            Item r0 = new Item(11, r32);
            r0.subType = r2;
            r0.boolValue = r4;
            r0.user = r5;
            return r0;
        L5:
            r32 = false;
            goto L6
        }

        public static Item asPeer(TLRPC.InputPeer r3, boolean r4, int r5) {
            Item r0 = new Item(9, false);
            r0.peer = r3;
            r0.chat = null;
            r0.boolValue = r4;
            r0.intValue = r5;
            return r0;
        }

        public static Item asSingleBoost(Object r3) {
            Item r0 = new Item(14, false);
            r0.user = r3;
            return r0;
        }

        public static Item asSlider(List<Integer> r3, int r4) {
            Item r0 = new Item(5, false);
            r0.values = r3;
            r0.intValue = r4;
            return r0;
        }

        public static Item asSubTitle(CharSequence r3) {
            Item r0 = new Item(6, false);
            r0.text = r3;
            return r0;
        }

        public static Item asSubTitleWithCounter(CharSequence r3, int r4) {
            Item r0 = new Item(13, false);
            r0.text = r3;
            r0.intValue = r4;
            return r0;
        }

        public static Item asSwitcher(CharSequence r2, boolean r3, boolean r4, int r5) {
            Item r0 = new Item(15, r3);
            r0.text = r2;
            r0.boolValue = r4;
            r0.subType = r5;
            return r0;
        }

        public static boolean eq(List<Integer> r5, List<Integer> r6) {
            if (r5 != null) goto L7;
            if (r6 != null) goto L7;
            return true;
        L7:
            if (r5 == null) goto L21;
            if (r6 == null) goto L21;
            if (r5.size() == r6.size()) goto L13;
            return false;
        L13:
            int r2 = 0;
        L15:
            if (r2 >= r5.size()) goto L20;
            if (r5.get(r2).intValue() != r6.get(r2).intValue()) goto L18;
            r2 = r2 + 1;
            goto L15
        L18:
            return false;
        L20:
            return true;
        L21:
            return false;
        }

        @Override // org.telegram.ui.Components.ListView.AdapterWithDiffUtils.Item
        public boolean contentsEquals(AdapterWithDiffUtils.Item r7) {
            if (this != r7) goto L6;
            return true;
        L6:
            if (r7 != null) goto L8;
        L47:
            return false;
        L8:
            if (getClass() != r7.getClass()) goto L47;
            Item r72 = (Item) r7;
            int r2 = r72.viewType;
            int r3 = this.viewType;
            if (r2 == r3) goto L13;
            return false;
        L13:
            if (r3 != 0) goto L19;
            if (this.boolValue != r72.boolValue) goto L17;
            return true;
        L17:
            return false;
        L19:
            if (r2 != 17) goto L33;
            if (this.intValue == r72.intValue) goto L23;
        L31:
            return false;
        L23:
            if (this.longValue != r72.longValue) goto L31;
            if (this.object != r72.object) goto L31;
            if (this.boolValue != r72.boolValue) goto L31;
            if (this.selectable != r72.selectable) goto L31;
            return true;
        L33:
            if (r3 != 5) goto L41;
            if (this.intValue == r72.intValue) goto L37;
        L39:
            return false;
        L37:
            if (eq(this.values, r72.values) == false) goto L39;
            return true;
        L41:
            if (r3 != 13) goto L47;
            if (this.intValue != r72.intValue) goto L47;
            if (TextUtils.equals(this.text, r72.text) == false) goto L47;
            return true;
        }

        public boolean equals(Object r7) {
            if (this != r7) goto L6;
            return true;
        L6:
            if (r7 != null) goto L8;
        L57:
            return false;
        L8:
            if (getClass() != r7.getClass()) goto L57;
            Item r72 = (Item) r7;
            int r2 = this.viewType;
            if (r2 == r72.viewType) goto L13;
            return false;
        L13:
            if (r2 != 0) goto L16;
            return true;
        L16:
            if (r2 != 17) goto L24;
            if (this.intValue == r72.intValue) goto L20;
        L22:
            return false;
        L20:
            if (this.object != r72.object) goto L22;
            return true;
        L24:
            if (r2 != 5) goto L28;
            return eq(this.values, r72.values);
        L28:
            if (r2 != 13) goto L32;
            return TextUtils.equals(this.text, r72.text);
        L32:
            if (this.chat != r72.chat) goto L57;
            if (this.user != r72.user) goto L57;
            if (this.peer != r72.peer) goto L57;
            if (this.object != r72.object) goto L57;
            if (this.boolValue != r72.boolValue) goto L57;
            if (this.intValue != r72.intValue) goto L57;
            if (this.intValue2 != r72.intValue2) goto L57;
            if (this.intValue3 != r72.intValue3) goto L57;
            if (this.longValue != r72.longValue) goto L57;
            if (this.subType != r72.subType) goto L57;
            if (this.floatValue != r72.floatValue) goto L57;
            if (TextUtils.equals(this.text, r72.text) == false) goto L57;
            return true;
        }
    }

    /* renamed from: $r8$lambda$_KblwMF8aSHLWo_-couIjYYUsuM, reason: not valid java name */
    public static /* synthetic */ void m12154$r8$lambda$_KblwMF8aSHLWo_couIjYYUsuM(BoostAdapter r0, HashMap r1) {
        r0.lambda$new$0(r1);
    }

    public BoostAdapter(Theme.ResourcesProvider r2) {
        this.items = new ArrayList();
        this.chatsParticipantsCount = new HashMap();
        this.resourcesProvider = r2;
        BoostRepository.loadParticipantsCount(new BoostAdapter$$ExternalSyntheticLambda0(this));
    }

    private int getParticipantsCount(TLRPC.Chat r4) {
        TLRPC.ChatFull r0 = MessagesController.getInstance(UserConfig.selectedAccount).getChatFull(r4.id);
        if (r0 == null) goto L8;
        int r02 = r0.participants_count;
        if (r02 <= 0) goto L8;
        return r02;
    L8:
        if (this.chatsParticipantsCount.isEmpty() == true) goto L14;
        Integer r3 = this.chatsParticipantsCount.get(Long.valueOf(r4.id));
        if (r3 == null) goto L14;
        return r3.intValue();
    L14:
        return r4.participants_count;
    }

    private /* synthetic */ void lambda$new$0(HashMap r2) {
        this.chatsParticipantsCount.clear();
        this.chatsParticipantsCount.putAll(r2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int r1) {
        return this.items.get(r1).viewType;
    }

    @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
    public boolean isEnabled(RecyclerView.ViewHolder r1) {
        int r0 = r1.getItemViewType();
        if (r0 != 2) goto L5;
        return true;
    L5:
        if (r0 != 11) goto L7;
        return true;
    L7:
        if (r0 != 8) goto L9;
        return true;
    L9:
        if (r0 != 10) goto L11;
        return true;
    L11:
        if (r0 != 15) goto L13;
        return true;
    L13:
        if (r0 != 12) goto L15;
        return true;
    L15:
        if (r0 != 17) goto L17;
        return true;
    L17:
        if (r0 == 18) goto L29;
        return false;
    L29:
        return true;
    }

    public void notifyAdditionalPrizeItem(boolean r5) {
        int r0 = 0;
    L4:
        if (r0 >= this.items.size()) goto L15;
        Item r1 = this.items.get(r0);
        if (r1.viewType != 15) goto L14;
        if (r1.subType != SwitcherCell.TYPE_ADDITION_PRIZE) goto L14;
        if (r5 == false) goto L12;
        notifyItemInserted(r0 + 1);
        return;
    L12:
        notifyItemRemoved(r0 + 1);
        return;
    L14:
        r0 = r0 + 1;
        goto L4
    }

    public void notifyAllVisibleTextDividers() {
        int r0 = 0;
    L4:
        if (r0 >= this.items.size()) goto L9;
        if (this.items.get(r0).viewType != 7) goto L8;
        notifyItemChanged(r0);
    L8:
        r0 = r0 + 1;
        goto L4
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder r10, int r11) {
        int r0 = r10.getItemViewType();
        Item r112 = this.items.get(r11);
        if (r0 != 0) goto L5;
        HeaderCell r102 = (HeaderCell) r10.itemView;
        this.headerCell = r102;
        r102.setBoostViaGifsText(this.currentChat);
        this.headerCell.setStars(r112.boolValue);
        return;
    L5:
        if (r0 != 2) goto L7;
        ((BoostTypeCell) r10.itemView).setType(r112.subType, r112.intValue, (TLRPC.User) r112.user, r112.selectable);
        return;
    L7:
        if (r0 != 5) goto L9;
        SliderCell r103 = (SliderCell) r10.itemView;
        r103.setValues(r112.values, r112.intValue);
        r103.setCallBack(this.sliderCallback);
        return;
    L9:
        if (r0 != 6) goto L11;
        ((org.telegram.ui.Cells.HeaderCell) r10.itemView).setText(r112.text);
        return;
    L11:
        if (r0 == 7) goto L46;
        switch(r0) {
            case 9: goto L35;
            case 10: goto L33;
            case 11: goto L31;
            case 12: goto L29;
            case 13: goto L27;
            case 14: goto L25;
            case 15: goto L23;
            case 16: goto L21;
            case 17: goto L14;
            default: goto L13;
        };
    L13:
        return;
    L14:
        StarGiveawayOptionCell r02 = (StarGiveawayOptionCell) r10.itemView;
        Object r9 = r112.object;
        if (r9 != null) goto L18;
        TL_stars.TL_starsGiveawayOption r92 = null;
    L17:
        TL_stars.TL_starsGiveawayOption r1 = r92;
        r02.setOption(r1, r112.intValue, r112.longValue, r112.selectable, r112.boolValue);
        return;
    L18:
        r92 = (TL_stars.TL_starsGiveawayOption) r9;
        goto L17
    L21:
        EnterPrizeCell r104 = (EnterPrizeCell) r10.itemView;
        r104.setCount(r112.intValue);
        r104.setAfterTextChangedListener(this.afterTextChangedListener);
        return;
    L23:
        ((SwitcherCell) r10.itemView).setData(r112.text, r112.selectable, r112.boolValue, r112.subType);
        return;
    L25:
        ((BoostTypeSingleCell) r10.itemView).setGiveaway((TL_stories.PrepaidGiveaway) r112.user);
        return;
    L27:
        SubtitleWithCounterCell r93 = (SubtitleWithCounterCell) r10.itemView;
        r93.setText(r112.text);
        r93.updateCounter(true, r112.intValue);
        return;
    L29:
        ((DurationCell) r10.itemView).setDuration(r112.object, r112.intValue, r112.intValue2, r112.longValue, r112.text, r112.boolValue, r112.selectable);
        return;
    L31:
        ((ParticipantsTypeCell) r10.itemView).setType(r112.subType, r112.selectable, r112.boolValue, (List) r112.user, this.currentChat);
        return;
    L33:
        ((DateEndCell) r10.itemView).setDate(r112.longValue);
        return;
    L35:
        ChatCell r105 = (ChatCell) r10.itemView;
        TLRPC.InputPeer r03 = r112.peer;
        if (r03 != null) goto L38;
        TLRPC.Chat r04 = r112.chat;
        r105.setChat(r04, r112.intValue, r112.boolValue, getParticipantsCount(r04));
    L44:
        r105.setChatDeleteListener(this.chatDeleteListener);
        return;
    L38:
        if ((r03 instanceof TLRPC.TL_inputPeerChat) == false) goto L41;
        TLRPC.Chat r05 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(r03.chat_id));
        r105.setChat(r05, r112.intValue, r112.boolValue, getParticipantsCount(r05));
        goto L44
    L41:
        if ((r03 instanceof TLRPC.TL_inputPeerChannel) == false) goto L44;
        TLRPC.Chat r06 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(r03.channel_id));
        r105.setChat(r06, r112.intValue, r112.boolValue, getParticipantsCount(r06));
        goto L44
    L46:
        TextInfoCell r94 = (TextInfoCell) r10.itemView;
        r94.setText(r112.text);
        r94.setBackground(r112.boolValue);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r9, int r10) {
        Context r1 = r9.getContext();
        switch(r10) {
            case 2: goto L21;
            case 3: goto L20;
            case 4: goto L19;
            case 5: goto L18;
            case 6: goto L17;
            case 7: goto L16;
            case 8: goto L15;
            case 9: goto L14;
            case 10: goto L13;
            case 11: goto L12;
            case 12: goto L11;
            case 13: goto L10;
            case 14: goto L9;
            case 15: goto L8;
            case 16: goto L7;
            case 17: goto L6;
            case 18: goto L5;
            default: goto L4;
        };
    L4:
        View r92 = new HeaderCell(r1, this.resourcesProvider);
    L22:
        r92.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
        return new RecyclerListView.Holder(r92);
    L5:
        StarsIntroActivity.ExpandView r93 = new StarsIntroActivity.ExpandView(r1, this.resourcesProvider);
        r93.set(LocaleController.getString(R.string.NotifyMoreOptions), true, true, false);
        r92 = r93;
        goto L22
    L6:
        r92 = new StarGiveawayOptionCell(r1, this.resourcesProvider);
        goto L22
    L7:
        r92 = new EnterPrizeCell(r1, this.resourcesProvider);
        goto L22
    L8:
        SwitcherCell r94 = new SwitcherCell(r1, this.resourcesProvider);
        r94.setHeight(50);
        r92 = r94;
        goto L22
    L9:
        r92 = new BoostTypeSingleCell(r1, this.resourcesProvider);
        goto L22
    L10:
        View r95 = new SubtitleWithCounterCell(r1, this.resourcesProvider);
        r95.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground, this.resourcesProvider));
        r92 = r95;
        goto L22
    L11:
        r92 = new DurationCell(r1, this.resourcesProvider);
        goto L22
    L12:
        r92 = new ParticipantsTypeCell(r1, this.resourcesProvider);
        goto L22
    L13:
        r92 = new DateEndCell(r1, this.resourcesProvider);
        goto L22
    L14:
        r92 = new ChatCell(r1, this.resourcesProvider);
        goto L22
    L15:
        r92 = new AddChannelCell(r1, this.resourcesProvider);
        goto L22
    L16:
        r92 = new TextInfoCell(r1, this.resourcesProvider);
        goto L22
    L17:
        View r0 = new org.telegram.ui.Cells.HeaderCell(r1, Theme.key_windowBackgroundWhiteBlueHeader, 21, 15, 3, false, this.resourcesProvider);
        r0.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground, this.resourcesProvider));
        r92 = r0;
        goto L22
    L18:
        r92 = new SliderCell(r1, this.resourcesProvider);
        goto L22
    L19:
        r92 = new ShadowSectionCell(r1, 12, Theme.getColor(Theme.key_windowBackgroundGray, this.resourcesProvider));
        goto L22
    L20:
        r92 = new View(r1);
        goto L22
    L21:
        r92 = new BoostTypeCell(r1, this.resourcesProvider);
        goto L22
    }

    public void setItems(TLRPC.Chat r1, List<Item> r2, RecyclerListView r3, SlideChooseView.Callback r4, ChatCell.ChatDeleteListener r5, EnterPrizeCell.AfterTextChangedListener r6) {
        this.items = r2;
        this.currentChat = r1;
        this.recyclerListView = r3;
        this.sliderCallback = r4;
        this.chatDeleteListener = r5;
        this.afterTextChangedListener = r6;
    }

    public void setPausedStars(boolean r1) {
        HeaderCell r0 = this.headerCell;
        if (r0 == null) goto L6;
        r0.setPaused(r1);
        return;
    }

    public void updateBoostCounter(int r5) {
        int r0 = 0;
    L4:
        if (r0 >= this.recyclerListView.getChildCount()) goto L12;
        View r1 = this.recyclerListView.getChildAt(r0);
        if ((r1 instanceof SubtitleWithCounterCell) == false) goto L9;
        ((SubtitleWithCounterCell) r1).updateCounter(true, r5);
    L9:
        if ((r1 instanceof ChatCell) == false) goto L11;
        ChatCell r12 = (ChatCell) r1;
        r12.setCounter(r5, getParticipantsCount(r12.getChat()));
    L11:
        r0 = r0 + 1;
        goto L4
    L12:
        notifyItemChanged(8);
        notifyItemRangeChanged(this.items.size() - 12, 12);
    }
}
