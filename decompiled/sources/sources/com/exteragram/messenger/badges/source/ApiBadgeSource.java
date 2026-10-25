package com.exteragram.messenger.badges.source;

import com.exteragram.messenger.api.db.ProfileDao;
import com.exteragram.messenger.api.dto.BadgeDTO;
import com.exteragram.messenger.api.dto.ProfileDTO;
import com.exteragram.messenger.api.model.ProfileStatus;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J!\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\r\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u000f\u0010\u000eJ\"\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\nH\u0086@¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0011H\u0086@¢\u0006\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0016R \u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00180\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Lcom/exteragram/messenger/badges/source/ApiBadgeSource;", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/api/db/ProfileDao;", "profileDao", "<init>", "(Lcom/exteragram/messenger/api/db/ProfileDao;)V", _UrlKt.FRAGMENT_ENCODE_SET, "id", _UrlKt.FRAGMENT_ENCODE_SET, "isUser", "Lcom/exteragram/messenger/api/dto/BadgeDTO;", "getBadge", "(JZ)Lcom/exteragram/messenger/api/dto/BadgeDTO;", "isDeveloper", "(J)Z", "canChangeBadge", "badge", _UrlKt.FRAGMENT_ENCODE_SET, "updateLocalBadge", "(JLcom/exteragram/messenger/api/dto/BadgeDTO;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadToCache", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/exteragram/messenger/api/db/ProfileDao;", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/exteragram/messenger/badges/source/BadgeInfo;", "cache", "Ljava/util/concurrent/ConcurrentHashMap;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ApiBadgeSource {
    private final ConcurrentHashMap<Long, BadgeInfo> cache;
    private final ProfileDao profileDao;

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.exteragram.messenger.badges.source.ApiBadgeSource", f = "ApiBadgeSource.kt", i = {}, l = {38}, m = "loadToCache", n = {}, s = {})
    /* renamed from: com.exteragram.messenger.badges.source.ApiBadgeSource$loadToCache$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ ApiBadgeSource this$0;

        public AnonymousClass1(ApiBadgeSource r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.loadToCache(this);
        }
    }

    /* renamed from: $r8$lambda$1RlpGWty-giJJAOo0oCe1OhlcXM, reason: not valid java name */
    public static BadgeInfo m1139$r8$lambda$1RlpGWtygiJJAOo0oCe1OhlcXM(BadgeDTO r6, Long r7, BadgeInfo r8) {
        return BadgeInfo.copy$default(r8, r6, null, false, 6, null);
    }

    public static BadgeInfo $r8$lambda$k56Kf1gLoZpo2cEpNa8MMgWDGfI(Function2 r0, Object r1, Object r2) {
        return (BadgeInfo) r0.invoke(r1, r2);
    }

    public ApiBadgeSource(ProfileDao r1) {
        this.profileDao = r1;
        this.cache = new ConcurrentHashMap();
    }

    public final boolean canChangeBadge(long r3) {
        BadgeInfo r0 = this.cache.get(Long.valueOf(r3));
        if (r0 == null) goto L8;
        if (r0.getCanChangeBadge() != true) goto L8;
    L9:
        return true;
    L8:
        if (isDeveloper(r3) == true) goto L9;
        return false;
    }

    public BadgeDTO getBadge(long r1, boolean r3) {
        BadgeInfo r0 = this.cache.get(Long.valueOf(r1));
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getBadge();
    }

    public final boolean isDeveloper(long r1) {
        BadgeInfo r0 = this.cache.get(Long.valueOf(r1));
        if (r0 == null) goto L5;
        ProfileStatus r02 = r0.getStatus();
    L7:
        if (r02 != ProfileStatus.DEVELOPER) goto L10;
        return true;
    L10:
        return false;
    L5:
        r02 = null;
        goto L7
    }

    public final Object loadToCache(Continuation<? super Unit> r9) {
        if ((r9 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r9;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r92 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        ResultKt.throwOnFailure(r92);
    L17:
        Iterator r93 = ((List) r92).iterator();
    L19:
        if (r93.hasNext() == false) goto L22;
        ProfileDTO r02 = (ProfileDTO) r93.next();
        this.cache.put(Boxing.boxLong(r02.getId()), new BadgeInfo(r02.getBadge(), r02.getStatus(), Intrinsics.areEqual(r02.getCanChangeBadge(), Boxing.boxBoolean(true))));
        goto L19
    L22:
        return Unit.INSTANCE;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r92);
        ProfileDao r94 = this.profileDao;
        r0.label = 1;
        r92 = r94.getAll(r0);
        if (r92 != r12) goto L17;
        return r12;
    L7:
        r0 = new AnonymousClass1(this, r9);
        goto L8
    }

    public final Object updateLocalBadge(long r5, BadgeDTO r7, Continuation<? super Unit> r8) {
        this.cache.computeIfPresent(Boxing.boxLong(r5), new ApiBadgeSource$$ExternalSyntheticLambda1(new ApiBadgeSource$$ExternalSyntheticLambda0(r7)));
        Object r4 = this.profileDao.updateBadge(r5, r7, r8);
        if (r4 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r4;
    L6:
        return Unit.INSTANCE;
    }
}
