package org.telegram.messenger;

import android.os.SystemClock;
import android.util.Pair;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.exteragram.messenger.updater.UpdaterUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_bots;
import org.telegram.tgnet.tl.TL_ephemeral;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Stories.StoriesController;

/* loaded from: classes.dex */
public class FileRefController extends BaseController {
    private static volatile FileRefController[] Instance;
    private ArrayList<Waiter> favStickersWaiter;
    private long lastCleanupTime;
    private HashMap<String, ArrayList<Requester>> locationRequester;
    private HashMap<TLObject, Object[]> multiMediaCache;
    private HashMap<String, ArrayList<Requester>> parentRequester;
    private ArrayList<Waiter> recentStickersWaiter;
    private HashMap<String, CachedResult> responseCache;
    private ArrayList<Waiter> savedGifsWaiters;
    private ArrayList<Waiter> wallpaperWaiters;

    public static class CachedResult {
        private long firstQueryTime;
        private TLObject response;

        /* renamed from: -$$Nest$fgetfirstQueryTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long m4069$$Nest$fgetfirstQueryTime(CachedResult r2) {
            return r2.firstQueryTime;
        }

        /* renamed from: -$$Nest$fgetresponse, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLObject m4070$$Nest$fgetresponse(CachedResult r0) {
            return r0.response;
        }

        /* renamed from: -$$Nest$fputfirstQueryTime, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4071$$Nest$fputfirstQueryTime(CachedResult r0, long r1) {
            r0.firstQueryTime = r1;
        }

        /* renamed from: -$$Nest$fputresponse, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4072$$Nest$fputresponse(CachedResult r0, TLObject r1) {
            r0.response = r1;
        }

        private CachedResult() {
        }

        public /* synthetic */ CachedResult(FileRefControllerIA r1) {
            this();
        }
    }

    /* loaded from: classes5.dex */
    public static class Requester {
        private Object[] args;
        private boolean completed;
        private TLRPC.InputFileLocation location;
        private String locationKey;

        /* renamed from: -$$Nest$fgetargs, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Object[] m4073$$Nest$fgetargs(Requester r0) {
            return r0.args;
        }

        /* renamed from: -$$Nest$fgetcompleted, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m4074$$Nest$fgetcompleted(Requester r0) {
            return r0.completed;
        }

        /* renamed from: -$$Nest$fgetlocation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TLRPC.InputFileLocation m4075$$Nest$fgetlocation(Requester r0) {
            return r0.location;
        }

        /* renamed from: -$$Nest$fgetlocationKey, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m4076$$Nest$fgetlocationKey(Requester r0) {
            return r0.locationKey;
        }

        /* renamed from: -$$Nest$fputargs, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4077$$Nest$fputargs(Requester r0, Object[] r1) {
            r0.args = r1;
        }

        /* renamed from: -$$Nest$fputcompleted, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4078$$Nest$fputcompleted(Requester r0, boolean r1) {
            r0.completed = r1;
        }

        /* renamed from: -$$Nest$fputlocation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4079$$Nest$fputlocation(Requester r0, TLRPC.InputFileLocation r1) {
            r0.location = r1;
        }

        /* renamed from: -$$Nest$fputlocationKey, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m4080$$Nest$fputlocationKey(Requester r0, String r1) {
            r0.locationKey = r1;
        }

        private Requester() {
        }

        public /* synthetic */ Requester(FileRefControllerIA r1) {
            this();
        }
    }

    /* loaded from: classes5.dex */
    public static class Waiter {
        private String locationKey;
        private String parentKey;

        /* renamed from: -$$Nest$fgetlocationKey, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m4081$$Nest$fgetlocationKey(Waiter r0) {
            return r0.locationKey;
        }

        /* renamed from: -$$Nest$fgetparentKey, reason: not valid java name */
        public static /* bridge */ /* synthetic */ String m4082$$Nest$fgetparentKey(Waiter r0) {
            return r0.parentKey;
        }

        public Waiter(String r1, String r2) {
            this.locationKey = r1;
            this.parentKey = r2;
        }
    }

    public static /* synthetic */ void $r8$lambda$2OHiWI1UXGwPuBabnEX2xeeojUw(TLObject r0, TLRPC.TL_error r1) {
    }

    public static /* synthetic */ void $r8$lambda$4jnsxsjbR0zavwtQm1Bi64OisRY(TLRPC.TL_theme r0) {
        Theme.setThemeFileReference(r0);
    }

    public static /* synthetic */ void $r8$lambda$67TLyPpeSQNksvyGHayGjOAKoos(FileRefController r0, String r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$requestReferenceFromServer$9(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$82j_ovSGmaq3mR0_GJCdOHPqtqI(FileRefController r0, String r1, String r2, StoriesController.BotPreview r3) {
        r0.lambda$requestReferenceFromServer$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$C8vjX2jhR2Vcf3F5mZlBbrO4tXA(FileRefController r0, String r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$requestReferenceFromServer$7(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$C_2yPO31_P785mpXN0N63Or_8x0(FileRefController r0, String r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$requestReferenceFromServer$29(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$DTN4ShTNF2O42OMLVZWb4DOgPuQ(FileRefController r0, String r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$requestReferenceFromServer$2(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$EFG6a0rKESJRADMb510aDQdDr-k, reason: not valid java name */
    public static /* synthetic */ void m4060$r8$lambda$EFG6a0rKESJRADMb510aDQdDrk(FileRefController r0, String r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$requestReferenceFromServer$25(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$FBMYTnN7R-MYeO_JDJJXs3ADOi8, reason: not valid java name */
    public static /* synthetic */ void m4061$r8$lambda$FBMYTnN7RMYeO_JDJJXs3ADOi8(FileRefController r0, Requester r1) {
        r0.lambda$onUpdateObjectReference$36(r1);
    }

    public static /* synthetic */ void $r8$lambda$FPJkcFobcG_Up5YRFSQY8eLH7Ac(FileRefController r0, String r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$requestReferenceFromServer$15(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$Ie0LLqbv_iWm0sBlvsF35jLLdBo(FileRefController r0, String r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$requestReferenceFromServer$8(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$J0nNpxj9Hk0zcAH1jIMOLoaNe70(FileRefController r0, String r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$requestReferenceFromServer$13(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$KiuatnwuKdxSaJlLBVbmDae5gts(FileRefController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$requestReferenceFromServer$19(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$LKYpyN2Rfn0O7YaDmXadW8o0BDQ(FileRefController r0, TLRPC.TL_messages_stickerSet r1) {
        r0.lambda$onRequestComplete$50(r1);
    }

    /* renamed from: $r8$lambda$LOzs9xE-0LGkye4fxWnukAlDOw8, reason: not valid java name */
    public static /* synthetic */ void m4062$r8$lambda$LOzs9xE0LGkye4fxWnukAlDOw8(FileRefController r0, String r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$requestReferenceFromServer$3(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$LjE7bGOyQklMXrEPwFy4Z_pAqYk(FileRefController r0, String r1, String r2, StoriesController.BotPreview r3) {
        r0.lambda$requestReferenceFromServer$0(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$NgzfdWN5HHAX1qHK0f0MVILPEP0(FileRefController r0, String r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$requestReferenceFromServer$10(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$NmgJNxopatYsHAj0WzeM-nhKqbM, reason: not valid java name */
    public static /* synthetic */ void m4063$r8$lambda$NmgJNxopatYsHAj0WzeMnhKqbM(FileRefController r0, Requester r1) {
        r0.lambda$onUpdateObjectReference$33(r1);
    }

    public static /* synthetic */ void $r8$lambda$QTprkZ8o2dTxOzlMd_dvBRJkmNA(FileRefController r0, String r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$requestReferenceFromServer$27(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$RDdUxGBZ1-bcmE_o5kisMQZX0rs, reason: not valid java name */
    public static /* synthetic */ void m4064$r8$lambda$RDdUxGBZ1bcmE_o5kisMQZX0rs(FileRefController r0, String r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$requestReferenceFromServer$28(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$RgUKJ8aaXPVJtnEMq_9aaqAbt8I(FileRefController r0, String r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$requestReferenceFromServer$16(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$T98R8Tcgp3A40FKNJLxLYlzQrEA(FileRefController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$requestReferenceFromServer$20(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$TnBVAujtfk0a9yiAMVfDaV7V4c8(FileRefController r0, String r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$requestReferenceFromServer$12(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$Tv28eWBb4q7hyd0o1AobIADzLCc(TLObject r0, TLRPC.TL_error r1) {
    }

    /* renamed from: $r8$lambda$U-XPjEBnVhRWJJ0c_wVXq_zXG3A, reason: not valid java name */
    public static /* synthetic */ void m4065$r8$lambda$UXPjEBnVhRWJJ0c_wVXq_zXG3A(FileRefController r0, TLRPC.TL_messages_sendMedia r1, Object[] r2) {
        r0.lambda$onUpdateObjectReference$31(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$W1MXbvQPZJlXXKhrgV3XxGwtwDU(FileRefController r0, TLRPC.TL_messages_sendMultiMedia r1, Object[] r2) {
        r0.lambda$sendErrorToObject$41(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$W6mNS2J02YUX2Pu6NR3hTqW6kls(FileRefController r0, String r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$requestReferenceFromServer$11(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$WEkNxJGTiCFi4MUEssadrVTFBt4(FileRefController r0, TLRPC.TL_messages_sendMultiMedia r1, Object[] r2) {
        r0.lambda$onUpdateObjectReference$30(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Ycm1HN5eVu5AVpqN8tIBwapodBk(FileRefController r0, String r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$requestReferenceFromServer$4(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$ZToGQFtw2zognfQdJ83dZILab_A(TLObject r0, TLRPC.TL_error r1) {
    }

    public static /* synthetic */ void $r8$lambda$ZfdfFDqOnmXdqYWngmMsAE4wODk(FileRefController r0, String r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$requestReferenceFromServer$24(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$aUAaC8yU0Gk7t1MQbNkowZAdLiQ(FileRefController r0, String r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$requestReferenceFromServer$26(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$bAlvBx-VzptTqjEisIdguGwQ2lg, reason: not valid java name */
    public static /* synthetic */ void m4066$r8$lambda$bAlvBxVzptTqjEisIdguGwQ2lg(FileRefController r0, String r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$requestReferenceFromServer$23(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$bP6KBJG6eRnD1L1Fw9rrtHGm3Ic(TLObject r0, TLRPC.TL_error r1) {
    }

    public static /* synthetic */ void $r8$lambda$cqHNwF1qK6CjTyebbICmss95cy8(FileRefController r0, Requester r1) {
        r0.lambda$onUpdateObjectReference$35(r1);
    }

    /* renamed from: $r8$lambda$dp_Y7uTQFqP7n8Mlk_i-0F1N47c, reason: not valid java name */
    public static /* synthetic */ void m4067$r8$lambda$dp_Y7uTQFqP7n8Mlk_i0F1N47c(FileRefController r0, String r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$requestReferenceFromServer$17(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$efALURo8rR2dl48evzNxQP_Rs4s(FileRefController r0, String r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$requestReferenceFromServer$14(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$f4DPn7UaYqtJIEseYNsW_4eZI0k(FileRefController r0, String r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$requestReferenceFromServer$5(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$gaHtXvyYlQ47_2aDycwRM5Zvfs4(FileRefController r0, Object[] r1) {
        r0.lambda$sendErrorToObject$44(r1);
    }

    public static /* synthetic */ void $r8$lambda$h7JJ239jgn8HZWj_cvrsxPAruP0(FileRefController r0, Requester r1) {
        r0.lambda$onUpdateObjectReference$34(r1);
    }

    public static /* synthetic */ void $r8$lambda$hK_SRLGU6KR6CdQSLtyDE0tHMAc(FileRefController r0, TLRPC.TL_messages_sendMedia r1, Object[] r2) {
        r0.lambda$sendErrorToObject$42(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ipuYm2pIlaBjxl1BJKGeYc7QxZ0(FileRefController r0, TL_ephemeral.TL_sendMessage r1, Object[] r2) {
        r0.lambda$onUpdateObjectReference$32(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$kINNtMhWzY2gttz00gYbEPrkxuw(FileRefController r0, Object[] r1) {
        r0.lambda$sendErrorToObject$45(r1);
    }

    public static /* synthetic */ void $r8$lambda$lIHXDhgmQFjOU2SXTlRAKgUZatA(FileRefController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$requestReferenceFromServer$18(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$lWmCoDpnqzY9rdEGmNMTbbgNGXM(FileRefController r0, String r1, String r2, TLRPC.TL_help_appUpdate r3, TLRPC.TL_error r4) {
        r0.lambda$requestReferenceFromServer$22(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$q6n373xTdA6ZhJ2_kN2z_KY9DDo(FileRefController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$requestReferenceFromServer$21(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$qdWOMiomm3rB_WGbxj5UKJqlzXc(FileRefController r0, TLRPC.Chat r1) {
        r0.lambda$onRequestComplete$48(r1);
    }

    public static /* synthetic */ void $r8$lambda$rHaFvNvwbYVOaAGt0Zr6c71Qa4Y(FileRefController r0, TL_ephemeral.TL_sendMessage r1, Object[] r2) {
        r0.lambda$sendErrorToObject$43(r1, r2);
    }

    /* renamed from: $r8$lambda$vFCIKAVm7KKfmifYis59-38OlZ4, reason: not valid java name */
    public static /* synthetic */ void m4068$r8$lambda$vFCIKAVm7KKfmifYis5938OlZ4(FileRefController r0, String r1, String r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$requestReferenceFromServer$6(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$wWY47_cmPVZE54QKXcp9WOKkEmo(FileRefController r0, TLRPC.Chat r1) {
        r0.lambda$onRequestComplete$49(r1);
    }

    public static /* synthetic */ void $r8$lambda$yAwu79Me_AQuwZaSF0VMWgEKlgM(FileRefController r0, TLRPC.User r1) {
        r0.lambda$onRequestComplete$47(r1);
    }

    static {
        Instance = new FileRefController[16];
    }

    public FileRefController(int r3) {
        super(r3);
        this.locationRequester = new HashMap();
        this.parentRequester = new HashMap();
        this.responseCache = new HashMap();
        this.multiMediaCache = new HashMap();
        this.lastCleanupTime = SystemClock.elapsedRealtime();
        this.wallpaperWaiters = new ArrayList();
        this.savedGifsWaiters = new ArrayList();
        this.recentStickersWaiter = new ArrayList();
        this.favStickersWaiter = new ArrayList();
    }

    private void broadcastWaitersData(ArrayList<Waiter> r12, TLObject r13, TLRPC.TL_error r14) {
        int r0 = r12.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        Waiter r3 = r12.get(r2);
        String r5 = Waiter.m4081$$Nest$fgetlocationKey(r3);
        String r6 = Waiter.m4082$$Nest$fgetparentKey(r3);
        if (r2 != (r0 - 1)) goto L7;
        boolean r9 = true;
    L8:
        onRequestComplete(r5, r6, r13, r14, r9, false);
        r2 = r2 + 1;
        goto L3
    L7:
        r9 = false;
        goto L8
    L9:
        r12.clear();
    }

    private void cleanupCache() {
        if (Math.abs(SystemClock.elapsedRealtime() - this.lastCleanupTime) < 600000) goto L24;
        this.lastCleanupTime = SystemClock.elapsedRealtime();
        Iterator<Map.Entry<String, CachedResult>> r0 = this.responseCache.entrySet().iterator();
        ArrayList r1 = null;
    L7:
        if (r0.hasNext() == false) goto L13;
        Map.Entry<String, CachedResult> r2 = r0.next();
        CachedResult r3 = r2.getValue();
        if (Math.abs(System.currentTimeMillis() - CachedResult.m4069$$Nest$fgetfirstQueryTime(r3)) < 60000) goto L7;
        if (r1 != null) goto L12;
        r1 = new ArrayList();
    L12:
        r1.add(r2.getKey());
        goto L7
    L13:
        if (r1 == null) goto L17;
        int r02 = r1.size();
        int r22 = 0;
    L15:
        if (r22 >= r02) goto L25;
        this.responseCache.remove(r1.get(r22));
        r22 = r22 + 1;
        goto L15
    L25:
        return;
    L17:
        return;
    }

    private CachedResult getCachedResponse(String r6) {
        CachedResult r0 = this.responseCache.get(r6);
        if (r0 != null) goto L5;
    L8:
        return r0;
    L5:
        if (Math.abs(System.currentTimeMillis() - CachedResult.m4069$$Nest$fgetfirstQueryTime(r0)) < 60000) goto L8;
        this.responseCache.remove(r6);
        return null;
    }

    public static int getFileRefErrorIndex(String r3) {
        if (r3 != null) goto L6;
        return -1;
    L6:
        if (r3.startsWith("FILE_REFERENCE_") == true) goto L8;
    L12:
        return -1;
    L8:
        if (r3.endsWith("_EXPIRED") == false) goto L12;
        return Integer.parseInt(r3.substring(15, r3.length() - 8));
    }

    private byte[] getFileReference(TLRPC.Chat r12, TLRPC.InputFileLocation r13, boolean[] r14, TLRPC.InputFileLocation[] r15) {
        if (r12 == null) goto L27;
        TLRPC.ChatPhoto r1 = r12.photo;
        if (r1 == null) goto L27;
        if ((r13 instanceof TLRPC.TL_inputFileLocation) == true) goto L12;
        if ((r13 instanceof TLRPC.TL_inputPeerPhotoFileLocation) == false) goto L27;
    L12:
        if ((r13 instanceof TLRPC.TL_inputPeerPhotoFileLocation) == false) goto L18;
        r14[0] = true;
        if (getPeerReferenceReplacement(null, r12, false, r13, r15, r14) == true) goto L16;
        return null;
    L16:
        return new byte[0];
    L18:
        byte[] r11 = getFileReference(r1.photo_small, r13, r14);
        if (getPeerReferenceReplacement(null, r12, false, r13, r15, r14) == true) goto L21;
        if (r11 != null) goto L28;
        byte[] r112 = getFileReference(r12.photo.photo_big, r13, r14);
        if (getPeerReferenceReplacement(null, r12, true, r13, r15, r14) == true) goto L26;
        return r112;
    L26:
        return new byte[0];
    L28:
        return r11;
    L21:
        return new byte[0];
    L27:
        return null;
    }

    private byte[] getFileReference(TLRPC.Document r10, ArrayList<TLRPC.Document> r11, TLRPC.InputFileLocation r12, boolean[] r13, TLRPC.InputFileLocation[] r14) {
        if (r10 == null) goto L30;
        if (r12 == null) goto L30;
        int r2 = 0;
        if ((r12 instanceof TLRPC.TL_inputDocumentFileLocation) == true) goto L9;
        int r1 = r10.thumbs.size();
        int r3 = 0;
    L13:
        if (r3 >= r1) goto L23;
        TLRPC.PhotoSize r4 = r10.thumbs.get(r3);
        byte[] r5 = getFileReference(r4, r12, r13);
        if (r13 != null) goto L17;
    L20:
        if (r5 != null) goto L21;
        r3 = r3 + 1;
        goto L13
    L21:
        return r5;
    L17:
        if (r13[0] == false) goto L20;
        TLRPC.TL_inputDocumentFileLocation r9 = new TLRPC.TL_inputDocumentFileLocation();
        r14[0] = r9;
        r9.id = r10.id;
        r9.volume_id = r12.volume_id;
        r9.local_id = r12.local_id;
        r9.access_hash = r10.access_hash;
        byte[] r102 = r10.file_reference;
        r9.file_reference = r102;
        r9.thumb_size = r4.type;
        return r102;
    L23:
        if (r11 == null) goto L30;
    L25:
        if (r2 >= r11.size()) goto L30;
        FileRefController r32 = this;
        TLRPC.InputFileLocation r6 = r12;
        boolean[] r7 = r13;
        TLRPC.InputFileLocation[] r8 = r14;
        byte[] r92 = r32.getFileReference(r11.get(r2), null, r6, r7, r8);
        if (r92 != null) goto L28;
        r2 = r2 + 1;
        FileRefController this = r32;
        r12 = r6;
        r13 = r7;
        r14 = r8;
        goto L25
    L28:
        return r92;
    L9:
        if (r10.id != r12.id) goto L23;
        return r10.file_reference;
    L30:
        return null;
    }

    private byte[] getFileReference(TLRPC.FileLocation r5, TLRPC.InputFileLocation r6, boolean[] r7) {
        byte[] r4 = null;
        if (r5 != null) goto L5;
    L15:
        return r4;
    L5:
        if ((r6 instanceof TLRPC.TL_inputFileLocation) == false) goto L15;
        if (r5.local_id != r6.local_id) goto L15;
        if (r5.volume_id != r6.volume_id) goto L15;
        r4 = r5.file_reference;
        if (r4 != null) goto L15;
        if (r7 == null) goto L15;
        r7[0] = true;
        goto L15
    }

    private byte[] getFileReference(TLRPC.Photo r8, TLRPC.InputFileLocation r9, boolean[] r10, TLRPC.InputFileLocation[] r11) {
        if (r8 != null) goto L6;
        return null;
    L6:
        if ((r9 instanceof TLRPC.TL_inputPhotoFileLocation) == false) goto L13;
        if (r8.id == r9.id) goto L10;
        return null;
    L10:
        return r8.file_reference;
    L13:
        if ((r9 instanceof TLRPC.TL_inputFileLocation) == false) goto L25;
        int r1 = r8.sizes.size();
        int r3 = 0;
    L15:
        if (r3 >= r1) goto L25;
        TLRPC.PhotoSize r4 = r8.sizes.get(r3);
        byte[] r5 = getFileReference(r4, r9, r10);
        if (r10 != null) goto L19;
    L22:
        if (r5 != null) goto L23;
        r3 = r3 + 1;
        goto L15
    L23:
        return r5;
    L19:
        if (r10[0] == false) goto L22;
        TLRPC.TL_inputPhotoFileLocation r7 = new TLRPC.TL_inputPhotoFileLocation();
        r11[0] = r7;
        r7.id = r8.id;
        r7.volume_id = r9.volume_id;
        r7.local_id = r9.local_id;
        r7.access_hash = r8.access_hash;
        byte[] r82 = r8.file_reference;
        r7.file_reference = r82;
        r7.thumb_size = r4.type;
        return r82;
    L25:
        return null;
    }

    private byte[] getFileReference(TLRPC.PhotoSize r2, TLRPC.InputFileLocation r3, boolean[] r4) {
        if (r2 != null) goto L4;
        return null;
    L4:
        if ((r3 instanceof TLRPC.TL_inputFileLocation) == true) goto L7;
        return null;
    L7:
        return getFileReference(r2.location, r3, r4);
    }

    private byte[] getFileReference(TLRPC.User r9, TLRPC.InputFileLocation r10, boolean[] r11, TLRPC.InputFileLocation[] r12) {
        if (r9 == null) goto L18;
        TLRPC.UserProfilePhoto r0 = r9.photo;
        if (r0 != null) goto L6;
        return null;
    L6:
        if ((r10 instanceof TLRPC.TL_inputFileLocation) == false) goto L22;
        byte[] r02 = getFileReference(r0.photo_small, r10, r11);
        if (getPeerReferenceReplacement(r9, null, false, r10, r12, r11) == true) goto L11;
        if (r02 != null) goto L17;
        byte[] r8 = getFileReference(r9.photo.photo_big, r10, r11);
        if (getPeerReferenceReplacement(r9, null, true, r10, r12, r11) == true) goto L16;
        return r8;
    L16:
        return new byte[0];
    L17:
        return r02;
    L11:
        return new byte[0];
    L22:
        return null;
    L18:
        return null;
    }

    private byte[] getFileReference(TLRPC.WebPage r9, TLRPC.InputFileLocation r10, boolean[] r11, TLRPC.InputFileLocation[] r12) {
        byte[] r8 = getFileReference(r9.document, null, r10, r11, r12);
        if (r8 == null) goto L5;
        return r8;
    L5:
        byte[] r82 = getFileReference(r9.photo, r10, r11, r12);
        if (r82 == null) goto L8;
        return r82;
    L8:
        int r102 = 0;
        if (r9.attributes.isEmpty() == true) goto L22;
        int r83 = r9.attributes.size();
        int r112 = 0;
    L11:
        if (r112 >= r83) goto L22;
        TLRPC.WebPageAttribute r122 = r9.attributes.get(r112);
        if ((r122 instanceof TLRPC.TL_webPageAttributeTheme) == false) goto L21;
        TLRPC.TL_webPageAttributeTheme r123 = (TLRPC.TL_webPageAttributeTheme) r122;
        int r6 = r123.documents.size();
        int r7 = 0;
    L16:
        if (r7 >= r6) goto L21;
        byte[] r1 = getFileReference(r123.documents.get(r7), null, r10, r11, r12);
        if (r1 != null) goto L19;
        r7 = r7 + 1;
        goto L16
    L19:
        return r1;
    L21:
        r112 = r112 + 1;
    L22:
        TL_iv.Page r84 = r9.cached_page;
        if (r84 == null) goto L37;
        int r85 = r84.documents.size();
        int r113 = 0;
    L25:
        TL_iv.Page r124 = r9.cached_page;
        if (r113 >= r85) goto L31;
        byte[] r125 = getFileReference(r124.documents.get(r113), null, r10, r11, r12);
        if (r125 != null) goto L29;
        r113 = r113 + 1;
        goto L25
    L29:
        return r125;
    L31:
        int r86 = r124.photos.size();
    L32:
        if (r102 >= r86) goto L47;
        byte[] r114 = getFileReference(r9.cached_page.photos.get(r102), r10, r11, r12);
        if (r114 != null) goto L35;
        r102 = r102 + 1;
        goto L32
    L35:
        return r114;
    L47:
        return null;
    L37:
        return null;
    }

    private byte[] getFileReferenceForMediaImpl(TLRPC.MessageMedia r8, TLRPC.InputFileLocation r9, boolean[] r10, TLRPC.InputFileLocation[] r11) {
        byte[] r0 = null;
        if (r8 != null) goto L5;
        return null;
    L5:
        TLRPC.Document r2 = r8.document;
        if (r2 == null) goto L8;
        FileRefController r1 = this;
        TLRPC.InputFileLocation r4 = r9;
        boolean[] r5 = r10;
        TLRPC.InputFileLocation[] r6 = r11;
        r0 = r1.getFileReference(r2, r8.alt_documents, r4, r5, r6);
    L19:
        if (r0 != null) goto L24;
        TLRPC.Photo r7 = r8.video_cover;
        if (r7 == null) goto L24;
        return r1.getFileReference(r7, r4, r5, r6);
    L24:
        return r0;
    L8:
        r1 = this;
        r4 = r9;
        r5 = r10;
        r6 = r11;
        TLRPC.TL_game r72 = r8.game;
        if (r72 == null) goto L13;
        r0 = r1.getFileReference(r72.document, null, r4, r5, r6);
        if (r0 != null) goto L19;
        r0 = r1.getFileReference(r8.game.photo, r4, r5, r6);
        goto L19
    L13:
        TLRPC.Photo r73 = r8.photo;
        if (r73 == null) goto L16;
        r0 = r1.getFileReference(r73, r4, r5, r6);
        goto L19
    L16:
        TLRPC.WebPage r74 = r8.webpage;
        if (r74 == null) goto L19;
        r0 = r1.getFileReference(r74, r4, r5, r6);
        goto L19
    }

    private byte[] getFileReferenceForPoll(TLRPC.TL_messageMediaPoll r4, TLRPC.InputFileLocation r5, boolean[] r6, TLRPC.InputFileLocation[] r7) {
        if (r4 != null) goto L5;
        return null;
    L5:
        byte[] r0 = getFileReferenceForMediaImpl(r4.attached_media, r5, r6, r7);
        if (r0 != null) goto L12;
        TLRPC.PollResults r1 = r4.results;
        if (r1 == null) goto L12;
        TLRPC.MessageMedia r12 = r1.solution_media;
        if (r12 == null) goto L12;
        r0 = getFileReferenceForMediaImpl(r12, r5, r6, r7);
    L12:
        if (r0 != null) goto L19;
        ArrayList<TLRPC.PollAnswer> r42 = r4.poll.answers;
        if (r42 == null) goto L19;
        int r13 = r42.size();
        int r2 = 0;
    L16:
        if (r2 >= r13) goto L19;
        TLRPC.PollAnswer r02 = r42.get(r2);
        r2 = r2 + 1;
        r0 = getFileReferenceForMediaImpl(r02.media, r5, r6, r7);
        if (r0 == null) goto L16;
    L19:
        return r0;
    }

    private byte[] getFileReferenceForRichMessage(TL_iv.RichMessage r11, TLRPC.InputFileLocation r12, boolean[] r13, TLRPC.InputFileLocation[] r14) {
        byte[] r0 = null;
        if (r11 != null) goto L5;
        return null;
    L5:
        ArrayList<TLRPC.Photo> r1 = r11.photos;
        int r2 = r1.size();
        int r3 = 0;
        int r4 = 0;
    L6:
        if (r4 >= r2) goto L10;
        TLRPC.Photo r02 = r1.get(r4);
        r4 = r4 + 1;
        r0 = getFileReference(r02, r12, r13, r14);
        if (r0 == null) goto L6;
        return r0;
    L10:
        ArrayList<TLRPC.Document> r112 = r11.documents;
        int r15 = r112.size();
    L11:
        if (r3 >= r15) goto L16;
        TLRPC.Document r03 = r112.get(r3);
        r3 = r3 + 1;
        FileRefController r42 = this;
        TLRPC.InputFileLocation r7 = r12;
        boolean[] r8 = r13;
        TLRPC.InputFileLocation[] r9 = r14;
        r0 = r42.getFileReference(r03, null, r7, r8, r9);
        if (r0 != null) goto L14;
        FileRefController this = r42;
        r12 = r7;
        r13 = r8;
        r14 = r9;
        goto L11
    L14:
        return r0;
    L16:
        return r0;
    }

    private Pair<byte[], TLRPC.InputFileLocation> getFileReferenceFromResponse(TLRPC.InputFileLocation r20, String r21, String r22, TLObject r23, Object... r24) {
        TLRPC.InputFileLocation r8 = null;
        if ((r20 instanceof TLRPC.TL_inputFileLocation) == false) goto L5;
    L8:
        TLRPC.InputFileLocation[] r9 = new TLRPC.InputFileLocation[1];
        boolean[] r10 = new boolean[1];
    L10:
        if (r22 == null) goto L17;
        TLObject r5 = r23;
        Pair<byte[], TLRPC.InputFileLocation> r0 = getFileReferenceFromResponse(r20, r21, null, r5, r24);
        if (r0 == null) goto L18;
        byte[] r1 = (byte[]) r0.first;
        Object r02 = r0.second;
        if (r02 == null) goto L20;
        if (r9 == null) goto L20;
        r9[0] = (TLRPC.InputFileLocation) r02;
    L20:
        if ((r5 instanceof StoriesController.BotPreview) == false) goto L28;
        TLRPC.MessageMedia r03 = ((StoriesController.BotPreview) r5).media;
        TLRPC.Document r2 = r03.document;
        if (r2 == null) goto L24;
        TLRPC.InputFileLocation[] r6 = r9;
        r1 = getFileReference(r2, r03.alt_documents, r20, r10, r6);
    L27:
        TLRPC.InputFileLocation[] r92 = r6;
        if (r1 != null) goto L267;
        return null;
    L267:
        if (r92 == null) goto L273;
        TLRPC.InputFileLocation r25 = r92[0];
        if (r25 == null) goto L273;
        r8 = r25;
    L273:
        return new Pair(r1, r8);
    L24:
        r6 = r9;
        boolean[] r3 = r10;
        TLRPC.Photo r04 = r03.photo;
        if (r04 == null) goto L27;
        r1 = getFileReference(r04, r20, r3, r6);
        goto L27
    L28:
        FileRefController r26 = this;
        TLRPC.InputFileLocation r4 = r20;
        r6 = r9;
        boolean[] r32 = r10;
        if ((r5 instanceof TLRPC.messages_Messages) == false) goto L67;
        TLRPC.messages_Messages r05 = (TLRPC.messages_Messages) r5;
        if (r05.messages.isEmpty() == true) goto L64;
        int r52 = r05.messages.size();
        int r93 = 0;
    L33:
        if (r93 >= r52) goto L59;
        TLRPC.Message r102 = r05.messages.get(r93);
        TLRPC.MessageMedia r12 = r102.media;
        if ((r12 instanceof TLRPC.TL_messageMediaPaidMedia) == false) goto L45;
        TLRPC.TL_messageMediaPaidMedia r122 = (TLRPC.TL_messageMediaPaidMedia) r12;
        int r103 = 0;
    L38:
        if (r103 >= r122.extended_media.size()) goto L58;
        TLRPC.MessageExtendedMedia r13 = r122.extended_media.get(r103);
        if ((r13 instanceof TLRPC.TL_messageExtendedMedia) == false) goto L42;
        r1 = r26.getFileReferenceForMediaImpl(((TLRPC.TL_messageExtendedMedia) r13).media, r4, r32, r6);
    L42:
        if (r1 != null) goto L58;
        r103 = r103 + 1;
    L58:
        r93 = r93 + 1;
        goto L33
    L45:
        TL_iv.RichMessage r132 = r102.rich_message;
        if (r132 == null) goto L49;
        r1 = r26.getFileReferenceForRichMessage(r132, r4, r32, r6);
        goto L58
    L49:
        if ((r12 instanceof TLRPC.TL_messageMediaPoll) == false) goto L51;
        r1 = r26.getFileReferenceForPoll((TLRPC.TL_messageMediaPoll) r12, r4, r32, r6);
        goto L58
    L51:
        if (r12 == null) goto L53;
        r1 = r26.getFileReferenceForMediaImpl(r12, r4, r32, r6);
        goto L58
    L53:
        TLRPC.MessageAction r104 = r102.action;
        if ((r104 instanceof TLRPC.TL_messageActionChatEditPhoto) == false) goto L56;
    L57:
        r1 = r26.getFileReference(r104.photo, r4, r32, r6);
        goto L58
    L56:
        if ((r104 instanceof TLRPC.TL_messageActionSuggestProfilePhoto) == false) goto L58;
    L59:
        if (r1 != null) goto L27;
        r26.getMessagesStorage().replaceMessageIfExists(r05.messages.get(0), r05.users, r05.chats, true);
        if (BuildVars.DEBUG_VERSION == false) goto L27;
        FileLog.d("file ref not found in messages, replacing message");
        goto L27
    L64:
        if (BuildVars.DEBUG_VERSION == false) goto L27;
        FileLog.d("empty messages, file ref not found");
        goto L27
    L67:
        if ((r5 instanceof TLRPC.TL_help_premiumPromo) == false) goto L75;
        ArrayList<TLRPC.Document> r06 = ((TLRPC.TL_help_premiumPromo) r5).videos;
        int r7 = r06.size();
        int r53 = 0;
    L69:
        if (r53 >= r7) goto L27;
        int r94 = r53 + 1;
        TLRPC.Document r14 = r06.get(r53);
        boolean[] r54 = r32;
        byte[] r27 = r26.getFileReference(r14, null, r4, r54, r6);
        r32 = r54;
        if (r27 != null) goto L72;
        r4 = r20;
        r1 = r27;
        r53 = r94;
        r26 = this;
    L72:
        r1 = r27;
        goto L27
    L75:
        if ((r5 instanceof TLRPC.TL_messages_availableReactions) == false) goto L101;
        TLRPC.TL_messages_availableReactions r07 = (TLRPC.TL_messages_availableReactions) r5;
        getMediaDataController().processLoadedReactions(r07.reactions, r07.hash, (int) (System.currentTimeMillis() / 1000), false);
        ArrayList<TLRPC.TL_availableReaction> r08 = r07.reactions;
        int r72 = r08.size();
        int r28 = 0;
    L77:
        if (r28 >= r72) goto L27;
        int r95 = r28 + 1;
        TLRPC.TL_availableReaction r105 = r08.get(r28);
        boolean[] r55 = r32;
        r27 = getFileReference(r105.static_icon, null, r20, r55, r6);
        if (r27 != null) goto L72;
        r27 = getFileReference(r105.appear_animation, null, r20, r55, r6);
        if (r27 != null) goto L72;
        r27 = getFileReference(r105.select_animation, null, r20, r55, r6);
        if (r27 != null) goto L72;
        r27 = getFileReference(r105.activate_animation, null, r20, r55, r6);
        if (r27 != null) goto L72;
        r27 = getFileReference(r105.effect_animation, null, r20, r55, r6);
        if (r27 != null) goto L72;
        r27 = getFileReference(r105.around_animation, null, r20, r55, r6);
        if (r27 != null) goto L72;
        r27 = getFileReference(r105.center_icon, null, r20, r55, r6);
        r32 = r55;
        if (r27 != null) goto L72;
        r1 = r27;
        r28 = r95;
        goto L77
    L101:
        if ((r5 instanceof TLRPC.TL_users_userFull) == false) goto L110;
        TLRPC.TL_users_userFull r09 = (TLRPC.TL_users_userFull) r5;
        getMessagesController().putUsers(r09.users, false);
        getMessagesController().putChats(r09.chats, false);
        TLRPC.UserFull r010 = r09.full_user;
        TL_bots.BotInfo r96 = r010.bot_info;
        if (r96 == null) goto L27;
        getMessagesStorage().updateUserInfo(r010, true);
        if (r1 != null) goto L107;
        TLRPC.InputFileLocation r42 = r20;
        byte[] r011 = getFileReference(r96.description_document, null, r42, r32, r6);
        FileRefController r29 = this;
        r32 = r32;
        r1 = r011;
    L108:
        if (r1 != null) goto L27;
        r1 = r29.getFileReference(r96.description_photo, r42, r32, r6);
        goto L27
    L107:
        r29 = this;
        r42 = r20;
        goto L108
    L110:
        FileRefController r210 = this;
        TLRPC.InputFileLocation r43 = r20;
        if ((r5 instanceof TLRPC.TL_attachMenuBotsBot) == false) goto L119;
        ArrayList<TLRPC.TL_attachMenuBotIcon> r012 = ((TLRPC.TL_attachMenuBotsBot) r5).bot.icons;
        int r73 = r012.size();
        int r56 = 0;
    L113:
        if (r56 >= r73) goto L27;
        int r97 = r56 + 1;
        TLRPC.Document r15 = r012.get(r56).icon;
        boolean[] r57 = r32;
        r27 = r210.getFileReference(r15, null, r43, r57, r6);
        r32 = r57;
        if (r27 != null) goto L72;
        r43 = r20;
        r1 = r27;
        r56 = r97;
        r210 = this;
        goto L113
    L119:
        if ((r5 instanceof TLRPC.TL_help_appUpdate) == false) goto L143;
        TLRPC.TL_help_appUpdate r98 = (TLRPC.TL_help_appUpdate) r5;
        SharedConfig.pendingAppUpdate = r98;     // Catch: Exception -> L123
        SharedConfig.saveConfig();     // Catch: Exception -> L123
    L274:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.appUpdateAvailable, new Object[0]);     // Catch: Exception -> L127
    L278:
        TLRPC.Document r013 = r98.document;     // Catch: Exception -> L133
        if (r013 == null) goto L135;
        r1 = r013.file_reference;     // Catch: Exception -> L133
        TLRPC.TL_inputDocumentFileLocation r014 = new TLRPC.TL_inputDocumentFileLocation();     // Catch: Exception -> L133
        TLRPC.Document r211 = r98.document;     // Catch: Exception -> L133
        r014.id = r211.id;     // Catch: Exception -> L133
        r014.access_hash = r211.access_hash;     // Catch: Exception -> L133
        r014.file_reference = r211.file_reference;     // Catch: Exception -> L133
        r014.thumb_size = _UrlKt.FRAGMENT_ENCODE_SET;     // Catch: Exception -> L133
        TLRPC.InputFileLocation[] r212 = {r014};     // Catch: Exception -> L133
    L136:
        r6 = r212;
    L138:
        if (r1 != null) goto L140;
        boolean[] r58 = r32;
        r1 = getFileReference(r98.document, null, r20, r58, r6);
    L141:
        if (r1 != null) goto L27;
        r1 = getFileReference(r98.sticker, null, r20, r58, r6);
        goto L27
    L140:
        r58 = r32;
        goto L141
    L135:
        r212 = r6;
    L133:
        e = move-exception;
        FileLog.e(e);
        r1 = null;
    L127:
        e = move-exception;
        FileLog.e(e);
    L123:
        e = move-exception;
        FileLog.e(e);
        goto L274
    L143:
        FileRefController r213 = this;
        TLRPC.InputFileLocation r44 = r20;
        if ((r5 instanceof TLRPC.TL_messages_webPage) == false) goto L147;
        TLRPC.TL_messages_webPage r015 = (TLRPC.TL_messages_webPage) r5;
        r213.getMessagesController().putChats(r015.chats, false);
        r213.getMessagesController().putUsers(r015.users, false);
        r1 = r213.getFileReference(r015.webpage, r44, r32, r6);
        goto L27
    L147:
        if ((r5 instanceof TLRPC.WebPage) == false) goto L150;
        r1 = r213.getFileReference((TLRPC.WebPage) r5, r44, r32, r6);
        goto L27
    L150:
        if ((r5 instanceof TL_account.TL_wallPapers) == false) goto L158;
        TL_account.TL_wallPapers r016 = (TL_account.TL_wallPapers) r5;
        int r74 = r016.wallpapers.size();
        int r99 = 0;
    L152:
        if (r99 >= r74) goto L27;
        boolean[] r59 = r32;
        r27 = r213.getFileReference(r016.wallpapers.get(r99).document, null, r44, r59, r6);
        r32 = r59;
        if (r27 != null) goto L72;
        r99 = r99 + 1;
        r44 = r20;
        r1 = r27;
        r213 = this;
        goto L152
    L158:
        if ((r5 instanceof TLRPC.TL_wallPaper) == false) goto L161;
        r1 = getFileReference(((TLRPC.TL_wallPaper) r5).document, null, r20, r32, r6);
        goto L27
    L161:
        if ((r5 instanceof TLRPC.TL_theme) == false) goto L163;
        r1 = getFileReference(((TLRPC.TL_theme) r5).document, null, r20, r32, r6);
        goto L27
    L163:
        FileRefController r214 = this;
        TLRPC.InputFileLocation r45 = r20;
        if ((r5 instanceof Vector) == false) goto L179;
        Vector r017 = (Vector) r5;
        if (r017.objects.isEmpty() == true) goto L27;
        int r510 = r017.objects.size();
        int r75 = 0;
    L168:
        if (r75 >= r510) goto L27;
        Object r910 = r017.objects.get(r75);
        if ((r910 instanceof TLRPC.User) == false) goto L173;
        r1 = r214.getFileReference((TLRPC.User) r910, r45, r32, r6);
    L175:
        if (r1 != null) goto L27;
        r75 = r75 + 1;
        goto L168
    L173:
        if ((r910 instanceof TLRPC.Chat) == false) goto L175;
        r1 = r214.getFileReference((TLRPC.Chat) r910, r45, r32, r6);
        goto L175
    L179:
        if ((r5 instanceof TLRPC.TL_messages_chats) == false) goto L189;
        TLRPC.TL_messages_chats r018 = (TLRPC.TL_messages_chats) r5;
        if (r018.chats.isEmpty() == true) goto L27;
        int r511 = r018.chats.size();
        int r76 = 0;
    L183:
        if (r76 >= r511) goto L27;
        r1 = r214.getFileReference(r018.chats.get(r76), r45, r32, r6);
        if (r1 != null) goto L27;
        r76 = r76 + 1;
        goto L183
    L189:
        if ((r5 instanceof TLRPC.TL_messages_savedGifs) == false) goto L197;
        TLRPC.TL_messages_savedGifs r019 = (TLRPC.TL_messages_savedGifs) r5;
        int r77 = r019.gifs.size();
        int r911 = 0;
    L191:
        if (r911 >= r77) goto L27;
        boolean[] r512 = r32;
        r27 = r214.getFileReference(r019.gifs.get(r911), null, r45, r512, r6);
        r32 = r512;
        if (r27 != null) goto L72;
        r911 = r911 + 1;
        r45 = r20;
        r1 = r27;
        r214 = this;
        goto L191
    L197:
        if ((r5 instanceof TLRPC.TL_messages_stickerSet) == false) goto L207;
        TLRPC.TL_messages_stickerSet r020 = (TLRPC.TL_messages_stickerSet) r5;
        if (r1 != null) goto L27;
        int r78 = r020.documents.size();
        int r912 = 0;
    L201:
        if (r912 >= r78) goto L27;
        boolean[] r513 = r32;
        r27 = getFileReference(r020.documents.get(r912), null, r20, r513, r6);
        r32 = r513;
        if (r27 != null) goto L72;
        r912 = r912 + 1;
        r1 = r27;
        goto L201
    L207:
        if ((r5 instanceof TLRPC.TL_messages_recentStickers) == false) goto L215;
        TLRPC.TL_messages_recentStickers r021 = (TLRPC.TL_messages_recentStickers) r5;
        int r79 = r021.stickers.size();
        int r913 = 0;
    L209:
        if (r913 >= r79) goto L27;
        boolean[] r514 = r32;
        r27 = getFileReference(r021.stickers.get(r913), null, r20, r514, r6);
        r32 = r514;
        if (r27 != null) goto L72;
        r913 = r913 + 1;
        r1 = r27;
        goto L209
    L215:
        if ((r5 instanceof TLRPC.TL_messages_favedStickers) == false) goto L222;
        TLRPC.TL_messages_favedStickers r022 = (TLRPC.TL_messages_favedStickers) r5;
        int r710 = r022.stickers.size();
        int r914 = 0;
    L217:
        if (r914 >= r710) goto L27;
        boolean[] r515 = r32;
        r27 = getFileReference(r022.stickers.get(r914), null, r20, r515, r6);
        if (r27 != null) goto L72;
        r914 = r914 + 1;
        r1 = r27;
        r32 = r515;
        goto L217
    L222:
        FileRefController r33 = this;
        if ((r5 instanceof TLRPC.photos_Photos) == false) goto L231;
        TLRPC.photos_Photos r023 = (TLRPC.photos_Photos) r5;
        int r215 = r023.photos.size();
        int r516 = 0;
    L225:
        if (r516 >= r215) goto L27;
        r1 = r33.getFileReference(r023.photos.get(r516), r20, r32, r6);
        if (r1 != null) goto L27;
        r516 = r516 + 1;
        goto L225
    L231:
        if ((r5 instanceof TL_stories.TL_stories_stories) == false) goto L27;
        TL_stories.TL_stories_stories r024 = (TL_stories.TL_stories_stories) r5;
        if (r024.stories.isEmpty() == true) goto L248;
        TL_stories.StoryItem r025 = r024.stories.get(0);
        TLRPC.MessageMedia r216 = r025.media;
        if (r216 == null) goto L248;
        if (r1 != null) goto L240;
        TLRPC.Photo r217 = r216.photo;
        if (r217 == null) goto L240;
        r1 = r33.getFileReference(r217, r20, r32, r6);
    L240:
        if (r1 != null) goto L244;
        TLRPC.Photo r218 = r025.media.video_cover;
        if (r218 == null) goto L244;
        r1 = r33.getFileReference(r218, r20, r32, r6);
    L244:
        if (r1 != null) goto L249;
        TLRPC.MessageMedia r219 = r025.media;
        TLRPC.Document r517 = r219.document;
        if (r517 == null) goto L249;
        byte[] r220 = r33.getFileReference(r517, r219.alt_documents, r20, r32, r6);
        r33 = r33;
        r1 = r220;
    L249:
        Object r221 = r24[1];
        if ((r221 instanceof FileLoadOperation) == false) goto L27;
        Object r222 = ((FileLoadOperation) r221).parentObject;
        if ((r222 instanceof TL_stories.StoryItem) == false) goto L27;
        TL_stories.StoryItem r223 = (TL_stories.StoryItem) r222;
        if (r025 != null) goto L256;
        TL_stories.TL_updateStory r46 = new TL_stories.TL_updateStory();
        r46.peer = r33.getMessagesController().getPeer(r223.dialogId);
        TL_stories.TL_storyItemDeleted r518 = new TL_stories.TL_storyItemDeleted();
        r46.story = r518;
        r518.id = r223.id;
        ArrayList<TLRPC.Update> r133 = new ArrayList();
        r133.add(r46);
        r33.getMessagesController().processUpdateArray(r133, null, null, false, 0);
    L261:
        if (r025 == null) goto L27;
        if (r1 != null) goto L27;
        TL_stories.TL_updateStory r47 = new TL_stories.TL_updateStory();
        r47.peer = MessagesController.getInstance(r33.currentAccount).getPeer(r223.dialogId);
        r47.story = r025;
        ArrayList<TLRPC.Update> r026 = new ArrayList();
        r026.add(r47);
        MessagesController.getInstance(r33.currentAccount).processUpdateArray(r026, null, null, false, 0);
        goto L27
    L256:
        TLRPC.User r48 = r33.getMessagesController().getUser(Long.valueOf(r223.dialogId));
        if (r48 == null) goto L261;
        if (r48.contact == false) goto L261;
        MessagesController.getInstance(r33.currentAccount).getStoriesController().getStoriesStorage().updateStoryItem(r223.dialogId, r025);
    L248:
        r025 = null;
    L18:
        r1 = null;
        goto L20
    L17:
        r5 = r23;
        goto L18
    L5:
        if ((r20 instanceof TLRPC.TL_inputPeerPhotoFileLocation) == true) goto L8;
        r9 = null;
        r10 = null;
        goto L10
    }

    public static FileRefController getInstance(int r3) {
        FileRefController r0 = Instance[r3];
        if (r0 == null) goto L5;
        return r0;
    L5:
        monitor-enter(FileRefController.class);
        FileRefController r02 = Instance[r3];     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        FileRefController[] r03 = Instance;     // Catch: Throwable -> L9
        FileRefController r2 = new FileRefController(r3);     // Catch: Throwable -> L9
        r03[r3] = r2;     // Catch: Throwable -> L9
        r02 = r2;
    L11:
        monitor-exit(FileRefController.class);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    }

    public static String getKeyForParentObject(Object r7) {
        if ((r7 instanceof StoriesController.BotPreview) == false) goto L18;
        StoriesController.BotPreview r72 = (StoriesController.BotPreview) r7;
        if (r72.list != null) goto L8;
        FileLog.d("failed request reference can't find list in botpreview");
        return null;
    L8:
        TLRPC.MessageMedia r0 = r72.media;
        if (r0.document == null) goto L13;
        return "botstory_doc_" + r72.media.document.id;
    L13:
        if (r0.photo == null) goto L17;
        return "botstory_photo_" + r72.media.photo.id;
    L17:
        return "botstory_" + r72.id;
    L18:
        long r2 = 0;
        if ((r7 instanceof TL_stories.StoryItem) == false) goto L27;
        TL_stories.StoryItem r73 = (TL_stories.StoryItem) r7;
        if (r73.dialogId != 0) goto L25;
        FileLog.d("failed request reference can't find dialogId");
        return null;
    L25:
        return "story_" + r73.dialogId + "_" + r73.id;
    L27:
        if ((r7 instanceof TLRPC.TL_help_premiumPromo) == false) goto L31;
        return "premium_promo";
    L31:
        if ((r7 instanceof TLRPC.TL_availableReaction) == false) goto L35;
        return "available_reaction_" + ((TLRPC.TL_availableReaction) r7).reaction;
    L35:
        if ((r7 instanceof TL_bots.BotInfo) == false) goto L39;
        return "bot_info_" + ((TL_bots.BotInfo) r7).user_id;
    L39:
        if ((r7 instanceof TLRPC.TL_attachMenuBot) == false) goto L43;
        return "attach_menu_bot_" + ((TLRPC.TL_attachMenuBot) r7).bot_id;
    L43:
        if ((r7 instanceof MessageObject) == false) goto L56;
        MessageObject r74 = (MessageObject) r7;
        long r02 = r74.getChannelId();
        if (r74.type != 29) goto L54;
        TLRPC.Message r22 = r74.messageOwner;
        if (r22 == null) goto L54;
        TLRPC.MessageFwdHeader r23 = r22.fwd_from;
        if (r23 == null) goto L54;
        TLRPC.Peer r24 = r23.from_id;
        if (r24 == null) goto L54;
        r02 = DialogObject.getPeerDialogId(r24);
    L54:
        return "message" + r74.getRealId() + "_" + r02 + "_" + r74.scheduled + "_" + r74.getQuickReplyId();
    L56:
        if ((r7 instanceof TLRPC.Message) == false) goto L63;
        TLRPC.Message r75 = (TLRPC.Message) r7;
        TLRPC.Peer r03 = r75.peer_id;
        if (r03 == null) goto L61;
        r2 = r03.channel_id;
    L61:
        return "message" + r75.id + "_" + r2 + "_" + r75.from_scheduled;
    L63:
        if ((r7 instanceof TLRPC.WebPage) == false) goto L67;
        return "webpage" + ((TLRPC.WebPage) r7).id;
    L67:
        if ((r7 instanceof TLRPC.User) == false) goto L71;
        return "user" + ((TLRPC.User) r7).id;
    L71:
        if ((r7 instanceof TLRPC.Chat) == false) goto L75;
        return "chat" + ((TLRPC.Chat) r7).id;
    L75:
        if ((r7 instanceof String) == false) goto L79;
        return "str".concat((String) r7);
    L79:
        if ((r7 instanceof TLRPC.TL_messages_stickerSet) == false) goto L83;
        return "set" + ((TLRPC.TL_messages_stickerSet) r7).set.id;
    L83:
        if ((r7 instanceof TLRPC.StickerSetCovered) == false) goto L87;
        return "set" + ((TLRPC.StickerSetCovered) r7).set.id;
    L87:
        if ((r7 instanceof TLRPC.InputStickerSet) == false) goto L91;
        return "set" + ((TLRPC.InputStickerSet) r7).id;
    L91:
        if ((r7 instanceof TLRPC.TL_wallPaper) == false) goto L95;
        return "wallpaper" + ((TLRPC.TL_wallPaper) r7).id;
    L95:
        if ((r7 instanceof TLRPC.TL_theme) == true) goto L97;
        if (r7 != null) goto L100;
        return null;
    L100:
        return _UrlKt.FRAGMENT_ENCODE_SET + r7;
    L97:
        return "theme" + ((TLRPC.TL_theme) r7).id;
    }

    private String getObjectString(Object r4) {
        if ((r4 instanceof String) == false) goto L7;
        return (String) r4;
    L7:
        if ((r4 instanceof TL_stories.StoryItem) == false) goto L11;
        TL_stories.StoryItem r42 = (TL_stories.StoryItem) r4;
        return "story(dialogId=" + r42.dialogId + " id=" + r42.id + ")";
    L11:
        if ((r4 instanceof MessageObject) == false) goto L14;
        MessageObject r43 = (MessageObject) r4;
        return "message(dialogId=" + r43.getDialogId() + "messageId" + r43.getId() + ")";
    L14:
        if (r4 != null) goto L18;
        return null;
    L18:
        return r4.getClass().getSimpleName();
    }

    private boolean getPeerReferenceReplacement(TLRPC.User r4, TLRPC.Chat r5, boolean r6, TLRPC.InputFileLocation r7, TLRPC.InputFileLocation[] r8, boolean[] r9) {
        if (r9 != null) goto L5;
    L30:
        return false;
    L5:
        if (r9[0] == false) goto L30;
        TLRPC.TL_inputPeerPhotoFileLocation r92 = new TLRPC.TL_inputPeerPhotoFileLocation();
        long r1 = r7.volume_id;
        r92.id = r1;
        r92.volume_id = r1;
        r92.local_id = r7.local_id;
        r92.big = r6;
        if (r4 == null) goto L17;
        if (r4.access_hash == 0) goto L11;
    L15:
        TLRPC.InputPeer r52 = new TLRPC.TL_inputPeerUser();
        r52.user_id = r4.id;
        r52.access_hash = r4.access_hash;
        r92.photo_id = r4.photo.photo_id;
    L28:
        r92.peer = r52;
        r8[0] = r92;
        return true;
    L11:
        if (r4.fromMessageId == 0) goto L15;
        if (r4.fromMessageDialogId == 0) goto L15;
        r52 = new TLRPC.TL_inputPeerUserFromMessage();
        r52.user_id = r4.id;
        r52.peer = getMessagesController().getInputPeer(r4.fromMessageDialogId);
        r52.msg_id = r4.fromMessageId;
        r92.photo_id = r4.photo.photo_id;
        goto L28
    L17:
        if (ChatObject.isChannel(r5) == true) goto L19;
        TLRPC.InputPeer r3 = new TLRPC.TL_inputPeerChat();
        r3.chat_id = r5.id;
    L27:
        r92.photo_id = r5.photo.photo_id;
        r52 = r3;
        goto L28
    L19:
        if (r5.access_hash == 0) goto L21;
    L25:
        r3 = new TLRPC.TL_inputPeerChannel();
        r3.channel_id = r5.id;
        r3.access_hash = r5.access_hash;
        goto L27
    L21:
        if (r5.fromMessageDialogId == 0) goto L25;
        if (r5.fromMessageId == 0) goto L25;
        TLRPC.TL_inputPeerChannelFromMessage r42 = new TLRPC.TL_inputPeerChannelFromMessage();
        r42.channel_id = r5.id;
        r42.peer = getMessagesController().getInputPeer(r5.fromMessageDialogId);
        r42.msg_id = r5.fromMessageId;
        r3 = r42;
        goto L27
    }

    public static boolean isFileRefError(String r1) {
        if ("FILEREF_EXPIRED".equals(r1) == false) goto L5;
        return true;
    L5:
        if ("FILE_REFERENCE_EXPIRED".equals(r1) == false) goto L7;
        return true;
    L7:
        if ("FILE_REFERENCE_EMPTY".equals(r1) == true) goto L18;
        if (r1 != null) goto L10;
        return false;
    L10:
        if (r1.startsWith("FILE_REFERENCE_") == true) goto L19;
        return false;
    L19:
        return true;
    L18:
        return true;
    }

    public static boolean isFileRefErrorCover(String r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (isFileRefError(r1) == true) goto L6;
        return false;
    L6:
        if (r1.endsWith("COVER_EXPIRED") == false) goto L12;
        return true;
    L12:
        return false;
    }

    private boolean isSameReference(byte[] r1, byte[] r2) {
        return Arrays.equals(r1, r2);
    }

    private /* synthetic */ void lambda$onRequestComplete$47(TLRPC.User r2) {
        getMessagesController().putUser(r2, false);
    }

    private /* synthetic */ void lambda$onRequestComplete$48(TLRPC.Chat r2) {
        getMessagesController().putChat(r2, false);
    }

    private /* synthetic */ void lambda$onRequestComplete$49(TLRPC.Chat r2) {
        getMessagesController().putChat(r2, false);
    }

    private /* synthetic */ void lambda$onRequestComplete$50(TLRPC.TL_messages_stickerSet r1) {
        getMediaDataController().replaceStickerSet(r1);
    }

    private /* synthetic */ void lambda$onUpdateObjectReference$30(TLRPC.TL_messages_sendMultiMedia r8, Object[] r9) {
        getSendMessagesHelper().performSendMessageRequestMulti(r8, (ArrayList) r9[1], (ArrayList) r9[2], null, (SendMessagesHelper.DelayedMessage) r9[4], ((Boolean) r9[5]).booleanValue());
    }

    private /* synthetic */ void lambda$onUpdateObjectReference$31(TLRPC.TL_messages_sendMedia r8, Object[] r9) {
        getSendMessagesHelper().performSendMessageRequestMulti(r8, (ArrayList) r9[1], (ArrayList) r9[2], null, (SendMessagesHelper.DelayedMessage) r9[4], ((Boolean) r9[5]).booleanValue());
    }

    private /* synthetic */ void lambda$onUpdateObjectReference$32(TL_ephemeral.TL_sendMessage r8, Object[] r9) {
        getSendMessagesHelper().performSendMessageRequestMulti(r8, (ArrayList) r9[1], (ArrayList) r9[2], null, (SendMessagesHelper.DelayedMessage) r9[4], ((Boolean) r9[5]).booleanValue());
    }

    private /* synthetic */ void lambda$onUpdateObjectReference$33(Requester r11) {
        getSendMessagesHelper().performSendMessageRequest((TLObject) Requester.m4073$$Nest$fgetargs(r11)[0], (MessageObject) Requester.m4073$$Nest$fgetargs(r11)[1], (String) Requester.m4073$$Nest$fgetargs(r11)[2], (SendMessagesHelper.DelayedMessage) Requester.m4073$$Nest$fgetargs(r11)[3], ((Boolean) Requester.m4073$$Nest$fgetargs(r11)[4]).booleanValue(), (SendMessagesHelper.DelayedMessage) Requester.m4073$$Nest$fgetargs(r11)[5], null, null, ((Boolean) Requester.m4073$$Nest$fgetargs(r11)[6]).booleanValue());
    }

    private /* synthetic */ void lambda$onUpdateObjectReference$34(Requester r11) {
        getSendMessagesHelper().performSendMessageRequest((TLObject) Requester.m4073$$Nest$fgetargs(r11)[0], (MessageObject) Requester.m4073$$Nest$fgetargs(r11)[1], (String) Requester.m4073$$Nest$fgetargs(r11)[2], (SendMessagesHelper.DelayedMessage) Requester.m4073$$Nest$fgetargs(r11)[3], ((Boolean) Requester.m4073$$Nest$fgetargs(r11)[4]).booleanValue(), (SendMessagesHelper.DelayedMessage) Requester.m4073$$Nest$fgetargs(r11)[5], null, null, ((Boolean) Requester.m4073$$Nest$fgetargs(r11)[6]).booleanValue());
    }

    private /* synthetic */ void lambda$onUpdateObjectReference$35(Requester r11) {
        getSendMessagesHelper().performSendMessageRequest((TLObject) Requester.m4073$$Nest$fgetargs(r11)[0], (MessageObject) Requester.m4073$$Nest$fgetargs(r11)[1], (String) Requester.m4073$$Nest$fgetargs(r11)[2], (SendMessagesHelper.DelayedMessage) Requester.m4073$$Nest$fgetargs(r11)[3], ((Boolean) Requester.m4073$$Nest$fgetargs(r11)[4]).booleanValue(), (SendMessagesHelper.DelayedMessage) Requester.m4073$$Nest$fgetargs(r11)[5], null, null, ((Boolean) Requester.m4073$$Nest$fgetargs(r11)[6]).booleanValue());
    }

    private /* synthetic */ void lambda$onUpdateObjectReference$36(Requester r11) {
        getSendMessagesHelper().performSendMessageRequest((TLObject) Requester.m4073$$Nest$fgetargs(r11)[0], (MessageObject) Requester.m4073$$Nest$fgetargs(r11)[1], (String) Requester.m4073$$Nest$fgetargs(r11)[2], (SendMessagesHelper.DelayedMessage) Requester.m4073$$Nest$fgetargs(r11)[3], ((Boolean) Requester.m4073$$Nest$fgetargs(r11)[4]).booleanValue(), (SendMessagesHelper.DelayedMessage) Requester.m4073$$Nest$fgetargs(r11)[5], null, null, ((Boolean) Requester.m4073$$Nest$fgetargs(r11)[6]).booleanValue());
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$0(String r8, String r9, StoriesController.BotPreview r10) {
        onRequestComplete(r8, r9, r10, null, true, false);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$1(String r3, String r4, StoriesController.BotPreview r5) {
        Utilities.stageQueue.postRunnable(new FileRefController$$ExternalSyntheticLambda50(this, r3, r4, r5));
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$10(String r8, String r9, TLObject r10, TLRPC.TL_error r11) {
        onRequestComplete(r8, r9, r10, r11, true, false);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$11(String r8, String r9, TLObject r10, TLRPC.TL_error r11) {
        onRequestComplete(r8, r9, r10, r11, true, false);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$12(String r8, String r9, TLObject r10, TLRPC.TL_error r11) {
        onRequestComplete(r8, r9, r10, r11, true, false);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$13(String r8, String r9, TLObject r10, TLRPC.TL_error r11) {
        onRequestComplete(r8, r9, r10, r11, true, false);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$14(String r8, String r9, TLObject r10, TLRPC.TL_error r11) {
        onRequestComplete(r8, r9, r10, r11, true, false);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$15(String r8, String r9, TLObject r10, TLRPC.TL_error r11) {
        onRequestComplete(r8, r9, r10, r11, true, false);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$16(String r8, String r9, TLObject r10, TLRPC.TL_error r11) {
        onRequestComplete(r8, r9, r10, r11, true, false);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$17(String r8, String r9, TLObject r10, TLRPC.TL_error r11) {
        onRequestComplete(r8, r9, r10, r11, true, false);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$18(TLObject r2, TLRPC.TL_error r3) {
        broadcastWaitersData(this.wallpaperWaiters, r2, r3);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$19(TLObject r2, TLRPC.TL_error r3) {
        broadcastWaitersData(this.savedGifsWaiters, r2, r3);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$2(String r8, String r9, TLObject r10, TLRPC.TL_error r11) {
        onRequestComplete(r8, r9, r10, r11, true, false);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$20(TLObject r2, TLRPC.TL_error r3) {
        broadcastWaitersData(this.recentStickersWaiter, r2, r3);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$21(TLObject r2, TLRPC.TL_error r3) {
        broadcastWaitersData(this.favStickersWaiter, r2, r3);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$22(String r8, String r9, TLRPC.TL_help_appUpdate r10, TLRPC.TL_error r11) {
        onRequestComplete(r8, r9, r10, r11, true, false);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$23(String r8, String r9, TLObject r10, TLRPC.TL_error r11) {
        onRequestComplete(r8, r9, r10, r11, true, false);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$24(String r8, String r9, TLObject r10, TLRPC.TL_error r11) {
        onRequestComplete(r8, r9, r10, r11, true, false);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$25(String r8, String r9, TLObject r10, TLRPC.TL_error r11) {
        onRequestComplete(r8, r9, r10, r11, false, false);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$26(String r8, String r9, TLObject r10, TLRPC.TL_error r11) {
        onRequestComplete(r8, r9, r10, r11, false, false);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$27(String r8, String r9, TLObject r10, TLRPC.TL_error r11) {
        onRequestComplete(r8, r9, r10, r11, true, false);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$28(String r8, String r9, TLObject r10, TLRPC.TL_error r11) {
        onRequestComplete(r8, r9, r10, r11, true, false);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$29(String r8, String r9, TLObject r10, TLRPC.TL_error r11) {
        onRequestComplete(r8, r9, r10, r11, true, false);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$3(String r12, String r13, TLObject r14, TLRPC.TL_error r15) {
        int r0 = (int) (System.currentTimeMillis() / 1000);
        if ((r14 instanceof TLRPC.TL_help_premiumPromo) == false) goto L5;
        MediaDataController r2 = getMediaDataController();
        r2.processLoadedPremiumPromo((TLRPC.TL_help_premiumPromo) r14, r0, false);
    L5:
        onRequestComplete(r12, r13, r14, r15, true, false);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$4(String r8, String r9, TLObject r10, TLRPC.TL_error r11) {
        onRequestComplete(r8, r9, r10, r11, true, false);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$5(String r8, String r9, TLObject r10, TLRPC.TL_error r11) {
        onRequestComplete(r8, r9, r10, r11, true, false);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$6(String r8, String r9, TLObject r10, TLRPC.TL_error r11) {
        onRequestComplete(r8, r9, r10, r11, true, false);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$7(String r8, String r9, TLObject r10, TLRPC.TL_error r11) {
        onRequestComplete(r8, r9, r10, r11, true, false);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$8(String r8, String r9, TLObject r10, TLRPC.TL_error r11) {
        onRequestComplete(r8, r9, r10, r11, true, false);
    }

    private /* synthetic */ void lambda$requestReferenceFromServer$9(String r8, String r9, TLObject r10, TLRPC.TL_error r11) {
        onRequestComplete(r8, r9, r10, r11, true, false);
    }

    private /* synthetic */ void lambda$sendErrorToObject$41(TLRPC.TL_messages_sendMultiMedia r8, Object[] r9) {
        getSendMessagesHelper().performSendMessageRequestMulti(r8, (ArrayList) r9[1], (ArrayList) r9[2], null, (SendMessagesHelper.DelayedMessage) r9[4], ((Boolean) r9[5]).booleanValue());
    }

    private /* synthetic */ void lambda$sendErrorToObject$42(TLRPC.TL_messages_sendMedia r8, Object[] r9) {
        getSendMessagesHelper().performSendMessageRequestMulti(r8, (ArrayList) r9[1], (ArrayList) r9[2], null, (SendMessagesHelper.DelayedMessage) r9[4], ((Boolean) r9[5]).booleanValue());
    }

    private /* synthetic */ void lambda$sendErrorToObject$43(TL_ephemeral.TL_sendMessage r8, Object[] r9) {
        getSendMessagesHelper().performSendMessageRequestMulti(r8, (ArrayList) r9[1], (ArrayList) r9[2], null, (SendMessagesHelper.DelayedMessage) r9[4], ((Boolean) r9[5]).booleanValue());
    }

    private /* synthetic */ void lambda$sendErrorToObject$44(Object[] r11) {
        getSendMessagesHelper().performSendMessageRequest((TLObject) r11[0], (MessageObject) r11[1], (String) r11[2], (SendMessagesHelper.DelayedMessage) r11[3], ((Boolean) r11[4]).booleanValue(), (SendMessagesHelper.DelayedMessage) r11[5], null, null, ((Boolean) r11[6]).booleanValue());
    }

    private /* synthetic */ void lambda$sendErrorToObject$45(Object[] r11) {
        getSendMessagesHelper().performSendMessageRequest((TLObject) r11[0], (MessageObject) r11[1], (String) r11[2], (SendMessagesHelper.DelayedMessage) r11[3], ((Boolean) r11[4]).booleanValue(), (SendMessagesHelper.DelayedMessage) r11[5], null, null, ((Boolean) r11[6]).booleanValue());
    }

    /* JADX WARN: Type inference failed for: r11v0 */
    /* JADX WARN: Type inference failed for: r11v47 */
    private boolean onRequestComplete(String r30, String r31, TLObject r32, TLRPC.TL_error r33, boolean r34, boolean r35) {
        FileRefController r1 = this;
        TLObject r4 = r32;
        boolean r9 = r4 instanceof TLRPC.TL_help_premiumPromo;
        if (r9 == false) goto L7;
        String r2 = "premium_promo";
    L5:
        String r10 = r2;
    L19:
        int r11 = 1;
        if (r31 == null) goto L41;
        ArrayList<Requester> r13 = r1.parentRequester.get(r31);
        if (r13 == null) goto L41;
        int r14 = r13.size();
        int r15 = 0;
        boolean r16 = false;
    L24:
        if (r15 >= r14) goto L37;
        Requester r22 = r13.get(r15);
        if (Requester.m4074$$Nest$fgetcompleted(r22) == false) goto L28;
        TLObject r7 = r4;
    L36:
        r15 = r15 + 1;
        r4 = r7;
        goto L24
    L28:
        String r23 = Requester.m4076$$Nest$fgetlocationKey(r22);
        if (r34 == false) goto L32;
        if (r16 == true) goto L32;
        boolean r6 = true;
    L33:
        r7 = r4;
        if (r1.onRequestComplete(r23, null, r4, r33, r6, r35) == false) goto L36;
        r16 = true;
    L32:
        r6 = false;
        goto L33
    L37:
        TLRPC.TL_error r3 = r33;
        TLObject r72 = r4;
        if (r16 == false) goto L40;
        r1.putReponseToCache(r10, r72);
    L40:
        r1.parentRequester.remove(r31);
    L42:
        ArrayList<Requester> r102 = r1.locationRequester.get(r30);
        if (r102 != null) goto L45;
        return r16;
    L45:
        int r132 = r102.size();
        boolean[] r0 = null;
        TLRPC.InputFileLocation[] r24 = null;
        byte[] r42 = null;
        int r152 = 0;
    L46:
        if (r152 >= r132) goto L387;
        Requester r5 = r102.get(r152);
        if (Requester.m4074$$Nest$fgetcompleted(r5) == false) goto L51;
        int r112 = r11;
    L386:
        r152 = r152 + 1;
        r3 = r33;
        r11 = r112;
        goto L46
    L51:
        if (r3 == null) goto L60;
        if (BuildVars.LOGS_ENABLED == false) goto L60;
        if (Requester.m4073$$Nest$fgetargs(r5).length <= r11) goto L60;
        if ((Requester.m4073$$Nest$fgetargs(r5)[r11] instanceof FileLoadOperation) == false) goto L60;
        FileLog.e("debug_loading: " + ((FileLoadOperation) Requester.m4073$$Nest$fgetargs(r5)[r11]).getCacheFileFinal().getName() + " can't update file reference: " + r3.code + " " + r3.text);
    L60:
        if ((Requester.m4075$$Nest$fgetlocation(r5) instanceof TLRPC.TL_inputFileLocation) == false) goto L62;
    L65:
        r24 = new TLRPC.InputFileLocation[r11];
        r0 = new boolean[r11];
    L64:
        TLRPC.InputFileLocation[] r62 = r24;
        boolean[] r25 = r0;
        Requester.m4078$$Nest$fputcompleted(r5, r11);
        if ((r72 instanceof StoriesController.BotPreview) == false) goto L75;
        TLRPC.MessageMedia r02 = ((StoriesController.BotPreview) r72).media;
        Requester r12 = r5;
        boolean[] r52 = r25;
        TLRPC.Document r26 = r02.document;
        if (r26 == null) goto L71;
        r42 = r1.getFileReference(r26, r02.alt_documents, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
    L74:
        boolean[] r27 = r52;
        r24 = r62;
        Requester r312 = r12;
    L374:
        if (r42 != null) goto L376;
        r112 = 1;
        r1.sendErrorToObject(Requester.m4073$$Nest$fgetargs(r312), 1);
    L385:
        r0 = r27;
        goto L386
    L376:
        if (r24 == null) goto L379;
        TLRPC.InputFileLocation r03 = r24[0];
    L381:
        if (r1.onUpdateObjectReference(r312, r42, r03, r35) == false) goto L383;
        r0 = r27;
        r112 = 1;
        r16 = true;
        goto L386
    L383:
        r112 = 1;
        goto L385
    L379:
        r03 = null;
        goto L381
    L71:
        TLRPC.Photo r04 = r02.photo;
        if (r04 == null) goto L74;
        r42 = r1.getFileReference(r04, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        goto L74
    L75:
        r12 = r5;
        r52 = r25;
        if ((r72 instanceof TLRPC.messages_Messages) == false) goto L119;
        TLRPC.messages_Messages r05 = (TLRPC.messages_Messages) r72;
        if (r05.messages.isEmpty() == true) goto L116;
        int r28 = r05.messages.size();
        int r36 = 0;
    L80:
        if (r36 >= r28) goto L110;
        TLRPC.Message r142 = r05.messages.get(r36);
        TLRPC.MessageMedia r113 = r142.media;
        int r19 = r28;
        if ((r113 instanceof TLRPC.TL_messageMediaPaidMedia) == false) goto L92;
        TLRPC.TL_messageMediaPaidMedia r114 = (TLRPC.TL_messageMediaPaidMedia) r113;
        int r20 = r36;
        int r29 = 0;
    L85:
        if (r29 >= r114.extended_media.size()) goto L106;
        TLRPC.MessageExtendedMedia r37 = r114.extended_media.get(r29);
        int r21 = r29;
        if ((r37 instanceof TLRPC.TL_messageExtendedMedia) == false) goto L89;
        r42 = r1.getFileReferenceForMediaImpl(((TLRPC.TL_messageExtendedMedia) r37).media, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
    L89:
        if (r42 != null) goto L106;
        r29 = r21 + 1;
    L106:
        if (r42 != null) goto L107;
        r36 = r20 + 1;
        r1 = this;
        r28 = r19;
        goto L80
    L107:
        if (r34 == false) goto L110;
        int r17 = 0;
        r1.getMessagesStorage().replaceMessageIfExists(r142, r05.users, r05.chats, false);
    L111:
        if (r42 != null) goto L118;
        getMessagesStorage().replaceMessageIfExists(r05.messages.get(r17), r05.users, r05.chats, true);
        if (BuildVars.DEBUG_VERSION == false) goto L118;
        FileLog.d("file ref not found in messages, replacing message");
    L118:
        r1 = this;
        goto L74
    L92:
        r20 = r36;
        TL_iv.RichMessage r210 = r142.rich_message;
        if (r210 == null) goto L97;
        byte[] r211 = r1.getFileReferenceForRichMessage(r210, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
    L95:
        r42 = r211;
        goto L106
    L97:
        if ((r113 instanceof TLRPC.TL_messageMediaPoll) == false) goto L99;
        r211 = r1.getFileReferenceForPoll((TLRPC.TL_messageMediaPoll) r113, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        goto L95
    L99:
        if (r113 == null) goto L101;
        r211 = r1.getFileReferenceForMediaImpl(r113, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        goto L95
    L101:
        TLRPC.MessageAction r212 = r142.action;
        if ((r212 instanceof TLRPC.TL_messageActionChatEditPhoto) == false) goto L104;
    L105:
        r211 = r1.getFileReference(r212.photo, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        goto L95
    L104:
        if ((r212 instanceof TLRPC.TL_messageActionSuggestProfilePhoto) == false) goto L106;
    L110:
        r17 = 0;
        goto L111
    L116:
        if (BuildVars.DEBUG_VERSION == false) goto L118;
        FileLog.d("empty messages, file ref not found");
        goto L118
    L119:
        if (r9 == false) goto L127;
        ArrayList<TLRPC.Document> r06 = ((TLRPC.TL_help_premiumPromo) r72).videos;
        int r115 = r06.size();
        int r18 = 0;
    L121:
        if (r18 >= r115) goto L118;
        int r143 = r18 + 1;
        r42 = getFileReference(r06.get(r18), null, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        if (r42 != null) goto L118;
        r18 = r143;
        goto L121
    L127:
        if ((r72 instanceof TLRPC.TL_messages_availableReactions) == false) goto L153;
        TLRPC.TL_messages_availableReactions r07 = (TLRPC.TL_messages_availableReactions) r72;
        boolean[] r116 = r52;
        getMediaDataController().processLoadedReactions(r07.reactions, r07.hash, (int) (System.currentTimeMillis() / 1000), false);
        ArrayList<TLRPC.TL_availableReaction> r08 = r07.reactions;
        int r110 = r08.size();
        r42 = r42;
        int r213 = 0;
    L129:
        if (r213 >= r110) goto L152;
        int r144 = r213 + 1;
        TLRPC.TL_availableReaction r214 = r08.get(r213);
        ArrayList<TLRPC.TL_availableReaction> r192 = r08;
        r52 = r116;
        int r117 = r110;
        byte[] r215 = getFileReference(r214.static_icon, null, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        if (r215 != null) goto L132;
        r215 = getFileReference(r214.appear_animation, null, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        if (r215 != null) goto L132;
        r215 = getFileReference(r214.select_animation, null, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        if (r215 != null) goto L132;
        r215 = getFileReference(r214.activate_animation, null, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        if (r215 != null) goto L132;
        r215 = getFileReference(r214.effect_animation, null, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        if (r215 != null) goto L132;
        r215 = getFileReference(r214.around_animation, null, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        if (r215 != null) goto L132;
        r42 = getFileReference(r214.center_icon, null, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        if (r42 != null) goto L118;
        r110 = r117;
        r213 = r144;
        r08 = r192;
        r116 = r52;
    L132:
        r42 = r215;
        goto L118
    L152:
        r52 = r116;
        goto L118
    L153:
        byte[] r145 = r42;
        if ((r72 instanceof TLRPC.TL_users_userFull) == false) goto L163;
        TLRPC.TL_users_userFull r09 = (TLRPC.TL_users_userFull) r72;
        getMessagesController().putUsers(r09.users, false);
        getMessagesController().putChats(r09.chats, false);
        TLRPC.UserFull r010 = r09.full_user;
        TL_bots.BotInfo r118 = r010.bot_info;
        if (r118 == null) goto L161;
        getMessagesStorage().updateUserInfo(r010, true);
        r1 = this;
        r42 = r1.getFileReference(r118.description_document, null, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        if (r42 == null) goto L160;
        r0 = r52;
        r24 = r62;
        r112 = 1;
        goto L386
    L160:
        r42 = r1.getFileReference(r118.description_photo, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        goto L74
    L161:
        r1 = this;
    L162:
        r42 = r145;
        goto L74
    L163:
        FileRefController r111 = this;
        if ((r72 instanceof TLRPC.TL_attachMenuBotsBot) == false) goto L185;
        TLRPC.TL_attachMenuBot r011 = ((TLRPC.TL_attachMenuBotsBot) r72).bot;
        ArrayList<TLRPC.TL_attachMenuBotIcon> r119 = r011.icons;
        int r216 = r119.size();
        byte[] r43 = r145;
        int r38 = 0;
    L166:
        if (r38 >= r216) goto L171;
        int r146 = r38 + 1;
        int r44 = r216;
        r43 = r111.getFileReference(r119.get(r38).icon, null, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        if (r43 != null) goto L171;
        r111 = this;
        r38 = r146;
        r216 = r44;
    L171:
        if (r34 == false) goto L181;
        TLRPC.TL_attachMenuBots r120 = getMediaDataController().getAttachMenuBots();
        ArrayList<TLRPC.TL_attachMenuBot> r217 = new ArrayList(r120.bots);
        int r39 = 0;
    L174:
        if (r39 >= r217.size()) goto L179;
        r145 = r43;
        r27 = r52;
        if (r217.get(r39).bot_id == r011.bot_id) goto L177;
        r39 = r39 + 1;
        r43 = r145;
        r52 = r27;
        goto L174
    L177:
        r217.set(r39, r011);
    L180:
        r120.bots = r217;
        getMediaDataController().processLoadedMenuBots(r120, r120.hash, (int) (System.currentTimeMillis() / 1000), false);
    L182:
        r1 = this;
        r24 = r62;
        r312 = r12;
    L183:
        r42 = r145;
        goto L374
    L179:
        r145 = r43;
        r27 = r52;
        goto L180
    L181:
        r145 = r43;
        r27 = r52;
        goto L182
    L185:
        if ((r72 instanceof TLRPC.TL_help_appUpdate) == false) goto L208;
        TLRPC.TL_help_appUpdate r1110 = (TLRPC.TL_help_appUpdate) r72;
        SharedConfig.pendingAppUpdate = r1110;     // Catch: Exception -> L189
        SharedConfig.saveConfig();     // Catch: Exception -> L189
    L393:
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.appUpdateAvailable, new Object[0]);     // Catch: Exception -> L193
    L391:
        TLRPC.Document r012 = r1110.document;     // Catch: Exception -> L199
        if (r012 == null) goto L201;
        r42 = r012.file_reference;     // Catch: Exception -> L199
        TLRPC.TL_inputDocumentFileLocation r013 = new TLRPC.TL_inputDocumentFileLocation();     // Catch: Exception -> L199
        TLRPC.Document r121 = r1110.document;     // Catch: Exception -> L199
        r013.id = r121.id;     // Catch: Exception -> L199
        r013.access_hash = r121.access_hash;     // Catch: Exception -> L199
        r013.file_reference = r121.file_reference;     // Catch: Exception -> L199
        r013.thumb_size = _UrlKt.FRAGMENT_ENCODE_SET;     // Catch: Exception -> L199
        r62 = new TLRPC.InputFileLocation[]{r013};     // Catch: Exception -> L199
    L203:
        if (r42 != null) goto L205;
        r52 = r52;
        r42 = getFileReference(r1110.document, null, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
    L206:
        if (r42 != null) goto L118;
        r1 = this;
        r42 = r1.getFileReference(r1110.sticker, null, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        goto L74
    L205:
        r52 = r52;
        goto L206
    L201:
        r42 = r145;
    L199:
        e = move-exception;
        FileLog.e(e);
        r42 = null;
    L193:
        e = move-exception;
        FileLog.e(e);
    L189:
        e = move-exception;
        FileLog.e(e);
        goto L393
    L208:
        r1 = this;
        r52 = r52;
        if ((r72 instanceof TLRPC.TL_messages_webPage) == false) goto L212;
        TLRPC.TL_messages_webPage r014 = (TLRPC.TL_messages_webPage) r72;
        r1.getMessagesController().putChats(r014.chats, false);
        r1.getMessagesController().putUsers(r014.users, false);
        r42 = r1.getFileReference(r014.webpage, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        goto L74
    L212:
        if ((r72 instanceof TLRPC.WebPage) == false) goto L215;
        r42 = r1.getFileReference((TLRPC.WebPage) r72, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        goto L74
    L215:
        if ((r72 instanceof TL_account.TL_wallPapers) == false) goto L226;
        TL_account.TL_wallPapers r015 = (TL_account.TL_wallPapers) r72;
        int r1111 = r015.wallpapers.size();
        r42 = r145;
        int r147 = 0;
    L217:
        if (r147 >= r1111) goto L222;
        r42 = r1.getFileReference(r015.wallpapers.get(r147).document, null, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        if (r42 != null) goto L222;
        r147 = r147 + 1;
        r1 = this;
    L222:
        if (r42 == null) goto L118;
        if (r34 == false) goto L118;
        getMessagesStorage().putWallpapers(r015.wallpapers, 1);
        goto L118
    L226:
        if ((r72 instanceof TLRPC.TL_wallPaper) == false) goto L232;
        TLRPC.TL_wallPaper r016 = (TLRPC.TL_wallPaper) r72;
        r42 = getFileReference(r016.document, null, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        if (r42 == null) goto L118;
        if (r34 == false) goto L118;
        ArrayList<TLRPC.WallPaper> r122 = new ArrayList();
        r122.add(r016);
        getMessagesStorage().putWallpapers(r122, 0);
        goto L118
    L232:
        if ((r72 instanceof TLRPC.TL_theme) == false) goto L237;
        TLRPC.TL_theme r017 = (TLRPC.TL_theme) r72;
        r1 = this;
        r42 = r1.getFileReference(r017.document, null, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        if (r42 == null) goto L74;
        if (r34 == false) goto L74;
        AndroidUtilities.runOnUIThread(new FileRefController$$ExternalSyntheticLambda0(r017));
        goto L74
    L237:
        r1 = this;
        if ((r72 instanceof Vector) == false) goto L261;
        Vector r018 = (Vector) r72;
        if (r018.objects.isEmpty() == true) goto L162;
        int r218 = r018.objects.size();
        r42 = r145;
        int r310 = 0;
    L242:
        if (r310 >= r218) goto L74;
        Object r1112 = r018.objects.get(r310);
        if ((r1112 instanceof TLRPC.User) == false) goto L250;
        TLRPC.User r1113 = (TLRPC.User) r1112;
        r42 = r1.getFileReference(r1113, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        if (r34 == false) goto L249;
        if (r42 == null) goto L249;
        ArrayList r148 = new ArrayList();
        r148.add(r1113);
        Vector r193 = r018;
        int r202 = r218;
        int r219 = r310;
        r1.getMessagesStorage().putUsersAndChats(r148, null, true, true);
        AndroidUtilities.runOnUIThread(new FileRefController$$ExternalSyntheticLambda1(r1, r1113));
    L257:
        if (r42 != null) goto L74;
        r310 = r219 + 1;
        r018 = r193;
        r218 = r202;
    L249:
        r193 = r018;
        r202 = r218;
        r219 = r310;
        goto L257
    L250:
        r193 = r018;
        r202 = r218;
        r219 = r310;
        if ((r1112 instanceof TLRPC.Chat) == false) goto L257;
        TLRPC.Chat r1114 = (TLRPC.Chat) r1112;
        byte[] r019 = r1.getFileReference(r1114, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        if (r34 == false) goto L256;
        if (r019 == null) goto L256;
        ArrayList r220 = new ArrayList();
        r220.add(r1114);
        r1.getMessagesStorage().putUsersAndChats(null, r220, true, true);
        AndroidUtilities.runOnUIThread(new FileRefController$$ExternalSyntheticLambda2(r1, r1114));
    L256:
        r42 = r019;
        goto L257
    L261:
        if ((r72 instanceof TLRPC.TL_messages_chats) == false) goto L276;
        TLRPC.TL_messages_chats r020 = (TLRPC.TL_messages_chats) r72;
        if (r020.chats.isEmpty() == true) goto L162;
        int r221 = r020.chats.size();
        r42 = r145;
        int r311 = 0;
    L265:
        if (r311 >= r221) goto L74;
        TLRPC.Chat r45 = r020.chats.get(r311);
        byte[] r1115 = r1.getFileReference(r45, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        if (r1115 != null) goto L268;
        r311 = r311 + 1;
        r42 = r1115;
        goto L265
    L268:
        if (r34 == false) goto L270;
        ArrayList r021 = new ArrayList();
        r021.add(r45);
        Object r149 = null;
        r1.getMessagesStorage().putUsersAndChats(null, r021, true, true);
        AndroidUtilities.runOnUIThread(new FileRefController$$ExternalSyntheticLambda3(r1, r45));
    L271:
        r42 = r1115;
        goto L74
    L270:
        r149 = null;
        goto L271
    L276:
        if ((r72 instanceof TLRPC.TL_messages_savedGifs) == false) goto L286;
        TLRPC.TL_messages_savedGifs r022 = (TLRPC.TL_messages_savedGifs) r72;
        int r222 = r022.gifs.size();
        r42 = r145;
        int r1410 = 0;
    L278:
        if (r1410 >= r222) goto L283;
        int r46 = r222;
        r42 = r1.getFileReference(r022.gifs.get(r1410), null, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        if (r42 != null) goto L283;
        r1410 = r1410 + 1;
        r1 = this;
        r222 = r46;
    L283:
        if (r34 == false) goto L118;
        getMediaDataController().processLoadedRecentDocuments(0, r022.gifs, true, 0, true);
        goto L118
    L286:
        if ((r72 instanceof TLRPC.TL_messages_stickerSet) == false) goto L299;
        TLRPC.TL_messages_stickerSet r023 = (TLRPC.TL_messages_stickerSet) r72;
        if (r145 != null) goto L296;
        int r123 = r023.documents.size();
        r42 = r145;
        int r1411 = 0;
    L290:
        if (r1411 >= r123) goto L295;
        int r194 = r123;
        r1 = this;
        r42 = r1.getFileReference(r023.documents.get(r1411), null, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        if (r42 != null) goto L297;
        r1411 = r1411 + 1;
        r123 = r194;
    L297:
        if (r34 == false) goto L74;
        AndroidUtilities.runOnUIThread(new FileRefController$$ExternalSyntheticLambda4(r1, r023));
        goto L74
    L295:
        r1 = this;
        goto L297
    L296:
        r1 = this;
        r42 = r145;
        goto L297
    L299:
        FileRefController r124 = this;
        if ((r72 instanceof TLRPC.TL_messages_recentStickers) == false) goto L310;
        TLRPC.TL_messages_recentStickers r024 = (TLRPC.TL_messages_recentStickers) r72;
        int r223 = r024.stickers.size();
        r42 = r145;
        int r1412 = 0;
    L302:
        if (r1412 >= r223) goto L307;
        int r47 = r223;
        r42 = r124.getFileReference(r024.stickers.get(r1412), null, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        if (r42 != null) goto L307;
        r1412 = r1412 + 1;
        r124 = this;
        r223 = r47;
    L307:
        if (r34 == false) goto L118;
        getMediaDataController().processLoadedRecentDocuments(0, r024.stickers, false, 0, true);
        goto L118
    L310:
        if ((r72 instanceof TLRPC.TL_messages_favedStickers) == false) goto L320;
        TLRPC.TL_messages_favedStickers r025 = (TLRPC.TL_messages_favedStickers) r72;
        int r125 = r025.stickers.size();
        r42 = r145;
        int r1413 = 0;
    L312:
        if (r1413 >= r125) goto L317;
        int r195 = r125;
        r1 = this;
        r42 = r1.getFileReference(r025.stickers.get(r1413), null, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        if (r42 != null) goto L318;
        r1413 = r1413 + 1;
        r125 = r195;
    L318:
        if (r34 == false) goto L74;
        r1.getMediaDataController().processLoadedRecentDocuments(2, r025.stickers, false, 0, true);
        goto L74
    L317:
        r1 = this;
        goto L318
    L320:
        r1 = this;
        if ((r72 instanceof TLRPC.photos_Photos) == false) goto L329;
        TLRPC.photos_Photos r026 = (TLRPC.photos_Photos) r72;
        int r224 = r026.photos.size();
        r42 = r145;
        int r313 = 0;
    L323:
        if (r313 >= r224) goto L74;
        r42 = r1.getFileReference(r026.photos.get(r313), Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
        if (r42 != null) goto L74;
        r313 = r313 + 1;
        goto L323
    L329:
        if ((r72 instanceof TL_stories.TL_stories_stories) == false) goto L373;
        TL_stories.TL_stories_stories r027 = (TL_stories.TL_stories_stories) r72;
        if (r027.stories.isEmpty() == true) goto L354;
        TL_stories.StoryItem r028 = r027.stories.get(0);
        if (r145 != null) goto L337;
        TLRPC.Document r225 = r028.music;
        if (r225 == null) goto L337;
        byte[] r48 = r1.getFileReference(r225, null, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
    L338:
        TLRPC.MessageMedia r226 = r028.media;
        if (r226 == null) goto L353;
        if (r48 != null) goto L344;
        TLRPC.Photo r227 = r226.photo;
        if (r227 == null) goto L344;
        r48 = r1.getFileReference(r227, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
    L344:
        if (r48 != null) goto L348;
        TLRPC.Photo r228 = r028.media.video_cover;
        if (r228 == null) goto L348;
        r48 = r1.getFileReference(r228, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
    L348:
        if (r48 != null) goto L352;
        TLRPC.MessageMedia r229 = r028.media;
        TLRPC.Document r314 = r229.document;
        if (r314 == null) goto L352;
        r48 = r1.getFileReference(r314, r229.alt_documents, Requester.m4075$$Nest$fgetlocation(r12), r52, r62);
    L352:
        TL_stories.StoryItem r230 = r028;
    L356:
        if ((Requester.m4073$$Nest$fgetargs(r12)[1] instanceof FileLoadOperation) == false) goto L371;
        Object r029 = ((FileLoadOperation) Requester.m4073$$Nest$fgetargs(r12)[1]).parentObject;
        if ((r029 instanceof TL_stories.StoryItem) == false) goto L371;
        TL_stories.StoryItem r030 = (TL_stories.StoryItem) r029;
        if (r230 != null) goto L362;
        TL_stories.TL_updateStory r315 = new TL_stories.TL_updateStory();
        r312 = r12;
        r315.peer = r1.getMessagesController().getPeer(r030.dialogId);
        TL_stories.TL_storyItemDeleted r1116 = new TL_stories.TL_storyItemDeleted();
        r315.story = r1116;
        r1116.id = r030.id;
        ArrayList<TLRPC.Update> r1117 = new ArrayList();
        r1117.add(r315);
        r1.getMessagesController().processUpdateArray(r1117, null, null, false, 0);
    L367:
        if (r230 == null) goto L370;
        if (r48 != null) goto L370;
        TL_stories.TL_updateStory r316 = new TL_stories.TL_updateStory();
        byte[] r126 = r48;
        r27 = r52;
        r316.peer = MessagesController.getInstance(r1.currentAccount).getPeer(r030.dialogId);
        r316.story = r230;
        ArrayList<TLRPC.Update> r031 = new ArrayList();
        r031.add(r316);
        MessagesController.getInstance(r1.currentAccount).processUpdateArray(r031, null, null, false, 0);
    L372:
        r24 = r62;
        r42 = r126;
    L370:
        r126 = r48;
        r27 = r52;
        goto L372
    L362:
        r312 = r12;
        TLRPC.User r317 = r1.getMessagesController().getUser(Long.valueOf(r030.dialogId));
        if (r317 == null) goto L367;
        if (r317.contact == false) goto L367;
        MessagesController.getInstance(r1.currentAccount).getStoriesController().getStoriesStorage().updateStoryItem(r030.dialogId, r230);
    L371:
        r27 = r52;
        r312 = r12;
        r126 = r48;
        goto L372
    L353:
        r230 = null;
    L337:
        r48 = r145;
        goto L338
    L354:
        r230 = null;
        r48 = r145;
        goto L356
    L373:
        r27 = r52;
        r312 = r12;
        r24 = r62;
        goto L183
    L62:
        if ((Requester.m4075$$Nest$fgetlocation(r5) instanceof TLRPC.TL_inputPeerPhotoFileLocation) == false) goto L64;
    L387:
        r1.locationRequester.remove(r30);
        if (r16 == false) goto L390;
        r1.putReponseToCache(r30, r72);
    L390:
        return r16;
    L41:
        r3 = r33;
        r72 = r4;
        r16 = false;
        goto L42
    L7:
        if ((r4 instanceof TL_account.TL_wallPapers) == false) goto L10;
        r2 = "wallpaper";
        goto L5
    L10:
        if ((r4 instanceof TLRPC.TL_messages_savedGifs) == false) goto L13;
        r2 = "gif";
        goto L5
    L13:
        if ((r4 instanceof TLRPC.TL_messages_recentStickers) == false) goto L16;
        r2 = "recent";
        goto L5
    L16:
        if ((r4 instanceof TLRPC.TL_messages_favedStickers) == false) goto L18;
        r2 = "fav";
        goto L5
    L18:
        r10 = r31;
        goto L19
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean onUpdateObjectReference(Requester r8, byte[] r9, TLRPC.InputFileLocation r10, boolean r11) {
        if (BuildVars.DEBUG_VERSION == false) goto L6;
        FileLog.d("fileref updated for " + Requester.m4073$$Nest$fgetargs(r8)[0] + " " + Requester.m4076$$Nest$fgetlocationKey(r8));
    L6:
        if ((Requester.m4073$$Nest$fgetargs(r8)[0] instanceof TL_stories.TL_storyItem) == false) goto L9;
        ((TL_stories.TL_storyItem) Requester.m4073$$Nest$fgetargs(r8)[0]).media.document.file_reference = r9;
        return true;
    L9:
        String r5 = null;
        if ((Requester.m4073$$Nest$fgetargs(r8)[0] instanceof TLRPC.TL_inputSingleMedia) == false) goto L43;
        TLRPC.TL_messages_sendMultiMedia r102 = (TLRPC.TL_messages_sendMultiMedia) Requester.m4073$$Nest$fgetargs(r8)[1];
        Object[] r0 = this.multiMediaCache.get(r102);
        if (r0 != null) goto L14;
        return true;
    L14:
        TLRPC.TL_inputSingleMedia r82 = (TLRPC.TL_inputSingleMedia) Requester.m4073$$Nest$fgetargs(r8)[0];
        TLRPC.InputMedia r1 = r82.media;
        if ((r1 instanceof TLRPC.TL_inputMediaDocument) == false) goto L23;
        TLRPC.TL_inputMediaDocument r12 = (TLRPC.TL_inputMediaDocument) r1;
        if (r11 == true) goto L19;
    L21:
        r12.id.file_reference = r9;
    L30:
        int r83 = r102.multi_media.indexOf(r82);
        if (r83 >= 0) goto L33;
        return true;
    L33:
        ArrayList r92 = (ArrayList) r0[3];
        r92.set(r83, null);
        int r84 = 0;
        boolean r112 = true;
    L35:
        if (r84 >= r92.size()) goto L40;
        if (r92.get(r84) == null) goto L39;
        r112 = false;
    L39:
        r84 = r84 + 1;
        goto L35
    L40:
        if (r112 == false) goto L307;
        this.multiMediaCache.remove(r102);
        AndroidUtilities.runOnUIThread(new FileRefController$$ExternalSyntheticLambda10(this, r102, r0));
    L307:
        return true;
    L19:
        if (isSameReference(r12.id.file_reference, r9) == false) goto L21;
        return false;
    L23:
        if ((r1 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L30;
        TLRPC.TL_inputMediaPhoto r13 = (TLRPC.TL_inputMediaPhoto) r1;
        if (r11 == true) goto L27;
    L29:
        r13.id.file_reference = r9;
        goto L30
    L27:
        if (isSameReference(r13.id.file_reference, r9) == false) goto L29;
        return false;
    L43:
        if (Requester.m4073$$Nest$fgetargs(r8).length < 2) goto L85;
        if ((Requester.m4073$$Nest$fgetargs(r8)[1] instanceof TLRPC.TL_messages_sendMedia) == false) goto L85;
        if ((((TLRPC.TL_messages_sendMedia) Requester.m4073$$Nest$fgetargs(r8)[1]).media instanceof TLRPC.TL_inputMediaPaidMedia) == false) goto L85;
        if ((Requester.m4073$$Nest$fgetargs(r8)[0] instanceof TLRPC.TL_inputMediaPhoto) == false) goto L51;
    L52:
        TLRPC.TL_messages_sendMedia r103 = (TLRPC.TL_messages_sendMedia) Requester.m4073$$Nest$fgetargs(r8)[1];
        Object[] r02 = this.multiMediaCache.get(r103);
        if (r02 != null) goto L56;
        return true;
    L56:
        if ((Requester.m4073$$Nest$fgetargs(r8)[0] instanceof TLRPC.TL_inputMediaDocument) == false) goto L64;
        TLRPC.TL_inputMediaDocument r85 = (TLRPC.TL_inputMediaDocument) Requester.m4073$$Nest$fgetargs(r8)[0];
        if (r11 == true) goto L60;
    L62:
        r85.id.file_reference = r9;
        TLRPC.TL_inputMediaPhoto r86 = r85;
    L72:
        int r87 = ((TLRPC.TL_inputMediaPaidMedia) r103.media).extended_media.indexOf(r86);
        if (r87 >= 0) goto L75;
        return true;
    L75:
        ArrayList r93 = (ArrayList) r02[3];
        r93.set(r87, null);
        int r88 = 0;
        boolean r113 = true;
    L77:
        if (r88 >= r93.size()) goto L82;
        if (r93.get(r88) == null) goto L81;
        r113 = false;
    L81:
        r88 = r88 + 1;
        goto L77
    L82:
        if (r113 == false) goto L307;
        this.multiMediaCache.remove(r103);
        AndroidUtilities.runOnUIThread(new FileRefController$$ExternalSyntheticLambda12(this, r103, r02));
        goto L307
    L60:
        if (isSameReference(r85.id.file_reference, r9) == false) goto L62;
        return false;
    L64:
        if ((Requester.m4073$$Nest$fgetargs(r8)[0] instanceof TLRPC.TL_inputMediaPhoto) == false) goto L71;
        TLRPC.TL_inputMediaPhoto r89 = (TLRPC.TL_inputMediaPhoto) Requester.m4073$$Nest$fgetargs(r8)[0];
        if (r11 == true) goto L68;
    L70:
        r89.id.file_reference = r9;
        r86 = r89;
        goto L72
    L68:
        if (isSameReference(r89.id.file_reference, r9) == false) goto L70;
        return false;
    L71:
        r86 = null;
        goto L72
    L51:
        if ((Requester.m4073$$Nest$fgetargs(r8)[0] instanceof TLRPC.TL_inputMediaDocument) == true) goto L52;
    L85:
        if (Requester.m4073$$Nest$fgetargs(r8).length < 2) goto L127;
        if ((Requester.m4073$$Nest$fgetargs(r8)[1] instanceof TL_ephemeral.TL_sendMessage) == false) goto L127;
        if ((((TL_ephemeral.TL_sendMessage) Requester.m4073$$Nest$fgetargs(r8)[1]).media instanceof TLRPC.TL_inputMediaPaidMedia) == false) goto L127;
        if ((Requester.m4073$$Nest$fgetargs(r8)[0] instanceof TLRPC.TL_inputMediaPhoto) == false) goto L93;
    L94:
        TL_ephemeral.TL_sendMessage r104 = (TL_ephemeral.TL_sendMessage) Requester.m4073$$Nest$fgetargs(r8)[1];
        Object[] r03 = this.multiMediaCache.get(r104);
        if (r03 != null) goto L98;
        return true;
    L98:
        if ((Requester.m4073$$Nest$fgetargs(r8)[0] instanceof TLRPC.TL_inputMediaDocument) == false) goto L106;
        TLRPC.TL_inputMediaDocument r810 = (TLRPC.TL_inputMediaDocument) Requester.m4073$$Nest$fgetargs(r8)[0];
        if (r11 == true) goto L102;
    L104:
        r810.id.file_reference = r9;
        TLRPC.TL_inputMediaPhoto r811 = r810;
    L114:
        int r812 = ((TLRPC.TL_inputMediaPaidMedia) r104.media).extended_media.indexOf(r811);
        if (r812 >= 0) goto L117;
        return true;
    L117:
        ArrayList r94 = (ArrayList) r03[3];
        r94.set(r812, null);
        int r813 = 0;
        boolean r114 = true;
    L119:
        if (r813 >= r94.size()) goto L124;
        if (r94.get(r813) == null) goto L123;
        r114 = false;
    L123:
        r813 = r813 + 1;
        goto L119
    L124:
        if (r114 == false) goto L307;
        this.multiMediaCache.remove(r104);
        AndroidUtilities.runOnUIThread(new FileRefController$$ExternalSyntheticLambda13(this, r104, r03));
        goto L307
    L102:
        if (isSameReference(r810.id.file_reference, r9) == false) goto L104;
        return false;
    L106:
        if ((Requester.m4073$$Nest$fgetargs(r8)[0] instanceof TLRPC.TL_inputMediaPhoto) == false) goto L113;
        TLRPC.TL_inputMediaPhoto r814 = (TLRPC.TL_inputMediaPhoto) Requester.m4073$$Nest$fgetargs(r8)[0];
        if (r11 == true) goto L110;
    L112:
        r814.id.file_reference = r9;
        r811 = r814;
        goto L114
    L110:
        if (isSameReference(r814.id.file_reference, r9) == false) goto L112;
        return false;
    L113:
        r811 = null;
        goto L114
    L93:
        if ((Requester.m4073$$Nest$fgetargs(r8)[0] instanceof TLRPC.TL_inputMediaDocument) == true) goto L94;
    L127:
        if ((Requester.m4073$$Nest$fgetargs(r8)[0] instanceof TLRPC.TL_messages_sendMedia) == false) goto L146;
        TLRPC.InputMedia r105 = ((TLRPC.TL_messages_sendMedia) Requester.m4073$$Nest$fgetargs(r8)[0]).media;
        if ((r105 instanceof TLRPC.TL_inputMediaDocument) == false) goto L137;
        TLRPC.TL_inputMediaDocument r106 = (TLRPC.TL_inputMediaDocument) r105;
        if (r11 == true) goto L133;
    L135:
        r106.id.file_reference = r9;
    L144:
        AndroidUtilities.runOnUIThread(new FileRefController$$ExternalSyntheticLambda14(this, r8));
        goto L307
    L133:
        if (isSameReference(r106.id.file_reference, r9) == false) goto L135;
        return false;
    L137:
        if ((r105 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L144;
        TLRPC.TL_inputMediaPhoto r107 = (TLRPC.TL_inputMediaPhoto) r105;
        if (r11 == true) goto L141;
    L143:
        r107.id.file_reference = r9;
        goto L144
    L141:
        if (isSameReference(r107.id.file_reference, r9) == false) goto L143;
        return false;
    L146:
        if ((Requester.m4073$$Nest$fgetargs(r8)[0] instanceof TL_ephemeral.TL_sendMessage) == false) goto L165;
        TLRPC.InputMedia r108 = ((TL_ephemeral.TL_sendMessage) Requester.m4073$$Nest$fgetargs(r8)[0]).media;
        if ((r108 instanceof TLRPC.TL_inputMediaDocument) == false) goto L156;
        TLRPC.TL_inputMediaDocument r109 = (TLRPC.TL_inputMediaDocument) r108;
        if (r11 == true) goto L152;
    L154:
        r109.id.file_reference = r9;
    L163:
        AndroidUtilities.runOnUIThread(new FileRefController$$ExternalSyntheticLambda15(this, r8));
        goto L307
    L152:
        if (isSameReference(r109.id.file_reference, r9) == false) goto L154;
        return false;
    L156:
        if ((r108 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L163;
        TLRPC.TL_inputMediaPhoto r1010 = (TLRPC.TL_inputMediaPhoto) r108;
        if (r11 == true) goto L160;
    L162:
        r1010.id.file_reference = r9;
        goto L163
    L160:
        if (isSameReference(r1010.id.file_reference, r9) == false) goto L162;
        return false;
    L165:
        if ((Requester.m4073$$Nest$fgetargs(r8)[0] instanceof TLRPC.TL_messages_editMessage) == false) goto L184;
        TLRPC.InputMedia r1011 = ((TLRPC.TL_messages_editMessage) Requester.m4073$$Nest$fgetargs(r8)[0]).media;
        if ((r1011 instanceof TLRPC.TL_inputMediaDocument) == false) goto L175;
        TLRPC.TL_inputMediaDocument r1012 = (TLRPC.TL_inputMediaDocument) r1011;
        if (r11 == true) goto L171;
    L173:
        r1012.id.file_reference = r9;
    L182:
        AndroidUtilities.runOnUIThread(new FileRefController$$ExternalSyntheticLambda16(this, r8));
        goto L307
    L171:
        if (isSameReference(r1012.id.file_reference, r9) == false) goto L173;
        return false;
    L175:
        if ((r1011 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L182;
        TLRPC.TL_inputMediaPhoto r1013 = (TLRPC.TL_inputMediaPhoto) r1011;
        if (r11 == true) goto L179;
    L181:
        r1013.id.file_reference = r9;
        goto L182
    L179:
        if (isSameReference(r1013.id.file_reference, r9) == false) goto L181;
        return false;
    L184:
        if ((Requester.m4073$$Nest$fgetargs(r8)[0] instanceof TLRPC.InputPhoto) == true) goto L289;
        if ((Requester.m4073$$Nest$fgetargs(r8)[0] instanceof TLRPC.InputDocument) == true) goto L289;
        if ((Requester.m4073$$Nest$fgetargs(r8)[0] instanceof TLRPC.TL_messages_addPollAnswer) == false) goto L208;
        TLRPC.InputMedia r1014 = ((TLRPC.TL_messages_addPollAnswer) Requester.m4073$$Nest$fgetargs(r8)[0]).answer.input_media;
        if ((r1014 instanceof TLRPC.TL_inputMediaDocument) == false) goto L199;
        TLRPC.TL_inputMediaDocument r1015 = (TLRPC.TL_inputMediaDocument) r1014;
        if (r11 == true) goto L195;
    L197:
        r1015.id.file_reference = r9;
    L206:
        AndroidUtilities.runOnUIThread(new FileRefController$$ExternalSyntheticLambda17(this, r8));
        goto L307
    L195:
        if (isSameReference(r1015.id.file_reference, r9) == false) goto L197;
        return false;
    L199:
        if ((r1014 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L206;
        TLRPC.TL_inputMediaPhoto r1016 = (TLRPC.TL_inputMediaPhoto) r1014;
        if (r11 == true) goto L203;
    L205:
        r1016.id.file_reference = r9;
        goto L206
    L203:
        if (isSameReference(r1016.id.file_reference, r9) == false) goto L205;
        return false;
    L208:
        if ((Requester.m4073$$Nest$fgetargs(r8)[0] instanceof TLRPC.TL_messages_saveGif) == false) goto L216;
        TLRPC.TL_messages_saveGif r815 = (TLRPC.TL_messages_saveGif) Requester.m4073$$Nest$fgetargs(r8)[0];
        if (r11 == true) goto L212;
    L214:
        r815.id.file_reference = r9;
        getConnectionsManager().sendRequest(r815, new FileRefController$$ExternalSyntheticLambda18());
        goto L307
    L212:
        if (isSameReference(r815.id.file_reference, r9) == false) goto L214;
        return false;
    L216:
        if ((Requester.m4073$$Nest$fgetargs(r8)[0] instanceof TLRPC.TL_messages_saveRecentSticker) == false) goto L224;
        TLRPC.TL_messages_saveRecentSticker r816 = (TLRPC.TL_messages_saveRecentSticker) Requester.m4073$$Nest$fgetargs(r8)[0];
        if (r11 == true) goto L220;
    L222:
        r816.id.file_reference = r9;
        getConnectionsManager().sendRequest(r816, new FileRefController$$ExternalSyntheticLambda19());
        goto L307
    L220:
        if (isSameReference(r816.id.file_reference, r9) == false) goto L222;
        return false;
    L224:
        if ((Requester.m4073$$Nest$fgetargs(r8)[0] instanceof TLRPC.TL_stickers_addStickerToSet) == false) goto L232;
        TLRPC.TL_stickers_addStickerToSet r817 = (TLRPC.TL_stickers_addStickerToSet) Requester.m4073$$Nest$fgetargs(r8)[0];
        if (r11 == true) goto L228;
    L230:
        r817.sticker.document.file_reference = r9;
        getConnectionsManager().sendRequest(r817, new FileRefController$$ExternalSyntheticLambda20());
        goto L307
    L228:
        if (isSameReference(r817.sticker.document.file_reference, r9) == false) goto L230;
        return false;
    L232:
        if ((Requester.m4073$$Nest$fgetargs(r8)[0] instanceof TLRPC.TL_messages_faveSticker) == false) goto L240;
        TLRPC.TL_messages_faveSticker r818 = (TLRPC.TL_messages_faveSticker) Requester.m4073$$Nest$fgetargs(r8)[0];
        if (r11 == true) goto L236;
    L238:
        r818.id.file_reference = r9;
        getConnectionsManager().sendRequest(r818, new FileRefController$$ExternalSyntheticLambda11());
        goto L307
    L236:
        if (isSameReference(r818.id.file_reference, r9) == false) goto L238;
        return false;
    L240:
        if ((Requester.m4073$$Nest$fgetargs(r8)[0] instanceof TLRPC.TL_messages_getAttachedStickers) == false) goto L259;
        TLRPC.TL_messages_getAttachedStickers r1017 = (TLRPC.TL_messages_getAttachedStickers) Requester.m4073$$Nest$fgetargs(r8)[0];
        TLRPC.InputStickeredMedia r04 = r1017.media;
        if ((r04 instanceof TLRPC.TL_inputStickeredMediaDocument) == false) goto L250;
        TLRPC.TL_inputStickeredMediaDocument r05 = (TLRPC.TL_inputStickeredMediaDocument) r04;
        if (r11 == true) goto L246;
    L248:
        r05.id.file_reference = r9;
    L257:
        getConnectionsManager().sendRequest(r1017, (RequestDelegate) Requester.m4073$$Nest$fgetargs(r8)[1]);
        goto L307
    L246:
        if (isSameReference(r05.id.file_reference, r9) == false) goto L248;
        return false;
    L250:
        if ((r04 instanceof TLRPC.TL_inputStickeredMediaPhoto) == false) goto L257;
        TLRPC.TL_inputStickeredMediaPhoto r06 = (TLRPC.TL_inputStickeredMediaPhoto) r04;
        if (r11 == true) goto L254;
    L256:
        r06.id.file_reference = r9;
        goto L257
    L254:
        if (isSameReference(r06.id.file_reference, r9) == false) goto L256;
        return false;
    L259:
        if ((Requester.m4073$$Nest$fgetargs(r8)[1] instanceof FileLoadOperation) == false) goto L307;
        FileLoadOperation r07 = (FileLoadOperation) Requester.m4073$$Nest$fgetargs(r8)[1];
        if (r10 == null) goto L273;
        if (r11 == false) goto L267;
        if (isSameReference(r07.location.file_reference, r10.file_reference) == false) goto L267;
        return false;
    L267:
        if (BuildVars.LOGS_ENABLED == false) goto L269;
        String r7 = Utilities.bytesToHex(r07.location.file_reference);
    L270:
        r07.location = r10;
        if (BuildVars.LOGS_ENABLED == false) goto L284;
        r5 = Utilities.bytesToHex(r10.file_reference);
    L284:
        r07.requestingReference = false;
        if (BuildVars.LOGS_ENABLED == false) goto L287;
        FileLog.d("debug_loading: " + r07.getCacheFileFinal().getName() + " " + r7 + " " + r5 + " reference updated resume download");
    L287:
        r07.startDownloadRequest(-1);
        goto L307
    L269:
        r7 = null;
        goto L270
    L273:
        if (r11 == false) goto L278;
        if (isSameReference(Requester.m4075$$Nest$fgetlocation(r8).file_reference, r9) == false) goto L278;
        return false;
    L278:
        if (BuildVars.LOGS_ENABLED == false) goto L280;
        r7 = Utilities.bytesToHex(r07.location.file_reference);
    L281:
        TLRPC.InputFileLocation r1018 = r07.location;
        Requester.m4075$$Nest$fgetlocation(r8).file_reference = r9;
        r1018.file_reference = r9;
        if (BuildVars.LOGS_ENABLED == false) goto L284;
        r5 = Utilities.bytesToHex(r07.location.file_reference);
        goto L284
    L280:
        r7 = null;
    L289:
        if ((Requester.m4073$$Nest$fgetargs(r8)[0] instanceof TLRPC.InputPhoto) == false) goto L296;
        TLRPC.InputPhoto r1019 = (TLRPC.InputPhoto) Requester.m4073$$Nest$fgetargs(r8)[0];
        if (r11 == true) goto L293;
    L295:
        r1019.file_reference = r9;
    L303:
        if (Requester.m4073$$Nest$fgetargs(r8).length <= 1) goto L307;
        if ((Requester.m4073$$Nest$fgetargs(r8)[1] instanceof Runnable) == false) goto L307;
        AndroidUtilities.runOnUIThread((Runnable) Requester.m4073$$Nest$fgetargs(r8)[1]);
        goto L307
    L293:
        if (isSameReference(r1019.file_reference, r9) == false) goto L295;
        return false;
    L296:
        TLRPC.InputDocument r1020 = (TLRPC.InputDocument) Requester.m4073$$Nest$fgetargs(r8)[0];
        if (r11 == true) goto L299;
    L301:
        r1020.file_reference = r9;
        goto L303
    L299:
        if (isSameReference(r1020.file_reference, r9) == false) goto L301;
        return false;
    }

    private void putReponseToCache(String r4, TLObject r5) {
        if (this.responseCache.get(r4) != null) goto L6;
        CachedResult r0 = new CachedResult(null);
        CachedResult.m4072$$Nest$fputresponse(r0, r5);
        CachedResult.m4071$$Nest$fputfirstQueryTime(r0, System.currentTimeMillis());
        this.responseCache.put(r4, r0);
        return;
    }

    private void requestReferenceFromServer(Object r8, String r9, String r10, Object[] r11) {
        if ((r8 instanceof StoriesController.BotPreview) == false) goto L11;
        StoriesController.BotPreview r82 = (StoriesController.BotPreview) r8;
        StoriesController.BotPreviewsList r0 = r82.list;
        if (r0 != null) goto L8;
        sendErrorToObject(r11, 0);
        return;
    L8:
        r0.requestReference(r82, new FileRefController$$ExternalSyntheticLambda21(this, r9, r10));
        return;
    L11:
        if ((r8 instanceof TL_stories.StoryItem) == false) goto L15;
        TL_stories.StoryItem r83 = (TL_stories.StoryItem) r8;
        TL_stories.TL_stories_getStoriesByID r112 = new TL_stories.TL_stories_getStoriesByID();
        r112.peer = getMessagesController().getInputPeer(r83.dialogId);
        r112.id.add(Integer.valueOf(r83.id));
        getConnectionsManager().sendRequest(r112, new FileRefController$$ExternalSyntheticLambda32(this, r9, r10));
        return;
    L15:
        if ((r8 instanceof TLRPC.TL_help_premiumPromo) == false) goto L19;
        getConnectionsManager().sendRequest(new TLRPC.TL_help_getPremiumPromo(), new FileRefController$$ExternalSyntheticLambda42(this, r9, r10));
        return;
    L19:
        if ((r8 instanceof TLRPC.TL_availableReaction) == false) goto L23;
        TLRPC.TL_messages_getAvailableReactions r84 = new TLRPC.TL_messages_getAvailableReactions();
        r84.hash = 0;
        getConnectionsManager().sendRequest(r84, new FileRefController$$ExternalSyntheticLambda43(this, r9, r10));
        return;
    L23:
        if ((r8 instanceof TL_bots.BotInfo) == false) goto L27;
        TLRPC.TL_users_getFullUser r113 = new TLRPC.TL_users_getFullUser();
        r113.id = getMessagesController().getInputUser(((TL_bots.BotInfo) r8).user_id);
        getConnectionsManager().sendRequest(r113, new FileRefController$$ExternalSyntheticLambda44(this, r9, r10));
        return;
    L27:
        if ((r8 instanceof TLRPC.TL_attachMenuBot) == false) goto L31;
        TLRPC.TL_messages_getAttachMenuBot r114 = new TLRPC.TL_messages_getAttachMenuBot();
        r114.bot = getMessagesController().getInputUser(((TLRPC.TL_attachMenuBot) r8).bot_id);
        getConnectionsManager().sendRequest(r114, new FileRefController$$ExternalSyntheticLambda45(this, r9, r10));
        return;
    L31:
        if ((r8 instanceof MessageObject) == false) goto L54;
        MessageObject r85 = (MessageObject) r8;
        long r02 = r85.getChannelId();
        TLRPC.InputPeer r115 = getMessagesController().getInputPeer(r85.getDialogId());
        if (r85.scheduled == false) goto L37;
        TLRPC.TL_messages_getScheduledMessages r116 = new TLRPC.TL_messages_getScheduledMessages();
        r116.peer = getMessagesController().getInputPeer(r85.getDialogId());
        r116.id.add(Integer.valueOf(r85.getRealId()));
        getConnectionsManager().sendRequest(r116, new FileRefController$$ExternalSyntheticLambda46(this, r9, r10));
        return;
    L37:
        if (r85.isQuickReply() == false) goto L40;
        TLRPC.TL_messages_getQuickReplyMessages r117 = new TLRPC.TL_messages_getQuickReplyMessages();
        r117.shortcut_id = r85.getQuickReplyId();
        r117.flags |= 1;
        r117.id.add(Integer.valueOf(r85.getRealId()));
        getConnectionsManager().sendRequest(r117, new FileRefController$$ExternalSyntheticLambda47(this, r9, r10));
        return;
    L40:
        TLRPC.Message r2 = r85.messageOwner;
        if (r2 == null) goto L48;
        if (r2.rich_message == null) goto L48;
        if (r115 == null) goto L48;
        TL_iv.getRichMessage r03 = new TL_iv.getRichMessage();
        r03.peer = r115;
        r03.id = r85.getRealId();
        getConnectionsManager().sendRequest(r03, new FileRefController$$ExternalSyntheticLambda48(this, r9, r10));
        return;
    L48:
        if (r02 == 0) goto L51;
        TLRPC.TL_channels_getMessages r118 = new TLRPC.TL_channels_getMessages();
        r118.channel = getMessagesController().getInputChannel(r02);
        r118.id.add(Integer.valueOf(r85.getRealId()));
        getConnectionsManager().sendRequest(r118, new FileRefController$$ExternalSyntheticLambda49(this, r9, r10));
        return;
    L51:
        TLRPC.TL_messages_getMessages r119 = new TLRPC.TL_messages_getMessages();
        r119.id.add(Integer.valueOf(r85.getRealId()));
        getConnectionsManager().sendRequest(r119, new FileRefController$$ExternalSyntheticLambda22(this, r9, r10));
        return;
    L54:
        if ((r8 instanceof TLRPC.TL_wallPaper) == false) goto L58;
        TLRPC.TL_wallPaper r86 = (TLRPC.TL_wallPaper) r8;
        TL_account.getWallPaper r1110 = new TL_account.getWallPaper();
        TLRPC.TL_inputWallPaper r04 = new TLRPC.TL_inputWallPaper();
        r04.id = r86.id;
        r04.access_hash = r86.access_hash;
        r1110.wallpaper = r04;
        getConnectionsManager().sendRequest(r1110, new FileRefController$$ExternalSyntheticLambda23(this, r9, r10));
        return;
    L58:
        if ((r8 instanceof TLRPC.TL_theme) == false) goto L62;
        TLRPC.TL_theme r87 = (TLRPC.TL_theme) r8;
        TL_account.getTheme r1111 = new TL_account.getTheme();
        TLRPC.TL_inputTheme r05 = new TLRPC.TL_inputTheme();
        r05.id = r87.id;
        r05.access_hash = r87.access_hash;
        r1111.theme = r05;
        r1111.format = "android";
        getConnectionsManager().sendRequest(r1111, new FileRefController$$ExternalSyntheticLambda24(this, r9, r10));
        return;
    L62:
        if ((r8 instanceof TLRPC.WebPage) == false) goto L66;
        TLRPC.TL_messages_getWebPage r1112 = new TLRPC.TL_messages_getWebPage();
        r1112.url = ((TLRPC.WebPage) r8).url;
        r1112.hash = 0;
        getConnectionsManager().sendRequest(r1112, new FileRefController$$ExternalSyntheticLambda25(this, r9, r10));
        return;
    L66:
        if ((r8 instanceof TLRPC.User) == false) goto L70;
        TLRPC.TL_users_getUsers r1113 = new TLRPC.TL_users_getUsers();
        r1113.id.add(getMessagesController().getInputUser((TLRPC.User) r8));
        getConnectionsManager().sendRequest(r1113, new FileRefController$$ExternalSyntheticLambda26(this, r9, r10));
        return;
    L70:
        if ((r8 instanceof TLRPC.Chat) == false) goto L84;
        TLRPC.Chat r88 = (TLRPC.Chat) r8;
        if ((r88 instanceof TLRPC.TL_chat) == false) goto L76;
        TLRPC.TL_messages_getChats r1114 = new TLRPC.TL_messages_getChats();
        r1114.id.add(Long.valueOf(r88.id));
        getConnectionsManager().sendRequest(r1114, new FileRefController$$ExternalSyntheticLambda27(this, r9, r10));
        return;
    L76:
        if ((r88 instanceof TLRPC.TL_channel) == false) goto L78;
    L81:
        TLRPC.TL_channels_getChannels r1115 = new TLRPC.TL_channels_getChannels();
        r1115.id.add(MessagesController.getInputChannel(r88));
        getConnectionsManager().sendRequest(r1115, new FileRefController$$ExternalSyntheticLambda28(this, r9, r10));
        return;
    L78:
        if ((r88 instanceof TLRPC.TL_community) == true) goto L81;
        return;
    L84:
        if ((r8 instanceof String) == false) goto L140;
        String r89 = (String) r8;
        if ("wallpaper".equals(r89) == false) goto L93;
        if (this.wallpaperWaiters.isEmpty() == false) goto L90;
        getConnectionsManager().sendRequest(new TL_account.getWallPapers(), new FileRefController$$ExternalSyntheticLambda29(this));
    L90:
        this.wallpaperWaiters.add(new Waiter(r9, r10));
        return;
    L93:
        if (r89.startsWith("gif") == false) goto L100;
        if (this.savedGifsWaiters.isEmpty() == false) goto L97;
        getConnectionsManager().sendRequest(new TLRPC.TL_messages_getSavedGifs(), new FileRefController$$ExternalSyntheticLambda30(this));
    L97:
        this.savedGifsWaiters.add(new Waiter(r9, r10));
        return;
    L100:
        if ("recent".equals(r89) == false) goto L107;
        if (this.recentStickersWaiter.isEmpty() == false) goto L104;
        getConnectionsManager().sendRequest(new TLRPC.TL_messages_getRecentStickers(), new FileRefController$$ExternalSyntheticLambda31(this));
    L104:
        this.recentStickersWaiter.add(new Waiter(r9, r10));
        return;
    L107:
        if ("fav".equals(r89) == false) goto L114;
        if (this.favStickersWaiter.isEmpty() == false) goto L111;
        getConnectionsManager().sendRequest(new TLRPC.TL_messages_getFavedStickers(), new FileRefController$$ExternalSyntheticLambda33(this));
    L111:
        this.favStickersWaiter.add(new Waiter(r9, r10));
        return;
    L114:
        if (PluginsConstants.UPDATE.equals(r89) == false) goto L118;
        UpdaterUtils.getAppUpdate(new FileRefController$$ExternalSyntheticLambda34(this, r9, r10));
        return;
    L118:
        if (r89.startsWith("avatar_") == false) goto L126;
        long r5 = Utilities.parseLong(r89).longValue();
        if (r5 <= 0) goto L123;
        TLRPC.TL_photos_getUserPhotos r810 = new TLRPC.TL_photos_getUserPhotos();
        r810.limit = 80;
        r810.offset = 0;
        r810.max_id = 0;
        r810.user_id = getMessagesController().getInputUser(r5);
        getConnectionsManager().sendRequest(r810, new FileRefController$$ExternalSyntheticLambda35(this, r9, r10));
        return;
    L123:
        TLRPC.TL_messages_search r811 = new TLRPC.TL_messages_search();
        r811.filter = new TLRPC.TL_inputMessagesFilterChatPhotos();
        r811.limit = 80;
        r811.offset_id = 0;
        r811.q = _UrlKt.FRAGMENT_ENCODE_SET;
        r811.peer = getMessagesController().getInputPeer(r5);
        getConnectionsManager().sendRequest(r811, new FileRefController$$ExternalSyntheticLambda36(this, r9, r10));
        return;
    L126:
        if (r89.startsWith("sent_") == false) goto L137;
        String[] r812 = r89.split("_");
        if (r812.length < 3) goto L135;
        long r06 = Utilities.parseLong(r812[1]).longValue();
        if (r06 == 0) goto L133;
        TLRPC.TL_channels_getMessages r1116 = new TLRPC.TL_channels_getMessages();
        r1116.channel = getMessagesController().getInputChannel(r06);
        r1116.id.add(Integer.valueOf(Utilities.parseInt(r812[2])));
        getConnectionsManager().sendRequest(r1116, new FileRefController$$ExternalSyntheticLambda37(this, r9, r10));
        return;
    L133:
        TLRPC.TL_messages_getMessages r1117 = new TLRPC.TL_messages_getMessages();
        r1117.id.add(Integer.valueOf(Utilities.parseInt(r812[2])));
        getConnectionsManager().sendRequest(r1117, new FileRefController$$ExternalSyntheticLambda38(this, r9, r10));
        return;
    L135:
        sendErrorToObject(r11, 0);
        return;
    L137:
        sendErrorToObject(r11, 0);
        return;
    L140:
        if ((r8 instanceof TLRPC.TL_messages_stickerSet) == false) goto L144;
        TLRPC.TL_messages_getStickerSet r1118 = new TLRPC.TL_messages_getStickerSet();
        TLRPC.TL_inputStickerSetID r07 = new TLRPC.TL_inputStickerSetID();
        r1118.stickerset = r07;
        TLRPC.StickerSet r813 = ((TLRPC.TL_messages_stickerSet) r8).set;
        r07.id = r813.id;
        r07.access_hash = r813.access_hash;
        getConnectionsManager().sendRequest(r1118, new FileRefController$$ExternalSyntheticLambda39(this, r9, r10));
        return;
    L144:
        if ((r8 instanceof TLRPC.StickerSetCovered) == false) goto L148;
        TLRPC.TL_messages_getStickerSet r1119 = new TLRPC.TL_messages_getStickerSet();
        TLRPC.TL_inputStickerSetID r08 = new TLRPC.TL_inputStickerSetID();
        r1119.stickerset = r08;
        TLRPC.StickerSet r814 = ((TLRPC.StickerSetCovered) r8).set;
        r08.id = r814.id;
        r08.access_hash = r814.access_hash;
        getConnectionsManager().sendRequest(r1119, new FileRefController$$ExternalSyntheticLambda40(this, r9, r10));
        return;
    L148:
        if ((r8 instanceof TLRPC.InputStickerSet) == false) goto L151;
        TLRPC.TL_messages_getStickerSet r1120 = new TLRPC.TL_messages_getStickerSet();
        r1120.stickerset = (TLRPC.InputStickerSet) r8;
        getConnectionsManager().sendRequest(r1120, new FileRefController$$ExternalSyntheticLambda41(this, r9, r10));
        return;
    L151:
        sendErrorToObject(r11, 0);
    }

    private void sendErrorToObject(Object[] r5, int r6) {
        Object r0 = r5[0];
        if ((r0 instanceof TLRPC.TL_inputSingleMedia) == false) goto L9;
        TLRPC.TL_messages_sendMultiMedia r52 = (TLRPC.TL_messages_sendMultiMedia) r5[1];
        Object[] r62 = this.multiMediaCache.get(r52);
        if (r62 == null) goto L78;
        this.multiMediaCache.remove(r52);
        AndroidUtilities.runOnUIThread(new FileRefController$$ExternalSyntheticLambda5(this, r52, r62));
        return;
    L78:
        return;
    L9:
        if ((r0 instanceof TLRPC.TL_inputMediaDocument) == false) goto L11;
    L12:
        Object r1 = r5[1];
        if ((r1 instanceof TLRPC.TL_messages_sendMedia) == false) goto L19;
        TLRPC.TL_messages_sendMedia r12 = (TLRPC.TL_messages_sendMedia) r1;
        Object[] r53 = this.multiMediaCache.get(r12);
        if (r53 == null) goto L79;
        this.multiMediaCache.remove(r12);
        AndroidUtilities.runOnUIThread(new FileRefController$$ExternalSyntheticLambda6(this, r12, r53));
        return;
    L79:
        return;
    L19:
        if ((r0 instanceof TLRPC.TL_inputMediaDocument) == false) goto L21;
    L22:
        Object r13 = r5[1];
        if ((r13 instanceof TL_ephemeral.TL_sendMessage) == false) goto L29;
        TL_ephemeral.TL_sendMessage r14 = (TL_ephemeral.TL_sendMessage) r13;
        Object[] r54 = this.multiMediaCache.get(r14);
        if (r54 == null) goto L80;
        this.multiMediaCache.remove(r14);
        AndroidUtilities.runOnUIThread(new FileRefController$$ExternalSyntheticLambda7(this, r14, r54));
        return;
    L80:
        return;
    L29:
        if ((r0 instanceof TLRPC.TL_messages_sendMedia) == false) goto L35;
        if ((((TLRPC.TL_messages_sendMedia) r0).media instanceof TLRPC.TL_inputMediaPaidMedia) == true) goto L35;
        if ((((TLRPC.TL_messages_sendMedia) r0).media instanceof TLRPC.TL_inputMediaPoll) == true) goto L35;
    L76:
        AndroidUtilities.runOnUIThread(new FileRefController$$ExternalSyntheticLambda8(this, r5));
        return;
    L35:
        if ((r0 instanceof TLRPC.TL_messages_editMessage) == true) goto L76;
        if ((r0 instanceof TLRPC.TL_messages_addPollAnswer) == true) goto L76;
        if ((r0 instanceof TL_ephemeral.TL_sendMessage) == false) goto L46;
        if ((((TL_ephemeral.TL_sendMessage) r0).media instanceof TLRPC.TL_inputMediaPaidMedia) == true) goto L46;
        if ((((TL_ephemeral.TL_sendMessage) r0).media instanceof TLRPC.TL_inputMediaPoll) == true) goto L46;
    L74:
        AndroidUtilities.runOnUIThread(new FileRefController$$ExternalSyntheticLambda9(this, r5));
        return;
    L46:
        if ((r0 instanceof TLRPC.TL_messages_editMessage) == true) goto L74;
        if ((r0 instanceof TLRPC.TL_messages_addPollAnswer) == true) goto L74;
        if ((r0 instanceof TLRPC.TL_messages_saveGif) == false) goto L55;
        TLRPC.TL_messages_saveGif r02 = (TLRPC.TL_messages_saveGif) r0;
        return;
    L55:
        if ((r0 instanceof TLRPC.TL_messages_saveRecentSticker) == false) goto L59;
        TLRPC.TL_messages_saveRecentSticker r03 = (TLRPC.TL_messages_saveRecentSticker) r0;
        return;
    L59:
        if ((r0 instanceof TLRPC.TL_stickers_addStickerToSet) == false) goto L63;
        TLRPC.TL_stickers_addStickerToSet r04 = (TLRPC.TL_stickers_addStickerToSet) r0;
        return;
    L63:
        if ((r0 instanceof TLRPC.TL_messages_faveSticker) == false) goto L67;
        TLRPC.TL_messages_faveSticker r05 = (TLRPC.TL_messages_faveSticker) r0;
        return;
    L67:
        if ((r0 instanceof TLRPC.TL_messages_getAttachedStickers) == false) goto L70;
        getConnectionsManager().sendRequest((TLRPC.TL_messages_getAttachedStickers) r0, (RequestDelegate) r5[1]);
        return;
    L70:
        Object r4 = r5[1];
        if ((r4 instanceof FileLoadOperation) == false) goto L81;
        FileLoadOperation r42 = (FileLoadOperation) r4;
        r42.requestingReference = false;
        FileLog.e("debug_loading: " + r42.getCacheFileFinal().getName() + " reference can't update: fail operation ");
        r42.onFail(false, 0);
        return;
    L81:
        return;
    L21:
        if ((r0 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L29;
    L11:
        if ((r0 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L19;
        goto L12
    }

    private boolean updateFileReferenceFromCache(byte[] r6, TLRPC.InputFileLocation r7, TLRPC.InputFileLocation r8, String r9, Object... r10) {
        Object r0 = r10[0];
        if ((r0 instanceof TL_stories.TL_storyItem) == false) goto L7;
        ((TL_stories.TL_storyItem) r0).media.document.file_reference = r6;
        return true;
    L7:
        if ((r0 instanceof TLRPC.TL_inputSingleMedia) == false) goto L10;
        return false;
    L10:
        if (r10.length < 2) goto L21;
        Object r1 = r10[1];
        if ((r1 instanceof TLRPC.TL_messages_sendMedia) == false) goto L21;
        if ((((TLRPC.TL_messages_sendMedia) r1).media instanceof TLRPC.TL_inputMediaPaidMedia) == false) goto L21;
        if ((r0 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L18;
    L19:
        return false;
    L18:
        if ((r0 instanceof TLRPC.TL_inputMediaDocument) == true) goto L19;
    L21:
        if (r10.length < 2) goto L32;
        Object r12 = r10[1];
        if ((r12 instanceof TLRPC.TL_messages_sendMedia) == false) goto L32;
        if ((((TLRPC.TL_messages_sendMedia) r12).media instanceof TLRPC.TL_inputMediaPoll) == false) goto L32;
        if ((r0 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L29;
    L30:
        return false;
    L29:
        if ((r0 instanceof TLRPC.TL_inputMediaDocument) == true) goto L30;
    L32:
        if (r10.length < 2) goto L43;
        Object r13 = r10[1];
        if ((r13 instanceof TL_ephemeral.TL_sendMessage) == false) goto L43;
        if ((((TL_ephemeral.TL_sendMessage) r13).media instanceof TLRPC.TL_inputMediaPaidMedia) == false) goto L43;
        if ((r0 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L40;
    L41:
        return false;
    L40:
        if ((r0 instanceof TLRPC.TL_inputMediaDocument) == true) goto L41;
    L43:
        if (r10.length < 2) goto L54;
        Object r14 = r10[1];
        if ((r14 instanceof TL_ephemeral.TL_sendMessage) == false) goto L54;
        if ((((TL_ephemeral.TL_sendMessage) r14).media instanceof TLRPC.TL_inputMediaPoll) == false) goto L54;
        if ((r0 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L51;
    L52:
        return false;
    L51:
        if ((r0 instanceof TLRPC.TL_inputMediaDocument) == true) goto L52;
    L54:
        if ((r0 instanceof TLRPC.TL_messages_sendMedia) == false) goto L68;
        TLRPC.InputMedia r72 = ((TLRPC.TL_messages_sendMedia) r0).media;
        if ((r72 instanceof TLRPC.TL_inputMediaDocument) == false) goto L62;
        TLRPC.TL_inputMediaDocument r73 = (TLRPC.TL_inputMediaDocument) r72;
        if (isSameReference(r73.id.file_reference, r6) == false) goto L60;
        return false;
    L60:
        r73.id.file_reference = r6;
    L174:
        return true;
    L62:
        if ((r72 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L174;
        TLRPC.TL_inputMediaPhoto r74 = (TLRPC.TL_inputMediaPhoto) r72;
        if (isSameReference(r74.id.file_reference, r6) == false) goto L66;
        return false;
    L66:
        r74.id.file_reference = r6;
        goto L174
    L68:
        if ((r0 instanceof TL_ephemeral.TL_sendMessage) == false) goto L82;
        TLRPC.InputMedia r75 = ((TL_ephemeral.TL_sendMessage) r0).media;
        if ((r75 instanceof TLRPC.TL_inputMediaDocument) == false) goto L76;
        TLRPC.TL_inputMediaDocument r76 = (TLRPC.TL_inputMediaDocument) r75;
        if (isSameReference(r76.id.file_reference, r6) == false) goto L74;
        return false;
    L74:
        r76.id.file_reference = r6;
        goto L174
    L76:
        if ((r75 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L174;
        TLRPC.TL_inputMediaPhoto r77 = (TLRPC.TL_inputMediaPhoto) r75;
        if (isSameReference(r77.id.file_reference, r6) == false) goto L80;
        return false;
    L80:
        r77.id.file_reference = r6;
        goto L174
    L82:
        if ((r0 instanceof TLRPC.TL_messages_editMessage) == false) goto L96;
        TLRPC.InputMedia r78 = ((TLRPC.TL_messages_editMessage) r0).media;
        if ((r78 instanceof TLRPC.TL_inputMediaDocument) == false) goto L90;
        TLRPC.TL_inputMediaDocument r79 = (TLRPC.TL_inputMediaDocument) r78;
        if (isSameReference(r79.id.file_reference, r6) == false) goto L88;
        return false;
    L88:
        r79.id.file_reference = r6;
        goto L174
    L90:
        if ((r78 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L174;
        TLRPC.TL_inputMediaPhoto r710 = (TLRPC.TL_inputMediaPhoto) r78;
        if (isSameReference(r710.id.file_reference, r6) == false) goto L94;
        return false;
    L94:
        r710.id.file_reference = r6;
        goto L174
    L96:
        if ((r0 instanceof TLRPC.TL_messages_addPollAnswer) == false) goto L110;
        TLRPC.InputMedia r711 = ((TLRPC.TL_messages_addPollAnswer) r0).answer.input_media;
        if ((r711 instanceof TLRPC.TL_inputMediaDocument) == false) goto L104;
        TLRPC.TL_inputMediaDocument r712 = (TLRPC.TL_inputMediaDocument) r711;
        if (isSameReference(r712.id.file_reference, r6) == false) goto L102;
        return false;
    L102:
        r712.id.file_reference = r6;
        goto L174
    L104:
        if ((r711 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L174;
        TLRPC.TL_inputMediaPhoto r713 = (TLRPC.TL_inputMediaPhoto) r711;
        if (isSameReference(r713.id.file_reference, r6) == false) goto L108;
        return false;
    L108:
        r713.id.file_reference = r6;
        goto L174
    L110:
        if ((r0 instanceof TLRPC.TL_messages_saveGif) == false) goto L116;
        TLRPC.TL_messages_saveGif r02 = (TLRPC.TL_messages_saveGif) r0;
        if (isSameReference(r02.id.file_reference, r6) == false) goto L114;
        return false;
    L114:
        r02.id.file_reference = r6;
        goto L174
    L116:
        if ((r0 instanceof TLRPC.TL_messages_saveRecentSticker) == false) goto L122;
        TLRPC.TL_messages_saveRecentSticker r03 = (TLRPC.TL_messages_saveRecentSticker) r0;
        if (isSameReference(r03.id.file_reference, r6) == false) goto L120;
        return false;
    L120:
        r03.id.file_reference = r6;
        goto L174
    L122:
        if ((r0 instanceof TLRPC.TL_stickers_addStickerToSet) == false) goto L128;
        TLRPC.TL_stickers_addStickerToSet r04 = (TLRPC.TL_stickers_addStickerToSet) r0;
        if (isSameReference(r04.sticker.document.file_reference, r6) == false) goto L126;
        return false;
    L126:
        r04.sticker.document.file_reference = r6;
        goto L174
    L128:
        if ((r0 instanceof TLRPC.TL_messages_faveSticker) == false) goto L134;
        TLRPC.TL_messages_faveSticker r05 = (TLRPC.TL_messages_faveSticker) r0;
        if (isSameReference(r05.id.file_reference, r6) == false) goto L132;
        return false;
    L132:
        r05.id.file_reference = r6;
        goto L174
    L134:
        if ((r0 instanceof TLRPC.TL_messages_getAttachedStickers) == false) goto L147;
        TLRPC.InputStickeredMedia r714 = ((TLRPC.TL_messages_getAttachedStickers) r0).media;
        if ((r714 instanceof TLRPC.TL_inputStickeredMediaDocument) == false) goto L142;
        TLRPC.TL_inputStickeredMediaDocument r715 = (TLRPC.TL_inputStickeredMediaDocument) r714;
        if (isSameReference(r715.id.file_reference, r6) == false) goto L140;
        return false;
    L140:
        r715.id.file_reference = r6;
        goto L174
    L142:
        if ((r714 instanceof TLRPC.TL_inputStickeredMediaPhoto) == false) goto L174;
        TLRPC.TL_inputStickeredMediaPhoto r716 = (TLRPC.TL_inputStickeredMediaPhoto) r714;
        if (isSameReference(r716.id.file_reference, r6) == false) goto L146;
        return false;
    L146:
        r716.id.file_reference = r6;
        goto L174
    L147:
        Object r102 = r10[1];
        if ((r102 instanceof FileLoadOperation) == false) goto L174;
        FileLoadOperation r103 = (FileLoadOperation) r102;
        String r06 = null;
        if (r7 == null) goto L162;
        if (isSameReference(r103.location.file_reference, r7.file_reference) == false) goto L155;
        return false;
    L155:
        if (BuildVars.LOGS_ENABLED == false) goto L157;
        String r5 = Utilities.bytesToHex(r103.location.file_reference);
    L158:
        r103.location = r7;
        if (BuildVars.LOGS_ENABLED == false) goto L172;
        r06 = Utilities.bytesToHex(r7.file_reference);
    L172:
        if (BuildVars.LOGS_ENABLED == false) goto L174;
        FileLog.d("debug_loading: from fileref cache updated fileref from " + r5 + " to " + r06);
        goto L174
    L157:
        r5 = null;
        goto L158
    L162:
        if (isSameReference(r8.file_reference, r6) == false) goto L165;
        return false;
    L165:
        if (BuildVars.LOGS_ENABLED == false) goto L167;
        r5 = Utilities.bytesToHex(r103.location.file_reference);
    L168:
        TLRPC.InputFileLocation r717 = r103.location;
        r8.file_reference = r6;
        r717.file_reference = r6;
        if (BuildVars.LOGS_ENABLED == false) goto L172;
        r06 = Utilities.bytesToHex(r6);
        goto L172
    L167:
        r5 = null;
        goto L168
    }

    public boolean applyCachedFileReference(Object r10, Object... r11) {
        Pair<TLRPC.InputFileLocation, String> r0 = getLocationAndKey(r10, r11);
        if (r0 != null) goto L5;
        return false;
    L5:
        TLRPC.InputFileLocation r4 = (TLRPC.InputFileLocation) r0.first;
        String r5 = (String) r0.second;
        String r6 = getKeyForParentObject(r10);
        if (r6 != null) goto L9;
        return false;
    L9:
        if ((r10 instanceof String) == false) goto L25;
        String r102 = (String) r10;
        String r02 = "wallpaper";
        if ("wallpaper".equals(r102) == true) goto L26;
        r02 = "gif";
        if (r102.startsWith("gif") == true) goto L26;
        r02 = "recent";
        if ("recent".equals(r102) == true) goto L26;
        r02 = "fav";
        if ("fav".equals(r102) == true) goto L26;
        r02 = PluginsConstants.UPDATE;
        if (PluginsConstants.UPDATE.equals(r102) == false) goto L25;
    L26:
        CachedResult r103 = getCachedResponse(r02);
        if (r103 == null) goto L32;
        FileRefController r3 = this;
        Object[] r8 = r11;
        Pair<byte[], TLRPC.InputFileLocation> r9 = r3.getFileReferenceFromResponse(r4, r5, r6, CachedResult.m4070$$Nest$fgetresponse(r103), r8);
        if (r9 != null) goto L31;
    L33:
        CachedResult r92 = r3.getCachedResponse(r6);
        if (r92 == null) goto L39;
        Pair<byte[], TLRPC.InputFileLocation> r93 = r3.getFileReferenceFromResponse(r4, r6, null, CachedResult.m4070$$Nest$fgetresponse(r92), r8);
        if (r93 == null) goto L39;
        return r3.updateFileReferenceFromCache((byte[]) r93.first, (TLRPC.InputFileLocation) r93.second, r4, r6, r8);
    L39:
        return false;
    L31:
        return r3.updateFileReferenceFromCache((byte[]) r9.first, (TLRPC.InputFileLocation) r9.second, r4, r5, r8);
    L32:
        r3 = this;
        r8 = r11;
    L25:
        r02 = r5;
        goto L26
    }

    public Pair<TLRPC.InputFileLocation, String> getLocationAndKey(Object r6, Object... r7) {
        Object r72 = r7[0];
        if ((r72 instanceof TLRPC.TL_messages_sendMultiMedia) == false) goto L6;
        return null;
    L6:
        if ((r72 instanceof TLRPC.TL_messages_sendMedia) == false) goto L13;
        if ((((TLRPC.TL_messages_sendMedia) r72).media instanceof TLRPC.TL_inputMediaPaidMedia) == false) goto L13;
        if ((r6 instanceof ArrayList) == false) goto L13;
        return null;
    L13:
        if ((r72 instanceof TLRPC.TL_messages_sendMedia) == false) goto L20;
        if ((((TLRPC.TL_messages_sendMedia) r72).media instanceof TLRPC.TL_inputMediaPoll) == false) goto L20;
        if ((r6 instanceof ArrayList) == false) goto L20;
        return null;
    L20:
        if ((r72 instanceof TL_ephemeral.TL_sendMessage) == false) goto L27;
        if ((((TL_ephemeral.TL_sendMessage) r72).media instanceof TLRPC.TL_inputMediaPaidMedia) == false) goto L27;
        if ((r6 instanceof ArrayList) == false) goto L27;
        return null;
    L27:
        if ((r72 instanceof TL_ephemeral.TL_sendMessage) == false) goto L34;
        if ((((TL_ephemeral.TL_sendMessage) r72).media instanceof TLRPC.TL_inputMediaPoll) == false) goto L34;
        if ((r6 instanceof ArrayList) == false) goto L34;
        return null;
    L34:
        if ((r72 instanceof StoriesController.BotPreview) == false) goto L46;
        StoriesController.BotPreview r73 = (StoriesController.BotPreview) r72;
        TLRPC.MessageMedia r5 = r73.media;
        if (r5.document == null) goto L40;
        TLRPC.TL_inputDocumentFileLocation r52 = new TLRPC.TL_inputDocumentFileLocation();
        r52.id = r73.media.document.id;
        return new Pair(r52, "botstory_doc_" + r73.media.document.id);
    L40:
        if (r5.photo == null) goto L44;
        TLRPC.TL_inputPhotoFileLocation r53 = new TLRPC.TL_inputPhotoFileLocation();
        r53.id = r73.media.photo.id;
        return new Pair(r53, "botstory_photo_" + r73.media.photo.id);
    L44:
        return new Pair(new TLRPC.TL_inputDocumentFileLocation(), "botstory_" + r73.id);
    L46:
        if ((r72 instanceof TL_stories.TL_storyItem) == false) goto L50;
        TL_stories.TL_storyItem r74 = (TL_stories.TL_storyItem) r72;
        TLRPC.TL_inputDocumentFileLocation r54 = new TLRPC.TL_inputDocumentFileLocation();
        r54.id = r74.media.document.id;
        return new Pair(r54, "story_" + r74.id);
    L50:
        if ((r72 instanceof TLRPC.TL_inputSingleMedia) == false) goto L60;
        TLRPC.InputMedia r55 = ((TLRPC.TL_inputSingleMedia) r72).media;
        if ((r55 instanceof TLRPC.TL_inputMediaDocument) == false) goto L56;
        TLRPC.TL_inputMediaDocument r56 = (TLRPC.TL_inputMediaDocument) r55;
        TLRPC.TL_inputDocumentFileLocation r62 = new TLRPC.TL_inputDocumentFileLocation();
        r62.id = r56.id.id;
        return new Pair(r62, "file_" + r56.id.id);
    L56:
        if ((r55 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L187;
        TLRPC.TL_inputMediaPhoto r57 = (TLRPC.TL_inputMediaPhoto) r55;
        TLRPC.TL_inputPhotoFileLocation r63 = new TLRPC.TL_inputPhotoFileLocation();
        r63.id = r57.id.id;
        return new Pair(r63, "photo_" + r57.id.id);
    L187:
        return null;
    L60:
        if ((r72 instanceof TLRPC.TL_inputMediaDocument) == false) goto L64;
        TLRPC.TL_inputMediaDocument r75 = (TLRPC.TL_inputMediaDocument) r72;
        TLRPC.TL_inputDocumentFileLocation r58 = new TLRPC.TL_inputDocumentFileLocation();
        r58.id = r75.id.id;
        return new Pair(r58, "file_" + r75.id.id);
    L64:
        if ((r72 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L68;
        TLRPC.TL_inputMediaPhoto r76 = (TLRPC.TL_inputMediaPhoto) r72;
        TLRPC.TL_inputPhotoFileLocation r59 = new TLRPC.TL_inputPhotoFileLocation();
        r59.id = r76.id.id;
        return new Pair(r59, "photo_" + r76.id.id);
    L68:
        if ((r72 instanceof TLRPC.TL_messages_sendMedia) == false) goto L93;
        TLRPC.InputMedia r77 = ((TLRPC.TL_messages_sendMedia) r72).media;
        if ((r77 instanceof TLRPC.TL_inputMediaDocument) == false) goto L74;
        TLRPC.TL_inputMediaDocument r78 = (TLRPC.TL_inputMediaDocument) r77;
        TLRPC.TL_inputDocumentFileLocation r510 = new TLRPC.TL_inputDocumentFileLocation();
        r510.id = r78.id.id;
        return new Pair(r510, "file_" + r78.id.id);
    L74:
        if ((r77 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L78;
        TLRPC.TL_inputMediaPhoto r79 = (TLRPC.TL_inputMediaPhoto) r77;
        TLRPC.TL_inputPhotoFileLocation r511 = new TLRPC.TL_inputPhotoFileLocation();
        r511.id = r79.id.id;
        return new Pair(r511, "photo_" + r79.id.id);
    L78:
        if ((r77 instanceof TLRPC.TL_inputMediaPaidMedia) == false) goto L187;
        TLRPC.TL_inputMediaPaidMedia r710 = (TLRPC.TL_inputMediaPaidMedia) r77;
        if ((r6 instanceof ArrayList) == true) goto L187;
        if (r710.extended_media.size() != 1) goto L187;
        TLRPC.InputMedia r512 = r710.extended_media.get(0);
        if ((r512 instanceof TLRPC.TL_inputMediaDocument) == false) goto L89;
        TLRPC.TL_inputMediaDocument r513 = (TLRPC.TL_inputMediaDocument) r512;
        TLRPC.TL_inputDocumentFileLocation r64 = new TLRPC.TL_inputDocumentFileLocation();
        r64.id = r513.id.id;
        return new Pair(r64, "file_" + r513.id.id);
    L89:
        if ((r512 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L187;
        TLRPC.TL_inputMediaPhoto r514 = (TLRPC.TL_inputMediaPhoto) r512;
        TLRPC.TL_inputPhotoFileLocation r65 = new TLRPC.TL_inputPhotoFileLocation();
        r65.id = r514.id.id;
        return new Pair(r65, "photo_" + r514.id.id);
    L93:
        if ((r72 instanceof TL_ephemeral.TL_sendMessage) == false) goto L118;
        TLRPC.InputMedia r711 = ((TL_ephemeral.TL_sendMessage) r72).media;
        if ((r711 instanceof TLRPC.TL_inputMediaDocument) == false) goto L99;
        TLRPC.TL_inputMediaDocument r712 = (TLRPC.TL_inputMediaDocument) r711;
        TLRPC.TL_inputDocumentFileLocation r515 = new TLRPC.TL_inputDocumentFileLocation();
        r515.id = r712.id.id;
        return new Pair(r515, "file_" + r712.id.id);
    L99:
        if ((r711 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L103;
        TLRPC.TL_inputMediaPhoto r713 = (TLRPC.TL_inputMediaPhoto) r711;
        TLRPC.TL_inputPhotoFileLocation r516 = new TLRPC.TL_inputPhotoFileLocation();
        r516.id = r713.id.id;
        return new Pair(r516, "photo_" + r713.id.id);
    L103:
        if ((r711 instanceof TLRPC.TL_inputMediaPaidMedia) == false) goto L187;
        TLRPC.TL_inputMediaPaidMedia r714 = (TLRPC.TL_inputMediaPaidMedia) r711;
        if ((r6 instanceof ArrayList) == true) goto L187;
        if (r714.extended_media.size() != 1) goto L187;
        TLRPC.InputMedia r517 = r714.extended_media.get(0);
        if ((r517 instanceof TLRPC.TL_inputMediaDocument) == false) goto L114;
        TLRPC.TL_inputMediaDocument r518 = (TLRPC.TL_inputMediaDocument) r517;
        TLRPC.TL_inputDocumentFileLocation r66 = new TLRPC.TL_inputDocumentFileLocation();
        r66.id = r518.id.id;
        return new Pair(r66, "file_" + r518.id.id);
    L114:
        if ((r517 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L187;
        TLRPC.TL_inputMediaPhoto r519 = (TLRPC.TL_inputMediaPhoto) r517;
        TLRPC.TL_inputPhotoFileLocation r67 = new TLRPC.TL_inputPhotoFileLocation();
        r67.id = r519.id.id;
        return new Pair(r67, "photo_" + r519.id.id);
    L118:
        if ((r72 instanceof TLRPC.TL_messages_editMessage) == false) goto L128;
        TLRPC.InputMedia r520 = ((TLRPC.TL_messages_editMessage) r72).media;
        if ((r520 instanceof TLRPC.TL_inputMediaDocument) == false) goto L124;
        TLRPC.TL_inputMediaDocument r521 = (TLRPC.TL_inputMediaDocument) r520;
        TLRPC.TL_inputDocumentFileLocation r68 = new TLRPC.TL_inputDocumentFileLocation();
        r68.id = r521.id.id;
        return new Pair(r68, "file_" + r521.id.id);
    L124:
        if ((r520 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L187;
        TLRPC.TL_inputMediaPhoto r522 = (TLRPC.TL_inputMediaPhoto) r520;
        TLRPC.TL_inputPhotoFileLocation r69 = new TLRPC.TL_inputPhotoFileLocation();
        r69.id = r522.id.id;
        return new Pair(r69, "photo_" + r522.id.id);
    L128:
        if ((r72 instanceof TLRPC.InputPhoto) == false) goto L132;
        TLRPC.InputPhoto r715 = (TLRPC.InputPhoto) r72;
        TLRPC.TL_inputPhotoFileLocation r523 = new TLRPC.TL_inputPhotoFileLocation();
        r523.id = r715.id;
        return new Pair(r523, "photo_" + r715.id);
    L132:
        if ((r72 instanceof TLRPC.InputDocument) == false) goto L136;
        TLRPC.InputDocument r716 = (TLRPC.InputDocument) r72;
        TLRPC.TL_inputDocumentFileLocation r524 = new TLRPC.TL_inputDocumentFileLocation();
        r524.id = r716.id;
        return new Pair(r524, "file_" + r716.id);
    L136:
        if ((r72 instanceof TLRPC.TL_messages_addPollAnswer) == false) goto L146;
        TLRPC.InputMedia r525 = ((TLRPC.TL_messages_addPollAnswer) r72).answer.input_media;
        if ((r525 instanceof TLRPC.TL_inputMediaDocument) == false) goto L142;
        TLRPC.TL_inputMediaDocument r526 = (TLRPC.TL_inputMediaDocument) r525;
        TLRPC.TL_inputDocumentFileLocation r610 = new TLRPC.TL_inputDocumentFileLocation();
        r610.id = r526.id.id;
        return new Pair(r610, "file_" + r526.id.id);
    L142:
        if ((r525 instanceof TLRPC.TL_inputMediaPhoto) == false) goto L187;
        TLRPC.TL_inputMediaPhoto r527 = (TLRPC.TL_inputMediaPhoto) r525;
        TLRPC.TL_inputPhotoFileLocation r611 = new TLRPC.TL_inputPhotoFileLocation();
        r611.id = r527.id.id;
        return new Pair(r611, "photo_" + r527.id.id);
    L146:
        if ((r72 instanceof TLRPC.TL_messages_saveGif) == false) goto L150;
        TLRPC.TL_messages_saveGif r717 = (TLRPC.TL_messages_saveGif) r72;
        TLRPC.TL_inputDocumentFileLocation r528 = new TLRPC.TL_inputDocumentFileLocation();
        r528.id = r717.id.id;
        return new Pair(r528, "file_" + r717.id.id);
    L150:
        if ((r72 instanceof TLRPC.TL_messages_saveRecentSticker) == false) goto L154;
        TLRPC.TL_messages_saveRecentSticker r718 = (TLRPC.TL_messages_saveRecentSticker) r72;
        TLRPC.TL_inputDocumentFileLocation r529 = new TLRPC.TL_inputDocumentFileLocation();
        r529.id = r718.id.id;
        return new Pair(r529, "file_" + r718.id.id);
    L154:
        if ((r72 instanceof TLRPC.TL_stickers_addStickerToSet) == false) goto L158;
        TLRPC.TL_stickers_addStickerToSet r719 = (TLRPC.TL_stickers_addStickerToSet) r72;
        TLRPC.TL_inputDocumentFileLocation r530 = new TLRPC.TL_inputDocumentFileLocation();
        r530.id = r719.sticker.document.id;
        return new Pair(r530, "file_" + r719.sticker.document.id);
    L158:
        if ((r72 instanceof TLRPC.TL_messages_faveSticker) == false) goto L162;
        TLRPC.TL_messages_faveSticker r720 = (TLRPC.TL_messages_faveSticker) r72;
        TLRPC.TL_inputDocumentFileLocation r531 = new TLRPC.TL_inputDocumentFileLocation();
        r531.id = r720.id.id;
        return new Pair(r531, "file_" + r720.id.id);
    L162:
        if ((r72 instanceof TLRPC.TL_messages_getAttachedStickers) == false) goto L172;
        TLRPC.InputStickeredMedia r532 = ((TLRPC.TL_messages_getAttachedStickers) r72).media;
        if ((r532 instanceof TLRPC.TL_inputStickeredMediaDocument) == false) goto L168;
        TLRPC.TL_inputStickeredMediaDocument r533 = (TLRPC.TL_inputStickeredMediaDocument) r532;
        TLRPC.TL_inputDocumentFileLocation r612 = new TLRPC.TL_inputDocumentFileLocation();
        r612.id = r533.id.id;
        return new Pair(r612, "file_" + r533.id.id);
    L168:
        if ((r532 instanceof TLRPC.TL_inputStickeredMediaPhoto) == false) goto L187;
        TLRPC.TL_inputStickeredMediaPhoto r534 = (TLRPC.TL_inputStickeredMediaPhoto) r532;
        TLRPC.TL_inputPhotoFileLocation r613 = new TLRPC.TL_inputPhotoFileLocation();
        r613.id = r534.id.id;
        return new Pair(r613, "photo_" + r534.id.id);
    L172:
        if ((r72 instanceof TLRPC.TL_inputFileLocation) == false) goto L176;
        TLRPC.TL_inputFileLocation r721 = (TLRPC.TL_inputFileLocation) r72;
        return new Pair(r721, "loc_" + r721.local_id + "_" + r721.volume_id);
    L176:
        if ((r72 instanceof TLRPC.TL_inputDocumentFileLocation) == false) goto L180;
        TLRPC.TL_inputDocumentFileLocation r722 = (TLRPC.TL_inputDocumentFileLocation) r72;
        return new Pair(r722, "file_" + r722.id);
    L180:
        if ((r72 instanceof TLRPC.TL_inputPhotoFileLocation) == false) goto L184;
        TLRPC.TL_inputPhotoFileLocation r723 = (TLRPC.TL_inputPhotoFileLocation) r72;
        return new Pair(r723, "photo_" + r723.id);
    L184:
        if ((r72 instanceof TLRPC.TL_inputPeerPhotoFileLocation) == false) goto L187;
        TLRPC.TL_inputPeerPhotoFileLocation r724 = (TLRPC.TL_inputPeerPhotoFileLocation) r72;
        return new Pair(r724, "avatar_" + r724.id);
    }

    public void requestReference(Object r11, Object... r12) {
        int r1 = 0;
        if (BuildVars.LOGS_ENABLED == false) goto L5;
        FileLog.d("start loading request reference parent " + getObjectString(r11) + " args = " + r12[0]);
    L5:
        Object r0 = r12[0];
        if ((r0 instanceof TLRPC.TL_messages_sendMultiMedia) == false) goto L15;
        TLRPC.TL_messages_sendMultiMedia r02 = (TLRPC.TL_messages_sendMultiMedia) r0;
        ArrayList r112 = (ArrayList) r11;
        this.multiMediaCache.put(r02, r12);
        int r122 = r02.multi_media.size();
    L8:
        if (r1 >= r122) goto L110;
        TLRPC.TL_inputSingleMedia r2 = r02.multi_media.get(r1);
        Object r3 = r112.get(r1);
        if (r3 == null) goto L13;
        requestReference(r3, new Object[]{r2, r02});
    L13:
        r1 = r1 + 1;
        goto L8
    L110:
        return;
    L15:
        if ((r0 instanceof TLRPC.TL_messages_sendMedia) == false) goto L28;
        if ((((TLRPC.TL_messages_sendMedia) r0).media instanceof TLRPC.TL_inputMediaPaidMedia) == false) goto L28;
        if ((r11 instanceof ArrayList) == false) goto L28;
        TLRPC.TL_messages_sendMedia r03 = (TLRPC.TL_messages_sendMedia) r0;
        TLRPC.TL_inputMediaPaidMedia r22 = (TLRPC.TL_inputMediaPaidMedia) r03.media;
        ArrayList r113 = (ArrayList) r11;
        this.multiMediaCache.put(r03, r12);
        int r123 = r22.extended_media.size();
    L21:
        if (r1 >= r123) goto L111;
        TLRPC.InputMedia r32 = r22.extended_media.get(r1);
        Object r4 = r113.get(r1);
        if (r4 == null) goto L26;
        requestReference(r4, new Object[]{r32, r03});
    L26:
        r1 = r1 + 1;
        goto L21
    L111:
        return;
    L28:
        if ((r0 instanceof TL_ephemeral.TL_sendMessage) == true) goto L30;
    L40:
        Pair<TLRPC.InputFileLocation, String> r04 = getLocationAndKey(r11, r12);
        if (r04 != null) goto L44;
        sendErrorToObject(r12, 0);
        return;
    L44:
        TLRPC.InputFileLocation r23 = (TLRPC.InputFileLocation) r04.first;
        String r42 = (String) r04.second;
        if ((r11 instanceof MessageObject) == false) goto L55;
        MessageObject r05 = (MessageObject) r11;
        if (r05.getRealId() >= 0) goto L55;
        TLRPC.Message r06 = r05.messageOwner;
        if (r06 == null) goto L55;
        TLRPC.MessageMedia r07 = r06.media;
        if (r07 == null) goto L55;
        TLRPC.WebPage r08 = r07.webpage;
        if (r08 == null) goto L55;
        r11 = r08;
    L55:
        String r5 = getKeyForParentObject(r11);
        if (r5 != null) goto L59;
        sendErrorToObject(r12, 0);
        return;
    L59:
        Requester r09 = new Requester(null);
        Requester.m4077$$Nest$fputargs(r09, r12);
        Requester.m4079$$Nest$fputlocation(r09, r23);
        Requester.m4080$$Nest$fputlocationKey(r09, r42);
        ArrayList<Requester> r24 = this.locationRequester.get(r42);
        if (r24 != null) goto L62;
        r24 = new ArrayList();
        this.locationRequester.put(r42, r24);
        r1 = 1;
    L62:
        r24.add(r09);
        ArrayList<Requester> r25 = this.parentRequester.get(r5);
        if (r25 != null) goto L65;
        r25 = new ArrayList();
        this.parentRequester.put(r5, r25);
        r1 = r1 + 1;
    L65:
        r25.add(r09);
        if (r1 == 2) goto L69;
        return;
    L69:
        if ((r11 instanceof String) == false) goto L85;
        String r010 = (String) r11;
        String r13 = "wallpaper";
        if ("wallpaper".equals(r010) == true) goto L86;
        r13 = "gif";
        if (r010.startsWith("gif") == true) goto L86;
        r13 = "recent";
        if ("recent".equals(r010) == true) goto L86;
        r13 = "fav";
        if ("fav".equals(r010) == true) goto L86;
        r13 = PluginsConstants.UPDATE;
        if (PluginsConstants.UPDATE.equals(r010) == false) goto L85;
    L86:
        cleanupCache();
        CachedResult r011 = getCachedResponse(r13);
        if (r011 == null) goto L91;
        FileRefController r33 = this;
        if (r33.onRequestComplete(r42, r5, CachedResult.m4070$$Nest$fgetresponse(r011), null, false, true) == true) goto L96;
        r33.responseCache.remove(r42);
    L97:
        r33.requestReferenceFromServer(r11, r42, r5, r12);
        return;
    L96:
        return;
    L91:
        r33 = this;
        CachedResult r10 = r33.getCachedResponse(r5);
        if (r10 == null) goto L97;
        if (r33.onRequestComplete(r42, r5, CachedResult.m4070$$Nest$fgetresponse(r10), null, false, true) == true) goto L108;
        r33.responseCache.remove(r5);
        goto L97
    L108:
        return;
    L85:
        r13 = r42;
        goto L86
    L30:
        if ((((TL_ephemeral.TL_sendMessage) r0).media instanceof TLRPC.TL_inputMediaPaidMedia) == false) goto L40;
        if ((r11 instanceof ArrayList) == false) goto L40;
        TL_ephemeral.TL_sendMessage r012 = (TL_ephemeral.TL_sendMessage) r0;
        TLRPC.TL_inputMediaPaidMedia r26 = (TLRPC.TL_inputMediaPaidMedia) r012.media;
        ArrayList r114 = (ArrayList) r11;
        this.multiMediaCache.put(r012, r12);
        int r124 = r26.extended_media.size();
    L34:
        if (r1 >= r124) goto L112;
        TLRPC.InputMedia r34 = r26.extended_media.get(r1);
        Object r43 = r114.get(r1);
        if (r43 == null) goto L39;
        requestReference(r43, new Object[]{r34, r012});
    L39:
        r1 = r1 + 1;
        goto L34
    }
}
