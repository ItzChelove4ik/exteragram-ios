package com.exteragram.messenger.plugins.utils;

import android.content.Context;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_bots;
import org.telegram.ui.ActionBar.BaseFragment;

@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0002\u0018\u0000 .2\u00020\u0001:\u0001.B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\u00002\b\u0010\f\u001a\u0004\u0018\u00010\rJ\u0010\u0010\u000e\u001a\u00020\u00002\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\u0010\u0010\u0011\u001a\u00020\u00002\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013J\u0010\u0010\u0014\u001a\u00020\u00002\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016J\u0010\u0010\u0017\u001a\u00020\u00002\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019J\u0010\u0010\u001a\u001a\u00020\u00002\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cJ\u0010\u0010\u001d\u001a\u00020\u00002\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fJ\u000e\u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\"J\u0010\u0010#\u001a\u00020\u00002\b\u0010$\u001a\u0004\u0018\u00010%J\u0010\u0010&\u001a\u00020\u00002\b\u0010'\u001a\u0004\u0018\u00010(J\u001a\u0010)\u001a\u00020\u00002\b\u0010*\u001a\u0004\u0018\u00010\u00062\b\u0010+\u001a\u0004\u0018\u00010\u0001J\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010-R*\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/exteragram/messenger/plugins/utils/MenuContextBuilder;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "contextData", "Ljava/util/HashMap;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/collections/HashMap;", "withAccount", "account", _UrlKt.FRAGMENT_ENCODE_SET, "withContext", "context", "Landroid/content/Context;", "withEncryptedChat", "encryptedChat", "Lorg/telegram/tgnet/TLRPC$EncryptedChat;", "withChat", "chat", "Lorg/telegram/tgnet/TLRPC$Chat;", "withChatFull", "chatFull", "Lorg/telegram/tgnet/TLRPC$ChatFull;", "withUser", "user", "Lorg/telegram/tgnet/TLRPC$User;", "withUserFull", "userFull", "Lorg/telegram/tgnet/TLRPC$UserFull;", "withBotInfo", "botInfo", "Lorg/telegram/tgnet/tl/TL_bots$BotInfo;", "withDialogId", "dialogId", _UrlKt.FRAGMENT_ENCODE_SET, "withMessage", "message", "Lorg/telegram/messenger/MessageObject;", "withGroupedMessage", "groupedMessages", "Lorg/telegram/messenger/MessageObject$GroupedMessages;", "withCustom", PluginsConstants.Settings.KEY, "value", "build", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MenuContextBuilder {
    public static final Companion Companion = null;
    private final HashMap<String, Object> contextData;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0007J\u0012\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007¨\u0006\t"}, d2 = {"Lcom/exteragram/messenger/plugins/utils/MenuContextBuilder$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "create", "Lcom/exteragram/messenger/plugins/utils/MenuContextBuilder;", "from", "fragment", "Lorg/telegram/ui/ActionBar/BaseFragment;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final MenuContextBuilder create() {
            return new MenuContextBuilder(null);
        }

        @JvmStatic
        public final MenuContextBuilder from(BaseFragment r2) {
            if (r2 != null) goto L6;
            return create();
        L6:
            return create().withCustom("fragment", r2).withAccount(r2.getCurrentAccount()).withContext(r2.getParentActivity());
        }
    }

    static {
        Companion = new Companion(null);
    }

    private MenuContextBuilder() {
        this.contextData = new HashMap();
    }

    public /* synthetic */ MenuContextBuilder(DefaultConstructorMarker r1) {
        this();
    }

    @JvmStatic
    public static final MenuContextBuilder create() {
        return Companion.create();
    }

    @JvmStatic
    public static final MenuContextBuilder from(BaseFragment r1) {
        return Companion.from(r1);
    }

    public final Map<String, Object> build() {
        return this.contextData;
    }

    public final MenuContextBuilder withAccount(int r3) {
        this.contextData.put("account", Integer.valueOf(r3));
        return this;
    }

    public final MenuContextBuilder withBotInfo(TL_bots.BotInfo r3) {
        if (r3 == null) goto L4;
        this.contextData.put("botInfo", r3);
    L4:
        return this;
    }

    public final MenuContextBuilder withChat(TLRPC.Chat r4) {
        if (r4 == null) goto L4;
        this.contextData.put("chat", r4);
        this.contextData.put("chatId", Long.valueOf(r4.id));
    L4:
        return this;
    }

    public final MenuContextBuilder withChatFull(TLRPC.ChatFull r3) {
        if (r3 == null) goto L4;
        this.contextData.put("chatFull", r3);
    L4:
        return this;
    }

    public final MenuContextBuilder withContext(Context r3) {
        if (r3 == null) goto L4;
        this.contextData.put("context", r3);
    L4:
        return this;
    }

    public final MenuContextBuilder withCustom(String r2, Object r3) {
        if (r2 == null) goto L5;
        if (r3 == null) goto L5;
        this.contextData.put(r2, r3);
    L5:
        return this;
    }

    public final MenuContextBuilder withDialogId(long r3) {
        this.contextData.put("dialog_id", Long.valueOf(r3));
        return this;
    }

    public final MenuContextBuilder withEncryptedChat(TLRPC.EncryptedChat r3) {
        if (r3 == null) goto L4;
        this.contextData.put("encryptedChat", r3);
    L4:
        return this;
    }

    public final MenuContextBuilder withGroupedMessage(MessageObject.GroupedMessages r3) {
        if (r3 == null) goto L4;
        this.contextData.put("groupedMessages", r3);
    L4:
        return this;
    }

    public final MenuContextBuilder withMessage(MessageObject r3) {
        if (r3 == null) goto L4;
        this.contextData.put("message", r3);
    L4:
        return this;
    }

    public final MenuContextBuilder withUser(TLRPC.User r4) {
        if (r4 == null) goto L4;
        this.contextData.put("user", r4);
        this.contextData.put("userId", Long.valueOf(r4.id));
    L4:
        return this;
    }

    public final MenuContextBuilder withUserFull(TLRPC.UserFull r3) {
        if (r3 == null) goto L4;
        this.contextData.put("userFull", r3);
    L4:
        return this;
    }
}
