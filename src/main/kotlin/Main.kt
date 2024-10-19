package ru.netology


data class Note (
    val id: Int,
    var text: String
)

data class Comment (
    val id: Int,
    var text: String,
    //var checkDeleted: Boolean
)

class NoteNotFoundException (message: String) : RuntimeException (message)

class NoteService {
    private var notes = mutableListOf<Note>()
    private var comments = mutableListOf<Comment>()
    private var deleteComments = mutableListOf<Comment>()
    private var unicumID = 0

    fun addNote(note: Note): Note {
        notes += note.copy(id = ++unicumID)
        return notes.last()
    }

    fun editNote(id: Int, text: String): Note {
        for ((index, note) in notes.withIndex()) {
            if (notes[index].id == id) {
                note.text = text
                return notes.last()
            }
        }
        return throw NoteNotFoundException("Нет похожего $id")
    }

    fun deleteNote(id: Int): Boolean {
        for ((index, note) in notes.withIndex()) {
            if (notes[index].id == id) {
                notes.remove(note)
                return true
            }
        }
        return throw NoteNotFoundException("Нет похожего $id")
    }

    fun createComment(noteID: Int, comment: Comment): Comment {
        for ((index, note) in notes.withIndex()) {
            if (notes[index].id == noteID) {
                comments.add(comment)
                //comments += note.copy(text = comment.text)
                return comments.last()
            }
        }
        return throw NoteNotFoundException("Нет похожего $noteID")
    }

    fun editComment(commentID: Int, text: String): Comment {
        //for ((index, note) in notes.withIndex()) {
            for ((index, comment) in comments.withIndex()) {
                //if (notes[index].id == noteID) {
                    if (comments[index].id == commentID) {
                        comment.text = text
                        return comments.last()
                    }
               // }
            //}
        }
        return throw NoteNotFoundException("Нет похожего $commentID")
    }

    fun deleteComment(commentID: Int): Boolean {
        //for ((index, note) in notes.withIndex()) {
            for ((index, comment) in comments.withIndex()) {
                //if (notes[index].id == noteID) {
                if (comments[index].id == commentID){
                        comments.remove(comment)
                        deleteComments.add(comment)
                        return true
                    }
                }
            //}

        return throw NoteNotFoundException("Нет похожего $commentID")
    }

    fun restoreComment(commentID: Int): Comment {
        //for ((index, note) in notes.withIndex()) {
            for ((index, comment) in deleteComments.withIndex()) {
                //if (notes[index].id == noteID) {
                  if (deleteComments[index].id == commentID){
                    deleteComments.remove(comment)
                    comments.add(comment)
                    return comments.last()
                }
            }
        //}
        return throw NoteNotFoundException("Нет похожего $commentID")
    }

    fun getNoteById(id: Int): Note? {
        for ((index, note) in notes.withIndex()) {
            return notes.find { it.id == id }
        }
        return throw NoteNotFoundException("Нет такой заметки с таким $id")
    }


    fun readNotes() {
        for (note in notes)
            print(note)
        println()
    }

    fun readComments() {
        for (comment in comments)
            print(comment)
        println()
    }


    fun checkDeleteComments() {
        for (comment in deleteComments)
            print(comment)
        println()
    }
}




fun main() {
    val service = NoteService()
    service.addNote(Note(1, "Купить продукты!"))
    service.addNote(Note(2,"Оплатить ЖКХ"))
    service.editNote(2,"Оплатить не только ЖКХ!")
    service.addNote(Note(3, "Заметка на удаление"))
    service.deleteNote(3)
    service.readNotes()
    service.createComment(2, Comment(1,"Оплатил счетчики за воду"))
    service.createComment(1, Comment(2,"Купил продукты на 3 дня"))
    service.createComment(1, Comment(3, "Комментарий на удаление"))
    service.readComments()
    service.editComment (1, "Оплатил счет не только за воду, но и за электричество!")
    service.deleteComment(3)
    service.readComments()
    service.checkDeleteComments()
    service.restoreComment(1)
    service.readComments()
}