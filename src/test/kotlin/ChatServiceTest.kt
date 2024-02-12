import org.junit.Assert.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ChatServiceTest{
    @Before
    fun clear()
    {
        ChatService.clear()
    }


    @Test
    fun addMessage() {
        ChatService.addMessage(1, Message(1, "Кот", incoming = true))
        Assert.assertEquals(
            Message(id = 1, text = "Кот", isDeleted = false, read = false, incoming = true),
            ChatService.getChats(1)
        )
    }

    @Test
    fun deleteMessage() {
        ChatService.addMessage(1, Message(1, "Одна кошка", incoming = true))
        ChatService.addMessage(1, Message(2, "Две кошки", incoming = true))
        ChatService.deleteMessage(1, 1)
        Assert.assertEquals(
            Message(id = 2, text = "Две кошки", isDeleted = false, read = false, incoming = true),
            ChatService.getChats(1)
        )
    }

    @Test
    fun deleteMessageLast() {
        ChatService.addMessage(1, Message(1, "Кот", incoming = true))
        ChatService.deleteMessage(1, 1)
        Assert.assertEquals(null, ChatService.getChatsMessage(1, 1))
    }

    @Test
    fun deleteChats() {
        ChatService.addMessage(1, Message(1, "Кот", incoming = true))
        Assert.assertTrue(ChatService.deleteChats(1))
    }

    @Test
    fun getLastMessage() {
        ChatService.addMessage(1, Message(1, "Кот", incoming = true))
        Assert.assertEquals(listOf("Кот"), ChatService.getLastMessage())
    }

    @Test
    fun getChats() {
        ChatService.addMessage(1, Message(1, "Кот", incoming = true))
        Assert.assertEquals(
            Message(id = 1, text = "Кот", isDeleted = false, read = false, incoming = true),
            ChatService.getChats(1)
        )
    }

    @Test
    fun getUnreadChatsCount() {
        ChatService.addMessage(1, Message(1, "Кот", incoming = true))
        Assert.assertEquals(1, ChatService.getUnreadChatsCount())
    }


    @Test
    fun getMessage() {

        ChatService.addMessage(1, Message(1, "Одна кошка", incoming = true))
        ChatService.addMessage(1, Message(2, "Две кошки", incoming = true))
        ChatService.addMessage(1, Message(3, "Три кошки", incoming = true))
        ChatService.printChats()
        Assert.assertEquals(
            listOf(
                Message(id = 2, text = "Две кошки", isDeleted = false, read = true, incoming = true),
                Message(id = 3, text = "Три кошки", isDeleted = false, read = true, incoming = true)
            ), ChatService.getMessage(1, 2, 2)
        )
    }

}