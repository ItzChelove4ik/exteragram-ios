package androidx.media3.session;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.core.util.ObjectsCompat;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes4.dex */
public final class SessionCommands {
    public static final SessionCommands EMPTY = null;
    private static final String FIELD_SESSION_COMMANDS = null;
    public final ImmutableSet<SessionCommand> commands;

    /* renamed from: androidx.media3.session.SessionCommands$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder {
        private final Set<SessionCommand> commands;

        public Builder() {
            this.commands = new HashSet();
        }

        private void addCommandCodes(List<Integer> r4) {
            int r0 = 0;
        L4:
            if (r0 >= r4.size()) goto L6;
            add(new SessionCommand(r4.get(r0).intValue()));
            r0 = r0 + 1;
            goto L4
        }

        public Builder add(SessionCommand r2) {
            this.commands.add((SessionCommand) Assertions.checkNotNull(r2));
            return this;
        }

        public Builder addAllLibraryCommands() {
            addCommandCodes(SessionCommand.LIBRARY_COMMANDS);
            return this;
        }

        public Builder addAllSessionCommands() {
            addCommandCodes(SessionCommand.SESSION_COMMANDS);
            return this;
        }

        public SessionCommands build() {
            return new SessionCommands(this.commands, null);
        }
    }

    static {
        EMPTY = new Builder().build();
        FIELD_SESSION_COMMANDS = Util.intToStringMaxRadix(0);
    }

    private SessionCommands(Collection<SessionCommand> r1) {
        this.commands = ImmutableSet.copyOf(r1);
    }

    public /* synthetic */ SessionCommands(Collection r1, AnonymousClass1 r2) {
        this(r1);
    }

    private static boolean containsCommandCode(Collection<SessionCommand> r1, int r2) {
        Iterator<SessionCommand> r12 = r1.iterator();
    L4:
        if (r12.hasNext() == false) goto L9;
        if (r12.next().commandCode != r2) goto L4;
        return true;
    L9:
        return false;
    }

    public boolean contains(int r3) {
        if (r3 == 0) goto L4;
        boolean r0 = true;
    L5:
        Assertions.checkArgument(r0, "Use contains(Command) for custom command");
        return containsCommandCode(this.commands, r3);
    L4:
        r0 = false;
        goto L5
    }

    public boolean contains(SessionCommand r1) {
        return this.commands.contains(Assertions.checkNotNull(r1));
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof SessionCommands) == true) goto L10;
        return false;
    L10:
        return this.commands.equals(((SessionCommands) r2).commands);
    }

    public int hashCode() {
        return ObjectsCompat.hash(new Object[]{this.commands});
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        ArrayList<? extends Parcelable> r1 = new ArrayList();
        UnmodifiableIterator<SessionCommand> r3 = this.commands.iterator();
    L4:
        if (r3.hasNext() == false) goto L6;
        r1.add(r3.next().toBundle());
        goto L4
    L6:
        r0.putParcelableArrayList(FIELD_SESSION_COMMANDS, r1);
        return r0;
    }
}
