package com.exteragram.messenger.export.output.htmlAndJson;

import com.exteragram.messenger.export.ExportSettings;
import com.exteragram.messenger.export.output.AbstractWriter;
import com.exteragram.messenger.export.output.OutputFile;
import org.telegram.messenger.Utilities;

/* loaded from: classes4.dex */
public final /* synthetic */ class HtmlAndJsonWriter$$ExternalSyntheticLambda14 implements Utilities.CallbackReturn {
    public final /* synthetic */ ExportSettings f$0;
    public final /* synthetic */ OutputFile.Stats f$1;

    public /* synthetic */ HtmlAndJsonWriter$$ExternalSyntheticLambda14(ExportSettings r1, OutputFile.Stats r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.CallbackReturn
    public final Object run(Object r2) {
        return HtmlAndJsonWriter.$r8$lambda$gZ_se4ka8JnnkRleO2lyuUNi89k(this.f$0, this.f$1, (AbstractWriter) r2);
    }
}
