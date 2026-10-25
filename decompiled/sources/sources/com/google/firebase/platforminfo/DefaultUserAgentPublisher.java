package com.google.firebase.platforminfo;

import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.Dependency;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public class DefaultUserAgentPublisher implements UserAgentPublisher {
    private final GlobalLibraryVersionRegistrar gamesSDKRegistrar;
    private final String javaSDKVersionUserAgent;

    public static /* synthetic */ UserAgentPublisher $r8$lambda$l1TCuYGXPzjNfiAA4kssoT8wtDA(ComponentContainer r2) {
        return new DefaultUserAgentPublisher(r2.setOf(LibraryVersion.class), GlobalLibraryVersionRegistrar.getInstance());
    }

    public DefaultUserAgentPublisher(Set<LibraryVersion> r1, GlobalLibraryVersionRegistrar r2) {
        this.javaSDKVersionUserAgent = toUserAgent(r1);
        this.gamesSDKRegistrar = r2;
    }

    public static Component<UserAgentPublisher> component() {
        return Component.builder(UserAgentPublisher.class).add(Dependency.setOf(LibraryVersion.class)).factory(new DefaultUserAgentPublisher$$ExternalSyntheticLambda0()).build();
    }

    private static String toUserAgent(Set<LibraryVersion> r3) {
        StringBuilder r0 = new StringBuilder();
        Iterator<LibraryVersion> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L9;
        LibraryVersion r1 = r32.next();
        r0.append(r1.getLibraryName());
        r0.append('/');
        r0.append(r1.getVersion());
        if (r32.hasNext() == false) goto L4;
        r0.append(' ');
        goto L4
    L9:
        return r0.toString();
    }

    @Override // com.google.firebase.platforminfo.UserAgentPublisher
    public String getUserAgent() {
        boolean r0 = this.gamesSDKRegistrar.getRegisteredVersions().isEmpty();
        String r1 = this.javaSDKVersionUserAgent;
        if (r0 == false) goto L6;
        return r1;
    L6:
        return r1 + ' ' + toUserAgent(this.gamesSDKRegistrar.getRegisteredVersions());
    }
}
