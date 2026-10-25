package androidx.car.app.messaging.model;

import android.annotation.SuppressLint;
import androidx.car.app.model.Action;
import androidx.car.app.model.CarIcon;
import androidx.car.app.model.CarText;
import androidx.car.app.model.Item;
import androidx.car.app.utils.CollectionUtils;
import androidx.core.app.Person;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
public class ConversationItem implements Item {
    private final List<Action> mActions;
    private final ConversationCallbackDelegate mConversationCallbackDelegate;
    private final CarIcon mIcon;
    private final String mId;
    private final boolean mIsGroupConversation;
    private final List<CarMessage> mMessages;
    private final Person mSelf;
    private final CarText mTitle;

    /* renamed from: androidx.car.app.messaging.model.ConversationItem$1, reason: invalid class name */
    public class AnonymousClass1 implements ConversationCallback {
        final /* synthetic */ ConversationItem this$0;

        public AnonymousClass1(ConversationItem r1) {
            this.this$0 = r1;
        }

        @Override // androidx.car.app.messaging.model.ConversationCallback
        public void onMarkAsRead() {
        }

        @Override // androidx.car.app.messaging.model.ConversationCallback
        public void onTextReply(String r1) {
        }
    }

    public static final class Builder {
        final List<Action> mActions;
        ConversationCallbackDelegate mConversationCallbackDelegate;
        CarIcon mIcon;
        String mId;
        boolean mIsGroupConversation;
        List<CarMessage> mMessages;
        Person mSelf;
        CarText mTitle;

        public Builder() {
            this.mActions = new ArrayList();
        }

        public Builder(ConversationItem r2) {
            this.mId = r2.getId();
            this.mTitle = r2.getTitle();
            this.mSelf = r2.getSelf();
            this.mIcon = r2.getIcon();
            this.mIsGroupConversation = r2.isGroupConversation();
            this.mConversationCallbackDelegate = r2.getConversationCallbackDelegate();
            this.mMessages = r2.getMessages();
            this.mActions = new ArrayList(r2.getActions());
        }

        public ConversationItem build() {
            return new ConversationItem(this);
        }

        @SuppressLint({"MissingGetterMatchingBuilder", "ExecutorRegistration"})
        public Builder setConversationCallback(ConversationCallback r2) {
            Objects.requireNonNull(r2);
            this.mConversationCallbackDelegate = new ConversationCallbackDelegateImpl(r2);
            return this;
        }

        public Builder setGroupConversation(boolean r1) {
            this.mIsGroupConversation = r1;
            return this;
        }

        public Builder setIcon(CarIcon r1) {
            this.mIcon = r1;
            return this;
        }

        public Builder setId(String r1) {
            this.mId = r1;
            return this;
        }

        public Builder setMessages(List<CarMessage> r1) {
            this.mMessages = r1;
            return this;
        }

        public Builder setSelf(Person r1) {
            this.mSelf = r1;
            return this;
        }

        public Builder setTitle(CarText r1) {
            this.mTitle = r1;
            return this;
        }
    }

    private ConversationItem() {
        this.mId = _UrlKt.FRAGMENT_ENCODE_SET;
        this.mTitle = new CarText.Builder(_UrlKt.FRAGMENT_ENCODE_SET).build();
        this.mSelf = new Person.Builder().setName(_UrlKt.FRAGMENT_ENCODE_SET).build();
        this.mIcon = null;
        this.mIsGroupConversation = false;
        this.mMessages = new ArrayList();
        this.mConversationCallbackDelegate = new ConversationCallbackDelegateImpl(new AnonymousClass1(this));
        this.mActions = Collections.EMPTY_LIST;
    }

    public ConversationItem(Builder r3) {
        String r0 = r3.mId;
        Objects.requireNonNull(r0);
        this.mId = r0;
        CarText r02 = r3.mTitle;
        Objects.requireNonNull(r02);
        this.mTitle = r02;
        this.mSelf = validateSender(r3.mSelf);
        this.mIcon = r3.mIcon;
        this.mIsGroupConversation = r3.mIsGroupConversation;
        List<CarMessage> r03 = CollectionUtils.unmodifiableCopy(r3.mMessages);
        Objects.requireNonNull(r03);
        this.mMessages = r03;
        Preconditions.checkState(!r0.isEmpty(), "Message list cannot be empty.");
        ConversationCallbackDelegate r04 = r3.mConversationCallbackDelegate;
        Objects.requireNonNull(r04);
        this.mConversationCallbackDelegate = r04;
        this.mActions = CollectionUtils.unmodifiableCopy(r3.mActions);
    }

    public static Person validateSender(Person r1) {
        Objects.requireNonNull(r1);
        Objects.requireNonNull(r1.getName());
        Objects.requireNonNull(r1.getKey());
        return r1;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof ConversationItem) == true) goto L8;
        return false;
    L8:
        ConversationItem r52 = (ConversationItem) r5;
        if (Objects.equals(this.mId, r52.mId) == true) goto L11;
    L23:
        return false;
    L11:
        if (Objects.equals(this.mTitle, r52.mTitle) == false) goto L23;
        if (Objects.equals(this.mIcon, r52.mIcon) == false) goto L23;
        if (PersonsEqualityHelper.arePersonsEqual(getSelf(), r52.getSelf()) == false) goto L23;
        if (this.mIsGroupConversation != r52.mIsGroupConversation) goto L23;
        if (Objects.equals(this.mMessages, r52.mMessages) == false) goto L23;
        if (Objects.equals(this.mActions, r52.mActions) == false) goto L23;
        return true;
    }

    public List<Action> getActions() {
        return this.mActions;
    }

    public ConversationCallbackDelegate getConversationCallbackDelegate() {
        return this.mConversationCallbackDelegate;
    }

    public CarIcon getIcon() {
        return this.mIcon;
    }

    public String getId() {
        return this.mId;
    }

    public List<CarMessage> getMessages() {
        return this.mMessages;
    }

    public Person getSelf() {
        return this.mSelf;
    }

    public CarText getTitle() {
        return this.mTitle;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(PersonsEqualityHelper.getPersonHashCode(getSelf())), this.mId, this.mTitle, this.mIcon, Boolean.valueOf(this.mIsGroupConversation), this.mMessages, this.mActions});
    }

    public boolean isGroupConversation() {
        return this.mIsGroupConversation;
    }
}
