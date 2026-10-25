package com.googlecode.mp4parser;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.NoAspectBoundException;

/* loaded from: classes5.dex */
public class RequiresParseDetailAspect {
    private static /* synthetic */ Throwable ajc$initFailureCause;
    public static final /* synthetic */ RequiresParseDetailAspect ajc$perSingletonInstance = null;

    static {
        ajc$postClinit();     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        ajc$initFailureCause = th;
    }

    public RequiresParseDetailAspect() {
    }

    private static /* synthetic */ void ajc$postClinit() {
        ajc$perSingletonInstance = new RequiresParseDetailAspect();
    }

    public static RequiresParseDetailAspect aspectOf() {
        RequiresParseDetailAspect r0 = ajc$perSingletonInstance;
        if (r0 == null) goto L6;
        return r0;
    L6:
        throw new NoAspectBoundException("com.googlecode.mp4parser.RequiresParseDetailAspect", ajc$initFailureCause);
    }

    public void before(JoinPoint r3) {
        if ((r3.getTarget() instanceof AbstractBox) == false) goto L9;
        if (((AbstractBox) r3.getTarget()).isParsed() == true) goto L10;
        ((AbstractBox) r3.getTarget()).parseDetails();
        return;
    L10:
        return;
    L9:
        throw new RuntimeException("Only methods in subclasses of " + AbstractBox.class.getName() + " can  be annotated with ParseDetail");
    }
}
