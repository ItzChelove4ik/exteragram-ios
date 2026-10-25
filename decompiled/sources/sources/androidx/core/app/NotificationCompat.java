package androidx.core.app;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Icon;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.RemoteViews;
import androidx.core.R$dimen;
import androidx.core.app.Person;
import androidx.core.content.LocusIdCompat;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.graphics.drawable.IconCompat;
import c.f$$ExternalSyntheticBUOutline1;
import c.f$$ExternalSyntheticBUOutline2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public abstract class NotificationCompat {

    /* renamed from: androidx.core.app.NotificationCompat$1, reason: invalid class name */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class Action {
        public PendingIntent actionIntent;

        @Deprecated
        public int icon;
        private boolean mAllowGeneratedReplies;
        private boolean mAuthenticationRequired;
        private final RemoteInput[] mDataOnlyRemoteInputs;
        final Bundle mExtras;
        private IconCompat mIcon;
        private final boolean mIsContextual;
        private final RemoteInput[] mRemoteInputs;
        private final int mSemanticAction;
        boolean mShowsUserInterface;
        public CharSequence title;

        public static final class Builder {
            private boolean mAllowGeneratedReplies;
            private boolean mAuthenticationRequired;
            private final Bundle mExtras;
            private final IconCompat mIcon;
            private final PendingIntent mIntent;
            private boolean mIsContextual;
            private ArrayList<RemoteInput> mRemoteInputs;
            private int mSemanticAction;
            private boolean mShowsUserInterface;
            private final CharSequence mTitle;

            public Builder(int r13, CharSequence r14, PendingIntent r15) {
                IconCompat r0 = null;
                if (r13 != 0) goto L5;
            L4:
                IconCompat r2 = r0;
                this(r2, r14, r15, new Bundle(), null, true, 0, true, false, false);
                return;
            L5:
                r0 = IconCompat.createWithResource(null, _UrlKt.FRAGMENT_ENCODE_SET, r13);
                goto L4
            }

            private Builder(IconCompat r2, CharSequence r3, PendingIntent r4, Bundle r5, RemoteInput[] r6, boolean r7, int r8, boolean r9, boolean r10, boolean r11) {
                this.mAllowGeneratedReplies = true;
                this.mShowsUserInterface = true;
                this.mIcon = r2;
                this.mTitle = Builder.limitCharSequenceLength(r3);
                this.mIntent = r4;
                this.mExtras = r5;
                if (r6 != null) goto L5;
                ArrayList<RemoteInput> r22 = null;
            L6:
                this.mRemoteInputs = r22;
                this.mAllowGeneratedReplies = r7;
                this.mSemanticAction = r8;
                this.mShowsUserInterface = r9;
                this.mIsContextual = r10;
                this.mAuthenticationRequired = r11;
                return;
            L5:
                r22 = new ArrayList(Arrays.asList(r6));
                goto L6
            }

            private void checkContextualActionNullFields() {
                if (this.mIsContextual == true) goto L6;
                return;
            L6:
                if (this.mIntent == null) goto L8;
                return;
            L8:
                f$$ExternalSyntheticBUOutline2.m("Contextual Actions must contain a valid PendingIntent");
            }

            public Builder addRemoteInput(RemoteInput r2) {
                if (this.mRemoteInputs != null) goto L5;
                this.mRemoteInputs = new ArrayList();
            L5:
                if (r2 == null) goto L7;
                this.mRemoteInputs.add(r2);
            L7:
                return this;
            }

            public Action build() {
                checkContextualActionNullFields();
                ArrayList r1 = new ArrayList();
                ArrayList r2 = new ArrayList();
                ArrayList<RemoteInput> r3 = this.mRemoteInputs;
                if (r3 == null) goto L10;
                int r4 = r3.size();
                int r5 = 0;
            L5:
                if (r5 >= r4) goto L10;
                RemoteInput r6 = r3.get(r5);
                r5 = r5 + 1;
                RemoteInput r62 = r6;
                if (r62.isDataOnly() == true) goto L8;
                r2.add(r62);
                goto L5
            L8:
                r1.add(r62);
            L10:
                RemoteInput[] r42 = null;
                if (r1.isEmpty() == false) goto L13;
                RemoteInput[] r11 = null;
            L15:
                if (r2.isEmpty() == true) goto L19;
                r42 = (RemoteInput[]) r2.toArray(new RemoteInput[r2.size()]);
            L19:
                return new Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, r42, r11, this.mAllowGeneratedReplies, this.mSemanticAction, this.mShowsUserInterface, this.mIsContextual, this.mAuthenticationRequired);
            L13:
                r11 = (RemoteInput[]) r1.toArray(new RemoteInput[r1.size()]);
                goto L15
            }

            public Builder setAllowGeneratedReplies(boolean r1) {
                this.mAllowGeneratedReplies = r1;
                return this;
            }

            public Builder setSemanticAction(int r1) {
                this.mSemanticAction = r1;
                return this;
            }

            public Builder setShowsUserInterface(boolean r1) {
                this.mShowsUserInterface = r1;
                return this;
            }
        }

        public Action(int r3, CharSequence r4, PendingIntent r5) {
            IconCompat r0 = null;
            if (r3 == 0) goto L6;
            r0 = IconCompat.createWithResource(null, _UrlKt.FRAGMENT_ENCODE_SET, r3);
        L6:
            this(r0, r4, r5);
        }

        public Action(IconCompat r13, CharSequence r14, PendingIntent r15) {
            this(r13, r14, r15, new Bundle(), null, null, true, 0, true, false, false);
        }

        public Action(IconCompat r3, CharSequence r4, PendingIntent r5, Bundle r6, RemoteInput[] r7, RemoteInput[] r8, boolean r9, int r10, boolean r11, boolean r12, boolean r13) {
            this.mShowsUserInterface = true;
            this.mIcon = r3;
            if (r3 != null) goto L5;
        L7:
            this.title = Builder.limitCharSequenceLength(r4);
            this.actionIntent = r5;
            if (r6 != null) goto L11;
            r6 = new Bundle();
        L11:
            this.mExtras = r6;
            this.mRemoteInputs = r7;
            this.mDataOnlyRemoteInputs = r8;
            this.mAllowGeneratedReplies = r9;
            this.mSemanticAction = r10;
            this.mShowsUserInterface = r11;
            this.mIsContextual = r12;
            this.mAuthenticationRequired = r13;
            return;
        L5:
            if (r3.getType() != 2) goto L7;
            this.icon = r3.getResId();
            goto L7
        }

        public PendingIntent getActionIntent() {
            return this.actionIntent;
        }

        public boolean getAllowGeneratedReplies() {
            return this.mAllowGeneratedReplies;
        }

        public Bundle getExtras() {
            return this.mExtras;
        }

        public IconCompat getIconCompat() {
            if (this.mIcon != null) goto L8;
            int r0 = this.icon;
            if (r0 == 0) goto L8;
            this.mIcon = IconCompat.createWithResource(null, _UrlKt.FRAGMENT_ENCODE_SET, r0);
        L8:
            return this.mIcon;
        }

        public RemoteInput[] getRemoteInputs() {
            return this.mRemoteInputs;
        }

        public int getSemanticAction() {
            return this.mSemanticAction;
        }

        public boolean getShowsUserInterface() {
            return this.mShowsUserInterface;
        }

        public CharSequence getTitle() {
            return this.title;
        }

        public boolean isAuthenticationRequired() {
            return this.mAuthenticationRequired;
        }

        public boolean isContextual() {
            return this.mIsContextual;
        }
    }

    /* loaded from: classes3.dex */
    public static class BigPictureStyle extends Style {
        private IconCompat mBigLargeIcon;
        private boolean mBigLargeIconSet;
        private CharSequence mPictureContentDescription;
        private IconCompat mPictureIcon;
        private boolean mShowBigPictureWhenCollapsed;

        public static class Api31Impl {
            public static void setBigPicture(Notification.BigPictureStyle r0, Icon r1) {
                r0.bigPicture(r1);
            }

            public static void setContentDescription(Notification.BigPictureStyle r0, CharSequence r1) {
                r0.setContentDescription(r1);
            }

            public static void showBigPictureWhenCollapsed(Notification.BigPictureStyle r0, boolean r1) {
                r0.showBigPictureWhenCollapsed(r1);
            }
        }

        public BigPictureStyle() {
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void apply(NotificationBuilderWithBuilderAccessor r6) {
            Notification.BigPictureStyle r0 = new Notification.BigPictureStyle(r6.getBuilder()).setBigContentTitle(this.mBigContentTitle);
            IconCompat r1 = this.mPictureIcon;
            Context r3 = null;
            if (r1 == null) goto L15;
            if (Build.VERSION.SDK_INT < 31) goto L12;
            if ((r6 instanceof NotificationCompatBuilder) == false) goto L9;
            Context r12 = ((NotificationCompatBuilder) r6).getContext();
        L10:
            Api31Impl.setBigPicture(r0, this.mPictureIcon.toIcon(r12));
            goto L15
        L9:
            r12 = null;
            goto L10
        L12:
            if (r1.getType() != 1) goto L15;
            r0 = r0.bigPicture(this.mPictureIcon.getBitmap());
        L15:
            if (this.mBigLargeIconSet == false) goto L24;
            if (this.mBigLargeIcon != null) goto L20;
            r0.bigLargeIcon(null);
            goto L24
        L20:
            if ((r6 instanceof NotificationCompatBuilder) == false) goto L22;
            r3 = ((NotificationCompatBuilder) r6).getContext();
        L22:
            r0.bigLargeIcon(this.mBigLargeIcon.toIcon(r3));
        L24:
            if (this.mSummaryTextSet == false) goto L27;
            r0.setSummaryText(this.mSummaryText);
        L27:
            if (Build.VERSION.SDK_INT < 31) goto L30;
            Api31Impl.showBigPictureWhenCollapsed(r0, this.mShowBigPictureWhenCollapsed);
            Api31Impl.setContentDescription(r0, this.mPictureContentDescription);
            return;
        }

        public BigPictureStyle bigLargeIcon(Bitmap r1) {
            if (r1 != null) goto L4;
            IconCompat r12 = null;
        L5:
            this.mBigLargeIcon = r12;
            this.mBigLargeIconSet = true;
            return this;
        L4:
            r12 = IconCompat.createWithBitmap(r1);
            goto L5
        }

        public BigPictureStyle bigPicture(Bitmap r1) {
            if (r1 != null) goto L4;
            IconCompat r12 = null;
        L5:
            this.mPictureIcon = r12;
            return this;
        L4:
            r12 = IconCompat.createWithBitmap(r1);
            goto L5
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public String getClassName() {
            return "androidx.core.app.NotificationCompat$BigPictureStyle";
        }
    }

    /* loaded from: classes3.dex */
    public static class BigTextStyle extends Style {
        private CharSequence mBigText;

        public BigTextStyle() {
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void addCompatExtras(Bundle r1) {
            super.addCompatExtras(r1);
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void apply(NotificationBuilderWithBuilderAccessor r2) {
            Notification.BigTextStyle r22 = new Notification.BigTextStyle(r2.getBuilder()).setBigContentTitle(this.mBigContentTitle).bigText(this.mBigText);
            if (this.mSummaryTextSet == false) goto L6;
            r22.setSummaryText(this.mSummaryText);
            return;
        }

        public BigTextStyle bigText(CharSequence r1) {
            this.mBigText = Builder.limitCharSequenceLength(r1);
            return this;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public String getClassName() {
            return "androidx.core.app.NotificationCompat$BigTextStyle";
        }
    }

    public static final class BubbleMetadata {
        private PendingIntent mDeleteIntent;
        private int mDesiredHeight;
        private int mDesiredHeightResId;
        private int mFlags;
        private IconCompat mIcon;
        private PendingIntent mPendingIntent;
        private String mShortcutId;

        /* loaded from: classes3.dex */
        public static class Api29Impl {
            public static Notification.BubbleMetadata toPlatform(BubbleMetadata r2) {
                if (r2 != null) goto L6;
                return null;
            L6:
                if (r2.getIntent() != null) goto L8;
                return null;
            L8:
                Notification.BubbleMetadata.Builder r0 = new Notification.BubbleMetadata.Builder().setIcon(r2.getIcon().toIcon()).setIntent(r2.getIntent()).setDeleteIntent(r2.getDeleteIntent()).setAutoExpandBubble(r2.getAutoExpandBubble()).setSuppressNotification(r2.isNotificationSuppressed());
                if (r2.getDesiredHeight() == 0) goto L12;
                r0.setDesiredHeight(r2.getDesiredHeight());
            L12:
                if (r2.getDesiredHeightResId() == 0) goto L15;
                r0.setDesiredHeightResId(r2.getDesiredHeightResId());
            L15:
                return r0.build();
            }
        }

        public static class Api30Impl {
            public static Notification.BubbleMetadata toPlatform(BubbleMetadata r3) {
                if (r3 != null) goto L6;
                return null;
            L6:
                if (r3.getShortcutId() == null) goto L8;
                Notification.BubbleMetadata.Builder r0 = new Notification.BubbleMetadata.Builder(r3.getShortcutId());
            L9:
                r0.setDeleteIntent(r3.getDeleteIntent()).setAutoExpandBubble(r3.getAutoExpandBubble()).setSuppressNotification(r3.isNotificationSuppressed());
                if (r3.getDesiredHeight() == 0) goto L13;
                r0.setDesiredHeight(r3.getDesiredHeight());
            L13:
                if (r3.getDesiredHeightResId() == 0) goto L16;
                r0.setDesiredHeightResId(r3.getDesiredHeightResId());
            L16:
                return r0.build();
            L8:
                r0 = new Notification.BubbleMetadata.Builder(r3.getIntent(), r3.getIcon().toIcon());
                goto L9
            }
        }

        public static final class Builder {
            private PendingIntent mDeleteIntent;
            private int mDesiredHeight;
            private int mDesiredHeightResId;
            private int mFlags;
            private IconCompat mIcon;
            private PendingIntent mPendingIntent;
            private String mShortcutId;

            public Builder(PendingIntent r2, IconCompat r3) {
                if (r2 == null) goto L9;
                if (r3 == null) goto L7;
                this.mPendingIntent = r2;
                this.mIcon = r3;
                return;
            L7:
                f$$ExternalSyntheticBUOutline2.m("Bubbles require non-null icon");
                throw null;
            L9:
                f$$ExternalSyntheticBUOutline2.m("Bubble requires non-null pending intent");
                throw null;
            }

            private Builder setFlag(int r2, boolean r3) {
                int r0 = this.mFlags;
                if (r3 == false) goto L6;
                this.mFlags = r2 | r0;
                return this;
            L6:
                this.mFlags = (~r2) & r0;
                return this;
            }

            public BubbleMetadata build() {
                String r7 = this.mShortcutId;
                if (r7 == null) goto L5;
            L10:
                if (r7 == null) goto L12;
            L15:
                BubbleMetadata r0 = new BubbleMetadata(this.mPendingIntent, this.mDeleteIntent, this.mIcon, this.mDesiredHeight, this.mDesiredHeightResId, this.mFlags, r7, null);
                r0.setFlags(this.mFlags);
                return r0;
            L12:
                if (this.mIcon != null) goto L15;
                f$$ExternalSyntheticBUOutline2.m("Must supply an icon or shortcut for the bubble");
                return null;
            L5:
                if (this.mPendingIntent != null) goto L10;
                f$$ExternalSyntheticBUOutline2.m("Must supply pending intent or shortcut to bubble");
                return null;
            }

            public Builder setAutoExpandBubble(boolean r2) {
                setFlag(1, r2);
                return this;
            }

            public Builder setDesiredHeight(int r2) {
                this.mDesiredHeight = Math.max(r2, 0);
                this.mDesiredHeightResId = 0;
                return this;
            }

            public Builder setSuppressNotification(boolean r2) {
                setFlag(2, r2);
                return this;
            }
        }

        private BubbleMetadata(PendingIntent r1, PendingIntent r2, IconCompat r3, int r4, int r5, int r6, String r7) {
            this.mPendingIntent = r1;
            this.mIcon = r3;
            this.mDesiredHeight = r4;
            this.mDesiredHeightResId = r5;
            this.mDeleteIntent = r2;
            this.mFlags = r6;
            this.mShortcutId = r7;
        }

        public /* synthetic */ BubbleMetadata(PendingIntent r1, PendingIntent r2, IconCompat r3, int r4, int r5, int r6, String r7, AnonymousClass1 r8) {
            this(r1, r2, r3, r4, r5, r6, r7);
        }

        public static Notification.BubbleMetadata toPlatform(BubbleMetadata r3) {
            if (r3 != null) goto L5;
            return null;
        L5:
            int r1 = Build.VERSION.SDK_INT;
            if (r1 < 30) goto L10;
            return Api30Impl.toPlatform(r3);
        L10:
            if (r1 == 29) goto L12;
            return null;
        L12:
            return Api29Impl.toPlatform(r3);
        }

        public boolean getAutoExpandBubble() {
            if ((this.mFlags & 1) == 0) goto L5;
            return true;
        L5:
            return false;
        }

        public PendingIntent getDeleteIntent() {
            return this.mDeleteIntent;
        }

        public int getDesiredHeight() {
            return this.mDesiredHeight;
        }

        public int getDesiredHeightResId() {
            return this.mDesiredHeightResId;
        }

        @SuppressLint({"InvalidNullConversion"})
        public IconCompat getIcon() {
            return this.mIcon;
        }

        @SuppressLint({"InvalidNullConversion"})
        public PendingIntent getIntent() {
            return this.mPendingIntent;
        }

        public String getShortcutId() {
            return this.mShortcutId;
        }

        public boolean isNotificationSuppressed() {
            if ((this.mFlags & 2) == 0) goto L6;
            return true;
        L6:
            return false;
        }

        public void setFlags(int r1) {
            this.mFlags = r1;
        }
    }

    public static class Builder {
        public ArrayList<Action> mActions;
        boolean mAllowSystemGeneratedContextualActions;
        int mBadgeIcon;
        RemoteViews mBigContentView;
        BubbleMetadata mBubbleMetadata;
        String mCategory;
        String mChannelId;
        int mColor;
        boolean mColorized;
        boolean mColorizedSet;
        CharSequence mContentInfo;
        PendingIntent mContentIntent;
        CharSequence mContentText;
        CharSequence mContentTitle;
        RemoteViews mContentView;
        public Context mContext;
        Bundle mExtras;
        int mFgsDeferBehavior;
        PendingIntent mFullScreenIntent;
        int mGroupAlertBehavior;
        String mGroupKey;
        boolean mGroupSummary;
        RemoteViews mHeadsUpContentView;
        ArrayList<Action> mInvisibleActions;
        IconCompat mLargeIcon;
        boolean mLocalOnly;
        LocusIdCompat mLocusId;
        Notification mNotification;
        int mNumber;

        @Deprecated
        public ArrayList<String> mPeople;
        public ArrayList<Person> mPersonList;
        int mPriority;
        int mProgress;
        boolean mProgressIndeterminate;
        int mProgressMax;
        Notification mPublicVersion;
        CharSequence[] mRemoteInputHistory;
        CharSequence mSettingsText;
        String mShortCriticalText;
        String mShortcutId;
        boolean mShowWhen;
        boolean mSilent;
        Object mSmallIcon;
        String mSortKey;
        Style mStyle;
        CharSequence mSubText;
        RemoteViews mTickerView;
        long mTimeout;
        boolean mUseChronometer;
        int mVisibility;

        @Deprecated
        public Builder(Context r2) {
            this(r2, null);
        }

        public Builder(Context r4, String r5) {
            this.mActions = new ArrayList();
            this.mPersonList = new ArrayList();
            this.mInvisibleActions = new ArrayList();
            this.mShowWhen = true;
            this.mLocalOnly = false;
            this.mColor = 0;
            this.mVisibility = 0;
            this.mBadgeIcon = 0;
            this.mGroupAlertBehavior = 0;
            this.mFgsDeferBehavior = 0;
            Notification r2 = new Notification();
            this.mNotification = r2;
            this.mContext = r4;
            this.mChannelId = r5;
            r2.when = System.currentTimeMillis();
            this.mNotification.audioStreamType = -1;
            this.mPriority = 0;
            this.mPeople = new ArrayList();
            this.mAllowSystemGeneratedContextualActions = true;
        }

        public static CharSequence limitCharSequenceLength(CharSequence r2) {
            if (r2 != null) goto L5;
            return r2;
        L5:
            if (r2.length() > 5120) goto L7;
            return r2;
        L7:
            return r2.subSequence(0, 5120);
        }

        private void setFlag(int r1, boolean r2) {
            Notification r0 = this.mNotification;
            if (r2 == false) goto L6;
            r0.flags = r1 | r0.flags;
            return;
        L6:
            int r22 = r0.flags;
            r0.flags = (~r1) & r22;
        }

        public Builder addAction(int r3, CharSequence r4, PendingIntent r5) {
            this.mActions.add(new Action(r3, r4, r5));
            return this;
        }

        public Builder addAction(Action r2) {
            if (r2 == null) goto L4;
            this.mActions.add(r2);
        L4:
            return this;
        }

        @Deprecated
        public Builder addPerson(String r2) {
            if (r2 != null) goto L4;
        L6:
            return this;
        L4:
            if (r2.isEmpty() == true) goto L6;
            this.mPeople.add(r2);
            goto L6
        }

        public Notification build() {
            return new NotificationCompatBuilder(this).build();
        }

        public Builder extend(Extender r1) {
            r1.extend(this);
            return this;
        }

        public Bundle getExtras() {
            if (this.mExtras != null) goto L6;
            this.mExtras = new Bundle();
        L6:
            return this.mExtras;
        }

        public Builder setAutoCancel(boolean r2) {
            setFlag(16, r2);
            return this;
        }

        public Builder setBubbleMetadata(BubbleMetadata r1) {
            this.mBubbleMetadata = r1;
            return this;
        }

        public Builder setCategory(String r1) {
            this.mCategory = r1;
            return this;
        }

        public Builder setChannelId(String r1) {
            this.mChannelId = r1;
            return this;
        }

        public Builder setColor(int r1) {
            this.mColor = r1;
            return this;
        }

        public Builder setContentIntent(PendingIntent r1) {
            this.mContentIntent = r1;
            return this;
        }

        public Builder setContentText(CharSequence r1) {
            this.mContentText = limitCharSequenceLength(r1);
            return this;
        }

        public Builder setContentTitle(CharSequence r1) {
            this.mContentTitle = limitCharSequenceLength(r1);
            return this;
        }

        public Builder setDefaults(int r2) {
            Notification r0 = this.mNotification;
            r0.defaults = r2;
            if ((r2 & 4) == 0) goto L5;
            r0.flags |= 1;
        L5:
            return this;
        }

        public Builder setDeleteIntent(PendingIntent r2) {
            this.mNotification.deleteIntent = r2;
            return this;
        }

        public Builder setGroup(String r1) {
            this.mGroupKey = r1;
            return this;
        }

        public Builder setGroupAlertBehavior(int r1) {
            this.mGroupAlertBehavior = r1;
            return this;
        }

        public Builder setGroupSummary(boolean r1) {
            this.mGroupSummary = r1;
            return this;
        }

        public Builder setLargeIcon(Bitmap r2) {
            if (r2 != null) goto L4;
            IconCompat r22 = null;
        L5:
            this.mLargeIcon = r22;
            return this;
        L4:
            r22 = IconCompat.createWithBitmap(NotificationCompat.reduceLargeIconSize(this.mContext, r2));
            goto L5
        }

        public Builder setLights(int r2, int r3, int r4) {
            Notification r0 = this.mNotification;
            r0.ledARGB = r2;
            r0.ledOnMS = r3;
            r0.ledOffMS = r4;
            if (r3 == 0) goto L6;
            if (r4 == 0) goto L6;
            int r22 = 1;
        L7:
            r0.flags = r22 | (r0.flags & (-2));
            return this;
        L6:
            r22 = 0;
            goto L7
        }

        public Builder setLocalOnly(boolean r1) {
            this.mLocalOnly = r1;
            return this;
        }

        public Builder setNumber(int r1) {
            this.mNumber = r1;
            return this;
        }

        public Builder setOngoing(boolean r2) {
            setFlag(2, r2);
            return this;
        }

        public Builder setOnlyAlertOnce(boolean r2) {
            setFlag(8, r2);
            return this;
        }

        public Builder setPriority(int r1) {
            this.mPriority = r1;
            return this;
        }

        public Builder setProgress(int r1, int r2, boolean r3) {
            this.mProgressMax = r1;
            this.mProgress = r2;
            this.mProgressIndeterminate = r3;
            return this;
        }

        public Builder setShortcutInfo(ShortcutInfoCompat r3) {
            if (r3 == null) goto L15;
            this.mShortcutId = r3.getId();
            if (this.mLocusId != null) goto L13;
            if (r3.getLocusId() == null) goto L10;
            this.mLocusId = r3.getLocusId();
            goto L13
        L10:
            if (r3.getId() == null) goto L13;
            this.mLocusId = new LocusIdCompat(r3.getId());
        L13:
            if (this.mContentTitle != null) goto L15;
            setContentTitle(r3.getShortLabel());
        L15:
            return this;
        }

        public Builder setShowWhen(boolean r1) {
            this.mShowWhen = r1;
            return this;
        }

        public Builder setSilent(boolean r1) {
            this.mSilent = r1;
            return this;
        }

        public Builder setSmallIcon(int r2) {
            this.mNotification.icon = r2;
            return this;
        }

        public Builder setSmallIcon(IconCompat r2) {
            this.mSmallIcon = r2.toIcon(this.mContext);
            return this;
        }

        public Builder setSortKey(String r1) {
            this.mSortKey = r1;
            return this;
        }

        public Builder setSound(Uri r2) {
            Notification r0 = this.mNotification;
            r0.sound = r2;
            r0.audioStreamType = -1;
            AudioAttributes.Builder r22 = new AudioAttributes.Builder().setContentType(4).setUsage(5);
            Notification r02 = this.mNotification;
            r02.audioAttributes = r22.build();
            return this;
        }

        public Builder setSound(Uri r2, int r3) {
            Notification r0 = this.mNotification;
            r0.sound = r2;
            r0.audioStreamType = r3;
            AudioAttributes.Builder r22 = new AudioAttributes.Builder().setContentType(4).setLegacyStreamType(r3);
            Notification r32 = this.mNotification;
            r32.audioAttributes = r22.build();
            return this;
        }

        public Builder setStyle(Style r2) {
            if (this.mStyle == r2) goto L7;
            this.mStyle = r2;
            if (r2 == null) goto L7;
            r2.setBuilder(this);
        L7:
            return this;
        }

        public Builder setSubText(CharSequence r1) {
            this.mSubText = limitCharSequenceLength(r1);
            return this;
        }

        public Builder setTicker(CharSequence r2) {
            Notification r0 = this.mNotification;
            r0.tickerText = limitCharSequenceLength(r2);
            return this;
        }

        public Builder setTimeoutAfter(long r1) {
            this.mTimeout = r1;
            return this;
        }

        public Builder setVibrate(long[] r2) {
            this.mNotification.vibrate = r2;
            return this;
        }

        public Builder setVisibility(int r1) {
            this.mVisibility = r1;
            return this;
        }

        public Builder setWhen(long r2) {
            this.mNotification.when = r2;
            return this;
        }
    }

    public interface Extender {
        Builder extend(Builder r1);
    }

    public static class InboxStyle extends Style {
        private ArrayList<CharSequence> mTexts;

        public InboxStyle() {
            this.mTexts = new ArrayList();
        }

        public InboxStyle addLine(CharSequence r2) {
            if (r2 == null) goto L4;
            this.mTexts.add(Builder.limitCharSequenceLength(r2));
        L4:
            return this;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void apply(NotificationBuilderWithBuilderAccessor r4) {
            Notification.InboxStyle r42 = new Notification.InboxStyle(r4.getBuilder()).setBigContentTitle(this.mBigContentTitle);
            if (this.mSummaryTextSet == false) goto L5;
            r42.setSummaryText(this.mSummaryText);
        L5:
            ArrayList<CharSequence> r3 = this.mTexts;
            int r0 = r3.size();
            int r1 = 0;
        L6:
            if (r1 >= r0) goto L8;
            CharSequence r2 = r3.get(r1);
            r1 = r1 + 1;
            r42.addLine(r2);
            goto L6
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public String getClassName() {
            return "androidx.core.app.NotificationCompat$InboxStyle";
        }

        public InboxStyle setBigContentTitle(CharSequence r1) {
            this.mBigContentTitle = Builder.limitCharSequenceLength(r1);
            return this;
        }

        public InboxStyle setSummaryText(CharSequence r1) {
            this.mSummaryText = Builder.limitCharSequenceLength(r1);
            this.mSummaryTextSet = true;
            return this;
        }
    }

    public static class MessagingStyle extends Style {
        private CharSequence mConversationTitle;
        private final List<Message> mHistoricMessages;
        private Boolean mIsGroupConversation;
        private final List<Message> mMessages;
        private Person mUser;

        public static class Api24Impl {
            public static Notification.MessagingStyle addMessage(Notification.MessagingStyle r0, Notification.MessagingStyle.Message r1) {
                return r0.addMessage(r1);
            }

            public static Notification.MessagingStyle createMessagingStyle(CharSequence r1) {
                return new Notification.MessagingStyle(r1);
            }

            public static Notification.MessagingStyle setConversationTitle(Notification.MessagingStyle r0, CharSequence r1) {
                return r0.setConversationTitle(r1);
            }
        }

        /* loaded from: classes3.dex */
        public static class Api26Impl {
            public static Notification.MessagingStyle addHistoricMessage(Notification.MessagingStyle r0, Notification.MessagingStyle.Message r1) {
                return r0.addHistoricMessage(r1);
            }
        }

        public static class Api28Impl {
            public static Notification.MessagingStyle createMessagingStyle(android.app.Person r1) {
                return new Notification.MessagingStyle(r1);
            }

            public static Notification.MessagingStyle setGroupConversation(Notification.MessagingStyle r0, boolean r1) {
                return r0.setGroupConversation(r1);
            }
        }

        public static final class Message {
            private String mDataMimeType;
            private Uri mDataUri;
            private Bundle mExtras;
            private final Person mPerson;
            private final CharSequence mText;
            private final long mTimestamp;

            public static class Api24Impl {
                public static Notification.MessagingStyle.Message createMessage(CharSequence r1, long r2, CharSequence r4) {
                    return new Notification.MessagingStyle.Message(r1, r2, r4);
                }

                public static Notification.MessagingStyle.Message setData(Notification.MessagingStyle.Message r0, String r1, Uri r2) {
                    return r0.setData(r1, r2);
                }
            }

            public static class Api28Impl {
                public static Parcelable castToParcelable(android.app.Person r0) {
                    return r0;
                }

                public static Notification.MessagingStyle.Message createMessage(CharSequence r1, long r2, android.app.Person r4) {
                    return new Notification.MessagingStyle.Message(r1, r2, r4);
                }
            }

            public Message(CharSequence r2, long r3, Person r5) {
                this.mExtras = new Bundle();
                this.mText = r2;
                this.mTimestamp = r3;
                this.mPerson = r5;
            }

            public static Bundle[] getBundleArrayForMessages(List<Message> r4) {
                Bundle[] r0 = new Bundle[r4.size()];
                int r1 = r4.size();
                int r2 = 0;
            L3:
                if (r2 >= r1) goto L5;
                r0[r2] = r4.get(r2).toBundle();
                r2 = r2 + 1;
                goto L3
            L5:
                return r0;
            }

            private Bundle toBundle() {
                Bundle r0 = new Bundle();
                CharSequence r1 = this.mText;
                if (r1 == null) goto L5;
                r0.putCharSequence("text", r1);
            L5:
                r0.putLong("time", this.mTimestamp);
                Person r12 = this.mPerson;
                if (r12 == null) goto L11;
                r0.putCharSequence("sender", r12.getName());
                int r13 = Build.VERSION.SDK_INT;
                Person r2 = this.mPerson;
                if (r13 < 28) goto L10;
                r0.putParcelable("sender_person", Api28Impl.castToParcelable(r2.toAndroidPerson()));
                goto L11
            L10:
                r0.putBundle("person", r2.toBundle());
            L11:
                String r14 = this.mDataMimeType;
                if (r14 == null) goto L14;
                r0.putString("type", r14);
            L14:
                Uri r15 = this.mDataUri;
                if (r15 == null) goto L17;
                r0.putParcelable("uri", r15);
            L17:
                Bundle r4 = this.mExtras;
                if (r4 == null) goto L20;
                r0.putBundle("extras", r4);
            L20:
                return r0;
            }

            public String getDataMimeType() {
                return this.mDataMimeType;
            }

            public Uri getDataUri() {
                return this.mDataUri;
            }

            public Person getPerson() {
                return this.mPerson;
            }

            public CharSequence getText() {
                return this.mText;
            }

            public long getTimestamp() {
                return this.mTimestamp;
            }

            public Message setData(String r1, Uri r2) {
                this.mDataMimeType = r1;
                this.mDataUri = r2;
                return this;
            }

            public Notification.MessagingStyle.Message toAndroidMessage() {
                Person r0 = getPerson();
                CharSequence r3 = null;
                android.app.Person r32 = null;
                if (Build.VERSION.SDK_INT < 28) goto L9;
                CharSequence r1 = getText();
                long r4 = getTimestamp();
                if (r0 == null) goto L8;
                r32 = r0.toAndroidPerson();
            L8:
                Notification.MessagingStyle.Message r02 = Api28Impl.createMessage(r1, r4, r32);
            L15:
                if (getDataMimeType() == null) goto L17;
                Api24Impl.setData(r02, getDataMimeType(), getDataUri());
            L17:
                return r02;
            L9:
                CharSequence r12 = getText();
                long r42 = getTimestamp();
                if (r0 == null) goto L13;
                r3 = r0.getName();
            L13:
                r02 = Api24Impl.createMessage(r12, r42, r3);
                goto L15
            }
        }

        public MessagingStyle(Person r2) {
            this.mMessages = new ArrayList();
            this.mHistoricMessages = new ArrayList();
            if (TextUtils.isEmpty(r2.getName()) == true) goto L6;
            this.mUser = r2;
            return;
        L6:
            f$$ExternalSyntheticBUOutline1.m("User's name must not be empty.");
            throw null;
        }

        @Deprecated
        public MessagingStyle(CharSequence r2) {
            this.mMessages = new ArrayList();
            this.mHistoricMessages = new ArrayList();
            this.mUser = new Person.Builder().setName(r2).build();
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void addCompatExtras(Bundle r3) {
            super.addCompatExtras(r3);
            r3.putCharSequence("android.selfDisplayName", this.mUser.getName());
            r3.putBundle("android.messagingStyleUser", this.mUser.toBundle());
            r3.putCharSequence("android.hiddenConversationTitle", this.mConversationTitle);
            if (this.mConversationTitle == null) goto L8;
            if (this.mIsGroupConversation.booleanValue() == false) goto L8;
            r3.putCharSequence("android.conversationTitle", this.mConversationTitle);
        L8:
            if (this.mMessages.isEmpty() == true) goto L11;
            r3.putParcelableArray("android.messages", Message.getBundleArrayForMessages(this.mMessages));
        L11:
            if (this.mHistoricMessages.isEmpty() == true) goto L13;
            r3.putParcelableArray("android.messages.historic", Message.getBundleArrayForMessages(this.mHistoricMessages));
        L13:
            Boolean r2 = this.mIsGroupConversation;
            if (r2 == null) goto L17;
            r3.putBoolean("android.isGroupConversation", r2.booleanValue());
            return;
        }

        public MessagingStyle addMessage(Message r2) {
            if (r2 == null) goto L6;
            this.mMessages.add(r2);
            if (this.mMessages.size() <= 25) goto L6;
            this.mMessages.remove(0);
        L6:
            return this;
        }

        public MessagingStyle addMessage(CharSequence r2, long r3, Person r5) {
            addMessage(new Message(r2, r3, r5));
            return this;
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public void apply(NotificationBuilderWithBuilderAccessor r5) {
            setGroupConversation(isGroupConversation());
            int r0 = Build.VERSION.SDK_INT;
            Person r1 = this.mUser;
            if (r0 < 28) goto L5;
            Notification.MessagingStyle r02 = Api28Impl.createMessagingStyle(r1.toAndroidPerson());
        L6:
            Iterator<Message> r12 = this.mMessages.iterator();
        L8:
            if (r12.hasNext() == false) goto L11;
            Api24Impl.addMessage(r02, r12.next().toAndroidMessage());
            goto L8
        L11:
            if (Build.VERSION.SDK_INT < 26) goto L17;
            Iterator<Message> r13 = this.mHistoricMessages.iterator();
        L14:
            if (r13.hasNext() == false) goto L17;
            Api26Impl.addHistoricMessage(r02, r13.next().toAndroidMessage());
        L17:
            if (this.mIsGroupConversation.booleanValue() == false) goto L19;
        L20:
            Api24Impl.setConversationTitle(r02, this.mConversationTitle);
        L22:
            if (Build.VERSION.SDK_INT < 28) goto L24;
            Api28Impl.setGroupConversation(r02, this.mIsGroupConversation.booleanValue());
        L24:
            r02.setBuilder(r5.getBuilder());
            return;
        L19:
            if (Build.VERSION.SDK_INT < 28) goto L22;
        L5:
            r02 = Api24Impl.createMessagingStyle(r1.getName());
            goto L6
        }

        @Override // androidx.core.app.NotificationCompat.Style
        public String getClassName() {
            return "androidx.core.app.NotificationCompat$MessagingStyle";
        }

        public List<Message> getMessages() {
            return this.mMessages;
        }

        public boolean isGroupConversation() {
            Builder r0 = this.mBuilder;
            if (r0 != null) goto L5;
        L13:
            Boolean r3 = this.mIsGroupConversation;
            if (r3 != null) goto L16;
            return false;
        L16:
            return r3.booleanValue();
        L5:
            if (r0.mContext.getApplicationInfo().targetSdkVersion >= 28) goto L13;
            if (this.mIsGroupConversation != null) goto L13;
            if (this.mConversationTitle == null) goto L12;
            return true;
        L12:
            return false;
        }

        public MessagingStyle setConversationTitle(CharSequence r1) {
            this.mConversationTitle = r1;
            return this;
        }

        public MessagingStyle setGroupConversation(boolean r1) {
            this.mIsGroupConversation = Boolean.valueOf(r1);
            return this;
        }
    }

    public static abstract class Style {
        CharSequence mBigContentTitle;
        protected Builder mBuilder;
        CharSequence mSummaryText;
        boolean mSummaryTextSet;

        public Style() {
            this.mSummaryTextSet = false;
        }

        public void addCompatExtras(Bundle r3) {
            if (this.mSummaryTextSet == false) goto L5;
            r3.putCharSequence("android.summaryText", this.mSummaryText);
        L5:
            CharSequence r0 = this.mBigContentTitle;
            if (r0 == null) goto L8;
            r3.putCharSequence("android.title.big", r0);
        L8:
            String r2 = getClassName();
            if (r2 == null) goto L12;
            r3.putString("androidx.core.app.extra.COMPAT_TEMPLATE", r2);
            return;
        }

        public abstract void apply(NotificationBuilderWithBuilderAccessor r1);

        public String getClassName() {
            return null;
        }

        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor r1) {
            return null;
        }

        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor r1) {
            return null;
        }

        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor r1) {
            return null;
        }

        public void setBuilder(Builder r2) {
            if (this.mBuilder == r2) goto L8;
            this.mBuilder = r2;
            if (r2 == null) goto L9;
            r2.setStyle(this);
            return;
        L9:
            return;
        }
    }

    public static final class WearableExtender implements Extender {
        private ArrayList<Action> mActions;
        private Bitmap mBackground;
        private String mBridgeTag;
        private int mContentActionIndex;
        private int mContentIcon;
        private int mContentIconGravity;
        private int mCustomContentHeight;
        private int mCustomSizePreset;
        private String mDismissalId;
        private PendingIntent mDisplayIntent;
        private int mFlags;
        private int mGravity;
        private int mHintScreenTimeout;
        private ArrayList<Notification> mPages;

        public static class Api24Impl {
            public static Notification.Action.Builder setAllowGeneratedReplies(Notification.Action.Builder r0, boolean r1) {
                return r0.setAllowGeneratedReplies(r1);
            }
        }

        public static class Api31Impl {
            public static Notification.Action.Builder setAuthenticationRequired(Notification.Action.Builder r0, boolean r1) {
                return r0.setAuthenticationRequired(r1);
            }
        }

        public WearableExtender() {
            this.mActions = new ArrayList();
            this.mFlags = 1;
            this.mPages = new ArrayList();
            this.mContentIconGravity = 8388613;
            this.mContentActionIndex = -1;
            this.mCustomSizePreset = 0;
            this.mGravity = 80;
        }

        private static Notification.Action getActionFromActionCompat(Action r4) {
            IconCompat r0 = r4.getIconCompat();
            if (r0 != null) goto L5;
            Icon r02 = null;
        L6:
            Notification.Action.Builder r1 = new Notification.Action.Builder(r02, r4.getTitle(), r4.getActionIntent());
            if (r4.getExtras() == null) goto L9;
            Bundle r03 = new Bundle(r4.getExtras());
        L10:
            r03.putBoolean("android.support.allowGeneratedReplies", r4.getAllowGeneratedReplies());
            Api24Impl.setAllowGeneratedReplies(r1, r4.getAllowGeneratedReplies());
            if (Build.VERSION.SDK_INT < 31) goto L13;
            Api31Impl.setAuthenticationRequired(r1, r4.isAuthenticationRequired());
        L13:
            r1.addExtras(r03);
            RemoteInput[] r42 = r4.getRemoteInputs();
            if (r42 == null) goto L19;
            android.app.RemoteInput[] r43 = RemoteInput.fromCompat(r42);
            int r04 = r43.length;
            int r2 = 0;
        L16:
            if (r2 >= r04) goto L19;
            r1.addRemoteInput(r43[r2]);
            r2 = r2 + 1;
        L19:
            return r1.build();
        L9:
            r03 = new Bundle();
            goto L10
        L5:
            r02 = r0.toIcon();
            goto L6
        }

        public WearableExtender addAction(Action r2) {
            this.mActions.add(r2);
            return this;
        }

        public WearableExtender clone() {
            WearableExtender r0 = new WearableExtender();
            r0.mActions = new ArrayList(this.mActions);
            r0.mFlags = this.mFlags;
            r0.mDisplayIntent = this.mDisplayIntent;
            r0.mPages = new ArrayList(this.mPages);
            r0.mBackground = this.mBackground;
            r0.mContentIcon = this.mContentIcon;
            r0.mContentIconGravity = this.mContentIconGravity;
            r0.mContentActionIndex = this.mContentActionIndex;
            r0.mCustomSizePreset = this.mCustomSizePreset;
            r0.mCustomContentHeight = this.mCustomContentHeight;
            r0.mGravity = this.mGravity;
            r0.mHintScreenTimeout = this.mHintScreenTimeout;
            r0.mDismissalId = this.mDismissalId;
            r0.mBridgeTag = this.mBridgeTag;
            return r0;
        }

        /* renamed from: clone, reason: collision with other method in class */
        public /* bridge */ /* synthetic */ Object m668clone() {
            return clone();
        }

        @Override // androidx.core.app.NotificationCompat.Extender
        public Builder extend(Builder r7) {
            Bundle r0 = new Bundle();
            if (this.mActions.isEmpty() == true) goto L8;
            ArrayList<? extends Parcelable> r1 = new ArrayList(this.mActions.size());
            ArrayList<Action> r2 = this.mActions;
            int r3 = r2.size();
            int r4 = 0;
        L5:
            if (r4 >= r3) goto L7;
            Action r5 = r2.get(r4);
            r4 = r4 + 1;
            r1.add(getActionFromActionCompat(r5));
            goto L5
        L7:
            r0.putParcelableArrayList("actions", r1);
        L8:
            int r12 = this.mFlags;
            if (r12 == 1) goto L11;
            r0.putInt("flags", r12);
        L11:
            PendingIntent r13 = this.mDisplayIntent;
            if (r13 == null) goto L15;
            r0.putParcelable("displayIntent", r13);
        L15:
            if (this.mPages.isEmpty() == true) goto L17;
            ArrayList<Notification> r14 = this.mPages;
            r0.putParcelableArray("pages", (Parcelable[]) r14.toArray(new Notification[r14.size()]));
        L17:
            Bitmap r15 = this.mBackground;
            if (r15 == null) goto L20;
            r0.putParcelable("background", r15);
        L20:
            int r16 = this.mContentIcon;
            if (r16 == 0) goto L23;
            r0.putInt("contentIcon", r16);
        L23:
            int r17 = this.mContentIconGravity;
            if (r17 == 8388613) goto L26;
            r0.putInt("contentIconGravity", r17);
        L26:
            int r18 = this.mContentActionIndex;
            if (r18 == (-1)) goto L29;
            r0.putInt("contentActionIndex", r18);
        L29:
            int r19 = this.mCustomSizePreset;
            if (r19 == 0) goto L32;
            r0.putInt("customSizePreset", r19);
        L32:
            int r110 = this.mCustomContentHeight;
            if (r110 == 0) goto L35;
            r0.putInt("customContentHeight", r110);
        L35:
            int r111 = this.mGravity;
            if (r111 == 80) goto L38;
            r0.putInt("gravity", r111);
        L38:
            int r112 = this.mHintScreenTimeout;
            if (r112 == 0) goto L41;
            r0.putInt("hintScreenTimeout", r112);
        L41:
            String r113 = this.mDismissalId;
            if (r113 == null) goto L44;
            r0.putString("dismissalId", r113);
        L44:
            String r6 = this.mBridgeTag;
            if (r6 == null) goto L47;
            r0.putString("bridgeTag", r6);
        L47:
            r7.getExtras().putBundle("android.wearable.EXTENSIONS", r0);
            return r7;
        }

        public WearableExtender setBridgeTag(String r1) {
            this.mBridgeTag = r1;
            return this;
        }

        public WearableExtender setDismissalId(String r1) {
            this.mDismissalId = r1;
            return this;
        }
    }

    @Deprecated
    public static Bundle getExtras(Notification r0) {
        return r0.extras;
    }

    public static Bitmap reduceLargeIconSize(Context r8, Bitmap r9) {
        if (r9 != null) goto L4;
    L13:
        return r9;
    L4:
        if (Build.VERSION.SDK_INT >= 27) goto L13;
        Resources r82 = r8.getResources();
        int r0 = r82.getDimensionPixelSize(R$dimen.compat_notification_large_icon_max_width);
        int r83 = r82.getDimensionPixelSize(R$dimen.compat_notification_large_icon_max_height);
        if (r9.getWidth() <= r0) goto L9;
    L11:
        double r02 = Math.min(r0 / Math.max(1, r9.getWidth()), r83 / Math.max(1, r9.getHeight()));
        return Bitmap.createScaledBitmap(r9, (int) Math.ceil(r9.getWidth() * r02), (int) Math.ceil(r9.getHeight() * r02), true);
    L9:
        if (r9.getHeight() > r83) goto L11;
        return r9;
    }
}
