package retrofit2;

import android.annotation.TargetApi;
import android.os.Build;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import okio.ByteString$$ExternalSyntheticBUOutline0;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

/* loaded from: classes3.dex */
class Reflection {

    @TargetApi(24)
    @IgnoreJRERequirement
    public static final class Android24 extends Reflection {
        public Android24() {
        }

        @Override // retrofit2.Reflection
        public Object invokeDefaultMethod(Method r2, Class<?> r3, Object r4, Object[] r5) {
            if (Build.VERSION.SDK_INT >= 26) goto L5;
            ByteString$$ExternalSyntheticBUOutline0.m("Calling default methods on API 24 and 25 is not supported");
            return null;
        L5:
            return DefaultMethodSupport.invoke(r2, r3, r4, r5);
        }

        @Override // retrofit2.Reflection
        public boolean isDefaultMethod(Method r1) {
            return r1.isDefault();
        }
    }

    @IgnoreJRERequirement
    /* loaded from: classes7.dex */
    public static class Java8 extends Reflection {
        public Java8() {
        }

        @Override // retrofit2.Reflection
        public String describeMethodParameter(Method r3, int r4) {
            Parameter r0 = r3.getParameters()[r4];
            if (r0.isNamePresent() == false) goto L7;
            return "parameter '" + r0.getName() + '\'';
        L7:
            return super.describeMethodParameter(r3, r4);
        }

        @Override // retrofit2.Reflection
        public Object invokeDefaultMethod(Method r1, Class<?> r2, Object r3, Object[] r4) {
            return DefaultMethodSupport.invoke(r1, r2, r3, r4);
        }

        @Override // retrofit2.Reflection
        public boolean isDefaultMethod(Method r1) {
            return r1.isDefault();
        }
    }

    public Reflection() {
    }

    public String describeMethodParameter(Method r1, int r2) {
        return "parameter #" + (r2 + 1);
    }

    public Object invokeDefaultMethod(Method r1, Class<?> r2, Object r3, Object[] r4) {
        throw new AssertionError();
    }

    public boolean isDefaultMethod(Method r1) {
        return false;
    }
}
