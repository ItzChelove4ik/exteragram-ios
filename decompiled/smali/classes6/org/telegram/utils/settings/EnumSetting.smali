.class public final Lorg/telegram/utils/settings/EnumSetting;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Enum<",
        "TT;>;>",
        "Ljava/lang/Object;"
    }
.end annotation


# instance fields
.field private final defaultValue:Ljava/lang/Enum;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "TT;"
        }
    .end annotation
.end field

.field private volatile loaded:Z

.field private final name:Ljava/lang/String;

.field private volatile value:Ljava/lang/Enum;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "TT;"
        }
    .end annotation
.end field


# direct methods
.method private constructor <init>(Ljava/lang/String;Ljava/lang/Enum;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "TT;)V"
        }
    .end annotation

    .line 12
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 13
    iput-object p1, p0, Lorg/telegram/utils/settings/EnumSetting;->name:Ljava/lang/String;

    .line 14
    iput-object p2, p0, Lorg/telegram/utils/settings/EnumSetting;->defaultValue:Ljava/lang/Enum;

    return-void
.end method

.method public static of(Ljava/lang/String;Ljava/lang/Enum;)Lorg/telegram/utils/settings/EnumSetting;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Enum<",
            "TT;>;>(",
            "Ljava/lang/String;",
            "TT;)",
            "Lorg/telegram/utils/settings/EnumSetting<",
            "TT;>;"
        }
    .end annotation

    .line 18
    new-instance v0, Lorg/telegram/utils/settings/EnumSetting;

    invoke-direct {v0, p0, p1}, Lorg/telegram/utils/settings/EnumSetting;-><init>(Ljava/lang/String;Ljava/lang/Enum;)V

    return-object v0
.end method

.method private readValue()Ljava/lang/Enum;
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()TT;"
        }
    .end annotation

    .line 41
    invoke-static {}, Lorg/telegram/utils/settings/SettingsPreferences;->get()Landroid/content/SharedPreferences;

    move-result-object v0

    iget-object v1, p0, Lorg/telegram/utils/settings/EnumSetting;->name:Ljava/lang/String;

    iget-object v2, p0, Lorg/telegram/utils/settings/EnumSetting;->defaultValue:Ljava/lang/Enum;

    invoke-virtual {v2}, Ljava/lang/Enum;->name()Ljava/lang/String;

    move-result-object v2

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 44
    :try_start_0
    iget-object v1, p0, Lorg/telegram/utils/settings/EnumSetting;->defaultValue:Ljava/lang/Enum;

    invoke-virtual {v1}, Ljava/lang/Enum;->getDeclaringClass()Ljava/lang/Class;

    move-result-object v1

    invoke-static {v1, v0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    .line 48
    :catch_0
    :cond_0
    iget-object p0, p0, Lorg/telegram/utils/settings/EnumSetting;->defaultValue:Ljava/lang/Enum;

    return-object p0
.end method


# virtual methods
.method public get()Ljava/lang/Enum;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()TT;"
        }
    .end annotation

    .line 22
    iget-boolean v0, p0, Lorg/telegram/utils/settings/EnumSetting;->loaded:Z

    if-nez v0, :cond_1

    .line 23
    monitor-enter p0

    .line 24
    :try_start_0
    iget-boolean v0, p0, Lorg/telegram/utils/settings/EnumSetting;->loaded:Z

    if-nez v0, :cond_0

    .line 25
    invoke-direct {p0}, Lorg/telegram/utils/settings/EnumSetting;->readValue()Ljava/lang/Enum;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/utils/settings/EnumSetting;->value:Ljava/lang/Enum;

    const/4 v0, 0x1

    .line 26
    iput-boolean v0, p0, Lorg/telegram/utils/settings/EnumSetting;->loaded:Z

    goto :goto_0

    :catchall_0
    move-exception v0

    goto :goto_1

    .line 28
    :cond_0
    :goto_0
    monitor-exit p0

    goto :goto_2

    :goto_1
    monitor-exit p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0

    .line 30
    :cond_1
    :goto_2
    iget-object p0, p0, Lorg/telegram/utils/settings/EnumSetting;->value:Ljava/lang/Enum;

    return-object p0
.end method

.method public declared-synchronized set(Ljava/lang/Enum;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(TT;)V"
        }
    .end annotation

    monitor-enter p0

    .line 34
    :try_start_0
    iput-object p1, p0, Lorg/telegram/utils/settings/EnumSetting;->value:Ljava/lang/Enum;

    const/4 v0, 0x1

    .line 35
    iput-boolean v0, p0, Lorg/telegram/utils/settings/EnumSetting;->loaded:Z

    .line 36
    invoke-static {}, Lorg/telegram/utils/settings/SettingsPreferences;->get()Landroid/content/SharedPreferences;

    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    iget-object v1, p0, Lorg/telegram/utils/settings/EnumSetting;->name:Ljava/lang/String;

    invoke-virtual {p1}, Ljava/lang/Enum;->name()Ljava/lang/String;

    move-result-object p1

    invoke-interface {v0, v1, p1}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object p1

    invoke-interface {p1}, Landroid/content/SharedPreferences$Editor;->apply()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 37
    monitor-exit p0

    return-void

    :catchall_0
    move-exception p1

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw p1
.end method
