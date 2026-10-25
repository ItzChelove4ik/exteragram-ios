package androidx.core.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.LocusId;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import androidx.collection.ArraySet;
import androidx.core.app.NotificationCompat;
import androidx.core.content.LocusIdCompat;
import androidx.core.graphics.drawable.IconCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
class NotificationCompatBuilder implements NotificationBuilderWithBuilderAccessor {
    private RemoteViews mBigContentView;
    private final Notification.Builder mBuilder;
    private final NotificationCompat.Builder mBuilderCompat;
    private RemoteViews mContentView;
    private final Context mContext;
    private final Bundle mExtras;
    private int mGroupAlertBehavior;
    private RemoteViews mHeadsUpContentView;

    public static class Api24Impl {
        public static Notification.Action.Builder setAllowGeneratedReplies(Notification.Action.Builder r0, boolean r1) {
            return r0.setAllowGeneratedReplies(r1);
        }

        public static Notification.Builder setCustomBigContentView(Notification.Builder r0, RemoteViews r1) {
            return r0.setCustomBigContentView(r1);
        }

        public static Notification.Builder setCustomContentView(Notification.Builder r0, RemoteViews r1) {
            return r0.setCustomContentView(r1);
        }

        public static Notification.Builder setCustomHeadsUpContentView(Notification.Builder r0, RemoteViews r1) {
            return r0.setCustomHeadsUpContentView(r1);
        }

        public static Notification.Builder setRemoteInputHistory(Notification.Builder r0, CharSequence[] r1) {
            return r0.setRemoteInputHistory(r1);
        }
    }

    public static class Api26Impl {
        public static Notification.Builder createBuilder(Context r1, String r2) {
            return new Notification.Builder(r1, r2);
        }

        public static Notification.Builder setBadgeIconType(Notification.Builder r0, int r1) {
            return r0.setBadgeIconType(r1);
        }

        public static Notification.Builder setColorized(Notification.Builder r0, boolean r1) {
            return r0.setColorized(r1);
        }

        public static Notification.Builder setGroupAlertBehavior(Notification.Builder r0, int r1) {
            return r0.setGroupAlertBehavior(r1);
        }

        public static Notification.Builder setSettingsText(Notification.Builder r0, CharSequence r1) {
            return r0.setSettingsText(r1);
        }

        public static Notification.Builder setShortcutId(Notification.Builder r0, String r1) {
            return r0.setShortcutId(r1);
        }

        public static Notification.Builder setTimeoutAfter(Notification.Builder r0, long r1) {
            return r0.setTimeoutAfter(r1);
        }
    }

    public static class Api28Impl {
        public static Notification.Builder addPerson(Notification.Builder r0, android.app.Person r1) {
            return r0.addPerson(r1);
        }

        public static Notification.Action.Builder setSemanticAction(Notification.Action.Builder r0, int r1) {
            return r0.setSemanticAction(r1);
        }
    }

    public static class Api29Impl {
        public static Notification.Builder setAllowSystemGeneratedContextualActions(Notification.Builder r0, boolean r1) {
            return r0.setAllowSystemGeneratedContextualActions(r1);
        }

        public static Notification.Builder setBubbleMetadata(Notification.Builder r0, Notification.BubbleMetadata r1) {
            return r0.setBubbleMetadata(r1);
        }

        public static Notification.Action.Builder setContextual(Notification.Action.Builder r0, boolean r1) {
            return r0.setContextual(r1);
        }

        public static Notification.Builder setLocusId(Notification.Builder r0, Object r1) {
            return r0.setLocusId((LocusId) r1);
        }
    }

    public static class Api31Impl {
        public static Notification.Action.Builder setAuthenticationRequired(Notification.Action.Builder r0, boolean r1) {
            return r0.setAuthenticationRequired(r1);
        }

        public static Notification.Builder setForegroundServiceBehavior(Notification.Builder r0, int r1) {
            return r0.setForegroundServiceBehavior(r1);
        }
    }

    public static final class Api36Impl {
        public static Notification.Builder setShortCriticalText(Notification.Builder r0, String r1) {
            return r0.setShortCriticalText(r1);
        }
    }

    public NotificationCompatBuilder(NotificationCompat.Builder r15) {
        this.mExtras = new Bundle();
        this.mBuilderCompat = r15;
        Context r0 = r15.mContext;
        this.mContext = r0;
        if (Build.VERSION.SDK_INT < 26) goto L5;
        this.mBuilder = Api26Impl.createBuilder(r0, r15.mChannelId);
    L6:
        Notification r1 = r15.mNotification;
        Notification.Builder r3 = this.mBuilder.setWhen(r1.when).setSmallIcon(r1.icon, r1.iconLevel).setContent(r1.contentView).setTicker(r1.tickerText, r15.mTickerView).setVibrate(r1.vibrate).setLights(r1.ledARGB, r1.ledOnMS, r1.ledOffMS);
        int r7 = 0;
        if ((r1.flags & 2) == 0) goto L9;
        boolean r4 = true;
    L10:
        Notification.Builder r32 = r3.setOngoing(r4);
        if ((r1.flags & 8) == 0) goto L13;
        boolean r42 = true;
    L14:
        Notification.Builder r33 = r32.setOnlyAlertOnce(r42);
        if ((r1.flags & 16) == 0) goto L17;
        boolean r43 = true;
    L18:
        Notification.Builder r34 = r33.setAutoCancel(r43).setDefaults(r1.defaults).setContentTitle(r15.mContentTitle).setContentText(r15.mContentText).setContentInfo(r15.mContentInfo).setContentIntent(r15.mContentIntent).setDeleteIntent(r1.deleteIntent);
        PendingIntent r44 = r15.mFullScreenIntent;
        if ((r1.flags & 128) == 0) goto L21;
        boolean r8 = true;
    L22:
        r34.setFullScreenIntent(r44, r8).setNumber(r15.mNumber).setProgress(r15.mProgressMax, r15.mProgress, r15.mProgressIndeterminate);
        Notification.Builder r35 = this.mBuilder;
        IconCompat r45 = r15.mLargeIcon;
        if (r45 != null) goto L25;
        Icon r02 = null;
    L26:
        r35.setLargeIcon(r02);
        this.mBuilder.setSubText(r15.mSubText).setUsesChronometer(r15.mUseChronometer).setPriority(r15.mPriority);
        ArrayList<NotificationCompat.Action> r03 = r15.mActions;
        int r36 = r03.size();
        int r46 = 0;
    L27:
        if (r46 >= r36) goto L29;
        NotificationCompat.Action r9 = r03.get(r46);
        r46 = r46 + 1;
        addAction(r9);
        goto L27
    L29:
        Bundle r04 = r15.mExtras;
        if (r04 == null) goto L32;
        this.mExtras.putAll(r04);
    L32:
        this.mContentView = r15.mContentView;
        this.mBigContentView = r15.mBigContentView;
        this.mBuilder.setShowWhen(r15.mShowWhen);
        this.mBuilder.setLocalOnly(r15.mLocalOnly);
        this.mBuilder.setGroup(r15.mGroupKey);
        this.mBuilder.setSortKey(r15.mSortKey);
        this.mBuilder.setGroupSummary(r15.mGroupSummary);
        this.mGroupAlertBehavior = r15.mGroupAlertBehavior;
        this.mBuilder.setCategory(r15.mCategory);
        this.mBuilder.setColor(r15.mColor);
        this.mBuilder.setVisibility(r15.mVisibility);
        this.mBuilder.setPublicVersion(r15.mPublicVersion);
        this.mBuilder.setSound(r1.sound, r1.audioAttributes);
        if (Build.VERSION.SDK_INT >= 28) goto L35;
        List r05 = combineLists(getPeople(r15.mPersonList), r15.mPeople);
    L36:
        if (r05 != null) goto L38;
    L43:
        this.mHeadsUpContentView = r15.mHeadsUpContentView;
        if (r15.mInvisibleActions.size() <= 0) goto L53;
        Bundle r06 = r15.getExtras().getBundle("android.car.EXTENSIONS");
        if (r06 != null) goto L48;
        r06 = new Bundle();
    L48:
        Bundle r92 = new Bundle(r06);
        Bundle r10 = new Bundle();
        int r11 = 0;
    L50:
        if (r11 >= r15.mInvisibleActions.size()) goto L52;
        r10.putBundle(Integer.toString(r11), NotificationCompatJellybean.getBundleForAction(r15.mInvisibleActions.get(r11)));
        r11 = r11 + 1;
        goto L50
    L52:
        r06.putBundle("invisible_actions", r10);
        r92.putBundle("invisible_actions", r10);
        r15.getExtras().putBundle("android.car.EXTENSIONS", r06);
        this.mExtras.putBundle("android.car.EXTENSIONS", r92);
    L53:
        Object r07 = r15.mSmallIcon;
        if (r07 == null) goto L56;
        this.mBuilder.setSmallIcon((Icon) r07);
    L56:
        this.mBuilder.setExtras(r15.mExtras);
        Api24Impl.setRemoteInputHistory(this.mBuilder, r15.mRemoteInputHistory);
        RemoteViews r08 = r15.mContentView;
        if (r08 == null) goto L59;
        Api24Impl.setCustomContentView(this.mBuilder, r08);
    L59:
        RemoteViews r09 = r15.mBigContentView;
        if (r09 == null) goto L62;
        Api24Impl.setCustomBigContentView(this.mBuilder, r09);
    L62:
        RemoteViews r010 = r15.mHeadsUpContentView;
        if (r010 == null) goto L65;
        Api24Impl.setCustomHeadsUpContentView(this.mBuilder, r010);
    L65:
        int r011 = Build.VERSION.SDK_INT;
        if (r011 < 26) goto L73;
        Api26Impl.setBadgeIconType(this.mBuilder, r15.mBadgeIcon);
        Api26Impl.setSettingsText(this.mBuilder, r15.mSettingsText);
        Api26Impl.setShortcutId(this.mBuilder, r15.mShortcutId);
        Api26Impl.setTimeoutAfter(this.mBuilder, r15.mTimeout);
        Api26Impl.setGroupAlertBehavior(this.mBuilder, r15.mGroupAlertBehavior);
        if (r15.mColorizedSet == false) goto L71;
        Api26Impl.setColorized(this.mBuilder, r15.mColorized);
    L71:
        if (TextUtils.isEmpty(r15.mChannelId) == true) goto L73;
        this.mBuilder.setSound(null).setDefaults(0).setLights(0, 0, 0).setVibrate(null);
    L73:
        if (r011 < 28) goto L77;
        ArrayList<Person> r012 = r15.mPersonList;
        int r37 = r012.size();
    L75:
        if (r7 >= r37) goto L77;
        Person r47 = r012.get(r7);
        r7 = r7 + 1;
        Api28Impl.addPerson(this.mBuilder, r47.toAndroidPerson());
    L77:
        int r013 = Build.VERSION.SDK_INT;
        if (r013 < 29) goto L83;
        Api29Impl.setAllowSystemGeneratedContextualActions(this.mBuilder, r15.mAllowSystemGeneratedContextualActions);
        Api29Impl.setBubbleMetadata(this.mBuilder, NotificationCompat.BubbleMetadata.toPlatform(r15.mBubbleMetadata));
        LocusIdCompat r38 = r15.mLocusId;
        if (r38 == null) goto L83;
        Api29Impl.setLocusId(this.mBuilder, r38.toLocusId());
    L83:
        if (r013 < 31) goto L88;
        int r39 = r15.mFgsDeferBehavior;
        if (r39 == 0) goto L88;
        Api31Impl.setForegroundServiceBehavior(this.mBuilder, r39);
    L88:
        if (r013 < 36) goto L91;
        Api36Impl.setShortCriticalText(this.mBuilder, r15.mShortCriticalText);
    L91:
        if (r15.mSilent == true) goto L93;
        return;
    L93:
        if (this.mBuilderCompat.mGroupSummary == false) goto L95;
        this.mGroupAlertBehavior = 2;
    L96:
        this.mBuilder.setVibrate(null);
        this.mBuilder.setSound(null);
        int r152 = r1.defaults & (-4);
        r1.defaults = r152;
        this.mBuilder.setDefaults(r152);
        if (r013 >= 26) goto L99;
        return;
    L99:
        if (TextUtils.isEmpty(this.mBuilderCompat.mGroupKey) == false) goto L101;
        this.mBuilder.setGroup("silent");
    L101:
        Api26Impl.setGroupAlertBehavior(this.mBuilder, this.mGroupAlertBehavior);
        return;
    L95:
        this.mGroupAlertBehavior = 1;
        goto L96
    L38:
        if (r05.isEmpty() == true) goto L43;
        Iterator r014 = r05.iterator();
    L41:
        if (r014.hasNext() == false) goto L43;
        this.mBuilder.addPerson((String) r014.next());
        goto L41
    L35:
        r05 = r15.mPeople;
        goto L36
    L25:
        r02 = r45.toIcon(r0);
        goto L26
    L21:
        r8 = false;
        goto L22
    L17:
        r43 = false;
        goto L18
    L13:
        r42 = false;
        goto L14
    L9:
        r4 = false;
        goto L10
    L5:
        this.mBuilder = new Notification.Builder(r15.mContext);
        goto L6
    }

    private void addAction(NotificationCompat.Action r6) {
        IconCompat r0 = r6.getIconCompat();
        if (r0 == null) goto L5;
        Icon r02 = r0.toIcon();
    L6:
        Notification.Action.Builder r1 = new Notification.Action.Builder(r02, r6.getTitle(), r6.getActionIntent());
        if (r6.getRemoteInputs() == null) goto L12;
        android.app.RemoteInput[] r03 = RemoteInput.fromCompat(r6.getRemoteInputs());
        int r2 = r03.length;
        int r3 = 0;
    L9:
        if (r3 >= r2) goto L12;
        r1.addRemoteInput(r03[r3]);
        r3 = r3 + 1;
    L12:
        if (r6.getExtras() == null) goto L14;
        Bundle r04 = new Bundle(r6.getExtras());
    L15:
        r04.putBoolean("android.support.allowGeneratedReplies", r6.getAllowGeneratedReplies());
        Api24Impl.setAllowGeneratedReplies(r1, r6.getAllowGeneratedReplies());
        r04.putInt("android.support.action.semanticAction", r6.getSemanticAction());
        int r22 = Build.VERSION.SDK_INT;
        if (r22 < 28) goto L19;
        Api28Impl.setSemanticAction(r1, r6.getSemanticAction());
    L19:
        if (r22 < 29) goto L22;
        Api29Impl.setContextual(r1, r6.isContextual());
    L22:
        if (r22 < 31) goto L24;
        Api31Impl.setAuthenticationRequired(r1, r6.isAuthenticationRequired());
    L24:
        r04.putBoolean("android.support.action.showsUserInterface", r6.getShowsUserInterface());
        r1.addExtras(r04);
        this.mBuilder.addAction(r1.build());
        return;
    L14:
        r04 = new Bundle();
        goto L15
    L5:
        r02 = null;
        goto L6
    }

    private static List<String> combineLists(List<String> r3, List<String> r4) {
        if (r3 != null) goto L4;
        return r4;
    L4:
        if (r4 != null) goto L6;
        return r3;
    L6:
        ArraySet r0 = new ArraySet(r3.size() + r4.size());
        r0.addAll(r3);
        r0.addAll(r4);
        return new ArrayList(r0);
    }

    private static List<String> getPeople(List<Person> r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        ArrayList r0 = new ArrayList(r2.size());
        Iterator<Person> r22 = r2.iterator();
    L7:
        if (r22.hasNext() == false) goto L9;
        r0.add(r22.next().resolveToLegacyUri());
        goto L7
    L9:
        return r0;
    }

    private void removeSoundAndVibration(Notification r1) {
        r1.sound = null;
        r1.vibrate = null;
        r1.defaults &= -4;
    }

    public Notification build() {
        NotificationCompat.Style r0 = this.mBuilderCompat.mStyle;
        if (r0 == null) goto L5;
        r0.apply(this);
    L5:
        if (r0 == null) goto L7;
        RemoteViews r1 = r0.makeContentView(this);
    L8:
        Notification r2 = buildInternal();
        if (r1 == null) goto L11;
        r2.contentView = r1;
    L14:
        if (r0 == null) goto L18;
        RemoteViews r12 = r0.makeBigContentView(this);
        if (r12 == null) goto L18;
        r2.bigContentView = r12;
    L18:
        if (r0 == null) goto L22;
        RemoteViews r3 = this.mBuilderCompat.mStyle.makeHeadsUpContentView(this);
        if (r3 == null) goto L22;
        r2.headsUpContentView = r3;
    L22:
        if (r0 == null) goto L26;
        Bundle r32 = NotificationCompat.getExtras(r2);
        if (r32 == null) goto L26;
        r0.addCompatExtras(r32);
    L26:
        return r2;
    L11:
        RemoteViews r13 = this.mBuilderCompat.mContentView;
        if (r13 == null) goto L14;
        r2.contentView = r13;
        goto L14
    L7:
        r1 = null;
        goto L8
    }

    public Notification buildInternal() {
        int r0 = Build.VERSION.SDK_INT;
        Notification.Builder r1 = this.mBuilder;
        if (r0 >= 26) goto L5;
        Notification r02 = r1.build();
        if (this.mGroupAlertBehavior != 0) goto L9;
    L22:
        return r02;
    L9:
        if (r02.getGroup() == null) goto L16;
        if ((r02.flags & 512) == 0) goto L16;
        if (this.mGroupAlertBehavior != 2) goto L16;
        removeSoundAndVibration(r02);
    L16:
        if (r02.getGroup() == null) goto L22;
        if ((r02.flags & 512) != 0) goto L22;
        if (this.mGroupAlertBehavior != 1) goto L22;
        removeSoundAndVibration(r02);
        goto L22
    L5:
        return r1.build();
    }

    @Override // androidx.core.app.NotificationBuilderWithBuilderAccessor
    public Notification.Builder getBuilder() {
        return this.mBuilder;
    }

    public Context getContext() {
        return this.mContext;
    }
}
