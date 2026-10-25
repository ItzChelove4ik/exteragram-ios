package com.exteragram.messenger.export.controllers;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.util.Log;
import androidx.camera.core.ImageCapture$$ExternalSyntheticBackport0;
import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.AppDataDirGuesser;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.export.ExportSettings;
import com.exteragram.messenger.export.api.ApiWrap$Chat;
import com.exteragram.messenger.export.api.ApiWrap$ChatProcess;
import com.exteragram.messenger.export.api.ApiWrap$ChatsProcess;
import com.exteragram.messenger.export.api.ApiWrap$ContactInfo;
import com.exteragram.messenger.export.api.ApiWrap$ContactsList;
import com.exteragram.messenger.export.api.ApiWrap$ContactsProcess;
import com.exteragram.messenger.export.api.ApiWrap$DialogInfo;
import com.exteragram.messenger.export.api.ApiWrap$DialogsInfo;
import com.exteragram.messenger.export.api.ApiWrap$DialogsProcess;
import com.exteragram.messenger.export.api.ApiWrap$Document;
import com.exteragram.messenger.export.api.ApiWrap$DownloadProgress;
import com.exteragram.messenger.export.api.ApiWrap$ExportPersonalInfo;
import com.exteragram.messenger.export.api.ApiWrap$File;
import com.exteragram.messenger.export.api.ApiWrap$FileLocation;
import com.exteragram.messenger.export.api.ApiWrap$FileOrigin;
import com.exteragram.messenger.export.api.ApiWrap$FileProcess;
import com.exteragram.messenger.export.api.ApiWrap$FileProgress;
import com.exteragram.messenger.export.api.ApiWrap$LeftChannelsProcess;
import com.exteragram.messenger.export.api.ApiWrap$LoadedFileCache;
import com.exteragram.messenger.export.api.ApiWrap$Media;
import com.exteragram.messenger.export.api.ApiWrap$Message;
import com.exteragram.messenger.export.api.ApiWrap$MessageId;
import com.exteragram.messenger.export.api.ApiWrap$MessagesSlice;
import com.exteragram.messenger.export.api.ApiWrap$OtherDataProcess;
import com.exteragram.messenger.export.api.ApiWrap$ParseMediaContext;
import com.exteragram.messenger.export.api.ApiWrap$Peer;
import com.exteragram.messenger.export.api.ApiWrap$Reaction;
import com.exteragram.messenger.export.api.ApiWrap$SessionsList;
import com.exteragram.messenger.export.api.ApiWrap$StoriesProcess;
import com.exteragram.messenger.export.api.ApiWrap$StoriesSlice;
import com.exteragram.messenger.export.api.ApiWrap$Story;
import com.exteragram.messenger.export.api.ApiWrap$TextPart;
import com.exteragram.messenger.export.api.ApiWrap$User;
import com.exteragram.messenger.export.api.ApiWrap$UserpicsInfo;
import com.exteragram.messenger.export.api.ApiWrap$UserpicsProcess;
import com.exteragram.messenger.export.api.DataTypesUtils;
import com.exteragram.messenger.export.api.ExportRequests$FinishTakeoutSession;
import com.exteragram.messenger.export.api.ExportRequests$InitTakeoutSession;
import com.exteragram.messenger.export.api.ExportRequests$InvokeWithMessagesRange;
import com.exteragram.messenger.export.api.ExportRequests$InvokeWithTakeoutWrapper;
import com.exteragram.messenger.export.api.ExportRequests$TL_contacts_getSaved;
import com.exteragram.messenger.export.api.ExportRequests$TL_inputTakeoutFileLocation;
import com.exteragram.messenger.export.api.ExportRequests$Takeout;
import com.exteragram.messenger.export.api.ExportRequests$getLeftChannels;
import com.exteragram.messenger.export.api.ExportRequests$getSplitRanges;
import com.exteragram.messenger.export.output.OutputFile;
import com.exteragram.messenger.export.output.html.HtmlWriter;
import com.google.android.gms.cast.CastStatusCodes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Set;
import okhttp3.OkHttpClient$Builder$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import okio.Buffer$$ExternalSyntheticBUOutline4;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.SaveToGallerySettingsHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.tgnet.tl.TL_stories;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline2;

/* loaded from: classes4.dex */
public class ExportRequestsController {
    private static final ExportRequestsController[] Instance = null;
    private ApiWrap$ChatProcess _chatProcess;
    private ApiWrap$ContactsProcess _contactsProcess;
    private ApiWrap$DialogsProcess _dialogsProcess;
    private final ApiWrap$LoadedFileCache _fileCache;
    private ApiWrap$FileProcess _fileProcess;
    private ApiWrap$LeftChannelsProcess _leftChannelsProcess;
    private ApiWrap$OtherDataProcess _otherDataProcess;
    private final HashMap<Long, ApiWrap$Document> _resolvedCustomEmoji;
    private long _selfId;
    private ExportSettings _settings;
    private StartProcess _startProcess;
    private OutputFile.Stats _stats;
    private ApiWrap$StoriesProcess _storiesProcess;
    private long _takeoutId;
    private final Set<Long> _unresolvedCustomEmoji;
    private ApiWrap$UserpicsProcess _userpicsProcess;
    public int index;
    private final int selectedAcc;
    public ArrayList<TLRPC.TL_messageRange> splits;

    /* renamed from: com.exteragram.messenger.export.controllers.ExportRequestsController$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$exteragram$messenger$export$controllers$ExportRequestsController$StartProcess$Step = null;

        static {
            int[] r0 = new int[StartProcess.Step.values().length];
            $SwitchMap$com$exteragram$messenger$export$controllers$ExportRequestsController$StartProcess$Step = r0;
            r0[StartProcess.Step.UserpicsCount.ordinal()] = 1;     // Catch: NoSuchFieldError -> L9
        L14:
            $SwitchMap$com$exteragram$messenger$export$controllers$ExportRequestsController$StartProcess$Step[StartProcess.Step.StoriesCount.ordinal()] = 2;     // Catch: NoSuchFieldError -> L10
        L18:
            $SwitchMap$com$exteragram$messenger$export$controllers$ExportRequestsController$StartProcess$Step[StartProcess.Step.SplitRanges.ordinal()] = 3;     // Catch: NoSuchFieldError -> L11
        L22:
            $SwitchMap$com$exteragram$messenger$export$controllers$ExportRequestsController$StartProcess$Step[StartProcess.Step.DialogsCount.ordinal()] = 4;     // Catch: NoSuchFieldError -> L12
        L16:
            $SwitchMap$com$exteragram$messenger$export$controllers$ExportRequestsController$StartProcess$Step[StartProcess.Step.LeftChannelsCount.ordinal()] = 5;     // Catch: NoSuchFieldError -> L13
            return;
        }
    }

    public static class StartInfo {
        public int dialogsCount;
        public int storiesCount;
        public int userpicsCount;

        public StartInfo() {
            this.userpicsCount = 0;
            this.storiesCount = 0;
            this.dialogsCount = 0;
        }
    }

    public static class StartProcess {
        public Utilities.Callback<StartInfo> done;
        public StartInfo info;
        public int splitIndex;
        public ArrayList<Step> steps;

        public enum Step extends Enum<Step> {
            private static final /* synthetic */ Step[] $VALUES = null;
            public static final Step DialogsCount = null;
            public static final Step LeftChannelsCount = null;
            public static final Step SplitRanges = null;
            public static final Step StoriesCount = null;
            public static final Step UserpicsCount = null;

            private static /* synthetic */ Step[] $values() {
                return new Step[]{UserpicsCount, StoriesCount, SplitRanges, DialogsCount, LeftChannelsCount};
            }

            static {
                UserpicsCount = new Step("UserpicsCount", 0);
                StoriesCount = new Step("StoriesCount", 1);
                SplitRanges = new Step("SplitRanges", 2);
                DialogsCount = new Step("DialogsCount", 3);
                LeftChannelsCount = new Step("LeftChannelsCount", 4);
                $VALUES = $values();
            }

            Step(String r1, int r2) {
            }

            public static Step valueOf(String r1) {
                return (Step) Enum.valueOf(Step.class, r1);
            }

            public static Step[] values() {
                return (Step[]) $VALUES.clone();
            }
        }

        public StartProcess() {
            this.steps = new ArrayList();
            this.splitIndex = 0;
            this.info = new StartInfo();
        }
    }

    /* renamed from: $r8$lambda$-d8drHcW0tbO0J-l_R3OPlOHXjM, reason: not valid java name */
    public static /* synthetic */ void m1323$r8$lambda$d8drHcW0tbO0Jl_R3OPlOHXjM(ExportRequestsController r0, String r1) {
        r0.otherDataDone(r1);
    }

    public static /* synthetic */ void $r8$lambda$15j77mHtvDz5TJyg58JCy0NHeio(ExportRequestsController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$requestStories$20(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$30ZtYc4_hGWJI_s1TbNQsqUK2CI(ExportRequestsController r0, ApiWrap$FileProgress r1) {
        return r0.loadMessageFileProgress(r1);
    }

    public static /* synthetic */ void $r8$lambda$34_d_iiKBxep_vKiBWyHr0EFlVA(ExportRequestsController r0, ArrayList r1) {
        r0.lambda$resolveCustomEmoji$11(r1);
    }

    public static /* synthetic */ void $r8$lambda$3VOaQKjOx64YFpu5ckZRRmyBUpU(ExportRequestsController r0) {
        r0.lambda$requestLeftChannelsCount$6();
    }

    /* renamed from: $r8$lambda$6afHhTRDA-nouz5Be_j8dqnywow, reason: not valid java name */
    public static /* synthetic */ void m1324$r8$lambda$6afHhTRDAnouz5Be_j8dqnywow(ExportRequestsController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$requestContacts$24(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$843cuR6nUap0rKjH079ozdMbQGk(ExportRequestsController r0, Runnable r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$startMainSession$0(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$8GV1xRvu0RMaPonfIf258hoGN6A(Utilities.Callback r0, TLObject r1, TLRPC.TL_error r2) {
        r0.run(r1);
    }

    /* renamed from: $r8$lambda$APBkmQF-ndzThkn3H4KEQLQtm0o, reason: not valid java name */
    public static /* synthetic */ void m1325$r8$lambda$APBkmQFndzThkn3H4KEQLQtm0o(ExportRequestsController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$requestUserpicsCount$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$AqQIrFYD2Z2jG_eLPJcClgVKwN0(ExportRequestsController r0, long r1, String r3) {
        r0.lambda$getCustomEmoji$27(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$DmwZRarXo4KJhcPxoUUiBRgasA4(ExportRequestsController r0, int r1, TLRPC.messages_Messages r2) {
        r0.lambda$requestMessagesCount$8(r1, r2);
    }

    /* renamed from: $r8$lambda$EVquxO9loyMIPrnn0CUm-OacozU, reason: not valid java name */
    public static /* synthetic */ void m1326$r8$lambda$EVquxO9loyMIPrnn0CUmOacozU(ExportRequestsController r0, ApiWrap$DialogsInfo r1) {
        r0.lambda$requestLeftChannelsIfNeeded$39(r1);
    }

    /* renamed from: $r8$lambda$FZTd00l88oI9Vf-burrYmYBqMNg, reason: not valid java name */
    public static /* synthetic */ void m1327$r8$lambda$FZTd00l88oI9VfburrYmYBqMNg(ExportRequestsController r0, String r1) {
        r0.loadUserpicDone(r1);
    }

    public static /* synthetic */ Boolean $r8$lambda$FoGckG13BxXcgDqTs7DVAPN3qyo(ApiWrap$DialogInfo.Type r1) {
        if (r1 != ApiWrap$DialogInfo.Type.PrivateSupergroup) goto L5;
    L8:
        boolean r12 = true;
    L10:
        return Boolean.valueOf(r12);
    L5:
        if (r1 == ApiWrap$DialogInfo.Type.PublicSupergroup) goto L8;
        r12 = false;
        goto L10
    }

    /* renamed from: $r8$lambda$FpZP15H65FKsSDHDt6t_Z_-JEPg, reason: not valid java name */
    public static /* synthetic */ void m1328$r8$lambda$FpZP15H65FKsSDHDt6t_Z_JEPg(ExportRequestsController r0, Runnable r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$requestLeftChannelsSliceGeneric$7(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$HJkllswopTj46m50CWW1T4TtX40(ExportRequestsController r0, TLObject r1) {
        r0.lambda$requestSinglePeerDialog$31(r1);
    }

    public static /* synthetic */ void $r8$lambda$HLcCsZ4U7reqNxiMfOsjFGps9C4(ExportRequestsController r0, Utilities.Callback r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$requestSessions$5(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$ID3PlX3WVIkpqmRir75qkyEtSFQ(ExportRequestsController r0, int r1, int r2, TLRPC.messages_Messages r3) {
        r0.lambda$checkFirstMessageDate$9(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$JOrrttBReoI92Mg6vHgLY0KTqG4(ExportRequestsController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$requestStoriesCount$3(r1, r2);
    }

    /* renamed from: $r8$lambda$K5E2oN2LAwJDAki5Obw5-XGaixo, reason: not valid java name */
    public static /* synthetic */ void m1329$r8$lambda$K5E2oN2LAwJDAki5Obw5XGaixo(Runnable r1, TLObject r2, TLRPC.TL_error r3) {
        r1.run();
        if ((r2 instanceof TLRPC.TL_boolTrue) == false) goto L6;
        Log.w("exteraGram", "finished successfully!!!");
        return;
    L6:
        Log.e("exteraGram", "failed: " + r3);
    }

    /* renamed from: $r8$lambda$K6Z8Dxak9YlqZ-3WIvUBpQOpswQ, reason: not valid java name */
    public static /* synthetic */ void m1330$r8$lambda$K6Z8Dxak9YlqZ3WIvUBpQOpswQ(Utilities.Callback r0, TLObject r1, TLRPC.TL_error r2) {
        if ((r1 instanceof TLRPC.TL_users_userFull) == false) goto L10;
        TLRPC.TL_users_userFull r12 = (TLRPC.TL_users_userFull) r1;
        if (r12.users.isEmpty() == true) goto L8;
        r0.run(DataTypesUtils.ParsePersonalInfo(r12));
        return;
    L8:
        f$$ExternalSyntheticBUOutline1.m("got 0 users in requestPersonalInfo!");
        return;
    }

    /* renamed from: $r8$lambda$KA0OoXvqimBQvzduYChl-_gP1pE, reason: not valid java name */
    public static /* synthetic */ void m1331$r8$lambda$KA0OoXvqimBQvzduYChl_gP1pE(ExportRequestsController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$finishUserpicsSlice$26(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$OQnCOWeNM1FtYFVh19GYpW6hCxg(ExportRequestsController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$requestDialogsSlice$30(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$PMUx98NPmod1Jx4SEsfQdFvUrYg(ExportRequestsController r0, long r1, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$filePartExtractReference$17(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$R7VhIwrAe6QIqfwQQzTTweqLrjw(ExportRequestsController r0, ExportRequests$InitTakeoutSession r1, Runnable r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$startMainSession$1(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$RmDcIu5Y9nWiAA8yoTUNgaQB4S4(ExportRequestsController r0, String r1) {
        r0.loadStoryDone(r1);
    }

    public static /* synthetic */ void $r8$lambda$SMeWQmrzyB9lI6B_qwahDUpzfbE(ExportRequestsController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$requestChatMessages$28(r1, r2);
    }

    /* renamed from: $r8$lambda$SREEZqzwJ4MoWKy-Bx3_VH0rzK8, reason: not valid java name */
    public static /* synthetic */ void m1332$r8$lambda$SREEZqzwJ4MoWKyBx3_VH0rzK8(ExportRequestsController r0) {
        r0.sendNextStartRequest();
    }

    /* renamed from: $r8$lambda$TkGuLbsaDn8b936V3W-CMhDoMsg, reason: not valid java name */
    public static /* synthetic */ void m1333$r8$lambda$TkGuLbsaDn8b936V3WCMhDoMsg(ExportRequestsController r0, String r1) {
        r0.loadMessageThumbDone(r1);
    }

    public static /* synthetic */ void $r8$lambda$TnWOs8ffvTcLhZXfN80zlGQfsDI(Utilities.Callback r0, TLObject r1, TLRPC.TL_error r2) {
        r0.run(r1);
    }

    public static /* synthetic */ void $r8$lambda$UfhuqH_l5CtFSzIR8sl9OJ5ezus(ExportRequestsController r0, Runnable r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$resolveCustomEmoji$12(r1, r2, r3);
    }

    public static /* synthetic */ Boolean $r8$lambda$Ul5p9X8NXd3Xj84vZfdHBNhSDvY(ExportRequestsController r0, Integer r1) {
        return r0.lambda$requestLeftChannelsIfNeeded$38(r1);
    }

    public static /* synthetic */ void $r8$lambda$XrPfpc_RJenak5TbaQ_9CeaJe9k(ExportRequestsController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$requestSinglePeerMigrated$37(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$_ZwQ3_fpcGwOdofH7VmUFYU4nJ4(ExportRequestsController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$requestUserpics$19(r1, r2);
    }

    /* renamed from: $r8$lambda$aMFwMXFV9LggntIw_VlM7zp-R54, reason: not valid java name */
    public static /* synthetic */ void m1334$r8$lambda$aMFwMXFV9LggntIw_VlM7zpR54(ExportRequestsController r0, long r1, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$filePartRefreshReference$15(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$cYlt3Jf7y6kXnPX67VSYeoWQHU4(ExportRequestsController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$requestDialogsCount$46(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$ftF0eN_4HVvrb9PDV7d08zjFhJ4(ExportRequestsController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$requestSplitRanges$45(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$giVt_aRGKLwR0pAVsPIQfdBkDS8(ExportRequestsController r0, Integer r1, Utilities.Callback2 r2) {
        r0.lambda$requestContacts$23(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$grVlOxfUNIKamC6ha7oDM6_s4eE(ExportRequestsController r0, Utilities.Callback r1, TLRPC.InputUser r2) {
        r0.lambda$requestSinglePeerDialog$33(r1, r2);
    }

    /* renamed from: $r8$lambda$h0IfLK-89433VSjduuWYrZuKb3E, reason: not valid java name */
    public static /* synthetic */ Boolean m1335$r8$lambda$h0IfLK89433VSjduuWYrZuKb3E(ApiWrap$FileProgress r0) {
        return Boolean.TRUE;
    }

    public static /* synthetic */ void $r8$lambda$haiBbeWj4X7iaxRqXcYJfoDJWLk(ExportRequestsController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$finishStoriesSlice$21(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$jQFbE73K5oBnNHTAxsqcnr4bZRI(ExportRequestsController r0) {
        r0.lambda$requestLeftChannelsSlice$40();
    }

    public static /* synthetic */ void $r8$lambda$jypaiBxEJN8dF7GW6OYaBhGanW0(ExportRequestsController r0, String r1) {
        r0.loadStoryThumbDone(r1);
    }

    public static /* synthetic */ void $r8$lambda$klc9ccxrT__Ncry5OLACYl3lmp4(ExportRequestsController r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$requestTopPeersSlice$25(r1, r2);
    }

    /* renamed from: $r8$lambda$lA0eqqubdwI-mVIMiTJWatiC_mY, reason: not valid java name */
    public static /* synthetic */ void m1336$r8$lambda$lA0eqqubdwImVIMiTJWatiC_mY(ExportRequestsController r0, Integer r1, Utilities.Callback2 r2, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$requestContacts$22(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$lOBGtEyJh6s7f_9Pu7Uk6kCOIOQ(ExportRequestsController r0, TLRPC.messages_Messages r1) {
        r0.lambda$requestMessagesSlice$10(r1);
    }

    public static /* synthetic */ void $r8$lambda$lieJVRcLFqYCco5Sf5IimHzB39k(ExportRequestsController r0, long r1, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$filePartRefreshReference$14(r1, r3, r4);
    }

    public static /* synthetic */ Boolean $r8$lambda$lksv7EJyL8ab4PT2yMGeQ3T0SFw(ApiWrap$DialogInfo.Type r1) {
        if (r1 != ApiWrap$DialogInfo.Type.PrivateChannel) goto L5;
    L8:
        boolean r12 = true;
    L10:
        return Boolean.valueOf(r12);
    L5:
        if (r1 == ApiWrap$DialogInfo.Type.PublicChannel) goto L8;
        r12 = false;
        goto L10
    }

    public static /* synthetic */ void $r8$lambda$lmRX6fF1QzoG_gs8r_wrPo5AgA0(ExportRequestsController r0, long r1, Utilities.Callback2 r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$fileRequest$44(r1, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$o2ZPkRqssEeFkasBEDPQDV8xNf4(ExportRequestsController r0, long r1, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$filePartRefreshReference$16(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$sJdGEKfxEDxb8Kvb6Te0WKvfXd8(ExportRequestsController r0, String r1) {
        r0.loadMessageFileDone(r1);
    }

    public static /* synthetic */ void $r8$lambda$u5ZlOsOMxKIfjKUcocdojvJTbR4(ExportRequestsController r0, TLRPC.InputPeer r1, int r2, int r3, int r4, int r5, TLObject r6, TLRPC.TL_error r7) {
        r0.lambda$requestChatMessages$29(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$unLZUkQLOVLND4d4vfYJVHU9Wmc(ExportRequestsController r0, long r1, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$loadFilePart$13(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$vUfOsPa7opdqP50v7zpxnpF9QyY(ExportRequestsController r0, long r1, TLObject r3, TLRPC.TL_error r4) {
        r0.lambda$filePartExtractReference$18(r1, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$vkObqxU0pvSsBX3VHgHI3JaG6Os(Utilities.Callback2 r2, TLObject r3, TLRPC.TL_error r4) {
        ExportController.exportQueue.postRunnable(new ExportRequestsController$$ExternalSyntheticLambda29(r2, r3, r4));
    }

    public static /* synthetic */ void $r8$lambda$w5QCUScDJuCGB_rvoe7X9bpqBng(Utilities.Callback2 r0, TLObject r1, TLRPC.TL_error r2) {
        r0.run(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$ymgC3aArSIYYSHU7mR3s2OoGibw(ExportRequestsController r0, ApiWrap$FileProgress r1) {
        return r0.loadStoryProgress(r1);
    }

    public static /* synthetic */ void $r8$lambda$zUhnSPX6kGtTskTPxh0MMvePxT4(ApiWrap$SessionsList r0, Utilities.Callback r1, TLObject r2, TLRPC.TL_error r3) {
        r0.webList = DataTypesUtils.ParseWebSessionsList((TL_account.webAuthorizations) r2).webList;
        r1.run(r0);
    }

    static {
        Instance = new ExportRequestsController[16];
    }

    private ExportRequestsController(int r2) {
        this.index = 0;
        this.selectedAcc = r2;
        this._unresolvedCustomEmoji = new HashSet();
        this._resolvedCustomEmoji = new HashMap();
        this._fileCache = new ApiWrap$LoadedFileCache(AppDataDirGuesser.PER_USER_RANGE);
        this.splits = new ArrayList();
    }

    public static ApiWrap$DialogsInfo ParseDialogsInfo(TLRPC.InputPeer r8, TLRPC.messages_Chats r9) {
        if ((r8 instanceof TLRPC.TL_inputPeerChat) == false) goto L6;
        long r0 = ((TLRPC.TL_inputPeerChat) r8).chat_id;
    L8:
        ApiWrap$DialogsInfo r82 = new ApiWrap$DialogsInfo();
        ArrayList<TLRPC.Chat> r92 = r9.chats;
        int r2 = r92.size();
        int r4 = 0;
    L9:
        if (r4 >= r2) goto L21;
        TLRPC.Chat r5 = r92.get(r4);
        r4 = r4 + 1;
        TLRPC.Chat r52 = r5;
        if ((r52 instanceof TLRPC.TL_channel) == false) goto L14;
        long r6 = ((TLRPC.TL_channel) r52).id;
    L18:
        if (r6 != r0) goto L9;
        ApiWrap$DialogInfo r53 = DataTypesUtils.DialogInfoFromChat(DataTypesUtils.ParseChat(r52));
        r53.isLeftChannel = false;
        r82.chats.add(r53);
        goto L9
    L14:
        if ((r52 instanceof TLRPC.TL_channelForbidden) == false) goto L16;
        r6 = ((TLRPC.TL_channelForbidden) r52).id;
        goto L18
    L16:
        r6 = r52.id;
        goto L18
    L21:
        return r82;
    L6:
        if ((r8 instanceof TLRPC.TL_inputPeerChannel) == false) goto L22;
        r0 = ((TLRPC.TL_inputPeerChannel) r8).channel_id;
        goto L8
    L22:
        DexMaker$$ExternalSyntheticBUOutline0.m("illegal type: ", r8);
        return null;
    }

    public static ApiWrap$DialogsInfo ParseDialogsInfo(TLRPC.InputPeer r9, Vector r10) {
        if ((r9 instanceof TLRPC.TL_inputPeerUser) == false) goto L6;
        long r3 = ((TLRPC.TL_inputPeerUser) r9).user_id;
    L8:
        ApiWrap$DialogsInfo r92 = new ApiWrap$DialogsInfo();
        ArrayList<T> r102 = r10.objects;
        int r0 = r102.size();
        int r5 = 0;
    L9:
        if (r5 >= r0) goto L20;
        Object r6 = r102.get(r5);
        r5 = r5 + 1;
        if ((r6 instanceof TLRPC.User) == false) goto L9;
        TLRPC.User r62 = (TLRPC.User) r6;
        if (r62.id == r3) goto L19;
        if (r3 != 0) goto L9;
        if (r62.self == false) goto L9;
    L19:
        r92.chats.add(DataTypesUtils.DialogInfoFromUser(DataTypesUtils.ParseUser(r62)));
        goto L9
    L20:
        return r92;
    L6:
        if ((r9 instanceof TLRPC.TL_inputPeerSelf) == false) goto L21;
        r3 = 0;
        goto L8
    L21:
        DexMaker$$ExternalSyntheticBUOutline0.m("wtf is it: ", r9);
        return null;
    }

    private ApiWrap$DialogsInfo ParseDialogsInfo(TLRPC.messages_Dialogs r17) {
        ApiWrap$DialogsInfo r1 = new ApiWrap$DialogsInfo();
        if (r17 != null) goto L6;
    L50:
        return r1;
    L6:
        if ((r17 instanceof TLRPC.TL_messages_dialogsNotModified) == false) goto L8;
        return r1;
    L8:
        HashMap<Long, ApiWrap$Peer> r2 = DataTypesUtils.ParsePeersLists(r17.users, r17.chats);
        HashMap<String, ApiWrap$Message> r3 = ParseMessagesList(0, r17.messages, _UrlKt.FRAGMENT_ENCODE_SET);
        ArrayList<TLRPC.Dialog> r0 = r17.dialogs;
        int r7 = r0.size();
        int r9 = 0;
    L9:
        if (r9 >= r7) goto L50;
        TLRPC.Dialog r10 = r0.get(r9);
        r9 = r9 + 1;
        TLRPC.Dialog r102 = r10;
        ApiWrap$DialogInfo r11 = new ApiWrap$DialogInfo();
        long r12 = MessageObject.getPeerId(r102.peer);
        r11.peerId = r12;
        ApiWrap$Peer r122 = r2.get(Long.valueOf(r12));
        if (r122 == null) goto L46;
        ApiWrap$User r13 = r122.user;
        boolean r14 = true;
        if (r13 == null) goto L15;
        boolean r15 = true;
    L16:
        if (r15 == false) goto L18;
        ApiWrap$DialogInfo.Type r132 = DataTypesUtils.DialogTypeFromUser(r13);
    L19:
        r11.type = r132;
        if (r15 == false) goto L22;
        String r133 = r122.user.info.firstName;
    L23:
        r11.name = r133;
        if (r133 != null) goto L26;
        r11.name = "Deleted Account";
    L26:
        if (r15 == false) goto L30;
        String r134 = r122.user.info.lastName;
        if (r134 == null) goto L30;
    L31:
        r11.lastName = r134;
        r11.colorIndex = r122.colorIndex();
        r11.input = r122.getInput();
        if (r15 == true) goto L34;
        long r4 = r122.chat.migratedToChannelId;
    L35:
        r11.migratedToChannelId = r4;
        ApiWrap$Chat r42 = r122.chat;
        if (r42 != null) goto L38;
    L40:
        r14 = false;
    L41:
        r11.isMonoforum = r14;
        if (r42 == null) goto L44;
        TLRPC.InputPeer r43 = r42.monoforumBroadcastInput;
    L45:
        r11.monoforumBroadcastInput = r43;
        goto L46
    L44:
        r43 = new TLRPC.TL_inputPeerEmpty();
        goto L45
    L38:
        if (r42.isMonoforum == false) goto L40;
    L34:
        r4 = 0;
    L30:
        r134 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L31
    L22:
        r133 = r122.chat.title;
        goto L23
    L18:
        r132 = DataTypesUtils.DialogTypeFromChat(r122.chat);
        goto L19
    L15:
        r15 = false;
    L46:
        r11.topMessageId = r102.top_message;
        new ApiWrap$MessageId().didAndMsgId = r11.peerId + "_" + r11.topMessageId;
        ApiWrap$Message r44 = r3.get(r11.peerId + "_" + r11.topMessageId);
        if (r44 == null) goto L49;
        r11.topMessageDate = r44.date;
    L49:
        r1.chats.add(r11);
        goto L9
    }

    private HashMap<String, ApiWrap$Message> ParseMessagesList(long r8, ArrayList<TLRPC.Message> r10, String r11) {
        ApiWrap$ParseMediaContext r7 = new ApiWrap$ParseMediaContext();
        r7.selfPeerId = r8;
        LinkedHashMap r82 = new LinkedHashMap();
        int r9 = r10.size();
        int r0 = 0;
    L3:
        if (r0 >= r9) goto L5;
        TLRPC.Message r1 = r10.get(r0);
        r0 = r0 + 1;
        ApiWrap$Message r12 = DataTypesUtils.ParseMessage(r7, r1, r11);
        new ApiWrap$MessageId().didAndMsgId = r12.peerId + "_" + r12.id;
        r82.put(r12.peerId + "_" + r12.id, r12);
        goto L3
    L5:
        return r82;
    }

    private void appendChatsSlice(ApiWrap$ChatsProcess r11, ArrayList<ApiWrap$DialogInfo> r12, ArrayList<ApiWrap$DialogInfo> r13, int r14) {
        ArrayList r0 = new ArrayList();
        int r1 = r13.size();
        int r3 = 0;
    L4:
        if (r3 >= r1) goto L15;
        ApiWrap$DialogInfo r6 = r13.get(r3);
        r3 = r3 + 1;
        ApiWrap$DialogInfo r62 = r6;
        if (goodByTypes(r62) == true) goto L7;
        if (r62.migratedToChannelId == 0) goto L4;
        int r4 = this._settings.types;
        if ((r4 & 256) != 0) goto L14;
        if ((r4 & 128) == 0) goto L4;
    L14:
        r0.add(r62);
        goto L4
    L7:
        r0.add(r62);
        goto L4
    L15:
        r12.ensureCapacity(r12.size() + r13.size());
        int r132 = r0.size();
        int r15 = 0;
    L16:
        if (r15 >= r132) goto L32;
        Object r32 = r0.get(r15);
        r15 = r15 + 1;
        ApiWrap$DialogInfo r33 = (ApiWrap$DialogInfo) r32;
        int r63 = r12.size();
        long r7 = r33.migratedToChannelId;
        if (r7 == 0) goto L27;
        Integer r72 = r11.indexByPeer.get(Long.valueOf(r7));
        if (r72 == null) goto L25;
        if (DataTypesUtils.AddMigrateFromSlice(r12.get(r72.intValue()), r33, r14, this.splits.size()) == true) goto L16;
    L25:
        if (goodByTypes(r33) == false) goto L16;
    L27:
        Integer r73 = r11.indexByPeer.putIfAbsent(Long.valueOf(r33.peerId), Integer.valueOf(r63));
        if (r73 != null) goto L30;
        r12.add(r33);
    L31:
        r12.get(r63).splits.add(Integer.valueOf(r14));
        r12.get(r63).messagesCountPerSplit.add(0);
        goto L16
    L30:
        r63 = r73.intValue();
        goto L31
    }

    private void appendSinglePeerDialogs(ApiWrap$DialogsInfo r13) {
        ExportRequestsController$$ExternalSyntheticLambda47 r0 = new ExportRequestsController$$ExternalSyntheticLambda47();
        ExportRequestsController$$ExternalSyntheticLambda48 r1 = new ExportRequestsController$$ExternalSyntheticLambda48();
        int r2 = this._dialogsProcess.splitIndexPlusOne - 1;
        ArrayList<ApiWrap$DialogInfo> r3 = r13.chats;
        int r4 = r3.size();
        int r6 = 0;
        int r7 = 0;
    L3:
        if (r7 >= r4) goto L14;
        ApiWrap$DialogInfo r8 = r3.get(r7);
        r7 = r7 + 1;
        ApiWrap$DialogInfo r82 = r8;
        if (((Boolean) r0.run(r82.type)).booleanValue() == false) goto L9;
        if (r6 != 0) goto L9;
        r6 = requestSinglePeerMigrated(r82);
    L9:
        if (((Boolean) r1.run(r82.type)).booleanValue() == true) goto L3;
        int r9 = r2;
    L12:
        if (r9 == 0) goto L3;
        r82.splits.add(Integer.valueOf(r9 - 1));
        r82.messagesCountPerSplit.add(0);
        r9 = r9 - 1;
        goto L12
    L14:
        if (r6 != 0) goto L16;
        this._dialogsProcess.processedCount += r13.chats.size();
    L16:
        ApiWrap$DialogsProcess r02 = this._dialogsProcess;
        appendChatsSlice(r02, r02.info.chats, r13.chats, r02.splitIndexPlusOne - 1);
        if (r6 != 0) goto L32;
        ApiWrap$DialogsProcess r132 = this._dialogsProcess;
        if (r132.progress.run(Integer.valueOf(r132.processedCount)).booleanValue() == false) goto L33;
        finishDialogsList();
        return;
    L33:
        return;
    }

    private void checkFirstMessageDate(int r8, int r9) {
        if (this._settings.singlePeerTill > 0) goto L6;
        messagesCountLoaded(r8, r9);
        return;
    L6:
        requestChatMessages(this._chatProcess.info.splits.get(r8).intValue(), 1, -1, 1, new ExportRequestsController$$ExternalSyntheticLambda21(this, r8, r9));
    }

    private void collectMessagesCustomEmoji(ApiWrap$MessagesSlice r12) {
        ArrayList<ApiWrap$Message> r122 = r12.list;
        int r0 = r122.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L18;
        ApiWrap$Message r3 = r122.get(r2);
        r2 = r2 + 1;
        ApiWrap$Message r32 = r3;
        ArrayList<ApiWrap$TextPart> r4 = r32.text;
        int r5 = r4.size();
        int r6 = 0;
    L5:
        if (r6 >= r5) goto L13;
        ApiWrap$TextPart r7 = r4.get(r6);
        r6 = r6 + 1;
        ApiWrap$TextPart r72 = r7;
        if (r72.type != ApiWrap$TextPart.Type.CustomEmoji) goto L5;
        long r73 = Long.parseLong(r72.additional);
        if (r73 == 0) goto L5;
        if (this._resolvedCustomEmoji.containsKey(Long.valueOf(r73)) == true) goto L5;
        this._unresolvedCustomEmoji.add(Long.valueOf(r73));
        goto L5
    L13:
        Iterator<ApiWrap$Reaction> r33 = r32.reactions.iterator();
        if (r33.hasNext() == false) goto L3;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r33.next());
        throw null;
    }

    private ApiWrap$Message currentFileMessage() {
        ApiWrap$ChatProcess r1 = this._chatProcess;
        return r1.slice.list.get(r1.fileIndex);
    }

    private ApiWrap$FileOrigin currentFileMessageOrigin() {
        ApiWrap$ChatProcess r0 = this._chatProcess;
        Integer r02 = r0.info.splits.get(r0.localSplitIndex);
        if (r02.intValue() < 0) goto L6;
        int r1 = r02.intValue();
    L5:
        int r3 = r1;
        int r03 = r02.intValue();
        ApiWrap$ChatProcess r12 = this._chatProcess;
        if (r03 < 0) goto L11;
        TLRPC.InputPeer r04 = r12.info.input;
    L10:
        TLRPC.InputPeer r4 = r04;
        return new ApiWrap$FileOrigin(r3, r4, currentFileMessage().id, 0, 0);
    L11:
        r04 = r12.info.migratedFromInput;
        goto L10
    L6:
        r1 = this._chatProcess.info.splits.size() + r02.intValue();
        goto L5
    }

    private void filePartDone(long r7, TLObject r9) {
        if ((r9 instanceof TLRPC.TL_upload_fileCdnRedirect) == false) goto L5;
        f$$ExternalSyntheticBUOutline1.m("TL_upload_fileCdnRedirect received! not supported.");
        return;
    L5:
        if ((r9 instanceof TLRPC.TL_upload_file) == false) goto L48;
        TLRPC.TL_upload_file r92 = (TLRPC.TL_upload_file) r9;
        int r0 = r92.bytes.limit();
        ApiWrap$FileProcess r1 = this._fileProcess;
        if (r0 == 0) goto L9;
        Iterator<ApiWrap$FileProcess.Request> r02 = r1.requests.iterator();
    L19:
        if (r02.hasNext() == false) goto L23;
        ApiWrap$FileProcess.Request r12 = r02.next();
        if (r12.offset != r7) goto L19;
    L24:
        if (r12 == null) goto L46;
        r12.bytes = r92.bytes;
    L27:
        if (this._fileProcess.requests.isEmpty() == true) goto L35;
        if (this._fileProcess.requests.getFirst().bytes.limit() == 0) goto L35;
        NativeByteBuffer r72 = this._fileProcess.requests.getFirst().bytes;
        if (this._fileProcess.file.writeBlock(r72).isSuccess() == false) goto L33;
        this._fileProcess.requests.removeFirst();
        goto L27
    L33:
        GlShader$$ExternalSyntheticBUOutline2.m("wtf! tried to write: ", r72.buffer.get());
        return;
    L35:
        ApiWrap$FileProcess r73 = this._fileProcess;
        Utilities.CallbackReturn<ApiWrap$FileProgress, Boolean> r8 = r73.progress;
        if (r8 == null) goto L39;
        r8.run(new ApiWrap$FileProgress(r73.file.size(), this._fileProcess.size));
    L39:
        if (this._fileProcess.requests.isEmpty() == false) goto L44;
        ApiWrap$FileProcess r74 = this._fileProcess;
        long r82 = r74.size;
        if (r82 == 0) goto L44;
        if (r82 <= r74.offset) goto L48;
    L44:
        loadFilePart();
        return;
    L46:
        Segment$$ExternalSyntheticBUOutline1.m("req not found!");
        return;
    L23:
        r12 = null;
        goto L24
    L9:
        if (r1.size <= 0) goto L11;
        Segment$$ExternalSyntheticBUOutline1.m("received data has 0 length and fileProcess size is not 0!!!");
        return;
    L11:
        if (r1.file.writeBlock(_UrlKt.FRAGMENT_ENCODE_SET).isSuccess() == true) goto L48;
        Segment$$ExternalSyntheticBUOutline1.m("writing empty block was not successful!");
        return;
    L48:
        ApiWrap$FileProcess r75 = this._fileProcess;
        String r83 = r75.relativePath;
        this._fileCache.save(r75.location, r83);
        r75.done.run(r75.relativePath);
    }

    private void filePartExtractReference(long r6, TLRPC.messages_Messages r8) {
        if ((r8 instanceof TLRPC.TL_messages_messagesNotModified) == true) goto L15;
        ApiWrap$ParseMediaContext r0 = new ApiWrap$ParseMediaContext();
        r0.selfPeerId = this._selfId;
        ArrayList<ApiWrap$Message> r82 = DataTypesUtils.ParseMessagesSlice(r0, r8.messages, r8.users, r8.chats, this._chatProcess.info.relativePath).list;
        int r02 = r82.size();
        int r1 = 0;
    L5:
        if (r1 >= r02) goto L13;
        ApiWrap$Message r2 = r82.get(r1);
        r1 = r1 + 1;
        ApiWrap$Message r22 = r2;
        if (r22.id != this._fileProcess.origin.messageId()) goto L5;
        boolean r3 = DataTypesUtils.RefreshFileReference(this._fileProcess.location.data, r22.getFile().location.data);
        boolean r23 = DataTypesUtils.RefreshFileReference(this._fileProcess.location.data, r22.media.getThumb().file.location.data);
        if (r3 == true) goto L11;
        if (r23 == false) goto L5;
    L11:
        this._fileProcess.requestId = fileRequest(r8.location, r6, new ExportRequestsController$$ExternalSyntheticLambda3(this, r6));
        return;
    L13:
        FileLog.w("Export Error: File unavailable.");
        this._fileProcess.done.run(_UrlKt.FRAGMENT_ENCODE_SET);
        return;
    L15:
        Segment$$ExternalSyntheticBUOutline1.m("wtf, TL_messages_messagesNotModified received!");
    }

    private void filePartExtractReference(long r6, TL_stories.TL_stories_stories r8) {
        int r0 = 0;
        ArrayList<ApiWrap$Story> r82 = DataTypesUtils.ParseStoriesSlice(r8.stories, 0).list;
        int r1 = r82.size();
    L3:
        if (r0 >= r1) goto L11;
        ApiWrap$Story r2 = r82.get(r0);
        r0 = r0 + 1;
        ApiWrap$Story r22 = r2;
        if (r22.id != this._fileProcess.origin.storyId()) goto L3;
        boolean r3 = DataTypesUtils.RefreshFileReference(this._fileProcess.location.data, r22.file().location.data);
        boolean r23 = DataTypesUtils.RefreshFileReference(this._fileProcess.location.data, r22.thumb().file.location.data);
        if (r3 == true) goto L9;
        if (r23 == false) goto L3;
    L9:
        this._fileProcess.requestId = fileRequest(r8.location, r6, new ExportRequestsController$$ExternalSyntheticLambda0(this, r6));
        return;
    L11:
        this._fileProcess.done.run(_UrlKt.FRAGMENT_ENCODE_SET);
    }

    private void filePartRefreshReference(long r6) {
        ApiWrap$FileOrigin r0 = this._fileProcess.origin;
        if (r0.storyId() == 0) goto L6;
        TL_stories.TL_stories_getStoriesByID r1 = new TL_stories.TL_stories_getStoriesByID();
        r1.peer = new TLRPC.TL_inputPeerSelf();
        r1.id = new ArrayList(Collections.singletonList(Integer.valueOf(r0.storyId())));
        this._fileProcess.requestId = mainRequest(r1, new ExportRequestsController$$ExternalSyntheticLambda53(this, r6));
    L10:
        if ((r0.peer() instanceof TLRPC.TL_inputPeerChannel) == false) goto L12;
    L16:
        TLRPC.TL_channels_getMessages r12 = new TLRPC.TL_channels_getMessages();
        r12.id = new ArrayList(Collections.singletonList(Integer.valueOf(r0.messageId())));
        if ((r0.peer() instanceof TLRPC.TL_inputPeerChannel) == false) goto L19;
        TLRPC.TL_inputChannel r2 = new TLRPC.TL_inputChannel();
        r2.channel_id = r0.peer().channel_id;
        r2.access_hash = r0.peer().access_hash;
        r12.channel = r2;
    L20:
        this._fileProcess.requestId = mainRequest(r12, new ExportRequestsController$$ExternalSyntheticLambda54(this, r6));
        return;
    L19:
        TLRPC.TL_inputChannelFromMessage r22 = new TLRPC.TL_inputChannelFromMessage();
        r22.peer = r0.peer().peer;
        r22.access_hash = r0.peer().access_hash;
        r22.channel_id = r0.peer().channel_id;
        r12.channel = r22;
        goto L20
    L12:
        if ((r0.peer() instanceof TLRPC.TL_inputPeerChannelFromMessage) == true) goto L16;
        new TLRPC.TL_messages_getMessages().id = new ArrayList(Collections.singletonList(Integer.valueOf(r0.messageId())));
        this._fileProcess.requestId = splitRequest(r0.split(), r1, new ExportRequestsController$$ExternalSyntheticLambda55(this, r6));
        return;
    L6:
        if (r0.messageId() != 0) goto L10;
        Log.e("exteraGram", "FILE_REFERENCE error for non-message file.");
    }

    private int fileRequest(ApiWrap$FileLocation r12, long r13, Utilities.Callback2<TLObject, TLRPC.TL_error> r15) {
        TLRPC.TL_upload_getFile r0 = new TLRPC.TL_upload_getFile();
        r0.location = r12.data;
        r0.offset = r13;
        r0.limit = 1048576;
        r0.cdn_supported = false;
        r0.precise = false;
        r0.flags = 0;
        ExportRequests$InvokeWithTakeoutWrapper r3 = new ExportRequests$InvokeWithTakeoutWrapper();
        r3.query = r0;
        r3.takeout_id = this._takeoutId;
        return ConnectionsManager.getInstance(this.selectedAcc).sendRequestSync(r3, new ExportRequestsController$$ExternalSyntheticLambda43(this, r13, r15), null, null, 0, r12.dcId, 65538, true);
    }

    private void finishDialogsList() {
        DataTypesUtils.FinalizeDialogsInfo(this._dialogsProcess.info, this._settings);
        ApiWrap$DialogsProcess r2 = this._dialogsProcess;
        r2.done.run(r2.info);
    }

    private void finishMessages() {
        this._chatProcess.done.run();
    }

    private void finishMessagesSlice() {
        ApiWrap$MessagesSlice r0 = this._chatProcess.slice;
        if (r0.list.isEmpty() == true) goto L10;
        ApiWrap$ChatProcess r1 = this._chatProcess;
        ArrayList<ApiWrap$Message> r3 = r0.list;
        r1.largestIdPlusOne = r3.get(r3.size() - 1).id + 1;
        ApiWrap$ChatProcess r12 = this._chatProcess;
        if (r12.info.splits.get(r12.localSplitIndex).intValue() >= 0) goto L8;
        r0 = DataTypesUtils.AdjustMigrateMessageIds(r0);
    L8:
        if (this._chatProcess.handleSlice.run(r0).booleanValue() == true) goto L10;
        return;
    L10:
        ApiWrap$ChatProcess r02 = this._chatProcess;
        if (r02.lastSlice == false) goto L16;
        int r13 = r02.localSplitIndex + 1;
        r02.localSplitIndex = r13;
        if (r13 >= r02.info.splits.size()) goto L16;
        ApiWrap$ChatProcess r03 = this._chatProcess;
        r03.lastSlice = false;
        r03.largestIdPlusOne = 1;
    L16:
        if (this._chatProcess.lastSlice == true) goto L19;
        requestMessagesSlice();
        return;
    L19:
        finishMessages();
    }

    private void finishStoriesSlice() {
        ApiWrap$StoriesProcess r0 = this._storiesProcess;
        ApiWrap$StoriesSlice r1 = r0.slice;
        if (r1.lastId == 0) goto L7;
        r0.processed += r1.list.size();
        ApiWrap$StoriesProcess r02 = this._storiesProcess;
        r02.offsetId = r1.lastId;
        if (r02.handleSlice.run(r1).booleanValue() == true) goto L7;
        return;
    L7:
        ApiWrap$StoriesProcess r03 = this._storiesProcess;
        if (r03.lastSlice == false) goto L11;
        r03.finish.run();
        return;
    L11:
        TL_stories.TL_stories_getStoriesArchive r04 = new TL_stories.TL_stories_getStoriesArchive();
        r04.peer = new TLRPC.TL_inputPeerSelf();
        r04.limit = 100;
        r04.offset_id = this._storiesProcess.offsetId;
        mainRequest(r04, new ExportRequestsController$$ExternalSyntheticLambda40(this));
    }

    private void finishUserpicsSlice() {
        ArrayList<HtmlWriter.Photo> r0 = this._userpicsProcess.slice;
        if (r0.isEmpty() == true) goto L7;
        this._userpicsProcess.processed += r0.size();
        this._userpicsProcess.maxId = r0.get(r0.size() - 1).id;
        if (this._userpicsProcess.handleSlice.run(r0).booleanValue() == true) goto L7;
        return;
    L7:
        ApiWrap$UserpicsProcess r02 = this._userpicsProcess;
        if (r02.lastSlice == false) goto L11;
        r02.finish.run();
        return;
    L11:
        TLRPC.TL_photos_getUserPhotos r03 = new TLRPC.TL_photos_getUserPhotos();
        r03.user_id = new TLRPC.TL_inputUserSelf();
        r03.offset = 0;
        r03.max_id = this._userpicsProcess.maxId;
        r03.limit = 100;
        mainRequest(r03, new ExportRequestsController$$ExternalSyntheticLambda46(this));
    }

    private String getCustomEmoji(String r10) {
        Long r0 = Utilities.parseLong(r10);
        if (r0.longValue() != 0) goto L5;
        return r10;
    L5:
        ApiWrap$Document r102 = this._resolvedCustomEmoji.get(r0);
        if (r102 == null) goto L8;
        ApiWrap$File r1 = r102.file;
        ApiWrap$FileOrigin r2 = new ApiWrap$FileOrigin(0, null, 0, 0, r0.longValue());
        long r3 = r0.longValue();
        if (processFileLoad(r1, r2, new ExportRequestsController$$ExternalSyntheticLambda1(this), new ExportRequestsController$$ExternalSyntheticLambda2(this, r3), null, null) == true) goto L13;
        return null;
    L13:
        ApiWrap$File.SkipReason r9 = r1.skipReason;
        if (r9 != ApiWrap$File.SkipReason.Unavailable) goto L18;
        return ApiWrap$TextPart.UnavailableEmoji();
    L18:
        if (r9 != ApiWrap$File.SkipReason.FileType) goto L20;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L20:
        if (r9 != ApiWrap$File.SkipReason.FileSize) goto L23;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L23:
        return r1.relativePath;
    L8:
        return ApiWrap$TextPart.UnavailableEmoji();
    }

    public static ExportRequestsController getInstance(int r3) {
        ExportRequestsController[] r0 = Instance;
        ExportRequestsController r1 = r0[r3];
        if (r1 == null) goto L5;
        return r1;
    L5:
        monitor-enter(ExportRequestsController.class);
        ExportRequestsController r12 = r0[r3];     // Catch: Throwable -> L9
        if (r12 != null) goto L11;
        r12 = new ExportRequestsController(r3);     // Catch: Throwable -> L9
        r0[r3] = r12;     // Catch: Throwable -> L9
    L11:
        monitor-exit(ExportRequestsController.class);     // Catch: Throwable -> L9
        return r12;
    L9:
        th = move-exception;
        throw th;
    }

    private boolean goodByTypes(ApiWrap$DialogInfo r1) {
        if ((this._settings.types & DataTypesUtils.SettingsFromDialogsType(r1.type)) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    private void handleUserpicsSlice(TLRPC.photos_Photos r3) {
        if ((r3 instanceof TLRPC.TL_photos_photos) == false) goto L5;
        this._userpicsProcess.lastSlice = true;
    L5:
        loadUserpicsFiles(DataTypesUtils.ParseUserpicsSlice(r3.photos, this._userpicsProcess.processed));
    }

    private /* synthetic */ void lambda$checkFirstMessageDate$9(int r2, int r3, TLRPC.messages_Messages r4) {
        if (DataTypesUtils.SingleMessageBefore(r4, this._settings.singlePeerTill) == true) goto L5;
        r3 = 0;
    L5:
        messagesCountLoaded(r2, r3);
    }

    private /* synthetic */ void lambda$filePartExtractReference$17(long r3, TLObject r5, TLRPC.TL_error r6) {
        if ((r5 instanceof TLRPC.TL_upload_file) == false) goto L6;
        this._fileProcess.requestId = 0;
        filePartDone(r3, (TLRPC.TL_upload_file) r5);
        return;
    }

    private /* synthetic */ void lambda$filePartExtractReference$18(long r3, TLObject r5, TLRPC.TL_error r6) {
        this._fileProcess.requestId = 0;
        filePartDone(r3, r5);
    }

    private /* synthetic */ void lambda$filePartRefreshReference$14(long r3, TLObject r5, TLRPC.TL_error r6) {
        if (r6 == null) goto L7;
        ApiWrap$FileProcess r2 = this._fileProcess;
        r2.requestId = 0;
        r2.done.run(_UrlKt.FRAGMENT_ENCODE_SET);
        return;
    L7:
        if ((r5 instanceof TL_stories.TL_stories_stories) == false) goto L10;
        this._fileProcess.requestId = 0;
        filePartExtractReference(r3, (TL_stories.TL_stories_stories) r5);
        return;
    }

    private /* synthetic */ void lambda$filePartRefreshReference$15(long r3, TLObject r5, TLRPC.TL_error r6) {
        if (r6 == null) goto L7;
        this._fileProcess.requestId = 0;
        FileLog.w("Export Error: File unavailable.");
        this._fileProcess.done.run(_UrlKt.FRAGMENT_ENCODE_SET);
        return;
    L7:
        if ((r5 instanceof TLRPC.TL_messages_messages) == false) goto L10;
        this._fileProcess.requestId = 0;
        filePartExtractReference(r3, (TLRPC.TL_messages_messages) r5);
        return;
    }

    private /* synthetic */ void lambda$filePartRefreshReference$16(long r3, TLObject r5, TLRPC.TL_error r6) {
        if (r6 == null) goto L7;
        this._fileProcess.requestId = 0;
        Log.w("exteraGram", "Export Error: File unavailable.");
        this._fileProcess.done.run(_UrlKt.FRAGMENT_ENCODE_SET);
        return;
    L7:
        if ((r5 instanceof TLRPC.messages_Messages) == false) goto L10;
        this._fileProcess.requestId = 0;
        filePartExtractReference(r3, (TLRPC.messages_Messages) r5);
        return;
    }

    private /* synthetic */ void lambda$fileRequest$44(long r5, Utilities.Callback2 r7, TLObject r8, TLRPC.TL_error r9) {
        if (r9 == null) goto L23;
        this._fileProcess.requestId = 0;
        if (Objects.equals(r9.text, "TAKEOUT_FILE_EMPTY") == false) goto L9;
        if (this._otherDataProcess == null) goto L9;
        TLRPC.TL_upload_file r52 = new TLRPC.TL_upload_file();
        r52.type = new TLRPC.TL_storage_filePartial();
        filePartDone(0, r52);
    L9:
        if (Objects.equals(r9.text, "LOCATION_INVALID") == false) goto L11;
    L22:
        Log.w("exteraGram", "Export Error: File unavailable.");
        this._fileProcess.done.run(_UrlKt.FRAGMENT_ENCODE_SET);
        goto L23
    L11:
        if (Objects.equals(r9.text, "VERSION_INVALID") == true) goto L22;
        if (Objects.equals(r9.text, "LOCATION_NOT_AVAILABLE") == true) goto L22;
        if (r9.code == 400) goto L18;
    L20:
        StringBuilder r4 = new StringBuilder("wtf! fileRequest, response: ");
        r4.append(r8);
        OkHttpClient$Builder$$ExternalSyntheticBUOutline0.m(r4, " error: ", r9.text);
        return;
    L18:
        if (r9.text.startsWith("FILE_REFERENCE") == false) goto L20;
        filePartRefreshReference(r5);
    L23:
        r7.run(r8, r9);
    }

    private /* synthetic */ void lambda$finishStoriesSlice$21(TLObject r1, TLRPC.TL_error r2) {
        if ((r1 instanceof TL_stories.TL_stories_stories) == false) goto L6;
        loadStoriesFiles(DataTypesUtils.ParseStoriesSlice(((TL_stories.TL_stories_stories) r1).stories, this._storiesProcess.processed));
        return;
    }

    private /* synthetic */ void lambda$finishUserpicsSlice$26(TLObject r1, TLRPC.TL_error r2) {
        if ((r1 instanceof TLRPC.photos_Photos) == false) goto L6;
        handleUserpicsSlice((TLRPC.photos_Photos) r1);
        return;
    }

    private /* synthetic */ void lambda$getCustomEmoji$27(long r1, String r3) {
        loadMessageEmojiDone(r1, r3);
    }

    private /* synthetic */ void lambda$loadFilePart$13(long r3, TLObject r5, TLRPC.TL_error r6) {
        this._fileProcess.requestId = 0;
        filePartDone(r3, r5);
    }

    private /* synthetic */ void lambda$requestChatMessages$28(TLObject r1, TLRPC.TL_error r2) {
        this._chatProcess.requestDone.run((TLRPC.messages_Messages) r1);
    }

    private /* synthetic */ void lambda$requestChatMessages$29(TLRPC.InputPeer r7, int r8, int r9, int r10, int r11, TLObject r12, TLRPC.TL_error r13) {
        if (r13 != null) goto L4;
        this._chatProcess.requestDone.run((TLRPC.messages_Messages) r12);
        return;
    L4:
        if (r13.text.equals("CHANNEL_PRIVATE") == false) goto L13;
        Log.d("exteraGram", "caught channel private");
        if ((r7 instanceof TLRPC.TL_inputPeerChannel) == false) goto L14;
        ApiWrap$ChatProcess r72 = this._chatProcess;
        ApiWrap$DialogInfo r122 = r72.info;
        if (r122.onlyMyMessages == true) goto L15;
        r122.onlyMyMessages = true;
        requestChatMessages(r8, r9, r10, r11, r72.requestDone);
        return;
    L15:
        return;
    L14:
        return;
    }

    private /* synthetic */ void lambda$requestContacts$22(Integer r3, Utilities.Callback2 r4, TLObject r5, TLRPC.TL_error r6) {
        if ((r5 instanceof TLRPC.TL_contacts_resolvedPeer) == false) goto L10;
        ApiWrap$ContactInfo r62 = this._contactsProcess.result.list.get(r3.intValue());
        TLRPC.Peer r52 = ((TLRPC.TL_contacts_resolvedPeer) r5).peer;
        if ((r52 instanceof TLRPC.TL_peerUser) == false) goto L7;
        r62.userId = Long.valueOf(((TLRPC.TL_peerUser) r52).user_id);
    L8:
        this._contactsProcess.result.list.set(r3.intValue(), r62);
        r4.run(Integer.valueOf(r3.intValue() + 1), r4);
        return;
    L7:
        r62.userId = 0L;
        goto L8
    L10:
        if (r6 == null) goto L13;
        r4.run(Integer.valueOf(r3.intValue() + 1), r4);
        return;
    }

    private /* synthetic */ void lambda$requestContacts$23(Integer r4, Utilities.Callback2 r5) {
        if (r4.intValue() != this._contactsProcess.result.list.size()) goto L6;
        requestTopPeersSlice();
        return;
    L6:
        TLRPC.TL_contacts_resolvePhone r0 = new TLRPC.TL_contacts_resolvePhone();
        r0.phone = this._contactsProcess.result.list.get(r4.intValue()).phoneNumber;
        mainRequest(r0, new ExportRequestsController$$ExternalSyntheticLambda42(this, r4, r5));
    }

    private /* synthetic */ void lambda$requestContacts$24(TLObject r1, TLRPC.TL_error r2) {
        if ((r1 instanceof Vector) == false) goto L6;
        ApiWrap$ContactsProcess r22 = this._contactsProcess;
        r22.result = DataTypesUtils.ParseContactsList((Vector) r1);
        new ExportRequestsController$$ExternalSyntheticLambda16(this);
        requestTopPeersSlice();
        return;
    }

    private /* synthetic */ void lambda$requestDialogsCount$46(TLObject r3, TLRPC.TL_error r4) {
        if ((r3 instanceof TLRPC.TL_messages_dialogs) == false) goto L6;
        int r32 = ((TLRPC.TL_messages_dialogs) r3).dialogs.size();
    L9:
        if (r32 < 0) goto L16;
        StartProcess r42 = this._startProcess;
        r42.info.dialogsCount += r32;
        int r33 = r42.splitIndex + 1;
        r42.splitIndex = r33;
        if (r33 < this.splits.size()) goto L14;
        sendNextStartRequest();
        return;
    L14:
        requestDialogsCount();
        return;
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("unexpected TL_messages_dialogsNotModified received");
        return;
    L6:
        if ((r3 instanceof TLRPC.TL_messages_dialogsSlice) == false) goto L8;
        r32 = ((TLRPC.TL_messages_dialogsSlice) r3).count;
        goto L9
    L8:
        r32 = -1;
        goto L9
    }

    private /* synthetic */ void lambda$requestDialogsSlice$30(TLObject r7, TLRPC.TL_error r8) {
        if ((r7 instanceof TLRPC.TL_messages_dialogsNotModified) == false) goto L6;
        return;
    L6:
        if ((r7 instanceof TLRPC.TL_messages_dialogs) == false) goto L9;
    L7:
        boolean r82 = true;
    L14:
        ApiWrap$DialogsInfo r72 = ParseDialogsInfo((TLRPC.messages_Dialogs) r7);
        this._dialogsProcess.processedCount += r72.chats.size();
        if (r72.chats.isEmpty() == false) goto L17;
        ApiWrap$DialogInfo r2 = new ApiWrap$DialogInfo();
    L18:
        ApiWrap$DialogsProcess r3 = this._dialogsProcess;
        if (r3.info != null) goto L21;
        r3.info = new ApiWrap$DialogsInfo();
    L21:
        ApiWrap$DialogsProcess r32 = this._dialogsProcess;
        appendChatsSlice(r32, r32.info.chats, r72.chats, r32.splitIndexPlusOne - 1);
        if (r82 == true) goto L27;
        int r73 = r2.topMessageDate;
        if (r73 <= 0) goto L27;
        ApiWrap$DialogsProcess r83 = this._dialogsProcess;
        r83.offsetId = r2.topMessageId;
        r83.offsetDate = r73;
        r83.offsetPeer = r2.input;
    L31:
        requestDialogsSlice();
        return;
    L27:
        if (useOnlyLastSplit() == true) goto L33;
        ApiWrap$DialogsProcess r74 = this._dialogsProcess;
        int r84 = r74.splitIndexPlusOne - 1;
        r74.splitIndexPlusOne = r84;
        if (r84 <= 0) goto L33;
        r74.offsetId = 0;
        r74.offsetDate = 0;
        r74.offsetPeer = new TLRPC.TL_inputPeerEmpty();
    L33:
        requestLeftChannelsIfNeeded();
        return;
    L17:
        ArrayList<ApiWrap$DialogInfo> r22 = r72.chats;
        r2 = r22.get(r22.size() - 1);
        goto L18
    L9:
        if ((r7 instanceof TLRPC.TL_messages_dialogsSlice) == true) goto L11;
    L13:
        r82 = false;
        goto L14
    L11:
        if (((TLRPC.TL_messages_dialogsSlice) r7).dialogs.size() >= 100) goto L13;
        goto L13
    }

    private /* synthetic */ void lambda$requestLeftChannelsCount$6() {
        this._startProcess.info.dialogsCount += this._leftChannelsProcess.fullCount;
        sendNextStartRequest();
    }

    private /* synthetic */ Boolean lambda$requestLeftChannelsIfNeeded$38(Integer r2) {
        ApiWrap$DialogsProcess r1 = this._dialogsProcess;
        return r1.progress.run(Integer.valueOf(r1.processedCount + r2.intValue()));
    }

    private /* synthetic */ void lambda$requestLeftChannelsIfNeeded$39(ApiWrap$DialogsInfo r2) {
        ApiWrap$DialogsInfo r0 = this._dialogsProcess.info;
        r0.left = r2.left;
        finishDialogsList();
    }

    private /* synthetic */ void lambda$requestLeftChannelsSlice$40() {
        ApiWrap$LeftChannelsProcess r0 = this._leftChannelsProcess;
        if (r0.finished == false) goto L6;
        r0.done.run(r0.info);
        return;
    L6:
        requestLeftChannelsSlice();
    }

    private /* synthetic */ void lambda$requestLeftChannelsSliceGeneric$7(Runnable r5, TLObject r6, TLRPC.TL_error r7) {
        if ((r6 instanceof TLRPC.messages_Chats) == false) goto L18;
        TLRPC.messages_Chats r62 = (TLRPC.messages_Chats) r6;
        ApiWrap$LeftChannelsProcess r72 = this._leftChannelsProcess;
        boolean r3 = true;
        appendChatsSlice(r72, r72.info.left, DataTypesUtils.ParseLeftChannelsInfo(r62).left, this.splits.size() - 1);
        ApiWrap$LeftChannelsProcess r4 = this._leftChannelsProcess;
        r4.offset += r62.chats.size();
        if ((r62 instanceof TLRPC.TL_messages_chats) == false) goto L8;
        int r63 = ((TLRPC.TL_messages_chats) r62).chats.size();
    L11:
        r4.fullCount = r63;
        r4.finished = r3;
        Utilities.CallbackReturn<Integer, Boolean> r64 = r4.progress;
        if (r64 != null) goto L14;
    L16:
        r5.run();
        return;
    L14:
        if (r64.run(Integer.valueOf(r4.info.left.size())).booleanValue() == true) goto L16;
        return;
    L8:
        if ((r62 instanceof TLRPC.TL_messages_chatsSlice) == false) goto L10;
        TLRPC.TL_messages_chatsSlice r65 = (TLRPC.TL_messages_chatsSlice) r62;
        int r73 = r65.count;
        r3 = r65.chats.isEmpty();
        r63 = r73;
        goto L11
    L10:
        r3 = false;
        r63 = 0;
        goto L11
    }

    private /* synthetic */ void lambda$requestMessagesCount$8(int r4, TLRPC.messages_Messages r5) {
        if ((r5 instanceof TLRPC.TL_messages_messages) == false) goto L6;
        int r0 = ((TLRPC.TL_messages_messages) r5).messages.size();
    L15:
        if (r0 >= 0) goto L17;
        Segment$$ExternalSyntheticBUOutline1.m("Unexpected messagesNotModified received");
        return;
    L17:
        if (DataTypesUtils.SingleMessageAfter(r5, this._settings.singlePeerFrom) == true) goto L20;
        messagesCountLoaded(r4, 0);
        return;
    L20:
        checkFirstMessageDate(r4, r0);
        return;
    L6:
        if ((r5 instanceof TLRPC.TL_messages_messagesSlice) == false) goto L9;
        r0 = ((TLRPC.TL_messages_messagesSlice) r5).count;
        goto L15
    L9:
        if ((r5 instanceof TLRPC.TL_messages_channelMessages) == false) goto L12;
        r0 = ((TLRPC.TL_messages_channelMessages) r5).count;
        goto L15
    L12:
        if ((r5 instanceof TLRPC.TL_messages_messagesNotModified) == false) goto L14;
        r0 = -1;
        goto L15
    L14:
        r0 = 0;
        goto L15
    }

    private /* synthetic */ void lambda$requestMessagesSlice$10(TLRPC.messages_Messages r5) {
        if ((r5 instanceof TLRPC.TL_messages_messagesNotModified) == false) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Unexpected messagesNotModified received.");
        return;
    L5:
        if ((r5 instanceof TLRPC.TL_messages_messages) == false) goto L7;
        this._chatProcess.lastSlice = true;
    L7:
        ApiWrap$ChatProcess r0 = this._chatProcess;
        loadMessagesFiles(DataTypesUtils.ParseMessagesSlice(r0.context, r5.messages, r5.users, r5.chats, r0.info.relativePath));
    }

    private /* synthetic */ void lambda$requestSessions$5(Utilities.Callback r2, TLObject r3, TLRPC.TL_error r4) {
        if ((r3 instanceof TL_account.authorizations) == false) goto L6;
        ApiWrap$SessionsList r32 = DataTypesUtils.ParseSessionsList((TL_account.authorizations) r3);
        mainRequest(new TL_account.getWebAuthorizations(), new ExportRequestsController$$ExternalSyntheticLambda28(r32, r2));
        return;
    }

    private /* synthetic */ void lambda$requestSinglePeerDialog$31(TLObject r2) {
        if ((r2 instanceof Vector) == false) goto L7;
        TLRPC.InputPeer r0 = this._settings.singlePeer;
        appendSinglePeerDialogs(ParseDialogsInfo(r0, (Vector) r2));
        return;
    L7:
        if ((r2 instanceof TLRPC.messages_Chats) == false) goto L10;
        TLRPC.InputPeer r02 = this._settings.singlePeer;
        appendSinglePeerDialogs(ParseDialogsInfo(r02, (TLRPC.messages_Chats) r2));
        return;
    }

    private /* synthetic */ void lambda$requestSinglePeerDialog$33(Utilities.Callback r3, TLRPC.InputUser r4) {
        TLRPC.TL_users_getUsers r0 = new TLRPC.TL_users_getUsers();
        r0.id = new ArrayList(ImageCapture$$ExternalSyntheticBackport0.m(r4));
        mainRequest(r0, new ExportRequestsController$$ExternalSyntheticLambda51(r3));
    }

    private /* synthetic */ void lambda$requestSinglePeerMigrated$37(TLObject r6, TLRPC.TL_error r7) {
        if ((r6 instanceof TLRPC.TL_messages_chatFull) == false) goto L14;
        TLRPC.TL_messages_chatFull r62 = (TLRPC.TL_messages_chatFull) r6;
        TLRPC.ChatFull r72 = r62.full_chat;
        if ((r72 instanceof TLRPC.TL_channelFull) == false) goto L7;
        long r3 = ((TLRPC.TL_channelFull) r72).migrated_from_chat_id;
    L9:
        if (r3 == 0) goto L12;
        TLRPC.TL_inputPeerChat r73 = new TLRPC.TL_inputPeerChat();
        r73.chat_id = r3;
        TLRPC.TL_messages_chats r0 = new TLRPC.TL_messages_chats();
        r0.chats = r62.chats;
        appendSinglePeerDialogs(ParseDialogsInfo(r73, r0));
        return;
    L12:
        appendSinglePeerDialogs(new ApiWrap$DialogsInfo());
        return;
    L7:
        r3 = 0;
        goto L9
    }

    private /* synthetic */ void lambda$requestSplitRanges$45(TLObject r1, TLRPC.TL_error r2) {
        vectorToRanges(r1, this.splits);
        if (useOnlyLastSplit() == false) goto L5;
        int r12 = this.splits.size() - 1;
    L6:
        this.index = r12;
        sendNextStartRequest();
        return;
    L5:
        r12 = 0;
        goto L6
    }

    private /* synthetic */ void lambda$requestStories$20(TLObject r2, TLRPC.TL_error r3) {
        if ((r2 instanceof TL_stories.TL_stories_stories) == false) goto L9;
        TL_stories.TL_stories_stories r22 = (TL_stories.TL_stories_stories) r2;
        if (this._storiesProcess.start.run(Integer.valueOf(r22.count)).booleanValue() == false) goto L10;
        loadStoriesFiles(DataTypesUtils.ParseStoriesSlice(r22.stories, this._storiesProcess.processed));
        return;
    L10:
        return;
    }

    private /* synthetic */ void lambda$requestStoriesCount$3(TLObject r1, TLRPC.TL_error r2) {
        if ((r1 instanceof TL_stories.TL_stories_stories) == false) goto L5;
        StartInfo r22 = this._startProcess.info;
        r22.storiesCount = ((TL_stories.TL_stories_stories) r1).count;
    L5:
        sendNextStartRequest();
    }

    private /* synthetic */ void lambda$requestTopPeersSlice$25(TLObject r7, TLRPC.TL_error r8) {
        if ((r7 instanceof TLRPC.contacts_TopPeers) == false) goto L32;
        TLRPC.contacts_TopPeers r72 = (TLRPC.contacts_TopPeers) r7;
        DataTypesUtils.AppendTopPeers(this._contactsProcess.result, r72);
        int r82 = this._contactsProcess.topPeersOffset;
        boolean r1 = true;
        if ((r72 instanceof TLRPC.TL_contacts_topPeersNotModified) == false) goto L8;
    L21:
        ApiWrap$ContactsProcess r73 = this._contactsProcess;
        if (r1 == false) goto L25;
        r73.done.run(r73.result);
        return;
    L25:
        r73.topPeersOffset = Math.max(Math.max(r73.result.correspondents.size(), this._contactsProcess.result.inlineBots.size()), this._contactsProcess.result.phoneCalls.size());
        requestTopPeersSlice();
        return;
    L8:
        if ((r72 instanceof TLRPC.TL_contacts_topPeersDisabled) == true) goto L21;
        if ((r72 instanceof TLRPC.TL_contacts_topPeers) == false) goto L20;
        ArrayList<TLRPC.TL_topPeerCategoryPeers> r74 = ((TLRPC.TL_contacts_topPeers) r72).categories;
        int r0 = r74.size();
        boolean r3 = true;
        int r4 = 0;
    L13:
        if (r4 >= r0) goto L19;
        TLRPC.TL_topPeerCategoryPeers r32 = r74.get(r4);
        r4 = r4 + 1;
        TLRPC.TL_topPeerCategoryPeers r33 = r32;
        if ((r33.peers.size() + r82) < r33.count) goto L17;
        r3 = true;
    L18:
        if (r3 == true) goto L13;
    L17:
        r3 = false;
    L19:
        r1 = r3;
        goto L21
    L20:
        r1 = false;
        goto L21
    }

    private /* synthetic */ void lambda$requestUserpics$19(TLObject r2, TLRPC.TL_error r3) {
        if ((r2 instanceof TLRPC.photos_Photos) == false) goto L16;
        TLRPC.photos_Photos r22 = (TLRPC.photos_Photos) r2;
        if ((r22 instanceof TLRPC.TL_photos_photos) == false) goto L8;
        ApiWrap$UserpicsInfo r0 = new ApiWrap$UserpicsInfo(((TLRPC.TL_photos_photos) r22).photos.size());
    L12:
        if (this._userpicsProcess.start.run(r0).booleanValue() == false) goto L17;
        handleUserpicsSlice(r22);
        return;
    L17:
        return;
    L8:
        if ((r22 instanceof TLRPC.TL_photos_photosSlice) == false) goto L10;
        r0 = new ApiWrap$UserpicsInfo(((TLRPC.TL_photos_photosSlice) r22).count);
        goto L12
    L10:
        r0 = null;
        goto L12
    }

    private /* synthetic */ void lambda$requestUserpicsCount$2(TLObject r1, TLRPC.TL_error r2) {
        if ((r1 instanceof TLRPC.photos_Photos) == false) goto L13;
        TLRPC.photos_Photos r12 = (TLRPC.photos_Photos) r1;
        if ((r12 instanceof TLRPC.TL_photos_photos) == false) goto L8;
        int r13 = ((TLRPC.TL_photos_photos) r12).photos.size();
    L11:
        this._startProcess.info.userpicsCount = r13;
        sendNextStartRequest();
        return;
    L8:
        if ((r12 instanceof TLRPC.TL_photos_photosSlice) == false) goto L10;
        r13 = ((TLRPC.TL_photos_photosSlice) r12).count;
        goto L11
    L10:
        r13 = 0;
        goto L11
    }

    private /* synthetic */ void lambda$resolveCustomEmoji$11(ArrayList r7) {
        int r0 = r7.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        Object r2 = r7.get(r1);
        r1 = r1 + 1;
        Long r22 = (Long) r2;
        if (this._resolvedCustomEmoji.containsKey(r22) == true) goto L3;
        ApiWrap$Document r3 = new ApiWrap$Document();
        ApiWrap$File r4 = new ApiWrap$File();
        r3.file = r4;
        r4.skipReason = ApiWrap$File.SkipReason.Unavailable;
        this._resolvedCustomEmoji.put(r22, r3);
        goto L3
    L8:
        resolveCustomEmoji();
    }

    private /* synthetic */ void lambda$resolveCustomEmoji$12(Runnable r7, TLObject r8, TLRPC.TL_error r9) {
        if (r9 == null) goto L6;
        FileLog.e("Export Error: Failed to get documents for emoji.");
        r7.run();
        return;
    L6:
        if ((r8 instanceof Vector) == false) goto L13;
        ArrayList<T> r82 = ((Vector) r8).objects;
        int r92 = r82.size();
        int r1 = 0;
    L8:
        if (r1 >= r92) goto L10;
        Object r2 = r82.get(r1);
        r1 = r1 + 1;
        ApiWrap$ChatProcess r3 = this._chatProcess;
        ApiWrap$Document r22 = DataTypesUtils.ParseDocument(r3.context, (TLRPC.Document) ((TLObject) r2), r3.info.relativePath, 0);
        this._resolvedCustomEmoji.put(Long.valueOf(r22.id), r22);
        goto L8
    L10:
        r7.run();
        return;
    }

    private /* synthetic */ void lambda$startMainSession$0(Runnable r2, TLObject r3, TLRPC.TL_error r4) {
        if (r4 == null) goto L8;
        if (r4.text == null) goto L8;
        ExportController.showError(r4);
        return;
    L8:
        if ((r3 instanceof ExportRequests$Takeout) == false) goto L11;
        this._takeoutId = ((ExportRequests$Takeout) r3).id;
        r2.run();
        return;
    }

    private /* synthetic */ void lambda$startMainSession$1(ExportRequests$InitTakeoutSession r4, Runnable r5, TLObject r6, TLRPC.TL_error r7) {
        if ((r6 instanceof Vector) == false) goto L20;
        ArrayList<T> r62 = ((Vector) r6).objects;
        int r72 = r62.size();
        int r0 = 0;
    L5:
        if (r0 >= r72) goto L11;
        Object r1 = r62.get(r0);
        r0 = r0 + 1;
        TLObject r12 = (TLObject) r1;
        if ((r12 instanceof TLRPC.User) == false) goto L5;
        TLRPC.User r13 = (TLRPC.User) r12;
        if (r13.self == false) goto L5;
        this._selfId = r13.id;
        goto L5
    L11:
        ConnectionsManager.getInstance(this.selectedAcc).sendRequest(r4, new ExportRequestsController$$ExternalSyntheticLambda52(this, r5));
        return;
    }

    private void loadFile(ApiWrap$File r5, ApiWrap$FileOrigin r6, Utilities.CallbackReturn<ApiWrap$FileProgress, Boolean> r7, Utilities.Callback<String> r8) {
        ApiWrap$FileProcess r52 = prepareFileProcess(r5, r6);
        this._fileProcess = r52;
        r52.progress = r7;
        r52.done = r8;
        if (r7 != null) goto L5;
    L7:
        loadFilePart();
        return;
    L5:
        if (r7.run(new ApiWrap$FileProgress(r52.file.size(), this._fileProcess.size)).booleanValue() == true) goto L7;
    }

    private void loadFilePart() {
        ApiWrap$FileProcess r0 = this._fileProcess;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.requestId == 0) goto L7;
        return;
    L7:
        if (r0.requests.size() >= 2) goto L17;
        ApiWrap$FileProcess r02 = this._fileProcess;
        long r1 = r02.size;
        if (r1 > 0) goto L11;
    L13:
        long r03 = r02.offset;
        ApiWrap$FileProcess.Request r2 = new ApiWrap$FileProcess.Request();
        r2.offset = r03;
        this._fileProcess.requests.add(r2);
        this._fileProcess.requestId = fileRequest(r2.location, r2.offset, new ExportRequestsController$$ExternalSyntheticLambda39(this, r03));
        this._fileProcess.offset += 1048576;
        return;
    L11:
        if (r02.offset < r1) goto L13;
        return;
    }

    private void loadMessageEmojiDone(long r2, String r4) {
        ApiWrap$Document r22 = this._resolvedCustomEmoji.get(Long.valueOf(r2));
        if (r22 == null) goto L7;
        r22.file.relativePath = r4;
        if (r4.isEmpty() == false) goto L7;
        ApiWrap$File r23 = r22.file;
        r23.skipReason = ApiWrap$File.SkipReason.Unavailable;
    L7:
        loadNextMessageFile();
    }

    private void loadMessageFileDone(String r3) {
        ApiWrap$ChatProcess r0 = this._chatProcess;
        int r1 = r0.fileIndex;
        ApiWrap$File r02 = r0.slice.list.get(r1).getFile();
        r02.relativePath = r3;
        if (r3.isEmpty() == false) goto L5;
        r02.skipReason = ApiWrap$File.SkipReason.Unavailable;
    L5:
        loadNextMessageFile();
    }

    private boolean loadMessageFileProgress(ApiWrap$FileProgress r10) {
        ApiWrap$FileProcess r1 = this._fileProcess;
        ApiWrap$DownloadProgress r0 = new ApiWrap$DownloadProgress(r1.randomId, r1.relativePath, this._chatProcess.fileIndex, r10.ready(), r10.total());
        return this._chatProcess.fileProgress.run(r0).booleanValue();
    }

    private void loadMessageThumbDone(String r3) {
        ApiWrap$ChatProcess r0 = this._chatProcess;
        int r1 = r0.fileIndex;
        ApiWrap$File r02 = r0.slice.list.get(r1).media.getThumb().file;
        if (r3.contains("null") == true) goto L9;
        r02.relativePath = r3;
        if (r3.isEmpty() == false) goto L7;
        r02.skipReason = ApiWrap$File.SkipReason.Unavailable;
    L7:
        loadNextMessageFile();
        return;
    L9:
        Segment$$ExternalSyntheticBUOutline1.m("zdes 1");
    }

    private void loadMessagesFiles(ApiWrap$MessagesSlice r3) {
        collectMessagesCustomEmoji(r3);
        if (r3.list.isEmpty() == false) goto L5;
        this._chatProcess.lastSlice = true;
    L5:
        ApiWrap$ChatProcess r0 = this._chatProcess;
        r0.slice = r3;
        r0.fileIndex = 0;
        resolveCustomEmoji();
    }

    private void loadNextMessageFile() {
        ArrayList<ApiWrap$Message> r0 = this._chatProcess.slice.list;
    L4:
        if (this._chatProcess.fileIndex >= r0.size()) goto L18;
        ApiWrap$Message r1 = r0.get(this._chatProcess.fileIndex);
        if (DataTypesUtils.SkipMessageByDate(r1, this._settings) == false) goto L9;
        ExportRequestsController r2 = this;
    L17:
        r2._chatProcess.fileIndex++;
        ExportRequestsController this = r2;
        goto L4
    L9:
        if (this.messageCustomEmojiReady(r1) == false) goto L16;
        ExportRequestsController r3 = this;
        r2 = r3;
        if (r3.processFileLoad(r0.get(this._chatProcess.fileIndex).getFile(), this.currentFileMessageOrigin(), new ExportRequestsController$$ExternalSyntheticLambda1(this), new ExportRequestsController$$ExternalSyntheticLambda56(this), this.currentFileMessage(), null) == false) goto L26;
        if (r2.processFileLoad(r1.getFile(), r2.currentFileMessageOrigin(), new ExportRequestsController$$ExternalSyntheticLambda1(r2), new ExportRequestsController$$ExternalSyntheticLambda57(r2), r2.currentFileMessage(), null) == true) goto L17;
        return;
    L26:
        return;
    L16:
        return;
    L18:
        this.finishMessagesSlice();
    }

    private void loadNextStory() {
        ArrayList<ApiWrap$Story> r0 = this._storiesProcess.slice.list;
    L4:
        if (this._storiesProcess.fileIndex >= r0.size()) goto L12;
        ApiWrap$Story r8 = r0.get(this._storiesProcess.fileIndex);
        ApiWrap$FileOrigin r4 = new ApiWrap$FileOrigin(0, null, 0, r8.id, 0);
        ExportRequestsController r2 = this;
        if (r2.processFileLoad(r8.file(), r4, new ExportRequestsController$$ExternalSyntheticLambda24(this), new ExportRequestsController$$ExternalSyntheticLambda25(this), null, r8) == false) goto L10;
        if (r2.processFileLoad(r8.thumb().file, r4, new ExportRequestsController$$ExternalSyntheticLambda24(r2), new ExportRequestsController$$ExternalSyntheticLambda26(r2), null, r8) == false) goto L17;
        r2._storiesProcess.fileIndex++;
        ExportRequestsController this = r2;
        goto L4
    L17:
        return;
    L10:
        return;
    L12:
        this.finishStoriesSlice();
    }

    private void loadNextUserpic() {
        ArrayList<HtmlWriter.Photo> r0 = this._userpicsProcess.slice;
    L4:
        if (this._userpicsProcess.fileIndex >= r0.size()) goto L9;
        ExportRequestsController r2 = this;
        if (r2.processFileLoad(r0.get(this._userpicsProcess.fileIndex).image.file, new ApiWrap$FileOrigin(), new ExportRequestsController$$ExternalSyntheticLambda32(this), new ExportRequestsController$$ExternalSyntheticLambda33(this), null, null) == false) goto L7;
        r2._userpicsProcess.fileIndex++;
        ExportRequestsController this = r2;
        goto L4
    L7:
        return;
    L9:
        this.finishUserpicsSlice();
    }

    private void loadStoriesFiles(ApiWrap$StoriesSlice r3) {
        if (r3.lastId != 0) goto L5;
        this._storiesProcess.lastSlice = true;
    L5:
        ApiWrap$StoriesProcess r0 = this._storiesProcess;
        r0.slice = r3;
        r0.fileIndex = 0;
        loadNextStory();
    }

    private void loadStoryDone(String r3) {
        ApiWrap$StoriesProcess r0 = this._storiesProcess;
        int r1 = r0.fileIndex;
        ApiWrap$File r02 = r0.slice.list.get(r1).file();
        r02.relativePath = r3;
        if (r3.isEmpty() == false) goto L5;
        r02.skipReason = ApiWrap$File.SkipReason.Unavailable;
    L5:
        loadNextStory();
    }

    private boolean loadStoryProgress(ApiWrap$FileProgress r10) {
        ApiWrap$FileProcess r1 = this._fileProcess;
        ApiWrap$DownloadProgress r0 = new ApiWrap$DownloadProgress(r1.randomId, r1.relativePath, this._storiesProcess.fileIndex, r10.ready(), r10.total());
        return this._storiesProcess.fileProgress.run(r0).booleanValue();
    }

    private void loadStoryThumbDone(String r3) {
        ApiWrap$StoriesProcess r0 = this._storiesProcess;
        int r1 = r0.fileIndex;
        ApiWrap$File r02 = r0.slice.list.get(r1).thumb().file;
        r02.relativePath = r3;
        if (r3.isEmpty() == false) goto L5;
        r02.skipReason = ApiWrap$File.SkipReason.Unavailable;
    L5:
        loadNextStory();
    }

    private void loadUserpicDone(String r3) {
        ApiWrap$UserpicsProcess r0 = this._userpicsProcess;
        int r1 = r0.fileIndex;
        ApiWrap$File r02 = r0.slice.get(r1).image.file;
        r02.relativePath = r3;
        if (r3.isEmpty() == false) goto L5;
        r02.skipReason = ApiWrap$File.SkipReason.Unavailable;
    L5:
        loadNextUserpic();
    }

    private void loadUserpicsFiles(ArrayList<HtmlWriter.Photo> r3) {
        if (r3.isEmpty() == false) goto L5;
        this._userpicsProcess.lastSlice = true;
    L5:
        ApiWrap$UserpicsProcess r0 = this._userpicsProcess;
        r0.slice = r3;
        r0.fileIndex = 0;
        loadNextUserpic();
    }

    private int mainRequest(TLObject r4, Utilities.Callback2<TLObject, TLRPC.TL_error> r5) {
        ExportRequests$InvokeWithTakeoutWrapper r0 = new ExportRequests$InvokeWithTakeoutWrapper();
        r0.query = r4;
        r0.takeout_id = this._takeoutId;
        return ConnectionsManager.getInstance(this.selectedAcc).sendRequest(r0, new ExportRequestsController$$ExternalSyntheticLambda7(r5));
    }

    private void messagesCountLoaded(int r2, int r3) {
        this._chatProcess.info.messagesCountPerSplit.set(r2, Integer.valueOf(r3));
        int r22 = r2 + 1;
        if (r22 >= this._chatProcess.info.splits.size()) goto L6;
        requestMessagesCount(r22);
        return;
    L6:
        ApiWrap$ChatProcess r23 = this._chatProcess;
        if (r23.start.run(r23.info).booleanValue() == false) goto L10;
        requestMessagesSlice();
        return;
    }

    private void otherDataDone(String r2) {
        this._otherDataProcess.file.relativePath = r2;
        if (r2.isEmpty() == false) goto L5;
        ApiWrap$File r22 = this._otherDataProcess.file;
        r22.skipReason = ApiWrap$File.SkipReason.Unavailable;
    L5:
        ApiWrap$OtherDataProcess r1 = this._otherDataProcess;
        r1.done.run(r1.file);
    }

    private ApiWrap$FileProcess prepareFileProcess(ApiWrap$File r5, ApiWrap$FileOrigin r6) {
        String r0 = OutputFile.PrepareRelativePath(this._settings.path, r5.suggestedPath);
        ApiWrap$FileProcess r1 = new ApiWrap$FileProcess(this._settings.path + "/" + r0, this._stats);
        r1.relativePath = r0;
        r1.location = r5.location;
        r1.size = r5.size;
        r1.origin = r6;
        r1.randomId = Utilities.random.nextLong();
        return r1;
    }

    private boolean processFileLoad(ApiWrap$File r7, ApiWrap$FileOrigin r8, Utilities.CallbackReturn<ApiWrap$FileProgress, Boolean> r9, Utilities.Callback<String> r10, ApiWrap$Message r11, ApiWrap$Story r12) {
        if (r7.relativePath.isEmpty() == true) goto L5;
    L68:
        return true;
    L5:
        if (r7.skipReason != ApiWrap$File.SkipReason.None) goto L68;
        if (r7.location != null) goto L16;
        byte[] r0 = r7.content;
        if (r0 != null) goto L12;
    L13:
        r7.skipReason = ApiWrap$File.SkipReason.Unavailable;
        return true;
    L12:
        if (r0.length == 0) goto L13;
    L16:
        if (writePreloadedFile(r7, r8) == true) goto L18;
        if (r11 == null) goto L21;
        Object r02 = r11.media;
    L25:
        if ((r02 instanceof ApiWrap$Media) == false) goto L45;
        Object r03 = ((ApiWrap$Media) r02).content;
        if ((r03 instanceof ApiWrap$Document) == false) goto L44;
        ApiWrap$Document r04 = (ApiWrap$Document) r03;
        if (r04.isSticker == false) goto L32;
        int r05 = 16;
    L46:
        if (r11 == null) goto L48;
        long r4 = r11.getFile().size;
    L51:
        if (r11 != null) goto L53;
    L56:
        if (r12 == null) goto L58;
    L61:
        if (r12 == null) goto L63;
    L66:
        loadFile(r7, r8, r9, r10);
        return false;
    L63:
        if (r4 <= this._settings.media.sizeLimit) goto L66;
        r7.skipReason = ApiWrap$File.SkipReason.FileSize;
        return true;
    L58:
        if ((this._settings.media.type & r05) == r05) goto L61;
        r7.skipReason = ApiWrap$File.SkipReason.FileType;
        return true;
    L53:
        if (DataTypesUtils.SkipMessageByDate(r11, this._settings) == false) goto L56;
        r7.skipReason = ApiWrap$File.SkipReason.DateLimits;
        return true;
    L48:
        if (r12 == null) goto L50;
        r4 = r12.file().size;
        goto L51
    L50:
        r4 = r7.size;
        goto L51
    L32:
        if (r04.isVideoMessage == false) goto L35;
        r05 = 8;
        goto L46
    L35:
        if (r04.isVoiceMessage == false) goto L38;
        r05 = 4;
        goto L46
    L38:
        if (r04.isAnimated == false) goto L41;
        r05 = 32;
        goto L46
    L41:
        if (r04.isVideoFile == false) goto L43;
        r05 = 2;
        goto L46
    L43:
        r05 = 64;
        goto L46
    L44:
        r05 = 1;
        goto L46
    L45:
        r05 = 0;
        goto L46
    L21:
        if (r12 == null) goto L23;
        r02 = r12.file();
        goto L25
    L23:
        r02 = null;
        goto L25
    L18:
        return !r7.relativePath.isEmpty();
    }

    private void requestChatMessages(int r9, int r10, int r11, int r12, Utilities.Callback<TLRPC.messages_Messages> r13) {
        this._chatProcess.requestDone = r13;
        int r132 = this.splits.size();
        ApiWrap$ChatProcess r0 = this._chatProcess;
        if (r9 < 0) goto L6;
        TLRPC.InputPeer r02 = r0.info.input;
    L5:
        TLRPC.InputPeer r3 = r02;
        if (r9 < 0) goto L9;
        int r133 = r9;
    L10:
        ApiWrap$DialogInfo r03 = this._chatProcess.info;
        if (r03.isMonoforum == false) goto L13;
        TLRPC.InputPeer r04 = r03.monoforumBroadcastInput;
    L15:
        if (this._chatProcess.info.onlyMyMessages == false) goto L18;
        TLRPC.TL_messages_search r92 = new TLRPC.TL_messages_search();
        r92.flags = 1;
        r92.peer = r3;
        r92.q = _UrlKt.FRAGMENT_ENCODE_SET;
        r92.from_id = r04;
        r92.saved_peer_id = new TLRPC.TL_inputPeerEmpty();
        r92.top_msg_id = 0;
        r92.filter = new TLRPC.TL_inputMessagesFilterEmpty();
        r92.offset_id = r10;
        r92.add_offset = r11;
        r92.limit = r12;
        splitRequest(r133, r92, new ExportRequestsController$$ExternalSyntheticLambda22(this));
        return;
    L18:
        TLRPC.TL_messages_getHistory r05 = new TLRPC.TL_messages_getHistory();
        r05.peer = r3;
        r05.offset_id = r10;
        r05.add_offset = r11;
        r05.limit = r12;
        splitRequest(r133, r05, new ExportRequestsController$$ExternalSyntheticLambda23(this, r3, r9, r10, r11, r12));
        return;
    L13:
        r04 = new TLRPC.TL_inputPeerSelf();
        goto L15
    L9:
        r133 = r132 + r9;
        goto L10
    L6:
        r02 = r0.info.migratedFromInput;
        goto L5
    }

    private void requestLeftChannelsCount() {
        this._leftChannelsProcess = new ApiWrap$LeftChannelsProcess();
        requestLeftChannelsSliceGeneric(new ExportRequestsController$$ExternalSyntheticLambda37(this));
    }

    private void requestLeftChannelsList(Utilities.CallbackReturn<Integer, Boolean> r2, Utilities.Callback<ApiWrap$DialogsInfo> r3) {
        ApiWrap$LeftChannelsProcess r0 = this._leftChannelsProcess;
        r0.progress = r2;
        r0.done = r3;
        requestLeftChannelsSlice();
    }

    private void requestLeftChannelsSlice() {
        requestLeftChannelsSliceGeneric(new ExportRequestsController$$ExternalSyntheticLambda50(this));
    }

    private void requestLeftChannelsSliceGeneric(Runnable r3) {
        ExportRequests$getLeftChannels r0 = new ExportRequests$getLeftChannels();
        r0.offset = this._leftChannelsProcess.offset;
        mainRequest(r0, new ExportRequestsController$$ExternalSyntheticLambda49(this, r3));
    }

    private void requestMessagesCount(int r8) {
        requestChatMessages(this._chatProcess.info.splits.get(r8).intValue(), 0, 0, 1, new ExportRequestsController$$ExternalSyntheticLambda5(this, r8));
    }

    private void requestMessagesSlice() {
        ApiWrap$ChatProcess r0 = this._chatProcess;
        if (r0.info.messagesCountPerSplit.get(r0.localSplitIndex).intValue() != 0) goto L6;
        loadMessagesFiles(new ApiWrap$MessagesSlice());
        return;
    L6:
        ApiWrap$ChatProcess r02 = this._chatProcess;
        requestChatMessages(r02.info.splits.get(r02.localSplitIndex).intValue(), this._chatProcess.largestIdPlusOne, -100, 100, new ExportRequestsController$$ExternalSyntheticLambda30(this));
    }

    private void requestSinglePeerDialog() {
        ExportRequestsController$$ExternalSyntheticLambda34 r0 = new ExportRequestsController$$ExternalSyntheticLambda34(this);
        ExportRequestsController$$ExternalSyntheticLambda35 r1 = new ExportRequestsController$$ExternalSyntheticLambda35(this, r0);
        TLRPC.InputPeer r2 = this._settings.singlePeer;
        if ((r2 instanceof TLRPC.TL_inputPeerUser) == false) goto L7;
        TLRPC.TL_inputPeerUser r22 = (TLRPC.TL_inputPeerUser) r2;
        TLRPC.TL_inputUser r6 = new TLRPC.TL_inputUser();
        r6.access_hash = r22.access_hash;
        r6.user_id = r22.user_id;
        r1.run(r6);
        return;
    L7:
        if ((r2 instanceof TLRPC.TL_inputPeerChat) == false) goto L11;
        TLRPC.TL_messages_getChats r12 = new TLRPC.TL_messages_getChats();
        r12.id = new ArrayList(ImageCapture$$ExternalSyntheticBackport0.m(Long.valueOf(((TLRPC.TL_inputPeerChat) r2).chat_id)));
        mainRequest(r12, new ExportRequestsController$$ExternalSyntheticLambda36(r0));
        return;
    L11:
        if ((r2 instanceof TLRPC.TL_inputPeerSelf) == false) goto L15;
        r1.run(new TLRPC.TL_inputUserSelf());
        return;
    L15:
        if ((r2 instanceof TLRPC.TL_inputPeerUserFromMessage) == false) goto L17;
        Segment$$ExternalSyntheticBUOutline1.m("From message peer in requestSinglePeerDialog.");
        return;
    L17:
        if ((r2 instanceof TLRPC.TL_inputPeerChannelFromMessage) == false) goto L19;
        Segment$$ExternalSyntheticBUOutline1.m("From message peer in requestSinglePeerDialog.");
        return;
    L19:
        if ((r2 instanceof TLRPC.TL_inputPeerEmpty) == true) goto L21;
        return;
    L21:
        Segment$$ExternalSyntheticBUOutline1.m("Empty peer in requestSinglePeerDialog.");
    }

    private int requestSinglePeerMigrated(ApiWrap$DialogInfo r4) {
        TLRPC.InputPeer r0 = r4.input;
        if ((r0 instanceof TLRPC.TL_inputPeerChannel) == false) goto L6;
        TLRPC.TL_inputPeerChannel r02 = (TLRPC.TL_inputPeerChannel) r0;
        TLRPC.TL_inputChannel r42 = new TLRPC.TL_inputChannel();
        r42.channel_id = r02.channel_id;
        r42.access_hash = r02.access_hash;
        TLRPC.TL_channels_getFullChannel r03 = new TLRPC.TL_channels_getFullChannel();
        r03.channel = r42;
        return mainRequest(r03, new ExportRequestsController$$ExternalSyntheticLambda58(this));
    L6:
        Buffer$$ExternalSyntheticBUOutline4.m("unexpected peer type: ", r4.input);
        return 0;
    }

    private void requestStoriesCount() {
        TL_stories.TL_stories_getStoriesArchive r0 = new TL_stories.TL_stories_getStoriesArchive();
        r0.peer = new TLRPC.TL_inputPeerSelf();
        r0.limit = 0;
        r0.offset_id = 0;
        mainRequest(r0, new ExportRequestsController$$ExternalSyntheticLambda31(this));
    }

    private void requestTopPeersSlice() {
        TLRPC.TL_contacts_getTopPeers r0 = new TLRPC.TL_contacts_getTopPeers();
        r0.correspondents = true;
        r0.bots_inline = true;
        r0.phone_calls = true;
        r0.offset = this._contactsProcess.topPeersOffset;
        r0.limit = 100;
        r0.hash = 0;
        mainRequest(r0, new ExportRequestsController$$ExternalSyntheticLambda41(this));
    }

    private void requestUserpicsCount() {
        TLRPC.TL_photos_getUserPhotos r0 = new TLRPC.TL_photos_getUserPhotos();
        r0.user_id = new TLRPC.TL_inputUserSelf();
        r0.offset = 0;
        r0.max_id = 0;
        r0.limit = 0;
        mainRequest(r0, new ExportRequestsController$$ExternalSyntheticLambda17(this));
    }

    private void resolveCustomEmoji() {
        if (this._unresolvedCustomEmoji.isEmpty() == false) goto L6;
        loadNextMessageFile();
        return;
    L6:
        int r0 = Math.min(this._unresolvedCustomEmoji.size(), 100);
        ArrayList<Long> r1 = new ArrayList(r0);
        ArrayList r2 = new ArrayList(this._unresolvedCustomEmoji);
        int r3 = r2.size();
        int r02 = Math.max(0, r2.size() - r0);
    L7:
        if (r02 == r3) goto L9;
        r1.add((Long) r2.get(r02));
        this._unresolvedCustomEmoji.remove(r2.get(r02));
        r02 = r02 + 1;
        goto L7
    L9:
        ExportRequestsController$$ExternalSyntheticLambda44 r03 = new ExportRequestsController$$ExternalSyntheticLambda44(this, r1);
        TLRPC.TL_messages_getCustomEmojiDocuments r22 = new TLRPC.TL_messages_getCustomEmojiDocuments();
        r22.document_id = r1;
        mainRequest(r22, new ExportRequestsController$$ExternalSyntheticLambda45(this, r03));
    }

    private void sendNextStartRequest() {
        ArrayList<StartProcess.Step> r0 = this._startProcess.steps;
        if (r0.isEmpty() == false) goto L6;
        finishStartProcess();
        Log.d("exteraGram", "caught empty steps in sendNextStartRequest, finishing start process...");
        return;
    L6:
        StartProcess.Step r2 = r0.get(0);
        r0.remove(0);
        int r02 = AnonymousClass1.$SwitchMap$com$exteragram$messenger$export$controllers$ExportRequestsController$StartProcess$Step[r2.ordinal()];
        if (r02 != 1) goto L9;
        requestUserpicsCount();
        return;
    L9:
        if (r02 != 2) goto L11;
        requestStoriesCount();
        return;
    L11:
        if (r02 != 3) goto L13;
        requestSplitRanges();
        return;
    L13:
        if (r02 != 4) goto L15;
        requestDialogsCount();
        return;
    L15:
        if (r02 == 5) goto L17;
        return;
    L17:
        requestLeftChannelsCount();
    }

    private void startMainSession(Runnable r8) {
        ExportSettings.MediaSettings r0 = this._settings.media;
        long r1 = r0.sizeLimit;
        if (r0.isEnabled() == true) goto L5;
    L6:
        int r02 = this._settings.types;
        if ((r02 & 2) == 0) goto L9;
    L12:
        boolean r03 = true;
    L13:
        ExportRequests$InitTakeoutSession r4 = new ExportRequests$InitTakeoutSession();
        if (r03 == false) goto L18;
        r4.files = true;
        if (r1 >= SaveToGallerySettingsHelper.MAX_VIDEO_LIMIT) goto L18;
        r4.file_max_size = r1;
    L18:
        int r04 = this._settings.types;
        if ((r04 & 4) == 0) goto L22;
        r4.contacts = true;
    L22:
        if ((r04 & 32) == 0) goto L24;
    L25:
        r4.message_users = true;
    L27:
        if ((r04 & 128) == 0) goto L30;
        r4.message_megagroups = true;
        r4.message_chats = true;
    L30:
        if ((r04 & 256) == 0) goto L33;
        r4.message_megagroups = true;
    L33:
        if ((r04 & 512) == 0) goto L35;
    L36:
        r4.message_channels = true;
    L37:
        TLRPC.TL_users_getUsers r05 = new TLRPC.TL_users_getUsers();
        TLRPC.TL_inputUser r12 = new TLRPC.TL_inputUser();
        r12.user_id = UserConfig.getInstance(this.selectedAcc).clientUserId;
        r05.id = new ArrayList(ImageCapture$$ExternalSyntheticBackport0.m(r12));
        ConnectionsManager.getInstance(this.selectedAcc).sendRequest(r05, new ExportRequestsController$$ExternalSyntheticLambda38(this, r4, r8));
        return;
    L35:
        if ((r04 & 1024) == 0) goto L37;
    L24:
        if ((r04 & 64) == 0) goto L27;
    L9:
        if ((r02 & 2048) != 0) goto L12;
        r03 = false;
        goto L13
    L5:
        if (r1 > 0) goto L12;
        goto L6
    }

    private boolean writePreloadedFile(ApiWrap$File r4, ApiWrap$FileOrigin r5) {
        String r0 = this._fileCache.find(r4.location);
        if (r0 == null) goto L6;
        r4.relativePath = r0;
        return true;
    L6:
        byte[] r02 = r4.content;
        if (r02 != null) goto L9;
        return false;
    L9:
        if (r02.length == 0) goto L16;
        ApiWrap$FileProcess r52 = prepareFileProcess(r4, r5);
        if (r52.file.writeBlock(r4.content).isSuccess() == false) goto L13;
        String r53 = r52.relativePath;
        r4.relativePath = r53;
        this._fileCache.save(r4.location, r53);
    L13:
        return true;
    L16:
        return false;
    }

    public void finishStartProcess() {
        StartProcess r1 = this._startProcess;
        r1.done.run(r1.info);
    }

    public void invokeFinish(boolean r2, Runnable r3) {
        ExportRequests$FinishTakeoutSession r0 = new ExportRequests$FinishTakeoutSession();
        r0.success = !r2;
        mainRequest(r0, new ExportRequestsController$$ExternalSyntheticLambda4(r3));
    }

    public boolean loadUserpicProgress(ApiWrap$FileProgress r10) {
        ApiWrap$FileProcess r1 = this._fileProcess;
        ApiWrap$DownloadProgress r0 = new ApiWrap$DownloadProgress(r1.randomId, r1.relativePath, this._userpicsProcess.fileIndex, r10.ready(), r10.total());
        return this._userpicsProcess.fileProgress.run(r0).booleanValue();
    }

    public boolean messageCustomEmojiReady(ApiWrap$Message r8) {
        ArrayList<ApiWrap$TextPart> r0 = r8.text;
        int r1 = r0.size();
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L12;
        ApiWrap$TextPart r4 = r0.get(r3);
        r3 = r3 + 1;
        ApiWrap$TextPart r42 = r4;
        if (r42.type != ApiWrap$TextPart.Type.CustomEmoji) goto L3;
        String r5 = getCustomEmoji(r42.additional);
        if (r5 == null) goto L11;
        if (r5.isEmpty() == true) goto L11;
        r42.additional = r5;
    L11:
        return false;
    L12:
        Iterator<ApiWrap$Reaction> r7 = r8.reactions.iterator();
        if (r7.hasNext() == true) goto L16;
        return true;
    L16:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r7.next());
        throw null;
    }

    public void requestContacts(Utilities.Callback<ApiWrap$ContactsList> r2) {
        ApiWrap$ContactsProcess r0 = new ApiWrap$ContactsProcess();
        this._contactsProcess = r0;
        r0.done = r2;
        mainRequest(new ExportRequests$TL_contacts_getSaved(), new ExportRequestsController$$ExternalSyntheticLambda11(this));
    }

    public void requestDialogsCount() {
        if (this._settings.onlySinglePeer() == false) goto L6;
        this._startProcess.info.dialogsCount = 1;
        sendNextStartRequest();
        return;
    L6:
        TLRPC.TL_messages_getDialogs r0 = new TLRPC.TL_messages_getDialogs();
        r0.offset_peer = new TLRPC.TL_inputPeerEmpty();
        r0.limit = 1;
        splitRequest(this.index, r0, new ExportRequestsController$$ExternalSyntheticLambda27(this));
    }

    public void requestDialogsList(Utilities.CallbackReturn<Integer, Boolean> r3, Utilities.Callback<ApiWrap$DialogsInfo> r4) {
        ApiWrap$DialogsProcess r0 = new ApiWrap$DialogsProcess();
        this._dialogsProcess = r0;
        r0.splitIndexPlusOne = this.splits.size();
        ApiWrap$DialogsProcess r02 = this._dialogsProcess;
        r02.progress = r3;
        r02.done = r4;
        requestDialogsSlice();
    }

    public void requestDialogsSlice() {
        if (this._settings.onlySinglePeer() == false) goto L6;
        requestSinglePeerDialog();
        return;
    L6:
        TLRPC.TL_messages_getDialogs r0 = new TLRPC.TL_messages_getDialogs();
        ApiWrap$DialogsProcess r1 = this._dialogsProcess;
        r0.offset_peer = r1.offsetPeer;
        r0.limit = 100;
        r0.offset_date = r1.offsetDate;
        r0.offset_id = r1.offsetId;
        splitRequest(r1.splitIndexPlusOne - 1, r0, new ExportRequestsController$$ExternalSyntheticLambda15(this));
    }

    public void requestLeftChannelsIfNeeded() {
        if ((this._settings.types & 1920) == 0) goto L6;
        requestLeftChannelsList(new ExportRequestsController$$ExternalSyntheticLambda19(this), new ExportRequestsController$$ExternalSyntheticLambda20(this));
        return;
    L6:
        finishDialogsList();
    }

    public void requestMessages(ApiWrap$DialogInfo r5, Utilities.CallbackReturn<ApiWrap$DialogInfo, Boolean> r6, Utilities.CallbackReturn<ApiWrap$DownloadProgress, Boolean> r7, Utilities.CallbackReturn<ApiWrap$MessagesSlice, Boolean> r8, Runnable r9) {
        ApiWrap$ChatProcess r0 = new ApiWrap$ChatProcess();
        this._chatProcess = r0;
        ApiWrap$ParseMediaContext r1 = r0.context;
        r1.selfPeerId = this._selfId;
        r0.info = r5;
        r0.start = r6;
        r0.fileProgress = r7;
        r0.handleSlice = r8;
        r0.done = r9;
        requestMessagesCount(0);
    }

    public void requestOtherData(String r3, Utilities.Callback<ApiWrap$File> r4) {
        ApiWrap$OtherDataProcess r0 = new ApiWrap$OtherDataProcess();
        this._otherDataProcess = r0;
        r0.done = r4;
        ApiWrap$File r42 = r0.file;
        r42.suggestedPath = r3;
        r42.location = new ApiWrap$FileLocation();
        ApiWrap$FileLocation r32 = this._otherDataProcess.file.location;
        r32.data = new ExportRequests$TL_inputTakeoutFileLocation();
        loadFile(this._otherDataProcess.file, new ApiWrap$FileOrigin(), new ExportRequestsController$$ExternalSyntheticLambda8(), new ExportRequestsController$$ExternalSyntheticLambda9(this));
    }

    public void requestPersonalInfo(Utilities.Callback<ApiWrap$ExportPersonalInfo> r3) {
        TLRPC.TL_users_getFullUser r0 = new TLRPC.TL_users_getFullUser();
        r0.id = new TLRPC.TL_inputUserSelf();
        mainRequest(r0, new ExportRequestsController$$ExternalSyntheticLambda10(r3));
    }

    public void requestSessions(Utilities.Callback<ApiWrap$SessionsList> r3) {
        mainRequest(new TL_account.getAuthorizations(), new ExportRequestsController$$ExternalSyntheticLambda12(this, r3));
    }

    public void requestSplitRanges() {
        mainRequest(new ExportRequests$getSplitRanges(), new ExportRequestsController$$ExternalSyntheticLambda18(this));
    }

    public void requestStories(Utilities.CallbackReturn<Integer, Boolean> r2, Utilities.CallbackReturn<ApiWrap$DownloadProgress, Boolean> r3, Utilities.CallbackReturn<ApiWrap$StoriesSlice, Boolean> r4, Runnable r5) {
        ApiWrap$StoriesProcess r0 = new ApiWrap$StoriesProcess();
        this._storiesProcess = r0;
        r0.start = r2;
        r0.fileProgress = r3;
        r0.handleSlice = r4;
        r0.finish = r5;
        TL_stories.TL_stories_getStoriesArchive r22 = new TL_stories.TL_stories_getStoriesArchive();
        r22.peer = new TLRPC.TL_inputPeerSelf();
        r22.limit = 100;
        r22.offset_id = this._storiesProcess.offsetId;
        mainRequest(r22, new ExportRequestsController$$ExternalSyntheticLambda14(this));
    }

    public void requestUserpics(Utilities.CallbackReturn<ApiWrap$UserpicsInfo, Boolean> r2, Utilities.CallbackReturn<ApiWrap$DownloadProgress, Boolean> r3, Utilities.CallbackReturn<ArrayList<HtmlWriter.Photo>, Boolean> r4, Runnable r5) {
        ApiWrap$UserpicsProcess r0 = new ApiWrap$UserpicsProcess();
        this._userpicsProcess = r0;
        r0.start = r2;
        r0.fileProgress = r3;
        r0.handleSlice = r4;
        r0.finish = r5;
        TLRPC.TL_photos_getUserPhotos r22 = new TLRPC.TL_photos_getUserPhotos();
        r22.limit = 100;
        r22.user_id = new TLRPC.TL_inputUserSelf();
        r22.offset = 0;
        r22.max_id = this._userpicsProcess.maxId;
        mainRequest(r22, new ExportRequestsController$$ExternalSyntheticLambda13(this));
    }

    public int splitRequest(int r2, TLObject r3, Utilities.Callback2<TLObject, TLRPC.TL_error> r4) {
        ExportRequests$InvokeWithMessagesRange r0 = new ExportRequests$InvokeWithMessagesRange();
        r0.query = r3;
        if (r2 >= 0) goto L5;
        r0.range = new TLRPC.TL_messageRange();
    L7:
        return mainRequest(r0, r4);
    L5:
        r0.range = this.splits.get(r2);
        goto L7
    }

    public void startExport(ExportSettings r1, OutputFile.Stats r2, Utilities.Callback<StartInfo> r3) {
        this._settings = r1;
        this._stats = r2;
        StartProcess r12 = new StartProcess();
        this._startProcess = r12;
        r12.done = r3;
        if ((this._settings.types & 2) == 0) goto L6;
        r12.steps.add(StartProcess.Step.UserpicsCount);
    L6:
        if ((this._settings.types & 2048) == 0) goto L9;
        this._startProcess.steps.add(StartProcess.Step.StoriesCount);
    L9:
        if ((this._settings.types & CastStatusCodes.DEVICE_CONNECTION_SUSPENDED) == 0) goto L11;
        this._startProcess.steps.add(StartProcess.Step.SplitRanges);
        this._startProcess.steps.add(StartProcess.Step.DialogsCount);
    L11:
        ExportSettings r13 = this._settings;
        if ((r13.types & 1920) != 0) goto L14;
    L16:
        startMainSession(new ExportRequestsController$$ExternalSyntheticLambda6(this));
        return;
    L14:
        if (r13.onlySinglePeer() == true) goto L16;
        this._startProcess.steps.add(StartProcess.Step.LeftChannelsCount);
        goto L16
    }

    public boolean useOnlyLastSplit() {
        if ((this._settings.types & Opcodes.SHL_INT_LIT8) != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public void vectorToRanges(TLObject r4, ArrayList<TLRPC.TL_messageRange> r5) {
        if ((r4 instanceof Vector) == false) goto L9;
        ArrayList<T> r3 = ((Vector) r4).objects;
        int r42 = r3.size();
        int r0 = 0;
    L5:
        if (r0 >= r42) goto L15;
        Object r1 = r3.get(r0);
        r0 = r0 + 1;
        TLObject r12 = (TLObject) r1;
        if ((r12 instanceof TLRPC.TL_messageRange) == false) goto L5;
        r5.add((TLRPC.TL_messageRange) r12);
        goto L5
    L15:
        return;
    }
}
