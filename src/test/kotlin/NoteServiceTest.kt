import org.junit.Test

import ru.netology.Comment
import ru.netology.Note
import ru.netology.NoteNotFoundException
import ru.netology.NoteService

class NoteServiceTest {

    @Test(expected = NoteNotFoundException::class)
    fun notFoundDeleteComment() {
        var checkresult = NoteService()
        val createNote = checkresult.addNote(Note(1, "Прекрасный день"))
        val createComment = checkresult.createComment(1, Comment(1, "Чудесный день!"))
        val deleteComment = checkresult.deleteComment(5)
    }


    @Test
    fun rightDeleteComment() {
        var checkresult = NoteService()
        val createNote = checkresult.addNote(Note(1, "Прекрасный день"))
        val createComment = checkresult.createComment(1, Comment(1, "Чудесный день!"))
        val deleteComment = checkresult.deleteComment(1)
    }

    @Test
    fun rightRestoreComment() {
        var checkresult = NoteService()
        val createNote = checkresult.addNote(Note(1, "Прекрасный день"))
        val createComment = checkresult.createComment(1, Comment(1, "Чудесный день!"))
        val newComment = checkresult.createComment(1, Comment(2,"Замечательный день!"))
        val deleteComment = checkresult.deleteComment(1)
        val restoreComment = checkresult.restoreComment(1)
    }


    @Test(expected = NoteNotFoundException::class)
    fun notFoundRestoreComment() {
        var checkresult = NoteService()
        val createNote = checkresult.addNote(Note(1, "Прекрасный день"))
        val createComment = checkresult.createComment(1, Comment(1, "Чудесный день!"))
        val newComment = checkresult.createComment(1, Comment(2,"Замечательный день!"))
        val deleteComment = checkresult.deleteComment(1)
        val restoreComment = checkresult.restoreComment(5)
    }

}
