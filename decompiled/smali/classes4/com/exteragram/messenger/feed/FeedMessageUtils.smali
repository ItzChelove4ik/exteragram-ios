.class public abstract Lcom/exteragram/messenger/feed/FeedMessageUtils;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static synthetic $r8$lambda$IXZLP3pS1__oSfuf53vB5B1rwvc(Lorg/telegram/tgnet/TLObject;Lorg/telegram/ui/ChatActivity;)V
    .locals 1

    .line 222
    instance-of v0, p0, Lorg/telegram/tgnet/TLRPC$TL_exportedMessageLink;

    if-eqz v0, :cond_0

    .line 223
    check-cast p0, Lorg/telegram/tgnet/TLRPC$TL_exportedMessageLink;

    iget-object p0, p0, Lorg/telegram/tgnet/TLRPC$TL_exportedMessageLink;->link:Ljava/lang/String;

    .line 224
    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->addToClipboard(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-static {p1}, Lorg/telegram/ui/Components/BulletinFactory;->canShowBulletin(Lorg/telegram/ui/ActionBar/BaseFragment;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 225
    invoke-static {p1}, Lorg/telegram/ui/Components/BulletinFactory;->of(Lorg/telegram/ui/ActionBar/BaseFragment;)Lorg/telegram/ui/Components/BulletinFactory;

    move-result-object p1

    const-string v0, "/c/"

    invoke-virtual {p0, v0}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result p0

    invoke-virtual {p1, p0}, Lorg/telegram/ui/Components/BulletinFactory;->createCopyLinkBulletin(Z)Lorg/telegram/ui/Components/Bulletin;

    move-result-object p0

    invoke-virtual {p0}, Lorg/telegram/ui/Components/Bulletin;->show()Lorg/telegram/ui/Components/Bulletin;

    :cond_0
    return-void
.end method

.method public static synthetic $r8$lambda$XflDMlSYrgi89Wgpq48L5M4D8XE(Lorg/telegram/ui/ChatActivity;Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/TLRPC$TL_error;)V
    .locals 0

    .line 221
    new-instance p2, Lcom/exteragram/messenger/feed/FeedMessageUtils$$ExternalSyntheticLambda1;

    invoke-direct {p2, p1, p0}, Lcom/exteragram/messenger/feed/FeedMessageUtils$$ExternalSyntheticLambda1;-><init>(Lorg/telegram/tgnet/TLObject;Lorg/telegram/ui/ChatActivity;)V

    invoke-static {p2}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;)V

    return-void
.end method

.method public static copyFeedPostLink(Lorg/telegram/ui/ChatActivity;Lorg/telegram/messenger/MessageObject;)V
    .locals 3

    if-eqz p0, :cond_2

    if-nez p1, :cond_0

    goto :goto_0

    .line 214
    :cond_0
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getMessagesController()Lorg/telegram/messenger/MessagesController;

    move-result-object v0

    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getDialogId()J

    move-result-wide v1

    neg-long v1, v1

    invoke-static {v1, v2}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/telegram/messenger/MessagesController;->getChat(Ljava/lang/Long;)Lorg/telegram/tgnet/TLRPC$Chat;

    move-result-object v0

    .line 215
    invoke-static {v0}, Lorg/telegram/messenger/ChatObject;->isChannel(Lorg/telegram/tgnet/TLRPC$Chat;)Z

    move-result v1

    if-nez v1, :cond_1

    goto :goto_0

    .line 218
    :cond_1
    new-instance v1, Lorg/telegram/tgnet/TLRPC$TL_channels_exportMessageLink;

    invoke-direct {v1}, Lorg/telegram/tgnet/TLRPC$TL_channels_exportMessageLink;-><init>()V

    .line 219
    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getRealId()I

    move-result p1

    iput p1, v1, Lorg/telegram/tgnet/TLRPC$TL_channels_exportMessageLink;->id:I

    .line 220
    invoke-static {v0}, Lorg/telegram/messenger/MessagesController;->getInputChannel(Lorg/telegram/tgnet/TLRPC$Chat;)Lorg/telegram/tgnet/TLRPC$InputChannel;

    move-result-object p1

    iput-object p1, v1, Lorg/telegram/tgnet/TLRPC$TL_channels_exportMessageLink;->channel:Lorg/telegram/tgnet/TLRPC$InputChannel;

    .line 221
    invoke-virtual {p0}, Lorg/telegram/ui/ActionBar/BaseFragment;->getConnectionsManager()Lorg/telegram/tgnet/ConnectionsManager;

    move-result-object p1

    new-instance v0, Lcom/exteragram/messenger/feed/FeedMessageUtils$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0}, Lcom/exteragram/messenger/feed/FeedMessageUtils$$ExternalSyntheticLambda0;-><init>(Lorg/telegram/ui/ChatActivity;)V

    invoke-virtual {p1, v1, v0}, Lorg/telegram/tgnet/ConnectionsManager;->sendRequest(Lorg/telegram/tgnet/TLObject;Lorg/telegram/tgnet/RequestDelegate;)I

    :cond_2
    :goto_0
    return-void
.end method

.method private static copyMessage(Lorg/telegram/tgnet/TLRPC$Message;)Lorg/telegram/tgnet/TLRPC$TL_message;
    .locals 3

    .line 246
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_message;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_message;-><init>()V

    .line 247
    iget v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->id:I

    iput v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->id:I

    .line 248
    iget-object v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->from_id:Lorg/telegram/tgnet/TLRPC$Peer;

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->from_id:Lorg/telegram/tgnet/TLRPC$Peer;

    .line 249
    iget v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->from_boosts_applied:I

    iput v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->from_boosts_applied:I

    .line 250
    iget-object v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->peer_id:Lorg/telegram/tgnet/TLRPC$Peer;

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->peer_id:Lorg/telegram/tgnet/TLRPC$Peer;

    .line 251
    iget-object v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->saved_peer_id:Lorg/telegram/tgnet/TLRPC$Peer;

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->saved_peer_id:Lorg/telegram/tgnet/TLRPC$Peer;

    .line 252
    iget v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->date:I

    iput v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->date:I

    .line 253
    iget v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->expire_date:I

    iput v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->expire_date:I

    .line 254
    iget-object v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->action:Lorg/telegram/tgnet/TLRPC$MessageAction;

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->action:Lorg/telegram/tgnet/TLRPC$MessageAction;

    .line 255
    iget-object v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->message:Ljava/lang/String;

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->message:Ljava/lang/String;

    .line 256
    iget-object v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->media:Lorg/telegram/tgnet/TLRPC$MessageMedia;

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->media:Lorg/telegram/tgnet/TLRPC$MessageMedia;

    .line 257
    iget v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->flags:I

    iput v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->flags:I

    .line 258
    iget v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->flags2:I

    iput v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->flags2:I

    .line 259
    iget-boolean v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->mentioned:Z

    iput-boolean v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->mentioned:Z

    .line 260
    iget-boolean v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->media_unread:Z

    iput-boolean v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->media_unread:Z

    .line 261
    iget-boolean v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->out:Z

    iput-boolean v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->out:Z

    .line 262
    iget-boolean v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->unread:Z

    iput-boolean v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->unread:Z

    .line 263
    iget-object v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->entities:Ljava/util/ArrayList;

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->entities:Ljava/util/ArrayList;

    .line 264
    iget-object v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->via_bot_name:Ljava/lang/String;

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->via_bot_name:Ljava/lang/String;

    .line 265
    iget-object v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->reply_markup:Lorg/telegram/tgnet/TLRPC$ReplyMarkup;

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->reply_markup:Lorg/telegram/tgnet/TLRPC$ReplyMarkup;

    .line 266
    iget v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->views:I

    iput v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->views:I

    .line 267
    iget v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->forwards:I

    iput v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->forwards:I

    .line 268
    iget-object v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->replies:Lorg/telegram/tgnet/TLRPC$MessageReplies;

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->replies:Lorg/telegram/tgnet/TLRPC$MessageReplies;

    .line 269
    iget v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->edit_date:I

    iput v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->edit_date:I

    .line 270
    iget-boolean v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->silent:Z

    iput-boolean v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->silent:Z

    .line 271
    iget-boolean v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->post:Z

    iput-boolean v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->post:Z

    .line 272
    iget-boolean v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->from_scheduled:Z

    iput-boolean v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->from_scheduled:Z

    .line 273
    iget-boolean v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->legacy:Z

    iput-boolean v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->legacy:Z

    .line 274
    iget-boolean v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->edit_hide:Z

    iput-boolean v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->edit_hide:Z

    .line 275
    iget-boolean v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->pinned:Z

    iput-boolean v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->pinned:Z

    .line 276
    iget-object v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->fwd_from:Lorg/telegram/tgnet/TLRPC$MessageFwdHeader;

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->fwd_from:Lorg/telegram/tgnet/TLRPC$MessageFwdHeader;

    .line 277
    iget-wide v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->via_bot_id:J

    iput-wide v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->via_bot_id:J

    .line 278
    iget-wide v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->via_business_bot_id:J

    iput-wide v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->via_business_bot_id:J

    .line 279
    iget-object v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->reply_to:Lorg/telegram/tgnet/TLRPC$MessageReplyHeader;

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->reply_to:Lorg/telegram/tgnet/TLRPC$MessageReplyHeader;

    .line 280
    iget-object v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->post_author:Ljava/lang/String;

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->post_author:Ljava/lang/String;

    .line 281
    iget-wide v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->grouped_id:J

    iput-wide v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->grouped_id:J

    .line 282
    iget-object v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->reactions:Lorg/telegram/tgnet/TLRPC$TL_messageReactions;

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->reactions:Lorg/telegram/tgnet/TLRPC$TL_messageReactions;

    .line 283
    iget-object v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->restriction_reason:Ljava/util/ArrayList;

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->restriction_reason:Ljava/util/ArrayList;

    .line 284
    iget v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->ttl_period:I

    iput v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->ttl_period:I

    .line 285
    iget v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->quick_reply_shortcut_id:I

    iput v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->quick_reply_shortcut_id:I

    .line 286
    iget-wide v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->effect:J

    iput-wide v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->effect:J

    .line 287
    iget-boolean v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->noforwards:Z

    iput-boolean v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->noforwards:Z

    .line 288
    iget-boolean v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->invert_media:Z

    iput-boolean v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->invert_media:Z

    .line 289
    iget-boolean v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->offline:Z

    iput-boolean v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->offline:Z

    .line 290
    iget-object v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->factcheck:Lorg/telegram/tgnet/TLRPC$TL_factCheck;

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->factcheck:Lorg/telegram/tgnet/TLRPC$TL_factCheck;

    .line 291
    iget v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->send_state:I

    iput v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->send_state:I

    .line 292
    iget v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->fwd_msg_id:I

    iput v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->fwd_msg_id:I

    .line 293
    iget-object v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->params:Ljava/util/HashMap;

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->params:Ljava/util/HashMap;

    .line 294
    iget-wide v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->random_id:J

    iput-wide v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->random_id:J

    .line 295
    iget v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->local_id:I

    iput v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->local_id:I

    .line 296
    iget-object v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->attachPath:Ljava/lang/String;

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->attachPath:Ljava/lang/String;

    .line 297
    iget-wide v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->dialog_id:J

    iput-wide v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->dialog_id:J

    .line 298
    iget v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->ttl:I

    iput v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->ttl:I

    .line 299
    iget v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->destroyTime:I

    iput v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->destroyTime:I

    .line 300
    iget-wide v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->destroyTimeMillis:J

    iput-wide v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->destroyTimeMillis:J

    .line 301
    iget v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->layer:I

    iput v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->layer:I

    .line 302
    iget v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->seq_in:I

    iput v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->seq_in:I

    .line 303
    iget v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->seq_out:I

    iput v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->seq_out:I

    .line 304
    iget-boolean v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->with_my_score:Z

    iput-boolean v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->with_my_score:Z

    .line 305
    iget-object v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->replyMessage:Lorg/telegram/tgnet/TLRPC$Message;

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->replyMessage:Lorg/telegram/tgnet/TLRPC$Message;

    .line 306
    iget v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->reqId:I

    iput v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->reqId:I

    .line 307
    iget v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->realId:I

    iput v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->realId:I

    .line 308
    iget v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->stickerVerified:I

    iput v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->stickerVerified:I

    .line 309
    iget-boolean v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->isThreadMessage:Z

    iput-boolean v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->isThreadMessage:Z

    .line 310
    iget-object v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->voiceTranscription:Ljava/lang/String;

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->voiceTranscription:Ljava/lang/String;

    .line 311
    iget-boolean v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->voiceTranscriptionOpen:Z

    iput-boolean v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->voiceTranscriptionOpen:Z

    .line 312
    iget-boolean v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->voiceTranscriptionRated:Z

    iput-boolean v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->voiceTranscriptionRated:Z

    .line 313
    iget-boolean v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->voiceTranscriptionFinal:Z

    iput-boolean v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->voiceTranscriptionFinal:Z

    .line 314
    iget-boolean v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->voiceTranscriptionForce:Z

    iput-boolean v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->voiceTranscriptionForce:Z

    .line 315
    iget-wide v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->voiceTranscriptionId:J

    iput-wide v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->voiceTranscriptionId:J

    .line 316
    iget-boolean v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->premiumEffectWasPlayed:Z

    iput-boolean v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->premiumEffectWasPlayed:Z

    .line 317
    iget-object v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->originalLanguage:Ljava/lang/String;

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->originalLanguage:Ljava/lang/String;

    .line 318
    iget-object v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->translatedToLanguage:Ljava/lang/String;

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->translatedToLanguage:Ljava/lang/String;

    .line 319
    iget-object v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->translatedText:Lorg/telegram/tgnet/TLRPC$TL_textWithEntities;

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->translatedText:Lorg/telegram/tgnet/TLRPC$TL_textWithEntities;

    .line 320
    iget-object v1, p0, Lorg/telegram/tgnet/TLRPC$Message;->replyStory:Lorg/telegram/tgnet/tl/TL_stories$StoryItem;

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->replyStory:Lorg/telegram/tgnet/tl/TL_stories$StoryItem;

    .line 321
    iget-object p0, p0, Lorg/telegram/tgnet/TLRPC$Message;->quick_reply_shortcut:Lorg/telegram/tgnet/TLRPC$InputQuickReplyShortcut;

    iput-object p0, v0, Lorg/telegram/tgnet/TLRPC$Message;->quick_reply_shortcut:Lorg/telegram/tgnet/TLRPC$InputQuickReplyShortcut;

    return-object v0
.end method

.method public static copyTranslationState(Lorg/telegram/messenger/MessageObject;Lorg/telegram/messenger/MessageObject;)V
    .locals 1

    if-eqz p0, :cond_1

    if-eqz p1, :cond_1

    if-eq p0, p1, :cond_1

    .line 232
    iget-object p0, p0, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    if-eqz p0, :cond_1

    iget-object p1, p1, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    if-nez p1, :cond_0

    goto :goto_0

    .line 235
    :cond_0
    iget-object v0, p0, Lorg/telegram/tgnet/TLRPC$Message;->translatedText:Lorg/telegram/tgnet/TLRPC$TL_textWithEntities;

    iput-object v0, p1, Lorg/telegram/tgnet/TLRPC$Message;->translatedText:Lorg/telegram/tgnet/TLRPC$TL_textWithEntities;

    .line 236
    iget-object v0, p0, Lorg/telegram/tgnet/TLRPC$Message;->translatedToLanguage:Ljava/lang/String;

    iput-object v0, p1, Lorg/telegram/tgnet/TLRPC$Message;->translatedToLanguage:Ljava/lang/String;

    .line 237
    iget-object v0, p0, Lorg/telegram/tgnet/TLRPC$Message;->translatedVoiceTranscription:Lorg/telegram/tgnet/TLRPC$TL_textWithEntities;

    iput-object v0, p1, Lorg/telegram/tgnet/TLRPC$Message;->translatedVoiceTranscription:Lorg/telegram/tgnet/TLRPC$TL_textWithEntities;

    .line 238
    iget-object v0, p0, Lorg/telegram/tgnet/TLRPC$Message;->translatedPoll:Lorg/telegram/messenger/TranslateController$PollText;

    iput-object v0, p1, Lorg/telegram/tgnet/TLRPC$Message;->translatedPoll:Lorg/telegram/messenger/TranslateController$PollText;

    .line 239
    iget-object v0, p0, Lorg/telegram/tgnet/TLRPC$Message;->summaryText:Lorg/telegram/tgnet/TLRPC$TL_textWithEntities;

    iput-object v0, p1, Lorg/telegram/tgnet/TLRPC$Message;->summaryText:Lorg/telegram/tgnet/TLRPC$TL_textWithEntities;

    .line 240
    iget-boolean v0, p0, Lorg/telegram/tgnet/TLRPC$Message;->summarizedOpen:Z

    iput-boolean v0, p1, Lorg/telegram/tgnet/TLRPC$Message;->summarizedOpen:Z

    .line 241
    iget-object v0, p0, Lorg/telegram/tgnet/TLRPC$Message;->translatedSummaryText:Lorg/telegram/tgnet/TLRPC$TL_textWithEntities;

    iput-object v0, p1, Lorg/telegram/tgnet/TLRPC$Message;->translatedSummaryText:Lorg/telegram/tgnet/TLRPC$TL_textWithEntities;

    .line 242
    iget-object p0, p0, Lorg/telegram/tgnet/TLRPC$Message;->translatedSummaryLanguage:Ljava/lang/String;

    iput-object p0, p1, Lorg/telegram/tgnet/TLRPC$Message;->translatedSummaryLanguage:Ljava/lang/String;

    :cond_1
    :goto_0
    return-void
.end method

.method public static createDateHeader(ILorg/telegram/messenger/MessageObject;I)Lorg/telegram/messenger/MessageObject;
    .locals 7

    .line 80
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_message;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_message;-><init>()V

    .line 81
    iget-object v1, p1, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    iget v1, v1, Lorg/telegram/tgnet/TLRPC$Message;->date:I

    int-to-long v1, v1

    invoke-static {v1, v2}, Lorg/telegram/messenger/LocaleController;->formatDateChat(J)Ljava/lang/String;

    move-result-object v1

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->message:Ljava/lang/String;

    const/4 v1, 0x0

    .line 82
    iput v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->id:I

    .line 83
    invoke-static {}, Ljava/util/Calendar;->getInstance()Ljava/util/Calendar;

    move-result-object v2

    .line 84
    iget-object p1, p1, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    iget p1, p1, Lorg/telegram/tgnet/TLRPC$Message;->date:I

    int-to-long v3, p1

    const-wide/16 v5, 0x3e8

    mul-long/2addr v3, v5

    invoke-virtual {v2, v3, v4}, Ljava/util/Calendar;->setTimeInMillis(J)V

    const/16 p1, 0xb

    .line 85
    invoke-virtual {v2, p1, v1}, Ljava/util/Calendar;->set(II)V

    const/16 p1, 0xc

    .line 86
    invoke-virtual {v2, p1, v1}, Ljava/util/Calendar;->set(II)V

    const/16 p1, 0xd

    .line 87
    invoke-virtual {v2, p1, v1}, Ljava/util/Calendar;->set(II)V

    const/16 p1, 0xe

    .line 88
    invoke-virtual {v2, p1, v1}, Ljava/util/Calendar;->set(II)V

    .line 89
    invoke-virtual {v2}, Ljava/util/Calendar;->getTimeInMillis()J

    move-result-wide v2

    div-long/2addr v2, v5

    long-to-int p1, v2

    iput p1, v0, Lorg/telegram/tgnet/TLRPC$Message;->date:I

    .line 90
    new-instance p1, Lorg/telegram/messenger/MessageObject;

    invoke-direct {p1, p0, v0, v1, v1}, Lorg/telegram/messenger/MessageObject;-><init>(ILorg/telegram/tgnet/TLRPC$Message;ZZ)V

    const/16 p0, 0xa

    .line 91
    iput p0, p1, Lorg/telegram/messenger/MessageObject;->type:I

    const/4 p0, 0x1

    .line 92
    iput p0, p1, Lorg/telegram/messenger/MessageObject;->contentType:I

    .line 93
    iput-boolean p0, p1, Lorg/telegram/messenger/MessageObject;->isDateObject:Z

    .line 94
    iput p2, p1, Lorg/telegram/messenger/MessageObject;->stableId:I

    return-object p1
.end method

.method public static createReplacement(IJLorg/telegram/messenger/MessageObject;)Lorg/telegram/messenger/MessageObject;
    .locals 20

    move-object/from16 v0, p3

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return-object v1

    .line 143
    :cond_0
    invoke-static/range {p0 .. p0}, Lcom/exteragram/messenger/feed/FeedController;->getInstance(I)Lcom/exteragram/messenger/feed/FeedController;

    move-result-object v2

    .line 144
    invoke-virtual {v0}, Lorg/telegram/messenger/MessageObject;->getRealId()I

    move-result v3

    move-wide/from16 v4, p1

    invoke-virtual {v2, v4, v5, v3}, Lcom/exteragram/messenger/feed/FeedController;->getMessage(JI)Lorg/telegram/messenger/MessageObject;

    move-result-object v3

    if-nez v3, :cond_1

    return-object v1

    .line 148
    :cond_1
    iget-object v0, v0, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    invoke-static {v0}, Lcom/exteragram/messenger/feed/FeedMessageUtils;->copyMessage(Lorg/telegram/tgnet/TLRPC$Message;)Lorg/telegram/tgnet/TLRPC$TL_message;

    move-result-object v6

    .line 149
    invoke-virtual {v3}, Lorg/telegram/messenger/MessageObject;->getId()I

    move-result v0

    iput v0, v6, Lorg/telegram/tgnet/TLRPC$Message;->id:I

    .line 150
    invoke-virtual {v3}, Lorg/telegram/messenger/MessageObject;->getRealId()I

    move-result v0

    iput v0, v6, Lorg/telegram/tgnet/TLRPC$Message;->realId:I

    .line 151
    invoke-virtual {v3}, Lorg/telegram/messenger/MessageObject;->getDialogId()J

    move-result-wide v0

    iput-wide v0, v6, Lorg/telegram/tgnet/TLRPC$Message;->dialog_id:J

    .line 152
    new-instance v4, Lorg/telegram/messenger/MessageObject;

    iget-object v7, v3, Lorg/telegram/messenger/MessageObject;->replyMessageObject:Lorg/telegram/messenger/MessageObject;

    const/16 v18, 0x0

    const/16 v19, 0x4

    const/4 v8, 0x0

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x0

    const/4 v12, 0x1

    const/4 v13, 0x1

    const-wide/16 v14, 0x0

    const/16 v16, 0x0

    const/16 v17, 0x0

    move/from16 v5, p0

    invoke-direct/range {v4 .. v19}, Lorg/telegram/messenger/MessageObject;-><init>(ILorg/telegram/tgnet/TLRPC$Message;Lorg/telegram/messenger/MessageObject;Ljava/util/AbstractMap;Ljava/util/AbstractMap;Landroidx/collection/LongSparseArray;Landroidx/collection/LongSparseArray;ZZJZZZI)V

    .line 153
    iget-boolean v0, v3, Lorg/telegram/messenger/MessageObject;->isPrimaryGroupMessage:Z

    iput-boolean v0, v4, Lorg/telegram/messenger/MessageObject;->isPrimaryGroupMessage:Z

    .line 154
    iget-wide v0, v3, Lorg/telegram/messenger/MessageObject;->localGroupId:J

    iput-wide v0, v4, Lorg/telegram/messenger/MessageObject;->localGroupId:J

    .line 155
    invoke-virtual {v4, v3}, Lorg/telegram/messenger/MessageObject;->copyStableParams(Lorg/telegram/messenger/MessageObject;)V

    .line 156
    invoke-virtual {v2, v3, v4}, Lcom/exteragram/messenger/feed/FeedController;->replaceMessage(Lorg/telegram/messenger/MessageObject;Lorg/telegram/messenger/MessageObject;)V

    return-object v4
.end method

.method public static createReplacements(IJLjava/util/ArrayList;)Ljava/util/ArrayList;
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(IJ",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/messenger/MessageObject;",
            ">;)",
            "Ljava/util/ArrayList<",
            "Lorg/telegram/messenger/MessageObject;",
            ">;"
        }
    .end annotation

    .line 161
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    const/4 v1, 0x0

    .line 162
    :goto_0
    invoke-virtual {p3}, Ljava/util/ArrayList;->size()I

    move-result v2

    if-ge v1, v2, :cond_1

    .line 163
    invoke-virtual {p3, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lorg/telegram/messenger/MessageObject;

    invoke-static {p0, p1, p2, v2}, Lcom/exteragram/messenger/feed/FeedMessageUtils;->createReplacement(IJLorg/telegram/messenger/MessageObject;)Lorg/telegram/messenger/MessageObject;

    move-result-object v2

    if-eqz v2, :cond_0

    .line 165
    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    :cond_0
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_1
    return-object v0
.end method

.method public static createUnreadDivider(II)Lorg/telegram/messenger/MessageObject;
    .locals 3

    .line 69
    new-instance v0, Lorg/telegram/tgnet/TLRPC$TL_message;

    invoke-direct {v0}, Lorg/telegram/tgnet/TLRPC$TL_message;-><init>()V

    .line 70
    const-string v1, ""

    iput-object v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->message:Ljava/lang/String;

    const/4 v1, 0x0

    .line 71
    iput v1, v0, Lorg/telegram/tgnet/TLRPC$Message;->id:I

    .line 72
    new-instance v2, Lorg/telegram/messenger/MessageObject;

    invoke-direct {v2, p0, v0, v1, v1}, Lorg/telegram/messenger/MessageObject;-><init>(ILorg/telegram/tgnet/TLRPC$Message;ZZ)V

    const/4 p0, 0x6

    .line 73
    iput p0, v2, Lorg/telegram/messenger/MessageObject;->type:I

    const/4 p0, 0x2

    .line 74
    iput p0, v2, Lorg/telegram/messenger/MessageObject;->contentType:I

    .line 75
    iput p1, v2, Lorg/telegram/messenger/MessageObject;->stableId:I

    return-object v2
.end method

.method public static filterAllowedOptions(Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/ArrayList<",
            "Ljava/lang/CharSequence;",
            ">;",
            "Ljava/util/ArrayList<",
            "Ljava/lang/Integer;",
            ">;",
            "Ljava/util/ArrayList<",
            "Ljava/lang/Integer;",
            ">;)V"
        }
    .end annotation

    .line 201
    invoke-virtual {p1}, Ljava/util/ArrayList;->size()I

    move-result v0

    add-int/lit8 v0, v0, -0x1

    :goto_0
    if-ltz v0, :cond_1

    .line 202
    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/Integer;

    invoke-virtual {v1}, Ljava/lang/Integer;->intValue()I

    move-result v1

    invoke-static {v1}, Lcom/exteragram/messenger/feed/FeedMessageUtils;->isAllowedFeedOption(I)Z

    move-result v1

    if-nez v1, :cond_0

    .line 203
    invoke-virtual {p2, v0}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    .line 204
    invoke-virtual {p0, v0}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    .line 205
    invoke-virtual {p1, v0}, Ljava/util/ArrayList;->remove(I)Ljava/lang/Object;

    :cond_0
    add-int/lit8 v0, v0, -0x1

    goto :goto_0

    :cond_1
    return-void
.end method

.method public static getForwardingMessageObject(IZLorg/telegram/messenger/MessageObject;)Lorg/telegram/messenger/MessageObject;
    .locals 17

    move-object/from16 v0, p2

    if-eqz p1, :cond_1

    if-eqz v0, :cond_1

    .line 125
    invoke-virtual {v0}, Lorg/telegram/messenger/MessageObject;->getId()I

    move-result v1

    invoke-virtual {v0}, Lorg/telegram/messenger/MessageObject;->getRealId()I

    move-result v2

    if-ne v1, v2, :cond_0

    goto :goto_0

    .line 128
    :cond_0
    iget-object v1, v0, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    invoke-static {v1}, Lcom/exteragram/messenger/feed/FeedMessageUtils;->copyMessage(Lorg/telegram/tgnet/TLRPC$Message;)Lorg/telegram/tgnet/TLRPC$TL_message;

    move-result-object v4

    .line 129
    invoke-virtual {v0}, Lorg/telegram/messenger/MessageObject;->getRealId()I

    move-result v1

    iput v1, v4, Lorg/telegram/tgnet/TLRPC$Message;->id:I

    const/4 v1, 0x0

    .line 130
    iput v1, v4, Lorg/telegram/tgnet/TLRPC$Message;->realId:I

    .line 131
    invoke-virtual {v0}, Lorg/telegram/messenger/MessageObject;->getDialogId()J

    move-result-wide v1

    iput-wide v1, v4, Lorg/telegram/tgnet/TLRPC$Message;->dialog_id:J

    .line 132
    new-instance v2, Lorg/telegram/messenger/MessageObject;

    iget-object v5, v0, Lorg/telegram/messenger/MessageObject;->replyMessageObject:Lorg/telegram/messenger/MessageObject;

    const/4 v15, 0x0

    const/16 v16, 0x0

    const/4 v6, 0x0

    const/4 v7, 0x0

    const/4 v8, 0x0

    const/4 v9, 0x0

    const/4 v10, 0x0

    const/4 v11, 0x1

    const-wide/16 v12, 0x0

    const/4 v14, 0x0

    move/from16 v3, p0

    invoke-direct/range {v2 .. v16}, Lorg/telegram/messenger/MessageObject;-><init>(ILorg/telegram/tgnet/TLRPC$Message;Lorg/telegram/messenger/MessageObject;Ljava/util/AbstractMap;Ljava/util/AbstractMap;Landroidx/collection/LongSparseArray;Landroidx/collection/LongSparseArray;ZZJZZZ)V

    .line 133
    iget-boolean v1, v0, Lorg/telegram/messenger/MessageObject;->isPrimaryGroupMessage:Z

    iput-boolean v1, v2, Lorg/telegram/messenger/MessageObject;->isPrimaryGroupMessage:Z

    .line 134
    iget-wide v3, v0, Lorg/telegram/messenger/MessageObject;->localGroupId:J

    iput-wide v3, v2, Lorg/telegram/messenger/MessageObject;->localGroupId:J

    .line 135
    invoke-virtual {v2, v0}, Lorg/telegram/messenger/MessageObject;->copyStableParams(Lorg/telegram/messenger/MessageObject;)V

    return-object v2

    :cond_1
    :goto_0
    return-object v0
.end method

.method public static getInputPeerForMessageRequest(Lorg/telegram/messenger/MessagesController;JZLorg/telegram/messenger/MessageObject;)Lorg/telegram/tgnet/TLRPC$InputPeer;
    .locals 0

    if-eqz p3, :cond_0

    if-eqz p4, :cond_0

    .line 99
    invoke-virtual {p4}, Lorg/telegram/messenger/MessageObject;->getDialogId()J

    move-result-wide p1

    :cond_0
    invoke-virtual {p0, p1, p2}, Lorg/telegram/messenger/MessagesController;->getInputPeer(J)Lorg/telegram/tgnet/TLRPC$InputPeer;

    move-result-object p0

    return-object p0
.end method

.method public static getPlaybackScrollMessageId(ZJLorg/telegram/messenger/MessageObject;)I
    .locals 2

    if-eqz p3, :cond_0

    .line 118
    iget v0, p3, Lorg/telegram/messenger/MessageObject;->searchType:I

    const/4 v1, 0x4

    if-ne v0, v1, :cond_0

    if-nez p0, :cond_0

    invoke-virtual {p3}, Lorg/telegram/messenger/MessageObject;->getDialogId()J

    move-result-wide v0

    cmp-long p0, v0, p1

    if-nez p0, :cond_0

    .line 119
    invoke-virtual {p3}, Lorg/telegram/messenger/MessageObject;->getRealId()I

    move-result p0

    return p0

    :cond_0
    if-eqz p3, :cond_1

    .line 121
    invoke-virtual {p3}, Lorg/telegram/messenger/MessageObject;->getId()I

    move-result p0

    return p0

    :cond_1
    const/4 p0, 0x0

    return p0
.end method

.method public static hasProfileSignature(Lorg/telegram/messenger/MessageObject;)Z
    .locals 0

    .line 46
    invoke-static {p0}, Lcom/exteragram/messenger/feed/FeedMessageUtils;->resolveDisplayChannel(Lorg/telegram/messenger/MessageObject;)Lorg/telegram/tgnet/TLRPC$Chat;

    move-result-object p0

    if-eqz p0, :cond_0

    .line 47
    iget-boolean p0, p0, Lorg/telegram/tgnet/TLRPC$Chat;->signature_profiles:Z

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public static isAllowedDoubleTapAction(I)Z
    .locals 2

    const/4 v0, 0x1

    if-eq p0, v0, :cond_0

    const/4 v1, 0x2

    if-eq p0, v1, :cond_0

    const/4 v1, 0x3

    if-eq p0, v1, :cond_0

    const/4 v1, 0x4

    if-eq p0, v1, :cond_0

    const/4 v1, 0x6

    if-eq p0, v1, :cond_0

    const/16 v1, 0x9

    if-eq p0, v1, :cond_0

    const/4 p0, 0x0

    return p0

    :cond_0
    return v0
.end method

.method public static isAllowedFeedOption(I)Z
    .locals 1

    const/4 v0, 0x2

    if-eq p0, v0, :cond_0

    const/4 v0, 0x3

    if-eq p0, v0, :cond_0

    const/4 v0, 0x4

    if-eq p0, v0, :cond_0

    const/4 v0, 0x6

    if-eq p0, v0, :cond_0

    const/4 v0, 0x7

    if-eq p0, v0, :cond_0

    const/16 v0, 0x8

    if-eq p0, v0, :cond_0

    const/16 v0, 0xa

    if-eq p0, v0, :cond_0

    const/16 v0, 0x10

    if-eq p0, v0, :cond_0

    const/16 v0, 0x16

    if-eq p0, v0, :cond_0

    const/16 v0, 0x19

    if-eq p0, v0, :cond_0

    const/16 v0, 0x24

    if-eq p0, v0, :cond_0

    const/16 v0, 0x73

    if-eq p0, v0, :cond_0

    const/16 v0, 0xc8

    if-eq p0, v0, :cond_0

    const/16 v0, 0xcb

    if-eq p0, v0, :cond_0

    const/16 v0, 0xce

    if-eq p0, v0, :cond_0

    const/16 v0, 0x1c

    if-eq p0, v0, :cond_0

    const/16 v0, 0x1d

    if-eq p0, v0, :cond_0

    const/4 p0, 0x0

    return p0

    :cond_0
    const/4 p0, 0x1

    return p0
.end method

.method public static isAllowedSwipeAction(I)Z
    .locals 1

    const/4 v0, 0x1

    if-eq p0, v0, :cond_0

    .line 193
    invoke-static {p0}, Lcom/exteragram/messenger/feed/FeedMessageUtils;->isAllowedDoubleTapAction(I)Z

    move-result p0

    if-eqz p0, :cond_0

    return v0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public static isPostRow(Lorg/telegram/messenger/MessageObject;)Z
    .locals 2

    if-eqz p0, :cond_0

    .line 24
    iget-boolean v0, p0, Lorg/telegram/messenger/MessageObject;->isDateObject:Z

    if-nez v0, :cond_0

    iget v0, p0, Lorg/telegram/messenger/MessageObject;->type:I

    const/4 v1, 0x6

    if-eq v0, v1, :cond_0

    invoke-virtual {p0}, Lorg/telegram/messenger/MessageObject;->isSponsored()Z

    move-result p0

    if-nez p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public static matchesPlaybackNotification(ILorg/telegram/messenger/MessageObject;I)Z
    .locals 7

    const/4 v0, 0x0

    if-nez p1, :cond_0

    return v0

    .line 106
    :cond_0
    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getId()I

    move-result v1

    const/4 v2, 0x1

    if-ne v1, p2, :cond_1

    return v2

    .line 109
    :cond_1
    invoke-static {p0}, Lcom/exteragram/messenger/feed/FeedController;->peekInstance(I)Lcom/exteragram/messenger/feed/FeedController;

    move-result-object p0

    if-nez p0, :cond_2

    return v0

    .line 113
    :cond_2
    invoke-virtual {p0, p2}, Lcom/exteragram/messenger/feed/FeedController;->resolveRealDialogId(I)J

    move-result-wide v3

    const-wide/16 v5, 0x0

    cmp-long v1, v3, v5

    if-eqz v1, :cond_3

    .line 114
    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getDialogId()J

    move-result-wide v5

    cmp-long v1, v3, v5

    if-nez v1, :cond_3

    invoke-virtual {p0, v3, v4, p2}, Lcom/exteragram/messenger/feed/FeedController;->resolveRealMessageId(JI)I

    move-result p0

    invoke-virtual {p1}, Lorg/telegram/messenger/MessageObject;->getFeedRealId()I

    move-result p1

    if-ne p0, p1, :cond_3

    return v2

    :cond_3
    return v0
.end method

.method public static resolveDisplayChannel(Lorg/telegram/messenger/MessageObject;)Lorg/telegram/tgnet/TLRPC$Chat;
    .locals 5

    const/4 v0, 0x0

    if-eqz p0, :cond_1

    .line 28
    iget-object v1, p0, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    if-eqz v1, :cond_1

    iget v1, p0, Lorg/telegram/messenger/MessageObject;->searchType:I

    const/4 v2, 0x4

    if-ne v1, v2, :cond_1

    iget-boolean v1, p0, Lorg/telegram/messenger/MessageObject;->preview:Z

    if-nez v1, :cond_1

    .line 32
    invoke-virtual {p0}, Lorg/telegram/messenger/MessageObject;->isSponsored()Z

    move-result v1

    if-nez v1, :cond_1

    iget v1, p0, Lorg/telegram/messenger/MessageObject;->type:I

    const/16 v2, 0x1b

    if-eq v1, v2, :cond_1

    iget-object v1, p0, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    iget-boolean v2, v1, Lorg/telegram/tgnet/TLRPC$Message;->post:Z

    if-eqz v2, :cond_1

    iget-object v2, v1, Lorg/telegram/tgnet/TLRPC$Message;->action:Lorg/telegram/tgnet/TLRPC$MessageAction;

    if-nez v2, :cond_1

    iget-object v1, v1, Lorg/telegram/tgnet/TLRPC$Message;->peer_id:Lorg/telegram/tgnet/TLRPC$Peer;

    if-eqz v1, :cond_1

    iget-wide v1, v1, Lorg/telegram/tgnet/TLRPC$Peer;->channel_id:J

    const-wide/16 v3, 0x0

    cmp-long v1, v1, v3

    if-nez v1, :cond_0

    goto :goto_0

    .line 40
    :cond_0
    iget v1, p0, Lorg/telegram/messenger/MessageObject;->currentAccount:I

    invoke-static {v1}, Lorg/telegram/messenger/MessagesController;->getInstance(I)Lorg/telegram/messenger/MessagesController;

    move-result-object v1

    iget-object p0, p0, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    iget-object p0, p0, Lorg/telegram/tgnet/TLRPC$Message;->peer_id:Lorg/telegram/tgnet/TLRPC$Peer;

    iget-wide v2, p0, Lorg/telegram/tgnet/TLRPC$Peer;->channel_id:J

    .line 41
    invoke-static {v2, v3}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object p0

    invoke-virtual {v1, p0}, Lorg/telegram/messenger/MessagesController;->getChat(Ljava/lang/Long;)Lorg/telegram/tgnet/TLRPC$Chat;

    move-result-object p0

    .line 42
    invoke-static {p0}, Lorg/telegram/messenger/ChatObject;->isChannelAndNotMegaGroup(Lorg/telegram/tgnet/TLRPC$Chat;)Z

    move-result v1

    if-eqz v1, :cond_1

    return-object p0

    :cond_1
    :goto_0
    return-object v0
.end method

.method public static shouldMergePosts(Lorg/telegram/messenger/MessageObject;Lorg/telegram/tgnet/TLRPC$Chat;Lorg/telegram/messenger/MessageObject;Lorg/telegram/tgnet/TLRPC$Chat;)Z
    .locals 4

    if-eqz p1, :cond_0

    if-eqz p3, :cond_0

    .line 56
    iget-wide v0, p1, Lorg/telegram/tgnet/TLRPC$Chat;->id:J

    iget-wide v2, p3, Lorg/telegram/tgnet/TLRPC$Chat;->id:J

    cmp-long p1, v0, v2

    if-nez p1, :cond_0

    iget p1, p0, Lorg/telegram/messenger/MessageObject;->contentType:I

    iget p3, p2, Lorg/telegram/messenger/MessageObject;->contentType:I

    if-ne p1, p3, :cond_0

    iget-object p1, p0, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    iget-object p1, p1, Lorg/telegram/tgnet/TLRPC$Message;->reply_markup:Lorg/telegram/tgnet/TLRPC$ReplyMarkup;

    instance-of p1, p1, Lorg/telegram/tgnet/TLRPC$TL_replyInlineMarkup;

    if-nez p1, :cond_0

    .line 61
    invoke-virtual {p0}, Lorg/telegram/messenger/MessageObject;->isOutOwner()Z

    move-result p1

    invoke-virtual {p2}, Lorg/telegram/messenger/MessageObject;->isOutOwner()Z

    move-result p3

    if-ne p1, p3, :cond_0

    iget-object p1, p0, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    iget p1, p1, Lorg/telegram/tgnet/TLRPC$Message;->date:I

    iget-object p3, p2, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    iget p3, p3, Lorg/telegram/tgnet/TLRPC$Message;->date:I

    sub-int/2addr p1, p3

    .line 62
    invoke-static {p1}, Ljava/lang/Math;->abs(I)I

    move-result p1

    const/16 p3, 0x12c

    if-gt p1, p3, :cond_0

    iget-object p1, p2, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    iget-wide v0, p1, Lorg/telegram/tgnet/TLRPC$Message;->paid_message_stars:J

    const-wide/16 v2, 0x0

    cmp-long p1, v0, v2

    if-gtz p1, :cond_0

    iget-object p0, p0, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    iget-object p0, p0, Lorg/telegram/tgnet/TLRPC$Message;->guestchat_via_from:Lorg/telegram/tgnet/TLRPC$Peer;

    .line 64
    invoke-static {p0}, Lorg/telegram/messenger/DialogObject;->getPeerDialogId(Lorg/telegram/tgnet/TLRPC$Peer;)J

    move-result-wide p0

    iget-object p2, p2, Lorg/telegram/messenger/MessageObject;->messageOwner:Lorg/telegram/tgnet/TLRPC$Message;

    iget-object p2, p2, Lorg/telegram/tgnet/TLRPC$Message;->guestchat_via_from:Lorg/telegram/tgnet/TLRPC$Peer;

    .line 65
    invoke-static {p2}, Lorg/telegram/messenger/DialogObject;->getPeerDialogId(Lorg/telegram/tgnet/TLRPC$Peer;)J

    move-result-wide p2

    cmp-long p0, p0, p2

    if-nez p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method
