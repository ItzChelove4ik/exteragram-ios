package com.exteragram.messenger.api.db;

import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteConnectionUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import c.f$$ExternalSyntheticBUOutline1;
import com.exteragram.messenger.api.dto.BadgeDTO;
import com.exteragram.messenger.api.dto.NowPlayingInfoDTO;
import com.exteragram.messenger.api.dto.ProfileDTO;
import com.exteragram.messenger.api.model.ProfileStatus;
import com.exteragram.messenger.api.model.ProfileType;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 )2\u00020\u0001:\u0001)B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001c\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u000eH\u0096@¢\u0006\u0002\u0010\u000fJ\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u000eH\u0096@¢\u0006\u0002\u0010\u0011J\u0018\u0010\u0012\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0096@¢\u0006\u0002\u0010\u0015J\u001c\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00140\u000eH\u0096@¢\u0006\u0002\u0010\u000fJ \u0010\u0019\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0096@¢\u0006\u0002\u0010\u001cJ \u0010\u001d\u001a\u00020\u00172\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0096@¢\u0006\u0002\u0010 J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0002J\u0010\u0010%\u001a\u00020\"2\u0006\u0010#\u001a\u00020&H\u0002J\u0010\u0010'\u001a\u00020$2\u0006\u0010#\u001a\u00020\"H\u0002J\u0010\u0010(\u001a\u00020&2\u0006\u0010#\u001a\u00020\"H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/exteragram/messenger/api/db/ProfileDao_Impl;", "Lcom/exteragram/messenger/api/db/ProfileDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfProfileDTO", "Landroidx/room/EntityInsertAdapter;", "Lcom/exteragram/messenger/api/dto/ProfileDTO;", "__converters", "Lcom/exteragram/messenger/api/db/Converters;", "insertAll", _UrlKt.FRAGMENT_ENCODE_SET, "profiles", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAll", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getById", "id", _UrlKt.FRAGMENT_ENCODE_SET, "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteProfiles", _UrlKt.FRAGMENT_ENCODE_SET, "ids", "updateNowPlaying", "newNowPlaying", "Lcom/exteragram/messenger/api/dto/NowPlayingInfoDTO;", "(JLcom/exteragram/messenger/api/dto/NowPlayingInfoDTO;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateBadge", "badge", "Lcom/exteragram/messenger/api/dto/BadgeDTO;", "(JLcom/exteragram/messenger/api/dto/BadgeDTO;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "__ProfileType_enumToString", _UrlKt.FRAGMENT_ENCODE_SET, "_value", "Lcom/exteragram/messenger/api/model/ProfileType;", "__ProfileStatus_enumToString", "Lcom/exteragram/messenger/api/model/ProfileStatus;", "__ProfileType_stringToEnum", "__ProfileStatus_stringToEnum", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nProfileDao_Impl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProfileDao_Impl.kt\ncom/exteragram/messenger/api/db/ProfileDao_Impl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,307:1\n1#2:308\n*E\n"})
/* loaded from: classes.dex */
public final class ProfileDao_Impl implements ProfileDao {
    public static final Companion Companion = null;
    private final Converters __converters;
    private final RoomDatabase __db;
    private final EntityInsertAdapter<ProfileDTO> __insertAdapterOfProfileDTO;

    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H\u0014J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0014¨\u0006\n"}, d2 = {"com/exteragram/messenger/api/db/ProfileDao_Impl$1", "Landroidx/room/EntityInsertAdapter;", "Lcom/exteragram/messenger/api/dto/ProfileDTO;", "createQuery", _UrlKt.FRAGMENT_ENCODE_SET, "bind", _UrlKt.FRAGMENT_ENCODE_SET, "statement", "Landroidx/sqlite/SQLiteStatement;", "entity", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nProfileDao_Impl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProfileDao_Impl.kt\ncom/exteragram/messenger/api/db/ProfileDao_Impl$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,307:1\n1#2:308\n*E\n"})
    /* renamed from: com.exteragram.messenger.api.db.ProfileDao_Impl$1, reason: invalid class name */
    public static final class AnonymousClass1 extends EntityInsertAdapter<ProfileDTO> {
        final /* synthetic */ ProfileDao_Impl this$0;

        public AnonymousClass1(ProfileDao_Impl r1) {
            this.this$0 = r1;
        }

        /* renamed from: bind, reason: avoid collision after fix types in other method */
        public void bind2(SQLiteStatement r5, ProfileDTO r6) {
            r5.bindLong(1, r6.getId());
            r5.bindText(2, ProfileDao_Impl.access$__ProfileType_enumToString(this.this$0, r6.getType()));
            r5.bindText(3, ProfileDao_Impl.access$__ProfileStatus_enumToString(this.this$0, r6.getStatus()));
            String r0 = ProfileDao_Impl.access$get__converters$p(this.this$0).fromBadgeDTO(r6.getBadge());
            if (r0 != null) goto L5;
            r5.bindNull(4);
        L6:
            String r4 = ProfileDao_Impl.access$get__converters$p(this.this$0).fromNowPlayingInfoDTO(r6.getNowPlaying());
            if (r4 != null) goto L9;
            r5.bindNull(5);
        L10:
            Boolean r42 = r6.getDeleted();
            Integer r02 = null;
            if (r42 == null) goto L13;
            Integer r43 = Integer.valueOf(r42.booleanValue() ? 1 : 0);
        L15:
            if (r43 != null) goto L17;
            r5.bindNull(6);
        L18:
            Boolean r44 = r6.getCanChangeBadge();
            if (r44 == null) goto L22;
            r02 = Integer.valueOf(r44.booleanValue() ? 1 : 0);
        L22:
            if (r02 != null) goto L25;
            r5.bindNull(7);
            return;
        L25:
            r5.bindLong(7, r02.intValue());
            return;
        L17:
            r5.bindLong(6, r43.intValue());
            goto L18
        L13:
            r43 = null;
            goto L15
        L9:
            r5.bindText(5, r4);
            goto L10
        L5:
            r5.bindText(4, r0);
            goto L6
        }

        @Override // androidx.room.EntityInsertAdapter
        public /* bridge */ /* synthetic */ void bind(SQLiteStatement r1, ProfileDTO r2) {
            bind2(r1, r2);
        }

        @Override // androidx.room.EntityInsertAdapter
        public String createQuery() {
            return "INSERT OR REPLACE INTO `ProfileDTO` (`id`,`type`,`status`,`badge`,`nowPlaying`,`deleted`,`canChangeBadge`) VALUES (?,?,?,?,?,?,?)";
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/exteragram/messenger/api/db/ProfileDao_Impl$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getRequiredConverters", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/reflect/KClass;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final List<KClass<?>> getRequiredConverters() {
            return CollectionsKt.emptyList();
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    /* loaded from: classes4.dex */
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1 = null;

        static {
            int[] r0 = new int[ProfileType.values().length];
            r0[ProfileType.USER.ordinal()] = 1;     // Catch: NoSuchFieldError -> L12
        L17:
            r0[ProfileType.CHAT.ordinal()] = 2;     // Catch: NoSuchFieldError -> L13
        L6:
            $EnumSwitchMapping$0 = r0;
            int[] r02 = new int[ProfileStatus.values().length];
            r02[ProfileStatus.DEFAULT.ordinal()] = 1;     // Catch: NoSuchFieldError -> L14
        L25:
            r02[ProfileStatus.DEVELOPER.ordinal()] = 2;     // Catch: NoSuchFieldError -> L15
        L21:
            r02[ProfileStatus.SUPPORTER.ordinal()] = 3;     // Catch: NoSuchFieldError -> L16
        L10:
            $EnumSwitchMapping$1 = r02;
        }
    }

    public static int $r8$lambda$2rPikLOPJnXPuTJs1oMdKpUjG0o(String r0, ProfileDao_Impl r1, BadgeDTO r2, long r3, SQLiteConnection r5) {
        SQLiteStatement r02 = r5.prepare(r0);
        String r12 = r1.__converters.fromBadgeDTO(r2);     // Catch: Throwable -> L6
        if (r12 != null) goto L8;
        r02.bindNull(1);     // Catch: Throwable -> L6
    L9:
        r02.bindLong(2, r3);     // Catch: Throwable -> L6
        r02.step();     // Catch: Throwable -> L6
        int r13 = SQLiteConnectionUtil.getTotalChangedRows(r5);     // Catch: Throwable -> L6
        r02.close();
        return r13;
    L8:
        r02.bindText(1, r12);     // Catch: Throwable -> L6
    L6:
        th = move-exception;
        r02.close();
        throw th;
    }

    public static List $r8$lambda$DUl6vDKn1QsJ1JOB_BR5bJxXCD0(String r23, ProfileDao_Impl r24, SQLiteConnection r25) {
        SQLiteStatement r1 = r25.prepare(r23);
        int r2 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "id");     // Catch: Throwable -> L25
        int r3 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "type");     // Catch: Throwable -> L25
        int r4 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "status");     // Catch: Throwable -> L25
        int r5 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "badge");     // Catch: Throwable -> L25
        int r6 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "nowPlaying");     // Catch: Throwable -> L25
        int r7 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "deleted");     // Catch: Throwable -> L25
        int r8 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "canChangeBadge");     // Catch: Throwable -> L25
        ArrayList r9 = new ArrayList();     // Catch: Throwable -> L25
    L4:
        if (r1.step() == false) goto L41;
        long r12 = r1.getLong(r2);     // Catch: Throwable -> L25
        ProfileType r14 = r24.__ProfileType_stringToEnum(r1.getText(r3));     // Catch: Throwable -> L25
        ProfileStatus r15 = r24.__ProfileStatus_stringToEnum(r1.getText(r4));     // Catch: Throwable -> L25
        if (r1.isNull(r5) == false) goto L9;
        String r10 = null;
    L10:
        BadgeDTO r16 = r24.__converters.toBadgeDTO(r10);     // Catch: Throwable -> L25
        if (r1.isNull(r6) == false) goto L13;
        String r102 = null;
    L14:
        NowPlayingInfoDTO r17 = r24.__converters.toNowPlayingInfoDTO(r102);     // Catch: Throwable -> L25
        if (r1.isNull(r7) == false) goto L17;
        Integer r103 = null;
    L19:
        if (r103 != null) goto L21;
        Boolean r104 = null;
    L29:
        if (r1.isNull(r8) == false) goto L31;
        long r19 = r12;
        Integer r11 = null;
    L32:
        if (r11 != null) goto L34;
        Boolean r112 = null;
    L39:
        r9.add(new ProfileDTO(r19, r14, r15, r16, r17, r104, r112));     // Catch: Throwable -> L25
        goto L4
    L34:
        if (r11.intValue() == 0) goto L36;
        boolean r113 = true;
    L37:
        r112 = Boolean.valueOf(r113);     // Catch: Throwable -> L25
        goto L39
    L36:
        r113 = false;
        goto L37
    L31:
        r19 = r12;
        r11 = Integer.valueOf((int) r1.getLong(r8));     // Catch: Throwable -> L25
        goto L32
    L21:
        if (r103.intValue() == 0) goto L23;
        boolean r105 = true;
    L24:
        r104 = Boolean.valueOf(r105);     // Catch: Throwable -> L25
        goto L29
    L23:
        r105 = false;
        goto L24
    L17:
        r103 = Integer.valueOf((int) r1.getLong(r7));     // Catch: Throwable -> L25
        goto L19
    L13:
        r102 = r1.getText(r6);     // Catch: Throwable -> L25
        goto L14
    L9:
        r10 = r1.getText(r5);     // Catch: Throwable -> L25
        goto L10
    L41:
        r1.close();
        return r9;
    L25:
        th = move-exception;
        r1.close();
        throw th;
    }

    /* renamed from: $r8$lambda$FM395jIgYCp-M8sj_PKKjfDXZ_Y, reason: not valid java name */
    public static int m1099$r8$lambda$FM395jIgYCpM8sj_PKKjfDXZ_Y(String r0, ProfileDao_Impl r1, NowPlayingInfoDTO r2, long r3, SQLiteConnection r5) {
        SQLiteStatement r02 = r5.prepare(r0);
        String r12 = r1.__converters.fromNowPlayingInfoDTO(r2);     // Catch: Throwable -> L6
        if (r12 != null) goto L8;
        r02.bindNull(1);     // Catch: Throwable -> L6
    L9:
        r02.bindLong(2, r3);     // Catch: Throwable -> L6
        r02.step();     // Catch: Throwable -> L6
        int r13 = SQLiteConnectionUtil.getTotalChangedRows(r5);     // Catch: Throwable -> L6
        r02.close();
        return r13;
    L8:
        r02.bindText(1, r12);     // Catch: Throwable -> L6
    L6:
        th = move-exception;
        r02.close();
        throw th;
    }

    public static Unit $r8$lambda$Wij10QS9XusMpsHaps7cQDspT7Q(ProfileDao_Impl r0, List r1, SQLiteConnection r2) {
        r0.__insertAdapterOfProfileDTO.insert(r2, r1);
        return Unit.INSTANCE;
    }

    public static int $r8$lambda$j6f9cnJG2ThNHCJCgms70gjEkcM(String r3, List r4, SQLiteConnection r5) {
        SQLiteStatement r32 = r5.prepare(r3);
        Iterator r42 = r4.iterator();     // Catch: Throwable -> L7
        int r0 = 1;
    L5:
        if (r42.hasNext() == false) goto L9;
        r32.bindLong(r0, ((Number) r42.next()).longValue());     // Catch: Throwable -> L7
        r0 = r0 + 1;     // Catch: Throwable -> L7
        goto L5
    L9:
        r32.step();     // Catch: Throwable -> L7
        int r43 = SQLiteConnectionUtil.getTotalChangedRows(r5);     // Catch: Throwable -> L7
        r32.close();
        return r43;
    L7:
        th = move-exception;
        r32.close();
        throw th;
    }

    public static ProfileDTO $r8$lambda$swADWxB83sScUaHXuBzEBTL7aMI(String r21, long r22, ProfileDao_Impl r24, SQLiteConnection r25) {
        SQLiteStatement r1 = r25.prepare(r21);
        boolean r2 = true;
        r1.bindLong(1, r22);     // Catch: Throwable -> L24
        int r3 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "id");     // Catch: Throwable -> L24
        int r4 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "type");     // Catch: Throwable -> L24
        int r5 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "status");     // Catch: Throwable -> L24
        int r6 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "badge");     // Catch: Throwable -> L24
        int r7 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "nowPlaying");     // Catch: Throwable -> L24
        int r8 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "deleted");     // Catch: Throwable -> L24
        int r9 = SQLiteStatementUtil.getColumnIndexOrThrow(r1, "canChangeBadge");     // Catch: Throwable -> L24
        ProfileDTO r11 = null;
        Boolean r112 = null;
        if (r1.step() == false) goto L39;
        long r13 = r1.getLong(r3);     // Catch: Throwable -> L24
        ProfileType r15 = r24.__ProfileType_stringToEnum(r1.getText(r4));     // Catch: Throwable -> L24
        ProfileStatus r16 = r24.__ProfileStatus_stringToEnum(r1.getText(r5));     // Catch: Throwable -> L24
        if (r1.isNull(r6) == false) goto L8;
        String r32 = null;
    L9:
        BadgeDTO r17 = r24.__converters.toBadgeDTO(r32);     // Catch: Throwable -> L24
        if (r1.isNull(r7) == false) goto L12;
        String r33 = null;
    L13:
        NowPlayingInfoDTO r18 = r24.__converters.toNowPlayingInfoDTO(r33);     // Catch: Throwable -> L24
        if (r1.isNull(r8) == false) goto L16;
        Integer r0 = null;
    L18:
        if (r0 != null) goto L20;
        Boolean r19 = null;
    L28:
        if (r1.isNull(r9) == false) goto L30;
        Integer r02 = null;
    L31:
        if (r02 != null) goto L33;
    L37:
        r11 = new ProfileDTO(r13, r15, r16, r17, r18, r19, r112);     // Catch: Throwable -> L24
        goto L39
    L33:
        if (r02.intValue() != 0) goto L36;
        r2 = false;
    L36:
        r112 = Boolean.valueOf(r2);     // Catch: Throwable -> L24
        goto L37
    L30:
        r02 = Integer.valueOf((int) r1.getLong(r9));     // Catch: Throwable -> L24
        goto L31
    L20:
        if (r0.intValue() == 0) goto L22;
        boolean r03 = true;
    L23:
        r19 = Boolean.valueOf(r03);     // Catch: Throwable -> L24
        goto L28
    L22:
        r03 = false;
        goto L23
    L16:
        r0 = Integer.valueOf((int) r1.getLong(r8));     // Catch: Throwable -> L24
        goto L18
    L12:
        r33 = r1.getText(r7);     // Catch: Throwable -> L24
        goto L13
    L8:
        r32 = r1.getText(r6);     // Catch: Throwable -> L24
    L39:
        r1.close();
        return r11;
    L24:
        th = move-exception;
        r1.close();
        throw th;
    }

    static {
        Companion = new Companion(null);
    }

    public ProfileDao_Impl(RoomDatabase r2) {
        this.__converters = new Converters();
        this.__db = r2;
        this.__insertAdapterOfProfileDTO = new AnonymousClass1(this);
    }

    private final String __ProfileStatus_enumToString(ProfileStatus r1) {
        int r0 = WhenMappings.$EnumSwitchMapping$1[r1.ordinal()];
        if (r0 != 1) goto L5;
        return PluginsConstants.Strategy.DEFAULT;
    L5:
        if (r0 != 2) goto L7;
        return "DEVELOPER";
    L7:
        if (r0 != 3) goto L10;
        return "SUPPORTER";
    L10:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    private final ProfileStatus __ProfileStatus_stringToEnum(String r2) {
        int r1 = r2.hashCode();
        if (r1 == (-2032180703)) goto L17;
        if (r1 == (-1589053526)) goto L13;
        if (r1 == (-1528175460)) goto L9;
    L20:
        f$$ExternalSyntheticBUOutline1.m("Can't convert value to enum, unknown value: ".concat(r2));
        return null;
    L9:
        if (r2.equals("SUPPORTER") == false) goto L20;
        return ProfileStatus.SUPPORTER;
    L13:
        if (r2.equals("DEVELOPER") == false) goto L20;
        return ProfileStatus.DEVELOPER;
    L17:
        if (r2.equals(PluginsConstants.Strategy.DEFAULT) == false) goto L20;
        return ProfileStatus.DEFAULT;
    }

    private final String __ProfileType_enumToString(ProfileType r1) {
        int r0 = WhenMappings.$EnumSwitchMapping$0[r1.ordinal()];
        if (r0 != 1) goto L5;
        return "USER";
    L5:
        if (r0 != 2) goto L8;
        return "CHAT";
    L8:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    private final ProfileType __ProfileType_stringToEnum(String r1) {
        if (Intrinsics.areEqual(r1, "USER") == false) goto L7;
        return ProfileType.USER;
    L7:
        if (Intrinsics.areEqual(r1, "CHAT") == true) goto L9;
        Native$$ExternalSyntheticBUOutline5.m("Can't convert value to enum, unknown value: ", r1);
        return null;
    L9:
        return ProfileType.CHAT;
    }

    public static final /* synthetic */ String access$__ProfileStatus_enumToString(ProfileDao_Impl r0, ProfileStatus r1) {
        return r0.__ProfileStatus_enumToString(r1);
    }

    public static final /* synthetic */ String access$__ProfileType_enumToString(ProfileDao_Impl r0, ProfileType r1) {
        return r0.__ProfileType_enumToString(r1);
    }

    public static final /* synthetic */ Converters access$get__converters$p(ProfileDao_Impl r0) {
        return r0.__converters;
    }

    @Override // com.exteragram.messenger.api.db.ProfileDao
    public Object deleteProfiles(List<Long> r3, Continuation<? super Integer> r4) {
        StringBuilder r0 = new StringBuilder();
        r0.append("DELETE FROM ProfileDTO WHERE id IN (");
        StringUtil.appendPlaceholders(r0, r3.size());
        r0.append(")");
        String r02 = r0.toString();
        return DBUtil.performSuspending(this.__db, false, true, new ProfileDao_Impl$$ExternalSyntheticLambda2(r02, r3), r4);
    }

    @Override // com.exteragram.messenger.api.db.ProfileDao
    public Object getAll(Continuation<? super List<ProfileDTO>> r4) {
        return DBUtil.performSuspending(this.__db, true, false, new ProfileDao_Impl$$ExternalSyntheticLambda4("SELECT * FROM ProfileDTO", this), r4);
    }

    @Override // com.exteragram.messenger.api.db.ProfileDao
    public Object getById(long r4, Continuation<? super ProfileDTO> r6) {
        return DBUtil.performSuspending(this.__db, true, false, new ProfileDao_Impl$$ExternalSyntheticLambda5("SELECT * FROM ProfileDTO WHERE id = ?", r4, this), r6);
    }

    @Override // com.exteragram.messenger.api.db.ProfileDao
    public Object insertAll(List<ProfileDTO> r3, Continuation<? super Unit> r4) {
        Object r2 = DBUtil.performSuspending(this.__db, false, true, new ProfileDao_Impl$$ExternalSyntheticLambda3(this, r3), r4);
        if (r2 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r2;
    L6:
        return Unit.INSTANCE;
    }

    @Override // com.exteragram.messenger.api.db.ProfileDao
    public Object updateBadge(long r8, BadgeDTO r10, Continuation<? super Integer> r11) {
        return DBUtil.performSuspending(this.__db, false, true, new ProfileDao_Impl$$ExternalSyntheticLambda1("UPDATE ProfileDTO SET badge = ? WHERE id = ?", this, r10, r8), r11);
    }

    @Override // com.exteragram.messenger.api.db.ProfileDao
    public Object updateNowPlaying(long r8, NowPlayingInfoDTO r10, Continuation<? super Integer> r11) {
        return DBUtil.performSuspending(this.__db, false, true, new ProfileDao_Impl$$ExternalSyntheticLambda0("UPDATE ProfileDTO SET nowPlaying = ? WHERE id = ?", this, r10, r8), r11);
    }
}
