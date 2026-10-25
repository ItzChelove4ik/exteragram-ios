.class public final Landroidx/car/app/HostDispatcher;
.super Ljava/lang/Object;
.source "SourceFile"


# instance fields
.field private mAppHost:Landroidx/car/app/IAppHost;

.field private mCarHost:Landroidx/car/app/ICarHost;

.field private mConstraintHost:Landroidx/car/app/constraints/IConstraintHost;

.field private mNavigationHost:Landroidx/car/app/navigation/INavigationHost;

.field private mPlaybackMediaHost:Landroidx/car/app/media/IMediaPlaybackHost;

.field private mSuggestionHost:Landroidx/car/app/suggestion/ISuggestionHost;


# direct methods
.method public static synthetic $r8$lambda$8w5CDveJSdeBcrRSDyC0QplFTHg(Landroidx/car/app/HostDispatcher;)Landroidx/car/app/media/IMediaPlaybackHost;
    .locals 1

    .line 185
    iget-object p0, p0, Landroidx/car/app/HostDispatcher;->mCarHost:Landroidx/car/app/ICarHost;

    .line 186
    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    const-string v0, "media_playback"

    invoke-interface {p0, v0}, Landroidx/car/app/ICarHost;->getHost(Ljava/lang/String;)Landroid/os/IBinder;

    move-result-object p0

    .line 185
    invoke-static {p0}, Landroidx/car/app/media/IMediaPlaybackHost$Stub;->asInterface(Landroid/os/IBinder;)Landroidx/car/app/media/IMediaPlaybackHost;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic $r8$lambda$JbnOiXNnUGgRwrRQRy5aIgSrvN0(Landroidx/car/app/HostDispatcher;)Landroidx/car/app/navigation/INavigationHost;
    .locals 1

    .line 197
    iget-object p0, p0, Landroidx/car/app/HostDispatcher;->mCarHost:Landroidx/car/app/ICarHost;

    .line 198
    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    const-string/jumbo v0, "navigation"

    invoke-interface {p0, v0}, Landroidx/car/app/ICarHost;->getHost(Ljava/lang/String;)Landroid/os/IBinder;

    move-result-object p0

    .line 197
    invoke-static {p0}, Landroidx/car/app/navigation/INavigationHost$Stub;->asInterface(Landroid/os/IBinder;)Landroidx/car/app/navigation/INavigationHost;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic $r8$lambda$hTonFC9rqQH4rH5typAaZw3qlJU(Landroidx/car/app/HostDispatcher;)Landroidx/car/app/constraints/IConstraintHost;
    .locals 1

    .line 162
    iget-object p0, p0, Landroidx/car/app/HostDispatcher;->mCarHost:Landroidx/car/app/ICarHost;

    .line 163
    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    const-string v0, "constraints"

    invoke-interface {p0, v0}, Landroidx/car/app/ICarHost;->getHost(Ljava/lang/String;)Landroid/os/IBinder;

    move-result-object p0

    .line 162
    invoke-static {p0}, Landroidx/car/app/constraints/IConstraintHost$Stub;->asInterface(Landroid/os/IBinder;)Landroidx/car/app/constraints/IConstraintHost;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic $r8$lambda$j8DL6KSzD_S_fWmiSleKmSJ8GSQ(Landroidx/car/app/HostDispatcher;)Landroidx/car/app/suggestion/ISuggestionHost;
    .locals 1

    .line 173
    iget-object p0, p0, Landroidx/car/app/HostDispatcher;->mCarHost:Landroidx/car/app/ICarHost;

    .line 174
    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    const-string/jumbo v0, "suggestion"

    invoke-interface {p0, v0}, Landroidx/car/app/ICarHost;->getHost(Ljava/lang/String;)Landroid/os/IBinder;

    move-result-object p0

    .line 173
    invoke-static {p0}, Landroidx/car/app/suggestion/ISuggestionHost$Stub;->asInterface(Landroid/os/IBinder;)Landroidx/car/app/suggestion/ISuggestionHost;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic $r8$lambda$qGjqOY3HWLrXXhC-aw0gszQD800(Landroidx/car/app/HostDispatcher;)Landroidx/car/app/IAppHost;
    .locals 1

    .line 153
    iget-object p0, p0, Landroidx/car/app/HostDispatcher;->mCarHost:Landroidx/car/app/ICarHost;

    .line 153
    invoke-static {p0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    const-string v0, "app"

    invoke-interface {p0, v0}, Landroidx/car/app/ICarHost;->getHost(Ljava/lang/String;)Landroid/os/IBinder;

    move-result-object p0

    invoke-static {p0}, Landroidx/car/app/IAppHost$Stub;->asInterface(Landroid/os/IBinder;)Landroidx/car/app/IAppHost;

    move-result-object p0

    return-object p0
.end method

.method public static synthetic $r8$lambda$s4xJ5LW05J7W13ehpZbJ4VKDjTE(Landroidx/car/app/HostDispatcher;Ljava/lang/String;Ljava/lang/String;Landroidx/car/app/HostCall;)Ljava/lang/Object;
    .locals 0

    .line 102
    invoke-virtual {p0, p1}, Landroidx/car/app/HostDispatcher;->getHost(Ljava/lang/String;)Landroid/os/IInterface;

    move-result-object p0

    const/4 p1, 0x0

    if-nez p0, :cond_0

    .line 104
    new-instance p0, Ljava/lang/StringBuilder;

    const-string p3, "Could not retrieve host while dispatching call "

    invoke-direct {p0, p3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    const-string p2, "CarApp.Dispatch"

    invoke-static {p2, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    return-object p1

    .line 108
    :cond_0
    invoke-interface {p3, p0}, Landroidx/car/app/HostCall;->dispatch(Ljava/lang/Object;)Ljava/lang/Object;

    return-object p1
.end method

.method public static synthetic $r8$lambda$x-LnTW9-SXkfbULQApW6HLJtW2U(Landroidx/car/app/HostDispatcher;Ljava/lang/String;Ljava/lang/String;Landroidx/car/app/HostCall;)Ljava/lang/Object;
    .locals 0

    .line 77
    invoke-virtual {p0, p1}, Landroidx/car/app/HostDispatcher;->getHost(Ljava/lang/String;)Landroid/os/IInterface;

    move-result-object p0

    if-nez p0, :cond_0

    .line 79
    new-instance p0, Ljava/lang/StringBuilder;

    const-string p1, "Could not retrieve host while dispatching call "

    invoke-direct {p0, p1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    const-string p1, "CarApp.Dispatch"

    invoke-static {p1, p0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    const/4 p0, 0x0

    return-object p0

    .line 83
    :cond_0
    invoke-interface {p3, p0}, Landroidx/car/app/HostCall;->dispatch(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public constructor <init>()V
    .locals 0

    .line 47
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public dispatch(Ljava/lang/String;Ljava/lang/String;Landroidx/car/app/HostCall;)V
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<ServiceT:",
            "Ljava/lang/Object;",
            "ReturnT:",
            "Ljava/lang/Object;",
            ">(",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            "Landroidx/car/app/HostCall<",
            "TServiceT;TReturnT;>;)V"
        }
    .end annotation

    .line 101
    new-instance v0, Landroidx/car/app/HostDispatcher$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0, p1, p2, p3}, Landroidx/car/app/HostDispatcher$$ExternalSyntheticLambda0;-><init>(Landroidx/car/app/HostDispatcher;Ljava/lang/String;Ljava/lang/String;Landroidx/car/app/HostCall;)V

    invoke-static {p2, v0}, Landroidx/car/app/utils/RemoteUtils;->dispatchCallToHost(Ljava/lang/String;Landroidx/car/app/utils/RemoteUtils$RemoteCall;)V

    return-void
.end method

.method public dispatchForResult(Ljava/lang/String;Ljava/lang/String;Landroidx/car/app/HostCall;)Ljava/lang/Object;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<ServiceT:",
            "Ljava/lang/Object;",
            "ReturnT:",
            "Ljava/lang/Object;",
            ">(",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            "Landroidx/car/app/HostCall<",
            "TServiceT;TReturnT;>;)TReturnT;"
        }
    .end annotation

    .line 76
    new-instance v0, Landroidx/car/app/HostDispatcher$$ExternalSyntheticLambda1;

    invoke-direct {v0, p0, p1, p2, p3}, Landroidx/car/app/HostDispatcher$$ExternalSyntheticLambda1;-><init>(Landroidx/car/app/HostDispatcher;Ljava/lang/String;Ljava/lang/String;Landroidx/car/app/HostCall;)V

    invoke-static {p2, v0}, Landroidx/car/app/utils/RemoteUtils;->dispatchCallToHostForResult(Ljava/lang/String;Landroidx/car/app/utils/RemoteUtils$RemoteCall;)Ljava/lang/Object;

    move-result-object p0

    return-object p0
.end method

.method public getHost(Ljava/lang/String;)Landroid/os/IInterface;
    .locals 2

    .line 141
    iget-object v0, p0, Landroidx/car/app/HostDispatcher;->mCarHost:Landroidx/car/app/ICarHost;

    if-nez v0, :cond_0

    .line 142
    const-string p0, "CarApp.Dispatch"

    const-string p1, "Host is not bound when attempting to retrieve host service"

    invoke-static {p0, p1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    const/4 p0, 0x0

    return-object p0

    .line 148
    :cond_0
    invoke-virtual {p1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    invoke-virtual {p1}, Ljava/lang/String;->hashCode()I

    move-result v0

    const/4 v1, -0x1

    sparse-switch v0, :sswitch_data_0

    goto :goto_0

    :sswitch_0
    const-string/jumbo v0, "navigation"

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_1

    goto :goto_0

    :cond_1
    const/4 v1, 0x5

    goto :goto_0

    :sswitch_1
    const-string v0, "media_playback"

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_2

    goto :goto_0

    :cond_2
    const/4 v1, 0x4

    goto :goto_0

    :sswitch_2
    const-string/jumbo v0, "suggestion"

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_3

    goto :goto_0

    :cond_3
    const/4 v1, 0x3

    goto :goto_0

    :sswitch_3
    const-string v0, "car"

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_4

    goto :goto_0

    :cond_4
    const/4 v1, 0x2

    goto :goto_0

    :sswitch_4
    const-string v0, "app"

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_5

    goto :goto_0

    :cond_5
    const/4 v1, 0x1

    goto :goto_0

    :sswitch_5
    const-string v0, "constraints"

    invoke-virtual {p1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_6

    goto :goto_0

    :cond_6
    const/4 v1, 0x0

    :goto_0
    packed-switch v1, :pswitch_data_0

    .line 208
    new-instance p0, Ljava/security/InvalidParameterException;

    const-string v0, "Invalid host type: "

    invoke-virtual {v0, p1}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p0, p1}, Ljava/security/InvalidParameterException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 193
    :pswitch_0
    iget-object p1, p0, Landroidx/car/app/HostDispatcher;->mNavigationHost:Landroidx/car/app/navigation/INavigationHost;

    if-nez p1, :cond_7

    .line 194
    new-instance p1, Landroidx/car/app/HostDispatcher$$ExternalSyntheticLambda6;

    invoke-direct {p1, p0}, Landroidx/car/app/HostDispatcher$$ExternalSyntheticLambda6;-><init>(Landroidx/car/app/HostDispatcher;)V

    .line 195
    const-string v0, "getHost(Navigation)"

    invoke-static {v0, p1}, Landroidx/car/app/utils/RemoteUtils;->dispatchCallToHostForResult(Ljava/lang/String;Landroidx/car/app/utils/RemoteUtils$RemoteCall;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroidx/car/app/navigation/INavigationHost;

    iput-object p1, p0, Landroidx/car/app/HostDispatcher;->mNavigationHost:Landroidx/car/app/navigation/INavigationHost;

    .line 202
    :cond_7
    iget-object p0, p0, Landroidx/car/app/HostDispatcher;->mNavigationHost:Landroidx/car/app/navigation/INavigationHost;

    return-object p0

    .line 181
    :pswitch_1
    iget-object p1, p0, Landroidx/car/app/HostDispatcher;->mPlaybackMediaHost:Landroidx/car/app/media/IMediaPlaybackHost;

    if-nez p1, :cond_8

    .line 182
    new-instance p1, Landroidx/car/app/HostDispatcher$$ExternalSyntheticLambda5;

    invoke-direct {p1, p0}, Landroidx/car/app/HostDispatcher$$ExternalSyntheticLambda5;-><init>(Landroidx/car/app/HostDispatcher;)V

    .line 183
    const-string v0, "getHost(Media)"

    invoke-static {v0, p1}, Landroidx/car/app/utils/RemoteUtils;->dispatchCallToHostForResult(Ljava/lang/String;Landroidx/car/app/utils/RemoteUtils$RemoteCall;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroidx/car/app/media/IMediaPlaybackHost;

    iput-object p1, p0, Landroidx/car/app/HostDispatcher;->mPlaybackMediaHost:Landroidx/car/app/media/IMediaPlaybackHost;

    .line 190
    :cond_8
    iget-object p0, p0, Landroidx/car/app/HostDispatcher;->mPlaybackMediaHost:Landroidx/car/app/media/IMediaPlaybackHost;

    return-object p0

    .line 169
    :pswitch_2
    iget-object p1, p0, Landroidx/car/app/HostDispatcher;->mSuggestionHost:Landroidx/car/app/suggestion/ISuggestionHost;

    if-nez p1, :cond_9

    .line 170
    new-instance p1, Landroidx/car/app/HostDispatcher$$ExternalSyntheticLambda4;

    invoke-direct {p1, p0}, Landroidx/car/app/HostDispatcher$$ExternalSyntheticLambda4;-><init>(Landroidx/car/app/HostDispatcher;)V

    .line 171
    const-string v0, "getHost(Suggestion)"

    invoke-static {v0, p1}, Landroidx/car/app/utils/RemoteUtils;->dispatchCallToHostForResult(Ljava/lang/String;Landroidx/car/app/utils/RemoteUtils$RemoteCall;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroidx/car/app/suggestion/ISuggestionHost;

    iput-object p1, p0, Landroidx/car/app/HostDispatcher;->mSuggestionHost:Landroidx/car/app/suggestion/ISuggestionHost;

    .line 178
    :cond_9
    iget-object p0, p0, Landroidx/car/app/HostDispatcher;->mSuggestionHost:Landroidx/car/app/suggestion/ISuggestionHost;

    return-object p0

    .line 205
    :pswitch_3
    iget-object p0, p0, Landroidx/car/app/HostDispatcher;->mCarHost:Landroidx/car/app/ICarHost;

    return-object p0

    .line 150
    :pswitch_4
    iget-object p1, p0, Landroidx/car/app/HostDispatcher;->mAppHost:Landroidx/car/app/IAppHost;

    if-nez p1, :cond_a

    .line 151
    new-instance p1, Landroidx/car/app/HostDispatcher$$ExternalSyntheticLambda2;

    invoke-direct {p1, p0}, Landroidx/car/app/HostDispatcher$$ExternalSyntheticLambda2;-><init>(Landroidx/car/app/HostDispatcher;)V

    .line 152
    const-string v0, "getHost(App)"

    invoke-static {v0, p1}, Landroidx/car/app/utils/RemoteUtils;->dispatchCallToHostForResult(Ljava/lang/String;Landroidx/car/app/utils/RemoteUtils$RemoteCall;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroidx/car/app/IAppHost;

    iput-object p1, p0, Landroidx/car/app/HostDispatcher;->mAppHost:Landroidx/car/app/IAppHost;

    .line 156
    :cond_a
    iget-object p0, p0, Landroidx/car/app/HostDispatcher;->mAppHost:Landroidx/car/app/IAppHost;

    return-object p0

    .line 159
    :pswitch_5
    iget-object p1, p0, Landroidx/car/app/HostDispatcher;->mConstraintHost:Landroidx/car/app/constraints/IConstraintHost;

    if-nez p1, :cond_b

    .line 160
    new-instance p1, Landroidx/car/app/HostDispatcher$$ExternalSyntheticLambda3;

    invoke-direct {p1, p0}, Landroidx/car/app/HostDispatcher$$ExternalSyntheticLambda3;-><init>(Landroidx/car/app/HostDispatcher;)V

    .line 161
    const-string v0, "getHost(Constraints)"

    invoke-static {v0, p1}, Landroidx/car/app/utils/RemoteUtils;->dispatchCallToHostForResult(Ljava/lang/String;Landroidx/car/app/utils/RemoteUtils$RemoteCall;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Landroidx/car/app/constraints/IConstraintHost;

    iput-object p1, p0, Landroidx/car/app/HostDispatcher;->mConstraintHost:Landroidx/car/app/constraints/IConstraintHost;

    .line 166
    :cond_b
    iget-object p0, p0, Landroidx/car/app/HostDispatcher;->mConstraintHost:Landroidx/car/app/constraints/IConstraintHost;

    return-object p0

    :sswitch_data_0
    .sparse-switch
        -0x5fc459ca -> :sswitch_5
        0x17a21 -> :sswitch_4
        0x17fd4 -> :sswitch_3
        0x4763ca04 -> :sswitch_2
        0x5d8d3816 -> :sswitch_1
        0x6f060a14 -> :sswitch_0
    .end sparse-switch

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public resetHosts()V
    .locals 1

    .line 124
    invoke-static {}, Landroidx/car/app/utils/ThreadUtils;->checkMainThread()V

    const/4 v0, 0x0

    .line 126
    iput-object v0, p0, Landroidx/car/app/HostDispatcher;->mCarHost:Landroidx/car/app/ICarHost;

    .line 127
    iput-object v0, p0, Landroidx/car/app/HostDispatcher;->mAppHost:Landroidx/car/app/IAppHost;

    .line 128
    iput-object v0, p0, Landroidx/car/app/HostDispatcher;->mNavigationHost:Landroidx/car/app/navigation/INavigationHost;

    return-void
.end method

.method public setCarHost(Landroidx/car/app/ICarHost;)V
    .locals 0

    .line 115
    invoke-static {}, Landroidx/car/app/utils/ThreadUtils;->checkMainThread()V

    .line 117
    invoke-virtual {p0}, Landroidx/car/app/HostDispatcher;->resetHosts()V

    .line 118
    iput-object p1, p0, Landroidx/car/app/HostDispatcher;->mCarHost:Landroidx/car/app/ICarHost;

    return-void
.end method
