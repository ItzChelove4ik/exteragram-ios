package com.google.android.gms.internal.measurement;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.HashMap;

/* loaded from: classes.dex */
public abstract class zzco extends zzbm implements zzcp {
    public zzco() {
        super("com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
    }

    public static zzcp asInterface(IBinder r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        IInterface r0 = r2.queryLocalInterface("com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
        if ((r0 instanceof zzcp) == false) goto L10;
        return (zzcp) r0;
    L10:
        return new zzcn(r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzbm
    public final boolean zza(int r11, Parcel r12, Parcel r13, int r14) {
        zzcs r4 = null;
        zzcv r42 = null;
        zzcs r43 = null;
        zzcs r44 = null;
        zzcs r45 = null;
        zzcs r46 = null;
        zzcy r47 = null;
        zzcy r48 = null;
        zzcy r49 = null;
        zzcs r410 = null;
        zzcs r411 = null;
        zzcs r412 = null;
        zzcs r413 = null;
        zzcs r414 = null;
        zzcs r415 = null;
        zzda r416 = null;
        zzcs r417 = null;
        zzcs r418 = null;
        zzcs r419 = null;
        zzcs r420 = null;
        zzcs r421 = null;
        switch(r11) {
            case 1: goto L210;
            case 2: goto L209;
            case 3: goto L201;
            case 4: goto L200;
            case 5: goto L192;
            case 6: goto L184;
            case 7: goto L183;
            case 8: goto L182;
            case 9: goto L181;
            case 10: goto L173;
            case 11: goto L172;
            case 12: goto L171;
            case 13: goto L170;
            case 14: goto L169;
            case 15: goto L168;
            case 16: goto L160;
            case 17: goto L152;
            case 18: goto L144;
            case 19: goto L136;
            case 20: goto L128;
            case 21: goto L120;
            case 22: goto L112;
            case 23: goto L111;
            case 24: goto L110;
            case 25: goto L109;
            case 26: goto L108;
            case 27: goto L107;
            case 28: goto L106;
            case 29: goto L105;
            case 30: goto L104;
            case 31: goto L96;
            case 32: goto L88;
            case 33: goto L87;
            case 34: goto L79;
            case 35: goto L71;
            case 36: goto L63;
            case 37: goto L62;
            case 38: goto L54;
            case 39: goto L53;
            case 40: goto L45;
            case 41: goto L4;
            case 42: goto L44;
            case 43: goto L43;
            case 44: goto L42;
            case 45: goto L41;
            case 46: goto L33;
            case 47: goto L4;
            case 48: goto L32;
            case 49: goto L4;
            case 50: goto L31;
            case 51: goto L30;
            case 52: goto L29;
            case 53: goto L28;
            case 54: goto L27;
            case 55: goto L26;
            case 56: goto L25;
            case 57: goto L17;
            case 58: goto L9;
            case 59: goto L8;
            case 60: goto L7;
            case 61: goto L6;
            default: goto L4;
        };
    L4:
        return false;
    L6:
        long r2 = r12.readLong();
        long r422 = r12.readLong();
        zzbn.zzf(r12);
        resetAnalyticsDataWithElapsedTime(r2, r422);
    L211:
        r13.writeNoException();
        return true;
    L7:
        IObjectWrapper r22 = IObjectWrapper.Stub.asInterface(r12.readStrongBinder());
        zzdb r3 = (zzdb) zzbn.zzb(r12, zzdb.CREATOR);
        long r32 = r12.readLong();
        long r5 = r12.readLong();
        zzbn.zzf(r12);
        initializeWithElapsedTime(r22, r3, r32, r5);
        goto L211
    L8:
        String r1 = r12.readString();
        String r23 = r12.readString();
        Bundle r33 = (Bundle) zzbn.zzb(r12, Bundle.CREATOR);
        boolean r423 = zzbn.zza(r12);
        boolean r52 = zzbn.zza(r12);
        long r6 = r12.readLong();
        long r8 = r12.readLong();
        zzbn.zzf(r12);
        logEventWithElapsedTime(r1, r23, r33, r423, r52, r6, r8);
        goto L211
    L9:
        IBinder r24 = r12.readStrongBinder();
        if (r24 == null) goto L16;
        IInterface r34 = r24.queryLocalInterface("com.google.android.gms.measurement.api.internal.IDynamiteUploadBatchesCallback");
        if ((r34 instanceof zzcv) == false) goto L15;
        r42 = (zzcv) r34;
        goto L16
    L15:
        r42 = new zzct(r24);
    L16:
        zzbn.zzf(r12);
        retrieveAndUploadBatches(r42);
        goto L211
    L17:
        zzdd r25 = (zzdd) zzbn.zzb(r12, zzdd.CREATOR);
        IBinder r53 = r12.readStrongBinder();
        if (r53 == null) goto L24;
        IInterface r35 = r53.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
        if ((r35 instanceof zzcs) == false) goto L23;
        r43 = (zzcs) r35;
        goto L24
    L23:
        r43 = new zzcq(r53);
    L24:
        long r54 = r12.readLong();
        zzbn.zzf(r12);
        onActivitySaveInstanceStateByScionActivityInfo(r25, r43, r54);
        goto L211
    L25:
        zzdd r26 = (zzdd) zzbn.zzb(r12, zzdd.CREATOR);
        long r36 = r12.readLong();
        zzbn.zzf(r12);
        onActivityResumedByScionActivityInfo(r26, r36);
        goto L211
    L26:
        zzdd r27 = (zzdd) zzbn.zzb(r12, zzdd.CREATOR);
        long r37 = r12.readLong();
        zzbn.zzf(r12);
        onActivityPausedByScionActivityInfo(r27, r37);
        goto L211
    L27:
        zzdd r28 = (zzdd) zzbn.zzb(r12, zzdd.CREATOR);
        long r38 = r12.readLong();
        zzbn.zzf(r12);
        onActivityDestroyedByScionActivityInfo(r28, r38);
        goto L211
    L28:
        zzdd r29 = (zzdd) zzbn.zzb(r12, zzdd.CREATOR);
        Bundle r39 = (Bundle) zzbn.zzb(r12, Bundle.CREATOR);
        long r424 = r12.readLong();
        zzbn.zzf(r12);
        onActivityCreatedByScionActivityInfo(r29, r39, r424);
        goto L211
    L29:
        zzdd r210 = (zzdd) zzbn.zzb(r12, zzdd.CREATOR);
        long r310 = r12.readLong();
        zzbn.zzf(r12);
        onActivityStoppedByScionActivityInfo(r210, r310);
        goto L211
    L30:
        zzdd r211 = (zzdd) zzbn.zzb(r12, zzdd.CREATOR);
        long r311 = r12.readLong();
        zzbn.zzf(r12);
        onActivityStartedByScionActivityInfo(r211, r311);
        goto L211
    L31:
        zzdd r212 = (zzdd) zzbn.zzb(r12, zzdd.CREATOR);
        String r213 = r12.readString();
        String r312 = r12.readString();
        long r425 = r12.readLong();
        zzbn.zzf(r12);
        setCurrentScreenByScionActivityInfo(r212, r213, r312, r425);
        goto L211
    L32:
        Intent r214 = (Intent) zzbn.zzb(r12, Intent.CREATOR);
        zzbn.zzf(r12);
        setSgtmDebugInfo(r214);
        goto L211
    L33:
        IBinder r215 = r12.readStrongBinder();
        if (r215 == null) goto L40;
        IInterface r313 = r215.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
        if ((r313 instanceof zzcs) == false) goto L39;
        r44 = (zzcs) r313;
        goto L40
    L39:
        r44 = new zzcq(r215);
    L40:
        zzbn.zzf(r12);
        getSessionId(r44);
        goto L211
    L41:
        Bundle r216 = (Bundle) zzbn.zzb(r12, Bundle.CREATOR);
        long r314 = r12.readLong();
        zzbn.zzf(r12);
        setConsentThirdParty(r216, r314);
        goto L211
    L42:
        Bundle r217 = (Bundle) zzbn.zzb(r12, Bundle.CREATOR);
        long r315 = r12.readLong();
        zzbn.zzf(r12);
        setConsent(r217, r315);
        goto L211
    L43:
        long r218 = r12.readLong();
        zzbn.zzf(r12);
        clearMeasurementEnabled(r218);
        goto L211
    L44:
        Bundle r219 = (Bundle) zzbn.zzb(r12, Bundle.CREATOR);
        zzbn.zzf(r12);
        setDefaultEventParameters(r219);
        goto L211
    L45:
        IBinder r220 = r12.readStrongBinder();
        if (r220 == null) goto L52;
        IInterface r316 = r220.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
        if ((r316 instanceof zzcs) == false) goto L51;
        r45 = (zzcs) r316;
        goto L52
    L51:
        r45 = new zzcq(r220);
    L52:
        zzbn.zzf(r12);
        isDataCollectionEnabled(r45);
        goto L211
    L53:
        boolean r221 = zzbn.zza(r12);
        zzbn.zzf(r12);
        setDataCollectionEnabled(r221);
        goto L211
    L54:
        IBinder r222 = r12.readStrongBinder();
        if (r222 == null) goto L61;
        IInterface r317 = r222.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
        if ((r317 instanceof zzcs) == false) goto L60;
        r46 = (zzcs) r317;
        goto L61
    L60:
        r46 = new zzcq(r222);
    L61:
        int r223 = r12.readInt();
        zzbn.zzf(r12);
        getTestFlag(r46, r223);
        goto L211
    L62:
        HashMap r224 = zzbn.zze(r12);
        zzbn.zzf(r12);
        initForTests(r224);
        goto L211
    L63:
        IBinder r318 = r12.readStrongBinder();
        if (r318 == null) goto L70;
        IInterface r225 = r318.queryLocalInterface("com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
        if ((r225 instanceof zzcy) == false) goto L69;
        r47 = (zzcy) r225;
        goto L70
    L69:
        r47 = new zzcw(r318);
    L70:
        zzbn.zzf(r12);
        unregisterOnMeasurementEventListener(r47);
        goto L211
    L71:
        IBinder r319 = r12.readStrongBinder();
        if (r319 == null) goto L78;
        IInterface r226 = r319.queryLocalInterface("com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
        if ((r226 instanceof zzcy) == false) goto L77;
        r48 = (zzcy) r226;
        goto L78
    L77:
        r48 = new zzcw(r319);
    L78:
        zzbn.zzf(r12);
        registerOnMeasurementEventListener(r48);
        goto L211
    L79:
        IBinder r320 = r12.readStrongBinder();
        if (r320 == null) goto L86;
        IInterface r227 = r320.queryLocalInterface("com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
        if ((r227 instanceof zzcy) == false) goto L85;
        r49 = (zzcy) r227;
        goto L86
    L85:
        r49 = new zzcw(r320);
    L86:
        zzbn.zzf(r12);
        setEventInterceptor(r49);
        goto L211
    L87:
        int r15 = r12.readInt();
        String r228 = r12.readString();
        IObjectWrapper r321 = IObjectWrapper.Stub.asInterface(r12.readStrongBinder());
        IObjectWrapper r426 = IObjectWrapper.Stub.asInterface(r12.readStrongBinder());
        IObjectWrapper r55 = IObjectWrapper.Stub.asInterface(r12.readStrongBinder());
        zzbn.zzf(r12);
        logHealthData(r15, r228, r321, r426, r55);
        goto L211
    L88:
        Bundle r229 = (Bundle) zzbn.zzb(r12, Bundle.CREATOR);
        IBinder r56 = r12.readStrongBinder();
        if (r56 == null) goto L95;
        IInterface r322 = r56.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
        if ((r322 instanceof zzcs) == false) goto L94;
        r410 = (zzcs) r322;
        goto L95
    L94:
        r410 = new zzcq(r56);
    L95:
        long r57 = r12.readLong();
        zzbn.zzf(r12);
        performAction(r229, r410, r57);
        goto L211
    L96:
        IObjectWrapper r230 = IObjectWrapper.Stub.asInterface(r12.readStrongBinder());
        IBinder r58 = r12.readStrongBinder();
        if (r58 == null) goto L103;
        IInterface r323 = r58.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
        if ((r323 instanceof zzcs) == false) goto L102;
        r411 = (zzcs) r323;
        goto L103
    L102:
        r411 = new zzcq(r58);
    L103:
        long r59 = r12.readLong();
        zzbn.zzf(r12);
        onActivitySaveInstanceState(r230, r411, r59);
        goto L211
    L104:
        IObjectWrapper r231 = IObjectWrapper.Stub.asInterface(r12.readStrongBinder());
        long r324 = r12.readLong();
        zzbn.zzf(r12);
        onActivityResumed(r231, r324);
        goto L211
    L105:
        IObjectWrapper r232 = IObjectWrapper.Stub.asInterface(r12.readStrongBinder());
        long r325 = r12.readLong();
        zzbn.zzf(r12);
        onActivityPaused(r232, r325);
        goto L211
    L106:
        IObjectWrapper r233 = IObjectWrapper.Stub.asInterface(r12.readStrongBinder());
        long r326 = r12.readLong();
        zzbn.zzf(r12);
        onActivityDestroyed(r233, r326);
        goto L211
    L107:
        IObjectWrapper r234 = IObjectWrapper.Stub.asInterface(r12.readStrongBinder());
        Bundle r327 = (Bundle) zzbn.zzb(r12, Bundle.CREATOR);
        long r427 = r12.readLong();
        zzbn.zzf(r12);
        onActivityCreated(r234, r327, r427);
        goto L211
    L108:
        IObjectWrapper r235 = IObjectWrapper.Stub.asInterface(r12.readStrongBinder());
        long r328 = r12.readLong();
        zzbn.zzf(r12);
        onActivityStopped(r235, r328);
        goto L211
    L109:
        IObjectWrapper r236 = IObjectWrapper.Stub.asInterface(r12.readStrongBinder());
        long r329 = r12.readLong();
        zzbn.zzf(r12);
        onActivityStarted(r236, r329);
        goto L211
    L110:
        String r237 = r12.readString();
        long r330 = r12.readLong();
        zzbn.zzf(r12);
        endAdUnitExposure(r237, r330);
        goto L211
    L111:
        String r238 = r12.readString();
        long r331 = r12.readLong();
        zzbn.zzf(r12);
        beginAdUnitExposure(r238, r331);
        goto L211
    L112:
        IBinder r239 = r12.readStrongBinder();
        if (r239 == null) goto L119;
        IInterface r332 = r239.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
        if ((r332 instanceof zzcs) == false) goto L118;
        r412 = (zzcs) r332;
        goto L119
    L118:
        r412 = new zzcq(r239);
    L119:
        zzbn.zzf(r12);
        generateEventId(r412);
        goto L211
    L120:
        IBinder r240 = r12.readStrongBinder();
        if (r240 == null) goto L127;
        IInterface r333 = r240.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
        if ((r333 instanceof zzcs) == false) goto L126;
        r413 = (zzcs) r333;
        goto L127
    L126:
        r413 = new zzcq(r240);
    L127:
        zzbn.zzf(r12);
        getGmpAppId(r413);
        goto L211
    L128:
        IBinder r241 = r12.readStrongBinder();
        if (r241 == null) goto L135;
        IInterface r334 = r241.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
        if ((r334 instanceof zzcs) == false) goto L134;
        r414 = (zzcs) r334;
        goto L135
    L134:
        r414 = new zzcq(r241);
    L135:
        zzbn.zzf(r12);
        getAppInstanceId(r414);
        goto L211
    L136:
        IBinder r242 = r12.readStrongBinder();
        if (r242 == null) goto L143;
        IInterface r335 = r242.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
        if ((r335 instanceof zzcs) == false) goto L142;
        r415 = (zzcs) r335;
        goto L143
    L142:
        r415 = new zzcq(r242);
    L143:
        zzbn.zzf(r12);
        getCachedAppInstanceId(r415);
        goto L211
    L144:
        IBinder r243 = r12.readStrongBinder();
        if (r243 == null) goto L151;
        IInterface r336 = r243.queryLocalInterface("com.google.android.gms.measurement.api.internal.IStringProvider");
        if ((r336 instanceof zzda) == false) goto L150;
        r416 = (zzda) r336;
        goto L151
    L150:
        r416 = new zzcz(r243);
    L151:
        zzbn.zzf(r12);
        setInstanceIdProvider(r416);
        goto L211
    L152:
        IBinder r244 = r12.readStrongBinder();
        if (r244 == null) goto L159;
        IInterface r337 = r244.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
        if ((r337 instanceof zzcs) == false) goto L158;
        r417 = (zzcs) r337;
        goto L159
    L158:
        r417 = new zzcq(r244);
    L159:
        zzbn.zzf(r12);
        getCurrentScreenClass(r417);
        goto L211
    L160:
        IBinder r245 = r12.readStrongBinder();
        if (r245 == null) goto L167;
        IInterface r338 = r245.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
        if ((r338 instanceof zzcs) == false) goto L166;
        r418 = (zzcs) r338;
        goto L167
    L166:
        r418 = new zzcq(r245);
    L167:
        zzbn.zzf(r12);
        getCurrentScreenName(r418);
        goto L211
    L168:
        IObjectWrapper r246 = IObjectWrapper.Stub.asInterface(r12.readStrongBinder());
        String r247 = r12.readString();
        String r339 = r12.readString();
        long r428 = r12.readLong();
        zzbn.zzf(r12);
        setCurrentScreen(r246, r247, r339, r428);
        goto L211
    L169:
        long r248 = r12.readLong();
        zzbn.zzf(r12);
        setSessionTimeoutDuration(r248);
        goto L211
    L170:
        long r249 = r12.readLong();
        zzbn.zzf(r12);
        setMinimumSessionDuration(r249);
        goto L211
    L171:
        long r250 = r12.readLong();
        zzbn.zzf(r12);
        resetAnalyticsData(r250);
        goto L211
    L172:
        boolean r251 = zzbn.zza(r12);
        long r340 = r12.readLong();
        zzbn.zzf(r12);
        setMeasurementEnabled(r251, r340);
        goto L211
    L173:
        String r252 = r12.readString();
        String r510 = r12.readString();
        IBinder r62 = r12.readStrongBinder();
        if (r62 == null) goto L180;
        IInterface r341 = r62.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
        if ((r341 instanceof zzcs) == false) goto L179;
        r419 = (zzcs) r341;
        goto L180
    L179:
        r419 = new zzcq(r62);
    L180:
        zzbn.zzf(r12);
        getConditionalUserProperties(r252, r510, r419);
        goto L211
    L181:
        String r253 = r12.readString();
        String r342 = r12.readString();
        Bundle r429 = (Bundle) zzbn.zzb(r12, Bundle.CREATOR);
        zzbn.zzf(r12);
        clearConditionalUserProperty(r253, r342, r429);
        goto L211
    L182:
        Bundle r254 = (Bundle) zzbn.zzb(r12, Bundle.CREATOR);
        long r343 = r12.readLong();
        zzbn.zzf(r12);
        setConditionalUserProperty(r254, r343);
        goto L211
    L183:
        String r255 = r12.readString();
        long r344 = r12.readLong();
        zzbn.zzf(r12);
        setUserId(r255, r344);
        goto L211
    L184:
        String r256 = r12.readString();
        IBinder r511 = r12.readStrongBinder();
        if (r511 == null) goto L191;
        IInterface r345 = r511.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
        if ((r345 instanceof zzcs) == false) goto L190;
        r420 = (zzcs) r345;
        goto L191
    L190:
        r420 = new zzcq(r511);
    L191:
        zzbn.zzf(r12);
        getMaxUserProperties(r256, r420);
        goto L211
    L192:
        String r257 = r12.readString();
        String r512 = r12.readString();
        boolean r63 = zzbn.zza(r12);
        IBinder r7 = r12.readStrongBinder();
        if (r7 == null) goto L199;
        IInterface r346 = r7.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
        if ((r346 instanceof zzcs) == false) goto L198;
        r421 = (zzcs) r346;
        goto L199
    L198:
        r421 = new zzcq(r7);
    L199:
        zzbn.zzf(r12);
        getUserProperties(r257, r512, r63, r421);
        goto L211
    L200:
        String r16 = r12.readString();
        String r258 = r12.readString();
        IObjectWrapper r347 = IObjectWrapper.Stub.asInterface(r12.readStrongBinder());
        boolean r430 = zzbn.zza(r12);
        long r513 = r12.readLong();
        zzbn.zzf(r12);
        setUserProperty(r16, r258, r347, r430, r513);
        goto L211
    L201:
        String r17 = r12.readString();
        String r259 = r12.readString();
        Bundle r514 = (Bundle) zzbn.zzb(r12, Bundle.CREATOR);
        IBinder r64 = r12.readStrongBinder();
        if (r64 == null) goto L208;
        IInterface r348 = r64.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
        if ((r348 instanceof zzcs) == false) goto L207;
        r4 = (zzcs) r348;
        goto L208
    L207:
        r4 = new zzcq(r64);
    L208:
        long r515 = r12.readLong();
        zzbn.zzf(r12);
        logEventAndBundle(r17, r259, r514, r4, r515);
        goto L211
    L209:
        String r18 = r12.readString();
        String r260 = r12.readString();
        Bundle r349 = (Bundle) zzbn.zzb(r12, Bundle.CREATOR);
        boolean r431 = zzbn.zza(r12);
        boolean r516 = zzbn.zza(r12);
        long r65 = r12.readLong();
        zzbn.zzf(r12);
        logEvent(r18, r260, r349, r431, r516, r65);
        goto L211
    L210:
        IObjectWrapper r261 = IObjectWrapper.Stub.asInterface(r12.readStrongBinder());
        zzdb r350 = (zzdb) zzbn.zzb(r12, zzdb.CREATOR);
        long r432 = r12.readLong();
        zzbn.zzf(r12);
        initialize(r261, r350, r432);
        goto L211
    }
}
