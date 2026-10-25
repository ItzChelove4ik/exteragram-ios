package androidx.camera.camera2.interop;

import androidx.camera.camera2.interop.CaptureRequestOptions;
import androidx.camera.core.impl.Config;

/* loaded from: classes3.dex */
public final /* synthetic */ class CaptureRequestOptions$Builder$Companion$$ExternalSyntheticLambda0 implements Config.OptionMatcher {
    public final /* synthetic */ CaptureRequestOptions.Builder f$0;
    public final /* synthetic */ Config f$1;

    public /* synthetic */ CaptureRequestOptions$Builder$Companion$$ExternalSyntheticLambda0(CaptureRequestOptions.Builder r1, Config r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.camera.core.impl.Config.OptionMatcher
    public final boolean onOptionMatched(Config.Option r2) {
        return CaptureRequestOptions.Builder.Companion.m108$r8$lambda$TpTyfsqJK_4iZRe960dG48bmbo(this.f$0, this.f$1, r2);
    }
}
