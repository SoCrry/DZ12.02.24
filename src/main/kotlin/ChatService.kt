object ChatService {
    private val chats = mutableMapOf<Int, Chat>()

    fun clear() {
        chats.clear()
    }

    fun addMessage(userId: Int, message: Message) {
        chats.getOrPut(userId) { Chat() }.messages += message
    }

    fun deleteMessage(userId: Int, messageId: Int): Boolean {
        return chats[userId]?.messages?.find { it.id == messageId }?.also {
            it.isDeleted = true
            if ((chats[userId]?.messages?.count { message: Message -> !message.isDeleted } ?: 0) == 0) {
                deleteChats(userId)
            }
        } != null
    }

    fun deleteChats(userId: Int): Boolean {
        return if (chats[userId] != null) {
            chats.remove(userId)
            true
        } else {
            false
        }
    }

    fun getLastMessage() =
        chats.values.map { it.messages.lastOrNull { message: Message -> !message.isDeleted }?.text ?: "Нет сообщений" }

    fun getChats(userId: Int) =
        chats[userId]?.messages?.lastOrNull { !it.isDeleted } ?: "Нет сообщений"

    fun getChatsMessage(userId: Int, messageId: Int) =
        chats[userId]?.messages?.find { it.id == messageId }

    fun getUnreadChatsCount() =
        chats.values.count { it.messages.any { message: Message -> !message.isDeleted && !message.read } }


    fun getMessage(userId: Int, lastMessageId: Int, countMessage: Int) =
        chats[userId]?.messages?.filter { it.id >= lastMessageId && !it.isDeleted }?.take(countMessage)
            ?.onEach { it.read = true }

    fun printChats() {
        println(chats)
    }
}