package ru.netology


data class Note (
    val id: Int,
    var text: String
)

data class Comment (
    val id: Int,
    val noteID: Int,
    var text: String
)

class NoteNotFoundException (message: String) : RuntimeException (message)

class NoteService {
    private var notes = mutableListOf<Note>()
    private var comments = mutableListOf<Comment>()
    private var deleteComments = mutableListOf<Comment>()
    private var unicumNoteID = 0
    private var unicumCommentID = 0

    fun addNote(note: Note): Note {
        notes += note.copy(id = ++unicumNoteID)
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

    fun createComment(comment: Comment): Comment {
       comments += comment.copy(id = ++unicumCommentID)
        for ((index, note) in notes.withIndex()) {
                return comments.last()
        }
        return throw NoteNotFoundException("Нет похожего ID")
    }

    fun editComment(noteID: Int, commentID: Int, text: String): Comment {
        for ((index, note) in notes.withIndex()) {
            if (notes[index].id == noteID){
            for ((index, comment) in comments.withIndex()) {
                    if (comments[index].id == commentID) {
                        comment.text = text
                        return comments.last()
                    }
                }
            }
        }
        return throw NoteNotFoundException("Нет похожего $commentID")
    }

    fun deleteComment(noteID: Int, commentID: Int): Boolean {
         for ((index, note) in notes.withIndex()) {
             if (notes[index].id == noteID) {
                 for ((index, comment) in comments.withIndex()) {
                     if (comments[index].id == commentID) {
                         comments.remove(comment)
                         deleteComments.add(comment)
                         return true
                     }
                 }
             }
         }
        return throw NoteNotFoundException("Нет похожего $commentID")
    }

    fun restoreComment(noteID: Int, commentID: Int): Comment {
         for ((index, note) in notes.withIndex()) {
             if (notes[index].id == noteID) {
                 for ((index, comment) in deleteComments.withIndex()) {
                     if (deleteComments[index].id == commentID) {
                         deleteComments.remove(comment)
                         comments.add(comment)
                         return comments.last()
                     }
                 }
             }
         }
        return throw NoteNotFoundException("Нет похожего $commentID")
    }

    fun getNotes() : Collection<Note> {
        for (note in notes)
            println(note)
        return notes
    }

    fun getNoteById(id: Int): Note? {
        for ((index, note) in notes.withIndex()) {
            var findNote = notes.find {it.id == id }
            println("Заметка найдена " + findNote)
            return findNote
        }
        return throw NoteNotFoundException("Нет такой заметки с таким $id")
    }

    fun getComments(findNoteID: Int) : Collection<Comment> {
        for ((index, note) in notes.withIndex()) {
            var findAllCommentByNoteID = comments.filter{it.noteID == findNoteID}
                println("Комментарии к заметкие найдены" + findAllCommentByNoteID)
                return findAllCommentByNoteID
            }
        return throw NoteNotFoundException("Нет похожего $findNoteID")
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
    service.addNote(Note(2,"Создаться ли оно?"))
    service.getNotes()
    service.deleteNote(3)
    service.getNotes()
    service.getNoteById(2)
    service.getNotes()
    service.createComment(Comment(1,2,"Оплатил счетчики за воду"))
    service.createComment(Comment(2,1,"Купил продукты на 3 дня"))
    service.createComment(Comment(3,1,"Комментарий на удаление"))
    service.createComment(Comment(2,1,"Создасться ли коммент?"))
    service.readComments()
    service.editComment (2, 1,"Оплатил счет не только за воду, но и за электричество!")
    service.deleteComment(1,3)
    service.readComments()
    service.checkDeleteComments()
    service.restoreComment(1,3)
    service.readComments()
    service.getComments(1)
}