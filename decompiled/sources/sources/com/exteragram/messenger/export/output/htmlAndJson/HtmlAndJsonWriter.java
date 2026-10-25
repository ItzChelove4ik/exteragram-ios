package com.exteragram.messenger.export.output.htmlAndJson;

import com.exteragram.messenger.export.ExportSettings;
import com.exteragram.messenger.export.api.ApiWrap$ContactsList;
import com.exteragram.messenger.export.api.ApiWrap$DialogInfo;
import com.exteragram.messenger.export.api.ApiWrap$DialogsInfo;
import com.exteragram.messenger.export.api.ApiWrap$ExportPersonalInfo;
import com.exteragram.messenger.export.api.ApiWrap$File;
import com.exteragram.messenger.export.api.ApiWrap$MessagesSlice;
import com.exteragram.messenger.export.api.ApiWrap$SessionsList;
import com.exteragram.messenger.export.api.ApiWrap$StoriesSlice;
import com.exteragram.messenger.export.api.ApiWrap$UserpicsInfo;
import com.exteragram.messenger.export.output.AbstractWriter;
import com.exteragram.messenger.export.output.OutputFile;
import com.exteragram.messenger.export.output.html.HtmlWriter;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.Utilities;

/* loaded from: classes4.dex */
public class HtmlAndJsonWriter extends AbstractWriter {
    private final ArrayList<AbstractWriter> _writers;

    /* renamed from: $r8$lambda$3gu-hQPfyife15g7zpDtnOVNEys, reason: not valid java name */
    public static /* synthetic */ AbstractWriter.Result m1341$r8$lambda$3guhQPfyife15g7zpDtnOVNEys(AbstractWriter r0) {
        return r0.writeDialogsEnd();
    }

    public static /* synthetic */ AbstractWriter.Result $r8$lambda$6wBuDGGjg_hlUAUCFBOiPYIbeNg(ApiWrap$StoriesSlice r0, AbstractWriter r1) {
        return r1.writeStoriesSlice(r0);
    }

    public static /* synthetic */ AbstractWriter.Result $r8$lambda$I8WxLNsVd8fFELONOX7gJO0tNy4(ApiWrap$UserpicsInfo r0, AbstractWriter r1) {
        return r1.writeUserpicsStart(r0);
    }

    /* renamed from: $r8$lambda$Lumeha3T-7j6fUgL8CBH5vPSVNg, reason: not valid java name */
    public static /* synthetic */ AbstractWriter.Result m1342$r8$lambda$Lumeha3T7j6fUgL8CBH5vPSVNg(AbstractWriter r0) {
        return r0.writeUserpicsEnd();
    }

    public static /* synthetic */ AbstractWriter.Result $r8$lambda$QTuvSgS3aodfl03O7xiBMAATMgc(ArrayList r0, AbstractWriter r1) {
        return r1.writeUserpicsSlice(r0);
    }

    /* renamed from: $r8$lambda$RLxsoRa2hADVF8-7l9wLHqPbxsI, reason: not valid java name */
    public static /* synthetic */ AbstractWriter.Result m1343$r8$lambda$RLxsoRa2hADVF87l9wLHqPbxsI(ApiWrap$MessagesSlice r0, AbstractWriter r1) {
        return r1.writeDialogSlice(r0);
    }

    /* renamed from: $r8$lambda$SbqGkiS3pyZL41eR-7iQXpuaUFk, reason: not valid java name */
    public static /* synthetic */ AbstractWriter.Result m1344$r8$lambda$SbqGkiS3pyZL41eR7iQXpuaUFk(AbstractWriter r0) {
        return r0.writeDialogEnd();
    }

    public static /* synthetic */ AbstractWriter.Result $r8$lambda$YREH2Vh3GDNzKVYLvw3YvKLFGM0(ApiWrap$DialogsInfo r0, AbstractWriter r1) {
        return r1.writeDialogsStart(r0);
    }

    public static /* synthetic */ AbstractWriter.Result $r8$lambda$bjQVHhq8tbWZ7TRoqp5R12d2xH0(ApiWrap$ExportPersonalInfo r0, AbstractWriter r1) {
        return r1.writePersonal(r0);
    }

    public static /* synthetic */ AbstractWriter.Result $r8$lambda$fNgC4gb7ov2ibePgxOdQklaJoGs(ApiWrap$SessionsList r0, AbstractWriter r1) {
        return r1.writeSessionsList(r0);
    }

    /* renamed from: $r8$lambda$fn9U61tF-SmhKIkS1sJwSEBH3eI, reason: not valid java name */
    public static /* synthetic */ AbstractWriter.Result m1345$r8$lambda$fn9U61tFSmhKIkS1sJwSEBH3eI(ApiWrap$File r0, AbstractWriter r1) {
        return r1.writeOtherData(r0);
    }

    public static /* synthetic */ AbstractWriter.Result $r8$lambda$gZ_se4ka8JnnkRleO2lyuUNi89k(ExportSettings r0, OutputFile.Stats r1, AbstractWriter r2) {
        return r2.start(r0, r1);
    }

    /* renamed from: $r8$lambda$m23U2o83h58G8g-3iycR7-cJZkA, reason: not valid java name */
    public static /* synthetic */ AbstractWriter.Result m1346$r8$lambda$m23U2o83h58G8g3iycR7cJZkA(AbstractWriter r0) {
        return r0.writeStoriesEnd();
    }

    public static /* synthetic */ AbstractWriter.Result $r8$lambda$n92pwSSjw9aEybbE7Z72SvizRnU(AbstractWriter r0) {
        return r0.finish();
    }

    /* renamed from: $r8$lambda$ovLz6xoubThhEu1Egx-J6-yBH44, reason: not valid java name */
    public static /* synthetic */ AbstractWriter.Result m1347$r8$lambda$ovLz6xoubThhEu1EgxJ6yBH44(ApiWrap$ContactsList r0, AbstractWriter r1) {
        return r1.writeContactsList(r0);
    }

    /* renamed from: $r8$lambda$vhssYXkotANdUJ-gSnnFs5dRBsI, reason: not valid java name */
    public static /* synthetic */ AbstractWriter.Result m1348$r8$lambda$vhssYXkotANdUJgSnnFs5dRBsI(ApiWrap$DialogInfo r0, AbstractWriter r1) {
        return r1.writeDialogStart(r0);
    }

    public static /* synthetic */ AbstractWriter.Result $r8$lambda$x1Qnsvo3L0oIaI3xakiaHHBnCeY(int r0, AbstractWriter r1) {
        return r1.writeStoriesStart(r0);
    }

    public HtmlAndJsonWriter() {
        ArrayList<AbstractWriter> r0 = new ArrayList();
        this._writers = r0;
        r0.add(AbstractWriter.CreateWriter(AbstractWriter.Format.Html));
        r0.add(AbstractWriter.CreateWriter(AbstractWriter.Format.Json));
    }

    private AbstractWriter.Result invoke(Utilities.CallbackReturn<AbstractWriter, AbstractWriter.Result> r6) {
        AbstractWriter.Result r0 = new AbstractWriter.Result(AbstractWriter.Result.Type.Success, _UrlKt.FRAGMENT_ENCODE_SET);
        ArrayList<AbstractWriter> r5 = this._writers;
        int r1 = r5.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L7;
        AbstractWriter r3 = r5.get(r2);
        r2 = r2 + 1;
        AbstractWriter.Result r32 = r6.run(r3);
        if (r32.isSuccess() == true) goto L3;
        r0 = r32;
        goto L3
    L7:
        return r0;
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result finish() {
        return invoke(new HtmlAndJsonWriter$$ExternalSyntheticLambda0());
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public String mainFilePath() {
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result start(ExportSettings r2, OutputFile.Stats r3) {
        return invoke(new HtmlAndJsonWriter$$ExternalSyntheticLambda14(r2, r3));
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeContactsList(ApiWrap$ContactsList r2) {
        return invoke(new HtmlAndJsonWriter$$ExternalSyntheticLambda5(r2));
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeDialogEnd() {
        return invoke(new HtmlAndJsonWriter$$ExternalSyntheticLambda2());
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeDialogSlice(ApiWrap$MessagesSlice r2) {
        return invoke(new HtmlAndJsonWriter$$ExternalSyntheticLambda3(r2));
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeDialogStart(ApiWrap$DialogInfo r2) {
        return invoke(new HtmlAndJsonWriter$$ExternalSyntheticLambda16(r2));
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeDialogsEnd() {
        return invoke(new HtmlAndJsonWriter$$ExternalSyntheticLambda15());
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeDialogsStart(ApiWrap$DialogsInfo r2) {
        return invoke(new HtmlAndJsonWriter$$ExternalSyntheticLambda1(r2));
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeOtherData(ApiWrap$File r2) {
        return invoke(new HtmlAndJsonWriter$$ExternalSyntheticLambda11(r2));
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writePersonal(ApiWrap$ExportPersonalInfo r2) {
        return invoke(new HtmlAndJsonWriter$$ExternalSyntheticLambda12(r2));
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeSessionsList(ApiWrap$SessionsList r2) {
        return invoke(new HtmlAndJsonWriter$$ExternalSyntheticLambda4(r2));
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeStoriesEnd() {
        return invoke(new HtmlAndJsonWriter$$ExternalSyntheticLambda7());
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeStoriesSlice(ApiWrap$StoriesSlice r2) {
        return invoke(new HtmlAndJsonWriter$$ExternalSyntheticLambda10(r2));
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeStoriesStart(int r2) {
        return invoke(new HtmlAndJsonWriter$$ExternalSyntheticLambda9(r2));
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeUserpicsEnd() {
        return invoke(new HtmlAndJsonWriter$$ExternalSyntheticLambda13());
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeUserpicsSlice(ArrayList<HtmlWriter.Photo> r2) {
        return invoke(new HtmlAndJsonWriter$$ExternalSyntheticLambda6(r2));
    }

    @Override // com.exteragram.messenger.export.output.AbstractWriter
    public AbstractWriter.Result writeUserpicsStart(ApiWrap$UserpicsInfo r2) {
        return invoke(new HtmlAndJsonWriter$$ExternalSyntheticLambda8(r2));
    }
}
