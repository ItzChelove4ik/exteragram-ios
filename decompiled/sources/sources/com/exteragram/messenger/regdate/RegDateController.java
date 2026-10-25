package com.exteragram.messenger.regdate;

import androidx.collection.LruCache;
import com.exteragram.messenger.api.db.DatabaseHelper;
import com.exteragram.messenger.api.dto.RegDateDTO;
import com.exteragram.messenger.api.model.RegDateFlag;
import com.exteragram.messenger.backup.InvisibleEncryptor;
import com.exteragram.messenger.badges.BadgesController;
import com.exteragram.messenger.utils.chats.ChatUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sun.jna.Callback;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\b\u0010\tJ%\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u00062\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n¢\u0006\u0004\b\u000e\u0010\u000fJ-\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\n¢\u0006\u0004\b\u0012\u0010\u0013J\u001f\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0015\u0010\u0016J\u0015\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR \u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b0\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001e¨\u0006 "}, d2 = {"Lcom/exteragram/messenger/regdate/RegDateController;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "currentAccount", "<init>", "(I)V", _UrlKt.FRAGMENT_ENCODE_SET, "userId", "findUserRegistrationDate", "(J)J", "Ljava/util/function/Consumer;", "Lcom/exteragram/messenger/api/dto/RegDateDTO;", Callback.METHOD_NAME, _UrlKt.FRAGMENT_ENCODE_SET, "fetchRegistrationDate", "(JLjava/util/function/Consumer;)V", "timestamp", _UrlKt.FRAGMENT_ENCODE_SET, "addRegistrationDate", "(JJLjava/util/function/Consumer;)V", "dto", "formatRegistrationDate", "(JLcom/exteragram/messenger/api/dto/RegDateDTO;)Ljava/lang/String;", "getUserRegistrationDate", "(J)Ljava/lang/String;", "I", "getCurrentAccount", "()I", "Landroidx/collection/LruCache;", "regDateCache", "Landroidx/collection/LruCache;", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class RegDateController {
    public static final Companion Companion = null;
    private static final RegDateController[] Instance = null;
    private static final Object[] lockObjects = null;
    private static Long[] regDates;
    private static Long[] regIds;
    private final int currentAccount;
    private final LruCache<Long, RegDateDTO> regDateCache;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\b\u0010\u0011\u001a\u00020\u0012H\u0002R\u0018\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0018\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\fR\u0018\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/exteragram/messenger/regdate/RegDateController$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Instance", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/exteragram/messenger/regdate/RegDateController;", "[Lcom/exteragram/messenger/regdate/RegDateController;", "lockObjects", "[Ljava/lang/Object;", "regIds", _UrlKt.FRAGMENT_ENCODE_SET, "[Ljava/lang/Long;", "regDates", "getInstance", "num", _UrlKt.FRAGMENT_ENCODE_SET, "initializeRegIds", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static final /* synthetic */ void access$initializeRegIds(Companion r0) {
            r0.initializeRegIds();
        }

        private final void initializeRegIds() {
            if (RegDateController.access$getRegIds$cp() == null) goto L14;
            return;
        L14:
            InputStream r6 = ApplicationLoader.applicationContext.getAssets().open("extera/registration_dates.bin");     // Catch: IOException -> L11
            byte[] r0 = new byte[r6.available()];     // Catch: IOException -> L11
            r6.read(r0);     // Catch: IOException -> L11
            r6.close();     // Catch: IOException -> L11
            String r62 = InvisibleEncryptor.decode(new String(r0, StandardCharsets.UTF_8));     // Catch: IOException -> L11
            Set<Map.Entry<String, JsonElement>> r63 = ((JsonObject) new Gson().fromJson(r62, JsonObject.class)).entrySet();     // Catch: IOException -> L11
            Long[] r02 = new Long[r63.size()];     // Catch: IOException -> L11
            Long[] r1 = new Long[r63.size()];     // Catch: IOException -> L11
            Iterator<Map.Entry<String, JsonElement>> r64 = r63.iterator();     // Catch: IOException -> L11
            int r2 = 0;
        L7:
            if (r64.hasNext() == false) goto L9;
            Map.Entry<String, JsonElement> r3 = r64.next();     // Catch: IOException -> L11
            r02[r2] = Long.valueOf(Long.parseLong(r3.getKey()));     // Catch: IOException -> L11
            r1[r2] = Long.valueOf(r3.getValue().getAsLong());     // Catch: IOException -> L11
            r2 = r2 + 1;     // Catch: IOException -> L11
            goto L7
        L9:
            RegDateController.access$setRegIds$cp((Long[]) ArraysKt.requireNoNulls(r02));     // Catch: IOException -> L11
            RegDateController.access$setRegDates$cp((Long[]) ArraysKt.requireNoNulls(r1));     // Catch: IOException -> L11
            return;
        L11:
            e = move-exception;
            FileLog.e(e);
        }

        @JvmStatic
        public final RegDateController getInstance(int r3) {
            RegDateController r2 = RegDateController.access$getInstance$cp()[r3];
            if (r2 != null) goto L16;
            Object r22 = RegDateController.access$getLockObjects$cp()[r3];
            monitor-enter(r22);
            RegDateController r0 = RegDateController.access$getInstance$cp()[r3];     // Catch: Throwable -> L9
            if (r0 != null) goto L11;
            r0 = new RegDateController(r3);     // Catch: Throwable -> L9
            RegDateController.access$getInstance$cp()[r3] = r0;     // Catch: Throwable -> L9
        L11:
            Unit r32 = Unit.INSTANCE;     // Catch: Throwable -> L9
            monitor-exit(r22);
            return r0;
        L9:
            th = move-exception;
            throw th;
        L16:
            return r2;
        }
    }

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[RegDateFlag.values().length];
            r0[RegDateFlag.LT.ordinal()] = 1;     // Catch: NoSuchFieldError -> L7
        L9:
            r0[RegDateFlag.ET.ordinal()] = 2;     // Catch: NoSuchFieldError -> L8
        L5:
            $EnumSwitchMapping$0 = r0;
        }
    }

    public static void $r8$lambda$4zGtL59Ghvw9LsLzVVX2EhOuQzk(String r4, RegDateController r5, long r6, Consumer r8) {
        if (r4 != null) goto L5;
    L11:
        r8.accept(null);
        return;
    L5:
        if (StringsKt.startsWith$default(r4, "failed", false, 2, null) == true) goto L11;
        RegDateDTO r42 = (RegDateDTO) new Gson().fromJson(r4, RegDateDTO.class);     // Catch: Exception -> L8
        r5.regDateCache.put(Long.valueOf(r6), r42);     // Catch: Exception -> L8
        r8.accept(r42);     // Catch: Exception -> L8
        return;
    L8:
        e = move-exception;
        FileLog.e(e);
        r8.accept(null);
    }

    public static void $r8$lambda$G1c2QSgvg4WDlLumPpNREmHk9vw(Consumer r8, long r9, long r11, RegDateController r13, Boolean r14) {
        AndroidUtilities.runOnUIThread(new RegDateController$$ExternalSyntheticLambda2(r14, r8, r9, r11, r13));
    }

    public static void $r8$lambda$HJm1Cd4BVHUq7LLWQik2VyJ66r0(Boolean r1, Consumer r2, long r3, long r5, RegDateController r7) {
        if (r1.booleanValue() == false) goto L6;
        r2.accept("ok");
        return;
    L6:
        ChatUtils.getInstance(r7.currentAccount).sendBotRequest("addregdate " + r3 + " " + r5, false, new RegDateController$$ExternalSyntheticLambda4(r3, r2));
    }

    public static void $r8$lambda$JY3aCSNNAp9E9x8zGEzBrRwCo8k(long r1, Consumer r3, String r4) {
        if (Intrinsics.areEqual(r4, "ok") == false) goto L5;
        DatabaseHelper.setRegDateAdded(r1);
    L5:
        if (r4 != null) goto L7;
        r4 = "no results";
    L7:
        r3.accept(r4);
    }

    /* renamed from: $r8$lambda$c-V0ZmCO03vn1l5cswOIykTe7CA, reason: not valid java name */
    public static void m1725$r8$lambda$cV0ZmCO03vn1l5cswOIykTe7CA(RegDateController r6, long r7, Consumer r9, String r10) {
        AndroidUtilities.runOnUIThread(new RegDateController$$ExternalSyntheticLambda3(r10, r6, r7, r9));
    }

    static {
        Companion = new Companion(null);
        Instance = new RegDateController[16];
        Object[] r1 = new Object[16];
        int r2 = 0;
    L3:
        if (r2 >= 16) goto L5;
        r1[r2] = new Object();
        r2 = r2 + 1;
        goto L3
    L5:
        lockObjects = r1;
    }

    public RegDateController(int r2) {
        this.currentAccount = r2;
        this.regDateCache = new LruCache(256);
    }

    public static final /* synthetic */ RegDateController[] access$getInstance$cp() {
        return Instance;
    }

    public static final /* synthetic */ Object[] access$getLockObjects$cp() {
        return lockObjects;
    }

    public static final /* synthetic */ Long[] access$getRegIds$cp() {
        return regIds;
    }

    public static final /* synthetic */ void access$setRegDates$cp(Long[] r0) {
        regDates = r0;
    }

    public static final /* synthetic */ void access$setRegIds$cp(Long[] r0) {
        regIds = r0;
    }

    private final long findUserRegistrationDate(long r11) {
        Companion.access$initializeRegIds(Companion);
        TLRPC.UserFull r0 = MessagesController.getInstance(this.currentAccount).getUserFull(r11);
        MessagesController.DialogPhotos r10 = MessagesController.getInstance(this.currentAccount).getDialogPhotos(r11);
        if (r0 == null) goto L5;
        TLRPC.Photo r1 = r0.profile_photo;
    L6:
        if (r1 == null) goto L8;
        int r02 = r0.profile_photo.date;
    L9:
        if (r10 == null) goto L20;
        if (r10.photos.isEmpty() == true) goto L20;
        Iterator<TLRPC.Photo> r102 = r10.photos.iterator();
    L14:
        if (r102.hasNext() == false) goto L20;
        TLRPC.Photo r12 = r102.next();
        if (r12 == null) goto L14;
        r02 = RangesKt.coerceAtMost(r12.date, r02);
    L20:
        if (r11 >= regIds[0].longValue()) goto L23;
        long r103 = regDates[0].longValue();
    L30:
        return RangesKt.coerceAtMost(r02, r103);
    L23:
        if (r11 <= regIds[r10.length - 1].longValue()) goto L25;
        r103 = regDates[regIds.length - 1].longValue();
        goto L30
    L25:
        int r104 = Arrays.binarySearch(regIds, Long.valueOf(r11));
        if (r104 < 0) goto L28;
        r103 = regDates[r104].longValue();
        goto L30
    L28:
        int r105 = -r104;
        int r13 = r105 - 2;
        int r106 = r105 - 1;
        long r2 = regIds[r13].longValue();
        long r4 = regIds[r106].longValue();
        long r6 = regDates[r13].longValue();
        long r8 = regDates[r106].longValue();
        r103 = (long) (r6 + ((r8 - r6) * ((r11 - r2) / (r4 - r2))));
        goto L30
    L8:
        r02 = Integer.MAX_VALUE;
        goto L9
    L5:
        r1 = null;
        goto L6
    }

    @JvmStatic
    public static final RegDateController getInstance(int r1) {
        return Companion.getInstance(r1);
    }

    public final void addRegistrationDate(long r8, long r10, Consumer<String> r12) {
        if (BadgesController.isDeveloper$default(BadgesController.INSTANCE, null, 1, null) == true) goto L6;
        r12.accept("прости, но нет");
        return;
    L6:
        DatabaseHelper.isRegDateAdded(r8, new RegDateController$$ExternalSyntheticLambda0(r12, r8, r10, this));
    }

    public final void fetchRegistrationDate(long r4, Consumer<RegDateDTO> r6) {
        if (BadgesController.hasBadge$default(BadgesController.INSTANCE, null, 1, null) == true) goto L6;
        r6.accept(null);
        return;
    L6:
        RegDateDTO r0 = this.regDateCache.get(Long.valueOf(r4));
        if (r0 == null) goto L10;
        r6.accept(r0);
        return;
    L10:
        ChatUtils.getInstance(this.currentAccount).sendBotRequest("regdate " + r4, false, new RegDateController$$ExternalSyntheticLambda1(this, r4, r6));
    }

    public final String formatRegistrationDate(long r6, RegDateDTO r8) {
        if (r8 == null) goto L4;
        String r0 = LocaleController.formatYearMont(r8.getTimestamp(), true);
        String r1 = ContactsController.formatName(MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r6)));
        RegDateFlag r82 = r8.getFlag();
        if (r6 != UserConfig.getInstance(this.currentAccount).clientUserId) goto L16;
        int r5 = WhenMappings.$EnumSwitchMapping$0[r82.ordinal()];
        if (r5 == 1) goto L15;
        if (r5 == 2) goto L13;
        return LocaleController.formatString(R.string.CreationDateSelfApproximately, new Object[]{r0});
    L13:
        return LocaleController.formatString(R.string.CreationDateSelfLater, new Object[]{r0});
    L15:
        return LocaleController.formatString(R.string.CreationDateSelfEarlier, new Object[]{r0});
    L16:
        int r52 = WhenMappings.$EnumSwitchMapping$0[r82.ordinal()];
        if (r52 == 1) goto L24;
        if (r52 == 2) goto L22;
        return LocaleController.formatString(R.string.CreationDateUserApproximately, new Object[]{r1, r0});
    L22:
        return LocaleController.formatString(R.string.CreationDateUserLater, new Object[]{r1, r0});
    L24:
        return LocaleController.formatString(R.string.CreationDateUserEarlier, new Object[]{r1, r0});
    L4:
        return getUserRegistrationDate(r6);
    }

    public final String getUserRegistrationDate(long r7) {
        long r0 = findUserRegistrationDate(r7);
        String r2 = LocaleController.formatDateChat(r0);
        String r3 = ContactsController.formatName(MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r7)));
        if (r7 != UserConfig.getInstance(this.currentAccount).clientUserId) goto L15;
        if (Long.valueOf(r0).equals(regDates[0]) == false) goto L9;
        return LocaleController.formatString(R.string.CreationDateSelfEarlier, new Object[]{r2});
    L9:
        if (Long.valueOf(r0).equals(regDates[r7.length - 1]) == false) goto L13;
        return LocaleController.formatString(R.string.CreationDateSelfLater, new Object[]{r2});
    L13:
        return LocaleController.formatString(R.string.CreationDateSelfApproximately, new Object[]{r2});
    L15:
        if (Long.valueOf(r0).equals(regDates[0]) == false) goto L19;
        return LocaleController.formatString(R.string.CreationDateUserEarlier, new Object[]{r3, r2});
    L19:
        if (Long.valueOf(r0).equals(regDates[r7.length - 1]) == false) goto L23;
        return LocaleController.formatString(R.string.CreationDateUserLater, new Object[]{r3, r2});
    L23:
        return LocaleController.formatString(R.string.CreationDateUserApproximately, new Object[]{r3, r2});
    }
}
