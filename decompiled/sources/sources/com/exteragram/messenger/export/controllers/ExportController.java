package com.exteragram.messenger.export.controllers;

import android.os.Environment;
import android.util.Log;
import com.exteragram.messenger.export.ExportSettings;
import com.exteragram.messenger.export.api.ApiWrap$ContactsList;
import com.exteragram.messenger.export.api.ApiWrap$DialogInfo;
import com.exteragram.messenger.export.api.ApiWrap$DialogsInfo;
import com.exteragram.messenger.export.api.ApiWrap$DownloadProgress;
import com.exteragram.messenger.export.api.ApiWrap$ExportPersonalInfo;
import com.exteragram.messenger.export.api.ApiWrap$File;
import com.exteragram.messenger.export.api.ApiWrap$MessagesSlice;
import com.exteragram.messenger.export.api.ApiWrap$SessionsList;
import com.exteragram.messenger.export.api.ApiWrap$StoriesSlice;
import com.exteragram.messenger.export.api.ApiWrap$UserpicsInfo;
import com.exteragram.messenger.export.controllers.ExportRequestsController;
import com.exteragram.messenger.export.output.AbstractWriter;
import com.exteragram.messenger.export.output.OutputFile;
import com.google.android.gms.cast.CastStatusCodes;
import j$.time.Instant;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BaseController;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.BulletinFactory;

/* loaded from: classes4.dex */
public class ExportController extends BaseController {
    public static final int CONTACTS_NOTIFICATION = 0;
    public static final int DIALOGS_LIST_NOTIFICATION = 0;
    public static final int DIALOGS_NOTIFICATION = 0;
    public static final int FINISH_NOTIFICATION = 0;
    public static final int INITIALIZATING_NOTIFICATION = 0;
    private static final ExportController[] Instance = null;
    public static final int OTHER_DATA_NOTIFICATION = 0;
    public static final int PERSONAL_INFO_NOTIFICATION = 0;
    public static final int SESSIONS_NOTIFICATION = 0;
    public static final int STORIES_NOTIFICATION = 0;
    public static final int USERPICS_NOTIFICATION = 0;
    private static int defaultId;
    public static volatile DispatchQueue exportQueue;
    private int _dialogIndex;
    private ApiWrap$DialogsInfo _dialogsInfo;
    private ProcessingState.Step _lastProcessingStep;
    private int _messagesCount;
    private int _messagesWritten;
    private ExportSettings _settings;
    private ProcessingState _state;
    private final OutputFile.Stats _stats;
    private int _stepIndex;
    private final ArrayList<ProcessingState.Step> _steps;
    private int _storiesCount;
    private int _storiesWritten;
    private int[] _substepsInStep;
    private int _substepsPassed;
    private int _substepsTotal;
    private int _userpicsCount;
    private int _userpicsWritten;
    private AbstractWriter _writer;
    private final int currAcc;

    /* renamed from: com.exteragram.messenger.export.controllers.ExportController$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type = null;
        static final /* synthetic */ int[] $SwitchMap$com$exteragram$messenger$export$controllers$ExportController$ProcessingState$Step = null;

        static {
            int[] r0 = new int[ApiWrap$DialogInfo.Type.values().length];
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type = r0;
            r0[ApiWrap$DialogInfo.Type.Self.ordinal()] = 1;     // Catch: NoSuchFieldError -> L19
        L33:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[ApiWrap$DialogInfo.Type.Replies.ordinal()] = 2;     // Catch: NoSuchFieldError -> L20
        L39:
            $SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[ApiWrap$DialogInfo.Type.VerifyCodes.ordinal()] = 3;     // Catch: NoSuchFieldError -> L21
        L8:
            int[] r3 = new int[ProcessingState.Step.values().length];
            $SwitchMap$com$exteragram$messenger$export$controllers$ExportController$ProcessingState$Step = r3;
            r3[ProcessingState.Step.Initializing.ordinal()] = 1;     // Catch: NoSuchFieldError -> L22
        L53:
            $SwitchMap$com$exteragram$messenger$export$controllers$ExportController$ProcessingState$Step[ProcessingState.Step.DialogsList.ordinal()] = 2;     // Catch: NoSuchFieldError -> L23
        L31:
            $SwitchMap$com$exteragram$messenger$export$controllers$ExportController$ProcessingState$Step[ProcessingState.Step.PersonalInfo.ordinal()] = 3;     // Catch: NoSuchFieldError -> L24
        L37:
            $SwitchMap$com$exteragram$messenger$export$controllers$ExportController$ProcessingState$Step[ProcessingState.Step.Userpics.ordinal()] = 4;     // Catch: NoSuchFieldError -> L25
        L51:
            $SwitchMap$com$exteragram$messenger$export$controllers$ExportController$ProcessingState$Step[ProcessingState.Step.Stories.ordinal()] = 5;     // Catch: NoSuchFieldError -> L26
        L35:
            $SwitchMap$com$exteragram$messenger$export$controllers$ExportController$ProcessingState$Step[ProcessingState.Step.Contacts.ordinal()] = 6;     // Catch: NoSuchFieldError -> L27
        L41:
            $SwitchMap$com$exteragram$messenger$export$controllers$ExportController$ProcessingState$Step[ProcessingState.Step.Sessions.ordinal()] = 7;     // Catch: NoSuchFieldError -> L28
        L45:
            $SwitchMap$com$exteragram$messenger$export$controllers$ExportController$ProcessingState$Step[ProcessingState.Step.OtherData.ordinal()] = 8;     // Catch: NoSuchFieldError -> L29
        L47:
            $SwitchMap$com$exteragram$messenger$export$controllers$ExportController$ProcessingState$Step[ProcessingState.Step.Dialogs.ordinal()] = 9;     // Catch: NoSuchFieldError -> L30
            return;
        }
    }

    public static class FinishedState extends ProcessingState {
        public long bytesCount;
        public int filesCount;
        public String path;

        public FinishedState(String r1, int r2, long r3) {
            this.path = r1;
            this.filesCount = r2;
            this.bytesCount = r3;
        }
    }

    public static class ProcessingState {
        public long bytesCount;
        public long bytesLoaded;
        public String bytesName;
        public long bytesRandomId;
        public int entityCount;
        public int entityIndex;
        public String entityName;
        public EntityType entityType;
        public int itemCount;
        public int itemIndex;
        public Step step;
        public int substepsNow;
        public int substepsPassed;
        public int substepsTotal;

        public enum EntityType extends Enum<EntityType> {
            private static final /* synthetic */ EntityType[] $VALUES = null;
            public static final EntityType Chat = null;
            public static final EntityType Other = null;
            public static final EntityType RepliesMessages = null;
            public static final EntityType SavedMessages = null;
            public static final EntityType VerifyCodes = null;

            private static /* synthetic */ EntityType[] $values() {
                return new EntityType[]{Chat, SavedMessages, RepliesMessages, VerifyCodes, Other};
            }

            static {
                Chat = new EntityType("Chat", 0);
                SavedMessages = new EntityType("SavedMessages", 1);
                RepliesMessages = new EntityType("RepliesMessages", 2);
                VerifyCodes = new EntityType("VerifyCodes", 3);
                Other = new EntityType("Other", 4);
                $VALUES = $values();
            }

            EntityType(String r1, int r2) {
            }

            public static EntityType valueOf(String r1) {
                return (EntityType) Enum.valueOf(EntityType.class, r1);
            }

            public static EntityType[] values() {
                return (EntityType[]) $VALUES.clone();
            }
        }

        public enum Step extends Enum<Step> {
            private static final /* synthetic */ Step[] $VALUES = null;
            public static final Step Contacts = null;
            public static final Step Dialogs = null;
            public static final Step DialogsList = null;
            public static final Step Initializing = null;
            public static final Step OtherData = null;
            public static final Step PersonalInfo = null;
            public static final Step Sessions = null;
            public static final Step Stories = null;
            public static final Step Userpics = null;

            private static /* synthetic */ Step[] $values() {
                return new Step[]{Initializing, DialogsList, PersonalInfo, Userpics, Stories, Contacts, Sessions, OtherData, Dialogs};
            }

            static {
                Initializing = new Step("Initializing", 0);
                DialogsList = new Step("DialogsList", 1);
                PersonalInfo = new Step("PersonalInfo", 2);
                Userpics = new Step("Userpics", 3);
                Stories = new Step("Stories", 4);
                Contacts = new Step("Contacts", 5);
                Sessions = new Step("Sessions", 6);
                OtherData = new Step("OtherData", 7);
                Dialogs = new Step("Dialogs", 8);
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

        public ProcessingState() {
            this.step = Step.Initializing;
            this.substepsPassed = 0;
            this.substepsNow = 0;
            this.substepsTotal = 0;
            this.entityType = EntityType.Other;
            this.entityIndex = 0;
            this.entityCount = 0;
            this.itemIndex = 0;
            this.itemCount = 0;
            this.bytesRandomId = 0;
            this.bytesLoaded = 0;
            this.bytesCount = 0;
        }
    }

    /* renamed from: $r8$lambda$1vTWbNIE-P_0GYcHkCs9WhcFHPs, reason: not valid java name */
    public static /* synthetic */ Boolean m1310$r8$lambda$1vTWbNIEP_0GYcHkCs9WhcFHPs(ExportController r0, ApiWrap$MessagesSlice r1) {
        return r0.lambda$exportNextDialog$23(r1);
    }

    public static /* synthetic */ void $r8$lambda$2CkpanCnEQtmn2056oqbPRfHrhA(ExportController r0, ApiWrap$File r1) {
        r0.lambda$exportOtherData$19(r1);
    }

    /* renamed from: $r8$lambda$4pgjlL5vAJ3NQrAMql0spwl-QxU, reason: not valid java name */
    public static /* synthetic */ void m1311$r8$lambda$4pgjlL5vAJ3NQrAMql0spwlQxU(ExportController r0, ApiWrap$DownloadProgress r1, ProcessingState r2) {
        r0.lambda$stateStories$14(r1, r2);
    }

    /* renamed from: $r8$lambda$5-Drlw8M_3grG-J4daaIieG2noU, reason: not valid java name */
    public static /* synthetic */ void m1312$r8$lambda$5Drlw8M_3grGJ4daaIieG2noU(ExportController r0) {
        r0.lambda$exportNextDialog$24();
    }

    public static /* synthetic */ Boolean $r8$lambda$6MrT_DFEPQFUcKkItFFjwvSlgyw(ExportController r0, ArrayList r1) {
        return r0.lambda$exportUserpics$7(r1);
    }

    public static /* synthetic */ Boolean $r8$lambda$6vcKJvfeKZe1GN32HqOUFlpBLpk(ExportController r0, ApiWrap$DialogInfo r1, ApiWrap$DialogInfo r2) {
        return r0.lambda$exportNextDialog$21(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$9OkVqKAVzJgO8Kok_rwrVRcQScw(ExportController r0, ApiWrap$ContactsList r1) {
        r0.lambda$exportContacts$15(r1);
    }

    public static /* synthetic */ Boolean $r8$lambda$DKJJ4VAJs2c8iBwkkSoBByaY3Dc(ExportController r0, Integer r1) {
        return r0.lambda$exportStories$10(r1);
    }

    /* renamed from: $r8$lambda$HTKiN3r-fZZIUD3qWnCgokFgrig, reason: not valid java name */
    public static /* synthetic */ Boolean m1313$r8$lambda$HTKiN3rfZZIUD3qWnCgokFgrig(ExportController r0, ApiWrap$DownloadProgress r1) {
        return r0.lambda$exportNextDialog$22(r1);
    }

    public static /* synthetic */ void $r8$lambda$IaUuSoZUK_XMpLghjgYflpuOeVA(ExportController r0, ApiWrap$DownloadProgress r1, ProcessingState r2) {
        r0.lambda$stateDialogs$25(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$SRYvSRJ9Wg3CtCrG80qknEGvH4Y(ExportController r0, ApiWrap$DownloadProgress r1, ProcessingState r2) {
        r0.lambda$stateUserpics$9(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$UUieFV5bzMxf6WEnWND13kFOujs(ExportController r0, int r1, ProcessingState r2) {
        r0.lambda$setState$17(r1, r2);
    }

    public static /* synthetic */ Boolean $r8$lambda$Xfh6AoMzgThSjyiMoEX7OoHmW6g(ExportController r0, Integer r1) {
        return r0.lambda$collectDialogsList$1(r1);
    }

    /* renamed from: $r8$lambda$XpHL9OJ9E0-jsaOwD7m1Z3whHP0, reason: not valid java name */
    public static /* synthetic */ void m1314$r8$lambda$XpHL9OJ9E0jsaOwD7m1Z3whHP0(ProcessingState r0) {
    }

    public static /* synthetic */ int $r8$lambda$Ywqr27C9wSD1pgQnpmJt0sTma0A(Integer r0) {
        return r0.intValue();
    }

    /* renamed from: $r8$lambda$avxSQSkrqEmXXj5mCjCNv-kb80k, reason: not valid java name */
    public static /* synthetic */ void m1315$r8$lambda$avxSQSkrqEmXXj5mCjCNvkb80k(ExportController r0) {
        r0.lambda$exportStories$13();
    }

    /* renamed from: $r8$lambda$c-5VFw32jteOe7WKS-yF2w2PJkE, reason: not valid java name */
    public static /* synthetic */ void m1316$r8$lambda$c5VFw32jteOe7WKSyF2w2PJkE(ExportController r0, ApiWrap$ExportPersonalInfo r1) {
        r0.lambda$exportPersonalInfo$3(r1);
    }

    /* renamed from: $r8$lambda$fZk-TRFsODCGO_pjHri91mIkkWA, reason: not valid java name */
    public static /* synthetic */ void m1317$r8$lambda$fZkTRFsODCGO_pjHri91mIkkWA(ExportController r0, ExportSettings r1) {
        r0.lambda$startExport$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$g2LYN_OmcDZMv9Hn17RZTpPNjRo(ExportController r0, int r1, ProcessingState r2) {
        r0.lambda$stateDialogsList$16(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$jX3GCnwF282x1HauPM7SqZYUQP4(ExportController r0, ApiWrap$DialogsInfo r1) {
        r0.lambda$collectDialogsList$2(r1);
    }

    public static /* synthetic */ Boolean $r8$lambda$ju1uDaRmER1W0NbiPJrVY7ZwxIQ(ExportController r0, ApiWrap$DownloadProgress r1) {
        return r0.lambda$exportStories$11(r1);
    }

    /* renamed from: $r8$lambda$lNQA-ZereOF7cyje1qIxIaHg0hc, reason: not valid java name */
    public static /* synthetic */ Boolean m1318$r8$lambda$lNQAZereOF7cyje1qIxIaHg0hc(ExportController r0, ApiWrap$DownloadProgress r1) {
        return r0.lambda$exportUserpics$6(r1);
    }

    /* renamed from: $r8$lambda$o3Iuo6s-kLkQ462-_7EmXohms_s, reason: not valid java name */
    public static /* synthetic */ void m1319$r8$lambda$o3Iuo6skLkQ462_7EmXohms_s(ExportController r0, ApiWrap$SessionsList r1) {
        r0.lambda$exportSessions$18(r1);
    }

    public static /* synthetic */ void $r8$lambda$p3zzoYCgtfm4Sbq7Tdg0jNU3diM(ExportController r0) {
        r0.setFinishedState();
    }

    /* renamed from: $r8$lambda$q-6s1TwlpUNnTl0bS-5WO8sZvVI, reason: not valid java name */
    public static /* synthetic */ Boolean m1320$r8$lambda$q6s1TwlpUNnTl0bS5WO8sZvVI(ExportController r0, ApiWrap$UserpicsInfo r1) {
        return r0.lambda$exportUserpics$5(r1);
    }

    /* renamed from: $r8$lambda$qvbYz1nv93kC1zzlTvlCim-Rrwc, reason: not valid java name */
    public static /* synthetic */ void m1321$r8$lambda$qvbYz1nv93kC1zzlTvlCimRrwc(ExportController r0, ExportRequestsController.StartInfo r1) {
        r0.initialized(r1);
    }

    /* renamed from: $r8$lambda$r7h25DgZCkTV8--e3TXmGiS9eTk, reason: not valid java name */
    public static /* synthetic */ Boolean m1322$r8$lambda$r7h25DgZCkTV8e3TXmGiS9eTk(ExportController r0, ApiWrap$StoriesSlice r1) {
        return r0.lambda$exportStories$12(r1);
    }

    public static /* synthetic */ void $r8$lambda$ruhmQNJAvsK3HI5z_EaaebRWhvI(ExportController r0) {
        r0.lambda$exportUserpics$8();
    }

    static {
        exportQueue = new DispatchQueue("exportQueue");
        Instance = new ExportController[16];
        INITIALIZATING_NOTIFICATION = 6666;
        int r1 = 6666 + 1;
        DIALOGS_LIST_NOTIFICATION = 6666;
        PERSONAL_INFO_NOTIFICATION = r1;
        USERPICS_NOTIFICATION = r1 + 1;
        STORIES_NOTIFICATION = r1 + 2;
        CONTACTS_NOTIFICATION = r1 + 3;
        SESSIONS_NOTIFICATION = r1 + 4;
        OTHER_DATA_NOTIFICATION = r1 + 5;
        int r0 = r1 + 7;
        DIALOGS_NOTIFICATION = r1 + 6;
        defaultId = r1 + 8;
        FINISH_NOTIFICATION = r0;
    }

    public ExportController(int r3) {
        super(r3);
        this._steps = new ArrayList();
        this._stats = new OutputFile.Stats();
        this._stepIndex = -1;
        this._substepsTotal = 0;
        this._substepsPassed = 0;
        this._dialogIndex = -1;
        this._lastProcessingStep = ProcessingState.Step.Initializing;
        this._messagesWritten = 0;
        this._messagesCount = 0;
        this._userpicsWritten = 0;
        this._userpicsCount = 0;
        this._storiesWritten = 0;
        this._storiesCount = 0;
        this.currAcc = r3;
    }

    private static String NormalizePath(ExportSettings r4) {
        File r0 = new File(String.valueOf(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "exteraGram")));
        String r1 = r0.getAbsolutePath();
        if (r1.endsWith("/") == true) goto L7;
        r1 = r1.concat("/");
    L7:
        if (r0.exists() == false) goto L20;
        String[] r02 = r0.list();
        if (r02 == null) goto L20;
        if (r02.length == 0) goto L20;
        String r03 = new SimpleDateFormat("yyyy-MM-dd_HH_mm").format(Calendar.getInstance().getTime());
        if (r4.onlySinglePeer() == false) goto L17;
        String r42 = "ChatExport_" + r03;
    L19:
        return r1.concat(r42);
    L17:
        r42 = "DataExport_" + r03;
    L20:
        return r1;
    }

    private void collectDialogsList() {
        setState(stateDialogsList(0));
        ExportRequestsController.getInstance(this.currAcc).requestDialogsList(new ExportController$$ExternalSyntheticLambda19(this), new ExportController$$ExternalSyntheticLambda20(this));
    }

    private void exportContacts() {
        setState(prepareState(ProcessingState.Step.Contacts, null));
        ExportRequestsController.getInstance(this.currAcc).requestContacts(new ExportController$$ExternalSyntheticLambda7(this));
    }

    private void exportDialogs() {
        if (this._writer.writeDialogsStart(this._dialogsInfo).isSuccess() == true) goto L5;
        return;
    L5:
        exportNextDialog();
    }

    private void exportNext() {
        int r0 = this._stepIndex + 1;
        this._stepIndex = r0;
        if (r0 >= this._steps.size()) goto L5;
        ProcessingState.Step r02 = this._steps.get(this._stepIndex);
        switch(AnonymousClass1.$SwitchMap$com$exteragram$messenger$export$controllers$ExportController$ProcessingState$Step[r02.ordinal()]) {
            case 1: goto L28;
            case 2: goto L26;
            case 3: goto L24;
            case 4: goto L22;
            case 5: goto L20;
            case 6: goto L18;
            case 7: goto L16;
            case 8: goto L14;
            case 9: goto L12;
            default: goto L11;
        };
    L11:
        return;
    L12:
        exportDialogs();
        return;
    L14:
        exportOtherData();
        return;
    L16:
        exportSessions();
        return;
    L18:
        exportContacts();
        return;
    L20:
        exportStories();
        return;
    L22:
        exportUserpics();
        return;
    L24:
        exportPersonalInfo();
        return;
    L26:
        collectDialogsList();
        return;
    L28:
        initialize();
        return;
    L5:
        if (this._writer.finish().isSuccess() == false) goto L30;
        ExportRequestsController.getInstance(this.currAcc).invokeFinish(false, new ExportController$$ExternalSyntheticLambda1(this));
        Log.i("exteraGram", "finished Export!");
        return;
    }

    private void exportNextDialog() {
        int r0 = this._dialogIndex + 1;
        this._dialogIndex = r0;
        ApiWrap$DialogInfo r3 = this._dialogsInfo.getItemAt(r0);
        if (r3 == null) goto L7;
        ExportRequestsController.getInstance(this.currAcc).requestMessages(r3, new ExportController$$ExternalSyntheticLambda8(this, r3), new ExportController$$ExternalSyntheticLambda9(this), new ExportController$$ExternalSyntheticLambda10(this), new ExportController$$ExternalSyntheticLambda11(this));
        return;
    L7:
        if (this._writer.writeDialogsEnd().isSuccess() == true) goto L9;
        return;
    L9:
        exportNext();
    }

    private void exportOtherData() {
        setState(prepareState(ProcessingState.Step.OtherData, null));
        ExportRequestsController.getInstance(this.currAcc).requestOtherData("lists/other_data.json", new ExportController$$ExternalSyntheticLambda12(this));
    }

    private void exportPersonalInfo() {
        setState(statePersonalInfo());
        ExportRequestsController.getInstance(this.currAcc).requestPersonalInfo(new ExportController$$ExternalSyntheticLambda17(this));
    }

    private void exportSessions() {
        setState(prepareState(ProcessingState.Step.Sessions, null));
        ExportRequestsController.getInstance(this.currAcc).requestSessions(new ExportController$$ExternalSyntheticLambda2(this));
    }

    private void exportStories() {
        ExportRequestsController.getInstance(this.currAcc).requestStories(new ExportController$$ExternalSyntheticLambda3(this), new ExportController$$ExternalSyntheticLambda4(this), new ExportController$$ExternalSyntheticLambda5(this), new ExportController$$ExternalSyntheticLambda6(this));
    }

    private void exportUserpics() {
        ExportRequestsController.getInstance(this.currAcc).requestUserpics(new ExportController$$ExternalSyntheticLambda13(this), new ExportController$$ExternalSyntheticLambda14(this), new ExportController$$ExternalSyntheticLambda15(this), new ExportController$$ExternalSyntheticLambda16(this));
    }

    private void fillMessagesState(ProcessingState r3, ApiWrap$DialogsInfo r4, int r5, ApiWrap$DownloadProgress r6) {
        ApiWrap$DialogInfo r0 = r4.getItemAt(r5);
        r3.entityIndex = r5;
        r3.entityCount = r4.chats.size() + r4.left.size();
        r3.entityName = r0.name;
        int r42 = AnonymousClass1.$SwitchMap$com$exteragram$messenger$export$api$ApiWrap$DialogInfo$Type[r0.type.ordinal()];
        if (r42 != 1) goto L5;
        r3.entityType = ProcessingState.EntityType.SavedMessages;
    L12:
        int r43 = this._messagesWritten + r6.itemIndex();
        r3.itemIndex = r43;
        r3.itemCount = Math.max(this._messagesCount, r43);
        r3.bytesRandomId = r6.randomId();
        if (r6.path().isEmpty() == true) goto L15;
        int r2 = r6.path().lastIndexOf(47);
        r3.bytesName = r6.path().substring(r2 + 1);
    L15:
        r3.bytesLoaded = r6.ready();
        r3.bytesCount = r6.total();
        return;
    L5:
        if (r42 != 2) goto L7;
        r3.entityType = ProcessingState.EntityType.RepliesMessages;
        goto L12
    L7:
        if (r42 == 3) goto L9;
        r3.entityType = ProcessingState.EntityType.Chat;
        goto L12
    L9:
        r3.entityType = ProcessingState.EntityType.VerifyCodes;
        goto L12
    }

    private void fillSubstepsInSteps(ExportRequestsController.StartInfo r4) {
        int[] r0 = new int[ProcessingState.Step.values().length];
        r0[ProcessingState.Step.Initializing.ordinal()] = 1;
        if ((this._settings.types & CastStatusCodes.DEVICE_CONNECTION_SUSPENDED) == 0) goto L6;
        r0[ProcessingState.Step.DialogsList.ordinal()] = 1;
    L6:
        if ((this._settings.types & 32) == 0) goto L9;
        r0[ProcessingState.Step.PersonalInfo.ordinal()] = 1;
    L9:
        if ((this._settings.types & 2) == 0) goto L12;
        r0[ProcessingState.Step.Userpics.ordinal()] = 1;
    L12:
        if ((this._settings.types & 2048) == 0) goto L15;
        r0[ProcessingState.Step.Stories.ordinal()] = 1;
    L15:
        if ((this._settings.types & 4) == 0) goto L18;
        r0[ProcessingState.Step.Contacts.ordinal()] = 1;
    L18:
        if ((this._settings.types & 8) == 0) goto L21;
        r0[ProcessingState.Step.Sessions.ordinal()] = 1;
    L21:
        if ((this._settings.types & 16) == 0) goto L24;
        r0[ProcessingState.Step.OtherData.ordinal()] = 1;
    L24:
        if ((this._settings.types & CastStatusCodes.DEVICE_CONNECTION_SUSPENDED) == 0) goto L26;
        r0[ProcessingState.Step.Dialogs.ordinal()] = r4.dialogsCount;
    L26:
        this._substepsInStep = r0;
        this._substepsTotal = Arrays.stream(r0).sum();
    }

    public static ExportController getInstance(int r3) {
        ExportController[] r0 = Instance;
        ExportController r1 = r0[r3];
        if (r1 == null) goto L5;
        return r1;
    L5:
        monitor-enter(ExportController.class);
        ExportController r12 = r0[r3];     // Catch: Throwable -> L9
        if (r12 != null) goto L11;
        r12 = new ExportController(r3);     // Catch: Throwable -> L9
        r0[r3] = r12;     // Catch: Throwable -> L9
    L11:
        monitor-exit(ExportController.class);     // Catch: Throwable -> L9
        return r12;
    L9:
        th = move-exception;
        throw th;
    }

    private void initialize() {
        setState(new ProcessingState());
        ExportRequestsController.getInstance(this.currAcc).startExport(this._settings, this._stats, new ExportController$$ExternalSyntheticLambda18(this));
    }

    private void initialized(ExportRequestsController.StartInfo r4) {
        if (this._writer.start(this._settings, this._stats).isSuccess() == true) goto L5;
        return;
    L5:
        fillSubstepsInSteps(r4);
        exportNext();
    }

    private /* synthetic */ Boolean lambda$collectDialogsList$1(Integer r2) {
        if (r2.intValue() <= 0) goto L6;
        setState(stateDialogsList(r2.intValue() - 1));
    L6:
        return Boolean.TRUE;
    }

    private /* synthetic */ void lambda$collectDialogsList$2(ApiWrap$DialogsInfo r1) {
        this._dialogsInfo = r1;
        exportNext();
    }

    private /* synthetic */ void lambda$exportContacts$15(ApiWrap$ContactsList r2) {
        if (this._writer.writeContactsList(r2).isSuccess() == true) goto L5;
        return;
    L5:
        exportNext();
    }

    private /* synthetic */ Boolean lambda$exportNextDialog$21(ApiWrap$DialogInfo r2, ApiWrap$DialogInfo r3) {
        if (this._writer.writeDialogStart(r2).isSuccess() == false) goto L5;
        this._messagesWritten = 0;
        this._messagesCount = r3.messagesCountPerSplit.stream().mapToInt(new ExportController$$ExternalSyntheticLambda27()).sum();
        setState(stateDialogs(new ApiWrap$DownloadProgress()));
        return Boolean.TRUE;
    L5:
        return Boolean.FALSE;
    }

    private /* synthetic */ Boolean lambda$exportNextDialog$22(ApiWrap$DownloadProgress r1) {
        setState(stateDialogs(r1));
        return Boolean.TRUE;
    }

    private /* synthetic */ Boolean lambda$exportNextDialog$23(ApiWrap$MessagesSlice r2) {
        if (this._writer.writeDialogSlice(r2).isSuccess() == false) goto L5;
        this._messagesWritten += r2.list.size();
        setState(stateDialogs(new ApiWrap$DownloadProgress()));
        return Boolean.TRUE;
    L5:
        return Boolean.FALSE;
    }

    private /* synthetic */ void lambda$exportNextDialog$24() {
        if (this._writer.writeDialogEnd().isSuccess() == true) goto L5;
        return;
    L5:
        exportNextDialog();
    }

    private /* synthetic */ void lambda$exportOtherData$19(ApiWrap$File r2) {
        if (this._writer.writeOtherData(r2).isSuccess() == true) goto L5;
        return;
    L5:
        exportNext();
    }

    private /* synthetic */ void lambda$exportPersonalInfo$3(ApiWrap$ExportPersonalInfo r2) {
        if (this._writer.writePersonal(r2).isSuccess() == true) goto L5;
        return;
    L5:
        exportNext();
    }

    private /* synthetic */ void lambda$exportSessions$18(ApiWrap$SessionsList r2) {
        if (this._writer.writeSessionsList(r2).isSuccess() == true) goto L5;
        return;
    L5:
        exportNext();
    }

    private /* synthetic */ Boolean lambda$exportStories$10(Integer r3) {
        if (this._writer.writeStoriesStart(r3.intValue()).isSuccess() == false) goto L5;
        this._storiesWritten = 0;
        this._storiesCount = r3.intValue();
        return Boolean.TRUE;
    L5:
        return Boolean.FALSE;
    }

    private /* synthetic */ Boolean lambda$exportStories$11(ApiWrap$DownloadProgress r1) {
        setState(stateStories(r1));
        return Boolean.TRUE;
    }

    private /* synthetic */ Boolean lambda$exportStories$12(ApiWrap$StoriesSlice r2) {
        if (this._writer.writeStoriesSlice(r2).isSuccess() == false) goto L5;
        this._storiesWritten += r2.list.size();
        setState(stateStories(new ApiWrap$DownloadProgress()));
        return Boolean.TRUE;
    L5:
        return Boolean.FALSE;
    }

    private /* synthetic */ void lambda$exportStories$13() {
        if (this._writer.writeStoriesEnd().isSuccess() == true) goto L5;
        return;
    L5:
        exportNext();
    }

    private /* synthetic */ Boolean lambda$exportUserpics$5(ApiWrap$UserpicsInfo r2) {
        if (this._writer.writeUserpicsStart(r2).isSuccess() == false) goto L5;
        this._userpicsWritten = 0;
        this._userpicsCount = r2.count();
        return Boolean.TRUE;
    L5:
        return Boolean.FALSE;
    }

    private /* synthetic */ Boolean lambda$exportUserpics$6(ApiWrap$DownloadProgress r1) {
        setState(stateUserpics(r1));
        return Boolean.TRUE;
    }

    private /* synthetic */ Boolean lambda$exportUserpics$7(ArrayList r2) {
        if (this._writer.writeUserpicsSlice(r2).isSuccess() == false) goto L5;
        this._userpicsWritten += r2.size();
        setState(stateUserpics(new ApiWrap$DownloadProgress()));
        return Boolean.TRUE;
    L5:
        return Boolean.FALSE;
    }

    private /* synthetic */ void lambda$exportUserpics$8() {
        if (this._writer.writeUserpicsEnd().isSuccess() == true) goto L5;
        return;
    L5:
        exportNext();
    }

    private /* synthetic */ void lambda$setState$17(int r1, ProcessingState r2) {
        NotificationCenter.getInstance(this.currAcc).postNotificationName(r1, new Object[]{r2});
    }

    private /* synthetic */ void lambda$startExport$0(ExportSettings r1) {
        startExportInternal(r1);
    }

    private /* synthetic */ void lambda$stateDialogs$25(ApiWrap$DownloadProgress r3, ProcessingState r4) {
        fillMessagesState(r4, this._dialogsInfo, this._dialogIndex, r3);
    }

    private /* synthetic */ void lambda$stateDialogsList$16(int r2, ProcessingState r3) {
        r3.entityIndex = r2;
        r3.entityCount = Math.max(r2, substepsInStep(ProcessingState.Step.Dialogs));
    }

    private /* synthetic */ void lambda$stateStories$14(ApiWrap$DownloadProgress r3, ProcessingState r4) {
        int r0 = this._storiesWritten + r3.itemIndex();
        r4.entityIndex = r0;
        r4.entityCount = Math.max(this._storiesCount, r0);
        r4.bytesRandomId = r3.randomId();
        if (r3.path().isEmpty() == true) goto L5;
        int r2 = r3.path().lastIndexOf(47);
        r4.bytesName = r3.path().substring(r2 + 1);
    L5:
        r4.bytesLoaded = r3.ready();
        r4.bytesCount = r3.total();
    }

    private /* synthetic */ void lambda$stateUserpics$9(ApiWrap$DownloadProgress r3, ProcessingState r4) {
        int r0 = this._userpicsWritten + r3.itemIndex();
        r4.entityIndex = r0;
        r4.entityCount = Math.max(this._userpicsCount, r0);
        r4.bytesRandomId = r3.randomId();
        if (r3.path().isEmpty() == true) goto L5;
        int r2 = r3.path().lastIndexOf(47);
        r4.bytesName = r3.path().substring(r2 + 1);
    L5:
        r4.bytesLoaded = r3.ready();
        r4.bytesCount = r3.total();
    }

    private ProcessingState prepareState(ProcessingState.Step r3, Utilities.Callback<ProcessingState> r4) {
        ProcessingState.Step r0 = this._lastProcessingStep;
        if (r3 == r0) goto L5;
        this._substepsPassed += substepsInStep(r0);
        this._lastProcessingStep = r3;
    L5:
        ProcessingState r02 = new ProcessingState();
        if (r4 == null) goto L8;
        r4.run(r02);
    L8:
        r02.step = r3;
        r02.substepsPassed = this._substepsPassed;
        r02.substepsNow = substepsInStep(this._lastProcessingStep);
        r02.substepsTotal = this._substepsTotal;
        return r02;
    }

    private void setFinishedState() {
        setState(new FinishedState(this._writer.mainFilePath(), this._stats.filesCount(), this._stats.bytesCount()));
    }

    private void setState(ProcessingState r3) {
        if (stopped() == false) goto L6;
        return;
    L6:
        if ((r3 instanceof FinishedState) == false) goto L9;
        int r0 = FINISH_NOTIFICATION;
    L22:
        if (r0 == (-1)) goto L24;
        AndroidUtilities.runOnUIThread(new ExportController$$ExternalSyntheticLambda21(this, r0, r3));
    L24:
        this._state = r3;
        return;
    L9:
        switch(AnonymousClass1.$SwitchMap$com$exteragram$messenger$export$controllers$ExportController$ProcessingState$Step[r3.step.ordinal()]) {
            case 1: goto L20;
            case 2: goto L19;
            case 3: goto L18;
            case 4: goto L17;
            case 5: goto L16;
            case 6: goto L15;
            case 7: goto L14;
            case 8: goto L13;
            case 9: goto L12;
            default: goto L11;
        };
    L12:
        r0 = DIALOGS_NOTIFICATION;
        goto L22
    L13:
        r0 = OTHER_DATA_NOTIFICATION;
        goto L22
    L14:
        r0 = SESSIONS_NOTIFICATION;
        goto L22
    L15:
        r0 = CONTACTS_NOTIFICATION;
        goto L22
    L16:
        r0 = STORIES_NOTIFICATION;
        goto L22
    L17:
        r0 = USERPICS_NOTIFICATION;
        goto L22
    L18:
        r0 = PERSONAL_INFO_NOTIFICATION;
        goto L22
    L19:
        r0 = DIALOGS_LIST_NOTIFICATION;
        goto L22
    L20:
        r0 = INITIALIZATING_NOTIFICATION;
        goto L22
    L11:
        throw new IncompatibleClassChangeError();
    }

    public static void showError(TLRPC.TL_error r3) {
        if (r3.text.contains("TAKEOUT_INVALID") == false) goto L6;
        showErrorBulletin(LocaleController.getString(R.string.ExportInvalid));
        return;
    L6:
        boolean r0 = r3.text.startsWith("TAKEOUT_INIT_DELAY_");
        String r32 = r3.text;
        if (r0 == false) goto L14;
        int r33 = Utilities.parseInt(r32.substring(r32.lastIndexOf("_")));
        Instant r02 = Instant.now();
        r02.plusSeconds(r33);
        if ((r33 / 3600) > 0) goto L11;
        String r34 = LocaleController.getString(R.string.ExportDelayLessThanHour);
    L12:
        showErrorBulletin(LocaleController.formatString(R.string.ExportDelay, new Object[]{r34, r02.toString()}));
        return;
    L11:
        r34 = LocaleController.getString(R.string.Hours_other);
        goto L12
    L14:
        showErrorBulletin("API error happened! Error text: " + r32);
    }

    private static void showErrorBulletin(String r1) {
        BulletinFactory.global().createErrorBulletin(r1);
    }

    private ProcessingState stateDialogs(ApiWrap$DownloadProgress r3) {
        return prepareState(ProcessingState.Step.Dialogs, new ExportController$$ExternalSyntheticLambda24(this, r3));
    }

    private ProcessingState stateDialogsList(int r3) {
        return prepareState(ProcessingState.Step.DialogsList, new ExportController$$ExternalSyntheticLambda22(this, r3));
    }

    private ProcessingState statePersonalInfo() {
        return prepareState(ProcessingState.Step.PersonalInfo, new ExportController$$ExternalSyntheticLambda23());
    }

    private ProcessingState stateStories(ApiWrap$DownloadProgress r3) {
        return prepareState(ProcessingState.Step.Stories, new ExportController$$ExternalSyntheticLambda26(this, r3));
    }

    private ProcessingState stateUserpics(ApiWrap$DownloadProgress r3) {
        return prepareState(ProcessingState.Step.Userpics, new ExportController$$ExternalSyntheticLambda25(this, r3));
    }

    private boolean stopped() {
        return this._state instanceof FinishedState;
    }

    private int substepsInStep(ProcessingState.Step r1) {
        return this._substepsInStep[r1.ordinal()];
    }

    public void fillExportSteps() {
        this._steps.add(ProcessingState.Step.Initializing);
        if (this._settings.onlySinglePeer() == true) goto L6;
        this._steps.add(ProcessingState.Step.DialogsList);
    L6:
        if ((this._settings.types & 1) == 0) goto L9;
        this._steps.add(ProcessingState.Step.PersonalInfo);
    L9:
        if ((this._settings.types & 2) == 0) goto L12;
        this._steps.add(ProcessingState.Step.Userpics);
    L12:
        if ((this._settings.types & 2048) == 0) goto L15;
        this._steps.add(ProcessingState.Step.Stories);
    L15:
        if ((this._settings.types & 4) == 0) goto L18;
        this._steps.add(ProcessingState.Step.Contacts);
    L18:
        if ((this._settings.types & 8) == 0) goto L21;
        this._steps.add(ProcessingState.Step.Sessions);
    L21:
        if ((this._settings.types & 16) == 0) goto L24;
        this._steps.add(ProcessingState.Step.OtherData);
    L24:
        if (this._settings.onlySinglePeer() == true) goto L27;
        this._steps.add(ProcessingState.Step.Dialogs);
        return;
    }

    public void startExport(ExportSettings r3) {
        exportQueue.postRunnable(new ExportController$$ExternalSyntheticLambda0(this, r3));
    }

    public void startExportInternal(ExportSettings r2) {
        ExportSettings r0 = this._settings;
        if (r0 == null) goto L10;
        String r02 = r0.path;
        if (r02 == null) goto L10;
        if (r02.isEmpty() == true) goto L10;
        showErrorBulletin("path is not empty! aborting");
        return;
    L10:
        this._settings = r2;
        r2.path = NormalizePath(r2);
        this._writer = AbstractWriter.CreateWriter(this._settings.format);
        fillExportSteps();
        exportNext();
    }
}
