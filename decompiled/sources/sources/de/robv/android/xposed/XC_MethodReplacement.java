package de.robv.android.xposed;

import de.robv.android.xposed.XC_MethodHook;

/* loaded from: classes.dex */
public abstract class XC_MethodReplacement extends XC_MethodHook {
    public static final XC_MethodReplacement DO_NOTHING = null;

    /* renamed from: de.robv.android.xposed.XC_MethodReplacement$1, reason: invalid class name */
    public class AnonymousClass1 extends XC_MethodReplacement {
        public AnonymousClass1(int r1) {
            super(r1);
        }

        @Override // de.robv.android.xposed.XC_MethodReplacement
        public Object replaceHookedMethod(XC_MethodHook.MethodHookParam r1) {
            return null;
        }
    }

    /* renamed from: de.robv.android.xposed.XC_MethodReplacement$2, reason: invalid class name */
    /* loaded from: classes5.dex */
    public class AnonymousClass2 extends XC_MethodReplacement {
        final /* synthetic */ Object val$result;

        public AnonymousClass2(int r1, Object r2) {
            this.val$result = r2;
            super(r1);
        }

        @Override // de.robv.android.xposed.XC_MethodReplacement
        public Object replaceHookedMethod(XC_MethodHook.MethodHookParam r1) {
            return this.val$result;
        }
    }

    static {
        DO_NOTHING = new AnonymousClass1(20000);
    }

    public XC_MethodReplacement() {
    }

    public XC_MethodReplacement(int r1) {
        super(r1);
    }

    public static XC_MethodReplacement returnConstant(int r1, Object r2) {
        return new AnonymousClass2(r1, r2);
    }

    public static XC_MethodReplacement returnConstant(Object r1) {
        return returnConstant(50, r1);
    }

    @Override // de.robv.android.xposed.XC_MethodHook
    public final void afterHookedMethod(XC_MethodHook.MethodHookParam r1) {
    }

    @Override // de.robv.android.xposed.XC_MethodHook
    public final void beforeHookedMethod(XC_MethodHook.MethodHookParam r1) {
        r1.setResult(replaceHookedMethod(r1));     // Catch: Throwable -> L4
        return;
    L4:
        th = move-exception;
        r1.setThrowable(th);
    }

    public abstract Object replaceHookedMethod(XC_MethodHook.MethodHookParam r1);
}
