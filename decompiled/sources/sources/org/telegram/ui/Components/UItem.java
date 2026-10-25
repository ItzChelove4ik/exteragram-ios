package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.view.KeyEvent;
import android.view.View;
import com.exteragram.messenger.plugins.models.SettingItem;
import com.exteragram.messenger.preferences.BasePreferencesActivity;
import com.exteragram.messenger.preferences.components.CustomPreferenceCell;
import com.exteragram.messenger.preferences.utils.SettingsRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import org.mvel2.MVEL$$ExternalSyntheticBUOutline0;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Business.BusinessLinksActivity;
import org.telegram.ui.Business.QuickRepliesController;
import org.telegram.ui.Cells.SlideIntChooseView;
import org.telegram.ui.ChannelMonetizationLayout;
import org.telegram.ui.Components.ListView.AdapterWithDiffUtils;
import org.telegram.ui.StatisticActivity;

/* loaded from: classes3.dex */
public class UItem extends AdapterWithDiffUtils.Item {
    private static LongSparseArray<UItemFactory<?>> factories = null;
    private static HashMap<Class<? extends UItemFactory<?>>, UItemFactory<?>> factoryInstances = null;
    private static int factoryViewType = 10000;
    public static int factoryViewTypeStartsWith = 10000;
    public boolean accent;
    public CharSequence animatedText;
    public Utilities.Callback<View> bind;
    public String chatType;
    public int checkBoxIconResId;
    public boolean checked;
    public View.OnClickListener clickCallback;
    public View.OnClickListener clickCallback2;
    public boolean collapsed;
    public long dialogId;
    public boolean drawLine;
    public Drawable drawable;
    public boolean enabled;
    public boolean exteraExpandableSwitch;
    public int flags;
    public float floatValue;
    public boolean hideDivider;
    public Integer iconColor;
    public int iconResId;
    public int id;
    public boolean include;
    public Utilities.Callback<Integer> intCallback;
    public int intValue;
    public boolean locked;
    public long longValue;
    public boolean multiline;
    public Object object;
    public Object object2;
    public int pad;
    public int parentSpanCount;
    public boolean prioritizeTitleOverValue;
    public boolean red;
    public boolean reordering;
    public SettingItem settingItem;
    public int spanCount;
    public CharSequence subtext;
    public View.OnClickListener switchClickCallback;
    public CharSequence text;
    public CharSequence textValue;
    public String[] texts;
    public boolean transparent;
    public View view;
    public boolean withUsername;

    public static abstract class UItemFactory<V extends View> {
        private ArrayList<V> cache;
        public final int viewType;

        public UItemFactory() {
            int r0 = UItem.m13472$$Nest$sfgetfactoryViewType();
            UItem.m13475$$Nest$sfputfactoryViewType(r0 + 1);
            this.viewType = r0;
        }

        public static void setup(UItemFactory r3) {
            if (UItem.m13471$$Nest$sfgetfactoryInstances() != null) goto L6;
            UItem.m13474$$Nest$sfputfactoryInstances(new HashMap());
        L6:
            if (UItem.m13470$$Nest$sfgetfactories() != null) goto L8;
            UItem.m13473$$Nest$sfputfactories(new LongSparseArray());
        L8:
            Class<?> r0 = r3.getClass();
            if (UItem.m13471$$Nest$sfgetfactoryInstances().containsKey(r0) == true) goto L12;
            UItem.m13471$$Nest$sfgetfactoryInstances().put(r0, r3);
            UItem.m13470$$Nest$sfgetfactories().put(r3.viewType, r3);
            return;
        }

        public void attachedView(RecyclerListView r1, View r2, UItem r3) {
        }

        public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
        }

        public boolean contentsEquals(UItem r1, UItem r2) {
            return r1.itemContentEquals(r2);
        }

        public V createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return null;
        }

        public boolean equals(UItem r1, UItem r2) {
            return r1.itemEquals(r2);
        }

        public V getCached() {
            ArrayList<V> r0 = this.cache;
            if (r0 != null) goto L5;
            return null;
        L5:
            if (r0.isEmpty() == false) goto L7;
            return null;
        L7:
            return this.cache.remove(0);
        }

        public boolean isClickable() {
            return true;
        }

        public boolean isShadow() {
            return false;
        }

        public void precache(Context r9, int r10, int r11, Theme.ResourcesProvider r12, int r13) {
            if (r9 != null) goto L5;
            return;
        L5:
            if (this.cache != null) goto L7;
            this.cache = new ArrayList();
        L7:
            int r0 = 0;
        L9:
            if (r0 >= (this.cache.size() - r13)) goto L13;
            UItemFactory<V> r2 = this;
            this.cache.add(r2.createView(r9, null, r10, r11, r12));
            r0 = r0 + 1;
            UItemFactory<V> this = r2;
            goto L9
        }

        public void precache(BaseFragment r7, int r8) {
            precache(r7.getContext(), r7.getCurrentAccount(), r7.getClassGuid(), r7.getResourceProvider(), r8);
        }
    }

    /* renamed from: -$$Nest$sfgetfactories, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m13470$$Nest$sfgetfactories() {
        return factories;
    }

    /* renamed from: -$$Nest$sfgetfactoryInstances, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m13471$$Nest$sfgetfactoryInstances() {
        return factoryInstances;
    }

    /* renamed from: -$$Nest$sfgetfactoryViewType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m13472$$Nest$sfgetfactoryViewType() {
        return factoryViewType;
    }

    /* renamed from: -$$Nest$sfputfactories, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13473$$Nest$sfputfactories(LongSparseArray r0) {
        factories = r0;
    }

    /* renamed from: -$$Nest$sfputfactoryInstances, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13474$$Nest$sfputfactoryInstances(HashMap r0) {
        factoryInstances = r0;
    }

    /* renamed from: -$$Nest$sfputfactoryViewType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13475$$Nest$sfputfactoryViewType(int r0) {
        factoryViewType = r0;
    }

    static {
    }

    public UItem(int r1, boolean r2) {
        super(r1, r2);
        this.enabled = true;
        this.spanCount = -1;
        this.drawLine = true;
        this.withUsername = true;
    }

    public static UItem asAddChat(Long r3) {
        UItem r0 = new UItem(13, false);
        r0.dialogId = r3.longValue();
        return r0;
    }

    public static UItem asAddChat(Long r3, String r4) {
        UItem r0 = new UItem(13, false);
        r0.dialogId = r3.longValue();
        r0.textValue = r4;
        return r0;
    }

    public static UItem asAnimatedHeader(int r3, CharSequence r4) {
        UItem r0 = new UItem(42, false);
        r0.id = r3;
        r0.animatedText = r4;
        return r0;
    }

    public static UItem asBlackHeader(CharSequence r3) {
        UItem r0 = new UItem(1, false);
        r0.text = r3;
        return r0;
    }

    public static UItem asBusinessChatLink(BusinessLinksActivity.BusinessLinkWrapper r3) {
        UItem r0 = new UItem(29, false);
        r0.object = r3;
        return r0;
    }

    public static UItem asButton(int r3, int r4, CharSequence r5) {
        UItem r0 = new UItem(3, false);
        r0.id = r3;
        r0.iconResId = r4;
        r0.text = r5;
        return r0;
    }

    public static UItem asButton(int r3, int r4, CharSequence r5, CharSequence r6) {
        UItem r0 = new UItem(3, false);
        r0.id = r3;
        r0.iconResId = r4;
        r0.text = r5;
        r0.textValue = r6;
        return r0;
    }

    public static UItem asButton(int r3, Drawable r4, CharSequence r5) {
        UItem r0 = new UItem(3, false);
        r0.id = r3;
        r0.object = r4;
        r0.text = r5;
        return r0;
    }

    public static UItem asButton(int r3, CharSequence r4) {
        UItem r0 = new UItem(3, false);
        r0.id = r3;
        r0.text = r4;
        return r0;
    }

    public static UItem asButton(int r3, CharSequence r4, CharSequence r5) {
        UItem r0 = new UItem(3, false);
        r0.id = r3;
        r0.text = r4;
        r0.textValue = r5;
        return r0;
    }

    public static UItem asButtonCheck(int r3, CharSequence r4, CharSequence r5) {
        UItem r0 = new UItem(5, false);
        r0.id = r3;
        r0.text = r4;
        r0.subtext = r5;
        return r0;
    }

    public static UItem asButtonWithSubtext(int r3, int r4, CharSequence r5, CharSequence r6, int r7, int r8) {
        UItem r0 = new UItem(3, false);
        r0.id = r3;
        r0.iconResId = r4;
        r0.text = r5;
        r0.subtext = r6;
        r0.pad = r7;
        r0.intValue = r8;
        return r0;
    }

    public static UItem asCenterShadow(CharSequence r3) {
        UItem r0 = new UItem(7, false);
        r0.text = r3;
        r0.accent = true;
        return r0;
    }

    public static UItem asChart(int r2, int r3, StatisticActivity.ChartViewData r4) {
        UItem r0 = new UItem(r2 + 18, false);
        r0.intValue = r3;
        r0.object = r4;
        return r0;
    }

    public static UItem asCheck(int r3, CharSequence r4) {
        UItem r0 = new UItem(4, false);
        r0.id = r3;
        r0.text = r4;
        return r0;
    }

    public static UItem asCheck(int r3, CharSequence r4, int r5) {
        UItem r0 = new UItem(4, false);
        r0.id = r3;
        r0.text = r4;
        r0.iconResId = r5;
        return r0;
    }

    public static UItem asCheck(int r3, CharSequence r4, CharSequence r5, boolean r6) {
        UItem r0 = new UItem(4, false);
        r0.id = r3;
        r0.text = r4;
        r0.textValue = r5;
        r0.multiline = r6;
        return r0;
    }

    public static UItem asCustom(int r3, View r4) {
        UItem r0 = new UItem(-1, false);
        r0.id = r3;
        r0.view = r4;
        r0.intValue = -1;
        return r0;
    }

    public static UItem asCustom(View r3) {
        UItem r0 = new UItem(-1, false);
        r0.view = r3;
        r0.intValue = -1;
        return r0;
    }

    public static UItem asCustomShadow(int r3, View r4) {
        UItem r0 = new UItem(-4, false);
        r0.id = r3;
        r0.view = r4;
        r0.intValue = -1;
        return r0;
    }

    public static UItem asCustomShadow(View r3) {
        UItem r0 = new UItem(-4, false);
        r0.view = r3;
        r0.intValue = -1;
        return r0;
    }

    public static UItem asCustomShadow(View r3, int r4) {
        UItem r0 = new UItem(-4, false);
        r0.view = r3;
        r0.intValue = r4;
        return r0;
    }

    public static UItem asCustomShadow(View r3, boolean r4) {
        UItem r0 = new UItem(-4, false);
        r0.view = r3;
        r0.intValue = -1;
        r0.checked = r4;
        return r0;
    }

    public static UItem asExpandableSwitch(int r3, CharSequence r4, CharSequence r5) {
        UItem r0 = new UItem(40, false);
        r0.id = r3;
        r0.text = r4;
        r0.animatedText = r5;
        return r0;
    }

    public static UItem asExteraExpandableSwitch(int r3, CharSequence r4, CharSequence r5, View.OnClickListener r6) {
        UItem r0 = new UItem(40, false);
        r0.id = r3;
        r0.text = r4;
        r0.animatedText = r5;
        r0.exteraExpandableSwitch = true;
        r0.switchClickCallback = r6;
        return r0;
    }

    public static UItem asFilterChat(boolean r3, long r4) {
        UItem r0 = new UItem(11, false);
        r0.include = r3;
        r0.dialogId = r4;
        return r0;
    }

    public static UItem asFlicker(int r3) {
        UItem r0 = new UItem(34, false);
        r0.intValue = r3;
        return r0;
    }

    public static UItem asFlicker(int r3, int r4) {
        UItem r0 = new UItem(34, false);
        r0.id = r3;
        r0.intValue = r4;
        return r0;
    }

    public static UItem asFullscreenCustom(View r1, int r2) {
        return asFullscreenCustom(r1, r2, false);
    }

    public static UItem asFullscreenCustom(View r3, int r4, boolean r5) {
        UItem r0 = new UItem(-3, false);
        r0.view = r3;
        r0.intValue = r4;
        r0.flags = r5 ? 1 : 0;
        return r0;
    }

    public static UItem asFullyCustom(View r3) {
        UItem r0 = new UItem(-2, false);
        r0.view = r3;
        return r0;
    }

    public static UItem asGraySection(CharSequence r3) {
        UItem r0 = new UItem(31, false);
        r0.text = r3;
        return r0;
    }

    public static UItem asGraySection(CharSequence r3, CharSequence r4, View.OnClickListener r5) {
        UItem r0 = new UItem(31, false);
        r0.text = r3;
        r0.subtext = r4;
        r0.clickCallback = r5;
        return r0;
    }

    public static UItem asHeader(int r2, CharSequence r3) {
        UItem r0 = new UItem(0, false);
        r0.id = r2;
        r0.text = r3;
        return r0;
    }

    public static UItem asHeader(CharSequence r2) {
        UItem r0 = new UItem(0, false);
        r0.text = r2;
        return r0;
    }

    public static UItem asIntSlideView(int r3, int r4, int r5, int r6, Utilities.CallbackReturn<Integer, CharSequence> r7, Utilities.Callback<Integer> r8) {
        UItem r0 = new UItem(15, false);
        r0.intValue = r5;
        r0.intCallback = r8;
        r0.object = SlideIntChooseView.Options.make(r3, r4, r6, r7);
        r0.longValue = -1;
        return r0;
    }

    public static UItem asLargeQuickReply(QuickRepliesController.QuickReply r3) {
        UItem r0 = new UItem(17, false);
        r0.object = r3;
        return r0;
    }

    public static UItem asLargeShadow(CharSequence r3) {
        UItem r0 = new UItem(8, false);
        r0.text = r3;
        return r0;
    }

    public static UItem asProceedOverview(ChannelMonetizationLayout.ProceedOverview r3) {
        UItem r0 = new UItem(24, false);
        r0.object = r3;
        return r0;
    }

    public static UItem asProfileCell(TLObject r3) {
        UItem r0 = new UItem(32, false);
        r0.object = r3;
        return r0;
    }

    public static UItem asQuickReply(QuickRepliesController.QuickReply r3) {
        UItem r0 = new UItem(16, false);
        r0.object = r3;
        return r0;
    }

    public static UItem asRadio(int r3, CharSequence r4) {
        UItem r0 = new UItem(10, false);
        r0.id = r3;
        r0.text = r4;
        return r0;
    }

    public static UItem asRadio(int r3, CharSequence r4, CharSequence r5) {
        UItem r0 = new UItem(10, false);
        r0.id = r3;
        r0.text = r4;
        r0.textValue = r5;
        return r0;
    }

    public static UItem asRadio2(int r3, CharSequence r4, CharSequence r5) {
        UItem r0 = new UItem(44, false);
        r0.id = r3;
        r0.text = r4;
        r0.textValue = r5;
        return r0;
    }

    public static UItem asRippleCheck(int r3, CharSequence r4) {
        UItem r0 = new UItem(9, false);
        r0.id = r3;
        r0.text = r4;
        return r0;
    }

    public static UItem asRoundCheckbox(int r3, CharSequence r4) {
        UItem r0 = new UItem(35, false);
        r0.id = r3;
        r0.text = r4;
        return r0;
    }

    public static UItem asRoundGroupCheckbox(int r3, CharSequence r4, CharSequence r5) {
        UItem r0 = new UItem(41, false);
        r0.id = r3;
        r0.text = r4;
        r0.animatedText = r5;
        return r0;
    }

    public static UItem asSearchMessage(int r3, MessageObject r4) {
        UItem r0 = new UItem(33, false);
        r0.id = r3;
        r0.object = r4;
        return r0;
    }

    public static UItem asSearchMessage(MessageObject r3) {
        UItem r0 = new UItem(33, false);
        r0.object = r3;
        return r0;
    }

    public static UItem asSettingsCell(int r3, int r4, CharSequence r5) {
        UItem r0 = new UItem(43, false);
        r0.id = r3;
        r0.iconResId = r4;
        r0.text = r5;
        return r0;
    }

    public static UItem asSettingsCell(int r3, int r4, CharSequence r5, CharSequence r6) {
        UItem r0 = new UItem(43, false);
        r0.id = r3;
        r0.iconResId = r4;
        r0.text = r5;
        r0.subtext = r6;
        return r0;
    }

    public static UItem asSettingsCell(int r3, CharSequence r4, CharSequence r5) {
        UItem r0 = new UItem(43, false);
        r0.id = r3;
        r0.text = r4;
        r0.subtext = r5;
        return r0;
    }

    public static UItem asShadow() {
        return new UItem(7, false);
    }

    public static UItem asShadow(int r3, CharSequence r4) {
        UItem r0 = new UItem(7, false);
        r0.id = r3;
        r0.text = r4;
        return r0;
    }

    public static UItem asShadow(CharSequence r3) {
        UItem r0 = new UItem(7, false);
        r0.text = r3;
        return r0;
    }

    public static UItem asShadowCollapseButton(int r3, CharSequence r4) {
        UItem r0 = new UItem(38, false);
        r0.id = r3;
        r0.animatedText = r4;
        return r0;
    }

    public static UItem asSlideView(int r3, String[] r4, int r5, Utilities.Callback<Integer> r6) {
        UItem r0 = new UItem(14, false);
        r0.id = r3;
        r0.texts = r4;
        r0.intValue = r5;
        r0.intCallback = r6;
        r0.longValue = -1;
        return r0;
    }

    public static UItem asSlideView(String[] r3, int r4, Utilities.Callback<Integer> r5) {
        UItem r0 = new UItem(14, false);
        r0.texts = r3;
        r0.intValue = r4;
        r0.intCallback = r5;
        r0.longValue = -1;
        return r0;
    }

    public static UItem asSpace(int r3) {
        UItem r0 = new UItem(28, false);
        r0.intValue = r3;
        return r0;
    }

    public static UItem asSpace(int r3, int r4) {
        UItem r0 = new UItem(28, false);
        r0.id = r3;
        r0.intValue = r4;
        return r0;
    }

    public static UItem asStickerButton(int r3, CharSequence r4, String r5) {
        UItem r0 = new UItem(3, false);
        r0.id = r3;
        r0.text = r4;
        r0.object = r5;
        return r0;
    }

    public static UItem asStickerButton(int r3, CharSequence r4, TLRPC.Document r5) {
        UItem r0 = new UItem(3, false);
        r0.id = r3;
        r0.text = r4;
        r0.object = r5;
        return r0;
    }

    public static UItem asSwitch(int r3, CharSequence r4) {
        UItem r0 = new UItem(39, false);
        r0.id = r3;
        r0.text = r4;
        r0.intValue = 1;
        return r0;
    }

    public static UItem asSwitchNoIcon(int r3, CharSequence r4) {
        UItem r0 = new UItem(39, false);
        r0.id = r3;
        r0.text = r4;
        r0.intValue = 0;
        return r0;
    }

    public static UItem asTopView(CharSequence r3, int r4) {
        UItem r0 = new UItem(2, false);
        r0.text = r3;
        r0.iconResId = r4;
        return r0;
    }

    public static UItem asTopView(CharSequence r3, CharSequence r4, int r5) {
        UItem r0 = new UItem(2, false);
        r0.text = r3;
        r0.animatedText = r4;
        r0.iconResId = r5;
        return r0;
    }

    public static UItem asTopView(CharSequence r3, CharSequence r4, int r5, String r6, String r7) {
        UItem r0 = new UItem(2, false);
        r0.text = r3;
        r0.animatedText = r4;
        r0.subtext = r6;
        r0.textValue = r7;
        r0.intValue = r5;
        return r0;
    }

    public static UItem asTopView(CharSequence r3, CharSequence r4, String r5, String r6) {
        UItem r0 = new UItem(2, false);
        r0.text = r3;
        r0.animatedText = r4;
        r0.subtext = r5;
        r0.textValue = r6;
        return r0;
    }

    public static UItem asUserCheckbox(int r3, TLObject r4) {
        UItem r0 = new UItem(37, false);
        r0.id = r3;
        r0.object = r4;
        return r0;
    }

    public static UItem asUserGroupCheckbox(int r3, CharSequence r4, CharSequence r5) {
        UItem r0 = new UItem(36, false);
        r0.id = r3;
        r0.text = r4;
        r0.animatedText = r5;
        return r0;
    }

    public static UItemFactory<?> findFactory(int r3) {
        LongSparseArray<UItemFactory<?>> r0 = factories;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.get(r3);
    }

    public static <F extends UItemFactory<?>> UItemFactory<?> getFactory(Class<F> r1) {
        if (factoryInstances != null) goto L6;
        factoryInstances = new HashMap();
    L6:
        if (factories != null) goto L8;
        factories = new LongSparseArray();
    L8:
        UItemFactory<?> r0 = factoryInstances.get(r1);
        if (r0 == null) goto L11;
        return r0;
    L11:
        MVEL$$ExternalSyntheticBUOutline0.m("UItemFactory was not setuped: ", r1);
        return null;
    }

    public static <F extends UItemFactory<?>> UItem ofFactory(Class<F> r2) {
        return new UItem(getFactory(r2).viewType, false);
    }

    public UItem accent() {
        this.accent = true;
        return this;
    }

    @Override // org.telegram.ui.Components.ListView.AdapterWithDiffUtils.Item
    public boolean contentsEquals(AdapterWithDiffUtils.Item r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L47:
        return false;
    L8:
        if (getClass() != r5.getClass()) goto L47;
        UItem r52 = (UItem) r5;
        int r2 = this.viewType;
        if (r2 == r52.viewType) goto L14;
        return false;
    L14:
        if (r2 != 31) goto L22;
        if (TextUtils.equals(this.text, r52.text) == true) goto L18;
    L20:
        return false;
    L18:
        if (TextUtils.equals(this.subtext, r52.subtext) == false) goto L20;
        return true;
    L22:
        if (r2 != 28) goto L28;
        if (this.intValue != r52.intValue) goto L26;
        return true;
    L26:
        return false;
    L28:
        if (r2 == 35) goto L41;
        if (r2 == 37) goto L41;
        if (r2 < factoryViewTypeStartsWith) goto L39;
        UItemFactory<?> r0 = findFactory(r2);
        if (r0 == null) goto L39;
        return r0.contentsEquals(this, r52);
    L39:
        return itemContentEquals(r52);
    L41:
        if (this.id != r52.id) goto L47;
        if (TextUtils.equals(this.text, r52.text) == false) goto L47;
        if (this.checked != r52.checked) goto L47;
        return true;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L45:
        return false;
    L8:
        if (getClass() != r5.getClass()) goto L45;
        UItem r52 = (UItem) r5;
        int r2 = this.viewType;
        if (r2 == r52.viewType) goto L14;
        return false;
    L14:
        if (r2 == 36) goto L43;
        if (r2 == 35) goto L43;
        if (r2 == 4) goto L43;
        if (r2 == 28) goto L23;
        KeyEvent.Callback r0 = r52.view;
        if ((r0 instanceof CustomPreferenceCell) == false) goto L31;
        return ((CustomPreferenceCell) r0).equals(this.view);
    L31:
        if (r2 != 31) goto L35;
        return TextUtils.equals(this.text, r52.text);
    L35:
        if (r2 < factoryViewTypeStartsWith) goto L41;
        UItemFactory<?> r02 = findFactory(r2);
        if (r02 == null) goto L41;
        return r02.equals(this, r52);
    L41:
        return itemEquals(r52);
    L23:
        if (this.id != r52.id) goto L25;
        return true;
    L25:
        return false;
    L43:
        if (this.id != r52.id) goto L45;
        return true;
    }

    public <F extends UItemFactory<?>> boolean instanceOf(Class<F> r4) {
        if (this.viewType >= factoryViewTypeStartsWith) goto L5;
        return false;
    L5:
        HashMap<Class<? extends UItemFactory<?>>, UItemFactory<?>> r0 = factoryInstances;
        if (r0 != null) goto L8;
        return false;
    L8:
        UItemFactory<?> r42 = r0.get(r4);
        if (r42 != null) goto L12;
        return false;
    L12:
        if (r42.viewType != this.viewType) goto L15;
        return true;
    L15:
        return false;
    }

    public boolean itemContentEquals(UItem r5) {
        int r0 = this.viewType;
        if (r0 != r5.viewType) goto L48;
        if (this.id == r5.id) goto L8;
        return false;
    L8:
        if (this.enabled == r5.enabled) goto L10;
        return false;
    L10:
        if (r0 == 0) goto L46;
        if (r0 == 1) goto L46;
        if (r0 == 3) goto L32;
        if (r0 == 7) goto L27;
        if (r0 == 26) goto L46;
        if (r0 != 34) goto L48;
        if (this.intValue != r5.intValue) goto L25;
        return true;
    L25:
        return false;
    L27:
        if (this.text != null) goto L46;
        if (r5.text != null) goto L46;
        return true;
    L32:
        if (this.object == r5.object) goto L34;
    L44:
        return false;
    L34:
        if (TextUtils.equals(this.text, r5.text) == false) goto L44;
        if (TextUtils.equals(this.textValue, r5.textValue) == false) goto L44;
        if (this.iconResId != r5.iconResId) goto L44;
        if (this.accent != r5.accent) goto L44;
        if (this.red != r5.red) goto L44;
        return true;
    L46:
        return TextUtils.equals(this.text, r5.text);
    L48:
        return super.contentsEquals(r5);
    }

    public boolean itemEquals(UItem r5) {
        if (this.id == r5.id) goto L5;
        return false;
    L5:
        if (this.pad == r5.pad) goto L7;
        return false;
    L7:
        if (this.dialogId == r5.dialogId) goto L9;
        return false;
    L9:
        if (this.iconResId == r5.iconResId) goto L11;
        return false;
    L11:
        if (this.hideDivider == r5.hideDivider) goto L13;
        return false;
    L13:
        if (this.transparent == r5.transparent) goto L15;
        return false;
    L15:
        if (this.red == r5.red) goto L17;
        return false;
    L17:
        if (this.locked == r5.locked) goto L19;
        return false;
    L19:
        if (this.accent == r5.accent) goto L21;
        return false;
    L21:
        if (this.view == r5.view) goto L23;
        return false;
    L23:
        if (TextUtils.equals(this.text, r5.text) == true) goto L25;
        return false;
    L25:
        if (TextUtils.equals(this.subtext, r5.subtext) == true) goto L27;
        return false;
    L27:
        if (TextUtils.equals(this.textValue, r5.textValue) == true) goto L29;
        return false;
    L29:
        if (this.view == r5.view) goto L31;
        return false;
    L31:
        if (this.intValue == r5.intValue) goto L33;
        return false;
    L33:
        if (Math.abs(this.floatValue - r5.floatValue) < 0.01f) goto L35;
        return false;
    L35:
        if (this.longValue == r5.longValue) goto L37;
        return false;
    L37:
        if (this.drawable == r5.drawable) goto L39;
        return false;
    L39:
        if (Objects.equals(this.object, r5.object) == true) goto L41;
        return false;
    L41:
        if (Objects.equals(this.object2, r5.object2) == false) goto L64;
        return true;
    L64:
        return false;
    }

    public UItem onBind(Utilities.Callback<View> r1) {
        this.bind = r1;
        return this;
    }

    public UItem pad() {
        this.pad = 1;
        return this;
    }

    public UItem prioritizeTitleOverValue(boolean r1) {
        this.prioritizeTitleOverValue = r1;
        return this;
    }

    public UItem red() {
        this.red = true;
        return this;
    }

    public UItem setCheckBoxIcon(int r1) {
        this.checkBoxIconResId = r1;
        return this;
    }

    public UItem setChecked(boolean r2) {
        this.checked = r2;
        if (this.viewType != 11) goto L5;
        this.viewType = 12;
    L5:
        return this;
    }

    public UItem setClickCallback(View.OnClickListener r1) {
        this.clickCallback = r1;
        return this;
    }

    public UItem setClickCallback2(View.OnClickListener r1) {
        this.clickCallback2 = r1;
        return this;
    }

    public UItem setCloseIcon(View.OnClickListener r1) {
        this.clickCallback = r1;
        return this;
    }

    public UItem setCollapsed(boolean r1) {
        this.collapsed = r1;
        return this;
    }

    public UItem setColorfulIcon(int r1, int r2) {
        setIcon(r1);
        this.iconColor = Integer.valueOf(r2);
        return this;
    }

    public UItem setEnabled(boolean r1) {
        this.enabled = r1;
        return this;
    }

    public UItem setIcon(int r1) {
        this.iconResId = r1;
        return this;
    }

    public UItem setId(int r1) {
        this.id = r1;
        return this;
    }

    public UItem setLinkAlias(String r2, BasePreferencesActivity r3) {
        if (SettingsRegistry.isValidForLinkAliases(this) == false) goto L5;
        SettingsRegistry.getInstance().addLinkAliasForOption(r2, r3, this);
    L5:
        return this;
    }

    public UItem setLocked(boolean r1) {
        this.locked = r1;
        return this;
    }

    public UItem setMinSliderValue(int r3) {
        this.longValue = r3;
        return this;
    }

    public UItem setMultiline(boolean r1) {
        this.multiline = r1;
        return this;
    }

    public UItem setPad(int r1) {
        this.pad = r1;
        return this;
    }

    public UItem setReordering(boolean r1) {
        this.reordering = r1;
        return this;
    }

    public UItem setSearchable(BasePreferencesActivity r2) {
        if (SettingsRegistry.isValidForSearch(this) == false) goto L5;
        SettingsRegistry.getInstance().addSearchEntry(r2, this);
    L5:
        return this;
    }

    public UItem setSpanCount(int r1) {
        this.spanCount = r1;
        return this;
    }

    public UItem setTransparent(boolean r1) {
        this.transparent = r1;
        return this;
    }

    public UItem setValue(CharSequence r1) {
        this.textValue = r1;
        return this;
    }

    public UItem showDivider(boolean r1) {
        this.hideDivider = !r1;
        return this;
    }

    public UItem withOpenButton(Utilities.Callback<TLRPC.User> r2) {
        this.locked = true;
        this.object2 = r2;
        return this;
    }

    public UItem withUsername(boolean r1) {
        this.withUsername = r1;
        return this;
    }
}
