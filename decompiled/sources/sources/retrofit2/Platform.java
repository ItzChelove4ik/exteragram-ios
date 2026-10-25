package retrofit2;

import java.util.concurrent.Executor;
import retrofit2.BuiltInFactories;
import retrofit2.Reflection;

/* loaded from: classes3.dex */
abstract class Platform {
    static final BuiltInFactories builtInFactories = null;
    static final Executor callbackExecutor = null;
    static final Reflection reflection = null;

    static {
        String r0 = System.getProperty("java.vm.name");
        r0.getClass();
        if (r0.equals("RoboVM") == false) goto L5;
        callbackExecutor = null;
        reflection = new Reflection();
        builtInFactories = new BuiltInFactories();
        return;
    L5:
        if (r0.equals("Dalvik") == true) goto L8;
        callbackExecutor = null;
        reflection = new Reflection.Java8();
        builtInFactories = new BuiltInFactories.Java8();
        return;
    L8:
        callbackExecutor = new AndroidMainExecutor();
        reflection = new Reflection.Android24();
        builtInFactories = new BuiltInFactories.Java8();
    }
}
