package org.telegram.ui.bots;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.HashMap;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.AvatarSpan;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.bots.BotBiometry;

/* loaded from: classes7.dex */
public class BotBiometrySettings extends BaseFragment {
    private final ArrayList<BotBiometry.Bot> biometryBots;
    private final HashMap<BotBiometry.Bot, SpannableStringBuilder> botName;
    private UniversalRecyclerView listView;

    /* renamed from: org.telegram.ui.bots.BotBiometrySettings$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ BotBiometrySettings this$0;

        public AnonymousClass1(BotBiometrySettings r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: $r8$lambda$1v23yz-UJ_MGz8EwSZzdXUVwnY8, reason: not valid java name */
    public static /* synthetic */ void m21311$r8$lambda$1v23yzUJ_MGz8EwSZzdXUVwnY8(BotBiometrySettings r0, ArrayList r1) {
        r0.lambda$createView$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$SFFOEW9Z4pzq7KZyqxtqYsVUEDU(BotBiometrySettings r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    /* renamed from: $r8$lambda$ejIfwlqWq19DeFGN8-AaG5kOeM4, reason: not valid java name */
    public static /* synthetic */ void m21312$r8$lambda$ejIfwlqWq19DeFGN8AaG5kOeM4(BotBiometrySettings r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onClick(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ boolean $r8$lambda$xLKjuI37dErx_ID844MWoKkBXjc(BotBiometrySettings r0, UItem r1, View r2, int r3, float r4, float r5) {
        return r0.onLongClick(r1, r2, r3, r4, r5);
    }

    public BotBiometrySettings() {
        this.biometryBots = new ArrayList();
        this.botName = new HashMap();
    }

    private void fillItems(ArrayList<UItem> r9, UniversalAdapter r10) {
        int r0 = 0;
    L4:
        if (r0 >= this.biometryBots.size()) goto L9;
        BotBiometry.Bot r1 = this.biometryBots.get(r0);
        SpannableStringBuilder r2 = this.botName.get(r1);
        if (r2 != null) goto L8;
        r2 = new SpannableStringBuilder();
        r2.append("a   ");
        AvatarSpan r4 = new AvatarSpan(null, this.currentAccount, 24.0f);
        r4.setUser(r1.user);
        r2.setSpan(r4, 0, 1, 33);
        r2.append(UserObject.getUserName(r1.user));
        this.botName.put(r1, r2);
    L8:
        r9.add(UItem.asCheck(r0, r2).setChecked(!r1.disabled));
        r0 = r0 + 1;
        goto L4
    L9:
        r9.add(UItem.asShadow(LocaleController.getString(R.string.PrivacyBiometryBotsInfo)));
    }

    private /* synthetic */ void lambda$createView$0(ArrayList r2) {
        this.biometryBots.clear();
        this.biometryBots.addAll(r2);
        UniversalRecyclerView r1 = this.listView;
        if (r1 == null) goto L8;
        UniversalAdapter r12 = r1.adapter;
        if (r12 == null) goto L9;
        r12.update(true);
        return;
    L9:
        return;
    }

    private void onClick(UItem r3, View r4, int r5, float r6, float r7) {
        if (r3.viewType != 4) goto L15;
        int r42 = r3.id;
        if (r42 >= 0) goto L7;
        return;
    L7:
        if (r42 >= this.biometryBots.size()) goto L19;
        BotBiometry.Bot r32 = this.biometryBots.get(r3.id);
        r32.disabled = !r32.disabled;
        BotBiometry.toggleBotDisabled(getContext(), this.currentAccount, r32.user.id, r32.disabled);
        UniversalRecyclerView r2 = this.listView;
        if (r2 == null) goto L17;
        UniversalAdapter r22 = r2.adapter;
        if (r22 == null) goto L18;
        r22.update(true);
        return;
    L18:
        return;
    L17:
        return;
    L19:
        return;
    }

    private boolean onLongClick(UItem r1, View r2, int r3, float r4, float r5) {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r5) {
        this.actionBar.setBackButtonDrawable(new BackDrawable(false));
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.PrivacyBiometryBots));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        FrameLayout r0 = new FrameLayout(r5);
        r0.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray, this.resourceProvider));
        UniversalRecyclerView r52 = new UniversalRecyclerView(this, new BotBiometrySettings$$ExternalSyntheticLambda0(this), new BotBiometrySettings$$ExternalSyntheticLambda1(this), new BotBiometrySettings$$ExternalSyntheticLambda2(this));
        this.listView = r52;
        r0.addView(r52, LayoutHelper.createFrame(-1, -1, 119));
        BotBiometry.getBots(getContext(), this.currentAccount, new BotBiometrySettings$$ExternalSyntheticLambda3(this));
        this.fragmentView = r0;
        return r0;
    }
}
