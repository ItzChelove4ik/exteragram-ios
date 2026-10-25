package de.robv.android.xposed;

import de.robv.android.xposed.callbacks.IXUnhook;
import de.robv.android.xposed.callbacks.XCallback;
import java.lang.reflect.Member;

/* loaded from: classes.dex */
public abstract class XC_MethodHook extends XCallback {

    public static final class MethodHookParam extends XCallback.Param {
        public Object[] args;
        public Member method;
        private Object result;
        boolean returnEarly;
        public Object thisObject;
        private Throwable throwable;

        public MethodHookParam() {
            this.result = null;
            this.throwable = null;
            this.returnEarly = false;
        }

        public Object getResult() {
            return this.result;
        }

        public Object getResultOrThrowable() {
            Throwable r0 = this.throwable;
            if (r0 == null) goto L5;
            throw r0;
        L5:
            return this.result;
        }

        public Throwable getThrowable() {
            return this.throwable;
        }

        public boolean hasThrowable() {
            if (this.throwable == null) goto L6;
            return true;
        L6:
            return false;
        }

        public void setResult(Object r1) {
            this.result = r1;
            this.throwable = null;
            this.returnEarly = true;
        }

        public void setThrowable(Throwable r1) {
            this.throwable = r1;
            this.result = null;
            this.returnEarly = true;
        }
    }

    public class Unhook implements IXUnhook<XC_MethodHook> {
        private final Member hookMethod;
        final /* synthetic */ XC_MethodHook this$0;

        public Unhook(XC_MethodHook r1, Member r2) {
            this.this$0 = r1;
            this.hookMethod = r2;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // de.robv.android.xposed.callbacks.IXUnhook
        public XC_MethodHook getCallback() {
            return this.this$0;
        }

        @Override // de.robv.android.xposed.callbacks.IXUnhook
        public /* bridge */ /* synthetic */ XC_MethodHook getCallback() {
            return getCallback();
        }

        public Member getHookedMethod() {
            return this.hookMethod;
        }

        @Override // de.robv.android.xposed.callbacks.IXUnhook
        public void unhook() {
            XposedBridge.unhookMethod(this.hookMethod, this.this$0);
        }
    }

    public XC_MethodHook() {
    }

    public XC_MethodHook(int r1) {
        super(r1);
    }

    public void afterHookedMethod(MethodHookParam r1) {
    }

    public void beforeHookedMethod(MethodHookParam r1) {
    }
}
