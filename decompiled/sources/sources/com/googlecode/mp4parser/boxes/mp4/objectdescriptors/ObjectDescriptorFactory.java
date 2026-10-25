package com.googlecode.mp4parser.boxes.mp4.objectdescriptors;

import com.coremedia.iso.IsoTypeReader;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class ObjectDescriptorFactory {
    protected static Map<Integer, Map<Integer, Class<? extends BaseDescriptor>>> descriptorRegistry;
    protected static Logger log;

    static {
        log = Logger.getLogger(ObjectDescriptorFactory.class.getName());
        descriptorRegistry = new HashMap();
        HashSet r0 = new HashSet();
        r0.add(DecoderSpecificInfo.class);
        r0.add(SLConfigDescriptor.class);
        r0.add(BaseDescriptor.class);
        r0.add(ExtensionDescriptor.class);
        r0.add(ObjectDescriptorBase.class);
        r0.add(ProfileLevelIndicationDescriptor.class);
        r0.add(AudioSpecificConfig.class);
        r0.add(ExtensionProfileLevelDescriptor.class);
        r0.add(ESDescriptor.class);
        r0.add(DecoderConfigDescriptor.class);
        Iterator r02 = r0.iterator();
    L4:
        if (r02.hasNext() == false) goto L5;
        Class<? extends BaseDescriptor> r1 = (Class) r02.next();
        Descriptor r2 = (Descriptor) r1.getAnnotation(Descriptor.class);
        int[] r3 = r2.tags();
        int r22 = r2.objectTypeIndication();
        Map<Integer, Class<? extends BaseDescriptor>> r4 = descriptorRegistry.get(Integer.valueOf(r22));
        if (r4 != null) goto L9;
        r4 = new HashMap();
    L9:
        int r5 = r3.length;
        int r6 = 0;
    L10:
        if (r6 >= r5) goto L11;
        r4.put(Integer.valueOf(r3[r6]), r1);
        r6 = r6 + 1;
        goto L10
    L11:
        descriptorRegistry.put(Integer.valueOf(r22), r4);
        goto L4
    }

    public static BaseDescriptor createFrom(int r7, ByteBuffer r8) {
        int r0 = IsoTypeReader.readUInt8(r8);
        Map<Integer, Class<? extends BaseDescriptor>> r1 = descriptorRegistry.get(Integer.valueOf(r7));
        if (r1 != null) goto L5;
        r1 = descriptorRegistry.get(-1);
    L5:
        Class<? extends BaseDescriptor> r12 = r1.get(Integer.valueOf(r0));
        if (r12 != null) goto L8;
    L17:
        log.warning("No ObjectDescriptor found for objectTypeIndication " + Integer.toHexString(r7) + " and tag " + Integer.toHexString(r0) + " found: " + r12);
        BaseDescriptor r3 = new UnknownDescriptor();
    L18:
        r3.parse(r0, r8);
        return r3;
    L8:
        if (r12.isInterface() == true) goto L17;
        if (Modifier.isAbstract(r12.getModifiers()) == true) goto L17;
        r3 = r12.newInstance();     // Catch: Exception -> L14
    L14:
        e = move-exception;
        log.log(Level.SEVERE, "Couldn't instantiate BaseDescriptor class " + r12 + " for objectTypeIndication " + r7 + " and tag " + r0, e);
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }
}
