package org.telegram.ui.Components.Premium.boosts.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.HashMap;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.TextCell;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ListView.AdapterWithDiffUtils;
import org.telegram.ui.Components.Premium.boosts.BoostRepository;
import org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorCountryCell;
import org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorLetterCell;
import org.telegram.ui.Components.Premium.boosts.cells.selector.SelectorUserCell;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.StickerEmptyView;

/* loaded from: classes7.dex */
public class SelectorAdapter extends AdapterWithDiffUtils {
    private boolean callButtonsVisible;
    private HashMap<Long, Integer> chatsParticipantsCount;
    private final Context context;
    private boolean isGreenSelector;
    private List<Item> items;
    private RecyclerListView listView;
    public boolean needChecks;
    public boolean needChecks2;
    private final Theme.ResourcesProvider resourcesProvider;
    private final boolean sections;
    private GraySectionCell topSectionCell;
    private View.OnClickListener topSectionClickListener;

    public static class Item extends AdapterWithDiffUtils.Item {
        public View.OnClickListener audioCall;
        public View.OnClickListener callback;
        public TLRPC.Chat chat;
        public boolean checked;
        public TLRPC.TL_help_country country;
        public Drawable icon;
        public int id;
        public View.OnClickListener options;
        public int padHeight;
        public TLRPC.InputPeer peer;
        public int resId;
        public CharSequence subtext;
        public CharSequence text;
        public int type;
        public TLRPC.User user;
        public View.OnClickListener videoCall;
        public View view;

        private Item(int r1, boolean r2) {
            super(r1, r2);
            this.padHeight = -1;
        }

        public static Item asButton(int r3, int r4, String r5) {
            Item r0 = new Item(9, false);
            r0.id = r3;
            r0.resId = r4;
            r0.text = r5;
            return r0;
        }

        public static Item asChat(TLRPC.Chat r3, boolean r4) {
            Item r0 = new Item(3, true);
            r0.chat = r3;
            r0.user = null;
            r0.peer = null;
            r0.checked = r4;
            return r0;
        }

        public static Item asCountry(TLRPC.TL_help_country r3, boolean r4) {
            Item r0 = new Item(6, true);
            r0.country = r3;
            r0.checked = r4;
            return r0;
        }

        public static Item asCustom(View r3) {
            Item r0 = new Item(10, false);
            r0.view = r3;
            return r0;
        }

        public static Item asCustomUser(int r3, Drawable r4, CharSequence r5, CharSequence r6) {
            Item r0 = new Item(3, true);
            r0.id = r3;
            r0.icon = r4;
            r0.text = r5;
            r0.subtext = r6;
            return r0;
        }

        public static Item asLetter(String r3) {
            Item r0 = new Item(7, false);
            r0.text = r3;
            return r0;
        }

        public static Item asNoUsers() {
            return new Item(5, false);
        }

        public static Item asPad(int r3) {
            Item r0 = new Item(-1, false);
            r0.padHeight = r3;
            return r0;
        }

        public static Item asPeer(TLRPC.InputPeer r3, boolean r4) {
            Item r0 = new Item(3, true);
            r0.peer = r3;
            r0.user = null;
            r0.chat = null;
            r0.checked = r4;
            return r0;
        }

        public static Item asTopSection(CharSequence r3) {
            Item r0 = new Item(8, false);
            r0.text = r3;
            return r0;
        }

        public static Item asUser(TLRPC.User r3, boolean r4) {
            Item r0 = new Item(3, true);
            r0.user = r3;
            r0.peer = null;
            r0.chat = null;
            r0.checked = r4;
            return r0;
        }

        @Override // org.telegram.ui.Components.ListView.AdapterWithDiffUtils.Item
        public boolean contentsEquals(AdapterWithDiffUtils.Item r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L29:
            return false;
        L8:
            if (getClass() != r5.getClass()) goto L29;
            Item r52 = (Item) r5;
            if (this.checked == r52.checked) goto L14;
            return false;
        L14:
            if (this.viewType == 8) goto L16;
            return true;
        L16:
            if (TextUtils.equals(this.subtext, r52.subtext) == true) goto L18;
        L27:
            return false;
        L18:
            if (this.callback != null) goto L20;
            boolean r4 = true;
        L22:
            if (r52.callback != null) goto L24;
            boolean r53 = true;
        L25:
            if (r4 != r53) goto L27;
            return true;
        L24:
            r53 = false;
            goto L25
        L20:
            r4 = false;
            goto L22
        }

        public boolean equals(Object r7) {
            if (this != r7) goto L6;
            return true;
        L6:
            if (r7 != null) goto L8;
        L55:
            return false;
        L8:
            if (getClass() != r7.getClass()) goto L55;
            Item r72 = (Item) r7;
            int r2 = this.viewType;
            if (r2 == r72.viewType) goto L14;
            return false;
        L14:
            if (r2 != (-1)) goto L19;
            if (this.padHeight == r72.padHeight) goto L19;
            return false;
        L19:
            if (r2 == 3) goto L21;
        L25:
            int r22 = this.viewType;
            if (r22 != 6) goto L31;
            if (this.country == r72.country) goto L31;
            return false;
        L31:
            if (r22 != 7) goto L36;
            if (TextUtils.equals(this.text, r72.text) == true) goto L36;
            return false;
        L36:
            if (this.viewType != 8) goto L41;
            if (TextUtils.equals(this.text, r72.text) == true) goto L41;
            return false;
        L41:
            if (this.viewType != 9) goto L50;
            if (TextUtils.equals(this.text, r72.text) == true) goto L45;
        L48:
            return false;
        L45:
            if (this.id != r72.id) goto L48;
            if (this.resId != r72.resId) goto L48;
        L50:
            if (this.viewType == 10) goto L52;
        L54:
            return true;
        L52:
            if (this.view == r72.view) goto L54;
            return false;
        L21:
            if (getDialogId() == r72.getDialogId()) goto L23;
        L24:
            return false;
        L23:
            if (this.type == r72.type) goto L25;
            goto L24
        }

        public long getDialogId() {
            TLRPC.User r0 = this.user;
            if (r0 != null) goto L5;
            TLRPC.Chat r02 = this.chat;
            if (r02 != null) goto L9;
            TLRPC.InputPeer r2 = this.peer;
            if (r2 != null) goto L13;
            return 0;
        L13:
            return DialogObject.getPeerDialogId(r2);
        L9:
            return -r02.id;
        L5:
            return r0.id;
        }

        public Item withCall(View.OnClickListener r1, View.OnClickListener r2) {
            this.audioCall = r1;
            this.videoCall = r2;
            return this;
        }

        public Item withOptions(View.OnClickListener r1) {
            this.options = r1;
            return this;
        }

        public Item withRightText(String r1, View.OnClickListener r2) {
            this.subtext = r1;
            this.callback = r2;
            return this;
        }
    }

    public static /* synthetic */ void $r8$lambda$EPXHbAwxN7Qx15uRhj1dCa3KOnM(boolean r1, View r2) {
        if ((r2 instanceof SelectorUserCell) == false) goto L6;
        ((SelectorUserCell) r2).setCallButtonsVisible(r1, true);
        return;
    }

    public static /* synthetic */ void $r8$lambda$f9wwx2W_KCouT8xHnYEWA1kxNe8(SelectorAdapter r0, HashMap r1) {
        r0.lambda$new$0(r1);
    }

    public SelectorAdapter(Context r2, boolean r3, Theme.ResourcesProvider r4) {
        this(r2, r3, true, r4);
    }

    public SelectorAdapter(Context r2, boolean r3, boolean r4, Theme.ResourcesProvider r5) {
        this.chatsParticipantsCount = new HashMap();
        this.callButtonsVisible = true;
        this.context = r2;
        this.needChecks = r3;
        this.resourcesProvider = r5;
        this.sections = r4;
        BoostRepository.loadParticipantsCount(new SelectorAdapter$$ExternalSyntheticLambda0(this));
    }

    private /* synthetic */ void lambda$new$0(HashMap r2) {
        this.chatsParticipantsCount.clear();
        this.chatsParticipantsCount.putAll(r2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<Item> r0 = this.items;
        if (r0 != null) goto L7;
        return 0;
    L7:
        return r0.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int r1) {
        List<Item> r0 = this.items;
        if (r0 == null) goto L8;
        if (r1 >= 0) goto L7;
        return -1;
    L7:
        return r0.get(r1).viewType;
    L8:
        return -1;
    }

    public int getParticipantsCount(TLRPC.Chat r4) {
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

    @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
    public boolean isEnabled(RecyclerView.ViewHolder r2) {
        if (r2.getItemViewType() != 3) goto L5;
        return true;
    L5:
        if (r2.getItemViewType() != 6) goto L7;
        return true;
    L7:
        if (r2.getItemViewType() == 9) goto L14;
        return false;
    L14:
        return true;
    }

    public void notifyChangedLast() {
        List<Item> r0 = this.items;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isEmpty() == true) goto L10;
        notifyItemChanged(this.items.size() - 1);
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder r10, int r11) {
        List<Item> r0 = this.items;
        if (r0 == null) goto L101;
        if (r11 < 0) goto L104;
        Item r02 = r0.get(r11);
        int r1 = r10.getItemViewType();
        boolean r4 = true;
        if (r1 != 3) goto L47;
        SelectorUserCell r102 = (SelectorUserCell) r10.itemView;
        Drawable r2 = r02.icon;
        if (r2 == null) goto L11;
        r102.setCustomUser(r2, r02.text, r02.subtext);
    L33:
        r102.setChecked(r02.checked, false);
        r102.setCheckboxAlpha(1.0f, false);
        int r112 = r11 + 1;
        if (r112 < this.items.size()) goto L36;
    L39:
        r102.setDivider(r4);
        if (r112 < this.items.size()) goto L42;
    L44:
        r102.setOptions(r02.options);
        r102.setCallButtons(r02.audioCall, r02.videoCall);
        r102.setCallButtonsVisible(this.callButtonsVisible, false);
        return;
    L42:
        if (this.items.get(r112).viewType != 7) goto L44;
        r102.setDivider(false);
        goto L44
    L36:
        if (this.items.get(r112).viewType == r1) goto L39;
        r4 = false;
        goto L39
    L11:
        TLRPC.User r22 = r02.user;
        if (r22 == null) goto L16;
        r102.setUser(r22);
        CharSequence r23 = r02.subtext;
        if (r23 == null) goto L33;
        r102.setSubtitle(r23);
        r102.subtitleTextView.setTextColor(Theme.getColor(Theme.key_dialogTextGray3, this.resourcesProvider));
        goto L33
    L16:
        TLRPC.Chat r24 = r02.chat;
        if (r24 == null) goto L19;
        r102.setChat(r24, getParticipantsCount(r24));
        goto L33
    L19:
        TLRPC.InputPeer r25 = r02.peer;
        if (r25 == null) goto L33;
        if ((r25 instanceof TLRPC.TL_inputPeerSelf) == false) goto L25;
        r102.setUser(UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser());
        goto L33
    L25:
        if ((r25 instanceof TLRPC.TL_inputPeerUser) == false) goto L28;
        r102.setUser(MessagesController.getInstance(UserConfig.selectedAccount).getUser(Long.valueOf(r25.user_id)));
        goto L33
    L28:
        if ((r25 instanceof TLRPC.TL_inputPeerChat) == false) goto L31;
        TLRPC.Chat r26 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(r25.chat_id));
        r102.setChat(r26, getParticipantsCount(r26));
        goto L33
    L31:
        if ((r25 instanceof TLRPC.TL_inputPeerChannel) == false) goto L33;
        TLRPC.Chat r27 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(r25.channel_id));
        r102.setChat(r27, getParticipantsCount(r27));
        goto L33
    L47:
        if (r1 != 6) goto L59;
        SelectorCountryCell r103 = (SelectorCountryCell) r10.itemView;
        if (r11 >= (this.items.size() - 1)) goto L55;
        int r113 = r11 + 1;
        if (r113 >= (this.items.size() - 1)) goto L55;
        if (this.items.get(r113).viewType == 7) goto L55;
    L56:
        r103.setCountry(r02.country, r4);
        r103.setChecked(r02.checked, false);
        return;
    L55:
        r4 = false;
        goto L56
    L59:
        if (r1 != (-1)) goto L66;
        int r9 = r02.padHeight;
        if (r9 >= 0) goto L64;
        r9 = (int) (AndroidUtilities.displaySize.y * 0.3f);
    L64:
        r10.itemView.setLayoutParams(new RecyclerView.LayoutParams(-1, r9));
        return;
    L66:
        if (r1 != 7) goto L70;
        ((SelectorLetterCell) r10.itemView).setLetter(r02.text);
        return;
    L70:
        if (r1 != 5) goto L74;
        ((StickerEmptyView) r10.itemView).stickerView.getImageReceiver().startAnimation();     // Catch: Exception -> L98
        return;
    L105:
        return;
    L74:
        if (r1 != 8) goto L87;
        GraySectionCell r104 = (GraySectionCell) r10.itemView;
        if (TextUtils.equals(r104.getText(), r02.text) == false) goto L81;
        CharSequence r114 = r02.subtext;
        if (r114 != null) goto L80;
        r114 = _UrlKt.FRAGMENT_ENCODE_SET;
    L80:
        r104.setRightText(r114, true, r02.callback);
    L84:
        this.topSectionCell = r104;
        return;
    L81:
        r104.setText(Emoji.replaceWithRestrictedEmoji(r02.text, r104.getTextView(), null));
        if (TextUtils.isEmpty(r02.subtext) == true) goto L84;
        r104.setRightText(r02.subtext, r02.callback);
        goto L84
    L87:
        if (r1 != 9) goto L91;
        TextCell r92 = (TextCell) r10.itemView;
        r92.setColors(Theme.key_windowBackgroundWhiteBlueIcon, Theme.key_windowBackgroundWhiteBlueButton);
        r92.setTextAndIcon(r02.text, r02.resId, false);
        return;
    L91:
        if (r1 != 10) goto L102;
        FrameLayout r93 = (FrameLayout) r10.itemView;
        if (r93.getChildCount() == 1) goto L95;
    L96:
        AndroidUtilities.removeFromParent(r02.view);
        r93.addView(r02.view, LayoutHelper.createFrame(-1, -2.0f));
        return;
    L95:
        if (r93.getChildAt(0) != r02.view) goto L96;
        return;
    L102:
        return;
    L104:
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r8, int r9) {
        if (r9 != (-1)) goto L6;
        View r92 = new View(this.context);
        r92.setTag(-33024);
        View r93 = r92;
    L34:
        return new RecyclerListView.Holder(r93);
    L6:
        if (r9 != 3) goto L9;
        r93 = new SelectorUserCell(this.context, this.needChecks, this.needChecks2, this.resourcesProvider, this.isGreenSelector);
        goto L34
    L9:
        if (r9 != 5) goto L12;
        StickerEmptyView r94 = new StickerEmptyView(this.context, null, 1, this.resourcesProvider);
        r94.title.setText(LocaleController.getString(R.string.NoResult));
        r94.subtitle.setText(LocaleController.getString(R.string.SearchEmptyViewFilteredSubtitle2));
        r94.linearLayout.setTranslationY(AndroidUtilities.dp(24.0f));
        r93 = r94;
        goto L34
    L12:
        if (r9 != 7) goto L17;
        SelectorLetterCell r95 = new SelectorLetterCell(this.context, this.resourcesProvider);
        r95.setTag(-33024);
        r93 = r95;
        if (this.sections == false) goto L34;
        r95.setBackground(null);
        r93 = r95;
        goto L34
    L17:
        if (r9 != 6) goto L22;
        SelectorCountryCell r96 = new SelectorCountryCell(this.context, this.resourcesProvider);
        r96.setTag(-33024);
        r93 = r96;
        if (this.sections == false) goto L34;
        r96.setBackground(null);
        r93 = r96;
        goto L34
    L22:
        if (r9 != 8) goto L27;
        GraySectionCell r97 = new GraySectionCell(this.context, this.resourcesProvider);
        r97.setTag(-33024);
        r93 = r97;
        if (this.sections == false) goto L34;
        r97.setBackground(null);
        r93 = r97;
        goto L34
    L27:
        if (r9 != 9) goto L30;
        TextCell r98 = new TextCell(this.context, this.resourcesProvider);
        r98.leftPadding = 16;
        r98.imageLeft = 19;
        r93 = r98;
        goto L34
    L30:
        if (r9 != 10) goto L32;
        r93 = new FrameLayout(this.context);
        goto L34
    L32:
        r93 = new View(this.context);
        goto L34
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(RecyclerView.ViewHolder r2) {
        super.onViewAttachedToWindow(r2);
        View r22 = r2.itemView;
        if ((r22 instanceof SelectorUserCell) == false) goto L6;
        ((SelectorUserCell) r22).setCallButtonsVisible(this.callButtonsVisible, false);
        return;
    }

    public void setCallButtonsVisible(boolean r2) {
        if (this.callButtonsVisible == r2) goto L6;
        this.callButtonsVisible = r2;
        AndroidUtilities.forEachViews(this.listView, new SelectorAdapter$$ExternalSyntheticLambda1(r2));
        return;
    }

    public void setData(List<Item> r1, RecyclerListView r2) {
        this.items = r1;
        this.listView = r2;
    }

    public void setGreenSelector(boolean r1) {
        this.isGreenSelector = r1;
    }

    public void setNeedChecks2(boolean r1) {
        this.needChecks2 = r1;
    }

    public void setTopSectionClickListener(View.OnClickListener r3) {
        this.topSectionClickListener = r3;
        GraySectionCell r2 = this.topSectionCell;
        if (r2 == null) goto L9;
        if (r3 != null) goto L7;
        r2.setRightText(null);
        return;
    L7:
        r2.setRightText(LocaleController.getString(R.string.UsersDeselectAll), true, r3);
        return;
    }
}
