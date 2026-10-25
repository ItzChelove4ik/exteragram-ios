package com.exteragram.messenger.badges;

import android.widget.FrameLayout;
import com.exteragram.messenger.api.db.ExteraDatabase;
import com.exteragram.messenger.api.dto.BadgeDTO;
import com.exteragram.messenger.badges.source.ApiBadgeSource;
import com.exteragram.messenger.components.SupporterBottomSheet;
import com.exteragram.messenger.utils.chats.ChatUtils;
import com.exteragram.messenger.utils.text.LocaleUtils;
import com.sun.jna.Callback;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;

@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JM\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u001f\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\bH\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u001d\u0010\u001a\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0007¢\u0006\u0004\b\u001a\u0010\u001bJ\u0019\u0010\u001c\u001a\u00020\n2\b\b\u0002\u0010\u0019\u001a\u00020\u0018H\u0007¢\u0006\u0004\b\u001c\u0010\u001dJ\u0015\u0010 \u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u001e¢\u0006\u0004\b \u0010!J\u0015\u0010\"\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u001e¢\u0006\u0004\b\"\u0010!J\u0017\u0010\"\u001a\u00020\n2\b\u0010$\u001a\u0004\u0018\u00010#¢\u0006\u0004\b\"\u0010%J\u000f\u0010&\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b&\u0010'J\u0019\u0010&\u001a\u0004\u0018\u00010\u00062\b\u0010)\u001a\u0004\u0018\u00010(¢\u0006\u0004\b&\u0010*J#\u0010+\u001a\u00020\n2\b\u0010)\u001a\u0004\u0018\u00010(2\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b+\u0010,J\u0019\u0010-\u001a\u0004\u0018\u00010\u00062\b\u0010)\u001a\u0004\u0018\u00010(¢\u0006\u0004\b-\u0010*J\u0019\u0010.\u001a\u00020\n2\b\b\u0002\u0010)\u001a\u00020(H\u0007¢\u0006\u0004\b.\u0010/J'\u00103\u001a\u00020\u00122\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u000e\u00102\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010100¢\u0006\u0004\b3\u00104J\u0019\u00105\u001a\u00020\n2\b\b\u0002\u0010)\u001a\u00020(H\u0007¢\u0006\u0004\b5\u0010/JO\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010)\u001a\u0004\u0018\u00010(2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0007¢\u0006\u0004\b\u0013\u00106JW\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010)\u001a\u0004\u0018\u00010(2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0007¢\u0006\u0004\b\u0013\u00107JW\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010$\u001a\u0004\u0018\u00010#2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0007¢\u0006\u0004\b\u0013\u00108R\u0014\u0010:\u001a\u0002098\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010;R\u0014\u0010=\u001a\u00020<8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b=\u0010>R\u0014\u0010@\u001a\u00020?8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b@\u0010AR\u0017\u0010B\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\bB\u0010C\u001a\u0004\bD\u0010'R\u0017\u0010E\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\bE\u0010C\u001a\u0004\bF\u0010'R\u0017\u0010G\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\bG\u0010C\u001a\u0004\bH\u0010'¨\u0006I"}, d2 = {"Lcom/exteragram/messenger/badges/BadgesController;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Lorg/telegram/ui/ActionBar/BaseFragment;", "fragment", "Lcom/exteragram/messenger/api/dto/BadgeDTO;", "badge", _UrlKt.FRAGMENT_ENCODE_SET, "text", _UrlKt.FRAGMENT_ENCODE_SET, "showButton", "Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;", "resourcesProvider", _UrlKt.FRAGMENT_ENCODE_SET, "account", "Landroid/widget/FrameLayout;", "containerLayout", _UrlKt.FRAGMENT_ENCODE_SET, "showBadgeBulletin", "(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/api/dto/BadgeDTO;Ljava/lang/CharSequence;ZLorg/telegram/ui/ActionBar/Theme$ResourcesProvider;ILandroid/widget/FrameLayout;)V", "fallbackText", "formatBadgeText", "(Lcom/exteragram/messenger/api/dto/BadgeDTO;Ljava/lang/CharSequence;)Ljava/lang/CharSequence;", "Lorg/telegram/tgnet/TLObject;", "obj", "getBadge", "(Lorg/telegram/tgnet/TLObject;)Lcom/exteragram/messenger/api/dto/BadgeDTO;", "hasBadge", "(Lorg/telegram/tgnet/TLObject;)Z", _UrlKt.FRAGMENT_ENCODE_SET, "id", "isTrusted", "(J)Z", "isExtera", "Lorg/telegram/tgnet/TLRPC$Chat;", "chat", "(Lorg/telegram/tgnet/TLRPC$Chat;)Z", "getDefaultBadge", "()Lcom/exteragram/messenger/api/dto/BadgeDTO;", "Lorg/telegram/tgnet/TLRPC$User;", "user", "(Lorg/telegram/tgnet/TLRPC$User;)Lcom/exteragram/messenger/api/dto/BadgeDTO;", "shouldUseSecondaryBadgeSlot", "(Lorg/telegram/tgnet/TLRPC$User;Lcom/exteragram/messenger/api/dto/BadgeDTO;)Z", "getSecondaryBadge", "canChangeBadge", "(Lorg/telegram/tgnet/TLRPC$User;)Z", "Ljava/util/function/Consumer;", _UrlKt.FRAGMENT_ENCODE_SET, Callback.METHOD_NAME, "updateBadge", "(Lcom/exteragram/messenger/api/dto/BadgeDTO;Ljava/util/function/Consumer;)V", "isDeveloper", "(Lorg/telegram/ui/ActionBar/BaseFragment;Lorg/telegram/tgnet/TLRPC$User;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;ILandroid/widget/FrameLayout;Ljava/lang/Boolean;)V", "(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/api/dto/BadgeDTO;Lorg/telegram/tgnet/TLRPC$User;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;ILandroid/widget/FrameLayout;Ljava/lang/Boolean;)V", "(Lorg/telegram/ui/ActionBar/BaseFragment;Lcom/exteragram/messenger/api/dto/BadgeDTO;Lorg/telegram/tgnet/TLRPC$Chat;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;ILandroid/widget/FrameLayout;Ljava/lang/Boolean;)V", "Lkotlinx/coroutines/CoroutineScope;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "Lcom/exteragram/messenger/badges/source/ApiBadgeSource;", "apiBadgeSource", "Lcom/exteragram/messenger/badges/source/ApiBadgeSource;", "Lcom/exteragram/messenger/badges/CachedRemoteSet;", "trustedPluginsCache", "Lcom/exteragram/messenger/badges/CachedRemoteSet;", "DEV_BADGE", "Lcom/exteragram/messenger/api/dto/BadgeDTO;", "getDEV_BADGE", "SUPPORTER_BADGE", "getSUPPORTER_BADGE", "TRUSTED_BADGE", "getTRUSTED_BADGE", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nBadgesController.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BadgesController.kt\ncom/exteragram/messenger/badges/BadgesController\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,259:1\n1#2:260\n*E\n"})
/* loaded from: classes.dex */
public final class BadgesController {
    private static final BadgeDTO DEV_BADGE = null;
    public static final BadgesController INSTANCE = null;
    private static final BadgeDTO SUPPORTER_BADGE = null;
    private static final BadgeDTO TRUSTED_BADGE = null;
    private static final ApiBadgeSource apiBadgeSource = null;
    private static final CoroutineScope scope = null;
    private static final CachedRemoteSet trustedPluginsCache = null;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.badges.BadgesController$1", f = "BadgesController.kt", i = {}, l = {53}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.exteragram.messenger.badges.BadgesController$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public AnonymousClass1(Continuation<? super AnonymousClass1> r2) {
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new AnonymousClass1(r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r4) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r4);
        L12:
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r4);
            ApiBadgeSource r42 = BadgesController.access$getApiBadgeSource$p();
            this.label = 1;
            if (r42.loadToCache(this) != r0) goto L12;
            return r0;
        }
    }

    /* renamed from: $r8$lambda$6jXLi1rXNa-PNrSPFHLYDk6_yBA, reason: not valid java name */
    public static void m1138$r8$lambda$6jXLi1rXNaPNrSPFHLYDk6_yBA(BaseFragment r0, Theme.ResourcesProvider r1) {
        SupporterBottomSheet.showAlert(r0, r1);
    }

    public static void $r8$lambda$q2QZZ0ppiIjePLLSVYRB87x0Q6Y(Consumer r7, BadgeDTO r8, String r9) {
        if (Intrinsics.areEqual("ok", r9) == false) goto L5;
        BuildersKt.launch$default(scope, null, null, new BadgesController$updateBadge$1$1(r8, null), 3, null);
    L5:
        r7.accept(r9);
    }

    static {
        INSTANCE = new BadgesController();
        CoroutineScope r1 = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
        scope = r1;
        trustedPluginsCache = new CachedRemoteSet("trusted_plugins", SetsKt.setOf(2562664432L));
        DEV_BADGE = new BadgeDTO(5359407509327085568L, null);
        SUPPORTER_BADGE = new BadgeDTO(5391059537102927631L, null);
        TRUSTED_BADGE = new BadgeDTO(5452008215409629764L, null);
        apiBadgeSource = new ApiBadgeSource(ExteraDatabase.Companion.getInstance().profileDao());
        BuildersKt.launch$default(r1, null, null, new AnonymousClass1(null), 3, null);
    }

    private BadgesController() {
    }

    public static final /* synthetic */ ApiBadgeSource access$getApiBadgeSource$p() {
        return apiBadgeSource;
    }

    public static /* synthetic */ boolean canChangeBadge$default(BadgesController r0, TLRPC.User r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser();
    L6:
        return r0.canChangeBadge(r1);
    }

    private final CharSequence formatBadgeText(BadgeDTO r1, CharSequence r2) {
        String r0 = r1.getText();
        if (r0 == null) goto L8;
        CharSequence r02 = LocaleUtils.formatWithUsernames(r0);
        if (r02 == null) goto L8;
        return r02;
    L8:
        return r2;
    }

    public static /* synthetic */ BadgeDTO getBadge$default(BadgesController r0, TLObject r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser();
    L6:
        return r0.getBadge(r1);
    }

    public static /* synthetic */ boolean hasBadge$default(BadgesController r0, TLObject r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser();
    L6:
        return r0.hasBadge(r1);
    }

    public static /* synthetic */ boolean isDeveloper$default(BadgesController r0, TLRPC.User r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser();
    L6:
        return r0.isDeveloper(r1);
    }

    private final void showBadgeBulletin(BaseFragment r3, BadgeDTO r4, CharSequence r5, boolean r6, Theme.ResourcesProvider r7, int r8, FrameLayout r9) {
        if (r9 == null) goto L4;
        BulletinFactory r2 = BulletinFactory.of(r9, r7);
    L5:
        TLRPC.Document r42 = AnimatedEmojiDrawable.findDocument(r8, r4.getDocumentId());
        BadgesController$$ExternalSyntheticLambda0 r82 = null;
        if (r6 == false) goto L8;
        String r92 = LocaleController.getString(R.string.FragmentUsernameOpen);
    L9:
        if (r6 == false) goto L11;
        r82 = new BadgesController$$ExternalSyntheticLambda0(r3, r7);
    L11:
        Bulletin r22 = r2.createEmojiBulletin(r42, r5, r92, r82);
        if (r6 == true) goto L14;
        r22.wrapContent();
    L14:
        r22.show();
        return;
    L8:
        r92 = null;
        goto L9
    L4:
        r2 = BulletinFactory.of(r3);
        goto L5
    }

    public static /* synthetic */ void showBadgeBulletin$default(BadgesController r8, BaseFragment r9, BadgeDTO r10, TLRPC.Chat r11, Theme.ResourcesProvider r12, int r13, FrameLayout r14, Boolean r15, int r16, Object r17) {
        if ((r16 & 8) == 0) goto L5;
        r12 = r9.getResourceProvider();
    L5:
        Theme.ResourcesProvider r4 = r12;
        if ((r16 & 16) == 0) goto L8;
        r13 = r9.getCurrentAccount();
    L8:
        int r5 = r13;
        if ((r16 & 32) == 0) goto L11;
        FrameLayout r6 = null;
    L13:
        if ((r16 & 64) == 0) goto L16;
        Boolean r7 = null;
    L17:
        r8.showBadgeBulletin(r9, r10, r11, r4, r5, r6, r7);
        return;
    L16:
        r7 = r15;
        goto L17
    L11:
        r6 = r14;
        goto L13
    }

    public static /* synthetic */ void showBadgeBulletin$default(BadgesController r8, BaseFragment r9, BadgeDTO r10, TLRPC.User r11, Theme.ResourcesProvider r12, int r13, FrameLayout r14, Boolean r15, int r16, Object r17) {
        if ((r16 & 8) == 0) goto L5;
        r12 = r9.getResourceProvider();
    L5:
        Theme.ResourcesProvider r4 = r12;
        if ((r16 & 16) == 0) goto L8;
        r13 = r9.getCurrentAccount();
    L8:
        int r5 = r13;
        if ((r16 & 32) == 0) goto L11;
        FrameLayout r6 = null;
    L13:
        if ((r16 & 64) == 0) goto L16;
        Boolean r7 = null;
    L17:
        r8.showBadgeBulletin(r9, r10, r11, r4, r5, r6, r7);
        return;
    L16:
        r7 = r15;
        goto L17
    L11:
        r6 = r14;
        goto L13
    }

    @JvmOverloads
    public final boolean canChangeBadge() {
        return canChangeBadge$default(this, null, 1, null);
    }

    @JvmOverloads
    public final boolean canChangeBadge(TLRPC.User r3) {
        return apiBadgeSource.canChangeBadge(r3.id);
    }

    @JvmOverloads
    public final BadgeDTO getBadge() {
        return getBadge$default(this, null, 1, null);
    }

    @JvmOverloads
    public final BadgeDTO getBadge(TLObject r4) {
    L6:
        e = move-exception;
        FileLog.e(e);
    L22:
        return null;
    L4:
        if ((r4 instanceof TLRPC.User) == false) goto L9;
        Pair r42 = TuplesKt.to(Long.valueOf(((TLRPC.User) r4).id), Boolean.TRUE);     // Catch: Exception -> L6
    L11:
        long r1 = ((Number) r42.component1()).longValue();     // Catch: Exception -> L6
        boolean r43 = ((Boolean) r42.component2()).booleanValue();     // Catch: Exception -> L6
        if (r43 == false) goto L14;
    L17:
        BadgeDTO r3 = apiBadgeSource.getBadge(r1, r43);     // Catch: Exception -> L6
        if (r3 == null) goto L22;
        return r3;
    L14:
        if (isTrusted(r1) == false) goto L17;
        return TRUSTED_BADGE;
    L9:
        if ((r4 instanceof TLRPC.Chat) == false) goto L20;
        r42 = TuplesKt.to(Long.valueOf(((TLRPC.Chat) r4).id), Boolean.FALSE);     // Catch: Exception -> L6
        goto L11
    L20:
        return null;
    }

    public final BadgeDTO getDefaultBadge() {
        if (apiBadgeSource.isDeveloper(UserConfig.getInstance(UserConfig.selectedAccount).clientUserId) == false) goto L7;
        return DEV_BADGE;
    L7:
        return SUPPORTER_BADGE;
    }

    public final BadgeDTO getDefaultBadge(TLRPC.User r1) {
        if (r1 != null) goto L6;
        return null;
    L6:
        if (isDeveloper(r1) == false) goto L10;
        return DEV_BADGE;
    L10:
        return SUPPORTER_BADGE;
    }

    public final BadgeDTO getSecondaryBadge(TLRPC.User r3) {
        BadgeDTO r2 = getBadge(r3);
        if (r2 != null) goto L6;
        return null;
    L6:
        if (INSTANCE.shouldUseSecondaryBadgeSlot(r3, r2) == false) goto L8;
        return r2;
    L8:
        return null;
    }

    @JvmOverloads
    public final boolean hasBadge() {
        return hasBadge$default(this, null, 1, null);
    }

    @JvmOverloads
    public final boolean hasBadge(TLObject r1) {
        if (getBadge(r1) == null) goto L6;
        return true;
    L6:
        return false;
    }

    @JvmOverloads
    public final boolean isDeveloper() {
        return isDeveloper$default(this, null, 1, null);
    }

    @JvmOverloads
    public final boolean isDeveloper(TLRPC.User r3) {
        return apiBadgeSource.isDeveloper(r3.id);
    }

    public final boolean isExtera(long r1) {
        return apiBadgeSource.isDeveloper(r1);
    }

    public final boolean isExtera(TLRPC.Chat r4) {
        if (r4 != null) goto L5;
    L7:
        return false;
    L5:
        if (INSTANCE.isExtera(r4.id) != true) goto L7;
        return true;
    }

    public final boolean isTrusted(long r1) {
        return trustedPluginsCache.contains(r1);
    }

    public final boolean shouldUseSecondaryBadgeSlot(TLRPC.User r3, BadgeDTO r4) {
        if (r3 != null) goto L5;
        return false;
    L5:
        if (r4 != null) goto L8;
        return false;
    L8:
        if (canChangeBadge(r3) == true) goto L10;
    L15:
        return false;
    L10:
        if (Intrinsics.areEqual(r4, getDefaultBadge(r3)) == true) goto L12;
        return true;
    L12:
        if (isDeveloper(r3) == false) goto L15;
        return true;
    }

    @JvmOverloads
    public final void showBadgeBulletin(BaseFragment r11, BadgeDTO r12, TLRPC.Chat r13, Theme.ResourcesProvider r14, int r15) {
        showBadgeBulletin$default(this, r11, r12, r13, r14, r15, null, null, 96, null);
    }

    @JvmOverloads
    public final void showBadgeBulletin(BaseFragment r9, BadgeDTO r10, TLRPC.Chat r11, Theme.ResourcesProvider r12, int r13, FrameLayout r14, Boolean r15) {
        if (r11 != null) goto L4;
        return;
    L4:
        boolean r2 = isExtera(r11);
        boolean r3 = isTrusted(r11.id);
        if (r2 == false) goto L8;
        String r1 = LocaleController.formatString(R.string.OfficialChannel, new Object[]{r11.title});
    L11:
        CharSequence r16 = formatBadgeText(r10, r1);
        if (r15 == null) goto L15;
        boolean r32 = r15.booleanValue();
    L19:
        showBadgeBulletin(r9, r10, r16, r32, r12, r13, r14);
        return;
    L15:
        if (r2 == true) goto L18;
        if (r3 == true) goto L18;
        r32 = true;
    L18:
        r32 = false;
        goto L19
    L8:
        if (r3 == false) goto L10;
        r1 = LocaleController.getString(R.string.PluginSourceTrustedInfo);
        goto L11
    L10:
        r1 = LocaleController.formatString(R.string.Supporter, new Object[]{r11.title});
        goto L11
    }

    @JvmOverloads
    public final void showBadgeBulletin(BaseFragment r11, BadgeDTO r12, TLRPC.User r13, Theme.ResourcesProvider r14, int r15) {
        showBadgeBulletin$default(this, r11, r12, r13, r14, r15, null, null, 96, null);
    }

    @JvmOverloads
    public final void showBadgeBulletin(BaseFragment r4, BadgeDTO r5, TLRPC.User r6, Theme.ResourcesProvider r7, int r8, FrameLayout r9, Boolean r10) {
        if (r6 != null) goto L4;
        return;
    L4:
        boolean r0 = isDeveloper(r6);
        if (r0 == false) goto L7;
        int r1 = R.string.Developer;
    L8:
        CharSequence r12 = formatBadgeText(r5, LocaleController.formatString(r1, new Object[]{r6.first_name}));
        if (r10 == null) goto L12;
        boolean r62 = r10.booleanValue();
    L17:
        showBadgeBulletin(r4, r5, r12, r62, r7, r8, r9);
        return;
    L12:
        if (r0 == false) goto L14;
    L16:
        r62 = false;
        goto L17
    L14:
        if (canChangeBadge(r6) == true) goto L16;
        r62 = true;
        goto L17
    L7:
        r1 = R.string.Supporter;
        goto L8
    }

    @JvmOverloads
    public final void showBadgeBulletin(BaseFragment r9, TLRPC.User r10, Theme.ResourcesProvider r11, int r12, FrameLayout r13, Boolean r14) {
        if (r10 == null) goto L9;
        BadgeDTO r2 = getBadge(r10);
        if (r2 != null) goto L7;
        return;
    L7:
        showBadgeBulletin(r9, r2, r10, r11, r12, r13, r14);
        return;
    }

    public final void updateBadge(BadgeDTO r5, Consumer<String> r6) {
        if (r5 == null) goto L12;
        String r4 = r5.getText();
        if (r4 != null) goto L6;
    L9:
        String r42 = "badge " + r5.getDocumentId();
    L10:
        ChatUtils.getInstance(UserConfig.selectedAccount).sendBotRequest(r42, false, new BadgesController$$ExternalSyntheticLambda1(r6, r5));
        return;
    L6:
        if (r4.length() == 0) goto L9;
        r42 = "badge " + r5.getDocumentId() + " " + r4;
        goto L10
    L12:
        r6.accept(null);
    }
}
